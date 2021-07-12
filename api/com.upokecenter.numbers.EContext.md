# com.upokecenter.numbers.EContext

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
 An arithmetic context for the.NET Framework decimal format (see "Forms of numbers"), 96 bits
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

* `EContext​(int precision,
ERounding rounding,
int exponentMinSmall,
int exponentMaxSmall,
boolean clampNormalExponents) EContext`<br>
 Initializes a new instance of the EContext
 class.
* `EContext​(EInteger bigintPrecision,
ERounding rounding,
EInteger exponentMin,
EInteger exponentMax,
boolean clampNormalExponents) EContext`<br>
 Initializes a new instance of the EContext
 class, .

## Methods

* `EContext Copy()`<br>
 Initializes a new EContext that is a copy of another EContext.
* `boolean ExponentWithinRange​(EInteger exponent)`<br>
 Determines whether a number can have the given Exponent property under this
 arithmetic context.
* `static EContext ForPrecision​(int precision)`<br>
 Creates a new arithmetic context using the given maximum number of digits,
 an unlimited exponent range, and the HalfUp rounding mode.
* `static EContext ForPrecisionAndRounding​(int precision,
ERounding rounding)`<br>
 Creates a new EContext object initialized with an unlimited exponent range,
 and the given rounding mode and maximum precision.
* `static EContext ForRounding​(ERounding rounding)`<br>
 Creates a new EContext object initialized with an unlimited precision, an
 unlimited exponent range, and the given rounding mode.
* `boolean getAdjustExponent()`<br>
 Gets a value indicating whether the EMax and EMin properties refer to the
 number's Exponent property adjusted to the number's precision, or
 just the number's Exponent property.
* `boolean getClampNormalExponents()`<br>
 Gets a value indicating whether a converted number's Exponent property will
 not be higher than EMax + 1 - Precision.
* `EInteger getEMax()`<br>
 Gets the highest exponent possible when a converted number is expressed in
 scientific notation with one nonzero digit before the radix point.
* `EInteger getEMin()`<br>
 Gets the lowest exponent possible when a converted number is expressed in
 scientific notation with one nonzero digit before the radix point.
* `int getFlags()`<br>
 Gets the flags that are set from converting numbers according to this
 arithmetic context.
* `boolean getHasExponentRange()`<br>
 Gets a value indicating whether this context defines a minimum and maximum
 exponent.
* `boolean getHasFlags()`<br>
 Gets a value indicating whether this context has a mutable Flags field.
* `boolean getHasFlagsOrTraps()`<br>
 Gets a value indicating whether this context has a mutable Flags field, one
 or more trap enablers, or both.
* `boolean getHasMaxPrecision()`<br>
 Gets a value indicating whether this context defines a maximum precision.
* `EContext GetNontrapping()`<br>
 Returns this context if it doesn't set traps, or a context without traps and
 with blank flags if it does, so that the resulting context does not
 cause trap exceptions to occur.
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
* `void setFlags​(int value)`<br>
* `java.lang.String toString()`<br>
 Returns this object in a text form intended to be read by humans.
* `<T> T TriggerTraps​(T result,
EContext trappableContext)`<br>
 Throws trap exceptions if the given context has flags set that also have
 traps enabled for them in this context, and adds the given context's
 flags to this context if HasFlags for this context is true.
* `EContext WithAdjustExponent​(boolean adjustExponent)`<br>
 Copies this EContext and sets the copy's "AdjustExponent" property to the
 given value.
* `EContext WithBigExponentRange​(EInteger exponentMin,
EInteger exponentMax)`<br>
 Copies this arithmetic context and sets the copy's exponent range.
* `EContext WithBigPrecision​(EInteger bigintPrecision)`<br>
 Copies this EContext and gives it a particular precision value.
* `EContext WithBlankFlags() HasFlags`<br>
 Copies this EContext with HasFlags set to true and a Flags value of
 0.
* `EContext WithExponentClamp​(boolean clamp)`<br>
 Copies this arithmetic context and sets the copy's "ClampNormalExponents"
 flag to the given value.
* `EContext WithExponentRange​(int exponentMinSmall,
int exponentMaxSmall)`<br>
 Copies this arithmetic context and sets the copy's exponent range.
* `EContext WithNoFlags() HasFlags`<br>
 Copies this EContext with HasFlags set to false and a Flags value of
 0.
* `EContext WithNoFlagsOrTraps() HasFlags`<br>
 Copies this EContext with HasFlags set to false, a Traps value of 0,
 and a Flags value of 0.
