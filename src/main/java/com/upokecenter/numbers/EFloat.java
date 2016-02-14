package com.upokecenter.numbers;
/*
Written in 2013 by Peter O.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://upokecenter.dreamhosters.com/articles/donate-now-2/
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
     * compared using the "==" operator (which only checks if each side of
     * the operator is the same instance).</p> <p><b>Comparison
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
     * A not-a-number value that signals an invalid operation flag when it&#x27;s
     * passed as an argument to any arithmetic operation in
     * arbitrary-precision binary float.
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

 private static final EInteger ValueOneShift23 =
      EInteger.FromInt32(1).ShiftLeft(23);

 private static final EInteger ValueOneShift52 =
      EInteger.FromInt32(1).ShiftLeft(52);

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
     * Gets this object&#x27;s exponent. This object&#x27;s value will be an
     * integer if the exponent is positive or zero.
     * @return This object's exponent. This object's value will be an integer if
     * the exponent is positive or zero.
     */
    public final EInteger getExponent() {
        return this.exponent;
      }

    /**
     * Gets a value indicating whether this object is finite (not infinity or NaN).
     * @return true if this object is finite (not infinity or not-a-number (NaN));
     * otherwise, false.
     */
    public final boolean isFinite() {
        return (this.flags & (BigNumberFlags.FlagInfinity |
                    BigNumberFlags.FlagNaN)) == 0;
      }

    /**
     * Gets a value indicating whether this object is negative, including negative
     * zero.
     * @return true if this object is negative, including negative zero; otherwise,
     * false.
     */
    public final boolean isNegative() {
        return (this.flags & BigNumberFlags.FlagNegative) != 0;
      }

    /**
     * Gets a value indicating whether this object&#x27;s value equals 0.
     * @return true if this object's value equals 0; otherwise, false.
     */
    public final boolean isZero() {
        return ((this.flags & BigNumberFlags.FlagSpecial) == 0) &&
          this.unsignedMantissa.isZero();
      }

    /**
     * Gets this object&#x27;s unscaled value.
     * @return This object's unscaled value. Will be negative if this object's
     * value is negative (including a negative NaN).
     */
    public final EInteger getMantissa() {
        return this.isNegative() ? ((this.unsignedMantissa).Negate()) :
          this.unsignedMantissa;
      }

    /**
     * Gets this value&#x27;s sign: -1 if negative; 1 if positive; 0 if zero.
     * @return This value's sign: -1 if negative; 1 if positive; 0 if zero.
     */
    public final int signum() {
        return (((this.flags & BigNumberFlags.FlagSpecial) == 0) &&
                this.unsignedMantissa.isZero()) ? 0 :
          (((this.flags & BigNumberFlags.FlagNegative) != 0) ? -1 : 1);
      }

    /**
     * Gets the absolute value of this object&#x27;s unscaled value.
     * @return The absolute value of this object's unscaled value.
     */
    public final EInteger getUnsignedMantissa() {
        return this.unsignedMantissa;
      }

    /**
     * Creates a number with the value exponent*2^mantissa (significand).
     * @return An arbitrary-precision binary float.
     */
    public static EFloat Create(int mantissaSmall, int exponentSmall) {
      return Create(EInteger.FromInt32(mantissaSmall), EInteger.FromInt32(exponentSmall));
    }

    /**
     * Creates a number with the value exponent*2^mantissa (significand).
     * @return An arbitrary-precision binary float.
     * @throws java.lang.NullPointerException The parameter "mantissa (significand)"
     * or {@code exponent} is null.
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
     * Creates a not-a-number arbitrary-precision binary float.
     * @param diag A number to use as diagnostic information associated with this
     * object. If none is needed, should be zero.
     * @return A quiet not-a-number.
     * @throws java.lang.NullPointerException The parameter {@code diag} is null.
     * @throws IllegalArgumentException The parameter {@code diag} is less than 0.
     */
    public static EFloat CreateNaN(EInteger diag) {
      return CreateNaN(diag, false, false, null);
    }

    /**
     * Creates a not-a-number arbitrary-precision binary float.
     * @param diag A number to use as diagnostic information associated with this
     * object. If none is needed, should be zero.
     * @param signaling Whether the return value will be signaling (true) or quiet
     * (false).
     * @param negative Whether the return value is negative.
     * @param ctx An arithmetic context to control the precision (in bits) of the
     * diagnostic information. The rounding and exponent range of this
     * context will be ignored. Can be null. The only flag that can be
     * signaled in this context is FlagInvalid, which happens if diagnostic
     * information needs to be truncated and too much memory is required to
     * do so.
     * @return An arbitrary-precision binary float.
     * @throws java.lang.NullPointerException The parameter {@code diag} is null.
     * @throws IllegalArgumentException The parameter {@code diag} is less than 0.
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
     * Creates a binary float from a 64-bit floating-point number. This method
     * computes the exact value of the floating point number, not an
     * approximation, as is often the case by converting the floating point
     * number to a string first.
     * @param dbl A 64-bit floating-point number.
     * @return A binary float with the same value as {@code dbl}.
     */
    public static EFloat FromDouble(double dbl) {
      int[] value = Extras.DoubleToIntegers(dbl);
      int floatExponent = (int)((value[1] >> 20) & 0x7ff);
      boolean neg = (value[1] >> 31) != 0;
      if (floatExponent == 2047) {
        if ((value[1] & 0xfffff) == 0 && value[0] == 0) {
          return neg ? NegativeInfinity : PositiveInfinity;
        }
        // Treat high bit of mantissa as quiet/signaling bit
        boolean quiet = (value[1] & 0x80000) != 0;
        value[1] &= 0x3ffff;
        EInteger info = FastInteger.WordsToEInteger(value);
        if (info.isZero()) {
          return quiet ? NaN : SignalingNaN;
        }
        value[0] = (neg ? BigNumberFlags.FlagNegative : 0) |
       (quiet ? BigNumberFlags.FlagQuietNaN : BigNumberFlags.FlagSignalingNaN);
        return CreateWithFlags(
          info,
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
      return CreateWithFlags(
        FastInteger.WordsToEInteger(value),
        EInteger.FromInt64(floatExponent - 1075),
        neg ? BigNumberFlags.FlagNegative : 0);
    }

    /**
     * Converts an arbitrary-precision integer to the same value as a binary float.
     * @param bigint An arbitrary-precision integer.
     * @return An arbitrary-precision binary float.
     */
    public static EFloat FromEInteger(EInteger bigint) {
      return EFloat.Create(bigint, EInteger.FromInt32(0));
    }

    /**
     * Creates a binary float from a 32-bit signed integer.
     * @param valueSmaller A 32-bit signed integer.
     * @return An arbitrary-precision binary float with the exponent set to 0.
     */
    public static EFloat FromInt32(int valueSmaller) {
      EInteger bigint = EInteger.FromInt32(valueSmaller);
      return EFloat.Create(bigint, EInteger.FromInt32(0));
    }

    /**
     * Converts a 64-bit integer to the same value as a binary float.
     * @param valueSmall A 64-bit signed integer.
     * @return An arbitrary-precision binary float with the exponent set to 0.
     */
    public static EFloat FromInt64(long valueSmall) {
      EInteger bigint = EInteger.FromInt64(valueSmall);
      return EFloat.Create(bigint, EInteger.FromInt32(0));
    }

    /**
     * Creates a binary float from a 32-bit floating-point number. This method
     * computes the exact value of the floating point number, not an
     * approximation, as is often the case by converting the floating point
     * number to a string first.
     * @param flt A 32-bit floating-point number.
     * @return A binary float with the same value as {@code flt}.
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
        valueFpMantissa &= 0x1fffff;
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
     * Creates a binary float from a text string that represents a number. Note
     * that if the string contains a negative exponent, the resulting value
     * might not be exact, in which case the resulting binary float will be
     * an approximation of this decimal number's value. <p>The format of the
     * string generally consists of:</p> <ul> <li>An optional plus sign ("+"
     * , U+002B) or minus sign ("-", U+002D) (if '-' , the value is
     * negative.)</li> <li>One or more digits, with a single optional
     * decimal point after the first digit and before the last digit.</li>
     * <li>Optionally, "E+"/"e+" (positive exponent) or "E-"/"e-" (negative
     * exponent) plus one or more digits specifying the exponent.</li></ul>
     * <p>The string can also be "-INF", "-Infinity", "Infinity", "INF",
     * quiet NaN ("NaN") followed by any number of digits, or signaling NaN
     * ("sNaN") followed by any number of digits, all in any combination of
     * upper and lower case.</p> <p>All characters mentioned above are the
     * corresponding characters in the Basic Latin range. In particular, the
     * digits must be the basic digits 0 to 9 (U + 0030 to U + 0039). The string
     * is not allowed to contain white space characters, including
     * spaces.</p>
     * @param str A text string.
     * @param offset A zero-based index showing where the desired portion of {@code
     * str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @param ctx An EContext object specifying the precision, rounding, and
     * exponent range (in bits) to apply to the parsed number. Can be null.
     * @return The parsed number, converted to arbitrary-precision binary float.
     * @throws java.lang.NullPointerException The parameter {@code str} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is
     * less than 0 or greater than {@code str} 's length, or {@code str} ' s
     * length minus {@code offset} is less than {@code length}.
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
     * Not documented yet.
     * @param str The parameter {@code str} is not documented yet.
     * @return An EFloat object.
     */
    public static EFloat FromString(String str) {
      return FromString(str, 0, str == null ? 0 : str.length(), null);
    }

    /**
     * Creates a binary float from a text string that represents a number. For more
     * information, see the FromString(String, int, int, EContext) method.
     * @param str A text string.
     * @param ctx An EContext object specifying the precision, rounding, and
     * exponent range to apply to the parsed number. Can be null.
     * @return The parsed number, converted to arbitrary-precision binary float.
     * @throws java.lang.NullPointerException The parameter {@code str} is null.
     */
    public static EFloat FromString(String str, EContext ctx) {
      return FromString(str, 0, str == null ? 0 : str.length(), ctx);
    }

    /**
     * Creates a binary float from a text string that represents a number. For more
     * information, see the <code>FromString(String, int, int, EContext)</code>
     * method.
     * @param str A text string.
     * @param offset A zero-based index showing where the desired portion of {@code
     * str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @return An arbitrary-precision binary float.
     * @throws java.lang.NullPointerException The parameter {@code str} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is
     * less than 0 or greater than {@code str} 's length, or {@code str} ' s
     * length minus {@code offset} is less than {@code length}.
     */
    public static EFloat FromString(String str, int offset, int length) {
      return FromString(str, offset, length, null);
    }

    /**
     * Gets the greater value between two binary floats.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return The larger value of the two numbers.
     */
    public static EFloat Max(
      EFloat first,
      EFloat second,
      EContext ctx) {
      return MathValue.Max(first, second, ctx);
    }

    /**
     * Gets the greater value between two binary floats.
     * @param first An arbitrary-precision binary float.
     * @param second Another arbitrary-precision binary float.
     * @return The larger value of the two numbers.
     */
    public static EFloat Max(
      EFloat first,
      EFloat second) {
      return Max(first, second, null);
    }

    /**
     * Gets the greater value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Max.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return An arbitrary-precision binary float.
     */
    public static EFloat MaxMagnitude(
      EFloat first,
      EFloat second,
      EContext ctx) {
      return MathValue.MaxMagnitude(first, second, ctx);
    }

    /**
     * Gets the greater value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Max.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return An arbitrary-precision binary float.
     */
    public static EFloat MaxMagnitude(
      EFloat first,
      EFloat second) {
      return MaxMagnitude(first, second, null);
    }

    /**
     * Gets the lesser value between two binary floats.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return The smaller value of the two numbers.
     */
    public static EFloat Min(
      EFloat first,
      EFloat second,
      EContext ctx) {
      return MathValue.Min(first, second, ctx);
    }

    /**
     * Gets the lesser value between two binary floats.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return The smaller value of the two numbers.
     */
    public static EFloat Min(
      EFloat first,
      EFloat second) {
      return Min(first, second, null);
    }

    /**
     * Gets the lesser value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Min.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return An arbitrary-precision binary float.
     */
    public static EFloat MinMagnitude(
      EFloat first,
      EFloat second,
      EContext ctx) {
      return MathValue.MinMagnitude(first, second, ctx);
    }

    /**
     * Gets the lesser value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Min.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return An arbitrary-precision binary float.
     */
    public static EFloat MinMagnitude(
      EFloat first,
      EFloat second) {
      return MinMagnitude(first, second, null);
    }

    /**
     * Finds the constant &#x3c0;, the circumference of a circle divided by its
     * diameter.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as &#x3c0; can never be represented exactly.</i>.
     * @return The constant π rounded to the given precision. Signals FlagInvalid
     * and returns not-a-number (NaN) if the parameter {@code ctx} is null
     * or the precision is unlimited (the context's Precision property is
     * 0).
     */
    public static EFloat PI(EContext ctx) {
      return MathValue.Pi(ctx);
    }

    /**
     * Finds the absolute value of this object (if it&#x27;s negative, it becomes
     * positive).
     * @return An arbitrary-precision binary float. Returns signaling NaN if this
     * value is signaling NaN.
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
     * Finds the absolute value of this object (if it&#x27;s negative, it becomes
     * positive).
     * @param context An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The absolute value of this object. Signals FlagInvalid and returns
     * quiet NaN if this value is signaling NaN.
     */
    public EFloat Abs(EContext context) {
      return MathValue.Abs(this, context);
    }

    /**
     * Adds this object and another binary float and returns the result.
     * @param otherValue An arbitrary-precision binary float.
     * @return The sum of the two objects.
     */
    public EFloat Add(EFloat otherValue) {
      return this.Add(otherValue, EContext.UnlimitedHalfEven);
    }

    /**
     * Finds the sum of this object and another object. The result&#x27;s exponent
     * is set to the lower of the exponents of the two operands.
     * @param otherValue The number to add to.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The sum of thisValue and the other object.
     */
    public EFloat Add(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.Add(this, otherValue, ctx);
    }

    /**
     * Compares the mathematical values of this object and another object,
     * accepting NaN values. <p>This method is not consistent with the
     * Equals method because two different numbers with the same
     * mathematical value, but different exponents, will compare as
     * equal.</p> <p>In this method, negative zero and positive zero are
     * considered equal.</p> <p>If this object or the other object is a
     * quiet NaN or signaling NaN, this method will not trigger an error.
     * Instead, NaN will compare greater than any other number, including
     * infinity. Two different NaN values will be considered equal.</p>
     * @param other An arbitrary-precision binary float.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other value
     * or if {@code other} is null, or 0 if both values are equal.
     */
    public int compareTo(EFloat other) {
      return MathValue.compareTo(this, other);
    }

    /**
     * Compares the mathematical values of this object and another object, treating
     * quiet NaN as signaling. <p>In this method, negative zero and positive
     * zero are considered equal.</p> <p>If this object or the other object
     * is a quiet NaN or signaling NaN, this method will return a quiet NaN
     * and will signal a FlagInvalid flag.</p>
     * @param other An arbitrary-precision binary float.
     * @param ctx An arithmetic context. The precision, rounding, and exponent
     * range are ignored. If {@code HasFlags} of the context is true, will
     * store the flags resulting from the operation (the flags are in
     * addition to the pre-existing flags). Can be null.
     * @return Quiet NaN if this object or the other object is NaN, or 0 if both
     * objects have the same value, or -1 if this object is less than the
     * other value, or 1 if this object is greater.
     */
    public EFloat CompareToSignal(
      EFloat other,
      EContext ctx) {
      return MathValue.CompareToWithContext(this, other, true, ctx);
    }

    /**
     * Compares the values of this object and another object, imposing a total
     * ordering on all possible values. In this method: <ul> <li>For objects
     * with the same value, the one with the higher exponent has a greater
     * "absolute value".</li> <li>Negative zero is less than positive
     * zero.</li> <li>Quiet NaN has a higher "absolute value" than signaling
     * NaN. If both objects are quiet NaN or both are signaling NaN, the one
     * with the higher diagnostic information has a greater "absolute
     * value".</li> <li>NaN has a higher "absolute value" than
     * infinity.</li> <li>Infinity has a higher "absolute value" than any
     * finite number.</li> <li>Negative numbers are less than positive
     * numbers.</li></ul>
     * @param other An arbitrary-precision binary float to compare with this one.
     * @param ctx An arithmetic context. Flags will be set in this context only if
     * {@code HasFlags} and {@code IsSimplified} of the context are true and
     * only if an operand needed to be rounded before carrying out the
     * operation. Can be null.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater.
     * Does not signal flags if either value is signaling NaN.
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
     * Compares the values of this object and another object, imposing a total
     * ordering on all possible values. In this method: <ul> <li>For objects
     * with the same value, the one with the higher exponent has a greater
     * "absolute value".</li> <li>Negative zero is less than positive
     * zero.</li> <li>Quiet NaN has a higher "absolute value" than signaling
     * NaN. If both objects are quiet NaN or both are signaling NaN, the one
     * with the higher diagnostic information has a greater "absolute
     * value".</li> <li>NaN has a higher "absolute value" than
     * infinity.</li> <li>Infinity has a higher "absolute value" than any
     * finite number.</li> <li>Negative numbers are less than positive
     * numbers.</li></ul>
     * @param other An arbitrary-precision binary float to compare with this one.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater.
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
     * Compares the absolute values of this object and another object, imposing a
     * total ordering on all possible values (ignoring their signs). In this
     * method: <ul> <li>For objects with the same value, the one with the
     * higher exponent has a greater "absolute value".</li> <li>Negative
     * zero and positive zero are considered equal.</li> <li>Quiet NaN has a
     * higher "absolute value" than signaling NaN. If both objects are quiet
     * NaN or both are signaling NaN, the one with the higher diagnostic
     * information has a greater "absolute value".</li> <li>NaN has a higher
     * "absolute value" than infinity.</li> <li>Infinity has a higher
     * "absolute value" than any finite number.</li></ul>
     * @param other An arbitrary-precision binary float to compare with this one.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater.
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
     * Compares the mathematical values of this object and another object. <p>In
     * this method, negative zero and positive zero are considered
     * equal.</p> <p>If this object or the other object is a quiet NaN or
     * signaling NaN, this method returns a quiet NaN, and will signal a
     * FlagInvalid flag if either is a signaling NaN.</p>
     * @param other An arbitrary-precision binary float.
     * @param ctx An arithmetic context. The precision, rounding, and exponent
     * range are ignored. If {@code HasFlags} of the context is true, will
     * store the flags resulting from the operation (the flags are in
     * addition to the pre-existing flags). Can be null.
     * @return Quiet NaN if this object or the other object is NaN, or 0 if both
     * objects have the same value, or -1 if this object is less than the
     * other value, or 1 if this object is greater.
     */
    public EFloat CompareToWithContext(
      EFloat other,
      EContext ctx) {
      return MathValue.CompareToWithContext(this, other, false, ctx);
    }

    /**
     * Returns a number with the same value as this one, but copying the sign
     * (positive or negative) of another number.
     * @param other A number whose sign will be copied.
     * @return An arbitrary-precision binary float.
     * @throws java.lang.NullPointerException The parameter {@code other} is null.
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
     * Divides this object by another binary float and returns the result. When
     * possible, the result will be exact.
     * @param divisor An arbitrary-precision binary float to divide by.
     * @return The quotient of the two numbers. Returns infinity if the divisor is
     * 0 and the dividend is nonzero. Returns not-a-number (NaN) if the
     * divisor and the dividend are 0. Returns NaN if the result can't be
     * exact because it would have a nonterminating binary expansion.
     */
    public EFloat Divide(EFloat divisor) {
      return this.Divide(
        divisor,
        EContext.ForRounding(ERounding.None));
    }

    /**
     * Divides this arbitrary-precision binary float by another arbitrary-precision
     * binary float. The preferred exponent for the result is this
     * object&#x27;s exponent minus the divisor&#x27;s exponent.
     * @param divisor An arbitrary-precision binary float to divide by.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
     * the dividend are 0; or, either {@code ctx} is null or {@code ctx} 's
     * precision is 0, and the result would have a nonterminating binary
     * expansion; or, the rounding mode is ERounding.None and the result is
     * not exact.
     */
    public EFloat Divide(
      EFloat divisor,
      EContext ctx) {
      return MathValue.Divide(this, divisor, ctx);
    }

    /**
     * Calculates the quotient and remainder using the DivideToIntegerNaturalScale
     * and the formula in RemainderNaturalScale.
     * @param divisor An arbitrary-precision binary float to divide by.
     * @return A 2 element array consisting of the quotient and remainder in that
     * order.
     * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EFloat[] DivideAndRemainderNaturalScale(EFloat
      divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

    /**
     * Calculates the quotient and remainder using the DivideToIntegerNaturalScale
     * and the formula in RemainderNaturalScale.
     * @param divisor An arbitrary-precision binary float to divide by.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only in
     * the division portion of the remainder calculation; as a result, it's
     * possible for the remainder to have a higher precision than given in
     * this context. Flags will be set on the given context only if the
     * context's {@code HasFlags} is true and the integer part of the
     * division result doesn't fit the precision and exponent range without
     * rounding. Can be null, in which the precision is unlimited and no
     * additional rounding, other than the rounding down to an integer after
     * division, is needed.
     * @return A 2 element array consisting of the quotient and remainder in that
     * order.
     * @deprecated Renamed to DivRemNaturalScale.
 */
@Deprecated
    public EFloat[] DivideAndRemainderNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return this.DivRemNaturalScale(divisor, ctx);
    }

    /**
     * Divides two arbitrary-precision binary floats, and gives a particular
     * exponent to the result.
     * @param divisor An arbitrary-precision binary float to divide by.
     * @param desiredExponentSmall The desired exponent. A negative number places
     * the cutoff point to the right of the usual radix point (so a negative
     * number means the number of binary digit places to round to). A
     * positive number places the cutoff point to the left of the usual
     * radix point.
     * @param ctx An arithmetic context object to control the rounding mode to use
     * if the result must be scaled down to have the same exponent as this
     * value. If the precision given in the context is other than 0, calls
     * the Quantize method with both arguments equal to the result of the
     * operation (and can signal FlagInvalid and return NaN if the result
     * doesn't fit the given precision). If {@code HasFlags} of the context
     * is true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
     * the dividend are 0. Signals FlagInvalid and returns not-a-number
     * (NaN) if the context defines an exponent range and the desired
     * exponent is outside that range. Signals FlagInvalid and returns
     * not-a-number (NaN) if the rounding mode is ERounding.None and the
     * result is not exact.
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
     * Divides two arbitrary-precision binary floats, and gives a particular
     * exponent to the result.
     * @param divisor An arbitrary-precision binary float to divide by.
     * @param desiredExponentSmall The desired exponent. A negative number places
     * the cutoff point to the right of the usual radix point (so a negative
     * number means the number of binary digit places to round to). A
     * positive number places the cutoff point to the left of the usual
     * radix point.
     * @param rounding The rounding mode to use if the result must be scaled down
     * to have the same exponent as this value.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
     * the dividend are 0. Signals FlagInvalid and returns not-a-number
     * (NaN) if the rounding mode is ERounding.None and the result is not
     * exact.
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
     * Divides two arbitrary-precision binary floats, and gives a particular
     * exponent to the result.
     * @param divisor An arbitrary-precision binary float to divide by.
     * @param exponent The desired exponent. A negative number places the cutoff
     * point to the right of the usual radix point (so a negative number
     * means the number of binary digit places to round to). A positive
     * number places the cutoff point to the left of the usual radix point.
     * @param ctx An arithmetic context object to control the rounding mode to use
     * if the result must be scaled down to have the same exponent as this
     * value. If the precision given in the context is other than 0, calls
     * the Quantize method with both arguments equal to the result of the
     * operation (and can signal FlagInvalid and return NaN if the result
     * doesn't fit the given precision). If {@code HasFlags} of the context
     * is true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
     * the dividend are 0. Signals FlagInvalid and returns not-a-number
     * (NaN) if the context defines an exponent range and the desired
     * exponent is outside that range. Signals FlagInvalid and returns
     * not-a-number (NaN) if the rounding mode is ERounding.None and the
     * result is not exact.
     */
    public EFloat DivideToExponent(
      EFloat divisor,
      EInteger exponent,
      EContext ctx) {
      return MathValue.DivideToExponent(this, divisor, exponent, ctx);
    }

    /**
     * Divides two arbitrary-precision binary floats, and gives a particular
     * exponent to the result.
     * @param divisor An arbitrary-precision binary float to divide by.
     * @param desiredExponent The desired exponent. A negative number places the
     * cutoff point to the right of the usual radix point (so a negative
     * number means the number of binary digit places to round to). A
     * positive number places the cutoff point to the left of the usual
     * radix point.
     * @param rounding The rounding mode to use if the result must be scaled down
     * to have the same exponent as this value.
     * @return The quotient of the two objects. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Returns not-a-number (NaN) if the divisor and the dividend are 0.
     * Returns NaN if the rounding mode is ERounding.None and the result is
     * not exact.
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
     * Divides two arbitrary-precision binary floats, and returns the integer part
     * of the result, rounded down, with the preferred exponent set to this
     * value&#x27;s exponent minus the divisor&#x27;s exponent.
     * @param divisor An arbitrary-precision binary float to divide by.
     * @return The integer part of the quotient of the two objects. Signals
     * FlagDivideByZero and returns infinity if the divisor is 0 and the
     * dividend is nonzero. Signals FlagInvalid and returns not-a-number
     * (NaN) if the divisor and the dividend are 0.
     */
    public EFloat DivideToIntegerNaturalScale(
      EFloat divisor) {
      return this.DivideToIntegerNaturalScale(
        divisor,
        EContext.ForRounding(ERounding.Down));
    }

    /**
     * Divides this object by another object, and returns the integer part of the
     * result (which is initially rounded down), with the preferred exponent
     * set to this value&#x27;s exponent minus the divisor&#x27;s exponent.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the integer part of the result. Flags will be
     * set on the given context only if the context's {@code HasFlags} is
     * true and the integer part of the result doesn't fit the precision and
     * exponent range without rounding. Can be null, in which the precision
     * is unlimited and no additional rounding, other than the rounding down
     * to an integer after division, is needed.
     * @return The integer part of the quotient of the two objects. Signals
     * FlagInvalid and returns not-a-number (NaN) if the return value would
     * overflow the exponent range. Signals FlagDivideByZero and returns
     * infinity if the divisor is 0 and the dividend is nonzero. Signals
     * FlagInvalid and returns not-a-number (NaN) if the divisor and the
     * dividend are 0. Signals FlagInvalid and returns not-a-number (NaN) if
     * the rounding mode is ERounding.None and the result is not exact.
     */
    public EFloat DivideToIntegerNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return MathValue.DivideToIntegerNaturalScale(this, divisor, ctx);
    }

    /**
     * Divides this object by another object, and returns the integer part of the
     * result, with the exponent set to 0.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision. The
     * rounding and exponent range settings of this context are ignored. If
     * {@code HasFlags} of the context is true, will also store the flags
     * resulting from the operation (the flags are in addition to the
     * pre-existing flags). Can be null, in which case the precision is
     * unlimited.
     * @return The integer part of the quotient of the two objects. The exponent
     * will be set to 0. Signals FlagDivideByZero and returns infinity if
     * the divisor is 0 and the dividend is nonzero. Signals FlagInvalid and
     * returns not-a-number (NaN) if the divisor and the dividend are 0, or
     * if the result doesn't fit the given precision.
     */
    public EFloat DivideToIntegerZeroScale(
      EFloat divisor,
      EContext ctx) {
      return MathValue.DivideToIntegerZeroScale(this, divisor, ctx);
    }

    /**
     * Divides this object by another binary float and returns a result with the
     * same exponent as this object (the dividend).
     * @param divisor The number to divide by.
     * @param rounding The rounding mode to use if the result must be scaled down
     * to have the same exponent as this value.
     * @return The quotient of the two numbers. Signals FlagDivideByZero and
     * returns infinity if the divisor is 0 and the dividend is nonzero.
     * Signals FlagInvalid and returns not-a-number (NaN) if the divisor and
     * the dividend are 0. Signals FlagInvalid and returns not-a-number
     * (NaN) if the rounding mode is ERounding.None and the result is not
     * exact.
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
     * Calculates the quotient and remainder using the DivideToIntegerNaturalScale
     * and the formula in RemainderNaturalScale.
     * @param divisor An arbitrary-precision binary float to divide by.
     * @return A 2 element array consisting of the quotient and remainder in that
     * order.
     */
    public EFloat[] DivRemNaturalScale(EFloat divisor) {
      return this.DivRemNaturalScale(divisor, null);
    }

    /**
     * Calculates the quotient and remainder using the DivideToIntegerNaturalScale
     * and the formula in RemainderNaturalScale.
     * @param divisor An arbitrary-precision binary float to divide by.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only in
     * the division portion of the remainder calculation; as a result, it's
     * possible for the remainder to have a higher precision than given in
     * this context. Flags will be set on the given context only if the
     * context's {@code HasFlags} is true and the integer part of the
     * division result doesn't fit the precision and exponent range without
     * rounding. Can be null, in which the precision is unlimited and no
     * additional rounding, other than the rounding down to an integer after
     * division, is needed.
     * @return A 2 element array consisting of the quotient and remainder in that
     * order.
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
     * Determines whether this object&#x27;s mantissa (significand) and exponent
     * are equal to those of another object.
     * @param other An arbitrary-precision binary float.
     * @return true if this object's mantissa (significand) and exponent are equal
     * to those of another object; otherwise, false .
     */
    public boolean equals(EFloat other) {
      return this.EqualsInternal(other);
    }

    /**
     * Determines whether this object&#x27;s mantissa (significand) and exponent
     * are equal to those of another object and that other object is an
     * arbitrary-precision binary float.
     * @param obj An arbitrary object.
     * @return true if the objects are equal; otherwise, false .
     */
    @Override public boolean equals(Object obj) {
      return this.EqualsInternal(((obj instanceof EFloat) ? (EFloat)obj : null));
    }

    /**
     * Determines whether this object&#x27;s mantissa (significand) and exponent
     * are equal to those of another object.
     * @param otherValue An arbitrary-precision binary float.
     * @return true if this object's mantissa (significand) and exponent are equal
     * to those of another object; otherwise, false .
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
     * Finds e (the base of natural logarithms) raised to the power of this
     * object&#x27;s value.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the exponential function's results are generally
     * not exact.</i> (Unlike in the General Binary Arithmetic
     * Specification, any rounding mode is allowed.).
     * @return Exponential of this object. If this object's value is 1, returns an
     * approximation to " e" within the given precision. Signals FlagInvalid
     * and returns not-a-number (NaN) if the parameter {@code ctx} is null
     * or the precision is unlimited (the context's Precision property is
     * 0).
     */
    public EFloat Exp(EContext ctx) {
      return MathValue.Exp(this, ctx);
    }

    /**
     * Calculates this object&#x27;s hash code.
     * @return This object's hash code.
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
     * Gets a value indicating whether this object is positive or negative
     * infinity.
     * @return true if this object is positive or negative infinity; otherwise,
     * false .
     */
    public boolean IsInfinity() {
      return (this.flags & BigNumberFlags.FlagInfinity) != 0;
    }

    /**
     * Gets a value indicating whether this object is not a number (NaN).
     * @return true if this object is not a number (NaN); otherwise, false .
     */
    public boolean IsNaN() {
      return (this.flags & (BigNumberFlags.FlagQuietNaN |
                    BigNumberFlags.FlagSignalingNaN)) != 0;
    }

    /**
     * Returns whether this object is negative infinity.
     * @return true if this object is negative infinity; otherwise, false .
     */
    public boolean IsNegativeInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
                    BigNumberFlags.FlagNegative)) ==
        (BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);
    }

    /**
     * Returns whether this object is positive infinity.
     * @return true if this object is positive infinity; otherwise, false .
     */
    public boolean IsPositiveInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
                BigNumberFlags.FlagNegative)) == BigNumberFlags.FlagInfinity;
    }

    /**
     * Gets a value indicating whether this object is a quiet not-a-number value.
     * @return true if this object is a quiet not-a-number value; otherwise, false
     * .
     */
    public boolean IsQuietNaN() {
      return (this.flags & BigNumberFlags.FlagQuietNaN) != 0;
    }

    /**
     * Gets a value indicating whether this object is a signaling not-a-number
     * value.
     * @return true if this object is a signaling not-a-number value; otherwise,
     * false .
     */
    public boolean IsSignalingNaN() {
      return (this.flags & BigNumberFlags.FlagSignalingNaN) != 0;
    }

    /**
     * Finds the natural logarithm of this object, that is, the power (exponent)
     * that e (the base of natural logarithms) must be raised to in order to
     * equal this object&#x27;s value.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the ln function's results are generally not
     * exact.</i> (Unlike in the General Binary Arithmetic Specification,
     * any rounding mode is allowed.).
     * @return Ln(this object). Signals the flag FlagInvalid and returns NaN if
     * this object is less than 0 (the result would be a complex number with
     * a real part equal to Ln of this object's absolute value and an
     * imaginary part equal to pi, but the return value is still NaN.).
     * Signals FlagInvalid and returns not-a-number (NaN) if the parameter
     * {@code ctx} is null or the precision is unlimited (the context's
     * Precision property is 0). Signals no flags and returns negative
     * infinity if this object's value is 0.
     */
    public EFloat Log(EContext ctx) {
      return MathValue.Ln(this, ctx);
    }

    /**
     * Finds the base-10 logarithm of this object, that is, the power (exponent)
     * that the number 10 must be raised to in order to equal this
     * object&#x27;s value.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the ln function's results are generally not
     * exact.</i> (Unlike in the General Binary Arithmetic Specification,
     * any rounding mode is allowed.).
     * @return Ln(this object)/Ln(10). Signals the flag FlagInvalid and returns
     * not-a-number (NaN) if this object is less than 0. Signals FlagInvalid
     * and returns not-a-number (NaN) if the parameter {@code ctx} is null
     * or the precision is unlimited (the context's Precision property is
     * 0).
     */
    public EFloat Log10(EContext ctx) {
      return MathValue.Log10(this, ctx);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the left.
     * @param places The number of binary digit places to move the radix point to
     * the left. If this number is negative, instead moves the radix point
     * to the right by this number's absolute value.
     * @return A number whose exponent is decreased by {@code places}, but not to
     * more than 0.
     */
    public EFloat MovePointLeft(int places) {
      return this.MovePointLeft(EInteger.FromInt32(places), null);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the left.
     * @param places The number of binary digit places to move the radix point to
     * the left. If this number is negative, instead moves the radix point
     * to the right by this number's absolute value.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is decreased by {@code places}, but not to
     * more than 0.
     */
    public EFloat MovePointLeft(int places, EContext ctx) {
      return this.MovePointLeft(EInteger.FromInt32(places), ctx);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the left.
     * @param bigPlaces The number of binary digit places to move the radix point
     * to the left. If this number is negative, instead moves the radix
     * point to the right by this number's absolute value.
     * @return A number whose exponent is decreased by {@code bigPlaces}, but not
     * to more than 0.
     */
    public EFloat MovePointLeft(EInteger bigPlaces) {
      return this.MovePointLeft(bigPlaces, null);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the left.
     * @param bigPlaces The number of binary digit places to move the radix point
     * to the left. If this number is negative, instead moves the radix
     * point to the right by this number's absolute value.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is decreased by {@code bigPlaces}, but not
     * to more than 0.
     */
    public EFloat MovePointLeft(
EInteger bigPlaces,
EContext ctx) {
      return (!this.isFinite()) ? this.RoundToPrecision(ctx) :
        this.MovePointRight((bigPlaces).Negate(), ctx);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the right.
     * @param places The number of binary digit places to move the radix point to
     * the right. If this number is negative, instead moves the radix point
     * to the left by this number's absolute value.
     * @return A number whose exponent is increased by {@code places}, but not to
     * more than 0.
     */
    public EFloat MovePointRight(int places) {
      return this.MovePointRight(EInteger.FromInt32(places), null);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the right.
     * @param places The number of binary digit places to move the radix point to
     * the right. If this number is negative, instead moves the radix point
     * to the left by this number's absolute value.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is increased by {@code places}, but not to
     * more than 0.
     */
    public EFloat MovePointRight(int places, EContext ctx) {
      return this.MovePointRight(EInteger.FromInt32(places), ctx);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the right.
     * @param bigPlaces The number of binary digit places to move the radix point
     * to the right. If this number is negative, instead moves the radix
     * point to the left by this number's absolute value.
     * @return A number whose exponent is increased by {@code bigPlaces}, but not
     * to more than 0.
     */
    public EFloat MovePointRight(EInteger bigPlaces) {
      return this.MovePointRight(bigPlaces, null);
    }

    /**
     * Returns a number similar to this number but with the radix point moved to
     * the right.
     * @param bigPlaces The number of binary digit places to move the radix point
     * to the right. If this number is negative, instead moves the radix
     * point to the left by this number's absolute value.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return A number whose exponent is increased by {@code bigPlaces}, but not
     * to more than 0.
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
     * Multiplies two binary floats. The resulting exponent will be the sum of the
     * exponents of the two binary floats.
     * @param otherValue Another binary float.
     * @return The product of the two binary floats.
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
          int sign = (longA == 0) ? 0 : (newflags == 0 ? 1 : -1);
          return CreateWithFlags(EInteger.FromInt64(longA), exp, newflags);
        } else {
          EInteger eintA = this.unsignedMantissa.Multiply(
           otherValue.unsignedMantissa);
          int sign = eintA.isZero() ? 0 : (newflags == 0 ? 1 : -1);
          return CreateWithFlags(eintA, exp, newflags);
        }
      }
      return this.Multiply(otherValue, EContext.UnlimitedHalfEven);
    }

    /**
     * Multiplies two binary floats. The resulting scale will be the sum of the
     * scales of the two binary floats. The result&#x27;s sign is positive
     * if both operands have the same sign, and negative if they have
     * different signs.
     * @param op Another binary float.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return The product of the two binary floats.
     */
    public EFloat Multiply(
      EFloat op,
      EContext ctx) {
      return MathValue.Multiply(this, op, ctx);
    }

    /**
     * Multiplies by one binary float, and then adds another binary float.
     * @param multiplicand The value to multiply.
     * @param augend The value to add.
     * @return The result this * {@code multiplicand} + {@code augend}.
     */
    public EFloat MultiplyAndAdd(
      EFloat multiplicand,
      EFloat augend) {
      return this.MultiplyAndAdd(multiplicand, augend, null);
    }

    /**
     * Multiplies by one value, and then adds another value.
     * @param op The value to multiply.
     * @param augend The value to add.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed. If
     * the precision doesn't indicate a simplified arithmetic, rounding and
     * precision/exponent adjustment is done only once, namely, after
     * multiplying and adding.
     * @return The result thisValue * multiplicand + augend.
     */
    public EFloat MultiplyAndAdd(
      EFloat op,
      EFloat augend,
      EContext ctx) {
      return MathValue.MultiplyAndAdd(this, op, augend, ctx);
    }

    /**
     * Multiplies by one value, and then subtracts another value.
     * @param op The value to multiply.
     * @param subtrahend The value to subtract.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed. If
     * the precision doesn't indicate a simplified arithmetic, rounding and
     * precision/exponent adjustment is done only once, namely, after
     * multiplying and subtracting.
     * @return The result thisValue * multiplicand - subtrahend.
     * @throws java.lang.NullPointerException The parameter {@code op} or {@code
     * subtrahend} is null.
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
     * Gets an object with the same value as this one, but with the sign reversed.
     * @return An arbitrary-precision binary float. If this value is positive zero,
     * returns negative zero. Returns signaling NaN if this value is
     * signaling NaN.
     */
    public EFloat Negate() {
      return new EFloat(
this.unsignedMantissa,
this.exponent,
this.flags ^ BigNumberFlags.FlagNegative);
    }

    /**
     * Returns a binary float with the same value as this object but with the sign
     * reversed.
     * @param context An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return An arbitrary-precision binary float. If this value is positive zero,
     * returns positive zero. Signals FlagInvalid and returns quiet NaN if
     * this value is signaling NaN.
     */
    public EFloat Negate(EContext context) {
      return MathValue.Negate(this, context);
    }

    /**
     * Finds the largest value that&#x27;s smaller than the given value.
     * @param ctx An arithmetic context object to control the precision and
     * exponent range of the result. The rounding mode from this context is
     * ignored. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags).
     * @return Returns the largest value that's less than the given value. Returns
     * negative infinity if the result is negative infinity. Signals
     * FlagInvalid and returns not-a-number (NaN) if the parameter {@code
     * ctx} is null, the precision is 0, or {@code ctx} has an unlimited
     * exponent range.
     */
    public EFloat NextMinus(EContext ctx) {
      return MathValue.NextMinus(this, ctx);
    }

    /**
     * Finds the smallest value that&#x27;s greater than the given value.
     * @param ctx An arithmetic context object to control the precision and
     * exponent range of the result. The rounding mode from this context is
     * ignored. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags).
     * @return Returns the smallest value that's greater than the given
     * value.Signals FlagInvalid and returns not-a-number (NaN) if the
     * parameter {@code ctx} is null, the precision is 0, or {@code ctx} has
     * an unlimited exponent range.
     */
    public EFloat NextPlus(EContext ctx) {
      return MathValue.NextPlus(this, ctx);
    }

    /**
     * Finds the next value that is closer to the other object&#x27;s value than
     * this object&#x27;s value. Returns a copy of this value with the same
     * sign as the other value if both values are equal.
     * @param otherValue An arbitrary-precision binary float that the return value
     * will approach.
     * @param ctx An arithmetic context object to control the precision and
     * exponent range of the result. The rounding mode from this context is
     * ignored. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags).
     * @return Returns the next value that is closer to the other object' s value
     * than this object's value. Signals FlagInvalid and returns NaN if the
     * parameter {@code ctx} is null, the precision is 0, or {@code ctx} has
     * an unlimited exponent range.
     */
    public EFloat NextToward(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.NextToward(this, otherValue, ctx);
    }

    /**
     * Rounds this object&#x27;s value to a given precision, using the given
     * rounding mode and range of exponent, and also converts negative zero
     * to positive zero.
     * @param ctx A context for controlling the precision, rounding mode, and
     * exponent range. Can be null, in which case the precision is unlimited
     * and rounding isn't needed.
     * @return The closest value to this object's value, rounded to the specified
     * precision. Returns the same value as this object if {@code ctx} is
     * null or the precision and exponent range are unlimited.
     */
    public EFloat Plus(EContext ctx) {
      return MathValue.Plus(this, ctx);
    }

    /**
     * Raises this object&#x27;s value to the given exponent.
     * @param exponent An arbitrary-precision binary float expressing the exponent
     * to raise this object's value to.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return This^exponent. Signals the flag FlagInvalid and returns NaN if this
     * object and exponent are both 0; or if this value is less than 0 and
     * the exponent either has a fractional part or is infinity. Signals
     * FlagInvalid and returns not-a-number (NaN) if the parameter {@code
     * ctx} is null or the precision is unlimited (the context's Precision
     * property is 0), and the exponent has a fractional part.
     */
    public EFloat Pow(EFloat exponent, EContext ctx) {
      return MathValue.Power(this, exponent, ctx);
    }

    /**
     * Raises this object&#x27;s value to the given exponent.
     * @param exponentSmall The exponent to raise this object's value to.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return This^exponent. Signals the flag FlagInvalid and returns NaN if this
     * object and exponent are both 0.
     */
    public EFloat Pow(int exponentSmall, EContext ctx) {
      return this.Pow(EFloat.FromInt64(exponentSmall), ctx);
    }

    /**
     * Raises this object&#x27;s value to the given exponent.
     * @param exponentSmall The exponent to raise this object's value to.
     * @return This^exponent. Returns not-a-number (NaN) if this object and
     * exponent are both 0.
     */
    public EFloat Pow(int exponentSmall) {
      return this.Pow(EFloat.FromInt64(exponentSmall), null);
    }

    /**
     * Finds the number of digits in this number's mantissa (significand). Returns
     * 1 if this value is 0, and 0 if this value is infinity or not-a-number
     * (NaN).
     * @return An arbitrary-precision integer.
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
     * Returns a binary float with the same value but a new exponent. <p>Note that
     * this is not always the same as rounding to a given number of binary
     * digit places, since it can fail if the difference between this
     * value's exponent and the desired exponent is too big, depending on
     * the maximum precision. If rounding to a number of binary digit places
     * is desired, it's better to use the RoundToExponent and
     * RoundToIntegral methods instead.</p> <p><b>Remark:</b> This method
     * can be used to implement fixed-point binary arithmetic, in which each
     * binary float has a fixed number of digits after the radix point. The
     * following code example returns a fixed-point number with up to 20
     * digits before and exactly 5 digits after the radix point:</p> <code>
     *  // After performing arithmetic operations, adjust  // the number to 5
     * digits after the radix point number = number.Quantize(
     * EInteger.FromInt32(-5),  // five digits after the radix point
     * EContext.ForPrecision(25)  // 25-digit precision); </code> <p>A
     * fixed-point binary arithmetic in which no digits come after the radix
     * point (a desired exponent of 0) is considered an "integer
     * arithmetic".</p>
     * @param desiredExponent The desired exponent for the result. The exponent is
     * the number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the sixteenth
     * (10b^-3, 0.0001b), and 3 means round to the sixteen-place (10b^3,
     * 1000b). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags). Can be null, in which case the default
     * rounding mode is HalfEven.
     * @return A binary float with the same value as this object but with the
     * exponent changed. Signals FlagInvalid and returns not-a-number (NaN)
     * if this object is infinity, if the rounded result can't fit the given
     * precision, or if the context defines an exponent range and the given
     * exponent is outside that range.
     */
    public EFloat Quantize(
      EInteger desiredExponent,
      EContext ctx) {
      return this.Quantize(
        EFloat.Create(EInteger.FromInt32(1), desiredExponent),
        ctx);
    }

    /**
     * Returns a binary float with the same value but a new exponent. <p>Note that
     * this is not always the same as rounding to a given number of binary
     * digit places, since it can fail if the difference between this
     * value's exponent and the desired exponent is too big, depending on
     * the maximum precision. If rounding to a number of binary digit places
     * is desired, it's better to use the RoundToExponent and
     * RoundToIntegral methods instead.</p> <p><b>Remark:</b> This method
     * can be used to implement fixed-point binary arithmetic, in which each
     * binary float has a fixed number of digits after the radix point. The
     * following code example returns a fixed-point number with up to 20
     * digits before and exactly 5 digits after the radix point:</p> <code>
     *  // After performing arithmetic operations, adjust  // the number to 5
     * digits after the radix point number = number.Quantize(-5,  // five
     * digits after the radix point EContext.ForPrecision(25)  // 25-digit
     * precision); </code> <p>A fixed-point binary arithmetic in which no
     * digits come after the radix point (a desired exponent of 0) is
     * considered an "integer arithmetic".</p>
     * @param desiredExponentInt The desired exponent for the result. The exponent
     * is the number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the sixteenth
     * (10b^-3, 0.0001b), and 3 means round to the sixteen-place (10b^3,
     * 1000b). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags). Can be null, in which case the default
     * rounding mode is HalfEven.
     * @return A binary float with the same value as this object but with the
     * exponent changed. Signals FlagInvalid and returns not-a-number (NaN)
     * if this object is infinity, if the rounded result can't fit the given
     * precision, or if the context defines an exponent range and the given
     * exponent is outside that range.
     */
    public EFloat Quantize(
      int desiredExponentSmall,
      EContext ctx) {
      return this.Quantize(
        EFloat.Create(EInteger.FromInt32(1), EInteger.FromInt32(desiredExponentSmall)),
        ctx);
    }

    /**
     * Returns a binary float with the same value as this object but with the same
     * exponent as another binary float. <p>Note that this is not always the
     * same as rounding to a given number of binary digit places, since it
     * can fail if the difference between this value's exponent and the
     * desired exponent is too big, depending on the maximum precision. If
     * rounding to a number of binary digit places is desired, it's better
     * to use the RoundToExponent and RoundToIntegral methods instead.</p>
     * <p><b>Remark:</b> This method can be used to implement fixed-point
     * binary arithmetic, in which a fixed number of digits come after the
     * radix point. A fixed-point binary arithmetic in which no digits come
     * after the radix point (a desired exponent of 0) is considered an
     * "integer arithmetic".</p>
     * @param otherValue A binary float containing the desired exponent of the
     * result. The mantissa (significand) is ignored. The exponent is the
     * number of fractional digits in the result, expressed as a negative
     * number. Can also be positive, which eliminates lower-order places
     * from the number. For example, -3 means round to the sixteenth
     * (10b^-3, 0.0001b), and 3 means round to the sixteen-place (10b^3,
     * 1000b). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags). Can be null, in which case the default
     * rounding mode is HalfEven.
     * @return A binary float with the same value as this object but with the
     * exponent changed. Signals FlagInvalid and returns not-a-number (NaN)
     * if the result can't fit the given precision without rounding, or if
     * the arithmetic context defines an exponent range and the given
     * exponent is outside that range.
     */
    public EFloat Quantize(
      EFloat otherValue,
      EContext ctx) {
      return MathValue.Quantize(this, otherValue, ctx);
    }

    /**
     * Removes trailing zeros from this object&#x27;s mantissa (significand). For
     * example, 1.00 becomes 1. <p>If this object's value is 0, changes the
     * exponent to 0.</p>
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and rounding isn't needed.
     * @return This value with trailing zeros removed. Note that if the result has
     * a very high exponent and the context says to clamp high exponents,
     * there may still be some trailing zeros in the mantissa (significand).
     */
    public EFloat Reduce(EContext ctx) {
      return MathValue.Reduce(this, ctx);
    }

    /**
     * Finds the remainder that results when dividing two arbitrary-precision
     * binary floats. The remainder is the value that remains when the
     * absolute value of this object is divided by the absolute value of the
     * other object; the remainder has the same sign (positive or negative)
     * as this object's value.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used both in
     * the division portion and in the remainder portion of the remainder
     * calculation. If {@code HasFlags} of the context is true, will also
     * store the flags resulting from the operation (the flags are in
     * addition to the pre-existing flags). Can be null, in which case the
     * precision is unlimited and no additional rounding (other than the
     * rounding from integer division) is needed.
     * @return The remainder of the two numbers. Signals FlagInvalid and returns
     * not-a-number (NaN) if the divisor is 0, or if the result doesn't fit
     * the given precision.
     */
    public EFloat Remainder(
      EFloat divisor,
      EContext ctx) {
      return MathValue.Remainder(this, divisor, ctx);
    }

    /**
     * Calculates the remainder of a number by the formula <code>"this" - (("this" /
     * "divisor") * "divisor")</code>
     * @param divisor The number to divide by.
     * @return An arbitrary-precision binary float.
     */
    public EFloat RemainderNaturalScale(
      EFloat divisor) {
      return this.RemainderNaturalScale(divisor, null);
    }

    /**
     * Calculates the remainder of a number by the formula "this" - (("this" /
     * "divisor") * "divisor").
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only in
     * the division portion of the remainder calculation; as a result, it's
     * possible for the return value to have a higher precision than given
     * in this context. Flags will be set on the given context only if the
     * context's {@code HasFlags} is true and the integer part of the
     * division result doesn't fit the precision and exponent range without
     * rounding. Can be null, in which the precision is unlimited and no
     * additional rounding, other than the rounding down to an integer after
     * division, is needed.
     * @return An arbitrary-precision binary float.
     */
    public EFloat RemainderNaturalScale(
      EFloat divisor,
      EContext ctx) {
      return this.Subtract(
        this.DivideToIntegerNaturalScale(divisor, ctx).Multiply(divisor, null),
        null);
    }

    /**
     * Finds the distance to the closest multiple of the given divisor, based on
     * the result of dividing this object&#x27;s value by another
     * object&#x27;s value. <ul> <li>If this and the other object divide
     * evenly, the result is 0.</li> <li>If the remainder's absolute value
     * is less than half of the divisor's absolute value, the result has the
     * same sign as this object and will be the distance to the closest
     * multiple.</li> <li>If the remainder's absolute value is more than
     * half of the divisor' s absolute value, the result has the opposite
     * sign of this object and will be the distance to the closest
     * multiple.</li> <li>If the remainder's absolute value is exactly half
     * of the divisor's absolute value, the result has the opposite sign of
     * this object if the quotient, rounded down, is odd, and has the same
     * sign as this object if the quotient, rounded down, is even, and the
     * result's absolute value is half of the divisor's absolute
     * value.</li></ul> This function is also known as the "IEEE Remainder"
     * function.
     * @param divisor The number to divide by.
     * @param ctx An arithmetic context object to control the precision. The
     * rounding and exponent range settings of this context are ignored (the
     * rounding mode is always treated as HalfEven). If {@code HasFlags} of
     * the context is true, will also store the flags resulting from the
     * operation (the flags are in addition to the pre-existing flags). Can
     * be null, in which the precision is unlimited.
     * @return The distance of the closest multiple. Signals FlagInvalid and
     * returns not-a-number (NaN) if the divisor is 0, or either the result
     * of integer division (the quotient) or the remainder wouldn't fit the
     * given precision.
     */
    public EFloat RemainderNear(
      EFloat divisor,
      EContext ctx) {
      return MathValue.RemainderNear(this, divisor, ctx);
    }

    /**
     * Returns a binary float with the same value as this object but rounded to a
     * new exponent if necessary.
     * @param exponent The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the sixteenth
     * (10b^-3, 0.0001b), and 3 means round to the sixteen-place (10b^3,
     * 1000b). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary float rounded to the closest value representable in the
     * given precision. If the result can't fit the precision, additional
     * digits are discarded to make it fit. Signals FlagInvalid and returns
     * not-a-number (NaN) if the precision context defines an exponent
     * range, the new exponent must be changed to the given exponent when
     * rounding, and the given exponent is outside of the valid range of the
     * arithmetic context.
     */
    public EFloat RoundToExponent(
      EInteger exponent,
      EContext ctx) {
      return MathValue.RoundToExponentSimple(this, exponent, ctx);
    }

    /**
     * Returns a binary float with the same value as this object but rounded to a
     * new exponent if necessary.
     * @param exponentSmall The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the sixteenth
     * (10b^-3, 0.0001b), and 3 means round to the sixteen-place (10b^3,
     * 1000b). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary float rounded to the closest value representable in the
     * given precision. If the result can't fit the precision, additional
     * digits are discarded to make it fit. Signals FlagInvalid and returns
     * not-a-number (NaN) if the precision context defines an exponent
     * range, the new exponent must be changed to the given exponent when
     * rounding, and the given exponent is outside of the valid range of the
     * arithmetic context.
     */
    public EFloat RoundToExponent(
      int exponentSmall,
      EContext ctx) {
      return this.RoundToExponent(EInteger.FromInt32(exponentSmall), ctx);
    }

    /**
     * Returns a binary float with the same value as this object but rounded to the
     * given exponent, and signals an inexact flag if the result would be
     * inexact.
     * @param exponent The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the sixteenth
     * (10b^-3, 0.0001b), and 3 means round to the sixteen-place (10b^3,
     * 1000b). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary float rounded to the closest value representable in the
     * given precision. Signals FlagInvalid and returns not-a-number (NaN)
     * if the result can't fit the given precision without rounding. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to the
     * given exponent when rounding, and the given exponent is outside of
     * the valid range of the arithmetic context.
     */
    public EFloat RoundToExponentExact(
      EInteger exponent,
      EContext ctx) {
      return MathValue.RoundToExponentExact(this, exponent, ctx);
    }

    /**
     * Returns a binary number with the same value as this object but rounded to
     * the given exponent.
     * @param exponent An EInteger object.
     * @param rounding An ERounding object.
     * @return A binary number rounded to the closest value representable in the
     * given precision.
     */
    public EFloat RoundToExponentExact(
      EInteger exponent,
      ERounding rounding) {
        // TODO: Edit doc for RoundToExponentExact
      return MathValue.RoundToExponentExact(
this,
exponent,
EContext.Unlimited.WithRounding(rounding));
    }

    /**
     * Returns a binary float with the same value as this object but rounded to an
     * integer, and signals an inexact flag if the result would be inexact.
     * @param exponentSmall The minimum exponent the result can have. This is the
     * maximum number of fractional digits in the result, expressed as a
     * negative number. Can also be positive, which eliminates lower-order
     * places from the number. For example, -3 means round to the sixteenth
     * (10b^-3, 0.0001b), and 3 means round to the sixteen-place (10b^3,
     * 1000b). A value of 0 rounds the number to an integer.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary float rounded to the closest value representable in the
     * given precision. Signals FlagInvalid and returns not-a-number (NaN)
     * if the result can't fit the given precision without rounding. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to the
     * given exponent when rounding, and the given exponent is outside of
     * the valid range of the arithmetic context.
     */
    public EFloat RoundToExponentExact(
      int exponentSmall,
      EContext ctx) {
      return this.RoundToExponentExact(EInteger.FromInt32(exponentSmall), ctx);
    }

    /**
     * Returns a binary float with the same value as this object but rounded to an
     * integer, and signals an inexact flag if the result would be inexact.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary float rounded to the closest integer representable in the
     * given precision. Signals FlagInvalid and returns not-a-number (NaN)
     * if the result can't fit the given precision without rounding. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to 0 when
     * rounding, and 0 is outside of the valid range of the arithmetic
     * context.
     */
    public EFloat RoundToIntegerExact(EContext ctx) {
      return MathValue.RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    /**
     * Returns a binary float with the same value as this object but rounded to an
     * integer, without adding the <code>FlagInexact</code> or FlagRounded flags.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags), except that this function will never add the
     * FlagRounded and {@code FlagInexact} flags (the only difference
     * between this and RoundToExponentExact). Can be null, in which case
     * the default rounding mode is HalfEven.
     * @return A binary float rounded to the closest integer representable in the
     * given precision. If the result can't fit the precision, additional
     * digits are discarded to make it fit. Signals FlagInvalid and returns
     * not-a-number (NaN) if the precision context defines an exponent
     * range, the new exponent must be changed to 0 when rounding, and 0 is
     * outside of the valid range of the arithmetic context.
     */
    public EFloat RoundToIntegerNoRoundedFlag(EContext ctx) {
      return MathValue.RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

    /**
     * Returns a binary float with the same value as this object but rounded to an
     * integer, and signals an inexact flag if the result would be inexact.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the default rounding mode is HalfEven.
     * @return A binary float rounded to the closest integer representable in the
     * given precision. Signals FlagInvalid and returns not-a-number (NaN)
     * if the result can't fit the given precision without rounding. Signals
     * FlagInvalid and returns not-a-number (NaN) if the arithmetic context
     * defines an exponent range, the new exponent must be changed to 0 when
     * rounding, and 0 is outside of the valid range of the arithmetic
     * context.
     * @deprecated Renamed to RoundToIntegerExact.
 */
@Deprecated
    public EFloat RoundToIntegralExact(EContext ctx) {
      return MathValue.RoundToExponentExact(this, EInteger.FromInt32(0), ctx);
    }

    /**
     * Returns a binary float with the same value as this object but rounded to an
     * integer, without adding the <code>FlagInexact</code> or FlagRounded flags.
     * @param ctx An arithmetic context to control precision and rounding of the
     * result. If {@code HasFlags} of the context is true, will also store
     * the flags resulting from the operation (the flags are in addition to
     * the pre-existing flags), except that this function will never add the
     * FlagRounded and {@code FlagInexact} flags (the only difference
     * between this and RoundToExponentExact). Can be null, in which case
     * the default rounding mode is HalfEven.
     * @return A binary float rounded to the closest integer representable in the
     * given precision. If the result can't fit the precision, additional
     * digits are discarded to make it fit. Signals FlagInvalid and returns
     * not-a-number (NaN) if the precision context defines an exponent
     * range, the new exponent must be changed to 0 when rounding, and 0 is
     * outside of the valid range of the arithmetic context.
     * @deprecated Renamed to RoundToIntegerNoRoundedFlag.
 */
@Deprecated
    public EFloat RoundToIntegralNoRoundedFlag(EContext ctx) {
      return MathValue.RoundToExponentNoRoundedFlag(this, EInteger.FromInt32(0), ctx);
    }

    /**
     * Rounds this object&#x27;s value to a given precision, using the given
     * rounding mode and range of exponent.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The closest value to this object's value, rounded to the specified
     * precision. Returns the same value as this object if {@code ctx} is
     * null or the precision and exponent range are unlimited.
     */
    public EFloat RoundToPrecision(EContext ctx) {
      return MathValue.RoundToPrecision(this, ctx);
    }

    /**
     * Returns a number similar to this number but with the scale adjusted.
     * @param places A 32-bit signed integer.
     * @return An arbitrary-precision binary float.
     */
    public EFloat ScaleByPowerOfTwo(int places) {
      return this.ScaleByPowerOfTwo(EInteger.FromInt32(places), null);
    }

    /**
     * Returns a number similar to this number but with the scale adjusted.
     * @param places A 32-bit signed integer.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null.
     * @return An arbitrary-precision binary float.
     */
    public EFloat ScaleByPowerOfTwo(int places, EContext ctx) {
      return this.ScaleByPowerOfTwo(EInteger.FromInt32(places), ctx);
    }

    /**
     * Returns a number similar to this number but with the scale adjusted.
     * @param bigPlaces An arbitrary-precision integer.
     * @return A number whose exponent is increased by {@code bigPlaces}.
     */
    public EFloat ScaleByPowerOfTwo(EInteger bigPlaces) {
      return this.ScaleByPowerOfTwo(bigPlaces, null);
    }

    /**
     * Returns a number similar to this number but with its scale adjusted.
     * @param bigPlaces An arbitrary-precision integer.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null.
     * @return A number whose exponent is increased by {@code bigPlaces}.
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
     * Finds the square root of this object&#x27;s value.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the square root function's results are generally
     * not exact for many inputs.</i> (Unlike in the General Binary
     * Arithmetic Specification, any rounding mode is allowed.).
     * @return The square root. Signals the flag FlagInvalid and returns NaN if
     * this object is less than 0 (the square root would be a complex
     * number, but the return value is still NaN). Signals FlagInvalid and
     * returns not-a-number (NaN) if the parameter {@code ctx} is null or
     * the precision is unlimited (the context's Precision property is 0).
     */
    public EFloat Sqrt(EContext ctx) {
      return MathValue.SquareRoot(this, ctx);
    }

    /**
     * Finds the square root of this object&#x27;s value.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). <i>This parameter
     * can't be null, as the square root function's results are generally
     * not exact for many inputs.</i> (Unlike in the General Binary
     * Arithmetic Specification, any rounding mode is allowed.).
     * @return The square root. Signals the flag FlagInvalid and returns NaN if
     * this object is less than 0 (the square root would be a complex
     * number, but the return value is still NaN). Signals FlagInvalid and
     * returns not-a-number (NaN) if the parameter {@code ctx} is null or
     * the precision is unlimited (the context's Precision property is 0).
     * @deprecated Renamed to Sqrt.
 */
@Deprecated
    public EFloat SquareRoot(EContext ctx) {
      return MathValue.SquareRoot(this, ctx);
    }

    /**
     * Subtracts an arbitrary-precision binary float from this instance and returns
     * the result.
     * @param otherValue The number to subtract from this instance's value.
     * @return The difference of the two objects.
     */
    public EFloat Subtract(EFloat otherValue) {
      return this.Subtract(otherValue, null);
    }

    /**
     * Subtracts an arbitrary-precision binary float from this instance.
     * @param otherValue The number to subtract from this instance's value.
     * @param ctx An arithmetic context to control precision, rounding, and
     * exponent range of the result. If {@code HasFlags} of the context is
     * true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case the precision is unlimited and no rounding is needed.
     * @return The difference of the two objects.
     * @throws java.lang.NullPointerException The parameter {@code otherValue} is
     * null.
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
     * Not documented yet.
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
        // 0x40000 is not really the signaling bit, but done to keep
        // the mantissa from being zero
        if (this.IsQuietNaN()) {
          nan[1] |= 0x80000;
        } else {
          nan[1] |= 0x40000;
        }
        if (!this.getUnsignedMantissa().isZero()) {
          // Copy diagnostic information
          int[] words = FastInteger.GetLastWords(this.getUnsignedMantissa(), 2);
          nan[0] = words[0];
          nan[1] = words[1] & 0x3ffff;
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
     * Converts this value to an arbitrary-precision decimal number.
     * @return An arbitrary-precision decimal number.
     */
    public EDecimal ToEDecimal() {
      return EDecimal.FromEFloat(this);
    }

    /**
     * Converts this value to an arbitrary-precision integer. Any fractional part
     * of this value will be discarded when converting to an
     * arbitrary-precision integer.
     * @return An arbitrary-precision integer.
     * @throws java.lang.ArithmeticException This object's value is infinity or
     * not-a-number (NaN).
     */
    public EInteger ToEInteger() {
      return this.ToEIntegerInternal(false);
    }

    /**
     * Converts this value to an arbitrary-precision integer, checking whether the
     * value contains a fractional part.
     * @return An arbitrary-precision integer.
     * @throws java.lang.ArithmeticException This object's value is infinity or
     * not-a-number (NaN).
     * @throws ArithmeticException This object's value is not an exact integer.
     */
    public EInteger ToEIntegerExact() {
      return this.ToEIntegerInternal(true);
    }

    /**
     * Converts this value to an arbitrary-precision decimal number, then returns
     * the value of that decimal's ToEngineeringString method.
     * @return A text string.
     */
    public String ToEngineeringString() {
      return this.ToEDecimal().ToEngineeringString();
    }

    /**
     * Converts this value to an arbitrary-precision decimal number.
     * @return An arbitrary-precision decimal number.
     * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal() {
      return EDecimal.FromEFloat(this);
    }

    /**
     * Converts this value to a 32-bit signed integer, throwing an exception if the
     * value can't fit.
     * @return A 32-bit signed integer.
     */
    public int ToInt32Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      if (this.unsignedMantissa.isZero()) {
        return 0;
      }
      if (this.exponent.isZero()) {
        if (this.unsignedMantissa.CanFitInInt32()) {
          int ret = this.unsignedMantissa.ToInt32Unchecked();
          if (this.isNegative()) {
            ret = -ret;
          }
          return ret;
        }
      }
      return this.ToEIntegerExact().ToInt32Checked();
    }

    /**
     * Converts this number to a 32-bit signed integer. If this value is not
     * exactly a 32-bit integer, truncates the value to an integer and
     * returns the lowest 32 bits of its two's-complement form (see <see
     * cref='T:PeterO.Numbers.EDecimal'>"Forms of numbers"</see>) (in which
     * case the return value might have a different sign than this object's
     * value).
     * @return A 32-bit signed integer. Returns 0 if this value is infinity or
     * not-a-number.
     */
    public int ToInt32Unchecked() {
      if (!this.isFinite()) {
        return 0;
      }
      if (this.unsignedMantissa.isZero()) {
        return 0;
      }
      if (this.exponent.isZero()) {
        if (this.unsignedMantissa.CanFitInInt32()) {
          int ret = this.unsignedMantissa.ToInt32Unchecked();
          if (this.isNegative()) {
            ret = -ret;
          }
          return ret;
        }
      }
      return this.ToEIntegerExact().ToInt32Unchecked();
    }

    /**
     * Converts this value to a 64-bit signed integer, throwing an exception if the
     * value can't fit.
     * @return A 64-bit signed integer.
     */
    public long ToInt64Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      if (this.unsignedMantissa.isZero()) {
        return 0;
      }
      if (this.exponent.isZero()) {
        if (this.unsignedMantissa.CanFitInInt32()) {
          int ret = this.unsignedMantissa.ToInt32Unchecked();
          if (this.isNegative()) {
            ret = -ret;
          }
          return ret;
        }
      }
      return this.ToEIntegerExact().ToInt64Checked();
    }

    /**
     * Converts this number to a 64-bit signed integer. If this value is not
     * exactly a 64-bit integer, truncates the value to an integer and
     * returns the lowest 64 bits of its two's-complement form (see <see
     * cref='T:PeterO.Numbers.EDecimal'>"Forms of numbers"</see>) (in which
     * case the return value might have a different sign than this object's
     * value).
     * @return A 64-bit signed integer. Returns 0 if this value is infinity or
     * not-a-number.
     */
    public long ToInt64Unchecked() {
      if (!this.isFinite()) {
        return 0;
      }
      if (this.unsignedMantissa.isZero()) {
        return 0;
      }
      if (this.isFinite() && this.exponent.isZero()) {
        if (this.unsignedMantissa.CanFitInInt32()) {
          int ret = this.unsignedMantissa.ToInt32Unchecked();
          if (this.isNegative()) {
            ret = -ret;
          }
          return ret;
        }
      }
      return this.ToEIntegerExact().ToInt64Unchecked();
    }

    /**
     * Converts this value to a string, but without exponential notation.
     * @return A text string.
     */
    public String ToPlainString() {
      return this.ToEDecimal().ToPlainString();
    }

    private String ToDebugString() {
      return "[" + this.getMantissa().ToRadixString(2) +"," +
        this.getMantissa().GetUnsignedBitLength() +"," + this.getExponent() + "]";
    }

    /**
     * Returns a string representation of this number's value after rounding to the
     * given precision. If the number after rounding is neither infinity nor
     * not-a-number (NaN), returns the shortest decimal form (in terms of
     * nonzero decimal digits) of this number's value that results in the
     * rounded number after the decimal form is converted to binary
     * floating-point format.
     * @param ctx An arithmetic context to control precision (in bits), rounding,
     * and exponent range of the rounded number. If {@code HasFlags} of the
     * context is true, will also store the flags resulting from the
     * operation (the flags are in addition to the pre-existing flags). Can
     * be null. If this parameter is null or defines no maximum precision,
     * returns the same value as the toString() method.
     * @return Shortest decimal form of this number's value for the given
     * arithmetic context. The text string will be in exponential notation
     * if the number's first nonzero decimal digit is more than five digits
     * after the decimal point, or if the number's exponent is greater than
     * 0 and its value is 10, 000, 000 or greater.
     */
    public String ToShortestString(EContext ctx) {
      if (ctx == null || !ctx.getHasMaxPrecision()) {
        return this.toString();
      }
      if (!this.isFinite()) {
        return this.ToEDecimal().ToEFloat(ctx).toString();
      }
      EContext ctx2 = ctx.WithNoFlags();
      EFloat valueEfRnd = this.RoundToPrecision(ctx);
      if (valueEfRnd.IsInfinity()) {
        return valueEfRnd.toString();
      }
      // NOTE: The original EFloat is converted to decimal,
      // not the rounded version, to avoid double rounding issues
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
      int precision = dec.getUnsignedMantissa().GetDigitCount();
      EInteger eprecision = EInteger.FromInt32(0);
      while (true) {
        EInteger nextPrecision = eprecision.Add(EInteger.FromInt32(1));
        EContext nextCtx = ctx2.WithBigPrecision(nextPrecision);
        EDecimal nextDec = dec.RoundToPrecision(nextCtx);
        EFloat newFloat = nextDec.ToEFloat(ctx2);
        if (newFloat.compareTo(valueEfRnd) == 0) {
          return (nextDec.getExponent().signum() > 0 &&
              nextDec.Abs().compareTo(EDecimal.FromInt32(10000000)) < 0) ?
                nextDec.ToPlainString() : nextDec.toString();
        }
        eprecision = nextPrecision;
      }
    }

    /**
     * Converts this value to its closest equivalent as 32-bit floating-point
     * number. The half-even rounding mode is used. <p>If this value is a
     * NaN, sets the high bit of the 32-bit floating point number's mantissa
     * (significand) for a quiet NaN, and clears it for a signaling NaN.
     * Then the next highest bit of the mantissa (significand) is cleared
     * for a quiet NaN, and set for a signaling NaN. Then the other bits of
     * the mantissa (significand) are set to the lowest bits of this
     * object's unsigned mantissa (significand).</p>
     * @return The closest 32-bit floating-point number to this value. The return
     * value can be positive infinity or negative infinity if this value
     * exceeds the range of a 32-bit floating point number.
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
        // Not IsQuietNaN(): not really the signaling bit, but done to keep
        // the mantissa from being zero
        nan |= this.IsQuietNaN() ? 0x400000 : 0x200000;
        if (!this.getUnsignedMantissa().isZero()) {
          // Transfer diagnostic information
          EInteger bigdata = this.getUnsignedMantissa().Remainder(EInteger.FromInt64(0x200000));
          nan |= bigdata.AsInt32Checked();
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
     * Converts this number's value to a text string.
     * @return A string representation of this object. The value is converted to
     * decimal and the decimal form of this number's value is returned. The
     * text string will be in exponential notation if the converted number's
     * scale is positive or if the number's first nonzero decimal digit is
     * more than five digits after the decimal point.
     */
    @Override public String toString() {
      return EDecimal.FromEFloat(this).toString();
    }

    /**
     * Returns the unit in the last place. The mantissa (significand) will be 1 and
     * the exponent will be this number's exponent. Returns 1 with an
     * exponent of 0 if this number is infinity or not-a-number (NaN).
     * @return An arbitrary-precision binary float.
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
        EInteger bigmantissa = this.getMantissa();
        FastInteger bigexponent = FastInteger.FromBig(this.getExponent()).Negate();
        bigmantissa = bigmantissa.Abs();
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
     * This is an internal method.
     * @return A 32-bit signed integer.
     */
      public int GetRadix() {
        return 2;
      }

    /**
     * This is an internal method.
     * @param value An arbitrary-precision binary float.
     * @return A 32-bit signed integer.
     */
      public int GetSign(EFloat value) {
        return value.signum();
      }

    /**
     * This is an internal method.
     * @param value An arbitrary-precision binary float.
     * @return An arbitrary-precision integer.
     */
      public EInteger GetMantissa(EFloat value) {
        return value.unsignedMantissa;
      }

    /**
     * This is an internal method.
     * @param value An arbitrary-precision binary float.
     * @return An arbitrary-precision integer.
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
     * This is an internal method.
     * @param bigint An arbitrary-precision integer.
     * @param lastDigit A 32-bit signed integer.
     * @param olderDigits A 32-bit signed integer. (2).
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
fastInt.AsEInteger(),
lastDigit,
olderDigits);
        }
      }

    /**
     * This is an internal method.
     * @param bigint An arbitrary-precision integer.
     * @return An IShiftAccumulator object.
     */
      public IShiftAccumulator CreateShiftAccumulator(EInteger bigint) {
        return new BitShiftAccumulator(bigint, 0, 0);
      }

    /**
     * This is an internal method.
     * @param num An arbitrary-precision integer.
     * @param den Another arbitrary-precision integer.
     * @return A Boolean object.
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
     * This is an internal method.
     * @param bigint Another arbitrary-precision integer.
     * @param power A fast integer.
     * @return An arbitrary-precision integer.
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
     * This is an internal method.
     * @param value An arbitrary-precision binary float.
     * @return A 32-bit signed integer.
     */
      public int GetFlags(EFloat value) {
        return value.flags;
      }

    /**
     * This is an internal method.
     * @return An arbitrary-precision binary float.
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
fmantissa.AsEInteger(),
fexponent.AsEInteger(),
flags);
      }

    /**
     * This is an internal method.
     * @return A 32-bit signed integer.
     */
      public int GetArithmeticSupport() {
        return BigNumberFlags.FiniteAndNonFinite;
      }

    /**
     * This is an internal method.
     * @param val A 32-bit signed integer.
     * @return An arbitrary-precision binary float.
     */
      public EFloat ValueOf(int val) {
        return FromInt64(val);
      }
    }
  }
