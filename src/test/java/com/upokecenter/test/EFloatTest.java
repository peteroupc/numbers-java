package com.upokecenter.test;

import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.util.*;
import com.upokecenter.numbers.*;

  public class EFloatTest {
    public static EFloat FromBinary(String str) {
      int smallExponent = 0;
      int index = 0;
      EInteger ret = EInteger.FromInt32(0);
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
            ret = EInteger.FromInt32(1);
          } else {
            ret = ret.ShiftLeft(1);
            ret = ret.Add(EInteger.FromInt32(1));
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
            ret = EInteger.FromInt32(1);
          } else {
            ret = ret.ShiftLeft(1);
            ret = ret.Add(EInteger.FromInt32(1));
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
      return EFloat.Create(ret, EInteger.FromInt32(smallExponent));
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
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      RandomGenerator fr = new RandomGenerator();
      TestAddCloseExponent(fr, 0);
      TestAddCloseExponent(fr, 100);
      TestAddCloseExponent(fr, -100);
      TestAddCloseExponent(fr, Integer.MIN_VALUE);
      TestAddCloseExponent(fr, Integer.MAX_VALUE);
    }
    @Test
    public void TestCompareTo() {
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        EFloat bigintA = RandomObjects.RandomEFloat(r);
        EFloat bigintB = RandomObjects.RandomEFloat(r);
        EFloat bigintC = RandomObjects.RandomEFloat(r);
        TestCommon.CompareTestRelations(bigintA, bigintB, bigintC);
      }
      TestCommon.CompareTestLess(EFloat.Zero, EFloat.NaN);
String str2561 =
  "7.00468923842476447758037175245551511770928808756622205663208" +
  "4784688080253355047487262563521426272927783429622650146484375";

      EDecimal a = EDecimal.FromString(
  str2561);
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
      try {
 EFloat.CreateNaN(null);
Assert.fail("Should have failed");
} catch (NullPointerException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      try {
 EFloat.CreateNaN(EInteger.FromString("-1"));
Assert.fail("Should have failed");
} catch (IllegalArgumentException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      try {
 EFloat.CreateNaN(null, false, false, null);
Assert.fail("Should have failed");
} catch (NullPointerException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      EFloat ef = EFloat.CreateNaN(EInteger.FromInt32(0), false, true, null);
      if (!(ef.isNegative())) {
 Assert.fail();
 }
      ef = EFloat.CreateNaN(EInteger.FromInt32(0), false, false, null);
      if (!(!ef.isNegative())) {
 Assert.fail();
 }
    }
    @Test
    public void TestDivide() {
      try {
        EFloat.FromString("1").Divide(EFloat.FromString("3"), null);
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      {
String stringTemp = EFloat.FromString(
  "1").Divide(EFloat.FromInt32(8)).toString();
Assert.assertEquals(
  "0.125",
  stringTemp);
}
      {
String stringTemp = EFloat.FromString(
  "10").Divide(EFloat.FromInt32(80)).toString();
Assert.assertEquals(
  "0.125",
  stringTemp);
}
      {
String stringTemp = EFloat.FromString(
  "10000").Divide(EFloat.FromInt32(80000)).toString();
Assert.assertEquals(
  "0.125",
  stringTemp);
}
      {
String stringTemp = EFloat.FromString(
  "1000").Divide(EFloat.FromInt32(8)).toString();
Assert.assertEquals(
  "125",
  stringTemp);
}
      {
String stringTemp = EFloat.FromString(
  "1").Divide(EFloat.FromInt32(256)).toString();
Assert.assertEquals(
  "0.00390625",
  stringTemp);
}
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 5000; ++i) {
        EFloat ed1 = RandomObjects.RandomEFloat(fr);
        EFloat ed2 = RandomObjects.RandomEFloat(fr);
        if (!ed1.isFinite() || !ed2.isFinite()) {
          continue;
        }
        EFloat ed3 = ed1.Multiply(ed2);
        if (!(ed3.isFinite())) {
 Assert.fail();
 }
        EFloat ed4;
        ed4 = ed3.Divide(ed1);
        if (!ed1.isZero()) {
          TestCommon.CompareTestEqual(ed4, ed2);
        } else {
          if (!(ed4.IsNaN())) {
 Assert.fail();
 }
        }
        ed4 = ed3.Divide(ed2);
        if (!ed2.isZero()) {
          TestCommon.CompareTestEqual(ed4, ed1);
        } else {
          if (!(ed4.IsNaN())) {
 Assert.fail();
 }
        }
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
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        EFloat bigintA = RandomObjects.RandomEFloat(r);
        EFloat bigintB = RandomObjects.RandomEFloat(r);
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
    public void TestEFloatDouble() {
      TestEFloatDoubleCore(3.5, "3.5");
      TestEFloatDoubleCore(7, "7");
      TestEFloatDoubleCore(1.75, "1.75");
      TestEFloatDoubleCore(3.5, "3.5");
      TestEFloatDoubleCore((double)Integer.MIN_VALUE, "-2147483648");
      TestEFloatDoubleCore(
        (double)Long.MIN_VALUE,
        "-9223372036854775808");
      RandomGenerator rand = new RandomGenerator();
      for (int i = 0; i < 2047; ++i) {
        // Try a random double with a given
        // exponent
        TestEFloatDoubleCore(RandomObjects.RandomDouble(rand, i), null);
        TestEFloatDoubleCore(RandomObjects.RandomDouble(rand, i), null);
        TestEFloatDoubleCore(RandomObjects.RandomDouble(rand, i), null);
        TestEFloatDoubleCore(RandomObjects.RandomDouble(rand, i), null);
      }
    }
    @Test
    public void TestEFloatSingle() {
      RandomGenerator rand = new RandomGenerator();
      for (int i = 0; i < 255; ++i) {
        // Try a random float with a given
        // exponent
        TestEFloatSingleCore(RandomObjects.RandomSingle(rand, i), null);
        TestEFloatSingleCore(RandomObjects.RandomSingle(rand, i), null);
        TestEFloatSingleCore(RandomObjects.RandomSingle(rand, i), null);
        TestEFloatSingleCore(RandomObjects.RandomSingle(rand, i), null);
      }
    }

    @Test
public void TestPrecisionOneHalfEven() {
  EFloat enumber = EFloat.Create(0x03, -1);
  EContext ectx = EContext.ForPrecisionAndRounding(1, ERounding.HalfEven);
  enumber = enumber.RoundToPrecision(ectx);
  TestCommon.CompareTestEqual(
    EFloat.Create(0x04, -1),
    enumber);
}

    @Test
    public void TestFloatDecimalSpecific() {
      String str =
  "874952453585761710286297571153092638434027760916318352";
      str += "6012074333883129482197203556946927736656883955";
      str += "41653.74728887385887787786487024277448654174804687500";
      EDecimal ed = EDecimal.FromString(str);
      EFloat ef2 = ed.ToEFloat();
      Assert.assertEquals(ef2.toString(), 0, ed.CompareToBinary(ef2));
    }

    @Test
    public void TestFloatDecimalRoundTrip() {
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 5000; ++i) {
        EFloat ef = RandomObjects.RandomEFloat(r);
        EDecimal ed = ef.ToEDecimal();
          EFloat ef2 = ed.ToEFloat();
          // Tests that values converted from float to decimal and
          // back have the same numerical value
          TestCommon.CompareTestEqual(ef, ef2);
      }
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
        EFloat.FromString("0..1");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("0.1x+222");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("0.1g-222");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
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
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      Assert.assertEquals(EFloat.Zero, EFloat.FromString("0"));
      Assert.assertEquals(EFloat.Zero, EFloat.FromString("0", null));
      try {
        EFloat.FromString(null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(null, 0, 1);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("x", -1, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("x", 2, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("x", 0, -1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("x", 0, 2);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("x", 1, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(null, 0, 1, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("x", -1, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("x", 2, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("x", 0, -1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("x", 0, 2, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("x", 1, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(
          "Infinity",
          EContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(
          "-Infinity",
          EContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(
          "NaN",
          EContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(
          "sNaN",
          EContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(
          "Infinity",
          EContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(
          "-Infinity",
          EContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(
          "NaN",
          EContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(
          "sNaN",
          EContext.Unlimited.WithSimplified(true));
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
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
      if (!(EFloat.PositiveInfinity.IsInfinity())) {
 Assert.fail();
 }
      if (!(EFloat.NegativeInfinity.IsInfinity())) {
 Assert.fail();
 }
      if (EFloat.Zero.IsInfinity()) {
 Assert.fail();
 }
      if (EFloat.NaN.IsInfinity()) {
 Assert.fail();
 }
    }
    @Test
    public void TestIsNaN() {
      if (EFloat.PositiveInfinity.IsNaN()) {
 Assert.fail();
 }
      if (EFloat.NegativeInfinity.IsNaN()) {
 Assert.fail();
 }
      if (EFloat.Zero.IsNaN()) {
 Assert.fail();
 }
      if (!(EFloat.NaN.IsNaN())) {
 Assert.fail();
 }
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
      if (EFloat.NaN.isZero()) {
 Assert.fail();
 }
      if (EFloat.SignalingNaN.isZero()) {
 Assert.fail();
 }
    }
    @Test
    public void TestLog() {
      if (!(EFloat.One.Log(null).IsNaN())) {
 Assert.fail();
 }
      if (!(EFloat.One.Log(EContext.Unlimited).IsNaN())) {
 Assert.fail();
 }
    }
    @Test
    public void TestLog10() {
      if (!(EFloat.One.Log10(null).IsNaN())) {
 Assert.fail();
 }
      if (!(EFloat.One.Log10(EContext.Unlimited)
              .IsNaN())) {
 Assert.fail();
 }
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
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.Max(EFloat.One, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        EFloat bigintA = RandomObjects.RandomEFloat(r);
        EFloat bigintB = RandomObjects.RandomEFloat(r);
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
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.MaxMagnitude(EFloat.One, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
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
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.Min(EFloat.One, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        EFloat bigintA = RandomObjects.RandomEFloat(r);
        EFloat bigintB = RandomObjects.RandomEFloat(r);
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
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.MinMagnitude(EFloat.One, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
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
    public void TestMovePointRight() {
      EFloat ef;
      EFloat ef2;
      ef = EFloat.FromInt32(0x100).MovePointRight(4);
      ef2 = EFloat.FromInt32(0x1000);
      Assert.assertEquals(0, ef.compareTo(ef2));
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

    private static final String[] ValueFPIntegers = { "1", "2", "4", "8",
      "281474976710656", "562949953421312", "1125899906842624",
      "2251799813685248", "4503599627370496", "9007199254740992",
      "18014398509481984", "36028797018963968", "72057594037927936",
      "144115188075855872", "288230376151711744",

  "11235582092889474423308157442431404585112356118389416079589380072358292237843810195794279832650471001320007117491962084853674360550901038905802964414967132773610493339054092829768888725077880882465817684505312860552384417646403930092119569408801702322709406917786643639996702871154982269052209770601514008576",

  "22471164185778948846616314884862809170224712236778832159178760144716584475687620391588559665300942002640014234983924169707348721101802077811605928829934265547220986678108185659537777450155761764931635369010625721104768835292807860184239138817603404645418813835573287279993405742309964538104419541203028017152",

  "44942328371557897693232629769725618340449424473557664318357520289433168951375240783177119330601884005280028469967848339414697442203604155623211857659868531094441973356216371319075554900311523529863270738021251442209537670585615720368478277635206809290837627671146574559986811484619929076208839082406056034304",

  "89884656743115795386465259539451236680898848947115328636715040578866337902750481566354238661203768010560056939935696678829394884407208311246423715319737062188883946712432742638151109800623047059726541476042502884419075341171231440736956555270413618581675255342293149119973622969239858152417678164812112068608"
    };

    private static final int[] ValueFPIntegersExp = { 0, 1, 2, 3, 48, 49,
      50, 51, 52,
      53, 54, 55, 56, 57, 58, 1020, 1021, 1022, 1023 };

    @Test
    public void TestFPDoubles() {
      for (int i = 0; i < ValueFPIntegersExp.length; ++i) {
        // Positive
        EFloat ef = EFloat.Create(1, ValueFPIntegersExp[i]);
        Assert.assertEquals(ValueFPIntegers[i], ef.toString());
        ef = EFloat.FromDouble(ef.ToDouble());
        Assert.assertEquals(ValueFPIntegers[i], ef.toString());
        ef = EFloat.FromDouble(
          EDecimal.FromString(ValueFPIntegers[i]).ToDouble());
        Assert.assertEquals(ValueFPIntegers[i], ef.toString());
        // Negative
        ef = EFloat.Create(-1, ValueFPIntegersExp[i]);
        Assert.assertEquals("-" + ValueFPIntegers[i], ef.toString());
        ef = EFloat.FromDouble(ef.ToDouble());
        Assert.assertEquals("-" + ValueFPIntegers[i], ef.toString());
        ef = EFloat.FromDouble(
          EDecimal.FromString("-" + ValueFPIntegers[i]).ToDouble());
        Assert.assertEquals("-" + ValueFPIntegers[i], ef.toString());
      }
      for (int i = -1074; i < 1024; ++i) {
        String intstr = TestCommon.IntToString(i);
        // Positive
        EFloat ef = EFloat.Create(1, i);
        String fpstr = ef.toString();
        ef = EFloat.FromDouble(ef.ToDouble());
        Assert.assertEquals(intstr, fpstr, ef.toString());
        ef = EFloat.FromDouble(
          EDecimal.FromString(fpstr).ToDouble());
        Assert.assertEquals(intstr, fpstr, ef.toString());
        // Negative
        ef = EFloat.Create(-1, i);
        Assert.assertEquals(intstr,"-" + fpstr,ef.toString());
        ef = EFloat.FromDouble(ef.ToDouble());
        Assert.assertEquals(intstr,"-" + fpstr,ef.toString());
        ef = EFloat.FromDouble(
          EDecimal.FromString("-" + fpstr).ToDouble());
        Assert.assertEquals(intstr,"-" + fpstr,ef.toString());
      }
      EFloat ef2 = EFloat.Create(1, 1024);
      Assert.assertTrue(((Double)(ef2.ToDouble())).isInfinite());
      ef2 = EFloat.Create(-1, 1024);
      Assert.assertTrue(((Double)(ef2.ToDouble())).isInfinite());
      ef2 = EFloat.Create(1, -1075);
      if (!(EFloat.FromDouble(ef2.ToDouble()).isZero()))Assert.fail();
      ef2 = EFloat.Create(-1, -1075);
      if (!(EFloat.FromDouble(ef2.ToDouble()).isZero()))Assert.fail();
    }

    @Test
    public void TestPI() {
      // not implemented yet
    }

    @Test
    public void TestPlus() {
      Assert.assertEquals(
  EFloat.Zero,
  EFloat.NegativeZero.Plus(EContext.Basic));
      Assert.assertEquals(
  EFloat.Zero,
  EFloat.NegativeZero.Plus(null));
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
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestToDouble() {
      // not implemented yet
    }
    @Test
    public void TestToEInteger() {
      try {
        EFloat.PositiveInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.NegativeInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.NaN.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.SignalingNaN.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      EFloat flo = EFloat.Create(999, -1);
      try {
        flo.ToEInteger();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.PositiveInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.NegativeInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    public EFloat RandomDoubleEFloat(RandomGenerator rnd) {
      return this.RandomDoubleEFloat(rnd, false);
    }

    public EFloat RandomDoubleEFloat(RandomGenerator rnd, boolean subnormal) {
      StringBuilder sb = new StringBuilder();
      if (rnd.UniformInt(2) == 0) {
        sb.append('-');
      }
      sb.append(subnormal ? '0' : '1');
      int subSize = 52;
      int[] oneChances = { 98, 2, 50, 50, 50 };
      int oneChance = oneChances[rnd.UniformInt(oneChances.length)];
      if (subnormal) {
        subSize = rnd.UniformInt(51);
      }
      for (int i = 0; i < 52; ++i) {
        sb.append(((i < 52 - subSize) || (rnd.UniformInt(100) >= oneChance)) ?
          '0' : '1');
      }
      String valueSbString = sb.toString();
      int expo = 0, exponent;
      if (subnormal) {
        exponent = -1074;
      } else {
        expo = rnd.UniformInt(2045) + 1 - 1023;
        exponent = expo - 52;
      }
      EInteger valueEiExponent = EInteger.FromInt64(exponent);
      EFloat ef = EFloat.Create(
        EInteger.FromRadixString(valueSbString, 2),
        valueEiExponent);
      return ef;
    }

    public EFloat RandomSingleEFloat(RandomGenerator rnd) {
      return this.RandomSingleEFloat(rnd, false);
    }

    public EFloat RandomSingleEFloat(RandomGenerator rnd, boolean subnormal) {
      StringBuilder sb = new StringBuilder();
      if (rnd.UniformInt(2) == 0) {
        sb.append('-');
      }
      sb.append(subnormal ? '0' : '1');
      int subSize = 23;
      int[] oneChances = { 98, 2, 50, 50, 50 };
      int oneChance = oneChances[rnd.UniformInt(oneChances.length)];
      if (subnormal) {
        subSize = rnd.UniformInt(22);
      }
      for (int i = 0; i < 23; ++i) {
        sb.append(((i < 23 - subSize) || (rnd.UniformInt(100) >= oneChance)) ?
          '0' : '1');
      }
      String valueSbString = sb.toString();
      int expo = 0, exponent;
      if (subnormal) {
        exponent = -149;
      } else {
        expo = rnd.UniformInt(252) + 1 - 127;
        exponent = expo - 23;
      }
      EInteger valueEiExponent = EInteger.FromInt64(exponent);
      EFloat ef = EFloat.Create(
        EInteger.FromRadixString(valueSbString, 2),
        valueEiExponent);
      return ef;
    }

    public static String OutputDouble(double dbl) {
      EFloat ef = EFloat.FromDouble(dbl);
      return dbl + " [" + ef.getMantissa().Abs().ToRadixString(2) +
        "," + ef.getExponent() + "]";
    }

    public static String OutputSingle(float flt) {
      EFloat ef = EFloat.FromSingle(flt);
      return flt + " [" + ef.getMantissa().Abs().ToRadixString(2) +
        "," + ef.getExponent() + "]";
    }

    public static String OutputEF(EFloat ef) {
      return ef.ToDouble() + " [" + ef.getMantissa().Abs().ToRadixString(2) +
        "," + ef.getExponent() + "]";
    }

    public static void TestDoubleRounding(
  EFloat expected,
  EFloat input,
  EFloat src) {
      if (!input.isFinite() || !expected.isFinite()) {
        return;
      }
      double expectedDouble = expected.ToDouble();
      if (((Double)(expectedDouble)).isInfinite()) {
        return;
      }
      String str = input.toString();
      if (input.ToDouble() != expectedDouble) {
  String msg =
  "\nexpectedDbl " + OutputDouble(expectedDouble) +
  ",\ngot----- " + OutputDouble(input.ToDouble()) +
        "\nsrc-----=" + OutputEF(src) + "\nexpected=" + OutputEF(expected) +
        "\ninput---=" + OutputEF(input);
        Assert.fail(msg);
      }
      double inputDouble = EDecimal.FromString(str).ToDouble();
      if (inputDouble != expectedDouble) {
  String msg = "\nexpectedDbl " + OutputDouble(expectedDouble) +
          ",\ngot----- " + OutputDouble(inputDouble) +
        "\nsrc-----=" + OutputEF(src) + "\nexpected=" + OutputEF(expected) +
        "\ninput---=" + OutputEF(input);
        Assert.fail(msg);
      }
    }

    public static void TestSingleRounding(
  EFloat expected,
  EFloat input,
  EFloat src) {
      if (!input.isFinite() || !expected.isFinite()) {
        return;
      }
      float expectedSingle = expected.ToSingle();
      if (((Float)(expectedSingle)).isInfinite()) {
        return;
      }
      String str = input.toString();
      if (input.ToSingle() != expectedSingle) {
        String msg = "\nexpectedDbl " + OutputSingle(expectedSingle) +
        ",\ngot----- " +
              OutputSingle(input.ToSingle()) + "\nsrc-----=" + OutputEF(src) +
          "\nexpected=" + OutputEF(expected) + "\ninput---=" +
                OutputEF(input);
        Assert.fail(msg);
      }
      float inputSingle = EDecimal.FromString(str).ToSingle();
      if (inputSingle != expectedSingle) {
        String msg = "\nexpectedDbl " + OutputSingle(expectedSingle) +
                ",\ngot----- " +
              OutputSingle(inputSingle) + "\nsrc-----=" + OutputEF(src) +
          "\nexpected=" + OutputEF(expected) + "\ninput---=" +
                OutputEF(input);
        Assert.fail(msg);
      }
    }

    private static EFloat quarter = EFloat.FromString("0.25");
    private static EFloat half = EFloat.FromString("0.5");
    private static EFloat threequarter = EFloat.FromString("0.75");

    private static void TestToFloatRoundingOne(EFloat efa, boolean dbl) {
      boolean isEven = efa.getUnsignedMantissa().isEven();
      EFloat efprev = efa.NextMinus(dbl ? EContext.Binary64 :
        EContext.Binary32);
      EFloat efnext = efa.NextPlus(dbl ? EContext.Binary64 :
        EContext.Binary32);
      EFloat efnextgap = efnext.Subtract(efa);
      EFloat efprevgap = efa.Subtract(efprev);
      EFloat efprev1q = efprev.Add(
     efprevgap.Multiply(quarter));
   EFloat efprev2q = efprev.Add(
     efprevgap.Multiply(half));
   EFloat efprev3q = efprev.Add(
     efprevgap.Multiply(threequarter));
      EFloat efnext1q = efa.Add(efnextgap.Multiply(quarter));
      EFloat efnext2q = efa.Add(efnextgap.Multiply(half));
      EFloat efnext3q = efa.Add(efnextgap.Multiply(threequarter));
      if (dbl) {
        TestDoubleRounding(efprev, efprev, efa);
        TestDoubleRounding(efprev, efprev1q, efa);
        TestDoubleRounding(isEven ? efa : efprev, efprev2q, efa);
        TestDoubleRounding(efa, efprev3q, efa);
        TestDoubleRounding(efa, efa, efa);
        TestDoubleRounding(efa, efnext1q, efa);
        TestDoubleRounding(isEven ? efa : efnext, efnext2q, efa);
        TestDoubleRounding(efnext, efnext3q, efa);
        TestDoubleRounding(efnext, efnext, efa);
      } else {
        TestSingleRounding(efprev, efprev, efa);
        TestSingleRounding(efprev, efprev1q, efa);
        TestSingleRounding(isEven ? efa : efprev, efprev2q, efa);
        TestSingleRounding(efa, efprev3q, efa);
        TestSingleRounding(efa, efa, efa);
        TestSingleRounding(efa, efnext1q, efa);
        TestSingleRounding(isEven ? efa : efnext, efnext2q, efa);
        TestSingleRounding(efnext, efnext3q, efa);
        TestSingleRounding(efnext, efnext, efa);
      }
    }

    private static String EFToString(EFloat ef) {
      return "[" + ef.getMantissa().ToRadixString(2) + "," +
        ef.getMantissa().GetUnsignedBitLength() + "," + ef.getExponent() + "]";
    }

    private static void TestBinaryToDecimal(
  String input,
  int digits,
  String expected,
  String msg) {
    EContext ec = EContext.ForPrecisionAndRounding(
  digits,
  ERounding.HalfEven);
      String str = EFloat.FromString(input, EContext.Binary64)
          .ToEDecimal().RoundToPrecision(ec).toString();
      TestCommon.CompareTestEqual(
       EDecimal.FromString(expected),
       EDecimal.FromString(str),
       msg);
    }

    @Test
    public void TestBinaryDecimalLine() {
TestBinaryToDecimal(
  "9.5673250588722716156829968E22",
  12,
  "9.56732505887E22",
  "");
    }

    @Test(timeout = 120000)
    public void TestToShortestString() {
      {
        EFloat ef = EFloat.FromDouble(64.1);
String stringTemp = ef.ToShortestString(EContext.Binary64);
Assert.assertEquals(
  "64.1",
  stringTemp);
stringTemp =
          EFloat.FromSingle(0.1f).ToShortestString(EContext.Binary32);
Assert.assertEquals(
  "0.1",
  stringTemp);
}
      {
String stringTemp = EFloat.NegativeZero.ToShortestString(EContext.Binary32);
Assert.assertEquals(
  "-0",
  stringTemp);
}
      {
String stringTemp = EFloat.FromDouble(0.1).ToShortestString(EContext.Binary64);
Assert.assertEquals(
  "0.1",
  stringTemp);
}
      {
String stringTemp = EFloat.FromString(
  "100").ToShortestString(EContext.Binary64);
Assert.assertEquals(
  "100",
  stringTemp);
}
      {
String stringTemp = EFloat.FromString(
  "1000").ToShortestString(EContext.Binary64);
Assert.assertEquals(
  "1000",
  stringTemp);
}
      {
String stringTemp = EFloat.FromString(
  "1000000").ToShortestString(EContext.Binary64);
Assert.assertEquals(
  "1000000",
  stringTemp);
}
      {
String stringTemp = EFloat.FromString(
  "10000000").ToShortestString(EContext.Binary64);
Assert.assertEquals(
  "1E+7",
  stringTemp);
}
      {
String stringTemp = EFloat.FromString(
  "10000000000").ToShortestString(EContext.Binary64);
Assert.assertEquals(
  "1E+10",
  stringTemp);
}
      {
String stringTemp =
  EFloat.FromDouble(199999d).ToShortestString(EContext.Binary64);
Assert.assertEquals(
  "199999",
  stringTemp);
}
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 10000; ++i) {
        EFloat efa = this.RandomDoubleEFloat(fr);
        String shortestStr = efa.ToShortestString(EContext.Binary64);
        EFloat shortest = EFloat.FromString(
          shortestStr,
          EContext.Binary64);
        if (!efa.equals(shortest)) {
          String msg = "\n" + EFToString(efa) + "\n" + EFToString(shortest) +
     "\n" + shortestStr;
          TestCommon.CompareTestEqual(
            efa,
            shortest,
            msg);
        }
      }
    }
    @Test
    public void TestToSingleRounding() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 1500; ++i) {
        EFloat efa = this.RandomSingleEFloat(fr, i >= 250);
        TestToFloatRoundingOne(efa, false);
      }
    }

    @Test
    public void TestConversions() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 20000; ++i) {
        boolean isNum, isTruncated, isInteger;
        EInteger eint;
        EFloat enumber = RandomObjects.RandomEFloat(fr);
        if (!enumber.isFinite()) {
          try {
 enumber.ToByteChecked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
  Assert.assertEquals(
  EInteger.FromInt32(0),
  EInteger.FromByte(enumber.ToByteUnchecked()));
          try {
 enumber.ToByteIfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          try {
 enumber.ToInt16Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
Assert.assertEquals(
  EInteger.FromInt32(0),
  EInteger.FromInt16(enumber.ToInt16Unchecked()));
          try {
 enumber.ToInt16IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          try {
 enumber.ToInt32Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
Assert.assertEquals(
  EInteger.FromInt32(0),
  EInteger.FromInt32(enumber.ToInt32Unchecked()));
          try {
 enumber.ToInt32IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          try {
 enumber.ToInt64Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
Assert.assertEquals(
  EInteger.FromInt32(0),
  EInteger.FromInt64(enumber.ToInt64Unchecked()));
          try {
 enumber.ToInt64IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          continue;
        }
        EFloat enumberInteger = EFloat.FromEInteger(enumber.ToEInteger());
        isInteger = enumberInteger.compareTo(enumber) == 0;
        eint = enumber.ToEInteger();
        isNum = enumber.compareTo(
        EFloat.FromString("0")) >= 0 && enumber.compareTo(
        EFloat.FromString("255")) <= 0;
        isTruncated = enumber.ToEInteger().compareTo(
        EInteger.FromString("0")) >= 0 && enumber.ToEInteger().compareTo(
        EInteger.FromString("255")) <= 0;
        if (isNum) {
     TestCommon.AssertEquals(
  eint,
  EInteger.FromByte(enumber.ToByteChecked()));
   TestCommon.AssertEquals(
  eint,
  EInteger.FromByte(enumber.ToByteUnchecked()));
          if (isInteger) {
     TestCommon.AssertEquals(
  eint,
  EInteger.FromByte(enumber.ToByteIfExact()));
          } else {
            try {
 enumber.ToByteIfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        } else if (isTruncated) {
     TestCommon.AssertEquals(
  eint,
  EInteger.FromByte(enumber.ToByteChecked()));
   TestCommon.AssertEquals(
  eint,
  EInteger.FromByte(enumber.ToByteUnchecked()));
          try {
 enumber.ToByteIfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
        } else {
          try {
 enumber.ToByteChecked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          try {
 enumber.ToByteUnchecked();
} catch (Exception ex) {
Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          if (isInteger) {
            try {
 enumber.ToByteIfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          } else {
            try {
 enumber.ToByteIfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        }
        isNum = enumber.compareTo(
        EFloat.FromString("-32768")) >= 0 && enumber.compareTo(
        EFloat.FromString("32767")) <= 0;
        isTruncated = enumber.ToEInteger().compareTo(
        EInteger.FromString("-32768")) >= 0 && enumber.ToEInteger().compareTo(
        EInteger.FromString("32767")) <= 0;
        if (isNum) {
   TestCommon.AssertEquals(
  eint,
  EInteger.FromInt16(enumber.ToInt16Checked()));
 TestCommon.AssertEquals(
  eint,
  EInteger.FromInt16(enumber.ToInt16Unchecked()));
          if (isInteger) {
   TestCommon.AssertEquals(
  eint,
  EInteger.FromInt16(enumber.ToInt16IfExact()));
          } else {
            try {
 enumber.ToInt16IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        } else if (isTruncated) {
   TestCommon.AssertEquals(
  eint,
  EInteger.FromInt16(enumber.ToInt16Checked()));
 TestCommon.AssertEquals(
  eint,
  EInteger.FromInt16(enumber.ToInt16Unchecked()));
          try {
 enumber.ToInt16IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
        } else {
          try {
 enumber.ToInt16Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          try {
 enumber.ToInt16Unchecked();
} catch (Exception ex) {
Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          if (isInteger) {
            try {
 enumber.ToInt16IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          } else {
            try {
 enumber.ToInt16IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        }
        isNum = enumber.compareTo(
        EFloat.FromString("-2147483648")) >= 0 && enumber.compareTo(
        EFloat.FromString("2147483647")) <= 0;
        isTruncated = enumber.ToEInteger().compareTo(
    EInteger.FromString("-2147483648")) >= 0 &&
          enumber.ToEInteger().compareTo(
        EInteger.FromString("2147483647")) <= 0;
        if (isNum) {
   TestCommon.AssertEquals(
  eint,
  EInteger.FromInt32(enumber.ToInt32Checked()));
 TestCommon.AssertEquals(
  eint,
  EInteger.FromInt32(enumber.ToInt32Unchecked()));
          if (isInteger) {
   TestCommon.AssertEquals(
  eint,
  EInteger.FromInt32(enumber.ToInt32IfExact()));
          } else {
            try {
 enumber.ToInt32IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        } else if (isTruncated) {
   TestCommon.AssertEquals(
  eint,
  EInteger.FromInt32(enumber.ToInt32Checked()));
 TestCommon.AssertEquals(
  eint,
  EInteger.FromInt32(enumber.ToInt32Unchecked()));
          try {
 enumber.ToInt32IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
        } else {
          try {
 enumber.ToInt32Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          try {
 enumber.ToInt32Unchecked();
} catch (Exception ex) {
Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          if (isInteger) {
            try {
 enumber.ToInt32IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          } else {
            try {
 enumber.ToInt32IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        }
        isNum = enumber.compareTo(
        EFloat.FromString("-9223372036854775808")) >= 0 && enumber.compareTo(
        EFloat.FromString("9223372036854775807")) <= 0;
        isTruncated = enumber.ToEInteger().compareTo(
        EInteger.FromString("-9223372036854775808")) >= 0 &&
          enumber.ToEInteger().compareTo(
        EInteger.FromString("9223372036854775807")) <= 0;
        if (isNum) {
   TestCommon.AssertEquals(
  eint,
  EInteger.FromInt64(enumber.ToInt64Checked()));
 TestCommon.AssertEquals(
  eint,
  EInteger.FromInt64(enumber.ToInt64Unchecked()));
          if (isInteger) {
   TestCommon.AssertEquals(
  eint,
  EInteger.FromInt64(enumber.ToInt64IfExact()));
          } else {
            try {
 enumber.ToInt64IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        } else if (isTruncated) {
   TestCommon.AssertEquals(
  eint,
  EInteger.FromInt64(enumber.ToInt64Checked()));
 TestCommon.AssertEquals(
  eint,
  EInteger.FromInt64(enumber.ToInt64Unchecked()));
          try {
 enumber.ToInt64IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
        } else {
          try {
 enumber.ToInt64Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          try {
 enumber.ToInt64Unchecked();
} catch (Exception ex) {
Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          if (isInteger) {
            try {
 enumber.ToInt64IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          } else {
            try {
 enumber.ToInt64IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        }
      }
    }

    @Test
    public void TestToDoubleRounding() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 1500; ++i) {
        EFloat efa = this.RandomDoubleEFloat(fr, i >= 250);
        TestToFloatRoundingOne(efa, true);
      }
      TestToFloatRoundingOne(EFloat.Create(0, -1074), true);
      EInteger mant = EInteger.FromRadixString(
        "10000000000000000000000000000000000000000000000000000",
        2);
      {
EFloat objectTemp = EFloat.Create(
  mant,
  EInteger.FromInt32(-1074));
TestToFloatRoundingOne(objectTemp, true);
}
      {
EFloat objectTemp = EFloat.Create(
  EInteger.FromRadixString("-10000000000000000000000000000000000000000000000000000", 2),
  EInteger.FromInt32(-1074));
TestToFloatRoundingOne(objectTemp, true);
}
    }

    @Test
    public void TestToEIntegerIfExact() {
      EFloat flo = EFloat.Create(999, -1);
      try {
        flo.ToEIntegerIfExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestToEngineeringString() {
      // not implemented yet
    }
    @Test
    public void TestToEDecimal() {
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
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        EFloat dec = RandomObjects.RandomEFloat(fr);
        ExtraTest.TestStringEqualRoundTrip(dec);
      }
    }
    @Test
    public void TestUnsignedMantissa() {
      // not implemented yet
    }

    private static void TestAddCloseExponent(RandomGenerator fr, int exp) {
      for (int i = 0; i < 1000; ++i) {
        EInteger exp1 = EInteger.FromInt32(exp)
          .Add(EInteger.FromInt32(fr.UniformInt(32) - 16));
        EInteger exp2 = exp1.Add(
          EInteger.FromInt32(fr.UniformInt(18) - 30));
        EInteger mant1 = EInteger.FromInt32(fr.UniformInt(0x10000000));
        EInteger mant2 = EInteger.FromInt32(fr.UniformInt(0x10000000));
        EFloat decA = EFloat.Create(mant1, exp1);
        EFloat decB = EFloat.Create(mant2, exp2);
        EFloat decC = decA.Add(decB);
        EFloat decD = decC.Subtract(decA);
        TestCommon.CompareTestEqual(decD, decB);
        decD = decC.Subtract(decB);
        TestCommon.CompareTestEqual(decD, decA);
      }
    }

    private static void TestEFloatDoubleCore(double d, String s) {
      double oldd = d;
      EFloat bf = EFloat.FromDouble(d);
      if (s != null) {
        Assert.assertEquals(s, bf.toString());
      }
      d = bf.ToDouble();
      Assert.assertEquals((double)oldd, d, 0);
    }

    private static void TestEFloatSingleCore(float d, String s) {
      float oldd = d;
      EFloat bf = EFloat.FromSingle(d);
      if (s != null) {
        Assert.assertEquals(s, bf.toString());
      }
      d = bf.ToSingle();
      Assert.assertEquals((float)oldd, d, 0f);
    }
  }
