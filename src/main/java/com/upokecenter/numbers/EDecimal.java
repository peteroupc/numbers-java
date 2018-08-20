package com.upokecenter.numbers;
/*
Written by Peter O. in 2013.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

    /**
     * Represents an arbitrary-precision decimal floating-point number. (The "E"
     * stands for "extended", meaning that instances of this class can be
     * values other than numbers proper, such as infinity and not-a-number.)
     * <p><b>About decimal arithmetic</b> </p> <p>Decimal (base-10)
     * arithmetic, such as that provided by this class, is appropriate for
     * calculations involving such real-world data as prices and other sums
     * of money, tax rates, and measurements. These calculations often
     * involve multiplying or dividing one decimal with another decimal, or
     * performing other operations on decimal numbers. Many of these
     * calculations also rely on rounding behavior in which the result after
     * rounding is a decimal number (for example, multiplying a price by a
     * premium rate, then rounding, should result in a decimal amount of
     * money).</p> <p>On the other hand, most implementations of
     * <code>float</code> and <code>double</code> , including in C# and Java, store
     * numbers in a binary (base-2) floating-point format and use binary
     * floating-point arithmetic. Many decimal numbers can't be represented
     * exactly in binary floating-point format (regardless of its length).
     * Applying binary arithmetic to numbers intended to be decimals can
     * sometimes lead to unintuitive results, as is shown in the description
     * for the FromDouble() method of this class.</p> <p><b>About EDecimal
     * instances</b> </p> <p>Each instance of this class consists of an
     * integer mantissa (significand) and an integer exponent, both
     * arbitrary-precision. The value of the number equals mantissa
     * (significand) * 10^exponent.</p> <p>The mantissa (significand) is the
     * value of the digits that make up a number, ignoring the decimal point
     * and exponent. For example, in the number 2356.78, the mantissa
     * (significand) is 235678. The exponent is where the "floating" decimal
     * point of the number is located. A positive exponent means "move it to
     * the right", and a negative exponent means "move it to the left." In
     * the example 2, 356.78, the exponent is -2, since it has 2 decimal
     * places and the decimal point is "moved to the left by 2." Therefore,
     * in the arbitrary-precision decimal representation, this number would
     * be stored as 235678 * 10^-2.</p> <p>The mantissa (significand) and
     * exponent format preserves trailing zeros in the number's value. This
     * may give rise to multiple ways to store the same value. For example,
     * 1.00 and 1 would be stored differently, even though they have the
     * same value. In the first case, 100 * 10^-2 (100 with decimal point
     * moved left by 2), and in the second case, 1 * 10^0 (1 with decimal
     * point moved 0).</p> <p>This class also supports values for negative
     * zero, not-a-number (NaN) values, and infinity. <b>Negative zero</b>
     * is generally used when a negative number is rounded to 0; it has the
     * same mathematical value as positive zero. <b>Infinity</b> is
     * generally used when a non-zero number is divided by zero, or when a
     * very high or very low number can't be represented in a given exponent
     * range. <b>Not-a-number</b> is generally used to signal errors.</p>
     * <p>This class implements the General Decimal Arithmetic Specification
     * version 1.70 (except part of chapter 6):
     * <code>http://speleotrove.com/decimal/decarith.html</code> </p> <p><b>Errors
     * and Exceptions</b> </p> <p>Passing a signaling NaN to any arithmetic
     * operation shown here will signal the flag FlagInvalid and return a
     * quiet NaN, even if another operand to that operation is a quiet NaN,
     * unless noted otherwise.</p> <p>Passing a quiet NaN to any arithmetic
     * operation shown here will return a quiet NaN, unless noted otherwise.
     * Invalid operations will also return a quiet NaN, as stated in the
     * individual methods.</p> <p>Unless noted otherwise, passing a null
     * arbitrary-precision decimal argument to any method here will throw an
     * exception.</p> <p>When an arithmetic operation signals the flag
     * FlagInvalid, FlagOverflow, or FlagDivideByZero, it will not throw an
     * exception too, unless the flag's trap is enabled in the arithmetic
     * context (see EContext's Traps property).</p> <p>If an operation
     * requires creating an intermediate value that might be too big to fit
     * in memory (or might require more than 2 gigabytes of memory to store
     * -- due to the current use of a 32-bit integer internally as a
     * length), the operation may signal an invalid-operation flag and
     * return not-a-number (NaN). In certain rare cases, the compareTo
     * method may throw OutOfMemoryError (called OutOfMemoryError in
     * Java) in the same circumstances.</p> <p><b>Serialization</b> </p>
     * <p>An arbitrary-precision decimal value can be serialized (converted
     * to a stable format) in one of the following ways:</p> <ul><li>By
     * calling the toString() method, which will always return distinct
     * strings for distinct arbitrary-precision decimal values.</li> <li>By
     * calling the UnsignedMantissa, Exponent, and IsNegative properties,
     * and calling the IsInfinity, IsQuietNaN, and IsSignalingNaN methods.
     * The return values combined will uniquely identify a particular
     * arbitrary-precision decimal value.</li> </ul> <p><b>Thread safety</b>
     * </p> <p>Instances of this class are immutable, so they are inherently
     * safe for use by multiple threads. Multiple instances of this object
     * with the same properties are interchangeable, so they should not be
     * compared using the "==" operator (which might only check if each side
     * of the operator is the same instance).</p> <p><b>Comparison
     * considerations</b> </p> <p>This class's natural ordering (under the
     * compareTo method) is not consistent with the Equals method. This
     * means that two values that compare as equal under the compareTo
     * method might not be equal under the Equals method. The compareTo
     * method compares the mathematical values of the two instances passed
     * to it (and considers two different NaN values as equal), while two
     * instances with the same mathematical value, but different exponents,
     * will be considered unequal under the Equals method.</p> <p><b>Forms
     * of numbers</b> </p> <p>There are several other types of numbers that
     * are mentioned in this class and elsewhere in this documentation. For
     * reference, they are specified here.</p> <p><b>Unsigned integer</b> :
     * An integer that's always 0 or greater, with the following maximum
     * values:</p> <ul><li>8-bit unsigned integer, or <i>byte</i> :
     * 255.</li> <li>16-bit unsigned integer: 65535.</li> <li>32-bit
     * unsigned integer: (2 <sup>32</sup> -1).</li> <li>64-bit unsigned
     * integer: (2 <sup>64</sup> -1).</li> </ul> <p><b>Signed integer</b> :
     * An integer in <i>two's-complement form</i> , with the following
     * ranges:</p> <ul><li>8-bit signed integer: -128 to 127.</li>
     * <li>16-bit signed integer: -32768 to 32767.</li> <li>32-bit signed
     * integer: -2 <sup>31</sup> to (2 <sup>31</sup> - 1).</li> <li>64-bit
     * signed integer: -2 <sup>63</sup> to (2 <sup>63</sup> - 1).</li> </ul>
     * <p><b>Two's complement form</b> : In <i>two' s-complement form</i> ,
     * nonnegative numbers have the highest (most significant) bit set to
     * zero, and negative numbers have that bit (and all bits beyond) set to
     * one, and a negative number is stored in such form by decreasing its
     * absolute value by 1 and swapping the bits of the resulting
     * number.</p> <p><b>64-bit floating-point number</b> : A 64-bit binary
     * floating-point number, in the form <i>significand</i> * 2
     * <sup><i>exponent</i> </sup> . The significand is 53 bits long
     * (Precision) and the exponent ranges from -1074 (EMin) to 971 (EMax).
     * The number is stored in the following format (commonly called the
     * IEEE 754 format):</p> <pre>|C|BBB...BBB|AAAAAA...AAAAAA| </pre>
     * <ul><li>A. Low 52 bits (Precision minus 1 bits): Lowest bits of the
     * significand.</li> <li>B. Next 11 bits: Exponent area: <ul><li>If all
     * bits are ones, this value is infinity if all bits in area A are
     * zeros, or not-a-number (NaN) otherwise.</li> <li>If all bits are
     * zeros, this is a subnormal number. The exponent is EMin and the
     * highest bit of the significand is zero.</li> <li>If any other number,
     * the exponent is this value reduced by 1, then raised by EMin, and the
     * highest bit of the significand is one.</li> </ul> </li> <li>C.
     * Highest bit: If one, this is a negative number.</li> </ul> <p>The
     * elements described above are in the same order as the order of each
     * bit of each element, that is, either most significant first or least
     * significant first.</p> <p><b>32-bit binary floating-point number</b>
     * : A 32-bit binary number which is stored similarly to a <i>64-bit
     * floating-point number</i> , except that:</p> <ul><li>Precision is 24
     * bits.</li> <li>EMin is -149.</li> <li>EMax is 104.</li> <li>A. The
     * low 23 bits (Precision minus 1 bits) are the lowest bits of the
     * significand.</li> <li>B. The next 8 bits are the exponent area.</li>
     * <li>C. If the highest bit is one, this is a negative number.</li>
     * </ul> <p><b>.NET Framework decimal</b> : A 128-bit decimal
     * floating-point number, in the form <i>significand</i> * 10 <sup>-
     * <i>scale</i> </sup> , where the scale ranges from 0 to 28. The number
     * is stored in the following format:</p> <ul><li>Low 96 bits are the
     * significand, as a 96-bit unsigned integer (all 96-bit values are
     * allowed, up to (2 <sup>96</sup> -1)).</li> <li>Next 16 bits are
     * unused.</li> <li>Next 8 bits are the scale, stored as an 8-bit
     * unsigned integer.</li> <li>Next 7 bits are unused.</li> <li>If the
     * highest bit is one, it's a negative number.</li> </ul> <p>The
     * elements described above are in the same order as the order of each
     * bit of each element, that is, either most significant first or least
     * significant first.</p>
     */
  public final class EDecimal implements Comparable<EDecimal> {
    //----------------------------------------------------------------

    /**
     * A not-a-number value.
     */

    public static final EDecimal NaN = CreateWithFlags(
        EInteger.FromInt32(0),
        EInteger.FromInt32(0),
        BigNumberFlags.FlagQuietNaN);

    /**
     * Negative infinity, less than any other number.
     */

    public static final EDecimal NegativeInfinity =
      CreateWithFlags(
        EInteger.FromInt32(0),
        EInteger.FromInt32(0),
        BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);

    /**
     * Represents the number negative zero.
     */

    public static final EDecimal NegativeZero =
      CreateWithFlags(
        EInteger.FromInt32(0),
        EInteger.FromInt32(0),
        BigNumberFlags.FlagNegative);

    /**
     * Represents the number 1.
     */

    public static final EDecimal One =
      EDecimal.Create(EInteger.FromInt32(1), EInteger.FromInt32(0));

    /**
     * Positive infinity, greater than any other number.
     */

    public static final EDecimal PositiveInfinity =
      CreateWithFlags(
        EInteger.FromInt32(0),
        EInteger.FromInt32(0),
        BigNumberFlags.FlagInfinity);

    /**
     * A not-a-number value that signals an invalid operation flag when it's passed
     * as an argument to any arithmetic operation in arbitrary-precision
     * decimal.
     */

public static final EDecimal SignalingNaN =
      CreateWithFlags(
        EInteger.FromInt32(0),
        EInteger.FromInt32(0),
        BigNumberFlags.FlagSignalingNaN);

    /**
     * Represents the number 10.
     */

    public static final EDecimal Ten =
      EDecimal.Create(EInteger.FromInt32(10), EInteger.FromInt32(0));

    /**
     * Represents the number 0.
     */

    public static final EDecimal Zero =
      EDecimal.Create(EInteger.FromInt32(0), EInteger.FromInt32(0));

    private static final int MaxSafeInt = 214748363;

    private static final IRadixMath<EDecimal> ExtendedMathValue = new
      RadixMath<EDecimal>(new DecimalMathHelper());

private static final FastIntegerFixed FastIntZero = new
      FastIntegerFixed(0);
    //----------------------------------------------------------------
    private static final IRadixMath<EDecimal> MathValue = new
      TrappableRadixMath<EDecimal>(
        new ExtendedOrSimpleRadixMath<EDecimal>(new
                    DecimalMathHelper()));

    private static final int[] ValueTenPowers = {
      1, 10, 100, 1000, 10000, 100000,
      1000000, 10000000, 100000000,
      1000000000
    };

    private final FastIntegerFixed exponent;
    private final int flags;
    private final FastIntegerFixed unsignedMantissa;

    private int sign;

    private EDecimal(
      FastIntegerFixed unsignedMantissa,
      FastIntegerFixed exponent,
      int flags) {
      this.unsignedMantissa = unsignedMantissa;
      this.exponent = exponent;
      this.flags = flags;
      this.sign = (((this.flags & BigNumberFlags.FlagSpecial) == 0) &&
                this.unsignedMantissa.isValueZero()) ? 0 : (((this.flags &
                    BigNumberFlags.FlagNegative) != 0) ? -1 : 1);
    }

    private EDecimal(
      FastIntegerFixed unsignedMantissa,
      FastIntegerFixed exponent,
      int flags,
      int sign) {
      this.unsignedMantissa = unsignedMantissa;
      this.exponent = exponent;
      this.flags = flags;
      this.sign = sign;
    }

    /**
     *
     */
    public final EInteger getExponent() {
        return this.exponent.ToEInteger();
      }

    /**
     *
     */
    public final boolean isFinite() {
        return (this.flags & (BigNumberFlags.FlagInfinity |
                    BigNumberFlags.FlagNaN)) == 0;
      }

    /**
     *
     */
    public final boolean isNegative() {
        return (this.flags & BigNumberFlags.FlagNegative) != 0;
      }

    /**
     *
     */
    public final boolean isZero() {
        return ((this.flags & BigNumberFlags.FlagSpecial) == 0) &&
          this.sign == 0;
      }

    /**
     *
     */
    public final EInteger getMantissa() {
        return this.isNegative() ? this.unsignedMantissa.ToEInteger().Negate() :
                this.unsignedMantissa.ToEInteger();
      }

    /**
     *
     */
    public final int signum() {
        return this.sign;
      }

    /**
     *
     */
    public final EInteger getUnsignedMantissa() {
        return this.unsignedMantissa.ToEInteger();
      }

    /**
     *
     * @param mantissaSmall Not documented yet.
     * @param exponentSmall Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal Create(int mantissaSmall, int exponentSmall) {
      if (mantissaSmall == Integer.MIN_VALUE) {
        return Create(EInteger.FromInt32(mantissaSmall), EInteger.FromInt32(exponentSmall));
      } else if (mantissaSmall < 0) {
        return new EDecimal(
  new FastIntegerFixed(mantissaSmall).Negate(),
  new FastIntegerFixed(exponentSmall),
  BigNumberFlags.FlagNegative,
  -1);
      } else if (mantissaSmall == 0) {
   return new EDecimal(
  FastIntZero,
  new FastIntegerFixed(exponentSmall),
  0,
  0);
      } else {
        return new EDecimal(
  new FastIntegerFixed(mantissaSmall),
  new FastIntegerFixed(exponentSmall),
  0,
  1);
      }
    }

    /**
     *
     * @param mantissa Not documented yet.
     * @param exponent Not documented yet.
     * @return An EDecimal object.
     * @throws NullPointerException The parameter is null.
     */
    public static EDecimal Create(
      EInteger mantissa,
      EInteger exponent) {
      if (mantissa == null) {
        throw new NullPointerException("mantissa");
      }
      if (exponent == null) {
        throw new NullPointerException("exponent");
      }
      FastIntegerFixed fi = FastIntegerFixed.FromBig(mantissa);
      int sign = fi.signum();
      return new EDecimal(
        sign < 0 ? fi.Negate() : fi,
        FastIntegerFixed.FromBig(exponent),
        (sign < 0) ? BigNumberFlags.FlagNegative : 0,
        sign);
    }

    /**
     *
     * @param diag Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal CreateNaN(EInteger diag) {
      return CreateNaN(diag, false, false, null);
    }

    /**
     *
     * @param diag Not documented yet.
     * @param signaling Not documented yet.
     * @param negative Not documented yet. (3).
     * @param ctx Not documented yet. (4).
     * @return An EDecimal object.
     * @throws NullPointerException The parameter is null.
     */
    public static EDecimal CreateNaN(
      EInteger diag,
      boolean signaling,
      boolean negative,
      EContext ctx) {
      if (diag == null) {
        throw new NullPointerException("diag");
      }
      if (diag.signum() < 0) {
        throw new
       IllegalArgumentException("Diagnostic information must be 0 or greater, was: " +
                    diag);
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
        FastIntZero,
        flags,
        negative ? -1 : 1).RoundToPrecision(ctx);
        int newFlags = ef.flags;
        newFlags &= ~BigNumberFlags.FlagQuietNaN;
        newFlags |= signaling ? BigNumberFlags.FlagSignalingNaN :
          BigNumberFlags.FlagQuietNaN;
        return new EDecimal(
  ef.unsignedMantissa,
  ef.exponent,
  newFlags,
  negative ? -1 : 1);
      }
      flags |= signaling ? BigNumberFlags.FlagSignalingNaN :
        BigNumberFlags.FlagQuietNaN;
      return new EDecimal(
        FastIntegerFixed.FromBig(diag),
        FastIntZero,
        flags,
        negative ? -1 : 1);
    }

    /**
     *
     * @param dbl Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal FromDouble(double dbl) {
      int[] value = Extras.DoubleToIntegers(dbl);
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
        return lvalue == 0 ? (quiet ? NaN : SignalingNaN) :
          new EDecimal(
            FastIntegerFixed.FromLong(lvalue),
            FastIntZero,
            flags,
            neg ? -1 : 1);
      }
      value[1] &= 0xfffff;

      // Mask out the exponent and sign
      if (floatExponent == 0) {
        ++floatExponent;
      } else {
        value[1] |= 0x100000;
      }
      if ((value[1] | value[0]) != 0) {
        floatExponent += NumberUtility.ShiftAwayTrailingZerosTwoElements(value);
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
        EInteger exp = NumberUtility.FindPowerOfFive(-floatExponent);
        bigmantissa = bigmantissa.Multiply(exp);
        if (neg) {
          bigmantissa=(bigmantissa).Negate();
        }
        return EDecimal.Create(bigmantissa, EInteger.FromInt32(floatExponent));
      }
    }

    /**
     *
     * @param bigint Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal FromEInteger(EInteger bigint) {
      return EDecimal.Create(bigint, EInteger.FromInt32(0));
    }

    /**
     *
     * @param ef Not documented yet.
     * @return An EDecimal object.
     * @deprecated Renamed to FromEFloat.
 */
