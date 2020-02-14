package com.upokecenter.test;

import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.util.*;
import com.upokecenter.numbers.*;

  public class ERationalTest {
    @Test
    public void TestFromBoolean() {
      Assert.assertEquals(ERational.Zero, ERational.FromBoolean(false));
      Assert.assertEquals(ERational.One, ERational.FromBoolean(true));
    }

    @Test
    public void TestIsInteger() {
      ERational ed = ERational.NaN;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = ERational.SignalingNaN;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = ERational.PositiveInfinity;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = ERational.NegativeInfinity;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = ERational.NegativeZero;
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = ERational.FromInt32(0);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = ERational.FromInt32(999);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = ERational.Create(1, 1);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = ERational.Create(4, 3);
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = ERational.Create(1998, 999);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
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
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.FromString("-2/");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.FromString("-2/x");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.FromString("-2/2x");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
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
        String erstr = ei1 + "/" + ei2;
        er = ERational.FromString(erstr);
        Assert.assertEquals(ei1, er.getNumerator());
        Assert.assertEquals(ei2, er.getDenominator());
        ERational er2 = ERational.FromString("xyzxyz" + erstr, 6, erstr.length());
        Assert.assertEquals(er, er2);
        er2 = ERational.FromString(erstr + "xyzxyz", 0, erstr.length());
        Assert.assertEquals(er, er2);
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
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.CreateNaN(EInteger.FromString("-1"));
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.CreateNaN(null, false, false);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
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
        isInteger = enumber.IsInteger();
        try {
        eint = enumber.ToSizedEInteger(128);
} catch (ArithmeticException ex) {
        eint = null;
}
        isNum = enumber.compareTo(
            ERational.FromString("0")) >= 0 && enumber.compareTo(
            ERational.FromString("255")) <= 0;
        isTruncated = eint != null && eint.compareTo(
            EInteger.FromString("0")) >= 0 && eint.compareTo(
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
            ERational.FromString("-32768")) >= 0 && enumber.compareTo(
            ERational.FromString("32767")) <= 0;
        isTruncated = eint != null && eint.compareTo(
            EInteger.FromString("-32768")) >= 0 && eint.compareTo(
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
            ERational.FromString("-2147483648")) >= 0 && enumber.compareTo(
            ERational.FromString("2147483647")) <= 0;
        isTruncated = eint != null && eint.compareTo(
            EInteger.FromString("-2147483648")) >= 0 &&
          eint.compareTo(
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
            ERational.FromString("-9223372036854775808")) >= 0 &&
enumber.compareTo(
            ERational.FromString("9223372036854775807")) <= 0;
        isTruncated = eint != null && eint.compareTo(
            EInteger.FromString("-9223372036854775808")) >= 0 &&
          eint.compareTo(
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
    public void TestFromEDecimal() {
      // not implemented yet
    }
    @Test
    public void TestFromEFloat() {
      // not implemented yet
    }
    @Test
    public void TestFromInt32() {
      RandomGenerator rg = new RandomGenerator();
      for (int i = 0; i < 100000; ++i) {
        int x = ((int)rg.UniformLong(0x100000000L));
        ERational er = ERational.FromInt32(x);
        Assert.assertEquals(x, er.ToInt32Checked());
      }
    }
    @Test
    public void TestFromInt64() {
      RandomGenerator rg = new RandomGenerator();
      for (int i = 0; i < 100000; ++i) {
        long lx = rg.UniformLong(0x100000000L);
        lx |= rg.UniformLong(0x100000000L) << 32;
        ERational er = ERational.FromInt64(lx);
        Assert.assertEquals(lx, er.ToInt64Checked());
      }
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
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.NegativeInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
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
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.NegativeInfinity.ToEIntegerIfExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.NaN.ToEIntegerIfExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        ERational.SignalingNaN.ToEIntegerIfExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
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
        EInteger

        einu =

          EInteger.FromString(
  "28639340913494881747377623206916746061582275669009584057200005895877190855460806265049976994790559314239504196566073076418122066663047843214093304712615303281606738109958930749038205003536660505770843083520929789398041971643780448348854216110572110080");
        EInteger

        eide =

          EInteger.FromString(
  "28750776350586315416568963769926903140467288646619110700090608062349507971216834400265907833250535439103972717016825766140425093348203742692910817677857142136154724843978828736062963555441482696667414841094248950779904985367206060177858687316906737921");
        ERational er = ERational.Create(einu, eide);
        if (er.ToDouble() != 0.9961240894599648) {
          {
            Assert.fail();
          }
        }
      }
      {
        EInteger einu = EInteger.FromString(
  "-1863418246957279563806778202220");
        EInteger eide = EInteger.FromString("-19678578840082347944784");
        ERational er = ERational.Create(einu, eide);
        if (er.ToDouble() != 94692724.61697146) {
          {
            Assert.fail();
          }
        }
      }
      {
        EInteger

        einu =

          EInteger.FromString(
  "4076519016091659614123967810815948021971418179181448227348179156907369222635865528247925962675975662292573232522040994064053716056779056877772145762157133017600277977993857255012481237909154562191956133361815939189188765582591976752644639816832074923917533202412261831136948488327238696366401393143430483853862409206072926270636634708427658004324025893452084392156171331315357778321");
        EInteger

        eide =

          EInteger.FromString(
  "30053171944928067073701295610305615550528475313651762605747995928280873504054555524482770637551229352053833204040176508017905604613275269326712841866496543443259054610192947054482094543617680125959791304338751588066392890768300263948395753895509338420186025123267827553750266011369180308262209872640035926912057521546563701774608660809446767698681096126508440107804769919833423");
        ERational er = ERational.Create(einu, eide);
        if (er.ToDouble() != 135643.55281904392) {
          {
            Assert.fail();
          }
        }
      }
      {
        EInteger

        einu =

          EInteger.FromString(
  "1511403407420566177125879751705174919961219403144875930457162257120122722655272014115654193161813303942680725228408688472463207815423286072464839669159790171090839166001298483052378069059586709468050527515714238705406557413088901766470376391793675568489517242957391369009520009135802104064744141393630251196494183173933166764391847068150447850873208820565708392740266030241436739479973556746967849370216658393469376169153719322380201757382492433970778193868859813905257907129");
        EInteger

        eide =

          EInteger.FromString(
  "33280326872418556663964295560581962459372663194753243937711700640769847281688678989080888395655255968809464944241709740235229430440220358888136580115183984148426321300076779067929256845460960374412025399228418151048867881803151188654740400911769029260101299010852510124394657719210512557219056770240433703773466050649165934947232109701091931316375025457884094601369099040539662925180178629168456052854383961780798426614401751942635734338570592693792402770422285391837522895");
        ERational er = ERational.Create(einu, eide);
        if (er.ToDouble() != 45.414319793630355) {
          {
            Assert.fail();
          }
        }
      }
      {
        EInteger

        einu =

          EInteger.FromString(
  "4580262652347057904595823189538330092141446914478260804973196814510627291391646213418768842639566381528657620330601723441056894595162972991287891715357876526586619905423313671685784154629275905");
        EInteger

        eide =

          EInteger.FromString(
  "69618385419980747871182638997636742424400675004554161998565839287053219405482277361151764990053828086980504492613723589203570770730126106876374983897020703784448042222113563586500409426177");
        ERational er = ERational.Create(einu, eide);
        if (er.ToDouble() != 65790.9922029376) {
          {
            Assert.fail();
          }
        }
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
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        ERational er = RandomObjects.RandomERational(r);
        if (!er.isFinite()) {
          continue;
        }
        EFloat d1 = er.ToEFloat(EContext.Binary64);
        EFloat d2 = EFloat.FromDouble(er.ToDouble());
        EFloat f1 = er.ToEFloat(EContext.Binary32);
        EFloat f2 = EFloat.FromSingle(er.ToSingle());
        TestCommon.CompareTestEqual(d1, d2);
        TestCommon.CompareTestEqual(f1, f2);
      }
    }
    @Test
    public void TestToEFloatExactIfPossible() {
      // not implemented yet
    }
    @Test
    public void TestToSingle() {
      {
        EInteger

        einu =

          EInteger.FromString(
  "23834066907087289577452688867414558445296281531936833295325883");
        EInteger

        eide =

          EInteger.FromString(
  "15534539262499368906407431994248182374430712739667361984187384");
        ERational er = ERational.Create(einu, eide);
        if (er.ToSingle() != 1.5342629f) {
          {
            Assert.fail();
          }
        }
      }
      {
        EInteger

        einu =

          EInteger.FromString(
  "810960135060981281503785965440835175294668141298803803547460989698362274666196457668871945147843026811474939398558048345508824437472113293479753442354751587129172572644207111322");
        EInteger

        eide =

          EInteger.FromString(
  "8636069838601268376160366035676284618591173290506370638651495048265433535122887503311121713113210756334318346499012492613933574362769457163247893183618197887269994447582406");
        ERational er = ERational.Create(einu, eide);
        if (er.ToSingle() != 93903.84f) {
          {
            Assert.fail();
          }
        }
      }
      {
        EInteger

        einu =

          EInteger.FromString(
  "5304526364574890840560010646982995091147049745335891793328971638083398372776881020737385269285313962480456970799876432850800217288754622531287312088627094716459388677110875589763876309767154811493713587548266302118702128140713847943813220444000149558451858486434548952528100987325483489103846117169127396143492784269717859375931838548228272458956269164541265674384274049925838897733393726865294654347110528232587161070050551911191748921823363718374235029025652161411749021539767638145888023826187998904792758711679426963655298710930379286566380412719951081442481912752606226825579282652244615176165948488913501881052236119601485041912755870872291382328650046941679756132191795266754755578717998060314999543392794552266017785845826689135711637934620440630411541570941623091339471820334399642470147289788247670537949185807565953741481260969262003821713854473800162526891947858738032889342185379914314601837168230401");
        EInteger

        eide =

          EInteger.FromString(
  "314944177666693062435667970118713788971364943249047803033421531625479805665887974826695957312704616135607768830461843040320915214498924878776098486460843170386743429190309213382409059276699175578131202848976860562103269385200067933278516994110948793966397273274561095278059552813837263246667902979802373764423242051621203673046302543396458170587180086168513487269072909436023623672832774473410447374166572602450025829757565722206528831737193693325366641789779988433920066038250246217122466831930984488488376897602569328870735242483153382823298770851533692901499103405933839095073558933874289906954773981166730182545945874420104943295006654606336460426487664749887149501004529776539788539790138421221891979725992354021449344162561338919161788820854058414168969170524268352719859157485395478948491097625295616529576147634914836729434828401979252334062837921285033926991403641472757182599676735173003424366592");
        ERational er = ERational.Create(einu, eide);
        if (er.ToSingle() != 16842752f) {
          {
            Assert.fail();
          }
        }
      }
      {
        EInteger

        einu =

          EInteger.FromString(
  "168454965896319523152946466297868353788653442478333054897266888365632816442459689504094784593003407052257838378350744863976911395447324682043201505802121428230532833801807385919217182917393169331131995399091595180147022248432736460962334512885449756328448038869567052211293995632271554966884235831399330758923823395757016394763663201462003600674301217377617929773574310286374410528841766603120042822775091737577375650070257097303426970958931891955210916312730765759232076578462254848494022412247939404096486657127755150795307882930843742739174773866939575412134790332414243733494025191252381312785597995676545526902109795428884488851686432945817518336");
        EInteger

        eide =

          EInteger.FromString(
  "2560456230470432125200127153853942947485331410297046485788363092693254893087674632081669431470943997861211823420280664260103704624833204181335525884445882353789910968078548977139510394225920153455036593956326287174665172052834856496139342329119913746617837115501730185897841673334296252407373535096380965640461701665193207709216689124054790466405686605794082677881554249918980929471157629684436875025303423239994182485173796649723584432392828090501741627156271148442708482961790840876549797457520029557145971886099230468571902675973580300751282021139511406516147542519057080067147156973393016707491665406989223376330081288167244479360682292215809");
        ERational er = ERational.Create(einu, eide);
        if (er.ToSingle() != 65791f) {
          {
            Assert.fail();
          }
        }
      }
      {
        EInteger

        einu =

          EInteger.FromString(
  "39215356267123987877590614038169329950553104245363134728114569544843954260579650482575583976655068396538280989359785620466615335637045961249410659720345372716260870883325919767127626475170545409329645945753389295843602596860604992106021861399768921364546233412395939496771385771383869099412088284642560566826732715440587598");
        EInteger

        eide =

          EInteger.FromString(
  "197658450495600297791407167158211849551820826959269938392947091731597383541098268667698802825252860974797603152693843742377064013804040323183252205162473858164029925399345656025573202708820392148271377531828338407588400392386194780662447572903426531525249490616839268873381954327942865262568823614759604691492427712757761");
        ERational er = ERational.Create(einu, eide);
        if (er.ToSingle() != 198.3996f) {
          {
            Assert.fail();
          }
        }
      }
      {
        EInteger einu =

          EInteger.FromString(
  "33620019770132064809388582444129948496804380624411337037741591106921198969319324627755112665377985681341222156990477708342458797569196901053539583732989861779369670007473985748950200087020644946651543133826715546097533673357308064127891751938493433688838204103947049712543973619025750670122616130986602980873564884488164793785787649182716110517599627486610078794136915827420976012053966156062599595550605945928088023407254446524465114189515612640212647778866075171040");
        EInteger eide =

          EInteger.FromString(
  "158579284607272054922773928807628666902047297595422926144611795958095987598633399676025657575059038678936609990027811890420475787566908597322290360998641421470992025696257435914482834743261657527026076990152952352171750535736399543734431588072628090736458014184486964107242626009879708680248285874462867450882574166373301335715689876844651794365682567590998345126417475872346384010498222631664194947596592168457779263318778528347614689435667087177685538089116102623488");
        ERational er = ERational.Create(einu, eide);
        if (er.ToSingle() != 0.21200764f) {
          Assert.fail();
        }
      }
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
