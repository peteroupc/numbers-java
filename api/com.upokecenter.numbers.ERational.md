# com.upokecenter.numbers.ERational

    public final class ERational extends Object implements Comparable<ERational>

Represents an arbitrary-precision rational number. This class can't be
 inherited. (The "E" stands for "extended", meaning that instances of
 this class can be values other than numbers proper, such as infinity
 and not-a-number.) <p><b>Thread safety:</b> Instances of this class
 are immutable, so they are inherently safe for use by multiple
 threads. Multiple instances of this object with the same properties
 are interchangeable, so they should not be compared using the "=="
 operator (which might only check if each side of the operator is the
 same instance).</p>

## Fields

* `static ERational NaN`<br>
 A not-a-number value.
* `static ERational NegativeInfinity`<br>
 Negative infinity, less than any other number.
* `static ERational NegativeZero`<br>
 A rational number for negative zero.
* `static ERational One`<br>
 The rational number one.
* `static ERational PositiveInfinity`<br>
 Positive infinity, greater than any other number.
* `static ERational SignalingNaN`<br>
 A signaling not-a-number value.
* `static ERational Ten`<br>
 The rational number ten.
* `static ERational Zero`<br>
 A rational number for zero.

## Constructors

* `ERational​(EInteger numerator,
         EInteger denominator)`<br>
 Deprecated.
Use the ERational.Create method instead.
 Use the ERational.Create method instead.

## Methods

* `ERational Abs()`<br>
* `ERational Add​(ERational otherValue)`<br>
* `int compareTo​(ERational other)`<br>
* `int CompareToBinary​(EFloat other)`<br>
* `int CompareToDecimal​(EDecimal other)`<br>
* `int CompareToTotal​(ERational other)`<br>
* `int CompareToTotalMagnitude​(ERational other)`<br>
* `ERational CopySign​(ERational other)`<br>
* `static ERational Create​(int numeratorSmall,
      int denominatorSmall)`<br>
* `static ERational Create​(EInteger numerator,
      EInteger denominator)`<br>
* `static ERational CreateNaN​(EInteger diag)`<br>
* `static ERational CreateNaN​(EInteger diag,
         boolean signaling,
         boolean negative)`<br>
* `ERational Divide​(ERational otherValue)`<br>
* `boolean equals​(ERational other)`<br>
* `boolean equals​(Object obj)`<br>
* `static ERational FromByte​(byte inputByte)`<br>
* `static ERational FromDouble​(double flt)`<br>
* `static ERational FromEDecimal​(EDecimal ef)`<br>
* `static ERational FromEFloat​(EFloat ef)`<br>
* `static ERational FromEInteger​(EInteger bigint)`<br>
* `static ERational FromExtendedDecimal​(EDecimal ef)`<br>
 Deprecated.
Renamed to FromEDecimal.
 Renamed to FromEDecimal.
* `static ERational FromExtendedFloat​(EFloat ef)`<br>
 Deprecated.
Renamed to FromEFloat.
 Renamed to FromEFloat.
* `static ERational FromInt16​(short inputInt16)`<br>
* `static ERational FromInt32​(int inputInt32)`<br>
* `static ERational FromInt64​(long inputInt64)`<br>
* `static ERational FromSingle​(float flt)`<br>
* `static ERational FromString​(String str)`<br>
* `static ERational FromString​(String str,
          int offset,
          int length)`<br>
