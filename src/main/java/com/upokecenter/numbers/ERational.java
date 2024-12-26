package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under the Unlicense: https://unlicense.org/

 */

  /**
   * <p>Represents an arbitrary-precision rational number. This class can't be
   * inherited. (The "E" stands for "extended", meaning that instances of this
   * class can be values other than numbers proper, such as infinity and
   * not-a-number.) In this class, a rational number consists of a numerator and
   * denominator, each an arbitrary-precision integer (EInteger), and this class
   * does not automatically convert rational numbers to lowest terms. </p>
   * <p><b>Thread safety:</b> Instances of this class are immutable, so they are
   * inherently safe for use by multiple threads. Multiple instances of this
   * object with the same properties are interchangeable, so they should not be
   * compared using the "==" operator (which might only check if each side of the
   * operator is the same instance).</p>
   */

  public final class ERational implements Comparable<ERational> {
    /**
     * A not-a-number value.
     */

    public static final ERational NaN = new ERational(
      FastIntegerFixed.Zero,
      FastIntegerFixed.One,
      (byte)BigNumberFlags.FlagQuietNaN);

    /**
     * Negative infinity, less than any other number.
     */

    public static final ERational NegativeInfinity =
      new ERational(
        FastIntegerFixed.Zero,
        FastIntegerFixed.One,
        (byte)(BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative));

    /**
     * A rational number for negative zero.
     */

    public static final ERational NegativeZero =
      new ERational(
          FastIntegerFixed.Zero,
          FastIntegerFixed.One,
          (byte)BigNumberFlags.FlagNegative);

    /**
     * The rational number one.
     */

    public static final ERational One = FromEInteger(EInteger.FromInt32(1));

    /**
     * Positive infinity, greater than any other number.
     */

    public static final ERational PositiveInfinity =
      new ERational(
        FastIntegerFixed.Zero,
        FastIntegerFixed.One,
        (byte)BigNumberFlags.FlagInfinity);

    /**
     * A signaling not-a-number value.
     */

    public static final ERational SignalingNaN =
      new ERational(
        FastIntegerFixed.Zero,
        FastIntegerFixed.One,
        (byte)BigNumberFlags.FlagSignalingNaN);

    /**
     * The rational number ten.
     */

    public static final ERational Ten = FromEInteger(EInteger.FromInt32(10));

    /**
     * A rational number for zero.
     */

    public static final ERational Zero = FromEInteger(EInteger.FromInt32(0));

    private final FastIntegerFixed denominator;

    private final byte flags;
    private final FastIntegerFixed unsignedNumerator;

    private ERational(
      FastIntegerFixed numerator,
      FastIntegerFixed denominator,
      byte flags) {
      this.unsignedNumerator = numerator;
      this.denominator = denominator;
      this.flags = flags;
    }

    /**
     * Initializes a new instance of the {@link com.upokecenter.numbers.ERational}
     * class.
     * @param numerator An arbitrary-precision integer serving as the numerator.
     * @param denominator An arbitrary-precision integer serving as the
     * denominator.
     * @throws NullPointerException The parameter {@code numerator} or {@code
     * denominator} is null.
     * @throws IllegalArgumentException Denominator is zero.
     * @deprecated Use the Create method instead.
 */
@Deprecated
    public ERational(EInteger numerator, EInteger denominator) {
      if (numerator == null) {
        throw new NullPointerException("numerator");
      }
      if (denominator == null) {
        throw new NullPointerException("denominator");
      }
      if (denominator.isZero()) {
        throw new IllegalArgumentException("denominator is zero");
      }
      boolean numNegative = numerator.signum() < 0;
      boolean denNegative = denominator.signum() < 0;
      this.flags = (byte)((numNegative != denNegative) ?
        BigNumberFlags.FlagNegative : 0);
      if (numNegative) {
        numerator = numerator.Negate();
      }
      if (denNegative) {
        denominator = denominator.Negate();
      }
      this.unsignedNumerator = FastIntegerFixed.FromBig(numerator);
      this.denominator = FastIntegerFixed.FromBig(denominator);
    }

    /**
     * Creates a copy of this arbitrary-precision rational number.
     * @return An arbitrary-precision rational number.
     */
    public ERational Copy() {
      return new ERational(
          this.unsignedNumerator,
          this.denominator,
          this.flags);
    }

    /**
     * Gets this object's denominator.
     * @return This object's denominator.
     */
    public final EInteger getDenominator() {
        return this.denominator.ToEInteger();
      }

    /**
     * Gets a value indicating whether this object is finite (not infinity or NaN).
     * @return {@code true} if this object is finite (not infinity or NaN);
     * otherwise, {@code false}.
     */
    public final boolean isFinite() {
        return !this.IsNaN() && !this.IsInfinity();
      }

    /**
     * Gets a value indicating whether this object's value is negative (including
     * negative zero).
     * @return {@code true} if this object's value is negative (including negative
     * zero); otherwise, {@code false}. {@code true} if this object's value is
     * negative; otherwise, {@code false}.
     */
    public final boolean isNegative() {
        return (this.flags & BigNumberFlags.FlagNegative) != 0;
      }

    /**
     * Gets a value indicating whether this object's value equals 0.
     * @return {@code true} if this object's value equals 0; otherwise, {@code
     * false}. {@code true} if this object's value equals 0; otherwise, {@code
     * false}.
     */
    public final boolean isZero() {
        return ((this.flags & (BigNumberFlags.FlagInfinity |
                BigNumberFlags.FlagNaN)) == 0) &&
this.unsignedNumerator.isValueZero();
      }

    /**
     * Returns whether this object's value is an integer.
     * @return {@code true} if this object's value is an integer; otherwise, {@code
     * false}.
     */
    public boolean IsInteger() {
      if (!this.isFinite()) {
        return false;
      }
      if (this.denominator.isEvenNumber() &&
           !this.unsignedNumerator.isEvenNumber()) {
        // Even denominator, odd numerator, so not an integer
        return false;
      }
      EInteger rem = this.getNumerator().Remainder(this.getDenominator());
      return rem.isZero();
    }

    /**
     * Gets this object's numerator.
     * @return This object's numerator. If this object is a not-a-number value,
     * returns the diagnostic information (which will be negative if this object is
     * negative).
     */
    public final EInteger getNumerator() {
        return this.isNegative() ? this.unsignedNumerator.Negate().ToEInteger() :
          this.unsignedNumerator.ToEInteger();
      }

    /**
     * Gets the sign of this rational number.
     * @return The sign of this rational number.
     */
    public final int signum() {
        return ((this.flags & (BigNumberFlags.FlagInfinity |
                BigNumberFlags.FlagNaN)) != 0) ? (this.isNegative() ? -1 : 1) :
          (this.unsignedNumerator.isValueZero() ? 0 : (this.isNegative() ? -1 : 1));
      }

    /**
     * Gets this object's numerator with the sign removed.
     * @return This object's numerator. If this object is a not-a-number value,
     * returns the diagnostic information.
     */
    public final EInteger getUnsignedNumerator() {
        return this.unsignedNumerator.ToEInteger();
      }

    /**
     * Creates a rational number with the given numerator and denominator.
     * @param numeratorSmall The numerator.
     * @param denominatorSmall The denominator.
     * @return An arbitrary-precision rational number.
     * @throws IllegalArgumentException The denominator is zero.
     */
    public static ERational Create(
      int numeratorSmall,
      int denominatorSmall) {
      return Create(EInteger.FromInt32(numeratorSmall), EInteger.FromInt32(denominatorSmall));
    }

    /**
     * Creates a rational number with the given numerator and denominator.
     * @param numeratorLong The numerator.
     * @param denominatorLong The denominator.
     * @return An arbitrary-precision rational number.
     * @throws IllegalArgumentException The denominator is zero.
     */
    public static ERational Create(
      long numeratorLong,
      long denominatorLong) {
      return Create(EInteger.FromInt64(numeratorLong), EInteger.FromInt64(denominatorLong));
    }

    /**
     * Creates a rational number with the given numerator and denominator.
     * @param numerator The numerator.
     * @param denominator The denominator.
     * @return An arbitrary-precision rational number.
     * @throws IllegalArgumentException The denominator is zero.
     * @throws NullPointerException The parameter {@code numerator} or {@code
     * denominator} is null.
     */
    public static ERational Create(
      EInteger numerator,
      EInteger denominator) {
      if (numerator == null) {
        throw new NullPointerException("numerator");
      }
      if (denominator == null) {
        throw new NullPointerException("denominator");
      }
      if (denominator.isZero()) {
        throw new IllegalArgumentException("denominator is zero");
      }
      boolean numNegative = numerator.signum() < 0;
      boolean denNegative = denominator.signum() < 0;
      byte bflags = (byte)((numNegative != denNegative) ?
        BigNumberFlags.FlagNegative : 0);
      if (numNegative) {
        numerator = numerator.Negate();
      }
      if (denNegative) {
        denominator = denominator.Negate();
      }
      return new ERational(
         FastIntegerFixed.FromBig(numerator),
         FastIntegerFixed.FromBig(denominator),
         bflags);
    }

    /**
     * Creates a not-a-number arbitrary-precision rational number.
     * @param diag An integer, 0 or greater, to use as diagnostic information
     * associated with this object. If none is needed, should be zero. To get the
     * diagnostic information from another arbitrary-precision rational number, use
     * that object's {@code UnsignedNumerator} property.
     * @return An arbitrary-precision rational number.
     * @throws IllegalArgumentException The parameter {@code diag} is less than 0.
     */
    public static ERational CreateNaN(EInteger diag) {
      return CreateNaN(diag, false, false);
    }

    /**
     * Creates a not-a-number arbitrary-precision rational number.
     * @param diag An integer, 0 or greater, to use as diagnostic information
     * associated with this object. If none is needed, should be zero. To get the
     * diagnostic information from another arbitrary-precision rational number, use
     * that object's {@code UnsignedNumerator} property.
     * @param signaling Whether the return value will be signaling (true) or quiet
     * (false).
     * @param negative Whether the return value is negative.
     * @return An arbitrary-precision rational number.
     * @throws IllegalArgumentException The parameter {@code diag} is less than 0.
     * @throws NullPointerException The parameter {@code diag} is null.
     */
    public static ERational CreateNaN(
      EInteger diag,
      boolean signaling,
      boolean negative) {
      if (diag == null) {
        throw new NullPointerException("diag");
      }
      if (diag.signum() < 0) {
        throw new IllegalArgumentException("Diagnostic information must be 0 or" +
          "\u0020greater," + "\u0020 was: " + diag);
      }
      if (diag.isZero() && !negative) {
        return signaling ? SignalingNaN : NaN;
      }
      int flags = 0;
      if (negative) {
        flags |= BigNumberFlags.FlagNegative;
      }
      flags |= signaling ? BigNumberFlags.FlagSignalingNaN :
        BigNumberFlags.FlagQuietNaN;
      return new ERational(FastIntegerFixed.FromBig(diag),
  FastIntegerFixed.One,
  (byte)flags);
    }

    /**
     * <p>Converts a 64-bit floating-point number to a rational number. This method
     * computes the exact value of the floating point number, not an approximation,
     * as is often the case by converting the number to a string. </p> <p>The input
     * value can be a not-a-number (NaN) value (such as {@code Double.NaN});
     * however, NaN values have multiple forms that are equivalent for many
     * applications' purposes, and {@code Double.NaN} is only one of these
     * equivalent forms. In fact, {@code ERational.FromDouble(Double.NaN)} could
     * produce an object that is represented differently between DotNet and Java,
     * because {@code Double.NaN} may have a different form in DotNet and Java (for
     * example, the NaN value's sign may be negative in DotNet, but positive in
     * Java). Use `IsNaN()` to determine whether an object from this class stores a
     * NaN value of any form.</p>
     * @param flt The parameter {@code flt} is a 64-bit floating-point number.
     * @return A rational number with the same value as {@code flt}.
     */
    public static ERational FromDouble(double flt) {
      return FromEFloat(EFloat.FromDouble(flt));
    }

    /**
     * Converts an arbitrary-precision decimal number to a rational number.
     * @param ef The number to convert as an arbitrary-precision decimal number.
     * @return An arbitrary-precision rational number.
     * @deprecated Renamed to FromEDecimal.
 */
@Deprecated
    public static ERational FromExtendedDecimal(EDecimal ef) {
      return FromEDecimal(ef);
    }

    /**
     * Converts an arbitrary-precision binary floating-point number to a rational
     * number.
     * @param ef The number to convert as an arbitrary-precision binary
     * floating-point number.
     * @return An arbitrary-precision rational number.
     * @deprecated Renamed to FromEFloat.
 */
@Deprecated
    public static ERational FromExtendedFloat(EFloat ef) {
      return FromEFloat(ef);
    }

    /**
     * Converts an arbitrary-precision decimal number to a rational number.
     * @param ef The number to convert as an arbitrary-precision decimal number.
     * @return An arbitrary-precision rational number.
     * @throws NullPointerException The parameter {@code ef} is null.
     * @throws IllegalArgumentException doesn't satisfy den.signum() &gt;= 0.
     */
    public static ERational FromEDecimal(EDecimal ef) {
      if (ef == null) {
        throw new NullPointerException("ef");
      }
      if (!ef.isFinite()) {
        return ef.IsInfinity() ? (ef.isNegative() ? NegativeInfinity :
PositiveInfinity) : CreateNaN(
                 ef.getUnsignedMantissa(),
                 ef.IsSignalingNaN(),
                 ef.isNegative());
      }
      EInteger num = ef.getMantissa();
      EInteger exp = ef.getExponent();
      if (exp.isZero()) {
        if (num.signum() != 0) {
          return FromEInteger(num);
        } else {
          return ef.isNegative() ? NegativeZero : Zero;
        }
      }
      boolean neg = num.signum() < 0;
      num = num.Abs();
      EInteger den = EInteger.FromInt32(1);
      if (exp.signum() < 0) {
        exp=(exp).Negate();
        den = NumberUtility.FindPowerOfTenFromBig(exp);
      } else {
        EInteger powerOfTen = NumberUtility.FindPowerOfTenFromBig(exp);
        num = num.Multiply(powerOfTen);
      }
      if (neg) {
        num=(num).Negate();
      }
      ERational rat = ERational.Create(num, den);

      return (ef.isNegative() && num.signum() == 0) ? rat.Negate() : rat;
    }

    /**
     * Converts an arbitrary-precision binary floating-point number to a rational
     * number.
     * @param ef The number to convert as an arbitrary-precision binary
     * floating-point number.
     * @return An arbitrary-precision rational number.
     * @throws NullPointerException The parameter {@code ef} is null.
     * @throws IllegalArgumentException doesn't satisfy den.signum() &gt;= 0.
     */
    public static ERational FromEFloat(EFloat ef) {
      if (ef == null) {
        throw new NullPointerException("ef");
      }
      if (!ef.isFinite()) {
        return ef.IsInfinity() ? (ef.isNegative() ? NegativeInfinity :
PositiveInfinity) : CreateNaN(
                 ef.getUnsignedMantissa(),
                 ef.IsSignalingNaN(),
                 ef.isNegative());
      }
      EInteger num = ef.getMantissa();
      EInteger exp = ef.getExponent();
      if (exp.isZero()) {
        if (num.signum() != 0) {
          return FromEInteger(num);
        } else {
          return ef.isNegative() ? NegativeZero : Zero;
        }
      }
      boolean neg = num.signum() < 0;
      num = num.Abs();
      EInteger den = EInteger.FromInt32(1);
      if (exp.signum() < 0) {
        exp=(exp).Negate();
        den = den.ShiftLeft(exp);
      } else {
        num = num.ShiftLeft(exp);
      }
      if (neg) {
        num=(num).Negate();
      }
      ERational rat = ERational.Create(num, den);

      return (ef.isNegative() && num.signum() == 0) ? rat.Negate() : rat;
    }

    /**
     * Converts an arbitrary-precision integer to a rational number.
     * @param bigint The number to convert as an arbitrary-precision integer.
     * @return The exact value of the integer as a rational number.
     */
    public static ERational FromEInteger(EInteger bigint) {
      return ERational.Create(bigint, EInteger.FromInt32(1));
    }

    /**
     * <p>Converts a 32-bit binary floating-point number to a rational number. This
     * method computes the exact value of the floating point number, not an
     * approximation, as is often the case by converting the number to a string.
     * </p> <p>The input value can be a not-a-number (NaN) value (such as {@code
     * Float.NaN} in DotNet or Float.NaN in Java); however, NaN values have
     * multiple forms that are equivalent for many applications' purposes, and
     * {@code Float.NaN} / {@code Float.NaN} is only one of these equivalent forms.
     * In fact, {@code ERational.FromSingle(Float.NaN)} or {@code
     * ERational.FromSingle(Float.NaN)} could produce an object that is represented
     * differently between DotNet and Java, because {@code Float.NaN} / {@code
     * Float.NaN} may have a different form in DotNet and Java (for example, the
     * NaN value's sign may be negative in DotNet, but positive in Java). Use
     * `IsNaN()` to determine whether an object from this class stores a NaN value
     * of any form.</p>
     * @param flt The parameter {@code flt} is a 32-bit binary floating-point
     * number.
     * @return A rational number with the same value as {@code flt}.
     */
    public static ERational FromSingle(float flt) {
      return FromEFloat(EFloat.FromSingle(flt));
    }

    /**
     * Creates a binary rational number from a 32-bit floating-point number encoded
     * in the IEEE 754 binary32 format. This method computes the exact value of the
     * floating point number, not an approximation, as is often the case by
     * converting the number to a string.
     * @param value A 32-bit integer encoded in the IEEE 754 binary32 format.
     * @return A rational number with the same floating-point value as {@code
     * value}.
     */
    public static ERational FromSingleBits(int value) {
      return FromEFloat(EFloat.FromSingleBits(value));
    }

    /**
     * Creates a binary rational number from a binary floating-point number encoded
     * in the IEEE 754 binary16 format (also known as a "half-precision"
     * floating-point number). This method computes the exact value of the floating
     * point number, not an approximation, as is often the case by converting the
     * number to a string.
     * @param value A 16-bit integer encoded in the IEEE 754 binary16 format.
     * @return A rational number with the same floating-point value as {@code
     * value}.
     */
    public static ERational FromHalfBits(short value) {
      return FromEFloat(EFloat.FromHalfBits(value));
    }

    /**
     * Creates a binary rational number from a 64-bit floating-point number encoded
     * in the IEEE 754 binary64 format. This method computes the exact value of the
     * floating point number, not an approximation, as is often the case by
     * converting the number to a string.
     * @param value A 64-bit integer encoded in the IEEE 754 binary64 format.
     * @return A rational number with the same floating-point value as {@code
     * value}.
     */
    public static ERational FromDoubleBits(long value) {
      return FromEFloat(EFloat.FromDoubleBits(value));
    }

    /**
     * Creates a rational number from a text string that represents a number. See
     * {@code FromString(string, int, int)} for more information.
     * @param str A string that represents a number.
     * @return An arbitrary-precision rational number with the same value as the
     * given string.
     * @throws NumberFormatException The parameter {@code str} is not a correctly
     * formatted number string.
     */
    public static ERational FromString(String str) {
      return FromString(str, 0, str == null ? 0 : str.length());
    }

    /**
     * <p>Creates a rational number from a text string that represents a
     * number.</p> <p>The format of the string generally consists of:</p> <ul>
     * <li>An optional plus sign ("+" , U+002B) or minus sign ("-", U+002D) (if '-'
     * , the value is negative.)</li><li>The numerator in the form of one or more
     * digits (these digits may begin with any number of
     * zeros).</li><li>Optionally, "/" followed by the denominator in the form of
     * one or more digits (these digits may begin with any number of zeros). If a
     * denominator is not given, it's equal to 1.</li></ul> <p>The string can also
     * be "-INF", "-Infinity", "Infinity", "INF", quiet NaN ("NaN" /"-NaN")
     * followed by any number of digits, or signaling NaN ("sNaN" /"-sNaN")
     * followed by any number of digits, all in any combination of uppercase and
     * lowercase.</p> <p>All characters mentioned earlier are the corresponding
     * characters in the Basic Latin range. In particular, the digits must be the
     * basic digits 0 to 9 (U+0030 to U+0039). The string is not allowed to contain
     * white space characters, including spaces.</p>
     * @param str A text string, a portion of which represents a number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @return An arbitrary-precision rational number.
     * @throws NumberFormatException The parameter {@code str} is not a correctly
     * formatted number string.
     * @throws NullPointerException The parameter {@code str} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code str} 's length, or {@code str} 's length minus
     * {@code offset} is less than {@code length}.
     */
    public static ERational FromString(
      String str,
      int offset,
      int length) {
       return ERationalTextString.FromString(str, offset, length, true);
    }

    /**
     * Creates a rational number from a sequence of {@code char} s that represents
     * a number. See {@code FromString(string, int, int)} for more information.
     * @param chars A sequence of {@code char} s that represents a number.
     * @return An arbitrary-precision rational number with the same value as the
     * given sequence of {@code char} s.
     * @throws NumberFormatException The parameter {@code chars} is not a correctly
     * formatted sequence of {@code char} s.
     */
    public static ERational FromString(char[] chars) {
      return FromString(chars, 0, chars == null ? 0 : chars.length);
    }

    /**
     * <p>Creates a rational number from a sequence of {@code char} s that
     * represents a number.</p> <p>The format of the sequence of {@code char} s
     * generally consists of:</p> <ul> <li>An optional plus sign ("+" , U+002B) or
     * minus sign ("-", U+002D) (if '-' , the value is negative.)</li><li>The
     * numerator in the form of one or more digits (these digits may begin with any
     * number of zeros).</li><li>Optionally, "/" followed by the denominator in the
     * form of one or more digits (these digits may begin with any number of
     * zeros). If a denominator is not given, it's equal to 1.</li></ul> <p>The
     * sequence of {@code char} s can also be "-INF", "-Infinity", "Infinity",
     * "INF", quiet NaN ("NaN" /"-NaN") followed by any number of digits, or
     * signaling NaN ("sNaN" /"-sNaN") followed by any number of digits, all in any
     * combination of uppercase and lowercase.</p> <p>All characters mentioned earlier
     * are the corresponding characters in the Basic Latin range. In particular,
     * the digits must be the basic digits 0 to 9 (U+0030 to U+0039). The sequence
     * of {@code char} s is not allowed to contain white space characters,
     * including spaces.</p>
     * @param chars A sequence of {@code char} s, a portion of which represents a
     * number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code chars} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * chars} (but not more than {@code chars} 's length).
     * @return An arbitrary-precision rational number.
     * @throws NumberFormatException The parameter {@code chars} is not a correctly
     * formatted sequence of {@code char} s.
     * @throws NullPointerException The parameter {@code chars} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code chars} 's length, or {@code chars} 's length
     * minus {@code offset} is less than {@code length}.
     */
    public static ERational FromString(
      char[] chars,
      int offset,
      int length) {
       return ERationalCharArrayString.FromString(chars, offset, length, true);
    }

    /**
     * Creates a rational number from a sequence of bytes that represents a number.
     * See {@code FromString(string, int, int)} for more information.
     * @param bytes A sequence of bytes that represents a number.
     * @return An arbitrary-precision rational number with the same value as the
     * given sequence of bytes.
     * @throws NumberFormatException The parameter {@code bytes} is not a correctly
     * formatted sequence of bytes.
     */
    public static ERational FromString(byte[] bytes) {
      return FromString(bytes, 0, bytes == null ? 0 : bytes.length);
    }

    /**
     * <p>Creates a rational number from a sequence of bytes that represents a
     * number.</p> <p>The format of the sequence of bytes generally consists
     * of:</p> <ul> <li>An optional plus sign ("+" , U+002B) or minus sign ("-",
     * U+002D) (if '-' , the value is negative.)</li><li>The numerator in the form
     * of one or more digits (these digits may begin with any number of
     * zeros).</li><li>Optionally, "/" followed by the denominator in the form of
     * one or more digits (these digits may begin with any number of zeros). If a
     * denominator is not given, it's equal to 1.</li></ul> <p>The sequence of
     * bytes can also be "-INF", "-Infinity", "Infinity", "INF", quiet NaN ("NaN"
     * /"-NaN") followed by any number of digits, or signaling NaN ("sNaN"
     * /"-sNaN") followed by any number of digits, all in any combination of upper
     * and lowercase.</p> <p>All characters mentioned earlier are the
     * corresponding characters in the Basic Latin range. In particular, the digits
     * must be the basic digits 0 to 9 (U+0030 to U+0039). The sequence of bytes is
     * not allowed to contain white space characters, including spaces.</p>
     * @param bytes A sequence of bytes, a portion of which represents a number.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code bytes} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * bytes} (but not more than {@code bytes} 's length).
     * @return An arbitrary-precision rational number.
     * @throws NumberFormatException The parameter {@code bytes} is not a correctly
     * formatted sequence of bytes.
     * @throws NullPointerException The parameter {@code bytes} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code bytes} 's length, or {@code bytes} 's length
     * minus {@code offset} is less than {@code length}.
     */
    public static ERational FromString(
      byte[] bytes,
      int offset,
      int length) {
       return ERationalByteArrayString.FromString(bytes, offset, length, true);
    }

    /**
     * <p>Compares the absolute values of this object and another object, imposing
     * a total ordering on all possible values (ignoring their signs). In this
     * method: </p> <ul> <li>For objects with the same value, the one with the
     * higher denominator has a greater "absolute value".</li><li>Negative zero and
     * positive zero are considered equal.</li><li>Quiet NaN has a higher "absolute
     * value" than signaling NaN. If both objects are quiet NaN or both are
     * signaling NaN, the one with the higher diagnostic information has a greater
     * "absolute value".</li><li>NaN has a higher "absolute value" than
     * infinity.</li><li>Infinity has a higher "absolute value" than any finite
     * number.</li></ul>
     * @param other An arbitrary-precision rational number to compare with this
     * one.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater. This
     * implementation returns a positive number if.
     */
    public int CompareToTotalMagnitude(ERational other) {
      if (other == null) {
        return 1;
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
        cmp = this.unsignedNumerator.compareTo(
            other.unsignedNumerator);
        return cmp;
      } else if (valueIThis == 1) {
        return 0;
      } else {
        cmp = this.Abs().compareTo(other.Abs());
        if (cmp == 0) {
          cmp = this.denominator.compareTo(
              other.denominator);
          return cmp;
        }
        return cmp;
      }
    }

    /**
     * <p>Compares the values of this object and another object, imposing a total
     * ordering on all possible values. In this method: </p> <ul> <li>For objects
     * with the same value, the one with the higher denominator has a greater
     * "absolute value".</li><li>Negative zero is less than positive
     * zero.</li><li>Quiet NaN has a higher "absolute value" than signaling NaN. If
     * both objects are quiet NaN or both are signaling NaN, the one with the
     * higher diagnostic information has a greater "absolute value".</li><li>NaN
     * has a higher "absolute value" than infinity.</li><li>Infinity has a higher
     * "absolute value" than any finite number.</li><li>Negative numbers are less
     * than positive numbers.</li></ul>
     * @param other An arbitrary-precision rational number to compare with this
     * one.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater. This
     * implementation returns a positive number if.
     */
    public int CompareToTotal(ERational other) {
      if (other == null) {
        return 1;
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
        cmp = this.unsignedNumerator.compareTo(
            other.unsignedNumerator);
        return neg1 ? -cmp : cmp;
      } else if (valueIThis == 1) {
        return 0;
      } else {
        cmp = this.compareTo(other);
        if (cmp == 0) {
          cmp = this.denominator.compareTo(
              other.denominator);
          return neg1 ? -cmp : cmp;
        }
        return cmp;
      }
    }

    /**
     * Returns the absolute value of this rational number, that is, a number with
     * the same value as this one but as a nonnegative number.
     * @return An arbitrary-precision rational number.
     */
    public ERational Abs() {
      if (this.isNegative()) {
        return new ERational(
            this.unsignedNumerator,
            this.denominator,
            (byte)(this.flags & ~BigNumberFlags.FlagNegative));
      }
      return this;
    }

    /**
     * Adds this arbitrary-precision rational number and another
     * arbitrary-precision rational number and returns the result.
     * @param otherValue Another arbitrary-precision rational number.
     * @return The sum of the two numbers, that is, this arbitrary-precision
     * rational number plus another arbitrary-precision rational number.
     * @throws NullPointerException The parameter {@code otherValue} is null.
     */
    public ERational Add(ERational otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.IsSignalingNaN()) {
        return CreateNaN(this.getUnsignedNumerator(), false, this.isNegative());
      }
      if (otherValue.IsSignalingNaN()) {
        return CreateNaN(
            otherValue.getUnsignedNumerator(),
            false,
            otherValue.isNegative());
      }
      if (this.IsQuietNaN()) {
        return this;
      }
      if (otherValue.IsQuietNaN()) {
        return otherValue;
      }
      if (this.IsInfinity()) {
        return otherValue.IsInfinity() ? ((this.isNegative() ==
              otherValue.isNegative()) ? this : NaN) : this;
      }
      if (otherValue.IsInfinity()) {
        return otherValue;
      }
      EInteger ad = this.getNumerator().Multiply(otherValue.getDenominator());
      EInteger bc = this.getDenominator().Multiply(otherValue.getNumerator());
      EInteger bd = this.getDenominator().Multiply(otherValue.getDenominator());
      ad = ad.Add(bc);
      return ERational.Create(ad, bd);
    }

    /**
     * Compares the mathematical value of an arbitrary-precision rational number
     * with that of this instance. This method currently uses the rules given in
     * the CompareToValue method, so that it it is not consistent with the Equals
     * method, but it may change in a future version to use the rules for the
     * CompareToTotal method instead.
     * @param other An arbitrary-precision rational number.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater. This implementation
     * returns a positive number if.
     */
    public int compareTo(ERational other) {
      return this.CompareToValue(other);
    }

    /**
     * Compares the mathematical value of an arbitrary-precision rational number
     * with that of this instance. In this method, NaN values are greater than any
     * other ERational value, and two NaN values (even if their payloads differ)
     * are treated as equal by this method. This method is not consistent with the
     * Equals method.
     * @param other An arbitrary-precision rational number.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater. This implementation
     * returns a positive number if.
     */
    public int CompareToValue(ERational other) {
      if (other == null) {
        return 1;
      }
      if (this == other) {
        return 0;
      }
      if (this.IsNaN()) {
        return other.IsNaN() ? 0 : 1;
      }
      if (other.IsNaN()) {
        return -1;
      }
      int signA = this.signum();
      int signB = other.signum();
      if (signA != signB) {
        return (signA < signB) ? -1 : 1;
      }
      if (signB == 0 || signA == 0) {
        // Special case: Either operand is zero
        return 0;
      }
      if (this.IsInfinity()) {
        if (other.IsInfinity()) {
          // if we get here, this only means that
          // both are positive infinity or both
          // are negative infinity
          return 0;
        }
        return this.isNegative() ? -1 : 1;
      }
      if (other.IsInfinity()) {
        return other.isNegative() ? 1 : -1;
      }
      // At this point, both numbers are finite and
      // have the same sign

      int dencmp = this.denominator.compareTo(other.denominator);
      // At this point, the signs are equal so we can compare
      // their absolute values instead
      int numcmp = this.unsignedNumerator.compareTo(other.unsignedNumerator);
      if (signA < 0) {
        numcmp = -numcmp;
      }
      if (numcmp == 0) {
        // Special case: numerators are equal, so the
        // number with the lower denominator is greater
        return signA < 0 ? dencmp : -dencmp;
      }
      if (dencmp == 0) {
        // denominators are equal
        return numcmp;
      }
      EInteger ea = this.getNumerator();
      EInteger eb = this.getDenominator();
      EInteger ec = other.getNumerator();
      EInteger ed = other.getDenominator();
      int cmpThis = ea.Abs().compareTo(eb);
      int cmpOther = ec.Abs().compareTo(ed);
      if (cmpThis == 0 && cmpOther == 0) {
        // Both numbers' absolute values are 1
        return 0;
      } else if (cmpThis == 0) {
        // This number's abs is 1, the other's isn't.
        return signA < 0 ? cmpOther : -cmpOther;
      } else if (cmpOther == 0) {
        // The other number's abs is 1, this one's isn't.
        return signA < 0 ? -cmpThis : cmpThis;
      } else if (cmpThis < 0 && cmpOther > 0) {
        return signA < 0 ? 1 : -1;
      } else if (cmpThis > 0 && cmpOther < 0) {
        return signA < 0 ? -1 : 1;
      }
      // Compare the number of bits of the products
      EInteger bitsADUpper = ea.GetUnsignedBitLengthAsEInteger().Add(
          ed.GetUnsignedBitLengthAsEInteger());
      EInteger bitsBCUpper = eb.GetUnsignedBitLengthAsEInteger().Add(
          ec.GetUnsignedBitLengthAsEInteger());
      EInteger bitsADLower = bitsADUpper.Subtract(1);
      EInteger bitsBCLower = bitsBCUpper.Subtract(1);
      if (bitsADLower.compareTo(bitsBCUpper) > 0) {
        return signA < 0 ? -1 : 1;
      }
      if (bitsBCLower.compareTo(bitsADUpper) > 0) {
        return signA < 0 ? 1 : -1;
      }
      EInteger ad = ea.Multiply(ed);
      EInteger bc = eb.Multiply(ec);
      return ad.compareTo(bc);
    }

    /**
     * Gets the greater value between two rational numbers.
     * @param first An arbitrary-precision rational number.
     * @param second Another arbitrary-precision rational number.
     * @return The larger value of the two numbers. If one is positive zero and the
     * other is negative zero, returns the positive zero. If the two numbers are
     * positive and have the same value, returns the one with the larger
     * denominator. If the two numbers are negative and have the same value,
     * returns the one with the smaller denominator.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
    public static ERational Max(
      ERational first,
      ERational second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      int cmp = first.CompareToValue(second);
      if (cmp == 0) {
        if (first.isNegative()) {
          return (!second.isNegative()) ? second :
(first.getDenominator().compareTo(second.getDenominator()) > 0 ?

              first : second);
        } else {
          return second.isNegative() ? first :
(first.getDenominator().compareTo(second.getDenominator()) < 0 ?

              first : second);
        }
      }
      return cmp > 0 ? first : second;
    }

    /**
     * Gets the greater value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Max.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return The larger value of the two numbers, ignoring their signs.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
    public static ERational MaxMagnitude(
      ERational first,
      ERational second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      int cmp = first.Abs().CompareToValue(second.Abs());
      return (cmp == 0) ? Max(first, second) : (cmp > 0 ? first : second);
    }

    /**
     * Gets the lesser value between two rational numbers.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return The smaller value of the two numbers. If one is positive zero and
     * the other is negative zero, returns the negative zero. If the two numbers
     * are positive and have the same value, returns the one with the smaller
     * denominator. If the two numbers are negative and have the same value,
     * returns the one with the larger denominator.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
    public static ERational Min(
      ERational first,
      ERational second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      int cmp = first.CompareToValue(second);
      if (cmp == 0) {
        if (first.isNegative()) {
          return (!second.isNegative()) ? first : (
              first.getDenominator().compareTo(second.getDenominator()) < 0 ?
              first : second);
        } else {
          return second.isNegative() ? second : (
              first.getDenominator().compareTo(second.getDenominator()) > 0 ?
              first : second);
        }
      }
      return cmp < 0 ? first : second;
    }

    /**
     * Gets the lesser value between two values, ignoring their signs. If the
     * absolute values are equal, has the same effect as Min.
     * @param first The first value to compare.
     * @param second The second value to compare.
     * @return The smaller value of the two numbers, ignoring their signs.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
    public static ERational MinMagnitude(
      ERational first,
      ERational second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      int cmp = first.Abs().CompareToValue(second.Abs());
      return (cmp == 0) ? Min(first, second) : (cmp < 0 ? first : second);
    }

    /**
     * Compares the mathematical value of an arbitrary-precision rational number
     * with that of this instance. This method currently uses the rules given in
     * the CompareToValue method, so that it it is not consistent with the Equals
     * method, but it may change in a future version to use the rules for the
     * CompareToTotal method instead.
     * @param intOther The parameter {@code intOther} is a 32-bit signed integer.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater.
     */
    public int compareTo(int intOther) {
      return this.CompareToValue(ERational.FromInt32(intOther));
    }

    /**
     * Compares the mathematical value of an arbitrary-precision rational number
     * with that of this instance. In this method, NaN values are greater than any
     * other ERational value, and two NaN values (even if their payloads differ)
     * are treated as equal by this method. This method is not consistent with the
     * Equals method.
     * @param intOther The parameter {@code intOther} is a 32-bit signed integer.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater.
     */
    public int CompareToValue(int intOther) {
      return this.CompareToValue(ERational.FromInt32(intOther));
    }

    /**
     * <p>Compares the mathematical values of this object and another object,
     * accepting NaN values. </p><p>This method is not consistent with the Equals
     * method because two different numbers with the same mathematical value, but
     * different exponents, will compare as equal.</p> <p>In this method, negative
     * zero and positive zero are considered equal.</p> <p>If this object is a
     * quiet NaN or signaling NaN, this method will not trigger an error. Instead,
     * NaN will compare greater than any other number, including infinity.</p>
     * @param intOther The parameter {@code intOther} is a 64-bit signed integer.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other value, or 0
     * if both values are equal.
     */
    public int CompareToValue(long intOther) {
      return this.CompareToValue(FromInt64(intOther));
    }

    /**
     * Compares the mathematical values of this object and another object,
     * accepting NaN values. This method currently uses the rules given in the
     * CompareToValue method, so that it it is not consistent with the Equals
     * method, but it may change in a future version to use the rules for the
     * CompareToTotal method instead.
     * @param intOther The parameter {@code intOther} is a 64-bit signed integer.
     * @return Less than 0 if this object's value is less than the other value, or
     * greater than 0 if this object's value is greater than the other value, or 0
     * if both values are equal.
     */
    public int compareTo(long intOther) {
      return this.CompareToValue(FromInt64(intOther));
    }

    /**
     * Compares an arbitrary-precision binary floating-point number with this
     * instance. In this method, NaN values are greater than any other ERational or
     * EFloat value, and two NaN values (even if their payloads differ) are treated
     * as equal by this method.
     * @param other An arbitrary-precision binary floating-point number.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater. This implementation
     * returns a positive number if.
     */
    public int CompareToBinary(EFloat other) {
      if (other == null) {
        return 1;
      }
      if (this.IsNaN()) {
        return other.IsNaN() ? 0 : 1;
      }
      int signA = this.signum();
      int signB = other.signum();
      if (signA != signB) {
        return (signA < signB) ? -1 : 1;
      }
      if (signB == 0 || signA == 0) {
        // Special case: Either operand is zero
        return 0;
      }
      if (this.IsInfinity()) {
        if (other.IsInfinity()) {
          // if we get here, this only means that
          // both are positive infinity or both
          // are negative infinity
          return 0;
        }
        return this.isNegative() ? -1 : 1;
      }
      if (other.IsInfinity()) {
        return other.isNegative() ? 1 : -1;
      }
      // At this point, both numbers are finite and
      // have the same sign

      EInteger bigExponent = other.getExponent();
      if (bigExponent.isZero()) {
        // Special case: other has exponent 0
        EInteger otherMant = other.getMantissa();
        EInteger bcx = this.getDenominator().Multiply(otherMant);
        return this.getNumerator().compareTo(bcx);
      }
      if (bigExponent.Abs().compareTo(EInteger.FromInt64(1000)) > 0) {
        // Other has a high absolute value of exponent, so try different
        // approaches to
        // comparison
        EInteger thisRem;
        EInteger thisInt;
        {
          EInteger[] divrem = this.getUnsignedNumerator().DivRem(this.getDenominator());
          thisInt = divrem[0];
          thisRem = divrem[1];
        }
        EFloat otherAbs = other.Abs();
        EFloat thisIntDec = EFloat.FromEInteger(thisInt);
        if (thisRem.isZero()) {
          // This Object's value is an integer
          // System.out.println("Shortcircuit IV");
          int ret = thisIntDec.compareTo(otherAbs);
          return this.isNegative() ? -ret : ret;
        }
        if (thisIntDec.compareTo(otherAbs) > 0) {
          // Truncated absolute value is greater than other's untruncated
          // absolute value
          // System.out.println("Shortcircuit I");
          return this.isNegative() ? -1 : 1;
        }
        // Round up
        thisInt = thisInt.Add(EInteger.FromInt32(1));
        thisIntDec = EFloat.FromEInteger(thisInt);
        if (thisIntDec.compareTo(otherAbs) < 0) {
          // Absolute value rounded up is less than other's unrounded
          // absolute value
          // System.out.println("Shortcircuit II");
          return this.isNegative() ? 1 : -1;
        }
        thisIntDec = EFloat.FromEInteger(this.getUnsignedNumerator()).Divide(
            EFloat.FromEInteger(this.getDenominator()),
            EContext.ForPrecisionAndRounding(256, ERounding.Down));
        if (thisIntDec.compareTo(otherAbs) > 0) {
          // Truncated absolute value is greater than other's untruncated
          // absolute value
          // System.out.println("Shortcircuit III");
          return this.isNegative() ? -1 : 1;
        }
        if (other.getExponent().signum() > 0) {
          // NOTE: if unsigned numerator is 0, bitLength will return
          // 0 instead of 1, but the possibility of 0 was already excluded
          EInteger bigDigitCount =
            this.getUnsignedNumerator().GetSignedBitLengthAsEInteger()
            .Subtract(1);
          if (bigDigitCount.compareTo(other.getExponent()) < 0) {
            // Numerator's digit count minus 1 is less than the other's
            // exponent,
            // and other's exponent is positive, so this value's absolute
            // value is less
            return this.isNegative() ? 1 : -1;
          }
        }
      }
      // Convert to rational number and use usual rational number
      // comparison
      // System.out.println("no shortcircuit");
      // System.out.println(this);
      // System.out.println(other);
      ERational otherRational = ERational.FromEFloat(other);
      return this.CompareToValue(otherRational);
    }

    /**
     * Compares an arbitrary-precision decimal number with this instance.
     * @param other An arbitrary-precision decimal number.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater. This implementation
     * returns a positive number if.
     */
    public int CompareToDecimal(EDecimal other) {
      if (other == null) {
        return 1;
      }
      if (this.IsNaN()) {
        return other.IsNaN() ? 0 : 1;
      }
      int signA = this.signum();
      int signB = other.signum();
      if (signA != signB) {
        return (signA < signB) ? -1 : 1;
      }
      if (signB == 0 || signA == 0) {
        // Special case: Either operand is zero
        return 0;
      }
      if (this.IsInfinity()) {
        if (other.IsInfinity()) {
          // if we get here, this only means that
          // both are positive infinity or both
          // are negative infinity
          return 0;
        }
        return this.isNegative() ? -1 : 1;
      }
      if (other.IsInfinity()) {
        return other.isNegative() ? 1 : -1;
      }
      // At this point, both numbers are finite and
      // have the same sign

      if (other.getExponent().isZero()) {
        // Special case: other has exponent 0
        EInteger otherMant = other.getMantissa();
        EInteger bcx = this.getDenominator().Multiply(otherMant);
        return this.getNumerator().compareTo(bcx);
      }
      if (other.getExponent().Abs().compareTo(EInteger.FromInt64(50)) > 0) {
        // Other has a high absolute value of exponent, so try different
        // approaches to
        // comparison
        EInteger thisRem;
        EInteger thisInt;
        {
          EInteger[] divrem = this.getUnsignedNumerator().DivRem(this.getDenominator());
          thisInt = divrem[0];
          thisRem = divrem[1];
        }
        EDecimal otherAbs = other.Abs();
        EDecimal thisIntDec = EDecimal.FromEInteger(thisInt);
        if (thisRem.isZero()) {
          // This Object's value is an integer
          // System.out.println("Shortcircuit IV");
          int ret = thisIntDec.compareTo(otherAbs);
          return this.isNegative() ? -ret : ret;
        }
        if (thisIntDec.compareTo(otherAbs) > 0) {
          // Truncated absolute value is greater than other's untruncated
          // absolute value
          // System.out.println("Shortcircuit I");
          return this.isNegative() ? -1 : 1;
        }
        // Round up
        thisInt = thisInt.Add(EInteger.FromInt32(1));
        thisIntDec = EDecimal.FromEInteger(thisInt);
        if (thisIntDec.compareTo(otherAbs) < 0) {
          // Absolute value rounded up is less than other's unrounded
          // absolute value
          // System.out.println("Shortcircuit II");
          return this.isNegative() ? 1 : -1;
        }
        // Conservative approximation of this rational number's absolute value,
        // as a decimal number. The true value will be greater or equal.
        thisIntDec = EDecimal.FromEInteger(this.getUnsignedNumerator()).Divide(
            EDecimal.FromEInteger(this.getDenominator()),
            EContext.ForPrecisionAndRounding(20, ERounding.Down));
        if (thisIntDec.compareTo(otherAbs) > 0) {
          // Truncated absolute value is greater than other's untruncated
          // absolute value
          // System.out.println("Shortcircuit III");
          return this.isNegative() ? -1 : 1;
        }
        // System.out.println("---" + this + " " + other);
        if (other.getExponent().signum() > 0) {
          EInteger bigDigitCount =
            this.getUnsignedNumerator().GetDigitCountAsEInteger()
            .Subtract(1);
          if (bigDigitCount.compareTo(other.getExponent()) < 0) {
            // Numerator's digit count minus 1 is less than the other's
            // exponent,
            // and other's exponent is positive, so this value's absolute
            // value is less
            return this.isNegative() ? 1 : -1;
          }
        }
      }
      // Convert to rational number and use usual rational number
      // comparison
      ERational otherRational = ERational.FromEDecimal(other);
      return this.CompareToValue(otherRational);
    }

    /**
     * Returns a number with the same value as this one, but copying the sign
     * (positive or negative) of another number.
     * @param other A number whose sign will be copied.
     * @return An arbitrary-precision rational number.
     * @throws NullPointerException The parameter {@code other} is null.
     */
    public ERational CopySign(ERational other) {
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
     * Divides this arbitrary-precision rational number by another
     * arbitrary-precision rational number and returns the result.
     * @param otherValue An arbitrary-precision rational number.
     * @return The result of dividing this arbitrary-precision rational number by
     * another arbitrary-precision rational number.
     * @throws NullPointerException The parameter {@code otherValue} is null.
     */
    public ERational Divide(ERational otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.IsSignalingNaN()) {
        return CreateNaN(this.getUnsignedNumerator(), false, this.isNegative());
      }
      if (otherValue.IsSignalingNaN()) {
        return CreateNaN(
            otherValue.getUnsignedNumerator(),
            false,
            otherValue.isNegative());
      }
      if (this.IsQuietNaN()) {
        return this;
      }
      if (otherValue.IsQuietNaN()) {
        return otherValue;
      }
      boolean resultNeg = this.isNegative() ^ otherValue.isNegative();
      if (this.IsInfinity()) {
        return otherValue.IsInfinity() ? NaN : (resultNeg ? NegativeInfinity :
            PositiveInfinity);
      }
      if (otherValue.IsInfinity()) {
        return resultNeg ? NegativeZero : Zero;
      }
      if (otherValue.isZero()) {
        return this.isZero() ? NaN : (resultNeg ? NegativeInfinity :
            PositiveInfinity);
      }
      if (this.isZero()) {
        return resultNeg ? NegativeZero : Zero;
      }
      EInteger ad = this.getNumerator().Multiply(otherValue.getDenominator());
      EInteger bc = this.getDenominator().Multiply(otherValue.getNumerator());
      return Create(ad, bc);
    }

    /**
     * Determines whether this object's numerator, denominator, and properties are
     * equal to those of another object and that other object is an
     * arbitrary-precision rational number. Not-a-number values are considered
     * equal if the rest of their properties are equal. This is not the same as
     * value equality. Notably, two ERationals with the same value, but of which
     * one is in lowest terms and the other is not, are compared as unequal by this
     * method (example: 1/2 vs. 5/10).
     * @param obj The parameter {@code obj} is an arbitrary object.
     * @return {@code true} if the objects are equal; otherwise, {@code false}. In
     * this method, two objects are not equal if they don't have the same type or
     * if one is null and the other isn't.
     */
    @Override public boolean equals(Object obj) {
      ERational other = ((obj instanceof ERational) ? (ERational)obj : null);
      return (
          other != null) && (
          (((
            this.unsignedNumerator) == null) ? ((other.unsignedNumerator) == null) : (
            this.unsignedNumerator).equals(other.unsignedNumerator)) && (((
            this.denominator) == null) ? ((other.denominator) == null) : (
            this.denominator).equals(other.denominator)) && this.flags == other.flags);
    }

    /**
     * Determines whether this object's numerator, denominator, and properties are
     * equal to those of another object. Not-a-number values are considered equal
     * if the rest of their properties are equal.
     * @param other An arbitrary-precision rational number to compare to.
     * @return Either {@code true} or {@code false}.
     */
    public boolean equals(ERational other) {
      return this.equals((Object)other);
    }

    /**
     * Returns the hash code for this instance. No application or process IDs are
     * used in the hash code calculation.
     * @return A 32-bit signed integer.
     */
    @Override public int hashCode() {
      int valueHashCode = 1857066527;
      {
        if (this.unsignedNumerator != null) {
          valueHashCode += 1857066539 * this.unsignedNumerator.hashCode();
        }
        if (this.denominator != null) {
          valueHashCode += 1857066551 * this.denominator.hashCode();
        }
        valueHashCode += 1857066623 * this.flags;
      }
      return valueHashCode;
    }

    /**
     * Gets a value indicating whether this object's value is infinity.
     * @return {@code true} if this object's value is infinity; otherwise, {@code
     * false}.
     */
    public boolean IsInfinity() {
      return (this.flags & BigNumberFlags.FlagInfinity) != 0;
    }

    /**
     * Returns whether this object is a not-a-number value.
     * @return {@code true} if this object is a not-a-number value; otherwise,
     * {@code false}.
     */
    public boolean IsNaN() {
      return (this.flags & BigNumberFlags.FlagNaN) != 0;
    }

    /**
     * Returns whether this object is negative infinity.
     * @return {@code true} if this object is negative infinity; otherwise, {@code
     * false}.
     */
    public boolean IsNegativeInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
            BigNumberFlags.FlagNegative)) ==
        (BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);
    }

    /**
     * Returns whether this object is positive infinity.
     * @return {@code true} if this object is positive infinity; otherwise, {@code
     * false}.
     */
    public boolean IsPositiveInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
            BigNumberFlags.FlagNegative)) == BigNumberFlags.FlagInfinity;
    }

    /**
     * Returns whether this object is a quiet not-a-number value.
     * @return {@code true} if this object is a quiet not-a-number value;
     * otherwise, {@code false}.
     */
    public boolean IsQuietNaN() {
      return (this.flags & BigNumberFlags.FlagQuietNaN) != 0;
    }

    /**
     * Returns whether this object is a signaling not-a-number value (which causes
     * an error if the value is passed to any arithmetic operation in this class).
     * @return {@code true} if this object is a signaling not-a-number value (which
     * causes an error if the value is passed to any arithmetic operation in this
     * class); otherwise, {@code false}.
     */
    public boolean IsSignalingNaN() {
      return (this.flags & BigNumberFlags.FlagSignalingNaN) != 0;
    }

    /**
     * Multiplies this arbitrary-precision rational number by another
     * arbitrary-precision rational number and returns the result.
     * @param otherValue An arbitrary-precision rational number.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * rational number times another arbitrary-precision rational number.
     * @throws NullPointerException The parameter {@code otherValue} is null.
     */
    public ERational Multiply(ERational otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.IsSignalingNaN()) {
        return CreateNaN(this.getUnsignedNumerator(), false, this.isNegative());
      }
      if (otherValue.IsSignalingNaN()) {
        return CreateNaN(
            otherValue.getUnsignedNumerator(),
            false,
            otherValue.isNegative());
      }
      if (this.IsQuietNaN()) {
        return this;
      }
      if (otherValue.IsQuietNaN()) {
        return otherValue;
      }
      boolean resultNeg = this.isNegative() ^ otherValue.isNegative();
      if (this.IsInfinity()) {
        return otherValue.isZero() ? NaN : (resultNeg ? NegativeInfinity :
            PositiveInfinity);
      }
      if (otherValue.IsInfinity()) {
        return this.isZero() ? NaN : (resultNeg ? NegativeInfinity :
            PositiveInfinity);
      }
      EInteger ac = this.getNumerator().Multiply(otherValue.getNumerator());
      EInteger bd = this.getDenominator().Multiply(otherValue.getDenominator());
      return ac.isZero() ? (resultNeg ? NegativeZero : Zero) :
        Create(ac, bd);
    }

    /**
     * Returns a rational number with the same value as this one but with the sign
     * reversed.
     * @return An arbitrary-precision rational number.
     */
    public ERational Negate() {
      return new ERational(
          this.unsignedNumerator,
          this.denominator,
          (byte)(this.flags ^ BigNumberFlags.FlagNegative));
    }

    /**
     * Returns the remainder that would result when this arbitrary-precision
     * rational number is divided by another arbitrary-precision rational number.
     * @param otherValue An arbitrary-precision rational number.
     * @return The remainder that would result when this arbitrary-precision
     * rational number is divided by another arbitrary-precision rational number.
     * @throws NullPointerException The parameter {@code otherValue} is null.
     */
    public ERational Remainder(ERational otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.IsSignalingNaN()) {
        return CreateNaN(this.getUnsignedNumerator(), false, this.isNegative());
      }
      if (otherValue.IsSignalingNaN()) {
        return CreateNaN(
            otherValue.getUnsignedNumerator(),
            false,
            otherValue.isNegative());
      }
      if (this.IsQuietNaN()) {
        return this;
      }
      if (otherValue.IsQuietNaN()) {
        return otherValue;
      }
      if (this.IsInfinity()) {
        return NaN;
      }
      if (otherValue.IsInfinity()) {
        return this;
      }
      if (otherValue.isZero()) {
        return NaN;
      }
      if (this.isZero()) {
        return this;
      }
      EInteger ad = this.getNumerator().Multiply(otherValue.getDenominator());
      EInteger bc = this.getDenominator().Multiply(otherValue.getNumerator());
      EInteger quo = ad.Divide(bc); // Find the integer quotient
      EInteger tnum = quo.Multiply(otherValue.getNumerator());
      EInteger tden = otherValue.getDenominator();
      EInteger thisDen = this.getDenominator();
      ad = this.getNumerator().Multiply(tden);
      bc = thisDen.Multiply(tnum);
      tden = tden.Multiply(thisDen);
      ad = ad.Subtract(bc);
      return Create(ad, tden);
    }

    /**
     * Subtracts an arbitrary-precision rational number from this
     * arbitrary-precision rational number and returns the result.
     * @param otherValue An arbitrary-precision rational number.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision rational number minus another arbitrary-precision
     * rational number.
     * @throws NullPointerException The parameter {@code otherValue} is null.
     */
    public ERational Subtract(ERational otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.IsSignalingNaN()) {
        return CreateNaN(this.getUnsignedNumerator(), false, this.isNegative());
      }
      if (otherValue.IsSignalingNaN()) {
        return CreateNaN(
            otherValue.getUnsignedNumerator(),
            false,
            otherValue.isNegative());
      }
      if (this.IsQuietNaN()) {
        return this;
      }
      if (otherValue.IsQuietNaN()) {
        return otherValue;
      }
      if (this.IsInfinity()) {
        if (otherValue.IsInfinity()) {
          return (this.isNegative() != otherValue.isNegative()) ?
            (this.isNegative() ? PositiveInfinity : NegativeInfinity) : NaN;
        }
        return this.isNegative() ? PositiveInfinity : NegativeInfinity;
      }
      if (otherValue.IsInfinity()) {
        return otherValue.isNegative() ? PositiveInfinity : NegativeInfinity;
      }
      EInteger ad = this.getNumerator().Multiply(otherValue.getDenominator());
      EInteger bc = this.getDenominator().Multiply(otherValue.getNumerator());
      EInteger bd = this.getDenominator().Multiply(otherValue.getDenominator());
      ad = ad.Subtract(bc);
      return ERational.Create(ad, bd);
    }

    /**
     * Converts this value to a 64-bit floating-point number. The half-even
     * rounding mode is used.
     * @return The closest 64-bit floating-point number to this value. The return
     * value can be positive infinity or negative infinity if this value exceeds
     * the range of a 64-bit floating point number.
     */
    public double ToDouble() {
      if (!this.isFinite()) {
        return this.ToEFloat(EContext.Binary64).ToDouble();
      }
      if (this.isNegative() && this.isZero()) {
        return EFloat.NegativeZero.ToDouble();
      }
      return EFloat.FromEInteger(this.getNumerator())
        .Divide(EFloat.FromEInteger(this.getDenominator()), EContext.Binary64)
        .ToDouble();
    }

    /**
     * <p>Converts this value to its closest equivalent as a 64-bit floating-point
     * number, expressed as an integer in the IEEE 754 binary64 format. The
     * half-even rounding mode is used. </p> <p>If this value is a NaN, sets the
     * high bit of the 64-bit floating point number's significand area for a quiet
     * NaN, and clears it for a signaling NaN. Then the other bits of the
     * significand area are set to the lowest bits of this object's unsigned
     * significand, and the next-highest bit of the significand area is set if
     * those bits are all zeros and this is a signaling NaN.</p>
     * @return The closest 64-bit binary floating-point number to this value,
     * expressed as an integer in the IEEE 754 binary64 format. The return value
     * can be positive infinity or negative infinity if this value exceeds the
     * range of a 64-bit floating point number.
     */
    public long ToDoubleBits() {
      if (!this.isFinite()) {
        return this.ToEFloat(EContext.Binary64).ToDoubleBits();
      }
      if (this.isNegative() && this.isZero()) {
        return EFloat.NegativeZero.ToDoubleBits();
      }
      return EFloat.FromEInteger(this.getNumerator())
        .Divide(EFloat.FromEInteger(this.getDenominator()), EContext.Binary64)
        .ToDoubleBits();
    }

    /**
     * <p>Converts this value to its closest equivalent as 32-bit floating-point
     * number, expressed as an integer in the IEEE 754 binary32 format. The
     * half-even rounding mode is used. </p> <p>If this value is a NaN, sets the
     * high bit of the 32-bit floating point number's significand area for a quiet
     * NaN, and clears it for a signaling NaN. Then the other bits of the
     * significand area are set to the lowest bits of this object's unsigned
     * significand, and the next-highest bit of the significand area is set if
     * those bits are all zeros and this is a signaling NaN.</p>
     * @return The closest 32-bit binary floating-point number to this value,
     * expressed as an integer in the IEEE 754 binary32 format. The return value
     * can be positive infinity or negative infinity if this value exceeds the
     * range of a 32-bit floating point number.
     */
    public int ToSingleBits() {
      if (!this.isFinite()) {
        return this.ToEFloat(EContext.Binary32).ToSingleBits();
      }
      if (this.isNegative() && this.isZero()) {
        return EFloat.NegativeZero.ToSingleBits();
      }
      return EFloat.FromEInteger(this.getNumerator())
        .Divide(EFloat.FromEInteger(this.getDenominator()), EContext.Binary32)
        .ToSingleBits();
    }

    /**
     * <p>Converts this value to its closest equivalent as a binary floating-point
     * number, expressed as an integer in the IEEE 754 binary16 format (also known
     * as a "half-precision" floating-point number). The half-even rounding mode is
     * used. </p> <p>If this value is a NaN, sets the high bit of the binary16
     * number's significand area for a quiet NaN, and clears it for a signaling
     * NaN. Then the other bits of the significand area are set to the lowest bits
     * of this object's unsigned significand, and the next-highest bit of the
     * significand area is set if those bits are all zeros and this is a signaling
     * NaN.</p>
     * @return The closest binary floating-point number to this value, expressed as
     * an integer in the IEEE 754 binary16 format. The return value can be positive
     * infinity or negative infinity if this value exceeds the range of a
     * floating-point number in the binary16 format.
     */
    public short ToHalfBits() {
      if (!this.isFinite()) {
        return this.ToEFloat(EContext.Binary16).ToHalfBits();
      }
      if (this.isNegative() && this.isZero()) {
        return EFloat.NegativeZero.ToHalfBits();
      }
      return EFloat.FromEInteger(this.getNumerator())
        .Divide(EFloat.FromEInteger(this.getDenominator()), EContext.Binary16)
        .ToHalfBits();
    }

    /**
     * Converts this value to its form in lowest terms. For example, (8/4) becomes
     * (4/1).
     * @return An arbitrary-precision rational with the same value as this one but
     * in lowest terms. Returns this object if it is infinity or NaN. Returns
     * ERational.NegativeZero if this object is a negative zero. Returns
     * ERational.Zero if this object is a positive zero.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN).
     */
    public ERational ToLowestTerms() {
      if (!this.isFinite()) {
        return this;
      }
      if (this.isZero()) {
        return this.isNegative() ? NegativeZero : Zero;
      }
      EInteger num = this.getNumerator();
      EInteger den = this.getDenominator();
      EInteger gcd = num.Abs().Gcd(den);
      return Create(num.Divide(gcd), den.Divide(gcd));
    }

    /**
     * Converts this value to an arbitrary-precision integer by dividing the
     * numerator by the denominator, discarding its fractional part, and checking
     * whether the resulting integer overflows the given signed bit count.
     * @param maxBitLength The maximum number of signed bits the integer can have.
     * The integer's value may not be less than -(2^maxBitLength) or greater than
     * (2^maxBitLength) - 1.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN), or this number's value, once converted to an integer by dividing the
     * numerator by the denominator and discarding its fractional part, is less
     * than -(2^maxBitLength) or greater than (2^maxBitLength) - 1.
     */
    public EInteger ToSizedEInteger(int maxBitLength) {
      if (maxBitLength < 0) {
        throw new IllegalArgumentException("maxBitLength (" + maxBitLength + ") is" +
          "\u0020not greater or equal to 0");
      }
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      EInteger unum = this.getUnsignedNumerator();
      EInteger uden = this.getDenominator();
      if (unum.compareTo(uden) < 0) {
        return EInteger.FromInt32(0);
      }
      EInteger numBits = unum.GetUnsignedBitLengthAsEInteger();
      EInteger denBits = uden.GetUnsignedBitLengthAsEInteger();
      if (numBits.Subtract(2).Subtract(denBits).compareTo(maxBitLength) >
0) {
        throw new ArithmeticException("Value out of range");
      }
      unum = this.ToEInteger();
      if (unum.GetSignedBitLengthAsInt64() > maxBitLength) {
        throw new ArithmeticException("Value out of range");
      }
      return unum;
    }

    /**
     * Converts this value to an arbitrary-precision integer, only if this number's
     * value is an exact integer and that integer does not overflow the given
     * signed bit count.
     * @param maxBitLength The maximum number of signed bits the integer can have.
     * The integer's value may not be less than -(2^maxBitLength) or greater than
     * (2^maxBitLength) - 1.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN), or this number's value as an integer is less than -(2^maxBitLength)
     * or greater than (2^maxBitLength) - 1.
     * @throws ArithmeticException This object's value is not an exact integer.
     */
    public EInteger ToSizedEIntegerIfExact(int maxBitLength) {
      if (maxBitLength < 0) {
        throw new IllegalArgumentException("maxBitLength (" + maxBitLength + ") is" +
          "\u0020not greater or equal to 0");
      }
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      EInteger unum = this.getUnsignedNumerator();
      EInteger uden = this.getDenominator();
      if (unum.isZero()) {
        return EInteger.FromInt32(0);
      }
      if (unum.compareTo(uden) < 0) {
        throw new ArithmeticException("Value is not an integer");
      }
      EInteger numBits = unum.GetUnsignedBitLengthAsEInteger();
      EInteger denBits = uden.GetUnsignedBitLengthAsEInteger();
      if (numBits.Subtract(2).Subtract(denBits).compareTo(maxBitLength) >
0) {
        throw new ArithmeticException("Value out of range");
      }
      unum = this.ToEIntegerIfExact();
      if (unum.GetSignedBitLengthAsInt64() > maxBitLength) {
        throw new ArithmeticException("Value out of range");
      }
      return unum;
    }

    /**
     * Converts this value to an arbitrary-precision integer by dividing the
     * numerator by the denominator and discarding the fractional part of the
     * result.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN).
     */
    public EInteger ToEInteger() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.getNumerator().Divide(this.getDenominator());
    }

    /**
     * Converts this value to an arbitrary-precision integer, checking whether the
     * value is an exact integer.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN).
     * @deprecated Renamed to ToEIntegerIfExact.
 */
