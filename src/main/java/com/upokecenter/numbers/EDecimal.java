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

  /**
   * Represents an arbitrary-precision decimal floating-point number. (The "E"
   *  stands for "extended", meaning that instances of this class can be
   * values other than numbers proper, such as infinity and not-a-number.)
   * <p><b>About decimal arithmetic</b> </p> <p>Decimal (base-10)
   * arithmetic, such as that provided by this class, is appropriate for
   * calculations involving such real-world data as prices and other sums
   * of money, tax rates, and measurements. These calculations often
   * involve multiplying or dividing one decimal with another decimal, or
   * performing other operations on decimal numbers. Many of these
   * calculations also rely on rounding behavior in which the result after
   * rounding is an arbitrary-precision decimal number (for example,
   * multiplying a price by a premium rate, then rounding, should result in
   * a decimal amount of money).</p> <p>On the other hand, most
   * implementations of <code>float</code> and <code>double</code> , including in C#
   * and Java, store numbers in a binary (base-2) floating-point format and
   * use binary floating-point arithmetic. Many decimal numbers can't be
   * represented exactly in binary floating-point format (regardless of its
   * length). Applying binary arithmetic to numbers intended to be decimals
   * can sometimes lead to unintuitive results, as is shown in the
   * description for the FromDouble() method of this class.</p> <p><b>About
   * EDecimal instances</b> </p> <p>Each instance of this class consists of
   * an integer significand and an integer exponent, both
   * arbitrary-precision. The value of the number equals significand *
   * 10^exponent.</p> <p>The significand is the value of the digits that
   * make up a number, ignoring the decimal point and exponent. For
   * example, in the number 2356.78, the significand is 235678. The
   *  exponent is where the "floating" decimal point of the number is
   *  located. A positive exponent means "move it to the right", and a
   *  negative exponent means "move it to the left." In the example 2,
   * 356.78, the exponent is -2, since it has 2 decimal places and the
   *  decimal point is "moved to the left by 2." Therefore, in the
   * arbitrary-precision decimal representation, this number would be
   * stored as 235678 * 10^-2.</p> <p>The significand and exponent format
   * preserves trailing zeros in the number's value. This may give rise to
   * multiple ways to store the same value. For example, 1.00 and 1 would
   * be stored differently, even though they have the same value. In the
   * first case, 100 * 10^-2 (100 with decimal point moved left by 2), and
   * in the second case, 1 * 10^0 (1 with decimal point moved 0).</p>
   * <p>This class also supports values for negative zero, not-a-number
   * (NaN) values, and infinity. <b>Negative zero</b> is generally used
   * when a negative number is rounded to 0; it has the same mathematical
   * value as positive zero. <b>Infinity</b> is generally used when a
   * non-zero number is divided by zero, or when a very high or very low
   * number can't be represented in a given exponent range.
   * <b>Not-a-number</b> is generally used to signal errors.</p> <p>This
   * class implements the General Decimal Arithmetic Specification version
   * 1.70 except part of chapter 6(
   * <code>http://speleotrove.com/decimal/decarith.html</code>).</p>
   * <p><b>Errors and Exceptions</b> </p> <p>Passing a signaling NaN to any
   * arithmetic operation shown here will signal the flag FlagInvalid and
   * return a quiet NaN, even if another operand to that operation is a
   * quiet NaN, unless the operation's documentation expressly states that
   * another result happens when a signaling NaN is passed to that
   * operation.</p> <p>Passing a quiet NaN to any arithmetic operation
   * shown here will return a quiet NaN, unless the operation's
   * documentation expressly states that another result happens when a
   * quiet NaN is passed to that operation. Invalid operations will also
   * return a quiet NaN, as stated in the individual methods.</p> <p>Unless
   * noted otherwise, passing a null arbitrary-precision decimal argument
   * to any method here will throw an exception.</p> <p>When an arithmetic
   * operation signals the flag FlagInvalid, FlagOverflow, or
   * FlagDivideByZero, it will not throw an exception too, unless the
   * flag's trap is enabled in the arithmetic context (see EContext's Traps
   * property).</p> <p>If an operation requires creating an intermediate
   * value that might be too big to fit in memory (or might require more
   * than 2 gigabytes of memory to store -- due to the current use of a
   * 32-bit integer internally as a length), the operation may signal an
   * invalid-operation flag and return not-a-number (NaN). In certain rare
   * cases, the compareTo method may throw OutOfMemoryError (called
   * OutOfMemoryError in Java) in the same circumstances.</p>
   * <p><b>Serialization</b> </p> <p>An arbitrary-precision decimal value
   * can be serialized (converted to a stable format) in one of the
   * following ways:</p> <ul><li>By calling the toString() method, which
   * will always return distinct strings for distinct arbitrary-precision
   * decimal values.</li> <li>By calling the UnsignedMantissa, Exponent,
   * and IsNegative properties, and calling the IsInfinity, IsQuietNaN, and
   * IsSignalingNaN methods. The return values combined will uniquely
   * identify a particular arbitrary-precision decimal value.</li> </ul>
   * <p><b>Thread safety</b> </p> <p>Instances of this class are immutable,
   * so they are inherently safe for use by multiple threads. Multiple
   * instances of this object with the same properties are interchangeable,
   *  so they should not be compared using the "==" operator (which might
   * only check if each side of the operator is the same instance).</p>
   * <p><b>Comparison considerations</b> </p> <p>This class's natural
   * ordering (under the compareTo method) is not consistent with the
   * Equals method. This means that two values that compare as equal under
   * the compareTo method might not be equal under the Equals method. The
   * compareTo method compares the mathematical values of the two instances
   * passed to it (and considers two different NaN values as equal), while
   * two instances with the same mathematical value, but different
   * exponents, will be considered unequal under the Equals method.</p>
   * <p><b>Security note</b> </p> <p>It is not recommended to implement
   * security-sensitive algorithms using the methods in this class, for
   * several reasons:</p> <ul><li><code>EDecimal</code> objects are immutable, so
   * they can't be modified, and the memory they occupy is not guaranteed
   * to be cleared in a timely fashion due to garbage collection. This is
   * relevant for applications that use many-digit-long numbers as secret
   * parameters.</li> <li>The methods in this class (especially those that
   *  involve arithmetic) are not guaranteed to be "constant-time"
   * (non-data-dependent) for all relevant inputs. Certain attacks that
   * involve encrypted communications have exploited the timing and other
   * aspects of such communications to derive keying material or cleartext
   * indirectly.</li> </ul> <p>Applications should instead use dedicated
   * security libraries to handle big numbers in security-sensitive
   * algorithms.</p> <p><b>Reproducibility note</b> </p> <p>Some
   * applications, such as simulations, care about results that are
   * reproducible, bit for bit, across computers and across runs of the
   *  application. Bruce Dawson, in "Floating-Point Determinism" (
   * <code>https://randomascii.wordpress.com/</code>
   * <code>2013/07/16/floating-point-determinism/</code>), identified many
   * reproducibility issues with floating-point numbers, and here is how
   * they relate to the EDecimal and EFloat classes of this library:</p>
   * <ul><li>Runtime floating-point settings: All the settings that change
   * how EDecimal and EFloat behave are given as parameters to the
   * appropriate methods, especially via EContext objects, which specify
   * the precision, rounding, and exponent range of numbers, among other
   *  things. The EDecimal and EFloat classes avoid the use of "native"
   * floating-point data types (except for methods that convert to or from
   * <code>float</code> , <code>double</code> , or <code>System.Decimal</code>). Such
   *  "native" types are often subject to runtime settings that change how
   * floating-point math behaves with them, and these settings are often
   * not accessible to .NET or Java code.</li> <li>Non-associativity and
   * intermediate precisions: In general, EDecimal and EFloat use
   *  "unlimited" precision in their calculations unless specified otherwise
   * by an EContext object. However, by limiting the precision of EDecimal,
   * EFloat, and other floating-point numbers in this way, operations such
   * as addition and multiplication on three or more numbers can be
   * <i>non-associative</i> , meaning the result can change depending on
   * the order in which those numbers are added or multiplied. This
   * property means that if an algorithm does not ensure such numbers are
   * added or multiplied in the same order every time, its results may not
   * be reproducible across computers or across runs of the application.
   * This non-associativity problem can happen, for example, if an
   * application splits a calculation across several threads and combines
   * their results in the end. The problems with an unspecified order of
   * operations (in the same line of code) and intermediate precisions
   * (problems present in C and C++, for example) don't exist with method
   * calls to EDecimal and EFloat methods, especially since they require
   * limited-precision support to be declared explicitly via EContext.</li>
   * <li>fmadd instruction: EDecimal and EFloat include a MultiplyAndAdd
   * method with the same semantics as in the General Decimal Arithmetic
   * Specification, which requires delivering correctly rounded results for
   * this method.</li> <li>Square root estimate: Not applicable since
   * EDecimal and EFloat don't include any estimates to square root.</li>
   * <li>Transcendental functions: This includes logarithms, exponentials,
   * and the Pi method. For these functions, results are not guaranteed to
   * always be correctly rounded. When using transcendentals, an
   * application that cares about reproducibility should choose one version
   * of this library and stick to it; this at least has the advantage that
   * the implementation will be the same across computers, unlike with
   *  "native" floating-point types where the choice of implementation is
   * often not within the application's control.</li> <li>Conversions:
   * Conversions between EDecimal or EFloat and text strings have the same
   * implementation across computers for the same version of this library
   * (see also the advice for transcendentals above). But as for the
   * ToDouble, ToSingle, FromDouble, and FromSingle methods, note that some
   * implementations of Java and.NET may or may not support preserving the
   * value of subnormal numbers (numbers other than zero with the lowest
   * possible exponent) or the payloads held in a not-a-number (NaN) value
   * of float or double; thus these methods should not be considered
   * reproducible across computers.</li> <li>Compiler differences: Not
   *  applicable where these classes don't use "native" floating-point
   * types.</li> <li>Uninitialized data; per-processor code: Not
   * applicable.</li> </ul> <p><b>Forms of numbers</b> </p> <p>There are
   * several other types of numbers that are mentioned in this class and
   * elsewhere in this documentation. For reference, they are specified
   * here.</p> <p><b>Unsigned integer</b> : An integer that's always 0 or
   * greater, with the following maximum values:</p> <ul><li>8-bit unsigned
   * integer, or <i>byte</i> : 255.</li> <li>16-bit unsigned integer:
   * 65535.</li> <li>32-bit unsigned integer: (2 <sup>32</sup> -1).</li>
   * <li>64-bit unsigned integer: (2 <sup>64</sup> -1).</li> </ul>
   * <p><b>Signed integer</b> : An integer in <i>two's-complement form</i>
   * , with the following ranges:</p> <ul><li>8-bit signed integer: -128 to
   * 127.</li> <li>16-bit signed integer: -32768 to 32767.</li> <li>32-bit
   * signed integer: -2 <sup>31</sup> to (2 <sup>31</sup> - 1).</li>
   * <li>64-bit signed integer: -2 <sup>63</sup> to (2 <sup>63</sup> -
   * 1).</li> </ul> <p><b>Two's complement form</b> : In
   * <i>two's-complement form</i> , nonnegative numbers have the highest
   * (most significant) bit set to zero, and negative numbers have that bit
   * (and all bits beyond) set to one, and a negative number is stored in
   * such form by decreasing its absolute value by 1 and swapping the bits
   * of the resulting number.</p> <p><b>64-bit floating-point number</b> :
   * A 64-bit binary floating-point number, in the form <i>significand</i>
   * * 2 <sup><i>exponent</i> </sup> . The significand is 53 bits long
   * (Precision) and the exponent ranges from -1074 (EMin) to 971 (EMax).
   * The number is stored in the following format (commonly called the IEEE
   * 754 format):</p> <pre>|C|BBB...BBB|AAAAAA...AAAAAA|</pre> <ul><li>A.
   * Low 52 bits (Precision minus 1 bits): Lowest bits of the
   * significand.</li> <li>B. Next 11 bits: Exponent area: <ul><li>If all
   * bits are ones, the final stored value is infinity (positive or
   * negative depending on the C bit) if all bits in area A are zeros, or
   * not-a-number (NaN) otherwise.</li> <li>If all bits are zeros, the
   * final stored value is a subnormal number, the exponent is EMin, and
   * the highest bit of the significand is zero.</li> <li>If any other
   * number, the exponent is this value reduced by 1, then raised by EMin,
   * and the highest bit of the significand is one.</li> </ul> </li> <li>C.
   * Highest bit: If one, this is a negative number.</li> </ul> <p>The
   * elements described above are in the same order as the order of each
   * bit of each element, that is, either most significant first or least
   * significant first.</p> <p><b>32-bit binary floating-point number</b> :
   * A 32-bit binary number which is stored similarly to a <i>64-bit
   * floating-point number</i> , except that:</p> <ul><li>Precision is 24
   * bits.</li> <li>EMin is -149.</li> <li>EMax is 104.</li> <li>A. The low
   * 23 bits (Precision minus 1 bits) are the lowest bits of the
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
   * highest bit is one, it's a negative number.</li> </ul> <p>The elements
   * described above are in the same order as the order of each bit of each
   * element, that is, either most significant first or least significant
   * first.</p>
   */

  public final class EDecimal implements Comparable<EDecimal> {
    private static final int RepeatDivideThreshold = 10000;
    static final int MaxSafeInt = 214748363;

    //----------------------------------------------------------------

    /**
     * A not-a-number value.
     */

    public static final EDecimal NaN = CreateWithFlags(
        EInteger.FromInt32(0),
        EInteger.FromInt32(0),
        (byte)BigNumberFlags.FlagQuietNaN);

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

    public static final EDecimal One = new EDecimal(
      FastIntegerFixed.FromInt32(1),
      FastIntegerFixed.Zero,
      (byte)0);

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

    public static final EDecimal Ten = new EDecimal(
      FastIntegerFixed.FromInt32(10),
      FastIntegerFixed.Zero,
      (byte)0);

    /**
     * Represents the number 0.
     */

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

    /**
     * Creates a copy of this arbitrary-precision binary number.
     * @return An arbitrary-precision decimal floating-point number.
     */
    public EDecimal Copy() {
      return new EDecimal(
          this.unsignedMantissa.Copy(),
          this.exponent.Copy(),
          this.flags);
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
     * Gets a value indicating whether this object is finite (not infinity or NaN).
     * @return {@code true} if this object is finite (not infinity or NaN);
     * otherwise, {@code false}.
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
     * Returns whether this object's value is an integer.
     * @return {@code true} if this object's value is an integer; otherwise, {@code
     * false}.
     */
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
            this.unsignedMantissa.isValueZero()) ? 0 : (((this.flags &
                BigNumberFlags.FlagNegative) != 0) ? -1 : 1);
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

    static EDecimal ChangeExponent(EDecimal ret, EInteger exponent) {
      return new EDecimal(
          ret.unsignedMantissa,
          FastIntegerFixed.FromBig(exponent),
          (byte)ret.flags);
    }

    /**
     * Returns a number with the value <code>exponent*10^significand</code>.
     * @param mantissaSmall Desired value for the significand.
     * @param exponentSmall Desired value for the exponent.
     * @return An arbitrary-precision decimal number.
     */
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

    /**
     * Creates a number with the value <code>exponent*10^significand</code>.
     * @param mantissa Desired value for the significand.
     * @param exponentSmall Desired value for the exponent.
     * @return An arbitrary-precision decimal number.
     * @throws NullPointerException The parameter {@code mantissa} is null.
     */
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

    /**
     * Creates a number with the value <code>exponent*10^significand</code>.
     * @param mantissa Desired value for the significand.
     * @param exponentLong Desired value for the exponent.
     * @return An arbitrary-precision decimal number.
     * @throws NullPointerException The parameter {@code mantissa} is null.
     */
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

    /**
     * Creates a number with the value <code>exponent*10^significand</code>.
     * @param mantissa Desired value for the significand.
     * @param exponent Desired value for the exponent.
     * @return An arbitrary-precision decimal number.
     * @throws NullPointerException The parameter {@code mantissa} or {@code
     * exponent} is null.
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

    /**
     * Creates a number with the value <code>exponent*10^significand</code>.
     * @param mantissaLong Desired value for the significand.
     * @param exponentSmall Desired value for the exponent.
     * @return An arbitrary-precision decimal number.
     */
    public static EDecimal Create(
      long mantissaLong,
      int exponentSmall) {
      return Create(mantissaLong, (long)exponentSmall);
    }

    /**
     * Creates a number with the value <code>exponent*10^significand</code>.
     * @param mantissaLong Desired value for the significand.
     * @param exponentLong Desired value for the exponent.
     * @return An arbitrary-precision decimal number.
     */
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

    /**
     * Creates a not-a-number arbitrary-precision decimal number.
     * @param diag An integer, 0 or greater, to use as diagnostic information
     * associated with this object. If none is needed, should be zero. To
     * get the diagnostic information from another arbitrary-precision
     * decimal floating-point number, use that object's {@code
     * UnsignedMantissa} property.
     * @return A quiet not-a-number.
     */
    public static EDecimal CreateNaN(EInteger diag) {
      return CreateNaN(diag, false, false, null);
    }

    /**
     * Creates a not-a-number arbitrary-precision decimal number.
     * @param diag An integer, 0 or greater, to use as diagnostic information
     * associated with this object. If none is needed, should be zero. To
     * get the diagnostic information from another arbitrary-precision
     * decimal floating-point number, use that object's {@code
     * UnsignedMantissa} property.
     * @param signaling Whether the return value will be signaling (true) or quiet
     * (false).
     * @param negative Whether the return value is negative.
     * @param ctx An arithmetic context to control the precision (in decimal
     * digits) of the diagnostic information. The rounding and exponent
     * range of this context will be ignored. Can be null. The only flag
     * that can be signaled in this context is FlagInvalid, which happens
     * if diagnostic information needs to be truncated and too much memory
     * is required to do so.
     * @return An arbitrary-precision decimal number.
     * @throws NullPointerException The parameter {@code diag} is null or is less
     * than 0.
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

    /**
     * Creates an arbitrary-precision decimal number from a 64-bit binary
     * floating-point number. This method computes the exact value of the
     * floating point number, not an approximation, as is often the case by
     * converting the floating point number to a string first. Remember,
     * though, that the exact value of a 64-bit binary floating-point
     * number is not always the value that results when passing a literal
     * decimal number (for example, calling <code>EDecimal.FromDouble(0.1)</code>
     *), since not all decimal numbers can be converted to exact binary
     * numbers (in the example given, the resulting arbitrary-precision
     *  decimal will be the value of the closest "double" to 0.1, not 0.1
     * exactly). To create an arbitrary-precision decimal number from a
     * decimal value, use FromString instead in most cases (for example:
     *  <code>EDecimal.FromString("0.1")</code>). <p>The input value can be a
     * not-a-number (NaN) value (such as <code>Double.NaN</code>); however, NaN
     * values have multiple forms that are equivalent for many
     * applications' purposes, and <code>Double.NaN</code> is only one of these
     * equivalent forms. In fact, <code>EDecimal.FromDouble(Double.NaN)</code>
     * could produce an object that is represented differently between
     * DotNet and Java, because <code>Double.NaN</code> may have a different form
     * in DotNet and Java (for example, the NaN value's sign may be
     * negative in DotNet, but positive in Java). Use `IsNaN()` to
     * determine whether an object from this class stores a NaN value of
     * any form.</p>
     * @param dbl The parameter {@code dbl} is a 64-bit floating-point number.
     * @return An arbitrary-precision decimal number with the same value as {@code
     * dbl}.
     */
    public static EDecimal FromDouble(double dbl) {
      long lvalue = Double.doubleToRawLongBits(dbl);
      return FromDoubleBits(lvalue);
    }

    /**
     * Creates an arbitrary-precision decimal number from a 64-bit binary
     * floating-point number, encoded in the IEEE 754 binary64 format. This
     * method computes the exact value of the floating point number, not an
     * approximation, as is often the case by converting the floating point
     * number to a string first. Remember, though, that the exact value of
     * a 64-bit binary floating-point number is not always the value that
     * results when passing a literal decimal number, since not all decimal
     * numbers can be converted to exact binary numbers (in the example
     * given, the resulting arbitrary-precision decimal will be the value
     *  of the closest "double" to 0.1, not 0.1 exactly). To create an
     * arbitrary-precision decimal number from a decimal value, use
     * FromString instead in most cases.
     * @param dblBits The parameter {@code dblBits} is a 64-bit signed integer.
     * @return An arbitrary-precision decimal number with the same value as {@code
     * dblBits}.
     */
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

    /**
     * Converts an arbitrary-precision integer to an arbitrary precision decimal.
     * @param bigint An arbitrary-precision integer.
     * @return An arbitrary-precision decimal number with the exponent set to 0.
     */
    public static EDecimal FromEInteger(EInteger bigint) {
      return EDecimal.Create(bigint, EInteger.FromInt32(0));
    }

    /**
     * Converts an arbitrary-precision binary floating-point number to an arbitrary
     * precision decimal.
     * @param ef An arbitrary-precision binary floating-point number.
     * @return An arbitrary-precision decimal number.
     * @deprecated Renamed to FromEFloat.
 */
