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
 An integer that&#39;s always 0 or greater, with the following maximum
 values:</p> <ul><li>8-bit unsigned integer, or <i>byte</i> :
 255.</li> <li>16-bit unsigned integer: 65535.</li> <li>32-bit
 unsigned integer: (2 <sup>32</sup> -1).</li> <li>64-bit unsigned
 integer: (2 <sup>64</sup> -1).</li> </ul> <p><b>Signed integer</b> :
 An integer in <i>two&#39;s complement form</i> , with the following
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
 IEEE 754 format):</p> <pre>|C|BBB...BBB|AAAAAA...AAAAAA|</pre>
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
 highest bit is one, it&#39;s a negative number.</li> </ul> <p>The
 elements described above are in the same order as the order of each
 bit of each element, that is, either most significant first or least
 significant first.</p>
