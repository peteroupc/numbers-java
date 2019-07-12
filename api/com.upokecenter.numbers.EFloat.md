# com.upokecenter.numbers.EFloat

    public final class EFloat extends java.lang.Object implements java.lang.Comparable<EFloat>

Represents an arbitrary-precision binary floating-point number. (The "E"
  stands for "extended", meaning that instances of this class can be
 values other than numbers proper, such as infinity and
 not-a-number.) Each number consists of an integer mantissa
 (significand) and an integer exponent, both arbitrary-precision. The
 value of the number equals mantissa (significand) * 2^exponent. This
 class also supports values for negative zero, not-a-number (NaN)
 values, and infinity. <p>Passing a signaling NaN to any arithmetic
 operation shown here will signal the flag FlagInvalid and return a
 quiet NaN, even if another operand to that operation is a quiet NaN,
 unless noted otherwise.</p> <p>Passing a quiet NaN to any arithmetic
 operation shown here will return a quiet NaN, unless noted
 otherwise.</p> <p>Unless noted otherwise, passing a null
 arbitrary-precision binary float argument to any method here will
 throw an exception.</p> <p>When an arithmetic operation signals the
 flag FlagInvalid, FlagOverflow, or FlagDivideByZero, it will not
 throw an exception too, unless the operation's trap is enabled in
 the arithmetic context (see EContext's Traps property).</p> <p>An
 arbitrary-precision binary float value can be serialized in one of
 the following ways:</p> <ul> <li>By calling the toString() method.
 However, not all strings can be converted back to an
 arbitrary-precision binary float without loss, especially if the
 string has a fractional part.</li> <li>By calling the
 UnsignedMantissa, Exponent, and IsNegative properties, and calling
 the IsInfinity, IsQuietNaN, and IsSignalingNaN methods. The return
 values combined will uniquely identify a particular
 arbitrary-precision binary float value.</li></ul> <p>If an operation
 requires creating an intermediate value that might be too big to fit
 in memory (or might require more than 2 gigabytes of memory to store
 -- due to the current use of a 32-bit integer internally as a
 length), the operation may signal an invalid-operation flag and
 return not-a-number (NaN). In certain rare cases, the compareTo
 method may throw OutOfMemoryError (called OutOfMemoryError in Java)
 in the same circumstances.</p> <p><b>Thread safety</b></p>
 <p>Instances of this class are immutable, so they are inherently
 safe for use by multiple threads. Multiple instances of this object
 with the same properties are interchangeable, so they should not be
  compared using the "==" operator (which might only check if each
 side of the operator is the same instance).</p> <p><b>Comparison
 considerations</b></p> <p>This class's natural ordering (under the
 compareTo method) is not consistent with the Equals method. This
 means that two values that compare as equal under the compareTo
 method might not be equal under the Equals method. The compareTo
 method compares the mathematical values of the two instances passed
 to it (and considers two different NaN values as equal), while two
 instances with the same mathematical value, but different exponents,
 will be considered unequal under the Equals method.</p>
 <p><b>Security note</b></p> <p>It is not recommended to implement
 security-sensitive algorithms using the methods in this class, for
 several reasons:</p> <ul> <li><code>EFloat</code> objects are immutable,
 so they can't be modified, and the memory they occupy is not
 guaranteed to be cleared in a timely fashion due to garbage
 collection. This is relevant for applications that use many-bit-long
 numbers as secret parameters.</li> <li>The methods in this class
 (especially those that involve arithmetic) are not guaranteed to be
  "constant-time" (non-data-dependent) for all relevant inputs.
 Certain attacks that involve encrypted communications have exploited
 the timing and other aspects of such communications to derive keying
 material or cleartext indirectly.</li></ul> <p>Applications should
 instead use dedicated security libraries to handle big numbers in
 security-sensitive algorithms.</p>

## Fields

* `static EFloat NaN`<br>
 A not-a-number value.
* `static EFloat NegativeInfinity`<br>
 Negative infinity, less than any other number.
* `static EFloat NegativeZero`<br>
 Represents the number negative zero.
* `static EFloat One`<br>
 Represents the number 1.
* `static EFloat PositiveInfinity`<br>
 Positive infinity, greater than any other number.
* `static EFloat SignalingNaN`<br>
 A not-a-number value that signals an invalid operation flag when it's passed
 as an argument to any arithmetic operation in arbitrary-precision
 binary float.
* `static EFloat Ten`<br>
 Represents the number 10.
* `static EFloat Zero`<br>
 Represents the number 0.

## Methods

* `EFloat Abs()`<br>
 Finds the absolute value of this object (if it's negative, it becomes
 positive).
* `EFloat Abs​(EContext context)`<br>
 Finds the absolute value of this object (if it's negative, it becomes
 positive).
* `EFloat Add​(int intValue)`<br>
* `EFloat Add​(EFloat otherValue)`<br>
 Adds this object and another binary float and returns the result.
* `EFloat Add​(EFloat otherValue,
   EContext ctx)`<br>
 Finds the sum of this object and another object.
* `int compareTo​(EFloat other)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `EFloat CompareToSignal​(EFloat other,
               EContext ctx)`<br>
 Compares the mathematical values of this object and another object, treating
 quiet NaN as signaling.
* `int CompareToTotal​(EFloat other)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values.
* `int CompareToTotal​(EFloat other,
              EContext ctx)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values.
* `int CompareToTotalMagnitude​(EFloat other)`<br>
 Compares the absolute values of this object and another object, imposing a
 total ordering on all possible values (ignoring their signs).
* `int CompareToTotalMagnitude​(EFloat other,
                       EContext ctx)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values (ignoring their signs).
* `EFloat CompareToWithContext​(EFloat other,
                    EContext ctx)`<br>
 Compares the mathematical values of this object and another object.
* `EFloat Copy()`<br>
 Creates a copy of this arbitrary-precision binary number.
* `EFloat CopySign​(EFloat other)`<br>
 Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number.
* `static EFloat Create​(int mantissaSmall,
      int exponentSmall)`<br>
 Creates a number with the value exponent*2^mantissa (significand).
* `static EFloat Create​(EInteger mantissa,
      EInteger exponent)`<br>
 Creates a number with the value exponent*2^mantissa (significand).
* `static EFloat CreateNaN​(EInteger diag)`<br>
 Creates a not-a-number arbitrary-precision binary floating-point number.
* `static EFloat CreateNaN​(EInteger diag,
         boolean signaling,
         boolean negative,
         EContext ctx)`<br>
 Creates a not-a-number arbitrary-precision binary floating-point number.
* `EFloat Divide​(int intValue)`<br>
 Divides this instance by the value of an arbitrary-precision integer.
* `EFloat Divide​(EFloat divisor)`<br>
 Divides this object by another binary float and returns the result.
* `EFloat Divide​(EFloat divisor,
      EContext ctx)`<br>
 Divides this arbitrary-precision binary float by another arbitrary-precision
 binary floating-point number.
* `EFloat[] DivideAndRemainderNaturalScale​(EFloat divisor)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EFloat[] DivideAndRemainderNaturalScale​(EFloat divisor,
                              EContext ctx)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EFloat DivideToExponent​(EFloat divisor,
                long desiredExponentSmall,
                EContext ctx)`<br>
 Divides two arbitrary-precision binary floating-point numbers, and gives a
 particular exponent to the result.
* `EFloat DivideToExponent​(EFloat divisor,
                long desiredExponentSmall,
                ERounding rounding)`<br>
 Divides two arbitrary-precision binary floating-point numbers, and gives a
 particular exponent to the result.
* `EFloat DivideToExponent​(EFloat divisor,
                EInteger exponent,
                EContext ctx)`<br>
 Divides two arbitrary-precision binary floating-point numbers, and gives a
 particular exponent to the result.
* `EFloat DivideToExponent​(EFloat divisor,
                EInteger desiredExponent,
                ERounding rounding)`<br>
 Divides two arbitrary-precision binary floating-point numbers, and gives a
 particular exponent to the result.
* `EFloat DivideToIntegerNaturalScale​(EFloat divisor)`<br>
 Divides two arbitrary-precision binary floating-point numbers, and returns
 the integer part of the result, rounded down, with the preferred
 exponent set to this value's exponent minus the divisor's exponent.
* `EFloat DivideToIntegerNaturalScale​(EFloat divisor,
                           EContext ctx)`<br>
 Divides this object by another object, and returns the integer part of the
 result (which is initially rounded down), with the preferred
 exponent set to this value's exponent minus the divisor's exponent.
* `EFloat DivideToIntegerZeroScale​(EFloat divisor,
                        EContext ctx)`<br>
 Divides this object by another object, and returns the integer part of the
 result, with the exponent set to 0.
* `EFloat DivideToSameExponent​(EFloat divisor,
                    ERounding rounding)`<br>
 Divides this object by another binary float and returns a result with the
 same exponent as this object (the dividend).
* `EFloat[] DivRemNaturalScale​(EFloat divisor)`<br>
 Calculates the quotient and remainder using the DivideToIntegerNaturalScale
 and the formula in RemainderNaturalScale.
* `EFloat[] DivRemNaturalScale​(EFloat divisor,
                  EContext ctx)`<br>
 Calculates the quotient and remainder using the DivideToIntegerNaturalScale
 and the formula in RemainderNaturalScale.
* `boolean equals​(EFloat other)`<br>
 Determines whether this object's mantissa (significand), exponent, and
 properties are equal to those of another object.
* `boolean equals​(java.lang.Object obj)`<br>
 Determines whether this object's mantissa (significand), exponent, and
 properties are equal to those of another object and that other
 object is an arbitrary-precision binary floating-point number.
* `boolean EqualsInternal​(EFloat otherValue)`<br>
 Determines whether this object's mantissa (significand) and exponent are
 equal to those of another object.
* `EFloat Exp​(EContext ctx)`<br>
 Finds e (the base of natural logarithms) raised to the power of this
 object's value.
* `static EFloat FromBoolean​(boolean boolValue)`<br>
 Converts a boolean value (either true or false) to an arbitrary-precision
 binary float.
* `static EFloat FromByte​(byte inputByte)`<br>
 Converts a byte (from 0 to 255) to an arbitrary-precision binary
 floating-point number.
* `static EFloat FromDouble​(double dbl)`<br>
 Creates a binary float from a 64-bit floating-point number.
* `static EFloat FromEInteger​(EInteger bigint)`<br>
 Converts an arbitrary-precision integer to the same value as a binary float.
* `static EFloat FromInt16​(short inputInt16)`<br>
 Converts a 16-bit signed integer to an arbitrary-precision binary
 floating-point number.
* `static EFloat FromInt32​(int inputInt32)`<br>
 Converts a 32-bit signed integer to an arbitrary-precision binary
 floating-point number.
* `static EFloat FromInt64​(long inputInt64)`<br>
 Converts a 64-bit signed integer to an arbitrary-precision binary
 floating-point number.
* `static EFloat FromSingle​(float flt)`<br>
 Creates a binary float from a 32-bit floating-point number.
* `static EFloat FromString​(java.lang.String str)`<br>
 Creates a binary float from a text string that represents a number, using an
 unlimited precision context.
* `static EFloat FromString​(java.lang.String str,
          int offset,
          int length)`<br>
 Creates a binary float from a text string that represents a number.
