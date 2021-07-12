# com.upokecenter.numbers.EFloats

## Methods

* `static EFloat And​(EFloat ed1,
EFloat ed2,
EContext ec)`<br>
 Performs a logical AND operation on two binary numbers in the form of
 logical operands.
* `static EFloat BooleanToEFloat​(boolean b,
EContext ec)`<br>
 Converts a boolean value (either true or false) to an arbitrary-precision
 binary floating-point number.
* `static EFloat Canonical​(EFloat ed)`<br>
 Returns a canonical version of the given arbitrary-precision number object.
* `static int CompareTotal​(EFloat ed,
EFloat other,
EContext ec)`<br>
 Compares the values of one arbitrary-precision number object and another
 object, imposing a total ordering on all possible values.
* `static int CompareTotalMagnitude​(EFloat ed,
EFloat other,
EContext ec)`<br>
 Compares the absolute values of two arbitrary-precision number objects,
 imposing a total ordering on all possible values (ignoring their
 signs).
* `static EFloat Copy​(EFloat ed)`<br>
 Creates a copy of the given arbitrary-precision number object.
* `static EFloat CopyAbs​(EFloat ed)`<br>
 Returns an arbitrary-precision number object with the same value as the
 given number object but with a nonnegative sign (that is, the given
 number object's absolute value).
* `static EFloat CopyNegate​(EFloat ed)`<br>
 Returns an arbitrary-precision number object with the sign reversed from the
 given number object.
* `static EFloat CopySign​(EFloat ed,
EFloat other)`<br>
 Returns an arbitrary-precision number object with the same value as the
 first given number object but with a the same sign (positive or
 negative) as the second given number object.
* `static EFloat Int32ToEFloat​(int i32,
EContext ec)`<br>
 Creates a binary floating-point number from a 32-bit signed integer.
* `static EFloat Invert​(EFloat ed1,
EContext ec)`<br>
 Performs a logical NOT operation on a binary number in the form of a
 logical operand.
* `static boolean IsCanonical​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is in a
 canonical form.
* `static boolean IsFinite​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is neither null
 nor infinity nor not-a-number (NaN).
* `static boolean IsInfinite​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is positive or
 negative infinity.
* `static boolean IsNaN​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is a
 not-a-number (NaN).
* `static boolean IsNormal​(EFloat ed,
EContext ec)`<br>
 Returns whether the given number is a normal number.
* `static boolean IsQuietNaN​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is a quiet
 not-a-number (NaN).
* `static boolean IsSignalingNaN​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is a signaling
 not-a-number (NaN).
* `static boolean IsSigned​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is negative
 (including negative infinity, negative not-a-number.get(NaN), or
 negative zero).
* `static boolean IsSubnormal​(EFloat ed,
EContext ec)`<br>
 Returns whether the given number is a subnormal number.
* `static boolean IsZero​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is zero
 (positive zero or negative zero).
* `static EFloat LogB​(EFloat ed,
EContext ec)`<br>
 Returns the base-2 exponent of an arbitrary-precision binary number (when
 that number is expressed in scientific notation with one nonzero
 digit before the radix point).
* `static int NumberClass​(EFloat ed,
EContext ec)`<br>
 Finds the number class for an arbitrary-precision binary number object.
* `static java.lang.String NumberClassString​(int nc)`<br>
 Converts a number class identifier (ranging from 0 through 9) to a text
 string.
* `static EFloat Or​(EFloat ed1,
EFloat ed2,
EContext ec)`<br>
 Performs a logical OR operation on two binary numbers in the form of
 logical operands.
* `static EFloat Radix​(EContext ec)`<br>
 Returns the number 2, the binary radix.
* `static EFloat Rescale​(EFloat ed,
EFloat scale,
EContext ec)`<br>
 Returns an arbitrary-precision binary number with the same value as this
 object but with the given exponent, expressed as an
 arbitrary-precision binary number.
* `static EFloat Rotate​(EFloat ed,
EFloat ed2,
EContext ec)`<br>
 Rotates the bits of an arbitrary-precision binary number's significand.
* `static boolean SameQuantum​(EFloat ed1,
EFloat ed2)`<br>
 Returns whether two arbitrary-precision numbers have the same exponent, they
 both are not-a-number (NaN), or they both are infinity (positive
 and/or negative).
* `static EFloat ScaleB​(EFloat ed,
EFloat ed2,
EContext ec)`<br>
 Finds an arbitrary-precision binary number whose binary point is moved a
 given number of places.
* `static EFloat Shift​(EFloat ed,
EFloat ed2,
EContext ec)`<br>
 Shifts the bits of an arbitrary-precision binary floating point number's
 significand.
* `static EFloat Trim​(EFloat ed1,
EContext ec)`<br>
 Returns an arbitrary-precision number with the same value as this one but
 with certain trailing zeros removed from its significand.
* `static EFloat Xor​(EFloat ed1,
EFloat ed2,
EContext ec)`<br>
 Performs a logical exclusive-OR (XOR) operation on two binary numbers in the
 form of logical operands.

## Method Details

### <a id='Radix(com.upokecenter.numbers.EContext)'>Radix</a>

Returns the number 2, the binary radix.

**Parameters:**

* <code>ec</code> - Specifies an arithmetic context for rounding the number 2. Can be
 null.

**Returns:**

* The number 2, or the closest representable number to 2 in the
 arithmetic context.

### <a id='Int32ToEFloat(int,com.upokecenter.numbers.EContext)'>Int32ToEFloat</a>

Creates a binary floating-point number from a 32-bit signed integer.

**Parameters:**

* <code>i32</code> - The parameter <code>i32</code> is a 32-bit signed integer.

* <code>ec</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. Can be null.

**Returns:**

* An arbitrary-precision binary floating-point number with the closest
 representable value to the given integer.

### <a id='BooleanToEFloat(boolean,com.upokecenter.numbers.EContext)'>BooleanToEFloat</a>

Converts a boolean value (either true or false) to an arbitrary-precision
 binary floating-point number.

**Parameters:**

* <code>b</code> - Either true or false.

* <code>ec</code> - A context used for rounding the result. Can be null.

**Returns:**

* Either 1 if <code>b</code> is true, or 0 if <code>b</code> is false.. The
 result will be rounded as specified by the given context, if any.

### <a id='IsCanonical(com.upokecenter.numbers.EFloat)'>IsCanonical</a>

Returns whether the given arbitrary-precision number object is in a
 canonical form. For the current version of EFloat, all EFloat
 objects are in a canonical form.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Always <code>true</code>.

### <a id='IsFinite(com.upokecenter.numbers.EFloat)'>IsFinite</a>

Returns whether the given arbitrary-precision number object is neither null
 nor infinity nor not-a-number (NaN).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> if the given arbitrary-precision number object
 is neither null nor infinity nor not-a-number (NaN), or <code>
 false</code> otherwise.

### <a id='IsInfinite(com.upokecenter.numbers.EFloat)'>IsInfinite</a>

Returns whether the given arbitrary-precision number object is positive or
 negative infinity.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> if the given arbitrary-precision number object
 is positive or negative infinity, or <code>false</code> otherwise.

### <a id='IsNaN(com.upokecenter.numbers.EFloat)'>IsNaN</a>

Returns whether the given arbitrary-precision number object is a
 not-a-number (NaN).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> or <code>false</code>.

### <a id='IsNormal(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>IsNormal</a>

Returns whether the given number is a <i>normal</i> number. A <i>subnormal
 number</i> is a nonzero finite number whose Exponent property (or
 the number's exponent when that number is expressed in scientific
 notation with one digit before the radix point) is less than the
 minimum possible exponent for that number. A <i>normal number</i> is
 nonzero and finite, but not subnormal.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

* <code>ec</code> - A context specifying the exponent range of arbitrary-precision
 numbers. Can be null. If AdjustExponent of the given context is
 <code>true</code>, a nonzero number is normal if the number's exponent
 (when that number is expressed in scientific notation with one
 nonzero digit before the radix point) is at least the given
 context's EMax property (e.g., if EMax is -100, 2.3456 * 10
 <sup>-99</sup> is normal, but 2.3456 * 10 <sup>-102</sup> is not).
 If AdjustExponent of the given context is <code>false</code>, a nonzero
 number is subnormal if the number's Exponent property is at least
 given context's EMax property (e.g., if EMax is -100, 23456 * 10
 <sup>-99</sup> is normal, but 23456 * 10 <sup>-102</sup> is not).

