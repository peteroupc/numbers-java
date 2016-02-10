package com.upokecenter.numbers;
/*
Written in 2014 by Peter O.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://upokecenter.dreamhosters.com/articles/donate-now-2/
 */

    /**
     * Arbitrary-precision rational number. This class cannot be inherited. (The
     * "E" stands for "extended", meaning that instances of this class can
     * be values other than numbers proper, such as infinity and
     * not-a-number.) <p><b>Thread safety:</b>Instances of this class are
     * immutable, so they are inherently safe for use by multiple threads.
     * Multiple instances of this object with the same properties are
     * interchangeable, so they should not be compared using the "=="
     * operator (which only checks if each side of the operator is the same
     * instance).</p>
     */
  public final class ERational implements Comparable<ERational> {
    /**
     * A not-a-number value.
     */
    public static final ERational NaN = CreateWithFlags(
EInteger.FromInt32(0),
EInteger.FromInt32(1),
BigNumberFlags.FlagQuietNaN);

    /**
     * Negative infinity, less than any other number.
     */
    public static final ERational NegativeInfinity =
      CreateWithFlags(
EInteger.FromInt32(0),
EInteger.FromInt32(1),
BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);

    /**
     * A rational number for negative zero.
     */
    public static final ERational NegativeZero =
      FromEInteger(EInteger.FromInt32(0)).ChangeSign(false);

    /**
     * The rational number one.
     */
    public static final ERational One = FromEInteger(EInteger.FromInt32(1));

    /**
     * Positive infinity, greater than any other number.
     */
    public static final ERational PositiveInfinity =
      CreateWithFlags(
EInteger.FromInt32(0),
EInteger.FromInt32(1),
BigNumberFlags.FlagInfinity);

    /**
     * A signaling not-a-number value.
     */
    public static final ERational SignalingNaN =
      CreateWithFlags(
EInteger.FromInt32(0),
EInteger.FromInt32(1),
BigNumberFlags.FlagSignalingNaN);

    /**
     * The rational number ten.
     */
    public static final ERational Ten = FromEInteger(EInteger.FromInt32(10));

    /**
     * A rational number for zero.
     */
    public static final ERational Zero = FromEInteger(EInteger.FromInt32(0));

    private EInteger denominator;

    private int flags;
    private EInteger unsignedNumerator;

    /**
     * Initializes a new instance of the <see cref='T:PeterO.Numbers.ERational'/>
     * class.
     * @param numerator An arbitrary-precision integer.
     * @param denominator Another arbitrary-precision integer.
     * @throws java.lang.NullPointerException The parameter {@code numerator} or
     * {@code denominator} is null.
     */
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
      this.flags = (numNegative != denNegative) ? BigNumberFlags.FlagNegative :
           0;
      if (numNegative) {
        numerator = numerator.Negate();
      }
      if (denNegative) {
        denominator = denominator.Negate();
      }

      this.unsignedNumerator = numerator;
      this.denominator = denominator;
    }

    /**
     * Gets this object's denominator.
     * @return This object's denominator.
     */
    public final EInteger getDenominator() {
        return this.denominator;
      }

    /**
     * Gets a value indicating whether this object is finite (not infinity or NaN).
     * @return true if this object is finite (not infinity or NaN), otherwise,
     * false.
     */
    public final boolean isFinite() {
        return !this.IsNaN() && !this.IsInfinity();
      }

    /**
     * Gets a value indicating whether this object's value is negative (including
     * negative zero).
     * @return true if this object's value is negative, otherwise, false.
     */
    public final boolean isNegative() {
        return (this.flags & BigNumberFlags.FlagNegative) != 0;
      }

    /**
     * Gets a value indicating whether this object's value equals 0.
     * @return true if this object's value equals 0, otherwise, false.
     */
    public final boolean isZero() {
        return ((this.flags & (BigNumberFlags.FlagInfinity |
          BigNumberFlags.FlagNaN)) == 0) && this.unsignedNumerator.isZero();
      }

    /**
     * Gets this object's numerator.
     * @return This object's numerator. If this object is a not-a-number value,
     * returns the diagnostic information (which will be negative if this
     * object is negative).
     */
    public final EInteger getNumerator() {
        return this.isNegative() ? ((this.unsignedNumerator).Negate()) :
          this.unsignedNumerator;
      }

    /**
     * Gets the sign of this rational number.
     * @return Zero if this value is zero or negative zero; -1 if this value is
     * less than 0; and 1 if this value is greater than 0.
     */
    public final int signum() {
        return ((this.flags & (BigNumberFlags.FlagInfinity |
          BigNumberFlags.FlagNaN)) != 0) ? (this.isNegative() ? -1 : 1) :
          (this.unsignedNumerator.isZero() ? 0 : (this.isNegative() ? -1 : 1));
      }

    /**
     * Gets this object's numerator with the sign removed.
     * @return This object's numerator. If this object is a not-a-number value,
     * returns the diagnostic information.
     */
    public final EInteger getUnsignedNumerator() {
        return this.unsignedNumerator;
      }

    /**
     * Creates a rational number with the given numerator and denominator.
     * @param numeratorSmall A 32-bit signed integer.
     * @param denominatorSmall A 32-bit signed integer. (2).
     * @return An arbitrary-precision rational number.
     */
    public static ERational Create(
int numeratorSmall,
int denominatorSmall) {
      return Create(EInteger.FromInt32(numeratorSmall), EInteger.FromInt32(denominatorSmall));
    }

    /**
     * Creates a rational number with the given numerator and denominator.
     * @param numerator An arbitrary-precision integer.
     * @param denominator Another arbitrary-precision integer.
     * @return An arbitrary-precision rational number.
     */
    public static ERational Create(
EInteger numerator,
EInteger denominator) {
      return new ERational(numerator, denominator);
    }

    /**
     * Creates a not-a-number arbitrary-precision rational number.
     * @param diag A number to use as diagnostic information associated with this
     * object. If none is needed, should be zero.
     * @return An arbitrary-precision rational number.
     * @throws java.lang.NullPointerException The parameter {@code diag} is null.
     * @throws IllegalArgumentException The parameter {@code diag} is less than 0.
     */
    public static ERational CreateNaN(EInteger diag) {
      return CreateNaN(diag, false, false);
    }

    /**
     * Creates a not-a-number arbitrary-precision rational number.
     * @param diag A number to use as diagnostic information associated with this
     * object. If none is needed, should be zero.
     * @param signaling Whether the return value will be signaling (true) or quiet
     * (false).
     * @param negative Whether the return value is negative.
     * @return An arbitrary-precision rational number.
     * @throws java.lang.NullPointerException The parameter {@code diag} is null.
     * @throws IllegalArgumentException The parameter {@code diag} is less than 0.
     */
    public static ERational CreateNaN(
EInteger diag,
boolean signaling,
boolean negative) {
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
      flags |= signaling ? BigNumberFlags.FlagSignalingNaN :
        BigNumberFlags.FlagQuietNaN;
      ERational er = new ERational(diag, EInteger.FromInt32(0));
      er.flags = flags;
      return er;
    }

    /**
     * Converts a 64-bit floating-point number to a rational number. This method
     * computes the exact value of the floating point number, not an
     * approximation, as is often the case by converting the number to a
     * string.
     * @param flt A 64-bit floating-point number.
     * @return A rational number with the same value as {@code flt}.
     */
    public static ERational FromDouble(double flt) {
      return FromEFloat(EFloat.FromDouble(flt));
    }

    /**
     * Converts an arbitrary-precision decimal number to a rational number.
     * @param ef An arbitrary-precision decimal number.
     * @return An arbitrary-precision rational number.
     * @throws java.lang.NullPointerException The parameter {@code ef} is null.
     * @deprecated Renamed to FromEDecimal.
 */
