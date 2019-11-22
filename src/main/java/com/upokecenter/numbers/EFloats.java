package com.upokecenter.numbers;

  /**
   * A class that implements additional operations on arbitrary-precision binary
   * floating-point numbers.
   */
  public final class EFloats {
private EFloats() {
}
    private static final int BinaryRadix = 2;

    /**
     * Returns the number 2, the binary radix.
     * @param ec Specifies an arithmetic context for rounding the number 2. Can be
     * null.
     * @return The number 2, or the closest representable number to 2 in the
     * arithmetic context.
     */
    public static EFloat Radix(EContext ec) {
      return EFloat.FromInt32(BinaryRadix).RoundToPrecision(ec);
    }

    /**
     * Creates a binary floating-point number from a 32-bit signed integer.
     * @param i32 The parameter {@code i32} is a 32-bit signed integer.
     * @param ec An arithmetic context to control the precision, rounding, and
     * exponent range of the result. Can be null.
     * @return An arbitrary-precision binary floating-point number with the closest
     * representable value to the given integer.
     */
    public static EFloat Int32ToEFloat(int i32, EContext ec) {
      // NOTE: Not a miscellaneous operation in the General Decimal
      // Arithmetic Specification 1.70, but required since some of the
      // miscellaneous operations here return integers
      return EFloat.FromInt32(i32).RoundToPrecision(ec);
    }

    /**
     * Converts a boolean value (either true or false) to an arbitrary-precision
     * binary floating-point number.
     * @param b Either true or false.
     * @param ec A context used for rounding the result. Can be null.
     * @return Either 1 if {@code b} is true, or 0 if {@code b} is false.. The
     * result will be rounded as specified by the given context, if any.
     */
    public static EFloat BooleanToEFloat(boolean b, EContext ec) {
      // NOTE: Not a miscellaneous operation in the General Decimal
      // Arithmetic Specification 1.70, but required since some of the
      // miscellaneous operations here return booleans
      return EFloat.FromInt32(b ? 1 : 0).RoundToPrecision(ec);
    }

    /**
     * Returns whether the given arbitrary-precision number object is in a
     * canonical form. For the current version of EDecimal, all EDecimal
     * objects are in a canonical form.
     * @param ed An arbitrary-precision number object.
     * @return Always {@code true}.
     */
    public static boolean IsCanonical(EFloat ed) {
      return true;
    }

    /**
     * Returns whether the given arbitrary-precision number object is neither null
     * nor infinity nor not-a-number (NaN).
     * @param ed An arbitrary-precision number object.
     * @return Either {@code true} if the given arbitrary-precision number object
     * is neither null nor infinity nor not-a-number (NaN), or {@code
     * false} otherwise.
     */
    public static boolean IsFinite(EFloat ed) {
      return ed != null && ed.isFinite();
    }

    /**
     * Returns whether the given arbitrary-precision number object is positive or
     * negative infinity.
     * @param ed An arbitrary-precision number object.
     * @return Either {@code true} if the given arbitrary-precision number object
     * is positive or negative infinity, or {@code false} otherwise.
     */
    public static boolean IsInfinite(EFloat ed) {
      return ed != null && ed.IsInfinity();
    }

    /**
     * Returns whether the given arbitrary-precision number object is a
     * not-a-number (NaN).
     * @param ed An arbitrary-precision number object.
     * @return Either {@code true} or {@code false}.
     */
    public static boolean IsNaN(EFloat ed) {
      return ed != null && ed.IsNaN();
    }

    /**
     * Returns whether the given number is a <i>normal</i> number. A <i>subnormal
     * number</i> is a nonzero finite number whose Exponent property (or
     * the number's exponent when that number is expressed in scientific
     * notation with one digit before the radix point) is less than the
     * minimum possible exponent for that number. A <i>normal number</i> is
     * nonzero and finite, but not subnormal.
     * @param ed An arbitrary-precision number object.
     * @param ec A context specifying the exponent range of arbitrary-precision
     * numbers. Can be null. If AdjustExponent of the given context is
     * {@code true}, a nonzero number is normal if the number's exponent
     * (when that number is expressed in scientific notation with one
     * nonzero digit before the radix point) is at least the given
     * context's EMax property (e.g., if EMax is -100, 2.3456 * 10
     * <sup>-99</sup> is normal, but 2.3456 * 10 <sup>-102</sup> is not).
     * If AdjustExponent of the given context is {@code false}, a nonzero
     * number is subnormal if the number's Exponent property is at least
     * given context's EMax property (e.g., if EMax is -100, 23456 * 10
     * <sup>-99</sup> is normal, but 23456 * 10 <sup>-102</sup> is not).
     * @return Either {@code true} if the given number is subnormal, or {@code
     * false} otherwise. Returns {@code true} if the given context is null
     * or HasExponentRange of the given context is {@code false}.
     */
    public static boolean IsNormal(EFloat ed, EContext ec) {
      return ed != null && ed.isFinite() && !ed.isZero() && !IsSubnormal(ed, ec);
    }

    /**
     * Returns whether the given arbitrary-precision number object is a quiet
     * not-a-number (NaN).
     * @param ed An arbitrary-precision number object.
     * @return Either {@code true} or {@code false}.
     */
    public static boolean IsQuietNaN(EFloat ed) {
      return ed != null && ed.IsQuietNaN();
    }

    /**
     * Returns whether the given arbitrary-precision number object is negative
     * (including negative infinity, negative not-a-number.get(NaN), or
     * negative zero).
     * @param ed An arbitrary-precision number object.
     * @return Either {@code true} or {@code false}.
     */
    public static boolean IsSigned(EFloat ed) {
      return ed != null && ed.isNegative();
    }

    /**
     * Returns whether the given arbitrary-precision number object is a signaling
     * not-a-number (NaN).
     * @param ed An arbitrary-precision number object.
     * @return Either {@code true} or {@code false}.
     */
    public static boolean IsSignalingNaN(EFloat ed) {
      return ed != null && ed.IsSignalingNaN();
    }

    /**
     * Converts a number class identifier (ranging from 0 through 9) to a text
     * string. An arbitrary-precision number object can belong in one of
     * ten number classes.
     * @param nc An integer identifying a number class.
     * @return A text string identifying the given number class as follows: 0 =
     *  "+Normal"; 1 = "-Normal", 2 = "+Subnormal", 3 = "-Subnormal", 4 =
     *  "+Zero", 5 = "-Zero", 6 = "+Infinity", 7 = "-Infinity", 8 = "NaN", 9
     *  = "sNaN".
     * @throws IllegalArgumentException The parameter {@code nc} is less than 0 or greater
     * than 9.
     */
    public static String NumberClassString(int nc) {
      return EDecimals.NumberClassString(nc);
    }

    /**
     * Finds the number class for an arbitrary-precision decimal number object.
     * @param ed An arbitrary-precision decimal number object.
     * @param ec A context object that specifies the precision and exponent range
     * of arbitrary-precision numbers. This is used only to distinguish
     * between normal and subnormal numbers. Can be null.
     * @return A 32-bit signed integer identifying the given number object, number
     * class as follows: 0 = positive normal; 1 = negative normal, 2 =
     * positive subnormal, 3 = negative subnormal, 4 = positive zero, 5 =
     * negative zero, 6 = positive infinity, 7 = negative infinity, 8 =
     * quiet not-a-number (NaN), 9 = signaling NaN.
     * @throws NullPointerException The parameter {@code ed} is null.
     */
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

    /**
     * Returns whether the given number is a <i>subnormal</i> number. A
     * <i>subnormal number</i> is a nonzero finite number whose Exponent
     * property (or the number's exponent when that number is expressed in
     * scientific notation with one digit before the radix point) is less
     * than the minimum possible exponent for that number.
     * @param ed An arbitrary-precision number object.
     * @param ec A context specifying the exponent range of arbitrary-precision
     * numbers. Can be null. If AdjustExponent of the given context is
     * {@code true}, a nonzero number is subnormal if the number's exponent
     * (when that number is expressed in scientific notation with one
     * nonzero digit before the radix point) is less than the given
     * context's EMax property (e.g., if EMax is -100, 2.3456 * 10
     * <sup>-102</sup> is subnormal, but 2.3456 * 10 <sup>-99</sup> is
     * not). If AdjustExponent of the given context is {@code false}, a
     * nonzero number is subnormal if the number's Exponent property is
     * less than the given context's EMax property (e.g., if EMax is -100,
     * 23456 * 10 <sup>-102</sup> is subnormal, but 23456 * 10
     * <sup>-99</sup> is not).
     * @return Either {@code true} if the given number is subnormal, or {@code
     * false} otherwise. Returns {@code false} if the given context is null
     * or HasExponentRange of the given context is {@code false}.
     * @throws NullPointerException The parameter {@code ed} is null.
     */
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

    /**
     * Returns whether the given arbitrary-precision number object is zero
     * (positive zero or negative zero).
     * @param ed An arbitrary-precision number object.
     * @return {@code true} if the given number has a value of zero (positive zero
     * or negative zero); otherwise, {@code false}.
     */
    public static boolean IsZero(EFloat ed) {
      return ed != null && ed.isZero();
    }

    /**
     * Returns the base-2 exponent of an arbitrary-precision binary number (when
     * that number is expressed in scientific notation with one nonzero
     * digit before the radix point). For example, returns 3 for the
     * numbers <code>1.11b * 2^3</code> and <code>111 * 2^1</code>.
     * @param ed An arbitrary-precision binary number.
     * @param ec An arithmetic context to control the precision, rounding, and
     * exponent range of the result. Can be null.
     * @return The base-2 exponent of the given number (when that number is
     * expressed in scientific notation with one nonzero digit before the
     * radix point). Signals DivideByZero and returns negative infinity if
     * {@code ed} is zero. Returns positive infinity if {@code ed} is
     * positive infinity or negative infinity.
     * @throws NullPointerException The parameter {@code ed} is null.
     */
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

    /**
     * Finds an arbitrary-precision binary number whose binary point is moved a
     * given number of places.
     * @param ed An arbitrary-precision binary number.
     * @param ed2 The number of binary places to move the binary point of "ed".
     * This must be an integer with an exponent of 0.
     * @param ec An arithmetic context to control the precision, rounding, and
     * exponent range of the result. Can be null.
     * @return The given arbitrary-precision binary number whose binary point is
     * moved the given number of places. Signals an invalid operation and
     * returns not-a-number (NaN) if {@code ed2} is infinity or NaN, has an
     * Exponent property other than 0. Signals an invalid operation and
     * returns not-a-number (NaN) if {@code ec} defines a limited precision
     * and exponent range and if {@code ed2} 's absolute value is greater
     * than twice the sum of the context's EMax property and its Precision
     * property.
     * @throws NullPointerException The parameter {@code ed} or {@code ed2} is
     * null.
     */
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
        return InvalidOperation(EFloat.NaN, ec);
      }
      EInteger scale = ed2.getMantissa();
      if (ec != null && ec.getHasMaxPrecision() && ec.getHasExponentRange()) {
        EInteger exp = ec.getEMax().Add(ec.getPrecision()).Multiply(2);
        if (scale.Abs().compareTo(exp.Abs()) > 0) {
          return InvalidOperation(EFloat.NaN, ec);
        }
      }
      if (ed.IsInfinity()) {
        return ed;
      }
      if (scale.isZero()) {
        return ed.RoundToPrecision(ec);
      }
      EFloat ret = EFloat.Create (
          ed.getUnsignedMantissa(),
          ed.getExponent().Add(scale));
      if (ed.isNegative()) {
        ret = ret.Negate();
      }
      return ret.RoundToPrecision(ec);
    }

    /**
     * Shifts the bits of an arbitrary-precision binary floating point number's
     * significand.
     * @param ed An arbitrary-precision binary floating point number containing the
     * significand to shift.
     * @param ed2 An arbitrary-precision number indicating the number of bits to
     * shift the first operand's significand. Must be an integer with an
     * exponent of 0. If this parameter is positive, the significand is
     * shifted to the left by the given number of bits. If this parameter
     * is negative, the significand is shifted to the right by the given
     * number of bits.
     * @param ec An arithmetic context to control the precision of
     * arbitrary-precision numbers. Can be null.
     * @return An arbitrary-precision decimal number whose significand is shifted
     * the given number of bits. Signals an invalid operation and returns
     * NaN (not-a-number) if {@code ed2} is a signaling NaN or if {@code
     * ed2} is not an integer, is negative, has an exponent other than 0,
     * or has an absolute value that exceeds the maximum precision
     * specified in the context.
     * @throws NullPointerException The parameter {@code ed} or {@code ed2} is
     * null.
     */
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
        return InvalidOperation(EFloat.NaN, ec);
      }
      EInteger shift = ed2.getMantissa();
      if (ec != null) {
        if (shift.Abs().compareTo(ec.getPrecision()) > 0) {
          return InvalidOperation(EFloat.NaN, ec);
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

    /**
     * Rotates the bits of an arbitrary-precision binary number's significand.
     * @param ed An arbitrary-precision number containing the significand to
     * rotate. If this significand contains more bits than the precision,
     * the most-significant bits are chopped off the significand.
     * @param ed2 An arbitrary-precision number indicating the number of bits to
     * rotate the first operand's significand. Must be an integer with an
     * exponent of 0. If this parameter is positive, the significand is
     * shifted to the left by the given number of bits and the
     * most-significant bits shifted out of the significand become the
     * least-significant bits instead. If this parameter is negative, the
     * number is shifted by the given number of bits and the
     * least-significant bits shifted out of the significand become the
     * most-significant bits instead.
     * @param ec An arithmetic context to control the precision of
     * arbitrary-precision numbers. If this parameter is null or specifies
     * an unlimited precision, this method has the same behavior as {@code
     * Shift}.
     * @return An arbitrary-precision binary number whose significand is rotated
     * the given number of bits. Signals an invalid operation and returns
     * NaN (not-a-number) if {@code ed2} is a signaling NaN or if {@code
     * ed2} is not an integer, is negative, has an exponent other than 0,
     * or has an absolute value that exceeds the maximum precision
     * specified in the context.
     * @throws NullPointerException The parameter {@code ed2} or {@code ed} is
     * null.
     */
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
        return InvalidOperation(EFloat.NaN, ec);
      }
      EInteger shift = ed2.getMantissa();
      if (shift.Abs().compareTo(ec.getPrecision()) > 0) {
        return InvalidOperation(EFloat.NaN, ec);
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

    /**
     * Compares the values of one arbitrary-precision number object and another
     * object, imposing a total ordering on all possible values. In this
     * method: <ul> <li>For objects with the same value, the one with the
     *  higher exponent has a greater "absolute value".</li> <li>Negative
     * zero is less than positive zero.</li> <li>Quiet NaN has a higher
     *  "absolute value" than signaling NaN. If both objects are quiet NaN
     * or both are signaling NaN, the one with the higher diagnostic
     *  information has a greater "absolute value".</li> <li>NaN has a
     *  higher "absolute value" than infinity.</li> <li>Infinity has a
     *  higher "absolute value" than any finite number.</li> <li>Negative
     * numbers are less than positive numbers.</li></ul>
     * @param ed The first arbitrary-precision number to compare.
     * @param other The second arbitrary-precision number to compare.
     * @param ec An arithmetic context. Flags will be set in this context only if
     * {@code HasFlags} and {@code IsSimplified} of the context are true
     * and only if an operand needed to be rounded before carrying out the
     * operation. Can be null.
     * @return The number 0 if both objects are null or equal, or -1 if the first
     * object is null or less than the other object, or 1 if the first
     * object is greater or the other object is null. Does not signal flags
     * if either value is signaling NaN.
     */
    public static int CompareTotal(EFloat ed, EFloat other, EContext ec) {
      return (ed == null) ? (other == null ? 0 : -1) : ((other == null) ? 1 :
          ed.CompareToTotal(other, ec));
    }

    /**
     * Compares the absolute values of two arbitrary-precision number objects,
     * imposing a total ordering on all possible values (ignoring their
     * signs). In this method: <ul> <li>For objects with the same value,
     *  the one with the higher exponent has a greater "absolute
     *  value".</li> <li>Negative zero and positive zero are considered
     *  equal.</li> <li>Quiet NaN has a higher "absolute value" than
     * signaling NaN. If both objects are quiet NaN or both are signaling
     * NaN, the one with the higher diagnostic information has a greater
     *  "absolute value".</li> <li>NaN has a higher "absolute value" than
     *  infinity.</li> <li>Infinity has a higher "absolute value" than any
     * finite number.</li></ul>
     * @param ed The first arbitrary-precision number to compare.
     * @param other The second arbitrary-precision number to compare.
     * @param ec An arithmetic context. Flags will be set in this context only if
     * {@code HasFlags} and {@code IsSimplified} of the context are true
     * and only if an operand needed to be rounded before carrying out the
     * operation. Can be null.
     * @return The number 0 if both objects are null or equal (ignoring their
     * signs), or -1 if the first object is null or less than the other
     * object (ignoring their signs), or 1 if the first object is greater
     * (ignoring their signs) or the other object is null. Does not signal
     * flags if either value is signaling NaN.
     */
    public static int CompareTotalMagnitude(
      EFloat ed,
      EFloat other,
      EContext ec) {
      return (ed == null) ? (other == null ? 0 : -1) : ((other == null) ? 1 :
          ed.CompareToTotalMagnitude(other, ec));
    }

    /**
     * Creates a copy of the given arbitrary-precision number object.
     * @param ed An arbitrary-precision number object to copy.
     * @return A copy of the given arbitrary-precision number object.
     * @throws NullPointerException The parameter {@code ed} is null.
     */
    public static EFloat Copy(EFloat ed) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      return ed.Copy();
    }

    /**
     * Returns a canonical version of the given arbitrary-precision number object.
     * In this method, this method behaves like the Copy method.
     * @param ed An arbitrary-precision number object.
     * @return A copy of the parameter {@code ed}.
     */
    public static EFloat Canonical(EFloat ed) {
      return Copy(ed);
    }

    /**
     * Returns an arbitrary-precision number object with the same value as the
     * given number object but with a nonnegative sign (that is, the given
     * number object's absolute value).
     * @param ed An arbitrary-precision number object.
     * @return An arbitrary-precision number object with the same value as the
     * given number object but with a nonnegative sign.
     * @throws NullPointerException The parameter {@code ed} is null.
     */
    public static EFloat CopyAbs(EFloat ed) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      return Copy(ed.Abs());
    }

    /**
     * Returns an arbitrary-precision number object with the sign reversed from the
     * given number object.
     * @param ed An arbitrary-precision number object.
     * @return An arbitrary-precision number object with the sign reversed from the
     * given number object.
     * @throws NullPointerException The parameter {@code ed} is null.
     */
    public static EFloat CopyNegate(EFloat ed) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      return Copy(ed.Negate());
    }

    /**
     * Returns an arbitrary-precision number object with the same value as the
     * first given number object but with a the same sign (positive or
     * negative) as the second given number object.
     * @param ed An arbitrary-precision number object with the value the result
     * will have.
     * @param other The parameter {@code other} is an arbitrary-precision decimal
     * floating-point number.
     * @return An arbitrary-precision number object with the same value as the
     * first given number object but with a the same sign (positive or
     * negative) as the second given number object.
     * @throws NullPointerException The parameter {@code ed} or {@code other} is
     * null.
     */
    public static EFloat CopySign(EFloat ed, EFloat other) {
      if (ed == null) {
        throw new NullPointerException("ed");
      }
      if (other == null) {
        throw new NullPointerException("other");
      }
      return ed.isNegative() == other.isNegative() ? Copy(ed) : CopyNegate(ed);
    }

    private static EFloat InvalidOperation(EFloat ed, EContext ec) {
      if (ec != null) {
        if (ec.getHasFlags()) {
          ec.setFlags(ec.getFlags()|(EContext.FlagInvalid));
        }
        if ((ec.getTraps() & EContext.FlagInvalid) != 0) {
          throw new ETrapException(EContext.FlagInvalid, ec, ed);
        }
      }
      return ed;
    }

    /**
     * Returns whether two arbitrary-precision numbers have the same exponent, they
     * both are not-a-number (NaN), or they both are infinity (positive
     * and/or negative).
     * @param ed1 The first arbitrary-precision number.
     * @param ed2 The second arbitrary-precision number.
     * @return Either {@code true} if the given arbitrary-precision numbers have
     * the same exponent, they both are not-a-number (NaN), or they both
     * are infinity (positive and/or negative); otherwise, {@code false}.
     */
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

    /**
     * Returns an arbitrary-precision number with the same value as this one but
     * with certain trailing zeros removed from its significand. If the
     * number's exponent is 0, it is returned unchanged (but may be rounded
     * depending on the arithmetic context); if that exponent is greater 0,
     * its trailing zeros are removed from the significand (then rounded if
     * necessary); if that exponent is less than 0, its trailing zeros are
     * removed from the significand until the exponent reaches 0 (then the
     * number is rounded if necessary).
     * @param ed1 An arbitrary-precision number.
     * @param ec An arithmetic context to control the precision, rounding, and
     * exponent range of the result. Can be null.
     * @return An arbitrary-precision number with the same value as this one but
     * with certain trailing zeros removed from its significand. If {@code
     * ed1} is not-a-number (NaN) or infinity, it is generally returned
     * unchanged.
     */
    public static EFloat Trim(EFloat ed1, EContext ec) {
      EFloat ed = ed1;
      if (ed1 == null) {
        return InvalidOperation(EFloat.NaN, ec);
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

    /**
     * Returns an arbitrary-precision binary number with the same value as this
     * object but with the given exponent, expressed as an
     * arbitrary-precision binary number. <p>Note that this is not always
     * the same as rounding to a given number of binary places, since it
     * can fail if the difference between this value's exponent and the
     * desired exponent is too big, depending on the maximum precision. If
     * rounding to a number of binary places is desired, it's better to use
     * the RoundToExponent and RoundToIntegral methods instead.</p>
     * <p><b>Remark:</b> This method can be used to implement fixed-point
     * binary arithmetic, in which a fixed number of digits come after the
     * binary point. A fixed-point binary arithmetic in which no digits
     * come after the binary point (a desired exponent of 0) is considered
     *  an "integer arithmetic" .</p>
     * @param ed An arbitrary-precision binary number whose exponent is to be
     * changed.
     * @param scale The desired exponent of the result, expressed as an
     * arbitrary-precision binary number. The exponent is the number of
     * fractional digits in the result, expressed as a negative number. Can
     * also be positive, which eliminates lower-order places from the
     * number. For example, -3 means round to the sixteenth (10b^-3,
     * 0.0001b), and 3 means round to the sixteens-place (10b^3, 1000b). A
     * value of 0 rounds the number to an integer.
     * @param ec An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags). Can be null, in which case the default
     * rounding mode is HalfEven.
     * @return An arbitrary-precision binary number with the same value as this
     * object but with the exponent changed. Signals FlagInvalid and
     * returns not-a-number (NaN) if the result can't fit the given
     * precision without rounding, or if the arithmetic context defines an
     * exponent range and the given exponent is outside that range.
     */
    public static EFloat Rescale(EFloat ed, EFloat scale, EContext ec) {
      if (ed == null || scale == null) {
        return InvalidOperation(EFloat.NaN, ec);
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
          return InvalidOperation(EFloat.NaN, ec);
        }
        EFloat rounded = scale.Quantize(0, tec);
        return ed.Quantize (
            EFloat.Create(EInteger.FromInt32(1), rounded.getMantissa()),
            ec);
      }
    }

    // Logical Operations

    /**
     * Performs a logical AND operation on two binary numbers in the form of
     * <i>logical operands</i>. A <code>logical operand</code> is a non-negative
     * base-2 number with an Exponent property of 0 (examples include the
     * base-2 numbers <code>01001</code> and <code>111001</code>). The logical AND
     * operation sets each bit of the result to 1 if the corresponding bits
     * of each logical operand are both 1, and to 0 otherwise. For example,
     * <code>01001 AND 111010 = 01000</code>.
     * @param ed1 The first logical operand to the logical AND operation.
     * @param ed2 The second logical operand to the logical AND operation.
     * @param ec An arithmetic context to control the maximum precision of
     * arbitrary-precision numbers. If a logical operand passed to this
     * method has more bits than the maximum precision specified in this
     * context, the operand's most significant bits that exceed that
     * precision are discarded. This parameter can be null.
     * @return The result of the logical AND operation as a logical operand.
     * Signals an invalid operation and returns not-a-number (NaN) if
     * {@code ed1}, {@code ed2}, or both are not logical operands.
     */
    public static EFloat And(EFloat ed1, EFloat ed2, EContext ec) {
      byte[] logi1 = EDecimals.FromLogical(ed1, ec, 2);
      if (logi1 == null) {
        return InvalidOperation(EFloat.NaN, ec);
      }
      byte[] logi2 = EDecimals.FromLogical(ed2, ec, 2);
      if (logi2 == null) {
        return InvalidOperation(EFloat.NaN, ec);
      }
      byte[] smaller = logi1.length < logi2.length ? logi1 : logi2;
      byte[] bigger = logi1.length < logi2.length ? logi2 : logi1;
      for (int i = 0; i < smaller.length; ++i) {
        smaller[i] &= bigger[i];
      }
      return EFloat.FromEInteger (
          EDecimals.ToLogical(
            smaller,
            2)).RoundToPrecision(ec);
    }

    /**
     * Performs a logical NOT operation on a binary number in the form of a
     * <i>logical operand</i>. A <code>logical operand</code> is a non-negative
     * base-2 number with an Exponent property of 0 (examples include
     * <code>01001</code> and <code>111001</code>). The logical NOT operation sets
     * each bit of the result to 1 if the corresponding bit is 0, and to 0
     * otherwise; it can set no more bits than the maximum precision,
     * however. For example, if the maximum precision is 8 bits, then
     * <code>NOT 111010 = 11000101</code>.
     * @param ed1 The operand to the logical NOT operation.
     * @param ec An arithmetic context to control the maximum precision of
     * arbitrary-precision numbers. If a logical operand passed to this
     * method has more bits than the maximum precision specified in this
     * context, the operand's most significant bits that exceed that
     * precision are discarded. This parameter cannot be null and must
     * specify a maximum precision (unlimited precision contexts are not
     * allowed).
     * @return The result of the logical NOT operation as a logical operand.
     * Signals an invalid operation and returns not-a-number (NaN) if
     * {@code ed1} is not a logical operand.
     */
    public static EFloat Invert(EFloat ed1, EContext ec) {
      if (ec == null || !ec.getHasMaxPrecision()) {
        return InvalidOperation(EFloat.NaN, ec);
      }
      EInteger ei = EInteger.FromInt32(1).ShiftLeft(ec.getPrecision()).Subtract(1);
      byte[] smaller = EDecimals.FromLogical(ed1, ec, 2);
      if (smaller == null) {
        return InvalidOperation(EFloat.NaN, ec);
      }
      byte[] bigger = ei.ToBytes(true);

      for (int i = 0; i < smaller.length; ++i) {
        bigger[i] ^= smaller[i];
      }
      return EFloat.FromEInteger (
          EDecimals.ToLogical(
            bigger,
            2)).RoundToPrecision(ec);
    }

    /**
     * Performs a logical exclusive-OR (XOR) operation on two binary numbers in the
     * form of <i>logical operands</i>. A <code>logical operand</code> is a
     * non-negative base-2 number with an Exponent property of 0 (examples
     * include the base-2 numbers <code>01001</code> and <code>111001</code>). The
     * logical exclusive-OR operation sets each digit of the result to 1 if
     * either corresponding digit of the logical operands, but not both, is
     * 1, and to 0 otherwise. For example, <code>01001 XOR 111010 =
     * 101010</code>.
     * @param ed1 The first logical operand to the logical exclusive-OR operation.
     * @param ed2 The second logical operand to the logical exclusive-OR operation.
     * @param ec An arithmetic context to control the maximum precision of
     * arbitrary-precision numbers. If a logical operand passed to this
     * method has more bits than the maximum precision specified in this
     * context, the operand's most significant bits that exceed that
     * precision are discarded. This parameter can be null.
     * @return The result of the logical exclusive-OR operation as a logical
     * operand. Signals an invalid operation and returns not-a-number (NaN)
     * if {@code ed1}, {@code ed2}, or both are not logical operands.
     */
    public static EFloat Xor(EFloat ed1, EFloat ed2, EContext ec) {
      byte[] logi1 = EDecimals.FromLogical(ed1, ec, 2);
      if (logi1 == null) {
        return InvalidOperation(EFloat.NaN, ec);
      }
      byte[] logi2 = EDecimals.FromLogical(ed2, ec, 2);
      if (logi2 == null) {
        return InvalidOperation(EFloat.NaN, ec);
      }
      byte[] smaller = logi1.length < logi2.length ? logi1 : logi2;
      byte[] bigger = logi1.length < logi2.length ? logi2 : logi1;
      for (int i = 0; i < smaller.length; ++i) {
        bigger[i] ^= smaller[i];
      }
      return EFloat.FromEInteger (
          EDecimals.ToLogical(
            bigger,
            2)).RoundToPrecision(ec);
    }

    /**
     * Performs a logical OR operation on two binary numbers in the form of
     * <i>logical operands</i>. A <code>logical operand</code> is a non-negative
     * base-2 number with an Exponent property of 0 (examples include the
     * base-2 numbers <code>01001</code> and <code>111001</code>). The logical OR
     * operation sets each bit of the result to 1 if either or both of the
     * corresponding bits of each logical operand are 1, and to 0
     * otherwise. For example, <code>01001 OR 111010 = 111011</code>.
     * @param ed1 The first logical operand to the logical OR operation.
     * @param ed2 The second logical operand to the logical OR operation.
     * @param ec An arithmetic context to control the maximum precision of
     * arbitrary-precision numbers. If a logical operand passed to this
     * method has more bits than the maximum precision specified in this
     * context, the operand's most significant bits that exceed that
     * precision are discarded. This parameter can be null.
     * @return The result of the logical OR operation as a logical operand. Signals
     * an invalid operation and returns not-a-number (NaN) if {@code ed1},
     * {@code ed2}, or both are not logical operands.
     */
    public static EFloat Or(EFloat ed1, EFloat ed2, EContext ec) {
      byte[] logi1 = EDecimals.FromLogical(ed1, ec, 2);
      if (logi1 == null) {
        return InvalidOperation(EFloat.NaN, ec);
      }
      byte[] logi2 = EDecimals.FromLogical(ed2, ec, 2);
      if (logi2 == null) {
        return InvalidOperation(EFloat.NaN, ec);
      }
      byte[] smaller = logi1.length < logi2.length ? logi1 : logi2;
      byte[] bigger = logi1.length < logi2.length ? logi2 : logi1;
      for (int i = 0; i < smaller.length; ++i) {
        bigger[i] |= smaller[i];
      }
      return EFloat.FromEInteger (
          EDecimals.ToLogical(
            bigger,
            2)).RoundToPrecision(ec);
    }
  }
