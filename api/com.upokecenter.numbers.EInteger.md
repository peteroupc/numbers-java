# com.upokecenter.numbers.EInteger

    public final class EInteger extends java.lang.Object implements java.lang.Comparable<EInteger>

Represents an arbitrary-precision integer. (The "E" stands for "extended",
 and has this prefix to group it with the other classes common to this
 library, particularly EDecimal, EFloat, and ERational.) <p>Instances
 of this class are immutable, so they are inherently safe for use by
 multiple threads. Multiple instances of this object with the same
 value are interchangeable, but they should be compared using the
  "Equals" method rather than the "==" operator.</p> <p><b>Security
 note</b></p> <p>It is not recommended to implement security-sensitive
 algorithms using the methods in this class, for several reasons:</p>
 <ul> <li><code>EInteger</code> objects are immutable, so they can't be
 modified, and the memory they occupy is not guaranteed to be cleared
 in a timely fashion due to garbage collection. This is relevant for
 applications that use many-bit-long numbers as secret parameters.</li>
 <li>The methods in this class (especially those that involve
  arithmetic) are not guaranteed to be "constant-time"
 (non-data-dependent) for all relevant inputs. Certain attacks that
 involve encrypted communications have exploited the timing and other
 aspects of such communications to derive keying material or cleartext
 indirectly.</li></ul> <p>Applications should instead use dedicated
 security libraries to handle big numbers in security-sensitive
 algorithms.</p>

## Methods

* `EInteger Abs()`<br>
 Returns the absolute value of this object's value.
* `EInteger Add​(int intValue)`<br>
 Adds this arbitrary-precision integer and a 32-bit signed integer and
 returns the result.
* `EInteger Add​(long longValue)`<br>
 Adds this arbitrary-precision integer and a 64-bit signed integer and
 returns the result.
* `EInteger Add​(EInteger bigintAugend)`<br>
 Adds this arbitrary-precision integer and another arbitrary-precision
 integer and returns the result.
* `EInteger And​(EInteger other)`<br>
 Does an AND operation between this arbitrary-precision integer and another
 one.
* `EInteger AndNot​(EInteger second)`<br>
 Does an AND NOT operation between this arbitrary-precision integer and
 another one.
* `int AsInt32Checked()`<br>
 Deprecated.
Renamed to ToInt32Checked.
 Renamed to ToInt32Checked.
* `int AsInt32Unchecked()`<br>
 Deprecated.
Renamed to ToInt32Unchecked.
 Renamed to ToInt32Unchecked.
* `long AsInt64Checked()`<br>
 Deprecated.
Renamed to ToInt64Checked.
 Renamed to ToInt64Checked.
* `long AsInt64Unchecked()`<br>
 Deprecated.
Renamed to ToInt64Unchecked.
 Renamed to ToInt64Unchecked.
* `boolean CanFitInInt32()`<br>
 Returns whether this object's value can fit in a 32-bit signed integer.
* `boolean CanFitInInt64()`<br>
 Returns whether this object's value can fit in a 64-bit signed integer.
* `int compareTo​(int intValue)`<br>
 Compares an arbitrary-precision integer with this instance.
* `int compareTo​(long longValue)`<br>
 Compares an arbitrary-precision integer with this instance.
* `int compareTo​(EInteger other)`<br>
 Compares an arbitrary-precision integer with this instance.
* `EInteger Decrement()`<br>
 Returns one subtracted from this arbitrary-precision integer.
* `EInteger Divide​(int intValue)`<br>
 Divides this arbitrary-precision integer by a 32-bit signed integer and
 returns the result.
* `EInteger Divide​(long longValue)`<br>
 Divides this arbitrary-precision integer by a 64-bit signed integer and
 returns the result.
* `EInteger Divide​(EInteger bigintDivisor)`<br>
 Divides this arbitrary-precision integer by another arbitrary-precision
 integer and returns the result.
* `EInteger[] DivRem​(int intDivisor)`<br>
 Divides this arbitrary-precision integer by a 32-bit signed integer and
 returns a two-item array containing the result of the division and
 the remainder, in that order.
* `EInteger[] DivRem​(long intDivisor)`<br>
 Divides this arbitrary-precision integer by a 64-bit signed integer and
 returns a two-item array containing the result of the division and
 the remainder, in that order.
* `EInteger[] DivRem​(EInteger divisor)`<br>
 Divides this arbitrary-precision integer by another arbitrary-precision
 integer and returns a two-item array containing the result of the
 division and the remainder, in that order.
* `boolean equals​(java.lang.Object obj)`<br>
 Determines whether this object and another object are equal and have the
 same type.
* `EInteger Eqv​(EInteger second)`<br>
 Does an XOR NOT operation (or equivalence operation, EQV operation, or
 exclusive-OR NOT operation) between this arbitrary-precision integer
 and another one.
* `static EInteger FromBoolean​(boolean boolValue)`<br>
 Converts a boolean value (true or false) to an arbitrary-precision integer.
* `static EInteger FromByte​(byte inputByte)`<br>
 Converts a byte (from 0 to 255) to an arbitrary-precision integer.
* `static EInteger FromBytes​(byte[] bytes,
         boolean littleEndian)`<br>
 Initializes an arbitrary-precision integer from an array of bytes.
* `static EInteger FromInt16​(short inputInt16)`<br>
 Converts a 16-bit signed integer to an arbitrary-precision integer.
* `static EInteger FromInt32​(int intValue)`<br>
 Converts a 32-bit signed integer to an arbitrary-precision integer.
* `static EInteger FromInt64​(long longerValue)`<br>
 Converts a 64-bit signed integer to an arbitrary-precision integer.
* `static EInteger FromRadixString​(byte[] bytes,
               int radix)`<br>
 Converts a sequence of bytes (interpreted as text) to an arbitrary-precision
 integer in a given radix.
* `static EInteger FromRadixString​(char[] cs,
               int radix) char`<br>
 Converts a sequence of char s to an arbitrary-precision integer in a
 given radix.
* `static EInteger FromRadixString​(java.lang.String str,
               int radix)`<br>
 Converts a string to an arbitrary-precision integer in a given radix.
* `static EInteger FromRadixSubstring​(byte[] bytes,
                  int radix,
                  int index,
                  int endIndex)`<br>
 Converts a portion of a sequence of bytes (interpreted as text) to an
 arbitrary-precision integer in a given radix.
* `static EInteger FromRadixSubstring​(char[] cs,
                  int radix,
                  int index,
                  int endIndex) char`<br>
 Converts a portion of a sequence of char s to an arbitrary-precision
 integer in a given radix.
* `static EInteger FromRadixSubstring​(java.lang.String str,
                  int radix,
                  int index,
                  int endIndex)`<br>
 Converts a portion of a string to an arbitrary-precision integer in a given
 radix.
* `static EInteger FromString​(byte[] bytes)`<br>
 Converts a sequence of bytes (interpreted as text) to an arbitrary-precision
 integer.
* `static EInteger FromString​(char[] cs) char`<br>
 Converts a sequence of char s to an arbitrary-precision integer.
* `static EInteger FromString​(java.lang.String str)`<br>
 Converts a string to an arbitrary-precision integer.
* `static EInteger FromSubstring​(byte[] bytes,
             int index,
             int endIndex)`<br>
 Converts a portion of a sequence of bytes (interpreted as text) to an
 arbitrary-precision integer.
* `static EInteger FromSubstring​(char[] cs,
             int index,
             int endIndex) char`<br>
 Converts a portion of a sequence of char s to an arbitrary-precision
 integer.
* `static EInteger FromSubstring​(java.lang.String str,
             int index,
             int endIndex)`<br>
 Converts a portion of a string to an arbitrary-precision integer.
* `EInteger Gcd​(EInteger bigintSecond)`<br>
 Returns the greatest common divisor of this integer and the given integer.
* `int GetDigitCount()`<br>
 Deprecated.
This method may overflow.
 This method may overflow.
* `EInteger GetDigitCountAsEInteger()`<br>
 Returns the number of decimal digits used by this integer, in the form of an
 arbitrary-precision integer.
* `long GetDigitCountAsInt64()`<br>
 Returns the number of decimal digits used by this integer, in the form of a
 64-bit signed integer.
* `int GetLowBit()`<br>
 Deprecated.
This method may overflow.
 This method may overflow.
* `EInteger GetLowBitAsEInteger()`<br>
 Gets the bit position of the lowest set bit in this number's absolute value,
 in the form of an arbitrary-precision integer.
* `long GetLowBitAsInt64()`<br>
 Gets the bit position of the lowest set bit in this number's absolute value,
 in the form of a 64-bit signed integer.
* `static EInteger getOne()`<br>
 Gets the number 1 as an arbitrary-precision integer.
* `boolean GetSignedBit​(int index) "Forms of numbers"`<br>
 Returns whether a bit is set in the two's-complement form (see "Forms of numbers") of this
 object's value.
* `boolean GetSignedBit​(EInteger bigIndex) "Forms of numbers"`<br>
 Returns whether a bit is set in the two's-complement form (see "Forms of numbers") of this
 object's value.
* `int GetSignedBitLength()`<br>
 Deprecated.
This method may overflow.
 This method may overflow.
* `EInteger GetSignedBitLengthAsEInteger()`<br>
 Finds the minimum number of bits needed to represent this object's value,
 except for its sign, in the form of an arbitrary-precision integer.
