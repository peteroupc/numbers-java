# com.upokecenter.numbers.EDecimal

    public final class EDecimal extends java.lang.Object implements java.lang.Comparable<EDecimal>

## Fields

* `static EDecimal NaN`<br>
* `static EDecimal NegativeInfinity`<br>
* `static EDecimal NegativeZero`<br>
* `static EDecimal One`<br>
* `static EDecimal PositiveInfinity`<br>
* `static EDecimal SignalingNaN`<br>
* `static EDecimal Ten`<br>
* `static EDecimal Zero`<br>

## Methods

* `EDecimal Abs()`<br>
* `EDecimal Abs​(EContext context)`<br>
* `EDecimal Add​(int intValue)`<br>
* `EDecimal Add​(EDecimal otherValue)`<br>
* `EDecimal Add​(EDecimal otherValue,
   EContext ctx)`<br>
* `int compareTo​(int intOther)`<br>
* `int compareTo​(EDecimal other)`<br>
* `int CompareToBinary​(EFloat other)`<br>
* `EDecimal CompareToSignal​(EDecimal other,
               EContext ctx)`<br>
* `int CompareToTotal​(EDecimal other)`<br>
* `int CompareToTotal​(EDecimal other,
              EContext ctx)`<br>
* `int CompareToTotalMagnitude​(EDecimal other)`<br>
* `int CompareToTotalMagnitude​(EDecimal other,
                       EContext ctx)`<br>
* `int CompareToValue​(int intOther)`<br>
* `int CompareToValue​(EDecimal other)`<br>
* `EDecimal CompareToWithContext​(EDecimal other,
                    EContext ctx)`<br>
* `EDecimal Copy()`<br>
* `EDecimal CopySign​(EDecimal other)`<br>
* `static EDecimal Create​(int mantissaSmall,
      int exponentSmall)`<br>
* `static EDecimal Create​(long mantissaLong,
      int exponentSmall)`<br>
* `static EDecimal Create​(long mantissaLong,
      long exponentLong)`<br>
* `static EDecimal Create​(EInteger mantissa,
      int exponentSmall)`<br>
* `static EDecimal Create​(EInteger mantissa,
      long exponentLong)`<br>
* `static EDecimal Create​(EInteger mantissa,
      EInteger exponent)`<br>
* `static EDecimal CreateNaN​(EInteger diag)`<br>
* `static EDecimal CreateNaN​(EInteger diag,
         boolean signaling,
         boolean negative,
         EContext ctx)`<br>
* `EDecimal Decrement()`<br>
* `EDecimal Divide​(int intValue)`<br>
* `EDecimal Divide​(EDecimal divisor)`<br>
* `EDecimal Divide​(EDecimal divisor,
      EContext ctx)`<br>
* `EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor,
                              EContext ctx)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EDecimal DivideToExponent​(EDecimal divisor,
                int desiredExponentInt)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                int desiredExponentInt,
                EContext ctx)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                int desiredExponentInt,
                ERounding rounding)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                long desiredExponentSmall)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                long desiredExponentSmall,
                EContext ctx)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                long desiredExponentSmall,
                ERounding rounding)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                EInteger exponent)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                EInteger exponent,
                EContext ctx)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                EInteger desiredExponent,
                ERounding rounding)`<br>
* `EDecimal DivideToIntegerNaturalScale​(EDecimal divisor)`<br>
* `EDecimal DivideToIntegerNaturalScale​(EDecimal divisor,
                           EContext ctx)`<br>
* `EDecimal DivideToIntegerZeroScale​(EDecimal divisor,
                        EContext ctx)`<br>
* `EDecimal DivideToSameExponent​(EDecimal divisor,
                    ERounding rounding)`<br>
* `EDecimal[] DivRemNaturalScale​(EDecimal divisor)`<br>
* `EDecimal[] DivRemNaturalScale​(EDecimal divisor,
                  EContext ctx)`<br>
