package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
https://creativecommons.org/publicdomain/zero/1.0/

 */

  /**
   * <p>Specifies the mode to use when "shortening" numbers that otherwise can't
   * fit a given number of digits, so that the shortened number has about the
   * same value. This "shortening" is known as rounding. (The "E" stands for
   * "extended", and has this prefix to group it with the other classes common to
   * this library, particularly EDecimal, EFloat, and ERational.).</p>
   *
   */
  public enum ERounding {
    /**
     * <p>Indicates that rounding will not be used. If rounding to an inexact value
     * is required, the rounding operation will report an error.</p>
     *
     */
    None,

    /**
     * <p>If there is a fractional part, the number is rounded to the closest
     * representable number away from zero.</p>
     *
     */
    Up,

    /**
     * <p>The fractional part is discarded (the number is truncated).</p>
     *
     */
    Down,

    /**
     * <p>Rounded to the nearest number; if the fractional part is exactly half,
     * the number is rounded to the closest representable number away from zero.
     * This is the most familiar rounding mode for many people.</p>
     *
     */
    HalfUp,

    /**
     * <p>Rounded to the nearest number; if the fractional part is exactly half, it
     * is discarded.</p>
     *
     */
    HalfDown,

    /**
     * <p>Rounded to the nearest number; if the fractional part is exactly half,
     * the number is rounded to the closest representable number that is even. This
     * is sometimes also known as "banker's rounding".</p>
     *
     */
    HalfEven,

    /**
     * <p>If there is a fractional part, the number is rounded to the highest
     * representable number that's closest to it.</p>
     *
     */
    Ceiling,

    /**
     * <p>If there is a fractional part, the number is rounded to the lowest
     * representable number that's closest to it.</p>
     *
     */
    Floor,

    /**
     * <p>If there is a fractional part and the whole number part is even, the
     * number is rounded to the closest representable odd number away from
     * zero.</p>
     *
     * @deprecated Consider using ERounding.OddOrZeroFiveUp instead.
 */
@Deprecated
    Odd,

    /**
     * <p>If there is a fractional part and if the last digit before rounding is 0
     * or half the radix, the number is rounded to the closest representable number
     * away from zero; otherwise the fractional part is discarded. In overflow, the
     * fractional part is always discarded.</p>
     *
     * @deprecated Use ERounding.OddOrZeroFiveUp instead.
 */
@Deprecated
    ZeroFiveUp,

    /**
     * <p>For binary floating point numbers, this is the same as Odd. For other
     * bases (including decimal numbers), this is the same as ZeroFiveUp. This
     * rounding mode is useful for rounding intermediate results at a slightly
     * higher precision (at least 2 bits more for binary) than the final
     * precision.</p>
     *
     */
    OddOrZeroFiveUp,
  }
