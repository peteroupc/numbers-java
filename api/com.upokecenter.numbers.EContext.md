# com.upokecenter.numbers.EContext

    public final class EContext extends Object

Contains parameters for controlling the precision, rounding, and exponent
 range of arbitrary-precision numbers. (The "E" stands for "extended",
 and has this prefix to group it with the other classes common to this
 library, particularly EDecimal, EFloat, and ERational.). <p><b>Thread
 safety:</b> With one exception, instances of this class are immutable
 and are safe to use among multiple threads. The one exception
 involves the <code>Flags</code> property. If the context's <code>HasFlags</code>
 property (a read-only property) is <code>true</code>, the <code>Flags</code>
 property is mutable, thus making the context mutable. This class
 doesn't synchronize access to such mutable contexts, so applications
 should provide their own synchronization if a context with the
 <code>HasFlags</code> property set to <code>true</code> will be shared among
 multiple threads and those threads need to read or write the
 <code>Flags</code> property (which can happen, for example, by passing the
 context to most methods of <code>EDecimal</code> such as <code>Add</code>).</p>

## Fields

* `static EContext Basic`<br>
 A basic arithmetic context, 9 digits precision, rounding mode half-up,
 unlimited exponent range.
* `static EContext BigDecimalJava`<br>
 An arithmetic context for Java's BigDecimal format.
* `static EContext Binary128`<br>
 An arithmetic context for the IEEE-754-2008 binary128 format, 113 bits
 precision.
* `static EContext Binary16`<br>
 An arithmetic context for the IEEE-754-2008 binary16 format, 11 bits
 precision.
* `static EContext Binary32`<br>
 An arithmetic context for the IEEE-754-2008 binary32 format, 24 bits
 precision.
* `static EContext Binary64`<br>
 An arithmetic context for the IEEE-754-2008 binary64 format, 53 bits
 precision.
* `static EContext CliDecimal "Forms of numbers"`<br>
 An arithmetic context for the .NET Framework decimal format (see "Forms of numbers"), 96 bits
 precision, and a valid exponent range of -28 to 0.
* `static EContext Decimal128`<br>
 An arithmetic context for the IEEE-754-2008 decimal128 format.
* `static EContext Decimal32`<br>
 An arithmetic context for the IEEE-754-2008 decimal32 format.
* `static EContext Decimal64`<br>
 An arithmetic context for the IEEE-754-2008 decimal64 format.
* `static int FlagClamped`<br>
 Signals that the exponent was adjusted to fit the exponent range.
* `static int FlagDivideByZero`<br>
 Signals a division of a nonzero number by zero.
* `static int FlagInexact`<br>
 Signals that the result was rounded to a different mathematical value, but
 as close as possible to the original.
* `static int FlagInvalid`<br>
 Signals an invalid operation.
* `static int FlagLostDigits`<br>
 Signals that an operand was rounded to a different mathematical value before
 an operation.
* `static int FlagOverflow`<br>
 Signals that the result is non-zero and the exponent is higher than the
 highest exponent allowed.
* `static int FlagRounded`<br>
 Signals that the result was rounded to fit the precision; either the value
 or the exponent may have changed from the original.
* `static int FlagSubnormal`<br>
 Signals that the result's exponent, before rounding, is lower than the
 lowest exponent allowed.
* `static int FlagUnderflow`<br>
 Signals that the result's exponent, before rounding, is lower than the
 lowest exponent allowed, and the result was rounded to a different
 mathematical value, but as close as possible to the original.
* `static EContext Unlimited`<br>
 No specific (theoretical) limit on precision.
* `static EContext UnlimitedHalfEven`<br>
 No specific (theoretical) limit on precision.

## Constructors

* `EContext(int precision,
        ERounding rounding,
        int exponentMinSmall,
        int exponentMaxSmall,
        boolean clampNormalExponents) EContext`<br>
 Initializes a new instance of the EContext
 class.

## Methods

* `EContext Copy()`<br>
 Initializes a new EContext that is a copy of another EContext.
* `boolean ExponentWithinRange(EInteger exponent)`<br>
 Determines whether a number can have the given Exponent property under this
 arithmetic context.
* `static EContext ForPrecision(int precision)`<br>
 Creates a new arithmetic context using the given maximum number of digits,
 an unlimited exponent range, and the HalfUp rounding mode.
* `static EContext ForPrecisionAndRounding(int precision,
                       ERounding rounding)`<br>
 Creates a new EContext object initialized with an unlimited exponent range,
 and the given rounding mode and maximum precision.