**Returns:**

* Either <code>true</code> if the given number is subnormal, or <code>
 false</code> otherwise. Returns <code>true</code> if the given context is null
 or HasExponentRange of the given context is <code>false</code>.

### <a id='IsQuietNaN(com.upokecenter.numbers.EFloat)'>IsQuietNaN</a>

Returns whether the given arbitrary-precision number object is a quiet
 not-a-number (NaN).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> or <code>false</code>.

### <a id='IsSigned(com.upokecenter.numbers.EFloat)'>IsSigned</a>

Returns whether the given arbitrary-precision number object is negative
 (including negative infinity, negative not-a-number.get(NaN), or
 negative zero).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> or <code>false</code>.

### <a id='IsSignalingNaN(com.upokecenter.numbers.EFloat)'>IsSignalingNaN</a>

Returns whether the given arbitrary-precision number object is a signaling
 not-a-number (NaN).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> or <code>false</code>.

### <a id='NumberClassString(int)'>NumberClassString</a>

Converts a number class identifier (ranging from 0 through 9) to a text
 string. An arbitrary-precision number object can belong in one of
 ten number classes.

**Parameters:**

* <code>nc</code> - An integer identifying a number class.

**Returns:**

* A text string identifying the given number class as follows: 0 =
  "+Normal"; 1 = "-Normal", 2 = "+Subnormal", 3 = "-Subnormal", 4 =
  "+Zero", 5 = "-Zero", 6 = "+Infinity", 7 = "-Infinity", 8 = "NaN", 9
  = "sNaN".

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>nc</code> is less than 0 or greater
 than 9.

