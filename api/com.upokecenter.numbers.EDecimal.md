# com.upokecenter.numbers.EDecimal

    public final class EDecimal extends java.lang.Object implements java.lang.Comparable<EDecimal>

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
 rounding is an arbitrary-precision decimal number (for example,
 multiplying a price by a premium rate, then rounding, should result in
 a decimal amount of money).</p> <p>On the other hand, most
 implementations of <code>float</code> and <code>double</code> , including in C#
 and Java, store numbers in a binary (base-2) floating-point format and
 use binary floating-point arithmetic. Many decimal numbers can't be
 represented exactly in binary floating-point format (regardless of its
 length). Applying binary arithmetic to numbers intended to be decimals
 can sometimes lead to unintuitive results, as is shown in the
 description for the FromDouble() method of this class.</p> <p><b>About
 EDecimal instances</b> </p> <p>Each instance of this class consists of
 an integer significand and an integer exponent, both
 arbitrary-precision. The value of the number equals significand *
 10^exponent.</p> <p>The significand is the value of the digits that
 make up a number, ignoring the decimal point and exponent. For
 example, in the number 2356.78, the significand is 235678. The
  exponent is where the "floating" decimal point of the number is
  located. A positive exponent means "move it to the right", and a
  negative exponent means "move it to the left." In the example 2,
 356.78, the exponent is -2, since it has 2 decimal places and the
  decimal point is "moved to the left by 2." Therefore, in the
 arbitrary-precision decimal representation, this number would be
 stored as 235678 * 10^-2.</p> <p>The significand and exponent format
 preserves trailing zeros in the number's value. This may give rise to
 multiple ways to store the same value. For example, 1.00 and 1 would
 be stored differently, even though they have the same value. In the
 first case, 100 * 10^-2 (100 with decimal point moved left by 2), and
 in the second case, 1 * 10^0 (1 with decimal point moved 0).</p>
 <p>This class also supports values for negative zero, not-a-number
 (NaN) values, and infinity. <b>Negative zero</b> is generally used
 when a negative number is rounded to 0; it has the same mathematical
 value as positive zero. <b>Infinity</b> is generally used when a
 non-zero number is divided by zero, or when a very high or very low
 number can't be represented in a given exponent range.
 <b>Not-a-number</b> is generally used to signal errors.</p> <p>This
 class implements the General Decimal Arithmetic Specification version
 1.70 except part of chapter 6(
 <code>http://speleotrove.com/decimal/decarith.html</code>).</p>
 <p><b>Errors and Exceptions</b> </p> <p>Passing a signaling NaN to any
 arithmetic operation shown here will signal the flag FlagInvalid and
 return a quiet NaN, even if another operand to that operation is a
 quiet NaN, unless the operation's documentation expressly states that
 another result happens when a signaling NaN is passed to that
 operation.</p> <p>Passing a quiet NaN to any arithmetic operation
 shown here will return a quiet NaN, unless the operation's
 documentation expressly states that another result happens when a
 quiet NaN is passed to that operation. Invalid operations will also
 return a quiet NaN, as stated in the individual methods.</p> <p>Unless
 noted otherwise, passing a null arbitrary-precision decimal argument
 to any method here will throw an exception.</p> <p>When an arithmetic
 operation signals the flag FlagInvalid, FlagOverflow, or
 FlagDivideByZero, it will not throw an exception too, unless the
 flag's trap is enabled in the arithmetic context (see EContext's Traps
 property).</p> <p>If an operation requires creating an intermediate
 value that might be too big to fit in memory (or might require more
 than 2 gigabytes of memory to store -- due to the current use of a
 32-bit integer internally as a length), the operation may signal an
 invalid-operation flag and return not-a-number (NaN). In certain rare
 cases, the compareTo method may throw OutOfMemoryError (called
 OutOfMemoryError in Java) in the same circumstances.</p>
 <p><b>Serialization</b> </p> <p>An arbitrary-precision decimal value
 can be serialized (converted to a stable format) in one of the
 following ways:</p> <ul><li>By calling the toString() method, which
 will always return distinct strings for distinct arbitrary-precision
 decimal values.</li> <li>By calling the UnsignedMantissa, Exponent,
 and IsNegative properties, and calling the IsInfinity, IsQuietNaN, and
 IsSignalingNaN methods. The return values combined will uniquely
 identify a particular arbitrary-precision decimal value.</li> </ul>
 <p><b>Thread safety</b> </p> <p>Instances of this class are immutable,
 so they are inherently safe for use by multiple threads. Multiple
 instances of this object with the same properties are interchangeable,
  so they should not be compared using the "==" operator (which might
 only check if each side of the operator is the same instance).</p>
 <p><b>Comparison considerations</b> </p> <p>This class's natural
 ordering (under the compareTo method) is not consistent with the
 Equals method. This means that two values that compare as equal under
 the compareTo method might not be equal under the Equals method. The
 compareTo method compares the mathematical values of the two instances
 passed to it (and considers two different NaN values as equal), while
 two instances with the same mathematical value, but different
 exponents, will be considered unequal under the Equals method.</p>
 <p><b>Security note</b> </p> <p>It is not recommended to implement
 security-sensitive algorithms using the methods in this class, for
 several reasons:</p> <ul><li><code>EDecimal</code> objects are immutable, so
 they can't be modified, and the memory they occupy is not guaranteed
 to be cleared in a timely fashion due to garbage collection. This is
 relevant for applications that use many-digit-long numbers as secret
 parameters.</li> <li>The methods in this class (especially those that
  involve arithmetic) are not guaranteed to be "constant-time"
 (non-data-dependent) for all relevant inputs. Certain attacks that
 involve encrypted communications have exploited the timing and other
 aspects of such communications to derive keying material or cleartext
 indirectly.</li> </ul> <p>Applications should instead use dedicated
 security libraries to handle big numbers in security-sensitive
 algorithms.</p> <p><b>Reproducibility note</b> </p> <p>Some
 applications, such as simulations, care about results that are
 reproducible, bit for bit, across computers and across runs of the
  application. Bruce Dawson, in "Floating-Point Determinism" (
 <code>https://randomascii.wordpress.com/</code>
 <code>2013/07/16/floating-point-determinism/</code>), identified many
 reproducibility issues with floating-point numbers, and here is how
 they relate to the EDecimal and EFloat classes of this library:</p>
 <ul><li>Runtime floating-point settings: All the settings that change
 how EDecimal and EFloat behave are given as parameters to the
 appropriate methods, especially via EContext objects, which specify
 the precision, rounding, and exponent range of numbers, among other
  things. The EDecimal and EFloat classes avoid the use of "native"
 floating-point data types (except for methods that convert to or from
 <code>float</code> , <code>double</code> , or <code>System.Decimal</code>). Such
  "native" types are often subject to runtime settings that change how
 floating-point math behaves with them, and these settings are often
 not accessible to .NET or Java code.</li> <li>Non-associativity and
 intermediate precisions: In general, EDecimal and EFloat use
  "unlimited" precision in their calculations unless specified otherwise
 by an EContext object. However, by limiting the precision of EDecimal,
 EFloat, and other floating-point numbers in this way, operations such
 as addition and multiplication on three or more numbers can be
 <i>non-associative</i> , meaning the result can change depending on
 the order in which those numbers are added or multiplied. This
 property means that if an algorithm does not ensure such numbers are
 added or multiplied in the same order every time, its results may not
 be reproducible across computers or across runs of the application.
 This non-associativity problem can happen, for example, if an
 application splits a calculation across several threads and combines
 their results in the end. The problems with an unspecified order of
 operations (in the same line of code) and intermediate precisions
 (problems present in C and C++, for example) don't exist with method
 calls to EDecimal and EFloat methods, especially since they require
 limited-precision support to be declared explicitly via EContext.</li>
 <li>fmadd instruction: EDecimal and EFloat include a MultiplyAndAdd
 method with the same semantics as in the General Decimal Arithmetic
 Specification, which requires delivering correctly rounded results for
 this method.</li> <li>Square root estimate: Not applicable since
 EDecimal and EFloat don't include any estimates to square root.</li>
 <li>Transcendental functions: This includes logarithms, exponentials,
 and the Pi method. For these functions, results are not guaranteed to
 always be correctly rounded. When using transcendentals, an
 application that cares about reproducibility should choose one version
 of this library and stick to it; this at least has the advantage that
 the implementation will be the same across computers, unlike with
  "native" floating-point types where the choice of implementation is
 often not within the application's control.</li> <li>Conversions:
 Conversions between EDecimal or EFloat and text strings have the same
 implementation across computers for the same version of this library
 (see also the advice for transcendentals above). But as for the
 ToDouble, ToSingle, FromDouble, and FromSingle methods, note that some
 implementations of Java and.NET may or may not support preserving the
 value of subnormal numbers (numbers other than zero with the lowest
 possible exponent) or the payloads held in a not-a-number (NaN) value
 of float or double; thus these methods should not be considered
 reproducible across computers.</li> <li>Compiler differences: Not
  applicable where these classes don't use "native" floating-point
 types.</li> <li>Uninitialized data; per-processor code: Not
 applicable.</li> </ul> <p><b>Forms of numbers</b> </p> <p>There are
 several other types of numbers that are mentioned in this class and
 elsewhere in this documentation. For reference, they are specified
 here.</p> <p><b>Unsigned integer</b> : An integer that's always 0 or
 greater, with the following maximum values:</p> <ul><li>8-bit unsigned
 integer, or <i>byte</i> : 255.</li> <li>16-bit unsigned integer:
 65535.</li> <li>32-bit unsigned integer: (2 <sup>32</sup> -1).</li>
 <li>64-bit unsigned integer: (2 <sup>64</sup> -1).</li> </ul>
 <p><b>Signed integer</b> : An integer in <i>two's-complement form</i>
 , with the following ranges:</p> <ul><li>8-bit signed integer: -128 to
 127.</li> <li>16-bit signed integer: -32768 to 32767.</li> <li>32-bit
 signed integer: -2 <sup>31</sup> to (2 <sup>31</sup> - 1).</li>
 <li>64-bit signed integer: -2 <sup>63</sup> to (2 <sup>63</sup> -
 1).</li> </ul> <p><b>Two's complement form</b> : In
 <i>two's-complement form</i> , nonnegative numbers have the highest
 (most significant) bit set to zero, and negative numbers have that bit
 (and all bits beyond) set to one, and a negative number is stored in
 such form by decreasing its absolute value by 1 and swapping the bits
 of the resulting number.</p> <p><b>64-bit floating-point number</b> :
 A 64-bit binary floating-point number, in the form <i>significand</i>
 * 2 <sup><i>exponent</i> </sup> . The significand is 53 bits long
 (Precision) and the exponent ranges from -1074 (EMin) to 971 (EMax).
 The number is stored in the following format (commonly called the IEEE
 754 format):</p> <pre>|C|BBB...BBB|AAAAAA...AAAAAA|</pre> <ul><li>A.
 Low 52 bits (Precision minus 1 bits): Lowest bits of the
 significand.</li> <li>B. Next 11 bits: Exponent area: <ul><li>If all
 bits are ones, the final stored value is infinity (positive or
 negative depending on the C bit) if all bits in area A are zeros, or
 not-a-number (NaN) otherwise.</li> <li>If all bits are zeros, the
 final stored value is a subnormal number, the exponent is EMin, and
 the highest bit of the significand is zero.</li> <li>If any other
 number, the exponent is this value reduced by 1, then raised by EMin,
 and the highest bit of the significand is one.</li> </ul> </li> <li>C.
 Highest bit: If one, this is a negative number.</li> </ul> <p>The
 elements described above are in the same order as the order of each
 bit of each element, that is, either most significant first or least
 significant first.</p> <p><b>32-bit binary floating-point number</b> :
 A 32-bit binary number which is stored similarly to a <i>64-bit
 floating-point number</i> , except that:</p> <ul><li>Precision is 24
 bits.</li> <li>EMin is -149.</li> <li>EMax is 104.</li> <li>A. The low
 23 bits (Precision minus 1 bits) are the lowest bits of the
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
 highest bit is one, it's a negative number.</li> </ul> <p>The elements
 described above are in the same order as the order of each bit of each
 element, that is, either most significant first or least significant
 first.</p>

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
 Finds the absolute value of this object (if it's negative, it becomes
 positive).
* `EDecimal Abs​(EContext context)`<br>
 Finds the absolute value of this object (if it's negative, it becomes
 positive).
* `EDecimal Add​(int intValue)`<br>
 Adds this arbitrary-precision decimal floating-point number and a 32-bit
 signed integer and returns the result.
* `EDecimal Add​(long longValue)`<br>
 Adds this arbitrary-precision decimal floating-point number and a 64-bit
 signed integer and returns the result.
* `EDecimal Add​(EDecimal otherValue)`<br>
 Adds this arbitrary-precision decimal floating-point number and another
 arbitrary-precision decimal floating-point number and returns the
 result.
* `EDecimal Add​(EDecimal otherValue,
   EContext ctx)`<br>
 Adds this arbitrary-precision decimal floating-point number and another
 arbitrary-precision decimal floating-point number and returns the
 result.
* `int compareTo​(int intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int compareTo​(long intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int compareTo​(EDecimal other)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int CompareToBinary​(EFloat other)`<br>
 Compares an arbitrary-precision binary floating-point number with this
 instance.
* `EDecimal CompareToSignal​(EDecimal other,
               EContext ctx)`<br>
 Compares the mathematical values of this object and another object, treating
 quiet NaN as signaling.
* `int CompareToTotal​(EDecimal other)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values.
* `int CompareToTotal​(EDecimal other,
              EContext ctx)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values.
* `int CompareToTotalMagnitude​(EDecimal other)`<br>
 Compares the absolute values of this object and another object, imposing a
 total ordering on all possible values (ignoring their signs).
