package com.upokecenter.numbers;
/*
Written by Peter O. in 2013.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

  final class NumberUtility {
private NumberUtility() {
}
    private static final EInteger[] ValueBigIntPowersOfTen = {
      EInteger.FromInt32(1), EInteger.FromInt32(10), EInteger.FromInt64(100), EInteger.FromInt64(1000),
      EInteger.FromInt64(10000), EInteger.FromInt64(100000), EInteger.FromInt64(1000000),
      EInteger.FromInt64(10000000), EInteger.FromInt64(100000000), EInteger.FromInt64(1000000000),
      EInteger.FromInt64(10000000000L), EInteger.FromInt64(100000000000L),
      EInteger.FromInt64(1000000000000L), EInteger.FromInt64(10000000000000L),
      EInteger.FromInt64(100000000000000L), EInteger.FromInt64(1000000000000000L),
      EInteger.FromInt64(10000000000000000L),
      EInteger.FromInt64(100000000000000000L), EInteger.FromInt64(1000000000000000000L),
    };

    private static final EInteger[] ValueBigIntPowersOfFive = {
      EInteger.FromInt32(1), EInteger.FromInt64(5), EInteger.FromInt64(25), EInteger.FromInt64(125),
      EInteger.FromInt64(625), EInteger.FromInt64(3125), EInteger.FromInt64(15625),
      EInteger.FromInt64(78125), EInteger.FromInt64(390625),
      EInteger.FromInt64(1953125), EInteger.FromInt64(9765625), EInteger.FromInt64(48828125),
      EInteger.FromInt64(244140625), EInteger.FromInt64(1220703125),
      EInteger.FromInt64(6103515625L), EInteger.FromInt64(30517578125L),
      EInteger.FromInt64(152587890625L), EInteger.FromInt64(762939453125L),
      EInteger.FromInt64(3814697265625L), EInteger.FromInt64(19073486328125L),
      EInteger.FromInt64(95367431640625L),
      EInteger.FromInt64(476837158203125L), EInteger.FromInt64(2384185791015625L),
      EInteger.FromInt64(11920928955078125L),
      EInteger.FromInt64(59604644775390625L), EInteger.FromInt64(298023223876953125L),
      EInteger.FromInt64(1490116119384765625L), EInteger.FromInt64(7450580596923828125L),
    };

    static int ShiftLeftOne(int[] arr) {
      {
        int carry = 0;
        for (int i = 0; i < arr.length; ++i) {
          int item = arr[i];
          arr[i] = (int)(arr[i] << 1) | (int)carry;
          carry = ((item >> 31) != 0) ? 1 : 0;
        }
        return carry;
      }
    }

    private static int CountTrailingZeros(int numberValue) {
      if (numberValue == 0) {
        return 32;
      }
      int i = 0;
      {
        if ((numberValue << 16) == 0) {
          numberValue >>= 16;
          i += 16;
        }
        if ((numberValue << 24) == 0) {
          numberValue >>= 8;
          i += 8;
        }
        if ((numberValue << 28) == 0) {
          numberValue >>= 4;
          i += 4;
        }
        if ((numberValue << 30) == 0) {
          numberValue >>= 2;
          i += 2;
        }
        if ((numberValue << 31) == 0) {
          ++i;
        }
      }
      return i;
    }

    static int BitPrecisionInt(int numberValue) {
      if (numberValue == 0) {
        return 0;
      }
      int i = 32;
      {
        if ((numberValue >> 16) == 0) {
          numberValue <<= 16;
          i -= 8;
        }
        if ((numberValue >> 24) == 0) {
          numberValue <<= 8;
          i -= 8;
        }
        if ((numberValue >> 28) == 0) {
          numberValue <<= 4;
          i -= 4;
        }
        if ((numberValue >> 30) == 0) {
          numberValue <<= 2;
          i -= 2;
        }
        if ((numberValue >> 31) == 0) {
          --i;
        }
      }
      return i;
    }

    static int ShiftAwayTrailingZerosTwoElements(int[] arr) {
      int a0 = arr[0];
      int a1 = arr[1];
      int tz = CountTrailingZeros(a0);
      if (tz == 0) {
        return 0;
      }
      {
        if (tz < 32) {
          int carry = a1 << (32 - tz);
          arr[0] = (int)((a0 >> tz) & (0x7fffffff >> (tz - 1))) | (int)carry;
          arr[1] = (a1 >> tz) & (0x7fffffff >> (tz - 1));
          return tz;
        }
        tz = CountTrailingZeros(a1);
        if (tz == 32) {
          arr[0] = 0;
        } else if (tz > 0) {
          arr[0] = (a1 >> tz) & (0x7fffffff >> (tz - 1));
        } else {
          arr[0] = a1;
        }
        arr[1] = 0;
        return 32 + tz;
      }
    }

    static boolean HasBitSet(int[] arr, int bit) {
      return (bit >> 5) < arr.length && (arr[bit >> 5] & (1 << (bit & 31))) !=
        0;
    }

    private static final class PowerCache {
      private static final int MaxSize = 128;
      private final EInteger[] outputs;
      private final EInteger[] inputs;
      private final int[] inputsInts;

      public PowerCache() {
        this.outputs = new EInteger[MaxSize];
        this.inputs = new EInteger[MaxSize];
        this.inputsInts = new int[MaxSize];
      }

      private int size;

      public EInteger[] FindCachedPowerOrSmaller(EInteger bi) {
        EInteger[] ret = null;
        EInteger minValue = null;
        if (bi.CanFitInInt32()) {
          return this.FindCachedPowerIntOrSmaller(bi.ToInt32Checked());
        }
        synchronized (this.outputs) {
          for (int i = 0; i < this.size; ++i) {
            if (this.inputs[i].compareTo(bi) <= 0 && (minValue == null ||
                this.inputs[i].compareTo(minValue) >= 0)) {
              // System.out.println("Have cached power (" + inputs[i] + "," + bi + ") ");
              ret = new EInteger[2];
              ret[0] = this.inputs[i];
              ret[1] = this.outputs[i];
              minValue = this.inputs[i];
            }
          }
        }
        return ret;
      }

      public EInteger[] FindCachedPowerIntOrSmaller(int precision) {
        EInteger[] ret = null;
        int integerMinValue = -1;
        synchronized (this.outputs) {
          for (int i = 0; i < this.size; ++i) {
            if (this.inputsInts[i] >= 0 &&
              this.inputsInts[i] <= precision && (integerMinValue == -1 ||
                this.inputsInts[i] >= integerMinValue)) {
              // System.out.println("Have cached power (" + inputs[i] + "," + bi + ") ");
              ret = new EInteger[2];
              ret[0] = this.inputs[i];
              ret[1] = this.outputs[i];
              integerMinValue = this.inputsInts[i];
            }
          }
        }
        return ret;
      }

      public EInteger GetCachedPower(EInteger bi) {
        if (bi.CanFitInInt32()) {
          return this.GetCachedPowerInt(bi.ToInt32Checked());
        }
        synchronized (this.outputs) {
          for (int i = 0; i < this.size; ++i) {
            if (bi.equals(this.inputs[i])) {
              if (i != 0) {
                EInteger tmp;
                // Move to head of cache if it isn't already
                tmp = this.inputs[i];
                this.inputs[i] = this.inputs[0];
                this.inputs[0] = tmp;
                int tmpi = this.inputsInts[i];
                this.inputsInts[i] = this.inputsInts[0];
                this.inputsInts[0] = tmpi;
                tmp = this.outputs[i];
                this.outputs[i] = this.outputs[0];
                this.outputs[0] = tmp;
                // Move formerly newest to next newest
                if (i != 1) {
                  tmp = this.inputs[i];
                  this.inputs[i] = this.inputs[1];
                  this.inputs[1] = tmp;
                  tmpi = this.inputsInts[i];
                  this.inputsInts[i] =
                    this.inputsInts[1];
                  this.inputsInts[1] = tmpi;
                  tmp = this.outputs[i];
                  this.outputs[i] = this.outputs[1];
                  this.outputs[1] = tmp;
                }
              }
              return this.outputs[0];
            }
          }
        }
        return null;
      }

      public EInteger GetCachedPowerInt(int ibi) {
        synchronized (this.outputs) {
          if (ibi > 0 && this.size < 64) {
            for (int i = 0; i < this.size; ++i) {
              if (this.inputsInts[i] == ibi) {
                return this.outputs[i];
              }
            }
            return null;
          }
          for (int i = 0; i < this.size; ++i) {
            if (this.inputsInts[i] >= 0 && this.inputsInts[i] == ibi) {
              if (i != 0) {
                EInteger tmp;
                // Move to head of cache if it isn't already
                tmp = this.inputs[i];
                this.inputs[i] = this.inputs[0];
                this.inputs[0] = tmp;
                int tmpi = this.inputsInts[i];
                this.inputsInts[i] = this.inputsInts[0];
                this.inputsInts[0] = tmpi;
                tmp = this.outputs[i];
                this.outputs[i] = this.outputs[0];
                this.outputs[0] = tmp;
                // Move formerly newest to next newest
                if (i != 1) {
                  tmp = this.inputs[i];
                  this.inputs[i] = this.inputs[1];
                  this.inputs[1] = tmp;
                  tmpi = this.inputsInts[i];
                  this.inputsInts[i] =
                    this.inputsInts[1];
                  this.inputsInts[1] = tmpi;
                  tmp = this.outputs[i];
                  this.outputs[i] = this.outputs[1];
                  this.outputs[1] = tmp;
                }
              }
              return this.outputs[0];
            }
          }
        }
        return null;
      }

      public void AddPower(int input, EInteger output) {
        this.AddPower(EInteger.FromInt32(input), output);
      }
      public void AddPower(EInteger input, EInteger output) {
        synchronized (this.outputs) {
          if (this.size < MaxSize) {
            // Shift newer entries down
            for (int i = this.size; i > 0; --i) {
              this.inputs[i] = this.inputs[i - 1];
              this.inputsInts[i] = this.inputsInts[i - 1];
              this.outputs[i] = this.outputs[i - 1];
            }
            this.inputs[0] = input;
            this.inputsInts[0] = input.CanFitInInt32() ?
              input.ToInt32Checked() : -1;
            this.outputs[0] = output;
            ++this.size;
          } else {
            // Shift newer entries down
            for (int i = MaxSize - 1; i > 0; --i) {
              this.inputs[i] = this.inputs[i - 1];
              this.inputsInts[i] = this.inputsInts[i - 1];
              this.outputs[i] = this.outputs[i - 1];
            }
            this.inputs[0] = input;
            this.inputsInts[0] = input.CanFitInInt32() ?
              input.ToInt32Checked() : -1;
            this.outputs[0] = output;
          }
        }
      }
    }

    private static final PowerCache ValuePowerOfFiveCache = new
    NumberUtility.PowerCache();

    private static final PowerCache ValuePowerOfTenCache = new
    NumberUtility.PowerCache();

    public static EInteger FindPowerOfTen(long diffLong) {
      if (diffLong < 0) {
        return EInteger.FromInt32(0);
      }
      if (diffLong == 0) {
        return EInteger.FromInt32(1);
      }
      return (diffLong <= Integer.MAX_VALUE) ? FindPowerOfTen((int)diffLong) :
        FindPowerOfTenFromBig(EInteger.FromInt64(diffLong));
    }

    static EInteger MultiplyByPowerOfFive(EInteger v, int precision) {
      if (precision < 0 || v.isZero()) {
        return EInteger.FromInt32(0);
      }
      if (precision <= 94) {
        return v.Multiply(FindPowerOfFive(precision));
      }
      EInteger otherPower = ValuePowerOfFiveCache.GetCachedPowerInt(precision);
      if (otherPower != null) {
        return v.Multiply(otherPower);
      }
      int powprec = 64;
      v = v.Multiply(FindPowerOfFive(precision & 63));
      precision >>= 6;
      while (precision > 0) {
        if ((precision & 1) == 1) {
          otherPower = ValuePowerOfFiveCache.GetCachedPowerInt(powprec);
          if (otherPower == null) {
            // NOTE: Assumes powprec is 2 or greater and is a power of 2
            EInteger prevPower = FindPowerOfFive(powprec >> 1);
            otherPower = prevPower.Multiply(prevPower);
            ValuePowerOfFiveCache.AddPower(powprec, otherPower);
          }
          v = v.Multiply(otherPower);
        }
        powprec = (powprec << 1);
        precision >>= 1;
      }
      return v;
    }

    static EInteger MultiplyByPowerOfFive(EInteger v, EInteger
      epower) {
      return epower.CanFitInInt32() ? MultiplyByPowerOfFive(v,
          epower.ToInt32Checked()) : v.Multiply(FindPowerOfFiveFromBig(
            epower));
    }
    static EInteger FindPowerOfFiveFromBig(EInteger diff) {
      int sign = diff.signum();
      if (sign < 0) {
        return EInteger.FromInt32(0);
      }
      if (sign == 0) {
        return EInteger.FromInt32(1);
      }
      if (diff.CanFitInInt32()) {
        return FindPowerOfFive(diff.ToInt32Checked());
      }
      EInteger epowprec = EInteger.FromInt32(1);
      EInteger ret = EInteger.FromInt32(1);
      while (diff.signum() > 0) {
        if (!diff.isEven()) {
          EInteger otherPower = ValuePowerOfFiveCache.GetCachedPower(epowprec);
          if (otherPower == null) {
            // NOTE: Assumes powprec is 2 or greater and is a power of 2
            EInteger prevPower = FindPowerOfFiveFromBig(epowprec.ShiftRight(
                  1));
            otherPower = prevPower.Multiply(prevPower);
            ValuePowerOfFiveCache.AddPower(epowprec, otherPower);
          }
          ret = ret.Multiply(otherPower);
        }
        epowprec = epowprec.ShiftLeft(1);
        diff = diff.ShiftRight(1);
      }
      return ret;
    }

    static EInteger FindPowerOfTenFromBig(EInteger
      bigintExponent) {
      int sign = bigintExponent.signum();
      if (sign < 0) {
        return EInteger.FromInt32(0);
      }
      if (sign == 0) {
        return EInteger.FromInt32(1);
      }
      return bigintExponent.CanFitInInt32() ?
        FindPowerOfTen(bigintExponent.ToInt32Checked()) :
        FindPowerOfFiveFromBig(bigintExponent).ShiftLeft(bigintExponent);
    }

    private static final EInteger ValueFivePower40 =
      (EInteger.FromInt64(95367431640625L)).Multiply(EInteger.FromInt64(95367431640625L));

    static EInteger FindPowerOfFive(int precision) {
      if (precision < 0) {
        return EInteger.FromInt32(0);
      }
      if (precision <= 27) {
        return ValueBigIntPowersOfFive[(int)precision];
      }
      EInteger bigpow;
      EInteger ret;
      if (precision == 40) {
        return ValueFivePower40;
      }
      int startPrecision = precision;
      bigpow = ValuePowerOfFiveCache.GetCachedPowerInt(precision);
      if (bigpow != null) {
        return bigpow;
      }
      EInteger origPrecision = EInteger.FromInt32(precision);
      // System.out.println("Getting power of five "+precision);
      if (precision <= 54) {
        if ((precision & 1) == 0) {
          ret = ValueBigIntPowersOfFive[(int)(precision >> 1)];
          ret = ret.Multiply(ret);
          ValuePowerOfFiveCache.AddPower(origPrecision, ret);
          return ret;
        }
        ret = ValueBigIntPowersOfFive[27];
        bigpow = ValueBigIntPowersOfFive[((int)precision) - 27];
        ret = ret.Multiply(bigpow);
        ValuePowerOfFiveCache.AddPower(origPrecision, ret);
        return ret;
      } else if (precision <= 94) {
        ret = ValueFivePower40;
        bigpow = FindPowerOfFive(precision - 40);
        ret = ret.Multiply(bigpow);
        ValuePowerOfFiveCache.AddPower(origPrecision, ret);
        return ret;
      }
      int powprec = 64;
      // System.out.println("pow="+(precision&63)+",precision="+precision);
      ret = FindPowerOfFive(precision & 63);
      precision >>= 6;
      while (precision > 0) {
        if ((precision & 1) == 1) {
          EInteger otherPower =
            ValuePowerOfFiveCache.GetCachedPowerInt(powprec);
          // System.out.println("pow="+powprec+",precision="+precision);
          if (otherPower == null) {
            // NOTE: Assumes powprec is 2 or greater and is a power of 2
            EInteger prevPower = FindPowerOfFive(powprec >> 1);
            otherPower = prevPower.Multiply(prevPower);
            ValuePowerOfFiveCache.AddPower(powprec, otherPower);
          }
          ret = ret.Multiply(otherPower);
        }
        powprec = (powprec << 1);
        precision >>= 1;
      }
      return ret;
    }

    static EInteger FindPowerOfTen(int precision) {
      if (precision < 0) {
        return EInteger.FromInt32(0);
      }
      if (precision <= 18) {
        return ValueBigIntPowersOfTen[(int)precision];
      }
      EInteger bigpow;
      EInteger ret;
      int startPrecision = precision;
      bigpow = ValuePowerOfTenCache.GetCachedPowerInt(precision);
      if (bigpow != null) {
        return bigpow;
      }
      // System.out.println("power="+precision);
      if (precision <= 27) {
        ret = ValueBigIntPowersOfFive[precision];
        ret = ret.ShiftLeft(precision);
        ValuePowerOfTenCache.AddPower(precision, ret);
        return ret;
      } else if (precision <= 36) {
        if ((precision & 1) == 0) {
          ret = ValueBigIntPowersOfTen[(int)(precision >> 1)];
          ret = ret.Multiply(ret);
          ValuePowerOfTenCache.AddPower(precision, ret);
          return ret;
        }
        ret = ValueBigIntPowersOfTen[18];
        bigpow = ValueBigIntPowersOfTen[((int)precision) - 18];
        ret = ret.Multiply(bigpow);
        ValuePowerOfTenCache.AddPower(precision, ret);
        return ret;
      }
      return FindPowerOfFive(precision).ShiftLeft(precision);
    }

    public static int BitLength(long mantlong) {
      if (mantlong == 0) {
        return 1;
      }
      int wcextra = 64;
      if ((mantlong >> 32) == 0) {
        mantlong <<= 32;
        wcextra -= 32;
      }
      if ((mantlong >> 48) == 0) {
        mantlong <<= 16;
        wcextra -= 16;
      }
      if ((mantlong >> 56) == 0) {
        mantlong <<= 8;
        wcextra -= 8;
      }
      if ((mantlong >> 60) == 0) {
        mantlong <<= 4;
        wcextra -= 4;
      }
      if ((mantlong >> 62) == 0) {
        mantlong <<= 2;
        wcextra -= 2;
      }
      return ((mantlong >> 63) == 0) ? wcextra - 1 : wcextra;
    }

    public static <THelper> THelper PreRound(
      THelper val,
      EContext ctx,
      IRadixMath<THelper> wrapper) {
      if (ctx == null || !ctx.getHasMaxPrecision()) {
        return val;
      }
      IRadixMathHelper<THelper> helper = wrapper.GetHelper();
      int thisFlags = helper.GetFlags(val);
      if ((thisFlags & BigNumberFlags.FlagSpecial) != 0) {
        // Infinity or NaN
        return val;
      }
      FastInteger fastPrecision = FastInteger.FromBig(ctx.getPrecision());
      EInteger mant = helper.GetMantissa(val).Abs();
      // Rounding is only to be done if the digit count is
      // too big (distinguishing this case is material
      // if the value also has an exponent that's out of range)
      FastInteger[] digitBounds = NumberUtility.DigitLengthBounds(
          helper,
          mant);
      if (digitBounds[1].compareTo(fastPrecision) <= 0) {
        // Upper bound is less than or equal to precision
        return val;
      }
      EContext ctx2 = ctx;
      if (digitBounds[0].compareTo(fastPrecision) <= 0) {
        // Lower bound is less than or equal to precision, so
        // calculate digit length more precisely
        FastInteger digits = helper.GetDigitLength(mant);
        ctx2 = ctx.WithBlankFlags().WithTraps(0);
        if (digits.compareTo(fastPrecision) <= 0) {
          return val;
        }
      }
      val = wrapper.RoundToPrecision(val, ctx2);
      // the only time rounding can signal an invalid
      // operation is if an operand is a signaling NaN, but
      // this was already checked beforehand

      if ((ctx2.getFlags() & EContext.FlagInexact) != 0) {
        if (ctx.getHasFlags()) {
          ctx.setFlags(ctx.getFlags()|(BigNumberFlags.LostDigitsFlags));
        }
      }
      if ((ctx2.getFlags() & EContext.FlagRounded) != 0) {
        if (ctx.getHasFlags()) {
          ctx.setFlags(ctx.getFlags()|(EContext.FlagRounded));
        }
      }
      if ((ctx2.getFlags() & EContext.FlagOverflow) != 0) {
        boolean neg = (thisFlags & BigNumberFlags.FlagNegative) != 0;
        if (ctx.getHasFlags()) {
          ctx.setFlags(ctx.getFlags()|(EContext.FlagLostDigits));
          ctx.setFlags(ctx.getFlags()|(EContext.FlagOverflow |
            EContext.FlagInexact | EContext.FlagRounded));
        }
      }
      return val;
    }

    public static <THelper> FastInteger[] DigitLengthBounds(
      IRadixMathHelper<THelper> helper,
      EInteger ei) {
      int radix = helper.GetRadix();
      if (radix == 2) {
        FastInteger fi =
          FastInteger.FromBig(ei.GetUnsignedBitLengthAsEInteger());
        return new FastInteger[] { fi, fi };
      } else if (radix == 10) {
        EInteger bigBitLength = ei.GetUnsignedBitLengthAsEInteger();
        if (bigBitLength.compareTo(33) < 0) {
          // Can easily be calculated without estimation
          FastInteger fi = helper.GetDigitLength(ei);
          return new FastInteger[] { fi, fi };
        } else if (bigBitLength.compareTo(2135) <= 0) {
          int ov = 1 + ((bigBitLength.ToInt32Checked() * 631305) >> 21);
          return new FastInteger[] {
            new FastInteger(ov - 2), // lower bound
            new FastInteger(ov), // upper bound
          };
        } else {
          // Bit length is big enough that these bounds will
          // overestimate or underestimate the true base-10 digit length
          // as appropriate.
          EInteger lowerBound = bigBitLength.Multiply(100).Divide(335);
          EInteger upperBound = bigBitLength.Divide(3);
          return new FastInteger[] {
            FastInteger.FromBig(lowerBound), // lower bound
            FastInteger.FromBig(upperBound), // upper bound
          };
        }
      } else {
        FastInteger fi = helper.GetDigitLength(ei);
        return new FastInteger[] { fi, fi };
      }
    }

    public static <THelper> FastInteger DigitLengthUpperBound(
      IRadixMathHelper<THelper> helper,
      EInteger ei) {
      int radix = helper.GetRadix();
      if (radix == 2) {
        return FastInteger.FromBig(ei.GetUnsignedBitLengthAsEInteger());
      } else if (radix == 10) {
        EInteger bigBitLength = ei.GetUnsignedBitLengthAsEInteger();
        if (bigBitLength.compareTo(2135) <= 0) {
          // May overestimate by 1
          return new FastInteger(1 + ((bigBitLength.ToInt32Checked() *
                  631305) >> 21));
        } else {
          // Bit length is big enough that dividing it by 3 will not
          // underestimate the true base-10 digit length.
          return FastInteger.FromBig(bigBitLength.Divide(3));
        }
      } else {
        return helper.GetDigitLength(ei);
      }
    }

    public static EInteger ReduceTrailingZeros(
      EInteger bigmant,
      FastInteger exponentMutable,
      int radix,
      FastInteger digits,
      FastInteger precision,
      FastInteger idealExp) {
      /*

      */ if (bigmant.isZero()) {
        exponentMutable.SetInt(0);
        return bigmant;
      }
      if (radix == 2) {
        if (!bigmant.isEven()) {
          return bigmant;
        }
        long lowbit = bigmant.GetLowBitAsInt64();
        if (lowbit != Long.MAX_VALUE) {
          if (precision != null && digits.compareTo(precision) >= 0) {
            // Limit by digits minus precision
            EInteger tmp = digits.AsEInteger().Subtract(precision.AsEInteger());
            if (tmp.compareTo(EInteger.FromInt64(lowbit)) < 0) {
              lowbit = tmp.ToInt64Checked();
            }
          }
          if (idealExp != null && exponentMutable.compareTo(idealExp) <= 0) {
            // Limit by idealExp minus exponentMutable
            EInteger tmp =
              idealExp.AsEInteger().Subtract(exponentMutable.AsEInteger());
            if (tmp.compareTo(EInteger.FromInt64(lowbit)) < 0) {
              lowbit = tmp.ToInt64Checked();
            }
          }
          bigmant = (lowbit <= Integer.MAX_VALUE) ?
            bigmant.ShiftRight((int)lowbit) :
            bigmant.ShiftRight(EInteger.FromInt64(lowbit));
          if (digits != null) {
            digits.SubtractInt64(lowbit);
          }
          if (exponentMutable != null) {
            exponentMutable.AddInt64(lowbit);
          }
          return bigmant;
        }
      }
      EInteger bigradix = EInteger.FromInt32(radix);
      FastInteger bitsToShift = new FastInteger(0);
      while (!bigmant.isZero()) {
        if (precision != null && digits.compareTo(precision) == 0) {
          break;
        }
        if (idealExp != null && exponentMutable.compareTo(idealExp) == 0) {
          break;
        }
        EInteger bigrem;
        EInteger bigquo;
        EInteger[] divrem = bigmant.DivRem(bigradix);
        bigquo = divrem[0];
        bigrem = divrem[1];
        if (!bigrem.isZero()) {
          break;
        }
        bigmant = bigquo;
        exponentMutable.Increment();
        if (digits != null) {
          digits.Decrement();
        }
      }
      return bigmant;
    }
  }
