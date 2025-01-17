# com.upokecenter.numbers.ERounding

    public enum ERounding extends Enum<ERounding>

Specifies the mode to use when "shortening" numbers that otherwise can't fit
 a given number of digits, so that the shortened number has about the same
 value. This "shortening" is known as rounding. (The "E" stands for
 "extended", and has this prefix to group it with the other classes common to
 this library, particularly EDecimal, EFloat, and ERational.).

## Nested Classes

## Enum Constants

* `Ceiling `<br>
 If there is a fractional part, the number is rounded to the highest
 representable number that's closest to it.

* `Down `<br>
 The fractional part is discarded (the number is truncated).

* `Floor `<br>
 If there is a fractional part, the number is rounded to the lowest
 representable number that's closest to it.

* `HalfDown `<br>
 Rounded to the nearest number; if the fractional part is exactly half, it is
 discarded.

* `HalfEven `<br>
 Rounded to the nearest number; if the fractional part is exactly half, the
 number is rounded to the closest representable number that is even.

* `HalfUp `<br>
 Rounded to the nearest number; if the fractional part is exactly half, the
 number is rounded to the closest representable number away from zero.

* `None `<br>
 Indicates that rounding will not be used.

* `Odd `<br>
 Deprecated.
Consider using ERounding.OddOrZeroFiveUp instead.

* `OddOrZeroFiveUp `<br>
 For binary floating point numbers, this is the same as Odd.

* `Up `<br>
 If there is a fractional part, the number is rounded to the closest
 representable number away from zero.

* `ZeroFiveUp `<br>
 Deprecated.
Use ERounding.OddOrZeroFiveUp instead.

## Methods

* `static ERounding valueOf(String name)`<br>
 Returns the enum constant of this class with the specified name.

* `static ERounding[] values()`<br>
 Returns an array containing the constants of this enum class, in
the order they are declared.

## Method Details

### values

    public static ERounding[] values()

### valueOf

    public static ERounding valueOf(String name)