* `int CompareToTotalMagnitude​(EDecimal other,
                       EContext ctx)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values (ignoring their signs).
* `int CompareToValue​(int intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int CompareToValue​(long intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int CompareToValue​(EDecimal other)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `EDecimal CompareToWithContext​(EDecimal other,
                    EContext ctx)`<br>
 Compares the mathematical values of this object and another object.
* `EDecimal Copy()`<br>
 Creates a copy of this arbitrary-precision binary number.
* `EDecimal CopySign​(EDecimal other)`<br>
 Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number.
* `static EDecimal Create​(int mantissaSmall,
      int exponentSmall) exponent*10^significand`<br>
 Returns a number with the value exponent*10^significand.
* `static EDecimal Create​(long mantissaLong,
      int exponentSmall) exponent*10^significand`<br>
 Creates a number with the value exponent*10^significand.
* `static EDecimal Create​(long mantissaLong,
      long exponentLong) exponent*10^significand`<br>
 Creates a number with the value exponent*10^significand.
* `static EDecimal Create​(EInteger mantissa,
      int exponentSmall) exponent*10^significand`<br>
 Creates a number with the value exponent*10^significand.
* `static EDecimal Create​(EInteger mantissa,
      long exponentLong) exponent*10^significand`<br>
 Creates a number with the value exponent*10^significand.
* `static EDecimal Create​(EInteger mantissa,
      EInteger exponent) exponent*10^significand`<br>
 Creates a number with the value exponent*10^significand.
* `static EDecimal CreateNaN​(EInteger diag)`<br>
 Creates a not-a-number arbitrary-precision decimal number.
* `static EDecimal CreateNaN​(EInteger diag,
         boolean signaling,
         boolean negative,
         EContext ctx)`<br>
 Creates a not-a-number arbitrary-precision decimal number.
* `EDecimal Decrement()`<br>
 Returns one subtracted from this arbitrary-precision decimal number.
* `EDecimal Divide​(int intValue)`<br>
 Divides this arbitrary-precision decimal floating-point number by a 32-bit
 signed integer and returns the result; returns NaN instead if the
 result would have a nonterminating decimal expansion (including 1/3,
 1/12, 1/7, 2/3, and so on); if this is not desired, use
 DivideToExponent, or use the Divide overload that takes an EContext.
* `EDecimal Divide​(long longValue)`<br>
 Divides this arbitrary-precision decimal floating-point number by a 64-bit
 signed integer and returns the result; returns NaN instead if the
 result would have a nonterminating decimal expansion (including 1/3,
 1/12, 1/7, 2/3, and so on); if this is not desired, use
 DivideToExponent, or use the Divide overload that takes an EContext.
* `EDecimal Divide​(EDecimal divisor)`<br>
 Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result; returns NaN instead if the result would have a
 nonterminating decimal expansion (including 1/3, 1/12, 1/7, 2/3, and
 so on); if this is not desired, use DivideToExponent, or use the
 Divide overload that takes an EContext.
* `EDecimal Divide​(EDecimal divisor,
      EContext ctx)`<br>
 Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result.
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
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent​(EDecimal divisor,
                int desiredExponentInt,
                EContext ctx)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent​(EDecimal divisor,
                int desiredExponentInt,
                ERounding rounding)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent​(EDecimal divisor,
                long desiredExponentSmall)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 64-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent​(EDecimal divisor,
                long desiredExponentSmall,
                EContext ctx)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToExponent​(EDecimal divisor,
                long desiredExponentSmall,
                ERounding rounding)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToExponent​(EDecimal divisor,
                EInteger exponent)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result, using the half-even rounding mode.
* `EDecimal DivideToExponent​(EDecimal divisor,
                EInteger exponent,
                EContext ctx)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToExponent​(EDecimal divisor,
                EInteger desiredExponent,
                ERounding rounding)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToIntegerNaturalScale​(EDecimal divisor)`<br>
 Divides two arbitrary-precision decimal numbers, and returns the integer
 part of the result, rounded down, with the preferred exponent set to
 this value's exponent minus the divisor's exponent.
* `EDecimal DivideToIntegerNaturalScale​(EDecimal divisor,
                           EContext ctx)`<br>
 Divides this object by another object, and returns the integer part of the
 result (which is initially rounded down), with the preferred
 exponent set to this value's exponent minus the divisor's exponent.
* `EDecimal DivideToIntegerZeroScale​(EDecimal divisor,
                        EContext ctx)`<br>
 Divides this object by another object, and returns the integer part of the
 result, with the exponent set to 0.
* `EDecimal DivideToSameExponent​(EDecimal divisor,
                    ERounding rounding)`<br>
 Divides this object by another decimal number and returns a result with the
 same exponent as this object (the dividend).
* `EDecimal[] DivRemNaturalScale​(EDecimal divisor)`<br>
 Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns a
 two-item array containing the result of the division and the
 remainder, in that order.
* `EDecimal[] DivRemNaturalScale​(EDecimal divisor,
                  EContext ctx)`<br>
 Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns a
 two-item array containing the result of the division and the
 remainder, in that order.
* `boolean equals​(EDecimal other)`<br>
 Determines whether this object's significand, exponent, and properties are
 equal to those of another object.
* `boolean equals​(java.lang.Object obj)`<br>
 Determines whether this object's significand, exponent, and properties are
 equal to those of another object and that other object is an
 arbitrary-precision decimal number.
* `EDecimal Exp​(EContext ctx)`<br>
 Finds e (the base of natural logarithms) raised to the power of this
 object's value.
* `EDecimal ExpM1​(EContext ctx)`<br>
 Finds e (the base of natural logarithms) raised to the power of this
 object's value, and subtracts the result by 1 and returns the final
 result, in a way that avoids loss of precision if the true result is
 very close to 0.
* `static EDecimal FromBoolean​(boolean boolValue)`<br>
 Converts a boolean value (true or false) to an arbitrary-precision decimal
 number.
* `static EDecimal FromByte​(byte inputByte)`<br>
 Converts a byte (from 0 to 255) to an arbitrary-precision decimal number.
* `static EDecimal FromDouble​(double dbl)`<br>
 Creates an arbitrary-precision decimal number from a 64-bit binary
 floating-point number.
* `static EDecimal FromDoubleBits​(long dblBits)`<br>
 Creates an arbitrary-precision decimal number from a 64-bit binary
 floating-point number, encoded in the IEEE 754 binary64 format.
* `static EDecimal FromEFloat​(EFloat bigfloat)`<br>
 Creates an arbitrary-precision decimal number from an arbitrary-precision
 binary floating-point number.
* `static EDecimal FromEInteger​(EInteger bigint)`<br>
 Converts an arbitrary-precision integer to an arbitrary precision decimal.
* `static EDecimal FromExtendedFloat​(EFloat ef)`<br>
 Deprecated.
Renamed to FromEFloat.
 Renamed to FromEFloat.
* `static EDecimal FromInt16​(short inputInt16)`<br>
 Converts a 16-bit signed integer to an arbitrary-precision decimal number.
* `static EDecimal FromInt32​(int valueSmaller)`<br>
 Creates an arbitrary-precision decimal number from a 32-bit signed integer.
* `static EDecimal FromInt64​(long valueSmall)`<br>
 Creates an arbitrary-precision decimal number from a 64-bit signed integer.
* `static EDecimal FromSingle​(float flt)`<br>
 Creates an arbitrary-precision decimal number from a 32-bit binary
 floating-point number.
* `static EDecimal FromSingleBits​(int value)`<br>
 Creates an arbitrary-precision decimal number from a 32-bit binary
 floating-point number encoded in the IEEE 754 binary32 format.
* `static EDecimal FromString​(byte[] bytes)`<br>
 Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number.
* `static EDecimal FromString​(byte[] bytes,
          int offset,
          int length)`<br>
 Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number.
* `static EDecimal FromString​(byte[] bytes,
          int offset,
          int length,
          EContext ctx)`<br>
 Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number.
* `static EDecimal FromString​(byte[] bytes,
          EContext ctx)`<br>
 Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number.
* `static EDecimal FromString​(char[] chars) char`<br>
 Creates an arbitrary-precision decimal number from a sequence of char
 s that represents a number.
* `static EDecimal FromString​(char[] chars,
          int offset,
          int length) char`<br>
 Creates an arbitrary-precision decimal number from a sequence of char
 s that represents a number.
* `static EDecimal FromString​(char[] chars,
          int offset,
          int length,
          EContext ctx) char`<br>
 Creates an arbitrary-precision decimal number from a sequence of
 char s that represents a number.
* `static EDecimal FromString​(char[] chars,
          EContext ctx) char`<br>
 Creates an arbitrary-precision decimal number from a sequence of char
 s that represents a number.
* `static EDecimal FromString​(java.lang.String str)`<br>
 Creates an arbitrary-precision decimal number from a text string that
 represents a number.
* `static EDecimal FromString​(java.lang.String str,
          int offset,
          int length)`<br>
 Creates an arbitrary-precision decimal number from a text string that
 represents a number.
* `static EDecimal FromString​(java.lang.String str,
          int offset,
          int length,
          EContext ctx)`<br>
 Creates an arbitrary-precision decimal number from a text string that
 represents a number.
* `static EDecimal FromString​(java.lang.String str,
          EContext ctx)`<br>
 Creates an arbitrary-precision decimal number from a text string that
 represents a number.
* `EInteger getExponent()`<br>
 Gets this object's exponent.
* `EInteger getMantissa()`<br>
 Gets this object's unscaled value, or significand, and makes it negative if
 this object is negative.
* `EInteger getUnsignedMantissa()`<br>
 Gets the absolute value of this object's unscaled value, or significand.
* `int hashCode()`<br>
 Calculates this object's hash code.
* `EDecimal Increment()`<br>
 Returns one added to this arbitrary-precision decimal number.
* `boolean isFinite()`<br>
 Gets a value indicating whether this object is finite (not infinity or NaN).
* `boolean IsInfinity()`<br>
 Gets a value indicating whether this object is positive or negative
 infinity.
* `boolean IsInteger()`<br>
 Returns whether this object's value is an integer.
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
* `EDecimal Log​(EContext ctx)`<br>
 Finds the natural logarithm of this object, that is, the power (exponent)
 that e (the base of natural logarithms) must be raised to in order
 to equal this object's value.
* `EDecimal Log10​(EContext ctx)`<br>
 Finds the base-10 logarithm of this object, that is, the power (exponent)
 that the number 10 must be raised to in order to equal this object's
 value.
* `EDecimal Log1P​(EContext ctx)`<br>
 Adds 1 to this object's value and finds the natural logarithm of the result,
 in a way that avoids loss of precision when this object's value is
 between 0 and 1.
* `EDecimal LogN​(EDecimal baseValue,
    EContext ctx)`<br>
 Finds the base-N logarithm of this object, that is, the power (exponent)
 that the number N must be raised to in order to equal this object's
 value.
* `static EDecimal Max​(EDecimal first,
   EDecimal second)`<br>
 Gets the greater value between two decimal numbers.
* `static EDecimal Max​(EDecimal first,
   EDecimal second,
   EContext ctx)`<br>
 Gets the greater value between two decimal numbers.
* `static EDecimal MaxMagnitude​(EDecimal first,
            EDecimal second)`<br>
 Gets the greater value between two values, ignoring their signs.
* `static EDecimal MaxMagnitude​(EDecimal first,
            EDecimal second,
            EContext ctx)`<br>
 Gets the greater value between two values, ignoring their signs.
* `static EDecimal Min​(EDecimal first,
   EDecimal second)`<br>
 Gets the lesser value between two decimal numbers.
* `static EDecimal Min​(EDecimal first,
   EDecimal second,
   EContext ctx)`<br>
 Gets the lesser value between two decimal numbers.
* `static EDecimal MinMagnitude​(EDecimal first,
            EDecimal second)`<br>
 Gets the lesser value between two values, ignoring their signs.
* `static EDecimal MinMagnitude​(EDecimal first,
            EDecimal second,
            EContext ctx)`<br>
 Gets the lesser value between two values, ignoring their signs.
* `EDecimal MovePointLeft​(int places)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointLeft​(int places,
             EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointLeft​(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointLeft​(EInteger bigPlaces,
             EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointRight​(int places)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal MovePointRight​(int places,
              EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal MovePointRight​(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal MovePointRight​(EInteger bigPlaces,
              EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal Multiply​(int intValue)`<br>
 Multiplies this arbitrary-precision decimal floating-point number by a
 32-bit signed integer and returns the result.
* `EDecimal Multiply​(long longValue)`<br>
 Multiplies this arbitrary-precision decimal floating-point number by a
 64-bit signed integer and returns the result.
* `EDecimal Multiply​(EDecimal otherValue)`<br>
 Multiplies this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result.
* `EDecimal Multiply​(EDecimal op,
        EContext ctx)`<br>
 Multiplies this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result.
* `EDecimal MultiplyAndAdd​(EDecimal multiplicand,
              EDecimal augend)`<br>
 Multiplies by one decimal number, and then adds another decimal number.
* `EDecimal MultiplyAndAdd​(EDecimal op,
              EDecimal augend,
              EContext ctx)`<br>
 Multiplies by one value, and then adds another value.
* `EDecimal MultiplyAndSubtract​(EDecimal op,
                   EDecimal subtrahend,
                   EContext ctx)`<br>
 Multiplies by one value, and then subtracts another value.
* `EDecimal Negate()`<br>
 Gets an object with the same value as this one, but with the sign reversed.
* `EDecimal Negate​(EContext context)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but with the sign reversed.
* `EDecimal NextMinus​(EContext ctx)`<br>
 Finds the largest value that's smaller than the given value.
* `EDecimal NextPlus​(EContext ctx)`<br>
 Finds the smallest value that's greater than the given value.
* `EDecimal NextToward​(EDecimal otherValue,
          EContext ctx)`<br>
 Finds the next value that is closer to the other object's value than this
 object's value.
* `static EDecimal PI​(EContext ctx)`<br>
 Finds the constant π, the circumference of a circle divided by its diameter.
* `EDecimal Plus​(EContext ctx)`<br>
 Rounds this object's value to a given precision, using the given rounding
 mode and range of exponent, and also converts negative zero to
 positive zero.
* `EDecimal Pow​(int exponentSmall)`<br>
 Raises this object's value to the given exponent.
* `EDecimal Pow​(int exponentSmall,
   EContext ctx)`<br>
 Raises this object's value to the given exponent.
* `EDecimal Pow​(EDecimal exponent)`<br>
 Raises this object's value to the given exponent, using unlimited precision.
* `EDecimal Pow​(EDecimal exponent,
   EContext ctx)`<br>
 Raises this object's value to the given exponent.
* `EInteger Precision()`<br>
 Finds the number of digits in this number's significand.
* `EDecimal PreRound​(EContext ctx)`<br>
 Returns a number in which the value of this object is rounded to fit the
 maximum precision allowed if it has more significant digits than the
 maximum precision.
* `EDecimal Quantize​(int desiredExponentInt,
        EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value but a new
 exponent.
* `EDecimal Quantize​(int desiredExponentInt,
        ERounding rounding)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 one but a new exponent.
* `EDecimal Quantize​(EDecimal otherValue,
        EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but with the same exponent as another decimal number.
* `EDecimal Quantize​(EInteger desiredExponent,
        EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value but a new
 exponent.
* `EDecimal Reduce​(EContext ctx)`<br>
 Returns an object with the same numerical value as this one but with
 trailing zeros removed from its significand.
* `EDecimal Remainder​(EDecimal divisor,
         EContext ctx)`<br>
 Returns the remainder that would result when this arbitrary-precision
 decimal floating-point number is divided by another
 arbitrary-precision decimal floating-point number.
* `EDecimal RemainderNaturalScale​(EDecimal divisor) "this" - (("this" /
  "divisor") * "divisor")`<br>
 Calculates the remainder of a number by the formula "this" - (("this" /
  "divisor") * "divisor").
* `EDecimal RemainderNaturalScale​(EDecimal divisor,
                     EContext ctx)`<br>
 Calculates the remainder of a number by the formula "this" - (("this" /
  "divisor") * "divisor").
* `EDecimal RemainderNear​(EDecimal divisor,
             EContext ctx)`<br>
 Finds the distance to the closest multiple of the given divisor, based on
 the result of dividing this object's value by another object's
 value.
* `EDecimal RemainderNoRoundAfterDivide​(EDecimal divisor,
                           EContext ctx)`<br>
 Finds the remainder that results when dividing two arbitrary-precision
 decimal numbers, except the intermediate division is not adjusted to
 fit the precision of the given arithmetic context.
* `EDecimal RoundToExponent​(int exponentSmall)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the
 HalfEven rounding mode.
* `EDecimal RoundToExponent​(int exponentSmall,
               EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary.
* `EDecimal RoundToExponent​(int exponentSmall,
               ERounding rounding)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary.
* `EDecimal RoundToExponent​(EInteger exponent)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the
 HalfEven rounding mode.
* `EDecimal RoundToExponent​(EInteger exponent,
               EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary.
* `EDecimal RoundToExponent​(EInteger exponent,
               ERounding rounding)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the given
 rounding mode.
* `EDecimal RoundToExponentExact​(int exponentSmall,
                    EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as a 32-bit
 signed integer, and signals an inexact flag if the result would be
 inexact.
* `EDecimal RoundToExponentExact​(int exponentSmall,
                    ERounding rounding)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as a 32-bit
 signed integer, and signals an inexact flag if the result would be
 inexact.
* `EDecimal RoundToExponentExact​(EInteger exponent,
                    EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as an
 arbitrary-precision integer, and signals an inexact flag if the
 result would be inexact.
* `EDecimal RoundToIntegerExact​(EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to an integer, and signals an inexact flag if the
 result would be inexact.
* `EDecimal RoundToIntegerNoRoundedFlag​(EContext ctx) FlagInexact FlagRounded`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to an integer, without adding the
 FlagInexact or FlagRounded flags.
* `EDecimal RoundToIntegralExact​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerExact.
 Renamed to RoundToIntegerExact.
* `EDecimal RoundToIntegralNoRoundedFlag​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerNoRoundedFlag.
 Renamed to RoundToIntegerNoRoundedFlag.
* `EDecimal RoundToPrecision​(EContext ctx)`<br>
 Rounds this object's value to a given precision, using the given rounding
 mode and range of exponent.
* `EDecimal ScaleByPowerOfTen​(int places)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `EDecimal ScaleByPowerOfTen​(int places,
                 EContext ctx)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `EDecimal ScaleByPowerOfTen​(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `EDecimal ScaleByPowerOfTen​(EInteger bigPlaces,
                 EContext ctx)`<br>
 Returns a number similar to this number but with its scale adjusted.
* `int signum()`<br>
 Gets this value's sign: -1 if negative; 1 if positive; 0 if zero.
* `EDecimal Sqrt​(EContext ctx)`<br>
 Finds the square root of this object's value.
* `EDecimal SquareRoot​(EContext ctx)`<br>
 Deprecated.
Renamed to Sqrt.
 Renamed to Sqrt.
* `EDecimal Subtract​(int intValue)`<br>
 Subtracts a 32-bit signed integer from this arbitrary-precision decimal
 floating-point number and returns the result.
* `EDecimal Subtract​(long longValue)`<br>
 Subtracts a 64-bit signed integer from this arbitrary-precision decimal
 floating-point number and returns the result.
* `EDecimal Subtract​(EDecimal otherValue)`<br>
 Subtracts an arbitrary-precision decimal floating-point number from this
 arbitrary-precision decimal floating-point number and returns the
 result.
* `EDecimal Subtract​(EDecimal otherValue,
        EContext ctx)`<br>
 Subtracts an arbitrary-precision decimal floating-point number from this
 arbitrary-precision decimal floating-point number and returns the
 result.
* `byte ToByteChecked()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) after converting it to an integer by discarding
 its fractional part.
* `byte ToByteIfExact()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) without rounding to a different numerical
 value.
* `byte ToByteUnchecked()`<br>
 Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a byte (from 0 to 255).
* `double ToDouble()`<br>
 Converts this value to its closest equivalent as a 64-bit floating-point
 number, using the half-even rounding mode.
* `long ToDoubleBits()`<br>
 Converts this value to its closest equivalent as a 64-bit floating-point
 number encoded in the IEEE 754 binary64 format, using the half-even
 rounding mode.
* `EFloat ToEFloat()`<br>
 Creates a binary floating-point number from this object's value.
* `EFloat ToEFloat​(EContext ec)`<br>
 Creates a binary floating-point number from this object's value.
* `EInteger ToEInteger()`<br>
 Converts this value to an arbitrary-precision integer, discarding the
 fractional part in this value.
* `EInteger ToEIntegerExact()`<br>
 Deprecated.
Renamed to ToEIntegerIfExact.
 Renamed to ToEIntegerIfExact.
* `EInteger ToEIntegerIfExact()`<br>
 Converts this value to an arbitrary-precision integer, checking whether the
 fractional part of the value would be lost.
* `java.lang.String ToEngineeringString()`<br>
 Same as toString(), except that when an exponent is used it will be a
 multiple of 3.
* `EFloat ToExtendedFloat()`<br>
 Deprecated.
Renamed to ToEFloat.
 Renamed to ToEFloat.
* `short ToInt16Checked()`<br>
 Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer after converting it to an integer by
 discarding its fractional part.
* `short ToInt16IfExact()`<br>
 Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer without rounding to a different numerical
 value.
* `short ToInt16Unchecked()`<br>
 Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 16-bit signed integer.
* `int ToInt32Checked()`<br>
 Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer after converting it to an integer by
 discarding its fractional part.
* `int ToInt32IfExact()`<br>
 Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer without rounding to a different numerical
 value.
* `int ToInt32Unchecked()`<br>
 Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 32-bit signed integer.
* `long ToInt64Checked()`<br>
 Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer after converting it to an integer by
 discarding its fractional part.
* `long ToInt64IfExact()`<br>
 Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer without rounding to a different numerical
 value.
* `long ToInt64Unchecked()`<br>
 Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 64-bit signed integer.
* `java.lang.String ToPlainString()`<br>
 Converts this value to a string as though with the toString method, but
 without using exponential notation.
* `float ToSingle()`<br>
 Converts this value to its closest equivalent as a 32-bit floating-point
 number, using the half-even rounding mode.
* `int ToSingleBits()`<br>
 Converts this value to its closest equivalent as a 32-bit floating-point
 number encoded in the IEEE 754 binary32 format, using the half-even
 rounding mode.
* `EInteger ToSizedEInteger​(int maxBitLength)`<br>
 Converts this value to an arbitrary-precision integer by discarding its
 fractional part and checking whether the resulting integer overflows
 the given signed bit count.
* `EInteger ToSizedEIntegerIfExact​(int maxBitLength)`<br>
 Converts this value to an arbitrary-precision integer, only if this number's
 value is an exact integer and that integer does not overflow the
 given signed bit count.
* `java.lang.String toString()`<br>
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

### Copy
    public EDecimal Copy()
Creates a copy of this arbitrary-precision binary number.

**Returns:**

* An arbitrary-precision decimal floating-point number.

### getExponent
    public final EInteger getExponent()
Gets this object's exponent. This object's value will be an integer if the
 exponent is positive or zero.

**Returns:**

* This object's exponent. This object's value will be an integer if
 the exponent is positive or zero.

### isFinite
    public final boolean isFinite()
Gets a value indicating whether this object is finite (not infinity or NaN).

**Returns:**

* <code>true</code> if this object is finite (not infinity or NaN);
 otherwise, <code>false</code>.

### isNegative
    public final boolean isNegative()
Gets a value indicating whether this object is negative, including negative
 zero.

**Returns:**

* <code>true</code> if this object is negative, including negative zero;
 otherwise, <code>false</code>.

### isZero
    public final boolean isZero()
Gets a value indicating whether this object's value equals 0.

**Returns:**

* <code>true</code> if this object's value equals 0; otherwise, <code>
 false</code>. <code>true</code> if this object's value equals 0; otherwise,
 <code>false</code>.

### IsInteger
    public boolean IsInteger()
Returns whether this object's value is an integer.

**Returns:**

* <code>true</code> if this object's value is an integer; otherwise, <code>
 false</code>.

### getMantissa
    public final EInteger getMantissa()
Gets this object's unscaled value, or significand, and makes it negative if
 this object is negative. If this value is not-a-number (NaN), that
  value's absolute value is the NaN's "payload" (diagnostic
 information).

**Returns:**

* This object's unscaled value. Will be negative if this object's
 value is negative (including a negative NaN).

### signum
    public final int signum()
Gets this value's sign: -1 if negative; 1 if positive; 0 if zero.

**Returns:**

* This value's sign: -1 if negative; 1 if positive; 0 if zero.

### getUnsignedMantissa
    public final EInteger getUnsignedMantissa()
Gets the absolute value of this object's unscaled value, or significand. If
  this value is not-a-number (NaN), that value is the NaN's "payload"
 (diagnostic information).

**Returns:**

* The absolute value of this object's unscaled value.

### Create
    public static EDecimal Create​(int mantissaSmall, int exponentSmall)
Returns a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissaSmall</code> - Desired value for the significand.

* <code>exponentSmall</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

### Create
    public static EDecimal Create​(EInteger mantissa, int exponentSmall)
Creates a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissa</code> - Desired value for the significand.

* <code>exponentSmall</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>mantissa</code> is null.

### Create
    public static EDecimal Create​(EInteger mantissa, long exponentLong)
Creates a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissa</code> - Desired value for the significand.

* <code>exponentLong</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>mantissa</code> is null.

### Create
    public static EDecimal Create​(EInteger mantissa, EInteger exponent)
Creates a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissa</code> - Desired value for the significand.

* <code>exponent</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>mantissa</code> or <code>
 exponent</code> is null.

### Create
    public static EDecimal Create​(long mantissaLong, int exponentSmall)
Creates a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissaLong</code> - Desired value for the significand.

* <code>exponentSmall</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

### Create
    public static EDecimal Create​(long mantissaLong, long exponentLong)
Creates a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissaLong</code> - Desired value for the significand.

* <code>exponentLong</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

### CreateNaN
    public static EDecimal CreateNaN​(EInteger diag)
Creates a not-a-number arbitrary-precision decimal number.

**Parameters:**

* <code>diag</code> - An integer, 0 or greater, to use as diagnostic information
 associated with this object. If none is needed, should be zero. To
 get the diagnostic information from another arbitrary-precision
 decimal floating-point number, use that object's <code>
 UnsignedMantissa</code> property.

**Returns:**

* A quiet not-a-number.

### CreateNaN
    public static EDecimal CreateNaN​(EInteger diag, boolean signaling, boolean negative, EContext ctx)
Creates a not-a-number arbitrary-precision decimal number.

**Parameters:**

* <code>diag</code> - An integer, 0 or greater, to use as diagnostic information
 associated with this object. If none is needed, should be zero. To
 get the diagnostic information from another arbitrary-precision
 decimal floating-point number, use that object's <code>
 UnsignedMantissa</code> property.

* <code>signaling</code> - Whether the return value will be signaling (true) or quiet
 (false).

* <code>negative</code> - Whether the return value is negative.

* <code>ctx</code> - An arithmetic context to control the precision (in decimal
 digits) of the diagnostic information. The rounding and exponent
 range of this context will be ignored. Can be null. The only flag
 that can be signaled in this context is FlagInvalid, which happens
 if diagnostic information needs to be truncated and too much memory
 is required to do so.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>diag</code> is null or is less
 than 0.

### FromDouble
    public static EDecimal FromDouble​(double dbl)
Creates an arbitrary-precision decimal number from a 64-bit binary
 floating-point number. This method computes the exact value of the
 floating point number, not an approximation, as is often the case by
 converting the floating point number to a string first. Remember,
 though, that the exact value of a 64-bit binary floating-point
 number is not always the value that results when passing a literal
 decimal number (for example, calling <code>EDecimal.FromDouble(0.1)</code>
), since not all decimal numbers can be converted to exact binary
 numbers (in the example given, the resulting arbitrary-precision
  decimal will be the value of the closest "double" to 0.1, not 0.1
 exactly). To create an arbitrary-precision decimal number from a
 decimal value, use FromString instead in most cases (for example:
  <code>EDecimal.FromString("0.1")</code>).

**Parameters:**

* <code>dbl</code> - The parameter <code>dbl</code> is a 64-bit floating-point number.

**Returns:**

* An arbitrary-precision decimal number with the same value as <code>
 dbl</code>.

### FromDoubleBits
    public static EDecimal FromDoubleBits​(long dblBits)
Creates an arbitrary-precision decimal number from a 64-bit binary
 floating-point number, encoded in the IEEE 754 binary64 format. This
 method computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the floating point
 number to a string first. Remember, though, that the exact value of
 a 64-bit binary floating-point number is not always the value that
 results when passing a literal decimal number, since not all decimal
 numbers can be converted to exact binary numbers (in the example
 given, the resulting arbitrary-precision decimal will be the value
  of the closest "double" to 0.1, not 0.1 exactly). To create an
 arbitrary-precision decimal number from a decimal value, use
 FromString instead in most cases.

**Parameters:**

* <code>dblBits</code> - The parameter <code>dblBits</code> is a 64-bit signed integer.

**Returns:**

* An arbitrary-precision decimal number with the same value as <code>
 dblBits</code>.

### FromEInteger
    public static EDecimal FromEInteger​(EInteger bigint)
Converts an arbitrary-precision integer to an arbitrary precision decimal.

**Parameters:**

* <code>bigint</code> - An arbitrary-precision integer.

**Returns:**

* An arbitrary-precision decimal number with the exponent set to 0.

### FromExtendedFloat
    @Deprecated public static EDecimal FromExtendedFloat​(EFloat ef)
Deprecated.
Renamed to FromEFloat.

**Parameters:**

* <code>ef</code> - An arbitrary-precision binary floating-point number.

**Returns:**

* An arbitrary-precision decimal number.

### FromEFloat
    public static EDecimal FromEFloat​(EFloat bigfloat)
Creates an arbitrary-precision decimal number from an arbitrary-precision
 binary floating-point number.

**Parameters:**

* <code>bigfloat</code> - An arbitrary-precision binary floating-point number.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigfloat</code> is null.

### FromBoolean
    public static EDecimal FromBoolean​(boolean boolValue)
Converts a boolean value (true or false) to an arbitrary-precision decimal
 number.

**Parameters:**

* <code>boolValue</code> - Either true or false.

**Returns:**

* The number 1 if <code>boolValue</code> is true; otherwise, 0.

### FromInt32
    public static EDecimal FromInt32​(int valueSmaller)
Creates an arbitrary-precision decimal number from a 32-bit signed integer.

**Parameters:**

* <code>valueSmaller</code> - The parameter <code>valueSmaller</code> is a 32-bit signed
 integer.

**Returns:**

* An arbitrary-precision decimal number with the exponent set to 0.

### FromInt64
    public static EDecimal FromInt64​(long valueSmall)
Creates an arbitrary-precision decimal number from a 64-bit signed integer.

**Parameters:**

* <code>valueSmall</code> - The parameter <code>valueSmall</code> is a 64-bit signed
 integer.

**Returns:**

* An arbitrary-precision decimal number with the exponent set to 0.

### FromSingle
    public static EDecimal FromSingle​(float flt)
Creates an arbitrary-precision decimal number from a 32-bit binary
 floating-point number. This method computes the exact value of the
 floating point number, not an approximation, as is often the case by
 converting the floating point number to a string first. Remember,
 though, that the exact value of a 32-bit binary floating-point
 number is not always the value that results when passing a literal
 decimal number (for example, calling
 <code>EDecimal.FromSingle(0.1f)</code>), since not all decimal numbers
 can be converted to exact binary numbers (in the example given, the
 resulting arbitrary-precision decimal will be the the value of the
  closest "float" to 0.1, not 0.1 exactly). To create an
 arbitrary-precision decimal number from a decimal value, use
 FromString instead in most cases (for example:
  <code>EDecimal.FromString("0.1")</code>).

**Parameters:**

* <code>flt</code> - The parameter <code>flt</code> is a 32-bit binary floating-point
 number.

**Returns:**

* An arbitrary-precision decimal number with the same value as <code>
 flt</code>.

### FromSingleBits
    public static EDecimal FromSingleBits​(int value)
Creates an arbitrary-precision decimal number from a 32-bit binary
 floating-point number encoded in the IEEE 754 binary32 format. This
 method computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the floating point
 number to a string first. Remember, though, that the exact value of
 a 32-bit binary floating-point number is not always the value that
 results when passing a literal decimal number, since not all decimal
 numbers can be converted to exact binary numbers (in the example
 given, the resulting arbitrary-precision decimal will be the the
  value of the closest "float" to 0.1, not 0.1 exactly). To create an
 arbitrary-precision decimal number from a decimal value, use
 FromString instead in most cases.

**Parameters:**

* <code>value</code> - A 32-bit binary floating-point number encoded in the IEEE 754
 binary32 format.

**Returns:**

* An arbitrary-precision decimal number with the same value as <code>
 value</code>.

### FromString
    public static EDecimal FromString​(char[] chars)
Creates an arbitrary-precision decimal number from a sequence of <code>char</code>
 s that represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information. Note that calling the overload
 that takes an EContext is often much faster than creating the
 EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
 especially if the context specifies a precision limit and exponent
 range.

**Parameters:**

* <code>chars</code> - A sequence that represents a number.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of <code>char</code> s.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>chars</code> is not a correctly
 formatted number sequence.

### FromString
    public static EDecimal FromString​(char[] chars, EContext ctx)
Creates an arbitrary-precision decimal number from a sequence of <code>char</code>
 s that represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information.

**Parameters:**

* <code>chars</code> - A sequence of <code>char</code> s that represents a number.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of <code>char</code> s.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>chars</code> is null.

### FromString
    public static EDecimal FromString​(char[] chars, int offset, int length)
Creates an arbitrary-precision decimal number from a sequence of <code>char</code>
 s that represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information. Note that calling the overload
 that takes an EContext is often much faster than creating the
 EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
 especially if the context specifies a precision limit and exponent
 range.

**Parameters:**

* <code>chars</code> - A sequence that represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>chars</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 chars</code> (but not more than <code>chars</code> 's length).

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of <code>char</code> s.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>chars</code> is not a correctly
 formatted number sequence.

* <code>java.lang.NullPointerException</code> - The parameter <code>chars</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>chars</code> 's length, or <code>chars</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### FromString
    public static EDecimal FromString​(char[] chars, int offset, int length, EContext ctx)
<p>Creates an arbitrary-precision decimal number from a sequence of
 <code>char</code> s that represents a number.</p> <p>The format of the
 sequence generally consists of:</p> <ul> <li>An optional plus sign
  ("+" , U+002B) or minus sign ("-", U+002D) (if the minus sign, the
 value is negative.)</li> <li>One or more digits, with a single
  optional decimal point (".", U+002E) before or after those digits or
 between two of them. These digits may begin with any number of
  zeros.</li> <li>Optionally, "E"/"e" followed by an optional
  (positive exponent) or "-" (negative exponent) and followed by one
 or more digits specifying the exponent (these digits may begin with
  any number of zeros).</li></ul> <p>The sequence can also be "-INF",
  "-Infinity", "Infinity", "INF", quiet NaN ("NaN" /"-NaN") followed
 by any number of digits (these digits may begin with any number of
  zeros), or signaling NaN ("sNaN" /"-sNaN") followed by any number of
 digits (these digits may begin with any number of zeros), all where
 the letters can be any combination of basic upper-case and/or basic
 lower-case letters.</p> <p>All characters mentioned above are the
 corresponding characters in the Basic Latin range. In particular,
 the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
 sequence is not allowed to contain white space characters, including
 spaces.</p>

**Parameters:**

* <code>chars</code> - A sequence of <code>char</code> s, a portion of which represents a
 number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>chars</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 chars</code> (but not more than <code>chars</code> 's length).

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of <code>char</code> s.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>chars</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>chars</code> 's length, or <code>chars</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### FromString
    public static EDecimal FromString​(byte[] bytes)
Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number. See
 <code>FromString(string, int, int, EContext)</code> for more information.
 Note that calling the overload that takes an EContext is often much
 faster than creating the EDecimal then calling
 <code>RoundToPrecision</code> on that EDecimal, especially if the context
 specifies a precision limit and exponent range.

**Parameters:**

* <code>bytes</code> - A sequence that represents a number.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of bytes (interpreted as text).

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>bytes</code> is not a correctly
 formatted number sequence.

### FromString
    public static EDecimal FromString​(byte[] bytes, EContext ctx)
Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number. See
 <code>FromString(string, int, int, EContext)</code> for more information.

**Parameters:**

* <code>bytes</code> - A sequence of bytes (interpreted as text) that represents a
 number.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of bytes (interpreted as text).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

### FromString
    public static EDecimal FromString​(byte[] bytes, int offset, int length)
Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number. See
 <code>FromString(string, int, int, EContext)</code> for more information.
 Note that calling the overload that takes an EContext is often much
 faster than creating the EDecimal then calling
 <code>RoundToPrecision</code> on that EDecimal, especially if the context
 specifies a precision limit and exponent range.

**Parameters:**

* <code>bytes</code> - A sequence that represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>bytes</code> begins.

* <code>length</code> - The length, in bytes, of the desired portion of <code>bytes</code>
 (but not more than <code>bytes</code> 's length).

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of bytes (interpreted as text).

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>bytes</code> is not a correctly
 formatted number sequence.

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>bytes</code> 's length, or <code>bytes</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### FromString
    public static EDecimal FromString​(byte[] bytes, int offset, int length, EContext ctx)
<p>Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number. Each byte in the
 sequence has to be a code point in the Basic Latin range (0x00 to
 0x7f or U+0000 to U+007F) of the Unicode Standard.</p> <p>The format
 of the sequence generally consists of:</p> <ul> <li>An optional plus
  sign ("+" , U+002B) or minus sign ("-", U+002D) (if the minus sign,
 the value is negative.)</li> <li>One or more digits, with a single
  optional decimal point (".", U+002E) before or after those digits or
 between two of them. These digits may begin with any number of
  zeros.</li> <li>Optionally, "E"/"e" followed by an optional
  (positive exponent) or "-" (negative exponent) and followed by one
 or more digits specifying the exponent (these digits may begin with
  any number of zeros).</li></ul> <p>The sequence can also be "-INF",
  "-Infinity", "Infinity", "INF", quiet NaN ("NaN" /"-NaN") followed
 by any number of digits (these digits may begin with any number of
  zeros), or signaling NaN ("sNaN" /"-sNaN") followed by any number of
 digits (these digits may begin with any number of zeros), all where
 the letters can be any combination of basic upper-case and/or basic
 lower-case letters.</p> <p>All characters mentioned above are the
 corresponding characters in the Basic Latin range. In particular,
 the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
 sequence is not allowed to contain white space characters, including
 spaces.</p>

**Parameters:**

* <code>bytes</code> - A sequence of bytes (interpreted as text), a portion of which
 represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>bytes</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 bytes</code> (but not more than <code>bytes</code> 's length).

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of bytes (interpreted as text).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>bytes</code> 's length, or <code>bytes</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### FromString
    public static EDecimal FromString​(java.lang.String str)
Creates an arbitrary-precision decimal number from a text string that
 represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information. Note that calling the overload
 that takes an EContext is often much faster than creating the
 EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
 especially if the context specifies a precision limit and exponent
 range.

**Parameters:**

* <code>str</code> - A string that represents a number.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

### FromString
    public static EDecimal FromString​(java.lang.String str, EContext ctx)
Creates an arbitrary-precision decimal number from a text string that
 represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information.

**Parameters:**

* <code>str</code> - A string that represents a number.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

### FromString
    public static EDecimal FromString​(java.lang.String str, int offset, int length)
Creates an arbitrary-precision decimal number from a text string that
 represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information. Note that calling the overload
 that takes an EContext is often much faster than creating the
 EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
 especially if the context specifies a precision limit and exponent
 range.

**Parameters:**

* <code>str</code> - A string that represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>str</code> 's length, or <code>str</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### FromString
    public static EDecimal FromString​(java.lang.String str, int offset, int length, EContext ctx)
<p>Creates an arbitrary-precision decimal number from a text string that
 represents a number.</p> <p>The format of the string generally
  consists of:</p> <ul> <li>An optional plus sign ("+" , U+002B) or
  minus sign ("-", U+002D) (if the minus sign, the value is
 negative.)</li> <li>One or more digits, with a single optional
  decimal point (".", U+002E) before or after those digits or between
 two of them. These digits may begin with any number of zeros.</li>
  <li>Optionally, "E"/"e" followed by an optional (positive exponent)
  or "-" (negative exponent) and followed by one or more digits
 specifying the exponent (these digits may begin with any number of
  zeros).</li></ul> <p>The string can also be "-INF", "-Infinity",
  "Infinity", "INF", quiet NaN ("NaN" /"-NaN") followed by any number
 of digits (these digits may begin with any number of zeros), or
  signaling NaN ("sNaN" /"-sNaN") followed by any number of digits
 (these digits may begin with any number of zeros), all where the
 letters can be any combination of basic upper-case and/or basic
 lower-case letters.</p> <p>All characters mentioned above are the
 corresponding characters in the Basic Latin range. In particular,
 the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
 string is not allowed to contain white space characters, including
 spaces.</p>

**Parameters:**

* <code>str</code> - A text string, a portion of which represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>str</code> 's length, or <code>str</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### Max
    public static EDecimal Max​(EDecimal first, EDecimal second, EContext ctx)
Gets the greater value between two decimal numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The larger value of the two numbers. If one is positive zero and the
 other is negative zero, returns the positive zero. If the two
 numbers are positive and have the same value, returns the one with
 the larger exponent. If the two numbers are negative and have the
 same value, returns the one with the smaller exponent.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### Max
    public static EDecimal Max​(EDecimal first, EDecimal second)
Gets the greater value between two decimal numbers.

**Parameters:**

* <code>first</code> - An arbitrary-precision decimal number.

* <code>second</code> - Another arbitrary-precision decimal number.

**Returns:**

* The larger value of the two numbers. If one is positive zero and the
 other is negative zero, returns the positive zero. If the two
 numbers are positive and have the same value, returns the one with
 the larger exponent. If the two numbers are negative and have the
 same value, returns the one with the smaller exponent.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### MaxMagnitude
    public static EDecimal MaxMagnitude​(EDecimal first, EDecimal second, EContext ctx)
Gets the greater value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Max.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The larger value of the two numbers, ignoring their signs.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### MaxMagnitude
    public static EDecimal MaxMagnitude​(EDecimal first, EDecimal second)
Gets the greater value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Max.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* The larger value of the two numbers, ignoring their signs.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### Min
    public static EDecimal Min​(EDecimal first, EDecimal second, EContext ctx)
Gets the lesser value between two decimal numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The smaller value of the two numbers. If one is positive zero and
 the other is negative zero, returns the negative zero. If the two
 numbers are positive and have the same value, returns the one with
 the smaller exponent. If the two numbers are negative and have the
 same value, returns the one with the larger exponent.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### Min
    public static EDecimal Min​(EDecimal first, EDecimal second)
Gets the lesser value between two decimal numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* The smaller value of the two numbers. If one is positive zero and
 the other is negative zero, returns the negative zero. If the two
 numbers are positive and have the same value, returns the one with
 the smaller exponent. If the two numbers are negative and have the
 same value, returns the one with the larger exponent.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### MinMagnitude
    public static EDecimal MinMagnitude​(EDecimal first, EDecimal second, EContext ctx)
Gets the lesser value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Min.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The smaller value of the two numbers, ignoring their signs.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### MinMagnitude
    public static EDecimal MinMagnitude​(EDecimal first, EDecimal second)
Gets the lesser value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Min.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* The smaller value of the two numbers, ignoring their signs.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### PI
    public static EDecimal PI​(EContext ctx)
Finds the constant π, the circumference of a circle divided by its diameter.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as π can never be represented exactly.</i>.

**Returns:**

* The constant π rounded to the given precision. Signals FlagInvalid
 and returns not-a-number (NaN) if the parameter <code>ctx</code> is null
 or the precision is unlimited (the context's Precision property is
 0).

### Abs
    public EDecimal Abs()
Finds the absolute value of this object (if it's negative, it becomes
 positive).

**Returns:**

* An arbitrary-precision decimal number. Returns signaling NaN if this
 value is signaling NaN. (In this sense, this method is similar to
  the "copy-abs" operation in the General Decimal Arithmetic
 Specification, except this method does not necessarily return a copy
 of this object.).

### CopySign
    public EDecimal CopySign​(EDecimal other)
Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number. (This method is similar to
  the "copy-sign" operation in the General Decimal Arithmetic
 Specification, except this method does not necessarily return a copy
 of this object.).

**Parameters:**

* <code>other</code> - A number whose sign will be copied.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>other</code> is null.

### Abs
    public EDecimal Abs​(EContext context)
Finds the absolute value of this object (if it's negative, it becomes
 positive).

**Parameters:**

* <code>context</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The absolute value of this object. Signals FlagInvalid and returns
 quiet NaN if this value is signaling NaN.

### Add
    public EDecimal Add​(EDecimal otherValue)
Adds this arbitrary-precision decimal floating-point number and another
 arbitrary-precision decimal floating-point number and returns the
 result. The exponent for the result is the lower of this
 arbitrary-precision decimal floating-point number's exponent and the
 other arbitrary-precision decimal floating-point number's exponent.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision decimal number.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 decimal floating-point number plus another arbitrary-precision
 decimal floating-point number. If this arbitrary-precision decimal
 floating-point number is not-a-number (NaN), returns NaN.

### Add
    public EDecimal Add​(EDecimal otherValue, EContext ctx)
Adds this arbitrary-precision decimal floating-point number and another
 arbitrary-precision decimal floating-point number and returns the
 result.

**Parameters:**

* <code>otherValue</code> - The number to add to.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 decimal floating-point number plus another arbitrary-precision
 decimal floating-point number. If this arbitrary-precision decimal
 floating-point number is not-a-number (NaN), returns NaN.

### compareTo
    public int compareTo​(EDecimal other)
Compares the mathematical values of this object and another object,
 accepting NaN values. This method currently uses the rules given in
 the CompareToValue method, so that it it is not consistent with the
 Equals method, but it may change in a future version to use the
 rules for the CompareToTotal method instead.

**Specified by:**

* <code>compareTo</code> in interface <code>java.lang.Comparable&lt;EDecimal&gt;</code>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
 value or if <code>other</code> is null, or 0 if both values are equal.
 This implementation returns a positive number if.

### compareTo
    public int compareTo​(int intOther)
Compares the mathematical values of this object and another object,
 accepting NaN values. This method currently uses the rules given in
 the CompareToValue method, so that it it is not consistent with the
 Equals method, but it may change in a future version to use the
 rules for the CompareToTotal method instead.

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 32-bit signed integer.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
 value, or 0 if both values are equal.

### CompareToValue
    public int CompareToValue​(int intOther)
Compares the mathematical values of this object and another object,
 accepting NaN values. <p>This method is not consistent with the
 Equals method because two different numbers with the same
 mathematical value, but different exponents, will compare as
 equal.</p> <p>In this method, negative zero and positive zero are
 considered equal.</p> <p>If this object is a quiet NaN or signaling
 NaN, this method will not trigger an error. Instead, NaN will
 compare greater than any other number, including infinity.</p>

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 32-bit signed integer.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
 value, or 0 if both values are equal.

### compareTo
    public int compareTo​(long intOther)
Compares the mathematical values of this object and another object,
 accepting NaN values. This method currently uses the rules given in
 the CompareToValue method, so that it it is not consistent with the
 Equals method, but it may change in a future version to use the
 rules for the CompareToTotal method instead.

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 64-bit signed integer.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
 value, or 0 if both values are equal.

### CompareToValue
    public int CompareToValue​(long intOther)
Compares the mathematical values of this object and another object,
 accepting NaN values. <p>This method is not consistent with the
 Equals method because two different numbers with the same
 mathematical value, but different exponents, will compare as
 equal.</p> <p>In this method, negative zero and positive zero are
 considered equal.</p> <p>If this object is a quiet NaN or signaling
 NaN, this method will not trigger an error. Instead, NaN will
 compare greater than any other number, including infinity.</p>

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 64-bit signed integer.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
 value, or 0 if both values are equal.

### CompareToValue
    public int CompareToValue​(EDecimal other)
Compares the mathematical values of this object and another object,
 accepting NaN values. <p>This method is not consistent with the
 Equals method because two different numbers with the same
 mathematical value, but different exponents, will compare as
 equal.</p> <p>In this method, negative zero and positive zero are
 considered equal.</p> <p>If this object or the other object is a
 quiet NaN or signaling NaN, this method will not trigger an error.
 Instead, NaN will compare greater than any other number, including
 infinity. Two different NaN values will be considered equal.</p>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
 value or if <code>other</code> is null, or 0 if both values are equal.
 This implementation returns a positive number if.

### CompareToBinary
    public int CompareToBinary​(EFloat other)
Compares an arbitrary-precision binary floating-point number with this
 instance.

**Parameters:**

* <code>other</code> - The other object to compare. Can be null.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less; or a positive number if this instance is greater. Returns 0 if
 both values are NaN (even signaling NaN) and 1 if this value is NaN
 (even signaling NaN) and the other isn't, or if the other value is
 null. This implementation returns a positive number if.

### CompareToSignal
    public EDecimal CompareToSignal​(EDecimal other, EContext ctx)
Compares the mathematical values of this object and another object, treating
 quiet NaN as signaling. <p>In this method, negative zero and
 positive zero are considered equal.</p> <p>If this object or the
 other object is a quiet NaN or signaling NaN, this method will
 return a quiet NaN and will signal a FlagInvalid flag.</p>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

* <code>ctx</code> - An arithmetic context. The precision, rounding, and exponent
 range are ignored. If <code>HasFlags</code> of the context is true, will
 store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null.

**Returns:**

* Quiet NaN if this object or the other object is NaN, or 0 if both
 objects have the same value, or -1 if this object is less than the
 other value, or a 1 if this object is greater. This implementation
 returns a positive number if.

### CompareToTotalMagnitude
    public int CompareToTotalMagnitude​(EDecimal other)
Compares the absolute values of this object and another object, imposing a
 total ordering on all possible values (ignoring their signs). In
 this method: <ul> <li>For objects with the same value, the one with
  the higher exponent has a greater "absolute value".</li>
 <li>Negative zero and positive zero are considered equal.</li>
  <li>Quiet NaN has a higher "absolute value" than signaling NaN. If
 both objects are quiet NaN or both are signaling NaN, the one with
  the higher diagnostic information has a greater "absolute
  value".</li> <li>NaN has a higher "absolute value" than
  infinity.</li> <li>Infinity has a higher "absolute value" than any
 finite number.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number to compare with this one.

**Returns:**

* The number 0 if both objects have the same value (ignoring their
 signs), or -1 if this object is less than the other value (ignoring
 their signs), or 1 if this object is greater (ignoring their signs).
 This implementation returns a positive number if.

### CompareToTotal
    public int CompareToTotal​(EDecimal other, EContext ctx)
Compares the values of this object and another object, imposing a total
 ordering on all possible values. In this method: <ul> <li>For
 objects with the same value, the one with the higher exponent has a
  greater "absolute value".</li> <li>Negative zero is less than
  positive zero.</li> <li>Quiet NaN has a higher "absolute value" than
 signaling NaN. If both objects are quiet NaN or both are signaling
 NaN, the one with the higher diagnostic information has a greater
  "absolute value".</li> <li>NaN has a higher "absolute value" than
  infinity.</li> <li>Infinity has a higher "absolute value" than any
 finite number.</li> <li>Negative numbers are less than positive
 numbers.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number to compare with this one.

* <code>ctx</code> - An arithmetic context. Flags will be set in this context only if
 <code>HasFlags</code> and <code>IsSimplified</code> of the context are true
 and only if an operand needed to be rounded before carrying out the
 operation. Can be null.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.
 Does not signal flags if either value is signaling NaN. This
 implementation returns a positive number if.

### CompareToTotalMagnitude
    public int CompareToTotalMagnitude​(EDecimal other, EContext ctx)
Compares the values of this object and another object, imposing a total
 ordering on all possible values (ignoring their signs). In this
 method: <ul> <li>For objects with the same value, the one with the
  higher exponent has a greater "absolute value".</li> <li>Negative
 zero is less than positive zero.</li> <li>Quiet NaN has a higher
  "absolute value" than signaling NaN. If both objects are quiet NaN
 or both are signaling NaN, the one with the higher diagnostic
  information has a greater "absolute value".</li> <li>NaN has a
  higher "absolute value" than infinity.</li> <li>Infinity has a
  higher "absolute value" than any finite number.</li> <li>Negative
 numbers are less than positive numbers.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number to compare with this one.

* <code>ctx</code> - An arithmetic context. Flags will be set in this context only if
 <code>HasFlags</code> and <code>IsSimplified</code> of the context are true
 and only if an operand needed to be rounded before carrying out the
 operation. Can be null.

**Returns:**

* The number 0 if both objects have the same value (ignoring their
 signs), or -1 if this object is less than the other value (ignoring
 their signs), or 1 if this object is greater (ignoring their signs).
 Does not signal flags if either value is signaling NaN. This
 implementation returns a positive number if.

### CompareToTotal
    public int CompareToTotal​(EDecimal other)
Compares the values of this object and another object, imposing a total
 ordering on all possible values. In this method: <ul> <li>For
 objects with the same value, the one with the higher exponent has a
  greater "absolute value".</li> <li>Negative zero is less than
  positive zero.</li> <li>Quiet NaN has a higher "absolute value" than
 signaling NaN. If both objects are quiet NaN or both are signaling
 NaN, the one with the higher diagnostic information has a greater
  "absolute value".</li> <li>NaN has a higher "absolute value" than
  infinity.</li> <li>Infinity has a higher "absolute value" than any
 finite number.</li> <li>Negative numbers are less than positive
 numbers.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number to compare with this one.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.
 This implementation returns a positive number if.

### CompareToWithContext
    public EDecimal CompareToWithContext​(EDecimal other, EContext ctx)
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
 other value, or 1 if this object is greater. This implementation
 returns a positive number if.

### Divide
    public EDecimal Divide​(EDecimal divisor)
Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result; returns NaN instead if the result would have a
 nonterminating decimal expansion (including 1/3, 1/12, 1/7, 2/3, and
 so on); if this is not desired, use DivideToExponent, or use the
 Divide overload that takes an EContext.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* The result of dividing this arbitrary-precision decimal
 floating-point number by another arbitrary-precision decimal
 floating-point number. Returns infinity if the divisor (this
 arbitrary-precision decimal floating-point number) is 0 and the
 dividend (the other arbitrary-precision decimal floating-point
 number) is nonzero. Returns not-a-number (NaN) if the divisor and
 the dividend are 0. Returns NaN if the result can't be exact because
 it would have a nonterminating binary expansion (examples include 1
 divided by any multiple of 3, such as 1/3 or 1/12). If this is not
 desired, use DivideToExponent instead, or use the Divide overload
 that takes an <code>EContext</code> (such as <code>EContext.Decimal128</code>
) instead.

### Divide
    public EDecimal Divide​(EDecimal divisor, EContext ctx)
Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The result of dividing this arbitrary-precision decimal
 floating-point number by another arbitrary-precision decimal
 floating-point number. Signals FlagDivideByZero and returns infinity
 if the divisor (this arbitrary-precision decimal floating-point
 number) is 0 and the dividend (the other arbitrary-precision decimal
 floating-point number) is nonzero. Signals FlagInvalid and returns
 not-a-number (NaN) if the divisor and the dividend are 0; or, either
 <code>ctx</code> is null or <code>ctx</code> 's precision is 0, and the result
 would have a nonterminating decimal expansion (examples include 1
 divided by any multiple of 3, such as 1/3 or 1/12); or, the rounding
 mode is ERounding.None and the result is not exact.

### DivideAndRemainderNaturalScale
    @Deprecated public EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor)
Deprecated.
Renamed to DivRemNaturalScale.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* A 2 element array consisting of the quotient and remainder in that
 order.

### DivideAndRemainderNaturalScale
    @Deprecated public EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor, EContext ctx)
Deprecated.
Renamed to DivRemNaturalScale.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only in
 the division portion of the remainder calculation; as a result, it's
 possible for the remainder to have a higher precision than given in
 this context. Flags will be set on the given context only if the
 context's <code>HasFlags</code> is true and the integer part of the
 division result doesn't fit the precision and exponent range without
 rounding. Can be null, in which the precision is unlimited and no
 additional rounding, other than the rounding down to an integer
 after division, is needed.

**Returns:**

* A 2 element array consisting of the quotient and remainder in that
 order.

### DivRemNaturalScale
    public EDecimal[] DivRemNaturalScale​(EDecimal divisor)
Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns a
 two-item array containing the result of the division and the
 remainder, in that order. The result of division is calculated as
 though by <code>DivideToIntegerNaturalScale</code>, and the remainder is
 calculated as though by <code>RemainderNaturalScale</code>.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* An array of two items: the first is the result of the division as an
 arbitrary-precision decimal floating-point number, and the second is
 the remainder as an arbitrary-precision decimal floating-point
 number. The result of division is the result of the method on the
 two operands, and the remainder is the result of the Remainder
 method on the two operands.

### DivRemNaturalScale
    public EDecimal[] DivRemNaturalScale​(EDecimal divisor, EContext ctx)
Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns a
 two-item array containing the result of the division and the
 remainder, in that order. The result of division is calculated as
 though by <code>DivideToIntegerNaturalScale</code>, and the remainder is
 calculated as though by <code>RemainderNaturalScale</code>.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only in
 the division portion of the remainder calculation; as a result, it's
 possible for the remainder to have a higher precision than given in
 this context. Flags will be set on the given context only if the
 context's <code>HasFlags</code> is true and the integer part of the
 division result doesn't fit the precision and exponent range without
 rounding. Can be null, in which the precision is unlimited and no
 additional rounding, other than the rounding down to an integer
 after division, is needed.

**Returns:**

* An array of two items: the first is the result of the division as an
 arbitrary-precision decimal floating-point number, and the second is
 the remainder as an arbitrary-precision decimal floating-point
 number. The result of division is the result of the method on the
 two operands, and the remainder is the result of the Remainder
 method on the two operands.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, long desiredExponentSmall, EContext ctx)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

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
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the context defines an exponent range and the desired
 exponent is outside that range. Signals FlagInvalid and returns
 not-a-number (NaN) if the rounding mode is ERounding.None and the
 result is not exact.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, int desiredExponentInt, EContext ctx)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - The number to divide by.

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
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the context defines an exponent range and the desired
 exponent is outside that range. Signals FlagInvalid and returns
 not-a-number (NaN) if the rounding mode is ERounding.None and the
 result is not exact.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, long desiredExponentSmall, ERounding rounding)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

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
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the rounding mode is ERounding.None and the result is not
 exact.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, int desiredExponentInt, ERounding rounding)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - The number to divide by.

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
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the rounding mode is ERounding.None and the result is not
 exact.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, EInteger exponent, EContext ctx)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

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
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the context defines an exponent range and the desired
 exponent is outside that range. Signals FlagInvalid and returns
 not-a-number (NaN) if the rounding mode is ERounding.None and the
 result is not exact.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, EInteger exponent)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result, using the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>exponent</code> - The desired exponent. A negative number places the cutoff
 point to the right of the usual decimal point (so a negative number
 means the number of decimal places to round to). A positive number
 places the cutoff point to the left of the usual decimal point.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, long desiredExponentSmall)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 64-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponentSmall</code> - The desired exponent. A negative number places
 the cutoff point to the right of the usual decimal point (so a
 negative number means the number of decimal places to round to). A
 positive number places the cutoff point to the left of the usual
 decimal point.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, int desiredExponentInt)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponentInt</code> - The desired exponent. A negative number places the
 cutoff point to the right of the usual decimal point (so a negative
 number means the number of decimal places to round to). A positive
 number places the cutoff point to the left of the usual decimal
 point.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0.

### DivideToExponent
    public EDecimal DivideToExponent​(EDecimal divisor, EInteger desiredExponent, ERounding rounding)
Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

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
    public EDecimal DivideToIntegerNaturalScale​(EDecimal divisor)
Divides two arbitrary-precision decimal numbers, and returns the integer
 part of the result, rounded down, with the preferred exponent set to
 this value's exponent minus the divisor's exponent.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* The integer part of the quotient of the two objects. Signals
 FlagDivideByZero and returns infinity if the divisor is 0 and the
 dividend is nonzero. Signals FlagInvalid and returns not-a-number
 (NaN) if the divisor and the dividend are 0.

### DivideToIntegerNaturalScale
    public EDecimal DivideToIntegerNaturalScale​(EDecimal divisor, EContext ctx)
Divides this object by another object, and returns the integer part of the
 result (which is initially rounded down), with the preferred
 exponent set to this value's exponent minus the divisor's exponent.

**Parameters:**

* <code>divisor</code> - The parameter <code>divisor</code> is an arbitrary-precision
 decimal floating-point number.

* <code>ctx</code> - The parameter <code>ctx</code> is an EContext object.

**Returns:**

* The integer part of the quotient of the two objects. Signals
 FlagInvalid and returns not-a-number (NaN) if the return value would
 overflow the exponent range. Signals FlagDivideByZero and returns
 infinity if the divisor is 0 and the dividend is nonzero. Signals
 FlagInvalid and returns not-a-number (NaN) if the divisor and the
 dividend are 0. Signals FlagInvalid and returns not-a-number (NaN)
 if the rounding mode is ERounding.None and the result is not exact.

### DivideToIntegerZeroScale
    public EDecimal DivideToIntegerZeroScale​(EDecimal divisor, EContext ctx)
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
 the divisor is 0 and the dividend is nonzero. Signals FlagInvalid
 and returns not-a-number (NaN) if the divisor and the dividend are
 0, or if the result doesn't fit the given precision.

### DivideToSameExponent
    public EDecimal DivideToSameExponent​(EDecimal divisor, ERounding rounding)
Divides this object by another decimal number and returns a result with the
 same exponent as this object (the dividend).

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>rounding</code> - The rounding mode to use if the result must be scaled down
 to have the same exponent as this value.

**Returns:**

* The quotient of the two numbers. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the rounding mode is ERounding.None and the result is not
 exact.

### equals
    public boolean equals​(EDecimal other)
Determines whether this object's significand, exponent, and properties are
 equal to those of another object. Not-a-number values are considered
 equal if the rest of their properties are equal.

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

**Returns:**

* <code>true</code> if this object's significand and exponent are equal to
 those of another object; otherwise, <code>false</code>.

### equals
    public boolean equals​(java.lang.Object obj)
Determines whether this object's significand, exponent, and properties are
 equal to those of another object and that other object is an
 arbitrary-precision decimal number. Not-a-number values are
 considered equal if the rest of their properties are equal.

**Overrides:**

* <code>equals</code> in class <code>java.lang.Object</code>

**Parameters:**

* <code>obj</code> - The parameter <code>obj</code> is an arbitrary object.

**Returns:**

* <code>true</code> if the objects are equal; otherwise, <code>false</code>. In
 this method, two objects are not equal if they don't have the same
 type or if one is null and the other isn't.

### Exp
    public EDecimal Exp​(EContext ctx)
Finds e (the base of natural logarithms) raised to the power of this
 object's value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the exponential function's results are generally
 not exact.</i> (Unlike in the General Decimal Arithmetic
 Specification, any rounding mode is allowed.).

**Returns:**

* Exponential of this object. If this object's value is 1, returns an
  approximation to " e" within the given precision. Signals
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>
 ctx</code> is null or the precision is unlimited (the context's Precision
 property is 0).

### ExpM1
    public EDecimal ExpM1​(EContext ctx)
Finds e (the base of natural logarithms) raised to the power of this
 object's value, and subtracts the result by 1 and returns the final
 result, in a way that avoids loss of precision if the true result is
 very close to 0.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the exponential function's results are generally
 not exact.</i> (Unlike in the General Binary Arithmetic
 Specification, any rounding mode is allowed.).

**Returns:**

* Exponential of this object, minus 1. Signals FlagInvalid and returns
 not-a-number (NaN) if the parameter <code>ctx</code> is null or the
 precision is unlimited (the context's Precision property is 0).

### hashCode
    public int hashCode()
Calculates this object's hash code. No application or process IDs are used
 in the hash code calculation.

**Overrides:**

* <code>hashCode</code> in class <code>java.lang.Object</code>

**Returns:**

* A 32-bit signed integer.

### IsInfinity
    public boolean IsInfinity()
Gets a value indicating whether this object is positive or negative
 infinity.

**Returns:**

* <code>true</code> if this object is positive or negative infinity;
 otherwise, <code>false</code>.

### IsNaN
    public boolean IsNaN()
Gets a value indicating whether this object is not a number (NaN).

**Returns:**

* <code>true</code> if this object is not a number (NaN); otherwise, <code>
 false</code>.

### IsNegativeInfinity
    public boolean IsNegativeInfinity()
Returns whether this object is negative infinity.

**Returns:**

* <code>true</code> if this object is negative infinity; otherwise, <code>
 false</code>.

### IsPositiveInfinity
    public boolean IsPositiveInfinity()
Returns whether this object is positive infinity.

**Returns:**

* <code>true</code> if this object is positive infinity; otherwise, <code>
 false</code>.

### IsQuietNaN
    public boolean IsQuietNaN()
Gets a value indicating whether this object is a quiet not-a-number value.

**Returns:**

* <code>true</code> if this object is a quiet not-a-number value;
 otherwise, <code>false</code>.

### IsSignalingNaN
    public boolean IsSignalingNaN()
Gets a value indicating whether this object is a signaling not-a-number
 value.

**Returns:**

* <code>true</code> if this object is a signaling not-a-number value;
 otherwise, <code>false</code>.

### Log
    public EDecimal Log​(EContext ctx)
Finds the natural logarithm of this object, that is, the power (exponent)
 that e (the base of natural logarithms) must be raised to in order
 to equal this object's value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the ln function's results are generally not
 exact.</i> (Unlike in the General Decimal Arithmetic Specification,
 any rounding mode is allowed.).

**Returns:**

* Ln(this object). Signals the flag FlagInvalid and returns NaN if
 this object is less than 0 (the result would be a complex number
 with a real part equal to Ln of this object's absolute value and an
 imaginary part equal to pi, but the return value is still NaN.).
 Signals FlagInvalid and returns not-a-number (NaN) if the parameter
 <code>ctx</code> is null or the precision is unlimited (the context's
 Precision property is 0). Signals no flags and returns negative
 infinity if this object's value is 0.

### Log10
    public EDecimal Log10​(EContext ctx)
Finds the base-10 logarithm of this object, that is, the power (exponent)
 that the number 10 must be raised to in order to equal this object's
 value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the ln function's results are generally not
 exact.</i> (Unlike in the General Decimal Arithmetic Specification,
 any rounding mode is allowed.).

**Returns:**

* Ln(this object)/Ln(10). Signals the flag FlagInvalid and returns
 not-a-number (NaN) if this object is less than 0. Signals
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>
 ctx</code> is null or the precision is unlimited (the context's Precision
 property is 0).

### Log1P
    public EDecimal Log1P​(EContext ctx)
Adds 1 to this object's value and finds the natural logarithm of the result,
 in a way that avoids loss of precision when this object's value is
 between 0 and 1.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the ln function's results are generally not
 exact.</i> (Unlike in the General Binary Arithmetic Specification,
 any rounding mode is allowed.).

**Returns:**

* Ln(1+(this object)). Signals the flag FlagInvalid and returns NaN if
 this object is less than -1 (the result would be a complex number
 with a real part equal to Ln of 1 plus this object's absolute value
 and an imaginary part equal to pi, but the return value is still
 NaN.). Signals FlagInvalid and returns not-a-number (NaN) if the
 parameter <code>ctx</code> is null or the precision is unlimited (the
 context's Precision property is 0). Signals no flags and returns
 negative infinity if this object's value is 0.

### LogN
    public EDecimal LogN​(EDecimal baseValue, EContext ctx)
Finds the base-N logarithm of this object, that is, the power (exponent)
 that the number N must be raised to in order to equal this object's
 value.

**Parameters:**

* <code>baseValue</code> - The parameter <code>baseValue</code> is a Numbers.EDecimal
 object.

* <code>ctx</code> - The parameter <code>ctx</code> is a Numbers.EContext object.

**Returns:**

* Ln(this object)/Ln(baseValue). Signals the flag FlagInvalid and
 returns not-a-number (NaN) if this object is less than 0. Signals
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>
 ctx</code> is null or the precision is unlimited (the context's Precision
 property is 0).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>baseValue</code> is null.

### MovePointLeft
    public EDecimal MovePointLeft​(int places)
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
    public EDecimal MovePointLeft​(int places, EContext ctx)
Returns a number similar to this number but with the decimal point moved to
 the left.

**Parameters:**

* <code>places</code> - The number of decimal places to move the decimal point to the
 left. If this number is negative, instead moves the decimal point to
 the right by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is decreased by <code>places</code>, but not to
 more than 0.

### MovePointLeft
    public EDecimal MovePointLeft​(EInteger bigPlaces)
Returns a number similar to this number but with the decimal point moved to
 the left.

**Parameters:**

* <code>bigPlaces</code> - The number of decimal places to move the decimal point to
 the left. If this number is negative, instead moves the decimal
 point to the right by this number's absolute value.

**Returns:**

* A number whose exponent is decreased by <code>bigPlaces</code>, but not
 to more than 0.

### MovePointLeft
    public EDecimal MovePointLeft​(EInteger bigPlaces, EContext ctx)
Returns a number similar to this number but with the decimal point moved to
 the left.

**Parameters:**

* <code>bigPlaces</code> - The number of decimal places to move the decimal point to
 the left. If this number is negative, instead moves the decimal
 point to the right by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is decreased by <code>bigPlaces</code>, but not
 to more than 0.

### MovePointRight
    public EDecimal MovePointRight​(int places)
Returns a number similar to this number but with the decimal point moved to
 the right.

**Parameters:**

* <code>places</code> - The number of decimal places to move the decimal point to the
 right. If this number is negative, instead moves the decimal point
 to the left by this number's absolute value.

**Returns:**

* A number whose exponent is increased by <code>places</code>, but not to
 more than 0.

### MovePointRight
    public EDecimal MovePointRight​(int places, EContext ctx)
Returns a number similar to this number but with the decimal point moved to
 the right.

**Parameters:**

* <code>places</code> - The number of decimal places to move the decimal point to the
 right. If this number is negative, instead moves the decimal point
 to the left by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is increased by <code>places</code>, but not to
 more than 0.

### MovePointRight
    public EDecimal MovePointRight​(EInteger bigPlaces)
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
    public EDecimal MovePointRight​(EInteger bigPlaces, EContext ctx)
Returns a number similar to this number but with the decimal point moved to
 the right.

**Parameters:**

* <code>bigPlaces</code> - The number of decimal places to move the decimal point to
 the right. If this number is negative, instead moves the decimal
 point to the left by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is increased by <code>bigPlaces</code>, but not
 to more than 0.

### Multiply
    public EDecimal Multiply​(EDecimal otherValue)
Multiplies this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result. The exponent for the result is this arbitrary-precision
 decimal floating-point number's exponent plus the other
 arbitrary-precision decimal floating-point number's exponent.

**Parameters:**

* <code>otherValue</code> - Another decimal number.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 decimal floating-point number times another arbitrary-precision
 decimal floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>otherValue</code> is null.

### Multiply
    public EDecimal Multiply​(EDecimal op, EContext ctx)
Multiplies this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result.

**Parameters:**

* <code>op</code> - Another decimal number.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 decimal floating-point number times another arbitrary-precision
 decimal floating-point number.

### Add
    public EDecimal Add​(long longValue)
Adds this arbitrary-precision decimal floating-point number and a 64-bit
 signed integer and returns the result. The exponent for the result
 is the lower of this arbitrary-precision decimal floating-point
 number's exponent and the other 64-bit signed integer's exponent.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 decimal floating-point number plus a 64-bit signed integer. If this
 arbitrary-precision decimal floating-point number is not-a-number
 (NaN), returns NaN.

### Subtract
    public EDecimal Subtract​(long longValue)
Subtracts a 64-bit signed integer from this arbitrary-precision decimal
 floating-point number and returns the result. The exponent for the
 result is the lower of this arbitrary-precision decimal
 floating-point number's exponent and the other 64-bit signed
 integer's exponent.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision decimal floating-point number minus a 64-bit
 signed integer. If this arbitrary-precision decimal floating-point
 number is not-a-number (NaN), returns NaN.

### Multiply
    public EDecimal Multiply​(long longValue)
Multiplies this arbitrary-precision decimal floating-point number by a
 64-bit signed integer and returns the result. The exponent for the
 result is this arbitrary-precision decimal floating-point number's
 exponent plus the other 64-bit signed integer's exponent.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 decimal floating-point number times a 64-bit signed integer.

### Divide
    public EDecimal Divide​(long longValue)
Divides this arbitrary-precision decimal floating-point number by a 64-bit
 signed integer and returns the result; returns NaN instead if the
 result would have a nonterminating decimal expansion (including 1/3,
 1/12, 1/7, 2/3, and so on); if this is not desired, use
 DivideToExponent, or use the Divide overload that takes an EContext.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The result of dividing this arbitrary-precision decimal
 floating-point number by a 64-bit signed integer. Returns infinity
 if the divisor (this arbitrary-precision decimal floating-point
 number) is 0 and the dividend (the other 64-bit signed integer) is
 nonzero. Returns not-a-number (NaN) if the divisor and the dividend
 are 0. Returns NaN if the result can't be exact because it would
 have a nonterminating binary expansion (examples include 1 divided
 by any multiple of 3, such as 1/3 or 1/12). If this is not desired,
 use DivideToExponent instead, or use the Divide overload that takes
 an <code>EContext</code> (such as <code>EContext.Decimal128</code>) instead.

### Add
    public EDecimal Add​(int intValue)
Adds this arbitrary-precision decimal floating-point number and a 32-bit
 signed integer and returns the result. The exponent for the result
 is the lower of this arbitrary-precision decimal floating-point
 number's exponent and the other 32-bit signed integer's exponent.

**Parameters:**

* <code>intValue</code> - A 32-bit signed integer to add to this object.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 decimal floating-point number plus a 32-bit signed integer. If this
 arbitrary-precision decimal floating-point number is not-a-number
 (NaN), returns NaN.

### Subtract
    public EDecimal Subtract​(int intValue)
Subtracts a 32-bit signed integer from this arbitrary-precision decimal
 floating-point number and returns the result. The exponent for the
 result is the lower of this arbitrary-precision decimal
 floating-point number's exponent and the other 32-bit signed
 integer's exponent.

**Parameters:**

* <code>intValue</code> - A 32-bit signed integer to subtract from this object.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision decimal floating-point number minus a 32-bit
 signed integer. If this arbitrary-precision decimal floating-point
 number is not-a-number (NaN), returns NaN.

### Multiply
    public EDecimal Multiply​(int intValue)
Multiplies this arbitrary-precision decimal floating-point number by a
 32-bit signed integer and returns the result. The exponent for the
 result is this arbitrary-precision decimal floating-point number's
 exponent plus the other 32-bit signed integer's exponent.

**Parameters:**

* <code>intValue</code> - A 32-bit signed integer to multiply this object by.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 decimal floating-point number times a 32-bit signed integer.

### Divide
    public EDecimal Divide​(int intValue)
Divides this arbitrary-precision decimal floating-point number by a 32-bit
 signed integer and returns the result; returns NaN instead if the
 result would have a nonterminating decimal expansion (including 1/3,
 1/12, 1/7, 2/3, and so on); if this is not desired, use
 DivideToExponent, or use the Divide overload that takes an EContext.

**Parameters:**

* <code>intValue</code> - A 32-bit signed integer, the divisor, to divide this object
 by.

**Returns:**

* The result of dividing this arbitrary-precision decimal
 floating-point number by a 32-bit signed integer. Returns infinity
 if the divisor (this arbitrary-precision decimal floating-point
 number) is 0 and the dividend (the other 32-bit signed integer) is
 nonzero. Returns not-a-number (NaN) if the divisor and the dividend
 are 0. Returns NaN if the result can't be exact because it would
 have a nonterminating binary expansion (examples include 1 divided
 by any multiple of 3, such as 1/3 or 1/12). If this is not desired,
 use DivideToExponent instead, or use the Divide overload that takes
 an <code>EContext</code> (such as <code>EContext.Decimal128</code>) instead.

### MultiplyAndAdd
    public EDecimal MultiplyAndAdd​(EDecimal multiplicand, EDecimal augend)
Multiplies by one decimal number, and then adds another decimal number.

**Parameters:**

* <code>multiplicand</code> - The value to multiply.

* <code>augend</code> - The value to add.

**Returns:**

* An arbitrary-precision decimal floating-point number.

### MultiplyAndAdd
    public EDecimal MultiplyAndAdd​(EDecimal op, EDecimal augend, EContext ctx)
Multiplies by one value, and then adds another value.

**Parameters:**

* <code>op</code> - The value to multiply.

* <code>augend</code> - The value to add.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed. If
 the precision doesn't indicate a simplified arithmetic, rounding and
 precision.Divide(exponent) adjustment is done only once, namely,
 after multiplying and adding.

**Returns:**

* The result thisValue * multiplicand + augend.

### MultiplyAndSubtract
    public EDecimal MultiplyAndSubtract​(EDecimal op, EDecimal subtrahend, EContext ctx)
Multiplies by one value, and then subtracts another value.

**Parameters:**

* <code>op</code> - The value to multiply.

* <code>subtrahend</code> - The value to subtract.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed. If
 the precision doesn't indicate a simplified arithmetic, rounding and
 precision.Divide(exponent) adjustment is done only once, namely,
 after multiplying and subtracting.

**Returns:**

* The result thisValue * multiplicand - subtrahend.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>op</code> or <code>subtrahend</code>
 is null.

### Negate
    public EDecimal Negate()
Gets an object with the same value as this one, but with the sign reversed.

**Returns:**

* An arbitrary-precision decimal number. If this value is positive
 zero, returns negative zero. Returns signaling NaN if this value is
 signaling NaN. (In this sense, this method is similar to the
  "copy-negate" operation in the General Decimal Arithmetic
 Specification, except this method does not necessarily return a copy
 of this object.).

### Negate
    public EDecimal Negate​(EContext context)
Returns an arbitrary-precision decimal number with the same value as this
 object but with the sign reversed.

**Parameters:**

* <code>context</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* An arbitrary-precision decimal number. If this value is positive
 zero, returns positive zero. Signals FlagInvalid and returns quiet
 NaN if this value is signaling NaN.

### NextMinus
    public EDecimal NextMinus​(EContext ctx)
Finds the largest value that's smaller than the given value.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision and
 exponent range of the result. The rounding mode from this context is
 ignored. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags).

**Returns:**

* Returns the largest value that's less than the given value. Returns
 negative infinity if the result is negative infinity. Signals
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>
 ctx</code> is null, the precision is 0, or <code>ctx</code> has an unlimited
 exponent range.

### NextPlus
    public EDecimal NextPlus​(EContext ctx)
Finds the smallest value that's greater than the given value.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision and
 exponent range of the result. The rounding mode from this context is
 ignored. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags).

**Returns:**

* Returns the smallest value that's greater than the given
 value.Signals FlagInvalid and returns not-a-number (NaN) if the
 parameter <code>ctx</code> is null, the precision is 0, or <code>ctx</code>
 has an unlimited exponent range.

### NextToward
    public EDecimal NextToward​(EDecimal otherValue, EContext ctx)
Finds the next value that is closer to the other object's value than this
 object's value. Returns a copy of this value with the same sign as
 the other value if both values are equal.

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
 parameter <code>ctx</code> is null, the precision is 0, or <code>ctx</code>
 has an unlimited exponent range.

### Plus
    public EDecimal Plus​(EContext ctx)
Rounds this object's value to a given precision, using the given rounding
 mode and range of exponent, and also converts negative zero to
 positive zero. The idiom <code>EDecimal.SignalingNaN.Plus(ctx)</code> is
 useful for triggering an invalid operation and returning
 not-a-number (NaN) for custom arithmetic operations.

**Parameters:**

* <code>ctx</code> - A context for controlling the precision, rounding mode, and
 exponent range. Can be null, in which case the precision is
 unlimited and rounding isn't needed.

**Returns:**

* The closest value to this object's value, rounded to the specified
 precision. If <code>ctx</code> is null or the precision and exponent
 range are unlimited, returns the same value as this object (or a
 quiet NaN if this object is a signaling NaN).

### Pow
    public EDecimal Pow​(EDecimal exponent, EContext ctx)
Raises this object's value to the given exponent.

**Parameters:**

* <code>exponent</code> - An arbitrary-precision decimal number expressing the
 exponent to raise this object's value to.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* This^exponent. Signals the flag FlagInvalid and returns NaN if this
 object and exponent are both 0; or if this value is less than 0 and
 the exponent either has a fractional part or is infinity. Signals
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>
 ctx</code> is null or the precision is unlimited (the context's Precision
 property is 0), and the exponent has a fractional part.

### Pow
    public EDecimal Pow​(EDecimal exponent)
Raises this object's value to the given exponent, using unlimited precision.

**Parameters:**

* <code>exponent</code> - An arbitrary-precision decimal number expressing the
 exponent to raise this object's value to.

**Returns:**

* This^exponent. Returns not-a-number (NaN) if the exponent has a
 fractional part.

### Pow
    public EDecimal Pow​(int exponentSmall, EContext ctx)
Raises this object's value to the given exponent.

**Parameters:**

* <code>exponentSmall</code> - The exponent to raise this object's value to.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* This^exponent. Signals the flag FlagInvalid and returns NaN if this
 object and exponent are both 0.

### Pow
    public EDecimal Pow​(int exponentSmall)
Raises this object's value to the given exponent.

**Parameters:**

* <code>exponentSmall</code> - The exponent to raise this object's value to.

**Returns:**

* This^exponent. Returns not-a-number (NaN) if this object and
 exponent are both 0.

### Precision
    public EInteger Precision()
Finds the number of digits in this number's significand. Returns 1 if this
 value is 0, and 0 if this value is infinity or not-a-number (NaN).

**Returns:**

* An arbitrary-precision integer.

### Quantize
    public EDecimal Quantize​(EInteger desiredExponent, EContext ctx)
Returns an arbitrary-precision decimal number with the same value but a new
 exponent. <p>Note that this is not always the same as rounding to a
 given number of decimal places, since it can fail if the difference
 between this value's exponent and the desired exponent is too big,
 depending on the maximum precision. If rounding to a number of
 decimal places is desired, it's better to use the RoundToExponent
 and RoundToIntegral methods instead.</p> <p><b>Remark:</b> This
 method can be used to implement fixed-point decimal arithmetic, in
 which each decimal number has a fixed number of digits after the
 decimal point. The following code example returns a fixed-point
 number with up to 20 digits before and exactly 5 digits after the
 decimal point:</p> <pre> /* After performing arithmetic operations, adjust /* the number to 5*/*/ /**/ digits after the decimal point number = number.Quantize(EInteger.FromInt32(-5), /* five digits after the decimal point*/ EContext.ForPrecision(25) /* 25-digit precision);*/</pre> <p>A fixed-point decimal arithmetic in
 which no digits come after the decimal point (a desired exponent of
  0) is considered an "integer arithmetic".</p>

**Parameters:**

* <code>desiredExponent</code> - The desired exponent for the result. The exponent is
 the number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags). Can be null, in which case the default
 rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number with the same value as this
 object but with the exponent changed. Signals FlagInvalid and
 returns not-a-number (NaN) if this object is infinity, if the
 rounded result can't fit the given precision, or if the context
 defines an exponent range and the given exponent is outside that
 range.

### Quantize
    public EDecimal Quantize​(int desiredExponentInt, ERounding rounding)
Returns an arbitrary-precision decimal number with the same value as this
 one but a new exponent. <p><b>Remark:</b> This method can be used to
 implement fixed-point decimal arithmetic, in which a fixed number of
 digits come after the decimal point. A fixed-point decimal
 arithmetic in which no digits come after the decimal point (a
  desired exponent of 0) is considered an "integer arithmetic" .</p>

**Parameters:**

* <code>desiredExponentInt</code> - The desired exponent for the result. The exponent
 is the number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>rounding</code> - A rounding mode to use in case the result needs to be
 rounded to fit the given exponent.

**Returns:**

* An arbitrary-precision decimal number with the same value as this
 object but with the exponent changed. Returns not-a-number (NaN) if
 this object is infinity, or if the rounding mode is ERounding.None
 and the result is not exact.

### Quantize
    public EDecimal Quantize​(int desiredExponentInt, EContext ctx)
Returns an arbitrary-precision decimal number with the same value but a new
 exponent. <p>Note that this is not always the same as rounding to a
 given number of decimal places, since it can fail if the difference
 between this value's exponent and the desired exponent is too big,
 depending on the maximum precision. If rounding to a number of
 decimal places is desired, it's better to use the RoundToExponent
 and RoundToIntegral methods instead.</p> <p><b>Remark:</b> This
 method can be used to implement fixed-point decimal arithmetic, in
 which each decimal number has a fixed number of digits after the
 decimal point. The following code example returns a fixed-point
 number with up to 20 digits before and exactly 5 digits after the
 decimal point:</p> <pre>/* After performing arithmetic operations, adjust the number to 5 digits after the decimal point */ number = number.Quantize(-5, /* five digits after the decimal point */EContext.ForPrecision(25) /* 25-digit precision*/);</pre> <p>A
 fixed-point decimal arithmetic in which no digits come after the
  decimal point (a desired exponent of 0) is considered an "integer
  arithmetic".</p>

**Parameters:**

* <code>desiredExponentInt</code> - The desired exponent for the result. The exponent
 is the number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags). Can be null, in which case the default
 rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number with the same value as this
 object but with the exponent changed. Signals FlagInvalid and
 returns not-a-number (NaN) if this object is infinity, if the
 rounded result can't fit the given precision, or if the context
 defines an exponent range and the given exponent is outside that
 range.

