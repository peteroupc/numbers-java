# com.upokecenter.numbers.EFloat

    public final class EFloat extends Object implements Comparable<EFloat>

Represents an arbitrary-precision binary floating-point number. (The "E"
 stands for "extended", meaning that instances of this class can be
 values other than numbers proper, such as infinity and not-a-number.)
 Each number consists of an integer mantissa (significand) and an
 integer exponent, both arbitrary-precision. The value of the number
 equals mantissa (significand) * 2^exponent. This class also supports
 values for negative zero, not-a-number (NaN) values, and infinity.
 <p>Passing a signaling NaN to any arithmetic operation shown here
 will signal the flag FlagInvalid and return a quiet NaN, even if
 another operand to that operation is a quiet NaN, unless noted
 otherwise.</p> <p>Passing a quiet NaN to any arithmetic operation
 shown here will return a quiet NaN, unless noted otherwise.</p>
 <p>Unless noted otherwise, passing a null arbitrary-precision binary
 float argument to any method here will throw an exception.</p>
 <p>When an arithmetic operation signals the flag FlagInvalid,
 FlagOverflow, or FlagDivideByZero, it will not throw an exception
 too, unless the operation's trap is enabled in the precision context
 (see EContext's Traps property).</p> <p>An arbitrary-precision binary
 float value can be serialized in one of the following ways:</p> <ul>
 <li>By calling the toString() method. However, not all strings can be
 converted back to an arbitrary-precision binary float without loss,
 especially if the string has a fractional part.</li> <li>By calling
 the UnsignedMantissa, Exponent, and IsNegative properties, and
 calling the IsInfinity, IsQuietNaN, and IsSignalingNaN methods. The
 return values combined will uniquely identify a particular
 arbitrary-precision binary float value.</li></ul> <p>If an operation
 requires creating an intermediate value that might be too big to fit
 in memory (or might require more than 2 gigabytes of memory to store
 -- due to the current use of a 32-bit integer internally as a
 length), the operation may signal an invalid-operation flag and
 return not-a-number (NaN). In certain rare cases, the compareTo
 method may throw OutOfMemoryError (called OutOfMemoryError in
 Java) in the same circumstances.</p> <p><b>Thread safety</b></p>
 <p>Instances of this class are immutable, so they are inherently safe
 for use by multiple threads. Multiple instances of this object with
 the same properties are interchangeable, so they should not be
 compared using the "==" operator (which might only check if each side
 of the operator is the same instance).</p> <p><b>Comparison
 considerations</b></p> <p>This class's natural ordering (under the
 compareTo method) is not consistent with the Equals method. This
 means that two values that compare as equal under the compareTo
 method might not be equal under the Equals method. The compareTo
 method compares the mathematical values of the two instances passed
 to it (and considers two different NaN values as equal), while two
 instances with the same mathematical value, but different exponents,
 will be considered unequal under the Equals method.</p>

## Fields

* `static EFloat NaN`<br>
 A not-a-number value.
* `static EFloat NegativeInfinity`<br>
 Negative infinity, less than any other number.
* `static EFloat NegativeZero`<br>
 Represents the number negative zero.
* `static EFloat One`<br>
 Represents the number 1.
* `static EFloat PositiveInfinity`<br>
 Positive infinity, greater than any other number.
* `static EFloat SignalingNaN`<br>
 A not-a-number value that signals an invalid operation flag when it's passed
 as an argument to any arithmetic operation in arbitrary-precision
 binary float.
* `static EFloat Ten`<br>
 Represents the number 10.
* `static EFloat Zero`<br>
 Represents the number 0.

## Methods

* `EFloat Abs()`<br>
* `EFloat Abs​(EContext context)`<br>
* `EFloat Add​(EFloat otherValue)`<br>
* `EFloat Add​(EFloat otherValue,
   EContext ctx)`<br>
* `int compareTo​(EFloat other)`<br>
* `EFloat CompareToSignal​(EFloat other,
               EContext ctx)`<br>
