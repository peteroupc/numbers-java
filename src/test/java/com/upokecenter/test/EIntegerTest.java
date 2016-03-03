package com.upokecenter.test;

import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.numbers.*;

  public class EIntegerTest {
private static long[] valueBitLengths = { -4294967297L, 33L, -4294967296L,
      32L,
    -4294967295L, 32L, -2147483649L, 32L, -2147483648L, 31L, -2147483647L,
        31L,
      -1073741825L, 31L, -1073741824L, 30L, -1073741823L, 30L, -536870913L, 30L,
      -536870912L, 29L, -536870911L, 29L, -268435457L, 29L, -268435456L, 28L,
      -268435455L, 28L, -134217729L, 28L, -134217728L, 27L, -134217727L, 27L,
-67108865L, 27L, -67108864L, 26L, -67108863L, 26L, -33554433L, 26L,
        -33554432L,
      25L, -33554431L, 25L, -16777217L, 25L, -16777216L, 24L, -16777215L, 24L,
-8388609L, 24L, -8388608L, 23L, -8388607L, 23L, -4194305L, 23L, -4194304L,
        22L,
-4194303L, 22L, -2097153L, 22L, -2097152L, 21L, -2097151L, 21L, -1048577L,
        21L,
   -1048576L, 20L, -1048575L, 20L, -524289L, 20L, -524288L, 19L, -524287L,
        19L,
      -262145L, 19L, -262144L, 18L, -262143L, 18L, -131073L, 18L, -131072L, 17L,
-131071L, 17L, -65537L, 17L, -65536L, 16L, -65535L, 16L, -32769L, 16L,
        -32768L,
      15L, -32767L, 15L, -16385L, 15L, -16384L, 14L, -16383L, 14L, -8193L,
        14L, -8192L,
      13L, -8191L, 13L, -4097L, 13L, -4096L, 12L, -4095L, 12L, -2049L, 12L,
        -2048L, 11L,
      -2047L, 11L, -1025L, 11L, -1024L, 10L, -1023L, 10L, -513L, 10L, -512L,
        9L, -511L,
      9L, -257L, 9L, -256L, 8L, -255L, 8L, -129L, 8L, -128L, 7L, -127L, 7L,
        -65L, 7L, -64L,
      6L, -63L, 6L, -33L, 6L, -32L, 5L, -31L, 5L, -17L, 5L, -16L, 4L, -15L,
        4L, -9L, 4L, -8L,
      3L, -7L, 3L, -5L, 3L, -4L, 2L, -3L, 2L, -2L, 1L, -1L, 0L, 0L, 0L, 1L,
        1L, 2L, 2L, 3L, 2L,
      4L, 3L, 5L, 3L, 7L, 3L, 8L, 4L, 9L, 4L, 15L, 4L, 16L, 5L, 17L, 5L,
        31L, 5L, 32L, 6L, 33L,
      6L, 63L, 6L, 64L, 7L, 65L, 7L, 127L, 7L, 128L, 8L, 129L, 8L, 255L, 8L,
        256L, 9L, 257L,
      9L, 511L, 9L, 512L, 10L, 513L, 10L, 1023L, 10L, 1024L, 11L, 1025L,
        11L, 2047L, 11L,
      2048L, 12L, 2049L, 12L, 4095L, 12L, 4096L, 13L, 4097L, 13L, 8191L,
        13L, 8192L, 14L,
   8193L, 14L, 16383L, 14L, 16384L, 15L, 16385L, 15L, 32767L, 15L, 32768L,
        16L,
32769L, 16L, 65535L, 16L, 65536L, 17L, 65537L, 17L, 131071L, 17L, 131072L,
        18L,
 131073L, 18L, 262143L, 18L, 262144L, 19L, 262145L, 19L, 524287L, 19L,
        524288L,
 20L, 524289L, 20L, 1048575L, 20L, 1048576L, 21L, 1048577L, 21L, 2097151L,
        21L,
      2097152L, 22L, 2097153L, 22L, 4194303L, 22L, 4194304L, 23L, 4194305L, 23L,
   8388607L, 23L, 8388608L, 24L, 8388609L, 24L, 16777215L, 24L, 16777216L,
        25L,
16777217L, 25L, 33554431L, 25L, 33554432L, 26L, 33554433L, 26L, 67108863L,
        26L,
  67108864L, 27L, 67108865L, 27L, 134217727L, 27L, 134217728L, 28L,
        134217729L,
      28L, 268435455L, 28L, 268435456L, 29L, 268435457L, 29L, 536870911L, 29L,
      536870912L, 30L, 536870913L, 30L, 1073741823L, 30L, 1073741824L, 31L,
      1073741825L, 31L, 2147483647L, 31L, 2147483648L, 32L, 2147483649L, 32L,
      4294967295L, 32L, 4294967296L, 33L, 4294967297L, 33 };

    private static long[] valueLowBits = { 0L, -1L, 1L, 0L, 2L, 1L, 3L, 0L, 4L,
      2L, 5L, 0L,
      7L, 0L, 8L, 3L, 9L, 0L, 15L, 0L, 16L, 4L, 17L, 0L, 31L, 0L, 32L, 5L,
        33L, 0L, 63L, 0L, 64L,
      6L, 65L, 0L, 127L, 0L, 128L, 7L, 129L, 0L, 255L, 0L, 256L, 8L, 257L,
        0L, 511L, 0L, 512L,
      9L, 513L, 0L, 1023L, 0L, 1024L, 10L, 1025L, 0L, 2047L, 0L, 2048L, 11L,
        2049L, 0L,
4095L, 0L, 4096L, 12L, 4097L, 0L, 8191L, 0L, 8192L, 13L, 8193L, 0L, 16383L,
        0L,
      16384L, 14L, 16385L, 0L, 32767L, 0L, 32768L, 15L, 32769L, 0L, 65535L,
        0L, 65536L,
      16L, 65537L, 0L, 131071L, 0L, 131072L, 17L, 131073L, 0L, 262143L, 0L,
        262144L, 18L,
      262145L, 0L, 524287L, 0L, 524288L, 19L, 524289L, 0L, 1048575L, 0L,
        1048576L, 20L,
      1048577L, 0L, 2097151L, 0L, 2097152L, 21L, 2097153L, 0L, 4194303L, 0L,
        4194304L,
   22L, 4194305L, 0L, 8388607L, 0L, 8388608L, 23L, 8388609L, 0L, 16777215L,
        0L,
   16777216L, 24L, 16777217L, 0L, 33554431L, 0L, 33554432L, 25L, 33554433L,
        0L,
 67108863L, 0L, 67108864L, 26L, 67108865L, 0L, 134217727L, 0L, 134217728L,
        27L,
      134217729L, 0L, 268435455L, 0L, 268435456L, 28L, 268435457L, 0L,
        536870911L, 0L,
      536870912L, 29L, 536870913L, 0L, 1073741823L, 0L, 1073741824L, 30L,
      1073741825L, 0L, 2147483647L, 0L, 2147483648L, 31L, 2147483649L, 0L,
      4294967295L, 0L, 4294967296L, 32L, 4294967297L, 0 };

    public static void AssertAdd(EInteger bi, EInteger bi2, String s) {
      EIntegerTest.AssertBigIntegersEqual(s, bi.Add(bi2));
      EIntegerTest.AssertBigIntegersEqual(s, bi2.Add(bi));
      EInteger negbi = EInteger.FromInt32(0).Subtract(bi);
      EInteger negbi2 = EInteger.FromInt32(0).Subtract(bi2);
      EIntegerTest.AssertBigIntegersEqual(s, bi.Subtract(negbi2));
      EIntegerTest.AssertBigIntegersEqual(s, bi2.Subtract(negbi));
    }

    public static void AssertBigIntegersEqual(String a, EInteger b) {
      Assert.assertEquals(a, b.toString());
      EInteger a2 = BigFromString(a);
      TestCommon.CompareTestEqualAndConsistent(a2, b);
      TestCommon.AssertEqualsHashCode(a2, b);
    }

    public static void DoTestDivide(
String dividend,
String divisor,
String result) {
      EInteger bigintA = BigFromString(dividend);
      EInteger bigintB = BigFromString(divisor);
      EInteger bigintTemp;
      if (bigintB.isZero()) {
        try {
          bigintTemp = bigintA.Divide(bigintB);
          Assert.fail("Expected divide by 0 error");
        } catch (ArithmeticException ex) {
          System.out.println(ex.getMessage());
        }
        try {
          bigintA.DivRem(bigintB);
          Assert.fail("Should have failed");
        } catch (ArithmeticException ex) {
          new Object();
        } catch (Exception ex) {
          Assert.fail(ex.toString());
          throw new IllegalStateException("", ex);
        }
      } else {
        AssertBigIntegersEqual(result, bigintA.Divide(bigintB));
        AssertBigIntegersEqual(result, bigintA.DivRem(bigintB)[0]);
      }
    }

    public static void DoTestDivRem(
String dividend,
String divisor,
String result,
String rem) {
      EInteger bigintA = BigFromString(dividend);
      EInteger bigintB = BigFromString(divisor);
      EInteger rembi;
      if (bigintB.isZero()) {
        try {
          EInteger quo;
          {
            EInteger[] divrem = bigintA.DivRem(bigintB);
            quo = divrem[0];
            rembi = divrem[1];
          }
          if (((Object)quo) == null) {
            Assert.fail();
          }
          Assert.fail("Expected divide by 0 error");
        } catch (ArithmeticException ex) {
          System.out.println(ex.getMessage());
        }
      } else {
        EInteger quo;
        {
          EInteger[] divrem = bigintA.DivRem(bigintB);
          quo = divrem[0];
          rembi = divrem[1];
        }
        AssertBigIntegersEqual(result, quo);
        AssertBigIntegersEqual(rem, rembi);
      }
    }

    public static void DoTestMultiply(String m1, String m2, String result) {
      EInteger bigintA = BigFromString(m1);
      EInteger bigintB = BigFromString(m2);
      EInteger bigintC = bigintA.Multiply(bigintB);
      if (result != null) {
        AssertBigIntegersEqual(result, bigintC);
      }
      TestMultiplyDivideOne(bigintA, bigintB);
    }

    public static void DoTestPow(String m1, int m2, String result) {
      EInteger bigintA = BigFromString(m1);
      AssertBigIntegersEqual(result, bigintA.Pow(m2));
      AssertBigIntegersEqual(result, bigintA.PowBigIntVar(EInteger.FromInt32(m2)));
    }

    public static void DoTestRemainder(
String dividend,
String divisor,
String result) {
      EInteger bigintA = BigFromString(dividend);
      EInteger bigintB = BigFromString(divisor);
      if (bigintB.isZero()) {
        try {
          bigintA.Remainder(bigintB); Assert.fail("Expected divide by 0 error");
        } catch (ArithmeticException ex) {
          System.out.println(ex.getMessage());
        }
        try {
          bigintA.DivRem(bigintB);
          Assert.fail("Should have failed");
        } catch (ArithmeticException ex) {
          new Object();
        } catch (Exception ex) {
          Assert.fail(ex.toString());
          throw new IllegalStateException("", ex);
        }
      } else {
        AssertBigIntegersEqual(result, bigintA.Remainder(bigintB));
        AssertBigIntegersEqual(result, bigintA.DivRem(bigintB)[1]);
      }
    }

    public static void DoTestShiftLeft(String m1, int m2, String result) {
      EInteger bigintA = BigFromString(m1);
      AssertBigIntegersEqual(result, bigintA.ShiftLeft(m2));
      m2 = -m2;
      AssertBigIntegersEqual(result, bigintA.ShiftRight(m2));
    }

    public static void DoTestShiftRight(String m1, int m2, String result) {
      EInteger bigintA = BigFromString(m1);
      AssertBigIntegersEqual(result, bigintA.ShiftRight(m2));
      m2 = -m2;
      AssertBigIntegersEqual(result, bigintA.ShiftLeft(m2));
    }

    public static void DoTestShiftRight2(String m1, int m2, EInteger result) {
      EInteger bigintA = BigFromString(m1);
      TestCommon.CompareTestEqualAndConsistent(result, bigintA.ShiftRight(m2));
      m2 = -m2;
      TestCommon.CompareTestEqualAndConsistent(result, bigintA.ShiftLeft(m2));
    }

    public static boolean IsPrime(int n) {
      if (n < 2) {
        return false;
      }
      if (n == 2) {
        return true;
      }
      if (n % 2 == 0) {
        return false;
      }
      if (n <= 23) {
        return n == 3 || n == 5 || n == 7 || n == 11 ||
          n == 13 || n == 17 || n == 19 || n == 23;
      }
      // Use a deterministic Rabin-Miller test
      int d = n - 1;
      int shift = 0;
      while ((d & 1) == 0) {
        d >>= 1;
        ++shift;
      }
      int mp = 0, mp2 = 0;
      boolean found = false;
      // For all 32-bit integers it's enough
      // to check the strong pseudoprime
      // bases 2, 7, and 61
      if (n > 2) {
        mp = ModPow(2, d, n);
        if (mp != 1 && mp + 1 != n) {
          found = false;
          for (int i = 1; i < shift; ++i) {
            mp2 = ModPow(2, d << i, n);
            if (mp2 + 1 == n) {
              found = true;
              break;
            }
          }
          if (found) {
            return false;
          }
        }
      }
      if (n > 7) {
        mp = ModPow(7, d, n);
        if (mp != 1 && mp + 1 != n) {
          found = false;
          for (int i = 1; i < shift; ++i) {
            mp2 = ModPow(7, d << i, n);
            if (mp2 + 1 == n) {
              found = true;
              break;
            }
          }
          if (found) {
            return false;
          }
        }
      }
      if (n > 61) {
        mp = ModPow(61, d, n);
        if (mp != 1 && mp + 1 != n) {
          found = false;
          for (int i = 1; i < shift; ++i) {
            mp2 = ModPow(61, d << i, n);
            if (mp2 + 1 == n) {
              found = true;
              break;
            }
          }
          if (found) {
            return false;
          }
        }
      }
      return true;
    }

    public static int ModPow(int x, int pow, int intMod) {
      if (x < 0) {
        throw new IllegalArgumentException(
          "x (" + x + ") is less than 0");
      }
      if (pow <= 0) {
        throw new IllegalArgumentException(
          "pow (" + pow + ") is not greater than 0");
      }
      if (intMod <= 0) {
        throw new IllegalArgumentException(
          "mod (" + intMod + ") is not greater than 0");
      }
      int r = 1;
      int v = x;
      while (pow != 0) {
        if ((pow & 1) != 0) {
          r = (int)(((long)r * (long)v) % intMod);
        }
        pow >>= 1;
        if (pow != 0) {
          v = (int)(((long)v * (long)v) % intMod);
        }
      }
      return r;
    }

    public static EInteger RandomBigInteger(FastRandom r) {
      int selection = r.NextValue(100);
      int count = r.NextValue(60) + 1;
      if (selection < 40) {
        count = r.NextValue(7) + 1;
      }
      if (selection < 50) {
        count = r.NextValue(15) + 1;
      }
      byte[] bytes = new byte[count];
      for (int i = 0; i < count; ++i) {
        bytes[i] = (byte)((int)r.NextValue(256));
      }
      return BigFromBytes(bytes);
    }

    @Test
    public void TestAdd() {
      EInteger posSmall = EInteger.FromInt64(5);
      EInteger negSmall = EInteger.FromInt64(-5);
      EInteger posLarge = EInteger.FromInt64(5555555);
      EInteger negLarge = EInteger.FromInt64(-5555555);
      AssertAdd(posSmall, posSmall, "10");
      AssertAdd(posSmall, negSmall, "0");
      AssertAdd(posSmall, posLarge, "5555560");
      AssertAdd(posSmall, negLarge, "-5555550");
      AssertAdd(negSmall, negSmall, "-10");
      AssertAdd(negSmall, posLarge, "5555550");
      AssertAdd(negSmall, negLarge, "-5555560");
      AssertAdd(posLarge, posLarge, "11111110");
      AssertAdd(posLarge, negLarge, "0");
      AssertAdd(negLarge, negLarge, "-11111110");
    }

    @Test
    public void TestAddSubtract() {
      FastRandom r = new FastRandom();
      for (int i = 0; i < 10000; ++i) {
        EInteger bigintA = RandomBigInteger(r);
        EInteger bigintB = RandomBigInteger(r);
        EInteger bigintC = bigintA.Add(bigintB);
        EInteger bigintD = bigintC.Subtract(bigintB);
        if (!bigintD.equals(bigintA)) {
          Assert.assertEquals("TestAddSubtract " + bigintA + "; " + bigintB,bigintA,bigintD);
        }
        bigintD = bigintC.Subtract(bigintA);
        if (!bigintD.equals(bigintB)) {
          Assert.assertEquals("TestAddSubtract " + bigintA + "; " + bigintB,bigintB,bigintD);
        }
        bigintC = bigintA.Subtract(bigintB);
        bigintD = bigintC.Add(bigintB);
        if (!bigintD.equals(bigintA)) {
          Assert.assertEquals("TestAddSubtract " + bigintA + "; " + bigintB,bigintA,bigintD);
        }
      }
    }

    @Test
    public void TestAsInt32Checked() {
      Assert.assertEquals(
        Integer.MIN_VALUE,
        BigValueOf(Integer.MIN_VALUE).ToInt32Checked());
      Assert.assertEquals(
        Integer.MAX_VALUE,
        BigValueOf(Integer.MAX_VALUE).ToInt32Checked());
      try {
        BigValueOf(Integer.MIN_VALUE - 1L).ToInt32Checked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        BigValueOf(Integer.MAX_VALUE + 1L).ToInt32Checked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        BigFromString("999999999999999999999999999999999").ToInt32Checked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      Assert.assertEquals(
        Integer.MIN_VALUE,
        BigValueOf(Integer.MIN_VALUE).ToInt32Checked());
      Assert.assertEquals(
        Integer.MAX_VALUE,
        BigValueOf(Integer.MAX_VALUE).ToInt32Checked());
      try {
        BigValueOf(Integer.MIN_VALUE - 1L).ToInt32Checked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        BigValueOf(Integer.MAX_VALUE + 1L).ToInt32Checked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestAsInt64Checked() {
      Assert.assertEquals(
        Long.MIN_VALUE,
        BigValueOf(Long.MIN_VALUE).ToInt64Checked());
      Assert.assertEquals(
        Long.MAX_VALUE,
        BigValueOf(Long.MAX_VALUE).ToInt64Checked());
      try {
        EInteger bigintTemp = BigValueOf(Long.MIN_VALUE);
        bigintTemp = bigintTemp.Subtract(EInteger.FromInt32(1));
        bigintTemp.ToInt64Checked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger bigintTemp = BigValueOf(Long.MAX_VALUE);
        bigintTemp = bigintTemp.Add(EInteger.FromInt32(1));
        bigintTemp.ToInt64Checked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      Assert.assertEquals(
        ((long)0xfffffff200000000L),
  BigValueOf(((long)0xfffffff200000000L)).ToInt64Checked());
      Assert.assertEquals(
        ((long)0xfffffff280000000L),
  BigValueOf(((long)0xfffffff280000000L)).ToInt64Checked());
      Assert.assertEquals(
        ((long)0xfffffff280000001L),
  BigValueOf(((long)0xfffffff280000001L)).ToInt64Checked());
      Assert.assertEquals(
        ((long)0xfffffff27fffffffL),
  BigValueOf(((long)0xfffffff27fffffffL)).ToInt64Checked());
      Assert.assertEquals(
        0x0000000380000001L,
        BigValueOf(0x0000000380000001L).ToInt64Checked());
      Assert.assertEquals(
        0x0000000382222222L,
        BigValueOf(0x0000000382222222L).ToInt64Checked());
      Assert.assertEquals(-8L, BigValueOf(-8L).ToInt64Checked());
      Assert.assertEquals(-32768L, BigValueOf(-32768L).ToInt64Checked());
      Assert.assertEquals(
        Integer.MIN_VALUE,
        BigValueOf(Integer.MIN_VALUE).ToInt64Checked());
      Assert.assertEquals(
        Integer.MAX_VALUE,
        BigValueOf(Integer.MAX_VALUE).ToInt64Checked());
      Assert.assertEquals(
        0x80000000L,
        BigValueOf(0x80000000L).ToInt64Checked());
      Assert.assertEquals(
        0x90000000L,
        BigValueOf(0x90000000L).ToInt64Checked());
      try {
        BigFromString("999999999999999999999999999999999").ToInt64Checked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      Assert.assertEquals(
        Long.MIN_VALUE,
        BigValueOf(Long.MIN_VALUE).ToInt64Checked());
      Assert.assertEquals(
        Long.MAX_VALUE,
        BigValueOf(Long.MAX_VALUE).ToInt64Checked());
      try {
        EInteger bigintTemp = BigValueOf(Long.MIN_VALUE);
        bigintTemp = bigintTemp.Subtract(EInteger.FromInt32(1));
        bigintTemp.ToInt64Checked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger bigintTemp = BigValueOf(Long.MAX_VALUE);
        bigintTemp = bigintTemp.Add(EInteger.FromInt32(1));
        bigintTemp.ToInt64Checked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      long longV = ((long)0xfffffff200000000L);
      Assert.assertEquals(
longV,
BigValueOf(longV).ToInt64Checked());
      longV = ((long)0xfffffff280000000L);
      Assert.assertEquals(
longV,
BigValueOf(longV).ToInt64Checked());
      longV = ((long)0xfffffff280000001L);
      Assert.assertEquals(
longV,
BigValueOf(longV).ToInt64Checked());
      longV = ((long)0xfffffff27fffffffL);
      Assert.assertEquals(
longV,
BigValueOf(longV).ToInt64Checked());
      Assert.assertEquals(
        0x0000000380000001L,
        BigValueOf(0x0000000380000001L).ToInt64Checked());
      Assert.assertEquals(
        0x0000000382222222L,
        BigValueOf(0x0000000382222222L).ToInt64Checked());
      Assert.assertEquals(-8L, BigValueOf(-8L).ToInt64Checked());
      Assert.assertEquals(-32768L, BigValueOf(-32768L).ToInt64Checked());
      Assert.assertEquals(
        Integer.MIN_VALUE,
        BigValueOf(Integer.MIN_VALUE).ToInt64Checked());
      Assert.assertEquals(
        Integer.MAX_VALUE,
        BigValueOf(Integer.MAX_VALUE).ToInt64Checked());
      Assert.assertEquals(
        0x80000000L,
        BigValueOf(0x80000000L).ToInt64Checked());
      Assert.assertEquals(
        0x90000000L,
        BigValueOf(0x90000000L).ToInt64Checked());
    }
    @Test
    public void TestBigIntegerModPow() {
      try {
        EInteger.FromInt32(1).ModPow(null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ModPow(null, EInteger.FromInt32(0));
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ModPow(EInteger.FromInt32(0), null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ModPow(BigFromString("-1"), BigFromString("1"));
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ModPow(BigFromString("0"), BigFromString("0"));
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ModPow(BigFromString("0"), BigFromString("-1"));
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ModPow(BigFromString("1"), BigFromString("0"));
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ModPow(BigFromString("1"), BigFromString("-1"));
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestCanFitInInt() {
      FastRandom r = new FastRandom();
      for (int i = 0; i < 2000; ++i) {
        EInteger bigintA = RandomBigInteger(r);
  Assert.assertEquals(bigintA.CanFitInInt32(), bigintA.GetSignedBitLength() <= 31);
  Assert.assertEquals(bigintA.CanFitInInt64(), bigintA.GetSignedBitLength() <= 63);
      }
    }
    @Test
    public void TestCompareTo() {
      FastRandom r = new FastRandom();
      for (int i = 0; i < 500; ++i) {
        EInteger bigintA = RandomBigInteger(r);
        EInteger bigintB = RandomBigInteger(r);
        EInteger bigintC = RandomBigInteger(r);
        TestCommon.CompareTestRelations(bigintA, bigintB, bigintC);
        TestCommon.CompareTestConsistency(bigintA, bigintB, bigintC);
      }
    }
    @Test
    public void TestDivide() {
      int intA, intB;
      FastRandom fr = new FastRandom();
      for (int i = 0; i < 10000; ++i) {
        intA = fr.NextValue(0x1000000);
        intB = fr.NextValue(0x1000000);
        if (intB == 0) {
          continue;
        }
        int c = intA / intB;
        EInteger bigintA = EInteger.FromInt32(intA);
        EInteger bigintB = EInteger.FromInt32(intB);
        EInteger bigintC = bigintA.Divide(bigintB);
        Assert.assertEquals(bigintC.AsInt32Checked(), c);
      }
      DoTestDivide("2472320648", "2831812081", "0");
      DoTestDivide("-2472320648", "2831812081", "0");
      DoTestDivide(
    "9999999999999999999999",
    "281474976710655",
    "35527136");
    }
    @Test
    public void TestDivRem() {
      // not implemented yet
    }

    @Test
    public void TestDivRem2() {
      try {
        EInteger.FromInt32(1).DivRem(EInteger.FromInt32(0));
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).DivRem(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestEquals() {
      if (EInteger.FromInt32(1).equals(null))Assert.fail();
      if (EInteger.FromInt32(0).equals(null))Assert.fail();
      if (EInteger.FromInt32(1).equals(EInteger.FromInt32(0)))Assert.fail();
      if (EInteger.FromInt32(0).equals(EInteger.FromInt32(1)))Assert.fail();
      TestCommon.AssertEqualsHashCode(
        EInteger.FromInt32(0),
        EInteger.FromString("-0"));
      TestCommon.AssertEqualsHashCode(
        EInteger.FromString("0"),
        EInteger.FromString("-0"));
      TestCommon.AssertEqualsHashCode(
        EInteger.FromInt32(0),
        EInteger.FromInt32(1));
      FastRandom r = new FastRandom();
      for (int i = 0; i < 1000; ++i) {
        EInteger bigintA = RandomObjects.RandomEInteger(r);
        EInteger bigintB = RandomObjects.RandomEInteger(r);
        TestCommon.AssertEqualsHashCode(bigintA, bigintB);
      }
    }

    @Test
    public void TestExceptions() {
      try {
        BigFromString(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      try {
        EInteger.FromInt32(0).GetSignedBit(-1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        BigFromString("x11");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        BigFromString(".");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        BigFromString("..");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        BigFromString("e200");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      try {
        EInteger.FromInt32(1).Mod(EInteger.FromInt64(-1));
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).Add(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).Subtract(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).Divide(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).Divide(EInteger.FromInt32(0));
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).Remainder(EInteger.FromInt32(0));
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).Mod(EInteger.FromInt32(0));
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).Remainder(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      Assert.assertEquals(EInteger.FromInt32(1), (EInteger.FromInt64(13)).Mod(EInteger.FromInt64(4)));
      Assert.assertEquals(EInteger.FromInt64(3), (EInteger.FromInt64(-13)).Mod(EInteger.FromInt64(4)));
    }
    @Test
    public void TestFromBytes() {
      Assert.assertEquals(
        EInteger.FromInt32(0), EInteger.FromBytes(new byte[] { }, false));

      try {
        EInteger.FromBytes(null, false);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestFromRadixString() {
      try {
        EInteger.FromRadixString(null, 10);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixString("0", 1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixString("0", 0);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixString("0", -37);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixString("0", Integer.MIN_VALUE);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixString("0", Integer.MAX_VALUE);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      FastRandom fr = new FastRandom();
      for (int i = 2; i <= 36; ++i) {
        for (int j = 0; j < 100; ++j) {
          StringAndBigInt sabi = StringAndBigInt.Generate(fr, i);
          Assert.assertEquals(
            sabi.getBigIntValue(),
            EInteger.FromRadixString(sabi.getStringValue(), i));
        }
      }
    }
    @Test
    public void TestFromRadixSubstring() {
      try {
        EInteger.FromRadixSubstring(null, 10, 0, 1);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("0", 1, 0, 1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("0", 0, 0, 1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("0", -37, 0, 1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("0", Integer.MIN_VALUE, 0, 1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("0", Integer.MAX_VALUE, 0, 1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("123", 10, -1, 2);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("123", 10, 4, 5);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("123", 10, 0, -8);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("123", 10, 0, 6);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("123", 10, 2, 0);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("123", 10, 0, 0);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("123", 10, 1, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("-", 10, 0, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("g", 16, 0, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("0123gggg", 16, 0, 8);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("0123gggg", 10, 0, 8);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromRadixSubstring("0123aaaa", 10, 0, 8);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      FastRandom fr = new FastRandom();
      for (int i = 2; i <= 36; ++i) {
        StringBuilder padding = new StringBuilder();
        for (int j = 0; j < 100; ++j) {
          StringAndBigInt sabi = StringAndBigInt.Generate(fr, i);
          padding.append('!');
          String sabiString = sabi.getStringValue();
          EInteger actualBigInt = EInteger.FromRadixSubstring(
            padding + sabiString,
            i,
            j + 1,
            j + 1 + sabiString.length());
          Assert.assertEquals(
            sabi.getBigIntValue(),
            actualBigInt);
        }
      }
    }
    @Test
    public void TestFromString() {
      try {
        BigFromString("xyz");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        BigFromString("");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        BigFromString(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestFromSubstring() {
      try {
        EInteger.FromSubstring(null, 0, 1);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      try {
        EInteger.FromSubstring("123", -1, 2);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromSubstring("123", 4, 2);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromSubstring("123", 1, -1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromSubstring("123", 1, 4);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromSubstring("123", 1, 0);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromSubstring("123", 2, 1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      {
        String stringTemp = EInteger.FromSubstring(
          "0123456789",
          9,
          10).toString();
        Assert.assertEquals(
          "9",
          stringTemp);
      }
      {
        String stringTemp = EInteger.FromSubstring(
          "0123456789",
          8,
          10).toString();
        Assert.assertEquals(
          "89",
          stringTemp);
      }
      {
        String stringTemp = EInteger.FromSubstring(
          "0123456789",
          7,
          10).toString();
        Assert.assertEquals(
          "789",
          stringTemp);
      }
      {
        String stringTemp = EInteger.FromSubstring(
          "0123456789",
          6,
          10).toString();
        Assert.assertEquals(
          "6789",
          stringTemp);
      }
    }

    @Test(timeout = 10000)
    public void TestGcdHang() {
      {
String stringTemp = BigFromString("781631509928000000").Gcd(
          BigFromString("1000000")).toString();
Assert.assertEquals(
"1000000",
stringTemp);
}
    }

    @Test
    public void TestGcd() {
      try {
        EInteger.FromInt32(0).Gcd(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      {
        String stringTemp = EInteger.FromInt32(0).Gcd(BigFromString(
        "244")).toString();
        Assert.assertEquals(
        "244",
        stringTemp);
      }
      {
        String stringTemp = EInteger.FromInt32(0).Gcd(BigFromString(
        "-244")).toString();
        Assert.assertEquals(
        "244",
        stringTemp);
      }
      {
        String stringTemp = BigFromString(
        "244").Gcd(EInteger.FromInt32(0)).toString();
        Assert.assertEquals(
        "244",
        stringTemp);
      }
      {
        String stringTemp = BigFromString(
        "-244").Gcd(EInteger.FromInt32(0)).toString();
        Assert.assertEquals(
        "244",
        stringTemp);
      }
      {
        String stringTemp = EInteger.FromInt32(1).Gcd(BigFromString("244")).toString();
        Assert.assertEquals(
        "1",
        stringTemp);
      }
      {
        String stringTemp = EInteger.FromInt32(1).Gcd(BigFromString(
        "-244")).toString();
        Assert.assertEquals(
        "1",
        stringTemp);
      }
      {
        String stringTemp = BigFromString("244").Gcd(EInteger.FromInt32(1)).toString();
        Assert.assertEquals(
        "1",
        stringTemp);
      }
      {
        String stringTemp = BigFromString(
        "-244").Gcd(EInteger.FromInt32(1)).toString();
        Assert.assertEquals(
        "1",
        stringTemp);
      }
      {
        String stringTemp = BigFromString("15").Gcd(BigFromString(
        "15")).toString();
        Assert.assertEquals(
        "15",
        stringTemp);
      }
      {
        String stringTemp = BigFromString("-15").Gcd(
                BigFromString("15")).toString();
        Assert.assertEquals(
        "15",
        stringTemp);
      }
      {
        String stringTemp = BigFromString("15").Gcd(
                BigFromString("-15")).toString();
        Assert.assertEquals(
        "15",
        stringTemp);
      }
      {
        String stringTemp = BigFromString(
        "-15").Gcd(BigFromString("-15")).toString();
        Assert.assertEquals(
        "15",
        stringTemp);
      }
      int prime = 0;
      FastRandom rand = new FastRandom();
      for (int i = 0; i < 1000; ++i) {
        while (true) {
          prime = rand.NextValue(0x7fffffff);
          prime |= 1;
          if (IsPrime(prime)) {
            break;
          }
        }
        EInteger bigprime = EInteger.FromInt32(prime);
        EInteger ba = RandomBigInteger(rand);
        if (ba.isZero()) {
          continue;
        }
        ba = ba.Multiply(bigprime);
        Assert.assertEquals(
          bigprime,
          bigprime.Gcd(ba));
      }
      TestGcdPair(EInteger.FromInt64(-1867), EInteger.FromInt64(-4456), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(4604), EInteger.FromInt64(-4516), EInteger.FromInt64(4));
      TestGcdPair(EInteger.FromInt64(-1756), EInteger.FromInt64(4525), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(1568), EInteger.FromInt64(-4955), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(2519), EInteger.FromInt64(2845), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-1470), EInteger.FromInt64(132), EInteger.FromInt64(6));
      TestGcdPair(EInteger.FromInt64(-2982), EInteger.FromInt64(2573), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-244), EInteger.FromInt64(-3929), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-3794), EInteger.FromInt64(-2325), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-2667), EInteger.FromInt64(2123), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-3712), EInteger.FromInt64(-1850), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(2329), EInteger.FromInt64(3874), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(1384), EInteger.FromInt64(-4278), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(213), EInteger.FromInt64(-1217), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(1163), EInteger.FromInt64(2819), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(1921), EInteger.FromInt64(-579), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(3886), EInteger.FromInt64(-13), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-3270), EInteger.FromInt64(-3760), EInteger.FromInt32(10));
      TestGcdPair(EInteger.FromInt64(-3528), EInteger.FromInt64(1822), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(1547), EInteger.FromInt64(-333), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(2402), EInteger.FromInt64(2850), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(4519), EInteger.FromInt64(1296), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(1821), EInteger.FromInt64(2949), EInteger.FromInt64(3));
      TestGcdPair(EInteger.FromInt64(-2634), EInteger.FromInt64(-4353), EInteger.FromInt64(3));
      TestGcdPair(EInteger.FromInt64(-1728), EInteger.FromInt64(199), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-4646), EInteger.FromInt64(-1418), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(-35), EInteger.FromInt64(-3578), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-2244), EInteger.FromInt64(-3250), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(-3329), EInteger.FromInt64(1039), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-3064), EInteger.FromInt64(-4730), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(-1214), EInteger.FromInt64(4130), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(-3038), EInteger.FromInt64(-3184), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(-209), EInteger.FromInt64(-1617), EInteger.FromInt64(11));
      TestGcdPair(EInteger.FromInt64(-1101), EInteger.FromInt64(-2886), EInteger.FromInt64(3));
      TestGcdPair(EInteger.FromInt64(-3021), EInteger.FromInt64(-4499), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(3108), EInteger.FromInt64(1815), EInteger.FromInt64(3));
      TestGcdPair(EInteger.FromInt64(1195), EInteger.FromInt64(4618), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-3643), EInteger.FromInt64(2156), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-2067), EInteger.FromInt64(-3780), EInteger.FromInt64(3));
      TestGcdPair(EInteger.FromInt64(4251), EInteger.FromInt64(1607), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(438), EInteger.FromInt64(741), EInteger.FromInt64(3));
      TestGcdPair(EInteger.FromInt64(-3692), EInteger.FromInt64(-2135), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-1076), EInteger.FromInt64(2149), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-3224), EInteger.FromInt64(-1532), EInteger.FromInt64(4));
      TestGcdPair(EInteger.FromInt64(-3713), EInteger.FromInt64(1721), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(3038), EInteger.FromInt64(-2657), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(4977), EInteger.FromInt64(-110), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-3305), EInteger.FromInt64(-922), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(1902), EInteger.FromInt64(2481), EInteger.FromInt64(3));
      TestGcdPair(EInteger.FromInt64(-4804), EInteger.FromInt64(-1378), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(-1446), EInteger.FromInt64(-4226), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(-1409), EInteger.FromInt64(3303), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-1626), EInteger.FromInt64(-3193), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(912), EInteger.FromInt64(-421), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(751), EInteger.FromInt64(-1755), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(3135), EInteger.FromInt64(-3581), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-4941), EInteger.FromInt64(-2885), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(4744), EInteger.FromInt64(3240), EInteger.FromInt64(8));
      TestGcdPair(EInteger.FromInt64(3488), EInteger.FromInt64(4792), EInteger.FromInt64(8));
      TestGcdPair(EInteger.FromInt64(3632), EInteger.FromInt64(3670), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(-4821), EInteger.FromInt64(-1749), EInteger.FromInt64(3));
      TestGcdPair(EInteger.FromInt64(4666), EInteger.FromInt64(2013), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(810), EInteger.FromInt64(-3466), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(2199), EInteger.FromInt64(161), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-1137), EInteger.FromInt64(-1620), EInteger.FromInt64(3));
      TestGcdPair(EInteger.FromInt64(-472), EInteger.FromInt64(66), EInteger.FromInt64(2));
      TestGcdPair(EInteger.FromInt64(3825), EInteger.FromInt64(2804), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-2895), EInteger.FromInt64(1942), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(1576), EInteger.FromInt64(-4209), EInteger.FromInt32(1));
      TestGcdPair(EInteger.FromInt64(-277), EInteger.FromInt64(-4415), EInteger.FromInt32(1));
      for (int i = 0; i < 1000; ++i) {
        prime = rand.NextValue(0x7fffffff);
        if (rand.NextValue(2) == 0) {
          prime = -prime;
        }
        int intB = rand.NextValue(0x7fffffff);
        if (rand.NextValue(2) == 0) {
          intB = -intB;
        }
        EInteger biga = EInteger.FromInt32(prime);
        EInteger bigb = EInteger.FromInt32(intB);
        EInteger ba = biga.Gcd(bigb);
        EInteger bb = bigb.Gcd(biga);
        Assert.assertEquals(ba, bb);
      }
    }

    @Test
    public void TestGetBits() {
      // not implemented yet
    }
    @Test
    public void TestGetDigitCount() {
      FastRandom r = new FastRandom();
      {
Object objectTemp = 39;
Object objectTemp2 = EInteger.FromString(
"101754295360222878437145684059582837272").GetDigitCount();
Assert.assertEquals(objectTemp, objectTemp2);
}
      for (int i = 0; i < 1000; ++i) {
        EInteger bigintA = RandomBigInteger(r);
        String str = bigintA.Abs().toString();
        Assert.assertEquals(str, str.length(), bigintA.GetDigitCount());
      }
    }
    @Test
    public void TestGetSignedBit() {
      if (EInteger.FromInt32(0).GetSignedBit(0))Assert.fail();
      if (EInteger.FromInt32(0).GetSignedBit(1))Assert.fail();
      if (!(EInteger.FromInt32(1).GetSignedBit(0)))Assert.fail();
      if (EInteger.FromInt32(1).GetSignedBit(1))Assert.fail();
      for (int i = 0; i < 32; ++i) {
        if (!(BigValueOf(-1).GetSignedBit(i)))Assert.fail();
      }
    }

    @Test
    public void TestGetSignedBitLength() {
      for (int i = 0; i < valueBitLengths.length; i += 2) {
        Assert.assertEquals(TestCommon.LongToString(valueBitLengths[i]), (int)valueBitLengths[i + 1], BigValueOf(valueBitLengths[i]).GetSignedBitLength());
      }
    }

    @Test
    public void TestGetUnsignedBit() {
      for (int i = 0; i < valueLowBits.length; i += 2) {
        int lowbit = (int)valueLowBits[i + 1];
        EInteger posint = BigValueOf(valueLowBits[i]);
        EInteger negint = BigValueOf(-valueLowBits[i]);
        for (int j = 0; j < lowbit; ++j) {
          if (posint.GetUnsignedBit(j))Assert.fail();
          if (negint.GetUnsignedBit(j))Assert.fail();
        }
        if (lowbit >= 0) {
          if (!(posint.GetUnsignedBit(lowbit)))Assert.fail();
          if (!(negint.GetUnsignedBit(lowbit)))Assert.fail();
        }
      }
    }

    @Test
    public void TestGetUnsignedBitLength() {
      for (int i = 0; i < valueBitLengths.length; i += 2) {
        if (valueBitLengths[i] < 0) {
          continue;
        }
        Assert.assertEquals(TestCommon.LongToString(valueBitLengths[i]), (int)valueBitLengths[i + 1], BigValueOf(valueBitLengths[i]).GetUnsignedBitLength());
        Assert.assertEquals(TestCommon.LongToString(-valueBitLengths[i]), (int)valueBitLengths[i + 1], BigValueOf(-valueBitLengths[i]).GetUnsignedBitLength());
      }
    }

    @Test
    public void TestGetLowBit() {
      for (int i = 0; i < valueLowBits.length; i += 2) {
        Assert.assertEquals(
          (int)valueLowBits[i + 1],
          BigValueOf(valueLowBits[i]).GetLowBit());
        Assert.assertEquals(
          (int)valueLowBits[i + 1],
          BigValueOf(-valueLowBits[i]).GetLowBit());
      }
    }

    @Test
    public void TestIntValueUnchecked() {
      Assert.assertEquals(0L, EInteger.FromInt32(0).ToInt32Unchecked());
      Assert.assertEquals(
        Integer.MIN_VALUE,
        BigValueOf(Integer.MIN_VALUE).ToInt32Unchecked());
      Assert.assertEquals(
        Integer.MAX_VALUE,
        BigValueOf(Integer.MAX_VALUE).ToInt32Unchecked());
      Assert.assertEquals(
        Integer.MAX_VALUE,
        BigValueOf(Integer.MIN_VALUE - 1L).ToInt32Unchecked());
      Assert.assertEquals(
        Integer.MIN_VALUE,
        BigValueOf(Integer.MAX_VALUE + 1L).ToInt32Unchecked());
    }

    @Test
    public void TestIsEven() {
      FastRandom r = new FastRandom();
      for (int i = 0; i < 1000; ++i) {
        EInteger bigintA = RandomBigInteger(r);
        EInteger mod = bigintA.Remainder(BigValueOf(2));
        Assert.assertEquals(mod.isZero(), bigintA.isEven());
        if (bigintA.isEven()) {
          bigintA = bigintA.Add(EInteger.FromInt32(1));
          if (!(!bigintA.isEven()))Assert.fail();
        } else {
          bigintA = bigintA.Add(EInteger.FromInt32(1));
          if (!(bigintA.isEven()))Assert.fail();
        }
      }
    }
    @Test
    public void TestIsPowerOfTwo() {
      if (!(BigValueOf(1).isPowerOfTwo()))Assert.fail();
      if (!(BigValueOf(2).isPowerOfTwo()))Assert.fail();
      if (!(BigValueOf(4).isPowerOfTwo()))Assert.fail();
      if (!(BigValueOf(8).isPowerOfTwo()))Assert.fail();
      if (!(BigValueOf(16).isPowerOfTwo()))Assert.fail();
      if (!(BigValueOf(32).isPowerOfTwo()))Assert.fail();
      if (!(BigValueOf(64).isPowerOfTwo()))Assert.fail();
      if (BigValueOf(65535).isPowerOfTwo())Assert.fail();
      if (!(BigValueOf(65536).isPowerOfTwo()))Assert.fail();
      if (BigValueOf(65537).isPowerOfTwo())Assert.fail();
      if (!(BigValueOf(0x100000).isPowerOfTwo()))Assert.fail();
      if (!(BigValueOf(0x10000000).isPowerOfTwo()))Assert.fail();
      if (BigValueOf(0).isPowerOfTwo())Assert.fail();
      if (BigValueOf(-1).isPowerOfTwo())Assert.fail();
      if (BigValueOf(-2).isPowerOfTwo())Assert.fail();
      if (BigValueOf(-3).isPowerOfTwo())Assert.fail();
      if (BigValueOf(-4).isPowerOfTwo())Assert.fail();
      if (BigValueOf(-5).isPowerOfTwo())Assert.fail();
      if (BigValueOf(-65536).isPowerOfTwo())Assert.fail();
    }
    @Test
    public void TestIsZero() {
      // not implemented yet
    }
    @Test
    public void TestLongValueUnchecked() {
      Assert.assertEquals(0L, EInteger.FromInt32(0).ToInt64Unchecked());
      Assert.assertEquals(
        Long.MIN_VALUE,
        BigValueOf(Long.MIN_VALUE).ToInt64Unchecked());
      Assert.assertEquals(
        Long.MAX_VALUE,
        BigValueOf(Long.MAX_VALUE).ToInt64Unchecked());
      {
        Object objectTemp = Long.MAX_VALUE;
        Object objectTemp2 = BigValueOf(Long.MIN_VALUE)
                .Subtract(EInteger.FromInt32(1)).ToInt64Unchecked();
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      Assert.assertEquals(
        Long.MIN_VALUE,
        BigValueOf(Long.MAX_VALUE).Add(EInteger.FromInt32(1)).ToInt64Unchecked());
      long aa = ((long)0xfffffff200000000L);
      Assert.assertEquals(
              aa,
              BigValueOf(aa).ToInt64Unchecked());
      aa = ((long)0xfffffff280000000L);
      Assert.assertEquals(
              aa,
              BigValueOf(aa).ToInt64Unchecked());
      aa = ((long)0xfffffff200000001L);
      Assert.assertEquals(
              aa,
              BigValueOf(aa).ToInt64Unchecked());
      aa = ((long)0xfffffff27fffffffL);
      Assert.assertEquals(
              aa,
              BigValueOf(aa).ToInt64Unchecked());
      Assert.assertEquals(
        0x0000000380000001L,
        BigValueOf(0x0000000380000001L).ToInt64Unchecked());
      Assert.assertEquals(
        0x0000000382222222L,
        BigValueOf(0x0000000382222222L).ToInt64Unchecked());
      Assert.assertEquals(-8L, BigValueOf(-8L).ToInt64Unchecked());
      Assert.assertEquals(
        -32768L,
        BigValueOf(-32768L).ToInt64Unchecked());
      Assert.assertEquals(
        Integer.MIN_VALUE,
        BigValueOf(Integer.MIN_VALUE).ToInt64Unchecked());
      Assert.assertEquals(
        Integer.MAX_VALUE,
        BigValueOf(Integer.MAX_VALUE).ToInt64Unchecked());
      Assert.assertEquals(
        0x80000000L,
        BigValueOf(0x80000000L).ToInt64Unchecked());
      Assert.assertEquals(
        0x90000000L,
        BigValueOf(0x90000000L).ToInt64Unchecked());
    }

    @Test
    public void TestMiscellaneous() {
      Assert.assertEquals(1, EInteger.FromInt32(0).GetDigitCount());
      EInteger minValue = EInteger.FromInt64(Integer.MIN_VALUE);
      EInteger minValueTimes2 = minValue.Add(minValue);
      Assert.assertEquals(Integer.MIN_VALUE, minValue.AsInt32Checked());
      try {
        System.out.println(minValueTimes2.AsInt32Checked());
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      EInteger verybig = EInteger.FromInt32(1).ShiftLeft(80);
      try {
        System.out.println(verybig.AsInt32Checked());
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        System.out.println(verybig.AsInt64Checked());
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).PowBigIntVar(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).Pow(-1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        (EInteger.FromInt32(0).Subtract(EInteger.FromInt64(1))).PowBigIntVar(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      if (EInteger.FromInt32(1).equals(EInteger.FromInt32(0)))Assert.fail();
      if (verybig.equals(EInteger.FromInt32(0)))Assert.fail();
      if (EInteger.FromInt32(1).equals(EInteger.FromInt32(0).Subtract(EInteger.FromInt64(1))))Assert.fail();
      Assert.assertEquals(1, EInteger.FromInt32(1).compareTo(null));
      EInteger[] tmpsqrt = EInteger.FromInt32(0).SqrtRem();
      Assert.assertEquals(EInteger.FromInt32(0), tmpsqrt[0]);
    }

    @Test
    public void TestMod() {
      try {
        EInteger.FromInt32(1).Mod(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        (EInteger.FromInt64(13)).Mod(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        (EInteger.FromInt64(13)).Mod(EInteger.FromInt64(-4));
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        (EInteger.FromInt64(-13)).Mod(EInteger.FromInt64(-4));
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestMultiply() {
      try {
        EInteger.FromInt32(1).Multiply(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      FastRandom r = new FastRandom();
      for (int i = 0; i < 10000; ++i) {
        EInteger bigintA = RandomBigInteger(r);
        EInteger bigintB = bigintA.Add(EInteger.FromInt32(1));
        EInteger bigintC = bigintA.Multiply(bigintB);
        // Test near-squaring
        if (bigintA.isZero() || bigintB.isZero()) {
          Assert.assertEquals(EInteger.FromInt32(0), bigintC);
        }
        if (bigintA.equals(EInteger.FromInt32(1))) {
          Assert.assertEquals(bigintB, bigintC);
        }
        if (bigintB.equals(EInteger.FromInt32(1))) {
          Assert.assertEquals(bigintA, bigintC);
        }
        bigintB = bigintA;
        // Test squaring
        bigintC = bigintA.Multiply(bigintB);
        if (bigintA.isZero() || bigintB.isZero()) {
          Assert.assertEquals(EInteger.FromInt32(0), bigintC);
        }
        if (bigintA.equals(EInteger.FromInt32(1))) {
          Assert.assertEquals(bigintB, bigintC);
        }
        if (bigintB.equals(EInteger.FromInt32(1))) {
          Assert.assertEquals(bigintA, bigintC);
        }
      }
      DoTestMultiply(
"39258416159456516340113264558732499166970244380745050",
"39258416159456516340113264558732499166970244380745051",
"1541223239349076530208308657654362309553698742116222355477449713742236585667505604058123112521437480247550");
      DoTestMultiply(
  "5786426269322750882632312999752639738983363095641642905722171221986067189342123124290107105663618428969517616421742429671402859775667602123564",
  "331378991485809774307751183645559883724387697397707434271522313077548174328632968616330900320595966360728317363190772921",
  "1917500101435169880779183578665955372346028226046021044867189027856189131730889958057717187493786883422516390996639766012958050987359732634213213442579444095928862861132583117668061032227577386757036981448703231972963300147061503108512300577364845823910107210444");
    }

    @Test
    public void TestMultiplyDivide() {
      FastRandom r = new FastRandom();
      for (int i = 0; i < 10000; ++i) {
        TestMultiplyDivideOne(
          RandomObjects.RandomEInteger(r),
          RandomObjects.RandomEInteger(r));
      }
      TestMultiplyDivideOne(EInteger.FromInt32(-985), EInteger.FromInt32(0));
    }

    @Test
    public void TestNegate() {
      // not implemented yet
    }
    @Test
    public void TestNot() {
      // not implemented yet
    }
    @Test
    public void TestOne() {
      // not implemented yet
    }
    @Test
    public void TestOperatorAddition() {
      // not implemented yet
    }
    @Test
    public void TestOperatorDivision() {
      // not implemented yet
    }
    @Test
    public void TestOperatorExplicit() {
      // not implemented yet
    }
    @Test
    public void TestOperatorGreaterThan() {
      // not implemented yet
    }
    @Test
    public void TestOperatorGreaterThanOrEqual() {
      // not implemented yet
    }
    @Test
    public void TestOperatorImplicit() {
      // not implemented yet
    }
    @Test
    public void TestOperatorLeftShift() {
      // not implemented yet
    }
    @Test
    public void TestOperatorLessThan() {
      // not implemented yet
    }
    @Test
    public void TestOperatorLessThanOrEqual() {
      // not implemented yet
    }
    @Test
    public void TestOperatorModulus() {
      // not implemented yet
    }
    @Test
    public void TestOperatorMultiply() {
      // not implemented yet
    }
    @Test
    public void TestOperatorRightShift() {
      // not implemented yet
    }
    @Test
    public void TestOperatorSubtraction() {
      // not implemented yet
    }
    @Test
    public void TestOperatorUnaryNegation() {
      // not implemented yet
    }
    @Test
    public void TestOr() {
      // not implemented yet
    }

    @Test
    public void TestPow() {
      FastRandom r = new FastRandom();
      for (int i = 0; i < 200; ++i) {
        int power = 1 + r.NextValue(8);
        EInteger bigintA = RandomBigInteger(r);
        EInteger bigintB = bigintA;
        for (int j = 1; j < power; ++j) {
          bigintB = bigintB.Multiply(bigintA);
        }
        DoTestPow(bigintA.toString(), power, bigintB.toString());
      }
    }
    @Test
    public void TestPowBigIntVar() {
      // not implemented yet
    }
    @Test
    public void TestRemainder() {
      DoTestRemainder("2472320648", "2831812081", "2472320648");
      DoTestRemainder("-2472320648", "2831812081", "-2472320648");
    }
    @Test
    public void TestShiftLeft() {
      EInteger bigint = EInteger.FromInt32(1);
      bigint = bigint.ShiftLeft(100);
      TestCommon.CompareTestEqualAndConsistent(bigint.ShiftLeft(12), bigint.ShiftRight(-12));
      TestCommon.CompareTestEqualAndConsistent(bigint.ShiftLeft(-12), bigint.ShiftRight(12));
      FastRandom r = new FastRandom();
      for (int i = 0; i < 1000; ++i) {
        EInteger bigintA = RandomBigInteger(r);
        EInteger bigintB = bigintA;
        for (int j = 0; j < 100; ++j) {
          EInteger ba = bigintA;
          ba = ba.ShiftLeft(j);
          TestCommon.CompareTestEqualAndConsistent(bigintB, ba);
          int negj = -j;
          ba = bigintA;
          ba = ba.ShiftRight(negj);
          TestCommon.CompareTestEqualAndConsistent(bigintB, ba);
          bigintB = bigintB.Multiply(EInteger.FromInt64(2));
        }
      }
    }
    @Test
    public void TestShiftRight() {
      EInteger bigint = EInteger.FromInt32(1);
      bigint = bigint.ShiftLeft(80);
      TestCommon.CompareTestEqualAndConsistent(bigint.ShiftLeft(12), bigint.ShiftRight(-12));
      TestCommon.CompareTestEqualAndConsistent(bigint.ShiftLeft(-12), bigint.ShiftRight(12));
      FastRandom r = new FastRandom();
      EInteger minusone = EInteger.FromInt32(0);
      minusone = minusone.Subtract(EInteger.FromInt32(1));
      for (int i = 0; i < 1000; ++i) {
        int smallint = r.NextValue(0x7fffffff);
        EInteger bigintA = EInteger.FromInt32(smallint);
        String str = bigintA.toString();
        for (int j = 32; j < 80; ++j) {
          DoTestShiftRight2(str, j, EInteger.FromInt32(0));
          DoTestShiftRight2("-" + str, j, minusone);
        }
      }
      for (int i = 0; i < 1000; ++i) {
        EInteger bigintA = RandomBigInteger(r);
        bigintA = bigintA.Abs();
        EInteger bigintB = bigintA;
        for (int j = 0; j < 100; ++j) {
          EInteger ba = bigintA;
          ba = ba.ShiftRight(j);
          TestCommon.CompareTestEqualAndConsistent(bigintB, ba);
          int negj = -j;
          ba = bigintA;
          ba = ba.ShiftLeft(negj);
          TestCommon.CompareTestEqualAndConsistent(bigintB, ba);
          bigintB = bigintB.Divide(EInteger.FromInt64(2));
        }
      }
    }
    @Test
    public void TestSign() {
      // not implemented yet
    }
    @Test
    public void TestSignedBitLength() {
      Assert.assertEquals(31, BigValueOf(-2147483647L).GetSignedBitLength());
      Assert.assertEquals(31, BigValueOf(-2147483648L).GetSignedBitLength());
      Assert.assertEquals(32, BigValueOf(-2147483649L).GetSignedBitLength());
      Assert.assertEquals(32, BigValueOf(-2147483650L).GetSignedBitLength());
      Assert.assertEquals(31, BigValueOf(2147483647L).GetSignedBitLength());
      Assert.assertEquals(32, BigValueOf(2147483648L).GetSignedBitLength());
      Assert.assertEquals(32, BigValueOf(2147483649L).GetSignedBitLength());
      Assert.assertEquals(32, BigValueOf(2147483650L).GetSignedBitLength());
      Assert.assertEquals(0, BigValueOf(0).GetSignedBitLength());
      Assert.assertEquals(1, BigValueOf(1).GetSignedBitLength());
      Assert.assertEquals(2, BigValueOf(2).GetSignedBitLength());
      Assert.assertEquals(2, BigValueOf(2).GetSignedBitLength());
      Assert.assertEquals(31, BigValueOf(Integer.MAX_VALUE).GetSignedBitLength());
      Assert.assertEquals(31, BigValueOf(Integer.MIN_VALUE).GetSignedBitLength());
      Assert.assertEquals(16, BigValueOf(65535).GetSignedBitLength());
      Assert.assertEquals(16, BigValueOf(-65535).GetSignedBitLength());
      Assert.assertEquals(17, BigValueOf(65536).GetSignedBitLength());
      Assert.assertEquals(16, BigValueOf(-65536).GetSignedBitLength());
      Assert.assertEquals(
        65,
        BigFromString("19084941898444092059").GetSignedBitLength());
      Assert.assertEquals(
        65,
        BigFromString("-19084941898444092059").GetSignedBitLength());
      Assert.assertEquals(0, BigValueOf(-1).GetSignedBitLength());
      Assert.assertEquals(1, BigValueOf(-2).GetSignedBitLength());
    }

    @Test
    public void TestSqrt() {
      FastRandom r = new FastRandom();
      for (int i = 0; i < 10000; ++i) {
        EInteger bigintA = RandomBigInteger(r);
        if (bigintA.signum() < 0) {
          bigintA = bigintA.Negate();
        }
        if (bigintA.signum() == 0) {
          bigintA = EInteger.FromInt32(1);
        }
        EInteger sr = bigintA.Sqrt();
        EInteger srsqr = sr.Multiply(sr);
        sr = sr.Add(EInteger.FromInt32(1));
        EInteger sronesqr = sr.Multiply(sr);
        if (srsqr.compareTo(bigintA) > 0) {
          Assert.fail(srsqr + " not " + bigintA +
            " or less (TestSqrt, sqrt=" + sr + ")");
        }
        if (sronesqr.compareTo(bigintA) <= 0) {
          Assert.fail(srsqr + " not greater than " + bigintA +
            " (TestSqrt, sqrt=" + sr + ")");
        }
      }
    }
    @Test
    public void TestSqrtWithRemainder() {
      EInteger[] eintarr;
      eintarr = EInteger.FromInt32(0).SqrtRem();
      Assert.assertEquals(EInteger.FromInt32(0), eintarr[0]);
      Assert.assertEquals(EInteger.FromInt32(0), eintarr[1]);
      eintarr = EInteger.FromInt32(-1).SqrtRem();
      Assert.assertEquals(EInteger.FromInt32(0), eintarr[0]);
      Assert.assertEquals(EInteger.FromInt32(0), eintarr[1]);
      eintarr = EInteger.FromInt32(1).SqrtRem();
      Assert.assertEquals(EInteger.FromInt32(1), eintarr[0]);
      Assert.assertEquals(EInteger.FromInt32(0), eintarr[1]);
    }
    @Test
    public void TestSubtract() {
EInteger ei1 =
  EInteger.FromString(
"5903310052234442839693218602919688229567185544510721229016780853271484375");
      EInteger ei2 = EInteger.FromString("710542735760100185871124267578125");
      {
String stringTemp = ei1.Subtract(ei2).toString();
Assert.assertEquals(
"5903310052234442839693218602919688229566475001774961128830909729003906250",
stringTemp);
}
    }
    @Test
    public void TestToByteArray() {
      // not implemented yet
    }

    @Test
    public void TestToRadixString() {
      FastRandom fr = new FastRandom();
      try {
        EInteger.FromInt32(1).ToRadixString(-1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ToRadixString(0);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ToRadixString(1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ToRadixString(37);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ToRadixString(Integer.MIN_VALUE);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.FromInt32(1).ToRadixString(Integer.MAX_VALUE);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      for (int i = 2; i <= 36; ++i) {
        for (int j = 0; j < 100; ++j) {
          StringAndBigInt sabi = StringAndBigInt.Generate(fr, i);
          // Upper case result expected
          String expected = ToUpperCaseAscii(sabi.getStringValue());
          int k = 0;
          // Expects result with no unnecessary leading zeros
          boolean negative = sabi.getBigIntValue().signum() < 0;
          if (expected.charAt(0) == '-') {
            ++k;
          }
          while (k < expected.length() - 1) {
            if (expected.charAt(k) == '0') {
              ++k;
            } else {
              break;
            }
          }
          expected = expected.substring(k);
          if (negative) {
            expected = "-" + expected;
          }
          Assert.assertEquals(
            expected,
            sabi.getBigIntValue().ToRadixString(i));
        }
      }
      FastRandom r = new FastRandom();
      for (int radix = 2; radix < 36; ++radix) {
        for (int i = 0; i < 80; ++i) {
          EInteger bigintA = RandomBigInteger(r);
          String s = bigintA.ToRadixString(radix);
          EInteger big2 = EInteger.FromRadixString(s, radix);
          Assert.assertEquals(big2.ToRadixString(radix), s);
        }
      }
    }

    @Test
    public void TestToString() {
      EInteger bi = EInteger.FromInt64(3);
      AssertBigIntegersEqual("3", bi);
      EInteger negseven = EInteger.FromInt64(-7);
      AssertBigIntegersEqual("-7", negseven);
      EInteger other = EInteger.FromInt64(-898989);
      AssertBigIntegersEqual("-898989", other);
      other = EInteger.FromInt64(898989);
      AssertBigIntegersEqual("898989", other);
      FastRandom r = new FastRandom();
      for (int i = 0; i < 1000; ++i) {
        EInteger bigintA = RandomBigInteger(r);
        String s = bigintA.toString();
        EInteger big2 = BigFromString(s);
        Assert.assertEquals(big2.toString(), s);
      }
    }
    @Test
    public void TestValueOf() {
      // not implemented yet
    }
    @Test
    public void TestXor() {
      // not implemented yet
    }
    @Test
    public void TestZero() {
      // not implemented yet
      {
        String stringTemp = EInteger.FromInt32(0).toString();
        Assert.assertEquals(
          "0",
          stringTemp);
      }
    }

    static EInteger BigFromBytes(byte[] bytes) {
      return EInteger.FromBytes(bytes, true);
    }

    static EInteger BigFromString(String str) {
      return EInteger.FromString(str);
    }

    static EInteger BigValueOf(long value) {
      return EInteger.FromInt64(value);
    }

    private static void TestGcdPair(
      EInteger biga,
      EInteger bigb,
      EInteger biggcd) {
      EInteger ba = biga.Gcd(bigb);
      EInteger bb = bigb.Gcd(biga);
      Assert.assertEquals(ba, biggcd);
      Assert.assertEquals(bb, biggcd);
    }

    private static String ToUpperCaseAscii(String str) {
      if (str == null) {
        return null;
      }
      int len = str.length();
      char c = (char)0;
      boolean hasLowerCase = false;
      for (int i = 0; i < len; ++i) {
        c = str.charAt(i);
        if (c >= 'a' && c <= 'z') {
          hasLowerCase = true;
          break;
        }
      }
      if (!hasLowerCase) {
        return str;
      }
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < len; ++i) {
        c = str.charAt(i);
        if (c >= 'a' && c <= 'z') {
          builder.append((char)(c - 0x20));
        } else {
          builder.append(c);
        }
      }
      return builder.toString();
    }

 public static void TestMultiplyDivideOne(
EInteger bigintA,
EInteger bigintB) {
      // Test that A*B/A = B and A*B/B = A
      EInteger bigintC = bigintA.Multiply(bigintB);
      EInteger bigintRem;
      EInteger bigintE;
      EInteger bigintD;
      TestCommon.CompareTestEqualAndConsistent(
        bigintC,
        bigintB.Multiply(bigintA));
      if (!bigintB.isZero()) {
        {
          EInteger[] divrem = bigintC.DivRem(bigintB);
          bigintD = divrem[0];
          bigintRem = divrem[1];
        }
        TestCommon.CompareTestEqualAndConsistent(bigintD, bigintA);
        TestCommon.CompareTestEqual(EInteger.FromInt32(0), bigintRem);
        bigintE = bigintC.Divide(bigintB);
        // Testing that DivRem and division method return
        // the same value
        TestCommon.CompareTestEqualAndConsistent(bigintD, bigintE);
        bigintE = bigintC.Remainder(bigintB);
        TestCommon.CompareTestEqualAndConsistent(bigintRem, bigintE);
        if (bigintE.signum() > 0 && !bigintC.Mod(bigintB).equals(bigintE)) {
          TestCommon.CompareTestEqualAndConsistent(
            bigintE,
            bigintC.Mod(bigintB));
        }
      }
      if (!bigintA.isZero()) {
          EInteger[] divrem = bigintC.DivRem(bigintA);
          bigintD = divrem[0];
          bigintRem = divrem[1];
        TestCommon.CompareTestEqualAndConsistent(bigintD, bigintB);
        TestCommon.CompareTestEqual(EInteger.FromInt32(0), bigintRem);
      }
      if (!bigintB.isZero()) {
          EInteger[] divrem = bigintA.DivRem(bigintB);
          bigintC = divrem[0];
          bigintRem = divrem[1];
        bigintD = bigintB.Multiply(bigintC);
        bigintD = bigintD.Add(bigintRem);
        TestCommon.CompareTestEqualAndConsistent(bigintA, bigintD);
      }
      // -----------------------------------
      // EDecimal
      // -----------------------------------
      EDecimal edecA = EDecimal.FromEInteger(bigintA);
      EDecimal edecB = EDecimal.FromEInteger(bigintB);
      EDecimal edecC = edecA.Multiply(edecB);
      EDecimal edecRem;
      EDecimal edecE;
      EDecimal edecD;
      TestCommon.CompareTestEqualAndConsistent(
        edecC,
        edecB.Multiply(edecA));
      if (!edecB.isZero()) {
        EDecimal[] divrem = edecC.DivRemNaturalScale(edecB);
        edecD = divrem[0].Plus(null);
        edecRem = divrem[1];
        TestCommon.CompareTestEqualAndConsistent(edecD, edecA);
        TestCommon.CompareTestEqual(EDecimal.Zero, edecRem);
        edecE = edecC.DivideToExponent(edecB, 0, ERounding.Down);
        // Testing that DivRemNaturalScale and division method return
        // the same value
        TestCommon.CompareTestEqualAndConsistent(edecD, edecE);
        edecE = edecC.RemainderNaturalScale(edecB, null);
        TestCommon.CompareTestEqualAndConsistent(edecRem, edecE);
      }
      if (!edecA.isZero()) {
        EDecimal[] divrem = edecC.DivRemNaturalScale(edecA);
        edecD = divrem[0].Plus(null);
        edecRem = divrem[1];
        TestCommon.CompareTestEqualAndConsistent(edecD, edecB);
        TestCommon.CompareTestEqual(EDecimal.Zero, edecRem);
      }
      if (!edecB.isZero()) {
        EDecimal[] divrem = edecA.DivRemNaturalScale(edecB);
        edecC = divrem[0].Plus(null);
        edecRem = divrem[1];
        edecD = edecB.Multiply(edecC);
        edecD = edecD.Add(edecRem);
        TestCommon.CompareTestEqualAndConsistent(edecA, edecD);
      }
      // -----------------------------------
      // EFloat
      // -----------------------------------
      EFloat efloatA = EFloat.FromEInteger(bigintA);
      EFloat efloatB = EFloat.FromEInteger(bigintB);
      EFloat efloatC = efloatA.Multiply(efloatB);
      EFloat efloatRem;
      EFloat efloatE;
      EFloat efloatD;
      TestCommon.CompareTestEqualAndConsistent(
        efloatC,
        efloatB.Multiply(efloatA));
      if (!efloatB.isZero()) {
        EFloat[] divrem = efloatC.DivRemNaturalScale(efloatB);
        efloatD = divrem[0].Plus(null);
        efloatRem = divrem[1];
        TestCommon.CompareTestEqualAndConsistent(efloatD, efloatA);
        TestCommon.CompareTestEqual(EFloat.Zero, efloatRem);
        efloatE = efloatC.DivideToExponent(efloatB, 0, ERounding.Down);
        // Testing that DivRemNaturalScale and division method return
        // the same value
        TestCommon.CompareTestEqualAndConsistent(efloatD, efloatE);
        efloatE = efloatC.RemainderNaturalScale(efloatB, null);
        TestCommon.CompareTestEqualAndConsistent(efloatRem, efloatE);
      }
      if (!efloatA.isZero()) {
        EFloat[] divrem = efloatC.DivRemNaturalScale(efloatA);
        efloatD = divrem[0].Plus(null);
        efloatRem = divrem[1];
        TestCommon.CompareTestEqualAndConsistent(efloatD, efloatB);
        TestCommon.CompareTestEqual(EFloat.Zero, efloatRem);
      }
      if (!efloatB.isZero()) {
        EFloat[] divrem = efloatA.DivRemNaturalScale(efloatB);
        efloatC = divrem[0].Plus(null);
        efloatRem = divrem[1];
        efloatD = efloatB.Multiply(efloatC);
        efloatD = efloatD.Add(efloatRem);
        TestCommon.CompareTestEqualAndConsistent(efloatA, efloatD);
      }
    }
  }
