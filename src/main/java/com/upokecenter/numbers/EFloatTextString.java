package com.upokecenter.numbers;

  final class EFloatTextString {
private EFloatTextString() {
}
    static EFloat FromString(
      String chars,
      int offset,
      int length,
      EContext ctx,
      boolean throwException) {
      if (chars == null) {
        if (!throwException) {
          return null;
        } else {
          throw new NullPointerException("chars");
        }
      }
      if (offset < 0) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException("offset(" + offset + ") is not" +
            "\u0020greater" + "\u0020or equal to 0");
        }
      }
      if (offset > chars.length()) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException("offset(" + offset + ") is not" +
            "\u0020less" + "\u0020or" + "\u0020equal to " + chars.length());
        }
      }
      if (length < 0) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException("length(" + length + ") is not" +
            "\u0020greater or" + "\u0020equal to 0");
        }
      }
      if (length > chars.length()) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException("length(" + length + ") is not" +
            "\u0020less" + "\u0020or" + "\u0020equal to " + chars.length());
        }
      }
      if (chars.length() - offset < length) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException("str's length minus " + offset + "(" +
            (chars.length() - offset) + ") is not greater or equal to " + length);
        }
      }
      EContext b64 = EContext.Binary64;
      if (ctx != null && ctx.getHasMaxPrecision() && ctx.getHasExponentRange() &&
        !ctx.isSimplified() && ctx.getEMax().compareTo(b64.getEMax()) <= 0 &&
        ctx.getEMin().compareTo(b64.getEMin()) >= 0 &&
        ctx.getPrecision().compareTo(b64.getPrecision()) <= 0) {
        int tmpoffset = offset;
        int endpos = offset + length;
        if (length == 0) {
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException();
          }
        }
        if (chars.charAt(tmpoffset) == '-' || chars.charAt(tmpoffset) == '+') {
          ++tmpoffset;
        }
        if (tmpoffset < endpos && ((chars.charAt(tmpoffset) >= '0' &&
          chars.charAt(tmpoffset) <= '9') || chars.charAt(tmpoffset) == '.')) {
          EFloat ef = DoubleEFloatFromString(
              chars,
              offset,
              length,
              ctx,
              throwException);
          if (ef != null) {
            return ef;
          }
        }
      }
      return EDecimal.FromString(
          chars,
          offset,
          length,
          EContext.Unlimited.WithSimplified(ctx != null && ctx.isSimplified()))
        .ToEFloat(ctx);
    }

    static EFloat DoubleEFloatFromString(
      String chars,
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
      if (length == 0) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException();
        }
      }
      int endStr = tmpoffset + length;
      boolean negative = false;
      boolean haveDecimalPoint = false;
      boolean haveDigits = false;
      boolean haveExponent = false;
      int newScaleInt = 0;
      int digitStart = 0;
      int i = tmpoffset;
      long mantissaLong = 0L;
      // Ordinary number
      if (chars.charAt(i) == '+' || chars.charAt(i) == '-') {
        if (chars.charAt(i) == '-') {
          negative = true;
        }
        ++i;
      }
      digitStart = i;
      int digitEnd = i;
      int decimalDigitStart = i;
      boolean haveNonzeroDigit = false;
      int decimalPrec = 0;
      int decimalDigitEnd = i;
      boolean nonzeroBeyondMax = false;
      int lastdigit = -1;
      // 768 is maximum precision of a decimal
      // half-ULP in double format
      int maxDecimalPrec = 768;
      if (length > 21) {
        int eminInt = ctx.getEMin().ToInt32Checked();
        int emaxInt = ctx.getEMax().ToInt32Checked();
        int precInt = ctx.getPrecision().ToInt32Checked();
        if (eminInt >= -14 && emaxInt <= 15) {
          maxDecimalPrec = (precInt <= 11) ? 21 : 63;
        } else if (eminInt >= -126 && emaxInt <= 127) {
          maxDecimalPrec = (precInt <= 24) ? 113 : 142;
        }
      }
      for (; i < endStr; ++i) {
        char ch = chars.charAt(i);
        if (ch >= '0' && ch <= '9') {
          int thisdigit = (int)(ch - '0');
          haveDigits = true;
          haveNonzeroDigit |= thisdigit != 0;
          if (decimalPrec > maxDecimalPrec) {
            if (thisdigit != 0) {
              nonzeroBeyondMax = true;
            }
            if (!haveDecimalPoint) {
              // NOTE: Absolute value will not be more than
              // the String portion's length, so will fit comfortably
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
            // the portion's length, so will fit comfortably
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
      EFloat ef1, ef2;
      if (haveExponent) {
        haveDigits = false;
        if (i == endStr) {
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException();
          }
        }
        char ch = chars.charAt(i);
        if (ch == '+' || ch == '-') {
          if (ch == '-') {
            expoffset = -1;
          }
          ++i;
        }
        expDigitStart = i;
        for (; i < endStr; ++i) {
          ch = chars.charAt(i);
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
          // length without remaining beyond Int32 range
          if (expoffset < 0) {
            return EFloat.SignalUnderflow(ctx, negative, zeroMantissa);
          } else {
            return EFloat.SignalOverflow(ctx, negative, zeroMantissa);
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
        mantissaLong != Long.MAX_VALUE && (ctx == null ||
          !ctx.getHasFlagsOrTraps())) {
        if (mantissaLong == 0) {
          EFloat ef = EFloat.Create(
              EInteger.FromInt32(0),
              EInteger.FromInt32(expInt));
          if (negative) {
            ef = ef.Negate();
          }
          return ef.RoundToPrecision(ctx);
        }
        long finalexp = (long)expInt + (long)newScaleInt;
        long ml = mantissaLong;
        if (finalexp >= -22 && finalexp <= 44) {
          int iexp = (int)finalexp;
          while (ml <= 900719925474099L && iexp > 22) {
            ml *= 10;
            --iexp;
          }
          int iabsexp = Math.abs(iexp);
          if (ml < 9007199254740992L && iabsexp == 0) {
            return EFloat.FromInt64(negative ?
                -mantissaLong : mantissaLong).RoundToPrecision(ctx);
          } else if (ml < 9007199254740992L && iabsexp <= 22) {
            EFloat efn =
              EFloat.FromEInteger(NumberUtility.FindPowerOfTen(iabsexp));
            if (negative) {
              ml = -ml;
            }
            EFloat efml = EFloat.FromInt64(ml);
            if (iexp < 0) {
              return efml.Divide(efn, ctx);
            } else {
              return efml.Multiply(efn, ctx);
            }
          }
        }
        long adjexpUpperBound = finalexp + (decimalPrec - 1);
        long adjexpLowerBound = finalexp;
        if (adjexpUpperBound < -326) {
          return EFloat.SignalUnderflow(ctx, negative, zeroMantissa);
        } else if (adjexpLowerBound > 309) {
          return EFloat.SignalOverflow(ctx, negative, zeroMantissa);
        }
        if (negative) {
          mantissaLong = -mantissaLong;
        }
        long absfinalexp = Math.abs(finalexp);
        ef1 = EFloat.Create(mantissaLong, (int)0);
        ef2 = EFloat.FromEInteger(NumberUtility.FindPowerOfTen(absfinalexp));
        if (finalexp < 0) {
          EFloat efret = ef1.Divide(ef2, ctx);
          /* System.out.println("div " + ef1 + "/" + ef2 + " -> " + (efret));
          */ return efret;
        } else {
          return ef1.Multiply(ef2, ctx);
        }
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
      EInteger adjExpUpperBound = exp.Add(decimalPrec).Subtract(1);
      EInteger adjExpLowerBound = exp;
      // System.out.println("exp=" + adjExpLowerBound + "~" + (adjExpUpperBound));
      if (adjExpUpperBound.compareTo(-326) < 0) {
        return EFloat.SignalUnderflow(ctx, negative, zeroMantissa);
      } else if (adjExpLowerBound.compareTo(309) > 0) {
        return EFloat.SignalOverflow(ctx, negative, zeroMantissa);
      }
      if (zeroMantissa) {
        EFloat ef = EFloat.Create(
            EInteger.FromInt32(0),
            exp);
        if (negative) {
          ef = ef.Negate();
        }
        return ef.RoundToPrecision(ctx);
      } else if (decimalDigitStart != decimalDigitEnd) {
        if (digitEnd - digitStart == 1 && chars.charAt(digitStart) == '0') {
          mant = EInteger.FromSubstring(
              chars,
              decimalDigitStart,
              decimalDigitEnd);
        } else {
          String ctmpstr = Extras.CharsConcat(
              chars,
              digitStart,
              digitEnd - digitStart,
              chars,
              decimalDigitStart,
              decimalDigitEnd - decimalDigitStart);
          mant = EInteger.FromString(ctmpstr);
        }
      } else {
        mant = EInteger.FromSubstring(chars, digitStart, digitEnd);
      }
      if (nonzeroBeyondMax) {
        mant = mant.Multiply(10).Add(1);
      }
      if (negative) {
        mant = mant.Negate();
      }
      return EDecimal.Create(mant, exp).ToEFloat(ctx);
    }
  }
