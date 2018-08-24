# com.upokecenter.numbers.EInteger

    public final class EInteger extends Object implements Comparable<EInteger>

Represents an arbitrary-precision integer. (The "E" stands for "extended",
 and has this prefix to group it with the other classes common to this
 library, particularly EDecimal, EFloat, and ERational.) <p>Instances
 of this class are immutable, so they are inherently safe for use by
 multiple threads. Multiple instances of this object with the same
 value are interchangeable, but they should be compared using the
 "Equals" method rather than the "==" operator. </p>

## Methods

* `EInteger Abs()`<br>
 Returns the absolute value of this object's value.
* `EInteger Add​(EInteger bigintAugend)`<br>
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
* `int compareTo​(EInteger other)`<br>
 Compares an arbitrary-precision integer with this instance.
* `EInteger Divide​(EInteger bigintDivisor)`<br>
 Divides this instance by the value of an arbitrary-precision integer.
* `EInteger[] DivRem​(EInteger divisor)`<br>
 Divides this object by another arbitrary-precision integer and returns the
 quotient and remainder.
* `boolean equals​(Object obj)`<br>
 Determines whether this object and another object are equal and have the
 same type.
* `static EInteger FromByte​(byte inputByte)`<br>
 Converts a byte (from 0 to 255) to an arbitrary-precision integer.
* `static EInteger FromBytes​(byte[] bytes,
         boolean littleEndian)`<br>
 Initializes an arbitrary-precision integer from an array of bytes.
* `static EInteger FromInt16​(short inputInt16)`<br>
 Converts a 16-bit signed integer to an arbitrary-precision integer.
* `static EInteger FromInt32​(int intValue)`<br>
 Converts a 32-bit signed integer to an arbitrary-precision integer.
* `static EInteger FromInt64​(long longerValue)`<br>
 Converts a 64-bit signed integer to an arbitrary-precision integer.
* `static EInteger FromRadixString​(String str,
               int radix)`<br>
 Converts a string to an arbitrary-precision integer in a given radix.
* `static EInteger FromRadixSubstring​(String str,
                  int radix,
                  int index,
                  int endIndex)`<br>
 Converts a portion of a string to an arbitrary-precision integer in a given
 radix.
* `static EInteger FromString​(String str)`<br>
 Converts a string to an arbitrary-precision integer.
* `static EInteger FromSubstring​(String str,
             int index,
             int endIndex)`<br>
 Converts a portion of a string to an arbitrary-precision integer.
* `EInteger Gcd​(EInteger bigintSecond)`<br>
 Returns the greatest common divisor of this integer and the given integer.
* `int GetDigitCount()`<br>
 Returns the number of decimal digits used by this integer.
* `int GetLowBit()`<br>
 Gets the lowest set bit in this number's absolute value.
* `EInteger GetLowBitAsEInteger()`<br>
 Gets the lowest set bit in this number's absolute value.
* `static EInteger getOne()`<br>
 Gets the number 1 as an arbitrary-precision integer.
* `boolean GetSignedBit​(int index) "Forms of numbers"`<br>
 Returns whether a bit is set in the two's-complement form (see "Forms of numbers") of this
 object' s value.
* `int GetSignedBitLength()`<br>
 Finds the minimum number of bits needed to represent this object's value,
 except for its sign.
* `static EInteger getTen()`<br>
 Gets the number 10 as an arbitrary-precision integer.
* `boolean GetUnsignedBit​(int index)`<br>
 Returns whether a bit is set in this number's absolute value.
* `int GetUnsignedBitLength()`<br>
 Finds the minimum number of bits needed to represent this number's absolute
 value.
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
 Gets a value indicating whether this object's value is a power of two.
* `boolean isZero()`<br>
 Gets a value indicating whether this value is 0.
* `EInteger Mod​(EInteger divisor)`<br>
 Finds the modulus remainder that results when this instance is divided by
 the value of an arbitrary-precision integer.
* `EInteger ModPow​(EInteger pow,
      EInteger mod)`<br>
 Calculates the remainder when this arbitrary-precision integer raised to a
 certain power is divided by another arbitrary-precision integer.
* `EInteger Multiply​(EInteger bigintMult)`<br>
 Multiplies this instance by the value of an arbitrary-precision integer
 object.
* `EInteger Negate()`<br>
 Gets the value of this object with the sign reversed.
* `EInteger Pow​(int powerSmall)`<br>
 Raises an arbitrary-precision integer to a power.
