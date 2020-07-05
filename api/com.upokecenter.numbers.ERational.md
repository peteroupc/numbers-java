# com.upokecenter.numbers.ERational

    public final class ERational extends java.lang.Object implements java.lang.Comparable<ERational>

## Fields

* `static ERational NaN`<br>
* `static ERational NegativeInfinity`<br>
* `static ERational NegativeZero`<br>
* `static ERational One`<br>
* `static ERational PositiveInfinity`<br>
* `static ERational SignalingNaN`<br>
* `static ERational Ten`<br>
* `static ERational Zero`<br>

## Constructors

* `ERational​(EInteger numerator,
         EInteger denominator)`<br>
 Deprecated.
Use the Create method instead.
 Use the Create method instead.

## Methods

* `ERational Abs()`<br>
* `ERational Add​(int v)`<br>
* `ERational Add​(long v)`<br>
* `ERational Add​(ERational otherValue)`<br>
* `int compareTo​(int intOther)`<br>
* `int compareTo​(long intOther)`<br>
* `int compareTo​(ERational other)`<br>
* `int CompareToBinary​(EFloat other)`<br>
* `int CompareToDecimal​(EDecimal other)`<br>
* `int CompareToTotal​(ERational other)`<br>
* `int CompareToTotalMagnitude​(ERational other)`<br>
* `int CompareToValue​(int intOther)`<br>
* `int CompareToValue​(long intOther)`<br>
* `int CompareToValue​(ERational other)`<br>
* `ERational Copy()`<br>
* `ERational CopySign​(ERational other)`<br>
* `static ERational Create​(int numeratorSmall,
      int denominatorSmall)`<br>
* `static ERational Create​(long numeratorLong,
      long denominatorLong)`<br>
* `static ERational Create​(EInteger numerator,
      EInteger denominator)`<br>
* `static ERational CreateNaN​(EInteger diag)`<br>
* `static ERational CreateNaN​(EInteger diag,
         boolean signaling,
         boolean negative)`<br>
* `ERational Decrement()`<br>
* `ERational Divide​(int v)`<br>
* `ERational Divide​(long v)`<br>
* `ERational Divide​(ERational otherValue)`<br>
* `boolean equals​(ERational other)`<br>
* `boolean equals​(java.lang.Object obj)`<br>
* `static ERational FromBoolean​(boolean boolValue)`<br>
* `static ERational FromByte​(byte inputByte)`<br>
* `static ERational FromDouble​(double flt)`<br>
* `static ERational FromDoubleBits​(long value)`<br>
* `static ERational FromEDecimal​(EDecimal ef)`<br>
* `static ERational FromEFloat​(EFloat ef)`<br>
* `static ERational FromEInteger​(EInteger bigint)`<br>
* `static ERational FromExtendedDecimal​(EDecimal ef)`<br>
 Deprecated.
Renamed to FromEDecimal.
 Renamed to FromEDecimal.
* `static ERational FromExtendedFloat​(EFloat ef)`<br>
 Deprecated.
Renamed to FromEFloat.
 Renamed to FromEFloat.
* `static ERational FromInt16​(short inputInt16)`<br>
* `static ERational FromInt32​(int inputInt32)`<br>
* `static ERational FromInt64​(long inputInt64)`<br>
* `static ERational FromSingle​(float flt)`<br>
* `static ERational FromSingleBits​(int value)`<br>
* `static ERational FromString​(byte[] bytes)`<br>
* `static ERational FromString​(byte[] bytes,
          int offset,
          int length)`<br>
* `static ERational FromString​(char[] chars)`<br>
* `static ERational FromString​(char[] chars,
          int offset,
          int length)`<br>
* `static ERational FromString​(java.lang.String str)`<br>
* `static ERational FromString​(java.lang.String str,
          int offset,
          int length)`<br>
* `EInteger getDenominator()`<br>
* `EInteger getNumerator()`<br>
* `EInteger getUnsignedNumerator()`<br>
* `int hashCode()`<br>
* `ERational Increment()`<br>
* `boolean isFinite()`<br>
* `boolean IsInfinity()`<br>
* `boolean IsInteger()`<br>
* `boolean IsNaN()`<br>
* `boolean isNegative()`<br>
* `boolean IsNegativeInfinity()`<br>
* `boolean IsPositiveInfinity()`<br>
* `boolean IsQuietNaN()`<br>
* `boolean IsSignalingNaN()`<br>
* `boolean isZero()`<br>
* `static ERational Max​(ERational first,
   ERational second)`<br>