@Deprecated
    public static EDecimal FromExtendedFloat(EFloat ef) {
      return FromEFloat(ef);
    }

    /**
     *
     * @param bigfloat Not documented yet.
     * @return An EDecimal object.
     * @throws NullPointerException The parameter is null.
     */
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
      EInteger bigintMant = bigfloat.getMantissa();
      if (bigintMant.isZero()) {
        return bigfloat.isNegative() ? EDecimal.NegativeZero :
          EDecimal.Zero;
      }
      if (bigintExp.isZero()) {
        // Integer
        return EDecimal.FromEInteger(bigintMant);
      }
      if (bigintExp.signum() > 0) {
        // Scaled integer
        FastInteger intcurexp = FastInteger.FromBig(bigintExp);
        EInteger bigmantissa = bigintMant;
        boolean neg = bigmantissa.signum() < 0;
        if (neg) {
          bigmantissa=(bigmantissa).Negate();
        }
        while (intcurexp.signum() > 0) {
          int shift = 1000000;
          if (intcurexp.CompareToInt(1000000) < 0) {
            shift = intcurexp.AsInt32();
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
        EInteger bigmantissa = bigintMant;
        EInteger negbigintExp=(bigintExp).Negate();
        negbigintExp = NumberUtility.FindPowerOfFiveFromBig(negbigintExp);
        bigmantissa = bigmantissa.Multiply(negbigintExp);
        return EDecimal.Create(bigmantissa, bigintExp);
      }
    }

    /**
     *
     * @param valueSmaller Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal FromInt32(int valueSmaller) {
      if (valueSmaller == 0) {
        return EDecimal.Zero;
      }
      if (valueSmaller == Integer.MIN_VALUE) {
        return Create(EInteger.FromInt32(valueSmaller), EInteger.FromInt32(0));
      }
      if (valueSmaller < 0) {
        return new EDecimal(
  new FastIntegerFixed(valueSmaller).Negate(),
  FastIntZero,
  BigNumberFlags.FlagNegative,
  -1);
      } else {
        return new
            EDecimal(new FastIntegerFixed(valueSmaller), FastIntZero, 0, 1);
      }
    }

    /**
     *
     * @param valueSmall Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal FromInt64(long valueSmall) {
      if (valueSmall == 0) {
        return EDecimal.Zero;
      }
      if (valueSmall > Integer.MIN_VALUE && valueSmall <= Integer.MAX_VALUE) {
        if (valueSmall < 0) {
          return new EDecimal(
  new FastIntegerFixed((int)valueSmall).Negate(),
  FastIntZero,
  BigNumberFlags.FlagNegative,
  -1);
        } else {
          return new EDecimal(
  new FastIntegerFixed((int)valueSmall),
  FastIntZero,
  0,
  1);
        }
      }
      EInteger bigint = EInteger.FromInt64(valueSmall);
      return EDecimal.Create(bigint, EInteger.FromInt32(0));
    }

    /**
     *
     * @param flt Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal FromSingle(float flt) {
      int value = Float.floatToRawIntBits(flt);
      boolean neg = (value >> 31) != 0;
      int floatExponent = (int)((value >> 23) & 0xff);
      int valueFpMantissa = value & 0x7fffff;
      if (floatExponent == 255) {
        if (valueFpMantissa == 0) {
          return neg ? NegativeInfinity : PositiveInfinity;
        }
        // Treat high bit of mantissa as quiet/signaling bit
        boolean quiet = (valueFpMantissa & 0x400000) != 0;
        valueFpMantissa &= 0x3fffff;
        value = (neg ? BigNumberFlags.FlagNegative : 0) |
       (quiet ? BigNumberFlags.FlagQuietNaN : BigNumberFlags.FlagSignalingNaN);
        return valueFpMantissa == 0 ? (quiet ? NaN : SignalingNaN) :
          new EDecimal(
            new FastIntegerFixed(valueFpMantissa),
            FastIntZero,
            value,
            neg ? -1 : 1);
      }
      if (floatExponent == 0) {
        ++floatExponent;
      } else {
        valueFpMantissa |= 1 << 23;
      }
      if (valueFpMantissa == 0) {
        return neg ? EDecimal.NegativeZero : EDecimal.Zero;
      }
      floatExponent -= 150;
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

    /**
     *
     * @param str Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal FromString(String str) {
      return FromString(str, 0, str == null ? 0 : str.length(), null);
    }

    /**
     *
     * @param str Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal FromString(String str, EContext ctx) {
      return FromString(str, 0, str == null ? 0 : str.length(), ctx);
    }

    /**
     *
     * @param str Not documented yet.
     * @param offset A zero-based index showing where the desired portion of {@code
     * str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @return An EDecimal object.
     * @throws IllegalArgumentException Either "offset" or "length" is less than 0 or
     * greater than "str"'s length, or "str"'s length minus "offset" is less
     * than "length".
     * @throws NullPointerException The parameter "str" is null.
     */
    public static EDecimal FromString(
      String str,
      int offset,
      int length) {
      return FromString(str, offset, length, null);
    }

    /**
     *
     * @param str Not documented yet.
     * @param offset A zero-based index showing where the desired portion of {@code
     * str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     * @throws NullPointerException The parameter is null.
     * @throws IllegalArgumentException Either "offset" or "length" is less than 0 or
     * greater than "str"'s length, or "str"'s length minus "offset" is less
     * than "length".
     */
    public static EDecimal FromString(
      String str,
      int offset,
      int length,
      EContext ctx) {
      int tmpoffset = offset;
      if (str == null) {
        throw new NullPointerException("str");
      }
      if (tmpoffset < 0) {
        throw new NumberFormatException("offset (" + tmpoffset + ") is less than " +
                    "0");
      }
      if (tmpoffset > str.length()) {
        throw new NumberFormatException("offset (" + tmpoffset + ") is more than " +
                    str.length());
      }
      if (length < 0) {
        throw new NumberFormatException("length (" + length + ") is less than " +
                    "0");
      }
      if (length > str.length()) {
        throw new NumberFormatException("length (" + length + ") is more than " +
                    str.length());
      }
      if (str.length() - tmpoffset < length) {
        throw new NumberFormatException("str's length minus " + tmpoffset + " (" +
                    (str.length() - tmpoffset) + ") is less than " + length);
      }
      if (length == 0) {
        throw new NumberFormatException();
      }
      boolean negative = false;
      int endStr = tmpoffset + length;
      if (str.charAt(0) == '+' || str.charAt(0) == '-') {
        negative = str.charAt(0) == '-';
        ++tmpoffset;
      }
      int mantInt = 0;
      FastInteger mant = null;
      int mantBuffer = 0;
      int mantBufferMult = 1;
      int expBuffer = 0;
      int expBufferMult = 1;
      boolean haveDecimalPoint = false;
      boolean haveDigits = false;
      boolean haveExponent = false;
      int newScaleInt = 0;
      FastInteger newScale = null;
      int i = tmpoffset;
      if (i + 8 == endStr) {
        if ((str.charAt(i) == 'I' || str.charAt(i) == 'i') &&
            (str.charAt(i + 1) == 'N' || str.charAt(i + 1) == 'n') &&
            (str.charAt(i + 2) == 'F' || str.charAt(i + 2) == 'f') &&
            (str.charAt(i + 3) == 'I' || str.charAt(i + 3) == 'i') && (str.charAt(i + 4) == 'N' ||
                    str.charAt(i + 4) == 'n') && (str.charAt(i + 5) ==
                    'I' || str.charAt(i + 5) == 'i') &&
            (str.charAt(i + 6) == 'T' || str.charAt(i + 6) == 't') && (str.charAt(i + 7) == 'Y' ||
                    str.charAt(i + 7) == 'y')) {
          if (ctx != null && ctx.isSimplified() && i < endStr) {
            throw new NumberFormatException("Infinity not allowed");
          }
          return negative ? NegativeInfinity : PositiveInfinity;
        }
      }
      if (i + 3 == endStr) {
        if ((str.charAt(i) == 'I' || str.charAt(i) == 'i') &&
            (str.charAt(i + 1) == 'N' || str.charAt(i + 1) == 'n') && (str.charAt(i + 2) == 'F' ||
                    str.charAt(i + 2) == 'f')) {
          if (ctx != null && ctx.isSimplified() && i < endStr) {
            throw new NumberFormatException("Infinity not allowed");
          }
          return negative ? NegativeInfinity : PositiveInfinity;
        }
      }
      if (i + 3 <= endStr) {
        // Quiet NaN
        if ((str.charAt(i) == 'N' || str.charAt(i) == 'n') && (str.charAt(i + 1) == 'A' || str.charAt(i +
                1) == 'a') && (str.charAt(i + 2) == 'N' || str.charAt(i + 2) == 'n')) {
          if (ctx != null && ctx.isSimplified() && i < endStr) {
            throw new NumberFormatException("NaN not allowed");
          }
          int flags2 = (negative ? BigNumberFlags.FlagNegative : 0) |
            BigNumberFlags.FlagQuietNaN;
          if (i + 3 == endStr) {
            return (!negative) ? NaN : new EDecimal(
              FastIntZero,
              FastIntZero,
              flags2,
              -1);
          }
          i += 3;
          FastInteger digitCount = new FastInteger(0);
          FastInteger maxDigits = null;
          haveDigits = false;
          if (ctx != null && ctx.getHasMaxPrecision()) {
            maxDigits = FastInteger.FromBig(ctx.getPrecision());
            if (ctx.getClampNormalExponents()) {
              maxDigits.Decrement();
            }
          }
          for (; i < endStr; ++i) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
              int thisdigit = (int)(str.charAt(i) - '0');
              haveDigits = haveDigits || thisdigit != 0;
              if (mantInt > MaxSafeInt) {
                if (mant == null) {
                  mant = new FastInteger(mantInt);
                  mantBuffer = thisdigit;
                  mantBufferMult = 10;
                } else {
                  if (mantBufferMult >= 1000000000) {
                    mant.Multiply(mantBufferMult).AddInt(mantBuffer);
                    mantBuffer = thisdigit;
                    mantBufferMult = 10;
                  } else {
                    // multiply by 10
   mantBufferMult = (mantBufferMult << 3) + (mantBufferMult << 1);
                    mantBuffer = (mantBuffer << 3) + (mantBuffer << 1);
                    mantBuffer += thisdigit;
                  }
                }
              } else {
                // multiply by 10
   mantInt = (mantInt << 3) + (mantInt << 1);
                mantInt += thisdigit;
              }
              if (haveDigits && maxDigits != null) {
                digitCount.Increment();
                if (digitCount.compareTo(maxDigits) > 0) {
                  // NaN contains too many digits
                  throw new NumberFormatException();
                }
              }
            } else {
              throw new NumberFormatException();
            }
          }
          if (mant != null && (mantBufferMult != 1 || mantBuffer != 0)) {
            mant.Multiply(mantBufferMult).AddInt(mantBuffer);
          }
          EInteger bigmant = (mant == null) ? (EInteger.FromInt32(mantInt)) :
            mant.AsEInteger();
          flags2 = (negative ? BigNumberFlags.FlagNegative : 0) |
            BigNumberFlags.FlagQuietNaN;
          return CreateWithFlags(
            FastIntegerFixed.FromBig(bigmant),
            FastIntZero,
            flags2);
        }
      }
      if (i + 4 <= endStr) {
        // Signaling NaN
        if ((str.charAt(i) == 'S' || str.charAt(i) == 's') && (str.charAt(i + 1) == 'N' || str.charAt(i +
                    1) == 'n') && (str.charAt(i + 2) == 'A' || str.charAt(i + 2) == 'a') &&
                (str.charAt(i + 3) == 'N' || str.charAt(i + 3) == 'n')) {
          if (ctx != null && ctx.isSimplified() && i < endStr) {
            throw new NumberFormatException("NaN not allowed");
          }
          if (i + 4 == endStr) {
            int flags2 = (negative ? BigNumberFlags.FlagNegative : 0) |
              BigNumberFlags.FlagSignalingNaN;
            return (!negative) ? SignalingNaN :
              new EDecimal(
                FastIntZero,
                FastIntZero,
                flags2,
                -1);
          }
          i += 4;
          FastInteger digitCount = new FastInteger(0);
          FastInteger maxDigits = null;
          haveDigits = false;
          if (ctx != null && ctx.getHasMaxPrecision()) {
            maxDigits = FastInteger.FromBig(ctx.getPrecision());
            if (ctx.getClampNormalExponents()) {
              maxDigits.Decrement();
            }
          }
          for (; i < endStr; ++i) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
              int thisdigit = (int)(str.charAt(i) - '0');
              haveDigits = haveDigits || thisdigit != 0;
              if (mantInt > MaxSafeInt) {
                if (mant == null) {
                  mant = new FastInteger(mantInt);
                  mantBuffer = thisdigit;
                  mantBufferMult = 10;
                } else {
                  if (mantBufferMult >= 1000000000) {
                    mant.Multiply(mantBufferMult).AddInt(mantBuffer);
                    mantBuffer = thisdigit;
                    mantBufferMult = 10;
                  } else {
                    // multiply by 10
   mantBufferMult = (mantBufferMult << 3) + (mantBufferMult << 1);
                    mantBuffer = (mantBuffer << 3) + (mantBuffer << 1);
                    mantBuffer += thisdigit;
                  }
                }
              } else {
                // multiply by 10
   mantInt = (mantInt << 3) + (mantInt << 1);
                mantInt += thisdigit;
              }
              if (haveDigits && maxDigits != null) {
                digitCount.Increment();
                if (digitCount.compareTo(maxDigits) > 0) {
                  // NaN contains too many digits
                  throw new NumberFormatException();
                }
              }
            } else {
              throw new NumberFormatException();
            }
          }
          if (mant != null && (mantBufferMult != 1 || mantBuffer != 0)) {
            mant.Multiply(mantBufferMult).AddInt(mantBuffer);
          }
          int flags3 = (negative ? BigNumberFlags.FlagNegative : 0) |
            BigNumberFlags.FlagSignalingNaN;
          EInteger bigmant = (mant == null) ? (EInteger.FromInt32(mantInt)) :
            mant.AsEInteger();
          return CreateWithFlags(
            bigmant,
            EInteger.FromInt32(0),
            flags3);
        }
      }
      // Ordinary number
      for (; i < endStr; ++i) {
        char ch = str.charAt(i);
        if (ch >= '0' && ch <= '9') {
          int thisdigit = (int)(ch - '0');
          if (mantInt > MaxSafeInt) {
            if (mant == null) {
              mant = new FastInteger(mantInt);
              mantBuffer = thisdigit;
              mantBufferMult = 10;
            } else {
              if (mantBufferMult >= 1000000000) {
                mant.Multiply(mantBufferMult).AddInt(mantBuffer);
                mantBuffer = thisdigit;
                mantBufferMult = 10;
              } else {
                // multiply mantBufferMult and mantBuffer each by 10
                mantBufferMult = (mantBufferMult << 3) + (mantBufferMult << 1);
                mantBuffer = (mantBuffer << 3) + (mantBuffer << 1);
                mantBuffer += thisdigit;
              }
            }
          } else {
            // multiply by 10
   mantInt = (mantInt << 3) + (mantInt << 1);
            mantInt += thisdigit;
          }
          haveDigits = true;
          if (haveDecimalPoint) {
            if (newScaleInt == Integer.MIN_VALUE) {
newScale = (newScale == null) ? ((new FastInteger(newScaleInt))) : newScale;
              newScale.Decrement();
            } else {
              --newScaleInt;
            }
          }
        } else if (ch == '.') {
          if (haveDecimalPoint) {
            throw new NumberFormatException();
          }
          haveDecimalPoint = true;
        } else if (ch == 'E' || ch == 'e') {
          haveExponent = true;
          ++i;
          break;
        } else {
          throw new NumberFormatException();
        }
      }
      if (!haveDigits) {
        throw new NumberFormatException();
      }
      if (mant != null && (mantBufferMult != 1 || mantBuffer != 0)) {
        mant.Multiply(mantBufferMult).AddInt(mantBuffer);
      }
      if (haveExponent) {
        FastInteger exp = null;
        int expInt = 0;
        tmpoffset = 1;
        haveDigits = false;
        if (i == endStr) {
          throw new NumberFormatException();
        }
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
          if (str.charAt(i) == '-') {
            tmpoffset = -1;
          }
          ++i;
        }
        for (; i < endStr; ++i) {
          char ch = str.charAt(i);
          if (ch >= '0' && ch <= '9') {
            haveDigits = true;
            int thisdigit = (int)(ch - '0');
            if (expInt > MaxSafeInt) {
              if (exp == null) {
                exp = new FastInteger(expInt);
                expBuffer = thisdigit;
                expBufferMult = 10;
              } else {
                if (expBufferMult >= 1000000000) {
                  exp.Multiply(expBufferMult).AddInt(expBuffer);
                  expBuffer = thisdigit;
                  expBufferMult = 10;
                } else {
                  // multiply expBufferMult and expBuffer each by 10
                  expBufferMult = (expBufferMult << 3) + (expBufferMult << 1);
                  expBuffer = (expBuffer << 3) + (expBuffer << 1);
                  expBuffer += thisdigit;
                }
              }
            } else {
              expInt *= 10;
              expInt += thisdigit;
            }
          } else {
            throw new NumberFormatException();
          }
        }
        if (!haveDigits) {
          throw new NumberFormatException();
        }
        if (exp != null && (expBufferMult != 1 || expBuffer != 0)) {
          exp.Multiply(expBufferMult).AddInt(expBuffer);
        }
   if (tmpoffset >= 0 && newScaleInt == 0 && newScale == null && exp == null) {
          newScaleInt = expInt;
        } else if (exp == null) {
newScale = (newScale == null) ? ((new FastInteger(newScaleInt))) : newScale;
          if (tmpoffset < 0) {
            newScale.SubtractInt(expInt);
          } else if (expInt != 0) {
            newScale.AddInt(expInt);
          }
        } else {
newScale = (newScale == null) ? ((new FastInteger(newScaleInt))) : newScale;
          if (tmpoffset < 0) {
            newScale.Subtract(exp);
          } else {
            newScale.Add(exp);
          }
        }
      }
      if (i != endStr) {
        throw new NumberFormatException();
      }
      FastIntegerFixed fastIntScale;
      FastIntegerFixed fastIntMant;
      fastIntScale = (newScale == null) ? (new FastIntegerFixed(newScaleInt)) :
        FastIntegerFixed.FromFastInteger(newScale);
      int sign = negative ? -1 : 1;
      if (mant == null) {
        fastIntMant = new FastIntegerFixed(mantInt);
        if (mantInt == 0) {
 sign = 0;
}
      } else if (mant.CanFitInInt32()) {
        mantInt = mant.AsInt32();
        fastIntMant = new FastIntegerFixed(mantInt);
        if (mantInt == 0) {
 sign = 0;
}
      } else {
        fastIntMant = FastIntegerFixed.FromFastInteger(mant);
      }
      EDecimal ret = new EDecimal(
  fastIntMant,
  fastIntScale,
  negative ? BigNumberFlags.FlagNegative : 0,
  sign);
      if (ctx != null) {
        ret = GetMathValue(ctx).RoundAfterConversion(ret, ctx);
      }
      return ret;
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EDecimal object.
     */
    public static EDecimal Max(
      EDecimal first,
      EDecimal second,
      EContext ctx) {
      return GetMathValue(ctx).Max(first, second, ctx);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal Max(
      EDecimal first,
      EDecimal second) {
      return Max(first, second, null);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EDecimal object.
     */
    public static EDecimal MaxMagnitude(
      EDecimal first,
      EDecimal second,
      EContext ctx) {
      return GetMathValue(ctx).MaxMagnitude(first, second, ctx);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal MaxMagnitude(
      EDecimal first,
      EDecimal second) {
      return MaxMagnitude(first, second, null);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EDecimal object.
     */
    public static EDecimal Min(
      EDecimal first,
      EDecimal second,
      EContext ctx) {
      return GetMathValue(ctx).Min(first, second, ctx);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal Min(
      EDecimal first,
      EDecimal second) {
      return Min(first, second, null);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EDecimal object.
     */
    public static EDecimal MinMagnitude(
      EDecimal first,
      EDecimal second,
      EContext ctx) {
      return GetMathValue(ctx).MinMagnitude(first, second, ctx);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal MinMagnitude(
      EDecimal first,
      EDecimal second) {
      return MinMagnitude(first, second, null);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public static EDecimal PI(EContext ctx) {
      return GetMathValue(ctx).Pi(ctx);
    }

    /**
     *
     * @return An EDecimal object.
     */
    public EDecimal Abs() {
      if (this.isNegative()) {
        EDecimal er = new EDecimal(
  this.unsignedMantissa,
  this.exponent,
  this.flags & ~BigNumberFlags.FlagNegative,
  Math.abs(this.sign));
        return er;
      }
      return this;
    }

    /**
     *
     * @param other Not documented yet.
     * @return An EDecimal object.
     * @throws NullPointerException The parameter is null.
     */
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

    /**
     *
     * @param context Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Abs(EContext context) {
      return ((context == null || context == EContext.UnlimitedHalfEven) ?
        ExtendedMathValue : MathValue).Abs(this, context);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Add(EDecimal otherValue) {
      if (this.isFinite() && otherValue != null && otherValue.isFinite() &&
        ((this.flags | otherValue.flags) & BigNumberFlags.FlagNegative) == 0 &&
            this.exponent.compareTo(otherValue.exponent) == 0) {
        FastIntegerFixed result = FastIntegerFixed.Add(
  this.unsignedMantissa,
  otherValue.unsignedMantissa);
        int sign = result.isValueZero() ? 0 : 1;
        return new EDecimal(result, this.exponent, 0, sign);
      }
      return this.Add(otherValue, EContext.UnlimitedHalfEven);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Add(
      EDecimal otherValue,
      EContext ctx) {
      return GetMathValue(ctx).Add(this, otherValue, ctx);
    }

    /**
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
     */
    public int compareTo(EDecimal other) {
      return ExtendedMathValue.compareTo(this, other);
    }

    /**
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
     */
    public int CompareToBinary(EFloat other) {
      if (other == null) {
        return 1;
      }
      if (this.IsNaN()) {
        return other.IsNaN() ? 0 : 1;
      }
      int signA = this.signum();
      int signB = other.signum();
      if (signA != signB) {
        return (signA < signB) ? -1 : 1;
      }
      if (signB == 0 || signA == 0) {
        // Special case: Either operand is zero
        return 0;
      }
      if (this.IsInfinity()) {
        if (other.IsInfinity()) {
          // if we get here, this only means that
          // both are positive infinity or both
          // are negative infinity
          return 0;
        }
        return this.isNegative() ? -1 : 1;
      }
      if (other.IsInfinity()) {
        return other.isNegative() ? 1 : -1;
      }
      // At this point, both numbers are finite and
      // have the same sign

      if (other.getExponent().compareTo(EInteger.FromInt64(-1000)) < 0) {
        // For very low exponents, the conversion to decimal can take
        // very long, so try this approach
        if (other.Abs(null).compareTo(EFloat.One) < 0) {
          // Abs less than 1
          if (this.Abs(null).compareTo(EDecimal.One) >= 0) {
            // Abs 1 or more
            return (signA > 0) ? 1 : -1;
          }
        }
      }
      if (other.getExponent().compareTo(EInteger.FromInt64(1000)) > 0) {
        // Very high exponents
        EInteger bignum = EInteger.FromInt32(1).ShiftLeft(999);
        if (this.Abs(null).compareTo(EDecimal.FromEInteger(bignum)) <=
            0) {
          // this object's absolute value is less
          return (signA > 0) ? -1 : 1;
        }
        // NOTE: The following check assumes that both
        // operands are nonzero
        EInteger thisAdjExp = this.GetAdjustedExponent();
        EInteger otherAdjExp = GetAdjustedExponentBinary(other);
        if (thisAdjExp.signum() > 0 && thisAdjExp.compareTo(otherAdjExp) >= 0) {
          // This Object's adjusted exponent is greater and is positive;
          // so this object's absolute value is greater, since exponents
          // have a greater value in decimal than in binary
          return (signA > 0) ? 1 : -1;
        }
        if (thisAdjExp.signum() > 0 && thisAdjExp.compareTo(EInteger.FromInt64(1000)) >= 0 &&
                otherAdjExp.compareTo(EInteger.FromInt64(1000)) >= 0) {
          thisAdjExp = thisAdjExp.Add(EInteger.FromInt32(1));
          otherAdjExp = thisAdjExp.Add(EInteger.FromInt32(1));
          EInteger ratio = otherAdjExp.Divide(thisAdjExp);
          // Check the ratio of the binary exponent to the decimal exponent.
          // If the ratio is less than 3, the decimal's absolute value is
          // greater. If it's 4 or greater, the binary' s absolute value is
          // greater.
          // (If the two absolute values are equal, the ratio will approach
          // ln(10)/ln(2), or about 3.322, as the exponents get higher and
          // higher.) This check assumes that both exponents are 1000 or
          // greater,
          // when the ratio between exponents of equal values is close to
          // ln(10)/ln(2).
          if (ratio.compareTo(EInteger.FromInt64(3)) < 0) {
            // Decimal abs. value is greater
            return (signA > 0) ? 1 : -1;
          }
          if (ratio.compareTo(EInteger.FromInt64(4)) >= 0) {
            return (signA > 0) ? -1 : 1;
          }
        }
      }
      EDecimal otherDec = EDecimal.FromEFloat(other);
      return this.compareTo(otherDec);
    }

    /**
     *
     * @param other Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal CompareToSignal(
      EDecimal other,
      EContext ctx) {
      return GetMathValue(ctx).CompareToWithContext(this, other, true, ctx);
    }

    /**
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
     */
    public int CompareToTotalMagnitude(EDecimal other) {
      if (other == null) {
        return -1;
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
     *
     * @param other Not documented yet.
     * @param ctx Not documented yet.
     * @return A 32-bit signed integer.
     */
    public int CompareToTotal(EDecimal other, EContext ctx) {
      if (other == null) {
        return -1;
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
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
     */
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

    /**
     *
     * @param other Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal CompareToWithContext(
      EDecimal other,
      EContext ctx) {
      return GetMathValue(ctx).CompareToWithContext(this, other, false, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Divide(EDecimal divisor) {
      return this.Divide(
        divisor,
        EContext.ForRounding(ERounding.None));
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Divide(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).Divide(this, divisor, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @return An EDecimal[] object.
     * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EDecimal[] DivideAndRemainderNaturalScale(EDecimal
      divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal[] object.
     * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EDecimal[] DivideAndRemainderNaturalScale(
      EDecimal divisor,
      EContext ctx) {
      return this.DivRemNaturalScale(divisor, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @return An EDecimal[] object.
     */
    public EDecimal[] DivRemNaturalScale(EDecimal
      divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal[] object.
     */
    public EDecimal[] DivRemNaturalScale(
      EDecimal divisor,
      EContext ctx) {
      EDecimal[] result = new EDecimal[2];
      result[0] = this.DivideToIntegerNaturalScale(divisor, ctx);
      result[1] = this.Subtract(
        result[0].Multiply(divisor, null),
        null);
      return result;
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param desiredExponentSmall Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EDecimal object.
     */
    public EDecimal DivideToExponent(
      EDecimal divisor,
      long desiredExponentSmall,
      EContext ctx) {
      return this.DivideToExponent(
        divisor,
        EInteger.FromInt64(desiredExponentSmall),
        ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param desiredExponentInt Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EDecimal object.
     */
    public EDecimal DivideToExponent(
      EDecimal divisor,
      int desiredExponentInt,
      EContext ctx) {
      return this.DivideToExponent(
        divisor,
        EInteger.FromInt32(desiredExponentInt),
        ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param desiredExponentSmall Not documented yet.
     * @param rounding Not documented yet. (3).
     * @return An EDecimal object.
     */
    public EDecimal DivideToExponent(
      EDecimal divisor,
      long desiredExponentSmall,
      ERounding rounding) {
      return this.DivideToExponent(
        divisor,
        EInteger.FromInt64(desiredExponentSmall),
        EContext.ForRounding(rounding));
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param desiredExponentInt Not documented yet.
     * @param rounding Not documented yet. (3).
     * @return An EDecimal object.
     */
    public EDecimal DivideToExponent(
      EDecimal divisor,
      int desiredExponentInt,
      ERounding rounding) {
      return this.DivideToExponent(
        divisor,
        EInteger.FromInt32(desiredExponentInt),
        EContext.ForRounding(rounding));
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param exponent Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EDecimal object.
     */
    public EDecimal DivideToExponent(
      EDecimal divisor,
      EInteger exponent,
      EContext ctx) {
      return GetMathValue(ctx).DivideToExponent(this, divisor, exponent, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param exponent Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal DivideToExponent(
      EDecimal divisor,
      EInteger exponent) {
      return this.DivideToExponent(divisor, exponent, ERounding.HalfEven);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param desiredExponentSmall Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal DivideToExponent(
      EDecimal divisor,
      long desiredExponentSmall) {
      return this.DivideToExponent(
  divisor,
  desiredExponentSmall,
  ERounding.HalfEven);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param desiredExponentInt Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal DivideToExponent(
      EDecimal divisor,
      int desiredExponentInt) {
 return this.DivideToExponent(
  divisor,
  desiredExponentInt,
  ERounding.HalfEven);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param desiredExponent Not documented yet.
     * @param rounding Not documented yet. (3).
     * @return An EDecimal object.
     */
    public EDecimal DivideToExponent(
      EDecimal divisor,
      EInteger desiredExponent,
      ERounding rounding) {
      return this.DivideToExponent(
        divisor,
        desiredExponent,
        EContext.ForRounding(rounding));
    }

    /**
     *
     * @param divisor Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal DivideToIntegerNaturalScale(EDecimal
                    divisor) {
      return this.DivideToIntegerNaturalScale(
        divisor,
        EContext.ForRounding(ERounding.Down));
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal DivideToIntegerNaturalScale(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).DivideToIntegerNaturalScale(this, divisor, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal DivideToIntegerZeroScale(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).DivideToIntegerZeroScale(this, divisor, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param rounding Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal DivideToSameExponent(
      EDecimal divisor,
      ERounding rounding) {
      return this.DivideToExponent(
        divisor,
        this.exponent.ToEInteger(),
        EContext.ForRounding(rounding));
    }

    /**
     *
     * @param other Not documented yet.
     * @return A Boolean object.
     */
    public boolean equals(EDecimal other) {
      return this.EqualsInternal(other);
    }

    /**
     *
     * @param obj Not documented yet.
     * @return A Boolean object.
     */
    @Override public boolean equals(Object obj) {
      return this.EqualsInternal(((obj instanceof EDecimal) ? (EDecimal)obj : null));
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Exp(EContext ctx) {
      return GetMathValue(ctx).Exp(this, ctx);
    }

    /**
     *
     * @return A 32-bit signed integer.
     */
    @Override public int hashCode() {
      int valueHashCode = 964453631;
      {
        valueHashCode += 964453723 * this.exponent.hashCode();
        valueHashCode += 964453939 * this.unsignedMantissa.hashCode();
        valueHashCode += 964453967 * this.flags;
      }
      return valueHashCode;
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsInfinity() {
      return (this.flags & BigNumberFlags.FlagInfinity) != 0;
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsNaN() {
      return (this.flags & (BigNumberFlags.FlagQuietNaN |
                    BigNumberFlags.FlagSignalingNaN)) != 0;
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsNegativeInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
                BigNumberFlags.FlagNegative)) == (BigNumberFlags.FlagInfinity |
                    BigNumberFlags.FlagNegative);
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsPositiveInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
                  BigNumberFlags.FlagNegative)) == BigNumberFlags.FlagInfinity;
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsQuietNaN() {
      return (this.flags & BigNumberFlags.FlagQuietNaN) != 0;
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsSignalingNaN() {
      return (this.flags & BigNumberFlags.FlagSignalingNaN) != 0;
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Log(EContext ctx) {
      return GetMathValue(ctx).Ln(this, ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Log10(EContext ctx) {
      return GetMathValue(ctx).Log10(this, ctx);
    }

    /**
     *
     * @param places Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal MovePointLeft(int places) {
      return this.MovePointLeft(EInteger.FromInt32(places), null);
    }

    /**
     *
     * @param places Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal MovePointLeft(int places, EContext ctx) {
      return this.MovePointLeft(EInteger.FromInt32(places), ctx);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal MovePointLeft(EInteger bigPlaces) {
      return this.MovePointLeft(bigPlaces, null);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal MovePointLeft(
  EInteger bigPlaces,
  EContext ctx) {
      return (!this.isFinite()) ? this.RoundToPrecision(ctx) :
        this.MovePointRight((bigPlaces).Negate(), ctx);
    }

    /**
     *
     * @param places Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal MovePointRight(int places) {
      return this.MovePointRight(EInteger.FromInt32(places), null);
    }

    /**
     *
     * @param places Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal MovePointRight(int places, EContext ctx) {
      return this.MovePointRight(EInteger.FromInt32(places), ctx);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal MovePointRight(EInteger bigPlaces) {
      return this.MovePointRight(bigPlaces, null);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
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

    /**
     *
     * @param otherValue Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Multiply(EDecimal otherValue) {
      if (this.isFinite() && otherValue.isFinite()) {
        int newflags = otherValue.flags ^ this.flags;
        if (this.unsignedMantissa.CanFitInInt32() &&
          otherValue.unsignedMantissa.CanFitInInt32()) {
          int integerA = this.unsignedMantissa.AsInt32();
          int integerB = otherValue.unsignedMantissa.AsInt32();
          long longA = ((long)integerA) * ((long)integerB);
          int sign = (longA == 0) ? 0 : (newflags == 0 ? 1 : -1);
          FastIntegerFixed exp = FastIntegerFixed.Add(
  this.exponent,
  otherValue.exponent);
          if ((longA >> 31) == 0) {
            return new EDecimal(
  new FastIntegerFixed((int)longA),
  exp,
  newflags,
  sign);
          } else {
            return new EDecimal(
  FastIntegerFixed.FromBig(EInteger.FromInt64(longA)),
  exp,
  newflags,
  sign);
          }
        } else {
          EInteger eintA = this.unsignedMantissa.ToEInteger().Multiply(
           otherValue.unsignedMantissa.ToEInteger());
          int sign = eintA.isZero() ? 0 : (newflags == 0 ? 1 : -1);
          return new EDecimal(
  FastIntegerFixed.FromBig(eintA),
  FastIntegerFixed.Add(this.exponent, otherValue.exponent),
  newflags,
  sign);
        }
      }
      return this.Multiply(otherValue, EContext.UnlimitedHalfEven);
    }

    /**
     *
     * @param op Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Multiply(EDecimal op, EContext ctx) {
      return GetMathValue(ctx).Multiply(this, op, ctx);
    }

    /**
     *
     * @param multiplicand Not documented yet.
     * @param augend Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal MultiplyAndAdd(
      EDecimal multiplicand,
      EDecimal augend) {
      return this.MultiplyAndAdd(multiplicand, augend, null);
    }

    /**
     *
     * @param op Not documented yet.
     * @param augend Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EDecimal object.
     */
    public EDecimal MultiplyAndAdd(
      EDecimal op,
      EDecimal augend,
      EContext ctx) {
      return GetMathValue(ctx).MultiplyAndAdd(this, op, augend, ctx);
    }

    /**
     *
     * @param op Not documented yet.
     * @param subtrahend Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EDecimal object.
     * @throws NullPointerException The parameter is null.
     */
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

    /**
     *
     * @return An EDecimal object.
     */
    public EDecimal Negate() {
      return new EDecimal(
  this.unsignedMantissa,
  this.exponent,
  this.flags ^ BigNumberFlags.FlagNegative,
  -this.sign);
    }

    /**
     *
     * @param context Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Negate(EContext context) {
      return ((context == null || context == EContext.UnlimitedHalfEven) ?
        ExtendedMathValue : MathValue).Negate(this, context);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal NextMinus(EContext ctx) {
      return GetMathValue(ctx).NextMinus(this, ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal NextPlus(EContext ctx) {
      return GetMathValue(ctx)
        .NextPlus(this, ctx);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal NextToward(
      EDecimal otherValue,
      EContext ctx) {
      return GetMathValue(ctx)
        .NextToward(this, otherValue, ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Plus(EContext ctx) {
      return GetMathValue(ctx).Plus(this, ctx);
    }

    /**
     *
     * @param exponent Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Pow(EDecimal exponent, EContext ctx) {
      return GetMathValue(ctx).Power(this, exponent, ctx);
    }

    /**
     *
     * @param exponentSmall Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Pow(int exponentSmall, EContext ctx) {
      return this.Pow(EDecimal.FromInt64(exponentSmall), ctx);
    }

    /**
     *
     * @param exponentSmall Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Pow(int exponentSmall) {
      return this.Pow(EDecimal.FromInt64(exponentSmall), null);
    }

    /**
     *
     * @return An EInteger object.
     */
    public EInteger Precision() {
      if (!this.isFinite()) {
        return EInteger.FromInt32(0);
      }
      if (this.isZero()) {
        return EInteger.FromInt32(1);
      }
      int digcount = this.unsignedMantissa.ToEInteger().GetDigitCount();
      return EInteger.FromInt32(digcount);
    }

    /**
     *
     * @param desiredExponent Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Quantize(
      EInteger desiredExponent,
      EContext ctx) {
      return this.Quantize(
        EDecimal.Create(EInteger.FromInt32(1), desiredExponent),
        ctx);
    }

    /**
     *
     * @param desiredExponentInt Not documented yet.
     * @param rounding Not documented yet.
     * @return An EDecimal object.
     */
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

    /**
     *
     * @param desiredExponentInt Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Quantize(
      int desiredExponentInt,
      EContext ctx) {
      if (ctx == null ||
         (!ctx.getHasExponentRange() && !ctx.getHasFlags() && ctx.getTraps() == 0 &&
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

    /**
     *
     * @param otherValue Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Quantize(
      EDecimal otherValue,
      EContext ctx) {
      return GetMathValue(ctx).Quantize(this, otherValue, ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Reduce(EContext ctx) {
      return GetMathValue(ctx).Reduce(this, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Remainder(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).Remainder(this, divisor, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RemainderNaturalScale(EDecimal divisor) {
      return this.RemainderNaturalScale(divisor, null);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RemainderNaturalScale(
      EDecimal divisor,
      EContext ctx) {
      return this.Subtract(
        this.DivideToIntegerNaturalScale(divisor, ctx).Multiply(divisor, null),
        null);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RemainderNear(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx)
        .RemainderNear(this, divisor, ctx);
    }

    /**
     *
     * @param exponent Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RoundToExponent(
      EInteger exponent,
      EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentSimple(this, exponent, ctx);
    }

    /**
     *
     * @param exponent Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RoundToExponent(
      EInteger exponent) {
      return this.RoundToExponent(
  exponent,
  EContext.ForRounding(ERounding.HalfEven));
    }

    /**
     *
     * @param exponent Not documented yet.
     * @param rounding Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RoundToExponent(
      EInteger exponent,
      ERounding rounding) {
      return this.RoundToExponent(
  exponent,
  EContext.ForRounding(rounding));
    }

    /**
     *
     * @param exponentSmall Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RoundToExponent(
      int exponentSmall) {
      return this.RoundToExponent(exponentSmall, ERounding.HalfEven);
    }

    /**
     *
     * @param exponentSmall Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RoundToExponent(
      int exponentSmall,
      EContext ctx) {
      if (ctx == null ||
         (!ctx.getHasExponentRange() && !ctx.getHasFlags() && ctx.getTraps() == 0 &&
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

    /**
     *
     * @param exponentSmall Not documented yet.
     * @param rounding Not documented yet.
     * @return An EDecimal object.
     */
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

    /**
     *
     * @param exponent Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RoundToExponentExact(
      EInteger exponent,
      EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentExact(this, exponent, ctx);
    }

    /**
     *
     * @param exponentSmall Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RoundToExponentExact(
      int exponentSmall,
      EContext ctx) {
      return this.RoundToExponentExact(EInteger.FromInt32(exponentSmall), ctx);
    }

    /**
     *
     * @param exponentSmall Not documented yet.
     * @param rounding Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RoundToExponentExact(
      int exponentSmall,
      ERounding rounding) {
 return this.RoundToExponentExact(
  EInteger.FromInt32(exponentSmall),
  EContext.Unlimited.WithRounding(rounding));
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RoundToIntegerExact(EContext ctx) {
      return GetMathValue(ctx).RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RoundToIntegerNoRoundedFlag(EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     * @deprecated Renamed to RoundToIntegerExact.
 */
@Deprecated
    public EDecimal RoundToIntegralExact(EContext ctx) {
      return GetMathValue(ctx).RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     * @deprecated Renamed to RoundToIntegerNoRoundedFlag.
 */
@Deprecated
    public EDecimal RoundToIntegralNoRoundedFlag(EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal RoundToPrecision(EContext ctx) {
      return GetMathValue(ctx).RoundToPrecision(this, ctx);
    }

    /**
     *
     * @param places Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal ScaleByPowerOfTen(int places) {
      return this.ScaleByPowerOfTen(EInteger.FromInt32(places), null);
    }

    /**
     *
     * @param places Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal ScaleByPowerOfTen(int places, EContext ctx) {
      return this.ScaleByPowerOfTen(EInteger.FromInt32(places), ctx);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal ScaleByPowerOfTen(EInteger bigPlaces) {
      return this.ScaleByPowerOfTen(bigPlaces, null);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal ScaleByPowerOfTen(
  EInteger bigPlaces,
  EContext ctx) {
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

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Sqrt(EContext ctx) {
      return GetMathValue(ctx).SquareRoot(this, ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     * @deprecated Renamed to Sqrt.
 */
@Deprecated
    public EDecimal SquareRoot(EContext ctx) {
      return GetMathValue(ctx).SquareRoot(this, ctx);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @return An EDecimal object.
     */
    public EDecimal Subtract(EDecimal otherValue) {
      return this.Subtract(otherValue, EContext.UnlimitedHalfEven);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     * @throws NullPointerException The parameter is null.
     */
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

    /**
     *
     * @return A 64-bit floating-point number.
     */
    public double ToDouble() {
      if (this.IsPositiveInfinity()) {
        return Double.POSITIVE_INFINITY;
      }
      if (this.IsNegativeInfinity()) {
        return Double.NEGATIVE_INFINITY;
      }
      if (this.isNegative() && this.isZero()) {
        return Extras.IntegersToDouble(new int[] { 0, ((int)(1 << 31)) });
      }
      if (this.isZero()) {
        return 0.0;
      }
      if (this.isFinite()) {
       EInteger adjExp = this.GetAdjustedExponent();
        if (adjExp.compareTo(EInteger.FromInt64(-326)) < 0) {
          // Very low exponent, treat as 0
        return this.isNegative() ? Extras.IntegersToDouble(new int[] { 0,
            ((int)(1 << 31)) }) : 0.0;
       }
       if (adjExp.compareTo(EInteger.FromInt64(309)) > 0) {
        // Very high exponent, treat as infinity
        return this.isNegative() ? Double.NEGATIVE_INFINITY :
          Double.POSITIVE_INFINITY;
       }
      }
      return this.ToEFloat(EContext.Binary64).ToDouble();
    }

    /**
     *
     * @return An EInteger object.
     */
    public EInteger ToEInteger() {
      return this.ToEIntegerInternal(false);
    }

    /**
     *
     * @return An EInteger object.
     * @deprecated Renamed to ToEIntegerIfExact.
 */
@Deprecated
    public EInteger ToEIntegerExact() {
      return this.ToEIntegerInternal(true);
    }

    /**
     *
     * @return An EInteger object.
     */
    public EInteger ToEIntegerIfExact() {
      return this.ToEIntegerInternal(true);
    }

    /**
     *
     * @return A string object.
     */
    public String ToEngineeringString() {
      return this.ToStringInternal(1);
    }

    /**
     *
     * @return An EFloat object.
     * @deprecated Renamed to ToEFloat.
 */
@Deprecated
    public EFloat ToExtendedFloat() {
      return this.ToEFloat(EContext.UnlimitedHalfEven);
    }

    /**
     *
     * @return An EFloat object.
     */
    public EFloat ToEFloat() {
      return this.ToEFloat(EContext.UnlimitedHalfEven);
    }

    /**
     *
     * @return A string object.
     */
    public String ToPlainString() {
      return this.ToStringInternal(2);
    }

    /**
     *
     * @return A 32-bit floating-point number.
     */
    public float ToSingle() {
      if (this.IsPositiveInfinity()) {
        return Float.POSITIVE_INFINITY;
      }
      if (this.IsNegativeInfinity()) {
        return Float.NEGATIVE_INFINITY;
      }
      if (this.isNegative() && this.isZero()) {
        return Float.intBitsToFloat(1 << 31);
      }
      if (this.isZero()) {
        return 0.0f;
      }
      EInteger adjExp = this.GetAdjustedExponent();
      if (adjExp.compareTo(EInteger.FromInt64(-47)) < 0) {
        // Very low exponent, treat as 0
        return this.isNegative() ?
          Float.intBitsToFloat(1 << 31) :
          0.0f;
      }
      if (adjExp.compareTo(EInteger.FromInt64(39)) > 0) {
        // Very high exponent, treat as infinity
        return this.isNegative() ? Float.NEGATIVE_INFINITY :
          Float.POSITIVE_INFINITY;
      }
      return this.ToEFloat(EContext.Binary32).ToSingle();
    }

    /**
     *
     * @return A string object.
     */
    @Override public String toString() {
      return this.ToStringInternal(0);
    }

    /**
     *
     * @return An EDecimal object.
     */
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

      int sign = (((flags & BigNumberFlags.FlagSpecial) == 0) &&
                mantissa.isValueZero()) ? 0 : (((flags &
                    BigNumberFlags.FlagNegative) != 0) ? -1 : 1);
      return new EDecimal(
        mantissa,
        exponent,
        flags,
        sign);
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

      int sign = (((flags & BigNumberFlags.FlagSpecial) == 0) &&
                mantissa.isZero()) ? 0 : (((flags &
                    BigNumberFlags.FlagNegative) != 0) ? -1 : 1);
      return new EDecimal(
        FastIntegerFixed.FromBig(mantissa),
        FastIntegerFixed.FromBig(exponent),
        flags,
        sign);
    }

    private static boolean AppendString(
      StringBuilder builder,
      char c,
      FastInteger count) {
      if (count.CompareToInt(Integer.MAX_VALUE) > 0 || count.signum() < 0) {
        throw new UnsupportedOperationException();
      }
      int icount = count.AsInt32();
      for (int i = icount - 1; i >= 0; --i) {
        builder.append(c);
      }
      return true;
    }

    private static EInteger GetAdjustedExponentBinary(EFloat ef) {
      if (!ef.isFinite()) {
        return EInteger.FromInt32(0);
      }
      if (ef.isZero()) {
        return EInteger.FromInt32(0);
      }
      EInteger retEInt = ef.getExponent();
      int smallPrecision = ef.getUnsignedMantissa().GetSignedBitLength();
      --smallPrecision;
      retEInt = retEInt.Add(EInteger.FromInt32(smallPrecision));
      return retEInt;
    }

    private static IRadixMath<EDecimal> GetMathValue(EContext ctx) {
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

    private EInteger GetAdjustedExponent() {
      if (!this.isFinite()) {
        return EInteger.FromInt32(0);
      }
      if (this.isZero()) {
        return EInteger.FromInt32(0);
      }
      EInteger retEInt = this.getExponent();
      int smallPrecision = this.getUnsignedMantissa().GetDigitCount();
      --smallPrecision;
      retEInt = retEInt.Add(EInteger.FromInt32(smallPrecision));
      return retEInt;
    }

    private EDecimal RoundToExponentFast(
  int exponentSmall,
  ERounding rounding) {
      if (this.isFinite() && this.exponent.CanFitInInt32() &&
        this.unsignedMantissa.CanFitInInt32()) {
        int thisExponentSmall = this.exponent.AsInt32();
        if (thisExponentSmall == exponentSmall) {
          return this;
        }
        int thisMantissaSmall = this.unsignedMantissa.AsInt32();
        if (thisExponentSmall >= -100 && thisExponentSmall <= 100 &&
          exponentSmall >= -100 && exponentSmall <= 100) {
          if (rounding == ERounding.Down) {
            int diff = exponentSmall - thisExponentSmall;
            if (diff >= 1 && diff <= 9) {
              thisMantissaSmall /= ValueTenPowers[diff];
              return new EDecimal(
                new FastIntegerFixed(thisMantissaSmall),
                new FastIntegerFixed(exponentSmall),
                this.flags,
                thisMantissaSmall == 0 ? 0 : ((this.flags == 0) ? 1 : -1));
            }
          } else if (rounding == ERounding.HalfEven &&
              thisMantissaSmall != Integer.MAX_VALUE) {
            int diff = exponentSmall - thisExponentSmall;
            if (diff >= 1 && diff <= 9) {
              int pwr = ValueTenPowers[diff - 1];
              int div = thisMantissaSmall / pwr;
              int div2 = (div > 43698) ? (div / 10) : ((div * 26215) >> 18);
              int rem = div - (div2 * 10);
              if (rem > 5) {
                ++div2;
              } else if (rem == 5 && (thisMantissaSmall - (div * pwr)) != 0) {
                ++div2;
              } else if (rem == 5 && (div2 & 1) == 1) {
                ++div2;
              }
              return new EDecimal(
                new FastIntegerFixed(div2),
                new FastIntegerFixed(exponentSmall),
                this.flags,
                div2 == 0 ? 0 : ((this.flags == 0) ? 1 : -1));
            }
          }
        }
      }
      return null;
    }

    private boolean IsIntegerPartZero() {
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
        FastInteger bigexponent = this.exponent.ToFastInteger().Negate();
        EInteger bigmantissa = this.unsignedMantissa.ToEInteger();
        DigitShiftAccumulator acc = new DigitShiftAccumulator(bigmantissa, 0, 0);
  return (acc.GetDigitLength().compareTo(bigexponent) <= 0) ? true :
          false;
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
        acc.TruncateRight(bigexponent);
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

    private static boolean HasTerminatingBinaryExpansion(EInteger
      den) {
      if (den.isZero()) {
        return false;
      }
      if (den.GetUnsignedBit(0) && den.compareTo(EInteger.FromInt32(1)) != 0) {
        return false;
      }
      int lowBit = den.GetLowBit();
      den = den.ShiftRight(lowBit);
      return den.equals(EInteger.FromInt32(1));
    }

    private EFloat WithThisSign(EFloat ef) {
      return this.isNegative() ? ef.Negate() : ef;
    }

    /**
     *
     * @param ec Not documented yet.
     * @return An EFloat object.
     */
    public EFloat ToEFloat(EContext ec) {
      // TODO: Investigate speeding up Binary64 case
      EInteger bigintExp = this.getExponent();
      EInteger bigintMant = this.getUnsignedMantissa();
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
      if (bigintMant.isZero()) {
        return this.isNegative() ? EFloat.NegativeZero.RoundToPrecision(ec) :
          EFloat.Zero.RoundToPrecision(ec);
      }
      if (bigintExp.isZero()) {
        // Integer
        // DebugUtility.Log("Integer");
     return this.WithThisSign(EFloat.FromEInteger(bigintMant))
  .RoundToPrecision(ec);
      }
      if (bigintExp.signum() > 0) {
        // Scaled integer
        // DebugUtility.Log("Scaled integer");
        EInteger bigmantissa = bigintMant;
        bigintExp = NumberUtility.FindPowerOfTenFromBig(bigintExp);
        bigmantissa = bigmantissa.Multiply(bigintExp);
    return this.WithThisSign(EFloat.FromEInteger(bigmantissa))
  .RoundToPrecision(ec);
      } else {
        // Fractional number
        // DebugUtility.Log("Fractional");
        EInteger scale = bigintExp;
        EInteger bigmantissa = bigintMant;
        boolean neg = bigmantissa.signum() < 0;
        if (neg) {
          bigmantissa=(bigmantissa).Negate();
        }
        EInteger negscale = scale.Negate();
        // DebugUtility.Log("" + negscale);
        EInteger divisor = NumberUtility.FindPowerOfTenFromBig(negscale);
        EInteger desiredHigh;
        EInteger desiredLow;
        boolean haveCopy = false;
ec = (ec == null) ? (EContext.UnlimitedHalfEven) : ec;
        EContext originalEc = ec;
        if (!ec.getHasMaxPrecision()) {
          EInteger num = bigmantissa;
          EInteger den = divisor;
          EInteger gcd = num.Gcd(den);
          if (gcd.compareTo(EInteger.FromInt32(1)) != 0) {
            den = den.Divide(gcd);
          }
          // DebugUtility.Log("num=" + (num.Divide(gcd)));
          // DebugUtility.Log("den=" + den);
          if (!HasTerminatingBinaryExpansion(den)) {
            // DebugUtility.Log("Approximate");
            // DebugUtility.Log("=>{0}\r\n->{1}", bigmantissa, divisor);
            ec = ec.WithPrecision(53).WithBlankFlags();
            haveCopy = true;
          } else {
            bigmantissa = bigmantissa.Divide(gcd);
            divisor = den;
          }
        }
        // NOTE: Precision added by 2 to accommodate rounding
        // to odd
        EInteger valueEcPrec = ec.getHasMaxPrecision() ? ec.getPrecision().Add(EInteger.FromInt32(2)) : EInteger.FromInt32(0);
        int valueEcPrecInt = 0;
        if (!valueEcPrec.CanFitInInt32()) {
          EInteger precm1 = valueEcPrec.Subtract(EInteger.FromInt32(1));
          desiredLow = EInteger.FromInt32(1);
          while (precm1.signum() > 0) {
           int shift = 1000000;
           if (precm1.compareTo(EInteger.FromInt64(1000000)) < 0) {
            shift = precm1.ToInt32Checked();
           }
           desiredLow = desiredLow.ShiftLeft(shift);
           precm1 = precm1.Subtract(EInteger.FromInt32(shift));
          }
          desiredHigh = desiredLow.ShiftLeft(1);
        } else {
          int prec = valueEcPrec.ToInt32Checked();
          valueEcPrecInt = prec;
          desiredHigh = EInteger.FromInt32(1).ShiftLeft(prec);
          int precm1 = prec - 1;
          desiredLow = EInteger.FromInt32(1).ShiftLeft(precm1);
        }
        // DebugUtility.Log("=>{0}\r\n->{1}", bigmantissa, divisor);
        EInteger[] quorem = ec.getHasMaxPrecision() ?
          bigmantissa.DivRem(divisor) : null;
        // DebugUtility.Log("=>{0}\r\n->{1}", quorem[0], desiredHigh);
        FastInteger adjust = new FastInteger(0);
        if (!ec.getHasMaxPrecision()) {
          int term = divisor.GetLowBit();
          bigmantissa = bigmantissa.ShiftLeft(term);
          adjust.SubtractInt(term);
          quorem = bigmantissa.DivRem(divisor);
        } else if (quorem[0].compareTo(desiredHigh) >= 0) {
          do {
            boolean optimized = false;
            if (divisor.compareTo(bigmantissa) < 0) {
              if (ec.getClampNormalExponents() && valueEcPrecInt > 0 &&
                  valueEcPrecInt != Integer.MAX_VALUE) {
               int valueBmBits = bigmantissa.GetUnsignedBitLength();
               int divBits = divisor.GetUnsignedBitLength();
               if (divBits < valueBmBits) {
                int bitdiff = valueBmBits - divBits;
                if (bitdiff > valueEcPrecInt + 1) {
                  bitdiff -= valueEcPrecInt + 1;
                  divisor = divisor.ShiftLeft(bitdiff);
                  adjust.AddInt(bitdiff);
                  optimized = true;
                }
               }
              }
            } else {
              if (ec.getClampNormalExponents() && valueEcPrecInt > 0) {
                int valueBmBits = bigmantissa.GetUnsignedBitLength();
                int divBits = divisor.GetUnsignedBitLength();
             if (valueBmBits >= divBits && valueEcPrecInt <= Integer.MAX_VALUE -
                  divBits) {
                  int vbb = divBits + valueEcPrecInt;
                  if (valueBmBits < vbb) {
                    valueBmBits = vbb - valueBmBits;
                    divisor = divisor.ShiftLeft(valueBmBits);
                    adjust.AddInt(valueBmBits);
                    optimized = true;
                  }
                }
              }
            }
            if (!optimized) {
              divisor = divisor.ShiftLeft(1);
              adjust.Increment();
            }
// DebugUtility.Log("deshigh\n==>" + (//
// bigmantissa) + "\n-->" + (//
// divisor));
// DebugUtility.Log("deshigh " + (//
// bigmantissa.GetUnsignedBitLength()) + "/" + (//
// divisor.GetUnsignedBitLength()));
            quorem = bigmantissa.DivRem(divisor);
            if (quorem[1].isZero()) {
              int valueBmBits = quorem[0].GetUnsignedBitLength();
              int divBits = desiredLow.GetUnsignedBitLength();
              if (valueBmBits < divBits) {
                valueBmBits = divBits - valueBmBits;
                quorem[0] = quorem[0].ShiftLeft(valueBmBits);
                adjust.AddInt(valueBmBits);
              }
            }
  // DebugUtility.Log("quorem[0]="+quorem[0]);
     // DebugUtility.Log("quorem[1]="+quorem[1]);
        // DebugUtility.Log("desiredLow="+desiredLow);
           // DebugUtility.Log("desiredHigh="+desiredHigh);
          } while (quorem[0].compareTo(desiredHigh) >= 0);
        } else if (quorem[0].compareTo(desiredLow) < 0) {
          do {
            boolean optimized = false;
            if (bigmantissa.compareTo(divisor) < 0) {
              int valueBmBits = bigmantissa.GetUnsignedBitLength();
              int divBits = divisor.GetUnsignedBitLength();
              if (valueBmBits < divBits) {
                valueBmBits = divBits - valueBmBits;
                bigmantissa = bigmantissa.ShiftLeft(valueBmBits);
                adjust.SubtractInt(valueBmBits);
                optimized = true;
              }
            } else {
              if (ec.getClampNormalExponents() && valueEcPrecInt > 0) {
                int valueBmBits = bigmantissa.GetUnsignedBitLength();
                int divBits = divisor.GetUnsignedBitLength();
             if (valueBmBits >= divBits && valueEcPrecInt <= Integer.MAX_VALUE -
                  divBits) {
                  int vbb = divBits + valueEcPrecInt;
                  if (valueBmBits < vbb) {
                    valueBmBits = vbb - valueBmBits;
                    bigmantissa = bigmantissa.ShiftLeft(valueBmBits);
                    adjust.SubtractInt(valueBmBits);
                    optimized = true;
                  }
                }
              }
            }
            if (!optimized) {
              bigmantissa = bigmantissa.ShiftLeft(1);
              adjust.Decrement();
            }
            // DebugUtility.Log("deslow " + (//
            // bigmantissa.GetUnsignedBitLength()) + "/" + (//
            // divisor.GetUnsignedBitLength()));
            quorem = bigmantissa.DivRem(divisor);
            if (quorem[1].isZero()) {
              int valueBmBits = quorem[0].GetUnsignedBitLength();
              int divBits = desiredLow.GetUnsignedBitLength();
              if (valueBmBits < divBits) {
                valueBmBits = divBits - valueBmBits;
                quorem[0] = quorem[0].ShiftLeft(valueBmBits);
                adjust.SubtractInt(valueBmBits);
              }
            }
          } while (quorem[0].compareTo(desiredLow) < 0);
        }
        // Round to odd to avoid rounding errors
        if (!quorem[1].isZero() && quorem[0].isEven()) {
          quorem[0] = quorem[0].Add(EInteger.FromInt32(1));
        }
        EFloat efret = this.WithThisSign(
  EFloat.Create(
  quorem[0],
  adjust.AsEInteger()));
        // DebugUtility.Log("-->" + (efret.getMantissa().ToRadixString(2)) + " " +
        // (// efret.getExponent()));
        efret = efret.RoundToPrecision(ec);
        if (haveCopy && originalEc.getHasFlags()) {
          originalEc.setFlags(originalEc.getFlags()|(ec.getFlags()));
        }
        return efret;
      }
    }

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
      String mantissaString = this.unsignedMantissa.toString();
      if (scaleSign == 0) {
        return negative ? "-" + mantissaString : mantissaString;
      }
      boolean iszero = this.unsignedMantissa.isValueZero();
      if (mode == 2 && iszero && scaleSign < 0) {
        // special case for zero in plain
        return negative ? "-" + mantissaString : mantissaString;
      }
      StringBuilder builder = null;
      if (mode == 0 && mantissaString.length() < 100 &&
        this.exponent.CanFitInInt32()) {
        int intExp = this.exponent.AsInt32();
        if (intExp > -100 && intExp < 100) {
          int adj = (intExp + mantissaString.length()) - 1;
          if (scaleSign >= 0 && adj >= -6) {
            if (scaleSign > 0) {
              int dp = intExp + mantissaString.length();
              if (dp < 0) {
                builder = new StringBuilder(mantissaString.length() + 6);
                if (negative) {
                  builder.append("-0.");
                } else {
                  builder.append("0.");
                }
                dp = -dp;
                for (int j = 0; j < dp; ++j) {
                  builder.append('0');
                }
                builder.append(mantissaString);
                return builder.toString();
              } else if (dp == 0) {
                builder = new StringBuilder(mantissaString.length() + 6);
                if (negative) {
                  builder.append("-0.");
                } else {
                  builder.append("0.");
                }
                builder.append(mantissaString);
                return builder.toString();
              } else if (dp > 0 && dp <= mantissaString.length()) {
                builder = new StringBuilder(mantissaString.length() + 6);
                if (negative) {
                  builder.append('-');
                }
                builder.append(mantissaString, 0, (0)+(dp));
                builder.append('.');
                builder.append(
                  mantissaString, dp, (dp)+(mantissaString.length() - dp));
                return builder.toString();
              }
            }
          }
        }
      }
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
        int intphase = adjustedExponent.Copy().Abs().Remainder(3).AsInt32();
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
                    0 ? Integer.MAX_VALUE : tmpFast.AsInt32());
            if (negative) {
              builder.append('-');
            }
            builder.append("0.");
            AppendString(builder, '0', decimalPoint.Copy().Negate());
            builder.append(mantissaString);
          } else if (cmp == 0) {
            FastInteger tmpFast = new FastInteger(mantissaString.length()).AddInt(6);
            builder = new StringBuilder(tmpFast.CompareToInt(Integer.MAX_VALUE) >
                    0 ? Integer.MAX_VALUE : tmpFast.AsInt32());
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
            int tmpInt = insertionPoint.AsInt32();
            if (tmpInt < 0) {
              tmpInt = 0;
            }
            FastInteger tmpFast = new FastInteger(mantissaString.length()).AddInt(6);
            builder = new StringBuilder(tmpFast.CompareToInt(Integer.MAX_VALUE) >
                    0 ? Integer.MAX_VALUE : tmpFast.AsInt32());
            if (negative) {
              builder.append('-');
            }
            builder.append(mantissaString, 0, (0)+(tmpInt));
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
            int tmpInt = decimalPoint.AsInt32();
            if (tmpInt < 0) {
              tmpInt = 0;
            }
            FastInteger tmpFast = new FastInteger(mantissaString.length()).AddInt(6);
            builder = new StringBuilder(tmpFast.CompareToInt(Integer.MAX_VALUE) >
                    0 ? Integer.MAX_VALUE : tmpFast.AsInt32());
            if (negative) {
              builder.append('-');
            }
            builder.append(mantissaString, 0, (0)+(tmpInt));
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
            int tmpInt = tmp.AsInt32();
            if (tmp.signum() < 0) {
              tmpInt = 0;
            }
            FastInteger tmpFast = new FastInteger(mantissaString.length()).AddInt(6);
            builder = new StringBuilder(tmpFast.CompareToInt(Integer.MAX_VALUE) >
                    0 ? Integer.MAX_VALUE : tmpFast.AsInt32());
            if (negative) {
              builder.append('-');
            }
            builder.append(mantissaString, 0, (0)+(tmpInt));
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
          StringBuilder builderReversed = new StringBuilder();
          while (adjustedExponent.signum() != 0) {
            int digit =
              adjustedExponent.Copy().Remainder(10).AsInt32();
            // Each digit is retrieved from right to left
            builderReversed.append((char)('0' + digit));
            adjustedExponent.Divide(10);
          }
          int count = builderReversed.length();
          String builderReversedString = builderReversed.toString();
          for (int i = 0; i < count; ++i) {
            builder.append(builderReversedString.charAt(count - 1 - i));
          }
        }
        return builder.toString();
      }
    }

    private static final class DecimalMathHelper implements IRadixMathHelper<EDecimal> {
    /**
     *
     * @return A 32-bit signed integer.
     */
      public int GetRadix() {
        return 10;
      }

    /**
     *
     * @param value Not documented yet.
     * @return A 32-bit signed integer.
     */
      public int GetSign(EDecimal value) {
        return value.signum();
      }

    /**
     *
     * @param value Not documented yet.
     * @return An EInteger object.
     */
      public EInteger GetMantissa(EDecimal value) {
        return value.unsignedMantissa.ToEInteger();
      }

    /**
     *
     * @param value Not documented yet.
     * @return An EInteger object.
     */
      public EInteger GetExponent(EDecimal value) {
        return value.exponent.ToEInteger();
      }

      public FastIntegerFixed GetMantissaFastInt(EDecimal value) {
        return value.unsignedMantissa;
      }

      public FastIntegerFixed GetExponentFastInt(EDecimal value) {
        return value.exponent;
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
  fastInt.AsInt32(),
  lastDigit,
  olderDigits);
        } else {
return new DigitShiftAccumulator(
  fastInt.ToEInteger(),
  lastDigit,
  olderDigits);
        }
      }

    /**
     *
     * @param bigint Not documented yet.
     * @return An IShiftAccumulator object.
     */
      public IShiftAccumulator CreateShiftAccumulator(EInteger bigint) {
        return new DigitShiftAccumulator(bigint, 0, 0);
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
        int lowBit = den.GetLowBit();
        den = den.ShiftRight(lowBit);
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
        if (fiveShift.CompareToInt(lowBit) > 0) {
          return fiveShift;
        } else {
          return new FastInteger(lowBit);
        }
      }

      public EInteger MultiplyByRadixPower(
        EInteger bigint,
        FastInteger power) {
        EInteger tmpbigint = bigint;
        if (tmpbigint.isZero()) {
          return tmpbigint;
        }
        boolean fitsInInt32 = power.CanFitInInt32();
        int powerInt = fitsInInt32 ? power.AsInt32() : 0;
        if (fitsInInt32 && powerInt == 0) {
          return tmpbigint;
        }
        EInteger bigtmp = null;
        if (tmpbigint.compareTo(EInteger.FromInt32(1)) != 0) {
          if (fitsInInt32) {
            if (powerInt <= 10) {
              bigtmp = NumberUtility.FindPowerOfTen(powerInt);
              tmpbigint = tmpbigint.Multiply(bigtmp);
            } else {
              bigtmp = NumberUtility.FindPowerOfFive(powerInt);
              tmpbigint = tmpbigint.Multiply(bigtmp);
              tmpbigint = tmpbigint.ShiftLeft(powerInt);
            }
          } else {
            bigtmp = NumberUtility.FindPowerOfTenFromBig(power.AsEInteger());
            tmpbigint = tmpbigint.Multiply(bigtmp);
          }
          return tmpbigint;
        }
        return fitsInInt32 ? NumberUtility.FindPowerOfTen(powerInt) :
          NumberUtility.FindPowerOfTenFromBig(power.AsEInteger());
      }

    /**
     *
     * @param value Not documented yet.
     * @return A 32-bit signed integer.
     */
      public int GetFlags(EDecimal value) {
        return value.flags;
      }

    /**
     *
     * @param mantissa Not documented yet.
     * @param exponent Not documented yet.
     * @param flags Not documented yet. (3).
     * @return An EDecimal object.
     */
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

    /**
     *
     * @return A 32-bit signed integer.
     */
      public int GetArithmeticSupport() {
        return BigNumberFlags.FiniteAndNonFinite;
      }

    /**
     *
     * @param val Not documented yet.
     * @return An EDecimal object.
     */
      public EDecimal ValueOf(int val) {
        return (val == 0) ? Zero : ((val == 1) ? One : FromInt64(val));
      }
    }

    // Begin integer conversions

    /**
     *
     * @return A Byte object.
     */
public byte ToByteChecked() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
if (this.IsIntegerPartZero()) {
 return (byte)0;
}
if (this.isNegative()) {
 throw new ArithmeticException("Value out of range");
}
if (this.exponent.CompareToInt(3) >= 0) {
throw new ArithmeticException("Value out of range: ");
}
 return this.ToEInteger().ToByteChecked();
}

    /**
     *
     * @return A Byte object.
     */
public byte ToByteUnchecked() {
 return this.isFinite() ? this.ToEInteger().ToByteUnchecked() : (byte)0;
}

    /**
     *
     * @return A Byte object.
     */
public byte ToByteIfExact() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
 if (this.isZero()) {
 return (byte)0;
}
 if (this.isNegative()) {
throw new ArithmeticException("Value out of range");
}
if (this.exponent.CompareToInt(3) >= 0) {
throw new ArithmeticException("Value out of range");
}
 return this.ToEIntegerIfExact().ToByteChecked();
}

    /**
     *
     * @param inputByte Not documented yet.
     * @return An EDecimal object.
     */
public static EDecimal FromByte(byte inputByte) {
 int val = ((int)inputByte) & 0xff;
 return FromInt32(val);
}

    /**
     *
     * @return A 16-bit signed integer.
     */
public short ToInt16Checked() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
if (this.IsIntegerPartZero()) {
 return (short)0;
}
if (this.exponent.CompareToInt(5) >= 0) {
throw new ArithmeticException("Value out of range: ");
}
 return this.ToEInteger().ToInt16Checked();
}

    /**
     *
     * @return A 16-bit signed integer.
     */
public short ToInt16Unchecked() {
 return this.isFinite() ? this.ToEInteger().ToInt16Unchecked() : (short)0;
}

    /**
     *
     * @return A 16-bit signed integer.
     */
public short ToInt16IfExact() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
 if (this.isZero()) {
 return (short)0;
}
if (this.exponent.CompareToInt(5) >= 0) {
throw new ArithmeticException("Value out of range");
}
 return this.ToEIntegerIfExact().ToInt16Checked();
}

    /**
     *
     * @param inputInt16 Not documented yet.
     * @return An EDecimal object.
     */
public static EDecimal FromInt16(short inputInt16) {
 int val = (int)inputInt16;
 return FromInt32(val);
}

    /**
     *
     * @return A 32-bit signed integer.
     */
public int ToInt32Checked() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
if (this.IsIntegerPartZero()) {
 return (int)0;
}
if (this.exponent.CompareToInt(10) >= 0) {
throw new ArithmeticException("Value out of range: ");
}
 return this.ToEInteger().ToInt32Checked();
}

    /**
     *
     * @return A 32-bit signed integer.
     */
public int ToInt32Unchecked() {
 return this.isFinite() ? this.ToEInteger().ToInt32Unchecked() : (int)0;
}

    /**
     *
     * @return A 32-bit signed integer.
     */
public int ToInt32IfExact() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
 if (this.isZero()) {
 return (int)0;
}
if (this.exponent.CompareToInt(10) >= 0) {
throw new ArithmeticException("Value out of range");
}
 return this.ToEIntegerIfExact().ToInt32Checked();
}

    /**
     *
     * @return A 64-bit signed integer.
     */
public long ToInt64Checked() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
if (this.IsIntegerPartZero()) {
 return (long)0;
}
if (this.exponent.CompareToInt(19) >= 0) {
throw new ArithmeticException("Value out of range: ");
}
 return this.ToEInteger().ToInt64Checked();
}

    /**
     *
     * @return A 64-bit signed integer.
     */
public long ToInt64Unchecked() {
 return this.isFinite() ? this.ToEInteger().ToInt64Unchecked() : (long)0;
}

    /**
     *
     * @return A 64-bit signed integer.
     */
public long ToInt64IfExact() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
 if (this.isZero()) {
 return (long)0;
}
if (this.exponent.CompareToInt(19) >= 0) {
throw new ArithmeticException("Value out of range");
}
 return this.ToEIntegerIfExact().ToInt64Checked();
}

// End integer conversions
  }
