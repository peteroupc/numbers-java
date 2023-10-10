# com.upokecenter.numbers.EFloat

    public final class EFloat extends Object implements Comparable<EFloat>

## Fields

* `static final EFloat NaN`<br>
  
* `static final EFloat NegativeInfinity`<br>
  
* `static final EFloat NegativeZero`<br>
  
* `static final EFloat One`<br>
  
* `static final EFloat PositiveInfinity`<br>
  
* `static final EFloat SignalingNaN`<br>
  
* `static final EFloat Ten`<br>
  
* `static final EFloat Zero`<br>
  

## Methods

* `EFloat Abs()`<br>
  
* `EFloat Abs(EContext context)`<br>
  
* `EFloat Add(int intValue)`<br>
  
* `EFloat Add(long longValue)`<br>
  
* `EFloat Add(EFloat otherValue)`<br>
  
* `EFloat Add(EFloat otherValue,
 EContext ctx)`<br>
  
* `int compareTo(int intOther)`<br>
  
* `int compareTo(long intOther)`<br>
  
* `int compareTo(EFloat other)`<br>
  
* `EFloat CompareToSignal(EFloat other,
 EContext ctx)`<br>
  
* `int CompareToTotal(EFloat other)`<br>
  
* `int CompareToTotal(EFloat other,
 EContext ctx)`<br>
  
* `int CompareToTotalMagnitude(EFloat other)`<br>
  
* `int CompareToTotalMagnitude(EFloat other,
 EContext ctx)`<br>
  
* `int CompareToValue(int intOther)`<br>
  
* `int CompareToValue(long intOther)`<br>
  
* `int CompareToValue(EFloat other)`<br>
  
* `EFloat CompareToWithContext(EFloat other,
 EContext ctx)`<br>
  
* `EFloat Copy()`<br>
  
* `EFloat CopySign(EFloat other)`<br>
  
* `static EFloat Create(int mantissaSmall,
 int exponentSmall)`<br>
  
* `static EFloat Create(long mantissaLong,
 int exponentSmall)`<br>
  
* `static EFloat Create(long mantissaLong,
 long exponentLong)`<br>
  
* `static EFloat Create(EInteger mantissa,
 int exponentSmall)`<br>
  
* `static EFloat Create(EInteger mantissa,
 long exponentLong)`<br>
  
* `static EFloat Create(EInteger mantissa,
 EInteger exponent)`<br>
  
* `static EFloat CreateNaN(EInteger diag)`<br>
  
* `static EFloat CreateNaN(EInteger diag,
 boolean signaling,
 boolean negative,
 EContext ctx)`<br>
  
* `EFloat Decrement()`<br>
  
* `EFloat Divide(int intValue)`<br>
  
* `EFloat Divide(long longValue)`<br>
  
* `EFloat Divide(EFloat divisor)`<br>
  
* `EFloat Divide(EFloat divisor,
 EContext ctx)`<br>
  
* `EFloat[] DivideAndRemainderNaturalScale(EFloat divisor)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.

* `EFloat[] DivideAndRemainderNaturalScale(EFloat divisor,
 EContext ctx)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.

* `EFloat DivideToExponent(EFloat divisor,
 long desiredExponentSmall,
 EContext ctx)`<br>
  
* `EFloat DivideToExponent(EFloat divisor,
 long desiredExponentSmall,
 ERounding rounding)`<br>
  
* `EFloat DivideToExponent(EFloat divisor,
 EInteger exponent,
 EContext ctx)`<br>
  
* `EFloat DivideToExponent(EFloat divisor,
 EInteger desiredExponent,
 ERounding rounding)`<br>
  
* `EFloat DivideToIntegerNaturalScale(EFloat divisor)`<br>
  
* `EFloat DivideToIntegerNaturalScale(EFloat divisor,
 EContext ctx)`<br>
  
* `EFloat DivideToIntegerZeroScale(EFloat divisor,
 EContext ctx)`<br>
  
* `EFloat DivideToSameExponent(EFloat divisor,
 ERounding rounding)`<br>
  
* `EFloat[] DivRemNaturalScale(EFloat divisor)`<br>
  
