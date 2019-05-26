package com.upokecenter.test;

import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.util.*;
import com.upokecenter.numbers.*;

  public class EDecimalTest {
    private static final String[] ValueTestStrings = { "1.265e-4",
      "0.0001265", "0.0001265", "0.0001265", "0.000E-1", "0.0000",
      "0.0000", "0.0000", "0.0000000000000e-3", "0E-16", "0.0E-15",
      "0.0000000000000000", "0.000000000e+1", "0E-8", "0.00E-6",
      "0.00000000", "0.000000000000000e+12", "0.000", "0.000", "0.000",
      "0.00000000000000e-11", "0E-25", "0.0E-24",
      "0.0000000000000000000000000", "0.000000000000e+5", "0E-7",
      "0.0E-6", "0.0000000", "0.0000e-4", "0E-8", "0.00E-6",
      "0.00000000", "0.000000e+2", "0.0000", "0.0000", "0.0000",
      "0.0e+3", "0E+2", "0.0E+3", "0", "0.000000000000000e+8", "0E-7",
      "0.0E-6", "0.0000000", "0.000e+10", "0E+7", "0.00E+9", "0",
      "0.0000000000000000000e-12", "0E-31", "0.0E-30",
      "0.0000000000000000000000000000000", "0.0000e-1", "0.00000",
      "0.00000", "0.00000", "0.00000000000e-11", "0E-22", "0.0E-21",
      "0.0000000000000000000000", "0.00000000000e-17", "0E-28", "0.0E-27",
  "0.0000000000000000000000000000", "0.00000000000000e+9", "0.00000",
  "0.00000", "0.00000", "0.0000000000e-18", "0E-28", "0.0E-27",
      "0.0000000000000000000000000000", "0.0e-13", "0E-14", "0.00E-12",
      "0.00000000000000", "0.000000000000000000e+10", "0E-8", "0.00E-6",
      "0.00000000", "0.0000e+19", "0E+15", "0E+15", "0", "0.00000e-8",
      "0E-13", "0.0E-12", "0.0000000000000", "0.00000000000e+14", "0E+3",
  "0E+3", "0", "0.000e-14", "0E-17", "0.00E-15",
      "0.00000000000000000", "0.000000e-19", "0E-25", "0.0E-24",
      "0.0000000000000000000000000", "0.000000000000e+19", "0E+7",
      "0.00E+9", "0", "0.0000000000000e+18", "0E+5", "0.0E+6", "0",
      "0.00000000000000e-2", "0E-16", "0.0E-15", "0.0000000000000000",
      "0.0000000000000e-18", "0E-31", "0.0E-30",
      "0.0000000000000000000000000000000", "0e-17", "0E-17", "0.00E-15",
      "0.00000000000000000", "0e+17", "0E+17", "0.0E+18", "0",
      "0.00000000000000000e+0", "0E-17", "0.00E-15",
      "0.00000000000000000", "0.0000000000000e+0", "0E-13", "0.0E-12",
      "0.0000000000000", "0.0000000000000000000e-12", "0E-31", "0.0E-30",
  "0.0000000000000000000000000000000", "0.0000000000000000000e+10",
      "0E-9", "0E-9", "0.000000000", "0.00000e-2", "0E-7", "0.0E-6",
      "0.0000000", "0.000000e+15", "0E+9", "0E+9", "0",
      "0.000000000e-10", "0E-19", "0.0E-18", "0.0000000000000000000",
      "0.00000000000000e+6", "0E-8", "0.00E-6", "0.00000000",
      "0.00000e+17", "0E+12", "0E+12", "0", "0.000000000000000000e-0",
      "0E-18", "0E-18", "0.000000000000000000", "0.0000000000000000e+11",
  "0.00000", "0.00000", "0.00000", "0.000000000000e+15", "0E+3",
      "0E+3", "0", "0.00000000e-19", "0E-27", "0E-27",
      "0.000000000000000000000000000", "0.00000e-6", "0E-11", "0.00E-9",
      "0.00000000000", "0e-14", "0E-14", "0.00E-12", "0.00000000000000",
  "0.000000000e+9", "0", "0", "0", "0.00000e+13", "0E+8",
      "0.0E+9", "0", "0.000e-0", "0.000", "0.000", "0.000",
      "0.000000000000000e+6", "0E-9", "0E-9", "0.000000000",
      "0.000000000e+17", "0E+8", "0.0E+9", "0", "0.00000000000e+6",
      "0.00000", "0.00000", "0.00000", "0.00000000000000e+3", "0E-11",
      "0.00E-9", "0.00000000000", "0e+0", "0", "0", "0", "0.000e+12",
      "0E+9", "0E+9", "0", "0.00000000000e+9", "0.00", "0.00", "0.00",
  "0.00000000000000e-9", "0E-23", "0.00E-21",
      "0.00000000000000000000000", "0e-1", "0.0", "0.0", "0.0",
      "0.0000e-13", "0E-17", "0.00E-15", "0.00000000000000000",
      "0.00000000000e-7", "0E-18", "0E-18", "0.000000000000000000",
      "0.00000000000000e+4", "0E-10", "0.0E-9", "0.0000000000",
      "0.00000000e-8", "0E-16", "0.0E-15", "0.0000000000000000",
      "0.00e-6", "0E-8", "0.00E-6", "0.00000000", "0.0e-1", "0.00",
      "0.00", "0.00", "0.0000000000000000e-10", "0E-26", "0.00E-24",
      "0.00000000000000000000000000", "0.00e+14", "0E+12", "0E+12", "0",
  "0.000000000000000000e+5", "0E-13", "0.0E-12", "0.0000000000000",
  "0.0e+7", "0E+6", "0E+6", "0", "0.00000000e+8", "0", "0", "0",
  "0.000000000e+0", "0E-9", "0E-9", "0.000000000", "0.000e+13",
      "0E+10", "0.00E+12", "0", "0.0000000000000000e+16", "0", "0",
      "0", "0.00000000e-1", "0E-9", "0E-9", "0.000000000",
      "0.00000000000e-15", "0E-26", "0.00E-24",
      "0.00000000000000000000000000", "0.0e+11", "0E+10", "0.00E+12",
      "0", "0.00000e+7", "0E+2", "0.0E+3", "0",
      "0.0000000000000000000e-19", "0E-38", "0.00E-36",
      "0.00000000000000000000000000000000000000", "0.0000000000e-6",
      "0E-16", "0.0E-15", "0.0000000000000000", "0.00000000000000000e-15",
  "0E-32", "0.00E-30", "0.00000000000000000000000000000000",
      "0.000000000000000e+2", "0E-13", "0.0E-12", "0.0000000000000",
      "0.0e-18", "0E-19", "0.0E-18", "0.0000000000000000000",
      "0.00000000000000e-6", "0E-20", "0.00E-18",
      "0.00000000000000000000", "0.000e-17", "0E-20", "0.00E-18",
      "0.00000000000000000000", "0.00000000000000e-7", "0E-21", "0E-21",
      "0.000000000000000000000", "0.000000e-9", "0E-15", "0E-15",
      "0.000000000000000", "0e-11", "0E-11", "0.00E-9", "0.00000000000",
  "0.000000000e+11", "0E+2", "0.0E+3", "0",
      "0.0000000000000000e+15", "0.0", "0.0", "0.0",
      "0.0000000000000000e+10", "0.000000", "0.000000", "0.000000",
      "0.000000000e+4", "0.00000", "0.00000", "0.00000",
      "0.000000000000000e-13", "0E-28", "0.0E-27",
      "0.0000000000000000000000000000", "0.0000000000000000000e-8",
      "0E-27", "0E-27", "0.000000000000000000000000000",
      "0.00000000000e-15", "0E-26", "0.00E-24",
      "0.00000000000000000000000000", "0.00e+12", "0E+10", "0.00E+12",
      "0", "0.0e+5", "0E+4", "0.00E+6", "0", "0.0000000000000000e+7",
      "0E-9", "0E-9", "0.000000000", "0.0000000000000000e-0", "0E-16",
      "0.0E-15", "0.0000000000000000", "0.000000000000000e+13", "0.00",
      "0.00", "0.00", "0.00000000000e-13", "0E-24", "0E-24",
      "0.000000000000000000000000", "0.000e-10", "0E-13", "0.0E-12",
      "0.0000000000000" };
    @Test
    public void TestAbs() {
      // not implemented yet
    }

    private static void TestAddCloseExponent(RandomGenerator fr, int exp) {
      for (int i = 0; i < 1000; ++i) {
        EInteger exp1 = EInteger.FromInt32(exp)
          .Add(EInteger.FromInt32(fr.UniformInt(32) - 16));
        EInteger exp2 = exp1.Add(EInteger.FromInt32(fr.UniformInt(18) - 9));
        EInteger mant1 = RandomObjects.RandomEInteger(fr);
        EInteger mant2 = RandomObjects.RandomEInteger(fr);
        EDecimal decA = EDecimal.Create(mant1, exp1);
        EDecimal decB = EDecimal.Create(mant2, exp2);
        EDecimal decC = decA.Add(decB);
        EDecimal decD = decC.Subtract(decA);
        TestCommon.CompareTestEqual(decD, decB);
        decD = decC.Subtract(decB);
        TestCommon.CompareTestEqual(decD, decA);
      }
    }

    @Test
    public void TestPrecisionOneHalfEven() {
      EDecimal edec = EDecimal.FromString("9.5e-1");
      EContext ectx = EContext.ForPrecisionAndRounding(1, ERounding.HalfEven);
      edec = edec.RoundToPrecision(ectx);
      TestCommon.CompareTestEqual(
        EDecimal.FromString("10.0e-1"),
        edec);
    }

    @Test
    public void TestAdd() {
      try {
        EDecimal.Zero.Add(null, EContext.Unlimited);
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
      AssertAddSubtract("617862143", "1528127703");
    }

    @Test
    public void TestAddThenCompare() {
      EDecimal a = EDecimal.FromString(
  "3432401181884624580219161996277760227145481682978308767347063168426989874100957186809774969532587926005597200790737572030389681269702414428117526594285731840");
      a = a.Add(
        EDecimal.FromString("18895577316172922617856"));
      EDecimal b = EDecimal.FromString(
  "3432401181884624580219161996277760227145481682978308767347063168426989874100957186809774969532587926005597200790737572030389681269702433323694842767208349696");
      Assert.assertEquals(a.toString(), b.toString());
      TestCommon.CompareTestEqual(a, b, "");
      Assert.assertEquals(a.signum(), b.signum());
    }

    @Test
    public void TestCompareTo() {
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        EDecimal bigintA = RandomObjects.RandomEDecimal(r);
        EDecimal bigintB = RandomObjects.RandomEDecimal(r);
        EDecimal bigintC = RandomObjects.RandomEDecimal(r);
        TestCommon.CompareTestRelations(bigintA, bigintB, bigintC);
      }
      // Test equivalence of EInteger and EDecimal for integers
      for (int i = 0; i < 3000; ++i) {
        EInteger bigintA = RandomObjects.RandomEInteger(r);
        EInteger bigintB = RandomObjects.RandomEInteger(r);
        EInteger bigintC = bigintA.Add(bigintB);
        EDecimal ba1 = EDecimal.FromEInteger(bigintA)
          .Add(EDecimal.FromEInteger(bigintB));
        EDecimal ba2 = EDecimal.FromEInteger(bigintC);
        Assert.assertEquals(ba1.signum(), ba2.signum());
        Assert.assertEquals(ba1.toString(), ba2.toString());
        TestCommon.CompareTestEqual(
  ba1,
  ba2,
  bigintA.toString() + "/" + bigintB.toString());
      }
      TestCommon.CompareTestEqual(
  EDecimal.FromString("-1.603074425947290000E+2147483671"),
  EDecimal.FromString("-1.60307442594729E+2147483671"));
      TestCommon.CompareTestLess(EDecimal.Zero, EDecimal.NaN);
      TestCommon.CompareTestLess(
  EDecimal.FromString("-4328117878201602191937590091183.9810549"),
  EDecimal.FromString("-14"));
      TestCommon.CompareTestGreater(
  EDecimal.FromString("937125319376706291597172.99"),
  EDecimal.FromString("9755.2823"));
      TestCommon.CompareTestLess(
  EDecimal.FromString("95"),
  EDecimal.FromString("1.41189247453434859259019E+26"));
      TestCommon.CompareTestGreater(
  EDecimal.FromString("379351600076111561037"),
  EDecimal.FromString("8451910"));
    }
    @Test(timeout = 100000)
    public void TestCompareToBinary() {
      {
        long numberTemp = EDecimal.NegativeInfinity.CompareToBinary(null);
        Assert.assertEquals(1, numberTemp);
      }
      {
        long numberTemp = EDecimal.PositiveInfinity.CompareToBinary(null);
        Assert.assertEquals(1, numberTemp);
      }
      Assert.assertEquals(1, EDecimal.NaN.CompareToBinary(null));
      Assert.assertEquals(1, EDecimal.SignalingNaN.CompareToBinary(null));
      Assert.assertEquals(1, EDecimal.Zero.CompareToBinary(null));
      Assert.assertEquals(1, EDecimal.One.CompareToBinary(null));

      {
        long numberTemp = EDecimal.NaN.CompareToBinary(EFloat.NaN);
        Assert.assertEquals(0, numberTemp);
      }

      {
        long numberTemp =
          EDecimal.SignalingNaN.CompareToBinary(EFloat.NaN);
        Assert.assertEquals(0, numberTemp);
      }

      {
        long numberTemp =
          EDecimal.NaN.CompareToBinary(EFloat.SignalingNaN);
        Assert.assertEquals(0, numberTemp);
      }

      {
        long numberTemp =
  EDecimal.SignalingNaN.CompareToBinary(EFloat.SignalingNaN);
        Assert.assertEquals(0, numberTemp);
      }
      {
        long numberTemp = EDecimal.NaN.CompareToBinary(EFloat.Zero);
        Assert.assertEquals(1, numberTemp);
      }

      {
        long numberTemp =
          EDecimal.SignalingNaN.CompareToBinary(EFloat.Zero);
        Assert.assertEquals(1, numberTemp);
      }
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 30000; ++i) {
        EInteger bigintA = RandomObjects.RandomEInteger(r);
        int cmp = EDecimal.FromEInteger(bigintA).CompareToBinary(
            EFloat.FromEInteger(bigintA));
        Assert.assertEquals(0, cmp);
      }
    }

