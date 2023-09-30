package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
https://creativecommons.org/publicdomain/zero/1.0/

 */

  /**
   * <p>Contains parameters for controlling the precision, rounding, and exponent
   * range of arbitrary-precision numbers. (The "E" stands for "extended", and
   * has this prefix to group it with the other classes common to this library,
   * particularly EDecimal, EFloat, and ERational.). </p> <p><b>Thread
   * safety:</b> With one exception, instances of this class are immutable and
   * are safe to use among multiple threads. The one exception involves the
   * {@code Flags} property. If the context's {@code HasFlags} property (a
   * read-only property) is {@code true}, the {@code Flags} property is mutable,
   * thus making the context mutable. This class doesn't synchronize access to
   * such mutable contexts, so applications should provide their own
   * synchronization if a context with the {@code HasFlags} property set to
   * {@code true} will be shared among multiple threads and at least one of those
   * threads needs to write the {@code Flags} property (which can happen, for
   * example, by passing the context to most methods of {@code EDecimal} such as
   * {@code Add}).</p>
   *
   */
  public final class EContext {
    /**
     * <p>Signals that the exponent was adjusted to fit the exponent range.</p>
     *
     */
    public static final int FlagClamped = 32;

    /**
     * <p>Signals a division of a nonzero number by zero.</p>
     *
     */
    public static final int FlagDivideByZero = 128;

    /**
     * <p>Signals that the result was rounded to a different mathematical value,
     * but as close as possible to the original.</p>
     *
     */
    public static final int FlagInexact = 1;

    /**
     * <p>Signals an invalid operation.</p>
     *
     */
    public static final int FlagInvalid = 64;

    /**
     * <p>Signals that an operand was rounded to a different mathematical value
     * before an operation.</p>
     *
     */
    public static final int FlagLostDigits = 256;

    /**
     * <p>Signals that the result is non-zero and the exponent is higher than the
     * highest exponent allowed.</p>
     *
     */
    public static final int FlagOverflow = 16;

    /**
     * <p>Signals that the result was rounded to fit the precision; either the
     * value or the exponent may have changed from the original.</p>
     *
     */
    public static final int FlagRounded = 2;

    /**
     * <p>Signals that the result's exponent, before rounding, is lower than the
     * lowest exponent allowed.</p>
     *
     */
    public static final int FlagSubnormal = 4;

    /**
     * <p>Signals that the result's exponent, before rounding, is lower than the
     * lowest exponent allowed, and the result was rounded to a different
     * mathematical value, but as close as possible to the original.</p>
     *
     */
    public static final int FlagUnderflow = 8;

    /**
     * <p>A basic arithmetic context, 9 digits precision, rounding mode half-up,
     * unlimited exponent range. The default rounding mode is HalfUp.</p>
     *
     */

    public static final EContext Basic =
      EContext.ForPrecisionAndRounding(9, ERounding.HalfUp);

    /**
     * <p>An arithmetic context for Java's BigDecimal format. The default rounding
     * mode is HalfUp.</p>
     *
     */

    public static final EContext BigDecimalJava =
      new EContext(0, ERounding.HalfUp, 0, 0, true)
    .WithExponentClamp(true).WithAdjustExponent(false)
    .WithBigExponentRange(
      EInteger.FromInt32(0).Subtract(EInteger.FromInt64(Integer.MAX_VALUE)),
      EInteger.FromInt32(1).Add(EInteger.FromInt64(Integer.MAX_VALUE)));

    /**
     * <p>An arithmetic context for the IEEE-754-2008 binary128 format, 113 bits
     * precision. The default rounding mode is HalfEven.</p>
     *
     */

    public static final EContext Binary128 =
      EContext.ForPrecisionAndRounding(113, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-16382, 16383);

    /**
     * <p>An arithmetic context for the IEEE-754-2008 binary16 format, 11 bits
     * precision. The default rounding mode is HalfEven.</p>
     *
     */

    public static final EContext Binary16 =
      EContext.ForPrecisionAndRounding(11, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-14, 15);

    /**
     * <p>An arithmetic context for the IEEE-754-2008 binary32 format, 24 bits
     * precision. The default rounding mode is HalfEven.</p>
     *
     */

    public static final EContext Binary32 =
      EContext.ForPrecisionAndRounding(24, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-126, 127);

    /**
     * <p>An arithmetic context for the IEEE-754-2008 binary64 format, 53 bits
     * precision. The default rounding mode is HalfEven.</p>
     *
     */

    public static final EContext Binary64 =
      EContext.ForPrecisionAndRounding(53, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-1022, 1023);

    /**
     * <p>An arithmetic context for the.NET Framework decimal format (see {@link
     * com.upokecenter.numbers.EDecimal "Forms of numbers"}), 96 bits precision,
     * and a valid exponent range of -28 to 0. The default rounding mode is
     * HalfEven. (The {@code "Cli"} stands for "Common Language Infrastructure",
     * which defined this format as the .NET Framework decimal format in version 1,
     * but leaves it unspecified in later versions.).</p>
     *
     */

    public static final EContext CliDecimal =
      new EContext(96, ERounding.HalfEven, 0, 28, true)
    .WithPrecisionInBits(true);

    /**
     * <p>An arithmetic context for the IEEE-754-2008 decimal128 format. The
     * default rounding mode is HalfEven.</p>
     *
     */

    public static final EContext Decimal128 =
      new EContext(34, ERounding.HalfEven, -6143, 6144, true);

    /**
     * <p>An arithmetic context for the IEEE-754-2008 decimal32 format. The default
     * rounding mode is HalfEven.</p>
     *
     */

    public static final EContext Decimal32 =
      new EContext(7, ERounding.HalfEven, -95, 96, true);

    /**
     * <p>An arithmetic context for the IEEE-754-2008 decimal64 format. The default
     * rounding mode is HalfEven.</p>
     *
     */

    public static final EContext Decimal64 =
      new EContext(16, ERounding.HalfEven, -383, 384, true);

    /**
     * <p>No specific (theoretical) limit on precision. Rounding mode HalfUp.</p>
     *
     */

    public static final EContext Unlimited =
      EContext.ForPrecision(0);

    /**
     * <p>No specific (theoretical) limit on precision. Rounding mode HalfEven.</p>
     *
     */

    public static final EContext UnlimitedHalfEven =
      EContext.ForPrecision(0).WithRounding(ERounding.HalfEven);

    private EContext(
      boolean adjustExponent,
      EInteger bigintPrecision,
      boolean clampNormalExponents,
      EInteger exponentMax,
      EInteger exponentMin,
      int flags,
      boolean hasExponentRange,
      boolean hasFlags,
      boolean precisionInBits,
      ERounding rounding,
      boolean simplified,
      int traps) {
      if (bigintPrecision == null) {
        throw new NullPointerException("bigintPrecision");
      }
      if (exponentMin == null) {
        throw new NullPointerException("exponentMin");
      }
      if (exponentMax == null) {
        throw new NullPointerException("exponentMax");
      }
      if (bigintPrecision.signum() < 0) {
        throw new IllegalArgumentException("precision(" + bigintPrecision +
          ") is less than 0");
      }
      if (exponentMin.compareTo(exponentMax) > 0) {
        throw new IllegalArgumentException("exponentMinSmall(" + exponentMin +
          ") is more than " + exponentMax);
      }
      this.adjustExponent = adjustExponent;
      this.bigintPrecision = bigintPrecision;
      this.clampNormalExponents = clampNormalExponents;
      this.exponentMax = exponentMax;
      this.exponentMin = exponentMin;
      this.flags = flags;
      this.hasExponentRange = hasExponentRange;
      this.hasFlags = hasFlags;
      this.precisionInBits = precisionInBits;
      this.rounding = rounding;
      this.simplified = simplified;
      this.traps = traps;
    }

    private final boolean adjustExponent;

    private final EInteger bigintPrecision;

    private final boolean clampNormalExponents;
    private final EInteger exponentMax;

    private final EInteger exponentMin;

    private final boolean hasExponentRange;
    private final boolean hasFlags;

    private final boolean precisionInBits;

    private final ERounding rounding;

    private final boolean simplified;

    private final int traps;

    private int flags;

    /**
     * <p>Initializes a new instance of the {@link
     * com.upokecenter.numbers.EContext} class.</p>
     * <p>@param precision </p>
     * <p>The value of the Precision property.</p>
     * <p>@param rounding </p>
     * <p>The value of the Rounding property.</p>
     * <p>@param exponentMinSmall </p>
     * <p>The value of the EMin property.</p>
     * <p>@param exponentMaxSmall </p>
     * <p>The value of the EMax property.</p>
     * <p>@param clampNormalExponents </p>
     * <p>The value of the ClampNormalExponents
     * property.</p>
     *
     */
    public EContext(
      int precision,
      ERounding rounding,
      int exponentMinSmall,
      int exponentMaxSmall,
      boolean clampNormalExponents) {
 this(
          true,
          EInteger.FromInt32(precision),
          clampNormalExponents,
          EInteger.FromInt32(exponentMaxSmall),
          EInteger.FromInt32(exponentMinSmall),
          0,
          true,
          false,
          false,
          rounding,
          false,
          0);
    }

    /**
     * <p>Initializes a new instance of the {@link
     * com.upokecenter.numbers.EContext} class, .</p>
     * <p>@param bigintPrecision </p>
     * <p>The value of the Precision property.</p>
     * <p>@param rounding </p>
     * <p>The value of the Rounding property.</p>
     * <p>@param exponentMin </p>
     * <p>The value of the EMin property.</p>
     * <p>@param exponentMax </p>
     * <p>The value of the EMax property.</p>
     * <p>@param clampNormalExponents </p>
     * <p>The value of the ClampNormalExponents
     * property.</p>
     *
     */
    public EContext(
      EInteger bigintPrecision,
      ERounding rounding,
      EInteger exponentMin,
      EInteger exponentMax,
      boolean clampNormalExponents) {
 this(
          true,
          bigintPrecision,
          clampNormalExponents,
          exponentMax,
          exponentMin,
          0,
          true,
          false,
          false,
          rounding,
          false,
          0);
    }

    /**
     * <p>Gets a value indicating whether the EMax and EMin properties refer to the
     * number's Exponent property adjusted to the number's precision, or just the
     * number's Exponent property. The default value is true, meaning that EMax and
     * EMin refer to the adjusted exponent. Setting this value to false (using
     * WithAdjustExponent) is useful for modeling floating point representations
     * with an integer significand and an integer exponent, such as Java's
     * BigDecimal.</p>
     * @return <p> {@code true} if the EMax and EMin properties refer to the
     * number's Exponent property adjusted to the number's precision, or false if
     * they refer to just the number's Exponent property.</p>
     *
     */
    public final boolean getAdjustExponent() {
        return this.adjustExponent;
      }

    /**
     * <p>Gets a value indicating whether a converted number's Exponent property
     * will not be higher than EMax + 1 - Precision. If a number's exponent is
     * higher than that value, but not high enough to cause overflow, the exponent
     * is clamped to that value and enough zeros are added to the number's
     * significand to account for the adjustment. If HasExponentRange is false,
     * this value is always false.</p>
     * @return <p>If true, a converted number's Exponent property will not be
     * higher than EMax + 1 - Precision.</p>
     *
     */
    public final boolean getClampNormalExponents() {
        return this.hasExponentRange && this.clampNormalExponents;
      }

    /**
     * <p>Gets the highest exponent possible when a converted number is expressed
     * in scientific notation with one nonzero digit before the radix point. For
     * example, with a precision of 3 and an EMax of 100, the maximum value
     * possible is 9.99E + 100. (This is not the same as the highest possible
     * Exponent property.) If HasExponentRange is false, this value will be 0.</p>
     * @return <p>The highest exponent possible when a converted number is
     * expressed in scientific notation with one nonzero digit before the radix
     * point. For example, with a precision of 3 and an EMax of 100, the maximum
     * value possible is 9.99E + 100. (This is not the same as the highest possible
     * Exponent property.) If HasExponentRange is false, this value will be 0.</p>
     *
     */
    public final EInteger getEMax() {
        return this.hasExponentRange ? this.exponentMax : EInteger.FromInt32(0);
      }

    /**
     * <p>Gets the lowest exponent possible when a converted number is expressed in
     * scientific notation with one nonzero digit before the radix point. For
     * example, with a precision of 3 and an EMin of -100, the next value that
     * comes after 0 is 0.001E-100. (If AdjustExponent is false, this property
     * specifies the lowest possible Exponent property instead.) If
     * HasExponentRange is false, this value will be 0.</p>
     * @return <p>The lowest exponent possible when a converted number is expressed
     * in scientific notation with one nonzero digit before the radix point. For
     * example, with a precision of 3 and an EMin of -100, the next value that
     * comes after 0 is 0.001E-100. (If AdjustExponent is false, this property
     * specifies the lowest possible Exponent property instead.) If
     * HasExponentRange is false, this value will be 0.</p>
     *
     */
    public final EInteger getEMin() {
        return this.hasExponentRange ? this.exponentMin : EInteger.FromInt32(0);
      }

    /**
     * <p>Gets the flags that are set from converting numbers according to this
     * arithmetic context. If {@code HasFlags} is false, this value will be 0. This
     * value is a combination of bit fields. To retrieve a particular flag, use the
     * AND operation on the return value of this method. For example: {@code
     * (this.getFlags() &amp;EContext.FlagInexact) != 0} returns {@code true} if
     * the Inexact flag is set.</p>
     * @return <p>The flags that are set from converting numbers according to this
     * arithmetic context. If {@code HasFlags} is false, this value will be 0. This
     * value is a combination of bit fields. To retrieve a particular flag, use the
     * AND operation on the return value of this method. For example: {@code
     * (this.getFlags() &amp;EContext.FlagInexact) != 0} returns {@code true} if the
     * Inexact flag is set.</p>
     *
     */
    public final int getFlags() {
        return this.flags;
      }
public final void setFlags(int value) {
        if (!this.getHasFlags()) {
          throw new IllegalStateException("Can't set flags");
        }
        this.flags = value;
      }

    /**
     * <p>Gets a value indicating whether this context defines a minimum and
     * maximum exponent. If false, converted exponents can have any exponent and
     * operations can't cause overflow or underflow.</p>
     * @return <p> {@code true} if this context defines a minimum and maximum
     * exponent; otherwise, {@code false}.. If false, converted exponents can have
     * any exponent and operations can't cause overflow or underflow. {@code true}
     * if this context defines a minimum and maximum exponent; otherwise, {@code
     * false}.</p>
     *
     */
    public final boolean getHasExponentRange() {
        return this.hasExponentRange;
      }

    /**
     * <p>Gets a value indicating whether this context has a mutable Flags
     * field.</p>
     * @return <p> {@code true} if this context has a mutable Flags field;
     * otherwise, {@code false}.</p>
     *
     */
    public final boolean getHasFlags() {
        return this.hasFlags;
      }

    /**
     * <p>Gets a value indicating whether this context defines a maximum precision.
     * This is the same as whether this context's Precision property is zero.</p>
     * @return <p> {@code true} if this context defines a maximum precision;
     * otherwise, {@code false}.</p>
     *
     */
    public final boolean getHasMaxPrecision() {
        return !this.bigintPrecision.isZero();
      }

    /**
     * <p>Gets a value indicating whether this context's Precision property is in
     * bits, rather than digits. The default is false.</p>
     * @return <p>{@code true} if this context's Precision property is in bits,
     * rather than digits; otherwise, {@code false}.. The default is false. {@code
     * true} if this context's Precision property is in bits, rather than digits;
     * otherwise, {@code false}. The default is false.</p>
     *
     */
    public final boolean isPrecisionInBits() {
        return this.precisionInBits;
      }

    /**
     * <p>Gets a value indicating whether to use a "simplified" arithmetic. In the
     * simplified arithmetic, infinity, not-a-number, and subnormal numbers are not
     * allowed, and negative zero is treated the same as positive zero. For further
     * details, see <a href="http://speleotrove.com/decimal/dax3274.html">{@code
     * http://speleotrove.com/decimal/dax3274.html}</a> .</p>
     *
     */
    public final boolean isSimplified() {
        return this.simplified;
      }

    /**
     * <p>Gets the maximum length of a converted number in digits, ignoring the
     * radix point and exponent. For example, if precision is 3, a converted
     * number's significand can range from 0 to 999 (up to three digits long). If
     * 0, converted numbers can have any precision. </p> <p>Not-a-number (NaN)
     * values can carry an optional number, its payload, that serves as its
     * "diagnostic information", In general, if an operation requires copying an
     * NaN's payload, only up to as many digits of that payload as the precision
     * given in this context, namely the least significant digits, are copied.</p>
     * @return <p>The maximum length of a converted number in digits, ignoring the
     * radix point and exponent. For example, if precision is 3, a converted
     * number's significand can range from 0 to 999 (up to three digits long). If
     * 0, converted numbers can have any precision.</p>
     *
     */
    public final EInteger getPrecision() {
        return this.bigintPrecision;
      }

    /**
     * <p>Gets the desired rounding mode when converting numbers that can't be
     * represented in the given precision and exponent range.</p>
     * @return <p>The desired rounding mode when converting numbers that can't be
     * represented in the given precision and exponent range.</p>
     *
     */
    public final ERounding getRounding() {
        return this.rounding;
      }

    /**
     * <p>Gets the traps that are set for each flag in the context. Whenever a flag
     * is signaled, even if {@code HasFlags} is false, and the flag's trap is
     * enabled, the operation will throw a TrapException. </p> <p>For example, if
     * Traps equals {@code FlagInexact} and FlagSubnormal, a TrapException will be
     * thrown if an operation's return value is not the same as the exact result
     * (FlagInexact) or if the return value's exponent is lower than the lowest
     * allowed (FlagSubnormal).</p>
     * @return <p>The traps that are set for each flag in the context. Whenever a
     * flag is signaled, even if {@code HasFlags} is false, and the flag's trap is
     * enabled, the operation will throw a TrapException. For example, if Traps
     * equals.</p>
     *
     */
    public final int getTraps() {
        return this.traps;
      }

    /**
     * <p>Creates a new arithmetic context using the given maximum number of
     * digits, an unlimited exponent range, and the HalfUp rounding mode.</p>
     * <p>@param precision </p>
     * <p>Maximum number of digits (precision).</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public static EContext ForPrecision(int precision) {
      return new EContext(
        precision,
        ERounding.HalfUp,
        0,
        0,
        false).WithUnlimitedExponents();
    }

    /**
     * <p>Creates a new EContext object initialized with an unlimited exponent
     * range, and the given rounding mode and maximum precision.</p>
     * <p>@param precision </p>
     * <p>Maximum number of digits (precision).</p>
     * <p>@param rounding </p>
     * <p>The parameter {@code rounding} is an ERounding
     * object.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public static EContext ForPrecisionAndRounding(
      int precision,
      ERounding rounding) {
      return new EContext(
        precision,
        rounding,
        0,
        0,
        false).WithUnlimitedExponents();
    }

    private static final EContext ForRoundingHalfEven = new EContext(
      0,
      ERounding.HalfEven,
      0,
      0,
      false).WithUnlimitedExponents();

    private static final EContext ForRoundingDown = new EContext(
      0,
      ERounding.Down,
      0,
      0,
      false).WithUnlimitedExponents();

    /**
     * <p>Creates a new EContext object initialized with an unlimited precision, an
     * unlimited exponent range, and the given rounding mode.</p>
     * <p>@param rounding </p>
     * <p>The rounding mode for the new precision context.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public static EContext ForRounding(ERounding rounding) {
      if (rounding == ERounding.HalfEven) {
        return ForRoundingHalfEven;
      }
      if (rounding == ERounding.Down) {
        return ForRoundingDown;
      }
      return new EContext(
        0,
        rounding,
        0,
        0,
        false).WithUnlimitedExponents();
    }

    /**
     * <p>Initializes a new EContext that is a copy of another EContext.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext Copy() {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    /**
     * <p>Determines whether a number can have the given Exponent property under
     * this arithmetic context.</p>
     * <p>@param exponent </p>
     * <p>An arbitrary-precision integer indicating the desired
     * exponent.</p>
     * @return <p> {@code true} if a number can have the given Exponent property
     * under this arithmetic context; otherwise, {@code false}. If this context
     * allows unlimited precision, returns true for the exponent EMax and any
     * exponent less than EMax.</p>
     * @throws NullPointerException <p>The parameter {@code exponent} is null.</p>
     *
     */
    public boolean ExponentWithinRange(EInteger exponent) {
      if (exponent == null) {
        throw new NullPointerException("exponent");
      }
      if (!this.getHasExponentRange()) {
        return true;
      }
      if (this.bigintPrecision.isZero()) {
        // Only check EMax, since with an unlimited
        // precision, any exponent less than EMin will exceed EMin if
        // the significand is the right size
        // TODO: In next major version, perhaps correct this to check
        // EMin here as well if AdjustExponent is true
        return exponent.compareTo(this.getEMax()) <= 0;
      } else {
        EInteger bigint = exponent;
        if (this.adjustExponent) {
          bigint = bigint.Add(this.bigintPrecision).Subtract(1);
        }
        return (bigint.compareTo(this.getEMin()) >= 0) &&
(exponent.compareTo(this.getEMax()) <= 0);
      }
    }

    /**
     * <p>Returns this object in a text form intended to be read by humans. The
     * value returned by this method is not intended to be parsed by computer
     * programs, and the exact text of the value may change at any time between
     * versions of this library.</p>
     * @return <p>A string representation of this object.</p>
     *
     */
    @Override public String toString() {
      return "[PrecisionContext ExponentMax=" + this.exponentMax +
        ", Traps=" + this.traps + ", ExponentMin=" + this.exponentMin +
        ", HasExponentRange=" + this.hasExponentRange + ", BigintPrecision=" +
        this.bigintPrecision + ", Rounding=" + this.rounding +
        ", ClampNormalExponents=" + this.clampNormalExponents +
        ", AdjustExponent=" + this.adjustExponent + ", Flags=" +
        this.flags + ", HasFlags=" + this.hasFlags + ", IsSimplified=" +
this.simplified +
        "]";
    }

    /**
     * <p>Gets a value indicating whether this context has a mutable Flags field,
     * one or more trap enablers, or both.</p>
     * @return <p> {@code true} if this context has a mutable Flags field, one or
     * more trap enablers, or both; otherwise, {@code false}.</p>
     *
     */
    public final boolean getHasFlagsOrTraps() {
        return this.getHasFlags() || this.getTraps() != 0;
      }

    /**
     * <p>Copies this EContext and sets the copy's "AdjustExponent" property to the
     * given value.</p>
     * <p>@param adjustExponent </p>
     *     <p>The new value of the "AdjustExponent" property for
     * the copy.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithAdjustExponent(boolean adjustExponent) {
      return new EContext(
        adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    /**
     * <p>Copies this arithmetic context and sets the copy's exponent range.</p>
     * <p>@param exponentMin </p>
     * <p>Desired minimum exponent (EMin).</p>
     * <p>@param exponentMax </p>
     * <p>Desired maximum exponent (EMax).</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     * @throws NullPointerException <p>The parameter {@code exponentMin} is
     * null.</p>
     * @throws IllegalArgumentException <p>ExponentMin greater than exponentMax".</p>
     *
     */
    public EContext WithBigExponentRange(
      EInteger exponentMin,
      EInteger exponentMax) {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        exponentMax,
        exponentMin,
        this.flags,
        true,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    /**
     * <p>Copies this EContext with {@code HasFlags} set to false, a Traps value of
     * 0, and a Flags value of 0.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithNoFlagsOrTraps() {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        0,
        this.hasExponentRange,
        false,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        0);
    }

    /**
     * <p>Copies this EContext and gives it a particular precision value.</p>
     * <p>@param bigintPrecision </p>
     * <p>Desired precision. 0 means unlimited
     * precision.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     * @throws NullPointerException <p>The parameter {@code bigintPrecision} is
     * null.</p>
     *
     */
    public EContext WithBigPrecision(EInteger bigintPrecision) {
      return new EContext(
        this.adjustExponent,
        bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    /**
     * <p>Copies this EContext with {@code HasFlags} set to true and a Flags value
     * of 0.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithBlankFlags() {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        0,
        this.hasExponentRange,
        true,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    /**
     * <p>Copies this arithmetic context and sets the copy's "ClampNormalExponents"
     * flag to the given value.</p>
     * <p>@param clamp </p>
     *     <p>The desired value of the "ClampNormalExponents" flag.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithExponentClamp(boolean clamp) {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        clamp,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    /**
     * <p>Copies this arithmetic context and sets the copy's exponent range.</p>
     * <p>@param exponentMinSmall </p>
     * <p>Desired minimum exponent (EMin).</p>
     * <p>@param exponentMaxSmall </p>
     * <p>Desired maximum exponent (EMax).</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithExponentRange(
      int exponentMinSmall,
      int exponentMaxSmall) {
      return this.WithBigExponentRange(
          EInteger.FromInt32(exponentMinSmall),
          EInteger.FromInt32(exponentMaxSmall));
    }

    /**
     * <p>Copies this EContext with {@code HasFlags} set to false and a Flags value
     * of 0.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithNoFlags() {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        0,
        this.hasExponentRange,
        false,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    /**
     * <p>Copies this EContext and gives it a particular precision value.</p>
     * <p>@param precision </p>
     * <p>Desired precision. 0 means unlimited precision.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithPrecision(int precision) {
      return this.WithBigPrecision(EInteger.FromInt32(precision));
    }

    /**
     * <p>Copies this EContext and sets the copy's "IsPrecisionInBits" property to
     * the given value.</p>
     * <p>@param isPrecisionBits </p>
     *     <p>The new value of the "IsPrecisionInBits" property
     * for the copy.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithPrecisionInBits(boolean isPrecisionBits) {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        isPrecisionBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    /**
     * <p>Copies this EContext with the specified rounding mode.</p>
     * <p>@param rounding </p>
     * <p>Desired value of the Rounding property.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithRounding(ERounding rounding) {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        rounding,
        this.simplified,
        this.traps);
    }

    /**
     * <p>Copies this EContext and sets the copy's "IsSimplified" property to the
     * given value.</p>
     * <p>@param simplified </p>
     * <p>Desired value of the IsSimplified property.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithSimplified(boolean simplified) {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        simplified,
        this.traps);
    }

    /**
     * <p>Copies this EContext with Traps set to the given value. (Also sets
     * HasFlags on the copy to {@code True}, but this may change in version 2.0 of
     * this library.).</p>
     * <p>@param traps </p>
     * <p>Flags representing the traps to enable. See the property
     * "Traps".</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithTraps(int traps) {
      // NOTE: Apparently HasFlags must be set to true because
      // some parts of code may treat HasFlags as HasFlagsOrTraps
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        true,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        traps);
    }

    /**
     * <p>Copies this EContext with an unlimited exponent range.</p>
     * @return <p>A context object for arbitrary-precision arithmetic settings.</p>
     *
     */
    public EContext WithUnlimitedExponents() {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        false,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

  /**
   * <p>Returns this context if it doesn't set traps, or a context without traps
   * and with blank flags if it does, so that the resulting context does not
   * cause trap exceptions to occur. This is not a general-purpose method; it is
   * intended to support custom implementations of arithmetic operations.</p>
   * @return <p>This context if it doesn't set traps, or a context without traps
   * and with blank flags if it does.</p>
   *
   */
    public EContext GetNontrapping() {
       return (this.getTraps() == 0) ? this : this.WithTraps(0).WithBlankFlags();
    }

  /**
   * <p>Throws trap exceptions if the given context has flags set that also have
   * traps enabled for them in this context, and adds the given context's flags
   * to this context if HasFlags for this context is true. This is not a
   * general-purpose method; it is intended to support custom implementations of
   * arithmetic operations.</p>
   * <p>@param result </p>
   * <p>The result of the operation.</p>
   * <p>@param trappableContext </p>
   * <p>An arithmetic context, usually a context returned
   * by the GetNontrapping method. Can be null.</p>
   * <p>@param <t> <p>Data type for the result of the operation.</p></t></p>
   * @return <p>The parameter {@code result} if no trap exceptions were
   * thrown.</p>
   *
   */
    public <T> T TriggerTraps(
      T result,
      EContext trappableContext) {
      if (trappableContext == null || trappableContext.getFlags() == 0) {
        return result;
      }
      if (this.getHasFlags()) {
        this.flags |= trappableContext.getFlags();
      }
      int traps = this.getTraps() & trappableContext.getFlags();
      if (traps == 0) {
        return result;
      }
      int mutexConditions = traps & (~(
            EContext.FlagClamped | EContext.FlagInexact |
            EContext.FlagRounded | EContext.FlagSubnormal));
      if (mutexConditions != 0) {
        for (int i = 0; i < 32; ++i) {
          int flag = mutexConditions & (1 << i);
          if (flag != 0) {
            throw new ETrapException(traps, flag, this, result);
          }
        }
      }
      if ((traps & EContext.FlagSubnormal) != 0) {
        throw new ETrapException(
          traps,
          traps & EContext.FlagSubnormal,
          this,
          result);
      }
      if ((traps & EContext.FlagInexact) != 0) {
        throw new ETrapException(
          traps,
          traps & EContext.FlagInexact,
          this,
          result);
      }
      if ((traps & EContext.FlagRounded) != 0) {
        throw new ETrapException(
          traps,
          traps & EContext.FlagRounded,
          this,
          result);
      }
      if ((traps & EContext.FlagClamped) != 0) {
        throw new ETrapException(
          traps,
          traps & EContext.FlagClamped,
          this,
          result);
      }
      return result;
    }
  }
