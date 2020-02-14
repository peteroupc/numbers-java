package com.upokecenter.test;
/*
Written by Peter O. in 2013.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

import org.junit.Assert;

import com.upokecenter.numbers.*;

  public final class ExtraTest {
private ExtraTest() {
}
    public static void TestStringEqualRoundTrip(EDecimal obj) {
      if (obj == null) {
        throw new NullPointerException("obj");
      }
      String str = obj.toString();
      EDecimal newobj = EDecimal.FromString(str);
      if (str.length() < 100 || !obj.equals(newobj)) {
        TestCommon.AssertEqualsHashCode(obj, newobj);
        String str2 = newobj.toString();
        TestCommon.AssertEqualsHashCode(str, str2);
      }
    }

    public static void TestStringEqualRoundTrip(ERational obj) {
      if (obj == null) {
        throw new NullPointerException("obj");
      }
      String str = obj.toString();
      ERational newobj = ERational.FromString(str);
      if (str.length() < 100 || !obj.equals(newobj)) {
        TestCommon.AssertEqualsHashCode(obj, newobj);
        String str2 = newobj.toString();
        TestCommon.AssertEqualsHashCode(str, str2);
      }
    }
    public static void TestStringEqualRoundTrip(EInteger obj) {
      if (obj == null) {
        throw new NullPointerException("obj");
      }
      String str = obj.toString();
      EInteger newobj = EInteger.FromString(str);
      if (str.length() < 100 || !obj.equals(newobj)) {
        TestCommon.AssertEqualsHashCode(obj, newobj);
        String str2 = newobj.toString();
        TestCommon.AssertEqualsHashCode(str, str2);
      }
    }
  }
