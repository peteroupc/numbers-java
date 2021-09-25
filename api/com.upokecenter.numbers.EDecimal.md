# com.upokecenter.numbers.EDecimal

## Fields

* `static EDecimal NaN`<br>
 A not-a-number value.
* `static EDecimal NegativeInfinity`<br>
 Negative infinity, less than any other number.
* `static EDecimal NegativeZero`<br>
 Represents the number negative zero.
* `static EDecimal One`<br>
 Represents the number 1.
* `static EDecimal PositiveInfinity`<br>
 Positive infinity, greater than any other number.
* `static EDecimal SignalingNaN`<br>
 A not-a-number value that signals an invalid operation flag when it's passed
 as an argument to any arithmetic operation in arbitrary-precision
 decimal.
* `static EDecimal Ten`<br>
 Represents the number 10.
* `static EDecimal Zero`<br>
 Represents the number 0.

## Methods

* `EDecimal Abs()`<br>
 Finds the absolute value of this object (if it's negative, it becomes
 positive).
* `EDecimal Abs​(EContext context)`<br>
 Finds the absolute value of this object (if it's negative, it becomes
 positive).
* `EDecimal Add​(int intValue)`<br>
 Adds this arbitrary-precision decimal floating-point number and a 32-bit
 signed integer and returns the result.
* `EDecimal Add​(long longValue)`<br>
 Adds this arbitrary-precision decimal floating-point number and a 64-bit
 signed integer and returns the result.
* `EDecimal Add​(EDecimal otherValue)`<br>
 Adds this arbitrary-precision decimal floating-point number and another
 arbitrary-precision decimal floating-point number and returns the
 result.
* `EDecimal Add​(EDecimal otherValue,
EContext ctx)`<br>
 Adds this arbitrary-precision decimal floating-point number and another
 arbitrary-precision decimal floating-point number and returns the
 result.
* `int compareTo​(int intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int compareTo​(long intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int compareTo​(EDecimal other)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int CompareToBinary​(EFloat other)`<br>
 Compares an arbitrary-precision binary floating-point number with this
 instance.
* `EDecimal CompareToSignal​(EDecimal other,
EContext ctx)`<br>
 Compares the mathematical values of this object and another object, treating
 quiet NaN as signaling.
* `int CompareToTotal​(EDecimal other)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values.
* `int CompareToTotal​(EDecimal other,
EContext ctx)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values.
* `int CompareToTotalMagnitude​(EDecimal other)`<br>
 Compares the absolute values of this object and another object, imposing a
 total ordering on all possible values (ignoring their signs).
* `int CompareToTotalMagnitude​(EDecimal other,
EContext ctx)`<br>
 Compares the values of this object and another object, imposing a total
 ordering on all possible values (ignoring their signs).
* `int CompareToValue​(int intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int CompareToValue​(long intOther)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `int CompareToValue​(EDecimal other)`<br>
 Compares the mathematical values of this object and another object,
 accepting NaN values.
* `EDecimal CompareToWithContext​(EDecimal other,
EContext ctx)`<br>
 Compares the mathematical values of this object and another object.
* `EDecimal Copy()`<br>
 Creates a copy of this arbitrary-precision binary number.
* `EDecimal CopySign​(EDecimal other)`<br>
 Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number.
* `static EDecimal Create​(int mantissaSmall,
int exponentSmall) exponent*10^significand`<br>
 Returns a number with the value exponent*10^significand.
* `static EDecimal Create​(long mantissaLong,
int exponentSmall) exponent*10^significand`<br>
 Creates a number with the value exponent*10^significand.
* `static EDecimal Create​(long mantissaLong,
long exponentLong) exponent*10^significand`<br>
 Creates a number with the value exponent*10^significand.
* `static EDecimal Create​(EInteger mantissa,
int exponentSmall) exponent*10^significand`<br>
 Creates a number with the value exponent*10^significand.
* `static EDecimal Create​(EInteger mantissa,
long exponentLong) exponent*10^significand`<br>
 Creates a number with the value exponent*10^significand.
* `static EDecimal Create​(EInteger mantissa,
EInteger exponent) exponent*10^significand`<br>
 Creates a number with the value exponent*10^significand.
* `static EDecimal CreateNaN​(EInteger diag)`<br>
 Creates a not-a-number arbitrary-precision decimal number.
* `static EDecimal CreateNaN​(EInteger diag,
boolean signaling,
boolean negative,
EContext ctx)`<br>
 Creates a not-a-number arbitrary-precision decimal number.
* `EDecimal Decrement()`<br>
 Returns one subtracted from this arbitrary-precision decimal number.
* `EDecimal Divide​(int intValue)`<br>
 Divides this arbitrary-precision decimal floating-point number by a 32-bit
 signed integer and returns the result; returns NaN instead if the
 result would have a nonterminating decimal expansion (including 1/3,
 1/12, 1/7, 2/3, and so on); if this is not desired, use
 DivideToExponent, or use the Divide overload that takes an EContext.
* `EDecimal Divide​(long longValue)`<br>
 Divides this arbitrary-precision decimal floating-point number by a 64-bit
 signed integer and returns the result; returns NaN instead if the
 result would have a nonterminating decimal expansion (including 1/3,
 1/12, 1/7, 2/3, and so on); if this is not desired, use
 DivideToExponent, or use the Divide overload that takes an EContext.
* `EDecimal Divide​(EDecimal divisor)`<br>
 Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result; returns NaN instead if the result would have a
 nonterminating decimal expansion (including 1/3, 1/12, 1/7, 2/3, and
 so on); if this is not desired, use DivideToExponent, or use the
 Divide overload that takes an EContext.
* `EDecimal Divide​(EDecimal divisor,
EContext ctx)`<br>
 Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result.
* `EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EDecimal[] DivideAndRemainderNaturalScale​(EDecimal divisor,
EContext ctx)`<br>
 Deprecated.
Renamed to DivRemNaturalScale.
 Renamed to DivRemNaturalScale.
* `EDecimal DivideToExponent​(EDecimal divisor,
int desiredExponentInt)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent​(EDecimal divisor,
int desiredExponentInt,
EContext ctx)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent​(EDecimal divisor,
int desiredExponentInt,
ERounding rounding)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent​(EDecimal divisor,
long desiredExponentSmall)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 64-bit signed integer) to the result, using
 the half-even rounding mode.
