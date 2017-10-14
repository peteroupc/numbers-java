package com.upokecenter.numbers;
/*
Written by Peter O. in 2013.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

    /**
     * Contains parameters for controlling the precision, rounding, and exponent
     * range of arbitrary-precision numbers. (The "E" stands for "extended",
     * and has this prefix to group it with the other classes common to this
     * library, particularly EDecimal, EFloat, and ERational.). <p><b>Thread
     * safety:</b> With one exception, instances of this class are immutable
     * and are safe to use among multiple threads. The one exception
     * involves the <code>Flags</code> property. If the context's <code>HasFlags</code>
     * property (a read-only property) is <code>true</code>, the <code>Flags</code>
     * property is mutable, thus making the context mutable. This class
     * doesn't synchronize access to such mutable contexts, so applications
     * should provide their own synchronization if a context with the
     * <code>HasFlags</code> property set to <code>true</code> will be shared among
     * multiple threads and at least one of those threads needs to write the
     * <code>Flags</code> property (which can happen, for example, by passing the
     * context to most methods of <code>EDecimal</code> such as <code>Add</code>).</p>
     */
  public final class EContext {
    /**
     * Signals that the exponent was adjusted to fit the exponent range.
     */
    public static final int FlagClamped = 32;

    /**
     * Signals a division of a nonzero number by zero.
     */
    public static final int FlagDivideByZero = 128;

    /**
     * Signals that the result was rounded to a different mathematical value, but
     * as close as possible to the original.
     */
    public static final int FlagInexact = 1;

    /**
     * Signals an invalid operation.
     */
    public static final int FlagInvalid = 64;

    /**
     * Signals that an operand was rounded to a different mathematical value before
     * an operation.
     */
    public static final int FlagLostDigits = 256;

    /**
     * Signals that the result is non-zero and the exponent is higher than the
     * highest exponent allowed.
     */
    public static final int FlagOverflow = 16;

    /**
     * Signals that the result was rounded to fit the precision; either the value
     * or the exponent may have changed from the original.
     */
    public static final int FlagRounded = 2;

    /**
     * Signals that the result&#x27;s exponent, before rounding, is lower than the
     * lowest exponent allowed.
     */
    public static final int FlagSubnormal = 4;

    /**
     * Signals that the result&#x27;s exponent, before rounding, is lower than the
     * lowest exponent allowed, and the result was rounded to a different
     * mathematical value, but as close as possible to the original.
     */
    public static final int FlagUnderflow = 8;

    /**
     * A basic arithmetic context, 9 digits precision, rounding mode half-up,
     * unlimited exponent range. The default rounding mode is HalfUp.
     */

    public static final EContext Basic =
      EContext.ForPrecisionAndRounding(9, ERounding.HalfUp);

    /**
     * An arithmetic context for Java's BigDecimal format. The default rounding
     * mode is HalfUp.
     */

    public static final EContext BigDecimalJava =
      new EContext(0, ERounding.HalfUp, 0, 0, true)
      .WithExponentClamp(true).WithAdjustExponent(false)
      .WithBigExponentRange(
  EInteger.FromInt32(0).Subtract(EInteger.FromInt64(Integer.MAX_VALUE)),
  EInteger.FromInt32(1).Add(EInteger.FromInt64(Integer.MAX_VALUE)));

    /**
     * An arithmetic context for the IEEE-754-2008 binary128 format, 113 bits
     * precision. The default rounding mode is HalfEven.
     */

    public static final EContext Binary128 =
      EContext.ForPrecisionAndRounding(113, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-16382, 16383);

    /**
     * An arithmetic context for the IEEE-754-2008 binary16 format, 11 bits
     * precision. The default rounding mode is HalfEven.
     */

    public static final EContext Binary16 =
      EContext.ForPrecisionAndRounding(11, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-14, 15);

    /**
     * An arithmetic context for the IEEE-754-2008 binary32 format, 24 bits
     * precision. The default rounding mode is HalfEven.
     */

    public static final EContext Binary32 =
      EContext.ForPrecisionAndRounding(24, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-126, 127);

    /**
     * An arithmetic context for the IEEE-754-2008 binary64 format, 53 bits
     * precision. The default rounding mode is HalfEven.
     */

    public static final EContext Binary64 =
      EContext.ForPrecisionAndRounding(53, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-1022, 1023);

    /**
     * An arithmetic context for the .NET Framework decimal format (see {@link
     * com.upokecenter.numbers.EDecimal "Forms of numbers"}), 96 bits
     * precision, and a valid exponent range of -28 to 0. The default
     * rounding mode is HalfEven. (The <code>"Cli"</code> stands for "Common
     * Language Infrastructure", which defined this format as the .NET
     * Framework decimal format in version 1, but leaves it unspecified in
     * later versions.).
     */

    public static final EContext CliDecimal =
      new EContext(96, ERounding.HalfEven, 0, 28, true)
      .WithPrecisionInBits(true);

    /**
     * An arithmetic context for the IEEE-754-2008 decimal128 format. The default
     * rounding mode is HalfEven.
     */

    public static final EContext Decimal128 =
      new EContext(34, ERounding.HalfEven, -6143, 6144, true);

    /**
     * An arithmetic context for the IEEE-754-2008 decimal32 format. The default
     * rounding mode is HalfEven.
     */

    public static final EContext Decimal32 =
      new EContext(7, ERounding.HalfEven, -95, 96, true);

    /**
     * An arithmetic context for the IEEE-754-2008 decimal64 format. The default
     * rounding mode is HalfEven.
     */

    public static final EContext Decimal64 =
      new EContext(16, ERounding.HalfEven, -383, 384, true);

    /**
     * No specific (theoretical) limit on precision. Rounding mode HalfUp.
     */

    public static final EContext Unlimited =
      EContext.ForPrecision(0);

    /**
     * No specific (theoretical) limit on precision. Rounding mode HalfEven.
     */

    public static final EContext UnlimitedHalfEven =
      EContext.ForPrecision(0).WithRounding(ERounding.HalfEven);

    private boolean adjustExponent;

    private EInteger bigintPrecision;

    private boolean clampNormalExponents;
    private EInteger exponentMax;

    private EInteger exponentMin;

    private int flags;

    private boolean hasExponentRange;
    private boolean hasFlags;

    private boolean precisionInBits;

    private ERounding rounding;

    private boolean simplified;

    private int traps;

    /**
     * Initializes a new instance of the {@link com.upokecenter.numbers.EContext}
     * class. <code>HasFlags</code> will be set to false.
     * @param precision The maximum number of digits a number can have, or 0 for an
     * unlimited number of digits.
     * @param rounding The rounding mode to use when a number can't fit the given
     * precision.
     * @param exponentMinSmall The minimum exponent.
     * @param exponentMaxSmall The maximum exponent.
     * @param clampNormalExponents Whether to clamp a number's significand to the
     * given maximum precision (if it isn't zero) while remaining within the
     * exponent range.
     */
    public EContext(
  int precision,
  ERounding rounding,
  int exponentMinSmall,
  int exponentMaxSmall,
  boolean clampNormalExponents) {
      if (precision < 0) {
        throw new IllegalArgumentException("precision (" + precision +
          ") is less than 0");
      }
      if (exponentMinSmall > exponentMaxSmall) {
        throw new IllegalArgumentException("exponentMinSmall (" + exponentMinSmall +
          ") is more than " + exponentMaxSmall);
      }
      this.bigintPrecision = precision == 0 ? EInteger.FromInt32(0) :
        EInteger.FromInt32(precision);
      this.rounding = rounding;
      this.clampNormalExponents = clampNormalExponents;
      this.hasExponentRange = true;
      this.adjustExponent = true;
      this.exponentMax = exponentMaxSmall == 0 ? EInteger.FromInt32(0) :
        EInteger.FromInt32(exponentMaxSmall);
      this.exponentMin = exponentMinSmall == 0 ? EInteger.FromInt32(0) :
        EInteger.FromInt32(exponentMinSmall);
    }

    /**
     * Gets a value indicating whether the EMax and EMin properties refer to the
     * number's Exponent property adjusted to the number's precision, or
     * just the number's Exponent property. The default value is true,
     * meaning that EMax and EMin refer to the adjusted exponent. Setting
     * this value to false (using WithAdjustExponent) is useful for modeling
     * floating point representations with an integer mantissa (significand)
     * and an integer exponent, such as Java's BigDecimal.
     * @return {@code true} if the EMax and EMin properties refer to the number's
     * Exponent property adjusted to the number's precision, or false if
     * they refer to just the number's Exponent property.
     */
    public final boolean getAdjustExponent() {
        return this.adjustExponent;
      }

    /**
     * Gets a value indicating whether a converted number&#x27;s Exponent property
     * will not be higher than EMax + 1 - Precision. If a number&#x27;s
     * exponent is higher than that value, but not high enough to cause
     * overflow, the exponent is clamped to that value and enough zeros are
     * added to the number&#x27;s mantissa (significand) to account for the
     * adjustment. If HasExponentRange is false, this value is always false.
     * @return If true, a converted number's Exponent property will not be higher
     * than EMax + 1 - Precision.
     */
    public final boolean getClampNormalExponents() {
        return this.hasExponentRange && this.clampNormalExponents;
      }

    /**
     * Gets the highest exponent possible when a converted number is expressed in
     * scientific notation with one digit before the radix point. For
     * example, with a precision of 3 and an EMax of 100, the maximum value
     * possible is 9.99E + 100. (This is not the same as the highest
     * possible Exponent property.) If HasExponentRange is false, this value
     * will be 0.
     * @return The highest exponent possible when a converted number is expressed
     * in scientific notation with one digit before the decimal point. For
     * example, with a precision of 3 and an EMax of 100, the maximum value
     * possible is 9.99E + 100. (This is not the same as the highest
     * possible Exponent property.) If HasExponentRange is false, this value
     * will be 0.
     */
    public final EInteger getEMax() {
        return this.hasExponentRange ? this.exponentMax : EInteger.FromInt32(0);
      }

    /**
     * Gets the lowest exponent possible when a converted number is expressed in
     * scientific notation with one digit before the radix point. For
     * example, with a precision of 3 and an EMin of -100, the next value
     * that comes after 0 is 0.001E-100. (If AdjustExponent is false, this
     * property specifies the lowest possible Exponent property instead.) If
     * HasExponentRange is false, this value will be 0.
     * @return The lowest exponent possible when a converted number is expressed in
     * scientific notation with one digit before the decimal point.
     */
    public final EInteger getEMin() {
        return this.hasExponentRange ? this.exponentMin : EInteger.FromInt32(0);
      }

    /**
     * Gets the flags that are set from converting numbers according to this
     * arithmetic context. If <code>HasFlags</code> is false, this value will be
     * 0. This value is a combination of bit fields. To retrieve a
     * particular flag, use the AND operation on the return value of this
     * method. For example: <code>(this.getFlags() &amp; EContext.FlagInexact) !=
     * 0</code> returns <code>true</code> if the Inexact flag is set.
     * @return The flags that are set from converting numbers according to this
     * arithmetic context. If. {@code HasFlags} is false, this value will be
     * 0.
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
     * Gets a value indicating whether this context defines a minimum and maximum
     * exponent. If false, converted exponents can have any exponent and
     * operations can't cause overflow or underflow.
     * @return {@code true} if this context defines a minimum and maximum exponent;
     * otherwise, {@code false}.
     */
    public final boolean getHasExponentRange() {
        return this.hasExponentRange;
      }

    /**
     * Gets a value indicating whether this context has a mutable Flags field.
     * @return {@code true} if this context has a mutable Flags field; otherwise,
     * {@code false}.
     */
    public final boolean getHasFlags() {
        return this.hasFlags;
      }

    /**
     * Gets a value indicating whether this context defines a maximum precision.
     * @return {@code true} if this context defines a maximum precision; otherwise,
     * {@code false}.
     */
    public final boolean getHasMaxPrecision() {
        return !this.bigintPrecision.isZero();
      }

    /**
     * Gets a value indicating whether this context's Precision property is in
     * bits, rather than digits. The default is false.
     * @return {@code true} if this context's Precision property is in bits, rather
     * than digits; otherwise, {@code false}. The default is false.
     */
    public final boolean isPrecisionInBits() {
        return this.precisionInBits;
      }

    /**
     * Gets a value indicating whether to use a "simplified" arithmetic. In the
     * simplified arithmetic, infinity, not-a-number, and subnormal numbers
     * are not allowed, and negative zero is treated the same as positive
     * zero. For further details, see <a
     * href='http://speleotrove.com/decimal/dax3274.html'>
     * <code>http://speleotrove.com/decimal/dax3274.html</code></a>
     * @return {@code true} if a "simplified" arithmetic will be used; otherwise,
     * {@code false}.
     */
    public final boolean isSimplified() {
        return this.simplified;
      }

    /**
     * Gets the maximum length of a converted number in digits, ignoring the radix
     * point and exponent. For example, if precision is 3, a converted
     * number&#x27;s mantissa (significand) can range from 0 to 999 (up to
     * three digits long). If 0, converted numbers can have any precision.
     * @return The maximum length of a converted number in digits, ignoring the
     * radix point and exponent.
     */
    public final EInteger getPrecision() {
        return this.bigintPrecision;
      }

    /**
     * Gets the desired rounding mode when converting numbers that can&#x27;t be
     * represented in the given precision and exponent range.
     * @return The desired rounding mode when converting numbers that can't be
     * represented in the given precision and exponent range.
     */
    public final ERounding getRounding() {
        return this.rounding;
      }

    /**
     * Gets the traps that are set for each flag in the context. Whenever a flag is
     * signaled, even if <code>HasFlags</code> is false, and the flag's trap is
     * enabled, the operation will throw a TrapException. <p>For example, if
     * Traps equals <code>FlagInexact</code> and FlagSubnormal, a TrapException
     * will be thrown if an operation's return value is not the same as the
     * exact result (FlagInexact) or if the return value's exponent is lower
     * than the lowest allowed (FlagSubnormal).</p>
     * @return The traps that are set for each flag in the context.
     */
    public final int getTraps() {
        return this.traps;
      }

    /**
     * Creates a new arithmetic context using the given maximum number of digits,
     * an unlimited exponent range, and the HalfUp rounding mode.
     * @param precision Maximum number of digits (precision).
     * @return A context object for arbitrary-precision arithmetic settings.
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
     * Creates a new EContext object initialized with an unlimited exponent range,
     * and the given rounding mode and maximum precision.
     * @param precision Maximum number of digits (precision).
     * @param rounding The parameter {@code rounding} is an ERounding object.
     * @return A context object for arbitrary-precision arithmetic settings.
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
     * Creates a new EContext object initialized with an unlimited precision, an
     * unlimited exponent range, and the given rounding mode.
     * @param rounding The rounding mode for the new precision context.
     * @return A context object for arbitrary-precision arithmetic settings.
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
     * Initializes a new EContext that is a copy of another EContext.
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext Copy() {
      EContext pcnew = new EContext(
        0,
        this.rounding,
        0,
        0,
        this.clampNormalExponents);
      pcnew.hasFlags = this.hasFlags;
      pcnew.precisionInBits = this.precisionInBits;
      pcnew.adjustExponent = this.adjustExponent;
      pcnew.simplified = this.simplified;
      pcnew.flags = this.flags;
      pcnew.exponentMax = this.exponentMax;
      pcnew.exponentMin = this.exponentMin;
      pcnew.hasExponentRange = this.hasExponentRange;
      pcnew.bigintPrecision = this.bigintPrecision;
      pcnew.rounding = this.rounding;
      pcnew.clampNormalExponents = this.clampNormalExponents;
      return pcnew;
    }

    /**
     * Determines whether a number can have the given Exponent property under this
     * arithmetic context.
     * @param exponent An arbitrary-precision integer indicating the desired
     * exponent.
     * @return {@code true} if a number can have the given Exponent property under
     * this arithmetic context; otherwise, {@code false}. If this context
     * allows unlimited precision, returns true for the exponent EMax and
     * any exponent less than EMax.
     * @throws java.lang.NullPointerException The parameter {@code exponent} is null.
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
        // the mantissa is the right size
        return exponent.compareTo(this.getEMax()) <= 0;
      } else {
        EInteger bigint = exponent;
        if (this.adjustExponent) {
          bigint = bigint.Add(this.bigintPrecision);
          bigint = bigint.Subtract(EInteger.FromInt32(1));
        }
        return (bigint.compareTo(this.getEMin()) >= 0) &&
          (exponent.compareTo(this.getEMax()) <= 0);
      }
    }

    /**
     * Gets a string representation of this object. Note that the format is not
     * intended to be parsed and may change at any time.
     * @return A string representation of this object.
     */
    @Override public String toString() {
      return "[PrecisionContext ExponentMax=" + this.exponentMax +
        ", Traps=" + this.traps + ", ExponentMin=" + this.exponentMin +
        ", HasExponentRange=" + this.hasExponentRange + ", BigintPrecision=" +
        this.bigintPrecision + ", Rounding=" + this.rounding +
        ", ClampNormalExponents=" + this.clampNormalExponents + ", Flags=" +
        this.flags + ", HasFlags=" + this.hasFlags + "]";
    }

    /**
     * Copies this EContext and sets the copy's "AdjustExponent" property to the
     * given value.
     * @param adjustExponent The new value of the "AdjustExponent" property for the
     * copy.
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext WithAdjustExponent(boolean adjustExponent) {
      EContext pc = this.Copy();
      pc.adjustExponent = adjustExponent;
      return pc;
    }

    /**
     * Copies this arithmetic context and sets the copy's exponent range.
     * @param exponentMin Desired minimum exponent (EMin).
     * @param exponentMax Desired maximum exponent (EMax).
     * @return A context object for arbitrary-precision arithmetic settings.
     * @throws java.lang.NullPointerException The parameter {@code exponentMin} is
     * null.
     * @throws java.lang.NullPointerException The parameter {@code exponentMax} is
     * null.
     */
    public EContext WithBigExponentRange(
      EInteger exponentMin,
      EInteger exponentMax) {
      if (exponentMin == null) {
        throw new NullPointerException("exponentMin");
      }
      if (exponentMax == null) {
        throw new NullPointerException("exponentMax");
      }
      if (exponentMin.compareTo(exponentMax) > 0) {
        throw new IllegalArgumentException("exponentMin greater than exponentMax");
      }
      EContext pc = this.Copy();
      pc.hasExponentRange = true;
      pc.exponentMin = exponentMin;
      pc.exponentMax = exponentMax;
      return pc;
    }

    /**
     * Copies this EContext and gives it a particular precision value.
     * @param bigintPrecision Desired precision. 0 means unlimited precision.
     * @return A context object for arbitrary-precision arithmetic settings.
     * @throws java.lang.NullPointerException The parameter {@code bigintPrecision}
     * is null.
     */
    public EContext WithBigPrecision(EInteger bigintPrecision) {
      if (bigintPrecision == null) {
        throw new NullPointerException("bigintPrecision");
      }
      if (bigintPrecision.signum() < 0) {
        throw new IllegalArgumentException("bigintPrecision's sign (" +
          bigintPrecision.signum() + ") is less than 0");
      }
      EContext pc = this.Copy();
      pc.bigintPrecision = bigintPrecision;
      return pc;
    }

    /**
     * Copies this EContext with <code>HasFlags</code> set to true and a Flags value of
     * 0.
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext WithBlankFlags() {
      EContext pc = this.Copy();
      pc.hasFlags = true;
      pc.flags = 0;
      return pc;
    }

    /**
     * Copies this arithmetic context and sets the copy's "ClampNormalExponents"
     * flag to the given value.
     * @param clamp The desired value of the "ClampNormalExponents" flag.
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext WithExponentClamp(boolean clamp) {
      EContext pc = this.Copy();
      pc.clampNormalExponents = clamp;
      return pc;
    }

    /**
     * Copies this arithmetic context and sets the copy&#x27;s exponent range.
     * @param exponentMinSmall Desired minimum exponent (EMin).
     * @param exponentMaxSmall Desired maximum exponent (EMax).
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext WithExponentRange(
      int exponentMinSmall,
      int exponentMaxSmall) {
      if (exponentMinSmall > exponentMaxSmall) {
        throw new IllegalArgumentException("exponentMinSmall (" + exponentMinSmall +
          ") is more than " + exponentMaxSmall);
      }
      EContext pc = this.Copy();
      pc.hasExponentRange = true;
      pc.exponentMin = EInteger.FromInt32(exponentMinSmall);
      pc.exponentMax = EInteger.FromInt32(exponentMaxSmall);
      return pc;
    }

    /**
     * Copies this EContext with <code>HasFlags</code> set to false and a Flags value of
     * 0.
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext WithNoFlags() {
      EContext pc = this.Copy();
      pc.hasFlags = false;
      pc.flags = 0;
      return pc;
    }

    /**
     * Copies this EContext and gives it a particular precision value.
     * @param precision Desired precision. 0 means unlimited precision.
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext WithPrecision(int precision) {
      if (precision < 0) {
        throw new IllegalArgumentException("precision (" + precision +
          ") is less than 0");
      }
      EContext pc = this.Copy();
      pc.bigintPrecision = EInteger.FromInt32(precision);
      return pc;
    }

    /**
     * Copies this EContext and sets the copy's "IsPrecisionInBits" property to the
     * given value.
     * @param isPrecisionBits The new value of the "IsPrecisionInBits" property for
     * the copy.
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext WithPrecisionInBits(boolean isPrecisionBits) {
      EContext pc = this.Copy();
      pc.precisionInBits = isPrecisionBits;
      return pc;
    }

    /**
     * Copies this EContext with the specified rounding mode.
     * @param rounding Desired value of the Rounding property.
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext WithRounding(ERounding rounding) {
      EContext pc = this.Copy();
      pc.rounding = rounding;
      return pc;
    }

    /**
     * Copies this EContext and sets the copy's "IsSimplified" property to the
     * given value.
     * @param simplified Desired value of the IsSimplified property.
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext WithSimplified(boolean simplified) {
      EContext pc = this.Copy();
      pc.simplified = simplified;
      return pc;
    }

    /**
     * Copies this EContext with Traps set to the given value.
     * @param traps Flags representing the traps to enable. See the property
     * "Traps".
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext WithTraps(int traps) {
      EContext pc = this.Copy();
      pc.hasFlags = true;
      pc.traps = traps;
      return pc;
    }

    /**
     * Copies this EContext with an unlimited exponent range.
     * @return A context object for arbitrary-precision arithmetic settings.
     */
    public EContext WithUnlimitedExponents() {
      EContext pc = this.Copy();
      pc.hasExponentRange = false;
      return pc;
    }
  }