* `boolean equals​(EDecimal other)`<br>
* `boolean equals​(java.lang.Object obj)`<br>
* `EDecimal Exp​(EContext ctx)`<br>
* `static EDecimal FromBoolean​(boolean boolValue)`<br>
* `static EDecimal FromByte​(byte inputByte)`<br>
* `static EDecimal FromDouble​(double dbl)`<br>
* `static EDecimal FromEFloat​(EFloat bigfloat)`<br>
* `static EDecimal FromEInteger​(EInteger bigint)`<br>
* `static EDecimal FromExtendedFloat​(EFloat ef)`<br>
 Deprecated.
Renamed to FromEFloat.
 Renamed to FromEFloat.
* `static EDecimal FromInt16​(short inputInt16)`<br>
* `static EDecimal FromInt32​(int valueSmaller)`<br>
* `static EDecimal FromInt64​(long valueSmall)`<br>
* `static EDecimal FromSingle​(float flt)`<br>
* `static EDecimal FromString​(java.lang.String str)`<br>
* `static EDecimal FromString​(java.lang.String str,
          int offset,
          int length)`<br>
* `static EDecimal FromString​(java.lang.String str,
          int offset,
          int length,
          EContext ctx)`<br>
* `static EDecimal FromString​(java.lang.String str,
          EContext ctx)`<br>
* `EInteger getExponent()`<br>
* `EInteger getMantissa()`<br>
* `EInteger getUnsignedMantissa()`<br>
* `int hashCode()`<br>
* `EDecimal Increment()`<br>
* `boolean isFinite()`<br>
* `boolean IsInfinity()`<br>
* `boolean IsInteger()`<br>
* `boolean IsNaN()`<br>
* `boolean isNegative()`<br>
* `boolean IsNegativeInfinity()`<br>
* `boolean IsPositiveInfinity()`<br>
* `boolean IsQuietNaN()`<br>
* `boolean IsSignalingNaN()`<br>
* `boolean isZero()`<br>
* `EDecimal Log​(EContext ctx)`<br>
* `EDecimal Log10​(EContext ctx)`<br>
* `EDecimal LogN​(EDecimal baseValue,
    EContext ctx)`<br>
* `static EDecimal Max​(EDecimal first,
   EDecimal second)`<br>
* `static EDecimal Max​(EDecimal first,
   EDecimal second,
   EContext ctx)`<br>
* `static EDecimal MaxMagnitude​(EDecimal first,
            EDecimal second)`<br>
* `static EDecimal MaxMagnitude​(EDecimal first,
            EDecimal second,
            EContext ctx)`<br>
* `static EDecimal Min​(EDecimal first,
   EDecimal second)`<br>
* `static EDecimal Min​(EDecimal first,
   EDecimal second,
   EContext ctx)`<br>
* `static EDecimal MinMagnitude​(EDecimal first,
            EDecimal second)`<br>
* `static EDecimal MinMagnitude​(EDecimal first,
            EDecimal second,
            EContext ctx)`<br>
* `EDecimal MovePointLeft​(int places)`<br>
* `EDecimal MovePointLeft​(int places,
             EContext ctx)`<br>
* `EDecimal MovePointLeft​(EInteger bigPlaces)`<br>
* `EDecimal MovePointLeft​(EInteger bigPlaces,
             EContext ctx)`<br>
* `EDecimal MovePointRight​(int places)`<br>
* `EDecimal MovePointRight​(int places,
              EContext ctx)`<br>
* `EDecimal MovePointRight​(EInteger bigPlaces)`<br>
* `EDecimal MovePointRight​(EInteger bigPlaces,
              EContext ctx)`<br>
* `EDecimal Multiply​(int intValue)`<br>
* `EDecimal Multiply​(EDecimal otherValue)`<br>
* `EDecimal Multiply​(EDecimal op,
        EContext ctx)`<br>
* `EDecimal MultiplyAndAdd​(EDecimal multiplicand,
              EDecimal augend)`<br>
* `EDecimal MultiplyAndAdd​(EDecimal op,
              EDecimal augend,
              EContext ctx)`<br>
* `EDecimal MultiplyAndSubtract​(EDecimal op,
                   EDecimal subtrahend,
                   EContext ctx)`<br>
