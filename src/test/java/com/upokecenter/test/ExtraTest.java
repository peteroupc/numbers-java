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
    public static void TestStringEqualRoundTrip(EDecimal obj) {
      String str = obj.toString();
      EDecimal newobj = EDecimal.FromString(str);
      String str2 = newobj.toString();
      TestCommon.AssertEqualsHashCode(obj, newobj);
      TestCommon.AssertEqualsHashCode(str, str2);
    }

    public static void TestStringEqualRoundTrip(EInteger obj) {
      String str = obj.toString();
      EInteger newobj = EInteger.FromString(str);
      String str2 = newobj.toString();
      TestCommon.AssertEqualsHashCode(obj, newobj);
      TestCommon.AssertEqualsHashCode(str, str2);
    }

    public static void TestStringEqualRoundTrip(EFloat obj) {
      String str = obj.toString();
      EFloat newobj = EFloat.FromString(str);
      String str2 = newobj.toString();
      TestCommon.AssertEqualsHashCode(obj, newobj);
      TestCommon.AssertEqualsHashCode(str, str2);
    }

    public static void TestStringEqualRoundTrip(ERational obj) {
      String str = obj.toString();
      ERational newobj = ERational.FromString(str);
      String str2 = newobj.toString();
      TestCommon.AssertEqualsHashCode(obj, newobj);
      TestCommon.AssertEqualsHashCode(str, str2);
    }
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

  if (!(((ERational.PositiveInfinity.ToDouble()) == Double.POSITIVE_INFINITY)))Assert.fail();

  if (!(((ERational.NegativeInfinity.ToDouble()) == Double.NEGATIVE_INFINITY)))Assert.fail();

  if (!(((ERational.PositiveInfinity.ToSingle()) == Float.POSITIVE_INFINITY)))Assert.fail();

  if (!(((ERational.NegativeInfinity.ToSingle()) == Float.NEGATIVE_INFINITY)))Assert.fail();
    }
    /*
    @Test
    public void TestEIntegerAnd() {
      try {
        EInteger.And(EInteger.FromInt32(0), null);
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
      try {
        EInteger.And(null, EInteger.FromInt32(0));
        Assert.fail("Should have failed");
      } catch (NullPointerException ex) {
        new Object();
      } catch (Exception ex) {
        Assert.fail(ex.toString());
        throw new IllegalStateException("", ex);
      }
    }
    */
  }
