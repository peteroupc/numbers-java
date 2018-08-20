# com.upokecenter.numbers.EDecimal

    public final class EDecimal extends Object implements Comparable<EDecimal>

Represents an arbitrary-precision decimal floating-point number. (The "E"
 stands for "extended", meaning that instances of this class can be
 values other than numbers proper, such as infinity and not-a-number.)
 <p><b>About decimal arithmetic</b> </p> <p>Decimal (base-10)
 arithmetic, such as that provided by this class, is appropriate for
 calculations involving such real-world data as prices and other sums
 of money, tax rates, and measurements. These calculations often
 involve multiplying or dividing one decimal with another decimal, or
 performing other operations on decimal numbers. Many of these
 calculations also rely on rounding behavior in which the result after
 rounding is a decimal number (for example, multiplying a price by a
 premium rate, then rounding, should result in a decimal amount of
 money).</p> <p>On the other hand, most implementations of
 <code>float</code> and <code>double</code> , including in C# and Java, store
 numbers in a binary (base-2) floating-point format and use binary
 floating-point arithmetic. Many decimal numbers can't be represented
 exactly in binary floating-point format (regardless of its length).
 Applying binary arithmetic to numbers intended to be decimals can
 sometimes lead to unintuitive results, as is shown in the description
 for the FromDouble() method of this class.</p> <p><b>About EDecimal
 instances</b> </p> <p>Each instance of this class consists of an
 integer mantissa (significand) and an integer exponent, both
 arbitrary-precision. The value of the number equals mantissa
 (significand) * 10^exponent.</p> <p>The mantissa (significand) is the
 value of the digits that make up a number, ignoring the decimal point
 and exponent. For example, in the number 2356.78, the mantissa
 (significand) is 235678. The exponent is where the "floating" decimal
 point of the number is located. A positive exponent means "move it to
 the right", and a negative exponent means "move it to the left." In
 the example 2, 356.78, the exponent is -2, since it has 2 decimal
 places and the decimal point is "moved to the left by 2." Therefore,
 in the arbitrary-precision decimal representation, this number would
 be stored as 235678 * 10^-2.</p> <p>The mantissa (significand) and
 exponent format preserves trailing zeros in the number's value. This
 may give rise to multiple ways to store the same value. For example,
 1.00 and 1 would be stored differently, even though they have the
 same value. In the first case, 100 * 10^-2 (100 with decimal point
 moved left by 2), and in the second case, 1 * 10^0 (1 with decimal
 point moved 0).</p> <p>This class also supports values for negative
 zero, not-a-number (NaN) values, and infinity. <b>Negative zero</b>
 is generally used when a negative number is rounded to 0; it has the
 same mathematical value as positive zero. <b>Infinity</b> is
 generally used when a non-zero number is divided by zero, or when a
 very high or very low number can't be represented in a given exponent
 range. <b>Not-a-number</b> is generally used to signal errors.</p>
 <p>This class implements the General Decimal Arithmetic Specification
 version 1.70 (except part of chapter 6):
 <code>http://speleotrove.com/decimal/decarith.html</code> </p> <p><b>Errors
 and Exceptions</b> </p> <p>Passing a signaling NaN to any arithmetic
 operation shown here will signal the flag FlagInvalid and return a
 quiet NaN, even if another operand to that operation is a quiet NaN,
 unless noted otherwise.</p> <p>Passing a quiet NaN to any arithmetic
 operation shown here will return a quiet NaN, unless noted otherwise.
 Invalid operations will also return a quiet NaN, as stated in the
 individual methods.</p> <p>Unless noted otherwise, passing a null
 arbitrary-precision decimal argument to any method here will throw an
 exception.</p> <p>When an arithmetic operation signals the flag
 FlagInvalid, FlagOverflow, or FlagDivideByZero, it will not throw an
 exception too, unless the flag's trap is enabled in the arithmetic
 context (see EContext's Traps property).</p> <p>If an operation
 requires creating an intermediate value that might be too big to fit
 in memory (or might require more than 2 gigabytes of memory to store
 -- due to the current use of a 32-bit integer internally as a
 length), the operation may signal an invalid-operation flag and
 return not-a-number (NaN). In certain rare cases, the compareTo
 method may throw OutOfMemoryError (called OutOfMemoryError in
 Java) in the same circumstances.</p> <p><b>Serialization</b> </p>
 <p>An arbitrary-precision decimal value can be serialized (converted
 to a stable format) in one of the following ways:</p> <ul><li>By
 calling the toString() method, which will always return distinct
 strings for distinct arbitrary-precision decimal values.</li> <li>By
 calling the UnsignedMantissa, Exponent, and IsNegative properties,
 and calling the IsInfinity, IsQuietNaN, and IsSignalingNaN methods.
 The return values combined will uniquely identify a particular
 arbitrary-precision decimal value.</li> </ul> <p><b>Thread safety</b>
 </p> <p>Instances of this class are immutable, so they are inherently
 safe for use by multiple threads. Multiple instances of this object
 with the same properties are interchangeable, so they should not be
 compared using the "==" operator (which might only check if each side
 of the operator is the same instance).</p> <p><b>Comparison
 considerations</b> </p> <p>This class's natural ordering (under the
 compareTo method) is not consistent with the Equals method. This
 means that two values that compare as equal under the compareTo
 method might not be equal under the Equals method. The compareTo
 method compares the mathematical values of the two instances passed
 to it (and considers two different NaN values as equal), while two
 instances with the same mathematical value, but different exponents,
 will be considered unequal under the Equals method.</p> <p><b>Forms
 of numbers</b> </p> <p>There are several other types of numbers that
 are mentioned in this class and elsewhere in this documentation. For
 reference, they are specified here.</p> <p><b>Unsigned integer</b> :
 An integer that's always 0 or greater, with the following maximum
 values:</p> <ul><li>8-bit unsigned integer, or <i>byte</i> :
 255.</li> <li>16-bit unsigned integer: 65535.</li> <li>32-bit
 unsigned integer: (2 <sup>32</sup> -1).</li> <li>64-bit unsigned
 integer: (2 <sup>64</sup> -1).</li> </ul> <p><b>Signed integer</b> :
 An integer in <i>two's-complement form</i> , with the following
 ranges:</p> <ul><li>8-bit signed integer: -128 to 127.</li>
 <li>16-bit signed integer: -32768 to 32767.</li> <li>32-bit signed
 integer: -2 <sup>31</sup> to (2 <sup>31</sup> - 1).</li> <li>64-bit
 signed integer: -2 <sup>63</sup> to (2 <sup>63</sup> - 1).</li> </ul>
 <p><b>Two's complement form</b> : In <i>two' s-complement form</i> ,
 nonnegative numbers have the highest (most significant) bit set to
 zero, and negative numbers have that bit (and all bits beyond) set to
 one, and a negative number is stored in such form by decreasing its
 absolute value by 1 and swapping the bits of the resulting
 number.</p> <p><b>64-bit floating-point number</b> : A 64-bit binary
 floating-point number, in the form <i>significand</i> * 2
 <sup><i>exponent</i> </sup> . The significand is 53 bits long
 (Precision) and the exponent ranges from -1074 (EMin) to 971 (EMax).
 The number is stored in the following format (commonly called the
 IEEE 754 format):</p> <pre>|C|BBB...BBB|AAAAAA...AAAAAA| </pre>
 <ul><li>A. Low 52 bits (Precision minus 1 bits): Lowest bits of the
 significand.</li> <li>B. Next 11 bits: Exponent area: <ul><li>If all
 bits are ones, this value is infinity if all bits in area A are
 zeros, or not-a-number (NaN) otherwise.</li> <li>If all bits are
 zeros, this is a subnormal number. The exponent is EMin and the
 highest bit of the significand is zero.</li> <li>If any other number,
 the exponent is this value reduced by 1, then raised by EMin, and the
 highest bit of the significand is one.</li> </ul> </li> <li>C.
 Highest bit: If one, this is a negative number.</li> </ul> <p>The
 elements described above are in the same order as the order of each
 bit of each element, that is, either most significant first or least
 significant first.</p> <p><b>32-bit binary floating-point number</b>
 : A 32-bit binary number which is stored similarly to a <i>64-bit
 floating-point number</i> , except that:</p> <ul><li>Precision is 24
 bits.</li> <li>EMin is -149.</li> <li>EMax is 104.</li> <li>A. The
 low 23 bits (Precision minus 1 bits) are the lowest bits of the
 significand.</li> <li>B. The next 8 bits are the exponent area.</li>
 <li>C. If the highest bit is one, this is a negative number.</li>
 </ul> <p><b>.NET Framework decimal</b> : A 128-bit decimal
 floating-point number, in the form <i>significand</i> * 10 <sup>-
 <i>scale</i> </sup> , where the scale ranges from 0 to 28. The number
 is stored in the following format:</p> <ul><li>Low 96 bits are the
 significand, as a 96-bit unsigned integer (all 96-bit values are
 allowed, up to (2 <sup>96</sup> -1)).</li> <li>Next 16 bits are
 unused.</li> <li>Next 8 bits are the scale, stored as an 8-bit
 unsigned integer.</li> <li>Next 7 bits are unused.</li> <li>If the
 highest bit is one, it's a negative number.</li> </ul> <p>The
 elements described above are in the same order as the order of each
 bit of each element, that is, either most significant first or least
 significant first.</p>