* `EFloat[] DivRemNaturalScale(EFloat divisor,
 EContext ctx)`<br>
  
* `boolean equals(EFloat other)`<br>
  
* `boolean equals(Object obj)`<br>
  
* `boolean EqualsInternal(EFloat otherValue)`<br>
  
* `EFloat Exp(EContext ctx)`<br>
  
* `EFloat ExpM1(EContext ctx)`<br>
  
* `static EFloat FromBoolean(boolean boolValue)`<br>
  
* `static EFloat FromByte(byte inputByte)`<br>
  
* `static EFloat FromDouble(double dbl)`<br>
  
* `static EFloat FromDoubleBits(long dblBits)`<br>
  
* `static EFloat FromEInteger(EInteger bigint)`<br>
  
* `static EFloat FromHalfBits(short value)`<br>
  
* `static EFloat FromInt16(short inputInt16)`<br>
  
* `static EFloat FromInt32(int inputInt32)`<br>
  
* `static EFloat FromInt64(long inputInt64)`<br>
  
* `static EFloat FromInt64AsUnsigned(long longerValue)`<br>
  
* `static EFloat FromSingle(float flt)`<br>
  
* `static EFloat FromSingleBits(int value)`<br>
  
* `static EFloat FromString(byte[] bytes)`<br>
  
* `static EFloat FromString(byte[] bytes,
 int offset,
 int length)`<br>
  
* `static EFloat FromString(byte[] bytes,
 int offset,
 int length,
 EContext ctx)`<br>
  
* `static EFloat FromString(byte[] bytes,
 EContext ctx)`<br>
  
* `static EFloat FromString(char[] chars)`<br>
  
* `static EFloat FromString(char[] chars,
 int offset,
 int length)`<br>
  
* `static EFloat FromString(char[] chars,
 int offset,
 int length,
 EContext ctx)`<br>
  
* `static EFloat FromString(char[] chars,
 EContext ctx)`<br>
  
* `static EFloat FromString(String str)`<br>
  
* `static EFloat FromString(String str,
 int offset,
 int length)`<br>
  
* `static EFloat FromString(String str,
 int offset,
 int length,
 EContext ctx)`<br>
  
* `static EFloat FromString(String str,
 EContext ctx)`<br>
  
* `final EInteger getExponent()`<br>
  
* `final EInteger getMantissa()`<br>
  
* `final EInteger getUnsignedMantissa()`<br>
  
* `int hashCode()`<br>
  
* `EFloat Increment()`<br>
  
* `final boolean isFinite()`<br>
  
* `boolean IsInfinity()`<br>
  
* `boolean IsInteger()`<br>
  
* `boolean IsNaN()`<br>
  
* `final boolean isNegative()`<br>
  
* `boolean IsNegativeInfinity()`<br>
  
* `boolean IsPositiveInfinity()`<br>
  
* `boolean IsQuietNaN()`<br>
  
* `boolean IsSignalingNaN()`<br>
  
* `final boolean isZero()`<br>
  
* `EFloat Log(EContext ctx)`<br>
  
* `EFloat Log10(EContext ctx)`<br>
  
* `EFloat Log1P(EContext ctx)`<br>
  
* `EFloat LogN(EFloat baseValue,
 EContext ctx)`<br>
  
* `static EFloat Max(EFloat first,
 EFloat second)`<br>
  
* `static EFloat Max(EFloat first,
 EFloat second,
 EContext ctx)`<br>
  
* `static EFloat MaxMagnitude(EFloat first,
 EFloat second)`<br>
  
* `static EFloat MaxMagnitude(EFloat first,
 EFloat second,
 EContext ctx)`<br>
  
* `static EFloat Min(EFloat first,
 EFloat second)`<br>
  
* `static EFloat Min(EFloat first,
 EFloat second,
 EContext ctx)`<br>
  
* `static EFloat MinMagnitude(EFloat first,
 EFloat second)`<br>
  
* `static EFloat MinMagnitude(EFloat first,
 EFloat second,
 EContext ctx)`<br>
  
* `EFloat MovePointLeft(int places)`<br>
  
* `EFloat MovePointLeft(int places,
 EContext ctx)`<br>
  
