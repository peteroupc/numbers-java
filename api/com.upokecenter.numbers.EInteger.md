# com.upokecenter.numbers.EInteger

    public final class EInteger extends Object implements Comparable<EInteger>

Represents an arbitrary-precision integer. (The "E" stands for "extended",
 and has this prefix to group it with the other classes common to this
 library, particularly EDecimal, EFloat, and ERational.) <p>Instances
 of this class are immutable, so they are inherently safe for use by
 multiple threads. Multiple instances of this object with the same
 value are interchangeable, but they should be compared using the
 "Equals" method rather than the "==" operator.</p>

## Methods

* `EInteger Abs()`<br>
 Returns the absolute value of this object's value.
* `EInteger Add(EInteger bigintAugend)`<br>
 Adds this object and another object.
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
* `int compareTo(EInteger other)`<br>
 Compares an arbitrary-precision integer with this instance.
* `EInteger Divide(EInteger bigintDivisor)`<br>
 Divides this instance by the value of an arbitrary-precision integer.
* `EInteger[] DivRem(EInteger divisor)`<br>
 Divides this object by another arbitrary-precision integer and returns the
 quotient and remainder.
* `boolean equals(Object obj)`<br>
 Determines whether this object and another object are equal.
* `static EInteger FromByte(byte inputByte)`<br>
 Converts a byte (from 0 to 255) to an arbitrary-precision integer.
* `static EInteger FromBytes(byte[] bytes,
         boolean littleEndian)`<br>
 Initializes an arbitrary-precision integer from an array of bytes.
* `static EInteger FromInt16(short inputInt16)`<br>
 Converts a 16-bit signed integer to an arbitrary-precision integer.
* `static EInteger FromInt32(int intValue)`<br>
 Converts a 32-bit signed integer to an arbitrary-precision integer.
* `static EInteger FromInt64(long longerValue)`<br>
 Converts a 64-bit signed integer to an arbitrary-precision integer.
* `static EInteger FromRadixString(String str,
               int radix)`<br>
 Converts a string to an arbitrary-precision integer.
* `static EInteger FromRadixSubstring(String str,
                  int radix,
                  int index,
                  int endIndex)`<br>
 Converts a portion of a string to an arbitrary-precision integer in a given
 radix.
* `static EInteger FromString(String str)`<br>
 Converts a string to an arbitrary-precision integer.
* `static EInteger FromSubstring(String str,
             int index,
             int endIndex)`<br>
 Converts a portion of a string to an arbitrary-precision integer.
* `EInteger Gcd(EInteger bigintSecond)`<br>
 Returns the greatest common divisor of two integers.
* `int GetDigitCount()`<br>
 Not documented yet.
* `int GetLowBit()`<br>
 Gets the lowest set bit in this number's absolute value.
* `EInteger GetLowBitAsEInteger()`<br>
 Gets the lowest set bit in this number's absolute value.
* `static EInteger getOne()`<br>
 Gets the number 1 as an arbitrary-precision integer.
* `boolean GetSignedBit(int index) "Forms of numbers"`<br>
 Returns whether a bit is set in the two's-complement form (see "Forms of numbers") of this
 object's value.
* `int GetSignedBitLength()`<br>
 Finds the minimum number of bits needed to represent this object's
 value, except for its sign.
* `static EInteger getTen()`<br>
 Gets the number 10 as an arbitrary-precision integer.
* `boolean GetUnsignedBit(int index)`<br>
 Returns whether a bit is set in this number's absolute value.
* `int GetUnsignedBitLength()`<br>
 Finds the minimum number of bits needed to represent this number's
 absolute value.
* `EInteger GetUnsignedBitLengthAsEInteger()`<br>
 Finds the minimum number of bits needed to represent this number's
 absolute value.
* `static EInteger getZero()`<br>
 Gets a value not documented yet.
* `int hashCode()`<br>
 Returns the hash code for this instance.
* `boolean isEven()`<br>
 Gets a value indicating whether this value is even.
* `boolean isPowerOfTwo()`<br>
 Gets a value indicating whether this object's value is a power of two.
* `boolean isZero()`<br>
 Gets a value indicating whether this value is 0.
* `EInteger Mod(EInteger divisor)`<br>
 Finds the modulus remainder that results when this instance is divided by
 the value of an arbitrary-precision integer.
