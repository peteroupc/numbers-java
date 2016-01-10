package com.upokecenter.test;
/*
Written in 2013 by Peter O.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://upokecenter.dreamhosters.com/articles/donate-now-2/
 */

import org.junit.Assert;
import org.junit.Test;
import com.upokecenter.numbers.*;

  public class ExtraTest {
    @Test
    public void TestExtendedInfinity() {
      if (!(EDecimal.PositiveInfinity.IsPositiveInfinity()))Assert.fail();
      if (EDecimal.PositiveInfinity.IsNegativeInfinity())Assert.fail();
      if (EDecimal.PositiveInfinity.isNegative())Assert.fail();
      if (EDecimal.NegativeInfinity.IsPositiveInfinity())Assert.fail();
      if (!(EDecimal.NegativeInfinity.IsNegativeInfinity()))Assert.fail();
      if (!(EDecimal.NegativeInfinity.isNegative()))Assert.fail();
      if (!(EFloat.PositiveInfinity.IsInfinity()))Assert.fail();
      if (!(EFloat.PositiveInfinity.IsPositiveInfinity()))Assert.fail();
      if (EFloat.PositiveInfinity.IsNegativeInfinity())Assert.fail();
      if (EFloat.PositiveInfinity.isNegative())Assert.fail();
      if (!(EFloat.NegativeInfinity.IsInfinity()))Assert.fail();
      if (EFloat.NegativeInfinity.IsPositiveInfinity())Assert.fail();
      if (!(EFloat.NegativeInfinity.IsNegativeInfinity()))Assert.fail();
      if (!(EFloat.NegativeInfinity.isNegative()))Assert.fail();
      if (!(ERational.PositiveInfinity.IsInfinity()))Assert.fail();
      if (!(ERational.PositiveInfinity.IsPositiveInfinity()))Assert.fail();
      if (ERational.PositiveInfinity.IsNegativeInfinity())Assert.fail();
      if (ERational.PositiveInfinity.isNegative())Assert.fail();
      if (!(ERational.NegativeInfinity.IsInfinity()))Assert.fail();
      if (ERational.NegativeInfinity.IsPositiveInfinity())Assert.fail();
      if (!(ERational.NegativeInfinity.IsNegativeInfinity()))Assert.fail();
      if (!(ERational.NegativeInfinity.isNegative()))Assert.fail();

      Assert.assertEquals(
        EDecimal.PositiveInfinity,
        EDecimal.FromDouble(Double.POSITIVE_INFINITY));
      Assert.assertEquals(
        EDecimal.NegativeInfinity,
        EDecimal.FromDouble(Double.NEGATIVE_INFINITY));
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
        ERational.FromExtendedDecimal(EDecimal.PositiveInfinity));
      Assert.assertEquals(
        ERational.NegativeInfinity,
        ERational.FromExtendedDecimal(EDecimal.NegativeInfinity));
      Assert.assertEquals(
        ERational.PositiveInfinity,
        ERational.FromExtendedFloat(EFloat.PositiveInfinity));
      Assert.assertEquals(
        ERational.NegativeInfinity,
        ERational.FromExtendedFloat(EFloat.NegativeInfinity));

  if (!(((ERational.PositiveInfinity.ToDouble()) == Double.POSITIVE_INFINITY)))Assert.fail();

  if (!(((ERational.NegativeInfinity.ToDouble()) == Double.NEGATIVE_INFINITY)))Assert.fail();

  if (!(((ERational.PositiveInfinity.ToSingle()) == Float.POSITIVE_INFINITY)))Assert.fail();

  if (!(((ERational.NegativeInfinity.ToSingle()) == Float.NEGATIVE_INFINITY)))Assert.fail();
      try {
        EDecimal.PositiveInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EDecimal.NegativeInfinity.ToEInteger();
        Assert.fail("Should have failed");
      } catch (ArithmeticException ex) {
        System.out.print("");
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
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
    public void TestExtendedMiscellaneous() {
      Assert.assertEquals(EDecimal.One, EDecimal.FromInt32(1));

      Assert.assertEquals(
        EFloat.Zero,
        EDecimal.Zero.ToExtendedFloat());
      Assert.assertEquals(
        EFloat.NegativeZero,
        EDecimal.NegativeZero.ToExtendedFloat());
      if (0.0f != EFloat.Zero.ToSingle()) {
        Assert.fail("Failed " + EFloat.Zero.ToDouble());
      }
      if (0.0f != EFloat.Zero.ToDouble()) {
        Assert.fail("Failed " + EFloat.Zero.ToDouble());
      }
    }
  }
