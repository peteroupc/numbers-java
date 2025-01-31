package com.upokecenter.numbers;

  final class EIntegerByteArrayString {
private EIntegerByteArrayString() {
}
    private static final int ShortMask = 0xffff;

    public static EInteger FromRadixSubstringImpl(
      byte[] cs,
      int radix,
      int index,
      int endIndex,
      boolean throwException) {
      if (radix < 2) {
        if (!throwException) {
          return null;
        } else {
          throw new IllegalArgumentException("radix(" + radix + ") is less than 2");
        }
      }
      if (radix > 36) {
        if (!throwException) {
          return null;
        } else {
          throw new IllegalArgumentException("radix(" + radix + ") is more than 36");
        }
      }
      if (index < 0) {
        if (!throwException) {
          return null;
        } else {
          throw new IllegalArgumentException("index(" + index + ") is less than " +
            "0");
        }
      }
      if (index > cs.length) {
        if (!throwException) {
          return null;
        } else {
          throw new IllegalArgumentException("index(" + index + ") is more than " +
            cs.length);
        }
      }
      if (endIndex < 0) {
        if (!throwException) {
          return null;
        } else {
          throw new IllegalArgumentException("endIndex(" + endIndex + ") is less" +
            "\u0020 than 0");
        }
      }
      if (endIndex > cs.length) {
        if (!throwException) {
          return null;
        } else {
          throw new IllegalArgumentException("endIndex(" + endIndex + ") is more" +
            "\u0020 than " + cs.length);
        }
      }
      if (endIndex < index) {
        if (!throwException) {
          return null;
        } else {
          throw new IllegalArgumentException("endIndex(" + endIndex + ") is less" +
            "\u0020 than " + index);
        }
      }
      if (index == endIndex) {
        if (!throwException) {
          return null;
        } else {
          throw new NumberFormatException("No digits");
        }
      }
      boolean negative = false;
      if (cs[index] == '-') {
        ++index;
        if (index == endIndex) {
          if (!throwException) {
            return null;
          } else {
            throw new NumberFormatException("No digits");
          }
        }
        negative = true;
      }
      // Skip leading zeros
      for (; index < endIndex; ++index) {
        byte c = cs[index];
        if (c != 0x30) {
          break;
        }
      }
      int effectiveLength = endIndex - index;
      if (effectiveLength == 0) {
        return EInteger.FromInt32(0);
      }
      int[] c2d = EInteger.CharToDigit;
      short[] bigint;
      if (radix == 16) {
        // Special case for hexadecimal radix
        int leftover = effectiveLength & 3;
        int wordCount = effectiveLength >> 2;
        if (leftover != 0) {
          ++wordCount;
        }
        bigint = new short[wordCount];
        int currentDigit = wordCount - 1;
        // Get most significant digits if effective
        // length is not divisible by 4
        if (leftover != 0) {
          int extraWord = 0;
          for (int i = 0; i < leftover; ++i) {
            extraWord <<= 4;
            byte c = cs[index + i];
            int digit = (c >= 0x80) ? 36 : c2d[(int)c];
            if (digit >= 16) {
              if (!throwException) {
                return null;
              } else {
                throw new NumberFormatException("Illegal character found");
              }
            }
            extraWord |= digit;
          }
          bigint[currentDigit] = ((short)extraWord);
          --currentDigit;
          index += leftover;
        }

        while (index < endIndex) {
          byte c = cs[index + 3];
          int digit = (c >= 0x80) ? 36 : c2d[(int)c];
          if (digit >= 16) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException("Illegal character found");
            }
          }
          int word = digit;
          c = cs[index + 2];
          digit = (c >= 0x80) ? 36 : c2d[(int)c];
          if (digit >= 16) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException("Illegal character found");
            }
          }

          word |= digit << 4;
          c = cs[index + 1];
          digit = (c >= 0x80) ? 36 : c2d[(int)c];
          if (digit >= 16) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException("Illegal character found");
            }
          }

          word |= digit << 8;
          c = cs[index];
          digit = (c >= 0x80) ? 36 : c2d[(int)c];
          if (digit >= 16) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException("Illegal character found");
            }
          }
          word |= digit << 12;
          index += 4;
          bigint[currentDigit] = ((short)word);
          --currentDigit;
        }
        int count = EInteger.CountWords(bigint);
        return (count == 0) ? EInteger.FromInt32(0) : new EInteger(
          count,
          bigint,
          negative);
      } else if (radix == 2) {
        // Special case for binary radix
        int leftover = effectiveLength & 15;
        int wordCount = effectiveLength >> 4;
        if (leftover != 0) {
          ++wordCount;
        }
        bigint = new short[wordCount];
        int currentDigit = wordCount - 1;
        // Get most significant digits if effective
        // length is not divisible by 4
        if (leftover != 0) {
          int extraWord = 0;
          for (int i = 0; i < leftover; ++i) {
            extraWord <<= 1;
            byte c = cs[index + i];
            int digit = (c == '0') ? 0 : ((c == '1') ? 1 : 2);
            if (digit >= 2) {
              if (!throwException) {
                return null;
              } else {
                throw new NumberFormatException("Illegal character found");
              }
            }
            extraWord |= digit;
          }
          bigint[currentDigit] = ((short)extraWord);
          --currentDigit;
          index += leftover;
        }
        while (index < endIndex) {
          int word = 0;
          int idx = index + 15;
          for (int i = 0; i < 16; ++i) {
            byte c = cs[idx];
            int digit = (c == '0') ? 0 : ((c == '1') ? 1 : 2);
            if (digit >= 2) {
              if (!throwException) {
                return null;
              } else {
                throw new NumberFormatException("Illegal character found");
              }
            }
            --idx;
            word |= digit << i;
          }
          index += 16;
          bigint[currentDigit] = ((short)word);
          --currentDigit;
        }
        int count = EInteger.CountWords(bigint);
        return (count == 0) ? EInteger.FromInt32(0) : new EInteger(
          count,
          bigint,
          negative);
      } else {
        return FromRadixSubstringGeneral(
            cs,
            radix,
            index,
            endIndex,
            negative,
            throwException);
      }
    }

    private static EInteger FromRadixSubstringGeneral(
      byte[] cs,
      int radix,
      int index,
      int endIndex,
      boolean negative,
      boolean throwException) {
      if (endIndex - index > 72) {
        int midIndex = index + ((endIndex - index) / 2);
        EInteger eia = FromRadixSubstringGeneral(
            cs,
            radix,
            index,
            midIndex,
            false,
            throwException);
        // System.out.println("eia="+eia);
        EInteger eib = FromRadixSubstringGeneral(
            cs,
            radix,
            midIndex,
            endIndex,
            false,
            throwException);
        // System.out.println("eib="+eib);
        EInteger mult = null;
        int intpow = endIndex - midIndex;
        if (radix == 10) {
          eia = NumberUtility.MultiplyByPowerOfFive(eia,
              intpow).ShiftLeft(intpow);
        } else if (radix == 5) {
          eia = NumberUtility.MultiplyByPowerOfFive(eia, intpow);
        } else {
          mult = EInteger.FromInt32(radix).Pow(endIndex - midIndex);
          eia = eia.Multiply(mult);
        }
        eia = eia.Add(eib);
        // System.out.println("index={0} {1} {2} [pow={3}] [pow={4} ms, muladd={5}
        // ms]",
        // index, midIndex, endIndex, endIndex-midIndex, swPow.getElapsedMilliseconds(),
        // swMulAdd.getElapsedMilliseconds());
        if (negative) {
          eia = eia.Negate();
        }
        // System.out.println("eia now="+eia);
        return eia;
      } else {
        return FromRadixSubstringInner(
            cs,
            radix,
            index,
            endIndex,
            negative,
            throwException);
      }
    }

    private static EInteger FromRadixSubstringInner(
      byte[] cs,
      int radix,
      int index,
      int endIndex,
      boolean negative,
      boolean throwException) {
      if (radix <= 10) {
        long rv = 0;
        int digitCount = 0;
        if (radix == 10) {
          for (int i = index; i < endIndex; ++i) {
            byte c = cs[i];
            int digit = (int)c - 0x30;
            if (digit >= radix || digit < 0) {
              if (!throwException) {
                return null;
              } else {
                throw new NumberFormatException("Illegal character found");
              }
            }
            if (digitCount < 0 || digitCount >= 18) {
              digitCount = -1;
              break;
            } else if (digitCount > 0 || digit != 0) {
              ++digitCount;
            }
            rv = (rv * 10) + digit;
          }
          // System.out.println("short="+(negative ? -rv : rv));
          if (digitCount >= 0) {
            return EInteger.FromInt64(negative ? -rv : rv);
          }
        } else {
          for (int i = index; i < endIndex; ++i) {
            byte c = cs[i];
            int digit = (c >= 0x80) ? 36 : ((int)c - 0x30);
            if (digit >= radix || digit < 0) {
              if (!throwException) {
                return null;
              } else {
                throw new NumberFormatException("Illegal character found");
              }
            }
            if (digitCount < 0 || digitCount >= 18) {
              digitCount = -1;
              break;
            } else if (digitCount > 0 || digit != 0) {
              ++digitCount;
            }
            rv = (rv * radix) + digit;
          }
          if (digitCount >= 0) {
            return EInteger.FromInt64(negative ? -rv : rv);
          }
        }
      }
      int[] c2d = EInteger.CharToDigit;
      int[] d2w = EInteger.DigitsInWord;
      long lsize = ((long)(endIndex - index) * 100 / d2w[radix]) + 1;
      lsize = Math.min(lsize, Integer.MAX_VALUE);
      lsize = Math.max(lsize, 5);
      short[] bigint = new short[(int)lsize];
      if (radix == 10) {
        long rv = 0;
        int ei = endIndex - index <= 18 ? endIndex : index + 18;
        for (int i = index; i < ei; ++i) {
          byte c = cs[i];
          int digit = (int)c - 0x30;
          if (digit >= radix || digit < 0) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException("Illegal character found");
            }
          }
          rv = (rv * 10) + digit;
        }
        bigint[0] = ((short)(rv & ShortMask));
        bigint[1] = ((short)((rv >> 16) & ShortMask));
        bigint[2] = ((short)((rv >> 32) & ShortMask));
        bigint[3] = ((short)((rv >> 48) & ShortMask));
        int bn = Math.min(bigint.length, 5);
        for (int i = ei; i < endIndex; ++i) {
          short carry = 0;
          int digit = 0;
          int overf = 0;
          if (i < endIndex - 3) {
            overf = 55536; // 2**16 minus 10**4
            int d1 = (int)cs[i] - 0x30;
            int d2 = (int)cs[i + 1] - 0x30;
            int d3 = (int)cs[i + 2] - 0x30;
            int d4 = (int)cs[i + 3] - 0x30;
            i += 3;
            if (d1 >= 10 || d1 < 0 || d2 >= 10 || d2 < 0 || d3 >= 10 ||
              d3 < 0 || d4 >= 10 || d4 < 0) {
              if (!throwException) {
                return null;
              } else {
                throw new NumberFormatException("Illegal character found");
              }
            }
            digit = (d1 * 1000) + (d2 * 100) + (d3 * 10) + d4;
            // Multiply by 10**4
            for (int j = 0; j < bn; ++j) {
              int p;
              p = ((((int)bigint[j]) & ShortMask) *
                10000);
              int p2 = ((int)carry) & ShortMask;
              p = (p + p2);
              bigint[j] = ((short)p);
              carry = ((short)(p >> 16));
            }
          } else {
            overf = 65526; // 2**16 minus radix 10
            byte c = cs[i];
            digit = (int)c - 0x30;
            if (digit >= 10 || digit < 0) {
              if (!throwException) {
                return null;
              } else {
                throw new NumberFormatException("Illegal character found");
              }
            }
            // Multiply by 10
            for (int j = 0; j < bn; ++j) {
              int p;
              p = ((((int)bigint[j]) & ShortMask) * 10);
              int p2 = ((int)carry) & ShortMask;
              p = (p + p2);
              bigint[j] = ((short)p);
              carry = ((short)(p >> 16));
            }
          }
          if (carry != 0) {
            bigint = EInteger.GrowForCarry(bigint, carry);
          }
          // Add the parsed digit
          if (digit != 0) {
            int d = bigint[0] & ShortMask;
            if (d <= overf) {
              bigint[0] = ((short)(d + digit));
            } else if (EInteger.IncrementWords(
              bigint,
              0,
              bigint.length,
              (short)digit) != 0) {
              bigint = EInteger.GrowForCarry(bigint, (short)1);
            }
          }
          bn = Math.min(bigint.length, bn + 1);
        }
      } else {
        boolean haveSmallInt = true;
        int[] msi = EInteger.MaxSafeInts;
        int maxSafeInt = msi[radix - 2];
        int maxShortPlusOneMinusRadix = 65536 - radix;
        int smallInt = 0;
        for (int i = index; i < endIndex; ++i) {
          byte c = cs[i];
          int digit = (c >= 0x80) ? 36 : c2d[(int)c];
          if (digit >= radix) {
            if (!throwException) {
              return null;
            } else {
              throw new NumberFormatException("Illegal character found");
            }
          }
          if (haveSmallInt && smallInt < maxSafeInt) {
            smallInt = (smallInt * radix) + digit;
          } else {
            if (haveSmallInt) {
              bigint[0] = ((short)(smallInt &
                ShortMask));
              bigint[1] = ((short)((smallInt >> 16) &
                ShortMask));
              haveSmallInt = false;
            }
            // Multiply by the radix
            short carry = 0;
            int n = bigint.length;
            for (int j = 0; j < n; ++j) {
              int p;
              p = ((((int)bigint[j]) & ShortMask) *
                radix);
              int p2 = ((int)carry) & ShortMask;
              p = (p + p2);
              bigint[j] = ((short)p);
              carry = ((short)(p >> 16));
            }
            if (carry != 0) {
              bigint = EInteger.GrowForCarry(bigint, carry);
            }
            // Add the parsed digit
            if (digit != 0) {
              int d = bigint[0] & ShortMask;
              if (d <= maxShortPlusOneMinusRadix) {
                bigint[0] = ((short)(d + digit));
              } else if (EInteger.IncrementWords(
                bigint,
                0,
                bigint.length,
                (short)digit) != 0) {
                bigint = EInteger.GrowForCarry(bigint, (short)1);
              }
            }
          }
        }
        if (haveSmallInt) {
          bigint[0] = ((short)(smallInt & ShortMask));
          bigint[1] = ((short)((smallInt >> 16) &
            ShortMask));
        }
      }
      int count = EInteger.CountWords(bigint);
      return (count == 0) ? EInteger.FromInt32(0) : new EInteger(
        count,
        bigint,
        negative);
    }
  }