* `EDecimal DivideToExponent​(EDecimal divisor,
long desiredExponentSmall,
EContext ctx)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToExponent​(EDecimal divisor,
long desiredExponentSmall,
ERounding rounding)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToExponent​(EDecimal divisor,
EInteger exponent)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result, using the half-even rounding mode.
* `EDecimal DivideToExponent​(EDecimal divisor,
EInteger exponent,
EContext ctx)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToExponent​(EDecimal divisor,
EInteger desiredExponent,
ERounding rounding)`<br>
 Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.
* `EDecimal DivideToIntegerNaturalScale​(EDecimal divisor)`<br>
 Divides two arbitrary-precision decimal numbers, and returns the integer
 part of the result, rounded down, with the preferred exponent set to
 this value's exponent minus the divisor's exponent.
* `EDecimal DivideToIntegerNaturalScale​(EDecimal divisor,
EContext ctx)`<br>
 Divides this object by another object, and returns the integer part of the
 result (which is initially rounded down), with the preferred
 exponent set to this value's exponent minus the divisor's exponent.
* `EDecimal DivideToIntegerZeroScale​(EDecimal divisor,
EContext ctx)`<br>
 Divides this object by another object, and returns the integer part of the
 result, with the exponent set to 0.
* `EDecimal DivideToSameExponent​(EDecimal divisor,
ERounding rounding)`<br>
 Divides this object by another decimal number and returns a result with the
 same exponent as this object (the dividend).
* `EDecimal[] DivRemNaturalScale​(EDecimal divisor)`<br>
 Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns a
 two-item array containing the result of the division and the
 remainder, in that order.
* `EDecimal[] DivRemNaturalScale​(EDecimal divisor,
EContext ctx)`<br>
 Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns a
 two-item array containing the result of the division and the
 remainder, in that order.
* `boolean equals​(EDecimal other)`<br>
 Determines whether this object's significand, exponent, and properties are
 equal to those of another object.
* `boolean equals​(java.lang.Object obj)`<br>
 Determines whether this object's significand, exponent, and properties are
 equal to those of another object and that other object is an
 arbitrary-precision decimal number.
* `EDecimal Exp​(EContext ctx)`<br>
 Finds e (the base of natural logarithms) raised to the power of this
 object's value.
* `EDecimal ExpM1​(EContext ctx)`<br>
 Finds e (the base of natural logarithms) raised to the power of this
 object's value, and subtracts the result by 1 and returns the final
 result, in a way that avoids loss of precision if the true result is
 very close to 0.
* `static EDecimal FromBoolean​(boolean boolValue)`<br>
 Converts a boolean value (true or false) to an arbitrary-precision decimal
 number.
* `static EDecimal FromByte​(byte inputByte)`<br>
 Converts a byte (from 0 to 255) to an arbitrary-precision decimal number.
* `static EDecimal FromDouble​(double dbl)`<br>
 Creates an arbitrary-precision decimal number from a 64-bit binary
 floating-point number.
* `static EDecimal FromDoubleBits​(long dblBits)`<br>
 Creates an arbitrary-precision decimal number from a 64-bit binary
 floating-point number, encoded in the IEEE 754 binary64 format.
* `static EDecimal FromEFloat​(EFloat bigfloat)`<br>
 Creates an arbitrary-precision decimal number from an arbitrary-precision
 binary floating-point number.
* `static EDecimal FromEInteger​(EInteger bigint)`<br>
 Converts an arbitrary-precision integer to an arbitrary precision decimal.
* `static EDecimal FromExtendedFloat​(EFloat ef)`<br>
 Deprecated.
Renamed to FromEFloat.
 Renamed to FromEFloat.
* `static EDecimal FromHalfBits​(short value)`<br>
 Creates a decimal floating-point number from a binary floating-point number
 encoded in the IEEE 754 binary16 format (also known as a
  "half-precision" floating-point number).
* `static EDecimal FromInt16​(short inputInt16)`<br>
 Converts a 16-bit signed integer to an arbitrary-precision decimal number.
* `static EDecimal FromInt32​(int valueSmaller)`<br>
 Creates an arbitrary-precision decimal number from a 32-bit signed integer.
* `static EDecimal FromInt64​(long valueSmall)`<br>
 Creates an arbitrary-precision decimal number from a 64-bit signed integer.
* `static EDecimal FromInt64AsUnsigned​(long longerValue)`<br>
 Converts an unsigned integer expressed as a 64-bit signed integer to an
 arbitrary-precision decimal number.
* `static EDecimal FromSingle​(float flt)`<br>
 Creates an arbitrary-precision decimal number from a 32-bit binary
 floating-point number.
* `static EDecimal FromSingleBits​(int value)`<br>
 Creates an arbitrary-precision decimal number from a 32-bit binary
 floating-point number encoded in the IEEE 754 binary32 format.
* `static EDecimal FromString​(byte[] bytes)`<br>
 Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number.
* `static EDecimal FromString​(byte[] bytes,
int offset,
int length)`<br>
 Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number.
* `static EDecimal FromString​(byte[] bytes,
int offset,
int length,
EContext ctx)`<br>
 Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number.
* `static EDecimal FromString​(byte[] bytes,
EContext ctx)`<br>
 Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number.
* `static EDecimal FromString​(char[] chars) char`<br>
 Creates an arbitrary-precision decimal number from a sequence of char
 s that represents a number.
* `static EDecimal FromString​(char[] chars,
int offset,
int length) char`<br>
 Creates an arbitrary-precision decimal number from a sequence of char
 s that represents a number.
* `static EDecimal FromString​(char[] chars,
int offset,
int length,
EContext ctx) char`<br>
 Creates an arbitrary-precision decimal number from a sequence of
 char s that represents a number.
* `static EDecimal FromString​(char[] chars,
EContext ctx) char`<br>
 Creates an arbitrary-precision decimal number from a sequence of char
 s that represents a number.
* `static EDecimal FromString​(java.lang.String str)`<br>
 Creates an arbitrary-precision decimal number from a text string that
 represents a number.
* `static EDecimal FromString​(java.lang.String str,
int offset,
int length)`<br>
 Creates an arbitrary-precision decimal number from a text string that
 represents a number.
* `static EDecimal FromString​(java.lang.String str,
int offset,
int length,
EContext ctx)`<br>
 Creates an arbitrary-precision decimal number from a text string that
 represents a number.
* `static EDecimal FromString​(java.lang.String str,
EContext ctx)`<br>
 Creates an arbitrary-precision decimal number from a text string that
 represents a number.
* `EInteger getExponent()`<br>
 Gets this object's exponent.
* `EInteger getMantissa()`<br>
 Gets this object's unscaled value, or significand, and makes it negative if
 this object is negative.
* `EInteger getUnsignedMantissa()`<br>
 Gets the absolute value of this object's unscaled value, or significand.
* `int hashCode()`<br>
 Calculates this object's hash code.
* `EDecimal Increment()`<br>
 Returns one added to this arbitrary-precision decimal number.
* `boolean isFinite()`<br>
 Gets a value indicating whether this object is finite (not infinity or NaN).
* `boolean IsInfinity()`<br>
 Gets a value indicating whether this object is positive or negative
 infinity.
* `boolean IsInteger()`<br>
 Returns whether this object's value is an integer.
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
* `EDecimal Log​(EContext ctx)`<br>
 Finds the natural logarithm of this object, that is, the power (exponent)
 that e (the base of natural logarithms) must be raised to in order
 to equal this object's value.
* `EDecimal Log10​(EContext ctx)`<br>
 Finds the base-10 logarithm of this object, that is, the power (exponent)
 that the number 10 must be raised to in order to equal this object's
 value.
* `EDecimal Log1P​(EContext ctx)`<br>
 Adds 1 to this object's value and finds the natural logarithm of the result,
 in a way that avoids loss of precision when this object's value is
 between 0 and 1.
* `EDecimal LogN​(EDecimal baseValue,
EContext ctx)`<br>
 Finds the base-N logarithm of this object, that is, the power (exponent)
 that the number N must be raised to in order to equal this object's
 value.
* `static EDecimal Max​(EDecimal first,
EDecimal second)`<br>
 Gets the greater value between two decimal numbers.
* `static EDecimal Max​(EDecimal first,
EDecimal second,
EContext ctx)`<br>
 Gets the greater value between two decimal numbers.
* `static EDecimal MaxMagnitude​(EDecimal first,
EDecimal second)`<br>
 Gets the greater value between two values, ignoring their signs.
* `static EDecimal MaxMagnitude​(EDecimal first,
EDecimal second,
EContext ctx)`<br>
 Gets the greater value between two values, ignoring their signs.
* `static EDecimal Min​(EDecimal first,
EDecimal second)`<br>
 Gets the lesser value between two decimal numbers.
* `static EDecimal Min​(EDecimal first,
EDecimal second,
EContext ctx)`<br>
 Gets the lesser value between two decimal numbers.
* `static EDecimal MinMagnitude​(EDecimal first,
EDecimal second)`<br>
 Gets the lesser value between two values, ignoring their signs.
* `static EDecimal MinMagnitude​(EDecimal first,
EDecimal second,
EContext ctx)`<br>
 Gets the lesser value between two values, ignoring their signs.
* `EDecimal MovePointLeft​(int places)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointLeft​(int places,
EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointLeft​(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointLeft​(EInteger bigPlaces,
EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the left.
* `EDecimal MovePointRight​(int places)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal MovePointRight​(int places,
EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal MovePointRight​(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal MovePointRight​(EInteger bigPlaces,
EContext ctx)`<br>
 Returns a number similar to this number but with the decimal point moved to
 the right.
* `EDecimal Multiply​(int intValue)`<br>
 Multiplies this arbitrary-precision decimal floating-point number by a
 32-bit signed integer and returns the result.
* `EDecimal Multiply​(long longValue)`<br>
 Multiplies this arbitrary-precision decimal floating-point number by a
 64-bit signed integer and returns the result.
* `EDecimal Multiply​(EDecimal otherValue)`<br>
 Multiplies this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result.
* `EDecimal Multiply​(EDecimal op,
EContext ctx)`<br>
 Multiplies this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result.
* `EDecimal MultiplyAndAdd​(EDecimal multiplicand,
EDecimal augend)`<br>
 Multiplies by one decimal number, and then adds another decimal number.
* `EDecimal MultiplyAndAdd​(EDecimal op,
EDecimal augend,
EContext ctx)`<br>
 Multiplies by one value, and then adds another value.
* `EDecimal MultiplyAndSubtract​(EDecimal op,
EDecimal subtrahend,
EContext ctx)`<br>
 Multiplies by one value, and then subtracts another value.
* `EDecimal Negate()`<br>
 Gets an object with the same value as this one, but with the sign reversed.
* `EDecimal Negate​(EContext context)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but with the sign reversed.
* `EDecimal NextMinus​(EContext ctx)`<br>
 Finds the largest value that's smaller than the given value.
* `EDecimal NextPlus​(EContext ctx)`<br>
 Finds the smallest value that's greater than the given value.
* `EDecimal NextToward​(EDecimal otherValue,
EContext ctx)`<br>
 Finds the next value that is closer to the other object's value than this
 object's value.
* `static EDecimal PI​(EContext ctx)`<br>
 Finds the constant π, the circumference of a circle divided by its diameter.
* `EDecimal Plus​(EContext ctx)`<br>
 Rounds this object's value to a given precision, using the given rounding
 mode and range of exponent, and also converts negative zero to
 positive zero.
* `EDecimal Pow​(int exponentSmall)`<br>
 Raises this object's value to the given exponent.
* `EDecimal Pow​(int exponentSmall,
EContext ctx)`<br>
 Raises this object's value to the given exponent.
* `EDecimal Pow​(EDecimal exponent)`<br>
 Raises this object's value to the given exponent, using unlimited precision.
* `EDecimal Pow​(EDecimal exponent,
EContext ctx)`<br>
 Raises this object's value to the given exponent.
* `EInteger Precision()`<br>
 Finds the number of digits in this number's significand.
* `EDecimal PreRound​(EContext ctx)`<br>
 Returns a number in which the value of this object is rounded to fit the
 maximum precision allowed if it has more significant digits than the
 maximum precision.
* `EDecimal Quantize​(int desiredExponentInt,
EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value but a new
 exponent.
* `EDecimal Quantize​(int desiredExponentInt,
ERounding rounding)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 one but a new exponent.
* `EDecimal Quantize​(EDecimal otherValue,
EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but with the same exponent as another decimal number.
* `EDecimal Quantize​(EInteger desiredExponent,
EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value but a new
 exponent.
* `EDecimal Reduce​(EContext ctx)`<br>
 Returns an object with the same numerical value as this one but with
 trailing zeros removed from its significand.
* `EDecimal Remainder​(EDecimal divisor,
EContext ctx)`<br>
 Returns the remainder that would result when this arbitrary-precision
 decimal floating-point number is divided by another
 arbitrary-precision decimal floating-point number.
* `EDecimal RemainderNaturalScale​(EDecimal divisor) "this" - (("this" /
  "divisor") * "divisor")`<br>
 Calculates the remainder of a number by the formula "this" - (("this" /
  "divisor") * "divisor").
* `EDecimal RemainderNaturalScale​(EDecimal divisor,
EContext ctx)`<br>
 Calculates the remainder of a number by the formula "this" - (("this" /
  "divisor") * "divisor").
* `EDecimal RemainderNear​(EDecimal divisor,
EContext ctx)`<br>
 Finds the distance to the closest multiple of the given divisor, based on
 the result of dividing this object's value by another object's
 value.
* `EDecimal RemainderNoRoundAfterDivide​(EDecimal divisor,
EContext ctx)`<br>
 Finds the remainder that results when dividing two arbitrary-precision
 decimal numbers, except the intermediate division is not adjusted to
 fit the precision of the given arithmetic context.
* `EDecimal RoundToExponent​(int exponentSmall)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the
 HalfEven rounding mode.
* `EDecimal RoundToExponent​(int exponentSmall,
EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary.
* `EDecimal RoundToExponent​(int exponentSmall,
ERounding rounding)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary.
* `EDecimal RoundToExponent​(EInteger exponent)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the
 HalfEven rounding mode.
* `EDecimal RoundToExponent​(EInteger exponent,
EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary.
* `EDecimal RoundToExponent​(EInteger exponent,
ERounding rounding)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the given
 rounding mode.
* `EDecimal RoundToExponentExact​(int exponentSmall,
EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as a 32-bit
 signed integer, and signals an inexact flag if the result would be
 inexact.
* `EDecimal RoundToExponentExact​(int exponentSmall,
ERounding rounding)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as a 32-bit
 signed integer, and signals an inexact flag if the result would be
 inexact.
* `EDecimal RoundToExponentExact​(EInteger exponent,
EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as an
 arbitrary-precision integer, and signals an inexact flag if the
 result would be inexact.
* `EDecimal RoundToIntegerExact​(EContext ctx)`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to an integer, and signals an inexact flag if the
 result would be inexact.
* `EDecimal RoundToIntegerNoRoundedFlag​(EContext ctx) FlagInexact FlagRounded`<br>
 Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to an integer, without adding the
 FlagInexact or FlagRounded flags.
* `EDecimal RoundToIntegralExact​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerExact.
 Renamed to RoundToIntegerExact.
* `EDecimal RoundToIntegralNoRoundedFlag​(EContext ctx)`<br>
 Deprecated.
Renamed to RoundToIntegerNoRoundedFlag.
 Renamed to RoundToIntegerNoRoundedFlag.
* `EDecimal RoundToPrecision​(EContext ctx)`<br>
 Rounds this object's value to a given precision, using the given rounding
 mode and range of exponent.
* `EDecimal ScaleByPowerOfTen​(int places)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `EDecimal ScaleByPowerOfTen​(int places,
EContext ctx)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `EDecimal ScaleByPowerOfTen​(EInteger bigPlaces)`<br>
 Returns a number similar to this number but with the scale adjusted.
* `EDecimal ScaleByPowerOfTen​(EInteger bigPlaces,
EContext ctx)`<br>
 Returns a number similar to this number but with its scale adjusted.
* `int signum()`<br>
 Gets this value's sign: -1 if negative; 1 if positive; 0 if zero.
* `EDecimal Sqrt​(EContext ctx)`<br>
 Finds the square root of this object's value.
* `EDecimal SquareRoot​(EContext ctx)`<br>
 Deprecated.
Renamed to Sqrt.
 Renamed to Sqrt.
* `EDecimal Subtract​(int intValue)`<br>
 Subtracts a 32-bit signed integer from this arbitrary-precision decimal
 floating-point number and returns the result.
* `EDecimal Subtract​(long longValue)`<br>
 Subtracts a 64-bit signed integer from this arbitrary-precision decimal
 floating-point number and returns the result.
* `EDecimal Subtract​(EDecimal otherValue)`<br>
 Subtracts an arbitrary-precision decimal floating-point number from this
 arbitrary-precision decimal floating-point number and returns the
 result.
* `EDecimal Subtract​(EDecimal otherValue,
EContext ctx)`<br>
 Subtracts an arbitrary-precision decimal floating-point number from this
 arbitrary-precision decimal floating-point number and returns the
 result.
* `byte ToByteChecked()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) after converting it to an integer by discarding
 its fractional part.
* `byte ToByteIfExact()`<br>
 Converts this number's value to a byte (from 0 to 255) if it can fit in a
 byte (from 0 to 255) without rounding to a different numerical
 value.
* `byte ToByteUnchecked()`<br>
 Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a byte (from 0 to 255).
* `double ToDouble()`<br>
 Converts this value to its closest equivalent as a 64-bit floating-point
 number, using the half-even rounding mode.
* `long ToDoubleBits()`<br>
 Converts this value to its closest equivalent as a 64-bit floating-point
 number encoded in the IEEE 754 binary64 format, using the half-even
 rounding mode.
* `EFloat ToEFloat()`<br>
 Creates a binary floating-point number from this object's value.
* `EFloat ToEFloat​(EContext ec)`<br>
 Creates a binary floating-point number from this object's value.
* `EInteger ToEInteger()`<br>
 Converts this value to an arbitrary-precision integer, discarding the
 fractional part in this value.
* `EInteger ToEIntegerExact()`<br>
 Deprecated.
Renamed to ToEIntegerIfExact.
 Renamed to ToEIntegerIfExact.
* `EInteger ToEIntegerIfExact()`<br>
 Converts this value to an arbitrary-precision integer, checking whether the
 fractional part of the value would be lost.
* `java.lang.String ToEngineeringString()`<br>
 Same as toString(), except that when an exponent is used it will be a
 multiple of 3.
* `EFloat ToExtendedFloat()`<br>
 Deprecated.
Renamed to ToEFloat.
 Renamed to ToEFloat.
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
* `java.lang.String ToPlainString()`<br>
 Converts this value to a string as though with the toString method, but
 without using exponential notation.
* `float ToSingle()`<br>
 Converts this value to its closest equivalent as a 32-bit floating-point
 number, using the half-even rounding mode.
* `int ToSingleBits()`<br>
 Converts this value to its closest equivalent as a 32-bit floating-point
 number encoded in the IEEE 754 binary32 format, using the half-even
 rounding mode.
* `EInteger ToSizedEInteger​(int maxBitLength)`<br>
 Converts this value to an arbitrary-precision integer by discarding its
 fractional part and checking whether the resulting integer overflows
 the given signed bit count.
* `EInteger ToSizedEIntegerIfExact​(int maxBitLength)`<br>
 Converts this value to an arbitrary-precision integer, only if this number's
 value is an exact integer and that integer does not overflow the
 given signed bit count.
* `java.lang.String toString()`<br>
 Converts this value to a text string.
* `EDecimal Ulp()`<br>
 Returns the unit in the last place.

## Field Details

### <a id='NaN'>NaN</a>

A not-a-number value.
### <a id='NegativeInfinity'>NegativeInfinity</a>

Negative infinity, less than any other number.
### <a id='NegativeZero'>NegativeZero</a>

Represents the number negative zero.
### <a id='One'>One</a>

Represents the number 1.
### <a id='PositiveInfinity'>PositiveInfinity</a>

Positive infinity, greater than any other number.
### <a id='SignalingNaN'>SignalingNaN</a>

A not-a-number value that signals an invalid operation flag when it's passed
 as an argument to any arithmetic operation in arbitrary-precision
 decimal.
### <a id='Ten'>Ten</a>

Represents the number 10.
### <a id='Zero'>Zero</a>

Represents the number 0.
## Method Details

### <a id='Copy()'>Copy</a>

Creates a copy of this arbitrary-precision binary number.

**Returns:**

* An arbitrary-precision decimal floating-point number.

### <a id='getExponent()'>getExponent</a>

Gets this object's exponent. This object's value will be an integer if the
 exponent is positive or zero.

**Returns:**

* This object's exponent. This object's value will be an integer if
 the exponent is positive or zero.

### <a id='isFinite()'>isFinite</a>

Gets a value indicating whether this object is finite (not infinity or NaN).

**Returns:**

* <code>true</code> if this object is finite (not infinity or NaN);
 otherwise, <code>false</code>.

### <a id='isNegative()'>isNegative</a>

Gets a value indicating whether this object is negative, including negative
 zero.

**Returns:**

* <code>true</code> if this object is negative, including negative zero;
 otherwise, <code>false</code>.

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

### <a id='getMantissa()'>getMantissa</a>

Gets this object's unscaled value, or significand, and makes it negative if
 this object is negative. If this value is not-a-number (NaN), that
  value's absolute value is the NaN's "payload" (diagnostic
 information).

**Returns:**

* This object's unscaled value. Will be negative if this object's
 value is negative (including a negative NaN).

### <a id='signum()'>signum</a>

Gets this value's sign: -1 if negative; 1 if positive; 0 if zero.

**Returns:**

* This value's sign: -1 if negative; 1 if positive; 0 if zero.

### <a id='getUnsignedMantissa()'>getUnsignedMantissa</a>

Gets the absolute value of this object's unscaled value, or significand. If
  this value is not-a-number (NaN), that value is the NaN's "payload"
 (diagnostic information).

**Returns:**

* The absolute value of this object's unscaled value.

### <a id='Create(int,int)'>Create</a>

Returns a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissaSmall</code> - Desired value for the significand.

* <code>exponentSmall</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

### <a id='Create(com.upokecenter.numbers.EInteger,int)'>Create</a>

Creates a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissa</code> - Desired value for the significand.

* <code>exponentSmall</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>mantissa</code> is null.

### <a id='Create(com.upokecenter.numbers.EInteger,long)'>Create</a>

Creates a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissa</code> - Desired value for the significand.

* <code>exponentLong</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>mantissa</code> is null.

### <a id='Create(com.upokecenter.numbers.EInteger,com.upokecenter.numbers.EInteger)'>Create</a>

Creates a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissa</code> - Desired value for the significand.

* <code>exponent</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>mantissa</code> or <code>
 exponent</code> is null.

### <a id='Create(long,int)'>Create</a>

Creates a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissaLong</code> - Desired value for the significand.

* <code>exponentSmall</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

### <a id='Create(long,long)'>Create</a>

Creates a number with the value <code>exponent*10^significand</code>.

**Parameters:**

* <code>mantissaLong</code> - Desired value for the significand.

* <code>exponentLong</code> - Desired value for the exponent.

**Returns:**

* An arbitrary-precision decimal number.

### <a id='CreateNaN(com.upokecenter.numbers.EInteger)'>CreateNaN</a>

Creates a not-a-number arbitrary-precision decimal number.

**Parameters:**

* <code>diag</code> - An integer, 0 or greater, to use as diagnostic information
 associated with this object. If none is needed, should be zero. To
 get the diagnostic information from another arbitrary-precision
 decimal floating-point number, use that object's <code>
 UnsignedMantissa</code> property.

**Returns:**

* A quiet not-a-number.

### <a id='CreateNaN(com.upokecenter.numbers.EInteger,boolean,boolean,com.upokecenter.numbers.EContext)'>CreateNaN</a>

Creates a not-a-number arbitrary-precision decimal number.

**Parameters:**

* <code>diag</code> - An integer, 0 or greater, to use as diagnostic information
 associated with this object. If none is needed, should be zero. To
 get the diagnostic information from another arbitrary-precision
 decimal floating-point number, use that object's <code>
 UnsignedMantissa</code> property.

* <code>signaling</code> - Whether the return value will be signaling (true) or quiet
 (false).

* <code>negative</code> - Whether the return value is negative.

* <code>ctx</code> - An arithmetic context to control the precision (in decimal
 digits) of the diagnostic information. The rounding and exponent
 range of this context will be ignored. Can be null. The only flag
 that can be signaled in this context is FlagInvalid, which happens
 if diagnostic information needs to be truncated and too much memory
 is required to do so.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>diag</code> is null or is less
 than 0.

### <a id='FromDouble(double)'>FromDouble</a>

Creates an arbitrary-precision decimal number from a 64-bit binary
 floating-point number. This method computes the exact value of the
 floating point number, not an approximation, as is often the case by
 converting the floating point number to a string first. Remember,
 though, that the exact value of a 64-bit binary floating-point
 number is not always the value that results when passing a literal
 decimal number (for example, calling <code>EDecimal.FromDouble(0.1)</code>
), since not all decimal numbers can be converted to exact binary
 numbers (in the example given, the resulting arbitrary-precision
  decimal will be the value of the closest "double" to 0.1, not 0.1
 exactly). To create an arbitrary-precision decimal number from a
 decimal value, use FromString instead in most cases (for example:
  <code>EDecimal.FromString("0.1")</code>). <p>The input value can be a
 not-a-number (NaN) value (such as <code>Double.NaN</code>); however, NaN
 values have multiple forms that are equivalent for many
 applications' purposes, and <code>Double.NaN</code> is only one of these
 equivalent forms. In fact, <code>EDecimal.FromDouble(Double.NaN)</code>
 could produce an object that is represented differently between
 DotNet and Java, because <code>Double.NaN</code> may have a different form
 in DotNet and Java (for example, the NaN value's sign may be
 negative in DotNet, but positive in Java). Use `IsNaN()` to
 determine whether an object from this class stores a NaN value of
 any form.</p>

**Parameters:**

* <code>dbl</code> - The parameter <code>dbl</code> is a 64-bit floating-point number.

**Returns:**

* An arbitrary-precision decimal number with the same value as <code>
 dbl</code>.

### <a id='FromDoubleBits(long)'>FromDoubleBits</a>

Creates an arbitrary-precision decimal number from a 64-bit binary
 floating-point number, encoded in the IEEE 754 binary64 format. This
 method computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the floating point
 number to a string first. Remember, though, that the exact value of
 a 64-bit binary floating-point number is not always the value that
 results when passing a literal decimal number, since not all decimal
 numbers can be converted to exact binary numbers (in the example
 given, the resulting arbitrary-precision decimal will be the value
  of the closest "double" to 0.1, not 0.1 exactly). To create an
 arbitrary-precision decimal number from a decimal value, use
 FromString instead in most cases.

**Parameters:**

* <code>dblBits</code> - The parameter <code>dblBits</code> is a 64-bit signed integer.

**Returns:**

* An arbitrary-precision decimal number with the same value as <code>
 dblBits</code>.

### <a id='FromEInteger(com.upokecenter.numbers.EInteger)'>FromEInteger</a>

Converts an arbitrary-precision integer to an arbitrary precision decimal.

**Parameters:**

* <code>bigint</code> - An arbitrary-precision integer.

**Returns:**

* An arbitrary-precision decimal number with the exponent set to 0.

### <a id='FromExtendedFloat(com.upokecenter.numbers.EFloat)'>FromExtendedFloat</a>

Converts an arbitrary-precision binary floating-point number to an arbitrary
 precision decimal.

**Parameters:**

* <code>ef</code> - An arbitrary-precision binary floating-point number.

**Returns:**

* An arbitrary-precision decimal number.

### <a id='FromEFloat(com.upokecenter.numbers.EFloat)'>FromEFloat</a>

Creates an arbitrary-precision decimal number from an arbitrary-precision
 binary floating-point number.

**Parameters:**

* <code>bigfloat</code> - An arbitrary-precision binary floating-point number.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigfloat</code> is null.

### <a id='FromBoolean(boolean)'>FromBoolean</a>

Converts a boolean value (true or false) to an arbitrary-precision decimal
 number.

**Parameters:**

* <code>boolValue</code> - Either true or false.

**Returns:**

* The number 1 if <code>boolValue</code> is true; otherwise, 0.

### <a id='FromInt32(int)'>FromInt32</a>

Creates an arbitrary-precision decimal number from a 32-bit signed integer.

**Parameters:**

* <code>valueSmaller</code> - The parameter <code>valueSmaller</code> is a 32-bit signed
 integer.

**Returns:**

* An arbitrary-precision decimal number with the exponent set to 0.

### <a id='FromInt64AsUnsigned(long)'>FromInt64AsUnsigned</a>

Converts an unsigned integer expressed as a 64-bit signed integer to an
 arbitrary-precision decimal number.

**Parameters:**

* <code>longerValue</code> - A 64-bit signed integer. If this value is 0 or greater,
 the return value will represent it. If this value is less than 0,
 the return value will store 2^64 plus this value instead.

**Returns:**

* An arbitrary-precision decimal number with the exponent set to 0. If
 <code>longerValue</code> is 0 or greater, the return value will represent
 it. If <code>longerValue</code> is less than 0, the return value will
 store 2^64 plus this value instead.

### <a id='FromInt64(long)'>FromInt64</a>

Creates an arbitrary-precision decimal number from a 64-bit signed integer.

**Parameters:**

* <code>valueSmall</code> - The parameter <code>valueSmall</code> is a 64-bit signed
 integer.

**Returns:**

* This number's value as an arbitrary-precision decimal number with
 the exponent set to 0.

### <a id='FromSingle(float)'>FromSingle</a>

Creates an arbitrary-precision decimal number from a 32-bit binary
 floating-point number. This method computes the exact value of the
 floating point number, not an approximation, as is often the case by
 converting the floating point number to a string first. Remember,
 though, that the exact value of a 32-bit binary floating-point
 number is not always the value that results when passing a literal
 decimal number (for example, calling
 <code>EDecimal.FromSingle(0.1f)</code>), since not all decimal numbers
 can be converted to exact binary numbers (in the example given, the
 resulting arbitrary-precision decimal will be the the value of the
  closest "float" to 0.1, not 0.1 exactly). To create an
 arbitrary-precision decimal number from a decimal value, use
 FromString instead in most cases (for example:
  <code>EDecimal.FromString("0.1")</code>). <p>The input value can be a
 not-a-number (NaN) value (such as <code>Float.NaN</code> in DotNet or
 Float.NaN in Java); however, NaN values have multiple forms that are
 equivalent for many applications' purposes, and <code>Float.NaN</code> /
 <code>Float.NaN</code> is only one of these equivalent forms. In fact,
 <code>EDecimal.FromSingle(Float.NaN)</code> or
 <code>EDecimal.FromSingle(Float.NaN)</code> could produce an object that
 is represented differently between DotNet and Java, because
 <code>Float.NaN</code> / <code>Float.NaN</code> may have a different form in
 DotNet and Java (for example, the NaN value's sign may be negative
 in DotNet, but positive in Java). Use `IsNaN()` to determine whether
 an object from this class stores a NaN value of any form.</p>

**Parameters:**

* <code>flt</code> - The parameter <code>flt</code> is a 32-bit binary floating-point
 number.

**Returns:**

* An arbitrary-precision decimal number with the same value as <code>
 flt</code>.

### <a id='FromSingleBits(int)'>FromSingleBits</a>

Creates an arbitrary-precision decimal number from a 32-bit binary
 floating-point number encoded in the IEEE 754 binary32 format. This
 method computes the exact value of the floating point number, not an
 approximation, as is often the case by converting the floating point
 number to a string first. Remember, though, that the exact value of
 a 32-bit binary floating-point number is not always the value that
 results when passing a literal decimal number, since not all decimal
 numbers can be converted to exact binary numbers (in the example
 given, the resulting arbitrary-precision decimal will be the the
  value of the closest "float" to 0.1, not 0.1 exactly). To create an
 arbitrary-precision decimal number from a decimal value, use
 FromString instead in most cases.

**Parameters:**

* <code>value</code> - A 32-bit binary floating-point number encoded in the IEEE 754
 binary32 format.

**Returns:**

* An arbitrary-precision decimal number with the same value as <code>
 value</code>.

### <a id='FromHalfBits(short)'>FromHalfBits</a>

Creates a decimal floating-point number from a binary floating-point number
 encoded in the IEEE 754 binary16 format (also known as a
  "half-precision" floating-point number). This method computes the
 exact value of the floating point number, not an approximation, as
 is often the case by converting the floating point number to a
 string first.

**Parameters:**

* <code>value</code> - A binary floating-point number encoded in the IEEE 754 binary16
 format.

**Returns:**

* A decimal floating-point number with the same floating-point value
 as <code>value</code>.

### <a id='FromString(char[])'>FromString</a>

Creates an arbitrary-precision decimal number from a sequence of <code>char</code>
 s that represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information. Note that calling the overload
 that takes an EContext is often much faster than creating the
 EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
 especially if the context specifies a precision limit and exponent
 range.

**Parameters:**

* <code>chars</code> - A sequence that represents a number.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of <code>char</code> s.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>chars</code> is not a correctly
 formatted number sequence.

### <a id='FromString(char[],com.upokecenter.numbers.EContext)'>FromString</a>

Creates an arbitrary-precision decimal number from a sequence of <code>char</code>
 s that represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information.

**Parameters:**

* <code>chars</code> - A sequence of <code>char</code> s that represents a number.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of <code>char</code> s.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>chars</code> is null.

### <a id='FromString(char[],int,int)'>FromString</a>

Creates an arbitrary-precision decimal number from a sequence of <code>char</code>
 s that represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information. Note that calling the overload
 that takes an EContext is often much faster than creating the
 EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
 especially if the context specifies a precision limit and exponent
 range.

**Parameters:**

* <code>chars</code> - A sequence that represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>chars</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 chars</code> (but not more than <code>chars</code> 's length).

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of <code>char</code> s.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>chars</code> is not a correctly
 formatted number sequence.

* <code>java.lang.NullPointerException</code> - The parameter <code>chars</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>chars</code> 's length, or <code>chars</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### <a id='FromString(char[],int,int,com.upokecenter.numbers.EContext)'>FromString</a>

<p>Creates an arbitrary-precision decimal number from a sequence of
 <code>char</code> s that represents a number.</p> <p>The format of the
 sequence generally consists of:</p> <ul> <li>An optional plus sign
  ("+" , U+002B) or minus sign ("-", U+002D) (if the minus sign, the
 value is negative.)</li> <li>One or more digits, with a single
  optional decimal point (".", U+002E) before or after those digits or
 between two of them. These digits may begin with any number of
  zeros.</li> <li>Optionally, "E"/"e" followed by an optional
  (positive exponent) or "-" (negative exponent) and followed by one
 or more digits specifying the exponent (these digits may begin with
  any number of zeros).</li></ul> <p>The sequence can also be "-INF",
  "-Infinity", "Infinity", "INF", quiet NaN ("NaN" /"-NaN") followed
 by any number of digits (these digits may begin with any number of
  zeros), or signaling NaN ("sNaN" /"-sNaN") followed by any number of
 digits (these digits may begin with any number of zeros), all where
 the letters can be any combination of basic upper-case and/or basic
 lower-case letters.</p> <p>All characters mentioned above are the
 corresponding characters in the Basic Latin range. In particular,
 the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
 sequence is not allowed to contain white space characters, including
 spaces.</p>

**Parameters:**

* <code>chars</code> - A sequence of <code>char</code> s, a portion of which represents a
 number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>chars</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 chars</code> (but not more than <code>chars</code> 's length).

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of <code>char</code> s.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>chars</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>chars</code> 's length, or <code>chars</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### <a id='FromString(byte[])'>FromString</a>

Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number. See
 <code>FromString(string, int, int, EContext)</code> for more information.
 Note that calling the overload that takes an EContext is often much
 faster than creating the EDecimal then calling
 <code>RoundToPrecision</code> on that EDecimal, especially if the context
 specifies a precision limit and exponent range.

**Parameters:**

* <code>bytes</code> - A sequence that represents a number.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of bytes (interpreted as text).

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>bytes</code> is not a correctly
 formatted number sequence.

### <a id='FromString(byte[],com.upokecenter.numbers.EContext)'>FromString</a>

Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number. See
 <code>FromString(string, int, int, EContext)</code> for more information.

**Parameters:**

* <code>bytes</code> - A sequence of bytes (interpreted as text) that represents a
 number.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of bytes (interpreted as text).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

### <a id='FromString(byte[],int,int)'>FromString</a>

Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number. See
 <code>FromString(string, int, int, EContext)</code> for more information.
 Note that calling the overload that takes an EContext is often much
 faster than creating the EDecimal then calling
 <code>RoundToPrecision</code> on that EDecimal, especially if the context
 specifies a precision limit and exponent range.

**Parameters:**

* <code>bytes</code> - A sequence that represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>bytes</code> begins.

* <code>length</code> - The length, in bytes, of the desired portion of <code>bytes</code>
 (but not more than <code>bytes</code> 's length).

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of bytes (interpreted as text).

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>bytes</code> is not a correctly
 formatted number sequence.

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>bytes</code> 's length, or <code>bytes</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### <a id='FromString(byte[],int,int,com.upokecenter.numbers.EContext)'>FromString</a>

<p>Creates an arbitrary-precision decimal number from a sequence of bytes
 (interpreted as text) that represents a number. Each byte in the
 sequence has to be a code point in the Basic Latin range (0x00 to
 0x7f or U+0000 to U+007F) of the Unicode Standard.</p> <p>The format
 of the sequence generally consists of:</p> <ul> <li>An optional plus
  sign ("+" , U+002B) or minus sign ("-", U+002D) (if the minus sign,
 the value is negative.)</li> <li>One or more digits, with a single
  optional decimal point (".", U+002E) before or after those digits or
 between two of them. These digits may begin with any number of
  zeros.</li> <li>Optionally, "E"/"e" followed by an optional
  (positive exponent) or "-" (negative exponent) and followed by one
 or more digits specifying the exponent (these digits may begin with
  any number of zeros).</li></ul> <p>The sequence can also be "-INF",
  "-Infinity", "Infinity", "INF", quiet NaN ("NaN" /"-NaN") followed
 by any number of digits (these digits may begin with any number of
  zeros), or signaling NaN ("sNaN" /"-sNaN") followed by any number of
 digits (these digits may begin with any number of zeros), all where
 the letters can be any combination of basic upper-case and/or basic
 lower-case letters.</p> <p>All characters mentioned above are the
 corresponding characters in the Basic Latin range. In particular,
 the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
 sequence is not allowed to contain white space characters, including
 spaces.</p>

**Parameters:**

* <code>bytes</code> - A sequence of bytes (interpreted as text), a portion of which
 represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>bytes</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 bytes</code> (but not more than <code>bytes</code> 's length).

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given sequence of bytes (interpreted as text).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bytes</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>bytes</code> 's length, or <code>bytes</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### <a id='FromString(java.lang.String)'>FromString</a>

Creates an arbitrary-precision decimal number from a text string that
 represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information. Note that calling the overload
 that takes an EContext is often much faster than creating the
 EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
 especially if the context specifies a precision limit and exponent
 range.

**Parameters:**

* <code>str</code> - A string that represents a number.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

### <a id='FromString(java.lang.String,com.upokecenter.numbers.EContext)'>FromString</a>

Creates an arbitrary-precision decimal number from a text string that
 represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information.

**Parameters:**

* <code>str</code> - A string that represents a number.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

### <a id='FromString(java.lang.String,int,int)'>FromString</a>

Creates an arbitrary-precision decimal number from a text string that
 represents a number. See <code>FromString(string, int, int,
 EContext)</code> for more information. Note that calling the overload
 that takes an EContext is often much faster than creating the
 EDecimal then calling <code>RoundToPrecision</code> on that EDecimal,
 especially if the context specifies a precision limit and exponent
 range.

**Parameters:**

* <code>str</code> - A string that represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>java.lang.NumberFormatException</code> - The parameter <code>str</code> is not a correctly
 formatted number string.

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>str</code> 's length, or <code>str</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### <a id='FromString(java.lang.String,int,int,com.upokecenter.numbers.EContext)'>FromString</a>

<p>Creates an arbitrary-precision decimal number from a text string that
 represents a number.</p> <p>The format of the string generally
  consists of:</p> <ul> <li>An optional plus sign ("+" , U+002B) or
  minus sign ("-", U+002D) (if the minus sign, the value is
 negative.)</li> <li>One or more digits, with a single optional
  decimal point (".", U+002E) before or after those digits or between
 two of them. These digits may begin with any number of zeros.</li>
  <li>Optionally, "E"/"e" followed by an optional (positive exponent)
  or "-" (negative exponent) and followed by one or more digits
 specifying the exponent (these digits may begin with any number of
  zeros).</li></ul> <p>The string can also be "-INF", "-Infinity",
  "Infinity", "INF", quiet NaN ("NaN" /"-NaN") followed by any number
 of digits (these digits may begin with any number of zeros), or
  signaling NaN ("sNaN" /"-sNaN") followed by any number of digits
 (these digits may begin with any number of zeros), all where the
 letters can be any combination of basic upper-case and/or basic
 lower-case letters.</p> <p>All characters mentioned above are the
 corresponding characters in the Basic Latin range. In particular,
 the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The
 string is not allowed to contain white space characters, including
 spaces.</p>

**Parameters:**

* <code>str</code> - A text string, a portion of which represents a number.

* <code>offset</code> - An index starting at 0 showing where the desired portion of
 <code>str</code> begins.

* <code>length</code> - The length, in code units, of the desired portion of <code>
 str</code> (but not more than <code>str</code> 's length).

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.
 Note that providing a context is often much faster than creating the
 EDecimal without a context then calling <code>RoundToPrecision</code> on
 that EDecimal, especially if the context specifies a precision limit
 and exponent range.

**Returns:**

* An arbitrary-precision decimal number with the same value as the
 given string.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>str</code> is null.

* <code>java.lang.IllegalArgumentException</code> - Either <code>offset</code> or <code>length</code> is less
 than 0 or greater than <code>str</code> 's length, or <code>str</code> 's
 length minus <code>offset</code> is less than <code>length</code>.

### <a id='Max(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>Max</a>

Gets the greater value between two decimal numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The larger value of the two numbers. If one is positive zero and the
 other is negative zero, returns the positive zero. If the two
 numbers are positive and have the same value, returns the one with
 the larger exponent. If the two numbers are negative and have the
 same value, returns the one with the smaller exponent.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### <a id='Max(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EDecimal)'>Max</a>

Gets the greater value between two decimal numbers.

**Parameters:**

* <code>first</code> - An arbitrary-precision decimal number.

* <code>second</code> - Another arbitrary-precision decimal number.

**Returns:**

* The larger value of the two numbers. If one is positive zero and the
 other is negative zero, returns the positive zero. If the two
 numbers are positive and have the same value, returns the one with
 the larger exponent. If the two numbers are negative and have the
 same value, returns the one with the smaller exponent.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### <a id='MaxMagnitude(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>MaxMagnitude</a>

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

* The larger value of the two numbers, ignoring their signs.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### <a id='MaxMagnitude(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EDecimal)'>MaxMagnitude</a>

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

### <a id='Min(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>Min</a>

Gets the lesser value between two decimal numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The smaller value of the two numbers. If one is positive zero and
 the other is negative zero, returns the negative zero. If the two
 numbers are positive and have the same value, returns the one with
 the smaller exponent. If the two numbers are negative and have the
 same value, returns the one with the larger exponent.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### <a id='Min(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EDecimal)'>Min</a>

Gets the lesser value between two decimal numbers.

**Parameters:**

* <code>first</code> - The first value to compare.

* <code>second</code> - The second value to compare.

**Returns:**

* The smaller value of the two numbers. If one is positive zero and
 the other is negative zero, returns the negative zero. If the two
 numbers are positive and have the same value, returns the one with
 the smaller exponent. If the two numbers are negative and have the
 same value, returns the one with the larger exponent.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### <a id='MinMagnitude(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>MinMagnitude</a>

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

* The smaller value of the two numbers, ignoring their signs.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>first</code> or <code>second</code>
 is null.

### <a id='MinMagnitude(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EDecimal)'>MinMagnitude</a>

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

### <a id='PI(com.upokecenter.numbers.EContext)'>PI</a>

Finds the constant π, the circumference of a circle divided by its diameter.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as π can never be represented exactly.</i>.

**Returns:**

* The constant π rounded to the given precision. Signals FlagInvalid
 and returns not-a-number (NaN) if the parameter <code>ctx</code> is null
 or the precision is unlimited (the context's Precision property is
 0).

### <a id='Abs()'>Abs</a>

Finds the absolute value of this object (if it's negative, it becomes
 positive).

**Returns:**

* An arbitrary-precision decimal number. Returns signaling NaN if this
 value is signaling NaN. (In this sense, this method is similar to
  the "copy-abs" operation in the General Decimal Arithmetic
 Specification, except this method does not necessarily return a copy
 of this object.).

### <a id='CopySign(com.upokecenter.numbers.EDecimal)'>CopySign</a>

Returns a number with the same value as this one, but copying the sign
 (positive or negative) of another number. (This method is similar to
  the "copy-sign" operation in the General Decimal Arithmetic
 Specification, except this method does not necessarily return a copy
 of this object.).

**Parameters:**

* <code>other</code> - A number whose sign will be copied.

**Returns:**

* An arbitrary-precision decimal number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>other</code> is null.

### <a id='Abs(com.upokecenter.numbers.EContext)'>Abs</a>

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

### <a id='Add(com.upokecenter.numbers.EDecimal)'>Add</a>

Adds this arbitrary-precision decimal floating-point number and another
 arbitrary-precision decimal floating-point number and returns the
 result. The exponent for the result is the lower of this
 arbitrary-precision decimal floating-point number's exponent and the
 other arbitrary-precision decimal floating-point number's exponent.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision decimal number.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 decimal floating-point number plus another arbitrary-precision
 decimal floating-point number. If this arbitrary-precision decimal
 floating-point number is not-a-number (NaN), returns NaN.

### <a id='Add(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>Add</a>

Adds this arbitrary-precision decimal floating-point number and another
 arbitrary-precision decimal floating-point number and returns the
 result.

**Parameters:**

* <code>otherValue</code> - The number to add to.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 decimal floating-point number plus another arbitrary-precision
 decimal floating-point number. If this arbitrary-precision decimal
 floating-point number is not-a-number (NaN), returns NaN.

### <a id='compareTo(com.upokecenter.numbers.EDecimal)'>compareTo</a>

Compares the mathematical values of this object and another object,
 accepting NaN values. This method currently uses the rules given in
 the CompareToValue method, so that it it is not consistent with the
 Equals method, but it may change in a future version to use the
 rules for the CompareToTotal method instead.

**Specified by:**

* <code>compareTo</code> in interface <code>java.lang.Comparable&lt;EDecimal&gt;</code>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
 value or if <code>other</code> is null, or 0 if both values are equal.
 This implementation returns a positive number if.

### <a id='compareTo(int)'>compareTo</a>

Compares the mathematical values of this object and another object,
 accepting NaN values. This method currently uses the rules given in
 the CompareToValue method, so that it it is not consistent with the
 Equals method, but it may change in a future version to use the
 rules for the CompareToTotal method instead.

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 32-bit signed integer.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
 value, or 0 if both values are equal.

### <a id='CompareToValue(int)'>CompareToValue</a>

Compares the mathematical values of this object and another object,
 accepting NaN values. <p>This method is not consistent with the
 Equals method because two different numbers with the same
 mathematical value, but different exponents, will compare as
 equal.</p> <p>In this method, negative zero and positive zero are
 considered equal.</p> <p>If this object is a quiet NaN or signaling
 NaN, this method will not trigger an error. Instead, NaN will
 compare greater than any other number, including infinity.</p>

**Parameters:**

* <code>intOther</code> - The parameter <code>intOther</code> is a 32-bit signed integer.

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

### <a id='CompareToValue(com.upokecenter.numbers.EDecimal)'>CompareToValue</a>

Compares the mathematical values of this object and another object,
 accepting NaN values. <p>This method is not consistent with the
 Equals method because two different numbers with the same
 mathematical value, but different exponents, will compare as
 equal.</p> <p>In this method, negative zero and positive zero are
 considered equal.</p> <p>If this object or the other object is a
 quiet NaN or signaling NaN, this method will not trigger an error.
 Instead, NaN will compare greater than any other number, including
 infinity. Two different NaN values will be considered equal.</p>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

**Returns:**

* Less than 0 if this object's value is less than the other value, or
 greater than 0 if this object's value is greater than the other
 value or if <code>other</code> is null, or 0 if both values are equal.
 This implementation returns a positive number if.

### <a id='CompareToBinary(com.upokecenter.numbers.EFloat)'>CompareToBinary</a>

Compares an arbitrary-precision binary floating-point number with this
 instance.

**Parameters:**

* <code>other</code> - The other object to compare. Can be null.

**Returns:**

* Zero if the values are equal; a negative number if this instance is
 less; or a positive number if this instance is greater. Returns 0 if
 both values are NaN (even signaling NaN) and 1 if this value is NaN
 (even signaling NaN) and the other isn't, or if the other value is
 null. This implementation returns a positive number if.

### <a id='CompareToSignal(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>CompareToSignal</a>

Compares the mathematical values of this object and another object, treating
 quiet NaN as signaling. <p>In this method, negative zero and
 positive zero are considered equal.</p> <p>If this object or the
 other object is a quiet NaN or signaling NaN, this method will
 return a quiet NaN and will signal a FlagInvalid flag.</p>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

* <code>ctx</code> - An arithmetic context. The precision, rounding, and exponent
 range are ignored. If <code>HasFlags</code> of the context is true, will
 store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null.

**Returns:**

* Quiet NaN if this object or the other object is NaN, or 0 if both
 objects have the same value, or -1 if this object is less than the
 other value, or a 1 if this object is greater. This implementation
 returns a positive number if.

### <a id='CompareToTotalMagnitude(com.upokecenter.numbers.EDecimal)'>CompareToTotalMagnitude</a>

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

* <code>other</code> - An arbitrary-precision decimal number to compare with this one.

**Returns:**

* The number 0 if both objects have the same value (ignoring their
 signs), or -1 if this object is less than the other value (ignoring
 their signs), or 1 if this object is greater (ignoring their signs).
 This implementation returns a positive number if.

### <a id='CompareToTotal(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>CompareToTotal</a>

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

* <code>other</code> - An arbitrary-precision decimal number to compare with this one.

* <code>ctx</code> - An arithmetic context. Flags will be set in this context only if
 <code>HasFlags</code> and <code>IsSimplified</code> of the context are true
 and only if an operand needed to be rounded before carrying out the
 operation. Can be null.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.
 Does not signal flags if either value is signaling NaN. This
 implementation returns a positive number if.

### <a id='CompareToTotalMagnitude(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>CompareToTotalMagnitude</a>

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

* <code>other</code> - An arbitrary-precision decimal number to compare with this one.

* <code>ctx</code> - An arithmetic context. Flags will be set in this context only if
 <code>HasFlags</code> and <code>IsSimplified</code> of the context are true
 and only if an operand needed to be rounded before carrying out the
 operation. Can be null.

**Returns:**

* The number 0 if both objects have the same value (ignoring their
 signs), or -1 if this object is less than the other value (ignoring
 their signs), or 1 if this object is greater (ignoring their signs).
 Does not signal flags if either value is signaling NaN. This
 implementation returns a positive number if.

### <a id='CompareToTotal(com.upokecenter.numbers.EDecimal)'>CompareToTotal</a>

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

* <code>other</code> - An arbitrary-precision decimal number to compare with this one.

**Returns:**

* The number 0 if both objects have the same value, or -1 if this
 object is less than the other value, or 1 if this object is greater.
 This implementation returns a positive number if.

### <a id='CompareToWithContext(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>CompareToWithContext</a>

Compares the mathematical values of this object and another object. <p>In
 this method, negative zero and positive zero are considered
 equal.</p> <p>If this object or the other object is a quiet NaN or
 signaling NaN, this method returns a quiet NaN, and will signal a
 FlagInvalid flag if either is a signaling NaN.</p>

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

* <code>ctx</code> - An arithmetic context. The precision, rounding, and exponent
 range are ignored. If <code>HasFlags</code> of the context is true, will
 store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null.

**Returns:**

* Quiet NaN if this object or the other object is NaN, or 0 if both
 objects have the same value, or -1 if this object is less than the
 other value, or 1 if this object is greater. This implementation
 returns a positive number if.

### <a id='Divide(com.upokecenter.numbers.EDecimal)'>Divide</a>

Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result; returns NaN instead if the result would have a
 nonterminating decimal expansion (including 1/3, 1/12, 1/7, 2/3, and
 so on); if this is not desired, use DivideToExponent, or use the
 Divide overload that takes an EContext.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* The result of dividing this arbitrary-precision decimal
 floating-point number by another arbitrary-precision decimal
 floating-point number. Returns infinity if the divisor (this
 arbitrary-precision decimal floating-point number) is 0 and the
 dividend (the other arbitrary-precision decimal floating-point
 number) is nonzero. Returns not-a-number (NaN) if the divisor and
 the dividend are 0. Returns NaN if the result can't be exact because
 it would have a nonterminating binary expansion (examples include 1
 divided by any multiple of 3, such as 1/3 or 1/12). If this is not
 desired, use DivideToExponent instead, or use the Divide overload
 that takes an <code>EContext</code> (such as <code>EContext.Decimal128</code>
) instead.

### <a id='Divide(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>Divide</a>

Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The result of dividing this arbitrary-precision decimal
 floating-point number by another arbitrary-precision decimal
 floating-point number. Signals FlagDivideByZero and returns infinity
 if the divisor (this arbitrary-precision decimal floating-point
 number) is 0 and the dividend (the other arbitrary-precision decimal
 floating-point number) is nonzero. Signals FlagInvalid and returns
 not-a-number (NaN) if the divisor and the dividend are 0; or, either
 <code>ctx</code> is null or <code>ctx</code> 's precision is 0, and the result
 would have a nonterminating decimal expansion (examples include 1
 divided by any multiple of 3, such as 1/3 or 1/12); or, the rounding
 mode is ERounding.None and the result is not exact.

### <a id='DivideAndRemainderNaturalScale(com.upokecenter.numbers.EDecimal)'>DivideAndRemainderNaturalScale</a>

Calculates the quotient and remainder using the DivideToIntegerNaturalScale
 and the formula in RemainderNaturalScale.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* A 2 element array consisting of the quotient and remainder in that
 order.

### <a id='DivideAndRemainderNaturalScale(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>DivideAndRemainderNaturalScale</a>

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

### <a id='DivRemNaturalScale(com.upokecenter.numbers.EDecimal)'>DivRemNaturalScale</a>

Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns a
 two-item array containing the result of the division and the
 remainder, in that order. The result of division is calculated as
 though by <code>DivideToIntegerNaturalScale</code>, and the remainder is
 calculated as though by <code>RemainderNaturalScale</code>.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* An array of two items: the first is the result of the division as an
 arbitrary-precision decimal floating-point number, and the second is
 the remainder as an arbitrary-precision decimal floating-point
 number. The result of division is the result of the method on the
 two operands, and the remainder is the result of the Remainder
 method on the two operands.

### <a id='DivRemNaturalScale(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>DivRemNaturalScale</a>

Divides this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns a
 two-item array containing the result of the division and the
 remainder, in that order. The result of division is calculated as
 though by <code>DivideToIntegerNaturalScale</code>, and the remainder is
 calculated as though by <code>RemainderNaturalScale</code>.

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

* An array of two items: the first is the result of the division as an
 arbitrary-precision decimal floating-point number, and the second is
 the remainder as an arbitrary-precision decimal floating-point
 number. The result of division is the result of the method on the
 two operands, and the remainder is the result of the Remainder
 method on the two operands.

### <a id='DivideToExponent(com.upokecenter.numbers.EDecimal,long,com.upokecenter.numbers.EContext)'>DivideToExponent</a>

Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponentSmall</code> - The desired exponent. A negative number places
 the cutoff point to the right of the usual decimal point (so a
 negative number means the number of decimal places to round to). A
 positive number places the cutoff point to the left of the usual
 decimal point.

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

### <a id='DivideToExponent(com.upokecenter.numbers.EDecimal,int,com.upokecenter.numbers.EContext)'>DivideToExponent</a>

Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponentInt</code> - The desired exponent. A negative number places the
 cutoff point to the right of the usual decimal point (so a negative
 number means the number of decimal places to round to). A positive
 number places the cutoff point to the left of the usual decimal
 point.

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

### <a id='DivideToExponent(com.upokecenter.numbers.EDecimal,long,com.upokecenter.numbers.ERounding)'>DivideToExponent</a>

Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponentSmall</code> - The desired exponent. A negative number places
 the cutoff point to the right of the usual decimal point (so a
 negative number means the number of decimal places to round to). A
 positive number places the cutoff point to the left of the usual
 decimal point.

* <code>rounding</code> - The rounding mode to use if the result must be scaled down
 to have the same exponent as this value.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the rounding mode is ERounding.None and the result is not
 exact.

### <a id='DivideToExponent(com.upokecenter.numbers.EDecimal,int,com.upokecenter.numbers.ERounding)'>DivideToExponent</a>

Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponentInt</code> - The desired exponent. A negative number places the
 cutoff point to the right of the usual decimal point (so a negative
 number means the number of decimal places to round to). A positive
 number places the cutoff point to the left of the usual decimal
 point.

* <code>rounding</code> - The rounding mode to use if the result must be scaled down
 to have the same exponent as this value.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0. Signals FlagInvalid and returns not-a-number
 (NaN) if the rounding mode is ERounding.None and the result is not
 exact.

### <a id='DivideToExponent(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EInteger,com.upokecenter.numbers.EContext)'>DivideToExponent</a>

Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>exponent</code> - The desired exponent. A negative number places the cutoff
 point to the right of the usual decimal point (so a negative number
 means the number of decimal places to round to). A positive number
 places the cutoff point to the left of the usual decimal point.

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

### <a id='DivideToExponent(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EInteger)'>DivideToExponent</a>

Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result, using the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>exponent</code> - The desired exponent. A negative number places the cutoff
 point to the right of the usual decimal point (so a negative number
 means the number of decimal places to round to). A positive number
 places the cutoff point to the left of the usual decimal point.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0.

### <a id='DivideToExponent(com.upokecenter.numbers.EDecimal,long)'>DivideToExponent</a>

Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 64-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponentSmall</code> - The desired exponent. A negative number places
 the cutoff point to the right of the usual decimal point (so a
 negative number means the number of decimal places to round to). A
 positive number places the cutoff point to the left of the usual
 decimal point.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0.

### <a id='DivideToExponent(com.upokecenter.numbers.EDecimal,int)'>DivideToExponent</a>

Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent (expressed as a 32-bit signed integer) to the result, using
 the half-even rounding mode.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponentInt</code> - The desired exponent. A negative number places the
 cutoff point to the right of the usual decimal point (so a negative
 number means the number of decimal places to round to). A positive
 number places the cutoff point to the left of the usual decimal
 point.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Signals FlagInvalid and returns not-a-number (NaN) if the divisor
 and the dividend are 0.

### <a id='DivideToExponent(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EInteger,com.upokecenter.numbers.ERounding)'>DivideToExponent</a>

Divides two arbitrary-precision decimal numbers, and gives a particular
 exponent to the result.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>desiredExponent</code> - The desired exponent. A negative number places the
 cutoff point to the right of the usual decimal point (so a negative
 number means the number of decimal places to round to). A positive
 number places the cutoff point to the left of the usual decimal
 point.

* <code>rounding</code> - The rounding mode to use if the result must be scaled down
 to have the same exponent as this value.

**Returns:**

* The quotient of the two objects. Signals FlagDivideByZero and
 returns infinity if the divisor is 0 and the dividend is nonzero.
 Returns not-a-number (NaN) if the divisor and the dividend are 0.
 Returns NaN if the rounding mode is ERounding.None and the result is
 not exact.

### <a id='DivideToIntegerNaturalScale(com.upokecenter.numbers.EDecimal)'>DivideToIntegerNaturalScale</a>

Divides two arbitrary-precision decimal numbers, and returns the integer
 part of the result, rounded down, with the preferred exponent set to
 this value's exponent minus the divisor's exponent.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* The integer part of the quotient of the two objects. Signals
 FlagDivideByZero and returns infinity if the divisor is 0 and the
 dividend is nonzero. Signals FlagInvalid and returns not-a-number
 (NaN) if the divisor and the dividend are 0.

### <a id='DivideToIntegerNaturalScale(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>DivideToIntegerNaturalScale</a>

Divides this object by another object, and returns the integer part of the
 result (which is initially rounded down), with the preferred
 exponent set to this value's exponent minus the divisor's exponent.

**Parameters:**

* <code>divisor</code> - The parameter <code>divisor</code> is an arbitrary-precision
 decimal floating-point number.

* <code>ctx</code> - The parameter <code>ctx</code> is an EContext object.

**Returns:**

* The integer part of the quotient of the two objects. Signals
 FlagInvalid and returns not-a-number (NaN) if the return value would
 overflow the exponent range. Signals FlagDivideByZero and returns
 infinity if the divisor is 0 and the dividend is nonzero. Signals
 FlagInvalid and returns not-a-number (NaN) if the divisor and the
 dividend are 0. Signals FlagInvalid and returns not-a-number (NaN)
 if the rounding mode is ERounding.None and the result is not exact.

### <a id='DivideToIntegerZeroScale(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>DivideToIntegerZeroScale</a>

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

### <a id='DivideToSameExponent(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.ERounding)'>DivideToSameExponent</a>

Divides this object by another decimal number and returns a result with the
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

### <a id='equals(com.upokecenter.numbers.EDecimal)'>equals</a>

Determines whether this object's significand, exponent, and properties are
 equal to those of another object. Not-a-number values are considered
 equal if the rest of their properties are equal.

**Parameters:**

* <code>other</code> - An arbitrary-precision decimal number.

**Returns:**

* <code>true</code> if this object's significand and exponent are equal to
 those of another object; otherwise, <code>false</code>.

### <a id='equals(java.lang.Object)'>equals</a>

Determines whether this object's significand, exponent, and properties are
 equal to those of another object and that other object is an
 arbitrary-precision decimal number. Not-a-number values are
 considered equal if the rest of their properties are equal.

**Overrides:**

* <code>equals</code> in class <code>java.lang.Object</code>

**Parameters:**

* <code>obj</code> - The parameter <code>obj</code> is an arbitrary object.

**Returns:**

* <code>true</code> if the objects are equal; otherwise, <code>false</code>. In
 this method, two objects are not equal if they don't have the same
 type or if one is null and the other isn't.

### <a id='Exp(com.upokecenter.numbers.EContext)'>Exp</a>

Finds e (the base of natural logarithms) raised to the power of this
 object's value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the exponential function's results are generally
 not exact.</i> (Unlike in the General Decimal Arithmetic
 Specification, any rounding mode is allowed.).

**Returns:**

* Exponential of this object. If this object's value is 1, returns an
  approximation to " e" within the given precision. Signals
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>
 ctx</code> is null or the precision is unlimited (the context's Precision
 property is 0).

### <a id='ExpM1(com.upokecenter.numbers.EContext)'>ExpM1</a>

Finds e (the base of natural logarithms) raised to the power of this
 object's value, and subtracts the result by 1 and returns the final
 result, in a way that avoids loss of precision if the true result is
 very close to 0.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the exponential function's results are generally
 not exact.</i> (Unlike in the General Binary Arithmetic
 Specification, any rounding mode is allowed.).

**Returns:**

* Exponential of this object, minus 1. Signals FlagInvalid and returns
 not-a-number (NaN) if the parameter <code>ctx</code> is null or the
 precision is unlimited (the context's Precision property is 0).

### <a id='hashCode()'>hashCode</a>

Calculates this object's hash code. No application or process IDs are used
 in the hash code calculation.

**Overrides:**

* <code>hashCode</code> in class <code>java.lang.Object</code>

**Returns:**

* A 32-bit signed integer.

### <a id='IsInfinity()'>IsInfinity</a>

Gets a value indicating whether this object is positive or negative
 infinity.

**Returns:**

* <code>true</code> if this object is positive or negative infinity;
 otherwise, <code>false</code>.

### <a id='IsNaN()'>IsNaN</a>

Gets a value indicating whether this object is not a number (NaN).

**Returns:**

* <code>true</code> if this object is not a number (NaN); otherwise, <code>
 false</code>.

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

Gets a value indicating whether this object is a quiet not-a-number value.

**Returns:**

* <code>true</code> if this object is a quiet not-a-number value;
 otherwise, <code>false</code>.

### <a id='IsSignalingNaN()'>IsSignalingNaN</a>

Gets a value indicating whether this object is a signaling not-a-number
 value.

**Returns:**

* <code>true</code> if this object is a signaling not-a-number value;
 otherwise, <code>false</code>.

### <a id='Log(com.upokecenter.numbers.EContext)'>Log</a>

Finds the natural logarithm of this object, that is, the power (exponent)
 that e (the base of natural logarithms) must be raised to in order
 to equal this object's value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the ln function's results are generally not
 exact.</i> (Unlike in the General Decimal Arithmetic Specification,
 any rounding mode is allowed.).

**Returns:**

* Ln(this object). Signals the flag FlagInvalid and returns NaN if
 this object is less than 0 (the result would be a complex number
 with a real part equal to Ln of this object's absolute value and an
 imaginary part equal to pi, but the return value is still NaN.).
 Signals FlagInvalid and returns not-a-number (NaN) if the parameter
 <code>ctx</code> is null or the precision is unlimited (the context's
 Precision property is 0). Signals no flags and returns negative
 infinity if this object's value is 0.

### <a id='Log10(com.upokecenter.numbers.EContext)'>Log10</a>

Finds the base-10 logarithm of this object, that is, the power (exponent)
 that the number 10 must be raised to in order to equal this object's
 value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the ln function's results are generally not
 exact.</i> (Unlike in the General Decimal Arithmetic Specification,
 any rounding mode is allowed.).

**Returns:**

* Ln(this object)/Ln(10). Signals the flag FlagInvalid and returns
 not-a-number (NaN) if this object is less than 0. Signals
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>
 ctx</code> is null or the precision is unlimited (the context's Precision
 property is 0).

### <a id='Log1P(com.upokecenter.numbers.EContext)'>Log1P</a>

Adds 1 to this object's value and finds the natural logarithm of the result,
 in a way that avoids loss of precision when this object's value is
 between 0 and 1.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the ln function's results are generally not
 exact.</i> (Unlike in the General Binary Arithmetic Specification,
 any rounding mode is allowed.).

**Returns:**

* Ln(1+(this object)). Signals the flag FlagInvalid and returns NaN if
 this object is less than -1 (the result would be a complex number
 with a real part equal to Ln of 1 plus this object's absolute value
 and an imaginary part equal to pi, but the return value is still
 NaN.). Signals FlagInvalid and returns not-a-number (NaN) if the
 parameter <code>ctx</code> is null or the precision is unlimited (the
 context's Precision property is 0). Signals no flags and returns
 negative infinity if this object's value is 0.

### <a id='LogN(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>LogN</a>

Finds the base-N logarithm of this object, that is, the power (exponent)
 that the number N must be raised to in order to equal this object's
 value.

**Parameters:**

* <code>baseValue</code> - The parameter <code>baseValue</code> is a Numbers.EDecimal
 object.

* <code>ctx</code> - The parameter <code>ctx</code> is a Numbers.EContext object.

**Returns:**

* Ln(this object)/Ln(baseValue). Signals the flag FlagInvalid and
 returns not-a-number (NaN) if this object is less than 0. Signals
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>
 ctx</code> is null or the precision is unlimited (the context's Precision
 property is 0).

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>baseValue</code> is null.

### <a id='MovePointLeft(int)'>MovePointLeft</a>

Returns a number similar to this number but with the decimal point moved to
 the left.

**Parameters:**

* <code>places</code> - The number of decimal places to move the decimal point to the
 left. If this number is negative, instead moves the decimal point to
 the right by this number's absolute value.

**Returns:**

* A number whose exponent is decreased by <code>places</code>, but not to
 more than 0.

### <a id='MovePointLeft(int,com.upokecenter.numbers.EContext)'>MovePointLeft</a>

Returns a number similar to this number but with the decimal point moved to
 the left.

**Parameters:**

* <code>places</code> - The number of decimal places to move the decimal point to the
 left. If this number is negative, instead moves the decimal point to
 the right by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is decreased by <code>places</code>, but not to
 more than 0.

### <a id='MovePointLeft(com.upokecenter.numbers.EInteger)'>MovePointLeft</a>

Returns a number similar to this number but with the decimal point moved to
 the left.

**Parameters:**

* <code>bigPlaces</code> - The number of decimal places to move the decimal point to
 the left. If this number is negative, instead moves the decimal
 point to the right by this number's absolute value.

**Returns:**

* A number whose exponent is decreased by <code>bigPlaces</code>, but not
 to more than 0.

### <a id='MovePointLeft(com.upokecenter.numbers.EInteger,com.upokecenter.numbers.EContext)'>MovePointLeft</a>

Returns a number similar to this number but with the decimal point moved to
 the left.

**Parameters:**

* <code>bigPlaces</code> - The number of decimal places to move the decimal point to
 the left. If this number is negative, instead moves the decimal
 point to the right by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is decreased by <code>bigPlaces</code>, but not
 to more than 0.

### <a id='MovePointRight(int)'>MovePointRight</a>

Returns a number similar to this number but with the decimal point moved to
 the right.

**Parameters:**

* <code>places</code> - The number of decimal places to move the decimal point to the
 right. If this number is negative, instead moves the decimal point
 to the left by this number's absolute value.

**Returns:**

* A number whose exponent is increased by <code>places</code>, but not to
 more than 0.

### <a id='MovePointRight(int,com.upokecenter.numbers.EContext)'>MovePointRight</a>

Returns a number similar to this number but with the decimal point moved to
 the right.

**Parameters:**

* <code>places</code> - The number of decimal places to move the decimal point to the
 right. If this number is negative, instead moves the decimal point
 to the left by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is increased by <code>places</code>, but not to
 more than 0.

### <a id='MovePointRight(com.upokecenter.numbers.EInteger)'>MovePointRight</a>

Returns a number similar to this number but with the decimal point moved to
 the right.

**Parameters:**

* <code>bigPlaces</code> - The number of decimal places to move the decimal point to
 the right. If this number is negative, instead moves the decimal
 point to the left by this number's absolute value.

**Returns:**

* A number whose exponent is increased by <code>bigPlaces</code>, but not
 to more than 0.

### <a id='MovePointRight(com.upokecenter.numbers.EInteger,com.upokecenter.numbers.EContext)'>MovePointRight</a>

Returns a number similar to this number but with the decimal point moved to
 the right.

**Parameters:**

* <code>bigPlaces</code> - The number of decimal places to move the decimal point to
 the right. If this number is negative, instead moves the decimal
 point to the left by this number's absolute value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* A number whose exponent is increased by <code>bigPlaces</code>, but not
 to more than 0.

### <a id='Multiply(com.upokecenter.numbers.EDecimal)'>Multiply</a>

Multiplies this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result. The exponent for the result is this arbitrary-precision
 decimal floating-point number's exponent plus the other
 arbitrary-precision decimal floating-point number's exponent.

**Parameters:**

* <code>otherValue</code> - Another decimal number.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 decimal floating-point number times another arbitrary-precision
 decimal floating-point number.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>otherValue</code> is null.

### <a id='Multiply(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>Multiply</a>

Multiplies this arbitrary-precision decimal floating-point number by another
 arbitrary-precision decimal floating-point number and returns the
 result.

**Parameters:**

* <code>op</code> - Another decimal number.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 decimal floating-point number times another arbitrary-precision
 decimal floating-point number.

### <a id='Add(long)'>Add</a>

Adds this arbitrary-precision decimal floating-point number and a 64-bit
 signed integer and returns the result. The exponent for the result
 is the lower of this arbitrary-precision decimal floating-point
 number's exponent and the other 64-bit signed integer's exponent.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 decimal floating-point number plus a 64-bit signed integer. If this
 arbitrary-precision decimal floating-point number is not-a-number
 (NaN), returns NaN.

### <a id='Subtract(long)'>Subtract</a>

Subtracts a 64-bit signed integer from this arbitrary-precision decimal
 floating-point number and returns the result. The exponent for the
 result is the lower of this arbitrary-precision decimal
 floating-point number's exponent and the other 64-bit signed
 integer's exponent.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision decimal floating-point number minus a 64-bit
 signed integer. If this arbitrary-precision decimal floating-point
 number is not-a-number (NaN), returns NaN.

### <a id='Multiply(long)'>Multiply</a>

Multiplies this arbitrary-precision decimal floating-point number by a
 64-bit signed integer and returns the result. The exponent for the
 result is this arbitrary-precision decimal floating-point number's
 exponent plus the other 64-bit signed integer's exponent.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 decimal floating-point number times a 64-bit signed integer.

### <a id='Divide(long)'>Divide</a>

Divides this arbitrary-precision decimal floating-point number by a 64-bit
 signed integer and returns the result; returns NaN instead if the
 result would have a nonterminating decimal expansion (including 1/3,
 1/12, 1/7, 2/3, and so on); if this is not desired, use
 DivideToExponent, or use the Divide overload that takes an EContext.

**Parameters:**

* <code>longValue</code> - The parameter <code>longValue</code> is a 64-bit signed integer.

**Returns:**

* The result of dividing this arbitrary-precision decimal
 floating-point number by a 64-bit signed integer. Returns infinity
 if the divisor (this arbitrary-precision decimal floating-point
 number) is 0 and the dividend (the other 64-bit signed integer) is
 nonzero. Returns not-a-number (NaN) if the divisor and the dividend
 are 0. Returns NaN if the result can't be exact because it would
 have a nonterminating binary expansion (examples include 1 divided
 by any multiple of 3, such as 1/3 or 1/12). If this is not desired,
 use DivideToExponent instead, or use the Divide overload that takes
 an <code>EContext</code> (such as <code>EContext.Decimal128</code>) instead.

### <a id='Add(int)'>Add</a>

Adds this arbitrary-precision decimal floating-point number and a 32-bit
 signed integer and returns the result. The exponent for the result
 is the lower of this arbitrary-precision decimal floating-point
 number's exponent and the other 32-bit signed integer's exponent.

**Parameters:**

* <code>intValue</code> - A 32-bit signed integer to add to this object.

**Returns:**

* The sum of the two numbers, that is, this arbitrary-precision
 decimal floating-point number plus a 32-bit signed integer. If this
 arbitrary-precision decimal floating-point number is not-a-number
 (NaN), returns NaN.

### <a id='Subtract(int)'>Subtract</a>

Subtracts a 32-bit signed integer from this arbitrary-precision decimal
 floating-point number and returns the result. The exponent for the
 result is the lower of this arbitrary-precision decimal
 floating-point number's exponent and the other 32-bit signed
 integer's exponent.

**Parameters:**

* <code>intValue</code> - A 32-bit signed integer to subtract from this object.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision decimal floating-point number minus a 32-bit
 signed integer. If this arbitrary-precision decimal floating-point
 number is not-a-number (NaN), returns NaN.

### <a id='Multiply(int)'>Multiply</a>

Multiplies this arbitrary-precision decimal floating-point number by a
 32-bit signed integer and returns the result. The exponent for the
 result is this arbitrary-precision decimal floating-point number's
 exponent plus the other 32-bit signed integer's exponent.

**Parameters:**

* <code>intValue</code> - A 32-bit signed integer to multiply this object by.

**Returns:**

* The product of the two numbers, that is, this arbitrary-precision
 decimal floating-point number times a 32-bit signed integer.

### <a id='Divide(int)'>Divide</a>

Divides this arbitrary-precision decimal floating-point number by a 32-bit
 signed integer and returns the result; returns NaN instead if the
 result would have a nonterminating decimal expansion (including 1/3,
 1/12, 1/7, 2/3, and so on); if this is not desired, use
 DivideToExponent, or use the Divide overload that takes an EContext.

**Parameters:**

* <code>intValue</code> - A 32-bit signed integer, the divisor, to divide this object
 by.

**Returns:**

* The result of dividing this arbitrary-precision decimal
 floating-point number by a 32-bit signed integer. Returns infinity
 if the divisor (this arbitrary-precision decimal floating-point
 number) is 0 and the dividend (the other 32-bit signed integer) is
 nonzero. Returns not-a-number (NaN) if the divisor and the dividend
 are 0. Returns NaN if the result can't be exact because it would
 have a nonterminating binary expansion (examples include 1 divided
 by any multiple of 3, such as 1/3 or 1/12). If this is not desired,
 use DivideToExponent instead, or use the Divide overload that takes
 an <code>EContext</code> (such as <code>EContext.Decimal128</code>) instead.

### <a id='MultiplyAndAdd(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EDecimal)'>MultiplyAndAdd</a>

Multiplies by one decimal number, and then adds another decimal number.

**Parameters:**

* <code>multiplicand</code> - The value to multiply.

* <code>augend</code> - The value to add.

**Returns:**

* An arbitrary-precision decimal floating-point number.

### <a id='MultiplyAndAdd(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>MultiplyAndAdd</a>

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

### <a id='MultiplyAndSubtract(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>MultiplyAndSubtract</a>

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

### <a id='Negate()'>Negate</a>

Gets an object with the same value as this one, but with the sign reversed.

**Returns:**

* An arbitrary-precision decimal number. If this value is positive
 zero, returns negative zero. Returns signaling NaN if this value is
 signaling NaN. (In this sense, this method is similar to the
  "copy-negate" operation in the General Decimal Arithmetic
 Specification, except this method does not necessarily return a copy
 of this object.).

### <a id='Negate(com.upokecenter.numbers.EContext)'>Negate</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but with the sign reversed.

**Parameters:**

* <code>context</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* An arbitrary-precision decimal number. If this value is positive
 zero, returns positive zero. Signals FlagInvalid and returns quiet
 NaN if this value is signaling NaN.

### <a id='NextMinus(com.upokecenter.numbers.EContext)'>NextMinus</a>

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
 FlagInvalid and returns not-a-number (NaN) if the parameter <code>
 ctx</code> is null, the precision is 0, or <code>ctx</code> has an unlimited
 exponent range.

### <a id='NextPlus(com.upokecenter.numbers.EContext)'>NextPlus</a>

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
 parameter <code>ctx</code> is null, the precision is 0, or <code>ctx</code>
 has an unlimited exponent range.

### <a id='NextToward(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>NextToward</a>

Finds the next value that is closer to the other object's value than this
 object's value. Returns a copy of this value with the same sign as
 the other value if both values are equal.

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision decimal number that the return
 value will approach.

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

### <a id='Plus(com.upokecenter.numbers.EContext)'>Plus</a>

Rounds this object's value to a given precision, using the given rounding
 mode and range of exponent, and also converts negative zero to
 positive zero. The idiom <code>EDecimal.SignalingNaN.Plus(ctx)</code> is
 useful for triggering an invalid operation and returning
 not-a-number (NaN) for custom arithmetic operations.

**Parameters:**

* <code>ctx</code> - A context for controlling the precision, rounding mode, and
 exponent range. Can be null, in which case the precision is
 unlimited and rounding isn't needed.

**Returns:**

* The closest value to this object's value, rounded to the specified
 precision. If <code>ctx</code> is null or the precision and exponent
 range are unlimited, returns the same value as this object (or a
 quiet NaN if this object is a signaling NaN).

### <a id='Pow(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>Pow</a>

Raises this object's value to the given exponent.

**Parameters:**

* <code>exponent</code> - An arbitrary-precision decimal number expressing the
 exponent to raise this object's value to.

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

### <a id='Pow(com.upokecenter.numbers.EDecimal)'>Pow</a>

Raises this object's value to the given exponent, using unlimited precision.

**Parameters:**

* <code>exponent</code> - An arbitrary-precision decimal number expressing the
 exponent to raise this object's value to.

**Returns:**

* This^exponent. Returns not-a-number (NaN) if the exponent has a
 fractional part.

### <a id='Pow(int,com.upokecenter.numbers.EContext)'>Pow</a>

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

### <a id='Pow(int)'>Pow</a>

Raises this object's value to the given exponent.

**Parameters:**

* <code>exponentSmall</code> - The exponent to raise this object's value to.

**Returns:**

* This^exponent. Returns not-a-number (NaN) if this object and
 exponent are both 0.

### <a id='Precision()'>Precision</a>

Finds the number of digits in this number's significand. Returns 1 if this
 value is 0, and 0 if this value is infinity or not-a-number (NaN).

**Returns:**

* An arbitrary-precision integer.

### <a id='Quantize(com.upokecenter.numbers.EInteger,com.upokecenter.numbers.EContext)'>Quantize</a>
    /* After performing arithmetic operations, adjust /* the number to 5*/*/ /**/ digits after the decimal point number = number.Quantize(EInteger.FromInt32(-5), /* five digits after the decimal point*/ EContext.ForPrecision(25) /* 25-digit precision);*/
Returns an arbitrary-precision decimal number with the same value but a new
 exponent. <p>Note that this is not always the same as rounding to a
 given number of decimal places, since it can fail if the difference
 between this value's exponent and the desired exponent is too big,
 depending on the maximum precision. If rounding to a number of
 decimal places is desired, it's better to use the RoundToExponent
 and RoundToIntegral methods instead.</p> <p><b>Remark:</b> This
 method can be used to implement fixed-point decimal arithmetic, in
 which each decimal number has a fixed number of digits after the
 decimal point. The following code example returns a fixed-point
 number with up to 20 digits before and exactly 5 digits after the
 decimal point:</p> <pre> /* After performing arithmetic operations, adjust /* the number to 5*/*/ /**/ digits after the decimal point number = number.Quantize(EInteger.FromInt32(-5), /* five digits after the decimal point*/ EContext.ForPrecision(25) /* 25-digit precision);*/</pre> <p>A fixed-point decimal arithmetic in
 which no digits come after the decimal point (a desired exponent of
  0) is considered an "integer arithmetic".</p>

**Parameters:**

* <code>desiredExponent</code> - The desired exponent for the result. The exponent is
 the number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags). Can be null, in which case the default
 rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number with the same value as this
 object but with the exponent changed. Signals FlagInvalid and
 returns not-a-number (NaN) if this object is infinity, if the
 rounded result can't fit the given precision, or if the context
 defines an exponent range and the given exponent is outside that
 range.

### <a id='Quantize(int,com.upokecenter.numbers.ERounding)'>Quantize</a>

Returns an arbitrary-precision decimal number with the same value as this
 one but a new exponent. <p><b>Remark:</b> This method can be used to
 implement fixed-point decimal arithmetic, in which a fixed number of
 digits come after the decimal point. A fixed-point decimal
 arithmetic in which no digits come after the decimal point (a
  desired exponent of 0) is considered an "integer arithmetic" .</p>

**Parameters:**

* <code>desiredExponentInt</code> - The desired exponent for the result. The exponent
 is the number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>rounding</code> - A rounding mode to use in case the result needs to be
 rounded to fit the given exponent.

**Returns:**

* An arbitrary-precision decimal number with the same value as this
 object but with the exponent changed. Returns not-a-number (NaN) if
 this object is infinity, or if the rounding mode is ERounding.None
 and the result is not exact.

### <a id='Quantize(int,com.upokecenter.numbers.EContext)'>Quantize</a>
    /* After performing arithmetic operations, adjust the number to 5 digits after the decimal point */ number = number.Quantize(-5, /* five digits after the decimal point */EContext.ForPrecision(25) /* 25-digit precision*/);
Returns an arbitrary-precision decimal number with the same value but a new
 exponent. <p>Note that this is not always the same as rounding to a
 given number of decimal places, since it can fail if the difference
 between this value's exponent and the desired exponent is too big,
 depending on the maximum precision. If rounding to a number of
 decimal places is desired, it's better to use the RoundToExponent
 and RoundToIntegral methods instead.</p> <p><b>Remark:</b> This
 method can be used to implement fixed-point decimal arithmetic, in
 which each decimal number has a fixed number of digits after the
 decimal point. The following code example returns a fixed-point
 number with up to 20 digits before and exactly 5 digits after the
 decimal point:</p> <pre>/* After performing arithmetic operations, adjust the number to 5 digits after the decimal point */ number = number.Quantize(-5, /* five digits after the decimal point */EContext.ForPrecision(25) /* 25-digit precision*/);</pre> <p>A
 fixed-point decimal arithmetic in which no digits come after the
  decimal point (a desired exponent of 0) is considered an "integer
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
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags). Can be null, in which case the default
 rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number with the same value as this
 object but with the exponent changed. Signals FlagInvalid and
 returns not-a-number (NaN) if this object is infinity, if the
 rounded result can't fit the given precision, or if the context
 defines an exponent range and the given exponent is outside that
 range.

### <a id='Quantize(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>Quantize</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but with the same exponent as another decimal number. <p>Note
 that this is not always the same as rounding to a given number of
 decimal places, since it can fail if the difference between this
 value's exponent and the desired exponent is too big, depending on
 the maximum precision. If rounding to a number of decimal places is
 desired, it's better to use the RoundToExponent and RoundToIntegral
 methods instead.</p> <p><b>Remark:</b> This method can be used to
 implement fixed-point decimal arithmetic, in which a fixed number of
 digits come after the decimal point. A fixed-point decimal
 arithmetic in which no digits come after the decimal point (a
  desired exponent of 0) is considered an "integer arithmetic" .</p>

**Parameters:**

* <code>otherValue</code> - An arbitrary-precision decimal number containing the
 desired exponent of the result. The significand is ignored. The
 exponent is the number of fractional digits in the result, expressed
 as a negative number. Can also be positive, which eliminates
 lower-order places from the number. For example, -3 means round to
 the thousandth (10^-3, 0.0001), and 3 means round to the
 thousands-place (10^3, 1000). A value of 0 rounds the number to an
 integer. The following examples for this parameter express a desired
 exponent of 3: <code>10e3</code>, <code>8888e3</code>, <code>4.56e5</code>.

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags). Can be null, in which case the default
 rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number with the same value as this
 object but with the exponent changed. Signals FlagInvalid and
 returns not-a-number (NaN) if the result can't fit the given
 precision without rounding, or if the arithmetic context defines an
 exponent range and the given exponent is outside that range.

### <a id='Reduce(com.upokecenter.numbers.EContext)'>Reduce</a>

Returns an object with the same numerical value as this one but with
 trailing zeros removed from its significand. For example, 1.00
 becomes 1. <p>If this object's value is 0, changes the exponent to
 0.</p>

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and rounding isn't needed.

**Returns:**

* This value with trailing zeros removed. Note that if the result has
 a very high exponent and the context says to clamp high exponents,
 there may still be some trailing zeros in the significand.

### <a id='Remainder(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>Remainder</a>

Returns the remainder that would result when this arbitrary-precision
 decimal floating-point number is divided by another
 arbitrary-precision decimal floating-point number. The remainder is
 the number that remains when the absolute value of this
 arbitrary-precision decimal floating-point number is divided (as
 though by DivideToIntegerZeroScale) by the absolute value of the
 other arbitrary-precision decimal floating-point number; the
 remainder has the same sign (positive or negative) as this
 arbitrary-precision decimal floating-point number.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result, and of the intermediate integer
 division. If <code>HasFlags</code> of the context is true, will also
 store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null, in which the
 precision is unlimited.

**Returns:**

* The remainder that would result when this arbitrary-precision
 decimal floating-point number is divided by another
 arbitrary-precision decimal floating-point number. Signals
 FlagDivideByZero and returns infinity if the divisor (this
 arbitrary-precision decimal floating-point number) is 0 and the
 dividend (the other arbitrary-precision decimal floating-point
 number) is nonzero. Signals FlagInvalid and returns not-a-number
 (NaN) if the divisor and the dividend are 0, or if the result of the
 division doesn't fit the given precision.

### <a id='RemainderNoRoundAfterDivide(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>RemainderNoRoundAfterDivide</a>

Finds the remainder that results when dividing two arbitrary-precision
 decimal numbers, except the intermediate division is not adjusted to
 fit the precision of the given arithmetic context. The value of this
 object is divided by the absolute value of the other object; the
 remainder has the same sign (positive or negative) as this object's
 value.

**Parameters:**

* <code>divisor</code> - The number to divide by.

* <code>ctx</code> - An arithmetic context object to control the precision, rounding,
 and exponent range of the result, but not also of the intermediate
 integer division. If <code>HasFlags</code> of the context is true, will
 also store the flags resulting from the operation (the flags are in
 addition to the pre-existing flags). Can be null, in which the
 precision is unlimited.

**Returns:**

* The remainder of the two numbers. Signals FlagInvalid and returns
 not-a-number (NaN) if the divisor is 0, or if the result doesn't fit
 the given precision.

### <a id='RemainderNaturalScale(com.upokecenter.numbers.EDecimal)'>RemainderNaturalScale</a>

Calculates the remainder of a number by the formula <code>"this" - (("this" /
  "divisor") * "divisor")</code>.

**Parameters:**

* <code>divisor</code> - The number to divide by.

**Returns:**

* An arbitrary-precision decimal number.

### <a id='RemainderNaturalScale(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>RemainderNaturalScale</a>

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

* An arbitrary-precision decimal number.

### <a id='RemainderNear(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>RemainderNear</a>

Finds the distance to the closest multiple of the given divisor, based on
 the result of dividing this object's value by another object's
 value. <ul> <li>If this and the other object divide evenly, the
 result is 0.</li> <li>If the remainder's absolute value is less than
 half of the divisor's absolute value, the result has the same sign
 as this object and will be the distance to the closest
 multiple.</li> <li>If the remainder's absolute value is more than
 half of the divisor's absolute value, the result has the opposite
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

### <a id='RoundToExponent(com.upokecenter.numbers.EInteger,com.upokecenter.numbers.EContext)'>RoundToExponent</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary. The resulting
 number's Exponent property will not necessarily be the given
 exponent; use the Quantize method instead to give the result a
 particular exponent.

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

* An arbitrary-precision decimal number rounded to the closest value
 representable in the given precision. If the result can't fit the
 precision, additional digits are discarded to make it fit. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to the
 given exponent when rounding, and the given exponent is outside of
 the valid range of the arithmetic context.

### <a id='RoundToExponent(com.upokecenter.numbers.EInteger)'>RoundToExponent</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the
 HalfEven rounding mode. The resulting number's Exponent property
 will not necessarily be the given exponent; use the Quantize method
 instead to give the result a particular exponent.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable for the given exponent.

### <a id='RoundToExponent(com.upokecenter.numbers.EInteger,com.upokecenter.numbers.ERounding)'>RoundToExponent</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the given
 rounding mode. The resulting number's Exponent property will not
 necessarily be the given exponent; use the Quantize method instead
 to give the result a particular exponent.

**Parameters:**

* <code>exponent</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>rounding</code> - Desired mode for rounding this number's value.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable for the given exponent.

### <a id='RoundToExponent(int)'>RoundToExponent</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary, using the
 HalfEven rounding mode. The resulting number's Exponent property
 will not necessarily be the given exponent; use the Quantize method
 instead to give the result a particular exponent.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable for the given exponent.

### <a id='RoundToExponent(int,com.upokecenter.numbers.EContext)'>RoundToExponent</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary. The resulting
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

* An arbitrary-precision decimal number rounded to the closest value
 representable in the given precision. If the result can't fit the
 precision, additional digits are discarded to make it fit. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to the
 given exponent when rounding, and the given exponent is outside of
 the valid range of the arithmetic context.

### <a id='RoundToExponent(int,com.upokecenter.numbers.ERounding)'>RoundToExponent</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to a new exponent if necessary. The resulting
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

* <code>rounding</code> - The desired mode to use to round the given number to the
 given exponent.

**Returns:**

* An arbitrary-precision decimal number rounded to the given negative
 number of decimal places.

### <a id='RoundToExponentExact(com.upokecenter.numbers.EInteger,com.upokecenter.numbers.EContext)'>RoundToExponentExact</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as an
 arbitrary-precision integer, and signals an inexact flag if the
 result would be inexact. The resulting number's Exponent property
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

* An arbitrary-precision decimal number rounded to the closest value
 representable in the given precision. Signals FlagInvalid and
 returns not-a-number (NaN) if the result can't fit the given
 precision without rounding. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to the given exponent when
 rounding, and the given exponent is outside of the valid range of
 the arithmetic context.

### <a id='RoundToExponentExact(int,com.upokecenter.numbers.EContext)'>RoundToExponentExact</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as a 32-bit
 signed integer, and signals an inexact flag if the result would be
 inexact. The resulting number's Exponent property will not
 necessarily be the given exponent; use the Quantize method instead
 to give the result a particular exponent.

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

* An arbitrary-precision decimal number rounded to the closest value
 representable in the given precision. Signals FlagInvalid and
 returns not-a-number (NaN) if the result can't fit the given
 precision without rounding. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to the given exponent when
 rounding, and the given exponent is outside of the valid range of
 the arithmetic context.

### <a id='RoundToExponentExact(int,com.upokecenter.numbers.ERounding)'>RoundToExponentExact</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to the given exponent represented as a 32-bit
 signed integer, and signals an inexact flag if the result would be
 inexact. The resulting number's Exponent property will not
 necessarily be the given exponent; use the Quantize method instead
 to give the result a particular exponent.

**Parameters:**

* <code>exponentSmall</code> - The minimum exponent the result can have. This is the
 maximum number of fractional digits in the result, expressed as a
 negative number. Can also be positive, which eliminates lower-order
 places from the number. For example, -3 means round to the
 thousandth (10^-3, 0.0001), and 3 means round to the thousand (10^3,
 1000). A value of 0 rounds the number to an integer.

* <code>rounding</code> - Desired mode for rounding this object's value.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest value
 representable using the given exponent.

### <a id='RoundToIntegerExact(com.upokecenter.numbers.EContext)'>RoundToIntegerExact</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to an integer, and signals an inexact flag if the
 result would be inexact. The resulting number's Exponent property
 will not necessarily be 0; use the Quantize method instead to give
 the result an exponent of 0.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest integer
 representable in the given precision. Signals FlagInvalid and
 returns not-a-number (NaN) if the result can't fit the given
 precision without rounding. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to 0 when rounding, and 0 is
 outside of the valid range of the arithmetic context.

### <a id='RoundToIntegerNoRoundedFlag(com.upokecenter.numbers.EContext)'>RoundToIntegerNoRoundedFlag</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to an integer, without adding the
 <code>FlagInexact</code> or <code>FlagRounded</code> flags. The resulting
 number's Exponent property will not necessarily be 0; use the
 Quantize method instead to give the result an exponent of 0.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags), except that this function will never add
 the <code>FlagRounded</code> and <code>FlagInexact</code> flags (the only
 difference between this and RoundToExponentExact). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest integer
 representable in the given precision. If the result can't fit the
 precision, additional digits are discarded to make it fit. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to 0
 when rounding, and 0 is outside of the valid range of the arithmetic
 context.

### <a id='RoundToIntegralExact(com.upokecenter.numbers.EContext)'>RoundToIntegralExact</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to an integer, and signals an inexact flag if the
 result would be inexact.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest integer
 representable in the given precision. Signals FlagInvalid and
 returns not-a-number (NaN) if the result can't fit the given
 precision without rounding. Signals FlagInvalid and returns
 not-a-number (NaN) if the arithmetic context defines an exponent
 range, the new exponent must be changed to 0 when rounding, and 0 is
 outside of the valid range of the arithmetic context.

### <a id='RoundToIntegralNoRoundedFlag(com.upokecenter.numbers.EContext)'>RoundToIntegralNoRoundedFlag</a>

Returns an arbitrary-precision decimal number with the same value as this
 object but rounded to an integer, without adding the
 <code>FlagInexact</code> or <code>FlagRounded</code> flags.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control precision and rounding of the
 result. If <code>HasFlags</code> of the context is true, will also store
 the flags resulting from the operation (the flags are in addition to
 the pre-existing flags), except that this function will never add
 the <code>FlagRounded</code> and <code>FlagInexact</code> flags (the only
 difference between this and RoundToExponentExact). Can be null, in
 which case the default rounding mode is HalfEven.

**Returns:**

* An arbitrary-precision decimal number rounded to the closest integer
 representable in the given precision. If the result can't fit the
 precision, additional digits are discarded to make it fit. Signals
 FlagInvalid and returns not-a-number (NaN) if the arithmetic context
 defines an exponent range, the new exponent must be changed to 0
 when rounding, and 0 is outside of the valid range of the arithmetic
 context.

### <a id='RoundToPrecision(com.upokecenter.numbers.EContext)'>RoundToPrecision</a>

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
 precision. Returns the same value as this object if <code>ctx</code> is
 null or the precision and exponent range are unlimited.

### <a id='PreRound(com.upokecenter.numbers.EContext)'>PreRound</a>

Returns a number in which the value of this object is rounded to fit the
 maximum precision allowed if it has more significant digits than the
 maximum precision. The maximum precision allowed is given in an
 arithmetic context. This method is designed for preparing operands
  to a custom arithmetic operation in accordance with the "simplified"
 arithmetic given in Appendix A of the General Decimal Arithmetic
 Specification.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited. Signals the flag LostDigits
 if the input number has greater precision than allowed and was
 rounded to a different numerical value in order to fit the
 precision.

**Returns:**

* This object rounded to the given precision. Returns this object and
 signals no flags if <code>ctx</code> is null or specifies an unlimited
 precision, if this object is infinity or not-a-number (including
 signaling NaN), or if the number's value has no more significant
 digits than the maximum precision given in <code>ctx</code>.

### <a id='ScaleByPowerOfTen(int)'>ScaleByPowerOfTen</a>

Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>places</code> - The power of 10 to scale by.

**Returns:**

* A number whose exponent is increased by <code>places</code>. For example,
  if <code>places</code> is 5, "78E-2" becomes "78E+3" and has a bigger
 value.

### <a id='ScaleByPowerOfTen(int,com.upokecenter.numbers.EContext)'>ScaleByPowerOfTen</a>

Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>places</code> - The power of 10 to scale by.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* A number whose exponent is generally increased by <code>places</code>.
  For example, in general, if <code>places</code> is 5, "78E-2" becomes
  "78E+3" and has a bigger value.

### <a id='ScaleByPowerOfTen(com.upokecenter.numbers.EInteger)'>ScaleByPowerOfTen</a>

Returns a number similar to this number but with the scale adjusted.

**Parameters:**

* <code>bigPlaces</code> - The power of 10 to scale by.

**Returns:**

* A number whose exponent is increased by <code>bigPlaces</code>. For
  example, if <code>bigPlaces</code> is 5, "78E-2" becomes "78E+3" and has
 a bigger value.

### <a id='ScaleByPowerOfTen(com.upokecenter.numbers.EInteger,com.upokecenter.numbers.EContext)'>ScaleByPowerOfTen</a>

Returns a number similar to this number but with its scale adjusted.

**Parameters:**

* <code>bigPlaces</code> - The power of 10 to scale by.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* A number whose exponent is generally increased by <code>bigPlaces</code>.
  For example, in general, if <code>bigPlaces</code> is 5, "78E-2" becomes
  "78E+3" and has a bigger value.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>bigPlaces</code> is null.

### <a id='Sqrt(com.upokecenter.numbers.EContext)'>Sqrt</a>

Finds the square root of this object's value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the square root function's results are generally
 not exact for many inputs.</i> (Unlike in the General Decimal
 Arithmetic Specification, any rounding mode is allowed.).

**Returns:**

* The square root. Signals the flag FlagInvalid and returns NaN if
 this object is less than 0 (the square root would be a complex
 number, but the return value is still NaN). Signals FlagInvalid and
 returns not-a-number (NaN) if the parameter <code>ctx</code> is null or
 the precision is unlimited (the context's Precision property is 0).

### <a id='SquareRoot(com.upokecenter.numbers.EContext)'>SquareRoot</a>

Finds the square root of this object's value.

**Parameters:**

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). <i>This parameter
 can't be null, as the square root function's results are generally
 not exact for many inputs.</i> (Unlike in the General Decimal
 Arithmetic Specification, any rounding mode is allowed.).

**Returns:**

* The square root. Signals the flag FlagInvalid and returns NaN if
 this object is less than 0 (the square root would be a complex
 number, but the return value is still NaN). Signals FlagInvalid and
 returns not-a-number (NaN) if the parameter <code>ctx</code> is null or
 the precision is unlimited (the context's Precision property is 0).

### <a id='Subtract(com.upokecenter.numbers.EDecimal)'>Subtract</a>

Subtracts an arbitrary-precision decimal floating-point number from this
 arbitrary-precision decimal floating-point number and returns the
 result. The exponent for the result is the lower of this
 arbitrary-precision decimal floating-point number's exponent and the
 other arbitrary-precision decimal floating-point number's exponent.

**Parameters:**

* <code>otherValue</code> - The number to subtract from this instance's value.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision decimal floating-point number minus another
 arbitrary-precision decimal floating-point number. If this
 arbitrary-precision decimal floating-point number is not-a-number
 (NaN), returns NaN.

### <a id='Subtract(com.upokecenter.numbers.EDecimal,com.upokecenter.numbers.EContext)'>Subtract</a>

Subtracts an arbitrary-precision decimal floating-point number from this
 arbitrary-precision decimal floating-point number and returns the
 result.

**Parameters:**

* <code>otherValue</code> - The number to subtract from this instance's value.

* <code>ctx</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. If <code>HasFlags</code> of the context is
 true, will also store the flags resulting from the operation (the
 flags are in addition to the pre-existing flags). Can be null, in
 which case the precision is unlimited and no rounding is needed.

**Returns:**

* The difference between the two numbers, that is, this
 arbitrary-precision decimal floating-point number minus another
 arbitrary-precision decimal floating-point number. If this
 arbitrary-precision decimal floating-point number is not-a-number
 (NaN), returns NaN.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>otherValue</code> is null.

### <a id='ToDoubleBits()'>ToDoubleBits</a>

Converts this value to its closest equivalent as a 64-bit floating-point
 number encoded in the IEEE 754 binary64 format, using the half-even
 rounding mode. <p>If this value is a NaN, sets the high bit of the
 binary64 value's significand area for a quiet NaN, and clears it for
 a signaling NaN. Then the other bits of the significand area are set
 to the lowest bits of this object's unsigned significand, and the
 next-highest bit of the significand area is set if those bits are
 all zeros and this is a signaling NaN.</p>

**Returns:**

* The closest 64-bit floating-point number to this value, encoded in
 the IEEE 754 binary64 format. The return value can be positive
 infinity or negative infinity, encoded in the IEEE 754 binary64
 format, if this value exceeds the range of a 64-bit floating point
 number.

### <a id='ToDouble()'>ToDouble</a>

Converts this value to its closest equivalent as a 64-bit floating-point
 number, using the half-even rounding mode. <p>If this value is a
 NaN, sets the high bit of the 64-bit floating point number's
 significand area for a quiet NaN, and clears it for a signaling NaN.
 Then the other bits of the significand area are set to the lowest
 bits of this object's unsigned significand, and the next-highest bit
 of the significand area is set if those bits are all zeros and this
 is a signaling NaN. Unfortunately, in the.NET implementation, the
 return value of this method may be a quiet NaN even if a signaling
 NaN would otherwise be generated.</p>

**Returns:**

* The closest 64-bit floating-point number to this value. The return
 value can be positive infinity or negative infinity if this value
 exceeds the range of a 64-bit floating point number.

### <a id='ToEInteger()'>ToEInteger</a>

Converts this value to an arbitrary-precision integer, discarding the
 fractional part in this value. Note that depending on the value,
 especially the exponent, generating the arbitrary-precision integer
 may require a huge amount of memory. Use the ToSizedEInteger method
 to convert a number to an EInteger only if the integer fits in a
 bounded bit range; that method will throw an exception on overflow.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

* <code>java.lang.UnsupportedOperationException</code> - There is not enough memory to store the value
 as an EInteger.

### <a id='ToEIntegerExact()'>ToEIntegerExact</a>

Converts this value to an arbitrary-precision integer, checking whether the
 fractional part of the value would be lost. Note that depending on
 the value, especially the exponent, generating the
 arbitrary-precision integer may require a huge amount of memory. Use
 the ToSizedEIntegerIfExact method to convert a number to an EInteger
 only if the integer fits in a bounded bit range; that method will
 throw an exception on overflow.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

* <code>java.lang.ArithmeticException</code> - This object's value is not an exact integer.

### <a id='ToEIntegerIfExact()'>ToEIntegerIfExact</a>

Converts this value to an arbitrary-precision integer, checking whether the
 fractional part of the value would be lost. Note that depending on
 the value, especially the exponent, generating the
 arbitrary-precision integer may require a huge amount of memory. Use
 the ToSizedEIntegerIfExact method to convert a number to an EInteger
 only if the integer fits in a bounded bit range; that method will
 throw an exception on overflow.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN).

* <code>java.lang.ArithmeticException</code> - This object's value is not an exact integer.

### <a id='ToEngineeringString()'>ToEngineeringString</a>

Same as toString(), except that when an exponent is used it will be a
 multiple of 3.

**Returns:**

* A text string.

### <a id='ToExtendedFloat()'>ToExtendedFloat</a>

Creates a binary floating-point number from this object's value. Note that
 if the binary floating-point number contains a negative exponent,
 the resulting value might not be exact, in which case the resulting
 binary floating-point number will be an approximation of this
 decimal number's value, using the half-even rounding mode.

**Returns:**

* An arbitrary-precision binary floating-point number.

### <a id='ToEFloat()'>ToEFloat</a>

Creates a binary floating-point number from this object's value. Note that
 if the binary floating-point number contains a negative exponent,
 the resulting value might not be exact, in which case the resulting
 binary floating-point number will be an approximation of this
 decimal number's value, using the half-even rounding mode.

**Returns:**

* An arbitrary-precision binary floating-point number.

### <a id='ToPlainString()'>ToPlainString</a>

Converts this value to a string as though with the toString method, but
 without using exponential notation.

**Returns:**

* A text string.

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

### <a id='ToSingleBits()'>ToSingleBits</a>

Converts this value to its closest equivalent as a 32-bit floating-point
 number encoded in the IEEE 754 binary32 format, using the half-even
 rounding mode. <p>If this value is a NaN, sets the high bit of the
 32-bit floating point number's significand area for a quiet NaN, and
 clears it for a signaling NaN. Then the other bits of the
 significand area are set to the lowest bits of this object's
 unsigned significand, and the next-highest bit of the significand
 area is set if those bits are all zeros and this is a signaling
 NaN.</p>

**Returns:**

* The closest 32-bit binary floating-point number to this value,
 encoded in the IEEE 754 binary32 format. The return value can be
 positive infinity or negative infinity if this value exceeds the
 range of a 32-bit floating point number.

### <a id='ToSingle()'>ToSingle</a>

Converts this value to its closest equivalent as a 32-bit floating-point
 number, using the half-even rounding mode. <p>If this value is a
 NaN, sets the high bit of the 32-bit floating point number's
 significand area for a quiet NaN, and clears it for a signaling NaN.
 Then the other bits of the significand area are set to the lowest
 bits of this object's unsigned significand, and the next-highest bit
 of the significand area is set if those bits are all zeros and this
 is a signaling NaN. Unfortunately, in the.NET implementation, the
 return value of this method may be a quiet NaN even if a signaling
 NaN would otherwise be generated.</p>

**Returns:**

* The closest 32-bit binary floating-point number to this value. The
 return value can be positive infinity or negative infinity if this
 value exceeds the range of a 32-bit floating point number.

### <a id='toString()'>toString</a>

Converts this value to a text string. Returns a value compatible with this
 class's FromString method.

**Overrides:**

* <code>toString</code> in class <code>java.lang.Object</code>

**Returns:**

* A string representation of this object. The text string will be in
 exponential notation (expressed as a number 1 or greater, but less
 than 10, times a power of 10) if this object's Exponent property is
 greater than 0 or if the number's first nonzero decimal digit is
 more than five digits after the decimal point.

### <a id='Ulp()'>Ulp</a>

Returns the unit in the last place. The significand will be 1 and the
 exponent will be this number's exponent. Returns 1 with an exponent
 of 0 if this number is infinity or not-a-number (NaN).

**Returns:**

* An arbitrary-precision decimal number.

### <a id='ToSizedEInteger(int)'>ToSizedEInteger</a>

Converts this value to an arbitrary-precision integer by discarding its
 fractional part and checking whether the resulting integer overflows
 the given signed bit count.

**Parameters:**

* <code>maxBitLength</code> - The maximum number of signed bits the integer can have.
 The integer's value may not be less than -(2^maxBitLength) or
 greater than (2^maxBitLength) - 1.

**Returns:**

* An arbitrary-precision integer.

**Throws:**

* <code>java.lang.ArithmeticException</code> - This object's value is infinity or not-a-number
 (NaN), or this number's value, once converted to an integer by
 discarding its fractional part, is less than -(2^maxBitLength) or
 greater than (2^maxBitLength) - 1.

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
 (NaN), or this number's value, once converted to an integer by
 discarding its fractional part, is less than -(2^maxBitLength) or
 greater than (2^maxBitLength) - 1.

* <code>java.lang.ArithmeticException</code> - This object's value is not an exact integer.

### <a id='ToEFloat(com.upokecenter.numbers.EContext)'>ToEFloat</a>

Creates a binary floating-point number from this object's value. Note that
 if the binary floating-point number contains a negative exponent,
 the resulting value might not be exact, in which case the resulting
 binary floating-point number will be an approximation of this
 decimal number's value.

**Parameters:**

* <code>ec</code> - An arithmetic context to control the precision, rounding, and
 exponent range of the result. The precision is in bits, and an
 example of this parameter is <code>EContext.Binary64</code>. Can be null.

**Returns:**

* An arbitrary-precision floating-point number.

### <a id='Increment()'>Increment</a>

Returns one added to this arbitrary-precision decimal number.

**Returns:**

* The given arbitrary-precision decimal number plus one.

### <a id='Decrement()'>Decrement</a>

Returns one subtracted from this arbitrary-precision decimal number.

**Returns:**

* The given arbitrary-precision decimal number minus one.

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

Converts this number's value to an integer by discarding its fractional
 part, and returns the least-significant bits of its two's-complement
 form as a byte (from 0 to 255).

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

Converts a byte (from 0 to 255) to an arbitrary-precision decimal number.

**Parameters:**

* <code>inputByte</code> - The number to convert as a byte (from 0 to 255).

**Returns:**

* This number's value as an arbitrary-precision decimal number.

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

Converts a 16-bit signed integer to an arbitrary-precision decimal number.

**Parameters:**

* <code>inputInt16</code> - The number to convert as a 16-bit signed integer.

**Returns:**

* This number's value as an arbitrary-precision decimal number.

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
