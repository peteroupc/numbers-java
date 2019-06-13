# com.upokecenter.numbers.EDecimalExtras

    public final class EDecimalExtras extends java.lang.Object

A class that implements additional operations on arbitrary-precision decimal
 numbers. Many of them are listed as miscellaneous operations in the
 General Decimal Arithmetic Specification version 1.70.

## Methods

* `static EDecimal And​(EDecimal ed1,
   EDecimal ed2,
   EContext ec)`<br>
 Performs a logical AND operation on two decimal numbers in the form of
 logical operands  .
* `static EDecimal BoolToEDecimal​(boolean b,
              EContext ec)`<br>
 Not documented yet.
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
 Not documented yet.
* `static EDecimal Copy​(EDecimal ed)`<br>
 Not documented yet.
* `static EDecimal CopyAbs​(EDecimal ed)`<br>
 Not documented yet.
* `static EDecimal CopyNegate​(EDecimal ed)`<br>
 Not documented yet.
* `static EDecimal CopySign​(EDecimal ed,
        EDecimal other)`<br>
 Not documented yet.
* `static EDecimal Int32ToEDecimal​(int i32,
               EContext ec)`<br>
 Not documented yet.
* `static EDecimal Invert​(EDecimal ed1,
      EContext ec)`<br>
 Not documented yet.
* `static boolean IsCanonical​(EDecimal ed)`<br>
 Not documented yet.
* `static boolean IsFinite​(EDecimal ed)`<br>
 Not documented yet.
* `static boolean IsInfinite​(EDecimal ed)`<br>
 Not documented yet.
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
 Not documented yet.
* `static EDecimal LogB​(EDecimal ed,
    EContext ec)`<br>
 Not documented yet.
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
 Not documented yet.
* `static EDecimal Rotate​(EDecimal ed,
      EDecimal ed2,
      EContext ec)`<br>
 Rotates the digits of an arbitrary-precision decimal number's mantissa.
* `static boolean SameQuantum​(EDecimal ed1,
           EDecimal ed2)`<br>
 Not documented yet.
* `static EDecimal ScaleB​(EDecimal ed,
      EDecimal ed2,
      EContext ec)`<br>
 Not documented yet.
* `static EDecimal Shift​(EDecimal ed,
     EDecimal ed2,
     EContext ec)`<br>
 Not documented yet.
* `static EDecimal Trim​(EDecimal ed1,
    EContext ec)`<br>
 Not documented yet.
* `static EDecimal Xor​(EDecimal ed1,
   EDecimal ed2,
   EContext ec)`<br>
 Performs a logical exclusive-OR (XOR) operation on two decimal numbers in
 the form of logical operands.

## Method Details

### Radix
    public static EDecimal Radix​(EContext ec)
Returns the number 10, the decimal radix.

**Parameters:**

* <code>ec</code> - Specifies a precision context for rounding the number 10. Can be
 null.

**Returns:**

* The number 10, rounded as given in the precision context.

### Int32ToEDecimal
    public static EDecimal Int32ToEDecimal​(int i32, EContext ec)
Not documented yet.

**Parameters:**

* <code>i32</code> - The parameter <code>i32</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An EDecimal object.

### BoolToEDecimal
    public static EDecimal BoolToEDecimal​(boolean b, EContext ec)
Not documented yet.

**Parameters:**

* <code>b</code> - The parameter <code>b</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An EDecimal object.

### IsCanonical
    public static boolean IsCanonical​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* Always <code>true</code> .