@Deprecated
    public EInteger ToEIntegerExact() {
      return this.ToEIntegerIfExact();
    }

    /**
     * Converts this value to an arbitrary-precision integer, checking whether the
     * value is an exact integer.
     * @return An arbitrary-precision integer.
     * @throws ArithmeticException This object's value is infinity or not-a-number
     * (NaN).
     */
    public EInteger ToEIntegerIfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      if (this.denominator.isEvenNumber() &&
           !this.unsignedNumerator.isEvenNumber()) {
        // Even denominator, odd numerator, so not an integer
        throw new ArithmeticException("Value is not an integer");
      }
      EInteger rem;
      EInteger quo;
      EInteger[] divrem = this.getNumerator().DivRem(this.getDenominator());
      quo = divrem[0];
      rem = divrem[1];
      if (!rem.isZero()) {
        throw new ArithmeticException("Value is not an integer");
      }
      return quo;
    }

    /**
     * Converts this rational number to an arbitrary-precision decimal number.
     * @return The exact value of the rational number, or not-a-number (NaN) if the
     * result can't be exact because it has a nonterminating decimal expansion.
     */
    public EDecimal ToEDecimal() {
      return this.ToEDecimal(null);
    }

    /**
     * Converts this rational number to an arbitrary-precision decimal number and
     * rounds the result to the given precision.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. If HasFlags of the context is true, will
     * also store the flags resulting from the operation (the flags are in addition
     * to the pre-existing flags). Can be null, in which case the precision is
     * unlimited and no rounding is needed.
     * @return The value of the rational number, rounded to the given precision.
     * Returns not-a-number (NaN) if the context is null and the result can't be
     * exact because it has a nonterminating decimal expansion.
     */
    public EDecimal ToEDecimal(EContext ctx) {
      if (this.IsNaN()) {
        return EDecimal.CreateNaN(
            this.getUnsignedNumerator(),
            this.IsSignalingNaN(),
            this.isNegative(),
            ctx);
      }
      if (this.IsPositiveInfinity()) {
        return EDecimal.PositiveInfinity.RoundToPrecision(ctx);
      }
      if (this.IsNegativeInfinity()) {
        return EDecimal.NegativeInfinity.RoundToPrecision(ctx);
      }
      EDecimal ef = (this.isNegative() && this.isZero()) ?
        EDecimal.NegativeZero : EDecimal.FromEInteger(this.getNumerator());
      return ef.Divide(EDecimal.FromEInteger(this.getDenominator()), ctx);
    }

    /**
     * Converts this rational number to an arbitrary-precision decimal number, but
     * if the result would have a nonterminating decimal expansion, rounds that
     * result to the given precision.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only if the
     * exact result would have a nonterminating decimal expansion. If HasFlags of
     * the context is true, will also store the flags resulting from the operation
     * (the flags are in addition to the pre-existing flags). Can be null, in which
     * case the precision is unlimited and no rounding is needed.
     * @return The exact value of the rational number if possible; otherwise, the
     * rounded version of the result if a context is given. Returns not-a-number
     * (NaN) if the context is null and the result can't be exact because it has a
     * nonterminating decimal expansion.
     */
    public EDecimal ToEDecimalExactIfPossible(EContext
      ctx) {
      if (ctx == null) {
        return this.ToEDecimal(null);
      }
      if (this.IsNaN()) {
        return EDecimal.CreateNaN(
            this.getUnsignedNumerator(),
            this.IsSignalingNaN(),
            this.isNegative(),
            ctx);
      }
      if (this.IsPositiveInfinity()) {
        return EDecimal.PositiveInfinity.RoundToPrecision(ctx);
      }
      if (this.IsNegativeInfinity()) {
        return EDecimal.NegativeInfinity.RoundToPrecision(ctx);
      }
      if (this.isNegative() && this.isZero()) {
        return EDecimal.NegativeZero;
      }
      EInteger num = this.getNumerator();
      EInteger den = this.getDenominator();
      EDecimal valueEdNum = (this.isNegative() && this.isZero()) ?
        EDecimal.NegativeZero : EDecimal.FromEInteger(num);
      EDecimal valueEdDen = EDecimal.FromEInteger(den);
      EDecimal ed = valueEdNum.Divide(valueEdDen, null);
      if (ed.IsNaN()) {
        // Result would be inexact, try again using the precision context
        ed = valueEdNum.Divide(valueEdDen, ctx);
      }
      return ed;
    }

    /**
     * Converts this rational number to an arbitrary-precision decimal number.
     * @return The exact value of the rational number, or not-a-number (NaN) if the
     * result can't be exact because it has a nonterminating decimal expansion.
     * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal() {
      return this.ToEDecimal();
    }

    /**
     * Converts this rational number to an arbitrary-precision decimal number and
     * rounds the result to the given precision.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. If HasFlags of the context is true, will
     * also store the flags resulting from the operation (the flags are in addition
     * to the pre-existing flags). Can be null, in which case the precision is
     * unlimited and no rounding is needed.
     * @return The value of the rational number, rounded to the given precision.
     * Returns not-a-number (NaN) if the context is null and the result can't be
     * exact because it has a nonterminating decimal expansion.
     * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal(EContext ctx) {
      return this.ToEDecimal(ctx);
    }

    /**
     * Converts this rational number to an arbitrary-precision decimal number, but
     * if the result would have a nonterminating decimal expansion, rounds that
     * result to the given precision.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only if the
     * exact result would have a nonterminating decimal expansion. If HasFlags of
     * the context is true, will also store the flags resulting from the operation
     * (the flags are in addition to the pre-existing flags). Can be null, in which
     * case the precision is unlimited and no rounding is needed.
     * @return The exact value of the rational number if possible; otherwise, the
     * rounded version of the result if a context is given. Returns not-a-number
     * (NaN) if the context is null and the result can't be exact because it has a
     * nonterminating decimal expansion.
     * @deprecated Renamed to ToEDecimalExactIfPossible.
 */
