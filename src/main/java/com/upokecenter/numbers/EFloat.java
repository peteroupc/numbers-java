package com.upokecenter.numbers;
/*
Written by Peter O. in 2013.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

    /**
     * Represents an arbitrary-precision binary floating-point number. (The "E"
     * stands for "extended", meaning that instances of this class can be
     * values other than numbers proper, such as infinity and not-a-number.)
     * Each number consists of an integer mantissa (significand) and an
     * integer exponent, both arbitrary-precision. The value of the number
     * equals mantissa (significand) * 2^exponent. This class also supports
     * values for negative zero, not-a-number (NaN) values, and infinity.
     * <p>Passing a signaling NaN to any arithmetic operation shown here
     * will signal the flag FlagInvalid and return a quiet NaN, even if
     * another operand to that operation is a quiet NaN, unless noted
     * otherwise.</p> <p>Passing a quiet NaN to any arithmetic operation
     * shown here will return a quiet NaN, unless noted otherwise.</p>
     * <p>Unless noted otherwise, passing a null arbitrary-precision binary
     * float argument to any method here will throw an exception.</p>
     * <p>When an arithmetic operation signals the flag FlagInvalid,
     * FlagOverflow, or FlagDivideByZero, it will not throw an exception
     * too, unless the operation's trap is enabled in the precision context
     * (see EContext's Traps property).</p> <p>An arbitrary-precision binary
     * float value can be serialized in one of the following ways:</p> <ul>
     * <li>By calling the toString() method. However, not all strings can be
     * converted back to an arbitrary-precision binary float without loss,
     * especially if the string has a fractional part.</li> <li>By calling
     * the UnsignedMantissa, Exponent, and IsNegative properties, and
     * calling the IsInfinity, IsQuietNaN, and IsSignalingNaN methods. The
     * return values combined will uniquely identify a particular
     * arbitrary-precision binary float value.</li></ul> <p>If an operation
     * requires creating an intermediate value that might be too big to fit
     * in memory (or might require more than 2 gigabytes of memory to store
     * -- due to the current use of a 32-bit integer internally as a
     * length), the operation may signal an invalid-operation flag and
     * return not-a-number (NaN). In certain rare cases, the compareTo
     * method may throw OutOfMemoryError (called OutOfMemoryError in
     * Java) in the same circumstances.</p> <p><b>Thread safety</b></p>
     * <p>Instances of this class are immutable, so they are inherently safe
     * for use by multiple threads. Multiple instances of this object with
     * the same properties are interchangeable, so they should not be
     * compared using the "==" operator (which might only check if each side
     * of the operator is the same instance).</p> <p><b>Comparison
     * considerations</b></p> <p>This class's natural ordering (under the
     * compareTo method) is not consistent with the Equals method. This
     * means that two values that compare as equal under the compareTo
     * method might not be equal under the Equals method. The compareTo
     * method compares the mathematical values of the two instances passed
     * to it (and considers two different NaN values as equal), while two
     * instances with the same mathematical value, but different exponents,
     * will be considered unequal under the Equals method.</p>
     */
  public final class EFloat implements Comparable<EFloat> {
    //----------------------------------------------------------------

    /**
     * A not-a-number value.
     */

    public static final EFloat NaN = CreateWithFlags(
      EInteger.FromInt32(0),
      EInteger.FromInt32(0),
      BigNumberFlags.FlagQuietNaN);

    /**
     * Negative infinity, less than any other number.
     */

    public static final EFloat NegativeInfinity = CreateWithFlags(
      EInteger.FromInt32(0),
      EInteger.FromInt32(0),
      BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);

    /**
     * Represents the number negative zero.
     */

    public static final EFloat NegativeZero = CreateWithFlags(
      EInteger.FromInt32(0),
      EInteger.FromInt32(0),
      BigNumberFlags.FlagNegative);

    /**
     * Represents the number 1.
     */

    public static final EFloat One =
      EFloat.Create(EInteger.FromInt32(1), EInteger.FromInt32(0));

    /**
     * Positive infinity, greater than any other number.
     */

    public static final EFloat PositiveInfinity = CreateWithFlags(
      EInteger.FromInt32(0),
      EInteger.FromInt32(0),
      BigNumberFlags.FlagInfinity);

    /**
     * A not-a-number value that signals an invalid operation flag when it's passed
     * as an argument to any arithmetic operation in arbitrary-precision
     * binary float.
     */

    public static final EFloat SignalingNaN = CreateWithFlags(
      EInteger.FromInt32(0),
      EInteger.FromInt32(0),
      BigNumberFlags.FlagSignalingNaN);

    /**
     * Represents the number 10.
     */

    public static final EFloat Ten =
      EFloat.Create(EInteger.FromInt32(10), EInteger.FromInt32(0));

    /**
     * Represents the number 0.
     */

    public static final EFloat Zero =
      EFloat.Create(EInteger.FromInt32(0), EInteger.FromInt32(0));
    //----------------------------------------------------------------
    private static final IRadixMath<EFloat> MathValue = new
      TrappableRadixMath<EFloat>(
        new ExtendedOrSimpleRadixMath<EFloat>(new BinaryMathHelper()));

    private final EInteger exponent;
    private final int flags;
    private final EInteger unsignedMantissa;

    private EFloat(
      EInteger unsignedMantissa,
      EInteger exponent,
      int flags) {
      this.unsignedMantissa = unsignedMantissa;
      this.exponent = exponent;
      this.flags = flags;
    }

    /**
     *
     */
    public final EInteger getExponent() {
        return this.exponent;
      }

    /**
     *
     */
    public final boolean isFinite() {
        return (this.flags & (BigNumberFlags.FlagInfinity |
                    BigNumberFlags.FlagNaN)) == 0;
      }

    /**
     *
     */
    public final boolean isNegative() {
        return (this.flags & BigNumberFlags.FlagNegative) != 0;
      }

    /**
     *
     */
    public final boolean isZero() {
        return ((this.flags & BigNumberFlags.FlagSpecial) == 0) &&
          this.unsignedMantissa.isZero();
      }

    /**
     *
     */
    public final EInteger getMantissa() {
        return this.isNegative() ? ((this.unsignedMantissa).Negate()) :
          this.unsignedMantissa;
      }

    /**
     *
     */
    public final int signum() {
        return (((this.flags & BigNumberFlags.FlagSpecial) == 0) &&
                this.unsignedMantissa.isZero()) ? 0 :
          (((this.flags & BigNumberFlags.FlagNegative) != 0) ? -1 : 1);
      }

    /**
     *
     */
    public final EInteger getUnsignedMantissa() {
        return this.unsignedMantissa;
      }

    /**
     *
     * @param mantissaSmall Not documented yet.
     * @param exponentSmall Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat Create(int mantissaSmall, int exponentSmall) {
      return Create(EInteger.FromInt32(mantissaSmall), EInteger.FromInt32(exponentSmall));
    }

    /**
     *
     * @param mantissa Not documented yet.
     * @param exponent Not documented yet.
     * @return An EFloat object.
     * @throws NullPointerException The parameter is null.
     */
    public static EFloat Create(
      EInteger mantissa,
      EInteger exponent) {
      if (mantissa == null) {
        throw new NullPointerException("mantissa");
      }
      if (exponent == null) {
        throw new NullPointerException("exponent");
      }
      int sign = mantissa.signum();
      return new EFloat(
        sign < 0 ? ((mantissa).Negate()) : mantissa,
        exponent,
        (sign < 0) ? BigNumberFlags.FlagNegative : 0);
    }

    /**
     *
     * @param diag Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat CreateNaN(EInteger diag) {
      return CreateNaN(diag, false, false, null);
    }

    /**
     *
     * @param diag Not documented yet.
     * @param signaling Not documented yet.
     * @param negative Not documented yet. (3).
     * @param ctx Not documented yet. (4).
     * @return An EFloat object.
     * @throws NullPointerException The parameter is null.
     */
    public static EFloat CreateNaN(
      EInteger diag,
      boolean signaling,
      boolean negative,
      EContext ctx) {
      if (diag == null) {
        throw new NullPointerException("diag");
      }
      if (diag.signum() < 0) {
        throw new
  IllegalArgumentException("Diagnostic information must be 0 or greater, was: " +
                    diag);
      }
      if (diag.isZero() && !negative) {
        return signaling ? SignalingNaN : NaN;
      }
      int flags = 0;
      if (negative) {
        flags |= BigNumberFlags.FlagNegative;
      }
      if (ctx != null && ctx.getHasMaxPrecision()) {
        flags |= BigNumberFlags.FlagQuietNaN;
        EFloat ef = CreateWithFlags(
          diag,
          EInteger.FromInt32(0),
          flags).RoundToPrecision(ctx);
        int newFlags = ef.flags;
        newFlags &= ~BigNumberFlags.FlagQuietNaN;
        newFlags |= signaling ? BigNumberFlags.FlagSignalingNaN :
          BigNumberFlags.FlagQuietNaN;
        return new EFloat(ef.unsignedMantissa, ef.exponent, newFlags);
      }
      flags |= signaling ? BigNumberFlags.FlagSignalingNaN :
        BigNumberFlags.FlagQuietNaN;
      return CreateWithFlags(diag, EInteger.FromInt32(0), flags);
    }

    /**
     *
     * @param dbl Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat FromDouble(double dbl) {
      int[] value = Extras.DoubleToIntegers(dbl);
      int floatExponent = (int)((value[1] >> 20) & 0x7ff);
      boolean neg = (value[1] >> 31) != 0;
      long lvalue;
      if (floatExponent == 2047) {
        if ((value[1] & 0xfffff) == 0 && value[0] == 0) {
          return neg ? NegativeInfinity : PositiveInfinity;
        }
        // Treat high bit of mantissa as quiet/signaling bit
        boolean quiet = (value[1] & 0x80000) != 0;
        value[1] &= 0x7ffff;
        lvalue = ((value[0] & 0xffffffffL) | ((long)value[1] << 32));
        if (lvalue == 0) {
          return quiet ? NaN : SignalingNaN;
        }
        value[0] = (neg ? BigNumberFlags.FlagNegative : 0) |
       (quiet ? BigNumberFlags.FlagQuietNaN : BigNumberFlags.FlagSignalingNaN);
        return CreateWithFlags(
          EInteger.FromInt64(lvalue),
          EInteger.FromInt32(0),
          value[0]);
      }
      value[1] &= 0xfffff;  // Mask out the exponent and sign
      if (floatExponent == 0) {
        ++floatExponent;
      } else {
        value[1] |= 0x100000;
      }
      if ((value[1] | value[0]) != 0) {
        floatExponent += NumberUtility.ShiftAwayTrailingZerosTwoElements(value);
      } else {
        return neg ? EFloat.NegativeZero : EFloat.Zero;
      }
      lvalue = ((value[0] & 0xffffffffL) | ((long)value[1] << 32));
      return CreateWithFlags(
        EInteger.FromInt64(lvalue),
        EInteger.FromInt64(floatExponent - 1075),
        neg ? BigNumberFlags.FlagNegative : 0);
    }

    /**
     *
     * @param bigint Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat FromEInteger(EInteger bigint) {
      return EFloat.Create(bigint, EInteger.FromInt32(0));
    }

    /**
     *
     * @param flt Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat FromSingle(float flt) {
      int value = Float.floatToRawIntBits(flt);
      boolean neg = (value >> 31) != 0;
      int floatExponent = (int)((value >> 23) & 0xff);
      int valueFpMantissa = value & 0x7fffff;
      EInteger bigmant;
      if (floatExponent == 255) {
        if (valueFpMantissa == 0) {
          return neg ? NegativeInfinity : PositiveInfinity;
        }
        // Treat high bit of mantissa as quiet/signaling bit
        boolean quiet = (valueFpMantissa & 0x400000) != 0;
        valueFpMantissa &= 0x3fffff;
        bigmant = EInteger.FromInt32(valueFpMantissa);
        value = (neg ? BigNumberFlags.FlagNegative : 0) | (quiet ?
                BigNumberFlags.FlagQuietNaN : BigNumberFlags.FlagSignalingNaN);
        if (bigmant.isZero()) {
          return quiet ? NaN : SignalingNaN;
        }
        return CreateWithFlags(
          bigmant,
          EInteger.FromInt32(0),
          value);
      }
      if (floatExponent == 0) {
        ++floatExponent;
      } else {
        valueFpMantissa |= 1 << 23;
      }
      if (valueFpMantissa == 0) {
        return neg ? EFloat.NegativeZero : EFloat.Zero;
      }
      while ((valueFpMantissa & 1) == 0) {
        ++floatExponent;
        valueFpMantissa >>= 1;
      }
      if (neg) {
        valueFpMantissa = -valueFpMantissa;
      }
      bigmant = EInteger.FromInt32(valueFpMantissa);
      return EFloat.Create(
        bigmant,
        EInteger.FromInt64(floatExponent - 150));
    }

    /**
     *
     * @param str Not documented yet.
     * @param offset A zero-based index showing where the desired portion of {@code
     * str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @param ctx Not documented yet.
     * @return An EFloat object.
     * @throws NullPointerException The parameter is null.
     * @throws IllegalArgumentException Either "offset" or "length" is less than 0 or
     * greater than "str"'s length, or "str"'s length minus "offset" is less
     * than "length".
     */
    public static EFloat FromString(
      String str,
      int offset,
      int length,
      EContext ctx) {
      if (str == null) {
        throw new NullPointerException("str");
      }
      return EDecimal.FromString(
        str,
        offset,
        length,
        EContext.Unlimited.WithSimplified(ctx != null && ctx.isSimplified()))
        .ToEFloat(ctx);
    }

    /**
     *
     * @param str Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat FromString(String str) {
      return FromString(str, 0, str == null ? 0 : str.length(), null);
    }

    /**
     *
     * @param str Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat FromString(String str, EContext ctx) {
      return FromString(str, 0, str == null ? 0 : str.length(), ctx);
    }

    /**
     *
     * @param str Not documented yet.
     * @param offset A zero-based index showing where the desired portion of {@code
     * str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @return An EFloat object.
     * @throws IllegalArgumentException Either "offset" or "length" is less than 0 or
     * greater than "str"'s length, or "str"'s length minus "offset" is less
     * than "length".
     * @throws NullPointerException The parameter "str" is null.
     */
    public static EFloat FromString(String str, int offset, int length) {
      return FromString(str, offset, length, null);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EFloat object.
     */
    public static EFloat Max(
      EFloat first,
      EFloat second,
      EContext ctx) {
      return MathValue.Max(first, second, ctx);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat Max(
      EFloat first,
      EFloat second) {
      return Max(first, second, null);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EFloat object.
     */
    public static EFloat MaxMagnitude(
      EFloat first,
      EFloat second,
      EContext ctx) {
      return MathValue.MaxMagnitude(first, second, ctx);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat MaxMagnitude(
      EFloat first,
      EFloat second) {
      return MaxMagnitude(first, second, null);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EFloat object.
     */
    public static EFloat Min(
      EFloat first,
      EFloat second,
      EContext ctx) {
      return MathValue.Min(first, second, ctx);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat Min(
      EFloat first,
      EFloat second) {
      return Min(first, second, null);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EFloat object.
     */
    public static EFloat MinMagnitude(
      EFloat first,
      EFloat second,
      EContext ctx) {
      return MathValue.MinMagnitude(first, second, ctx);
    }

    /**
     *
     * @param first Not documented yet.
     * @param second Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat MinMagnitude(
      EFloat first,
      EFloat second) {
      return MinMagnitude(first, second, null);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public static EFloat PI(EContext ctx) {
      return MathValue.Pi(ctx);
    }

    /**
     *
     * @return An EFloat object.
     */
    public EFloat Abs() {
      if (this.isNegative()) {
        EFloat er = new EFloat(
  this.unsignedMantissa,
  this.exponent,
  this.flags & ~BigNumberFlags.FlagNegative);
        return er;
      }
      return this;
    }

    /**
     *
     * @param context Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Abs(EContext context) {
      return MathValue.Abs(this, context);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Add(EFloat otherValue) {
      return this.Add(otherValue, EContext.UnlimitedHalfEven);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Add(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.Add(this, otherValue, ctx);
    }

    /**
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
     */
    public int compareTo(EFloat other) {
      return MathValue.compareTo(this, other);
    }

    /**
     *
     * @param other Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat CompareToSignal(
      EFloat other,
      EContext ctx) {
      return MathValue.CompareToWithContext(this, other, true, ctx);
    }

    /**
     *
     * @param other Not documented yet.
     * @param ctx Not documented yet.
     * @return A 32-bit signed integer.
     */
    public int CompareToTotal(EFloat other, EContext ctx) {
      if (other == null) {
        return -1;
      }
      if (this.IsSignalingNaN() || other.IsSignalingNaN()) {
        return this.CompareToTotal(other);
      }
      if (ctx != null && ctx.isSimplified()) {
        return this.RoundToPrecision(ctx)
          .CompareToTotal(other.RoundToPrecision(ctx));
      } else {
        return this.CompareToTotal(other);
      }
    }

    /**
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
     */
    public int CompareToTotal(EFloat other) {
      if (other == null) {
        return -1;
      }
      boolean neg1 = this.isNegative();
      boolean neg2 = other.isNegative();
      if (neg1 != neg2) {
        return neg1 ? -1 : 1;
      }
      int valueIThis = 0;
      int valueIOther = 0;
      int cmp;
      if (this.IsSignalingNaN()) {
        valueIThis = 2;
      } else if (this.IsNaN()) {
        valueIThis = 3;
      } else if (this.IsInfinity()) {
        valueIThis = 1;
      }
      if (other.IsSignalingNaN()) {
        valueIOther = 2;
      } else if (other.IsNaN()) {
        valueIOther = 3;
      } else if (other.IsInfinity()) {
        valueIOther = 1;
      }
      if (valueIThis > valueIOther) {
        return neg1 ? -1 : 1;
      } else if (valueIThis < valueIOther) {
        return neg1 ? 1 : -1;
      }
      if (valueIThis >= 2) {
        cmp = this.unsignedMantissa.compareTo(
         other.unsignedMantissa);
        return neg1 ? -cmp : cmp;
      } else if (valueIThis == 1) {
        return 0;
      } else {
        cmp = this.compareTo(other);
        if (cmp == 0) {
          cmp = this.exponent.compareTo(
           other.exponent);
          return neg1 ? -cmp : cmp;
        }
        return cmp;
      }
    }

    /**
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
     */
    public int CompareToTotalMagnitude(EFloat other) {
      if (other == null) {
        return -1;
      }
      int valueIThis = 0;
      int valueIOther = 0;
      int cmp;
      if (this.IsSignalingNaN()) {
        valueIThis = 2;
      } else if (this.IsNaN()) {
        valueIThis = 3;
      } else if (this.IsInfinity()) {
        valueIThis = 1;
      }
      if (other.IsSignalingNaN()) {
        valueIOther = 2;
      } else if (other.IsNaN()) {
        valueIOther = 3;
      } else if (other.IsInfinity()) {
        valueIOther = 1;
      }
      if (valueIThis > valueIOther) {
        return 1;
      } else if (valueIThis < valueIOther) {
        return -1;
      }
      if (valueIThis >= 2) {
        cmp = this.unsignedMantissa.compareTo(
         other.unsignedMantissa);
        return cmp;
      } else if (valueIThis == 1) {
        return 0;
      } else {
        cmp = this.Abs().compareTo(other.Abs());
        if (cmp == 0) {
          cmp = this.exponent.compareTo(
           other.exponent);
          return cmp;
        }
        return cmp;
      }
    }

    /**
     *
     * @param other Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat CompareToWithContext(
      EFloat other,
      EContext ctx) {
      return MathValue.CompareToWithContext(this, other, false, ctx);
    }

    /**
     *
     * @param other Not documented yet.
     * @return An EFloat object.
     * @throws NullPointerException The parameter is null.
     */
    public EFloat CopySign(EFloat other) {
      if (other == null) {
        throw new NullPointerException("other");
      }
      if (this.isNegative()) {
        return other.isNegative() ? this : this.Negate();
      } else {
        return other.isNegative() ? this.Negate() : this;
      }
    }

    /**
     *
     * @param divisor Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Divide(EFloat divisor) {
      return this.Divide(
        divisor,
        EContext.ForRounding(ERounding.None));
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Divide(
      EFloat divisor,
      EContext ctx) {
      return MathValue.Divide(this, divisor, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @return An EFloat[] object.
     * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EFloat[] DivideAndRemainderNaturalScale(EFloat
      divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat[] object.
     * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EFloat[] DivideAndRemainderNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return this.DivRemNaturalScale(divisor, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param desiredExponentSmall Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EFloat object.
     */
    public EFloat DivideToExponent(
      EFloat divisor,
      long desiredExponentSmall,
      EContext ctx) {
      return this.DivideToExponent(
        divisor,
        EInteger.FromInt64(desiredExponentSmall),
        ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param desiredExponentSmall Not documented yet.
     * @param rounding Not documented yet. (3).
     * @return An EFloat object.
     */
    public EFloat DivideToExponent(
      EFloat divisor,
      long desiredExponentSmall,
      ERounding rounding) {
      return this.DivideToExponent(
        divisor,
        EInteger.FromInt64(desiredExponentSmall),
        EContext.ForRounding(rounding));
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param exponent Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EFloat object.
     */
    public EFloat DivideToExponent(
      EFloat divisor,
      EInteger exponent,
      EContext ctx) {
      return MathValue.DivideToExponent(this, divisor, exponent, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param desiredExponent Not documented yet.
     * @param rounding Not documented yet. (3).
     * @return An EFloat object.
     */
    public EFloat DivideToExponent(
      EFloat divisor,
      EInteger desiredExponent,
      ERounding rounding) {
      return this.DivideToExponent(
        divisor,
        desiredExponent,
        EContext.ForRounding(rounding));
    }

    /**
     *
     * @param divisor Not documented yet.
     * @return An EFloat object.
     */
    public EFloat DivideToIntegerNaturalScale(
      EFloat divisor) {
      return this.DivideToIntegerNaturalScale(
        divisor,
        EContext.ForRounding(ERounding.Down));
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat DivideToIntegerNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return MathValue.DivideToIntegerNaturalScale(this, divisor, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat DivideToIntegerZeroScale(
      EFloat divisor,
      EContext ctx) {
      return MathValue.DivideToIntegerZeroScale(this, divisor, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param rounding Not documented yet.
     * @return An EFloat object.
     */
    public EFloat DivideToSameExponent(
      EFloat divisor,
      ERounding rounding) {
      return this.DivideToExponent(
        divisor,
        this.exponent,
        EContext.ForRounding(rounding));
    }

    /**
     *
     * @param divisor Not documented yet.
     * @return An EFloat[] object.
     */
    public EFloat[] DivRemNaturalScale(EFloat divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat[] object.
     */
    public EFloat[] DivRemNaturalScale(
      EFloat divisor,
      EContext ctx) {
      EFloat[] result = new EFloat[2];
      result[0] = this.DivideToIntegerNaturalScale(divisor, ctx);
      result[1] = this.Subtract(
        result[0].Multiply(divisor, null),
        null);
      return result;
    }

    /**
     *
     * @param other Not documented yet.
     * @return A Boolean object.
     */
    public boolean equals(EFloat other) {
      return this.EqualsInternal(other);
    }

    /**
     *
     * @param obj Not documented yet.
     * @return A Boolean object.
     */
    @Override public boolean equals(Object obj) {
      return this.EqualsInternal(((obj instanceof EFloat) ? (EFloat)obj : null));
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @return A Boolean object.
     */
    public boolean EqualsInternal(EFloat otherValue) {
      if (otherValue == null) {
        return false;
      }
      return this.exponent.equals(otherValue.exponent) &&
        this.unsignedMantissa.equals(otherValue.unsignedMantissa) &&
        this.flags == otherValue.flags;
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Exp(EContext ctx) {
      return MathValue.Exp(this, ctx);
    }

    /**
     *
     * @return A 32-bit signed integer.
     */
    @Override public int hashCode() {
      int valueHashCode = 403796923;
      {
        valueHashCode += 403797019 * this.exponent.hashCode();
        valueHashCode += 403797059 * this.unsignedMantissa.hashCode();
        valueHashCode += 403797127 * this.flags;
      }
      return valueHashCode;
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsInfinity() {
      return (this.flags & BigNumberFlags.FlagInfinity) != 0;
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsNaN() {
      return (this.flags & (BigNumberFlags.FlagQuietNaN |
                    BigNumberFlags.FlagSignalingNaN)) != 0;
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsNegativeInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
                    BigNumberFlags.FlagNegative)) ==
        (BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsPositiveInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
                BigNumberFlags.FlagNegative)) == BigNumberFlags.FlagInfinity;
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsQuietNaN() {
      return (this.flags & BigNumberFlags.FlagQuietNaN) != 0;
    }

    /**
     *
     * @return A Boolean object.
     */
    public boolean IsSignalingNaN() {
      return (this.flags & BigNumberFlags.FlagSignalingNaN) != 0;
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Log(EContext ctx) {
      return MathValue.Ln(this, ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Log10(EContext ctx) {
      return MathValue.Log10(this, ctx);
    }

    /**
     *
     * @param places Not documented yet.
     * @return An EFloat object.
     */
    public EFloat MovePointLeft(int places) {
      return this.MovePointLeft(EInteger.FromInt32(places), null);
    }

    /**
     *
     * @param places Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat MovePointLeft(int places, EContext ctx) {
      return this.MovePointLeft(EInteger.FromInt32(places), ctx);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @return An EFloat object.
     */
    public EFloat MovePointLeft(EInteger bigPlaces) {
      return this.MovePointLeft(bigPlaces, null);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat MovePointLeft(
  EInteger bigPlaces,
  EContext ctx) {
      return (!this.isFinite()) ? this.RoundToPrecision(ctx) :
        this.MovePointRight((bigPlaces).Negate(), ctx);
    }

    /**
     *
     * @param places Not documented yet.
     * @return An EFloat object.
     */
    public EFloat MovePointRight(int places) {
      return this.MovePointRight(EInteger.FromInt32(places), null);
    }

    /**
     *
     * @param places Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat MovePointRight(int places, EContext ctx) {
      return this.MovePointRight(EInteger.FromInt32(places), ctx);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @return An EFloat object.
     */
    public EFloat MovePointRight(EInteger bigPlaces) {
      return this.MovePointRight(bigPlaces, null);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat MovePointRight(
  EInteger bigPlaces,
  EContext ctx) {
      if (!this.isFinite()) {
        return this.RoundToPrecision(ctx);
      }
      EInteger bigExp = this.getExponent();
      bigExp = bigExp.Add(bigPlaces);
      if (bigExp.signum() > 0) {
        EInteger mant = NumberUtility.ShiftLeft(
          this.unsignedMantissa,
          bigExp);
        return CreateWithFlags(
  mant,
  EInteger.FromInt32(0),
  this.flags).RoundToPrecision(ctx);
      }
      return CreateWithFlags(
        this.unsignedMantissa,
        bigExp,
        this.flags).RoundToPrecision(ctx);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Multiply(EFloat otherValue) {
      if (this.isFinite() && otherValue.isFinite()) {
        EInteger exp = this.exponent.Add(otherValue.exponent);
        int newflags = otherValue.flags ^ this.flags;
        if (this.unsignedMantissa.CanFitInInt32() &&
          otherValue.unsignedMantissa.CanFitInInt32()) {
          int integerA = this.unsignedMantissa.ToInt32Unchecked();
          int integerB = otherValue.unsignedMantissa.ToInt32Unchecked();
          long longA = ((long)integerA) * ((long)integerB);
          return CreateWithFlags(EInteger.FromInt64(longA), exp, newflags);
        } else {
          EInteger eintA = this.unsignedMantissa.Multiply(
           otherValue.unsignedMantissa);
          return CreateWithFlags(eintA, exp, newflags);
        }
      }
      return this.Multiply(otherValue, EContext.UnlimitedHalfEven);
    }

    /**
     *
     * @param op Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Multiply(
      EFloat op,
      EContext ctx) {
      return MathValue.Multiply(this, op, ctx);
    }

    /**
     *
     * @param multiplicand Not documented yet.
     * @param augend Not documented yet.
     * @return An EFloat object.
     */
    public EFloat MultiplyAndAdd(
      EFloat multiplicand,
      EFloat augend) {
      return this.MultiplyAndAdd(multiplicand, augend, null);
    }

    /**
     *
     * @param op Not documented yet.
     * @param augend Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EFloat object.
     */
    public EFloat MultiplyAndAdd(
      EFloat op,
      EFloat augend,
      EContext ctx) {
      return MathValue.MultiplyAndAdd(this, op, augend, ctx);
    }

    /**
     *
     * @param op Not documented yet.
     * @param subtrahend Not documented yet.
     * @param ctx Not documented yet. (3).
     * @return An EFloat object.
     * @throws NullPointerException The parameter is null.
     */
    public EFloat MultiplyAndSubtract(
      EFloat op,
      EFloat subtrahend,
      EContext ctx) {
      if (op == null) {
        throw new NullPointerException("op");
      }
      if (subtrahend == null) {
        throw new NullPointerException("subtrahend");
      }
      EFloat negated = subtrahend;
      if ((subtrahend.flags & BigNumberFlags.FlagNaN) == 0) {
        int newflags = subtrahend.flags ^ BigNumberFlags.FlagNegative;
        negated = CreateWithFlags(
          subtrahend.unsignedMantissa,
          subtrahend.exponent,
          newflags);
      }
      return MathValue.MultiplyAndAdd(this, op, negated, ctx);
    }

    /**
     *
     * @return An EFloat object.
     */
    public EFloat Negate() {
      return new EFloat(
  this.unsignedMantissa,
  this.exponent,
  this.flags ^ BigNumberFlags.FlagNegative);
    }

    /**
     *
     * @param context Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Negate(EContext context) {
      return MathValue.Negate(this, context);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat NextMinus(EContext ctx) {
      return MathValue.NextMinus(this, ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat NextPlus(EContext ctx) {
      return MathValue.NextPlus(this, ctx);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat NextToward(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.NextToward(this, otherValue, ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Plus(EContext ctx) {
      return MathValue.Plus(this, ctx);
    }

    /**
     *
     * @param exponent Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Pow(EFloat exponent, EContext ctx) {
      return MathValue.Power(this, exponent, ctx);
    }

    /**
     *
     * @param exponentSmall Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Pow(int exponentSmall, EContext ctx) {
      return this.Pow(EFloat.FromInt64(exponentSmall), ctx);
    }

    /**
     *
     * @param exponentSmall Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Pow(int exponentSmall) {
      return this.Pow(EFloat.FromInt64(exponentSmall), null);
    }

    /**
     *
     * @return An EInteger object.
     */
    public EInteger Precision() {
      if (!this.isFinite()) {
        return EInteger.FromInt32(0);
      }
      if (this.isZero()) {
        return EInteger.FromInt32(1);
      }
      int bitlen = this.unsignedMantissa.GetSignedBitLength();
      return EInteger.FromInt32(bitlen);
    }

    /**
     *
     * @param desiredExponent Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Quantize(
      EInteger desiredExponent,
      EContext ctx) {
      return this.Quantize(
        EFloat.Create(EInteger.FromInt32(1), desiredExponent),
        ctx);
    }

    /**
     *
     * @param desiredExponentInt Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Quantize(
      int desiredExponentInt,
      EContext ctx) {
      return this.Quantize(
        EFloat.Create(EInteger.FromInt32(1), EInteger.FromInt32(desiredExponentInt)),
        ctx);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Quantize(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.Quantize(this, otherValue, ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Reduce(EContext ctx) {
      return MathValue.Reduce(this, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Remainder(
      EFloat divisor,
      EContext ctx) {
      return MathValue.Remainder(this, divisor, ctx);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @return An EFloat object.
     */
    public EFloat RemainderNaturalScale(
      EFloat divisor) {
      return this.RemainderNaturalScale(divisor, null);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat RemainderNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return this.Subtract(
        this.DivideToIntegerNaturalScale(divisor, ctx).Multiply(divisor, null),
        null);
    }

    /**
     *
     * @param divisor Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat RemainderNear(
      EFloat divisor,
      EContext ctx) {
      return MathValue.RemainderNear(this, divisor, ctx);
    }

    /**
     *
     * @param exponent Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat RoundToExponent(
      EInteger exponent,
      EContext ctx) {
      return MathValue.RoundToExponentSimple(this, exponent, ctx);
    }

    /**
     *
     * @param exponentSmall Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat RoundToExponent(
      int exponentSmall,
      EContext ctx) {
      return this.RoundToExponent(EInteger.FromInt32(exponentSmall), ctx);
    }

    /**
     *
     * @param exponent Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat RoundToExponentExact(
      EInteger exponent,
      EContext ctx) {
      return MathValue.RoundToExponentExact(this, exponent, ctx);
    }

    /**
     *
     * @param exponent Not documented yet.
     * @param rounding Not documented yet.
     * @return An EFloat object.
     */
    public EFloat RoundToExponentExact(
      EInteger exponent,
      ERounding rounding) {
       return MathValue.RoundToExponentExact(
  this,
  exponent,
  EContext.Unlimited.WithRounding(rounding));
    }

    /**
     *
     * @param exponentSmall Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat RoundToExponentExact(
      int exponentSmall,
      EContext ctx) {
      return this.RoundToExponentExact(EInteger.FromInt32(exponentSmall), ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat RoundToIntegerExact(EContext ctx) {
      return MathValue.RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat RoundToIntegerNoRoundedFlag(EContext ctx) {
      return MathValue.RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     * @deprecated Renamed to RoundToIntegerExact.
 */
@Deprecated
    public EFloat RoundToIntegralExact(EContext ctx) {
      return MathValue.RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     * @deprecated Renamed to RoundToIntegerNoRoundedFlag.
 */
@Deprecated
    public EFloat RoundToIntegralNoRoundedFlag(EContext ctx) {
      return MathValue.RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat RoundToPrecision(EContext ctx) {
      return MathValue.RoundToPrecision(this, ctx);
    }

    /**
     *
     * @param places Not documented yet.
     * @return An EFloat object.
     */
    public EFloat ScaleByPowerOfTwo(int places) {
      return this.ScaleByPowerOfTwo(EInteger.FromInt32(places), null);
    }

    /**
     *
     * @param places Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat ScaleByPowerOfTwo(int places, EContext ctx) {
      return this.ScaleByPowerOfTwo(EInteger.FromInt32(places), ctx);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @return An EFloat object.
     */
    public EFloat ScaleByPowerOfTwo(EInteger bigPlaces) {
      return this.ScaleByPowerOfTwo(bigPlaces, null);
    }

    /**
     *
     * @param bigPlaces Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat ScaleByPowerOfTwo(
  EInteger bigPlaces,
  EContext ctx) {
      if (bigPlaces.isZero()) {
        return this.RoundToPrecision(ctx);
      }
      if (!this.isFinite()) {
        return this.RoundToPrecision(ctx);
      }
      EInteger bigExp = this.getExponent();
      bigExp = bigExp.Add(bigPlaces);
      return CreateWithFlags(
        this.unsignedMantissa,
        bigExp,
        this.flags).RoundToPrecision(ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Sqrt(EContext ctx) {
      return MathValue.SquareRoot(this, ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     * @deprecated Renamed to Sqrt.
 */
@Deprecated
    public EFloat SquareRoot(EContext ctx) {
      return MathValue.SquareRoot(this, ctx);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @return An EFloat object.
     */
    public EFloat Subtract(EFloat otherValue) {
      return this.Subtract(otherValue, null);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @param ctx Not documented yet.
     * @return An EFloat object.
     * @throws NullPointerException The parameter is null.
     */
    public EFloat Subtract(
      EFloat otherValue,
      EContext ctx) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      EFloat negated = otherValue;
      if ((otherValue.flags & BigNumberFlags.FlagNaN) == 0) {
        int newflags = otherValue.flags ^ BigNumberFlags.FlagNegative;
        negated = CreateWithFlags(
          otherValue.unsignedMantissa,
          otherValue.exponent,
          newflags);
      }
      return this.Add(negated, ctx);
    }

    /**
     *
     * @return A 64-bit floating-point number.
     */
    public double ToDouble() {
      if (this.IsPositiveInfinity()) {
        return Double.POSITIVE_INFINITY;
      }
      if (this.IsNegativeInfinity()) {
        return Double.NEGATIVE_INFINITY;
      }
      if (this.IsNaN()) {
        int[] nan = { 0, 0x7ff00000 };
        if (this.isNegative()) {
          nan[1] |= ((int)(1 << 31));
        }
        if (this.IsQuietNaN()) {
          nan[1] |= 0x80000;
        } else if (this.getUnsignedMantissa().isZero()) {
          // Set the 0x40000 bit to keep the mantissa from
          // being zero if this is a signaling NaN
          nan[1] |= 0x40000;
        }
        if (!this.getUnsignedMantissa().isZero()) {
          // Copy diagnostic information
          int[] words = FastInteger.GetLastWords(this.getUnsignedMantissa(), 2);
          nan[0] = words[0];
          nan[1] |= words[1] & 0x7ffff;
          if ((words[0] | (words[1] & 0x7ffff)) == 0 && !this.IsQuietNaN()) {
            // Set the 0x40000 bit to keep the mantissa from
            // being zero if this is a signaling NaN
            nan[1] |= 0x40000;
          }
        }
        return Extras.IntegersToDouble(nan);
      }
      EFloat thisValue = this.RoundToPrecision(EContext.Binary64);
      if (!thisValue.isFinite()) {
        return thisValue.ToDouble();
      }
      EInteger mant = thisValue.unsignedMantissa;
      if (thisValue.isNegative() && mant.isZero()) {
        return Extras.IntegersToDouble(new int[] { 0, ((int)(1 << 31)) });
      } else if (mant.isZero()) {
        return 0.0;
      }
      // DebugUtility.Log("-->" + (//
      // thisValue.unsignedMantissa.ToRadixString(2)) + ", " + (//
      // thisValue.exponent));
      int bitLength = mant.GetUnsignedBitLength();

      int expo = thisValue.exponent.ToInt32Checked();
      boolean subnormal = false;
      if (bitLength < 53) {
        int diff = 53 - bitLength;
        expo -= diff;
        if (expo < -1074) {
          // DebugUtility.Log("Diff changed from " + diff + " to " + (diff -
          // (-1074 - expo)));
          diff -= -1074 - expo;
          expo = -1074;
          subnormal = true;
        }
        mant = mant.ShiftLeft(diff);
        bitLength += diff;
      }
      // DebugUtility.Log("2->" + (mant.ToRadixString(2)) + ", " + expo);
      int[] mantissaBits;
      mantissaBits = FastInteger.GetLastWords(mant, 2);
      // Clear the high bits where the exponent and sign are
      mantissaBits[1] &= 0xfffff;
      if (!subnormal) {
        int smallexponent = (expo + 1075) << 20;
        mantissaBits[1] |= smallexponent;
      }
      if (this.isNegative()) {
        mantissaBits[1] |= ((int)(1 << 31));
      }
      return Extras.IntegersToDouble(mantissaBits);
    }

    /**
     *
     * @return An EDecimal object.
     */
    public EDecimal ToEDecimal() {
      return EDecimal.FromEFloat(this);
    }

    /**
     *
     * @return An EInteger object.
     */
    public EInteger ToEInteger() {
      return this.ToEIntegerInternal(false);
    }

    /**
     *
     * @return An EInteger object.
     * @deprecated Renamed to ToEIntegerIfExact.
 */
@Deprecated
    public EInteger ToEIntegerExact() {
      return this.ToEIntegerInternal(true);
    }

    /**
     *
     * @return An EInteger object.
     */
    public EInteger ToEIntegerIfExact() {
      return this.ToEIntegerInternal(true);
    }

    /**
     *
     * @return A string object.
     */
    public String ToEngineeringString() {
      return this.ToEDecimal().ToEngineeringString();
    }

    /**
     *
     * @return An EDecimal object.
     * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal() {
      return EDecimal.FromEFloat(this);
    }

    /**
     *
     * @return A string object.
     */
    public String ToPlainString() {
      return this.ToEDecimal().ToPlainString();
    }

    private String ToDebugString() {
      return "[" + this.getMantissa().ToRadixString(2) +
        "," + this.getMantissa().GetUnsignedBitLength() +
        "," + this.getExponent() + "]";
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return A string object.
     */
    public String ToShortestString(EContext ctx) {
      if (ctx == null || !ctx.getHasMaxPrecision()) {
        return this.toString();
      }
      if (this.IsNaN()) {
        return CreateNaN(
  this.getUnsignedMantissa(),
  this.IsSignalingNaN(),
  this.isNegative(),
  ctx).toString();
      }
      if (this.IsInfinity()) {
        return this.RoundToPrecision(ctx).toString();
      }
      EContext ctx2 = ctx.WithNoFlags();
      EFloat valueEfRnd = this.RoundToPrecision(ctx);
      if (valueEfRnd.IsInfinity()) {
        return valueEfRnd.toString();
      }
      // NOTE: The original EFloat is converted to decimal,
      // not the rounded version, to avoid double rounding issues
      boolean mantissaIsPowerOfTwo = this.unsignedMantissa.isPowerOfTwo();
      EDecimal dec = this.ToEDecimal();
      if (ctx.getPrecision().compareTo(EInteger.FromInt32(10)) >= 0) {
        // Preround the decimal so the significand has closer to the
        // number of decimal digits of the maximum possible
        // decimal significand, to speed up further rounding
        EInteger roundedPrec = ctx.getPrecision().ShiftRight(1).Add(
          EInteger.FromInt32(3));
        dec = dec.RoundToPrecision(
          ctx2.WithRounding(ERounding.Odd).WithBigPrecision(roundedPrec));
      }
      // int precision = dec.getUnsignedMantissa().GetDigitCount();
      EInteger eprecision = EInteger.FromInt32(0);
      while (true) {
        EInteger nextPrecision = eprecision.Add(EInteger.FromInt32(1));
        EContext nextCtx = ctx2.WithBigPrecision(nextPrecision);
        EDecimal nextDec = dec.RoundToPrecision(nextCtx);
        EFloat newFloat = nextDec.ToEFloat(ctx2);
        if (newFloat.compareTo(valueEfRnd) == 0) {
          if (mantissaIsPowerOfTwo) {
            nextPrecision = eprecision;
            nextCtx = ctx2.WithBigPrecision(nextPrecision);
            EDecimal nextDec2 = dec.RoundToPrecision(nextCtx);
            nextDec2 = nextDec2.NextPlus(nextCtx);
            newFloat = nextDec2.ToEFloat(ctx2);
            if (newFloat.compareTo(valueEfRnd) == 0) {
              nextDec = nextDec2;
            }
          }
          return (nextDec.getExponent().signum() > 0 &&
              nextDec.Abs().compareTo(EDecimal.FromInt32(10000000)) < 0) ?
                nextDec.ToPlainString() : nextDec.toString();
        }
        eprecision = nextPrecision;
      }
    }

    /**
     *
     * @return A 32-bit floating-point number.
     */
    public float ToSingle() {
      if (this.IsPositiveInfinity()) {
        return Float.POSITIVE_INFINITY;
      }
      if (this.IsNegativeInfinity()) {
        return Float.NEGATIVE_INFINITY;
      }
      if (this.IsNaN()) {
        int nan = 0x7f800000;
        if (this.isNegative()) {
          nan |= ((int)(1 << 31));
        }
        // IsQuietNaN(): the quiet bit for X86 at least
        // If signaling NaN and mantissa is 0: set 0x200000
        // bit to keep the mantissa from being zero
        if (this.IsQuietNaN()) {
          nan |= 0x400000;
        } else if (this.getUnsignedMantissa().isZero()) {
          nan |= 0x200000;
        }
        if (!this.getUnsignedMantissa().isZero()) {
          // Transfer diagnostic information
          EInteger bigdata = this.getUnsignedMantissa().Remainder(EInteger.FromInt64(0x400000));
          int intData = bigdata.ToInt32Checked();
          nan |= intData;
          if (intData == 0 && !this.IsQuietNaN()) {
            nan |= 0x200000;
          }
        }
        return Float.intBitsToFloat(nan);
      }
      EFloat thisValue = this.RoundToPrecision(EContext.Binary32);
      if (!thisValue.isFinite()) {
        return thisValue.ToSingle();
      }
      EInteger mant = thisValue.unsignedMantissa;
      if (thisValue.isNegative() && mant.isZero()) {
        return Float.intBitsToFloat(1 << 31);
      } else if (mant.isZero()) {
        return 0.0f;
      }
      // DebugUtility.Log("-->" + (//
      // thisValue.unsignedMantissa.ToRadixString(2)) + ", " + (//
      // thisValue.exponent));
      int bitLength = mant.GetUnsignedBitLength();

      int expo = thisValue.exponent.ToInt32Checked();
      boolean subnormal = false;
      if (bitLength < 24) {
        int diff = 24 - bitLength;
        expo -= diff;
        if (expo < -149) {
          // DebugUtility.Log("Diff changed from " + diff + " to " + (diff -
          // (-149 - expo)));
          diff -= -149 - expo;
          expo = -149;
          subnormal = true;
        }
        mant = mant.ShiftLeft(diff);
        bitLength += diff;
      }
      // DebugUtility.Log("2->" + (mant.ToRadixString(2)) + ", " + expo);
      int smallmantissa = ((int)mant.ToInt32Checked()) & 0x7fffff;
      if (!subnormal) {
          smallmantissa |= (expo + 150) << 23;
      }
      if (this.isNegative()) {
          smallmantissa |= 1 << 31;
      }
      return Float.intBitsToFloat(smallmantissa);
    }

    /**
     *
     * @return A string object.
     */
    @Override public String toString() {
      return EDecimal.FromEFloat(this).toString();
    }

    /**
     *
     * @return An EFloat object.
     */
    public EFloat Ulp() {
      return (!this.isFinite()) ? EFloat.One :
        EFloat.Create(EInteger.FromInt32(1), this.exponent);
    }

    static EFloat CreateWithFlags(
      EInteger mantissa,
      EInteger exponent,
      int flags) {
      if (mantissa == null) {
        throw new NullPointerException("mantissa");
      }
      if (exponent == null) {
        throw new NullPointerException("exponent");
      }
      int sign = mantissa == null ? 0 : mantissa.signum();
      return new EFloat(
        sign < 0 ? ((mantissa).Negate()) : mantissa,
        exponent,
        flags);
    }

    private EInteger ToEIntegerInternal(boolean exact) {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      if (this.isZero()) {
        return EInteger.FromInt32(0);
      }
      int expsign = this.getExponent().signum();
      if (expsign == 0) {
        // Integer
        return this.getMantissa();
      }
      if (expsign > 0) {
        // Integer with trailing zeros
        EInteger curexp = this.getExponent();
        EInteger bigmantissa = this.getMantissa();
        if (bigmantissa.isZero()) {
          return bigmantissa;
        }
        boolean neg = bigmantissa.signum() < 0;
        if (neg) {
          bigmantissa = bigmantissa.Negate();
        }
        bigmantissa = NumberUtility.ShiftLeft(bigmantissa, curexp);
        if (neg) {
          bigmantissa = bigmantissa.Negate();
        }
        return bigmantissa;
      } else {
        if (exact && !this.unsignedMantissa.isEven()) {
          // Mantissa is odd and will have to shift a nonzero
          // number of bits, so can't be an exact integer
          throw new ArithmeticException("Not an exact integer");
        }
        FastInteger bigexponent = FastInteger.FromBig(this.getExponent()).Negate();
        EInteger bigmantissa = this.unsignedMantissa;
        BitShiftAccumulator acc = new BitShiftAccumulator(bigmantissa, 0, 0);
        acc.ShiftRight(bigexponent);
        if (exact && (acc.getLastDiscardedDigit() != 0 || acc.getOlderDiscardedDigits() !=
                    0)) {
          // Some digits were discarded
          throw new ArithmeticException("Not an exact integer");
        }
        bigmantissa = acc.getShiftedInt();
        if (this.isNegative()) {
          bigmantissa = bigmantissa.Negate();
        }
        return bigmantissa;
      }
    }

    private static final class BinaryMathHelper implements IRadixMathHelper<EFloat> {
    /**
     *
     * @return A 32-bit signed integer.
     */
      public int GetRadix() {
        return 2;
      }

    /**
     *
     * @param value Not documented yet.
     * @return A 32-bit signed integer.
     */
      public int GetSign(EFloat value) {
        return value.signum();
      }

    /**
     *
     * @param value Not documented yet.
     * @return An EInteger object.
     */
      public EInteger GetMantissa(EFloat value) {
        return value.unsignedMantissa;
      }

    /**
     *
     * @param value Not documented yet.
     * @return An EInteger object.
     */
      public EInteger GetExponent(EFloat value) {
        return value.exponent;
      }

      public FastIntegerFixed GetMantissaFastInt(EFloat value) {
        return FastIntegerFixed.FromBig(value.unsignedMantissa);
      }

      public FastIntegerFixed GetExponentFastInt(EFloat value) {
        return FastIntegerFixed.FromBig(value.exponent);
      }

    /**
     *
     * @param bigint Not documented yet.
     * @param lastDigit Not documented yet.
     * @param olderDigits Not documented yet. (3).
     * @return An IShiftAccumulator object.
     */
      public IShiftAccumulator CreateShiftAccumulatorWithDigits(
        EInteger bigint,
        int lastDigit,
        int olderDigits) {
        return new BitShiftAccumulator(bigint, lastDigit, olderDigits);
      }

      public IShiftAccumulator CreateShiftAccumulatorWithDigitsFastInt(
        FastIntegerFixed fastInt,
        int lastDigit,
        int olderDigits) {
        if (fastInt.CanFitInInt32()) {
     return new BitShiftAccumulator(
  fastInt.AsInt32(),
  lastDigit,
  olderDigits);
        } else {
  return new BitShiftAccumulator(
  fastInt.ToEInteger(),
  lastDigit,
  olderDigits);
        }
      }

    /**
     *
     * @param bigint Not documented yet.
     * @return An IShiftAccumulator object.
     */
      public IShiftAccumulator CreateShiftAccumulator(EInteger bigint) {
        return new BitShiftAccumulator(bigint, 0, 0);
      }

    /**
     *
     * @param num Not documented yet.
     * @param den Not documented yet.
     * @return A FastInteger object.
     */
      public FastInteger DivisionShift(EInteger num, EInteger den) {
        if (den.isZero()) {
          return null;
        }
        if (den.GetUnsignedBit(0) && den.compareTo(EInteger.FromInt32(1)) != 0) {
          return null;
        }
        int lowBit = den.GetLowBit();
        den = den.ShiftRight(lowBit);
        return den.equals(EInteger.FromInt32(1)) ? new FastInteger(lowBit) : null;
      }

    /**
     *
     * @param bigint Not documented yet.
     * @param power Not documented yet.
     * @return An EInteger object.
     */
      public EInteger MultiplyByRadixPower(
        EInteger bigint,
        FastInteger power) {
        EInteger tmpbigint = bigint;
        if (power.signum() <= 0) {
          return tmpbigint;
        }
        if (tmpbigint.signum() < 0) {
          tmpbigint = tmpbigint.Negate();
          if (power.CanFitInInt32()) {
            tmpbigint = NumberUtility.ShiftLeftInt(tmpbigint, power.AsInt32());
            tmpbigint = tmpbigint.Negate();
          } else {
            tmpbigint = NumberUtility.ShiftLeft(
              tmpbigint,
              power.AsEInteger());
            tmpbigint = tmpbigint.Negate();
          }
          return tmpbigint;
        }
        return power.CanFitInInt32() ? NumberUtility.ShiftLeftInt(
          tmpbigint,
          power.AsInt32()) : NumberUtility.ShiftLeft(
          tmpbigint,
          power.AsEInteger());
      }

    /**
     *
     * @param value Not documented yet.
     * @return A 32-bit signed integer.
     */
      public int GetFlags(EFloat value) {
        return value.flags;
      }

    /**
     *
     * @param mantissa Not documented yet.
     * @param exponent Not documented yet.
     * @param flags Not documented yet. (3).
     * @return An EFloat object.
     */
      public EFloat CreateNewWithFlags(
        EInteger mantissa,
        EInteger exponent,
        int flags) {
        return EFloat.CreateWithFlags(mantissa, exponent, flags);
      }

      public EFloat CreateNewWithFlagsFastInt(
        FastIntegerFixed fmantissa,
        FastIntegerFixed fexponent,
        int flags) {
        return CreateWithFlags(
  fmantissa.ToEInteger(),
  fexponent.ToEInteger(),
  flags);
      }

    /**
     *
     * @return A 32-bit signed integer.
     */
      public int GetArithmeticSupport() {
        return BigNumberFlags.FiniteAndNonFinite;
      }

    /**
     *
     * @param val Not documented yet.
     * @return An EFloat object.
     */
      public EFloat ValueOf(int val) {
        return FromInt64(val);
      }
    }
        // Begin integer conversions

    /**
     *
     * @return A Byte object.
     */
public byte ToByteChecked() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
return this.isZero() ? ((byte)0) : this.ToEInteger().ToByteChecked();
}

    /**
     *
     * @return A Byte object.
     */
public byte ToByteUnchecked() {
 return this.isFinite() ? this.ToEInteger().ToByteUnchecked() : (byte)0;
}

    /**
     *
     * @return A Byte object.
     */
public byte ToByteIfExact() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
 return this.isZero() ? ((byte)0) : this.ToEIntegerIfExact().ToByteChecked();
}

    /**
     *
     * @param inputByte Not documented yet.
     * @return An EFloat object.
     */
public static EFloat FromByte(byte inputByte) {
 int val = ((int)inputByte) & 0xff;
 return FromInt32(val);
}

    /**
     *
     * @return A 16-bit signed integer.
     */
public short ToInt16Checked() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
return this.isZero() ? ((short)0) : this.ToEInteger().ToInt16Checked();
}

    /**
     *
     * @return A 16-bit signed integer.
     */
public short ToInt16Unchecked() {
 return this.isFinite() ? this.ToEInteger().ToInt16Unchecked() : (short)0;
}

    /**
     *
     * @return A 16-bit signed integer.
     */
public short ToInt16IfExact() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
 return this.isZero() ? ((short)0) :
   this.ToEIntegerIfExact().ToInt16Checked();
}

    /**
     *
     * @param inputInt16 Not documented yet.
     * @return An EFloat object.
     */
public static EFloat FromInt16(short inputInt16) {
 int val = (int)inputInt16;
 return FromInt32(val);
}

    /**
     *
     * @return A 32-bit signed integer.
     */
public int ToInt32Checked() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
return this.isZero() ? ((int)0) : this.ToEInteger().ToInt32Checked();
}

    /**
     *
     * @return A 32-bit signed integer.
     */
public int ToInt32Unchecked() {
 return this.isFinite() ? this.ToEInteger().ToInt32Unchecked() : (int)0;
}

    /**
     *
     * @return A 32-bit signed integer.
     */
public int ToInt32IfExact() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
 return this.isZero() ? ((int)0) : this.ToEIntegerIfExact().ToInt32Checked();
}

    /**
     *
     * @param inputInt32 Not documented yet.
     * @return An EFloat object.
     */
public static EFloat FromInt32(int inputInt32) {
 return FromEInteger(EInteger.FromInt32(inputInt32));
}

    /**
     *
     * @return A 64-bit signed integer.
     */
public long ToInt64Checked() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
return this.isZero() ? ((long)0) : this.ToEInteger().ToInt64Checked();
}

    /**
     *
     * @return A 64-bit signed integer.
     */
public long ToInt64Unchecked() {
 return this.isFinite() ? this.ToEInteger().ToInt64Unchecked() : (long)0;
}

    /**
     *
     * @return A 64-bit signed integer.
     */
public long ToInt64IfExact() {
 if (!this.isFinite()) {
 throw new ArithmeticException("Value is infinity or NaN");
}
 return this.isZero() ? ((long)0) : this.ToEIntegerIfExact().ToInt64Checked();
}

    /**
     *
     * @param inputInt64 Not documented yet.
     * @return An EFloat object.
     */
public static EFloat FromInt64(long inputInt64) {
 return FromEInteger(EInteger.FromInt64(inputInt64));
}

// End integer conversions
  }