### <a id='NumberClass(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>NumberClass</a>

Finds the number class for an arbitrary-precision binary number object.

**Parameters:**

* <code>ed</code> - An arbitrary-precision binary number object.

* <code>ec</code> - A context object that specifies the precision and exponent range
 of arbitrary-precision numbers. This is used only to distinguish
 between normal and subnormal numbers. Can be null.

**Returns:**

* A 32-bit signed integer identifying the given number object, number
 class as follows: 0 = positive normal; 1 = negative normal, 2 =
 positive subnormal, 3 = negative subnormal, 4 = positive zero, 5 =
 negative zero, 6 = positive infinity, 7 = negative infinity, 8 =
 quiet not-a-number (NaN), 9 = signaling NaN.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> is null.

### <a id='IsSubnormal(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>IsSubnormal</a>

Returns whether the given number is a <i>subnormal</i> number. A
 <i>subnormal number</i> is a nonzero finite number whose Exponent
 property (or the number's exponent when that number is expressed in
 scientific notation with one digit before the radix point) is less
 than the minimum possible exponent for that number.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

* <code>ec</code> - A context specifying the exponent range of arbitrary-precision
 numbers. Can be null. If AdjustExponent of the given context is
 <code>true</code>, a nonzero number is subnormal if the number's exponent
 (when that number is expressed in scientific notation with one
 nonzero digit before the radix point) is less than the given
 context's EMax property (e.g., if EMax is -100, 2.3456 * 10
 <sup>-102</sup> is subnormal, but 2.3456 * 10 <sup>-99</sup> is
 not). If AdjustExponent of the given context is <code>false</code>, a
 nonzero number is subnormal if the number's Exponent property is
 less than the given context's EMax property (e.g., if EMax is -100,
 23456 * 10 <sup>-102</sup> is subnormal, but 23456 * 10
 <sup>-99</sup> is not).

