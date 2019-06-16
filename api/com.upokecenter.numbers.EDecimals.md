# com.upokecenter.numbers.EDecimals

    public final class EDecimals extends java.lang.Object

A class that implements additional operations on arbitrary-precision decimal
 numbers. Many of them are listed as miscellaneous operations in the
 General Decimal Arithmetic Specification version 1.70.

## Methods

* `static EDecimal And​(EDecimal ed1,
   EDecimal ed2,
   EContext ec)`<br>
 Performs a logical AND operation on two decimal numbers in the form of
 logical operands  .
* `static EDecimal BooleanToEDecimal​(boolean b,
                 EContext ec)`<br>
 Converts a boolean value (either true or false) to an arbitrary-precision
 decimal number.
* `static EDecimal Canonical​(EDecimal ed)`<br>
 Returns a canonical version of the given arbitrary-precision number object.
* `static int CompareTotal​(EDecimal ed,
            EDecimal other,
            EContext ec)`<br>
 Compares the values of one arbitrary-precision number object and another
 object, imposing a total ordering on all possible values.
* `static int CompareTotalMagnitude​(EDecimal ed,
                     EDecimal other,
                     EContext ec)`<br>
 Compares the absolute values of two arbitrary-precision number objects,
 imposing a total ordering on all possible values (ignoring their
 signs).
* `static EDecimal Copy​(EDecimal ed)`<br>
 Creates a copy of the given arbitrary-precision number object.
