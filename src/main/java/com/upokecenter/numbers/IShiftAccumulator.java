package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under the Unlicense: https://unlicense.org/

 */

  /**
   * Common interface for classes that shift a number of digits and record
   * information on whether a non-zero digit was discarded this way.
   */
  interface IShiftAccumulator {
    EInteger getShiftedInt();

    FastInteger GetDigitLength();

    FastInteger OverestimateDigitLength();

    int getOlderDiscardedDigits();

    int getLastDiscardedDigit();

    FastInteger getShiftedIntFast();

    FastInteger getDiscardedDigitCount();

    void TruncateOrShiftRight(FastInteger bits, boolean truncate);

    int ShiftedIntMod(int mod);

    void ShiftRightInt(int bits);

    void ShiftToDigits(FastInteger bits, FastInteger preShift, boolean truncate);
  }
