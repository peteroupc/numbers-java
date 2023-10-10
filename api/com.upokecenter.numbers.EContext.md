# com.upokecenter.numbers.EContext

    public final class EContext extends Object

## Fields

* `static final EContext Basic`<br>
  
* `static final EContext BigDecimalJava`<br>
  
* `static final EContext Binary128`<br>
  
* `static final EContext Binary16`<br>
  
* `static final EContext Binary32`<br>
  
* `static final EContext Binary64`<br>
  
* `static final EContext CliDecimal`<br>
  
* `static final EContext Decimal128`<br>
  
* `static final EContext Decimal32`<br>
  
* `static final EContext Decimal64`<br>
  
* `static final int FlagClamped`<br>
  
* `static final int FlagDivideByZero`<br>
  
* `static final int FlagInexact`<br>
  
* `static final int FlagInvalid`<br>
  
* `static final int FlagLostDigits`<br>
  
* `static final int FlagOverflow`<br>
  
* `static final int FlagRounded`<br>
  
* `static final int FlagSubnormal`<br>
  
* `static final int FlagUnderflow`<br>
  
* `static final EContext Unlimited`<br>
  
* `static final EContext UnlimitedHalfEven`<br>
  

## Constructors

## Methods

* `EContext Copy()`<br>
  
* `boolean ExponentWithinRange(EInteger exponent)`<br>
  
* `static EContext ForPrecision(int precision)`<br>
  
* `static EContext ForPrecisionAndRounding(int precision,
 ERounding rounding)`<br>
  
* `static EContext ForRounding(ERounding rounding)`<br>
  
* `final boolean getAdjustExponent()`<br>
  
* `final boolean getClampNormalExponents()`<br>
  
* `final EInteger getEMax()`<br>
  
* `final EInteger getEMin()`<br>
  
* `final int getFlags()`<br>
  
* `final boolean getHasExponentRange()`<br>
  
* `final boolean getHasFlags()`<br>
  
* `final boolean getHasFlagsOrTraps()`<br>
  
* `final boolean getHasMaxPrecision()`<br>
  
* `EContext GetNontrapping()`<br>
  
* `final EInteger getPrecision()`<br>
  
* `final ERounding getRounding()`<br>
  
* `final int getTraps()`<br>
  
* `final boolean isPrecisionInBits()`<br>
  
* `final boolean isSimplified()`<br>
  
* `final void setFlags(int value)`<br>
  
* `String toString()`<br>
  
* `<T> T TriggerTraps(T result,
 EContext trappableContext)`<br>
  
* `EContext WithAdjustExponent(boolean adjustExponent)`<br>
  
* `EContext WithBigExponentRange(EInteger exponentMin,
 EInteger exponentMax)`<br>
  
* `EContext WithBigPrecision(EInteger bigintPrecision)`<br>
  
* `EContext WithBlankFlags()`<br>
  
* `EContext WithExponentClamp(boolean clamp)`<br>
  
* `EContext WithExponentRange(int exponentMinSmall,
 int exponentMaxSmall)`<br>
  
* `EContext WithNoFlags()`<br>
  
* `EContext WithNoFlagsOrTraps()`<br>
  
* `EContext WithPrecision(int precision)`<br>
  
* `EContext WithPrecisionInBits(boolean isPrecisionBits)`<br>
  
* `EContext WithRounding(ERounding rounding)`<br>
  
* `EContext WithSimplified(boolean simplified)`<br>
  
* `EContext WithTraps(int traps)`<br>
  
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
    public final void setFlags(int value)
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
    public static EContext ForPrecision(int precision)
### ForPrecisionAndRounding
    public static EContext ForPrecisionAndRounding(int precision, ERounding rounding)
### ForRounding
    public static EContext ForRounding(ERounding rounding)
### Copy
    public EContext Copy()
### ExponentWithinRange
    public boolean ExponentWithinRange(EInteger exponent)
### toString
    public String toString()

**Overrides:**

* <code>toString</code> in class <code>Object</code>

### getHasFlagsOrTraps
    public final boolean getHasFlagsOrTraps()
### WithAdjustExponent
    public EContext WithAdjustExponent(boolean adjustExponent)
### WithBigExponentRange
    public EContext WithBigExponentRange(EInteger exponentMin, EInteger exponentMax)
### WithNoFlagsOrTraps
    public EContext WithNoFlagsOrTraps()
### WithBigPrecision
    public EContext WithBigPrecision(EInteger bigintPrecision)
### WithBlankFlags
    public EContext WithBlankFlags()
### WithExponentClamp
    public EContext WithExponentClamp(boolean clamp)
### WithExponentRange
    public EContext WithExponentRange(int exponentMinSmall, int exponentMaxSmall)
### WithNoFlags
    public EContext WithNoFlags()
### WithPrecision
    public EContext WithPrecision(int precision)
### WithPrecisionInBits
    public EContext WithPrecisionInBits(boolean isPrecisionBits)
### WithRounding
    public EContext WithRounding(ERounding rounding)
### WithSimplified
    public EContext WithSimplified(boolean simplified)
### WithTraps
    public EContext WithTraps(int traps)
### WithUnlimitedExponents
    public EContext WithUnlimitedExponents()
### GetNontrapping
    public EContext GetNontrapping()
### TriggerTraps
    public <T> T TriggerTraps(T result, EContext trappableContext)
