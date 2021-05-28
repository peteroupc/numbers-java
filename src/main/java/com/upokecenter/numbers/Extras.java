package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
http://creativecommons.org/publicdomain/zero/1.0/

 */

  final class Extras {
private Extras() {
}
    public static byte[] CharsConcat(
      byte[] c1,
      int offset1,
      int length1,
      byte[] c2,
      int offset2,
      int length2) {
      byte[] chars = new byte[length1 + length2];
      System.arraycopy(c1, offset1, chars, 0, length1);
      System.arraycopy(c2, offset2, chars, length1, length2);
      return chars;
    }
    public static String CharsConcat(
      String s1,
      int offset1,
      int length1,
      String s2,
      int offset2,
      int length2) {
      // System.out.println(s1.substring(offset1, (offset1)+(length1)));
      // System.out.println(s2.substring(offset2, (offset2)+(length2)));
      return s1.substring(offset1, (offset1)+(length1)) + s2.substring(offset2, (offset2)+(length2));
    }

    public static char[] CharsConcat(
      char[] c1,
      int offset1,
      int length1,
      char[] c2,
      int offset2,
      int length2) {
      char[] chars = new char[length1 + length2];
      System.arraycopy(c1, offset1, chars, 0, length1);
      System.arraycopy(c2, offset2, chars, length1, length2);
      return chars;
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
      return IntegersToDouble(integers[0], integers[1]);
    }

    public static double IntegersToDouble(int lsw, int msw) {
      // NOTE: least significant word first
      long value = ((long)lsw) & 0xffffffffL;
      value |= (((long)msw) & 0xffffffffL) << 32;
      return Double.longBitsToDouble(value);
    }
  }
