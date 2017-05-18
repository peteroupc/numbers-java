package com.upokecenter.numbers;
/*
Written by Peter O. in 2014.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

    /**
     * Common interface for classes that shift a number of digits and record
     * information on whether a non-zero digit was discarded this way.
     */
  interface IShiftAccumulator {
    EInteger getShiftedInt();

    FastInteger GetDigitLength();

    int getOlderDiscardedDigits();

    int getLastDiscardedDigit();

    FastInteger getShiftedIntFast();

    FastInteger getDiscardedDigitCount();

    void TruncateRight(FastInteger bits);

    void ShiftRight(FastInteger bits);

    void ShiftRightInt(int bits);

    void ShiftToDigits(FastInteger bits, FastInteger preShift, boolean truncate);
  }
