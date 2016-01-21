package com.upokecenter.test;

import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.numbers.*;

  public class ERationalTest {
    @Test
    public void TestConstructor() {
      // not implemented yet
    }
    @Test
    public void TestAbs() {
      // not implemented yet
    }
    @Test
    public void TestAdd() {
      // not implemented yet
    }
    @Test
    public void TestCompareTo() {
      FastRandom r = new FastRandom();
      for (int i = 0; i < 500; ++i) {
        ERational bigintA = RandomObjects.RandomRational(r);
        ERational bigintB = RandomObjects.RandomRational(r);
        ERational bigintC = RandomObjects.RandomRational(r);
        TestCommon.CompareTestRelations(bigintA, bigintB, bigintC);
      }
      TestCommon.CompareTestLess(ERational.Zero, ERational.NaN);
      for (int i = 0; i < 100; ++i) {
        EInteger num = RandomObjects.RandomBigInteger(r);
        if (num.isZero()) {
          // Skip if number is 0; 0/1 and 0/2 are
          // equal in that case
          continue;
        }
        num = num.Abs();
        ERational rat = new ERational(num, EInteger.FromInt32(1));
        ERational rat2 = new ERational(num, EInteger.FromInt64(2));
        TestCommon.CompareTestLess(rat2, rat);
        TestCommon.CompareTestGreater(rat, rat2);
      }
      TestCommon.CompareTestLess(
        new ERational(EInteger.FromInt32(1), EInteger.FromInt64(2)),
        new ERational(EInteger.FromInt64(4), EInteger.FromInt32(1)));
      for (int i = 0; i < 100; ++i) {
        EInteger num = RandomObjects.RandomBigInteger(r);
        EInteger den = RandomObjects.RandomBigInteger(r);
        if (den.isZero()) {
          den = EInteger.FromInt32(1);
        }
        ERational rat = new ERational(num, den);
        for (int j = 0; j < 10; ++j) {
          EInteger num2 = num;
          EInteger den2 = den;
          EInteger mult = RandomObjects.RandomBigInteger(r);
          if (mult.isZero() || mult.equals(EInteger.FromInt32(1))) {
            mult = EInteger.FromInt64(2);
          }
          num2 = num2.Multiply(mult);
          den2 = den2.Multiply(mult);
          ERational rat2 = new ERational(num2, den2);
          TestCommon.CompareTestEqual(rat, rat2);
        }
      }
    }
    @Test
    public void TestCompareToBinary() {
      // not implemented yet
    }
    @Test
    public void TestCompareToDecimal() {
      FastRandom fr = new FastRandom();
      for (int i = 0; i < 100; ++i) {
        ERational er = RandomObjects.RandomRational(fr);
        int exp = -100000 + fr.NextValue(200000);
        EDecimal ed = EDecimal.Create(
          RandomObjects.RandomBigInteger(fr),
          EInteger.FromInt32(exp));
        ERational er2 = ERational.FromEDecimal(ed);
        int c2r = er.compareTo(er2);
        int c2d = er.CompareToDecimal(ed);
        Assert.assertEquals(c2r, c2d);
      }
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
    public void TestDenominator() {
      // not implemented yet
    }
    @Test
    public void TestDivide() {
      FastRandom fr = new FastRandom();
      for (int i = 0; i < 500; ++i) {
        ERational er = RandomObjects.RandomRational(fr);
        ERational er2 = RandomObjects.RandomRational(fr);
        if (er2.isZero() || !er2.isFinite()) {
          continue;
        }
        if (er.isZero() || !er.isFinite()) {
          continue;
        }
        ERational ermult = er.Multiply(er2);
        ERational erdiv = ermult.Divide(er);
        TestCommon.CompareTestEqual(erdiv, er2);
        erdiv = ermult.Divide(er2);
        TestCommon.CompareTestEqual(erdiv, er);
      }
    }
    @Test
    public void TestEquals() {
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
    public void TestFromEDecimal() {
      // not implemented yet
    }
    @Test
    public void TestFromEFloat() {
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
    public void TestGetHashCode() {
      // not implemented yet
    }
    @Test
    public void TestIsFinite() {
      if (ERational.PositiveInfinity.isFinite())Assert.fail();
      if (ERational.NegativeInfinity.isFinite())Assert.fail();
      if (!(ERational.Zero.isFinite()))Assert.fail();
      if (ERational.NaN.isFinite())Assert.fail();
    }
    @Test
    public void TestIsInfinity() {
      if (!(ERational.PositiveInfinity.IsInfinity()))Assert.fail();
      if (!(ERational.NegativeInfinity.IsInfinity()))Assert.fail();
      if (ERational.Zero.IsInfinity())Assert.fail();
      if (ERational.NaN.IsInfinity())Assert.fail();
    }
    @Test
    public void TestIsNaN() {
      if (ERational.PositiveInfinity.IsNaN())Assert.fail();
      if (ERational.NegativeInfinity.IsNaN())Assert.fail();
      if (ERational.Zero.IsNaN())Assert.fail();
      if (ERational.One.IsNaN())Assert.fail();
      if (!(ERational.NaN.IsNaN()))Assert.fail();
    }
    @Test
    public void TestIsNegative() {
      // not implemented yet
    }
    @Test
    public void TestIsNegativeInfinity() {
      if (ERational.PositiveInfinity.IsNegativeInfinity())Assert.fail();
      if (!(ERational.NegativeInfinity.IsNegativeInfinity()))Assert.fail();
      if (ERational.Zero.IsNegativeInfinity())Assert.fail();
      if (ERational.One.IsNegativeInfinity())Assert.fail();
      if (ERational.NaN.IsNegativeInfinity())Assert.fail();
    }
    @Test
    public void TestIsPositiveInfinity() {
      if (!(ERational.PositiveInfinity.IsPositiveInfinity()))Assert.fail();
      if (ERational.NegativeInfinity.IsPositiveInfinity())Assert.fail();
      if (ERational.Zero.IsPositiveInfinity())Assert.fail();
      if (ERational.One.IsPositiveInfinity())Assert.fail();
      if (ERational.NaN.IsPositiveInfinity())Assert.fail();
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
      if (!(ERational.NegativeZero.isZero()))Assert.fail();
      if (!(ERational.Zero.isZero()))Assert.fail();
      if (ERational.One.isZero())Assert.fail();
      if (ERational.NegativeInfinity.isZero())Assert.fail();
      if (ERational.PositiveInfinity.isZero())Assert.fail();
      if (ERational.NaN.isZero())Assert.fail();
      if (ERational.SignalingNaN.isZero())Assert.fail();
    }
    @Test
    public void TestMultiply() {
      // not implemented yet
    }
    @Test
    public void TestNegate() {
      // not implemented yet
    }
    @Test
    public void TestNumerator() {
      // not implemented yet
    }
    @Test
    public void TestRemainder() {
      FastRandom fr = new FastRandom();
      for (int i = 0; i < 100; ++i) {
        ERational er;
        ERational er2;
        er = new ERational(
          RandomObjects.RandomBigInteger(fr),
          EInteger.FromInt32(1));
        er2 = new ERational(
          RandomObjects.RandomBigInteger(fr),
          EInteger.FromInt32(1));
        if (er2.isZero() || !er2.isFinite()) {
          continue;
        }
        if (er.isZero() || !er.isFinite()) {
          // Code below will divide by "er",
          // so skip if "er" is zero
          continue;
        }
        ERational ermult = er.Multiply(er2);
        ERational erdiv = ermult.Divide(er);
        erdiv = ermult.Remainder(er);
        if (!erdiv.isZero()) {
          Assert.fail(ermult + "; " + er);
        }
        erdiv = ermult.Remainder(er2);
        if (!erdiv.isZero()) {
          Assert.fail(er + "; " + er2);
        }
      }
    }
    @Test
    public void TestSign() {
      Assert.assertEquals(0, ERational.NegativeZero.signum());
      Assert.assertEquals(0, ERational.Zero.signum());
      Assert.assertEquals(1, ERational.One.signum());
      Assert.assertEquals(-1, ERational.NegativeInfinity.signum());
      Assert.assertEquals(1, ERational.PositiveInfinity.signum());
    }
    @Test
    public void TestSubtract() {
      // not implemented yet
    }
    @Test
    public void TestToEInteger() {
      try {
        ERational.PositiveInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.NegativeInfinity.ToEInteger();
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
      try {
        ERational.PositiveInfinity.ToEIntegerExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.NegativeInfinity.ToEIntegerExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.NaN.ToEIntegerExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.SignalingNaN.ToEIntegerExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestToDouble() {
      // test for correct rounding
      double dbl;
      dbl = ERational.FromEDecimal(
        EDecimal.FromString(
       "1.972579273363468721491642554610734805464744567871093749999999999999"))
        .ToDouble();
      {
String stringTemp = EFloat.FromDouble(dbl).ToPlainString();
Assert.assertEquals(
"1.9725792733634686104693400920950807631015777587890625",
stringTemp);
}
    }
    @Test
    public void TestToEDecimal() {
      // not implemented yet
    }
    @Test
    public void TestToEDecimalExactIfPossible() {
      // not implemented yet
    }
    @Test
    public void TestToEFloat() {
      // not implemented yet
    }
    @Test
    public void TestToEFloatExactIfPossible() {
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
    public void TestUnsignedNumerator() {
      // not implemented yet
    }
  }
