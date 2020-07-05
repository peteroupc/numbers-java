# com.upokecenter.numbers.EInteger

    public final class EInteger extends java.lang.Object implements java.lang.Comparable<EInteger>

## Methods

* `EInteger Abs()`<br>
* `EInteger Add​(int intValue)`<br>
* `EInteger Add​(long longValue)`<br>
* `EInteger Add​(EInteger bigintAugend)`<br>
* `EInteger And​(EInteger other)`<br>
* `EInteger AndNot​(EInteger second)`<br>
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
* `int compareTo​(int intValue)`<br>
* `int compareTo​(long longValue)`<br>
* `int compareTo​(EInteger other)`<br>
* `EInteger Decrement()`<br>
* `EInteger Divide​(int intValue)`<br>
* `EInteger Divide​(long longValue)`<br>
* `EInteger Divide​(EInteger bigintDivisor)`<br>
* `EInteger[] DivRem​(int intDivisor)`<br>
* `EInteger[] DivRem​(long intDivisor)`<br>
* `EInteger[] DivRem​(EInteger divisor)`<br>
* `boolean equals​(java.lang.Object obj)`<br>
* `EInteger Eqv​(EInteger second)`<br>
* `static EInteger FromBoolean​(boolean boolValue)`<br>
* `static EInteger FromByte​(byte inputByte)`<br>
* `static EInteger FromBytes​(byte[] bytes,
         boolean littleEndian)`<br>
* `static EInteger FromInt16​(short inputInt16)`<br>
* `static EInteger FromInt32​(int intValue)`<br>
* `static EInteger FromInt64​(long longerValue)`<br>
* `static EInteger FromRadixString​(byte[] bytes,
               int radix)`<br>
* `static EInteger FromRadixString​(char[] cs,
               int radix)`<br>
* `static EInteger FromRadixString​(java.lang.String str,
               int radix)`<br>
* `static EInteger FromRadixSubstring​(byte[] bytes,
                  int radix,
                  int index,
                  int endIndex)`<br>
* `static EInteger FromRadixSubstring​(char[] cs,
                  int radix,
                  int index,
                  int endIndex)`<br>
* `static EInteger FromRadixSubstring​(java.lang.String str,
                  int radix,
                  int index,
                  int endIndex)`<br>
* `static EInteger FromString​(byte[] bytes)`<br>
* `static EInteger FromString​(char[] cs)`<br>
* `static EInteger FromString​(java.lang.String str)`<br>
* `static EInteger FromSubstring​(byte[] bytes,
             int index,
             int endIndex)`<br>
* `static EInteger FromSubstring​(char[] cs,
             int index,
             int endIndex)`<br>
* `static EInteger FromSubstring​(java.lang.String str,
             int index,
             int endIndex)`<br>
* `EInteger Gcd​(EInteger bigintSecond)`<br>
* `int GetDigitCount()`<br>
 Deprecated.
This method may overflow.
 This method may overflow.
* `EInteger GetDigitCountAsEInteger()`<br>
* `long GetDigitCountAsInt64()`<br>
* `int GetLowBit()`<br>
 Deprecated.
This method may overflow.
 This method may overflow.
* `EInteger GetLowBitAsEInteger()`<br>
* `long GetLowBitAsInt64()`<br>
* `static EInteger getOne()`<br>
* `boolean GetSignedBit​(int index)`<br>
* `boolean GetSignedBit​(EInteger bigIndex)`<br>
* `int GetSignedBitLength()`<br>
 Deprecated.
This method may overflow.
 This method may overflow.
* `EInteger GetSignedBitLengthAsEInteger()`<br>
* `long GetSignedBitLengthAsInt64()`<br>
* `static EInteger getTen()`<br>
* `boolean GetUnsignedBit​(int index)`<br>
* `boolean GetUnsignedBit​(EInteger bigIndex)`<br>
* `int GetUnsignedBitLength()`<br>
 Deprecated.
