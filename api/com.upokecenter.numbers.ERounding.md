# com.upokecenter.numbers.ERounding

    public enum ERounding extends Enum<ERounding>

Specifies the mode to use when &#x22;shortening&#x22; numbers that otherwise
 can&#x27;t fit a given number of digits, so that the shortened number
 has about the same value. This &#x22;shortening&#x22; is known as
 rounding. (The "E" stands for "extended", and has this prefix to
 group it with the other classes common to this library, particularly
 EDecimal, EFloat, and ERational.).

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
 number is rounded to the closest representable number away from zero.
* `None`<br>
 Indicates that rounding will not be used.
* `Odd`<br>
 If there is a fractional part and the whole number part is even, the number
 is rounded to the closest representable odd number away from zero.
* `OddOrZeroFiveUp`<br>
 For binary floating point numbers, this is the same as Odd.
* `Up`<br>
 If there is a fractional part, the number is rounded to the closest
 representable number away from zero.
* `ZeroFiveUp`<br>
 If there is a fractional part and if the last digit before rounding is 0 or
 half the radix, the number is rounded to the closest representable
 number away from zero; otherwise the fractional part is discarded.

## Methods

* `static ERounding valueOf​(String name)`<br>
 Returns the enum constant of this type with the specified name.
* `static ERounding[] values()`<br>
 Returns an array containing the constants of this enum type, in
the order they are declared.

## Method Details

### None
    public static final ERounding None
### Up
    public static final ERounding Up
### Down
    public static final ERounding Down
### HalfUp
    public static final ERounding HalfUp
### HalfDown
    public static final ERounding HalfDown
### HalfEven
    public static final ERounding HalfEven
### Ceiling
    public static final ERounding Ceiling
### Floor
    public static final ERounding Floor
### Odd
    public static final ERounding Odd
### ZeroFiveUp
    public static final ERounding ZeroFiveUp
### OddOrZeroFiveUp
    public static final ERounding OddOrZeroFiveUp
### values
    public static ERounding[] values()
### valueOf
    public static ERounding valueOf​(String name)
## Enum Constant Details

### None
    public static final ERounding None
Indicates that rounding will not be used. If rounding is required, the
 rounding operation will report an error.
### Up
    public static final ERounding Up
If there is a fractional part, the number is rounded to the closest
 representable number away from zero.
### Down
    public static final ERounding Down
The fractional part is discarded (the number is truncated).
### HalfUp
    public static final ERounding HalfUp
Rounded to the nearest number; if the fractional part is exactly half, the
 number is rounded to the closest representable number away from zero.
 This is the most familiar rounding mode for many people.
### HalfDown
    public static final ERounding HalfDown
Rounded to the nearest number; if the fractional part is exactly half, it is
 discarded.
### HalfEven
    public static final ERounding HalfEven
Rounded to the nearest number; if the fractional part is exactly half, the
 number is rounded to the closest representable number that is even.
 This is sometimes also known as &#x22;banker&#x27;s rounding&#x22;.
### Ceiling
    public static final ERounding Ceiling
If there is a fractional part, the number is rounded to the highest
 representable number that&#x27;s closest to it.
### Floor
    public static final ERounding Floor
If there is a fractional part, the number is rounded to the lowest
 representable number that&#x27;s closest to it.
### Odd
    public static final ERounding Odd
If there is a fractional part and the whole number part is even, the number
 is rounded to the closest representable odd number away from zero.
### ZeroFiveUp
    public static final ERounding ZeroFiveUp
If there is a fractional part and if the last digit before rounding is 0 or
 half the radix, the number is rounded to the closest representable
 number away from zero; otherwise the fractional part is discarded. In
 overflow, the fractional part is always discarded.
### OddOrZeroFiveUp
    public static final ERounding OddOrZeroFiveUp
For binary floating point numbers, this is the same as Odd. For other bases
 (including decimal numbers), this is the same as ZeroFiveUp. This
 rounding mode is useful for rounding intermediate results at a
 slightly higher precision (at least 2 bits more for binary) than the
 final precision.