**Returns:**

* Either <code>true</code> if the given number is subnormal, or <code>
 false</code> otherwise. Returns <code>false</code> if the given context is null
 or HasExponentRange of the given context is <code>false</code>.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> is null.

### <a id='IsZero(com.upokecenter.numbers.EFloat)'>IsZero</a>

Returns whether the given arbitrary-precision number object is zero
 (positive zero or negative zero).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* <code>true</code> if the given number has a value of zero (positive zero
 or negative zero); otherwise, <code>false</code>.

### <a id='LogB(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>LogB</a>

Returns the base-2 exponent of an arbitrary-precision binary number (when
 that number is expressed in scientific notation with one nonzero
 digit before the radix point). For example, returns 3 for the
 numbers <code>1.11b * 2^3</code> and <code>111 * 2^1</code>.

**Parameters:**

* <code>ed</code> - An arbitrary-precision binary number.

* <code>ec</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. Can be null.

**Returns:**

* The base-2 exponent of the given number (when that number is
 expressed in scientific notation with one nonzero digit before the
 radix point). Signals DivideByZero and returns negative infinity if
 <code>ed</code> is zero. Returns positive infinity if <code>ed</code> is
 positive infinity or negative infinity.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> is null.

### <a id='ScaleB(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>ScaleB</a>

Finds an arbitrary-precision binary number whose binary point is moved a
 given number of places.

**Parameters:**

* <code>ed</code> - An arbitrary-precision binary number.

* <code>ed2</code> - The number of binary places to move the binary point of "ed".
 This must be an integer with an exponent of 0.

* <code>ec</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. Can be null.

**Returns:**

* The given arbitrary-precision binary number whose binary point is
 moved the given number of places. Signals an invalid operation and
 returns not-a-number (NaN) if <code>ed2</code> is infinity or NaN, has an
 Exponent property other than 0. Signals an invalid operation and
 returns not-a-number (NaN) if <code>ec</code> defines a limited precision
 and exponent range and if <code>ed2</code> 's absolute value is greater
 than twice the sum of the context's EMax property and its Precision
 property.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> or <code>ed2</code> is
 null.

### <a id='Shift(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>Shift</a>

Shifts the bits of an arbitrary-precision binary floating point number's
 significand.

**Parameters:**

* <code>ed</code> - An arbitrary-precision binary floating point number containing the
 significand to shift.

* <code>ed2</code> - An arbitrary-precision number indicating the number of bits to
 shift the first operand's significand. Must be an integer with an
 exponent of 0. If this parameter is positive, the significand is
 shifted to the left by the given number of bits. If this parameter
 is negative, the significand is shifted to the right by the given
 number of bits.

* <code>ec</code> - An arithmetic context to control the precision of
 arbitrary-precision numbers. Can be null.

**Returns:**

* An arbitrary-precision binary number whose significand is shifted
 the given number of bits. Signals an invalid operation and returns
 NaN (not-a-number) if <code>ed2</code> is a signaling NaN or if <code>
 ed2</code> is not an integer, is negative, has an exponent other than 0,
 or has an absolute value that exceeds the maximum precision
 specified in the context.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> or <code>ed2</code> is
 null.

### <a id='Rotate(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>Rotate</a>

Rotates the bits of an arbitrary-precision binary number's significand.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number containing the significand to
 rotate. If this significand contains more bits than the precision,
 the most-significant bits are chopped off the significand.

* <code>ed2</code> - An arbitrary-precision number indicating the number of bits to
 rotate the first operand's significand. Must be an integer with an
 exponent of 0. If this parameter is positive, the significand is
 shifted to the left by the given number of bits and the
 most-significant bits shifted out of the significand become the
 least-significant bits instead. If this parameter is negative, the
 number is shifted by the given number of bits and the
 least-significant bits shifted out of the significand become the
 most-significant bits instead.

* <code>ec</code> - An arithmetic context to control the precision of
 arbitrary-precision numbers. If this parameter is null or specifies
 an unlimited precision, this method has the same behavior as <code>
 Shift</code>.

