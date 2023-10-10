package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
https://creativecommons.org/publicdomain/zero/1.0/

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