## Fields

* `static EDecimal NaN`<br>
 A not-a-number value.
* `static EDecimal NegativeInfinity`<br>
 Negative infinity, less than any other number.
* `static EDecimal NegativeZero`<br>
 Represents the number negative zero.
* `static EDecimal One`<br>
 Represents the number 1.
* `static EDecimal PositiveInfinity`<br>
 Positive infinity, greater than any other number.
* `static EDecimal SignalingNaN`<br>
 A not-a-number value that signals an invalid operation flag when it's passed
 as an argument to any arithmetic operation in arbitrary-precision
 decimal.
* `static EDecimal Ten`<br>
 Represents the number 10.
* `static EDecimal Zero`<br>
 Represents the number 0.

## Methods

* `EDecimal Abs()`<br>
* `EDecimal Abs​(EContext context)`<br>
* `EDecimal Add​(EDecimal otherValue)`<br>
* `EDecimal Add​(EDecimal otherValue,
   EContext ctx)`<br>
* `int compareTo​(EDecimal other)`<br>
* `int CompareToBinary​(EFloat other)`<br>
* `EDecimal CompareToSignal​(EDecimal other,
               EContext ctx)`<br>
* `int CompareToTotal​(EDecimal other)`<br>
* `int CompareToTotal​(EDecimal other,
              EContext ctx)`<br>