### Quantize
    public EDecimal Quantize​(EDecimal otherValue, EContext ctx)
Returns an arbitrary-precision decimal number with the same value as this
 object but with the same exponent as another decimal number. <p>Note
 that this is not always the same as rounding to a given number of
 decimal places, since it can fail if the difference between this
 value's exponent and the desired exponent is too big, depending on
 the maximum precision. If rounding to a number of decimal places is
 desired, it's better to use the RoundToExponent and RoundToIntegral
 methods instead.</p> <p><b>Remark:</b> This method can be used to
 implement fixed-point decimal arithmetic, in which a fixed number of
 digits come after the decimal point. A fixed-point decimal
 arithmetic in which no digits come after the decimal point (a
  desired exponent of 0) is considered an "integer arithmetic" .</p>

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision decimal number containing the
 desired exponent of the result. The significand is ignored. The
 exponent is the number of fractional digits in the result, expressed
 as a negative number. Can also be positive, which eliminates
 lower-order places from the number. For example, -3 means round to
 the thousandth (10^-3, 0.0001), and 3 means round to the
 thousands-place (10^3, 1000). A value of 0 rounds the number to an
 integer. The following examples for this parameter express a desired
 exponent of 3: <code>10e3</code>, <code>8888e3</code>, <code>4.56e5</code>.

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags). Can be null, in which case the default
 rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number with the same value as this
 object but with the exponent changed. Signals FlagInvalid and
 returns not-a-number (NaN) if the result can't fit the given
 precision without rounding, or if the arithmetic context defines an
 exponent range and the given exponent is outside that range.

