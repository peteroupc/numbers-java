package com.upokecenter.test;
/*
Written in 2013 by Peter O.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

import org.junit.Assert;

import com.upokecenter.numbers.*;

  public class ExtraTest {
    public static void TestStringEqualRoundTrip(EDecimal obj) {
      String str = obj.toString();
      EDecimal newobj = EDecimal.FromString(str);
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

        public static void TestStringEqualRoundTrip(EInteger obj) {
      String str = obj.toString();
      EInteger newobj = EInteger.FromString(str);
      String str2 = newobj.toString();
      TestCommon.AssertEqualsHashCode(obj, newobj);
      TestCommon.AssertEqualsHashCode(str, str2);
    }

    public static void TestStringEqualRoundTrip(EFloat obj) {
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
  }
