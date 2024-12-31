# com.upokecenter.numbers.ERational

    public final class ERational extends Object implements Comparable<ERational>

<p>Represents an arbitrary-precision rational number. This class can't be
 inherited. (The "E" stands for "extended", meaning that instances of this
 class can be values other than numbers proper, such as infinity and
 not-a-number.) In this class, a rational number consists of a numerator and
 denominator, each an arbitrary-precision integer (EInteger), and this class
 does not automatically convert rational numbers to lowest terms. </p>
 <p><b>Thread safety:</b> Instances of this class are immutable, so they are
 inherently safe for use by multiple threads. Multiple instances of this
 object with the same properties are interchangeable, so they should not be
 compared using the "==" operator (which might only check if each side of the
 operator is the same instance).</p>

## Fields

* `static final ERational NaN`<br>
 A not-a-number value.

* `static final ERational NegativeInfinity`<br>
 Negative infinity, less than any other number.

* `static final ERational NegativeZero`<br>
 A rational number for negative zero.

* `static final ERational One`<br>
 The rational number one.

* `static final ERational PositiveInfinity`<br>
 Positive infinity, greater than any other number.

* `static final ERational SignalingNaN`<br>
 A signaling not-a-number value.

* `static final ERational Ten`<br>
 The rational number ten.

* `static final ERational Zero`<br>
 A rational number for zero.

## Constructors

## Methods

* `ERational Abs()`<br>
 Returns the absolute value of this rational number, that is, a number with
 the same value as this one but as a nonnegative number.

* `ERational Add(int v)`<br>
 Adds this arbitrary-precision rational number and a 32-bit signed integer
 and returns the result.

* `ERational Add(long v)`<br>
 Adds this arbitrary-precision rational number and a 64-bit signed integer
 and returns the result.

* `ERational Add(ERational otherValue)`<br>
 Adds this arbitrary-precision rational number and another
 arbitrary-precision rational number and returns the result.

* `int compareTo(int intOther)`<br>
 Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance.

* `int compareTo(long intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.

* `int compareTo(ERational other)`<br>
 Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance.

* `int CompareToBinary(EFloat other)`<br>
 Compares an arbitrary-precision binary floating-point number with this
 instance.

* `int CompareToDecimal(EDecimal other)`<br>
 Compares an arbitrary-precision decimal number with this instance.

* `int CompareToTotal(ERational other)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values.

* `int CompareToTotalMagnitude(ERational other)`<br>
 Compares the absolute values of this object and another object, imposing
 a total ordering on all possible values (ignoring their signs).

* `int CompareToValue(int intOther)`<br>
 Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance.

* `int CompareToValue(long intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.

* `int CompareToValue(ERational other)`<br>
 Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance.

* `ERational Copy()`<br>
 Creates a copy of this arbitrary-precision rational number.

* `ERational CopySign(ERational other)`<br>
 Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number.

* `static ERational Create(int numeratorSmall,
 int denominatorSmall)`<br>
 Creates a rational number with the given numerator and denominator.

* `static ERational Create(long numeratorLong,
 long denominatorLong)`<br>
 Creates a rational number with the given numerator and denominator.

* `static ERational Create(EInteger numerator,
 EInteger denominator)`<br>
 Creates a rational number with the given numerator and denominator.

* `static ERational CreateNaN(EInteger diag)`<br>
 Creates a not-a-number arbitrary-precision rational number.

* `static ERational CreateNaN(EInteger diag,
 boolean signaling,
 boolean negative)`<br>
 Creates a not-a-number arbitrary-precision rational number.

* `ERational Decrement()`<br>
 Subtracts one from an arbitrary-precision rational number.

* `ERational Divide(int v)`<br>
 Divides this arbitrary-precision rational number by a 32-bit signed integer
 and returns the result.

* `ERational Divide(long v)`<br>
 Divides this arbitrary-precision rational number by a 64-bit signed integer
 and returns the result.

* `ERational Divide(ERational otherValue)`<br>
 Divides this arbitrary-precision rational number by another
 arbitrary-precision rational number and returns the result.

* `boolean equals(ERational other)`<br>
 Determines whether this object's numerator, denominator, and properties are
 equal to those of another object.

* `boolean equals(Object obj)`<br>
 Determines whether this object's numerator, denominator, and properties are
 equal to those of another object and that other object is an
 arbitrary-precision rational number.

* `static ERational FromBoolean(boolean boolValue)`<br>
 Converts a Boolean value (true or false) to an arbitrary-precision rational
 number.

* `static ERational FromByte(byte inputByte)`<br>
 Converts a byte (from 0 to 255) to an arbitrary-precision rational number.

* `static ERational FromDouble(double flt)`<br>
 Converts a 64-bit floating-point number to a rational number.

* `static ERational FromDoubleBits(long value)`<br>
 Creates a binary rational number from a 64-bit floating-point number encoded
 in the IEEE 754 binary64 format.

* `static ERational FromEDecimal(EDecimal ef)`<br>
 Converts an arbitrary-precision decimal number to a rational number.

* `static ERational FromEFloat(EFloat ef)`<br>
 Converts an arbitrary-precision binary floating-point number to a rational
 number.

* `static ERational FromEInteger(EInteger bigint)`<br>
 Converts an arbitrary-precision integer to a rational number.

* `static ERational FromExtendedDecimal(EDecimal ef)`<br>
 Deprecated.
Renamed to FromEDecimal.

* `static ERational FromExtendedFloat(EFloat ef)`<br>
 Deprecated.
Renamed to FromEFloat.

* `static ERational FromHalfBits(short value)`<br>
 Creates a binary rational number from a binary floating-point number encoded
 in the IEEE 754 binary16 format (also known as a "half-precision"
 floating-point number).

* `static ERational FromInt16(short inputInt16)`<br>
 Converts a 16-bit signed integer to an arbitrary-precision rational number.

* `static ERational FromInt32(int inputInt32)`<br>
 Converts a 32-bit signed integer to an arbitrary-precision rational number.

* `static ERational FromInt64(long inputInt64)`<br>
 Converts a 64-bit signed integer to an arbitrary-precision rational number.

* `static ERational FromInt64AsUnsigned(long longerValue)`<br>
 Converts an unsigned integer expressed as a 64-bit signed integer to an
 arbitrary-precision rational number.

* `static ERational FromSingle(float flt)`<br>
 Converts a 32-bit binary floating-point number to a rational number.

* `static ERational FromSingleBits(int value)`<br>
 Creates a binary rational number from a 32-bit floating-point number encoded
 in the IEEE 754 binary32 format.

* `static ERational FromString(byte[] bytes)`<br>
 Creates a rational number from a sequence of bytes that represents a number.

* `static ERational FromString(byte[] bytes,
 int offset,
 int length)`<br>
 Creates a rational number from a sequence of bytes that represents a
 number.

* `static ERational FromString(char[] chars)`<br>
 Creates a rational number from a sequence of char s that represents
 a number.

* `static ERational FromString(char[] chars,
 int offset,
 int length)`<br>
 Creates a rational number from a sequence of char s that
 represents a number.

* `static ERational FromString(String str)`<br>
 Creates a rational number from a text string that represents a number.

* `static ERational FromString(String str,
 int offset,
 int length)`<br>
 Creates a rational number from a text string that represents a
 number.

* `final EInteger getDenominator()`<br>
 Gets this object's denominator.

* `final EInteger getNumerator()`<br>
 Gets this object's numerator.

* `final EInteger getUnsignedNumerator()`<br>
 Gets this object's numerator with the sign removed.

* `int hashCode()`<br>
 Returns the hash code for this instance.

* `ERational Increment()`<br>
 Adds one to an arbitrary-precision rational number.

* `final boolean isFinite()`<br>
 Gets a value indicating whether this object is finite (not infinity or NaN).

* `boolean IsInfinity()`<br>
 Gets a value indicating whether this object's value is infinity.

* `boolean IsInteger()`<br>
 Returns whether this object's value is an integer.

* `boolean IsNaN()`<br>
 Returns whether this object is a not-a-number value.

* `final boolean isNegative()`<br>
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
 an error if the value is passed to any arithmetic operation in this class).

* `final boolean isZero()`<br>
 Gets a value indicating whether this object's value equals 0.

* `static ERational Max(ERational first,
 ERational second)`<br>
 Gets the greater value between two rational numbers.

* `static ERational MaxMagnitude(ERational first,
 ERational second)`<br>
 Gets the greater value between two values, ignoring their signs.

* `static ERational Min(ERational first,
 ERational second)`<br>
 Gets the lesser value between two rational numbers.

* `static ERational MinMagnitude(ERational first,
 ERational second)`<br>
 Gets the lesser value between two values, ignoring their signs.

* `ERational Multiply(int v)`<br>
 Multiplies this arbitrary-precision rational number by a 32-bit signed
 integer and returns the result.

* `ERational Multiply(long v)`<br>
 Multiplies this arbitrary-precision rational number by a 64-bit signed
 integer and returns the result.

* `ERational Multiply(ERational otherValue)`<br>
 Multiplies this arbitrary-precision rational number by another
 arbitrary-precision rational number and returns the result.

* `ERational Negate()`<br>
 Returns a rational number with the same value as this one but with the sign
 reversed.

* `ERational Remainder(int v)`<br>
 Returns the remainder that would result when this arbitrary-precision
 rational number is divided by a 32-bit signed integer.

* `ERational Remainder(long v)`<br>
 Returns the remainder that would result when this arbitrary-precision
 rational number is divided by a 64-bit signed integer.

* `ERational Remainder(ERational otherValue)`<br>
 Returns the remainder that would result when this arbitrary-precision
 rational number is divided by another arbitrary-precision rational number.

* `final int signum()`<br>
 Gets the sign of this rational number.

* `ERational Subtract(int v)`<br>
 Subtracts a 32-bit signed integer from this arbitrary-precision rational
 number and returns the result.

* `ERational Subtract(long v)`<br>
 Subtracts a 64-bit signed integer from this arbitrary-precision rational
 number and returns the result.

* `ERational Subtract(ERational otherValue)`<br>
 Subtracts an arbitrary-precision rational number from this
 arbitrary-precision rational number and returns the result.

* `byte ToByteChecked()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) after converting it to an integer by discarding its
 fractional part.