* `int CompareToTotal​(EFloat other)`<br>
* `int CompareToTotal​(EFloat other,
              EContext ctx)`<br>
* `int CompareToTotalMagnitude​(EFloat other)`<br>
* `EFloat CompareToWithContext​(EFloat other,
                    EContext ctx)`<br>
* `EFloat CopySign​(EFloat other)`<br>
* `static EFloat Create​(int mantissaSmall,
      int exponentSmall)`<br>
* `static EFloat Create​(EInteger mantissa,
      EInteger exponent)`<br>
* `static EFloat CreateNaN​(EInteger diag)`<br>
* `static EFloat CreateNaN​(EInteger diag,
         boolean signaling,
         boolean negative,
         EContext ctx)`<br>
* `EFloat Divide​(EFloat divisor)`<br>
* `EFloat Divide​(EFloat divisor,
      EContext ctx)`<br>
* `EFloat[] DivideAndRemainderNaturalScale​(EFloat divisor)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EFloat[] DivideAndRemainderNaturalScale​(EFloat divisor,
                              EContext ctx)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EFloat DivideToExponent​(EFloat divisor,
                long desiredExponentSmall,
                EContext ctx)`<br>
* `EFloat DivideToExponent​(EFloat divisor,
                long desiredExponentSmall,
                ERounding rounding)`<br>
* `EFloat DivideToExponent​(EFloat divisor,
                EInteger exponent,
                EContext ctx)`<br>
* `EFloat DivideToExponent​(EFloat divisor,
                EInteger desiredExponent,
                ERounding rounding)`<br>
* `EFloat DivideToIntegerNaturalScale​(EFloat divisor)`<br>
* `EFloat DivideToIntegerNaturalScale​(EFloat divisor,
                           EContext ctx)`<br>
* `EFloat DivideToIntegerZeroScale​(EFloat divisor,
                        EContext ctx)`<br>
* `EFloat DivideToSameExponent​(EFloat divisor,
                    ERounding rounding)`<br>
* `EFloat[] DivRemNaturalScale​(EFloat divisor)`<br>
* `EFloat[] DivRemNaturalScale​(EFloat divisor,
                  EContext ctx)`<br>
* `boolean equals​(EFloat other)`<br>
* `boolean equals​(Object obj)`<br>
* `boolean EqualsInternal​(EFloat otherValue)`<br>
* `EFloat Exp​(EContext ctx)`<br>
* `static EFloat FromByte​(byte inputByte)`<br>
* `static EFloat FromDouble​(double dbl)`<br>
* `static EFloat FromEInteger​(EInteger bigint)`<br>
* `static EFloat FromInt16​(short inputInt16)`<br>
* `static EFloat FromInt32​(int inputInt32)`<br>
* `static EFloat FromInt64​(long inputInt64)`<br>
* `static EFloat FromSingle​(float flt)`<br>
* `static EFloat FromString​(String str)`<br>
* `static EFloat FromString​(String str,
          int offset,
          int length)`<br>
* `static EFloat FromString​(String str,
          int offset,
          int length,
          EContext ctx)`<br>
* `static EFloat FromString​(String str,
          EContext ctx)`<br>
* `EInteger getExponent()`<br>
* `EInteger getMantissa()`<br>
* `EInteger getUnsignedMantissa()`<br>
* `int hashCode()`<br>
* `boolean isFinite()`<br>
* `boolean IsInfinity()`<br>
* `boolean IsNaN()`<br>
* `boolean isNegative()`<br>
* `boolean IsNegativeInfinity()`<br>
* `boolean IsPositiveInfinity()`<br>
* `boolean IsQuietNaN()`<br>
* `boolean IsSignalingNaN()`<br>
* `boolean isZero()`<br>
* `EFloat Log​(EContext ctx)`<br>
* `EFloat Log10​(EContext ctx)`<br>
* `static EFloat Max​(EFloat first,
   EFloat second)`<br>