* `EFloat MovePointLeft(EInteger bigPlaces)`<br>
  
* `EFloat MovePointLeft(EInteger bigPlaces,
 EContext ctx)`<br>
  
* `EFloat MovePointRight(int places)`<br>
  
* `EFloat MovePointRight(int places,
 EContext ctx)`<br>
  
* `EFloat MovePointRight(EInteger bigPlaces)`<br>
  
* `EFloat MovePointRight(EInteger bigPlaces,
 EContext ctx)`<br>
  
* `EFloat Multiply(int intValue)`<br>
  
* `EFloat Multiply(long longValue)`<br>
  
* `EFloat Multiply(EFloat otherValue)`<br>
  
* `EFloat Multiply(EFloat op,
 EContext ctx)`<br>
  
* `EFloat MultiplyAndAdd(EFloat multiplicand,
 EFloat augend)`<br>
  
* `EFloat MultiplyAndAdd(EFloat op,
 EFloat augend,
 EContext ctx)`<br>
  
* `EFloat MultiplyAndSubtract(EFloat op,
 EFloat subtrahend,
 EContext ctx)`<br>
  
* `EFloat Negate()`<br>
  
* `EFloat Negate(EContext context)`<br>
  
* `EFloat NextMinus(EContext ctx)`<br>
  
* `EFloat NextPlus(EContext ctx)`<br>
  
* `EFloat NextToward(EFloat otherValue,
 EContext ctx)`<br>
  
* `static EFloat PI(EContext ctx)`<br>
  
* `EFloat Plus(EContext ctx)`<br>
  
* `EFloat Pow(int exponentSmall)`<br>
  
* `EFloat Pow(int exponentSmall,
 EContext ctx)`<br>
  
* `EFloat Pow(EFloat exponent)`<br>
  
* `EFloat Pow(EFloat exponent,
 EContext ctx)`<br>
  
* `EInteger Precision()`<br>
  
* `EFloat PreRound(EContext ctx)`<br>
  
* `EFloat Quantize(int desiredExponentInt,
 EContext ctx)`<br>
  
* `EFloat Quantize(EFloat otherValue,
 EContext ctx)`<br>
  
* `EFloat Quantize(EInteger desiredExponent,
 EContext ctx)`<br>
  
* `EFloat Reduce(EContext ctx)`<br>
  
* `EFloat Remainder(EFloat divisor,
 EContext ctx)`<br>
  
* `EFloat RemainderNaturalScale(EFloat divisor)`<br>
  
* `EFloat RemainderNaturalScale(EFloat divisor,
 EContext ctx)`<br>
  
* `EFloat RemainderNear(EFloat divisor,
 EContext ctx)`<br>
  
* `EFloat RemainderNoRoundAfterDivide(EFloat divisor,
 EContext ctx)`<br>
  
* `EFloat RoundToExponent(int exponentSmall,
 EContext ctx)`<br>
  
* `EFloat RoundToExponent(EInteger exponent,
 EContext ctx)`<br>
  
* `EFloat RoundToExponentExact(int exponentSmall,
 EContext ctx)`<br>
  
* `EFloat RoundToExponentExact(EInteger exponent,
 EContext ctx)`<br>
  
* `EFloat RoundToExponentExact(EInteger exponent,
 ERounding rounding)`<br>
  
* `EFloat RoundToIntegerExact(EContext ctx)`<br>
  
* `EFloat RoundToIntegerNoRoundedFlag(EContext ctx)`<br>
  
