# com.upokecenter.numbers.EInteger

    public final class EInteger extends java.lang.Object implements java.lang.Comparable<EInteger>

Represents an arbitrary-precision integer. (The "E" stands for "extended",
 and has this prefix to group it with the other classes common to
 this library, particularly EDecimal, EFloat, and ERational.)
 <p>Instances of this class are immutable, so they are inherently
 safe for use by multiple threads. Multiple instances of this object
 with the same value are interchangeable, but they should be compared
  using the "Equals" method rather than the "==" operator.</p>
 <p><b>Security note</b></p> <p>It is not recommended to implement
 security-sensitive algorithms using the methods in this class, for
 several reasons:</p> <ul> <li><code>EInteger</code> objects are immutable,
 so they can't be modified, and the memory they occupy is not
 guaranteed to be cleared in a timely fashion due to garbage
 collection. This is relevant for applications that use many-bit-long
 numbers as secret parameters.</li> <li>The methods in this class
 (especially those that involve arithmetic) are not guaranteed to be
  "constant-time" (non-data-dependent) for all relevant inputs.
 Certain attacks that involve encrypted communications have exploited
 the timing and other aspects of such communications to derive keying
 material or cleartext indirectly.</li></ul> <p>Applications should
 instead use dedicated security libraries to handle big numbers in
 security-sensitive algorithms.</p>

## Methods

* `EInteger Abs()`<br>
 Returns the absolute value of this object's value.
* `EInteger Add​(int intValue)`<br>
 Not documented yet.
* `EInteger Add​(EInteger bigintAugend)`<br>
 Adds this object and another object.
* `EInteger And​(EInteger other)`<br>
 Does an AND operation between two arbitrary-precision integer values.
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
* `int compareTo​(EInteger other)`<br>
 Compares an arbitrary-precision integer with this instance.
* `EInteger Divide​(int intValue)`<br>
 Divides this instance by the value of an arbitrary-precision integer.
* `EInteger Divide​(EInteger bigintDivisor)`<br>
 Divides this instance by the value of an arbitrary-precision integer.
* `EInteger[] DivRem​(EInteger divisor)`<br>
 Divides this object by another arbitrary-precision integer and returns the
 quotient and remainder.
* `boolean equals​(java.lang.Object obj)`<br>
 Determines whether this object and another object are equal and have the
 same type.
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
* `static EInteger FromRadixString​(java.lang.String str,
               int radix)`<br>
 Converts a string to an arbitrary-precision integer in a given radix.
* `static EInteger FromRadixSubstring​(java.lang.String str,
                  int radix,
                  int index,
                  int endIndex)`<br>
 Converts a portion of a string to an arbitrary-precision integer in a given
 radix.
* `static EInteger FromString​(java.lang.String str)`<br>
 Converts a string to an arbitrary-precision integer.
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
* `int GetLowBit()`<br>
 Deprecated.
This method may overflow.
 This method may overflow.
* `EInteger GetLowBitAsEInteger()`<br>
 Gets the lowest set bit in this number's absolute value, in the form of an
 arbitrary-precision integer.
* `static EInteger getOne()`<br>
 Gets the number 1 as an arbitrary-precision integer.
* `boolean GetSignedBit​(int index) "Forms of numbers"`<br>
 Returns whether a bit is set in the two's-complement form (see "Forms of numbers") of this
 object' s value.
* `boolean GetSignedBit​(EInteger bigIndex) "Forms of numbers"`<br>
 Returns whether a bit is set in the two's-complement form (see "Forms of numbers") of this
 object' s value.
* `int GetSignedBitLength()`<br>
 Deprecated.
This method may overflow.
 This method may overflow.
* `EInteger GetSignedBitLengthAsEInteger()`<br>
 Finds the minimum number of bits needed to represent this object's value,
 except for its sign, in the form of an arbitrary-precision integer.
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
 value.
* `static EInteger getZero()`<br>
 Gets the number zero as an arbitrary-precision integer.
* `int hashCode()`<br>
 Returns the hash code for this instance.
* `boolean isEven()`<br>
 Gets a value indicating whether this value is even.
* `boolean isPowerOfTwo()`<br>
 Gets a value indicating whether this object's value is a power of two, and
 greater than 0.
* `boolean isZero()`<br>
 Gets a value indicating whether this value is 0.
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
 Multiplies this instance by the value of an arbitrary-precision integer
 object.
* `EInteger Multiply​(EInteger bigintMult)`<br>
 Multiplies this instance by the value of an arbitrary-precision integer
 object.
* `EInteger Negate()`<br>
 Gets the value of this object with the sign reversed.