* `EInteger getDenominator()`<br>
* `EInteger getNumerator()`<br>
* `EInteger getUnsignedNumerator()`<br>
* `int hashCode()`<br>
* `boolean isFinite()`<br>
* `boolean IsInfinity()`<br>
* `boolean IsNaN()`<br>
* `boolean isNegative()`<br>
* `boolean IsNegativeInfinity()`<br>
* `boolean IsPositiveInfinity()`<br>
* `boolean IsQuietNaN()`<br>
* `boolean IsSignalingNaN()`<br>
* `boolean isZero()`<br>
* `ERational Multiply​(ERational otherValue)`<br>
* `ERational Negate()`<br>
* `ERational Remainder​(ERational otherValue)`<br>
* `int signum()`<br>
* `ERational Subtract​(ERational otherValue)`<br>
* `byte ToByteChecked()`<br>
* `byte ToByteIfExact()`<br>
* `byte ToByteUnchecked()`<br>
* `double ToDouble()`<br>
* `EDecimal ToEDecimal()`<br>
* `EDecimal ToEDecimal​(EContext ctx)`<br>
* `EDecimal ToEDecimalExactIfPossible​(EContext ctx)`<br>
* `EFloat ToEFloat()`<br>
* `EFloat ToEFloat​(EContext ctx)`<br>
* `EFloat ToEFloatExactIfPossible​(EContext ctx)`<br>
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
* `EDecimal ToExtendedDecimal​(EContext ctx)`<br>
 Deprecated.
Renamed to ToEDecimal.
 Renamed to ToEDecimal.
* `EDecimal ToExtendedDecimalExactIfPossible​(EContext ctx)`<br>
 Deprecated.
Renamed to ToEDecimalExactIfPossible.
 Renamed to ToEDecimalExactIfPossible.
* `EFloat ToExtendedFloat()`<br>
 Deprecated.
Renamed to ToEFloat.
 Renamed to ToEFloat.
* `EFloat ToExtendedFloat​(EContext ctx)`<br>
 Deprecated.
Renamed to ToEFloat.
 Renamed to ToEFloat.
* `EFloat ToExtendedFloatExactIfPossible​(EContext ctx)`<br>
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
* `float ToSingle()`<br>
* `String toString()`<br>

## Field Details

### NaN
    public static final ERational NaN
A not-a-number value.
### NegativeInfinity
    public static final ERational NegativeInfinity
Negative infinity, less than any other number.
### NegativeZero
    public static final ERational NegativeZero
A rational number for negative zero.
### One
    public static final ERational One
The rational number one.
### PositiveInfinity
    public static final ERational PositiveInfinity
Positive infinity, greater than any other number.
### SignalingNaN
    public static final ERational SignalingNaN
A signaling not-a-number value.
### Ten
    public static final ERational Ten
The rational number ten.
### Zero
    public static final ERational Zero
A rational number for zero.
## Method Details

### getDenominator
    public final EInteger getDenominator()
### isFinite
    public final boolean isFinite()
### isNegative
    public final boolean isNegative()
### isZero
    public final boolean isZero()
### getNumerator
    public final EInteger getNumerator()
### signum
    public final int signum()
### getUnsignedNumerator
    public final EInteger getUnsignedNumerator()
### Create
    public static ERational Create​(int numeratorSmall, int denominatorSmall)

**Parameters:**

* <code>numeratorSmall</code> - Not documented yet.

* <code>denominatorSmall</code> - Not documented yet.

**Returns:**

* An ERational object.

### Create
    public static ERational Create​(EInteger numerator, EInteger denominator)

**Parameters:**

* <code>numerator</code> - Not documented yet.

* <code>denominator</code> - Not documented yet.

**Returns:**

* An ERational object.

### CreateNaN
    public static ERational CreateNaN​(EInteger diag)

**Parameters:**

* <code>diag</code> - Not documented yet.

**Returns:**

* An ERational object.

### CreateNaN
    public static ERational CreateNaN​(EInteger diag, boolean signaling, boolean negative)

**Parameters:**

* <code>diag</code> - Not documented yet.

* <code>signaling</code> - Not documented yet.

* <code>negative</code> - Not documented yet. (3).

**Returns:**

* An ERational object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### FromDouble
    public static ERational FromDouble​(double flt)

**Parameters:**

* <code>flt</code> - Not documented yet.

**Returns:**

* An ERational object.

### FromExtendedDecimal
    @Deprecated public static ERational FromExtendedDecimal​(EDecimal ef)
Deprecated.
<div class='deprecationComment'>Renamed to FromEDecimal.</div>

**Parameters:**

* <code>ef</code> - Not documented yet.

**Returns:**

* An ERational object.