* `EFloat RoundToIntegralExact(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerExact.

* `EFloat RoundToIntegralNoRoundedFlag(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerNoRoundedFlag.

* `EFloat RoundToPrecision(EContext ctx)`<br>
  
* `EFloat ScaleByPowerOfTwo(int places)`<br>
  
* `EFloat ScaleByPowerOfTwo(int places,
 EContext ctx)`<br>
  
* `EFloat ScaleByPowerOfTwo(EInteger bigPlaces)`<br>
  
* `EFloat ScaleByPowerOfTwo(EInteger bigPlaces,
 EContext ctx)`<br>
  
* `final int signum()`<br>
  
* `EFloat Sqrt(EContext ctx)`<br>
  
* `EFloat SquareRoot(EContext ctx)`<br>
 Deprecated.
Renamed to Sqrt.

* `EFloat Subtract(int intValue)`<br>
  
* `EFloat Subtract(long longValue)`<br>
  
* `EFloat Subtract(EFloat otherValue)`<br>
  
* `EFloat Subtract(EFloat otherValue,
 EContext ctx)`<br>
  
* `byte ToByteChecked()`<br>
  
* `byte ToByteIfExact()`<br>
  
* `byte ToByteUnchecked()`<br>
  
* `double ToDouble()`<br>
  
* `long ToDoubleBits()`<br>
  
* `EDecimal ToEDecimal()`<br>
  
* `EInteger ToEInteger()`<br>
  
* `EInteger ToEIntegerExact()`<br>
 Deprecated.
Renamed to ToEIntegerIfExact.

* `EInteger ToEIntegerIfExact()`<br>
  
* `String ToEngineeringString()`<br>
  
* `EDecimal ToExtendedDecimal()`<br>
 Deprecated.
Renamed to ToEDecimal.

* `short ToHalfBits()`<br>
  
* `short ToInt16Checked()`<br>
  
* `short ToInt16IfExact()`<br>
  
* `short ToInt16Unchecked()`<br>
  
* `int ToInt32Checked()`<br>
  
* `int ToInt32IfExact()`<br>
  
* `int ToInt32Unchecked()`<br>
  
* `long ToInt64Checked()`<br>
  
* `long ToInt64IfExact()`<br>
  
* `long ToInt64Unchecked()`<br>
  
* `String ToPlainString()`<br>
  
* `String ToShortestString(EContext ctx)`<br>
  
* `float ToSingle()`<br>
  
* `int ToSingleBits()`<br>
  
* `EInteger ToSizedEInteger(int maxBitLength)`<br>
  
* `EInteger ToSizedEIntegerIfExact(int maxBitLength)`<br>
  
* `String toString()`<br>
  
* `EFloat Ulp()`<br>
  

## Field Details

### NaN
    public static final EFloat NaN
### NegativeInfinity
    public static final EFloat NegativeInfinity
### NegativeZero
    public static final EFloat NegativeZero
### One
    public static final EFloat One
### PositiveInfinity
    public static final EFloat PositiveInfinity
### SignalingNaN
    public static final EFloat SignalingNaN
### Ten
    public static final EFloat Ten
### Zero
    public static final EFloat Zero
## Method Details

### getExponent
    public final EInteger getExponent()
### isFinite
    public final boolean isFinite()
### isNegative
    public final boolean isNegative()
### isZero
    public final boolean isZero()
### getMantissa
    public final EInteger getMantissa()
### signum
    public final int signum()
### getUnsignedMantissa
    public final EInteger getUnsignedMantissa()
### Copy
    public EFloat Copy()
### Create
    public static EFloat Create(int mantissaSmall, int exponentSmall)
### Create
    public static EFloat Create(EInteger mantissa, int exponentSmall)
### Create
    public static EFloat Create(EInteger mantissa, long exponentLong)
### Create
    public static EFloat Create(EInteger mantissa, EInteger exponent)
### Create
    public static EFloat Create(long mantissaLong, int exponentSmall)
### Create
    public static EFloat Create(long mantissaLong, long exponentLong)
### CreateNaN
    public static EFloat CreateNaN(EInteger diag)
### CreateNaN
    public static EFloat CreateNaN(EInteger diag, boolean signaling, boolean negative, EContext ctx)
### FromDoubleBits
    public static EFloat FromDoubleBits(long dblBits)
### FromSingle
    public static EFloat FromSingle(float flt)
### FromDouble
    public static EFloat FromDouble(double dbl)
### FromEInteger
    public static EFloat FromEInteger(EInteger bigint)
### FromString
    public static EFloat FromString(String str, int offset, int length, EContext ctx)
### FromString
    public static EFloat FromString(String str)
### FromString
    public static EFloat FromString(String str, EContext ctx)
### FromString
    public static EFloat FromString(String str, int offset, int length)
### FromString
    public static EFloat FromString(char[] chars, int offset, int length, EContext ctx)
### FromString
    public static EFloat FromString(char[] chars)
### FromString
    public static EFloat FromString(char[] chars, EContext ctx)
### FromString
    public static EFloat FromString(char[] chars, int offset, int length)
### FromString
    public static EFloat FromString(byte[] bytes, int offset, int length, EContext ctx)
### FromString
    public static EFloat FromString(byte[] bytes)
### FromString
    public static EFloat FromString(byte[] bytes, EContext ctx)
### FromString
    public static EFloat FromString(byte[] bytes, int offset, int length)
### Max
    public static EFloat Max(EFloat first, EFloat second, EContext ctx)
### Max
    public static EFloat Max(EFloat first, EFloat second)
### MaxMagnitude
    public static EFloat MaxMagnitude(EFloat first, EFloat second, EContext ctx)
### MaxMagnitude
    public static EFloat MaxMagnitude(EFloat first, EFloat second)
### Min
    public static EFloat Min(EFloat first, EFloat second, EContext ctx)
### Min
    public static EFloat Min(EFloat first, EFloat second)
### MinMagnitude
    public static EFloat MinMagnitude(EFloat first, EFloat second, EContext ctx)
### MinMagnitude
    public static EFloat MinMagnitude(EFloat first, EFloat second)
### PI
    public static EFloat PI(EContext ctx)
### Abs
    public EFloat Abs()
### Abs
    public EFloat Abs(EContext context)
### Add
    public EFloat Add(int intValue)
### Subtract
    public EFloat Subtract(int intValue)
### Multiply
    public EFloat Multiply(int intValue)
### Divide
    public EFloat Divide(int intValue)
### Add
    public EFloat Add(long longValue)
### Subtract
    public EFloat Subtract(long longValue)
### Multiply
    public EFloat Multiply(long longValue)
### Divide
    public EFloat Divide(long longValue)
### Add
    public EFloat Add(EFloat otherValue)
### Add
    public EFloat Add(EFloat otherValue, EContext ctx)
### compareTo
    public int compareTo(EFloat other)

**Specified by:**

* <code>compareTo</code> in interface <code>Comparable&lt;EFloat&gt;</code>

### CompareToValue
    public int CompareToValue(EFloat other)
### compareTo
    public int compareTo(int intOther)
### CompareToValue
    public int CompareToValue(int intOther)
### CompareToValue
    public int CompareToValue(long intOther)
### compareTo
    public int compareTo(long intOther)
### CompareToSignal
    public EFloat CompareToSignal(EFloat other, EContext ctx)
### CompareToTotal
    public int CompareToTotal(EFloat other, EContext ctx)
### CompareToTotalMagnitude
    public int CompareToTotalMagnitude(EFloat other, EContext ctx)
### CompareToTotal
    public int CompareToTotal(EFloat other)
### CompareToTotalMagnitude
    public int CompareToTotalMagnitude(EFloat other)
### CompareToWithContext
    public EFloat CompareToWithContext(EFloat other, EContext ctx)
### CopySign
    public EFloat CopySign(EFloat other)
### Divide
    public EFloat Divide(EFloat divisor)
### Divide
    public EFloat Divide(EFloat divisor, EContext ctx)
### DivideAndRemainderNaturalScale
    @Deprecated public EFloat[] DivideAndRemainderNaturalScale(EFloat divisor)
### DivideAndRemainderNaturalScale
    @Deprecated public EFloat[] DivideAndRemainderNaturalScale(EFloat divisor, EContext ctx)
### DivideToExponent
    public EFloat DivideToExponent(EFloat divisor, long desiredExponentSmall, EContext ctx)
### DivideToExponent
    public EFloat DivideToExponent(EFloat divisor, long desiredExponentSmall, ERounding rounding)
### DivideToExponent
    public EFloat DivideToExponent(EFloat divisor, EInteger exponent, EContext ctx)
### DivideToExponent
    public EFloat DivideToExponent(EFloat divisor, EInteger desiredExponent, ERounding rounding)
### DivideToIntegerNaturalScale
    public EFloat DivideToIntegerNaturalScale(EFloat divisor)
### DivideToIntegerNaturalScale
    public EFloat DivideToIntegerNaturalScale(EFloat divisor, EContext ctx)
### DivideToIntegerZeroScale
    public EFloat DivideToIntegerZeroScale(EFloat divisor, EContext ctx)
### DivideToSameExponent
    public EFloat DivideToSameExponent(EFloat divisor, ERounding rounding)
### DivRemNaturalScale
    public EFloat[] DivRemNaturalScale(EFloat divisor)
### DivRemNaturalScale
    public EFloat[] DivRemNaturalScale(EFloat divisor, EContext ctx)
### equals
    public boolean equals(EFloat other)
### equals
    public boolean equals(Object obj)

**Overrides:**

* <code>equals</code> in class <code>Object</code>

### EqualsInternal
    public boolean EqualsInternal(EFloat otherValue)
### Exp
    public EFloat Exp(EContext ctx)
### ExpM1
    public EFloat ExpM1(EContext ctx)
### hashCode
    public int hashCode()

**Overrides:**

* <code>hashCode</code> in class <code>Object</code>

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
    public EFloat Log(EContext ctx)
### Log10
    public EFloat Log10(EContext ctx)
### Log1P
    public EFloat Log1P(EContext ctx)
### LogN
    public EFloat LogN(EFloat baseValue, EContext ctx)
### MovePointLeft
    public EFloat MovePointLeft(int places)
### MovePointLeft
    public EFloat MovePointLeft(int places, EContext ctx)
### MovePointLeft
    public EFloat MovePointLeft(EInteger bigPlaces)
### MovePointLeft
    public EFloat MovePointLeft(EInteger bigPlaces, EContext ctx)
### MovePointRight
    public EFloat MovePointRight(int places)
### MovePointRight
    public EFloat MovePointRight(int places, EContext ctx)
### MovePointRight
    public EFloat MovePointRight(EInteger bigPlaces)
### MovePointRight
    public EFloat MovePointRight(EInteger bigPlaces, EContext ctx)
### Multiply
    public EFloat Multiply(EFloat otherValue)
### Multiply
    public EFloat Multiply(EFloat op, EContext ctx)
### MultiplyAndAdd
    public EFloat MultiplyAndAdd(EFloat multiplicand, EFloat augend)
### MultiplyAndAdd
    public EFloat MultiplyAndAdd(EFloat op, EFloat augend, EContext ctx)
### MultiplyAndSubtract
    public EFloat MultiplyAndSubtract(EFloat op, EFloat subtrahend, EContext ctx)
### Negate
    public EFloat Negate()
### Negate
    public EFloat Negate(EContext context)
### NextMinus
    public EFloat NextMinus(EContext ctx)
### NextPlus
    public EFloat NextPlus(EContext ctx)
### NextToward
    public EFloat NextToward(EFloat otherValue, EContext ctx)
### Plus
    public EFloat Plus(EContext ctx)
### Pow
    public EFloat Pow(EFloat exponent)
### Pow
    public EFloat Pow(EFloat exponent, EContext ctx)
### Pow
    public EFloat Pow(int exponentSmall, EContext ctx)
### Pow
    public EFloat Pow(int exponentSmall)
### Precision
    public EInteger Precision()
### IsInteger
    public boolean IsInteger()
### Quantize
    public EFloat Quantize(EInteger desiredExponent, EContext ctx)
### Quantize
    public EFloat Quantize(int desiredExponentInt, EContext ctx)
### Quantize
    public EFloat Quantize(EFloat otherValue, EContext ctx)
### Reduce
    public EFloat Reduce(EContext ctx)
### Remainder
    public EFloat Remainder(EFloat divisor, EContext ctx)
### RemainderNoRoundAfterDivide
    public EFloat RemainderNoRoundAfterDivide(EFloat divisor, EContext ctx)
### RemainderNaturalScale
    public EFloat RemainderNaturalScale(EFloat divisor)
### RemainderNaturalScale
    public EFloat RemainderNaturalScale(EFloat divisor, EContext ctx)
### RemainderNear
    public EFloat RemainderNear(EFloat divisor, EContext ctx)
### RoundToExponent
    public EFloat RoundToExponent(EInteger exponent, EContext ctx)
### RoundToExponent
    public EFloat RoundToExponent(int exponentSmall, EContext ctx)
### RoundToExponentExact
    public EFloat RoundToExponentExact(EInteger exponent, EContext ctx)
### RoundToExponentExact
    public EFloat RoundToExponentExact(EInteger exponent, ERounding rounding)
### RoundToExponentExact
    public EFloat RoundToExponentExact(int exponentSmall, EContext ctx)
### RoundToIntegerExact
    public EFloat RoundToIntegerExact(EContext ctx)
### RoundToIntegerNoRoundedFlag
    public EFloat RoundToIntegerNoRoundedFlag(EContext ctx)
### RoundToIntegralExact
    @Deprecated public EFloat RoundToIntegralExact(EContext ctx)
### RoundToIntegralNoRoundedFlag
    @Deprecated public EFloat RoundToIntegralNoRoundedFlag(EContext ctx)
### RoundToPrecision
    public EFloat RoundToPrecision(EContext ctx)
### PreRound
    public EFloat PreRound(EContext ctx)
### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo(int places)
### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo(int places, EContext ctx)
### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo(EInteger bigPlaces)
### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo(EInteger bigPlaces, EContext ctx)
### Sqrt
    public EFloat Sqrt(EContext ctx)
### SquareRoot
    @Deprecated public EFloat SquareRoot(EContext ctx)
### Subtract
    public EFloat Subtract(EFloat otherValue)
### Subtract
    public EFloat Subtract(EFloat otherValue, EContext ctx)
### ToDouble
    public double ToDouble()
### FromSingleBits
    public static EFloat FromSingleBits(int value)
### FromHalfBits
    public static EFloat FromHalfBits(short value)
### ToSingleBits
    public int ToSingleBits()
### ToHalfBits
    public short ToHalfBits()
### ToDoubleBits
    public long ToDoubleBits()
### ToEDecimal
    public EDecimal ToEDecimal()
### ToEInteger
    public EInteger ToEInteger()
### ToEIntegerExact
    @Deprecated public EInteger ToEIntegerExact()
### ToEIntegerIfExact
    public EInteger ToEIntegerIfExact()
### ToEngineeringString
    public String ToEngineeringString()
### ToExtendedDecimal
    @Deprecated public EDecimal ToExtendedDecimal()
### ToPlainString
    public String ToPlainString()
### ToShortestString
    public String ToShortestString(EContext ctx)
### ToSingle
    public float ToSingle()
### toString
    public String toString()

**Overrides:**

* <code>toString</code> in class <code>Object</code>

### Ulp
    public EFloat Ulp()
### ToSizedEInteger
    public EInteger ToSizedEInteger(int maxBitLength)
### ToSizedEIntegerIfExact
    public EInteger ToSizedEIntegerIfExact(int maxBitLength)
### Increment
    public EFloat Increment()
### Decrement
    public EFloat Decrement()
### ToByteChecked
    public byte ToByteChecked()
### ToByteUnchecked
    public byte ToByteUnchecked()
### ToByteIfExact
    public byte ToByteIfExact()
### FromByte
    public static EFloat FromByte(byte inputByte)
### ToInt16Checked
    public short ToInt16Checked()
### ToInt16Unchecked
    public short ToInt16Unchecked()
### ToInt16IfExact
    public short ToInt16IfExact()
### FromInt16
    public static EFloat FromInt16(short inputInt16)
### ToInt32Checked
    public int ToInt32Checked()
### ToInt32Unchecked
    public int ToInt32Unchecked()
### ToInt32IfExact
    public int ToInt32IfExact()
### FromBoolean
    public static EFloat FromBoolean(boolean boolValue)
### FromInt32
    public static EFloat FromInt32(int inputInt32)
### ToInt64Checked
    public long ToInt64Checked()
### ToInt64Unchecked
    public long ToInt64Unchecked()
### ToInt64IfExact
    public long ToInt64IfExact()
### FromInt64AsUnsigned
    public static EFloat FromInt64AsUnsigned(long longerValue)
### FromInt64
    public static EFloat FromInt64(long inputInt64)
