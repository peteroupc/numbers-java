package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
https://creativecommons.org/publicdomain/zero/1.0/

 */

/*
TODO: In next major version, maybe convert EDecimal.One/Ten/Zero to fields
rather than properties
*/

  public final class EDecimal implements Comparable<EDecimal> {
    private static final int RepeatDivideThreshold = 10000;
    static final int MaxSafeInt = 214748363;

    //----------------------------------------------------------------

    public static final EDecimal NaN = CreateWithFlags(
        EInteger.FromInt32(0),
        EInteger.FromInt32(0),
        (byte)BigNumberFlags.FlagQuietNaN);

    public static final EDecimal NegativeInfinity =
      CreateWithFlags(
        EInteger.FromInt32(0),
        EInteger.FromInt32(0),
        BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);

    public static final EDecimal NegativeZero =
      CreateWithFlags(
        EInteger.FromInt32(0),
        EInteger.FromInt32(0),
        BigNumberFlags.FlagNegative);

    public static final EDecimal One = new EDecimal(
      FastIntegerFixed.FromInt32(1),
      FastIntegerFixed.Zero,
      (byte)0);

    public static final EDecimal PositiveInfinity =
      CreateWithFlags(
        EInteger.FromInt32(0),
        EInteger.FromInt32(0),
        BigNumberFlags.FlagInfinity);

    public static final EDecimal SignalingNaN =
      CreateWithFlags(
        EInteger.FromInt32(0),
        EInteger.FromInt32(0),
        BigNumberFlags.FlagSignalingNaN);

    public static final EDecimal Ten = new EDecimal(
      FastIntegerFixed.FromInt32(10),
      FastIntegerFixed.Zero,
      (byte)0);

    public static final EDecimal Zero = new EDecimal(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)0);

    private static final int CacheFirst = -24;
    private static final int CacheLast = 128;
    private static final EDecimal[] Cache = EDecimalCache(CacheFirst,
        CacheLast);

    static EDecimal FromCache(int v) {
      return Cache[v - CacheFirst];
    }

    private static EDecimal[] EDecimalCache(int first, int last) {
      EDecimal[] cache = new EDecimal[(last - first) + 1];
      int i;
      for (i = first; i <= last; ++i) {
        if (i == 0) {
          cache[i - first] = Zero;
        } else if (i == 1) {
          cache[i - first] = One;
        } else if (i == 10) {
          cache[i - first] = Ten;
        } else {
          cache[i - first] = new EDecimal(
            FastIntegerFixed.FromInt32(Math.abs(i)),
            FastIntegerFixed.Zero,
            (byte)(i < 0 ? BigNumberFlags.FlagNegative : 0));
        }
      }
      return cache;
    }

    private static final DecimalMathHelper HelperValue = new
    DecimalMathHelper();

    private static final IRadixMath<EDecimal> ExtendedMathValue = new
    RadixMath<EDecimal>(HelperValue);
    //----------------------------------------------------------------
    private static final IRadixMath<EDecimal> MathValue = new
    TrappableRadixMath<EDecimal>(
      new ExtendedOrSimpleRadixMath<EDecimal>(HelperValue));

    private static final int[] ValueTenPowers = {
      1, 10, 100, 1000, 10000, 100000,
      1000000, 10000000, 100000000,
      1000000000,
    };

    private final FastIntegerFixed unsignedMantissa;
    private final FastIntegerFixed exponent;
    private final byte flags;

    EDecimal(
      FastIntegerFixed unsignedMantissa,
      FastIntegerFixed exponent,
      byte flags) {
      this.unsignedMantissa = unsignedMantissa;
      this.exponent = exponent;
      this.flags = flags;
    }

    public EDecimal Copy() {
      return new EDecimal(
          this.unsignedMantissa.Copy(),
          this.exponent.Copy(),
          this.flags);
    }

    public final EInteger getExponent() {
        return this.exponent.ToEInteger();
      }

    public final boolean isFinite() {
        return (this.flags & (BigNumberFlags.FlagInfinity |
              BigNumberFlags.FlagNaN)) == 0;
      }

    public final boolean isNegative() {
        return (this.flags & BigNumberFlags.FlagNegative) != 0;
      }

    public final boolean isZero() {
        return ((this.flags & BigNumberFlags.FlagSpecial) == 0) &&
          this.unsignedMantissa.isValueZero();
      }

    public boolean IsInteger() {
      if (!this.isFinite()) {
        return false;
      }
      if (this.isZero() || this.exponent.CompareToInt(0) >= 0) {
        return true;
      } else {
        EDecimal r = this.Reduce(null);
        return r.exponent.CompareToInt(0) >= 0;
      }
    }

    public final EInteger getMantissa() {
        return this.isNegative() ? this.unsignedMantissa.ToEInteger().Negate() :
          this.unsignedMantissa.ToEInteger();
      }

    public final int signum() {
        return (((this.flags & BigNumberFlags.FlagSpecial) == 0) &&
            this.unsignedMantissa.isValueZero()) ? 0 : (((this.flags &
                BigNumberFlags.FlagNegative) != 0) ? -1 : 1);
      }

    public final EInteger getUnsignedMantissa() {
        return this.unsignedMantissa.ToEInteger();
      }

    static EDecimal ChangeExponent(EDecimal ret, EInteger exponent) {
      return new EDecimal(
          ret.unsignedMantissa,
          FastIntegerFixed.FromBig(exponent),
          (byte)ret.flags);
    }

    public static EDecimal Create(int mantissaSmall, int exponentSmall) {
      if (exponentSmall == 0 && mantissaSmall >= CacheFirst &&
        mantissaSmall <= CacheLast) {
        return Cache[mantissaSmall - CacheFirst];
      }
      if (mantissaSmall < 0) {
        if (mantissaSmall == Integer.MIN_VALUE) {
          FastIntegerFixed fi = FastIntegerFixed.FromInt64(Integer.MIN_VALUE);
          return new EDecimal(
              fi.Negate(),
              FastIntegerFixed.FromInt32(exponentSmall),
              (byte)BigNumberFlags.FlagNegative);
        }
        return new EDecimal(
            FastIntegerFixed.FromInt32(-mantissaSmall),
            FastIntegerFixed.FromInt32(exponentSmall),
            (byte)BigNumberFlags.FlagNegative);
      } else if (mantissaSmall == 0) {
        return new EDecimal(
            FastIntegerFixed.Zero,
            FastIntegerFixed.FromInt32(exponentSmall),
            (byte)0);
      } else {
        return new EDecimal(
            FastIntegerFixed.FromInt32(mantissaSmall),
            FastIntegerFixed.FromInt32(exponentSmall),
            (byte)0);
      }
    }

    public static EDecimal Create(
      EInteger mantissa,
      int exponentSmall) {
      if (mantissa == null) {
        throw new NullPointerException("mantissa");
      }
      if (mantissa.CanFitInInt32()) {
        int mantissaSmall = mantissa.ToInt32Checked();
        return Create(mantissaSmall, exponentSmall);
      }
      FastIntegerFixed fi = FastIntegerFixed.FromBig(mantissa);
      int sign = fi.signum();
      return new EDecimal(
          sign < 0 ? fi.Negate() : fi,
          FastIntegerFixed.FromInt32(exponentSmall),
          (byte)((sign < 0) ? BigNumberFlags.FlagNegative : 0));
    }

    public static EDecimal Create(
      EInteger mantissa,
      long exponentLong) {
      if (mantissa == null) {
        throw new NullPointerException("mantissa");
      }
      if (mantissa.CanFitInInt64()) {
        long mantissaLong = mantissa.ToInt64Checked();
        return Create(mantissaLong, exponentLong);
      }
      FastIntegerFixed fi = FastIntegerFixed.FromBig(mantissa);
      int sign = fi.signum();
      return new EDecimal(
          sign < 0 ? fi.Negate() : fi,
          FastIntegerFixed.FromInt64(exponentLong),
          (byte)((sign < 0) ? BigNumberFlags.FlagNegative : 0));
    }

    public static EDecimal Create(
      EInteger mantissa,
      EInteger exponent) {
      if (mantissa == null) {
        throw new NullPointerException("mantissa");
      }
      if (exponent == null) {
        throw new NullPointerException("exponent");
      }
      if (mantissa.CanFitInInt32() && exponent.isZero()) {
        int mantissaSmall = mantissa.ToInt32Checked();
        return Create(mantissaSmall, 0);
      }
      FastIntegerFixed fi = FastIntegerFixed.FromBig(mantissa);
      int sign = fi.signum();
      return new EDecimal(
          sign < 0 ? fi.Negate() : fi,
          FastIntegerFixed.FromBig(exponent),
          (byte)((sign < 0) ? BigNumberFlags.FlagNegative : 0));
    }

    public static EDecimal Create(
      long mantissaLong,
      int exponentSmall) {
      return Create(mantissaLong, (long)exponentSmall);
    }

    public static EDecimal Create(
      long mantissaLong,
      long exponentLong) {
      if (mantissaLong >= Integer.MIN_VALUE && mantissaLong <= Integer.MAX_VALUE &&
        exponentLong >= Integer.MIN_VALUE && exponentLong <= Integer.MAX_VALUE) {
        return Create((int)mantissaLong, (int)exponentLong);
      } else if (mantissaLong == Long.MIN_VALUE) {
        FastIntegerFixed fi = FastIntegerFixed.FromInt64(mantissaLong);
        return new EDecimal(
            fi.Negate(),
            FastIntegerFixed.FromInt64(exponentLong),
            (byte)((mantissaLong < 0) ? BigNumberFlags.FlagNegative : 0));
      } else {
        FastIntegerFixed fi = FastIntegerFixed.FromInt64(Math.abs(
              mantissaLong));
        return new EDecimal(
            fi,
            FastIntegerFixed.FromInt64(exponentLong),
            (byte)((mantissaLong < 0) ? BigNumberFlags.FlagNegative : 0));
      }
    }

    public static EDecimal CreateNaN(EInteger diag) {
      return CreateNaN(diag, false, false, null);
    }

    public static EDecimal CreateNaN(
      EInteger diag,
      boolean signaling,
      boolean negative,
      EContext ctx) {
      if (diag == null) {
        throw new NullPointerException("diag");
      }
      if (diag.signum() < 0) {
        throw new IllegalArgumentException("Diagnostic information must be 0 or" +
          "\u0020greater," + "\u0020 was: " + diag);
      }
      if (diag.isZero() && !negative) {
        return signaling ? SignalingNaN : NaN;
      }
      int flags = 0;
      if (negative) {
        flags |= BigNumberFlags.FlagNegative;
      }
      if (ctx != null && ctx.getHasMaxPrecision()) {
        flags |= BigNumberFlags.FlagQuietNaN;
        EDecimal ef = new EDecimal(
          FastIntegerFixed.FromBig(diag),
          FastIntegerFixed.Zero,
          (byte)flags).RoundToPrecision(ctx);

        int newFlags = ef.flags;
        newFlags &= ~BigNumberFlags.FlagQuietNaN;
        newFlags |= signaling ? BigNumberFlags.FlagSignalingNaN :
          BigNumberFlags.FlagQuietNaN;
        return new EDecimal(
            ef.unsignedMantissa,
            ef.exponent,
            (byte)newFlags);
      }
      flags |= signaling ? BigNumberFlags.FlagSignalingNaN :
        BigNumberFlags.FlagQuietNaN;
      return new EDecimal(
          FastIntegerFixed.FromBig(diag),
          FastIntegerFixed.Zero,
          (byte)flags);
    }

    public static EDecimal FromDouble(double dbl) {
      long lvalue = Double.doubleToRawLongBits(dbl);
      return FromDoubleBits(lvalue);
    }

    public static EDecimal FromDoubleBits(long dblBits) {
      int[] value = new int[] {
        ((int)(dblBits & 0xffffffffL)),
        ((int)((dblBits >> 32) & 0xffffffffL)),
      };
      int floatExponent = (int)((value[1] >> 20) & 0x7ff);
      boolean neg = (value[1] >> 31) != 0;
      long lvalue;
      if (floatExponent == 2047) {
        if ((value[1] & 0xfffff) == 0 && value[0] == 0) {
          return neg ? NegativeInfinity : PositiveInfinity;
        }
        // Treat high bit of mantissa as quiet/signaling bit
        boolean quiet = (value[1] & 0x80000) != 0;
        value[1] &= 0x7ffff;
        lvalue = ((value[0] & 0xffffffffL) | ((long)value[1] << 32));
        int flags = (neg ? BigNumberFlags.FlagNegative : 0) | (quiet ?
            BigNumberFlags.FlagQuietNaN : BigNumberFlags.FlagSignalingNaN);
        return (lvalue == 0 && !neg) ? (quiet ? NaN : SignalingNaN) :
          new EDecimal(
            FastIntegerFixed.FromInt64(lvalue),
            FastIntegerFixed.Zero,
            (byte)flags);
      }
      value[1] &= 0xfffff;

      // Mask out the exponent and sign
      if (floatExponent == 0) {
        ++floatExponent;
      } else {
        value[1] |= 0x100000;
      }
      if ((value[1] | value[0]) != 0) {
        floatExponent += NumberUtility.ShiftAwayTrailingZerosTwoElements(
            value);
      } else {
        return neg ? EDecimal.NegativeZero : EDecimal.Zero;
      }
      floatExponent -= 1075;
      lvalue = ((value[0] & 0xffffffffL) | ((long)value[1] << 32));
      if (floatExponent == 0) {
        if (neg) {
          lvalue = -lvalue;
        }
        return EDecimal.FromInt64(lvalue);
      }
      if (floatExponent > 0) {
        // Value is an integer
        EInteger bigmantissa = EInteger.FromInt64(lvalue);
        bigmantissa = bigmantissa.ShiftLeft(floatExponent);
        if (neg) {
          bigmantissa=(bigmantissa).Negate();
        }
        return EDecimal.FromEInteger(bigmantissa);
      } else {
        // Value has a fractional part
        EInteger bigmantissa = EInteger.FromInt64(lvalue);
        EInteger bigexp = NumberUtility.FindPowerOfFive(-floatExponent);
        bigmantissa = bigmantissa.Multiply(bigexp);
        if (neg) {
          bigmantissa=(bigmantissa).Negate();
        }
        return EDecimal.Create(bigmantissa, EInteger.FromInt32(floatExponent));
      }
    }

    public static EDecimal FromEInteger(EInteger bigint) {
      return EDecimal.Create(bigint, EInteger.FromInt32(0));
    }

/**
 * @deprecated Renamed to FromEFloat.
 */