* `static EFloat Max​(EFloat first,
   EFloat second,
   EContext ctx)`<br>
* `static EFloat MaxMagnitude​(EFloat first,
            EFloat second)`<br>
* `static EFloat MaxMagnitude​(EFloat first,
            EFloat second,
            EContext ctx)`<br>
* `static EFloat Min​(EFloat first,
   EFloat second)`<br>
* `static EFloat Min​(EFloat first,
   EFloat second,
   EContext ctx)`<br>
* `static EFloat MinMagnitude​(EFloat first,
            EFloat second)`<br>
* `static EFloat MinMagnitude​(EFloat first,
            EFloat second,
            EContext ctx)`<br>
* `EFloat MovePointLeft​(int places)`<br>
* `EFloat MovePointLeft​(int places,
             EContext ctx)`<br>
* `EFloat MovePointLeft​(EInteger bigPlaces)`<br>
* `EFloat MovePointLeft​(EInteger bigPlaces,
             EContext ctx)`<br>
* `EFloat MovePointRight​(int places)`<br>
* `EFloat MovePointRight​(int places,
              EContext ctx)`<br>
* `EFloat MovePointRight​(EInteger bigPlaces)`<br>
* `EFloat MovePointRight​(EInteger bigPlaces,
              EContext ctx)`<br>
* `EFloat Multiply​(EFloat otherValue)`<br>
* `EFloat Multiply​(EFloat op,
        EContext ctx)`<br>
* `EFloat MultiplyAndAdd​(EFloat multiplicand,
              EFloat augend)`<br>
* `EFloat MultiplyAndAdd​(EFloat op,
              EFloat augend,
              EContext ctx)`<br>
* `EFloat MultiplyAndSubtract​(EFloat op,
                   EFloat subtrahend,
                   EContext ctx)`<br>
* `EFloat Negate()`<br>
* `EFloat Negate​(EContext context)`<br>
* `EFloat NextMinus​(EContext ctx)`<br>
* `EFloat NextPlus​(EContext ctx)`<br>
* `EFloat NextToward​(EFloat otherValue,
          EContext ctx)`<br>
* `static EFloat PI​(EContext ctx)`<br>
* `EFloat Plus​(EContext ctx)`<br>
* `EFloat Pow​(int exponentSmall)`<br>
* `EFloat Pow​(int exponentSmall,
   EContext ctx)`<br>
* `EFloat Pow​(EFloat exponent,
   EContext ctx)`<br>
* `EInteger Precision()`<br>
* `EFloat Quantize​(int desiredExponentInt,
        EContext ctx)`<br>
* `EFloat Quantize​(EFloat otherValue,
        EContext ctx)`<br>
* `EFloat Quantize​(EInteger desiredExponent,
        EContext ctx)`<br>
* `EFloat Reduce​(EContext ctx)`<br>
* `EFloat Remainder​(EFloat divisor,
         EContext ctx)`<br>
* `EFloat RemainderNaturalScale​(EFloat divisor)`<br>
* `EFloat RemainderNaturalScale​(EFloat divisor,
                     EContext ctx)`<br>
* `EFloat RemainderNear​(EFloat divisor,
             EContext ctx)`<br>
* `EFloat RoundToExponent​(int exponentSmall,
               EContext ctx)`<br>
* `EFloat RoundToExponent​(EInteger exponent,
               EContext ctx)`<br>
* `EFloat RoundToExponentExact​(int exponentSmall,
                    EContext ctx)`<br>
* `EFloat RoundToExponentExact​(EInteger exponent,
                    EContext ctx)`<br>
* `EFloat RoundToExponentExact​(EInteger exponent,
                    ERounding rounding)`<br>
* `EFloat RoundToIntegerExact​(EContext ctx)`<br>
* `EFloat RoundToIntegerNoRoundedFlag​(EContext ctx)`<br>
* `EFloat RoundToIntegralExact​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerExact.
 Renamed to RoundToIntegerExact.
* `EFloat RoundToIntegralNoRoundedFlag​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerNoRoundedFlag.
 Renamed to RoundToIntegerNoRoundedFlag.
