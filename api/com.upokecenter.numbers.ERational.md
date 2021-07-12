# com.upokecenter.numbers.ERational

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

* `ERational​(EInteger numerator,
EInteger denominator)`<br>
 Deprecated.
Use the Create method instead.
 Use the Create method instead.

## Methods

* `ERational Abs()`<br>
 Returns the absolute value of this rational number, that is, a number with
 the same value as this one but as a nonnegative number.
* `ERational Add​(int v)`<br>
 Adds this arbitrary-precision rational number and a 32-bit signed integer
 and returns the result.
* `ERational Add​(long v)`<br>
 Adds this arbitrary-precision rational number and a 64-bit signed integer
 and returns the result.
* `ERational Add​(ERational otherValue)`<br>
 Adds this arbitrary-precision rational number and another
 arbitrary-precision rational number and returns the result.
* `int compareTo​(int intOther)`<br>
 Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance.
* `int compareTo​(long intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int compareTo​(ERational other)`<br>
 Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance.
* `int CompareToBinary​(EFloat other)`<br>
 Compares an arbitrary-precision binary floating-point number with this
 instance.
* `int CompareToDecimal​(EDecimal other)`<br>
 Compares an arbitrary-precision decimal number with this instance.
* `int CompareToTotal​(ERational other)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values.
* `int CompareToTotalMagnitude​(ERational other)`<br>
 Compares the absolute values of this object and another object, imposing a
 total ordering on all possible values (ignoring their signs).
* `int CompareToValue​(int intOther)`<br>
 Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance.
* `int CompareToValue​(long intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int CompareToValue​(ERational other)`<br>
 Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance.
* `ERational Copy()`<br>
 Creates a copy of this arbitrary-precision rational number.
* `ERational CopySign​(ERational other)`<br>
 Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number.
* `static ERational Create​(int numeratorSmall,
int denominatorSmall)`<br>
 Creates a rational number with the given numerator and denominator.
* `static ERational Create​(long numeratorLong,
long denominatorLong)`<br>
 Creates a rational number with the given numerator and denominator.
* `static ERational Create​(EInteger numerator,
EInteger denominator)`<br>
 Creates a rational number with the given numerator and denominator.
* `static ERational CreateNaN​(EInteger diag)`<br>
 Creates a not-a-number arbitrary-precision rational number.
* `static ERational CreateNaN​(EInteger diag,
boolean signaling,
boolean negative)`<br>
 Creates a not-a-number arbitrary-precision rational number.
* `ERational Decrement()`<br>
 Subtracts one from an arbitrary-precision rational number.
* `ERational Divide​(int v)`<br>
 Divides this arbitrary-precision rational number by a 32-bit signed integer
 and returns the result.
* `ERational Divide​(long v)`<br>
 Divides this arbitrary-precision rational number by a 64-bit signed integer
 and returns the result.
* `ERational Divide​(ERational otherValue)`<br>
 Divides this arbitrary-precision rational number by another
 arbitrary-precision rational number and returns the result.
* `boolean equals​(ERational other)`<br>
 Determines whether this object's numerator, denominator, and properties are
 equal to those of another object.
* `boolean equals​(java.lang.Object obj)`<br>
 Determines whether this object's numerator, denominator, and properties are
 equal to those of another object and that other object is an
 arbitrary-precision rational number.
* `static ERational FromBoolean​(boolean boolValue)`<br>
 Converts a boolean value (true or false) to an arbitrary-precision rational
 number.
* `static ERational FromByte​(byte inputByte)`<br>
 Converts a byte (from 0 to 255) to an arbitrary-precision rational number.
* `static ERational FromDouble​(double flt)`<br>
 Converts a 64-bit floating-point number to a rational number.
* `static ERational FromDoubleBits​(long value)`<br>
 Creates a binary rational number from a 64-bit floating-point number encoded
 in the IEEE 754 binary64 format.
* `static ERational FromEDecimal​(EDecimal ef)`<br>
 Converts an arbitrary-precision decimal number to a rational number.
* `static ERational FromEFloat​(EFloat ef)`<br>
 Converts an arbitrary-precision binary floating-point number to a rational
 number.
* `static ERational FromEInteger​(EInteger bigint)`<br>
 Converts an arbitrary-precision integer to a rational number.
* `static ERational FromExtendedDecimal​(EDecimal ef)`<br>
 Deprecated.
Renamed to FromEDecimal.
 Renamed to FromEDecimal.
* `static ERational FromExtendedFloat​(EFloat ef)`<br>
 Deprecated.
Renamed to FromEFloat.
 Renamed to FromEFloat.
* `static ERational FromHalfBits​(short value)`<br>
 Creates a binary rational number from a binary floating-point number encoded
  in the IEEE 754 binary16 format (also known as a "half-precision"
 floating-point number).
* `static ERational FromInt16​(short inputInt16)`<br>
 Converts a 16-bit signed integer to an arbitrary-precision rational number.
* `static ERational FromInt32​(int inputInt32)`<br>
 Converts a 32-bit signed integer to an arbitrary-precision rational number.
* `static ERational FromInt64​(long inputInt64)`<br>
 Converts a 64-bit signed integer to an arbitrary-precision rational number.
* `static ERational FromInt64AsUnsigned​(long longerValue)`<br>
 Converts an unsigned integer expressed as a 64-bit signed integer to an
 arbitrary-precision rational number.
* `static ERational FromSingle​(float flt)`<br>
 Converts a 32-bit binary floating-point number to a rational number.
* `static ERational FromSingleBits​(int value)`<br>
 Creates a binary rational number from a 32-bit floating-point number encoded
 in the IEEE 754 binary32 format.
* `static ERational FromString​(byte[] bytes)`<br>
 Creates a rational number from a sequence of bytes that represents a number.
* `static ERational FromString​(byte[] bytes,
int offset,
int length)`<br>
 Creates a rational number from a sequence of bytes that represents a
 number.
* `static ERational FromString​(char[] chars) char`<br>
 Creates a rational number from a sequence of char s that represents a
 number.
* `static ERational FromString​(char[] chars,
int offset,
int length) char`<br>
 Creates a rational number from a sequence of char s that
 represents a number.
* `static ERational FromString​(java.lang.String str)`<br>
 Creates a rational number from a text string that represents a number.
* `static ERational FromString​(java.lang.String str,
int offset,
int length)`<br>
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
* `ERational Increment()`<br>
 Adds one to an arbitrary-precision rational number.
* `boolean isFinite()`<br>
 Gets a value indicating whether this object is finite (not infinity or NaN).
* `boolean IsInfinity()`<br>
 Gets a value indicating whether this object's value is infinity.
* `boolean IsInteger()`<br>
 Returns whether this object's value is an integer.
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
* `static ERational Max​(ERational first,
ERational second)`<br>
 Gets the greater value between two rational numbers.
* `static ERational MaxMagnitude​(ERational first,
ERational second)`<br>
 Gets the greater value between two values, ignoring their signs.
* `static ERational Min​(ERational first,
ERational second)`<br>
 Gets the lesser value between two rational numbers.
* `static ERational MinMagnitude​(ERational first,
ERational second)`<br>
 Gets the lesser value between two values, ignoring their signs.
* `ERational Multiply​(int v)`<br>
 Multiplies this arbitrary-precision rational number by a 32-bit signed
 integer and returns the result.
* `ERational Multiply​(long v)`<br>
 Multiplies this arbitrary-precision rational number by a 64-bit signed
 integer and returns the result.
* `ERational Multiply​(ERational otherValue)`<br>
 Multiplies this arbitrary-precision rational number by another
 arbitrary-precision rational number and returns the result.
* `ERational Negate()`<br>
 Returns a rational number with the same value as this one but with the sign
 reversed.
* `ERational Remainder​(int v)`<br>
 Returns the remainder that would result when this arbitrary-precision
 rational number is divided by a 32-bit signed integer.
* `ERational Remainder​(long v)`<br>
 Returns the remainder that would result when this arbitrary-precision
 rational number is divided by a 64-bit signed integer.
* `ERational Remainder​(ERational otherValue)`<br>
 Returns the remainder that would result when this arbitrary-precision
 rational number is divided by another arbitrary-precision rational
 number.
* `int signum()`<br>
 Gets the sign of this rational number.
* `ERational Subtract​(int v)`<br>
 Subtracts a 32-bit signed integer from this arbitrary-precision rational
 number and returns the result.
* `ERational Subtract​(long v)`<br>
 Subtracts a 64-bit signed integer from this arbitrary-precision rational
 number and returns the result.
* `ERational Subtract​(ERational otherValue)`<br>
 Subtracts an arbitrary-precision rational number from this
 arbitrary-precision rational number and returns the result.
* `byte ToByteChecked()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) after converting it to an integer by discarding
 its fractional part.