* `int CompareToTotalMagnitude​(EDecimal other)`<br>
* `EDecimal CompareToWithContext​(EDecimal other,
                    EContext ctx)`<br>
* `EDecimal CopySign​(EDecimal other)`<br>
* `static EDecimal Create​(int mantissaSmall,
      int exponentSmall)`<br>
* `static EDecimal Create​(EInteger mantissa,
      EInteger exponent)`<br>
* `static EDecimal CreateNaN​(EInteger diag)`<br>
* `static EDecimal CreateNaN​(EInteger diag,
         boolean signaling,
         boolean negative,
         EContext ctx)`<br>
* `EDecimal Divide​(EDecimal divisor)`<br>
* `EDecimal Divide​(EDecimal divisor,
      EContext ctx)`<br>
* `EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor,
                              EContext ctx)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EDecimal DivideToExponent​(EDecimal divisor,
                int desiredExponentInt)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                int desiredExponentInt,
                EContext ctx)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                int desiredExponentInt,
                ERounding rounding)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                long desiredExponentSmall)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                long desiredExponentSmall,
                EContext ctx)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                long desiredExponentSmall,
                ERounding rounding)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                EInteger exponent)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                EInteger exponent,
                EContext ctx)`<br>
* `EDecimal DivideToExponent​(EDecimal divisor,
                EInteger desiredExponent,
                ERounding rounding)`<br>
* `EDecimal DivideToIntegerNaturalScale​(EDecimal divisor)`<br>
* `EDecimal DivideToIntegerNaturalScale​(EDecimal divisor,
                           EContext ctx)`<br>
* `EDecimal DivideToIntegerZeroScale​(EDecimal divisor,
                        EContext ctx)`<br>
* `EDecimal DivideToSameExponent​(EDecimal divisor,
                    ERounding rounding)`<br>
* `EDecimal[] DivRemNaturalScale​(EDecimal divisor)`<br>
* `EDecimal[] DivRemNaturalScale​(EDecimal divisor,
                  EContext ctx)`<br>
* `boolean equals​(EDecimal other)`<br>
* `boolean equals​(Object obj)`<br>
* `EDecimal Exp​(EContext ctx)`<br>
* `static EDecimal FromByte​(byte inputByte)`<br>
* `static EDecimal FromDouble​(double dbl)`<br>
* `static EDecimal FromEFloat​(EFloat bigfloat)`<br>
* `static EDecimal FromEInteger​(EInteger bigint)`<br>
* `static EDecimal FromExtendedFloat​(EFloat ef)`<br>
 Deprecated.