* `EDecimal Negate()`<br>
* `EDecimal Negate​(EContext context)`<br>
* `EDecimal NextMinus​(EContext ctx)`<br>
* `EDecimal NextPlus​(EContext ctx)`<br>
* `EDecimal NextToward​(EDecimal otherValue,
          EContext ctx)`<br>
* `static EDecimal PI​(EContext ctx)`<br>
* `EDecimal Plus​(EContext ctx)`<br>
* `EDecimal Pow​(int exponentSmall)`<br>
* `EDecimal Pow​(int exponentSmall,
   EContext ctx)`<br>
* `EDecimal Pow​(EDecimal exponent)`<br>
* `EDecimal Pow​(EDecimal exponent,
   EContext ctx)`<br>
* `EInteger Precision()`<br>
* `EDecimal PreRound​(EContext ctx)`<br>
* `EDecimal Quantize​(int desiredExponentInt,
        EContext ctx)`<br>
* `EDecimal Quantize​(int desiredExponentInt,
        ERounding rounding)`<br>
* `EDecimal Quantize​(EDecimal otherValue,
        EContext ctx)`<br>
* `EDecimal Quantize​(EInteger desiredExponent,
        EContext ctx)`<br>
* `EDecimal Reduce​(EContext ctx)`<br>
* `EDecimal Remainder​(EDecimal divisor,
         EContext ctx)`<br>
* `EDecimal RemainderNaturalScale​(EDecimal divisor)`<br>
* `EDecimal RemainderNaturalScale​(EDecimal divisor,
                     EContext ctx)`<br>
* `EDecimal RemainderNear​(EDecimal divisor,
             EContext ctx)`<br>
* `EDecimal RemainderNoRoundAfterDivide​(EDecimal divisor,
                           EContext ctx)`<br>
* `EDecimal RoundToExponent​(int exponentSmall)`<br>
* `EDecimal RoundToExponent​(int exponentSmall,
               EContext ctx)`<br>
* `EDecimal RoundToExponent​(int exponentSmall,
               ERounding rounding)`<br>
* `EDecimal RoundToExponent​(EInteger exponent)`<br>
* `EDecimal RoundToExponent​(EInteger exponent,
               EContext ctx)`<br>
* `EDecimal RoundToExponent​(EInteger exponent,
               ERounding rounding)`<br>
* `EDecimal RoundToExponentExact​(int exponentSmall,
                    EContext ctx)`<br>
* `EDecimal RoundToExponentExact​(int exponentSmall,
                    ERounding rounding)`<br>
* `EDecimal RoundToExponentExact​(EInteger exponent,
                    EContext ctx)`<br>
* `EDecimal RoundToIntegerExact​(EContext ctx)`<br>
* `EDecimal RoundToIntegerNoRoundedFlag​(EContext ctx)`<br>
* `EDecimal RoundToIntegralExact​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerExact.
 Renamed to RoundToIntegerExact.
* `EDecimal RoundToIntegralNoRoundedFlag​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerNoRoundedFlag.
 Renamed to RoundToIntegerNoRoundedFlag.
* `EDecimal RoundToPrecision​(EContext ctx)`<br>
* `EDecimal ScaleByPowerOfTen​(int places)`<br>
* `EDecimal ScaleByPowerOfTen​(int places,
                 EContext ctx)`<br>
* `EDecimal ScaleByPowerOfTen​(EInteger bigPlaces)`<br>
* `EDecimal ScaleByPowerOfTen​(EInteger bigPlaces,
                 EContext ctx)`<br>
* `int signum()`<br>
* `EDecimal Sqrt​(EContext ctx)`<br>
* `EDecimal SquareRoot​(EContext ctx)`<br>
 Deprecated.
Renamed to Sqrt.
 Renamed to Sqrt.
* `EDecimal Subtract​(int intValue)`<br>
* `EDecimal Subtract​(EDecimal otherValue)`<br>
* `EDecimal Subtract​(EDecimal otherValue,
        EContext ctx)`<br>