@Deprecated
    public static ERational FromExtendedDecimal(EDecimal ef) {
      return FromEDecimal(ef);
    }

    /**
     * Not documented yet.
     * @param ef An arbitrary-precision binary float.
     * @return An arbitrary-precision rational number.
     * @throws java.lang.NullPointerException The parameter {@code ef} is null.
     * @deprecated Renamed to FromEFloat.
 */
@Deprecated
    public static ERational FromExtendedFloat(EFloat ef) {
      return FromEFloat(ef);
    }

    /**
     * Converts an arbitrary-precision decimal number to a rational number.
     * @param ef An arbitrary-precision decimal number.
     * @return An arbitrary-precision rational number.
     * @throws java.lang.NullPointerException The parameter {@code ef} is null.
     */
    public static ERational FromEDecimal(EDecimal ef) {
      if (ef == null) {
        throw new NullPointerException("ef");
      }
      if (!ef.isFinite()) {
        ERational er = new ERational(ef.getMantissa(), EInteger.FromInt32(1));
        int flags = 0;
        if (ef.isNegative()) {
          flags |= BigNumberFlags.FlagNegative;
        }
        if (ef.IsInfinity()) {
          flags |= BigNumberFlags.FlagInfinity;
        }
        if (ef.IsSignalingNaN()) {
          flags |= BigNumberFlags.FlagSignalingNaN;
        }
        if (ef.IsQuietNaN()) {
          flags |= BigNumberFlags.FlagQuietNaN;
        }
        er.flags = flags;
        return er;
      }
      EInteger num = ef.getMantissa();
      EInteger exp = ef.getExponent();
      if (exp.isZero()) {
        return FromEInteger(num);
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
      return new ERational(num, den);
    }

    /**
     * Not documented yet.
     * @param ef An arbitrary-precision binary float.
     * @return An arbitrary-precision rational number.
     * @throws java.lang.NullPointerException The parameter {@code ef} is null.
     */
    public static ERational FromEFloat(EFloat ef) {
      if (ef == null) {
        throw new NullPointerException("ef");
      }
      if (!ef.isFinite()) {
        ERational er = new ERational(ef.getMantissa(), EInteger.FromInt32(1));
        int flags = 0;
        if (ef.isNegative()) {
          flags |= BigNumberFlags.FlagNegative;
        }
        if (ef.IsInfinity()) {
          flags |= BigNumberFlags.FlagInfinity;
        }
        if (ef.IsSignalingNaN()) {
          flags |= BigNumberFlags.FlagSignalingNaN;
        }
        if (ef.IsQuietNaN()) {
          flags |= BigNumberFlags.FlagQuietNaN;
        }
        er.flags = flags;
        return er;
      }
      EInteger num = ef.getMantissa();
      EInteger exp = ef.getExponent();
      if (exp.isZero()) {
        return FromEInteger(num);
      }
      boolean neg = num.signum() < 0;
      num = num.Abs();
      EInteger den = EInteger.FromInt32(1);
      if (exp.signum() < 0) {
        exp=(exp).Negate();
        den = NumberUtility.ShiftLeft(den, exp);
      } else {
        num = NumberUtility.ShiftLeft(num, exp);
      }
      if (neg) {
        num=(num).Negate();
      }
      return new ERational(num, den);
    }

    /**
     * Converts an arbitrary-precision integer to a rational number.
     * @param bigint An arbitrary-precision integer.
     * @return The exact value of the integer as a rational number.
     */
    public static ERational FromEInteger(EInteger bigint) {
      return new ERational(bigint, EInteger.FromInt32(1));
    }

    /**
     * Not documented yet.
     * @param smallint The parameter {@code smallint} is not documented yet.
     * @return An arbitrary-precision rational number.
     */
    public static ERational FromInt32(int smallint) {
      return new ERational(EInteger.FromInt32(smallint), EInteger.FromInt32(1));
    }

    /**
     * Not documented yet.
     * @param longInt The parameter {@code longInt} is not documented yet.
     * @return An arbitrary-precision rational number.
     */
    public static ERational FromInt64(long longInt) {
      return new ERational(EInteger.FromInt64(longInt), EInteger.FromInt32(1));
    }

    /**
     * Converts a 32-bit floating-point number to a rational number. This method
     * computes the exact value of the floating point number, not an
     * approximation, as is often the case by converting the number to a
     * string.
     * @param flt A 32-bit floating-point number.
     * @return A rational number with the same value as {@code flt}.
     */
    public static ERational FromSingle(float flt) {
      return FromEFloat(EFloat.FromSingle(flt));
    }

    /**
     * Creates a rational number from a string that represents a number. See
     * <code>FromString(String, int, int)</code> for more information.
     * @param str A string that represents a number.
     * @return An arbitrary-precision rational number with the same value as the
     * given string.
     * @throws java.lang.NullPointerException The parameter {@code str} is null.
     * @throws java.lang.NumberFormatException The parameter {@code str} is not a correctly
     * formatted number string.
     */
    public static ERational FromString(String str) {
      return FromString(str, 0, str == null ? 0 : str.length());
    }

    private static final int MaxSafeInt = 214748363;

    /**
     * <p>Creates a rational number from a string that represents a number.</p>
     * <p>The format of the string generally consists of:</p> <ul> <li>An
     * optional plus sign ("+" , U+002B) or minus sign ("-", U+002D) (if '-'
     * , the value is negative.)</li> <li>The numerator in the form of one
     * or more digits.</li> <li>Optionally, "/" followed by the denominator
     * in the form of one or more digits. If a denominator is not given,
     * it's equal to 1.</li></ul> <p>The string can also be "-INF",
     * "-Infinity", "Infinity", "INF" , quiet NaN ("NaN" /"-NaN") followed
     * by any number of digits, or signaling NaN ("sNaN" /"-sNaN") followed
     * by any number of digits, all in any combination of upper and lower
     * case.</p> <p>All characters mentioned above are the corresponding
     * characters in the Basic Latin range. In particular, the digits must
     * be the basic digits 0 to 9 (U + 0030 to U + 0039). The string is not
     * allowed to contain white space characters, including spaces.</p>
     * @param str A text string, a portion of which represents a number.
     * @param offset A zero-based index that identifies the start of the number.
     * @param length The length of the number within the string.
     * @return Not documented yet.
     * @throws java.lang.NullPointerException The parameter {@code str} is null.
     * @throws java.lang.NumberFormatException The parameter {@code str} is not a correctly
     * formatted number string.
     */
    public static ERational FromString(
      String str,
      int offset,
      int length) {
      int tmpoffset = offset;
      if (str == null) {
        throw new NullPointerException("str");
      }
      if (tmpoffset < 0) {
        throw new NumberFormatException("offset (" + tmpoffset + ") is less than " +
                    "0");
      }
      if (tmpoffset > str.length()) {
        throw new NumberFormatException("offset (" + tmpoffset + ") is more than " +
                    str.length());
      }
      if (length < 0) {
        throw new NumberFormatException("length (" + length + ") is less than " +
                    "0");
      }
      if (length > str.length()) {
        throw new NumberFormatException("length (" + length + ") is more than " +
                    str.length());
      }
      if (str.length() - tmpoffset < length) {
        throw new NumberFormatException("str's length minus " + tmpoffset + " (" +
                    (str.length() - tmpoffset) + ") is less than " + length);
      }
      if (length == 0) {
        throw new NumberFormatException();
      }
      boolean negative = false;
      int endStr = tmpoffset + length;
      if (str.charAt(0) == '+' || str.charAt(0) == '-') {
        negative = str.charAt(0) == '-';
        ++tmpoffset;
      }
      int numerInt = 0;
      FastInteger numer = null;
      int numerBuffer = 0;
      int numerBufferMult = 1;
      int denomBuffer = 0;
      int denomBufferMult = 1;
      boolean haveDigits = false;
      boolean haveDenominator = false;
      int ndenomInt = 0;
      FastInteger ndenom = null;
      int i = tmpoffset;
      if (i + 8 == endStr) {
        if ((str.charAt(i) == 'I' || str.charAt(i) == 'i') &&
            (str.charAt(i + 1) == 'N' || str.charAt(i + 1) == 'n') &&
            (str.charAt(i + 2) == 'F' || str.charAt(i + 2) == 'f') &&
            (str.charAt(i + 3) == 'I' || str.charAt(i + 3) == 'i') && (str.charAt(i + 4) == 'N' ||
                    str.charAt(i + 4) == 'n') && (str.charAt(i + 5) ==
                    'I' || str.charAt(i + 5) == 'i') &&
            (str.charAt(i + 6) == 'T' || str.charAt(i + 6) == 't') && (str.charAt(i + 7) == 'Y' ||
                    str.charAt(i + 7) == 'y')) {
          return negative ? NegativeInfinity : PositiveInfinity;
        }
      }
      if (i + 3 == endStr) {
        if ((str.charAt(i) == 'I' || str.charAt(i) == 'i') &&
            (str.charAt(i + 1) == 'N' || str.charAt(i + 1) == 'n') && (str.charAt(i + 2) == 'F' ||
                    str.charAt(i + 2) == 'f')) {
          return negative ? NegativeInfinity : PositiveInfinity;
        }
      }
      if (i + 3 <= endStr) {
        // Quiet NaN
        if ((str.charAt(i) == 'N' || str.charAt(i) == 'n') && (str.charAt(i + 1) == 'A' || str.charAt(i +
                1) == 'a') && (str.charAt(i + 2) == 'N' || str.charAt(i + 2) == 'n')) {
          int flags2 = (negative ? BigNumberFlags.FlagNegative : 0) |
            BigNumberFlags.FlagQuietNaN;
          if (i + 3 == endStr) {
            return (!negative) ? NaN : NaN.Negate();
          }
          i += 3;
          FastInteger digitCount = new FastInteger(0);
          for (; i < endStr; ++i) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
              int thisdigit = (int)(str.charAt(i) - '0');
              haveDigits = haveDigits || thisdigit != 0;
              if (numerInt > MaxSafeInt) {
                if (numer == null) {
                  numer = new FastInteger(numerInt);
                  numerBuffer = thisdigit;
                  numerBufferMult = 10;
                } else {
                  if (numerBufferMult >= 1000000000) {
                    numer.Multiply(numerBufferMult).AddInt(numerBuffer);
                    numerBuffer = thisdigit;
                    numerBufferMult = 10;
                  } else {
                    numerBufferMult *= 10;
                    numerBuffer = (numerBuffer << 3) + (numerBuffer << 1);
                    numerBuffer += thisdigit;
                  }
                }
              } else {
                numerInt *= 10;
                numerInt += thisdigit;
              }
            } else {
              throw new NumberFormatException();
            }
          }
          if (numer != null && (numerBufferMult != 1 || numerBuffer != 0)) {
            numer.Multiply(numerBufferMult).AddInt(numerBuffer);
          }
          EInteger bignumer = (numer == null) ? (EInteger.FromInt32(numerInt)) :
            numer.AsEInteger();
          return CreateNaN(bignumer, false, negative);
        }
      }
      if (i + 4 <= endStr) {
        // Signaling NaN
        if ((str.charAt(i) == 'S' || str.charAt(i) == 's') && (str.charAt(i + 1) == 'N' || str.charAt(i +
                    1) == 'n') && (str.charAt(i + 2) == 'A' || str.charAt(i + 2) == 'a') &&
                (str.charAt(i + 3) == 'N' || str.charAt(i + 3) == 'n')) {
          if (i + 4 == endStr) {
            int flags2 = (negative ? BigNumberFlags.FlagNegative : 0) |
              BigNumberFlags.FlagSignalingNaN;
            return (!negative) ? SignalingNaN : SignalingNaN.Negate();
          }
          i += 4;
          FastInteger digitCount = new FastInteger(0);
          for (; i < endStr; ++i) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
              int thisdigit = (int)(str.charAt(i) - '0');
              haveDigits = haveDigits || thisdigit != 0;
              if (numerInt > MaxSafeInt) {
                if (numer == null) {
                  numer = new FastInteger(numerInt);
                  numerBuffer = thisdigit;
                  numerBufferMult = 10;
                } else {
                  if (numerBufferMult >= 1000000000) {
                    numer.Multiply(numerBufferMult).AddInt(numerBuffer);
                    numerBuffer = thisdigit;
                    numerBufferMult = 10;
                  } else {
                    numerBufferMult *= 10;
                    numerBuffer = (numerBuffer << 3) + (numerBuffer << 1);
                    numerBuffer += thisdigit;
                  }
                }
              } else {
                numerInt *= 10;
                numerInt += thisdigit;
              }
            } else {
              throw new NumberFormatException();
            }
          }
          if (numer != null && (numerBufferMult != 1 || numerBuffer != 0)) {
            numer.Multiply(numerBufferMult).AddInt(numerBuffer);
          }
          int flags3 = (negative ? BigNumberFlags.FlagNegative : 0) |
            BigNumberFlags.FlagSignalingNaN;
          EInteger bignumer = (numer == null) ? (EInteger.FromInt32(numerInt)) :
            numer.AsEInteger();
          return CreateWithFlags(
            bignumer,
            EInteger.FromInt32(0),
            flags3);
        }
      }
      // Ordinary number
      for (; i < endStr; ++i) {
        if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
          int thisdigit = (int)(str.charAt(i) - '0');
          if (numerInt > MaxSafeInt) {
            if (numer == null) {
              numer = new FastInteger(numerInt);
              numerBuffer = thisdigit;
              numerBufferMult = 10;
            } else {
              if (numerBufferMult >= 1000000000) {
                numer.Multiply(numerBufferMult).AddInt(numerBuffer);
                numerBuffer = thisdigit;
                numerBufferMult = 10;
              } else {
                // multiply numerBufferMult and numerBuffer each by 10
             numerBufferMult = (numerBufferMult << 3) + (numerBufferMult <<
                  1);
                numerBuffer = (numerBuffer << 3) + (numerBuffer << 1);
                numerBuffer += thisdigit;
              }
            }
          } else {
            numerInt *= 10;
            numerInt += thisdigit;
          }
          haveDigits = true;
        } else if (str.charAt(i) == '/') {
          haveDenominator = true;
          ++i;
          break;
        } else {
          throw new NumberFormatException();
        }
      }
      if (!haveDigits) {
        throw new NumberFormatException();
      }
      if (numer != null && (numerBufferMult != 1 || numerBuffer != 0)) {
        numer.Multiply(numerBufferMult).AddInt(numerBuffer);
      }
      if (haveDenominator) {
        FastInteger denom = null;
        int denomInt = 0;
        tmpoffset = 1;
        haveDigits = false;
        if (i == endStr) {
          throw new NumberFormatException();
        }
        for (; i < endStr; ++i) {
          if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            haveDigits = true;
            int thisdigit = (int)(str.charAt(i) - '0');
            if (denomInt > MaxSafeInt) {
              if (denom == null) {
                denom = new FastInteger(denomInt);
                denomBuffer = thisdigit;
                denomBufferMult = 10;
              } else {
                if (denomBufferMult >= 1000000000) {
                  denom.Multiply(denomBufferMult).AddInt(denomBuffer);
                  denomBuffer = thisdigit;
                  denomBufferMult = 10;
                } else {
                  // multiply denomBufferMult and denomBuffer each by 10
             denomBufferMult = (denomBufferMult << 3) + (denomBufferMult <<
                    1);
                  denomBuffer = (denomBuffer << 3) + (denomBuffer << 1);
                  denomBuffer += thisdigit;
                }
              }
            } else {
              denomInt *= 10;
              denomInt += thisdigit;
            }
          } else {
            throw new NumberFormatException();
          }
        }
        if (!haveDigits) {
          throw new NumberFormatException();
        }
        if (denom != null && (denomBufferMult != 1 || denomBuffer != 0)) {
          denom.Multiply(denomBufferMult).AddInt(denomBuffer);
        }
        if (denom == null) {
          ndenomInt = denomInt;
        } else {
          ndenom = denom;
        }
      } else {
        ndenomInt = 1;
      }
      if (i != endStr) {
        throw new NumberFormatException();
      }
      if (ndenom == null ? (ndenomInt == 0) : ndenom.isValueZero()) {
        throw new NumberFormatException();
      }
      ERational erat = Create(
        numer == null ? EInteger.FromInt32(numerInt) : numer.AsEInteger(),
        ndenom == null ? EInteger.FromInt32(ndenomInt) : ndenom.AsEInteger());
      return negative ? erat.Negate() : erat;
    }

    /**
     * Compares the absolute values of this object and another object, imposing a
     * total ordering on all possible values (ignoring their signs). In this
     * method: <ul> <li>For objects with the same value, the one with the
     * higher denominator has a greater "absolute value".</li> <li>Negative
     * zero and positive zero are considered equal.</li> <li>Quiet NaN has a
     * higher "absolute value" than signaling NaN. If both objects are quiet
     * NaN or both are signaling NaN, the one with the higher diagnostic
     * information has a greater "absolute value".</li> <li>NaN has a higher
     * "absolute value" than infinity.</li> <li>Infinity has a higher
     * "absolute value" than any finite number.</li></ul>
     * @param other An arbitrary-precision rational number to compare with this
     * one.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater.
     */
    public int CompareToTotalMagnitude(ERational other) {
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
     * Compares the values of this object and another object, imposing a total
     * ordering on all possible values. In this method: <ul> <li>For objects
     * with the same value, the one with the higher denominator has a
     * greater "absolute value".</li> <li>Negative zero is less than
     * positive zero.</li> <li>Quiet NaN has a higher "absolute value" than
     * signaling NaN. If both objects are quiet NaN or both are signaling
     * NaN, the one with the higher diagnostic information has a greater
     * "absolute value".</li> <li>NaN has a higher "absolute value" than
     * infinity.</li> <li>Infinity has a higher "absolute value" than any
     * finite number.</li> <li>Negative numbers are less than positive
     * numbers.</li></ul>
     * @param other An arbitrary-precision rational number to compare with this
     * one.
     * @return The number 0 if both objects have the same value, or -1 if this
     * object is less than the other value, or 1 if this object is greater.
     */
    public int CompareToTotal(ERational other) {
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
     * Not documented yet.
     * @return An arbitrary-precision rational number.
     */
    public ERational Abs() {
      if (this.isNegative()) {
        ERational er = new ERational(this.unsignedNumerator, this.denominator);
        er.flags = this.flags & ~BigNumberFlags.FlagNegative;
        return er;
      }
      return this;
    }

    /**
     * Adds two rational numbers.
     * @param otherValue Another arbitrary-precision rational number.
     * @return The sum of the two numbers. Returns not-a-number (NaN) if either
     * operand is NaN.
     * @throws java.lang.NullPointerException The parameter {@code otherValue} is
     * null.
     */
    public ERational Add(ERational otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.IsSignalingNaN()) {
        return CreateNaN(this.unsignedNumerator, false, this.isNegative());
      }
      if (otherValue.IsSignalingNaN()) {
        return CreateNaN(
      otherValue.unsignedNumerator,
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
      return new ERational(ad, bd);
    }

    /**
     * Compares an arbitrary-precision rational number with this instance.
     * @param other An arbitrary-precision rational number.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater.
     */
    public int compareTo(ERational other) {
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
      EInteger ad = this.getNumerator().Multiply(other.getDenominator());
      EInteger bc = this.getDenominator().Multiply(other.getNumerator());
      return ad.compareTo(bc);
    }

    /**
     * Compares an arbitrary-precision binary float with this instance.
     * @param other An arbitrary-precision binary float.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater.
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
          int digitCount = this.getUnsignedNumerator().GetSignedBitLength();
          --digitCount;
          EInteger bigDigitCount = EInteger.FromInt32(digitCount);
          if (bigDigitCount.compareTo(other.getExponent()) < 0) {
            // Numerator's digit count minus 1 is less than the other' s
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
      EInteger ad = this.getNumerator().Multiply(otherRational.getDenominator());
      EInteger bc = this.getDenominator().Multiply(otherRational.getNumerator());
      return ad.compareTo(bc);
    }

    /**
     * Compares an arbitrary-precision decimal number with this instance.
     * @param other An arbitrary-precision decimal number.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater.
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
          int digitCount = this.getUnsignedNumerator().GetDigitCount();
          --digitCount;
          EInteger bigDigitCount = EInteger.FromInt32(digitCount);
          if (bigDigitCount.compareTo(other.getExponent()) < 0) {
            // Numerator's digit count minus 1 is less than the other' s
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
      ERational otherRational = ERational.FromEDecimal(other);
      EInteger ad = this.getNumerator().Multiply(otherRational.getDenominator());
      EInteger bc = this.getDenominator().Multiply(otherRational.getNumerator());
      return ad.compareTo(bc);
    }

    /**
     * Returns a number with the same value as this one, but copying the sign
     * (positive or negative) of another number.
     * @param other A number whose sign will be copied.
     * @return An arbitrary-precision rational number.
     * @throws java.lang.NullPointerException The parameter {@code other} is null.
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
     * Divides this instance by the value of an arbitrary-precision rational number
     * object.
     * @param otherValue An arbitrary-precision rational number.
     * @return The quotient of the two objects.
     * @throws java.lang.NullPointerException The parameter {@code otherValue} is
     * null.
     */
    public ERational Divide(ERational otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.IsSignalingNaN()) {
        return CreateNaN(this.unsignedNumerator, false, this.isNegative());
      }
      if (otherValue.IsSignalingNaN()) {
        return CreateNaN(
      otherValue.unsignedNumerator,
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
      return new ERational(ad, bc).ChangeSign(resultNeg);
    }

    /**
     * Determines whether this object and another object are equal.
     * @param obj An arbitrary object.
     * @return true if the objects are equal, otherwise, false.
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
     * Not documented yet.
     * @param other The parameter {@code other} is not documented yet.
     * @return A Boolean object.
     */
    public boolean equals(ERational other) {
      return this.equals((Object)other);
    }

    /**
     * Returns the hash code for this instance.
     * @return A 32-bit hash code.
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
     * @return true if this object's value is infinity, otherwise, false.
     */
    public boolean IsInfinity() {
      return (this.flags & BigNumberFlags.FlagInfinity) != 0;
    }

    /**
     * Returns whether this object is a not-a-number value.
     * @return true if this object is a not-a-number value, otherwise, false.
     */
    public boolean IsNaN() {
      return (this.flags & BigNumberFlags.FlagNaN) != 0;
    }

    /**
     * Returns whether this object is negative infinity.
     * @return true if this object is negative infinity, otherwise, false.
     */
    public boolean IsNegativeInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
        BigNumberFlags.FlagNegative)) ==
        (BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);
    }

    /**
     * Returns whether this object is positive infinity.
     * @return true if this object is positive infinity, otherwise, false.
     */
    public boolean IsPositiveInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
        BigNumberFlags.FlagNegative)) == BigNumberFlags.FlagInfinity;
    }

    /**
     * Returns whether this object is a quiet not-a-number value.
     * @return true if this object is a quiet not-a-number value, otherwise, false.
     */
    public boolean IsQuietNaN() {
      return (this.flags & BigNumberFlags.FlagQuietNaN) != 0;
    }

    /**
     * Returns whether this object is a signaling not-a-number value (which causes
     * an error if the value is passed to any arithmetic operation in this
     * class).
     * @return true if this object is a signaling not-a-number value (which causes
     * an error if the value is passed to any arithmetic operation in this
     * class), otherwise, false.
     */
    public boolean IsSignalingNaN() {
      return (this.flags & BigNumberFlags.FlagSignalingNaN) != 0;
    }

    /**
     * Multiplies this instance by the value of an arbitrary-precision rational
     * number.
     * @param otherValue An arbitrary-precision rational number.
     * @return The product of the two numbers.
     * @throws java.lang.NullPointerException The parameter {@code otherValue} is
     * null.
     */
    public ERational Multiply(ERational otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.IsSignalingNaN()) {
        return CreateNaN(this.unsignedNumerator, false, this.isNegative());
      }
      if (otherValue.IsSignalingNaN()) {
        return CreateNaN(
      otherValue.unsignedNumerator,
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
      return ac.isZero() ? (resultNeg ? NegativeZero : Zero) : new
        ERational(ac, bd).ChangeSign(resultNeg);
    }

    /**
     * Returns a rational number with the sign reversed.
     * @return An arbitrary-precision rational number.
     */
    public ERational Negate() {
      ERational er = new ERational(this.unsignedNumerator, this.denominator);
      er.flags = this.flags ^ BigNumberFlags.FlagNegative;
      return er;
    }

    /**
     * Finds the remainder that results when this instance is divided by the value
     * of an arbitrary-precision rational number.
     * @param otherValue An arbitrary-precision rational number.
     * @return The remainder of the two objects.
     * @throws java.lang.NullPointerException The parameter {@code otherValue} is
     * null.
     */
    public ERational Remainder(ERational otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.IsSignalingNaN()) {
        return CreateNaN(this.unsignedNumerator, false, this.isNegative());
      }
      if (otherValue.IsSignalingNaN()) {
        return CreateNaN(
      otherValue.unsignedNumerator,
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
      EInteger quo = ad.Divide(bc);  // Find the integer quotient
      EInteger tnum = quo.Multiply(otherValue.getNumerator());
      EInteger tden = otherValue.getDenominator();
      EInteger thisDen = this.getDenominator();
      ad = this.getNumerator().Multiply(tden);
      bc = thisDen.Multiply(tnum);
      tden = tden.Multiply(thisDen);
      ad = ad.Subtract(bc);
      return new ERational(ad, tden).ChangeSign(resultNeg);
    }

    /**
     * Subtracts an arbitrary-precision rational number from this instance.
     * @param otherValue An arbitrary-precision rational number.
     * @return The difference of the two objects.
     * @throws java.lang.NullPointerException The parameter {@code otherValue} is
     * null.
     */
    public ERational Subtract(ERational otherValue) {
      if (otherValue == null) {
        throw new NullPointerException("otherValue");
      }
      if (this.IsSignalingNaN()) {
        return CreateNaN(this.unsignedNumerator, false, this.isNegative());
      }
      if (otherValue.IsSignalingNaN()) {
        return CreateNaN(
      otherValue.unsignedNumerator,
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
      return new ERational(ad, bd);
    }

    /**
     * Converts this value to a 64-bit floating-point number. The half-even
     * rounding mode is used.
     * @return The closest 64-bit floating-point number to this value. The return
     * value can be positive infinity or negative infinity if this value
     * exceeds the range of a 64-bit floating point number.
     */
    public double ToDouble() {
      if (!this.isFinite()) {
        return this.ToEFloat(EContext.Binary64).ToDouble();
      }
      if (this.isNegative() && this.isZero()) {
        return EFloat.NegativeZero.ToDouble();
      }
      return EFloat.FromEInteger(this.getNumerator())
        .Divide(EFloat.FromEInteger(this.denominator), EContext.Binary64)
        .ToDouble();
    }

    /**
     * Converts this value to an arbitrary-precision integer. Any fractional part
     * in this value will be discarded when converting to an
     * arbitrary-precision integer.
     * @return An arbitrary-precision integer.
     * @throws java.lang.ArithmeticException This object's value is infinity or NaN.
     */
    public EInteger ToEInteger() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.getNumerator().Divide(this.denominator);
    }

    /**
     * Converts this value to an arbitrary-precision integer, checking whether the
     * value is an exact integer.
     * @return An arbitrary-precision integer.
     * @throws java.lang.ArithmeticException This object's value is infinity or NaN.
     * @throws ArithmeticException This object's value is not an exact integer.
     */
    public EInteger ToEIntegerExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      EInteger rem;
      EInteger quo;
      {
        EInteger[] divrem = this.getNumerator().DivRem(this.denominator);
        quo = divrem[0];
        rem = divrem[1];
      }
      if (!rem.isZero()) {
        throw new ArithmeticException("Value is not an integral value");
      }
      return quo;
    }

    /**
     * Converts this rational number to a decimal number.
     * @return The exact value of the rational number, or not-a-number (NaN) if the
     * result can't be exact because it has a nonterminating decimal
     * expansion.
     */
    public EDecimal ToEDecimal() {
      return this.ToEDecimal(null);
    }

    /**
     * Converts this rational number to a decimal number and rounds the result to
     * the given precision.
     * @param ctx An EContext object.
     * @return An arbitrary-precision decimal.
     */
    public EDecimal ToEDecimal(EContext ctx) {
      if (this.IsNaN()) {
        return EDecimal.CreateNaN(
this.unsignedNumerator,
this.IsSignalingNaN(),
this.isNegative(),
ctx);
      }
      if (this.IsPositiveInfinity()) {
        return EDecimal.PositiveInfinity;
      }
      if (this.IsNegativeInfinity()) {
        return EDecimal.NegativeInfinity;
      }
      EDecimal ef = (this.isNegative() && this.isZero()) ?
 EDecimal.NegativeZero : EDecimal.FromEInteger(this.getNumerator());
      return ef.Divide(EDecimal.FromEInteger(this.getDenominator()), ctx);
    }

    /**
     * Converts this rational number to a decimal number, but if the result would
     * have a nonterminating decimal expansion, rounds that result to the
     * given precision.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only if
     * the exact result would have a nonterminating decimal expansion. If
     * {@code HasFlags} of the context is true, will also store the flags
     * resulting from the operation (the flags are in addition to the
     * pre-existing flags). Can be null, in which case this method is the
     * same as ToExtendedDecimal.
     * @return An arbitrary-precision decimal.
     */
    public EDecimal ToEDecimalExactIfPossible(EContext
          ctx) {
      if (ctx == null) {
        return this.ToEDecimal(null);
      }
      if (this.IsNaN()) {
        return EDecimal.CreateNaN(
this.unsignedNumerator,
this.IsSignalingNaN(),
this.isNegative(),
ctx);
      }
      if (this.IsPositiveInfinity()) {
        return EDecimal.PositiveInfinity;
      }
      if (this.IsNegativeInfinity()) {
        return EDecimal.NegativeInfinity;
      }
      if (this.isNegative() && this.isZero()) {
        return EDecimal.NegativeZero;
      }
      EDecimal valueEdNum = (this.isNegative() && this.isZero()) ?
 EDecimal.NegativeZero : EDecimal.FromEInteger(this.getNumerator());
      EDecimal valueEdDen = EDecimal.FromEInteger(this.getDenominator());
      EDecimal ed = valueEdNum.Divide(valueEdDen, null);
      if (ed.IsNaN()) {
        // Result would be inexact, try again using the precision context
        ed = valueEdNum.Divide(valueEdDen, ctx);
      }
      return ed;
    }

    /**
     * Converts this rational number to a decimal number.
     * @return The exact value of the rational number, or not-a-number (NaN) if the
     * result can't be exact because it has a nonterminating decimal
     * expansion.
     * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal() {
      return this.ToEDecimal();
    }

    /**
     * Converts this rational number to a decimal number and rounds the result to
     * the given precision.
     * @param ctx An EContext object.
     * @return An arbitrary-precision decimal.
     * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal(EContext ctx) {
      return this.ToEDecimal(ctx);
    }

    /**
     * Converts this rational number to a decimal number, but if the result would
     * have a nonterminating decimal expansion, rounds that result to the
     * given precision.
     * @param ctx An arithmetic context object to control the precision. The
     * rounding and exponent range settings of this context are ignored.
     * This context will be used only if the exact result would have a
     * nonterminating decimal expansion. If {@code HasFlags} of the context
     * is true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case this method is the same as ToExtendedDecimal.
     * @return An arbitrary-precision decimal.
     * @deprecated Renamed to ToEDecimalExactIfPossible.
 */
@Deprecated
    public EDecimal ToExtendedDecimalExactIfPossible(EContext ctx) {
      return this.ToEDecimalExactIfPossible(ctx);
    }

    /**
     * Converts this rational number to a binary number.
     * @return The exact value of the rational number, or not-a-number (NaN) if the
     * result can't be exact because it has a nonterminating binary
     * expansion.
     */
    public EFloat ToEFloat() {
      return this.ToEFloat(null);
    }

    /**
     * Converts this rational number to a binary number and rounds the result to
     * the given precision.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only if
     * the exact result would have a nonterminating binary expansion. If
     * {@code HasFlags} of the context is true, will also store the flags
     * resulting from the operation (the flags are in addition to the
     * pre-existing flags). Can be null, in which case this method is the
     * same as ToExtendedFloat.
     * @return An arbitrary-precision binary float.
     */
    public EFloat ToEFloat(EContext ctx) {
      if (this.IsNaN()) {
        return EFloat.CreateNaN(
this.unsignedNumerator,
this.IsSignalingNaN(),
this.isNegative(),
ctx);
      }
      if (this.IsPositiveInfinity()) {
        return EFloat.PositiveInfinity;
      }
      if (this.IsNegativeInfinity()) {
        return EFloat.NegativeInfinity;
      }
      EFloat ef = (this.isNegative() && this.isZero()) ?
     EFloat.NegativeZero : EFloat.FromEInteger(this.getNumerator());
      return ef.Divide(EFloat.FromEInteger(this.getDenominator()), ctx);
    }

    /**
     * Converts this rational number to a binary number, but if the result would
     * have a nonterminating binary expansion, rounds that result to the
     * given precision.
     * @param ctx An arithmetic context object to control the precision, rounding,
     * and exponent range of the result. This context will be used only if
     * the exact result would have a nonterminating binary expansion. If
     * {@code HasFlags} of the context is true, will also store the flags
     * resulting from the operation (the flags are in addition to the
     * pre-existing flags). Can be null, in which case this method is the
     * same as ToExtendedFloat.
     * @return An arbitrary-precision binary float.
     */
    public EFloat ToEFloatExactIfPossible(EContext ctx) {
      if (ctx == null) {
        return this.ToEFloat(null);
      }
      if (this.IsNaN()) {
        return EFloat.CreateNaN(
this.unsignedNumerator,
this.IsSignalingNaN(),
this.isNegative(),
ctx);
      }
      if (this.IsPositiveInfinity()) {
        return EFloat.PositiveInfinity;
      }
      if (this.IsNegativeInfinity()) {
        return EFloat.NegativeInfinity;
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
     * Converts this rational number to a binary number.
     * @return The exact value of the rational number, or not-a-number (NaN) if the
     * result can't be exact because it has a nonterminating binary
     * expansion.
     * @deprecated Renamed to ToEFloat.
 */
@Deprecated
    public EFloat ToExtendedFloat() {
      return this.ToEFloat();
    }

    /**
     * Converts this rational number to a binary number and rounds the result to
     * the given precision.
     * @param ctx An EContext object.
     * @return An arbitrary-precision binary float.
     * @deprecated Renamed to ToEFloat.
 */
@Deprecated
    public EFloat ToExtendedFloat(EContext ctx) {
      return this.ToEFloat(ctx);
    }

    /**
     * Converts this rational number to a binary number, but if the result would
     * have a nonterminating binary expansion, rounds that result to the
     * given precision.
     * @param ctx An arithmetic context object to control the precision. The
     * rounding and exponent range settings of this context are ignored.
     * This context will be used only if the exact result would have a
     * nonterminating binary expansion. If {@code HasFlags} of the context
     * is true, will also store the flags resulting from the operation (the
     * flags are in addition to the pre-existing flags). Can be null, in
     * which case this method is the same as ToExtendedFloat.
     * @return An arbitrary-precision binary float.
     * @deprecated Renamed to ToEFloatExactIfPossible.
 */
@Deprecated
    public EFloat ToExtendedFloatExactIfPossible(EContext ctx) {
      return this.ToEFloatExactIfPossible(ctx);
    }

    /**
     * Converts this value to a 32-bit floating-point number. The half-even
     * rounding mode is used.
     * @return The closest 32-bit floating-point number to this value. The return
     * value can be positive infinity or negative infinity if this value
     * exceeds the range of a 32-bit floating point number.
     */
    public float ToSingle() {
      return
  this.ToEFloat(EContext.Binary32.WithRounding(ERounding.Odd))
        .ToSingle();
    }

    /**
     * Converts this object to a text string.
     * @return A string representation of this object. If this object's value is
     * infinity or not-a-number, the result is the analogous return value of
     * the EDecimal.toString method. Otherwise, the return value has the
     * following form: [-]numerator/denominator.
     */
    @Override public String toString() {
      if (!this.isFinite()) {
        if (this.IsSignalingNaN()) {
          if (this.unsignedNumerator.isZero()) {
            return this.isNegative() ? "-sNaN" : "sNaN";
          }
          return this.isNegative() ? "-sNaN" + this.unsignedNumerator :
              "sNaN" + this.unsignedNumerator;
        }
        if (this.IsQuietNaN()) {
          if (this.unsignedNumerator.isZero()) {
            return this.isNegative() ? "-NaN" : "NaN";
          }
          return this.isNegative() ? "-NaN" + this.unsignedNumerator :
              "NaN" + this.unsignedNumerator;
        }
        if (this.IsInfinity()) {
          return this.isNegative() ? "-Infinity" : "Infinity";
        }
      }
      return this.getNumerator() + "/" + this.getDenominator();
    }

    private static ERational CreateWithFlags(
EInteger numerator,
EInteger denominator,
int flags) {
      ERational er = new ERational(numerator, denominator);
      er.flags = flags;
      return er;
    }

    private ERational ChangeSign(boolean negative) {
      if (negative) {
        this.flags |= BigNumberFlags.FlagNegative;
      } else {
        this.flags &= ~BigNumberFlags.FlagNegative;
      }
      return this;
    }
  }
