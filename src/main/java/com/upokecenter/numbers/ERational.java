package com.upokecenter.numbers;
/*
Written by Peter O. in 2014.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

  public final class ERational implements Comparable<ERational> {
    private static final int MaxSafeInt = 214748363;

    public static final ERational NaN = new ERational(
      EInteger.FromInt32(0),
      EInteger.FromInt32(1),
      BigNumberFlags.FlagQuietNaN);

    public static final ERational NegativeInfinity =
      new ERational(
        EInteger.FromInt32(0),
        EInteger.FromInt32(1),
        BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);

    public static final ERational NegativeZero =
      new ERational(EInteger.FromInt32(0), EInteger.FromInt32(1), BigNumberFlags.FlagNegative);

    public static final ERational One = FromEInteger(EInteger.FromInt32(1));

    public static final ERational PositiveInfinity =
      new ERational(
        EInteger.FromInt32(0),
        EInteger.FromInt32(1),
        BigNumberFlags.FlagInfinity);

    public static final ERational SignalingNaN =
      new ERational(
        EInteger.FromInt32(0),
        EInteger.FromInt32(1),
        BigNumberFlags.FlagSignalingNaN);

    public static final ERational Ten = FromEInteger(EInteger.FromInt32(10));

    public static final ERational Zero = FromEInteger(EInteger.FromInt32(0));

    private final EInteger denominator;

    private final int flags;
    private final EInteger unsignedNumerator;

    private ERational(EInteger numerator, EInteger denominator, int flags) {
      this.unsignedNumerator = numerator;
      this.denominator = denominator;
      this.flags = flags;
    }

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

    public ERational Copy() {
      return new ERational(
          this.unsignedNumerator,
          this.denominator,
          this.flags);
    }

    public final EInteger getDenominator() {
        return this.denominator;
      }

    public final boolean isFinite() {
        return !this.IsNaN() && !this.IsInfinity();
      }

    public final boolean isNegative() {
        return (this.flags & BigNumberFlags.FlagNegative) != 0;
      }

    public final boolean isZero() {
        return ((this.flags & (BigNumberFlags.FlagInfinity |
                BigNumberFlags.FlagNaN)) == 0) && this.unsignedNumerator.isZero();
      }

    public boolean IsInteger() {
      return this.isFinite() &&
        this.unsignedNumerator.Remainder(this.denominator).signum() == 0;
    }

    public final EInteger getNumerator() {
        return this.isNegative() ? ((this.unsignedNumerator).Negate()) :
          this.unsignedNumerator;
      }

    public final int signum() {
        return ((this.flags & (BigNumberFlags.FlagInfinity |
                BigNumberFlags.FlagNaN)) != 0) ? (this.isNegative() ? -1 : 1) :
          (this.unsignedNumerator.isZero() ? 0 : (this.isNegative() ? -1 : 1));
      }

    public final EInteger getUnsignedNumerator() {
        return this.unsignedNumerator;
      }

    public static ERational Create(
      int numeratorSmall,
      int denominatorSmall) {
      return Create(EInteger.FromInt32(numeratorSmall), EInteger.FromInt32(denominatorSmall));
    }

    public static ERational Create(
      long numeratorLong,
      long denominatorLong) {
      return Create(EInteger.FromInt64(numeratorLong), EInteger.FromInt64(denominatorLong));
    }

    public static ERational Create(
      EInteger numerator,
      EInteger denominator) {
      return new ERational(numerator, denominator);
    }

    public static ERational CreateNaN(EInteger diag) {
      return CreateNaN(diag, false, false);
    }

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
      return new ERational(diag, EInteger.FromInt32(1), flags);
    }

    public static ERational FromDouble(double flt) {
      return FromEFloat(EFloat.FromDouble(flt));
    }

/**
 * @deprecated Renamed to FromEDecimal.
 */
@Deprecated
    public static ERational FromExtendedDecimal(EDecimal ef) {
      return FromEDecimal(ef);
    }