* `static ERational MaxMagnitude​(ERational first,
            ERational second)`<br>
* `static ERational Min​(ERational first,
   ERational second)`<br>
* `static ERational MinMagnitude​(ERational first,
            ERational second)`<br>
* `ERational Multiply​(int v)`<br>
* `ERational Multiply​(long v)`<br>
* `ERational Multiply​(ERational otherValue)`<br>
* `ERational Negate()`<br>
* `ERational Remainder​(int v)`<br>
* `ERational Remainder​(long v)`<br>
* `ERational Remainder​(ERational otherValue)`<br>
* `int signum()`<br>
* `ERational Subtract​(int v)`<br>
* `ERational Subtract​(long v)`<br>
* `ERational Subtract​(ERational otherValue)`<br>
* `byte ToByteChecked()`<br>
* `byte ToByteIfExact()`<br>
* `byte ToByteUnchecked()`<br>
* `double ToDouble()`<br>
* `long ToDoubleBits()`<br>
* `EDecimal ToEDecimal()`<br>
* `EDecimal ToEDecimal​(EContext ctx)`<br>
* `EDecimal ToEDecimalExactIfPossible​(EContext ctx)`<br>
* `EFloat ToEFloat()`<br>
* `EFloat ToEFloat​(EContext ctx)`<br>
* `EFloat ToEFloatExactIfPossible​(EContext ctx)`<br>
* `EInteger ToEInteger()`<br>
* `EInteger ToEIntegerExact()`<br>
 Deprecated.
Renamed to ToEIntegerIfExact.
 Renamed to ToEIntegerIfExact.
* `EInteger ToEIntegerIfExact()`<br>
* `EDecimal ToExtendedDecimal()`<br>
 Deprecated.
Renamed to ToEDecimal.
 Renamed to ToEDecimal.
* `EDecimal ToExtendedDecimal​(EContext ctx)`<br>
 Deprecated.
Renamed to ToEDecimal.
 Renamed to ToEDecimal.
* `EDecimal ToExtendedDecimalExactIfPossible​(EContext ctx)`<br>
 Deprecated.
Renamed to ToEDecimalExactIfPossible.
 Renamed to ToEDecimalExactIfPossible.
* `EFloat ToExtendedFloat()`<br>
 Deprecated.
Renamed to ToEFloat.
 Renamed to ToEFloat.
* `EFloat ToExtendedFloat​(EContext ctx)`<br>
 Deprecated.
Renamed to ToEFloat.
 Renamed to ToEFloat.
* `EFloat ToExtendedFloatExactIfPossible​(EContext ctx)`<br>
 Deprecated.
Renamed to ToEFloatExactIfPossible.
 Renamed to ToEFloatExactIfPossible.
* `short ToInt16Checked()`<br>
* `short ToInt16IfExact()`<br>
* `short ToInt16Unchecked()`<br>
* `int ToInt32Checked()`<br>
* `int ToInt32IfExact()`<br>
* `int ToInt32Unchecked()`<br>
* `long ToInt64Checked()`<br>
* `long ToInt64IfExact()`<br>
* `long ToInt64Unchecked()`<br>
* `ERational ToLowestTerms()`<br>
* `float ToSingle()`<br>
* `int ToSingleBits()`<br>
* `EInteger ToSizedEInteger​(int maxBitLength)`<br>
* `EInteger ToSizedEIntegerIfExact​(int maxBitLength)`<br>
* `java.lang.String toString()`<br>

## Field Details

### NaN
    public static final ERational NaN
### NegativeInfinity
    public static final ERational NegativeInfinity
### NegativeZero
    public static final ERational NegativeZero
### One
    public static final ERational One
### PositiveInfinity
    public static final ERational PositiveInfinity
### SignalingNaN
    public static final ERational SignalingNaN
### Ten
    public static final ERational Ten