@Deprecated
    public EDecimal ToExtendedDecimalExactIfPossible(EContext ctx) {
      return this.ToEDecimalExactIfPossible(ctx);
    }

    /**
     * Converts this rational number to a binary floating-point number.
     * @return The exact value of the rational number, or not-a-number (NaN) if the
     * result can't be exact because it has a nonterminating binary expansion.
     */
    public EFloat ToEFloat() {
      return this.ToEFloat(null);
    }

    /**
     * Converts this rational number to a binary floating-point number and rounds
     * that result to the given precision.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. If HasFlags of the context is true, will
     * also store the flags resulting from the operation (the flags are in addition
     * to the pre-existing flags). Can be null, in which case the precision is
     * unlimited and no rounding is needed.
     * @return The value of the rational number, rounded to the given precision.
     * Returns not-a-number (NaN) if the context is null and the result can't be
     * exact because it has a nonterminating binary expansion.
     */
    public EFloat ToEFloat(EContext ctx) {
      if (this.IsNaN()) {
        return EFloat.CreateNaN(
            this.getUnsignedNumerator(),
            this.IsSignalingNaN(),
            this.isNegative(),
            ctx);
      }
      if (this.IsPositiveInfinity()) {
        return EFloat.PositiveInfinity.RoundToPrecision(ctx);
      }
      if (this.IsNegativeInfinity()) {
        return EFloat.NegativeInfinity.RoundToPrecision(ctx);
      }
      EFloat ef = (this.isNegative() && this.isZero()) ?
        EFloat.NegativeZero : EFloat.FromEInteger(this.getNumerator());
      return ef.Divide(EFloat.FromEInteger(this.getDenominator()), ctx);
    }

    /**
     * Converts this rational number to a binary floating-point number, but if the
     * result would have a nonterminating binary expansion, rounds that result to
     * the given precision.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only if the
     * exact result would have a nonterminating binary expansion. If HasFlags of
     * the context is true, will also store the flags resulting from the operation
     * (the flags are in addition to the pre-existing flags). Can be null, in which
     * case the precision is unlimited and no rounding is needed.
     * @return The exact value of the rational number if possible; otherwise, the
     * rounded version of the result if a context is given. Returns not-a-number
     * (NaN) if the context is null and the result can't be exact because it has a
     * nonterminating binary expansion.
     */
    public EFloat ToEFloatExactIfPossible(EContext ctx) {
      if (ctx == null) {
        return this.ToEFloat(null);
      }
      if (this.IsNaN()) {
        return EFloat.CreateNaN(
            this.getUnsignedNumerator(),
            this.IsSignalingNaN(),
            this.isNegative(),
            ctx);
      }
      if (this.IsPositiveInfinity()) {
        return EFloat.PositiveInfinity.RoundToPrecision(ctx);
      }
      if (this.IsNegativeInfinity()) {
        return EFloat.NegativeInfinity.RoundToPrecision(ctx);
      }
      if (this.isZero()) {
        return this.isNegative() ? EFloat.NegativeZero :
          EFloat.Zero;
      }
      EFloat valueEdNum = (this.isNegative() && this.isZero()) ?
        EFloat.NegativeZero : EFloat.FromEInteger(this.getNumerator());
      EFloat valueEdDen = EFloat.FromEInteger(this.getDenominator());
      EFloat ed = valueEdNum.Divide(valueEdDen, null);
      if (ed.IsNaN()) {
        // Result would be inexact, try again using the precision context
        ed = valueEdNum.Divide(valueEdDen, ctx);
      }
      return ed;
    }

    /**
     * Converts this rational number to a binary floating-point number.
     * @return The exact value of the rational number, or not-a-number (NaN) if the
     * result can't be exact because it has a nonterminating binary expansion.
     * @deprecated Renamed to ToEFloat.
 */