* `EFloat RoundToPrecision​(EContext ctx)`<br>
* `EFloat ScaleByPowerOfTwo​(int places)`<br>
* `EFloat ScaleByPowerOfTwo​(int places,
                 EContext ctx)`<br>
* `EFloat ScaleByPowerOfTwo​(EInteger bigPlaces)`<br>
* `EFloat ScaleByPowerOfTwo​(EInteger bigPlaces,
                 EContext ctx)`<br>
* `int signum()`<br>
* `EFloat Sqrt​(EContext ctx)`<br>
* `EFloat SquareRoot​(EContext ctx)`<br>
 Deprecated.
Renamed to Sqrt.
 Renamed to Sqrt.
* `EFloat Subtract​(EFloat otherValue)`<br>
* `EFloat Subtract​(EFloat otherValue,
        EContext ctx)`<br>
* `byte ToByteChecked()`<br>
* `byte ToByteIfExact()`<br>
* `byte ToByteUnchecked()`<br>
* `double ToDouble()`<br>
* `EDecimal ToEDecimal()`<br>
* `EInteger ToEInteger()`<br>
* `EInteger ToEIntegerExact()`<br>
 Deprecated.
Renamed to ToEIntegerIfExact.
 Renamed to ToEIntegerIfExact.
* `EInteger ToEIntegerIfExact()`<br>
* `String ToEngineeringString()`<br>
* `EDecimal ToExtendedDecimal()`<br>
 Deprecated.
Renamed to ToEDecimal.
 Renamed to ToEDecimal.
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
* `String ToShortestString​(EContext ctx)`<br>
* `float ToSingle()`<br>
* `String toString()`<br>
* `EFloat Ulp()`<br>

## Field Details

### NaN
    public static final EFloat NaN
A not-a-number value.
### NegativeInfinity
    public static final EFloat NegativeInfinity
Negative infinity, less than any other number.
### NegativeZero
    public static final EFloat NegativeZero
Represents the number negative zero.
### One
    public static final EFloat One
Represents the number 1.
### PositiveInfinity
    public static final EFloat PositiveInfinity
Positive infinity, greater than any other number.
### SignalingNaN
    public static final EFloat SignalingNaN
A not-a-number value that signals an invalid operation flag when it's passed
 as an argument to any arithmetic operation in arbitrary-precision
 binary float.
### Ten
    public static final EFloat Ten
Represents the number 10.
### Zero
    public static final EFloat Zero
Represents the number 0.
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
### Create
    public static EFloat Create​(int mantissaSmall, int exponentSmall)

**Parameters:**

* <code>mantissaSmall</code> - Not documented yet.

* <code>exponentSmall</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Create
    public static EFloat Create​(EInteger mantissa, EInteger exponent)

**Parameters:**

* <code>mantissa</code> - Not documented yet.

* <code>exponent</code> - Not documented yet.

**Returns:**

* An EFloat object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### CreateNaN
    public static EFloat CreateNaN​(EInteger diag)

**Parameters:**

* <code>diag</code> - Not documented yet.

**Returns:**

* An EFloat object.

### CreateNaN
    public static EFloat CreateNaN​(EInteger diag, boolean signaling, boolean negative, EContext ctx)

**Parameters:**

* <code>diag</code> - Not documented yet.

* <code>signaling</code> - Not documented yet.

* <code>negative</code> - Not documented yet. (3).

* <code>ctx</code> - Not documented yet. (4).

**Returns:**

* An EFloat object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### FromDouble
    public static EFloat FromDouble​(double dbl)

**Parameters:**

* <code>dbl</code> - Not documented yet.

**Returns:**

* An EFloat object.

### FromEInteger
    public static EFloat FromEInteger​(EInteger bigint)

**Parameters:**