@Deprecated
    public static EDecimal FromExtendedFloat(EFloat ef) {
      return FromEFloat(ef);
    }

    /**
     * Creates an arbitrary-precision decimal number from an arbitrary-precision
     * binary floating-point number.
     * @param bigfloat An arbitrary-precision binary floating-point number.
     * @return An arbitrary-precision decimal number.
     * @throws NullPointerException The parameter {@code bigfloat} is null.
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

    /**
     * Converts a boolean value (true or false) to an arbitrary-precision decimal
     * number.
     * @param boolValue Either true or false.
     * @return The number 1 if {@code boolValue} is true; otherwise, 0.
     */
    public static EDecimal FromBoolean(boolean boolValue) {
      return boolValue ? EDecimal.One : EDecimal.Zero;
    }

    /**
     * Creates an arbitrary-precision decimal number from a 32-bit signed integer.
     * @param valueSmaller The parameter {@code valueSmaller} is a 32-bit signed
     * integer.
     * @return An arbitrary-precision decimal number with the exponent set to 0.
     */
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

    /**
     * Converts an unsigned integer expressed as a 64-bit signed integer to an
     * arbitrary-precision decimal number.
     * @param longerValue A 64-bit signed integer. If this value is 0 or greater,
     * the return value will represent it. If this value is less than 0,
     * the return value will store 2^64 plus this value instead.
     * @return An arbitrary-precision decimal number with the exponent set to 0. If
     * {@code longerValue} is 0 or greater, the return value will represent
     * it. If {@code longerValue} is less than 0, the return value will
     * store 2^64 plus this value instead.
     */
    public static EDecimal FromInt64AsUnsigned(long longerValue) {
      return longerValue >= 0 ? FromInt64(longerValue) :
        FromEInteger(EInteger.FromInt64AsUnsigned(longerValue));
    }

    /**
     * Creates an arbitrary-precision decimal number from a 64-bit signed integer.
     * @param valueSmall The parameter {@code valueSmall} is a 64-bit signed
     * integer.
     * @return This number's value as an arbitrary-precision decimal number with
     * the exponent set to 0.
     */
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

    /**
     * Creates an arbitrary-precision decimal number from a 32-bit binary
     * floating-point number. This method computes the exact value of the
     * floating point number, not an approximation, as is often the case by
     * converting the floating point number to a string first. Remember,
     * though, that the exact value of a 32-bit binary floating-point
     * number is not always the value that results when passing a literal
     * decimal number (for example, calling
     * <code>EDecimal.FromSingle(0.1f)</code>), since not all decimal numbers
     * can be converted to exact binary numbers (in the example given, the
     * resulting arbitrary-precision decimal will be the the value of the
     *  closest "float" to 0.1, not 0.1 exactly). To create an
     * arbitrary-precision decimal number from a decimal value, use
     * FromString instead in most cases (for example:
     *  <code>EDecimal.FromString("0.1")</code>). <p>The input value can be a
     * not-a-number (NaN) value (such as <code>Float.NaN</code> in DotNet or
     * Float.NaN in Java); however, NaN values have multiple forms that are
     * equivalent for many applications' purposes, and <code>Float.NaN</code> /
     * <code>Float.NaN</code> is only one of these equivalent forms. In fact,
     * <code>EDecimal.FromSingle(Float.NaN)</code> or
     * <code>EDecimal.FromSingle(Float.NaN)</code> could produce an object that
     * is represented differently between DotNet and Java, because
     * <code>Float.NaN</code> / <code>Float.NaN</code> may have a different form in
     * DotNet and Java (for example, the NaN value's sign may be negative
     * in DotNet, but positive in Java). Use `IsNaN()` to determine whether
     * an object from this class stores a NaN value of any form.</p>
     * @param flt The parameter {@code flt} is a 32-bit binary floating-point
     * number.
     * @return An arbitrary-precision decimal number with the same value as {@code
     * flt}.
     */
    public static EDecimal FromSingle(float flt) {
      int value = Float.floatToRawIntBits(flt);
      return FromSingleBits(value);
    }

    /**
     * Creates an arbitrary-precision decimal number from a 32-bit binary
     * floating-point number encoded in the IEEE 754 binary32 format. This
     * method computes the exact value of the floating point number, not an
     * approximation, as is often the case by converting the floating point
     * number to a string first. Remember, though, that the exact value of
     * a 32-bit binary floating-point number is not always the value that
     * results when passing a literal decimal number, since not all decimal
     * numbers can be converted to exact binary numbers (in the example
     * given, the resulting arbitrary-precision decimal will be the the
     *  value of the closest "float" to 0.1, not 0.1 exactly). To create an
     * arbitrary-precision decimal number from a decimal value, use
     * FromString instead in most cases.
     * @param value A 32-bit binary floating-point number encoded in the IEEE 754
     * binary32 format.
     * @return An arbitrary-precision decimal number with the same value as {@code
     * value}.
     */
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

    /**
     * Creates a decimal floating-point number from a binary floating-point number
     * encoded in the IEEE 754 binary16 format (also known as a
     *  "half-precision" floating-point number). This method computes the
     * exact value of the floating point number, not an approximation, as
     * is often the case by converting the floating point number to a
     * string first.
     * @param value A binary floating-point number encoded in the IEEE 754 binary16
     * format.
     * @return A decimal floating-point number with the same floating-point value
     * as {@code value}.
     */
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

    /**
     * Creates an arbitrary-precision decimal number from a sequence of <code>char</code>
     * s that represents a number. See <code>FromString(string, int, int,
     * EContext)</code> for more information. Note that calling the overload
     * that takes an EContext is often much faster than creating the
     * EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
     * especially if the context specifies a precision limit and exponent
     * range.
     * @param chars A sequence that represents a number.
     * @return An arbitrary-precision decimal number with the same value as the
     * given sequence of {@code char} s.
     * @throws NumberFormatException The parameter {@code chars} is not a correctly
     * formatted number sequence.
     */
    public static EDecimal FromString(char[] chars) {
      return FromString(chars, 0, chars == null ? 0 : chars.length, null);
    }

    /**
     * Creates an arbitrary-precision decimal number from a sequence of <code>char</code>
     * s that represents a number. See <code>FromString(string, int, int,
     * EContext)</code> for more information.
     * @param chars A sequence of {@code char} s that represents a number.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * Note that providing a context is often much faster than creating the
     * EDecimal without a context then calling {@code RoundToPrecision} on
     * that EDecimal, especially if the context specifies a precision limit
     * and exponent range.
     * @return An arbitrary-precision decimal number with the same value as the
     * given sequence of {@code char} s.
     * @throws NullPointerException The parameter {@code chars} is null.
     */
    public static EDecimal FromString(char[] chars, EContext ctx) {
      return FromString(chars, 0, chars == null ? 0 : chars.length, ctx);
    }

    /**
     * Creates an arbitrary-precision decimal number from a sequence of <code>char</code>
     * s that represents a number. See <code>FromString(string, int, int,
     * EContext)</code> for more information. Note that calling the overload
     * that takes an EContext is often much faster than creating the
     * EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
     * especially if the context specifies a precision limit and exponent
     * range.
     * @param chars A sequence that represents a number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code chars} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * chars} (but not more than {@code chars} 's length).
     * @return An arbitrary-precision decimal number with the same value as the
     * given sequence of {@code char} s.
     * @throws NumberFormatException The parameter {@code chars} is not a correctly
     * formatted number sequence.
     * @throws NullPointerException The parameter {@code chars} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code chars} 's length, or {@code chars} 's
     * length minus {@code offset} is less than {@code length}.
     */
    public static EDecimal FromString(
      char[] chars,
      int offset,
      int length) {
      return FromString(chars, offset, length, null);
    }

    /**
     * <p>Creates an arbitrary-precision decimal number from a sequence of
     * <code>char</code> s that represents a number.</p> <p>The format of the
     * sequence generally consists of:</p> <ul> <li>An optional plus sign
     *  ("+" , U+002B) or minus sign ("-", U+002D) (if the minus sign, the
     * value is negative.)</li> <li>One or more digits, with a single
     *  optional decimal point (".", U+002E) before or after those digits or
     * between two of them. These digits may begin with any number of
     *  zeros.</li> <li>Optionally, "E"/"e" followed by an optional
     *  (positive exponent) or "-" (negative exponent) and followed by one
     * or more digits specifying the exponent (these digits may begin with
     *  any number of zeros).</li></ul> <p>The sequence can also be "-INF",
     *  "-Infinity", "Infinity", "INF", quiet NaN ("NaN" /"-NaN") followed
     * by any number of digits (these digits may begin with any number of
     *  zeros), or signaling NaN ("sNaN" /"-sNaN") followed by any number of
     * digits (these digits may begin with any number of zeros), all where
     * the letters can be any combination of basic upper-case and/or basic
     * lower-case letters.</p> <p>All characters mentioned above are the
     * corresponding characters in the Basic Latin range. In particular,
     * the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
     * sequence is not allowed to contain white space characters, including
     * spaces.</p>
     * @param chars A sequence of {@code char} s, a portion of which represents a
     * number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code chars} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * chars} (but not more than {@code chars} 's length).
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * Note that providing a context is often much faster than creating the
     * EDecimal without a context then calling {@code RoundToPrecision} on
     * that EDecimal, especially if the context specifies a precision limit
     * and exponent range.
     * @return An arbitrary-precision decimal number with the same value as the
     * given sequence of {@code char} s.
     * @throws NullPointerException The parameter {@code chars} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code chars} 's length, or {@code chars} 's
     * length minus {@code offset} is less than {@code length}.
     */
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

    /**
     * Creates an arbitrary-precision decimal number from a sequence of bytes
     * (interpreted as text) that represents a number. See
     * <code>FromString(string, int, int, EContext)</code> for more information.
     * Note that calling the overload that takes an EContext is often much
     * faster than creating the EDecimal then calling
     * <code>RoundToPrecision</code> on that EDecimal, especially if the context
     * specifies a precision limit and exponent range.
     * @param bytes A sequence that represents a number.
     * @return An arbitrary-precision decimal number with the same value as the
     * given sequence of bytes (interpreted as text).
     * @throws NumberFormatException The parameter {@code bytes} is not a correctly
     * formatted number sequence.
     */
    public static EDecimal FromString(byte[] bytes) {
      return FromString(bytes, 0, bytes == null ? 0 : bytes.length, null);
    }

    /**
     * Creates an arbitrary-precision decimal number from a sequence of bytes
     * (interpreted as text) that represents a number. See
     * <code>FromString(string, int, int, EContext)</code> for more information.
     * @param bytes A sequence of bytes (interpreted as text) that represents a
     * number.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * Note that providing a context is often much faster than creating the
     * EDecimal without a context then calling {@code RoundToPrecision} on
     * that EDecimal, especially if the context specifies a precision limit
     * and exponent range.
     * @return An arbitrary-precision decimal number with the same value as the
     * given sequence of bytes (interpreted as text).
     * @throws NullPointerException The parameter {@code bytes} is null.
     */
    public static EDecimal FromString(byte[] bytes, EContext ctx) {
      return FromString(bytes, 0, bytes == null ? 0 : bytes.length, ctx);
    }

    /**
     * Creates an arbitrary-precision decimal number from a sequence of bytes
     * (interpreted as text) that represents a number. See
     * <code>FromString(string, int, int, EContext)</code> for more information.
     * Note that calling the overload that takes an EContext is often much
     * faster than creating the EDecimal then calling
     * <code>RoundToPrecision</code> on that EDecimal, especially if the context
     * specifies a precision limit and exponent range.
     * @param bytes A sequence that represents a number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code bytes} begins.
     * @param length The length, in bytes, of the desired portion of {@code bytes}
     * (but not more than {@code bytes} 's length).
     * @return An arbitrary-precision decimal number with the same value as the
     * given sequence of bytes (interpreted as text).
     * @throws NumberFormatException The parameter {@code bytes} is not a correctly
     * formatted number sequence.
     * @throws NullPointerException The parameter {@code bytes} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code bytes} 's length, or {@code bytes} 's
     * length minus {@code offset} is less than {@code length}.
     */
    public static EDecimal FromString(
      byte[] bytes,
      int offset,
      int length) {
      return FromString(bytes, offset, length, null);
    }

    /**
     * <p>Creates an arbitrary-precision decimal number from a sequence of bytes
     * (interpreted as text) that represents a number. Each byte in the
     * sequence has to be a code point in the Basic Latin range (0x00 to
     * 0x7f or U+0000 to U+007F) of the Unicode Standard.</p> <p>The format
     * of the sequence generally consists of:</p> <ul> <li>An optional plus
     *  sign ("+" , U+002B) or minus sign ("-", U+002D) (if the minus sign,
     * the value is negative.)</li> <li>One or more digits, with a single
     *  optional decimal point (".", U+002E) before or after those digits or
     * between two of them. These digits may begin with any number of
     *  zeros.</li> <li>Optionally, "E"/"e" followed by an optional
     *  (positive exponent) or "-" (negative exponent) and followed by one
     * or more digits specifying the exponent (these digits may begin with
     *  any number of zeros).</li></ul> <p>The sequence can also be "-INF",
     *  "-Infinity", "Infinity", "INF", quiet NaN ("NaN" /"-NaN") followed
     * by any number of digits (these digits may begin with any number of
     *  zeros), or signaling NaN ("sNaN" /"-sNaN") followed by any number of
     * digits (these digits may begin with any number of zeros), all where
     * the letters can be any combination of basic upper-case and/or basic
     * lower-case letters.</p> <p>All characters mentioned above are the
     * corresponding characters in the Basic Latin range. In particular,
     * the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
     * sequence is not allowed to contain white space characters, including
     * spaces.</p>
     * @param bytes A sequence of bytes (interpreted as text), a portion of which
     * represents a number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code bytes} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * bytes} (but not more than {@code bytes} 's length).
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * Note that providing a context is often much faster than creating the
     * EDecimal without a context then calling {@code RoundToPrecision} on
     * that EDecimal, especially if the context specifies a precision limit
     * and exponent range.
     * @return An arbitrary-precision decimal number with the same value as the
     * given sequence of bytes (interpreted as text).
     * @throws NullPointerException The parameter {@code bytes} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code bytes} 's length, or {@code bytes} 's
     * length minus {@code offset} is less than {@code length}.
     */
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

    /**
     * Creates an arbitrary-precision decimal number from a text string that
     * represents a number. See <code>FromString(string, int, int,
     * EContext)</code> for more information. Note that calling the overload
     * that takes an EContext is often much faster than creating the
     * EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
     * especially if the context specifies a precision limit and exponent
     * range.
     * @param str A string that represents a number.
     * @return An arbitrary-precision decimal number with the same value as the
     * given string.
     * @throws NumberFormatException The parameter {@code str} is not a correctly
     * formatted number string.
     */
    public static EDecimal FromString(String str) {
      return FromString(str, 0, str == null ? 0 : str.length(), null);
    }

    /**
     * Creates an arbitrary-precision decimal number from a text string that
     * represents a number. See <code>FromString(string, int, int,
     * EContext)</code> for more information.
     * @param str A string that represents a number.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * Note that providing a context is often much faster than creating the
     * EDecimal without a context then calling {@code RoundToPrecision} on
     * that EDecimal, especially if the context specifies a precision limit
     * and exponent range.
     * @return An arbitrary-precision decimal number with the same value as the
     * given string.
     * @throws NullPointerException The parameter {@code str} is null.
     */
    public static EDecimal FromString(String str, EContext ctx) {
      return FromString(str, 0, str == null ? 0 : str.length(), ctx);
    }

    /**
     * Creates an arbitrary-precision decimal number from a text string that
     * represents a number. See <code>FromString(string, int, int,
     * EContext)</code> for more information. Note that calling the overload
     * that takes an EContext is often much faster than creating the
     * EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
     * especially if the context specifies a precision limit and exponent
     * range.
     * @param str A string that represents a number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @return An arbitrary-precision decimal number with the same value as the
     * given string.
     * @throws NumberFormatException The parameter {@code str} is not a correctly
     * formatted number string.
     * @throws NullPointerException The parameter {@code str} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code str} 's length, or {@code str} 's
     * length minus {@code offset} is less than {@code length}.
     */
    public static EDecimal FromString(
      String str,
      int offset,
      int length) {
      return FromString(str, offset, length, null);
    }

    /**
     * <p>Creates an arbitrary-precision decimal number from a text string that
     * represents a number.</p> <p>The format of the string generally
     *  consists of:</p> <ul> <li>An optional plus sign ("+" , U+002B) or
     *  minus sign ("-", U+002D) (if the minus sign, the value is
     * negative.)</li> <li>One or more digits, with a single optional
     *  decimal point (".", U+002E) before or after those digits or between
     * two of them. These digits may begin with any number of zeros.</li>
     *  <li>Optionally, "E"/"e" followed by an optional (positive exponent)
     *  or "-" (negative exponent) and followed by one or more digits
     * specifying the exponent (these digits may begin with any number of
     *  zeros).</li></ul> <p>The string can also be "-INF", "-Infinity",
     *  "Infinity", "INF", quiet NaN ("NaN" /"-NaN") followed by any number
     * of digits (these digits may begin with any number of zeros), or
     *  signaling NaN ("sNaN" /"-sNaN") followed by any number of digits
     * (these digits may begin with any number of zeros), all where the
     * letters can be any combination of basic upper-case and/or basic
     * lower-case letters.</p> <p>All characters mentioned above are the
     * corresponding characters in the Basic Latin range. In particular,
     * the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
     * string is not allowed to contain white space characters, including
     * spaces.</p>
     * @param str A text string, a portion of which represents a number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * Note that providing a context is often much faster than creating the
     * EDecimal without a context then calling {@code RoundToPrecision} on
     * that EDecimal, especially if the context specifies a precision limit
     * and exponent range.
     * @return An arbitrary-precision decimal number with the same value as the
     * given string.
     * @throws NullPointerException The parameter {@code str} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code str} 's length, or {@code str} 's
     * length minus {@code offset} is less than {@code length}.
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

    /**
     * Gets the greater value between two decimal numbers.
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

    /**
     * Gets the greater value between two decimal numbers.
     * @param first An arbitrary-precision decimal number.
     * @param second Another arbitrary-precision decimal number.
     * @return The larger value of the two numbers. If one is positive zero and the
     * other is negative zero, returns the positive zero. If the two
     * numbers are positive and have the same value, returns the one with
     * the larger exponent. If the two numbers are negative and have the
     * same value, returns the one with the smaller exponent.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
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

    /**
     * Gets the greater value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Max.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return The larger value of the two numbers, ignoring their signs.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
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

    /**
     * Gets the lesser value between two decimal numbers.
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

    /**
     * Gets the lesser value between two decimal numbers.
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

    /**
     * Gets the lesser value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Min.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return The smaller value of the two numbers, ignoring their signs.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
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
    public static EDecimal PI(EContext ctx) {
      return GetMathValue(ctx).Pi(ctx);
    }

    /**
     * Finds the absolute value of this object (if it's negative, it becomes
     * positive).
     * @return An arbitrary-precision decimal number. Returns signaling NaN if this
     * value is signaling NaN. (In this sense, this method is similar to
     *  the "copy-abs" operation in the General Decimal Arithmetic
     * Specification, except this method does not necessarily return a copy
     * of this object.).
     */
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

    /**
     * Returns a number with the same value as this one, but copying the sign
     * (positive or negative) of another number. (This method is similar to
     *  the "copy-sign" operation in the General Decimal Arithmetic
     * Specification, except this method does not necessarily return a copy
     * of this object.).
     * @param other A number whose sign will be copied.
     * @return An arbitrary-precision decimal number.
     * @throws NullPointerException The parameter {@code other} is null.
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
    public EDecimal Abs(EContext context) {
      return ((context == null || context == EContext.UnlimitedHalfEven) ?
          ExtendedMathValue : MathValue).Abs(this, context);
    }

    /**
     * Adds this arbitrary-precision decimal floating-point number and another
     * arbitrary-precision decimal floating-point number and returns the
     * result. The exponent for the result is the lower of this
     * arbitrary-precision decimal floating-point number's exponent and the
     * other arbitrary-precision decimal floating-point number's exponent.
     * @param otherValue An arbitrary-precision decimal number.
     * @return The sum of the two numbers, that is, this arbitrary-precision
     * decimal floating-point number plus another arbitrary-precision
     * decimal floating-point number. If this arbitrary-precision decimal
     * floating-point number is not-a-number (NaN), returns NaN.
     */
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

    /**
     * Adds this arbitrary-precision decimal floating-point number and another
     * arbitrary-precision decimal floating-point number and returns the
     * result.
     * @param otherValue The number to add to.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The sum of the two numbers, that is, this arbitrary-precision
     * decimal floating-point number plus another arbitrary-precision
     * decimal floating-point number. If this arbitrary-precision decimal
     * floating-point number is not-a-number (NaN), returns NaN.
     */
    public EDecimal Add(
      EDecimal otherValue,
      EContext ctx) {
      return GetMathValue(ctx).Add(this, otherValue, ctx);
    }

    /**
     * Compares the mathematical values of this object and another object,
     * accepting NaN values. This method currently uses the rules given in
     * the CompareToValue method, so that it it is not consistent with the
     * Equals method, but it may change in a future version to use the
     * rules for the CompareToTotal method instead.
     * @param other An arbitrary-precision decimal number.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other
     * value or if {@code other} is null, or 0 if both values are equal.
     * This implementation returns a positive number if.
     */
    public int compareTo(EDecimal other) {
      return this.CompareToValue(other);
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
      return this.CompareToValue(EDecimal.FromInt32(intOther));
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
     * @param intOther The parameter {@code intOther} is a 32-bit signed integer.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other
     * value, or 0 if both values are equal.
     */
    public int CompareToValue(int intOther) {
      return this.CompareToValue(EDecimal.FromInt32(intOther));
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
      return this.CompareToValue(EDecimal.FromInt64(intOther));
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
      return this.CompareToValue(EDecimal.FromInt64(intOther));
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
     * @param other An arbitrary-precision decimal number.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other
     * value or if {@code other} is null, or 0 if both values are equal.
     * This implementation returns a positive number if.
     */
    public int CompareToValue(EDecimal other) {
      return ExtendedMathValue.compareTo(this, other);
    }

    /**
     * Compares an arbitrary-precision binary floating-point number with this
     * instance.
     * @param other The other object to compare. Can be null.
     * @return Zero if the values are equal; a negative number if this instance is
     * less; or a positive number if this instance is greater. Returns 0 if
     * both values are NaN (even signaling NaN) and 1 if this value is NaN
     * (even signaling NaN) and the other isn't, or if the other value is
     * null. This implementation returns a positive number if.
     */
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

    /**
     * Compares the mathematical values of this object and another object, treating
     * quiet NaN as signaling. <p>In this method, negative zero and
     * positive zero are considered equal.</p> <p>If this object or the
     * other object is a quiet NaN or signaling NaN, this method will
     * return a quiet NaN and will signal a FlagInvalid flag.</p>
     * @param other An arbitrary-precision decimal number.
     * @param ctx An arithmetic context. The precision, rounding, and exponent
     * range are ignored. If {@code HasFlags} of the context is true, will
     * store the flags resulting from the operation (the flags are in
     * addition to the pre-existing flags). Can be null.
     * @return Quiet NaN if this object or the other object is NaN, or 0 if both
     * objects have the same value, or -1 if this object is less than the
     * other value, or a 1 if this object is greater. This implementation
     * returns a positive number if.
     */
    public EDecimal CompareToSignal(
      EDecimal other,
      EContext ctx) {
      return GetMathValue(ctx).CompareToWithContext(this, other, true, ctx);
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
     * @param other An arbitrary-precision decimal number to compare with this one.
     * @return The number 0 if both objects have the same value (ignoring their
     * signs), or -1 if this object is less than the other value (ignoring
     * their signs), or 1 if this object is greater (ignoring their signs).
     * This implementation returns a positive number if.
     */
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
     * @param other An arbitrary-precision decimal number to compare with this one.
     * @param ctx An arithmetic context. Flags will be set in this context only if
     * {@code HasFlags} and {@code IsSimplified} of the context are true
     * and only if an operand needed to be rounded before carrying out the
     * operation. Can be null.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater.
     * Does not signal flags if either value is signaling NaN. This
     * implementation returns a positive number if.
     */
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
     * @param other An arbitrary-precision decimal number to compare with this one.
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
     * @param other An arbitrary-precision decimal number to compare with this one.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater.
     * This implementation returns a positive number if.
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
     * Compares the mathematical values of this object and another object. <p>In
     * this method, negative zero and positive zero are considered
     * equal.</p> <p>If this object or the other object is a quiet NaN or
     * signaling NaN, this method returns a quiet NaN, and will signal a
     * FlagInvalid flag if either is a signaling NaN.</p>
     * @param other An arbitrary-precision decimal number.
     * @param ctx An arithmetic context. The precision, rounding, and exponent
     * range are ignored. If {@code HasFlags} of the context is true, will
     * store the flags resulting from the operation (the flags are in
     * addition to the pre-existing flags). Can be null.
     * @return Quiet NaN if this object or the other object is NaN, or 0 if both
     * objects have the same value, or -1 if this object is less than the
     * other value, or 1 if this object is greater. This implementation
     * returns a positive number if.
     */
    public EDecimal CompareToWithContext(
      EDecimal other,
      EContext ctx) {
      return GetMathValue(ctx).CompareToWithContext(this, other, false, ctx);
    }

    /**
     * Divides this arbitrary-precision decimal floating-point number by another
     * arbitrary-precision decimal floating-point number and returns the
     * result; returns NaN instead if the result would have a
     * nonterminating decimal expansion (including 1/3, 1/12, 1/7, 2/3, and
     * so on); if this is not desired, use DivideToExponent, or use the
     * Divide overload that takes an EContext.
     * @param divisor The number to divide by.
     * @return The result of dividing this arbitrary-precision decimal
     * floating-point number by another arbitrary-precision decimal
     * floating-point number. Returns infinity if the divisor (this
     * arbitrary-precision decimal floating-point number) is 0 and the
     * dividend (the other arbitrary-precision decimal floating-point
     * number) is nonzero. Returns not-a-number (NaN) if the divisor and
     * the dividend are 0. Returns NaN if the result can't be exact because
     * it would have a nonterminating binary expansion (examples include 1
     * divided by any multiple of 3, such as 1/3 or 1/12). If this is not
     * desired, use DivideToExponent instead, or use the Divide overload
     * that takes an {@code EContext} (such as {@code EContext.Decimal128}
     *) instead.
     */
    public EDecimal Divide(EDecimal divisor) {
      return this.Divide(
          divisor,
          EContext.ForRounding(ERounding.None));
    }

    /**
     * Divides this arbitrary-precision decimal floating-point number by another
     * arbitrary-precision decimal floating-point number and returns the
     * result.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The result of dividing this arbitrary-precision decimal
     * floating-point number by another arbitrary-precision decimal
     * floating-point number. Signals FlagDivideByZero and returns infinity
     * if the divisor (this arbitrary-precision decimal floating-point
     * number) is 0 and the dividend (the other arbitrary-precision decimal
     * floating-point number) is nonzero. Signals FlagInvalid and returns
     * not-a-number (NaN) if the divisor and the dividend are 0; or, either
     * {@code ctx} is null or {@code ctx} 's precision is 0, and the result
     * would have a nonterminating decimal expansion (examples include 1
     * divided by any multiple of 3, such as 1/3 or 1/12); or, the rounding
     * mode is ERounding.None and the result is not exact.
     */
    public EDecimal Divide(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).Divide(this, divisor, ctx);
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
    public EDecimal[] DivideAndRemainderNaturalScale(EDecimal
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
    public EDecimal[] DivideAndRemainderNaturalScale(
      EDecimal divisor,
      EContext ctx) {
      return this.DivRemNaturalScale(divisor, ctx);
    }

    /**
     * Divides this arbitrary-precision decimal floating-point number by another
     * arbitrary-precision decimal floating-point number and returns a
     * two-item array containing the result of the division and the
     * remainder, in that order. The result of division is calculated as
     * though by <code>DivideToIntegerNaturalScale</code>, and the remainder is
     * calculated as though by <code>RemainderNaturalScale</code>.
     * @param divisor The number to divide by.
     * @return An array of two items: the first is the result of the division as an
     * arbitrary-precision decimal floating-point number, and the second is
     * the remainder as an arbitrary-precision decimal floating-point
     * number. The result of division is the result of the method on the
     * two operands, and the remainder is the result of the Remainder
     * method on the two operands.
     */
    public EDecimal[] DivRemNaturalScale(EDecimal
      divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

    /**
     * Divides this arbitrary-precision decimal floating-point number by another
     * arbitrary-precision decimal floating-point number and returns a
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
     * arbitrary-precision decimal floating-point number, and the second is
     * the remainder as an arbitrary-precision decimal floating-point
     * number. The result of division is the result of the method on the
     * two operands, and the remainder is the result of the Remainder
     * method on the two operands.
     */
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

    /**
     * Divides two arbitrary-precision decimal numbers, and gives a particular
     * exponent to the result.
     * @param divisor The number to divide by.
     * @param desiredExponentSmall The desired exponent. A negative number places
     * the cutoff point to the right of the usual decimal point (so a
     * negative number means the number of decimal places to round to). A
     * positive number places the cutoff point to the left of the usual
     * decimal point.
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
     * Divides two arbitrary-precision decimal numbers, and gives a particular
     * exponent (expressed as a 32-bit signed integer) to the result, using
     * the half-even rounding mode.
     * @param divisor The number to divide by.
     * @param desiredExponentInt The desired exponent. A negative number places the
     * cutoff point to the right of the usual decimal point (so a negative
     * number means the number of decimal places to round to). A positive
     * number places the cutoff point to the left of the usual decimal
     * point.
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
     * Divides two arbitrary-precision decimal numbers, and gives a particular
     * exponent to the result.
     * @param divisor The number to divide by.
     * @param desiredExponentSmall The desired exponent. A negative number places
     * the cutoff point to the right of the usual decimal point (so a
     * negative number means the number of decimal places to round to). A
     * positive number places the cutoff point to the left of the usual
     * decimal point.
     * @param rounding The rounding mode to use if the result must be scaled down
     * to have the same exponent as this value.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor
     * and the dividend are 0. Signals FlagInvalid and returns not-a-number
     * (NaN) if the rounding mode is ERounding.None and the result is not
     * exact.
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
     * Divides two arbitrary-precision decimal numbers, and gives a particular
     * exponent (expressed as a 32-bit signed integer) to the result, using
     * the half-even rounding mode.
     * @param divisor The number to divide by.
     * @param desiredExponentInt The desired exponent. A negative number places the
     * cutoff point to the right of the usual decimal point (so a negative
     * number means the number of decimal places to round to). A positive
     * number places the cutoff point to the left of the usual decimal
     * point.
     * @param rounding The rounding mode to use if the result must be scaled down
     * to have the same exponent as this value.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor
     * and the dividend are 0. Signals FlagInvalid and returns not-a-number
     * (NaN) if the rounding mode is ERounding.None and the result is not
     * exact.
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
     * Divides two arbitrary-precision decimal numbers, and gives a particular
     * exponent to the result.
     * @param divisor The number to divide by.
     * @param exponent The desired exponent. A negative number places the cutoff
     * point to the right of the usual decimal point (so a negative number
     * means the number of decimal places to round to). A positive number
     * places the cutoff point to the left of the usual decimal point.
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
    public EDecimal DivideToExponent(
      EDecimal divisor,
      EInteger exponent,
      EContext ctx) {
      return GetMathValue(ctx).DivideToExponent(this, divisor, exponent, ctx);
    }

    /**
     * Divides two arbitrary-precision decimal numbers, and gives a particular
     * exponent to the result, using the half-even rounding mode.
     * @param divisor The number to divide by.
     * @param exponent The desired exponent. A negative number places the cutoff
     * point to the right of the usual decimal point (so a negative number
     * means the number of decimal places to round to). A positive number
     * places the cutoff point to the left of the usual decimal point.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor
     * and the dividend are 0.
     */
    public EDecimal DivideToExponent(
      EDecimal divisor,
      EInteger exponent) {
      return this.DivideToExponent(divisor, exponent, ERounding.HalfEven);
    }

    /**
     * Divides two arbitrary-precision decimal numbers, and gives a particular
     * exponent (expressed as a 64-bit signed integer) to the result, using
     * the half-even rounding mode.
     * @param divisor The number to divide by.
     * @param desiredExponentSmall The desired exponent. A negative number places
     * the cutoff point to the right of the usual decimal point (so a
     * negative number means the number of decimal places to round to). A
     * positive number places the cutoff point to the left of the usual
     * decimal point.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor
     * and the dividend are 0.
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
     * Divides two arbitrary-precision decimal numbers, and gives a particular
     * exponent (expressed as a 32-bit signed integer) to the result, using
     * the half-even rounding mode.
     * @param divisor The number to divide by.
     * @param desiredExponentInt The desired exponent. A negative number places the
     * cutoff point to the right of the usual decimal point (so a negative
     * number means the number of decimal places to round to). A positive
     * number places the cutoff point to the left of the usual decimal
     * point.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor
     * and the dividend are 0.
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
     * Divides two arbitrary-precision decimal numbers, and gives a particular
     * exponent to the result.
     * @param divisor The number to divide by.
     * @param desiredExponent The desired exponent. A negative number places the
     * cutoff point to the right of the usual decimal point (so a negative
     * number means the number of decimal places to round to). A positive
     * number places the cutoff point to the left of the usual decimal
     * point.
     * @param rounding The rounding mode to use if the result must be scaled down
     * to have the same exponent as this value.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Returns not-a-number (NaN) if the divisor and the dividend are 0.
     * Returns NaN if the rounding mode is ERounding.None and the result is
     * not exact.
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
     * Divides two arbitrary-precision decimal numbers, and returns the integer
     * part of the result, rounded down, with the preferred exponent set to
     * this value's exponent minus the divisor's exponent.
     * @param divisor The number to divide by.
     * @return The integer part of the quotient of the two objects. Signals
     * FlagDivideByZero and returns infinity if the divisor is 0 and the
     * dividend is nonzero. Signals FlagInvalid and returns not-a-number
     * (NaN) if the divisor and the dividend are 0.
     */
    public EDecimal DivideToIntegerNaturalScale(EDecimal
      divisor) {
      return this.DivideToIntegerNaturalScale(
          divisor,
          EContext.ForRounding(ERounding.Down));
    }

    /**
     * Divides this object by another object, and returns the integer part of the
     * result (which is initially rounded down), with the preferred
     * exponent set to this value's exponent minus the divisor's exponent.
     * @param divisor The parameter {@code divisor} is an arbitrary-precision
     * decimal floating-point number.
     * @param ctx The parameter {@code ctx} is an EContext object.
     * @return The integer part of the quotient of the two objects. Signals
     * FlagInvalid and returns not-a-number (NaN) if the return value would
     * overflow the exponent range. Signals FlagDivideByZero and returns
     * infinity if the divisor is 0 and the dividend is nonzero. Signals
     * FlagInvalid and returns not-a-number (NaN) if the divisor and the
     * dividend are 0. Signals FlagInvalid and returns not-a-number (NaN)
     * if the rounding mode is ERounding.None and the result is not exact.
     */
    public EDecimal DivideToIntegerNaturalScale(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).DivideToIntegerNaturalScale(
          this,
          divisor,
          ctx);
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
    public EDecimal DivideToIntegerZeroScale(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).DivideToIntegerZeroScale(this, divisor, ctx);
    }

    /**
     * Divides this object by another decimal number and returns a result with the
     * same exponent as this object (the dividend).
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
    public EDecimal DivideToSameExponent(
      EDecimal divisor,
      ERounding rounding) {
      return this.DivideToExponent(
          divisor,
          this.exponent.ToEInteger(),
          EContext.ForRounding(rounding));
    }

    /**
     * Determines whether this object's significand, exponent, and properties are
     * equal to those of another object. Not-a-number values are considered
     * equal if the rest of their properties are equal.
     * @param other An arbitrary-precision decimal number.
     * @return {@code true} if this object's significand and exponent are equal to
     * those of another object; otherwise, {@code false}.
     */
    public boolean equals(EDecimal other) {
      return this.EqualsInternal(other);
    }

    /**
     * Determines whether this object's significand, exponent, and properties are
     * equal to those of another object and that other object is an
     * arbitrary-precision decimal number. Not-a-number values are
     * considered equal if the rest of their properties are equal.
     * @param obj The parameter {@code obj} is an arbitrary object.
     * @return {@code true} if the objects are equal; otherwise, {@code false}. In
     * this method, two objects are not equal if they don't have the same
     * type or if one is null and the other isn't.
     */
    @Override public boolean equals(Object obj) {
      return this.EqualsInternal(((obj instanceof EDecimal) ? (EDecimal)obj : null));
    }

    /**
     * Finds e (the base of natural logarithms) raised to the power of this
     * object's value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the exponential function's results are generally
     * not exact.</i> (Unlike in the General Decimal Arithmetic
     * Specification, any rounding mode is allowed.).
     * @return Exponential of this object. If this object's value is 1, returns an
     *  approximation to " e" within the given precision. Signals
     * FlagInvalid and returns not-a-number (NaN) if the parameter {@code
     * ctx} is null or the precision is unlimited (the context's Precision
     * property is 0).
     */
    public EDecimal Exp(EContext ctx) {
      return GetMathValue(ctx).Exp(this, ctx);
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

    /**
     * Calculates this object's hash code. No application or process IDs are used
     * in the hash code calculation.
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
            BigNumberFlags.FlagNegative)) == (BigNumberFlags.FlagInfinity |
          BigNumberFlags.FlagNegative);
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
     * exact.</i> (Unlike in the General Decimal Arithmetic Specification,
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
    public EDecimal Log(EContext ctx) {
      return GetMathValue(ctx).Ln(this, ctx);
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
     * exact.</i> (Unlike in the General Decimal Arithmetic Specification,
     * any rounding mode is allowed.).
     * @return Ln(this object)/Ln(10). Signals the flag FlagInvalid and returns
     * not-a-number (NaN) if this object is less than 0. Signals
     * FlagInvalid and returns not-a-number (NaN) if the parameter {@code
     * ctx} is null or the precision is unlimited (the context's Precision
     * property is 0).
     */
    public EDecimal Log10(EContext ctx) {
      return this.LogN(EDecimal.FromInt32(10), ctx);
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

    /**
     * Finds the base-N logarithm of this object, that is, the power (exponent)
     * that the number N must be raised to in order to equal this object's
     * value.
     * @param baseValue The parameter {@code baseValue} is a Numbers.EDecimal
     * object.
     * @param ctx The parameter {@code ctx} is a Numbers.EContext object.
     * @return Ln(this object)/Ln(baseValue). Signals the flag FlagInvalid and
     * returns not-a-number (NaN) if this object is less than 0. Signals
     * FlagInvalid and returns not-a-number (NaN) if the parameter {@code
     * ctx} is null or the precision is unlimited (the context's Precision
     * property is 0).
     * @throws NullPointerException The parameter {@code baseValue} is null.
     */
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

    /**
     * Returns a number similar to this number but with the decimal point moved to
     * the left.
     * @param places The number of decimal places to move the decimal point to the
     * left. If this number is negative, instead moves the decimal point to
     * the right by this number's absolute value.
     * @return A number whose exponent is decreased by {@code places}, but not to
     * more than 0.
     */
    public EDecimal MovePointLeft(int places) {
      return this.MovePointLeft(EInteger.FromInt32(places), null);
    }

    /**
     * Returns a number similar to this number but with the decimal point moved to
     * the left.
     * @param places The number of decimal places to move the decimal point to the
     * left. If this number is negative, instead moves the decimal point to
     * the right by this number's absolute value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is decreased by {@code places}, but not to
     * more than 0.
     */
    public EDecimal MovePointLeft(int places, EContext ctx) {
      return this.MovePointLeft(EInteger.FromInt32(places), ctx);
    }

    /**
     * Returns a number similar to this number but with the decimal point moved to
     * the left.
     * @param bigPlaces The number of decimal places to move the decimal point to
     * the left. If this number is negative, instead moves the decimal
     * point to the right by this number's absolute value.
     * @return A number whose exponent is decreased by {@code bigPlaces}, but not
     * to more than 0.
     */
    public EDecimal MovePointLeft(EInteger bigPlaces) {
      return this.MovePointLeft(bigPlaces, null);
    }

    /**
     * Returns a number similar to this number but with the decimal point moved to
     * the left.
     * @param bigPlaces The number of decimal places to move the decimal point to
     * the left. If this number is negative, instead moves the decimal
     * point to the right by this number's absolute value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is decreased by {@code bigPlaces}, but not
     * to more than 0.
     */
    public EDecimal MovePointLeft(
      EInteger bigPlaces,
      EContext ctx) {
      return (!this.isFinite()) ? this.RoundToPrecision(ctx) :
        this.MovePointRight((bigPlaces).Negate(), ctx);
    }

    /**
     * Returns a number similar to this number but with the decimal point moved to
     * the right.
     * @param places The number of decimal places to move the decimal point to the
     * right. If this number is negative, instead moves the decimal point
     * to the left by this number's absolute value.
     * @return A number whose exponent is increased by {@code places}, but not to
     * more than 0.
     */
    public EDecimal MovePointRight(int places) {
      return this.MovePointRight(EInteger.FromInt32(places), null);
    }

    /**
     * Returns a number similar to this number but with the decimal point moved to
     * the right.
     * @param places The number of decimal places to move the decimal point to the
     * right. If this number is negative, instead moves the decimal point
     * to the left by this number's absolute value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is increased by {@code places}, but not to
     * more than 0.
     */
    public EDecimal MovePointRight(int places, EContext ctx) {
      return this.MovePointRight(EInteger.FromInt32(places), ctx);
    }

    /**
     * Returns a number similar to this number but with the decimal point moved to
     * the right.
     * @param bigPlaces The number of decimal places to move the decimal point to
     * the right. If this number is negative, instead moves the decimal
     * point to the left by this number's absolute value.
     * @return A number whose exponent is increased by {@code bigPlaces}, but not
     * to more than 0.
     */
    public EDecimal MovePointRight(EInteger bigPlaces) {
      return this.MovePointRight(bigPlaces, null);
    }

    /**
     * Returns a number similar to this number but with the decimal point moved to
     * the right.
     * @param bigPlaces The number of decimal places to move the decimal point to
     * the right. If this number is negative, instead moves the decimal
     * point to the left by this number's absolute value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is increased by {@code bigPlaces}, but not
     * to more than 0.
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
     * Multiplies this arbitrary-precision decimal floating-point number by another
     * arbitrary-precision decimal floating-point number and returns the
     * result. The exponent for the result is this arbitrary-precision
     * decimal floating-point number's exponent plus the other
     * arbitrary-precision decimal floating-point number's exponent.
     * @param otherValue Another decimal number.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * decimal floating-point number times another arbitrary-precision
     * decimal floating-point number.
     * @throws NullPointerException The parameter {@code otherValue} is null.
     */
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

    /**
     * Multiplies this arbitrary-precision decimal floating-point number by another
     * arbitrary-precision decimal floating-point number and returns the
     * result.
     * @param op Another decimal number.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * decimal floating-point number times another arbitrary-precision
     * decimal floating-point number.
     */
    public EDecimal Multiply(EDecimal op, EContext ctx) {
      return GetMathValue(ctx).Multiply(this, op, ctx);
    }

    /**
     * Adds this arbitrary-precision decimal floating-point number and a 64-bit
     * signed integer and returns the result. The exponent for the result
     * is the lower of this arbitrary-precision decimal floating-point
     * number's exponent and the other 64-bit signed integer's exponent.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The sum of the two numbers, that is, this arbitrary-precision
     * decimal floating-point number plus a 64-bit signed integer. If this
     * arbitrary-precision decimal floating-point number is not-a-number
     * (NaN), returns NaN.
     */
    public EDecimal Add(long longValue) {
      return this.Add(EDecimal.FromInt64(longValue));
    }

    /**
     * Subtracts a 64-bit signed integer from this arbitrary-precision decimal
     * floating-point number and returns the result. The exponent for the
     * result is the lower of this arbitrary-precision decimal
     * floating-point number's exponent and the other 64-bit signed
     * integer's exponent.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision decimal floating-point number minus a 64-bit
     * signed integer. If this arbitrary-precision decimal floating-point
     * number is not-a-number (NaN), returns NaN.
     */
    public EDecimal Subtract(long longValue) {
      return this.Subtract(EDecimal.FromInt64(longValue));
    }

    /**
     * Multiplies this arbitrary-precision decimal floating-point number by a
     * 64-bit signed integer and returns the result. The exponent for the
     * result is this arbitrary-precision decimal floating-point number's
     * exponent plus the other 64-bit signed integer's exponent.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * decimal floating-point number times a 64-bit signed integer.
     */
    public EDecimal Multiply(long longValue) {
      return this.Multiply(EDecimal.FromInt64(longValue));
    }

    /**
     * Divides this arbitrary-precision decimal floating-point number by a 64-bit
     * signed integer and returns the result; returns NaN instead if the
     * result would have a nonterminating decimal expansion (including 1/3,
     * 1/12, 1/7, 2/3, and so on); if this is not desired, use
     * DivideToExponent, or use the Divide overload that takes an EContext.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The result of dividing this arbitrary-precision decimal
     * floating-point number by a 64-bit signed integer. Returns infinity
     * if the divisor (this arbitrary-precision decimal floating-point
     * number) is 0 and the dividend (the other 64-bit signed integer) is
     * nonzero. Returns not-a-number (NaN) if the divisor and the dividend
     * are 0. Returns NaN if the result can't be exact because it would
     * have a nonterminating binary expansion (examples include 1 divided
     * by any multiple of 3, such as 1/3 or 1/12). If this is not desired,
     * use DivideToExponent instead, or use the Divide overload that takes
     * an {@code EContext} (such as {@code EContext.Decimal128}) instead.
     */
    public EDecimal Divide(long longValue) {
      return this.Divide(EDecimal.FromInt64(longValue));
    }

    /**
     * Adds this arbitrary-precision decimal floating-point number and a 32-bit
     * signed integer and returns the result. The exponent for the result
     * is the lower of this arbitrary-precision decimal floating-point
     * number's exponent and the other 32-bit signed integer's exponent.
     * @param intValue A 32-bit signed integer to add to this object.
     * @return The sum of the two numbers, that is, this arbitrary-precision
     * decimal floating-point number plus a 32-bit signed integer. If this
     * arbitrary-precision decimal floating-point number is not-a-number
     * (NaN), returns NaN.
     */
    public EDecimal Add(int intValue) {
      return this.Add(EDecimal.FromInt32(intValue));
    }

    /**
     * Subtracts a 32-bit signed integer from this arbitrary-precision decimal
     * floating-point number and returns the result. The exponent for the
     * result is the lower of this arbitrary-precision decimal
     * floating-point number's exponent and the other 32-bit signed
     * integer's exponent.
     * @param intValue A 32-bit signed integer to subtract from this object.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision decimal floating-point number minus a 32-bit
     * signed integer. If this arbitrary-precision decimal floating-point
     * number is not-a-number (NaN), returns NaN.
     */
    public EDecimal Subtract(int intValue) {
      return (intValue == Integer.MIN_VALUE) ?
        this.Subtract(EDecimal.FromInt32(intValue)) : this.Add(-intValue);
    }

    /**
     * Multiplies this arbitrary-precision decimal floating-point number by a
     * 32-bit signed integer and returns the result. The exponent for the
     * result is this arbitrary-precision decimal floating-point number's
     * exponent plus the other 32-bit signed integer's exponent.
     * @param intValue A 32-bit signed integer to multiply this object by.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * decimal floating-point number times a 32-bit signed integer.
     */
    public EDecimal Multiply(int intValue) {
      return this.Multiply(EDecimal.FromInt32(intValue));
    }

    /**
     * Divides this arbitrary-precision decimal floating-point number by a 32-bit
     * signed integer and returns the result; returns NaN instead if the
     * result would have a nonterminating decimal expansion (including 1/3,
     * 1/12, 1/7, 2/3, and so on); if this is not desired, use
     * DivideToExponent, or use the Divide overload that takes an EContext.
     * @param intValue A 32-bit signed integer, the divisor, to divide this object
     * by.
     * @return The result of dividing this arbitrary-precision decimal
     * floating-point number by a 32-bit signed integer. Returns infinity
     * if the divisor (this arbitrary-precision decimal floating-point
     * number) is 0 and the dividend (the other 32-bit signed integer) is
     * nonzero. Returns not-a-number (NaN) if the divisor and the dividend
     * are 0. Returns NaN if the result can't be exact because it would
     * have a nonterminating binary expansion (examples include 1 divided
     * by any multiple of 3, such as 1/3 or 1/12). If this is not desired,
     * use DivideToExponent instead, or use the Divide overload that takes
     * an {@code EContext} (such as {@code EContext.Decimal128}) instead.
     */
    public EDecimal Divide(int intValue) {
      return this.Divide(EDecimal.FromInt32(intValue));
    }

    /**
     * Multiplies by one decimal number, and then adds another decimal number.
     * @param multiplicand The value to multiply.
     * @param augend The value to add.
     * @return An arbitrary-precision decimal floating-point number.
     */
    public EDecimal MultiplyAndAdd(
      EDecimal multiplicand,
      EDecimal augend) {
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
    public EDecimal MultiplyAndAdd(
      EDecimal op,
      EDecimal augend,
      EContext ctx) {
      return GetMathValue(ctx).MultiplyAndAdd(this, op, augend, ctx);
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
     * Gets an object with the same value as this one, but with the sign reversed.
     * @return An arbitrary-precision decimal number. If this value is positive
     * zero, returns negative zero. Returns signaling NaN if this value is
     * signaling NaN. (In this sense, this method is similar to the
     *  "copy-negate" operation in the General Decimal Arithmetic
     * Specification, except this method does not necessarily return a copy
     * of this object.).
     */
    public EDecimal Negate() {
      return new EDecimal(
          this.unsignedMantissa,
          this.exponent,
          (byte)(this.flags ^ BigNumberFlags.FlagNegative));
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but with the sign reversed.
     * @param context An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return An arbitrary-precision decimal number. If this value is positive
     * zero, returns positive zero. Signals FlagInvalid and returns quiet
     * NaN if this value is signaling NaN.
     */
    public EDecimal Negate(EContext context) {
      return ((context == null || context == EContext.UnlimitedHalfEven) ?
          ExtendedMathValue : MathValue).Negate(this, context);
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
    public EDecimal NextMinus(EContext ctx) {
      return GetMathValue(ctx).NextMinus(this, ctx);
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
    public EDecimal NextPlus(EContext ctx) {
      return GetMathValue(ctx).NextPlus(this, ctx);
    }

    /**
     * Finds the next value that is closer to the other object's value than this
     * object's value. Returns a copy of this value with the same sign as
     * the other value if both values are equal.
     * @param otherValue An arbitrary-precision decimal number that the return
     * value will approach.
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
    public EDecimal NextToward(
      EDecimal otherValue,
      EContext ctx) {
      return GetMathValue(ctx)
        .NextToward(this, otherValue, ctx);
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
    public EDecimal Plus(EContext ctx) {
      return GetMathValue(ctx).Plus(this, ctx);
    }

    /**
     * Raises this object's value to the given exponent.
     * @param exponent An arbitrary-precision decimal number expressing the
     * exponent to raise this object's value to.
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
    public EDecimal Pow(EDecimal exponent, EContext ctx) {
      return GetMathValue(ctx).Power(this, exponent, ctx);
    }

    /**
     * Raises this object's value to the given exponent, using unlimited precision.
     * @param exponent An arbitrary-precision decimal number expressing the
     * exponent to raise this object's value to.
     * @return This^exponent. Returns not-a-number (NaN) if the exponent has a
     * fractional part.
     */
    public EDecimal Pow(EDecimal exponent) {
      return this.Pow(exponent, null);
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
    public EDecimal Pow(int exponentSmall, EContext ctx) {
      return this.Pow(EDecimal.FromInt64(exponentSmall), ctx);
    }

    /**
     * Raises this object's value to the given exponent.
     * @param exponentSmall The exponent to raise this object's value to.
     * @return This^exponent. Returns not-a-number (NaN) if this object and
     * exponent are both 0.
     */
    public EDecimal Pow(int exponentSmall) {
      return this.Pow(EDecimal.FromInt64(exponentSmall), null);
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
        this.unsignedMantissa.ToEInteger().GetDigitCountAsEInteger();
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value but a new
     * exponent. <p>Note that this is not always the same as rounding to a
     * given number of decimal places, since it can fail if the difference
     * between this value's exponent and the desired exponent is too big,
     * depending on the maximum precision. If rounding to a number of
     * decimal places is desired, it's better to use the RoundToExponent
     * and RoundToIntegral methods instead.</p> <p><b>Remark:</b> This
     * method can be used to implement fixed-point decimal arithmetic, in
     * which each decimal number has a fixed number of digits after the
     * decimal point. The following code example returns a fixed-point
     * number with up to 20 digits before and exactly 5 digits after the
     * decimal point:</p> <pre> &#x2f;&#x2a; After performing arithmetic operations, adjust &#x2f;&#x2a; the number to 5&#x2a;&#x2f;&#x2a;&#x2f; &#x2f;&#x2a;&#x2a;&#x2f; digits after the decimal point number = number.Quantize(EInteger.FromInt32(-5), &#x2f;&#x2a; five digits after the decimal point&#x2a;&#x2f; EContext.ForPrecision(25) &#x2f;&#x2a; 25-digit precision);&#x2a;&#x2f;</pre> <p>A fixed-point decimal arithmetic in
     * which no digits come after the decimal point (a desired exponent of
     *  0) is considered an "integer arithmetic".</p>
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
     * @return An arbitrary-precision decimal number with the same value as this
     * object but with the exponent changed. Signals FlagInvalid and
     * returns not-a-number (NaN) if this object is infinity, if the
     * rounded result can't fit the given precision, or if the context
     * defines an exponent range and the given exponent is outside that
     * range.
     */
    public EDecimal Quantize(
      EInteger desiredExponent,
      EContext ctx) {
      return this.Quantize(
          EDecimal.Create(EInteger.FromInt32(1), desiredExponent),
          ctx);
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * one but a new exponent. <p><b>Remark:</b> This method can be used to
     * implement fixed-point decimal arithmetic, in which a fixed number of
     * digits come after the decimal point. A fixed-point decimal
     * arithmetic in which no digits come after the decimal point (a
     *  desired exponent of 0) is considered an "integer arithmetic" .</p>
     * @param desiredExponentInt The desired exponent for the result. The exponent
     * is the number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @param rounding A rounding mode to use in case the result needs to be
     * rounded to fit the given exponent.
     * @return An arbitrary-precision decimal number with the same value as this
     * object but with the exponent changed. Returns not-a-number (NaN) if
     * this object is infinity, or if the rounding mode is ERounding.None
     * and the result is not exact.
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
     * Returns an arbitrary-precision decimal number with the same value but a new
     * exponent. <p>Note that this is not always the same as rounding to a
     * given number of decimal places, since it can fail if the difference
     * between this value's exponent and the desired exponent is too big,
     * depending on the maximum precision. If rounding to a number of
     * decimal places is desired, it's better to use the RoundToExponent
     * and RoundToIntegral methods instead.</p> <p><b>Remark:</b> This
     * method can be used to implement fixed-point decimal arithmetic, in
     * which each decimal number has a fixed number of digits after the
     * decimal point. The following code example returns a fixed-point
     * number with up to 20 digits before and exactly 5 digits after the
     * decimal point:</p> <pre>/* After performing arithmetic operations, adjust the number to 5 digits after the decimal point &#x2a;&#x2f; number = number.Quantize(-5, /* five digits after the decimal point &#x2a;&#x2f;EContext.ForPrecision(25) /* 25-digit precision&#x2a;&#x2f;);</pre> <p>A
     * fixed-point decimal arithmetic in which no digits come after the
     *  decimal point (a desired exponent of 0) is considered an "integer
     *  arithmetic".</p>
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
     * @return An arbitrary-precision decimal number with the same value as this
     * object but with the exponent changed. Signals FlagInvalid and
     * returns not-a-number (NaN) if this object is infinity, if the
     * rounded result can't fit the given precision, or if the context
     * defines an exponent range and the given exponent is outside that
     * range.
     */
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

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but with the same exponent as another decimal number. <p>Note
     * that this is not always the same as rounding to a given number of
     * decimal places, since it can fail if the difference between this
     * value's exponent and the desired exponent is too big, depending on
     * the maximum precision. If rounding to a number of decimal places is
     * desired, it's better to use the RoundToExponent and RoundToIntegral
     * methods instead.</p> <p><b>Remark:</b> This method can be used to
     * implement fixed-point decimal arithmetic, in which a fixed number of
     * digits come after the decimal point. A fixed-point decimal
     * arithmetic in which no digits come after the decimal point (a
     *  desired exponent of 0) is considered an "integer arithmetic" .</p>
     * @param otherValue An arbitrary-precision decimal number containing the
     * desired exponent of the result. The significand is ignored. The
     * exponent is the number of fractional digits in the result, expressed
     * as a negative number. Can also be positive, which eliminates
     * lower-order places from the number. For example, -3 means round to
     * the thousandth (10^-3, 0.0001), and 3 means round to the
     * thousands-place (10^3, 1000). A value of 0 rounds the number to an
     * integer. The following examples for this parameter express a desired
     * exponent of 3: {@code 10e3}, {@code 8888e3}, {@code 4.56e5}.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags). Can be null, in which case the default
     * rounding mode is HalfEven.
     * @return An arbitrary-precision decimal number with the same value as this
     * object but with the exponent changed. Signals FlagInvalid and
     * returns not-a-number (NaN) if the result can't fit the given
     * precision without rounding, or if the arithmetic context defines an
     * exponent range and the given exponent is outside that range.
     */
    public EDecimal Quantize(
      EDecimal otherValue,
      EContext ctx) {
      return GetMathValue(ctx).Quantize(this, otherValue, ctx);
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
    public EDecimal Reduce(EContext ctx) {
      return GetMathValue(ctx).Reduce(this, ctx);
    }

    /**
     * Returns the remainder that would result when this arbitrary-precision
     * decimal floating-point number is divided by another
     * arbitrary-precision decimal floating-point number. The remainder is
     * the number that remains when the absolute value of this
     * arbitrary-precision decimal floating-point number is divided (as
     * though by DivideToIntegerZeroScale) by the absolute value of the
     * other arbitrary-precision decimal floating-point number; the
     * remainder has the same sign (positive or negative) as this
     * arbitrary-precision decimal floating-point number.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result, and of the intermediate integer
     * division. If {@code HasFlags} of the context is true, will also
     * store the flags resulting from the operation (the flags are in
     * addition to the pre-existing flags). Can be null, in which the
     * precision is unlimited.
     * @return The remainder that would result when this arbitrary-precision
     * decimal floating-point number is divided by another
     * arbitrary-precision decimal floating-point number. Signals
     * FlagDivideByZero and returns infinity if the divisor (this
     * arbitrary-precision decimal floating-point number) is 0 and the
     * dividend (the other arbitrary-precision decimal floating-point
     * number) is nonzero. Signals FlagInvalid and returns not-a-number
     * (NaN) if the divisor and the dividend are 0, or if the result of the
     * division doesn't fit the given precision.
     */
    public EDecimal Remainder(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).Remainder(this, divisor, ctx, true);
    }

    /**
     * Finds the remainder that results when dividing two arbitrary-precision
     * decimal numbers, except the intermediate division is not adjusted to
     * fit the precision of the given arithmetic context. The value of this
     * object is divided by the absolute value of the other object; the
     * remainder has the same sign (positive or negative) as this object's
     * value.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result, but not also of the intermediate
     * integer division. If {@code HasFlags} of the context is true, will
     * also store the flags resulting from the operation (the flags are in
     * addition to the pre-existing flags). Can be null, in which the
     * precision is unlimited.
     * @return The remainder of the two numbers. Signals FlagInvalid and returns
     * not-a-number (NaN) if the divisor is 0, or if the result doesn't fit
     * the given precision.
     */
    public EDecimal RemainderNoRoundAfterDivide(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx).Remainder(this, divisor, ctx, false);
    }

    /**
     * Calculates the remainder of a number by the formula <code>"this" - (("this" /
     *  "divisor") * "divisor")</code>.
     * @param divisor The number to divide by.
     * @return An arbitrary-precision decimal number.
     */
    public EDecimal RemainderNaturalScale(EDecimal divisor) {
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
     * @return An arbitrary-precision decimal number.
     */
    public EDecimal RemainderNaturalScale(
      EDecimal divisor,
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
    public EDecimal RemainderNear(
      EDecimal divisor,
      EContext ctx) {
      return GetMathValue(ctx)
        .RemainderNear(this, divisor, ctx);
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to a new exponent if necessary. The resulting
     * number's Exponent property will not necessarily be the given
     * exponent; use the Quantize method instead to give the result a
     * particular exponent.
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
     * @return An arbitrary-precision decimal number rounded to the closest value
     * representable in the given precision. If the result can't fit the
     * precision, additional digits are discarded to make it fit. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to the
     * given exponent when rounding, and the given exponent is outside of
     * the valid range of the arithmetic context.
     */
    public EDecimal RoundToExponent(
      EInteger exponent,
      EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentSimple(this, exponent, ctx);
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to a new exponent if necessary, using the
     * HalfEven rounding mode. The resulting number's Exponent property
     * will not necessarily be the given exponent; use the Quantize method
     * instead to give the result a particular exponent.
     * @param exponent The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @return An arbitrary-precision decimal number rounded to the closest value
     * representable for the given exponent.
     */
    public EDecimal RoundToExponent(
      EInteger exponent) {
      return this.RoundToExponent(
          exponent,
          EContext.ForRounding(ERounding.HalfEven));
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to a new exponent if necessary, using the given
     * rounding mode. The resulting number's Exponent property will not
     * necessarily be the given exponent; use the Quantize method instead
     * to give the result a particular exponent.
     * @param exponent The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @param rounding Desired mode for rounding this number's value.
     * @return An arbitrary-precision decimal number rounded to the closest value
     * representable for the given exponent.
     */
    public EDecimal RoundToExponent(
      EInteger exponent,
      ERounding rounding) {
      return this.RoundToExponent(
          exponent,
          EContext.ForRounding(rounding));
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to a new exponent if necessary, using the
     * HalfEven rounding mode. The resulting number's Exponent property
     * will not necessarily be the given exponent; use the Quantize method
     * instead to give the result a particular exponent.
     * @param exponentSmall The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @return An arbitrary-precision decimal number rounded to the closest value
     * representable for the given exponent.
     */
    public EDecimal RoundToExponent(
      int exponentSmall) {
      return this.RoundToExponent(exponentSmall, ERounding.HalfEven);
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to a new exponent if necessary. The resulting
     * number's Exponent property will not necessarily be the given
     * exponent; use the Quantize method instead to give the result a
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
     * @return An arbitrary-precision decimal number rounded to the closest value
     * representable in the given precision. If the result can't fit the
     * precision, additional digits are discarded to make it fit. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to the
     * given exponent when rounding, and the given exponent is outside of
     * the valid range of the arithmetic context.
     */
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

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to a new exponent if necessary. The resulting
     * number's Exponent property will not necessarily be the given
     * exponent; use the Quantize method instead to give the result a
     * particular exponent.
     * @param exponentSmall The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @param rounding The desired mode to use to round the given number to the
     * given exponent.
     * @return An arbitrary-precision decimal number rounded to the given negative
     * number of decimal places.
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
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to the given exponent represented as an
     * arbitrary-precision integer, and signals an inexact flag if the
     * result would be inexact. The resulting number's Exponent property
     * will not necessarily be the given exponent; use the Quantize method
     * instead to give the result a particular exponent.
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
     * @return An arbitrary-precision decimal number rounded to the closest value
     * representable in the given precision. Signals FlagInvalid and
     * returns not-a-number (NaN) if the result can't fit the given
     * precision without rounding. Signals FlagInvalid and returns
     * not-a-number (NaN) if the arithmetic context defines an exponent
     * range, the new exponent must be changed to the given exponent when
     * rounding, and the given exponent is outside of the valid range of
     * the arithmetic context.
     */
    public EDecimal RoundToExponentExact(
      EInteger exponent,
      EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentExact(this, exponent, ctx);
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to the given exponent represented as a 32-bit
     * signed integer, and signals an inexact flag if the result would be
     * inexact. The resulting number's Exponent property will not
     * necessarily be the given exponent; use the Quantize method instead
     * to give the result a particular exponent.
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
     * @return An arbitrary-precision decimal number rounded to the closest value
     * representable in the given precision. Signals FlagInvalid and
     * returns not-a-number (NaN) if the result can't fit the given
     * precision without rounding. Signals FlagInvalid and returns
     * not-a-number (NaN) if the arithmetic context defines an exponent
     * range, the new exponent must be changed to the given exponent when
     * rounding, and the given exponent is outside of the valid range of
     * the arithmetic context.
     */
    public EDecimal RoundToExponentExact(
      int exponentSmall,
      EContext ctx) {
      return this.RoundToExponentExact(EInteger.FromInt32(exponentSmall), ctx);
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to the given exponent represented as a 32-bit
     * signed integer, and signals an inexact flag if the result would be
     * inexact. The resulting number's Exponent property will not
     * necessarily be the given exponent; use the Quantize method instead
     * to give the result a particular exponent.
     * @param exponentSmall The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the
     * thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
     * 1000). A value of 0 rounds the number to an integer.
     * @param rounding Desired mode for rounding this object's value.
     * @return An arbitrary-precision decimal number rounded to the closest value
     * representable using the given exponent.
     */
    public EDecimal RoundToExponentExact(
      int exponentSmall,
      ERounding rounding) {
      return this.RoundToExponentExact(
          EInteger.FromInt32(exponentSmall),
          EContext.Unlimited.WithRounding(rounding));
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to an integer, and signals an inexact flag if the
     * result would be inexact. The resulting number's Exponent property
     * will not necessarily be 0; use the Quantize method instead to give
     * the result an exponent of 0.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return An arbitrary-precision decimal number rounded to the closest integer
     * representable in the given precision. Signals FlagInvalid and
     * returns not-a-number (NaN) if the result can't fit the given
     * precision without rounding. Signals FlagInvalid and returns
     * not-a-number (NaN) if the arithmetic context defines an exponent
     * range, the new exponent must be changed to 0 when rounding, and 0 is
     * outside of the valid range of the arithmetic context.
     */
    public EDecimal RoundToIntegerExact(EContext ctx) {
      return GetMathValue(ctx).RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to an integer, without adding the
     * <code>FlagInexact</code> or <code>FlagRounded</code> flags. The resulting
     * number's Exponent property will not necessarily be 0; use the
     * Quantize method instead to give the result an exponent of 0.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags), except that this function will never add
     * the {@code FlagRounded} and {@code FlagInexact} flags (the only
     * difference between this and RoundToExponentExact). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return An arbitrary-precision decimal number rounded to the closest integer
     * representable in the given precision. If the result can't fit the
     * precision, additional digits are discarded to make it fit. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to 0
     * when rounding, and 0 is outside of the valid range of the arithmetic
     * context.
     */
    public EDecimal RoundToIntegerNoRoundedFlag(EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to an integer, and signals an inexact flag if the
     * result would be inexact.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return An arbitrary-precision decimal number rounded to the closest integer
     * representable in the given precision. Signals FlagInvalid and
     * returns not-a-number (NaN) if the result can't fit the given
     * precision without rounding. Signals FlagInvalid and returns
     * not-a-number (NaN) if the arithmetic context defines an exponent
     * range, the new exponent must be changed to 0 when rounding, and 0 is
     * outside of the valid range of the arithmetic context.
     * @deprecated Renamed to RoundToIntegerExact.
 */
@Deprecated
    public EDecimal RoundToIntegralExact(EContext ctx) {
      return GetMathValue(ctx).RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    /**
     * Returns an arbitrary-precision decimal number with the same value as this
     * object but rounded to an integer, without adding the
     * <code>FlagInexact</code> or <code>FlagRounded</code> flags.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags), except that this function will never add
     * the {@code FlagRounded} and {@code FlagInexact} flags (the only
     * difference between this and RoundToExponentExact). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return An arbitrary-precision decimal number rounded to the closest integer
     * representable in the given precision. If the result can't fit the
     * precision, additional digits are discarded to make it fit. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to 0
     * when rounding, and 0 is outside of the valid range of the arithmetic
     * context.
     * @deprecated Renamed to RoundToIntegerNoRoundedFlag.
 */
@Deprecated
    public EDecimal RoundToIntegralNoRoundedFlag(EContext ctx) {
      return GetMathValue(ctx)
        .RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
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
    public EDecimal RoundToPrecision(EContext ctx) {
      return GetMathValue(ctx).RoundToPrecision(this, ctx);
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
    public EDecimal PreRound(EContext ctx) {
      return NumberUtility.PreRound(this, ctx, GetMathValue(ctx));
    }

    /**
     * Returns a number similar to this number but with the scale adjusted.
     * @param places The power of 10 to scale by.
     * @return A number whose exponent is increased by {@code places}. For example,
     *  if {@code places} is 5, "78E-2" becomes "78E+3" and has a bigger
     * value.
     */
    public EDecimal ScaleByPowerOfTen(int places) {
      return this.ScaleByPowerOfTen(EInteger.FromInt32(places), null);
    }

    /**
     * Returns a number similar to this number but with the scale adjusted.
     * @param places The power of 10 to scale by.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return A number whose exponent is generally increased by {@code places}.
     *  For example, in general, if {@code places} is 5, "78E-2" becomes
     *  "78E+3" and has a bigger value.
     */
    public EDecimal ScaleByPowerOfTen(int places, EContext ctx) {
      return this.ScaleByPowerOfTen(EInteger.FromInt32(places), ctx);
    }

    /**
     * Returns a number similar to this number but with the scale adjusted.
     * @param bigPlaces The power of 10 to scale by.
     * @return A number whose exponent is increased by {@code bigPlaces}. For
     *  example, if {@code bigPlaces} is 5, "78E-2" becomes "78E+3" and has
     * a bigger value.
     */
    public EDecimal ScaleByPowerOfTen(EInteger bigPlaces) {
      return this.ScaleByPowerOfTen(bigPlaces, null);
    }

    /**
     * Returns a number similar to this number but with its scale adjusted.
     * @param bigPlaces The power of 10 to scale by.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return A number whose exponent is generally increased by {@code bigPlaces}.
     *  For example, in general, if {@code bigPlaces} is 5, "78E-2" becomes
     *  "78E+3" and has a bigger value.
     * @throws NullPointerException The parameter {@code bigPlaces} is null.
     */
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

    /**
     * Finds the square root of this object's value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the square root function's results are generally
     * not exact for many inputs.</i> (Unlike in the General Decimal
     * Arithmetic Specification, any rounding mode is allowed.).
     * @return The square root. Signals the flag FlagInvalid and returns NaN if
     * this object is less than 0 (the square root would be a complex
     * number, but the return value is still NaN). Signals FlagInvalid and
     * returns not-a-number (NaN) if the parameter {@code ctx} is null or
     * the precision is unlimited (the context's Precision property is 0).
     */
    public EDecimal Sqrt(EContext ctx) {
      return GetMathValue(ctx).SquareRoot(this, ctx);
    }

    /**
     * Finds the square root of this object's value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the square root function's results are generally
     * not exact for many inputs.</i> (Unlike in the General Decimal
     * Arithmetic Specification, any rounding mode is allowed.).
     * @return The square root. Signals the flag FlagInvalid and returns NaN if
     * this object is less than 0 (the square root would be a complex
     * number, but the return value is still NaN). Signals FlagInvalid and
     * returns not-a-number (NaN) if the parameter {@code ctx} is null or
     * the precision is unlimited (the context's Precision property is 0).
     * @deprecated Renamed to Sqrt.
 */
@Deprecated
    public EDecimal SquareRoot(EContext ctx) {
      return GetMathValue(ctx).SquareRoot(this, ctx);
    }

    /**
     * Subtracts an arbitrary-precision decimal floating-point number from this
     * arbitrary-precision decimal floating-point number and returns the
     * result. The exponent for the result is the lower of this
     * arbitrary-precision decimal floating-point number's exponent and the
     * other arbitrary-precision decimal floating-point number's exponent.
     * @param otherValue The number to subtract from this instance's value.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision decimal floating-point number minus another
     * arbitrary-precision decimal floating-point number. If this
     * arbitrary-precision decimal floating-point number is not-a-number
     * (NaN), returns NaN.
     */
    public EDecimal Subtract(EDecimal otherValue) {
      return this.Subtract(otherValue, EContext.UnlimitedHalfEven);
    }

    /**
     * Subtracts an arbitrary-precision decimal floating-point number from this
     * arbitrary-precision decimal floating-point number and returns the
     * result.
     * @param otherValue The number to subtract from this instance's value.
     * @param ctx An arithmetic context to control the precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision decimal floating-point number minus another
     * arbitrary-precision decimal floating-point number. If this
     * arbitrary-precision decimal floating-point number is not-a-number
     * (NaN), returns NaN.
     * @throws NullPointerException The parameter {@code otherValue} is null.
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

    /**
     * Converts this value to its closest equivalent as a 64-bit floating-point
     * number encoded in the IEEE 754 binary64 format, using the half-even
     * rounding mode. <p>If this value is a NaN, sets the high bit of the
     * binary64 value's significand area for a quiet NaN, and clears it for
     * a signaling NaN. Then the other bits of the significand area are set
     * to the lowest bits of this object's unsigned significand, and the
     * next-highest bit of the significand area is set if those bits are
     * all zeros and this is a signaling NaN.</p>
     * @return The closest 64-bit floating-point number to this value, encoded in
     * the IEEE 754 binary64 format. The return value can be positive
     * infinity or negative infinity, encoded in the IEEE 754 binary64
     * format, if this value exceeds the range of a 64-bit floating point
     * number.
     */
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

    /**
     * Converts this value to its closest equivalent as a 64-bit floating-point
     * number, using the half-even rounding mode. <p>If this value is a
     * NaN, sets the high bit of the 64-bit floating point number's
     * significand area for a quiet NaN, and clears it for a signaling NaN.
     * Then the other bits of the significand area are set to the lowest
     * bits of this object's unsigned significand, and the next-highest bit
     * of the significand area is set if those bits are all zeros and this
     * is a signaling NaN. Unfortunately, in the.NET implementation, the
     * return value of this method may be a quiet NaN even if a signaling
     * NaN would otherwise be generated.</p>
     * @return The closest 64-bit floating-point number to this value. The return
     * value can be positive infinity or negative infinity if this value
     * exceeds the range of a 64-bit floating point number.
     */
    public double ToDouble() {
      long value = this.ToDoubleBits();
      return Double.longBitsToDouble(value);
    }

    /**
     * Converts this value to an arbitrary-precision integer, discarding the
     * fractional part in this value. Note that depending on the value,
     * especially the exponent, generating the arbitrary-precision integer
     * may require a huge amount of memory. Use the ToSizedEInteger method
     * to convert a number to an EInteger only if the integer fits in a
     * bounded bit range; that method will throw an exception on overflow.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN).
     * @throws UnsupportedOperationException There is not enough memory to store the value
     * as an EInteger.
     */
    public EInteger ToEInteger() {
      return this.ToEIntegerInternal(false);
    }

    /**
     * Converts this value to an arbitrary-precision integer, checking whether the
     * fractional part of the value would be lost. Note that depending on
     * the value, especially the exponent, generating the
     * arbitrary-precision integer may require a huge amount of memory. Use
     * the ToSizedEIntegerIfExact method to convert a number to an EInteger
     * only if the integer fits in a bounded bit range; that method will
     * throw an exception on overflow.
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
     * fractional part of the value would be lost. Note that depending on
     * the value, especially the exponent, generating the
     * arbitrary-precision integer may require a huge amount of memory. Use
     * the ToSizedEIntegerIfExact method to convert a number to an EInteger
     * only if the integer fits in a bounded bit range; that method will
     * throw an exception on overflow.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN).
     * @throws ArithmeticException This object's value is not an exact integer.
     */
    public EInteger ToEIntegerIfExact() {
      return this.ToEIntegerInternal(true);
    }

    /**
     * Same as toString(), except that when an exponent is used it will be a
     * multiple of 3.
     * @return A text string.
     */
    public String ToEngineeringString() {
      return this.ToStringInternal(1);
    }

    /**
     * Creates a binary floating-point number from this object's value. Note that
     * if the binary floating-point number contains a negative exponent,
     * the resulting value might not be exact, in which case the resulting
     * binary floating-point number will be an approximation of this
     * decimal number's value, using the half-even rounding mode.
     * @return An arbitrary-precision binary floating-point number.
     * @deprecated Renamed to ToEFloat.
 */
@Deprecated
    public EFloat ToExtendedFloat() {
      return this.ToEFloat(EContext.UnlimitedHalfEven);
    }

    /**
     * Creates a binary floating-point number from this object's value. Note that
     * if the binary floating-point number contains a negative exponent,
     * the resulting value might not be exact, in which case the resulting
     * binary floating-point number will be an approximation of this
     * decimal number's value, using the half-even rounding mode.
     * @return An arbitrary-precision binary floating-point number.
     */
    public EFloat ToEFloat() {
      return this.ToEFloat(EContext.UnlimitedHalfEven);
    }

    /**
     * Converts this value to a string as though with the toString method, but
     * without using exponential notation.
     * @return A text string.
     */
    public String ToPlainString() {
      return this.ToStringInternal(2);
    }

    /**
     * Converts this value to its closest equivalent as a binary floating-point
     * number, expressed as an integer in the IEEE 754 binary16 format
     *  (also known as a "half-precision" floating-point number). The
     * half-even rounding mode is used. <p>If this value is a NaN, sets the
     * high bit of the binary16 number's significand area for a quiet NaN,
     * and clears it for a signaling NaN. Then the other bits of the
     * significand area are set to the lowest bits of this object's
     * unsigned significand, and the next-highest bit of the significand
     * area is set if those bits are all zeros and this is a signaling
     * NaN.</p>
     * @return The closest binary floating-point number to this value, expressed as
     * an integer in the IEEE 754 binary16 format. The return value can be
     * positive infinity or negative infinity if this value exceeds the
     * range of a floating-point number in the binary16 format.
     */
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
          int m = this.unsignedMantissa.ToInt32();
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
        }
        if (this.exponent.CompareToInt(39) > 0) {
          // Very high exponent, treat as infinity
          return this.isNegative() ? ((short)(EFloat.Binary16Infinity +
(1 << (EFloat.Binary16Bits - 1)))) :
            (short)EFloat.Binary16Infinity;
        }
      }
      return this.ToEFloat(EContext.Binary16).ToHalfBits();
    }

    /**
     * Converts this value to its closest equivalent as a 32-bit floating-point
     * number encoded in the IEEE 754 binary32 format, using the half-even
     * rounding mode. <p>If this value is a NaN, sets the high bit of the
     * 32-bit floating point number's significand area for a quiet NaN, and
     * clears it for a signaling NaN. Then the other bits of the
     * significand area are set to the lowest bits of this object's
     * unsigned significand, and the next-highest bit of the significand
     * area is set if those bits are all zeros and this is a signaling
     * NaN.</p>
     * @return The closest 32-bit binary floating-point number to this value,
     * encoded in the IEEE 754 binary32 format. The return value can be
     * positive infinity or negative infinity if this value exceeds the
     * range of a 32-bit floating point number.
     */
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

    /**
     * Converts this value to its closest equivalent as a 32-bit floating-point
     * number, using the half-even rounding mode. <p>If this value is a
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
     * Converts this value to a text string. Returns a value compatible with this
     * class's FromString method.
     * @return A string representation of this object. The text string will be in
     * exponential notation (expressed as a number 1 or greater, but less
     * than 10, times a power of 10) if this object's Exponent property is
     * greater than 0 or if the number's first nonzero decimal digit is
     * more than five digits after the decimal point.
     */
    @Override public String toString() {
      return this.ToStringInternal(0);
    }

    /**
     * Returns the unit in the last place. The significand will be 1 and the
     * exponent will be this number's exponent. Returns 1 with an exponent
     * of 0 if this number is infinity or not-a-number (NaN).
     * @return An arbitrary-precision decimal number.
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

    /**
     * Creates a binary floating-point number from this object's value. Note that
     * if the binary floating-point number contains a negative exponent,
     * the resulting value might not be exact, in which case the resulting
     * binary floating-point number will be an approximation of this
     * decimal number's value.
     * @param ec An arithmetic context to control the precision, rounding, and
     * exponent range of the result. The precision is in bits, and an
     * example of this parameter is {@code EContext.Binary64}. Can be null.
     * @return An arbitrary-precision floating-point number.
     */
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
      /**
       * This is an internal method.
       * @return A 32-bit signed integer.
       */
      public int GetRadix() {
        return 10;
      }

      /**
       * This is an internal method.
       * @param value An arbitrary-precision decimal number.
       * @return A 32-bit signed integer.
       */
      public int GetSign(EDecimal value) {
        return value.signum();
      }

      /**
       * This is an internal method.
       * @param value An arbitrary-precision decimal number.
       * @return An arbitrary-precision integer.
       */
      public EInteger GetMantissa(EDecimal value) {
        return value.unsignedMantissa.ToEInteger();
      }

      /**
       * This is an internal method.
       * @param value An arbitrary-precision decimal number.
       * @return An arbitrary-precision integer.
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

      /**
       * This is an internal method.
       * @param value An arbitrary-precision decimal number.
       * @return A 32-bit signed integer.
       */
      public int GetFlags(EDecimal value) {
        return ((int)value.flags) & 0xff;
      }

      /**
       * This is an internal method.
       * @param mantissa The parameter {@code mantissa} is a Numbers.EInteger object.
       * @param exponent The parameter {@code exponent} is an internal parameter.
       * @param flags The parameter {@code flags} is an internal parameter.
       * @return An arbitrary-precision decimal number.
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
       * This is an internal method.
       * @return A 32-bit signed integer.
       */
      public int GetArithmeticSupport() {
        return BigNumberFlags.FiniteAndNonFinite;
      }

      /**
       * This is an internal method.
       * @param val The parameter {@code val} is a 32-bit signed integer.
       * @return An arbitrary-precision decimal number.
       */
      public EDecimal ValueOf(int val) {
        return (val == 0) ? Zero : ((val == 1) ? One : FromInt64(val));
      }
    }

    /**
     * Returns one added to this arbitrary-precision decimal number.
     * @return The given arbitrary-precision decimal number plus one.
     */
    public EDecimal Increment() {
      return this.Add(1);
    }

    /**
     * Returns one subtracted from this arbitrary-precision decimal number.
     * @return The given arbitrary-precision decimal number minus one.
     */
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
      this.CheckTrivialOverflow(3);
      if (this.IsIntegerPartZero()) {
        return (byte)0;
      } else if (this.isNegative()) {
        throw new ArithmeticException("Value out of range");
      }
      return this.ToEInteger().ToByteChecked();
    }

    /**
     * Converts this number's value to an integer by discarding its fractional
     * part, and returns the least-significant bits of its two's-complement
     * form as a byte (from 0 to 255).
     * @return This number, converted to a byte (from 0 to 255). Returns 0 if this
     * value is infinity or not-a-number.
     */
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
      if (this.isNegative() && !this.isZero()) {
        throw new ArithmeticException("Value out of range");
      }
      this.CheckTrivialOverflow(3);
      return this.ToEIntegerIfExact().ToByteChecked();
    }

    /**
     * Converts a byte (from 0 to 255) to an arbitrary-precision decimal number.
     * @param inputByte The number to convert as a byte (from 0 to 255).
     * @return This number's value as an arbitrary-precision decimal number.
     */
    public static EDecimal FromByte(byte inputByte) {
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
      this.CheckTrivialOverflow(5);
      return this.IsIntegerPartZero() ? ((short)0) :
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
      this.CheckTrivialOverflow(5);
      return this.ToEIntegerIfExact().ToInt16Checked();
    }

    /**
     * Converts a 16-bit signed integer to an arbitrary-precision decimal number.
     * @param inputInt16 The number to convert as a 16-bit signed integer.
     * @return This number's value as an arbitrary-precision decimal number.
     */
    public static EDecimal FromInt16(short inputInt16) {
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
      this.CheckTrivialOverflow(10);
      return this.IsIntegerPartZero() ? ((int)0) :
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
      if (this.isZero()) {
        return (int)0;
      }
      this.CheckTrivialOverflow(10);
      return this.ToEIntegerIfExact().ToInt32Checked();
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
      this.CheckTrivialOverflow(19);
      return this.IsIntegerPartZero() ? 0L : this.ToEInteger().ToInt64Checked();
    }

    /**
     * Converts this number's value to an integer by discarding its fractional
     * part, and returns the least-significant bits of its two's-complement
     * form as a 64-bit signed integer.
     * @return This number, converted to a 64-bit signed integer. Returns 0 if this
     * value is infinity or not-a-number.
     */
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
      if (this.isZero()) {
        return 0L;
      }
      this.CheckTrivialOverflow(19);
      return this.ToEIntegerIfExact().ToInt64Checked();
    }
    // End integer conversions
  }
