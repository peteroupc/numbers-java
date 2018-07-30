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
