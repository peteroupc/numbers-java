# com.upokecenter.numbers.EDecimal

    public final class EDecimal extends Object implements Comparable<EDecimal>

Represents an arbitrary-precision decimal floating-point number. (The "E"
 stands for "extended", meaning that instances of this class can be
 values other than numbers proper, such as infinity and not-a-number.)
 <p><b>About decimal arithmetic</b></p> <p>Decimal (base-10)
 arithmetic, such as that provided by this class, is appropriate for
 calculations involving such real-world data as prices and other sums
 of money, tax rates, and measurements. These calculations often
 involve multiplying or dividing one decimal with another decimal, or
 performing other operations on decimal numbers. Many of these
 calculations also rely on rounding behavior in which the result after
 rounding is a decimal number (for example, multiplying a price by a
 premium rate, then rounding, should result in a decimal amount of
 money).</p> <p>On the other hand, most implementations of
 <code>float</code> and <code>double</code>, including in C# and Java, store
 numbers in a binary (base-2) floating-point format and use binary
 floating-point arithmetic. Many decimal numbers can't be represented
 exactly in binary floating-point format (regardless of its length).
 Applying binary arithmetic to numbers intended to be decimals can
 sometimes lead to unintuitive results, as is shown in the description
 for the FromDouble() method of this class.</p> <p><b>About EDecimal
 instances</b></p> <p>Each instance of this class consists of an
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
 <code>http://speleotrove.com/decimal/decarith.html</code></p> <p><b>Errors
 and Exceptions</b></p> <p>Passing a signaling NaN to any arithmetic
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
 Java) in the same circumstances.</p> <p><b>Serialization</b></p>
 <p>An arbitrary-precision decimal value can be serialized (converted
 to a stable format) in one of the following ways:</p> <ul> <li>By
 calling the toString() method, which will always return distinct
 strings for distinct arbitrary-precision decimal values.</li> <li>By
 calling the UnsignedMantissa, Exponent, and IsNegative properties,
 and calling the IsInfinity, IsQuietNaN, and IsSignalingNaN methods.
 The return values combined will uniquely identify a particular
 arbitrary-precision decimal value.</li></ul> <p><b>Thread
 safety</b></p> <p>Instances of this class are immutable, so they are
 inherently safe for use by multiple threads. Multiple instances of
 this object with the same properties are interchangeable, so they
 should not be compared using the "==" operator (which might only
 check if each side of the operator is the same instance).</p>
 <p><b>Comparison considerations</b></p> <p>This class's natural
 ordering (under the compareTo method) is not consistent with the
 Equals method. This means that two values that compare as equal under
 the compareTo method might not be equal under the Equals method. The
 compareTo method compares the mathematical values of the two
 instances passed to it (and considers two different NaN values as
 equal), while two instances with the same mathematical value, but
 different exponents, will be considered unequal under the Equals
 method.</p> <p><b>Forms of numbers</b></p> <p>There are several other
 types of numbers that are mentioned in this class and elsewhere in
 this documentation. For reference, they are specified here.</p>
 <p><b>Unsigned integer</b>: An integer that&#39;s always 0 or
 greater, with the following maximum values:</p> <ul> <li>8-bit
 unsigned integer, or <i>byte</i>: 255.</li> <li>16-bit unsigned
 integer: 65535.</li> <li>32-bit unsigned integer:
 (2<sup>32</sup>-1).</li> <li>64-bit unsigned integer:
 (2<sup>64</sup>-1).</li> </ul> <p><b>Signed integer</b>: An integer
 in <i>two&#39;s complement form</i>, with the following ranges:</p>
 <ul> <li>8-bit signed integer: -128 to 127.</li> <li>16-bit signed
 integer: -32768 to 32767.</li> <li>32-bit signed integer:
 -2<sup>31</sup> to (2<sup>31</sup> - 1).</li> <li>64-bit signed
 integer: -2<sup>63</sup> to (2<sup>63</sup> - 1).</li> </ul>
 <p><b>Two's complement form</b>: In <i>two's-complement form</i>,
 positive numbers have the highest (most significant) bit set to zero,
 and negative numbers have that bit (and all bits beyond) set to one.
 To store a negative number, decrease its absolute value by 1 and swap
 the bits of the resulting number.</p> <p><b>64-bit floating-point
 number</b>: A 64-bit binary floating-point number, in the form
 <i>significand</i> * 2<sup><i>exponent</i></sup>. The significand is
 53 bits long (Precision) and the exponent ranges from -1075 (EMin) to
 971 (EMax). The number is stored in the following format (commonly
 called the IEEE 754 format):</p>
 <code>|C|BBB...BBB|AAAAAA...AAAAAA|</code> <ul> <li>A. Low 52 bits
 (Precision minus 1 bits): Lowest bits of the significand.</li> <li>B.
 Next 11 bits: Exponent area: <ul> <li>If all bits are ones, this
 value is infinity if all bits in area A are zeros, or not-a-number
 (NaN) otherwise.</li> <li>If all bits are zeros, this is a subnormal
 number. The exponent is EMin and the highest bit of the significand
 is zero.</li> <li>If any other number, the exponent is this value
 plus EMin, and the highest bit of the significand is one.</li>
 </ul></li> <li>C. Highest bit: If one, this is a negative
 number.</li> </ul> <p>The elements described above are in the same
 order as the order of each bit of each element, that is, either most
 significant first or least significant first.</p> <p><b>32-bit
 floating-point number</b>: A 32-bit binary number which is stored
 similarly to a <i>64-bit floating-point number</i>, except that:</p>
 <ul> <li>Precision is 24 bits.</li> <li>EMin is -150.</li> <li>EMax
 is 104.</li> <li>A. The low 23 bits (Precision minus 1 bits) are the
 lowest bits of the significand.</li> <li>B. The next 8 bits are the
 exponent area.</li> <li>C. If the highest bit is one, this is a
 negative number.</li> </ul> <p><b>.NET Framework decimal</b>: A
 128-bit decimal floating-point number, in the form <i>significand</i>
 * 10<sup>-<i>scale</i></sup>, where the scale ranges from 0 to 28.
 The number is stored in the following format:</p> <ul> <li>Low 96
 bits are the significand, as a 96-bit unsigned integer (all 96-bit
 values are allowed, up to (2<sup>96</sup>-1)).</li> <li>Next 16 bits
 are unused.</li> <li>Next 8 bits are the scale, stored as an 8-bit
 unsigned integer.</li> <li>Next 7 bits are unused.</li> <li>If the
 highest bit is one, it&#39;s a negative number.</li> </ul> <p>The
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
 A not-a-number value that signals an invalid operation flag when it's
 passed as an argument to any arithmetic operation in
 arbitrary-precision decimal.
* `static EDecimal Ten`<br>
 Represents the number 10.
* `static EDecimal Zero`<br>
 Represents the number 0.

## Methods

* `EDecimal Abs()`<br>
 Finds the absolute value of this object (if it's negative, it becomes
 positive).
* `EDecimal Abs(EContext context)`<br>
 Finds the absolute value of this object (if it's negative, it becomes
 positive).
* `EDecimal Add(EDecimal otherValue)`<br>
 Adds this object and another decimal number and returns the result.
* `EDecimal Add(EDecimal otherValue,
   EContext ctx)`<br>
 Finds the sum of this object and another object.
* `int compareTo(EDecimal other)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int CompareToBinary(EFloat other)`<br>
 Compares an arbitrary-precision binary float with this instance.
* `EDecimal CompareToSignal(EDecimal other,
               EContext ctx)`<br>
 Compares the mathematical values of this object and another object, treating
 quiet NaN as signaling.
* `int CompareToTotal(EDecimal other)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values.
* `int CompareToTotal(EDecimal other,
              EContext ctx)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values.
* `int CompareToTotalMagnitude(EDecimal other)`<br>
 Compares the absolute values of this object and another object, imposing a
 total ordering on all possible values (ignoring their signs).
* `EDecimal CompareToWithContext(EDecimal other,
                    EContext ctx)`<br>
 Compares the mathematical values of this object and another object.
* `EDecimal CopySign(EDecimal other)`<br>
 Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number.
* `static EDecimal Create(EInteger mantissa,
      EInteger exponent) exponent*10^mantissa`<br>
 Creates a number with the value exponent*10^mantissa.
* `static EDecimal Create(int mantissaSmall,
      int exponentSmall) exponent*10^mantissa`<br>
 Creates a number with the value exponent*10^mantissa.
* `static EDecimal CreateNaN(EInteger diag)`<br>
 Creates a not-a-number arbitrary-precision decimal number.
* `static EDecimal CreateNaN(EInteger diag,
         boolean signaling,
         boolean negative,
         EContext ctx)`<br>
 Creates a not-a-number arbitrary-precision decimal number.
* `EDecimal Divide(EDecimal divisor)`<br>
 Divides this object by another decimal number and returns the result.
* `EDecimal Divide(EDecimal divisor,
      EContext ctx)`<br>
 Divides this arbitrary-precision decimal number by another
 arbitrary-precision decimal number.
