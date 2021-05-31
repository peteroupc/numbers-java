package com.upokecenter.test;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.util.*;
import com.upokecenter.numbers.*;

  public class EDecimalTest {
    private static final String[] ValueTestStrings = {
      "1.265e-4",
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
      "0." + TestCommon.Repeat("0", 31) + "", "0.0000e-1", "0.00000",
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
      "0." + TestCommon.Repeat("0", 31) + "", "0e-17", "0E-17",
      "0.00E-15",
      "0.00000000000000000", "0e+17", "0E+17", "0.0E+18", "0",
      "0.00000000000000000e+0", "0E-17", "0.00E-15",
      "0.00000000000000000", "0.0000000000000e+0", "0E-13", "0.0E-12",
      "0.0000000000000", "0.0000000000000000000e-12", "0E-31", "0.0E-30",
      "0." + TestCommon.Repeat("0", 31) + "",
      "0.0000000000000000000e+10",
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
      "0." + TestCommon.Repeat("0", 38) + "", "0.0000000000e-6",
      "0E-16", "0.0E-15", "0.0000000000000000", "0.00000000000000000e-15",
      "0E-32", "0.00E-30", "0." + TestCommon.Repeat("0", 32) + "",
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
      "0.0000000000000",
    };
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
        if (!ba1.equals(ba2)) {
          Assert.assertEquals(ba1.toString(), ba2.toString());
          TestCommon.CompareTestEqual(
            ba1,
            ba2,
            bigintA.toString() + "/" + bigintB.toString());
        }
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

    @Test
    public void TestCompareToBinarySpecific1A() {
      EFloat ef;
      EDecimal ed;
      ef = EFloat.Create(
  EInteger.FromString("-9664843630203122783591902764846273689479381470150824364957816856082399492757121214693125730981133439908"),
  -17247);
      ed = EDecimal.FromEFloat(ef);
      Assert.assertEquals(0, ed.CompareToBinary(ef));
    }
    @Test
    public void TestCompareToBinarySpecific1B() {
      EFloat ef;
      EDecimal ed;
      ef = EFloat.Create(
  EInteger.FromString("-2467365858090833230674801431449857270755907368718802724012643203913243742810052368602482131073985474496270026402026901711301805506483138316932981526091018161243282613098255694573218797136340778209470766121110562843973682548635610931370244160852853706133513187695008443087958097846576792398400512366911345362406722788893972946659265028631132327775167697067574535402565194880977992197706600276103283316072610566206275168152573770312869585911831390513851119697252481532540704706697299199745684224875503861044195414865414501211853220204780962526179105198234107560421422472675347263283990608089697319048891315126404866394172515587870915984406737373646406191043645162091665444977123227178202520024830788089974236992351854383678539013034083419874455158726412688896635633819017324077511735883476845008734150221821164999757875534151181805265806734244829066298005793659243034786743021850398154955226684325077441797994355484078284651814522091252889035546936605403702617900755410567544044017356945337917854344068537582739700174480391485976108360704358539233556356055416272178277957515564601573063597136628104876885194939300236889051122715898223511987845895344284500599930150533252352792434580138801671556601310092800472408049970821810559885509976187264868852561693833270555422604022272867933389426568067071959374066789450739921141187691825889495918119483261575309"),
  -11254);
      ed = EDecimal.FromEFloat(ef);
      Assert.assertEquals(0, ed.CompareToBinary(ef));
    }
    @Test
    public void TestCompareToBinarySpecific1C() {
      EFloat ef;
      EDecimal ed;
      ef = EFloat.Create(
  EInteger.FromString("-325087637545375466523593319915642000166507448768479353748943585507647929726919984092640176973845417409613528524759214900494790101204316104560025164009709666878119359566275239162086759961271534412991410161699457719070992680179292505"),
  -22909);
      ed = EDecimal.FromEFloat(ef);
      Assert.assertEquals(0, ed.CompareToBinary(ef));
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
      EDecimal ed = EDecimal.FromString(
          "7.19575518693181831266567929996929334493885016E+432");
      EFloat ef = EFloat.Create(eim, eie);
      Assert.assertEquals(-1, ed.CompareToBinary(ef));
      eim = EInteger.FromString("309485028268090241945960705");
      eie = EInteger.FromString("525342875590");
      ed =

        EDecimal.FromString(
  "9.511414777277089412154948033116658722787183213120804541938141882272749696679385407387275461761800238977533242480831603777061215911374370925809077057683501541910383022943115134850573547079633633752563620027531228739865573373209036911484539031800435471797748936642897560822226476374652683917217409048036924712889788014206259609E+676");
      ef = EFloat.Create(eim, eie);
      Assert.assertEquals(-1, ed.CompareToBinary(ef));
    }

    private static String DigitString(IRandomGenExtended r, int count) {
      if (count <= 0) {
        throw new IllegalArgumentException("count");
      }
      StringBuilder sb = new StringBuilder();
      sb.append((char)(0x31 + r.GetInt32(9)));
      for (int i = 1; i < count; ++i) {
        sb.append((char)(0x30 + r.GetInt32(10)));
      }
      return sb.toString();
    }
    @Test
    public void TestBadCompare() {
      // Regression test for bug where X compares
      // as less than Y, but X compares as greater than
      // Y + Z, where X and Y have the same number
      // of digits and Z is a number in (0, 1).
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        int digits = r.GetInt32(400) + 1;
        String ds1 = DigitString(r, digits);
        String ds2 = DigitString(r, digits);
        EInteger ei1 = EInteger.FromString(ds1);
        EInteger ei2 = EInteger.FromString(ds2);
        int cmp = ei1.compareTo(ei2);
        if (cmp < 0) {
          EDecimal ed1 = EDecimal.FromString(ds1);
          EDecimal ed2 = EDecimal.FromString(ds2);
          if (!(ed1.CompareToValue(ed2) < 0)) {
 Assert.fail();
 }
          digits = r.GetInt32(400) + 1;
          ds2 += "." + DigitString(r, digits);
          ed2 = EDecimal.FromString(ds2);
          if (!(ed1.CompareToValue(ed2) < 0)) {
 Assert.fail(ds1 + "\n" + ds2);
 }
        } else if (cmp == 0) {
          EDecimal ed1 = EDecimal.FromString(ds1);
          EDecimal ed2 = EDecimal.FromString(ds2);
          if (!(ed1.CompareToValue(ed2) == 0)) {
 Assert.fail();
 }
          digits = r.GetInt32(400) + 1;
          ds2 += "." + DigitString(r, digits);
          ed2 = EDecimal.FromString(ds2);
          if (!(ed1.CompareToValue(ed2) < 0)) {
 Assert.fail(ds1 + "\n" + ds2);
 }
        } else {
          EDecimal ed1 = EDecimal.FromString(ds1);
          EDecimal ed2 = EDecimal.FromString(ds2);
          if (!(ed1.CompareToValue(ed2) > 0)) {
 Assert.fail();
 }
          digits = r.GetInt32(400) + 1;
          ds1 += "." + DigitString(r, digits);
          ed1 = EDecimal.FromString(ds1);
          if (!(ed1.CompareToValue(ed2) > 0)) {
 Assert.fail(ds1 + "\n" + ds2);
 }
        }
      }
    }

    @Test
    public void TestCompareToSignal() {
      // not implemented yet
    }

    @Test
    public void TestIsInteger() {
      EDecimal ed = EDecimal.NaN;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = EDecimal.SignalingNaN;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = EDecimal.PositiveInfinity;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = EDecimal.NegativeInfinity;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = EDecimal.NegativeZero;
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = EDecimal.FromInt32(0);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = EDecimal.FromInt32(999);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = EDecimal.Create(999, 999);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = EDecimal.Create(0, -999);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = EDecimal.Create(999, -999);
      if (ed.IsInteger()) {
 Assert.fail();
 }
    }

    @Test(timeout = 100000)
    public void TestConversions() {
      TestConversionsOne(EDecimal.FromString("4766857907817990.0000000000"));
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 5000; ++i) {
        EDecimal enumber = RandomObjects.RandomEDecimal(fr);
        TestConversionsOne(enumber);
      }
      TestConversionsOne(EDecimal.FromString("-0.8995"));
      TestConversionsOne(EDecimal.FromString("-4.061532283038E+14"));
    }
    public static void TestConversionsOne(EDecimal enumber) {
      boolean isNum, isTruncated, isInteger;
      EInteger eint;
      if (enumber == null) {
        throw new NullPointerException("enumber");
      }
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
        return;
      }
      try {
        eint = enumber.ToSizedEInteger(128);
      } catch (ArithmeticException ex) {
        eint = null;
      }
      isInteger = enumber.IsInteger();
      isNum = enumber.compareTo(0) >= 0 && enumber.compareTo(255) <= 0;
      isTruncated = eint != null && eint.compareTo(0) >= 0 &&
        eint.compareTo(255) <= 0;
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
          Assert.fail(ex.toString() + " " + isTruncated + " eint=" + eint);
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
      isTruncated = eint != null && eint.compareTo(
          EInteger.FromString("-32768")) >= 0 && eint.compareTo(
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
      isTruncated = eint != null && eint.compareTo(
          EInteger.FromString("-2147483648")) >= 0 &&
        eint.compareTo(
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
      isTruncated = eint != null && eint.compareTo(
          EInteger.FromString("-9223372036854775808")) >= 0 &&
        eint.compareTo(
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
    public void TestCompareToNull() {
      TestCommon.CompareTestLess(0, EInteger.FromInt32(0).compareTo(null));

      TestCommon.CompareTestLess(0, EDecimal.Zero.compareTo(null));
      TestCommon.CompareTestLess(0, EDecimal.Zero.CompareToBinary(null));
      {
        int integerTemp2 = EDecimal.Zero.CompareToTotal(null,
            EContext.Unlimited);
        TestCommon.CompareTestLess(0, integerTemp2);
      }
      {
        int integerTemp2 = EDecimal.Zero.CompareToTotalMagnitude(null,
            EContext.Unlimited);
        TestCommon.CompareTestLess(0, integerTemp2);
      }
      TestCommon.CompareTestLess(0, EDecimal.Zero.CompareToTotal(null, null));
      {
        int integerTemp2 = EDecimal.Zero.CompareToTotalMagnitude(null,
            null);
        TestCommon.CompareTestLess(0, integerTemp2);
      }
      TestCommon.CompareTestGreater(
        EDecimal.Zero.CompareToSignal(null, EContext.Unlimited),
        EDecimal.Zero);
      TestCommon.CompareTestGreater(
        EDecimal.Zero.CompareToWithContext(null, null),
        EDecimal.Zero);

      TestCommon.CompareTestLess(0, EFloat.Zero.compareTo(null));
      {
        int integerTemp2 = EFloat.Zero.CompareToTotal(null,
            EContext.Unlimited);
        TestCommon.CompareTestLess(0, integerTemp2);
      }
      {
        int integerTemp2 = EFloat.Zero.CompareToTotalMagnitude(null,
            EContext.Unlimited);
        TestCommon.CompareTestLess(0, integerTemp2);
      }
      TestCommon.CompareTestLess(0, EFloat.Zero.CompareToTotal(null, null));
      TestCommon.CompareTestLess(0,
        EFloat.Zero.CompareToTotalMagnitude(null, null));
      TestCommon.CompareTestGreater(
        EFloat.Zero.CompareToSignal(null, EContext.Unlimited),
        EFloat.Zero);
      TestCommon.CompareTestGreater(
        EFloat.Zero.CompareToWithContext(null, null),
        EFloat.Zero);

      TestCommon.CompareTestLess(0, ERational.Zero.compareTo(null));
      TestCommon.CompareTestLess(0, ERational.Zero.CompareToTotal(null));
      TestCommon.CompareTestLess(0,
        ERational.Zero.CompareToTotalMagnitude(null));
      TestCommon.CompareTestLess(0, ERational.Zero.CompareToBinary(null));
      TestCommon.CompareTestLess(0, ERational.Zero.CompareToDecimal(null));
      {
        int objectTemp2 = EDecimals.CompareTotal(
            EDecimal.Zero,
            null,
            EContext.Unlimited);
        TestCommon.CompareTestLess(0, objectTemp2);
      }
      {
        int objectTemp2 = EDecimals.CompareTotalMagnitude(
            EDecimal.Zero,
            null,
            EContext.Unlimited);
        TestCommon.CompareTestLess(0, objectTemp2);
      }
      {
        int objectTemp2 = EDecimals.CompareTotal(
            null,
            EDecimal.Zero,
            EContext.Unlimited);
        TestCommon.CompareTestGreater(0, objectTemp2);
      }
      {
        int objectTemp2 = EDecimals.CompareTotalMagnitude(
            null,
            EDecimal.Zero,
            EContext.Unlimited);
        TestCommon.CompareTestGreater(0, objectTemp2);
      }
      {
        int integerTemp2 = EDecimals.CompareTotal(
            null,
            null,
            EContext.Unlimited);
        Assert.assertEquals(0, integerTemp2);
      }
      {
        int objectTemp2 = EDecimals.CompareTotalMagnitude(
            null,
            null,
            EContext.Unlimited);
        Assert.assertEquals(0, objectTemp2);
      }
      {
        int integerTemp2 = EFloats.CompareTotal(
            EFloat.Zero,
            null,
            EContext.Unlimited);
        TestCommon.CompareTestLess(0, integerTemp2);
      }
      {
        int integerTemp2 = EFloats.CompareTotalMagnitude(
            EFloat.Zero,
            null,
            EContext.Unlimited);
        TestCommon.CompareTestLess(0, integerTemp2);
      }
      {
        int integerTemp2 = EFloats.CompareTotal(
            null,
            EFloat.Zero,
            EContext.Unlimited);
        TestCommon.CompareTestGreater(0, integerTemp2);
      }
      {
        int integerTemp2 = EFloats.CompareTotalMagnitude(null,
            EFloat.Zero,
            EContext.Unlimited);
        TestCommon.CompareTestGreater(0, integerTemp2);
      }
      {
        int integerTemp2 = EFloats.CompareTotal(
            null,
            null,
            EContext.Unlimited);
        Assert.assertEquals(0, integerTemp2);
      }
      {
        int objectTemp2 = EFloats.CompareTotalMagnitude(
            null,
            null,
            EContext.Unlimited);
        Assert.assertEquals(0, objectTemp2);
      }
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

      EDecimal ed1 = EDecimal.FromString(
          "-4.283595962816029891509990043176592129349E-265444677156");
      EDecimal ed2 = EDecimal.FromString(
          "1.154883492783088701967E+230940250505264307520");
      EDecimalTest.TestDivideOne(ed1, ed2);
      ed1 = EDecimal.FromString(
          "-3.77339248640695614E-16962706853");
      ed2 = EDecimal.FromString(
          "-8.801467625870877584114178689458325778E+19649240327");
      EDecimalTest.TestDivideOne(ed1, ed2);
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 5000; ++i) {
        ed1 = RandomObjects.RandomEDecimal(fr);
        ed2 = RandomObjects.RandomEDecimal(fr);
        TestDivideOne(ed1, ed2);
      }
      try {
        EDecimal.FromString("1").Divide(EDecimal.FromString("3"), null);
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    public static void TestDivideOne(EDecimal ed1, EDecimal ed2) {
      if (ed2 == null) {
        throw new NullPointerException("ed2");
      }
      if (ed1 == null) {
        throw new NullPointerException("ed1");
      }
      if (!ed1.isFinite() || !ed2.isFinite()) {
        return;
      }
      EDecimal ed3 = ed1.Multiply(ed2);
      if (!(ed3.isFinite())) {
 Assert.fail();
 }
      EDecimal ed4;
      ed4 = ed3.Divide(ed1);
      if (!ed1.isZero()) {
        if (ed4.compareTo(ed2) != 0) {
          TestCommon.CompareTestEqual(ed4, ed2, "ed1=" + ed1 + "\ned2=" + ed2);
        }
      } else {
        if (!(ed4.IsNaN())) {
 Assert.fail();
 }
      }
      ed4 = ed3.Divide(ed2);
      if (!ed2.isZero()) {
        if (ed4.compareTo(ed1) != 0) {
          TestCommon.CompareTestEqual(ed4, ed1, "ed1=" + ed1 + "\ned2=" + ed2);
        }
      } else {
        if (!(ed4.IsNaN())) {
 Assert.fail();
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
      if (EDecimal.One.equals(null)) {
 Assert.fail();
 }
      if (EDecimal.Zero.equals(null)) {
 Assert.fail();
 }
      if (EDecimal.One.equals(EDecimal.Zero)) {
 Assert.fail();
 }
      if (EDecimal.Zero.equals(EDecimal.One)) {
 Assert.fail();
 }
      {
        Object objectTemp =

  EDecimal.FromString("0.009461540475412139260145553670698466186015902447450593622262751970123371581303298477485466592231565609");
        Object objectTemp2 =

  EDecimal.FromString("0.009461540475412139260145553670698466186015902447450593622262751970123371581303298477485466592231565609");
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      {
        Object objectTemp =

  EDecimal.FromString("0.009461540475412139260145553670698466186015902447450593622262751970123371581303298477485466592231565609");
        Object objectTemp2 =

  EDecimal.FromString("0.001809476049361792727571247490438259768858020288404502743164967883090669271207537395819291033916115474");
        if ((objectTemp).equals(objectTemp2)) {
 Assert.fail((objectTemp)+" may not be equal to "+(objectTemp2));
}
      }
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
      TestEDecimalSingleCore(-5.41868103E-41f, null);
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
        {
          String stringTemp2 =

  "2.29360000000000010330982488752915582352898127282969653606414794921875E-7";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
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
        {
          String stringTemp2 =

  "0.0000019512000000000000548530838806460252499164198525249958038330078125";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
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
        {
          String stringTemp2 =
  "0.0000310349999999999967797807698399736864303122274577617645263671875";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
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
        {
          String stringTemp2 =

  "0.00000323700000000000009386523676380154057596882921643555164337158203125";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
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
        {
          String stringTemp2 =

  "2.28179999999999995794237200343046456652018605382181704044342041015625E-7";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
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
        {
          String stringTemp2 =

  "0.00000380250000000000001586513038998038638283105683512985706329345703125";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
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
        {
          String stringTemp2 =
  "3.911600000000000165617541382501176627783934236504137516021728515625E-7";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
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
        {
          String stringTemp2 =
  "0.00013414000000000001334814203612921801322954706847667694091796875";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
      }

      {
        stringTemp = EDecimal.FromDouble(3.445E-7).toString();
        {
          String stringTemp2 =

  "3.4449999999999999446924077266263264363033158588223159313201904296875E-7";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
      }

      {
        stringTemp = EDecimal.FromDouble(1.361E-7).toString();
        {
          String stringTemp2 =

  "1.3610000000000000771138253079228785935583800892345607280731201171875E-7";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
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
        {
          String stringTemp2 =
  "0.00000600000000000000015200514458246772164784488268196582794189453125";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
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
        {
          String stringTemp2 =
  "0.00023310000000000000099260877295392901942250318825244903564453125";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
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
        {
          String stringTemp2 =
  "0.00001830000000000000097183545932910675446692039258778095245361328125";
          Assert.assertEquals(stringTemp2, stringTemp);
        }
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
        EDecimal.FromString((String)null);
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
        EDecimal.FromString((String)null, null);
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
        EDecimal.FromString((String)null, 0, 1);
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
        EDecimal.FromString((String)null, 0, 1, null);
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
        EDecimal.FromString(r);
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
      if (!(EDecimal.PositiveInfinity.IsPositiveInfinity())) {
 Assert.fail();
 }
      if (EDecimal.NegativeInfinity.IsPositiveInfinity()) {
 Assert.fail();
 }
      if (EDecimal.Zero.IsPositiveInfinity()) {
 Assert.fail();
 }
      if (EDecimal.NaN.IsPositiveInfinity()) {
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
      AppResources resources = new AppResources("Resources");
      String json = resources.GetString("logprec15");
      json = DecTestUtil.ParseJSONString(json);
      String[] items = DecTestUtil.SplitAt(json, "\u002c");
      for (int i = 0; i < items.length; i += 2) {
        TestCommon.CompareTestEqual(
          EDecimal.FromString(items[i]),
          EDecimal.FromString(items[i + 1]).Log(ep));
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
      if (EDecimal.SignalingNaN.CopySign(
          EDecimal.Zero).isNegative()) {
 Assert.fail();
 }
      if (!(
        EDecimal.SignalingNaN.CopySign(EDecimal.NegativeZero).isNegative())) {
 Assert.fail();
 }
    }

    @Test(timeout = 20000)
    public void TestAddSpecific() {
       EDecimal ed1 =
EDecimal.FromString("-6.44157770841120149430189812635250244E+472921500817");
       EDecimal ed2 = EDecimal.FromString("162693755097376304199934012293120");
       // Should return NaN due to memory issues
       ed1 = ed1.Add(ed2);
       if (!(!ed1.isFinite())) {
 Assert.fail();
 }
    }

    @Test(timeout = 20000)
    public void TestAddSpecific2() {
       String estr =
"1.38142827576784791711343100763562342897471169437179108373895" +
           "E-1527495144586647199842075371956087247329299647091240949";
       EDecimal ed1 = EDecimal.FromString(estr);
       EDecimal ed2 = EDecimal.FromString("162693755097376304199934012293120");
       // Should return NaN due to memory issues
       ed1 = ed1.Add(ed2);
       if (!(!ed1.isFinite())) {
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
      Assert.assertEquals(EDecimal.NaN, EDecimal.SignalingNaN.Plus(null));
      Assert.assertEquals(
        EDecimal.NaN,
        EDecimal.SignalingNaN.Plus(EContext.Unlimited));
      Assert.assertEquals(
        EDecimal.NaN,
        EDecimal.SignalingNaN.Plus(EContext.Decimal128));
    }
    @Test
    public void TestPow() {
      EDecimal a = EDecimal.FromInt32(33000);
      EDecimal b = EDecimal.FromInt32(6);
      EDecimal result = EDecimal.FromString("1291467969000000000000000000");
      EDecimal powa = a.Pow(b);
      TestCommon.CompareTestEqual((EDecimal)result, (EDecimal)powa);
      RandomGenerator r = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        a = EDecimal.FromInt32(r.UniformInt(1000000) + 1);
        b = EDecimal.FromInt32(r.UniformInt(12) + 2);
        EInteger ei = a.ToEInteger().Pow(b.ToEInteger());
        result = EDecimal.FromEInteger(ei);
        powa = a.Pow(b);
        TestCommon.CompareTestEqual((EDecimal)result, (EDecimal)powa);
      }
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
      TestRoundToExponentOne("-0", "-0", 0, ERounding.Down);
      TestRoundToExponentOne("-0", "-0", 0, ERounding.HalfEven);
      TestRoundToExponentOne("-0", "-0", 0, ERounding.Floor);
      TestRoundToExponentOne("-0.0", "-0", 0, ERounding.Down);
      TestRoundToExponentOne("-0.0", "-0", 0, ERounding.HalfEven);
      TestRoundToExponentOne("-0.0", "-0", 0, ERounding.Floor);
      TestRoundToExponentOne("-0.0000", "-0", 0, ERounding.Down);
      TestRoundToExponentOne("-0.0000", "-0", 0, ERounding.HalfEven);
      TestRoundToExponentOne("-0.0000", "-0", 0, ERounding.Floor);
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
        str + " -> " + EDecimal.FromDouble(ed.ToDouble()));
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
    public void TestToSizedEInteger() {
      try {
        EDecimal.PositiveInfinity.ToSizedEInteger(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.NegativeInfinity.ToSizedEInteger(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.PositiveInfinity.ToSizedEInteger(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.NegativeInfinity.ToSizedEInteger(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.NaN.ToSizedEInteger(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.PositiveInfinity.ToSizedEIntegerIfExact(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.NegativeInfinity.ToSizedEIntegerIfExact(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.PositiveInfinity.ToSizedEIntegerIfExact(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.NegativeInfinity.ToSizedEIntegerIfExact(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.NaN.ToSizedEIntegerIfExact(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      RandomGenerator rg = new RandomGenerator();
      for (int i = 0; i < 75000; ++i) {
        EDecimal ed = RandomObjects.RandomEDecimal(rg);
        boolean bv = rg.UniformInt(2) == 0;
        int msb = rg.UniformInt(129);
        try {
          TestSizedEIntegerOne(ed, bv, msb);
        } catch (IllegalStateException ex) {
          throw new IllegalStateException(
            ed + " " + bv + " " + msb,
            ex);
        }
      }
      for (int i = 0; i < 10000; ++i) {
        EInteger ei = RandomObjects.RandomEInteger(rg);
        int expo = rg.UniformInt(20);
        boolean bv = rg.UniformInt(2) == 0;
        int msb = rg.UniformInt(129);
        EDecimal ed = EDecimal.FromEInteger(ei)
          .ScaleByPowerOfTen(expo).MovePointLeft(expo);
        TestSizedEIntegerOne(ed, bv, msb);
      }
    }

    @Test
    public void TestSizedEIntegerSpecific1() {
      TestSizedEIntegerOne(
        EDecimal.FromString("1478.0619950000000000"),
        true,
        105);
      TestSizedEIntegerOne(
        EDecimal.FromString("1478.0619950000000000"),
        false,
        105);
    }

    public static boolean TestSizedEIntegerOne(EDecimal ed, boolean isExact, int
      maxSignedBits) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      if (!ed.isFinite() || ed.isZero()) {
        { return false;
        }
      }
      EInteger ei = null;
      EInteger ei2 = null;
      try {
        ei = ed.getExponent().compareTo(maxSignedBits + 6) > 0 ? null : (isExact ?
            ed.ToEIntegerIfExact() : ed.ToEInteger());
        if (ei != null &&
          ei.GetSignedBitLengthAsEInteger().compareTo(maxSignedBits) > 0) {
          ei = null;
        }
      } catch (ArithmeticException ex) {
        ei = null;
      } catch (UnsupportedOperationException ex) {
        ei = null;
      }
      try {
        ei2 = isExact ? ed.ToSizedEIntegerIfExact(maxSignedBits) :
          ed.ToSizedEInteger(maxSignedBits);
      } catch (UnsupportedOperationException ex) {
        Assert.fail(ed.toString());
      } catch (ArithmeticException ex) {
        ei2 = null;
      }
      if (ei == null) {
        if (!(ei2 == null)) {
 Assert.fail();
 }
      } else {
        Assert.assertEquals(ei, ei2);
        if (!(ei.GetSignedBitLengthAsEInteger().compareTo(
            maxSignedBits) <= 0)) {
 Assert.fail();
 }
      }
      return true;
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
      RandomGenerator rg = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        EInteger ei = RandomObjects.RandomEInteger(rg);
        int expo = rg.UniformInt(20);
        EDecimal ed = EDecimal.FromEInteger(ei)
          .ScaleByPowerOfTen(expo).MovePointLeft(expo);
        Assert.assertEquals(ei, ed.ToEIntegerIfExact());
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

    private static final Object UlpSync = new Object();
    private static EDecimal[] valueUlpTable = null;

    private static EDecimal GetHalfUlp(double dbl) {
      long value = Double.doubleToRawLongBits(dbl);
      int exponent = (int)((value >> 52) & 0x7ffL);
      synchronized (UlpSync) {
        valueUlpTable = (valueUlpTable == null) ? (new EDecimal[2048]) : valueUlpTable;
        if (exponent == 0) {
          if (valueUlpTable[exponent] == null) {
            valueUlpTable[exponent] = EFloat.Create(1, exponent - 1075)
              .ToEDecimal();
          }
          EDecimal ed = valueUlpTable[exponent];
          if (ed == null) {
            Assert.fail();
          }
          return ed;
        } else if (exponent == 2047) {
          throw new IllegalArgumentException("dbl is non-finite");
        } else {
          int e1 = exponent - 1;
          if (valueUlpTable[e1] == null) {
            valueUlpTable[e1] = EFloat.Create(1, e1 - 1075)
              .ToEDecimal();
          }
          EDecimal ed = valueUlpTable[e1];
          if (ed == null) {
            Assert.fail();
          }
          return ed;
        }
      }
    }

    private static EDecimal GetHalfUlp(float sng) {
      int value = Float.floatToRawIntBits(sng);
      int exponent = (int)((value >> 23) & 0xff);

      synchronized (UlpSync) {
        valueUlpTable = (valueUlpTable == null) ? (new EDecimal[2048]) : valueUlpTable;
        if (exponent == 0) {
          exponent += 925;

          if (valueUlpTable[exponent] == null) {
            valueUlpTable[exponent] = EFloat.Create(1, exponent - 1075)
              .ToEDecimal();
          }
          EDecimal ed = valueUlpTable[exponent];
          if (ed == null) {
            Assert.fail();
          }
          return ed;
        } else if (exponent == 255) {
          throw new IllegalArgumentException("sng is non-finite");
        } else {
          exponent += 924;
          if (valueUlpTable[exponent] == null) {
            valueUlpTable[exponent] = EFloat.Create(1, exponent - 1075)
              .ToEDecimal();
          }
          EDecimal ed = valueUlpTable[exponent];
          if (ed == null) {
            Assert.fail();
          }
          return ed;
        }
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
        edec = RandomObjects.RandomEDecimal(fr);
        if (edec.isFinite()) {
          dbl = edec.ToDouble();
          if (((dbl) == Double.NEGATIVE_INFINITY)) {
            if (!(edec.isNegative())) {
 Assert.fail();
 }
            TestCommon.CompareTestGreaterEqual(
              edec.Abs(),
              DoubleOverflowToInfinity);
          } else if (((dbl) == Double.POSITIVE_INFINITY)) {
            if (!(!edec.isNegative())) {
 Assert.fail();
 }
            TestCommon.CompareTestGreaterEqual(
              edec.Abs(),
              DoubleOverflowToInfinity);
          } else if (dbl == 0.0) {
            TestCommon.CompareTestLessEqual(
              edec.Abs(),
              DoubleUnderflowToZero);
            Assert.assertEquals(
              edec.isNegative(),
              EDecimal.FromDouble(dbl).isNegative());
          } else {
            if (!(!Double.isNaN(dbl))) {
 Assert.fail();
 }
            edec = edec.Abs();
            TestCommon.CompareTestGreater(
              edec,
              DoubleUnderflowToZero);
            TestCommon.CompareTestLess(
              edec,
              DoubleOverflowToInfinity);
            EDecimal halfUlp = GetHalfUlp(dbl);
            EDecimal difference = EDecimal.FromDouble(dbl).Abs()
              .Subtract(edec).Abs();
            TestCommon.CompareTestLessEqual(
              difference,
              halfUlp);
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
        edec = RandomObjects.RandomEDecimal(fr);
        if (edec.isFinite()) {
          sng = edec.ToSingle();
          if (((sng) == Float.NEGATIVE_INFINITY)) {
            if (!(edec.isNegative())) {
 Assert.fail();
 }
            TestCommon.CompareTestGreaterEqual(
              edec.Abs(),
              SingleOverflowToInfinity);
          } else if (((sng) == Float.POSITIVE_INFINITY)) {
            if (!(!edec.isNegative())) {
 Assert.fail();
 }
            TestCommon.CompareTestGreaterEqual(
              edec.Abs(),
              SingleOverflowToInfinity);
          } else if (sng == 0.0f) {
            if ((SingleUnderflowToZero)==null) {
 Assert.fail("ufZero");
 }
            TestCommon.CompareTestLessEqual(
              edec.Abs(),
              SingleUnderflowToZero);
            Assert.assertEquals(
              edec.isNegative(),
              EDecimal.FromSingle(sng).isNegative());
          } else {
            if (!(!Float.isNaN(sng))) {
 Assert.fail();
 }
            edec = edec.Abs();
            TestCommon.CompareTestGreater(
              edec,
              SingleUnderflowToZero);
            TestCommon.CompareTestLess(
              edec,
              SingleOverflowToInfinity);
            EDecimal halfUlp = GetHalfUlp(sng);
            EDecimal difference = EDecimal.FromSingle(sng).Abs()
              .Subtract(edec).Abs();
            if ((difference)==null) {
 Assert.fail("difference");
 }
            if ((halfUlp)==null) {
 Assert.fail("halfUlp");
 }
            TestCommon.CompareTestLessEqual(difference, halfUlp);
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

    private static EInteger RandomEIntegerShort(
      IRandomGenExtended rg) {
      byte[] bytes = RandomObjects.RandomByteStringShort(rg);
      return (bytes.length == 0) ? EInteger.FromInt32(0) :
        EInteger.FromBytes(bytes, true);
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
        EInteger mantBig = RandomEIntegerShort(fr);
        EInteger expBig = RandomEIntegerShort(fr);
        // System.out.println("i=" + i + " mant=" +
        // mantBig.GetUnsignedBitLengthAsInt64() + " expBig=" +
        // expBig.GetUnsignedBitLengthAsInt64());
        EDecimal dec = EDecimal.Create(mantBig, expBig);
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

    static void TestEDecimalSingleCore(float d, String s) {
      float oldd = d;
      EDecimal bf = EDecimal.FromSingle(d);
      if (s != null) {
        Assert.assertEquals(s, bf.toString());
      }
      d = bf.ToSingle();
      Assert.assertEquals((float)oldd, d, 0f);
    }

    private static void TestRoundToExponentOne(
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

    @Test
    public void TestStringContextSpecific1() {
      String str = "60277096704082E-96";
      String expected = "6.027709E-83";
      EContext ec = EContext.Basic.WithExponentClamp(
          true).WithAdjustExponent(
          false).WithRounding(
          ERounding.Down).WithExponentRange(-95, 96).WithPrecision(7);
      TestStringContextOne(str, ec);
      String actualstr = EDecimal.FromString(str).RoundToPrecision(
          ec).toString();
      Assert.assertEquals(expected, actualstr);
      actualstr = EDecimal.FromString(str, ec).toString();
      Assert.assertEquals(expected, actualstr);
    }

    @Test
    public void TestStringContextSpecific2() {
      String str = "8.888888888888E-214748365";
      String expected = "1E-103";
      EContext
      ec = EContext.Basic.WithExponentClamp(
          false).WithAdjustExponent(
          true).WithRounding(
          ERounding.OddOrZeroFiveUp).WithExponentRange(-95,
          96).WithPrecision(9);
      TestStringContextOne(str, ec);
      String actualstr = EDecimal.FromString(str).RoundToPrecision(
          ec).toString();
      Assert.assertEquals(expected, actualstr);
      actualstr = EDecimal.FromString(str, ec).toString();
      Assert.assertEquals(expected, actualstr);
    }

    @Test
    public void TestStringContextSpecific7() {
      EContext ec = EContext.Unlimited.WithExponentClamp(
          true).WithAdjustExponent(
          true).WithRounding(
          ERounding.Floor).WithExponentRange(-9999999, 9999999);
      String str = TestCommon.Repeat("7", 1000) + "E-" +
        TestCommon.Repeat("7", 1000);
      TestStringContextOne(str, ec);
    }

    @Test
    public void TestStringContextSpecific3() {
      String str = "10991.709233660650E-90";
      String expected = "1.099171E-86";
      EContext ec = EContext.Basic.WithExponentClamp(
          true).WithAdjustExponent(
          false).WithRounding(
          ERounding.Up).WithExponentRange(-95, 96).WithPrecision(7);
      TestStringContextOne(str, ec);
      String actualstr = EDecimal.FromString(str).RoundToPrecision(
          ec).toString();
      Assert.assertEquals(expected, actualstr);
      actualstr = EDecimal.FromString(str, ec).toString();
      Assert.assertEquals(expected, actualstr);
    }

    @Test
    public void TestStringContextSpecific4a() {
      EContext ec = EContext.Basic.WithExponentClamp(
          true).WithAdjustExponent(
          true).WithRounding(
          ERounding.HalfUp).WithExponentRange(-95, 96).WithPrecision(7);
      TestStringContextOne("806840.80E+60", ec);
    }

    @Test
    public void TestStringContextSpecific4b() {
      EContext ec = EContext.Basic.WithExponentClamp(
          true).WithAdjustExponent(
          false).WithRounding(
          ERounding.Ceiling).WithExponentRange(-95, 96).WithPrecision(7);
      TestStringContextOne("900.01740E-90", ec);
    }

    @Test
    public void TestStringContextSpecific4c() {
      String num =

  "-16120570567037778210732025954408283690946444690491951476102714548515145821906708291828685686116455423481898854735868999853690814E-7564";
      TestStringContextOneEFloat(num, EContext.Binary64);
    }

    @Test
    public void TestStringContextSpecific4d() {
      EContext ec = EContext.Unlimited.WithPrecision(53).WithExponentRange(
          -1022,
          1023).WithRounding(
          ERounding.HalfUp).WithAdjustExponent(
          true).WithExponentClamp(true).WithSimplified(false);
      String str = "1111111." + TestCommon.Repeat("1", 770) + "E-383";
      TestStringContextOneEFloat(str, ec);
    }

    @Test
    public void TestStringContextSpecific4e() {
      EContext ec =
        EContext.Unlimited.WithPrecision(53).WithExponentRange(-1022,
          1023).WithRounding(
          ERounding.Down).WithAdjustExponent(
          false).WithExponentClamp(true).WithSimplified(false);
      String str = TestCommon.Repeat("8", 257) + "." +
        TestCommon.Repeat("8",
          120) + "E+60";
      EFloat ef = EFloat.FromString(str, ec);
      EFloat ef2 = EDecimal.FromString(str).ToEFloat(ec);
      Assert.assertEquals(ef, ef2);
      EDecimalTest.TestStringContextOneEFloat(str, ec);
    }

    @Test
    public void TestStringContextUnderflow() {
      EContext ec = EContext.Binary64.WithRounding(
          ERounding.HalfUp);
      for (int i = 0; i < 700; ++i) {
        String str = "1111111" + (i == 0 ? "" : ".") +
          TestCommon.Repeat("0", i) + "E-383";
        TestStringContextOneEFloat(str, ec);
        if (!(EFloat.FromString(str, ec).isZero())) {
 Assert.fail();
 }
      }
    }

    @Test
    public void TestStringContextSpecific4() {
      EContext ec = EContext.Basic.WithExponentClamp(
          true).WithAdjustExponent(
          true).WithRounding(
          ERounding.Floor).WithExponentRange(-95, 96).WithPrecision(7);
      TestStringContextOne("66666666666666666E+40", ec);
      TestStringContextOne("6666666666666666.6E+40", ec);
      TestStringContextOne("666666666666666.66E+40", ec);
      TestStringContextOne("66666666666666.666E+40", ec);
      TestStringContextOne("6.6666666666666666E+40", ec);
      TestStringContextOne("66.666666666666666E+40", ec);
      TestStringContextOne("666.66666666666666E+40", ec);
    }

    private static long unoptTime = 0;
    private static long unoptRoundTime = 0;
    private static long optTime = 0;
    /*
     private static final System.Diagnostics.Stopwatch swUnopt = new
     System.Diagnostics.Stopwatch();
     private static final System.Diagnostics.Stopwatch swUnoptRound = new
     System.Diagnostics.Stopwatch();
     private static final System.Diagnostics.Stopwatch swOpt2 = new
     System.Diagnostics.Stopwatch();
      */
    public static void TearDown() {
      if (unoptTime > 0) {
        System.out.println("unoptTime = " + unoptTime + " ms");
        System.out.println("unoptRoundTime = " + unoptRoundTime + " ms");
        System.out.println("optTime = " + optTime + " ms");
        unoptTime = 0;
        unoptRoundTime = 0;
        optTime = 0;
      }
    }

    @Test
    public void TestZerosRoundingNone() {
      EContext ec =
        EContext.Unlimited.WithPrecision(11).WithExponentRange(-14,
          15).WithRounding(
          ERounding.None).WithAdjustExponent(
          false).WithExponentClamp(
          true).WithSimplified(false).WithTraps(EContext.FlagInvalid);
      String str = "0E+5936";
      try {
        EFloat.FromString(str, ec);
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestZerosRoundingNone4() {
      EContext ec =
        EContext.Unlimited.WithPrecision(11).WithExponentRange(-14,
          15).WithRounding(
          ERounding.Ceiling).WithAdjustExponent(
          true).WithExponentClamp(
          true).WithSimplified(false).WithTraps(EContext.FlagInvalid);
      String str = "049E+20";
      try {
        EFloat.FromString(str, ec);
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    public static String ToLowerCaseAscii(String str) {
      if (str == null) {
        return null;
      }
      int len = str.length();
      char c = (char)0;
      boolean hasUpperCase = false;
      for (int i = 0; i < len; ++i) {
        c = str.charAt(i);
        if (c >= 'A' && c <= 'Z') {
          hasUpperCase = true;
          break;
        }
      }
      if (!hasUpperCase) {
        return str;
      }
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < len; ++i) {
        c = str.charAt(i);
        if (c >= 'A' && c <= 'Z') {
          builder.append((char)(c + 0x20));
        } else {
          builder.append(c);
        }
      }
      return builder.toString();
    }

    public static void TestUnsignedLongOne(long v, String expectedStr) {
         EInteger ei = EInteger.FromInt64AsUnsigned(v);
         Assert.assertEquals(
           expectedStr,
           ToLowerCaseAscii(ei.ToRadixString(16)));
         EDecimal ed = EDecimal.FromInt64AsUnsigned(v);
         TestCommon.CompareTestEqual(EDecimal.FromEInteger(ei), ed);
         EFloat ef = EFloat.FromInt64AsUnsigned(v);
         TestCommon.CompareTestEqual(EFloat.FromEInteger(ei), ef);
         ERational er = ERational.FromInt64AsUnsigned(v);
         TestCommon.CompareTestEqual(ERational.FromEInteger(ei), er);
    }

    @Test
    public void TestUnsignedLong() {
       TestUnsignedLongOne(0x0L, "0");
       TestUnsignedLongOne(0xFL, "f");
       TestUnsignedLongOne(0xFFFFFFFFL, "ffffffff");
       TestUnsignedLongOne(-1, "ffffffffffffffff");
       TestUnsignedLongOne(-3, "fffffffffffffffd");
       TestUnsignedLongOne(Long.MAX_VALUE, "7fffffffffffffff");
       TestUnsignedLongOne(Long.MAX_VALUE - 1, "7ffffffffffffffe");
       TestUnsignedLongOne(Long.MIN_VALUE, "8000000000000000");
       TestUnsignedLongOne(Long.MIN_VALUE + 1, "8000000000000001");
    }

    @Test
    public void TestZerosRoundingNone3() {
      EContext ec =
        EContext.Unlimited.WithPrecision(11).WithExponentRange(-14,
          15).WithRounding(
          ERounding.None).WithAdjustExponent(
          false).WithExponentClamp(
          true).WithSimplified(false).WithTraps(EContext.FlagInvalid);
      String str = "0.0000E+59398886";
      try {
        EFloat.FromString(str, ec);
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    public static void TestStringContextOne(
      String str,
      EContext ec) {
      TestStringContextOne(str, null, ec);
    }

    // Test potential cases where FromString is implemented
    // to take context into account when building the EDecimal
    public static void TestStringContextOne(
      String str,
      EDecimal ed,
      EContext ec) {
      if (ec == null) {
        throw new NullPointerException("ec");
      }
      if (str == null) {
        throw new NullPointerException("str");
      }
      // System.out.println("TestStringContextOne length=" + str.length());
      EContext noneRounding = ec.WithRounding(
          ERounding.None).WithTraps(EContext.FlagInvalid);
      EContext downRounding = ec.WithRounding(ERounding.Down);
      EDecimal ed2;
      ed = (ed == null) ? (EDecimal.FromString(str)) : ed;
      if (ed == null) {
        Assert.fail();
      }
      EDecimal edorig = ed;
      ed = ed.RoundToPrecision(ec);
      ed2 = EDecimal.FromString(str, ec);
      // System.out.println("exponent "+ed.getExponent()+
      // " "+ed2.getExponent());
      EDecimal ef3 = EDecimal.NaN;
      try {
        ef3 = EDecimal.FromString(str, noneRounding);
      } catch (ETrapException ex) {
        // NOTE: Intentionally empty
      }
      EDecimal edef2 = ec.getRounding() == ERounding.Down ? ed2 :
        EDecimal.FromString(str, downRounding);
      if ((ef3 != null && !ef3.IsNaN()) && edorig != null &&
        edorig.compareTo(edef2) != 0) {
        System.out.println("# ERounding.None fails to detect rounding was" +
          "\u0020necessary");
        if (str == null) {
          throw new NullPointerException("str");
        }
        System.out.println("# str = " + str.substring(0,Math.min(str.length(),
              200)) + (str.length() > 200 ? "..." : "") + "\n# ec = " +
          ECString(ec));
        System.out.println("# ef3 = " + ef3.toString());
      } else if ((ef3 == null || ef3.IsNaN()) && edorig != null &&
        edorig.compareTo(edef2) == 0) {
        System.out.println("# ERounding.None fails to detect rounding was" +
          "\u0020unnecessary");
        if (str == null) {
          throw new NullPointerException("str");
        }
        System.out.println("# str = " + str.substring(0,Math.min(str.length(),
              200)) + (str.length() > 200 ? "..." : "") + "\n# ec = " +
          ECString(ec));
        System.out.println("# ed = " + edorig);
        System.out.println("# edef2 = " + edef2);
        System.out.println("# ef3 = " + ef3.toString());
      }

      if (!ed.equals(ed2)) {
        if (ec == null) {
          throw new NullPointerException("ec");
        }
        if (str == null) {
          throw new NullPointerException("str");
        }
        String bstr = "";
        if (ec.getHasMaxPrecision()) {
          EContext ecf = ec.WithBlankFlags();
          EDecimal.FromString(str).RoundToPrecision(ecf);
          bstr += "# " + ecf.getPrecision() + " / " + ec.getPrecision() + "\r\n";
          bstr += DecTestUtil.ContextToDecTestForm(ecf);
          bstr += "untitled toSci " + str + " -> " + ed.toString() +
            " " + DecTestUtil.FlagsToString(ecf.getFlags()) + "\n";
          str = ed2.toString();
          bstr += "# exponent: actual " + ed2.getExponent() + ", expected " +
            ed.getExponent() + "\n";
          bstr += "# was: " + str.substring(0,Math.min(str.length(), 200)) +
            (str.length() > 200 ? "..." : "");
        } else {
          bstr += "# " + str.substring(0,Math.min(str.length(), 200)) +
            (str.length() > 200 ? "..." : "");
        }
        bstr += "\n# " + ECString(ec);
        throw new IllegalStateException(bstr);
      }
    }

    public static String ECString(EContext ec) {
      StringBuilder sb = new StringBuilder().append("EContext.Unlimited");
      if (ec == null) {
        throw new NullPointerException("ec");
      }
      if (ec.getHasMaxPrecision()) {
        sb.append(".WithPrecision(" + ec.getPrecision().toString() + ")");
      }
      if (ec.getHasExponentRange()) {
        sb.append(".WithExponentRange(" + ec.getEMin().toString() + "," +
          "\u0020" + ec.getEMax().toString() + ")");
      }
      sb.append(".WithRounding(ERounding." + ec.getRounding() + ")");
      sb.append(".WithAdjustExponent(" + (ec.getAdjustExponent() ? "true" :
          "false") + ")");
      sb.append(".WithExponentClamp(" + (ec.getClampNormalExponents() ? "true" :
          "false") + ")");
      sb.append(".WithSimplified(" + (ec.isSimplified() ? "true" : "false") +
        ")");
      if (ec.getHasFlags()) {
        sb.append(".WithBlankFlags()");
      }
      if (ec.getTraps() != 0) {
        sb.append(".WithTraps(" + TestCommon.IntToString(ec.getTraps()) + ")");
      }
      return sb.toString();
    }

    public static void TestStringContextOneEFloatSimple(String str, EContext
      ec) {
      TestStringContextOneEFloat(str, null, ec, true);
    }

    public static void TestStringContextOneEFloat(String str, EContext ec) {
      TestStringContextOneEFloat(str, null, ec, false);
    }

    public static void TestStringContextOneEFloat(
      String str,
      EDecimal ed,
      EContext ec) {
      TestStringContextOneEFloat(str, ed, ec, false);
    }

    public static void TestStringContextOneEFloat(
      String str,
      EDecimal ed,
      EContext ec,
      boolean noLeadingZerosTest) {
      if (ec == null) {
        throw new NullPointerException("ec");
      }
      if (str == null) {
        throw new NullPointerException("str");
      }
      // System.out.println("EFloat size="+str.length());
      String leadingZeros = TestCommon.Repeat('0', 800);
      int[] counts = {
        0, 1, 2, 50, 200, 600, 800,
      };
      ed = (ed == null) ? (EDecimal.FromString("xyzxyz" + str, 6, str.length())) : ed;
      if (ed == null) {
        Assert.fail();
      }
      EFloat ef = ed.ToEFloat(ec);
      for (int i = 0; i < counts.length; ++i) {
        // Parse a String with leading zeros (to test whether
        // the 768-digit trick delivers a correctly rounded EFloat
        // even if the String has leading zeros)
        TestStringContextOneEFloatCore(
          leadingZeros.substring(0, counts[i]) + str,
          ec,
          ed,
          ef);
        if (noLeadingZerosTest || str.length() == 0 || str.charAt(0) == '-') {
          break;
        }
      }
    }

    // Test potential cases where FromString is implemented
    // to take context into account when building the EFloat
    public static void TestStringContextOneEFloatCore(
      String str,
      EContext ec,
      EDecimal ed,
      EFloat ef) {
      if (ec == null) {
        throw new NullPointerException("ec");
      }
      if (str == null) {
        throw new NullPointerException("str");
      }
      EFloat ef2 = null;
      EContext noneRounding = ec.WithRounding(
          ERounding.None);
      EContext downRounding = ec.WithRounding(ERounding.Down);
      ef2 = EFloat.FromString("xyzxyz" + str, 6, str.length(), ec);
      EFloat ef3 = EFloat.NaN;
      ef3 = EFloat.FromString(str, noneRounding);
      EDecimal edef2 = (ec.getRounding() == ERounding.Down ?
          ef2 : EFloat.FromString(str, downRounding)).ToEDecimal();
      if ((ef3 != null && !ef3.IsNaN()) && ed != null &&
        ed.compareTo(edef2) != 0) {
        System.out.println("# ERounding.None fails to detect rounding was" +
          "\u0020necessary");
        if (str == null) {
          throw new NullPointerException("str");
        }
        System.out.println("# str = " + str.substring(0,Math.min(str.length(),
              200)) + (str.length() > 200 ? "..." : "") + "\n# ec = " +
          ECString(ec));
        System.out.println("# ef3 = " + ef3.toString());
      } else if ((ef3 == null || ef3.IsNaN()) && ed != null &&
        ed.compareTo(edef2) == 0) {
        System.out.println("# ERounding.None fails to detect rounding was" +
          "\u0020unnecessary");
        if (str == null) {
          throw new NullPointerException("str");
        }
        System.out.println("# str = " + str.substring(0,Math.min(str.length(),
              200)) + (str.length() > 200 ? "..." : "") + "\n# ec = " +
          ECString(ec));
        System.out.println("# ed = " + ed);
        System.out.println("# edef2 = " + edef2);
        System.out.println("# ef3 = " + ef3.toString());
      }
      if (ef == null || ef2 == null) {
        return;
      }
      if (ef.compareTo(ef2) != 0) {
        if (ec == null) {
          throw new NullPointerException("ec");
        }
        if (str == null) {
          throw new NullPointerException("str");
        }
        String bstr = "";
        if (ec.getHasMaxPrecision()) {
          EContext ecf = ec.WithBlankFlags();
          // bstr += DecTestUtil.ContextToDecTestForm(ecf);
          // bstr += "untitled toSci " + str + " -> " + ef.toString() +
          // DecTestUtil.FlagsToString(ecf.getFlags()) + "\n";
          bstr += "{\nEContext ec = " + ECString(ec) + ";\n";
          bstr += "String str = \"" + str + "\";\n";
          bstr += "EDecimalTest.TestStringContextOneEFloat(str, ec);\n}\n";
          str = ef2.toString();
          bstr += "// expected: " + EFloatTest.OutputEF(ef) + "\n";
          bstr += "// was: " + EFloatTest.OutputEF(ef2) + "\n";
          bstr += "// was: " + str.substring(0,Math.min(str.length(), 200)) +
            (str.length() > 200 ? "..." : "");
        } else {
          bstr += "# " + str.substring(0,Math.min(str.length(), 200)) +
            (str.length() > 200 ? "..." : "");
          bstr += "\n# " + ECString(ec);
        }
        throw new IllegalStateException(bstr);
      }
    }

    private static void AppendZeroFullDigits(
      StringBuilder sb,
      RandomGenerator rand,
      int count) {
      for (int i = 0; i < count; ++i) {
        if (rand.UniformInt(100) < 30) {
          sb.append('0');
        } else {
          int c = 0x30 + rand.UniformInt(10);
          sb.append((char)c);
        }
      }
    }

    private static void AppendNines(
      StringBuilder sb,
      int prec,
      int point) {
      if (point >= 0) {
        sb.append(TestCommon.Repeat("9", point)).append(".");
        sb.append(TestCommon.Repeat("9", prec - point));
      } else {
        sb.append(TestCommon.Repeat("9", prec));
      }
    }

    private static void AppendDigits(
      StringBuilder sb,
      RandomGenerator rand,
      int prec,
      int point) {
      String[] digits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
      if (rand.UniformInt(100) < 30) {
        if (point >= 0) {
          AppendZeroFullDigits(sb, rand, point);
          sb.append(".");
          AppendZeroFullDigits(sb, rand, prec - point);
        } else {
          AppendZeroFullDigits(sb, rand, prec);
        }
      } else {
        int digit = rand.UniformInt(10);
        if (point >= 0) {
          sb.append(TestCommon.Repeat(digits[digit], point)).append(".");
          sb.append(TestCommon.Repeat(digits[digit], prec - point));
        } else {
          sb.append(TestCommon.Repeat(digits[digit], prec));
        }
      }
    }

    @Test
    public void TestLeadingTrailingPoint() {
      Assert.assertEquals(EDecimal.FromString("4"), EDecimal.FromString("4."));
      Assert.assertEquals(EDecimal.FromString("0.4"), EDecimal.FromString(".4"));
      {
        Object objectTemp = EDecimal.FromString("4e+5");
        Object objectTemp2 = EDecimal.FromString(
            "4.e+5");
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      Assert.assertEquals(
        EDecimal.FromString("99999999999"),
        EDecimal.FromString("99999999999."));
      Assert.assertEquals(EDecimal.FromString("0.99999999999"),
        EDecimal.FromString(".99999999999"));
      Assert.assertEquals(
        EDecimal.FromString("99999999999e+5"),
        EDecimal.FromString("99999999999.e+5"));
    }

    @Test
    public void TestStringContextSpecificEFloat1() {
      StringBuilder sb = new StringBuilder();
      EContext ec = EContext.Basic.WithPrecision(53).WithExponentClamp(true)
        .WithAdjustExponent(true).WithExponentRange(-1022, 1023)
        .WithRounding(ERounding.Up);
      TestStringContextOne("73007936310086.7E-383", ec);
    }

    @Test
    public void TestStringContextSpecific5() {
      StringBuilder sb = new StringBuilder();
      EContext ec = EContext.Basic.WithPrecision(7).WithExponentClamp(true)
        .WithAdjustExponent(true).WithExponentRange(-95, 96)
        .WithRounding(ERounding.HalfUp);
      AppendNines(sb, 400, 283);
      sb.append("E-384");
      TestStringContextOne(sb.toString(), ec);
    }

    @Test
    public void TestStringContextSpecific7a() {
      EContext ec = EContext.Unlimited.WithPrecision(
          9).WithRounding(
          ERounding.HalfEven).WithAdjustExponent(
          true).WithExponentClamp(false).WithSimplified(false);
      String str =

        "-54887844326635" + TestCommon.Repeat("0",
          1562) + "." + TestCommon.Repeat("0", 613) + "";
      EDecimal ed = EDecimal.FromString(str, ec);
      Assert.assertEquals(EInteger.FromInt32(1567), ed.getExponent());
      TestStringContextOne(str, ec);
    }

    @Test
    public void TestStringContextSpecific6() {
      StringBuilder sb = new StringBuilder();
      EContext ec = EContext.Basic.WithPrecision(7).WithExponentClamp(true)
        .WithAdjustExponent(true).WithExponentRange(-95, 96)
        .WithRounding(ERounding.HalfUp);
      AppendNines(sb, 400, 284);
      sb.append("E-385");
      TestStringContextOne(sb.toString(), ec);
    }

    @Test
    public void TestStringContextSpecific6a() {
      StringBuilder sb = new StringBuilder();
      EContext ec = EContext.Basic.WithPrecision(11).WithExponentClamp(true)
        .WithAdjustExponent(true).WithExponentRange(-14, 15)
        .WithRounding(ERounding.HalfDown);
      String str =

  "00726010602910507435000059115940090202200019076401000797770037005004100060.0201983258000005067E-96";
      TestStringContextOne(str, ec);
    }
    @Test
    public void TestStringContextSpecific6b() {
      EContext ec = EContext.Unlimited.WithPrecision(19).WithExponentRange(
          -353,
          354).WithRounding(
          ERounding.Down).WithAdjustExponent(
          true).WithExponentClamp(false).WithSimplified(false);
      String str =

  "-66534305690092119160982082298.6117943850214200578102634919915948108612006388913171795430645264401356581996334057250931572660709458186036652420658265819852148081219105264213931192572998661457444645337884797981765786873164189138929912494098111738350059458760047160519097327194211787079276441238232334641699878542658228963741574816826153104358339605788240136295139854413718595196475956646218991803499475021107664682894173676216342285575942409166581979296601101999863633968466436458631641517841792";
      TestStringContextOneEFloat(str, ec);
    }

    @Test
    public void TestStringContextSpecific6c() {
      EContext ec = EContext.Unlimited.WithPrecision(11).WithExponentRange(-14,
          15).WithRounding(
          ERounding.Up).WithAdjustExponent(
          false).WithExponentClamp(true).WithSimplified(false);
      String str = "111111111111111." + TestCommon.Repeat("1", 87) + "E+3";
      TestStringContextOneEFloat(str, ec);
    }
    @Test
    public void TestFromStringSubstring() {
      String tstr =

  "-3.00931381333368754713014659613049757554804012787921371662913692598770508705049030832574634419795955864174175076186656951904296875000E-49";
      try {
        EDecimal.FromString(
          "xyzxyz" + tstr,
          6,
          tstr.length());
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString(
          ("xyzxyz" + tstr).toCharArray(),
          6,
          tstr.length());
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString("xyzxyz" + tstr, 6, tstr.length());
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString(tstr, 0, tstr.length());
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(tstr, 0, tstr.length());
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.FromString(
          tstr + "xyzxyz",
          0,
          tstr.length());
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.FromString(tstr + "xyzxyz", 0, tstr.length());
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

       // Under the General Decimal Arithmetic Specification,
       // power(1.0, -integer) should act the same as power(1/1.0, integer)
    @Test
    public void TestPowerOneExpNegativeInteger() {
     String str = "precision: 16\nrounding: half_even\nminexponent:" +
"\u0020-383\nmaxexponent: 384\nextended: 1\n" +
        "custom_power_12767 power 1.0 -6290 -> 1 ";
     DecTestUtil.ParseDecTests(str, false);
     }
    @Test
    public void TestPowerOneExpNegativeInteger2() {
     String str = "precision: 16\nrounding: half_even\nminexponent:" +
"\u0020-383\nmaxexponent: 384\nextended: 1\ncustom_power_1330 power 1.000" +
"\u0020-9287 ->" +
"\u00201"; // "1";
     DecTestUtil.ParseDecTests(str, false);
    }

    @Test
    public void TestPowerOneExpNonInteger() {
      // In the General Decimal Arithmetic Specification, power(1, noninteger)
      // is ((specified instanceof inexact) ? (inexact)specified : null), perhaps due to an oversight
      String str = "precision: 34\nrounding: half_even\nminexponent: " +
        "-6143\nmaxexponent: 6144\nextended: 1\n" +
        "untitled power 1.0 -268575.66 -> " +
        "1." + TestCommon.Repeat("0", 33) + " Inexact Rounded";
      DecTestUtil.ParseDecTests(
        str,
        false);
    }

    @Test
    public void TestDecTestSpecificPower4() {
     String str = "precision: 34\nrounding: half_even\nminexponent: " +
"-6143\nmaxexponent: 6144\nextended: 1\ncustom_power_4373 power " +
"6.69368032938021915 -52.62 -> 3.576921343298362853247641466638896E-44 " +
"Inexact Rounded";
// "3.5769213432983628532476414666388955000992271E-44";
     DecTestUtil.ParseDecTests(str, false);
    }

    @Test
    public void TestDecTestSpecificPower5() {
     String str = "precision: 34\nrounding: half_even\nminexponent:" +
"\u0020-6143\nmaxexponent: 6144\nextended: 1\ncustom_power_9740 power 3.724" +
"\u0020-6856" +
"\u0020-> 1.437760829269881315895729920406088E-3915 Inexact Rounded";
// "\u00201.4377608292698813158957299204060875000448219E-3915";
     DecTestUtil.ParseDecTests(str, false);
    }

    @Test
    public void TestDecTestSpecificPower6() {
     String str = "precision: 34\nrounding: half_even\nminexponent:" +
"\u0020-6143\nmaxexponent: 6144\nextended: 1\ncustom_power_17409 power" +
"\u00200.009326" +
"\u0020-75.59 -> 2.956132346267103882996614250892197E\u002b153 Inexact Rounded";
// "\u00202.9561323462671038829966142508921974999579417E+153";
     DecTestUtil.ParseDecTests(str, false);
    }
    @Test
    public void TestDecTestSpecificPower7() {
     String str = "precision: 34\nrounding: half_even\nminexponent:" +
"\u0020-6143\nmaxexponent: 6144\nextended: 1\ncustom_power_19421 power" +
"\u00201.6026491E-8" +
"\u0020-91.7 -> 6.551106896440510811119799436358778E\u002b714 Inexact Rounded";
// "\u00206.5511068964405108111197994363587784994316246E+714";
     DecTestUtil.ParseDecTests(str, false);
    }
    @Test
    public void TestDecTestSpecificPower8() {
     String str = "precision: 16\nrounding: half_even\nminexponent:" +
"\u0020-383\nmaxexponent: 384\nextended: 1\ncustom_power_14214 power -0.05304" +
"\u0020-7.000 -> -846789359.6071463 Inexact Rounded";
     DecTestUtil.ParseDecTests(str, false);
    }
    @Test
    public void TestDecTestSpecificPower9() {
     String str = "precision: 34\nrounding: half_even\nminexponent:" +
"\u0020-6143\nmaxexponent: 6144\nextended: 1\ncustom_power_18478 power" +
"\u00200.007432" +
"\u0020-66.89 -> 2.521968518855321930003285016375665E\u002b142 Inexact Rounded";
// "\u00202.5219685188553219300032850163756645000465660E+142";
     DecTestUtil.ParseDecTests(str, false);
    }

    @Test
    public void TestDecTestSpecificPower1() {
     String str = "precision: 34\nrounding: half_even\nminexponent: -6143\n" +
         "maxexponent: 6144\nextended:1\n" +
         "custom_power_16 power 0.07585 -756.0 -> " +
         "5.669929347251241273640310019664757E+846 Inexact Rounded";
     DecTestUtil.ParseDecTests(
       str,
       false);
    }

    @Test
    public void TestDecTestSpecificPower2() {
     String str = "precision: 34\nrounding: half_even\nminexponent: -6143\n" +
         "maxexponent: 6144\nextended:1\n" +
         "custom_power_16 power 0.0060351448 -472.9 -> " +
         "3.256786372719241448264997647923555E+1049 Inexact Rounded";
     DecTestUtil.ParseDecTests(
       str,
       false);
    }

    @Test
    public void TestDecTestSpecificPower3() {
     String str = "precision: 34\nrounding: half_even\nminexponent: -6143\n" +
         "maxexponent: 6144\nextended:1\n" +
         "custom_power_1334 power 0.04749 -448.8 -> " +
         "8.728634162796843910279804297263345E+593 Inexact Rounded";
     DecTestUtil.ParseDecTests(
       str,
       false);
    }

    @Test
    public void TestStringContextSpecificMore() {
      {
        String str = "precision: 7\nrounding: half_down\nmaxexponent: " +
          "96\n" + "minexponent: -95\nextended: 1\nclamp: 1\nuntitled toSci " +
          "555555555555555555E-94 -> 5.555556E-77 Inexact Rounded";
        DecTestUtil.ParseDecTests(
          str,
          false);
        EContext ec = EContext.Basic.WithPrecision(7).WithExponentClamp(true)
          .WithAdjustExponent(false).WithExponentRange(-95, 96)
          .WithRounding(ERounding.HalfEven);
        TestStringContextOne("487565.00310E-96", ec);
      }
      {
        EContext ec =
          EContext.Unlimited.WithPrecision(53).WithExponentRange(-1022,
            1023).WithRounding(ERounding.Down).WithAdjustExponent(
            true).WithExponentClamp(true).WithSimplified(false);
        String str = TestCommon.Repeat("6", 4605) + "." +
          TestCommon.Repeat("6",
            1538) + "E-999";
        TestStringContextOneEFloat(str, ec);
      }

      {
        EContext ec =
          EContext.Unlimited.WithPrecision(53).WithExponentRange(-1022,
            1023).WithRounding(ERounding.Ceiling).WithAdjustExponent(
            true).WithExponentClamp(true).WithSimplified(false);
        String str = TestCommon.Repeat("2", 6125) + "E-6143";
        TestStringContextOneEFloat(str, ec);
      }
      {
        EContext ec =
          EContext.Unlimited.WithPrecision(53).WithExponentRange(-1022,
            1023).WithRounding(ERounding.Down).WithAdjustExponent(
            true).WithExponentClamp(true).WithSimplified(false);
        String str = TestCommon.Repeat("2", 6165) + "E-6144";
        TestStringContextOneEFloat(str, ec);
      }
      {
        EContext ec =
          EContext.Unlimited.WithPrecision(53).WithExponentRange(-1022,
            1023).WithRounding(ERounding.HalfDown).WithAdjustExponent(
            true).WithExponentClamp(true).WithSimplified(false);
        String str =

  "0073000021960980007305056484911080609040458307738480635500600457003065700100902090896615030732652553075037501950247305214001000609697011805466300376799178890090568606761710483020290709180410260358805508079072601000651506000108202180008505073022007850910039820241008201400236050050246900250895790030707320060332426231940803760330870307891088002560602070261700009603405009775900250580042858910209705051402664037402890735007100990890000080400608930007400063761001060038422005000901008707958734420510029017800102078015560159466901008804187630045304308036045704003720440800815040565098720007028200106057003008686040653375800708409606600950560830030801760629754000760076605050983054030300803305080990620430700840009286601907275803099010881000640070927000080000008405306277002008029320337060582690200276900884274000901881004000310130000010529021820708007358724730000264010468066140278717080644405250055023049005410260170061028282960000387000838458809907775526543276066028052520064503000100807184090046410708762022080505049057381004290108034510779400054092008480560904006710454005310519093930221902122039610110010310904999053064480005347036006292155510100964159041000000080600200100953050100060302304400634050055060861170040001026314715506000820010098082607848000300070322081050000867074060748210041090041240035812005308214300200822920208400270023090003110479608027914239601710012061077000000001366560070009908108210007200750510209013151790093000730800276080250400008507803982439000000100669040403095399000960069280699214017925506071788615943730001561163245370150912016780305000103085005002106910768477605450240090047901050014022241930983590520881960050016606569010002040010370729830045000941024068389350459300480300460129770020070002010620036091110067768612651641004505298037850000830052791040670031808464000400917134087560809900804039066079020994020480506102558203708801200020055570506007057370403232340060613900619082084008555809560002551508005090489460838796507547672109005864200042100300800202808375953761040902004824600723084003003588014870008004933604890506600283627630387525107610907076802062000707616040007004667672660696000803953009260500830623049700845959005030500345031202000705360600000390110950008663000307062070324006059000068150026170125510711631670061503700990508040003960989089053312086107836920027007406700037704711600129067105252600004920298536005058003378580770002980990070504061768302300582325530610900070088099090323600407001087000440930030462709046304080073000593087600000344409005232117218901836766839909000807090823449060248032004031900400110470891000900601010431160000070805200609304320030476297778010900166400814109652150659007000004880205940084400030100534010300901904843027618403007084007410069934011066490027980605006620507000062039401035282097008512010185715616200003399000005045063608090067334002280090404390702501970603454069394005030700607001900941079758000519044404070855813207865778701005089030470063099108055888010000003500002327800542400707001714596712000400990450450094099170006070110660063300076733101806000077642101002303300105052300040581500702107300406209568326733460000050017760050800006777088068746920122152940222758028000744600119619246910290007803096261105023025068095480520103800879006903026138000075065382650022776414190069820188028458006098597000080229201880402170000023708000300233407460107004002100620601037785440008078000275604004145076507670153209445808502222360303327008009610000262892306003242430010300100300507208955280567155839100609759005082306090914146902008430007000356015185204375040562052010010106300370778308000027123420720080032072501006300902004395951040000035010055000070046080700080705006107706038049944005988070566700080000060273468025208869030759887691081340130051960076000810308727414108000040900005603783030824102080636044620119003403060406395605000000809000049693800000740740548264030900006320682153099988100417093340603621381680099828609008563245960739002010070080007093003000804025952503731474790226099261408010000790030302007718888342790470174084922301628701101613035300282601960820160194870077649252500091663420030054007077905000998009400886261390733103000189409201720888050111740989704007008606106600080833500509085085930806453406370977000439088350070539980687301901401050603080033918420570708904206031303051194000941044249882850350287700217022108640280058008034061255000708900005700182580214020076828009160506803309889379942603028064058021500016134500890569952490601000374000006654005340022209600080005977940420305900046963005063030214062337813016809210709203009208235500500922080001007072405558870306333550007038602140692020099072607760612384761090084000690025083900500374028101052519672720000485054141000704300306977025038900163623020400088010119763150804236200401850001001409500077000303050003009603400650005700902000072307202045707346000000167205713464110440039353600000399904610342901843008000005594505070080205000700000053290017653000628060060690030314284647630000227060008200230620510520606048052224017172525569283900900608092340909200258090778365000018156400220054455624700957208070003317950118450666253954414360904060640038749620706821283188030017057660780030100200874602220708065100205806002801008020070900349008951201003309048007037020034030719039090741410281099409002350082853006190004501527010804016220045556053282803803963075710866040620903150683367989410091680380309001070040036004901694203009806750070136107031500028738630130672000108030203200903203164022990715040206153000080002807000089400800510090316601000897800270023400240061092603021855008103721301372064900094700101400099060000370001204460046540253039940435053492530192158900041900801069002870000208420076700840560446600901800000007005210091070230011680030100844939703300020814109588024500654010010591923310010000258070220317280470889600010630004302304116034597040040400079961220303019772608870000007900080070807270804860260006000180013914550718684524093070000900960002370080001006099036005109603605005000000720290690207143018030912104700417603710790049567030554608500000490900420008109490930680201788168084422807403098800879740010800510180314329503000090907036709400073080057602060260501004308490265400394E-6200";
        TestStringContextOneEFloat(str, ec);
      }
    }

    @Test
    public void TestEDecimalEFloatWithHighExponent() {
      String decstr = "0E100441809235791722330759976";
      Assert.assertEquals(0L, EDecimal.FromString(decstr).ToDoubleBits());
      Assert.assertEquals(0L, EFloat.FromString(decstr).ToDoubleBits());
    {
        Object objectTemp = 0L;
        Object objectTemp2 = EDecimal.FromString(decstr,
  EContext.Decimal32).ToDoubleBits();
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      {
        Object objectTemp = 0L;
        Object objectTemp2 = EFloat.FromString(decstr,
  EContext.Binary64).ToDoubleBits();
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      decstr = "0E-100441809235791722330759976";
      Assert.assertEquals(0L, EDecimal.FromString(decstr).ToDoubleBits());
      Assert.assertEquals(0L, EFloat.FromString(decstr).ToDoubleBits());
    {
        Object objectTemp = 0L;
        Object objectTemp2 = EDecimal.FromString(decstr,
  EContext.Decimal32).ToDoubleBits();
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      {
        Object objectTemp = 0L;
        Object objectTemp2 = EFloat.FromString(decstr,
  EContext.Binary64).ToDoubleBits();
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      decstr = "-0E100441809235791722330759976";
      long negzero = 1L << 63;
      Assert.assertEquals(negzero, EDecimal.FromString(decstr).ToDoubleBits());
      Assert.assertEquals(negzero, EFloat.FromString(decstr).ToDoubleBits());
    {
        Object objectTemp = negzero;
        Object objectTemp2 = EDecimal.FromString(decstr,
  EContext.Decimal32).ToDoubleBits();
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      {
        Object objectTemp = negzero;
        Object objectTemp2 = EFloat.FromString(decstr,
  EContext.Binary64).ToDoubleBits();
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      decstr = "-0E-100441809235791722330759976";
      Assert.assertEquals(negzero, EDecimal.FromString(decstr).ToDoubleBits());
      Assert.assertEquals(negzero, EFloat.FromString(decstr).ToDoubleBits());
    {
        Object objectTemp = negzero;
        Object objectTemp2 = EDecimal.FromString(decstr,
  EContext.Decimal32).ToDoubleBits();
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      {
        Object objectTemp = negzero;
        Object objectTemp2 = EFloat.FromString(decstr,
  EContext.Binary64).ToDoubleBits();
        Assert.assertEquals(objectTemp, objectTemp2);
      }
    }

    @Test
    public void TestRescaleInvalid() {
      HashMap<String, String> context = new HashMap<String, String>();
      context.put("precision","9");
      context.put("rounding","half_up");
      context.put("maxexponent","96");
      context.put("minexponent","-96");
      {
        String objectTemp = "rr rescale 12345678.9 -2 -> NaN" +
          " Invalid_operation";
        DecTestUtil.ParseDecTest(objectTemp, context);
      }
      {
        String stringTemp = "rr quantize 12345678.9 0e-2 -> NaN" +
          " Invalid_operation";
        DecTestUtil.ParseDecTest(stringTemp, context);
      }
    }

    public static EContext RandomEFloatContext(IRandomGenExtended r) {
      return RandomEFloatContext(r, 20000);
    }

    public static EContext RandomEFloatContext(IRandomGenExtended r, int
      maxExponent) {
      if (r == null) {
        throw new NullPointerException("r");
      }
      int prec = 1 + r.GetInt32(53);
      int emax = 1 + r.GetInt32(maxExponent);
      int emin = -(emax - 1);
      ERounding[] roundings = {
        ERounding.Down, ERounding.Up,
        ERounding.OddOrZeroFiveUp, ERounding.HalfUp,
        ERounding.HalfDown, ERounding.HalfEven,
        ERounding.Ceiling, ERounding.Floor,
      };
      ERounding rounding = roundings[r.GetInt32(roundings.length)];
      return EContext.Unlimited.WithPrecision(prec)
        .WithExponentRange(emin, emax).WithRounding(rounding)
        .WithSimplified(false).WithAdjustExponent(r.GetInt32(2) == 0)
        .WithExponentClamp(r.GetInt32(2) == 0);
    }

    public static EContext RandomEDecimalContext(IRandomGenExtended r) {
      if (r == null) {
        throw new NullPointerException("r");
      }
      int prec = 1 + r.GetInt32(100000);
      int emax = 1 + r.GetInt32(20000);
      int emin = -(emax - 1);
      ERounding[] roundings = {
        ERounding.Down, ERounding.Up,
        ERounding.OddOrZeroFiveUp, ERounding.HalfUp,
        ERounding.HalfDown, ERounding.HalfEven,
        ERounding.Ceiling, ERounding.Floor,
      };
      ERounding rounding = roundings[r.GetInt32(roundings.length)];
      return EContext.Unlimited.WithPrecision(prec)
        .WithExponentRange(emin, emax).WithRounding(rounding)
        .WithSimplified(false).WithAdjustExponent(r.GetInt32(2) == 0)
        .WithExponentClamp(r.GetInt32(2) == 0);
    }

    @Test
    public void TestStringContext() {
      System.out.println("TestStringContextEFloat");
      TestStringContextEFloat();
      System.out.println("TestStringContextEDecimal");
      TestStringContextEDecimal();
    }

    public static void TestStringContextEDecimal() {
      EContext[] econtexts = {
        EContext.Basic,
        EContext.Decimal32,
        EContext.Decimal32.WithAdjustExponent(false),
        EContext.Decimal64,
        EContext.Decimal64.WithAdjustExponent(false),
        /* EContext.Basic.WithExponentRange(-95, 96),
        EContext.Basic.WithAdjustExponent(false),
        EContext.Decimal32.WithExponentClamp(true),
        EContext.Decimal32.WithExponentClamp(true).WithAdjustExponent(false),
        EContext.BigDecimalJava,
        EContext.BigDecimalJava.WithAdjustExponent(true),
        EContext.BigDecimalJava.WithExponentClamp(true),
        EContext.BigDecimalJava.WithExponentClamp(true).WithAdjustExponent(
        false),
        EContext.Unlimited.WithExponentRange(-64, 64),
        */
      };
      TestStringContextCore(econtexts, false);
    }

    public static void TestStringContextEFloat() {
      EContext[] econtexts = {
        EContext.Binary64,
        EContext.Binary64.WithExponentRange(-95, 96),
        EContext.Binary64.WithAdjustExponent(false),
        EContext.Binary32,
        EContext.Binary32.WithAdjustExponent(false),
        EContext.Binary32.WithExponentClamp(true),
        EContext.Binary32.WithExponentClamp(true).WithAdjustExponent(false),
        EContext.Binary16,
        EContext.Binary16.WithAdjustExponent(false),
        EContext.Binary16.WithExponentClamp(true),
        EContext.Binary16.WithExponentClamp(true).WithAdjustExponent(false),
        // EContext.Unlimited.WithExponentRange(-64, 64),
      };
      TestStringContextCore(econtexts, true);
    }

    public static byte[] StringToBytes(String str) {
      if (str == null) {
        throw new NullPointerException("str");
      }
      byte[] bytes = new byte[str.length()];
      for (int i = 0; i < str.length(); ++i) {
        byte b = (byte)(str.charAt(i) >= 0x80 ? (byte)'?' : (byte)str.charAt(i));
        bytes[i] = b;
      }
      return bytes;
    }

    public static void TestStringContextCore(EContext[] econtexts, boolean
      efloat) {
      if (econtexts == null) {
        throw new NullPointerException("econtexts");
      }
      ERounding[] roundings = {
        ERounding.Down, ERounding.Up,
        ERounding.OddOrZeroFiveUp, ERounding.HalfUp,
        ERounding.HalfDown, ERounding.HalfEven,
        ERounding.Ceiling, ERounding.Floor,
      };
      int[] exponents = {
        94, 95, 96, 97,
        384, 383, 385,
        6144, 6200, 6143,
        10000, 0, 1, 2, 3, 4, 5, 10, 20, 40, 60,
        70, 80, 90,
        214748362, 214748363, 214748364, 214748365,
        Integer.MAX_VALUE, Integer.MAX_VALUE - 1,
      };
      int[] precisionRanges = {
        1, 7,
        1, 7,
        1, 20,
        1, 20,
        1, 20,
        1, 20,
        1, 20,
        1, 20,
        1, 20,
        90, 120,
        90, 120,
        90, 120,
        370, 400,
        370, 400,
        370, 400,
        1000, 1500,
        1000, 1500,
        6100, 6200,
        740, 800,
      };
      String[] digits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
      RandomGenerator rand = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        if (i % 10 == 0) {
          System.out.println(i);
        }
        int precRange = rand.UniformInt(precisionRanges.length / 2) * 2;
        int exponent = exponents[rand.UniformInt(exponents.length)];
        int prec = precisionRanges[precRange] +
          rand.UniformInt(1 + (precisionRanges[precRange + 1] -
              precisionRanges[precRange]));
        precRange = rand.UniformInt(precisionRanges.length / 2) * 2;
        int eprec = precisionRanges[precRange] +
          rand.UniformInt(1 + (precisionRanges[precRange + 1] -
              precisionRanges[precRange]));
        int point = -1;
        if (rand.UniformInt(2) == 0) {
          point = rand.UniformInt(prec);
          if (point == 0) {
            point = -1;
          }
        }
        String sbs;
        if (rand.UniformInt(100) < 98) {
          StringBuilder sb = new StringBuilder();
          AppendDigits(sb, rand, prec, point);
          sb.append(rand.UniformInt(2) == 0 ? "E+" : "E-");
          if (rand.UniformInt(100) < 10) {
            AppendDigits(sb, rand, eprec, -1);
          } else {
            sb.append(TestCommon.LongToString(exponent));
          }
          sbs = sb.toString();
        } else {
          sbs = RandomObjects.RandomDecimalString(rand);
        }
        EDecimal ed = EDecimal.FromString("xyzxyz" + sbs, 6, sbs.length());
        if (rand.UniformInt(100) < 10) {
          EDecimal ed2 = EDecimal.FromString(("xyzxyz" + sbs).toCharArray(),
  6,
  sbs.length());
          Assert.assertEquals(ed, ed2);
        }
        if (rand.UniformInt(100) < 10) {
          EDecimal ed2 = EDecimal.FromString(StringToBytes("xyzxyz" + sbs),
  6,
  sbs.length());
          Assert.assertEquals(ed, ed2);
        }
        for (int j = 0; j < econtexts.length; ++j) {
          ERounding rounding = roundings[rand.UniformInt(roundings.length)];
          EContext ec = econtexts[j];
          ERounding thisrounding = ec.getRounding();
          ec = (rounding == thisrounding) ? ec : ec.WithRounding(rounding);
          if (efloat) {
            TestStringContextOneEFloat(sbs, ed, ec, false);
          }
          TestStringContextOne(sbs, ed, ec);
        }
      }
      TearDown();
    }
  }
