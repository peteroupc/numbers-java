package com.upokecenter.test;

import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.numbers.*;

  public class EContextTest {
    @Test
    public void TestConstructor() {
      try {
        Assert.assertEquals (
          null,
          new EContext(-1, ERounding.HalfEven, 0, 0, false));
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        Assert.assertEquals (
          null,
          new EContext(0, ERounding.HalfEven, 0, -1, false));
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestAdjustExponent() {
      // not implemented yet
    }
    @Test
    public void TestClampNormalExponents() {
      // not implemented yet
    }
    @Test
    public void TestCopy() {
      // not implemented yet
    }
    @Test
    public void TestEMax() {
      EContext ctx = EContext.Unlimited;
      Assert.assertEquals(EInteger.FromInt32(0), ctx.getEMax());
      ctx = EContext.Unlimited.WithExponentRange(-5, 5);
      Assert.assertEquals(EInteger.FromInt64(5), ctx.getEMax());
    }
    @Test
    public void TestEMin() {
      EContext ctx = EContext.Unlimited;
      Assert.assertEquals(EInteger.FromInt32(0), ctx.getEMin());
      ctx = EContext.Unlimited.WithExponentRange(-5, 5);
      Assert.assertEquals(EInteger.FromInt64(-5), ctx.getEMin());
    }
    @Test
    public void TestExponentWithinRange() {
      if (!(EContext.Unlimited.ExponentWithinRange(
  EInteger.FromString (
            "-9999999"))))Assert.fail();

      if (!(EContext.Unlimited.ExponentWithinRange(
  EInteger.FromString (
            "9999999"))))Assert.fail();
      try {
        EContext.Unlimited.ExponentWithinRange(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestFlags() {
      EContext ctx = EContext.Unlimited;
      try {
        ctx.setFlags(5);
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      ctx = ctx.WithBlankFlags();
      try {
        ctx.setFlags(5);
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      ctx = ctx.WithNoFlags();
      try {
        ctx.setFlags(5);
        Assert.fail("Should have failed");
      } catch (IllegalStateException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }

    @Test
    public void TestCliDecimal() {
      EDecimal valueEdTmp;
      valueEdTmp = EDecimal.FromString (
          "-79228162514264337593543950336")
        .RoundToPrecision(EContext.CliDecimal);
      Assert.assertEquals(
        EDecimal.NegativeInfinity,
        valueEdTmp);
      valueEdTmp = EDecimal.FromString (
          "8.782580686213340724E+28")
        .RoundToPrecision(EContext.CliDecimal);
      Assert.assertEquals(
        EDecimal.PositiveInfinity,
        valueEdTmp);
      {
        Object objectTemp = EDecimal.NegativeInfinity;
        Object objectTemp2 = EDecimal.FromString (
            "-9.3168444507547E+28").RoundToPrecision(EContext.CliDecimal);
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      {
        String stringTemp =

          EDecimal.FromString (
            "-9344285899206687626894794544.04982268810272216796875")
          .RoundToPrecision(EContext.CliDecimal).ToPlainString();
        Assert.assertEquals(
          "-9344285899206687626894794544",
          stringTemp);
      }
      {
        Object objectTemp = EDecimal.PositiveInfinity;
        Object objectTemp2 = EDecimal.FromString (
            "96148154858060747311034406200").RoundToPrecision(
  EContext.CliDecimal);
        Assert.assertEquals(objectTemp, objectTemp2);
      }
      {
        Object objectTemp = EDecimal.PositiveInfinity;
        Object objectTemp2 = EDecimal.FromString (
            "90246605365627217170000000000").RoundToPrecision(
  EContext.CliDecimal);
        Assert.assertEquals(objectTemp, objectTemp2);
      }
    }

    @Test
    public void TestForPrecision() {
      // not implemented yet
    }
    @Test
    public void TestForPrecisionAndRounding() {
      // not implemented yet
    }
    @Test
    public void TestForRounding() {
      EContext ctx;
      ctx = EContext.ForRounding(ERounding.HalfEven);
      Assert.assertEquals(ERounding.HalfEven, ctx.getRounding());
      ctx = EContext.ForRounding(ERounding.HalfUp);
      Assert.assertEquals(ERounding.HalfUp, ctx.getRounding());
    }
    @Test
    public void TestHasExponentRange() {
      // not implemented yet
    }
    @Test
    public void TestHasFlags() {
      // not implemented yet
    }
    @Test
    public void TestHasMaxPrecision() {
      // not implemented yet
    }
    @Test
    public void TestIsPrecisionInBits() {
      // not implemented yet
    }
    @Test
    public void TestIsSimplified() {
      // not implemented yet
    }
    @Test
    public void TestPrecision() {
      // not implemented yet
    }
    @Test
    public void TestRounding() {
      // not implemented yet
    }
    @Test
    public void TestToString() {
      if (EContext.Unlimited.toString() == null) {
        Assert.fail();
      }
    }
    @Test
    public void TestTraps() {
      // not implemented yet
    }
    @Test
    public void TestWithAdjustExponent() {
      // not implemented yet
    }
    @Test
    public void TestWithBigExponentRange() {
      // not implemented yet
    }
    @Test
    public void TestWithBigPrecision() {
      try {
        EContext.Unlimited.WithBigPrecision(null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EContext.Unlimited.WithBigPrecision(EInteger.FromInt64(-1));
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestWithBlankFlags() {
      // not implemented yet
    }
    @Test
    public void TestWithExponentClamp() {
      // not implemented yet
    }
    @Test
    public void TestWithExponentRange() {
      try {
        EContext.Unlimited.WithExponentRange(1, 0);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EContext.Unlimited.WithBigExponentRange(null, EInteger.FromInt32(0));
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EContext.Unlimited.WithBigExponentRange(EInteger.FromInt32(0), null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger bigintBig = EInteger.FromInt32(1).ShiftLeft(64);
        EContext.Unlimited.WithBigExponentRange(
          bigintBig,
          EInteger.FromInt32(0));
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    @Test
    public void TestWithNoFlags() {
      // not implemented yet
    }
    @Test
    public void TestWithPrecision() {
      try {
        EContext.Unlimited.WithPrecision(-1);
        Assert.fail("Should have failed");
      } catch (IllegalArgumentException ex) {
        // NOTE: Intentionally empty
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      EContext ctx;
      ctx = EContext.Unlimited.WithPrecision(6);
      Assert.assertEquals(EInteger.FromInt64(6), ctx.getPrecision());
    }
    @Test
    public void TestWithPrecisionInBits() {
      // not implemented yet
    }
    @Test
    public void TestWithRounding() {
      // not implemented yet
    }
    @Test
    public void TestWithSimplified() {
      EContext pc = new EContext(0, ERounding.HalfUp, 0, 5, true);
      if (pc.isSimplified()) {
 Assert.fail();
 }
      pc = pc.WithSimplified(true);
      if (!(pc.isSimplified())) {
 Assert.fail();
 }
      pc = pc.WithSimplified(false);
      if (pc.isSimplified()) {
 Assert.fail();
 }
    }
    @Test
    public void TestWithTraps() {
      // not implemented yet
    }
    @Test
    public void TestWithUnlimitedExponents() {
      EContext pc = new EContext(0, ERounding.HalfUp, 0, 5, true);
      if (!(pc.getHasExponentRange())) {
 Assert.fail();
 }
      pc = pc.WithUnlimitedExponents();
      if (pc.getHasExponentRange()) {
 Assert.fail();
 }
    }
  }
