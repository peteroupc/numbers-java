package com.upokecenter.numbers;

  final class ERationalTextString {
private ERationalTextString() {
}
    private static final int MaxSafeInt = EDecimal.MaxSafeInt;

    public static ERational FromString(
      String chars,
      int offset,
      int length) {
      int tmpoffset = offset;
      if (chars == null) {
        throw new NullPointerException("chars");
      }
      if (tmpoffset < 0) {
        throw new NumberFormatException("offset(" + tmpoffset + ") is less than " +
          "0");
      }
      if (tmpoffset > chars.length()) {
        throw new NumberFormatException("offset(" + tmpoffset + ") is more than " +
          chars.length());
      }
      if (length < 0) {
        throw new NumberFormatException("length(" + length + ") is less than " +
          "0");
      }
      if (length > chars.length()) {
        throw new NumberFormatException("length(" + length + ") is more than " +
          chars.length());
      }
      if (chars.length() - tmpoffset < length) {
        throw new NumberFormatException("chars's length minus " + tmpoffset + "(" +
          (chars.length() - tmpoffset) + ") is less than " + length);
      }
      if (length == 0) {
        throw new NumberFormatException();
      }
      boolean negative = false;
      int endStr = tmpoffset + length;
      if (chars.charAt(tmpoffset) == '+' || chars.charAt(tmpoffset) == '-') {
        negative = chars.charAt(tmpoffset) == '-';
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
        if ((chars.charAt(i) == 'I' || chars.charAt(i) == 'i') &&
          (chars.charAt(i + 1) == 'N' || chars.charAt(i + 1) == 'n') &&
          (chars.charAt(i + 2) == 'F' || chars.charAt(i + 2) == 'f') &&
          (chars.charAt(i + 3) == 'I' || chars.charAt(i + 3) == 'i') && (chars.charAt(i + 4) ==
'N' ||
            chars.charAt(i + 4) == 'n') && (chars.charAt(i + 5) == 'I' || chars.charAt(i + 5) ==
'i') &&
          (chars.charAt(i + 6) == 'T' || chars.charAt(i + 6) == 't') && (chars.charAt(i + 7) ==
'Y' ||
            chars.charAt(i + 7) == 'y')) {
          return negative ? ERational.NegativeInfinity :
ERational.PositiveInfinity;
        }
      }
      if (i + 3 == endStr) {
        if ((chars.charAt(i) == 'I' || chars.charAt(i) == 'i') &&
          (chars.charAt(i + 1) == 'N' || chars.charAt(i + 1) == 'n') && (chars.charAt(i + 2) ==
'F' ||
            chars.charAt(i + 2) == 'f')) {
          return negative ? ERational.NegativeInfinity :
ERational.PositiveInfinity;
        }
      }
      int numerStart = 0;
      if (i + 3 <= endStr) {
        // Quiet NaN
        if ((chars.charAt(i) == 'N' || chars.charAt(i) == 'n') && (chars.charAt(i + 1) == 'A' ||
chars.charAt(i +
              1) == 'a') && (chars.charAt(i + 2) == 'N' || chars.charAt(i + 2) == 'n')) {
          if (i + 3 == endStr) {
            return (!negative) ? ERational.NaN : ERational.NaN.Negate();
          }
          i += 3;
          numerStart = i;
          for (; i < endStr; ++i) {
            if (chars.charAt(i) >= '0' && chars.charAt(i) <= '9') {
              int thisdigit = (int)(chars.charAt(i) - '0');
              if (numerInt <= MaxSafeInt) {
                numerInt *= 10;
                numerInt += thisdigit;
              }
            } else {
              throw new NumberFormatException();
            }
          }
          if (numerInt > MaxSafeInt) {
            numer = EInteger.FromSubstring(chars, numerStart, endStr);
            return ERational.CreateNaN(numer, false, negative);
          } else {
            return ERational.CreateNaN(
               EInteger.FromInt32(numerInt),
               false,
               negative);
          }
        }
      }
      if (i + 4 <= endStr) {
        // Signaling NaN
        if ((chars.charAt(i) == 'S' || chars.charAt(i) == 's') && (chars.charAt(i + 1) == 'N' ||
chars.charAt(i +
              1) == 'n') && (chars.charAt(i + 2) == 'A' || chars.charAt(i + 2) == 'a') &&
          (chars.charAt(i + 3) == 'N' || chars.charAt(i + 3) == 'n')) {
          if (i + 4 == endStr) {
            return (!negative) ? ERational.SignalingNaN :
ERational.SignalingNaN.Negate();
          }
          i += 4;
          numerStart = i;
          for (; i < endStr; ++i) {
            if (chars.charAt(i) >= '0' && chars.charAt(i) <= '9') {
              int thisdigit = (int)(chars.charAt(i) - '0');
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
            numer = EInteger.FromSubstring(chars, numerStart, endStr);
            return ERational.CreateNaN(numer, true, negative);
          } else {
            return ERational.CreateNaN(
              EInteger.FromInt32(numerInt),
              true,
              negative);
          }
        }
      }
      // Ordinary number
      numerStart = i;
      int numerEnd = i;
      for (; i < endStr; ++i) {
        if (chars.charAt(i) >= '0' && chars.charAt(i) <= '9') {
          int thisdigit = (int)(chars.charAt(i) - '0');
          numerEnd = i + 1;
          if (numerInt <= MaxSafeInt) {
            numerInt *= 10;
            numerInt += thisdigit;
          }
          haveDigits = true;
        } else if (chars.charAt(i) == '/') {
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
        numer = EInteger.FromSubstring(chars, numerStart, numerEnd);
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
          if (chars.charAt(i) >= '0' && chars.charAt(i) <= '9') {
            haveDigits = true;
            int thisdigit = (int)(chars.charAt(i) - '0');
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
          denom = EInteger.FromSubstring(chars, numerStart, numerEnd);
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
      ERational erat = ERational.Create(
          numer == null ? EInteger.FromInt32(numerInt) : numer,
          ndenom == null ? EInteger.FromInt32(ndenomInt) : ndenom);
      return negative ? erat.Negate() : erat;
    }
}
