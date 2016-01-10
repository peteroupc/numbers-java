package com.upokecenter.test;

import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.numbers.*;

  public class EFloatTest {
    @Test
    public void TestMovePointRight() {
      EFloat ef;
      EFloat ef2;
      ef = EFloat.FromInt32(0x100).MovePointRight(4);
      ef2 = EFloat.FromInt32(0x1000);
      Assert.assertEquals(0, ef.compareTo(ef2));
    }
    @Test
    public void TestMovePointLeft() {
      EFloat ef;
      EFloat ef2;
      ef = EFloat.FromInt32(0x150).MovePointLeft(4);
      ef2 = EFloat.FromInt32(0x15);
      Assert.assertEquals(0, ef.compareTo(ef2));
    }

    @Test
    public void TestFloatDecimalRoundTrip() {
      FastRandom r = new FastRandom();
      for (int i = 0; i < 5000; ++i) {
        EFloat ef = RandomObjects.RandomExtendedFloat(r);
        EDecimal ed = ef.ToExtendedDecimal();
        EFloat ef2 = ed.ToExtendedFloat();
        // Tests that values converted from float to decimal and
        // back have the same numerical value
        TestCommon.CompareTestEqual(ef, ef2);
      }
    }

    public static EFloat FromBinary(String str) {
      int smallExponent = 0;
      int index = 0;
      EInteger ret = EInteger.FromInt64(0);
      while (index < str.length()) {
        if (str.charAt(index) == '0') {
          ++index;
        } else {
          break;
        }
      }
      while (index < str.length()) {
        if (str.charAt(index) == '.') {
          ++index;
          break;
        }
        if (str.charAt(index) == '1') {
          ++index;
          if (ret.isZero()) {
            ret = EInteger.FromInt64(1);
          } else {
            ret = ret.ShiftLeft(1);
            ret = ret.Add(EInteger.FromInt64(1));
          }
        } else if (str.charAt(index) == '0') {
          ++index;
          ret = ret.ShiftLeft(1);
          continue;
        } else {
          break;
        }
      }
      while (index < str.length()) {
        if (str.charAt(index) == '1') {
          ++index;
          --smallExponent;
          if (ret.isZero()) {
            ret = EInteger.FromInt64(1);
          } else {
            ret = ret.ShiftLeft(1);
            ret = ret.Add(EInteger.FromInt64(1));
          }
        } else if (str.charAt(index) == '0') {
          ++index;
          --smallExponent;
          ret = ret.ShiftLeft(1);
          continue;
        } else {
          break;
        }
      }
      return EFloat.Create(ret, EInteger.FromInt64(smallExponent));
    }

    @Test
    public void TestAbs() {
      // not implemented yet
    }
    @Test
    public void TestAdd() {
      try {
        EFloat.Zero.Add(null, EContext.Unlimited);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestCompareTo() {
      FastRandom r = new FastRandom();
      for (int i = 0; i < 500; ++i) {
        EFloat bigintA = RandomObjects.RandomExtendedFloat(r);
        EFloat bigintB = RandomObjects.RandomExtendedFloat(r);
        EFloat bigintC = RandomObjects.RandomExtendedFloat(r);
        TestCommon.CompareTestRelations(bigintA, bigintB, bigintC);
      }
      TestCommon.CompareTestLess(EFloat.Zero, EFloat.NaN);
      EDecimal a = EDecimal.FromString(
        "7.00468923842476447758037175245551511770928808756622205663208" + "4784688080253355047487262563521426272927783429622650146484375");
      EDecimal b = EDecimal.FromString("5");
      TestCommon.CompareTestLess(b, a);
    }
    @Test
    public void TestCompareToSignal() {
      // not implemented yet
    }
    @Test
    public void TestCompareToWithContext() {
      // not implemented yet
    }
    @Test
    public void TestCreate() {
      // not implemented yet
    }
    @Test
    public void TestCreateNaN() {
      // not implemented yet
    }
    @Test
    public void TestDivide() {
      try {
 EDecimal.FromString("1").Divide(EDecimal.FromString("3"), null);
} catch (Exception ex) {
Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
    }
    @Test
    public void TestDivideToExponent() {
      // not implemented yet
    }
    @Test
    public void TestDivideToIntegerNaturalScale() {
      // not implemented yet
    }
    @Test
    public void TestDivideToIntegerZeroScale() {
      // not implemented yet
    }
    @Test
    public void TestDivideToSameExponent() {
      // not implemented yet
    }
    @Test
    public void TestEquals() {
      FastRandom r = new FastRandom();
      for (int i = 0; i < 500; ++i) {
        EFloat bigintA = RandomObjects.RandomExtendedFloat(r);
        EFloat bigintB = RandomObjects.RandomExtendedFloat(r);
        TestCommon.AssertEqualsHashCode(bigintA, bigintB);
      }
    }
    @Test
    public void TestEqualsInternal() {
      // not implemented yet
    }
    @Test
    public void TestExp() {
      // not implemented yet
    }
    @Test
    public void TestExponent() {
      // not implemented yet
    }
    @Test
    public void TestFromBigInteger() {
      // not implemented yet
    }
    @Test
    public void TestFromDouble() {
      // not implemented yet
    }

    @Test
    public void TestExtendedFloatDouble() {
      TestExtendedFloatDoubleCore(3.5, "3.5");
      TestExtendedFloatDoubleCore(7, "7");
      TestExtendedFloatDoubleCore(1.75, "1.75");
      TestExtendedFloatDoubleCore(3.5, "3.5");
      TestExtendedFloatDoubleCore((double)Integer.MIN_VALUE, "-2147483648");
      TestExtendedFloatDoubleCore(
        (double)Long.MIN_VALUE,
        "-9223372036854775808");
      FastRandom rand = new FastRandom();
      for (int i = 0; i < 2047; ++i) {
        // Try a random double with a given
        // exponent
        TestExtendedFloatDoubleCore(RandomObjects.RandomDouble(rand, i), null);
        TestExtendedFloatDoubleCore(RandomObjects.RandomDouble(rand, i), null);
        TestExtendedFloatDoubleCore(RandomObjects.RandomDouble(rand, i), null);
        TestExtendedFloatDoubleCore(RandomObjects.RandomDouble(rand, i), null);
      }
    }

    private static void TestExtendedFloatDoubleCore(double d, String s) {
      double oldd = d;
      EFloat bf = EFloat.FromDouble(d);
      if (s != null) {
        Assert.assertEquals(s, bf.toString());
      }
      d = bf.ToDouble();
      Assert.assertEquals((double)oldd, d, 0);
    }
    @Test
    public void TestExtendedFloatSingle() {
      FastRandom rand = new FastRandom();
      for (int i = 0; i < 255; ++i) {
        // Try a random float with a given
        // exponent
        TestExtendedFloatSingleCore(RandomObjects.RandomSingle(rand, i), null);
        TestExtendedFloatSingleCore(RandomObjects.RandomSingle(rand, i), null);
        TestExtendedFloatSingleCore(RandomObjects.RandomSingle(rand, i), null);
        TestExtendedFloatSingleCore(RandomObjects.RandomSingle(rand, i), null);
      }
    }

    private static void TestExtendedFloatSingleCore(float d, String s) {
      float oldd = d;
      EFloat bf = EFloat.FromSingle(d);
      if (s != null) {
        Assert.assertEquals(s, bf.toString());
      }
      d = bf.ToSingle();
      Assert.assertEquals((float)oldd, d, 0f);
    }

    @Test
    public void TestFromInt32() {
      // not implemented yet
    }
    @Test
    public void TestFromInt64() {
      // not implemented yet
    }
    @Test
    public void TestFromSingle() {
      // not implemented yet
    }
    @Test
    public void TestFromString() {
      try {
 EFloat.FromString("2", 0, 1, null);
} catch (Exception ex) {
Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      try {
        EFloat.FromString("");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestIsFinite() {
      // not implemented yet
    }

    @Test
    public void TestIsInfinity() {
      if (!(EFloat.PositiveInfinity.IsInfinity()))Assert.fail();
      if (!(EFloat.NegativeInfinity.IsInfinity()))Assert.fail();
      if (EFloat.Zero.IsInfinity())Assert.fail();
      if (EFloat.NaN.IsInfinity())Assert.fail();
    }
    @Test
    public void TestIsNaN() {
      if (EFloat.PositiveInfinity.IsNaN())Assert.fail();
      if (EFloat.NegativeInfinity.IsNaN())Assert.fail();
      if (EFloat.Zero.IsNaN())Assert.fail();
      if (!(EFloat.NaN.IsNaN()))Assert.fail();
    }
    @Test
    public void TestIsNegative() {
      // not implemented yet
    }
    @Test
    public void TestIsNegativeInfinity() {
      // not implemented yet
    }
    @Test
    public void TestIsPositiveInfinity() {
      // not implemented yet
    }
    @Test
    public void TestIsQuietNaN() {
      // not implemented yet
    }
    @Test
    public void TestIsSignalingNaN() {
      // not implemented yet
    }
    @Test
    public void TestIsZero() {
      if (EFloat.NaN.isZero())Assert.fail();
      if (EFloat.SignalingNaN.isZero())Assert.fail();
    }
    @Test
    public void TestLog() {
      if (!(EFloat.One.Log(null).IsNaN()))Assert.fail();
      if (!(EFloat.One.Log(EContext.Unlimited).IsNaN()))Assert.fail();
    }
    @Test
    public void TestLog10() {
      if (!(EFloat.One.Log10(null).IsNaN()))Assert.fail();
      if (!(EFloat.One.Log10(EContext.Unlimited)
              .IsNaN()))Assert.fail();
    }
    @Test
    public void TestMantissa() {
      // not implemented yet
    }
    @Test
    public void TestMax() {
      try {
        EFloat.Max(null, EFloat.One);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.Max(EFloat.One, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      FastRandom r = new FastRandom();
      for (int i = 0; i < 500; ++i) {
        EFloat bigintA = RandomObjects.RandomExtendedFloat(r);
        EFloat bigintB = RandomObjects.RandomExtendedFloat(r);
        if (!bigintA.isFinite() || !bigintB.isFinite()) {
          continue;
        }
        int cmp = TestCommon.CompareTestReciprocal(bigintA, bigintB);
        if (cmp < 0) {
     TestCommon.CompareTestEqual(
bigintB,
EFloat.Max(bigintA, bigintB));
        } else if (cmp > 0) {
     TestCommon.CompareTestEqual(
bigintA,
EFloat.Max(bigintA, bigintB));
        } else {
     TestCommon.CompareTestEqual(
bigintA,
EFloat.Max(bigintA, bigintB));
     TestCommon.CompareTestEqual(
bigintB,
EFloat.Max(bigintA, bigintB));
        }
      }
    }
    @Test
    public void TestMaxMagnitude() {
      try {
        EFloat.MaxMagnitude(null, EFloat.One);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.MaxMagnitude(EFloat.One, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestMin() {
      try {
        EFloat.Min(null, EFloat.One);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.Min(EFloat.One, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      FastRandom r = new FastRandom();
      for (int i = 0; i < 500; ++i) {
        EFloat bigintA = RandomObjects.RandomExtendedFloat(r);
        EFloat bigintB = RandomObjects.RandomExtendedFloat(r);
        if (!bigintA.isFinite() || !bigintB.isFinite()) {
          continue;
        }
        int cmp = TestCommon.CompareTestReciprocal(bigintA, bigintB);
        if (cmp < 0) {
     TestCommon.CompareTestEqual(
bigintA,
EFloat.Min(bigintA, bigintB));
        } else if (cmp > 0) {
     TestCommon.CompareTestEqual(
bigintB,
EFloat.Min(bigintA, bigintB));
        } else {
     TestCommon.CompareTestEqual(
bigintA,
EFloat.Min(bigintA, bigintB));
     TestCommon.CompareTestEqual(
bigintB,
EFloat.Min(bigintA, bigintB));
        }
      }
    }
    @Test
    public void TestMinMagnitude() {
      try {
        EFloat.MinMagnitude(null, EFloat.One);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.MinMagnitude(EFloat.One, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestMultiply() {
      // not implemented yet
    }
    @Test
    public void TestMultiplyAndAdd() {
      // not implemented yet
    }
    @Test
    public void TestMultiplyAndSubtract() {
      // not implemented yet
    }
    @Test
    public void TestNegate() {
      // not implemented yet
    }
    @Test
    public void TestNextMinus() {
      // not implemented yet
    }
    @Test
    public void TestNextPlus() {
      // not implemented yet
    }
    @Test
    public void TestNextToward() {
      // not implemented yet
    }
    @Test
    public void TestPI() {
      // not implemented yet
    }
    @Test
    public void TestPlus() {
      // not implemented yet
    }
    @Test
    public void TestPow() {
      // not implemented yet
    }
    @Test
    public void TestQuantize() {
      // not implemented yet
    }
    @Test
    public void TestReduce() {
      // not implemented yet
    }
    @Test
    public void TestRemainder() {
      // not implemented yet
    }
    @Test
    public void TestRemainderNaturalScale() {
      // not implemented yet
    }
    @Test
    public void TestRemainderNear() {
      // not implemented yet
    }
    @Test
    public void TestRoundToBinaryPrecision() {
      // not implemented yet
    }
    @Test
    public void TestRoundToExponent() {
      // not implemented yet
    }
    @Test
    public void TestRoundToExponentExact() {
      // not implemented yet
    }
    @Test
    public void TestRoundToIntegralExact() {
      // not implemented yet
    }
    @Test
    public void TestRoundToIntegralNoRoundedFlag() {
      // not implemented yet
    }
    @Test
    public void TestRoundToPrecision() {
      // not implemented yet
    }
    @Test
    public void TestSign() {
      // not implemented yet
    }
    @Test
    public void TestSquareRoot() {
      // not implemented yet
    }
    @Test
    public void TestSubtract() {
      try {
        EFloat.Zero.Subtract(null, EContext.Unlimited);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestToEInteger() {
      try {
        EFloat.PositiveInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.NegativeInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.NaN.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.SignalingNaN.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestToEIntegerExact() {
      // not implemented yet
    }
    @Test
    public void TestToDouble() {
      // not implemented yet
    }
    @Test
    public void TestToEngineeringString() {
      // not implemented yet
    }
    @Test
    public void TestToExtendedDecimal() {
      // not implemented yet
    }
    @Test
    public void TestToPlainString() {
      // not implemented yet
    }
    @Test
    public void TestToSingle() {
      // not implemented yet
    }
    @Test
    public void TestToString() {
      // not implemented yet
    }
    @Test
    public void TestUnsignedMantissa() {
      // not implemented yet
    }
  }