### Reduce
    public EDecimal Reduce​(EContext ctx)
Returns an object with the same numerical value as this one but with
 trailing zeros removed from its significand. For example, 1.00
 becomes 1. <p>If this object's value is 0, changes the exponent to
 0.</p>

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* This value with trailing zeros removed. Note that if the result has
 a very high exponent and the context says to clamp high exponents,
 there may still be some trailing zeros in the significand.

### Remainder
    public EDecimal Remainder​(EDecimal divisor, EContext ctx)
Returns the remainder that would result when this arbitrary-precision
 decimal floating-point number is divided by another
 arbitrary-precision decimal floating-point number. The remainder is
 the number that remains when the absolute value of this
 arbitrary-precision decimal floating-point number is divided (as
 though by DivideToIntegerZeroScale) by the absolute value of the
 other arbitrary-precision decimal floating-point number; the
 remainder has the same sign (positive or negative) as this
 arbitrary-precision decimal floating-point number.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result, and of the intermediate integer
 division. If <code>HasFlags</code> of the context is true, will also
 store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null, in which the
 precision is unlimited.

**Returns:**

* The remainder that would result when this arbitrary-precision
 decimal floating-point number is divided by another
 arbitrary-precision decimal floating-point number. Signals
 FlagDivideByZero and returns infinity if the divisor (this
 arbitrary-precision decimal floating-point number) is 0 and the
 dividend (the other arbitrary-precision decimal floating-point
 number) is nonzero. Signals FlagInvalid and returns not-a-number
 (NaN) if the divisor and the dividend are 0, or if the result of the
 division doesn't fit the given precision.