### Zero
    public static final ERational Zero
## Method Details

### Copy
    public ERational Copy()
### getDenominator
    public final EInteger getDenominator()
### isFinite
    public final boolean isFinite()
### isNegative
    public final boolean isNegative()
### isZero
    public final boolean isZero()
### IsInteger
    public boolean IsInteger()
### getNumerator
    public final EInteger getNumerator()
### signum
    public final int signum()
### getUnsignedNumerator
    public final EInteger getUnsignedNumerator()
### Create
    public static ERational Create​(int numeratorSmall, int denominatorSmall)
### Create
    public static ERational Create​(long numeratorLong, long denominatorLong)
### Create
    public static ERational Create​(EInteger numerator, EInteger denominator)
### CreateNaN
    public static ERational CreateNaN​(EInteger diag)
### CreateNaN
    public static ERational CreateNaN​(EInteger diag, boolean signaling, boolean negative)
### FromDouble
    public static ERational FromDouble​(double flt)
### FromExtendedDecimal
    @Deprecated public static ERational FromExtendedDecimal​(EDecimal ef)
Deprecated.
Renamed to FromEDecimal.

### FromExtendedFloat
    @Deprecated public static ERational FromExtendedFloat​(EFloat ef)
Deprecated.
Renamed to FromEFloat.

### FromEDecimal
    public static ERational FromEDecimal​(EDecimal ef)
### FromEFloat
    public static ERational FromEFloat​(EFloat ef)
### FromEInteger
    public static ERational FromEInteger​(EInteger bigint)
### FromSingle
    public static ERational FromSingle​(float flt)
### FromSingleBits
    public static ERational FromSingleBits​(int value)
### FromDoubleBits
    public static ERational FromDoubleBits​(long value)
### FromString
    public static ERational FromString​(java.lang.String str)
### FromString
    public static ERational FromString​(java.lang.String str, int offset, int length)
### FromString
    public static ERational FromString​(char[] chars)
### FromString
    public static ERational FromString​(char[] chars, int offset, int length)
### FromString
    public static ERational FromString​(byte[] bytes)
### FromString
    public static ERational FromString​(byte[] bytes, int offset, int length)
### CompareToTotalMagnitude
    public int CompareToTotalMagnitude​(ERational other)
### CompareToTotal
    public int CompareToTotal​(ERational other)
### Abs
    public ERational Abs()
### Add
    public ERational Add​(ERational otherValue)
### compareTo
    public int compareTo​(ERational other)

**Specified by:**

* <code>compareTo</code> in interface <code>java.lang.Comparable&lt;ERational&gt;</code>

### CompareToValue
    public int CompareToValue​(ERational other)
### Max
    public static ERational Max​(ERational first, ERational second)
### MaxMagnitude
    public static ERational MaxMagnitude​(ERational first, ERational second)
### Min
    public static ERational Min​(ERational first, ERational second)
### MinMagnitude
    public static ERational MinMagnitude​(ERational first, ERational second)
### compareTo
    public int compareTo​(int intOther)
### CompareToValue
    public int CompareToValue​(int intOther)
### CompareToValue
    public int CompareToValue​(long intOther)
### compareTo
    public int compareTo​(long intOther)
### CompareToBinary
    public int CompareToBinary​(EFloat other)
### CompareToDecimal
    public int CompareToDecimal​(EDecimal other)
### CopySign
    public ERational CopySign​(ERational other)
### Divide
    public ERational Divide​(ERational otherValue)
### equals
    public boolean equals​(java.lang.Object obj)

**Overrides:**

* <code>equals</code> in class <code>java.lang.Object</code>

### equals
    public boolean equals​(ERational other)
### hashCode
    public int hashCode()

**Overrides:**

* <code>hashCode</code> in class <code>java.lang.Object</code>

### IsInfinity
    public boolean IsInfinity()
### IsNaN
    public boolean IsNaN()
### IsNegativeInfinity
    public boolean IsNegativeInfinity()
### IsPositiveInfinity
    public boolean IsPositiveInfinity()
### IsQuietNaN
    public boolean IsQuietNaN()
### IsSignalingNaN
    public boolean IsSignalingNaN()