* <code>bigint</code> - Not documented yet.

**Returns:**

* An EFloat object.

### FromSingle
    public static EFloat FromSingle​(float flt)

**Parameters:**

* <code>flt</code> - Not documented yet.

**Returns:**

* An EFloat object.

### FromString
    public static EFloat FromString​(String str, int offset, int length, EContext ctx)

**Parameters:**

* <code>str</code> - Not documented yet.

* <code>offset</code> - A zero-based index showing where the desired portion of <code>
 str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

* <code>IllegalArgumentException</code> - Either "offset" or "length" is less than 0 or
 greater than "str"'s length, or "str"'s length minus "offset" is less
 than "length".

### FromString
    public static EFloat FromString​(String str)

**Parameters:**

* <code>str</code> - Not documented yet.

**Returns:**

* An EFloat object.

### FromString
    public static EFloat FromString​(String str, EContext ctx)

**Parameters:**

* <code>str</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### FromString
    public static EFloat FromString​(String str, int offset, int length)

**Parameters:**

* <code>str</code> - Not documented yet.

* <code>offset</code> - A zero-based index showing where the desired portion of <code>
 str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

**Returns:**

* An EFloat object.

**Throws:**

* <code>IllegalArgumentException</code> - Either "offset" or "length" is less than 0 or
 greater than "str"'s length, or "str"'s length minus "offset" is less
 than "length".

* <code>NullPointerException</code> - The parameter "str" is null.

### Max
    public static EFloat Max​(EFloat first, EFloat second, EContext ctx)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EFloat object.

### Max
    public static EFloat Max​(EFloat first, EFloat second)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MaxMagnitude
    public static EFloat MaxMagnitude​(EFloat first, EFloat second, EContext ctx)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EFloat object.

### MaxMagnitude
    public static EFloat MaxMagnitude​(EFloat first, EFloat second)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Min
    public static EFloat Min​(EFloat first, EFloat second, EContext ctx)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EFloat object.

### Min
    public static EFloat Min​(EFloat first, EFloat second)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MinMagnitude
    public static EFloat MinMagnitude​(EFloat first, EFloat second, EContext ctx)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EFloat object.

### MinMagnitude
    public static EFloat MinMagnitude​(EFloat first, EFloat second)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

**Returns:**

* An EFloat object.

### PI
    public static EFloat PI​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Abs
    public EFloat Abs()

**Returns:**

* An EFloat object.

### Abs
    public EFloat Abs​(EContext context)

**Parameters:**