@Test(timeout = 1000)
public void TestSlowCompareTo() {
EInteger ei = EInteger.FromString(
  "-108854259699738613336073386912819333959164543792902007057925129910904321192623590227704182838777516070192327852552376209933022606");
EFloat ef = EFloat.Create(
  ei,
  EInteger.FromString("-94432713210"));
EDecimal ed = EDecimal.FromString("-0.00007");
Assert.assertEquals(-1, ed.CompareToBinary(ef));
}

@Test(timeout = 1000)
public void TestSlowCompareTo2() {
EFloat ef = EFloat.Create(
 EInteger.FromString("310698658007725142033104896"),
 EInteger.FromString("-910015527228"));
EDecimal ed = EDecimal.FromString(
  "5.46812681195752988681792163205092489269012868995370381431608431437654836803981061017608940175753472E-373278497416");
Assert.assertEquals(-1, ed.CompareToBinary(ef));
}

@Test(timeout = 1000)
public void TestSlowCompareTo3() {
EDecimal ed;
EFloat ef;
ef = EFloat.Create(
  EInteger.FromString("1766847170502052161990715830264538670879951287225036514637396697134727424"),
  EInteger.FromString("-312166824097095580"));
ed=EDecimal.FromString("

  9.173994463968662338877236893297097756859177826848079536001717300706132083132181223420891571892014689615873E-411");
Assert.assertEquals(1, ed.CompareToBinary(ef));
ed=EDecimal.FromString("-0.8686542656448184");
ef = EFloat.Create(
  EInteger.FromString("-140066031252330072924596216562033152419723178072587092376847513280411121126147871380984127579961289495006067586678128473926216639728812381688517268223431349786843141449122136993998169636988109708853983609451615499412285220750795244924615776386873830928453488263516664209329914433973932921432682935336466252311348743988191166143"),
  EInteger.FromString("-6881037062769847"));
Assert.assertEquals(1, ed.CompareToBinary(ef));
}

    @Test
    public void TestCompareToSignal() {
      // not implemented yet
    }

    @Test
    public void TestConversions() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 20000; ++i) {
        boolean isNum, isTruncated, isInteger;
        EInteger eint;
        EDecimal enumber = RandomObjects.RandomEDecimal(fr);
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
        EDecimal enumberInteger = EDecimal.FromEInteger(enumber.ToEInteger());
        isInteger = enumberInteger.compareTo(enumber) == 0;
        eint = enumber.ToEInteger();
        isNum = enumber.compareTo(
        EDecimal.FromString("0")) >= 0 && enumber.compareTo(
        EDecimal.FromString("255")) <= 0;
        isTruncated = enumber.ToEInteger().compareTo(
        EInteger.FromString("0")) >= 0 && enumber.ToEInteger().compareTo(
        EInteger.FromString("255")) <= 0;
        if (isNum) {
          TestCommon.AssertEqualsHashCode(
       eint,
       EInteger.FromByte(enumber.ToByteChecked()));
          TestCommon.AssertEqualsHashCode(
         eint,
         EInteger.FromByte(enumber.ToByteUnchecked()));
          if (isInteger) {
            TestCommon.AssertEqualsHashCode(
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
          TestCommon.AssertEqualsHashCode(
       eint,
       EInteger.FromByte(enumber.ToByteChecked()));
          TestCommon.AssertEqualsHashCode(
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
        EDecimal.FromString("-32768")) >= 0 && enumber.compareTo(
        EDecimal.FromString("32767")) <= 0;
        isTruncated = enumber.ToEInteger().compareTo(
      EInteger.FromString("-32768")) >= 0 && enumber.ToEInteger()
  .compareTo(
        EInteger.FromString("32767")) <= 0;
        if (isNum) {
          TestCommon.AssertEqualsHashCode(
         eint,
         EInteger.FromInt16(enumber.ToInt16Checked()));
          TestCommon.AssertEqualsHashCode(
           eint,
           EInteger.FromInt16(enumber.ToInt16Unchecked()));
          if (isInteger) {
            TestCommon.AssertEqualsHashCode(
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
          TestCommon.AssertEqualsHashCode(
         eint,
         EInteger.FromInt16(enumber.ToInt16Checked()));
          TestCommon.AssertEqualsHashCode(
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
        EDecimal.FromString("-2147483648")) >= 0 && enumber.compareTo(
        EDecimal.FromString("2147483647")) <= 0;
        isTruncated = enumber.ToEInteger().compareTo(
    EInteger.FromString("-2147483648")) >= 0 &&
          enumber.ToEInteger().compareTo(
        EInteger.FromString("2147483647")) <= 0;
        if (isNum) {
          TestCommon.AssertEqualsHashCode(
         eint,
         EInteger.FromInt32(enumber.ToInt32Checked()));
          TestCommon.AssertEqualsHashCode(
           eint,
           EInteger.FromInt32(enumber.ToInt32Unchecked()));
          if (isInteger) {
            TestCommon.AssertEqualsHashCode(
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
          TestCommon.AssertEqualsHashCode(
         eint,
         EInteger.FromInt32(enumber.ToInt32Checked()));
          TestCommon.AssertEqualsHashCode(
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
        isTruncated = enumber.ToEInteger().compareTo(
        EInteger.FromString("-9223372036854775808")) >= 0 &&
          enumber.ToEInteger().compareTo(
        EInteger.FromString("9223372036854775807")) <= 0;
        isNum = isTruncated && enumber.compareTo(
      EDecimal.FromString("-9223372036854775808")) >= 0 &&
          enumber.compareTo(
        EDecimal.FromString("9223372036854775807")) <= 0;
        if (isNum) {
          TestCommon.AssertEqualsHashCode(
         eint,
         EInteger.FromInt64(enumber.ToInt64Checked()));
          TestCommon.AssertEqualsHashCode(
           eint,
           EInteger.FromInt64(enumber.ToInt64Unchecked()));
          if (isInteger) {
            TestCommon.AssertEqualsHashCode(
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
          TestCommon.AssertEqualsHashCode(
         eint,
         EInteger.FromInt64(enumber.ToInt64Checked()));
          TestCommon.AssertEqualsHashCode(
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
    public void TestCompareToWithContext() {
      // not implemented yet
    }
    @Test
    public void TestCreate() {
      try {
        EDecimal.Create(null, EInteger.FromInt32(1));
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.Create(null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.Create(EInteger.FromInt32(1), null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestCreateNaN() {
      try {
 EDecimal.CreateNaN(null);
Assert.fail("Should have failed");
} catch (NullPointerException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      try {
 EDecimal.CreateNaN(EInteger.FromString("-1"));
Assert.fail("Should have failed");
} catch (IllegalArgumentException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      try {
 EDecimal.CreateNaN(null, false, false, null);
Assert.fail("Should have failed");
} catch (NullPointerException ex) {
// NOTE: Intentionally empty
} catch (Exception ex) {
 Assert.fail(ex.toString());
throw new IllegalStateException("", ex);
}
      EDecimal ef = EDecimal.CreateNaN(EInteger.FromInt32(0), false, true, null);
      if (!(ef.isNegative())) {
 Assert.fail();
 }
      ef = EDecimal.CreateNaN(EInteger.FromInt32(0), false, false, null);
      if (!(!ef.isNegative())) {
 Assert.fail();
 }
    }

    @Test
    public void TestDecimalsEquivalent() {
      AssertDecimalsEquivalent("1.310E-7", "131.0E-9");
      AssertDecimalsEquivalent("0.001231", "123.1E-5");
      AssertDecimalsEquivalent("3.0324E+6", "303.24E4");
      AssertDecimalsEquivalent("3.726E+8", "372.6E6");
      AssertDecimalsEquivalent("2663.6", "266.36E1");
      AssertDecimalsEquivalent("34.24", "342.4E-1");
      AssertDecimalsEquivalent("3492.5", "349.25E1");
      AssertDecimalsEquivalent("0.31919", "319.19E-3");
      AssertDecimalsEquivalent("2.936E-7", "293.6E-9");
      AssertDecimalsEquivalent("6.735E+10", "67.35E9");
      AssertDecimalsEquivalent("7.39E+10", "7.39E10");
      AssertDecimalsEquivalent("0.0020239", "202.39E-5");
      AssertDecimalsEquivalent("1.6717E+6", "167.17E4");
      AssertDecimalsEquivalent("1.7632E+9", "176.32E7");
      AssertDecimalsEquivalent("39.526", "395.26E-1");
      AssertDecimalsEquivalent("0.002939", "29.39E-4");
      AssertDecimalsEquivalent("0.3165", "316.5E-3");
      AssertDecimalsEquivalent("3.7910E-7", "379.10E-9");
      AssertDecimalsEquivalent("0.000016035", "160.35E-7");
      AssertDecimalsEquivalent("0.001417", "141.7E-5");
      AssertDecimalsEquivalent("7.337E+5", "73.37E4");
      AssertDecimalsEquivalent("3.4232E+12", "342.32E10");
      AssertDecimalsEquivalent("2.828E+8", "282.8E6");
      AssertDecimalsEquivalent("4.822E-7", "48.22E-8");
      AssertDecimalsEquivalent("2.6328E+9", "263.28E7");
      AssertDecimalsEquivalent("2.9911E+8", "299.11E6");
      AssertDecimalsEquivalent("3.636E+9", "36.36E8");
      AssertDecimalsEquivalent("0.20031", "200.31E-3");
      AssertDecimalsEquivalent("1.922E+7", "19.22E6");
      AssertDecimalsEquivalent("3.0924E+8", "309.24E6");
      AssertDecimalsEquivalent("2.7236E+7", "272.36E5");
      AssertDecimalsEquivalent("0.01645", "164.5E-4");
      AssertDecimalsEquivalent("0.000292", "29.2E-5");
      AssertDecimalsEquivalent("1.9939", "199.39E-2");
      AssertDecimalsEquivalent("2.7929E+9", "279.29E7");
      AssertDecimalsEquivalent("1.213E+7", "121.3E5");
      AssertDecimalsEquivalent("2.765E+6", "276.5E4");
      AssertDecimalsEquivalent("270.11", "270.11E0");
      AssertDecimalsEquivalent("0.017718", "177.18E-4");
      AssertDecimalsEquivalent("0.003607", "360.7E-5");
      AssertDecimalsEquivalent("0.00038618", "386.18E-6");
      AssertDecimalsEquivalent("0.0004230", "42.30E-5");
      AssertDecimalsEquivalent("1.8410E+5", "184.10E3");
      AssertDecimalsEquivalent("0.00030427", "304.27E-6");
      AssertDecimalsEquivalent("6.513E+6", "65.13E5");
      AssertDecimalsEquivalent("0.06717", "67.17E-3");
      AssertDecimalsEquivalent("0.00031123", "311.23E-6");
      AssertDecimalsEquivalent("0.0031639", "316.39E-5");
      AssertDecimalsEquivalent("1.146E+5", "114.6E3");
      AssertDecimalsEquivalent("0.00039937", "399.37E-6");
      AssertDecimalsEquivalent("3.3817", "338.17E-2");
      AssertDecimalsEquivalent("0.00011128", "111.28E-6");
      AssertDecimalsEquivalent("7.818E+7", "78.18E6");
      AssertDecimalsEquivalent("2.6417E-7", "264.17E-9");
      AssertDecimalsEquivalent("1.852E+9", "185.2E7");
      AssertDecimalsEquivalent("0.0016216", "162.16E-5");
      AssertDecimalsEquivalent("2.2813E+6", "228.13E4");
      AssertDecimalsEquivalent("3.078E+12", "307.8E10");
      AssertDecimalsEquivalent("0.00002235", "22.35E-6");
      AssertDecimalsEquivalent("0.0032827", "328.27E-5");
      AssertDecimalsEquivalent("1.334E+9", "133.4E7");
      AssertDecimalsEquivalent("34.022", "340.22E-1");
      AssertDecimalsEquivalent("7.19E+6", "7.19E6");
      AssertDecimalsEquivalent("35.311", "353.11E-1");
      AssertDecimalsEquivalent("3.4330E+6", "343.30E4");
      AssertDecimalsEquivalent("0.000022923", "229.23E-7");
      AssertDecimalsEquivalent("2.899E+4", "289.9E2");
      AssertDecimalsEquivalent("0.00031", "3.1E-4");
      AssertDecimalsEquivalent("2.0418E+5", "204.18E3");
      AssertDecimalsEquivalent("3.3412E+11", "334.12E9");
      AssertDecimalsEquivalent("1.717E+10", "171.7E8");
      AssertDecimalsEquivalent("2.7024E+10", "270.24E8");
      AssertDecimalsEquivalent("1.0219E+9", "102.19E7");
      AssertDecimalsEquivalent("15.13", "151.3E-1");
      AssertDecimalsEquivalent("91.23", "91.23E0");
      AssertDecimalsEquivalent("3.4114E+6", "341.14E4");
      AssertDecimalsEquivalent("33.832", "338.32E-1");
      AssertDecimalsEquivalent("0.19234", "192.34E-3");
      AssertDecimalsEquivalent("16835", "168.35E2");
      AssertDecimalsEquivalent("0.00038610", "386.10E-6");
      AssertDecimalsEquivalent("1.6624E+9", "166.24E7");
      AssertDecimalsEquivalent("2.351E+9", "235.1E7");
      AssertDecimalsEquivalent("0.03084", "308.4E-4");
      AssertDecimalsEquivalent("0.00429", "42.9E-4");
      AssertDecimalsEquivalent("9.718E-8", "97.18E-9");
      AssertDecimalsEquivalent("0.00003121", "312.1E-7");
      AssertDecimalsEquivalent("3.175E+4", "317.5E2");
      AssertDecimalsEquivalent("376.6", "376.6E0");
      AssertDecimalsEquivalent("0.0000026110", "261.10E-8");
      AssertDecimalsEquivalent("7.020E+11", "70.20E10");
      AssertDecimalsEquivalent("2.1533E+9", "215.33E7");
      AssertDecimalsEquivalent("3.8113E+7", "381.13E5");
      AssertDecimalsEquivalent("7.531", "75.31E-1");
      AssertDecimalsEquivalent("991.0", "99.10E1");
      AssertDecimalsEquivalent("2.897E+8", "289.7E6");
      AssertDecimalsEquivalent("0.0000033211", "332.11E-8");
      AssertDecimalsEquivalent("0.03169", "316.9E-4");
      AssertDecimalsEquivalent("2.7321E+12", "273.21E10");
      AssertDecimalsEquivalent("394.38", "394.38E0");
      AssertDecimalsEquivalent("5.912E+7", "59.12E6");
    }

    @Test
    public void TestDivide() {
      {
        String stringTemp = EDecimal.FromString(
        "1").Divide(EDecimal.FromInt32(8)).toString();
        Assert.assertEquals(
        "0.125",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
        "10").Divide(EDecimal.FromInt32(80)).toString();
        Assert.assertEquals(
        "0.125",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
        "10000").Divide(EDecimal.FromInt32(80000)).toString();
        Assert.assertEquals(
        "0.125",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
        "1000").Divide(EDecimal.FromInt32(8)).toString();
        Assert.assertEquals(
        "125",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
        "1").Divide(EDecimal.FromInt32(256)).toString();
        Assert.assertEquals(
        "0.00390625",
        stringTemp);
      }
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 5000; ++i) {
        EDecimal ed1 = RandomObjects.RandomEDecimal(fr);
        EDecimal ed2 = RandomObjects.RandomEDecimal(fr);
        if (!ed1.isFinite() || !ed2.isFinite()) {
          continue;
        }
        EDecimal ed3 = ed1.Multiply(ed2);
        if (!(ed3.isFinite())) {
 Assert.fail();
 }
        EDecimal ed4;
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
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        EDecimal bigintA = RandomObjects.RandomEDecimal(r);
        EDecimal bigintB = RandomObjects.RandomEDecimal(r);
        TestCommon.AssertEqualsHashCode(bigintA, bigintB);
      }
    }
    @Test
    public void TestExp() {
      {
        String stringTemp =
          EDecimal.One.Exp(EContext.ForPrecision(25)).toString();
        Assert.assertEquals(
        "2.718281828459045235360287",
        stringTemp);
      }
      {
        String stringTemp =
          EDecimal.One.Exp(EContext.ForPrecision(10)).toString();
        Assert.assertEquals(
        "2.718281828",
        stringTemp);
      }
      {
        String stringTemp =
          EDecimal.One.Exp(EContext.ForPrecision(9)).toString();
        Assert.assertEquals(
        "2.71828183",
        stringTemp);
      }
      {
        String stringTemp =
          EDecimal.One.Exp(EContext.ForPrecision(8)).toString();
        Assert.assertEquals(
        "2.7182818",
        stringTemp);
      }
      {
        String stringTemp =
          EDecimal.One.Exp(EContext.ForPrecision(5)).toString();
        Assert.assertEquals(
        "2.7183",
        stringTemp);
      }
      {
        String stringTemp =
          EDecimal.One.Exp(EContext.ForPrecision(3)).toString();
        Assert.assertEquals(
        "2.72",
        stringTemp);
      }
      {
        String stringTemp =
          EDecimal.One.Exp(EContext.ForPrecision(1)).toString();
        Assert.assertEquals(
        "3",
        stringTemp);
      }
    }
    @Test
    public void TestExponent() {
      Assert.assertEquals(
        EInteger.FromInt64(-7),
        EDecimal.FromString("1.265e-4").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-4),
        EDecimal.FromString("0.000E-1").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-16),
        EDecimal.FromString("0.57484848535648e-2").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-22),
        EDecimal.FromString("0.485448e-16").getExponent());
      {
        EInteger bigintTemp = EInteger.FromInt64(-20);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.5657575351495151495649565150e+8").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      Assert.assertEquals(
        EInteger.FromInt64(-10),
        EDecimal.FromString("0e-10").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-17),
        EDecimal.FromString("0.504952e-11").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-13),
        EDecimal.FromString("0e-13").getExponent());
      {
        EInteger bigintTemp = EInteger.FromInt64(-43);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.49495052535648555757515648e-17").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      Assert.assertEquals(
        EInteger.FromInt64(7),
        EDecimal.FromString("0.485654575150e+19").getExponent());
      Assert.assertEquals(
        EInteger.FromInt32(0),
        EDecimal.FromString("0.48515648e+8").getExponent());
      {
        EInteger bigintTemp = EInteger.FromInt64(-45);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.49485251485649535552535451544956e-13").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      Assert.assertEquals(
        EInteger.FromInt64(-6),
        EDecimal.FromString("0.565754515152575448505257e+18").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(16),
        EDecimal.FromString("0e+16").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(6),
        EDecimal.FromString("0.5650e+10").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-5),
        EDecimal.FromString("0.49555554575756575556e+15").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-37),
        EDecimal.FromString("0.57494855545057534955e-17").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-25),
        EDecimal.FromString("0.4956504855525748575456e-3").getExponent());
      {
        EInteger bigintTemp = EInteger.FromInt64(-26);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.55575355495654484948525354545053494854e+12").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      {
        EInteger bigintTemp = EInteger.FromInt64(-22);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.484853575350494950575749545057e+8").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      Assert.assertEquals(
        EInteger.FromInt64(11),
        EDecimal.FromString("0.52545451e+19").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-29),
        EDecimal.FromString("0.48485654495751485754e-9").getExponent());
      {
        EInteger bigintTemp = EInteger.FromInt64(-38);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.56525456555549545257535556495655574848e+0").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      {
        EInteger bigintTemp = EInteger.FromInt64(-15);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.485456485657545752495450554857e+15").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      Assert.assertEquals(
        EInteger.FromInt64(-37),
        EDecimal.FromString("0.485448525554495048e-19").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-29),
        EDecimal.FromString("0.494952485550514953565655e-5").getExponent());
      {
        EInteger bigintTemp = EInteger.FromInt64(-8);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.50495454554854505051534950e+18").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      {
        EInteger bigintTemp = EInteger.FromInt64(-37);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.5156524853575655535351554949525449e-3").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      Assert.assertEquals(
        EInteger.FromInt64(3),
        EDecimal.FromString("0e+3").getExponent());
      {
        EInteger bigintTemp = EInteger.FromInt64(-8);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.51505056554957575255555250e+18").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      Assert.assertEquals(
        EInteger.FromInt64(-14),
        EDecimal.FromString("0.5456e-10").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-36),
        EDecimal.FromString("0.494850515656505252555154e-12").getExponent());
      {
        EInteger bigintTemp = EInteger.FromInt64(-42);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.535155525253485757525253555749575749e-6").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      {
        EInteger bigintTemp = EInteger.FromInt64(-29);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.56554952554850525552515549564948e+3").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      {
        EInteger bigintTemp = EInteger.FromInt64(-40);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.494855545257545656515554495057e-10").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      Assert.assertEquals(
        EInteger.FromInt64(-18),
        EDecimal.FromString("0.5656504948515252555456e+4").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-17),
        EDecimal.FromString("0e-17").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-32),
        EDecimal.FromString("0.55535551515249535049495256e-6").getExponent());
      {
        EInteger bigintTemp = EInteger.FromInt64(-31);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.4948534853564853565654514855e-3").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      Assert.assertEquals(
        EInteger.FromInt64(-38),
        EDecimal.FromString("0.5048485057535249555455e-16").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-16),
        EDecimal.FromString("0e-16").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(5),
        EDecimal.FromString("0.5354e+9").getExponent());
      Assert.assertEquals(
        EInteger.FromInt32(1),
        EDecimal.FromString("0.54e+3").getExponent());
      {
        EInteger bigintTemp = EInteger.FromInt64(-38);
        EInteger bigintTemp2 = EDecimal.FromString(
                  "0.4849525755545751574853494948e-10").getExponent();
        Assert.assertEquals(bigintTemp, bigintTemp2);
      }
      Assert.assertEquals(
        EInteger.FromInt64(-33),
        EDecimal.FromString("0.52514853565252565251565548e-7").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-13),
        EDecimal.FromString("0.575151545652e-1").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-22),
        EDecimal.FromString("0.49515354514852e-8").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-24),
        EDecimal.FromString("0.54535357515356545554e-4").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-11),
        EDecimal.FromString("0.574848e-5").getExponent());
      Assert.assertEquals(
        EInteger.FromInt64(-3),
        EDecimal.FromString("0.565055e+3").getExponent());
    }

    @Test
    public void TestEDecimalDouble() {
      TestEDecimalDoubleCore(3.5, "3.5");
      TestEDecimalDoubleCore(7, "7");
      TestEDecimalDoubleCore(1.75, "1.75");
      TestEDecimalDoubleCore(3.5, "3.5");
      TestEDecimalDoubleCore((double)Integer.MIN_VALUE, "-2147483648");
      TestEDecimalDoubleCore(
        (double)Long.MIN_VALUE,
        "-9223372036854775808");
      RandomGenerator rand = new RandomGenerator();
      for (int i = 0; i < 2047; ++i) {
        // Try a random double with a given
        // exponent
        TestEDecimalDoubleCore(
  RandomObjects.RandomDouble(rand, i),
  null);
        TestEDecimalDoubleCore(
  RandomObjects.RandomDouble(rand, i),
  null);
        TestEDecimalDoubleCore(
  RandomObjects.RandomDouble(rand, i),
  null);
        TestEDecimalDoubleCore(
  RandomObjects.RandomDouble(rand, i),
  null);
      }
    }
    @Test
    public void TestEDecimalSingle() {
      RandomGenerator rand = new RandomGenerator();
      for (int i = 0; i < 255; ++i) {
        // Try a random float with a given
        // exponent
        TestEDecimalSingleCore(
  RandomObjects.RandomSingle(rand, i),
  null);
        TestEDecimalSingleCore(
  RandomObjects.RandomSingle(rand, i),
  null);
        TestEDecimalSingleCore(
  RandomObjects.RandomSingle(rand, i),
  null);
        TestEDecimalSingleCore(
  RandomObjects.RandomSingle(rand, i),
  null);
      }
    }

    @Test
    public void TestFromEInteger() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 5000; ++i) {
        EInteger ei = RandomObjects.RandomEInteger(fr);
        EDecimal edec = EDecimal.FromEInteger(ei);
        Assert.assertEquals(EInteger.FromInt32(0), edec.getExponent());
        Assert.assertEquals(ei, edec.getMantissa());
        Assert.assertEquals(ei, edec.ToEInteger());
      }
    }
    @Test
    public void TestFromDouble() {
      String stringTemp;
      {
        stringTemp = EDecimal.FromDouble(0.75).toString();
        Assert.assertEquals(
          "0.75",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.5).toString();
        Assert.assertEquals(
          "0.5",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.25).toString();
        Assert.assertEquals(
          "0.25",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.875).toString();
        Assert.assertEquals(
          "0.875",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.125).toString();
        Assert.assertEquals(
          "0.125",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.2133).toString();
        Assert.assertEquals(
          "0.213299999999999989608312489508534781634807586669921875",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(2.2936E-7).toString();
        Assert.assertEquals(
   "2.29360000000000010330982488752915582352898127282969653606414794921875E-7",
   stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(3.8932E9).toString();
        Assert.assertEquals(
          "3893200000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(128230.0).toString();
        Assert.assertEquals(
          "128230",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(127210.0).toString();
        Assert.assertEquals(
          "127210",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.26723).toString();
        Assert.assertEquals(
          "0.267230000000000023074875343809253536164760589599609375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.30233).toString();
        Assert.assertEquals(
          "0.302329999999999987636556397774256765842437744140625",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(1.9512E-6).toString();
        Assert.assertEquals(
    "0.0000019512000000000000548530838806460252499164198525249958038330078125",
    stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(199500.0).toString();
        Assert.assertEquals(
          "199500",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(3.6214E7).toString();
        Assert.assertEquals(
          "36214000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1.9133E12).toString();
        Assert.assertEquals(
          "1913300000000",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(2.1735E-4).toString();
        Assert.assertEquals(
          "0.0002173499999999999976289799530349000633577816188335418701171875",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(3.1035E-5).toString();
        Assert.assertEquals(
       "0.0000310349999999999967797807698399736864303122274577617645263671875",
       stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1.275).toString();
        Assert.assertEquals(
          "1.274999999999999911182158029987476766109466552734375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(214190.0).toString();
        Assert.assertEquals(
          "214190",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(3.9813E9).toString();
        Assert.assertEquals(
          "3981300000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1092700.0).toString();
        Assert.assertEquals(
          "1092700",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(0.02361).toString();
        Assert.assertEquals(
          "0.023609999999999999042987752773115062154829502105712890625",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(12.322).toString();
        Assert.assertEquals(
          "12.321999999999999175770426518283784389495849609375",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(0.002587).toString();
        Assert.assertEquals(
          "0.002586999999999999889921387108415729016996920108795166015625",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1.322E9).toString();
        Assert.assertEquals(
          "1322000000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(9.531E10).toString();
        Assert.assertEquals(
          "95310000000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(142.38).toString();
        Assert.assertEquals(
          "142.3799999999999954525264911353588104248046875",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(2252.5).toString();
        Assert.assertEquals(
          "2252.5",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(3.636E11).toString();
        Assert.assertEquals(
          "363600000000",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(3.237E-6).toString();
        Assert.assertEquals(
   "0.00000323700000000000009386523676380154057596882921643555164337158203125",
   stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(728000.0).toString();
        Assert.assertEquals(
          "728000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(2.5818E7).toString();
        Assert.assertEquals(
          "25818000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1090000.0).toString();
        Assert.assertEquals(
          "1090000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1.551).toString();
        Assert.assertEquals(
          "1.5509999999999999342747969421907328069210052490234375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(26.035).toString();
        Assert.assertEquals(
          "26.035000000000000142108547152020037174224853515625",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(8.33E8).toString();
        Assert.assertEquals(
          "833000000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(8.123E11).toString();
        Assert.assertEquals(
          "812300000000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(2622.9).toString();
        Assert.assertEquals(
          "2622.90000000000009094947017729282379150390625",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1.291).toString();
        Assert.assertEquals(
          "1.290999999999999925393012745189480483531951904296875",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(286140.0).toString();
        Assert.assertEquals(
          "286140",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.06733).toString();
        Assert.assertEquals(
          "0.06733000000000000095923269327613525092601776123046875",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(3.2516E-4).toString();
        Assert.assertEquals(
          "0.000325160000000000010654532811571471029310487210750579833984375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(3.8323E8).toString();
        Assert.assertEquals(
          "383230000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.028433).toString();
        Assert.assertEquals(
          "0.02843299999999999994049204588009160943329334259033203125",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(8.37E8).toString();
        Assert.assertEquals(
          "837000000",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(0.01608).toString();
        Assert.assertEquals(
          "0.0160800000000000005428990590417015482671558856964111328125",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(3.621E12).toString();
        Assert.assertEquals(
          "3621000000000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(78.12).toString();
        Assert.assertEquals(
          "78.1200000000000045474735088646411895751953125",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1.308E9).toString();
        Assert.assertEquals(
          "1308000000",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(0.031937).toString();
        Assert.assertEquals(
          "0.031937000000000000110578213252665591426193714141845703125",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1581500.0).toString();
        Assert.assertEquals(
          "1581500",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(244200.0).toString();
        Assert.assertEquals(
          "244200",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(2.2818E-7).toString();
        Assert.assertEquals(
   "2.28179999999999995794237200343046456652018605382181704044342041015625E-7",
   stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(39.734).toString();
        Assert.assertEquals(
          "39.73400000000000176214598468504846096038818359375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1614.0).toString();
        Assert.assertEquals(
          "1614",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(3.8319E-4).toString();
        Assert.assertEquals(
          "0.0003831899999999999954607143859419693399104289710521697998046875",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(543.4).toString();
        Assert.assertEquals(
          "543.3999999999999772626324556767940521240234375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(3.1931E8).toString();
        Assert.assertEquals(
          "319310000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1429000.0).toString();
        Assert.assertEquals(
          "1429000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(2.6537E12).toString();
        Assert.assertEquals(
          "2653700000000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(7.22E8).toString();
        Assert.assertEquals(
          "722000000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(27.2).toString();
        Assert.assertEquals(
          "27.199999999999999289457264239899814128875732421875",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(3.8025E-6).toString();
        Assert.assertEquals(
   "0.00000380250000000000001586513038998038638283105683512985706329345703125",
   stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(3.6416E-5).toString();
        Assert.assertEquals(
          "0.0000364159999999999982843446044711299691698513925075531005859375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(2006000.0).toString();
        Assert.assertEquals(
          "2006000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(2.6812E9).toString();
        Assert.assertEquals(
          "2681200000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(2.7534E10).toString();
        Assert.assertEquals(
          "27534000000",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(3.9116E-7).toString();
        Assert.assertEquals(
     "3.911600000000000165617541382501176627783934236504137516021728515625E-7",
     stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(0.0028135).toString();
        Assert.assertEquals(
          "0.0028135000000000000286437540353290387429296970367431640625",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.9119).toString();
        Assert.assertEquals(
          "0.91190000000000004387601393318618647754192352294921875",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(2241200.0).toString();
        Assert.assertEquals(
          "2241200",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(32.45).toString();
        Assert.assertEquals(
          "32.4500000000000028421709430404007434844970703125",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1.38E10).toString();
        Assert.assertEquals(
          "13800000000",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(0.0473).toString();
        Assert.assertEquals(
          "0.047300000000000001765254609153998899273574352264404296875",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(205.34).toString();
        Assert.assertEquals(
          "205.340000000000003410605131648480892181396484375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(3.9819).toString();
        Assert.assertEquals(
          "3.981899999999999995026200849679298698902130126953125",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1152.8).toString();
        Assert.assertEquals(
          "1152.799999999999954525264911353588104248046875",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1322000.0).toString();
        Assert.assertEquals(
          "1322000",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(1.3414E-4).toString();
        Assert.assertEquals(
          "0.00013414000000000001334814203612921801322954706847667694091796875",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(3.445E-7).toString();
        Assert.assertEquals(
    "3.4449999999999999446924077266263264363033158588223159313201904296875E-7",
    stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(1.361E-7).toString();
        Assert.assertEquals(
    "1.3610000000000000771138253079228785935583800892345607280731201171875E-7",
    stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(2.609E7).toString();
        Assert.assertEquals(
          "26090000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(9.936).toString();
        Assert.assertEquals(
          "9.93599999999999994315658113919198513031005859375",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(6.0E-6).toString();
        Assert.assertEquals(
      "0.00000600000000000000015200514458246772164784488268196582794189453125",
      stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(260.31).toString();
        Assert.assertEquals(
          "260.31000000000000227373675443232059478759765625",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(344.6).toString();
        Assert.assertEquals(
          "344.6000000000000227373675443232059478759765625",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(3.4237).toString();
        Assert.assertEquals(
          "3.423700000000000187583282240666449069976806640625",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(2.3421E9).toString();
        Assert.assertEquals(
          "2342100000",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(2.331E-4).toString();
        Assert.assertEquals(
          "0.00023310000000000000099260877295392901942250318825244903564453125",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.734).toString();
        Assert.assertEquals(
          "0.7339999999999999857891452847979962825775146484375",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(0.015415).toString();
        Assert.assertEquals(
          "0.01541499999999999988287147090204598498530685901641845703125",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(0.0035311).toString();
        Assert.assertEquals(
          "0.0035311000000000001240729741169843691750429570674896240234375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(1.2217E12).toString();
        Assert.assertEquals(
          "1221700000000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(0.483).toString();
        Assert.assertEquals(
          "0.48299999999999998490096686509787105023860931396484375",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(2.872E-4).toString();
        Assert.assertEquals(
          "0.0002871999999999999878506906636488338335766457021236419677734375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(96.11).toString();
        Assert.assertEquals(
          "96.1099999999999994315658113919198513031005859375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(36570.0).toString();
        Assert.assertEquals(
          "36570",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(1.83E-5).toString();
        Assert.assertEquals(
      "0.00001830000000000000097183545932910675446692039258778095245361328125",
      stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(3.0131E8).toString();
        Assert.assertEquals(
          "301310000",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(382200.0).toString();
        Assert.assertEquals(
          "382200",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(2.4835E8).toString();
        Assert.assertEquals(
          "248350000",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(0.001584).toString();
        Assert.assertEquals(
          "0.0015839999999999999046040866090834242640994489192962646484375",
          stringTemp);
      }

      {
        stringTemp = EDecimal.FromDouble(7.62E-4).toString();
        Assert.assertEquals(
          "0.000761999999999999982035203682784185730270110070705413818359375",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromDouble(3.133E11).toString();
        Assert.assertEquals(
          "313300000000",
          stringTemp);
      }
    }
    @Test
    public void TestFromEFloat() {
      Assert.assertEquals(
        EDecimal.Zero,
        EDecimal.FromEFloat(EFloat.Zero));
      Assert.assertEquals(
        EDecimal.NegativeZero,
        EDecimal.FromEFloat(EFloat.NegativeZero));
      try {
        EDecimal.FromEFloat(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      EFloat bf;
      bf = EFloat.FromInt64(20);
      {
        String stringTemp = EDecimal.FromEFloat(bf).toString();
        Assert.assertEquals(
        "20",
        stringTemp);
      }
      bf = EFloat.Create(EInteger.FromInt64(3), EInteger.FromInt64(-1));
      {
        String stringTemp = EDecimal.FromEFloat(bf).toString();
        Assert.assertEquals(
        "1.5",
        stringTemp);
      }
      bf = EFloat.Create(EInteger.FromInt64(-3), EInteger.FromInt64(-1));
      {
        String stringTemp = EDecimal.FromEFloat(bf).toString();
        Assert.assertEquals(
        "-1.5",
        stringTemp);
      }
    }
    @Test
    public void TestFromInt32() {
      Assert.assertEquals(EDecimal.One, EDecimal.FromInt32(1));
    }
    @Test
    public void TestFromSingle() {
      String stringTemp;
      {
        stringTemp = EDecimal.FromSingle(0.75f).toString();
        Assert.assertEquals(
          "0.75",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromSingle(0.5f).toString();
        Assert.assertEquals(
          "0.5",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromSingle(0.25f).toString();
        Assert.assertEquals(
          "0.25",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromSingle(0.875f).toString();
        Assert.assertEquals(
          "0.875",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromSingle(0.125f).toString();
        Assert.assertEquals(
          "0.125",
          stringTemp);
      }
    }

    @Test
    public void TestFromString() {
      try {
        EDecimal.FromString("");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      Assert.assertEquals(EDecimal.Zero, EDecimal.FromString("0"));
      Assert.assertEquals(
        EDecimal.Zero,
        EDecimal.FromString("0", null));
      try {
        EDecimal.FromString(null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString(null, 0, 1);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      try {
        EDecimal.FromString("0..1");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("0.1x+222");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("0.1g-222");
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      try {
        EDecimal.FromString("x", -1, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("x", 2, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("x", 0, -1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("x", 0, 2);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("x", 1, 1);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString(null, 0, 1, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("x", -1, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("x", 2, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("x", 0, -1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("x", 0, 2, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("x", 1, 1, null);
        Assert.fail("Should have failed");
      } catch (NumberFormatException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }

      RandomGenerator rand = new RandomGenerator();
      for (int i = 0; i < 3000; ++i) {
        String r = RandomObjects.RandomDecimalString(rand);
        try {
          EDecimal.FromString(r);
        } catch (Exception ex) {
          Assert.fail(ex.toString());
          throw new IllegalStateException("", ex);
        }
      }
    }

    @Test
    public void TestFromStringDecimal() {
      {
        String stringTemp = EDecimal.FromString(
        "-89675213981993819.5183499484258059",
        EContext.CliDecimal).toString();
        Assert.assertEquals(
        "-89675213981993819.51834994843",
        stringTemp);
      }
    }

    @Test
    public void TestIsFinite() {
      // not implemented yet
    }
    @Test
    public void TestIsInfinity() {
      if (!(EDecimal.PositiveInfinity.IsInfinity())) {
 Assert.fail();
 }
      if (!(EDecimal.NegativeInfinity.IsInfinity())) {
 Assert.fail();
 }
      if (EDecimal.Zero.IsInfinity()) {
 Assert.fail();
 }
      if (EDecimal.NaN.IsInfinity()) {
 Assert.fail();
 }
    }
    @Test
    public void TestIsNaN() {
      if (EDecimal.PositiveInfinity.IsNaN()) {
 Assert.fail();
 }
      if (EDecimal.NegativeInfinity.IsNaN()) {
 Assert.fail();
 }
      if (EDecimal.Zero.IsNaN()) {
 Assert.fail();
 }
      if (!(EDecimal.NaN.IsNaN())) {
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
      if (EDecimal.NaN.isZero()) {
 Assert.fail();
 }
      if (EDecimal.SignalingNaN.isZero()) {
 Assert.fail();
 }
    }
    @Test
    public void TestLog() {
      if (!(EDecimal.One.Log(null).IsNaN())) {
 Assert.fail();
 }
      if (!(EDecimal.One.Log(EContext.Unlimited).IsNaN())) {
 Assert.fail();
 }
    }
    @Test
    public void TestLog10() {
      if (!(EDecimal.One.Log10(null).IsNaN())) {
 Assert.fail();
 }
      if (!(EDecimal.One.Log10(EContext.Unlimited)
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
        EDecimal.Max(null, EDecimal.One);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.Max(EDecimal.One, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        EDecimal bigintA = RandomObjects.RandomEDecimal(r);
        EDecimal bigintB = RandomObjects.RandomEDecimal(r);
        if (!bigintA.isFinite() || !bigintB.isFinite()) {
          continue;
        }
        int cmp = TestCommon.CompareTestReciprocal(bigintA, bigintB);
        if (cmp < 0) {
          TestCommon.CompareTestEqual(
       bigintB,
       EDecimal.Max(bigintA, bigintB));
        } else if (cmp > 0) {
          TestCommon.CompareTestEqual(
       bigintA,
       EDecimal.Max(bigintA, bigintB));
        } else {
          TestCommon.CompareTestEqual(
       bigintA,
       EDecimal.Max(bigintA, bigintB));
          TestCommon.CompareTestEqual(
       bigintB,
       EDecimal.Max(bigintA, bigintB));
        }
      }
    }
    @Test
    public void TestMaxMagnitude() {
      try {
        EDecimal.MaxMagnitude(null, EDecimal.One);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.MaxMagnitude(EDecimal.One, null);
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
        EDecimal.Min(null, EDecimal.One);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.Min(EDecimal.One, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        EDecimal bigintA = RandomObjects.RandomEDecimal(r);
        EDecimal bigintB = RandomObjects.RandomEDecimal(r);
        if (!bigintA.isFinite() || !bigintB.isFinite()) {
          continue;
        }
        int cmp = TestCommon.CompareTestReciprocal(bigintA, bigintB);
        if (cmp < 0) {
          TestCommon.CompareTestEqual(
       bigintA,
       EDecimal.Min(bigintA, bigintB));
        } else if (cmp > 0) {
          TestCommon.CompareTestEqual(
       bigintB,
       EDecimal.Min(bigintA, bigintB));
        } else {
          TestCommon.CompareTestEqual(
       bigintA,
       EDecimal.Min(bigintA, bigintB));
          TestCommon.CompareTestEqual(
       bigintB,
       EDecimal.Min(bigintA, bigintB));
        }
      }
    }
    @Test
    public void TestMinMagnitude() {
      try {
        EDecimal.MinMagnitude(null, EDecimal.One);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.MinMagnitude(EDecimal.One, null);
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
      {
        String stringTemp = EDecimal.FromString(
        "1").MovePointLeft(EInteger.FromInt32(0), null).toString();
        Assert.assertEquals(
        "1",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
        "0.1").MovePointLeft(EInteger.FromInt32(0), null).toString();
        Assert.assertEquals(
        "0.1",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
        "0.01").MovePointLeft(EInteger.FromInt32(0), null).toString();
        Assert.assertEquals(
        "0.01",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
        "1").MovePointLeft(0, null).toString();
        Assert.assertEquals(
        "1",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
        "0.1").MovePointLeft(0, null).toString();
        Assert.assertEquals(
        "0.1",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
        "0.01").MovePointLeft(0, null).toString();
        Assert.assertEquals(
        "0.01",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
  "1").MovePointLeft(0).toString();
        Assert.assertEquals(
        "1",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
        "0.1").MovePointLeft(0).toString();
        Assert.assertEquals(
        "0.1",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
        "0.01").MovePointLeft(0).toString();
        Assert.assertEquals(
        "0.01",
        stringTemp);
      }
    }
    @Test
    public void TestMultiply() {
      // not implemented yet
    }
    @Test
    public void TestMultiplyAndAdd() {
      try {
        EDecimal.One.MultiplyAndAdd(null, EDecimal.Zero, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.One.MultiplyAndAdd(EDecimal.Zero, null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.One.MultiplyAndAdd(null, null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.One.MultiplyAndAdd(null, EDecimal.Zero);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.One.MultiplyAndAdd(EDecimal.Zero, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.One.MultiplyAndAdd(null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestMultiplyAndSubtract() {
      try {
        EDecimal.One.MultiplyAndSubtract(
   null,
   EDecimal.Zero,
   null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.One.MultiplyAndSubtract(
   EDecimal.Zero,
   null,
   null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.One.MultiplyAndSubtract(null, null, null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestCopySign() {
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        EDecimal ed = RandomObjects.RandomEDecimal(r);
        ed = ed.CopySign(EDecimal.Zero);
        if (ed.isNegative()) {
 Assert.fail();
 }
        ed = ed.CopySign(EDecimal.NegativeZero);
        if (!(ed.isNegative())) {
 Assert.fail();
 }
      }
      if (EDecimal.SignalingNaN.CopySign(EDecimal.Zero).isNegative()) {
 Assert.fail();
 }
      if (!(
        EDecimal.SignalingNaN.CopySign(EDecimal.NegativeZero).isNegative())) {
 Assert.fail();
 }
    }

    @Test
    public void TestNegate() {
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        EDecimal ed = RandomObjects.RandomEDecimal(r);
        ed = ed.CopySign(EDecimal.Zero);
        if (!(ed.Negate().isNegative())) {
 Assert.fail();
 }
        ed = ed.CopySign(EDecimal.NegativeZero);
        if (!(ed.isNegative())) {
 Assert.fail();
 }
      }
      if (!(EDecimal.SignalingNaN.Negate().isNegative())) {
 Assert.fail();
 }
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
      EDecimal pi = EDecimal.PI(EContext.ForPrecision(3));
      {
        String stringTemp = pi.ToPlainString();
        Assert.assertEquals(
          "3.14",
          stringTemp);
      }
      pi = EDecimal.PI(EContext.ForPrecision(4));
      {
        String stringTemp = pi.ToPlainString();
        Assert.assertEquals(
          "3.142",
          stringTemp);
      }
      pi = EDecimal.PI(EContext.ForPrecision(5));
      {
        String stringTemp = pi.ToPlainString();
        Assert.assertEquals(
          "3.1416",
          stringTemp);
      }
      pi = EDecimal.PI(EContext.ForPrecision(6));
      {
        String stringTemp = pi.ToPlainString();
        Assert.assertEquals(
          "3.14159",
          stringTemp);
      }
      pi = EDecimal.PI(EContext.ForPrecision(7));
      {
        String stringTemp = pi.ToPlainString();
        Assert.assertEquals(
          "3.141593",
          stringTemp);
      }
      pi = EDecimal.PI(EContext.ForPrecision(8));
      {
        String stringTemp = pi.ToPlainString();
        Assert.assertEquals(
          "3.1415927",
          stringTemp);
      }
      pi = EDecimal.PI(EContext.ForPrecision(9));
      {
        String stringTemp = pi.ToPlainString();
        Assert.assertEquals(
          "3.14159265",
          stringTemp);
      }
      pi = EDecimal.PI(EContext.ForPrecision(10));
      {
        String stringTemp = pi.ToPlainString();
        Assert.assertEquals(
          "3.141592654",
          stringTemp);
      }
      pi = EDecimal.PI(EContext.ForPrecision(25));
      {
        String stringTemp = pi.ToPlainString();
        Assert.assertEquals(
          "3.141592653589793238462643",
          stringTemp);
      }
    }
    @Test
    public void TestPlus() {
      Assert.assertEquals(
  EDecimal.Zero,
  EDecimal.NegativeZero.Plus(EContext.Basic));
      Assert.assertEquals(
  EDecimal.Zero,
  EDecimal.NegativeZero.Plus(null));
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
    @Test(timeout = 60000)
    public void TestRemainder() {
      {
        EDecimal dividend = EDecimal.Create(
        EInteger.FromString("59840771232212955222033039906"),
        EInteger.FromInt32(0));
        EDecimal divisor = EDecimal.Create(56, -3);
        EDecimal remainder = dividend.RemainderNoRoundAfterDivide(
  divisor,
  EContext.CliDecimal);
        Assert.assertEquals(remainder.toString(),0,remainder.compareTo(EDecimal.FromString("0.24e-1")));
      }
      {
        EDecimal dividend = EDecimal.Create(
        EInteger.FromString("71335024459231687631753628354"),
        EInteger.FromInt32(-1));
        EDecimal divisor = EDecimal.Create(99, -4);
        EDecimal remainder = dividend.RemainderNoRoundAfterDivide(
  divisor,
  EContext.CliDecimal);
        Assert.assertEquals(remainder.toString(),0,remainder.compareTo(EDecimal.FromString("0.42e-2")));
      }
      {
        EDecimal dividend = EDecimal.Create(
        EInteger.FromString("68541311159644774501062173149"),
        EInteger.FromInt32(0));
        EDecimal divisor = EDecimal.Create(68, -4);
        EDecimal remainder = dividend.RemainderNoRoundAfterDivide(
  divisor,
  EContext.CliDecimal);
        Assert.assertEquals(remainder.toString(),0,remainder.compareTo(EDecimal.FromString("0.4e-3")));
      }
      {
        EDecimal dividend = EDecimal.Create(
        EInteger.FromString("72230705069845418380625072039"),
        EInteger.FromInt32(0));
        EDecimal divisor = EDecimal.Create(67, -4);
        EDecimal remainder = dividend.RemainderNoRoundAfterDivide(
  divisor,
  EContext.CliDecimal);
        Assert.assertEquals(remainder.toString(),0,remainder.compareTo(EDecimal.FromString("0.36e-2")));
      }
      {
        EDecimal dividend = EDecimal.Create(
        EInteger.FromString("66072931440684617011747269944"),
        EInteger.FromInt32(-1));
        EDecimal divisor = EDecimal.Create(29, -4);
        EDecimal remainder = dividend.RemainderNoRoundAfterDivide(
  divisor,
  EContext.CliDecimal);
        Assert.assertEquals(remainder.toString(),0,remainder.compareTo(EDecimal.FromString("0.2e-2")));
      }
      {
        EDecimal dividend = EDecimal.Create(
        EInteger.FromString("25599757933935193456538556810"),
        EInteger.FromInt32(0));
        EDecimal divisor = EDecimal.Create(74, -3);
        EDecimal remainder = dividend.RemainderNoRoundAfterDivide(
  divisor,
  EContext.CliDecimal);
        Assert.assertEquals(remainder.toString(),0,remainder.compareTo(EDecimal.FromString("0.3e-1")));
      }
      {
        EDecimal dividend = EDecimal.Create(
        EInteger.FromString("56970681730399566214724883073"),
        EInteger.FromInt32(0));
        EDecimal divisor = EDecimal.Create(38, -4);
        EDecimal remainder = dividend.RemainderNoRoundAfterDivide(
  divisor,
  EContext.CliDecimal);
        Assert.assertEquals(remainder.toString(),0,remainder.compareTo(EDecimal.FromString("0.12e-2")));
      }
      {
        EDecimal dividend = EDecimal.Create(
        EInteger.FromString("76187141448183306266415732331"),
        EInteger.FromInt32(-2));
        EDecimal divisor = EDecimal.Create(71, -4);
        EDecimal remainder = dividend.RemainderNoRoundAfterDivide(
  divisor,
  EContext.CliDecimal);
        Assert.assertEquals(remainder.toString(),0,remainder.compareTo(EDecimal.FromString("0.6e-2")));
      }
      {
        EDecimal dividend = EDecimal.Create(
        EInteger.FromString("56333974884046765961647166477"),
        EInteger.FromInt32(-2));
        EDecimal divisor = EDecimal.Create(63, -4);
        EDecimal remainder = dividend.RemainderNoRoundAfterDivide(
  divisor,
  EContext.CliDecimal);
        Assert.assertEquals(remainder.toString(),0,remainder.compareTo(EDecimal.FromString("0.35e-2")));
      }
      {
        EDecimal dividend = EDecimal.Create(
        EInteger.FromString("53394382853562286627297867112"),
        EInteger.FromInt32(0));
        EDecimal divisor = EDecimal.Create(99, -3);
        EDecimal remainder = dividend.RemainderNoRoundAfterDivide(
  divisor,
  EContext.CliDecimal);
        Assert.assertEquals(remainder.toString(),0,remainder.compareTo(EDecimal.FromString("0.32e-1")));
      }
    }
    @Test
    public void TestRemainderNaturalScale() {
      // not implemented yet
    }
    @Test
    public void TestRemainderNear() {
      // not implemented yet
    }
    @Test(timeout = 60000)
    public void TestRoundToExponent() {
      this.TestRoundToExponentOne("-0", "-0", 0, ERounding.Down);
      this.TestRoundToExponentOne("-0", "-0", 0, ERounding.HalfEven);
      this.TestRoundToExponentOne("-0", "-0", 0, ERounding.Floor);
      this.TestRoundToExponentOne("-0.0", "-0", 0, ERounding.Down);
      this.TestRoundToExponentOne("-0.0", "-0", 0, ERounding.HalfEven);
      this.TestRoundToExponentOne("-0.0", "-0", 0, ERounding.Floor);
      this.TestRoundToExponentOne("-0.0000", "-0", 0, ERounding.Down);
      this.TestRoundToExponentOne("-0.0000", "-0", 0, ERounding.HalfEven);
      this.TestRoundToExponentOne("-0.0000", "-0", 0, ERounding.Floor);
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
    public void TestScaling() {
      {
        String stringTemp = EDecimal.FromString(
          "5.000").ScaleByPowerOfTen(5).toString();
        Assert.assertEquals(
          "5.000E+5",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
          "5.000").ScaleByPowerOfTen(-5).toString();
        Assert.assertEquals(
          "0.00005000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
          "500000").MovePointRight(5).toString();
        Assert.assertEquals(
          "50000000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
          "500000").MovePointRight(-5).toString();
        Assert.assertEquals(
          "5.00000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
          "500000").MovePointLeft(-5).toString();
        Assert.assertEquals(
          "50000000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
          "500000").MovePointLeft(5).toString();
        Assert.assertEquals(
          "5.00000",
          stringTemp);
      }
    }
    @Test
    public void TestSign() {
      // not implemented yet
    }
    @Test
    public void TestSignalingNaN() {
      {
        String stringTemp = EDecimal.SignalingNaN.toString();
        Assert.assertEquals(
        "sNaN",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.SignalingNaN.ToEngineeringString();
        Assert.assertEquals(
        "sNaN",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.SignalingNaN.ToPlainString();
        Assert.assertEquals(
        "sNaN",
        stringTemp);
      }
    }

    /*
    private static void CA(byte[] bytes) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; ++i) {
        sb.AppendFormat("{0:X2}", bytes[i]);
      }
      System.out.println(sb.toString());
    }
    */

    private static void AssertQuietNaN(String str) {
      EDecimal ed = EDecimal.FromString(str);
      if (!(ed.IsQuietNaN())) {
 Assert.fail();
 }
      if (!(EDecimal.FromDouble(ed.ToDouble()).IsQuietNaN()))Assert.fail();
      if (!(EDecimal.FromSingle(ed.ToSingle()).IsQuietNaN()))Assert.fail();
      EFloat ef = EFloat.FromString(str);
      if (!(ef.IsQuietNaN())) {
 Assert.fail();
 }
      if (!(EFloat.FromDouble(ef.ToDouble()).IsQuietNaN()))Assert.fail();
      if (!(EFloat.FromSingle(ef.ToSingle()).IsQuietNaN()))Assert.fail();
      ERational er = ERational.FromString(str);
      if (!(er.IsQuietNaN())) {
 Assert.fail();
 }
      if (!(ERational.FromDouble(er.ToDouble()).IsQuietNaN()))Assert.fail();
      if (!(ERational.FromSingle(er.ToSingle()).IsQuietNaN()))Assert.fail();
    }

    private static void AssertSignalingNaN(String str) {
      EDecimal ed = EDecimal.FromString(str);
      if (!(ed.IsSignalingNaN())) {
 Assert.fail();
 }
      EFloat ef = EFloat.FromString(str);
      if (!(ef.IsSignalingNaN())) {
 Assert.fail();
 }
      ERational er = ERational.FromString(str);
      if (!(er.IsSignalingNaN())) {
 Assert.fail();
 }
      // NOTE: Unfortunately, .NET might quiet signaling
      // NaNs it may otherwise generate
      if (!(
        EDecimal.FromDouble(ed.ToDouble()).IsNaN()))Assert.fail(
        str);
      if (!(EDecimal.FromSingle(ed.ToSingle()).IsNaN()))Assert.fail();
      if (!(EFloat.FromDouble(ef.ToDouble()).IsNaN()))Assert.fail();
      if (!(EFloat.FromSingle(ef.ToSingle()).IsNaN()))Assert.fail();
      if (!(ERational.FromDouble(er.ToDouble()).IsNaN()))Assert.fail();
      if (!(ERational.FromSingle(er.ToSingle()).IsNaN()))Assert.fail();
    }

    @Test
    public void TestQuietSignalingNaN() {
      for (int i = 0; i <= 50; ++i) {
        AssertQuietNaN("NaN" + TestCommon.IntToString(i));
        AssertSignalingNaN("sNaN" + TestCommon.IntToString(i));
      }
    }
    @Test
    public void TestSquareRoot() {
      // not implemented yet
    }
    @Test
    public void TestSubtract() {
      try {
        EDecimal.Zero.Subtract(null, EContext.Unlimited);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestToEInteger() {
      try {
        EDecimal.PositiveInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.NegativeInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.PositiveInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.NegativeInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.NaN.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.SignalingNaN.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      EDecimal dec = EDecimal.Create(999, -1);
      try {
        dec.ToEInteger();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestToEIntegerIfExact() {
      EDecimal dec = EDecimal.Create(999, -1);
      try {
        dec.ToEIntegerIfExact();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    private static final EDecimal DoubleUnderflowToZero =
      EFloat.Create(1, -1075).ToEDecimal();

    private static final EDecimal DoubleOverflowToInfinity =
EFloat.Create(
  EInteger.FromInt64((1L << 53) - 1),
  EInteger.FromInt32(971)).Add(
         EFloat.Create(1, 970)).ToEDecimal();

    private static final EDecimal SingleUnderflowToZero =
      EFloat.Create(1, -150).ToEDecimal();

    private static final EDecimal SingleOverflowToInfinity =
EFloat.Create(
  EInteger.FromInt64((1L << 24) - 1),
  EInteger.FromInt32(104)).Add(
         EFloat.Create(1, 103)).ToEDecimal();

    private static EDecimal[] MakeUlpTable() {
      EDecimal[] edecarr = new EDecimal[2048];
      for (int i = 0; i < 2048; ++i) {
        edecarr[i] = EFloat.Create(1, i - 1075).ToEDecimal();
      }
      return edecarr;
    }

    private static final EDecimal[] UlpTable = MakeUlpTable();

    private static EDecimal GetHalfUlp(double dbl) {
      long value = Double.doubleToRawLongBits(dbl);
      int exponent = (int)((value >> 52) & 0x7ffL);
      if (exponent == 0) {
        return UlpTable[exponent];
      } else if (exponent == 2047) {
        throw new IllegalArgumentException();
      } else {
        return UlpTable[exponent - 1];
      }
    }

    private static EDecimal GetHalfUlp(float sng) {
      int value = Float.floatToRawIntBits(sng);
      int exponent = (int)((value >> 23) & 0xff);
      if (exponent == 0) {
        return UlpTable[exponent + 925];
      } else if (exponent == 255) {
        throw new IllegalArgumentException();
      } else {
        return UlpTable[exponent + 924];
      }
    }

    @Test
    public void TestToDouble() {
      // test for correct rounding
      double dbl;
      dbl = EDecimal.FromString(
        "1.972579273363468721491642554610734805464744567871093749999999999999")
        .ToDouble();
      {
        String stringTemp = EFloat.FromDouble(dbl).ToPlainString();
        Assert.assertEquals(
          "1.9725792733634686104693400920950807631015777587890625",
          stringTemp);
      }
      RandomGenerator fr = new RandomGenerator();
      dbl = DoubleOverflowToInfinity.ToDouble();
      if (!(((dbl) == Double.POSITIVE_INFINITY)))Assert.fail();
      dbl = DoubleOverflowToInfinity.Negate().ToDouble();
      if (!(((dbl) == Double.NEGATIVE_INFINITY)))Assert.fail();
      dbl = DoubleUnderflowToZero.ToDouble();
      if (!(dbl == 0.0)) {
 Assert.fail();
 }
      dbl = DoubleUnderflowToZero.Negate().ToDouble();
      if (!(dbl == 0.0)) {
 Assert.fail();
 }
      for (int i = 0; i < 10000; ++i) {
        EDecimal edec;
        if (fr.UniformInt(100) < 10) {
          String decimals = RandomObjects.RandomBigIntString(fr);
          if (decimals.charAt(0) == '-') {
            decimals = decimals.substring(1);
          }
          String edecstr = RandomObjects.RandomBigIntString(fr) +
            "." + decimals + "e" + RandomObjects.RandomBigIntString(fr);
          edec = EDecimal.FromString(edecstr);
        } else {
          edec = RandomObjects.RandomEDecimal(fr);
        }
        if (edec.isFinite()) {
          dbl = edec.ToDouble();
          if (((dbl) == Double.NEGATIVE_INFINITY)) {
            if (!(edec.isNegative())) {
 Assert.fail();
 }
  TestCommon.CompareTestGreaterEqual(edec.Abs(), DoubleOverflowToInfinity);
          } else if (((dbl) == Double.POSITIVE_INFINITY)) {
            if (!(!edec.isNegative())) {
 Assert.fail();
 }
  TestCommon.CompareTestGreaterEqual(edec.Abs(), DoubleOverflowToInfinity);
          } else if (dbl == 0.0) {
            TestCommon.CompareTestLessEqual(edec.Abs(), DoubleUnderflowToZero);
Assert.assertEquals(edec.isNegative(), EDecimal.FromDouble(dbl).isNegative());
          } else {
            if (!(!Double.isNaN(dbl))) {
 Assert.fail();
 }
            edec = edec.Abs();
            TestCommon.CompareTestGreater(edec, DoubleUnderflowToZero);
            TestCommon.CompareTestLess(edec, DoubleOverflowToInfinity);
            EDecimal halfUlp = GetHalfUlp(dbl);
            EDecimal difference = EDecimal.FromDouble(dbl).Abs()
              .Subtract(edec).Abs();
            TestCommon.CompareTestLessEqual(difference, halfUlp);
          }
        }
      }
    }
    @Test
    public void TestToEngineeringString() {
      String stringTemp;
      {
        stringTemp = EDecimal.FromString(
          "89.12E-1").ToEngineeringString();
        Assert.assertEquals(
          "8.912",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "242.31E-4").ToEngineeringString();
        Assert.assertEquals(
          "0.024231",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "229.18E5").ToEngineeringString();
        Assert.assertEquals(
          "22.918E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "326.18E-7").ToEngineeringString();
        Assert.assertEquals(
          "0.000032618",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
        "55.0E6").ToEngineeringString();
        Assert.assertEquals(
          "55.0E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "224.36E3").ToEngineeringString();
        Assert.assertEquals(
          "224.36E+3",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "230.12E9").ToEngineeringString();
        Assert.assertEquals(
          "230.12E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "113.20E-7").ToEngineeringString();
        Assert.assertEquals(
          "0.000011320",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "317.7E-9").ToEngineeringString();
        Assert.assertEquals(
          "317.7E-9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "339.3E-2").ToEngineeringString();
        Assert.assertEquals(
          "3.393",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "271.35E8").ToEngineeringString();
        Assert.assertEquals(
          "27.135E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "377.19E-9").ToEngineeringString();
        Assert.assertEquals(
          "377.19E-9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "321.27E7").ToEngineeringString();
        Assert.assertEquals(
          "3.2127E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "294.22E-2").ToEngineeringString();
        Assert.assertEquals(
          "2.9422",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "110.31E-8").ToEngineeringString();
        Assert.assertEquals(
          "0.0000011031",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "243.24E-2").ToEngineeringString();
        Assert.assertEquals(
          "2.4324",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "64.12E-5").ToEngineeringString();
        Assert.assertEquals(
          "0.0006412",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "142.23E1").ToEngineeringString();
        Assert.assertEquals(
          "1422.3",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "293.0E0").ToEngineeringString();
        Assert.assertEquals(
          "293.0",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "253.20E-8").ToEngineeringString();
        Assert.assertEquals(
          "0.0000025320",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "366.6E8").ToEngineeringString();
        Assert.assertEquals(
          "36.66E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "345.26E10").ToEngineeringString();
        Assert.assertEquals(
          "3.4526E+12",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "270.4E-2").ToEngineeringString();
        Assert.assertEquals(
          "2.704",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
        "4.32E8").ToEngineeringString();
        Assert.assertEquals(
          "432E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "224.22E0").ToEngineeringString();
        Assert.assertEquals(
          "224.22",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "315.30E-7").ToEngineeringString();
        Assert.assertEquals(
          "0.000031530",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "115.32E5").ToEngineeringString();
        Assert.assertEquals(
          "11.532E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "394.20E2").ToEngineeringString();
        Assert.assertEquals(
          "39420",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "67.24E-9").ToEngineeringString();
        Assert.assertEquals(
          "67.24E-9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "349.33E2").ToEngineeringString();
        Assert.assertEquals(
          "34933",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "67.8E-9").ToEngineeringString();
        Assert.assertEquals(
          "67.8E-9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "192.31E5").ToEngineeringString();
        Assert.assertEquals(
          "19.231E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "173.17E7").ToEngineeringString();
        Assert.assertEquals(
          "1.7317E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
        "43.9E0").ToEngineeringString();
        Assert.assertEquals(
          "43.9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "168.12E-8").ToEngineeringString();
        Assert.assertEquals(
          "0.0000016812",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "371.5E10").ToEngineeringString();
        Assert.assertEquals(
          "3.715E+12",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "42.4E-8").ToEngineeringString();
        Assert.assertEquals(
          "424E-9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "161.23E10").ToEngineeringString();
        Assert.assertEquals(
          "1.6123E+12",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "302.8E6").ToEngineeringString();
        Assert.assertEquals(
          "302.8E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "175.13E0").ToEngineeringString();
        Assert.assertEquals(
          "175.13",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "298.20E-9").ToEngineeringString();
        Assert.assertEquals(
          "298.20E-9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "362.23E8").ToEngineeringString();
        Assert.assertEquals(
          "36.223E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "277.39E2").ToEngineeringString();
        Assert.assertEquals(
          "27739",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "117.34E-4").ToEngineeringString();
        Assert.assertEquals(
          "0.011734",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "190.13E-9").ToEngineeringString();
        Assert.assertEquals(
          "190.13E-9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "350.19E-2").ToEngineeringString();
        Assert.assertEquals(
          "3.5019",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "383.27E-9").ToEngineeringString();
        Assert.assertEquals(
          "383.27E-9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "242.17E5").ToEngineeringString();
        Assert.assertEquals(
          "24.217E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "299.23E7").ToEngineeringString();
        Assert.assertEquals(
          "2.9923E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "302.22E-2").ToEngineeringString();
        Assert.assertEquals(
          "3.0222",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "45.21E-3").ToEngineeringString();
        Assert.assertEquals(
          "0.04521",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "150.0E-1").ToEngineeringString();
        Assert.assertEquals(
          "15.00",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
        "29.0E4").ToEngineeringString();
        Assert.assertEquals(
          "290E+3",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "263.37E3").ToEngineeringString();
        Assert.assertEquals(
          "263.37E+3",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "283.21E-1").ToEngineeringString();
        Assert.assertEquals(
          "28.321",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "21.32E0").ToEngineeringString();
        Assert.assertEquals(
          "21.32",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "69.20E-6").ToEngineeringString();
        Assert.assertEquals(
          "0.00006920",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "72.8E-3").ToEngineeringString();
        Assert.assertEquals(
          "0.0728",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "164.6E7").ToEngineeringString();
        Assert.assertEquals(
          "1.646E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "118.17E-2").ToEngineeringString();
        Assert.assertEquals(
          "1.1817",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "262.35E-7").ToEngineeringString();
        Assert.assertEquals(
          "0.000026235",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "233.7E5").ToEngineeringString();
        Assert.assertEquals(
          "23.37E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "391.24E0").ToEngineeringString();
        Assert.assertEquals(
          "391.24",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "221.36E1").ToEngineeringString();
        Assert.assertEquals(
          "2213.6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "353.32E0").ToEngineeringString();
        Assert.assertEquals(
          "353.32",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "129.31E-4").ToEngineeringString();
        Assert.assertEquals(
          "0.012931",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "176.26E-5").ToEngineeringString();
        Assert.assertEquals(
          "0.0017626",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "207.5E3").ToEngineeringString();
        Assert.assertEquals(
          "207.5E+3",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "314.10E0").ToEngineeringString();
        Assert.assertEquals(
          "314.10",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "379.20E9").ToEngineeringString();
        Assert.assertEquals(
          "379.20E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "379.12E-6").ToEngineeringString();
        Assert.assertEquals(
          "0.00037912",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "74.38E-8").ToEngineeringString();
        Assert.assertEquals(
          "743.8E-9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "234.17E-9").ToEngineeringString();
        Assert.assertEquals(
          "234.17E-9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "13.26E7").ToEngineeringString();
        Assert.assertEquals(
          "132.6E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "251.5E5").ToEngineeringString();
        Assert.assertEquals(
          "25.15E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "87.32E0").ToEngineeringString();
        Assert.assertEquals(
          "87.32",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "331.16E7").ToEngineeringString();
        Assert.assertEquals(
          "3.3116E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
        "61.4E8").ToEngineeringString();
        Assert.assertEquals(
          "6.14E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "209.7E-6").ToEngineeringString();
        Assert.assertEquals(
          "0.0002097",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
       "5.4E6").ToEngineeringString();
        Assert.assertEquals(
          "5.4E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "219.9E0").ToEngineeringString();
        Assert.assertEquals(
          "219.9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "26.31E-6").ToEngineeringString();
        Assert.assertEquals(
          "0.00002631",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "48.28E7").ToEngineeringString();
        Assert.assertEquals(
          "482.8E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "267.8E0").ToEngineeringString();
        Assert.assertEquals(
          "267.8",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "320.9E-3").ToEngineeringString();
        Assert.assertEquals(
          "0.3209",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "300.15E-3").ToEngineeringString();
        Assert.assertEquals(
          "0.30015",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "260.11E4").ToEngineeringString();
        Assert.assertEquals(
          "2.6011E+6",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "114.29E-2").ToEngineeringString();
        Assert.assertEquals(
          "1.1429",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "306.0E-6").ToEngineeringString();
        Assert.assertEquals(
          "0.0003060",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
        "97.7E3").ToEngineeringString();
        Assert.assertEquals(
          "97.7E+3",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "122.29E8").ToEngineeringString();
        Assert.assertEquals(
          "12.229E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
        "69.4E2").ToEngineeringString();
        Assert.assertEquals(
          "6.94E+3",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "383.5E0").ToEngineeringString();
        Assert.assertEquals(
          "383.5",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "315.30E3").ToEngineeringString();
        Assert.assertEquals(
          "315.30E+3",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "130.38E9").ToEngineeringString();
        Assert.assertEquals(
          "130.38E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "206.16E9").ToEngineeringString();
        Assert.assertEquals(
          "206.16E+9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "304.28E-9").ToEngineeringString();
        Assert.assertEquals(
          "304.28E-9",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
  "66.13E4").ToEngineeringString();
        Assert.assertEquals(
          "661.3E+3",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
          "185.33E-2").ToEngineeringString();
        Assert.assertEquals(
          "1.8533",
          stringTemp);
      }
      {
        stringTemp = EDecimal.FromString(
        "70.7E6").ToEngineeringString();
        Assert.assertEquals(
          "70.7E+6",
          stringTemp);
      }
    }
    @Test
    public void TestToEFloat() {
      Assert.assertEquals(
  EFloat.Zero,
  EDecimal.Zero.ToEFloat());
      Assert.assertEquals(
        EFloat.NegativeZero,
        EDecimal.NegativeZero.ToEFloat());
      if (EFloat.Zero.ToSingle() != 0.0f) {
        Assert.fail("Failed " + EFloat.Zero.ToDouble());
      }
      if (EFloat.Zero.ToDouble() != 0.0f) {
        Assert.fail("Failed " + EFloat.Zero.ToDouble());
      }
      EDecimal df;
      df = EDecimal.FromInt64(20);
      {
        String stringTemp = df.ToEFloat().toString();
        Assert.assertEquals(
        "20",
        stringTemp);
      }
      df = EDecimal.FromInt64(-20);
      {
        String stringTemp = df.ToEFloat().toString();
        Assert.assertEquals(
        "-20",
        stringTemp);
      }
      df = EDecimal.Create(EInteger.FromInt64(15), EInteger.FromInt64(-1));
      {
        String stringTemp = df.ToEFloat().toString();
        Assert.assertEquals(
        "1.5",
        stringTemp);
      }
      df = EDecimal.Create(EInteger.FromInt64(-15), EInteger.FromInt64(-1));
      {
        String stringTemp = df.ToEFloat().toString();
        Assert.assertEquals(
        "-1.5",
        stringTemp);
      }
    }
    @Test
    public void TestToPlainString() {
      {
        String stringTemp = EDecimal.NegativeZero.ToPlainString();
        Assert.assertEquals(
        "-0",
        stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "277.22E9").ToPlainString();
        Assert.assertEquals(
          "277220000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "391.19E4").ToPlainString();
        Assert.assertEquals(
          "3911900",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "383.27E-9").ToPlainString();
        Assert.assertEquals(
          "0.00000038327",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "47.33E9").ToPlainString();
        Assert.assertEquals(
          "47330000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "322.21E3").ToPlainString();
        Assert.assertEquals(
          "322210",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "191.3E-2").ToPlainString();
        Assert.assertEquals(
          "1.913",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "119.17E2").ToPlainString();
        Assert.assertEquals(
          "11917",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "159.6E-6").ToPlainString();
        Assert.assertEquals(
          "0.0001596",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "70.16E9").ToPlainString();
        Assert.assertEquals(
          "70160000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "166.24E9").ToPlainString();
        Assert.assertEquals(
          "166240000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "235.25E3").ToPlainString();
        Assert.assertEquals(
          "235250",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "37.22E7").ToPlainString();
        Assert.assertEquals(
          "372200000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "320.26E8").ToPlainString();
        Assert.assertEquals(
          "32026000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "127.11E-9").ToPlainString();
        Assert.assertEquals(
          "0.00000012711",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "97.29E-7").ToPlainString();
        Assert.assertEquals(
          "0.000009729",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "175.13E9").ToPlainString();
        Assert.assertEquals(
          "175130000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "38.21E-7").ToPlainString();
        Assert.assertEquals(
          "0.000003821",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
  "6.28E1").ToPlainString();
        Assert.assertEquals(
          "62.8",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "138.29E6").ToPlainString();
        Assert.assertEquals(
          "138290000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "160.19E1").ToPlainString();
        Assert.assertEquals(
          "1601.9",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "358.12E2").ToPlainString();
        Assert.assertEquals(
          "35812",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "249.28E10").ToPlainString();
        Assert.assertEquals(
          "2492800000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "311.23E-6").ToPlainString();
        Assert.assertEquals(
          "0.00031123",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "164.33E-3").ToPlainString();
        Assert.assertEquals(
          "0.16433",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "299.20E-1").ToPlainString();
        Assert.assertEquals(
          "29.920",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "105.39E3").ToPlainString();
        Assert.assertEquals(
          "105390",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "382.5E4").ToPlainString();
        Assert.assertEquals(
          "3825000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
  "90.9E1").ToPlainString();
        Assert.assertEquals(
          "909",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "329.15E8").ToPlainString();
        Assert.assertEquals(
          "32915000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "245.23E8").ToPlainString();
        Assert.assertEquals(
          "24523000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "97.19E-8").ToPlainString();
        Assert.assertEquals(
          "0.0000009719",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "55.12E7").ToPlainString();
        Assert.assertEquals(
          "551200000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "12.38E2").ToPlainString();
        Assert.assertEquals(
          "1238",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "250.20E-5").ToPlainString();
        Assert.assertEquals(
          "0.0025020",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "53.20E2").ToPlainString();
        Assert.assertEquals(
          "5320",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "141.5E8").ToPlainString();
        Assert.assertEquals(
          "14150000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "338.34E-5").ToPlainString();
        Assert.assertEquals(
          "0.0033834",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "160.39E9").ToPlainString();
        Assert.assertEquals(
          "160390000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "152.17E6").ToPlainString();
        Assert.assertEquals(
          "152170000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
  "13.3E9").ToPlainString();
        Assert.assertEquals(
          "13300000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
  "1.38E1").ToPlainString();
        Assert.assertEquals(
          "13.8",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "348.21E-9").ToPlainString();
        Assert.assertEquals(
          "0.00000034821",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
  "52.5E7").ToPlainString();
        Assert.assertEquals(
          "525000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "215.21E10").ToPlainString();
        Assert.assertEquals(
          "2152100000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "234.28E9").ToPlainString();
        Assert.assertEquals(
          "234280000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "310.24E9").ToPlainString();
        Assert.assertEquals(
          "310240000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "345.39E9").ToPlainString();
        Assert.assertEquals(
          "345390000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "116.38E-9").ToPlainString();
        Assert.assertEquals(
          "0.00000011638",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "276.25E10").ToPlainString();
        Assert.assertEquals(
          "2762500000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "158.32E-8").ToPlainString();
        Assert.assertEquals(
          "0.0000015832",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "272.5E2").ToPlainString();
        Assert.assertEquals(
          "27250",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "389.33E-9").ToPlainString();
        Assert.assertEquals(
          "0.00000038933",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "381.15E7").ToPlainString();
        Assert.assertEquals(
          "3811500000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "280.0E3").ToPlainString();
        Assert.assertEquals(
          "280000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "274.2E-6").ToPlainString();
        Assert.assertEquals(
          "0.0002742",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "387.14E-7").ToPlainString();
        Assert.assertEquals(
          "0.000038714",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "227.7E-7").ToPlainString();
        Assert.assertEquals(
          "0.00002277",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "201.21E2").ToPlainString();
        Assert.assertEquals(
          "20121",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "255.4E3").ToPlainString();
        Assert.assertEquals(
          "255400",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "187.27E-7").ToPlainString();
        Assert.assertEquals(
          "0.000018727",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "169.7E-4").ToPlainString();
        Assert.assertEquals(
          "0.01697",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
  "69.9E9").ToPlainString();
        Assert.assertEquals(
          "69900000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "3.20E-2").ToPlainString();
        Assert.assertEquals(
          "0.0320",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "236.30E2").ToPlainString();
        Assert.assertEquals(
          "23630",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "220.22E-9").ToPlainString();
        Assert.assertEquals(
          "0.00000022022",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "287.30E-1").ToPlainString();
        Assert.assertEquals(
          "28.730",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "156.3E-9").ToPlainString();
        Assert.assertEquals(
          "0.0000001563",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "136.23E-1").ToPlainString();
        Assert.assertEquals(
          "13.623",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "125.27E8").ToPlainString();
        Assert.assertEquals(
          "12527000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "180.30E-7").ToPlainString();
        Assert.assertEquals(
          "0.000018030",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "351.5E7").ToPlainString();
        Assert.assertEquals(
          "3515000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "28.28E9").ToPlainString();
        Assert.assertEquals(
          "28280000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "288.4E-3").ToPlainString();
        Assert.assertEquals(
          "0.2884",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "12.22E4").ToPlainString();
        Assert.assertEquals(
          "122200",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "257.5E-5").ToPlainString();
        Assert.assertEquals(
          "0.002575",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "389.20E3").ToPlainString();
        Assert.assertEquals(
          "389200",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "394.9E-4").ToPlainString();
        Assert.assertEquals(
          "0.03949",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "134.26E-7").ToPlainString();
        Assert.assertEquals(
          "0.000013426",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "58.29E5").ToPlainString();
        Assert.assertEquals(
          "5829000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "88.5E-5").ToPlainString();
        Assert.assertEquals(
          "0.000885",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "193.29E-4").ToPlainString();
        Assert.assertEquals(
          "0.019329",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "71.35E10").ToPlainString();
        Assert.assertEquals(
          "713500000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "252.0E1").ToPlainString();
        Assert.assertEquals(
          "2520",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "53.2E-8").ToPlainString();
        Assert.assertEquals(
          "0.000000532",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "181.20E-1").ToPlainString();
        Assert.assertEquals(
          "18.120",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "55.21E-9").ToPlainString();
        Assert.assertEquals(
          "0.00000005521",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "57.31E0").ToPlainString();
        Assert.assertEquals(
          "57.31",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "113.13E-9").ToPlainString();
        Assert.assertEquals(
          "0.00000011313",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "53.23E1").ToPlainString();
        Assert.assertEquals(
          "532.3",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "368.37E-7").ToPlainString();
        Assert.assertEquals(
          "0.000036837",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "187.4E-4").ToPlainString();
        Assert.assertEquals(
          "0.01874",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
  "5.26E8").ToPlainString();
        Assert.assertEquals(
          "526000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "308.32E4").ToPlainString();
        Assert.assertEquals(
          "3083200",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "76.15E-2").ToPlainString();
        Assert.assertEquals(
          "0.7615",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "117.38E7").ToPlainString();
        Assert.assertEquals(
          "1173800000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "15.37E-4").ToPlainString();
        Assert.assertEquals(
          "0.001537",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
   "145.3E0").ToPlainString();
        Assert.assertEquals(
          "145.3",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
    "226.29E8").ToPlainString();
        Assert.assertEquals(
          "22629000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "224.26E10").ToPlainString();
        Assert.assertEquals(
          "2242600000000",
          stringTemp);
      }
      {
        String stringTemp = EDecimal.FromString(
     "268.18E-9").ToPlainString();
        Assert.assertEquals(
          "0.00000026818",
          stringTemp);
      }
    }
    @Test
    public void TestToSingle() {
      RandomGenerator fr = new RandomGenerator();
      float sng;
      sng = SingleOverflowToInfinity.ToSingle();
      if (!(((sng) == Float.POSITIVE_INFINITY)))Assert.fail();
      sng = SingleOverflowToInfinity.Negate().ToSingle();
      if (!(((sng) == Float.NEGATIVE_INFINITY)))Assert.fail();
      sng = SingleUnderflowToZero.ToSingle();
      if (!(sng == 0.0)) {
 Assert.fail();
 }
      sng = SingleUnderflowToZero.Negate().ToSingle();
      if (!(sng == 0.0)) {
 Assert.fail();
 }
      for (int i = 0; i < 10000; ++i) {
        EDecimal edec;
        if (fr.UniformInt(100) < 10) {
          String decimals = RandomObjects.RandomBigIntString(fr);
          if (decimals.charAt(0) == '-') {
            decimals = decimals.substring(1);
          }
          String edecstr = RandomObjects.RandomBigIntString(fr) +
            "." + decimals + "e" + RandomObjects.RandomBigIntString(fr);
          edec = EDecimal.FromString(edecstr);
        } else {
          edec = RandomObjects.RandomEDecimal(fr);
        }
        if (edec.isFinite()) {
          sng = edec.ToSingle();
          if (((sng) == Float.NEGATIVE_INFINITY)) {
            if (!(edec.isNegative())) {
 Assert.fail();
 }
  TestCommon.CompareTestGreaterEqual(edec.Abs(), SingleOverflowToInfinity);
          } else if (((sng) == Float.POSITIVE_INFINITY)) {
            if (!(!edec.isNegative())) {
 Assert.fail();
 }
  TestCommon.CompareTestGreaterEqual(edec.Abs(), SingleOverflowToInfinity);
          } else if (sng == 0.0f) {
            TestCommon.CompareTestLessEqual(edec.Abs(), SingleUnderflowToZero);
Assert.assertEquals(edec.isNegative(), EDecimal.FromSingle(sng).isNegative());
          } else {
            if (!(!Float.isNaN(sng))) {
 Assert.fail();
 }
            edec = edec.Abs();
            TestCommon.CompareTestGreater(edec, SingleUnderflowToZero);
            TestCommon.CompareTestLess(edec, SingleOverflowToInfinity);
            EDecimal halfUlp = GetHalfUlp(sng);
            EDecimal difference = EDecimal.FromSingle(sng).Abs()
              .Subtract(edec).Abs();
            TestCommon.CompareTestLessEqual(difference, halfUlp);
          }
        }
      }
    }

    @Test
    public void TestToString() {
      for (int i = 0; i < ValueTestStrings.length; i += 4) {
        Assert.assertEquals(
  ValueTestStrings[i + 1],
  EDecimal.FromString(ValueTestStrings[i]).toString());
        Assert.assertEquals(
  ValueTestStrings[i + 2],
  EDecimal.FromString(ValueTestStrings[i]).ToEngineeringString());
        Assert.assertEquals(
  ValueTestStrings[i + 3],
  EDecimal.FromString(ValueTestStrings[i]).ToPlainString());
      }
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        // Generate arbitrary-precision integers for exponent
        // and mantissa
        EInteger mantBig = RandomObjects.RandomEInteger(fr);
        EInteger expBig = RandomObjects.RandomEInteger(fr);
        EDecimal dec = EDecimal.Create(mantBig, expBig);
        ExtraTest.TestStringEqualRoundTrip(dec);
      }
      for (int i = 0; i < 1000; ++i) {
        EDecimal dec = RandomObjects.RandomEDecimal(fr);
        ExtraTest.TestStringEqualRoundTrip(dec);
      }
    }
    @Test
    public void TestUnsignedMantissa() {
      // not implemented yet
    }
    @Test
    public void TestZero() {
      Assert.assertEquals(EDecimal.Zero, EDecimal.FromInt32(0));
      if (EDecimal.Zero.ToSingle() != 0.0) {
        Assert.fail("Failed " + EDecimal.Zero.ToSingle());
      }
      if (EDecimal.Zero.ToDouble() != 0.0) {
        Assert.fail("Failed " + EDecimal.Zero.ToDouble());
      }
    }

    private static void AssertAddSubtract(String a, String b) {
      EDecimal decA = EDecimal.FromString(a);
      EDecimal decB = EDecimal.FromString(b);
      EDecimal decC = decA.Add(decB);
      EDecimal decD = decC.Subtract(decA);
      TestCommon.CompareTestEqual(decD, decB);
      decD = decC.Subtract(decB);
      TestCommon.CompareTestEqual(decD, decA);
    }

    private static void AssertDecimalsEquivalent(String a, String b) {
      EDecimal ca = EDecimal.FromString(a);
      EDecimal cb = EDecimal.FromString(b);
      TestCommon.CompareTestEqual(ca, cb);
    }

    private static void TestEDecimalDoubleCore(double d, String s) {
      double oldd = d;
      EDecimal bf = EDecimal.FromDouble(d);
      if (s != null) {
        Assert.assertEquals(s, bf.toString());
      }
      d = bf.ToDouble();
      Assert.assertEquals((double)oldd, d, 0);
    }

    private static void TestEDecimalSingleCore(float d, String s) {
      float oldd = d;
      EDecimal bf = EDecimal.FromSingle(d);
      if (s != null) {
        Assert.assertEquals(s, bf.toString());
      }
      d = bf.ToSingle();
      Assert.assertEquals((float)oldd, d, 0f);
    }

    private void TestRoundToExponentOne(
      String input,
      String expected,
      int exponent,
      ERounding rounding) {
      EDecimal inputED = EDecimal.FromString(input);
      inputED = inputED.RoundToExponent(
  exponent,
  EContext.ForRounding(rounding));
      Assert.assertEquals(expected, inputED.toString());
    }
  }