### RemainderNoRoundAfterDivide
    public EDecimal RemainderNoRoundAfterDivide​(EDecimal divisor, EContext ctx)
Finds the remainder that results when dividing two arbitrary-precision
 decimal numbers, except the intermediate division is not adjusted to
 fit the precision of the given arithmetic context. The value of this
 object is divided by the absolute value of the other object; the
 remainder has the same sign (positive or negative) as this object's
 value.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result, but not also of the intermediate
 integer division. If <code>HasFlags</code> of the context is true, will
 also store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null, in which the
 precision is unlimited.

**Returns:**

* The remainder of the two numbers. Signals FlagInvalid and returns
 not-a-number (NaN) if the divisor is 0, or if the result doesn't fit
 the given precision.

### RemainderNaturalScale
    public EDecimal RemainderNaturalScale​(EDecimal divisor)
Calculates the remainder of a number by the formula <code>"this" - (("this" /
  "divisor") * "divisor")</code>.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* An arbitrary-precision decimal number.

### RemainderNaturalScale
    public EDecimal RemainderNaturalScale​(EDecimal divisor, EContext ctx)
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
 additional rounding, other than the rounding down to an integer
 after division, is needed.

**Returns:**

* An arbitrary-precision decimal number.

### RemainderNear
    public EDecimal RemainderNear​(EDecimal divisor, EContext ctx)
