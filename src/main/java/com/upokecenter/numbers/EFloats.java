package com.upokecenter.numbers;

  public final class EFloats {
private EFloats() {
}
    private static final int BinaryRadix = 2;

    public static EFloat Radix(EContext ec) {
      return EFloat.FromInt32(BinaryRadix).RoundToPrecision(ec);
    }

    public static EFloat Int32ToEFloat(int i32, EContext ec) {
      // NOTE: Not a miscellaneous operation in the General Decimal
      // Arithmetic Specification 1.70, but required since some of the
      // miscellaneous operations here return integers
      return EFloat.FromInt32(i32).RoundToPrecision(ec);
    }

    public static EFloat BooleanToEFloat(boolean b, EContext ec) {
      // NOTE: Not a miscellaneous operation in the General Decimal
      // Arithmetic Specification 1.70, but required since some of the
      // miscellaneous operations here return booleans
      return EFloat.FromInt32(b ? 1 : 0).RoundToPrecision(ec);
    }

    public static boolean IsCanonical(EFloat ed) {
      // Deliberately unused because all objects are in a canonical
      // form regardless of their value. Removing the parameter
      // or renaming it to be a "discard" parameter would be a
      // breaking change, though.
      return true;
    }

    public static boolean IsFinite(EFloat ed) {
      return ed != null && ed.isFinite();
    }

    public static boolean IsInfinite(EFloat ed) {
      return ed != null && ed.IsInfinity();
    }

    public static boolean IsNaN(EFloat ed) {
      return ed != null && ed.IsNaN();
    }

    public static boolean IsNormal(EFloat ed, EContext ec) {
      return ed != null && ed.isFinite() && !ed.isZero() && !IsSubnormal(ed, ec);
    }

    public static boolean IsQuietNaN(EFloat ed) {
      return ed != null && ed.IsQuietNaN();
    }

    public static boolean IsSigned(EFloat ed) {
      return ed != null && ed.isNegative();
    }

    public static boolean IsSignalingNaN(EFloat ed) {
      return ed != null && ed.IsSignalingNaN();
    }

    public static String NumberClassString(int nc) {
      return EDecimals.NumberClassString(nc);
    }

    public static int NumberClass(EFloat ed, EContext ec) {
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

    public static boolean IsSubnormal(EFloat ed, EContext ec) {
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

    public static boolean IsZero(EFloat ed) {
      return ed != null && ed.isZero();
    }

    public static EFloat LogB(EFloat ed, EContext ec) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      if (ed.IsNaN()) {
        return ed.RoundToPrecision(ec);
      }
      if (ed.IsInfinity()) {
        return EFloat.PositiveInfinity;
      }
      if (ed.isZero()) {
        return EFloat.FromInt32(-1).Divide(EFloat.Zero, ec);
      }
      EInteger ei = ed.getExponent().Add(ed.Precision().Subtract(1));
      return EFloat.FromEInteger(ei).RoundToPrecision(ec);
    }

    public static EFloat ScaleB(EFloat ed, EFloat ed2, EContext ec) {
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
      EFloat ret = EFloat.Create(
          ed.getUnsignedMantissa(),
          ed.getExponent().Add(scale));
      if (ed.isNegative()) {
        ret = ret.Negate();
      }
      return ret.RoundToPrecision(ec);
    }

    public static EFloat Shift(EFloat ed, EFloat ed2, EContext ec) {
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
      if (shift.signum() < 0) {
        if (shift.Abs().compareTo(mantprec) < 0) {
          EInteger divisor = EInteger.FromInt32(1).ShiftLeft(shift.Abs());
          mant = mant.Divide(divisor);
        } else {
          mant = EInteger.FromInt32(0);
        }
        EFloat ret = EFloat.Create(mant, ed.getExponent());
        return ed.isNegative() ? ret.Negate() : ret;
      } else {
        EInteger mult = EInteger.FromInt32(1).ShiftLeft(shift);
        mant = mant.Multiply(mult);
        if (ec != null && ec.getHasMaxPrecision()) {
          EInteger mod = EInteger.FromInt32(1).ShiftLeft(ec.getPrecision());
          mant = mant.Remainder(mod);
        }
        EFloat ret = EFloat.Create(mant, ed.getExponent());
        return ed.isNegative() ? ret.Negate() : ret;
      }
    }

    public static EFloat Rotate(EFloat ed, EFloat ed2, EContext ec) {
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
      if (ec != null && ec.getHasMaxPrecision() && mantprec.compareTo(
  ec.getPrecision()) > 0) {
        mant = mant.Remainder(EInteger.FromInt32(1).ShiftLeft(ec.getPrecision()));
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
      // Right shift
      if (rightShift.compareTo(mantprec) < 0) {
        EInteger divisor = EInteger.FromInt32(1).ShiftLeft(rightShift);
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
        EInteger mult = EInteger.FromInt32(1).ShiftLeft(leftShift);
        mantLeft = mant.Multiply(mult);
        EInteger mod = EInteger.FromInt32(1).ShiftLeft(ec.getPrecision());
        mantLeft = mantLeft.Remainder(mod);
      }
      EFloat ret = EFloat.Create(mantRight.Add(mantLeft), ed.getExponent());
      return ed.isNegative() ? ret.Negate() : ret;
    }

    public static int CompareTotal(EFloat ed, EFloat other, EContext ec) {
      return (ed == null) ? (other == null ? 0 : -1) : ((other == null) ? 1 :
          ed.CompareToTotal(other, ec));
    }

    public static int CompareTotalMagnitude(
      EFloat ed,
      EFloat other,
      EContext ec) {
      return (ed == null) ? (other == null ? 0 : -1) : ((other == null) ? 1 :
          ed.CompareToTotalMagnitude(other, ec));
    }

    public static EFloat Copy(EFloat ed) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      return ed.Copy();
    }

    public static EFloat Canonical(EFloat ed) {
      return Copy(ed);
    }

    public static EFloat CopyAbs(EFloat ed) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      return Copy(ed.Abs());
    }

    public static EFloat CopyNegate(EFloat ed) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      return Copy(ed.Negate());
    }

    public static EFloat CopySign(EFloat ed, EFloat other) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      if (other == null) {
        throw new NullPointerException("other");
      }
      return ed.isNegative() == other.isNegative() ? Copy(ed) : CopyNegate(ed);
    }

    private static EFloat InvalidOperation(EContext ec) {
      return EFloat.SignalingNaN.Plus(ec);
    }

    public static boolean SameQuantum(EFloat ed1, EFloat ed2) {
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

    public static EFloat Trim(EFloat ed1, EContext ec) {
      EFloat ed = ed1;
      if (ed1 == null) {
        return InvalidOperation(ec);
      }
      if (ed.IsSignalingNaN()) {
        return EFloat.CreateNaN(
          ed.getUnsignedMantissa(),
          true,
          ed.isNegative(),
          ec);
      }
      if (ed.isFinite()) {
        if (ed.isZero()) {
          return (ed.isNegative() ? EFloat.NegativeZero :
              EFloat.Zero).RoundToPrecision(ec);
        } else if (ed.getExponent().signum() > 0) {
          return ed.Reduce(ec);
        } else if (ed.getExponent().signum() == 0) {
          return ed.RoundToPrecision(ec);
        } else {
          EInteger exp = ed.getExponent();
          EInteger mant = ed.getUnsignedMantissa();
          boolean neg = ed.isNegative();
          boolean trimmed = false;
          EInteger radixint = EInteger.FromInt32(BinaryRadix);
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
          EFloat ret = EFloat.Create(mant, exp);
          if (neg) {
            ret = ret.Negate();
          }
          return ret.RoundToPrecision(ec);
        }
      } else {
        return ed1.Plus(ec);
      }
    }

    public static EFloat Rescale(EFloat ed, EFloat scale, EContext ec) {
      if (ed == null || scale == null) {
        return InvalidOperation(ec);
      }
      if (!scale.isFinite()) {
        return ed.Quantize(scale, ec);
      }
      if (scale.getExponent().isZero()) {
        return ed.Quantize(EFloat.Create(EInteger.FromInt32(1), scale.getMantissa()), ec);
      } else {
        EContext tec = ec == null ? null : ec.WithTraps(0).WithBlankFlags();
        EFloat rv = scale.RoundToExponentExact(0, tec);
        if (!rv.isFinite() || (tec.getFlags() & EContext.FlagInexact) != 0) {
          if (ec != null && ec.isSimplified()) {
            // In simplified arithmetic, round scale to trigger
            // appropriate error conditions
            scale = scale.RoundToPrecision(ec);
          }
          return InvalidOperation(ec);
        }
        EFloat rounded = scale.Quantize(0, tec);
        return ed.Quantize(
            EFloat.Create(EInteger.FromInt32(1), rounded.getMantissa()),
            ec);
      }
    }

    // Logical Operations

    public static EFloat And(EFloat ed1, EFloat ed2, EContext ec) {
      byte[] logi1 = EDecimals.FromLogical(ed1, ec, 2);
      if (logi1 == null) {
        return InvalidOperation(ec);
      }
      byte[] logi2 = EDecimals.FromLogical(ed2, ec, 2);
      if (logi2 == null) {
        return InvalidOperation(ec);
      }
      byte[] smaller = logi1.length < logi2.length ? logi1 : logi2;
      byte[] bigger = logi1.length < logi2.length ? logi2 : logi1;
      for (int i = 0; i < smaller.length; ++i) {
        smaller[i] &= bigger[i];
      }
      return EFloat.FromEInteger(
          EDecimals.ToLogical(
            smaller,
            2)).RoundToPrecision(ec);
    }

    public static EFloat Invert(EFloat ed1, EContext ec) {
      if (ec == null || !ec.getHasMaxPrecision()) {
        return InvalidOperation(ec);
      }
      EInteger ei = EInteger.FromInt32(1).ShiftLeft(ec.getPrecision()).Subtract(1);
      byte[] smaller = EDecimals.FromLogical(ed1, ec, 2);
      if (smaller == null) {
        return InvalidOperation(ec);
      }
      byte[] bigger = ei.ToBytes(true);

      for (int i = 0; i < smaller.length; ++i) {
        bigger[i] ^= smaller[i];
      }
      return EFloat.FromEInteger(
          EDecimals.ToLogical(
            bigger,
            2)).RoundToPrecision(ec);
    }

    public static EFloat Xor(EFloat ed1, EFloat ed2, EContext ec) {
      byte[] logi1 = EDecimals.FromLogical(ed1, ec, 2);
      if (logi1 == null) {
        return InvalidOperation(ec);
      }
      byte[] logi2 = EDecimals.FromLogical(ed2, ec, 2);
      if (logi2 == null) {
        return InvalidOperation(ec);
      }
      byte[] smaller = logi1.length < logi2.length ? logi1 : logi2;
      byte[] bigger = logi1.length < logi2.length ? logi2 : logi1;
      for (int i = 0; i < smaller.length; ++i) {
        bigger[i] ^= smaller[i];
      }
      return EFloat.FromEInteger(
          EDecimals.ToLogical(
            bigger,
            2)).RoundToPrecision(ec);
    }

    public static EFloat Or(EFloat ed1, EFloat ed2, EContext ec) {
      byte[] logi1 = EDecimals.FromLogical(ed1, ec, 2);
      if (logi1 == null) {
        return InvalidOperation(ec);
      }
      byte[] logi2 = EDecimals.FromLogical(ed2, ec, 2);
      if (logi2 == null) {
        return InvalidOperation(ec);
      }
      byte[] smaller = logi1.length < logi2.length ? logi1 : logi2;
      byte[] bigger = logi1.length < logi2.length ? logi2 : logi1;
      for (int i = 0; i < smaller.length; ++i) {
        bigger[i] |= smaller[i];
      }
      return EFloat.FromEInteger(
          EDecimals.ToLogical(
            bigger,
            2)).RoundToPrecision(ec);
    }
  }