@Deprecated
    public EFloat ToExtendedFloat() {
      return this.ToEFloat();
    }

    /**
     * Converts this rational number to a binary floating-point number and rounds
     * that result to the given precision.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. If HasFlags of the context is true, will
     * also store the flags resulting from the operation (the flags are in addition
     * to the pre-existing flags). Can be null, in which case the precision is
     * unlimited and no rounding is needed.
     * @return The value of the rational number, rounded to the given precision.
     * Returns not-a-number (NaN) if the context is null and the result can't be
     * exact because it has a nonterminating binary expansion.
     * @deprecated Renamed to ToEFloat.
 */
@Deprecated
    public EFloat ToExtendedFloat(EContext ctx) {
      return this.ToEFloat(ctx);
    }

    /**
     * Converts this rational number to a binary floating-point number, but if the
     * result would have a nonterminating binary expansion, rounds that result to
     * the given precision.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only if the
     * exact result would have a nonterminating binary expansion. If HasFlags of
     * the context is true, will also store the flags resulting from the operation
     * (the flags are in addition to the pre-existing flags). Can be null, in which
     * case the precision is unlimited and no rounding is needed.
     * @return The exact value of the rational number if possible; otherwise, the
     * rounded version of the result if a context is given. Returns not-a-number
     * (NaN) if the context is null and the result can't be exact because it has a
     * nonterminating binary expansion.
     * @deprecated Renamed to ToEFloatExactIfPossible.
 */