* `EInteger PowBigIntVar​(EInteger power)`<br>
 Raises an arbitrary-precision integer to a power, which is given as another
 arbitrary-precision integer.
* `EInteger Remainder​(EInteger divisor)`<br>
 Finds the remainder that results when this instance is divided by the value
 of an arbitrary-precision integer.
* `EInteger ShiftLeft​(int numberBits)`<br>
 Returns an arbitrary-precision integer with the bits shifted to the left by
 a number of bits.
* `EInteger ShiftRight​(int numberBits)`<br>
 Returns an arbitrary-precision integer with the bits shifted to the right.
* `int signum()`<br>
 Gets the sign of this object's value.
* `EInteger Sqrt()`<br>
 Finds the square root of this instance's value, rounded down.
* `EInteger[] SqrtRem()`<br>
 Calculates the square root and the remainder.
* `EInteger Subtract​(EInteger subtrahend)`<br>
 Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer.
* `byte ToByteChecked()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255).
* `byte[] ToBytes​(boolean littleEndian)`<br>
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
* `String ToRadixString​(int radix)`<br>
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
Gets the number zero as an arbitrary-precision integer.

**Returns:**

* The number zero as an arbitrary-precision integer.

### isEven
    public final boolean isEven()
Gets a value indicating whether this value is even.

**Returns:**

* <code>true</code> if this value is even; otherwise, <code>false</code> .

### isPowerOfTwo
    public final boolean isPowerOfTwo()
Gets a value indicating whether this object's value is a power of two.

**Returns:**

* <code>true</code> if this object's value is a power of two; otherwise,
 <code>false</code>. <code>true</code> if this object's value is a power of two;
 otherwise, <code>false</code>.

### isZero
    public final boolean isZero()
Gets a value indicating whether this value is 0.

**Returns:**

* <code>true</code> if this value is 0; otherwise, <code>false</code> .

### signum
    public final int signum()
Gets the sign of this object's value.

**Returns:**

* The sign of this object's value.

### FromBytes
    public static EInteger FromBytes​(byte[] bytes, boolean littleEndian)
Initializes an arbitrary-precision integer from an array of bytes.

**Parameters:**

* <code>bytes</code> - The parameter <code>bytes</code> is not documented yet.

* <code>littleEndian</code> - The parameter <code>littleEndian</code> is not documented
 yet.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>bytes</code> is null.

### FromInt32
    public static EInteger FromInt32​(int intValue)
Converts a 32-bit signed integer to an arbitrary-precision integer.

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is not documented yet.

**Returns:**

* An arbitrary-precision integer with the same value as the 64-bit
 number.

### FromInt64
    public static EInteger FromInt64​(long longerValue)
Converts a 64-bit signed integer to an arbitrary-precision integer.

**Parameters:**

* <code>longerValue</code> - The parameter <code>longerValue</code> is not documented yet.

**Returns:**

* An arbitrary-precision integer with the same value as the 64-bit
 number.

### FromRadixString
    public static EInteger FromRadixString​(String str, int radix)
Converts a string to an arbitrary-precision integer in a given radix.

**Parameters:**

* <code>str</code> - The parameter <code>str</code> is not documented yet.

* <code>radix</code> - The parameter <code>radix</code> is not documented yet.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

### FromRadixSubstring
    public static EInteger FromRadixSubstring​(String str, int radix, int index, int endIndex)
Converts a portion of a string to an arbitrary-precision integer in a given
 radix.

**Parameters:**

* <code>str</code> - The parameter <code>str</code> is not documented yet.

* <code>radix</code> - The parameter <code>radix</code> is not documented yet.

* <code>index</code> - The parameter <code>index</code> is not documented yet.

* <code>endIndex</code> - The parameter <code>endIndex</code> is not documented yet.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>NumberFormatException</code> - The string portion is empty or in an invalid
 format.

* <code>IllegalArgumentException</code> - "Doesn't satisfy (endIndex - index) % 4 ==
 0".

### FromString
    public static EInteger FromString​(String str)
Converts a string to an arbitrary-precision integer.

**Parameters:**

* <code>str</code> - The parameter <code>str</code> is not documented yet.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 string.

**Throws:**

* <code>NumberFormatException</code> - The parameter <code>str</code> is in an invalid
 format.

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

### FromSubstring
    public static EInteger FromSubstring​(String str, int index, int endIndex)
Converts a portion of a string to an arbitrary-precision integer.

**Parameters:**

* <code>str</code> - The parameter <code>str</code> is not documented yet.

* <code>index</code> - The parameter <code>index</code> is not documented yet.

* <code>endIndex</code> - The parameter <code>endIndex</code> is not documented yet.

**Returns:**

* An arbitrary-precision integer with the same value as given in the
 string portion.

**Throws:**

* <code>IllegalArgumentException</code> - The parameter <code>index</code> is less than 0,
 <code>endIndex</code> is less than 0, or either is greater than the
 string's length, or <code>endIndex</code> is less than <code>index</code>.

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

### Abs
    public EInteger Abs()
Returns the absolute value of this object's value.

**Returns:**

* An arbitrary-precision integer.

### Add
    public EInteger Add​(EInteger bigintAugend)
Adds this object and another object.

**Parameters:**

* <code>bigintAugend</code> - The parameter <code>bigintAugend</code> is not documented
 yet.

**Returns:**

* The sum of the two objects.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>bigintAugend</code> is
 null.

### AsInt32Checked
    @Deprecated public int AsInt32Checked()
Deprecated.
<div class='deprecationComment'>Renamed to ToInt32Checked.</div>

**Returns:**

* A 32-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is too big to fit a
 32-bit signed integer.

### AsInt32Unchecked
    @Deprecated public int AsInt32Unchecked()
Deprecated.
<div class='deprecationComment'>Renamed to ToInt32Unchecked.</div>

**Returns:**

* A 32-bit signed integer.

### AsInt64Checked
    @Deprecated public long AsInt64Checked()
Deprecated.
<div class='deprecationComment'>Renamed to ToInt64Checked.</div>

**Returns:**

* A 64-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is too big to fit a
 64-bit signed integer.

### AsInt64Unchecked
    @Deprecated public long AsInt64Unchecked()
Deprecated.
<div class='deprecationComment'>Renamed to ToInt64Unchecked.</div>

**Returns:**

* A 64-bit signed integer.

### CanFitInInt32
    public boolean CanFitInInt32()
Returns whether this object's value can fit in a 32-bit signed integer.

**Returns:**

* <code>true</code> if this object's value can fit in a 32-bit signed
 integer; otherwise, <code>false</code>.

### CanFitInInt64
    public boolean CanFitInInt64()
Returns whether this object's value can fit in a 64-bit signed integer.

**Returns:**

* <code>true</code> if this object's value can fit in a 64-bit signed
 integer; otherwise, <code>false</code>.

### compareTo
    public int compareTo​(EInteger other)
Compares an arbitrary-precision integer with this instance.

**Specified by:**

* <code>compareTo</code>&nbsp;in interface&nbsp;<code>Comparable&lt;EInteger&gt;</code>

**Parameters:**

* <code>other</code> - The parameter <code>other</code> is not documented yet.

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

* <code>bigintDivisor</code> - The parameter <code>bigintDivisor</code> is not documented
 yet.

**Returns:**

* The quotient of the two objects.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>bigintDivisor</code> is
 null.

* <code>ArithmeticException</code> - Attempted to divide by zero.

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

* <code>ArithmeticException</code> - The parameter divisor is 0.

* <code>NullPointerException</code> - The parameter <code>divisor</code> is null.

### equals
    public boolean equals​(Object obj)
Determines whether this object and another object are equal and have the
 same type.

**Overrides:**

* <code>equals</code>&nbsp;in class&nbsp;<code>Object</code>

**Parameters:**

* <code>obj</code> - The parameter <code>obj</code> is not documented yet.

**Returns:**

* <code>true</code> if this object and another object are equal; otherwise,
 <code>false</code>.

### Gcd
    public EInteger Gcd​(EInteger bigintSecond)
Returns the greatest common divisor of this integer and the given integer.
 The greatest common divisor (GCD) is also known as the greatest
 common factor (GCF).

**Parameters:**

* <code>bigintSecond</code> - The parameter <code>bigintSecond</code> is not documented
 yet.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>bigintSecond</code> is
 null.

### GetDigitCount
    public int GetDigitCount()
Returns the number of decimal digits used by this integer.

**Returns:**

* A 32-bit signed integer.

### hashCode
    public int hashCode()
Returns the hash code for this instance. No application or process IDs are
 used in the hash code calculation.

**Overrides:**

* <code>hashCode</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A 32-bit signed integer.

### GetLowBit
    public int GetLowBit()
Gets the lowest set bit in this number's absolute value. (This will also be
 the lowest set bit in the number's two's-complement form (see <code>"Forms of numbers"</code>).).

**Returns:**

* A 32-bit signed integer.

### GetLowBitAsEInteger
    public EInteger GetLowBitAsEInteger()
Gets the lowest set bit in this number's absolute value. (This will also be
 the lowest set bit in the number's two's-complement form (see <code>"Forms of numbers"</code>).).

**Returns:**

* An arbitrary-precision integer.

### GetSignedBit
    public boolean GetSignedBit​(int index)
Returns whether a bit is set in the two's-complement form (see <code>"Forms of numbers"</code>) of this
 object' s value.

**Parameters:**

* <code>index</code> - The parameter <code>index</code> is not documented yet.

**Returns:**

* <code>true</code> if a bit is set in the two's-complement form (see
 <code>EDecimal</code>) of this object' s value;
 otherwise, <code>false</code>.

### GetSignedBitLength
    public int GetSignedBitLength()
Finds the minimum number of bits needed to represent this object's value,
 except for its sign. If the value is negative, finds the number of
 bits in the value equal to this object's absolute value minus 1.

**Returns:**

* A 32-bit signed integer.

### GetUnsignedBit
    public boolean GetUnsignedBit​(int index)
Returns whether a bit is set in this number's absolute value.

**Parameters:**

* <code>index</code> - The parameter <code>index</code> is not documented yet.

**Returns:**

* <code>true</code> if a bit is set in this number's absolute value.

### GetUnsignedBitLengthAsEInteger
    public EInteger GetUnsignedBitLengthAsEInteger()
Finds the minimum number of bits needed to represent this number's absolute
 value.

**Returns:**

* An arbitrary-precision integer.

### GetUnsignedBitLength
    public int GetUnsignedBitLength()
Finds the minimum number of bits needed to represent this number's absolute
 value.

**Returns:**

* A 32-bit signed integer.

### Mod
    public EInteger Mod​(EInteger divisor)
Finds the modulus remainder that results when this instance is divided by
 the value of an arbitrary-precision integer. The modulus remainder is
 the same as the normal remainder if the normal remainder is positive,
 and equals divisor plus normal remainder if the normal remainder is
 negative.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>divisor</code> is null.

### ModPow
    public EInteger ModPow​(EInteger pow, EInteger mod)
Calculates the remainder when this arbitrary-precision integer raised to a
 certain power is divided by another arbitrary-precision integer.

**Parameters:**

* <code>pow</code> - The parameter <code>pow</code> is not documented yet.

* <code>mod</code> - The parameter <code>mod</code> is not documented yet.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>pow</code> or <code>
 mod</code> is null.

### Multiply
    public EInteger Multiply​(EInteger bigintMult)
Multiplies this instance by the value of an arbitrary-precision integer
 object.

**Parameters:**

* <code>bigintMult</code> - The parameter <code>bigintMult</code> is not documented yet.

**Returns:**

* The product of the two numbers.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>bigintMult</code> is
 null.

### Negate
    public EInteger Negate()
Gets the value of this object with the sign reversed.

**Returns:**

* An arbitrary-precision integer.

### Pow
    public EInteger Pow​(int powerSmall)
Raises an arbitrary-precision integer to a power.

**Parameters:**

* <code>powerSmall</code> - The parameter <code>powerSmall</code> is not documented yet.

**Returns:**

* The result. Returns 1 if "powerSmall" is 0.

### PowBigIntVar
    public EInteger PowBigIntVar​(EInteger power)
Raises an arbitrary-precision integer to a power, which is given as another
 arbitrary-precision integer.

**Parameters:**

* <code>power</code> - The parameter <code>power</code> is not documented yet.

**Returns:**

* The result. Returns 1 if "power" is 0.

**Throws:**

* <code>IllegalArgumentException</code> - The parameter <code>power</code> is less than 0.

* <code>NullPointerException</code> - The parameter <code>power</code> is null.

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

* <code>ArithmeticException</code> - Attempted to divide by zero.

* <code>NullPointerException</code> - The parameter <code>divisor</code> is null.

### ShiftLeft
    public EInteger ShiftLeft​(int numberBits)
Returns an arbitrary-precision integer with the bits shifted to the left by
 a number of bits. A value of 1 doubles this value, a value of 2
 multiplies it by 4, a value of 3 by 8, a value of 4 by 16, and so on.

**Parameters:**

* <code>numberBits</code> - The parameter <code>numberBits</code> is not documented yet.

**Returns:**

* An arbitrary-precision integer.

### ShiftRight
    public EInteger ShiftRight​(int numberBits)
Returns an arbitrary-precision integer with the bits shifted to the right.
 For this operation, the arbitrary-precision integer is treated as a
 two's-complement form (see <code>"Forms of numbers"</code>). Thus, for negative values, the
 arbitrary-precision integer is sign-extended.

**Parameters:**

* <code>numberBits</code> - The parameter <code>numberBits</code> is not documented yet.

**Returns:**

* An arbitrary-precision integer.

### Sqrt
    public EInteger Sqrt()
Finds the square root of this instance's value, rounded down.

**Returns:**

* An arbitrary-precision integer.

### SqrtRem
    public EInteger[] SqrtRem()
Calculates the square root and the remainder.

**Returns:**

* An EInteger[] object.

### Subtract
    public EInteger Subtract​(EInteger subtrahend)
Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer.

**Parameters:**

* <code>subtrahend</code> - The parameter <code>subtrahend</code> is not documented yet.

**Returns:**

* The difference of the two objects.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>subtrahend</code> is
 null.

### ToBytes
    public byte[] ToBytes​(boolean littleEndian)
Returns a byte array of this integer's value. The byte array will take the
 number's two' s-complement form (see <code>"Forms of numbers"</code>), using the
 fewest bytes necessary to store its value unambiguously. If this
 value is negative, the bits that appear beyond the most significant
 bit of the number will be all ones. The resulting byte array can be
 passed to the <code>FromBytes()</code> method (with the same byte order) to
 reconstruct this integer's value.

**Parameters:**

* <code>littleEndian</code> - The parameter <code>littleEndian</code> is not documented
 yet.

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

**Throws:**

* <code>ArithmeticException</code> - This object's value is too big to fit a
 64-bit signed integer.

### ToInt64Unchecked
    public long ToInt64Unchecked()
Converts this object's value to a 64-bit signed integer. If the value can't
 fit in a 64-bit integer, returns the lower 64 bits of this object's
 two' s-complement form (see <code>"Forms of numbers"</code>) (in which case the return value might have a
 different sign than this object's value).

**Returns:**

* A 64-bit signed integer.

### ToRadixString
    public String ToRadixString​(int radix)
Generates a string representing the value of this object, in the given
 radix.

**Parameters:**

* <code>radix</code> - The parameter <code>radix</code> is not documented yet.

**Returns:**

* A string representing the value of this object. If this value is 0,
 returns "0". If negative, the string will begin with a minus sign
 ("-", U+002D). Depending on the radix, the string will use the basic
 digits 0 to 9 (U + 0030 to U + 0039) and then the basic letters A to Z
 (U + 0041 to U + 005A). For example, 0-9 in radix 10, and 0-9, then A-F
 in radix 16.

### toString
    public String toString()
Converts this object to a text string in base 10.

**Overrides:**

* <code>toString</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A text string.

### ToByteChecked
    public byte ToByteChecked()
Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255).

**Returns:**

* A byte (from 0 to 255).

**Throws:**

* <code>ArithmeticException</code> - This value is less than 0 or greater than
 255.

### ToByteUnchecked
    public byte ToByteUnchecked()
Converts this number to a byte (from 0 to 255), returning the
 least-significant bits of this number's two's-complement form.

**Returns:**

* A byte (from 0 to 255).

### FromByte
    public static EInteger FromByte​(byte inputByte)
Converts a byte (from 0 to 255) to an arbitrary-precision integer.

**Parameters:**

* <code>inputByte</code> - The parameter <code>inputByte</code> is not documented yet.

**Returns:**

* This number's value as an arbitrary-precision integer.

### ToInt16Checked
    public short ToInt16Checked()
Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer.

**Returns:**

* A 16-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is less than -32768 or greater
 than 32767.

### ToInt16Unchecked
    public short ToInt16Unchecked()
Converts this number to a 16-bit signed integer, returning the
 least-significant bits of this number's two's-complement form.

**Returns:**

* A 16-bit signed integer.

### FromInt16
    public static EInteger FromInt16​(short inputInt16)
Converts a 16-bit signed integer to an arbitrary-precision integer.

**Parameters:**

* <code>inputInt16</code> - The parameter <code>inputInt16</code> is not documented yet.

**Returns:**

* This number's value as an arbitrary-precision integer.
