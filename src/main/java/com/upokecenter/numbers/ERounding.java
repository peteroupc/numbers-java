package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
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