Finds the distance to the closest multiple of the given divisor, based on
 the result of dividing this object's value by another object's
 value. <ul> <li>If this and the other object divide evenly, the
 result is 0.</li> <li>If the remainder's absolute value is less than
 half of the divisor's absolute value, the result has the same sign
 as this object and will be the distance to the closest
 multiple.</li> <li>If the remainder's absolute value is more than
 half of the divisor's absolute value, the result has the opposite
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
 rounding and exponent range settings of this context are ignored
 (the rounding mode is always treated as HalfEven). If <code>
 HasFlags</code> of the context is true, will also store the flags
 resulting from the operation (the flags are in addition to the
 pre-existing flags). Can be null, in which the precision is
 unlimited.

**Returns:**

* The distance of the closest multiple. Signals FlagInvalid and
 returns not-a-number (NaN) if the divisor is 0, or either the result
 of integer division (the quotient) or the remainder wouldn't fit the
 given precision.

### RoundToExponent
    public EDecimal RoundToExponent​(EInteger exponent, EContext ctx)
Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary. The resulting
 number's Exponent property will not necessarily be the given
 exponent; use the Quantize method instead to give the result a
 particular exponent.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable in the given precision. If the result can't fit the
 precision, additional digits are discarded to make it fit. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to the
 given exponent when rounding, and the given exponent is outside of
 the valid range of the arithmetic context.