@Deprecated
    public static EDecimal FromExtendedFloat(EFloat ef) {
      return FromEFloat(ef);
    }

    public static EDecimal FromEFloat(EFloat bigfloat) {
      if (bigfloat == null) {
        throw new NullPointerException("bigfloat");
      }
      if (bigfloat.IsNaN() || bigfloat.IsInfinity()) {
        int flags = (bigfloat.isNegative() ? BigNumberFlags.FlagNegative : 0) |
          (bigfloat.IsInfinity() ? BigNumberFlags.FlagInfinity : 0) |
          (bigfloat.IsQuietNaN() ? BigNumberFlags.FlagQuietNaN : 0) |
          (bigfloat.IsSignalingNaN() ? BigNumberFlags.FlagSignalingNaN : 0);
        return CreateWithFlags(
            bigfloat.getUnsignedMantissa(),
            bigfloat.getExponent(),
            flags);
      }
      EInteger bigintExp = bigfloat.getExponent();
      EInteger bigSignedMantissa = bigfloat.getMantissa();
      if (bigSignedMantissa.isZero()) {
        return bigfloat.isNegative() ? EDecimal.NegativeZero :
          EDecimal.Zero;
      }
      if (bigintExp.isZero()) {
        // Integer
        return EDecimal.FromEInteger(bigSignedMantissa);
      }
      if (bigintExp.signum() > 0) {
        // Scaled integer
        FastInteger intcurexp = FastInteger.FromBig(bigintExp);
        EInteger bigmantissa = bigSignedMantissa;
        boolean neg = bigmantissa.signum() < 0;
        if (neg) {
          bigmantissa=(bigmantissa).Negate();
        }
        while (intcurexp.signum() > 0) {
          int shift = 1000000;
          if (intcurexp.CompareToInt(1000000) < 0) {
            shift = intcurexp.ToInt32();
          }
          bigmantissa = bigmantissa.ShiftLeft(shift);
          intcurexp.AddInt(-shift);
        }
        if (neg) {
          bigmantissa=(bigmantissa).Negate();
        }
        return EDecimal.FromEInteger(bigmantissa);
      } else {
        // Fractional number
        EInteger bigmantissa = bigSignedMantissa;
        EInteger negbigintExp=(bigintExp).Negate();
        negbigintExp = NumberUtility.FindPowerOfFiveFromBig(negbigintExp);
        bigmantissa = bigmantissa.Multiply(negbigintExp);
        return EDecimal.Create(bigmantissa, bigintExp);
      }
    }

    public static EDecimal FromBoolean(boolean boolValue) {
      return boolValue ? EDecimal.One : EDecimal.Zero;
    }

    public static EDecimal FromInt32(int valueSmaller) {
      if (valueSmaller >= CacheFirst && valueSmaller <= CacheLast) {
        return Cache[valueSmaller - CacheFirst];
      }
      if (valueSmaller == Integer.MIN_VALUE) {
        return Create(EInteger.FromInt32(valueSmaller), EInteger.FromInt32(0));
      }
      if (valueSmaller < 0) {
        return new EDecimal(
            FastIntegerFixed.FromInt32(valueSmaller).Negate(),
            FastIntegerFixed.Zero,
            (byte)BigNumberFlags.FlagNegative);
      } else {
        return new EDecimal(
            FastIntegerFixed.FromInt32(valueSmaller),
            FastIntegerFixed.Zero,
            (byte)0);
      }
    }

    public static EDecimal FromInt64AsUnsigned(long longerValue) {
      return longerValue >= 0 ? FromInt64(longerValue) :
        FromEInteger(EInteger.FromInt64AsUnsigned(longerValue));
    }

    public static EDecimal FromInt64(long valueSmall) {
      if (valueSmall >= CacheFirst && valueSmall <= CacheLast) {
        return Cache[(int)(valueSmall - CacheFirst)];
      }
      if (valueSmall > Integer.MIN_VALUE && valueSmall <= Integer.MAX_VALUE) {
        if (valueSmall < 0) {
          return new EDecimal(
              FastIntegerFixed.FromInt32((int)valueSmall).Negate(),
              FastIntegerFixed.Zero,
              (byte)BigNumberFlags.FlagNegative);
        } else {
          return new EDecimal(
              FastIntegerFixed.FromInt32((int)valueSmall),
              FastIntegerFixed.Zero,
              (byte)0);
        }
      }
      EInteger bigint = EInteger.FromInt64(valueSmall);
      return EDecimal.Create(bigint, EInteger.FromInt32(0));
    }

    public static EDecimal FromSingle(float flt) {
      int value = Float.floatToRawIntBits(flt);
      return FromSingleBits(value);
    }

    public static EDecimal FromSingleBits(int value) {
      boolean neg = (value >> (EFloat.Binary32Bits - 1)) != 0;
      int floatExponent = (int)((value >> EFloat.Binary32SignifAreaBits) &
((1 << EFloat.Binary32ExponentArea) - 1));
      int valueFpMantissa = value & ((1 << EFloat.Binary32SignifAreaBits) - 1);
      EInteger bigmant;
      if (floatExponent == ((1 << EFloat.Binary32ExponentArea) - 1)) {
        if (valueFpMantissa == 0) {
          return neg ? NegativeInfinity : PositiveInfinity;
        }
        // Treat high bit of mantissa as quiet/signaling bit
        boolean quiet = (valueFpMantissa &
(1 << (EFloat.Binary32SignifAreaBits - 1))) != 0;
        valueFpMantissa &= (1 << (EFloat.Binary32SignifAreaBits - 1)) - 1;
        bigmant = EInteger.FromInt32(valueFpMantissa);
        value = (neg ? BigNumberFlags.FlagNegative : 0) | (quiet ?
            BigNumberFlags.FlagQuietNaN : BigNumberFlags.FlagSignalingNaN);
        if (bigmant.isZero() && !neg) {
          return quiet ? NaN : SignalingNaN;
        }
        return CreateWithFlags(
            bigmant,
            EInteger.FromInt32(0),
            value);
      }
      if (floatExponent == 0) {
        ++floatExponent;
      } else {
        valueFpMantissa |= 1 << EFloat.Binary32SignifAreaBits;
      }
      if (valueFpMantissa == 0) {
        return neg ? EDecimal.NegativeZero : EDecimal.Zero;
      }
      --floatExponent;
      floatExponent += EFloat.Binary32EMin;
      while ((valueFpMantissa & 1) == 0) {
        ++floatExponent;
        valueFpMantissa >>= 1;
      }
      if (floatExponent == 0) {
        if (neg) {
          valueFpMantissa = -valueFpMantissa;
        }
        return EDecimal.FromInt64(valueFpMantissa);
      }
      if (floatExponent > 0) {
        // Value is an integer
        EInteger bigmantissa = EInteger.FromInt32(valueFpMantissa);
        bigmantissa = bigmantissa.ShiftLeft(floatExponent);
        if (neg) {
          bigmantissa=(bigmantissa).Negate();
        }
        return EDecimal.FromEInteger(bigmantissa);
      } else {
        // Value has a fractional part
        EInteger bigmantissa = EInteger.FromInt32(valueFpMantissa);
        EInteger bigexponent = NumberUtility.FindPowerOfFive(-floatExponent);
        bigmantissa = bigmantissa.Multiply(bigexponent);
        if (neg) {
          bigmantissa=(bigmantissa).Negate();
        }
        return EDecimal.Create(bigmantissa, EInteger.FromInt32(floatExponent));
      }
    }

    public static EDecimal FromHalfBits(short value) {
      int ivalue = ((int)value) & 0xffff;
      boolean neg = (ivalue >> (EFloat.Binary16Bits - 1)) != 0;
      int floatExponent = (int)((ivalue >> EFloat.Binary16SignifAreaBits) &
((1 << EFloat.Binary16ExponentArea) - 1));
      int valueFpMantissa = ivalue & ((1 << EFloat.Binary16SignifAreaBits) - 1);
      EInteger bigmant;
      if (floatExponent == ((1 << EFloat.Binary16ExponentArea) - 1)) {
        if (valueFpMantissa == 0) {
          return neg ? NegativeInfinity : PositiveInfinity;
        }
        // Treat high bit of mantissa as quiet/signaling bit
        boolean quiet = (valueFpMantissa &
(1 << (EFloat.Binary16SignifAreaBits - 1))) != 0;
        valueFpMantissa &= (1 << (EFloat.Binary16SignifAreaBits - 1)) - 1;
        bigmant = EInteger.FromInt32(valueFpMantissa);
        ivalue = (neg ? BigNumberFlags.FlagNegative : 0) | (quiet ?
            BigNumberFlags.FlagQuietNaN : BigNumberFlags.FlagSignalingNaN);
        if (bigmant.isZero() && !neg) {
          return quiet ? NaN : SignalingNaN;
        }
        return CreateWithFlags(
            bigmant,
            EInteger.FromInt32(0),
            ivalue);
      }
      if (floatExponent == 0) {
        ++floatExponent;
      } else {
        valueFpMantissa |= 1 << EFloat.Binary16SignifAreaBits;
      }
      if (valueFpMantissa == 0) {
        return neg ? EDecimal.NegativeZero : EDecimal.Zero;
      }
      --floatExponent;
      floatExponent += EFloat.Binary16EMin;
      while ((valueFpMantissa & 1) == 0) {
        ++floatExponent;
        valueFpMantissa >>= 1;
      }
      if (floatExponent == 0) {
        if (neg) {
          valueFpMantissa = -valueFpMantissa;
        }
        return EDecimal.FromInt64(valueFpMantissa);
      }
      if (floatExponent > 0) {
        // Value is an integer
        EInteger bigmantissa = EInteger.FromInt32(valueFpMantissa);
        bigmantissa = bigmantissa.ShiftLeft(floatExponent);
        if (neg) {
          bigmantissa=(bigmantissa).Negate();
        }
        return EDecimal.FromEInteger(bigmantissa);
      } else {
        // Value has a fractional part
        EInteger bigmantissa = EInteger.FromInt32(valueFpMantissa);
        EInteger bigexponent = NumberUtility.FindPowerOfFive(-floatExponent);
        bigmantissa = bigmantissa.Multiply(bigexponent);
        if (neg) {
          bigmantissa=(bigmantissa).Negate();
        }
        return EDecimal.Create(bigmantissa, EInteger.FromInt32(floatExponent));
      }
    }

    public static EDecimal FromString(char[] chars) {
      return FromString(chars, 0, chars == null ? 0 : chars.length, null);
    }

    public static EDecimal FromString(char[] chars, EContext ctx) {
      return FromString(chars, 0, chars == null ? 0 : chars.length, ctx);
    }

    public static EDecimal FromString(
      char[] chars,
      int offset,
      int length) {
      return FromString(chars, offset, length, null);
    }

    public static EDecimal FromString(
      char[] chars,
      int offset,
      int length,
      EContext ctx) {
      if (chars == null) {
        throw new NullPointerException("chars");
      }
      return EDecimalCharArrayString.FromString(
          chars,
          offset,
          length,
          ctx,
          true);
    }

    public static EDecimal FromString(byte[] bytes) {
      return FromString(bytes, 0, bytes == null ? 0 : bytes.length, null);
    }

    public static EDecimal FromString(byte[] bytes, EContext ctx) {
      return FromString(bytes, 0, bytes == null ? 0 : bytes.length, ctx);
    }

    public static EDecimal FromString(
      byte[] bytes,
      int offset,
      int length) {
      return FromString(bytes, offset, length, null);
    }

    public static EDecimal FromString(
      byte[] bytes,
      int offset,
      int length,
      EContext ctx) {
      if (bytes == null) {
        throw new NullPointerException("bytes");
      }
      return EDecimalByteArrayString.FromString(
          bytes,
          offset,
          length,
          ctx,
          true);
    }

    public static EDecimal FromString(String str) {
      return FromString(str, 0, str == null ? 0 : str.length(), null);
    }

    public static EDecimal FromString(String str, EContext ctx) {
      return FromString(str, 0, str == null ? 0 : str.length(), ctx);
    }

    public static EDecimal FromString(
      String str,
      int offset,
      int length) {
      return FromString(str, offset, length, null);
    }

    public static EDecimal FromString(
      String str,
      int offset,
      int length,
      EContext ctx) {
      int tmpoffset = offset;
      if (str == null) {
        throw new NullPointerException("str");
      }
      return EDecimalTextString.FromString(str, offset, length, ctx, true);
    }

    static EDecimal SignalUnderflow(EContext ec, boolean negative, boolean
      zeroSignificand) {
      EInteger eTiny = ec.getEMin().Subtract(ec.getPrecision().Subtract(1));
      eTiny = eTiny.Subtract(2); // subtract 2 from proper eTiny to
      // trigger underflow (2, rather than 1, because of HalfUp mode)
      EDecimal ret = EDecimal.Create(
          zeroSignificand ? EInteger.FromInt32(0) : EInteger.FromInt32(1),
          eTiny);
      if (negative) {
        ret = ret.Negate();
      }
      return ret.RoundToPrecision(ec);
    }

    static EDecimal SignalOverflow(EContext ec, boolean negative, boolean
      zeroSignificand) {
      if (zeroSignificand) {
        EDecimal ret = EDecimal.Create(EInteger.FromInt32(0), ec.getEMax());
        if (negative) {
          ret = ret.Negate();
        }
        return ret.RoundToPrecision(ec);
      } else {
        return GetMathValue(ec).SignalOverflow(ec, negative);
      }
    }

    // 1 = Overflow; 2 = Underflow, adjust significand to 1; 0 = None;
    // 3 = Underflow, adjust significant to have precision
    static int CheckOverflowUnderflow(
      EContext ec,
      int precisionInt,
      EInteger exponent) {
      // NOTE: precisionInt is an Int32 because the maximum supported
      // length of a String fits in an Int32
      // NOTE: Not guaranteed to catch all overflows or underflows.
      if (exponent == null) {
        throw new NullPointerException("exponent");
      }
      if (precisionInt < 0) {
        throw new IllegalArgumentException("doesn't satisfy precision.signum()>= 0");
      }
      // "Precision" is the number of digits in a number starting with
      // the first nonzero digit
      if (ec == null || !ec.getHasExponentRange()) {
        return 0;
      }
      if (ec.getAdjustExponent()) {
        // If precision is in bits, this is too difficult to determine,
        // so ignore precision
        if (ec.isPrecisionInBits()) {
          if (exponent.compareTo(ec.getEMax()) > 0) {
            return 1; // Overflow
          }
        } else {
          EInteger adjExponent = exponent.Add(precisionInt).Subtract(1);
          if (adjExponent.compareTo(ec.getEMax()) > 0) {
            return 1; // Overflow
          }
          if (ec.getHasMaxPrecision()) {
            EInteger etiny = ec.getEMin().Subtract(ec.getPrecision().Subtract(1));
            etiny = etiny.Subtract(1); // Buffer in case of rounding
            // System.out.println("adj: adjexp=" + adjExponent + " exp=" + exponent + "
            // etiny="+etiny);
            if (adjExponent.compareTo(etiny) < 0) {
              return 2; // Underflow to zero
            }
          } else {
            EInteger etiny = ec.getEMin().Subtract(precisionInt - 1);
            etiny = etiny.Subtract(1); // Buffer in case of rounding
            // System.out.println("adj: adjexp=" + adjExponent + " exp=" + exponent + "
            // etiny="+etiny);
            if (adjExponent.compareTo(etiny) < 0) {
              return 3; // Underflow to zero
            }
          }
        }
      } else {
        // Exponent range is independent of precision
        if (exponent.compareTo(ec.getEMax()) > 0) {
          return 1; // Overflow
        }
        if (!ec.isPrecisionInBits()) {
          EInteger adjExponent = exponent.Add(precisionInt).Subtract(1);
          EInteger etiny = ec.getHasMaxPrecision() ?
            ec.getEMin().Subtract(ec.getPrecision().Subtract(1)) :
            ec.getEMin().Subtract(precisionInt - 1);
          etiny = etiny.Subtract(1); // Buffer in case of rounding
          // System.out.println("noadj: adjexp=" + adjExponent + " exp=" + exponent + "
          // etiny="+etiny);
          if (adjExponent.compareTo(etiny) < 0) {
            return 2; // Underflow to zero
          }
        }
      }
      return 0;
    }

    public static EDecimal Max(
      EDecimal first,
      EDecimal second,
      EContext ctx) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return GetMathValue(ctx).Max(first, second, ctx);
    }

    public static EDecimal Max(
      EDecimal first,
      EDecimal second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return Max(first, second, null);
    }

    public static EDecimal MaxMagnitude(
      EDecimal first,
      EDecimal second,
      EContext ctx) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return GetMathValue(ctx).MaxMagnitude(first, second, ctx);
    }

    public static EDecimal MaxMagnitude(
      EDecimal first,
      EDecimal second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return MaxMagnitude(first, second, null);
    }

    public static EDecimal Min(
      EDecimal first,
      EDecimal second,
      EContext ctx) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return GetMathValue(ctx).Min(first, second, ctx);
    }

    public static EDecimal Min(
      EDecimal first,
      EDecimal second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return Min(first, second, null);
    }

    public static EDecimal MinMagnitude(
      EDecimal first,
      EDecimal second,
      EContext ctx) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return GetMathValue(ctx).MinMagnitude(first, second, ctx);
    }

    public static EDecimal MinMagnitude(
      EDecimal first,
      EDecimal second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return MinMagnitude(first, second, null);
    }

    public static EDecimal PI(EContext ctx) {
      return GetMathValue(ctx).Pi(ctx);
    }

    public EDecimal Abs() {
      if (this.isNegative()) {
        EDecimal er = new EDecimal(
          this.unsignedMantissa,
          this.exponent,
          (byte)(this.flags & ~BigNumberFlags.FlagNegative));
        return er;
      }
      return this;
    }

    public EDecimal CopySign(EDecimal other) {
      if (other == null) {
        throw new NullPointerException("other");
      }
      if (this.isNegative()) {
        return other.isNegative() ? this : this.Negate();
      } else {
        return other.isNegative() ? this.Negate() : this;
      }
    }

    public EDecimal Abs(EContext context) {
      return ((context == null || context == EContext.UnlimitedHalfEven) ?
          ExtendedMathValue : MathValue).Abs(this, context);
    }

    public EDecimal Add(EDecimal otherValue) {
      if (this.isFinite() && otherValue != null && otherValue.isFinite() &&
        ((this.flags | otherValue.flags) & BigNumberFlags.FlagNegative) == 0 &&
        this.exponent.compareTo(otherValue.exponent) == 0) {
        FastIntegerFixed result = FastIntegerFixed.Add(
            this.unsignedMantissa,
            otherValue.unsignedMantissa);
        return new EDecimal(result, this.exponent, (byte)0);
      }
      return this.Add(otherValue, EContext.UnlimitedHalfEven);
    }

    public EDecimal Add(
      EDecimal otherValue,
      EContext ctx) {
      return GetMathValue(ctx).Add(this, otherValue, ctx);
    }

    public int compareTo(EDecimal other) {
      return this.CompareToValue(other);
    }

    public int compareTo(int intOther) {
      return this.CompareToValue(EDecimal.FromInt32(intOther));
    }

    public int CompareToValue(int intOther) {
      return this.CompareToValue(EDecimal.FromInt32(intOther));
    }

    public int compareTo(long intOther) {
      return this.CompareToValue(EDecimal.FromInt64(intOther));
    }

    public int CompareToValue(long intOther) {
      return this.CompareToValue(EDecimal.FromInt64(intOther));
    }

    public int CompareToValue(EDecimal other) {
      return ExtendedMathValue.compareTo(this, other);
    }

    public int CompareToBinary(EFloat other) {
      return CompareEDecimalToEFloat(this, other);
    }
    private static int CompareEDecimalToEFloat(EDecimal ed, EFloat ef) {
      if (ef == null) {
        return 1;
      }
      if (ed.IsNaN()) {
        return ef.IsNaN() ? 0 : 1;
      }
      int signA = ed.signum();
      int signB = ef.signum();
      if (signA != signB) {
        return (signA < signB) ? -1 : 1;
      }
      if (signB == 0 || signA == 0) {
        // Special case: Either operand is zero
        return 0;
      }
      if (ed.IsInfinity()) {
        if (ef.IsInfinity()) {
          // if we get here, this only means that
          // both are positive infinity or both
          // are negative infinity
          return 0;
        }
        return ed.isNegative() ? -1 : 1;
      }
      if (ef.IsInfinity()) {
        return ef.isNegative() ? 1 : -1;
      }
      // At this point, both numbers are finite and
      // have the same sign

      // System.out.println("efexp=" + ef.getExponent());
      if (ef.getExponent().compareTo(EInteger.FromInt64(-1000)) < 0) {
        // For very low exponents (less than -1000), the conversion to
        // decimal can take very long, so try this approach
        if (ef.Abs(null).compareTo(EFloat.One) < 0) {
          // Abs less than 1
          if (ed.Abs(null).compareTo(EDecimal.One) >= 0) {
            // Abs 1 or more
            return (signA > 0) ? 1 : -1;
          }
        }
        // System.out.println("edexp=" + ed.getExponent() + ", efexp=" +
        // ef.getExponent());
        EInteger bitCount = ef.getMantissa().GetUnsignedBitLengthAsEInteger();
        EInteger absexp = ef.getExponent().Abs();
        if (absexp.compareTo(bitCount) > 0) {
          // Float's absolute value is less than 1, so do a trial comparison
          // using a different EFloat with the same significand but
          // with an exponent changed to be closer to 0
          EFloat trial = EFloat.Create(ef.getMantissa(), EInteger.FromInt32(
                -1000));
          int trialcmp = CompareEDecimalToEFloat(ed, trial);
          // System.out.println("trialcmp result="+trialcmp);
          if (ef.signum() < 0 && trialcmp < 0) {
            // if float and decimal are negative and
            // decimal is less than trial float (which in turn is
            // less than the actual float), then the decimal is
            // less than the actual float
            return -1;
          }
          if (ef.signum() > 0 && trialcmp > 0) {
            // if float and decimal are positive and
            // decimal is greater than trial float (which in turn is
            // greater than the actual float), then the decimal is
            // greater than the actual float
            return 1;
          }
        }
        EInteger[] thisAdjExpBounds = GetAdjustedExponentDecimalBounds(ed);
        EInteger otherAdjExp = GetAdjustedExponentBinary(ef);
        // System.out.println("taexp=" + thisAdjExp + ", oaexp=" + otherAdjExp);
        // System.out.println("td=" + ed.ToDouble() + ", tf=" + ef.ToDouble());
        if (thisAdjExpBounds[0].signum() < 0 &&
          thisAdjExpBounds[0].compareTo(-1000) >= 0 &&
          otherAdjExp.compareTo(-4000) < 0) {
          // With these exponent combinations, the binary's absolute
          // value is less than the decimal's
          return (signA > 0) ? 1 : -1;
        }
        if (thisAdjExpBounds[1].signum() < 0 &&
          thisAdjExpBounds[1].compareTo(-1000) < 0 &&
          otherAdjExp.compareTo(-1000) < 0) {
          // Check the ratio of the negative binary exponent to
          // the negative decimal exponent.
          // If the ratio times 1000, rounded down, is less than 3321, the
          // binary's absolute value is
          // greater. If it's greater than 3322, the decimal's absolute value is
          // greater.
          // (If the two absolute values are equal, the ratio will approach
          // ln(10)/ln(2), or about 3.32193, as the exponents get higher and
          // higher.) If it's 3321 to 3322, the two numbers being compared may or may
          // not be equal. This check assumes that both exponents are less than
          // -1000, when the ratio between exponents of equal values is
          // close to ln(10)/ln(2).
          EInteger ratio;
          EInteger adjexp;
          EInteger divisor1 = thisAdjExpBounds[0].Add(1).Abs();
          EInteger divisor2 = thisAdjExpBounds[1].Add(1).Abs();
          otherAdjExp = otherAdjExp.Add(1).Abs();
          adjexp = EInteger.Min(divisor1, divisor2);
          ratio = otherAdjExp.Multiply(1000).Divide(adjexp);
          // System.out.println("taexp=" + adjexp + ", oaexp=" +
          // otherAdjExp + " ratio=" + ratio);
          if (ratio.compareTo(3321) < 0) {
            // Binary abs. value is greater
            return (signA > 0) ? -1 : 1;
          }
          adjexp = EInteger.Max(divisor1, divisor2);
          ratio = otherAdjExp.Multiply(1000).Divide(adjexp);
          if (ratio.compareTo(3322) > 0) {
            return (signA > 0) ? 1 : -1;
          }
        }
      }
      if (ef.getExponent().compareTo(1000) > 0) {
        // Very high exponents
        EInteger bignum = EInteger.FromInt32(1).ShiftLeft(999);
        if (ed.Abs(null).CompareToValue(EDecimal.FromEInteger(bignum)) <=
          0) {
          // this object's absolute value is less
          return (signA > 0) ? -1 : 1;
        }
        // NOTE: The following check assumes that both
        // operands are nonzero
        EInteger[] thisAdjExpBounds = GetAdjustedExponentDecimalBounds(ed);
        EInteger otherAdjExp = GetAdjustedExponentBinary(ef);
        if (thisAdjExpBounds[0].signum() > 0 &&
          thisAdjExpBounds[0].compareTo(otherAdjExp) >= 0) {
          // This Object's adjusted exponent lower bound
          // is greater and is positive;
          // so this (decimal) Object's absolute value is greater,
          // since exponents have a greater value in decimal than in binary
          return (signA > 0) ? 1 : -1;
        }
        if (thisAdjExpBounds[1].signum() > 0 &&
          thisAdjExpBounds[1].compareTo(1000) < 0 &&
          otherAdjExp.compareTo(4000) >= 0) {
          // With these exponent combinations (using the adjusted exponent
          // upper bound), the binary's absolute
          // value is greater than the decimal's
          return (signA > 0) ? -1 : 1;
        }
        if (thisAdjExpBounds[0].signum() > 0 &&
          thisAdjExpBounds[0].compareTo(1000) >= 0 &&
          otherAdjExp.compareTo(1000) >= 0) {
          // Check the ratio of the binary exponent to the decimal exponent.
          // If the ratio times 1000, rounded down, is less than 3321, the
          // decimal's absolute value is
          // greater. If it's 3322 or greater, the binary's absolute value is
          // greater.
          // (If the two absolute values are equal, the ratio will approach
          // ln(10)/ln(2), or about 3.32193, as the exponents get higher and
          // higher.) This check assumes that both exponents are 1000 or
          // greater, when the ratio between exponents of equal values is
          // close to ln(10)/ln(2).
          EInteger ratio;
          EInteger adjexp;
          EInteger divisor1 = thisAdjExpBounds[0].Add(1);
          EInteger divisor2 = thisAdjExpBounds[1].Add(1);
          otherAdjExp = otherAdjExp.Add(1);
          adjexp = EInteger.Min(divisor1, divisor2);
          ratio = otherAdjExp.Multiply(1000).Divide(adjexp);
          if (ratio.compareTo(3321) < 0) {
            // Decimal abs. value is greater
            return (signA > 0) ? 1 : -1;
          }
          adjexp = EInteger.Max(divisor1, divisor2);
          ratio = otherAdjExp.Multiply(1000).Divide(adjexp);
          if (ratio.compareTo(3322) >= 0) {
            return (signA > 0) ? -1 : 1;
          }
        }
      }
      EDecimal otherDec = EDecimal.FromEFloat(ef);
      /* System.out.println("Traditional compare");
      System.out.println("ef="+ef);
      String ed0=""+ed;
      String od0=""+otherDec;
      if (!ed0.equals(od0)) {
        System.out.println("ed="+ed0);
        System.out.println("od="+od0);
      }
      */
      return ed.compareTo(otherDec);
    }

    public EDecimal CompareToSignal(
      EDecimal other,
      EContext ctx) {
      return GetMathValue(ctx).CompareToWithContext(this, other, true, ctx);
    }

    public int CompareToTotalMagnitude(EDecimal other) {
      if (other == null) {
        return 1;
      }
      int valueIThis = 0;
      int valueIOther = 0;
      int cmp;
      if (this.IsSignalingNaN()) {
        valueIThis = 2;
      } else if (this.IsNaN()) {
        valueIThis = 3;
      } else if (this.IsInfinity()) {
        valueIThis = 1;
      }
      if (other.IsSignalingNaN()) {
        valueIOther = 2;
      } else if (other.IsNaN()) {
        valueIOther = 3;
      } else if (other.IsInfinity()) {
        valueIOther = 1;
      }
      if (valueIThis > valueIOther) {
        return 1;
      } else if (valueIThis < valueIOther) {
        return -1;
      }
      if (valueIThis >= 2) {
        cmp = this.unsignedMantissa.compareTo(
            other.unsignedMantissa);
        return cmp;
      } else if (valueIThis == 1) {
        return 0;
      } else {
        cmp = this.Abs().compareTo(other.Abs());
        if (cmp == 0) {
          cmp = this.exponent.compareTo(
              other.exponent);
          return cmp;
        }
        return cmp;
      }
    }

    public int CompareToTotal(EDecimal other, EContext ctx) {
      if (other == null) {
        return 1;
      }
      if (this.IsSignalingNaN() || other.IsSignalingNaN()) {
        return this.CompareToTotal(other);
      }
      if (ctx != null && ctx.isSimplified()) {
        return this.RoundToPrecision(ctx)
          .CompareToTotal(other.RoundToPrecision(ctx));
      } else {
        return this.CompareToTotal(other);
      }
    }

    public int CompareToTotalMagnitude(EDecimal other, EContext ctx) {
      if (other == null) {
        return 1;
      }
      if (this.IsSignalingNaN() || other.IsSignalingNaN()) {
        return this.CompareToTotalMagnitude(other);
      }
      if (ctx != null && ctx.isSimplified()) {
        return this.RoundToPrecision(ctx)
          .CompareToTotalMagnitude(other.RoundToPrecision(ctx));
      } else {
        return this.CompareToTotalMagnitude(other);
      }
    }

    public int CompareToTotal(EDecimal other) {
      if (other == null) {
        return -1;
      }
      boolean neg1 = this.isNegative();
      boolean neg2 = other.isNegative();
      if (neg1 != neg2) {
        return neg1 ? -1 : 1;
      }
      int valueIThis = 0;
      int valueIOther = 0;
      int cmp;
      if (this.IsSignalingNaN()) {
        valueIThis = 2;
      } else if (this.IsNaN()) {
        valueIThis = 3;
      } else if (this.IsInfinity()) {
        valueIThis = 1;
      }
      if (other.IsSignalingNaN()) {
        valueIOther = 2;
      } else if (other.IsNaN()) {
        valueIOther = 3;
      } else if (other.IsInfinity()) {
        valueIOther = 1;
      }
      if (valueIThis > valueIOther) {
        return neg1 ? -1 : 1;
      } else if (valueIThis < valueIOther) {
        return neg1 ? 1 : -1;
      }
      if (valueIThis >= 2) {
        cmp = this.unsignedMantissa.compareTo(
            other.unsignedMantissa);
        return neg1 ? -cmp : cmp;
      } else if (valueIThis == 1) {
        return 0;
      } else {
        cmp = this.compareTo(other);
        if (cmp == 0) {
          cmp = this.exponent.compareTo(
              other.exponent);
          return neg1 ? -cmp : cmp;
        }
        return cmp;
      }
    }

    public EDecimal CompareToWithContext(
      EDecimal other,
      EContext ctx) {
      return GetMathValue(ctx).CompareToWithContext(this, other, false, ctx);
    }

    public EDecimal Divide(EDecimal divisor) {
      return this.Divide(
          divisor,
          EContext.ForRounding(ERounding.None));
    }

    public EDecimal Divide(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).Divide(this, divisor, ctx);
    }