Renamed to FromEFloat.
 Renamed to FromEFloat.
* `static EDecimal FromInt16​(short inputInt16)`<br>
* `static EDecimal FromInt32​(int valueSmaller)`<br>
* `static EDecimal FromInt64​(long valueSmall)`<br>
* `static EDecimal FromSingle​(float flt)`<br>
* `static EDecimal FromString​(String str)`<br>
* `static EDecimal FromString​(String str,
          int offset,
          int length)`<br>
* `static EDecimal FromString​(String str,
          int offset,
          int length,
          EContext ctx)`<br>
* `static EDecimal FromString​(String str,
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
* `EDecimal Log​(EContext ctx)`<br>
* `EDecimal Log10​(EContext ctx)`<br>
* `static EDecimal Max​(EDecimal first,
   EDecimal second)`<br>
* `static EDecimal Max​(EDecimal first,
   EDecimal second,
   EContext ctx)`<br>
* `static EDecimal MaxMagnitude​(EDecimal first,
            EDecimal second)`<br>
* `static EDecimal MaxMagnitude​(EDecimal first,
            EDecimal second,
            EContext ctx)`<br>
* `static EDecimal Min​(EDecimal first,
   EDecimal second)`<br>
* `static EDecimal Min​(EDecimal first,
   EDecimal second,
   EContext ctx)`<br>
* `static EDecimal MinMagnitude​(EDecimal first,
            EDecimal second)`<br>
* `static EDecimal MinMagnitude​(EDecimal first,
            EDecimal second,
            EContext ctx)`<br>
* `EDecimal MovePointLeft​(int places)`<br>
* `EDecimal MovePointLeft​(int places,
             EContext ctx)`<br>
* `EDecimal MovePointLeft​(EInteger bigPlaces)`<br>
* `EDecimal MovePointLeft​(EInteger bigPlaces,
             EContext ctx)`<br>
* `EDecimal MovePointRight​(int places)`<br>
* `EDecimal MovePointRight​(int places,
              EContext ctx)`<br>
* `EDecimal MovePointRight​(EInteger bigPlaces)`<br>
* `EDecimal MovePointRight​(EInteger bigPlaces,
              EContext ctx)`<br>
* `EDecimal Multiply​(EDecimal otherValue)`<br>
* `EDecimal Multiply​(EDecimal op,
        EContext ctx)`<br>
* `EDecimal MultiplyAndAdd​(EDecimal multiplicand,
              EDecimal augend)`<br>
* `EDecimal MultiplyAndAdd​(EDecimal op,
              EDecimal augend,
              EContext ctx)`<br>
* `EDecimal MultiplyAndSubtract​(EDecimal op,
                   EDecimal subtrahend,
                   EContext ctx)`<br>
* `EDecimal Negate()`<br>
* `EDecimal Negate​(EContext context)`<br>
* `EDecimal NextMinus​(EContext ctx)`<br>
* `EDecimal NextPlus​(EContext ctx)`<br>
* `EDecimal NextToward​(EDecimal otherValue,
          EContext ctx)`<br>
* `static EDecimal PI​(EContext ctx)`<br>
* `EDecimal Plus​(EContext ctx)`<br>
* `EDecimal Pow​(int exponentSmall)`<br>
* `EDecimal Pow​(int exponentSmall,
   EContext ctx)`<br>
* `EDecimal Pow​(EDecimal exponent,
   EContext ctx)`<br>
* `EInteger Precision()`<br>
* `EDecimal Quantize​(int desiredExponentInt,
        EContext ctx)`<br>
* `EDecimal Quantize​(int desiredExponentInt,
        ERounding rounding)`<br>
* `EDecimal Quantize​(EDecimal otherValue,
        EContext ctx)`<br>
* `EDecimal Quantize​(EInteger desiredExponent,
        EContext ctx)`<br>
* `EDecimal Reduce​(EContext ctx)`<br>
* `EDecimal Remainder​(EDecimal divisor,
         EContext ctx)`<br>
* `EDecimal RemainderNaturalScale​(EDecimal divisor)`<br>
* `EDecimal RemainderNaturalScale​(EDecimal divisor,
                     EContext ctx)`<br>
* `EDecimal RemainderNear​(EDecimal divisor,
             EContext ctx)`<br>
* `EDecimal RoundToExponent​(int exponentSmall)`<br>
* `EDecimal RoundToExponent​(int exponentSmall,
               EContext ctx)`<br>
* `EDecimal RoundToExponent​(int exponentSmall,
               ERounding rounding)`<br>
* `EDecimal RoundToExponent​(EInteger exponent)`<br>
* `EDecimal RoundToExponent​(EInteger exponent,
               EContext ctx)`<br>
* `EDecimal RoundToExponent​(EInteger exponent,
               ERounding rounding)`<br>
* `EDecimal RoundToExponentExact​(int exponentSmall,
                    EContext ctx)`<br>
* `EDecimal RoundToExponentExact​(int exponentSmall,
                    ERounding rounding)`<br>
* `EDecimal RoundToExponentExact​(EInteger exponent,
                    EContext ctx)`<br>
* `EDecimal RoundToIntegerExact​(EContext ctx)`<br>
* `EDecimal RoundToIntegerNoRoundedFlag​(EContext ctx)`<br>
* `EDecimal RoundToIntegralExact​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerExact.
 Renamed to RoundToIntegerExact.
