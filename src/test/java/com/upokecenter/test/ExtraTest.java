package com.upokecenter.test;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
https://creativecommons.org/publicdomain/zero/1.0/

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
