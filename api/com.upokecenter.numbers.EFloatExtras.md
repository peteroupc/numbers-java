# com.upokecenter.numbers.EFloatExtras

    public final class EFloatExtras extends java.lang.Object

A class that implements additional operations on arbitrary-precision binary
 floating-point numbers.

## Methods

* `static EFloat And​(EFloat ed1,
   EFloat ed2,
   EContext ec)`<br>
 Not documented yet.
* `static EFloat BooleanToEFloat​(boolean b,
               EContext ec)`<br>
 Converts a boolean value (either true or false) to an arbitrary-precision
 binary floating-point number.
* `static EFloat BoolToEFloat​(boolean b,
            EContext ec)`<br>
 Deprecated. 
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
 Not documented yet.
* `static EFloat Copy​(EFloat ed)`<br>
 Not documented yet.
* `static EFloat CopyAbs​(EFloat ed)`<br>
 Not documented yet.
* `static EFloat CopyNegate​(EFloat ed)`<br>
 Not documented yet.
* `static EFloat CopySign​(EFloat ed,
        EFloat other)`<br>
 Not documented yet.
* `static EFloat Int32ToEFloat​(int i32,
             EContext ec)`<br>
 Not documented yet.
* `static EFloat Invert​(EFloat ed1,
      EContext ec)`<br>
 Not documented yet.
* `static boolean IsCanonical​(EFloat ed)`<br>
 Not documented yet.
* `static boolean IsFinite​(EFloat ed)`<br>
 Not documented yet.
* `static boolean IsInfinite​(EFloat ed)`<br>
 Not documented yet.
* `static boolean IsNaN​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is a
 not-a-number (NaN).
* `static boolean IsNormal​(EFloat ed,
        EContext ec)`<br>
 Returns whether the given number is a  normal  number.
* `static boolean IsQuietNaN​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is a quiet
 not-a-number (NaN).
