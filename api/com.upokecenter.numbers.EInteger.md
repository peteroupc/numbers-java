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
* `EInteger Add​(EInteger bigintAugend)`<br>
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
* `boolean CanFitInInt64()`<br>
* `int compareTo​(EInteger other)`<br>
* `EInteger Divide​(EInteger bigintDivisor)`<br>
* `EInteger[] DivRem​(EInteger divisor)`<br>
* `boolean equals​(Object obj)`<br>
* `static EInteger FromByte​(byte inputByte)`<br>
* `static EInteger FromBytes​(byte[] bytes,
         boolean littleEndian)`<br>
* `static EInteger FromInt16​(short inputInt16)`<br>
* `static EInteger FromInt32​(int intValue)`<br>
* `static EInteger FromInt64​(long longerValue)`<br>
* `static EInteger FromRadixString​(String str,
               int radix)`<br>
* `static EInteger FromRadixSubstring​(String str,
                  int radix,
                  int index,
                  int endIndex)`<br>
* `static EInteger FromString​(String str)`<br>
* `static EInteger FromSubstring​(String str,
             int index,
             int endIndex)`<br>
* `EInteger Gcd​(EInteger bigintSecond)`<br>
* `int GetDigitCount()`<br>
* `int GetLowBit()`<br>
* `EInteger GetLowBitAsEInteger()`<br>
* `static EInteger getOne()`<br>
* `boolean GetSignedBit​(int index)`<br>
* `int GetSignedBitLength()`<br>
* `static EInteger getTen()`<br>
* `boolean GetUnsignedBit​(int index)`<br>
* `int GetUnsignedBitLength()`<br>
* `EInteger GetUnsignedBitLengthAsEInteger()`<br>
* `static EInteger getZero()`<br>
* `int hashCode()`<br>
* `boolean isEven()`<br>
* `boolean isPowerOfTwo()`<br>
* `boolean isZero()`<br>
* `EInteger Mod​(EInteger divisor)`<br>
* `EInteger ModPow​(EInteger pow,
      EInteger mod)`<br>
* `EInteger Multiply​(EInteger bigintMult)`<br>
* `EInteger Negate()`<br>
* `EInteger Pow​(int powerSmall)`<br>
* `EInteger PowBigIntVar​(EInteger power)`<br>
* `EInteger Remainder​(EInteger divisor)`<br>
* `EInteger ShiftLeft​(int numberBits)`<br>
* `EInteger ShiftRight​(int numberBits)`<br>
* `int signum()`<br>
* `EInteger Sqrt()`<br>
* `EInteger[] SqrtRem()`<br>
* `EInteger Subtract​(EInteger subtrahend)`<br>
* `byte ToByteChecked()`<br>
* `byte[] ToBytes​(boolean littleEndian)`<br>
* `byte ToByteUnchecked()`<br>
* `short ToInt16Checked()`<br>
* `short ToInt16Unchecked()`<br>
* `int ToInt32Checked()`<br>
* `int ToInt32Unchecked()`<br>
* `long ToInt64Checked()`<br>
* `long ToInt64Unchecked()`<br>
* `String ToRadixString​(int radix)`<br>
* `String toString()`<br>

## Method Details

### getOne
    public static EInteger getOne()
### getTen
    public static EInteger getTen()
### getZero
    public static EInteger getZero()
### isEven
    public final boolean isEven()
### isPowerOfTwo
    public final boolean isPowerOfTwo()
### isZero
    public final boolean isZero()
### signum
    public final int signum()
### FromBytes
    public static EInteger FromBytes​(byte[] bytes, boolean littleEndian)

**Parameters:**

* <code>bytes</code> - Not documented yet.

* <code>littleEndian</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### FromInt32
    public static EInteger FromInt32​(int intValue)

**Parameters:**

* <code>intValue</code> - Not documented yet.

**Returns:**

* An EInteger object.

### FromInt64
    public static EInteger FromInt64​(long longerValue)

**Parameters:**

* <code>longerValue</code> - Not documented yet.

**Returns:**

* An EInteger object.

### FromRadixString
    public static EInteger FromRadixString​(String str, int radix)

**Parameters:**

* <code>str</code> - Not documented yet.

* <code>radix</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### FromRadixSubstring
    public static EInteger FromRadixSubstring​(String str, int radix, int index, int endIndex)

**Parameters:**

* <code>str</code> - Not documented yet.

* <code>radix</code> - Not documented yet.

* <code>index</code> - Not documented yet. (3).

* <code>endIndex</code> - Not documented yet. (4).

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### FromString
    public static EInteger FromString​(String str)

**Parameters:**

* <code>str</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### FromSubstring
    public static EInteger FromSubstring​(String str, int index, int endIndex)

**Parameters:**

* <code>str</code> - Not documented yet.

* <code>index</code> - Not documented yet.

* <code>endIndex</code> - Not documented yet. (3).

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### Abs
    public EInteger Abs()

**Returns:**

* An EInteger object.

### Add
    public EInteger Add​(EInteger bigintAugend)

**Parameters:**

* <code>bigintAugend</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### AsInt32Checked
    @Deprecated public int AsInt32Checked()
Deprecated.
<div class='deprecationComment'>Renamed to ToInt32Checked.</div>

**Returns:**

* A 32-bit signed integer.

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

### AsInt64Unchecked
    @Deprecated public long AsInt64Unchecked()
Deprecated.
<div class='deprecationComment'>Renamed to ToInt64Unchecked.</div>