/**
 * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EDecimal[] DivideAndRemainderNaturalScale(EDecimal
      divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

/**
 * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EDecimal[] DivideAndRemainderNaturalScale(
      EDecimal divisor,
      EContext ctx) {
      return this.DivRemNaturalScale(divisor, ctx);
    }

    public EDecimal[] DivRemNaturalScale(EDecimal
      divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

    public EDecimal[] DivRemNaturalScale(
      EDecimal divisor,
      EContext ctx) {
      EDecimal[] result = new EDecimal[2];
      result[0] = this.DivideToIntegerNaturalScale(divisor, null);
      result[1] = this.Subtract(
          result[0].Multiply(divisor, null),
          ctx);
      result[0] = result[0].RoundToPrecision(ctx);
      return result;
    }

    public EDecimal DivideToExponent(
      EDecimal divisor,
      long desiredExponentSmall,
      EContext ctx) {
      return this.DivideToExponent(
          divisor,
          EInteger.FromInt64(desiredExponentSmall),
          ctx);
    }

    public EDecimal DivideToExponent(
      EDecimal divisor,
      int desiredExponentInt,
      EContext ctx) {
      return this.DivideToExponent(
          divisor,
          EInteger.FromInt32(desiredExponentInt),
          ctx);
    }

    public EDecimal DivideToExponent(
      EDecimal divisor,
      long desiredExponentSmall,
      ERounding rounding) {
      return this.DivideToExponent(
          divisor,
          EInteger.FromInt64(desiredExponentSmall),
          EContext.ForRounding(rounding));
    }

    public EDecimal DivideToExponent(
      EDecimal divisor,
      int desiredExponentInt,
      ERounding rounding) {
      return this.DivideToExponent(
          divisor,
          EInteger.FromInt32(desiredExponentInt),
          EContext.ForRounding(rounding));
    }

    public EDecimal DivideToExponent(
      EDecimal divisor,
      EInteger exponent,
      EContext ctx) {
      return GetMathValue(ctx).DivideToExponent(this, divisor, exponent, ctx);
    }

    public EDecimal DivideToExponent(
      EDecimal divisor,
      EInteger exponent) {
      return this.DivideToExponent(divisor, exponent, ERounding.HalfEven);
    }

    public EDecimal DivideToExponent(
      EDecimal divisor,
      long desiredExponentSmall) {
      return this.DivideToExponent(
          divisor,
          desiredExponentSmall,
          ERounding.HalfEven);
    }

    public EDecimal DivideToExponent(
      EDecimal divisor,
      int desiredExponentInt) {
      return this.DivideToExponent(
          divisor,
          desiredExponentInt,
          ERounding.HalfEven);
    }

    public EDecimal DivideToExponent(
      EDecimal divisor,
      EInteger desiredExponent,
      ERounding rounding) {
      return this.DivideToExponent(
          divisor,
          desiredExponent,
          EContext.ForRounding(rounding));
    }

    public EDecimal DivideToIntegerNaturalScale(EDecimal
      divisor) {
      return this.DivideToIntegerNaturalScale(
          divisor,
          EContext.ForRounding(ERounding.Down));
    }

    public EDecimal DivideToIntegerNaturalScale(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).DivideToIntegerNaturalScale(
          this,
          divisor,
          ctx);
    }

    public EDecimal DivideToIntegerZeroScale(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).DivideToIntegerZeroScale(this, divisor, ctx);
    }

    public EDecimal DivideToSameExponent(
      EDecimal divisor,
      ERounding rounding) {
      return this.DivideToExponent(
          divisor,
          this.exponent.ToEInteger(),
          EContext.ForRounding(rounding));
    }

    public boolean equals(EDecimal other) {
      return this.EqualsInternal(other);
    }

    @Override public boolean equals(Object obj) {
      return this.EqualsInternal(((obj instanceof EDecimal) ? (EDecimal)obj : null));
    }

    public EDecimal Exp(EContext ctx) {
      return GetMathValue(ctx).Exp(this, ctx);
    }

    public EDecimal ExpM1(EContext ctx) {
      EDecimal value = this;
      if (value.IsNaN()) {
        return value.Plus(ctx);
      }
      if (ctx == null || !ctx.getHasMaxPrecision()) {
        return EDecimal.SignalingNaN.Plus(ctx);
      }
      if (ctx.getTraps() != 0) {
        EContext tctx = ctx.GetNontrapping();
        EDecimal ret = value.ExpM1(tctx);
        return ctx.TriggerTraps(ret, tctx);
      } else if (ctx.isSimplified()) {
        EContext tmpctx = ctx.WithSimplified(false).WithBlankFlags();
        EDecimal ret = value.PreRound(ctx).ExpM1(tmpctx);
        if (ctx.getHasFlags()) {
          int flags = ctx.getFlags();
          ctx.setFlags(flags | tmpctx.getFlags());
        }
        // System.out.println("{0} {1} [{4} {5}] -> {2}
        // [{3}]",value,baseValue,ret,ret.RoundToPrecision(ctx),
        // value.Quantize(value, ctx), baseValue.Quantize(baseValue, ctx));
        return ret.RoundToPrecision(ctx);
      } else {
        if (value.compareTo(-1) == 0) {
          return EDecimal.NegativeInfinity;
        } else if (value.IsPositiveInfinity()) {
          return EDecimal.PositiveInfinity;
        } else if (value.IsNegativeInfinity()) {
          return EDecimal.FromInt32(-1).Plus(ctx);
        } else if (value.compareTo(0) == 0) {
          return EDecimal.FromInt32(0).Plus(ctx);
        }
        int flags = ctx.getFlags();
        EContext tmpctx = null;
        EDecimal ret;
        {
          EInteger prec = ctx.getPrecision().Add(3);
          tmpctx = ctx.WithBigPrecision(prec).WithBlankFlags();
          if (value.Abs().compareTo(EDecimal.Create(5, -1)) < 0) {
            ret = value.Exp(tmpctx).Add(EDecimal.FromInt32(-1), ctx);
            EDecimal oldret = ret;
            while (true) {
              prec = prec.Add(ctx.getPrecision()).Add(3);
              tmpctx = ctx.WithBigPrecision(prec).WithBlankFlags();
              ret = value.Exp(tmpctx).Add(EDecimal.FromInt32(-1), ctx);
              if (ret.compareTo(0) != 0 && ret.compareTo(oldret) == 0) {
                break;
              }
              oldret = ret;
            }
          } else {
            ret = value.Exp(tmpctx).Add(EDecimal.FromInt32(-1), ctx);
          }
          flags |= tmpctx.getFlags();
        }
        if (ctx.getHasFlags()) {
          flags |= ctx.getFlags();
          ctx.setFlags(flags);
        }
        return ret;
      }
    }

    @Override public int hashCode() {
      int valueHashCode = 964453631;
      {
        valueHashCode += 964453723 * this.exponent.hashCode();
        valueHashCode += 964453939 * this.unsignedMantissa.hashCode();
        valueHashCode += 964453967 * this.flags;
      }
      return valueHashCode;
    }

    public boolean IsInfinity() {
      return (this.flags & BigNumberFlags.FlagInfinity) != 0;
    }

    public boolean IsNaN() {
      return (this.flags & (BigNumberFlags.FlagQuietNaN |
            BigNumberFlags.FlagSignalingNaN)) != 0;
    }

    public boolean IsNegativeInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
            BigNumberFlags.FlagNegative)) == (BigNumberFlags.FlagInfinity |
          BigNumberFlags.FlagNegative);
    }

    public boolean IsPositiveInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
            BigNumberFlags.FlagNegative)) == BigNumberFlags.FlagInfinity;
    }

    public boolean IsQuietNaN() {
      return (this.flags & BigNumberFlags.FlagQuietNaN) != 0;
    }

    public boolean IsSignalingNaN() {
      return (this.flags & BigNumberFlags.FlagSignalingNaN) != 0;
    }

    public EDecimal Log(EContext ctx) {
      return GetMathValue(ctx).Ln(this, ctx);
    }

    public EDecimal Log10(EContext ctx) {
      return this.LogN(EDecimal.FromInt32(10), ctx);
    }

    public EDecimal Log1P(EContext ctx) {
      EDecimal value = this;
      if (value.IsNaN()) {
        return value.Plus(ctx);
      }
      if (ctx == null || !ctx.getHasMaxPrecision() ||
        (value.compareTo(-1) < 0)) {
        return EDecimal.SignalingNaN.Plus(ctx);
      }
      if (ctx.getTraps() != 0) {
        EContext tctx = ctx.GetNontrapping();
        EDecimal ret = value.Log1P(tctx);
        return ctx.TriggerTraps(ret, tctx);
      } else if (ctx.isSimplified()) {
        EContext tmpctx = ctx.WithSimplified(false).WithBlankFlags();
        EDecimal ret = value.PreRound(ctx).Log1P(tmpctx);
        if (ctx.getHasFlags()) {
          int flags = ctx.getFlags();
          ctx.setFlags(flags | tmpctx.getFlags());
        }
        // System.out.println("{0} {1} [{4} {5}] -> {2}
        // [{3}]",value,baseValue,ret,ret.RoundToPrecision(ctx),
        // value.Quantize(value, ctx), baseValue.Quantize(baseValue, ctx));
        return ret.RoundToPrecision(ctx);
      } else {
        if (value.compareTo(-1) == 0) {
          return EDecimal.NegativeInfinity;
        } else if (value.IsPositiveInfinity()) {
          return EDecimal.PositiveInfinity;
        }
        if (value.compareTo(0) == 0) {
          return EDecimal.FromInt32(0).Plus(ctx);
        }
        int flags = ctx.getFlags();
        EContext tmpctx = null;
        EDecimal ret;
        // System.out.println("cmp=" +
        // value.compareTo(EDecimal.Create(1, -1)) +
        // " add=" + value.Add(EDecimal.FromInt32(1)));
        if (value.compareTo(EDecimal.Create(5, -1)) < 0) {
          ret = value.Add(EDecimal.FromInt32(1)).Log(ctx);
        } else {
          tmpctx = ctx.WithBigPrecision(ctx.getPrecision().Add(3)).WithBlankFlags();
          ret = value.Add(EDecimal.FromInt32(1), tmpctx).Log(ctx);
          flags |= tmpctx.getFlags();
        }
        if (ctx.getHasFlags()) {
          flags |= ctx.getFlags();
          ctx.setFlags(flags);
        }
        return ret;
      }
    }

    public EDecimal LogN(EDecimal baseValue, EContext ctx) {
      EDecimal value = this;
      if (baseValue == null) {
        throw new NullPointerException("baseValue");
      }
      if (value.IsNaN()) {
        return value.Plus(ctx);
      }
      if (baseValue.IsNaN()) {
        return baseValue.Plus(ctx);
      }
      if (ctx == null || !ctx.getHasMaxPrecision() ||
        (value.isNegative() && !value.isZero()) ||
        (baseValue.isNegative() && !baseValue.isZero())) {
        return EDecimal.SignalingNaN.Plus(ctx);
      }
      if (ctx.getTraps() != 0) {
        EContext tctx = ctx.GetNontrapping();
        EDecimal ret = value.LogN(baseValue, tctx);
        return ctx.TriggerTraps(ret, tctx);
      } else if (ctx.isSimplified()) {
        EContext tmpctx = ctx.WithSimplified(false).WithBlankFlags();
        EDecimal ret = value.PreRound(ctx).LogN(baseValue.PreRound(ctx),
            tmpctx);
        if (ctx.getHasFlags()) {
          int flags = ctx.getFlags();
          ctx.setFlags(flags | tmpctx.getFlags());
        }
        // System.out.println("{0} {1} [{4} {5}] -> {2}
        // [{3}]",value,baseValue,ret,ret.RoundToPrecision(ctx),
        // value.Quantize(value, ctx), baseValue.Quantize(baseValue, ctx));
        return ret.RoundToPrecision(ctx);
      } else {
        if (value.isZero()) {
          return baseValue.compareTo(1) < 0 ? EDecimal.PositiveInfinity :
            EDecimal.NegativeInfinity;
        } else if (value.IsPositiveInfinity()) {
          return baseValue.compareTo(1) < 0 ? EDecimal.NegativeInfinity :
            EDecimal.PositiveInfinity;
        }
        if (baseValue.compareTo(10) == 0) {
          EDecimal ev = value.Reduce(null);
          if (ev.getUnsignedMantissa().compareTo(1) == 0) {
            return EDecimal.FromEInteger(ev.getExponent()).Plus(ctx);
          }
        } else if (value.compareTo(1) == 0) {
          return EDecimal.FromInt32(0).Plus(ctx);
        } else if (value.compareTo(baseValue) == 0) {
          return EDecimal.FromInt32(1).Plus(ctx);
        }
        int flags = ctx.getFlags();
        EContext tmpctx =
          ctx.WithBigPrecision(ctx.getPrecision().Add(5)).WithBlankFlags();
        EDecimal ret = value.Log(tmpctx).Divide(baseValue.Log(tmpctx), ctx);
        if (ret.IsInteger() && !ret.isZero()) {
          flags |= EContext.FlagRounded | EContext.FlagInexact;
          if (baseValue.Pow(ret).CompareToValue(value) == 0) {
            EDecimal rtmp = ret.Quantize(EDecimal.FromInt32(1),
                ctx.WithNoFlags());
            if (!rtmp.IsNaN()) {
              flags &= ~(EContext.FlagRounded | EContext.FlagInexact);
              ret = rtmp;
            }
          }
        } else {
          flags |= tmpctx.getFlags();
        }
        if (ctx.getHasFlags()) {
          flags |= ctx.getFlags();
          ctx.setFlags(flags);
        }
        return ret;
      }
    }

    public EDecimal MovePointLeft(int places) {
      return this.MovePointLeft(EInteger.FromInt32(places), null);
    }

    public EDecimal MovePointLeft(int places, EContext ctx) {
      return this.MovePointLeft(EInteger.FromInt32(places), ctx);
    }

    public EDecimal MovePointLeft(EInteger bigPlaces) {
      return this.MovePointLeft(bigPlaces, null);
    }

    public EDecimal MovePointLeft(
      EInteger bigPlaces,
      EContext ctx) {
      return (!this.isFinite()) ? this.RoundToPrecision(ctx) :
        this.MovePointRight((bigPlaces).Negate(), ctx);
    }

    public EDecimal MovePointRight(int places) {
      return this.MovePointRight(EInteger.FromInt32(places), null);
    }

    public EDecimal MovePointRight(int places, EContext ctx) {
      return this.MovePointRight(EInteger.FromInt32(places), ctx);
    }

    public EDecimal MovePointRight(EInteger bigPlaces) {
      return this.MovePointRight(bigPlaces, null);
    }

    public EDecimal MovePointRight(
      EInteger bigPlaces,
      EContext ctx) {
      if (!this.isFinite()) {
        return this.RoundToPrecision(ctx);
      }
      EInteger bigExp = this.getExponent();
      bigExp = bigExp.Add(bigPlaces);
      if (bigExp.signum() > 0) {
        EInteger mant = this.unsignedMantissa.ToEInteger();
        EInteger bigPower = NumberUtility.FindPowerOfTenFromBig(bigExp);
        mant = mant.Multiply(bigPower);
        return CreateWithFlags(
            mant,
            EInteger.FromInt32(0),
            this.flags).RoundToPrecision(ctx);
      }
      return CreateWithFlags(
          this.unsignedMantissa,
          FastIntegerFixed.FromBig(bigExp),
          this.flags).RoundToPrecision(ctx);
    }

    public EDecimal Multiply(EDecimal otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.isFinite() && otherValue.isFinite()) {
        int newflags = otherValue.flags ^ this.flags;
        if (this.unsignedMantissa.CanFitInInt32() &&
          otherValue.unsignedMantissa.CanFitInInt32()) {
          int integerA = this.unsignedMantissa.ToInt32();
          int integerB = otherValue.unsignedMantissa.ToInt32();
          long longA = ((long)integerA) * ((long)integerB);
          FastIntegerFixed exp = FastIntegerFixed.Add(
              this.exponent,
              otherValue.exponent);
          if ((longA >> 31) == 0) {
            return new EDecimal(
                FastIntegerFixed.FromInt32((int)longA),
                exp,
                (byte)newflags);
          } else {
            return new EDecimal(
                FastIntegerFixed.FromBig(EInteger.FromInt64(longA)),
                exp,
                (byte)newflags);
          }
        } else {
          EInteger eintA = this.unsignedMantissa.ToEInteger().Multiply(
              otherValue.unsignedMantissa.ToEInteger());
          return new EDecimal(
              FastIntegerFixed.FromBig(eintA),
              FastIntegerFixed.Add(this.exponent, otherValue.exponent),
              (byte)newflags);
        }
      }
      return this.Multiply(otherValue, EContext.UnlimitedHalfEven);
    }

    public EDecimal Multiply(EDecimal op, EContext ctx) {
      return GetMathValue(ctx).Multiply(this, op, ctx);
    }

    public EDecimal Add(long longValue) {
      return this.Add(EDecimal.FromInt64(longValue));
    }

    public EDecimal Subtract(long longValue) {
      return this.Subtract(EDecimal.FromInt64(longValue));
    }

    public EDecimal Multiply(long longValue) {
      return this.Multiply(EDecimal.FromInt64(longValue));
    }

    public EDecimal Divide(long longValue) {
      return this.Divide(EDecimal.FromInt64(longValue));
    }

    public EDecimal Add(int intValue) {
      return this.Add(EDecimal.FromInt32(intValue));
    }

    public EDecimal Subtract(int intValue) {
      return (intValue == Integer.MIN_VALUE) ?
        this.Subtract(EDecimal.FromInt32(intValue)) : this.Add(-intValue);
    }

    public EDecimal Multiply(int intValue) {
      return this.Multiply(EDecimal.FromInt32(intValue));
    }

    public EDecimal Divide(int intValue) {
      return this.Divide(EDecimal.FromInt32(intValue));
    }

    public EDecimal MultiplyAndAdd(
      EDecimal multiplicand,
      EDecimal augend) {
      return this.MultiplyAndAdd(multiplicand, augend, null);
    }

    public EDecimal MultiplyAndAdd(
      EDecimal op,
      EDecimal augend,
      EContext ctx) {
      return GetMathValue(ctx).MultiplyAndAdd(this, op, augend, ctx);
    }

    public EDecimal MultiplyAndSubtract(
      EDecimal op,
      EDecimal subtrahend,
      EContext ctx) {
      if (op == null) {
        throw new NullPointerException("op");
      }
      if (subtrahend == null) {
        throw new NullPointerException("subtrahend");
      }
      EDecimal negated = subtrahend;
      if ((subtrahend.flags & BigNumberFlags.FlagNaN) == 0) {
        int newflags = subtrahend.flags ^ BigNumberFlags.FlagNegative;
        negated = CreateWithFlags(
            subtrahend.unsignedMantissa,
            subtrahend.exponent,
            newflags);
      }
      return GetMathValue(ctx)
        .MultiplyAndAdd(this, op, negated, ctx);
    }

    public EDecimal Negate() {
      return new EDecimal(
          this.unsignedMantissa,
          this.exponent,
          (byte)(this.flags ^ BigNumberFlags.FlagNegative));
    }

    public EDecimal Negate(EContext context) {
      return ((context == null || context == EContext.UnlimitedHalfEven) ?
          ExtendedMathValue : MathValue).Negate(this, context);
    }

    public EDecimal NextMinus(EContext ctx) {
      return GetMathValue(ctx).NextMinus(this, ctx);
    }

    public EDecimal NextPlus(EContext ctx) {
      return GetMathValue(ctx).NextPlus(this, ctx);
    }

    public EDecimal NextToward(
      EDecimal otherValue,
      EContext ctx) {
      return GetMathValue(ctx)
        .NextToward(this, otherValue, ctx);
    }

    public EDecimal Plus(EContext ctx) {
      return GetMathValue(ctx).Plus(this, ctx);
    }

    public EDecimal Pow(EDecimal exponent, EContext ctx) {
      return GetMathValue(ctx).Power(this, exponent, ctx);
    }

    public EDecimal Pow(EDecimal exponent) {
      return this.Pow(exponent, null);
    }

    public EDecimal Pow(int exponentSmall, EContext ctx) {
      return this.Pow(EDecimal.FromInt64(exponentSmall), ctx);
    }

    public EDecimal Pow(int exponentSmall) {
      return this.Pow(EDecimal.FromInt64(exponentSmall), null);
    }

    public EInteger Precision() {
      if (!this.isFinite()) {
        return EInteger.FromInt32(0);
      }
      return this.isZero() ? EInteger.FromInt32(1) :
        this.unsignedMantissa.ToEInteger().GetDigitCountAsEInteger();
    }

    public EDecimal Quantize(
      EInteger desiredExponent,
      EContext ctx) {
      return this.Quantize(
          EDecimal.Create(EInteger.FromInt32(1), desiredExponent),
          ctx);
    }

    public EDecimal Quantize(
      int desiredExponentInt,
      ERounding rounding) {
      EDecimal ret = this.RoundToExponentFast(
          desiredExponentInt,
          rounding);
      if (ret != null) {
        return ret;
      }
      return this.Quantize(
          EDecimal.Create(EInteger.FromInt32(1), EInteger.FromInt32(desiredExponentInt)),
          EContext.ForRounding(rounding));
    }

    public EDecimal Quantize(
      int desiredExponentInt,
      EContext ctx) {
      if (ctx == null ||
        (!ctx.getHasExponentRange() && !ctx.getHasFlagsOrTraps() &&
          !ctx.getHasMaxPrecision() && !ctx.isSimplified())) {
        EDecimal ret = this.RoundToExponentFast(
            desiredExponentInt,
            ctx == null ? ERounding.HalfEven : ctx.getRounding());
        if (ret != null) {
          return ret;
        }
      }
      return this.Quantize(
          EDecimal.Create(EInteger.FromInt32(1), EInteger.FromInt32(desiredExponentInt)),
          ctx);
    }

    public EDecimal Quantize(
      EDecimal otherValue,
      EContext ctx) {
      return GetMathValue(ctx).Quantize(this, otherValue, ctx);
    }

    public EDecimal Reduce(EContext ctx) {
      return GetMathValue(ctx).Reduce(this, ctx);
    }

    public EDecimal Remainder(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).Remainder(this, divisor, ctx, true);
    }

    public EDecimal RemainderNoRoundAfterDivide(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).Remainder(this, divisor, ctx, false);
    }

    public EDecimal RemainderNaturalScale(EDecimal divisor) {
      return this.RemainderNaturalScale(divisor, null);
    }

    public EDecimal RemainderNaturalScale(
      EDecimal divisor,
      EContext ctx) {
      return this.Subtract(
        this.DivideToIntegerNaturalScale(divisor, null).Multiply(divisor, null),
        ctx);
    }

    public EDecimal RemainderNear(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx)
        .RemainderNear(this, divisor, ctx);
    }

    public EDecimal RoundToExponent(
      EInteger exponent,
      EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentSimple(this, exponent, ctx);
    }

    public EDecimal RoundToExponent(
      EInteger exponent) {
      return this.RoundToExponent(
          exponent,
          EContext.ForRounding(ERounding.HalfEven));
    }

    public EDecimal RoundToExponent(
      EInteger exponent,
      ERounding rounding) {
      return this.RoundToExponent(
          exponent,
          EContext.ForRounding(rounding));
    }

    public EDecimal RoundToExponent(
      int exponentSmall) {
      return this.RoundToExponent(exponentSmall, ERounding.HalfEven);
    }

    public EDecimal RoundToExponent(
      int exponentSmall,
      EContext ctx) {
      if (ctx == null ||
        (!ctx.getHasExponentRange() && !ctx.getHasFlagsOrTraps() &&
          !ctx.getHasMaxPrecision() && !ctx.isSimplified())) {
        EDecimal ret = this.RoundToExponentFast(
            exponentSmall,
            ctx == null ? ERounding.HalfEven : ctx.getRounding());
        if (ret != null) {
          return ret;
        }
      }
      return this.RoundToExponent(EInteger.FromInt32(exponentSmall), ctx);
    }

    public EDecimal RoundToExponent(
      int exponentSmall,
      ERounding rounding) {
      EDecimal ret = this.RoundToExponentFast(
          exponentSmall,
          rounding);
      if (ret != null) {
        return ret;
      }
      return this.RoundToExponent(
          exponentSmall,
          EContext.ForRounding(rounding));
    }

    public EDecimal RoundToExponentExact(
      EInteger exponent,
      EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentExact(this, exponent, ctx);
    }

    public EDecimal RoundToExponentExact(
      int exponentSmall,
      EContext ctx) {
      return this.RoundToExponentExact(EInteger.FromInt32(exponentSmall), ctx);
    }

    public EDecimal RoundToExponentExact(
      int exponentSmall,
      ERounding rounding) {
      return this.RoundToExponentExact(
          EInteger.FromInt32(exponentSmall),
          EContext.Unlimited.WithRounding(rounding));
    }

    public EDecimal RoundToIntegerExact(EContext ctx) {
      return GetMathValue(ctx).RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    public EDecimal RoundToIntegerNoRoundedFlag(EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

/**
 * @deprecated Renamed to RoundToIntegerExact.
 */