* `EInteger Not()`<br>
 Returns an arbitrary-precision integer with every bit flipped from this one.
* `EInteger Or​(EInteger second)`<br>
 Does an OR operation between two arbitrary-precision integer
 instances.
* `EInteger Pow​(int powerSmall)`<br>
 Raises an arbitrary-precision integer to a power.
* `EInteger Pow​(EInteger bigPower)`<br>
 Raises an arbitrary-precision integer to a power.
* `EInteger PowBigIntVar​(EInteger power)`<br>
 Raises an arbitrary-precision integer to a power, which is given as another
 arbitrary-precision integer.
* `EInteger Remainder​(int intValue)`<br>
 Finds the remainder that results when this instance is divided by the value
 of an arbitrary-precision integer.
* `EInteger Remainder​(EInteger divisor)`<br>
 Finds the remainder that results when this instance is divided by the value
 of an arbitrary-precision integer.
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
 Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer.
* `EInteger Subtract​(EInteger subtrahend)`<br>
 Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer.
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
 Finds the exclusive "or" of two arbitrary-precision integer objects.

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

* <code>true</code> if this object' s value is a power of two, and greater
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

* The sign of this object' s value.

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
 the basic digits 0 to 9 (U + 0030 to U + 0039) and then the basic
 letters A to Z (U + 0041 to U + 005A). For example, 0-9 in radix 10, and
 0-9, then A-F in radix 16.

**Returns:**

* An arbitrary-precision integer with the same value as the given
 string.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

### FromRadixSubstring
    public static EInteger FromRadixSubstring​(java.lang.String str, int radix, int index, int endIndex)
Converts a portion of a string to an arbitrary-precision integer in a given
 radix.

**Parameters:**

* <code>str</code> - A text string. The desired portion of the string must contain
 only characters allowed by the given radix, except that it may start
  with a minus sign ("-", U+002D) to indicate a negative number. The
 desired portion is not allowed to contain white space characters,
 including spaces.

* <code>radix</code> - A base from 2 to 36. Depending on the radix, the string can use
 the basic digits 0 to 9 (U + 0030 to U + 0039) and then the basic
 letters A to Z (U + 0041 to U + 005A). For example, 0-9 in radix 10, and
 0-9, then A-F in radix 16.

* <code>index</code> - The index of the string that starts the string portion.

* <code>endIndex</code> - The index of the string that ends the string portion. The
 length will be index + endIndex - 1.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 string portion.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

* <code>java.lang.NumberFormatException</code> - The string portion is empty or in an invalid
 format.

* <code>java.lang.IllegalArgumentException</code> - Doesn't satisfy (endIndex - index) % 4 == 0".

### FromString
    public static EInteger FromString​(java.lang.String str)
Converts a string to an arbitrary-precision integer.

**Parameters:**

* <code>str</code> - A text string. The string must contain only basic digits 0 to 9
  (U+0030 to U+0039), except that it may start with a minus sign ("-",
 U + 002D) to indicate a negative number. The string is not allowed to
 contain white space characters, including spaces.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 string.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>str</code> is in an invalid
 format.

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

### FromSubstring
    public static EInteger FromSubstring​(java.lang.String str, int index, int endIndex)
Converts a portion of a string to an arbitrary-precision integer.

**Parameters:**

* <code>str</code> - A text string. The desired portion of the string must contain
 only basic digits 0 to 9 (U + 0030 to U + 0039), except that it may
  start with a minus sign ("-", U+002D) to indicate a negative number.
 The desired portion is not allowed to contain white space
 characters, including spaces.

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
Adds this object and another object.

**Parameters:**

* <code>bigintAugend</code> - Another arbitrary-precision integer.

**Returns:**

* The sum of the two objects.

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

### Add
    public EInteger Add​(int intValue)
Not documented yet.

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is not documented yet.

**Returns:**

* An arbitrary-precision integer.

### Subtract
    public EInteger Subtract​(int intValue)
Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer.

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is a 32-bit signed integer.

**Returns:**

* The difference of the two objects.

### Multiply
    public EInteger Multiply​(int intValue)
Multiplies this instance by the value of an arbitrary-precision integer
 object.<p> </p><pre>EInteger result =
  EInteger.FromString("5").Multiply(200);</pre> .

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is a 32-bit signed integer.

**Returns:**

* The product of the two numbers.

### Divide
    public EInteger Divide​(int intValue)
Divides this instance by the value of an arbitrary-precision integer. The
 result is rounded down (the fractional part is discarded). Except if
 the result is 0, it will be negative if this object is positive and
 the other is negative, or vice versa, and will be positive if both
 are positive or both are negative.

**Parameters:**

