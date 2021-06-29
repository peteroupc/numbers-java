package com.upokecenter.test;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.util.*;
import com.upokecenter.numbers.*;

  public class EFloatTest {
    public static EFloat FromBinary(String str) {
      int smallExponent = 0;
      int index = 0;
      EInteger ret = EInteger.FromInt32(0);
      if (str == null) {
        throw new NullPointerException("str");
      }
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

    public static boolean TestCompareToBinary2(EFloat ef) {
      if (ef == null) {
        throw new NullPointerException("ef");
      }
      if (!ef.isFinite() || ef.signum() == 0) {
        return false;
      }
      ef = ef.Abs();
      int cmp;
      EDecimal ed = EDecimal.FromEFloat(ef);
      cmp = ed.CompareToBinary(ef);
      Assert.assertEquals(0, cmp);
      EDecimal ednew = EDecimal.Create(ed.getMantissa().Add(1), ed.getExponent());
      cmp = ednew.compareTo(ed);
      Assert.assertEquals(1, cmp > 0 ? 1 : cmp);
      ed = ednew;
      cmp = ed.CompareToBinary(ef);
      Assert.assertEquals(1, cmp > 0 ? 1 : cmp);
      //----Rational
      ERational er = ERational.FromEFloat(ef);
      cmp = er.CompareToBinary(ef);
      Assert.assertEquals(0, cmp);
      er = ERational.Create(er.getNumerator().Add(1), er.getDenominator());
      cmp = er.CompareToBinary(ef);
      Assert.assertEquals(1, cmp > 0 ? 1 : cmp);
      return true;
    }

    @Test
    public void TestCompareToBinary2Test() {
      RandomGenerator rg = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        TestCompareToBinary2(RandomObjects.RandomEFloat(rg));
      }
    }
    @Test
    public void TestFromBoolean() {
      Assert.assertEquals(EFloat.Zero, EFloat.FromBoolean(false));
      Assert.assertEquals(EFloat.One, EFloat.FromBoolean(true));
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
    public void TestDivSpecial() {
      RandomGenerator rg = new RandomGenerator();
      EContext ec = EContext.Unlimited.WithPrecision(2048);
      for (int i = 0; i < 1000; ++i) {
        int a = rg.GetInt32(900) + 1;
        int b = rg.GetInt32(900) + 1;
        EFloat ef = EFloat.Create(
            EInteger.FromInt32(1).ShiftLeft(a).Subtract(1),
            EInteger.FromInt32(-a));
        EFloat ef2 = EFloat.Create(
            EInteger.FromInt32(1).ShiftLeft(b).Subtract(1),
            EInteger.FromInt32(-b));
        EFloat efdiv = ef.Divide(ef2, ec);
        for (int j = 1; j < 100; ++j) {
          EFloat ef3 = EFloat.Create(
              ef2.getMantissa().ShiftLeft(j),
              ef2.getExponent().Subtract(j));
          if (ef2.CompareToValue(ef3) != 0) {
            Assert.fail("a=" + a + ", b=" + b + ", j=" + j +
              "\nef2=" + OutputEF(ef2) + "\nef3=" + OutputEF(ef3));
          }
          EFloat efdiv2 = ef.Divide(ef3, ec);
          if (efdiv.CompareToValue(efdiv2) != 0) {
            Assert.fail("a=" + a + ", b=" + b + ", j=" + j +
              "\nefdiv=" + OutputEF(efdiv) + "\nefdiv2=" + OutputEF(efdiv2));
          }
        }
      }
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
      if (EFloat.One.equals(null)) {
 Assert.fail();
 }
      if (EFloat.Zero.equals(null)) {
 Assert.fail();
 }
      if (EFloat.One.equals(EFloat.Zero)) {
 Assert.fail();
 }
      if (EFloat.Zero.equals(EFloat.One)) {
 Assert.fail();
 }
      {
        Object objectTemp =

  EFloat.FromString("0.009461540475412139260145553670698466186015902447450593622262751970123371581303298477485466592231565609");
        Object objectTemp2 =

  EFloat.FromString("0.009461540475412139260145553670698466186015902447450593622262751970123371581303298477485466592231565609");
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      {
        Object objectTemp =

  EFloat.FromString("0.009461540475412139260145553670698466186015902447450593622262751970123371581303298477485466592231565609");
        Object objectTemp2 =

  EFloat.FromString("0.001809476049361792727571247490438259768858020288404502743164967883090669271207537395819291033916115474");
        if ((objectTemp).equals(objectTemp2)) {
 Assert.fail((objectTemp)+" may not be equal to "+(objectTemp2));
}
      }
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

    public static String RandomDecimalString(RandomGenerator rand, int
      digitsBefore) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < digitsBefore; ++i) {
        if (rand == null) {
          throw new NullPointerException("rand");
        }
        sb.append((char)(0x30 + rand.UniformInt(10)));
      }
      sb.append('.');
      for (int i = 0; i < digitsBefore; ++i) {
        if (rand == null) {
          throw new NullPointerException("rand");
        }
        sb.append((char)(0x30 + rand.UniformInt(10)));
        sb.append((char)(0x30 + rand.UniformInt(10)));
      }
      return sb.toString();
    }

    public static void TestDigitStringsOne(String str) {
      TestCommon.CompareTestEqual(
        EDecimal.FromString(str).ToEFloat(EContext.Binary64),
        EFloat.FromString(str, EContext.Binary64),
        str);
    }

    @Test
    public void TestRandomDigitStrings() {
      TestDigitStringsOne("9.5");
      TestDigitStringsOne("0.1");
      TestDigitStringsOne("664.07742299");
      TestDigitStringsOne("7062.66606310");
      TestDigitStringsOne("0664.07742299");
      RandomGenerator rand = new RandomGenerator();
      ArrayList<String> strings = new ArrayList<String>();
      for (int i = 0; i < 10000; ++i) {
        strings.add(RandomDecimalString(rand, 4));
      }
      ArrayList<EFloat> eflist1 = new ArrayList<EFloat>();
      ArrayList<EFloat> eflist2 = new ArrayList<EFloat>();
      EContext ec = EContext.Binary64;
      // System.Diagnostics.Stopwatch sw = new System.Diagnostics.Stopwatch();
      // sw.Restart();
      for (int i = 0; i < strings.size(); ++i) {
        eflist1.add(EDecimal.FromString(strings.get(i)).ToEFloat(ec));
      }
      // long em = sw.getElapsedMilliseconds();
      // sw.Restart();
      for (int i = 0; i < strings.size(); ++i) {
        eflist2.add(EFloat.FromString(strings.get(i), ec));
      }
      // long em2 = sw.getElapsedMilliseconds();
      // System.out.println("EFloat FS={0} ms\nDouble FS={1} ms", em, em2);
      for (int i = 0; i < strings.size(); ++i) {
        TestCommon.CompareTestEqual(eflist1.get(i), eflist2.get(i), strings.get(i));
      }
    }

    public static void TestEFloatDoubleCoreExact(double d, String s) {
      Assert.assertEquals(s, EFloat.FromDouble(d).toString());
      TestEFloatDoubleCore(d, s);
    }

    public static void TestEFloatSingleCoreExact(float d, String s) {
      Assert.assertEquals(s, EFloat.FromSingle(d).toString());
      TestEFloatSingleCore(d, s);
    }

    @Test
    public void TestEFloatDouble() {
      TestEFloatDoubleCoreExact(3.5, "3.5");
      TestEFloatDoubleCoreExact(7, "7");
      TestEFloatDoubleCoreExact(1.75, "1.75");
      TestEFloatDoubleCoreExact(3.5, "3.5");
      TestEFloatDoubleCoreExact((double)Integer.MIN_VALUE, "-2147483648");
      TestEFloatDoubleCoreExact(
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
    public void TestDoubleSingleBitsSpecific() {
      {
        String str =
          "-0.0230382307472970331019279655038189957849681377410888671875";
        EFloat ed = EFloat.FromDoubleBits(-4641074497532188517L);
        EFloat edExp = EFloat.FromString(str);
        Assert.assertEquals(0, edExp.CompareToValue(ed));
        Assert.assertEquals(-4641074497532188517L, ed.ToDoubleBits());
      }
      {
        String str = "-0.023038230747297033";
        EFloat ed = EFloat.FromDoubleBits(-4641074497532188517L);
        EFloat edExp = EFloat.FromString(str);
        String messageTemp = "-0.023038230747297033" +
          "\n" + ed.toString() + "\n" + edExp.toString();
        Assert.assertEquals(messageTemp, 0, edExp.CompareToValue(ed));
        Assert.assertEquals(-4641074497532188517L, ed.ToDoubleBits());
      }
      {
        String str = "-5761315294415299";
        EDecimal ed = EDecimal.FromDoubleBits(-4380744721764447805L);
        EDecimal edExp = EDecimal.FromString(str);
        String messageTemp = "-5761315294415299" +
          "\n" + ed.toString() + "\n" + edExp.toString();
        Assert.assertEquals(messageTemp, 0, edExp.CompareToValue(ed));
        Assert.assertEquals(-4380744721764447805L, ed.ToDoubleBits());
      }
      {
        String str = "4569138";
        EDecimal ed = EDecimal.FromSingleBits(1250652260);
        EDecimal edExp = EDecimal.FromString(str);
        String messageTemp = "4569138" +
          "\n" + ed.toString() + "\n" + edExp.toString();
        Assert.assertEquals(messageTemp, 0, edExp.CompareToValue(ed));
        Assert.assertEquals(1250652260, ed.ToSingleBits());
      }
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
        EFloat.FromString((String)null);
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
        EFloat.FromString((String)null, null);
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
        EFloat.FromString((String)null, 0, 1);
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
        EFloat.FromString((String)null, 0, 1, null);
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
     public void TestCloseToPowerOfTwo() {
        String[] variations = {
          "", ".0", ".00", ".000",
          ".4", ".40", ".6", ".60", ".5", ".50", ".500",
        };
        for (int i = 31; i < 129; ++i) {
           EInteger ei = EInteger.FromInt32(1).ShiftLeft(i);
           for (String vari : variations) {
              TestStringToDoubleSingleOne(ei.toString() + vari);
              TestStringToDoubleSingleOne(ei.Add(1).toString() + vari);
              TestStringToDoubleSingleOne(ei.Subtract(1).toString() + vari);
              TestStringToDoubleSingleOne(ei.Add(2).toString() + vari);
              TestStringToDoubleSingleOne(ei.Subtract(2).toString() + vari);
           }
        }
     }

    public static void TestParseNumberFxxLine(String line) {
      // Parse test case format used in:
      // https://github.com/nigeltao/parse-number-fxx-test-data
      String f16 = line.substring(0, 4);
      if (line.charAt(4) != ' ') {
        Assert.fail(line);
      }
      String f32 = line.substring(4 + 1, (4 + 1)+(8));
      if (line.charAt(4 + 9) != ' ') {
        Assert.fail(line);
      }
      String f64 = line.substring(4 + 1 + 8 + 1, (4 + 1 + 8 + 1)+(16));
      if (line.charAt(4 + 26) != ' ') {
        Assert.fail(line);
      }
      String str = line.substring(4 + 1 + 8 + 1 + 16 + 1);
      short sf16 = EInteger.FromRadixString(f16, 16).ToInt16Unchecked();
      int sf32 = EInteger.FromRadixString(f32, 16).ToInt32Unchecked();
      long sf64 = EInteger.FromRadixString(f64, 16).ToInt64Unchecked();
      TestParseNumberFxx(str, sf16, sf32, sf64, line);
    }

    public static void TestParseNumberFxx(
      String str,
      short _f16,
      int f32,
      long f64,
      String line) {
       // TODO: Support f16 test
       // TODO: Add From/ToHalfBits in EDecimal/EFloat/ERational
       EFloat efsng = EFloat.FromSingleBits(f32);
       EFloat efdbl = EFloat.FromDoubleBits(f64);
       // Begin test
       if (efsng.isFinite()) {
         TestStringToSingleOne(str);
       }
       if (efdbl.isFinite()) {
         TestStringToDoubleOne(str);
       }
       EFloat ef;
       ef = EFloat.FromString(str, EContext.Binary64);
       Assert.assertEquals(line, f64, ef.ToDoubleBits());
       ef = EFloat.FromString(str, EContext.Binary32);
       Assert.assertEquals(line, f32, ef.ToSingleBits());
       ef = EFloat.FromString(
          "xxx" + str + "xxx",
          3,
          str.length(),
          EContext.Binary64);
       Assert.assertEquals(line, f64, ef.ToDoubleBits());
       ef = EFloat.FromString(
          "xxx" + str + "xxx",
          3,
          str.length(),
          EContext.Binary32);
       Assert.assertEquals(line, f32, ef.ToSingleBits());
       EDecimal ed = EDecimal.FromString(str);
       Assert.assertEquals(str, ed.ToSingleBits(), f32);
       Assert.assertEquals(str, ed.ToDoubleBits(), f64);
    }

    @Test
    public void TestLog() {
      if (!(EFloat.One.Log(null).IsNaN())) {
 Assert.fail();
 }
      if (!(EFloat.One.Log(EContext.Unlimited).IsNaN())) {
 Assert.fail();
 }
      {
        EFloat efa = EFloat.Create(6202238844624971L,
            908).Log(EContext.Binary64);
        EFloat efb = EFloat.Create(731990329769283L, -40);
        String str = "" + EFloat.Create(6202238844624971L, 908);
        TestCommon.CompareTestEqual(efb, efa, str);
      }
      {
        EFloat efaa = EFloat.Create(
            EInteger.FromString("7692406748247399"),
            EInteger.FromString("-465"));
        EFloat efa = efaa.Log(EContext.Binary64).Reduce(null);
        EFloat efb = EFloat.Create(
            EInteger.FromString("-5026693231795637"),
            EInteger.FromString("-44"));
        String str = OutputEF(efb) + "\n" + OutputEF(efa);
        TestCommon.CompareTestEqual(efb, efa, str);
      }
      {
        EFloat efa = EFloat.Create(
            EInteger.FromString("5591241150794165"),
            EInteger.FromString("-944")).Log(EContext.Binary64).Reduce(null);
        EFloat efb = EFloat.Create(
            EInteger.FromString("-339788104073483"),
            EInteger.FromString("-39"));
        String str = OutputEF(efb) + "\n" + OutputEF(efa);
        TestCommon.CompareTestEqual(efb, efa, str);
      }
      {
        EFloat efa = EFloat.Create(
            EInteger.FromString("5309985732671123"),
            EInteger.FromString("276")).Log(EContext.Binary64).Reduce(null);
        EFloat efb = EFloat.Create(
            EInteger.FromString("1000630292553943"),
            EInteger.FromString("-42"));
        String str = OutputEF(efb) + "\n" + OutputEF(efa);
        TestCommon.CompareTestEqual(efb, efa, str);
      }
      {
        EFloat efa = EFloat.Create(
            EInteger.FromString("8242379924809039"),
            EInteger.FromString("-234")).Log(EContext.Binary64).Reduce(null);
        EFloat efb = EFloat.Create(
            EInteger.FromString("-276083795723785"),
            EInteger.FromString("-41"));
        String str = OutputEF(efb) + "\n" + OutputEF(efa);
        TestCommon.CompareTestEqual(efb, efa, str);
      }
      {
        EFloat ef = EFloat.FromString(
            "49565911.77858351171016693115234375")
          .Log(EContext.Binary64);
        EFloat ef2 = EFloat.FromString(
            "17.718813892893447103915605111978948116302490234375");
        TestCommon.CompareTestEqual(ef2, ef);
      }
    }

    @Test
    public void TestLogExpSpecificA() {
      EFloat efa = EFloat.Create(5094638944929121L,
  -43).ExpM1(EContext.Binary64);
      EInteger mant = efa.getMantissa();
      if (!(mant.Abs().GetUnsignedBitLengthAsInt64() <= 53)) {
 Assert.fail();
 }
      EFloat efb = EFloat.Create(6823497764200007L, 783);
      String str = "ExpM1\n" + OutputEF(efb) + "\n" + OutputEF(efa);
      TestCommon.CompareTestEqual(efb, efa, str);
    }
    @Test
    public void TestLogExpSpecificB() {
      EFloat efa = EFloat.Create(1168389840651401L, 526).Log(EContext.Binary64);
      EInteger mant = efa.getMantissa();
      if (!(mant.Abs().GetUnsignedBitLengthAsInt64() <= 53)) {
 Assert.fail();
 }
      EFloat efb = EFloat.Create(1756095199620111L, -42);
      String str = OutputEF(efb) + "\n" + OutputEF(efa);
      TestCommon.CompareTestEqual(efb, efa, str);
    }
    @Test
    public void TestLogExpSpecificC() {
      EFloat efa = EFloat.Create(-1184982539430741L,
  -52).Exp(EContext.Binary64);
      EInteger mant = efa.getMantissa();
      if (!(mant.Abs().GetUnsignedBitLengthAsInt64() <= 53)) {
 Assert.fail();
 }
      EFloat efb = EFloat.Create(6923387652188847L, -53);
      String str = "Exp\n" + OutputEF(efb) + "\n" + OutputEF(efa);
      TestCommon.CompareTestEqual(efb, efa, str);
    }
    @Test
    public void TestLogExpSpecificD() {
      EFloat efa = EFloat.Create(6832986215039611L,
  -38).Log1P(EContext.Binary64);
      EFloat efb = EFloat.Create(1424402087294909L, -47);
      String str = OutputEF(efb) + "\n" + OutputEF(efa);
      TestCommon.CompareTestEqual(efb, efa, str);
    }
    @Test
    public void TestLogExpSpecificE() {
      EFloat efa = EFloat.Create(5615046595603761L,
  -44).ExpM1(EContext.Binary64);
      EInteger mant = efa.getMantissa();
      if (!(mant.Abs().GetUnsignedBitLengthAsInt64() <= 53)) {
 Assert.fail();
 }
      EFloat efb = EFloat.Create(6269016557695007L, 408);
      String str = "ExpM1\n" + OutputEF(efb) + "\n" + OutputEF(efa);
      TestCommon.CompareTestEqual(efb, efa, str);
    }
    @Test
    public void TestLogExpSpecificF() {
      EFloat efaa = EFloat.Create(7894203448763243L, 790);
      EFloat efa = efaa.Log(EContext.Binary64);
      EInteger mant = efa.getMantissa();
      if (!(mant.Abs().GetUnsignedBitLengthAsInt64() <= 53)) {
 Assert.fail();
 }
      EFloat efb = EFloat.Create(642324992820697L, -40);
      String str = OutputEF(efb) + "\n" + OutputEF(efa) + "\n" + efaa;
      TestCommon.CompareTestEqual(efb, efa, str);
    }
    @Test
    public void TestLogExpSpecificG() {
      EFloat efa = EFloat.Create(4939846268124649L, -48).Log(EContext.Binary64);
      EFloat efb = EFloat.Create(6451509911495955L, -51);
      String str = OutputEF(efb) + "\n" + OutputEF(efa) + "\nInput: " +
        OutputEF(EFloat.Create(4939846268124649L, -48));
      TestCommon.CompareTestEqual(efb, efa, str);
    }

    @Test
    public void TestExpM1() {
      {
        EFloat efa = EFloat.Create(1007123499737607L,
            -522).ExpM1(EContext.Binary64);
        EFloat efb = EFloat.Create(1007123499737607L, -522);
        String str = OutputEF(efb) + "\n" + OutputEF(efa);
        TestCommon.CompareTestEqual(efb, efa, str);
      }
      {
        EFloat efa = EFloat.Create(6580149561684505L,
            -1071).ExpM1(EContext.Binary64);
        EFloat efb = EFloat.Create(6580149561684505L, -1071);
        String str = OutputEF(efb) + "\n" + OutputEF(efa);
        TestCommon.CompareTestEqual(efb, efa, str);
      }
      {
        EFloat efa = EFloat.Create(-3676681081736271L,
            -81).ExpM1(EContext.Binary64);
        EFloat efb = EFloat.Create(-7353362157881635L, -82);
        String str = OutputEF(efb) + "\n" + OutputEF(efa);
        TestCommon.CompareTestEqual(efb, efa, str);
      }
      {
        EFloat efa = EFloat.Create(-969434867059159L,
            -66).ExpM1(EContext.Binary64);
        EFloat efb = EFloat.Create(-7755427989821553L, -69);
        String str = OutputEF(efb) + "\n" + OutputEF(efa);
        TestCommon.CompareTestEqual(efb, efa, str);
      }
      {
        EFloat efa = EFloat.Create(3153411279369011L,
            -70).ExpM1(EContext.Binary64);
        EFloat efb = EFloat.Create(6306830981643433L, -71);
        String str = OutputEF(efb) + "\n" + OutputEF(efa);
        TestCommon.CompareTestEqual(efb, efa, str);
      }
      {
        EFloat efa = EFloat.Create(-1481872941857973L,
            -86).ExpM1(EContext.Binary64);
        EFloat efb = EFloat.Create(-740936470921891L, -85);
        String str = OutputEF(efb) + "\n" + OutputEF(efa);
        TestCommon.CompareTestEqual(efb, efa, str);
      }
    }

    public static String DebugStringLog1P(
      EFloat expec,
      EFloat actual,
      EFloat inp) {
      return OutputEF(expec) + "\n" + OutputEF(actual) + "\n" + OutputEF(inp);
    }

    @Test
    public void TestLog1P() {
      {
        EFloat efa = EFloat.Create(3326311965476095L,
            -26);
        EFloat efl = efa.Log1P(EContext.Binary64);
        EFloat efb = EFloat.Create(4987402727842631L, -48);
        TestCommon.CompareTestEqual(efb, efl, DebugStringLog1P(efb, efl, efa));
      }
      {
        EFloat efa = EFloat.Create(-5934733692758989L,
            -166);
        EFloat efl = efa.Log1P(EContext.Binary64);
        EFloat efb = EFloat.Create(-5934733692758989L, -166);
        TestCommon.CompareTestEqual(efb, efl, DebugStringLog1P(efb, efl, efa));
      }
      {
        EFloat efa = EFloat.Create(7028563965745449L,
            -26);
        EFloat efl = efa.Log1P(EContext.Binary64);
        EFloat efb = EFloat.Create(2598989644557185L, -47);
        TestCommon.CompareTestEqual(efb, efl, DebugStringLog1P(efb, efl, efa));
      }
      {
        EFloat efa = EFloat.Create(6661843800332999L,
            -311);
        EFloat efl = efa.Log1P(EContext.Binary64);
        EFloat efb = EFloat.Create(6661843800332999L, -311);
        TestCommon.CompareTestEqual(efb, efl, DebugStringLog1P(efb, efl, efa));
      }
      {
        EFloat efa = EFloat.Create(2966802219632029L,
            -588);
        EFloat efl = efa.Log1P(EContext.Binary64);
        EFloat efb = EFloat.Create(2966802219632029L, -588);
        TestCommon.CompareTestEqual(efb, efl, DebugStringLog1P(efb, efl, efa));
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
    public void TestIsInteger() {
      EFloat ed = EFloat.NaN;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = EFloat.SignalingNaN;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = EFloat.PositiveInfinity;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = EFloat.NegativeInfinity;
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = EFloat.NegativeZero;
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = EFloat.FromInt32(0);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = EFloat.FromInt32(999);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = EFloat.Create(999, 999);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = EFloat.Create(999, -999);
      if (ed.IsInteger()) {
 Assert.fail();
 }
      ed = EFloat.Create(0, -999);
      if (!(ed.IsInteger())) {
 Assert.fail();
 }
      ed = EFloat.Create(EInteger.FromInt32(999).ShiftLeft(999), -999);
      if (!(ed.IsInteger())) {
 Assert.fail();
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

    private static final String[] ValueFPIntegers = {
      "1", "2", "4", "8",
      "281474976710656", "562949953421312", "1125899906842624",
      "2251799813685248", "4503599627370496", "9007199254740992",
      "18014398509481984", "36028797018963968", "72057594037927936",
      "144115188075855872", "288230376151711744",
      "11235582092889474423308157442431404585112356118389416079589380072358292237843810195794279832650471001320007117491962084853674360550901038905802964414967132773610493339054092829768888725077880882465817684505312860552384417646403930092119569408801702322709406917786643639996702871154982269052209770601514008576",
      "22471164185778948846616314884862809170224712236778832159178760144716584475687620391588559665300942002640014234983924169707348721101802077811605928829934265547220986678108185659537777450155761764931635369010625721104768835292807860184239138817603404645418813835573287279993405742309964538104419541203028017152",
      "44942328371557897693232629769725618340449424473557664318357520289433168951375240783177119330601884005280028469967848339414697442203604155623211857659868531094441973356216371319075554900311523529863270738021251442209537670585615720368478277635206809290837627671146574559986811484619929076208839082406056034304",
      "89884656743115795386465259539451236680898848947115328636715040578866337902750481566354238661203768010560056939935696678829394884407208311246423715319737062188883946712432742638151109800623047059726541476042502884419075341171231440736956555270413618581675255342293149119973622969239858152417678164812112068608",
    };

    private static final int[] ValueFPIntegersExp = {
      0, 1, 2, 3, 48, 49,
      50, 51, 52,
      53, 54, 55, 56, 57, 58, 1020, 1021, 1022, 1023,
    };

    @Test
    public void TestIntegerDoubleSingle() {
      TestIntegerDoubleSingleOne(EInteger.FromString("16777216"));
      TestIntegerDoubleSingleOne(EInteger.FromString("9007199254740992"));

      TestIntegerDoubleSingleOne(
        EInteger.FromString("36410213260593956497280175692088585748480"));
      RandomGenerator rg = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        EInteger ei = RandomObjects.RandomEInteger(rg);
        TestIntegerDoubleSingleOne(ei);
      }
    }

    public static boolean TestIntegerDoubleSingleOne(EInteger ei) {
      EInteger ei2 = ei;
      if (ei == null) {
        throw new NullPointerException("ei");
      }
      while (ei2.isEven() && !ei2.isZero()) {
        ei2 = ei2.ShiftRight(1);
      }
      if (!(ei2.GetUnsignedBitLengthAsInt64() <= 64)) {
        { return false;
        }
      }
      long db = EDecimal.FromEInteger(ei).ToDoubleBits();
      EFloat ef = EFloat.FromDoubleBits(db);
      EFloat eif = EFloat.FromEInteger(ei).RoundToPrecision(EContext.Binary32);
      if (!eif.isFinite() || !ei.equals(eif.ToEInteger())) {
        return false;
      }
      Assert.assertEquals("dbl origdb=" + db + " newdb=" + ef.ToDoubleBits(),ei.toString(),ef.toString());
      int sb = EDecimal.FromEInteger(ei).ToSingleBits();
      ef = EFloat.FromSingleBits(sb);
      eif = EFloat.FromEInteger(ei).RoundToPrecision(EContext.Binary32);
      if (eif.isFinite() && ei.equals(eif.ToEInteger())) {
        Assert.assertEquals("sng origdb=" + sb + " newdb=" + ef.ToSingleBits(),ei.toString(),ef.toString());
      }
      return true;
    }

    @Test
    public void TestFPDoubleBits() {
      for (int i = 0; i < ValueFPIntegersExp.length; ++i) {
        // Positive
        EFloat ef = EFloat.Create(1, ValueFPIntegersExp[i]);
        Assert.assertEquals(ValueFPIntegers[i], ef.toString());
        ef = EFloat.FromDoubleBits(ef.ToDoubleBits());
        Assert.assertEquals(ValueFPIntegers[i], ef.toString());
        ef = EFloat.FromDoubleBits(
            EDecimal.FromString(ValueFPIntegers[i]).ToDoubleBits());
        Assert.assertEquals(ValueFPIntegers[i], ef.toString());
        // Negative
        ef = EFloat.Create(-1, ValueFPIntegersExp[i]);
        Assert.assertEquals("-" + ValueFPIntegers[i], ef.toString());
        ef = EFloat.FromDoubleBits(ef.ToDoubleBits());
        Assert.assertEquals("-" + ValueFPIntegers[i], ef.toString());
        ef = EFloat.FromDoubleBits(
            EDecimal.FromString("-" + ValueFPIntegers[i]).ToDoubleBits());
        Assert.assertEquals("-" + ValueFPIntegers[i], ef.toString());
      }
      for (int i = -1074; i < 1024; ++i) {
        String intstr = TestCommon.IntToString(i);
        // Positive
        EFloat ef = EFloat.Create(1, i);
        String fpstr = ef.toString();
        ef = EFloat.FromDoubleBits(ef.ToDoubleBits());
        Assert.assertEquals(intstr, fpstr, ef.toString());
        ef = EFloat.FromDoubleBits(
            EDecimal.FromString(fpstr).ToDoubleBits());
        Assert.assertEquals(intstr, fpstr, ef.toString());
        // Negative
        ef = EFloat.Create(-1, i);
        Assert.assertEquals(intstr,"-" + fpstr,ef.toString());
        ef = EFloat.FromDoubleBits(ef.ToDoubleBits());
        Assert.assertEquals(intstr,"-" + fpstr,ef.toString());
        ef = EFloat.FromDoubleBits(
            EDecimal.FromString("-" + fpstr).ToDoubleBits());
        Assert.assertEquals(intstr,"-" + fpstr,ef.toString());
      }
      EFloat ef2 = EFloat.Create(1, 1024);
      if (!(ef2.ToDoubleBits() == (0x7ffL << 52))) {
 Assert.fail();
 }
      ef2 = EFloat.Create(-1, 1024);
      if (!(ef2.ToDoubleBits() == (0xfffL << 52))) {
 Assert.fail();
 }
      ef2 = EFloat.Create(1, -1075);
      if (!(EFloat.FromDoubleBits(ef2.ToDoubleBits()).isZero()))Assert.fail();
      ef2 = EFloat.Create(-1, -1075);
      if (!(EFloat.FromDoubleBits(ef2.ToDoubleBits()).isZero()))Assert.fail();
    }

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
      EContext[] ecs = new EContext[] {
        EContext.Binary32,
        EContext.Binary64,
      };
      EInteger[] powers = new EInteger[] {
        EInteger.FromInt32(1).ShiftLeft(63).Subtract(2),
        EInteger.FromInt32(1).ShiftLeft(63).Subtract(1),
        EInteger.FromInt32(1).ShiftLeft(63),
        EInteger.FromInt32(1).ShiftLeft(64).Subtract(2),
        EInteger.FromInt32(1).ShiftLeft(64).Subtract(1),
        EInteger.FromInt32(1).ShiftLeft(64),
      };
      EFloat efi = EFloat.PositiveInfinity;
      ArrayList<EInteger> powerlist = new ArrayList<EInteger>();
      for (EInteger ei : powers) {
        powerlist.add(ei);
      }
      RandomGenerator rg = new RandomGenerator();
      for (int i = 0; i < 500; ++i) {
        EInteger ei = RandomObjects.RandomEInteger(rg);
        EInteger thresh = EInteger.FromInt64(6999999999999999999L);
        if (ei.Abs().compareTo(thresh) < 0) {
          ei = ei.Add(ei.signum() < 0 ? thresh.Negate() : thresh);
        }
        powerlist.add(ei);
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

    public static EFloat RandomDoubleEFloat(RandomGenerator rnd) {
      return RandomDoubleEFloat(rnd, false);
    }

    public static EFloat RandomDoubleEFloat(RandomGenerator rnd, boolean
      subnormal) {
      StringBuilder sb = new StringBuilder();
      if (rnd == null) {
        throw new NullPointerException("rnd");
      }
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

    public static EFloat RandomSingleEFloat(RandomGenerator rnd) {
      return RandomSingleEFloat(rnd, false);
    }

    public static EFloat RandomSingleEFloat(RandomGenerator rnd, boolean
      subnormal) {
      StringBuilder sb = new StringBuilder();
      if (rnd == null) {
        throw new NullPointerException("rnd");
      }
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
      if (ef == null) {
        throw new NullPointerException("ef");
      }
      double dbl = ef.ToDouble();
      EFloat efd = EFloat.FromDouble(dbl);
      return dbl +
        " [dbl=" + efd.getMantissa().Abs() + "," + efd.getExponent() + "]" +
        " [" + ef.getMantissa().Abs().ToRadixString(2) +
        "," + ef.getExponent() + "]";
    }

    public static void TestDoubleRounding(
      EFloat expected,
      EFloat input,
      EFloat src) {
      if (input == null) {
        throw new NullPointerException("input");
      }
      if (expected == null) {
        throw new NullPointerException("expected");
      }
      if (!input.isFinite() || !expected.isFinite()) {
        return;
      }
      double expectedDouble = expected.ToDouble();
      if (((Double)(expectedDouble)).isInfinite()) {
        return;
      }
      if (input.ToDouble() != expectedDouble) {
        String msg = "\ninputDouble\nexpectedDbl " +
          OutputDouble(expectedDouble) +
          ",\ngot----- " + OutputDouble(input.ToDouble()) +
          "\nsrc-----=" + OutputEF(src) + "\nexpected=" +
          OutputEF(expected) + "\ninput---=" + OutputEF(input);
        throw new IllegalStateException(msg);
      }
      String str = input.toString();
      double inputDouble = EFloat.FromString(str,
          EContext.Binary64).ToDouble();
      if (inputDouble != expectedDouble) {
        String msg = "\ninputString\nexpectedDbl " +
          OutputDouble(expectedDouble) +
          ",\ngot----- " + OutputDouble(inputDouble) +
          "\nsrc-----=" + OutputEF(src) + "\nstr------=" + str +
          "\nexpected=" + OutputEF(expected) + "\ninput---=" + OutputEF(
            input);
        throw new IllegalStateException(msg);
      }
    }

    public static void TestSingleRounding(
      EFloat expected,
      EFloat input,
      EFloat src) {
      if (expected == null) {
        throw new NullPointerException("expected");
      }
      if (input == null) {
        throw new NullPointerException("input");
      }
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
        throw new IllegalStateException(msg);
      }
      float inputSingle = EFloat.FromString(str, EContext.Binary32).ToSingle();
      if (inputSingle != expectedSingle) {
        String msg = "\nexpectedDbl " + OutputSingle(expectedSingle) +
          ",\ngot----- " +
          OutputSingle(inputSingle) + "\nsrc-----=" + OutputEF(src) +
          "\nexpected=" + OutputEF(expected) + "\ninput---=" +
          OutputEF(input);
        throw new IllegalStateException(msg);
      }
    }

    private static void TestStringEFloatPrecisionOne(String str) {
      EFloat ef1 = EDecimal.FromString(str).ToEFloat(EContext.Binary32);
      EFloat ef2 = EFloat.FromString(str, EContext.Binary32);
      System.out.println(OutputEF(ef1));
      System.out.println(OutputEF(ef2));
      TestCommon.CompareTestLess(ef1.getMantissa().ToInt32Checked(), 1 << 24);
      TestCommon.CompareTestLess(ef2.getMantissa().ToInt32Checked(), 1 << 24);
    }

    @Test
    public void TestStringEFloatPrecision() {
      TestStringEFloatPrecisionOne("43260094.4962653487189790");
    }

    private static ERational PowerOfTwo(int p) {
      EInteger ei = EInteger.FromInt32(1).ShiftLeft(Math.abs(p));
      if (p < 0) {
        return ERational.Create(EInteger.FromInt32(1), ei);
      } else {
        return ERational.Create(ei, EInteger.FromInt32(1));
      }
    }

    private static boolean IsPowerOfTwo(long v) {
      while ((v & 1) == 0 && v > 0) {
        v >>= 1;
      }
      return v == 1;
    }

    private static boolean IsPowerOfTwo(int v) {
      while ((v & 1) == 0 && v > 0) {
        v >>= 1;
      }
      return v == 1;
    }

    private static void TestStringToSingleOne(String str) {
      EDecimal ed = EDecimal.FromString(str);
      if (ed.IsInfinity() || ed.IsNaN()) {
        // Expected String to represent a finite number
        Assert.fail(str);
      }
      EFloat ef = EFloat.FromString(str, EContext.Binary32);
      if (ef.signum() == 0) {
        if (!(ed.isNegative() == ef.isNegative())) {
 Assert.fail();
 }
        ERational half = PowerOfTwo(-149).Divide(2);
        if (half.CompareToDecimal(ed.Abs()) < 0) {
          String msg = "str=" + str + "\nef=" + OutputEF(ef);
          Assert.fail(msg);
        }
      } else if (ef.IsInfinity()) {
        EDecimal half = EDecimal.FromEInteger(
            EInteger.FromInt32((1 << 25) - 1).ShiftLeft(103));
        if (ed.Abs().compareTo(half) < 0) {
          String msg = "str=" + str + "\nef=" + OutputEF(ef);
          Assert.fail(msg);
        }
      } else if (ef.IsNaN()) {
        String msg = "str=" + str + "\nef=" + OutputEF(ef);
        Assert.fail(msg);
      } else {
        if (ed.isNegative() != ef.isNegative()) {
          if (!(
            ed.isNegative() == ef.isNegative())) {
 Assert.fail(
            ed + "\nef=" + ef + "\nstr=" + str);
 }
        }
        EInteger eimant = ef.Abs().getMantissa();
        long lmant = eimant.ToInt64Checked();
        int exp = ef.getExponent().ToInt32Checked();
        while (lmant < (1 << 23) && exp > -149) {
          --exp;
          lmant <<= 1;
        }
        while (lmant >= (1 >> 24) && (lmant & 1) == 0) {
          ++exp;
          lmant >>= 1;
        }
        if (!(lmant < (1 << 24))) {
 Assert.fail();
 }
        ERational ulp = PowerOfTwo(exp);
        ERational half = PowerOfTwo(exp - 1);
        ERational binValue = ERational.FromInt64(lmant).Multiply(ulp);
        ERational decValue = ERational.FromEDecimal(ed).Abs();
        ERational diffValue = decValue.Subtract(binValue);
        if (IsPowerOfTwo(lmant) && exp != -149) {
          // Different closeness check applies when approximate
          // binary number is a power of 2
          ERational negQuarter = PowerOfTwo(exp - 2).Negate();
          // NOTE: Order of subtraction in diffValue is important here
          if (negQuarter.compareTo(diffValue) > 0 ||
            diffValue.compareTo(half) > 0) {
            String msg = "str=" + str + "\nef=" + OutputEF(ef) +
              "\nmant=" + lmant + "\nexp=" + exp +
              "\nnegQuarter=" + negQuarter + "\n" +
              "\ndiffValue=" + diffValue + "\n" + "\nhalf=" + half + "\n";
            Assert.fail(msg);
          }
        } else {
          int cmp = diffValue.Abs().compareTo(half);
          if (cmp > 0 || (cmp == 0 && (lmant & 1) != 0)) {
            String msg = "str=" + str + "\nef=" + OutputEF(ef) +
              "\nmant=" + lmant + "\nexp=" + exp;
            Assert.fail(msg);
          }
        }
      }
    }

    private static void TestStringToDoubleOne(String str) {
      EDecimal ed = EDecimal.FromString(str);
      if (ed.IsInfinity() || ed.IsNaN()) {
        // Expected String to represent a finite number
        Assert.fail(str);
      }
      EFloat ef = EFloat.FromString(str, EContext.Binary64);
      if (ef.signum() == 0) {
        if (!(ed.isNegative() == ef.isNegative())) {
 Assert.fail();
 }
        ERational half = ERational.Create(
            EInteger.FromInt32(1),
            EInteger.FromInt32(2).Pow(1074)).Divide(2);
        if (half.CompareToDecimal(ed.Abs()) < 0) {
          String msg = "str=" + str + "\nef=" + OutputEF(ef);
          Assert.fail(msg);
        }
      } else if (ef.IsInfinity()) {
        EDecimal half = EDecimal.FromEInteger(
            EInteger.FromInt64((1L << 54) - 1).ShiftLeft(970));
        if (ed.Abs().compareTo(half) < 0) {
          String msg = "str=" + str + "\nef=" + OutputEF(ef);
          Assert.fail(msg);
        }
      } else if (ef.IsNaN()) {
        String msg = "str=" + str + "\nef=" + OutputEF(ef);
        Assert.fail(msg);
      } else {
        if (ed.isNegative() != ef.isNegative()) {
          String msg = "not negative str=" + str +
            "\nef=" + OutputEF(ef);
          Assert.fail(msg);
        }
        EInteger eimant = ef.Abs().getMantissa();
        long lmant = eimant.ToInt64Checked();
        int exp = ef.getExponent().ToInt32Checked();
        while (lmant < (1L << 52) && exp > -1074) {
          --exp;
          lmant <<= 1;
        }
        while (lmant >= (1L >> 53) && (lmant & 1) == 0) {
          ++exp;
          lmant >>= 1;
        }
        ERational ulp = PowerOfTwo(exp);
        ERational half = PowerOfTwo(exp - 1);
        ERational binValue = ERational.FromInt64(lmant).Multiply(ulp);
        ERational decValue = ERational.FromEDecimal(ed).Abs();
        ERational diffValue = decValue.Subtract(binValue);
        if (IsPowerOfTwo(lmant) && exp != -1074) {
          // Different closeness check applies when approximate
          // binary number is a power of 2
          ERational negQuarter = PowerOfTwo(exp - 2).Negate();
          // NOTE: Order of subtraction in diffValue is important here
          if (negQuarter.compareTo(diffValue) > 0 ||
            diffValue.compareTo(half) > 0) {
            String msg = "str=" + str + "\nef=" + OutputEF(ef) +
              "\nmant=" + lmant + "\nexp=" + exp;
            Assert.fail(msg);
          }
        } else {
          int cmp = diffValue.Abs().compareTo(half);
          if (cmp > 0 || (cmp == 0 && (lmant & 1) != 0)) {
            String msg = "str=" + str + "\nef=" + OutputEF(ef) +
              "\nmant=" + lmant + "\nexp=" + exp;
            Assert.fail(msg);
          }
        }
      }
    }

    public static void TestStringToDoubleSingleOne(String str) {
      TestStringToDoubleOne(str);
      TestStringToSingleOne(str);
    }

    @Test
    public void TestStringToDoubleSubnormal() {
      String str = "-2.3369664830896376E-303";
      TestStringToDoubleSingleOne(str);
      double efd = EFloat.FromString(str).ToDouble();
      if (!(Math.abs(efd) > 0.0)) {
 Assert.fail();
 }
    }

    @Test(timeout = 100000)
    public void TestStringToDoubleManyDigits() {
      RandomGenerator rand = new RandomGenerator();
      for (int i = 0; i < 1000; ++i) {
        StringBuilder sb = new StringBuilder();
        int pointIndex = rand.UniformInt(1000);
        for (int j = 0; j < 1000; ++j) {
          if (j == pointIndex) {
            sb.append('.');
          }
          sb.append((char)(0x30 + rand.UniformInt(10)));
        }
        String str = sb.toString();
        TestStringToDoubleSingleOne(str);
        TestStringToDoubleSingleOne(str + "e" +
          TestCommon.IntToString(rand.UniformInt(100) - 50));
      }
    }

    @Test
    public void TestCloseToOverflowSpecific() {
      EContext ec =
        EContext.Unlimited.WithPrecision(53).WithExponentRange(-1022,
          1023).WithRounding(
          ERounding.Down).WithAdjustExponent(
          false).WithExponentClamp(true).WithSimplified(false);
      EInteger emant =

  EInteger.FromString("88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
      EInteger eexp =

  EInteger.FromString("1000000000000000000000000000000000000000000000000000000000000");
      EFloat efmant = EFloat.FromEInteger(emant);
      EFloat efexp = EFloat.FromEInteger(eexp);
      EFloat ef2 = efmant.Multiply(efexp, ec);
      // should be finite because rounding mode is
      // ERounding.Down
      if (!(ef2.isFinite())) {
 Assert.fail();
 }
      ef2 = efmant.Multiply(efexp, null);
      if (!(ef2.isFinite())) {
 Assert.fail();
 }
    }

    @Test
    public void TestStringToDoubleSpecificA() {
      String str =

  "395327047447757233151852025916007341543830859020311182348280049405196796002596109672166636419495856284607016106216608940280159980410562166599659829549836399698289289291865158130408917411887384321629920907652092446340673107744633313627817849916899822288644199811238047243389339131191051062809216261025215824523.4450649076678708780046658731481724174843552673744114894507741447375332545091864773666544122664744761333144781246291659228465651037706198817528715653479238826021855332253112859123685832653222952164708641577926580176434675271038652656763152189489079211898438385589908245057380361924564889535903026779733005698423207728797753101352096950270825633677221801202735885609696599439158086869381984718373482202897732285374878471795568389970731523802567947950548336665365358918558902407299370109971613731348136804887326596306602541763433746075226973971630905830686044475031568633180101625817896363428603835057150659940109566037118543874354367476000190935017225290762348459773388606367426256772899921636";
      String strb =

  "179769313486231580793728971405303415079934132710037826936173778980444968292764750946649017977587207096330286416692887910946555547851940402630657488671505820681908902000708383676273854845817711531764475730270069855571366959622842914819860834936475292719074168444365510704342711559699508093042880177904174497792";
      EDecimal eda = EDecimal.FromString(str);
      EDecimal edb = EDecimal.FromString(strb);
      TestCommon.CompareTestLess(edb, eda);
      TestStringToDoubleSingleOne(str);
    }

    @Test
    public void TestStringToDoubleExp() {
      ArrayList<String> s1list = new ArrayList<String>();
      ArrayList<String> s2list = new ArrayList<String>();
      for (int i = -304; i <= 304; ++i) {
        s1list.add(TestCommon.IntToString(i));
      }
      for (int i = 0; i <= 1000; ++i) {
        s2list.add(TestCommon.IntToString(i));
      }
      for (int i = 0; i < s1list.size(); ++i) {
        for (int j = 0; j < s2list.size(); ++j) {
          TestStringToDoubleSingleOne(s2list.get(j) + "e" + s1list.get(i));
        }
      }
    }

    @Test
    public void TestIntStringToDouble() {
      for (int i = 0; i < 1000000; ++i) {
        String intstr = TestCommon.IntToString(i);
        TestStringToDoubleSingleOne(intstr);
        TestStringToDoubleSingleOne(intstr + ".0");
        TestStringToDoubleSingleOne(intstr + ".000");
      }
    }

    @Test
    public void TestStringToDouble() {
      RandomGenerator rg = new RandomGenerator();
      TestStringToDoubleSingleOne("9.5");
      TestStringToDoubleSingleOne("0.1");
      TestStringToDoubleSingleOne("43260094.4962653487189790");
      TestStringToDoubleSingleOne("215e7");
      for (int i = 0; i < 100; ++i) {
        for (int j = 1; j <= 10; ++j) {
          TestStringToDoubleSingleOne(RandomDecimalString(rg, j));
        }
      }
    }

    private static EFloat quarter = EFloat.FromString("0.25");
    private static EFloat half = EFloat.FromString("0.5");
    private static EFloat threequarter = EFloat.FromString("0.75");

    public static void TestToFloatRoundingOne(EFloat efa, boolean dbl) {
      int bitCount = dbl ? 53 : 24;
      if (efa == null) {
        throw new NullPointerException("efa");
      }
      EInteger emant = efa.getMantissa();
      int mantBits = emant.GetUnsignedBitLengthAsEInteger().ToInt32Checked();
      if (mantBits > bitCount) {
        throw new IllegalStateException("Too many bits; expected double-" +
          "\u0020or single-sized significand");
      }
      boolean fullPrecision = mantBits == bitCount;
      boolean isSubnormal = EFloats.IsSubnormal(efa,
          dbl ? EContext.Binary64 : EContext.Binary32);
      boolean isEven = efa.getUnsignedMantissa().isEven();
      if (isSubnormal) {
        int minExponent = dbl ? -1074 : -149;
        EInteger eexp = efa.getExponent();
        if (eexp.compareTo(minExponent) > 0) {
          isEven = true;
        }
      } else if (!fullPrecision) {
        isEven = true;
      }
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
      try {
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
      } catch (Exception ex) {
        String msg = "" + ("dbl_____=" + dbl + ", full=" +
            fullPrecision + ",sub=" + isSubnormal) + "\n" + ("efprev__=" +
            OutputEF(efprev)) + "\n" + ("efprev1q=" + OutputEF(efprev1q)) +
          "\n" + ("efprev2q=" + OutputEF(efprev2q)) + "\n" +
          ("efprev3q=" + OutputEF(efprev3q)) + "\n" +
          ("efa_____=" + OutputEF(efa)) + "\n" +
          ("efnext1q=" + OutputEF(efnext1q)) + "\n" +
          ("efnext2q=" + OutputEF(efnext2q)) + "\n" +
          ("efnext3q=" + OutputEF(efnext3q)) + "\n" +
          ("efnext__=" + OutputEF(efnext));

        throw new IllegalStateException(ex.getMessage() + "\n" + msg, ex);
      }
    }

    private static String EFToString(EFloat ef) {
      return "[" + ef.getMantissa().ToRadixString(2) + "," +
        ef.getMantissa().GetUnsignedBitLengthAsEInteger() + "," + ef.getExponent() + "]";
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

    @Test(timeout = 200000)
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
        String stringTemp = EFloat.NegativeZero.ToShortestString(
            EContext.Binary32);
        Assert.assertEquals(
          "-0",
          stringTemp);
      }
      {
        String stringTemp =
          EFloat.FromDouble(0.1).ToShortestString(EContext.Binary64);
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
        stringTemp = EFloat.FromString(
            "9.5").ToShortestString(EContext.Binary64);
        Assert.assertEquals(
          "9.5",
          stringTemp);
        stringTemp = EFloat.FromString(
            "0.1").ToShortestString(EContext.Binary64);
        Assert.assertEquals(
          "0.1",
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
      // Power of 2
      EFloat eef = EFloat.Create(EInteger.FromInt64(4503599627370496L),
          EInteger.FromInt32(-49));
      {
        String stringTemp = eef.ToShortestString(EContext.Binary64);
        Assert.assertEquals(
          "8",
          stringTemp);
      }
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 10000; ++i) {
        EFloat efa = RandomDoubleEFloat(fr);
        TestShortestStringOne(efa);
      }
    }

    public static boolean TestShortestStringOne(float sng) {
      EFloat ef = EFloat.FromSingle(sng);
      if (!ef.isFinite()) {
        { return false;
        }
      }
      if (!(ef.getMantissa().GetUnsignedBitLengthAsEInteger().compareTo(24)
        <= 0)) {
 Assert.fail();
 }
      Assert.assertEquals(ef, EFloat.FromSingle(ef.ToSingle()));
      return EFloatTest.TestShortestStringOne(ef, EContext.Binary32);
    }
    public static boolean TestShortestStringOne(double dbl) {
      EFloat ef = EFloat.FromDouble(dbl);
      if (!ef.isFinite()) {
        { return false;
        }
      }
      if (!(ef.getMantissa().GetUnsignedBitLengthAsEInteger().compareTo(53)
        <= 0)) {
 Assert.fail();
 }
      Assert.assertEquals(ef, EFloat.FromDouble(ef.ToDouble()));
      return EFloatTest.TestShortestStringOne(ef, EContext.Binary64);
    }

    public static boolean TestSingleRoundingOne(float sng) {
      EFloat ef = EFloat.FromSingle(sng);
      if (!ef.isFinite()) {
        { return false;
        }
      }
      if (!(ef.getMantissa().GetUnsignedBitLengthAsEInteger().compareTo(24)
        <= 0)) {
 Assert.fail();
 }
      Assert.assertEquals(ef, EFloat.FromSingle(ef.ToSingle()));
      EFloatTest.TestToFloatRoundingOne(ef, false);
      return true;
    }
    public static boolean TestDoubleRoundingOne(double dbl) {
      EFloat ef = EFloat.FromDouble(dbl);
      if (!ef.isFinite()) {
        { return false;
        }
      }
      if (!(ef.getMantissa().GetUnsignedBitLengthAsEInteger().compareTo(53)
        <= 0)) {
 Assert.fail();
 }
      Assert.assertEquals(ef, EFloat.FromDouble(ef.ToDouble()));
      EFloatTest.TestToFloatRoundingOne(ef, true);
      return true;
    }
    public static boolean TestShortestStringOne(EFloat efa) {
      return TestShortestStringOne(efa, EContext.Binary64);
    }
    public static boolean TestShortestStringOne(EFloat efa, EContext ctx) {
      if (efa == null) {
        throw new NullPointerException("efa");
      }
      String shortestStr = efa.ToShortestString(ctx);
      EFloat shortest = EFloat.FromString(
          shortestStr,
          ctx);
      if (!efa.equals(shortest)) {
        String msg = "\n" + EFToString(efa) + "\n" + EFToString(shortest) +
          "\n" + shortestStr;
        TestCommon.CompareTestEqual(
          efa,
          shortest,
          msg);
      }
      return true;
    }
    @Test
    public void TestToSingleRounding() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 1500; ++i) {
        TestSingleRoundingOne(RandomObjects.RandomSingle(fr));
      }
    }

    @Test
    public void TestToDoubleRounding2() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 1500; ++i) {
        TestDoubleRoundingOne(RandomObjects.RandomDouble(fr));
      }
    }

    @Test
    public void TestSingleShortestString() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 1500; ++i) {
        TestShortestStringOne(RandomObjects.RandomSingle(fr));
      }
    }

    @Test
    public void TestDoubleShortestString() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 1500; ++i) {
        TestShortestStringOne(RandomObjects.RandomDouble(fr));
      }
    }

    @Test
    public void TestConversions() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 20000; ++i) {
        EFloat enumber = RandomObjects.RandomEFloat(fr);
        TestConversionsOne(enumber);
      }
    }
    public static void TestConversionsOne(EFloat enumber) {
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
          EFloat.FromString("-2147483648")) >= 0 && enumber.compareTo(
          EFloat.FromString("2147483647")) <= 0;
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
          EFloat.FromString("-9223372036854775808")) >= 0 &&
        enumber.compareTo(
          EFloat.FromString("9223372036854775807")) <= 0;
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

    @Test(timeout = 100000)
    public void TestToDoubleRounding() {
      RandomGenerator fr = new RandomGenerator();
      for (int i = 0; i < 1500; ++i) {
        EFloat efa = RandomDoubleEFloat(fr, i >= 250);
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
        objectTemp = EFloat.Create(
            EInteger.FromRadixString("1010011", 2),
            EInteger.FromInt32(-1034));
        TestToFloatRoundingOne(objectTemp, true);
        objectTemp = EFloat.Create(
  EInteger.FromRadixString("100110100000000011000010111000111111101", 2),
  EInteger.FromInt32(-1073));
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
      for (int i = 0; i < 1000; ++i) {
        RandomGenerator rg = new RandomGenerator();
        EInteger ei = RandomObjects.RandomEInteger(rg);
        int expo = rg.UniformInt(20);
        EFloat ed = EFloat.FromEInteger(ei).ScaleByPowerOfTwo(
            expo).MovePointLeft(expo);
        Assert.assertEquals(ei, ed.ToEIntegerIfExact());
      }
    }

    @Test
    public void TestToSizedEInteger() {
      try {
        EFloat.PositiveInfinity.ToSizedEInteger(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.NegativeInfinity.ToSizedEInteger(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.PositiveInfinity.ToSizedEInteger(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.NegativeInfinity.ToSizedEInteger(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.NaN.ToSizedEInteger(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.PositiveInfinity.ToSizedEIntegerIfExact(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.NegativeInfinity.ToSizedEIntegerIfExact(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.PositiveInfinity.ToSizedEIntegerIfExact(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.NegativeInfinity.ToSizedEIntegerIfExact(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EFloat.NaN.ToSizedEIntegerIfExact(32);
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      RandomGenerator rg = new RandomGenerator();
      for (int i = 0; i < 100000; ++i) {
        boolean b = rg.UniformInt(2) == 0;
        TestSizedEIntegerOne(
          RandomObjects.RandomEFloat(rg),
          b,
          rg.UniformInt(129));
      }
    }

    public static boolean TestSizedEIntegerOne(
      EFloat ed,
      boolean isExact,
      int maxSignedBits) {
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
    public void TestInfinities() {
      Assert.assertEquals(
        EDecimal.PositiveInfinity,
        EDecimal.FromSingle(Float.POSITIVE_INFINITY));
      Assert.assertEquals(
        EDecimal.NegativeInfinity,
        EDecimal.FromSingle(Float.NEGATIVE_INFINITY));

      Assert.assertEquals(
        EFloat.PositiveInfinity,
        EFloat.FromDouble(Double.POSITIVE_INFINITY));
      Assert.assertEquals(
        EFloat.NegativeInfinity,
        EFloat.FromDouble(Double.NEGATIVE_INFINITY));
      Assert.assertEquals(
        EFloat.PositiveInfinity,
        EFloat.FromSingle(Float.POSITIVE_INFINITY));
      Assert.assertEquals(
        EFloat.NegativeInfinity,
        EFloat.FromSingle(Float.NEGATIVE_INFINITY));

      Assert.assertEquals(
        ERational.PositiveInfinity,
        ERational.FromDouble(Double.POSITIVE_INFINITY));
      Assert.assertEquals(
        ERational.NegativeInfinity,
        ERational.FromDouble(Double.NEGATIVE_INFINITY));
      Assert.assertEquals(
        ERational.PositiveInfinity,
        ERational.FromSingle(Float.POSITIVE_INFINITY));
      Assert.assertEquals(
        ERational.NegativeInfinity,
        ERational.FromSingle(Float.NEGATIVE_INFINITY));

      Assert.assertEquals(
        ERational.PositiveInfinity,
        ERational.FromEDecimal(EDecimal.PositiveInfinity));
      Assert.assertEquals(
        ERational.NegativeInfinity,
        ERational.FromEDecimal(EDecimal.NegativeInfinity));
      Assert.assertEquals(
        ERational.PositiveInfinity,
        ERational.FromEFloat(EFloat.PositiveInfinity));
      Assert.assertEquals(
        ERational.NegativeInfinity,
        ERational.FromEFloat(EFloat.NegativeInfinity));
      if (!(
        ((
          ERational.PositiveInfinity.ToDouble()) == Double.POSITIVE_INFINITY)))Assert.fail();
      if (!(
        ((
          ERational.NegativeInfinity.ToDouble()) == Double.NEGATIVE_INFINITY)))Assert.fail();
      if (!(
        ((
          ERational.PositiveInfinity.ToSingle()) == Float.POSITIVE_INFINITY)))Assert.fail();
      if (!(
        ((
          ERational.NegativeInfinity.ToSingle()) == Float.NEGATIVE_INFINITY)))Assert.fail();
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
        if (dec.isFinite()) {
          EDecimal ed = EDecimal.FromString(dec.toString());
          Assert.assertEquals(0, ed.CompareToBinary(dec));
        }
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
      d = bf.ToDouble();
      if (Double.isNaN(oldd)) {
        if (!(Double.isNaN(d))) {
 Assert.fail();
 }
      } else {
        Assert.assertEquals((double)oldd, d, 0);
      }
      if (s != null) {
        EFloat bf2 = EFloat.FromString(s, EContext.Binary64);
        d = bf.ToDouble();
        if (Double.isNaN(oldd)) {
          if (!(Double.isNaN(d))) {
 Assert.fail();
 }
        } else {
          Assert.assertEquals((double)oldd, d, 0);
        }
      }
      if (bf.isFinite()) {
        String s2 = bf.toString();
        TestStringToDoubleOne(s2);
        if (s != null && !s.equals(s2)) {
          TestStringToDoubleOne(s);
        }
      }
    }

    private static void TestEFloatSingleCore(float d, String s) {
      float oldd = d;
      EFloat bf = EFloat.FromSingle(d);
      d = bf.ToSingle();
      if (Float.isNaN(oldd)) {
        if (!(Float.isNaN(d))) {
 Assert.fail();
 }
      } else {
        Assert.assertEquals((float)oldd, d, 0f);
      }
      if (s != null) {
        EFloat bf2 = EFloat.FromString(s, EContext.Binary32);
        d = bf.ToSingle();
        if (Float.isNaN(oldd)) {
          if (!(Float.isNaN(d))) {
 Assert.fail();
 }
        } else {
          Assert.assertEquals((double)oldd, d, 0);
        }
      }
      if (bf.isFinite()) {
        String s2 = bf.toString();
        TestStringToSingleOne(s2);
        if (s != null && !s.equals(s2)) {
          TestStringToSingleOne(s);
        }
      }
    }
  }