/**
 * @deprecated Renamed to FromEFloat.
 */
@Deprecated
    public static ERational FromExtendedFloat(EFloat ef) {
      return FromEFloat(ef);
    }

    public static ERational FromEDecimal(EDecimal ef) {
      if (ef == null) {
        throw new NullPointerException("ef");
      }
      if (!ef.isFinite()) {
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
        return new ERational(ef.getUnsignedMantissa(), EInteger.FromInt32(1), flags);
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

    public static ERational FromEFloat(EFloat ef) {
      if (ef == null) {
        throw new NullPointerException("ef");
      }
      if (!ef.isFinite()) {
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
        return new ERational(ef.getUnsignedMantissa(), EInteger.FromInt32(1), flags);
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
        den = den.ShiftLeft(exp);
      } else {
        num = num.ShiftLeft(exp);
      }
      if (neg) {
        num=(num).Negate();
      }
      return ERational.Create(num, den);
    }

    public static ERational FromEInteger(EInteger bigint) {
      return ERational.Create(bigint, EInteger.FromInt32(1));
    }

    public static ERational FromSingle(float flt) {
      return FromEFloat(EFloat.FromSingle(flt));
    }

    public static ERational FromString(String str) {
      return FromString(str, 0, str == null ? 0 : str.length());
    }

    public static ERational FromString(
      String str,
      int offset,
      int length) {
      int tmpoffset = offset;
      if (str == null) {
        throw new NullPointerException("str");
      }
      if (tmpoffset < 0) {
        throw new NumberFormatException("offset(" + tmpoffset + ") is less than " +
          "0");
      }
      if (tmpoffset > str.length()) {
        throw new NumberFormatException("offset(" + tmpoffset + ") is more than " +
          str.length());
      }
      if (length < 0) {
        throw new NumberFormatException("length(" + length + ") is less than " +
          "0");
      }
      if (length > str.length()) {
        throw new NumberFormatException("length(" + length + ") is more than " +
          str.length());
      }
      if (str.length() - tmpoffset < length) {
        throw new NumberFormatException("str's length minus " + tmpoffset + "(" +
          (str.length() - tmpoffset) + ") is less than " + length);
      }
      if (length == 0) {
        throw new NumberFormatException();
      }
      boolean negative = false;
      int endStr = tmpoffset + length;
      if (str.charAt(tmpoffset) == '+' || str.charAt(tmpoffset) == '-') {
        negative = str.charAt(tmpoffset) == '-';
        ++tmpoffset;
      }
      int numerInt = 0;
      EInteger numer = null;
      boolean haveDigits = false;
      boolean haveDenominator = false;
      int ndenomInt = 0;
      EInteger ndenom = null;
      int i = tmpoffset;
      if (i + 8 == endStr) {
        if ((str.charAt(i) == 'I' || str.charAt(i) == 'i') &&
          (str.charAt(i + 1) == 'N' || str.charAt(i + 1) == 'n') &&
          (str.charAt(i + 2) == 'F' || str.charAt(i + 2) == 'f') &&
          (str.charAt(i + 3) == 'I' || str.charAt(i + 3) == 'i') && (str.charAt(i + 4) == 'N' ||
            str.charAt(i + 4) == 'n') && (str.charAt(i + 5) == 'I' || str.charAt(i + 5) == 'i') &&
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
      int numerStart = 0;
      if (i + 3 <= endStr) {
        // Quiet NaN
        if ((str.charAt(i) == 'N' || str.charAt(i) == 'n') && (str.charAt(i + 1) == 'A' || str.charAt(i +
              1) == 'a') && (str.charAt(i + 2) == 'N' || str.charAt(i + 2) == 'n')) {
          if (i + 3 == endStr) {
            return (!negative) ? NaN : NaN.Negate();
          }
          i += 3;
          numerStart = i;
          for (; i < endStr; ++i) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
              int thisdigit = (int)(str.charAt(i) - '0');
              if (numerInt <= MaxSafeInt) {
                numerInt *= 10;
                numerInt += thisdigit;
              }
            } else {
              throw new NumberFormatException();
            }
          }
          if (numerInt > MaxSafeInt) {
            numer = EInteger.FromSubstring(str, numerStart, endStr);
            return CreateNaN(numer, false, negative);
          } else {
            return CreateNaN(EInteger.FromInt32(numerInt), false, negative);
          }
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
          numerStart = i;
          for (; i < endStr; ++i) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
              int thisdigit = (int)(str.charAt(i) - '0');
              haveDigits = haveDigits || thisdigit != 0;
              if (numerInt <= MaxSafeInt) {
                numerInt *= 10;
                numerInt += thisdigit;
              }
            } else {
              throw new NumberFormatException();
            }
          }
          int flags3 = (negative ? BigNumberFlags.FlagNegative : 0) |
            BigNumberFlags.FlagSignalingNaN;
          if (numerInt > MaxSafeInt) {
            numer = EInteger.FromSubstring(str, numerStart, endStr);
            return new ERational(numer,
                EInteger.FromInt32(1),
                flags3);
          } else {
            return new ERational(EInteger.FromInt32(numerInt),
                EInteger.FromInt32(1),
                flags3);
          }
        }
      }
      // Ordinary number
      numerStart = i;
      int numerEnd = i;
      for (; i < endStr; ++i) {
        if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
          int thisdigit = (int)(str.charAt(i) - '0');
          numerEnd = i + 1;
          if (numerInt <= MaxSafeInt) {
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
      if (numerInt > MaxSafeInt) {
        numer = EInteger.FromSubstring(str, numerStart, numerEnd);
      }
      if (haveDenominator) {
        EInteger denom = null;
        int denomInt = 0;
        tmpoffset = 1;
        haveDigits = false;
        if (i == endStr) {
          throw new NumberFormatException();
        }
        numerStart = i;
        for (; i < endStr; ++i) {
          if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            haveDigits = true;
            int thisdigit = (int)(str.charAt(i) - '0');
            numerEnd = i + 1;
            if (denomInt <= MaxSafeInt) {
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
        if (denomInt > MaxSafeInt) {
          denom = EInteger.FromSubstring(str, numerStart, numerEnd);
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
      if (ndenom == null ? (ndenomInt == 0) : ndenom.isZero()) {
        throw new NumberFormatException();
      }
      ERational erat = Create(
          numer == null ? EInteger.FromInt32(numerInt) : numer,
          ndenom == null ? EInteger.FromInt32(ndenomInt) : ndenom);
      return negative ? erat.Negate() : erat;
    }

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

    public ERational Abs() {
      if (this.isNegative()) {
        return new ERational(
            this.unsignedNumerator,
            this.denominator,
            this.flags & ~BigNumberFlags.FlagNegative);
      }
      return this;
    }

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

    public int compareTo(ERational other) {
      return this.CompareToValue(other);
    }

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
          return (!second.isNegative()) ? first :
(first.getDenominator().compareTo(second.getDenominator()) < 0 ?

              first : second);
        } else {
          return second.isNegative() ? second :
(first.getDenominator().compareTo(second.getDenominator()) > 0 ?

              first : second);
        }
      }
      return cmp < 0 ? first : second;
    }

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

    public int compareTo(int intOther) {
      return this.CompareToValue(ERational.FromInt32(intOther));
    }

    public int CompareToValue(int intOther) {
      return this.CompareToValue(ERational.FromInt32(intOther));
    }

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
      return new ERational(
          ad.Abs(),
          bc.Abs(),
          resultNeg ? BigNumberFlags.FlagNegative : 0);
    }

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

    public boolean equals(ERational other) {
      return this.equals((Object)other);
    }

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

    public boolean IsInfinity() {
      return (this.flags & BigNumberFlags.FlagInfinity) != 0;
    }

    public boolean IsNaN() {
      return (this.flags & BigNumberFlags.FlagNaN) != 0;
    }

    public boolean IsNegativeInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
            BigNumberFlags.FlagNegative)) ==
        (BigNumberFlags.FlagInfinity | BigNumberFlags.FlagNegative);
    }

    public boolean IsPositiveInfinity() {
      return (this.flags & (BigNumberFlags.FlagInfinity |
            BigNumberFlags.FlagNegative)) == BigNumberFlags.FlagInfinity;
    }

    public boolean IsQuietNaN() {
      return (this.flags & BigNumberFlags.FlagQuietNaN) != 0;
    }

    public boolean IsSignalingNaN() {
      return (this.flags & BigNumberFlags.FlagSignalingNaN) != 0;
    }

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
        new ERational(
          ac.Abs(),
          bd.Abs(),
          resultNeg ? BigNumberFlags.FlagNegative : 0);
    }

    public ERational Negate() {
      return new ERational(
          this.unsignedNumerator,
          this.denominator,
          this.flags ^ BigNumberFlags.FlagNegative);
    }

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
      EInteger quo = ad.Divide(bc); // Find the integer quotient
      EInteger tnum = quo.Multiply(otherValue.getNumerator());
      EInteger tden = otherValue.getDenominator();
      EInteger thisDen = this.getDenominator();
      ad = this.getNumerator().Multiply(tden);
      bc = thisDen.Multiply(tnum);
      tden = tden.Multiply(thisDen);
      ad = ad.Subtract(bc);
      return new ERational(
          ad.Abs(),
          tden.Abs(),
          resultNeg ? BigNumberFlags.FlagNegative : 0);
    }

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

    public ERational ToLowestTerms() {
      if (!this.isFinite()) {
        return this;
      }
      if (this.isZero()) {
        return this.isNegative() ? NegativeZero : Zero;
      }
      EInteger num = this.getNumerator();
      EInteger den = this.denominator;
      EInteger gcd = num.Abs().Gcd(den);
      return Create(num.Divide(gcd), den.Divide(gcd));
    }

    public EInteger ToSizedEInteger(int maxBitLength) {
      if (maxBitLength < 0) {
        throw new IllegalArgumentException("maxBitLength (" + maxBitLength + ") is" +
          "\u0020not greater or equal to 0");
      }
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      EInteger unum = this.getUnsignedNumerator();
      EInteger uden = this.denominator;
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

    public EInteger ToSizedEIntegerIfExact(int maxBitLength) {
      if (maxBitLength < 0) {
        throw new IllegalArgumentException("maxBitLength (" + maxBitLength + ") is" +
          "\u0020not greater or equal to 0");
      }
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      EInteger unum = this.getUnsignedNumerator();
      EInteger uden = this.denominator;
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

    public EInteger ToEInteger() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      return this.getNumerator().Divide(this.denominator);
    }

/**
 * @deprecated Renamed to ToEIntegerIfExact.
 */
@Deprecated
    public EInteger ToEIntegerExact() {
      return this.ToEIntegerIfExact();
    }

    public EInteger ToEIntegerIfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      if (this.denominator.isEven() && !this.unsignedNumerator.isEven()) {
        // Even denominator, odd numerator, so not an integer
        throw new ArithmeticException("Value is not an integer");
      }
      EInteger rem;
      EInteger quo;
      EInteger[] divrem = this.getNumerator().DivRem(this.denominator);
      quo = divrem[0];
      rem = divrem[1];
      if (!rem.isZero()) {
        throw new ArithmeticException("Value is not an integer");
      }
      return quo;
    }

    public EDecimal ToEDecimal() {
      return this.ToEDecimal(null);
    }

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
 * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal() {
      return this.ToEDecimal();
    }

