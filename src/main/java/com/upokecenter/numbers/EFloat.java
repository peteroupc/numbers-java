package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

  public final class EFloat implements Comparable<EFloat> {
    //-----------------------------------------------
    private static final int CacheFirst = -24;
    private static final int CacheLast = 128;

    public static final EFloat NaN = new EFloat(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)BigNumberFlags.FlagQuietNaN);

    public static final EFloat NegativeInfinity = new EFloat(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)(BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative));

    public static final EFloat NegativeZero = new EFloat(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)BigNumberFlags.FlagNegative);

    public static final EFloat One = new EFloat(
      FastIntegerFixed.One,
      FastIntegerFixed.Zero,
      (byte)0);

    public static final EFloat PositiveInfinity = new EFloat(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)BigNumberFlags.FlagInfinity);

    public static final EFloat SignalingNaN = new EFloat(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)BigNumberFlags.FlagSignalingNaN);

    public static final EFloat Ten = new EFloat(
      FastIntegerFixed.FromInt32(10),
      FastIntegerFixed.Zero,
      (byte)0);

    public static final EFloat Zero = new EFloat(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)0);

    private static final EFloat[] Cache = EFloatCache(CacheFirst,
        CacheLast);

    private static EFloat[] EFloatCache(int first, int last) {
      EFloat[] cache = new EFloat[(last - first) + 1];
      int i;
      for (i = first; i <= last; ++i) {
        if (i == 0) {
          cache[i - first] = Zero;
        } else if (i == 1) {
          cache[i - first] = One;
        } else if (i == 10) {
          cache[i - first] = Ten;
        } else {
          cache[i - first] = new EFloat(
            FastIntegerFixed.FromInt32(Math.abs(i)),
            FastIntegerFixed.Zero,
            (byte)((i < 0) ? BigNumberFlags.FlagNegative : 0));
        }
      }
      return cache;
    }

    //----------------------------------------------------------------
    private static final IRadixMath<EFloat> MathValue = new
    TrappableRadixMath<EFloat>(
      new ExtendedOrSimpleRadixMath<EFloat>(new BinaryMathHelper()));

    static IRadixMath<EFloat> GetMathValue() {
      return MathValue;
    }

    private final FastIntegerFixed exponent;
    private final FastIntegerFixed unsignedMantissa;
    private final byte flags;

    private EFloat(
      FastIntegerFixed unsignedMantissa,
      FastIntegerFixed exponent,
      byte flags) {
      this.unsignedMantissa = unsignedMantissa;
      this.exponent = exponent;
      this.flags = flags;
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

    public final EInteger getMantissa() {
        return this.isNegative() ? this.unsignedMantissa.ToEInteger().Negate() :
          this.unsignedMantissa.ToEInteger();
      }

    public final int signum() {
        return (((this.flags & BigNumberFlags.FlagSpecial) == 0) &&
            this.unsignedMantissa.isValueZero()) ? 0 :
          (((this.flags & BigNumberFlags.FlagNegative) != 0) ? -1 : 1);
      }

    public final EInteger getUnsignedMantissa() {
        return this.unsignedMantissa.ToEInteger();
      }

    public EFloat Copy() {
      return new EFloat(this.unsignedMantissa, this.exponent, this.flags);
    }

    public static EFloat Create(int mantissaSmall, int exponentSmall) {
      if (exponentSmall == 0 && mantissaSmall >= CacheFirst &&
        mantissaSmall <= CacheLast) {
        return Cache[mantissaSmall - CacheFirst];
      }
      if (mantissaSmall < 0) {
        if (mantissaSmall == Integer.MIN_VALUE) {
          FastIntegerFixed fi = FastIntegerFixed.FromInt64(Integer.MIN_VALUE);
          return new EFloat(
              fi.Negate(),
              FastIntegerFixed.FromInt32(exponentSmall),
              (byte)BigNumberFlags.FlagNegative);
        }
        return new EFloat(
            FastIntegerFixed.FromInt32(-mantissaSmall),
            FastIntegerFixed.FromInt32(exponentSmall),
            (byte)BigNumberFlags.FlagNegative);
      } else if (mantissaSmall == 0) {
        return new EFloat(
            FastIntegerFixed.Zero,
            FastIntegerFixed.FromInt32(exponentSmall),
            (byte)0);
      } else {
        return new EFloat(
            FastIntegerFixed.FromInt32(mantissaSmall),
            FastIntegerFixed.FromInt32(exponentSmall),
            (byte)0);
      }
    }

    public static EFloat Create(
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
      return new EFloat(
          sign < 0 ? fi.Negate() : fi,
          FastIntegerFixed.FromInt32(exponentSmall),
          (byte)((sign < 0) ? BigNumberFlags.FlagNegative : 0));
    }

    public static EFloat Create(
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
      return new EFloat(
          sign < 0 ? fi.Negate() : fi,
          FastIntegerFixed.FromInt64(exponentLong),
          (byte)((sign < 0) ? BigNumberFlags.FlagNegative : 0));
    }

    public static EFloat Create(
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
      return new EFloat(
          sign < 0 ? fi.Negate() : fi,
          FastIntegerFixed.FromBig(exponent),
          (byte)((sign < 0) ? BigNumberFlags.FlagNegative : 0));
    }

    public static EFloat Create(
      long mantissaLong,
      int exponentSmall) {
      return Create(mantissaLong, (long)exponentSmall);
    }

    public static EFloat Create(
      long mantissaLong,
      long exponentLong) {
      if (mantissaLong >= Integer.MIN_VALUE && mantissaLong <= Integer.MAX_VALUE &&
        exponentLong >= Integer.MIN_VALUE && exponentLong <= Integer.MAX_VALUE) {
        return Create((int)mantissaLong, (int)exponentLong);
      } else if (mantissaLong == Long.MIN_VALUE) {
        FastIntegerFixed fi = FastIntegerFixed.FromInt64(mantissaLong);
        return new EFloat(
            fi.Negate(),
            FastIntegerFixed.FromInt64(exponentLong),
            (byte)((mantissaLong < 0) ? BigNumberFlags.FlagNegative : 0));
      } else {
        FastIntegerFixed fi = FastIntegerFixed.FromInt64(Math.abs(
              mantissaLong));
        return new EFloat(
            fi,
            FastIntegerFixed.FromInt64(exponentLong),
            (byte)((mantissaLong < 0) ? BigNumberFlags.FlagNegative : 0));
      }
    }

    public static EFloat CreateNaN(EInteger diag) {
      return CreateNaN(diag, false, false, null);
    }

    public static EFloat CreateNaN(
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
        EFloat ef = new EFloat(
          FastIntegerFixed.FromBig(diag),
          FastIntegerFixed.Zero,
          (byte)flags).RoundToPrecision(ctx);

        int newFlags = ef.flags;
        newFlags &= ~BigNumberFlags.FlagQuietNaN;
        newFlags |= signaling ? BigNumberFlags.FlagSignalingNaN :
          BigNumberFlags.FlagQuietNaN;
        return new EFloat(
            ef.unsignedMantissa,
            ef.exponent,
            (byte)newFlags);
      }
      flags |= signaling ? BigNumberFlags.FlagSignalingNaN :
        BigNumberFlags.FlagQuietNaN;
      return new EFloat(
          FastIntegerFixed.FromBig(diag),
          FastIntegerFixed.Zero,
          (byte)flags);
    }

    public static EFloat FromDoubleBits(long dblBits) {
      int floatExponent = (int)((dblBits >> 52) & 0x7ff);
      boolean neg = (dblBits >> 63) != 0;
      long lvalue;
      if (floatExponent == 2047) {
        if ((dblBits & ((1L << 52) - 1)) == 0) {
          return neg ? EFloat.NegativeInfinity : EFloat.PositiveInfinity;
        }
        // Treat high bit of mantissa as quiet/signaling bit
        boolean quiet = ((dblBits >> 32) & 0x80000) != 0;
        lvalue = dblBits & ((1L << 51) - 1);
        if (lvalue == 0) {
          return quiet ? NaN : SignalingNaN;
        }
        int flags = (neg ? BigNumberFlags.FlagNegative : 0) |
          (quiet ? BigNumberFlags.FlagQuietNaN :
            BigNumberFlags.FlagSignalingNaN);
        return CreateWithFlags(
            EInteger.FromInt64(lvalue),
            EInteger.FromInt32(0),
            flags);
      }
      lvalue = dblBits & ((1L << 52) - 1); // Mask out the exponent and sign
      if (floatExponent == 0) {
        ++floatExponent;
      } else {
        lvalue |= 1L << 52;
      }
      if (lvalue != 0) {
        // Shift away trailing zeros
        while ((lvalue & 1L) == 0) {
          lvalue >>= 1;
          ++floatExponent;
        }
      } else {
        return neg ? EFloat.NegativeZero : EFloat.Zero;
      }
      return CreateWithFlags(
          EInteger.FromInt64(lvalue),
          EInteger.FromInt64(floatExponent - 1075),
          neg ? BigNumberFlags.FlagNegative : 0);
    }

    public static EFloat FromSingle(float flt) {
      return FromSingleBits(
          Float.floatToRawIntBits(flt));
    }

    public static EFloat FromDouble(double dbl) {
      long lvalue = Double.doubleToRawLongBits(dbl);
      return FromDoubleBits(lvalue);
    }

    public static EFloat FromEInteger(EInteger bigint) {
      return EFloat.Create(bigint, (int)0);
    }

    public static EFloat FromSingleBits(int value) {
      boolean neg = (value >> 31) != 0;
      int floatExponent = (int)((value >> 23) & 0xff);
      int valueFpMantissa = value & 0x7fffff;
      EInteger bigmant;
      if (floatExponent == 255) {
        if (valueFpMantissa == 0) {
          return neg ? NegativeInfinity : PositiveInfinity;
        }
        // Treat high bit of mantissa as quiet/signaling bit
        boolean quiet = (valueFpMantissa & 0x400000) != 0;
        valueFpMantissa &= 0x3fffff;
        bigmant = EInteger.FromInt32(valueFpMantissa);
        value = (neg ? BigNumberFlags.FlagNegative : 0) | (quiet ?
            BigNumberFlags.FlagQuietNaN : BigNumberFlags.FlagSignalingNaN);
        if (bigmant.isZero()) {
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
        valueFpMantissa |= 1 << 23;
      }
      if (valueFpMantissa == 0) {
        return neg ? EFloat.NegativeZero : EFloat.Zero;
      }
      while ((valueFpMantissa & 1) == 0) {
        ++floatExponent;
        valueFpMantissa >>= 1;
      }
      if (neg) {
        valueFpMantissa = -valueFpMantissa;
      }
      bigmant = EInteger.FromInt32(valueFpMantissa);
      return EFloat.Create(
          bigmant,
          EInteger.FromInt64(floatExponent - 150));
    }

    static EFloat SignalUnderflow(EContext ec, boolean negative, boolean
      zeroSignificand) {
      EInteger eTiny = ec.getEMin().Subtract(ec.getPrecision().Subtract(1));
      eTiny = eTiny.Subtract(2); // subtract 2 from proper eTiny to
      // trigger underflow (2, rather than 1, because of HalfUp mode)
      EFloat ret = EFloat.Create(
          zeroSignificand ? EInteger.FromInt32(0) : EInteger.FromInt32(1),
          eTiny);
      if (negative) {
        ret = ret.Negate();
      }
      return ret.RoundToPrecision(ec);
    }

    static EFloat SignalOverflow(EContext ec, boolean negative, boolean
      zeroSignificand) {
      if (zeroSignificand) {
        EFloat ret = EFloat.Create(EInteger.FromInt32(0), ec.getEMax());
        if (negative) {
          ret = ret.Negate();
        }
        return ret.RoundToPrecision(ec);
      } else {
        return MathValue.SignalOverflow(ec, negative);
      }
    }

    public static EFloat FromString(
      String str,
      int offset,
      int length,
      EContext ctx) {
      return EFloatTextString.FromString(str, offset, length, ctx);
    }

    public static EFloat FromString(String str) {
      return FromString(str, 0, str == null ? 0 : str.length(), null);
    }

    public static EFloat FromString(String str, EContext ctx) {
      return FromString(str, 0, str == null ? 0 : str.length(), ctx);
    }

    public static EFloat FromString(String str, int offset, int length) {
      return FromString(str, offset, length, null);
    }

    public static EFloat FromString(
      char[] chars,
      int offset,
      int length,
      EContext ctx) {
      return EFloatCharArrayString.FromString(chars, offset, length, ctx);
    }

    public static EFloat FromString(char[] chars) {
      return FromString(chars, 0, chars == null ? 0 : chars.length, null);
    }

    public static EFloat FromString(char[] chars, EContext ctx) {
      return FromString(chars, 0, chars == null ? 0 : chars.length, ctx);
    }

    public static EFloat FromString(char[] chars, int offset, int length) {
      return FromString(chars, offset, length, null);
    }

    public static EFloat FromString(
      byte[] bytes,
      int offset,
      int length,
      EContext ctx) {
      return EFloatByteArrayString.FromString(bytes, offset, length, ctx);
    }

    public static EFloat FromString(byte[] bytes) {
      return FromString(bytes, 0, bytes == null ? 0 : bytes.length, null);
    }

    public static EFloat FromString(byte[] bytes, EContext ctx) {
      return FromString(bytes, 0, bytes == null ? 0 : bytes.length, ctx);
    }

    public static EFloat FromString(byte[] bytes, int offset, int length) {
      return FromString(bytes, offset, length, null);
    }

    public static EFloat Max(
      EFloat first,
      EFloat second,
      EContext ctx) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return MathValue.Max(first, second, ctx);
    }

    public static EFloat Max(
      EFloat first,
      EFloat second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return Max(first, second, null);
    }

    public static EFloat MaxMagnitude(
      EFloat first,
      EFloat second,
      EContext ctx) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return MathValue.MaxMagnitude(first, second, ctx);
    }

    public static EFloat MaxMagnitude(
      EFloat first,
      EFloat second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return MaxMagnitude(first, second, null);
    }

    public static EFloat Min(
      EFloat first,
      EFloat second,
      EContext ctx) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return MathValue.Min(first, second, ctx);
    }

    public static EFloat Min(
      EFloat first,
      EFloat second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return Min(first, second, null);
    }

    public static EFloat MinMagnitude(
      EFloat first,
      EFloat second,
      EContext ctx) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return MathValue.MinMagnitude(first, second, ctx);
    }

    public static EFloat MinMagnitude(
      EFloat first,
      EFloat second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return MinMagnitude(first, second, null);
    }

    public static EFloat PI(EContext ctx) {
      return MathValue.Pi(ctx);
    }

    public EFloat Abs() {
      if (this.isNegative()) {
        EFloat er = new EFloat(
          this.unsignedMantissa,
          this.exponent,
          (byte)(this.flags & ~BigNumberFlags.FlagNegative));
        return er;
      }
      return this;
    }

    public EFloat Abs(EContext context) {
      return MathValue.Abs(this, context);
    }

    public EFloat Add(int intValue) {
      return this.Add(EFloat.FromInt32(intValue));
    }

    public EFloat Subtract(int intValue) {
      return (intValue == Integer.MIN_VALUE) ?
        this.Subtract(EFloat.FromInt32(intValue)) : this.Add(-intValue);
    }

    public EFloat Multiply(int intValue) {
      return this.Multiply(EFloat.FromInt32(intValue));
    }

    public EFloat Divide(int intValue) {
      return this.Divide(EFloat.FromInt32(intValue));
    }

    public EFloat Add(long longValue) {
      return this.Add(EFloat.FromInt64(longValue));
    }

    public EFloat Subtract(long longValue) {
      return this.Subtract(EFloat.FromInt64(longValue));
    }

    public EFloat Multiply(long longValue) {
      return this.Multiply(EFloat.FromInt64(longValue));
    }

    public EFloat Divide(long longValue) {
      return this.Divide(EFloat.FromInt64(longValue));
    }

    public EFloat Add(EFloat otherValue) {
      return this.Add(otherValue, EContext.UnlimitedHalfEven);
    }

    public EFloat Add(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.Add(this, otherValue, ctx);
    }

    public int compareTo(EFloat other) {
      return MathValue.compareTo(this, other);
    }

    public int CompareToValue(EFloat other) {
      return MathValue.compareTo(this, other);
    }

    public int compareTo(int intOther) {
      return this.CompareToValue(EFloat.FromInt32(intOther));
    }

    public int CompareToValue(int intOther) {
      return this.CompareToValue(EFloat.FromInt32(intOther));
    }

    public int CompareToValue(long intOther) {
      return this.CompareToValue(FromInt64(intOther));
    }

    public int compareTo(long intOther) {
      return this.CompareToValue(FromInt64(intOther));
    }

    public EFloat CompareToSignal(
      EFloat other,
      EContext ctx) {
      return MathValue.CompareToWithContext(this, other, true, ctx);
    }

    public int CompareToTotal(EFloat other, EContext ctx) {
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

    public int CompareToTotalMagnitude(EFloat other, EContext ctx) {
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

    public int CompareToTotal(EFloat other) {
      if (other == null) {
        return 1;
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

    public int CompareToTotalMagnitude(EFloat other) {
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

    public EFloat CompareToWithContext(
      EFloat other,
      EContext ctx) {
      return MathValue.CompareToWithContext(this, other, false, ctx);
    }

    public EFloat CopySign(EFloat other) {
      if (other == null) {
        throw new NullPointerException("other");
      }
      if (this.isNegative()) {
        return other.isNegative() ? this : this.Negate();
      } else {
        return other.isNegative() ? this.Negate() : this;
      }
    }

    public EFloat Divide(EFloat divisor) {
      return this.Divide(
          divisor,
          EContext.ForRounding(ERounding.None));
    }

    public EFloat Divide(
      EFloat divisor,
      EContext ctx) {
      return MathValue.Divide(this, divisor, ctx);
    }

/**
 * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EFloat[] DivideAndRemainderNaturalScale(EFloat
      divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

/**
 * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EFloat[] DivideAndRemainderNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return this.DivRemNaturalScale(divisor, ctx);
    }

    public EFloat DivideToExponent(
      EFloat divisor,
      long desiredExponentSmall,
      EContext ctx) {
      return this.DivideToExponent(
          divisor,
          EInteger.FromInt64(desiredExponentSmall),
          ctx);
    }

    public EFloat DivideToExponent(
      EFloat divisor,
      long desiredExponentSmall,
      ERounding rounding) {
      return this.DivideToExponent(
          divisor,
          EInteger.FromInt64(desiredExponentSmall),
          EContext.ForRounding(rounding));
    }

    public EFloat DivideToExponent(
      EFloat divisor,
      EInteger exponent,
      EContext ctx) {
      return MathValue.DivideToExponent(this, divisor, exponent, ctx);
    }

    public EFloat DivideToExponent(
      EFloat divisor,
      EInteger desiredExponent,
      ERounding rounding) {
      return this.DivideToExponent(
          divisor,
          desiredExponent,
          EContext.ForRounding(rounding));
    }

    public EFloat DivideToIntegerNaturalScale(
      EFloat divisor) {
      return this.DivideToIntegerNaturalScale(
          divisor,
          EContext.ForRounding(ERounding.Down));
    }

    public EFloat DivideToIntegerNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return MathValue.DivideToIntegerNaturalScale(this, divisor, ctx);
    }

    public EFloat DivideToIntegerZeroScale(
      EFloat divisor,
      EContext ctx) {
      return MathValue.DivideToIntegerZeroScale(this, divisor, ctx);
    }

    public EFloat DivideToSameExponent(
      EFloat divisor,
      ERounding rounding) {
      return this.DivideToExponent(
          divisor,
          this.exponent.ToEInteger(),
          EContext.ForRounding(rounding));
    }

    public EFloat[] DivRemNaturalScale(EFloat divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

    public EFloat[] DivRemNaturalScale(
      EFloat divisor,
      EContext ctx) {
      EFloat[] result = new EFloat[2];
      result[0] = this.DivideToIntegerNaturalScale(divisor, null);
      result[1] = this.Subtract(
          result[0].Multiply(divisor, null),
          ctx);
      result[0] = result[0].RoundToPrecision(ctx);
      return result;
    }

    public boolean equals(EFloat other) {
      return this.EqualsInternal(other);
    }

    @Override public boolean equals(Object obj) {
      return this.EqualsInternal(((obj instanceof EFloat) ? (EFloat)obj : null));
    }

    public boolean EqualsInternal(EFloat otherValue) {
      if (otherValue == null) {
        return false;
      }
      return this.exponent.equals(otherValue.exponent) &&
        this.unsignedMantissa.equals(otherValue.unsignedMantissa) &&
        this.flags == otherValue.flags;
    }

    public EFloat Exp(EContext ctx) {
      return MathValue.Exp(this, ctx);
    }

    public EFloat ExpM1(EContext ctx) {
      EFloat value = this;
      if (value.IsNaN()) {
        return value.Plus(ctx);
      }
      if (ctx == null || !ctx.getHasMaxPrecision()) {
        return EFloat.SignalingNaN.Plus(ctx);
      }
      if (ctx.getTraps() != 0) {
        EContext tctx = ctx.GetNontrapping();
        EFloat ret = value.ExpM1(tctx);
        return ctx.TriggerTraps(ret, tctx);
      } else if (ctx.isSimplified()) {
        EContext tmpctx = ctx.WithSimplified(false).WithBlankFlags();
        EFloat ret = value.PreRound(ctx).ExpM1(tmpctx);
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
          return EFloat.NegativeInfinity;
        } else if (value.IsPositiveInfinity()) {
          return EFloat.PositiveInfinity;
        } else if (value.IsNegativeInfinity()) {
          return EFloat.FromInt32(-1).Plus(ctx);
        } else if (value.compareTo(0) == 0) {
          return EFloat.FromInt32(0).Plus(ctx);
        }
        int flags = ctx.getFlags();
        EContext tmpctx = null;
        EFloat ret;
        {
          EInteger prec = ctx.getPrecision().Add(3);
          tmpctx = ctx.WithBigPrecision(prec).WithBlankFlags();
          if (value.Abs().compareTo(EFloat.Create(1, -1)) < 0) {
            ret = value.Exp(tmpctx).Add(EFloat.FromInt32(-1), ctx);
            EFloat oldret = ret;
            while (true) {
              prec = prec.Add(ctx.getPrecision()).Add(3);
              tmpctx = ctx.WithBigPrecision(prec).WithBlankFlags();
              ret = value.Exp(tmpctx).Add(EFloat.FromInt32(-1), ctx);
              if (ret.compareTo(0) != 0 && ret.compareTo(oldret) == 0) {
                break;
              }
              oldret = ret;
            }
          } else {
            ret = value.Exp(tmpctx).Add(EFloat.FromInt32(-1), ctx);
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
      int valueHashCode = 403796923;
      {
        valueHashCode += 403797019 * this.exponent.hashCode();
        valueHashCode += 403797059 * this.unsignedMantissa.hashCode();
        valueHashCode += 403797127 * this.flags;
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
            BigNumberFlags.FlagNegative)) ==
        (BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);
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

    public EFloat Log(EContext ctx) {
      return MathValue.Ln(this, ctx);
    }

    public EFloat Log10(EContext ctx) {
      return this.LogN(EFloat.FromInt32(10), ctx);
    }

    public EFloat Log1P(EContext ctx) {
      EFloat value = this;
      if (value.IsNaN()) {
        return value.Plus(ctx);
      }
      if (ctx == null || !ctx.getHasMaxPrecision() ||
        (value.compareTo(-1) < 0)) {
        return EFloat.SignalingNaN.Plus(ctx);
      }
      if (ctx.getTraps() != 0) {
        EContext tctx = ctx.GetNontrapping();
        EFloat ret = value.Log1P(tctx);
        return ctx.TriggerTraps(ret, tctx);
      } else if (ctx.isSimplified()) {
        EContext tmpctx = ctx.WithSimplified(false).WithBlankFlags();
        EFloat ret = value.PreRound(ctx).Log1P(tmpctx);
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
          return EFloat.NegativeInfinity;
        } else if (value.IsPositiveInfinity()) {
          return EFloat.PositiveInfinity;
        }
        if (value.compareTo(0) == 0) {
          return EFloat.FromInt32(0).Plus(ctx);
        }
        int flags = ctx.getFlags();
        EContext tmpctx = null;
        EFloat ret;
        // System.out.println("cmp=" +
        // value.compareTo(EFloat.Create(1, -1)) +
        // " add=" + value.Add(EFloat.FromInt32(1)));
        if (value.compareTo(EFloat.Create(1, -1)) < 0) {
          ret = value.Add(EFloat.FromInt32(1)).Log(ctx);
        } else {
          tmpctx = ctx.WithBigPrecision(ctx.getPrecision().Add(3)).WithBlankFlags();
          // System.out.println("orig "+value);
          // System.out.println("sub "+value.Add(EFloat.FromInt32(1),
          // tmpctx).Subtract(value));
          ret = value.Add(EFloat.FromInt32(1), tmpctx).Log(ctx);
          // System.out.println("ret "+ret);
          flags |= tmpctx.getFlags();
        }
        if (ctx.getHasFlags()) {
          flags |= ctx.getFlags();
          ctx.setFlags(flags);
        }
        return ret;
      }
    }

    public EFloat LogN(EFloat baseValue, EContext ctx) {
      EFloat value = this;
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
        return EFloat.SignalingNaN.Plus(ctx);
      }
      if (ctx.getTraps() != 0) {
        EContext tctx = ctx.GetNontrapping();
        EFloat ret = value.LogN(baseValue, tctx);
        return ctx.TriggerTraps(ret, tctx);
      } else if (ctx.isSimplified()) {
        EContext tmpctx = ctx.WithSimplified(false).WithBlankFlags();
        EFloat ret = value.PreRound(ctx).LogN(baseValue.PreRound(ctx),
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
          return baseValue.compareTo(1) < 0 ? EFloat.PositiveInfinity :
            EFloat.NegativeInfinity;
        } else if (value.IsPositiveInfinity()) {
          return baseValue.compareTo(1) < 0 ? EFloat.NegativeInfinity :
            EFloat.PositiveInfinity;
        }
        if (baseValue.compareTo(2) == 0) {
          EFloat ev = value.Reduce(null);
          if (ev.getUnsignedMantissa().compareTo(1) == 0) {
            return EFloat.FromEInteger(ev.getExponent()).Plus(ctx);
          }
        } else if (value.compareTo(1) == 0) {
          return EFloat.FromInt32(0).Plus(ctx);
        } else if (value.compareTo(baseValue) == 0) {
          return EFloat.FromInt32(1).Plus(ctx);
        }
        int flags = ctx.getFlags();
        EContext tmpctx =
          ctx.WithBigPrecision(ctx.getPrecision().Add(3)).WithBlankFlags();
        EFloat ret = value.Log(tmpctx).Divide(baseValue.Log(tmpctx), ctx);
        if (ret.IsInteger() && !ret.isZero()) {
          flags |= EContext.FlagRounded | EContext.FlagInexact;
          if (baseValue.Pow(ret).CompareToValue(value) == 0) {
            EFloat rtmp = ret.Quantize(EFloat.FromInt32(1),
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

    public EFloat MovePointLeft(int places) {
      return this.MovePointLeft(EInteger.FromInt32(places), null);
    }

    public EFloat MovePointLeft(int places, EContext ctx) {
      return this.MovePointLeft(EInteger.FromInt32(places), ctx);
    }

    public EFloat MovePointLeft(EInteger bigPlaces) {
      return this.MovePointLeft(bigPlaces, null);
    }

    public EFloat MovePointLeft(
      EInteger bigPlaces,
      EContext ctx) {
      return (!this.isFinite()) ? this.RoundToPrecision(ctx) :
        this.MovePointRight((bigPlaces).Negate(), ctx);
    }

    public EFloat MovePointRight(int places) {
      return this.MovePointRight(EInteger.FromInt32(places), null);
    }

    public EFloat MovePointRight(int places, EContext ctx) {
      return this.MovePointRight(EInteger.FromInt32(places), ctx);
    }

    public EFloat MovePointRight(EInteger bigPlaces) {
      return this.MovePointRight(bigPlaces, null);
    }

    public EFloat MovePointRight(
      EInteger bigPlaces,
      EContext ctx) {
      if (!this.isFinite()) {
        return this.RoundToPrecision(ctx);
      }
      EInteger bigExp = this.getExponent();
      bigExp = bigExp.Add(bigPlaces);
      if (bigExp.signum() > 0) {
        EInteger mant = this.getUnsignedMantissa().ShiftLeft(bigExp);
        return CreateWithFlags(
            mant,
            EInteger.FromInt32(0),
            this.flags).RoundToPrecision(ctx);
      }
      return CreateWithFlags(
          this.getUnsignedMantissa(),
          bigExp,
          this.flags).RoundToPrecision(ctx);
    }

    public EFloat Multiply(EFloat otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.isFinite() && otherValue.isFinite()) {
        EInteger exp = this.getExponent().Add(otherValue.getExponent());
        int newflags = otherValue.flags ^ this.flags;
        if (this.unsignedMantissa.CanFitInInt32() &&
          otherValue.unsignedMantissa.CanFitInInt32()) {
          int integerA = this.unsignedMantissa.ToInt32();
          int integerB = otherValue.unsignedMantissa.ToInt32();
          long longA = ((long)integerA) * ((long)integerB);
          return CreateWithFlags(longA, exp, newflags);
        } else {
          EInteger eintA = this.getUnsignedMantissa().Multiply(
              otherValue.getUnsignedMantissa());
          return CreateWithFlags(eintA, exp, newflags);
        }
      }
      return this.Multiply(otherValue, EContext.UnlimitedHalfEven);
    }

    public EFloat Multiply(
      EFloat op,
      EContext ctx) {
      return MathValue.Multiply(this, op, ctx);
    }

    public EFloat MultiplyAndAdd(
      EFloat multiplicand,
      EFloat augend) {
      return this.MultiplyAndAdd(multiplicand, augend, null);
    }

    public EFloat MultiplyAndAdd(
      EFloat op,
      EFloat augend,
      EContext ctx) {
      return MathValue.MultiplyAndAdd(this, op, augend, ctx);
    }

    public EFloat MultiplyAndSubtract(
      EFloat op,
      EFloat subtrahend,
      EContext ctx) {
      if (op == null) {
        throw new NullPointerException("op");
      }
      if (subtrahend == null) {
        throw new NullPointerException("subtrahend");
      }
      EFloat negated = subtrahend;
      if ((subtrahend.flags & BigNumberFlags.FlagNaN) == 0) {
        int newflags = subtrahend.flags ^ BigNumberFlags.FlagNegative;
        negated = new EFloat(
          subtrahend.unsignedMantissa,
          subtrahend.exponent,
          (byte)newflags);
      }
      return MathValue.MultiplyAndAdd(this, op, negated, ctx);
    }

    public EFloat Negate() {
      return new EFloat(
          this.unsignedMantissa,
          this.exponent,
          (byte)(this.flags ^ BigNumberFlags.FlagNegative));
    }

    public EFloat Negate(EContext context) {
      return MathValue.Negate(this, context);
    }

    public EFloat NextMinus(EContext ctx) {
      return MathValue.NextMinus(this, ctx);
    }

    public EFloat NextPlus(EContext ctx) {
      return MathValue.NextPlus(this, ctx);
    }

    public EFloat NextToward(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.NextToward(this, otherValue, ctx);
    }

    public EFloat Plus(EContext ctx) {
      return MathValue.Plus(this, ctx);
    }

    public EFloat Pow(EFloat exponent) {
      return this.Pow(exponent, null);
    }

    public EFloat Pow(EFloat exponent, EContext ctx) {
      return MathValue.Power(this, exponent, ctx);
    }

    public EFloat Pow(int exponentSmall, EContext ctx) {
      return this.Pow(EFloat.FromInt64(exponentSmall), ctx);
    }

    public EFloat Pow(int exponentSmall) {
      return this.Pow(EFloat.FromInt64(exponentSmall), null);
    }

    public EInteger Precision() {
      if (!this.isFinite()) {
        return EInteger.FromInt32(0);
      }
      return this.isZero() ? EInteger.FromInt32(1) :
        this.getUnsignedMantissa().GetSignedBitLengthAsEInteger();
    }

    public boolean IsInteger() {
      if (!this.isFinite()) {
        return false;
      }
      if (this.isZero() || this.getExponent().compareTo(0) >= 0) {
        return true;
      } else {
        EInteger absexp = this.getExponent().Abs();
        EInteger mant = this.getUnsignedMantissa();
        return mant.GetLowBitAsEInteger().compareTo(absexp) >= 0;
      }
    }

    public EFloat Quantize(
      EInteger desiredExponent,
      EContext ctx) {
      return this.Quantize(
          EFloat.Create(EInteger.FromInt32(1), desiredExponent),
          ctx);
    }

    public EFloat Quantize(
      int desiredExponentInt,
      EContext ctx) {
      return this.Quantize(
          EFloat.Create(1, desiredExponentInt),
          ctx);
    }

    public EFloat Quantize(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.Quantize(this, otherValue, ctx);
    }

    public EFloat Reduce(EContext ctx) {
      return MathValue.Reduce(this, ctx);
    }

    public EFloat Remainder(
      EFloat divisor,
      EContext ctx) {
      return MathValue.Remainder(this, divisor, ctx, true);
    }

    public EFloat RemainderNoRoundAfterDivide(
      EFloat divisor,
      EContext ctx) {
      return MathValue.Remainder(this, divisor, ctx, false);
    }

    public EFloat RemainderNaturalScale(
      EFloat divisor) {
      return this.RemainderNaturalScale(divisor, null);
    }

    public EFloat RemainderNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return this.Subtract(
        this.DivideToIntegerNaturalScale(divisor, null).Multiply(divisor, null),
        ctx);
    }

    public EFloat RemainderNear(
      EFloat divisor,
      EContext ctx) {
      return MathValue.RemainderNear(this, divisor, ctx);
    }

    public EFloat RoundToExponent(
      EInteger exponent,
      EContext ctx) {
      return MathValue.RoundToExponentSimple(this, exponent, ctx);
    }

    public EFloat RoundToExponent(
      int exponentSmall,
      EContext ctx) {
      return this.RoundToExponent(EInteger.FromInt32(exponentSmall), ctx);
    }

    public EFloat RoundToExponentExact(
      EInteger exponent,
      EContext ctx) {
      return MathValue.RoundToExponentExact(this, exponent, ctx);
    }

    public EFloat RoundToExponentExact(
      EInteger exponent,
      ERounding rounding) {
      return MathValue.RoundToExponentExact(
          this,
          exponent,
          EContext.Unlimited.WithRounding(rounding));
    }

    public EFloat RoundToExponentExact(
      int exponentSmall,
      EContext ctx) {
      return this.RoundToExponentExact(EInteger.FromInt32(exponentSmall), ctx);
    }

    public EFloat RoundToIntegerExact(EContext ctx) {
      return MathValue.RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    public EFloat RoundToIntegerNoRoundedFlag(EContext ctx) {
      return MathValue.RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

/**
 * @deprecated Renamed to RoundToIntegerExact.
 */
@Deprecated
    public EFloat RoundToIntegralExact(EContext ctx) {
      return MathValue.RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

/**
 * @deprecated Renamed to RoundToIntegerNoRoundedFlag.
 */
@Deprecated
    public EFloat RoundToIntegralNoRoundedFlag(EContext ctx) {
      return MathValue.RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

    public EFloat RoundToPrecision(EContext ctx) {
      return MathValue.RoundToPrecision(this, ctx);
    }

    public EFloat PreRound(EContext ctx) {
      return NumberUtility.PreRound(this, ctx, MathValue);
    }

    public EFloat ScaleByPowerOfTwo(int places) {
      return this.ScaleByPowerOfTwo(EInteger.FromInt32(places), null);
    }

    public EFloat ScaleByPowerOfTwo(int places, EContext ctx) {
      return this.ScaleByPowerOfTwo(EInteger.FromInt32(places), ctx);
    }

    public EFloat ScaleByPowerOfTwo(EInteger bigPlaces) {
      return this.ScaleByPowerOfTwo(bigPlaces, null);
    }

    public EFloat ScaleByPowerOfTwo(
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
      return new EFloat(
          this.unsignedMantissa,
          FastIntegerFixed.FromBig(bigExp),
          (byte)this.flags).RoundToPrecision(ctx);
    }

    public EFloat Sqrt(EContext ctx) {
      return MathValue.SquareRoot(this, ctx);
    }

/**
 * @deprecated Renamed to Sqrt.
 */
@Deprecated
    public EFloat SquareRoot(EContext ctx) {
      return MathValue.SquareRoot(this, ctx);
    }

    public EFloat Subtract(EFloat otherValue) {
      return this.Subtract(otherValue, null);
    }

    public EFloat Subtract(
      EFloat otherValue,
      EContext ctx) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      EFloat negated = otherValue;
      if ((otherValue.flags & BigNumberFlags.FlagNaN) == 0) {
        int newflags = otherValue.flags ^ BigNumberFlags.FlagNegative;
        negated = new EFloat(
          otherValue.unsignedMantissa,
          otherValue.exponent,
          (byte)newflags);
      }
      return this.Add(negated, ctx);
    }

    public double ToDouble() {
      long value = this.ToDoubleBits();
      return Double.longBitsToDouble(value);
    }

    public int ToSingleBits() {
      if (this.IsPositiveInfinity()) {
        return 0x7f800000;
      }
      if (this.IsNegativeInfinity()) {
        return (int)0xff800000;
      }
      if (this.IsNaN()) {
        int nan = 0x7f800000;
        if (this.isNegative()) {
          nan |= ((int)(1 << 31));
        }
        // IsQuietNaN(): the quiet bit for X86 at least
        // If signaling NaN and mantissa is 0: set 0x200000
        // bit to keep the mantissa from being zero
        if (this.IsQuietNaN()) {
          nan |= 0x400000;
        } else if (this.getUnsignedMantissa().isZero()) {
          nan |= 0x200000;
        }
        if (!this.getUnsignedMantissa().isZero()) {
          // Transfer diagnostic information
          EInteger bigdata = this.getUnsignedMantissa().Remainder(EInteger.FromInt64(0x400000));
          int intData = bigdata.ToInt32Checked();
          nan |= intData;
          if (intData == 0 && !this.IsQuietNaN()) {
            nan |= 0x200000;
          }
        }
        return nan;
      }
      EFloat thisValue = this;
      // System.out.println("beforeround=" +thisValue + " ["+
      // thisValue.getMantissa() + " " + thisValue.getExponent());
      // Check whether rounding can be avoided for common cases
      // where the value already fits a single
      if (!thisValue.isFinite() ||
        thisValue.unsignedMantissa.CompareToInt(0x1000000) >= 0 ||
        thisValue.exponent.CompareToInt(-95) < 0 ||
        thisValue.exponent.CompareToInt(95) > 0) {
        thisValue = this.RoundToPrecision(EContext.Binary32);
      }
      // System.out.println("afterround=" +thisValue + " ["+
      // thisValue.getMantissa() + " " + thisValue.getExponent());
      if (!thisValue.isFinite()) {
        return thisValue.ToSingleBits();
      }
      int intmant = thisValue.unsignedMantissa.ToInt32();
      if (thisValue.isNegative() && intmant == 0) {
        return (int)1 << 31;
      } else if (intmant == 0) {
        return 0;
      }
      int intBitLength = NumberUtility.BitLength(intmant);
      int expo = thisValue.exponent.ToInt32();
      boolean subnormal = false;
      if (intBitLength < 24) {
        int diff = 24 - intBitLength;
        expo -= diff;
        if (expo < -149) {
          // System.out.println("Diff changed from " + diff + " to " + (diff -
          // (-149 - expo)));
          diff -= -149 - expo;
          expo = -149;
          subnormal = true;
        }
        intmant <<= diff;
      }
      // System.out.println("intmant=" + intmant + " " + intBitLength +
      // " expo=" + expo +
      // " subnormal=" + subnormal);
      int smallmantissa = intmant & 0x7fffff;
      if (!subnormal) {
        smallmantissa |= (expo + 150) << 23;
      }
      if (this.isNegative()) {
        smallmantissa |= 1 << 31;
      }
      return smallmantissa;
    }

    public long ToDoubleBits() {
      if (this.IsPositiveInfinity()) {
        return (long)0x7ff0000000000000L;
      }
      if (this.IsNegativeInfinity()) {
        return (long)0xfff0000000000000L;
      }
      if (this.IsNaN()) {
        int[] nan = { 0, 0x7ff00000 };
        if (this.isNegative()) {
          nan[1] |= ((int)(1 << 31));
        }
        if (this.IsQuietNaN()) {
          // Quiet NaN is a NaN in which the highest bit of
          // the mantissa area is set
          nan[1] |= 0x80000;
        } else if (this.getUnsignedMantissa().isZero()) {
          // Set the 0x40000 bit to keep the mantissa from
          // being zero if this is a signaling NaN
          nan[1] |= 0x40000;
        }
        if (!this.getUnsignedMantissa().isZero()) {
          // Copy diagnostic information
          int[] words = FastInteger.GetLastWords(this.getUnsignedMantissa(), 2);
          nan[0] = words[0];
          nan[1] |= words[1] & 0x7ffff;
          if ((words[0] | (words[1] & 0x7ffff)) == 0 && !this.IsQuietNaN()) {
            // Set the 0x40000 bit to keep the mantissa from
            // being zero if this is a signaling NaN
            nan[1] |= 0x40000;
          }
        }
        long lret = (((long)nan[0]) & 0xffffffffL);
        lret |= (((long)nan[1]) << 32);
        /*
         System.out.println("lret={0:X8} {1:X8} {2:X}", nan[0], nan[1], lret);
        */ return lret;
      }
      EFloat thisValue = this;
      // Check whether rounding can be avoided for common cases
      // where the value already fits a double
      if (!thisValue.isFinite() ||
        thisValue.unsignedMantissa.CompareToInt64(1L << 52) >= 0 ||
        thisValue.exponent.CompareToInt(-900) < 0 ||
        thisValue.exponent.CompareToInt(900) > 0) {
        thisValue = this.RoundToPrecision(EContext.Binary64);
      }
      if (!thisValue.isFinite()) {
        return thisValue.ToDoubleBits();
      }
      long longmant = thisValue.unsignedMantissa.ToInt64();
      if (thisValue.isNegative() && longmant == 0) {
        return 1L << 63;
      } else if (longmant == 0) {
        return 0L;
      }
      // System.out.println("todouble -->" + this);
      long longBitLength = NumberUtility.BitLength(longmant);
      int expo = thisValue.exponent.ToInt32();
      boolean subnormal = false;
      if (longBitLength < 53) {
        int diff = 53 - (int)longBitLength;
        expo -= diff;
        if (expo < -1074) {
          // System.out.println("Diff changed from " + diff + " to " + (diff -
          // (-1074 - expo)));
          diff -= -1074 - expo;
          expo = -1074;
          subnormal = true;
        }
        longmant <<= diff;
      }
      // Clear the high bits where the exponent and sign are
      longmant &= 0xfffffffffffffL;
      if (!subnormal) {
        longmant |= (long)(expo + 1075) << 52;
      }
      if (thisValue.isNegative()) {
        longmant |= ((long)(1L << 63));
      }
      return longmant;
    }

    public EDecimal ToEDecimal() {
      return EDecimal.FromEFloat(this);
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
      return this.ToEDecimal().ToEngineeringString();
    }

/**
 * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal() {
      return EDecimal.FromEFloat(this);
    }

    public String ToPlainString() {
      return this.ToEDecimal().ToPlainString();
    }

    private String ToDebugString() {
      return "[" + this.getMantissa().ToRadixString(2) +
        "," + this.getMantissa().GetUnsignedBitLengthAsEInteger() +
        "," + this.getExponent() + "]";
    }

    public String ToShortestString(EContext ctx) {
      if (ctx == null || !ctx.getHasMaxPrecision()) {
        return this.toString();
      }
      if (this.IsNaN()) {
        return CreateNaN(
            this.getUnsignedMantissa(),
            this.IsSignalingNaN(),
            this.isNegative(),
            ctx).toString();
      }
      if (this.IsInfinity()) {
        return this.RoundToPrecision(ctx).toString();
      }
      EContext ctx2 = ctx.WithNoFlags();
      EFloat valueEfRnd = this.RoundToPrecision(ctx);
      if (valueEfRnd.IsInfinity()) {
        return valueEfRnd.toString();
      }
      if (this.isZero()) {
        return this.RoundToPrecision(ctx).toString();
      }
      // NOTE: The original EFloat is converted to decimal,
      // not the rounded version, to avoid double rounding issues
      EDecimal dec = this.ToEDecimal();
      if (ctx.getPrecision().compareTo(10) >= 0) {
        // Preround the decimal so the significand has closer to the
        // number of decimal digits of the maximum possible
        // decimal significand, to speed up further rounding
        EInteger roundedPrec = ctx.getPrecision().ShiftRight(1).Add(
            EInteger.FromInt32(3));
        EInteger dmant = dec.getUnsignedMantissa();
        EInteger dexp = dec.getExponent();
        boolean dneg = dec.isNegative();
        DigitShiftAccumulator dsa = new DigitShiftAccumulator(dmant, 0, 0);
        dsa.ShiftToDigits(FastInteger.FromBig(roundedPrec), null, false);
        dmant = dsa.getShiftedInt();
        dexp = dexp.Add(dsa.getDiscardedDigitCount().ToEInteger());
        if (dsa.getLastDiscardedDigit() != 0 || dsa.getOlderDiscardedDigits() != 0) {
          if (dmant.Remainder(10).ToInt32Checked() != 9) {
            dmant = dmant.Add(1);
          }
        }
        dec = EDecimal.Create(dmant, dexp);
        if (dneg) {
          dec = dec.Negate();
        }
      }
      boolean mantissaIsPowerOfTwo = this.getUnsignedMantissa().isPowerOfTwo();
      EInteger eprecision = EInteger.FromInt32(0);
      while (true) {
        EInteger nextPrecision = eprecision.Add(EInteger.FromInt32(1));
        EContext nextCtx = ctx2.WithBigPrecision(nextPrecision);
        EDecimal nextDec = dec.RoundToPrecision(nextCtx);
        EFloat newFloat = nextDec.ToEFloat(ctx2);
        // System.out.println("nextDec=" + nextDec);
        if (newFloat.compareTo(valueEfRnd) == 0) {
          if (mantissaIsPowerOfTwo && eprecision.signum() > 0) {
            nextPrecision = eprecision;
            nextCtx = ctx2.WithBigPrecision(nextPrecision);

            EDecimal nextDec2 = dec.RoundToPrecision(nextCtx);
            nextDec2 = nextDec2.NextPlus(nextCtx);
            newFloat = nextDec2.ToEFloat(ctx2);
            if (newFloat.compareTo(valueEfRnd) == 0) {
              nextDec = nextDec2;
            }
          }
          return (nextDec.getExponent().signum() > 0 &&
              nextDec.Abs().compareTo(EDecimal.FromInt32(10000000)) < 0) ?
            nextDec.ToPlainString() : nextDec.toString();
        }
        eprecision = nextPrecision;
      }
    }

    public float ToSingle() {
      int sb = this.ToSingleBits();
      return Float.intBitsToFloat(sb);
    }

    @Override public String toString() {
      return EDecimal.FromEFloat(this).toString();
    }

    public EFloat Ulp() {
      return (!this.isFinite()) ? EFloat.One :
        EFloat.Create(EInteger.FromInt32(1), this.getExponent());
    }

    static EFloat CreateWithFlags(
      long mantissa,
      EInteger exponent,
      int flags) {
      if (exponent == null) {
        throw new NullPointerException("exponent");
      }
      return new EFloat(
          FastIntegerFixed.FromInt64(mantissa).Abs(),
          FastIntegerFixed.FromBig(exponent),
          (byte)flags);
    }

    static EFloat CreateWithFlags(
      EInteger mantissa,
      EInteger exponent,
      int flags) {
      if (mantissa == null) {
        throw new NullPointerException("mantissa");
      }
      if (exponent == null) {
        throw new NullPointerException("exponent");
      }
      return new EFloat(
          FastIntegerFixed.FromBig(mantissa).Abs(),
          FastIntegerFixed.FromBig(exponent),
          (byte)flags);
    }

    private EInteger ToEIntegerInternal(boolean exact) {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      if (this.isZero()) {
        return EInteger.FromInt32(0);
      }
      int expsign = this.getExponent().signum();
      if (expsign == 0) {
        // Integer
        return this.getMantissa();
      }
      if (expsign > 0) {
        // Integer with trailing zeros
        EInteger curexp = this.getExponent();
        EInteger bigmantissa = this.getMantissa();
        if (bigmantissa.isZero()) {
          return bigmantissa;
        }
        boolean neg = bigmantissa.signum() < 0;
        if (neg) {
          bigmantissa = bigmantissa.Negate();
        }
        bigmantissa = bigmantissa.ShiftLeft(curexp);
        if (neg) {
          bigmantissa = bigmantissa.Negate();
        }
        return bigmantissa;
      } else {
        if (exact && !this.unsignedMantissa.isEvenNumber()) {
          // Mantissa is odd and will have to shift a nonzero
          // number of bits, so can't be an exact integer
          throw new ArithmeticException("Not an exact integer");
        }
        FastInteger bigexponent = FastInteger.FromBig(this.getExponent()).Negate();
        EInteger bigmantissa = this.getUnsignedMantissa();
        BitShiftAccumulator acc = new BitShiftAccumulator(bigmantissa, 0, 0);
        acc.ShiftRight(bigexponent);
        if (exact && (acc.getLastDiscardedDigit() != 0 || acc.getOlderDiscardedDigits() !=
            0)) {
          // Some digits were discarded
          throw new ArithmeticException("Not an exact integer");
        }
        bigmantissa = acc.getShiftedInt();
        if (this.isNegative()) {
          bigmantissa = bigmantissa.Negate();
        }
        return bigmantissa;
      }
    }

    private static EInteger PowerOfRadixBitsLowerBound(EInteger e) {
      return e.Abs();
    }
    private static EInteger PowerOfRadixBitsUpperBound(EInteger e) {
      return e.Abs().Add(1);
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
        // x * 2^y
        long imantbits = mant.GetSignedBitLengthAsInt64();
        if (imantbits >= maxBitLength) {
          throw new ArithmeticException("Value out of range");
        }
        if (exp.compareTo(0x100000) < 0 && imantbits < 0x100000) {
          // Lower bound of bit count in 2^exp based on ln(2^exp)/ln(2)
          long expBitsLowerBound = exp.ToInt64Checked();
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
        // x * 2^-y. Check for trivial overflow cases before
        // running ToEInteger.
        exp = exp.Abs();
        long imantbits = mant.GetSignedBitLengthAsInt64();
        if (exp.compareTo(0x100000) < 0 && imantbits < 0x100000) {
          long expBitsUpperBound = exp.ToInt64Checked() + 1;
          long expBitsLowerBound = exp.ToInt64Checked();
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
        } else if (imantbits < 0x100000 && exp.compareTo(0x200000) >= 0) {
          // (mant / 2^exp) would be less than one, so not exact
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

    private static final class BinaryMathHelper implements IRadixMathHelper<EFloat> {
      public int GetRadix() {
        return 2;
      }

      public int GetSign(EFloat value) {
        return value.signum();
      }

      public EInteger GetMantissa(EFloat value) {
        return value.unsignedMantissa.ToEInteger();
      }

      public EInteger GetExponent(EFloat value) {
        return value.exponent.ToEInteger();
      }

      public FastInteger GetDigitLength(EInteger ei) {
        return FastInteger.FromBig(ei.GetUnsignedBitLengthAsEInteger());
      }

      public FastIntegerFixed GetMantissaFastInt(EFloat value) {
        return value.unsignedMantissa;
      }

      public FastIntegerFixed GetExponentFastInt(EFloat value) {
        return value.exponent;
      }

      public IShiftAccumulator CreateShiftAccumulatorWithDigits(
        EInteger bigint,
        int lastDigit,
        int olderDigits) {
        return new BitShiftAccumulator(bigint, lastDigit, olderDigits);
      }

      public IShiftAccumulator CreateShiftAccumulatorWithDigitsFastInt(
        FastIntegerFixed fastInt,
        int lastDigit,
        int olderDigits) {
        if (fastInt.CanFitInInt32()) {
          return new BitShiftAccumulator(
              fastInt.ToInt32(),
              lastDigit,
              olderDigits);
        } else {
          return new BitShiftAccumulator(
              fastInt.ToEInteger(),
              lastDigit,
              olderDigits);
        }
      }

      public FastInteger DivisionShift(EInteger num, EInteger den) {
        if (den.isZero()) {
          return null;
        }
        if (den.GetUnsignedBit(0) && den.compareTo(EInteger.FromInt32(1)) != 0) {
          return null;
        }
        EInteger valueELowBit = den.GetLowBitAsEInteger();
        return
          den.GetUnsignedBitLengthAsEInteger().equals(valueELowBit.Add(1)) ?
          FastInteger.FromBig(valueELowBit) : null;
      }

      public EInteger MultiplyByRadixPower(
        EInteger bigint,
        FastInteger power) {
        EInteger tmpbigint = bigint;
        if (power.signum() <= 0) {
          return tmpbigint;
        }
        if (tmpbigint.signum() < 0) {
          tmpbigint = tmpbigint.Negate();
          tmpbigint = power.ShiftEIntegerLeftByThis(tmpbigint);
          tmpbigint = tmpbigint.Negate();
          return tmpbigint;
        }
        return power.ShiftEIntegerLeftByThis(tmpbigint);
      }

      public FastIntegerFixed MultiplyByRadixPowerFastInt(
        FastIntegerFixed fbigint,
        FastIntegerFixed fpower) {
        if (fpower.signum() <= 0) {
          return fbigint;
        }
        EInteger ei = this.MultiplyByRadixPower(
            fbigint.ToEInteger(),
            FastInteger.FromBig(fpower.ToEInteger()));
        return FastIntegerFixed.FromBig(ei);
      }

      public int GetFlags(EFloat value) {
        return value.flags;
      }

      public EFloat CreateNewWithFlags(
        EInteger mantissa,
        EInteger exponent,
        int flags) {
        return new EFloat(FastIntegerFixed.FromBig(mantissa),
            FastIntegerFixed.FromBig(exponent),
            (byte)flags);
      }

      public EFloat CreateNewWithFlagsFastInt(
        FastIntegerFixed fmantissa,
        FastIntegerFixed fexponent,
        int flags) {
        return new EFloat(
            fmantissa,
            fexponent,
            (byte)flags);
      }

      public int GetArithmeticSupport() {
        return BigNumberFlags.FiniteAndNonFinite;
      }

      public EFloat ValueOf(int val) {
        return FromInt32(val);
      }
    }

    public EFloat Increment() {
      return this.Add(1);
    }

    public EFloat Decrement() {
      return this.Subtract(1);
    }

    // Begin integer conversions

    public byte ToByteChecked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((byte)0) :
        this.ToEInteger().ToByteChecked();
    }

    public byte ToByteUnchecked() {
      return this.isFinite() ? this.ToEInteger().ToByteUnchecked() : (byte)0;
    }

    public byte ToByteIfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((byte)0) : this.ToEIntegerIfExact().ToByteChecked();
    }

    public static EFloat FromByte(byte inputByte) {
      int val = ((int)inputByte) & 0xff;
      return FromInt32(val);
    }

    public short ToInt16Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((short)0) :
        this.ToEInteger().ToInt16Checked();
    }

    public short ToInt16Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt16Unchecked() : (short)0;
    }

    public short ToInt16IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((short)0) :
        this.ToEIntegerIfExact().ToInt16Checked();
    }

    public static EFloat FromInt16(short inputInt16) {
      int val = (int)inputInt16;
      return FromInt32(val);
    }

    public int ToInt32Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((int)0) :
        this.ToEInteger().ToInt32Checked();
    }

    public int ToInt32Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt32Unchecked() : (int)0;
    }

    public int ToInt32IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((int)0) : this.ToEIntegerIfExact().ToInt32Checked();
    }

    public static EFloat FromBoolean(boolean boolValue) {
      return boolValue ? EFloat.One : EFloat.Zero;
    }

    public static EFloat FromInt32(int inputInt32) {
      if (inputInt32 >= CacheFirst && inputInt32 <= CacheLast) {
        return Cache[inputInt32 - CacheFirst];
      }
      if (inputInt32 == Integer.MIN_VALUE) {
        return FromEInteger(EInteger.FromInt32(inputInt32));
      }
      return new EFloat(
          FastIntegerFixed.FromInt32(Math.abs(inputInt32)),
          FastIntegerFixed.Zero,
          (byte)((inputInt32 < 0) ? BigNumberFlags.FlagNegative : 0));
    }

    public long ToInt64Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? 0L :
        this.ToEInteger().ToInt64Checked();
    }

    public long ToInt64Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt64Unchecked() : 0L;
    }

    public long ToInt64IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? 0L : this.ToEIntegerIfExact().ToInt64Checked();
    }

    public static EFloat FromInt64(long inputInt64) {
      if (inputInt64 >= CacheFirst && inputInt64 <= CacheLast) {
        return Cache[(int)inputInt64 - CacheFirst];
      }
      if (inputInt64 == Long.MIN_VALUE) {
        return FromEInteger(EInteger.FromInt64(inputInt64));
      }
      return new EFloat(
          FastIntegerFixed.FromInt64(Math.abs(inputInt64)),
          FastIntegerFixed.Zero,
          (byte)((inputInt64 < 0) ? BigNumberFlags.FlagNegative : 0));
    }

    // End integer conversions
  }
