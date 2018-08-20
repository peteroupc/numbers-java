package com.upokecenter.numbers;
/*
Written by Peter O. in 2014.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

    /**
     * Represents an arbitrary-precision rational number. This class can't be
     * inherited. (The "E" stands for "extended", meaning that instances of
     * this class can be values other than numbers proper, such as infinity
     * and not-a-number.) <p><b>Thread safety:</b> Instances of this class
     * are immutable, so they are inherently safe for use by multiple
     * threads. Multiple instances of this object with the same properties
     * are interchangeable, so they should not be compared using the "=="
     * operator (which might only check if each side of the operator is the
     * same instance).</p>
     */
  public final class ERational implements Comparable<ERational> {
    private static final int MaxSafeInt = 214748363;

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

    private ERational() {
}

    private void Initialize(EInteger numerator, EInteger denominator) {
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
      this.flags = (numNegative != denNegative) ?
              BigNumberFlags.FlagNegative : 0;
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
     *
     * @param numerator An EInteger object.
     * @param denominator An EInteger object.
     * @deprecated Use the ERational.Create method instead. This constructor will be private or
* unavailable in version 1.0.
 */
@Deprecated
    public ERational(EInteger numerator, EInteger denominator) {
      this.Initialize(numerator, denominator);
    }

    /**
     *
     */
    public final EInteger getDenominator() {
        return this.denominator;
      }

    /**
     *
     */
    public final boolean isFinite() {
        return !this.IsNaN() && !this.IsInfinity();
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
        return ((this.flags & (BigNumberFlags.FlagInfinity |
          BigNumberFlags.FlagNaN)) == 0) && this.unsignedNumerator.isZero();
      }

    /**
     *
     */
    public final EInteger getNumerator() {
        return this.isNegative() ? ((this.unsignedNumerator).Negate()) :
          this.unsignedNumerator;
      }

    /**
     *
     */
    public final int signum() {
        return ((this.flags & (BigNumberFlags.FlagInfinity |
          BigNumberFlags.FlagNaN)) != 0) ? (this.isNegative() ? -1 : 1) :
          (this.unsignedNumerator.isZero() ? 0 : (this.isNegative() ? -1 : 1));
      }

    /**
     *
     */
    public final EInteger getUnsignedNumerator() {
        return this.unsignedNumerator;
      }

    /**
     *
     * @param numeratorSmall Not documented yet.
     * @param denominatorSmall Not documented yet.
     * @return An ERational object.
     */
    public static ERational Create(
  int numeratorSmall,
  int denominatorSmall) {
      return Create(EInteger.FromInt32(numeratorSmall), EInteger.FromInt32(denominatorSmall));
    }

    /**
     *
     * @param numerator Not documented yet.
     * @param denominator Not documented yet.
     * @return An ERational object.
     */
    public static ERational Create(
  EInteger numerator,
  EInteger denominator) {
            ERational er = new ERational();
      er.Initialize(numerator, denominator);
            return er;
    }

    /**
     *
     * @param diag Not documented yet.
     * @return An ERational object.
     */
    public static ERational CreateNaN(EInteger diag) {
      return CreateNaN(diag, false, false);
    }

    /**
     *
     * @param diag Not documented yet.
     * @param signaling Not documented yet.
     * @param negative Not documented yet. (3).
     * @return An ERational object.
     * @throws NullPointerException The parameter is null.
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
      ERational er = ERational.Create(diag, EInteger.FromInt32(1));
      er.flags = flags;
      return er;
    }

    /**
     *
     * @param flt Not documented yet.
     * @return An ERational object.
     */
    public static ERational FromDouble(double flt) {
      return FromEFloat(EFloat.FromDouble(flt));
    }

    /**
     *
     * @param ef Not documented yet.
     * @return An ERational object.
     * @deprecated Renamed to FromEDecimal.
 */
@Deprecated
    public static ERational FromExtendedDecimal(EDecimal ef) {
      return FromEDecimal(ef);
    }

    /**
     *
     * @param ef Not documented yet.
     * @return An ERational object.
     * @deprecated Renamed to FromEFloat.
 */
@Deprecated
    public static ERational FromExtendedFloat(EFloat ef) {
      return FromEFloat(ef);
    }

    /**
     *
     * @param ef Not documented yet.
     * @return An ERational object.
     * @throws NullPointerException The parameter is null.
     */
    public static ERational FromEDecimal(EDecimal ef) {
      if (ef == null) {
        throw new NullPointerException("ef");
      }
      if (!ef.isFinite()) {
        ERational er = ERational.Create(ef.getMantissa(), EInteger.FromInt32(1));
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
      return ERational.Create(num, den);
    }

    /**
     *
     * @param ef Not documented yet.
     * @return An ERational object.
     * @throws NullPointerException The parameter is null.
     */
    public static ERational FromEFloat(EFloat ef) {
      if (ef == null) {
        throw new NullPointerException("ef");
      }
      if (!ef.isFinite()) {
        ERational er = ERational.Create(ef.getMantissa(), EInteger.FromInt32(1));
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
      return ERational.Create(num, den);
    }

    /**
     *
     * @param bigint Not documented yet.
     * @return An ERational object.
     */
    public static ERational FromEInteger(EInteger bigint) {
      return ERational.Create(bigint, EInteger.FromInt32(1));
    }

    /**
     *
     * @param flt Not documented yet.
     * @return An ERational object.
     */
    public static ERational FromSingle(float flt) {
      return FromEFloat(EFloat.FromSingle(flt));
    }

    /**
     *
     * @param str Not documented yet.
     * @return An ERational object.
     */
    public static ERational FromString(String str) {
      return FromString(str, 0, str == null ? 0 : str.length());
    }

    /**
     *
     * @param str Not documented yet.
     * @param offset A zero-based index showing where the desired portion of {@code
     * str} begins.
     * @param length The length, in code units, of the desired portion of {@code
     * str} (but not more than {@code str} 's length).
     * @return An ERational object.
     * @throws NullPointerException The parameter is null.
     * @throws IllegalArgumentException Either "offset" or "length" is less than 0 or
     * greater than "str"'s length, or "str"'s length minus "offset" is less
     * than "length".
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
          if (i + 3 == endStr) {
            return (!negative) ? NaN : NaN.Negate();
          }
          i += 3;
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
            return (!negative) ? SignalingNaN : SignalingNaN.Negate();
          }
          i += 4;
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
            EInteger.FromInt32(1),
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
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
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
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
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
     *
     * @return An ERational object.
     */
    public ERational Abs() {
      if (this.isNegative()) {
     ERational er = ERational.Create(
  this.unsignedNumerator,
  this.denominator);
        er.flags = this.flags & ~BigNumberFlags.FlagNegative;
        return er;
      }
      return this;
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @return An ERational object.
     * @throws NullPointerException The parameter is null.
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
      return ERational.Create(ad, bd);
    }

    /**
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
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
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
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
     *
     * @param other Not documented yet.
     * @return A 32-bit signed integer.
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
     *
     * @param other Not documented yet.
     * @return An ERational object.
     * @throws NullPointerException The parameter is null.
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
     *
     * @param otherValue Not documented yet.
     * @return An ERational object.
     * @throws NullPointerException The parameter is null.
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
      return ERational.Create(ad, bc).ChangeSign(resultNeg);
    }

    /**
     *
     * @param obj Not documented yet.
     * @return A Boolean object.
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
     *
     * @param other Not documented yet.
     * @return A Boolean object.
     */
    public boolean equals(ERational other) {
      return this.equals((Object)other);
    }

    /**
     *
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
      return (this.flags & BigNumberFlags.FlagNaN) != 0;
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
     * @param otherValue Not documented yet.
     * @return An ERational object.
     * @throws NullPointerException The parameter is null.
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
      return ac.isZero() ? (resultNeg ? NegativeZero : Zero) :
               ERational.Create(ac, bd).ChangeSign(resultNeg);
    }

    /**
     *
     * @return An ERational object.
     */
    public ERational Negate() {
      ERational er = ERational.Create(this.unsignedNumerator, this.denominator);
      er.flags = this.flags ^ BigNumberFlags.FlagNegative;
      return er;
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @return An ERational object.
     * @throws NullPointerException The parameter is null.
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
      return ERational.Create(ad, tden).ChangeSign(resultNeg);
    }

    /**
     *
     * @param otherValue Not documented yet.
     * @return An ERational object.
     * @throws NullPointerException The parameter is null.
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
      return ERational.Create(ad, bd);
    }

    /**
     *
     * @return A 64-bit floating-point number.
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
     *
     * @return An EInteger object.
     */
    public EInteger ToEInteger() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.getNumerator().Divide(this.denominator);
    }

    /**
     *
     * @return An EInteger object.
     * @deprecated Renamed to ToEIntegerIfExact.
 */
@Deprecated
    public EInteger ToEIntegerExact() {
      return this.ToEIntegerIfExact();
    }

    /**
     *
     * @return An EInteger object.
     */
    public EInteger ToEIntegerIfExact() {
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
     *
     * @return An EDecimal object.
     */
    public EDecimal ToEDecimal() {
      return this.ToEDecimal(null);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
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
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
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
        return EDecimal.PositiveInfinity.RoundToPrecision(ctx);
      }
      if (this.IsNegativeInfinity()) {
        return EDecimal.NegativeInfinity.RoundToPrecision(ctx);
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
     *
     * @return An EDecimal object.
     * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal() {
      return this.ToEDecimal();
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal(EContext ctx) {
      return this.ToEDecimal(ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EDecimal object.
     * @deprecated Renamed to ToEDecimalExactIfPossible.
 */
@Deprecated
    public EDecimal ToExtendedDecimalExactIfPossible(EContext ctx) {
      return this.ToEDecimalExactIfPossible(ctx);
    }

    /**
     *
     * @return An EFloat object.
     */
    public EFloat ToEFloat() {
      return this.ToEFloat(null);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
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
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
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
     *
     * @return An EFloat object.
     * @deprecated Renamed to ToEFloat.
 */
@Deprecated
    public EFloat ToExtendedFloat() {
      return this.ToEFloat();
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     * @deprecated Renamed to ToEFloat.
 */
@Deprecated
    public EFloat ToExtendedFloat(EContext ctx) {
      return this.ToEFloat(ctx);
    }

    /**
     *
     * @param ctx Not documented yet.
     * @return An EFloat object.
     * @deprecated Renamed to ToEFloatExactIfPossible.
 */
@Deprecated
    public EFloat ToExtendedFloatExactIfPossible(EContext ctx) {
      return this.ToEFloatExactIfPossible(ctx);
    }

    /**
     *
     * @return A 32-bit floating-point number.
     */
    public float ToSingle() {
      return
  this.ToEFloat(EContext.Binary32.WithRounding(ERounding.Odd))
        .ToSingle();
    }

    /**
     *
     * @return A string object.
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
      return (this.getNumerator().isZero() && this.isNegative()) ? ("-0/" +
        this.getDenominator()) : (this.getNumerator() + "/" + this.getDenominator());
    }

    private static ERational CreateWithFlags(
  EInteger numerator,
  EInteger denominator,
  int flags) {
      ERational er = ERational.Create(numerator, denominator);
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
     * @return An ERational object.
     */
public static ERational FromByte(byte inputByte) {
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
     * @return An ERational object.
     */
public static ERational FromInt16(short inputInt16) {
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
     * @return An ERational object.
     */
public static ERational FromInt32(int inputInt32) {
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
     * @return An ERational object.
     */
public static ERational FromInt64(long inputInt64) {
 return FromEInteger(EInteger.FromInt64(inputInt64));
}

// End integer conversions
  }
