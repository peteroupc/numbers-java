# com.upokecenter.numbers.ERounding

## Nested Classes

## Enum Constants

* `Ceiling`<br>
 If there is a fractional part, the number is rounded to the highest
 representable number that's closest to it.
* `Down`<br>
 The fractional part is discarded (the number is truncated).
* `Floor`<br>
 If there is a fractional part, the number is rounded to the lowest
 representable number that's closest to it.
* `HalfDown`<br>
 Rounded to the nearest number; if the fractional part is exactly half, it is
 discarded.
* `HalfEven`<br>
 Rounded to the nearest number; if the fractional part is exactly half, the
 number is rounded to the closest representable number that is even.
* `HalfUp`<br>
 Rounded to the nearest number; if the fractional part is exactly half, the
 number is rounded to the closest representable number away from
 zero.
* `None`<br>
 Indicates that rounding will not be used.
* `Odd`<br>
 Deprecated.
Consider using ERounding.OddOrZeroFiveUp instead.
 Consider using ERounding.OddOrZeroFiveUp instead.
* `OddOrZeroFiveUp`<br>
 For binary floating point numbers, this is the same as Odd.
* `Up`<br>
 If there is a fractional part, the number is rounded to the closest
 representable number away from zero.
* `ZeroFiveUp`<br>
 Deprecated.
Use ERounding.OddOrZeroFiveUp instead.
 Use ERounding.OddOrZeroFiveUp instead.

## Methods

* `static ERounding valueOf​(java.lang.String name)`<br>
 Returns the enum constant of this type with the specified name.
* `static ERounding[] values()`<br>
 Returns an array containing the constants of this enum type, in
the order they are declared.

## Enum Constant Details

### <a id='None'>None</a>

Indicates that rounding will not be used. If rounding to an inexact value is
 required, the rounding operation will report an error.
### <a id='Up'>Up</a>

If there is a fractional part, the number is rounded to the closest
 representable number away from zero.
### <a id='Down'>Down</a>

The fractional part is discarded (the number is truncated).
### <a id='HalfUp'>HalfUp</a>

Rounded to the nearest number; if the fractional part is exactly half, the
 number is rounded to the closest representable number away from
 zero. This is the most familiar rounding mode for many people.
### <a id='HalfDown'>HalfDown</a>

Rounded to the nearest number; if the fractional part is exactly half, it is
 discarded.
### <a id='HalfEven'>HalfEven</a>

Rounded to the nearest number; if the fractional part is exactly half, the
 number is rounded to the closest representable number that is even.
  This is sometimes also known as "banker's rounding".
### <a id='Ceiling'>Ceiling</a>

If there is a fractional part, the number is rounded to the highest
 representable number that's closest to it.
### <a id='Floor'>Floor</a>

If there is a fractional part, the number is rounded to the lowest
 representable number that's closest to it.
### <a id='Odd'>Odd</a>

If there is a fractional part and the whole number part is even, the number
 is rounded to the closest representable odd number away from zero.
### <a id='ZeroFiveUp'>ZeroFiveUp</a>

If there is a fractional part and if the last digit before rounding is 0 or
 half the radix, the number is rounded to the closest representable
 number away from zero; otherwise the fractional part is discarded.
 In overflow, the fractional part is always discarded.
### <a id='OddOrZeroFiveUp'>OddOrZeroFiveUp</a>

For binary floating point numbers, this is the same as Odd. For other bases
 (including decimal numbers), this is the same as ZeroFiveUp. This
 rounding mode is useful for rounding intermediate results at a
 slightly higher precision (at least 2 bits more for binary) than the
 final precision.
## Method Details

### <a id='values()'>values</a>

### <a id='valueOf(java.lang.String)'>valueOf</a>