**Returns:**

* An arbitrary-precision binary number whose significand is rotated
 the given number of bits. Signals an invalid operation and returns
 NaN (not-a-number) if <code>ed2</code> is a signaling NaN or if <code>
 ed2</code> is not an integer, is negative, has an exponent other than 0,
 or has an absolute value that exceeds the maximum precision
 specified in the context.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed2</code> or <code>ed</code> is
 null.

### <a id='CompareTotal(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>CompareTotal</a>

Compares the values of one arbitrary-precision number object and another
 object, imposing a total ordering on all possible values. In this
 method: <ul> <li>For objects with the same value, the one with the
  higher exponent has a greater "absolute value".</li> <li>Negative
 zero is less than positive zero.</li> <li>Quiet NaN has a higher
  "absolute value" than signaling NaN. If both objects are quiet NaN
 or both are signaling NaN, the one with the higher diagnostic
  information has a greater "absolute value".</li> <li>NaN has a
  higher "absolute value" than infinity.</li> <li>Infinity has a
  higher "absolute value" than any finite number.</li> <li>Negative
 numbers are less than positive numbers.</li></ul>

**Parameters:**

* <code>ed</code> - The first arbitrary-precision number to compare.

* <code>other</code> - The second arbitrary-precision number to compare.

* <code>ec</code> - An arithmetic context. Flags will be set in this context only if
 <code>HasFlags</code> and <code>IsSimplified</code> of the context are true
 and only if an operand needed to be rounded before carrying out the
 operation. Can be null.

**Returns:**

* The number 0 if both objects are null or equal, or -1 if the first
 object is null or less than the other object, or 1 if the first
 object is greater or the other object is null. Does not signal flags
 if either value is signaling NaN.

### <a id='CompareTotalMagnitude(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>CompareTotalMagnitude</a>

Compares the absolute values of two arbitrary-precision number objects,
 imposing a total ordering on all possible values (ignoring their
 signs). In this method: <ul> <li>For objects with the same value,
  the one with the higher exponent has a greater "absolute
  value".</li> <li>Negative zero and positive zero are considered
  equal.</li> <li>Quiet NaN has a higher "absolute value" than
 signaling NaN. If both objects are quiet NaN or both are signaling
 NaN, the one with the higher diagnostic information has a greater
  "absolute value".</li> <li>NaN has a higher "absolute value" than
  infinity.</li> <li>Infinity has a higher "absolute value" than any
 finite number.</li></ul>

**Parameters:**

* <code>ed</code> - The first arbitrary-precision number to compare.

* <code>other</code> - The second arbitrary-precision number to compare.

* <code>ec</code> - An arithmetic context. Flags will be set in this context only if
 <code>HasFlags</code> and <code>IsSimplified</code> of the context are true
 and only if an operand needed to be rounded before carrying out the
 operation. Can be null.

**Returns:**

* The number 0 if both objects are null or equal (ignoring their
 signs), or -1 if the first object is null or less than the other
 object (ignoring their signs), or 1 if the first object is greater
 (ignoring their signs) or the other object is null. Does not signal
 flags if either value is signaling NaN.

### <a id='Copy(com.upokecenter.numbers.EFloat)'>Copy</a>

Creates a copy of the given arbitrary-precision number object.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object to copy.

**Returns:**

* A copy of the given arbitrary-precision number object.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> is null.

### <a id='Canonical(com.upokecenter.numbers.EFloat)'>Canonical</a>

Returns a canonical version of the given arbitrary-precision number object.
 In this method, this method behaves like the Copy method.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* A copy of the parameter <code>ed</code>.

### <a id='CopyAbs(com.upokecenter.numbers.EFloat)'>CopyAbs</a>

