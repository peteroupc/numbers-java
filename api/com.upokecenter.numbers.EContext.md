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
 multiple threads and at least one of those threads needs to write the
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

* `EContext​(int precision,
        ERounding rounding,
        int exponentMinSmall,
        int exponentMaxSmall,
        boolean clampNormalExponents)`<br>

## Methods

* `EContext Copy()`<br>
* `boolean ExponentWithinRange​(EInteger exponent)`<br>
* `static EContext ForPrecision​(int precision)`<br>
* `static EContext ForPrecisionAndRounding​(int precision,
                       ERounding rounding)`<br>
* `static EContext ForRounding​(ERounding rounding)`<br>
* `boolean getAdjustExponent()`<br>
* `boolean getClampNormalExponents()`<br>
* `EInteger getEMax()`<br>
* `EInteger getEMin()`<br>
* `int getFlags()`<br>
* `boolean getHasExponentRange()`<br>
* `boolean getHasFlags()`<br>
* `boolean getHasMaxPrecision()`<br>
* `EInteger getPrecision()`<br>
* `ERounding getRounding()`<br>
* `int getTraps()`<br>
* `boolean isPrecisionInBits()`<br>
* `boolean isSimplified()`<br>
* `void setFlags​(int value)`<br>
* `String toString()`<br>
* `EContext WithAdjustExponent​(boolean adjustExponent)`<br>
* `EContext WithBigExponentRange​(EInteger exponentMin,
                    EInteger exponentMax)`<br>
* `EContext WithBigPrecision​(EInteger bigintPrecision)`<br>
* `EContext WithBlankFlags()`<br>
* `EContext WithExponentClamp​(boolean clamp)`<br>
* `EContext WithExponentRange​(int exponentMinSmall,
                 int exponentMaxSmall)`<br>
* `EContext WithNoFlags()`<br>
* `EContext WithPrecision​(int precision)`<br>
* `EContext WithPrecisionInBits​(boolean isPrecisionBits)`<br>
* `EContext WithRounding​(ERounding rounding)`<br>
* `EContext WithSimplified​(boolean simplified)`<br>
* `EContext WithTraps​(int traps)`<br>
* `EContext WithUnlimitedExponents()`<br>

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
Signals that the result's exponent, before rounding, is lower than the
 lowest exponent allowed.
### FlagUnderflow
    public static final int FlagUnderflow
Signals that the result's exponent, before rounding, is lower than the
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
### getClampNormalExponents
    public final boolean getClampNormalExponents()
### getEMax
    public final EInteger getEMax()
### getEMin
    public final EInteger getEMin()
### getFlags
    public final int getFlags()
### setFlags
    public final void setFlags​(int value)
### getHasExponentRange
    public final boolean getHasExponentRange()
### getHasFlags
    public final boolean getHasFlags()
### getHasMaxPrecision
    public final boolean getHasMaxPrecision()
### isPrecisionInBits
    public final boolean isPrecisionInBits()
### isSimplified
    public final boolean isSimplified()
### getPrecision
    public final EInteger getPrecision()
### getRounding
    public final ERounding getRounding()
### getTraps
    public final int getTraps()
### ForPrecision
    public static EContext ForPrecision​(int precision)

**Parameters:**

* <code>precision</code> - Not documented yet.

**Returns:**

* An EContext object.

### ForPrecisionAndRounding
    public static EContext ForPrecisionAndRounding​(int precision, ERounding rounding)

**Parameters:**

* <code>precision</code> - Not documented yet.

* <code>rounding</code> - Not documented yet.

**Returns:**

* An EContext object.

### ForRounding
    public static EContext ForRounding​(ERounding rounding)

**Parameters:**

* <code>rounding</code> - Not documented yet.

**Returns:**

* An EContext object.

### Copy
    public EContext Copy()

**Returns:**

* An EContext object.

### ExponentWithinRange
    public boolean ExponentWithinRange​(EInteger exponent)

**Parameters:**

* <code>exponent</code> - Not documented yet.

**Returns:**

* A Boolean object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### toString
    public String toString()

**Overrides:**

* <code>toString</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A string object.

### WithAdjustExponent
    public EContext WithAdjustExponent​(boolean adjustExponent)

**Parameters:**

* <code>adjustExponent</code> - Not documented yet.

**Returns:**

* An EContext object.

### WithBigExponentRange
    public EContext WithBigExponentRange​(EInteger exponentMin, EInteger exponentMax)

**Parameters:**

* <code>exponentMin</code> - Not documented yet.

* <code>exponentMax</code> - Not documented yet.

**Returns:**

* An EContext object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### WithBigPrecision
    public EContext WithBigPrecision​(EInteger bigintPrecision)

**Parameters:**

* <code>bigintPrecision</code> - Not documented yet.

**Returns:**

* An EContext object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### WithBlankFlags
    public EContext WithBlankFlags()

**Returns:**

* An EContext object.

### WithExponentClamp
    public EContext WithExponentClamp​(boolean clamp)

**Parameters:**

* <code>clamp</code> - Not documented yet.

**Returns:**

* An EContext object.

### WithExponentRange
    public EContext WithExponentRange​(int exponentMinSmall, int exponentMaxSmall)

**Parameters:**

* <code>exponentMinSmall</code> - Not documented yet.

* <code>exponentMaxSmall</code> - Not documented yet.

**Returns:**

* An EContext object.

### WithNoFlags
    public EContext WithNoFlags()

**Returns:**

* An EContext object.

### WithPrecision
    public EContext WithPrecision​(int precision)

**Parameters:**

* <code>precision</code> - Not documented yet.

**Returns:**

* An EContext object.

### WithPrecisionInBits
    public EContext WithPrecisionInBits​(boolean isPrecisionBits)

**Parameters:**

* <code>isPrecisionBits</code> - Not documented yet.

**Returns:**

* An EContext object.

### WithRounding
    public EContext WithRounding​(ERounding rounding)

**Parameters:**

* <code>rounding</code> - Not documented yet.

**Returns:**

* An EContext object.

### WithSimplified
    public EContext WithSimplified​(boolean simplified)

**Parameters:**

* <code>simplified</code> - Not documented yet.

**Returns:**

* An EContext object.

### WithTraps
    public EContext WithTraps​(int traps)

**Parameters:**

* <code>traps</code> - Not documented yet.

**Returns:**

* An EContext object.

### WithUnlimitedExponents
    public EContext WithUnlimitedExponents()

**Returns:**

* An EContext object.