* `byte ToByteChecked()`<br>
* `byte ToByteIfExact()`<br>
* `byte ToByteUnchecked()`<br>
* `double ToDouble()`<br>
* `EFloat ToEFloat()`<br>
* `EFloat ToEFloat​(EContext ec)`<br>
* `EInteger ToEInteger()`<br>
* `EInteger ToEIntegerExact()`<br>
 Deprecated.
Renamed to ToEIntegerIfExact.
 Renamed to ToEIntegerIfExact.
* `EInteger ToEIntegerIfExact()`<br>
* `java.lang.String ToEngineeringString()`<br>
* `EFloat ToExtendedFloat()`<br>
 Deprecated.
Renamed to ToEFloat.
 Renamed to ToEFloat.
* `short ToInt16Checked()`<br>
* `short ToInt16IfExact()`<br>
* `short ToInt16Unchecked()`<br>
* `int ToInt32Checked()`<br>
* `int ToInt32IfExact()`<br>
* `int ToInt32Unchecked()`<br>
* `long ToInt64Checked()`<br>
* `long ToInt64IfExact()`<br>
* `long ToInt64Unchecked()`<br>
* `java.lang.String ToPlainString()`<br>
* `float ToSingle()`<br>
* `EInteger ToSizedEInteger​(int maxBitLength)`<br>
* `EInteger ToSizedEIntegerIfExact​(int maxBitLength)`<br>
* `java.lang.String toString()`<br>
* `EDecimal Ulp()`<br>

## Field Details

### NaN
    public static final EDecimal NaN
### NegativeInfinity
    public static final EDecimal NegativeInfinity
### NegativeZero
    public static final EDecimal NegativeZero
### One
    public static final EDecimal One
### PositiveInfinity
    public static final EDecimal PositiveInfinity
### SignalingNaN
    public static final EDecimal SignalingNaN
### Ten
    public static final EDecimal Ten
### Zero
    public static final EDecimal Zero
## Method Details

### Copy
    public EDecimal Copy()
### getExponent
    public final EInteger getExponent()
### isFinite
    public final boolean isFinite()
### isNegative
    public final boolean isNegative()
### isZero
    public final boolean isZero()
### IsInteger
    public boolean IsInteger()
### getMantissa
    public final EInteger getMantissa()
### signum
    public final int signum()
### getUnsignedMantissa
    public final EInteger getUnsignedMantissa()
### Create
    public static EDecimal Create​(int mantissaSmall, int exponentSmall)
### Create
    public static EDecimal Create​(EInteger mantissa, int exponentSmall)
### Create
    public static EDecimal Create​(EInteger mantissa, long exponentLong)
### Create
    public static EDecimal Create​(EInteger mantissa, EInteger exponent)
### Create
    public static EDecimal Create​(long mantissaLong, int exponentSmall)
### Create
    public static EDecimal Create​(long mantissaLong, long exponentLong)
### CreateNaN
    public static EDecimal CreateNaN​(EInteger diag)
### CreateNaN
    public static EDecimal CreateNaN​(EInteger diag, boolean signaling, boolean negative, EContext ctx)
### FromDouble
    public static EDecimal FromDouble​(double dbl)
### FromEInteger
    public static EDecimal FromEInteger​(EInteger bigint)
### FromExtendedFloat
    @Deprecated public static EDecimal FromExtendedFloat​(EFloat ef)
Deprecated.
Renamed to FromEFloat.

### FromEFloat
    public static EDecimal FromEFloat​(EFloat bigfloat)
### FromBoolean
    public static EDecimal FromBoolean​(boolean boolValue)
### FromInt32
    public static EDecimal FromInt32​(int valueSmaller)
### FromInt64
    public static EDecimal FromInt64​(long valueSmall)
### FromSingle
    public static EDecimal FromSingle​(float flt)
### FromString
    public static EDecimal FromString​(java.lang.String str)
### FromString
    public static EDecimal FromString​(java.lang.String str, EContext ctx)
### FromString
    public static EDecimal FromString​(java.lang.String str, int offset, int length)
### FromString
    public static EDecimal FromString​(java.lang.String str, int offset, int length, EContext ctx)
### Max
    public static EDecimal Max​(EDecimal first, EDecimal second, EContext ctx)
