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
      "0.0000000000000", };
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
    public void TestFromBoolean() {
      Assert.assertEquals(EDecimal.Zero, EDecimal.FromBoolean(false));
      Assert.assertEquals(EDecimal.One, EDecimal.FromBoolean(true));
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
      String str;
      str =

  "1766847170502052161990715830264538670879951287225036514637396697134727424";
      ef = EFloat.Create(
        EInteger.FromString(str),
        EInteger.FromString("-312166824097095580"));
      str =

  "9.173994463968662338877236893297097756859177826848079536001717300706132083132181223420891571892014689615873E-411";
      ed = EDecimal.FromString(str);
      Assert.assertEquals(ed.toString(), 1, ed.CompareToBinary(ef));
      ed = EDecimal.FromString("-0.8686542656448184");
      EInteger num = EInteger.FromString(
  "-140066031252330072924596216562033152419723178072587092376847513280411121126147871380984127579961289495006067586678128473926216639728812381688517268223431349786843141449122136993998169636988109708853983609451615499412285220750795244924615776386873830928453488263516664209329914433973932921432682935336466252311348743988191166143");

      ef = EFloat.Create(
         num,
         EInteger.FromString("-6881037062769847"));
      Assert.assertEquals(ed.toString(), -1, ed.CompareToBinary(ef));
    }
    @Test(timeout = 1000)
    public void TestSlowCompareTo4() {
      EInteger eim =
        EInteger.FromString("22387857484482745027162156293292508271673600");
      EInteger eie = EInteger.FromString("17968626318971");
      EDecimal ed =
  EDecimal.FromString("7.19575518693181831266567929996929334493885016E+432");
      EFloat ef = EFloat.Create(eim, eie);
      Assert.assertEquals(-1, ed.CompareToBinary(ef));
      eim = EInteger.FromString("309485028268090241945960705");
      eie = EInteger.FromString("525342875590");
      ed =

  EDecimal.FromString("9.511414777277089412154948033116658722787183213120804541938141882272749696679385407387275461761800238977533242480831603777061215911374370925809077057683501541910383022943115134850573547079633633752563620027531228739865573373209036911484539031800435471797748936642897560822226476374652683917217409048036924712889788014206259609E+676");
      ef = EFloat.Create(eim, eie);
      Assert.assertEquals(-1, ed.CompareToBinary(ef));
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
      EContext ep = EContext.ForPrecision(15);
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0356314114802513"),
        EDecimal.FromString("1.03627381745706").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0373968817623155"),
        EDecimal.FromString("1.03810494401").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0432827364772243"),
        EDecimal.FromString("1.04423309590353").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0186600055430932"),
        EDecimal.FromString("1.0188351914064").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00500175918648486"),
        EDecimal.FromString("1.0050142888654").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0108398808589417"),
        EDecimal.FromString("1.01089884523045").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0309712776627391"),
        EDecimal.FromString("1.03145587763747").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0460771519682849"),
        EDecimal.FromString("1.04715519792508").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0470428765486254"),
        EDecimal.FromString("1.04816694989739").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0438768736300122"),
        EDecimal.FromString("1.04485369792508").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0509086560712933"),
        EDecimal.FromString("1.05222677436587").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0444493069875580"),
        EDecimal.FromString("1.045451978257").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00572014245865502"),
        EDecimal.FromString("1.00573653371206").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0603302139066789"),
        EDecimal.FromString("1.062187237638").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0343944133103810"),
        EDecimal.FromString("1.03499274114926").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0484212311476323"),
        EDecimal.FromString("1.0496126917769").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0592259839831431"),
        EDecimal.FromString("1.06101498604256").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0314193739053837"),
        EDecimal.FromString("1.0319181727093").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0325454895096346"),
        EDecimal.FromString("1.03308088641805").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0320556021843019"),
        EDecimal.FromString("1.03257491712984").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0180919740442453"),
        EDecimal.FromString("1.018256625263").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0161069675572072"),
        EDecimal.FromString("1.01623738402289").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0362618222337890"),
        EDecimal.FromString("1.03692730157526").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0160279003290006"),
        EDecimal.FromString("1.01615703612622").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0405031376223747"),
        EDecimal.FromString("1.04133457701206").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0259640429170119"),
        EDecimal.FromString("1.02630404491026").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00462071528198663"),
        EDecimal.FromString("1.00463140724868").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0253265490857446"),
        EDecimal.FromString("1.02564999091247").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0428626803402336"),
        EDecimal.FromString("1.04379455149623").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00505974482383953"),
        EDecimal.FromString("1.00507256694912").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0253477466357082"),
        EDecimal.FromString("1.02567173240983").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0103593452031559"),
        EDecimal.FromString("1.01041318898784").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0577930967778597"),
        EDecimal.FromString("1.0594957599442").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0605510803783060"),
        EDecimal.FromString("1.0624218650951").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0515379232818110"),
        EDecimal.FromString("1.05288911454563").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0398295508760999"),
        EDecimal.FromString("1.04063338402621").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0572552494980222"),
        EDecimal.FromString("1.05892606624955").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0599191352755233"),
        EDecimal.FromString("1.06175068489722").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0145302269052378"),
        EDecimal.FromString("1.01463630380329").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00827384686550294"),
        EDecimal.FromString("1.00830816973189").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0139072652510642"),
        EDecimal.FromString("1.01400442113286").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0178067331558986"),
        EDecimal.FromString("1.01796621825859").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00482032967856655"),
        EDecimal.FromString("1.00483196615738").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0578599288803556"),
        EDecimal.FromString("1.05956657063961").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0158547374442843"),
        EDecimal.FromString("1.01598109067657").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0580281929921016"),
        EDecimal.FromString("1.05974487266795").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0342876100056156"),
        EDecimal.FromString("1.03488220640694").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0399845771432218"),
        EDecimal.FromString("1.04079472204067").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0419575406922623"),
        EDecimal.FromString("1.0428501991132").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0199289914732731"),
        EDecimal.FromString("1.02012889960461").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00542054813233849"),
        EDecimal.FromString("1.00543526588411").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0570409917281523"),
        EDecimal.FromString("1.05869920741614").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0359519758988480"),
        EDecimal.FromString("1.0366060632211").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0183245634433442"),
        EDecimal.FromString("1.01849348850447").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0522431598259170"),
        EDecimal.FromString("1.05363191231938").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0310314583340598"),
        EDecimal.FromString("1.03151795321248").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0508903146419096"),
        EDecimal.FromString("1.05220747519978").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0578049332833509"),
        EDecimal.FromString("1.0595083007458").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00664017298556502"),
        EDecimal.FromString("1.00666226781162").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00583583924023100"),
        EDecimal.FromString("1.00585290092365").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0550347874338319"),
        EDecimal.FromString("1.05657736965152").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0380360917143104"),
        EDecimal.FromString("1.03876872314594").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0271897108319056"),
        EDecimal.FromString("1.02756272405278").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0392648227562750"),
        EDecimal.FromString("1.04004587499887").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0372366472327774"),
        EDecimal.FromString("1.0379386170787").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0404472225234041"),
        EDecimal.FromString("1.04127635231396").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0350974091376694"),
        EDecimal.FromString("1.03572059253583").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0572800995381237"),
        EDecimal.FromString("1.05895238093172").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0201930356261252"),
        EDecimal.FromString("1.02039829424018").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0107178898731536"),
        EDecimal.FromString("1.01077553220548").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00807683612290659"),
        EDecimal.FromString("1.00810954175717").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0155859742417655"),
        EDecimal.FromString("1.01570806903567").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0434516450545831"),
        EDecimal.FromString("1.04440949072707").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0339503658926824"),
        EDecimal.FromString("1.03453325731907").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0264080018658522"),
        EDecimal.FromString("1.02675978293223").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0254816863021023"),
        EDecimal.FromString("1.0258091197401").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0145924359812063"),
        EDecimal.FromString("1.01469942535354").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0464483851104622"),
        EDecimal.FromString("1.04754400880483").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0223786774198137"),
        EDecimal.FromString("1.02263095841155").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0301739282409140"),
        EDecimal.FromString("1.03063377468501").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0162656858886543"),
        EDecimal.FromString("1.01639869232579").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0198575062820163"),
        EDecimal.FromString("1.02005597810155").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0188074683804394"),
        EDecimal.FromString("1.01898544281249").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0298259314035765"),
        EDecimal.FromString("1.03027517978953").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0508967122084692"),
        EDecimal.FromString("1.05221420678867").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00913898768426632"),
        EDecimal.FromString("1.00918087573978").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0186891166558291"),
        EDecimal.FromString("1.01886485126423").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0501736496212947"),
        EDecimal.FromString("1.05145366505482").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0294809997760305"),
        EDecimal.FromString("1.02991986657785").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0514278823353069"),
        EDecimal.FromString("1.05277326000539").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0378825222093977"),
        EDecimal.FromString("1.03860921219573").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0293200913096772"),
        EDecimal.FromString("1.02975415708404").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0382277777577714"),
        EDecimal.FromString("1.03896785969779").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0345672111480830"),
        EDecimal.FromString("1.03517160110983").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0116716248862378"),
        EDecimal.FromString("1.0117400040731").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0418029390817057"),
        EDecimal.FromString("1.04268898525513").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0468616948323439"),
        EDecimal.FromString("1.04797705841341").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0215961114579878"),
        EDecimal.FromString("1.02183099528477").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0493424207510331"),
        EDecimal.FromString("1.05058002955851").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0474205073016784"),
        EDecimal.FromString("1.04856284471833").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0423627006747925"),
        EDecimal.FromString("1.04327280588755").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0132474317089713"),
        EDecimal.FromString("1.0133355676942").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0386033035992894"),
        EDecimal.FromString("1.03935809224423").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0318367117055694"),
        EDecimal.FromString("1.032348921047").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0344304387279775"),
        EDecimal.FromString("1.0350300278666").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0279341520766204"),
        EDecimal.FromString("1.02832796893068").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0554725486612969"),
        EDecimal.FromString("1.05703999951109").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0285655181991532"),
        EDecimal.FromString("1.02897742537394").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0132050787889902"),
        EDecimal.FromString("1.01329265088282").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0253024029674372"),
        EDecimal.FromString("1.02562522574544").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0524107886123178"),
        EDecimal.FromString("1.0538085461622").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0262063853941794"),
        EDecimal.FromString("1.02655279211462").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0423730132932208"),
        EDecimal.FromString("1.04328356481739").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0368585261620800"),
        EDecimal.FromString("1.03754622480806").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0346371146724833"),
        EDecimal.FromString("1.03524396578235").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0257704590760202"),
        EDecimal.FromString("1.0261053882602").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0274653213595895"),
        EDecimal.FromString("1.0278459701884").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0245148497490255"),
        EDecimal.FromString("1.02481780928147").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0335460458269518"),
        EDecimal.FromString("1.03411505931309").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0502866812476162"),
        EDecimal.FromString("1.0515725192896").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0224088625089816"),
        EDecimal.FromString("1.0226618270841").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0554132201720877"),
        EDecimal.FromString("1.05697728878517").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0536274554738816"),
        EDecimal.FromString("1.0550914603801").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0268504089253829"),
        EDecimal.FromString("1.02721412920423").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0365065186559171"),
        EDecimal.FromString("1.0371810650222").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0343615913562628"),
        EDecimal.FromString("1.03495877122248").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0221394567453604"),
        EDecimal.FromString("1.02238635320244").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0448865348593958"),
        EDecimal.FromString("1.04590917894372").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0166381300594860"),
        EDecimal.FromString("1.01677731459744").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0195832902936564"),
        EDecimal.FromString("1.01977630079108").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0228850234010125"),
        EDecimal.FromString("1.02314889460398").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0132201491015593"),
        EDecimal.FromString("1.01330792163486").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00958191940352785"),
        EDecimal.FromString("1.00962797296958").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00938873987111145"),
        EDecimal.FromString("1.00943295234745").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0384002814483168"),
        EDecimal.FromString("1.03914710094739").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0371942157773529"),
        EDecimal.FromString("1.03789457676689").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0513162668731550"),
        EDecimal.FromString("1.05265576078892").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0341544495137175"),
        EDecimal.FromString("1.03474441015799").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0289057937835930"),
        EDecimal.FromString("1.02932762084684").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0291484693470790"),
        EDecimal.FromString("1.02957744381898").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0125765698735498"),
        EDecimal.FromString("1.01265598751305").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0561107088524904"),
        EDecimal.FromString("1.05771477564401").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0128797139754467"),
        EDecimal.FromString("1.01296301473731").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0374009111468270"),
        EDecimal.FromString("1.03810912694241").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0240869397759103"),
        EDecimal.FromString("1.0243793733325").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0475273484738502"),
        EDecimal.FromString("1.04867488038666").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0534301916571227"),
        EDecimal.FromString("1.05488334953864").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0496325892147524"),
        EDecimal.FromString("1.05088491898421").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0212937814978126"),
        EDecimal.FromString("1.02152211185537").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0337434618884044"),
        EDecimal.FromString("1.03431923038785").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0493462384346567"),
        EDecimal.FromString("1.05058404034834").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0292205178304847"),
        EDecimal.FromString("1.02965162598468").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0264635472374315"),
        EDecimal.FromString("1.02681681626985").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00899916868710895"),
        EDecimal.FromString("1.00903978294574").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0386853436767682"),
        EDecimal.FromString("1.03944336476048").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0355223843395444"),
        EDecimal.FromString("1.03616084164458").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00945633136996858"),
        EDecimal.FromString("1.0095011837396").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0432291909759528"),
        EDecimal.FromString("1.04417718341591").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0111183711966097"),
        EDecimal.FromString("1.01118040999592").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0557434242121982"),
        EDecimal.FromString("1.05732636458618").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0455547416493869"),
        EDecimal.FromString("1.04660829611021").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0376540924445765"),
        EDecimal.FromString("1.03837199003298").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0346437608769558"),
        EDecimal.FromString("1.03525084624829").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00913935775823410"),
        EDecimal.FromString("1.00918124921142").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0127641437670947"),
        EDecimal.FromString("1.01284595315519").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0529683597893979"),
        EDecimal.FromString("1.05439628327111").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0221100207370752"),
        EDecimal.FromString("1.02235625867221").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0488807035146789"),
        EDecimal.FromString("1.05009507061626").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0601167853282098"),
        EDecimal.FromString("1.06196056071633").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0122570332871866"),
        EDecimal.FromString("1.01233245856806").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0155960462545241"),
        EDecimal.FromString("1.01571829931182").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00622972059858053"),
        EDecimal.FromString("1.00624916566609").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0307752274338741"),
        EDecimal.FromString("1.03125368029766").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0419807789503076"),
        EDecimal.FromString("1.04287443341681").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0338293732947807"),
        EDecimal.FromString("1.03440809402472").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0572722988295292"),
        EDecimal.FromString("1.058944120385").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0200516535156747"),
        EDecimal.FromString("1.02025403837368").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0592830979550339"),
        EDecimal.FromString("1.0610755865532").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0354811646816581"),
        EDecimal.FromString("1.03611813232941").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0340066263995749"),
        EDecimal.FromString("1.03459146232183").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0270483252933606"),
        EDecimal.FromString("1.02741745181359").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0356893924541261"),
        EDecimal.FromString("1.0363339033641").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0404953577632950"),
        EDecimal.FromString("1.04132647560731").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0226046518596446"),
        EDecimal.FromString("1.02286207298154").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0445515865720168"),
        EDecimal.FromString("1.04555891211939").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0495278723336211"),
        EDecimal.FromString("1.05077487935467").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0382102764689060"),
        EDecimal.FromString("1.03894967658027").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0539973509991736"),
        EDecimal.FromString("1.0554818061792").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0330979948401649"),
        EDecimal.FromString("1.03365182682395").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0206178886454442"),
        EDecimal.FromString("1.02083190564043").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0229221295249334"),
        EDecimal.FromString("1.02318686039803").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0256425259392493"),
        EDecimal.FromString("1.02597412377594").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00722799525141007"),
        EDecimal.FromString("1.00725418025944").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0507013206332150"),
        EDecimal.FromString("1.05200863308164").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0257853448654805"),
        EDecimal.FromString("1.02612066276266").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0548470267532319"),
        EDecimal.FromString("1.05637900458865").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0193759450960889"),
        EDecimal.FromString("1.01956487699213").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0268129763962236"),
        EDecimal.FromString("1.02717567870104").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0149992326829050"),
        EDecimal.FromString("1.01511228570241").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0489727966196631"),
        EDecimal.FromString("1.05019178158498").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0139291355804347"),
        EDecimal.FromString("1.01402659798604").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00990288241857822"),
        EDecimal.FromString("1.00995207821798").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0587081439716914"),
        EDecimal.FromString("1.06046569226546").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0228488236363584"),
        EDecimal.FromString("1.02311185752516").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0236704461152745"),
        EDecimal.FromString("1.02395281465308").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0535344921138693"),
        EDecimal.FromString("1.05499338009183").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0378690305270770"),
        EDecimal.FromString("1.03859519970471").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0223651866829105"),
        EDecimal.FromString("1.0226171624594").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0250044600761967"),
        EDecimal.FromString("1.02531969351819").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0346263830076382"),
        EDecimal.FromString("1.03523285595069").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0558379211320719"),
        EDecimal.FromString("1.05742628339187").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0528457512748796"),
        EDecimal.FromString("1.05426701323407").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0313517636558056"),
        EDecimal.FromString("1.03184840682257").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0602757092557191"),
        EDecimal.FromString("1.06212934507108").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0475276420025697"),
        EDecimal.FromString("1.0486751882029").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0293359094313700"),
        EDecimal.FromString("1.02977044598944").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0255456308995539"),
        EDecimal.FromString("1.02587471678859").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0378348521697383"),
        EDecimal.FromString("1.03855970283346").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0420316537612427"),
        EDecimal.FromString("1.04292749080607").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0531118647807092"),
        EDecimal.FromString("1.05454760525805").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0139513348460979"),
        EDecimal.FromString("1.01404910888174").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0598898362949834"),
        EDecimal.FromString("1.06171957714028").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0432619332453575"),
        EDecimal.FromString("1.04421137270627").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0521744648316097"),
        EDecimal.FromString("1.05355953556715").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0265999583641338"),
        EDecimal.FromString("1.02695689506261").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0585722388948663"),
        EDecimal.FromString("1.06032157938714").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0381787864057048"),
        EDecimal.FromString("1.03891696050441").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00848147077249274"),
        EDecimal.FromString("1.00851754034796").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0554700566186747"),
        EDecimal.FromString("1.05703736532564").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0602304733787649"),
        EDecimal.FromString("1.06208129980541").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0244300571900407"),
        EDecimal.FromString("1.02473091604093").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0443888581923993"),
        EDecimal.FromString("1.04538878385455").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0266872984330656"),
        EDecimal.FromString("1.02704659346569").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00994151173482433"),
        EDecimal.FromString("1.00999109272975").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0262222473713547"),
        EDecimal.FromString("1.02656907540072").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0503250075304938"),
        EDecimal.FromString("1.05161282292778").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0195772899506269"),
        EDecimal.FromString("1.01977018180182").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0433167913427868"),
        EDecimal.FromString("1.04426865772675").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0504712596822756"),
        EDecimal.FromString("1.05176663481336").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0172939313050412"),
        EDecimal.FromString("1.01744433712").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0113918810533540"),
        EDecimal.FromString("1.01145701563046").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0454495635781257"),
        EDecimal.FromString("1.04649822165707").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0267085378809636"),
        EDecimal.FromString("1.02706840759996").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0140151931743785"),
        EDecimal.FromString("1.01411386643026").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0199625521839445"),
        EDecimal.FromString("1.02016313642996").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0365904826229605"),
        EDecimal.FromString("1.0372681545151").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0417388586889094"),
        EDecimal.FromString("1.04262217147614").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0386111577453658"),
        EDecimal.FromString("1.03936625554657").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00766204452916127"),
        EDecimal.FromString("1.00769147310535").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0490906834211789"),
        EDecimal.FromString("1.05031559263279").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0209210552388835"),
        EDecimal.FromString("1.0211414346888").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0414253174737055"),
        EDecimal.FromString("1.04229531769724").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0124819797739008"),
        EDecimal.FromString("1.0125602048124").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0499441202285322"),
        EDecimal.FromString("1.0512123532287").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0542955026266178"),
        EDecimal.FromString("1.05579654671532").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0410082568189865"),
        EDecimal.FromString("1.04186070796524").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0150802953359922"),
        EDecimal.FromString("1.01519457673279").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0192815863826414"),
        EDecimal.FromString("1.0194686767008").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0603784061121182"),
        EDecimal.FromString("1.06223842801705").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00629364182863192"),
        EDecimal.FromString("1.00631348840627").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0315591587838764"),
        EDecimal.FromString("1.0320624293479").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0216608761830936"),
        EDecimal.FromString("1.02189717603135").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0218424262913541"),
        EDecimal.FromString("1.0220827184164").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0490424819101668"),
        EDecimal.FromString("1.05026496705431").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0286899433931071"),
        EDecimal.FromString("1.02910546405512").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0255175950595838"),
        EDecimal.FromString("1.02584595593237").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0113043712633683"),
        EDecimal.FromString("1.01136850711218").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0563441420380122"),
        EDecimal.FromString("1.0579617101937").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0441997338329168"),
        EDecimal.FromString("1.04519109406496").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0441098652966907"),
        EDecimal.FromString("1.0450971684918").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0159287062968802"),
        EDecimal.FromString("1.01605624441159").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0158944138640438"),
        EDecimal.FromString("1.01602140196849").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0512977132197875"),
        EDecimal.FromString("1.05263623036").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0600803709238746"),
        EDecimal.FromString("1.06192189075916").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0241448511441661"),
        EDecimal.FromString("1.0244386982614").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0487757536198577"),
        EDecimal.FromString("1.04998486903197").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00555052857564936"),
        EDecimal.FromString("1.00556596129943").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0488878897816526"),
        EDecimal.FromString("1.0501026169069").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0129331780218604"),
        EDecimal.FromString("1.0130171732867").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0195581108945102"),
        EDecimal.FromString("1.01975062375983").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0442665419932982"),
        EDecimal.FromString("1.04526092369177").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0344019329116970"),
        EDecimal.FromString("1.0350005239113").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0365845932547272"),
        EDecimal.FromString("1.03726204567897").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00604126455652895"),
        EDecimal.FromString("1.0060595497987").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0374439247648826"),
        EDecimal.FromString("1.03815378073225").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00893470432659082"),
        EDecimal.FromString("1.00897473793796").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0493464269620031"),
        EDecimal.FromString("1.05058423841218").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0365145104692442"),
        EDecimal.FromString("1.03718935401278").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0108399825132420"),
        EDecimal.FromString("1.01089894799267").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0361860360154227"),
        EDecimal.FromString("1.0368487197541").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0158778843863308"),
        EDecimal.FromString("1.01600460780417").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0362180249590484"),
        EDecimal.FromString("1.03688188797985").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00709089586054033"),
        EDecimal.FromString("1.00711609579074").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0247064414721960"),
        EDecimal.FromString("1.02501417470188").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0408200721589514"),
        EDecimal.FromString("1.0416646642089").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0230265235988237"),
        EDecimal.FromString("1.02329368061834").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00411756540148137"),
        EDecimal.FromString("1.00412605422099").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0330324715924324"),
        EDecimal.FromString("1.03358410081807").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0440331767285487"),
        EDecimal.FromString("1.04501702455948").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0424598733278070"),
        EDecimal.FromString("1.04337418839964").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0300286583390142"),
        EDecimal.FromString("1.03048406549205").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0123068048803149"),
        EDecimal.FromString("1.0123828452212").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0201504214463311"),
        EDecimal.FromString("1.0203548117303").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0213331643243503"),
        EDecimal.FromString("1.02156234307571").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0328279211435084"),
        EDecimal.FromString("1.03337270234781").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0192719856634848"),
        EDecimal.FromString("1.01945888911533").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0303006251040892"),
        EDecimal.FromString("1.03076436102361").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0445299383294409"),
        EDecimal.FromString("1.04553627785143").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00933196537508330"),
        EDecimal.FromString("1.00937564392715").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0288390020465388"),
        EDecimal.FromString("1.02925887256298").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0604417417356075"),
        EDecimal.FromString("1.06230570768076").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0420937685999445"),
        EDecimal.FromString("1.04299227409092").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0544936446736653"),
        EDecimal.FromString("1.05600576513115").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0423130030615210"),
        EDecimal.FromString("1.04322095900745").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00402769807265458"),
        EDecimal.FromString("1.0040358201493").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0249430741891245"),
        EDecimal.FromString("1.02525675529105").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0249716770451617"),
        EDecimal.FromString("1.02528608098182").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0431550543147375"),
        EDecimal.FromString("1.04409977447527").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0171451428716117"),
        EDecimal.FromString("1.01729296443251").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0595101010576163"),
        EDecimal.FromString("1.06131648134432").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0398678336155086"),
        EDecimal.FromString("1.04067322308544").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0306980083235453"),
        EDecimal.FromString("1.03117405088044").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0374585293801857"),
        EDecimal.FromString("1.03816894267956").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0230538165179612"),
        EDecimal.FromString("1.02332160967115").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0590935410418274"),
        EDecimal.FromString("1.06087447140232").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0361236954265937"),
        EDecimal.FromString("1.03678408400912").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0161272778861612"),
        EDecimal.FromString("1.01625802434806").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0343361795748225"),
        EDecimal.FromString("1.03493247141055").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0177134758667705"),
        EDecimal.FromString("1.0178712899151").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0208623707233401"),
        EDecimal.FromString("1.02108151125671").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0483375045916944"),
        EDecimal.FromString("1.04952481500001").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0475252574163321"),
        EDecimal.FromString("1.04867268754946").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0381965955050061"),
        EDecimal.FromString("1.03893546284448").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0312440187309719"),
        EDecimal.FromString("1.03173723638267").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0156737684559638"),
        EDecimal.FromString("1.01579724624201").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0457790275003949"),
        EDecimal.FromString("1.04684306186891").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0147767941026916"),
        EDecimal.FromString("1.01488651067829").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0539436345029564"),
        EDecimal.FromString("1.0554251109175").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0377004925433309"),
        EDecimal.FromString("1.03842017171367").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0372457765381051"),
        EDecimal.FromString("1.0379480927805").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0340999272510227"),
        EDecimal.FromString("1.03468799508939").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0545757879436629"),
        EDecimal.FromString("1.05609251246064").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0171891690183572"),
        EDecimal.FromString("1.01733775290777").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0550352195075489"),
        EDecimal.FromString("1.05657782617093").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0546079348743222"),
        EDecimal.FromString("1.05612646313911").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0162432560399893"),
        EDecimal.FromString("1.01637589491261").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0603386411823559"),
        EDecimal.FromString("1.06219618902039").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0154654994270133"),
        EDecimal.FromString("1.015585709165").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0187780315650403"),
        EDecimal.FromString("1.0189554475676").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00954943970970414"),
        EDecimal.FromString("1.00959518109468").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0583893854369344"),
        EDecimal.FromString("1.06012771364488").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0566994568770719"),
        EDecimal.FromString("1.05833768647951").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0407557716873093"),
        EDecimal.FromString("1.04159768683306").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0503750097784955"),
        EDecimal.FromString("1.05166540724761").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0511559650502047"),
        EDecimal.FromString("1.05248703167568").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0413756714603860"),
        EDecimal.FromString("1.04224357317448").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0186736675447437"),
        EDecimal.FromString("1.01884911082955").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0287603667026869"),
        EDecimal.FromString("1.02917793961976").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0189664555815209"),
        EDecimal.FromString("1.01914746133508").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00460682485925289"),
        EDecimal.FromString("1.00461745259066").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0339896783526173"),
        EDecimal.FromString("1.03457392816573").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0183522297098011"),
        EDecimal.FromString("1.0185216668065").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0477684089270911"),
        EDecimal.FromString("1.0489277049004").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0125012035887692"),
        EDecimal.FromString("1.01257967026942").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0202372514989745"),
        EDecimal.FromString("1.02044341303889").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0315811406273541"),
        EDecimal.FromString("1.03208511623203").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0249870861852151"),
        EDecimal.FromString("1.02530187988036").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0557742805285740"),
        EDecimal.FromString("1.05735899028635").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0202637447879503"),
        EDecimal.FromString("1.02047044829924").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00523313325074964"),
        EDecimal.FromString("1.00524685000933").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0291259806714611"),
        EDecimal.FromString("1.02955429024617").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0520754801097177"),
        EDecimal.FromString("1.05345525443073").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0137117745259758"),
        EDecimal.FromString("1.01380621204798").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0174078409921551"),
        EDecimal.FromString("1.01756024048723").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0315601833389113"),
        EDecimal.FromString("1.0320634867532").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0428867805719914"),
        EDecimal.FromString("1.04381970748996").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0181483074004493"),
        EDecimal.FromString("1.0183139886919").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0500437165780426"),
        EDecimal.FromString("1.05131705535553").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0526609407594206"),
        EDecimal.FromString("1.05407219160702").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0500227347727728"),
        EDecimal.FromString("1.05129499705721").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0114862767404131"),
        EDecimal.FromString("1.01155249731684").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00777154138651772"),
        EDecimal.FromString("1.00780181819595").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0592657465319474"),
        EDecimal.FromString("1.0610571755415").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00786712058591605"),
        EDecimal.FromString("1.00789814769036").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00506753152703602"),
        EDecimal.FromString("1.00508039318136").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0491444719493408"),
        EDecimal.FromString("1.05037208908204").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0515585455387738"),
        EDecimal.FromString("1.05291082771939").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0523638911314601"),
        EDecimal.FromString("1.05375912635492").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0499605728551498"),
        EDecimal.FromString("1.05122964857532").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00967088346891925"),
        EDecimal.FromString("1.00971779757411").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0571686709359944"),
        EDecimal.FromString("1.0588343899221").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.00372311447843256"),
        EDecimal.FromString("1.00373005387853").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0445342045483084"),
        EDecimal.FromString("1.04554073834754").Log(ep));
      TestCommon.CompareTestEqual(
        EDecimal.FromString("0.0107556415651578"),
        EDecimal.FromString("1.01081369141234").Log(ep));
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
    public void TestToByteChecked() {
      Assert.assertEquals((byte)0, EDecimal.FromString("-0.1").ToByteChecked());
      Assert.assertEquals((byte)0, EDecimal.FromString("-0.4").ToByteChecked());
      Assert.assertEquals((byte)0, EDecimal.FromString("-0.5").ToByteChecked());
      Assert.assertEquals((byte)0, EDecimal.FromString("-0.6").ToByteChecked());
      try {
        EDecimal.FromString("-1.0").ToByteChecked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("-1.4").ToByteChecked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("-1.5").ToByteChecked();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString("-1.6").ToByteChecked();
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
      for (int i = 0; i < 100000; ++i) {
        EDecimal edec;
        String edecstr;
        if (fr.UniformInt(100) < 10) {
          String decimals = RandomObjects.RandomBigIntString(fr);
          if (decimals.charAt(0) == '-') {
            decimals = decimals.substring(1);
          }
          edecstr = RandomObjects.RandomBigIntString(fr) +
            "." + decimals + "e" + RandomObjects.RandomBigIntString(fr);
          edec = EDecimal.FromString(edecstr);
        } else {
          edec = RandomObjects.RandomEDecimal(fr);
          edecstr = edec.toString();
        }
        if (edec.isFinite()) {
          dbl = edec.ToDouble();
          if (((dbl) == Double.NEGATIVE_INFINITY)) {
            if (!(edec.isNegative())) {
 Assert.fail();
 }
            TestCommon.CompareTestGreaterEqual(
  edec.Abs(),
  DoubleOverflowToInfinity,
  edecstr);
          } else if (((dbl) == Double.POSITIVE_INFINITY)) {
            if (!(!edec.isNegative())) {
 Assert.fail();
 }
            TestCommon.CompareTestGreaterEqual(
  edec.Abs(),
  DoubleOverflowToInfinity,
  edecstr);
          } else if (dbl == 0.0) {
            TestCommon.CompareTestLessEqual(
  edec.Abs(),
  DoubleUnderflowToZero,
  edecstr);
            Assert.assertEquals(edecstr, edec.isNegative(), EDecimal.FromDouble(dbl).isNegative());
          } else {
            if (!(!Double.isNaN(dbl))) {
 Assert.fail();
 }
            edec = edec.Abs();
            TestCommon.CompareTestGreater(
              edec,
              DoubleUnderflowToZero,
              edecstr);
            TestCommon.CompareTestLess(
              edec,
              DoubleOverflowToInfinity,
              edecstr);
            EDecimal halfUlp = GetHalfUlp(dbl);
            EDecimal difference = EDecimal.FromDouble(dbl).Abs()
              .Subtract(edec).Abs();
            TestCommon.CompareTestLessEqual(
              difference,
              halfUlp,
              edecstr);
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
      for (int i = 0; i < 100000; ++i) {
        EDecimal edec;
        String edecstr;
        if (fr.UniformInt(100) < 10) {
          String decimals = RandomObjects.RandomBigIntString(fr);
          if (decimals.charAt(0) == '-') {
            decimals = decimals.substring(1);
          }
          edecstr = RandomObjects.RandomBigIntString(fr) +
            "." + decimals + "e" + RandomObjects.RandomBigIntString(fr);
          edec = EDecimal.FromString(edecstr);
        } else {
          edec = RandomObjects.RandomEDecimal(fr);
          edecstr = edec.toString();
        }
        if (edec.isFinite()) {
          sng = edec.ToSingle();
          if (((sng) == Float.NEGATIVE_INFINITY)) {
            if (!(edec.isNegative())) {
 Assert.fail();
 }
            TestCommon.CompareTestGreaterEqual(
  edec.Abs(),
  SingleOverflowToInfinity,
  edecstr);
          } else if (((sng) == Float.POSITIVE_INFINITY)) {
            if (!(!edec.isNegative())) {
 Assert.fail();
 }
            TestCommon.CompareTestGreaterEqual(
  edec.Abs(),
  SingleOverflowToInfinity,
  edecstr);
          } else if (sng == 0.0f) {
            TestCommon.CompareTestLessEqual(
              edec.Abs(),
              SingleUnderflowToZero,
              edecstr);
            Assert.assertEquals(edecstr, edec.isNegative(), EDecimal.FromSingle(sng).isNegative());
          } else {
            if (!(!Float.isNaN(sng))) {
 Assert.fail();
 }
            edec = edec.Abs();
            TestCommon.CompareTestGreater(edec, SingleUnderflowToZero, edecstr);
            TestCommon.CompareTestLess(edec, SingleOverflowToInfinity, edecstr);
            EDecimal halfUlp = GetHalfUlp(sng);
            EDecimal difference = EDecimal.FromSingle(sng).Abs()
              .Subtract(edec).Abs();
            TestCommon.CompareTestLessEqual(difference, halfUlp, edecstr);
          }
        }
      }
    }
    private static String Repeat(String s, int count) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < count; ++i) {
        sb.append(s);
      }
      return sb.toString();
    }
    @Test
    public void TestOnePlusOne() {
      EContext ec = EContext.ForRounding(ERounding.Up).WithPrecision(4);
      EDecimal ed = EDecimal.FromString("1");
      EDecimal ed2;
      String str;
      for (int i = 10; i < 1000; ++i) {
        str = "1." + Repeat("0", i) + "3";
        ed2 = EDecimal.FromString(str);
        Assert.assertEquals(str,"2.001",ed.Add(ed2, ec).toString());
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

    public static EDecimal Ulps(EDecimal expected, EDecimal actual, int
      precision) {
      if (expected == null) {
        throw new NullPointerException("expected");
      }
      if (actual == null) {
        throw new NullPointerException("actual");
      }
      if (precision <= 0) {
        throw new IllegalArgumentException("precision");
      }
      EInteger k = EInteger.FromInt32(0);
      while (true) {
        EDecimal pk = EDecimal.Create(EInteger.FromInt32(1), k.Negate())
          .Multiply(expected).Abs();
        if (pk.compareTo(EDecimal.FromInt32(1)) >= 0 &&
            pk.compareTo(EDecimal.FromInt32(10)) < 0) {
          break;
        }
        k = k.Add((pk.compareTo(EDecimal.FromInt32(1)) < 0) ? -1 : 1);
      }
      return expected.Subtract(actual).Divide(EDecimal.Create(
        EInteger.FromInt32(1),
        k.Subtract(precision - 1)),
          EContext.ForPrecisionAndRounding(5, ERounding.Up)).Abs();
    }
  }