**Returns:**

* A 64-bit signed integer.

### CanFitInInt32
    public boolean CanFitInInt32()

**Returns:**

* A Boolean object.

### CanFitInInt64
    public boolean CanFitInInt64()

**Returns:**

* A Boolean object.

### compareTo
    public int compareTo​(EInteger other)

**Specified by:**

* <code>compareTo</code>&nbsp;in interface&nbsp;<code>Comparable&lt;EInteger&gt;</code>

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### Divide
    public EInteger Divide​(EInteger bigintDivisor)

**Parameters:**

* <code>bigintDivisor</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

* <code>ArithmeticException</code> - Attempted to divide by zero.

### DivRem
    public EInteger[] DivRem​(EInteger divisor)

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EInteger[] object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

* <code>ArithmeticException</code> - Attempted to divide by zero.

### equals
    public boolean equals​(Object obj)

**Overrides:**

* <code>equals</code>&nbsp;in class&nbsp;<code>Object</code>

**Parameters:**

* <code>obj</code> - Not documented yet.

**Returns:**

* A Boolean object.

### Gcd
    public EInteger Gcd​(EInteger bigintSecond)

**Parameters:**

* <code>bigintSecond</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### GetDigitCount
    public int GetDigitCount()

**Returns:**

* A 32-bit signed integer.

### hashCode
    public int hashCode()

**Overrides:**

* <code>hashCode</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A 32-bit signed integer.

### GetLowBit
    public int GetLowBit()

**Returns:**

* A 32-bit signed integer.

### GetLowBitAsEInteger
    public EInteger GetLowBitAsEInteger()

**Returns:**

* An EInteger object.

### GetSignedBit
    public boolean GetSignedBit​(int index)

**Parameters:**

* <code>index</code> - Not documented yet.

**Returns:**

* A Boolean object.

### GetSignedBitLength
    public int GetSignedBitLength()

**Returns:**

* A 32-bit signed integer.

### GetUnsignedBit
    public boolean GetUnsignedBit​(int index)

**Parameters:**

* <code>index</code> - Not documented yet.

**Returns:**

* A Boolean object.

### GetUnsignedBitLengthAsEInteger
    public EInteger GetUnsignedBitLengthAsEInteger()

**Returns:**

* An EInteger object.

### GetUnsignedBitLength
    public int GetUnsignedBitLength()

**Returns:**

* A 32-bit signed integer.

### Mod
    public EInteger Mod​(EInteger divisor)

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### ModPow
    public EInteger ModPow​(EInteger pow, EInteger mod)

**Parameters:**

* <code>pow</code> - Not documented yet.

* <code>mod</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### Multiply
    public EInteger Multiply​(EInteger bigintMult)

**Parameters:**

* <code>bigintMult</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### Negate
    public EInteger Negate()

**Returns:**

* An EInteger object.

### Pow
    public EInteger Pow​(int powerSmall)

**Parameters:**

* <code>powerSmall</code> - Not documented yet.

**Returns:**

* An EInteger object.

### PowBigIntVar
    public EInteger PowBigIntVar​(EInteger power)

**Parameters:**

* <code>power</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### Remainder
    public EInteger Remainder​(EInteger divisor)

**Parameters:**

* <code>divisor</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

* <code>ArithmeticException</code> - Attempted to divide by zero.

### ShiftLeft
    public EInteger ShiftLeft​(int numberBits)

**Parameters:**

* <code>numberBits</code> - Not documented yet.

**Returns:**

* An EInteger object.

### ShiftRight
    public EInteger ShiftRight​(int numberBits)

**Parameters:**

* <code>numberBits</code> - Not documented yet.

**Returns:**

* An EInteger object.

### Sqrt
    public EInteger Sqrt()

**Returns:**

* An EInteger object.

### SqrtRem
    public EInteger[] SqrtRem()

**Returns:**

* An EInteger[] object.

### Subtract
    public EInteger Subtract​(EInteger subtrahend)

**Parameters:**

* <code>subtrahend</code> - Not documented yet.

**Returns:**

* An EInteger object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### ToBytes
    public byte[] ToBytes​(boolean littleEndian)

**Parameters:**

* <code>littleEndian</code> - Not documented yet.

**Returns:**

* A byte array.

### ToInt32Checked
    public int ToInt32Checked()

**Returns:**

* A 32-bit signed integer.

### ToInt32Unchecked
    public int ToInt32Unchecked()

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

### ToRadixString
    public String ToRadixString​(int radix)

**Parameters:**

* <code>radix</code> - Not documented yet.

**Returns:**

* A string object.

### toString
    public String toString()

**Overrides:**

* <code>toString</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A string object.

### ToByteChecked
    public byte ToByteChecked()

**Returns:**

* A Byte object.

### ToByteUnchecked
    public byte ToByteUnchecked()

**Returns:**

* A Byte object.

### FromByte
    public static EInteger FromByte​(byte inputByte)

**Parameters:**

* <code>inputByte</code> - Not documented yet.

**Returns:**

* An EInteger object.

### ToInt16Checked
    public short ToInt16Checked()

**Returns:**

* A 16-bit signed integer.

### ToInt16Unchecked
    public short ToInt16Unchecked()

**Returns:**

* A 16-bit signed integer.

### FromInt16
    public static EInteger FromInt16​(short inputInt16)

**Parameters:**

* <code>inputInt16</code> - Not documented yet.

**Returns:**

* An EInteger object.