* `EDecimal[] DivideAndRemainderNaturalScale(EDecimal divisor)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EDecimal[] DivideAndRemainderNaturalScale(EDecimal divisor,
                              EContext ctx)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EDecimal DivideToExponent(EDecimal divisor,
                EInteger exponent)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result, using the half-even rounding mode.
* `EDecimal DivideToExponent(EDecimal divisor,
                EInteger exponent,
                EContext ctx)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToExponent(EDecimal divisor,
                EInteger desiredExponent,
                ERounding rounding)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToExponent(EDecimal divisor,
                int desiredExponentInt)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent(EDecimal divisor,
                int desiredExponentInt,
                EContext ctx)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent(EDecimal divisor,
                int desiredExponentInt,
                ERounding rounding)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent(EDecimal divisor,
                long desiredExponentSmall)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 64-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent(EDecimal divisor,
                long desiredExponentSmall,
                EContext ctx)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToExponent(EDecimal divisor,
                long desiredExponentSmall,
                ERounding rounding)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToIntegerNaturalScale(EDecimal divisor)`<br>
 Divides two arbitrary-precision decimal numbers, and returns the integer
 part of the result, rounded down, with the preferred exponent set to
 this value's exponent minus the divisor's exponent.
* `EDecimal DivideToIntegerNaturalScale(EDecimal divisor,
                           EContext ctx)`<br>
 Divides this object by another object, and returns the integer part of the
 result (which is initially rounded down), with the preferred exponent
 set to this value's exponent minus the divisor's exponent.
* `EDecimal DivideToIntegerZeroScale(EDecimal divisor,
                        EContext ctx)`<br>
 Divides this object by another object, and returns the integer part of the
 result, with the exponent set to 0.
* `EDecimal DivideToSameExponent(EDecimal divisor,
                    ERounding rounding)`<br>
 Divides this object by another decimal number and returns a result with the
 same exponent as this object (the dividend).
* `EDecimal[] DivRemNaturalScale(EDecimal divisor)`<br>
 Calculates the quotient and remainder using the DivideToIntegerNaturalScale
 and the formula in RemainderNaturalScale.
* `EDecimal[] DivRemNaturalScale(EDecimal divisor,
                  EContext ctx)`<br>
 Calculates the quotient and remainder using the DivideToIntegerNaturalScale
 and the formula in RemainderNaturalScale.
* `boolean equals(EDecimal other)`<br>
 Determines whether this object's mantissa (significand) and exponent
 are equal to those of another object.
* `boolean equals(Object obj)`<br>
 Determines whether this object's mantissa (significand) and exponent
 are equal to those of another object and that other object is an
 arbitrary-precision decimal number.
* `EDecimal Exp(EContext ctx)`<br>
 Finds e (the base of natural logarithms) raised to the power of this
 object's value.
* `static EDecimal FromDouble(double dbl)`<br>
 Creates a decimal number from a 64-bit binary floating-point number.
* `static EDecimal FromEFloat(EFloat bigfloat)`<br>
 Creates a decimal number from an arbitrary-precision binary floating-point
 number.
* `static EDecimal FromEInteger(EInteger bigint)`<br>
 Converts an arbitrary-precision integer to an arbitrary precision decimal.
* `static EDecimal FromExtendedFloat(EFloat ef)`<br>
 Deprecated.
Renamed to FromEFloat.
 Renamed to FromEFloat.
* `static EDecimal FromInt32(int valueSmaller)`<br>
 Creates a decimal number from a 32-bit signed integer.
* `static EDecimal FromInt64(long valueSmall)`<br>
 Creates a decimal number from a 64-bit signed integer.
* `static EDecimal FromSingle(float flt)`<br>
 Creates a decimal number from a 32-bit binary floating-point number.
* `static EDecimal FromString(String str)`<br>
 Creates a decimal number from a text string that represents a number.
* `static EDecimal FromString(String str,
          EContext ctx)`<br>
 Creates a decimal number from a text string that represents a number.
* `static EDecimal FromString(String str,
          int offset,
          int length)`<br>
 Creates a decimal number from a text string that represents a number.
* `static EDecimal FromString(String str,
          int offset,
          int length,
          EContext ctx)`<br>
 Creates a decimal number from a text string that represents a number.
* `EInteger getExponent()`<br>
 Gets this object's exponent.
* `EInteger getMantissa()`<br>
 Gets this object's unscaled value.
* `EInteger getUnsignedMantissa()`<br>
 Gets the absolute value of this object's unscaled value.
* `int hashCode()`<br>
 Calculates this object's hash code.
* `boolean isFinite()`<br>
 Gets a value indicating whether this object is finite (not infinity or NaN).
* `boolean IsInfinity()`<br>
 Gets a value indicating whether this object is positive or negative
 infinity.
* `boolean IsNaN()`<br>
 Gets a value indicating whether this object is not a number (NaN).
* `boolean isNegative()`<br>
 Gets a value indicating whether this object is negative, including negative
 zero.
* `boolean IsNegativeInfinity()`<br>
 Returns whether this object is negative infinity.
* `boolean IsPositiveInfinity()`<br>
 Returns whether this object is positive infinity.
* `boolean IsQuietNaN()`<br>
 Gets a value indicating whether this object is a quiet not-a-number value.
* `boolean IsSignalingNaN()`<br>
 Gets a value indicating whether this object is a signaling not-a-number
 value.
* `boolean isZero()`<br>
 Gets a value indicating whether this object's value equals 0.
* `EDecimal Log(EContext ctx)`<br>
 Finds the natural logarithm of this object, that is, the power (exponent)
 that e (the base of natural logarithms) must be raised to in order to
 equal this object's value.
* `EDecimal Log10(EContext ctx)`<br>
 Finds the base-10 logarithm of this object, that is, the power (exponent)
 that the number 10 must be raised to in order to equal this
 object's value.
* `static EDecimal Max(EDecimal first,
   EDecimal second)`<br>
 Gets the greater value between two decimal numbers.
* `static EDecimal Max(EDecimal first,
   EDecimal second,
   EContext ctx)`<br>
 Gets the greater value between two decimal numbers.
* `static EDecimal MaxMagnitude(EDecimal first,
            EDecimal second)`<br>
 Gets the greater value between two values, ignoring their signs.
* `static EDecimal MaxMagnitude(EDecimal first,
            EDecimal second,
            EContext ctx)`<br>
 Gets the greater value between two values, ignoring their signs.
* `static EDecimal Min(EDecimal first,
   EDecimal second)`<br>
 Gets the lesser value between two decimal numbers.
* `static EDecimal Min(EDecimal first,
   EDecimal second,
   EContext ctx)`<br>
 Gets the lesser value between two decimal numbers.
* `static EDecimal MinMagnitude(EDecimal first,
            EDecimal second)`<br>
 Gets the lesser value between two values, ignoring their signs.
* `static EDecimal MinMagnitude(EDecimal first,
            EDecimal second,
            EContext ctx)`<br>
 Gets the lesser value between two values, ignoring their signs.
* `EDecimal MovePointLeft(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointLeft(EInteger bigPlaces,
             EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointLeft(int places)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointLeft(int places,
             EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointRight(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal MovePointRight(EInteger bigPlaces,
              EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal MovePointRight(int places)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal MovePointRight(int places,
              EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal Multiply(EDecimal otherValue)`<br>
 Multiplies two decimal numbers.
* `EDecimal Multiply(EDecimal op,
        EContext ctx)`<br>
 Multiplies two decimal numbers.
* `EDecimal MultiplyAndAdd(EDecimal multiplicand,
              EDecimal augend)`<br>
 Multiplies by one decimal number, and then adds another decimal number.
* `EDecimal MultiplyAndAdd(EDecimal op,
              EDecimal augend,
              EContext ctx)`<br>
 Multiplies by one value, and then adds another value.
* `EDecimal MultiplyAndSubtract(EDecimal op,
                   EDecimal subtrahend,
                   EContext ctx)`<br>
 Multiplies by one value, and then subtracts another value.
* `EDecimal Negate()`<br>
 Gets an object with the same value as this one, but with the sign reversed.
* `EDecimal Negate(EContext context)`<br>
 Returns a decimal number with the same value as this object but with the
 sign reversed.
* `EDecimal NextMinus(EContext ctx)`<br>
 Finds the largest value that's smaller than the given value.
* `EDecimal NextPlus(EContext ctx)`<br>
 Finds the smallest value that's greater than the given value.
* `EDecimal NextToward(EDecimal otherValue,
          EContext ctx)`<br>
 Finds the next value that is closer to the other object's value than
 this object's value.
* `static EDecimal PI(EContext ctx)`<br>
 Finds the constant π, the circumference of a circle divided by its
 diameter.
* `EDecimal Plus(EContext ctx)`<br>
 Rounds this object's value to a given precision, using the given
 rounding mode and range of exponent, and also converts negative zero
 to positive zero.
* `EDecimal Pow(EDecimal exponent,
   EContext ctx)`<br>
 Raises this object's value to the given exponent.
* `EDecimal Pow(int exponentSmall)`<br>
 Raises this object's value to the given exponent.
* `EDecimal Pow(int exponentSmall,
   EContext ctx)`<br>
 Raises this object's value to the given exponent.
* `EInteger Precision()`<br>
 Finds the number of digits in this number's mantissa (significand).
* `EDecimal Quantize(EDecimal otherValue,
        EContext ctx)`<br>
 Returns a decimal number with the same value as this object but with the
 same exponent as another decimal number.
* `EDecimal Quantize(EInteger desiredExponent,
        EContext ctx)`<br>
 Returns a decimal number with the same value but a new exponent.
* `EDecimal Quantize(int desiredExponentInt,
        EContext ctx)`<br>
 Returns a decimal number with the same value but a new exponent.
* `EDecimal Quantize(int desiredExponentInt,
        ERounding rounding)`<br>
 Returns a decimal number with the same value as this one but a new exponent.
* `EDecimal Reduce(EContext ctx)`<br>
 Removes trailing zeros from this object's mantissa (significand).
* `EDecimal Remainder(EDecimal divisor,
         EContext ctx)`<br>
 Finds the remainder that results when dividing two arbitrary-precision
 decimal numbers.
* `EDecimal RemainderNaturalScale(EDecimal divisor) "this" - (("this" /
 "divisor") * "divisor")`<br>
 Calculates the remainder of a number by the formula "this" - (("this" /
 "divisor") * "divisor")
* `EDecimal RemainderNaturalScale(EDecimal divisor,
                     EContext ctx)`<br>
 Calculates the remainder of a number by the formula "this" - (("this" /
 "divisor") * "divisor").
* `EDecimal RemainderNear(EDecimal divisor,
             EContext ctx)`<br>
 Finds the distance to the closest multiple of the given divisor, based on
 the result of dividing this object's value by another
 object's value.
* `EDecimal RoundToExponent(EInteger exponent)`<br>
 Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary, using the HalfEven rounding mode.
* `EDecimal RoundToExponent(EInteger exponent,
               EContext ctx)`<br>
 Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary.
* `EDecimal RoundToExponent(EInteger exponent,
               ERounding rounding)`<br>
 Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary, using the given rounding mode.
* `EDecimal RoundToExponent(int exponentSmall)`<br>
 Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary, using the HalfEven rounding mode.
* `EDecimal RoundToExponent(int exponentSmall,
               EContext ctx)`<br>
 Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary.
* `EDecimal RoundToExponent(int exponentSmall,
               ERounding rounding)`<br>
 Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary.
* `EDecimal RoundToExponentExact(EInteger exponent,
                    EContext ctx)`<br>
 Returns a decimal number with the same value as this object but rounded to
 the given exponent, and signals an inexact flag if the result would
 be inexact.
* `EDecimal RoundToExponentExact(int exponentSmall,
                    EContext ctx)`<br>
 Returns a decimal number with the same value as this object but rounded to
 an integer, and signals an inexact flag if the result would be
 inexact.
* `EDecimal RoundToExponentExact(int exponentSmall,
                    ERounding rounding)`<br>
 Returns a decimal number with the same value as this object but rounded to
 an integer, and signals an inexact flag if the result would be
 inexact.
* `EDecimal RoundToIntegerExact(EContext ctx)`<br>
 Returns a decimal number with the same value as this object but rounded to
 an integer, and signals an inexact flag if the result would be
 inexact.
* `EDecimal RoundToIntegerNoRoundedFlag(EContext ctx) FlagInexact`<br>
 Returns a decimal number with the same value as this object but rounded to
 an integer, without adding the FlagInexact or FlagRounded
 flags.
* `EDecimal RoundToIntegralExact(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerExact.
 Renamed to RoundToIntegerExact.
* `EDecimal RoundToIntegralNoRoundedFlag(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerNoRoundedFlag.
 Renamed to RoundToIntegerNoRoundedFlag.
* `EDecimal RoundToPrecision(EContext ctx)`<br>
 Rounds this object's value to a given precision, using the given
 rounding mode and range of exponent.
* `EDecimal ScaleByPowerOfTen(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `EDecimal ScaleByPowerOfTen(EInteger bigPlaces,
                 EContext ctx)`<br>
 Returns a number similar to this number but with its scale adjusted.
* `EDecimal ScaleByPowerOfTen(int places)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `EDecimal ScaleByPowerOfTen(int places,
                 EContext ctx)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `int signum()`<br>
 Gets this value's sign: -1 if negative; 1 if positive; 0 if zero.
* `EDecimal Sqrt(EContext ctx)`<br>
 Finds the square root of this object's value.
* `EDecimal SquareRoot(EContext ctx)`<br>
 Deprecated.
Renamed to Sqrt.
 Renamed to Sqrt.
* `EDecimal Subtract(EDecimal otherValue)`<br>
 Subtracts an arbitrary-precision decimal number from this instance and
 returns the result.
* `EDecimal Subtract(EDecimal otherValue,
        EContext ctx)`<br>
 Subtracts an arbitrary-precision decimal number from this instance.
* `double ToDouble()`<br>
 Converts this value to its closest equivalent as a 64-bit floating-point
 number.
* `EFloat ToEFloat()`<br>
 Creates a binary floating-point number from this object's value.
* `EFloat ToEFloat(EContext ec)`<br>
 Not documented yet.
* `EInteger ToEInteger()`<br>
 Converts this value to an arbitrary-precision integer.
* `EInteger ToEIntegerExact()`<br>
 Converts this value to an arbitrary-precision integer, checking whether the
 fractional part of the integer would be lost.
* `String ToEngineeringString()`<br>
 Same as toString(), except that when an exponent is used it will be a
 multiple of 3.
* `EFloat ToExtendedFloat()`<br>
 Deprecated.
Renamed to ToEFloat.
 Renamed to ToEFloat.
* `int ToInt32Checked()`<br>
 Converts this number to a 32-bit signed integer, if possible, without
 truncating or rounding it.
* `int ToInt32Unchecked()`<br>
 Not documented yet.
* `long ToInt64Checked()`<br>
 Converts this number to a 64-bit signed integer, if possible, without
 truncating or rounding it.
* `long ToInt64Unchecked()`<br>
 Not documented yet.
* `String ToPlainString()`<br>
 Converts this value to a string, but without using exponential notation.
* `float ToSingle()`<br>
 Converts this value to its closest equivalent as a 32-bit floating-point
 number.
* `String toString()`<br>
 Converts this value to a string.
* `EDecimal Ulp()`<br>
 Returns the unit in the last place.

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
A not-a-number value that signals an invalid operation flag when it&#x27;s
 passed as an argument to any arithmetic operation in
 arbitrary-precision decimal.
### Ten
    public static final EDecimal Ten
Represents the number 10.
### Zero
    public static final EDecimal Zero
Represents the number 0.
## Method Details

### getExponent
    public final EInteger getExponent()
Gets this object&#x27;s exponent. This object&#x27;s value will be an
 integer if the exponent is positive or zero.

**Returns:**

* This object's exponent. This object's value will be an integer if
 the exponent is positive or zero.

### isFinite
    public final boolean isFinite()
Gets a value indicating whether this object is finite (not infinity or NaN).

**Returns:**

* true if this object is finite (not infinity or not-a-number (NaN));
 otherwise, false.

### isNegative
    public final boolean isNegative()
Gets a value indicating whether this object is negative, including negative
 zero.

**Returns:**

* true if this object is negative, including negative zero; otherwise,
 false.

### isZero
    public final boolean isZero()
Gets a value indicating whether this object&#x27;s value equals 0.

**Returns:**

* true if this object's value equals 0; otherwise, false.

### getMantissa
    public final EInteger getMantissa()
Gets this object&#x27;s unscaled value.

**Returns:**

* This object's unscaled value. Will be negative if this object's
 value is negative (including a negative NaN).

### signum
    public final int signum()
Gets this value&#x27;s sign: -1 if negative; 1 if positive; 0 if zero.

**Returns:**

* This value's sign: -1 if negative; 1 if positive; 0 if zero.

### getUnsignedMantissa
    public final EInteger getUnsignedMantissa()
Gets the absolute value of this object&#x27;s unscaled value.

**Returns:**

* The absolute value of this object's unscaled value.

### Create
    public static EDecimal Create(int mantissaSmall, int exponentSmall)
Creates a number with the value <code>exponent*10^mantissa</code>.

**Returns:**

* An arbitrary-precision decimal number.

### Create
    public static EDecimal Create(EInteger mantissa, EInteger exponent)
Creates a number with the value <code>exponent*10^mantissa</code>.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>mantissa</code> or
 <code>exponent</code> is null.

### CreateNaN
    public static EDecimal CreateNaN(EInteger diag)
Creates a not-a-number arbitrary-precision decimal number.

**Parameters:**

* <code>diag</code> - A number to use as diagnostic information associated with this
 object. If none is needed, should be zero.

**Returns:**

* A quiet not-a-number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>diag</code> is null or
 is less than 0.

### CreateNaN
    public static EDecimal CreateNaN(EInteger diag, boolean signaling, boolean negative, EContext ctx)
Creates a not-a-number arbitrary-precision decimal number.

**Parameters:**

* <code>diag</code> - A number to use as diagnostic information associated with this
 object. If none is needed, should be zero.

* <code>signaling</code> - Whether the return value will be signaling (true) or quiet
 (false).

* <code>negative</code> - Whether the return value is negative.

* <code>ctx</code> - An arithmetic context to control the precision (in decimal
 digits) of the diagnostic information. The rounding and exponent
 range of this context will be ignored. Can be null. The only flag
 that can be signaled in this context is FlagInvalid, which happens if
 diagnostic information needs to be truncated and too much memory is
 required to do so.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>diag</code> is null or
 is less than 0.

### FromDouble
    public static EDecimal FromDouble(double dbl)
Creates a decimal number from a 64-bit binary floating-point number. This
 method computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the floating point
 number to a string first. Remember, though, that the exact value of a
 64-bit binary floating-point number is not always the value that
 results when passing a literal decimal number (for example, calling
 <code>ExtendedDecimal.FromDouble(0.1f)</code>), since not all decimal
 numbers can be converted to exact binary numbers (in the example
 given, the resulting arbitrary-precision decimal will be the value of
 the closest "double" to 0.1, not 0.1 exactly). To create an
 arbitrary-precision decimal number from a decimal number, use
 FromString instead in most cases (for example:
 <code>ExtendedDecimal.FromString("0.1")</code>).

**Parameters:**

* <code>dbl</code> - A 64-bit floating-point number.

**Returns:**

* A decimal number with the same value as <code>dbl</code>.

### FromEInteger
    public static EDecimal FromEInteger(EInteger bigint)
Converts an arbitrary-precision integer to an arbitrary precision decimal.

**Parameters:**

* <code>bigint</code> - An arbitrary-precision integer.

**Returns:**

* An arbitrary-precision decimal number with the exponent set to 0.

### FromExtendedFloat
    @Deprecated public static EDecimal FromExtendedFloat(EFloat ef)
Deprecated.&nbsp;<i>Renamed to FromEFloat.</i>

**Parameters:**

* <code>ef</code> - The parameter <code>ef</code> is not documented yet.

**Returns:**

* An EDecimal object.

### FromEFloat
    public static EDecimal FromEFloat(EFloat bigfloat)
Creates a decimal number from an arbitrary-precision binary floating-point
 number.

**Parameters:**

* <code>bigfloat</code> - An arbitrary-precision binary floating-point number.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>bigfloat</code> is null.

### FromInt32
    public static EDecimal FromInt32(int valueSmaller)
Creates a decimal number from a 32-bit signed integer.

**Parameters:**

* <code>valueSmaller</code> - A 32-bit signed integer.

**Returns:**

* An arbitrary-precision decimal number with the exponent set to 0.

### FromInt64
    public static EDecimal FromInt64(long valueSmall)
Creates a decimal number from a 64-bit signed integer.

**Parameters:**

* <code>valueSmall</code> - A 64-bit signed integer.

**Returns:**

* An arbitrary-precision decimal number with the exponent set to 0.

### FromSingle
    public static EDecimal FromSingle(float flt)
Creates a decimal number from a 32-bit binary floating-point number. This
 method computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the floating point
 number to a string first. Remember, though, that the exact value of a
 32-bit binary floating-point number is not always the value that
 results when passing a literal decimal number (for example, calling
 <code>ExtendedDecimal.FromSingle(0.1f)</code>), since not all decimal
 numbers can be converted to exact binary numbers (in the example
 given, the resulting arbitrary-precision decimal will be the the
 value of the closest "float" to 0.1, not 0.1 exactly). To create an
 arbitrary-precision decimal number from a decimal number, use
 FromString instead in most cases (for example:
 <code>ExtendedDecimal.FromString("0.1")</code>).

**Parameters:**

* <code>flt</code> - A 32-bit floating-point number.

**Returns:**

* A decimal number with the same value as <code>flt</code>.

### FromString
    public static EDecimal FromString(String str)
Creates a decimal number from a text string that represents a number. See
 <code>FromString(String, int, int, EContext)</code> for more information.

**Parameters:**

* <code>str</code> - A string that represents a number.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

### FromString
    public static EDecimal FromString(String str, EContext ctx)
Creates a decimal number from a text string that represents a number. See
 <code>FromString(String, int, int, EContext)</code> for more information.

**Parameters:**

* <code>str</code> - A string that represents a number.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

### FromString
    public static EDecimal FromString(String str, int offset, int length)
Creates a decimal number from a text string that represents a number. See
 <code>FromString(String, int, int, EContext)</code> for more information.

**Parameters:**

* <code>str</code> - A string that represents a number.

* <code>offset</code> - A zero-based index showing where the desired portion of <code>str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>str</code> (but not more than <code>str</code> 's length).

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

### FromString
    public static EDecimal FromString(String str, int offset, int length, EContext ctx)
<p>Creates a decimal number from a text string that represents a number.</p>
 <p>The format of the string generally consists of:</p> <ul> <li>An
 optional plus sign ("+" , U+002B) or minus sign ("-", U+002D) (if '-'
 , the value is negative.)</li> <li>One or more digits, with a single
 optional decimal point after the first digit and before the last
 digit.</li> <li>Optionally, "E"/"e" followed by an optional (positive
 exponent) or "-" (negative exponent) and followed by one or more
 digits specifying the exponent.</li></ul> <p>The string can also be
 "-INF", "-Infinity", "Infinity", "INF", quiet NaN ("NaN" /"-NaN")
 followed by any number of digits, or signaling NaN ("sNaN" /"-sNaN")
 followed by any number of digits, all in any combination of upper and
 lower case.</p> <p>All characters mentioned above are the
 corresponding characters in the Basic Latin range. In particular, the
 digits must be the basic digits 0 to 9 (U + 0030 to U + 0039). The string
 is not allowed to contain white space characters, including
 spaces.</p>

**Parameters:**

* <code>str</code> - A text string, a portion of which represents a number.

* <code>offset</code> - A zero-based index that identifies the start of the number.

* <code>length</code> - The length of the number within the string.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

### Max
    public static EDecimal Max(EDecimal first, EDecimal second, EContext ctx)
Gets the greater value between two decimal numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The larger value of the two numbers.

### Max
    public static EDecimal Max(EDecimal first, EDecimal second)
Gets the greater value between two decimal numbers.

**Parameters:**

* <code>first</code> - An arbitrary-precision decimal number.

* <code>second</code> - Another arbitrary-precision decimal number.

**Returns:**

* The larger value of the two numbers.

### MaxMagnitude
    public static EDecimal MaxMagnitude(EDecimal first, EDecimal second, EContext ctx)
Gets the greater value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Max.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* An arbitrary-precision decimal number.

### MaxMagnitude
    public static EDecimal MaxMagnitude(EDecimal first, EDecimal second)
Gets the greater value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Max.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* An arbitrary-precision decimal number.

### Min
    public static EDecimal Min(EDecimal first, EDecimal second, EContext ctx)
Gets the lesser value between two decimal numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The smaller value of the two numbers.

### Min
    public static EDecimal Min(EDecimal first, EDecimal second)
Gets the lesser value between two decimal numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* The smaller value of the two numbers.

### MinMagnitude
    public static EDecimal MinMagnitude(EDecimal first, EDecimal second, EContext ctx)
Gets the lesser value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Min.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* An arbitrary-precision decimal number.

### MinMagnitude
    public static EDecimal MinMagnitude(EDecimal first, EDecimal second)
Gets the lesser value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Min.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* An arbitrary-precision decimal number.

### PI
    public static EDecimal PI(EContext ctx)
Finds the constant &#x3c0;, the circumference of a circle divided by its
 diameter.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as &#x3c0; can never be represented exactly.</i>.

**Returns:**

* The constant π rounded to the given precision. Signals FlagInvalid
 and returns not-a-number (NaN) if the parameter <code>ctx</code> is null
 or the precision is unlimited (the context's Precision property is
 0).

### Abs
    public EDecimal Abs()
Finds the absolute value of this object (if it&#x27;s negative, it becomes
 positive).

**Returns:**

* An arbitrary-precision decimal number. Returns signaling NaN if this
 value is signaling NaN.

### CopySign
    public EDecimal CopySign(EDecimal other)
Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number.

**Parameters:**

* <code>other</code> - A number whose sign will be copied.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>other</code> is null.

### Abs
    public EDecimal Abs(EContext context)
Finds the absolute value of this object (if it&#x27;s negative, it becomes
 positive).

**Parameters:**

* <code>context</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The absolute value of this object. Signals FlagInvalid and returns
 quiet NaN if this value is signaling NaN.

### Add
    public EDecimal Add(EDecimal otherValue)
Adds this object and another decimal number and returns the result.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision decimal number.

**Returns:**

* The sum of the two objects.

### Add
    public EDecimal Add(EDecimal otherValue, EContext ctx)
Finds the sum of this object and another object. The result&#x27;s exponent
 is set to the lower of the exponents of the two operands.

**Parameters:**

* <code>otherValue</code> - The number to add to.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The sum of thisValue and the other object.

### compareTo
    public int compareTo(EDecimal other)
Compares the mathematical values of this object and another object,
 accepting NaN values. <p>This method is not consistent with the
 Equals method because two different numbers with the same
 mathematical value, but different exponents, will compare as
 equal.</p> <p>In this method, negative zero and positive zero are
 considered equal.</p> <p>If this object or the other object is a
 quiet NaN or signaling NaN, this method will not trigger an error.
 Instead, NaN will compare greater than any other number, including
 infinity. Two different NaN values will be considered equal.</p>

**Specified by:**

* <code>compareTo</code>&nbsp;in interface&nbsp;<code>Comparable&lt;EDecimal&gt;</code>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other value
 or if <code>other</code> is null, or 0 if both values are equal.

### CompareToBinary
    public int CompareToBinary(EFloat other)
Compares an arbitrary-precision binary float with this instance.

**Parameters:**

* <code>other</code> - The other object to compare. Can be null.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater. Returns 0 if
 both values are NaN (even signaling NaN) and 1 if this value is NaN
 (even signaling NaN) and the other isn't, or if the other value is
 null.

### CompareToSignal
    public EDecimal CompareToSignal(EDecimal other, EContext ctx)
Compares the mathematical values of this object and another object, treating
 quiet NaN as signaling. <p>In this method, negative zero and positive
 zero are considered equal.</p> <p>If this object or the other object
 is a quiet NaN or signaling NaN, this method will return a quiet NaN
 and will signal a FlagInvalid flag.</p>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

* <code>ctx</code> - An arithmetic context. The precision, rounding, and exponent
 range are ignored. If <code>HasFlags</code> of the context is true, will
 store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null.

**Returns:**

* Quiet NaN if this object or the other object is NaN, or 0 if both
 objects have the same value, or -1 if this object is less than the
 other value, or 1 if this object is greater.

### CompareToTotalMagnitude
    public int CompareToTotalMagnitude(EDecimal other)
Compares the absolute values of this object and another object, imposing a
 total ordering on all possible values (ignoring their signs). In this
 method: <ul> <li>For objects with the same value, the one with the
 higher exponent has a greater "absolute value".</li> <li>Negative
 zero and positive zero are considered equal.</li> <li>Quiet NaN has a
 higher "absolute value" than signaling NaN. If both objects are quiet
 NaN or both are signaling NaN, the one with the higher diagnostic
 information has a greater "absolute value".</li> <li>NaN has a higher
 "absolute value" than infinity.</li> <li>Infinity has a higher
 "absolute value" than any finite number.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number to compare with this one.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.

### CompareToTotal
    public int CompareToTotal(EDecimal other, EContext ctx)
Compares the values of this object and another object, imposing a total
 ordering on all possible values. In this method: <ul> <li>For objects
 with the same value, the one with the higher exponent has a greater
 "absolute value".</li> <li>Negative zero is less than positive
 zero.</li> <li>Quiet NaN has a higher "absolute value" than signaling
 NaN. If both objects are quiet NaN or both are signaling NaN, the one
 with the higher diagnostic information has a greater "absolute
 value".</li> <li>NaN has a higher "absolute value" than
 infinity.</li> <li>Infinity has a higher "absolute value" than any
 finite number.</li> <li>Negative numbers are less than positive
 numbers.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number to compare with this one.

* <code>ctx</code> - An arithmetic context. Flags will be set in this context only if
 <code>HasFlags</code> and <code>IsSimplified</code> of the context are true and
 only if an operand needed to be rounded before carrying out the
 operation. Can be null.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.
 Does not signal flags if either value is signaling NaN.

### CompareToTotal
    public int CompareToTotal(EDecimal other)
Compares the values of this object and another object, imposing a total
 ordering on all possible values. In this method: <ul> <li>For objects
 with the same value, the one with the higher exponent has a greater
 "absolute value".</li> <li>Negative zero is less than positive
 zero.</li> <li>Quiet NaN has a higher "absolute value" than signaling
 NaN. If both objects are quiet NaN or both are signaling NaN, the one
 with the higher diagnostic information has a greater "absolute
 value".</li> <li>NaN has a higher "absolute value" than
 infinity.</li> <li>Infinity has a higher "absolute value" than any
 finite number.</li> <li>Negative numbers are less than positive
 numbers.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number to compare with this one.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.

### CompareToWithContext
    public EDecimal CompareToWithContext(EDecimal other, EContext ctx)
Compares the mathematical values of this object and another object. <p>In
 this method, negative zero and positive zero are considered
 equal.</p> <p>If this object or the other object is a quiet NaN or
 signaling NaN, this method returns a quiet NaN, and will signal a
 FlagInvalid flag if either is a signaling NaN.</p>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

* <code>ctx</code> - An arithmetic context. The precision, rounding, and exponent
 range are ignored. If <code>HasFlags</code> of the context is true, will
 store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null.

**Returns:**

* Quiet NaN if this object or the other object is NaN, or 0 if both
 objects have the same value, or -1 if this object is less than the
 other value, or 1 if this object is greater.

### Divide
    public EDecimal Divide(EDecimal divisor)
Divides this object by another decimal number and returns the result. When
 possible, the result will be exact.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

**Returns:**

* The quotient of the two numbers. Returns infinity if the divisor is
 0 and the dividend is nonzero. Returns not-a-number (NaN) if the
 divisor and the dividend are 0. Returns NaN if the result can't be
 exact because it would have a nonterminating decimal expansion.

### Divide
    public EDecimal Divide(EDecimal divisor, EContext ctx)
Divides this arbitrary-precision decimal number by another
 arbitrary-precision decimal number. The preferred exponent for the
 result is this object&#x27;s exponent minus the divisor&#x27;s
 exponent.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
 the dividend are 0; or, either <code>ctx</code> is null or <code>ctx</code> 's
 precision is 0, and the result would have a nonterminating decimal
 expansion; or, the rounding mode is ERounding.None and the result is
 not exact.

### DivideAndRemainderNaturalScale
    @Deprecated public EDecimal[] DivideAndRemainderNaturalScale(EDecimal divisor)
Deprecated.&nbsp;<i>Renamed to DivRemNaturalScale.</i>

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

**Returns:**

* A 2 element array consisting of the quotient and remainder in that
 order.

### DivideAndRemainderNaturalScale
    @Deprecated public EDecimal[] DivideAndRemainderNaturalScale(EDecimal divisor, EContext ctx)
Deprecated.&nbsp;<i>Renamed to DivRemNaturalScale.</i>

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only in
 the division portion of the remainder calculation; as a result, it's
 possible for the remainder to have a higher precision than given in
 this context. Flags will be set on the given context only if the
 context's <code>HasFlags</code> is true and the integer part of the
 division result doesn't fit the precision and exponent range without
 rounding. Can be null, in which the precision is unlimited and no
 additional rounding, other than the rounding down to an integer after
 division, is needed.

**Returns:**

* A 2 element array consisting of the quotient and remainder in that
 order.

### DivRemNaturalScale
    public EDecimal[] DivRemNaturalScale(EDecimal divisor)
Calculates the quotient and remainder using the DivideToIntegerNaturalScale
 and the formula in RemainderNaturalScale.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

**Returns:**

* A 2 element array consisting of the quotient and remainder in that
 order.

### DivRemNaturalScale
    public EDecimal[] DivRemNaturalScale(EDecimal divisor, EContext ctx)
Calculates the quotient and remainder using the DivideToIntegerNaturalScale
 and the formula in RemainderNaturalScale.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only in
 the division portion of the remainder calculation; as a result, it's
 possible for the remainder to have a higher precision than given in
 this context. Flags will be set on the given context only if the
 context's <code>HasFlags</code> is true and the integer part of the
 division result doesn't fit the precision and exponent range without
 rounding. Can be null, in which the precision is unlimited and no
 additional rounding, other than the rounding down to an integer after
 division, is needed.

**Returns:**

* A 2 element array consisting of the quotient and remainder in that
 order.

### DivideToExponent
    public EDecimal DivideToExponent(EDecimal divisor, long desiredExponentSmall, EContext ctx)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>desiredExponentSmall</code> - The desired exponent. A negative number places
 the cutoff point to the right of the usual decimal point (so a
 negative number means the number of decimal places to round to). A
 positive number places the cutoff point to the left of the usual
 decimal point.

* <code>ctx</code> - An arithmetic context object to control the rounding mode to use
 if the result must be scaled down to have the same exponent as this
 value. If the precision given in the context is other than 0, calls
 the Quantize method with both arguments equal to the result of the
 operation (and can signal FlagInvalid and return NaN if the result
 doesn't fit the given precision). If <code>HasFlags</code> of the context
 is true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
 the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the context defines an exponent range and the desired
 exponent is outside that range. Signals FlagInvalid and returns
 not-a-number (NaN) if the rounding mode is ERounding.None and the
 result is not exact.

### DivideToExponent
    public EDecimal DivideToExponent(EDecimal divisor, int desiredExponentInt, EContext ctx)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>desiredExponentInt</code> - The desired exponent. A negative number places the
 cutoff point to the right of the usual decimal point (so a negative
 number means the number of decimal places to round to). A positive
 number places the cutoff point to the left of the usual decimal
 point.

* <code>ctx</code> - An arithmetic context object to control the rounding mode to use
 if the result must be scaled down to have the same exponent as this
 value. If the precision given in the context is other than 0, calls
 the Quantize method with both arguments equal to the result of the
 operation (and can signal FlagInvalid and return NaN if the result
 doesn't fit the given precision). If <code>HasFlags</code> of the context
 is true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
 the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the context defines an exponent range and the desired
 exponent is outside that range. Signals FlagInvalid and returns
 not-a-number (NaN) if the rounding mode is ERounding.None and the
 result is not exact.

### DivideToExponent
    public EDecimal DivideToExponent(EDecimal divisor, long desiredExponentSmall, ERounding rounding)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>desiredExponentSmall</code> - The desired exponent. A negative number places
 the cutoff point to the right of the usual decimal point (so a
 negative number means the number of decimal places to round to). A
 positive number places the cutoff point to the left of the usual
 decimal point.

* <code>rounding</code> - The rounding mode to use if the result must be scaled down
 to have the same exponent as this value.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
 the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the rounding mode is ERounding.None and the result is not
 exact.

### DivideToExponent
    public EDecimal DivideToExponent(EDecimal divisor, int desiredExponentInt, ERounding rounding)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>desiredExponentInt</code> - The desired exponent. A negative number places the
 cutoff point to the right of the usual decimal point (so a negative
 number means the number of decimal places to round to). A positive
 number places the cutoff point to the left of the usual decimal
 point.

* <code>rounding</code> - The rounding mode to use if the result must be scaled down
 to have the same exponent as this value.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
 the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the rounding mode is ERounding.None and the result is not
 exact.

### DivideToExponent
    public EDecimal DivideToExponent(EDecimal divisor, EInteger exponent, EContext ctx)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>exponent</code> - The desired exponent. A negative number places the cutoff
 point to the right of the usual decimal point (so a negative number
 means the number of decimal places to round to). A positive number
 places the cutoff point to the left of the usual decimal point.

* <code>ctx</code> - An arithmetic context object to control the rounding mode to use
 if the result must be scaled down to have the same exponent as this
 value. If the precision given in the context is other than 0, calls
 the Quantize method with both arguments equal to the result of the
 operation (and can signal FlagInvalid and return NaN if the result
 doesn't fit the given precision). If <code>HasFlags</code> of the context
 is true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
 the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the context defines an exponent range and the desired
 exponent is outside that range. Signals FlagInvalid and returns
 not-a-number (NaN) if the rounding mode is ERounding.None and the
 result is not exact.

### DivideToExponent
    public EDecimal DivideToExponent(EDecimal divisor, EInteger exponent)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result, using the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>exponent</code> - The desired exponent. A negative number places the cutoff
 point to the right of the usual decimal point (so a negative number
 means the number of decimal places to round to). A positive number
 places the cutoff point to the left of the usual decimal point.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
 the dividend are 0.

### DivideToExponent
    public EDecimal DivideToExponent(EDecimal divisor, long desiredExponentSmall)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 64-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>desiredExponentSmall</code> - The desired exponent. A negative number places
 the cutoff point to the right of the usual decimal point (so a
 negative number means the number of decimal places to round to). A
 positive number places the cutoff point to the left of the usual
 decimal point.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
 the dividend are 0.

### DivideToExponent
    public EDecimal DivideToExponent(EDecimal divisor, int desiredExponentInt)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>desiredExponentInt</code> - The desired exponent. A negative number places the
 cutoff point to the right of the usual decimal point (so a negative
 number means the number of decimal places to round to). A positive
 number places the cutoff point to the left of the usual decimal
 point.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
 the dividend are 0.

### DivideToExponent
    public EDecimal DivideToExponent(EDecimal divisor, EInteger desiredExponent, ERounding rounding)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

* <code>desiredExponent</code> - The desired exponent. A negative number places the
 cutoff point to the right of the usual decimal point (so a negative
 number means the number of decimal places to round to). A positive
 number places the cutoff point to the left of the usual decimal
 point.

* <code>rounding</code> - The rounding mode to use if the result must be scaled down
 to have the same exponent as this value.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Returns not-a-number (NaN) if the divisor and the dividend are 0.
 Returns NaN if the rounding mode is ERounding.None and the result is
 not exact.

### DivideToIntegerNaturalScale
    public EDecimal DivideToIntegerNaturalScale(EDecimal divisor)
Divides two arbitrary-precision decimal numbers, and returns the integer
 part of the result, rounded down, with the preferred exponent set to
 this value&#x27;s exponent minus the divisor&#x27;s exponent.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision decimal number to divide by.

**Returns:**

* The integer part of the quotient of the two objects. Signals
 FlagDivideByZero and returns infinity if the divisor is 0 and the
 dividend is nonzero. Signals FlagInvalid and returns not-a-number
 (NaN) if the divisor and the dividend are 0.

### DivideToIntegerNaturalScale
    public EDecimal DivideToIntegerNaturalScale(EDecimal divisor, EContext ctx)
Divides this object by another object, and returns the integer part of the
 result (which is initially rounded down), with the preferred exponent
 set to this value&#x27;s exponent minus the divisor&#x27;s exponent.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the integer part of the result. Flags will be
 set on the given context only if the context's <code>HasFlags</code> is
 true and the integer part of the result doesn't fit the precision and
 exponent range without rounding. Can be null, in which the precision
 is unlimited and no additional rounding, other than the rounding down
 to an integer after division, is needed.

**Returns:**

* The integer part of the quotient of the two objects. Signals
 FlagInvalid and returns not-a-number (NaN) if the return value would
 overflow the exponent range. Signals FlagDivideByZero and returns
 infinity if the divisor is 0 and the dividend is nonzero. Signals
 FlagInvalid and returns not-a-number (NaN) if the divisor and the
 dividend are 0. Signals FlagInvalid and returns not-a-number (NaN) if
 the rounding mode is ERounding.None and the result is not exact.

### DivideToIntegerZeroScale
    public EDecimal DivideToIntegerZeroScale(EDecimal divisor, EContext ctx)
Divides this object by another object, and returns the integer part of the
 result, with the exponent set to 0.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision. The
 rounding and exponent range settings of this context are ignored. If
 <code>HasFlags</code> of the context is true, will also store the flags
 resulting from the operation (the flags are in addition to the
 pre-existing flags). Can be null, in which case the precision is
 unlimited.

**Returns:**

* The integer part of the quotient of the two objects. The exponent
 will be set to 0. Signals FlagDivideByZero and returns infinity if
 the divisor is 0 and the dividend is nonzero. Signals FlagInvalid and
 returns not-a-number (NaN) if the divisor and the dividend are 0, or
 if the result doesn't fit the given precision.

### DivideToSameExponent
    public EDecimal DivideToSameExponent(EDecimal divisor, ERounding rounding)
Divides this object by another decimal number and returns a result with the
 same exponent as this object (the dividend).

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>rounding</code> - The rounding mode to use if the result must be scaled down
 to have the same exponent as this value.

**Returns:**

* The quotient of the two numbers. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
 the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the rounding mode is ERounding.None and the result is not
 exact.

### equals
    public boolean equals(EDecimal other)
Determines whether this object&#x27;s mantissa (significand) and exponent
 are equal to those of another object.

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

**Returns:**

* true if this object's mantissa (significand) and exponent are equal
 to those of another object; otherwise, false .

### equals
    public boolean equals(Object obj)
Determines whether this object&#x27;s mantissa (significand) and exponent
 are equal to those of another object and that other object is an
 arbitrary-precision decimal number.

**Overrides:**

* <code>equals</code>&nbsp;in class&nbsp;<code>Object</code>

**Parameters:**

* <code>obj</code> - An arbitrary object.

**Returns:**

* true if the objects are equal; otherwise, false .

### Exp
    public EDecimal Exp(EContext ctx)
Finds e (the base of natural logarithms) raised to the power of this
 object&#x27;s value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the exponential function's results are generally
 not exact.</i> (Unlike in the General Decimal Arithmetic
 Specification, any rounding mode is allowed.).

**Returns:**

* Exponential of this object. If this object's value is 1, returns an
 approximation to " e" within the given precision. Signals FlagInvalid
 and returns not-a-number (NaN) if the parameter <code>ctx</code> is null
 or the precision is unlimited (the context's Precision property is
 0).

### hashCode
    public int hashCode()
Calculates this object&#x27;s hash code.

**Overrides:**

* <code>hashCode</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* This object's hash code.

### IsInfinity
    public boolean IsInfinity()
Gets a value indicating whether this object is positive or negative
 infinity.

**Returns:**

* true if this object is positive or negative infinity; otherwise,
 false .

### IsNaN
    public boolean IsNaN()
Gets a value indicating whether this object is not a number (NaN).

**Returns:**

* true if this object is not a number (NaN); otherwise, false .

### IsNegativeInfinity
    public boolean IsNegativeInfinity()
Returns whether this object is negative infinity.

**Returns:**

* true if this object is negative infinity; otherwise, false .

### IsPositiveInfinity
    public boolean IsPositiveInfinity()
Returns whether this object is positive infinity.

**Returns:**

* true if this object is positive infinity; otherwise, false .

### IsQuietNaN
    public boolean IsQuietNaN()
Gets a value indicating whether this object is a quiet not-a-number value.

**Returns:**

* true if this object is a quiet not-a-number value; otherwise, false
 .

### IsSignalingNaN
    public boolean IsSignalingNaN()
Gets a value indicating whether this object is a signaling not-a-number
 value.

**Returns:**

* true if this object is a signaling not-a-number value; otherwise,
 false .

### Log
    public EDecimal Log(EContext ctx)
Finds the natural logarithm of this object, that is, the power (exponent)
 that e (the base of natural logarithms) must be raised to in order to
 equal this object&#x27;s value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the ln function's results are generally not
 exact.</i> (Unlike in the General Decimal Arithmetic Specification,
 any rounding mode is allowed.).

**Returns:**

* Ln(this object). Signals the flag FlagInvalid and returns NaN if
 this object is less than 0 (the result would be a complex number with
 a real part equal to Ln of this object's absolute value and an
 imaginary part equal to pi, but the return value is still NaN.).
 Signals FlagInvalid and returns not-a-number (NaN) if the parameter
 <code>ctx</code> is null or the precision is unlimited (the context's
 Precision property is 0). Signals no flags and returns negative
 infinity if this object's value is 0.

### Log10
    public EDecimal Log10(EContext ctx)
Finds the base-10 logarithm of this object, that is, the power (exponent)
 that the number 10 must be raised to in order to equal this
 object&#x27;s value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the ln function's results are generally not
 exact.</i> (Unlike in the General Decimal Arithmetic Specification,
 any rounding mode is allowed.).

**Returns:**

* Ln(this object)/Ln(10). Signals the flag FlagInvalid and returns
 not-a-number (NaN) if this object is less than 0. Signals FlagInvalid
 and returns not-a-number (NaN) if the parameter <code>ctx</code> is null
 or the precision is unlimited (the context's Precision property is
 0).

### MovePointLeft
    public EDecimal MovePointLeft(int places)
Returns a number similar to this number but with the decimal point moved to
 the left.

**Parameters:**

* <code>places</code> - The number of decimal places to move the decimal point to the
 left. If this number is negative, instead moves the decimal point to
 the right by this number's absolute value.

**Returns:**

* A number whose exponent is decreased by <code>places</code>, but not to
 more than 0.

### MovePointLeft
    public EDecimal MovePointLeft(int places, EContext ctx)
Returns a number similar to this number but with the decimal point moved to
 the left.

**Parameters:**

* <code>places</code> - The number of decimal places to move the decimal point to the
 left. If this number is negative, instead moves the decimal point to
 the right by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is decreased by <code>places</code>, but not to
 more than 0.

### MovePointLeft
    public EDecimal MovePointLeft(EInteger bigPlaces)
Returns a number similar to this number but with the decimal point moved to
 the left.

**Parameters:**

* <code>bigPlaces</code> - The number of decimal places to move the decimal point to
 the left. If this number is negative, instead moves the decimal point
 to the right by this number's absolute value.

**Returns:**

* A number whose exponent is decreased by <code>bigPlaces</code>, but not
 to more than 0.

### MovePointLeft
    public EDecimal MovePointLeft(EInteger bigPlaces, EContext ctx)
Returns a number similar to this number but with the decimal point moved to
 the left.

**Parameters:**

* <code>bigPlaces</code> - The number of decimal places to move the decimal point to
 the left. If this number is negative, instead moves the decimal point
 to the right by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is decreased by <code>bigPlaces</code>, but not
 to more than 0.

### MovePointRight
    public EDecimal MovePointRight(int places)
Returns a number similar to this number but with the decimal point moved to
 the right.

**Parameters:**

* <code>places</code> - The number of decimal places to move the decimal point to the
 right. If this number is negative, instead moves the decimal point to
 the left by this number's absolute value.

**Returns:**

* A number whose exponent is increased by <code>places</code>, but not to
 more than 0.

### MovePointRight
    public EDecimal MovePointRight(int places, EContext ctx)
Returns a number similar to this number but with the decimal point moved to
 the right.

**Parameters:**

* <code>places</code> - The number of decimal places to move the decimal point to the
 right. If this number is negative, instead moves the decimal point to
 the left by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is increased by <code>places</code>, but not to
 more than 0.

### MovePointRight
    public EDecimal MovePointRight(EInteger bigPlaces)
Returns a number similar to this number but with the decimal point moved to
 the right.

**Parameters:**

* <code>bigPlaces</code> - The number of decimal places to move the decimal point to
 the right. If this number is negative, instead moves the decimal
 point to the left by this number's absolute value.

**Returns:**

* A number whose exponent is increased by <code>bigPlaces</code>, but not
 to more than 0.

### MovePointRight
    public EDecimal MovePointRight(EInteger bigPlaces, EContext ctx)
Returns a number similar to this number but with the decimal point moved to
 the right.

**Parameters:**

* <code>bigPlaces</code> - The number of decimal places to move the decimal point to
 the right. If this number is negative, instead moves the decimal
 point to the left by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is increased by <code>bigPlaces</code>, but not
 to more than 0.

### Multiply
    public EDecimal Multiply(EDecimal otherValue)
Multiplies two decimal numbers. The resulting exponent will be the sum of
 the exponents of the two decimal numbers.

**Parameters:**

* <code>otherValue</code> - Another decimal number.

**Returns:**

* The product of the two decimal numbers.

### Multiply
    public EDecimal Multiply(EDecimal op, EContext ctx)
Multiplies two decimal numbers. The resulting scale will be the sum of the
 scales of the two decimal numbers. The result&#x27;s sign is positive
 if both operands have the same sign, and negative if they have
 different signs.

**Parameters:**

* <code>op</code> - Another decimal number.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The product of the two decimal numbers.

### MultiplyAndAdd
    public EDecimal MultiplyAndAdd(EDecimal multiplicand, EDecimal augend)
Multiplies by one decimal number, and then adds another decimal number.

**Parameters:**

* <code>multiplicand</code> - The value to multiply.

* <code>augend</code> - The value to add.

**Returns:**

* The result this * <code>multiplicand</code> + <code>augend</code>.

### MultiplyAndAdd
    public EDecimal MultiplyAndAdd(EDecimal op, EDecimal augend, EContext ctx)
Multiplies by one value, and then adds another value.

**Parameters:**

* <code>op</code> - The value to multiply.

* <code>augend</code> - The value to add.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed. If
 the precision doesn't indicate a simplified arithmetic, rounding and
 precision/exponent adjustment is done only once, namely, after
 multiplying and adding.

**Returns:**

* The result thisValue * multiplicand + augend.

### MultiplyAndSubtract
    public EDecimal MultiplyAndSubtract(EDecimal op, EDecimal subtrahend, EContext ctx)
Multiplies by one value, and then subtracts another value.

**Parameters:**

* <code>op</code> - The value to multiply.

* <code>subtrahend</code> - The value to subtract.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed. If
 the precision doesn't indicate a simplified arithmetic, rounding and
 precision/exponent adjustment is done only once, namely, after
 multiplying and subtracting.

**Returns:**

* The result thisValue * multiplicand - subtrahend.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>op</code> or <code>subtrahend</code> is null.

### Negate
    public EDecimal Negate()
Gets an object with the same value as this one, but with the sign reversed.

**Returns:**

* An arbitrary-precision decimal number. If this value is positive
 zero, returns negative zero. Returns signaling NaN if this value is
 signaling NaN.

### Negate
    public EDecimal Negate(EContext context)
Returns a decimal number with the same value as this object but with the
 sign reversed.

**Parameters:**

* <code>context</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* An arbitrary-precision decimal number. If this value is positive
 zero, returns positive zero. Signals FlagInvalid and returns quiet
 NaN if this value is signaling NaN.

### NextMinus
    public EDecimal NextMinus(EContext ctx)
Finds the largest value that&#x27;s smaller than the given value.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision and
 exponent range of the result. The rounding mode from this context is
 ignored. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags).

**Returns:**

* Returns the largest value that's less than the given value. Returns
 negative infinity if the result is negative infinity. Signals
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>ctx</code> is null, the precision is 0, or <code>ctx</code> has an unlimited
 exponent range.

### NextPlus
    public EDecimal NextPlus(EContext ctx)
Finds the smallest value that&#x27;s greater than the given value.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision and
 exponent range of the result. The rounding mode from this context is
 ignored. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags).

**Returns:**

* Returns the smallest value that's greater than the given
 value.Signals FlagInvalid and returns not-a-number (NaN) if the
 parameter <code>ctx</code> is null, the precision is 0, or <code>ctx</code> has
 an unlimited exponent range.

### NextToward
    public EDecimal NextToward(EDecimal otherValue, EContext ctx)
Finds the next value that is closer to the other object&#x27;s value than
 this object&#x27;s value. Returns a copy of this value with the same
 sign as the other value if both values are equal.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision decimal number that the return
 value will approach.

* <code>ctx</code> - An arithmetic context object to control the precision and
 exponent range of the result. The rounding mode from this context is
 ignored. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags).

**Returns:**

* Returns the next value that is closer to the other object' s value
 than this object's value. Signals FlagInvalid and returns NaN if the
 parameter <code>ctx</code> is null, the precision is 0, or <code>ctx</code> has
 an unlimited exponent range.

### Plus
    public EDecimal Plus(EContext ctx)
Rounds this object&#x27;s value to a given precision, using the given
 rounding mode and range of exponent, and also converts negative zero
 to positive zero.

**Parameters:**

* <code>ctx</code> - A context for controlling the precision, rounding mode, and
 exponent range. Can be null, in which case the precision is unlimited
 and rounding isn't needed.

**Returns:**

* The closest value to this object's value, rounded to the specified
 precision. Returns the same value as this object if <code>ctx</code> is
 null or the precision and exponent range are unlimited.

### Pow
    public EDecimal Pow(EDecimal exponent, EContext ctx)
Raises this object&#x27;s value to the given exponent.

**Parameters:**

* <code>exponent</code> - An arbitrary-precision decimal number expressing the
 exponent to raise this object's value to.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* This^exponent. Signals the flag FlagInvalid and returns NaN if this
 object and exponent are both 0; or if this value is less than 0 and
 the exponent either has a fractional part or is infinity. Signals
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>ctx</code> is null or the precision is unlimited (the context's Precision
 property is 0), and the exponent has a fractional part.

### Pow
    public EDecimal Pow(int exponentSmall, EContext ctx)
Raises this object&#x27;s value to the given exponent.

**Parameters:**

* <code>exponentSmall</code> - The exponent to raise this object's value to.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* This^exponent. Signals the flag FlagInvalid and returns NaN if this
 object and exponent are both 0.

### Pow
    public EDecimal Pow(int exponentSmall)
Raises this object&#x27;s value to the given exponent.

**Parameters:**

* <code>exponentSmall</code> - The exponent to raise this object's value to.

**Returns:**

* This^exponent. Returns not-a-number (NaN) if this object and
 exponent are both 0.

### Precision
    public EInteger Precision()
Finds the number of digits in this number's mantissa (significand). Returns
 1 if this value is 0, and 0 if this value is infinity or not-a-number
 (NaN).

**Returns:**

* An arbitrary-precision integer.

### Quantize
    public EDecimal Quantize(EInteger desiredExponent, EContext ctx)
Returns a decimal number with the same value but a new exponent. <p>Note
 that this is not always the same as rounding to a given number of
 decimal places, since it can fail if the difference between this
 value's exponent and the desired exponent is too big, depending on
 the maximum precision. If rounding to a number of decimal places is
 desired, it's better to use the RoundToExponent and RoundToIntegral
 methods instead.</p> <p><b>Remark:</b> This method can be used to
 implement fixed-point decimal arithmetic, in which each decimal
 number has a fixed number of digits after the decimal point. The
 following code example returns a fixed-point number with up to 20
 digits before and exactly 5 digits after the decimal point:</p>
 <code>  // After performing arithmetic operations, adjust  // the
 number to 5 digits after the decimal point number = number.Quantize(
 EInteger.FromInt32(-5),  // five digits after the decimal point
 EContext.ForPrecision(25)  // 25-digit precision); </code> <p>A
 fixed-point decimal arithmetic in which no digits come after the
 decimal point (a desired exponent of 0) is considered an "integer
 arithmetic".</p>

**Parameters:**

* <code>desiredExponent</code> - The desired exponent for the result. The exponent is
 the number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags). Can be null, in which case the default
 rounding mode is HalfEven.

**Returns:**

* A decimal number with the same value as this object but with the
 exponent changed. Signals FlagInvalid and returns not-a-number (NaN)
 if this object is infinity, if the rounded result can't fit the given
 precision, or if the context defines an exponent range and the given
 exponent is outside that range.

### Quantize
    public EDecimal Quantize(int desiredExponentInt, ERounding rounding)
Returns a decimal number with the same value as this one but a new exponent.
 <p><b>Remark:</b> This method can be used to implement fixed-point
 decimal arithmetic, in which a fixed number of digits come after the
 decimal point. A fixed-point decimal arithmetic in which no digits
 come after the decimal point (a desired exponent of 0) is considered
 an "integer arithmetic".</p>

**Parameters:**

* <code>desiredExponentInt</code> - The desired exponent for the result. The exponent
 is the number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>rounding</code> - A rounding mode to use in case the result needs to be
 rounded to fit the given exponent.

**Returns:**

* A decimal number with the same value as this object but with the
 exponent changed. Returns not-a-number (NaN) if this object is
 infinity, or if the rounding mode is ERounding.None and the result is
 not exact.

### Quantize
    public EDecimal Quantize(int desiredExponentInt, EContext ctx)
Returns a decimal number with the same value but a new exponent. <p>Note
 that this is not always the same as rounding to a given number of
 decimal places, since it can fail if the difference between this
 value's exponent and the desired exponent is too big, depending on
 the maximum precision. If rounding to a number of decimal places is
 desired, it's better to use the RoundToExponent and RoundToIntegral
 methods instead.</p> <p><b>Remark:</b> This method can be used to
 implement fixed-point decimal arithmetic, in which each decimal
 number has a fixed number of digits after the decimal point. The
 following code example returns a fixed-point number with up to 20
 digits before and exactly 5 digits after the decimal point:</p>
 <code>  // After performing arithmetic operations, adjust  // the
 number to 5 digits after the decimal point number =
 number.Quantize(-5,  // five digits after the decimal point
 EContext.ForPrecision(25)  // 25-digit precision); </code> <p>A
 fixed-point decimal arithmetic in which no digits come after the
 decimal point (a desired exponent of 0) is considered an "integer
 arithmetic".</p>

**Parameters:**

* <code>desiredExponentInt</code> - The desired exponent for the result. The exponent
 is the number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags). Can be null, in which case the default
 rounding mode is HalfEven.

**Returns:**

* A decimal number with the same value as this object but with the
 exponent changed. Signals FlagInvalid and returns not-a-number (NaN)
 if this object is infinity, if the rounded result can't fit the given
 precision, or if the context defines an exponent range and the given
 exponent is outside that range.

### Quantize
    public EDecimal Quantize(EDecimal otherValue, EContext ctx)
Returns a decimal number with the same value as this object but with the
 same exponent as another decimal number. <p>Note that this is not
 always the same as rounding to a given number of decimal places,
 since it can fail if the difference between this value's exponent and
 the desired exponent is too big, depending on the maximum precision.
 If rounding to a number of decimal places is desired, it's better to
 use the RoundToExponent and RoundToIntegral methods instead.</p>
 <p><b>Remark:</b> This method can be used to implement fixed-point
 decimal arithmetic, in which a fixed number of digits come after the
 decimal point. A fixed-point decimal arithmetic in which no digits
 come after the decimal point (a desired exponent of 0) is considered
 an "integer arithmetic".</p>

**Parameters:**

* <code>otherValue</code> - A decimal number containing the desired exponent of the
 result. The mantissa (significand) is ignored. The exponent is the
 number of fractional digits in the result, expressed as a negative
 number. Can also be positive, which eliminates lower-order places
 from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags). Can be null, in which case the default
 rounding mode is HalfEven.

**Returns:**

* A decimal number with the same value as this object but with the
 exponent changed. Signals FlagInvalid and returns not-a-number (NaN)
 if the result can't fit the given precision without rounding, or if
 the arithmetic context defines an exponent range and the given
 exponent is outside that range.

### Reduce
    public EDecimal Reduce(EContext ctx)
Removes trailing zeros from this object&#x27;s mantissa (significand). For
 example, 1.00 becomes 1. <p>If this object's value is 0, changes the
 exponent to 0.</p>

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* This value with trailing zeros removed. Note that if the result has
 a very high exponent and the context says to clamp high exponents,
 there may still be some trailing zeros in the mantissa (significand).

### Remainder
    public EDecimal Remainder(EDecimal divisor, EContext ctx)
Finds the remainder that results when dividing two arbitrary-precision
 decimal numbers. The remainder is the value that remains when the
 absolute value of this object is divided by the absolute value of the
 other object; the remainder has the same sign (positive or negative)
 as this object's value.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used both in
 the division portion and in the remainder portion of the remainder
 calculation. If <code>HasFlags</code> of the context is true, will also
 store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null, in which case the
 precision is unlimited and no additional rounding (other than the
 rounding from integer division) is needed.

**Returns:**

* The remainder of the two numbers. Signals FlagInvalid and returns
 not-a-number (NaN) if the divisor is 0, or if the result doesn't fit
 the given precision.

### RemainderNaturalScale
    public EDecimal RemainderNaturalScale(EDecimal divisor)
Calculates the remainder of a number by the formula <code>"this" - (("this" /
 "divisor") * "divisor")</code>

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* An arbitrary-precision decimal number.

### RemainderNaturalScale
    public EDecimal RemainderNaturalScale(EDecimal divisor, EContext ctx)
Calculates the remainder of a number by the formula "this" - (("this" /
 "divisor") * "divisor").

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only in
 the division portion of the remainder calculation; as a result, it's
 possible for the return value to have a higher precision than given
 in this context. Flags will be set on the given context only if the
 context's <code>HasFlags</code> is true and the integer part of the
 division result doesn't fit the precision and exponent range without
 rounding. Can be null, in which the precision is unlimited and no
 additional rounding, other than the rounding down to an integer after
 division, is needed.

**Returns:**

* An arbitrary-precision decimal number.

### RemainderNear
    public EDecimal RemainderNear(EDecimal divisor, EContext ctx)
Finds the distance to the closest multiple of the given divisor, based on
 the result of dividing this object&#x27;s value by another
 object&#x27;s value. <ul> <li>If this and the other object divide
 evenly, the result is 0.</li> <li>If the remainder's absolute value
 is less than half of the divisor's absolute value, the result has the
 same sign as this object and will be the distance to the closest
 multiple.</li> <li>If the remainder's absolute value is more than
 half of the divisor' s absolute value, the result has the opposite
 sign of this object and will be the distance to the closest
 multiple.</li> <li>If the remainder's absolute value is exactly half
 of the divisor's absolute value, the result has the opposite sign of
 this object if the quotient, rounded down, is odd, and has the same
 sign as this object if the quotient, rounded down, is even, and the
 result's absolute value is half of the divisor's absolute
 value.</li></ul> This function is also known as the "IEEE Remainder"
 function.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision. The
 rounding and exponent range settings of this context are ignored (the
 rounding mode is always treated as HalfEven). If <code>HasFlags</code> of
 the context is true, will also store the flags resulting from the
 operation (the flags are in addition to the pre-existing flags). Can
 be null, in which the precision is unlimited.

**Returns:**

* The distance of the closest multiple. Signals FlagInvalid and
 returns not-a-number (NaN) if the divisor is 0, or either the result
 of integer division (the quotient) or the remainder wouldn't fit the
 given precision.

### RoundToExponent
    public EDecimal RoundToExponent(EInteger exponent, EContext ctx)
Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A decimal number rounded to the closest value representable in the
 given precision. If the result can't fit the precision, additional
 digits are discarded to make it fit. Signals FlagInvalid and returns
 not-a-number (NaN) if the precision context defines an exponent
 range, the new exponent must be changed to the given exponent when
 rounding, and the given exponent is outside of the valid range of the
 arithmetic context.

### RoundToExponent
    public EDecimal RoundToExponent(EInteger exponent)
Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary, using the HalfEven rounding mode.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

**Returns:**

* A decimal number rounded to the closest value representable for the
 given exponent.

### RoundToExponent
    public EDecimal RoundToExponent(EInteger exponent, ERounding rounding)
Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary, using the given rounding mode.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>rounding</code> - Desired mode for rounding this number's value.

**Returns:**

* A decimal number rounded to the closest value representable for the
 given exponent.

### RoundToExponent
    public EDecimal RoundToExponent(int exponentSmall)
Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary, using the HalfEven rounding mode.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

**Returns:**

* A decimal number rounded to the closest value representable for the
 given exponent.

### RoundToExponent
    public EDecimal RoundToExponent(int exponentSmall, EContext ctx)
Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A decimal number rounded to the closest value representable in the
 given precision. If the result can't fit the precision, additional
 digits are discarded to make it fit. Signals FlagInvalid and returns
 not-a-number (NaN) if the precision context defines an exponent
 range, the new exponent must be changed to the given exponent when
 rounding, and the given exponent is outside of the valid range of the
 arithmetic context.

### RoundToExponent
    public EDecimal RoundToExponent(int exponentSmall, ERounding rounding)
Returns a decimal number with the same value as this object but rounded to a
 new exponent if necessary.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>rounding</code> - The desired mode to use to round the given number to the
 given exponent.

**Returns:**

* A decimal number rounded to the given negative number of decimal
 places.

### RoundToExponentExact
    public EDecimal RoundToExponentExact(EInteger exponent, EContext ctx)
Returns a decimal number with the same value as this object but rounded to
 the given exponent, and signals an inexact flag if the result would
 be inexact.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A decimal number rounded to the closest value representable in the
 given precision. Signals FlagInvalid and returns not-a-number (NaN)
 if the result can't fit the given precision without rounding. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to the
 given exponent when rounding, and the given exponent is outside of
 the valid range of the arithmetic context.

### RoundToExponentExact
    public EDecimal RoundToExponentExact(int exponentSmall, EContext ctx)
Returns a decimal number with the same value as this object but rounded to
 an integer, and signals an inexact flag if the result would be
 inexact.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A decimal number rounded to the closest value representable in the
 given precision. Signals FlagInvalid and returns not-a-number (NaN)
 if the result can't fit the given precision without rounding. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to the
 given exponent when rounding, and the given exponent is outside of
 the valid range of the arithmetic context.

### RoundToExponentExact
    public EDecimal RoundToExponentExact(int exponentSmall, ERounding rounding)
Returns a decimal number with the same value as this object but rounded to
 an integer, and signals an inexact flag if the result would be
 inexact.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the thousandth
 (10^-3, 0.0001), and 3 means round to the thousand (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>rounding</code> - Desired mode for rounding this object's value.

**Returns:**

* A decimal number rounded to the closest value representable using
 the given exponent.

### RoundToIntegerExact
    public EDecimal RoundToIntegerExact(EContext ctx)
Returns a decimal number with the same value as this object but rounded to
 an integer, and signals an inexact flag if the result would be
 inexact.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A decimal number rounded to the closest integer representable in the
 given precision. Signals FlagInvalid and returns not-a-number (NaN)
 if the result can't fit the given precision without rounding. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to 0 when
 rounding, and 0 is outside of the valid range of the arithmetic
 context.

### RoundToIntegerNoRoundedFlag
    public EDecimal RoundToIntegerNoRoundedFlag(EContext ctx)
Returns a decimal number with the same value as this object but rounded to
 an integer, without adding the <code>FlagInexact</code> or FlagRounded
 flags.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags), except that this function will never add the
 FlagRounded and <code>FlagInexact</code> flags (the only difference
 between this and RoundToExponentExact). Can be null, in which case
 the default rounding mode is HalfEven.

**Returns:**

* A decimal number rounded to the closest integer representable in the
 given precision. If the result can't fit the precision, additional
 digits are discarded to make it fit. Signals FlagInvalid and returns
 not-a-number (NaN) if the precision context defines an exponent
 range, the new exponent must be changed to 0 when rounding, and 0 is
 outside of the valid range of the arithmetic context.

### RoundToIntegralExact
    @Deprecated public EDecimal RoundToIntegralExact(EContext ctx)
Deprecated.&nbsp;<i>Renamed to RoundToIntegerExact.</i>

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A decimal number rounded to the closest integer representable in the
 given precision. Signals FlagInvalid and returns not-a-number (NaN)
 if the result can't fit the given precision without rounding. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to 0 when
 rounding, and 0 is outside of the valid range of the arithmetic
 context.

### RoundToIntegralNoRoundedFlag
    @Deprecated public EDecimal RoundToIntegralNoRoundedFlag(EContext ctx)
Deprecated.&nbsp;<i>Renamed to RoundToIntegerNoRoundedFlag.</i>

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags), except that this function will never add the
 FlagRounded and <code>FlagInexact</code> flags (the only difference
 between this and RoundToExponentExact). Can be null, in which case
 the default rounding mode is HalfEven.

**Returns:**

* A decimal number rounded to the closest integer representable in the
 given precision. If the result can't fit the precision, additional
 digits are discarded to make it fit. Signals FlagInvalid and returns
 not-a-number (NaN) if the precision context defines an exponent
 range, the new exponent must be changed to 0 when rounding, and 0 is
 outside of the valid range of the arithmetic context.

### RoundToPrecision
    public EDecimal RoundToPrecision(EContext ctx)
Rounds this object&#x27;s value to a given precision, using the given
 rounding mode and range of exponent.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The closest value to this object's value, rounded to the specified
 precision. Returns the same value as this object if <code>ctx</code> is
 null or the precision and exponent range are unlimited.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen(int places)
Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>places</code> - The power of 10 to scale by.

**Returns:**

* An arbitrary-precision decimal number.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen(int places, EContext ctx)
Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>places</code> - The power of 10 to scale by.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* An arbitrary-precision decimal number.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen(EInteger bigPlaces)
Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>bigPlaces</code> - The power of 10 to scale by.

**Returns:**

* An arbitrary-precision decimal number.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen(EInteger bigPlaces, EContext ctx)
Returns a number similar to this number but with its scale adjusted.

**Parameters:**

* <code>bigPlaces</code> - The power of 10 to scale by.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* A number whose exponent is increased by <code>bigPlaces</code>.

### Sqrt
    public EDecimal Sqrt(EContext ctx)
Finds the square root of this object&#x27;s value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the square root function's results are generally
 not exact for many inputs.</i> (Unlike in the General Decimal
 Arithmetic Specification, any rounding mode is allowed.).

**Returns:**

* The square root. Signals the flag FlagInvalid and returns NaN if
 this object is less than 0 (the square root would be a complex
 number, but the return value is still NaN). Signals FlagInvalid and
 returns not-a-number (NaN) if the parameter <code>ctx</code> is null or
 the precision is unlimited (the context's Precision property is 0).

### SquareRoot
    @Deprecated public EDecimal SquareRoot(EContext ctx)
Deprecated.&nbsp;<i>Renamed to Sqrt.</i>

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the square root function's results are generally
 not exact for many inputs.</i> (Unlike in the General Decimal
 Arithmetic Specification, any rounding mode is allowed.).

**Returns:**

* The square root. Signals the flag FlagInvalid and returns NaN if
 this object is less than 0 (the square root would be a complex
 number, but the return value is still NaN). Signals FlagInvalid and
 returns not-a-number (NaN) if the parameter <code>ctx</code> is null or
 the precision is unlimited (the context's Precision property is 0).

### Subtract
    public EDecimal Subtract(EDecimal otherValue)
Subtracts an arbitrary-precision decimal number from this instance and
 returns the result.

**Parameters:**

* <code>otherValue</code> - The number to subtract from this instance's value.

**Returns:**

* The difference of the two objects.

### Subtract
    public EDecimal Subtract(EDecimal otherValue, EContext ctx)
Subtracts an arbitrary-precision decimal number from this instance.

**Parameters:**

* <code>otherValue</code> - The number to subtract from this instance's value.

* <code>ctx</code> - An arithmetic context to control precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The difference of the two objects.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>otherValue</code> is
 null.

### ToDouble
    public double ToDouble()
Converts this value to its closest equivalent as a 64-bit floating-point
 number. The half-even rounding mode is used. <p>If this value is a
 NaN, sets the high bit of the 64-bit floating point number's mantissa
 (significand) for a quiet NaN, and clears it for a signaling NaN.
 Then the next highest bit of the mantissa (significand) is cleared
 for a quiet NaN, and set for a signaling NaN. Then the other bits of
 the mantissa (significand) are set to the lowest bits of this
 object's unsigned mantissa (significand).</p>

**Returns:**

* The closest 64-bit floating-point number to this value. The return
 value can be positive infinity or negative infinity if this value
 exceeds the range of a 64-bit floating point number.

### ToEInteger
    public EInteger ToEInteger()
Converts this value to an arbitrary-precision integer. Any fractional part
 in this value will be discarded when converting to an
 arbitrary-precision integer.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is infinity or
 not-a-number (NaN).

### ToEIntegerExact
    public EInteger ToEIntegerExact()
Converts this value to an arbitrary-precision integer, checking whether the
 fractional part of the integer would be lost.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is infinity or
 not-a-number (NaN).

* <code>ArithmeticException</code> - This object's value is not an exact integer.

### ToEngineeringString
    public String ToEngineeringString()
Same as toString(), except that when an exponent is used it will be a
 multiple of 3.

**Returns:**

* A text string.

### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat()
Deprecated.&nbsp;<i>Renamed to ToEFloat.</i>

**Returns:**

* An arbitrary-precision binary float.

### ToEFloat
    public EFloat ToEFloat()
Creates a binary floating-point number from this object&#x27;s value. Note
 that if the binary floating-point number contains a negative
 exponent, the resulting value might not be exact, in which case the
 resulting binary float will be an approximation of this decimal
 number's value.

**Returns:**

* An arbitrary-precision binary float.

### ToInt32Checked
    public int ToInt32Checked()
Converts this number to a 32-bit signed integer, if possible, without
 truncating or rounding it.

**Returns:**

* A 32-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This number's value is infinity,
 not-a-number, or doesn't fit into a 32-bit signed integer.

### ToInt32Unchecked
    public int ToInt32Unchecked()
Not documented yet.

**Returns:**

* A 32-bit signed integer. Returns 0 if this value is infinity or
 not-a-number.

### ToInt64Checked
    public long ToInt64Checked()
Converts this number to a 64-bit signed integer, if possible, without
 truncating or rounding it.

**Returns:**

* A 64-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This number's value is infinity,
 not-a-number, or doesn't fit into a 64-bit signed integer.

### ToInt64Unchecked
    public long ToInt64Unchecked()
Not documented yet.

**Returns:**

* A 64-bit signed integer. Returns 0 if this value is infinity or
 not-a-number.

### ToPlainString
    public String ToPlainString()
Converts this value to a string, but without using exponential notation.

**Returns:**

* A text string.

### ToSingle
    public float ToSingle()
Converts this value to its closest equivalent as a 32-bit floating-point
 number. The half-even rounding mode is used. <p>If this value is a
 NaN, sets the high bit of the 32-bit floating point number's mantissa
 (significand) for a quiet NaN, and clears it for a signaling NaN.
 Then the next highest bit of the mantissa (significand) is cleared
 for a quiet NaN, and set for a signaling NaN. Then the other bits of
 the mantissa (significand) are set to the lowest bits of this
 object's unsigned mantissa (significand).</p>

**Returns:**

* The closest 32-bit floating-point number to this value. The return
 value can be positive infinity or negative infinity if this value
 exceeds the range of a 32-bit floating point number.

### toString
    public String toString()
Converts this value to a string. Returns a value compatible with this
 class's FromString method.

**Overrides:**

* <code>toString</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A string representation of this object. The text string will be in
 exponential notation if the exponent is greater than 0 or if the
 number's first nonzero digit is more than five digits after the
 decimal point.

### Ulp
    public EDecimal Ulp()
Returns the unit in the last place. The mantissa (significand) will be 1 and
 the exponent will be this number's exponent. Returns 1 with an
 exponent of 0 if this number is infinity or not-a-number (NaN).

**Returns:**

* An arbitrary-precision decimal number.

### ToEFloat
    public EFloat ToEFloat(EContext ec)
Not documented yet.

**Parameters:**

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An EFloat object.