This method may overflow.
 This method may overflow.
* `EInteger GetUnsignedBitLengthAsEInteger()`<br>
* `long GetUnsignedBitLengthAsInt64()`<br>
* `static EInteger getZero()`<br>
* `int hashCode()`<br>
* `EInteger Imp​(EInteger second)`<br>
* `EInteger Increment()`<br>
* `boolean isEven()`<br>
* `boolean isPowerOfTwo()`<br>
* `boolean isZero()`<br>
* `static EInteger Max​(EInteger first,
   EInteger second)`<br>
* `static EInteger MaxMagnitude​(EInteger first,
            EInteger second)`<br>
* `static EInteger Min​(EInteger first,
   EInteger second)`<br>
* `static EInteger MinMagnitude​(EInteger first,
            EInteger second)`<br>
* `EInteger Mod​(int smallDivisor)`<br>
* `EInteger Mod​(EInteger divisor)`<br>
* `EInteger ModPow​(EInteger pow,
      EInteger mod)`<br>
* `EInteger Multiply​(int intValue)`<br>
* `EInteger Multiply​(long longValue)`<br>
* `EInteger Multiply​(EInteger bigintMult)`<br>
* `EInteger Negate()`<br>
* `EInteger Not()`<br>
* `EInteger Or​(EInteger second)`<br>
* `EInteger OrNot​(EInteger second)`<br>
* `EInteger Pow​(int powerSmall)`<br>
* `EInteger Pow​(EInteger bigPower)`<br>
* `EInteger PowBigIntVar​(EInteger power)`<br>
* `EInteger Remainder​(int intValue)`<br>
* `EInteger Remainder​(long longValue)`<br>
* `EInteger Remainder​(EInteger divisor)`<br>
* `EInteger Root​(int root)`<br>
* `EInteger Root​(EInteger root)`<br>
* `EInteger[] RootRem​(int root)`<br>
* `EInteger[] RootRem​(EInteger root)`<br>
* `EInteger ShiftLeft​(int numberBits)`<br>
* `EInteger ShiftLeft​(EInteger eshift)`<br>
* `EInteger ShiftRight​(int numberBits)`<br>
* `EInteger ShiftRight​(EInteger eshift)`<br>
* `int signum()`<br>
* `EInteger Sqrt()`<br>
* `EInteger[] SqrtRem()`<br>
* `EInteger Subtract​(int intValue)`<br>
* `EInteger Subtract​(long longValue)`<br>
* `EInteger Subtract​(EInteger subtrahend)`<br>
* `byte ToByteChecked()`<br>
* `byte[] ToBytes​(boolean littleEndian)`<br>
* `byte ToByteUnchecked()`<br>
* `short ToInt16Checked()`<br>
* `short ToInt16Unchecked()`<br>
* `int ToInt32Checked()`<br>
* `int ToInt32Unchecked()`<br>
* `long ToInt64Checked()`<br>
* `long ToInt64Unchecked()`<br>
* `java.lang.String ToRadixString​(int radix)`<br>
* `java.lang.String toString()`<br>
* `EInteger Xor​(EInteger other)`<br>
* `EInteger XorNot​(EInteger second)`<br>

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
### FromBoolean
    public static EInteger FromBoolean​(boolean boolValue)
### FromInt32
    public static EInteger FromInt32​(int intValue)
### FromInt64
    public static EInteger FromInt64​(long longerValue)
### FromRadixString
    public static EInteger FromRadixString​(java.lang.String str, int radix)
### FromRadixSubstring
    public static EInteger FromRadixSubstring​(java.lang.String str, int radix, int index, int endIndex)
### FromSubstring
    public static EInteger FromSubstring​(char[] cs, int index, int endIndex)
### FromString
    public static EInteger FromString​(char[] cs)
### FromRadixString
    public static EInteger FromRadixString​(char[] cs, int radix)
