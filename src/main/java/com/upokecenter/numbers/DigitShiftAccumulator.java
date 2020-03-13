package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

  final class DigitShiftAccumulator implements IShiftAccumulator {
    private static final long[] TenPowersLong = {
      1L, 10L, 100L, 1000L, 10000L, 100000L,
      1000000L, 10000000L, 100000000L,
      1000000000L,
      10000000000L,
      100000000000L,
      1000000000000L,
      10000000000000L,
      100000000000000L,
      1000000000000000L,
      10000000000000000L,
      100000000000000000L,
      1000000000000000000L,
    };

    private static final EInteger ValueTen = EInteger.FromInt32(10);

    private static final int[] ValueTenPowers = {
      1, 10, 100, 1000, 10000, 100000,
      1000000, 10000000, 100000000,
    };

    private int bitLeftmost;

    private int bitsAfterLeftmost;
    private FastInteger discardedDigitCount;
    private boolean isSmall;
    private FastInteger knownDigitLength;

    private EInteger shiftedBigInt;

    private int shiftedSmall;

    @Override public String toString() {
      return "[this.bitLeftmost=" + this.bitLeftmost +
        ", this.bitsAfterLeftmost=" + this.bitsAfterLeftmost +
        ", this.discardedDigitCount=" + this.discardedDigitCount +
        ", this.isSmall=" + this.isSmall + ", this.knownDigitLength=" +
        this.knownDigitLength + ", this.shiftedBigInt=" +
        this.shiftedBigInt + ", this.shiftedSmall=" +
        this.shiftedSmall + "]";
    }

    public DigitShiftAccumulator(
      EInteger bigint,
      int lastDiscarded,
      int olderDiscarded) {
      if (bigint.CanFitInInt32()) {
        this.shiftedSmall = bigint.ToInt32Checked();
        if (this.shiftedSmall < 0) {
          throw new IllegalArgumentException("shiftedSmall(" + this.shiftedSmall +
            ") is less than 0");
        }
        this.isSmall = true;
      } else {
        this.shiftedBigInt = bigint;
        this.isSmall = false;
      }
      this.bitsAfterLeftmost = (olderDiscarded != 0) ? 1 : 0;
      this.bitLeftmost = lastDiscarded;
    }

    public DigitShiftAccumulator(
      int smallint,
      int lastDiscarded,
      int olderDiscarded) {
      this.shiftedSmall = smallint;
      if (this.shiftedSmall < 0) {
        throw new IllegalArgumentException("shiftedSmall(" + this.shiftedSmall +
          ") is less than 0");
      }
      this.isSmall = true;
      this.bitsAfterLeftmost = (olderDiscarded != 0) ? 1 : 0;
      this.bitLeftmost = lastDiscarded;
    }

    public final FastInteger getDiscardedDigitCount() {
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        return this.discardedDigitCount;
      }

    public final int getLastDiscardedDigit() {
        return this.bitLeftmost;
      }

    public final int getOlderDiscardedDigits() {
        return this.bitsAfterLeftmost;
      }

    public final EInteger getShiftedInt() {
        return this.isSmall ? (EInteger.FromInt32(this.shiftedSmall)) :
          this.shiftedBigInt;
      }

    public int ShiftedIntMod(int mod) {
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

    public final FastInteger getShiftedIntFast() {
        return this.isSmall ? new FastInteger(this.shiftedSmall) :
          FastInteger.FromBig(this.shiftedBigInt);
      }

    public FastInteger GetDigitLength() {
      this.knownDigitLength = (this.knownDigitLength == null) ? (this.CalcKnownDigitLength()) : this.knownDigitLength;
      return this.knownDigitLength;
    }

    public FastInteger OverestimateDigitLength() {
      // If digit length is known, return it
      if (this.knownDigitLength != null) {
        return this.knownDigitLength;
      }
      if (this.isSmall) {
        // Can easily be calculated without estimation
        return this.GetDigitLength();
      } else {
        return NumberUtility.DecimalDigitLengthBounds(this.shiftedBigInt)[1];
      }
    }

    private FastInteger UnderestimateDigitLength() {
      // If digit length is known, return it
      if (this.knownDigitLength != null) {
        return this.knownDigitLength;
      }
      if (this.isSmall) {
        // Can easily be calculated without estimation
        return this.GetDigitLength();
      } else {
        return NumberUtility.DecimalDigitLengthBounds(this.shiftedBigInt)[0];
      }
    }

    public void ShiftRight(FastInteger fastint) {
      if (fastint == null) {
        throw new NullPointerException("fastint");
      }
      if (fastint.CanFitInInt32()) {
        int fi = fastint.ToInt32();
        if (fi < 0) {
          return;
        }
        this.ShiftRightInt(fi);
      } else {
        if (fastint.signum() <= 0) {
          return;
        }
        EInteger digitsToShift = fastint.ToEInteger();
        while (digitsToShift.signum() > 0) {
          if (digitsToShift.compareTo(1000000) >= 0 &&
             (this.isSmall ||
this.shiftedBigInt.GetUnsignedBitLengthAsEInteger().compareTo(digitsToShift) <
0)) {
            // Bit length is less than digits to shift, and digits to shift is >= 1000000,
            // so whole number would be shifted
            this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
            this.discardedDigitCount.AddBig(digitsToShift);
            this.bitsAfterLeftmost |= this.bitLeftmost;
            this.bitsAfterLeftmost |= (this.isSmall ? this.shiftedSmall == 0 :
this.shiftedBigInt.isZero()) ? 0 : 1;
            this.bitLeftmost = 0;
            this.knownDigitLength = new FastInteger(1);
            this.isSmall = true;
            this.shiftedSmall = 0;
            return;
          }
          int count = 1000000;
          if (digitsToShift.compareTo(1000000) < 0) {
            count = digitsToShift.ToInt32Checked();
          }
          this.ShiftRightInt(count);
          digitsToShift = digitsToShift.Subtract(EInteger.FromInt32(count));
          if (this.isSmall ? this.shiftedSmall == 0 :
            this.shiftedBigInt.isZero()) {
            return;
          }
        }
      }
    }

    public void ShiftRightInt(int digits) {
      // <summary>Shifts a number to the right, gathering information on
      // whether the last digit discarded is set and whether the discarded
      // digits to the right of that digit are set. Assumes that the big
      // integer being shifted is positive.</summary>
      if (this.isSmall) {
        this.ShiftRightSmall(digits);
      } else {
        this.ShiftRightBig(digits, false, false);
      }
    }

    public void ShiftToDigits(
      FastInteger bits,
      FastInteger preShift,
      boolean truncate) {
      if (preShift != null && preShift.signum() > 0) {
        FastInteger kdl = (this.knownDigitLength == null) ? (this.CalcKnownDigitLength()) : this.knownDigitLength;
        this.knownDigitLength = kdl;
        // System.out.println("bits=" + bits + " pre=" + preShift + " known=" +
        // (//kdl) + " [" + this.shiftedBigInt + "]");
        if (kdl.compareTo(bits) <= 0) {
          // Known digit length is already small enough
          this.TruncateOrShiftRight(preShift, truncate);
          return;
        } else {
          FastInteger bitDiff = kdl.Copy().Subtract(bits);
          // System.out.println("bitDiff=" + bitDiff);
          int cmp = bitDiff.compareTo(preShift);
          if (cmp <= 0) {
            // Difference between desired digit length and current
            // length is smaller than the shift, make it the shift
            this.TruncateOrShiftRight(preShift, truncate);
            return;
          } else {
            this.TruncateOrShiftRight(bitDiff, truncate);
            return;
          }
        }
      }
      if (bits.CanFitInInt32()) {
        int intval = bits.ToInt32();
        if (intval < 0) {
          throw new IllegalArgumentException("intval(" + intval + ") is less than " +
            "0");
        }
        if (this.isSmall) {
          this.ShiftToDigitsSmall(intval);
        } else {
          this.ShiftToDigitsBig(intval, truncate);
        }
      } else {
        FastInteger kdl = (this.knownDigitLength == null) ? (this.CalcKnownDigitLength()) : this.knownDigitLength;
        this.knownDigitLength = kdl;
        EInteger bigintDiff = kdl.ToEInteger();
        EInteger bitsBig = bits.ToEInteger();
        bigintDiff = bigintDiff.Subtract(bitsBig);
        if (bigintDiff.signum() > 0) {
          // current length is greater than the
          // desired bit length
          this.ShiftRight(FastInteger.FromBig(bigintDiff));
        }
      }
    }

    public boolean TruncateRightExact(FastInteger fastint) {
      if (fastint == null) {
        throw new NullPointerException("fastint");
      }
      if (fastint.CanFitInInt32()) {
        if (fastint.signum() < 0) {
          return (this.bitLeftmost | this.bitsAfterLeftmost) == 0;
        }
        if (!this.isSmall && !this.shiftedBigInt.CanFitInInt64()) {
          int a = fastint.ToInt32();
          if (a > 10) {
            this.ShiftRightBig(10, true, true);
            if ((this.bitLeftmost | this.bitsAfterLeftmost) != 0) {
              return false;
            }
            this.ShiftRightBig(a - 10, true, true);
          } else {
            this.ShiftRightBig(a, true, true);
          }
          return (this.bitLeftmost | this.bitsAfterLeftmost) == 0;
        }
      }
      this.TruncateOrShiftRight(fastint, true);
      return (this.bitLeftmost | this.bitsAfterLeftmost) == 0;
    }

    public void TruncateRightSimple(FastInteger fastint) {
      if (fastint == null) {
        throw new NullPointerException("fastint");
      }
      if (fastint.CanFitInInt32()) {
        if (fastint.signum() < 0) {
          return;
        }
        if (!this.isSmall && !this.shiftedBigInt.CanFitInInt64()) {
          this.ShiftRightBig(fastint.ToInt32(), true, true);
          return;
        }
      }
      this.TruncateOrShiftRight(fastint, true);
    }

    public void TruncateOrShiftRight(FastInteger fastint, boolean truncate) {
      // 'Truncate' is true if the caller doesn't care about the exact identity
      // of the last digit and the discarded digits.
      if (fastint == null) {
        throw new NullPointerException("fastint");
      }
      if (truncate && fastint.CanFitInInt32()) {
        int fi = fastint.ToInt32();
        if (fi < 0) {
          return;
        }
        if (!this.isSmall) {
          if (this.shiftedBigInt.CanFitInInt64()) {
            this.TruncateRightLong(this.shiftedBigInt.ToInt64Checked(), fi);
          } else {
            this.ShiftRightBig(fi, true, false);
          }
        } else {
          this.TruncateRightSmall(fi);
        }
      } else {
        this.ShiftRight(fastint);
      }
    }

    private static int FastParseLong(String str, int offset, int length) {
      // Assumes the String is length 9 or less and contains
      // only the digits '0' through '9'
      if (length > 9) {
        throw new IllegalArgumentException("length(" + length + ") is more than " +
          "9 ");
      }
      int ret = 0;
      for (int i = 0; i < length; ++i) {
        int digit = (int)(str.charAt(offset + i) - '0');
        ret *= 10;
        ret += digit;
      }
      return ret;
    }

    private static EInteger DivideByPowerOfTen(EInteger ei, int pow) {
      // if (pow > 100) {
      // int mid = pow / 2;
      // ei = DivideByPowerOfTen(ei, pow - mid);
      // return DivideByPowerOfTen(ei, mid);
      // } else {
      return ei.Divide(NumberUtility.FindPowerOfTen(pow));
      // }
    }

    private FastInteger CalcKnownDigitLength() {
      if (this.isSmall) {
        int kb = NumberUtility.DecimalDigitLength(this.shiftedSmall);
        return new FastInteger(kb);
      } else {
        long digits = this.shiftedBigInt.GetDigitCountAsInt64();
        if (digits == Long.MAX_VALUE) {
           return
FastInteger.FromBig(this.shiftedBigInt.GetDigitCountAsEInteger());
        } else if (digits < Integer.MAX_VALUE) {
          return new FastInteger((int)digits);
        } else {
           return FastInteger.FromBig(EInteger.FromInt64(digits));
        }
      }
    }

    private void UpdateKnownLengthInt(int digits) {
      if (this.knownDigitLength != null) {
        this.knownDigitLength.SubtractInt(digits);
        if (this.knownDigitLength.CompareToInt(1) < 0) {
          this.knownDigitLength.SetInt(1);
        }
      }
    }

    private void UpdateKnownLength(FastInteger digitsShiftedFast) {
      if (this.knownDigitLength != null) {
        this.knownDigitLength.Subtract(digitsShiftedFast);
        if (this.knownDigitLength.CompareToInt(1) < 0) {
          this.knownDigitLength.SetInt(1);
        }
      }
    }

    private void ShiftRightBig(int digits, boolean truncate, boolean simple) {
      if (digits <= 0) {
        return;
      }
      if (this.shiftedBigInt.isZero()) {
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        this.discardedDigitCount.AddInt(digits);
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = 0;
        this.knownDigitLength = new FastInteger(1);
        return;
      }
      if (truncate) {
        EInteger bigquo;
        {
          // To avoid having to calculate a very big power of 10,
          // or the digit length of a very big integer,
          // check the digit count to see if doing so can be avoided
          EInteger bigBitLength =
            this.shiftedBigInt.GetUnsignedBitLengthAsEInteger();
          boolean bigPower = false;
          if (digits > 50 &&
              bigBitLength.compareTo(100) > 0 &&
              bigBitLength.Add(5).compareTo(digits) < 0) {
            // Has much fewer bits than digits to shift, so all of them
            // will be shifted to the right
            bigPower = true;
          } else {
          // NOTE: Overflowing bigBitLength will be MaxValue, which is OK
          // for the use of this variable
          int bitLength = bigBitLength.CanFitInInt32() ?
            bigBitLength.ToInt32Checked() : Integer.MAX_VALUE;
          // 10^48 has 160 bits; 10^98 has 326; bit length is cheaper
          // to calculate than base-10 digit length
          if ((digits > 50 && bitLength < 160) ||
              (digits > 100 && bitLength < 326)) {
            bigPower = true;
          } else {
            FastInteger digitsUpperBound = this.OverestimateDigitLength();
            bigPower = digitsUpperBound.Copy().SubtractInt(digits)
              .CompareToInt(-2) < 0;
          }
          }
          if (bigPower) {
            // Power of 10 to be divided would be much bigger
            this.discardedDigitCount = (this.discardedDigitCount == null) ? (new FastInteger(0)) : this.discardedDigitCount;
            this.discardedDigitCount.AddInt(digits);
            this.bitsAfterLeftmost |= this.bitLeftmost;
            this.bitsAfterLeftmost |= this.shiftedBigInt.isZero() ? 0 : 1;
            this.bitLeftmost = 0;
            this.knownDigitLength = new FastInteger(1);
            this.isSmall = true;
            this.shiftedSmall = 0;
            return;
          }
        }
        if (!simple && this.ShiftedIntMod(2) == 0 && this.bitLeftmost == 0) {
          EInteger[] quorem = this.shiftedBigInt.DivRem(
              NumberUtility.FindPowerOfTen(digits));
          bigquo = quorem[0];
          this.bitLeftmost |= quorem[1].isZero() ? 0 : 1;
        } else {
          this.bitLeftmost = 1;
          bigquo = this.shiftedBigInt.Divide(
              NumberUtility.FindPowerOfTen(digits));
        }
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.discardedDigitCount = this.discardedDigitCount == null ?
          new FastInteger(digits) : this.discardedDigitCount.AddInt(digits);
        if (bigquo.isZero()) {
          // Shifted all the way to 0
          this.isSmall = true;
          this.shiftedBigInt = null;
          this.shiftedSmall = 0;
          this.knownDigitLength = new FastInteger(1);
        } else if (bigquo.CanFitInInt32()) {
          this.isSmall = true;
          this.shiftedSmall = bigquo.ToInt32Unchecked();
          this.shiftedBigInt = null;
          this.UpdateKnownLengthInt(digits);
        } else {
          this.isSmall = false;
          this.shiftedBigInt = bigquo;
          this.UpdateKnownLengthInt(digits);
        }
        return;
      }
      if (digits == 1) {
        EInteger bigrem;
        EInteger bigquo;
        EInteger[] divrem = this.shiftedBigInt.DivRem(EInteger.FromInt32(10));
        bigquo = divrem[0];
        bigrem = divrem[1];
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = bigrem.ToInt32Checked();
        this.shiftedBigInt = bigquo;
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        this.discardedDigitCount.AddInt(digits);
        this.UpdateKnownLengthInt(digits);
        return;
      }
      if (digits >= 2 && digits <= 8) {
        EInteger bigrem;
        EInteger bigquo;
        EInteger[] divrem =
          this.shiftedBigInt.DivRem(NumberUtility.FindPowerOfTen(digits));
        bigquo = divrem[0];
        bigrem = divrem[1];
        int intRem = bigrem.ToInt32Checked();
        int smallPower = ValueTenPowers[digits - 1];
        int leftBit = intRem / smallPower;
        int otherBits = intRem - (leftBit * smallPower);
        this.bitsAfterLeftmost |= otherBits | this.bitLeftmost;
        this.bitLeftmost = leftBit;
        this.shiftedBigInt = bigquo;
        this.discardedDigitCount = (this.discardedDigitCount != null) ?
          this.discardedDigitCount.AddInt(digits) : new FastInteger(digits);
        this.UpdateKnownLengthInt(digits);
        this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
        if (this.shiftedBigInt.CanFitInInt32()) {
          this.isSmall = true;
          this.shiftedSmall = this.shiftedBigInt.ToInt32Unchecked();
          this.shiftedBigInt = null;
        }
        return;
      }
      this.knownDigitLength = (this.knownDigitLength == null) ? (this.CalcKnownDigitLength()) : this.knownDigitLength;
      if (new FastInteger(digits).Decrement().compareTo(this.knownDigitLength)
        >= 0) {
        // Shifting more bits than available
        this.bitsAfterLeftmost |= this.shiftedBigInt.isZero() ? 0 : 1;
        this.isSmall = true;
        this.shiftedSmall = 0;
        this.knownDigitLength = new FastInteger(1);
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        this.discardedDigitCount.AddInt(digits);
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = 0;
        return;
      }
      if (this.shiftedBigInt.CanFitInInt32()) {
        this.isSmall = true;
        this.shiftedSmall = this.shiftedBigInt.ToInt32Checked();
        this.ShiftRightSmall(digits);
        return;
      }
      if (this.shiftedBigInt.CanFitInInt64()) {
        this.ShiftRightLong(this.shiftedBigInt.ToInt64Unchecked(), digits);
        return;
      }
      EInteger sbi = this.shiftedBigInt;
      EInteger[] divrem1 = sbi.DivRem(NumberUtility.FindPowerOfTen(digits - 1));
      EInteger[] divrem2 = divrem1[0].DivRem(10);
      this.bitsAfterLeftmost |= this.bitLeftmost;
      this.bitsAfterLeftmost |= divrem1[1].isZero() ? 0 : 1;
      this.bitLeftmost = divrem2[1].ToInt32Checked();
      this.discardedDigitCount = (this.discardedDigitCount != null) ?
          this.discardedDigitCount.AddInt(digits) : new FastInteger(digits);
      this.UpdateKnownLengthInt(digits);
      if (divrem2[0].CanFitInInt32()) {
        this.isSmall = true;
        this.shiftedSmall = divrem2[0].ToInt32Checked();
      } else {
        this.isSmall = false;
        this.shiftedBigInt = divrem2[0];
      }
      /* String str = this.shiftedBigInt.toString();
      System.out.println("srb=" + str + " digits="+digits);
      // NOTE: Will be 1 if the value is 0
      int digitLength = str.length();
      int bitDiff = 0;
      if (digits > digitLength) {
        bitDiff = digits - digitLength;
      }
      this.discardedDigitCount = (this.discardedDigitCount == null) ? (new FastInteger(0)) : this.discardedDigitCount;
      this.discardedDigitCount.AddInt(digits);
      this.bitsAfterLeftmost |= this.bitLeftmost;
      int digitShift = Math.min(digitLength, digits);
      if (digits >= digitLength) {
        this.isSmall = true;
        this.shiftedSmall = 0;
        this.knownDigitLength = new FastInteger(1);
      } else {
        int newLength = (int)(digitLength - digitShift);
        if (newLength <= 9) {
          // Fits in a small number
          this.isSmall = true;
          this.shiftedSmall = FastParseLong(str, 0, newLength);
        } else {
          this.shiftedBigInt = EInteger.FromSubstring(str, 0, newLength);
        }
        this.UpdateKnownLengthInt(digitShift);
      }
      for (int i = str.length() - 1; i >= 0; --i) {
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = (int)(str.charAt(i) - '0');
        --digitShift;
        if (digitShift <= 0) {
          break;
        }
      }
      this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
      if (bitDiff > 0) {
        // Shifted more digits than the digit length
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = 0;
      }
      */
    }

    private void ShiftRightLong(long shiftedLong, int digits) {
      if (digits <= 0) {
        return;
      }
      if (shiftedLong == 0) {
        this.shiftedSmall = 0;
        this.isSmall = true;
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        this.discardedDigitCount.AddInt(digits);
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = 0;
        this.knownDigitLength = new FastInteger(1);
        return;
      }

      if (digits >= 2 && digits <= 8) {
        if (shiftedLong >= ValueTenPowers[digits]) {
          long bigPower = ValueTenPowers[digits];
          long smallPower = ValueTenPowers[digits - 1];
          this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(
              0)) : this.discardedDigitCount;
          this.discardedDigitCount.AddInt(digits);
          long div = shiftedLong / bigPower;
          long rem = shiftedLong - (div * bigPower);
          long rem2 = rem / smallPower;
          this.bitLeftmost = (int)rem2;
          this.bitsAfterLeftmost |= ((rem - (rem2 * smallPower)) == 0) ? 0 : 1;
          this.isSmall = div <= Integer.MAX_VALUE;
          if (this.isSmall) {
            this.shiftedSmall = (int)div;
            this.knownDigitLength = (div < 10) ? new FastInteger(1) :
              new FastInteger(NumberUtility.DecimalDigitLength(div));
          } else {
            this.shiftedBigInt = EInteger.FromInt64(div);
            this.knownDigitLength = (div < 10) ? new FastInteger(1) :
              this.CalcKnownDigitLength();
          }
          return;
        } else if (this.shiftedSmall >= ValueTenPowers[digits - 1]) {
          int smallPower = ValueTenPowers[digits - 1];
          if (this.discardedDigitCount != null) {
            this.discardedDigitCount.AddInt(digits);
          } else {
            this.discardedDigitCount = new FastInteger(digits);
          }
          long rem = shiftedLong;
          long rem2 = rem / smallPower;
          this.bitLeftmost = (int)rem2;
          this.bitsAfterLeftmost |= ((rem - (rem2 * smallPower)) == 0) ? 0 : 1;
          this.isSmall = true;
          this.shiftedSmall = 0;
          this.knownDigitLength = new FastInteger(1);
          return;
        } else {
          if (this.discardedDigitCount != null) {
            this.discardedDigitCount.AddInt(digits);
          } else {
            this.discardedDigitCount = new FastInteger(digits);
          }
          this.bitLeftmost = 0;
          this.bitsAfterLeftmost |= (shiftedLong == 0) ? 0 : 1;
          this.isSmall = true;
          this.shiftedSmall = 0;
          this.knownDigitLength = new FastInteger(1);
          return;
        }
      }
      this.knownDigitLength = new FastInteger(
        NumberUtility.DecimalDigitLength(shiftedLong));
      if (this.discardedDigitCount != null) {
        this.discardedDigitCount.AddInt(digits);
      } else {
        this.discardedDigitCount = new FastInteger(digits);
      }
      int digitsShifted = 0;
      while (digits > 0) {
        if (shiftedLong == 0) {
          this.bitsAfterLeftmost |= this.bitLeftmost;
          this.bitLeftmost = 0;
          break;
        } else {
          long newShift = (shiftedLong < 43698) ? ((shiftedLong * 26215) >>
              18) : (shiftedLong / 10);
          int digit = (int)(shiftedLong - (newShift * 10));
          this.bitsAfterLeftmost |= this.bitLeftmost;
          this.bitLeftmost = digit;
          --digits;
          ++digitsShifted;
          shiftedLong = newShift;
        }
      }
      this.isSmall = shiftedLong <= Integer.MAX_VALUE;
      if (this.isSmall) {
        this.shiftedSmall = (int)shiftedLong;
      } else {
        this.shiftedBigInt = EInteger.FromInt64(shiftedLong);
      }
      this.UpdateKnownLengthInt(digitsShifted);
      this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
    }

    private void ShiftToDigitsBig(int digits, boolean truncate) {
      // Shifts a number until it reaches the given number of digits,
      // gathering information on whether the last digit discarded is set
      // and whether the discarded digits to the right of that digit are set.
      // Assumes that the big integer being shifted is positive.
      // 'Truncate' is true if the caller doesn't care about the exact identity
      // of the last digit and the discarded digits.
      if (this.knownDigitLength != null) {
        if (this.knownDigitLength.CompareToInt(digits) <= 0) {
          return;
        }
      }
      // System.out.println("ShiftToDigitsBig(" + digits + ")");
      // System.Diagnostics.Stopwatch sw = new System.Diagnostics.Stopwatch();sw.Restart();
      String str;
      FastInteger estDigitLength = this.UnderestimateDigitLength();
      boolean haveKnownDigitLength = this.knownDigitLength != null;

      if (estDigitLength.CompareToInt(digits) <= 0) {
        if (!haveKnownDigitLength) {
          this.GetDigitLength();
          this.ShiftToDigitsBig(digits, truncate);
        }

        return;
      }
      FastInteger digitDiff = estDigitLength.Copy().SubtractInt(digits);
      if (truncate && digitDiff.CanFitInInt32()) {
        // System.out.println("d=" + sw.getElapsedMilliseconds() + " ms");
        this.TruncateOrShiftRight(digitDiff, truncate);
        if (!haveKnownDigitLength) {
          this.GetDigitLength();
          this.ShiftToDigitsBig(digits, truncate);
        }

        return;
      }
      if (digitDiff.CompareToInt(1) == 0) {
        EInteger bigrem;
        EInteger bigquo;
        EInteger[] divrem = this.shiftedBigInt.DivRem(ValueTen);
        bigquo = divrem[0];
        bigrem = divrem[1];
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = bigrem.ToInt32Checked();
        this.shiftedBigInt = bigquo;
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        this.discardedDigitCount.Add(digitDiff);
        this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
        if (!haveKnownDigitLength) {
          this.GetDigitLength();
          this.ShiftToDigitsBig(digits, truncate);
        } else {
          this.UpdateKnownLength(digitDiff);
        }

        return;
      }
      if (digitDiff.CompareToInt(9) <= 0) {
        EInteger bigrem;
        int diffInt = digitDiff.ToInt32();
        EInteger radixPower = NumberUtility.FindPowerOfTen(diffInt);
        EInteger bigquo;
        EInteger[] divrem = this.shiftedBigInt.DivRem(radixPower);
        bigquo = divrem[0];
        bigrem = divrem[1];
        int rem = bigrem.ToInt32Checked();
        this.bitsAfterLeftmost |= this.bitLeftmost;
        for (int i = 0; i < diffInt; ++i) {
          if (i == diffInt - 1) {
            this.bitLeftmost = rem % 10;
          } else {
            int intQuot = (rem < 43698) ? ((rem * 26215) >> 18) : (rem / 10);
            this.bitsAfterLeftmost |= rem - (intQuot * 10);
            rem = intQuot;
          }
        }
        this.shiftedBigInt = bigquo;
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        this.discardedDigitCount.Add(digitDiff);
        this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
        if (!haveKnownDigitLength) {
          this.GetDigitLength();
          this.ShiftToDigitsBig(digits, truncate);
        } else {
          this.UpdateKnownLength(digitDiff);
        }

        return;
      }
      // System.out.println("e1=" + sw.getElapsedMilliseconds() + " ms");
      if (digitDiff.CanFitInInt32()) {
        EInteger bigrem = null;
        EInteger bigquo;
        EInteger[] divrem;
        EInteger radixPower;
        int power = digitDiff.ToInt32() - 1;
        if (!this.shiftedBigInt.isEven() || this.bitsAfterLeftmost != 0) {
          // System.out.println("f=" + sw.getElapsedMilliseconds() + " ms.get(pow=" + power +
          // ")");
          bigquo = this.shiftedBigInt;
          // System.out.println("fa=" + sw.getElapsedMilliseconds() + " ms.get(" + (//
          // (!this.shiftedBigInt.isEven() || this.bitsAfterLeftmost != 0)) + ")");
          this.bitsAfterLeftmost |= 1;
          bigquo = bigquo.Divide(NumberUtility.FindPowerOfTen(power));
          // System.out.println("faa=" + sw.getElapsedMilliseconds() + " ms");
        } else {
          // System.out.println("fb=" + sw.getElapsedMilliseconds() + " ms.get(pow=" + power +
          // ")");
          radixPower = NumberUtility.FindPowerOfTen(power);
          // System.out.println("fc=" + sw.getElapsedMilliseconds() + " ms.get(" + (//
          // (!this.shiftedBigInt.isEven() || this.bitsAfterLeftmost != 0)) + ")");
          divrem = this.shiftedBigInt.DivRem(radixPower);
          bigquo = divrem[0];
          bigrem = divrem[1];
          this.bitsAfterLeftmost |= this.bitLeftmost;
          if (!bigrem.isZero()) {
            this.bitsAfterLeftmost |= 1;
          }
        }
        // System.out.println("g=" + sw.getElapsedMilliseconds() + " ms");
        EInteger bigquo2;
        divrem = bigquo.DivRem(ValueTen);
        bigquo2 = divrem[0];
        bigrem = divrem[1];
        this.bitLeftmost = bigrem.ToInt32Checked();
        this.shiftedBigInt = bigquo2;
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        this.discardedDigitCount.Add(digitDiff);
        this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
        // System.out.println("h=" + sw.getElapsedMilliseconds() + " ms");
        if (!haveKnownDigitLength) {
          this.GetDigitLength();
          this.ShiftToDigitsBig(digits, truncate);
        } else {
          this.UpdateKnownLength(digitDiff);
        }

        return;
      }
      str = this.shiftedBigInt.toString();
      // System.out.println("sdb=" + str + " digits="+digits);
      // NOTE: Will be 1 if the value is 0
      int digitLength = str.length();
      this.knownDigitLength = new FastInteger(digitLength);
      // Shift by the difference in digit length
      if (digitLength > digits) {
        int digitShift = digitLength - digits;
        this.UpdateKnownLengthInt(digitShift);
        int newLength = (int)(digitLength - digitShift);
        // System.out.println("dlen= " + digitLength + " dshift=" +
        // digitShift + " newlen= " + newLength);
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        if (digitShift <= Integer.MAX_VALUE) {
          this.discardedDigitCount.AddInt((int)digitShift);
        } else {
          this.discardedDigitCount.AddBig(EInteger.FromInt32(digitShift));
        }
        for (int i = str.length() - 1; i >= 0; --i) {
          this.bitsAfterLeftmost |= this.bitLeftmost;
          this.bitLeftmost = (int)(str.charAt(i) - '0');
          --digitShift;
          if (digitShift <= 0) {
            break;
          }
        }
        if (newLength <= 9) {
          this.isSmall = true;
          this.shiftedSmall = FastParseLong(str, 0, newLength);
        } else {
          this.shiftedBigInt = EInteger.FromSubstring(str, 0, newLength);
        }
        this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
      }
    }

    private void ShiftToDigitsSmall(int digits) {
      int kb = 0;
      int v2 = this.shiftedSmall;
      kb = (v2 >= 1000000000) ? 10 : ((v2 >= 100000000) ? 9 : ((v2 >=
              10000000) ? 8 : ((v2 >= 1000000) ? 7 : ((v2 >= 100000) ? 6 :
                ((v2 >= 10000) ? 5 : ((v2 >= 1000) ? 4 : ((v2 >= 100) ? 3 : ((v2
                >= 10) ? 2 : 1))))))));
      this.knownDigitLength = new FastInteger(kb);
      if (kb > digits) {
        int digitShift = (int)(kb - digits);
        this.UpdateKnownLengthInt(digitShift);
        this.discardedDigitCount = this.discardedDigitCount != null ?
          this.discardedDigitCount.AddInt(digitShift) :
          new FastInteger(digitShift);
        for (int i = 0; i < digitShift; ++i) {
          int digit = (int)(this.shiftedSmall % 10);
          this.shiftedSmall /= 10;
          this.bitsAfterLeftmost |= this.bitLeftmost;
          this.bitLeftmost = digit;
        }
        this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
      }
    }

    private void TruncateRightLong(long shiftedLong, int digits) {
      if (digits <= 0) {
        return;
      }
      if (shiftedLong == 0 || digits >= 21) {
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        this.discardedDigitCount.AddInt(digits);
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = shiftedLong == 0 ? 0 : 1;
        this.shiftedSmall = 0;
        this.isSmall = true;
        this.knownDigitLength = new FastInteger(1);
        return;
      }
      if (digits >= 1 && digits <= TenPowersLong.length - 1) {
        if (shiftedLong >= TenPowersLong[digits]) {
          long bigPower = TenPowersLong[digits];
          if (this.discardedDigitCount != null) {
            this.discardedDigitCount.AddInt(digits);
          } else {
            this.discardedDigitCount = new FastInteger(digits);
          }
          long quo = shiftedLong / bigPower;
          this.bitsAfterLeftmost |= this.bitLeftmost;
          this.bitLeftmost = (shiftedLong & 1) == 1 ? 1 :
            (shiftedLong - (quo * bigPower) == 0 ? 0 : 1);
          shiftedLong = quo;
          this.isSmall = shiftedLong <= Integer.MAX_VALUE;
          if (this.isSmall) {
            this.shiftedSmall = (int)shiftedLong;
          } else {
            this.shiftedBigInt = EInteger.FromInt64(shiftedLong);
          }
          this.UpdateKnownLengthInt(digits);
          return;
        } else {
          if (this.discardedDigitCount != null) {
            this.discardedDigitCount.AddInt(digits);
          } else {
            this.discardedDigitCount = new FastInteger(digits);
          }
          this.bitsAfterLeftmost |= this.bitLeftmost;
          this.bitLeftmost = shiftedLong == 0 ? 0 : 1;
          shiftedLong = 0;
          this.isSmall = shiftedLong <= Integer.MAX_VALUE;
          if (this.isSmall) {
            this.shiftedSmall = (int)shiftedLong;
          } else {
            this.shiftedBigInt = EInteger.FromInt64(shiftedLong);
          }
          this.UpdateKnownLengthInt(digits);
          return;
        }
      }
      this.ShiftRightInt(digits);
    }

    private void ShiftRightSmall(int digits) {
      if (digits <= 0) {
        return;
      }
      if (this.shiftedSmall == 0) {
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        this.discardedDigitCount.AddInt(digits);
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = 0;
        this.knownDigitLength = new FastInteger(1);
        return;
      }
      if (digits >= 2 && digits <= 8) {
        if (this.shiftedSmall >= ValueTenPowers[digits]) {
          int bigPower = ValueTenPowers[digits];
          int smallPower = ValueTenPowers[digits - 1];
          this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(
              0)) : this.discardedDigitCount;
          this.discardedDigitCount.AddInt(digits);
          int div = this.shiftedSmall / bigPower;
          int rem = this.shiftedSmall - (div * bigPower);
          int rem2 = rem / smallPower;
          this.bitLeftmost = rem2;
          this.bitsAfterLeftmost |= rem - (rem2 * smallPower);
          this.shiftedSmall = div;
          this.knownDigitLength = (div < 10) ? new FastInteger(1) :
            this.CalcKnownDigitLength();
          return;
        } else if (this.shiftedSmall >= ValueTenPowers[digits - 1]) {
          int smallPower = ValueTenPowers[digits - 1];
          if (this.discardedDigitCount != null) {
            this.discardedDigitCount.AddInt(digits);
          } else {
            this.discardedDigitCount = new FastInteger(digits);
          }
          int rem = this.shiftedSmall;
          int rem2 = rem / smallPower;
          this.bitLeftmost = rem2;
          this.bitsAfterLeftmost |= rem - (rem2 * smallPower);
          this.shiftedSmall = 0;
          this.knownDigitLength = new FastInteger(1);
          return;
        } else {
          if (this.discardedDigitCount != null) {
            this.discardedDigitCount.AddInt(digits);
          } else {
            this.discardedDigitCount = new FastInteger(digits);
          }
          int rem = this.shiftedSmall;
          this.bitLeftmost = 0;
          this.bitsAfterLeftmost |= rem;
          this.shiftedSmall = 0;
          this.knownDigitLength = new FastInteger(1);
          return;
        }
      }
      int v2 = this.shiftedSmall;
      int kb = (v2 >= 1000000000) ? 10 : ((v2 >= 100000000) ? 9 : ((v2 >=
              10000000) ? 8 : ((v2 >= 1000000) ? 7 : ((v2 >= 100000) ? 6 :
                ((v2 >= 10000) ? 5 : ((v2 >= 1000) ? 4 : ((v2 >= 100) ? 3 : ((v2
                >= 10) ? 2 : 1))))))));
      this.knownDigitLength = new FastInteger(kb);
      if (this.discardedDigitCount != null) {
        this.discardedDigitCount.AddInt(digits);
      } else {
        this.discardedDigitCount = new FastInteger(digits);
      }
      int digitsShifted = 0;
      while (digits > 0) {
        if (this.shiftedSmall == 0) {
          this.bitsAfterLeftmost |= this.bitLeftmost;
          this.bitLeftmost = 0;
          this.knownDigitLength = new FastInteger(1);
          break;
        } else {
          int digit = (int)(this.shiftedSmall % 10);
          this.bitsAfterLeftmost |= this.bitLeftmost;
          this.bitLeftmost = digit;
          --digits;
          ++digitsShifted;
          this.shiftedSmall /= 10;
        }
      }
      this.UpdateKnownLengthInt(digitsShifted);
      this.bitsAfterLeftmost = (this.bitsAfterLeftmost != 0) ? 1 : 0;
    }

    private void TruncateRightSmall(int digits) {
      if (digits <= 0) {
        return;
      }
      if (this.shiftedSmall == 0 || digits >= 11) {
        this.discardedDigitCount = (this.discardedDigitCount == null) ? (new
FastInteger(0)) : this.discardedDigitCount;
        this.discardedDigitCount.AddInt(digits);
        this.bitsAfterLeftmost |= this.bitLeftmost;
        this.bitLeftmost = (this.shiftedSmall == 0) ? 0 : 1;
        this.shiftedSmall = 0;
        this.knownDigitLength = new FastInteger(1);
        return;
      }
      if (digits >= 1 && digits <= 8) {
        if (this.shiftedSmall >= ValueTenPowers[digits]) {
          int bigPower = ValueTenPowers[digits];
          if (this.discardedDigitCount != null) {
            this.discardedDigitCount.AddInt(digits);
          } else {
            this.discardedDigitCount = new FastInteger(digits);
          }
          this.bitsAfterLeftmost |= this.bitLeftmost;
          if ((this.shiftedSmall & 1) == 1) {
            this.bitLeftmost = 1;
            this.shiftedSmall /= bigPower;
          } else {
            int quo = this.shiftedSmall / bigPower;
            int rem = this.shiftedSmall - (quo * bigPower);
            this.shiftedSmall = quo;
            this.bitLeftmost |= (rem == 0) ? 0 : 1;
          }
          this.UpdateKnownLengthInt(digits);
          return;
        } else {
          if (this.discardedDigitCount != null) {
            this.discardedDigitCount.AddInt(digits);
          } else {
            this.discardedDigitCount = new FastInteger(digits);
          }
          this.bitsAfterLeftmost |= this.bitLeftmost;
          this.bitLeftmost = (this.shiftedSmall == 0) ? 0 : 1;
          this.shiftedSmall = 0;
          this.knownDigitLength = new FastInteger(1);
          return;
        }
      }
      this.ShiftRightSmall(digits);
    }
  }
