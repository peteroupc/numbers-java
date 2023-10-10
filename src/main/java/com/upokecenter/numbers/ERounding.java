package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
https://creativecommons.org/publicdomain/zero/1.0/

 */

  public enum ERounding {
    None,

    Up,

    Down,

    HalfUp,

    HalfDown,

    HalfEven,

    Ceiling,

    Floor,

/**
 * @deprecated Consider using ERounding.OddOrZeroFiveUp instead.
 */
@Deprecated
    Odd,

/**
 * @deprecated Use ERounding.OddOrZeroFiveUp instead.
 */
@Deprecated
    ZeroFiveUp,

    OddOrZeroFiveUp,
  }