* `EDecimal RoundToIntegralNoRoundedFlag​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerNoRoundedFlag.
 Renamed to RoundToIntegerNoRoundedFlag.
* `EDecimal RoundToPrecision​(EContext ctx)`<br>
* `EDecimal ScaleByPowerOfTen​(int places)`<br>
* `EDecimal ScaleByPowerOfTen​(int places,
                 EContext ctx)`<br>
* `EDecimal ScaleByPowerOfTen​(EInteger bigPlaces)`<br>
* `EDecimal ScaleByPowerOfTen​(EInteger bigPlaces,
                 EContext ctx)`<br>
* `int signum()`<br>
* `EDecimal Sqrt​(EContext ctx)`<br>
* `EDecimal SquareRoot​(EContext ctx)`<br>
 Deprecated.
Renamed to Sqrt.
 Renamed to Sqrt.
* `EDecimal Subtract​(EDecimal otherValue)`<br>
* `EDecimal Subtract​(EDecimal otherValue,
        EContext ctx)`<br>
* `byte ToByteChecked()`<br>
* `byte ToByteIfExact()`<br>
* `byte ToByteUnchecked()`<br>
* `double ToDouble()`<br>
* `EFloat ToEFloat()`<br>
* `EFloat ToEFloat​(EContext ec)`<br>
* `EInteger ToEInteger()`<br>
* `EInteger ToEIntegerExact()`<br>
 Deprecated.
Renamed to ToEIntegerIfExact.
 Renamed to ToEIntegerIfExact.
* `EInteger ToEIntegerIfExact()`<br>
* `String ToEngineeringString()`<br>
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
* `String ToPlainString()`<br>
* `float ToSingle()`<br>
* `String toString()`<br>
* `EDecimal Ulp()`<br>

## Field Details

### NaN
    public static final EDecimal NaN
A not-a-number value.
### NegativeInfinity
    public static final EDecimal NegativeInfinity
Negative infinity, less than any other number.
### NegativeZero
    public static final EDecimal NegativeZero
Represents the number negative zero.
### One
    public static final EDecimal One
Represents the number 1.
### PositiveInfinity
    public static final EDecimal PositiveInfinity
Positive infinity, greater than any other number.
### SignalingNaN
    public static final EDecimal SignalingNaN
A not-a-number value that signals an invalid operation flag when it's passed
 as an argument to any arithmetic operation in arbitrary-precision
 decimal.
### Ten
    public static final EDecimal Ten
Represents the number 10.
### Zero
    public static final EDecimal Zero
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
    public static EDecimal Create​(int mantissaSmall, int exponentSmall)

**Parameters:**

* <code>mantissaSmall</code> - Not documented yet.

* <code>exponentSmall</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Create
    public static EDecimal Create​(EInteger mantissa, EInteger exponent)

**Parameters:**

* <code>mantissa</code> - Not documented yet.

* <code>exponent</code> - Not documented yet.

**Returns:**

* An EDecimal object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### CreateNaN
    public static EDecimal CreateNaN​(EInteger diag)

**Parameters:**

* <code>diag</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### CreateNaN
    public static EDecimal CreateNaN​(EInteger diag, boolean signaling, boolean negative, EContext ctx)

**Parameters:**

* <code>diag</code> - Not documented yet.

* <code>signaling</code> - Not documented yet.

* <code>negative</code> - Not documented yet. (3).

* <code>ctx</code> - Not documented yet. (4).

**Returns:**

* An EDecimal object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### FromDouble
    public static EDecimal FromDouble​(double dbl)

**Parameters:**

* <code>dbl</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### FromEInteger
    public static EDecimal FromEInteger​(EInteger bigint)

**Parameters:**

* <code>bigint</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### FromExtendedFloat
    @Deprecated public static EDecimal FromExtendedFloat​(EFloat ef)
Deprecated.
<div class='deprecationComment'>Renamed to FromEFloat.</div>

**Parameters:**

* <code>ef</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### FromEFloat
    public static EDecimal FromEFloat​(EFloat bigfloat)

**Parameters:**

* <code>bigfloat</code> - Not documented yet.

**Returns:**

* An EDecimal object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### FromInt32
    public static EDecimal FromInt32​(int valueSmaller)

**Parameters:**

* <code>valueSmaller</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### FromInt64
    public static EDecimal FromInt64​(long valueSmall)

**Parameters:**

* <code>valueSmall</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### FromSingle
    public static EDecimal FromSingle​(float flt)

**Parameters:**

* <code>flt</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### FromString
    public static EDecimal FromString​(String str)

**Parameters:**

* <code>str</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### FromString
    public static EDecimal FromString​(String str, EContext ctx)

**Parameters:**

* <code>str</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### FromString
    public static EDecimal FromString​(String str, int offset, int length)

**Parameters:**

* <code>str</code> - Not documented yet.

* <code>offset</code> - A zero-based index showing where the desired portion of <code>
 str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

**Returns:**

* An EDecimal object.

**Throws:**

* <code>IllegalArgumentException</code> - Either "offset" or "length" is less than 0 or
 greater than "str"'s length, or "str"'s length minus "offset" is less
 than "length".

* <code>NullPointerException</code> - The parameter "str" is null.

### FromString
    public static EDecimal FromString​(String str, int offset, int length, EContext ctx)

**Parameters:**

* <code>str</code> - Not documented yet.

* <code>offset</code> - A zero-based index showing where the desired portion of <code>
 str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

* <code>IllegalArgumentException</code> - Either "offset" or "length" is less than 0 or
 greater than "str"'s length, or "str"'s length minus "offset" is less
 than "length".

### Max
    public static EDecimal Max​(EDecimal first, EDecimal second, EContext ctx)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### Max
    public static EDecimal Max​(EDecimal first, EDecimal second)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MaxMagnitude
    public static EDecimal MaxMagnitude​(EDecimal first, EDecimal second, EContext ctx)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### MaxMagnitude
    public static EDecimal MaxMagnitude​(EDecimal first, EDecimal second)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Min
    public static EDecimal Min​(EDecimal first, EDecimal second, EContext ctx)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### Min
    public static EDecimal Min​(EDecimal first, EDecimal second)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MinMagnitude
    public static EDecimal MinMagnitude​(EDecimal first, EDecimal second, EContext ctx)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### MinMagnitude
    public static EDecimal MinMagnitude​(EDecimal first, EDecimal second)

**Parameters:**

* <code>first</code> - Not documented yet.

* <code>second</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### PI
    public static EDecimal PI​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Abs
    public EDecimal Abs()

**Returns:**

* An EDecimal object.

### CopySign
    public EDecimal CopySign​(EDecimal other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* An EDecimal object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### Abs
    public EDecimal Abs​(EContext context)

**Parameters:**

* <code>context</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Add
    public EDecimal Add​(EDecimal otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Add
    public EDecimal Add​(EDecimal otherValue, EContext ctx)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### compareTo
    public int compareTo​(EDecimal other)

**Specified by:**

* <code>compareTo</code>&nbsp;in interface&nbsp;<code>Comparable&lt;EDecimal&gt;</code>

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToBinary
    public int CompareToBinary​(EFloat other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToSignal
    public EDecimal CompareToSignal​(EDecimal other, EContext ctx)

**Parameters:**

* <code>other</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### CompareToTotalMagnitude
    public int CompareToTotalMagnitude​(EDecimal other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToTotal
    public int CompareToTotal​(EDecimal other, EContext ctx)

**Parameters:**

* <code>other</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToTotal
    public int CompareToTotal​(EDecimal other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToWithContext
    public EDecimal CompareToWithContext​(EDecimal other, EContext ctx)

**Parameters:**

* <code>other</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Divide
    public EDecimal Divide​(EDecimal divisor)

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Divide
    public EDecimal Divide​(EDecimal divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### DivideAndRemainderNaturalScale
    @Deprecated public EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor)
Deprecated.
<div class='deprecationComment'>Renamed to DivRemNaturalScale.</div>

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EDecimal[] object.

### DivideAndRemainderNaturalScale
    @Deprecated public EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor, EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to DivRemNaturalScale.</div>

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal[] object.

### DivRemNaturalScale
    public EDecimal[] DivRemNaturalScale​(EDecimal divisor)

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EDecimal[] object.

### DivRemNaturalScale
    public EDecimal[] DivRemNaturalScale​(EDecimal divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal[] object.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, long desiredExponentSmall, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>desiredExponentSmall</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, int desiredExponentInt, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>desiredExponentInt</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, long desiredExponentSmall, ERounding rounding)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>desiredExponentSmall</code> - Not documented yet.

* <code>rounding</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, int desiredExponentInt, ERounding rounding)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>desiredExponentInt</code> - Not documented yet.

* <code>rounding</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, EInteger exponent, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>exponent</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, EInteger exponent)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>exponent</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, long desiredExponentSmall)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>desiredExponentSmall</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, int desiredExponentInt)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>desiredExponentInt</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, EInteger desiredExponent, ERounding rounding)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>desiredExponent</code> - Not documented yet.

* <code>rounding</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### DivideToIntegerNaturalScale
    public EDecimal DivideToIntegerNaturalScale​(EDecimal divisor)

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### DivideToIntegerNaturalScale
    public EDecimal DivideToIntegerNaturalScale​(EDecimal divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### DivideToIntegerZeroScale
    public EDecimal DivideToIntegerZeroScale​(EDecimal divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### DivideToSameExponent
    public EDecimal DivideToSameExponent​(EDecimal divisor, ERounding rounding)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>rounding</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### equals
    public boolean equals​(EDecimal other)

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

### Exp
    public EDecimal Exp​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

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
    public EDecimal Log​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Log10
    public EDecimal Log10​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MovePointLeft
    public EDecimal MovePointLeft​(int places)

**Parameters:**

* <code>places</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MovePointLeft
    public EDecimal MovePointLeft​(int places, EContext ctx)

**Parameters:**

* <code>places</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MovePointLeft
    public EDecimal MovePointLeft​(EInteger bigPlaces)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MovePointLeft
    public EDecimal MovePointLeft​(EInteger bigPlaces, EContext ctx)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MovePointRight
    public EDecimal MovePointRight​(int places)

**Parameters:**

* <code>places</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MovePointRight
    public EDecimal MovePointRight​(int places, EContext ctx)

**Parameters:**

* <code>places</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MovePointRight
    public EDecimal MovePointRight​(EInteger bigPlaces)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MovePointRight
    public EDecimal MovePointRight​(EInteger bigPlaces, EContext ctx)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Multiply
    public EDecimal Multiply​(EDecimal otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Multiply
    public EDecimal Multiply​(EDecimal op, EContext ctx)

**Parameters:**

* <code>op</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MultiplyAndAdd
    public EDecimal MultiplyAndAdd​(EDecimal multiplicand, EDecimal augend)

**Parameters:**

* <code>multiplicand</code> - Not documented yet.

* <code>augend</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### MultiplyAndAdd
    public EDecimal MultiplyAndAdd​(EDecimal op, EDecimal augend, EContext ctx)

**Parameters:**

* <code>op</code> - Not documented yet.

* <code>augend</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### MultiplyAndSubtract
    public EDecimal MultiplyAndSubtract​(EDecimal op, EDecimal subtrahend, EContext ctx)

**Parameters:**

* <code>op</code> - Not documented yet.

* <code>subtrahend</code> - Not documented yet.

* <code>ctx</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### Negate
    public EDecimal Negate()

**Returns:**

* An EDecimal object.

### Negate
    public EDecimal Negate​(EContext context)

**Parameters:**

* <code>context</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### NextMinus
    public EDecimal NextMinus​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### NextPlus
    public EDecimal NextPlus​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### NextToward
    public EDecimal NextToward​(EDecimal otherValue, EContext ctx)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Plus
    public EDecimal Plus​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Pow
    public EDecimal Pow​(EDecimal exponent, EContext ctx)

**Parameters:**

* <code>exponent</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Pow
    public EDecimal Pow​(int exponentSmall, EContext ctx)

**Parameters:**

* <code>exponentSmall</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Pow
    public EDecimal Pow​(int exponentSmall)

**Parameters:**

* <code>exponentSmall</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Precision
    public EInteger Precision()

**Returns:**

* An EInteger object.

### Quantize
    public EDecimal Quantize​(EInteger desiredExponent, EContext ctx)

**Parameters:**

* <code>desiredExponent</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Quantize
    public EDecimal Quantize​(int desiredExponentInt, ERounding rounding)

**Parameters:**

* <code>desiredExponentInt</code> - Not documented yet.

* <code>rounding</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Quantize
    public EDecimal Quantize​(int desiredExponentInt, EContext ctx)

**Parameters:**

* <code>desiredExponentInt</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Quantize
    public EDecimal Quantize​(EDecimal otherValue, EContext ctx)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Reduce
    public EDecimal Reduce​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Remainder
    public EDecimal Remainder​(EDecimal divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RemainderNaturalScale
    public EDecimal RemainderNaturalScale​(EDecimal divisor)

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RemainderNaturalScale
    public EDecimal RemainderNaturalScale​(EDecimal divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RemainderNear
    public EDecimal RemainderNear​(EDecimal divisor, EContext ctx)

**Parameters:**

* <code>divisor</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToExponent
    public EDecimal RoundToExponent​(EInteger exponent, EContext ctx)

**Parameters:**

* <code>exponent</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToExponent
    public EDecimal RoundToExponent​(EInteger exponent)

**Parameters:**

* <code>exponent</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToExponent
    public EDecimal RoundToExponent​(EInteger exponent, ERounding rounding)

**Parameters:**

* <code>exponent</code> - Not documented yet.

* <code>rounding</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToExponent
    public EDecimal RoundToExponent​(int exponentSmall)

**Parameters:**

* <code>exponentSmall</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToExponent
    public EDecimal RoundToExponent​(int exponentSmall, EContext ctx)

**Parameters:**

* <code>exponentSmall</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToExponent
    public EDecimal RoundToExponent​(int exponentSmall, ERounding rounding)

**Parameters:**

* <code>exponentSmall</code> - Not documented yet.

* <code>rounding</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToExponentExact
    public EDecimal RoundToExponentExact​(EInteger exponent, EContext ctx)

**Parameters:**

* <code>exponent</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToExponentExact
    public EDecimal RoundToExponentExact​(int exponentSmall, EContext ctx)

**Parameters:**

* <code>exponentSmall</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToExponentExact
    public EDecimal RoundToExponentExact​(int exponentSmall, ERounding rounding)

**Parameters:**

* <code>exponentSmall</code> - Not documented yet.

* <code>rounding</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToIntegerExact
    public EDecimal RoundToIntegerExact​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToIntegerNoRoundedFlag
    public EDecimal RoundToIntegerNoRoundedFlag​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToIntegralExact
    @Deprecated public EDecimal RoundToIntegralExact​(EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to RoundToIntegerExact.</div>

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToIntegralNoRoundedFlag
    @Deprecated public EDecimal RoundToIntegralNoRoundedFlag​(EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to RoundToIntegerNoRoundedFlag.</div>

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### RoundToPrecision
    public EDecimal RoundToPrecision​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(int places)

**Parameters:**

* <code>places</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(int places, EContext ctx)

**Parameters:**

* <code>places</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(EInteger bigPlaces)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(EInteger bigPlaces, EContext ctx)

**Parameters:**

* <code>bigPlaces</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Sqrt
    public EDecimal Sqrt​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### SquareRoot
    @Deprecated public EDecimal SquareRoot​(EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to Sqrt.</div>

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Subtract
    public EDecimal Subtract​(EDecimal otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Subtract
    public EDecimal Subtract​(EDecimal otherValue, EContext ctx)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### ToDouble
    public double ToDouble()

**Returns:**

* A 64-bit floating-point number.

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

### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat()
Deprecated.
<div class='deprecationComment'>Renamed to ToEFloat.</div>

**Returns:**

* An EFloat object.

### ToEFloat
    public EFloat ToEFloat()

**Returns:**

* An EFloat object.

### ToPlainString
    public String ToPlainString()

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
    public EDecimal Ulp()

**Returns:**

* An EDecimal object.

### ToEFloat
    public EFloat ToEFloat​(EContext ec)

**Parameters:**

* <code>ec</code> - Not documented yet.

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
    public static EDecimal FromByte​(byte inputByte)

**Parameters:**

* <code>inputByte</code> - Not documented yet.

**Returns:**

* An EDecimal object.

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
    public static EDecimal FromInt16​(short inputInt16)

**Parameters:**

* <code>inputInt16</code> - Not documented yet.

**Returns:**

* An EDecimal object.

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