### Max
    public static EDecimal Max​(EDecimal first, EDecimal second)
### MaxMagnitude
    public static EDecimal MaxMagnitude​(EDecimal first, EDecimal second, EContext ctx)
### MaxMagnitude
    public static EDecimal MaxMagnitude​(EDecimal first, EDecimal second)
### Min
    public static EDecimal Min​(EDecimal first, EDecimal second, EContext ctx)
### Min
    public static EDecimal Min​(EDecimal first, EDecimal second)
### MinMagnitude
    public static EDecimal MinMagnitude​(EDecimal first, EDecimal second, EContext ctx)
### MinMagnitude
    public static EDecimal MinMagnitude​(EDecimal first, EDecimal second)
### PI
    public static EDecimal PI​(EContext ctx)
### Abs
    public EDecimal Abs()
### CopySign
    public EDecimal CopySign​(EDecimal other)
### Abs
    public EDecimal Abs​(EContext context)
### Add
    public EDecimal Add​(EDecimal otherValue)
### Add
    public EDecimal Add​(EDecimal otherValue, EContext ctx)
### compareTo
    public int compareTo​(EDecimal other)

**Specified by:**

* <code>compareTo</code> in interface <code>java.lang.Comparable&lt;EDecimal&gt;</code>

### compareTo
    public int compareTo​(int intOther)
### CompareToValue
    public int CompareToValue​(int intOther)
### CompareToValue
    public int CompareToValue​(EDecimal other)
### CompareToBinary
    public int CompareToBinary​(EFloat other)
### CompareToSignal
    public EDecimal CompareToSignal​(EDecimal other, EContext ctx)
### CompareToTotalMagnitude
    public int CompareToTotalMagnitude​(EDecimal other)
### CompareToTotal
    public int CompareToTotal​(EDecimal other, EContext ctx)
### CompareToTotalMagnitude
    public int CompareToTotalMagnitude​(EDecimal other, EContext ctx)
### CompareToTotal
    public int CompareToTotal​(EDecimal other)
### CompareToWithContext
    public EDecimal CompareToWithContext​(EDecimal other, EContext ctx)
### Divide
    public EDecimal Divide​(EDecimal divisor)
### Divide
    public EDecimal Divide​(EDecimal divisor, EContext ctx)
### DivideAndRemainderNaturalScale
    @Deprecated public EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor)
Deprecated.
Renamed to DivRemNaturalScale.

### DivideAndRemainderNaturalScale
    @Deprecated public EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor, EContext ctx)
Deprecated.
Renamed to DivRemNaturalScale.

### DivRemNaturalScale
    public EDecimal[] DivRemNaturalScale​(EDecimal divisor)
### DivRemNaturalScale
    public EDecimal[] DivRemNaturalScale​(EDecimal divisor, EContext ctx)
### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, long desiredExponentSmall, EContext ctx)
### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, int desiredExponentInt, EContext ctx)
### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, long desiredExponentSmall, ERounding rounding)
### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, int desiredExponentInt, ERounding rounding)
### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, EInteger exponent, EContext ctx)
### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, EInteger exponent)
### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, long desiredExponentSmall)
### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, int desiredExponentInt)
### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, EInteger desiredExponent, ERounding rounding)
### DivideToIntegerNaturalScale
    public EDecimal DivideToIntegerNaturalScale​(EDecimal divisor)
### DivideToIntegerNaturalScale
    public EDecimal DivideToIntegerNaturalScale​(EDecimal divisor, EContext ctx)
### DivideToIntegerZeroScale
    public EDecimal DivideToIntegerZeroScale​(EDecimal divisor, EContext ctx)
### DivideToSameExponent
    public EDecimal DivideToSameExponent​(EDecimal divisor, ERounding rounding)
### equals
    public boolean equals​(EDecimal other)
### equals
    public boolean equals​(java.lang.Object obj)

**Overrides:**

* <code>equals</code> in class <code>java.lang.Object</code>

### Exp
    public EDecimal Exp​(EContext ctx)
### hashCode
    public int hashCode()

**Overrides:**

* <code>hashCode</code> in class <code>java.lang.Object</code>