* `static EContext ForRounding(ERounding rounding)`<br>
 Creates a new EContext object initialized with an unlimited precision, an
 unlimited exponent range, and the given rounding mode.
* `boolean getAdjustExponent()`<br>
 Gets a value indicating whether the EMax and EMin properties refer to the
 number's Exponent property adjusted to the number's precision, or
 just the number's Exponent property.
* `boolean getClampNormalExponents()`<br>
 Gets a value indicating whether a converted number's Exponent property
 will not be higher than EMax + 1 - Precision.
* `EInteger getEMax()`<br>
 Gets the highest exponent possible when a converted number is expressed in
 scientific notation with one digit before the radix point.
* `EInteger getEMin()`<br>
 Gets the lowest exponent possible when a converted number is expressed in
 scientific notation with one digit before the radix point.
* `int getFlags()`<br>
 Gets the flags that are set from converting numbers according to this
 arithmetic context.
* `boolean getHasExponentRange()`<br>
 Gets a value indicating whether this context defines a minimum and maximum
 exponent.
* `boolean getHasFlags()`<br>
 Gets a value indicating whether this context has a mutable Flags field.
* `boolean getHasMaxPrecision()`<br>
 Gets a value indicating whether this context defines a maximum precision.
* `EInteger getPrecision()`<br>
 Gets the maximum length of a converted number in digits, ignoring the radix
 point and exponent.
* `ERounding getRounding()`<br>
 Gets the desired rounding mode when converting numbers that can't be
 represented in the given precision and exponent range.
* `int getTraps()`<br>
 Gets the traps that are set for each flag in the context.
* `boolean isPrecisionInBits()`<br>
 Gets a value indicating whether this context's Precision property is in
 bits, rather than digits.
* `boolean isSimplified()`<br>
 Gets a value indicating whether to use a "simplified" arithmetic.
* `void setFlags(int value)`<br>
* `String toString()`<br>
 Gets a string representation of this object.
* `EContext WithAdjustExponent(boolean adjustExponent)`<br>
 Copies this EContext and sets the copy's "AdjustExponent" property to the
 given value.
* `EContext WithBigExponentRange(EInteger exponentMin,
                    EInteger exponentMax)`<br>
 Copies this arithmetic context and sets the copy's exponent range.
* `EContext WithBigPrecision(EInteger bigintPrecision)`<br>
 Copies this EContext and gives it a particular precision value.
* `EContext WithBlankFlags() HasFlags`<br>
 Copies this EContext with HasFlags set to true and a Flags value of
 0.
* `EContext WithExponentClamp(boolean clamp)`<br>
 Copies this arithmetic context and sets the copy's "ClampNormalExponents"
 flag to the given value.
* `EContext WithExponentRange(int exponentMinSmall,
                 int exponentMaxSmall)`<br>
 Copies this arithmetic context and sets the copy's exponent range.
* `EContext WithNoFlags() HasFlags`<br>
 Copies this EContext with HasFlags set to false and a Flags value of
 0.
* `EContext WithPrecision(int precision)`<br>
 Copies this EContext and gives it a particular precision value.
* `EContext WithPrecisionInBits(boolean isPrecisionBits)`<br>
 Copies this EContext and sets the copy's "IsPrecisionInBits" property to the
 given value.
* `EContext WithRounding(ERounding rounding)`<br>
 Copies this EContext with the specified rounding mode.
* `EContext WithSimplified(boolean simplified)`<br>
 Copies this EContext and sets the copy's "IsSimplified" property to the
 given value.
* `EContext WithTraps(int traps)`<br>
 Copies this EContext with Traps set to the given value.
* `EContext WithUnlimitedExponents()`<br>
 Copies this EContext with an unlimited exponent range.

## Field Details

### FlagClamped
    public static final int FlagClamped
Signals that the exponent was adjusted to fit the exponent range.
### FlagDivideByZero
    public static final int FlagDivideByZero
Signals a division of a nonzero number by zero.
### FlagInexact
    public static final int FlagInexact
Signals that the result was rounded to a different mathematical value, but
 as close as possible to the original.
### FlagInvalid
    public static final int FlagInvalid
Signals an invalid operation.
### FlagLostDigits
    public static final int FlagLostDigits
Signals that an operand was rounded to a different mathematical value before
 an operation.
### FlagOverflow
    public static final int FlagOverflow
Signals that the result is non-zero and the exponent is higher than the
 highest exponent allowed.