* `byte ToByteIfExact()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) without rounding to a different numerical
 value.
* `byte ToByteUnchecked()`<br>
 Converts this number's value to an integer (using ToEInteger), and returns
 the least-significant bits of that integer's two's-complement form
 as a byte (from 0 to 255).
* `double ToDouble()`<br>
 Converts this value to a 64-bit floating-point number.
* `long ToDoubleBits()`<br>
 Converts this value to its closest equivalent as a 64-bit floating-point
 number, expressed as an integer in the IEEE 754 binary64 format.
* `EDecimal ToEDecimal()`<br>
 Converts this rational number to an arbitrary-precision decimal number.
* `EDecimal ToEDecimal​(EContext ctx)`<br>
 Converts this rational number to an arbitrary-precision decimal number and
 rounds the result to the given precision.
* `EDecimal ToEDecimalExactIfPossible​(EContext ctx)`<br>
 Converts this rational number to an arbitrary-precision decimal number, but
 if the result would have a nonterminating decimal expansion, rounds
 that result to the given precision.
* `EFloat ToEFloat()`<br>
 Converts this rational number to a binary floating-point number.
* `EFloat ToEFloat​(EContext ctx)`<br>
 Converts this rational number to a binary floating-point number and rounds
 that result to the given precision.
* `EFloat ToEFloatExactIfPossible​(EContext ctx)`<br>
 Converts this rational number to a binary floating-point number, but if the
 result would have a nonterminating binary expansion, rounds that
 result to the given precision.
* `EInteger ToEInteger()`<br>
 Converts this value to an arbitrary-precision integer by dividing the
 numerator by the denominator and discarding the fractional part of
 the result.
* `EInteger ToEIntegerExact()`<br>
 Deprecated.
Renamed to ToEIntegerIfExact.
 Renamed to ToEIntegerIfExact.
* `EInteger ToEIntegerIfExact()`<br>
 Converts this value to an arbitrary-precision integer, checking whether the
 value is an exact integer.
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
* `short ToHalfBits()`<br>
 Converts this value to its closest equivalent as a binary floating-point
 number, expressed as an integer in the IEEE 754 binary16 format
  (also known as a "half-precision" floating-point number).
* `short ToInt16Checked()`<br>
 Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer after converting it to an integer by
 discarding its fractional part.
* `short ToInt16IfExact()`<br>
 Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer without rounding to a different numerical
 value.
* `short ToInt16Unchecked()`<br>
 Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 16-bit signed integer.
* `int ToInt32Checked()`<br>
 Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer after converting it to an integer by
 discarding its fractional part.
* `int ToInt32IfExact()`<br>
 Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer without rounding to a different numerical
 value.
* `int ToInt32Unchecked()`<br>
 Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 32-bit signed integer.
* `long ToInt64Checked()`<br>
 Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer after converting it to an integer by
 discarding its fractional part.
* `long ToInt64IfExact()`<br>
 Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer without rounding to a different numerical
 value.
* `long ToInt64Unchecked()`<br>
 Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 64-bit signed integer.
* `ERational ToLowestTerms()`<br>
 Converts this value to its form in lowest terms.
* `float ToSingle()`<br>
 Converts this value to a 32-bit binary floating-point number.
* `int ToSingleBits()`<br>
 Converts this value to its closest equivalent as 32-bit floating-point
 number, expressed as an integer in the IEEE 754 binary32 format.
* `EInteger ToSizedEInteger​(int maxBitLength)`<br>
 Converts this value to an arbitrary-precision integer by dividing the
 numerator by the denominator, discarding its fractional part, and
 checking whether the resulting integer overflows the given signed
 bit count.
* `EInteger ToSizedEIntegerIfExact​(int maxBitLength)`<br>
 Converts this value to an arbitrary-precision integer, only if this number's
 value is an exact integer and that integer does not overflow the
 given signed bit count.
* `java.lang.String toString()`<br>
 Converts this object to a text string.

## Field Details

### <a id='NaN'>NaN</a>

A not-a-number value.
### <a id='NegativeInfinity'>NegativeInfinity</a>

Negative infinity, less than any other number.
### <a id='NegativeZero'>NegativeZero</a>

A rational number for negative zero.
### <a id='One'>One</a>

The rational number one.
### <a id='PositiveInfinity'>PositiveInfinity</a>

Positive infinity, greater than any other number.
### <a id='SignalingNaN'>SignalingNaN</a>

A signaling not-a-number value.
### <a id='Ten'>Ten</a>

The rational number ten.
### <a id='Zero'>Zero</a>

A rational number for zero.
## Method Details

### <a id='Copy()'>Copy</a>

Creates a copy of this arbitrary-precision rational number.

**Returns:**

* An arbitrary-precision rational number.

### <a id='getDenominator()'>getDenominator</a>

Gets this object's denominator.

**Returns:**

* This object's denominator.

### <a id='isFinite()'>isFinite</a>

Gets a value indicating whether this object is finite (not infinity or NaN).

**Returns:**

* <code>true</code> if this object is finite (not infinity or NaN);
 otherwise, <code>false</code>.

### <a id='isNegative()'>isNegative</a>

Gets a value indicating whether this object's value is negative (including
 negative zero).

**Returns:**

* <code>true</code> if this object's value is negative (including negative
 zero); otherwise, <code>false</code>. <code>true</code> if this object's value
 is negative; otherwise, <code>false</code>.

### <a id='isZero()'>isZero</a>

Gets a value indicating whether this object's value equals 0.

**Returns:**

* <code>true</code> if this object's value equals 0; otherwise, <code>
 false</code>. <code>true</code> if this object's value equals 0; otherwise,
 <code>false</code>.

### <a id='IsInteger()'>IsInteger</a>

Returns whether this object's value is an integer.

**Returns:**

* <code>true</code> if this object's value is an integer; otherwise, <code>
 false</code>.

### <a id='getNumerator()'>getNumerator</a>

Gets this object's numerator.

**Returns:**

* This object's numerator. If this object is a not-a-number value,
 returns the diagnostic information (which will be negative if this
 object is negative).

### <a id='signum()'>signum</a>

Gets the sign of this rational number.

**Returns:**

* The sign of this rational number.

### <a id='getUnsignedNumerator()'>getUnsignedNumerator</a>

Gets this object's numerator with the sign removed.

**Returns:**

* This object's numerator. If this object is a not-a-number value,
 returns the diagnostic information.

### <a id='Create(int,int)'>Create</a>

Creates a rational number with the given numerator and denominator.

**Parameters:**

* <code>numeratorSmall</code> - The numerator.

* <code>denominatorSmall</code> - The denominator.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The denominator is zero.

### <a id='Create(long,long)'>Create</a>

Creates a rational number with the given numerator and denominator.

**Parameters:**

* <code>numeratorLong</code> - The numerator.

* <code>denominatorLong</code> - The denominator.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The denominator is zero.

### <a id='Create(com.upokecenter.numbers.EInteger,com.upokecenter.numbers.EInteger)'>Create</a>

Creates a rational number with the given numerator and denominator.

**Parameters:**

* <code>numerator</code> - The numerator.

* <code>denominator</code> - The denominator.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The denominator is zero.

* <code>java.lang.NullPointerException</code> - The parameter <code>numerator</code> or <code>
 denominator</code> is null.

### <a id='CreateNaN(com.upokecenter.numbers.EInteger)'>CreateNaN</a>

Creates a not-a-number arbitrary-precision rational number.

**Parameters:**

* <code>diag</code> - An integer, 0 or greater, to use as diagnostic information
 associated with this object. If none is needed, should be zero. To
 get the diagnostic information from another arbitrary-precision
 rational number, use that object's <code>UnsignedNumerator</code>
 property.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>diag</code> is less than 0.

### <a id='CreateNaN(com.upokecenter.numbers.EInteger,boolean,boolean)'>CreateNaN</a>

Creates a not-a-number arbitrary-precision rational number.

**Parameters:**

* <code>diag</code> - An integer, 0 or greater, to use as diagnostic information
 associated with this object. If none is needed, should be zero. To
 get the diagnostic information from another arbitrary-precision
 rational number, use that object's <code>UnsignedNumerator</code>
 property.

* <code>signaling</code> - Whether the return value will be signaling (true) or quiet
 (false).

* <code>negative</code> - Whether the return value is negative.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>diag</code> is less than 0.

* <code>java.lang.NullPointerException</code> - The parameter <code>diag</code> is null.

### <a id='FromDouble(double)'>FromDouble</a>

Converts a 64-bit floating-point number to a rational number. This method
 computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the number to a
 string. <p>The input value can be a not-a-number (NaN) value (such
 as <code>Double.NaN</code>); however, NaN values have multiple forms that
 are equivalent for many applications' purposes, and
 <code>Double.NaN</code> is only one of these equivalent forms. In fact,
 <code>ERational.FromDouble(Double.NaN)</code> could produce an object that
 is represented differently between DotNet and Java, because
 <code>Double.NaN</code> may have a different form in DotNet and Java (for
 example, the NaN value's sign may be negative in DotNet, but
 positive in Java). Use `IsNaN()` to determine whether an object from
 this class stores a NaN value of any form.</p>

**Parameters:**

* <code>flt</code> - The parameter <code>flt</code> is a 64-bit floating-point number.

**Returns:**

* A rational number with the same value as <code>flt</code>.

### <a id='FromExtendedDecimal(com.upokecenter.numbers.EDecimal)'>FromExtendedDecimal</a>

Converts an arbitrary-precision decimal number to a rational number.

**Parameters:**

* <code>ef</code> - The number to convert as an arbitrary-precision decimal number.

**Returns:**

* An arbitrary-precision rational number.

### <a id='FromExtendedFloat(com.upokecenter.numbers.EFloat)'>FromExtendedFloat</a>

Converts an arbitrary-precision binary floating-point number to a rational
 number.

**Parameters:**

* <code>ef</code> - The number to convert as an arbitrary-precision binary
 floating-point number.

**Returns:**

* An arbitrary-precision rational number.

### <a id='FromEDecimal(com.upokecenter.numbers.EDecimal)'>FromEDecimal</a>

Converts an arbitrary-precision decimal number to a rational number.

**Parameters:**

* <code>ef</code> - The number to convert as an arbitrary-precision decimal number.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ef</code> is null.

* <code>java.lang.IllegalArgumentException</code> - doesn't satisfy den.signum() &gt;= 0.

### <a id='FromEFloat(com.upokecenter.numbers.EFloat)'>FromEFloat</a>

Converts an arbitrary-precision binary floating-point number to a rational
 number.

**Parameters:**

* <code>ef</code> - The number to convert as an arbitrary-precision binary
 floating-point number.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ef</code> is null.

* <code>java.lang.IllegalArgumentException</code> - doesn't satisfy den.signum() &gt;= 0.

### <a id='FromEInteger(com.upokecenter.numbers.EInteger)'>FromEInteger</a>

Converts an arbitrary-precision integer to a rational number.

**Parameters:**

* <code>bigint</code> - The number to convert as an arbitrary-precision integer.

**Returns:**

* The exact value of the integer as a rational number.

### <a id='FromSingle(float)'>FromSingle</a>

Converts a 32-bit binary floating-point number to a rational number. This
 method computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the number to a
 string. <p>The input value can be a not-a-number (NaN) value (such
 as <code>Float.NaN</code> in DotNet or Float.NaN in Java); however, NaN
 values have multiple forms that are equivalent for many
 applications' purposes, and <code>Float.NaN</code> / <code>Float.NaN</code> is
 only one of these equivalent forms. In fact,
 <code>ERational.FromSingle(Float.NaN)</code> or
 <code>ERational.FromSingle(Float.NaN)</code> could produce an object that
 is represented differently between DotNet and Java, because
 <code>Float.NaN</code> / <code>Float.NaN</code> may have a different form in
 DotNet and Java (for example, the NaN value's sign may be negative
 in DotNet, but positive in Java). Use `IsNaN()` to determine whether
 an object from this class stores a NaN value of any form.</p>

**Parameters:**

* <code>flt</code> - The parameter <code>flt</code> is a 32-bit binary floating-point
 number.

**Returns:**

* A rational number with the same value as <code>flt</code>.

### <a id='FromSingleBits(int)'>FromSingleBits</a>

Creates a binary rational number from a 32-bit floating-point number encoded
 in the IEEE 754 binary32 format. This method computes the exact
 value of the floating point number, not an approximation, as is
 often the case by converting the number to a string.

**Parameters:**

* <code>value</code> - A 32-bit integer encoded in the IEEE 754 binary32 format.

**Returns:**

* A rational number with the same floating-point value as <code>
 value</code>.

### <a id='FromHalfBits(short)'>FromHalfBits</a>

Creates a binary rational number from a binary floating-point number encoded
  in the IEEE 754 binary16 format (also known as a "half-precision"
 floating-point number). This method computes the exact value of the
 floating point number, not an approximation, as is often the case by
 converting the number to a string.

**Parameters:**

* <code>value</code> - A 16-bit integer encoded in the IEEE 754 binary16 format.

**Returns:**

* A rational number with the same floating-point value as <code>
 value</code>.

### <a id='FromDoubleBits(long)'>FromDoubleBits</a>

Creates a binary rational number from a 64-bit floating-point number encoded
 in the IEEE 754 binary64 format. This method computes the exact
 value of the floating point number, not an approximation, as is
 often the case by converting the number to a string.

**Parameters:**

* <code>value</code> - A 64-bit integer encoded in the IEEE 754 binary64 format.

**Returns:**

* A rational number with the same floating-point value as <code>
 value</code>.

### <a id='FromString(java.lang.String)'>FromString</a>

Creates a rational number from a text string that represents a number. See
 <code>FromString(string, int, int)</code> for more information.

**Parameters:**

* <code>str</code> - A string that represents a number.

**Returns:**

* An arbitrary-precision rational number with the same value as the
 given string.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

### <a id='FromString(java.lang.String,int,int)'>FromString</a>

<p>Creates a rational number from a text string that represents a
 number.</p> <p>The format of the string generally consists of:</p>
  <ul> <li>An optional plus sign ("+" , U+002B) or minus sign ("-",
 U+002D) (if '-' , the value is negative.)</li> <li>The numerator in
 the form of one or more digits (these digits may begin with any
  number of zeros).</li> <li>Optionally, "/" followed by the
 denominator in the form of one or more digits (these digits may
 begin with any number of zeros). If a denominator is not given, it's
  equal to 1.</li></ul> <p>The string can also be "-INF", "-Infinity",
  "Infinity", "INF", quiet NaN ("NaN" /"-NaN") followed by any number
  of digits, or signaling NaN ("sNaN" /"-sNaN") followed by any number
 of digits, all in any combination of upper and lower case.</p>
 <p>All characters mentioned above are the corresponding characters
 in the Basic Latin range. In particular, the digits must be the
 basic digits 0 to 9 (U+0030 to U+0039). The string is not allowed to
 contain white space characters, including spaces.</p>

**Parameters:**

* <code>str</code> - A text string, a portion of which represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>str</code> 's length, or <code>str</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### <a id='FromString(char[])'>FromString</a>

Creates a rational number from a sequence of <code>char</code> s that represents a
 number. See <code>FromString(string, int, int)</code> for more
 information.

**Parameters:**

* <code>chars</code> - A sequence of <code>char</code> s that represents a number.

**Returns:**

* An arbitrary-precision rational number with the same value as the
 given sequence of <code>char</code> s.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>chars</code> is not a correctly
 formatted sequence of <code>char</code> s.

### <a id='FromString(char[],int,int)'>FromString</a>

<p>Creates a rational number from a sequence of <code>char</code> s that
 represents a number.</p> <p>The format of the sequence of
 <code>char</code> s generally consists of:</p> <ul> <li>An optional plus
  sign ("+" , U+002B) or minus sign ("-", U+002D) (if '-' , the value
 is negative.)</li> <li>The numerator in the form of one or more
 digits (these digits may begin with any number of zeros).</li>
  <li>Optionally, "/" followed by the denominator in the form of one
 or more digits (these digits may begin with any number of zeros). If
 a denominator is not given, it's equal to 1.</li></ul> <p>The
  sequence of <code>char</code> s can also be "-INF", "-Infinity",
  "Infinity", "INF", quiet NaN ("NaN" /"-NaN") followed by any number
  of digits, or signaling NaN ("sNaN" /"-sNaN") followed by any number
 of digits, all in any combination of upper and lower case.</p>
 <p>All characters mentioned above are the corresponding characters
 in the Basic Latin range. In particular, the digits must be the
 basic digits 0 to 9 (U+0030 to U+0039). The sequence of <code>char</code>
 s is not allowed to contain white space characters, including
 spaces.</p>

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

* <code>java.lang.NumberFormatException</code> - The parameter <code>chars</code> is not a correctly
 formatted sequence of <code>char</code> s.

* <code>java.lang.NullPointerException</code> - The parameter <code>chars</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>chars</code> 's length, or <code>chars</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### <a id='FromString(byte[])'>FromString</a>

Creates a rational number from a sequence of bytes that represents a number.
 See <code>FromString(string, int, int)</code> for more information.

**Parameters:**

* <code>bytes</code> - A sequence of bytes that represents a number.

**Returns:**

* An arbitrary-precision rational number with the same value as the
 given sequence of bytes.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>bytes</code> is not a correctly
 formatted sequence of bytes.

### <a id='FromString(byte[],int,int)'>FromString</a>

<p>Creates a rational number from a sequence of bytes that represents a
 number.</p> <p>The format of the sequence of bytes generally
  consists of:</p> <ul> <li>An optional plus sign ("+" , U+002B) or
  minus sign ("-", U+002D) (if '-' , the value is negative.)</li>
 <li>The numerator in the form of one or more digits (these digits
  may begin with any number of zeros).</li> <li>Optionally, "/"
 followed by the denominator in the form of one or more digits (these
 digits may begin with any number of zeros). If a denominator is not
 given, it's equal to 1.</li></ul> <p>The sequence of bytes can also
  be "-INF", "-Infinity", "Infinity", "INF", quiet NaN ("NaN" /"-NaN")
  followed by any number of digits, or signaling NaN ("sNaN" /"-sNaN")
 followed by any number of digits, all in any combination of upper
 and lower case.</p> <p>All characters mentioned above are the
 corresponding characters in the Basic Latin range. In particular,
 the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
 sequence of bytes is not allowed to contain white space characters,
 including spaces.</p>

**Parameters:**

* <code>bytes</code> - A sequence of bytes, a portion of which represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>bytes</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 bytes</code> (but not more than <code>bytes</code> 's length).

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>bytes</code> is not a correctly
 formatted sequence of bytes.

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>bytes</code> 's length, or <code>bytes</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### <a id='CompareToTotalMagnitude(com.upokecenter.numbers.ERational)'>CompareToTotalMagnitude</a>

Compares the absolute values of this object and another object, imposing a
 total ordering on all possible values (ignoring their signs). In
 this method: <ul> <li>For objects with the same value, the one with
  the higher denominator has a greater "absolute value".</li>
 <li>Negative zero and positive zero are considered equal.</li>
  <li>Quiet NaN has a higher "absolute value" than signaling NaN. If
 both objects are quiet NaN or both are signaling NaN, the one with
  the higher diagnostic information has a greater "absolute
  value".</li> <li>NaN has a higher "absolute value" than
  infinity.</li> <li>Infinity has a higher "absolute value" than any
 finite number.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number to compare with this
 one.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.
 This implementation returns a positive number if.

### <a id='CompareToTotal(com.upokecenter.numbers.ERational)'>CompareToTotal</a>

Compares the values of this object and another object, imposing a total
 ordering on all possible values. In this method: <ul> <li>For
 objects with the same value, the one with the higher denominator has
  a greater "absolute value".</li> <li>Negative zero is less than
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
 This implementation returns a positive number if.

### <a id='Abs()'>Abs</a>

Returns the absolute value of this rational number, that is, a number with
 the same value as this one but as a nonnegative number.

**Returns:**

* An arbitrary-precision rational number.

### <a id='Add(com.upokecenter.numbers.ERational)'>Add</a>

Adds this arbitrary-precision rational number and another
 arbitrary-precision rational number and returns the result.

**Parameters:**

* <code>otherValue</code> - Another arbitrary-precision rational number.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 rational number plus another arbitrary-precision rational number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>otherValue</code> is null.

### <a id='compareTo(com.upokecenter.numbers.ERational)'>compareTo</a>

Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance. This method currently uses the rules
 given in the CompareToValue method, so that it it is not consistent
 with the Equals method, but it may change in a future version to use
 the rules for the CompareToTotal method instead.

**Specified by:**

* <code>compareTo</code> in interface <code>java.lang.Comparable&lt;ERational&gt;</code>

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater. This
 implementation returns a positive number if.

### <a id='CompareToValue(com.upokecenter.numbers.ERational)'>CompareToValue</a>

Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance. In this method, NaN values are greater
 than any other ERational value, and two NaN values (even if their
 payloads differ) are treated as equal by this method. This method is
 not consistent with the Equals method.

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater. This
 implementation returns a positive number if.

### <a id='Max(com.upokecenter.numbers.ERational,com.upokecenter.numbers.ERational)'>Max</a>

Gets the greater value between two rational numbers.

**Parameters:**

* <code>first</code> - An arbitrary-precision rational number.

* <code>second</code> - Another arbitrary-precision rational number.

**Returns:**

* The larger value of the two numbers. If one is positive zero and the
 other is negative zero, returns the positive zero. If the two
 numbers are positive and have the same value, returns the one with
 the larger denominator. If the two numbers are negative and have the
 same value, returns the one with the smaller denominator.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### <a id='MaxMagnitude(com.upokecenter.numbers.ERational,com.upokecenter.numbers.ERational)'>MaxMagnitude</a>

Gets the greater value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Max.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* The larger value of the two numbers, ignoring their signs.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### <a id='Min(com.upokecenter.numbers.ERational,com.upokecenter.numbers.ERational)'>Min</a>

Gets the lesser value between two rational numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* The smaller value of the two numbers. If one is positive zero and
 the other is negative zero, returns the negative zero. If the two
 numbers are positive and have the same value, returns the one with
 the smaller denominator. If the two numbers are negative and have
 the same value, returns the one with the larger denominator.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### <a id='MinMagnitude(com.upokecenter.numbers.ERational,com.upokecenter.numbers.ERational)'>MinMagnitude</a>

Gets the lesser value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Min.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* The smaller value of the two numbers, ignoring their signs.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### <a id='compareTo(int)'>compareTo</a>

Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance. This method currently uses the rules
 given in the CompareToValue method, so that it it is not consistent
 with the Equals method, but it may change in a future version to use
 the rules for the CompareToTotal method instead.

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 32-bit signed integer.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater.

### <a id='CompareToValue(int)'>CompareToValue</a>

Compares the mathematical value of an arbitrary-precision rational number
 with that of this instance. In this method, NaN values are greater
 than any other ERational value, and two NaN values (even if their
 payloads differ) are treated as equal by this method. This method is
 not consistent with the Equals method.

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 32-bit signed integer.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater.

### <a id='CompareToValue(long)'>CompareToValue</a>

Compares the mathematical values of this object and another object,
 accepting NaN values. <p>This method is not consistent with the
 Equals method because two different numbers with the same
 mathematical value, but different exponents, will compare as
 equal.</p> <p>In this method, negative zero and positive zero are
 considered equal.</p> <p>If this object is a quiet NaN or signaling
 NaN, this method will not trigger an error. Instead, NaN will
 compare greater than any other number, including infinity.</p>

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 64-bit signed integer.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
 value, or 0 if both values are equal.

### <a id='compareTo(long)'>compareTo</a>

Compares the mathematical values of this object and another object,
 accepting NaN values. This method currently uses the rules given in
 the CompareToValue method, so that it it is not consistent with the
 Equals method, but it may change in a future version to use the
 rules for the CompareToTotal method instead.

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 64-bit signed integer.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
 value, or 0 if both values are equal.

### <a id='CompareToBinary(com.upokecenter.numbers.EFloat)'>CompareToBinary</a>

Compares an arbitrary-precision binary floating-point number with this
 instance. In this method, NaN values are greater than any other
 ERational or EFloat value, and two NaN values (even if their
 payloads differ) are treated as equal by this method.

**Parameters:**

* <code>other</code> - An arbitrary-precision binary floating-point number.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater. This
 implementation returns a positive number if.

### <a id='CompareToDecimal(com.upokecenter.numbers.EDecimal)'>CompareToDecimal</a>

Compares an arbitrary-precision decimal number with this instance.

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less, or a positive number if this instance is greater. This
 implementation returns a positive number if.

### <a id='CopySign(com.upokecenter.numbers.ERational)'>CopySign</a>

Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number.

**Parameters:**

* <code>other</code> - A number whose sign will be copied.

**Returns:**

* An arbitrary-precision rational number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>other</code> is null.

### <a id='Divide(com.upokecenter.numbers.ERational)'>Divide</a>

Divides this arbitrary-precision rational number by another
 arbitrary-precision rational number and returns the result.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The result of dividing this arbitrary-precision rational number by
 another arbitrary-precision rational number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>otherValue</code> is null.

### <a id='equals(java.lang.Object)'>equals</a>

Determines whether this object's numerator, denominator, and properties are
 equal to those of another object and that other object is an
 arbitrary-precision rational number. Not-a-number values are
 considered equal if the rest of their properties are equal. This is
 not the same as value equality. Notably, two ERationals with the
 same value, but of which one is in lowest terms and the other is
 not, are compared as unequal by this method (example: 1/2 vs. 5/10).

**Overrides:**

* <code>equals</code> in class <code>java.lang.Object</code>

**Parameters:**

* <code>obj</code> - The parameter <code>obj</code> is an arbitrary object.

**Returns:**

* <code>true</code> if the objects are equal; otherwise, <code>false</code>. In
 this method, two objects are not equal if they don't have the same
 type or if one is null and the other isn't.

### <a id='equals(com.upokecenter.numbers.ERational)'>equals</a>

Determines whether this object's numerator, denominator, and properties are
 equal to those of another object. Not-a-number values are considered
 equal if the rest of their properties are equal.

**Parameters:**

* <code>other</code> - An arbitrary-precision rational number to compare to.

**Returns:**

* Either <code>true</code> or <code>false</code>.

### <a id='hashCode()'>hashCode</a>

Returns the hash code for this instance. No application or process IDs are
 used in the hash code calculation.

**Overrides:**

* <code>hashCode</code> in class <code>java.lang.Object</code>

**Returns:**

* A 32-bit signed integer.

### <a id='IsInfinity()'>IsInfinity</a>

Gets a value indicating whether this object's value is infinity.

**Returns:**

* <code>true</code> if this object's value is infinity; otherwise, <code>
 false</code>.

### <a id='IsNaN()'>IsNaN</a>

Returns whether this object is a not-a-number value.

**Returns:**

* <code>true</code> if this object is a not-a-number value; otherwise,
 <code>false</code>.

### <a id='IsNegativeInfinity()'>IsNegativeInfinity</a>

Returns whether this object is negative infinity.

**Returns:**

* <code>true</code> if this object is negative infinity; otherwise, <code>
 false</code>.

### <a id='IsPositiveInfinity()'>IsPositiveInfinity</a>

Returns whether this object is positive infinity.

**Returns:**

* <code>true</code> if this object is positive infinity; otherwise, <code>
 false</code>.

### <a id='IsQuietNaN()'>IsQuietNaN</a>

Returns whether this object is a quiet not-a-number value.

**Returns:**

* <code>true</code> if this object is a quiet not-a-number value;
 otherwise, <code>false</code>.

### <a id='IsSignalingNaN()'>IsSignalingNaN</a>

Returns whether this object is a signaling not-a-number value (which causes
 an error if the value is passed to any arithmetic operation in this
 class).

**Returns:**

* <code>true</code> if this object is a signaling not-a-number value (which
 causes an error if the value is passed to any arithmetic operation
 in this class); otherwise, <code>false</code>.

### <a id='Multiply(com.upokecenter.numbers.ERational)'>Multiply</a>

Multiplies this arbitrary-precision rational number by another
 arbitrary-precision rational number and returns the result.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 rational number times another arbitrary-precision rational number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>otherValue</code> is null.

### <a id='Negate()'>Negate</a>

Returns a rational number with the same value as this one but with the sign
 reversed.

**Returns:**

* An arbitrary-precision rational number.

### <a id='Remainder(com.upokecenter.numbers.ERational)'>Remainder</a>

Returns the remainder that would result when this arbitrary-precision
 rational number is divided by another arbitrary-precision rational
 number.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The remainder that would result when this arbitrary-precision
 rational number is divided by another arbitrary-precision rational
 number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>otherValue</code> is null.

### <a id='Subtract(com.upokecenter.numbers.ERational)'>Subtract</a>

Subtracts an arbitrary-precision rational number from this
 arbitrary-precision rational number and returns the result.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision rational number.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision rational number minus another
 arbitrary-precision rational number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>otherValue</code> is null.

### <a id='ToDouble()'>ToDouble</a>

Converts this value to a 64-bit floating-point number. The half-even
 rounding mode is used.

**Returns:**

* The closest 64-bit floating-point number to this value. The return
 value can be positive infinity or negative infinity if this value
 exceeds the range of a 64-bit floating point number.

### <a id='ToDoubleBits()'>ToDoubleBits</a>

Converts this value to its closest equivalent as a 64-bit floating-point
 number, expressed as an integer in the IEEE 754 binary64 format. The
 half-even rounding mode is used. <p>If this value is a NaN, sets the
 high bit of the 64-bit floating point number's significand area for
 a quiet NaN, and clears it for a signaling NaN. Then the other bits
 of the significand area are set to the lowest bits of this object's
 unsigned significand, and the next-highest bit of the significand
 area is set if those bits are all zeros and this is a signaling
 NaN.</p>

**Returns:**

* The closest 64-bit binary floating-point number to this value,
 expressed as an integer in the IEEE 754 binary64 format. The return
 value can be positive infinity or negative infinity if this value
 exceeds the range of a 64-bit floating point number.

### <a id='ToSingleBits()'>ToSingleBits</a>

Converts this value to its closest equivalent as 32-bit floating-point
 number, expressed as an integer in the IEEE 754 binary32 format. The
 half-even rounding mode is used. <p>If this value is a NaN, sets the
 high bit of the 32-bit floating point number's significand area for
 a quiet NaN, and clears it for a signaling NaN. Then the other bits
 of the significand area are set to the lowest bits of this object's
 unsigned significand, and the next-highest bit of the significand
 area is set if those bits are all zeros and this is a signaling
 NaN.</p>

**Returns:**

* The closest 32-bit binary floating-point number to this value,
 expressed as an integer in the IEEE 754 binary32 format. The return
 value can be positive infinity or negative infinity if this value
 exceeds the range of a 32-bit floating point number.

### <a id='ToHalfBits()'>ToHalfBits</a>

Converts this value to its closest equivalent as a binary floating-point
 number, expressed as an integer in the IEEE 754 binary16 format
  (also known as a "half-precision" floating-point number). The
 half-even rounding mode is used. <p>If this value is a NaN, sets the
 high bit of the binary16 number's significand area for a quiet NaN,
 and clears it for a signaling NaN. Then the other bits of the
 significand area are set to the lowest bits of this object's
 unsigned significand, and the next-highest bit of the significand
 area is set if those bits are all zeros and this is a signaling
 NaN.</p>

**Returns:**

* The closest binary floating-point number to this value, expressed as
 an integer in the IEEE 754 binary16 format. The return value can be
 positive infinity or negative infinity if this value exceeds the
 range of a floating-point number in the binary16 format.

### <a id='ToLowestTerms()'>ToLowestTerms</a>

Converts this value to its form in lowest terms. For example, (8/4) becomes
 (4/1).

**Returns:**

* An arbitrary-precision rational with the same value as this one but
 in lowest terms. Returns this object if it is infinity or NaN.
 Returns ERational.NegativeZero if this object is a negative zero.
 Returns ERational.Zero if this object is a positive zero.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

### <a id='ToSizedEInteger(int)'>ToSizedEInteger</a>

Converts this value to an arbitrary-precision integer by dividing the
 numerator by the denominator, discarding its fractional part, and
 checking whether the resulting integer overflows the given signed
 bit count.

**Parameters:**

* <code>maxBitLength</code> - The maximum number of signed bits the integer can have.
 The integer's value may not be less than -(2^maxBitLength) or
 greater than (2^maxBitLength) - 1.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN), or this number's value, once converted to an integer by
 dividing the numerator by the denominator and discarding its
 fractional part, is less than -(2^maxBitLength) or greater than
 (2^maxBitLength) - 1.

### <a id='ToSizedEIntegerIfExact(int)'>ToSizedEIntegerIfExact</a>

Converts this value to an arbitrary-precision integer, only if this number's
 value is an exact integer and that integer does not overflow the
 given signed bit count.

**Parameters:**

* <code>maxBitLength</code> - The maximum number of signed bits the integer can have.
 The integer's value may not be less than -(2^maxBitLength) or
 greater than (2^maxBitLength) - 1.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN), or this number's value as an integer is less than
 -(2^maxBitLength) or greater than (2^maxBitLength) - 1.

* <code>java.lang.ArithmeticException</code> - This object's value is not an exact integer.

### <a id='ToEInteger()'>ToEInteger</a>

Converts this value to an arbitrary-precision integer by dividing the
 numerator by the denominator and discarding the fractional part of
 the result.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

### <a id='ToEIntegerExact()'>ToEIntegerExact</a>

Converts this value to an arbitrary-precision integer, checking whether the
 value is an exact integer.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

### <a id='ToEIntegerIfExact()'>ToEIntegerIfExact</a>

Converts this value to an arbitrary-precision integer, checking whether the
 value is an exact integer.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

### <a id='ToEDecimal()'>ToEDecimal</a>

Converts this rational number to an arbitrary-precision decimal number.

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating decimal
 expansion.

### <a id='ToEDecimal(com.upokecenter.numbers.EContext)'>ToEDecimal</a>

Converts this rational number to an arbitrary-precision decimal number and
 rounds the result to the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result
 can't be exact because it has a nonterminating decimal expansion.

### <a id='ToEDecimalExactIfPossible(com.upokecenter.numbers.EContext)'>ToEDecimalExactIfPossible</a>

Converts this rational number to an arbitrary-precision decimal number, but
 if the result would have a nonterminating decimal expansion, rounds
 that result to the given precision.

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

### <a id='ToExtendedDecimal()'>ToExtendedDecimal</a>

Converts this rational number to an arbitrary-precision decimal number.

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating decimal
 expansion.

### <a id='ToExtendedDecimal(com.upokecenter.numbers.EContext)'>ToExtendedDecimal</a>

Converts this rational number to an arbitrary-precision decimal number and
 rounds the result to the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result
 can't be exact because it has a nonterminating decimal expansion.

### <a id='ToExtendedDecimalExactIfPossible(com.upokecenter.numbers.EContext)'>ToExtendedDecimalExactIfPossible</a>

Converts this rational number to an arbitrary-precision decimal number, but
 if the result would have a nonterminating decimal expansion, rounds
 that result to the given precision.

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

### <a id='ToEFloat()'>ToEFloat</a>

Converts this rational number to a binary floating-point number.

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating binary
 expansion.

### <a id='ToEFloat(com.upokecenter.numbers.EContext)'>ToEFloat</a>

Converts this rational number to a binary floating-point number and rounds
 that result to the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result
 can't be exact because it has a nonterminating binary expansion.

### <a id='ToEFloatExactIfPossible(com.upokecenter.numbers.EContext)'>ToEFloatExactIfPossible</a>

Converts this rational number to a binary floating-point number, but if the
 result would have a nonterminating binary expansion, rounds that
 result to the given precision.

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

### <a id='ToExtendedFloat()'>ToExtendedFloat</a>

Converts this rational number to a binary floating-point number.

**Returns:**

* The exact value of the rational number, or not-a-number (NaN) if the
 result can't be exact because it has a nonterminating binary
 expansion.

### <a id='ToExtendedFloat(com.upokecenter.numbers.EContext)'>ToExtendedFloat</a>

Converts this rational number to a binary floating-point number and rounds
 that result to the given precision.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. If HasFlags of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The value of the rational number, rounded to the given precision.
 Returns not-a-number (NaN) if the context is null and the result
 can't be exact because it has a nonterminating binary expansion.

### <a id='ToExtendedFloatExactIfPossible(com.upokecenter.numbers.EContext)'>ToExtendedFloatExactIfPossible</a>

Converts this rational number to a binary floating-point number, but if the
 result would have a nonterminating binary expansion, rounds that
 result to the given precision.

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

### <a id='ToSingle()'>ToSingle</a>

Converts this value to a 32-bit binary floating-point number. The half-even
 rounding mode is used.

**Returns:**

* The closest 32-bit binary floating-point number to this value. The
 return value can be positive infinity or negative infinity if this
 value exceeds the range of a 32-bit floating point number.

### <a id='toString()'>toString</a>

Converts this object to a text string.

**Overrides:**

* <code>toString</code> in class <code>java.lang.Object</code>

**Returns:**

* A string representation of this object. If this object's value is
 infinity or not-a-number, the result is the analogous return value
 of the <code>EDecimal.toString</code> method. Otherwise, the return value
 has the following form: <code>[-]numerator.Divide(denominator)</code>.

### <a id='Increment()'>Increment</a>

Adds one to an arbitrary-precision rational number.

**Returns:**

* The given arbitrary-precision rational number plus one.

### <a id='Decrement()'>Decrement</a>

Subtracts one from an arbitrary-precision rational number.

**Returns:**

* The given arbitrary-precision rational number minus one.

### <a id='Add(int)'>Add</a>

Adds this arbitrary-precision rational number and a 32-bit signed integer
 and returns the result.

**Parameters:**

* <code>v</code> - A 32-bit signed integer.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 rational number plus a 32-bit signed integer.

### <a id='Subtract(int)'>Subtract</a>

Subtracts a 32-bit signed integer from this arbitrary-precision rational
 number and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 32-bit signed integer.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision rational number minus a 32-bit signed integer.

### <a id='Multiply(int)'>Multiply</a>

Multiplies this arbitrary-precision rational number by a 32-bit signed
 integer and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 32-bit signed integer.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 rational number times a 32-bit signed integer.

### <a id='Divide(int)'>Divide</a>

Divides this arbitrary-precision rational number by a 32-bit signed integer
 and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 32-bit signed integer.

**Returns:**

* The result of dividing this arbitrary-precision rational number by a
 32-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - The parameter <code>v</code> is zero.

### <a id='Remainder(int)'>Remainder</a>

Returns the remainder that would result when this arbitrary-precision
 rational number is divided by a 32-bit signed integer.

**Parameters:**

* <code>v</code> - The divisor.

**Returns:**

* The remainder that would result when this arbitrary-precision
 rational number is divided by a 32-bit signed integer.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>v</code> is zero.

### <a id='Add(long)'>Add</a>

Adds this arbitrary-precision rational number and a 64-bit signed integer
 and returns the result.

**Parameters:**

* <code>v</code> - A 64-bit signed integer.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 rational number plus a 64-bit signed integer.

### <a id='Subtract(long)'>Subtract</a>

Subtracts a 64-bit signed integer from this arbitrary-precision rational
 number and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 64-bit signed integer.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision rational number minus a 64-bit signed integer.

### <a id='Multiply(long)'>Multiply</a>

Multiplies this arbitrary-precision rational number by a 64-bit signed
 integer and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 64-bit signed integer.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 rational number times a 64-bit signed integer.

### <a id='Divide(long)'>Divide</a>

Divides this arbitrary-precision rational number by a 64-bit signed integer
 and returns the result.

**Parameters:**

* <code>v</code> - The parameter <code>v</code> is a 64-bit signed integer.

**Returns:**

* The result of dividing this arbitrary-precision rational number by a
 64-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - The parameter <code>v</code> is zero.

### <a id='Remainder(long)'>Remainder</a>

Returns the remainder that would result when this arbitrary-precision
 rational number is divided by a 64-bit signed integer.

**Parameters:**

* <code>v</code> - The divisor.

**Returns:**

* The remainder that would result when this arbitrary-precision
 rational number is divided by a 64-bit signed integer.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>v</code> is zero.

### <a id='ToByteChecked()'>ToByteChecked</a>

Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) after converting it to an integer by discarding
 its fractional part.

**Returns:**

* This number's value, truncated to a byte (from 0 to 255).

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional
 part, is less than 0 or greater than 255.

### <a id='ToByteUnchecked()'>ToByteUnchecked</a>

Converts this number's value to an integer (using ToEInteger), and returns
 the least-significant bits of that integer's two's-complement form
 as a byte (from 0 to 255).

**Returns:**

* This number, converted to a byte (from 0 to 255). Returns 0 if this
 value is infinity or not-a-number.

### <a id='ToByteIfExact()'>ToByteIfExact</a>

Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) without rounding to a different numerical
 value.

**Returns:**

* This number's value as a byte (from 0 to 255).

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than 0 or greater than 255.

### <a id='FromByte(byte)'>FromByte</a>

Converts a byte (from 0 to 255) to an arbitrary-precision rational number.

**Parameters:**

* <code>inputByte</code> - The number to convert as a byte (from 0 to 255).

**Returns:**

* This number's value as an arbitrary-precision rational number.

### <a id='ToInt16Checked()'>ToInt16Checked</a>

Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer after converting it to an integer by
 discarding its fractional part.

**Returns:**

* This number's value, truncated to a 16-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional
 part, is less than -32768 or greater than 32767.

### <a id='ToInt16Unchecked()'>ToInt16Unchecked</a>

Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 16-bit signed integer.

**Returns:**

* This number, converted to a 16-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### <a id='ToInt16IfExact()'>ToInt16IfExact</a>

Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer without rounding to a different numerical
 value.

**Returns:**

* This number's value as a 16-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -32768 or greater than 32767.

### <a id='FromInt16(short)'>FromInt16</a>

Converts a 16-bit signed integer to an arbitrary-precision rational number.

**Parameters:**

* <code>inputInt16</code> - The number to convert as a 16-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision rational number.

### <a id='ToInt32Checked()'>ToInt32Checked</a>

Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer after converting it to an integer by
 discarding its fractional part.

**Returns:**

* This number's value, truncated to a 32-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional
 part, is less than -2147483648 or greater than 2147483647.

### <a id='ToInt32Unchecked()'>ToInt32Unchecked</a>

Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 32-bit signed integer.

**Returns:**

* This number, converted to a 32-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### <a id='ToInt32IfExact()'>ToInt32IfExact</a>

Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer without rounding to a different numerical
 value.

**Returns:**

* This number's value as a 32-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -2147483648 or greater than
 2147483647.

### <a id='FromBoolean(boolean)'>FromBoolean</a>

Converts a boolean value (true or false) to an arbitrary-precision rational
 number.

**Parameters:**

* <code>boolValue</code> - Either true or false.

**Returns:**

* The number 1 if <code>boolValue</code> is true; otherwise, 0.

### <a id='FromInt32(int)'>FromInt32</a>

Converts a 32-bit signed integer to an arbitrary-precision rational number.

**Parameters:**

* <code>inputInt32</code> - The number to convert as a 32-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision rational number.

### <a id='ToInt64Checked()'>ToInt64Checked</a>

Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer after converting it to an integer by
 discarding its fractional part.

**Returns:**

* This number's value, truncated to a 64-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or the
 number, once converted to an integer by discarding its fractional
 part, is less than -9223372036854775808 or greater than
 9223372036854775807.

### <a id='ToInt64Unchecked()'>ToInt64Unchecked</a>

Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a 64-bit signed integer.

**Returns:**

* This number, converted to a 64-bit signed integer. Returns 0 if this
 value is infinity or not-a-number.

### <a id='ToInt64IfExact()'>ToInt64IfExact</a>

Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer without rounding to a different numerical
 value.

**Returns:**

* This number's value as a 64-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -9223372036854775808 or greater
 than 9223372036854775807.

### <a id='FromInt64AsUnsigned(long)'>FromInt64AsUnsigned</a>

Converts an unsigned integer expressed as a 64-bit signed integer to an
 arbitrary-precision rational number.

**Parameters:**

* <code>longerValue</code> - A 64-bit signed integer. If this value is 0 or greater,
 the return value will represent it. If this value is less than 0,
 the return value will store 2^64 plus this value instead.

**Returns:**

* An arbitrary-precision rational number. If <code>longerValue</code> is 0
 or greater, the return value will represent it. If <code>
 longerValue</code> is less than 0, the return value will store 2^64 plus
 this value instead.

### <a id='FromInt64(long)'>FromInt64</a>

Converts a 64-bit signed integer to an arbitrary-precision rational number.

**Parameters:**

* <code>inputInt64</code> - The number to convert as a 64-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision rational number.