* <code>context</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Add
    public EFloat Add​(EFloat otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Add
    public EFloat Add​(EFloat otherValue, EContext ctx)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### compareTo
    public int compareTo​(EFloat other)

**Specified by:**

* <code>compareTo</code>&nbsp;in interface&nbsp;<code>Comparable&lt;EFloat&gt;</code>

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToSignal
    public EFloat CompareToSignal​(EFloat other, EContext ctx)

**Parameters:**

* <code>other</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### CompareToTotal
    public int CompareToTotal​(EFloat other, EContext ctx)

**Parameters:**

* <code>other</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToTotal
    public int CompareToTotal​(EFloat other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToTotalMagnitude
    public int CompareToTotalMagnitude​(EFloat other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToWithContext
    public EFloat CompareToWithContext​(EFloat other, EContext ctx)

**Parameters:**

* <code>other</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### CopySign
    public EFloat CopySign​(EFloat other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* An EFloat object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### Divide
    public EFloat Divide​(EFloat divisor)

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Divide
    public EFloat Divide​(EFloat divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### DivideAndRemainderNaturalScale
    @Deprecated public EFloat[] DivideAndRemainderNaturalScale​(EFloat divisor)
Deprecated.
<div class='deprecationComment'>Renamed to DivRemNaturalScale.</div>

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EFloat[] object.

### DivideAndRemainderNaturalScale
    @Deprecated public EFloat[] DivideAndRemainderNaturalScale​(EFloat divisor, EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to DivRemNaturalScale.</div>

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat[] object.

### DivideToExponent
    public EFloat DivideToExponent​(EFloat divisor, long desiredExponentSmall, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>desiredExponentSmall</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EFloat object.

### DivideToExponent
    public EFloat DivideToExponent​(EFloat divisor, long desiredExponentSmall, ERounding rounding)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>desiredExponentSmall</code> - Not documented yet.

* <code>rounding</code> - Not documented yet. (3).

**Returns:**

* An EFloat object.

### DivideToExponent
    public EFloat DivideToExponent​(EFloat divisor, EInteger exponent, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>exponent</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EFloat object.

### DivideToExponent
    public EFloat DivideToExponent​(EFloat divisor, EInteger desiredExponent, ERounding rounding)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>desiredExponent</code> - Not documented yet.

* <code>rounding</code> - Not documented yet. (3).

**Returns:**

* An EFloat object.

### DivideToIntegerNaturalScale
    public EFloat DivideToIntegerNaturalScale​(EFloat divisor)

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EFloat object.

### DivideToIntegerNaturalScale
    public EFloat DivideToIntegerNaturalScale​(EFloat divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### DivideToIntegerZeroScale
    public EFloat DivideToIntegerZeroScale​(EFloat divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### DivideToSameExponent
    public EFloat DivideToSameExponent​(EFloat divisor, ERounding rounding)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>rounding</code> - Not documented yet.

**Returns:**

* An EFloat object.

### DivRemNaturalScale
    public EFloat[] DivRemNaturalScale​(EFloat divisor)

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EFloat[] object.

### DivRemNaturalScale
    public EFloat[] DivRemNaturalScale​(EFloat divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat[] object.

### equals
    public boolean equals​(EFloat other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A Boolean object.

### equals
    public boolean equals​(Object obj)

**Overrides:**

* <code>equals</code>&nbsp;in class&nbsp;<code>Object</code>

**Parameters:**

* <code>obj</code> - Not documented yet.

**Returns:**

* A Boolean object.

### EqualsInternal
    public boolean EqualsInternal​(EFloat otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* A Boolean object.

### Exp
    public EFloat Exp​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### hashCode
    public int hashCode()

**Overrides:**

* <code>hashCode</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A 32-bit signed integer.

### IsInfinity
    public boolean IsInfinity()

**Returns:**

* A Boolean object.

### IsNaN
    public boolean IsNaN()

**Returns:**

* A Boolean object.

### IsNegativeInfinity
    public boolean IsNegativeInfinity()

**Returns:**

* A Boolean object.

### IsPositiveInfinity
    public boolean IsPositiveInfinity()

**Returns:**

* A Boolean object.

### IsQuietNaN
    public boolean IsQuietNaN()

**Returns:**

* A Boolean object.

### IsSignalingNaN
    public boolean IsSignalingNaN()

**Returns:**

* A Boolean object.

### Log
    public EFloat Log​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Log10
    public EFloat Log10​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MovePointLeft
    public EFloat MovePointLeft​(int places)

**Parameters:**

* <code>places</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MovePointLeft
    public EFloat MovePointLeft​(int places, EContext ctx)

**Parameters:**

* <code>places</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MovePointLeft
    public EFloat MovePointLeft​(EInteger bigPlaces)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MovePointLeft
    public EFloat MovePointLeft​(EInteger bigPlaces, EContext ctx)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MovePointRight
    public EFloat MovePointRight​(int places)

**Parameters:**

* <code>places</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MovePointRight
    public EFloat MovePointRight​(int places, EContext ctx)

**Parameters:**

* <code>places</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MovePointRight
    public EFloat MovePointRight​(EInteger bigPlaces)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MovePointRight
    public EFloat MovePointRight​(EInteger bigPlaces, EContext ctx)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Multiply
    public EFloat Multiply​(EFloat otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Multiply
    public EFloat Multiply​(EFloat op, EContext ctx)

**Parameters:**

* <code>op</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MultiplyAndAdd
    public EFloat MultiplyAndAdd​(EFloat multiplicand, EFloat augend)

**Parameters:**

* <code>multiplicand</code> - Not documented yet.

* <code>augend</code> - Not documented yet.

**Returns:**

* An EFloat object.

### MultiplyAndAdd
    public EFloat MultiplyAndAdd​(EFloat op, EFloat augend, EContext ctx)

**Parameters:**

* <code>op</code> - Not documented yet.

* <code>augend</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EFloat object.

### MultiplyAndSubtract
    public EFloat MultiplyAndSubtract​(EFloat op, EFloat subtrahend, EContext ctx)

**Parameters:**

* <code>op</code> - Not documented yet.

* <code>subtrahend</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EFloat object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### Negate
    public EFloat Negate()

**Returns:**

* An EFloat object.

### Negate
    public EFloat Negate​(EContext context)

**Parameters:**

* <code>context</code> - Not documented yet.

**Returns:**

* An EFloat object.

### NextMinus
    public EFloat NextMinus​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### NextPlus
    public EFloat NextPlus​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### NextToward
    public EFloat NextToward​(EFloat otherValue, EContext ctx)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Plus
    public EFloat Plus​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Pow
    public EFloat Pow​(EFloat exponent, EContext ctx)

**Parameters:**

* <code>exponent</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Pow
    public EFloat Pow​(int exponentSmall, EContext ctx)

**Parameters:**

* <code>exponentSmall</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Pow
    public EFloat Pow​(int exponentSmall)

**Parameters:**

* <code>exponentSmall</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Precision
    public EInteger Precision()

**Returns:**

* An EInteger object.

### Quantize
    public EFloat Quantize​(EInteger desiredExponent, EContext ctx)

**Parameters:**

* <code>desiredExponent</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Quantize
    public EFloat Quantize​(int desiredExponentInt, EContext ctx)

**Parameters:**

* <code>desiredExponentInt</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Quantize
    public EFloat Quantize​(EFloat otherValue, EContext ctx)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Reduce
    public EFloat Reduce​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Remainder
    public EFloat Remainder​(EFloat divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RemainderNaturalScale
    public EFloat RemainderNaturalScale​(EFloat divisor)

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RemainderNaturalScale
    public EFloat RemainderNaturalScale​(EFloat divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RemainderNear
    public EFloat RemainderNear​(EFloat divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RoundToExponent
    public EFloat RoundToExponent​(EInteger exponent, EContext ctx)

**Parameters:**

* <code>exponent</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RoundToExponent
    public EFloat RoundToExponent​(int exponentSmall, EContext ctx)

**Parameters:**

* <code>exponentSmall</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RoundToExponentExact
    public EFloat RoundToExponentExact​(EInteger exponent, EContext ctx)

**Parameters:**

* <code>exponent</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RoundToExponentExact
    public EFloat RoundToExponentExact​(EInteger exponent, ERounding rounding)

**Parameters:**

* <code>exponent</code> - Not documented yet.

* <code>rounding</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RoundToExponentExact
    public EFloat RoundToExponentExact​(int exponentSmall, EContext ctx)

**Parameters:**

* <code>exponentSmall</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RoundToIntegerExact
    public EFloat RoundToIntegerExact​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RoundToIntegerNoRoundedFlag
    public EFloat RoundToIntegerNoRoundedFlag​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RoundToIntegralExact
    @Deprecated public EFloat RoundToIntegralExact​(EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to RoundToIntegerExact.</div>

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RoundToIntegralNoRoundedFlag
    @Deprecated public EFloat RoundToIntegralNoRoundedFlag​(EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to RoundToIntegerNoRoundedFlag.</div>

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### RoundToPrecision
    public EFloat RoundToPrecision​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo​(int places)

**Parameters:**

* <code>places</code> - Not documented yet.

**Returns:**

* An EFloat object.

### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo​(int places, EContext ctx)

**Parameters:**

* <code>places</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo​(EInteger bigPlaces)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

**Returns:**

* An EFloat object.

### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo​(EInteger bigPlaces, EContext ctx)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Sqrt
    public EFloat Sqrt​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### SquareRoot
    @Deprecated public EFloat SquareRoot​(EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to Sqrt.</div>

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Subtract
    public EFloat Subtract​(EFloat otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Subtract
    public EFloat Subtract​(EFloat otherValue, EContext ctx)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### ToDouble
    public double ToDouble()

**Returns:**

* A 64-bit floating-point number.

### ToEDecimal
    public EDecimal ToEDecimal()

**Returns:**

* An EDecimal object.

### ToEInteger
    public EInteger ToEInteger()

**Returns:**

* An EInteger object.

### ToEIntegerExact
    @Deprecated public EInteger ToEIntegerExact()
Deprecated.
<div class='deprecationComment'>Renamed to ToEIntegerIfExact.</div>

**Returns:**

* An EInteger object.

### ToEIntegerIfExact
    public EInteger ToEIntegerIfExact()

**Returns:**

* An EInteger object.

### ToEngineeringString
    public String ToEngineeringString()

**Returns:**

* A string object.

### ToExtendedDecimal
    @Deprecated public EDecimal ToExtendedDecimal()
Deprecated.
<div class='deprecationComment'>Renamed to ToEDecimal.</div>

**Returns:**

* An EDecimal object.

### ToPlainString
    public String ToPlainString()

**Returns:**

* A string object.

### ToShortestString
    public String ToShortestString​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* A string object.

### ToSingle
    public float ToSingle()

**Returns:**

* A 32-bit floating-point number.

### toString
    public String toString()

**Overrides:**

* <code>toString</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A string object.

### Ulp
    public EFloat Ulp()

**Returns:**

* An EFloat object.

### ToByteChecked
    public byte ToByteChecked()

**Returns:**

* A Byte object.

### ToByteUnchecked
    public byte ToByteUnchecked()

**Returns:**

* A Byte object.

### ToByteIfExact
    public byte ToByteIfExact()

**Returns:**

* A Byte object.

### FromByte
    public static EFloat FromByte​(byte inputByte)

**Parameters:**

* <code>inputByte</code> - Not documented yet.

**Returns:**

* An EFloat object.

### ToInt16Checked
    public short ToInt16Checked()

**Returns:**

* A 16-bit signed integer.

### ToInt16Unchecked
    public short ToInt16Unchecked()

**Returns:**

* A 16-bit signed integer.

### ToInt16IfExact
    public short ToInt16IfExact()

**Returns:**

* A 16-bit signed integer.

### FromInt16
    public static EFloat FromInt16​(short inputInt16)

**Parameters:**

* <code>inputInt16</code> - Not documented yet.

**Returns:**

* An EFloat object.

### ToInt32Checked
    public int ToInt32Checked()

**Returns:**

* A 32-bit signed integer.

### ToInt32Unchecked
    public int ToInt32Unchecked()

**Returns:**

* A 32-bit signed integer.

### ToInt32IfExact
    public int ToInt32IfExact()

**Returns:**

* A 32-bit signed integer.

### FromInt32
    public static EFloat FromInt32​(int inputInt32)

**Parameters:**

* <code>inputInt32</code> - Not documented yet.

**Returns:**

* An EFloat object.

### ToInt64Checked
    public long ToInt64Checked()

**Returns:**

* A 64-bit signed integer.

### ToInt64Unchecked
    public long ToInt64Unchecked()

**Returns:**

* A 64-bit signed integer.

### ToInt64IfExact
    public long ToInt64IfExact()

**Returns:**

* A 64-bit signed integer.

### FromInt64
    public static EFloat FromInt64​(long inputInt64)

**Parameters:**

* <code>inputInt64</code> - Not documented yet.

**Returns:**

* An EFloat object.