### FromExtendedFloat
    @Deprecated public static ERational FromExtendedFloat​(EFloat ef)
Deprecated.
<div class='deprecationComment'>Renamed to FromEFloat.</div>

**Parameters:**

* <code>ef</code> - Not documented yet.

**Returns:**

* An ERational object.

### FromEDecimal
    public static ERational FromEDecimal​(EDecimal ef)

**Parameters:**

* <code>ef</code> - Not documented yet.

**Returns:**

* An ERational object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### FromEFloat
    public static ERational FromEFloat​(EFloat ef)

**Parameters:**

* <code>ef</code> - Not documented yet.

**Returns:**

* An ERational object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### FromEInteger
    public static ERational FromEInteger​(EInteger bigint)

**Parameters:**

* <code>bigint</code> - Not documented yet.

**Returns:**

* An ERational object.

### FromSingle
    public static ERational FromSingle​(float flt)

**Parameters:**

* <code>flt</code> - Not documented yet.

**Returns:**

* An ERational object.

### FromString
    public static ERational FromString​(String str)

**Parameters:**

* <code>str</code> - Not documented yet.

**Returns:**

* An ERational object.

### FromString
    public static ERational FromString​(String str, int offset, int length)

**Parameters:**

* <code>str</code> - Not documented yet.

* <code>offset</code> - A zero-based index showing where the desired portion of <code>
 str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

**Returns:**

* An ERational object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

* <code>IllegalArgumentException</code> - Either "offset" or "length" is less than 0 or
 greater than "str"'s length, or "str"'s length minus "offset" is less
 than "length".