* `static EFloat FromString​(java.lang.String str,
          int offset,
          int length,
          EContext ctx)`<br>
 Creates a binary float from a text string that represents a number.
* `static EFloat FromString​(java.lang.String str,
          EContext ctx)`<br>
 Creates a binary float from a text string that represents a number.
* `EInteger getExponent()`<br>
 Gets this object's exponent.
* `EInteger getMantissa()`<br>
 Gets this object's unscaled value, or mantissa, and makes it negative if
 this obejct is negative.
* `EInteger getUnsignedMantissa()`<br>
 Gets the absolute value of this object's unscaled value, or mantissa.
* `int hashCode()`<br>
 Calculates this object's hash code.
* `boolean isFinite()`<br>
 Gets a value indicating whether this object is finite (not infinity or NaN).
* `boolean IsInfinity()`<br>
 Gets a value indicating whether this object is positive or negative
 infinity.
* `boolean IsNaN()`<br>
 Gets a value indicating whether this object is not a number (NaN).
* `boolean isNegative()`<br>
 Gets a value indicating whether this object is negative, including negative
 zero.
* `boolean IsNegativeInfinity()`<br>
 Returns whether this object is negative infinity.
* `boolean IsPositiveInfinity()`<br>
 Returns whether this object is positive infinity.
* `boolean IsQuietNaN()`<br>
 Gets a value indicating whether this object is a quiet not-a-number value.
* `boolean IsSignalingNaN()`<br>
 Gets a value indicating whether this object is a signaling not-a-number
 value.
* `boolean isZero()`<br>
 Gets a value indicating whether this object's value equals 0.
* `EFloat Log​(EContext ctx)`<br>
 Finds the natural logarithm of this object, that is, the power (exponent)
 that e (the base of natural logarithms) must be raised to in order
 to equal this object's value.
* `EFloat Log10​(EContext ctx)`<br>
 Finds the base-10 logarithm of this object, that is, the power (exponent)
 that the number 10 must be raised to in order to equal this object's
 value.
* `static EFloat Max​(EFloat first,
   EFloat second)`<br>
 Gets the greater value between two binary floating-point numbers.
* `static EFloat Max​(EFloat first,
   EFloat second,
   EContext ctx)`<br>
 Gets the greater value between two binary floating-point numbers.
* `static EFloat MaxMagnitude​(EFloat first,
            EFloat second)`<br>
 Gets the greater value between two values, ignoring their signs.
* `static EFloat MaxMagnitude​(EFloat first,
            EFloat second,
            EContext ctx)`<br>
 Gets the greater value between two values, ignoring their signs.
* `static EFloat Min​(EFloat first,
   EFloat second)`<br>
 Gets the lesser value between two binary floating-point numbers.
* `static EFloat Min​(EFloat first,
   EFloat second,
   EContext ctx)`<br>
 Gets the lesser value between two binary floating-point numbers.
* `static EFloat MinMagnitude​(EFloat first,
            EFloat second)`<br>
 Gets the lesser value between two values, ignoring their signs.
* `static EFloat MinMagnitude​(EFloat first,
            EFloat second,
            EContext ctx)`<br>
 Gets the lesser value between two values, ignoring their signs.
* `EFloat MovePointLeft​(int places)`<br>
 Returns a number similar to this number but with the radix point moved to
 the left.
* `EFloat MovePointLeft​(int places,
             EContext ctx)`<br>
 Returns a number similar to this number but with the radix point moved to
 the left.