* `EContext WithPrecision​(int precision)`<br>
 Copies this EContext and gives it a particular precision value.
* `EContext WithPrecisionInBits​(boolean isPrecisionBits)`<br>
 Copies this EContext and sets the copy's "IsPrecisionInBits" property to the
 given value.
* `EContext WithRounding​(ERounding rounding)`<br>
 Copies this EContext with the specified rounding mode.
* `EContext WithSimplified​(boolean simplified)`<br>
 Copies this EContext and sets the copy's "IsSimplified" property to the
 given value.
* `EContext WithTraps​(int traps)`<br>
 Copies this EContext with Traps set to the given value.
* `EContext WithUnlimitedExponents()`<br>
 Copies this EContext with an unlimited exponent range.

## Field Details

### <a id='FlagClamped'>FlagClamped</a>

Signals that the exponent was adjusted to fit the exponent range.
### <a id='FlagDivideByZero'>FlagDivideByZero</a>

Signals a division of a nonzero number by zero.
### <a id='FlagInexact'>FlagInexact</a>

Signals that the result was rounded to a different mathematical value, but
 as close as possible to the original.
### <a id='FlagInvalid'>FlagInvalid</a>

Signals an invalid operation.
### <a id='FlagLostDigits'>FlagLostDigits</a>

Signals that an operand was rounded to a different mathematical value before
 an operation.
### <a id='FlagOverflow'>FlagOverflow</a>

Signals that the result is non-zero and the exponent is higher than the
 highest exponent allowed.
### <a id='FlagRounded'>FlagRounded</a>

Signals that the result was rounded to fit the precision; either the value
 or the exponent may have changed from the original.
### <a id='FlagSubnormal'>FlagSubnormal</a>

Signals that the result's exponent, before rounding, is lower than the
 lowest exponent allowed.
### <a id='FlagUnderflow'>FlagUnderflow</a>

Signals that the result's exponent, before rounding, is lower than the
 lowest exponent allowed, and the result was rounded to a different
 mathematical value, but as close as possible to the original.
### <a id='Basic'>Basic</a>

A basic arithmetic context, 9 digits precision, rounding mode half-up,
 unlimited exponent range. The default rounding mode is HalfUp.
### <a id='BigDecimalJava'>BigDecimalJava</a>

An arithmetic context for Java's BigDecimal format. The default rounding
 mode is HalfUp.
### <a id='Binary128'>Binary128</a>

An arithmetic context for the IEEE-754-2008 binary128 format, 113 bits
 precision. The default rounding mode is HalfEven.
### <a id='Binary16'>Binary16</a>

An arithmetic context for the IEEE-754-2008 binary16 format, 11 bits
 precision. The default rounding mode is HalfEven.
### <a id='Binary32'>Binary32</a>

An arithmetic context for the IEEE-754-2008 binary32 format, 24 bits
 precision. The default rounding mode is HalfEven.
### <a id='Binary64'>Binary64</a>

An arithmetic context for the IEEE-754-2008 binary64 format, 53 bits
 precision. The default rounding mode is HalfEven.
### <a id='CliDecimal'>CliDecimal</a>

An arithmetic context for the.NET Framework decimal format (see <code>"Forms of numbers"</code>), 96 bits
 precision, and a valid exponent range of -28 to 0. The default
  rounding mode is HalfEven. (The <code>"Cli"</code> stands for "Common
  Language Infrastructure", which defined this format as the .NET
 Framework decimal format in version 1, but leaves it unspecified in
 later versions.).
### <a id='Decimal128'>Decimal128</a>

An arithmetic context for the IEEE-754-2008 decimal128 format. The default
 rounding mode is HalfEven.
### <a id='Decimal32'>Decimal32</a>

An arithmetic context for the IEEE-754-2008 decimal32 format. The default
 rounding mode is HalfEven.
### <a id='Decimal64'>Decimal64</a>

An arithmetic context for the IEEE-754-2008 decimal64 format. The default
 rounding mode is HalfEven.
### <a id='Unlimited'>Unlimited</a>

No specific (theoretical) limit on precision. Rounding mode HalfUp.
### <a id='UnlimitedHalfEven'>UnlimitedHalfEven</a>

No specific (theoretical) limit on precision. Rounding mode HalfEven.
## Method Details

### <a id='getAdjustExponent()'>getAdjustExponent</a>