### FromRadixSubstring
    public static EInteger FromRadixSubstring​(char[] cs, int radix, int index, int endIndex)
### FromSubstring
    public static EInteger FromSubstring​(byte[] bytes, int index, int endIndex)
### FromString
    public static EInteger FromString​(byte[] bytes)
### FromRadixString
    public static EInteger FromRadixString​(byte[] bytes, int radix)
### FromRadixSubstring
    public static EInteger FromRadixSubstring​(byte[] bytes, int radix, int index, int endIndex)
### FromString
    public static EInteger FromString​(java.lang.String str)
### FromSubstring
    public static EInteger FromSubstring​(java.lang.String str, int index, int endIndex)
### Abs
    public EInteger Abs()
### Add
    public EInteger Add​(EInteger bigintAugend)
### AsInt32Checked
    @Deprecated public int AsInt32Checked()
Deprecated.
Renamed to ToInt32Checked.

### AsInt32Unchecked
    @Deprecated public int AsInt32Unchecked()
Deprecated.
Renamed to ToInt32Unchecked.

### AsInt64Checked
    @Deprecated public long AsInt64Checked()
Deprecated.
Renamed to ToInt64Checked.

### AsInt64Unchecked
    @Deprecated public long AsInt64Unchecked()
Deprecated.
Renamed to ToInt64Unchecked.

### CanFitInInt32
    public boolean CanFitInInt32()
### CanFitInInt64
    public boolean CanFitInInt64()
### compareTo
    public int compareTo​(EInteger other)

**Specified by:**

* <code>compareTo</code> in interface <code>java.lang.Comparable&lt;EInteger&gt;</code>

### Max
    public static EInteger Max​(EInteger first, EInteger second)
### Min
    public static EInteger Min​(EInteger first, EInteger second)
### MaxMagnitude
    public static EInteger MaxMagnitude​(EInteger first, EInteger second)
### MinMagnitude
    public static EInteger MinMagnitude​(EInteger first, EInteger second)
### Add
    public EInteger Add​(int intValue)
### Subtract
    public EInteger Subtract​(int intValue)
### Multiply
    public EInteger Multiply​(int intValue)
### Divide
    public EInteger Divide​(int intValue)
### Remainder
    public EInteger Remainder​(int intValue)
### compareTo
    public int compareTo​(int intValue)
### Divide
    public EInteger Divide​(EInteger bigintDivisor)
### DivRem
    public EInteger[] DivRem​(int intDivisor)
### Add
    public EInteger Add​(long longValue)
### Subtract
    public EInteger Subtract​(long longValue)
### Multiply
    public EInteger Multiply​(long longValue)
### Divide
    public EInteger Divide​(long longValue)
### Remainder
    public EInteger Remainder​(long longValue)
### compareTo
    public int compareTo​(long longValue)
### DivRem
    public EInteger[] DivRem​(long intDivisor)
### DivRem
    public EInteger[] DivRem​(EInteger divisor)
### equals
    public boolean equals​(java.lang.Object obj)

**Overrides:**

* <code>equals</code> in class <code>java.lang.Object</code>

### Gcd
    public EInteger Gcd​(EInteger bigintSecond)
### GetDigitCountAsEInteger
    public EInteger GetDigitCountAsEInteger()
### GetDigitCount
    @Deprecated public int GetDigitCount()
Deprecated.
This method may overflow. Use GetDigitCountAsEInteger instead.

### GetDigitCountAsInt64
    public long GetDigitCountAsInt64()
### hashCode
    public int hashCode()

**Overrides:**

* <code>hashCode</code> in class <code>java.lang.Object</code>

### GetLowBit
    @Deprecated public int GetLowBit()
Deprecated.
This method may overflow. Use GetLowBitAsEInteger instead.

### GetLowBitAsInt64
    public long GetLowBitAsInt64()
### GetLowBitAsEInteger
    public EInteger GetLowBitAsEInteger()