### RoundToExponent
    public EDecimal RoundToExponent​(EInteger exponent)
Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the
 HalfEven rounding mode. The resulting number's Exponent property
 will not necessarily be the given exponent; use the Quantize method
 instead to give the result a particular exponent.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable for the given exponent.

### RoundToExponent
    public EDecimal RoundToExponent​(EInteger exponent, ERounding rounding)
Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the given
 rounding mode. The resulting number's Exponent property will not
 necessarily be the given exponent; use the Quantize method instead
 to give the result a particular exponent.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>rounding</code> - Desired mode for rounding this number's value.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable for the given exponent.

### RoundToExponent
    public EDecimal RoundToExponent​(int exponentSmall)
Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the
 HalfEven rounding mode. The resulting number's Exponent property
 will not necessarily be the given exponent; use the Quantize method
 instead to give the result a particular exponent.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable for the given exponent.

### RoundToExponent
    public EDecimal RoundToExponent​(int exponentSmall, EContext ctx)
Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary. The resulting
 number's Exponent property will not necessarily be the given
 exponent; use the Quantize method instead to give the result a
 particular exponent.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable in the given precision. If the result can't fit the
 precision, additional digits are discarded to make it fit. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to the
 given exponent when rounding, and the given exponent is outside of
 the valid range of the arithmetic context.

### RoundToExponent
    public EDecimal RoundToExponent​(int exponentSmall, ERounding rounding)
Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary. The resulting
 number's Exponent property will not necessarily be the given
 exponent; use the Quantize method instead to give the result a
 particular exponent.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>rounding</code> - The desired mode to use to round the given number to the
 given exponent.

**Returns:**

* An arbitrary-precision decimal number rounded to the given negative
 number of decimal places.

### RoundToExponentExact
    public EDecimal RoundToExponentExact​(EInteger exponent, EContext ctx)
Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as an
 arbitrary-precision integer, and signals an inexact flag if the
 result would be inexact. The resulting number's Exponent property
 will not necessarily be the given exponent; use the Quantize method
 instead to give the result a particular exponent.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable in the given precision. Signals FlagInvalid and
 returns not-a-number (NaN) if the result can't fit the given
 precision without rounding. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to the given exponent when
 rounding, and the given exponent is outside of the valid range of
 the arithmetic context.

### RoundToExponentExact
    public EDecimal RoundToExponentExact​(int exponentSmall, EContext ctx)
Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as a 32-bit
 signed integer, and signals an inexact flag if the result would be
 inexact. The resulting number's Exponent property will not
 necessarily be the given exponent; use the Quantize method instead
 to give the result a particular exponent.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable in the given precision. Signals FlagInvalid and
 returns not-a-number (NaN) if the result can't fit the given
 precision without rounding. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to the given exponent when
 rounding, and the given exponent is outside of the valid range of
 the arithmetic context.

### RoundToExponentExact
    public EDecimal RoundToExponentExact​(int exponentSmall, ERounding rounding)
Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as a 32-bit
 signed integer, and signals an inexact flag if the result would be
 inexact. The resulting number's Exponent property will not
 necessarily be the given exponent; use the Quantize method instead
 to give the result a particular exponent.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>rounding</code> - Desired mode for rounding this object's value.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable using the given exponent.

### RoundToIntegerExact
    public EDecimal RoundToIntegerExact​(EContext ctx)
Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to an integer, and signals an inexact flag if the
 result would be inexact. The resulting number's Exponent property
 will not necessarily be 0; use the Quantize method instead to give
 the result an exponent of 0.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest integer
 representable in the given precision. Signals FlagInvalid and
 returns not-a-number (NaN) if the result can't fit the given
 precision without rounding. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to 0 when rounding, and 0 is
 outside of the valid range of the arithmetic context.