### CompareToTotalMagnitude
    public int CompareToTotalMagnitude​(ERational other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToTotal
    public int CompareToTotal​(ERational other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### Abs
    public ERational Abs()

**Returns:**

* An ERational object.

### Add
    public ERational Add​(ERational otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* An ERational object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### compareTo
    public int compareTo​(ERational other)

**Specified by:**

* <code>compareTo</code>&nbsp;in interface&nbsp;<code>Comparable&lt;ERational&gt;</code>

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToBinary
    public int CompareToBinary​(EFloat other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CompareToDecimal
    public int CompareToDecimal​(EDecimal other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

### CopySign
    public ERational CopySign​(ERational other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* An ERational object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### Divide
    public ERational Divide​(ERational otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* An ERational object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### equals
    public boolean equals​(Object obj)

**Overrides:**

* <code>equals</code>&nbsp;in class&nbsp;<code>Object</code>

**Parameters:**

* <code>obj</code> - Not documented yet.

**Returns:**

* A Boolean object.

### equals
    public boolean equals​(ERational other)

**Parameters:**

* <code>other</code> - Not documented yet.

**Returns:**

* A Boolean object.

### hashCode
    public int hashCode()

**Overrides:**

* <code>hashCode</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A 32-bit signed integer.

### IsInfinity
    public boolean IsInfinity()

**Returns:**

* A Boolean object.

### IsNaN
    public boolean IsNaN()

**Returns:**

* A Boolean object.

### IsNegativeInfinity
    public boolean IsNegativeInfinity()

**Returns:**

* A Boolean object.

### IsPositiveInfinity
    public boolean IsPositiveInfinity()

**Returns:**

* A Boolean object.

### IsQuietNaN
    public boolean IsQuietNaN()

**Returns:**

* A Boolean object.

### IsSignalingNaN
    public boolean IsSignalingNaN()

**Returns:**

* A Boolean object.

### Multiply
    public ERational Multiply​(ERational otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* An ERational object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### Negate
    public ERational Negate()

**Returns:**

* An ERational object.

### Remainder
    public ERational Remainder​(ERational otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* An ERational object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### Subtract
    public ERational Subtract​(ERational otherValue)

**Parameters:**

* <code>otherValue</code> - Not documented yet.

**Returns:**

* An ERational object.

**Throws:**

* <code>NullPointerException</code> - The parameter is null.

### ToDouble
    public double ToDouble()

**Returns:**

* A 64-bit floating-point number.

### ToEInteger
    public EInteger ToEInteger()

**Returns:**

* An EInteger object.

### ToEIntegerExact
    @Deprecated public EInteger ToEIntegerExact()
Deprecated.
<div class='deprecationComment'>Renamed to ToEIntegerIfExact.</div>

**Returns:**

* An EInteger object.

### ToEIntegerIfExact
    public EInteger ToEIntegerIfExact()

**Returns:**

* An EInteger object.

### ToEDecimal
    public EDecimal ToEDecimal()

**Returns:**

* An EDecimal object.

### ToEDecimal
    public EDecimal ToEDecimal​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### ToEDecimalExactIfPossible
    public EDecimal ToEDecimalExactIfPossible​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### ToExtendedDecimal
    @Deprecated public EDecimal ToExtendedDecimal()
Deprecated.
<div class='deprecationComment'>Renamed to ToEDecimal.</div>

**Returns:**

* An EDecimal object.

### ToExtendedDecimal
    @Deprecated public EDecimal ToExtendedDecimal​(EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to ToEDecimal.</div>

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### ToExtendedDecimalExactIfPossible
    @Deprecated public EDecimal ToExtendedDecimalExactIfPossible​(EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to ToEDecimalExactIfPossible.</div>

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### ToEFloat
    public EFloat ToEFloat()

**Returns:**

* An EFloat object.

### ToEFloat
    public EFloat ToEFloat​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### ToEFloatExactIfPossible
    public EFloat ToEFloatExactIfPossible​(EContext ctx)

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat()
Deprecated.
<div class='deprecationComment'>Renamed to ToEFloat.</div>

**Returns:**

* An EFloat object.

### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat​(EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to ToEFloat.</div>

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### ToExtendedFloatExactIfPossible
    @Deprecated public EFloat ToExtendedFloatExactIfPossible​(EContext ctx)
Deprecated.
<div class='deprecationComment'>Renamed to ToEFloatExactIfPossible.</div>

**Parameters:**

* <code>ctx</code> - Not documented yet.

**Returns:**

* An EFloat object.

### ToSingle
    public float ToSingle()

**Returns:**

* A 32-bit floating-point number.

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

### ToByteIfExact
    public byte ToByteIfExact()

**Returns:**

* A Byte object.

### FromByte
    public static ERational FromByte​(byte inputByte)

**Parameters:**

* <code>inputByte</code> - Not documented yet.

**Returns:**

* An ERational object.

### ToInt16Checked
    public short ToInt16Checked()

**Returns:**

* A 16-bit signed integer.

### ToInt16Unchecked
    public short ToInt16Unchecked()

**Returns:**

* A 16-bit signed integer.

### ToInt16IfExact
    public short ToInt16IfExact()

**Returns:**

* A 16-bit signed integer.

### FromInt16
    public static ERational FromInt16​(short inputInt16)

**Parameters:**

* <code>inputInt16</code> - Not documented yet.

**Returns:**

* An ERational object.

### ToInt32Checked
    public int ToInt32Checked()

**Returns:**

* A 32-bit signed integer.

### ToInt32Unchecked
    public int ToInt32Unchecked()

**Returns:**

* A 32-bit signed integer.

### ToInt32IfExact
    public int ToInt32IfExact()

**Returns:**

* A 32-bit signed integer.

### FromInt32
    public static ERational FromInt32​(int inputInt32)

**Parameters:**

* <code>inputInt32</code> - Not documented yet.

**Returns:**

* An ERational object.

### ToInt64Checked
    public long ToInt64Checked()

**Returns:**

* A 64-bit signed integer.

### ToInt64Unchecked
    public long ToInt64Unchecked()

**Returns:**

* A 64-bit signed integer.

### ToInt64IfExact
    public long ToInt64IfExact()

**Returns:**

* A 64-bit signed integer.

### FromInt64
    public static ERational FromInt64​(long inputInt64)

**Parameters:**

* <code>inputInt64</code> - Not documented yet.

**Returns:**

* An ERational object.