### FlagRounded
    public static final int FlagRounded
Signals that the result was rounded to fit the precision; either the value
 or the exponent may have changed from the original.
### FlagSubnormal
    public static final int FlagSubnormal
Signals that the result&#x27;s exponent, before rounding, is lower than the
 lowest exponent allowed.
### FlagUnderflow
    public static final int FlagUnderflow
Signals that the result&#x27;s exponent, before rounding, is lower than the
 lowest exponent allowed, and the result was rounded to a different
 mathematical value, but as close as possible to the original.
### Basic
    public static final EContext Basic
A basic arithmetic context, 9 digits precision, rounding mode half-up,
 unlimited exponent range. The default rounding mode is HalfUp.
### BigDecimalJava
    public static final EContext BigDecimalJava
An arithmetic context for Java's BigDecimal format. The default rounding
 mode is HalfUp.
### Binary128
    public static final EContext Binary128
An arithmetic context for the IEEE-754-2008 binary128 format, 113 bits
 precision. The default rounding mode is HalfEven.
### Binary16
    public static final EContext Binary16
An arithmetic context for the IEEE-754-2008 binary16 format, 11 bits
 precision. The default rounding mode is HalfEven.
### Binary32
    public static final EContext Binary32
An arithmetic context for the IEEE-754-2008 binary32 format, 24 bits
 precision. The default rounding mode is HalfEven.
### Binary64
    public static final EContext Binary64
An arithmetic context for the IEEE-754-2008 binary64 format, 53 bits
 precision. The default rounding mode is HalfEven.
### CliDecimal
    public static final EContext CliDecimal
An arithmetic context for the .NET Framework decimal format (see <code>"Forms of numbers"</code>), 96 bits
 precision, and a valid exponent range of -28 to 0. The default
 rounding mode is HalfEven. (The <code>"Cli"</code> stands for "Common
 Language Infrastructure", which defined this format as the .NET
 Framework decimal format in version 1, but leaves it unspecified in
 later versions.).
### Decimal128
    public static final EContext Decimal128
An arithmetic context for the IEEE-754-2008 decimal128 format. The default
 rounding mode is HalfEven.
### Decimal32
    public static final EContext Decimal32
An arithmetic context for the IEEE-754-2008 decimal32 format. The default
 rounding mode is HalfEven.
### Decimal64
    public static final EContext Decimal64
An arithmetic context for the IEEE-754-2008 decimal64 format. The default
 rounding mode is HalfEven.
### Unlimited
    public static final EContext Unlimited
No specific (theoretical) limit on precision. Rounding mode HalfUp.
### UnlimitedHalfEven
    public static final EContext UnlimitedHalfEven
No specific (theoretical) limit on precision. Rounding mode HalfEven.
## Method Details

### getAdjustExponent
    public final boolean getAdjustExponent()
Gets a value indicating whether the EMax and EMin properties refer to the
 number's Exponent property adjusted to the number's precision, or
 just the number's Exponent property. The default value is true,
 meaning that EMax and EMin refer to the adjusted exponent. Setting
 this value to false (using WithAdjustExponent) is useful for modeling
 floating point representations with an integer mantissa (significand)
 and an integer exponent, such as Java's BigDecimal.

**Returns:**

* <code>true</code> if the EMax and EMin properties refer to the number's
 Exponent property adjusted to the number's precision, or false if
 they refer to just the number's Exponent property.

### getClampNormalExponents
    public final boolean getClampNormalExponents()
Gets a value indicating whether a converted number&#x27;s Exponent property
 will not be higher than EMax + 1 - Precision. If a number&#x27;s
 exponent is higher than that value, but not high enough to cause
 overflow, the exponent is clamped to that value and enough zeros are
 added to the number&#x27;s mantissa (significand) to account for the
 adjustment. If HasExponentRange is false, this value is always false.

**Returns:**

* If true, a converted number's Exponent property will not be higher
 than EMax + 1 - Precision.

### getEMax
    public final EInteger getEMax()
Gets the highest exponent possible when a converted number is expressed in
 scientific notation with one digit before the radix point. For
 example, with a precision of 3 and an EMax of 100, the maximum value
 possible is 9.99E + 100. (This is not the same as the highest
 possible Exponent property.) If HasExponentRange is false, this value
 will be 0.

**Returns:**

* The highest exponent possible when a converted number is expressed
 in scientific notation with one digit before the decimal point. For
 example, with a precision of 3 and an EMax of 100, the maximum value
 possible is 9.99E + 100. (This is not the same as the highest
 possible Exponent property.) If HasExponentRange is false, this value
 will be 0.