### RoundToIntegerNoRoundedFlag
    public EDecimal RoundToIntegerNoRoundedFlag​(EContext ctx)
Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to an integer, without adding the
 <code>FlagInexact</code> or <code>FlagRounded</code> flags. The resulting
 number's Exponent property will not necessarily be 0; use the
 Quantize method instead to give the result an exponent of 0.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags), except that this function will never add
 the <code>FlagRounded</code> and <code>FlagInexact</code> flags (the only
 difference between this and RoundToExponentExact). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest integer
 representable in the given precision. If the result can't fit the
 precision, additional digits are discarded to make it fit. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to 0
 when rounding, and 0 is outside of the valid range of the arithmetic
 context.

### RoundToIntegralExact
    @Deprecated public EDecimal RoundToIntegralExact​(EContext ctx)
Deprecated.
Renamed to RoundToIntegerExact.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest integer
 representable in the given precision. Signals FlagInvalid and
 returns not-a-number (NaN) if the result can't fit the given
 precision without rounding. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to 0 when rounding, and 0 is
 outside of the valid range of the arithmetic context.

### RoundToIntegralNoRoundedFlag
    @Deprecated public EDecimal RoundToIntegralNoRoundedFlag​(EContext ctx)
Deprecated.
Renamed to RoundToIntegerNoRoundedFlag.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags), except that this function will never add
 the <code>FlagRounded</code> and <code>FlagInexact</code> flags (the only
 difference between this and RoundToExponentExact). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest integer
 representable in the given precision. If the result can't fit the
 precision, additional digits are discarded to make it fit. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to 0
 when rounding, and 0 is outside of the valid range of the arithmetic
 context.

### RoundToPrecision
    public EDecimal RoundToPrecision​(EContext ctx)
Rounds this object's value to a given precision, using the given rounding
 mode and range of exponent.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The closest value to this object's value, rounded to the specified
 precision. Returns the same value as this object if <code>ctx</code> is
 null or the precision and exponent range are unlimited.

### PreRound
    public EDecimal PreRound​(EContext ctx)
Returns a number in which the value of this object is rounded to fit the
 maximum precision allowed if it has more significant digits than the
 maximum precision. The maximum precision allowed is given in an
 arithmetic context. This method is designed for preparing operands
  to a custom arithmetic operation in accordance with the "simplified"
 arithmetic given in Appendix A of the General Decimal Arithmetic
 Specification.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited. Signals the flag LostDigits
 if the input number has greater precision than allowed and was
 rounded to a different numerical value in order to fit the
 precision.

**Returns:**

* This object rounded to the given precision. Returns this object and
 signals no flags if <code>ctx</code> is null or specifies an unlimited
 precision, if this object is infinity or not-a-number (including
 signaling NaN), or if the number's value has no more significant
 digits than the maximum precision given in <code>ctx</code>.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(int places)
Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>places</code> - The power of 10 to scale by.

**Returns:**

* A number whose exponent is increased by <code>places</code>. For example,
  if <code>places</code> is 5, "78E-2" becomes "78E+3" and has a bigger
 value.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(int places, EContext ctx)
Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>places</code> - The power of 10 to scale by.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* A number whose exponent is generally increased by <code>places</code>.
  For example, in general, if <code>places</code> is 5, "78E-2" becomes
  "78E+3" and has a bigger value.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(EInteger bigPlaces)
Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>bigPlaces</code> - The power of 10 to scale by.

**Returns:**

* A number whose exponent is increased by <code>bigPlaces</code>. For
  example, if <code>bigPlaces</code> is 5, "78E-2" becomes "78E+3" and has
 a bigger value.

### ScaleByPowerOfTen
    public EDecimal ScaleByPowerOfTen​(EInteger bigPlaces, EContext ctx)
Returns a number similar to this number but with its scale adjusted.

**Parameters:**

* <code>bigPlaces</code> - The power of 10 to scale by.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* A number whose exponent is generally increased by <code>bigPlaces</code>.
  For example, in general, if <code>bigPlaces</code> is 5, "78E-2" becomes
  "78E+3" and has a bigger value.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigPlaces</code> is null.

### Sqrt
    public EDecimal Sqrt​(EContext ctx)
Finds the square root of this object's value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
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
    @Deprecated public EDecimal SquareRoot​(EContext ctx)
Deprecated.
Renamed to Sqrt.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
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
    public EDecimal Subtract​(EDecimal otherValue)
Subtracts an arbitrary-precision decimal floating-point number from this
 arbitrary-precision decimal floating-point number and returns the
 result. The exponent for the result is the lower of this
 arbitrary-precision decimal floating-point number's exponent and the
 other arbitrary-precision decimal floating-point number's exponent.

**Parameters:**

* <code>otherValue</code> - The number to subtract from this instance's value.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision decimal floating-point number minus another
 arbitrary-precision decimal floating-point number. If this
 arbitrary-precision decimal floating-point number is not-a-number
 (NaN), returns NaN.

### Subtract
    public EDecimal Subtract​(EDecimal otherValue, EContext ctx)
Subtracts an arbitrary-precision decimal floating-point number from this
 arbitrary-precision decimal floating-point number and returns the
 result.

**Parameters:**

* <code>otherValue</code> - The number to subtract from this instance's value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision decimal floating-point number minus another
 arbitrary-precision decimal floating-point number. If this
 arbitrary-precision decimal floating-point number is not-a-number
 (NaN), returns NaN.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>otherValue</code> is null.

### ToDoubleBits
    public long ToDoubleBits()
Converts this value to its closest equivalent as a 64-bit floating-point
 number encoded in the IEEE 754 binary64 format, using the half-even
 rounding mode. <p>If this value is a NaN, sets the high bit of the
 binary64 value's significand area for a quiet NaN, and clears it for
 a signaling NaN. Then the other bits of the significand area are set
 to the lowest bits of this object's unsigned significand, and the
 next-highest bit of the significand area is set if those bits are
 all zeros and this is a signaling NaN.</p>

**Returns:**

* The closest 64-bit floating-point number to this value, encoded in
 the IEEE 754 binary64 format. The return value can be positive
 infinity or negative infinity, encoded in the IEEE 754 binary64
 format, if this value exceeds the range of a 64-bit floating point
 number.

### ToDouble
    public double ToDouble()
Converts this value to its closest equivalent as a 64-bit floating-point
 number, using the half-even rounding mode. <p>If this value is a
 NaN, sets the high bit of the 64-bit floating point number's
 significand area for a quiet NaN, and clears it for a signaling NaN.
 Then the other bits of the significand area are set to the lowest
 bits of this object's unsigned significand, and the next-highest bit
 of the significand area is set if those bits are all zeros and this
 is a signaling NaN. Unfortunately, in the.NET implementation, the
 return value of this method may be a quiet NaN even if a signaling
 NaN would otherwise be generated.</p>

**Returns:**

* The closest 64-bit floating-point number to this value. The return
 value can be positive infinity or negative infinity if this value
 exceeds the range of a 64-bit floating point number.

### ToEInteger
    public EInteger ToEInteger()
Converts this value to an arbitrary-precision integer, discarding the
 fractional part in this value. Note that depending on the value,
 especially the exponent, generating the arbitrary-precision integer
 may require a huge amount of memory. Use the ToSizedEInteger method
 to convert a number to an EInteger only if the integer fits in a
 bounded bit range; that method will throw an exception on overflow.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

* <code>java.lang.UnsupportedOperationException</code> - There is not enough memory to store the value
 as an EInteger.

### ToEIntegerExact
    @Deprecated public EInteger ToEIntegerExact()
Deprecated.
Renamed to ToEIntegerIfExact.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

* <code>java.lang.ArithmeticException</code> - This object's value is not an exact integer.

### ToEIntegerIfExact
    public EInteger ToEIntegerIfExact()
Converts this value to an arbitrary-precision integer, checking whether the
 fractional part of the value would be lost. Note that depending on
 the value, especially the exponent, generating the
 arbitrary-precision integer may require a huge amount of memory. Use
 the ToSizedEIntegerIfExact method to convert a number to an EInteger
 only if the integer fits in a bounded bit range; that method will
 throw an exception on overflow.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

* <code>java.lang.ArithmeticException</code> - This object's value is not an exact integer.

### ToEngineeringString
    public java.lang.String ToEngineeringString()
Same as toString(), except that when an exponent is used it will be a
 multiple of 3.

**Returns:**

* A text string.

### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat()
Deprecated.
Renamed to ToEFloat.

**Returns:**

* An arbitrary-precision binary floating-point number.

### ToEFloat
    public EFloat ToEFloat()
Creates a binary floating-point number from this object's value. Note that
 if the binary floating-point number contains a negative exponent,
 the resulting value might not be exact, in which case the resulting
 binary floating-point number will be an approximation of this
 decimal number's value, using the half-even rounding mode.

**Returns:**

* An arbitrary-precision binary floating-point number.

### ToPlainString
    public java.lang.String ToPlainString()
Converts this value to a string as though with the toString method, but
 without using exponential notation.

**Returns:**

* A text string.

### ToSingleBits
    public int ToSingleBits()
Converts this value to its closest equivalent as a 32-bit floating-point
 number encoded in the IEEE 754 binary32 format, using the half-even
 rounding mode. <p>If this value is a NaN, sets the high bit of the
 32-bit floating point number's significand area for a quiet NaN, and
 clears it for a signaling NaN. Then the other bits of the
 significand area are set to the lowest bits of this object's
 unsigned significand, and the next-highest bit of the significand
 area is set if those bits are all zeros and this is a signaling
 NaN.</p>

**Returns:**

* The closest 32-bit binary floating-point number to this value,
 encoded in the IEEE 754 binary32 format. The return value can be
 positive infinity or negative infinity if this value exceeds the
 range of a 32-bit floating point number.

### ToSingle
    public float ToSingle()
Converts this value to its closest equivalent as a 32-bit floating-point
 number, using the half-even rounding mode. <p>If this value is a
 NaN, sets the high bit of the 32-bit floating point number's
 significand area for a quiet NaN, and clears it for a signaling NaN.
 Then the other bits of the significand area are set to the lowest
 bits of this object's unsigned significand, and the next-highest bit
 of the significand area is set if those bits are all zeros and this
 is a signaling NaN. Unfortunately, in the.NET implementation, the
 return value of this method may be a quiet NaN even if a signaling
 NaN would otherwise be generated.</p>

**Returns:**

* The closest 32-bit binary floating-point number to this value. The
 return value can be positive infinity or negative infinity if this
 value exceeds the range of a 32-bit floating point number.

### toString
    public java.lang.String toString()
Converts this value to a string. Returns a value compatible with this
 class's FromString method.

**Overrides:**

* <code>toString</code> in class <code>java.lang.Object</code>

**Returns:**

* A string representation of this object. The text string will be in
 exponential notation if the exponent is greater than 0 or if the
 number's first nonzero digit is more than five digits after the
 decimal point.

### Ulp
    public EDecimal Ulp()
Returns the unit in the last place. The significand will be 1 and the
 exponent will be this number's exponent. Returns 1 with an exponent
 of 0 if this number is infinity or not-a-number (NaN).

**Returns:**

* An arbitrary-precision decimal number.

### ToSizedEInteger
    public EInteger ToSizedEInteger​(int maxBitLength)
Converts this value to an arbitrary-precision integer by discarding its
 fractional part and checking whether the resulting integer overflows
 the given signed bit count.

**Parameters:**

* <code>maxBitLength</code> - The maximum number of signed bits the integer can have.
 The integer's value may not be less than -(2^maxBitLength) or
 greater than (2^maxBitLength) - 1.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN), or this number's value, once converted to an integer by
 discarding its fractional part, is less than -(2^maxBitLength) or
 greater than (2^maxBitLength) - 1.

### ToSizedEIntegerIfExact
    public EInteger ToSizedEIntegerIfExact​(int maxBitLength)
Converts this value to an arbitrary-precision integer, only if this number's
 value is an exact integer and that integer does not overflow the
 given signed bit count.

**Parameters:**

* <code>maxBitLength</code> - The maximum number of signed bits the integer can have.
 The integer's value may not be less than -(2^maxBitLength) or
 greater than (2^maxBitLength) - 1.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN), or this number's value, once converted to an integer by
 discarding its fractional part, is less than -(2^maxBitLength) or
 greater than (2^maxBitLength) - 1.

* <code>java.lang.ArithmeticException</code> - This object's value is not an exact integer.

### ToEFloat
    public EFloat ToEFloat​(EContext ec)
Creates a binary floating-point number from this object's value. Note that
 if the binary floating-point number contains a negative exponent,
 the resulting value might not be exact, in which case the resulting
 binary floating-point number will be an approximation of this
 decimal number's value.

**Parameters:**

* <code>ec</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. The precision is in bits, and an
 example of this parameter is <code>EContext.Binary64</code>. Can be null.

**Returns:**

* An arbitrary-precision float floating-point number.

### Increment
    public EDecimal Increment()
Returns one added to this arbitrary-precision decimal number.

**Returns:**

* The given arbitrary-precision decimal number plus one.

### Decrement
    public EDecimal Decrement()
Returns one subtracted from this arbitrary-precision decimal number.

**Returns:**

* The given arbitrary-precision decimal number minus one.

### ToByteChecked
    public byte ToByteChecked()
Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) after converting it to an integer by discarding
 its fractional part.

**Returns:**

* This number's value, truncated to a byte (from 0 to 255).

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional
 part, is less than 0 or greater than 255.

### ToByteUnchecked
    public byte ToByteUnchecked()
Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a byte (from 0 to 255).

**Returns:**

* This number, converted to a byte (from 0 to 255). Returns 0 if this
 value is infinity or not-a-number.

### ToByteIfExact
    public byte ToByteIfExact()
Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) without rounding to a different numerical
 value.

**Returns:**

* This number's value as a byte (from 0 to 255).

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than 0 or greater than 255.

### FromByte
    public static EDecimal FromByte​(byte inputByte)
Converts a byte (from 0 to 255) to an arbitrary-precision decimal number.

**Parameters:**

* <code>inputByte</code> - The number to convert as a byte (from 0 to 255).

**Returns:**

* This number's value as an arbitrary-precision decimal number.

### ToInt16Checked
    public short ToInt16Checked()
Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer after converting it to an integer by
 discarding its fractional part.

**Returns:**

* This number's value, truncated to a 16-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional
 part, is less than -32768 or greater than 32767.

### ToInt16Unchecked
    public short ToInt16Unchecked()
Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 16-bit signed integer.

**Returns:**

* This number, converted to a 16-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### ToInt16IfExact
    public short ToInt16IfExact()
Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer without rounding to a different numerical
 value.

**Returns:**

* This number's value as a 16-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -32768 or greater than 32767.

### FromInt16
    public static EDecimal FromInt16​(short inputInt16)
Converts a 16-bit signed integer to an arbitrary-precision decimal number.

**Parameters:**

* <code>inputInt16</code> - The number to convert as a 16-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision decimal number.

### ToInt32Checked
    public int ToInt32Checked()
Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer after converting it to an integer by
 discarding its fractional part.

**Returns:**

* This number's value, truncated to a 32-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional
 part, is less than -2147483648 or greater than 2147483647.

### ToInt32Unchecked
    public int ToInt32Unchecked()
Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 32-bit signed integer.

**Returns:**

* This number, converted to a 32-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### ToInt32IfExact
    public int ToInt32IfExact()
Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer without rounding to a different numerical
 value.

**Returns:**

* This number's value as a 32-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -2147483648 or greater than
 2147483647.

### ToInt64Checked
    public long ToInt64Checked()
Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer after converting it to an integer by
 discarding its fractional part.

**Returns:**

* This number's value, truncated to a 64-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional
 part, is less than -9223372036854775808 or greater than
 9223372036854775807.

### ToInt64Unchecked
    public long ToInt64Unchecked()
Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 64-bit signed integer.

**Returns:**

* This number, converted to a 64-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### ToInt64IfExact
    public long ToInt64IfExact()
Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer without rounding to a different numerical
 value.

**Returns:**

* This number's value as a 64-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -9223372036854775808 or greater
 than 9223372036854775807.
