package com.upokecenter.test;
/*
Written in 2013-2016 by Peter O.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://upokecenter.dreamhosters.com/articles/donate-now-2/
 */

import org.junit.Assert;

  public final class TestCommon {
private TestCommon() {
}
    private static final String ValueDigits = "0123456789";

    public static void AssertByteArraysEqual(byte[] arr1, byte[] arr2) {
      if (!ByteArraysEqual(arr1, arr2)) {
        Assert.fail("Expected " + ToByteArrayString(arr1) + ", got " +
          ToByteArrayString(arr2));
      }
    }

    public static void AssertEqualsHashCode(Object o, Object o2) {
      if (o.equals(o2)) {
        if (!o2.equals(o)) {
          Assert.fail(
"" + o + " equals " + o2 + " but not vice versa");
        }
        // Test for the guarantee that equal objects
        // must have equal hash codes
        if (o2.hashCode() != o.hashCode()) {
          // Don't use Assert.assertEquals directly because it has
          // quite a lot of overhead
          Assert.fail(
"" + o + " and " + o2 + " don't have equal hash codes");
        }
      } else {
        if (o2.equals(o)) {
          Assert.fail("" + o + " does not equal " + o2 +
            " but not vice versa");
        }
        // At least check that hashCode doesn't throw
        try {
          o.hashCode();
        } catch (Exception ex) {
          Assert.fail(ex.toString());
          throw new IllegalStateException("", ex);
        }
        try {
          o2.hashCode();
        } catch (Exception ex) {
          Assert.fail(ex.toString());
          throw new IllegalStateException("", ex);
        }
      }
    }

    public static <T extends Comparable<T>> void CompareTestConsistency(T o1, T o2, T o3) {
      if (o1 == null) {
        throw new NullPointerException("o1");
      }
      if (o2 == null) {
        throw new NullPointerException("o2");
      }
      if (o3 == null) {
        throw new NullPointerException("o3");
      }
      int cmp = CompareTestReciprocal(o1, o2);
      int cmp2 = CompareTestReciprocal(o2, o3);
      int cmp3 = CompareTestReciprocal(o1, o3);
      Assert.assertEquals(cmp == 0, o1.equals(o2));
      Assert.assertEquals(cmp == 0, o2.equals(o1));
      Assert.assertEquals(cmp2 == 0, o2.equals(o3));
      Assert.assertEquals(cmp2 == 0, o3.equals(o2));
      Assert.assertEquals(cmp3 == 0, o1.equals(o3));
      Assert.assertEquals(cmp3 == 0, o3.equals(o1));
    }

    public static <T extends Comparable<T>> void CompareTestEqual(T o1, T o2) {
      if (CompareTestReciprocal(o1, o2) != 0) {
        Assert.fail(ObjectMessages(
          o1,
          o2,
          "Not equal: " + CompareTestReciprocal(o1, o2)));
      }
    }

    public static <T extends Comparable<T>> void CompareTestEqual(T o1, T o2, String msg) {
      if (CompareTestReciprocal(o1, o2) != 0) {
        String str = msg + "\r\n" + ObjectMessages(
          o1,
          o2,
          "Not equal: " + CompareTestReciprocal(o1, o2));
        Assert.fail(str);
      }
    }

    public static <T extends Comparable<T>> void CompareTestEqualAndConsistent(T o1, T o2) {
      CompareTestEqualAndConsistent(o1, o2, null);
    }

    public static <T extends Comparable<T>> void CompareTestEqualAndConsistent(
T o1,
T o2,
String msg) {
      if (CompareTestReciprocal(o1, o2) != 0) {
        msg = (msg == null ? "" : (msg + "\r\n")) +
          "Not equal: " + CompareTestReciprocal(o1, o2);
        Assert.fail(ObjectMessages(
          o1,
          o2,
          msg));
      }
      if (!o1.equals(o2)) {
        msg = (msg == null ? "" : (msg + "\r\n")) +
          "Not equal: " + CompareTestReciprocal(o1, o2);
        Assert.fail(ObjectMessages(
          o1,
          o2,
          msg));
      }
    }

    public static <T extends Comparable<T>> void CompareTestGreater(T o1, T o2) {
      CompareTestLess(o2, o1);
    }

    public static <T extends Comparable<T>> void CompareTestLess(T o1, T o2) {
      if (CompareTestReciprocal(o1, o2) >= 0) {
        Assert.fail(ObjectMessages(
          o1,
          o2,
          "Not less: " + CompareTestReciprocal(o1, o2)));
      }
    }

    public static <T extends Comparable<T>> int CompareTestReciprocal(T o1, T o2) {
      if (o1 == null) {
        throw new NullPointerException("o1");
      }
      if (o2 == null) {
        throw new NullPointerException("o2");
      }
      int cmp, cmp2;
      cmp = ((o1.compareTo(o2) == 0) ? 0 : ((o1.compareTo(o2)< 0) ? -1 : 1));
      cmp2 = ((o2.compareTo(o1) == 0) ? 0 : ((o2.compareTo(o1)< 0) ? -1 : 1));
      if (-cmp2 != cmp) {
        Assert.assertEquals(ObjectMessages(o1, o2, "Not reciprocal"),cmp,-cmp2);
      }
      return cmp;
    }

    public static <T extends Comparable<T>> void CompareTestRelations(T o1, T o2, T o3) {
      if (o1 == null) {
        throw new NullPointerException("o1");
      }
      if (o2 == null) {
        throw new NullPointerException("o2");
      }
      if (o3 == null) {
        throw new NullPointerException("o3");
      }
      if (o1.compareTo(o1) != 0) {
        Assert.fail(o1.toString());
      }
      if (o2.compareTo(o2) != 0) {
        Assert.fail(o2.toString());
      }
      if (o3.compareTo(o3) != 0) {
        Assert.fail(o3.toString());
      }
      int cmp12 = CompareTestReciprocal(o1, o2);
      int cmp23 = CompareTestReciprocal(o2, o3);
      int cmp13 = CompareTestReciprocal(o1, o3);
      // CompareTestReciprocal tests compareTo both
      // ways, so shortcutting by negating the values
      // is allowed here
      int cmp21 = -cmp12;
      int cmp32 = -cmp23;
      int cmp31 = -cmp13;
      // Transitivity checks
      for (int i = -1; i <= 1; ++i) {
        if (cmp12 == i) {
          if (cmp23 == i && cmp13 != i) {
            Assert.fail(ObjectMessages(o1, o2, o3, "Not transitive"));
          }
        }
        if (cmp23 == i) {
          if (cmp31 == i && cmp21 != i) {
            Assert.fail(ObjectMessages(o1, o2, o3, "Not transitive"));
          }
        }
        if (cmp31 == i) {
          if (cmp12 == i && cmp32 != i) {
            Assert.fail(ObjectMessages(o1, o2, o3, "Not transitive"));
          }
        }
      }
    }

    public static String IntToString(int value) {
      if (value == Integer.MIN_VALUE) {
        return "-2147483648";
      }
      if (value == 0) {
        return "0";
      }
      boolean neg = value < 0;
      char[] chars = new char[12];
      int count = 11;
      if (neg) {
        value = -value;
      }
      while (value > 43698) {
        int intdivvalue = value / 10;
        char digit = ValueDigits.charAt((int)(value - (intdivvalue * 10)));
        chars[count--] = digit;
        value = intdivvalue;
      }
      while (value > 9) {
        int intdivvalue = (value * 26215) >> 18;
        char digit = ValueDigits.charAt((int)(value - (intdivvalue * 10)));
        chars[count--] = digit;
        value = intdivvalue;
      }
      if (value != 0) {
        chars[count--] = ValueDigits.charAt((int)value);
      }
      if (neg) {
        chars[count] = '-';
      } else {
        ++count;
      }
      return new String(chars, count, 12 - count);
    }

    public static String LongToString(long longValue) {
      if (longValue == Long.MIN_VALUE) {
        return "-9223372036854775808";
      }
      if (longValue == 0L) {
        return "0";
      }
      if (longValue == (long)Integer.MIN_VALUE) {
        return "-2147483648";
      }
      boolean neg = longValue < 0;
      int count = 0;
      char[] chars;
      int intlongValue = ((int)longValue);
      if ((long)intlongValue == longValue) {
        chars = new char[12];
        if (neg) {
          chars[0] = '-';
          ++count;
          intlongValue = -intlongValue;
        }
        while (intlongValue != 0) {
          int intdivlongValue = intlongValue / 10;
          char digit = ValueDigits.charAt((int)(intlongValue - (intdivlongValue *
              10)));
          chars[count++] = digit;
          intlongValue = intdivlongValue;
        }
      } else {
        chars = new char[24];
        if (neg) {
          chars[0] = '-';
          ++count;
          longValue = -longValue;
        }
        while (longValue != 0) {
          long divlongValue = longValue / 10;
          char digit = ValueDigits.charAt((int)(longValue - (divlongValue * 10)));
          chars[count++] = digit;
          longValue = divlongValue;
        }
      }
      if (neg) {
        ReverseChars(chars, 1, count - 1);
      } else {
        ReverseChars(chars, 0, count);
      }
      return new String(chars, 0, count);
    }

    public static String ObjectMessages(
      Object o1,
      Object o2,
      String s) {
      return s + ":\n" + o1 + " and\n" + o2;
    }

    public static String ObjectMessages(
      Object o1,
      Object o2,
      Object o3,
      String s) {
      return s + ":\n" + o1 + " and\n" + o2 + " and\n" + o3;
    }

    public static String Repeat(char c, int num) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < num; ++i) {
        sb.append(c);
      }
      return sb.toString();
    }

    public static String Repeat(String c, int num) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < num; ++i) {
        sb.append(c);
      }
      return sb.toString();
    }

    public static String ToByteArrayString(byte[] bytes) {
      if (bytes == null) {
        return "null";
      }
      StringBuilder sb = new StringBuilder();
      String ValueHex = "0123456789ABCDEF";
      sb.append("new byte[] { ");
      for (int i = 0; i < bytes.length; ++i) {
        if (i > 0) {
          sb.append(",");  }
        if ((bytes[i] & 0x80) != 0) {
          sb.append("(byte)0x");
        } else {
          sb.append("0x");
        }
        sb.append(ValueHex.charAt((bytes[i] >> 4) & 0xf));
        sb.append(ValueHex.charAt(bytes[i] & 0xf));
      }
      sb.append("}");
      return sb.toString();
    }

    private static boolean ByteArraysEqual(byte[] arr1, byte[] arr2) {
      if (arr1 == null) {
        return arr2 == null;
      }
      if (arr2 == null) {
        return false;
      }
      if (arr1.length != arr2.length) {
        return false;
      }
      for (int i = 0; i < arr1.length; ++i) {
        if (arr1[i] != arr2[i]) {
          return false;
        }
      }
      return true;
    }

    private static void ReverseChars(char[] chars, int offset, int length) {
      int half = length >> 1;
      int right = offset + length - 1;
      for (int i = 0; i < half; i++, right--) {
        char value = chars[offset + i];
        chars[offset + i] = chars[right];
        chars[right] = value;
      }
    }
  }
