package com.upokecenter.test;

import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.util.*;
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
    public void TestFromString() {
      ERational er;
      er = ERational.FromString("-2/4");
      Assert.assertEquals(EInteger.FromInt32(-2), er.getNumerator());
      Assert.assertEquals(EInteger.FromInt32(4), er.getDenominator());
      er = ERational.FromString("2/4");
      Assert.assertEquals(EInteger.FromInt32(2), er.getNumerator());
      Assert.assertEquals(EInteger.FromInt32(4), er.getDenominator());
      er = ERational.FromString("293939393939/4");
      Assert.assertEquals(EInteger.FromString("293939393939"), er.getNumerator());
      Assert.assertEquals(EInteger.FromInt32(4), er.getDenominator());
      er = ERational.FromString("-293939393939/4");
      Assert.assertEquals(EInteger.FromString("-293939393939"), er.getNumerator());
      Assert.assertEquals(EInteger.FromInt32(4), er.getDenominator());
      er = ERational.FromString("-2/293939393939");
      Assert.assertEquals(EInteger.FromInt32(-2), er.getNumerator());
      Assert.assertEquals(EInteger.FromString("293939393939"), er.getDenominator());
      er = ERational.FromString("-2");
      Assert.assertEquals(EInteger.FromString("-2"), er.getNumerator());
      Assert.assertEquals(EInteger.FromInt32(1), er.getDenominator());
      er = ERational.FromString("2");
      Assert.assertEquals(EInteger.FromString("2"), er.getNumerator());
      Assert.assertEquals(EInteger.FromInt32(1), er.getDenominator());
      try {
 ERational.FromString("-2x");
Assert.fail("Should have failed");
} catch (NumberFormatException ex) {
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      try {
 ERational.FromString("-2/");
Assert.fail("Should have failed");
} catch (NumberFormatException ex) {
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      try {
 ERational.FromString("-2/x");
Assert.fail("Should have failed");
} catch (NumberFormatException ex) {
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      try {
 ERational.FromString("-2/2x");
Assert.fail("Should have failed");
} catch (NumberFormatException ex) {
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        EInteger ei1 = RandomObjects.RandomEInteger(fr);
        EInteger ei2 = RandomObjects.RandomEInteger(fr).Abs();
        if (ei2.isZero()) {
          ei2 = EInteger.FromInt32(1);
        }
        er = ERational.FromString(ei1 + "/" + ei2);
        Assert.assertEquals(ei1, er.getNumerator());
        Assert.assertEquals(ei2, er.getDenominator());
      }
    }
    @Test
    public void TestAdd() {
      // not implemented yet
    }
    @Test
    public void TestCompareTo() {
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        ERational bigintA = RandomObjects.RandomERational(r);
        ERational bigintB = RandomObjects.RandomERational(r);
        ERational bigintC = RandomObjects.RandomERational(r);
        TestCommon.CompareTestRelations(bigintA, bigintB, bigintC);
      }
      TestCommon.CompareTestLess(ERational.Zero, ERational.NaN);
      ERational rat, rat2;
      for (int i = 0; i < 100; ++i) {
        EInteger num = RandomObjects.RandomEInteger(r);
        if (num.isZero()) {
          // Skip if number is 0; 0/1 and 0/2 are
          // equal in that case
          continue;
        }
        num = num.Abs();
        rat = ERational.Create(num, EInteger.FromInt32(1));
        rat2 = ERational.Create(num, EInteger.FromInt64(2));
        TestCommon.CompareTestLess(rat2, rat);
        TestCommon.CompareTestGreater(rat, rat2);
      }
      TestCommon.CompareTestLess(
        ERational.Create(EInteger.FromInt32(1), EInteger.FromInt64(2)),
        ERational.Create(EInteger.FromInt64(4), EInteger.FromInt32(1)));
      for (int i = 0; i < 100; ++i) {
        EInteger num = RandomObjects.RandomEInteger(r);
        EInteger den = RandomObjects.RandomEInteger(r);
        if (den.isZero()) {
          den = EInteger.FromInt32(1);
        }
        rat = ERational.Create(num, den);
        for (int j = 0; j < 10; ++j) {
          EInteger num2 = num;
          EInteger den2 = den;
          EInteger mult = RandomObjects.RandomEInteger(r);
          if (mult.isZero() || mult.equals(EInteger.FromInt32(1))) {
            mult = EInteger.FromInt64(2);
          }
          num2 = num2.Multiply(mult);
          den2 = den2.Multiply(mult);
          rat2 = ERational.Create(num2, den2);
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
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 100; ++i) {
        ERational er = RandomObjects.RandomERational(fr);
        int exp = -100000 + fr.UniformInt(200000);
        EDecimal ed = EDecimal.Create(
          RandomObjects.RandomEInteger(fr),
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
      try {
 ERational.CreateNaN(null);
Assert.fail("Should have failed");
} catch (NullPointerException ex) {
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      try {
 ERational.CreateNaN(EInteger.FromString("-1"));
Assert.fail("Should have failed");
} catch (IllegalArgumentException ex) {
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      try {
 ERational.CreateNaN(null, false, false);
Assert.fail("Should have failed");
} catch (NullPointerException ex) {
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      ERational ef = ERational.CreateNaN(EInteger.FromInt32(0), false, true);
      if (!(ef.isNegative())) {
 Assert.fail();
 }
      ef = ERational.CreateNaN(EInteger.FromInt32(0), false, false);
      if (!(!ef.isNegative())) {
 Assert.fail();
 }
    }
    @Test
    public void TestDenominator() {
      // not implemented yet
    }
    @Test
    public void TestDivide() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        ERational er = RandomObjects.RandomERational(fr);
        ERational er2 = RandomObjects.RandomERational(fr);
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
      ERational era = ERational.FromString("0/3920");
      ERational erb = ERational.FromString("0/3920");
      TestCommon.CompareTestEqualAndConsistent(
        era,
        erb);
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
    public void TestConversions() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 20000; ++i) {
        boolean isNum, isTruncated, isInteger;
        EInteger eint;
        ERational enumber = RandomObjects.RandomERational(fr);
        if (!enumber.isFinite()) {
          try {
 enumber.ToByteChecked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          try {
 enumber.ToInt16Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          try {
 enumber.ToInt32Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          try {
 enumber.ToInt64Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          continue;
        }
        ERational enumberInteger = ERational.FromEInteger(enumber.ToEInteger());
        isInteger = enumberInteger.compareTo(enumber) == 0;
        eint = enumber.ToEInteger();
        isNum = enumber.compareTo(
        ERational.FromString("0")) >= 0 && enumber.compareTo(
        ERational.FromString("255")) <= 0;
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
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
        } else {
          try {
 enumber.ToByteChecked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          } else {
            try {
 enumber.ToByteIfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        }
        isNum = enumber.compareTo(
        ERational.FromString("-32768")) >= 0 && enumber.compareTo(
        ERational.FromString("32767")) <= 0;
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
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
        } else {
          try {
 enumber.ToInt16Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          } else {
            try {
 enumber.ToInt16IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        }
        isNum = enumber.compareTo(
        ERational.FromString("-2147483648")) >= 0 && enumber.compareTo(
        ERational.FromString("2147483647")) <= 0;
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
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
        } else {
          try {
 enumber.ToInt32Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          } else {
            try {
 enumber.ToInt32IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        }
        isNum = enumber.compareTo(
        ERational.FromString("-9223372036854775808")) >= 0 && enumber.compareTo(
        ERational.FromString("9223372036854775807")) <= 0;
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
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
        } else {
          try {
 enumber.ToInt64Checked();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
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
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          } else {
            try {
 enumber.ToInt64IfExact();
Assert.fail("Should have failed");
} catch (ArithmeticException ex) {
new Object();
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
          }
        }
      }
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
      if (ERational.PositiveInfinity.isFinite()) {
 Assert.fail();
 }
      if (ERational.NegativeInfinity.isFinite()) {
 Assert.fail();
 }
      if (!(ERational.Zero.isFinite())) {
 Assert.fail();
 }
      if (ERational.NaN.isFinite()) {
 Assert.fail();
 }
    }
    @Test
    public void TestIsInfinity() {
      if (!(ERational.PositiveInfinity.IsInfinity())) {
 Assert.fail();
 }
      if (!(ERational.NegativeInfinity.IsInfinity())) {
 Assert.fail();
 }
      if (ERational.Zero.IsInfinity()) {
 Assert.fail();
 }
      if (ERational.NaN.IsInfinity()) {
 Assert.fail();
 }
    }
    @Test
    public void TestIsNaN() {
      if (ERational.PositiveInfinity.IsNaN()) {
 Assert.fail();
 }
      if (ERational.NegativeInfinity.IsNaN()) {
 Assert.fail();
 }
      if (ERational.Zero.IsNaN()) {
 Assert.fail();
 }
      if (ERational.One.IsNaN()) {
 Assert.fail();
 }
      if (!(ERational.NaN.IsNaN())) {
 Assert.fail();
 }
    }
    @Test
    public void TestIsNegative() {
      // not implemented yet
    }
    @Test
    public void TestIsNegativeInfinity() {
      if (ERational.PositiveInfinity.IsNegativeInfinity()) {
 Assert.fail();
 }
      if (!(ERational.NegativeInfinity.IsNegativeInfinity())) {
 Assert.fail();
 }
      if (ERational.Zero.IsNegativeInfinity()) {
 Assert.fail();
 }
      if (ERational.One.IsNegativeInfinity()) {
 Assert.fail();
 }
      if (ERational.NaN.IsNegativeInfinity()) {
 Assert.fail();
 }
    }
    @Test
    public void TestIsPositiveInfinity() {
      if (!(ERational.PositiveInfinity.IsPositiveInfinity())) {
 Assert.fail();
 }
      if (ERational.NegativeInfinity.IsPositiveInfinity()) {
 Assert.fail();
 }
      if (ERational.Zero.IsPositiveInfinity()) {
 Assert.fail();
 }
      if (ERational.One.IsPositiveInfinity()) {
 Assert.fail();
 }
      if (ERational.NaN.IsPositiveInfinity()) {
 Assert.fail();
 }
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
      if (!(ERational.NegativeZero.isZero())) {
 Assert.fail();
 }
      if (!(ERational.Zero.isZero())) {
 Assert.fail();
 }
      if (ERational.One.isZero()) {
 Assert.fail();
 }
      if (ERational.NegativeInfinity.isZero()) {
 Assert.fail();
 }
      if (ERational.PositiveInfinity.isZero()) {
 Assert.fail();
 }
      if (ERational.NaN.isZero()) {
 Assert.fail();
 }
      if (ERational.SignalingNaN.isZero()) {
 Assert.fail();
 }
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
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 100; ++i) {
        ERational er;
        ERational er2;
                er = ERational.Create(
          RandomObjects.RandomEInteger(fr),
          EInteger.FromInt32(1));
                er2 = ERational.Create(
          RandomObjects.RandomEInteger(fr),
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
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.NegativeInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestToEIntegerIfExact() {
      try {
        ERational.PositiveInfinity.ToEIntegerIfExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.NegativeInfinity.ToEIntegerIfExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.NaN.ToEIntegerIfExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.SignalingNaN.ToEIntegerIfExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        new Object();
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
      RandomGenerator fr = new RandomGenerator();
      ERational dec;
       for (int i = 0; i < 1000; ++i) {
        dec = RandomObjects.RandomERational(fr);
        ExtraTest.TestStringEqualRoundTrip(dec);
      }
      dec = ERational.FromString("-0/500");
      ExtraTest.TestStringEqualRoundTrip(dec);
    }
    @Test
    public void TestUnsignedNumerator() {
      // not implemented yet
    }
  }