* `EInteger ModPow(EInteger pow,
      EInteger mod)`<br>
 Calculates the remainder when an arbitrary-precision integer raised to a
 certain power is divided by another arbitrary-precision integer.
* `EInteger Multiply(EInteger bigintMult)`<br>
 Multiplies this instance by the value of an arbitrary-precision integer
 object.
* `EInteger Negate()`<br>
 Gets the value of this object with the sign reversed.
* `EInteger Pow(int powerSmall)`<br>
 Raises an arbitrary-precision integer to a power.
* `EInteger PowBigIntVar(EInteger power)`<br>
 Raises an arbitrary-precision integer to a power, which is given as another
 arbitrary-precision integer.
* `EInteger Remainder(EInteger divisor)`<br>
 Finds the remainder that results when this instance is divided by the value
 of an arbitrary-precision integer.
* `EInteger ShiftLeft(int numberBits)`<br>
 Returns an arbitrary-precision integer with the bits shifted to the left by
 a number of bits.
* `EInteger ShiftRight(int numberBits)`<br>
 Returns an arbitrary-precision integer with the bits shifted to the right.
* `int signum()`<br>
 Gets the sign of this object's value.
* `EInteger Sqrt()`<br>
 Finds the square root of this instance's value, rounded down.
* `EInteger[] SqrtRem()`<br>
 Calculates the square root and the remainder.
* `EInteger Subtract(EInteger subtrahend)`<br>
 Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer.
