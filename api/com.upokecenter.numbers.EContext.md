# com.upokecenter.numbers.EContext

    public final class EContext extends java.lang.Object

## Fields

* `static EContext Basic`<br>
* `static EContext BigDecimalJava`<br>
* `static EContext Binary128`<br>
* `static EContext Binary16`<br>
* `static EContext Binary32`<br>
* `static EContext Binary64`<br>
* `static EContext CliDecimal`<br>
* `static EContext Decimal128`<br>
* `static EContext Decimal32`<br>
* `static EContext Decimal64`<br>
* `static int FlagClamped`<br>
* `static int FlagDivideByZero`<br>
* `static int FlagInexact`<br>
* `static int FlagInvalid`<br>
* `static int FlagLostDigits`<br>
* `static int FlagOverflow`<br>
* `static int FlagRounded`<br>
* `static int FlagSubnormal`<br>
* `static int FlagUnderflow`<br>
* `static EContext Unlimited`<br>
* `static EContext UnlimitedHalfEven`<br>

## Constructors

* `EContext​(int precision,
        ERounding rounding,
        int exponentMinSmall,
        int exponentMaxSmall,
        boolean clampNormalExponents)`<br>
* `EContext​(EInteger bigintPrecision,
        ERounding rounding,
        EInteger exponentMin,
        EInteger exponentMax,
        boolean clampNormalExponents)`<br>

## Methods

* `EContext Copy()`<br>
* `boolean ExponentWithinRange​(EInteger exponent)`<br>
* `static EContext ForPrecision​(int precision)`<br>
* `static EContext ForPrecisionAndRounding​(int precision,
                       ERounding rounding)`<br>
* `static EContext ForRounding​(ERounding rounding)`<br>
* `boolean getAdjustExponent()`<br>
* `boolean getClampNormalExponents()`<br>
* `EInteger getEMax()`<br>
* `EInteger getEMin()`<br>
* `int getFlags()`<br>
* `boolean getHasExponentRange()`<br>
* `boolean getHasFlags()`<br>
* `boolean getHasFlagsOrTraps()`<br>
* `boolean getHasMaxPrecision()`<br>
* `EContext GetNontrapping()`<br>
* `EInteger getPrecision()`<br>
* `ERounding getRounding()`<br>
* `int getTraps()`<br>
* `boolean isPrecisionInBits()`<br>
* `boolean isSimplified()`<br>
* `void setFlags​(int value)`<br>
* `java.lang.String toString()`<br>
* `<T> T TriggerTraps​(T result,
            EContext trappableContext)`<br>
* `EContext WithAdjustExponent​(boolean adjustExponent)`<br>
* `EContext WithBigExponentRange​(EInteger exponentMin,
                    EInteger exponentMax)`<br>
* `EContext WithBigPrecision​(EInteger bigintPrecision)`<br>
* `EContext WithBlankFlags()`<br>
* `EContext WithExponentClamp​(boolean clamp)`<br>
* `EContext WithExponentRange​(int exponentMinSmall,
                 int exponentMaxSmall)`<br>
* `EContext WithNoFlags()`<br>
* `EContext WithNoFlagsOrTraps()`<br>
* `EContext WithPrecision​(int precision)`<br>
* `EContext WithPrecisionInBits​(boolean isPrecisionBits)`<br>
* `EContext WithRounding​(ERounding rounding)`<br>
* `EContext WithSimplified​(boolean simplified)`<br>
* `EContext WithTraps​(int traps)`<br>
* `EContext WithUnlimitedExponents()`<br>

## Field Details

### FlagClamped
    public static final int FlagClamped
### FlagDivideByZero
    public static final int FlagDivideByZero
### FlagInexact
    public static final int FlagInexact
### FlagInvalid
    public static final int FlagInvalid
### FlagLostDigits
    public static final int FlagLostDigits
### FlagOverflow
    public static final int FlagOverflow
### FlagRounded
    public static final int FlagRounded
### FlagSubnormal
    public static final int FlagSubnormal
### FlagUnderflow
    public static final int FlagUnderflow
### Basic
    public static final EContext Basic
### BigDecimalJava
    public static final EContext BigDecimalJava
### Binary128
    public static final EContext Binary128
### Binary16
    public static final EContext Binary16
### Binary32
    public static final EContext Binary32
### Binary64
    public static final EContext Binary64
### CliDecimal
    public static final EContext CliDecimal
### Decimal128
    public static final EContext Decimal128
### Decimal32
    public static final EContext Decimal32
### Decimal64
    public static final EContext Decimal64
### Unlimited
    public static final EContext Unlimited
### UnlimitedHalfEven
    public static final EContext UnlimitedHalfEven
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
### ForPrecisionAndRounding
    public static EContext ForPrecisionAndRounding​(int precision, ERounding rounding)
### ForRounding
    public static EContext ForRounding​(ERounding rounding)
### Copy
    public EContext Copy()
### ExponentWithinRange
    public boolean ExponentWithinRange​(EInteger exponent)
### toString
    public java.lang.String toString()

**Overrides:**

* <code>toString</code> in class <code>java.lang.Object</code>

### getHasFlagsOrTraps
    public final boolean getHasFlagsOrTraps()
### WithAdjustExponent
    public EContext WithAdjustExponent​(boolean adjustExponent)
### WithBigExponentRange
    public EContext WithBigExponentRange​(EInteger exponentMin, EInteger exponentMax)
### WithNoFlagsOrTraps
    public EContext WithNoFlagsOrTraps()
### WithBigPrecision
    public EContext WithBigPrecision​(EInteger bigintPrecision)
### WithBlankFlags
    public EContext WithBlankFlags()
### WithExponentClamp
    public EContext WithExponentClamp​(boolean clamp)
### WithExponentRange
    public EContext WithExponentRange​(int exponentMinSmall, int exponentMaxSmall)
### WithNoFlags
    public EContext WithNoFlags()
### WithPrecision
    public EContext WithPrecision​(int precision)
### WithPrecisionInBits
    public EContext WithPrecisionInBits​(boolean isPrecisionBits)
### WithRounding
    public EContext WithRounding​(ERounding rounding)
### WithSimplified
    public EContext WithSimplified​(boolean simplified)
### WithTraps
    public EContext WithTraps​(int traps)
### WithUnlimitedExponents
    public EContext WithUnlimitedExponents()
### GetNontrapping
    public EContext GetNontrapping()
### TriggerTraps
    public <T> T TriggerTraps​(T result, EContext trappableContext)