Gets a value indicating whether the EMax and EMin properties refer to the
 number's Exponent property adjusted to the number's precision, or
 just the number's Exponent property. The default value is true,
 meaning that EMax and EMin refer to the adjusted exponent. Setting
 this value to false (using WithAdjustExponent) is useful for
 modeling floating point representations with an integer significand
 and an integer exponent, such as Java's BigDecimal.

**Returns:**

* <code>true</code> if the EMax and EMin properties refer to the number's
 Exponent property adjusted to the number's precision, or false if
 they refer to just the number's Exponent property.

### <a id='getClampNormalExponents()'>getClampNormalExponents</a>

Gets a value indicating whether a converted number's Exponent property will
 not be higher than EMax + 1 - Precision. If a number's exponent is
 higher than that value, but not high enough to cause overflow, the
 exponent is clamped to that value and enough zeros are added to the
 number's significand to account for the adjustment. If
 HasExponentRange is false, this value is always false.

**Returns:**

* If true, a converted number's Exponent property will not be higher
 than EMax + 1 - Precision.

### <a id='getEMax()'>getEMax</a>

Gets the highest exponent possible when a converted number is expressed in
 scientific notation with one nonzero digit before the radix point.
 For example, with a precision of 3 and an EMax of 100, the maximum
 value possible is 9.99E + 100. (This is not the same as the highest
 possible Exponent property.) If HasExponentRange is false, this
 value will be 0.

**Returns:**

* The highest exponent possible when a converted number is expressed
 in scientific notation with one nonzero digit before the radix
 point. For example, with a precision of 3 and an EMax of 100, the
 maximum value possible is 9.99E + 100. (This is not the same as the
 highest possible Exponent property.) If HasExponentRange is false,
 this value will be 0.

### <a id='getEMin()'>getEMin</a>

Gets the lowest exponent possible when a converted number is expressed in
 scientific notation with one nonzero digit before the radix point.
 For example, with a precision of 3 and an EMin of -100, the next
 value that comes after 0 is 0.001E-100. (If AdjustExponent is false,
 this property specifies the lowest possible Exponent property
 instead.) If HasExponentRange is false, this value will be 0.

**Returns:**

* The lowest exponent possible when a converted number is expressed in
 scientific notation with one nonzero digit before the radix point.
 For example, with a precision of 3 and an EMin of -100, the next
 value that comes after 0 is 0.001E-100. (If AdjustExponent is false,
 this property specifies the lowest possible Exponent property
 instead.) If HasExponentRange is false, this value will be 0.

### <a id='getFlags()'>getFlags</a>

Gets the flags that are set from converting numbers according to this
 arithmetic context. If <code>HasFlags</code> is false, this value will be
 0. This value is a combination of bit fields. To retrieve a
 particular flag, use the AND operation on the return value of this
 method. For example: <code>(this.getFlags() &amp;EContext.FlagInexact)
 != 0</code> returns <code>true</code> if the Inexact flag is set.

**Returns:**

* The flags that are set from converting numbers according to this
 arithmetic context. If <code>HasFlags</code> is false, this value will be
 0. This value is a combination of bit fields. To retrieve a
 particular flag, use the AND operation on the return value of this
 method. For example: <code>(this.getFlags() &amp;EContext.FlagInexact)
 !=0</code> returns <code>true</code> if the Inexact flag is set.

### <a id='setFlags(int)'>setFlags</a>

### <a id='getHasExponentRange()'>getHasExponentRange</a>

Gets a value indicating whether this context defines a minimum and maximum
 exponent. If false, converted exponents can have any exponent and
 operations can't cause overflow or underflow.

**Returns:**

* <code>true</code> if this context defines a minimum and maximum exponent;
 otherwise, <code>false</code>.. If false, converted exponents can have
 any exponent and operations can't cause overflow or underflow.
 <code>true</code> if this context defines a minimum and maximum exponent;
 otherwise, <code>false</code>.

### <a id='getHasFlags()'>getHasFlags</a>

Gets a value indicating whether this context has a mutable Flags field.

**Returns:**

* <code>true</code> if this context has a mutable Flags field; otherwise,
 <code>false</code>.

### <a id='getHasMaxPrecision()'>getHasMaxPrecision</a>

Gets a value indicating whether this context defines a maximum precision.
 This is the same as whether this context's Precision property is
 zero.

**Returns:**

* <code>true</code> if this context defines a maximum precision; otherwise,
 <code>false</code>.

### <a id='isPrecisionInBits()'>isPrecisionInBits</a>

Gets a value indicating whether this context's Precision property is in
 bits, rather than digits. The default is false.

**Returns:**