### getEMin
    public final EInteger getEMin()
Gets the lowest exponent possible when a converted number is expressed in
 scientific notation with one digit before the radix point. For
 example, with a precision of 3 and an EMin of -100, the next value
 that comes after 0 is 0.001E-100. (If AdjustExponent is false, this
 property specifies the lowest possible Exponent property instead.) If
 HasExponentRange is false, this value will be 0.

**Returns:**

* The lowest exponent possible when a converted number is expressed in
 scientific notation with one digit before the decimal point.

### getFlags
    public final int getFlags()
Gets the flags that are set from converting numbers according to this
 arithmetic context. If <code>HasFlags</code> is false, this value will be
 0. This value is a combination of bit fields. To retrieve a
 particular flag, use the AND operation on the return value of this
 method. For example: <code>(this.getFlags() &amp; EContext.FlagInexact) !=
 0</code> returns <code>true</code> if the Inexact flag is set.

**Returns:**

* The flags that are set from converting numbers according to this
 arithmetic context. If. <code>HasFlags</code> is false, this value will be
 0.

### setFlags
    public final void setFlags(int value)
### getHasExponentRange
    public final boolean getHasExponentRange()
Gets a value indicating whether this context defines a minimum and maximum
 exponent. If false, converted exponents can have any exponent and
 operations can't cause overflow or underflow.

**Returns:**

* <code>true</code> if this context defines a minimum and maximum exponent;
 otherwise, <code>false</code>.

### getHasFlags
    public final boolean getHasFlags()
Gets a value indicating whether this context has a mutable Flags field.

**Returns:**

* <code>true</code> if this context has a mutable Flags field; otherwise,
 <code>false</code>.

### getHasMaxPrecision
    public final boolean getHasMaxPrecision()
Gets a value indicating whether this context defines a maximum precision.

**Returns:**

* <code>true</code> if this context defines a maximum precision; otherwise,
 <code>false</code>.

### isPrecisionInBits
    public final boolean isPrecisionInBits()
Gets a value indicating whether this context's Precision property is in
 bits, rather than digits. The default is false.

**Returns:**

* <code>true</code> if this context's Precision property is in bits, rather
 than digits; otherwise, <code>false</code>. The default is false.

### isSimplified
    public final boolean isSimplified()
Gets a value indicating whether to use a "simplified" arithmetic. In the
 simplified arithmetic, infinity, not-a-number, and subnormal numbers
 are not allowed, and negative zero is treated the same as positive
 zero. For further details, see
 <code>http://speleotrove.com/decimal/dax3274.html</code>

**Returns:**

* <code>true</code> if a "simplified" arithmetic will be used; otherwise,
 <code>false</code>.

### getPrecision
    public final EInteger getPrecision()
Gets the maximum length of a converted number in digits, ignoring the radix
 point and exponent. For example, if precision is 3, a converted
 number&#x27;s mantissa (significand) can range from 0 to 999 (up to
 three digits long). If 0, converted numbers can have any precision.

**Returns:**

* The maximum length of a converted number in digits, ignoring the
 radix point and exponent.

### getRounding
    public final ERounding getRounding()
Gets the desired rounding mode when converting numbers that can&#x27;t be
 represented in the given precision and exponent range.

**Returns:**

* The desired rounding mode when converting numbers that can't be
 represented in the given precision and exponent range.

### getTraps
    public final int getTraps()
Gets the traps that are set for each flag in the context. Whenever a flag is
 signaled, even if <code>HasFlags</code> is false, and the flag's trap is
 enabled, the operation will throw a TrapException. <p>For example, if
 Traps equals <code>FlagInexact</code> and FlagSubnormal, a TrapException
 will be thrown if an operation's return value is not the same as the
 exact result (FlagInexact) or if the return value's exponent is lower
 than the lowest allowed (FlagSubnormal).</p>

**Returns:**

* The traps that are set for each flag in the context.

### ForPrecision
    public static EContext ForPrecision(int precision)
Creates a new arithmetic context using the given maximum number of digits,
 an unlimited exponent range, and the HalfUp rounding mode.

**Parameters:**

* <code>precision</code> - Maximum number of digits (precision).

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### ForPrecisionAndRounding
    public static EContext ForPrecisionAndRounding(int precision, ERounding rounding)
Creates a new EContext object initialized with an unlimited exponent range,
 and the given rounding mode and maximum precision.