* `static EDecimal CopyAbs​(EDecimal ed)`<br>
 Returns an arbitrary-precision number object with the same value as the
 given number object but with a nonnegative sign (that is, the given
 number object's absolute value).
* `static EDecimal CopyNegate​(EDecimal ed)`<br>
 Returns an arbitrary-precision number object with the sign reversed from the
 given number object.
* `static EDecimal CopySign​(EDecimal ed,
        EDecimal other)`<br>
 Returns an arbitrary-precision number object with the same value as the
 first given number object but with a the same sign (positive or
 negative) as the second given number object.
* `static EDecimal Int32ToEDecimal​(int i32,
               EContext ec)`<br>
 Creates an arbitrary-precision decimal number from a 32-bit signed integer.
* `static EDecimal Invert​(EDecimal ed1,
      EContext ec)`<br>
 Performs a logical NOT operation on an arbitrary-precision decimal number in
 the form of a  logical operand  .
* `static boolean IsCanonical​(EDecimal ed)`<br>
 Returns whether the given arbitrary-precision number object is in a
 canonical form.
* `static boolean IsFinite​(EDecimal ed)`<br>
 Returns whether the given arbitrary-precision number object is neither null
 nor infinity nor not-a-number (NaN).
* `static boolean IsInfinite​(EDecimal ed)`<br>
 Returns whether the given arbitrary-precision number object is positive or
 negative infinity.
* `static boolean IsNaN​(EDecimal ed)`<br>
 Returns whether the given arbitrary-precision number object is a
 not-a-number (NaN).
* `static boolean IsNormal​(EDecimal ed,
        EContext ec)`<br>
 Returns whether the given number is a  normal  number.
* `static boolean IsQuietNaN​(EDecimal ed)`<br>
 Returns whether the given arbitrary-precision number object is a quiet
 not-a-number (NaN).
* `static boolean IsSignalingNaN​(EDecimal ed)`<br>
 Returns whether the given arbitrary-precision number object is a signaling
 not-a-number (NaN).
* `static boolean IsSigned​(EDecimal ed)`<br>
 Returns whether the given arbitrary-precision number object is negative
 (including negative infinity, negative not-a-number [NaN], or
 negative zero).
* `static boolean IsSubnormal​(EDecimal ed,
           EContext ec)`<br>
 Returns whether the given number is a  subnormal  number.
* `static boolean IsZero​(EDecimal ed)`<br>
 Returns whether the given arbitrary-precision number object is zero
 (positive zero or negative zero).
* `static EDecimal LogB​(EDecimal ed,
    EContext ec)`<br>
 Returns the base-10 exponent of an arbitrary-precision decimal number (when
 that number is expressed in scientific notation with one digit before
 the radix point).
* `static int NumberClass​(EDecimal ed,
           EContext ec)`<br>
 Finds the number class for an arbitrary-precision decimal number object.
* `static java.lang.String NumberClassString​(int nc)`<br>
 Converts a number class identifier (ranging from 1 to 9) to a text string.
* `static EDecimal Or​(EDecimal ed1,
  EDecimal ed2,
  EContext ec)`<br>
 Performs a logical OR operation on two decimal numbers in the form of
 logical operands  .
* `static EDecimal Radix​(EContext ec)`<br>
 Returns the number 10, the decimal radix.
* `static EDecimal Rescale​(EDecimal ed,
       EDecimal scale,
       EContext ec)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but with the given exponent, expressed as an
 arbitrary-precision decimal number.
* `static EDecimal Rotate​(EDecimal ed,
      EDecimal ed2,
      EContext ec)`<br>
 Rotates the digits of an arbitrary-precision decimal number's mantissa.
* `static boolean SameQuantum​(EDecimal ed1,
           EDecimal ed2)`<br>
 Returns whether two arbitrary-precision numbers have the same exponent, they
 both are not-a-number (NaN), or they both are infinity (positive
 and/or negative).
* `static EDecimal ScaleB​(EDecimal ed,
      EDecimal ed2,
      EContext ec)`<br>
 Finds an arbitrary-precision decimal number whose decimal point is moved a
 given number of places.
* `static EDecimal Shift​(EDecimal ed,
     EDecimal ed2,
     EContext ec)`<br>
 Shifts the digits of an arbitrary-precision decimal number's mantissa.
* `static EDecimal Trim​(EDecimal ed1,
    EContext ec)`<br>
 Returns an arbitrary-precision number with the same value as this one but
 with certain trailing zeros removed from its mantissa.
* `static EDecimal Xor​(EDecimal ed1,
   EDecimal ed2,
   EContext ec)`<br>
 Performs a logical exclusive-OR (XOR) operation on two decimal numbers in
 the form of  logical operands  .

## Method Details

### Radix
    public static EDecimal Radix​(EContext ec)
Returns the number 10, the decimal radix.

**Parameters:**

* <code>ec</code> - Specifies an arithmetic context for rounding the number 10. Can be
 null.

**Returns:**

* The number 10, or the closest representable number to 10 in the
 arithmetic context.

### Int32ToEDecimal
    public static EDecimal Int32ToEDecimal​(int i32, EContext ec)
Creates an arbitrary-precision decimal number from a 32-bit signed integer.

**Parameters:**

* <code>i32</code> - The parameter <code>i32</code> is a 32-bit signed integer.

* <code>ec</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. Can be null.

**Returns:**

* An arbitrary-precision decimal number with the closest representable
 value to the given integer.

### BooleanToEDecimal
    public static EDecimal BooleanToEDecimal​(boolean b, EContext ec)
Converts a boolean value (either true or false) to an arbitrary-precision
 decimal number.

**Parameters:**

* <code>b</code> - Either true or false.

* <code>ec</code> - A context used for rounding the result. Can be null.

**Returns:**

* Either 1 if <code>b</code> is true, or 0 if <code>b</code> is false.. The
 result will be rounded as specified by the given context, if any.

### IsCanonical
    public static boolean IsCanonical​(EDecimal ed)
Returns whether the given arbitrary-precision number object is in a
 canonical form. For the current version of EDecimal, all EDecimal
 objects are in a canonical form.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Always <code>true</code> .

### IsFinite
    public static boolean IsFinite​(EDecimal ed)
Returns whether the given arbitrary-precision number object is neither null
 nor infinity nor not-a-number (NaN).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> if the given arbitrary-precision number object
 is neither null nor infinity nor not-a-number (NaN), or <code>false</code>
 otherwise.

### IsInfinite
    public static boolean IsInfinite​(EDecimal ed)
Returns whether the given arbitrary-precision number object is positive or
 negative infinity.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> if the given arbitrary-precision number object
 is positive or negative infinity, or <code>false</code> otherwise.

### IsNaN
    public static boolean IsNaN​(EDecimal ed)
Returns whether the given arbitrary-precision number object is a
 not-a-number (NaN).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### IsNormal
    public static boolean IsNormal​(EDecimal ed, EContext ec)
Returns whether the given number is a <i> normal </i> number. A <i>
 subnormal number </i> is a nonzero finite number whose Exponent
 property (or the number's exponent when that number is expressed in
 scientific notation with one digit before the radix point) is less
 than the minimum possible exponent for that number. A <i> normal
 number </i> is nonzero and finite, but not subnormal.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

* <code>ec</code> - A context specifying the exponent range of arbitrary-precision
 numbers. Can be null. If AdjustExponent of the given context is
 <code>true</code> , a nonzero number is normal if the number's exponent
 (when that number is expressed in scientific notation with one
 nonzero digit before the radix point) is at least the given context's
 EMax property (e.g., if EMax is -100, 2.3456 * 10 <sup> -99 </sup> is
 normal, but 2.3456 * 10 <sup> -102 </sup> is not). If AdjustExponent
 of the given context is <code>false</code> , a nonzero number is subnormal
 if the number's Exponent property is at least given context's EMax
 property (e.g., if EMax is -100, 23456 * 10 <sup> -99 </sup> is
 normal, but 23456 * 10 <sup> -102 </sup> is not).

**Returns:**

* Either <code>true</code> if the given number is subnormal, or <code>
 false</code> otherwise. Returns <code>true</code> if the given context is null
 or HasExponentRange of the given context is <code>false</code> .

### IsQuietNaN
    public static boolean IsQuietNaN​(EDecimal ed)
Returns whether the given arbitrary-precision number object is a quiet
 not-a-number (NaN).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### IsSigned
    public static boolean IsSigned​(EDecimal ed)
Returns whether the given arbitrary-precision number object is negative
 (including negative infinity, negative not-a-number [NaN], or
 negative zero).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### IsSignalingNaN
    public static boolean IsSignalingNaN​(EDecimal ed)
Returns whether the given arbitrary-precision number object is a signaling
 not-a-number (NaN).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### NumberClassString
    public static java.lang.String NumberClassString​(int nc)
Converts a number class identifier (ranging from 1 to 9) to a text string.
 An arbitrary-precision number object can belong in one of ten number
 classes.

**Parameters:**

* <code>nc</code> - An integer identifying a number class.

**Returns:**

* A text string identifying the given number class as follows: 0 =
 "+Normal"; 1 = "-Normal", 2 = "+Subnormal", 3 = "-Subnormal", 4 =
 "+Zero", 5 = "-Zero", 6 = "+Infinity", 7 = "-Infinity", 8 = "NaN", 9
 = "sNaN".

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>nc</code> is less than 0 or
 greater than 9 .

### NumberClass
    public static int NumberClass​(EDecimal ed, EContext ec)
Finds the number class for an arbitrary-precision decimal number object.

**Parameters:**

* <code>ed</code> - An arbitrary-precision decimal number object.

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

### IsSubnormal
    public static boolean IsSubnormal​(EDecimal ed, EContext ec)
Returns whether the given number is a <i> subnormal </i> number. A <i>
 subnormal number </i> is a nonzero finite number whose Exponent
 property (or the number's exponent when that number is expressed in
 scientific notation with one digit before the radix point) is less
 than the minimum possible exponent for that number.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

* <code>ec</code> - A context specifying the exponent range of arbitrary-precision
 numbers. Can be null. If AdjustExponent of the given context is
 <code>true</code> , a nonzero number is subnormal if the number's exponent
 (when that number is expressed in scientific notation with one
 nonzero digit before the radix point) is less than the given
 context's EMax property (e.g., if EMax is -100, 2.3456 * 10 <sup>
 -102 </sup> is subnormal, but 2.3456 * 10 <sup> -99 </sup> is not).
 If AdjustExponent of the given context is <code>false</code> , a nonzero
 number is subnormal if the number's Exponent property is less than
 the given context's EMax property (e.g., if EMax is -100, 23456 * 10
 <sup> -102 </sup> is subnormal, but 23456 * 10 <sup> -99 </sup> is
 not).

**Returns:**

* Either <code>true</code> if the given number is subnormal, or <code>
 false</code> otherwise. Returns <code>false</code> if the given context is null
 or HasExponentRange of the given context is <code>false</code> .

### IsZero
    public static boolean IsZero​(EDecimal ed)
Returns whether the given arbitrary-precision number object is zero
 (positive zero or negative zero).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* <code>true</code> if the given number has a value of zero (positive zero
 or negative zero); otherwise, <code>false</code> .

### LogB
    public static EDecimal LogB​(EDecimal ed, EContext ec)
Returns the base-10 exponent of an arbitrary-precision decimal number (when
 that number is expressed in scientific notation with one digit before
 the radix point). For example, returns 3 for the numbers <code>6.66E +
 3</code> and <code>666E + 1</code>

**Parameters:**

* <code>ed</code> - An arbitrary-precision decimal number.

* <code>ec</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. Can be null.

**Returns:**

* The base-10 exponent of the given number (when that number is
 expressed in scientific notation with one nonzero digit before the
 radix point). Signals DivideByZero and returns negative infinity if
 <code>ed</code> is zero. Returns positive infinity if <code>ed</code> is
 positive infinity or negative infinity.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> is null.

### ScaleB
    public static EDecimal ScaleB​(EDecimal ed, EDecimal ed2, EContext ec)
Finds an arbitrary-precision decimal number whose decimal point is moved a
 given number of places.

**Parameters:**

* <code>ed</code> - An arbitrary-precision decimal number.

* <code>ed2</code> - The number of decimal places to move the decimal point of "ed".
 This must be an integer with an exponent of 0.

* <code>ec</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. Can be null.

**Returns:**

* The given arbitrary-precision decimal number whose decimal point is
 moved the given number of places. Signals an invalid operation and
 returns not-a-number (NaN) if <code>ed2</code> is infinity or NaN, has an
 Exponent property other than 0. Signals an invalid operation and
 returns not-a-number (NaN) if <code>ec</code> defines a limited precision
 and exponent range and if <code>ed2</code> 's absolute value is greater
 than twice the sum of the context's EMax property and its Precision
 property.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> or <code>ed2</code>
 is null.

### Shift
    public static EDecimal Shift​(EDecimal ed, EDecimal ed2, EContext ec)
Shifts the digits of an arbitrary-precision decimal number's mantissa.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number containing the mantissa to shift.

* <code>ed2</code> - An arbitrary-precision number indicating the number of digits to
 shift the first operand's mantissa. Must be an integer with an
 exponent of 0. If this parameter is positive, the mantissa is shifted
 to the left by the given number of digits . If this parameter is
 negative, the mantissa is shifted to the right by the given number of
 digits.

* <code>ec</code> - An arithmetic context to control the precision of
 arbitrary-precision numbers. Can be null.

**Returns:**

* An arbitrary-precision decimal number whose mantissa is shifted the
 given number of digits. Signals an invalid operation and returns NaN
 (not-a-number) if <code>ed2</code> is a signaling NaN or if <code>ed2</code> is
 not an integer, is negative, has an exponent other than 0, or has an
 absolute value that exceeds the maximum precision specified in the
 context.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> or <code>ed2</code>
 is null.

### Rotate
    public static EDecimal Rotate​(EDecimal ed, EDecimal ed2, EContext ec)
Rotates the digits of an arbitrary-precision decimal number's mantissa.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number containing the mantissa to rotate.
 If this mantissa contains more digits than the precision, the
 most-significant digits are chopped off the mantissa before the
 rotation begins.

* <code>ed2</code> - An arbitrary-precision number indicating the number of digits to
 rotate the first operand's mantissa. Must be an integer with an
 exponent of 0. If this parameter is positive, the mantissa is shifted
 to the left by the given number of digits and the most-significant
 digits shifted out of the mantissa become the least-significant
 digits instead. If this parameter is negative, the mantissa is
 shifted to the right by the given number of digits and the
 least-significant digits shifted out of the mantissa become the
 most-significant digits instead.

* <code>ec</code> - An arithmetic context to control the precision of
 arbitrary-precision numbers. If this parameter is null or specifies
 an unlimited precision, this method has the same behavior as <code>
 Shift</code> .

**Returns:**

* An arbitrary-precision decimal number whose mantissa is rotated the
 given number of digits. Signals an invalid operation and returns NaN
 (not-a-number) if <code>ed2</code> is a signaling NaN or if <code>ed2</code> is
 not an integer, is negative, has an exponent other than 0, or has an
 absolute value that exceeds the maximum precision specified in the
 context.

### CompareTotal
    public static int CompareTotal​(EDecimal ed, EDecimal other, EContext ec)
Compares the values of one arbitrary-precision number object and another
 object, imposing a total ordering on all possible values. In this
 method: <ul> <li>For objects with the same value, the one with the
 higher exponent has a greater "absolute value". </li> <li>Negative
 zero is less than positive zero. </li> <li>Quiet NaN has a higher
 "absolute value" than signaling NaN. If both objects are quiet NaN or
 both are signaling NaN, the one with the higher diagnostic
 information has a greater "absolute value". </li> <li>NaN has a
 higher "absolute value" than infinity. </li> <li>Infinity has a
 higher "absolute value" than any finite number. </li> <li>Negative
 numbers are less than positive numbers. </li> </ul>

**Parameters:**

* <code>ed</code> - The first arbitrary-precision number to compare.

* <code>other</code> - The second arbitrary-precision number to compare.

* <code>ec</code> - An arithmetic context. Flags will be set in this context only if
 <code>HasFlags</code> and <code>IsSimplified</code> of the context are true and
 only if an operand needed to be rounded before carrying out the
 operation. Can be null.

**Returns:**

* The number 0 if both objects have the same value, or -1 if the first
 object is less than the other value, or 1 if the first object is
 greater. Does not signal flags if either value is signaling NaN.

### CompareTotalMagnitude
    public static int CompareTotalMagnitude​(EDecimal ed, EDecimal other, EContext ec)
Compares the absolute values of two arbitrary-precision number objects,
 imposing a total ordering on all possible values (ignoring their
 signs). In this method: <ul> <li>For objects with the same value, the
 one with the higher exponent has a greater "absolute value". </li>
 <li>Negative zero and positive zero are considered equal. </li>
 <li>Quiet NaN has a higher "absolute value" than signaling NaN. If
 both objects are quiet NaN or both are signaling NaN, the one with
 the higher diagnostic information has a greater "absolute value".
 </li> <li>NaN has a higher "absolute value" than infinity. </li>
 <li>Infinity has a higher "absolute value" than any finite number.
 </li> </ul>

**Parameters:**

* <code>ed</code> - The first arbitrary-precision number to compare.

* <code>other</code> - The second arbitrary-precision number to compare.

* <code>ec</code> - An arithmetic context. Flags will be set in this context only if
 <code>HasFlags</code> and <code>IsSimplified</code> of the context are true and
 only if an operand needed to be rounded before carrying out the
 operation. Can be null.

**Returns:**

* The number 0 if both objects have the same value (ignoring their
 signs), or -1 if the first object is less than the other value
 (ignoring their signs), or 1 if the first object is greater (ignoring
 their signs). Does not signal flags if either value is signaling NaN.

### Copy
    public static EDecimal Copy​(EDecimal ed)
Creates a copy of the given arbitrary-precision number object.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object to copy.

**Returns:**

* A copy of the given arbitrary-precision number object.

### Canonical
    public static EDecimal Canonical​(EDecimal ed)
Returns a canonical version of the given arbitrary-precision number object.
 In this method, this method behaves like the Copy method.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* A copy of the parameter <code>ed</code>.

### CopyAbs
    public static EDecimal CopyAbs​(EDecimal ed)
Returns an arbitrary-precision number object with the same value as the
 given number object but with a nonnegative sign (that is, the given
 number object's absolute value).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* An arbitrary-precision number object with the same value as the
 given number object but with a nonnegative sign.

### CopyNegate
    public static EDecimal CopyNegate​(EDecimal ed)
Returns an arbitrary-precision number object with the sign reversed from the
 given number object.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* An arbitrary-precision number object with the sign reversed from the
 given number object.

### CopySign
    public static EDecimal CopySign​(EDecimal ed, EDecimal other)
Returns an arbitrary-precision number object with the same value as the
 first given number object but with a the same sign (positive or
 negative) as the second given number object.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object with the value the result
 will have.

* <code>other</code> - The parameter <code>other</code> is an EDecimal object.

**Returns:**

* An arbitrary-precision number object with the same value as the
 first given number object but with a the same sign (positive or
 negative) as the second given number object.

### SameQuantum
    public static boolean SameQuantum​(EDecimal ed1, EDecimal ed2)
Returns whether two arbitrary-precision numbers have the same exponent, they
 both are not-a-number (NaN), or they both are infinity (positive
 and/or negative).

**Parameters:**

* <code>ed1</code> - The first arbitrary-precision number.

* <code>ed2</code> - The second arbitrary-precision number.

**Returns:**

* Either <code>true</code> if the given arbitrary-precision numbers have
 the same exponent, they both are not-a-number (NaN), or they both are
 infinity (positive and/or negative); otherwise, <code>false</code> .

### Trim
    public static EDecimal Trim​(EDecimal ed1, EContext ec)
Returns an arbitrary-precision number with the same value as this one but
 with certain trailing zeros removed from its mantissa. If the
 number's exponent is 0, it is returned unchanged (but may be rounded
 depending on the arithmetic context); if that exponent is greater 0,
 its trailing zeros are removed from the mantissa (then rounded if
 necessary); if that exponent is less than 0, its trailing zeros are
 removed from the mantissa until the exponent reaches 0 (then the
 number is rounded if necessary).

**Parameters:**

* <code>ed1</code> - An arbitrary-precision number.

* <code>ec</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. Can be null.

**Returns:**

* An arbitrary-precision number with the same value as this one but
 with certain trailing zeros removed from its mantissa. If <code>ed1</code>
 is not-a-number (NaN) or infinity, it is generally returned
 unchanged.

### Rescale
    public static EDecimal Rescale​(EDecimal ed, EDecimal scale, EContext ec)
Returns an arbitrary-precision decimal number with the same value as this
 object but with the given exponent, expressed as an
 arbitrary-precision decimal number. <p>Note that this is not always
 the same as rounding to a given number of decimal places, since it
 can fail if the difference between this value's exponent and the
 desired exponent is too big, depending on the maximum precision. If
 rounding to a number of decimal places is desired, it's better to use
 the RoundToExponent and RoundToIntegral methods instead. </p>
 <p><b>Remark:</b> This method can be used to implement fixed-point
 decimal arithmetic, in which a fixed number of digits come after the
 decimal point. A fixed-point decimal arithmetic in which no digits
 come after the decimal point (a desired exponent of 0) is considered
 an "integer arithmetic" . </p>

**Parameters:**

* <code>ed</code> - An arbitrary-precision decimal number whose exponent is to be
 changed.

* <code>scale</code> - The desired exponent of the result, expressed as an
 arbitrary-precision decimal number. The exponent is the number of
 fractional digits in the result, expressed as a negative number. Can
 also be positive, which eliminates lower-order places from the
 number. For example, -3 means round to the thousandth (10^-3,
 0.0001), and 3 means round to the thousands-place (10^3, 1000). A
 value of 0 rounds the number to an integer.

* <code>ec</code> - The parameter <code>ec</code> is an EContext object.

**Returns:**

* An arbitrary-precision decimal number with the same value as this
 object but with the exponent changed. Signals FlagInvalid and returns
 not-a-number (NaN) if the result can't fit the given precision
 without rounding, or if the arithmetic context defines an exponent
 range and the given exponent is outside that range.

### And
    public static EDecimal And​(EDecimal ed1, EDecimal ed2, EContext ec)
Performs a logical AND operation on two decimal numbers in the form of <i>
 logical operands </i> . A <code>logical operand</code> is a non-negative
 base-10 number with an Exponent property of 0 and no other base-10
 digits than 0 or 1 (examples include <code>01001</code> and <code>111001</code> ,
 but not <code>02001</code> or <code>99999</code>). The logical AND operation
 sets each digit of the result to 1 if the corresponding digits of
 each logical operand are both 1, and to 0 otherwise. For example,
 <code>01001 AND 111010 = 01000</code>

**Parameters:**

* <code>ed1</code> - The first logical operand to the logical AND operation.

* <code>ed2</code> - The second logical operand to the logical AND operation.

* <code>ec</code> - An arithmetic context to control the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more digits than the maximum precision specified in this
 context, the operand's most significant digits that exceed that
 precision are discarded. This parameter can be null.

**Returns:**

* The result of the logical AND operation as a logical operand.
 Signals an invalid operation and returns not-a-number (NaN) if <code>
 ed1</code> , <code>ed2</code> , or both are not logical operands.

### Invert
    public static EDecimal Invert​(EDecimal ed1, EContext ec)
Performs a logical NOT operation on an arbitrary-precision decimal number in
 the form of a <i> logical operand </i> . A <code>logical operand</code> is
 a non-negative base-10 number with an Exponent property of 0 and no
 other base-10 digits than 0 or 1 (examples include <code>01001</code> and
 <code>111001</code> , but not <code>02001</code> or <code>99999</code>). The logical
 NOT operation sets each digit of the result to 1 if the corresponding
 digit is 0, and to 0 otherwise; it can set no more digits than the
 maximum precision, however. For example, if the maximum precision is
 8 digits, then <code>NOT 111010 = 11000101</code>

**Parameters:**

* <code>ed1</code> - The logical operand to the logical NOT operation.

* <code>ec</code> - An arithmetic context to control the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more digits than the maximum precision specified in this
 context, the operand's most significant digits that exceed that
 precision are discarded. This parameter cannot be null and must
 specify a maximum precision (unlimited precision contexts are not
 allowed).

**Returns:**

* The result of the logical NOT operation as a logical operand.
 Signals an invalid operation and returns not-a-number (NaN) if <code>
 ed1</code> is not a logical operand.

### Xor
    public static EDecimal Xor​(EDecimal ed1, EDecimal ed2, EContext ec)
Performs a logical exclusive-OR (XOR) operation on two decimal numbers in
 the form of <i> logical operands </i> . A <code>logical operand</code> is a
 non-negative base-10 number with an exponent of 0 and no other
 base-10 digits than 0 or 1 (examples include <code>01001</code> and
 <code>111001</code> , but not <code>02001</code> or <code>99999</code>). The logical
 exclusive-OR operation sets each digit of the result to 1 if either
 corresponding digit of the logical operands, but not both, is 1, and
 to 0 otherwise. For example, <code>01001 XOR 111010 = 101010</code>

**Parameters:**

* <code>ed1</code> - The first logical operand to the logical exclusive-OR operation.

* <code>ed2</code> - The second logical operand to the logical exclusive-OR operation.

* <code>ec</code> - An arithmetic context to control the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more digits than the maximum precision specified in this
 context, the operand's most significant digits that exceed that
 precision are discarded. This parameter can be null.

**Returns:**

* An EDecimal object.

### Or
    public static EDecimal Or​(EDecimal ed1, EDecimal ed2, EContext ec)
Performs a logical OR operation on two decimal numbers in the form of <i>
 logical operands </i> . A <code>logical operand</code> is a non-negative
 base-10 number with an Exponent property of 0 and no other base-10
 digits than 0 or 1 (examples include <code>01001</code> and <code>111001</code> ,
 but not <code>02001</code> or <code>99999</code>). The logical OR operation sets
 each digit of the result to 1 if either or both of the corresponding
 digits of the logical operands are 1, and to 0 otherwise. For
 example, <code>01001 OR 111010 = 111011</code>

**Parameters:**

* <code>ed1</code> - The first logical operand to the logical OR operation.

* <code>ed2</code> - The second logical operand to the logical OR operation.

* <code>ec</code> - An arithmetic context to control the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more digits than the maximum precision specified in this
 context, the operand's most significant digits that exceed that
 precision are discarded. This parameter can be null.

**Returns:**

* The result of the logical OR operation as a logical operand. Signals
 an invalid operation and returns not-a-number (NaN) if <code>ed1</code> ,
 <code>ed2</code> , or both are not logical operands.