@Deprecated
    public EDecimal RoundToIntegralExact(EContext ctx) {
      return GetMathValue(ctx).RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

/**
 * @deprecated Renamed to RoundToIntegerNoRoundedFlag.
 */
@Deprecated
    public EDecimal RoundToIntegralNoRoundedFlag(EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

    public EDecimal RoundToPrecision(EContext ctx) {
      return GetMathValue(ctx).RoundToPrecision(this, ctx);
    }

    public EDecimal PreRound(EContext ctx) {
      return NumberUtility.PreRound(this, ctx, GetMathValue(ctx));
    }

    public EDecimal ScaleByPowerOfTen(int places) {
      return this.ScaleByPowerOfTen(EInteger.FromInt32(places), null);
    }

    public EDecimal ScaleByPowerOfTen(int places, EContext ctx) {
      return this.ScaleByPowerOfTen(EInteger.FromInt32(places), ctx);
    }

    public EDecimal ScaleByPowerOfTen(EInteger bigPlaces) {
      return this.ScaleByPowerOfTen(bigPlaces, null);
    }

    public EDecimal ScaleByPowerOfTen(
      EInteger bigPlaces,
      EContext ctx) {
      if (bigPlaces == null) {
        throw new NullPointerException("bigPlaces");
      }
      if (bigPlaces.isZero()) {
        return this.RoundToPrecision(ctx);
      }
      if (!this.isFinite()) {
        return this.RoundToPrecision(ctx);
      }
      EInteger bigExp = this.getExponent();
      bigExp = bigExp.Add(bigPlaces);
      return CreateWithFlags(
          this.unsignedMantissa,
          FastIntegerFixed.FromBig(bigExp),
          this.flags).RoundToPrecision(ctx);
    }

    public EDecimal Sqrt(EContext ctx) {
      return GetMathValue(ctx).SquareRoot(this, ctx);
    }

/**
 * @deprecated Renamed to Sqrt.
 */
@Deprecated
    public EDecimal SquareRoot(EContext ctx) {
      return GetMathValue(ctx).SquareRoot(this, ctx);
    }

    public EDecimal Subtract(EDecimal otherValue) {
      return this.Subtract(otherValue, EContext.UnlimitedHalfEven);
    }

    public EDecimal Subtract(
      EDecimal otherValue,
      EContext ctx) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      EDecimal negated = otherValue;
      if ((otherValue.flags & BigNumberFlags.FlagNaN) == 0) {
        int newflags = otherValue.flags ^ BigNumberFlags.FlagNegative;
        negated = CreateWithFlags(
            otherValue.unsignedMantissa,
            otherValue.exponent,
            newflags);
      }
      return this.Add(negated, ctx);
    }

    private static long IntegerToDoubleBits(long v, int expshift, boolean neg) {
      int nexp = expshift;
      while (v < (1L << 52)) {
        v <<= 1;
        --nexp;
      }
      // Clear the high bits where the exponent and sign are
      v &= 0xfffffffffffffL;
      // NOTE: Assumed not to be subnormal
      v |= (long)(nexp + 1075) << 52;
      if (neg) {
        v |= ((long)(1L << 63));
      }
      return v;
    }

    private static int IntegerToSingleBits(int iv, int expshift, boolean neg) {
      int nexp = expshift;
      while (iv < (1 << EFloat.Binary32SignifAreaBits)) {
        iv <<= 1;
        --nexp;
      }
      // Clear the high bits where the exponent and sign are
      iv &= (1 << EFloat.Binary32SignifAreaBits) - 1;
      // NOTE: Assumed not to be subnormal
      iv |= (nexp + 1 - EFloat.Binary32EMin) << EFloat.Binary32SignifAreaBits;
      if (neg) {
        iv |= 1 << (EFloat.Binary32Bits - 1);
      }
      return iv;
    }

    private static short IntegerToHalfBits(int iv, int expshift, boolean neg) {
      int nexp = expshift;
      while (iv < (1 << EFloat.Binary16SignifAreaBits)) {
        iv <<= 1;
        --nexp;
      }
      // Clear the high bits where the exponent and sign are
      iv &= (1 << EFloat.Binary16SignifAreaBits) - 1;
      // NOTE: Assumed not to be subnormal
      iv |= (nexp + 1 - EFloat.Binary16EMin) << EFloat.Binary16SignifAreaBits;
      if (neg) {
        iv |= 1 << (EFloat.Binary16Bits - 1);
      }
      return (short)iv;
    }

    public long ToDoubleBits() {
      if (this.IsPositiveInfinity()) {
        return (long)0x7ff0000000000000L;
      }
      if (this.IsNegativeInfinity()) {
        return (long)0xfff0000000000000L;
      }
      if (this.isNegative() && this.isZero()) {
        return 1L << 63;
      }
      if (this.isZero()) {
        return 0;
      }
      if (this.isFinite()) {
        if (this.exponent.CompareToInt(0) == 0 &&
          this.unsignedMantissa.CanFitInInt64()) {
          long v = this.unsignedMantissa.ToInt64();
          if (v < (1L << 53)) {
            // This integer fits exactly in double
            return IntegerToDoubleBits(v, 0, this.isNegative());
          }
        }
        if (this.exponent.CompareToInt(0) < 0 &&
          this.exponent.CompareToInt(-8) >= 0 &&
          this.unsignedMantissa.CanFitInInt32()) {
          int m = this.unsignedMantissa.ToInt32();
          int iex = -this.exponent.ToInt32();
          int vtp = ValueTenPowers[iex];
          if (m != Integer.MIN_VALUE) {
            if (m % vtp == 0) {
              // Will fit in double without rounding
              // System.out.println("m=" + m + " vtp=" + vtp);
              return IntegerToDoubleBits(m / vtp, 0, this.isNegative());
            }
            // Shift significand to be a 53-bit number (which
            // can fit exactly in a double)
            long am = Math.abs(m);
            int expshift = 0;
            while (am < (1L << 52)) {
              am <<= 1;
              --expshift;
            }
            int divdCount = NumberUtility.BitLength(m);
            int divsCount = NumberUtility.BitLength(vtp);
            int dividendShift = (divdCount <= divsCount) ? ((divsCount -
                  divdCount) + 53 + 1) : Math.max(0,
                (53 + 1) - (divdCount - divsCount));
            long lquo = -1;
            long lrem = -1;
            if (divsCount + dividendShift > 63) {
              EInteger eim = EInteger.FromInt32(m).ShiftLeft(dividendShift);
              EInteger[] divrem3 = eim.DivRem(EInteger.FromInt32(vtp));
              EInteger equo = divrem3[0];
              EInteger erem = divrem3[1];
              if (equo.CanFitInInt64() && erem.CanFitInInt64()) {
                lquo = equo.ToInt64Checked();
                lrem = erem.ToInt64Checked();
              }
            } else {
              long ldividend = ((long)m) << dividendShift;
              lquo = ldividend / vtp;
              lrem = ldividend - (lquo * vtp);
            }
            int nexp = -dividendShift;
            if (lquo >= (1L << 53)) {
              while (lquo >= (1L << 54)) {
                lrem |= lquo & 1L;
                lquo >>= 1;
                ++nexp;
              }
              if ((lquo & 3L) == 3 && lrem == 0) {
                lquo >>= 1;
                ++lquo;
                ++nexp;
              } else if ((lquo & 1L) != 0 && lrem != 0) {
                lquo >>= 1;
                ++lquo;
                ++nexp;
              } else {
                lquo >>= 1;
                ++nexp;
              }
              while (lquo >= (1L << 53)) {
                lquo >>= 1;
                ++nexp;
              }
              // Clear the high bits where the exponent and sign are
              lquo &= 0xfffffffffffffL;
              // NOTE: Assumed not to be subnormal
              lquo |= (long)(nexp + 1075) << 52;
              if (this.isNegative()) {
                lquo |= ((long)(1L << 63));
              }
              return lquo;
            }
          }
        }
        if (this.exponent.CompareToInt(309) > 0) {
          // Very high exponent, treat as infinity
          return this.isNegative() ? ((long)0xfff0000000000000L) :
            0x7ff0000000000000L;
        }
      }
      return this.ToEFloat(EContext.Binary64).ToDoubleBits();
    }

    public double ToDouble() {
      long value = this.ToDoubleBits();
      return Double.longBitsToDouble(value);
    }

    public EInteger ToEInteger() {
      return this.ToEIntegerInternal(false);
    }

/**
 * @deprecated Renamed to ToEIntegerIfExact.
 */
@Deprecated
    public EInteger ToEIntegerExact() {
      return this.ToEIntegerInternal(true);
    }

    public EInteger ToEIntegerIfExact() {
      return this.ToEIntegerInternal(true);
    }

    public String ToEngineeringString() {
      return this.ToStringInternal(1);
    }

/**
 * @deprecated Renamed to ToEFloat.
 */
@Deprecated
    public EFloat ToExtendedFloat() {
      return this.ToEFloat(EContext.UnlimitedHalfEven);
    }

    public EFloat ToEFloat() {
      return this.ToEFloat(EContext.UnlimitedHalfEven);
    }

    public String ToPlainString() {
      return this.ToStringInternal(2);
    }

    public short ToHalfBits() {
      if (this.IsPositiveInfinity()) {
        return (short)EFloat.Binary16Infinity;
      }
      if (this.IsNegativeInfinity()) {
        return ((short)(EFloat.Binary16Infinity +
(1 << (EFloat.Binary16Bits - 1))));
      }
      if (this.isNegative() && this.isZero()) {
        return (short)((int)1 << (EFloat.Binary16Bits - 1));
      }
      if (this.isZero()) {
        return 0;
      }
      if (this.isFinite()) {
        if (this.exponent.CompareToInt(0) == 0 &&
          this.unsignedMantissa.CanFitInInt32()) {
          int v = this.unsignedMantissa.ToInt32();
          if (v < (1 << EFloat.Binary16SignifBits)) {
            // This integer fits exactly in float
            return IntegerToHalfBits(v, 0, this.isNegative());
          }
        }
        if (this.exponent.CompareToInt(0) < 0 &&
          this.exponent.CompareToInt(-6) >= 0 &&
          this.unsignedMantissa.CanFitInInt32()) {
          return this.ToEFloat(EContext.Binary16).ToHalfBits();
          // TODO: Find out what's wrong with the code commented out below
          /* int m = this.unsignedMantissa.ToInt32();
          int iex = -this.exponent.ToInt32();
          int vtp = ValueTenPowers[iex];
          if (m >= -(1 << EFloat.Binary16SignifAreaBits) && m < (1 <<
EFloat.Binary16SignifAreaBits)) {
            if (m % vtp == 0) {
              return IntegerToHalfBits(m / vtp, 0, this.isNegative());
            }
            // Shift significand to be a number which
            // can fit exactly in a single
            long am = Math.abs(m);
            while (am < (1 << EFloat.Binary16SignifAreaBits)) {
              am <<= 1;
            }
            int divdCount = NumberUtility.BitLength(m);
            int divsCount = NumberUtility.BitLength(vtp);
            int dividendShift = (divdCount <= divsCount) ? ((divsCount -
                  divdCount) + EFloat.Binary16SignifBits + 1) : Math.max(0,
                (EFloat.Binary16SignifBits + 1) - (divdCount - divsCount));
            long lquo = -1;
            long lrem = -1;
            if (divsCount + dividendShift > 63) {
              EInteger eim = EInteger.FromInt32(m).ShiftLeft(dividendShift);
              EInteger[] divrem3 = eim.DivRem(EInteger.FromInt32(vtp));
              EInteger equo = divrem3[0];
              EInteger erem = divrem3[1];
              if (equo.CanFitInInt64() && erem.CanFitInInt64()) {
                lquo = equo.ToInt64Checked();
                lrem = erem.ToInt64Checked();
              }
            } else {
              long ldividend = ((long)m) << dividendShift;
              lquo = ldividend / vtp;
              lrem = ldividend - (lquo * vtp);
            }
            int nexp = -dividendShift;
            if (lquo >= (1L << EFloat.Binary16SignifBits)) {
              while (lquo >= (1L << (EFloat.Binary16SignifBits + 1))) {
                lrem |= lquo & 1L;
                lquo >>= 1;
                ++nexp;
              }
              if ((lquo & 3L) == 3 && lrem == 0) {
                lquo >>= 1;
                ++lquo;
                ++nexp;
              } else if ((lquo & 1L) != 0 && lrem != 0) {
                lquo >>= 1;
                ++lquo;
                ++nexp;
              } else {
                lquo >>= 1;
                ++nexp;
              }
              while (lquo >= (1L << EFloat.Binary16SignifBits)) {
                lquo >>= 1;
                ++nexp;
              }
              int smallmantissa = (int)(lquo &
((1 << EFloat.Binary16SignifAreaBits) - 1));
              // NOTE: Assumed not to be subnormal
              smallmantissa |= (nexp + 1 - EFloat.Binary16EMin) <<
EFloat.Binary16SignifAreaBits;
              if (this.isNegative()) {
                smallmantissa |= 1 << (EFloat.Binary16Bits - 1);
              }
              return (short)smallmantissa;
            }
          }
          */ }
        if (this.exponent.CompareToInt(39) > 0) {
          // Very high exponent, treat as infinity
          return this.isNegative() ? ((short)(EFloat.Binary16Infinity +
(1 << (EFloat.Binary16Bits - 1)))) :
            (short)EFloat.Binary16Infinity;
        }
      }
      return this.ToEFloat(EContext.Binary16).ToHalfBits();
    }

    public int ToSingleBits() {
      if (this.IsPositiveInfinity()) {
        return EFloat.Binary32Infinity;
      }
      if (this.IsNegativeInfinity()) {
        return ((int)(EFloat.Binary32Infinity +
(1 << (EFloat.Binary32Bits - 1))));
      }
      if (this.isNegative() && this.isZero()) {
        return (int)1 << (EFloat.Binary32Bits - 1);
      }
      if (this.isZero()) {
        return 0;
      }
      if (this.isFinite()) {
        if (this.exponent.CompareToInt(0) == 0 &&
          this.unsignedMantissa.CanFitInInt32()) {
          int v = this.unsignedMantissa.ToInt32();
          if (v < (1 << EFloat.Binary32SignifBits)) {
            // This integer fits exactly in float
            return IntegerToSingleBits(v, 0, this.isNegative());
          }
        }
        if (this.exponent.CompareToInt(0) < 0 &&
          this.exponent.CompareToInt(-6) >= 0 &&
          this.unsignedMantissa.CanFitInInt32()) {
          int m = this.unsignedMantissa.ToInt32();
          int iex = -this.exponent.ToInt32();
          int vtp = ValueTenPowers[iex];
          if (m >= -(1 << EFloat.Binary32SignifAreaBits) && m < (1 <<
EFloat.Binary32SignifAreaBits)) {
            if (m % vtp == 0) {
              return IntegerToSingleBits(m / vtp, 0, this.isNegative());
            }
            // Shift significand to be a number which
            // can fit exactly in a single
            long am = Math.abs(m);
            while (am < (1 << EFloat.Binary32SignifAreaBits)) {
              am <<= 1;
            }
            int divdCount = NumberUtility.BitLength(m);
            int divsCount = NumberUtility.BitLength(vtp);
            int dividendShift = (divdCount <= divsCount) ? ((divsCount -
                  divdCount) + EFloat.Binary32SignifBits + 1) : Math.max(0,
                (EFloat.Binary32SignifBits + 1) - (divdCount - divsCount));
            long lquo = -1;
            long lrem = -1;
            if (divsCount + dividendShift > 63) {
              EInteger eim = EInteger.FromInt32(m).ShiftLeft(dividendShift);
              EInteger[] divrem3 = eim.DivRem(EInteger.FromInt32(vtp));
              EInteger equo = divrem3[0];
              EInteger erem = divrem3[1];
              if (equo.CanFitInInt64() && erem.CanFitInInt64()) {
                lquo = equo.ToInt64Checked();
                lrem = erem.ToInt64Checked();
              }
            } else {
              long ldividend = ((long)m) << dividendShift;
              lquo = ldividend / vtp;
              lrem = ldividend - (lquo * vtp);
            }
            int nexp = -dividendShift;
            if (lquo >= (1L << EFloat.Binary32SignifBits)) {
              while (lquo >= (1L << (EFloat.Binary32SignifBits + 1))) {
                lrem |= lquo & 1L;
                lquo >>= 1;
                ++nexp;
              }
              if ((lquo & 3L) == 3 && lrem == 0) {
                lquo >>= 1;
                ++lquo;
                ++nexp;
              } else if ((lquo & 1L) != 0 && lrem != 0) {
                lquo >>= 1;
                ++lquo;
                ++nexp;
              } else {
                lquo >>= 1;
                ++nexp;
              }
              while (lquo >= (1L << EFloat.Binary32SignifBits)) {
                lquo >>= 1;
                ++nexp;
              }
              int smallmantissa = (int)(lquo &
((1 << EFloat.Binary32SignifAreaBits) - 1));
              // NOTE: Assumed not to be subnormal
              smallmantissa |= (nexp + 1 - EFloat.Binary32EMin) <<
EFloat.Binary32SignifAreaBits;
              if (this.isNegative()) {
                smallmantissa |= 1 << (EFloat.Binary32Bits - 1);
              }
              return smallmantissa;
            }
          }
        }
        if (this.exponent.CompareToInt(39) > 0) {
          // Very high exponent, treat as infinity
          return this.isNegative() ? ((int)(EFloat.Binary32Infinity +
(1 << (EFloat.Binary32Bits - 1)))) :
            EFloat.Binary32Infinity;
        }
      }
      return this.ToEFloat(EContext.Binary32).ToSingleBits();
    }

    public float ToSingle() {
      int sb = this.ToSingleBits();
      return Float.intBitsToFloat(sb);
    }

    @Override public String toString() {
      return this.ToStringInternal(0);
    }

    public EDecimal Ulp() {
      return (!this.isFinite()) ? EDecimal.One :
        EDecimal.Create(EInteger.FromInt32(1), this.getExponent());
    }

    static EDecimal CreateWithFlags(
      FastIntegerFixed mantissa,
      FastIntegerFixed exponent,
      int flags) {
      if (mantissa == null) {
        throw new NullPointerException("mantissa");
      }
      if (exponent == null) {
        throw new NullPointerException("exponent");
      }

      return new EDecimal(
          mantissa,
          exponent,
          (byte)flags);
    }

    static EDecimal CreateWithFlags(
      EInteger mantissa,
      EInteger exponent,
      int flags) {
      if (mantissa == null) {
        throw new NullPointerException("mantissa");
      }
      if (exponent == null) {
        throw new NullPointerException("exponent");
      }

      return new EDecimal(
          FastIntegerFixed.FromBig(mantissa),
          FastIntegerFixed.FromBig(exponent),
          (byte)flags);
    }
    private static boolean AppendString(
      StringBuilder builder,
      char c,
      FastInteger count) {
      if (count.CompareToInt(Integer.MAX_VALUE) > 0 || count.signum() < 0) {
        throw new UnsupportedOperationException();
      }
      int icount = count.ToInt32();
      if (icount > RepeatDivideThreshold) {
        StringBuilder sb2 = new StringBuilder(RepeatDivideThreshold);
        for (int i = 0; i < RepeatDivideThreshold; ++i) {
          builder.append(c);
        }
        String sb2str = sb2.toString();
        int rem, count2;
        count2 = icount / RepeatDivideThreshold;
        rem = icount % RepeatDivideThreshold;
        for (int i = 0; i < count2; ++i) {
          builder.append(sb2str);
        }
        for (int i = 0; i < rem; ++i) {
          builder.append(c);
        }
      } else {
        for (int i = 0; i < icount; ++i) {
          builder.append(c);
        }
      }
      return true;
    }

    static IRadixMath<EDecimal> GetMathValue(EContext ctx) {
      if (ctx == null || ctx == EContext.UnlimitedHalfEven) {
        return ExtendedMathValue;
      }
      return (!ctx.isSimplified() && ctx.getTraps() == 0) ? ExtendedMathValue :
        MathValue;
    }

    private boolean EqualsInternal(EDecimal otherValue) {
      return (otherValue != null) && (this.flags == otherValue.flags &&
          this.unsignedMantissa.equals(otherValue.unsignedMantissa) &&
          this.exponent.equals(otherValue.exponent));
    }

    private static EInteger[] GetAdjustedExponentDecimalBounds(
      EDecimal ed) {
      if (!ed.isFinite()) {
        return new EInteger[] { EInteger.FromInt32(0), EInteger.FromInt32(0) };
      }
      if (ed.isZero()) {
        return new EInteger[] { EInteger.FromInt32(0), EInteger.FromInt32(0) };
      }
      EInteger retEInt = ed.getExponent();
      EInteger[] sigPrecBounds = NumberUtility.DecimalDigitLengthBoundsAsEI(
          ed.getUnsignedMantissa());
      EInteger eia = retEInt.Add(sigPrecBounds[0].Subtract(1));
      EInteger eib = retEInt.Add(sigPrecBounds[1].Subtract(1));
      return new EInteger[] {
        EInteger.Min(eia, eib),
        EInteger.Max(eia, eib),
      };
    }

    private static EInteger GetAdjustedExponentBinary(EFloat ef) {
      if (!ef.isFinite()) {
        return EInteger.FromInt32(0);
      }
      if (ef.isZero()) {
        return EInteger.FromInt32(0);
      }
      EInteger retEInt = ef.getExponent();
      EInteger valueEiPrecision =
        ef.getUnsignedMantissa().GetSignedBitLengthAsEInteger();
      retEInt = retEInt.Add(valueEiPrecision.Subtract(1));
      return retEInt;
    }

    private EDecimal RoundToExponentFast(
      int exponentSmall,
      ERounding rounding) {
      if (this.isFinite() && this.exponent.CanFitInInt32() &&
        this.unsignedMantissa.CanFitInInt32()) {
        int thisExponentSmall = this.exponent.ToInt32();
        if (thisExponentSmall == exponentSmall) {
          return this;
        }
        if (thisExponentSmall >= -100 && thisExponentSmall <= 100 &&
          exponentSmall >= -100 && exponentSmall <= 100) {
          if (rounding == ERounding.Down) {
            int diff = exponentSmall - thisExponentSmall;
            if (diff >= 1 && diff <= 9) {
              int thisMantissaSmall = this.unsignedMantissa.ToInt32();
              thisMantissaSmall /= ValueTenPowers[diff];
              return new EDecimal(
                  FastIntegerFixed.FromInt32(thisMantissaSmall),
                  FastIntegerFixed.FromInt32(exponentSmall),
                  this.flags);
            }
          } else if (rounding == ERounding.HalfEven) {
            int diff = exponentSmall - thisExponentSmall;
            int thisMantissaSmall = this.unsignedMantissa.ToInt32();
            if (diff >= 1 && diff <= 9 && thisMantissaSmall != Integer.MAX_VALUE) {
              int pwr = ValueTenPowers[diff - 1];
              int div = thisMantissaSmall / pwr;
              int div2 = (div > 43698) ? (div / 10) : ((div * 26215) >> 18);
              // int div2 = (int)(((long)div*3435973837L) >> 35); // Division by 10
              int rem = div - (div2 * 10);
              if (rem > 5) {
                ++div2;
              } else if (rem == 5) {
                if ((div2 & 1) == 1 || (thisMantissaSmall - (div * pwr)) != 0) {
                  ++div2;
                }
              }
              return new EDecimal(
                  FastIntegerFixed.FromInt32(div2),
                  FastIntegerFixed.FromInt32(exponentSmall),
                  this.flags);
            }
          }
        }
      }
      return null;
    }

    private boolean IsIntegerPartZero() {
      // Returns whether the number, once its fractional part
      // is discarded, is zero.
      if (!this.isFinite()) {
        return false;
      }
      if (this.unsignedMantissa.isValueZero()) {
        return true;
      }
      int sign = this.getExponent().signum();
      if (sign >= 0) {
        return false;
      } else {
        EInteger umantissa = this.getUnsignedMantissa();
        EInteger[] bounds =
          NumberUtility.DecimalDigitLengthBoundsAsEI(umantissa);
        EInteger digitCountUpper = bounds[1];
        EInteger digitCountLower = bounds[0];
        EInteger bigexponent = this.getExponent();
        return (digitCountUpper.compareTo(bigexponent.Abs()) < 0) ? true :
          ((digitCountLower.compareTo(bigexponent.Abs()) > 0) ? false :
            (this.compareTo(-1) > 0 && this.compareTo(1) < 0));
      }
    }

    private EInteger ToEIntegerInternal(boolean exact) {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      int sign = this.getExponent().signum();
      if (this.isZero()) {
        return EInteger.FromInt32(0);
      }
      if (sign == 0) {
        EInteger bigmantissa = this.getMantissa();
        return bigmantissa;
      }
      if (sign > 0) {
        EInteger exponent = this.getExponent();
        EInteger exponentBitSize = exponent.GetUnsignedBitLengthAsEInteger();
        if (exponentBitSize.compareTo(64) > 0) {
          throw new UnsupportedOperationException(
            "Not enough memory to store as EInteger.");
        }
        EInteger bigmantissa = this.getMantissa();
        EInteger bigexponent =
          NumberUtility.FindPowerOfTenFromBig(this.getExponent());
        bigmantissa = bigmantissa.Multiply(bigexponent);
        return bigmantissa;
      } else {
        if (exact && !this.unsignedMantissa.isEvenNumber()) {
          // Mantissa is odd and will have to shift a nonzero
          // number of digits, so can't be an exact integer
          throw new ArithmeticException("Not an exact integer");
        }
        FastInteger bigexponent = this.exponent.ToFastInteger().Negate();
        EInteger bigmantissa = this.unsignedMantissa.ToEInteger();
        DigitShiftAccumulator acc = new DigitShiftAccumulator(bigmantissa, 0, 0);
        if (exact) {
          if (!acc.TruncateRightExact(bigexponent)) {
            // Some digits were discarded
            throw new ArithmeticException("Not an exact integer");
          }
        } else {
          acc.TruncateRightSimple(bigexponent);
        }
        bigmantissa = acc.getShiftedInt();
        if (this.isNegative()) {
          bigmantissa = bigmantissa.Negate();
        }
        return bigmantissa;
      }
    }

    private static EInteger PowerOfRadixBitsLowerBound(EInteger e) {
      return e.Abs().Multiply(332).Divide(100).Add(1);
    }
    private static EInteger PowerOfRadixBitsUpperBound(EInteger e) {
      return e.Abs().Multiply(333).Divide(100).Add(1);
    }

    public EInteger ToSizedEInteger(int maxBitLength) {
      return this.ToSizedEInteger(maxBitLength, false);
    }

    public EInteger ToSizedEIntegerIfExact(int maxBitLength) {
      return this.ToSizedEInteger(maxBitLength, true);
    }

    private EInteger ToSizedEInteger(int maxBitLength, boolean exact) {
      if (maxBitLength < 0) {
        throw new IllegalArgumentException("maxBitLength (" + maxBitLength +
          ") is not greater or equal to 0");
      }
      if (!this.isFinite() || this.isZero()) {
        return exact ? this.ToEIntegerIfExact() : this.ToEInteger();
      }
      EInteger mant = this.getMantissa();
      EInteger exp = this.getExponent();
      if (exp.signum() > 0) {
        // x * 10^y
        long imantbits = mant.GetSignedBitLengthAsInt64();
        if (imantbits >= maxBitLength) {
          throw new ArithmeticException("Value out of range");
        }
        if (exp.compareTo(0x100000) < 0 && imantbits < 0x100000) {
          // Lower bound of bit count in 10^exp based on ln(10^exp)/ln(2)
          long expBitsLowerBound = (exp.ToInt64Checked() * 332 / 100) + 1;
          if ((imantbits - 1) + expBitsLowerBound > maxBitLength) {
            throw new ArithmeticException("Value out of range");
          }
        } else if (exp.compareTo(maxBitLength) > 0) {
          // Digits in exp is more than max bit length, so out of range
          throw new ArithmeticException("Value out of range");
        } else {
          EInteger mantbits = mant.GetSignedBitLengthAsEInteger();
          if (mantbits.Subtract(1).Add(PowerOfRadixBitsLowerBound(exp))
            .compareTo(maxBitLength) > 0) {
            throw new ArithmeticException("Value out of range");
          }
        }
        mant = exact ? this.ToEIntegerIfExact() : this.ToEInteger();
      } else if (exp.signum() < 0) {
        // x * 10^-y. Check for trivial overflow cases before
        // running ToEInteger.
        exp = exp.Abs();
        long imantbits = mant.GetSignedBitLengthAsInt64();
        if (exp.compareTo(0x100000) < 0 && imantbits < 0x100000) {
          long expBitsUpperBound = (exp.ToInt64Checked() * 333 / 100) + 1;
          long expBitsLowerBound = (exp.ToInt64Checked() * 332 / 100) + 1;
          if (imantbits - 1 - expBitsUpperBound > maxBitLength) {
            throw new ArithmeticException("Value out of range");
          }
          if (imantbits + 1 < expBitsLowerBound) {
            // Less than one, so not exact
            if (exact) {
              throw new ArithmeticException("Not an exact integer");
            } else {
              return EInteger.FromInt32(0);
            }
          }
        } else if (imantbits < 0x100000 && exp.compareTo(0x400000) >= 0) {
          // (mant / 10^exp) would be less than one, so not exact
          if (exact) {
            throw new ArithmeticException("Not an exact integer");
          } else {
            return EInteger.FromInt32(0);
          }
        } else {
          EInteger mantbits = mant.GetSignedBitLengthAsEInteger();
          if (mantbits.Subtract(1).Subtract(PowerOfRadixBitsUpperBound(exp))
            .compareTo(maxBitLength) > 0) {
            throw new ArithmeticException("Value out of range");
          }
        }
        mant = exact ? this.ToEIntegerIfExact() : this.ToEInteger();
      }
      if (mant.GetSignedBitLengthAsEInteger().compareTo(maxBitLength) > 0) {
        throw new ArithmeticException("Value out of range");
      }
      return mant;
    }

    private static boolean HasTerminatingBinaryExpansion(EInteger
      den) {
      if (den.isZero()) {
        return false;
      }
      if (den.GetUnsignedBit(0) && den.compareTo(EInteger.FromInt32(1)) != 0) {
        return false;
      }
      // NOTE: Equivalent to (den >> lowBit(den)) == 1
      return den.GetUnsignedBitLengthAsEInteger()
        .equals(den.GetLowBitAsEInteger().Add(1));
    }

    private EFloat WithThisSign(EFloat ef) {
      return this.isNegative() ? ef.Negate() : ef;
    }

    public EFloat ToEFloat(EContext ec) {
      EInteger bigintExp = this.getExponent();
      EInteger bigUnsignedMantissa = this.getUnsignedMantissa();
      // System.out.println("ToEFloat " + this.getExponent() + "," + this.getMantissa());
      if (this.IsNaN()) {
        return EFloat.CreateNaN(
            this.getUnsignedMantissa(),
            this.IsSignalingNaN(),
            this.isNegative(),
            ec);
      }
      if (this.IsPositiveInfinity()) {
        return EFloat.PositiveInfinity.RoundToPrecision(ec);
      }
      if (this.IsNegativeInfinity()) {
        return EFloat.NegativeInfinity.RoundToPrecision(ec);
      }
      if (bigUnsignedMantissa.isZero()) {
        return this.isNegative() ? EFloat.NegativeZero.RoundToPrecision(ec) :
          EFloat.Zero.RoundToPrecision(ec);
      }
      if (bigintExp.isZero()) {
        // Integer
        // System.out.println("Integer");
        return this.WithThisSign(EFloat.FromEInteger(bigUnsignedMantissa))
          .RoundToPrecision(ec);
      }
      EContext b64 = EContext.Binary64;
      if (ec != null && ec.getHasMaxPrecision() &&
        ec.getHasExponentRange() &&
        !ec.isSimplified() && ec.getEMax().compareTo(b64.getEMax()) <= 0 &&
        ec.getEMin().compareTo(b64.getEMin()) >= 0 &&
        ec.getPrecision().compareTo(b64.getPrecision()) <= 0) {
        // Quick check for overflow or underflow
        EInteger[] bounds =
          NumberUtility.DecimalDigitLengthBoundsAsEI(bigUnsignedMantissa);
        EInteger digitCountUpper = bounds[1];
        EInteger adjexpLowerBound = bigintExp;
        EInteger adjexpUpperBound = bigintExp.Add(
            digitCountUpper.Subtract(1));
        if (adjexpUpperBound.compareTo(-326) < 0) {
          // Underflow to zero
          EInteger eTiny = ec.getEMin().Subtract(ec.getPrecision().Subtract(1));
          eTiny = eTiny.Subtract(2); // subtract 2 from proper eTiny to
          // trigger underflow (2, rather than 1, because of HalfUp mode)
          EFloat ret = EFloat.Create(EInteger.FromInt32(
                this.isNegative() ? -1 : 1),
              eTiny);
          return ret.RoundToPrecision(ec);
        } else if (adjexpLowerBound.compareTo(309) > 0) {
          // System.out.println("Overflow A");
          return EFloat.GetMathValue().SignalOverflow(ec, this.isNegative());
        }
        EInteger digitCountLower = bounds[0];
        // System.out.println("DCL " + digitCountLower + "," + bigintExp);
        if (bigintExp.signum() >= 0 &&
          digitCountLower.Subtract(2).compareTo(309) > 0) {
          // System.out.println("Overflow B");
          return EFloat.GetMathValue().SignalOverflow(ec, this.isNegative());
        } else if (ec.getAdjustExponent() &&
              digitCountLower.Add(bigintExp).Subtract(2).compareTo(309) > 0) {
          // System.out.println("Overflow C");
          return EFloat.GetMathValue().SignalOverflow(ec, this.isNegative());
        }
      }
      if (bigintExp.signum() > 0) {
        // Scaled integer
        // --- Optimizations for Binary32 and Binary64
        if (ec == EContext.Binary32) {
          if (bigintExp.compareTo(39) > 0) {
            return this.isNegative() ? EFloat.NegativeInfinity :
              EFloat.PositiveInfinity;
          }
        } else if (ec == EContext.Binary64) {
          if (bigintExp.compareTo(309) > 0) {
            // System.out.println("Overflow D");
            return this.isNegative() ? EFloat.NegativeInfinity :
              EFloat.PositiveInfinity;
          }
        }
        // --- End optimizations for Binary32 and Binary64
        // System.out.println("Scaled integer");
        EInteger bigmantissa = bigUnsignedMantissa;
        bigintExp = NumberUtility.FindPowerOfTenFromBig(bigintExp);
        bigmantissa = bigmantissa.Multiply(bigintExp);
        return this.WithThisSign(EFloat.FromEInteger(bigmantissa))
          .RoundToPrecision(ec);
      } else {
        // Fractional number
        // System.out.println("Fractional");
        EInteger scale = bigintExp;
        EInteger bigmantissa = bigUnsignedMantissa;
        EInteger negscale = scale.Negate();
        EInteger divisor = NumberUtility.FindPowerOfTenFromBig(negscale);
        ec = (ec == null) ? (EContext.UnlimitedHalfEven) : ec;
        if (ec.getHasMaxPrecision()) {
          EFloat efNum = EFloat.FromEInteger(bigmantissa);
          if (this.signum() < 0) {
            efNum = efNum.Negate();
          }
          EFloat efDen = EFloat.FromEInteger(divisor);
          return efNum.Divide(efDen, ec);
        } else if (!ec.getHasFlagsOrTraps()) {
          EFloat efNum = EFloat.FromEInteger(bigmantissa);
          if (this.signum() < 0) {
            efNum = efNum.Negate();
          }
          EFloat efDen = EFloat.FromEInteger(divisor);
          EFloat ret = efNum.Divide(efDen, ec);
          if (!ret.IsNaN()) {
            return ret;
          }
          EInteger bitprec = bigmantissa.GetUnsignedBitLengthAsEInteger();
          bitprec = EInteger.Max(bitprec, EInteger.FromInt32(53));
          return efNum.Divide(
              efDen,
              ec.WithBigPrecision(bitprec));
        } else if (ec.getTraps() != 0) {
          EContext tctx = ec.GetNontrapping();
          EFloat ret = this.ToEFloat(tctx);
          return ec.TriggerTraps(ret, tctx);
        } else {
          EContext tmpctx = ec.WithBlankFlags();
          EFloat efNum = EFloat.FromEInteger(bigmantissa);
          if (this.signum() < 0) {
            efNum = efNum.Negate();
          }
          EFloat efDen = EFloat.FromEInteger(divisor);
          EFloat ret = efNum.Divide(efDen, tmpctx);
          if (!ret.IsNaN()) {
            ec.setFlags(ec.getFlags()|(tmpctx.getFlags()));
            return ret;
          }
          EInteger bitprec = bigmantissa.GetUnsignedBitLengthAsEInteger();
          bitprec = EInteger.Max(bitprec, EInteger.FromInt32(53));
          tmpctx = ec.WithBigPrecision(bitprec).WithBlankFlags();
          ret = efNum.Divide(efDen, tmpctx);
          ec.setFlags(ec.getFlags()|(tmpctx.getFlags()));
          return ret;
        }
      }
    }

    // TODO: Add option to always display exponential notation
    private String ToStringInternal(int mode) {
      boolean negative = (this.flags & BigNumberFlags.FlagNegative) != 0;
      if (!this.isFinite()) {
        if ((this.flags & BigNumberFlags.FlagInfinity) != 0) {
          return negative ? "-Infinity" : "Infinity";
        }
        if ((this.flags & BigNumberFlags.FlagSignalingNaN) != 0) {
          return this.unsignedMantissa.isValueZero() ?
            (negative ? "-sNaN" : "sNaN") :
            (negative ? "-sNaN" + this.unsignedMantissa :
              "sNaN" + this.unsignedMantissa);
        }
        if ((this.flags & BigNumberFlags.FlagQuietNaN) != 0) {
          return this.unsignedMantissa.isValueZero() ? (negative ?
              "-NaN" : "NaN") : (negative ? "-NaN" + this.unsignedMantissa :
              "NaN" + this.unsignedMantissa);
        }
      }
      int scaleSign = -this.exponent.signum();
      String mantissaString;
      if (scaleSign == 0) {
        mantissaString = this.unsignedMantissa.toString();
        return negative ? "-" + mantissaString : mantissaString;
      }
      boolean iszero = this.unsignedMantissa.isValueZero();
      if (mode == 2 && iszero && scaleSign < 0) {
        // special case for zero in plain
        mantissaString = this.unsignedMantissa.toString();
        return negative ? "-" + mantissaString : mantissaString;
      }
      if (mode == 0 && this.unsignedMantissa.CanFitInInt32() &&
        this.exponent.CanFitInInt32()) {
        int intExp = this.exponent.ToInt32();
        int intMant = this.unsignedMantissa.ToInt32();
        if (intMant < 1000 && intExp == -2) {
          int a, b, c;
          int i = 0;
          a = intMant % 10;
          intMant /= 10;
          b = intMant % 10;
          intMant /= 10;
          c = intMant;
          int clength = (negative ? 1 : 0) + 4;
          char[] chars = new char[clength];
          if (negative) {
            chars[i++] = '-';
          }
          chars[i++] = (char)(0x30 + c);
          chars[i++] = '.';
          chars[i++] = (char)(0x30 + b);
          chars[i++] = (char)(0x30 + a);
          return new String(chars, 0, clength);
        } else if (intMant < 100 && intExp == -1) {
          int a, b;
          int i = 0;
          a = intMant % 10;
          intMant /= 10;
          b = intMant;
          int clength = (negative ? 1 : 0) + 3;
          char[] chars = new char[clength];
          if (negative) {
            chars[i++] = '-';
          }
          chars[i++] = (char)(0x30 + b);
          chars[i++] = '.';
          chars[i++] = (char)(0x30 + a);
          return new String(chars, 0, clength);
        }
      }
      mantissaString = this.unsignedMantissa.toString();
      if (mode == 0 && mantissaString.length() < 100 &&
        this.exponent.CanFitInInt32()) {
        int intExp = this.exponent.ToInt32();
        if (intExp > -100 && intExp < 100) {
          int adj = (intExp + mantissaString.length()) - 1;
          if (scaleSign >= 0 && adj >= -6) {
            if (scaleSign > 0) {
              int ms = mantissaString.length();
              int dp = intExp + ms;
              if (dp < 0) {
                dp = -dp;
                int clength = 2 + dp + (negative ? 1 : 0) + ms;
                char[] chars = new char[clength];
                int i = 0;
                if (negative) {
                  chars[i++] = '-';
                }
                chars[i++] = '0';
                chars[i++] = '.';
                for (int j = 0; j < dp; ++j) {
                  chars[i++] = '0';
                }
                for (int j = 0; j < ms; ++j) {
                  chars[i++] = mantissaString.charAt(j);
                }
                return new String(chars, 0, clength);
              } else if (dp == 0) {
                int clength = 2 + (negative ? 1 : 0) + ms;
                char[] chars = new char[clength];
                int i = 0;
                if (negative) {
                  chars[i++] = '-';
                }
                chars[i++] = '0';
                chars[i++] = '.';
                for (int j = 0; j < ms; ++j) {
                  chars[i++] = mantissaString.charAt(j);
                }
                return new String(chars, 0, clength);
              } else if (dp > 0 && dp <= ms) {
                int clength = 1 + (negative ? 1 : 0) + ms;
                char[] chars = new char[clength];
                int i = 0;
                int j = 0;
                if (negative) {
                  chars[i++] = '-';
                }
                for (j = 0; j < dp; ++j) {
                  chars[i++] = mantissaString.charAt(j);
                }
                chars[i++] = '.';
                for (j = dp; j < ms; ++j) {
                  chars[i++] = mantissaString.charAt(j);
                }
                return new String(chars, 0, clength);
              }
            }
          }
        }
      }
      StringBuilder builder = null;
      FastInteger adjustedExponent = FastInteger.FromBig(this.getExponent());
      FastInteger builderLength = new FastInteger(mantissaString.length());
      FastInteger thisExponent = adjustedExponent.Copy();
      adjustedExponent.Add(builderLength).Decrement();
      FastInteger decimalPointAdjust = new FastInteger(1);
      FastInteger threshold = new FastInteger(-6);
      if (mode == 1) {
        // engineering String adjustments
        FastInteger newExponent = adjustedExponent.Copy();
        boolean adjExponentNegative = adjustedExponent.signum() < 0;
        int intphase = adjustedExponent.Copy().Abs().Remainder(3).ToInt32();
        if (iszero && (adjustedExponent.compareTo(threshold) < 0 || scaleSign <
            0)) {
          if (intphase == 1) {
            if (adjExponentNegative) {
              decimalPointAdjust.Increment();
              newExponent.Increment();
            } else {
              decimalPointAdjust.AddInt(2);
              newExponent.AddInt(2);
            }
          } else if (intphase == 2) {
            if (!adjExponentNegative) {
              decimalPointAdjust.Increment();
              newExponent.Increment();
            } else {
              decimalPointAdjust.AddInt(2);
              newExponent.AddInt(2);
            }
          }
          threshold.Increment();
        } else {
          if (intphase == 1) {
            if (!adjExponentNegative) {
              decimalPointAdjust.Increment();
              newExponent.Decrement();
            } else {
              decimalPointAdjust.AddInt(2);
              newExponent.AddInt(-2);
            }
          } else if (intphase == 2) {
            if (adjExponentNegative) {
              decimalPointAdjust.Increment();
              newExponent.Decrement();
            } else {
              decimalPointAdjust.AddInt(2);
              newExponent.AddInt(-2);
            }
          }
        }
        adjustedExponent = newExponent;
      }
      if (mode == 2 || (adjustedExponent.compareTo(threshold) >= 0 &&
          scaleSign >= 0)) {
        if (scaleSign > 0) {
          FastInteger decimalPoint = thisExponent.Copy().Add(builderLength);
          int cmp = decimalPoint.CompareToInt(0);
          builder = null;
          if (cmp < 0) {
            FastInteger tmpFast = new FastInteger(mantissaString.length()).AddInt(6);
            builder = new StringBuilder(tmpFast.CompareToInt(Integer.MAX_VALUE) >
              0 ? Integer.MAX_VALUE : tmpFast.ToInt32());
            if (negative) {
              builder.append('-');
            }
            builder.append("0.");
            AppendString(builder, '0', decimalPoint.Copy().Negate());
            builder.append(mantissaString);
          } else if (cmp == 0) {
            FastInteger tmpFast = new FastInteger(mantissaString.length()).AddInt(6);
            builder = new StringBuilder(tmpFast.CompareToInt(Integer.MAX_VALUE) >
              0 ? Integer.MAX_VALUE : tmpFast.ToInt32());
            if (negative) {
              builder.append('-');
            }
            builder.append("0.");
            builder.append(mantissaString);
          } else if (decimalPoint.CompareToInt(mantissaString.length()) > 0) {
            FastInteger insertionPoint = builderLength;
            if (!insertionPoint.CanFitInInt32()) {
              throw new UnsupportedOperationException();
            }
            int tmpInt = insertionPoint.ToInt32();
            if (tmpInt < 0) {
              tmpInt = 0;
            }
            FastInteger tmpFast = new FastInteger(mantissaString.length()).AddInt(6);
            builder = new StringBuilder(tmpFast.CompareToInt(Integer.MAX_VALUE) >
              0 ? Integer.MAX_VALUE : tmpFast.ToInt32());
            if (negative) {
              builder.append('-');
            }
            builder.append(mantissaString, 0, tmpInt);
            AppendString(
              builder,
              '0',
              decimalPoint.Copy().SubtractInt(builder.length()));
            builder.append('.');
            builder.append(
              mantissaString, tmpInt, (tmpInt)+(mantissaString.length() - tmpInt));
          } else {
            if (!decimalPoint.CanFitInInt32()) {
              throw new UnsupportedOperationException();
            }
            int tmpInt = decimalPoint.ToInt32();
            if (tmpInt < 0) {
              tmpInt = 0;
            }
            FastInteger tmpFast = new FastInteger(mantissaString.length()).AddInt(6);
            builder = new StringBuilder(tmpFast.CompareToInt(Integer.MAX_VALUE) >
              0 ? Integer.MAX_VALUE : tmpFast.ToInt32());
            if (negative) {
              builder.append('-');
            }
            builder.append(mantissaString, 0, tmpInt);
            builder.append('.');
            builder.append(
              mantissaString, tmpInt, (tmpInt)+(mantissaString.length() - tmpInt));
          }
          return builder.toString();
        }
        if (mode == 2 && scaleSign < 0) {
          FastInteger negscale = thisExponent.Copy();
          builder = new StringBuilder();
          if (negative) {
            builder.append('-');
          }
          builder.append(mantissaString);
          AppendString(builder, '0', negscale);
          return builder.toString();
        }
        return (!negative) ? mantissaString : ("-" + mantissaString);
      } else {
        if (mode == 1 && iszero && decimalPointAdjust.CompareToInt(1) > 0) {
          builder = new StringBuilder();
          if (negative) {
            builder.append('-');
          }
          builder.append(mantissaString);
          builder.append('.');
          AppendString(
            builder,
            '0',
            decimalPointAdjust.Copy().Decrement());
        } else {
          FastInteger tmp = decimalPointAdjust.Copy();
          int cmp = tmp.CompareToInt(mantissaString.length());
          if (cmp > 0) {
            tmp.SubtractInt(mantissaString.length());
            builder = new StringBuilder();
            if (negative) {
              builder.append('-');
            }
            builder.append(mantissaString);
            AppendString(builder, '0', tmp);
          } else if (cmp < 0) {
            // Insert a decimal point at the right place
            if (!tmp.CanFitInInt32()) {
              throw new UnsupportedOperationException();
            }
            int tmpInt = tmp.ToInt32();
            if (tmp.signum() < 0) {
              tmpInt = 0;
            }
            FastInteger tmpFast = new FastInteger(mantissaString.length()).AddInt(6);
            builder = new StringBuilder(tmpFast.CompareToInt(Integer.MAX_VALUE) >
              0 ? Integer.MAX_VALUE : tmpFast.ToInt32());
            if (negative) {
              builder.append('-');
            }
            builder.append(mantissaString, 0, tmpInt);
            builder.append('.');
            builder.append(
              mantissaString, tmpInt, (tmpInt)+(mantissaString.length() - tmpInt));
          } else if (adjustedExponent.signum() == 0 && !negative) {
            return mantissaString;
          } else if (adjustedExponent.signum() == 0 && negative) {
            return "-" + mantissaString;
          } else {
            builder = new StringBuilder();
            if (negative) {
              builder.append('-');
            }
            builder.append(mantissaString);
          }
        }
        if (adjustedExponent.signum() != 0) {
          builder.append(adjustedExponent.signum() < 0 ? "E-" : "E+");
          adjustedExponent.Abs();
          builder.append(adjustedExponent.toString());
        }
        return builder.toString();
      }
    }

    private static final class DecimalMathHelper implements IRadixMathHelper<EDecimal> {
      public int GetRadix() {
        return 10;
      }

      public int GetSign(EDecimal value) {
        return value.signum();
      }

      public EInteger GetMantissa(EDecimal value) {
        return value.unsignedMantissa.ToEInteger();
      }

      public EInteger GetExponent(EDecimal value) {
        return value.exponent.ToEInteger();
      }

      public FastIntegerFixed GetMantissaFastInt(EDecimal value) {
        return value.unsignedMantissa;
      }

      public FastIntegerFixed GetExponentFastInt(EDecimal value) {
        return value.exponent;
      }

      public FastInteger GetDigitLength(EInteger ei) {
        long i64 = ei.GetDigitCountAsInt64();
        if (i64 != Long.MAX_VALUE) {
          return FastInteger.FromInt64(i64);
        } else {
          return FastInteger.FromBig(ei.GetDigitCountAsEInteger());
        }
      }

      public IShiftAccumulator CreateShiftAccumulatorWithDigits(
        EInteger bigint,
        int lastDigit,
        int olderDigits) {
        return new DigitShiftAccumulator(bigint, lastDigit, olderDigits);
      }

      public IShiftAccumulator CreateShiftAccumulatorWithDigitsFastInt(
        FastIntegerFixed fastInt,
        int lastDigit,
        int olderDigits) {
        if (fastInt.CanFitInInt32()) {
          return new DigitShiftAccumulator(
              fastInt.ToInt32(),
              lastDigit,
              olderDigits);
        } else {
          return new DigitShiftAccumulator(
              fastInt.ToEInteger(),
              lastDigit,
              olderDigits);
        }
      }
      public FastInteger DivisionShift(
        EInteger num,
        EInteger den) {
        if (den.isZero()) {
          return null;
        }
        EInteger gcd = den.Gcd(EInteger.FromInt32(10));
        if (gcd.compareTo(EInteger.FromInt32(1)) == 0) {
          return null;
        }
        if (den.isZero()) {
          return null;
        }
        // Eliminate factors of 2
        EInteger elowbit = den.GetLowBitAsEInteger();
        den = den.ShiftRight(elowbit);
        // Eliminate factors of 5
        FastInteger fiveShift = new FastInteger(0);
        while (true) {
          EInteger bigrem;
          EInteger bigquo;
          {
            EInteger[] divrem = den.DivRem(EInteger.FromInt64(5));
            bigquo = divrem[0];
            bigrem = divrem[1];
          }
          if (!bigrem.isZero()) {
            break;
          }
          fiveShift.Increment();
          den = bigquo;
        }
        if (den.compareTo(EInteger.FromInt32(1)) != 0) {
          return null;
        }
        FastInteger fastlowbit = FastInteger.FromBig(elowbit);
        if (fiveShift.compareTo(fastlowbit) > 0) {
          return fiveShift;
        } else {
          return fastlowbit;
        }
      }

      public FastIntegerFixed MultiplyByRadixPowerFastInt(
        FastIntegerFixed fbigint,
        FastIntegerFixed fpower) {
        // System.out.println("mbrp "+fbigint+"/"+fpower);
        if (fbigint.isValueZero()) {
          return fbigint;
        }
        boolean fitsInInt32 = fpower.CanFitInInt32();
        int powerInt = fitsInInt32 ? fpower.ToInt32() : 0;
        if (fitsInInt32 && powerInt == 0) {
          return fbigint;
        }
        EInteger bigint = fbigint.ToEInteger();
        EInteger ret = null;
        if (bigint.compareTo(1) != 0) {
          if (fitsInInt32) {
            ret = NumberUtility.MultiplyByPowerOfTen(bigint, powerInt);
          } else {
            EInteger eipower = fpower.ToEInteger();
            ret = NumberUtility.MultiplyByPowerOfTen(bigint, eipower);
          }
        } else {
          ret = fitsInInt32 ? NumberUtility.FindPowerOfTen(powerInt) :
            NumberUtility.FindPowerOfTenFromBig(fpower.ToEInteger());
        }
        return FastIntegerFixed.FromBig(ret);
      }

      public EInteger MultiplyByRadixPower(
        EInteger bigint,
        FastInteger power) {
        if (bigint.isZero()) {
          return bigint;
        }
        boolean fitsInInt32 = power.CanFitInInt32();
        int powerInt = fitsInInt32 ? power.ToInt32() : 0;
        if (fitsInInt32 && powerInt == 0) {
          return bigint;
        }
        if (bigint.compareTo(1) != 0) {
          if (fitsInInt32) {
            return NumberUtility.MultiplyByPowerOfTen(bigint, powerInt);
          } else {
            EInteger eipower = power.ToEInteger();
            return NumberUtility.MultiplyByPowerOfTen(bigint, eipower);
          }
        }
        return fitsInInt32 ? NumberUtility.FindPowerOfTen(powerInt) :
          NumberUtility.FindPowerOfTenFromBig(power.ToEInteger());
      }

      public int GetFlags(EDecimal value) {
        return ((int)value.flags) & 0xff;
      }

      public EDecimal CreateNewWithFlags(
        EInteger mantissa,
        EInteger exponent,
        int flags) {
        return CreateWithFlags(
            FastIntegerFixed.FromBig(mantissa),
            FastIntegerFixed.FromBig(exponent),
            flags);
      }

      public EDecimal CreateNewWithFlagsFastInt(
        FastIntegerFixed fmantissa,
        FastIntegerFixed fexponent,
        int flags) {
        return CreateWithFlags(fmantissa, fexponent, flags);
      }

      public int GetArithmeticSupport() {
        return BigNumberFlags.FiniteAndNonFinite;
      }

      public EDecimal ValueOf(int val) {
        return (val == 0) ? Zero : ((val == 1) ? One : FromInt64(val));
      }
    }

    public EDecimal Increment() {
      return this.Add(1);
    }

    public EDecimal Decrement() {
      return this.Subtract(1);
    }

    // Begin integer conversions
    private void CheckTrivialOverflow(int maxDigits) {
      if (this.isZero()) {
        return;
      }
      if (this.exponent.signum() < 0) {
        EInteger bigexponent = this.getExponent();
        EInteger bigmantissa = this.getUnsignedMantissa();
        bigexponent = bigexponent.Abs();
        bigmantissa = bigmantissa.Abs();
        EInteger lowerBound =
          NumberUtility.DecimalDigitLengthBoundsAsEI(bigmantissa)[0];
        if (lowerBound.Subtract(bigexponent).compareTo(maxDigits) > 0) {
          throw new ArithmeticException("Value out of range");
        }
      } else {
        if (this.exponent.CompareToInt(maxDigits) >= 0) {
          throw new ArithmeticException("Value out of range");
        }
      }
    }

    public byte ToByteChecked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(3);
      if (this.IsIntegerPartZero()) {
        return (byte)0;
      } else if (this.isNegative()) {
        throw new ArithmeticException("Value out of range");
      }
      return this.ToEInteger().ToByteChecked();
    }

    public byte ToByteUnchecked() {
      if (this.isFinite()) {
        if (this.IsIntegerPartZero()) {
          return (byte)0;
        }
        if (this.exponent.CompareToInt(8) >= 0) {
          /* Whether positive or negative, 10^x mod 256 is always 0
              for x >= 8 */ return (byte)0;
        }
        return this.ToEInteger().ToByteUnchecked();
      }
      return (byte)0;
    }

    public byte ToByteIfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      if (this.isNegative() && !this.isZero()) {
        throw new ArithmeticException("Value out of range");
      }
      this.CheckTrivialOverflow(3);
      return this.ToEIntegerIfExact().ToByteChecked();
    }

    public static EDecimal FromByte(byte inputByte) {
      int val = ((int)inputByte) & 0xff;
      return FromInt32(val);
    }

    public short ToInt16Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(5);
      return this.IsIntegerPartZero() ? ((short)0) :
        this.ToEInteger().ToInt16Checked();
    }

    public short ToInt16Unchecked() {
      if (this.isFinite()) {
        if (this.IsIntegerPartZero()) {
          return (short)0;
        }
        if (this.exponent.CompareToInt(16) >= 0) {
          /* Whether positive or negative, 10^x mod 65536 is always 0
              for x >= 16 */ return (short)0;
        }
        return this.ToEInteger().ToInt16Unchecked();
      }
      return (short)0;
    }

    public short ToInt16IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(5);
      return this.ToEIntegerIfExact().ToInt16Checked();
    }

    public static EDecimal FromInt16(short inputInt16) {
      int val = (int)inputInt16;
      return FromInt32(val);
    }

    public int ToInt32Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(10);
      return this.IsIntegerPartZero() ? ((int)0) :
        this.ToEInteger().ToInt32Checked();
    }

    public int ToInt32Unchecked() {
      if (this.isFinite()) {
        if (this.IsIntegerPartZero()) {
          return 0;
        }
        if (this.exponent.CompareToInt(32) >= 0) {
          /* Whether positive or negative, 10^x mod 2^32 is always 0
              for x >= 32 */ return 0;
        }
        return this.ToEInteger().ToInt32Unchecked();
      }
      return 0;
    }

    public int ToInt32IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      if (this.isZero()) {
        return (int)0;
      }
      this.CheckTrivialOverflow(10);
      return this.ToEIntegerIfExact().ToInt32Checked();
    }

    public long ToInt64Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(19);
      return this.IsIntegerPartZero() ? 0L : this.ToEInteger().ToInt64Checked();
    }

    public long ToInt64Unchecked() {
      if (this.isFinite()) {
        if (this.IsIntegerPartZero()) {
          return 0L;
        }
        if (this.exponent.CompareToInt(64) >= 0) {
          /* Whether positive or negative, 10^x mod 2^64 is always 0
              for x >= 64 */ return 0L;
        }
        return this.ToEInteger().ToInt64Unchecked();
      }
      return 0L;
    }

    public long ToInt64IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      if (this.isZero()) {
        return 0L;
      }
      this.CheckTrivialOverflow(19);
      return this.ToEIntegerIfExact().ToInt64Checked();
    }
    // End integer conversions
  }