**Parameters:**

* <code>precision</code> - Maximum number of digits (precision).

* <code>rounding</code> - An ERounding object.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### ForRounding
    public static EContext ForRounding(ERounding rounding)
Creates a new EContext object initialized with an unlimited precision, an
 unlimited exponent range, and the given rounding mode.

**Parameters:**

* <code>rounding</code> - The rounding mode for the new precision context.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### Copy
    public EContext Copy()
Initializes a new EContext that is a copy of another EContext.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### ExponentWithinRange
    public boolean ExponentWithinRange(EInteger exponent)
Determines whether a number can have the given Exponent property under this
 arithmetic context.

**Parameters:**

* <code>exponent</code> - An arbitrary-precision integer indicating the desired
 exponent.

**Returns:**

* <code>true</code> if a number can have the given Exponent property under
 this arithmetic context; otherwise, <code>false</code>. If this context
 allows unlimited precision, returns true for the exponent EMax and
 any exponent less than EMax.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>exponent</code> is null.

### toString
    public String toString()
Gets a string representation of this object. Note that the format is not
 intended to be parsed and may change at any time.

**Overrides:**

* <code>toString</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A string representation of this object.

### WithAdjustExponent
    public EContext WithAdjustExponent(boolean adjustExponent)
Copies this EContext and sets the copy's "AdjustExponent" property to the
 given value.

**Parameters:**

* <code>adjustExponent</code> - The new value of the "AdjustExponent" property for the
 copy.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### WithBigExponentRange
    public EContext WithBigExponentRange(EInteger exponentMin, EInteger exponentMax)
Copies this arithmetic context and sets the copy's exponent range.

**Parameters:**

* <code>exponentMin</code> - Desired minimum exponent (EMin).

* <code>exponentMax</code> - Desired maximum exponent (EMax).

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>exponentMin</code> is
 null.

* <code>NullPointerException</code> - The parameter <code>exponentMax</code> is
 null.

### WithBigPrecision
    public EContext WithBigPrecision(EInteger bigintPrecision)
Copies this EContext and gives it a particular precision value.

**Parameters:**

* <code>bigintPrecision</code> - The parameter <code>bigintPrecision</code> is not
 documented yet.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>bigintPrecision</code>
 is null.

### WithBlankFlags
    public EContext WithBlankFlags()
Copies this EContext with <code>HasFlags</code> set to true and a Flags value of
 0.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### WithExponentClamp
    public EContext WithExponentClamp(boolean clamp)
Copies this arithmetic context and sets the copy's "ClampNormalExponents"
 flag to the given value.

**Parameters:**

* <code>clamp</code> - The parameter <code>clamp</code> is not documented yet.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### WithExponentRange
    public EContext WithExponentRange(int exponentMinSmall, int exponentMaxSmall)
Copies this arithmetic context and sets the copy&#x27;s exponent range.

**Parameters:**

* <code>exponentMinSmall</code> - Desired minimum exponent (EMin).

* <code>exponentMaxSmall</code> - Desired maximum exponent (EMax).

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### WithNoFlags
    public EContext WithNoFlags()
Copies this EContext with <code>HasFlags</code> set to false and a Flags value of
 0.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### WithPrecision
    public EContext WithPrecision(int precision)
Copies this EContext and gives it a particular precision value.

**Parameters:**

* <code>precision</code> - Desired precision. 0 means unlimited precision.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### WithPrecisionInBits
    public EContext WithPrecisionInBits(boolean isPrecisionBits)
Copies this EContext and sets the copy's "IsPrecisionInBits" property to the
 given value.

**Parameters:**

* <code>isPrecisionBits</code> - The new value of the "IsPrecisionInBits" property for
 the copy.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### WithRounding
    public EContext WithRounding(ERounding rounding)
Copies this EContext with the specified rounding mode.

**Parameters:**

* <code>rounding</code> - Desired value of the Rounding property.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### WithSimplified
    public EContext WithSimplified(boolean simplified)
Copies this EContext and sets the copy's "IsSimplified" property to the
 given value.

**Parameters:**

* <code>simplified</code> - The parameter <code>simplified</code> is not documented yet.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### WithTraps
    public EContext WithTraps(int traps)
Copies this EContext with Traps set to the given value.

**Parameters:**

* <code>traps</code> - Flags representing the traps to enable. See the property
 "Traps".

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### WithUnlimitedExponents
    public EContext WithUnlimitedExponents()
Copies this EContext with an unlimited exponent range.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.