### IsInfinity
    public boolean IsInfinity()
### IsNaN
    public boolean IsNaN()
### IsNegativeInfinity
    public boolean IsNegativeInfinity()
### IsPositiveInfinity
    public boolean IsPositiveInfinity()
### IsQuietNaN
    public boolean IsQuietNaN()
### IsSignalingNaN
    public boolean IsSignalingNaN()
### Log
    public EDecimal Log​(EContext ctx)
### Log10
    public EDecimal Log10​(EContext ctx)
### LogN
    public EDecimal LogN​(EDecimal baseValue, EContext ctx)
### MovePointLeft
    public EDecimal MovePointLeft​(int places)
### MovePointLeft
    public EDecimal MovePointLeft​(int places, EContext ctx)
### MovePointLeft
    public EDecimal MovePointLeft​(EInteger bigPlaces)
### MovePointLeft
    public EDecimal MovePointLeft​(EInteger bigPlaces, EContext ctx)
### MovePointRight
    public EDecimal MovePointRight​(int places)
### MovePointRight
    public EDecimal MovePointRight​(int places, EContext ctx)
### MovePointRight
    public EDecimal MovePointRight​(EInteger bigPlaces)
### MovePointRight
    public EDecimal MovePointRight​(EInteger bigPlaces, EContext ctx)
### Multiply
    public EDecimal Multiply​(EDecimal otherValue)
### Multiply
    public EDecimal Multiply​(EDecimal op, EContext ctx)
### Add
    public EDecimal Add​(int intValue)
### Subtract
    public EDecimal Subtract​(int intValue)
### Multiply
    public EDecimal Multiply​(int intValue)
### Divide
    public EDecimal Divide​(int intValue)
### MultiplyAndAdd
    public EDecimal MultiplyAndAdd​(EDecimal multiplicand, EDecimal augend)
### MultiplyAndAdd
    public EDecimal MultiplyAndAdd​(EDecimal op, EDecimal augend, EContext ctx)
### MultiplyAndSubtract
    public EDecimal MultiplyAndSubtract​(EDecimal op, EDecimal subtrahend, EContext ctx)
### Negate
    public EDecimal Negate()
### Negate
    public EDecimal Negate​(EContext context)
### NextMinus
    public EDecimal NextMinus​(EContext ctx)
### NextPlus
    public EDecimal NextPlus​(EContext ctx)
### NextToward
    public EDecimal NextToward​(EDecimal otherValue, EContext ctx)
### Plus
    public EDecimal Plus​(EContext ctx)
### Pow
    public EDecimal Pow​(EDecimal exponent, EContext ctx)
### Pow
    public EDecimal Pow​(EDecimal exponent)
### Pow
    public EDecimal Pow​(int exponentSmall, EContext ctx)
### Pow
    public EDecimal Pow​(int exponentSmall)
### Precision
    public EInteger Precision()
### Quantize
    public EDecimal Quantize​(EInteger desiredExponent, EContext ctx)
### Quantize
    public EDecimal Quantize​(int desiredExponentInt, ERounding rounding)
### Quantize
    public EDecimal Quantize​(int desiredExponentInt, EContext ctx)
### Quantize
    public EDecimal Quantize​(EDecimal otherValue, EContext ctx)
### Reduce
    public EDecimal Reduce​(EContext ctx)
### Remainder
    public EDecimal Remainder​(EDecimal divisor, EContext ctx)
### RemainderNoRoundAfterDivide
    public EDecimal RemainderNoRoundAfterDivide​(EDecimal divisor, EContext ctx)
### RemainderNaturalScale
    public EDecimal RemainderNaturalScale​(EDecimal divisor)
### RemainderNaturalScale
    public EDecimal RemainderNaturalScale​(EDecimal divisor, EContext ctx)
### RemainderNear
    public EDecimal RemainderNear​(EDecimal divisor, EContext ctx)
### RoundToExponent
    public EDecimal RoundToExponent​(EInteger exponent, EContext ctx)
### RoundToExponent
    public EDecimal RoundToExponent​(EInteger exponent)