* `long GetSignedBitLengthAsInt64()`<br>
 Finds the minimum number of bits needed to represent this object's value,
 except for its sign, in the form of a 64-bit signed integer.
* `static EInteger getTen()`<br>
 Gets the number 10 as an arbitrary-precision integer.
* `boolean GetUnsignedBit​(int index)`<br>
 Returns whether a bit is set in this number's absolute value.
* `boolean GetUnsignedBit​(EInteger bigIndex)`<br>
 Returns whether a bit is set in this number's absolute value.
* `int GetUnsignedBitLength()`<br>
 Deprecated.
This method may overflow.
 This method may overflow.
* `EInteger GetUnsignedBitLengthAsEInteger()`<br>
 Finds the minimum number of bits needed to represent this number's absolute
 value, in the form of an arbitrary-precision integer.
* `long GetUnsignedBitLengthAsInt64()`<br>
 Finds the minimum number of bits needed to represent this number's absolute
 value, in the form of a 64-bit signed integer.
* `static EInteger getZero()`<br>
 Gets the number zero as an arbitrary-precision integer.
* `int hashCode()`<br>
 Returns the hash code for this instance.
* `EInteger Imp​(EInteger second)`<br>
 Does an OR NOT operation (or implication or IMP operation) between this
 arbitrary-precision integer and another one.
* `EInteger Increment()`<br>
 Returns one added to this arbitrary-precision integer.
* `boolean isEven()`<br>
 Gets a value indicating whether this value is even.
* `boolean isPowerOfTwo()`<br>
 Gets a value indicating whether this object's value is a power of two, and
 greater than 0.
* `boolean isZero()`<br>
 Gets a value indicating whether this value is 0.
* `static EInteger Max​(EInteger first,
   EInteger second)`<br>
 Returns the greater of two arbitrary-precision integers.
* `static EInteger MaxMagnitude​(EInteger first,
            EInteger second)`<br>
 Of two arbitrary-precision integers, returns the one with the greater
 absolute value.
* `static EInteger Min​(EInteger first,
   EInteger second)`<br>
 Returns the smaller of two arbitrary-precision integers.
* `static EInteger MinMagnitude​(EInteger first,
            EInteger second)`<br>
 Of two arbitrary-precision integers, returns the one with the smaller
 absolute value.
* `EInteger Mod​(int smallDivisor)`<br>
 Finds the modulus remainder that results when this instance is divided by
 the value of another integer.
* `EInteger Mod​(EInteger divisor)`<br>
 Finds the modulus remainder that results when this instance is divided by
 the value of an arbitrary-precision integer.
* `EInteger ModPow​(EInteger pow,
      EInteger mod)`<br>
 Calculates the remainder when this arbitrary-precision integer raised to a
 certain power is divided by another arbitrary-precision integer.
* `EInteger Multiply​(int intValue)`<br>
 Multiplies this arbitrary-precision integer by a 32-bit signed integer and
  returns the result.
* `EInteger Multiply​(long longValue)`<br>
 Multiplies this arbitrary-precision integer by a 64-bit signed integer and
 returns the result.
* `EInteger Multiply​(EInteger bigintMult)`<br>
 Multiplies this arbitrary-precision integer by another arbitrary-precision
 integer and returns the result.
* `EInteger Negate()`<br>
 Gets the value of this object with the sign reversed.
* `EInteger Not()`<br>
 Returns an arbitrary-precision integer with every bit flipped from this one
 (also called an inversion or NOT operation).
* `EInteger Or​(EInteger second)`<br>
 Does an OR operation between this arbitrary-precision integer and another
 one.
* `EInteger OrNot​(EInteger second)`<br>
 Does an OR NOT operation (or implication or IMP operation) between this
 arbitrary-precision integer and another one.
* `EInteger Pow​(int powerSmall)`<br>
 Raises an arbitrary-precision integer to a power.
* `EInteger Pow​(EInteger bigPower)`<br>
 Raises an arbitrary-precision integer to a power.
* `EInteger PowBigIntVar​(EInteger power)`<br>
 Raises an arbitrary-precision integer to a power, which is given as another
 arbitrary-precision integer.
* `EInteger Remainder​(int intValue)`<br>
 Returns the remainder that would result when this arbitrary-precision
 integer is divided by a 32-bit signed integer.
* `EInteger Remainder​(long longValue)`<br>
 Returns the remainder that would result when this arbitrary-precision
 integer is divided by a 64-bit signed integer.
* `EInteger Remainder​(EInteger divisor)`<br>
 Returns the remainder that would result when this arbitrary-precision
 integer is divided by another arbitrary-precision integer.
* `EInteger Root​(int root)`<br>
 Finds the nth root of this instance's value, rounded down.
* `EInteger Root​(EInteger root)`<br>
 Finds the nth root of this instance's value, rounded down.
* `EInteger[] RootRem​(int root)`<br>
 Calculates the nth root and the remainder.
* `EInteger[] RootRem​(EInteger root)`<br>
 Calculates the nth root and the remainder.
* `EInteger ShiftLeft​(int numberBits)`<br>
 Returns an arbitrary-precision integer with the bits shifted to the left by
 a number of bits.
* `EInteger ShiftLeft​(EInteger eshift)`<br>
 Returns an arbitrary-precision integer with the bits shifted to the left by
 a number of bits given as an arbitrary-precision integer.
* `EInteger ShiftRight​(int numberBits)`<br>
 Returns an arbitrary-precision integer with the bits shifted to the right.
* `EInteger ShiftRight​(EInteger eshift)`<br>
 Returns an arbitrary-precision integer with the bits shifted to the right.
* `int signum()`<br>
 Gets the sign of this object's value.
* `EInteger Sqrt()`<br>
 Finds the square root of this instance's value, rounded down.
* `EInteger[] SqrtRem()`<br>
 Calculates the square root and the remainder.
* `EInteger Subtract​(int intValue)`<br>
 Subtracts a 32-bit signed integer from this arbitrary-precision integer and
 returns the result.
* `EInteger Subtract​(long longValue)`<br>
 Subtracts a 64-bit signed integer from this arbitrary-precision integer and
 returns the result.
* `EInteger Subtract​(EInteger subtrahend)`<br>
 Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer and returns the result.
