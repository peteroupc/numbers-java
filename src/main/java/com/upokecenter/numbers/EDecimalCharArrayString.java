package com.upokecenter.numbers;

  final class EDecimalCharArrayString {
private EDecimalCharArrayString() {
}
    private static final int MaxSafeInt = EDecimal.MaxSafeInt;

    static EDecimal FromString(
      char[] chars,
      int offset,
      int length,
      EContext ctx,
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
        } else { throw new NumberFormatException("offset(" + tmpoffset + ") is" +
"\u0020less" + "\u0020than " + "0");
}
      }
      if (tmpoffset > chars.length) {
        if (!throwException) {
          return null;
        } else { throw new NumberFormatException("offset(" + tmpoffset + ") is" +
"\u0020more" + "\u0020than " + chars.length);
}
      }
      if (length <= 0) {
        if (length == 0) {
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException("length is 0");
          }
        }
        if (!throwException) {
          return null;
        } else {
  throw new NumberFormatException("length(" + length + ") is less than " + "0");
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
        } else { throw new NumberFormatException("chars's length minus " +
tmpoffset + "(" + (chars.length - tmpoffset) + ") is less than " + length);
}
      }
      boolean negative = false;
      int endStr = tmpoffset + length;
      char c = chars[tmpoffset];
      if (c == '-') {
        negative = true;
        ++tmpoffset;
        if (tmpoffset >= endStr) {
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException();
          }
        }
        c = chars[tmpoffset];
      } else if (chars[tmpoffset] == '+') {
        ++tmpoffset;
        if (tmpoffset >= endStr) {
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException();
          }
        }
        c = chars[tmpoffset];
      }
      int i = tmpoffset;
      if (c < '0' || c > '9') {
        EDecimal ed = ParseSpecialValue(
          chars,
          i,
          endStr,
          negative,
          ctx,
          throwException);
        if (ed != null) {
          return ed;
        }
      }
      if (ctx != null && ctx.getHasMaxPrecision() && ctx.getHasExponentRange() &&
        !ctx.isSimplified()) {
        return ParseOrdinaryNumberLimitedPrecision(
          chars,
          i,
          endStr,
          negative,
          ctx,
          throwException);
      } else {
        return ParseOrdinaryNumber(
          chars,
          i,
          endStr,
          negative,
          ctx,
          throwException);
      }
    }

    private static EDecimal ParseSpecialValue(
      char[] chars,
      int i,
      int endStr,
      boolean negative,
      EContext ctx,
      boolean throwException) {
      int mantInt = 0;
      EInteger mant = null;
      boolean haveDigits = false;
      int digitStart = 0;
      if (i + 8 == endStr) {
        if ((chars[i] == 'I' || chars[i] == 'i') &&
          (chars[i + 1] == 'N' || chars[i + 1] == 'n') &&
          (chars[i + 2] == 'F' || chars[i + 2] == 'f') &&
          (chars[i + 3] == 'I' || chars[i + 3] == 'i') && (chars[i + 4] ==
            'N' ||
            chars[i + 4] == 'n') && (chars[i + 5] == 'I' || chars[i + 5] ==
            'i') &&
          (chars[i + 6] == 'T' || chars[i + 6] == 't') && (chars[i + 7] ==
            'Y' || chars[i + 7] == 'y')) {
          if (ctx != null && ctx.isSimplified() && i < endStr) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException("Infinity not allowed");
            }
          }
          return negative ? EDecimal.NegativeInfinity :
            EDecimal.PositiveInfinity;
        }
      }
      if (i + 3 == endStr) {
        if ((chars[i] == 'I' || chars[i] == 'i') &&
          (chars[i + 1] == 'N' || chars[i + 1] == 'n') && (chars[i + 2] ==
            'F' || chars[i + 2] == 'f')) {
          if (ctx != null && ctx.isSimplified() && i < endStr) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException("Infinity not allowed");
            }
          }
          return negative ? EDecimal.NegativeInfinity :
            EDecimal.PositiveInfinity;
        }
      }
      if (i + 3 <= endStr) {
        // Quiet NaN
        if ((chars[i] == 'N' || chars[i] == 'n') && (chars[i + 1] == 'A' ||
            chars[i +
              1] == 'a') && (chars[i + 2] == 'N' || chars[i + 2] == 'n')) {
          if (ctx != null && ctx.isSimplified() && i < endStr) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException("NaN not allowed");
            }
          }
          int flags2 = (negative ? BigNumberFlags.FlagNegative : 0) |
            BigNumberFlags.FlagQuietNaN;
          if (i + 3 == endStr) {
            return (!negative) ? EDecimal.NaN : new EDecimal(
                FastIntegerFixed.Zero,
                FastIntegerFixed.Zero,
                (byte)flags2);
          }
          i += 3;
          FastInteger digitCount = new FastInteger(0);
          FastInteger maxDigits = null;
          haveDigits = false;
          if (ctx != null && ctx.getHasMaxPrecision()) {
            maxDigits = FastInteger.FromBig(ctx.getPrecision());
            if (ctx.getClampNormalExponents()) {
              maxDigits.Decrement();
            }
          }
          digitStart = i;
          for (; i < endStr; ++i) {
            if (chars[i] >= '0' && chars[i] <= '9') {
              int thisdigit = (int)(chars[i] - '0');
              haveDigits = haveDigits || thisdigit != 0;
              if (mantInt <= MaxSafeInt) {
                // multiply by 10
                mantInt *= 10;
                mantInt += thisdigit;
              }
              if (haveDigits && maxDigits != null) {
                digitCount.Increment();
                if (digitCount.compareTo(maxDigits) > 0) {
                  // NaN contains too many digits
                  if (!throwException) {
                    return null;
                  } else {
                    throw new NumberFormatException();
                  }
                }
              }
            } else {
              if (!throwException) {
                return null;
              } else {
                throw new NumberFormatException();
              }
            }
          }
          if (mantInt > MaxSafeInt) {
            mant = EInteger.FromSubstring(chars, digitStart, endStr);
          }
          EInteger bigmant = (mant == null) ? (EInteger.FromInt32(mantInt)) :
            mant;
          flags2 = (negative ? BigNumberFlags.FlagNegative : 0) |
            BigNumberFlags.FlagQuietNaN;
          return EDecimal.CreateWithFlags(
              FastIntegerFixed.FromBig(bigmant),
              FastIntegerFixed.Zero,
              flags2);
        }
      }
      if (i + 4 <= endStr) {
        // Signaling NaN
        if ((chars[i] == 'S' || chars[i] == 's') && (chars[i + 1] == 'N' ||
            chars[i +
              1] == 'n') && (chars[i + 2] == 'A' || chars[i + 2] == 'a') &&
          (chars[i + 3] == 'N' || chars[i + 3] == 'n')) {
          if (ctx != null && ctx.isSimplified() && i < endStr) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException("NaN not allowed");
            }
          }
          if (i + 4 == endStr) {
            int flags2 = (negative ? BigNumberFlags.FlagNegative : 0) |
              BigNumberFlags.FlagSignalingNaN;
            return (!negative) ? EDecimal.SignalingNaN :
              new EDecimal(
                FastIntegerFixed.Zero,
                FastIntegerFixed.Zero,
                (byte)flags2);
          }
          i += 4;
          FastInteger digitCount = new FastInteger(0);
          FastInteger maxDigits = null;
          haveDigits = false;
          if (ctx != null && ctx.getHasMaxPrecision()) {
            maxDigits = FastInteger.FromBig(ctx.getPrecision());
            if (ctx.getClampNormalExponents()) {
              maxDigits.Decrement();
            }
          }
          digitStart = i;
          for (; i < endStr; ++i) {
            if (chars[i] >= '0' && chars[i] <= '9') {
              int thisdigit = (int)(chars[i] - '0');
              haveDigits = haveDigits || thisdigit != 0;
              if (mantInt <= MaxSafeInt) {
                // multiply by 10
                mantInt *= 10;
                mantInt += thisdigit;
              }
              if (haveDigits && maxDigits != null) {
                digitCount.Increment();
                if (digitCount.compareTo(maxDigits) > 0) {
                  // NaN contains too many digits
                  if (!throwException) {
                    return null;
                  } else {
                    throw new NumberFormatException();
                  }
                }
              }
            } else {
              if (!throwException) {
                return null;
              } else {
                throw new NumberFormatException();
              }
            }
          }
          if (mantInt > MaxSafeInt) {
            mant = EInteger.FromSubstring(chars, digitStart, endStr);
          }
          int flags3 = (negative ? BigNumberFlags.FlagNegative : 0) |
            BigNumberFlags.FlagSignalingNaN;
          EInteger bigmant = (mant == null) ? (EInteger.FromInt32(mantInt)) :
            mant;
          return EDecimal.CreateWithFlags(
              bigmant,
              EInteger.FromInt32(0),
              flags3);
        }
      }
      return null;
    }

    private static EDecimal ParseOrdinaryNumberLimitedPrecision(
      char[] chars,
      int offset,
      int endStr,
      boolean negative,
      EContext ctx,
      boolean throwException) {
      int tmpoffset = offset;
      if (chars == null) {
        if (!throwException) {
          return null;
        } else {
          throw new NullPointerException("chars");
        }
      }
      if (ctx == null || !ctx.getHasMaxPrecision()) {
        if (!throwException) {
          return null;
        } else {
          throw new IllegalStateException();
        }
      }
      boolean haveDecimalPoint = false;
      boolean haveDigits = false;
      boolean haveExponent = false;
      int newScaleInt = 0;
      int i = tmpoffset;
      long mantissaLong = 0L;
      // Ordinary number
      int digitStart = i;
      int digitEnd = i;
      int decimalDigitStart = i;
      boolean haveNonzeroDigit = false;
      int decimalPrec = 0;
      int decimalDigitEnd = i;
      boolean nonzeroBeyondMax = false;
      boolean beyondMax = false;
      int lastdigit = -1;
      EInteger precisionPlusTwo = ctx.getPrecision().Add(2);
      for (; i < endStr; ++i) {
        char ch = chars[i];
        if (ch >= '0' && ch <= '9') {
          int thisdigit = (int)(ch - '0');
          haveDigits = true;
          haveNonzeroDigit |= thisdigit != 0;
          if (beyondMax || (precisionPlusTwo.compareTo(decimalPrec) < 0 &&
              mantissaLong == Long.MAX_VALUE)) {
            // Well beyond maximum precision, significand is
            // max or bigger
            beyondMax = true;
            if (thisdigit != 0) {
              nonzeroBeyondMax = true;
            }
            if (!haveDecimalPoint) {
              // NOTE: Absolute value will not be more than
              // the sequence portion's length, so will fit comfortably
              // in an 'int'.
              newScaleInt = (newScaleInt + 1);
            }
            continue;
          }
          lastdigit = thisdigit;
          if (haveNonzeroDigit) {
            ++decimalPrec;
          }
          if (haveDecimalPoint) {
            decimalDigitEnd = i + 1;
          } else {
            digitEnd = i + 1;
          }
          if (mantissaLong < 922337203685477580L ||
               (mantissaLong == 922337203685477580L && thisdigit <= 7)) {
            mantissaLong *= 10;
            mantissaLong += thisdigit;
          } else {
            mantissaLong = Long.MAX_VALUE;
          }
          if (haveDecimalPoint) {
            // NOTE: Absolute value will not be more than
            // the sequence portion's length, so will fit comfortably
            // in an 'int'.
            newScaleInt = (newScaleInt - 1);
          }
        } else if (ch == '.') {
          if (haveDecimalPoint) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException();
            }
          }
          haveDecimalPoint = true;
          decimalDigitStart = i + 1;
          decimalDigitEnd = i + 1;
        } else if (ch == 'E' || ch == 'e') {
          haveExponent = true;
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
      int expInt = 0;
      int expoffset = 1;
      int expDigitStart = -1;
      int expPrec = 0;
      boolean zeroMantissa = !haveNonzeroDigit;
      haveNonzeroDigit = false;
      if (haveExponent) {
        haveDigits = false;
        if (i == endStr) {
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException();
          }
        }
        if (chars[i] == '+' || chars[i] == '-') {
          if (chars[i] == '-') {
            expoffset = -1;
          }
          ++i;
        }
        expDigitStart = i;
        for (; i < endStr; ++i) {
          char ch = chars[i];
          if (ch >= '0' && ch <= '9') {
            haveDigits = true;
            int thisdigit = (int)(ch - '0');
            haveNonzeroDigit |= thisdigit != 0;
            if (haveNonzeroDigit) {
              ++expPrec;
            }
            if (expInt < 214748364 || (expInt == 214748364 && thisdigit < 7)) {
              expInt *= 10;
              expInt += thisdigit;
            } else {
              expInt = Integer.MAX_VALUE;
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
        expInt *= expoffset;
        if (expPrec > 12) {
          // Exponent that can't be compensated by digit
          // length without remaining higher than Integer.MAX_VALUE
          if (expoffset < 0) {
            return EDecimal.SignalUnderflow(ctx, negative, zeroMantissa);
          } else {
            return EDecimal.SignalOverflow(ctx, negative, zeroMantissa);
          }
        }
      }
      if (i != endStr) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException();
        }
      }
      if (expInt != Integer.MAX_VALUE && expInt > -Integer.MAX_VALUE &&
        mantissaLong != Long.MAX_VALUE) {
        // Low precision, low exponent
        long finalexp = (long)expInt + (long)newScaleInt;
        if (negative) {
          mantissaLong = -mantissaLong;
        }
        EDecimal eret = EDecimal.Create(mantissaLong, finalexp);
        if (negative && zeroMantissa) {
          eret = eret.Negate();
        }
        return eret.RoundToPrecision(ctx);
      }
      EInteger mant = null;
      EInteger exp = (!haveExponent) ? EInteger.FromInt32(0) :
        EInteger.FromSubstring(chars, expDigitStart, endStr);
      if (expoffset < 0) {
        exp = exp.Negate();
      }
      exp = exp.Add(newScaleInt);
      if (nonzeroBeyondMax) {
        exp = exp.Subtract(1);
        ++decimalPrec;
      }
      if (ctx.getHasExponentRange()) {
        EInteger adjExpUpperBound = exp.Add(decimalPrec).Subtract(1);
        EInteger adjExpLowerBound = exp;
        EInteger eTiny = ctx.getEMin().Subtract(ctx.getPrecision().Subtract(1));
        eTiny = eTiny.Subtract(1);
        // System.out.println("exp=" + adjExpLowerBound + "~" +
        // adjExpUpperBound + ", emin={0} emax={1}", ctx.getEMin(), ctx.getEMax());
        if (adjExpUpperBound.compareTo(eTiny) < 0) {
          return EDecimal.SignalUnderflow(ctx, negative, zeroMantissa);
        } else if (adjExpLowerBound.compareTo(ctx.getEMax()) > 0) {
          return EDecimal.SignalOverflow(ctx, negative, zeroMantissa);
        }
      }
      if (zeroMantissa) {
        EDecimal ef = EDecimal.Create(
            EInteger.FromInt32(0),
            exp);
        if (negative) {
          ef = ef.Negate();
        }
        return ef.RoundToPrecision(ctx);
      } else if (decimalDigitStart != decimalDigitEnd) {
        char[] ctmpstr = Extras.CharsConcat(
            chars,
            digitStart,
            digitEnd - digitStart,
            chars,
            decimalDigitStart,
            decimalDigitEnd - decimalDigitStart);
        mant = EInteger.FromString(ctmpstr);
      } else {
        mant = EInteger.FromSubstring(chars, digitStart, digitEnd);
      }
      if (nonzeroBeyondMax) {
        mant = mant.Multiply(10).Add(1);
      }
      if (negative) {
        mant = mant.Negate();
      }
      return EDecimal.Create(mant, exp)
        .RoundToPrecision(ctx);
    }

    private static EDecimal ParseOrdinaryNumberNoContext(
      char[] chars,
      int i,
      int endStr,
      boolean negative,
      boolean throwException) {
      // NOTE: Negative sign at beginning was omitted
      // from the sequence portion
      int mantInt = 0;
      EInteger mant = null;
      boolean haveDecimalPoint = false;
      boolean haveExponent = false;
      int newScaleInt = 0;
      boolean haveDigits = false;
      int digitStart = 0;
      EInteger newScale = null;
      // Ordinary number
      if (endStr - i == 1) {
        char tch = chars[i];
        if (tch >= '0' && tch <= '9') {
          // String portion is a single digit
          int si = (int)(tch - '0');
          return negative ? ((si == 0) ? EDecimal.NegativeZero :
              EDecimal.FromCache(-si)) : EDecimal.FromCache(si);
        }
      }
      digitStart = i;
      int digitEnd = i;
      int decimalDigitStart = i;
      boolean haveNonzeroDigit = false;
      int decimalPrec = 0;
      int firstdigit = -1;
      int decimalDigitEnd = i;
      int lastdigit = -1;
      int realDigitEnd = -1;
      int realDecimalEnd = -1;
      for (; i < endStr; ++i) {
        char ch = chars[i];
        if (ch >= '0' && ch <= '9') {
          int thisdigit = (int)(ch - '0');
          haveNonzeroDigit |= thisdigit != 0;
          if (firstdigit < 0) {
            firstdigit = thisdigit;
          }
          haveDigits = true;
          lastdigit = thisdigit;
          if (haveNonzeroDigit) {
            ++decimalPrec;
          }
          if (haveDecimalPoint) {
            decimalDigitEnd = i + 1;
          } else {
            digitEnd = i + 1;
          }
          if (mantInt <= MaxSafeInt) {
            // multiply by 10
            mantInt *= 10;
            mantInt += thisdigit;
          }
          if (haveDecimalPoint) {
            if (newScaleInt == Integer.MIN_VALUE ||
              newScaleInt == Integer.MAX_VALUE) {
              newScale = (newScale == null) ? (EInteger.FromInt32(newScaleInt)) : newScale;
              newScale = newScale.Subtract(1);
            } else {
              --newScaleInt;
            }
          }
        } else if (ch == '.') {
          if (haveDecimalPoint) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException();
            }
          }
          haveDecimalPoint = true;
          realDigitEnd = i;
          decimalDigitStart = i + 1;
          decimalDigitEnd = i + 1;
        } else if (ch == 'E' || ch == 'e') {
          realDecimalEnd = i;
          haveExponent = true;
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
      if (realDigitEnd < 0) {
        realDigitEnd = i;
      }
      if (realDecimalEnd < 0) {
        realDecimalEnd = i;
      }
      EDecimal ret = null;
      EInteger exp = null;
      int expInt = 0;
      int expoffset = 1;
      int expDigitStart = -1;
      int expPrec = 0;
      haveNonzeroDigit = false;
      if (haveExponent) {
        haveDigits = false;
        if (i == endStr) {
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException();
          }
        }
        char ch = chars[i];
        if (ch == '+' || ch == '-') {
          if (ch == '-') {
            expoffset = -1;
          }
          ++i;
        }
        expDigitStart = i;
        for (; i < endStr; ++i) {
          ch = chars[i];
          if (ch >= '0' && ch <= '9') {
            haveDigits = true;
            int thisdigit = (int)(ch - '0');
            haveNonzeroDigit |= thisdigit != 0;
            if (haveNonzeroDigit) {
              ++expPrec;
            }
            if (expInt <= MaxSafeInt) {
              expInt *= 10;
              expInt += thisdigit;
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
      }
      if (i != endStr) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException();
        }
      }
      // Calculate newScale if exponent is "small"
      if (haveExponent && expInt <= MaxSafeInt) {
        if (expoffset >= 0 && newScaleInt == 0 && newScale == null) {
          newScaleInt = expInt;
        } else if (newScale == null) {
          long tmplong = newScaleInt;
          if (expoffset < 0) {
            tmplong -= expInt;
          } else if (expInt != 0) {
            tmplong += expInt;
          }
          if (tmplong >= Integer.MAX_VALUE && tmplong <= Integer.MIN_VALUE) {
            newScaleInt = (int)tmplong;
          } else {
            newScale = EInteger.FromInt64(tmplong);
          }
        } else {
          if (expoffset < 0) {
            newScale = newScale.Subtract(expInt);
          } else if (expInt != 0) {
            newScale = newScale.Add(expInt);
          }
        }
      }
      int de = digitEnd;
      int dde = decimalDigitEnd;
      if (!haveExponent && haveDecimalPoint) {
        // No more than 18 digits
        long lv = 0L;
        int expo = -(dde - decimalDigitStart);
        int digitCount = 0;
        if (mantInt <= MaxSafeInt) {
          lv = mantInt;
        } else {
          int vi = 0;
          for (vi = digitStart; vi < de; ++vi) {
            char chvi = chars[vi];

            if (digitCount < 0 || digitCount >= 18) {
              digitCount = -1;
              break;
            } else if (digitCount > 0 || chvi != '0') {
              ++digitCount;
            }
            lv = ((lv * 10) + (int)(chvi - '0'));
          }
          for (vi = decimalDigitStart; vi < dde; ++vi) {
            char chvi = chars[vi];

            if (digitCount < 0 || digitCount >= 18) {
              digitCount = -1;
              break;
            } else if (digitCount > 0 || chvi != '0') {
              ++digitCount;
            }
            lv = ((lv * 10) + (int)(chvi - '0'));
          }
        }
        if (negative) {
          lv = -lv;
        }
        if (digitCount >= 0 && (!negative || lv != 0)) {
          ret = EDecimal.Create(lv, (long)expo);
          return ret;
        }
      }
      // Parse significand if it's "big"
      if (mantInt > MaxSafeInt) {
        if (haveDecimalPoint) {
          if (digitEnd - digitStart == 1 && firstdigit == 0) {
            mant = EInteger.FromSubstring(
                chars,
                decimalDigitStart,
                decimalDigitEnd);
          } else {
            char[] cdecstr = Extras.CharsConcat(
                chars,
                digitStart,
                digitEnd - digitStart,
                chars,
                decimalDigitStart,
                decimalDigitEnd - decimalDigitStart);
            mant = EInteger.FromString(cdecstr);
          }
        } else {
          mant = EInteger.FromSubstring(chars, digitStart, digitEnd);
        }
      }
      if (haveExponent && expInt > MaxSafeInt) {
        // Parse exponent if it's "big"
        exp = EInteger.FromSubstring(chars, expDigitStart, endStr);
        newScale = (newScale == null) ? (EInteger.FromInt32(newScaleInt)) : newScale;
        newScale = (expoffset < 0) ? newScale.Subtract(exp) :
          newScale.Add(exp);
      }
      FastIntegerFixed fastIntScale;
      FastIntegerFixed fastIntMant;
      fastIntScale = (newScale == null) ? FastIntegerFixed.FromInt32(
          newScaleInt) : FastIntegerFixed.FromBig(newScale);
      if (mant == null) {
        fastIntMant = FastIntegerFixed.FromInt32(mantInt);
      } else if (mant.CanFitInInt32()) {
        mantInt = mant.ToInt32Checked();
        fastIntMant = FastIntegerFixed.FromInt32(mantInt);
      } else {
        fastIntMant = FastIntegerFixed.FromBig(mant);
      }
      ret = new EDecimal(
        fastIntMant,
        fastIntScale,
        (byte)(negative ? BigNumberFlags.FlagNegative : 0));
      return ret;
    }

    private static EDecimal ParseOrdinaryNumber(
      char[] chars,
      int i,
      int endStr,
      boolean negative,
      EContext ctx,
      boolean throwException) {
      if (ctx == null) {
        return ParseOrdinaryNumberNoContext(
          chars,
          i,
          endStr,
          negative,
          throwException);
      }
      // NOTE: Negative sign at beginning was omitted
      // from the sequence portion
      int mantInt = 0;
      EInteger mant = null;
      boolean haveDecimalPoint = false;
      boolean haveExponent = false;
      int newScaleInt = 0;
      boolean haveDigits = false;
      int digitStart = 0;
      EInteger newScale = null;
      // Ordinary number
      if (endStr - i == 1) {
        char tch = chars[i];
        if (tch >= '0' && tch <= '9') {
          // String portion is a single digit
          EDecimal cret;
          int si = (int)(tch - '0');
          cret = negative ? ((si == 0) ? EDecimal.NegativeZero :
              EDecimal.FromCache(-si)) : EDecimal.FromCache(si);
          if (ctx != null) {
            cret = EDecimal.GetMathValue(ctx).RoundAfterConversion(cret, ctx);
          }
          return cret;
        }
      }
      digitStart = i;
      int digitEnd = i;
      int decimalDigitStart = i;
      boolean haveNonzeroDigit = false;
      int decimalPrec = 0;
      int decimalDigitEnd = i;
      // NOTE: Also check HasFlagsOrTraps here because
      // it's burdensome to determine which flags have
      // to be set when applying the optimization here
      boolean roundDown = ctx != null && ctx.getHasMaxPrecision() &&
        !ctx.isPrecisionInBits() && (ctx.getRounding() == ERounding.Down ||
          (negative && ctx.getRounding() == ERounding.Ceiling) ||
          (!negative && ctx.getRounding() == ERounding.Floor)) &&
        !ctx.getHasFlagsOrTraps();
      boolean roundHalf = ctx != null && ctx.getHasMaxPrecision() &&
        !ctx.isPrecisionInBits() && (ctx.getRounding() == ERounding.HalfUp ||
          (ctx.getRounding() == ERounding.HalfDown) ||
          (ctx.getRounding() == ERounding.HalfEven)) &&
        !ctx.getHasFlagsOrTraps();
      boolean roundUp = ctx != null && ctx.getHasMaxPrecision() &&
        !ctx.isPrecisionInBits() && (ctx.getRounding() == ERounding.Up ||
          (!negative && ctx.getRounding() == ERounding.Ceiling) ||
          (negative && ctx.getRounding() == ERounding.Floor)) &&
        !ctx.getHasFlagsOrTraps();
      boolean haveIgnoredDigit = false;
      int lastdigit = -1;
      boolean beyondPrecision = false;
      boolean ignoreNextDigit = false;
      int zerorun = 0;
      int realDigitEnd = -1;
      int realDecimalEnd = -1;
      // System.out.println("round half=" + (// roundHalf) +
      // " up=" + roundUp + " down=" + roundDown +
      // " maxprec=" + (ctx != null && ctx.getHasMaxPrecision()));
      for (; i < endStr; ++i) {
        char ch = chars[i];
        if (ch >= '0' && ch <= '9') {
          int thisdigit = (int)(ch - '0');
          haveNonzeroDigit |= thisdigit != 0;
          haveDigits = true;
          beyondPrecision |= ctx != null && ctx.getHasMaxPrecision() &&
            !ctx.isPrecisionInBits() && ctx.getPrecision().compareTo(decimalPrec)
            <= 0;
          if (ctx != null) {
            if (ignoreNextDigit) {
              haveIgnoredDigit = true;
              ignoreNextDigit = false;
            }
            if (roundDown && (haveIgnoredDigit || beyondPrecision)) {
              // "Ignored" digit
              haveIgnoredDigit = true;
            } else if (roundUp && beyondPrecision && !haveIgnoredDigit) {
              if (thisdigit > 0) {
                ignoreNextDigit = true;
              } else {
                roundUp = false;
              }
            } else if (roundHalf && beyondPrecision && !haveIgnoredDigit) {
              if (thisdigit >= 1 && thisdigit < 5) {
                ignoreNextDigit = true;
              } else if (thisdigit > 5 || (thisdigit == 5 &&
                  ctx.getRounding() == ERounding.HalfUp)) {
                roundHalf = false;
                roundUp = true;
                ignoreNextDigit = true;
              } else {
                roundHalf = false;
              }
            }
          }
          if (haveIgnoredDigit) {
            zerorun = 0;
            if (newScaleInt == Integer.MIN_VALUE ||
              newScaleInt == Integer.MAX_VALUE) {
              newScale = (newScale == null) ? (EInteger.FromInt32(newScaleInt)) : newScale;
              newScale = newScale.Add(1);
            } else {
              ++newScaleInt;
            }
          } else {
            lastdigit = thisdigit;
            if (beyondPrecision && thisdigit == 0) {
              ++zerorun;
            } else {
              zerorun = 0;
            }
            if (haveNonzeroDigit) {
              ++decimalPrec;
            }
            if (haveDecimalPoint) {
              decimalDigitEnd = i + 1;
            } else {
              digitEnd = i + 1;
            }
            if (mantInt <= MaxSafeInt) {
              // multiply by 10
              mantInt *= 10;
              mantInt += thisdigit;
            }
          }
          if (haveDecimalPoint) {
            if (newScaleInt == Integer.MIN_VALUE ||
              newScaleInt == Integer.MAX_VALUE) {
              newScale = (newScale == null) ? (EInteger.FromInt32(newScaleInt)) : newScale;
              newScale = newScale.Subtract(1);
            } else {
              --newScaleInt;
            }
          }
        } else if (ch == '.') {
          if (haveDecimalPoint) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException();
            }
          }
          haveDecimalPoint = true;
          realDigitEnd = i;
          decimalDigitStart = i + 1;
          decimalDigitEnd = i + 1;
        } else if (ch == 'E' || ch == 'e') {
          realDecimalEnd = i;
          haveExponent = true;
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
      if (realDigitEnd < 0) {
        realDigitEnd = i;
      }
      if (realDecimalEnd < 0) {
        realDecimalEnd = i;
      }
      if (zerorun > 0 && lastdigit == 0 && (ctx == null ||
          !ctx.getHasFlagsOrTraps())) {
        decimalPrec -= zerorun;
        int nondec = 0;
        // NOTE: This check is apparently needed for correctness
        if (ctx == null || (!ctx.getHasMaxPrecision() ||
            decimalPrec - ctx.getPrecision().ToInt32Checked() > zerorun)) {
          if (haveDecimalPoint) {
            int decdigits = decimalDigitEnd - decimalDigitStart;
            nondec = Math.min(decdigits, zerorun);
            decimalDigitEnd -= nondec;
            int remain = zerorun - nondec;
            digitEnd -= remain;
            // System.out.println("remain={0} nondec={1}
            // newScale={2}",remain,nondec,newScaleInt);
            nondec = zerorun;
          } else {
            digitEnd -= zerorun;
            nondec = zerorun;
          }
          if (newScaleInt > Integer.MIN_VALUE + nondec &&
            newScaleInt < Integer.MAX_VALUE - nondec) {
            newScaleInt += nondec;
          } else {
            newScale = (newScale == null) ? (EInteger.FromInt32(newScaleInt)) : newScale;
            newScale = newScale.Add(nondec);
          }
        }
        // System.out.println("-->zerorun={0} prec={1} [whole={2}, dec={3}]
        // chars={4}",zerorun,decimalPrec,
        // digitEnd-digitStart, decimalDigitEnd-decimalDigitStart, chars);
      }
      // if (ctx != null) {
      // System.out.println("roundup [prec=" + decimalPrec + ", ctxprec=" +
      // (// ctx.getPrecision()) + ", chars=" + (// chars.substring(0,Math.min(20,
      // chars.length))) + "] " + (ctx.getRounding()));
      // }
      // System.out.println("digitRange="+digitStart+"-"+digitEnd+
      // "decdigitRange="+decimalDigitStart+"-"+decimalDigitEnd);
      if (
        roundUp && ctx != null &&
        ctx.getPrecision().compareTo(decimalPrec) < 0) {
        int precdiff = decimalPrec - ctx.getPrecision().ToInt32Checked();
        // System.out.println("precdiff = " + precdiff + " [prec=" + (// decimalPrec) +
        // ",
        // ctxprec=" + ctx.getPrecision() + "]");
        if (precdiff > 1) {
          int precchop = precdiff - 1;
          decimalPrec -= precchop;
          int nondec = precchop;
          // System.out.println("precchop=" + (precchop));
          if (haveDecimalPoint) {
            int decdigits = decimalDigitEnd - decimalDigitStart;
            // System.out.println("decdigits=" + decdigits + " decprecchop=" + (decdigits));
            decimalDigitEnd -= nondec;
            int remain = precchop - nondec;
            digitEnd -= remain;
          } else {
            digitEnd -= precchop;
          }
          if (newScaleInt < Integer.MAX_VALUE - nondec) {
            newScaleInt += nondec;
          } else {
            newScale = (newScale == null) ? (EInteger.FromInt32(newScaleInt)) : newScale;
            newScale = newScale.Add(nondec);
          }
        }
      }
      EDecimal ret = null;
      EInteger exp = null;
      int expInt = 0;
      int expoffset = 1;
      int expDigitStart = -1;
      int expPrec = 0;
      haveNonzeroDigit = false;
      if (haveExponent) {
        haveDigits = false;
        if (i == endStr) {
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException();
          }
        }
        if (chars[i] == '+' || chars[i] == '-') {
          if (chars[i] == '-') {
            expoffset = -1;
          }
          ++i;
        }
        expDigitStart = i;
        for (; i < endStr; ++i) {
          char ch = chars[i];
          if (ch >= '0' && ch <= '9') {
            haveDigits = true;
            int thisdigit = (int)(ch - '0');
            haveNonzeroDigit |= thisdigit != 0;
            if (haveNonzeroDigit) {
              ++expPrec;
            }
            if (expInt <= MaxSafeInt) {
              expInt *= 10;
              expInt += thisdigit;
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
      }
      if (i != endStr) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException();
        }
      }
      // Calculate newScale if exponent is "small"
      if (haveExponent && expInt <= MaxSafeInt) {
        if (expoffset >= 0 && newScaleInt == 0 && newScale == null) {
          newScaleInt = expInt;
        } else if (newScale == null) {
          long tmplong = newScaleInt;
          if (expoffset < 0) {
            tmplong -= expInt;
          } else if (expInt != 0) {
            tmplong += expInt;
          }
          if (tmplong >= Integer.MAX_VALUE && tmplong <= Integer.MIN_VALUE) {
            newScaleInt = (int)tmplong;
          } else {
            newScale = EInteger.FromInt64(tmplong);
          }
        } else {
          if (expoffset < 0) {
            newScale = newScale.Subtract(expInt);
          } else if (expInt != 0) {
            newScale = newScale.Add(expInt);
          }
        }
      }
      if (ctx != null && (mantInt > MaxSafeInt || (haveExponent &&
            expInt > MaxSafeInt)) && ctx.getHasExponentRange()) {
        EInteger ns;
        if (expInt <= MaxSafeInt && ctx != null) {
          ns = (newScale == null) ? (EInteger.FromInt32(newScaleInt)) : newScale;
        } else {
          EInteger trialExponent = EInteger.FromInt32(MaxSafeInt);
          if (expPrec > 25) {
            // Exponent has many significant digits; use a bigger trial exponent
            trialExponent = EInteger.FromInt64(Long.MAX_VALUE);
          }
          // Trial exponent; in case of overflow or
          // underflow, the real exponent will also overflow or underflow
          if (expoffset >= 0 && newScaleInt == 0 && newScale == null) {
            ns = trialExponent;
          } else {
            ns = (newScale == null) ? (EInteger.FromInt32(newScaleInt)) : newScale;
            ns = (expoffset < 0) ? ns.Subtract(trialExponent) :
              ns.Add(trialExponent);
          }
        }
        int expwithin = EDecimal.CheckOverflowUnderflow(
            ctx,
            decimalPrec,
            ns);
        if (mantInt == 0 && (expwithin == 1 || expwithin == 2 ||
            expwithin == 3)) {
          // Significand is zero
          ret = new EDecimal(
            FastIntegerFixed.FromInt32(0),
            FastIntegerFixed.FromBig(ns),
            (byte)(negative ? BigNumberFlags.FlagNegative : 0));
          return EDecimal.GetMathValue(ctx).RoundAfterConversion(ret, ctx);
        } else if (expwithin == 1) {
          // Exponent indicates overflow
          return EDecimal.GetMathValue(ctx).SignalOverflow(ctx, negative);
        } else if (expwithin == 2 || (expwithin == 3 && mantInt < MaxSafeInt)) {
          // Exponent indicates underflow to zero
          ret = new EDecimal(
            FastIntegerFixed.FromInt32(expwithin == 3 ? mantInt : 1),
            FastIntegerFixed.FromBig(ns),
            (byte)(negative ? BigNumberFlags.FlagNegative : 0));
          return EDecimal.GetMathValue(ctx).RoundAfterConversion(ret, ctx);
        } else if (expwithin == 3 && (ctx == null || ctx.getTraps() == 0)) {
          // Exponent indicates underflow to zero, adjust exponent
          ret = new EDecimal(
            FastIntegerFixed.FromInt32(1),
            FastIntegerFixed.FromBig(ns),
            (byte)(negative ? BigNumberFlags.FlagNegative : 0));
          ret = EDecimal.GetMathValue(ctx).RoundAfterConversion(ret, ctx);
          ns = ret.getExponent().Subtract(decimalPrec - 1);
          ret = EDecimal.ChangeExponent(ret, ns);
          return ret;
        }
      }
      // System.out.println("digitRange="+digitStart+"-"+digitEnd+
      // "decdigitRange="+decimalDigitStart+"-"+decimalDigitEnd);
      int de = digitEnd;
      int dde = decimalDigitEnd;
      if (!haveExponent && haveDecimalPoint && newScale == null) {
        // No more than 18 digits
        long lv = 0L;
        int expo = newScaleInt; // -(dde - decimalDigitStart);
        int digitCount = 0;
        if (mantInt <= MaxSafeInt) {
          lv = mantInt;
        } else {
          int vi = 0;
          for (vi = digitStart; vi < de; ++vi) {
            char chvi = chars[vi];

            if (digitCount < 0 || digitCount >= 18) {
              digitCount = -1;
              break;
            } else if (digitCount > 0 || chvi != '0') {
              ++digitCount;
            }
            lv = ((lv * 10) + (int)(chvi - '0'));
          }
          for (vi = decimalDigitStart; vi < dde; ++vi) {
            char chvi = chars[vi];

            if (digitCount < 0 || digitCount >= 18) {
              digitCount = -1;
              break;
            } else if (digitCount > 0 || chvi != '0') {
              ++digitCount;
            }
            lv = ((lv * 10) + (int)(chvi - '0'));
          }
        }
        if (negative) {
          lv = -lv;
        }
        if (digitCount >= 0 && (!negative || lv != 0)) {
          // System.out.println("lv="+lv+" expo="+expo);
          ret = EDecimal.Create(lv, (long)expo);
          if (ctx != null) {
            ret = EDecimal.GetMathValue(ctx).RoundAfterConversion(ret, ctx);
          }
          return ret;
        }
      }
      // Parse significand if it's "big"
      if (mantInt > MaxSafeInt) {
        if (haveDecimalPoint) {
          if (digitEnd - digitStart == 1 && chars[digitStart] == '0') {
            mant = EInteger.FromSubstring(
                chars,
                decimalDigitStart,
                decimalDigitEnd);
          } else {
            char[] cdecstr = Extras.CharsConcat(
                chars,
                digitStart,
                digitEnd - digitStart,
                chars,
                decimalDigitStart,
                decimalDigitEnd - decimalDigitStart);
            mant = EInteger.FromString(cdecstr);
          }
        } else {
          mant = EInteger.FromSubstring(chars, digitStart, digitEnd);
        }
      }
      if (haveExponent && expInt > MaxSafeInt) {
        // Parse exponent if it's "big"
        exp = EInteger.FromSubstring(chars, expDigitStart, endStr);
        newScale = (newScale == null) ? (EInteger.FromInt32(newScaleInt)) : newScale;
        newScale = (expoffset < 0) ? newScale.Subtract(exp) :
          newScale.Add(exp);
      }
      FastIntegerFixed fastIntScale;
      FastIntegerFixed fastIntMant;
      fastIntScale = (newScale == null) ? FastIntegerFixed.FromInt32(
          newScaleInt) : FastIntegerFixed.FromBig(newScale);
      // System.out.println("fim="+ Chop(mant) + ", exp=" + fastIntScale);
      if (mant == null) {
        fastIntMant = FastIntegerFixed.FromInt32(mantInt);
      } else if (mant.CanFitInInt32()) {
        mantInt = mant.ToInt32Checked();
        fastIntMant = FastIntegerFixed.FromInt32(mantInt);
      } else {
        fastIntMant = FastIntegerFixed.FromBig(mant);
      }
      ret = new EDecimal(
        fastIntMant,
        fastIntScale,
        (byte)(negative ? BigNumberFlags.FlagNegative : 0));
      if (ctx != null) {
        // System.out.println("rounding");
        ret = EDecimal.GetMathValue(ctx).RoundAfterConversion(ret, ctx);
      }
      return ret;
    }
  }