* <code>intValue</code> - The divisor.

**Returns:**

* The quotient of the two objects.

**Throws:**

* <code>java.lang.ArithmeticException</code> - Attempted to divide by zero.

### Remainder
    public EInteger Remainder​(int intValue)
Finds the remainder that results when this instance is divided by the value
 of an arbitrary-precision integer. The remainder is the value that
 remains when the absolute value of this object is divided by the
 absolute value of the other object; the remainder has the same sign
 (positive or negative) as this object.

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is a 32-bit signed integer.

**Returns:**

* The remainder of the two numbers.

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
Divides this instance by the value of an arbitrary-precision integer. The
 result is rounded down (the fractional part is discarded). Except if
 the result is 0, it will be negative if this object is positive and
 the other is negative, or vice versa, and will be positive if both
 are positive or both are negative.

**Parameters:**

* <code>bigintDivisor</code> - The divisor.

**Returns:**

* The quotient of the two objects.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigintDivisor</code> is null.

* <code>java.lang.ArithmeticException</code> - Attempted to divide by zero.

### DivRem
    public EInteger[] DivRem​(EInteger divisor)
Divides this object by another arbitrary-precision integer and returns the
 quotient and remainder.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* An array with two arbitrary-precision integers: the first is the
 quotient, and the second is the remainder.

**Throws:**

* <code>java.lang.ArithmeticException</code> - The parameter divisor is 0.

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
 common factor (GCF).

**Parameters:**

* <code>bigintSecond</code> - Another arbitrary-precision integer.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigintSecond</code> is null.

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

* <code>java.lang.ArithmeticException</code> - The return value would exceed the range of
 a 32-bit signed integer.

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

* The lowest bit set in the number, starting at 0. Returns -1 if this
 value is 0.

### GetLowBitAsEInteger
    public EInteger GetLowBitAsEInteger()
Gets the lowest set bit in this number's absolute value, in the form of an
 arbitrary-precision integer. (This will also be the lowest set bit
 in the number's two's-complement form (see <code>"Forms of numbers"</code>).).

**Returns:**

* The lowest bit set in the number, starting at 0. Returns -1 if this
 value is 0 or odd.

### GetSignedBit
    public boolean GetSignedBit​(EInteger bigIndex)
Returns whether a bit is set in the two's-complement form (see <code>"Forms of numbers"</code>) of this
 object' s value.

**Parameters:**

* <code>bigIndex</code> - The index, starting at zero, of the bit to test, where 0 is
 the least significant bit, 1 is the next least significant bit, and
 so on.

**Returns:**

* <code>true</code> if the given bit is set in the two' s-complement form
 (see <code>EDecimal</code>) of this object' s
 value; otherwise, <code>false</code>.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigIndex</code> is null.

### GetSignedBit
    public boolean GetSignedBit​(int index)
Returns whether a bit is set in the two's-complement form (see <code>"Forms of numbers"</code>) of this
 object' s value.

**Parameters:**

* <code>index</code> - The index, starting at 0, of the bit to test, where 0 is the
 least significant bit, 1 is the next least significant bit, and so
 on.

**Returns:**

* <code>true</code> if the given bit is set in the two' s-complement form
 (see <code>EDecimal</code>) of this object' s
 value; otherwise, <code>false</code>.

### GetSignedBitLengthAsEInteger
    public EInteger GetSignedBitLengthAsEInteger()
Finds the minimum number of bits needed to represent this object's value,
 except for its sign, in the form of an arbitrary-precision integer.
 If the value is negative, finds the number of bits in the value
 equal to this object's absolute value minus 1. For example, all
 integers in the interval [-(2^63), (2^63) - 1], which is the same as
 the range of integers in Java's and.getNET()'s <code>long</code> type, have a
 signed bit length of 63 or less, and all other integers have a
 signed bit length of greater than 63.

**Returns:**

* The number of bits in this object's value. Returns 0 if this
 object's value is 0 or negative 1.

### GetSignedBitLength
    @Deprecated public int GetSignedBitLength()
Deprecated.
This method may overflow. Use GetSignedBitLengthAsEInteger instead.

**Returns:**

* The number of bits in this object's value. Returns 0 if this
 object's value is 0 or negative 1.

**Throws:**

* <code>java.lang.ArithmeticException</code> - The return value would exceed the range of
 a 32-bit signed integer.

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
 value. For example, all integers in the interval [-((2^63) - 1),
 (2^63) - 1] have an unsigned bit length of 63 or less, and all other
 integers have an unsigned bit length of greater than 63. This
 interval is not the same as the range of integers in Java's
 and.getNET()'s <code>long</code> type.

**Returns:**