* `byte ToByteIfExact()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) without rounding to a different numerical value.

* `byte ToByteUnchecked()`<br>
 Converts this number's value to an integer (using ToEInteger), and returns
 the least-significant bits of that integer's two's-complement form as a byte
 (from 0 to 255).

* `double ToDouble()`<br>
 Converts this value to a 64-bit floating-point number.

* `long ToDoubleBits()`<br>
 Converts this value to its closest equivalent as a 64-bit floating-point
 number, expressed as an integer in the IEEE 754 binary64 format.

* `EDecimal ToEDecimal()`<br>
 Converts this rational number to an arbitrary-precision decimal number.

* `EDecimal ToEDecimal(EContext ctx)`<br>
 Converts this rational number to an arbitrary-precision decimal number and
 rounds the result to the given precision.

* `EDecimal ToEDecimalExactIfPossible(EContext ctx)`<br>
 Converts this rational number to an arbitrary-precision decimal number, but
 if the result would have a nonterminating decimal expansion, rounds that
 result to the given precision.

* `EFloat ToEFloat()`<br>
 Converts this rational number to a binary floating-point number.

* `EFloat ToEFloat(EContext ctx)`<br>
 Converts this rational number to a binary floating-point number and rounds
 that result to the given precision.

* `EFloat ToEFloatExactIfPossible(EContext ctx)`<br>
 Converts this rational number to a binary floating-point number, but if the
 result would have a nonterminating binary expansion, rounds that result to
 the given precision.

* `EInteger ToEInteger()`<br>
 Converts this value to an arbitrary-precision integer by dividing the
 numerator by the denominator and discarding the fractional part of the
 result.

* `EInteger ToEIntegerExact()`<br>
 Deprecated.
Renamed to ToEIntegerIfExact.

* `EInteger ToEIntegerIfExact()`<br>
 Converts this value to an arbitrary-precision integer, checking whether the
 value is an exact integer.

* `EDecimal ToExtendedDecimal()`<br>
 Deprecated.
Renamed to ToEDecimal.

* `EDecimal ToExtendedDecimal(EContext ctx)`<br>
 Deprecated.
Renamed to ToEDecimal.

* `EDecimal ToExtendedDecimalExactIfPossible(EContext ctx)`<br>
 Deprecated.
Renamed to ToEDecimalExactIfPossible.

* `EFloat ToExtendedFloat()`<br>
 Deprecated.
Renamed to ToEFloat.

* `EFloat ToExtendedFloat(EContext ctx)`<br>
 Deprecated.
Renamed to ToEFloat.

* `EFloat ToExtendedFloatExactIfPossible(EContext ctx)`<br>
 Deprecated.
Renamed to ToEFloatExactIfPossible.

* `short ToHalfBits()`<br>
 Converts this value to its closest equivalent as a binary floating-point
 number, expressed as an integer in the IEEE 754 binary16 format (also known
 as a "half-precision" floating-point number).

* `short ToInt16Checked()`<br>
 Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer after converting it to an integer by discarding its
 fractional part.

* `short ToInt16IfExact()`<br>
 Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer without rounding to a different numerical value.

* `short ToInt16Unchecked()`<br>
 Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement form as
 a 16-bit signed integer.

* `int ToInt32Checked()`<br>
 Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer after converting it to an integer by discarding its
 fractional part.

* `int ToInt32IfExact()`<br>
 Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer without rounding to a different numerical value.

* `int ToInt32Unchecked()`<br>
 Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement form as
 a 32-bit signed integer.

* `long ToInt64Checked()`<br>
 Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer after converting it to an integer by discarding its
 fractional part.

* `long ToInt64IfExact()`<br>
 Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer without rounding to a different numerical value.

* `long ToInt64Unchecked()`<br>
 Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement form as
 a 64-bit signed integer.

* `ERational ToLowestTerms()`<br>
 Converts this value to its form in lowest terms.

* `float ToSingle()`<br>
 Converts this value to a 32-bit binary floating-point number.

* `int ToSingleBits()`<br>
 Converts this value to its closest equivalent as 32-bit floating-point
 number, expressed as an integer in the IEEE 754 binary32 format.

* `EInteger ToSizedEInteger(int maxBitLength)`<br>
 Converts this value to an arbitrary-precision integer by dividing the
 numerator by the denominator, discarding its fractional part, and checking
 whether the resulting integer overflows the given signed bit count.

* `EInteger ToSizedEIntegerIfExact(int maxBitLength)`<br>
 Converts this value to an arbitrary-precision integer, only if this number's
 value is an exact integer and that integer does not overflow the given
 signed bit count.

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

### Copy
    public ERational Copy()
Creates a copy of this arbitrary-precision rational number.

**Returns:**

* An arbitrary-precision rational number.

### getDenominator
    public final EInteger getDenominator()
Gets this object's denominator.

**Returns:**

* This object's denominator.

### isFinite
    public final boolean isFinite()
Gets a value indicating whether this object is finite (not infinity or NaN).

**Returns:**

* <code>true</code> if this object is finite (not infinity or NaN);
 otherwise, <code>false</code>.

### isNegative
    public final boolean isNegative()
Gets a value indicating whether this object's value is negative (including
 negative zero).

**Returns:**

* <code>true</code> if this object's value is negative (including negative
 zero); otherwise, <code>false</code>. <code>true</code> if this object's value is
 negative; otherwise, <code>false</code>.

### isZero
    public final boolean isZero()
Gets a value indicating whether this object's value equals 0.

**Returns:**

* <code>true</code> if this object's value equals 0; otherwise, <code>
 false</code>. <code>true</code> if this object's value equals 0; otherwise, <code>
 false</code>.

### IsInteger
    public boolean IsInteger()
Returns whether this object's value is an integer.

**Returns:**

* <code>true</code> if this object's value is an integer; otherwise, <code>
 false</code>.

### getNumerator
    public final EInteger getNumerator()
Gets this object's numerator.

**Returns:**

* This object's numerator. If this object is a not-a-number value,
 returns the diagnostic information (which will be negative if this object is
 negative).

### signum
    public final int signum()
Gets the sign of this rational number.

**Returns:**

* The sign of this rational number.

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
    public static ERational Create(long numeratorLong, long denominatorLong)
Creates a rational number with the given numerator and denominator.

**Parameters:**

* <code>numeratorLong</code> - The numerator.

* <code>denominatorLong</code> - The denominator.

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

* <code>NullPointerException</code> - The parameter <code>numerator</code> or <code>
 denominator</code> is null.

### CreateNaN
    public static ERational CreateNaN(EInteger diag)
Creates a not-a-number arbitrary-precision rational number.

**Parameters:**

* <code>diag</code> - An integer, 0 or greater, to use as diagnostic information
 associated with this object. If none is needed, should be zero. To get the
 diagnostic information from another arbitrary-precision rational number, use
 that object's <code>UnsignedNumerator</code> property.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>IllegalArgumentException</code> - The parameter <code>diag</code> is less than 0.

### CreateNaN
    public static ERational CreateNaN(EInteger diag, boolean signaling, boolean negative)
Creates a not-a-number arbitrary-precision rational number.

**Parameters:**

* <code>diag</code> - An integer, 0 or greater, to use as diagnostic information
 associated with this object. If none is needed, should be zero. To get the
 diagnostic information from another arbitrary-precision rational number, use
 that object's <code>UnsignedNumerator</code> property.

* <code>signaling</code> - Whether the return value will be signaling (true) or quiet
 (false).

* <code>negative</code> - Whether the return value is negative.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>IllegalArgumentException</code> - The parameter <code>diag</code> is less than 0.

* <code>NullPointerException</code> - The parameter <code>diag</code> is null.

### FromDouble
    public static ERational FromDouble(double flt)
<p>Converts a 64-bit floating-point number to a rational number. This method
 computes the exact value of the floating point number, not an approximation,
 as is often the case by converting the number to a string. </p> <p>The input
 value can be a not-a-number (NaN) value (such as <code>Double.NaN</code>);
 however, NaN values have multiple forms that are equivalent for many
 applications' purposes, and <code>Double.NaN</code> is only one of these
 equivalent forms. In fact, <code>ERational.FromDouble(Double.NaN)</code> could
 produce an object that is represented differently between DotNet and Java,
 because <code>Double.NaN</code> may have a different form in DotNet and Java (for
 example, the NaN value's sign may be negative in DotNet, but positive in
 Java). Use `IsNaN()` to determine whether an object from this class stores a
 NaN value of any form.</p>

**Parameters:**

* <code>flt</code> - The parameter <code>flt</code> is a 64-bit floating-point number.

**Returns:**

* A rational number with the same value as <code>flt</code>.

### FromExtendedDecimal
    @Deprecated public static ERational FromExtendedDecimal(EDecimal ef)
Converts an arbitrary-precision decimal number to a rational number.

**Parameters:**

* <code>ef</code> - The number to convert as an arbitrary-precision decimal number.

**Returns:**

* An arbitrary-precision rational number.

### FromExtendedFloat
    @Deprecated public static ERational FromExtendedFloat(EFloat ef)
Converts an arbitrary-precision binary floating-point number to a rational
 number.

**Parameters:**

* <code>ef</code> - The number to convert as an arbitrary-precision binary
 floating-point number.

**Returns:**

* An arbitrary-precision rational number.

### FromEDecimal
    public static ERational FromEDecimal(EDecimal ef)
Converts an arbitrary-precision decimal number to a rational number.

**Parameters:**

* <code>ef</code> - The number to convert as an arbitrary-precision decimal number.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>ef</code> is null.

* <code>IllegalArgumentException</code> - doesn't satisfy den.signum() &gt;= 0.

### FromEFloat
    public static ERational FromEFloat(EFloat ef)
Converts an arbitrary-precision binary floating-point number to a rational
 number.

**Parameters:**

* <code>ef</code> - The number to convert as an arbitrary-precision binary
 floating-point number.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>ef</code> is null.

* <code>IllegalArgumentException</code> - doesn't satisfy den.signum() &gt;= 0.

### FromEInteger
    public static ERational FromEInteger(EInteger bigint)
Converts an arbitrary-precision integer to a rational number.

**Parameters:**

* <code>bigint</code> - The number to convert as an arbitrary-precision integer.

**Returns:**

* The exact value of the integer as a rational number.

### FromSingle
    public static ERational FromSingle(float flt)
<p>Converts a 32-bit binary floating-point number to a rational number. This
 method computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the number to a string.
 </p> <p>The input value can be a not-a-number (NaN) value (such as <code>
 Float.NaN</code> in DotNet or Float.NaN in Java); however, NaN values have
 multiple forms that are equivalent for many applications' purposes, and
 <code>Float.NaN</code> / <code>Float.NaN</code> is only one of these equivalent forms.
 In fact, <code>ERational.FromSingle(Float.NaN)</code> or <code>
 ERational.FromSingle(Float.NaN)</code> could produce an object that is represented
 differently between DotNet and Java, because <code>Float.NaN</code> / <code>
 Float.NaN</code> may have a different form in DotNet and Java (for example, the
 NaN value's sign may be negative in DotNet, but positive in Java). Use
 `IsNaN()` to determine whether an object from this class stores a NaN value
 of any form.</p>

**Parameters:**

* <code>flt</code> - The parameter <code>flt</code> is a 32-bit binary floating-point
 number.

**Returns:**

* A rational number with the same value as <code>flt</code>.

### FromSingleBits
    public static ERational FromSingleBits(int value)
Creates a binary rational number from a 32-bit floating-point number encoded
 in the IEEE 754 binary32 format. This method computes the exact value of the
 floating point number, not an approximation, as is often the case by
 converting the number to a string.

**Parameters:**

* <code>value</code> - A 32-bit integer encoded in the IEEE 754 binary32 format.

**Returns:**

* A rational number with the same floating-point value as <code>
 value</code>.

### FromHalfBits
    public static ERational FromHalfBits(short value)
Creates a binary rational number from a binary floating-point number encoded
 in the IEEE 754 binary16 format (also known as a "half-precision"
 floating-point number). This method computes the exact value of the floating
 point number, not an approximation, as is often the case by converting the
 number to a string.

**Parameters:**

* <code>value</code> - A 16-bit integer encoded in the IEEE 754 binary16 format.

**Returns:**

* A rational number with the same floating-point value as <code>
 value</code>.

### FromDoubleBits
    public static ERational FromDoubleBits(long value)
Creates a binary rational number from a 64-bit floating-point number encoded
 in the IEEE 754 binary64 format. This method computes the exact value of the
 floating point number, not an approximation, as is often the case by
 converting the number to a string.

**Parameters:**

* <code>value</code> - A 64-bit integer encoded in the IEEE 754 binary64 format.

**Returns:**

* A rational number with the same floating-point value as <code>
 value</code>.

### FromString
    public static ERational FromString(String str)
Creates a rational number from a text string that represents a number. See
 <code>FromString(string, int, int)</code> for more information.

**Parameters:**

* <code>str</code> - A string that represents a number.

**Returns:**

* An arbitrary-precision rational number with the same value as the
 given string.

**Throws:**

* <code>NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

### FromString
    public static ERational FromString(String str, int offset, int length)
<p>Creates a rational number from a text string that represents a
 number.</p> <p>The format of the string generally consists of:</p> <ul>
 <li>An optional plus sign ("+" , U+002B) or minus sign ("-", U+002D) (if '-'
 , the value is negative.)</li><li>The numerator in the form of one or more
 digits (these digits may begin with any number of
 zeros).</li><li>Optionally, "/" followed by the denominator in the form of
 one or more digits (these digits may begin with any number of zeros). If a
 denominator is not given, it's equal to 1.</li></ul> <p>The string can also
 be "-INF", "-Infinity", "Infinity", "INF", quiet NaN ("NaN" /"-NaN")
 followed by any number of digits, or signaling NaN ("sNaN" /"-sNaN")
 followed by any number of digits, all in any combination of uppercase and
 lowercase.</p> <p>All characters mentioned earlier are the corresponding
 characters in the Basic Latin range. In particular, the digits must be the
 basic digits 0 to 9 (U+0030 to U+0039). The string is not allowed to contain
 white space characters, including spaces.</p>

**Parameters:**

* <code>str</code> - A text string, a portion of which represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

* <code>NullPointerException</code> - The parameter <code>str</code> is null.

* <code>IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>str</code> 's length, or <code>str</code> 's length minus
 <code>offset</code> is less than <code>length</code>.

### FromString
    public static ERational FromString(char[] chars)
Creates a rational number from a sequence of <code>char</code> s that represents
 a number. See <code>FromString(string, int, int)</code> for more information.

**Parameters:**

* <code>chars</code> - A sequence of <code>char</code> s that represents a number.

**Returns:**

* An arbitrary-precision rational number with the same value as the
 given sequence of <code>char</code> s.

**Throws:**

* <code>NumberFormatException</code> - The parameter <code>chars</code> is not a correctly
 formatted sequence of <code>char</code> s.

### FromString
    public static ERational FromString(char[] chars, int offset, int length)
<p>Creates a rational number from a sequence of <code>char</code> s that
 represents a number.</p> <p>The format of the sequence of <code>char</code> s
 generally consists of:</p> <ul> <li>An optional plus sign ("+" , U+002B) or
 minus sign ("-", U+002D) (if '-' , the value is negative.)</li><li>The
 numerator in the form of one or more digits (these digits may begin with any
 number of zeros).</li><li>Optionally, "/" followed by the denominator in the
 form of one or more digits (these digits may begin with any number of
 zeros). If a denominator is not given, it's equal to 1.</li></ul> <p>The
 sequence of <code>char</code> s can also be "-INF", "-Infinity", "Infinity",
 "INF", quiet NaN ("NaN" /"-NaN") followed by any number of digits, or
 signaling NaN ("sNaN" /"-sNaN") followed by any number of digits, all in any
 combination of uppercase and lowercase.</p> <p>All characters mentioned earlier
 are the corresponding characters in the Basic Latin range. In particular,
 the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The sequence
 of <code>char</code> s is not allowed to contain white space characters,
 including spaces.</p>

**Parameters:**

* <code>chars</code> - A sequence of <code>char</code> s, a portion of which represents a
 number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>chars</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 chars</code> (but not more than <code>chars</code> 's length).

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NumberFormatException</code> - The parameter <code>chars</code> is not a correctly
 formatted sequence of <code>char</code> s.

* <code>NullPointerException</code> - The parameter <code>chars</code> is null.

* <code>IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>chars</code> 's length, or <code>chars</code> 's length
 minus <code>offset</code> is less than <code>length</code>.

### FromString
    public static ERational FromString(byte[] bytes)
Creates a rational number from a sequence of bytes that represents a number.
 See <code>FromString(string, int, int)</code> for more information.

**Parameters:**

* <code>bytes</code> - A sequence of bytes that represents a number.

**Returns:**

* An arbitrary-precision rational number with the same value as the
 given sequence of bytes.

**Throws:**

* <code>NumberFormatException</code> - The parameter <code>bytes</code> is not a correctly
 formatted sequence of bytes.

### FromString
    public static ERational FromString(byte[] bytes, int offset, int length)
<p>Creates a rational number from a sequence of bytes that represents a
 number.</p> <p>The format of the sequence of bytes generally consists
 of:</p> <ul> <li>An optional plus sign ("+" , U+002B) or minus sign ("-",
 U+002D) (if '-' , the value is negative.)</li><li>The numerator in the form
 of one or more digits (these digits may begin with any number of
 zeros).</li><li>Optionally, "/" followed by the denominator in the form of
 one or more digits (these digits may begin with any number of zeros). If a
 denominator is not given, it's equal to 1.</li></ul> <p>The sequence of
 bytes can also be "-INF", "-Infinity", "Infinity", "INF", quiet NaN ("NaN"
 /"-NaN") followed by any number of digits, or signaling NaN ("sNaN"
 /"-sNaN") followed by any number of digits, all in any combination of uppercase and lowercase.</p> <p>All characters mentioned earlier are the
 corresponding characters in the Basic Latin range. In particular, the digits
 must be the basic digits 0 to 9 (U+0030 to U+0039). The sequence of bytes is
 not allowed to contain white space characters, including spaces.</p>

**Parameters:**

* <code>bytes</code> - A sequence of bytes, a portion of which represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>bytes</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 bytes</code> (but not more than <code>bytes</code> 's length).

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>NumberFormatException</code> - The parameter <code>bytes</code> is not a correctly
 formatted sequence of bytes.

* <code>NullPointerException</code> - The parameter <code>bytes</code> is null.

* <code>IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>bytes</code> 's length, or <code>bytes</code> 's length
 minus <code>offset</code> is less than <code>length</code>.

### CompareToTotalMagnitude
    public int CompareToTotalMagnitude(ERational other)
<p>Compares the absolute values of this object and another object, imposing
 a total ordering on all possible values (ignoring their signs). In this
 method: </p> <ul> <li>For objects with the same value, the one with the
 higher denominator has a greater "absolute value".</li><li>Negative zero and
 positive zero are considered equal.</li><li>Quiet NaN has a higher "absolute
 value" than signaling NaN. If both objects are quiet NaN or both are
 signaling NaN, the one with the higher diagnostic information has a greater
 "absolute value".</li><li>NaN has a higher "absolute value" than
 infinity.</li><li>Infinity has a higher "absolute value" than any finite
 number.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number to compare with this
 one.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater. This
 implementation returns a positive number if.

### CompareToTotal
    public int CompareToTotal(ERational other)
<p>Compares the values of this object and another object, imposing a total
 ordering on all possible values. In this method: </p> <ul> <li>For objects
 with the same value, the one with the higher denominator has a greater
 "absolute value".</li><li>Negative zero is less than positive
 zero.</li><li>Quiet NaN has a higher "absolute value" than signaling NaN. If
 both objects are quiet NaN or both are signaling NaN, the one with the
 higher diagnostic information has a greater "absolute value".</li><li>NaN
 has a higher "absolute value" than infinity.</li><li>Infinity has a higher
 "absolute value" than any finite number.</li><li>Negative numbers are less
 than positive numbers.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number to compare with this
 one.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater. This
 implementation returns a positive number if.

### Abs
    public ERational Abs()
Returns the absolute value of this rational number, that is, a number with
 the same value as this one but as a nonnegative number.

**Returns:**

* An arbitrary-precision rational number.

### Add
    public ERational Add(ERational otherValue)
Adds this arbitrary-precision rational number and another
 arbitrary-precision rational number and returns the result.

**Parameters:**

* <code>otherValue</code> - Another arbitrary-precision rational number.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 rational number plus another arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>otherValue</code> is null.

### compareTo
    public int compareTo(ERational other)
Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance. This method currently uses the rules given in
 the CompareToValue method, so that it it is not consistent with the Equals
 method, but it may change in a future version to use the rules for the
 CompareToTotal method instead.

**Specified by:**

* <code>compareTo</code> in interface <code>Comparable&lt;ERational&gt;</code>

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater. This implementation
 returns a positive number if.

### CompareToValue
    public int CompareToValue(ERational other)
Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance. In this method, NaN values are greater than any
 other ERational value, and two NaN values (even if their payloads differ)
 are treated as equal by this method. This method is not consistent with the
 Equals method.

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater. This implementation
 returns a positive number if.

### Max
    public static ERational Max(ERational first, ERational second)
Gets the greater value between two rational numbers.

**Parameters:**

* <code>first</code> - An arbitrary-precision rational number.

* <code>second</code> - Another arbitrary-precision rational number.

**Returns:**

* The larger value of the two numbers. If one is positive zero and the
 other is negative zero, returns the positive zero. If the two numbers are
 positive and have the same value, returns the one with the larger
 denominator. If the two numbers are negative and have the same value,
 returns the one with the smaller denominator.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### MaxMagnitude
    public static ERational MaxMagnitude(ERational first, ERational second)
Gets the greater value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Max.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* The larger value of the two numbers, ignoring their signs.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### Min
    public static ERational Min(ERational first, ERational second)
Gets the lesser value between two rational numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* The smaller value of the two numbers. If one is positive zero and
 the other is negative zero, returns the negative zero. If the two numbers
 are positive and have the same value, returns the one with the smaller
 denominator. If the two numbers are negative and have the same value,
 returns the one with the larger denominator.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### MinMagnitude
    public static ERational MinMagnitude(ERational first, ERational second)
Gets the lesser value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Min.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* The smaller value of the two numbers, ignoring their signs.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### compareTo
    public int compareTo(int intOther)
Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance. This method currently uses the rules given in
 the CompareToValue method, so that it it is not consistent with the Equals
 method, but it may change in a future version to use the rules for the
 CompareToTotal method instead.

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 32-bit signed integer.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater.

### CompareToValue
    public int CompareToValue(int intOther)
Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance. In this method, NaN values are greater than any
 other ERational value, and two NaN values (even if their payloads differ)
 are treated as equal by this method. This method is not consistent with the
 Equals method.

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 32-bit signed integer.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater.

### CompareToValue
    public int CompareToValue(long intOther)
<p>Compares the mathematical values of this object and another object,
 accepting NaN values. </p><p>This method is not consistent with the Equals
 method because two different numbers with the same mathematical value, but
 different exponents, will compare as equal.</p> <p>In this method, negative
 zero and positive zero are considered equal.</p> <p>If this object is a
 quiet NaN or signaling NaN, this method will not trigger an error. Instead,
 NaN will compare greater than any other number, including infinity.</p>

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 64-bit signed integer.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other value, or 0
 if both values are equal.

### compareTo
    public int compareTo(long intOther)
Compares the mathematical values of this object and another object,
 accepting NaN values. This method currently uses the rules given in the
 CompareToValue method, so that it it is not consistent with the Equals
 method, but it may change in a future version to use the rules for the
 CompareToTotal method instead.

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 64-bit signed integer.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other value, or 0
 if both values are equal.

### CompareToBinary
    public int CompareToBinary(EFloat other)
Compares an arbitrary-precision binary floating-point number with this
 instance. In this method, NaN values are greater than any other ERational or
 EFloat value, and two NaN values (even if their payloads differ) are treated
 as equal by this method.

**Parameters:**

* <code>other</code> - An arbitrary-precision binary floating-point number.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater. This implementation
 returns a positive number if.

### CompareToDecimal
    public int CompareToDecimal(EDecimal other)
Compares an arbitrary-precision decimal number with this instance.

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater. This implementation
 returns a positive number if.

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
Divides this arbitrary-precision rational number by another
 arbitrary-precision rational number and returns the result.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The result of dividing this arbitrary-precision rational number by
 another arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>otherValue</code> is null.

### equals
    public boolean equals(Object obj)
Determines whether this object's numerator, denominator, and properties are
 equal to those of another object and that other object is an
 arbitrary-precision rational number. Not-a-number values are considered
 equal if the rest of their properties are equal. This is not the same as
 value equality. Notably, two ERationals with the same value, but of which
 one is in lowest terms and the other is not, are compared as unequal by this
 method (example: 1/2 vs. 5/10).

**Overrides:**

* <code>equals</code> in class <code>Object</code>

**Parameters:**

* <code>obj</code> - The parameter <code>obj</code> is an arbitrary object.

**Returns:**

* <code>true</code> if the objects are equal; otherwise, <code>false</code>. In
 this method, two objects are not equal if they don't have the same type or
 if one is null and the other isn't.

### equals
    public boolean equals(ERational other)
Determines whether this object's numerator, denominator, and properties are
 equal to those of another object. Not-a-number values are considered equal
 if the rest of their properties are equal.

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number to compare to.

**Returns:**

* Either <code>true</code> or <code>false</code>.

### hashCode
    public int hashCode()
Returns the hash code for this instance. No application or process IDs are
 used in the hash code calculation.

**Overrides:**

* <code>hashCode</code> in class <code>Object</code>

**Returns:**

* A 32-bit signed integer.

### IsInfinity
    public boolean IsInfinity()
Gets a value indicating whether this object's value is infinity.

**Returns:**

* <code>true</code> if this object's value is infinity; otherwise, <code>
 false</code>.

### IsNaN
    public boolean IsNaN()
Returns whether this object is a not-a-number value.

**Returns:**

* <code>true</code> if this object is a not-a-number value; otherwise,
 <code>false</code>.

### IsNegativeInfinity
    public boolean IsNegativeInfinity()
Returns whether this object is negative infinity.

**Returns:**

* <code>true</code> if this object is negative infinity; otherwise, <code>
 false</code>.

### IsPositiveInfinity
    public boolean IsPositiveInfinity()
Returns whether this object is positive infinity.

**Returns:**

* <code>true</code> if this object is positive infinity; otherwise, <code>
 false</code>.

### IsQuietNaN
    public boolean IsQuietNaN()
Returns whether this object is a quiet not-a-number value.

**Returns:**

* <code>true</code> if this object is a quiet not-a-number value;
 otherwise, <code>false</code>.

### IsSignalingNaN
    public boolean IsSignalingNaN()
Returns whether this object is a signaling not-a-number value (which causes
 an error if the value is passed to any arithmetic operation in this class).

**Returns:**

* <code>true</code> if this object is a signaling not-a-number value (which
 causes an error if the value is passed to any arithmetic operation in this
 class); otherwise, <code>false</code>.

### Multiply
    public ERational Multiply(ERational otherValue)
Multiplies this arbitrary-precision rational number by another
 arbitrary-precision rational number and returns the result.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 rational number times another arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>otherValue</code> is null.

### Negate
    public ERational Negate()
Returns a rational number with the same value as this one but with the sign
 reversed.

**Returns:**

* An arbitrary-precision rational number.

### Remainder
    public ERational Remainder(ERational otherValue)
Returns the remainder that would result when this arbitrary-precision
 rational number is divided by another arbitrary-precision rational number.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The remainder that would result when this arbitrary-precision
 rational number is divided by another arbitrary-precision rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>otherValue</code> is null.

### Subtract
    public ERational Subtract(ERational otherValue)
Subtracts an arbitrary-precision rational number from this
 arbitrary-precision rational number and returns the result.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision rational number minus another arbitrary-precision
 rational number.

**Throws:**

* <code>NullPointerException</code> - The parameter <code>otherValue</code> is null.

### ToDouble
    public double ToDouble()
Converts this value to a 64-bit floating-point number. The half-even
 rounding mode is used.

**Returns:**

* The closest 64-bit floating-point number to this value. The return
 value can be positive infinity or negative infinity if this value exceeds
 the range of a 64-bit floating point number.

### ToDoubleBits
    public long ToDoubleBits()
<p>Converts this value to its closest equivalent as a 64-bit floating-point
 number, expressed as an integer in the IEEE 754 binary64 format. The
 half-even rounding mode is used. </p> <p>If this value is a NaN, sets the
 high bit of the 64-bit floating point number's significand area for a quiet
 NaN, and clears it for a signaling NaN. Then the other bits of the
 significand area are set to the lowest bits of this object's unsigned
 significand, and the next-highest bit of the significand area is set if
 those bits are all zeros and this is a signaling NaN.</p>

**Returns:**

* The closest 64-bit binary floating-point number to this value,
 expressed as an integer in the IEEE 754 binary64 format. The return value
 can be positive infinity or negative infinity if this value exceeds the
 range of a 64-bit floating point number.

### ToSingleBits
    public int ToSingleBits()
<p>Converts this value to its closest equivalent as 32-bit floating-point
 number, expressed as an integer in the IEEE 754 binary32 format. The
 half-even rounding mode is used. </p> <p>If this value is a NaN, sets the
 high bit of the 32-bit floating point number's significand area for a quiet
 NaN, and clears it for a signaling NaN. Then the other bits of the
 significand area are set to the lowest bits of this object's unsigned
 significand, and the next-highest bit of the significand area is set if
 those bits are all zeros and this is a signaling NaN.</p>

**Returns:**

* The closest 32-bit binary floating-point number to this value,
 expressed as an integer in the IEEE 754 binary32 format. The return value
 can be positive infinity or negative infinity if this value exceeds the
 range of a 32-bit floating point number.

### ToHalfBits
    public short ToHalfBits()
<p>Converts this value to its closest equivalent as a binary floating-point
 number, expressed as an integer in the IEEE 754 binary16 format (also known
 as a "half-precision" floating-point number). The half-even rounding mode is
 used. </p> <p>If this value is a NaN, sets the high bit of the binary16
 number's significand area for a quiet NaN, and clears it for a signaling
 NaN. Then the other bits of the significand area are set to the lowest bits
 of this object's unsigned significand, and the next-highest bit of the
 significand area is set if those bits are all zeros and this is a signaling
 NaN.</p>

**Returns:**

* The closest binary floating-point number to this value, expressed as
 an integer in the IEEE 754 binary16 format. The return value can be positive
 infinity or negative infinity if this value exceeds the range of a
 floating-point number in the binary16 format.

### ToLowestTerms
    public ERational ToLowestTerms()
Converts this value to its form in lowest terms. For example, (8/4) becomes
 (4/1).

**Returns:**

* An arbitrary-precision rational with the same value as this one but
 in lowest terms. Returns this object if it is infinity or NaN. Returns
 ERational.NegativeZero if this object is a negative zero. Returns
 ERational.Zero if this object is a positive zero.

**Throws:**

* <code>ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

### ToSizedEInteger
    public EInteger ToSizedEInteger(int maxBitLength)
Converts this value to an arbitrary-precision integer by dividing the
 numerator by the denominator, discarding its fractional part, and checking
 whether the resulting integer overflows the given signed bit count.

**Parameters:**

* <code>maxBitLength</code> - The maximum number of signed bits the integer can have.
 The integer's value may not be less than -(2^maxBitLength) or greater than
 (2^maxBitLength) - 1.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN), or this number's value, once converted to an integer by dividing the
 numerator by the denominator and discarding its fractional part, is less
 than -(2^maxBitLength) or greater than (2^maxBitLength) - 1.

### ToSizedEIntegerIfExact
    public EInteger ToSizedEIntegerIfExact(int maxBitLength)
Converts this value to an arbitrary-precision integer, only if this number's
 value is an exact integer and that integer does not overflow the given
 signed bit count.

**Parameters:**

* <code>maxBitLength</code> - The maximum number of signed bits the integer can have.
 The integer's value may not be less than -(2^maxBitLength) or greater than
 (2^maxBitLength) - 1.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN), or this number's value as an integer is less than -(2^maxBitLength)
 or greater than (2^maxBitLength) - 1.

* <code>ArithmeticException</code> - This object's value is not an exact integer.

### ToEInteger
    public EInteger ToEInteger()
Converts this value to an arbitrary-precision integer by dividing the
 numerator by the denominator and discarding the fractional part of the
 result.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

### ToEIntegerExact
    @Deprecated public EInteger ToEIntegerExact()
Converts this value to an arbitrary-precision integer, checking whether the
 value is an exact integer.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

### ToEIntegerIfExact
    public EInteger ToEIntegerIfExact()
Converts this value to an arbitrary-precision integer, checking whether the
 value is an exact integer.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

### ToEDecimal
    public EDecimal ToEDecimal()
Converts this rational number to an arbitrary-precision decimal number.

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating decimal expansion.

### ToEDecimal
    public EDecimal ToEDecimal(EContext ctx)
Converts this rational number to an arbitrary-precision decimal number and
 rounds the result to the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is true, will
 also store the flags resulting from the operation (the flags are in addition
 to the pre-existing flags). Can be null, in which case the precision is
 unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result can't be
 exact because it has a nonterminating decimal expansion.

### ToEDecimalExactIfPossible
    public EDecimal ToEDecimalExactIfPossible(EContext ctx)
Converts this rational number to an arbitrary-precision decimal number, but
 if the result would have a nonterminating decimal expansion, rounds that
 result to the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only if the
 exact result would have a nonterminating decimal expansion. If HasFlags of
 the context is true, will also store the flags resulting from the operation
 (the flags are in addition to the pre-existing flags). Can be null, in which
 case the precision is unlimited and no rounding is needed.

**Returns:**

* The exact value of the rational number if possible; otherwise, the
 rounded version of the result if a context is given. Returns not-a-number
 (NaN) if the context is null and the result can't be exact because it has a
 nonterminating decimal expansion.

### ToExtendedDecimal
    @Deprecated public EDecimal ToExtendedDecimal()
Converts this rational number to an arbitrary-precision decimal number.

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating decimal expansion.

### ToExtendedDecimal
    @Deprecated public EDecimal ToExtendedDecimal(EContext ctx)
Converts this rational number to an arbitrary-precision decimal number and
 rounds the result to the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is true, will
 also store the flags resulting from the operation (the flags are in addition
 to the pre-existing flags). Can be null, in which case the precision is
 unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result can't be
 exact because it has a nonterminating decimal expansion.

### ToExtendedDecimalExactIfPossible
    @Deprecated public EDecimal ToExtendedDecimalExactIfPossible(EContext ctx)
Converts this rational number to an arbitrary-precision decimal number, but
 if the result would have a nonterminating decimal expansion, rounds that
 result to the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only if the
 exact result would have a nonterminating decimal expansion. If HasFlags of
 the context is true, will also store the flags resulting from the operation
 (the flags are in addition to the pre-existing flags). Can be null, in which
 case the precision is unlimited and no rounding is needed.

**Returns:**

* The exact value of the rational number if possible; otherwise, the
 rounded version of the result if a context is given. Returns not-a-number
 (NaN) if the context is null and the result can't be exact because it has a
 nonterminating decimal expansion.

### ToEFloat
    public EFloat ToEFloat()
Converts this rational number to a binary floating-point number.

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating binary expansion.

### ToEFloat
    public EFloat ToEFloat(EContext ctx)
Converts this rational number to a binary floating-point number and rounds
 that result to the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is true, will
 also store the flags resulting from the operation (the flags are in addition
 to the pre-existing flags). Can be null, in which case the precision is
 unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result can't be
 exact because it has a nonterminating binary expansion.

### ToEFloatExactIfPossible
    public EFloat ToEFloatExactIfPossible(EContext ctx)
Converts this rational number to a binary floating-point number, but if the
 result would have a nonterminating binary expansion, rounds that result to
 the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only if the
 exact result would have a nonterminating binary expansion. If HasFlags of
 the context is true, will also store the flags resulting from the operation
 (the flags are in addition to the pre-existing flags). Can be null, in which
 case the precision is unlimited and no rounding is needed.

**Returns:**

* The exact value of the rational number if possible; otherwise, the
 rounded version of the result if a context is given. Returns not-a-number
 (NaN) if the context is null and the result can't be exact because it has a
 nonterminating binary expansion.

### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat()
Converts this rational number to a binary floating-point number.

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating binary expansion.

### ToExtendedFloat
    @Deprecated public EFloat ToExtendedFloat(EContext ctx)
Converts this rational number to a binary floating-point number and rounds
 that result to the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is true, will
 also store the flags resulting from the operation (the flags are in addition
 to the pre-existing flags). Can be null, in which case the precision is
 unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result can't be
 exact because it has a nonterminating binary expansion.

### ToExtendedFloatExactIfPossible
    @Deprecated public EFloat ToExtendedFloatExactIfPossible(EContext ctx)
Converts this rational number to a binary floating-point number, but if the
 result would have a nonterminating binary expansion, rounds that result to
 the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only if the
 exact result would have a nonterminating binary expansion. If HasFlags of
 the context is true, will also store the flags resulting from the operation
 (the flags are in addition to the pre-existing flags). Can be null, in which
 case the precision is unlimited and no rounding is needed.

**Returns:**

* The exact value of the rational number if possible; otherwise, the
 rounded version of the result if a context is given. Returns not-a-number
 (NaN) if the context is null and the result can't be exact because it has a
 nonterminating binary expansion.

### ToSingle
    public float ToSingle()
Converts this value to a 32-bit binary floating-point number. The half-even
 rounding mode is used.

**Returns:**

* The closest 32-bit binary floating-point number to this value. The
 return value can be positive infinity or negative infinity if this value
 exceeds the range of a 32-bit floating point number.

### toString
    public String toString()
Converts this object to a text string.

**Overrides:**

* <code>toString</code> in class <code>Object</code>

**Returns:**

* A string representation of this object. If this object's value is
 infinity or not-a-number, the result is the analogous return value of the
 <code>EDecimal.toString</code> method. Otherwise, the return value has the
 following form: <code>[-]numerator.Divide(denominator)</code>.

### Increment
    public ERational Increment()
Adds one to an arbitrary-precision rational number.

**Returns:**

* The given arbitrary-precision rational number plus one.

### Decrement
    public ERational Decrement()
Subtracts one from an arbitrary-precision rational number.

**Returns:**

* The given arbitrary-precision rational number minus one.

### Add
    public ERational Add(int v)
Adds this arbitrary-precision rational number and a 32-bit signed integer
 and returns the result.

**Parameters:**

* <code>v</code> - A 32-bit signed integer.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 rational number plus a 32-bit signed integer.

### Subtract
    public ERational Subtract(int v)
Subtracts a 32-bit signed integer from this arbitrary-precision rational
 number and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 32-bit signed integer.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision rational number minus a 32-bit signed integer.

### Multiply
    public ERational Multiply(int v)
Multiplies this arbitrary-precision rational number by a 32-bit signed
 integer and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 32-bit signed integer.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 rational number times a 32-bit signed integer.

### Divide
    public ERational Divide(int v)
Divides this arbitrary-precision rational number by a 32-bit signed integer
 and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 32-bit signed integer.

**Returns:**

* The result of dividing this arbitrary-precision rational number by a
 32-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - The parameter <code>v</code> is zero.

### Remainder
    public ERational Remainder(int v)
Returns the remainder that would result when this arbitrary-precision
 rational number is divided by a 32-bit signed integer.

**Parameters:**

* <code>v</code> - The divisor.

**Returns:**

* The remainder that would result when this arbitrary-precision
 rational number is divided by a 32-bit signed integer.

**Throws:**

* <code>IllegalArgumentException</code> - The parameter <code>v</code> is zero.

### Add
    public ERational Add(long v)
Adds this arbitrary-precision rational number and a 64-bit signed integer
 and returns the result.

**Parameters:**

* <code>v</code> - A 64-bit signed integer.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 rational number plus a 64-bit signed integer.

### Subtract
    public ERational Subtract(long v)
Subtracts a 64-bit signed integer from this arbitrary-precision rational
 number and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 64-bit signed integer.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision rational number minus a 64-bit signed integer.

### Multiply
    public ERational Multiply(long v)
Multiplies this arbitrary-precision rational number by a 64-bit signed
 integer and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 64-bit signed integer.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 rational number times a 64-bit signed integer.

### Divide
    public ERational Divide(long v)
Divides this arbitrary-precision rational number by a 64-bit signed integer
 and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 64-bit signed integer.

**Returns:**

* The result of dividing this arbitrary-precision rational number by a
 64-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - The parameter <code>v</code> is zero.

### Remainder
    public ERational Remainder(long v)
Returns the remainder that would result when this arbitrary-precision
 rational number is divided by a 64-bit signed integer.

**Parameters:**

* <code>v</code> - The divisor.

**Returns:**

* The remainder that would result when this arbitrary-precision
 rational number is divided by a 64-bit signed integer.

**Throws:**

* <code>IllegalArgumentException</code> - The parameter <code>v</code> is zero.

### ToByteChecked
    public byte ToByteChecked()
Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) after converting it to an integer by discarding its
 fractional part.

**Returns:**

* This number's value, truncated to a byte (from 0 to 255).

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional part, is
 less than 0 or greater than 255.

### ToByteUnchecked
    public byte ToByteUnchecked()
Converts this number's value to an integer (using ToEInteger), and returns
 the least-significant bits of that integer's two's-complement form as a byte
 (from 0 to 255).

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

* <code>ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than 0 or greater than 255.

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
 16-bit signed integer after converting it to an integer by discarding its
 fractional part.

**Returns:**

* This number's value, truncated to a 16-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional part, is
 less than -32768 or greater than 32767.

### ToInt16Unchecked
    public short ToInt16Unchecked()
Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement form as
 a 16-bit signed integer.

**Returns:**

* This number, converted to a 16-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### ToInt16IfExact
    public short ToInt16IfExact()
Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer without rounding to a different numerical value.

**Returns:**

* This number's value as a 16-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -32768 or greater than 32767.

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
 32-bit signed integer after converting it to an integer by discarding its
 fractional part.

**Returns:**

* This number's value, truncated to a 32-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional part, is
 less than -2147483648 or greater than 2147483647.

### ToInt32Unchecked
    public int ToInt32Unchecked()
Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement form as
 a 32-bit signed integer.

**Returns:**

* This number, converted to a 32-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### ToInt32IfExact
    public int ToInt32IfExact()
Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer without rounding to a different numerical value.

**Returns:**

* This number's value as a 32-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -2147483648 or greater than 2147483647.

### FromBoolean
    public static ERational FromBoolean(boolean boolValue)
Converts a Boolean value (true or false) to an arbitrary-precision rational
 number.

**Parameters:**

* <code>boolValue</code> - Either true or false.

**Returns:**

* The number 1 if <code>boolValue</code> is true; otherwise, 0.

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
 64-bit signed integer after converting it to an integer by discarding its
 fractional part.

**Returns:**

* This number's value, truncated to a 64-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional part, is
 less than -9223372036854775808 or greater than 9223372036854775807.

### ToInt64Unchecked
    public long ToInt64Unchecked()
Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement form as
 a 64-bit signed integer.

**Returns:**

* This number, converted to a 64-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### ToInt64IfExact
    public long ToInt64IfExact()
Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer without rounding to a different numerical value.

**Returns:**

* This number's value as a 64-bit signed integer.

**Throws:**

* <code>ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -9223372036854775808 or greater than
 9223372036854775807.

### FromInt64AsUnsigned
    public static ERational FromInt64AsUnsigned(long longerValue)
Converts an unsigned integer expressed as a 64-bit signed integer to an
 arbitrary-precision rational number.

**Parameters:**

* <code>longerValue</code> - A 64-bit signed integer. If this value is 0 or greater,
 the return value will represent it. If this value is less than 0, the return
 value will store 2^64 plus this value instead.

**Returns:**

* An arbitrary-precision rational number. If <code>longerValue</code> is 0
 or greater, the return value will represent it. If <code>longerValue</code> is
 less than 0, the return value will store 2^64 plus this value instead.

### FromInt64
    public static ERational FromInt64(long inputInt64)
Converts a 64-bit signed integer to an arbitrary-precision rational number.

**Parameters:**

* <code>inputInt64</code> - The number to convert as a 64-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision rational number.