* `byte ToByteChecked()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255).
* `byte[] ToBytes​(boolean littleEndian)`<br>
 Returns a byte array of this integer's value.
* `byte ToByteUnchecked()`<br>
 Converts this number to a byte (from 0 to 255), returning the
 least-significant bits of this number's two's-complement form.
* `short ToInt16Checked()`<br>
 Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer.
* `short ToInt16Unchecked()`<br>
 Converts this number to a 16-bit signed integer, returning the
 least-significant bits of this number's two's-complement form.
* `int ToInt32Checked()`<br>
 Converts this object's value to a 32-bit signed integer, throwing an
 exception if it can't fit.
* `int ToInt32Unchecked()`<br>
 Converts this object's value to a 32-bit signed integer.
* `long ToInt64Checked()`<br>
 Converts this object's value to a 64-bit signed integer, throwing an
 exception if it can't fit.
* `long ToInt64Unchecked()`<br>
 Converts this object's value to a 64-bit signed integer.
* `java.lang.String ToRadixString​(int radix)`<br>
 Generates a string representing the value of this object, in the given
 radix.
* `java.lang.String toString()`<br>
 Converts this object to a text string in base 10.
* `EInteger Xor​(EInteger other)`<br>
 Does an exclusive OR (XOR) operation between this arbitrary-precision
 integer and another one.
* `EInteger XorNot​(EInteger second)`<br>
 Does an XOR NOT operation (or equivalence operation, EQV operation, or
 exclusive-OR NOT operation) between this arbitrary-precision integer
 and another one.

## Method Details

### getOne
    public static EInteger getOne()
Gets the number 1 as an arbitrary-precision integer.

**Returns:**

* The number 1 as an arbitrary-precision integer.

### getTen
    public static EInteger getTen()
Gets the number 10 as an arbitrary-precision integer.

**Returns:**

* The number 10 as an arbitrary-precision integer.

### getZero
    public static EInteger getZero()
Gets the number zero as an arbitrary-precision integer.

**Returns:**

* The number zero as an arbitrary-precision integer.

### isEven
    public final boolean isEven()
Gets a value indicating whether this value is even.

**Returns:**

* <code>true</code> if this value is even; otherwise, <code>false</code>.

### isPowerOfTwo
    public final boolean isPowerOfTwo()
Gets a value indicating whether this object's value is a power of two, and
 greater than 0.

**Returns:**

* <code>true</code> if this object's value is a power of two, and greater
 than 0; otherwise, <code>false</code>.

### isZero
    public final boolean isZero()
Gets a value indicating whether this value is 0.

**Returns:**

* <code>true</code> if this value is 0; otherwise, <code>false</code>.

### signum
    public final int signum()
Gets the sign of this object's value.

**Returns:**

* The sign of this object's value.

### FromBytes
    public static EInteger FromBytes​(byte[] bytes, boolean littleEndian)
Initializes an arbitrary-precision integer from an array of bytes.

**Parameters:**

* <code>bytes</code> - A byte array consisting of the two's-complement form (see
  <code>"Forms of numbers"</code>) of the
 arbitrary-precision integer to create. The byte array is encoded
 using the following rules: <ul> <li>Positive numbers have the first
 byte's highest bit cleared, and negative numbers have the bit
 set.</li> <li>The last byte contains the lowest 8-bits, the
 next-to-last contains the next lowest 8 bits, and so on. For
 example, the number 300 can be encoded as <code>0x01, 0x2c</code> and 200
 as <code>0x00, 0xc8</code>. (Note that the second example contains a set
 high bit in <code>0xc8</code>, so an additional 0 is added at the start
 to ensure it's interpreted as positive.)</li> <li>To encode negative
 numbers, take the absolute value of the number, subtract by 1,
 encode the number into bytes, and toggle each bit of each byte. Any
 further bits that appear beyond the most significant bit of the
 number will be all ones. For example, the number -450 can be encoded
 as <code>0xfe, 0x70</code> and -52869 as <code>0xff, 0x31, 0x7b</code>. (Note
 that the second example contains a cleared high bit in <code>0x31,
 0x7B</code>, so an additional 0xff is added at the start to ensure it's
 interpreted as negative.)</li></ul> <p>For little-endian, the byte
 order is reversed from the byte order just discussed.</p>.

* <code>littleEndian</code> - If true, the byte order is little-endian, or
 least-significant-byte first. If false, the byte order is
 big-endian, or most-significant-byte first.

**Returns:**

* An arbitrary-precision integer. Returns 0 if the byte array's length
 is 0.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

### FromBoolean
    public static EInteger FromBoolean​(boolean boolValue)
Converts a boolean value (true or false) to an arbitrary-precision integer.

**Parameters:**

* <code>boolValue</code> - Either true or false.

**Returns:**

* The number 1 if <code>boolValue</code> is true; otherwise, 0.

### FromInt32
    public static EInteger FromInt32​(int intValue)
Converts a 32-bit signed integer to an arbitrary-precision integer.

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is a 32-bit signed integer.

**Returns:**

* An arbitrary-precision integer with the same value as the 64-bit
 number.

### FromInt64
    public static EInteger FromInt64​(long longerValue)
Converts a 64-bit signed integer to an arbitrary-precision integer.

**Parameters:**

* <code>longerValue</code> - The parameter <code>longerValue</code> is a 64-bit signed
 integer.

**Returns:**

* An arbitrary-precision integer with the same value as the 64-bit
 number.

### FromRadixString
    public static EInteger FromRadixString​(java.lang.String str, int radix)
Converts a string to an arbitrary-precision integer in a given radix.

**Parameters:**

* <code>str</code> - A string described by the FromRadixSubstring method.

* <code>radix</code> - A base from 2 to 36. Depending on the radix, the string can use
 the basic digits 0 to 9 (U+0030 to U+0039) and then the basic
 upper-case letters A to Z (U+0041 to U+005A). For example, 0-9 in
 radix 10, and 0-9, then A-F in radix 16. Where a basic upper-case
 letter A to Z is allowed in the string, the corresponding basic
 lower-case letter (U+0061 to U+007a) is allowed instead.

**Returns:**

* An arbitrary-precision integer with the same value as the given
 string.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

* <code>java.lang.NumberFormatException</code> - The string is empty or in an invalid format.

### FromRadixSubstring
    public static EInteger FromRadixSubstring​(java.lang.String str, int radix, int index, int endIndex)
Converts a portion of a string to an arbitrary-precision integer in a given
 radix.

**Parameters:**

* <code>str</code> - A text string. The desired portion of the string must contain
 only characters allowed by the given radix, except that it may start
  with a minus sign ("-", U+002D) to indicate a negative number. The
 desired portion is not allowed to contain white space characters,
 including spaces. The desired portion may start with any number of
 zeros.

* <code>radix</code> - A base from 2 to 36. Depending on the radix, the string can use
 the basic digits 0 to 9 (U+0030 to U+0039) and then the basic
 upper-case letters A to Z (U+0041 to U+005A). For example, 0-9 in
 radix 10, and 0-9, then A-F in radix 16. Where a basic upper-case
 letter A to Z is allowed in the string, the corresponding basic
 lower-case letter (U+0061 to U+007a) is allowed instead.

* <code>index</code> - The index of the string that starts the string portion.

* <code>endIndex</code> - The index of the string that ends the string portion. The
 length will be index + endIndex - 1.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 string portion.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

* <code>java.lang.NumberFormatException</code> - The string portion is empty or in an invalid format.

### FromSubstring
    public static EInteger FromSubstring​(char[] cs, int index, int endIndex)
Converts a portion of a sequence of <code>char</code> s to an arbitrary-precision
 integer.

**Parameters:**

* <code>cs</code> - A sequence of <code>char</code> s, the desired portion of which
 describes an integer in base-10 (decimal) form. The desired portion
 of the sequence of <code>char</code> s must contain only basic digits 0
 to 9 (U+0030 to U+0039), except that it may start with a minus sign
  ("-", U+002D) to indicate a negative number. The desired portion is
 not allowed to contain white space characters, including spaces. The
 desired portion may start with any number of zeros.

* <code>index</code> - The index of the sequence of <code>char</code> s that starts the
 desired portion.

* <code>endIndex</code> - The index of the sequence of <code>char</code> s that ends the
 desired portion. The length will be index + endIndex - 1.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 sequence of <code>char</code> s portion.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>index</code> is less than 0, <code>
 endIndex</code> is less than 0, or either is greater than the sequence's
 length, or <code>endIndex</code> is less than <code>index</code>.

* <code>java.lang.NullPointerException</code> - The parameter <code>cs</code> is null.

### FromString
    public static EInteger FromString​(char[] cs)
Converts a sequence of <code>char</code> s to an arbitrary-precision integer.

**Parameters:**

* <code>cs</code> - A sequence of <code>char</code> s describing an integer in base-10
 (decimal) form. The sequence must contain only basic digits 0 to 9
  (U+0030 to U+0039), except that it may start with a minus sign ("-",
 U+002D) to indicate a negative number. The sequence is not allowed
 to contain white space characters, including spaces. The sequence
 may start with any number of zeros.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 sequence of <code>char</code> s.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>cs</code> is in an invalid format.

* <code>java.lang.NullPointerException</code> - The parameter <code>cs</code> is null.

### FromRadixString
    public static EInteger FromRadixString​(char[] cs, int radix)
Converts a sequence of <code>char</code> s to an arbitrary-precision integer in a
 given radix.

**Parameters:**

* <code>cs</code> - A sequence of <code>char</code> s described by the FromRadixSubstring
 method.

* <code>radix</code> - A base from 2 to 36. Depending on the radix, the sequence of
 <code>char</code> s can use the basic digits 0 to 9 (U+0030 to U+0039)
 and then the basic upper-case letters A to Z (U+0041 to U+005A). For
 example, 0-9 in radix 10, and 0-9, then A-F in radix 16. Where a
 basic upper-case letter A to Z is allowed in the sequence of <code>
 char</code> s, the corresponding basic lower-case letter (U+0061 to
 U+007a) is allowed instead.

**Returns:**

* An arbitrary-precision integer with the same value as the given
 sequence of <code>char</code> s.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>cs</code> is null.

* <code>java.lang.NumberFormatException</code> - The sequence of <code>char</code> s is empty or in an
 invalid format.

### FromRadixSubstring
    public static EInteger FromRadixSubstring​(char[] cs, int radix, int index, int endIndex)
Converts a portion of a sequence of <code>char</code> s to an arbitrary-precision
 integer in a given radix.

**Parameters:**

* <code>cs</code> - A text sequence of <code>char</code> s. The desired portion of the
 sequence of <code>char</code> s must contain only characters allowed by
  the given radix, except that it may start with a minus sign ("-",
 U+002D) to indicate a negative number. The desired portion is not
 allowed to contain white space characters, including spaces. The
 desired portion may start with any number of zeros.

* <code>radix</code> - A base from 2 to 36. Depending on the radix, the sequence of
 <code>char</code> s can use the basic digits 0 to 9 (U+0030 to U+0039)
 and then the basic upper-case letters A to Z (U+0041 to U+005A). For
 example, 0-9 in radix 10, and 0-9, then A-F in radix 16. Where a
 basic upper-case letter A to Z is allowed in the sequence of <code>
 char</code> s, the corresponding basic lower-case letter (U+0061 to
 U+007a) is allowed instead.

* <code>index</code> - The index of the sequence of <code>char</code> s that starts the
 desired portion.

* <code>endIndex</code> - The index of the sequence of <code>char</code> s that ends the
 desired portion. The length will be index + endIndex - 1.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 sequence's portion.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>cs</code> is null.

* <code>java.lang.NumberFormatException</code> - The portion is empty or in an invalid format.

### FromSubstring
    public static EInteger FromSubstring​(byte[] bytes, int index, int endIndex)
Converts a portion of a sequence of bytes (interpreted as text) to an
 arbitrary-precision integer. Each byte in the sequence has to be a
 character in the Basic Latin range (0x00 to 0x7f or U+0000 to
 U+007F) of the Unicode Standard.

**Parameters:**

* <code>bytes</code> - A sequence of bytes (interpreted as text), the desired portion
 of which describes an integer in base-10 (decimal) form. The desired
 portion of the sequence of bytes (interpreted as text) must contain
 only basic digits 0 to 9 (U+0030 to U+0039), except that it may
  start with a minus sign ("-", U+002D) to indicate a negative number.
 The desired portion is not allowed to contain white space
 characters, including spaces. The desired portion may start with any
 number of zeros.

* <code>index</code> - The index of the sequence of bytes (interpreted as text) that
 starts the desired portion.

* <code>endIndex</code> - The index of the sequence of bytes (interpreted as text)
 that ends the desired portion. The length will be index + endIndex -
 1.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 sequence of bytes (interpreted as text) portion.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>index</code> is less than 0, <code>
 endIndex</code> is less than 0, or either is greater than the sequence's
 length, or <code>endIndex</code> is less than <code>index</code>.

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

### FromString
    public static EInteger FromString​(byte[] bytes)
Converts a sequence of bytes (interpreted as text) to an arbitrary-precision
 integer. Each byte in the sequence has to be a code point in the
 Basic Latin range (0x00 to 0x7f or U+0000 to U+007F) of the Unicode
 Standard.

**Parameters:**

* <code>bytes</code> - A sequence of bytes describing an integer in base-10 (decimal)
 form. The sequence must contain only basic digits 0 to 9 (U+0030 to
  U+0039), except that it may start with a minus sign ("-", U+002D) to
 indicate a negative number. The sequence is not allowed to contain
 white space characters, including spaces. The sequence may start
 with any number of zeros.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 sequence of bytes.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>bytes</code> is in an invalid format.

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

### FromRadixString
    public static EInteger FromRadixString​(byte[] bytes, int radix)
Converts a sequence of bytes (interpreted as text) to an arbitrary-precision
 integer in a given radix. Each byte in the sequence has to be a
 character in the Basic Latin range (0x00 to 0x7f or U+0000 to
 U+007F) of the Unicode Standard.

**Parameters:**

* <code>bytes</code> - A sequence of bytes (interpreted as text) described by the
 FromRadixSubstring method.

* <code>radix</code> - A base from 2 to 36. Depending on the radix, the sequence of
 bytes can use the basic digits 0 to 9 (U+0030 to U+0039) and then
 the basic upper-case letters A to Z (U+0041 to U+005A). For example,
 0-9 in radix 10, and 0-9, then A-F in radix 16. Where a basic
 upper-case letter A to Z is allowed in the sequence of bytes, the
 corresponding basic lower-case letter (U+0061 to U+007a) is allowed
 instead.

**Returns:**

* An arbitrary-precision integer with the same value as the given
 sequence of bytes.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

* <code>java.lang.NumberFormatException</code> - The sequence of bytes (interpreted as text) is empty
 or in an invalid format.

### FromRadixSubstring
    public static EInteger FromRadixSubstring​(byte[] bytes, int radix, int index, int endIndex)
Converts a portion of a sequence of bytes (interpreted as text) to an
 arbitrary-precision integer in a given radix. Each byte in the
 sequence has to be a character in the Basic Latin range (0x00 to
 0x7f or U+0000 to U+007F) of the Unicode Standard.

**Parameters:**

* <code>bytes</code> - A sequence of bytes (interpreted as text). The desired portion
 of the sequence of bytes (interpreted as text) must contain only
 characters allowed by the given radix, except that it may start with
  a minus sign ("-", U+002D) to indicate a negative number. The
 desired portion is not allowed to contain white space characters,
 including spaces. The desired portion may start with any number of
 zeros.

* <code>radix</code> - A base from 2 to 36. Depending on the radix, the sequence of
 bytes (interpreted as text) can use the basic digits 0 to 9 (U+0030
 to U+0039) and then the basic upper-case letters A to Z (U+0041 to
 U+005A). For example, 0-9 in radix 10, and 0-9, then A-F in radix
 16. Where a basic upper-case letter A to Z is allowed in the
 sequence of bytes (interpreted as text), the corresponding basic
 lower-case letter (U+0061 to U+007a) is allowed instead.

* <code>index</code> - The index of the sequence of bytes (interpreted as text) that
 starts the desired portion.

* <code>endIndex</code> - The index of the sequence of bytes (interpreted as text)
 that ends the desired portion. The length will be index + endIndex -
 1.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 sequence's portion.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

* <code>java.lang.NumberFormatException</code> - The portion is empty or in an invalid format.

### FromString
    public static EInteger FromString​(java.lang.String str)
Converts a string to an arbitrary-precision integer.

**Parameters:**

* <code>str</code> - A text string describing an integer in base-10 (decimal) form.
 The string must contain only basic digits 0 to 9 (U+0030 to U+0039),
  except that it may start with a minus sign ("-", U+002D) to indicate
 a negative number. The string is not allowed to contain white space
 characters, including spaces. The string may start with any number
 of zeros.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 string.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>str</code> is in an invalid format.

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

### FromSubstring
    public static EInteger FromSubstring​(java.lang.String str, int index, int endIndex)
Converts a portion of a string to an arbitrary-precision integer.

**Parameters:**

* <code>str</code> - A text string, the desired portion of which describes an integer
 in base-10 (decimal) form. The desired portion of the string must
 contain only basic digits 0 to 9 (U+0030 to U+0039), except that it
  may start with a minus sign ("-", U+002D) to indicate a negative
 number. The desired portion is not allowed to contain white space
 characters, including spaces. The desired portion may start with any
 number of zeros.

* <code>index</code> - The index of the string that starts the string portion.

* <code>endIndex</code> - The index of the string that ends the string portion. The
 length will be index + endIndex - 1.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 string portion.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>index</code> is less than 0, <code>
 endIndex</code> is less than 0, or either is greater than the string's
 length, or <code>endIndex</code> is less than <code>index</code>.

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

### Abs
    public EInteger Abs()
Returns the absolute value of this object's value.

**Returns:**

* This object's value with the sign removed.

### Add
    public EInteger Add​(EInteger bigintAugend)
Adds this arbitrary-precision integer and another arbitrary-precision
 integer and returns the result.

**Parameters:**

* <code>bigintAugend</code> - Another arbitrary-precision integer.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 integer plus another arbitrary-precision integer.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigintAugend</code> is null.

### AsInt32Checked
    @Deprecated public int AsInt32Checked()
Deprecated.
Renamed to ToInt32Checked.

**Returns:**

* A 32-bit signed integer.

### AsInt32Unchecked
    @Deprecated public int AsInt32Unchecked()
Deprecated.
Renamed to ToInt32Unchecked.

**Returns:**

* A 32-bit signed integer.

### AsInt64Checked
    @Deprecated public long AsInt64Checked()
Deprecated.
Renamed to ToInt64Checked.

**Returns:**

* A 64-bit signed integer.

### AsInt64Unchecked
    @Deprecated public long AsInt64Unchecked()
Deprecated.
Renamed to ToInt64Unchecked.

**Returns:**

* A 64-bit signed integer.

### CanFitInInt32
    public boolean CanFitInInt32()
Returns whether this object's value can fit in a 32-bit signed integer.

**Returns:**

* <code>true</code> if this object's value is from -2147483648 through
 2147483647; otherwise, <code>false</code>.

### CanFitInInt64
    public boolean CanFitInInt64()
Returns whether this object's value can fit in a 64-bit signed integer.

**Returns:**

* <code>true</code> if this object's value is from -9223372036854775808
 through 9223372036854775807; otherwise, <code>false</code>.

### compareTo
    public int compareTo​(EInteger other)
Compares an arbitrary-precision integer with this instance.

**Specified by:**

* <code>compareTo</code> in interface <code>java.lang.Comparable&lt;EInteger&gt;</code>

**Parameters:**

* <code>other</code> - The integer to compare to this value.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater. This
 implementation returns a positive number if.

### Max
    public static EInteger Max​(EInteger first, EInteger second)
Returns the greater of two arbitrary-precision integers.

**Parameters:**

* <code>first</code> - The first integer to compare.

* <code>second</code> - The second integer to compare.

**Returns:**

* The greater of the two integers.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### Min
    public static EInteger Min​(EInteger first, EInteger second)
Returns the smaller of two arbitrary-precision integers.

**Parameters:**

* <code>first</code> - The first integer to compare.

* <code>second</code> - The second integer to compare.

**Returns:**

* The smaller of the two integers.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### MaxMagnitude
    public static EInteger MaxMagnitude​(EInteger first, EInteger second)
Of two arbitrary-precision integers, returns the one with the greater
 absolute value. If both integers have the same absolute value, this
 method has the same effect as Max.

**Parameters:**

* <code>first</code> - The first integer to compare.

* <code>second</code> - The second integer to compare.

**Returns:**

* The integer with the greater absolute value.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### MinMagnitude
    public static EInteger MinMagnitude​(EInteger first, EInteger second)
Of two arbitrary-precision integers, returns the one with the smaller
 absolute value. If both integers have the same absolute value, this
 method has the same effect as Min.

**Parameters:**

* <code>first</code> - The first integer to compare.

* <code>second</code> - The second integer to compare.

**Returns:**

* The integer with the smaller absolute value.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### Add
    public EInteger Add​(int intValue)
Adds this arbitrary-precision integer and a 32-bit signed integer and
 returns the result.

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is a 32-bit signed integer.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 integer plus a 32-bit signed integer.

### Subtract
    public EInteger Subtract​(int intValue)
Subtracts a 32-bit signed integer from this arbitrary-precision integer and
 returns the result.

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is a 32-bit signed integer.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision integer minus a 32-bit signed integer.

### Multiply
    public EInteger Multiply​(int intValue)
Multiplies this arbitrary-precision integer by a 32-bit signed integer and
  returns the result.<p> </p><pre>EInteger result = EInteger.FromString("5").Multiply(200);</pre> .

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is a 32-bit signed integer.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 integer times a 32-bit signed integer.

### Divide
    public EInteger Divide​(int intValue)
Divides this arbitrary-precision integer by a 32-bit signed integer and
 returns the result. The result of the division is rounded down (the
 fractional part is discarded). Except if the result of the division
 is 0, it will be negative if this arbitrary-precision integer is
 positive and the other 32-bit signed integer is negative, or vice
 versa, and will be positive if both are positive or both are
 negative.

**Parameters:**

* <code>intValue</code> - The divisor.

**Returns:**

* The result of dividing this arbitrary-precision integer by a 32-bit
 signed integer. The result of the division is rounded down (the
 fractional part is discarded). Except if the result of the division
 is 0, it will be negative if this arbitrary-precision integer is
 positive and the other 32-bit signed integer is negative, or vice
 versa, and will be positive if both are positive or both are
 negative.

**Throws:**

* <code>java.lang.ArithmeticException</code> - Attempted to divide by zero.

### Remainder
    public EInteger Remainder​(int intValue)
Returns the remainder that would result when this arbitrary-precision
 integer is divided by a 32-bit signed integer. The remainder is the
 number that remains when the absolute value of this
 arbitrary-precision integer is divided by the absolute value of the
 other 32-bit signed integer; the remainder has the same sign
 (positive or negative) as this arbitrary-precision integer.

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is a 32-bit signed integer.

**Returns:**

* The remainder that would result when this arbitrary-precision
 integer is divided by a 32-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - Attempted to divide by zero.

* <code>java.lang.NullPointerException</code> - The parameter <code>intValue</code> is null.

### compareTo
    public int compareTo​(int intValue)
Compares an arbitrary-precision integer with this instance.

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is a 32-bit signed integer.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater.

### Divide
    public EInteger Divide​(EInteger bigintDivisor)
Divides this arbitrary-precision integer by another arbitrary-precision
 integer and returns the result. The result of the division is
 rounded down (the fractional part is discarded). Except if the
 result of the division is 0, it will be negative if this
 arbitrary-precision integer is positive and the other
 arbitrary-precision integer is negative, or vice versa, and will be
 positive if both are positive or both are negative.

**Parameters:**

* <code>bigintDivisor</code> - The divisor.

**Returns:**

* The result of dividing this arbitrary-precision integer by another
 arbitrary-precision integer. The result of the division is rounded
 down (the fractional part is discarded). Except if the result of the
 division is 0, it will be negative if this arbitrary-precision
 integer is positive and the other arbitrary-precision integer is
 negative, or vice versa, and will be positive if both are positive
 or both are negative.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigintDivisor</code> is null.

* <code>java.lang.ArithmeticException</code> - Attempted to divide by zero.

### DivRem
    public EInteger[] DivRem​(int intDivisor)
Divides this arbitrary-precision integer by a 32-bit signed integer and
 returns a two-item array containing the result of the division and
 the remainder, in that order. The result of the division is rounded
 down (the fractional part is discarded). Except if the result of the
 division is 0, it will be negative if this arbitrary-precision
 integer is positive and the other 32-bit signed integer is negative,
 or vice versa, and will be positive if both are positive or both are
 negative. The remainder is the number that remains when the absolute
 value of this arbitrary-precision integer is divided by the absolute
 value of the other 32-bit signed integer; the remainder has the same
 sign (positive or negative) as this arbitrary-precision integer.

**Parameters:**

* <code>intDivisor</code> - The number to divide by.

**Returns:**

* An array of two items: the first is the result of the division as an
 arbitrary-precision integer, and the second is the remainder as an
 arbitrary-precision integer. The result of division is the result of
 the Divide method on the two operands, and the remainder is the
 result of the Remainder method on the two operands.

**Throws:**

* <code>java.lang.ArithmeticException</code> - The parameter <code>intDivisor</code> is 0.

### Add
    public EInteger Add​(long longValue)
Adds this arbitrary-precision integer and a 64-bit signed integer and
 returns the result.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 integer plus a 64-bit signed integer.

### Subtract
    public EInteger Subtract​(long longValue)
Subtracts a 64-bit signed integer from this arbitrary-precision integer and
 returns the result.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision integer minus a 64-bit signed integer.

### Multiply
    public EInteger Multiply​(long longValue)
Multiplies this arbitrary-precision integer by a 64-bit signed integer and
 returns the result.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 integer times a 64-bit signed integer.

### Divide
    public EInteger Divide​(long longValue)
Divides this arbitrary-precision integer by a 64-bit signed integer and
 returns the result. The result of the division is rounded down (the
 fractional part is discarded). Except if the result of the division
 is 0, it will be negative if this arbitrary-precision integer is
 positive and the other 64-bit signed integer is negative, or vice
 versa, and will be positive if both are positive or both are
 negative.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The result of dividing this arbitrary-precision integer by a 64-bit
 signed integer. The result of the division is rounded down (the
 fractional part is discarded). Except if the result of the division
 is 0, it will be negative if this arbitrary-precision integer is
 positive and the other 64-bit signed integer is negative, or vice
 versa, and will be positive if both are positive or both are
 negative.

### Remainder
    public EInteger Remainder​(long longValue)
Returns the remainder that would result when this arbitrary-precision
 integer is divided by a 64-bit signed integer. The remainder is the
 number that remains when the absolute value of this
 arbitrary-precision integer is divided by the absolute value of the
 other 64-bit signed integer; the remainder has the same sign
 (positive or negative) as this arbitrary-precision integer.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The remainder that would result when this arbitrary-precision
 integer is divided by a 64-bit signed integer.

### compareTo
    public int compareTo​(long longValue)
Compares an arbitrary-precision integer with this instance.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater.

### DivRem
    public EInteger[] DivRem​(long intDivisor)
Divides this arbitrary-precision integer by a 64-bit signed integer and
 returns a two-item array containing the result of the division and
 the remainder, in that order. The result of the division is rounded
 down (the fractional part is discarded). Except if the result of the
 division is 0, it will be negative if this arbitrary-precision
 integer is positive and the other 64-bit signed integer is negative,
 or vice versa, and will be positive if both are positive or both are
 negative. The remainder is the number that remains when the absolute
 value of this arbitrary-precision integer is divided by the absolute
 value of the other 64-bit signed integer; the remainder has the same
 sign (positive or negative) as this arbitrary-precision integer.

**Parameters:**

* <code>intDivisor</code> - The parameter <code>intDivisor</code> is a 64-bit signed
 integer.

**Returns:**

* An array of two items: the first is the result of the division as an
 arbitrary-precision integer, and the second is the remainder as an
 arbitrary-precision integer. The result of division is the result of
 the Divide method on the two operands, and the remainder is the
 result of the Remainder method on the two operands.

### DivRem
    public EInteger[] DivRem​(EInteger divisor)
Divides this arbitrary-precision integer by another arbitrary-precision
 integer and returns a two-item array containing the result of the
 division and the remainder, in that order. The result of the
 division is rounded down (the fractional part is discarded). Except
 if the result of the division is 0, it will be negative if this
 arbitrary-precision integer is positive and the other
 arbitrary-precision integer is negative, or vice versa, and will be
 positive if both are positive or both are negative. The remainder is
 the number that remains when the absolute value of this
 arbitrary-precision integer is divided by the absolute value of the
 other arbitrary-precision integer; the remainder has the same sign
 (positive or negative) as this arbitrary-precision integer.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* An array of two items: the first is the result of the division as an
 arbitrary-precision integer, and the second is the remainder as an
 arbitrary-precision integer. The result of division is the result of
 the Divide method on the two operands, and the remainder is the
 result of the Remainder method on the two operands.

**Throws:**

* <code>java.lang.ArithmeticException</code> - The parameter <code>divisor</code> is 0.

* <code>java.lang.NullPointerException</code> - The parameter <code>divisor</code> is null.

### equals
    public boolean equals​(java.lang.Object obj)
Determines whether this object and another object are equal and have the
 same type.

**Overrides:**

* <code>equals</code> in class <code>java.lang.Object</code>

**Parameters:**

* <code>obj</code> - The parameter <code>obj</code> is an arbitrary object.

**Returns:**

* <code>true</code> if this object and another object are equal; otherwise,
 <code>false</code>.

### Gcd
    public EInteger Gcd​(EInteger bigintSecond)
Returns the greatest common divisor of this integer and the given integer.
 The greatest common divisor (GCD) is also known as the greatest
 common factor (GCF). This method works even if either or both
 integers are negative.

**Parameters:**

* <code>bigintSecond</code> - Another arbitrary-precision integer. Can be negative.

**Returns:**

* The greatest common divisor of this integer and the given integer.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigintSecond</code> is null.

* <code>java.lang.ArithmeticException</code> - Attempted to divide by zero.

### GetDigitCountAsEInteger
    public EInteger GetDigitCountAsEInteger()
Returns the number of decimal digits used by this integer, in the form of an
 arbitrary-precision integer.

**Returns:**

* The number of digits in the decimal form of this integer. Returns 1
 if this number is 0.

### GetDigitCount
    @Deprecated public int GetDigitCount()
Deprecated.
This method may overflow. Use GetDigitCountAsEInteger instead.

**Returns:**

* The number of digits in the decimal form of this integer. Returns 1
 if this number is 0.

**Throws:**

* <code>java.lang.ArithmeticException</code> - The return value would exceed the range of a
 32-bit signed integer.

### GetDigitCountAsInt64
    public long GetDigitCountAsInt64()
Returns the number of decimal digits used by this integer, in the form of a
 64-bit signed integer.

**Returns:**

* The number of digits in the decimal form of this integer. Returns 1
 if this number is 0. Returns 2^63 - 1(<code>Long.MAX_VALUE</code> in.NET
 or <code>Long.MAX_VALUE</code> in Java) if the number of decimal digits
 is 2^63 - 1 or greater. (Use <code>GetDigitCountAsEInteger</code> instead
 if the application relies on the exact number of decimal digits.).

### hashCode
    public int hashCode()
Returns the hash code for this instance. No application or process IDs are
 used in the hash code calculation.

**Overrides:**

* <code>hashCode</code> in class <code>java.lang.Object</code>

**Returns:**

* A 32-bit signed integer.

### GetLowBit
    @Deprecated public int GetLowBit()
Deprecated.
This method may overflow. Use GetLowBitAsEInteger instead.

**Returns:**

* The bit position of the lowest bit set in the number's absolute
 value, starting at 0. Returns -1 if this value is 0.

### GetLowBitAsInt64
    public long GetLowBitAsInt64()
Gets the bit position of the lowest set bit in this number's absolute value,
 in the form of a 64-bit signed integer. (This will also be the
 position of the lowest set bit in the number's two's-complement form
  (see <code>"Forms of numbers"</code>
).).

**Returns:**

* The bit position of the lowest bit set in the number's absolute
 value, starting at 0. Returns -1 if this value is 0 or odd. Returns
 2^63 - 1 (<code>Long.MAX_VALUE</code> in.NET or <code>Long.MAX_VALUE</code>
 in Java) if this number is other than zero but the lowest set bit is
 at 2^63 - 1 or greater. (Use <code>GetLowBitAsEInteger</code> instead if
 the application relies on the exact value of the lowest set bit
 position.).

### GetLowBitAsEInteger
    public EInteger GetLowBitAsEInteger()
Gets the bit position of the lowest set bit in this number's absolute value,
 in the form of an arbitrary-precision integer. (This will also be
 the position of the lowest set bit in the number's two's-complement
  form (see <code>"Forms of
  numbers"</code>).).

**Returns:**

* The bit position of the lowest bit set in the number's absolute
 value, starting at 0. Returns -1 if this value is 0 or odd.

### GetSignedBit
    public boolean GetSignedBit​(EInteger bigIndex)
Returns whether a bit is set in the two's-complement form (see <code>"Forms of numbers"</code>) of this
 object's value.

**Parameters:**

* <code>bigIndex</code> - The index, starting at zero, of the bit to test, where 0 is
 the least significant bit, 1 is the next least significant bit, and
 so on.

**Returns:**

* <code>true</code> if the given bit is set in the two' s-complement form
 (see <code>EDecimal</code>) of this object's
 value; otherwise, <code>false</code>.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigIndex</code> is null.

### GetSignedBit
    public boolean GetSignedBit​(int index)
Returns whether a bit is set in the two's-complement form (see <code>"Forms of numbers"</code>) of this
 object's value.

**Parameters:**

* <code>index</code> - The index, starting at 0, of the bit to test, where 0 is the
 least significant bit, 1 is the next least significant bit, and so
 on.

**Returns:**

* <code>true</code> if the given bit is set in the two' s-complement form
 (see <code>EDecimal</code>) of this object's
 value; otherwise, <code>false</code>.

### GetSignedBitLengthAsEInteger
    public EInteger GetSignedBitLengthAsEInteger()
Finds the minimum number of bits needed to represent this object's value,
 except for its sign, in the form of an arbitrary-precision integer.
 If the value is negative, finds the number of bits in the value
 equal to this object's absolute value minus 1. For example, all
 integers in the interval [-(2^63), (2^63) - 1], which is the same as
 the range of integers in Java's and.NET's <code>long</code> type, have a
 signed bit length of 63 or less, and all other integers have a
 signed bit length of greater than 63.

**Returns:**

* The number of bits in this object's value, except for its sign.
 Returns 0 if this object's value is 0 or negative 1.

### GetSignedBitLengthAsInt64
    public long GetSignedBitLengthAsInt64()
Finds the minimum number of bits needed to represent this object's value,
 except for its sign, in the form of a 64-bit signed integer. If the
 value is negative, finds the number of bits in the value equal to
 this object's absolute value minus 1. For example, all integers in
 the interval [-(2^63), (2^63) - 1], which is the same as the range
 of integers in Java's and.NET's <code>long</code> type, have a signed bit
 length of 63 or less, and all other integers have a signed bit
 length of greater than 63.

**Returns:**

* The number of bits in this object's value, except for its sign.
 Returns 0 if this object's value is 0 or negative 1. Returns 2^63 -
 1 (<code>Long.MAX_VALUE</code> in.NET or <code>Long.MAX_VALUE</code> in Java)
 if the number of bits is 2^63 - 1 or greater. (Use <code>
 GetUnsignedBitLengthAsEInteger</code> instead if the application relies on
 the exact number of bits.).

### GetSignedBitLength
    @Deprecated public int GetSignedBitLength()
Deprecated.
This method may overflow. Use GetSignedBitLengthAsEInteger instead.

**Returns:**

* The number of bits in this object's value, except for its sign.
 Returns 0 if this object's value is 0 or negative 1.

**Throws:**

* <code>java.lang.ArithmeticException</code> - The return value would exceed the range of a
 32-bit signed integer.

### GetUnsignedBit
    public boolean GetUnsignedBit​(EInteger bigIndex)
Returns whether a bit is set in this number's absolute value.

**Parameters:**

* <code>bigIndex</code> - The index, starting at zero, of the bit to test, where 0 is
 the least significant bit, 1 is the next least significant bit, and
 so on.

**Returns:**

* <code>true</code> if the given bit is set in this number's absolute
 value.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigIndex</code> is null.

### GetUnsignedBit
    public boolean GetUnsignedBit​(int index)
Returns whether a bit is set in this number's absolute value.

**Parameters:**

* <code>index</code> - The index, starting at 0, of the bit to test, where 0 is the
 least significant bit, 1 is the next least significant bit, and so
 on.

**Returns:**

* <code>true</code> if the given bit is set in this number's absolute
 value.

### GetUnsignedBitLengthAsEInteger
    public EInteger GetUnsignedBitLengthAsEInteger()
Finds the minimum number of bits needed to represent this number's absolute
 value, in the form of an arbitrary-precision integer. For example,
 all integers in the interval [-((2^63) - 1), (2^63) - 1] have an
 unsigned bit length of 63 or less, and all other integers have an
 unsigned bit length of greater than 63. This interval is not the
 same as the range of integers in Java's and.NET's <code>long</code> type.

**Returns:**

* The number of bits in this object's absolute value. Returns 0 if
 this object's value is 0, and returns 1 if the value is negative 1.

### GetUnsignedBitLengthAsInt64
    public long GetUnsignedBitLengthAsInt64()
Finds the minimum number of bits needed to represent this number's absolute
 value, in the form of a 64-bit signed integer. For example, all
 integers in the interval [-((2^63) - 1), (2^63) - 1] have an
 unsigned bit length of 63 or less, and all other integers have an
 unsigned bit length of greater than 63. This interval is not the
 same as the range of integers in Java's and.NET's <code>long</code> type.

**Returns:**

* The number of bits in this object's absolute value. Returns 0 if
 this object's value is 0, and returns 1 if the value is negative 1.
 Returns 2^63 - 1 (<code>Long.MAX_VALUE</code> in.NET or <code>
 Long.MAX_VALUE</code> in Java) if the number of bits is 2^63 - 1 or
 greater. (Use <code>GetUnsignedBitLengthAsEInteger</code> instead if the
 application relies on the exact number of bits.).

### GetUnsignedBitLength
    @Deprecated public int GetUnsignedBitLength()
Deprecated.
This method may overflow. Use GetUnsignedBitLengthAsEInteger instead.

**Returns:**

* The number of bits in this object's absolute value. Returns 0 if
 this object's value is 0, and returns 1 if the value is negative 1.

**Throws:**

* <code>java.lang.ArithmeticException</code> - The return value would exceed the range of a
 32-bit signed integer.

### Mod
    public EInteger Mod​(EInteger divisor)
Finds the modulus remainder that results when this instance is divided by
 the value of an arbitrary-precision integer. The modulus remainder
 is the same as the normal remainder if the normal remainder is
 positive, and equals divisor plus normal remainder if the normal
 remainder is negative.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>divisor</code> is less than 0.

* <code>java.lang.NullPointerException</code> - The parameter <code>divisor</code> is null.

### Mod
    public EInteger Mod​(int smallDivisor)
Finds the modulus remainder that results when this instance is divided by
 the value of another integer. The modulus remainder is the same as
 the normal remainder if the normal remainder is positive, and equals
 divisor plus normal remainder if the normal remainder is negative.

**Parameters:**

* <code>smallDivisor</code> - The divisor of the modulus.

**Returns:**

* The modulus remainder.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>smallDivisor</code> is less than 0.

### ModPow
    public EInteger ModPow​(EInteger pow, EInteger mod)
Calculates the remainder when this arbitrary-precision integer raised to a
 certain power is divided by another arbitrary-precision integer.

**Parameters:**

* <code>pow</code> - The power to raise this integer by.

* <code>mod</code> - The integer to divide the raised number by.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>pow</code> or <code>mod</code> is
 null.

### Multiply
    public EInteger Multiply​(EInteger bigintMult)
Multiplies this arbitrary-precision integer by another arbitrary-precision
 integer and returns the result.

**Parameters:**

* <code>bigintMult</code> - Another arbitrary-precision integer.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 integer times another arbitrary-precision integer.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigintMult</code> is null.

### Negate
    public EInteger Negate()
Gets the value of this object with the sign reversed.

**Returns:**

* This object's value with the sign reversed.

### Pow
    public EInteger Pow​(EInteger bigPower)
Raises an arbitrary-precision integer to a power.

**Parameters:**

* <code>bigPower</code> - The exponent to raise this integer to.

**Returns:**

* The result. Returns 1 if <code>bigPower</code> is 0.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigPower</code> is null.

* <code>java.lang.IllegalArgumentException</code> - BigPower is negative.

### Pow
    public EInteger Pow​(int powerSmall)
Raises an arbitrary-precision integer to a power.

**Parameters:**

* <code>powerSmall</code> - The exponent to raise this integer to.

**Returns:**

* The result. Returns 1 if <code>powerSmall</code> is 0.

### PowBigIntVar
    public EInteger PowBigIntVar​(EInteger power)
Raises an arbitrary-precision integer to a power, which is given as another
 arbitrary-precision integer.

**Parameters:**

* <code>power</code> - The exponent to raise to.

**Returns:**

* The result. Returns 1 if <code>power</code> is 0.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>power</code> is less than 0.

* <code>java.lang.NullPointerException</code> - The parameter <code>power</code> is null.

### Remainder
    public EInteger Remainder​(EInteger divisor)
Returns the remainder that would result when this arbitrary-precision
 integer is divided by another arbitrary-precision integer. The
 remainder is the number that remains when the absolute value of this
 arbitrary-precision integer is divided by the absolute value of the
 other arbitrary-precision integer; the remainder has the same sign
 (positive or negative) as this arbitrary-precision integer.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* The remainder that would result when this arbitrary-precision
 integer is divided by another arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - Attempted to divide by zero.

* <code>java.lang.NullPointerException</code> - The parameter <code>divisor</code> is null.

### ShiftRight
    public EInteger ShiftRight​(EInteger eshift)
Returns an arbitrary-precision integer with the bits shifted to the right.
 For this operation, the arbitrary-precision integer is treated as a
 two's-complement form (see <code>"Forms of numbers"</code>). Thus, for negative values, the
 arbitrary-precision integer is sign-extended.

**Parameters:**

* <code>eshift</code> - The number of bits to shift. Can be negative, in which case
 this is the same as ShiftLeft with the absolute value of this
 parameter.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>eshift</code> is null.

### ShiftLeft
    public EInteger ShiftLeft​(EInteger eshift)
Returns an arbitrary-precision integer with the bits shifted to the left by
 a number of bits given as an arbitrary-precision integer. A value of
 1 doubles this value, a value of 2 multiplies it by 4, a value of 3
 by 8, a value of 4 by 16, and so on.

**Parameters:**

* <code>eshift</code> - The number of bits to shift. Can be negative, in which case
 this is the same as ShiftRight with the absolute value of this
 parameter.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>eshift</code> is null.

### ShiftLeft
    public EInteger ShiftLeft​(int numberBits)
Returns an arbitrary-precision integer with the bits shifted to the left by
 a number of bits. A value of 1 doubles this value, a value of 2
 multiplies it by 4, a value of 3 by 8, a value of 4 by 16, and so
 on.

**Parameters:**

* <code>numberBits</code> - The number of bits to shift. Can be negative, in which
 case this is the same as shiftRight with the absolute value of this
 parameter.

**Returns:**

* An arbitrary-precision integer.

### Not
    public EInteger Not()
Returns an arbitrary-precision integer with every bit flipped from this one
 (also called an inversion or NOT operation).

**Returns:**

* An arbitrary-precision integer in which each bit in its two's
 complement representation is set if the corresponding bit of this
 integer is clear, and vice versa. Returns -1 if this integer is 0.
 If this integer is positive, the return value is negative, and vice
 versa. This method uses the two's complement form of negative
 integers (see <code>EDecimal</code>). For
 example, in binary, NOT 10100 =...11101011 (or in decimal, NOT 20 =
 -21). In binary, NOT...11100110 = 11001 (or in decimal, NOT -26 =
 25).

### And
    public EInteger And​(EInteger other)
Does an AND operation between this arbitrary-precision integer and another
 one.<p>Each arbitrary-precision integer is treated as a
 two's-complement form (see <code>"Forms of numbers"</code>) for the purposes of this operator.</p>

**Parameters:**

* <code>other</code> - Another arbitrary-precision integer that participates in the
 operation.

**Returns:**

* An arbitrary-precision integer in which each bit is set if the
 corresponding bits of this integer and the other integer (in their
 two's-complement representation) are both set. For example, in
 binary, 10110 AND 01100 = 00100 (or in decimal, 22 AND 12 = 4). This
 method uses the two's complement form of negative integers (see
 <code>EDecimal</code>). For example, in binary,
...11100111 AND 01100 = 00100 (or in decimal, -25 AND 12 = 4).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>other</code> is null.

### Or
    public EInteger Or​(EInteger second)
Does an OR operation between this arbitrary-precision integer and another
 one.<p>Each arbitrary-precision integer is treated as a
 two's-complement form (see <code>"Forms of numbers"</code>) for the purposes of this operator.</p>

**Parameters:**

* <code>second</code> - Another arbitrary-precision integer that participates in the
 operation.

**Returns:**

* An arbitrary-precision integer in which each bit is set if the
 corresponding bit of this integer is set, the other integer's
 corresponding bit is set, or both. For example, in binary, 10110 OR
 11010 = 11110 (or in decimal, 22 OR 26 = 30). This method uses the
 two's complement form of negative integers (see <code>EDecimal</code>). For example, in binary,
...11101110 OR 01011 =...11101111 (or in decimal, -18 OR 11 = -17).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>second</code> is null.

### AndNot
    public EInteger AndNot​(EInteger second)
Does an AND NOT operation between this arbitrary-precision integer and
 another one.<p>Each arbitrary-precision integer is treated as a
 two's-complement form (see <code>"Forms of numbers"</code>) for the purposes of this operator.</p>

**Parameters:**

* <code>second</code> - Another arbitrary-precision integer that participates in the
 operation.

**Returns:**

* An arbitrary-precision integer in which each bit is set if the
 corresponding bit of this integer is set, and the other integer's
 corresponding bit is not set. For example, in binary, 10110 AND NOT
 11010 = 00100 (or in decimal, 22 AND NOT 26 = 4). This method uses
 the two's complement form of negative integers (see <code>EDecimal</code>). For example, in binary,
...11101110 AND NOT 01011 = 00100 (or in decimal, -18 OR 11 = 4).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>second</code> is null.

### OrNot
    public EInteger OrNot​(EInteger second)
Does an OR NOT operation (or implication or IMP operation) between this
 arbitrary-precision integer and another one.<p>Each
 arbitrary-precision integer is treated as a two's-complement form
  (see <code>"Forms of numbers"</code>)
 for the purposes of this operator.</p>

**Parameters:**

* <code>second</code> - Another arbitrary-precision integer that participates in the
 operation.

**Returns:**

* An arbitrary-precision integer in which each bit is set if the
 corresponding bit of this integer is set, the other integer's
 corresponding bit is not set, or both. For example, in binary, 10110
 OR NOT 11010 = 00100 (or in decimal, 22 OR NOT 26 = 23). This method
 uses the two's complement form of negative integers (see <code>EDecimal</code>). For example, in binary,
...11101110 OR NOT 01011 =...11111110 (or in decimal, -18 OR 11 =
 -2).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>second</code> is null.

### Imp
    public EInteger Imp​(EInteger second)
Does an OR NOT operation (or implication or IMP operation) between this
 arbitrary-precision integer and another one.<p>Each
 arbitrary-precision integer is treated as a two's-complement form
  (see <code>"Forms of numbers"</code>)
 for the purposes of this operator.</p>

**Parameters:**

* <code>second</code> - Another arbitrary-precision integer that participates in the
 operation.

**Returns:**

* An arbitrary-precision integer in which each bit is set if the
 corresponding bit of this integer is set, the other integer's
 corresponding bit is not set, or both. For example, in binary, 10110
 OR NOT 11010 = 00100 (or in decimal, 22 OR NOT 26 = 23). This method
 uses the two's complement form of negative integers (see <code>EDecimal</code>). For example, in binary,
...11101110 OR NOT 01011 =...11111110 (or in decimal, -18 OR 11 =
 -2).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>second</code> is null.

### XorNot
    public EInteger XorNot​(EInteger second)
Does an XOR NOT operation (or equivalence operation, EQV operation, or
 exclusive-OR NOT operation) between this arbitrary-precision integer
 and another one.<p>Each arbitrary-precision integer is treated as a
 two's-complement form (see <code>"Forms of numbers"</code>) for the purposes of this operator.</p>

**Parameters:**

* <code>second</code> - Another arbitrary-precision integer that participates in the
 operation.

**Returns:**

* An arbitrary-precision integer in which each bit is set if the
 corresponding bit of this integer is set or the other integer's
 corresponding bit is not set, but not both. For example, in binary,
 10110 XOR NOT 11010 = 10011 (or in decimal, 22 XOR NOT 26 = 19).
 This method uses the two's complement form of negative integers (see
 <code>EDecimal</code>). For example, in binary,
...11101110 XOR NOT 01011 =...11111010 (or in decimal, -18 OR 11 =
 -6).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>second</code> is null.

### Eqv
    public EInteger Eqv​(EInteger second)
Does an XOR NOT operation (or equivalence operation, EQV operation, or
 exclusive-OR NOT operation) between this arbitrary-precision integer
 and another one.<p>Each arbitrary-precision integer is treated as a
 two's-complement form (see <code>"Forms of numbers"</code>) for the purposes of this operator.</p>

**Parameters:**

* <code>second</code> - Another arbitrary-precision integer that participates in the
 operation.

**Returns:**

* An arbitrary-precision integer in which each bit is set if the
 corresponding bit of this integer is set or the other integer's
 corresponding bit is not set, but not both. For example, in binary,
 10110 XOR NOT 11010 = 10011 (or in decimal, 22 XOR NOT 26 = 19).
 This method uses the two's complement form of negative integers (see
 <code>EDecimal</code>). For example, in binary,
...11101110 XOR NOT 01011 =...11111010 (or in decimal, -18 OR 11 =
 -6).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>second</code> is null.

### Xor
    public EInteger Xor​(EInteger other)
Does an exclusive OR (XOR) operation between this arbitrary-precision
 integer and another one.

**Parameters:**

* <code>other</code> - Another arbitrary-precision integer that participates in the
 operation.

**Returns:**

* An arbitrary-precision integer in which each bit is set if the
 corresponding bit is set in one input integer but not in the other.
 For example, in binary, 11010 XOR 01001 = 10011 (or in decimal, 26
 XOR 9 = 19). This method uses the two's complement form of negative
 integers (see <code>EDecimal</code>). For
 example, in binary, ...11101101 XOR 00011 =...11101110 (or in
 decimal, -19 XOR 3 = -18).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>other</code> is null.

### ShiftRight
    public EInteger ShiftRight​(int numberBits)
Returns an arbitrary-precision integer with the bits shifted to the right.
 For this operation, the arbitrary-precision integer is treated as a
 two's-complement form (see <code>"Forms of numbers"</code>). Thus, for negative values, the
 arbitrary-precision integer is sign-extended.

**Parameters:**

* <code>numberBits</code> - The number of bits to shift. Can be negative, in which
 case this is the same as shiftLeft with the absolute value of this
 parameter.

**Returns:**

* An arbitrary-precision integer.

### Sqrt
    public EInteger Sqrt()
Finds the square root of this instance's value, rounded down.

**Returns:**

* The square root of this object's value. Returns 0 if this value is 0
 or less.

### SqrtRem
    public EInteger[] SqrtRem()
Calculates the square root and the remainder.

**Returns:**

* An array of two arbitrary-precision integers: the first integer is
 the square root, and the second is the difference between this value
 and the square of the first integer. Returns two zeros if this value
 is 0 or less, or one and zero if this value equals 1.

### Root
    public EInteger Root​(EInteger root)
Finds the nth root of this instance's value, rounded down.

**Parameters:**

* <code>root</code> - The root to find; must be 1 or greater. If this value is 2, this
 method finds the square root; if 3, the cube root, and so on.

**Returns:**

* The square root of this object's value. Returns 0 if this value is 0
 or less.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>root</code> is null.

### RootRem
    public EInteger[] RootRem​(EInteger root)
Calculates the nth root and the remainder.

**Parameters:**

* <code>root</code> - The root to find; must be 1 or greater. If this value is 2, this
 method finds the square root; if 3, the cube root, and so on.

**Returns:**

* An array of two arbitrary-precision integers: the first integer is
 the nth root, and the second is the difference between this value
 and the nth power of the first integer. Returns two zeros if this
 value is 0 or less, or one and zero if this value equals 1.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>root</code> is null.

### Root
    public EInteger Root​(int root)
Finds the nth root of this instance's value, rounded down.

**Parameters:**

* <code>root</code> - The root to find; must be 1 or greater. If this value is 2, this
 method finds the square root; if 3, the cube root, and so on.

**Returns:**

* The square root of this object's value. Returns 0 if this value is 0
 or less.

### RootRem
    public EInteger[] RootRem​(int root)
Calculates the nth root and the remainder.

**Parameters:**

* <code>root</code> - The root to find; must be 1 or greater. If this value is 2, this
 method finds the square root; if 3, the cube root, and so on.

**Returns:**

* An array of two arbitrary-precision integers: the first integer is
 the nth root, and the second is the difference between this value
 and the nth power of the first integer. Returns two zeros if this
 value is 0 or less, or one and zero if this value equals 1.

### Subtract
    public EInteger Subtract​(EInteger subtrahend)
Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer and returns the result.

**Parameters:**

* <code>subtrahend</code> - Another arbitrary-precision integer.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision integer minus another arbitrary-precision
 integer.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>subtrahend</code> is null.

### ToBytes
    public byte[] ToBytes​(boolean littleEndian)
Returns a byte array of this integer's value. The byte array will take the
 number's two's-complement form (see <code>"Forms of numbers"</code>), using the
 fewest bytes necessary to store its value unambiguously. If this
 value is negative, the bits that appear beyond the most significant
 bit of the number will be all ones. The resulting byte array can be
 passed to the <code>FromBytes()</code> method (with the same byte order)
 to reconstruct this integer's value.

**Parameters:**

* <code>littleEndian</code> - See the 'littleEndian' parameter of the <code>
 FromBytes()</code> method.

**Returns:**

* A byte array. If this value is 0, returns a byte array with the
 single element 0.

### ToInt32Checked
    public int ToInt32Checked()
Converts this object's value to a 32-bit signed integer, throwing an
 exception if it can't fit.

**Returns:**

* A 32-bit signed integer.

### ToInt32Unchecked
    public int ToInt32Unchecked()
Converts this object's value to a 32-bit signed integer. If the value can't
 fit in a 32-bit integer, returns the lower 32 bits of this object's
 two's-complement form (see <code>"Forms of numbers"</code>) (in which case the return value might have a
 different sign than this object's value).

**Returns:**

* A 32-bit signed integer.

### ToInt64Checked
    public long ToInt64Checked()
Converts this object's value to a 64-bit signed integer, throwing an
 exception if it can't fit.

**Returns:**

* A 64-bit signed integer.

### ToInt64Unchecked
    public long ToInt64Unchecked()
Converts this object's value to a 64-bit signed integer. If the value can't
 fit in a 64-bit integer, returns the lower 64 bits of this object's
 two's-complement form (see <code>"Forms of numbers"</code>) (in which case the return value might have a
 different sign than this object's value).

**Returns:**

* A 64-bit signed integer.

### ToRadixString
    public java.lang.String ToRadixString​(int radix)
Generates a string representing the value of this object, in the given
 radix.

**Parameters:**

* <code>radix</code> - A radix from 2 through 36. For example, to generate a
 hexadecimal (base-16) string, specify 16. To generate a decimal
 (base-10) string, specify 10.

**Returns:**

* A string representing the value of this object. If this value is 0,
  returns "0". If negative, the string will begin with a minus sign
  ("-", U+002D). Depending on the radix, the string will use the basic
 digits 0 to 9 (U+0030 to U+0039) and then the basic upper-case
 letters A to Z (U+0041 to U+005A). For example, 0-9 in radix 10, and
 0-9, then A-F in radix 16.

### toString
    public java.lang.String toString()
Converts this object to a text string in base 10.

**Overrides:**

* <code>toString</code> in class <code>java.lang.Object</code>

**Returns:**

* A string representation of this object. If this value is 0, returns
  "0". If negative, the string will begin with a minus sign ("-",
 U+002D). The string will use the basic digits 0 to 9 (U+0030 to
 U+0039).

### Increment
    public EInteger Increment()
Returns one added to this arbitrary-precision integer.

**Returns:**

* The given arbitrary-precision integer plus one.

### Decrement
    public EInteger Decrement()
Returns one subtracted from this arbitrary-precision integer.

**Returns:**

* The given arbitrary-precision integer minus one.

### ToByteChecked
    public byte ToByteChecked()
Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255).

**Returns:**

* This number's value as a byte (from 0 to 255).

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is less than 0 or greater than 255.

### ToByteUnchecked
    public byte ToByteUnchecked()
Converts this number to a byte (from 0 to 255), returning the
 least-significant bits of this number's two's-complement form.

**Returns:**

* This number, converted to a byte (from 0 to 255).

### FromByte
    public static EInteger FromByte​(byte inputByte)
Converts a byte (from 0 to 255) to an arbitrary-precision integer.

**Parameters:**

* <code>inputByte</code> - The number to convert as a byte (from 0 to 255).

**Returns:**

* This number's value as an arbitrary-precision integer.

### ToInt16Checked
    public short ToInt16Checked()
Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer.

**Returns:**

* This number's value as a 16-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is less than -32768 or greater than
 32767.

### ToInt16Unchecked
    public short ToInt16Unchecked()
Converts this number to a 16-bit signed integer, returning the
 least-significant bits of this number's two's-complement form.

**Returns:**

* This number, converted to a 16-bit signed integer.

### FromInt16
    public static EInteger FromInt16​(short inputInt16)
Converts a 16-bit signed integer to an arbitrary-precision integer.

**Parameters:**

* <code>inputInt16</code> - The number to convert as a 16-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision integer.