### RoundToExponent
    public EDecimal RoundToExponent​(EInteger exponent, ERounding rounding)
### RoundToExponent
    public EDecimal RoundToExponent​(int exponentSmall)
### RoundToExponent
    public EDecimal RoundToExponent​(int exponentSmall, EContext ctx)
### RoundToExponent
    public EDecimal RoundToExponent​(int exponentSmall, ERounding rounding)
### RoundToExponentExact
    public EDecimal RoundToExponentExact​(EInteger exponent, EContext ctx)
### RoundToExponentExact
    public EDecimal RoundToExponentExact​(int exponentSmall, EContext ctx)
### RoundToExponentExact
    public EDecimal RoundToExponentExact​(int exponentSmall, ERounding rounding)
### RoundToIntegerExact
    public EDecimal RoundToIntegerExact​(EContext ctx)
### RoundToIntegerNoRoundedFlag
    public EDecimal RoundToIntegerNoRoundedFlag​(EContext ctx)
### RoundToIntegralExact
    @Deprecated public EDecimal RoundToIntegralExact​(EContext ctx)
Deprecated.
Renamed to RoundToIntegerExact.

### RoundToIntegralNoRoundedFlag
    @Deprecated public EDecimal RoundToIntegralNoRoundedFlag​(EContext ctx)
Deprecated.
Renamed to RoundToIntegerNoRoundedFlag.

### RoundToPrecision
    public EDecimal RoundToPrecision​(EContext ctx)
### PreRound
    public EDecimal PreRound​(EContext ctx)
### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(int places)
### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(int places, EContext ctx)
### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(EInteger bigPlaces)
### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(EInteger bigPlaces, EContext ctx)
### Sqrt
    public EDecimal Sqrt​(EContext ctx)
### SquareRoot
    @Deprecated public EDecimal SquareRoot​(EContext ctx)
Deprecated.
Renamed to Sqrt.

### Subtract
    public EDecimal Subtract​(EDecimal otherValue)
### Subtract
    public EDecimal Subtract​(EDecimal otherValue, EContext ctx)
### ToDouble
    public double ToDouble()
### ToEInteger
    public EInteger ToEInteger()
### ToEIntegerExact
    @Deprecated public EInteger ToEIntegerExact()
Deprecated.
Renamed to ToEIntegerIfExact.

### ToEIntegerIfExact
    public EInteger ToEIntegerIfExact()
### ToEngineeringString
    public java.lang.String ToEngineeringString()
### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat()
Deprecated.
Renamed to ToEFloat.

### ToEFloat
    public EFloat ToEFloat()
### ToPlainString
    public java.lang.String ToPlainString()
### ToSingle
    public float ToSingle()
### toString
    public java.lang.String toString()

**Overrides:**

* <code>toString</code> in class <code>java.lang.Object</code>

### Ulp
    public EDecimal Ulp()
### ToSizedEInteger
    public EInteger ToSizedEInteger​(int maxBitLength)
### ToSizedEIntegerIfExact
    public EInteger ToSizedEIntegerIfExact​(int maxBitLength)
### ToEFloat
    public EFloat ToEFloat​(EContext ec)
### Increment
    public EDecimal Increment()
### Decrement
    public EDecimal Decrement()
### ToByteChecked
    public byte ToByteChecked()
### ToByteUnchecked
    public byte ToByteUnchecked()
### ToByteIfExact
    public byte ToByteIfExact()
### FromByte
    public static EDecimal FromByte​(byte inputByte)
### ToInt16Checked
    public short ToInt16Checked()
### ToInt16Unchecked
    public short ToInt16Unchecked()
### ToInt16IfExact
    public short ToInt16IfExact()
### FromInt16
    public static EDecimal FromInt16​(short inputInt16)
### ToInt32Checked
    public int ToInt32Checked()
### ToInt32Unchecked
    public int ToInt32Unchecked()
### ToInt32IfExact
    public int ToInt32IfExact()
### ToInt64Checked
    public long ToInt64Checked()
### ToInt64Unchecked
    public long ToInt64Unchecked()
### ToInt64IfExact
    public long ToInt64IfExact()