* <code>true</code> if this context's Precision property is in bits, rather
 than digits; otherwise, <code>false</code>.. The default is false. <code>
 true</code> if this context's Precision property is in bits, rather than
 digits; otherwise, <code>false</code>. The default is false.

### <a id='isSimplified()'>isSimplified</a>

Gets a value indicating whether to use a "simplified" arithmetic. In the
 simplified arithmetic, infinity, not-a-number, and subnormal numbers
 are not allowed, and negative zero is treated the same as positive
 zero. For further details, see <code>http://speleotrove.com/decimal/dax3274.html</code>
.
### <a id='getPrecision()'>getPrecision</a>

Gets the maximum length of a converted number in digits, ignoring the radix
 point and exponent. For example, if precision is 3, a converted
 number's significand can range from 0 to 999 (up to three digits
 long). If 0, converted numbers can have any precision.
 <p>Not-a-number (NaN) values can carry an optional number, its
  payload, that serves as its "diagnostic information", In general, if
 an operation requires copying an NaN's payload, only up to as many
 digits of that payload as the precision given in this context,
 namely the least significant digits, are copied.</p>

**Returns:**

* The maximum length of a converted number in digits, ignoring the
 radix point and exponent. For example, if precision is 3, a
 converted number's significand can range from 0 to 999 (up to three
 digits long). If 0, converted numbers can have any precision.

### <a id='getRounding()'>getRounding</a>

Gets the desired rounding mode when converting numbers that can't be
 represented in the given precision and exponent range.

**Returns:**

* The desired rounding mode when converting numbers that can't be
 represented in the given precision and exponent range.

### <a id='getTraps()'>getTraps</a>

Gets the traps that are set for each flag in the context. Whenever a flag is
 signaled, even if <code>HasFlags</code> is false, and the flag's trap is
 enabled, the operation will throw a TrapException. <p>For example,
 if Traps equals <code>FlagInexact</code> and FlagSubnormal, a
 TrapException will be thrown if an operation's return value is not
 the same as the exact result (FlagInexact) or if the return value's
 exponent is lower than the lowest allowed (FlagSubnormal).</p>

**Returns:**

* The traps that are set for each flag in the context. Whenever a flag
 is signaled, even if <code>HasFlags</code> is false, and the flag's trap
 is enabled, the operation will throw a TrapException. For example,
 if Traps equals.

### <a id='ForPrecision(int)'>ForPrecision</a>

Creates a new arithmetic context using the given maximum number of digits,
 an unlimited exponent range, and the HalfUp rounding mode.

**Parameters:**

* <code>precision</code> - Maximum number of digits (precision).

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='ForPrecisionAndRounding(int,com.upokecenter.numbers.ERounding)'>ForPrecisionAndRounding</a>

Creates a new EContext object initialized with an unlimited exponent range,
 and the given rounding mode and maximum precision.

**Parameters:**

* <code>precision</code> - Maximum number of digits (precision).

* <code>rounding</code> - The parameter <code>rounding</code> is an ERounding object.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='ForRounding(com.upokecenter.numbers.ERounding)'>ForRounding</a>

Creates a new EContext object initialized with an unlimited precision, an
 unlimited exponent range, and the given rounding mode.

**Parameters:**

* <code>rounding</code> - The rounding mode for the new precision context.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='Copy()'>Copy</a>

Initializes a new EContext that is a copy of another EContext.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='ExponentWithinRange(com.upokecenter.numbers.EInteger)'>ExponentWithinRange</a>

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

* <code>java.lang.NullPointerException</code> - The parameter <code>exponent</code> is null.

### <a id='toString()'>toString</a>

Returns this object in a text form intended to be read by humans. The value
 returned by this method is not intended to be parsed by computer
 programs, and the exact text of the value may change at any time
 between versions of this library.

**Overrides:**

* <code>toString</code> in class <code>java.lang.Object</code>

**Returns:**

* A string representation of this object.

### <a id='getHasFlagsOrTraps()'>getHasFlagsOrTraps</a>

Gets a value indicating whether this context has a mutable Flags field, one
 or more trap enablers, or both.

**Returns:**

* <code>true</code> if this context has a mutable Flags field, one or more
 trap enablers, or both; otherwise, <code>false</code>.

### <a id='WithAdjustExponent(boolean)'>WithAdjustExponent</a>

Copies this EContext and sets the copy's "AdjustExponent" property to the
 given value.

**Parameters:**