### Multiply
    public ERational Multiply​(ERational otherValue)
### Negate
    public ERational Negate()
### Remainder
    public ERational Remainder​(ERational otherValue)
### Subtract
    public ERational Subtract​(ERational otherValue)
### ToDouble
    public double ToDouble()
### ToDoubleBits
    public long ToDoubleBits()
### ToSingleBits
    public int ToSingleBits()
### ToLowestTerms
    public ERational ToLowestTerms()
### ToSizedEInteger
    public EInteger ToSizedEInteger​(int maxBitLength)
### ToSizedEIntegerIfExact
    public EInteger ToSizedEIntegerIfExact​(int maxBitLength)
### ToEInteger
    public EInteger ToEInteger()
### ToEIntegerExact
    @Deprecated public EInteger ToEIntegerExact()
Deprecated.
Renamed to ToEIntegerIfExact.

### ToEIntegerIfExact
    public EInteger ToEIntegerIfExact()
### ToEDecimal
    public EDecimal ToEDecimal()
### ToEDecimal
    public EDecimal ToEDecimal​(EContext ctx)
### ToEDecimalExactIfPossible
    public EDecimal ToEDecimalExactIfPossible​(EContext ctx)
### ToExtendedDecimal
    @Deprecated public EDecimal ToExtendedDecimal()
Deprecated.
Renamed to ToEDecimal.

### ToExtendedDecimal
    @Deprecated public EDecimal ToExtendedDecimal​(EContext ctx)
Deprecated.
Renamed to ToEDecimal.

### ToExtendedDecimalExactIfPossible
    @Deprecated public EDecimal ToExtendedDecimalExactIfPossible​(EContext ctx)
Deprecated.
Renamed to ToEDecimalExactIfPossible.

### ToEFloat
    public EFloat ToEFloat()
### ToEFloat
    public EFloat ToEFloat​(EContext ctx)
### ToEFloatExactIfPossible
    public EFloat ToEFloatExactIfPossible​(EContext ctx)
### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat()
Deprecated.
Renamed to ToEFloat.

### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat​(EContext ctx)
Deprecated.
Renamed to ToEFloat.

### ToExtendedFloatExactIfPossible
    @Deprecated public EFloat ToExtendedFloatExactIfPossible​(EContext ctx)
Deprecated.
Renamed to ToEFloatExactIfPossible.

### ToSingle
    public float ToSingle()
### toString
    public java.lang.String toString()

**Overrides:**

* <code>toString</code> in class <code>java.lang.Object</code>

### Increment
    public ERational Increment()
### Decrement
    public ERational Decrement()
### Add
    public ERational Add​(int v)
### Subtract
    public ERational Subtract​(int v)
### Multiply
    public ERational Multiply​(int v)
### Divide
    public ERational Divide​(int v)
### Remainder
    public ERational Remainder​(int v)
### Add
    public ERational Add​(long v)
### Subtract
    public ERational Subtract​(long v)
### Multiply
    public ERational Multiply​(long v)
### Divide
    public ERational Divide​(long v)
### Remainder
    public ERational Remainder​(long v)
### ToByteChecked
    public byte ToByteChecked()
### ToByteUnchecked
    public byte ToByteUnchecked()
### ToByteIfExact
    public byte ToByteIfExact()
### FromByte
    public static ERational FromByte​(byte inputByte)
### ToInt16Checked
    public short ToInt16Checked()
### ToInt16Unchecked
    public short ToInt16Unchecked()
### ToInt16IfExact
    public short ToInt16IfExact()
### FromInt16
    public static ERational FromInt16​(short inputInt16)
### ToInt32Checked
    public int ToInt32Checked()
### ToInt32Unchecked
    public int ToInt32Unchecked()
### ToInt32IfExact
    public int ToInt32IfExact()
### FromBoolean
    public static ERational FromBoolean​(boolean boolValue)
### FromInt32
    public static ERational FromInt32​(int inputInt32)
### ToInt64Checked
    public long ToInt64Checked()
### ToInt64Unchecked
    public long ToInt64Unchecked()
### ToInt64IfExact
    public long ToInt64IfExact()
### FromInt64
    public static ERational FromInt64​(long inputInt64)