@Deprecated
    public EFloat ToExtendedFloatExactIfPossible(EContext ctx) {
      return this.ToEFloatExactIfPossible(ctx);
    }

    /**
     * Converts this value to a 32-bit binary floating-point number. The half-even
     * rounding mode is used.
     * @return The closest 32-bit binary floating-point number to this value. The
     * return value can be positive infinity or negative infinity if this value
     * exceeds the range of a 32-bit floating point number.
     */
    public float ToSingle() {
      if (!this.isFinite()) {
        return this.ToEFloat(EContext.Binary32).ToSingle();
      }
      if (this.isNegative() && this.isZero()) {
        return EFloat.NegativeZero.ToSingle();
      }
      return EFloat.FromEInteger(this.getNumerator())
        .Divide(EFloat.FromEInteger(this.getDenominator()), EContext.Binary32)
        .ToSingle();
    }

    /**
     * Converts this object to a text string.
     * @return A string representation of this object. If this object's value is
     * infinity or not-a-number, the result is the analogous return value of the
     * {@code EDecimal.toString} method. Otherwise, the return value has the
     * following form: {@code [-]numerator.Divide(denominator)}.
     */
    @Override public String toString() {
      if (!this.isFinite()) {
        if (this.IsSignalingNaN()) {
          if (this.unsignedNumerator.isValueZero()) {
            return this.isNegative() ? "-sNaN" : "sNaN";
          }
          return this.isNegative() ? "-sNaN" + this.unsignedNumerator :
            "sNaN" + this.unsignedNumerator;
        }
        if (this.IsQuietNaN()) {
          if (this.unsignedNumerator.isValueZero()) {
            return this.isNegative() ? "-NaN" : "NaN";
          }
          return this.isNegative() ? "-NaN" + this.unsignedNumerator :
            "NaN" + this.unsignedNumerator;
        }
        if (this.IsInfinity()) {
          return this.isNegative() ? "-Infinity" : "Infinity";
        }
      }
      return (this.unsignedNumerator.isValueZero() && this.isNegative()) ? ("-0/" +
          this.getDenominator()) : (this.getNumerator() + "/" + this.getDenominator());
    }

    /**
     * Adds one to an arbitrary-precision rational number.
     * @return The given arbitrary-precision rational number plus one.
     */
    public ERational Increment() {
      return this.Add(FromInt32(1));
    }

    /**
     * Subtracts one from an arbitrary-precision rational number.
     * @return The given arbitrary-precision rational number minus one.
     */
    public ERational Decrement() {
      return this.Subtract(FromInt32(1));
    }

    /**
     * Adds this arbitrary-precision rational number and a 32-bit signed integer
     * and returns the result.
     * @param v A 32-bit signed integer.
     * @return The sum of the two numbers, that is, this arbitrary-precision
     * rational number plus a 32-bit signed integer.
     */
    public ERational Add(int v) {
      return this.Add(FromInt32(v));
    }

    /**
     * Subtracts a 32-bit signed integer from this arbitrary-precision rational
     * number and returns the result.
     * @param v The parameter {@code v} is a 32-bit signed integer.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision rational number minus a 32-bit signed integer.
     */
    public ERational Subtract(int v) {
      return this.Subtract(FromInt32(v));
    }

    /**
     * Multiplies this arbitrary-precision rational number by a 32-bit signed
     * integer and returns the result.
     * @param v The parameter {@code v} is a 32-bit signed integer.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * rational number times a 32-bit signed integer.
     */
    public ERational Multiply(int v) {
      return this.Multiply(FromInt32(v));
    }

    /**
     * Divides this arbitrary-precision rational number by a 32-bit signed integer
     * and returns the result.
     * @param v The parameter {@code v} is a 32-bit signed integer.
     * @return The result of dividing this arbitrary-precision rational number by a
     * 32-bit signed integer.
     * @throws ArithmeticException The parameter {@code v} is zero.
     */
    public ERational Divide(int v) {
      return this.Divide(FromInt32(v));
    }

    /**
     * Returns the remainder that would result when this arbitrary-precision
     * rational number is divided by a 32-bit signed integer.
     * @param v The divisor.
     * @return The remainder that would result when this arbitrary-precision
     * rational number is divided by a 32-bit signed integer.
     * @throws IllegalArgumentException The parameter {@code v} is zero.
     */
    public ERational Remainder(int v) {
      return this.Remainder(FromInt32(v));
    }

    /**
     * Adds this arbitrary-precision rational number and a 64-bit signed integer
     * and returns the result.
     * @param v A 64-bit signed integer.
     * @return The sum of the two numbers, that is, this arbitrary-precision
     * rational number plus a 64-bit signed integer.
     */
    public ERational Add(long v) {
      return this.Add(FromInt64(v));
    }

    /**
     * Subtracts a 64-bit signed integer from this arbitrary-precision rational
     * number and returns the result.
     * @param v The parameter {@code v} is a 64-bit signed integer.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision rational number minus a 64-bit signed integer.
     */
    public ERational Subtract(long v) {
      return this.Subtract(FromInt64(v));
    }

    /**
     * Multiplies this arbitrary-precision rational number by a 64-bit signed
     * integer and returns the result.
     * @param v The parameter {@code v} is a 64-bit signed integer.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * rational number times a 64-bit signed integer.
     */
    public ERational Multiply(long v) {
      return this.Multiply(FromInt64(v));
    }

    /**
     * Divides this arbitrary-precision rational number by a 64-bit signed integer
     * and returns the result.
     * @param v The parameter {@code v} is a 64-bit signed integer.
     * @return The result of dividing this arbitrary-precision rational number by a
     * 64-bit signed integer.
     * @throws ArithmeticException The parameter {@code v} is zero.
     */
    public ERational Divide(long v) {
      return this.Divide(FromInt64(v));
    }

    /**
     * Returns the remainder that would result when this arbitrary-precision
     * rational number is divided by a 64-bit signed integer.
     * @param v The divisor.
     * @return The remainder that would result when this arbitrary-precision
     * rational number is divided by a 64-bit signed integer.
     * @throws IllegalArgumentException The parameter {@code v} is zero.
     */
    public ERational Remainder(long v) {
      return this.Remainder(FromInt64(v));
    }

    // Begin integer conversions
    private void CheckTrivialOverflow(int maxBits) {
      if (this.isZero()) {
        return;
      }
      if (!this.isFinite()) {
        throw new ArithmeticException("Value out of range");
      }
      EInteger bignum = this.getUnsignedNumerator();
      EInteger bigden = this.getDenominator();
      EInteger numbits = bignum.GetUnsignedBitLengthAsEInteger();
      EInteger denbits = bigden.GetUnsignedBitLengthAsEInteger();
      if (numbits.compareTo(denbits.Add(1).Add(maxBits)) > 0) {
        throw new ArithmeticException("Value out of range");
      }
    }

    /**
     * Converts this number's value to a byte (from 0 to 255) if it can fit in a
     * byte (from 0 to 255) after converting it to an integer by discarding its
     * fractional part.
     * @return This number's value, truncated to a byte (from 0 to 255).
     * @throws ArithmeticException This value is infinity or not-a-number, or the
     * number, once converted to an integer by discarding its fractional part, is
     * less than 0 or greater than 255.
     */
    public byte ToByteChecked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      if (this.isNegative() && !this.isZero() &&
        this.getUnsignedNumerator().compareTo(this.getDenominator()) >= 0) {
        throw new ArithmeticException("Value out of range");
      }
      this.CheckTrivialOverflow(8);
      return this.isZero() ? ((byte)0) : this.ToEInteger().ToByteChecked();
    }

    /**
     * Converts this number's value to an integer (using ToEInteger), and returns
     * the least-significant bits of that integer's two's-complement form as a byte
     * (from 0 to 255).
     * @return This number, converted to a byte (from 0 to 255). Returns 0 if this
     * value is infinity or not-a-number.
     */
    public byte ToByteUnchecked() {
      return this.isFinite() ? this.ToEInteger().ToByteUnchecked() : (byte)0;
    }

    /**
     * Converts this number's value to a byte (from 0 to 255) if it can fit in a
     * byte (from 0 to 255) without rounding to a different numerical value.
     * @return This number's value as a byte (from 0 to 255).
     * @throws ArithmeticException This value is infinity or not-a-number, is not
     * an exact integer, or is less than 0 or greater than 255.
     */
    public byte ToByteIfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      if (this.isNegative() && !this.isZero() &&
        this.getUnsignedNumerator().compareTo(this.getDenominator()) >= 0) {
        throw new ArithmeticException("Value out of range");
      }
      this.CheckTrivialOverflow(8);
      return this.isZero() ? ((byte)0) : this.ToEIntegerIfExact().ToByteChecked();
    }

    /**
     * Converts a byte (from 0 to 255) to an arbitrary-precision rational number.
     * @param inputByte The number to convert as a byte (from 0 to 255).
     * @return This number's value as an arbitrary-precision rational number.
     */
    public static ERational FromByte(byte inputByte) {
      int val = ((int)inputByte) & 0xff;
      return FromInt32(val);
    }

    /**
     * Converts this number's value to a 16-bit signed integer if it can fit in a
     * 16-bit signed integer after converting it to an integer by discarding its
     * fractional part.
     * @return This number's value, truncated to a 16-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, or the
     * number, once converted to an integer by discarding its fractional part, is
     * less than -32768 or greater than 32767.
     */
    public short ToInt16Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(15);
      return this.isZero() ? ((short)0) : this.ToEInteger().ToInt16Checked();
    }

    /**
     * Converts this number's value to an integer by discarding its fractional
     * part, and returns the least-significant bits of its two's-complement form as
     * a 16-bit signed integer.
     * @return This number, converted to a 16-bit signed integer. Returns 0 if this
     * value is infinity or not-a-number.
     */
    public short ToInt16Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt16Unchecked() : (short)0;
    }

    /**
     * Converts this number's value to a 16-bit signed integer if it can fit in a
     * 16-bit signed integer without rounding to a different numerical value.
     * @return This number's value as a 16-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, is not
     * an exact integer, or is less than -32768 or greater than 32767.
     */
    public short ToInt16IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(15);
      return this.isZero() ? ((short)0) :
        this.ToEIntegerIfExact().ToInt16Checked();
    }

    /**
     * Converts a 16-bit signed integer to an arbitrary-precision rational number.
     * @param inputInt16 The number to convert as a 16-bit signed integer.
     * @return This number's value as an arbitrary-precision rational number.
     */
    public static ERational FromInt16(short inputInt16) {
      int val = (int)inputInt16;
      return FromInt32(val);
    }

    /**
     * Converts this number's value to a 32-bit signed integer if it can fit in a
     * 32-bit signed integer after converting it to an integer by discarding its
     * fractional part.
     * @return This number's value, truncated to a 32-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, or the
     * number, once converted to an integer by discarding its fractional part, is
     * less than -2147483648 or greater than 2147483647.
     */
    public int ToInt32Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(31);
      return this.isZero() ? ((int)0) : this.ToEInteger().ToInt32Checked();
    }

    /**
     * Converts this number's value to an integer by discarding its fractional
     * part, and returns the least-significant bits of its two's-complement form as
     * a 32-bit signed integer.
     * @return This number, converted to a 32-bit signed integer. Returns 0 if this
     * value is infinity or not-a-number.
     */
    public int ToInt32Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt32Unchecked() : (int)0;
    }

    /**
     * Converts this number's value to a 32-bit signed integer if it can fit in a
     * 32-bit signed integer without rounding to a different numerical value.
     * @return This number's value as a 32-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, is not
     * an exact integer, or is less than -2147483648 or greater than 2147483647.
     */
    public int ToInt32IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(31);
      return this.isZero() ? ((int)0) : this.ToEIntegerIfExact().ToInt32Checked();
    }

    /**
     * Converts a Boolean value (true or false) to an arbitrary-precision rational
     * number.
     * @param boolValue Either true or false.
     * @return The number 1 if {@code boolValue} is true; otherwise, 0.
     */
    public static ERational FromBoolean(boolean boolValue) {
      return FromInt32(boolValue ? 1 : 0);
    }

    /**
     * Converts a 32-bit signed integer to an arbitrary-precision rational number.
     * @param inputInt32 The number to convert as a 32-bit signed integer.
     * @return This number's value as an arbitrary-precision rational number.
     */
    public static ERational FromInt32(int inputInt32) {
      return FromEInteger(EInteger.FromInt32(inputInt32));
    }

    /**
     * Converts this number's value to a 64-bit signed integer if it can fit in a
     * 64-bit signed integer after converting it to an integer by discarding its
     * fractional part.
     * @return This number's value, truncated to a 64-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, or the
     * number, once converted to an integer by discarding its fractional part, is
     * less than -9223372036854775808 or greater than 9223372036854775807.
     */
    public long ToInt64Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(63);
      return this.isZero() ? 0L : this.ToEInteger().ToInt64Checked();
    }

    /**
     * Converts this number's value to an integer by discarding its fractional
     * part, and returns the least-significant bits of its two's-complement form as
     * a 64-bit signed integer.
     * @return This number, converted to a 64-bit signed integer. Returns 0 if this
     * value is infinity or not-a-number.
     */
    public long ToInt64Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt64Unchecked() : 0L;
    }

    /**
     * Converts this number's value to a 64-bit signed integer if it can fit in a
     * 64-bit signed integer without rounding to a different numerical value.
     * @return This number's value as a 64-bit signed integer.
     * @throws ArithmeticException This value is infinity or not-a-number, is not
     * an exact integer, or is less than -9223372036854775808 or greater than
     * 9223372036854775807.
     */
    public long ToInt64IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(63);
      return this.isZero() ? 0L : this.ToEIntegerIfExact().ToInt64Checked();
    }

    /**
     * Converts an unsigned integer expressed as a 64-bit signed integer to an
     * arbitrary-precision rational number.
     * @param longerValue A 64-bit signed integer. If this value is 0 or greater,
     * the return value will represent it. If this value is less than 0, the return
     * value will store 2^64 plus this value instead.
     * @return An arbitrary-precision rational number. If {@code longerValue} is 0
     * or greater, the return value will represent it. If {@code longerValue} is
     * less than 0, the return value will store 2^64 plus this value instead.
     */
    public static ERational FromInt64AsUnsigned(long longerValue) {
      return longerValue >= 0 ? FromInt64(longerValue) :
           FromEInteger(EInteger.FromInt64AsUnsigned(longerValue));
    }

    /**
     * Converts a 64-bit signed integer to an arbitrary-precision rational number.
     * @param inputInt64 The number to convert as a 64-bit signed integer.
     * @return This number's value as an arbitrary-precision rational number.
     */
    public static ERational FromInt64(long inputInt64) {
      return FromEInteger(EInteger.FromInt64(inputInt64));
    }

    // End integer conversions
  }