* <code>adjustExponent</code> - The new value of the "AdjustExponent" property for the
 copy.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='WithBigExponentRange(com.upokecenter.numbers.EInteger,com.upokecenter.numbers.EInteger)'>WithBigExponentRange</a>

Copies this arithmetic context and sets the copy's exponent range.

**Parameters:**

* <code>exponentMin</code> - Desired minimum exponent (EMin).

* <code>exponentMax</code> - Desired maximum exponent (EMax).

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>exponentMin</code> is null.

* <code>java.lang.IllegalArgumentException</code> - ExponentMin greater than exponentMax".

### <a id='WithNoFlagsOrTraps()'>WithNoFlagsOrTraps</a>

Copies this EContext with <code>HasFlags</code> set to false, a Traps value of 0,
 and a Flags value of 0.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='WithBigPrecision(com.upokecenter.numbers.EInteger)'>WithBigPrecision</a>

Copies this EContext and gives it a particular precision value.

**Parameters:**

* <code>bigintPrecision</code> - Desired precision. 0 means unlimited precision.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigintPrecision</code> is null.

### <a id='WithBlankFlags()'>WithBlankFlags</a>

Copies this EContext with <code>HasFlags</code> set to true and a Flags value of
 0.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='WithExponentClamp(boolean)'>WithExponentClamp</a>

Copies this arithmetic context and sets the copy's "ClampNormalExponents"
 flag to the given value.

**Parameters:**

* <code>clamp</code> - The desired value of the "ClampNormalExponents" flag.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='WithExponentRange(int,int)'>WithExponentRange</a>

Copies this arithmetic context and sets the copy's exponent range.

**Parameters:**

* <code>exponentMinSmall</code> - Desired minimum exponent (EMin).

* <code>exponentMaxSmall</code> - Desired maximum exponent (EMax).

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='WithNoFlags()'>WithNoFlags</a>

Copies this EContext with <code>HasFlags</code> set to false and a Flags value of
 0.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='WithPrecision(int)'>WithPrecision</a>

Copies this EContext and gives it a particular precision value.

**Parameters:**

* <code>precision</code> - Desired precision. 0 means unlimited precision.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='WithPrecisionInBits(boolean)'>WithPrecisionInBits</a>

Copies this EContext and sets the copy's "IsPrecisionInBits" property to the
 given value.

**Parameters:**

* <code>isPrecisionBits</code> - The new value of the "IsPrecisionInBits" property for
 the copy.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='WithRounding(com.upokecenter.numbers.ERounding)'>WithRounding</a>

Copies this EContext with the specified rounding mode.

**Parameters:**

* <code>rounding</code> - Desired value of the Rounding property.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='WithSimplified(boolean)'>WithSimplified</a>

Copies this EContext and sets the copy's "IsSimplified" property to the
 given value.

**Parameters:**

* <code>simplified</code> - Desired value of the IsSimplified property.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='WithTraps(int)'>WithTraps</a>

Copies this EContext with Traps set to the given value. (Also sets HasFlags
 on the copy to <code>True</code>, but this may change in version 2.0 of
 this library.).

**Parameters:**

* <code>traps</code> - Flags representing the traps to enable. See the property
  "Traps".

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='WithUnlimitedExponents()'>WithUnlimitedExponents</a>

Copies this EContext with an unlimited exponent range.

**Returns:**

* A context object for arbitrary-precision arithmetic settings.

### <a id='GetNontrapping()'>GetNontrapping</a>

Returns this context if it doesn't set traps, or a context without traps and
 with blank flags if it does, so that the resulting context does not
 cause trap exceptions to occur. This is not a general-purpose method;
 it is intended to support custom implementations of arithmetic
 operations.

**Returns:**

* This context if it doesn't set traps, or a context without traps and
 with blank flags if it does.

### <a id='TriggerTraps(java.lang.Object,com.upokecenter.numbers.EContext)'>
<!--   -->
</a><a id='TriggerTraps(T,com.upokecenter.numbers.EContext)'>TriggerTraps</a>

Throws trap exceptions if the given context has flags set that also have
 traps enabled for them in this context, and adds the given context's
 flags to this context if HasFlags for this context is true. This is
 not a general-purpose method; it is intended to support custom
 implementations of arithmetic operations.

**Type Parameters:**

* <code>T</code> - Data type for the result of the operation.

**Parameters:**

* <code>result</code> - The result of the operation.

* <code>trappableContext</code> - An arithmetic context, usually a context returned by
 the GetNontrapping method. Can be null.

**Returns:**

* The parameter <code>result</code> if no trap exceptions were thrown.