Returns an arbitrary-precision number object with the same value as the
 given number object but with a nonnegative sign (that is, the given
 number object's absolute value).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* An arbitrary-precision number object with the same value as the
 given number object but with a nonnegative sign.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> is null.

### <a id='CopyNegate(com.upokecenter.numbers.EFloat)'>CopyNegate</a>

Returns an arbitrary-precision number object with the sign reversed from the
 given number object.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* An arbitrary-precision number object with the sign reversed from the
 given number object.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> is null.

### <a id='CopySign(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EFloat)'>CopySign</a>

Returns an arbitrary-precision number object with the same value as the
 first given number object but with a the same sign (positive or
 negative) as the second given number object.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object with the value the result
 will have.

* <code>other</code> - The parameter <code>other</code> is an arbitrary-precision binary
 floating-point number.

**Returns:**

* An arbitrary-precision number object with the same value as the
 first given number object but with a the same sign (positive or
 negative) as the second given number object.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> or <code>other</code> is
 null.

### <a id='SameQuantum(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EFloat)'>SameQuantum</a>

Returns whether two arbitrary-precision numbers have the same exponent, they
 both are not-a-number (NaN), or they both are infinity (positive
 and/or negative).

**Parameters:**

* <code>ed1</code> - The first arbitrary-precision number.

* <code>ed2</code> - The second arbitrary-precision number.

**Returns:**

* Either <code>true</code> if the given arbitrary-precision numbers have
 the same exponent, they both are not-a-number (NaN), or they both
 are infinity (positive and/or negative); otherwise, <code>false</code>.

### <a id='Trim(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>Trim</a>

Returns an arbitrary-precision number with the same value as this one but
 with certain trailing zeros removed from its significand. If the
 number's exponent is 0, it is returned unchanged (but may be rounded
 depending on the arithmetic context); if that exponent is greater 0,
 its trailing zeros are removed from the significand (then rounded if
 necessary); if that exponent is less than 0, its trailing zeros are
 removed from the significand until the exponent reaches 0 (then the
 number is rounded if necessary).

**Parameters:**

* <code>ed1</code> - An arbitrary-precision number.

* <code>ec</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. Can be null.

**Returns:**

* An arbitrary-precision number with the same value as this one but
 with certain trailing zeros removed from its significand. If <code>
 ed1</code> is not-a-number (NaN) or infinity, it is generally returned
 unchanged.

### <a id='Rescale(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>Rescale</a>

Returns an arbitrary-precision binary number with the same value as this
 object but with the given exponent, expressed as an
 arbitrary-precision binary number. <p>Note that this is not always
 the same as rounding to a given number of binary places, since it
 can fail if the difference between this value's exponent and the
 desired exponent is too big, depending on the maximum precision. If
 rounding to a number of binary places is desired, it's better to use
 the RoundToExponent and RoundToIntegral methods instead.</p>
 <p><b>Remark:</b> This method can be used to implement fixed-point
 binary arithmetic, in which a fixed number of digits come after the
 binary point. A fixed-point binary arithmetic in which no digits
 come after the binary point (a desired exponent of 0) is considered
  an "integer arithmetic" .</p>

**Parameters:**

* <code>ed</code> - An arbitrary-precision binary number whose exponent is to be
 changed.

* <code>scale</code> - The desired exponent of the result, expressed as an
 arbitrary-precision binary number. The exponent is the number of
 fractional digits in the result, expressed as a negative number. Can
 also be positive, which eliminates lower-order places from the
 number. For example, -3 means round to the sixteenth (10b^-3,
 0.0001b), and 3 means round to the sixteens-place (10b^3, 1000b). A
 value of 0 rounds the number to an integer.

* <code>ec</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags). Can be null, in which case the default
 rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision binary number with the same value as this
 object but with the exponent changed. Signals FlagInvalid and
 returns not-a-number (NaN) if the result can't fit the given
 precision without rounding, or if the arithmetic context defines an
 exponent range and the given exponent is outside that range.

### <a id='And(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>And</a>

Performs a logical AND operation on two binary numbers in the form of
 <i>logical operands</i>. A <code>logical operand</code> is a non-negative
 base-2 number with an Exponent property of 0 (examples include the
 base-2 numbers <code>01001</code> and <code>111001</code>). The logical AND
 operation sets each bit of the result to 1 if the corresponding bits
 of each logical operand are both 1, and to 0 otherwise. For example,
 <code>01001 AND 111010 = 01000</code>.