### GetSignedBit
    public boolean GetSignedBit​(EInteger bigIndex)
### GetSignedBit
    public boolean GetSignedBit​(int index)
### GetSignedBitLengthAsEInteger
    public EInteger GetSignedBitLengthAsEInteger()
### GetSignedBitLengthAsInt64
    public long GetSignedBitLengthAsInt64()
### GetSignedBitLength
    @Deprecated public int GetSignedBitLength()
Deprecated.
This method may overflow. Use GetSignedBitLengthAsEInteger instead.

### GetUnsignedBit
    public boolean GetUnsignedBit​(EInteger bigIndex)
### GetUnsignedBit
    public boolean GetUnsignedBit​(int index)
### GetUnsignedBitLengthAsEInteger
    public EInteger GetUnsignedBitLengthAsEInteger()
### GetUnsignedBitLengthAsInt64
    public long GetUnsignedBitLengthAsInt64()
### GetUnsignedBitLength
    @Deprecated public int GetUnsignedBitLength()
Deprecated.
This method may overflow. Use GetUnsignedBitLengthAsEInteger instead.

### Mod
    public EInteger Mod​(EInteger divisor)
### Mod
    public EInteger Mod​(int smallDivisor)
### ModPow
    public EInteger ModPow​(EInteger pow, EInteger mod)
### Multiply
    public EInteger Multiply​(EInteger bigintMult)
### Negate
    public EInteger Negate()
### Pow
    public EInteger Pow​(EInteger bigPower)
### Pow
    public EInteger Pow​(int powerSmall)
### PowBigIntVar
    public EInteger PowBigIntVar​(EInteger power)
### Remainder
    public EInteger Remainder​(EInteger divisor)
### ShiftRight
    public EInteger ShiftRight​(EInteger eshift)
### ShiftLeft
    public EInteger ShiftLeft​(EInteger eshift)
### ShiftLeft
    public EInteger ShiftLeft​(int numberBits)
### Not
    public EInteger Not()
### And
    public EInteger And​(EInteger other)
### Or
    public EInteger Or​(EInteger second)
### AndNot
    public EInteger AndNot​(EInteger second)
### OrNot
    public EInteger OrNot​(EInteger second)
### Imp
    public EInteger Imp​(EInteger second)
### XorNot
    public EInteger XorNot​(EInteger second)
### Eqv
    public EInteger Eqv​(EInteger second)
### Xor
    public EInteger Xor​(EInteger other)
### ShiftRight
    public EInteger ShiftRight​(int numberBits)
### Sqrt
    public EInteger Sqrt()
### SqrtRem
    public EInteger[] SqrtRem()
### Root
    public EInteger Root​(EInteger root)
### RootRem
    public EInteger[] RootRem​(EInteger root)
### Root
    public EInteger Root​(int root)
### RootRem
    public EInteger[] RootRem​(int root)
### Subtract
    public EInteger Subtract​(EInteger subtrahend)
### ToBytes
    public byte[] ToBytes​(boolean littleEndian)
### ToInt32Checked
    public int ToInt32Checked()
### ToInt32Unchecked
    public int ToInt32Unchecked()
### ToInt64Checked
    public long ToInt64Checked()
### ToInt64Unchecked
    public long ToInt64Unchecked()
### ToRadixString
    public java.lang.String ToRadixString​(int radix)
### toString
    public java.lang.String toString()

**Overrides:**

* <code>toString</code> in class <code>java.lang.Object</code>

### Increment
    public EInteger Increment()
### Decrement
    public EInteger Decrement()
### ToByteChecked
    public byte ToByteChecked()
### ToByteUnchecked
    public byte ToByteUnchecked()
### FromByte
    public static EInteger FromByte​(byte inputByte)
### ToInt16Checked
    public short ToInt16Checked()
### ToInt16Unchecked
    public short ToInt16Unchecked()
### FromInt16
    public static EInteger FromInt16​(short inputInt16)
