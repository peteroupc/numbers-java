package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under the Unlicense: https://unlicense.org/

 */

  final class BigNumberFlags {
private BigNumberFlags() {
}
    static final int FlagNegative = 1;
    static final int FlagQuietNaN = 4;
    static final int FlagSignalingNaN = 8;
    static final int FlagInfinity = 2;
    static final int FlagSpecial = FlagQuietNaN | FlagSignalingNaN |
      FlagInfinity;
    static final int FlagNaN = FlagQuietNaN | FlagSignalingNaN;
    static final int LostDigitsFlags = EContext.FlagLostDigits |
      EContext.FlagInexact | EContext.FlagRounded;
    static final int FiniteOnly = 0;
    static final int FiniteAndNonFinite = 1;
  }
