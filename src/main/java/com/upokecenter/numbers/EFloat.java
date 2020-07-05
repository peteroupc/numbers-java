package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

  /**
   * Represents an arbitrary-precision binary floating-point number. (The "E"
   *  stands for "extended", meaning that instances of this class can be
   * values other than numbers proper, such as infinity and not-a-number.)
   * Each number consists of an integer significand and an integer
   * exponent, both arbitrary-precision. The value of the number equals
   * significand * 2^exponent. This class also supports values for negative
   * zero, not-a-number (NaN) values, and infinity. <p>Passing a signaling
   * NaN to any arithmetic operation shown here will signal the flag
   * FlagInvalid and return a quiet NaN, even if another operand to that
   * operation is a quiet NaN, unless the operation's documentation
   * expressly states that another result happens when a signaling NaN is
   * passed to that operation.</p> <p>Passing a quiet NaN to any arithmetic
   * operation shown here will return a quiet NaN, unless the operation's
   * documentation expressly states that another result happens when a
   * quiet NaN is passed to that operation.</p> <p>Unless noted otherwise,
   * passing a null arbitrary-precision binary floating-point number
   * argument to any method here will throw an exception.</p> <p>When an
   * arithmetic operation signals the flag FlagInvalid, FlagOverflow, or
   * FlagDivideByZero, it will not throw an exception too, unless the
   * operation's trap is enabled in the arithmetic context (see EContext's
   * Traps property).</p> <p>An arbitrary-precision binary floating-point
   * number value can be serialized in one of the following ways:</p> <ul>
   * <li>By calling the toString() method. However, not all strings can be
   * converted back to an arbitrary-precision binary floating-point number
   * without loss, especially if the string has a fractional part.</li>
   * <li>By calling the UnsignedMantissa, Exponent, and IsNegative
   * properties, and calling the IsInfinity, IsQuietNaN, and IsSignalingNaN
   * methods. The return values combined will uniquely identify a
   * particular arbitrary-precision binary floating-point number
   * value.</li></ul> <p>If an operation requires creating an intermediate
   * value that might be too big to fit in memory (or might require more
   * than 2 gigabytes of memory to store -- due to the current use of a
   * 32-bit integer internally as a length), the operation may signal an
   * invalid-operation flag and return not-a-number (NaN). In certain rare
   * cases, the compareTo method may throw OutOfMemoryError (called
   * OutOfMemoryError in Java) in the same circumstances.</p> <p><b>Thread
   * safety</b></p> <p>Instances of this class are immutable, so they are
   * inherently safe for use by multiple threads. Multiple instances of
   * this object with the same properties are interchangeable, so they
   *  should not be compared using the "==" operator (which might only check
   * if each side of the operator is the same instance).</p>
   * <p><b>Comparison considerations</b></p> <p>This class's natural
   * ordering (under the compareTo method) is not consistent with the
   * Equals method. This means that two values that compare as equal under
   * the compareTo method might not be equal under the Equals method. The
   * compareTo method compares the mathematical values of the two instances
   * passed to it (and considers two different NaN values as equal), while
   * two instances with the same mathematical value, but different
   * exponents, will be considered unequal under the Equals method.</p>
   * <p><b>Security note</b></p> <p>It is not recommended to implement
   * security-sensitive algorithms using the methods in this class, for
   * several reasons:</p> <ul> <li><code>EFloat</code> objects are immutable, so
   * they can't be modified, and the memory they occupy is not guaranteed
   * to be cleared in a timely fashion due to garbage collection. This is
   * relevant for applications that use many-bit-long numbers as secret
   * parameters.</li> <li>The methods in this class (especially those that
   *  involve arithmetic) are not guaranteed to be "constant-time"
   * (non-data-dependent) for all relevant inputs. Certain attacks that
   * involve encrypted communications have exploited the timing and other
   * aspects of such communications to derive keying material or cleartext
   * indirectly.</li></ul> <p>Applications should instead use dedicated
   * security libraries to handle big numbers in security-sensitive
   * algorithms.</p> <p><b>Reproducibility note</b></p> <p>See the
   * reproducibility note in the EDecimal class's documentation.</p>
   */

  public final class EFloat implements Comparable<EFloat> {
    //-----------------------------------------------
    private static final int CacheFirst = -24;
    private static final int CacheLast = 128;

    /**
     * A not-a-number value.
     */

    public static final EFloat NaN = new EFloat(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)BigNumberFlags.FlagQuietNaN);

    /**
     * Negative infinity, less than any other number.
     */

    public static final EFloat NegativeInfinity = new EFloat(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)(BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative));

    /**
     * Represents the number negative zero.
     */

    public static final EFloat NegativeZero = new EFloat(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)BigNumberFlags.FlagNegative);

    /**
     * Represents the number 1.
     */

    public static final EFloat One = new EFloat(
      FastIntegerFixed.One,
      FastIntegerFixed.Zero,
      (byte)0);

    /**
     * Positive infinity, greater than any other number.
     */

    public static final EFloat PositiveInfinity = new EFloat(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)BigNumberFlags.FlagInfinity);

    /**
     * A not-a-number value that signals an invalid operation flag when it's passed
     * as an argument to any arithmetic operation in arbitrary-precision
     * binary floating-point number.
     */

    public static final EFloat SignalingNaN = new EFloat(
      FastIntegerFixed.Zero,
      FastIntegerFixed.Zero,
      (byte)BigNumberFlags.FlagSignalingNaN);

    /**
     * Represents the number 10.
     */

    public static final EFloat Ten = new EFloat(
      FastIntegerFixed.FromInt32(10),
      FastIntegerFixed.Zero,
      (byte)0);

    /**
     * Represents the number 0.
     */

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

    /**
     * Gets this object's exponent. This object's value will be an integer if the
     * exponent is positive or zero.
     * @return This object's exponent. This object's value will be an integer if
     * the exponent is positive or zero.
     */
    public final EInteger getExponent() {
        return this.exponent.ToEInteger();
      }

    /**
     * Gets a value indicating whether this object is finite (not infinity or
     * not-a-number, NaN).
     * @return {@code true} if this object is finite (not infinity or not-a-number,
     * NaN); otherwise, {@code false}.
     */
    public final boolean isFinite() {
        return (this.flags & (BigNumberFlags.FlagInfinity |
              BigNumberFlags.FlagNaN)) == 0;
      }

    /**
     * Gets a value indicating whether this object is negative, including negative
     * zero.
     * @return {@code true} if this object is negative, including negative zero;
     * otherwise, {@code false}.
     */
    public final boolean isNegative() {
        return (this.flags & BigNumberFlags.FlagNegative) != 0;
      }

    /**
     * Gets a value indicating whether this object's value equals 0.
     * @return {@code true} if this object's value equals 0; otherwise, {@code
     * false}. {@code true} if this object's value equals 0; otherwise,
     * {@code false}.
     */
    public final boolean isZero() {
        return ((this.flags & BigNumberFlags.FlagSpecial) == 0) &&
          this.unsignedMantissa.isValueZero();
      }

    /**
     * Gets this object's unscaled value, or significand, and makes it negative if
     * this object is negative. If this value is not-a-number (NaN), that
     *  value's absolute value is the NaN's "payload" (diagnostic
     * information).
     * @return This object's unscaled value. Will be negative if this object's
     * value is negative (including a negative NaN).
     */
    public final EInteger getMantissa() {
        return this.isNegative() ? this.unsignedMantissa.ToEInteger().Negate() :
          this.unsignedMantissa.ToEInteger();
      }

    /**
     * Gets this value's sign: -1 if negative; 1 if positive; 0 if zero.
     * @return This value's sign: -1 if negative; 1 if positive; 0 if zero.
     */
    public final int signum() {
        return (((this.flags & BigNumberFlags.FlagSpecial) == 0) &&
            this.unsignedMantissa.isValueZero()) ? 0 :
          (((this.flags & BigNumberFlags.FlagNegative) != 0) ? -1 : 1);
      }

    /**
     * Gets the absolute value of this object's unscaled value, or significand. If
     *  this value is not-a-number (NaN), that value is the NaN's "payload"
     * (diagnostic information).
     * @return The absolute value of this object's unscaled value.
     */
    public final EInteger getUnsignedMantissa() {
        return this.unsignedMantissa.ToEInteger();
      }

    /**
     * Creates a copy of this arbitrary-precision binary number.
     * @return An arbitrary-precision binary floating-point number.
     */
    public EFloat Copy() {
      return new EFloat(this.unsignedMantissa, this.exponent, this.flags);
    }

    /**
     * Returns an arbitrary-precision number with the value
     * <code>exponent*2^significand</code>.
     * @param mantissaSmall Desired value for the significand.
     * @param exponentSmall Desired value for the exponent.
     * @return An arbitrary-precision binary number.
     */
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

    /**
     * Returns an arbitrary-precision number with the value
     * <code>exponent*2^significand</code>.
     * @param mantissa Desired value for the significand.
     * @param exponentSmall Desired value for the exponent.
     * @return An arbitrary-precision binary number.
     * @throws NullPointerException The parameter {@code mantissa} is null.
     */
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

    /**
     * Returns an arbitrary-precision number with the value
     * <code>exponent*2^significand</code>.
     * @param mantissa Desired value for the significand.
     * @param exponentLong Desired value for the exponent.
     * @return An arbitrary-precision binary number.
     * @throws NullPointerException The parameter {@code mantissa} is null.
     */
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

    /**
     * Returns an arbitrary-precision number with the value
     * <code>exponent*2^significand</code>.
     * @param mantissa Desired value for the significand.
     * @param exponent Desired value for the exponent.
     * @return An arbitrary-precision binary number.
     * @throws NullPointerException The parameter {@code mantissa} or {@code
     * exponent} is null.
     */
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

    /**
     * Returns an arbitrary-precision number with the value
     * <code>exponent*2^significand</code>.
     * @param mantissaLong Desired value for the significand.
     * @param exponentSmall Desired value for the exponent.
     * @return An arbitrary-precision binary number.
     */
    public static EFloat Create(
      long mantissaLong,
      int exponentSmall) {
      return Create(mantissaLong, (long)exponentSmall);
    }

    /**
     * Returns an arbitrary-precision number with the value
     * <code>exponent*2^significand</code>.
     * @param mantissaLong Desired value for the significand.
     * @param exponentLong Desired value for the exponent.
     * @return An arbitrary-precision binary number.
     */
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

    /**
     * Creates a not-a-number arbitrary-precision binary number.
     * @param diag An integer, 0 or greater, to use as diagnostic information
     * associated with this object. If none is needed, should be zero. To
     * get the diagnostic information from another arbitrary-precision
     * binary floating-point number, use that object's {@code
     * UnsignedMantissa} property.
     * @return A quiet not-a-number.
     */
    public static EFloat CreateNaN(EInteger diag) {
      return CreateNaN(diag, false, false, null);
    }

    /**
     * Creates a not-a-number arbitrary-precision binary number.
     * @param diag An integer, 0 or greater, to use as diagnostic information
     * associated with this object. If none is needed, should be zero. To
     * get the diagnostic information from another arbitrary-precision
     * binary floating-point number, use that object's {@code
     * UnsignedMantissa} property.
     * @param signaling Whether the return value will be signaling (true) or quiet
     * (false).
     * @param negative Whether the return value is negative.
     * @param ctx An arithmetic context to control the precision (in binary digits)
     * of the diagnostic information. The rounding and exponent range of
     * this context will be ignored. Can be null. The only flag that can be
     * signaled in this context is FlagInvalid, which happens if diagnostic
     * information needs to be truncated and too much memory is required to
     * do so.
     * @return An arbitrary-precision binary number.
     * @throws NullPointerException The parameter {@code diag} is null or is less
     * than 0.
     */
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

    /**
     * Creates a binary floating-point number from a 64-bit floating-point number
     * encoded in the IEEE 754 binary64 format. This method computes the
     * exact value of the floating point number, not an approximation, as
     * is often the case by converting the floating point number to a
     * string first.
     * @param dblBits The parameter {@code dblBits} is a 64-bit signed integer.
     * @return A binary floating-point number with the same value as the
     * floating-point number encoded in {@code dblBits}.
     */
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

    /**
     * Creates a binary floating-point number from a 32-bit floating-point number.
     * This method computes the exact value of the floating point number,
     * not an approximation, as is often the case by converting the
     * floating point number to a string first.
     * @param flt The parameter {@code flt} is a 64-bit floating-point number.
     * @return A binary floating-point number with the same value as {@code flt}.
     */
    public static EFloat FromSingle(float flt) {
      return FromSingleBits(
          Float.floatToRawIntBits(flt));
    }

    /**
     * Creates a binary floating-point number from a 64-bit floating-point number.
     * This method computes the exact value of the floating point number,
     * not an approximation, as is often the case by converting the
     * floating point number to a string first.
     * @param dbl The parameter {@code dbl} is a 64-bit floating-point number.
     * @return A binary floating-point number with the same value as {@code dbl}.
     */
    public static EFloat FromDouble(double dbl) {
      long lvalue = Double.doubleToRawLongBits(dbl);
      return FromDoubleBits(lvalue);
    }

    /**
     * Converts an arbitrary-precision integer to the same value as a binary
     * floating-point number.
     * @param bigint An arbitrary-precision integer.
     * @return An arbitrary-precision binary floating-point number.
     */
    public static EFloat FromEInteger(EInteger bigint) {
      return EFloat.Create(bigint, (int)0);
    }

    /**
     * Creates a binary floating-point number from a 32-bit floating-point number
     * encoded in the IEEE 754 binary32 format. This method computes the
     * exact value of the floating point number, not an approximation, as
     * is often the case by converting the floating point number to a
     * string first.
     * @param value A 32-bit binary floating-point number encoded in the IEEE 754
     * binary32 format.
     * @return A binary floating-point number with the same floating-point value as
     * {@code value}.
     */
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

    /**
     * Creates a binary floating-point number from a text string that represents a
     * number. Note that if the string contains a negative exponent, the
     * resulting value might not be exact, in which case the resulting
     * binary floating-point number will be an approximation of this
     * decimal number's value. <p>The format of the string generally
     *  consists of:</p> <ul> <li>An optional plus sign ("+" , U+002B) or
     *  minus sign ("-", U+002D) (if '-' , the value is negative.)</li>
     *  <li>One or more digits, with a single optional decimal point (".",
     * U+002E) before or after those digits or between two of them. These
     * digits may begin with any number of zeros.</li> <li>Optionally,
     *  "E+"/"e+" (positive exponent) or "E-"/"e-" (negative exponent) plus
     * one or more digits specifying the exponent (these digits may begin
     * with any number of zeros).</li></ul> <p>The string can also be
     *  "-INF", "-Infinity", "Infinity", "INF", quiet NaN ("NaN") followed
     * by any number of digits (these digits may begin with any number of
     *  zeros), or signaling NaN ("sNaN") followed by any number of digits
     * (these digits may begin with any number of zeros), all where the
     * letters can be any combination of basic upper-case and/or basic
     * lower-case letters.</p> <p>All characters mentioned above are the
     * corresponding characters in the Basic Latin range. In particular,
     * the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
     * string is not allowed to contain white space characters, including
     * spaces.</p>
     * @param str The parameter {@code str} is a text string.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If HasFlags of the context is true,
     * will also store the flags resulting from the operation (the flags
     * are in addition to the pre-existing flags). Can be null, in which
     * case the precision is unlimited. Note that providing a context is
     * often much faster than creating an EDecimal without a context then
     * calling ToEFloat on that EDecimal, especially if the context
     * specifies a precision limit and exponent range.
     * @return The parsed number, converted to arbitrary-precision binary
     * floating-point number.
     * @throws NullPointerException The parameter {@code str} is null.
     * @throws NumberFormatException The portion given of {@code str} is not a correctly
     * formatted number string; or either {@code offset} or {@code length}
     * is less than 0 or greater than {@code str} 's length, or {@code str}
     * 's length minus {@code offset} is less than {@code length}.
     */
    public static EFloat FromString(
      String str,
      int offset,
      int length,
      EContext ctx) {
      return EFloatTextString.FromString(str, offset, length, ctx);
    }

    /**
     * Creates a binary floating-point number from a text string that represents a
     * number, using an unlimited precision context. For more information,
     * see the <code>FromString(string, int, int, EContext)</code> method.
     * @param str A text string to convert to a binary floating-point number.
     * @return The parsed number, converted to arbitrary-precision binary
     * floating-point number.
     * @throws NullPointerException The parameter {@code str} is null.
     * @throws NumberFormatException The portion given of {@code str} is not a correctly
     * formatted number string.
     */
    public static EFloat FromString(String str) {
      return FromString(str, 0, str == null ? 0 : str.length(), null);
    }

    /**
     * Creates a binary floating-point number from a text string that represents a
     * number. For more information, see the <code>FromString(string, int,
     * int, EContext)</code> method.
     * @param str A text string to convert to a binary floating-point number.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If HasFlags of the context is true,
     * will also store the flags resulting from the operation (the flags
     * are in addition to the pre-existing flags). Can be null, in which
     * case the precision is unlimited. Note that providing a context is
     * often much faster than creating an EDecimal without a context then
     * calling ToEFloat on that EDecimal, especially if the context
     * specifies a precision limit and exponent range.
     * @return The parsed number, converted to arbitrary-precision binary
     * floating-point number.
     * @throws NullPointerException The parameter {@code str} is null.
     */
    public static EFloat FromString(String str, EContext ctx) {
      return FromString(str, 0, str == null ? 0 : str.length(), ctx);
    }

    /**
     * Creates a binary floating-point number from a text string that represents a
     * number. For more information, see the <code>FromString(string, int,
     * int, EContext)</code> method.
     * @param str The parameter {@code str} is a text string.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @return An arbitrary-precision binary floating-point number.
     * @throws T:IllegalArgumentException Either {@code offset} or {@code length}
     * is less than 0 or greater than {@code str} 's length, or {@code str}
     * 's length minus {@code offset} is less than {@code length}.
     * @throws NullPointerException The parameter {@code str} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code str} 's length, or {@code str} 's
     * length minus {@code offset} is less than {@code length}.
     */
    public static EFloat FromString(String str, int offset, int length) {
      return FromString(str, offset, length, null);
    }

    /**
     * Creates a binary floating-point number from a sequence of <code>char</code> s that
     * represents a number. Note that if the sequence contains a negative
     * exponent, the resulting value might not be exact, in which case the
     * resulting binary floating-point number will be an approximation of
     * this decimal number's value. <p>The format of the sequence generally
     *  consists of:</p> <ul> <li>An optional plus sign ("+" , U+002B) or
     *  minus sign ("-", U+002D) (if '-' , the value is negative.)</li>
     *  <li>One or more digits, with a single optional decimal point (".",
     * U+002E) before or after those digits or between two of them. These
     * digits may begin with any number of zeros.</li> <li>Optionally,
     *  "E+"/"e+" (positive exponent) or "E-"/"e-" (negative exponent) plus
     * one or more digits specifying the exponent (these digits may begin
     * with any number of zeros).</li></ul> <p>The sequence can also be
     *  "-INF", "-Infinity", "Infinity", "INF", quiet NaN ("NaN") followed
     * by any number of digits (these digits may begin with any number of
     *  zeros), or signaling NaN ("sNaN") followed by any number of digits
     * (these digits may begin with any number of zeros), all where the
     * letters can be any combination of basic upper-case and/or basic
     * lower-case letters.</p> <p>All characters mentioned above are the
     * corresponding characters in the Basic Latin range. In particular,
     * the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
     * sequence is not allowed to contain white space characters, including
     * spaces.</p>
     * @param chars A sequence of {@code char} s to convert to a binary
     * floating-point number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code chars} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * chars} (but not more than {@code chars} 's length).
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If HasFlags of the context is true,
     * will also store the flags resulting from the operation (the flags
     * are in addition to the pre-existing flags). Can be null, in which
     * case the precision is unlimited. Note that providing a context is
     * often much faster than creating an EDecimal without a context then
     * calling ToEFloat on that EDecimal, especially if the context
     * specifies a precision limit and exponent range.
     * @return The parsed number, converted to arbitrary-precision binary
     * floating-point number.
     * @throws NullPointerException The parameter {@code chars} is null.
     * @throws NumberFormatException The portion given of {@code chars} is not a
     * correctly formatted number sequence; or either {@code offset} or
     * {@code length} is less than 0 or greater than {@code chars} 's
     * length, or {@code chars} 's length minus {@code offset} is less than
     * {@code length}.
     */
    public static EFloat FromString(
      char[] chars,
      int offset,
      int length,
      EContext ctx) {
      return EFloatCharArrayString.FromString(chars, offset, length, ctx);
    }

    /**
     * Creates a binary floating-point number from a sequence of <code>char</code> s that
     * represents a number, using an unlimited precision context. For more
     * information, see the <code>FromString(string, int, int, EContext)</code>
     * method.
     * @param chars A sequence of {@code char} s to convert to a binary
     * floating-point number.
     * @return The parsed number, converted to arbitrary-precision binary
     * floating-point number.
     * @throws NullPointerException The parameter {@code chars} is null.
     * @throws NumberFormatException The portion given of {@code chars} is not a
     * correctly formatted number sequence.
     */
    public static EFloat FromString(char[] chars) {
      return FromString(chars, 0, chars == null ? 0 : chars.length, null);
    }

    /**
     * Creates a binary floating-point number from a sequence of <code>char</code> s that
     * represents a number. For more information, see the
     * <code>FromString(string, int, int, EContext)</code> method.
     * @param chars A sequence of {@code char} s to convert to a binary
     * floating-point number.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If HasFlags of the context is true,
     * will also store the flags resulting from the operation (the flags
     * are in addition to the pre-existing flags). Can be null, in which
     * case the precision is unlimited. Note that providing a context is
     * often much faster than creating an EDecimal without a context then
     * calling ToEFloat on that EDecimal, especially if the context
     * specifies a precision limit and exponent range.
     * @return The parsed number, converted to arbitrary-precision binary
     * floating-point number.
     * @throws NullPointerException The parameter {@code chars} is null.
     */
    public static EFloat FromString(char[] chars, EContext ctx) {
      return FromString(chars, 0, chars == null ? 0 : chars.length, ctx);
    }

    /**
     * Creates a binary floating-point number from a sequence of <code>char</code> s that
     * represents a number. For more information, see the
     * <code>FromString(string, int, int, EContext)</code> method.
     * @param chars A sequence of {@code char} s to convert to a binary
     * floating-point number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code chars} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * chars} (but not more than {@code chars} 's length).
     * @return An arbitrary-precision binary floating-point number.
     * @throws NullPointerException The parameter {@code chars} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code chars} 's length, or {@code chars} 's
     * length minus {@code offset} is less than {@code length}.
     */
    public static EFloat FromString(char[] chars, int offset, int length) {
      return FromString(chars, offset, length, null);
    }

    /**
     * Creates a binary floating-point number from a sequence of bytes that
     * represents a number. Note that if the sequence contains a negative
     * exponent, the resulting value might not be exact, in which case the
     * resulting binary floating-point number will be an approximation of
     * this decimal number's value. <p>The format of the sequence generally
     *  consists of:</p> <ul> <li>An optional plus sign ("+" , U+002B) or
     *  minus sign ("-", U+002D) (if '-' , the value is negative.)</li>
     *  <li>One or more digits, with a single optional decimal point (".",
     * U+002E) before or after those digits or between two of them. These
     * digits may begin with any number of zeros.</li> <li>Optionally,
     *  "E+"/"e+" (positive exponent) or "E-"/"e-" (negative exponent) plus
     * one or more digits specifying the exponent (these digits may begin
     * with any number of zeros).</li></ul> <p>The sequence can also be
     *  "-INF", "-Infinity", "Infinity", "INF", quiet NaN ("NaN") followed
     * by any number of digits (these digits may begin with any number of
     *  zeros), or signaling NaN ("sNaN") followed by any number of digits
     * (these digits may begin with any number of zeros), all where the
     * letters can be any combination of basic upper-case and/or basic
     * lower-case letters.</p> <p>All characters mentioned above are the
     * corresponding characters in the Basic Latin range. In particular,
     * the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
     * sequence is not allowed to contain white space characters, including
     * spaces.</p>
     * @param bytes A sequence of bytes to convert to a binary floating-point
     * number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code bytes} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * bytes} (but not more than {@code bytes} 's length).
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If HasFlags of the context is true,
     * will also store the flags resulting from the operation (the flags
     * are in addition to the pre-existing flags). Can be null, in which
     * case the precision is unlimited. Note that providing a context is
     * often much faster than creating an EDecimal without a context then
     * calling ToEFloat on that EDecimal, especially if the context
     * specifies a precision limit and exponent range.
     * @return The parsed number, converted to arbitrary-precision binary
     * floating-point number.
     * @throws NullPointerException The parameter {@code bytes} is null.
     * @throws NumberFormatException The portion given of {@code bytes} is not a
     * correctly formatted number sequence; or either {@code offset} or
     * {@code length} is less than 0 or greater than {@code bytes} 's
     * length, or {@code bytes} 's length minus {@code offset} is less than
     * {@code length}.
     */
    public static EFloat FromString(
      byte[] bytes,
      int offset,
      int length,
      EContext ctx) {
      return EFloatByteArrayString.FromString(bytes, offset, length, ctx);
    }

    /**
     * Creates a binary floating-point number from a sequence of bytes that
     * represents a number, using an unlimited precision context. For more
     * information, see the <code>FromString(string, int, int, EContext)</code>
     * method.
     * @param bytes A sequence of bytes to convert to a binary floating-point
     * number.
     * @return The parsed number, converted to arbitrary-precision binary
     * floating-point number.
     * @throws NullPointerException The parameter {@code bytes} is null.
     * @throws NumberFormatException The portion given of {@code bytes} is not a
     * correctly formatted number sequence.
     */
    public static EFloat FromString(byte[] bytes) {
      return FromString(bytes, 0, bytes == null ? 0 : bytes.length, null);
    }

    /**
     * Creates a binary floating-point number from a sequence of bytes that
     * represents a number. For more information, see the
     * <code>FromString(string, int, int, EContext)</code> method.
     * @param bytes A sequence of bytes to convert to a binary floating-point
     * number.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If HasFlags of the context is true,
     * will also store the flags resulting from the operation (the flags
     * are in addition to the pre-existing flags). Can be null, in which
     * case the precision is unlimited. Note that providing a context is
     * often much faster than creating an EDecimal without a context then
     * calling ToEFloat on that EDecimal, especially if the context
     * specifies a precision limit and exponent range.
     * @return The parsed number, converted to arbitrary-precision binary
     * floating-point number.
     * @throws NullPointerException The parameter {@code bytes} is null.
     */
    public static EFloat FromString(byte[] bytes, EContext ctx) {
      return FromString(bytes, 0, bytes == null ? 0 : bytes.length, ctx);
    }

    /**
     * Creates a binary floating-point number from a sequence of bytes that
     * represents a number. For more information, see the
     * <code>FromString(string, int, int, EContext)</code> method.
     * @param bytes A sequence of bytes to convert to a binary floating-point
     * number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code bytes} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * bytes} (but not more than {@code bytes} 's length).
     * @return An arbitrary-precision binary floating-point number.
     * @throws NullPointerException The parameter {@code bytes} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code bytes} 's length, or {@code bytes} 's
     * length minus {@code offset} is less than {@code length}.
     */
    public static EFloat FromString(byte[] bytes, int offset, int length) {
      return FromString(bytes, offset, length, null);
    }

    /**
     * Gets the greater value between two binary floating-point numbers.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return The larger value of the two numbers. If one is positive zero and the
     * other is negative zero, returns the positive zero. If the two
     * numbers are positive and have the same value, returns the one with
     * the larger exponent. If the two numbers are negative and have the
     * same value, returns the one with the smaller exponent.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
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

    /**
     * Gets the greater value between two binary floating-point numbers.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return The larger value of the two numbers. If one is positive zero and the
     * other is negative zero, returns the positive zero. If the two
     * numbers are positive and have the same value, returns the one with
     * the larger exponent. If the two numbers are negative and have the
     * same value, returns the one with the smaller exponent.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
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

    /**
     * Gets the greater value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Max.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return The larger value of the two numbers, ignoring their signs.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
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

    /**
     * Gets the greater value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Max.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return The larger value of the two numbers, ignoring their signs.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
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

    /**
     * Gets the lesser value between two binary floating-point numbers.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return The smaller value of the two numbers. If one is positive zero and
     * the other is negative zero, returns the negative zero. If the two
     * numbers are positive and have the same value, returns the one with
     * the smaller exponent. If the two numbers are negative and have the
     * same value, returns the one with the larger exponent.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
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

    /**
     * Gets the lesser value between two binary floating-point numbers.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return The smaller value of the two numbers. If one is positive zero and
     * the other is negative zero, returns the negative zero. If the two
     * numbers are positive and have the same value, returns the one with
     * the smaller exponent. If the two numbers are negative and have the
     * same value, returns the one with the larger exponent.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
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

    /**
     * Gets the lesser value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Min.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return The smaller value of the two numbers, ignoring their signs.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
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

    /**
     * Gets the lesser value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Min.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return The smaller value of the two numbers, ignoring their signs.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
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

    /**
     * Finds the constant π, the circumference of a circle divided by its diameter.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as π can never be represented exactly.</i>.
     * @return The constant π rounded to the given precision. Signals FlagInvalid
     * and returns not-a-number (NaN) if the parameter {@code ctx} is null
     * or the precision is unlimited (the context's Precision property is
     * 0).
     */
    public static EFloat PI(EContext ctx) {
      return MathValue.Pi(ctx);
    }

    /**
     * Finds the absolute value of this object (if it's negative, it becomes
     * positive).
     * @return An arbitrary-precision binary floating-point number. Returns
     * signaling NaN if this value is signaling NaN. (In this sense, this
     *  method is similar to the "copy-abs" operation in the General Decimal
     * Arithmetic Specification, except this method does not necessarily
     * return a copy of this object.).
     */
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

    /**
     * Finds the absolute value of this object (if it's negative, it becomes
     * positive).
     * @param context An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The absolute value of this object. Signals FlagInvalid and returns
     * quiet NaN if this value is signaling NaN.
     */
    public EFloat Abs(EContext context) {
      return MathValue.Abs(this, context);
    }

    /**
     * Adds this arbitrary-precision binary floating-point number and a 32-bit
     * signed integer and returns the result. The exponent for the result
     * is the lower of this arbitrary-precision binary floating-point
     * number's exponent and the other 32-bit signed integer's exponent.
     * @param intValue The parameter {@code intValue} is a 32-bit signed integer.
     * @return The sum of the two numbers, that is, this arbitrary-precision binary
     * floating-point number plus a 32-bit signed integer. If this
     * arbitrary-precision binary floating-point number is not-a-number
     * (NaN), returns NaN.
     */
    public EFloat Add(int intValue) {
      return this.Add(EFloat.FromInt32(intValue));
    }

    /**
     * Subtracts a 32-bit signed integer from this arbitrary-precision binary
     * floating-point number and returns the result. The exponent for the
     * result is the lower of this arbitrary-precision binary
     * floating-point number's exponent and the other 32-bit signed
     * integer's exponent.
     * @param intValue The parameter {@code intValue} is a 32-bit signed integer.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision binary floating-point number minus a 32-bit
     * signed integer. If this arbitrary-precision binary floating-point
     * number is not-a-number (NaN), returns NaN.
     */
    public EFloat Subtract(int intValue) {
      return (intValue == Integer.MIN_VALUE) ?
        this.Subtract(EFloat.FromInt32(intValue)) : this.Add(-intValue);
    }

    /**
     * Multiplies this arbitrary-precision binary floating-point number by a 32-bit
     * signed integer and returns the result. The exponent for the result
     * is this arbitrary-precision binary floating-point number's exponent
     *  plus the other 32-bit signed integer's exponent.<p> <pre>EInteger result = EInteger.FromString("5").Multiply(200);</pre> . </p>
     * @param intValue The parameter {@code intValue} is a 32-bit signed integer.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * binary floating-point number times a 32-bit signed integer.
     */
    public EFloat Multiply(int intValue) {
      return this.Multiply(EFloat.FromInt32(intValue));
    }

    /**
     * Divides this arbitrary-precision binary floating-point number by a 32-bit
     * signed integer and returns the result; returns NaN instead if the
     * result would have a nonterminating binary expansion (including 1/3,
     * 1/12, 1/7, 2/3, and so on); if this is not desired, use
     * DivideToExponent, or use the Divide overload that takes an EContext.
     * @param intValue The divisor.
     * @return The result of dividing this arbitrary-precision binary
     * floating-point number by a 32-bit signed integer. Returns infinity
     * if the divisor (this arbitrary-precision binary floating-point
     * number) is 0 and the dividend (the other 32-bit signed integer) is
     * nonzero. Returns not-a-number (NaN) if the divisor and the dividend
     * are 0. Returns NaN if the result can't be exact because it would
     * have a nonterminating binary expansion (examples include 1 divided
     * by any multiple of 3, such as 1/3 or 1/12). If this is not desired,
     * use DivideToExponent instead, or use the Divide overload that takes
     * an {@code EContext} (such as {@code EContext.Binary64}) instead.
     * @throws ArithmeticException Attempted to divide by zero.
     */
    public EFloat Divide(int intValue) {
      return this.Divide(EFloat.FromInt32(intValue));
    }

    /**
     * Adds this arbitrary-precision binary floating-point number and a 64-bit
     * signed integer and returns the result. The exponent for the result
     * is the lower of this arbitrary-precision binary floating-point
     * number's exponent and the other 64-bit signed integer's exponent.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The sum of the two numbers, that is, this arbitrary-precision binary
     * floating-point number plus a 64-bit signed integer. If this
     * arbitrary-precision binary floating-point number is not-a-number
     * (NaN), returns NaN.
     */
    public EFloat Add(long longValue) {
      return this.Add(EFloat.FromInt64(longValue));
    }

    /**
     * Subtracts a 64-bit signed integer from this arbitrary-precision binary
     * floating-point number and returns the result. The exponent for the
     * result is the lower of this arbitrary-precision binary
     * floating-point number's exponent and the other 64-bit signed
     * integer's exponent.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision binary floating-point number minus a 64-bit
     * signed integer. If this arbitrary-precision binary floating-point
     * number is not-a-number (NaN), returns NaN.
     */
    public EFloat Subtract(long longValue) {
      return this.Subtract(EFloat.FromInt64(longValue));
    }

    /**
     * Multiplies this arbitrary-precision binary floating-point number by a 64-bit
     * signed integer and returns the result. The exponent for the result
     * is this arbitrary-precision binary floating-point number's exponent
     *  plus the other 64-bit signed integer's exponent.<p> <pre>EInteger result = EInteger.FromString("5").Multiply(200L);</pre> . </p>
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * binary floating-point number times a 64-bit signed integer.
     */
    public EFloat Multiply(long longValue) {
      return this.Multiply(EFloat.FromInt64(longValue));
    }

    /**
     * Divides this arbitrary-precision binary floating-point number by a 64-bit
     * signed integer and returns the result; returns NaN instead if the
     * result would have a nonterminating binary expansion (including 1/3,
     * 1/12, 1/7, 2/3, and so on); if this is not desired, use
     * DivideToExponent, or use the Divide overload that takes an EContext.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The result of dividing this arbitrary-precision binary
     * floating-point number by a 64-bit signed integer. Returns infinity
     * if the divisor (this arbitrary-precision binary floating-point
     * number) is 0 and the dividend (the other 64-bit signed integer) is
     * nonzero. Returns not-a-number (NaN) if the divisor and the dividend
     * are 0. Returns NaN if the result can't be exact because it would
     * have a nonterminating binary expansion (examples include 1 divided
     * by any multiple of 3, such as 1/3 or 1/12). If this is not desired,
     * use DivideToExponent instead, or use the Divide overload that takes
     * an {@code EContext} (such as {@code EContext.Binary64}) instead.
     * @throws ArithmeticException Attempted to divide by zero.
     */
    public EFloat Divide(long longValue) {
      return this.Divide(EFloat.FromInt64(longValue));
    }

    /**
     * Adds this arbitrary-precision binary floating-point number and another
     * arbitrary-precision binary floating-point number and returns the
     * result. The exponent for the result is the lower of this
     * arbitrary-precision binary floating-point number's exponent and the
     * other arbitrary-precision binary floating-point number's exponent.
     * @param otherValue An arbitrary-precision binary floating-point number.
     * @return The sum of the two numbers, that is, this arbitrary-precision binary
     * floating-point number plus another arbitrary-precision binary
     * floating-point number. If this arbitrary-precision binary
     * floating-point number is not-a-number (NaN), returns NaN.
     */
    public EFloat Add(EFloat otherValue) {
      return this.Add(otherValue, EContext.UnlimitedHalfEven);
    }

    /**
     * Adds this arbitrary-precision binary floating-point number and another
     * arbitrary-precision binary floating-point number and returns the
     * result.
     * @param otherValue The number to add to.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The sum of the two numbers, that is, this arbitrary-precision binary
     * floating-point number plus another arbitrary-precision binary
     * floating-point number. If this arbitrary-precision binary
     * floating-point number is not-a-number (NaN), returns NaN.
     */
    public EFloat Add(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.Add(this, otherValue, ctx);
    }

    /**
     * Compares the mathematical values of this object and another object,
     * accepting NaN values. This method currently uses the rules given in
     * the CompareToValue method, so that it it is not consistent with the
     * Equals method, but it may change in a future version to use the
     * rules for the CompareToTotal method instead.
     * @param other An arbitrary-precision binary floating-point number.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other
     * value or if {@code other} is null, or 0 if both values are equal.
     */
    public int compareTo(EFloat other) {
      return MathValue.compareTo(this, other);
    }

    /**
     * Compares the mathematical values of this object and another object,
     * accepting NaN values. <p>This method is not consistent with the
     * Equals method because two different numbers with the same
     * mathematical value, but different exponents, will compare as
     * equal.</p> <p>In this method, negative zero and positive zero are
     * considered equal.</p> <p>If this object or the other object is a
     * quiet NaN or signaling NaN, this method will not trigger an error.
     * Instead, NaN will compare greater than any other number, including
     * infinity. Two different NaN values will be considered equal.</p>
     * @param other An arbitrary-precision binary floating-point number.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other
     * value or if {@code other} is null, or 0 if both values are equal.
     */
    public int CompareToValue(EFloat other) {
      return MathValue.compareTo(this, other);
    }

    /**
     * Compares the mathematical values of this object and another object,
     * accepting NaN values. This method currently uses the rules given in
     * the CompareToValue method, so that it it is not consistent with the
     * Equals method, but it may change in a future version to use the
     * rules for the CompareToTotal method instead.
     * @param intOther The parameter {@code intOther} is a 32-bit signed integer.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other
     * value, or 0 if both values are equal.
     */
    public int compareTo(int intOther) {
      return this.CompareToValue(EFloat.FromInt32(intOther));
    }

    /**
     * Compares the mathematical values of this object and another object,
     * accepting NaN values. <p>This method is not consistent with the
     * Equals method because two different numbers with the same
     * mathematical value, but different exponents, will compare as
     * equal.</p> <p>In this method, negative zero and positive zero are
     * considered equal.</p> <p>If this object is a quiet NaN or signaling
     * NaN, this method will not trigger an error. Instead, NaN will
     * compare greater than any other number.</p>
     * @param intOther The parameter {@code intOther} is a 32-bit signed integer.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other
     * value, or 0 if both values are equal.
     */
    public int CompareToValue(int intOther) {
      return this.CompareToValue(EFloat.FromInt32(intOther));
    }

    /**
     * Compares the mathematical values of this object and another object,
     * accepting NaN values. <p>This method is not consistent with the
     * Equals method because two different numbers with the same
     * mathematical value, but different exponents, will compare as
     * equal.</p> <p>In this method, negative zero and positive zero are
     * considered equal.</p> <p>If this object is a quiet NaN or signaling
     * NaN, this method will not trigger an error. Instead, NaN will
     * compare greater than any other number, including infinity.</p>
     * @param intOther The parameter {@code intOther} is a 64-bit signed integer.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other
     * value, or 0 if both values are equal.
     */
    public int CompareToValue(long intOther) {
      return this.CompareToValue(FromInt64(intOther));
    }

    /**
     * Compares the mathematical values of this object and another object,
     * accepting NaN values. This method currently uses the rules given in
     * the CompareToValue method, so that it it is not consistent with the
     * Equals method, but it may change in a future version to use the
     * rules for the CompareToTotal method instead.
     * @param intOther The parameter {@code intOther} is a 64-bit signed integer.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other
     * value, or 0 if both values are equal.
     */
    public int compareTo(long intOther) {
      return this.CompareToValue(FromInt64(intOther));
    }

    /**
     * Compares the mathematical values of this object and another object, treating
     * quiet NaN as signaling. <p>In this method, negative zero and
     * positive zero are considered equal.</p> <p>If this object or the
     * other object is a quiet NaN or signaling NaN, this method will
     * return a quiet NaN and will signal a FlagInvalid flag.</p>
     * @param other An arbitrary-precision binary floating-point number.
     * @param ctx An arithmetic context. The precision, rounding, and exponent
     * range are ignored. If {@code HasFlags} of the context is true, will
     * store the flags resulting from the operation (the flags are in
     * addition to the pre-existing flags). Can be null.
     * @return Quiet NaN if this object or the other object is NaN, or 0 if both
     * objects have the same value, or -1 if this object is less than the
     * other value, or 1 if this object is greater. This implementation
     * returns a positive number if.
     */
    public EFloat CompareToSignal(
      EFloat other,
      EContext ctx) {
      return MathValue.CompareToWithContext(this, other, true, ctx);
    }

    /**
     * Compares the values of this object and another object, imposing a total
     * ordering on all possible values. In this method: <ul> <li>For
     * objects with the same value, the one with the higher exponent has a
     *  greater "absolute value".</li> <li>Negative zero is less than
     *  positive zero.</li> <li>Quiet NaN has a higher "absolute value" than
     * signaling NaN. If both objects are quiet NaN or both are signaling
     * NaN, the one with the higher diagnostic information has a greater
     *  "absolute value".</li> <li>NaN has a higher "absolute value" than
     *  infinity.</li> <li>Infinity has a higher "absolute value" than any
     * finite number.</li> <li>Negative numbers are less than positive
     * numbers.</li></ul>
     * @param other An arbitrary-precision binary floating-point number to compare
     * with this one.
     * @param ctx An arithmetic context. Flags will be set in this context only if
     * {@code HasFlags} and {@code IsSimplified} of the context are true
     * and only if an operand needed to be rounded before carrying out the
     * operation. Can be null.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater.
     * Does not signal flags if either value is signaling NaN. This
     * implementation returns a positive number if.
     */
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

    /**
     * Compares the values of this object and another object, imposing a total
     * ordering on all possible values (ignoring their signs). In this
     * method: <ul> <li>For objects with the same value, the one with the
     *  higher exponent has a greater "absolute value".</li> <li>Negative
     * zero is less than positive zero.</li> <li>Quiet NaN has a higher
     *  "absolute value" than signaling NaN. If both objects are quiet NaN
     * or both are signaling NaN, the one with the higher diagnostic
     *  information has a greater "absolute value".</li> <li>NaN has a
     *  higher "absolute value" than infinity.</li> <li>Infinity has a
     *  higher "absolute value" than any finite number.</li> <li>Negative
     * numbers are less than positive numbers.</li></ul>
     * @param other An arbitrary-precision binary floating-point number to compare
     * with this one.
     * @param ctx An arithmetic context. Flags will be set in this context only if
     * {@code HasFlags} and {@code IsSimplified} of the context are true
     * and only if an operand needed to be rounded before carrying out the
     * operation. Can be null.
     * @return The number 0 if both objects have the same value (ignoring their
     * signs), or -1 if this object is less than the other value (ignoring
     * their signs), or 1 if this object is greater (ignoring their signs).
     * Does not signal flags if either value is signaling NaN. This
     * implementation returns a positive number if.
     */
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

    /**
     * Compares the values of this object and another object, imposing a total
     * ordering on all possible values. In this method: <ul> <li>For
     * objects with the same value, the one with the higher exponent has a
     *  greater "absolute value".</li> <li>Negative zero is less than
     *  positive zero.</li> <li>Quiet NaN has a higher "absolute value" than
     * signaling NaN. If both objects are quiet NaN or both are signaling
     * NaN, the one with the higher diagnostic information has a greater
     *  "absolute value".</li> <li>NaN has a higher "absolute value" than
     *  infinity.</li> <li>Infinity has a higher "absolute value" than any
     * finite number.</li> <li>Negative numbers are less than positive
     * numbers.</li></ul>
     * @param other An arbitrary-precision binary floating-point number to compare
     * with this one.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater.
     * This implementation returns a positive number if.
     */
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

    /**
     * Compares the absolute values of this object and another object, imposing a
     * total ordering on all possible values (ignoring their signs). In
     * this method: <ul> <li>For objects with the same value, the one with
     *  the higher exponent has a greater "absolute value".</li>
     * <li>Negative zero and positive zero are considered equal.</li>
     *  <li>Quiet NaN has a higher "absolute value" than signaling NaN. If
     * both objects are quiet NaN or both are signaling NaN, the one with
     *  the higher diagnostic information has a greater "absolute
     *  value".</li> <li>NaN has a higher "absolute value" than
     *  infinity.</li> <li>Infinity has a higher "absolute value" than any
     * finite number.</li></ul>
     * @param other An arbitrary-precision binary floating-point number to compare
     * with this one.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater.
     * This implementation returns a positive number if.
     */
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

    /**
     * Compares the mathematical values of this object and another object. <p>In
     * this method, negative zero and positive zero are considered
     * equal.</p> <p>If this object or the other object is a quiet NaN or
     * signaling NaN, this method returns a quiet NaN, and will signal a
     * FlagInvalid flag if either is a signaling NaN.</p>
     * @param other An arbitrary-precision binary floating-point number.
     * @param ctx An arithmetic context. The precision, rounding, and exponent
     * range are ignored. If {@code HasFlags} of the context is true, will
     * store the flags resulting from the operation (the flags are in
     * addition to the pre-existing flags). Can be null.
     * @return Quiet NaN if this object or the other object is NaN, or 0 if both
     * objects have the same value, or -1 if this object is less than the
     * other value, or 1 if this object is greater. This implementation
     * returns a positive number if.
     */
    public EFloat CompareToWithContext(
      EFloat other,
      EContext ctx) {
      return MathValue.CompareToWithContext(this, other, false, ctx);
    }

    /**
     * Returns a number with the same value as this one, but copying the sign
     * (positive or negative) of another number. (This method is similar to
     *  the "copy-sign" operation in the General Decimal Arithmetic
     * Specification, except this method does not necessarily return a copy
     * of this object.).
     * @param other A number whose sign will be copied.
     * @return An arbitrary-precision binary floating-point number.
     * @throws NullPointerException The parameter {@code other} is null.
     */
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

    /**
     * Divides this arbitrary-precision binary floating-point number by another
     * arbitrary-precision binary floating-point number and returns the
     * result; returns NaN instead if the result would have a
     * nonterminating binary expansion (including 1/3, 1/12, 1/7, 2/3, and
     * so on); if this is not desired, use DivideToExponent, or use the
     * Divide overload that takes an EContext.
     * @param divisor The number to divide by.
     * @return The result of dividing this arbitrary-precision binary
     * floating-point number by another arbitrary-precision binary
     * floating-point number. Returns infinity if the divisor (this
     * arbitrary-precision binary floating-point number) is 0 and the
     * dividend (the other arbitrary-precision binary floating-point
     * number) is nonzero. Returns not-a-number (NaN) if the divisor and
     * the dividend are 0. Returns NaN if the result can't be exact because
     * it would have a nonterminating binary expansion (examples include 1
     * divided by any multiple of 3, such as 1/3 or 1/12). If this is not
     * desired, use DivideToExponent instead, or use the Divide overload
     * that takes an {@code EContext} (such as {@code EContext.Binary64})
     * instead.
     */
    public EFloat Divide(EFloat divisor) {
      return this.Divide(
          divisor,
          EContext.ForRounding(ERounding.None));
    }

    /**
     * Divides this arbitrary-precision binary floating-point number by another
     * arbitrary-precision binary floating-point number and returns the
     * result.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The result of dividing this arbitrary-precision binary
     * floating-point number by another arbitrary-precision binary
     * floating-point number. Signals FlagDivideByZero and returns infinity
     * if the divisor (this arbitrary-precision binary floating-point
     * number) is 0 and the dividend (the other arbitrary-precision binary
     * floating-point number) is nonzero. Signals FlagInvalid and returns
     * not-a-number (NaN) if the divisor and the dividend are 0; or, either
     * {@code ctx} is null or {@code ctx} 's precision is 0, and the result
     * would have a nonterminating decimal expansion (examples include 1
     * divided by any multiple of 3, such as 1/3 or 1/12); or, the rounding
     * mode is ERounding.None and the result is not exact.
     */
    public EFloat Divide(
      EFloat divisor,
      EContext ctx) {
      return MathValue.Divide(this, divisor, ctx);
    }

    /**
     * Calculates the quotient and remainder using the DivideToIntegerNaturalScale
     * and the formula in RemainderNaturalScale.
     * @param divisor The number to divide by.
     * @return A 2 element array consisting of the quotient and remainder in that
     * order.
     * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EFloat[] DivideAndRemainderNaturalScale(EFloat
      divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

    /**
     * Calculates the quotient and remainder using the DivideToIntegerNaturalScale
     * and the formula in RemainderNaturalScale.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only in
     * the division portion of the remainder calculation; as a result, it's
     * possible for the remainder to have a higher precision than given in
     * this context. Flags will be set on the given context only if the
     * context's {@code HasFlags} is true and the integer part of the
     * division result doesn't fit the precision and exponent range without
     * rounding. Can be null, in which the precision is unlimited and no
     * additional rounding, other than the rounding down to an integer
     * after division, is needed.
     * @return A 2 element array consisting of the quotient and remainder in that
     * order.
     * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EFloat[] DivideAndRemainderNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return this.DivRemNaturalScale(divisor, ctx);
    }

    /**
     * Divides two arbitrary-precision binary floating-point numbers, and gives a
     * particular exponent to the result.
     * @param divisor The number to divide by.
     * @param desiredExponentSmall The desired exponent. A negative number places
     * the cutoff point to the right of the usual radix point (so a
     * negative number means the number of binary digit places to round
     * to). A positive number places the cutoff point to the left of the
     * usual radix point.
     * @param ctx An arithmetic context object to control the rounding mode to use
     * if the result must be scaled down to have the same exponent as this
     * value. If the precision given in the context is other than 0, calls
     * the Quantize method with both arguments equal to the result of the
     * operation (and can signal FlagInvalid and return NaN if the result
     * doesn't fit the given precision). If {@code HasFlags} of the context
     * is true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor
     * and the dividend are 0. Signals FlagInvalid and returns not-a-number
     * (NaN) if the context defines an exponent range and the desired
     * exponent is outside that range. Signals FlagInvalid and returns
     * not-a-number (NaN) if the rounding mode is ERounding.None and the
     * result is not exact.
     */
    public EFloat DivideToExponent(
      EFloat divisor,
      long desiredExponentSmall,
      EContext ctx) {
      return this.DivideToExponent(
          divisor,
          EInteger.FromInt64(desiredExponentSmall),
          ctx);
    }

    /**
     * Divides two arbitrary-precision binary floating-point numbers, and gives a
     * particular exponent to the result.
     * @param divisor The number to divide by.
     * @param desiredExponentSmall The desired exponent. A negative number places
     * the cutoff point to the right of the usual radix point (so a
     * negative number means the number of binary digit places to round
     * to). A positive number places the cutoff point to the left of the
     * usual radix point.
     * @param rounding The rounding mode to use if the result must be scaled down
     * to have the same exponent as this value.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor
     * and the dividend are 0. Signals FlagInvalid and returns not-a-number
     * (NaN) if the rounding mode is ERounding.None and the result is not
     * exact.
     */
    public EFloat DivideToExponent(
      EFloat divisor,
      long desiredExponentSmall,
      ERounding rounding) {
      return this.DivideToExponent(
          divisor,
          EInteger.FromInt64(desiredExponentSmall),
          EContext.ForRounding(rounding));
    }

    /**
     * Divides two arbitrary-precision binary floating-point numbers, and gives a
     * particular exponent to the result.
     * @param divisor The number to divide by.
     * @param exponent The desired exponent. A negative number places the cutoff
     * point to the right of the usual radix point (so a negative number
     * means the number of binary digit places to round to). A positive
     * number places the cutoff point to the left of the usual radix point.
     * @param ctx An arithmetic context object to control the rounding mode to use
     * if the result must be scaled down to have the same exponent as this
     * value. If the precision given in the context is other than 0, calls
     * the Quantize method with both arguments equal to the result of the
     * operation (and can signal FlagInvalid and return NaN if the result
     * doesn't fit the given precision). If {@code HasFlags} of the context
     * is true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor
     * and the dividend are 0. Signals FlagInvalid and returns not-a-number
     * (NaN) if the context defines an exponent range and the desired
     * exponent is outside that range. Signals FlagInvalid and returns
     * not-a-number (NaN) if the rounding mode is ERounding.None and the
     * result is not exact.
     */
    public EFloat DivideToExponent(
      EFloat divisor,
      EInteger exponent,
      EContext ctx) {
      return MathValue.DivideToExponent(this, divisor, exponent, ctx);
    }

    /**
     * Divides two arbitrary-precision binary floating-point numbers, and gives a
     * particular exponent to the result.
     * @param divisor The number to divide by.
     * @param desiredExponent The desired exponent. A negative number places the
     * cutoff point to the right of the usual radix point (so a negative
     * number means the number of binary digit places to round to). A
     * positive number places the cutoff point to the left of the usual
     * radix point.
     * @param rounding The rounding mode to use if the result must be scaled down
     * to have the same exponent as this value.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Returns not-a-number (NaN) if the divisor and the dividend are 0.
     * Returns NaN if the rounding mode is ERounding.None and the result is
     * not exact.
     */
    public EFloat DivideToExponent(
      EFloat divisor,
      EInteger desiredExponent,
      ERounding rounding) {
      return this.DivideToExponent(
          divisor,
          desiredExponent,
          EContext.ForRounding(rounding));
    }

    /**
     * Divides two arbitrary-precision binary floating-point numbers, and returns
     * the integer part of the result, rounded down, with the preferred
     * exponent set to this value's exponent minus the divisor's exponent.
     * @param divisor The number to divide by.
     * @return The integer part of the quotient of the two objects. Signals
     * FlagDivideByZero and returns infinity if the divisor is 0 and the
     * dividend is nonzero. Signals FlagInvalid and returns not-a-number
     * (NaN) if the divisor and the dividend are 0.
     */
    public EFloat DivideToIntegerNaturalScale(
      EFloat divisor) {
      return this.DivideToIntegerNaturalScale(
          divisor,
          EContext.ForRounding(ERounding.Down));
    }

    /**
     * Divides this object by another object, and returns the integer part of the
     * result (which is initially rounded down), with the preferred
     * exponent set to this value's exponent minus the divisor's exponent.
     * @param divisor An arbitrary-precision binary floating-point number.
     * @param ctx The parameter {@code ctx} is an EContext object.
     * @return The integer part of the quotient of the two objects. Signals
     * FlagInvalid and returns not-a-number (NaN) if the return value would
     * overflow the exponent range. Signals FlagDivideByZero and returns
     * infinity if the divisor is 0 and the dividend is nonzero. Signals
     * FlagInvalid and returns not-a-number (NaN) if the divisor and the
     * dividend are 0. Signals FlagInvalid and returns not-a-number (NaN)
     * if the rounding mode is ERounding.None and the result is not exact.
     */
    public EFloat DivideToIntegerNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return MathValue.DivideToIntegerNaturalScale(this, divisor, ctx);
    }

    /**
     * Divides this object by another object, and returns the integer part of the
     * result, with the exponent set to 0.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision. The
     * rounding and exponent range settings of this context are ignored. If
     * {@code HasFlags} of the context is true, will also store the flags
     * resulting from the operation (the flags are in addition to the
     * pre-existing flags). Can be null, in which case the precision is
     * unlimited.
     * @return The integer part of the quotient of the two objects. The exponent
     * will be set to 0. Signals FlagDivideByZero and returns infinity if
     * the divisor is 0 and the dividend is nonzero. Signals FlagInvalid
     * and returns not-a-number (NaN) if the divisor and the dividend are
     * 0, or if the result doesn't fit the given precision.
     */
    public EFloat DivideToIntegerZeroScale(
      EFloat divisor,
      EContext ctx) {
      return MathValue.DivideToIntegerZeroScale(this, divisor, ctx);
    }

    /**
     * Divides this object by another binary floating-point number and returns a
     * result with the same exponent as this object (the dividend).
     * @param divisor The number to divide by.
     * @param rounding The rounding mode to use if the result must be scaled down
     * to have the same exponent as this value.
     * @return The quotient of the two numbers. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor
     * and the dividend are 0. Signals FlagInvalid and returns not-a-number
     * (NaN) if the rounding mode is ERounding.None and the result is not
     * exact.
     */
    public EFloat DivideToSameExponent(
      EFloat divisor,
      ERounding rounding) {
      return this.DivideToExponent(
          divisor,
          this.exponent.ToEInteger(),
          EContext.ForRounding(rounding));
    }

    /**
     * Divides this arbitrary-precision binary floating-point number by another
     * arbitrary-precision binary floating-point number and returns a
     * two-item array containing the result of the division and the
     * remainder, in that order. The result of division is calculated as
     * though by <code>DivideToIntegerNaturalScale</code>, and the remainder is
     * calculated as though by <code>RemainderNaturalScale</code>.
     * @param divisor The number to divide by.
     * @return An array of two items: the first is the result of the division as an
     * arbitrary-precision binary floating-point number, and the second is
     * the remainder as an arbitrary-precision binary floating-point
     * number. The result of division is the result of the method on the
     * two operands, and the remainder is the result of the Remainder
     * method on the two operands.
     */
    public EFloat[] DivRemNaturalScale(EFloat divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

    /**
     * Divides this arbitrary-precision binary floating-point number by another
     * arbitrary-precision binary floating-point number and returns a
     * two-item array containing the result of the division and the
     * remainder, in that order. The result of division is calculated as
     * though by <code>DivideToIntegerNaturalScale</code>, and the remainder is
     * calculated as though by <code>RemainderNaturalScale</code>.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only in
     * the division portion of the remainder calculation; as a result, it's
     * possible for the remainder to have a higher precision than given in
     * this context. Flags will be set on the given context only if the
     * context's {@code HasFlags} is true and the integer part of the
     * division result doesn't fit the precision and exponent range without
     * rounding. Can be null, in which the precision is unlimited and no
     * additional rounding, other than the rounding down to an integer
     * after division, is needed.
     * @return An array of two items: the first is the result of the division as an
     * arbitrary-precision binary floating-point number, and the second is
     * the remainder as an arbitrary-precision binary floating-point
     * number. The result of division is the result of the method on the
     * two operands, and the remainder is the result of the Remainder
     * method on the two operands.
     */
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

    /**
     * Determines whether this object's significand, exponent, and properties are
     * equal to those of another object. Not-a-number values are considered
     * equal if the rest of their properties are equal.
     * @param other An arbitrary-precision binary floating-point number.
     * @return {@code true} if this object's significand and exponent are equal to
     * those of another object; otherwise, {@code false}.
     */
    public boolean equals(EFloat other) {
      return this.EqualsInternal(other);
    }

    /**
     * Determines whether this object's significand, exponent, and properties are
     * equal to those of another object and that other object is an
     * arbitrary-precision binary floating-point number. Not-a-number
     * values are considered equal if the rest of their properties are
     * equal.
     * @param obj The parameter {@code obj} is an arbitrary object.
     * @return {@code true} if the objects are equal; otherwise, {@code false}. In
     * this method, two objects are not equal if they don't have the same
     * type or if one is null and the other isn't.
     */
    @Override public boolean equals(Object obj) {
      return this.EqualsInternal(((obj instanceof EFloat) ? (EFloat)obj : null));
    }

    /**
     * Determines whether this object's significand and exponent are equal to those
     * of another object.
     * @param otherValue An arbitrary-precision binary floating-point number.
     * @return {@code true} if this object's significand and exponent are equal to
     * those of another object; otherwise, {@code false}.
     */
    public boolean EqualsInternal(EFloat otherValue) {
      if (otherValue == null) {
        return false;
      }
      return this.exponent.equals(otherValue.exponent) &&
        this.unsignedMantissa.equals(otherValue.unsignedMantissa) &&
        this.flags == otherValue.flags;
    }

    /**
     * Finds e (the base of natural logarithms) raised to the power of this
     * object's value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the exponential function's results are generally
     * not exact.</i> (Unlike in the General Binary Arithmetic
     * Specification, any rounding mode is allowed.).
     * @return Exponential of this object. If this object's value is 1, returns an
     *  approximation to " e" within the given precision. Signals
     * FlagInvalid and returns not-a-number (NaN) if the parameter {@code
     * ctx} is null or the precision is unlimited (the context's Precision
     * property is 0).
     */
    public EFloat Exp(EContext ctx) {
      return MathValue.Exp(this, ctx);
    }

    /**
     * Finds e (the base of natural logarithms) raised to the power of this
     * object's value, and subtracts the result by 1 and returns the final
     * result, in a way that avoids loss of precision if the true result is
     * very close to 0.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the exponential function's results are generally
     * not exact.</i> (Unlike in the General Binary Arithmetic
     * Specification, any rounding mode is allowed.).
     * @return Exponential of this object, minus 1. Signals FlagInvalid and returns
     * not-a-number (NaN) if the parameter {@code ctx} is null or the
     * precision is unlimited (the context's Precision property is 0).
     */
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

    /**
     * Calculates this object's hash code. No application or process IDs are used
     * in the hash code calculation.
     * @return A 32-bit signed integer.
     */
    @Override public int hashCode() {
      int valueHashCode = 403796923;
      {
        valueHashCode += 403797019 * this.exponent.hashCode();
        valueHashCode += 403797059 * this.unsignedMantissa.hashCode();
        valueHashCode += 403797127 * this.flags;
      }
      return valueHashCode;
    }

    /**
     * Gets a value indicating whether this object is positive or negative
     * infinity.
     * @return {@code true} if this object is positive or negative infinity;
     * otherwise, {@code false}.
     */
    public boolean IsInfinity() {
      return (this.flags & BigNumberFlags.FlagInfinity) != 0;
    }

    /**
     * Gets a value indicating whether this object is not a number (NaN).
     * @return {@code true} if this object is not a number (NaN); otherwise, {@code
     * false}.
     */
    public boolean IsNaN() {
      return (this.flags & (BigNumberFlags.FlagQuietNaN |
            BigNumberFlags.FlagSignalingNaN)) != 0;
    }

    /**
     * Returns whether this object is negative infinity.
     * @return {@code true} if this object is negative infinity; otherwise, {@code
     * false}.
     */
    public boolean IsNegativeInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
            BigNumberFlags.FlagNegative)) ==
        (BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);
    }

    /**
     * Returns whether this object is positive infinity.
     * @return {@code true} if this object is positive infinity; otherwise, {@code
     * false}.
     */
    public boolean IsPositiveInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
            BigNumberFlags.FlagNegative)) == BigNumberFlags.FlagInfinity;
    }

    /**
     * Gets a value indicating whether this object is a quiet not-a-number value.
     * @return {@code true} if this object is a quiet not-a-number value;
     * otherwise, {@code false}.
     */
    public boolean IsQuietNaN() {
      return (this.flags & BigNumberFlags.FlagQuietNaN) != 0;
    }

    /**
     * Gets a value indicating whether this object is a signaling not-a-number
     * value.
     * @return {@code true} if this object is a signaling not-a-number value;
     * otherwise, {@code false}.
     */
    public boolean IsSignalingNaN() {
      return (this.flags & BigNumberFlags.FlagSignalingNaN) != 0;
    }

    /**
     * Finds the natural logarithm of this object, that is, the power (exponent)
     * that e (the base of natural logarithms) must be raised to in order
     * to equal this object's value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the ln function's results are generally not
     * exact.</i> (Unlike in the General Binary Arithmetic Specification,
     * any rounding mode is allowed.).
     * @return Ln(this object). Signals the flag FlagInvalid and returns NaN if
     * this object is less than 0 (the result would be a complex number
     * with a real part equal to Ln of this object's absolute value and an
     * imaginary part equal to pi, but the return value is still NaN.).
     * Signals FlagInvalid and returns not-a-number (NaN) if the parameter
     * {@code ctx} is null or the precision is unlimited (the context's
     * Precision property is 0). Signals no flags and returns negative
     * infinity if this object's value is 0.
     */
    public EFloat Log(EContext ctx) {
      return MathValue.Ln(this, ctx);
    }

    /**
     * Finds the base-10 logarithm of this object, that is, the power (exponent)
     * that the number 10 must be raised to in order to equal this object's
     * value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the ln function's results are generally not
     * exact.</i> (Unlike in the General Binary Arithmetic Specification,
     * any rounding mode is allowed.).
     * @return Ln(this object)/Ln(10). Signals the flag FlagInvalid and returns
     * not-a-number (NaN) if this object is less than 0. Signals
     * FlagInvalid and returns not-a-number (NaN) if the parameter {@code
     * ctx} is null or the precision is unlimited (the context's Precision
     * property is 0).
     */
    public EFloat Log10(EContext ctx) {
      return this.LogN(EFloat.FromInt32(10), ctx);
    }

    /**
     * Adds 1 to this object's value and finds the natural logarithm of the result,
     * in a way that avoids loss of precision when this object's value is
     * between 0 and 1.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the ln function's results are generally not
     * exact.</i> (Unlike in the General Binary Arithmetic Specification,
     * any rounding mode is allowed.).
     * @return Ln(1+(this object)). Signals the flag FlagInvalid and returns NaN if
     * this object is less than -1 (the result would be a complex number
     * with a real part equal to Ln of 1 plus this object's absolute value
     * and an imaginary part equal to pi, but the return value is still
     * NaN.). Signals FlagInvalid and returns not-a-number (NaN) if the
     * parameter {@code ctx} is null or the precision is unlimited (the
     * context's Precision property is 0). Signals no flags and returns
     * negative infinity if this object's value is 0.
     */
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

    /**
     * Finds the base-N logarithm of this object, that is, the power (exponent)
     * that the number N must be raised to in order to equal this object's
     * value.
     * @param baseValue The parameter {@code baseValue} is a Numbers.EFloat object.
     * @param ctx The parameter {@code ctx} is a Numbers.EContext object.
     * @return Ln(this object)/Ln(baseValue). Signals the flag FlagInvalid and
     * returns not-a-number (NaN) if this object is less than 0. Signals
     * FlagInvalid and returns not-a-number (NaN) if the parameter {@code
     * ctx} is null or the precision is unlimited (the context's Precision
     * property is 0).
     * @throws NullPointerException The parameter {@code baseValue} is null.
     */
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

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the left.
     * @param places The number of binary digit places to move the radix point to
     * the left. If this number is negative, instead moves the radix point
     * to the right by this number's absolute value.
     * @return A number whose exponent is decreased by {@code places}, but not to
     * more than 0.
     */
    public EFloat MovePointLeft(int places) {
      return this.MovePointLeft(EInteger.FromInt32(places), null);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the left.
     * @param places The number of binary digit places to move the radix point to
     * the left. If this number is negative, instead moves the radix point
     * to the right by this number's absolute value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is decreased by {@code places}, but not to
     * more than 0.
     */
    public EFloat MovePointLeft(int places, EContext ctx) {
      return this.MovePointLeft(EInteger.FromInt32(places), ctx);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the left.
     * @param bigPlaces The number of binary digit places to move the radix point
     * to the left. If this number is negative, instead moves the radix
     * point to the right by this number's absolute value.
     * @return A number whose exponent is decreased by {@code bigPlaces}, but not
     * to more than 0.
     */
    public EFloat MovePointLeft(EInteger bigPlaces) {
      return this.MovePointLeft(bigPlaces, null);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the left.
     * @param bigPlaces The number of binary digit places to move the radix point
     * to the left. If this number is negative, instead moves the radix
     * point to the right by this number's absolute value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is decreased by {@code bigPlaces}, but not
     * to more than 0.
     */
    public EFloat MovePointLeft(
      EInteger bigPlaces,
      EContext ctx) {
      return (!this.isFinite()) ? this.RoundToPrecision(ctx) :
        this.MovePointRight((bigPlaces).Negate(), ctx);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the right.
     * @param places The number of binary digit places to move the radix point to
     * the right. If this number is negative, instead moves the radix point
     * to the left by this number's absolute value.
     * @return A number whose exponent is increased by {@code places}, but not to
     * more than 0.
     */
    public EFloat MovePointRight(int places) {
      return this.MovePointRight(EInteger.FromInt32(places), null);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the right.
     * @param places The number of binary digit places to move the radix point to
     * the right. If this number is negative, instead moves the radix point
     * to the left by this number's absolute value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is increased by {@code places}, but not to
     * more than 0.
     */
    public EFloat MovePointRight(int places, EContext ctx) {
      return this.MovePointRight(EInteger.FromInt32(places), ctx);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the right.
     * @param bigPlaces The number of binary digit places to move the radix point
     * to the right. If this number is negative, instead moves the radix
     * point to the left by this number's absolute value.
     * @return A number whose exponent is increased by {@code bigPlaces}, but not
     * to more than 0.
     */
    public EFloat MovePointRight(EInteger bigPlaces) {
      return this.MovePointRight(bigPlaces, null);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the right.
     * @param bigPlaces The number of binary digit places to move the radix point
     * to the right. If this number is negative, instead moves the radix
     * point to the left by this number's absolute value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is increased by {@code bigPlaces}, but not
     * to more than 0.
     */
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

    /**
     * Multiplies this arbitrary-precision binary floating-point number by another
     * arbitrary-precision binary floating-point number and returns the
     * result. The exponent for the result is this arbitrary-precision
     * binary floating-point number's exponent plus the other
     * arbitrary-precision binary floating-point number's exponent.
     * @param otherValue Another binary floating-point number.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * binary floating-point number times another arbitrary-precision
     * binary floating-point number.
     * @throws NullPointerException The parameter {@code otherValue} is null.
     */
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

    /**
     * Multiplies this arbitrary-precision binary floating-point number by another
     * arbitrary-precision binary floating-point number and returns the
     * result.
     * @param op Another binary floating-point number.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * binary floating-point number times another arbitrary-precision
     * binary floating-point number.
     */
    public EFloat Multiply(
      EFloat op,
      EContext ctx) {
      return MathValue.Multiply(this, op, ctx);
    }

    /**
     * Multiplies by one binary floating-point number, and then adds another binary
     * floating-point number.
     * @param multiplicand The value to multiply.
     * @param augend The value to add.
     * @return An arbitrary-precision binary floating-point number.
     */
    public EFloat MultiplyAndAdd(
      EFloat multiplicand,
      EFloat augend) {
      return this.MultiplyAndAdd(multiplicand, augend, null);
    }

    /**
     * Multiplies by one value, and then adds another value.
     * @param op The value to multiply.
     * @param augend The value to add.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed. If
     * the precision doesn't indicate a simplified arithmetic, rounding and
     * precision.Divide(exponent) adjustment is done only once, namely,
     * after multiplying and adding.
     * @return The result thisValue * multiplicand + augend.
     */
    public EFloat MultiplyAndAdd(
      EFloat op,
      EFloat augend,
      EContext ctx) {
      return MathValue.MultiplyAndAdd(this, op, augend, ctx);
    }

    /**
     * Multiplies by one value, and then subtracts another value.
     * @param op The value to multiply.
     * @param subtrahend The value to subtract.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed. If
     * the precision doesn't indicate a simplified arithmetic, rounding and
     * precision.Divide(exponent) adjustment is done only once, namely,
     * after multiplying and subtracting.
     * @return The result thisValue * multiplicand - subtrahend.
     * @throws NullPointerException The parameter {@code op} or {@code subtrahend}
     * is null.
     */
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

    /**
     * Gets an object with the same value as this one, but with the sign reversed.
     * @return An arbitrary-precision binary floating-point number. If this value
     * is positive zero, returns negative zero. Returns signaling NaN if
     * this value is signaling NaN. (In this sense, this method is similar
     *  to the "copy-negate" operation in the General Decimal Arithmetic
     * Specification, except this method does not necessarily return a copy
     * of this object.).
     */
    public EFloat Negate() {
      return new EFloat(
          this.unsignedMantissa,
          this.exponent,
          (byte)(this.flags ^ BigNumberFlags.FlagNegative));
    }

    /**
     * Returns a binary floating-point number with the same value as this object
     * but with the sign reversed.
     * @param context An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return An arbitrary-precision binary floating-point number. If this value
     * is positive zero, returns positive zero. Signals FlagInvalid and
     * returns quiet NaN if this value is signaling NaN.
     */
    public EFloat Negate(EContext context) {
      return MathValue.Negate(this, context);
    }

    /**
     * Finds the largest value that's smaller than the given value.
     * @param ctx An arithmetic context object to control the precision and
     * exponent range of the result. The rounding mode from this context is
     * ignored. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags).
     * @return Returns the largest value that's less than the given value. Returns
     * negative infinity if the result is negative infinity. Signals
     * FlagInvalid and returns not-a-number (NaN) if the parameter {@code
     * ctx} is null, the precision is 0, or {@code ctx} has an unlimited
     * exponent range.
     */
    public EFloat NextMinus(EContext ctx) {
      return MathValue.NextMinus(this, ctx);
    }

    /**
     * Finds the smallest value that's greater than the given value.
     * @param ctx An arithmetic context object to control the precision and
     * exponent range of the result. The rounding mode from this context is
     * ignored. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags).
     * @return Returns the smallest value that's greater than the given
     * value.Signals FlagInvalid and returns not-a-number (NaN) if the
     * parameter {@code ctx} is null, the precision is 0, or {@code ctx}
     * has an unlimited exponent range.
     */
    public EFloat NextPlus(EContext ctx) {
      return MathValue.NextPlus(this, ctx);
    }

    /**
     * Finds the next value that is closer to the other object's value than this
     * object's value. Returns a copy of this value with the same sign as
     * the other value if both values are equal.
     * @param otherValue An arbitrary-precision binary floating-point number that
     * the return value will approach.
     * @param ctx An arithmetic context object to control the precision and
     * exponent range of the result. The rounding mode from this context is
     * ignored. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags).
     * @return Returns the next value that is closer to the other object' s value
     * than this object's value. Signals FlagInvalid and returns NaN if the
     * parameter {@code ctx} is null, the precision is 0, or {@code ctx}
     * has an unlimited exponent range.
     */
    public EFloat NextToward(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.NextToward(this, otherValue, ctx);
    }

    /**
     * Rounds this object's value to a given precision, using the given rounding
     * mode and range of exponent, and also converts negative zero to
     * positive zero. The idiom <code>EDecimal.SignalingNaN.Plus(ctx)</code> is
     * useful for triggering an invalid operation and returning
     * not-a-number (NaN) for custom arithmetic operations.
     * @param ctx A context for controlling the precision, rounding mode, and
     * exponent range. Can be null, in which case the precision is
     * unlimited and rounding isn't needed.
     * @return The closest value to this object's value, rounded to the specified
     * precision. If {@code ctx} is null or the precision and exponent
     * range are unlimited, returns the same value as this object (or a
     * quiet NaN if this object is a signaling NaN).
     */
    public EFloat Plus(EContext ctx) {
      return MathValue.Plus(this, ctx);
    }

    /**
     * Raises this object's value to the given exponent, using unlimited precision.
     * @param exponent An arbitrary-precision binary floating-point number
     * expressing the exponent to raise this object's value to.
     * @return This^exponent. Returns not-a-number (NaN) if the exponent has a
     * fractional part.
     */
    public EFloat Pow(EFloat exponent) {
      return this.Pow(exponent, null);
    }

    /**
     * Raises this object's value to the given exponent.
     * @param exponent An arbitrary-precision binary floating-point number
     * expressing the exponent to raise this object's value to.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return This^exponent. Signals the flag FlagInvalid and returns NaN if this
     * object and exponent are both 0; or if this value is less than 0 and
     * the exponent either has a fractional part or is infinity. Signals
     * FlagInvalid and returns not-a-number (NaN) if the parameter {@code
     * ctx} is null or the precision is unlimited (the context's Precision
     * property is 0), and the exponent has a fractional part.
     */
    public EFloat Pow(EFloat exponent, EContext ctx) {
      return MathValue.Power(this, exponent, ctx);
    }

    /**
     * Raises this object's value to the given exponent.
     * @param exponentSmall The exponent to raise this object's value to.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return This^exponent. Signals the flag FlagInvalid and returns NaN if this
     * object and exponent are both 0.
     */
    public EFloat Pow(int exponentSmall, EContext ctx) {
      return this.Pow(EFloat.FromInt64(exponentSmall), ctx);
    }

    /**
     * Raises this object's value to the given exponent.
     * @param exponentSmall The exponent to raise this object's value to.
     * @return This^exponent. Returns not-a-number (NaN) if this object and
     * exponent are both 0.
     */
    public EFloat Pow(int exponentSmall) {
      return this.Pow(EFloat.FromInt64(exponentSmall), null);
    }

    /**
     * Finds the number of digits in this number's significand. Returns 1 if this
     * value is 0, and 0 if this value is infinity or not-a-number (NaN).
     * @return An arbitrary-precision integer.
     */
    public EInteger Precision() {
      if (!this.isFinite()) {
        return EInteger.FromInt32(0);
      }
      return this.isZero() ? EInteger.FromInt32(1) :
        this.getUnsignedMantissa().GetSignedBitLengthAsEInteger();
    }

    /**
     * Returns whether this object's value is an integer.
     * @return {@code true} if this object's value is an integer; otherwise, {@code
     * false}.
     */
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

    /**
     * Returns a binary floating-point number with the same value but a new
     * exponent. <p>Note that this is not always the same as rounding to a
     * given number of binary digit places, since it can fail if the
     * difference between this value's exponent and the desired exponent is
     * too big, depending on the maximum precision. If rounding to a number
     * of binary digit places is desired, it's better to use the
     * RoundToExponent and RoundToIntegral methods instead.</p>
     * <p><b>Remark:</b> This method can be used to implement fixed-point
     * binary arithmetic, in which each binary floating-point number has a
     * fixed number of digits after the radix point. The following code
     * example returns a fixed-point number with up to 20 digits before and
     * exactly 5 digits after the radix point:</p> <pre> &#x2f;&#x2a; After performing arithmetic operations, adjust &#x2f;&#x2a; the number to 5 &#x2f;&#x2a; &#x2a;&#x2f;&#x2a;&#x2f;&#x2a;&#x2f; digits after the radix point number = number.Quantize(EInteger.FromInt32(-5), &#x2f;&#x2a; five digits after the radix point&#x2a;&#x2f; EContext.ForPrecision(25) &#x2f;&#x2a; 25-digit precision);&#x2a;&#x2f;</pre> <p>A fixed-point binary arithmetic in
     * which no digits come after the radix point (a desired exponent of 0)
     *  is considered an "integer arithmetic".</p>
     * @param desiredExponent The desired exponent for the result. The exponent is
     * the number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags). Can be null, in which case the default
     * rounding mode is HalfEven.
     * @return A binary floating-point number with the same value as this object
     * but with the exponent changed. Signals FlagInvalid and returns
     * not-a-number (NaN) if this object is infinity, if the rounded result
     * can't fit the given precision, or if the context defines an exponent
     * range and the given exponent is outside that range.
     */
    public EFloat Quantize(
      EInteger desiredExponent,
      EContext ctx) {
      return this.Quantize(
          EFloat.Create(EInteger.FromInt32(1), desiredExponent),
          ctx);
    }

    /**
     * Returns a binary floating-point number with the same value but a new
     * exponent. <p>Note that this is not always the same as rounding to a
     * given number of binary digit places, since it can fail if the
     * difference between this value's exponent and the desired exponent is
     * too big, depending on the maximum precision. If rounding to a number
     * of binary digit places is desired, it's better to use the
     * RoundToExponent and RoundToIntegral methods instead.</p>
     * <p><b>Remark:</b> This method can be used to implement fixed-point
     * binary arithmetic, in which each binary floating-point number has a
     * fixed number of digits after the radix point. The following code
     * example returns a fixed-point number with up to 20 digits before and
     * exactly 5 digits after the radix point:</p> <pre> &#x2f;&#x2a; After performing arithmetic operations, adjust &#x2f;&#x2a; the number to 5&#x2a;&#x2f;&#x2a;&#x2f; digits after the radix point number = number.Quantize(-5, &#x2f;&#x2a; five digits&#x2a;&#x2f; after the radix point EContext.ForPrecision(25) &#x2f;&#x2a; 25-digit precision);&#x2a;&#x2f;</pre> <p>A fixed-point binary arithmetic in
     * which no digits come after the radix point (a desired exponent of 0)
     *  is considered an "integer arithmetic".</p>
     * @param desiredExponentInt The desired exponent for the result. The exponent
     * is the number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags). Can be null, in which case the default
     * rounding mode is HalfEven.
     * @return A binary floating-point number with the same value as this object
     * but with the exponent changed. Signals FlagInvalid and returns
     * not-a-number (NaN) if this object is infinity, if the rounded result
     * can't fit the given precision, or if the context defines an exponent
     * range and the given exponent is outside that range.
     */
    public EFloat Quantize(
      int desiredExponentInt,
      EContext ctx) {
      return this.Quantize(
          EFloat.Create(1, desiredExponentInt),
          ctx);
    }

    /**
     * Returns a binary floating-point number with the same value as this object
     * but with the same exponent as another binary floating-point number.
     * <p>Note that this is not always the same as rounding to a given
     * number of binary digit places, since it can fail if the difference
     * between this value's exponent and the desired exponent is too big,
     * depending on the maximum precision. If rounding to a number of
     * binary digit places is desired, it's better to use the
     * RoundToExponent and RoundToIntegral methods instead.</p>
     * <p><b>Remark:</b> This method can be used to implement fixed-point
     * binary arithmetic, in which a fixed number of digits come after the
     * radix point. A fixed-point binary arithmetic in which no digits come
     * after the radix point (a desired exponent of 0) is considered an
     *  "integer arithmetic" .</p>
     * @param otherValue A binary floating-point number containing the desired
     * exponent of the result. The significand is ignored. The exponent is
     * the number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the sixteenth
     * (10b^-3, 0.0001b), and 3 means round to the sixteen-place (10b^3,
     * 1000b). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags). Can be null, in which case the default
     * rounding mode is HalfEven.
     * @return A binary floating-point number with the same value as this object
     * but with the exponent changed. Signals FlagInvalid and returns
     * not-a-number (NaN) if the result can't fit the given precision
     * without rounding, or if the arithmetic context defines an exponent
     * range and the given exponent is outside that range.
     */
    public EFloat Quantize(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.Quantize(this, otherValue, ctx);
    }

    /**
     * Returns an object with the same numerical value as this one but with
     * trailing zeros removed from its significand. For example, 1.00
     * becomes 1. <p>If this object's value is 0, changes the exponent to
     * 0.</p>
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return This value with trailing zeros removed. Note that if the result has
     * a very high exponent and the context says to clamp high exponents,
     * there may still be some trailing zeros in the significand.
     */
    public EFloat Reduce(EContext ctx) {
      return MathValue.Reduce(this, ctx);
    }

    /**
     * Returns the remainder that would result when this arbitrary-precision binary
     * floating-point number is divided by another arbitrary-precision
     * binary floating-point number. The remainder is the number that
     * remains when the absolute value of this arbitrary-precision binary
     * floating-point number is divided (as though by
     * DivideToIntegerZeroScale) by the absolute value of the other
     * arbitrary-precision binary floating-point number; the remainder has
     * the same sign (positive or negative) as this arbitrary-precision
     * binary floating-point number.
     * @param divisor An arbitrary-precision binary floating-point number.
     * @param ctx The parameter {@code ctx} is an EContext object.
     * @return The remainder that would result when this arbitrary-precision binary
     * floating-point number is divided by another arbitrary-precision
     * binary floating-point number. Signals FlagDivideByZero and returns
     * infinity if the divisor (this arbitrary-precision binary
     * floating-point number) is 0 and the dividend (the other
     * arbitrary-precision binary floating-point number) is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor
     * and the dividend are 0, or if the result of the division doesn't fit
     * the given precision.
     */
    public EFloat Remainder(
      EFloat divisor,
      EContext ctx) {
      return MathValue.Remainder(this, divisor, ctx, true);
    }

    /**
     * Finds the remainder that results when dividing two arbitrary-precision
     * binary floating-point numbers. The remainder is the value that
     * remains when the absolute value of this object is divided by the
     * absolute value of the other object; the remainder has the same sign
     * (positive or negative) as this object's value.
     * @param divisor An arbitrary-precision binary floating-point number.
     * @param ctx The parameter {@code ctx} is an EContext object.
     * @return The remainder of the two numbers. Signals FlagInvalid and returns
     * not-a-number (NaN) if the divisor is 0, or if the result doesn't fit
     * the given precision.
     */
    public EFloat RemainderNoRoundAfterDivide(
      EFloat divisor,
      EContext ctx) {
      return MathValue.Remainder(this, divisor, ctx, false);
    }

    /**
     * Calculates the remainder of a number by the formula <code>"this" - (("this" /
     *  "divisor") * "divisor")</code>.
     * @param divisor The number to divide by.
     * @return An arbitrary-precision binary floating-point number.
     */
    public EFloat RemainderNaturalScale(
      EFloat divisor) {
      return this.RemainderNaturalScale(divisor, null);
    }

    /**
     * Calculates the remainder of a number by the formula "this" - (("this" /
     *  "divisor") * "divisor").
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only in
     * the division portion of the remainder calculation; as a result, it's
     * possible for the return value to have a higher precision than given
     * in this context. Flags will be set on the given context only if the
     * context's {@code HasFlags} is true and the integer part of the
     * division result doesn't fit the precision and exponent range without
     * rounding. Can be null, in which the precision is unlimited and no
     * additional rounding, other than the rounding down to an integer
     * after division, is needed.
     * @return An arbitrary-precision binary floating-point number.
     */
    public EFloat RemainderNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return this.Subtract(
        this.DivideToIntegerNaturalScale(divisor, null).Multiply(divisor, null),
        ctx);
    }

    /**
     * Finds the distance to the closest multiple of the given divisor, based on
     * the result of dividing this object's value by another object's
     * value. <ul> <li>If this and the other object divide evenly, the
     * result is 0.</li> <li>If the remainder's absolute value is less than
     * half of the divisor's absolute value, the result has the same sign
     * as this object and will be the distance to the closest
     * multiple.</li> <li>If the remainder's absolute value is more than
     * half of the divisor's absolute value, the result has the opposite
     * sign of this object and will be the distance to the closest
     * multiple.</li> <li>If the remainder's absolute value is exactly half
     * of the divisor's absolute value, the result has the opposite sign of
     * this object if the quotient, rounded down, is odd, and has the same
     * sign as this object if the quotient, rounded down, is even, and the
     * result's absolute value is half of the divisor's absolute
     *  value.</li></ul> This function is also known as the "IEEE Remainder"
     * function.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision. The
     * rounding and exponent range settings of this context are ignored
     * (the rounding mode is always treated as HalfEven). If {@code
     * HasFlags} of the context is true, will also store the flags
     * resulting from the operation (the flags are in addition to the
     * pre-existing flags). Can be null, in which the precision is
     * unlimited.
     * @return The distance of the closest multiple. Signals FlagInvalid and
     * returns not-a-number (NaN) if the divisor is 0, or either the result
     * of integer division (the quotient) or the remainder wouldn't fit the
     * given precision.
     */
    public EFloat RemainderNear(
      EFloat divisor,
      EContext ctx) {
      return MathValue.RemainderNear(this, divisor, ctx);
    }

    /**
     * Returns a binary floating-point number with the same value as this object
     * but rounded to a new exponent if necessary. The resulting number's
     * Exponent property will not necessarily be the given exponent; use
     * the Quantize method instead to give the result a particular
     * exponent.
     * @param exponent The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary floating-point number rounded to the closest value
     * representable in the given precision. If the result can't fit the
     * precision, additional digits are discarded to make it fit. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to the
     * given exponent when rounding, and the given exponent is outside of
     * the valid range of the arithmetic context.
     */
    public EFloat RoundToExponent(
      EInteger exponent,
      EContext ctx) {
      return MathValue.RoundToExponentSimple(this, exponent, ctx);
    }

    /**
     * Returns a binary floating-point number with the same value as this object
     * but rounded to a new exponent if necessary. The resulting number's
     * Exponent property will not necessarily be the given exponent; use
     * the Quantize method instead to give the result a particular
     * exponent.
     * @param exponentSmall The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary floating-point number rounded to the closest value
     * representable in the given precision. If the result can't fit the
     * precision, additional digits are discarded to make it fit. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to the
     * given exponent when rounding, and the given exponent is outside of
     * the valid range of the arithmetic context.
     */
    public EFloat RoundToExponent(
      int exponentSmall,
      EContext ctx) {
      return this.RoundToExponent(EInteger.FromInt32(exponentSmall), ctx);
    }

    /**
     * Returns a binary floating-point number with the same value as this object
     * but rounded to the given exponent, and signals an inexact flag if
     * the result would be inexact. The resulting number's Exponent
     * property will not necessarily be the given exponent; use the
     * Quantize method instead to give the result a particular exponent.
     * @param exponent The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary floating-point number rounded to the closest value
     * representable in the given precision. Signals FlagInvalid and
     * returns not-a-number (NaN) if the result can't fit the given
     * precision without rounding. Signals FlagInvalid and returns
     * not-a-number (NaN) if the arithmetic context defines an exponent
     * range, the new exponent must be changed to the given exponent when
     * rounding, and the given exponent is outside of the valid range of
     * the arithmetic context.
     */
    public EFloat RoundToExponentExact(
      EInteger exponent,
      EContext ctx) {
      return MathValue.RoundToExponentExact(this, exponent, ctx);
    }

    /**
     * Returns a binary number with the same value as this object but rounded to
     * the given exponent. The resulting number's Exponent property will
     * not necessarily be the given exponent; use the Quantize method
     * instead to give the result a particular exponent.
     * @param exponent The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the eighth
     * (10^-1, 1/8), and 3 means round to the eight (2^3, 8). A value of 0
     * rounds the number to an integer.
     * @param rounding Desired mode for rounding this object's value.
     * @return A binary number rounded to the closest value representable in the
     * given precision.
     */
    public EFloat RoundToExponentExact(
      EInteger exponent,
      ERounding rounding) {
      return MathValue.RoundToExponentExact(
          this,
          exponent,
          EContext.Unlimited.WithRounding(rounding));
    }

    /**
     * Returns a binary floating-point number with the same value as this object
     * but rounded to the given exponent represented as a 32-bit signed
     * integer, and signals an inexact flag if the result would be inexact.
     * The resulting number's Exponent property will not necessarily be the
     * given exponent; use the Quantize method instead to give the result a
     * particular exponent.
     * @param exponentSmall The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary floating-point number rounded to the closest value
     * representable in the given precision. Signals FlagInvalid and
     * returns not-a-number (NaN) if the result can't fit the given
     * precision without rounding. Signals FlagInvalid and returns
     * not-a-number (NaN) if the arithmetic context defines an exponent
     * range, the new exponent must be changed to the given exponent when
     * rounding, and the given exponent is outside of the valid range of
     * the arithmetic context.
     */
    public EFloat RoundToExponentExact(
      int exponentSmall,
      EContext ctx) {
      return this.RoundToExponentExact(EInteger.FromInt32(exponentSmall), ctx);
    }

    /**
     * Returns a binary floating-point number with the same value as this object
     * but rounded to an integer, and signals an inexact flag if the result
     * would be inexact. The resulting number's Exponent property will not
     * necessarily be 0; use the Quantize method instead to give the result
     * an exponent of 0.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary floating-point number rounded to the closest integer
     * representable in the given precision. Signals FlagInvalid and
     * returns not-a-number (NaN) if the result can't fit the given
     * precision without rounding. Signals FlagInvalid and returns
     * not-a-number (NaN) if the arithmetic context defines an exponent
     * range, the new exponent must be changed to 0 when rounding, and 0 is
     * outside of the valid range of the arithmetic context.
     */
    public EFloat RoundToIntegerExact(EContext ctx) {
      return MathValue.RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    /**
     * Returns a binary floating-point number with the same value as this object
     * but rounded to an integer, without adding the <code>FlagInexact</code> or
     * <code>FlagRounded</code> flags. The resulting number's Exponent property
     * will not necessarily be 0; use the Quantize method instead to give
     * the result an exponent of 0.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags), except that this function will never add
     * the {@code FlagRounded} and {@code FlagInexact} flags (the only
     * difference between this and RoundToExponentExact). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary floating-point number rounded to the closest integer
     * representable in the given precision. If the result can't fit the
     * precision, additional digits are discarded to make it fit. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to 0
     * when rounding, and 0 is outside of the valid range of the arithmetic
     * context.
     */
    public EFloat RoundToIntegerNoRoundedFlag(EContext ctx) {
      return MathValue.RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

    /**
     * Returns a binary floating-point number with the same value as this object
     * but rounded to an integer, and signals an inexact flag if the result
     * would be inexact.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary floating-point number rounded to the closest integer
     * representable in the given precision. Signals FlagInvalid and
     * returns not-a-number (NaN) if the result can't fit the given
     * precision without rounding. Signals FlagInvalid and returns
     * not-a-number (NaN) if the arithmetic context defines an exponent
     * range, the new exponent must be changed to 0 when rounding, and 0 is
     * outside of the valid range of the arithmetic context.
     * @deprecated Renamed to RoundToIntegerExact.
 */
@Deprecated
    public EFloat RoundToIntegralExact(EContext ctx) {
      return MathValue.RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    /**
     * Returns a binary floating-point number with the same value as this object
     * but rounded to an integer, without adding the <code>FlagInexact</code> or
     * <code>FlagRounded</code> flags.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags), except that this function will never add
     * the {@code FlagRounded} and {@code FlagInexact} flags (the only
     * difference between this and RoundToExponentExact). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary floating-point number rounded to the closest integer
     * representable in the given precision. If the result can't fit the
     * precision, additional digits are discarded to make it fit. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to 0
     * when rounding, and 0 is outside of the valid range of the arithmetic
     * context.
     * @deprecated Renamed to RoundToIntegerNoRoundedFlag.
 */
@Deprecated
    public EFloat RoundToIntegralNoRoundedFlag(EContext ctx) {
      return MathValue.RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

    /**
     * Rounds this object's value to a given precision, using the given rounding
     * mode and range of exponent.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The closest value to this object's value, rounded to the specified
     * precision. Returns the same value as this object if {@code ctx} is
     * null or the precision and exponent range are unlimited.
     */
    public EFloat RoundToPrecision(EContext ctx) {
      return MathValue.RoundToPrecision(this, ctx);
    }

    /**
     * Returns a number in which the value of this object is rounded to fit the
     * maximum precision allowed if it has more significant digits than the
     * maximum precision. The maximum precision allowed is given in an
     * arithmetic context. This method is designed for preparing operands
     *  to a custom arithmetic operation in accordance with the "simplified"
     * arithmetic given in Appendix A of the General Decimal Arithmetic
     * Specification.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited. Signals the flag LostDigits
     * if the input number has greater precision than allowed and was
     * rounded to a different numerical value in order to fit the
     * precision.
     * @return This object rounded to the given precision. Returns this object and
     * signals no flags if {@code ctx} is null or specifies an unlimited
     * precision, if this object is infinity or not-a-number (including
     * signaling NaN), or if the number's value has no more significant
     * digits than the maximum precision given in {@code ctx}.
     */
    public EFloat PreRound(EContext ctx) {
      return NumberUtility.PreRound(this, ctx, MathValue);
    }

    /**
     * Returns a number similar to this number but with the scale adjusted.
     * @param places The parameter {@code places} is a 32-bit signed integer.
     * @return An arbitrary-precision binary floating-point number.
     */
    public EFloat ScaleByPowerOfTwo(int places) {
      return this.ScaleByPowerOfTwo(EInteger.FromInt32(places), null);
    }

    /**
     * Returns a number similar to this number but with the scale adjusted.
     * @param places The parameter {@code places} is a 32-bit signed integer.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null.
     * @return An arbitrary-precision binary floating-point number.
     */
    public EFloat ScaleByPowerOfTwo(int places, EContext ctx) {
      return this.ScaleByPowerOfTwo(EInteger.FromInt32(places), ctx);
    }

    /**
     * Returns a number similar to this number but with the scale adjusted.
     * @param bigPlaces An arbitrary-precision integer.
     * @return A number whose exponent is increased by {@code bigPlaces}.
     */
    public EFloat ScaleByPowerOfTwo(EInteger bigPlaces) {
      return this.ScaleByPowerOfTwo(bigPlaces, null);
    }

    /**
     * Returns a number similar to this number but with its scale adjusted.
     * @param bigPlaces An arbitrary-precision integer.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null.
     * @return An arbitrary-precision binary floating-point number.
     * @throws NullPointerException The parameter {@code bigPlaces} is null.
     */
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

    /**
     * Finds the square root of this object's value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the square root function's results are generally
     * not exact for many inputs.</i> (Unlike in the General Binary
     * Arithmetic Specification, any rounding mode is allowed.).
     * @return The square root. Signals the flag FlagInvalid and returns NaN if
     * this object is less than 0 (the square root would be a complex
     * number, but the return value is still NaN). Signals FlagInvalid and
     * returns not-a-number (NaN) if the parameter {@code ctx} is null or
     * the precision is unlimited (the context's Precision property is 0).
     */
    public EFloat Sqrt(EContext ctx) {
      return MathValue.SquareRoot(this, ctx);
    }

    /**
     * Finds the square root of this object's value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the square root function's results are generally
     * not exact for many inputs.</i> (Unlike in the General Binary
     * Arithmetic Specification, any rounding mode is allowed.).
     * @return The square root. Signals the flag FlagInvalid and returns NaN if
     * this object is less than 0 (the square root would be a complex
     * number, but the return value is still NaN). Signals FlagInvalid and
     * returns not-a-number (NaN) if the parameter {@code ctx} is null or
     * the precision is unlimited (the context's Precision property is 0).
     * @deprecated Renamed to Sqrt.
 */
@Deprecated
    public EFloat SquareRoot(EContext ctx) {
      return MathValue.SquareRoot(this, ctx);
    }

    /**
     * Subtracts an arbitrary-precision binary floating-point number from this
     * arbitrary-precision binary floating-point number and returns the
     * result. The exponent for the result is the lower of this
     * arbitrary-precision binary floating-point number's exponent and the
     * other arbitrary-precision binary floating-point number's exponent.
     * @param otherValue The number to subtract from this instance's value.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision binary floating-point number minus another
     * arbitrary-precision binary floating-point number. If this
     * arbitrary-precision binary floating-point number is not-a-number
     * (NaN), returns NaN.
     */
    public EFloat Subtract(EFloat otherValue) {
      return this.Subtract(otherValue, null);
    }

    /**
     * Subtracts an arbitrary-precision binary floating-point number from this
     * arbitrary-precision binary floating-point number and returns the
     * result.
     * @param otherValue The number to subtract from this instance's value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision binary floating-point number minus another
     * arbitrary-precision binary floating-point number. If this
     * arbitrary-precision binary floating-point number is not-a-number
     * (NaN), returns NaN.
     * @throws NullPointerException The parameter {@code otherValue} is null.
     */
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

    /**
     * Converts this value to a 64-bit floating-point number encoded in the IEEE
     * 754 binary64 format.
     * @return This number, converted to a 64-bit floating-point number encoded in
     * the IEEE 754 binary64 format. The return value can be positive
     * infinity or negative infinity if this value exceeds the range of a
     * 64-bit floating point number.
     */
    public double ToDouble() {
      long value = this.ToDoubleBits();
      return Double.longBitsToDouble(value);
    }

    /**
     * Converts this value to its closest equivalent as 32-bit floating-point
     * number, expressed as an integer in the IEEE 754 binary32 format. The
     * half-even rounding mode is used. <p>If this value is a NaN, sets the
     * high bit of the 32-bit floating point number's significand area for
     * a quiet NaN, and clears it for a signaling NaN. Then the other bits
     * of the significand area are set to the lowest bits of this object's
     * unsigned significand, and the next-highest bit of the significand
     * area is set if those bits are all zeros and this is a signaling
     * NaN.</p>
     * @return The closest 32-bit binary floating-point number to this value,
     * expressed as an integer in the IEEE 754 binary32 format. The return
     * value can be positive infinity or negative infinity if this value
     * exceeds the range of a 32-bit floating point number.
     */
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

    /**
     * Converts this value to its closest equivalent as a 64-bit floating-point
     * number, expressed as an integer in the IEEE 754 binary64 format. The
     * half-even rounding mode is used. <p>If this value is a NaN, sets the
     * high bit of the 64-bit floating point number's significand area for
     * a quiet NaN, and clears it for a signaling NaN. Then the other bits
     * of the significand area are set to the lowest bits of this object's
     * unsigned significand, and the next-highest bit of the significand
     * area is set if those bits are all zeros and this is a signaling
     * NaN.</p>
     * @return The closest 64-bit binary floating-point number to this value,
     * expressed as an integer in the IEEE 754 binary64 format. The return
     * value can be positive infinity or negative infinity if this value
     * exceeds the range of a 64-bit floating point number.
     */
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

    /**
     * Converts this value to an arbitrary-precision decimal number.
     * @return This number, converted to an arbitrary-precision decimal number.
     */
    public EDecimal ToEDecimal() {
      return EDecimal.FromEFloat(this);
    }

    /**
     * Converts this value to an arbitrary-precision integer. Any fractional part
     * of this value will be discarded when converting to an
     * arbitrary-precision integer. Note that depending on the value,
     * especially the exponent, generating the arbitrary-precision integer
     * may require a huge amount of memory. Use the ToSizedEInteger method
     * to convert a number to an EInteger only if the integer fits in a
     * bounded bit range; that method will throw an exception on overflow.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN).
     */
    public EInteger ToEInteger() {
      return this.ToEIntegerInternal(false);
    }

    /**
     * Converts this value to an arbitrary-precision integer, checking whether the
     * value contains a fractional part. Note that depending on the value,
     * especially the exponent, generating the arbitrary-precision integer
     * may require a huge amount of memory. Use the ToSizedEIntegerIfExact
     * method to convert a number to an EInteger only if the integer fits
     * in a bounded bit range; that method will throw an exception on
     * overflow.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN).
     * @throws ArithmeticException This object's value is not an exact integer.
     * @deprecated Renamed to ToEIntegerIfExact.
 */
@Deprecated
    public EInteger ToEIntegerExact() {
      return this.ToEIntegerInternal(true);
    }

    /**
     * Converts this value to an arbitrary-precision integer, checking whether the
     * value contains a fractional part. Note that depending on the value,
     * especially the exponent, generating the arbitrary-precision integer
     * may require a huge amount of memory. Use the ToSizedEIntegerIfExact
     * method to convert a number to an EInteger only if the integer fits
     * in a bounded bit range; that method will throw an exception on
     * overflow.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN).
     * @throws ArithmeticException This object's value is not an exact integer.
     */
    public EInteger ToEIntegerIfExact() {
      return this.ToEIntegerInternal(true);
    }

    /**
     * Converts this value to an arbitrary-precision decimal number, then returns
     * the value of that decimal's ToEngineeringString method.
     * @return A text string.
     */
    public String ToEngineeringString() {
      return this.ToEDecimal().ToEngineeringString();
    }

    /**
     * Converts this value to an arbitrary-precision decimal number.
     * @return An arbitrary-precision decimal number.
     * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal() {
      return EDecimal.FromEFloat(this);
    }

    /**
     * Converts this value to a string, but without exponential notation.
     * @return A text string.
     */
    public String ToPlainString() {
      return this.ToEDecimal().ToPlainString();
    }

    private String ToDebugString() {
      return "[" + this.getMantissa().ToRadixString(2) +
        "," + this.getMantissa().GetUnsignedBitLengthAsEInteger() +
        "," + this.getExponent() + "]";
    }

    /**
     * Returns a string representation of this number's value after rounding that
     * value to the given precision (using the given arithmetic context,
     * such as <code>EContext.Binary64</code>). If the number after rounding is
     * neither infinity nor not-a-number (NaN), returns the shortest
     * decimal form of this number's value (in terms of decimal digits
     * starting with the first nonzero digit and ending with the last
     * nonzero digit) that results in the rounded number after the decimal
     * form is converted to binary floating-point format (using the given
     * arithmetic context).<p> <p>The following example converts an EFloat
     * number to its shortest round-tripping decimal form using the same
     * precision as the <code>double</code> type in Java and.NET:</p> <pre> string str = efloat.ToShortestString(EContext.Binary64); </pre> </p>
     * @param ctx An arithmetic context to control precision (in bits), rounding,
     * and exponent range of the rounded number. If {@code HasFlags} of the
     * context is true, will also store the flags resulting from the
     * operation (the flags are in addition to the pre-existing flags). Can
     * be null. If this parameter is null or defines no maximum precision,
     * returns the same value as the toString() method.
     * @return Shortest decimal form of this number's value for the given
     * arithmetic context. The text string will be in exponential notation
     * (expressed as a number 1 or greater, but less than 10, times a power
     * of 10) if the number's first nonzero decimal digit is more than five
     * digits after the decimal point, or if the number's exponent is
     * greater than 0 and its value is 10, 000, 000 or greater.
     */
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

    /**
     * Converts this value to its closest equivalent as a 32-bit floating-point
     * number. The half-even rounding mode is used. <p>If this value is a
     * NaN, sets the high bit of the 32-bit floating point number's
     * significand area for a quiet NaN, and clears it for a signaling NaN.
     * Then the other bits of the significand area are set to the lowest
     * bits of this object's unsigned significand, and the next-highest bit
     * of the significand area is set if those bits are all zeros and this
     * is a signaling NaN. Unfortunately, in the.NET implementation, the
     * return value of this method may be a quiet NaN even if a signaling
     * NaN would otherwise be generated.</p>
     * @return The closest 32-bit binary floating-point number to this value. The
     * return value can be positive infinity or negative infinity if this
     * value exceeds the range of a 32-bit floating point number.
     */
    public float ToSingle() {
      int sb = this.ToSingleBits();
      return Float.intBitsToFloat(sb);
    }

    /**
     * Converts this number's value to a text string.
     * @return A string representation of this object. The value is converted to
     * decimal and the decimal form of this number's value is returned. The
     * text string will be in exponential notation (expressed as a number 1
     * or greater, but less than 10, times a power of 10) if the converted
     * number's scale is positive or if the number's first nonzero decimal
     * digit is more than five digits after the decimal point.
     */
    @Override public String toString() {
      return EDecimal.FromEFloat(this).toString();
    }

    /**
     * Returns the unit in the last place. The significand will be 1 and the
     * exponent will be this number's exponent. Returns 1 with an exponent
     * of 0 if this number is infinity or not-a-number (NaN).
     * @return An arbitrary-precision binary floating-point number.
     */
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

    /**
     * Converts this value to an arbitrary-precision integer by discarding its
     * fractional part and checking whether the resulting integer overflows
     * the given signed bit count.
     * @param maxBitLength The maximum number of signed bits the integer can have.
     * The integer's value may not be less than -(2^maxBitLength) or
     * greater than (2^maxBitLength) - 1.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN), or this number's value, once converted to an integer by
     * discarding its fractional part, is less than -(2^maxBitLength) or
     * greater than (2^maxBitLength) - 1.
     */
    public EInteger ToSizedEInteger(int maxBitLength) {
      return this.ToSizedEInteger(maxBitLength, false);
    }

    /**
     * Converts this value to an arbitrary-precision integer, only if this number's
     * value is an exact integer and that integer does not overflow the
     * given signed bit count.
     * @param maxBitLength The maximum number of signed bits the integer can have.
     * The integer's value may not be less than -(2^maxBitLength) or
     * greater than (2^maxBitLength) - 1.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN), or this number's value, once converted to an integer by
     * discarding its fractional part, is less than -(2^maxBitLength) or
     * greater than (2^maxBitLength) - 1.
     * @throws ArithmeticException This object's value is not an exact integer.
     */
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
      /**
       * This is an internal method.
       * @return A 32-bit signed integer.
       */
      public int GetRadix() {
        return 2;
      }

      /**
       * This is an internal method.
       * @param value An arbitrary-precision binary floating-point number.
       * @return A 32-bit signed integer.
       */
      public int GetSign(EFloat value) {
        return value.signum();
      }

      /**
       * This is an internal method.
       * @param value An arbitrary-precision binary floating-point number.
       * @return An arbitrary-precision integer.
       */
      public EInteger GetMantissa(EFloat value) {
        return value.unsignedMantissa.ToEInteger();
      }

      /**
       * This is an internal method.
       * @param value An arbitrary-precision binary floating-point number.
       * @return An arbitrary-precision integer.
       */
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

      /**
       * This is an internal method.
       * @param bigint An arbitrary-precision integer.
       * @param lastDigit The parameter {@code lastDigit} is a 32-bit signed integer.
       * @param olderDigits The parameter {@code olderDigits} is a 32-bit signed
       * integer.
       * @return An IShiftAccumulator object.
       */
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

      /**
       * This is an internal method.
       * @param num An arbitrary-precision integer.
       * @param den Another arbitrary-precision integer.
       * @return A FastInteger object.
       */
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

      /**
       * This is an internal method.
       * @param bigint Another arbitrary-precision integer.
       * @param power A fast integer.
       * @return An arbitrary-precision integer.
       */
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

      /**
       * This is an internal method.
       * @param value An arbitrary-precision binary floating-point number.
       * @return A 32-bit signed integer.
       */
      public int GetFlags(EFloat value) {
        return value.flags;
      }

      /**
       * This is an internal method.
       * @param mantissa The parameter {@code mantissa} is a Numbers.EInteger object.
       * @param exponent The parameter {@code exponent} is an internal parameter.
       * @param flags The parameter {@code flags} is an internal parameter.
       * @return An arbitrary-precision binary floating-point number.
       */
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

      /**
       * This is an internal method.
       * @return A 32-bit signed integer.
       */
      public int GetArithmeticSupport() {
        return BigNumberFlags.FiniteAndNonFinite;
      }

      /**
       * This is an internal method.
       * @param val The parameter {@code val} is a 32-bit signed integer.
       * @return An arbitrary-precision binary floating-point number.
       */
      public EFloat ValueOf(int val) {
        return FromInt32(val);
      }
    }

    /**
     * Returns one added to this arbitrary-precision binary floating-point number.
     * @return The given arbitrary-precision binary floating-point number plus one.
     */
    public EFloat Increment() {
      return this.Add(1);
    }

    /**
     * Returns one subtracted from this arbitrary-precision binary floating-point
     * number.
     * @return The given arbitrary-precision binary floating-point number minus
     * one.
     */
    public EFloat Decrement() {
      return this.Subtract(1);
    }

    // Begin integer conversions

    /**
     * Converts this number's value to a byte (from 0 to 255) if it can fit in a
     * byte (from 0 to 255) after converting it to an integer by discarding
     * its fractional part.
     * @return This number's value, truncated to a byte (from 0 to 255).
     * @throws ArithmeticException This value is infinity or not-a-number, or the
     * number, once converted to an integer by discarding its fractional
     * part, is less than 0 or greater than 255.
     */
    public byte ToByteChecked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((byte)0) :
        this.ToEInteger().ToByteChecked();
    }

    /**
     * Converts this number's value to an integer by discarding its fractional
     * part, and returns the least-significant bits of its two's-complement
     * form as a byte (from 0 to 255).
     * @return This number, converted to a byte (from 0 to 255). Returns 0 if this
     * value is infinity or not-a-number.
     */
    public byte ToByteUnchecked() {
      return this.isFinite() ? this.ToEInteger().ToByteUnchecked() : (byte)0;
    }

    /**
     * Converts this number's value to a byte (from 0 to 255) if it can fit in a
     * byte (from 0 to 255) without rounding to a different numerical
     * value.
     * @return This number's value as a byte (from 0 to 255).
     * @throws ArithmeticException This value is infinity or not-a-number, is not
     * an exact integer, or is less than 0 or greater than 255.
     */
    public byte ToByteIfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((byte)0) : this.ToEIntegerIfExact().ToByteChecked();
    }

    /**
     * Converts a byte (from 0 to 255) to an arbitrary-precision binary
     * floating-point number.
     * @param inputByte The number to convert as a byte (from 0 to 255).
     * @return This number's value as an arbitrary-precision binary floating-point
     * number.
     */
    public static EFloat FromByte(byte inputByte) {
      int val = ((int)inputByte) & 0xff;
      return FromInt32(val);
    }

    /**
     * Converts this number's value to a 16-bit signed integer if it can fit in a
     * 16-bit signed integer after converting it to an integer by
     * discarding its fractional part.
     * @return This number's value, truncated to a 16-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, or the
     * number, once converted to an integer by discarding its fractional
     * part, is less than -32768 or greater than 32767.
     */
    public short ToInt16Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((short)0) :
        this.ToEInteger().ToInt16Checked();
    }

    /**
     * Converts this number's value to an integer by discarding its fractional
     * part, and returns the least-significant bits of its two's-complement
     * form as a 16-bit signed integer.
     * @return This number, converted to a 16-bit signed integer. Returns 0 if this
     * value is infinity or not-a-number.
     */
    public short ToInt16Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt16Unchecked() : (short)0;
    }

    /**
     * Converts this number's value to a 16-bit signed integer if it can fit in a
     * 16-bit signed integer without rounding to a different numerical
     * value.
     * @return This number's value as a 16-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, is not
     * an exact integer, or is less than -32768 or greater than 32767.
     */
    public short ToInt16IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((short)0) :
        this.ToEIntegerIfExact().ToInt16Checked();
    }

    /**
     * Converts a 16-bit signed integer to an arbitrary-precision binary
     * floating-point number.
     * @param inputInt16 The number to convert as a 16-bit signed integer.
     * @return This number's value as an arbitrary-precision binary floating-point
     * number.
     */
    public static EFloat FromInt16(short inputInt16) {
      int val = (int)inputInt16;
      return FromInt32(val);
    }

    /**
     * Converts this number's value to a 32-bit signed integer if it can fit in a
     * 32-bit signed integer after converting it to an integer by
     * discarding its fractional part.
     * @return This number's value, truncated to a 32-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, or the
     * number, once converted to an integer by discarding its fractional
     * part, is less than -2147483648 or greater than 2147483647.
     */
    public int ToInt32Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((int)0) :
        this.ToEInteger().ToInt32Checked();
    }

    /**
     * Converts this number's value to an integer by discarding its fractional
     * part, and returns the least-significant bits of its two's-complement
     * form as a 32-bit signed integer.
     * @return This number, converted to a 32-bit signed integer. Returns 0 if this
     * value is infinity or not-a-number.
     */
    public int ToInt32Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt32Unchecked() : (int)0;
    }

    /**
     * Converts this number's value to a 32-bit signed integer if it can fit in a
     * 32-bit signed integer without rounding to a different numerical
     * value.
     * @return This number's value as a 32-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, is not
     * an exact integer, or is less than -2147483648 or greater than
     * 2147483647.
     */
    public int ToInt32IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? ((int)0) : this.ToEIntegerIfExact().ToInt32Checked();
    }

    /**
     * Converts a boolean value (either true or false) to an arbitrary-precision
     * binary floating-point number.
     * @param boolValue Either true or false.
     * @return The number 1 if {@code boolValue} is true, otherwise, 0.
     */
    public static EFloat FromBoolean(boolean boolValue) {
      return boolValue ? EFloat.One : EFloat.Zero;
    }

    /**
     * Converts a 32-bit signed integer to an arbitrary-precision binary
     * floating-point number.
     * @param inputInt32 The number to convert as a 32-bit signed integer.
     * @return This number's value as an arbitrary-precision binary floating-point
     * number.
     */
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

    /**
     * Converts this number's value to a 64-bit signed integer if it can fit in a
     * 64-bit signed integer after converting it to an integer by
     * discarding its fractional part.
     * @return This number's value, truncated to a 64-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, or the
     * number, once converted to an integer by discarding its fractional
     * part, is less than -9223372036854775808 or greater than
     * 9223372036854775807.
     */
    public long ToInt64Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? 0L :
        this.ToEInteger().ToInt64Checked();
    }

    /**
     * Converts this number's value to an integer by discarding its fractional
     * part, and returns the least-significant bits of its two's-complement
     * form as a 64-bit signed integer.
     * @return This number, converted to a 64-bit signed integer. Returns 0 if this
     * value is infinity or not-a-number.
     */
    public long ToInt64Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt64Unchecked() : 0L;
    }

    /**
     * Converts this number's value to a 64-bit signed integer if it can fit in a
     * 64-bit signed integer without rounding to a different numerical
     * value.
     * @return This number's value as a 64-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, is not
     * an exact integer, or is less than -9223372036854775808 or greater
     * than 9223372036854775807.
     */
    public long ToInt64IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.isZero() ? 0L : this.ToEIntegerIfExact().ToInt64Checked();
    }

    /**
     * Converts a 64-bit signed integer to an arbitrary-precision binary
     * floating-point number.
     * @param inputInt64 The number to convert as a 64-bit signed integer.
     * @return This number's value as an arbitrary-precision binary floating-point
     * number.
     */
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