* The number of bits in this object's value. Returns 0 if this
 object's value is 0, and returns 1 if the value is negative 1.

### GetUnsignedBitLength
    @Deprecated public int GetUnsignedBitLength()
Deprecated.
This method may overflow. Use GetUnsignedBitLengthAsEInteger instead.

**Returns:**

* The number of bits in this object's value. Returns 0 if this
 object's value is 0, and returns 1 if the value is negative 1.

**Throws:**

* <code>java.lang.ArithmeticException</code> - The return value would exceed the range of
 a 32-bit signed integer.

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
Multiplies this instance by the value of an arbitrary-precision integer
 object.

**Parameters:**

* <code>bigintMult</code> - Another arbitrary-precision integer.

**Returns:**

* The product of the two numbers.

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
Finds the remainder that results when this instance is divided by the value
 of an arbitrary-precision integer. The remainder is the value that
 remains when the absolute value of this object is divided by the
 absolute value of the other object; the remainder has the same sign
 (positive or negative) as this object.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* The remainder of the two numbers.

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
 this is the same as shiftLeft with the absolute value of this
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
 this is the same as shiftRight with the absolute value of this
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
Returns an arbitrary-precision integer with every bit flipped from this one.

**Returns:**

* An arbitrary-precision integer.

### And
    public EInteger And​(EInteger other)
Does an AND operation between two arbitrary-precision integer values.<p>Each
 arbitrary-precision integer is treated as a two's-complement form
  (see <code>"Forms of numbers"</code>)
 for the purposes of this operator.</p>

**Parameters:**

* <code>other</code> - An arbitrary-precision integer.

**Returns:**

* An arbitrary-precision integer in which each bit is set if the
 corresponding bits of this integer and the other integer are both
 set.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>other</code> is null.

### Or
    public EInteger Or​(EInteger second)
Does an OR operation between two arbitrary-precision integer
 instances.<p>Each arbitrary-precision integer is treated as a
 two's-complement form (see <code>"Forms of numbers"</code>) for the purposes of this operator.</p>

**Parameters:**

* <code>second</code> - The second operand.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>second</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Doesn't satisfy biggerCount&gt;0; doesn't satisfy
 biggerCount == CountWords(result).

### Xor
    public EInteger Xor​(EInteger other)
Finds the exclusive "or" of two arbitrary-precision integer objects. <p>Each
 arbitrary-precision integer is treated as a two's-complement form
  (see <code>"Forms of numbers"</code>)
 for the purposes of this operator.</p>

**Parameters:**

* <code>other</code> - An arbitrary-precision integer.

**Returns:**

* An arbitrary-precision integer in which each bit is set if the
 corresponding bit is set in one input integer but not in the other.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>other</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Doesn't satisfy smallerCount ==
 CountWords(result).

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

### Subtract
    public EInteger Subtract​(EInteger subtrahend)
Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer.

**Parameters:**

* <code>subtrahend</code> - Another arbitrary-precision integer.

**Returns:**

* The difference of the two objects.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>subtrahend</code> is null.

### ToBytes
    public byte[] ToBytes​(boolean littleEndian)
Returns a byte array of this integer's value. The byte array will take the
 number's two' s-complement form (see <code>"Forms of numbers"</code>), using the
 fewest bytes necessary to store its value unambiguously. If this
 value is negative, the bits that appear beyond the most significant
 bit of the number will be all ones. The resulting byte array can be
 passed to the <code>FromBytes()</code> method (with the same byte order)
 to reconstruct this integer's value.

**Parameters:**

* <code>littleEndian</code> - Either <code>true</code> or <code>false</code>.

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
 two' s-complement form (see <code>"Forms of numbers"</code>) (in which case the return value might have a
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
 two' s-complement form (see <code>"Forms of numbers"</code>) (in which case the return value might have a
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
 digits 0 to 9 (U + 0030 to U + 0039) and then the basic letters A to Z
 (U + 0041 to U + 005A). For example, 0-9 in radix 10, and 0-9, then A-F
 in radix 16.

### toString
    public java.lang.String toString()
Converts this object to a text string in base 10.

**Overrides:**

* <code>toString</code> in class <code>java.lang.Object</code>

**Returns:**

* A string representation of this object. If negative, the string will
  begin with a minus sign ("-", U+002D). The string will use the basic
 digits 0 to 9 (U + 0030 to U + 0039).

### ToByteChecked
    public byte ToByteChecked()
Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255).

**Returns:**

* This number's value as a byte (from 0 to 255).

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is less than 0 or greater than
 255.

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

* <code>java.lang.ArithmeticException</code> - This value is less than -32768 or greater
 than 32767.

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
