package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
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