### IsFinite
    public static boolean IsFinite​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### IsInfinite
    public static boolean IsInfinite​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* Either <code>true</code> or <code>false</code> .

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
 property (or the number's exponent in scientific notation) is less
 than the minimum possible exponent for that number. A <i> normal
 number </i> is nonzero and finite, but not subnormal.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

* <code>ec</code> - A context specifying the exponent range of arbitrary-precision
 numbers. Can be null. If AdjustExponent of the given context is
 <code>true</code> , a nonzero number is normal if the number's exponent in
 scientific notation is at least the given context's EMax property
 (e.g., if EMax is -100, 2.3456 * 10 <sup> -99 </sup> is normal, but
 2.3456 * 10 <sup> -102 </sup> is not). If AdjustExponent of the given
 context is <code>false</code> , a nonzero number is subnormal if the
 number's Exponent property is at least given context's EMax property
 (e.g., if EMax is -100, 23456 * 10 <sup> -99 </sup> is normal, but
 23456 * 10 <sup> -102 </sup> is not).

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

* A 32-bit signed integer identifying the given number class as
 follows: 0 = positive normal; 1 = negative normal, 2 = positive
 subnormal, 3 = negative subnormal, 4 = positive zero, 5 = negative
 zero, 6 = positive infinity, 7 = negative infinity, 8 = quiet
 not-a-number (NaN), 9 = signaling NaN.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> is null.

### IsSubnormal
    public static boolean IsSubnormal​(EDecimal ed, EContext ec)
Returns whether the given number is a <i> subnormal </i> number. A <i>
 subnormal number </i> is a nonzero finite number whose Exponent
 property (or the number's exponent in scientific notation) is less
 than the minimum possible exponent for that number.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

* <code>ec</code> - A context specifying the exponent range of arbitrary-precision
 numbers. Can be null. If AdjustExponent of the given context is
 <code>true</code> , a nonzero number is subnormal if the number's exponent
 in scientific notation is less than the given context's EMax property
 (e.g., if EMax is -100, 2.3456 * 10 <sup> -102 </sup> is subnormal,
 but 2.3456 * 10 <sup> -99 </sup> is not). If AdjustExponent of the
 given context is <code>false</code> , a nonzero number is subnormal if the
 number's Exponent property is less than the given context's EMax
 property (e.g., if EMax is -100, 23456 * 10 <sup> -102 </sup> is
 subnormal, but 23456 * 10 <sup> -99 </sup> is not).

**Returns:**

* Either <code>true</code> if the given number is subnormal, or <code>
 false</code> otherwise. Returns <code>false</code> if the given context is null
 or HasExponentRange of the given context is <code>false</code> .

### IsZero
    public static boolean IsZero​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### LogB
    public static EDecimal LogB​(EDecimal ed, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An EDecimal object.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> is null.

### ScaleB
    public static EDecimal ScaleB​(EDecimal ed, EDecimal ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>ed2</code> - The parameter <code>ed2</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An EDecimal object.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> or <code>ed2</code>
 is null.

### Shift
    public static EDecimal Shift​(EDecimal ed, EDecimal ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>ed2</code> - The parameter <code>ed2</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An EDecimal object.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> or <code>ed2</code>
 is null.

### Rotate
    public static EDecimal Rotate​(EDecimal ed, EDecimal ed2, EContext ec)
Rotates the digits of an arbitrary-precision decimal number's mantissa.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number containing the mantissa to rotate.
 If this mantissa contains more digits than the precision, the
 most-significant digits are chopped off the mantissa.

* <code>ed2</code> - An arbitrary-precision number indicating the number of digits to
 rotate the first operand's mantissa. Must be an integer with an
 exponent of 0. If this parameter is positive, the mantissa is shifted
 by the given number of digits and the most-significant digits shifted
 out of the mantissa become the least-significant digits instead. If
 this parameter is negative, the number is shifted by the given number
 of digits and the least-significant digits shifted out of the
 mantissa become the most-significant digits instead.

* <code>ec</code> - A context that specifies the precision of arbitrary-precision
 numbers. If this parameter is null or specifies an unlimited
 precision, this method has the same behavior as <code>Shift</code>.

**Returns:**

* An arbitrary-precision decimal number whose mantissa is rotated the
 given number of bits. Signals an invalid operation and returns NaN
 (not-a-number) if <code>ed2</code> is a signaling NaN or if <code>ed2</code> is
 not an integer, is negative, has an exponent other than 0, or has an
 absolute value that exceeds the maximum precision specified in the
 context.

### CompareTotal
    public static int CompareTotal​(EDecimal ed, EDecimal other, EContext ec)
Compares the values of one arbitrary-precision number object and another
 object, imposing a total ordering on all possible values. In this
 method: <ul> <li>For objects with the same value, the one with the
 higher exponent has a greater "absolute value".</li> <li>Negative
 zero is less than positive zero.</li> <li>Quiet NaN has a higher
 "absolute value" than signaling NaN. If both objects are quiet NaN or
 both are signaling NaN, the one with the higher diagnostic
 information has a greater "absolute value".</li> <li>NaN has a higher
 "absolute value" than infinity.</li> <li>Infinity has a higher
 "absolute value" than any finite number.</li> <li>Negative numbers
 are less than positive numbers.</li></ul>

**Returns:**

* The number 0 if both objects have the same value, or -1 if the first
 object is less than the other value, or 1 if the first object is
 greater. Does not signal flags if either value is signaling NaN.

### CompareTotalMagnitude
    public static int CompareTotalMagnitude​(EDecimal ed, EDecimal other, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>other</code> - The parameter <code>other</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* A 32-bit signed integer.

### Copy
    public static EDecimal Copy​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* An EDecimal object.

### Canonical
    public static EDecimal Canonical​(EDecimal ed)
Returns a canonical version of the given arbitrary-precision number object.
 In this method, this is the same as that object.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* The parameter <code>ed</code>.

### CopyAbs
    public static EDecimal CopyAbs​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* An EDecimal object.

### CopyNegate
    public static EDecimal CopyNegate​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* An EDecimal object.

### CopySign
    public static EDecimal CopySign​(EDecimal ed, EDecimal other)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>other</code> - The parameter <code>other</code> is not documented yet.

**Returns:**

* An EDecimal object.

### SameQuantum
    public static boolean SameQuantum​(EDecimal ed1, EDecimal ed2)
Not documented yet.

**Parameters:**

* <code>ed1</code> - The parameter <code>ed1</code> is not documented yet.

* <code>ed2</code> - The parameter <code>ed2</code> is not documented yet.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### Trim
    public static EDecimal Trim​(EDecimal ed1, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - The parameter <code>ed1</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An EDecimal object.

### Rescale
    public static EDecimal Rescale​(EDecimal ed, EDecimal scale, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>scale</code> - The parameter <code>scale</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An EDecimal object.

### And
    public static EDecimal And​(EDecimal ed1, EDecimal ed2, EContext ec)
Performs a logical AND operation on two decimal numbers in the form of <i>
 logical operands </i> . A <code>logical operand</code> is a non-negative
 base-10 number with an exponent of 0 and no other base-10 digits than
 0 or 1 (examples include <code>01001</code> and <code>111001</code> , but not
 <code>02001</code> or <code>99999</code>). The logical AND operation sets each
 digit of the result to 1 if the corresponding digits of each logical
 operand are both 1, and to 0 otherwise. For example, <code>01001 AND
 111010 = 01000</code>

**Parameters:**

* <code>ed1</code> - The first logical operand to the logical AND operation.

* <code>ed2</code> - The second logical operand to the logical AND operation.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An EDecimal object.

### Invert
    public static EDecimal Invert​(EDecimal ed1, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - The parameter <code>ed1</code> is not documented yet.

* <code>ec</code> - A context that specifies the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more digits than the maximum precision specified in this
 context, the operand's most significant digits that exceed that
 precision are discarded. This parameter cannot be null and must
 specify a maximum precision (unlimited precision contexts are not
 allowed).

**Returns:**

* An EDecimal object.

### Xor
    public static EDecimal Xor​(EDecimal ed1, EDecimal ed2, EContext ec)
Performs a logical exclusive-OR (XOR) operation on two decimal numbers in
 the form of <i>logical operands</i>. A <code>logical operand</code> is a
 non-negative base-10 number with an exponent of 0 and no other
 base-10 digits than 0 or 1 (examples include <code>01001</code> and
 <code>111001</code>, but not <code>02001</code> or <code>99999</code>). The logical
 exclusive-OR operation sets each digit of the result to 1 if either
 corresponding digit of the logical operands, but not both, are 1, and
 to 0 otherwise. For example, <code>01001 OR 111010 = 101010</code>

**Parameters:**

* <code>ed1</code> - The first logical operand to the logical exclusive-OR operation.

* <code>ed2</code> - The second logical operand to the logical exclusive-OR operation.

* <code>ec</code> - A context that specifies the maximum precision of
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
 base-10 number with an exponent of 0 and no other base-10 digits than
 0 or 1 (examples include <code>01001</code> and <code>111001</code> , but not
 <code>02001</code> or <code>99999</code>). The logical OR operation sets each
 digit of the result to 1 if either or both of the corresponding
 digits of the logical operands are 1, and to 0 otherwise. For
 example, <code>01001 OR 111010 = 111011</code>

**Parameters:**

* <code>ed1</code> - The first logical operand to the logical OR operation.

* <code>ed2</code> - The second logical operand to the logical OR operation.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An EDecimal object.