/**
 * @deprecated Renamed to ToEDecimal.
 */
@Deprecated
    public EDecimal ToExtendedDecimal(EContext ctx) {
      return this.ToEDecimal(ctx);
    }

/**
 * @deprecated Renamed to ToEDecimalExactIfPossible.
 */
@Deprecated
    public EDecimal ToExtendedDecimalExactIfPossible(EContext ctx) {
      return this.ToEDecimalExactIfPossible(ctx);
    }

    public EFloat ToEFloat() {
      return this.ToEFloat(null);
    }

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
 * @deprecated Renamed to ToEFloat.
 */
@Deprecated
    public EFloat ToExtendedFloat() {
      return this.ToEFloat();
    }

/**
 * @deprecated Renamed to ToEFloat.
 */
@Deprecated
    public EFloat ToExtendedFloat(EContext ctx) {
      return this.ToEFloat(ctx);
    }

/**
 * @deprecated Renamed to ToEFloatExactIfPossible.
 */
@Deprecated
    public EFloat ToExtendedFloatExactIfPossible(EContext ctx) {
      return this.ToEFloatExactIfPossible(ctx);
    }

    public float ToSingle() {
      if (!this.isFinite()) {
        return this.ToEFloat(EContext.Binary32).ToSingle();
      }
      if (this.isNegative() && this.isZero()) {
        return EFloat.NegativeZero.ToSingle();
      }
      return EFloat.FromEInteger(this.getNumerator())
        .Divide(EFloat.FromEInteger(this.denominator), EContext.Binary32)
        .ToSingle();
    }

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

    public ERational Increment() {
      return this.Add(FromInt32(1));
    }

    public ERational Decrement() {
      return this.Subtract(FromInt32(1));
    }

    public ERational Add(int v) {
      return this.Add(FromInt32(v));
    }

    public ERational Subtract(int v) {
      return this.Subtract(FromInt32(v));
    }

    public ERational Multiply(int v) {
      return this.Multiply(FromInt32(v));
    }

    public ERational Divide(int v) {
      return this.Divide(FromInt32(v));
    }

    public ERational Remainder(int v) {
      return this.Remainder(FromInt32(v));
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

    public byte ToByteUnchecked() {
      return this.isFinite() ? this.ToEInteger().ToByteUnchecked() : (byte)0;
    }

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

    public static ERational FromByte(byte inputByte) {
      int val = ((int)inputByte) & 0xff;
      return FromInt32(val);
    }

    public short ToInt16Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(15);
      return this.isZero() ? ((short)0) : this.ToEInteger().ToInt16Checked();
    }

    public short ToInt16Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt16Unchecked() : (short)0;
    }

    public short ToInt16IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(15);
      return this.isZero() ? ((short)0) :
        this.ToEIntegerIfExact().ToInt16Checked();
    }

    public static ERational FromInt16(short inputInt16) {
      int val = (int)inputInt16;
      return FromInt32(val);
    }

    public int ToInt32Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(31);
      return this.isZero() ? ((int)0) : this.ToEInteger().ToInt32Checked();
    }

    public int ToInt32Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt32Unchecked() : (int)0;
    }

    public int ToInt32IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(31);
      return this.isZero() ? ((int)0) : this.ToEIntegerIfExact().ToInt32Checked();
    }

    public static ERational FromBoolean(boolean boolValue) {
      return FromInt32(boolValue ? 1 : 0);
    }

    public static ERational FromInt32(int inputInt32) {
      return FromEInteger(EInteger.FromInt32(inputInt32));
    }

    public long ToInt64Checked() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(63);
      return this.isZero() ? 0L : this.ToEInteger().ToInt64Checked();
    }

    public long ToInt64Unchecked() {
      return this.isFinite() ? this.ToEInteger().ToInt64Unchecked() : 0L;
    }

    public long ToInt64IfExact() {
      if (!this.isFinite()) {
        throw new ArithmeticException("Value is infinity or NaN");
      }
      this.CheckTrivialOverflow(63);
      return this.isZero() ? 0L : this.ToEIntegerIfExact().ToInt64Checked();
    }

    public static ERational FromInt64(long inputInt64) {
      return FromEInteger(EInteger.FromInt64(inputInt64));
    }

    // End integer conversions
  }