**Parameters:**

* <code>ed1</code> - The first logical operand to the logical AND operation.

* <code>ed2</code> - The second logical operand to the logical AND operation.

* <code>ec</code> - An arithmetic context to control the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more bits than the maximum precision specified in this
 context, the operand's most significant bits that exceed that
 precision are discarded. This parameter can be null.

**Returns:**

* The result of the logical AND operation as a logical operand.
 Signals an invalid operation and returns not-a-number (NaN) if
 <code>ed1</code>, <code>ed2</code>, or both are not logical operands.

### <a id='Invert(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>Invert</a>

Performs a logical NOT operation on a binary number in the form of a
 <i>logical operand</i>. A <code>logical operand</code> is a non-negative
 base-2 number with an Exponent property of 0 (examples include
 <code>01001</code> and <code>111001</code>). The logical NOT operation sets
 each bit of the result to 1 if the corresponding bit is 0, and to 0
 otherwise; it can set no more bits than the maximum precision,
 however. For example, if the maximum precision is 8 bits, then
 <code>NOT 111010 = 11000101</code>.

**Parameters:**

* <code>ed1</code> - The operand to the logical NOT operation.

* <code>ec</code> - An arithmetic context to control the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more bits than the maximum precision specified in this
 context, the operand's most significant bits that exceed that
 precision are discarded. This parameter cannot be null and must
 specify a maximum precision (unlimited precision contexts are not
 allowed).

**Returns:**

* The result of the logical NOT operation as a logical operand.
 Signals an invalid operation and returns not-a-number (NaN) if
 <code>ed1</code> is not a logical operand.

### <a id='Xor(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>Xor</a>

Performs a logical exclusive-OR (XOR) operation on two binary numbers in the
 form of <i>logical operands</i>. A <code>logical operand</code> is a
 non-negative base-2 number with an Exponent property of 0 (examples
 include the base-2 numbers <code>01001</code> and <code>111001</code>). The
 logical exclusive-OR operation sets each digit of the result to 1 if
 either corresponding digit of the logical operands, but not both, is
 1, and to 0 otherwise. For example, <code>01001 XOR 111010 =
 101010</code>.

**Parameters:**

* <code>ed1</code> - The first logical operand to the logical exclusive-OR operation.

* <code>ed2</code> - The second logical operand to the logical exclusive-OR operation.

* <code>ec</code> - An arithmetic context to control the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more bits than the maximum precision specified in this
 context, the operand's most significant bits that exceed that
 precision are discarded. This parameter can be null.

**Returns:**

* The result of the logical exclusive-OR operation as a logical
 operand. Signals an invalid operation and returns not-a-number (NaN)
 if <code>ed1</code>, <code>ed2</code>, or both are not logical operands.

### <a id='Or(com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EFloat,com.upokecenter.numbers.EContext)'>Or</a>

Performs a logical OR operation on two binary numbers in the form of
 <i>logical operands</i>. A <code>logical operand</code> is a non-negative
 base-2 number with an Exponent property of 0 (examples include the
 base-2 numbers <code>01001</code> and <code>111001</code>). The logical OR
 operation sets each bit of the result to 1 if either or both of the
 corresponding bits of each logical operand are 1, and to 0
 otherwise. For example, <code>01001 OR 111010 = 111011</code>.

**Parameters:**

* <code>ed1</code> - The first logical operand to the logical OR operation.

* <code>ed2</code> - The second logical operand to the logical OR operation.

* <code>ec</code> - An arithmetic context to control the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more bits than the maximum precision specified in this
 context, the operand's most significant bits that exceed that
 precision are discarded. This parameter can be null.

**Returns:**

* The result of the logical OR operation as a logical operand. Signals
 an invalid operation and returns not-a-number (NaN) if <code>ed1</code>,
 <code>ed2</code>, or both are not logical operands.