* `byte ToByteChecked()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255).
* `byte[] ToBytes(boolean littleEndian)`<br>
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
* `String ToRadixString(int radix)`<br>
 Generates a string representing the value of this object, in the given
 radix.
* `String toString()`<br>
 Converts this object to a text string in base 10.

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
Gets a value not documented yet.

**Returns:**

* A value not documented yet.

### isEven
    public final boolean isEven()
Gets a value indicating whether this value is even.

**Returns:**

* <code>true</code> if this value is even; otherwise, <code>false</code>.

### isPowerOfTwo
    public final boolean isPowerOfTwo()
Gets a value indicating whether this object&#x27;s value is a power of two.

**Returns:**

* <code>true</code> if this object's value is a power of two; otherwise,
 <code>false</code> .

### isZero
    public final boolean isZero()
Gets a value indicating whether this value is 0.

**Returns:**

* <code>true</code> if this value is 0; otherwise, <code>false</code>.

### signum
    public final int signum()
Gets the sign of this object's value.

**Returns:**

* 0 if this value is zero; -1 if this value is negative, or 1 if this
 value is positive.

### FromBytes
    public static EInteger FromBytes(byte[] bytes, boolean littleEndian)
Initializes an arbitrary-precision integer from an array of bytes.

**Parameters:**

* <code>bytes</code> - A byte array consisting of the two's-complement form (see
 <code>"Forms of numbers"</code>) of the
 arbitrary-precision integer to create. The byte array is encoded
 using the following rules: <ul> <li>Positive numbers have the first
 byte's highest bit cleared, and negative numbers have the bit
 set.</li> <li>The last byte contains the lowest 8-bits, the
 next-to-last contains the next lowest 8 bits, and so on. For example,
 the number 300 can be encoded as <code>0x01, 0x2c</code> and 200 as <code>0x00, 0xc8</code>. (Note that the second example contains a set high bit in
 <code>0xC8</code>, so an additional 0 is added at the start to ensure it's
 interpreted as positive.)</li> <li>To encode negative numbers, take
 the absolute value of the number, subtract by 1, encode the number
 into bytes, and toggle each bit of each byte. Any further bits that
 appear beyond the most significant bit of the number will be all
 ones. For example, the number -450 can be encoded as <code>0xfe,
 0x70</code> and -52869 as <code>0xff, 0x31, 0x7b</code>. (Note that the second
 example contains a cleared high bit in <code>0x31, 0x7b</code>, so an
 additional 0xFF is added at the start to ensure it's interpreted as
 negative.)</li></ul> <p>For little-endian, the byte order is reversed
 from the byte order just discussed.</p>.

* <code>littleEndian</code> - If true, the byte order is little-endian, or
 least-significant-byte first. If false, the byte order is big-endian,
 or most-significant-byte first.

**Returns:**

* An arbitrary-precision integer. Returns 0 if the byte array's length
 is 0.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>bytes</code> is null.

### FromInt32
    public static EInteger FromInt32(int intValue)
Converts a 32-bit signed integer to an arbitrary-precision integer.

**Parameters:**

* <code>intValue</code> - A 32-bit signed integer.

**Returns:**

* An arbitrary-precision integer with the same value as the 64-bit
 number.

### FromInt64
    public static EInteger FromInt64(long longerValue)
Converts a 64-bit signed integer to an arbitrary-precision integer.

**Parameters:**

* <code>longerValue</code> - A 64-bit signed integer.

**Returns:**

* An arbitrary-precision integer with the same value as the 64-bit
 number.

### FromRadixString
    public static EInteger FromRadixString(String str, int radix)
Converts a string to an arbitrary-precision integer.

**Parameters:**

* <code>str</code> - A text string. The string must contain only characters allowed by
 the given radix, except that it may start with a minus sign ("-",
 U + 002D) to indicate a negative number. The string is not allowed to
 contain white space characters, including spaces.

* <code>radix</code> - A base from 2 to 36. Depending on the radix, the string can use
 the basic digits 0 to 9 (U + 0030 to U + 0039) and then the basic letters
 A to Z (U + 0041 to U + 005A). For example, 0-9 in radix 10, and 0-9,
 then A-F in radix 16.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 string.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>IllegalArgumentException</code> - The parameter <code>radix</code> is less than 2
 or greater than 36.

* <code>NumberFormatException</code> - The string is empty or in an invalid format.

### FromRadixSubstring
    public static EInteger FromRadixSubstring(String str, int radix, int index, int endIndex)
Converts a portion of a string to an arbitrary-precision integer in a given
 radix.

**Parameters:**

* <code>str</code> - A text string. The desired portion of the string must contain
 only characters allowed by the given radix, except that it may start
 with a minus sign ("-", U+002D) to indicate a negative number. The
 desired portion is not allowed to contain white space characters,
 including spaces.

* <code>radix</code> - A base from 2 to 36. Depending on the radix, the string can use
 the basic digits 0 to 9 (U + 0030 to U + 0039) and then the basic letters
 A to Z (U + 0041 to U + 005A). For example, 0-9 in radix 10, and 0-9,
 then A-F in radix 16.

* <code>index</code> - The index of the string that starts the string portion.

* <code>endIndex</code> - The index of the string that ends the string portion. The
 length will be index + endIndex - 1.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 string portion.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>IllegalArgumentException</code> - The parameter <code>index</code> is less than 0,
 <code>endIndex</code> is less than 0, or either is greater than the
 string's length, or <code>endIndex</code> is less than <code>index</code>.

* <code>NumberFormatException</code> - The string portion is empty or in an invalid
 format.

### FromString
    public static EInteger FromString(String str)
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

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>NumberFormatException</code> - The parameter <code>str</code> is in an invalid
 format.

### FromSubstring
    public static EInteger FromSubstring(String str, int index, int endIndex)
Converts a portion of a string to an arbitrary-precision integer.

**Parameters:**

* <code>str</code> - A text string. The desired portion of the string must contain
 only basic digits 0 to 9 (U + 0030 to U + 0039), except that it may start
 with a minus sign ("-", U+002D) to indicate a negative number. The
 desired portion is not allowed to contain white space characters,
 including spaces.

* <code>index</code> - The index of the string that starts the string portion.

* <code>endIndex</code> - The index of the string that ends the string portion. The
 length will be index + endIndex - 1.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 string portion.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>IllegalArgumentException</code> - The parameter <code>index</code> is less than 0,
 <code>endIndex</code> is less than 0, or either is greater than the
 string's length, or <code>endIndex</code> is less than <code>index</code>.

* <code>NumberFormatException</code> - The string portion is empty or in an invalid
 format.

### Abs
    public EInteger Abs()
Returns the absolute value of this object's value.

**Returns:**

* This object's value with the sign removed.

### Add
    public EInteger Add(EInteger bigintAugend)
Adds this object and another object.

**Parameters:**

* <code>bigintAugend</code> - Another arbitrary-precision integer.

**Returns:**

* The sum of the two objects.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>bigintAugend</code> is
 null.

### AsInt32Checked
    @Deprecated public int AsInt32Checked()
Deprecated.&nbsp;<i>Renamed to ToInt32Checked.</i>

**Returns:**

* A 32-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is too big to fit a
 32-bit signed integer.

### AsInt32Unchecked
    @Deprecated public int AsInt32Unchecked()
Deprecated.&nbsp;<i>Renamed to ToInt32Unchecked.</i>

**Returns:**

* A 32-bit signed integer.

### AsInt64Checked
    @Deprecated public long AsInt64Checked()
Deprecated.&nbsp;<i>Renamed to ToInt64Checked.</i>

**Returns:**

* A 64-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is too big to fit a
 64-bit signed integer.

### AsInt64Unchecked
    @Deprecated public long AsInt64Unchecked()
Deprecated.&nbsp;<i>Renamed to ToInt64Unchecked.</i>

**Returns:**

* A 64-bit signed integer.

### CanFitInInt32
    public boolean CanFitInInt32()
Returns whether this object's value can fit in a 32-bit signed integer.

**Returns:**

* <code>true</code> if this object's value is Integer.MIN_VALUE or greater,
 and Integer.MAX_VALUE or less; otherwise, false .

### CanFitInInt64
    public boolean CanFitInInt64()
Returns whether this object's value can fit in a 64-bit signed integer.

**Returns:**

* <code>true</code> if this object's value is Long.MIN_VALUE or greater,
 and Long.MAX_VALUE or less; otherwise, false .

### compareTo
    public int compareTo(EInteger other)
Compares an arbitrary-precision integer with this instance.

**Specified by:**

* <code>compareTo</code>&nbsp;in interface&nbsp;<code>Comparable&lt;EInteger&gt;</code>

**Parameters:**

* <code>other</code> - The parameter <code>other</code> is not documented yet.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater.

### Divide
    public EInteger Divide(EInteger bigintDivisor)
Divides this instance by the value of an arbitrary-precision integer. The
 result is rounded down (the fractional part is discarded). Except if
 the result is 0, it will be negative if this object is positive and
 the other is negative, or vice versa, and will be positive if both
 are positive or both are negative.

**Parameters:**

* <code>bigintDivisor</code> - Another arbitrary-precision integer.

**Returns:**

* The quotient of the two objects.

**Throws:**

* <code>ArithmeticException</code> - The parameter <code>bigintDivisor</code> is
 zero.

* <code>NullPointerException</code> - The parameter <code>bigintDivisor</code> is
 null.

### DivRem
    public EInteger[] DivRem(EInteger divisor)
Divides this object by another arbitrary-precision integer and returns the
 quotient and remainder.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* An array with two arbitrary-precision integers: the first is the
 quotient, and the second is the remainder.

**Throws:**

* <code>NullPointerException</code> - The parameter divisor is null.

* <code>ArithmeticException</code> - The parameter divisor is 0.

* <code>ArithmeticException</code> - Attempted to divide by zero.

### equals
    public boolean equals(Object obj)
Determines whether this object and another object are equal.

**Overrides:**

* <code>equals</code>&nbsp;in class&nbsp;<code>Object</code>

**Parameters:**

* <code>obj</code> - An arbitrary object.

**Returns:**

* <code>true</code> if this object and another object are equal; otherwise,
 false .

### Gcd
    public EInteger Gcd(EInteger bigintSecond)
Returns the greatest common divisor of two integers. The greatest common
 divisor (GCD) is also known as the greatest common factor (GCF).

**Parameters:**

* <code>bigintSecond</code> - Another arbitrary-precision integer.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>bigintSecond</code> is
 null.

### GetDigitCount
    public int GetDigitCount()
Not documented yet.

**Returns:**

* A 32-bit signed integer.

### hashCode
    public int hashCode()
Returns the hash code for this instance.

**Overrides:**

* <code>hashCode</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A 32-bit signed integer.

### GetLowBit
    public int GetLowBit()
Gets the lowest set bit in this number's absolute value. (This will also be
 the lowest set bit in the number's two's-complement form (see <code>"Forms of numbers"</code>).).

**Returns:**

* The lowest bit set in the number, starting at 0. Returns -1 if this
 value is 0 or odd.

### GetLowBitAsEInteger
    public EInteger GetLowBitAsEInteger()
Gets the lowest set bit in this number's absolute value. (This will also be
 the lowest set bit in the number's two's-complement form (see <code>"Forms of numbers"</code>).).

**Returns:**

* The lowest bit set in the number, starting at 0. Returns -1 if this
 value is 0 or odd.

### GetSignedBit
    public boolean GetSignedBit(int index)
Returns whether a bit is set in the two's-complement form (see <code>"Forms of numbers"</code>) of this
 object's value.

**Parameters:**

* <code>index</code> - Zero based index of the bit to test. 0 means the least
 significant bit.

**Returns:**

* <code>true</code> if a bit is set in the two's-complement form (see
 <code>EDecimal</code>) of this object's value;
 otherwise, false .

### GetSignedBitLength
    public int GetSignedBitLength()
Finds the minimum number of bits needed to represent this object&#x27;s
 value, except for its sign. If the value is negative, finds the
 number of bits in the value equal to this object's absolute value
 minus 1.

**Returns:**

* The number of bits in this object's value. Returns 0 if this
 object's value is 0 or negative 1.

### GetUnsignedBit
    public boolean GetUnsignedBit(int index)
Returns whether a bit is set in this number's absolute value.

**Parameters:**

* <code>index</code> - Zero based index of the bit to test. 0 means the least
 significant bit.

**Returns:**

* <code>true</code> if a bit is set in this number's absolute value.

### GetUnsignedBitLengthAsEInteger
    public EInteger GetUnsignedBitLengthAsEInteger()
Finds the minimum number of bits needed to represent this number&#x27;s
 absolute value.

**Returns:**

* The number of bits in this object's value. Returns 0 if this
 object's value is 0, and returns 1 if the value is negative 1.

### GetUnsignedBitLength
    public int GetUnsignedBitLength()
Finds the minimum number of bits needed to represent this number&#x27;s
 absolute value.

**Returns:**

* The number of bits in this object's value. Returns 0 if this
 object's value is 0, and returns 1 if the value is negative 1.

### Mod
    public EInteger Mod(EInteger divisor)
Finds the modulus remainder that results when this instance is divided by
 the value of an arbitrary-precision integer. The modulus remainder is
 the same as the normal remainder if the normal remainder is positive,
 and equals divisor plus normal remainder if the normal remainder is
 negative.

**Parameters:**

* <code>divisor</code> - A divisor greater than 0 (the modulus).

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>ArithmeticException</code> - The parameter <code>divisor</code> is negative.

* <code>NullPointerException</code> - The parameter <code>divisor</code> is null.

### ModPow
    public EInteger ModPow(EInteger pow, EInteger mod)
Calculates the remainder when an arbitrary-precision integer raised to a
 certain power is divided by another arbitrary-precision integer.

**Parameters:**

* <code>pow</code> - Another arbitrary-precision integer.

* <code>mod</code> - An arbitrary-precision integer. (3).

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>pow</code> or <code>mod</code> is null.

### Multiply
    public EInteger Multiply(EInteger bigintMult)
Multiplies this instance by the value of an arbitrary-precision integer
 object.

**Parameters:**

* <code>bigintMult</code> - Another arbitrary-precision integer.

**Returns:**

* The product of the two numbers.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>bigintMult</code> is
 null.

### Negate
    public EInteger Negate()
Gets the value of this object with the sign reversed.

**Returns:**

* This object's value with the sign reversed.

### Pow
    public EInteger Pow(int powerSmall)
Raises an arbitrary-precision integer to a power.

**Parameters:**

* <code>powerSmall</code> - The exponent to raise to.

**Returns:**

* The result. Returns 1 if <code>powerSmall</code> is 0.

**Throws:**

* <code>IllegalArgumentException</code> - The parameter <code>powerSmall</code> is less
 than 0.

### PowBigIntVar
    public EInteger PowBigIntVar(EInteger power)
Raises an arbitrary-precision integer to a power, which is given as another
 arbitrary-precision integer.

**Parameters:**

* <code>power</code> - The exponent to raise to.

**Returns:**

* The result. Returns 1 if <code>power</code> is 0.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>power</code> is null.

* <code>IllegalArgumentException</code> - The parameter <code>power</code> is less than 0.

### Remainder
    public EInteger Remainder(EInteger divisor)
Finds the remainder that results when this instance is divided by the value
 of an arbitrary-precision integer. The remainder is the value that
 remains when the absolute value of this object is divided by the
 absolute value of the other object; the remainder has the same sign
 (positive or negative) as this object.

**Parameters:**

* <code>divisor</code> - Another arbitrary-precision integer.

**Returns:**

* The remainder of the two numbers.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>divisor</code> is null.

* <code>ArithmeticException</code> - Attempted to divide by zero.

### ShiftLeft
    public EInteger ShiftLeft(int numberBits)
Returns an arbitrary-precision integer with the bits shifted to the left by
 a number of bits. A value of 1 doubles this value, a value of 2
 multiplies it by 4, a value of 3 by 8, a value of 4 by 16, and so on.

**Parameters:**

* <code>numberBits</code> - The number of bits to shift. Can be negative, in which
 case this is the same as shiftRight with the absolute value of
 numberBits.

**Returns:**

* An arbitrary-precision integer.

### ShiftRight
    public EInteger ShiftRight(int numberBits)
Returns an arbitrary-precision integer with the bits shifted to the right.
 For this operation, the arbitrary-precision integer is treated as a
 two's-complement form (see <code>"Forms of numbers"</code>). Thus, for negative values, the
 arbitrary-precision integer is sign-extended.

**Parameters:**

* <code>numberBits</code> - Number of bits to shift right.

**Returns:**

* An arbitrary-precision integer.

### Sqrt
    public EInteger Sqrt()
Finds the square root of this instance&#x27;s value, rounded down.

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
    public EInteger Subtract(EInteger subtrahend)
Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer.

**Parameters:**

* <code>subtrahend</code> - Another arbitrary-precision integer.

**Returns:**

* The difference of the two objects.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>subtrahend</code> is
 null.

### ToBytes
    public byte[] ToBytes(boolean littleEndian)
Returns a byte array of this integer&#x27;s value. The byte array will take
 the number's two's-complement form (see <code>"Forms of numbers"</code>), using the
 fewest bytes necessary to store its value unambiguously. If this
 value is negative, the bits that appear beyond the most significant
 bit of the number will be all ones. The resulting byte array can be
 passed to the <code>FromBytes()</code> method (with the same byte order) to
 reconstruct this integer's value.

**Parameters:**

* <code>littleEndian</code> - If true, the byte order is little-endian, or
 least-significant-byte first. If false, the byte order is big-endian,
 or most-significant-byte first.

**Returns:**

* A byte array. If this value is 0, returns a byte array with the
 single element 0.

### ToInt32Checked
    public int ToInt32Checked()
Converts this object's value to a 32-bit signed integer, throwing an
 exception if it can't fit.

**Returns:**

* A 32-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is too big to fit a
 32-bit signed integer.

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

**Throws:**

* <code>ArithmeticException</code> - This object's value is too big to fit a
 64-bit signed integer.

### ToInt64Unchecked
    public long ToInt64Unchecked()
Converts this object's value to a 64-bit signed integer. If the value can't
 fit in a 64-bit integer, returns the lower 64 bits of this object's
 two's-complement form (see <code>"Forms of numbers"</code>) (in which case the return value might have a
 different sign than this object's value).

**Returns:**

* A 64-bit signed integer.

### ToRadixString
    public String ToRadixString(int radix)
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

**Throws:**

* <code>IllegalArgumentException</code> - The parameter "index" is less than 0,
 "endIndex" is less than 0, or either is greater than the string's
 length, or "endIndex" is less than "index" ; or radix is less than 2
 or greater than 36.

### toString
    public String toString()
Converts this object to a text string in base 10.

**Overrides:**

* <code>toString</code>&nbsp;in class&nbsp;<code>Object</code>

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

* <code>ArithmeticException</code> - This value is less than 0 or greater than
 255.

### ToByteUnchecked
    public byte ToByteUnchecked()
Converts this number to a byte (from 0 to 255), returning the
 least-significant bits of this number's two's-complement form.

**Returns:**

* This number, converted to a byte (from 0 to 255).

### FromByte
    public static EInteger FromByte(byte inputByte)
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

* <code>ArithmeticException</code> - This value is less than -32768 or greater
 than 32767.

### ToInt16Unchecked
    public short ToInt16Unchecked()
Converts this number to a 16-bit signed integer, returning the
 least-significant bits of this number's two's-complement form.

**Returns:**

* This number, converted to a 16-bit signed integer.

### FromInt16
    public static EInteger FromInt16(short inputInt16)
Converts a 16-bit signed integer to an arbitrary-precision integer.

**Parameters:**

* <code>inputInt16</code> - The number to convert as a 16-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision integer.
