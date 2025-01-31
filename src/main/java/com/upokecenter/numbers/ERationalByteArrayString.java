package com.upokecenter.numbers;

  final class ERationalByteArrayString {
private ERationalByteArrayString() {
}
    private static final int MaxSafeInt = EDecimal.MaxSafeInt;

    public static ERational FromString(
      byte[] chars,
      int offset,
      int length,
      boolean throwException) {
      int tmpoffset = offset;
      if (chars == null) {
        if (!throwException) {
          return null;
        } else {
          throw new NullPointerException("chars");
        }
      }
      if (tmpoffset < 0) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException("offset(" + tmpoffset + ") is" +
            "\u0020less" + "\u0020than " + "0");
        }
      }
      if (tmpoffset > chars.length) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException("offset(" + tmpoffset + ") is" +
            "\u0020more" + "\u0020than " + chars.length);
        }
      }
      if (length < 0) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException("length(" + length + ") is less than " +
            "0");
        }
      }
      if (length > chars.length) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException("length(" + length + ") is more than " +
            chars.length);
        }
      }
      if (chars.length - tmpoffset < length) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException("chars's length minus " +
            tmpoffset + "(" + (chars.length - tmpoffset) + ") is less than " +
            length);
        }
      }
      if (length == 0) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException();
        }
      }
      boolean negative = false;
      int endStr = tmpoffset + length;
      if (chars[tmpoffset] == '+' || chars[tmpoffset] == '-') {
        negative = chars[tmpoffset] == '-';
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
        if ((chars[i] == 'I' || chars[i] == 'i') &&
          (chars[i + 1] == 'N' || chars[i + 1] == 'n') &&
          (chars[i + 2] == 'F' || chars[i + 2] == 'f') &&
          (chars[i + 3] == 'I' || chars[i + 3] == 'i') && (chars[i + 4] ==
          'N' || chars[i + 4] == 'n') && (chars[i + 5] == 'I' || chars[i + 5] ==
            'i') &&
          (chars[i + 6] == 'T' || chars[i + 6] == 't') && (chars[i + 7] ==
          'Y' || chars[i + 7] == 'y')) {
          return negative ? ERational.NegativeInfinity :
            ERational.PositiveInfinity;
        }
      }
      if (i + 3 == endStr) {
        if ((chars[i] == 'I' || chars[i] == 'i') &&
          (chars[i + 1] == 'N' || chars[i + 1] == 'n') && (chars[i + 2] ==
          'F' || chars[i + 2] == 'f')) {
          return negative ? ERational.NegativeInfinity :
            ERational.PositiveInfinity;
        }
      }
      int numerStart = 0;
      if (i + 3 <= endStr) {
        // Quiet NaN
        if ((chars[i] == 'N' || chars[i] == 'n') && (chars[i + 1] == 'A' ||
          chars[i +
            1] == 'a') && (chars[i + 2] == 'N' || chars[i + 2] == 'n')) {
          if (i + 3 == endStr) {
            return (!negative) ? ERational.NaN : ERational.NaN.Negate();
          }
          i += 3;
          numerStart = i;
          for (; i < endStr; ++i) {
            if (chars[i] >= '0' && chars[i] <= '9') {
              int thisdigit = (int)(chars[i] - '0');
              if (numerInt <= MaxSafeInt) {
                numerInt *= 10;
                numerInt += thisdigit;
              }
            } else {
              if (!throwException) {
                return null;
              } else {
                throw new NumberFormatException();
              }
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
        if ((chars[i] == 'S' || chars[i] == 's') && (chars[i + 1] == 'N' ||
          chars[i +
            1] == 'n') && (chars[i + 2] == 'A' || chars[i + 2] == 'a') &&
          (chars[i + 3] == 'N' || chars[i + 3] == 'n')) {
          if (i + 4 == endStr) {
            return (!negative) ? ERational.SignalingNaN :
              ERational.SignalingNaN.Negate();
          }
          i += 4;
          numerStart = i;
          for (; i < endStr; ++i) {
            if (chars[i] >= '0' && chars[i] <= '9') {
              int thisdigit = (int)(chars[i] - '0');
              haveDigits = haveDigits || thisdigit != 0;
              if (numerInt <= MaxSafeInt) {
                numerInt *= 10;
                numerInt += thisdigit;
              }
            } else {
              if (!throwException) {
                return null;
              } else {
                throw new NumberFormatException();
              }
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
        if (chars[i] >= '0' && chars[i] <= '9') {
          int thisdigit = (int)(chars[i] - '0');
          numerEnd = i + 1;
          if (numerInt <= MaxSafeInt) {
            numerInt *= 10;
            numerInt += thisdigit;
          }
          haveDigits = true;
        } else if (chars[i] == '/') {
          haveDenominator = true;
          ++i;
          break;
        } else {
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException();
          }
        }
      }
      if (!haveDigits) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException();
        }
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
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException();
          }
        }
        numerStart = i;
        for (; i < endStr; ++i) {
          if (chars[i] >= '0' && chars[i] <= '9') {
            haveDigits = true;
            int thisdigit = (int)(chars[i] - '0');
            numerEnd = i + 1;
            if (denomInt <= MaxSafeInt) {
              denomInt *= 10;
              denomInt += thisdigit;
            }
          } else {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException();
            }
          }
        }
        if (!haveDigits) {
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException();
          }
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
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException();
        }
      }
      if (ndenom == null ? (ndenomInt == 0) : ndenom.isZero()) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException();
        }
      }
      ERational erat = ERational.Create(
          numer == null ? EInteger.FromInt32(numerInt) : numer,
          ndenom == null ? EInteger.FromInt32(ndenomInt) : ndenom);
      return negative ? erat.Negate() : erat;
    }
  }
