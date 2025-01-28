package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under the Unlicense: https://unlicense.org/

 */

  final class BitShiftAccumulator implements IShiftAccumulator {
    private static final int SmallBitLength = 32;
    private int bitLeftmost;

    public final int getLastDiscardedDigit() {
        return this.bitLeftmost;
      }

    private int bitsAfterLeftmost;

    public final int getOlderDiscardedDigits() {
        return this.bitsAfterLeftmost;
      }

    private EInteger shiftedBigInt;
    private FastInteger knownBitLength;

    public FastInteger GetDigitLength() {
      this.knownBitLength = (this.knownBitLength == null) ? (this.CalcKnownBitLength()) : this.knownBitLength;
      return FastInteger.CopyFrozen(this.knownBitLength);
    }

    public FastInteger OverestimateDigitLength() {
       return this.GetDigitLength();
    }

    private static void VerifyKnownLength() {
    }

    public void ShiftToDigits(
      FastInteger bits,
      FastInteger preShift,
      boolean truncate) {
      if (bits.signum() < 0) {
        throw new IllegalArgumentException("bits's sign(" + bits.signum() +
          ") is less than 0");
      }
      if (preShift != null && preShift.signum() > 0) {
        this.knownBitLength = (this.knownBitLength == null) ? (this.CalcKnownBitLength()) : this.knownBitLength;
        // System.out.println("bits=" + bits + " pre=" + preShift + " known=" +
        // (//kbl) + " [" + this.shiftedBigInt + "]");
        if (this.knownBitLength.compareTo(bits) <= 0) {
          // Known digit length is already small enough
          // NOTE: For BitShiftAccumulator, truncating and shifting
          // are the same, unlike in DigitShiftAccumulator
          this.ShiftRight(preShift);
          VerifyKnownLength();
          return;
        } else {
          FastInteger bitDiff = this.knownBitLength.Copy()
            .Subtract(bits);
          // System.out.println("bitDiff=" + bitDiff);
          int cmp = bitDiff.compareTo(preShift);
          if (cmp <= 0) {
            // NOTE: For BitShiftAccumulator, truncating and shifting
            // are the same, unlike in DigitShiftAccumulator
            this.ShiftRight(preShift);
            VerifyKnownLength();
            return;
          } else {
            // NOTE: For BitShiftAccumulator, truncating and shifting
            // are the same, unlike in DigitShiftAccumulator
            this.ShiftRight(bitDiff);
            VerifyKnownLength();
            return;
          }
        }
      }
      if (bits.CanFitInInt32()) {
        this.ShiftToDigitsInt(bits.ToInt32());
        VerifyKnownLength();
      } else {
        this.knownBitLength = (this.knownBitLength == null) ? (this.CalcKnownBitLength()) : this.knownBitLength;
        EInteger bigintDiff = this.knownBitLength.ToEInteger();
        EInteger bitsBig = bits.ToEInteger();
        bigintDiff = bigintDiff.Subtract(bitsBig);
        if (bigintDiff.signum() > 0) {
          // current length is greater than the
          // desired bit length
          this.ShiftRight(FastInteger.FromBig(bigintDiff));
        }
        VerifyKnownLength();
      }
    }

    private int shiftedSmall;
    private boolean isSmall;

    public final EInteger getShiftedInt() {
        return this.isSmall ? (EInteger.FromInt32(this.shiftedSmall)) :
          this.shiftedBigInt;
      }

    public final FastInteger getShiftedIntFast() {
        return this.isSmall ? new FastInteger(this.shiftedSmall) :
          FastInteger.FromBig(this.shiftedBigInt);
      }

    public int ShiftedIntMod(int mod) {
      if (mod < 1) {
        throw new IllegalArgumentException("mod (" + mod +
           ") is not greater or equal to 1");
      }
      switch (mod) {
        case 1:
          return 0;
        case 2:
          return this.isSmall ? (this.shiftedSmall & 1) :
            (this.shiftedBigInt.isEven() ? 0 : 1);
        default:
          return this.isSmall ? (this.shiftedSmall % mod) :
            this.shiftedBigInt.Mod(mod).ToInt32Checked();
      }
    }

    private FastInteger discardedBitCount;

    public final FastInteger getDiscardedDigitCount() {
        return this.discardedBitCount;
      }

    public BitShiftAccumulator(
      EInteger bigint,
      int lastDiscarded,
      int olderDiscarded) {
      if (bigint.signum() < 0) {
        throw new IllegalArgumentException("bigint's sign(" + bigint.signum() +
          ") is less than 0");
      }
      if (bigint.CanFitInInt32()) {
        this.isSmall = true;
        this.shiftedSmall = bigint.ToInt32Checked();
      } else {
        this.shiftedBigInt = bigint;
      }
      this.discardedBitCount = new FastInteger(0);
      this.bitsAfterLeftmost = (olderDiscarded != 0) ? 1 : 0;
      this.bitLeftmost = (lastDiscarded != 0) ? 1 : 0;
    }

    public BitShiftAccumulator(
      int smallint,
      int lastDiscarded,
      int olderDiscarded) {
      this.shiftedSmall = smallint;
      if (this.shiftedSmall < 0) {
        throw new IllegalArgumentException("shiftedSmall(" + this.shiftedSmall +
          ") is less than 0");
      }
      this.isSmall = true;
      this.discardedBitCount = new FastInteger(0);
      this.bitsAfterLeftmost = (olderDiscarded != 0) ? 1 : 0;
      this.bitLeftmost = (lastDiscarded != 0) ? 1 : 0;
    }

    public static BitShiftAccumulator FromInt32(int smallNumber) {
      if (smallNumber < 0) {
        throw new IllegalArgumentException("smallNumber(" + smallNumber +
          ") is less than 0");
      }
      BitShiftAccumulator bsa = new BitShiftAccumulator(EInteger.FromInt32(0), 0, 0);
      bsa.shiftedSmall = smallNumber;
      bsa.discardedBitCount = new FastInteger(0);
      bsa.isSmall = true;
      return bsa;
    }

    public void TruncateOrShiftRight(FastInteger fastint, boolean truncate) {
      this.ShiftRight(fastint);
    }

    public void ShiftRight(FastInteger fastint) {
      if (fastint.signum() <= 0) {
        return;
      }
      if (fastint.CanFitInInt32()) {
        this.ShiftRightInt(fastint.ToInt32());
      } else {
        EInteger bi = fastint.ToEInteger();
        while (bi.signum() > 0) {
          int count = 1000000;
          if (bi.compareTo(EInteger.FromInt64(1000000)) < 0) {
            count = bi.ToInt32Checked();
          }
          this.ShiftRightInt(count);
          bi = bi.Subtract(EInteger.FromInt32(count));
          if (this.isSmall ? this.shiftedSmall == 0 :
            this.shiftedBigInt.isZero()) {
            break;
          }
        }
      }
    }

    private void ShiftRightBig(int bits) {
      if (bits <= 0) {
        return;
      }
      if (this.shiftedBigInt.isZero()) {
        this.discardedBitCount.AddInt(bits);
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = 0;
        this.isSmall = true;
        this.shiftedSmall = 0;
        this.knownBitLength = new FastInteger(1);
        return;
      }
      this.knownBitLength = (this.knownBitLength == null) ? (this.CalcKnownBitLength()) : this.knownBitLength;
      this.discardedBitCount.AddInt(bits);
      int cmp = this.knownBitLength.CompareToInt(bits);
      if (cmp < 0) {
        // too few bits
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitsAfterLeftmost |= this.shiftedBigInt.isZero() ? 0 : 1;
        this.bitLeftmost = 0;
        this.isSmall = true;
        this.shiftedSmall = 0;
        this.knownBitLength = new FastInteger(1);
      } else {
        // enough bits in the current value
        int bs = bits;
        this.knownBitLength.SubtractInt(bits);
        if (bs == 1) {
          boolean odd = !this.shiftedBigInt.isEven();
          this.shiftedBigInt = shiftedBigInt.ShiftRight(1);
          this.bitsAfterLeftmost |= this.bitLeftmost;
          this.bitLeftmost = odd ? 1 : 0;
        } else {
          this.bitsAfterLeftmost |= this.bitLeftmost;
          long lowestSet = this.shiftedBigInt.GetLowBitAsInt64();
          if (lowestSet == Long.MAX_VALUE) {
            EInteger lowestSetBit = this.shiftedBigInt.GetLowBitAsEInteger();
            if (lowestSetBit.compareTo(bs - 1) < 0) {
              // One of the discarded bits after
              // the last one is set
              this.bitsAfterLeftmost |= 1;
              this.bitLeftmost = this.shiftedBigInt.GetSignedBit(bs - 1) ? 1 :
0;
            } else if (lowestSetBit.compareTo(bs - 1) > 0) {
              // Means all discarded bits are zero
              this.bitLeftmost = 0;
            } else {
              // Only the last discarded bit is set
              this.bitLeftmost = 1;
            }
          } else {
            if (lowestSet < bs - 1) {
              // One of the discarded bits after
              // the last one is set
              this.bitsAfterLeftmost |= 1;
              this.bitLeftmost = this.shiftedBigInt.GetSignedBit(bs - 1) ? 1 :
0;
            } else if (lowestSet > bs - 1) {
              // Means all discarded bits are zero
              this.bitLeftmost = 0;
            } else {
              // Only the last discarded bit is set
              this.bitLeftmost = 1;
            }
          }
          this.shiftedBigInt = shiftedBigInt.ShiftRight(bs);
        }
        if (this.knownBitLength.CompareToInt(SmallBitLength) < 0) {
          // Shifting to small number of bits,
          // convert to small integer
          this.isSmall = true;
          this.shiftedSmall = this.shiftedBigInt.ToInt32Checked();
        }
        this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
      }
    }

    private FastInteger CalcKnownBitLength() {
      if (this.isSmall) {
        int kb = SmallBitLength;
        for (int i = SmallBitLength - 1; i >= 0; --i) {
          if ((this.shiftedSmall & (1 << i)) != 0) {
            break;
          }
          --kb;
        }
        // Make sure bit length is 1 if value is 0
        if (kb == 0) {
          ++kb;
        }
        return new FastInteger(kb);
      }
      if (this.shiftedBigInt.isZero()) {
         { return new FastInteger(1);
      }
}
      long sbe = this.shiftedBigInt.GetSignedBitLengthAsInt64();
      return (sbe < Integer.MAX_VALUE) ? new FastInteger((int)sbe) :
FastInteger.FromBig(this.shiftedBigInt.GetSignedBitLengthAsEInteger());
    }

    private void ShiftBigToBits(int bits) {
      // Shifts a number until it reaches the specified number of bits,
      // gathering information on whether the last bit discarded is set and
      // whether the discarded bits to the right of that bit are set. Assumes
      // that the big integer being shifted is positive.
      if (this.knownBitLength != null) {
        if (this.knownBitLength.CompareToInt(bits) <= 0) {
          return;
        }
      }
      this.knownBitLength = (this.knownBitLength == null) ? (this.CalcKnownBitLength()) : this.knownBitLength;
      if (this.knownBitLength.CompareToInt(bits) <= 0) {
        return;
      }
      // Shift by the difference in bit length
      if (this.knownBitLength.CompareToInt(bits) > 0) {
        int bs = 0;
        if (this.knownBitLength.CanFitInInt32()) {
          bs = this.knownBitLength.ToInt32();
          bs -= bits;
        } else {
          FastInteger bitShift =
            this.knownBitLength.Copy().SubtractInt(bits);
          if (!bitShift.CanFitInInt32()) {
            this.ShiftRight(bitShift);
            return;
          }
          bs = bitShift.ToInt32();
        }
        this.knownBitLength.SetInt(bits);
        this.discardedBitCount.AddInt(bs);
        if (bs == 1) {
          boolean odd = !this.shiftedBigInt.isEven();
          this.shiftedBigInt = shiftedBigInt.ShiftRight(1);
          this.bitsAfterLeftmost |= this.bitLeftmost;
          this.bitLeftmost = odd ? 1 : 0;
        } else {
          this.bitsAfterLeftmost |= this.bitLeftmost;
          long lowestSet = this.shiftedBigInt.GetLowBitAsInt64();
          if (lowestSet == Long.MAX_VALUE) {
            EInteger lowestSetBit = this.shiftedBigInt.GetLowBitAsEInteger();
            if (lowestSetBit.compareTo(bs - 1) < 0) {
              // One of the discarded bits after
              // the last one is set
              this.bitsAfterLeftmost |= 1;
              this.bitLeftmost = this.shiftedBigInt.GetSignedBit(bs - 1) ? 1 :
0;
            } else if (lowestSetBit.compareTo(bs - 1) > 0) {
              // Means all discarded bits are zero
              this.bitLeftmost = 0;
            } else {
              // Only the last discarded bit is set
              this.bitLeftmost = 1;
            }
          } else {
            if (lowestSet < bs - 1) {
              // One of the discarded bits after
              // the last one is set
              this.bitsAfterLeftmost |= 1;
              this.bitLeftmost = this.shiftedBigInt.GetSignedBit(bs - 1) ? 1 :
0;
            } else if (lowestSet > bs - 1) {
              // Means all discarded bits are zero
              this.bitLeftmost = 0;
            } else {
              // Only the last discarded bit is set
              this.bitLeftmost = 1;
            }
          }
          this.shiftedBigInt = shiftedBigInt.ShiftRight(bs);
        }
        if (bits < SmallBitLength) {
          // Shifting to small number of bits,
          // convert to small integer
          this.isSmall = true;
          this.shiftedSmall = this.shiftedBigInt.ToInt32Checked();
        }
        this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
      }
    }

    public void ShiftRightInt(int bits) {
      // <summary>Shifts a number to the right, gathering information on
      // whether the last bit discarded is set and whether the discarded
      // bits to the right of that bit are set. Assumes that the big integer
      // being shifted is positive.</summary>
      if (this.isSmall) {
        this.ShiftRightSmall(bits);
      } else {
        this.ShiftRightBig(bits);
      }
    }

    private void ShiftRightSmall(int bits) {
      if (bits <= 0) {
        return;
      }
      if (this.shiftedSmall == 0) {
        this.discardedBitCount.AddInt(bits);
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = 0;
        this.knownBitLength = new FastInteger(1);
        return;
      }
      int kb = SmallBitLength;
      for (int i = SmallBitLength - 1; i >= 0; --i) {
        if ((this.shiftedSmall & (1 << i)) != 0) {
          break;
        }
        --kb;
      }
      int shift = (int)Math.min(kb, bits);
      boolean shiftingMoreBits = bits > kb;
      kb -= shift;
      this.knownBitLength = new FastInteger(kb);
      this.discardedBitCount.AddInt(bits);
      this.bitsAfterLeftmost |= this.bitLeftmost;
      // Get the bottommost shift minus 1 bits
      this.bitsAfterLeftmost |= (shift > 1 && (this.shiftedSmall <<
            (SmallBitLength - shift + 1)) != 0) ? 1 : 0;
      // Get the bit just above that bit
      this.bitLeftmost = (int)((this.shiftedSmall >> (shift - 1)) & 0x01);
      this.shiftedSmall >>= shift;
      if (shiftingMoreBits) {
        // Shifted more bits than the bit length
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = 0;
      }
      this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
    }
    public void ShiftToDigitsInt(int bits) {
      if (bits < 0) {
        throw new IllegalArgumentException("bits(" + bits + ") is less than 0");
      }
      if (this.isSmall) {
        this.ShiftSmallToBits(bits);
      } else {
        this.ShiftBigToBits(bits);
      }
    }

    private void ShiftSmallToBits(int bits) {
      if (this.knownBitLength != null) {
        if (this.knownBitLength.CompareToInt(bits) <= 0) {
          return;
        }
      }
      this.knownBitLength = (this.knownBitLength == null) ? (this.CalcKnownBitLength()) : this.knownBitLength;
      if (this.knownBitLength.CompareToInt(bits) <= 0) {
        return;
      }
      int kbl = this.knownBitLength.ToInt32();
      // Shift by the difference in bit length
      if (kbl > bits) {
        int bitShift = kbl - (int)bits;
        int shift = (int)bitShift;
        this.knownBitLength = new FastInteger(bits);
        this.discardedBitCount.AddInt(bitShift);
        this.bitsAfterLeftmost |= this.bitLeftmost;
        // Get the bottommost shift minus 1 bits
        this.bitsAfterLeftmost |= (shift > 1 && (this.shiftedSmall <<
              (SmallBitLength - shift + 1)) != 0) ? 1 : 0;
        // Get the bit just above that bit
        this.bitLeftmost = (int)((this.shiftedSmall >> (shift - 1)) & 0x01);
        this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
        this.shiftedSmall >>= shift;
      } else {
        this.knownBitLength = new FastInteger(kbl);
      }
    }
  }
