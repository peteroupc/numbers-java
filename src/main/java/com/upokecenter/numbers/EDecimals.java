package com.upokecenter.numbers;

  public final class EDecimals {
private EDecimals() {
}
    private static final int DecimalRadix = 10;

    public static EDecimal Radix(EContext ec) {
      return EDecimal.FromInt32(DecimalRadix).RoundToPrecision(ec);
    }

    public static EDecimal Int32ToEDecimal(int i32, EContext ec) {
      // NOTE: Not a miscellaneous operation in the General Decimal
      // Arithmetic Specification 1.70, but required since some of the
      // miscellaneous operations here return integers
      return EDecimal.FromInt32(i32).RoundToPrecision(ec);
    }

    public static EDecimal BooleanToEDecimal(boolean b, EContext ec) {
      // NOTE: Not a miscellaneous operation in the General Decimal
      // Arithmetic Specification 1.70, but required since some of the
      // miscellaneous operations here return booleans
      return EDecimal.FromInt32(b ? 1 : 0).RoundToPrecision(ec);
    }

    public static boolean IsCanonical(EDecimal ed) {
      // Deliberately unused because all objects are in a canonical
      // form regardless of their value. Removing the parameter
      // or renaming it to be a "discard" parameter would be a
      // breaking change, though.
      return true;
    }

    public static boolean IsFinite(EDecimal ed) {
      return ed != null && ed.isFinite();
    }

    public static boolean IsInfinite(EDecimal ed) {
      return ed != null && ed.IsInfinity();
    }

    public static boolean IsNaN(EDecimal ed) {
      return ed != null && ed.IsNaN();
    }

    public static boolean IsNormal(EDecimal ed, EContext ec) {
      return ed != null && ed.isFinite() && !ed.isZero() && !IsSubnormal(ed, ec);
    }

    public static boolean IsQuietNaN(EDecimal ed) {
      return ed != null && ed.IsQuietNaN();
    }

    public static boolean IsSigned(EDecimal ed) {
      return ed != null && ed.isNegative();
    }

    public static boolean IsSignalingNaN(EDecimal ed) {
      return ed != null && ed.IsSignalingNaN();
    }

    private static final String[] NumberClasses = {
      "+Normal", "-Normal",
      "+Subnormal", "-Subnormal",
      "+Zero", "-Zero",
      "+Infinity", "-Infinity",
      "NaN", "sNaN",
    };

    public static String NumberClassString(int nc) {
      if (nc < 0) {
        throw new IllegalArgumentException("nc(" + nc +
          ") is not greater or equal to 0");
      }
      if (nc > 9) {
        throw new IllegalArgumentException("nc(" + nc +
          ") is not less or equal to 9");
      }
      return NumberClasses[nc];
    }

    public static int NumberClass(EDecimal ed, EContext ec) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      if (ed.IsQuietNaN()) {
        return 8;
      }
      if (ed.IsNaN()) {
        return 9;
      }
      if (ed.IsInfinity()) {
        return ed.isNegative() ? 7 : 6;
      }
      if (ed.isZero()) {
        return ed.isNegative() ? 5 : 4;
      }
      return IsSubnormal(ed, ec) ? (ed.isNegative() ? 3 : 2) :
        (ed.isNegative() ? 1 : 0);
    }

    public static boolean IsSubnormal(EDecimal ed, EContext ec) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      if (ed.isFinite() && ec != null && !ed.isZero() && ec.getHasExponentRange()) {
        if (ec.getAdjustExponent()) {
          return ed.getExponent().Add(ed.Precision().Subtract(1)).compareTo(
              ec.getEMin()) < 0;
        } else {
          return ed.getExponent().compareTo(ec.getEMin()) < 0;
        }
      }
      return false;
    }

    public static boolean IsZero(EDecimal ed) {
      return ed != null && ed.isZero();
    }

    public static EDecimal LogB(EDecimal ed, EContext ec) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      if (ed.IsNaN()) {
        return ed.RoundToPrecision(ec);
      }
      if (ed.IsInfinity()) {
        return EDecimal.PositiveInfinity;
      }
      if (ed.isZero()) {
        return EDecimal.FromInt32(-1).Divide(EDecimal.Zero, ec);
      }
      EInteger ei = ed.getExponent().Add(ed.Precision().Subtract(1));
      return EDecimal.FromEInteger(ei).RoundToPrecision(ec);
    }

    public static EDecimal ScaleB(EDecimal ed, EDecimal ed2, EContext ec) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      if (ed2 == null) {
        throw new NullPointerException("ed2");
      }
      if (ed.IsNaN() || ed2.IsNaN()) {
        return ed.Add(ed2, ec);
      }
      if (!ed2.isFinite() || ed2.getExponent().signum() != 0) {
        return InvalidOperation(ec);
      }
      EInteger scale = ed2.getMantissa();
      if (ec != null && ec.getHasMaxPrecision() && ec.getHasExponentRange()) {
        EInteger exp = ec.getEMax().Add(ec.getPrecision()).Multiply(2);
        if (scale.Abs().compareTo(exp.Abs()) > 0) {
          return InvalidOperation(ec);
        }
      }
      if (ed.IsInfinity()) {
        return ed;
      }
      if (scale.isZero()) {
        return ed.RoundToPrecision(ec);
      }
      EDecimal ret = EDecimal.Create(
          ed.getUnsignedMantissa(),
          ed.getExponent().Add(scale));
      if (ed.isNegative()) {
        ret = ret.Negate();
      }
      return ret.RoundToPrecision(ec);
    }

    public static EDecimal Shift(EDecimal ed, EDecimal ed2, EContext ec) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      if (ed2 == null) {
        throw new NullPointerException("ed2");
      }
      if (ed.IsNaN() || ed2.IsNaN()) {
        return ed.Add(ed2, ec);
      }
      if (!ed2.isFinite() || ed2.getExponent().signum() != 0) {
        return InvalidOperation(ec);
      }
      EInteger shift = ed2.getMantissa();
      if (ec != null) {
        if (shift.Abs().compareTo(ec.getPrecision()) > 0) {
          return InvalidOperation(ec);
        }
      }
      if (ed.IsInfinity()) {
        // NOTE: Must check for validity of second
        // parameter first, before checking if first
        // parameter is infinity here
        return ed;
      }
      EInteger mant = ed.getUnsignedMantissa();
      if (mant.isZero()) {
        return ed.RoundToPrecision(ec);
      }
      EInteger mantprec = ed.Precision();
      EInteger radix = EInteger.FromInt32(DecimalRadix);
      if (shift.signum() < 0) {
        if (shift.Abs().compareTo(mantprec) < 0) {
          EInteger divisor = radix.Pow(shift.Abs());
          mant = mant.Divide(divisor);
        } else {
          mant = EInteger.FromInt32(0);
        }
        EDecimal ret = EDecimal.Create(mant, ed.getExponent());
        return ed.isNegative() ? ret.Negate() : ret;
      } else {
        EInteger mult = radix.Pow(shift);
        mant = mant.Multiply(mult);
        if (ec != null && ec.getHasMaxPrecision()) {
          EInteger mod = radix.Pow(ec.getPrecision());
          mant = mant.Remainder(mod);
        }
        EDecimal ret = EDecimal.Create(mant, ed.getExponent());
        return ed.isNegative() ? ret.Negate() : ret;
      }
    }

    public static EDecimal Rotate(EDecimal ed, EDecimal ed2, EContext ec) {
      if (ec == null || !ec.getHasMaxPrecision()) {
        return Shift(ed, ed2, ec);
      }
      if (ed2 == null) {
        throw new NullPointerException("ed2");
      }
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      if (ed.IsNaN() || ed2.IsNaN()) {
        return ed.Add(ed2, ec);
      }
      if (!ed2.isFinite() || ed2.getExponent().signum() != 0) {
        return InvalidOperation(ec);
      }
      EInteger shift = ed2.getMantissa();
      if (shift.Abs().compareTo(ec.getPrecision()) > 0) {
        return InvalidOperation(ec);
      }
      if (ed.IsInfinity()) {
        // NOTE: Must check for validity of second
        // parameter first, before checking if first
        // parameter is infinity here
        return ed;
      }
      EInteger mant = ed.getUnsignedMantissa();
      EInteger mantprec = ed.Precision();
      if (ec != null && ec.getHasMaxPrecision() &&
        mantprec.compareTo(ec.getPrecision()) > 0) {
        mant = mant.Remainder(
            EInteger.FromInt32(DecimalRadix).Pow(ec.getPrecision()));
        mantprec = ec.getPrecision();
      }
      if (mant.isZero()) {
        return ed.RoundToPrecision(ec);
      }
      EInteger rightShift = shift.signum() < 0 ? shift.Abs() :
        ec.getPrecision().Subtract(shift);
      EInteger leftShift = ec.getPrecision().Subtract(rightShift);
      EInteger mantRight = EInteger.FromInt32(0);
      EInteger mantLeft = EInteger.FromInt32(0);
      EInteger radix = EInteger.FromInt32(DecimalRadix);
      // Right shift
      if (rightShift.compareTo(mantprec) < 0) {
        EInteger divisor = radix.Pow(rightShift);
        mantRight = mant.Divide(divisor);
      } else {
        mantRight = EInteger.FromInt32(0);
      }
      // Left shift
      if (leftShift.isZero()) {
        mantLeft = mant;
      } else if (leftShift.compareTo(ec.getPrecision()) == 0) {
        mantLeft = EInteger.FromInt32(0);
      } else {
        EInteger mult = radix.Pow(leftShift);
        mantLeft = mant.Multiply(mult);
        EInteger mod = radix.Pow(ec.getPrecision());
        mantLeft = mantLeft.Remainder(mod);
      }
      EDecimal ret = EDecimal.Create(mantRight.Add(mantLeft), ed.getExponent());
      return ed.isNegative() ? ret.Negate() : ret;
    }

    public static int CompareTotal(EDecimal ed, EDecimal other, EContext ec) {
      return (ed == null) ? (other == null ? 0 : -1) : ((other == null) ? 1 :
          ed.CompareToTotal(other, ec));
    }

    public static int CompareTotalMagnitude(
      EDecimal ed,
      EDecimal other,
      EContext ec) {
      return (ed == null) ? (other == null ? 0 : -1) : ((other == null) ? 1 :
          ed.CompareToTotalMagnitude(other, ec));
    }

    public static EDecimal Copy(EDecimal ed) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      return ed.Copy();
    }

    public static EDecimal Canonical(EDecimal ed) {
      return Copy(ed);
    }

    public static EDecimal CopyAbs(EDecimal ed) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      return Copy(ed.Abs());
    }

    public static EDecimal CopyNegate(EDecimal ed) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      return Copy(ed.Negate());
    }

    public static EDecimal CopySign(EDecimal ed, EDecimal other) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      if (other == null) {
        throw new NullPointerException("other");
      }
      return ed.isNegative() == other.isNegative() ? Copy(ed) : CopyNegate(ed);
    }

    private static EDecimal InvalidOperation(EContext ec) {
      return EDecimal.SignalingNaN.Plus(ec);
    }

    public static boolean SameQuantum(EDecimal ed1, EDecimal ed2) {
      if (ed1 == null || ed2 == null) {
        return false;
      }
      if (ed1.isFinite() && ed2.isFinite()) {
        return ed1.getExponent().equals(ed2.getExponent());
      } else {
        return (ed1.IsNaN() && ed2.IsNaN()) || (ed1.IsInfinity() &&
            ed2.IsInfinity());
      }
    }

    public static EDecimal Trim(EDecimal ed1, EContext ec) {
      EDecimal ed = ed1;
      if (ed1 == null) {
        return InvalidOperation(ec);
      }
      if (ed.IsSignalingNaN()) {
        return EDecimal.CreateNaN(
          ed.getUnsignedMantissa(),
          true,
          ed.isNegative(),
          ec);
      }
      if (ed.isFinite()) {
        if (ed.isZero()) {
          return (ed.isNegative() ? EDecimal.NegativeZero :
              EDecimal.Zero).RoundToPrecision(ec);
        } else if (ed.getExponent().signum() > 0) {
          return ed.Reduce(ec);
        } else if (ed.getExponent().signum() == 0) {
          return ed.RoundToPrecision(ec);
        } else {
          EInteger exp = ed.getExponent();
          EInteger mant = ed.getUnsignedMantissa();
          boolean neg = ed.isNegative();
          boolean trimmed = false;
          EInteger radixint = EInteger.FromInt32(DecimalRadix);
          while (exp.signum() < 0 && mant.signum() > 0) {
            EInteger[] divrem = mant.DivRem(radixint);
            int rem = divrem[1].ToInt32Checked();
            if (rem != 0) {
              break;
            }
            mant = divrem[0];
            exp = exp.Add(1);
            trimmed = true;
          }
          if (!trimmed) {
            return ed.RoundToPrecision(ec);
          }
          EDecimal ret = EDecimal.Create(mant, exp);
          if (neg) {
            ret = ret.Negate();
          }
          return ret.RoundToPrecision(ec);
        }
      } else {
        return ed1.Plus(ec);
      }
    }

    public static EDecimal Rescale(EDecimal ed, EDecimal scale, EContext ec) {
      if (ed == null || scale == null) {
        return InvalidOperation(ec);
      }
      if (!scale.isFinite()) {
        return ed.Quantize(scale, ec);
      }
      if (scale.getExponent().isZero()) {
        return ed.Quantize(EDecimal.Create(EInteger.FromInt32(1), scale.getMantissa()), ec);
      } else {
        EContext tec = ec == null ? null : ec.WithTraps(0).WithBlankFlags();
        EDecimal rv = scale.RoundToExponentExact(0, tec);
        if (!rv.isFinite() || (tec.getFlags() & EContext.FlagInexact) != 0) {
          if (ec != null && ec.isSimplified()) {
            // In simplified arithmetic, round scale to trigger
            // appropriate error conditions
            scale = scale.RoundToPrecision(ec);
          }
          return InvalidOperation(ec);
        }
        EDecimal rounded = scale.Quantize(0, tec);
        return ed.Quantize(
            EDecimal.Create(EInteger.FromInt32(1), rounded.getMantissa()),
            ec);
      }
    }

    // Logical Operations

    public static EDecimal And(EDecimal ed1, EDecimal ed2, EContext ec) {
      byte[] logi1 = FromLogical(ed1, ec, 10);
      if (logi1 == null) {
        return InvalidOperation(ec);
      }
      byte[] logi2 = FromLogical(ed2, ec, 10);
      if (logi2 == null) {
        return InvalidOperation(ec);
      }
      byte[] smaller = logi1.length < logi2.length ? logi1 : logi2;
      byte[] bigger = logi1.length < logi2.length ? logi2 : logi1;
      for (int i = 0; i < smaller.length; ++i) {
        smaller[i] &= bigger[i];
      }
      return EDecimal.FromEInteger(ToLogical(smaller,
            10)).RoundToPrecision(ec);
    }

    public static EDecimal Invert(EDecimal ed1, EContext ec) {
      if (ec == null || !ec.getHasMaxPrecision()) {
        return InvalidOperation(ec);
      }
      byte[] smaller = FromLogical(ed1, ec, 10);
      if (smaller == null) {
        return InvalidOperation(ec);
      }
      EInteger ei = EInteger.FromInt32(1).ShiftLeft(ec.getPrecision()).Subtract(1);
      byte[] bigger = ei.ToBytes(true);

      for (int i = 0; i < smaller.length; ++i) {
        bigger[i] ^= smaller[i];
      }
      return EDecimal.FromEInteger(ToLogical(bigger, 10)).RoundToPrecision(
  ec);
    }

    public static EDecimal Xor(EDecimal ed1, EDecimal ed2, EContext ec) {
      byte[] logi1 = FromLogical(ed1, ec, 10);
      if (logi1 == null) {
        return InvalidOperation(ec);
      }
      byte[] logi2 = FromLogical(ed2, ec, 10);
      if (logi2 == null) {
        return InvalidOperation(ec);
      }
      byte[] smaller = logi1.length < logi2.length ? logi1 : logi2;
      byte[] bigger = logi1.length < logi2.length ? logi2 : logi1;
      for (int i = 0; i < smaller.length; ++i) {
        bigger[i] ^= smaller[i];
      }
      return EDecimal.FromEInteger(ToLogical(bigger, 10)).RoundToPrecision(
  ec);
    }

    public static EDecimal Or(EDecimal ed1, EDecimal ed2, EContext ec) {
      byte[] logi1 = FromLogical(ed1, ec, 10);
      if (logi1 == null) {
        return InvalidOperation(ec);
      }
      byte[] logi2 = FromLogical(ed2, ec, 10);
      if (logi2 == null) {
        return InvalidOperation(ec);
      }
      byte[] smaller = logi1.length < logi2.length ? logi1 : logi2;
      byte[] bigger = logi1.length < logi2.length ? logi2 : logi1;
      for (int i = 0; i < smaller.length; ++i) {
        bigger[i] |= smaller[i];
      }
      return EDecimal.FromEInteger(ToLogical(bigger, 10)).RoundToPrecision(
  ec);
    }

    static EInteger ToLogical(byte[] bytes, int iradix) {
      if (bytes == null) {
        throw new NullPointerException("bytes");
      }
      EInteger ret = EInteger.FromInt32(0);
      int i;
      for (i = bytes.length - 1; i >= 0; --i) {
        int b = bytes[i];
        for (int j = 7; j >= 0; --j) {
          ret = ((bytes[i] & (1 << j)) != 0) ? ret.Multiply(iradix).Add(1) :
            ret.Multiply(iradix);
        }
      }
      return ret;
    }

    static byte[] FromLogical(EInteger um, EContext ec, int iradix) {
      if (um == null || um.signum() < 0) {
        return null;
      }
      if (um.signum() == 0) {
        return new byte[] { 0 };
      }
      EInteger ret = EInteger.FromInt32(0);
      EInteger prec = um.GetDigitCountAsEInteger();
      EInteger maxprec = (ec != null && ec.getHasMaxPrecision()) ? ec.getPrecision() :
        null;
      EInteger bytecount = prec.ShiftRight(3).Add(1);
      if (bytecount.compareTo(0x7fffffff) > 0) {
        return null; // Out of memory
      }
      int bitindex = 0;
      byte[] bytes = new byte[bytecount.ToInt32Checked()];
      EInteger radixint = EInteger.FromInt32(iradix);
      while (um.signum() > 0) {
        EInteger[] divrem = um.DivRem(radixint);
        int rem = divrem[1].ToInt32Checked();
        um = divrem[0];
        if (rem == 1) {
          // Don't collect bits beyond max precision
          if (maxprec == null || maxprec.compareTo(bitindex) > 0) {
            int byteindex = bitindex >> 3;
            int mask = 1 << (bitindex & 7);
            bytes[byteindex] |= (byte)mask;
          }
        } else if (rem != 0) {
          return null;
        }
        ++bitindex;
      }
      return bytes;
    }

    static byte[] FromLogical(EDecimal ed, EContext ec, int iradix) {
      if (ed == null) {
        return null;
      }
      if (ec != null && ec.isPrecisionInBits() && iradix != 2) {
        // Round to bit precision if necessary and if the radix isn't binary
        ed = ed.RoundToPrecision(ec);
      }
      return (!ed.isFinite() || ed.isNegative() || ed.getExponent().signum() != 0 ||
          ed.getMantissa().signum() < 0) ? null : FromLogical(
            ed.getUnsignedMantissa(),
            ec,
            iradix);
    }

    static byte[] FromLogical(EFloat ed, EContext ec, int iradix) {
      if (ed == null) {
        return null;
      }
      // NOTE: Precision of EFloat is already in bits, so no need to check for
      // IsPrecisionInBits here
      return (!ed.isFinite() || ed.isNegative() || ed.getExponent().signum() != 0 ||
          ed.getMantissa().signum() < 0) ? null : FromLogical(
            ed.getUnsignedMantissa(),
            ec,
            iradix);
    }
  }
