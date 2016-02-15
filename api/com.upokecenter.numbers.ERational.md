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

* `ERational(EInteger numerator,
         EInteger denominator) ERational`<br>
 Initializes a new instance of the ERational
 class.

## Methods

* `ERational Abs()`<br>
 Not documented yet.
* `ERational Add(ERational otherValue)`<br>
 Adds two rational numbers.
* `int compareTo(ERational other)`<br>
 Compares an arbitrary-precision rational number with this instance.
* `int CompareToBinary(EFloat other)`<br>
 Compares an arbitrary-precision binary float with this instance.
* `int CompareToDecimal(EDecimal other)`<br>
 Compares an arbitrary-precision decimal number with this instance.
* `int CompareToTotal(ERational other)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values.
* `int CompareToTotalMagnitude(ERational other)`<br>
 Compares the absolute values of this object and another object, imposing a
 total ordering on all possible values (ignoring their signs).
* `ERational CopySign(ERational other)`<br>
 Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number.
* `static ERational Create(EInteger numerator,
      EInteger denominator)`<br>
 Creates a rational number with the given numerator and denominator.
* `static ERational Create(int numeratorSmall,
      int denominatorSmall)`<br>
 Creates a rational number with the given numerator and denominator.
* `static ERational CreateNaN(EInteger diag)`<br>
 Creates a not-a-number arbitrary-precision rational number.
* `static ERational CreateNaN(EInteger diag,
         boolean signaling,
         boolean negative)`<br>
 Creates a not-a-number arbitrary-precision rational number.
* `ERational Divide(ERational otherValue)`<br>
 Divides this instance by the value of an arbitrary-precision rational number
 object.
* `boolean equals(ERational other)`<br>
 Not documented yet.
* `boolean equals(Object obj)`<br>
 Determines whether this object and another object are equal.
* `static ERational FromByte(byte inputByte)`<br>
 Converts a byte (from 0 to 255) to an arbitrary-precision rational number.
* `static ERational FromDouble(double flt)`<br>
 Converts a 64-bit floating-point number to a rational number.
* `static ERational FromEDecimal(EDecimal ef)`<br>
 Converts an arbitrary-precision decimal number to a rational number.
* `static ERational FromEFloat(EFloat ef)`<br>
 Not documented yet.
* `static ERational FromEInteger(EInteger bigint)`<br>
 Converts an arbitrary-precision integer to a rational number.
* `static ERational FromExtendedDecimal(EDecimal ef)`<br>
 Deprecated.
Renamed to FromEDecimal.
 Renamed to FromEDecimal.
* `static ERational FromExtendedFloat(EFloat ef)`<br>
 Deprecated.
Renamed to FromEFloat.
 Renamed to FromEFloat.
* `static ERational FromInt16(short inputInt16)`<br>
 Converts a 16-bit signed integer to an arbitrary-precision rational number.
* `static ERational FromInt32(int inputInt32)`<br>
 Converts a 32-bit signed integer to an arbitrary-precision rational number.
* `static ERational FromInt64(long inputInt64)`<br>
 Converts a 64-bit signed integer to an arbitrary-precision rational number.
* `static ERational FromSingle(float flt)`<br>
 Converts a 32-bit floating-point number to a rational number.
* `static ERational FromString(String str)`<br>
 Creates a rational number from a text string that represents a number.
* `static ERational FromString(String str,
          int offset,
          int length)`<br>
 Creates a rational number from a text string that represents a
 number.
* `EInteger getDenominator()`<br>
 Gets this object's denominator.
* `EInteger getNumerator()`<br>
 Gets this object's numerator.
* `EInteger getUnsignedNumerator()`<br>
 Gets this object's numerator with the sign removed.
* `int hashCode()`<br>
 Returns the hash code for this instance.
* `boolean isFinite()`<br>
 Gets a value indicating whether this object is finite (not infinity or NaN).
* `boolean IsInfinity()`<br>
 Gets a value indicating whether this object's value is infinity.
* `boolean IsNaN()`<br>
 Returns whether this object is a not-a-number value.
* `boolean isNegative()`<br>
 Gets a value indicating whether this object's value is negative (including
 negative zero).
* `boolean IsNegativeInfinity()`<br>
 Returns whether this object is negative infinity.
* `boolean IsPositiveInfinity()`<br>
 Returns whether this object is positive infinity.
* `boolean IsQuietNaN()`<br>
 Returns whether this object is a quiet not-a-number value.
* `boolean IsSignalingNaN()`<br>
 Returns whether this object is a signaling not-a-number value (which causes
 an error if the value is passed to any arithmetic operation in this
 class).
* `boolean isZero()`<br>
 Gets a value indicating whether this object's value equals 0.
* `ERational Multiply(ERational otherValue)`<br>
 Multiplies this instance by the value of an arbitrary-precision rational
 number.
* `ERational Negate()`<br>
 Returns a rational number with the sign reversed.
* `ERational Remainder(ERational otherValue)`<br>
 Finds the remainder that results when this instance is divided by the value
 of an arbitrary-precision rational number.
* `int signum()`<br>
 Gets the sign of this rational number.
* `ERational Subtract(ERational otherValue)`<br>
 Subtracts an arbitrary-precision rational number from this instance.
* `byte ToByteChecked()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) after truncating to an integer.
* `byte ToByteIfExact()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) without rounding to a different numerical value.
* `byte ToByteUnchecked()`<br>
 Truncates this number's value to an integer and returns the
 least-significant bits of its two's-complement form as a byte (from 0
 to 255).
* `double ToDouble()`<br>
 Converts this value to a 64-bit floating-point number.
* `EDecimal ToEDecimal()`<br>
 Converts this rational number to a decimal number.
* `EDecimal ToEDecimal(EContext ctx)`<br>
 Converts this rational number to a decimal number and rounds the result to
 the given precision.
* `EDecimal ToEDecimalExactIfPossible(EContext ctx)`<br>
 Converts this rational number to a decimal number, but if the result would
 have a nonterminating decimal expansion, rounds that result to the
 given precision.
* `EFloat ToEFloat()`<br>
 Converts this rational number to a binary float.
* `EFloat ToEFloat(EContext ctx)`<br>
 Converts this rational number to a binary float and rounds that result to
 the given precision.
* `EFloat ToEFloatExactIfPossible(EContext ctx)`<br>
 Converts this rational number to a binary float, but if the result would
 have a nonterminating binary expansion, rounds that result to the
 given precision.
* `EInteger ToEInteger()`<br>
 Converts this value to an arbitrary-precision integer.
* `EInteger ToEIntegerExact()`<br>
 Deprecated.
Renamed to ToEIntegerIfExact.
 Renamed to ToEIntegerIfExact.
* `EInteger ToEIntegerIfExact()`<br>
* `EDecimal ToExtendedDecimal()`<br>
 Deprecated.
Renamed to ToEDecimal.
 Renamed to ToEDecimal.
* `EDecimal ToExtendedDecimal(EContext ctx)`<br>
 Deprecated.
Renamed to ToEDecimal.
 Renamed to ToEDecimal.
* `EDecimal ToExtendedDecimalExactIfPossible(EContext ctx)`<br>
 Deprecated.
Renamed to ToEDecimalExactIfPossible.
 Renamed to ToEDecimalExactIfPossible.
* `EFloat ToExtendedFloat()`<br>
 Deprecated.
Renamed to ToEFloat.
 Renamed to ToEFloat.
* `EFloat ToExtendedFloat(EContext ctx)`<br>
 Deprecated.
Renamed to ToEFloat.
 Renamed to ToEFloat.
* `EFloat ToExtendedFloatExactIfPossible(EContext ctx)`<br>
 Deprecated.
Renamed to ToEFloatExactIfPossible.
 Renamed to ToEFloatExactIfPossible.
* `short ToInt16Checked()`<br>
 Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer after truncating to an integer.
* `short ToInt16IfExact()`<br>
 Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer without rounding to a different numerical
 value.
* `short ToInt16Unchecked()`<br>
 Truncates this number's value to an integer and returns the
 least-significant bits of its two's-complement form as a 16-bit
 signed integer.
* `int ToInt32Checked()`<br>
 Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer after truncating to an integer.
* `int ToInt32IfExact()`<br>
 Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer without rounding to a different numerical
 value.
* `int ToInt32Unchecked()`<br>
 Truncates this number's value to an integer and returns the
 least-significant bits of its two's-complement form as a 32-bit
 signed integer.
* `long ToInt64Checked()`<br>
 Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer after truncating to an integer.
* `long ToInt64IfExact()`<br>
 Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer without rounding to a different numerical
 value.
* `long ToInt64Unchecked()`<br>
 Truncates this number's value to an integer and returns the
 least-significant bits of its two's-complement form as a 64-bit
 signed integer.
* `float ToSingle()`<br>
 Converts this value to a 32-bit floating-point number.
* `String toString()`<br>
 Converts this object to a text string.

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
Gets this object's denominator.

**Returns:**

* This object's denominator.

### isFinite
    public final boolean isFinite()
Gets a value indicating whether this object is finite (not infinity or NaN).

**Returns:**

* <code>true</code> if this object is finite (not infinity or not-a-number
 (NaN)); otherwise, <code>false</code>.

### isNegative
    public final boolean isNegative()
Gets a value indicating whether this object's value is negative (including
 negative zero).

**Returns:**

* <code>true</code> if this object's value is negative; otherwise, <code>false</code>.

### isZero
    public final boolean isZero()
Gets a value indicating whether this object's value equals 0.

**Returns:**

* <code>true</code> if this object's value equals 0; otherwise, <code>false</code>.

### getNumerator
    public final EInteger getNumerator()
Gets this object's numerator.

**Returns:**

* This object's numerator. If this object is a not-a-number value,
 returns the diagnostic information (which will be negative if this
 object is negative).

### signum
    public final int signum()
Gets the sign of this rational number.

**Returns:**

* Zero if this value is zero or negative zero; -1 if this value is
 less than 0; and 1 if this value is greater than 0.

### getUnsignedNumerator
    public final EInteger getUnsignedNumerator()
Gets this object's numerator with the sign removed.

**Returns:**

* This object's numerator. If this object is a not-a-number value,
 returns the diagnostic information.

### Create
    public static ERational Create(int numeratorSmall, int denominatorSmall)
Creates a rational number with the given numerator and denominator.

**Parameters:**

* <code>numeratorSmall</code> - The numerator.

* <code>denominatorSmall</code> - The denominator.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>IllegalArgumentException</code> - The denominator is zero.

### Create
    public static ERational Create(EInteger numerator, EInteger denominator)
Creates a rational number with the given numerator and denominator.

**Parameters:**

* <code>numerator</code> - The numerator.

* <code>denominator</code> - The denominator.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>IllegalArgumentException</code> - The denominator is zero.

### CreateNaN
    public static ERational CreateNaN(EInteger diag)
Creates a not-a-number arbitrary-precision rational number.

**Parameters:**

* <code>diag</code> - A number to use as diagnostic information associated with this
 object. If none is needed, should be zero.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>diag</code> is null.

* <code>IllegalArgumentException</code> - The parameter <code>diag</code> is less than 0.

### CreateNaN
    public static ERational CreateNaN(EInteger diag, boolean signaling, boolean negative)
Creates a not-a-number arbitrary-precision rational number.

**Parameters:**

* <code>diag</code> - A number to use as diagnostic information associated with this
 object. If none is needed, should be zero.

* <code>signaling</code> - Whether the return value will be signaling (true) or quiet
 (false).

* <code>negative</code> - Whether the return value is negative.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>diag</code> is null.

* <code>IllegalArgumentException</code> - The parameter <code>diag</code> is less than 0.

### FromDouble
    public static ERational FromDouble(double flt)
Converts a 64-bit floating-point number to a rational number. This method
 computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the number to a
 string.

**Parameters:**

* <code>flt</code> - A 64-bit floating-point number.

**Returns:**

* A rational number with the same value as <code>flt</code>.

### FromExtendedDecimal
    @Deprecated public static ERational FromExtendedDecimal(EDecimal ef)
Deprecated.&nbsp;<i>Renamed to FromEDecimal.</i>

**Parameters:**

* <code>ef</code> - An arbitrary-precision decimal number.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>ef</code> is null.

### FromExtendedFloat
    @Deprecated public static ERational FromExtendedFloat(EFloat ef)
Deprecated.&nbsp;<i>Renamed to FromEFloat.</i>

**Parameters:**

* <code>ef</code> - An arbitrary-precision binary float.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>ef</code> is null.

### FromEDecimal
    public static ERational FromEDecimal(EDecimal ef)
Converts an arbitrary-precision decimal number to a rational number.

**Parameters:**

* <code>ef</code> - An arbitrary-precision decimal number.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>ef</code> is null.

### FromEFloat
    public static ERational FromEFloat(EFloat ef)
Not documented yet.

**Parameters:**

* <code>ef</code> - An arbitrary-precision binary float.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>ef</code> is null.

### FromEInteger
    public static ERational FromEInteger(EInteger bigint)
Converts an arbitrary-precision integer to a rational number.

**Parameters:**

* <code>bigint</code> - An arbitrary-precision integer.

**Returns:**

* The exact value of the integer as a rational number.

### FromSingle
    public static ERational FromSingle(float flt)
Converts a 32-bit floating-point number to a rational number. This method
 computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the number to a
 string.

**Parameters:**

* <code>flt</code> - A 32-bit floating-point number.

**Returns:**

* A rational number with the same value as <code>flt</code>.

### FromString
    public static ERational FromString(String str)
Creates a rational number from a text string that represents a number. See
 <code>FromString(String, int, int)</code> for more information.

**Parameters:**

* <code>str</code> - A string that represents a number.

**Returns:**

* An arbitrary-precision rational number with the same value as the
 given string.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

### FromString
    public static ERational FromString(String str, int offset, int length)
<p>Creates a rational number from a text string that represents a
 number.</p> <p>The format of the string generally consists of:</p>
 <ul> <li>An optional plus sign ("+" , U+002B) or minus sign ("-",
 U+002D) (if '-' , the value is negative.)</li> <li>The numerator in
 the form of one or more digits.</li> <li>Optionally, "/" followed by
 the denominator in the form of one or more digits. If a denominator
 is not given, it's equal to 1.</li></ul> <p>The string can also be
 "-INF", "-Infinity", "Infinity", "INF", quiet NaN ("NaN" /"-NaN")
 followed by any number of digits, or signaling NaN ("sNaN" /"-sNaN")
 followed by any number of digits, all in any combination of upper and
 lower case.</p> <p>All characters mentioned above are the
 corresponding characters in the Basic Latin range. In particular, the
 digits must be the basic digits 0 to 9 (U + 0030 to U + 0039). The string
 is not allowed to contain white space characters, including
 spaces.</p>

**Parameters:**

* <code>str</code> - A text string, a portion of which represents a number.

* <code>offset</code> - A zero-based index that identifies the start of the number.

* <code>length</code> - The length of the number within the string.

**Returns:**

* An ERational object.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

### CompareToTotalMagnitude
    public int CompareToTotalMagnitude(ERational other)
Compares the absolute values of this object and another object, imposing a
 total ordering on all possible values (ignoring their signs). In this
 method: <ul> <li>For objects with the same value, the one with the
 higher denominator has a greater "absolute value".</li> <li>Negative
 zero and positive zero are considered equal.</li> <li>Quiet NaN has a
 higher "absolute value" than signaling NaN. If both objects are quiet
 NaN or both are signaling NaN, the one with the higher diagnostic
 information has a greater "absolute value".</li> <li>NaN has a higher
 "absolute value" than infinity.</li> <li>Infinity has a higher
 "absolute value" than any finite number.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number to compare with this
 one.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.

### CompareToTotal
    public int CompareToTotal(ERational other)
Compares the values of this object and another object, imposing a total
 ordering on all possible values. In this method: <ul> <li>For objects
 with the same value, the one with the higher denominator has a
 greater "absolute value".</li> <li>Negative zero is less than
 positive zero.</li> <li>Quiet NaN has a higher "absolute value" than
 signaling NaN. If both objects are quiet NaN or both are signaling
 NaN, the one with the higher diagnostic information has a greater
 "absolute value".</li> <li>NaN has a higher "absolute value" than
 infinity.</li> <li>Infinity has a higher "absolute value" than any
 finite number.</li> <li>Negative numbers are less than positive
 numbers.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number to compare with this
 one.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.

### Abs
    public ERational Abs()
Not documented yet.

**Returns:**

* An arbitrary-precision rational number.

### Add
    public ERational Add(ERational otherValue)
Adds two rational numbers.

**Parameters:**

* <code>otherValue</code> - Another arbitrary-precision rational number.

**Returns:**

* The sum of the two numbers. Returns not-a-number (NaN) if either
 operand is NaN.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>otherValue</code> is
 null.

### compareTo
    public int compareTo(ERational other)
Compares an arbitrary-precision rational number with this instance.

**Specified by:**

* <code>compareTo</code>&nbsp;in interface&nbsp;<code>Comparable&lt;ERational&gt;</code>

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater.

### CompareToBinary
    public int CompareToBinary(EFloat other)
Compares an arbitrary-precision binary float with this instance.

**Parameters:**

* <code>other</code> - An arbitrary-precision binary float.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater.

### CompareToDecimal
    public int CompareToDecimal(EDecimal other)
Compares an arbitrary-precision decimal number with this instance.

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater.

### CopySign
    public ERational CopySign(ERational other)
Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number.

**Parameters:**

* <code>other</code> - A number whose sign will be copied.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>other</code> is null.

### Divide
    public ERational Divide(ERational otherValue)
Divides this instance by the value of an arbitrary-precision rational number
 object.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The quotient of the two objects.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>otherValue</code> is
 null.

### equals
    public boolean equals(Object obj)
Determines whether this object and another object are equal.

**Overrides:**

* <code>equals</code>&nbsp;in class&nbsp;<code>Object</code>

**Parameters:**

* <code>obj</code> - An arbitrary object.

**Returns:**

* <code>true</code> if the objects are equal; otherwise, false .

### equals
    public boolean equals(ERational other)
Not documented yet.

**Parameters:**

* <code>other</code> - The parameter <code>other</code> is not documented yet.

**Returns:**

* A Boolean object.

### hashCode
    public int hashCode()
Returns the hash code for this instance.

**Overrides:**

* <code>hashCode</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A 32-bit hash code.

### IsInfinity
    public boolean IsInfinity()
Gets a value indicating whether this object's value is infinity.

**Returns:**

* <code>true</code> if this object's value is infinity; otherwise, false .

### IsNaN
    public boolean IsNaN()
Returns whether this object is a not-a-number value.

**Returns:**

* <code>true</code> if this object is a not-a-number value; otherwise,
 false .

### IsNegativeInfinity
    public boolean IsNegativeInfinity()
Returns whether this object is negative infinity.

**Returns:**

* <code>true</code> if this object is negative infinity; otherwise, false .

### IsPositiveInfinity
    public boolean IsPositiveInfinity()
Returns whether this object is positive infinity.

**Returns:**

* <code>true</code> if this object is positive infinity; otherwise, false .

### IsQuietNaN
    public boolean IsQuietNaN()
Returns whether this object is a quiet not-a-number value.

**Returns:**

* <code>true</code> if this object is a quiet not-a-number value;
 otherwise, false .

### IsSignalingNaN
    public boolean IsSignalingNaN()
Returns whether this object is a signaling not-a-number value (which causes
 an error if the value is passed to any arithmetic operation in this
 class).

**Returns:**

* <code>true</code> if this object is a signaling not-a-number value (which
 causes an error if the value is passed to any arithmetic operation in
 this class); otherwise, false .

### Multiply
    public ERational Multiply(ERational otherValue)
Multiplies this instance by the value of an arbitrary-precision rational
 number.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The product of the two numbers.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>otherValue</code> is
 null.

### Negate
    public ERational Negate()
Returns a rational number with the sign reversed.

**Returns:**

* An arbitrary-precision rational number.

### Remainder
    public ERational Remainder(ERational otherValue)
Finds the remainder that results when this instance is divided by the value
 of an arbitrary-precision rational number.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The remainder of the two numbers.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>otherValue</code> is
 null.

### Subtract
    public ERational Subtract(ERational otherValue)
Subtracts an arbitrary-precision rational number from this instance.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The difference of the two objects.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>otherValue</code> is
 null.

### ToDouble
    public double ToDouble()
Converts this value to a 64-bit floating-point number. The half-even
 rounding mode is used.

**Returns:**

* The closest 64-bit floating-point number to this value. The return
 value can be positive infinity or negative infinity if this value
 exceeds the range of a 64-bit floating point number.

### ToEInteger
    public EInteger ToEInteger()
Converts this value to an arbitrary-precision integer. Any fractional part
 in this value will be discarded when converting to an
 arbitrary-precision integer.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is infinity or
 not-a-number (NaN).

### ToEIntegerExact
    @Deprecated public EInteger ToEIntegerExact()
Deprecated.&nbsp;<i>Renamed to ToEIntegerIfExact.</i>

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is infinity or
 not-a-number (NaN).

* <code>ArithmeticException</code> - This object's value is not an exact integer.

### ToEIntegerIfExact
    public EInteger ToEIntegerIfExact()
### ToEDecimal
    public EDecimal ToEDecimal()
Converts this rational number to a decimal number.

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating decimal
 expansion.

### ToEDecimal
    public EDecimal ToEDecimal(EContext ctx)
Converts this rational number to a decimal number and rounds the result to
 the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is true,
 will also store the flags resulting from the operation (the flags are
 in addition to the pre-existing flags). Can be null, in which case
 the precision is unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result
 can't be exact because it has a nonterminating decimal expansion.

### ToEDecimalExactIfPossible
    public EDecimal ToEDecimalExactIfPossible(EContext ctx)
Converts this rational number to a decimal number, but if the result would
 have a nonterminating decimal expansion, rounds that result to the
 given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only if
 the exact result would have a nonterminating decimal expansion. If
 HasFlags of the context is true, will also store the flags resulting
 from the operation (the flags are in addition to the pre-existing
 flags). Can be null, in which case the precision is unlimited and no
 rounding is needed.

**Returns:**

* The exact value of the rational number if possible; otherwise, the
 rounded version of the result if a context is given. Returns
 not-a-number (NaN) if the context is null and the result can't be
 exact because it has a nonterminating decimal expansion.

### ToExtendedDecimal
    @Deprecated public EDecimal ToExtendedDecimal()
Deprecated.&nbsp;<i>Renamed to ToEDecimal.</i>

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating decimal
 expansion.

### ToExtendedDecimal
    @Deprecated public EDecimal ToExtendedDecimal(EContext ctx)
Deprecated.&nbsp;<i>Renamed to ToEDecimal.</i>

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is true,
 will also store the flags resulting from the operation (the flags are
 in addition to the pre-existing flags). Can be null, in which case
 the precision is unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result
 can't be exact because it has a nonterminating decimal expansion.

### ToExtendedDecimalExactIfPossible
    @Deprecated public EDecimal ToExtendedDecimalExactIfPossible(EContext ctx)
Deprecated.&nbsp;<i>Renamed to ToEDecimalExactIfPossible.</i>

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only if
 the exact result would have a nonterminating decimal expansion. If
 HasFlags of the context is true, will also store the flags resulting
 from the operation (the flags are in addition to the pre-existing
 flags). Can be null, in which case the precision is unlimited and no
 rounding is needed.

**Returns:**

* The exact value of the rational number if possible; otherwise, the
 rounded version of the result if a context is given. Returns
 not-a-number (NaN) if the context is null and the result can't be
 exact because it has a nonterminating decimal expansion.

### ToEFloat
    public EFloat ToEFloat()
Converts this rational number to a binary float.

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating binary
 expansion.

### ToEFloat
    public EFloat ToEFloat(EContext ctx)
Converts this rational number to a binary float and rounds that result to
 the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is true,
 will also store the flags resulting from the operation (the flags are
 in addition to the pre-existing flags). Can be null, in which case
 the precision is unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result
 can't be exact because it has a nonterminating binary expansion.

### ToEFloatExactIfPossible
    public EFloat ToEFloatExactIfPossible(EContext ctx)
Converts this rational number to a binary float, but if the result would
 have a nonterminating binary expansion, rounds that result to the
 given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only if
 the exact result would have a nonterminating binary expansion. If
 HasFlags of the context is true, will also store the flags resulting
 from the operation (the flags are in addition to the pre-existing
 flags). Can be null, in which case the precision is unlimited and no
 rounding is needed.

**Returns:**

* The exact value of the rational number if possible; otherwise, the
 rounded version of the result if a context is given. Returns
 not-a-number (NaN) if the context is null and the result can't be
 exact because it has a nonterminating binary expansion.

### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat()
Deprecated.&nbsp;<i>Renamed to ToEFloat.</i>

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating binary
 expansion.

### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat(EContext ctx)
Deprecated.&nbsp;<i>Renamed to ToEFloat.</i>

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is true,
 will also store the flags resulting from the operation (the flags are
 in addition to the pre-existing flags). Can be null, in which case
 the precision is unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result
 can't be exact because it has a nonterminating binary expansion.

### ToExtendedFloatExactIfPossible
    @Deprecated public EFloat ToExtendedFloatExactIfPossible(EContext ctx)
Deprecated.&nbsp;<i>Renamed to ToEFloatExactIfPossible.</i>

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only if
 the exact result would have a nonterminating binary expansion. If
 HasFlags of the context is true, will also store the flags resulting
 from the operation (the flags are in addition to the pre-existing
 flags). Can be null, in which case the precision is unlimited and no
 rounding is needed.

**Returns:**

* The exact value of the rational number if possible; otherwise, the
 rounded version of the result if a context is given. Returns
 not-a-number (NaN) if the context is null and the result can't be
 exact because it has a nonterminating binary expansion.

### ToSingle
    public float ToSingle()
Converts this value to a 32-bit floating-point number. The half-even
 rounding mode is used.

**Returns:**

* The closest 32-bit floating-point number to this value. The return
 value can be positive infinity or negative infinity if this value
 exceeds the range of a 32-bit floating point number.

### toString
    public String toString()
Converts this object to a text string.

**Overrides:**

* <code>toString</code>&nbsp;in class&nbsp;<code>Object</code>

**Returns:**

* A string representation of this object. If this object's value is
 infinity or not-a-number, the result is the analogous return value of
 the <code>EDecimal.toString</code> method. Otherwise, the return value has
 the following form: <code>[-]numerator/denominator</code>.

### ToByteChecked
    public byte ToByteChecked()
Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) after truncating to an integer.

**Returns:**

* This number's value, truncated to a byte (from 0 to 255).

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or
 the truncated integer is outside the range of a byte (from 0 to 255).

### ToByteUnchecked
    public byte ToByteUnchecked()
Truncates this number's value to an integer and returns the
 least-significant bits of its two's-complement form as a byte (from 0
 to 255).

**Returns:**

* This number, converted to a byte (from 0 to 255). Returns 0 if this
 value is infinity or not-a-number.

### ToByteIfExact
    public byte ToByteIfExact()
Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) without rounding to a different numerical value.

**Returns:**

* This number's value as a byte (from 0 to 255).

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or
 the number can't fit in a byte (from 0 to 255) without rounding to a
 different numerical value.

### FromByte
    public static ERational FromByte(byte inputByte)
Converts a byte (from 0 to 255) to an arbitrary-precision rational number.

**Parameters:**

* <code>inputByte</code> - The number to convert as a byte (from 0 to 255).

**Returns:**

* This number's value as an arbitrary-precision rational number.

### ToInt16Checked
    public short ToInt16Checked()
Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer after truncating to an integer.

**Returns:**

* This number's value, truncated to a 16-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or
 the truncated integer is outside the range of a 16-bit signed
 integer.

### ToInt16Unchecked
    public short ToInt16Unchecked()
Truncates this number's value to an integer and returns the
 least-significant bits of its two's-complement form as a 16-bit
 signed integer.

**Returns:**

* This number, converted to a 16-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### ToInt16IfExact
    public short ToInt16IfExact()
Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer without rounding to a different numerical
 value.

**Returns:**

* This number's value as a 16-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or
 the number can't fit in a 16-bit signed integer without rounding to a
 different numerical value.

### FromInt16
    public static ERational FromInt16(short inputInt16)
Converts a 16-bit signed integer to an arbitrary-precision rational number.

**Parameters:**

* <code>inputInt16</code> - The number to convert as a 16-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision rational number.

### ToInt32Checked
    public int ToInt32Checked()
Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer after truncating to an integer.

**Returns:**

* This number's value, truncated to a 32-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or
 the truncated integer is outside the range of a 32-bit signed
 integer.

### ToInt32Unchecked
    public int ToInt32Unchecked()
Truncates this number's value to an integer and returns the
 least-significant bits of its two's-complement form as a 32-bit
 signed integer.

**Returns:**

* This number, converted to a 32-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### ToInt32IfExact
    public int ToInt32IfExact()
Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer without rounding to a different numerical
 value.

**Returns:**

* This number's value as a 32-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or
 the number can't fit in a 32-bit signed integer without rounding to a
 different numerical value.

### FromInt32
    public static ERational FromInt32(int inputInt32)
Converts a 32-bit signed integer to an arbitrary-precision rational number.

**Parameters:**

* <code>inputInt32</code> - The number to convert as a 32-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision rational number.

### ToInt64Checked
    public long ToInt64Checked()
Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer after truncating to an integer.

**Returns:**

* This number's value, truncated to a 64-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or
 the truncated integer is outside the range of a 64-bit signed
 integer.

### ToInt64Unchecked
    public long ToInt64Unchecked()
Truncates this number's value to an integer and returns the
 least-significant bits of its two's-complement form as a 64-bit
 signed integer.

**Returns:**

* This number, converted to a 64-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### ToInt64IfExact
    public long ToInt64IfExact()
Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer without rounding to a different numerical
 value.

**Returns:**

* This number's value as a 64-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or
 the number can't fit in a 64-bit signed integer without rounding to a
 different numerical value.

### FromInt64
    public static ERational FromInt64(long inputInt64)
Converts a 64-bit signed integer to an arbitrary-precision rational number.

**Parameters:**

* <code>inputInt64</code> - The number to convert as a 64-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision rational number.