* `EFloat MovePointLeft​(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the radix point moved to
 the left.
* `EFloat MovePointLeft​(EInteger bigPlaces,
             EContext ctx)`<br>
 Returns a number similar to this number but with the radix point moved to
 the left.
* `EFloat MovePointRight​(int places)`<br>
 Returns a number similar to this number but with the radix point moved to
 the right.
* `EFloat MovePointRight​(int places,
              EContext ctx)`<br>
 Returns a number similar to this number but with the radix point moved to
 the right.
* `EFloat MovePointRight​(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the radix point moved to
 the right.
* `EFloat MovePointRight​(EInteger bigPlaces,
              EContext ctx)`<br>
 Returns a number similar to this number but with the radix point moved to
 the right.
* `EFloat Multiply​(int intValue)`<br>
 Multiplies this instance by the value of an arbitrary-precision // /integer
 object.
* `EFloat Multiply​(EFloat otherValue)`<br>
 Multiplies two binary floating-point numbers.
* `EFloat Multiply​(EFloat op,
        EContext ctx)`<br>
 Multiplies two binary floating-point numbers.
* `EFloat MultiplyAndAdd​(EFloat multiplicand,
              EFloat augend)`<br>
 Multiplies by one binary float, and then adds another binary float.
* `EFloat MultiplyAndAdd​(EFloat op,
              EFloat augend,
              EContext ctx)`<br>
 Multiplies by one value, and then adds another value.
* `EFloat MultiplyAndSubtract​(EFloat op,
                   EFloat subtrahend,
                   EContext ctx)`<br>
 Multiplies by one value, and then subtracts another value.
* `EFloat Negate()`<br>
 Gets an object with the same value as this one, but with the sign reversed.
* `EFloat Negate​(EContext context)`<br>
 Returns a binary float with the same value as this object but with the sign
 reversed.
* `EFloat NextMinus​(EContext ctx)`<br>
 Finds the largest value that's smaller than the given value.
* `EFloat NextPlus​(EContext ctx)`<br>
 Finds the smallest value that's greater than the given value.
* `EFloat NextToward​(EFloat otherValue,
          EContext ctx)`<br>
 Finds the next value that is closer to the other object's value than this
 object's value.
* `static EFloat PI​(EContext ctx)`<br>
 Finds the constant π, the circumference of a circle divided by its diameter.
* `EFloat Plus​(EContext ctx)`<br>
 Rounds this object's value to a given precision, using the given rounding
 mode and range of exponent, and also converts negative zero to
 positive zero.
* `EFloat Pow​(int exponentSmall)`<br>
 Raises this object's value to the given exponent.
* `EFloat Pow​(int exponentSmall,
   EContext ctx)`<br>
 Raises this object's value to the given exponent.
* `EFloat Pow​(EFloat exponent,
   EContext ctx)`<br>
 Raises this object's value to the given exponent.
* `EInteger Precision()`<br>
 Finds the number of digits in this number's mantissa (significand).
* `EFloat Quantize​(int desiredExponentInt,
        EContext ctx)`<br>
 Returns a binary float with the same value but a new exponent.
* `EFloat Quantize​(EFloat otherValue,
        EContext ctx)`<br>
 Returns a binary float with the same value as this object but with the same
 exponent as another binary float.
* `EFloat Quantize​(EInteger desiredExponent,
        EContext ctx)`<br>
 Returns a binary float with the same value but a new exponent.
* `EFloat Reduce​(EContext ctx)`<br>
 Returns an object with the same numerical value as this one but with
 trailing zeros removed from its mantissa (significand).
* `EFloat Remainder​(EFloat divisor,
         EContext ctx)`<br>
 Finds the remainder that results when dividing two arbitrary-precision
 binary floating-point numbers.
* `EFloat RemainderNaturalScale​(EFloat divisor) "this" - (("this" /
  "divisor") * "divisor")`<br>
 Calculates the remainder of a number by the formula "this" - (("this" /
  "divisor") * "divisor").
* `EFloat RemainderNaturalScale​(EFloat divisor,
                     EContext ctx)`<br>
 Calculates the remainder of a number by the formula "this" - (("this" /
  "divisor") * "divisor").
* `EFloat RemainderNear​(EFloat divisor,
             EContext ctx)`<br>
 Finds the distance to the closest multiple of the given divisor, based on
 the result of dividing this object's value by another object's
 value.
* `EFloat RemainderNoRoundAfterDivide​(EFloat divisor,
                           EContext ctx)`<br>
 Finds the remainder that results when dividing two arbitrary-precision
 binary floating-point numbers.
* `EFloat RoundToExponent​(int exponentSmall,
               EContext ctx)`<br>
 Returns a binary float with the same value as this object but rounded to a
 new exponent if necessary.
* `EFloat RoundToExponent​(EInteger exponent,
               EContext ctx)`<br>
 Returns a binary float with the same value as this object but rounded to a
 new exponent if necessary.
* `EFloat RoundToExponentExact​(int exponentSmall,
                    EContext ctx)`<br>
 Returns a binary float with the same value as this object but rounded to the
 given exponent represented as a 32-bit signed integer, and signals
 an inexact flag if the result would be inexact.
* `EFloat RoundToExponentExact​(EInteger exponent,
                    EContext ctx)`<br>
 Returns a binary float with the same value as this object but rounded to the
 given exponent, and signals an inexact flag if the result would be
 inexact.
* `EFloat RoundToExponentExact​(EInteger exponent,
                    ERounding rounding)`<br>
 Returns a binary number with the same value as this object but rounded to
 the given exponent.
* `EFloat RoundToIntegerExact​(EContext ctx)`<br>
 Returns a binary float with the same value as this object but rounded to an
 integer, and signals an inexact flag if the result would be inexact.
* `EFloat RoundToIntegerNoRoundedFlag​(EContext ctx) FlagInexact FlagRounded`<br>
 Returns a binary float with the same value as this object but rounded to an
 integer, without adding the FlagInexact or FlagRounded
 flags.
* `EFloat RoundToIntegralExact​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerExact.
 Renamed to RoundToIntegerExact.
* `EFloat RoundToIntegralNoRoundedFlag​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerNoRoundedFlag.
 Renamed to RoundToIntegerNoRoundedFlag.
* `EFloat RoundToPrecision​(EContext ctx)`<br>
 Rounds this object's value to a given precision, using the given rounding
 mode and range of exponent.
* `EFloat ScaleByPowerOfTwo​(int places)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `EFloat ScaleByPowerOfTwo​(int places,
                 EContext ctx)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `EFloat ScaleByPowerOfTwo​(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `EFloat ScaleByPowerOfTwo​(EInteger bigPlaces,
                 EContext ctx)`<br>
 Returns a number similar to this number but with its scale adjusted.
* `int signum()`<br>
 Gets this value's sign: -1 if negative; 1 if positive; 0 if zero.
* `EFloat Sqrt​(EContext ctx)`<br>
 Finds the square root of this object's value.
* `EFloat SquareRoot​(EContext ctx)`<br>
 Deprecated.
Renamed to Sqrt.
 Renamed to Sqrt.
* `EFloat Subtract​(int intValue)`<br>
 Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer.
* `EFloat Subtract​(EFloat otherValue)`<br>
 Subtracts an arbitrary-precision binary float from this instance and returns
 the result.
* `EFloat Subtract​(EFloat otherValue,
        EContext ctx)`<br>
 Subtracts an arbitrary-precision binary float from this instance.
* `byte ToByteChecked()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) after truncating to an integer.
* `byte ToByteIfExact()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) without rounding to a different numerical
 value.
* `byte ToByteUnchecked()`<br>
 Truncates this number's value to an integer and returns the
 least-significant bits of its two's-complement form as a byte (from
 0 to 255).
* `double ToDouble()`<br>
 Converts this value to a 64-bit floating-point number.
* `EDecimal ToEDecimal()`<br>
 Converts this value to an arbitrary-precision decimal number.
* `EInteger ToEInteger()`<br>
 Converts this value to an arbitrary-precision integer.
* `EInteger ToEIntegerExact()`<br>
 Deprecated.
Renamed to ToEIntegerIfExact.
 Renamed to ToEIntegerIfExact.
* `EInteger ToEIntegerIfExact()`<br>
 Converts this value to an arbitrary-precision integer, checking whether the
 value contains a fractional part.
* `java.lang.String ToEngineeringString()`<br>
 Converts this value to an arbitrary-precision decimal number, then returns
 the value of that decimal's ToEngineeringString method.
* `EDecimal ToExtendedDecimal()`<br>
 Deprecated.
Renamed to ToEDecimal.
 Renamed to ToEDecimal.
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
* `java.lang.String ToPlainString()`<br>
 Converts this value to a string, but without exponential notation.
* `java.lang.String ToShortestString​(EContext ctx)`<br>
 Returns a string representation of this number's value after rounding to the
 given precision (using the given arithmetic context).
* `float ToSingle()`<br>
 Converts this value to its closest equivalent as 32-bit floating-point
 number.
* `java.lang.String toString()`<br>
 Converts this number's value to a text string.
* `EFloat Ulp()`<br>
 Returns the unit in the last place.

## Field Details

### NaN
    public static final EFloat NaN
A not-a-number value.
### NegativeInfinity
    public static final EFloat NegativeInfinity
Negative infinity, less than any other number.
### NegativeZero
    public static final EFloat NegativeZero
Represents the number negative zero.
### One
    public static final EFloat One
Represents the number 1.
### PositiveInfinity
    public static final EFloat PositiveInfinity
Positive infinity, greater than any other number.
### SignalingNaN
    public static final EFloat SignalingNaN
A not-a-number value that signals an invalid operation flag when it's passed
 as an argument to any arithmetic operation in arbitrary-precision
 binary float.
### Ten
    public static final EFloat Ten
Represents the number 10.
### Zero
    public static final EFloat Zero
Represents the number 0.
## Method Details

### getExponent
    public final EInteger getExponent()
Gets this object's exponent. This object's value will be an integer if the
 exponent is positive or zero.

**Returns:**

* This object's exponent. This object' s value will be an integer if
 the exponent is positive or zero.

### isFinite
    public final boolean isFinite()
Gets a value indicating whether this object is finite (not infinity or NaN).

**Returns:**

* <code>true</code> if this object is finite (not infinity or NaN);
 otherwise, <code>false</code>.

### isNegative
    public final boolean isNegative()
Gets a value indicating whether this object is negative, including negative
 zero.

**Returns:**

* <code>true</code> if this object is negative, including negative zero;
 otherwise, <code>false</code>.

### isZero
    public final boolean isZero()
Gets a value indicating whether this object's value equals 0.

**Returns:**

* <code>true</code> if this object's value equals 0; otherwise, <code>
 false</code>. <code>true</code> if this object' s value equals 0; otherwise,.
 <code>false</code>.

### getMantissa
    public final EInteger getMantissa()
Gets this object's unscaled value, or mantissa, and makes it negative if
 this obejct is negative. If this value is not-a-number (NaN), that
  value's absolute value is the NaN's "payload" (diagnostic
 information).

**Returns:**

* This object' s unscaled value. Will be negative if this object's
 value is negative (including a negative NaN).

### signum
    public final int signum()
Gets this value's sign: -1 if negative; 1 if positive; 0 if zero.

**Returns:**

* This value's sign: -1 if negative; 1 if positive; 0 if zero.

### getUnsignedMantissa
    public final EInteger getUnsignedMantissa()
Gets the absolute value of this object's unscaled value, or mantissa. If
  this value is not-a-number (NaN), that value is the NaN's "payload"
 (diagnostic information).

**Returns:**

* The absolute value of this object's unscaled value.

### Copy
    public EFloat Copy()
Creates a copy of this arbitrary-precision binary number.

**Returns:**

* An arbitrary-precision binary floating-point number.

### Create
    public static EFloat Create​(int mantissaSmall, int exponentSmall)
Creates a number with the value exponent*2^mantissa (significand).

**Parameters:**

* <code>mantissaSmall</code> - Desired value for the mantissa.

* <code>exponentSmall</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision binary floating-point number.

### Create
    public static EFloat Create​(EInteger mantissa, EInteger exponent)
Creates a number with the value exponent*2^mantissa (significand).

**Parameters:**

* <code>mantissa</code> - Desired value for the mantissa.

* <code>exponent</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision binary floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter "mantissa (significand)" or
 <code>exponent</code> is null.

### CreateNaN
    public static EFloat CreateNaN​(EInteger diag)
Creates a not-a-number arbitrary-precision binary floating-point number.

**Parameters:**

* <code>diag</code> - An integer, 0 or greater, to use as diagnostic information
 associated with this object. If none is needed, should be zero. To
 get the diagnostic information from another arbitrary-precision
 binary floating-point number, use that object's <code>
 UnsignedMantissa</code> property.

**Returns:**

* A quiet not-a-number.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>diag</code> is less than 0.

### CreateNaN
    public static EFloat CreateNaN​(EInteger diag, boolean signaling, boolean negative, EContext ctx)
Creates a not-a-number arbitrary-precision binary floating-point number.

**Parameters:**

* <code>diag</code> - An integer, 0 or greater, to use as diagnostic information
 associated with this object. If none is needed, should be zero. To
 get the diagnostic information from another arbitrary-precision
 binary floating-point number, use that object's <code>
 UnsignedMantissa</code> property.

* <code>signaling</code> - Whether the return value will be signaling (true) or quiet
 (false).

* <code>negative</code> - Whether the return value is negative.

* <code>ctx</code> - An arithmetic context to control the precision (in bits) of the
 diagnostic information. The rounding and exponent range of this
 context will be ignored. Can be null. The only flag that can be
 signaled in this context is FlagInvalid, which happens if diagnostic
 information needs to be truncated and too much memory is required to
 do so.

**Returns:**

* An arbitrary-precision binary floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>diag</code> is null.

### FromDouble
    public static EFloat FromDouble​(double dbl)
Creates a binary float from a 64-bit floating-point number. This method
 computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the floating point
 number to a string first.

**Parameters:**

* <code>dbl</code> - The parameter <code>dbl</code> is a 64-bit floating-point number.

**Returns:**

* A binary float with the same value as "dbl".

### FromEInteger
    public static EFloat FromEInteger​(EInteger bigint)
Converts an arbitrary-precision integer to the same value as a binary float.

**Parameters:**

* <code>bigint</code> - An arbitrary-precision integer.

**Returns:**

* An arbitrary-precision binary floating-point number.

### FromSingle
    public static EFloat FromSingle​(float flt)
Creates a binary float from a 32-bit floating-point number. This method
 computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the floating point
 number to a string first.

**Parameters:**

* <code>flt</code> - The parameter <code>flt</code> is a 32-bit binary floating-point
 number.

**Returns:**

* A binary float with the same value as "flt".

### FromString
    public static EFloat FromString​(java.lang.String str, int offset, int length, EContext ctx)
Creates a binary float from a text string that represents a number. Note
 that if the string contains a negative exponent, the resulting value
 might not be exact, in which case the resulting binary float will be
 an approximation of this decimal number's value. <p>The format of
 the string generally consists of:</p> <ul> <li>An optional plus sign
  ("+" , U+002B) or minus sign ("-", U+002D) (if '-' , the value is
 negative.)</li> <li>One or more digits, with a single optional
 decimal point after the first digit and before the last digit.</li>
  <li>Optionally, "E+"/"e+" (positive exponent) or "E-"/"e-" (negative
 exponent) plus one or more digits specifying the exponent.</li></ul>
  <p>The string can also be "-INF", "-Infinity", "Infinity", "INF",
  quiet NaN ("NaN") followed by any number of digits, or signaling NaN
  ("sNaN") followed by any number of digits, all in any combination of
 upper and lower case.</p> <p>All characters mentioned above are the
 corresponding characters in the Basic Latin range. In particular,
 the digits must be the basic digits 0 to 9 (U + 0030 to U + 0039). The
 string is not allowed to contain white space characters, including
 spaces.</p>

**Parameters:**

* <code>str</code> - The parameter <code>str</code> is a text string.

* <code>offset</code> - A zero-based index showing where the desired portion of <code>
 str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

* <code>ctx</code> - The parameter <code>ctx</code> is an EContext object.

**Returns:**

* The parsed number, converted to arbitrary-precision binary
 floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>str</code> 's length, or <code>str</code> ' s
 length minus <code>offset</code> is less than <code>length</code>.

### FromString
    public static EFloat FromString​(java.lang.String str)
Creates a binary float from a text string that represents a number, using an
 unlimited precision context. For more information, see the
 <code>FromString(string, int, int, EContext)</code> method.

**Parameters:**

* <code>str</code> - A text string to convert to a binary float.

**Returns:**

* The parsed number, converted to arbitrary-precision binary
 floating-point number.

### FromString
    public static EFloat FromString​(java.lang.String str, EContext ctx)
Creates a binary float from a text string that represents a number. For more
 information, see the <code>FromString(string, int, int, EContext)</code>
 method.

**Parameters:**

* <code>str</code> - A text string to convert to a binary float.

* <code>ctx</code> - An arithmetic context specifying the precision, rounding, and
 exponent range to apply to the parsed number. Can be null.

**Returns:**

* The parsed number, converted to arbitrary-precision binary
 floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

### FromString
    public static EFloat FromString​(java.lang.String str, int offset, int length)
Creates a binary float from a text string that represents a number. For more
 information, see the <code>FromString(string, int, int, EContext)</code>
 method.

**Parameters:**

* <code>str</code> - The parameter <code>str</code> is a text string.

* <code>offset</code> - A zero-based index showing where the desired portion of <code>
 str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

**Returns:**

* An arbitrary-precision binary floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either "offset" or "length" is less than 0 or
  greater than "str"'s length, or "str"'s length minus "offset" is
  less than "length".

### Max
    public static EFloat Max​(EFloat first, EFloat second, EContext ctx)
Gets the greater value between two binary floating-point numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The larger value of the two numbers.

### Max
    public static EFloat Max​(EFloat first, EFloat second)
Gets the greater value between two binary floating-point numbers.

**Parameters:**

* <code>first</code> - An arbitrary-precision binary floating-point number.

* <code>second</code> - Another arbitrary-precision binary floating-point number.

**Returns:**

* The greater of the two arbitrary-precision numbers.

### MaxMagnitude
    public static EFloat MaxMagnitude​(EFloat first, EFloat second, EContext ctx)
Gets the greater value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Max.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* An arbitrary-precision binary floating-point number.

### MaxMagnitude
    public static EFloat MaxMagnitude​(EFloat first, EFloat second)
Gets the greater value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Max.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* An arbitrary-precision binary floating-point number.

### Min
    public static EFloat Min​(EFloat first, EFloat second, EContext ctx)
Gets the lesser value between two binary floating-point numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The smaller value of the two numbers.

### Min
    public static EFloat Min​(EFloat first, EFloat second)
Gets the lesser value between two binary floating-point numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* An arbitrary-precision binary floating-point number.

### MinMagnitude
    public static EFloat MinMagnitude​(EFloat first, EFloat second, EContext ctx)
Gets the lesser value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Min.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* An arbitrary-precision binary floating-point number.

### MinMagnitude
    public static EFloat MinMagnitude​(EFloat first, EFloat second)
Gets the lesser value between two values, ignoring their signs. If the
 absolute values are equal, has the same effect as Min.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* An arbitrary-precision binary floating-point number.

### PI
    public static EFloat PI​(EContext ctx)
Finds the constant π, the circumference of a circle divided by its diameter.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as π can never be represented exactly.</i>.

**Returns:**

* The constant π rounded to the given precision. Signals FlagInvalid
  and returns not-a-number (NaN) if the parameter "ctx" is null or the
 precision is unlimited (the context's Precision property is 0).

### Abs
    public EFloat Abs()
Finds the absolute value of this object (if it's negative, it becomes
 positive).

**Returns:**

* An arbitrary-precision binary floating-point number. Returns
 signaling NaN if this value is signaling NaN. (In this sense, this
  method is similar to the "copy-abs" operation in the General Decimal
 Arithmetic Specification, except this method does not necessarily
 return a copy of this object.).

### Abs
    public EFloat Abs​(EContext context)
Finds the absolute value of this object (if it's negative, it becomes
 positive).

**Parameters:**

* <code>context</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The absolute value of this object. Signals FlagInvalid and returns
 quiet NaN if this value is signaling NaN.

### Add
    public EFloat Add​(int intValue)

**Parameters:**

* <code>intValue</code> - Not documented yet.

**Returns:**

* An EFloat object.

### Subtract
    public EFloat Subtract​(int intValue)
Subtracts an arbitrary-precision integer from this arbitrary-precision
 integer.

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is a 32-bit signed integer.

**Returns:**

* The difference of the two objects.

### Multiply
    public EFloat Multiply​(int intValue)
Multiplies this instance by the value of an arbitrary-precision // /integer
 object.<p> </p><pre>EInteger result =
  EInteger.FromString("5").Multiply(200);</pre> .

**Parameters:**

* <code>intValue</code> - The parameter <code>intValue</code> is a 32-bit signed integer.

**Returns:**

* The product of the two numbers.

### Divide
    public EFloat Divide​(int intValue)
Divides this instance by the value of an arbitrary-precision integer. The
 result is rounded down (the fractional part is discarded). Except if
 the result is 0, it will be negative if this object is positive and
 the other is negative, or vice versa, and will be positive if both
 are positive or both are negative.

**Parameters:**

* <code>intValue</code> - The divisor.

**Returns:**

* The quotient of the two objects.

**Throws:**

* <code>java.lang.ArithmeticException</code> - Attempted to divide by zero.

### Add
    public EFloat Add​(EFloat otherValue)
Adds this object and another binary float and returns the result.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision binary floating-point number.

**Returns:**

* The sum of the two objects.

### Add
    public EFloat Add​(EFloat otherValue, EContext ctx)
Finds the sum of this object and another object. The result's exponent is
 set to the lower of the exponents of the two operands.

**Parameters:**

* <code>otherValue</code> - The number to add to.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* An arbitrary-precision binary floating-point number.

### compareTo
    public int compareTo​(EFloat other)
Compares the mathematical values of this object and another object,
 accepting NaN values. <p>This method is not consistent with the
 Equals method because two different numbers with the same
 mathematical value, but different exponents, will compare as
 equal.</p> <p>In this method, negative zero and positive zero are
 considered equal.</p> <p>If this object or the other object is a
 quiet NaN or signaling NaN, this method will not trigger an error.
 Instead, NaN will compare greater than any other number, including
 infinity. Two different NaN values will be considered equal.</p>

**Specified by:**

* <code>compareTo</code> in interface <code>java.lang.Comparable&lt;EFloat&gt;</code>

**Parameters:**

* <code>other</code> - An arbitrary-precision binary floating-point number.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
  value or if "other" is null, or 0 if both values are equal.

### CompareToSignal
    public EFloat CompareToSignal​(EFloat other, EContext ctx)
Compares the mathematical values of this object and another object, treating
 quiet NaN as signaling. <p>In this method, negative zero and
 positive zero are considered equal.</p> <p>If this object or the
 other object is a quiet NaN or signaling NaN, this method will
 return a quiet NaN and will signal a FlagInvalid flag.</p>

**Parameters:**

* <code>other</code> - An arbitrary-precision binary floating-point number.

* <code>ctx</code> - An arithmetic context. The precision, rounding, and exponent
 range are ignored. If <code>HasFlags</code> of the context is true, will
 store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null.

**Returns:**

* Quiet NaN if this object or the other object is NaN, or 0 if both
 objects have the same value, or -1 if this object is less than the
 other value, or 1 if this object is greater.

### CompareToTotal
    public int CompareToTotal​(EFloat other, EContext ctx)
Compares the values of this object and another object, imposing a total
 ordering on all possible values. In this method: <ul> <li>For
 objects with the same value, the one with the higher exponent has a
  greater "absolute value".</li> <li>Negative zero is less than
  positive zero.</li> <li>Quiet NaN has a higher "absolute value" than
 signaling NaN. If both objects are quiet NaN or both are signaling
 NaN, the one with the higher diagnostic information has a greater
  "absolute value".</li> <li>NaN has a higher "absolute value" than
  infinity.</li> <li>Infinity has a higher "absolute value" than any
 finite number.</li> <li>Negative numbers are less than positive
 numbers.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision binary float to compare with this one.

* <code>ctx</code> - An arithmetic context. Flags will be set in this context only if
 <code>HasFlags</code> and <code>IsSimplified</code> of the context are true
 and only if an operand needed to be rounded before carrying out the
 operation. Can be null.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.
 Does not signal flags if either value is signaling NaN.

### CompareToTotalMagnitude
    public int CompareToTotalMagnitude​(EFloat other, EContext ctx)
Compares the values of this object and another object, imposing a total
 ordering on all possible values (ignoring their signs). In this
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

* <code>other</code> - An arbitrary-precision binary float to compare with this one.

* <code>ctx</code> - An arithmetic context. Flags will be set in this context only if
 <code>HasFlags</code> and <code>IsSimplified</code> of the context are true
 and only if an operand needed to be rounded before carrying out the
 operation. Can be null.

**Returns:**

* The number 0 if both objects have the same value (ignoring their
 signs), or -1 if this object is less than the other value (ignoring
 their signs), or 1 if this object is greater (ignoring their signs).
 Does not signal flags if either value is signaling NaN.

### CompareToTotal
    public int CompareToTotal​(EFloat other)
Compares the values of this object and another object, imposing a total
 ordering on all possible values. In this method: <ul> <li>For
 objects with the same value, the one with the higher exponent has a
  greater "absolute value".</li> <li>Negative zero is less than
  positive zero.</li> <li>Quiet NaN has a higher "absolute value" than
 signaling NaN. If both objects are quiet NaN or both are signaling
 NaN, the one with the higher diagnostic information has a greater
  "absolute value".</li> <li>NaN has a higher "absolute value" than
  infinity.</li> <li>Infinity has a higher "absolute value" than any
 finite number.</li> <li>Negative numbers are less than positive
 numbers.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision binary float to compare with this one.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.

### CompareToTotalMagnitude
    public int CompareToTotalMagnitude​(EFloat other)
Compares the absolute values of this object and another object, imposing a
 total ordering on all possible values (ignoring their signs). In
 this method: <ul> <li>For objects with the same value, the one with
  the higher exponent has a greater "absolute value".</li>
 <li>Negative zero and positive zero are considered equal.</li>
  <li>Quiet NaN has a higher "absolute value" than signaling NaN. If
 both objects are quiet NaN or both are signaling NaN, the one with
  the higher diagnostic information has a greater "absolute
  value".</li> <li>NaN has a higher "absolute value" than
  infinity.</li> <li>Infinity has a higher "absolute value" than any
 finite number.</li></ul>

**Parameters:**

* <code>other</code> - An arbitrary-precision binary float to compare with this one.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.

### CompareToWithContext
    public EFloat CompareToWithContext​(EFloat other, EContext ctx)
Compares the mathematical values of this object and another object. <p>In
 this method, negative zero and positive zero are considered
 equal.</p> <p>If this object or the other object is a quiet NaN or
 signaling NaN, this method returns a quiet NaN, and will signal a
 FlagInvalid flag if either is a signaling NaN.</p>

**Parameters:**

* <code>other</code> - An arbitrary-precision binary floating-point number.

* <code>ctx</code> - An arithmetic context. The precision, rounding, and exponent
 range are ignored. If <code>HasFlags</code> of the context is true, will
 store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null.

**Returns:**

* Quiet NaN if this object or the other object is NaN, or 0 if both
 objects have the same value, or -1 if this object is less than the
 other value, or 1 if this object is greater.

### CopySign
    public EFloat CopySign​(EFloat other)
Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number. (This method is similar to
  the "copy-sign" operation in the General Decimal Arithmetic
 Specification, except this method does not necessarily return a copy
 of this object.).

**Parameters:**

* <code>other</code> - A number whose sign will be copied.

**Returns:**

* An arbitrary-precision binary floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>other</code> is null.

### Divide
    public EFloat Divide​(EFloat divisor)
Divides this object by another binary float and returns the result. When
 possible, the result will be exact.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* The quotient of the two numbers. Returns infinity if the divisor is
 0 and the dividend is nonzero. Returns not-a-number (NaN) if the
 divisor and the dividend are 0. Returns NaN if the result can't be
 exact because it would have a nonterminating binary expansion.

### Divide
    public EFloat Divide​(EFloat divisor, EContext ctx)
Divides this arbitrary-precision binary float by another arbitrary-precision
 binary floating-point number. The preferred exponent for the result
 is this object's exponent minus the divisor's exponent.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0; or, either <code>ctx</code> is null or <code>
 ctx</code> 's precision is 0, and the result would have a nonterminating
 binary expansion; or, the rounding mode is ERounding.None and the
 result is not exact.

### DivideAndRemainderNaturalScale
    @Deprecated public EFloat[] DivideAndRemainderNaturalScale​(EFloat divisor)
Deprecated.
Renamed to DivRemNaturalScale.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* A 2 element array consisting of the quotient and remainder in that
 order.

### DivideAndRemainderNaturalScale
    @Deprecated public EFloat[] DivideAndRemainderNaturalScale​(EFloat divisor, EContext ctx)
Deprecated.
Renamed to DivRemNaturalScale.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only in
 the division portion of the remainder calculation; as a result, it's
 possible for the remainder to have a higher precision than given in
 this context. Flags will be set on the given context only if the
 context's <code>HasFlags</code> is true and the integer part of the
 division result doesn't fit the precision and exponent range without
 rounding. Can be null, in which the precision is unlimited and no
 additional rounding, other than the rounding down to an integer
 after division, is needed.

**Returns:**

* A 2 element array consisting of the quotient and remainder in that
 order.

### DivideToExponent
    public EFloat DivideToExponent​(EFloat divisor, long desiredExponentSmall, EContext ctx)
Divides two arbitrary-precision binary floating-point numbers, and gives a
 particular exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponentSmall</code> - The desired exponent. A negative number places
 the cutoff point to the right of the usual radix point (so a
 negative number means the number of binary digit places to round
 to). A positive number places the cutoff point to the left of the
 usual radix point.

* <code>ctx</code> - An arithmetic context object to control the rounding mode to use
 if the result must be scaled down to have the same exponent as this
 value. If the precision given in the context is other than 0, calls
 the Quantize method with both arguments equal to the result of the
 operation (and can signal FlagInvalid and return NaN if the result
 doesn't fit the given precision). If <code>HasFlags</code> of the context
 is true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the context defines an exponent range and the desired
 exponent is outside that range. Signals FlagInvalid and returns
 not-a-number (NaN) if the rounding mode is ERounding.None and the
 result is not exact.

### DivideToExponent
    public EFloat DivideToExponent​(EFloat divisor, long desiredExponentSmall, ERounding rounding)
Divides two arbitrary-precision binary floating-point numbers, and gives a
 particular exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponentSmall</code> - The desired exponent. A negative number places
 the cutoff point to the right of the usual radix point (so a
 negative number means the number of binary digit places to round
 to). A positive number places the cutoff point to the left of the
 usual radix point.

* <code>rounding</code> - The rounding mode to use if the result must be scaled down
 to have the same exponent as this value.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the rounding mode is ERounding.None and the result is not
 exact.

### DivideToExponent
    public EFloat DivideToExponent​(EFloat divisor, EInteger exponent, EContext ctx)
Divides two arbitrary-precision binary floating-point numbers, and gives a
 particular exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>exponent</code> - The desired exponent. A negative number places the cutoff
 point to the right of the usual radix point (so a negative number
 means the number of binary digit places to round to). A positive
 number places the cutoff point to the left of the usual radix point.

* <code>ctx</code> - An arithmetic context object to control the rounding mode to use
 if the result must be scaled down to have the same exponent as this
 value. If the precision given in the context is other than 0, calls
 the Quantize method with both arguments equal to the result of the
 operation (and can signal FlagInvalid and return NaN if the result
 doesn't fit the given precision). If <code>HasFlags</code> of the context
 is true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the context defines an exponent range and the desired
 exponent is outside that range. Signals FlagInvalid and returns
 not-a-number (NaN) if the rounding mode is ERounding.None and the
 result is not exact.

### DivideToExponent
    public EFloat DivideToExponent​(EFloat divisor, EInteger desiredExponent, ERounding rounding)
Divides two arbitrary-precision binary floating-point numbers, and gives a
 particular exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponent</code> - The desired exponent. A negative number places the
 cutoff point to the right of the usual radix point (so a negative
 number means the number of binary digit places to round to). A
 positive number places the cutoff point to the left of the usual
 radix point.

* <code>rounding</code> - The rounding mode to use if the result must be scaled down
 to have the same exponent as this value.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Returns not-a-number (NaN) if the divisor and the dividend are 0.
 Returns NaN if the rounding mode is ERounding.None and the result is
 not exact.

### DivideToIntegerNaturalScale
    public EFloat DivideToIntegerNaturalScale​(EFloat divisor)
Divides two arbitrary-precision binary floating-point numbers, and returns
 the integer part of the result, rounded down, with the preferred
 exponent set to this value's exponent minus the divisor's exponent.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* The integer part of the quotient of the two objects. Signals
 FlagDivideByZero and returns infinity if the divisor is 0 and the
 dividend is nonzero. Signals FlagInvalid and returns not-a-number
 (NaN) if the divisor and the dividend are 0.

### DivideToIntegerNaturalScale
    public EFloat DivideToIntegerNaturalScale​(EFloat divisor, EContext ctx)
Divides this object by another object, and returns the integer part of the
 result (which is initially rounded down), with the preferred
 exponent set to this value's exponent minus the divisor's exponent.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision binary floating-point number.

* <code>ctx</code> - The parameter <code>ctx</code> is an EContext object.

**Returns:**

* The integer part of the quotient of the two objects. Signals
 FlagInvalid and returns not-a-number (NaN) if the return value would
 overflow the exponent range. Signals FlagDivideByZero and returns
 infinity if the divisor is 0 and the dividend is nonzero. Signals
 FlagInvalid and returns not-a-number (NaN) if the divisor and the
 dividend are 0. Signals FlagInvalid and returns not-a-number (NaN)
 if the rounding mode is ERounding.None and the result is not exact.

### DivideToIntegerZeroScale
    public EFloat DivideToIntegerZeroScale​(EFloat divisor, EContext ctx)
Divides this object by another object, and returns the integer part of the
 result, with the exponent set to 0.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision. The
 rounding and exponent range settings of this context are ignored. If
 <code>HasFlags</code> of the context is true, will also store the flags
 resulting from the operation (the flags are in addition to the
 pre-existing flags). Can be null, in which case the precision is
 unlimited.

**Returns:**

* The integer part of the quotient of the two objects. The exponent
 will be set to 0. Signals FlagDivideByZero and returns infinity if
 the divisor is 0 and the dividend is nonzero. Signals FlagInvalid
 and returns not-a-number (NaN) if the divisor and the dividend are
 0, or if the result doesn't fit the given precision.

### DivideToSameExponent
    public EFloat DivideToSameExponent​(EFloat divisor, ERounding rounding)
Divides this object by another binary float and returns a result with the
 same exponent as this object (the dividend).

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>rounding</code> - The rounding mode to use if the result must be scaled down
 to have the same exponent as this value.

**Returns:**

* The quotient of the two numbers. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the rounding mode is ERounding.None and the result is not
 exact.

### DivRemNaturalScale
    public EFloat[] DivRemNaturalScale​(EFloat divisor)
Calculates the quotient and remainder using the DivideToIntegerNaturalScale
 and the formula in RemainderNaturalScale.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* A 2 element array consisting of the quotient and remainder in that
 order.

### DivRemNaturalScale
    public EFloat[] DivRemNaturalScale​(EFloat divisor, EContext ctx)
Calculates the quotient and remainder using the DivideToIntegerNaturalScale
 and the formula in RemainderNaturalScale.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only in
 the division portion of the remainder calculation; as a result, it's
 possible for the remainder to have a higher precision than given in
 this context. Flags will be set on the given context only if the
 context's <code>HasFlags</code> is true and the integer part of the
 division result doesn't fit the precision and exponent range without
 rounding. Can be null, in which the precision is unlimited and no
 additional rounding, other than the rounding down to an integer
 after division, is needed.

**Returns:**

* A 2 element array consisting of the quotient and remainder in that
 order.

### equals
    public boolean equals​(EFloat other)
Determines whether this object's mantissa (significand), exponent, and
 properties are equal to those of another object. Not-a-number values
 are considered equal if the rest of their properties are equal.

**Parameters:**

* <code>other</code> - An arbitrary-precision binary floating-point number.

**Returns:**

* <code>true</code> if this object's mantissa (significand) and exponent
 are equal to those of another object; otherwise, <code>false</code>.

### equals
    public boolean equals​(java.lang.Object obj)
Determines whether this object's mantissa (significand), exponent, and
 properties are equal to those of another object and that other
 object is an arbitrary-precision binary floating-point number.
 Not-a-number values are considered equal if the rest of their
 properties are equal.

**Overrides:**

* <code>equals</code> in class <code>java.lang.Object</code>

**Parameters:**

* <code>obj</code> - The parameter <code>obj</code> is an arbitrary object.

**Returns:**

* <code>true</code> if the objects are equal; otherwise, <code>false</code>.

### EqualsInternal
    public boolean EqualsInternal​(EFloat otherValue)
Determines whether this object's mantissa (significand) and exponent are
 equal to those of another object.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision binary floating-point number.

**Returns:**

* <code>true</code> if this object's mantissa (significand) and exponent
 are equal to those of another object; otherwise, <code>false</code>.

### Exp
    public EFloat Exp​(EContext ctx)
Finds e (the base of natural logarithms) raised to the power of this
 object's value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the exponential function's results are generally
 not exact.</i> (Unlike in the General Binary Arithmetic
 Specification, any rounding mode is allowed.).

**Returns:**

* Exponential of this object. If this object's value is 1, returns an
  approximation to " e" within the given precision. Signals
  FlagInvalid and returns not-a-number (NaN) if the parameter "ctx" is
 null or the precision is unlimited (the context's Precision property
 is 0).

### hashCode
    public int hashCode()
Calculates this object's hash code. No application or process IDs are used
 in the hash code calculation.

**Overrides:**

* <code>hashCode</code> in class <code>java.lang.Object</code>

**Returns:**

* A 32-bit signed integer.

### IsInfinity
    public boolean IsInfinity()
Gets a value indicating whether this object is positive or negative
 infinity.

**Returns:**

* <code>true</code> if this object is positive or negative infinity;
 otherwise, <code>false</code>.

### IsNaN
    public boolean IsNaN()
Gets a value indicating whether this object is not a number (NaN).

**Returns:**

* <code>true</code> if this object is not a number (NaN); otherwise, <code>
 false</code>.

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
Gets a value indicating whether this object is a quiet not-a-number value.

**Returns:**

* <code>true</code> if this object is a quiet not-a-number value;
 otherwise, <code>false</code>.

### IsSignalingNaN
    public boolean IsSignalingNaN()
Gets a value indicating whether this object is a signaling not-a-number
 value.

**Returns:**

* <code>true</code> if this object is a signaling not-a-number value;
 otherwise, <code>false</code>.

### Log
    public EFloat Log​(EContext ctx)
Finds the natural logarithm of this object, that is, the power (exponent)
 that e (the base of natural logarithms) must be raised to in order
 to equal this object's value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the ln function's results are generally not
 exact.</i> (Unlike in the General Binary Arithmetic Specification,
 any rounding mode is allowed.).

**Returns:**

* Ln(this object). Signals the flag FlagInvalid and returns NaN if
 this object is less than 0 (the result would be a complex number
 with a real part equal to Ln of this object's absolute value and an
 imaginary part equal to pi, but the return value is still NaN.).
 Signals FlagInvalid and returns not-a-number (NaN) if the parameter
  "ctx" is null or the precision is unlimited (the context's Precision
 property is 0). Signals no flags and returns negative infinity if
 this object's value is 0.

### Log10
    public EFloat Log10​(EContext ctx)
Finds the base-10 logarithm of this object, that is, the power (exponent)
 that the number 10 must be raised to in order to equal this object's
 value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the ln function's results are generally not
 exact.</i> (Unlike in the General Binary Arithmetic Specification,
 any rounding mode is allowed.).

**Returns:**

* Ln(this object)/Ln(10). Signals the flag FlagInvalid and returns
 not-a-number (NaN) if this object is less than 0. Signals
  FlagInvalid and returns not-a-number (NaN) if the parameter "ctx" is
 null or the precision is unlimited (the context's Precision property
 is 0).

### MovePointLeft
    public EFloat MovePointLeft​(int places)
Returns a number similar to this number but with the radix point moved to
 the left.

**Parameters:**

* <code>places</code> - The number of binary digit places to move the radix point to
 the left. If this number is negative, instead moves the radix point
 to the right by this number's absolute value.

**Returns:**

* A number whose exponent is decreased by "places", but not to more
 than 0.

### MovePointLeft
    public EFloat MovePointLeft​(int places, EContext ctx)
Returns a number similar to this number but with the radix point moved to
 the left.

**Parameters:**

* <code>places</code> - The number of binary digit places to move the radix point to
 the left. If this number is negative, instead moves the radix point
 to the right by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is decreased by <code>places</code>, but not to
 more than 0.

### MovePointLeft
    public EFloat MovePointLeft​(EInteger bigPlaces)
Returns a number similar to this number but with the radix point moved to
 the left.

**Parameters:**

* <code>bigPlaces</code> - The number of binary digit places to move the radix point
 to the left. If this number is negative, instead moves the radix
 point to the right by this number's absolute value.

**Returns:**

* A number whose exponent is decreased by "bigPlaces", but not to more
 than 0.

### MovePointLeft
    public EFloat MovePointLeft​(EInteger bigPlaces, EContext ctx)
Returns a number similar to this number but with the radix point moved to
 the left.

**Parameters:**

* <code>bigPlaces</code> - The number of binary digit places to move the radix point
 to the left. If this number is negative, instead moves the radix
 point to the right by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is decreased by <code>bigPlaces</code>, but not
 to more than 0.

### MovePointRight
    public EFloat MovePointRight​(int places)
Returns a number similar to this number but with the radix point moved to
 the right.

**Parameters:**

* <code>places</code> - The number of binary digit places to move the radix point to
 the right. If this number is negative, instead moves the radix point
 to the left by this number's absolute value.

**Returns:**

* A number whose exponent is increased by "places", but not to more
 than 0.

### MovePointRight
    public EFloat MovePointRight​(int places, EContext ctx)
Returns a number similar to this number but with the radix point moved to
 the right.

**Parameters:**

* <code>places</code> - The number of binary digit places to move the radix point to
 the right. If this number is negative, instead moves the radix point
 to the left by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is increased by <code>places</code>, but not to
 more than 0.

### MovePointRight
    public EFloat MovePointRight​(EInteger bigPlaces)
Returns a number similar to this number but with the radix point moved to
 the right.

**Parameters:**

* <code>bigPlaces</code> - The number of binary digit places to move the radix point
 to the right. If this number is negative, instead moves the radix
 point to the left by this number's absolute value.

**Returns:**

* A number whose exponent is increased by "bigPlaces", but not to more
 than 0.

### MovePointRight
    public EFloat MovePointRight​(EInteger bigPlaces, EContext ctx)
Returns a number similar to this number but with the radix point moved to
 the right.

**Parameters:**

* <code>bigPlaces</code> - The number of binary digit places to move the radix point
 to the right. If this number is negative, instead moves the radix
 point to the left by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is increased by <code>bigPlaces</code>, but not
 to more than 0.

### Multiply
    public EFloat Multiply​(EFloat otherValue)
Multiplies two binary floating-point numbers. The resulting exponent will be
 the sum of the exponents of the two binary floating-point numbers.

**Parameters:**

* <code>otherValue</code> - Another binary float.

**Returns:**

* The product of the two binary floating-point numbers.

### Multiply
    public EFloat Multiply​(EFloat op, EContext ctx)
Multiplies two binary floating-point numbers. The resulting scale will be
 the sum of the scales of the two binary floating-point numbers. The
 result's sign is positive if both operands have the same sign, and
 negative if they have different signs.

**Parameters:**

* <code>op</code> - Another binary float.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* An arbitrary-precision binary floating-point number.

### MultiplyAndAdd
    public EFloat MultiplyAndAdd​(EFloat multiplicand, EFloat augend)
Multiplies by one binary float, and then adds another binary float.

**Parameters:**

* <code>multiplicand</code> - The value to multiply.

* <code>augend</code> - The value to add.

**Returns:**

* An arbitrary-precision binary floating-point number.

### MultiplyAndAdd
    public EFloat MultiplyAndAdd​(EFloat op, EFloat augend, EContext ctx)
Multiplies by one value, and then adds another value.

**Parameters:**

* <code>op</code> - The value to multiply.

* <code>augend</code> - The value to add.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed. If
 the precision doesn't indicate a simplified arithmetic, rounding and
 precision.Divide(exponent) adjustment is done only once, namely,
 after multiplying and adding.

**Returns:**

* The result thisValue * multiplicand + augend.

### MultiplyAndSubtract
    public EFloat MultiplyAndSubtract​(EFloat op, EFloat subtrahend, EContext ctx)
Multiplies by one value, and then subtracts another value.

**Parameters:**

* <code>op</code> - The value to multiply.

* <code>subtrahend</code> - The value to subtract.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed. If
 the precision doesn't indicate a simplified arithmetic, rounding and
 precision.Divide(exponent) adjustment is done only once, namely,
 after multiplying and subtracting.

**Returns:**

* The result thisValue * multiplicand - subtrahend.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>op</code> or <code>subtrahend</code>
 is null.

### Negate
    public EFloat Negate()
Gets an object with the same value as this one, but with the sign reversed.

**Returns:**

* An arbitrary-precision binary floating-point number. If this value
 is positive zero, returns negative zero. Returns signaling NaN if
 this value is signaling NaN. (In this sense, this method is similar
  to the "copy-negate" operation in the General Decimal Arithmetic
 Specification, except this method does not necessarily return a copy
 of this object.).

### Negate
    public EFloat Negate​(EContext context)
Returns a binary float with the same value as this object but with the sign
 reversed.

**Parameters:**

* <code>context</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* An arbitrary-precision binary floating-point number. If this value
 is positive zero, returns positive zero. Signals FlagInvalid and
 returns quiet NaN if this value is signaling NaN.

### NextMinus
    public EFloat NextMinus​(EContext ctx)
Finds the largest value that's smaller than the given value.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision and
 exponent range of the result. The rounding mode from this context is
 ignored. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags).

**Returns:**

* Returns the largest value that's less than the given value. Returns
 negative infinity if the result is negative infinity. Signals
  FlagInvalid and returns not-a-number (NaN) if the parameter "ctx" is
  null, the precision is 0, or "ctx" has an unlimited exponent range.

### NextPlus
    public EFloat NextPlus​(EContext ctx)
Finds the smallest value that's greater than the given value.

**Parameters:**

* <code>ctx</code> - An arithmetic context object to control the precision and
 exponent range of the result. The rounding mode from this context is
 ignored. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags).

**Returns:**

* Returns the smallest value that's greater than the given
 value.Signals FlagInvalid and returns not-a-number (NaN) if the
  parameter "ctx" is null, the precision is 0, or "ctx" has an
 unlimited exponent range.

### NextToward
    public EFloat NextToward​(EFloat otherValue, EContext ctx)
Finds the next value that is closer to the other object's value than this
 object's value. Returns a copy of this value with the same sign as
 the other value if both values are equal.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision binary float that the return value
 will approach.

* <code>ctx</code> - An arithmetic context object to control the precision and
 exponent range of the result. The rounding mode from this context is
 ignored. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags).

**Returns:**

* Returns the next value that is closer to the other object' s value
 than this object's value. Signals FlagInvalid and returns NaN if the
 parameter <code>ctx</code> is null, the precision is 0, or <code>ctx</code>
 has an unlimited exponent range.

### Plus
    public EFloat Plus​(EContext ctx)
Rounds this object's value to a given precision, using the given rounding
 mode and range of exponent, and also converts negative zero to
 positive zero.

**Parameters:**

* <code>ctx</code> - A context for controlling the precision, rounding mode, and
 exponent range. Can be null, in which case the precision is
 unlimited and rounding isn't needed.

**Returns:**

* The closest value to this object's value, rounded to the specified
  precision. Returns the same value as this object if "ctx" is null or
 the precision and exponent range are unlimited.

### Pow
    public EFloat Pow​(EFloat exponent, EContext ctx)
Raises this object's value to the given exponent.

**Parameters:**

* <code>exponent</code> - An arbitrary-precision binary float expressing the exponent
 to raise this object's value to.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* This^exponent. Signals the flag FlagInvalid and returns NaN if this
 object and exponent are both 0; or if this value is less than 0 and
 the exponent either has a fractional part or is infinity. Signals
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>
 ctx</code> is null or the precision is unlimited (the context's Precision
 property is 0), and the exponent has a fractional part.

### Pow
    public EFloat Pow​(int exponentSmall, EContext ctx)
Raises this object's value to the given exponent.

**Parameters:**

* <code>exponentSmall</code> - The exponent to raise this object's value to.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* This^exponent. Signals the flag FlagInvalid and returns NaN if this
 object and exponent are both 0.

### Pow
    public EFloat Pow​(int exponentSmall)
Raises this object's value to the given exponent.

**Parameters:**

* <code>exponentSmall</code> - The exponent to raise this object's value to.

**Returns:**

* This^exponent. Returns not-a-number (NaN) if this object and
 exponent are both 0.

### Precision
    public EInteger Precision()
Finds the number of digits in this number's mantissa (significand). Returns
 1 if this value is 0, and 0 if this value is infinity or
 not-a-number (NaN).

**Returns:**

* An arbitrary-precision integer.

### Quantize
    public EFloat Quantize​(EInteger desiredExponent, EContext ctx)
Returns a binary float with the same value but a new exponent. <p>Note that
 this is not always the same as rounding to a given number // /of
 binary digit places, since it can fail if the difference between
 this value's exponent and the desired exponent is too big, depending
 on the maximum precision. If rounding to a number of binary digit
 places is desired, it's better to use the RoundToExponent and
 RoundToIntegral methods instead.</p> <p><b>Remark:</b> This method
 can be used to implement fixed-point // /binary arithmetic, in which
 each binary float has a fixed number of digits after the radix
 point. The following code example returns a fixed-point number with
 up to 20 digits before and exactly 5 digits after the radix
 point:</p> <pre> // After performing arithmetic operations, adjust
 // the number to 5 // digits after the radix point number =
 number.Quantize(EInteger.FromInt32(-5), // five digits after the
 radix point EContext.ForPrecision(25) // 25-digit precision);</pre>
 <p>A // /fixed-point binary arithmetic in which no digits come after
  the radix point (a desired exponent of 0) is considered an "integer
  arithmetic".</p>

**Parameters:**

* <code>desiredExponent</code> - The desired exponent for the result. The exponent is
 the number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting // /from the operation (the flags are in
 addition to the pre-existing flags). Can be null, in which case the
 default rounding mode is HalfEven.

**Returns:**

* A binary float with the same value as this object but with the
 exponent changed. Signals FlagInvalid and returns not-a-number (NaN)
 if this object is infinity, if the rounded result can't fit the
 given precision, or if the context defines an exponent range and the
 given exponent is outside that range.

### Quantize
    public EFloat Quantize​(int desiredExponentInt, EContext ctx)
Returns a binary float with the same value but a new exponent. <p>Note that
 this is not always the same as rounding to a given number // /of
 binary digit places, since it can fail if the difference between
 this value's exponent and the desired exponent is too big, depending
 on the maximum precision. If rounding to a number of binary digit
 places is desired, it's better to use the RoundToExponent and
 RoundToIntegral methods instead.</p> <p><b>Remark:</b> This method
 can be used to implement fixed-point // /binary arithmetic, in which
 each binary float has a fixed number of digits after the radix
 point. The following code example returns a fixed-point number with
 up to 20 digits before and exactly 5 digits after the radix
 point:</p> <pre> // After performing arithmetic operations, adjust
 // the number to 5 digits after the radix point number =
 number.Quantize(-5, // five digits after the radix point
 EContext.ForPrecision(25) // 25-digit precision);</pre> <p>A
 fixed-point binary arithmetic in which no digits // /come after the
  radix point (a desired exponent of 0) is considered an "integer
  arithmetic".</p>

**Parameters:**

* <code>desiredExponentInt</code> - The desired exponent for the result. The exponent
 is the number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting // /from the operation (the flags are in
 addition to the pre-existing flags). Can be null, in which case the
 default rounding mode is HalfEven.

**Returns:**

* A binary float with the same value as this object but with the
 exponent changed. Signals FlagInvalid and returns not-a-number (NaN)
 if this object is infinity, if the rounded result can't fit the
 given precision, or if the context defines an exponent range and the
 given exponent is outside that range.

### Quantize
    public EFloat Quantize​(EFloat otherValue, EContext ctx)
Returns a binary float with the same value as this object but with the same
 exponent as another binary float. <p>Note that this is not always
 the same as rounding to a given number of binary digit places, since
 it can fail if the difference between this value's exponent and the
 desired exponent is too big, depending on the maximum precision. If
 rounding to a number of binary digit places is desired, it's better
 to use the RoundToExponent and RoundToIntegral methods instead.</p>
 <p><b>Remark:</b> This method can be used to implement fixed-point
 binary arithmetic, in which a fixed number of digits come after the
 radix point. A fixed-point binary arithmetic in which no digits come
 after the radix point (a desired exponent of 0) is considered an
  "integer arithmetic" .</p>

**Parameters:**

* <code>otherValue</code> - A binary float containing the desired exponent of the
 result. The mantissa (significand) is ignored. The exponent is the
 number of fractional digits in the result, expressed as a negative
 number. Can also be positive, which eliminates lower-order places
 from the number. For example, -3 means round to the sixteenth
 (10b^-3, 0.0001b), and 3 means round to the sixteen-place (10b^3,
 1000b). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags). Can be null, in which case the default
 rounding mode is HalfEven.

**Returns:**

* A binary float with the same value as this object but with the
 exponent changed. Signals FlagInvalid and returns not-a-number (NaN)
 if the result can't fit the given precision without rounding, or if
 the arithmetic context defines an exponent range and the given
 exponent is outside that range.

### Reduce
    public EFloat Reduce​(EContext ctx)
Returns an object with the same numerical value as this one but with
 trailing zeros removed from its mantissa (significand). For example,
 1.00 becomes 1. <p>If this object's value is 0, changes the exponent
 to 0.</p>

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* This value with trailing zeros removed. Note that if the result has
 a very high exponent and the context says to clamp high exponents,
 there may still be some trailing zeros in the mantissa
 (significand).

### Remainder
    public EFloat Remainder​(EFloat divisor, EContext ctx)
Finds the remainder that results when dividing two arbitrary-precision
 binary floating-point numbers. The remainder is the value that
 remains when the absolute value of this object is divided by the
 absolute value of the other object; the remainder has the same sign
 (positive or negative) as this object's value.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision binary floating-point number.

* <code>ctx</code> - The parameter <code>ctx</code> is an EContext object.

**Returns:**

* The remainder of the two numbers. Signals FlagInvalid and returns
 not-a-number (NaN) if the divisor is 0, or if the result doesn't fit
 the given precision.

### RemainderNoRoundAfterDivide
    public EFloat RemainderNoRoundAfterDivide​(EFloat divisor, EContext ctx)
Finds the remainder that results when dividing two arbitrary-precision
 binary floating-point numbers. The remainder is the value that
 remains when the absolute value of this object is divided by the
 absolute value of the other object; the remainder has the same sign
 (positive or negative) as this object's value.

**Parameters:**

* <code>divisor</code> - An arbitrary-precision binary floating-point number.

* <code>ctx</code> - The parameter <code>ctx</code> is an EContext object.

**Returns:**

* The remainder of the two numbers. Signals FlagInvalid and returns
 not-a-number (NaN) if the divisor is 0, or if the result doesn't fit
 the given precision.

### RemainderNaturalScale
    public EFloat RemainderNaturalScale​(EFloat divisor)
Calculates the remainder of a number by the formula <code>"this" - (("this" /
  "divisor") * "divisor")</code>.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* An arbitrary-precision binary floating-point number.

### RemainderNaturalScale
    public EFloat RemainderNaturalScale​(EFloat divisor, EContext ctx)
Calculates the remainder of a number by the formula "this" - (("this" /
  "divisor") * "divisor").

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result. This context will be used only in
 the division portion of the remainder calculation; as a result, it's
 possible for the return value to have a higher precision than given
 in this context. Flags will be set on the given context only if the
 context's <code>HasFlags</code> is true and the integer part of the
 division result doesn't fit the precision and exponent range without
 rounding. Can be null, in which the precision is unlimited and no
 additional rounding, other than the rounding down to an integer
 after division, is needed.

**Returns:**

* An arbitrary-precision binary floating-point number.

### RemainderNear
    public EFloat RemainderNear​(EFloat divisor, EContext ctx)
Finds the distance to the closest multiple of the given divisor, based on
 the result of dividing this object's value by another object's
 value. <ul> <li>If this and the other object divide evenly, the
 result is 0.</li> <li>If the remainder's absolute value is less than
 half of the divisor's absolute value, the result has the same sign
 as this object and will be the distance to the closest
 multiple.</li> <li>If the remainder's absolute value is more than
 half of the divisor' s absolute value, the result has the opposite
 sign of this object and will be the distance to the closest
 multiple.</li> <li>If the remainder's absolute value is exactly half
 of the divisor's absolute value, the result has the opposite sign of
 this object if the quotient, rounded down, is odd, and has the same
 sign as this object if the quotient, rounded down, is even, and the
 result's absolute value is half of the divisor's absolute
  value.</li></ul> This function is also known as the "IEEE Remainder"
 function.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision. The
 rounding and exponent range settings of this context are ignored
 (the rounding mode is always treated as HalfEven). If <code>
 HasFlags</code> of the context is true, will also store the flags
 resulting from the operation (the flags are in addition to the
 pre-existing flags). Can be null, in which the precision is
 unlimited.

**Returns:**

* The distance of the closest multiple. Signals FlagInvalid and
 returns not-a-number (NaN) if the divisor is 0, or either the result
 of integer division (the quotient) or the remainder wouldn't fit the
 given precision.

### RoundToExponent
    public EFloat RoundToExponent​(EInteger exponent, EContext ctx)
Returns a binary float with the same value as this object but rounded to a
 new exponent if necessary. The resulting number's Exponent property
 will not necessarily be the given exponent; use the Quantize method
 instead to give the result a particular exponent.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A binary float rounded to the closest value representable in the
 given precision. If the result can't fit the precision, additional
 digits are discarded to make it fit. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to the given exponent when
 rounding, and the given exponent is outside of the valid range of
 the arithmetic context.

### RoundToExponent
    public EFloat RoundToExponent​(int exponentSmall, EContext ctx)
Returns a binary float with the same value as this object but rounded to a
 new exponent if necessary. The resulting number's Exponent property
 will not necessarily be the given exponent; use the Quantize method
 instead to give the result a particular exponent.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A binary float rounded to the closest value representable in the
 given precision. If the result can't fit the precision, additional
 digits are discarded to make it fit. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to the given exponent when
 rounding, and the given exponent is outside of the valid range of
 the arithmetic context.

### RoundToExponentExact
    public EFloat RoundToExponentExact​(EInteger exponent, EContext ctx)
Returns a binary float with the same value as this object but rounded to the
 given exponent, and signals an inexact flag if the result would be
 inexact. The resulting number's Exponent property will not
 necessarily be the given exponent; use the Quantize method instead
 to give the result a particular exponent.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A binary float rounded to the closest value representable in the
 given precision. Signals FlagInvalid and returns not-a-number (NaN)
 if the result can't fit the given precision without rounding.
 Signals FlagInvalid and returns not-a-number (NaN) if the arithmetic
 context defines an exponent range, the new exponent must be changed
 to the given exponent when rounding, and the given exponent is
 outside of the valid range of the arithmetic context.

### RoundToExponentExact
    public EFloat RoundToExponentExact​(EInteger exponent, ERounding rounding)
Returns a binary number with the same value as this object but rounded to
 the given exponent. The resulting number's Exponent property will
 not necessarily be the given exponent; use the Quantize method
 instead to give the result a particular exponent.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the eighth
 (10^-1, 1/8), and 3 means round to the eight (2^3, 8). A value of 0
 rounds the number to an integer.

* <code>rounding</code> - Desired mode for rounding this object's value.

**Returns:**

* A binary number rounded to the closest value representable in the
 given precision.

### RoundToExponentExact
    public EFloat RoundToExponentExact​(int exponentSmall, EContext ctx)
Returns a binary float with the same value as this object but rounded to the
 given exponent represented as a 32-bit signed integer, and signals
 an inexact flag if the result would be inexact. The resulting
 number's Exponent property will not necessarily be the given
 exponent; use the Quantize method instead to give the result a
 particular exponent.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A binary float rounded to the closest value representable in the
 given precision. Signals FlagInvalid and returns not-a-number (NaN)
 if the result can't fit the given precision without rounding.
 Signals FlagInvalid and returns not-a-number (NaN) if the arithmetic
 context defines an exponent range, the new exponent must be changed
 to the given exponent when rounding, and the given exponent is
 outside of the valid range of the arithmetic context.

### RoundToIntegerExact
    public EFloat RoundToIntegerExact​(EContext ctx)
Returns a binary float with the same value as this object but rounded to an
 integer, and signals an inexact flag if the result would be inexact.
 The resulting number's Exponent property will not necessarily be 0;
 use the Quantize method instead to give the result an exponent of 0.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A binary float rounded to the closest integer representable in the
 given precision. Signals FlagInvalid and returns not-a-number (NaN)
 if the result can't fit the given precision without rounding.
 Signals FlagInvalid and returns not-a-number (NaN) if the arithmetic
 context defines an exponent range, the new exponent must be changed
 to 0 when rounding, and 0 is outside of the valid range of the
 arithmetic context.

### RoundToIntegerNoRoundedFlag
    public EFloat RoundToIntegerNoRoundedFlag​(EContext ctx)
Returns a binary float with the same value as this object but rounded to an
 integer, without adding the <code>FlagInexact</code> or <code>FlagRounded</code>
 flags. The resulting number's Exponent property will not necessarily
 be 0; use the Quantize method instead to give the result an exponent
 of 0.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags), except that this function will never add
 the <code>FlagRounded</code> and <code>FlagInexact</code> flags (the only
 difference between this and RoundToExponentExact). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A binary float rounded to the closest integer representable in the
 given precision. If the result can't fit the precision, additional
 digits are discarded to make it fit. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to 0 when rounding, and 0 is
 outside of the valid range of the arithmetic context.

### RoundToIntegralExact
    @Deprecated public EFloat RoundToIntegralExact​(EContext ctx)
Deprecated.
Renamed to RoundToIntegerExact.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A binary float rounded to the closest integer representable in the
 given precision. Signals FlagInvalid and returns not-a-number (NaN)
 if the result can't fit the given precision without rounding.
 Signals FlagInvalid and returns not-a-number (NaN) if the arithmetic
 context defines an exponent range, the new exponent must be changed
 to 0 when rounding, and 0 is outside of the valid range of the
 arithmetic context.

### RoundToIntegralNoRoundedFlag
    @Deprecated public EFloat RoundToIntegralNoRoundedFlag​(EContext ctx)
Deprecated.
Renamed to RoundToIntegerNoRoundedFlag.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags), except that this function will never add
 the <code>FlagRounded</code> and <code>FlagInexact</code> flags (the only
 difference between this and RoundToExponentExact). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* A binary float rounded to the closest integer representable in the
 given precision. If the result can't fit the precision, additional
 digits are discarded to make it fit. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to 0 when rounding, and 0 is
 outside of the valid range of the arithmetic context.

### RoundToPrecision
    public EFloat RoundToPrecision​(EContext ctx)
Rounds this object's value to a given precision, using the given rounding
 mode and range of exponent.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The closest value to this object's value, rounded to the specified
  precision. Returns the same value as this object if "ctx" is null or
 the precision and exponent range are unlimited.

### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo​(int places)
Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>places</code> - The parameter <code>places</code> is a 32-bit signed integer.

**Returns:**

* An arbitrary-precision binary floating-point number.

### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo​(int places, EContext ctx)
Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>places</code> - The parameter <code>places</code> is a 32-bit signed integer.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null.

**Returns:**

* An arbitrary-precision binary floating-point number.

### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo​(EInteger bigPlaces)
Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>bigPlaces</code> - An arbitrary-precision integer.

**Returns:**

* A number whose exponent is increased by "bigPlaces".

### ScaleByPowerOfTwo
    public EFloat ScaleByPowerOfTwo​(EInteger bigPlaces, EContext ctx)
Returns a number similar to this number but with its scale adjusted.

**Parameters:**

* <code>bigPlaces</code> - An arbitrary-precision integer.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null.

**Returns:**

* An arbitrary-precision binary floating-point number.

### Sqrt
    public EFloat Sqrt​(EContext ctx)
Finds the square root of this object's value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the square root function's results are generally
 not exact for many inputs.</i> (Unlike in the General Binary
 Arithmetic Specification, any rounding mode is allowed.).

**Returns:**

* The square root. Signals the flag FlagInvalid and returns NaN if
 this object is less than 0 (the square root would be a complex
 number, but the return value is still NaN). Signals FlagInvalid and
  returns not-a-number (NaN) if the parameter "ctx" is null or the
 precision is unlimited (the context's Precision property is 0).

### SquareRoot
    @Deprecated public EFloat SquareRoot​(EContext ctx)
Deprecated.
Renamed to Sqrt.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the square root function's results are generally
 not exact for many inputs.</i> (Unlike in the General Binary
 Arithmetic Specification, any rounding mode is allowed.).

**Returns:**

* The square root. Signals the flag FlagInvalid and returns NaN if
 this object is less than 0 (the square root would be a complex
 number, but the return value is still NaN). Signals FlagInvalid and
  returns not-a-number (NaN) if the parameter "ctx" is null or the
 precision is unlimited (the context's Precision property is 0).

### Subtract
    public EFloat Subtract​(EFloat otherValue)
Subtracts an arbitrary-precision binary float from this instance and returns
 the result.

**Parameters:**

* <code>otherValue</code> - The number to subtract from this instance's value.

**Returns:**

* The difference of the two objects.

### Subtract
    public EFloat Subtract​(EFloat otherValue, EContext ctx)
Subtracts an arbitrary-precision binary float from this instance.

**Parameters:**

* <code>otherValue</code> - The number to subtract from this instance's value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* An arbitrary-precision binary floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>otherValue</code> is null.

### ToDouble
    public double ToDouble()
Converts this value to a 64-bit floating-point number.

**Returns:**

* This number, converted to a 64-bit floating-point number.

### ToEDecimal
    public EDecimal ToEDecimal()
Converts this value to an arbitrary-precision decimal number.

**Returns:**

* This number, converted to an arbitrary-precision decimal number.

### ToEInteger
    public EInteger ToEInteger()
Converts this value to an arbitrary-precision integer. Any fractional part
 of this value will be discarded when converting to an
 arbitrary-precision integer.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or
 not-a-number (NaN).

### ToEIntegerExact
    @Deprecated public EInteger ToEIntegerExact()
Deprecated.
Renamed to ToEIntegerIfExact.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or
 not-a-number (NaN).

### ToEIntegerIfExact
    public EInteger ToEIntegerIfExact()
Converts this value to an arbitrary-precision integer, checking whether the
 value contains a fractional part.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or
 not-a-number (NaN).

### ToEngineeringString
    public java.lang.String ToEngineeringString()
Converts this value to an arbitrary-precision decimal number, then returns
 the value of that decimal's ToEngineeringString method.

**Returns:**

* A text string.

### ToExtendedDecimal
    @Deprecated public EDecimal ToExtendedDecimal()
Deprecated.
Renamed to ToEDecimal.

**Returns:**

* An arbitrary-precision decimal number.

### ToPlainString
    public java.lang.String ToPlainString()
Converts this value to a string, but without exponential notation.

**Returns:**

* A text string.

### ToShortestString
    public java.lang.String ToShortestString​(EContext ctx)
Returns a string representation of this number's value after rounding to the
 given precision (using the given arithmetic context). If the number
 after rounding is neither infinity nor not-a-number (NaN), returns
 the shortest decimal form (in terms of nonzero decimal digits) of
 this number's value that results in the rounded number after the
 decimal form is converted to binary floating-point format (using the
 given arithmetic context).

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision (in bits), rounding,
 and exponent range of the rounded number. If <code>HasFlags</code> of the
 context is true, will also store the flags resulting from the
 operation (the flags are in addition to the pre-existing flags). Can
 be null. If this parameter is null or defines no maximum precision,
 returns the same value as the toString() method.

**Returns:**

* Shortest decimal form of this number's value for the given
 arithmetic context. The text string will be in exponential notation
 if the number's first nonzero decimal digit is more than five digits
 after the decimal point, or if the number's exponent is greater than
 0 and its value is 10, 000, 000 or greater.

### ToSingle
    public float ToSingle()
Converts this value to its closest equivalent as 32-bit floating-point
 number. The half-even rounding mode is used. <p>If this value is a
 NaN, sets the high bit of the 32-bit floating point number's
 significand area for a quiet NaN, and clears it for a signaling NaN.
 Then the other bits of the significand area are set to the lowest
 bits of this object's unsigned mantissa (significand), and the
 next-highest bit of the significand area is set if those bits are
 all zeros and this is a signaling NaN. Unfortunately, in the.getNET()
 implementation, the return value of this method may be a quiet NaN
 even if a signaling NaN would otherwise be generated.</p>

**Returns:**

* The closest 32-bit binary floating-point number to this value. The
 return value can be positive infinity or negative infinity if this
 value exceeds the range of a 32-bit floating point number.

### toString
    public java.lang.String toString()
Converts this number's value to a text string.

**Overrides:**

* <code>toString</code> in class <code>java.lang.Object</code>

**Returns:**

* A string representation of this object. The value is converted to
 decimal and the decimal form of this number's value is returned. The
 text string will be in exponential notation if the converted
 number's scale is positive or if the number's first nonzero decimal
 digit is more than five digits after the decimal point.

### Ulp
    public EFloat Ulp()
Returns the unit in the last place. The mantissa (significand) will be 1 and
 the exponent will be this number's exponent. Returns 1 with an
 exponent of 0 if this number is infinity or not-a-number (NaN).

**Returns:**

* An arbitrary-precision binary floating-point number.

### ToByteChecked
    public byte ToByteChecked()
Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) after truncating to an integer.

**Returns:**

* This number's value, truncated to a byte (from 0 to 255).

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or
 the truncated integer is less than 0 or greater than 255.

### ToByteUnchecked
    public byte ToByteUnchecked()
Truncates this number's value to an integer and returns the
 least-significant bits of its two's-complement form as a byte (from
 0 to 255).

**Returns:**

* This number, converted to a byte (from 0 to 255). Returns 0 if this
 value is infinity or not-a-number.

### ToByteIfExact
    public byte ToByteIfExact()
Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) without rounding to a different numerical
 value.

**Returns:**

* This number's value as a byte (from 0 to 255).

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than 0 or greater than 255.

### FromByte
    public static EFloat FromByte​(byte inputByte)
Converts a byte (from 0 to 255) to an arbitrary-precision binary
 floating-point number.

**Parameters:**

* <code>inputByte</code> - The number to convert as a byte (from 0 to 255).

**Returns:**

* This number's value as an arbitrary-precision binary floating-point
 number.

### ToInt16Checked
    public short ToInt16Checked()
Converts this number's value to a 16-bit signed integer if it can fit in a
 16-bit signed integer after truncating to an integer.

**Returns:**

* This number's value, truncated to a 16-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or
 the truncated integer is less than -32768 or greater than 32767.

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

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -32768 or greater than 32767.

### FromInt16
    public static EFloat FromInt16​(short inputInt16)
Converts a 16-bit signed integer to an arbitrary-precision binary
 floating-point number.

**Parameters:**

* <code>inputInt16</code> - The number to convert as a 16-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision binary floating-point
 number.

### ToInt32Checked
    public int ToInt32Checked()
Converts this number's value to a 32-bit signed integer if it can fit in a
 32-bit signed integer after truncating to an integer.

**Returns:**

* This number's value, truncated to a 32-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or
 the truncated integer is less than -2147483648 or greater than
 2147483647.

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

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -2147483648 or greater than
 2147483647.

### FromBoolean
    public static EFloat FromBoolean​(boolean boolValue)
Converts a boolean value (either true or false) to an arbitrary-precision
 binary float.

**Parameters:**

* <code>boolValue</code> - Either true or false.

**Returns:**

* The number 1 if <code>boolValue</code> is true, otherwise, 0.

### FromInt32
    public static EFloat FromInt32​(int inputInt32)
Converts a 32-bit signed integer to an arbitrary-precision binary
 floating-point number.

**Parameters:**

* <code>inputInt32</code> - The number to convert as a 32-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision binary floating-point
 number.

### ToInt64Checked
    public long ToInt64Checked()
Converts this number's value to a 64-bit signed integer if it can fit in a
 64-bit signed integer after truncating to an integer.

**Returns:**

* This number's value, truncated to a 64-bit signed integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, or
 the truncated integer is less than -9223372036854775808 or greater
 than 9223372036854775807.

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

* <code>java.lang.ArithmeticException</code> - This value is infinity or not-a-number, is not
 an exact integer, or is less than -9223372036854775808 or greater
 than 9223372036854775807.

### FromInt64
    public static EFloat FromInt64​(long inputInt64)
Converts a 64-bit signed integer to an arbitrary-precision binary
 floating-point number.

**Parameters:**

* <code>inputInt64</code> - The number to convert as a 64-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision binary floating-point
 number.