* `static boolean IsSignalingNaN​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is a signaling
 not-a-number (NaN).
* `static boolean IsSigned​(EFloat ed)`<br>
 Returns whether the given arbitrary-precision number object is negative
 (including negative infinity, negative not-a-number [NaN], or
 negative zero).
* `static boolean IsSubnormal​(EFloat ed,
           EContext ec)`<br>
 Returns whether the given number is a  subnormal  number.
* `static boolean IsZero​(EFloat ed)`<br>
 Not documented yet.
* `static EFloat LogB​(EFloat ed,
    EContext ec)`<br>
 Not documented yet.
* `static int NumberClass​(EFloat ed,
           EContext ec)`<br>
 Finds the number class for an arbitrary-precision decimal number object.
* `static java.lang.String NumberClassString​(int nc)`<br>
 Converts a number class identifier (ranging from 1 to 9) to a text string.
* `static EFloat Or​(EFloat ed1,
  EFloat ed2,
  EContext ec)`<br>
 Not documented yet.
* `static EFloat Radix​(EContext ec)`<br>
 Not documented yet.
* `static EFloat Rescale​(EFloat ed,
       EFloat scale,
       EContext ec)`<br>
 Not documented yet.
* `static EFloat Rotate​(EFloat ed,
      EFloat ed2,
      EContext ec)`<br>
 Rotates the digits of an arbitrary-precision binary number's mantissa.
* `static boolean SameQuantum​(EFloat ed1,
           EFloat ed2)`<br>
 Not documented yet.
* `static EFloat ScaleB​(EFloat ed,
      EFloat ed2,
      EContext ec)`<br>
 Not documented yet.
* `static EFloat Shift​(EFloat ed,
     EFloat ed2,
     EContext ec)`<br>
 Not documented yet.
* `static EFloat Trim​(EFloat ed1,
    EContext ec)`<br>
 Not documented yet.
* `static EFloat Xor​(EFloat ed1,
   EFloat ed2,
   EContext ec)`<br>
 Not documented yet.

## Method Details

### Radix
    public static EFloat Radix​(EContext ec)
Not documented yet.

**Parameters:**

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An arbitrary-precision binary floating-point number.

### Int32ToEFloat
    public static EFloat Int32ToEFloat​(int i32, EContext ec)
Not documented yet.

**Parameters:**

* <code>i32</code> - The parameter <code>i32</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An arbitrary-precision binary floating-point number.

### BoolToEFloat
    @Deprecated public static EFloat BoolToEFloat​(boolean b, EContext ec)
Deprecated.

**Parameters:**

* <code>b</code> - Either true or false.

* <code>ec</code> - A context used for rounding the result. Can be null.

**Returns:**

* Either 1 if <code>b</code> is true, or 0 if <code>b</code> is false.. The
 result will be rounded as specified by the given context, if any.

### BooleanToEFloat
    public static EFloat BooleanToEFloat​(boolean b, EContext ec)
Converts a boolean value (either true or false) to an arbitrary-precision
 binary floating-point number.

**Parameters:**

* <code>b</code> - Either true or false.

* <code>ec</code> - A context used for rounding the result. Can be null.

**Returns:**

* Either 1 if <code>b</code> is true, or 0 if <code>b</code> is false.. The
 result will be rounded as specified by the given context, if any.

### IsCanonical
    public static boolean IsCanonical​(EFloat ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* Always <code>true</code> .

### IsFinite
    public static boolean IsFinite​(EFloat ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### IsInfinite
    public static boolean IsInfinite​(EFloat ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### IsNaN
    public static boolean IsNaN​(EFloat ed)
Returns whether the given arbitrary-precision number object is a
 not-a-number (NaN).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### IsNormal
    public static boolean IsNormal​(EFloat ed, EContext ec)
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
    public static boolean IsQuietNaN​(EFloat ed)
Returns whether the given arbitrary-precision number object is a quiet
 not-a-number (NaN).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### IsSigned
    public static boolean IsSigned​(EFloat ed)
Returns whether the given arbitrary-precision number object is negative
 (including negative infinity, negative not-a-number [NaN], or
 negative zero).

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### IsSignalingNaN
    public static boolean IsSignalingNaN​(EFloat ed)
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
    public static int NumberClass​(EFloat ed, EContext ec)
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
    public static boolean IsSubnormal​(EFloat ed, EContext ec)
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
    public static boolean IsZero​(EFloat ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### LogB
    public static EFloat LogB​(EFloat ed, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An arbitrary-precision binary floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> is null.

### ScaleB
    public static EFloat ScaleB​(EFloat ed, EFloat ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>ed2</code> - The parameter <code>ed2</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An arbitrary-precision binary floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> or <code>ed2</code>
 is null.

### Shift
    public static EFloat Shift​(EFloat ed, EFloat ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>ed2</code> - The parameter <code>ed2</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An arbitrary-precision binary floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> or <code>ed2</code>
 is null.

### Rotate
    public static EFloat Rotate​(EFloat ed, EFloat ed2, EContext ec)
Rotates the digits of an arbitrary-precision binary number's mantissa.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number containing the mantissa to rotate.
 If this mantissa contains more bits than the precision, the
 most-significant bits are chopped off the mantissa.

* <code>ed2</code> - An arbitrary-precision number indicating the number of bits to
 rotate the first operand's mantissa. Must be an integer with an
 exponent of 0. If this parameter is positive, the mantissa is shifted
 by the given number of bits and the most-significant bits shifted out
 of the mantissa become the least-significant bits instead. If this
 parameter is negative, the number is shifted by the given number of
 bits and the least-significant bits shifted out of the mantissa
 become the most-significant bits instead.

* <code>ec</code> - A context that specifies the precision of arbitrary-precision
 numbers. If this parameter is null or specifies an unlimited
 precision, this method has the same behavior as <code>Shift</code>.

**Returns:**

* An arbitrary-precision binary number whose mantissa is rotated the
 given number of bits. Signals an invalid operation and returns NaN
 (not-a-number) if <code>ed2</code> is a signaling NaN or if <code>ed2</code> is
 not an integer, is negative, has an exponent other than 0, or has an
 absolute value that exceeds the maximum precision specified in the
 context.

### CompareTotal
    public static int CompareTotal​(EFloat ed, EFloat other, EContext ec)
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

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>other</code> - Not documented yet.

* <code>ec</code> - Not documented yet. (3).

**Returns:**

* The number 0 if both objects have the same value, or -1 if the first
 object is less than the other value, or 1 if the first object is
 greater. Does not signal flags if either value is signaling NaN.

### CompareTotalMagnitude
    public static int CompareTotalMagnitude​(EFloat ed, EFloat other, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>other</code> - The parameter <code>other</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* A 32-bit signed integer.

### Copy
    public static EFloat Copy​(EFloat ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* An arbitrary-precision binary floating-point number.

### Canonical
    public static EFloat Canonical​(EFloat ed)
Returns a canonical version of the given arbitrary-precision number object.
 In this method, this is the same as that object.

**Parameters:**

* <code>ed</code> - An arbitrary-precision number object.

**Returns:**

* The parameter <code>ed</code>.

### CopyAbs
    public static EFloat CopyAbs​(EFloat ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* An arbitrary-precision binary floating-point number.

### CopyNegate
    public static EFloat CopyNegate​(EFloat ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

**Returns:**

* An arbitrary-precision binary floating-point number.

### CopySign
    public static EFloat CopySign​(EFloat ed, EFloat other)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>other</code> - The parameter <code>other</code> is not documented yet.

**Returns:**

* An arbitrary-precision binary floating-point number.

### SameQuantum
    public static boolean SameQuantum​(EFloat ed1, EFloat ed2)
Not documented yet.

**Parameters:**

* <code>ed1</code> - The parameter <code>ed1</code> is not documented yet.

* <code>ed2</code> - The parameter <code>ed2</code> is not documented yet.

**Returns:**

* Either <code>true</code> or <code>false</code> .

### Trim
    public static EFloat Trim​(EFloat ed1, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - The parameter <code>ed1</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An arbitrary-precision binary floating-point number.

### Rescale
    public static EFloat Rescale​(EFloat ed, EFloat scale, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - The parameter <code>ed</code> is not documented yet.

* <code>scale</code> - The parameter <code>scale</code> is not documented yet.

* <code>ec</code> - The parameter <code>ec</code> is not documented yet.

**Returns:**

* An arbitrary-precision binary floating-point number.

### And
    public static EFloat And​(EFloat ed1, EFloat ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - The parameter <code>ed1</code> is not documented yet.

* <code>ed2</code> - The parameter <code>ed2</code> is not documented yet.

* <code>ec</code> - A context that specifies the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more bits than the maximum precision specified in this
 context, the operand's most significant bits that exceed that
 precision are discarded. This parameter can be null.

**Returns:**

* An arbitrary-precision binary floating-point number.

### Invert
    public static EFloat Invert​(EFloat ed1, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - The parameter <code>ed1</code> is not documented yet.

* <code>ec</code> - A context that specifies the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more bits than the maximum precision specified in this
 context, the operand's most significant bits that exceed that
 precision are discarded. This parameter cannot be null and must
 specify a maximum precision (unlimited precision contexts are not
 allowed).

**Returns:**

* An arbitrary-precision binary floating-point number.

### Xor
    public static EFloat Xor​(EFloat ed1, EFloat ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - The parameter <code>ed1</code> is not documented yet.

* <code>ed2</code> - The parameter <code>ed2</code> is not documented yet.

* <code>ec</code> - A context that specifies the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more bits than the maximum precision specified in this
 context, the operand's most significant bits that exceed that
 precision are discarded. This parameter can be null.

**Returns:**

* An arbitrary-precision binary floating-point number.

### Or
    public static EFloat Or​(EFloat ed1, EFloat ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - The parameter <code>ed1</code> is not documented yet.

* <code>ed2</code> - The parameter <code>ed2</code> is not documented yet.

* <code>ec</code> - A context that specifies the maximum precision of
 arbitrary-precision numbers. If a logical operand passed to this
 method has more bits than the maximum precision specified in this
 context, the operand's most significant bits that exceed that
 precision are discarded. This parameter can be null.

**Returns:**

* An arbitrary-precision binary floating-point number.
