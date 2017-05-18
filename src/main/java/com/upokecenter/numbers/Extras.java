package com.upokecenter.numbers;
/*
Written by Peter O. in 2013.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

  final class Extras {
private Extras() {
}
    public static int[] DoubleToIntegers(double dbl) {
      long value = Double.doubleToRawLongBits(dbl);
      int[] ret = new int[2];
      ret[0] = ((int)(value & 0xffffffffL));
      ret[1] = ((int)((value >> 32) & 0xffffffffL));
      return ret;
    }

    public static double IntegersToDouble(int[] integers) {
      // NOTE: least significant word first
      long value = ((long)integers[0]) & 0xffffffffL;
      value |= (((long)integers[1]) & 0xffffffffL) << 32;
      return Double.longBitsToDouble(value);
    }
  }
