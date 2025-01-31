package com.upokecenter.numbers;
/*
Written in 2013-2019 by Peter O.

Parts of the code were adapted by Peter O. from
public-domain code by Wei Dai.

Parts of the GCD function adapted by Peter O.
from public domain GCD code by Christian
Stigen Larsen (http://csl.sublevel3.org).

Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under the Unlicense: https://unlicense.org/

 */

// TODO: In next major version, perhaps change GetSigned/UnsignedBitLength
// to return MaxValue on overflow
// TODO: In next major version, perhaps change GetLowBit/GetDigitCount
// to return MaxValue on overflow
// TODO: Perhaps add overload to FromBytes to take a sign and magnitude

  /**
   * <p>Represents an arbitrary-precision integer. (The "E" stands for
   * "extended", and has this prefix to group it with the other classes common to
   * this library, particularly EDecimal, EFloat, and ERational.)
   * </p><p>Instances of this class are immutable, so they are inherently safe
   * for use by multiple threads. Multiple instances of this object with the same
   * value are interchangeable, but they should be compared using the "Equals"
   * method rather than the "==" operator.</p> <p><b>Security note</b></p> <p>It
   * is not recommended to implement security-sensitive algorithms using the
   * methods in this class, for several reasons:</p> <ul> <li> {@code EInteger}
   * objects are immutable, so they can't be modified, and the memory they occupy
   * is not guaranteed to be cleared in a timely fashion due to garbage
   * collection. This is relevant for applications that use many-bit-long numbers
   * as secret parameters.</li><li>The methods in this class (especially those
   * that involve arithmetic) are not guaranteed to be "constant-time"
   * (nondata-dependent) for all relevant inputs. Certain attacks that involve
   * encrypted communications have exploited the timing and other aspects of such
   * communications to derive keying material or cleartext indirectly.</li></ul>
   * <p>Applications should instead use dedicated security libraries to handle
   * big numbers in security-sensitive algorithms.</p>
   */

  public final class EInteger implements Comparable<EInteger> {
    private static final String Digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int Toom3Threshold = 100;
    private static final int Toom4Threshold = 400;
    private static final int MultRecursionThreshold = 10;
    private static final int RecursiveDivisionLimit = (Toom3Threshold * 2) + 1;

    private static final int CacheFirst = -24;
    private static final int CacheLast = 128;

    private static final int ShortMask = 0xffff;

    static final int[] CharToDigit = {
      36, 36, 36, 36, 36, 36,
      36,
      36,
      36, 36, 36, 36, 36, 36, 36, 36,
      36, 36, 36, 36, 36, 36, 36, 36,
      36, 36, 36, 36, 36, 36, 36, 36,
      36, 36, 36, 36, 36, 36, 36, 36,
      36, 36, 36, 36, 36, 36, 36, 36,
      0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 36, 36, 36, 36, 36, 36,
      36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
      25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 36, 36, 36, 36,
      36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
      25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 36, 36, 36, 36,
    };

    static final int[] MaxSafeInts = {
      1073741823, 715827881,
      536870911, 429496728, 357913940, 306783377, 268435455, 238609293,
      214748363, 195225785, 178956969, 165191048, 153391688, 143165575,
      134217727, 126322566, 119304646, 113025454, 107374181, 102261125,
      97612892, 93368853, 89478484, 85899344, 82595523, 79536430, 76695843,
      74051159, 71582787, 69273665, 67108863, 65075261, 63161282, 61356674,
      59652322,
    };

    private static final EInteger ValueOne = new EInteger(
      1, new short[] { 1 }, false);

    private static final EInteger ValueTen = new EInteger(
      1, new short[] { 10 }, false);

    private static final EInteger ValueZero = new EInteger(
      0, new short[] { 0 }, false);

    private final boolean negative;
    private final int wordCount;
    private final short[] words;

    private static final EInteger[] Cache = EIntegerCache(CacheFirst,
        CacheLast);

    private static EInteger[] EIntegerCache(int first, int last) {
      int i = 0;
      EInteger[] cache = new EInteger[(last - first) + 1];
      for (i = first; i <= last; ++i) {
        if (i == 0) {
          cache[i - first] = ValueZero;
        } else if (i == 1) {
          cache[i - first] = ValueOne;
        } else if (i == 10) {
          cache[i - first] = ValueTen;
        } else {
          int iabs = Math.abs(i);
          short[] words = new short[] {
            ((short)iabs),
          };
          cache[i - first] = new EInteger(1, words, i < 0);
        }
      }
      return cache;
    }

    EInteger(int wordCount, short[] reg, boolean negative) {
      this.wordCount = wordCount;
      this.words = reg;
      this.negative = negative;
    }

    /**
     * Gets the number 1 as an arbitrary-precision integer.
     * @return The number 1 as an arbitrary-precision integer.
     */
    public static EInteger getOne() {
        return ValueOne;
      }

    /**
     * Gets the number 10 as an arbitrary-precision integer.
     * @return The number 10 as an arbitrary-precision integer.
     */
    public static EInteger getTen() {
        return ValueTen;
      }

    /**
     * Gets the number zero as an arbitrary-precision integer.
     * @return The number zero as an arbitrary-precision integer.
     */
    public static EInteger getZero() {
        return ValueZero;
      }

    /**
     * Gets a value indicating whether this value is even.
     * @return {@code true} if this value is even; otherwise, {@code false}.
     */
    public final boolean isEven() {
        return !this.GetUnsignedBit(0);
      }

    /**
     * Gets a value indicating whether this object's value is a power of two, and
     * greater than 0.
     * @return {@code true} if this object's value is a power of two, and greater
     * than 0; otherwise, {@code false}.
     */
    public final boolean isPowerOfTwo() {
        int wc = this.wordCount;
        if (this.negative || wc == 0 ||
          (wc > 1 && this.words[0] != 0)) {
          return false;
        }
        for (int i = 0; i < wc - 1; ++i) {
          if (this.words[i] != 0) {
            return false;
          }
        }
        int lastw = ((int)this.words[wc - 1]) & 0xffff;
        if (lastw == 0) {
          throw new IllegalStateException();
        }
        while ((lastw & 1) == 0) {
          lastw >>= 1;
        }
        return lastw == 1;
      }

    /**
     * Gets a value indicating whether this value is 0.
     * @return {@code true} if this value is 0; otherwise, {@code false}.
     */
    public final boolean isZero() {
        return this.wordCount == 0;
      }

    /**
     * Gets the sign of this object's value.
     * @return The sign of this object's value.
     */
    public final int signum() {
        return (this.wordCount == 0) ? 0 : (this.negative ? -1 : 1);
      }

    static EInteger FromInts(int[] intWords, int count) {
      short[] words = new short[count << 1];
      int j = 0;
      for (int i = 0; i < count; ++i, j += 2) {
        int w = intWords[i];
        words[j] = ((short)w);
        words[j + 1] = ((short)(w >> 16));
      }
      int newwordCount = words.length;
      while (newwordCount != 0 && words[newwordCount - 1] == 0) {
        --newwordCount;
      }
      return (newwordCount == 0) ? EInteger.FromInt32(0) :
        new EInteger(newwordCount, words, false);
    }

    /**
     * Initializes an arbitrary-precision integer from an array of bytes.
     * @param bytes A byte array consisting of the two's-complement form (see
     * {@link com.upokecenter.numbers.EDecimal "Forms of numbers"}) of the
     * arbitrary-precision integer to create. The byte array is encoded using the
     * rules given in the FromBytes(bytes, offset, length, littleEndian) overload.
     * @param littleEndian If true, the byte order is little-endian, or
     * least-significant-byte first. If false, the byte order is big-endian, or
     * most-significant-byte first.
     * @return An arbitrary-precision integer. Returns 0 if the byte array's length
     * is 0.
     * @throws NullPointerException The parameter {@code bytes} is null.
     */
    public static EInteger FromBytes(byte[] bytes, boolean littleEndian) {
      if (bytes == null) {
        throw new NullPointerException("bytes");
      }
      return FromBytes(bytes, 0, bytes.length, littleEndian);
    }

    /**
     * <p>Initializes an arbitrary-precision integer from a portion of an array of
     * bytes. The portion of the byte array is encoded using the following rules:
     * </p><ul> <li>Positive numbers have the first byte's highest bit cleared, and
     * negative numbers have the bit set.</li><li>The last byte contains the lowest
     * 8-bits, the next-to-last contains the next lowest 8 bits, and so on. For
     * example, the number 300 can be encoded as {@code 0x01, 0x2c} and 200 as
     * {@code 0x00, 0xc8}. (Note that the second example contains a set high bit in
     * {@code 0xC8}, so an additional 0 is added at the start to ensure it's
     * interpreted as positive.)</li><li>To encode negative numbers, take the
     * absolute value of the number, subtract by 1, encode the number into bytes,
     * and reverse each bit of each byte. Any further bits that appear beyond the
     * most significant bit of the number will be all ones. For example, the number
     * -450 can be encoded as {@code 0xfe, 0x70} and -52869 as {@code 0xff, 0x31,
     * 0x7b}. (Note that the second example contains a cleared high bit in {@code
     * 0x31, 0x7B}, so an additional 0xff is added at the start to ensure it's
     * interpreted as negative.)</li></ul> <p>For little-endian, the byte order is
     * reversed from the byte order just discussed.</p>
     * @param bytes A byte array consisting of the two's-complement form (see
     * {@link com.upokecenter.numbers.EDecimal "Forms of numbers"}) of the
     * arbitrary-precision integer to create. The byte array is encoded using the
     * rules given in the FromBytes(bytes, offset, length, littleEndian) overload.
     * @param offset An index starting at 0 showing where the desired portion of
     * {@code bytes} begins.
     * @param length The length, in bytes, of the desired portion of {@code bytes}
     * (but not more than {@code bytes} 's length).
     * @param littleEndian If true, the byte order is little-endian, or
     * least-significant-byte first. If false, the byte order is big-endian, or
     * most-significant-byte first.
     * @return An arbitrary-precision integer. Returns 0 if the byte array's length
     * is 0.
     * @throws NullPointerException The parameter {@code bytes} is null.
     * @throws IllegalArgumentException Either {@code offset} or {@code length} is less
     * than 0 or greater than {@code bytes} 's length, or {@code bytes} 's length
     * minus {@code offset} is less than {@code length}.
     */
    public static EInteger FromBytes(
      byte[] bytes,
      int offset,
      int length,
      boolean littleEndian) {
      if (bytes == null) {
        throw new NullPointerException("bytes");
      }
      if (offset < 0) {
        throw new IllegalArgumentException("offset (" + offset + ") is not greater" +
          "\u0020or equal to 0");
      }
      if (offset > bytes.length) {
        throw new IllegalArgumentException("offset (" + offset + ") is not less or" +
          "\u0020equal to " + bytes.length);
      }
      if (length < 0) {
        throw new IllegalArgumentException("length (" + length + ") is not " +
          "greater or equal to 0");
      }
      if (length > bytes.length) {
        throw new IllegalArgumentException("length (" + length + ") is not less or" +
          "\u0020equal to " + bytes.length);
      }
      if (bytes.length - offset < length) {
        throw new IllegalArgumentException("bytes's length minus " + offset + " (" +
          (bytes.length - offset) + ") is not greater or equal to " + length);
      }
      if (length == 0) {
        return EInteger.FromInt32(0);
      } else if (length == 1) {
        return (((int)bytes[offset] & 0x80) == 0) ?
          FromInt32((int)bytes[offset]) :
          FromInt32(-1 - ((~bytes[offset]) & 0x7f));
      }
      int len = length;
      int wordLength = (len >> 1) + (len & 1);
      short[] newreg = new short[wordLength];
      int valueJIndex = littleEndian ? len - 1 : 0;
      boolean numIsNegative = false;
      boolean odd = (len & 1) != 0;
      int evenedLen = odd ? len - 1 : len;
      int j = 0;
      if (littleEndian) {
        for (int i = 0; i < evenedLen; i += 2, j++) {
          int index2 = i + 1;
          int nrj = ((int)bytes[offset + i]) & 0xff;
          nrj |= ((int)bytes[offset + i + 1]) << 8;
          newreg[j] = ((short)nrj);
        }
        if (odd) {
          newreg[evenedLen >> 1] =
            ((short)(((int)bytes[offset + evenedLen]) & 0xff));
        }
        numIsNegative = (bytes[offset + len - 1] & 0x80) != 0;
      } else {
        for (int i = 0; i < evenedLen; i += 2, j++) {
          int index = len - 1 - i;
          int index2 = len - 2 - i;
          int nrj = ((int)bytes[offset + index]) & 0xff;
          nrj |= ((int)bytes[offset + index2]) << 8;
          newreg[j] = ((short)nrj);
        }
        if (odd) {
          newreg[evenedLen >> 1] = ((short)(((int)bytes[offset]) &
            0xff));
        }
        numIsNegative = (bytes[offset] & 0x80) != 0;
      }
      if (numIsNegative) {
        // Sign extension and two's-complement
        if (odd) {
          newreg[len >> 1] |= ((short)0xff00);
        }
        j = (len >> 1) + 1;
        for (; j < newreg.length; ++j) {
          newreg[j] = ((short)0xffff); // sign extend remaining words
        }
        TwosComplement(newreg, 0, (int)newreg.length);
      }
      int newwordCount = newreg.length;
      while (newwordCount != 0 && newreg[newwordCount - 1] == 0) {
        --newwordCount;
      }
      return (newwordCount == 0) ? EInteger.FromInt32(0) :
        new EInteger(newwordCount, newreg, numIsNegative);
    }

    /**
     * Converts a Boolean value (true or false) to an arbitrary-precision integer.
     * @param boolValue Either true or false.
     * @return The number 1 if {@code boolValue} is true; otherwise, 0.
     */
    public static EInteger FromBoolean(boolean boolValue) {
      return boolValue ? ValueOne : ValueZero;
    }

    /**
     * Converts a 32-bit signed integer to an arbitrary-precision integer.
     * @param intValue The parameter {@code intValue} is a 32-bit signed integer.
     * @return An arbitrary-precision integer with the same value as the 64-bit
     * number.
     */
    public static EInteger FromInt32(int intValue) {
      if (intValue >= CacheFirst && intValue <= CacheLast) {
        return Cache[intValue - CacheFirst];
      }
      short[] retreg;
      boolean retnegative;
      int retwordcount;
      retnegative = intValue < 0;
      if ((intValue >> 15) == 0) {
        retreg = new short[2];
        if (retnegative) {
          intValue = -intValue;
        }
        retreg[0] = (short)(intValue & ShortMask);
        retwordcount = 1;
      } else if (intValue == Integer.MIN_VALUE) {
        retreg = new short[2];
        retreg[0] = 0;
        retreg[1] = ((short)0x8000);
        retwordcount = 2;
      } else {
        {
          retreg = new short[2];
          if (retnegative) {
            intValue = -intValue;
          }
          retreg[0] = (short)(intValue & ShortMask);
          intValue >>= 16;
          retreg[1] = (short)(intValue & ShortMask);
          retwordcount = (retreg[1] == 0) ? 1 : 2;
        }
      }
      return new EInteger(retwordcount, retreg, retnegative);
    }

    /**
     * Converts an unsigned integer expressed as a 64-bit signed integer to an
     * arbitrary-precision integer.
     * @param longerValue A 64-bit signed integer. If this value is 0 or greater,
     * the return value will represent it. If this value is less than 0, the return
     * value will store 2^64 plus this value instead.
     * @return An arbitrary-precision integer. If {@code longerValue} is 0 or
     * greater, the return value will represent it. If {@code longerValue} is less
     * than 0, the return value will store 2^64 plus this value instead.
     */
    public static EInteger FromInt64AsUnsigned(long longerValue) {
      if (longerValue >= 0) {
        return EInteger.FromInt64(longerValue);
      } else {
        return EInteger.FromInt32(1).ShiftLeft(64).Add(longerValue);
      }
    }

    /**
     * Converts a 64-bit signed integer to an arbitrary-precision integer.
     * @param longerValue The parameter {@code longerValue} is a 64-bit signed
     * integer.
     * @return An arbitrary-precision integer with the same value as the 64-bit
     * number.
     */
    public static EInteger FromInt64(long longerValue) {
      if (longerValue >= CacheFirst && longerValue <= CacheLast) {
        return Cache[(int)(longerValue - CacheFirst)];
      }
      short[] retreg;
      boolean retnegative;
      int retwordcount;
      {
        retnegative = longerValue < 0;
        if ((longerValue >> 16) == 0) {
          retreg = new short[1];
          int intValue = (int)longerValue;
          if (retnegative) {
            intValue = -intValue;
          }
          retreg[0] = (short)(intValue & ShortMask);
          retwordcount = 1;
        } else if ((longerValue >> 31) == 0) {
          retreg = new short[2];
          int intValue = (int)longerValue;
          if (retnegative) {
            intValue = -intValue;
          }
          retreg[0] = (short)(intValue & ShortMask);
          retreg[1] = (short)((intValue >> 16) & ShortMask);
          retwordcount = 2;
        } else if (longerValue == Long.MIN_VALUE) {
          retreg = new short[4];
          retreg[0] = 0;
          retreg[1] = 0;
          retreg[2] = 0;
          retreg[3] = ((short)0x8000);
          retwordcount = 4;
        } else {
          retreg = new short[4];
          long ut = longerValue;
          if (retnegative) {
            ut = -ut;
          }
          retreg[0] = (short)(ut & ShortMask);
          ut >>= 16;
          retreg[1] = (short)(ut & ShortMask);
          ut >>= 16;
          retreg[2] = (short)(ut & ShortMask);
          ut >>= 16;
          retreg[3] = (short)(ut & ShortMask);
          // at this point, the word count can't
          // be 0 (the check for 0 was already done above)
          retwordcount = 4;
          while (retwordcount != 0 &&
            retreg[retwordcount - 1] == 0) {
            --retwordcount;
          }
        }
      }
      return new EInteger(retwordcount, retreg, retnegative);
    }

    // Approximate number of digits, multiplied by 100, that fit in
    // each 16-bit word of an EInteger. This is used to calculate
    // an upper bound on the EInteger's word array size based on
    // the radix and the number of digits. Calculated from:
    // ceil(ln(65536)*100/ln(radix)).
    static final int[] DigitsInWord = {
      0, 0,
      1600, 1010, 800, 690, 619, 570, 534, 505, 482, 463, 447,
      433, 421, 410, 400, 392, 384, 377, 371, 365, 359, 354,
      349, 345, 341, 337, 333, 330, 327, 323, 320, 318, 315,
      312, 310, 308,
    };

    /**
     * Converts a string to an arbitrary-precision integer in a given radix.
     * @param str A string described by the FromRadixSubstring method.
     * @param radix A base from 2 to 36. Depending on the radix, the string can use
     * the basic digits 0 to 9 (U+0030 to U+0039) and then the basic uppercase
     * letters A to Z (U+0041 to U+005A). For example, 0-9 in radix 10, and 0-9,
     * then A-F in radix 16. Where a basic uppercase letter A to Z is allowed in
     * the string, the corresponding basic lowercase letter (U+0061 to U+007a) is
     * allowed instead.
     * @return An arbitrary-precision integer with the same value as the specified
     * string.
     * @throws NullPointerException The parameter {@code str} is null.
     * @throws NumberFormatException The string is empty or in an invalid format.
     */
    public static EInteger FromRadixString(String str, int radix) {
      if (str == null) {
        throw new NullPointerException("str");
      }
      return FromRadixSubstring(str, radix, 0, str.length());
    }

    /**
     * Converts a portion of a string to an arbitrary-precision integer in a given
     * radix.
     * @param str A text string. The desired portion of the string must contain
     * only characters allowed by the specified radix, except that it may start
     * with a minus sign ("-", U+002D) to indicate a negative number. The desired
     * portion is not allowed to contain white space characters, including spaces.
     * The desired portion may start with any number of zeros.
     * @param radix A base from 2 to 36. Depending on the radix, the string can use
     * the basic digits 0 to 9 (U+0030 to U+0039) and then the basic uppercase
     * letters A to Z (U+0041 to U+005A). For example, 0-9 in radix 10, and 0-9,
     * then A-F in radix 16. Where a basic uppercase letter A to Z is allowed in
     * the string, the corresponding basic lowercase letter (U+0061 to U+007a) is
     * allowed instead.
     * @param index The index of the string that starts the string portion.
     * @param endIndex The index of the string that ends the string portion. The
     * length will be index + endIndex - 1.
     * @return An arbitrary-precision integer with the same value as given in the
     * string portion.
     * @throws NullPointerException The parameter {@code str} is null.
     * @throws NumberFormatException The string portion is empty or in an invalid format.
     */
    public static EInteger FromRadixSubstring(
      String str,
      int radix,
      int index,
      int endIndex) {
      if (str == null) {
        throw new NullPointerException("str");
      }
      return EIntegerTextString.FromRadixSubstringImpl(
          str,
          radix,
          index,
          endIndex,
          true);
    }

    /**
     * Converts a portion of a sequence of {@code char} s to an arbitrary-precision
     * integer.
     * @param cs A sequence of {@code char} s, the desired portion of which
     * describes an integer in base-10 (decimal) form. The desired portion of the
     * sequence of {@code char} s must contain only basic digits 0 to 9 (U+0030 to
     * U+0039), except that it may start with a minus sign ("-", U+002D) to
     * indicate a negative number. The desired portion is not allowed to contain
     * white space characters, including spaces. The desired portion may start with
     * any number of zeros.
     * @param index The index of the sequence of {@code char} s that starts the
     * desired portion.
     * @param endIndex The index of the sequence of {@code char} s that ends the
     * desired portion. The length will be index + endIndex - 1.
     * @return An arbitrary-precision integer with the same value as given in the
     * sequence of {@code char} s portion.
     * @throws IllegalArgumentException The parameter {@code index} is less than 0, {@code
     * endIndex} is less than 0, or either is greater than the sequence's length,
     * or {@code endIndex} is less than {@code index}.
     * @throws NullPointerException The parameter {@code cs} is null.
     */
    public static EInteger FromSubstring(
      char[] cs,
      int index,
      int endIndex) {
      if (cs == null) {
        throw new NullPointerException("cs");
      }
      return FromRadixSubstring(cs, 10, index, endIndex);
    }

    /**
     * Converts a sequence of {@code char} s to an arbitrary-precision integer.
     * @param cs A sequence of {@code char} s describing an integer in base-10
     * (decimal) form. The sequence must contain only basic digits 0 to 9 (U+0030
     * to U+0039), except that it may start with a minus sign ("-", U+002D) to
     * indicate a negative number. The sequence is not allowed to contain white
     * space characters, including spaces. The sequence may start with any number
     * of zeros.
     * @return An arbitrary-precision integer with the same value as given in the
     * sequence of {@code char} s.
     * @throws NumberFormatException The parameter {@code cs} is in an invalid format.
     * @throws NullPointerException The parameter {@code cs} is null.
     */
    public static EInteger FromString(char[] cs) {
      if (cs == null) {
        throw new NullPointerException("cs");
      }
      int len = cs.length;
      if (len == 1) {
        char c = cs[0];
        if (c >= '0' && c <= '9') {
          return FromInt32((int)(c - '0'));
        }
        throw new NumberFormatException();
      }
      return FromRadixSubstring(cs, 10, 0, len);
    }

    /**
     * Converts a sequence of {@code char} s to an arbitrary-precision integer in a
     * given radix.
     * @param cs A sequence of {@code char} s described by the FromRadixSubstring
     * method.
     * @param radix A base from 2 to 36. Depending on the radix, the sequence of
     * {@code char} s can use the basic digits 0 to 9 (U+0030 to U+0039) and then
     * the basic uppercase letters A to Z (U+0041 to U+005A). For example, 0-9 in
     * radix 10, and 0-9, then A-F in radix 16. Where a basic uppercase letter A to
     * Z is allowed in the sequence of {@code char} s, the corresponding basic
     * lowercase letter (U+0061 to U+007a) is allowed instead.
     * @return An arbitrary-precision integer with the same value as the specified
     * sequence of {@code char} s.
     * @throws NullPointerException The parameter {@code cs} is null.
     * @throws NumberFormatException The sequence of {@code char} s is empty or in an
     * invalid format.
     */
    public static EInteger FromRadixString(char[] cs, int radix) {
      if (cs == null) {
        throw new NullPointerException("cs");
      }
      return FromRadixSubstring(cs, radix, 0, cs.length);
    }

    /**
     * Converts a portion of a sequence of {@code char} s to an arbitrary-precision
     * integer in a given radix.
     * @param cs A text sequence of {@code char} s. The desired portion of the
     * sequence of {@code char} s must contain only characters allowed by the
     * specified radix, except that it may start with a minus sign ("-", U+002D) to
     * indicate a negative number. The desired portion is not allowed to contain
     * white space characters, including spaces. The desired portion may start with
     * any number of zeros.
     * @param radix A base from 2 to 36. Depending on the radix, the sequence of
     * {@code char} s can use the basic digits 0 to 9 (U+0030 to U+0039) and then
     * the basic uppercase letters A to Z (U+0041 to U+005A). For example, 0-9 in
     * radix 10, and 0-9, then A-F in radix 16. Where a basic uppercase letter A to
     * Z is allowed in the sequence of {@code char} s, the corresponding basic
     * lowercase letter (U+0061 to U+007a) is allowed instead.
     * @param index The index of the sequence of {@code char} s that starts the
     * desired portion.
     * @param endIndex The index of the sequence of {@code char} s that ends the
     * desired portion. The length will be index + endIndex - 1.
     * @return An arbitrary-precision integer with the same value as given in the
     * sequence's portion.
     * @throws NullPointerException The parameter {@code cs} is null.
     * @throws NumberFormatException The portion is empty or in an invalid format.
     */
    public static EInteger FromRadixSubstring(
      char[] cs,
      int radix,
      int index,
      int endIndex) {
      if (cs == null) {
        throw new NullPointerException("cs");
      }
      return EIntegerCharArrayString.FromRadixSubstringImpl(
          cs,
          radix,
          index,
          endIndex,
          true);
    }

    /**
     * Converts a portion of a sequence of bytes (interpreted as text) to an
     * arbitrary-precision integer. Each byte in the sequence has to be a character
     * in the Basic Latin range (0x00 to 0x7f or U+0000 to U+007F) of the Unicode
     * Standard.
     * @param bytes A sequence of bytes (interpreted as text), the desired portion
     * of which describes an integer in base-10 (decimal) form. The desired portion
     * of the sequence of bytes (interpreted as text) must contain only basic
     * digits 0 to 9 (U+0030 to U+0039), except that it may start with a minus sign
     * ("-", U+002D) to indicate a negative number. The desired portion is not
     * allowed to contain white space characters, including spaces. The desired
     * portion may start with any number of zeros.
     * @param index The index of the sequence of bytes (interpreted as text) that
     * starts the desired portion.
     * @param endIndex The index of the sequence of bytes (interpreted as text)
     * that ends the desired portion. The length will be index + endIndex - 1.
     * @return An arbitrary-precision integer with the same value as given in the
     * sequence of bytes (interpreted as text) portion.
     * @throws IllegalArgumentException The parameter {@code index} is less than 0, {@code
     * endIndex} is less than 0, or either is greater than the sequence's length,
     * or {@code endIndex} is less than {@code index}.
     * @throws NullPointerException The parameter {@code bytes} is null.
     */
    public static EInteger FromSubstring(
      byte[] bytes,
      int index,
      int endIndex) {
      if (bytes == null) {
        throw new NullPointerException("bytes");
      }
      return FromRadixSubstring(bytes, 10, index, endIndex);
    }

    /**
     * Converts a sequence of bytes (interpreted as text) to an arbitrary-precision
     * integer. Each byte in the sequence has to be a code point in the Basic Latin
     * range (0x00 to 0x7f or U+0000 to U+007F) of the Unicode Standard.
     * @param bytes A sequence of bytes describing an integer in base-10 (decimal)
     * form. The sequence must contain only basic digits 0 to 9 (U+0030 to U+0039),
     * except that it may start with a minus sign ("-", U+002D) to indicate a
     * negative number. The sequence is not allowed to contain white space
     * characters, including spaces. The sequence may start with any number of
     * zeros.
     * @return An arbitrary-precision integer with the same value as given in the
     * sequence of bytes.
     * @throws NumberFormatException The parameter {@code bytes} is in an invalid format.
     * @throws NullPointerException The parameter {@code bytes} is null.
     */
    public static EInteger FromString(byte[] bytes) {
      if (bytes == null) {
        throw new NullPointerException("bytes");
      }
      int len = bytes.length;
      if (len == 1) {
        byte c = bytes[0];
        if (c >= '0' && c <= '9') {
          return FromInt32((int)(c - '0'));
        }
        throw new NumberFormatException();
      }
      return FromRadixSubstring(bytes, 10, 0, len);
    }

    /**
     * Converts a sequence of bytes (interpreted as text) to an arbitrary-precision
     * integer in a given radix. Each byte in the sequence has to be a character in
     * the Basic Latin range (0x00 to 0x7f or U+0000 to U+007F) of the Unicode
     * Standard.
     * @param bytes A sequence of bytes (interpreted as text) described by the
     * FromRadixSubstring method.
     * @param radix A base from 2 to 36. Depending on the radix, the sequence of
     * bytes can use the basic digits 0 to 9 (U+0030 to U+0039) and then the basic
     * uppercase letters A to Z (U+0041 to U+005A). For example, 0-9 in radix 10,
     * and 0-9, then A-F in radix 16. Where a basic uppercase letter A to Z is
     * allowed in the sequence of bytes, the corresponding basic lowercase letter
     * (U+0061 to U+007a) is allowed instead.
     * @return An arbitrary-precision integer with the same value as the specified
     * sequence of bytes.
     * @throws NullPointerException The parameter {@code bytes} is null.
     * @throws NumberFormatException The sequence of bytes (interpreted as text) is empty
     * or in an invalid format.
     */
    public static EInteger FromRadixString(byte[] bytes, int radix) {
      if (bytes == null) {
        throw new NullPointerException("bytes");
      }
      return FromRadixSubstring(bytes, radix, 0, bytes.length);
    }

    /**
     * Converts a portion of a sequence of bytes (interpreted as text) to an
     * arbitrary-precision integer in a given radix. Each byte in the sequence has
     * to be a character in the Basic Latin range (0x00 to 0x7f or U+0000 to
     * U+007F) of the Unicode Standard.
     * @param bytes A sequence of bytes (interpreted as text). The desired portion
     * of the sequence of bytes (interpreted as text) must contain only characters
     * allowed by the specified radix, except that it may start with a minus sign
     * ("-", U+002D) to indicate a negative number. The desired portion is not
     * allowed to contain white space characters, including spaces. The desired
     * portion may start with any number of zeros.
     * @param radix A base from 2 to 36. Depending on the radix, the sequence of
     * bytes (interpreted as text) can use the basic digits 0 to 9 (U+0030 to
     * U+0039) and then the basic uppercase letters A to Z (U+0041 to U+005A). For
     * example, 0-9 in radix 10, and 0-9, then A-F in radix 16. Where a basic
     * uppercase letter A to Z is allowed in the sequence of bytes (interpreted as
     * text), the corresponding basic lowercase letter (U+0061 to U+007a) is
     * allowed instead.
     * @param index The index of the sequence of bytes (interpreted as text) that
     * starts the desired portion.
     * @param endIndex The index of the sequence of bytes (interpreted as text)
     * that ends the desired portion. The length will be index + endIndex - 1.
     * @return An arbitrary-precision integer with the same value as given in the
     * sequence's portion.
     * @throws NullPointerException The parameter {@code bytes} is null.
     * @throws NumberFormatException The portion is empty or in an invalid format.
     */
    public static EInteger FromRadixSubstring(
      byte[] bytes,
      int radix,
      int index,
      int endIndex) {
      if (bytes == null) {
        throw new NullPointerException("bytes");
      }
      return EIntegerByteArrayString.FromRadixSubstringImpl(
          bytes,
          radix,
          index,
          endIndex,
          true);
    }

    /**
     * Converts a string to an arbitrary-precision integer.
     * @param str A text string describing an integer in base-10 (decimal) form.
     * The string must contain only basic digits 0 to 9 (U+0030 to U+0039), except
     * that it may start with a minus sign ("-", U+002D) to indicate a negative
     * number. The string is not allowed to contain white space characters,
     * including spaces. The string may start with any number of zeros.
     * @return An arbitrary-precision integer with the same value as given in the
     * string.
     * @throws NumberFormatException The parameter {@code str} is in an invalid format.
     * @throws NullPointerException The parameter {@code str} is null.
     */
    public static EInteger FromString(String str) {
      if (str == null) {
        throw new NullPointerException("str");
      }
      int len = str.length();
      if (len == 1) {
        char c = str.charAt(0);
        if (c >= '0' && c <= '9') {
          return FromInt32((int)(c - '0'));
        }
        throw new NumberFormatException();
      }
      return FromRadixSubstring(str, 10, 0, len);
    }

    /**
     * Converts a portion of a string to an arbitrary-precision integer.
     * @param str A text string, the desired portion of which describes an integer
     * in base-10 (decimal) form. The desired portion of the string must contain
     * only basic digits 0 to 9 (U+0030 to U+0039), except that it may start with a
     * minus sign ("-", U+002D) to indicate a negative number. The desired portion
     * is not allowed to contain white space characters, including spaces. The
     * desired portion may start with any number of zeros.
     * @param index The index of the string that starts the string portion.
     * @param endIndex The index of the string that ends the string portion. The
     * length will be index + endIndex - 1.
     * @return An arbitrary-precision integer with the same value as given in the
     * string portion.
     * @throws IllegalArgumentException The parameter {@code index} is less than 0, {@code
     * endIndex} is less than 0, or either is greater than the string's length, or
     * {@code endIndex} is less than {@code index}.
     * @throws NullPointerException The parameter {@code str} is null.
     */
    public static EInteger FromSubstring(
      String str,
      int index,
      int endIndex) {
      if (str == null) {
        throw new NullPointerException("str");
      }
      return FromRadixSubstring(str, 10, index, endIndex);
    }

    /**
     * Returns the absolute value of this object's value.
     * @return This object's value with the sign removed.
     */
    public EInteger Abs() {
      return (this.wordCount == 0 || !this.negative) ? this : new
        EInteger(this.wordCount, this.words, false);
    }

    /**
     * Adds this arbitrary-precision integer and another arbitrary-precision
     * integer and returns the result.
     * @param bigintAugend Another arbitrary-precision integer.
     * @return The sum of the two numbers, that is, this arbitrary-precision
     * integer plus another arbitrary-precision integer.
     * @throws NullPointerException The parameter {@code bigintAugend} is null.
     */
    public EInteger Add(EInteger bigintAugend) {
      if (bigintAugend == null) {
        throw new NullPointerException("bigintAugend");
      }
      if (this.wordCount == 0) {
        return bigintAugend;
      }
      if (bigintAugend.wordCount == 0) {
        return this;
      }
      short[] sumreg;
      if (bigintAugend.wordCount == 1 && this.wordCount == 1) {
        if (this.negative == bigintAugend.negative) {
          int intSum = (((int)this.words[0]) & ShortMask) +
            (((int)bigintAugend.words[0]) & ShortMask);
          sumreg = new short[2];
          sumreg[0] = ((short)intSum);
          sumreg[1] = ((short)(intSum >> 16));
          return new EInteger(
              ((intSum >> 16) == 0) ? 1 : 2,
              sumreg,
              this.negative);
        } else {
          int a = ((int)this.words[0]) & ShortMask;
          int b = ((int)bigintAugend.words[0]) & ShortMask;
          if (a == b) {
            return EInteger.FromInt32(0);
          }
          if (a > b) {
            a -= b;
            sumreg = new short[2];
            sumreg[0] = ((short)a);
            return new EInteger(1, sumreg, this.negative);
          }
          b -= a;
          sumreg = new short[2];
          sumreg[0] = ((short)b);
          return new EInteger(1, sumreg, !this.negative);
        }
      }
      if ((!this.negative) == (!bigintAugend.negative)) {
        // both nonnegative or both negative
        int addendCount = this.wordCount;
        int augendCount = bigintAugend.wordCount;
        if (augendCount <= 2 && addendCount <= 2 &&
          (this.wordCount < 2 || (this.words[1] >> 15) == 0) &&
          (bigintAugend.wordCount < 2 || (bigintAugend.words[1] >> 15) == 0)) {
          int a = ((int)this.words[0]) & ShortMask;
          if (this.wordCount == 2) {
            a |= (((int)this.words[1]) & ShortMask) << 16;
          }
          int b = ((int)bigintAugend.words[0]) & ShortMask;
          if (bigintAugend.wordCount == 2) {
            b |= (((int)bigintAugend.words[1]) & ShortMask) << 16;
          }
          a = ((int)(a + b));
          sumreg = new short[2];
          sumreg[0] = ((short)(a & ShortMask));
          sumreg[1] = ((short)((a >> 16) & ShortMask));
          int wcount = (sumreg[1] == 0) ? 1 : 2;
          return new EInteger(wcount, sumreg, this.negative);
        }
        if (augendCount <= 2 && addendCount <= 2) {
          int a = ((int)this.words[0]) & ShortMask;
          if (this.wordCount == 2) {
            a |= (((int)this.words[1]) & ShortMask) << 16;
          }
          int b = ((int)bigintAugend.words[0]) & ShortMask;
          if (bigintAugend.wordCount == 2) {
            b |= (((int)bigintAugend.words[1]) & ShortMask) << 16;
          }
          long longResult = ((long)a) & 0xffffffffL;
          longResult += ((long)b) & 0xffffffffL;
          if ((longResult >> 32) == 0) {
            a = ((int)longResult);
            sumreg = new short[2];
            sumreg[0] = ((short)(a & ShortMask));
            sumreg[1] = ((short)((a >> 16) & ShortMask));
            int wcount = (sumreg[1] == 0) ? 1 : 2;
            return new EInteger(wcount, sumreg, this.negative);
          }
        }
        // System.out.println("" + this + " + " + bigintAugend);
        int wordLength2 = (int)Math.max(
          this.words.length,
          bigintAugend.words.length);
        sumreg = new short[wordLength2];
        int carry;
        int desiredLength = Math.max(addendCount, augendCount);
        if (addendCount == augendCount) {
          carry = AddInternal(
              sumreg,
              0,
              this.words,
              0,
              bigintAugend.words,
              0,
              addendCount);
        } else if (addendCount > augendCount) {
          // Addend is bigger
          carry = AddInternal(
              sumreg,
              0,
              this.words,
              0,
              bigintAugend.words,
              0,
              augendCount);
          System.arraycopy(
            this.words,
            augendCount,
            sumreg,
            augendCount,
            addendCount - augendCount);
          if (carry != 0) {
            carry = IncrementWords(
                sumreg,
                augendCount,
                addendCount - augendCount,
                (short)carry);
          }
        } else {
          // Augend is bigger
          carry = AddInternal(
              sumreg,
              0,
              this.words,
              0,
              bigintAugend.words,
              0,
              (int)addendCount);
          System.arraycopy(
            bigintAugend.words,
            addendCount,
            sumreg,
            addendCount,
            augendCount - addendCount);
          if (carry != 0) {
            carry = IncrementWords(
                sumreg,
                addendCount,
                (int)(augendCount - addendCount),
                (short)carry);
          }
        }
        boolean needShorten = true;
        if (carry != 0) {
          int nextIndex = desiredLength;
          int len = nextIndex + 1;
          sumreg = CleanGrow(sumreg, len);
          sumreg[nextIndex] = (short)carry;
          needShorten = false;
        }
        int sumwordCount = CountWords(sumreg);
        if (sumwordCount == 0) {
          return EInteger.FromInt32(0);
        }
        if (needShorten) {
          sumreg = ShortenArray(sumreg, sumwordCount);
        }
        return new EInteger(sumwordCount, sumreg, this.negative);
      }
      EInteger minuend = this;
      EInteger subtrahend = bigintAugend;
      if (this.negative) {
        // this is negative, b is nonnegative
        minuend = bigintAugend;
        subtrahend = this;
      }
      // Do a subtraction
      int words1Size = minuend.wordCount;
      int words2Size = subtrahend.wordCount;
      boolean diffNeg = false;

      short borrow;
      int wordLength = (int)Math.max(
        minuend.words.length,
        subtrahend.words.length);
      short[] diffReg = new short[wordLength];
      if (words1Size == words2Size) {
        if (Compare(minuend.words, 0, subtrahend.words, 0, (int)words1Size) >=
          0) {
          // words1 is at least as high as words2
          SubtractInternal(
            diffReg,
            0,
            minuend.words,
            0,
            subtrahend.words,
            0,
            words1Size);
        } else {
          // words1 is less than words2
          SubtractInternal(
            diffReg,
            0,
            subtrahend.words,
            0,
            minuend.words,
            0,
            words1Size);
          diffNeg = true; // difference will be negative
        }
      } else if (words1Size > words2Size) {
        // words1 is greater than words2
        borrow = (short)SubtractInternal(
          diffReg,
          0,
          minuend.words,
          0,
          subtrahend.words,
          0,
          words2Size);
        System.arraycopy(
          minuend.words,
          words2Size,
          diffReg,
          words2Size,
          words1Size - words2Size);
        DecrementWords(
          diffReg,
          words2Size,
          words1Size - words2Size,
          borrow);
      } else {
        // words1 is less than words2
        borrow = (short)SubtractInternal(
          diffReg,
          0,
          subtrahend.words,
          0,
          minuend.words,
          0,
          words1Size);
        System.arraycopy(
          subtrahend.words,
          words1Size,
          diffReg,
          words1Size,
          words2Size - words1Size);
        DecrementWords(
          diffReg,
          words1Size,
          words2Size - words1Size,
          borrow);
        diffNeg = true;
      }
      int count = CountWords(diffReg);
      if (count == 0) {
        return EInteger.FromInt32(0);
      }
      diffReg = ShortenArray(diffReg, count);
      return new EInteger(count, diffReg, diffNeg);
    }

    /**
     * Converts this object's value to a 32-bit signed integer, throwing an
     * exception if it can't fit.
     * @return A 32-bit signed integer.
     * @throws ArithmeticException This object's value is too big to fit a
     * 32-bit signed integer.
     * @deprecated Renamed to ToInt32Checked.
 */
@Deprecated
    public int AsInt32Checked() {
      return this.ToInt32Checked();
    }

    /**
     * Converts this object's value to a 32-bit signed integer. If the value can't
     * fit in a 32-bit integer, returns the lower 32 bits of this object's
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}) (in which case the return value might have a different sign than
     * this object's value).
     * @return A 32-bit signed integer.
     * @deprecated Renamed to ToInt32Unchecked.
 */
@Deprecated
    public int AsInt32Unchecked() {
      return this.ToInt32Unchecked();
    }

    /**
     * Converts this object's value to a 64-bit signed integer, throwing an
     * exception if it can't fit.
     * @return A 64-bit signed integer.
     * @throws ArithmeticException This object's value is too big to fit a
     * 64-bit signed integer.
     * @deprecated Renamed to ToInt64Checked.
 */
@Deprecated
    public long AsInt64Checked() {
      return this.ToInt64Checked();
    }

    /**
     * Converts this object's value to a 64-bit signed integer. If the value can't
     * fit in a 64-bit integer, returns the lower 64 bits of this object's
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}) (in which case the return value might have a different sign than
     * this object's value).
     * @return A 64-bit signed integer.
     * @deprecated Renamed to ToInt64Unchecked.
 */
@Deprecated
    public long AsInt64Unchecked() {
      return this.ToInt64Unchecked();
    }

    /**
     * Returns whether this object's value can fit in a 32-bit signed integer.
     * @return {@code true} if this object's value is from -2147483648 through
     * 2147483647; otherwise, {@code false}.
     */
    public boolean CanFitInInt32() {
      int c = this.wordCount;
      if (c > 2) {
        return false;
      }
      if (c == 2 && (this.words[1] & 0x8000) != 0) {
        return this.negative && this.words[1] == ((short)0x8000) &&
          this.words[0] == 0;
      }
      return true;
    }

    /**
     * Returns whether this object's value can fit in a 64-bit signed integer.
     * @return {@code true} if this object's value is from -9223372036854775808
     * through 9223372036854775807; otherwise, {@code false}.
     */
    public boolean CanFitInInt64() {
      int c = this.wordCount;
      if (c > 4) {
        return false;
      }
      if (c == 4 && (this.words[3] & 0x8000) != 0) {
        return this.negative && this.words[3] == ((short)0x8000) &&
          this.words[2] == 0 && this.words[1] == 0 &&
          this.words[0] == 0;
      }
      return true;
    }

    /**
     * Compares an arbitrary-precision integer with this instance.
     * @param other The integer to compare to this value.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater. This implementation
     * returns a positive number if.
     */
    public int compareTo(EInteger other) {
      if (other == null) {
        return 1;
      }
      if (this == other) {
        return 0;
      }
      int size = this.wordCount, tempSize = other.wordCount;
      int sa = size == 0 ? 0 : (this.negative ? -1 : 1);
      int sb = tempSize == 0 ? 0 : (other.negative ? -1 : 1);
      if (sa != sb) {
        return (sa < sb) ? -1 : 1;
      }
      if (sa == 0) {
        return 0;
      }
      if (size == tempSize) {
        if (size == 1 && this.words[0] == other.words[0]) {
          return 0;
        } else {
          short[] words1 = this.words;
          short[] words2 = other.words;
          while ((size--) != 0) {
            int an = ((int)words1[size]) & ShortMask;
            int bn = ((int)words2[size]) & ShortMask;
            if (an > bn) {
              return (sa > 0) ? 1 : -1;
            }
            if (an < bn) {
              return (sa > 0) ? -1 : 1;
            }
          }
          return 0;
        }
      }
      return ((size > tempSize) ^ (sa <= 0)) ? 1 : -1;
    }

    /**
     * Returns the greater of two arbitrary-precision integers.
     * @param first The first integer to compare.
     * @param second The second integer to compare.
     * @return The greater of the two integers.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
    public static EInteger Max(EInteger first, EInteger second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return first.compareTo(second) > 0 ? first : second;
    }

    /**
     * Returns the smaller of two arbitrary-precision integers.
     * @param first The first integer to compare.
     * @param second The second integer to compare.
     * @return The smaller of the two integers.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
    public static EInteger Min(EInteger first, EInteger second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      return first.compareTo(second) < 0 ? first : second;
    }

    /**
     * Of two arbitrary-precision integers, returns the one with the greater
     * absolute value. If both integers have the same absolute value, this method
     * has the same effect as Max.
     * @param first The first integer to compare.
     * @param second The second integer to compare.
     * @return The integer with the greater absolute value.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
    public static EInteger MaxMagnitude(EInteger first, EInteger second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      int cmp = first.Abs().compareTo(second.Abs());
      return (cmp == 0) ? Max(first, second) : (cmp > 0 ? first : second);
    }

    /**
     * Of two arbitrary-precision integers, returns the one with the smaller
     * absolute value. If both integers have the same absolute value, this method
     * has the same effect as Min.
     * @param first The first integer to compare.
     * @param second The second integer to compare.
     * @return The integer with the smaller absolute value.
     * @throws NullPointerException The parameter {@code first} or {@code second}
     * is null.
     */
    public static EInteger MinMagnitude(EInteger first, EInteger second) {
      if (first == null) {
        throw new NullPointerException("first");
      }
      if (second == null) {
        throw new NullPointerException("second");
      }
      int cmp = first.Abs().compareTo(second.Abs());
      return (cmp == 0) ? Min(first, second) : (cmp < 0 ? first : second);
    }

    /**
     * Adds this arbitrary-precision integer and a 32-bit signed integer and
     * returns the result.
     * @param intValue The parameter {@code intValue} is a 32-bit signed integer.
     * @return The sum of the two numbers, that is, this arbitrary-precision
     * integer plus a 32-bit signed integer.
     */
    public EInteger Add(int intValue) {
      if (intValue == 0) {
        return this;
      }
      if (this.wordCount == 0) {
        return EInteger.FromInt32(intValue);
      }
      if (this.wordCount == 1 && intValue >= -0x7ffe0000 && intValue <
        0x7ffe0000) {
        short[] sumreg;
        int intSum = this.negative ?
          intValue - (((int)this.words[0]) & ShortMask) :
          intValue + (((int)this.words[0]) & ShortMask);
        if (intSum >= CacheFirst && intSum <= CacheLast) {
          return Cache[intSum - CacheFirst];
        } else if ((intSum >> 16) == 0) {
          sumreg = new short[1];
          sumreg[0] = ((short)intSum);
          return new EInteger(
              1,
              sumreg,
              false);
        } else if (intSum > 0) {
          sumreg = new short[2];
          sumreg[0] = ((short)intSum);
          sumreg[1] = ((short)(intSum >> 16));
          return new EInteger(
              2,
              sumreg,
              false);
        } else if (intSum > -65536) {
          sumreg = new short[1];
          intSum = -intSum;
          sumreg[0] = ((short)intSum);
          return new EInteger(
              1,
              sumreg,
              true);
        } else {
          sumreg = new short[2];
          intSum = -intSum;
          sumreg[0] = ((short)intSum);
          sumreg[1] = ((short)(intSum >> 16));
          return new EInteger(
              2,
              sumreg,
              true);
        }
      }
      return this.Add(EInteger.FromInt32(intValue));
    }

    /**
     * Subtracts a 32-bit signed integer from this arbitrary-precision integer and
     * returns the result.
     * @param intValue The parameter {@code intValue} is a 32-bit signed integer.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision integer minus a 32-bit signed integer.
     */
    public EInteger Subtract(int intValue) {
      return (intValue == Integer.MIN_VALUE) ?
        this.Subtract(EInteger.FromInt32(intValue)) : ((intValue == 0) ?
        this : this.Add(-intValue));
    }

    /**
     * <p>Multiplies this arbitrary-precision integer by a 32-bit signed integer
     * and returns the result.</p><pre>EInteger result =
     * EInteger.FromString("5").Multiply(200);</pre> .
     * @param intValue The parameter {@code intValue} is a 32-bit signed integer.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * integer times a 32-bit signed integer.
     */
    public EInteger Multiply(int intValue) {
      return this.Multiply(EInteger.FromInt32(intValue));
    }

    /**
     * Divides this arbitrary-precision integer by a 32-bit signed integer and
     * returns the result. The result of the division is rounded down (the
     * fractional part is discarded). Except if the result of the division is 0, it
     * will be negative if this arbitrary-precision integer is positive and the
     * other 32-bit signed integer is negative, or vice versa, and will be positive
     * if both are positive or both are negative.
     * @param intValue The divisor.
     * @return The result of dividing this arbitrary-precision integer by a 32-bit
     * signed integer. The result of the division is rounded down (the fractional
     * part is discarded). Except if the result of the division is 0, it will be
     * negative if this arbitrary-precision integer is positive and the other
     * 32-bit signed integer is negative, or vice versa, and will be positive if
     * both are positive or both are negative.
     * @throws ArithmeticException Attempted to divide by zero.
     */
    public EInteger Divide(int intValue) {
      return this.Divide(EInteger.FromInt32(intValue));
    }

    /**
     * Returns the remainder that would result when this arbitrary-precision
     * integer is divided by a 32-bit signed integer. The remainder is the number
     * that remains when the absolute value of this arbitrary-precision integer is
     * divided by the absolute value of the other 32-bit signed integer; the
     * remainder has the same sign (positive or negative) as this
     * arbitrary-precision integer.
     * @param intValue The parameter {@code intValue} is a 32-bit signed integer.
     * @return The remainder that would result when this arbitrary-precision
     * integer is divided by a 32-bit signed integer.
     * @throws ArithmeticException Attempted to divide by zero.
     * @throws NullPointerException The parameter {@code intValue} is null.
     */
    public EInteger Remainder(int intValue) {
      return this.Remainder(EInteger.FromInt32(intValue));
    }

    /**
     * Compares an arbitrary-precision integer with this instance.
     * @param intValue The parameter {@code intValue} is a 32-bit signed integer.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater.
     */
    public int compareTo(int intValue) {
      int c = this.wordCount;
      if (c > 2) {
        return this.negative ? -1 : 1;
      }
      if (c == 2 && (this.words[1] & 0x8000) != 0) {
        if (this.negative && this.words[1] == ((short)0x8000) &&
          this.words[0] == 0) {
          // This value is Integer.MIN_VALUE
          return intValue == Integer.MIN_VALUE ? 0 : -1;
        } else {
          return this.negative ? -1 : 1;
        }
      }
      int thisInt = this.ToInt32Unchecked();
      return thisInt == intValue ? 0 : (thisInt < intValue ? -1 : 1);
    }

    /**
     * Divides this arbitrary-precision integer by another arbitrary-precision
     * integer and returns the result. The result of the division is rounded down
     * (the fractional part is discarded). Except if the result of the division is
     * 0, it will be negative if this arbitrary-precision integer is positive and
     * the other arbitrary-precision integer is negative, or vice versa, and will
     * be positive if both are positive or both are negative.
     * @param bigintDivisor The divisor.
     * @return The result of dividing this arbitrary-precision integer by another
     * arbitrary-precision integer. The result of the division is rounded down (the
     * fractional part is discarded). Except if the result of the division is 0, it
     * will be negative if this arbitrary-precision integer is positive and the
     * other arbitrary-precision integer is negative, or vice versa, and will be
     * positive if both are positive or both are negative.
     * @throws NullPointerException The parameter {@code bigintDivisor} is null.
     * @throws ArithmeticException Attempted to divide by zero.
     */
    public EInteger Divide(EInteger bigintDivisor) {
      if (bigintDivisor == null) {
        throw new NullPointerException("bigintDivisor");
      }
      int words1Size = this.wordCount;
      int words2Size = bigintDivisor.wordCount;
      // ---- Special cases
      if (words2Size == 0) {
        // Divisor is 0
        throw new ArithmeticException();
      }
      if (words1Size < words2Size) {
        // Dividend is less than divisor (includes case
        // where dividend is 0)
        return EInteger.FromInt32(0);
      }
      // System.out.println("divide " + this + " " + bigintDivisor);
      if (words1Size <= 2 && words2Size <= 2 && this.CanFitInInt32() &&
        bigintDivisor.CanFitInInt32()) {
        int valueASmall = this.ToInt32Checked();
        int valueBSmall = bigintDivisor.ToInt32Checked();
        if (valueASmall != Integer.MIN_VALUE || valueBSmall != -1) {
          int result = valueASmall / valueBSmall;
          return EInteger.FromInt32(result);
        }
      }
      if (words1Size <= 4 && words2Size <= 4 && this.CanFitInInt64() &&
        bigintDivisor.CanFitInInt64()) {
        long valueALong = this.ToInt64Checked();
        long valueBLong = bigintDivisor.ToInt64Checked();
        if (valueALong != Long.MIN_VALUE || valueBLong != -1) {
          long resultLong = valueALong / valueBLong;
          return EInteger.FromInt64(resultLong);
        }
      }
      short[] quotReg;
      int quotwordCount;
      if (words2Size == 1) {
        // divisor is small, use a fast path
        quotReg = new short[this.words.length];
        quotwordCount = this.wordCount;
        FastDivide(quotReg, this.words, words1Size, bigintDivisor.words[0]);
        while (quotwordCount != 0 && quotReg[quotwordCount - 1] == 0) {
          --quotwordCount;
        }
        return (quotwordCount != 0) ? new EInteger(
          quotwordCount,
          quotReg,
          this.negative ^ bigintDivisor.negative) : EInteger.FromInt32(0);
      }
      // ---- General case
      quotReg = new short[(int)(words1Size - words2Size + 1)];
      GeneralDivide(
        this.words,
        0,
        this.wordCount,
        bigintDivisor.words,
        0,
        bigintDivisor.wordCount,
        quotReg,
        0,
        null,
        0);
      quotwordCount = CountWords(quotReg);
      quotReg = ShortenArray(quotReg, quotwordCount);
      return (quotwordCount != 0) ? new EInteger(quotwordCount,
        quotReg,
        this.negative ^ bigintDivisor.negative) :
        EInteger.FromInt32(0);
    }

    private static int LinearMultiplySubtractMinuend1Bigger(
      short[] resultArr,
      int resultStart,
      short[] minuendArr,
      int minuendArrStart,
      int factor1,
      short[] factor2,
      int factor2Start,
      int factor2Count) {
      int a = 0;
      int b = 0;
      int SMask = ShortMask;
      int cc = 0;
      if (factor1 == 0) {
        for (int i = 0; i < factor2Count; ++i) {
          b = ((int)minuendArr[minuendArrStart + i] & SMask) - cc;
          resultArr[resultStart + i] = ((short)b);
          cc = (b >> 31) & 1;
        }
      } else {
        for (int i = 0; i < factor2Count; ++i) {
          a = ((((int)factor2[factor2Start + i]) & SMask) * factor1);
          a = (a + cc);
          b = ((int)minuendArr[minuendArrStart + i] & SMask) - (a & SMask);
          resultArr[resultStart + i] = ((short)b);
          cc = (a >> 16) + ((b >> 31) & 1);
          cc &= SMask;
        }
      }
      a = cc;
      b = ((int)minuendArr[minuendArrStart + factor2Count] & SMask) - a;
      resultArr[resultStart + factor2Count] = ((short)b);
      cc = (b >> 31) & 1;
      return cc;
    }

    private static void DivideThreeBlocksByTwo(
      short[] valueALow,
      int posALow,
      short[] valueAMidHigh,
      int posAMidHigh,
      short[] b,
      int posB,
      int blockCount,
      short[] quot,
      int posQuot,
      short[] rem,
      int posRem,
      short[] tmp) {
      // NOTE: size of 'quot' equals 'blockCount' * 2
      // NOTE: size of 'rem' equals 'blockCount' * 2

      // Implements Algorithm 2 of Burnikel & Ziegler 1998
      int c;
      // If AHigh is less than BHigh
      if (
        WordsCompare(
          valueAMidHigh,
          posAMidHigh + blockCount,
          blockCount,
          b,
          posB + blockCount,
          blockCount) < 0) {
        // Divide AMidHigh by BHigh
        RecursiveDivideInner(
          valueAMidHigh,
          posAMidHigh,
          b,
          posB + blockCount,
          quot,
          posQuot,
          rem,
          posRem,
          blockCount);
        // Copy remainder to temp at block position 4
        System.arraycopy(rem, posRem, tmp, blockCount * 4, blockCount);
        java.util.Arrays.fill(tmp, blockCount * 5, (blockCount * 5)+(blockCount), (short)0);
      } else {
        // BHigh is less than AHigh
        // set quotient to all ones
        short allones = ((short)0xffff);
        for (int i = 0; i < blockCount; ++i) {
          quot[posQuot + i] = allones;
        }
        java.util.Arrays.fill(quot, posQuot + blockCount, (posQuot + blockCount)+(blockCount), (short)0);
        // copy AMidHigh to temp
        System.arraycopy(
          valueAMidHigh,
          posAMidHigh,
          tmp,
          blockCount * 4,
          blockCount * 2);
        // subtract BHigh from temp's high block
        SubtractInternal(
          tmp,
          blockCount * 5,
          tmp,
          blockCount * 5,
          b,
          posB + blockCount,
          blockCount);
        // add BHigh to temp
        c = AddInternal(
            tmp,
            blockCount * 4,
            tmp,
            blockCount * 4,
            b,
            posB + blockCount,
            blockCount);
        IncrementWords(tmp, blockCount * 5, blockCount, (short)c);
      }
      AsymmetricMultiply(
        tmp,
        0,
        tmp,
        blockCount * 2,
        quot,
        posQuot,
        blockCount,
        b,
        posB,
        blockCount);
      int bc3 = blockCount * 3;
      System.arraycopy(valueALow, posALow, tmp, bc3, blockCount);
      java.util.Arrays.fill(tmp, blockCount * 2, (blockCount * 2)+(blockCount), (short)0);
      c = SubtractInternal(tmp, bc3, tmp, bc3, tmp, 0, blockCount * 3);
      if (c != 0) {
        while (true) {
          c = AddInternal(tmp, bc3, tmp, bc3, b, posB, blockCount * 2);
          c = IncrementWords(tmp, blockCount * 5, blockCount, (short)c);
          DecrementWords(quot, posQuot, blockCount * 2, (short)1);
          if (c != 0) {
            break;
          }
        }
      }
      System.arraycopy(tmp, bc3, rem, posRem, blockCount * 2);
    }

    private static void RecursiveDivideInner(
      short[] a,
      int posA,
      short[] b,
      int posB,
      short[] quot,
      int posQuot,
      short[] rem,
      int posRem,
      int blockSize) {
      // NOTE: size of 'a', 'quot', and 'rem' is 'blockSize'*2
      // NOTE: size of 'b' is 'blockSize'

      // Implements Algorithm 1 of Burnikel & Ziegler 1998
      if (blockSize < RecursiveDivisionLimit || (blockSize & 1) == 1) {
        GeneralDivide(
          a,
          posA,
          blockSize * 2,
          b,
          posB,
          blockSize,
          quot,
          posQuot,
          rem,
          posRem);
      } else {
        int halfBlock = blockSize >> 1;
        short[] tmp = new short[halfBlock * 10];
        java.util.Arrays.fill(quot, posQuot, (posQuot)+(blockSize * 2), (short)0);
        java.util.Arrays.fill(rem, posRem, (posRem)+(blockSize), (short)0);
        DivideThreeBlocksByTwo(
          a,
          posA + halfBlock,
          a,
          posA + blockSize,
          b,
          posB,
          halfBlock,
          tmp,
          halfBlock * 6,
          tmp,
          halfBlock * 8,
          tmp);
        DivideThreeBlocksByTwo(
          a,
          posA,
          tmp,
          halfBlock * 8,
          b,
          posB,
          halfBlock,
          quot,
          posQuot,
          rem,
          posRem,
          tmp);
        System.arraycopy(tmp, halfBlock * 6, quot, posQuot + halfBlock, halfBlock);
      }
    }

    private static void RecursiveDivide(
      short[] a,
      int posA,
      int countA,
      short[] b,
      int posB,
      int countB,
      short[] quot,
      int posQuot,
      short[] rem,
      int posRem) {
      int workPosA, workPosB, i;
      short[] workA = a;
      short[] workB = b;
      workPosA = posA;
      workPosB = posB;
      int blocksB = RecursiveDivisionLimit;
      int shiftB = 0;
      int m = 1;
      while (blocksB < countB) {
        blocksB <<= 1;
        m <<= 1;
      }
      workB = new short[blocksB];
      workPosB = 0;
      System.arraycopy(b, posB, workB, blocksB - countB, countB);
      int shiftA = 0;
      int extraWord = 0;
      int wordsA = countA + (blocksB - countB);
      if ((b[countB - 1] & 0x8000) == 0) {
        int x = b[countB - 1];
        while ((x & 0x8000) == 0) {
          ++shiftB;
          x <<= 1;
        }
        x = a[countA - 1];
        while ((x & 0x8000) == 0) {
          ++shiftA;
          x <<= 1;
        }
        if (shiftA < shiftB) {
          // Shifting A would require an extra word
          ++extraWord;
        }
        ShiftWordsLeftByBits(
          workB,
          workPosB + blocksB - countB,
          countB,
          shiftB);
      }
      int blocksA = (wordsA + extraWord + (blocksB - 1)) / blocksB;
      int totalWordsA = blocksA * blocksB;
      workA = new short[totalWordsA];
      workPosA = 0;
      System.arraycopy(
        a,
        posA,
        workA,
        workPosA + (blocksB - countB),
        countA);
      ShiftWordsLeftByBits(
        workA,
        workPosA + (blocksB - countB),
        countA + extraWord,
        shiftB);
      // Start division
      // "tmprem" holds temporary space for the following:
      // - blocksB: Remainder
      // - blocksB * 2: Dividend
      // - blocksB * 2: Quotient
      short[] tmprem = new short[blocksB * 5];
      int size = 0;
      for (i = blocksA - 1; i >= 0; --i) {
        int workAIndex = workPosA + (i * blocksB);
        // Set the low part of the subdividend with the working
        // block of the dividend
        System.arraycopy(workA, workAIndex, tmprem, blocksB, blocksB);
        // Clear the quotient
        java.util.Arrays.fill(tmprem, blocksB * 3, (blocksB * 3)+(blocksB << 1), (short)0);
        RecursiveDivideInner(
          tmprem,
          blocksB,
          workB,
          workPosB,
          tmprem,
          blocksB * 3,
          tmprem,
          0,
          blocksB);
        if (quot != null) {
          size = Math.min(blocksB, quot.length - (i * blocksB));
          // System.out.println("quot len=" + quot.length + ",bb=" + blocksB +
          // ",size=" + size + " [" + countA + "," + countB + "]");
          if (size > 0) {
            System.arraycopy(
              tmprem,
              blocksB * 3,
              quot,
              posQuot + (i * blocksB),
              size);
          }
        }
        // Set the high part of the subdividend with the remainder
        System.arraycopy(tmprem, 0, tmprem, blocksB << 1, blocksB);
      }
      if (rem != null) {
        System.arraycopy(tmprem, blocksB - countB, rem, posRem, countB);
        ShiftWordsRightByBits(rem, posRem, countB, shiftB);
      }
    }

    private static String WordsToString(short[] a, int pos, int len) {
      while (len != 0 && a[pos + len - 1] == 0) {
        --len;
      }
      if (len == 0) {
        return "\"0\"";
      }
      short[] words = new short[len];
      System.arraycopy(a, pos, words, 0, len);
      return "\"" + new EInteger(len, words, false).toString() + "\"";
    }
    private static String WordsToStringHex(short[] a, int pos, int len) {
      while (len != 0 && a[pos + len - 1] == 0) {
        --len;
      }
      if (len == 0) {
        return "\"0\"";
      }
      short[] words = new short[len];
      System.arraycopy(a, pos, words, 0, len);
      return "\"" + new EInteger(len, words, false).ToRadixString(16) +
        "\"";
    }
    private static String WordsToString2(
      short[] a,
      int pos,
      int len,
      short[] b,
      int pos2,
      int len2) {
      short[] words = new short[len + len2];
      System.arraycopy(a, pos, words, 0, len);
      System.arraycopy(b, pos2, words, len, len2);
      len += len2;
      while (len != 0 && words[len - 1] == 0) {
        --len;
      }
      return (len == 0) ? "\"0\"" : ("\"" + new EInteger(
        len,
        words,
        false).toString() + "\"");
    }

    private static void GeneralDivide(
      short[] a,
      int posA,
      int countA,
      short[] b,
      int posB,
      int countB,
      short[] quot,
      int posQuot,
      short[] rem,
      int posRem) {
      int origQuotSize = countA - countB + 1;
      int origCountA = countA;
      int origCountB = countB;
      while (countB > 0 && b[posB + countB - 1] == 0) {
        --countB;
      }
      while (countA > 0 && a[posA + countA - 1] == 0) {
        --countA;
      }
      int newQuotSize = countA - countB + 1;
      if (quot != null) {
        if (newQuotSize < 0 || newQuotSize >= origQuotSize) {
          java.util.Arrays.fill(quot, posQuot, (posQuot)+(Math.max(0, origQuotSize)), (short)0);
        } else {
          java.util.Arrays.fill(quot, posQuot + newQuotSize, (posQuot + newQuotSize)+(Math.max(0, origQuotSize - newQuotSize)), (short)0);
        }
      }
      if (rem != null) {
        java.util.Arrays.fill(rem, posRem + countB, (posRem + countB)+(origCountB - countB), (short)0);
      }

      if (countA < countB) {
        // A is less than B, so quotient is 0, remainder is "a"
        if (quot != null) {
          java.util.Arrays.fill(quot, posQuot, (posQuot)+(Math.max(0, origQuotSize)), (short)0);
        }
        if (rem != null) {
          System.arraycopy(a, posA, rem, posRem, origCountA);
        }
        return;
      } else if (countA == countB) {
        int cmp = Compare(a, posA, b, posB, countA);
        if (cmp == 0) {
          // A equals B, so quotient is 1, remainder is 0
          if (quot != null) {
            quot[posQuot] = 1;
            java.util.Arrays.fill(quot, posQuot + 1, (posQuot + 1)+(Math.max(0, origQuotSize - 1)), (short)0);
          }
          if (rem != null) {
            java.util.Arrays.fill(rem, posRem, (posRem)+(countA), (short)0);
          }
          return;
        } else if (cmp < 0) {
          // A is less than B, so quotient is 0, remainder is "a"
          if (quot != null) {
            java.util.Arrays.fill(quot, posQuot, (posQuot)+(Math.max(0, origQuotSize)), (short)0);
          }
          if (rem != null) {
            System.arraycopy(a, posA, rem, posRem, origCountA);
          }
          return;
        }
      }
      if (countB == 1) {
        // Divisor is a single word
        short shortRemainder = FastDivideAndRemainder(
            quot,
            posQuot,
            a,
            posA,
            countA,
            b[posB]);
        if (rem != null) {
          rem[posRem] = shortRemainder;
        }
        return;
      }
      int workPosA, workPosB;
      short[] workAB = null;
      short[] workA = a;
      short[] workB = b;
      workPosA = posA;
      workPosB = posB;
      if (countB > RecursiveDivisionLimit) {
        RecursiveDivide(
          a,
          posA,
          countA,
          b,
          posB,
          countB,
          quot,
          posQuot,
          rem,
          posRem);
        return;
      }
      int sh = 0;
      boolean noShift = false;
      if ((b[posB + countB - 1] & 0x8000) == 0) {
        // Normalize a and b by shifting both until the high
        // bit of b is the highest bit of the last word
        int x = b[posB + countB - 1];
        if (x == 0) {
          throw new IllegalStateException();
        }
        while ((x & 0x8000) == 0) {
          ++sh;
          x <<= 1;
        }
        workAB = new short[countA + 1 + countB];
        workPosA = 0;
        workPosB = countA + 1;
        workA = workAB;
        workB = workAB;
        System.arraycopy(a, posA, workA, workPosA, countA);
        System.arraycopy(b, posB, workB, workPosB, countB);
        ShiftWordsLeftByBits(workA, workPosA, countA + 1, sh);
        ShiftWordsLeftByBits(workB, workPosB, countB, sh);
      } else {
        noShift = true;
        workA = new short[countA + 1];
        workPosA = 0;
        System.arraycopy(a, posA, workA, workPosA, countA);
      }
      int c = 0;
      short pieceBHigh = workB[workPosB + countB - 1];
      int pieceBHighInt = ((int)pieceBHigh) & ShortMask;
      int endIndex = workPosA + countA;

      short pieceBNextHigh = workB[workPosB + countB - 2];
      int pieceBNextHighInt = ((int)pieceBNextHigh) & ShortMask;
      for (int offset = countA - countB; offset >= 0; --offset) {
        int wpoffset = workPosA + offset;
        int wpaNextHigh = ((int)workA[wpoffset + countB - 1]) & ShortMask;
        int wpaHigh = 0;
        if (!noShift || wpoffset + countB < endIndex) {
          wpaHigh = ((int)workA[wpoffset + countB]) & ShortMask;
        }
        int dividend = (wpaNextHigh + (wpaHigh << 16));
        int divnext = ((int)workA[wpoffset + countB - 2]) & ShortMask;
        int quorem0 = (dividend >> 31) == 0 ? (dividend / pieceBHighInt) :
          ((int)(((long)dividend & 0xffffffffL) / pieceBHighInt));
        int quorem1 = (dividend - (quorem0 * pieceBHighInt));
        // System.out.println("{0:X8}/{1:X4} = { 2:X8},{3:X4}",
        // dividend, pieceBHigh, quorem0, quorem1);
        long t = (((long)quorem1) << 16) | (divnext & 0xffffL);
        // NOTE: quorem0 won't be higher than (1<< 16)+1 as long as
        // pieceBHighInt is
        // normalized (see Burnikel & Ziegler 1998). Since the following
        // code block
        // corrects all cases where quorem0 is too high by 2, and all
        // remaining cases
        // will reduce quorem0 by 1 if it's at least (1<< 16), quorem0 will
        // be guaranteed to
        // have a bit length of 16 or less by the end of the code block.
        if ((quorem0 >> 16) != 0 ||
          ((quorem0 * pieceBNextHighInt) & 0xffffffffL) > t) {
          quorem1 += pieceBHighInt;
          --quorem0;
          if ((quorem1 >> 16) == 0) {
            t = (((long)quorem1) << 16) | (divnext & 0xffffL);
            if ((quorem0 >> 16) != 0 ||
              ((quorem0 * pieceBNextHighInt) & 0xffffffffL) > t) {
              --quorem0;
              if (rem == null && offset == 0) {
                // We can stop now and break; all cases where quorem0
                // is 2 too big will have been caught by now
                if (quot != null) {
                  quot[posQuot + offset] = ((short)quorem0);
                }
                break;
              }
            }
          }
        }
        int q1 = quorem0 & ShortMask;

        c = LinearMultiplySubtractMinuend1Bigger(
            workA,
            wpoffset,
            workA,
            wpoffset,
            q1,
            workB,
            workPosB,
            countB);
        if (c != 0) {
          // T(workA,workPosA,countA+1,"workA X");
          c = AddInternal(
              workA,
              wpoffset,
              workA,
              wpoffset,
              workB,
              workPosB,
              countB);
          c = IncrementWords(workA, wpoffset + countB, 1, (short)c);
          // T(workA,workPosA,countA+1,"workA "+c);
          --quorem0;
        }
        if (quot != null) {
          quot[posQuot + offset] = ((short)quorem0);
        }
      }
      if (rem != null) {
        if (sh != 0) {
          ShiftWordsRightByBits(workA, workPosA, countB + 1, sh);
        }
        System.arraycopy(workA, workPosA, rem, posRem, countB);
      }
    }

    /**
     * Divides this arbitrary-precision integer by a 32-bit signed integer and
     * returns a two-item array containing the result of the division and the
     * remainder, in that order. The result of the division is rounded down (the
     * fractional part is discarded). Except if the result of the division is 0, it
     * will be negative if this arbitrary-precision integer is positive and the
     * other 32-bit signed integer is negative, or vice versa, and will be positive
     * if both are positive or both are negative. The remainder is the number that
     * remains when the absolute value of this arbitrary-precision integer is
     * divided by the absolute value of the other 32-bit signed integer; the
     * remainder has the same sign (positive or negative) as this
     * arbitrary-precision integer.
     * @param intDivisor The number to divide by.
     * @return An array of two items: the first is the result of the division as an
     * arbitrary-precision integer, and the second is the remainder as an
     * arbitrary-precision integer. The result of division is the result of the
     * Divide method on the two operands, and the remainder is the result of the
     * Remainder method on the two operands.
     * @throws ArithmeticException The parameter {@code intDivisor} is 0.
     */
    public EInteger[] DivRem(int intDivisor) {
      return this.DivRem(EInteger.FromInt32(intDivisor));
    }

    /**
     * Adds this arbitrary-precision integer and a 64-bit signed integer and
     * returns the result.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The sum of the two numbers, that is, this arbitrary-precision
     * integer plus a 64-bit signed integer.
     */
    public EInteger Add(long longValue) {
      return this.Add(EInteger.FromInt64(longValue));
    }

    /**
     * Subtracts a 64-bit signed integer from this arbitrary-precision integer and
     * returns the result.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision integer minus a 64-bit signed integer.
     */
    public EInteger Subtract(long longValue) {
      return this.Subtract(EInteger.FromInt64(longValue));
    }

    /**
     * Multiplies this arbitrary-precision integer by a 64-bit signed integer and
     * returns the result.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * integer times a 64-bit signed integer.
     */
    public EInteger Multiply(long longValue) {
      return this.Multiply(EInteger.FromInt64(longValue));
    }

    /**
     * Divides this arbitrary-precision integer by a 64-bit signed integer and
     * returns the result. The result of the division is rounded down (the
     * fractional part is discarded). Except if the result of the division is 0, it
     * will be negative if this arbitrary-precision integer is positive and the
     * other 64-bit signed integer is negative, or vice versa, and will be positive
     * if both are positive or both are negative.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The result of dividing this arbitrary-precision integer by a 64-bit
     * signed integer. The result of the division is rounded down (the fractional
     * part is discarded). Except if the result of the division is 0, it will be
     * negative if this arbitrary-precision integer is positive and the other
     * 64-bit signed integer is negative, or vice versa, and will be positive if
     * both are positive or both are negative.
     */
    public EInteger Divide(long longValue) {
      return this.Divide(EInteger.FromInt64(longValue));
    }

    /**
     * Returns the remainder that would result when this arbitrary-precision
     * integer is divided by a 64-bit signed integer. The remainder is the number
     * that remains when the absolute value of this arbitrary-precision integer is
     * divided by the absolute value of the other 64-bit signed integer; the
     * remainder has the same sign (positive or negative) as this
     * arbitrary-precision integer.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return The remainder that would result when this arbitrary-precision
     * integer is divided by a 64-bit signed integer.
     */
    public EInteger Remainder(long longValue) {
      return this.Remainder(EInteger.FromInt64(longValue));
    }

    /**
     * Compares an arbitrary-precision integer with this instance.
     * @param longValue The parameter {@code longValue} is a 64-bit signed integer.
     * @return Zero if the values are equal; a negative number if this instance is
     * less, or a positive number if this instance is greater.
     */
    public int compareTo(long longValue) {
      return this.compareTo(EInteger.FromInt64(longValue));
    }

    /**
     * Divides this arbitrary-precision integer by a 64-bit signed integer and
     * returns a two-item array containing the result of the division and the
     * remainder, in that order. The result of the division is rounded down (the
     * fractional part is discarded). Except if the result of the division is 0, it
     * will be negative if this arbitrary-precision integer is positive and the
     * other 64-bit signed integer is negative, or vice versa, and will be positive
     * if both are positive or both are negative. The remainder is the number that
     * remains when the absolute value of this arbitrary-precision integer is
     * divided by the absolute value of the other 64-bit signed integer; the
     * remainder has the same sign (positive or negative) as this
     * arbitrary-precision integer.
     * @param intDivisor The parameter {@code intDivisor} is a 64-bit signed
     * integer.
     * @return An array of two items: the first is the result of the division as an
     * arbitrary-precision integer, and the second is the remainder as an
     * arbitrary-precision integer. The result of division is the result of the
     * Divide method on the two operands, and the remainder is the result of the
     * Remainder method on the two operands.
     */
    public EInteger[] DivRem(long intDivisor) {
      return this.DivRem(EInteger.FromInt64(intDivisor));
    }

    /**
     * Divides this arbitrary-precision integer by another arbitrary-precision
     * integer and returns a two-item array containing the result of the division
     * and the remainder, in that order. The result of the division is rounded down
     * (the fractional part is discarded). Except if the result of the division is
     * 0, it will be negative if this arbitrary-precision integer is positive and
     * the other arbitrary-precision integer is negative, or vice versa, and will
     * be positive if both are positive or both are negative. The remainder is the
     * number that remains when the absolute value of this arbitrary-precision
     * integer is divided by the absolute value of the other arbitrary-precision
     * integer; the remainder has the same sign (positive or negative) as this
     * arbitrary-precision integer.
     * @param divisor The number to divide by.
     * @return An array of two items: the first is the result of the division as an
     * arbitrary-precision integer, and the second is the remainder as an
     * arbitrary-precision integer. The result of division is the result of the
     * Divide method on the two operands, and the remainder is the result of the
     * Remainder method on the two operands.
     * @throws ArithmeticException The parameter {@code divisor} is 0.
     * @throws NullPointerException The parameter {@code divisor} is null.
     */
    public EInteger[] DivRem(EInteger divisor) {
      if (divisor == null) {
        throw new NullPointerException("divisor");
      }
      int words1Size = this.wordCount;
      int words2Size = divisor.wordCount;
      if (words2Size == 0) {
        // Divisor is 0
        throw new ArithmeticException();
      }

      if (words1Size < words2Size) {
        // Dividend is less than divisor (includes case
        // where dividend is 0)
        return new EInteger[] { EInteger.FromInt32(0), this };
      }
      if (words2Size == 1) {
        // divisor is small, use a fast path
        short[] quotient = new short[this.wordCount];
        int smallRemainder;
        switch (divisor.words[0]) {
          case 2:
            smallRemainder = (int)FastDivideAndRemainderTwo(
              quotient,
              0,
              this.words,
              0,
              words1Size);
            break;
          case 10:
            smallRemainder = (int)FastDivideAndRemainderTen(
              quotient,
              0,
              this.words,
              0,
              words1Size);
            break;
          default:
            // System.out.println("smalldiv=" + (divisor.words[0]));
            smallRemainder = ((int)FastDivideAndRemainder(
              quotient,
              0,
              this.words,
              0,
              words1Size,
              divisor.words[0])) & ShortMask;
            break;
        }
        int count = this.wordCount;
        while (count != 0 &&
          quotient[count - 1] == 0) {
          --count;
        }
        if (count == 0) {
          return new EInteger[] { EInteger.FromInt32(0), this };
        }
        quotient = ShortenArray(quotient, count);
        EInteger bigquo = new EInteger(
          count,
          quotient,
          this.negative ^ divisor.negative);
        if (this.negative) {
          smallRemainder = -smallRemainder;
        }
        return new EInteger[] { bigquo, EInteger.FromInt64(smallRemainder) };
      }
      if (this.CanFitInInt32() && divisor.CanFitInInt32()) {
        long dividendSmall = this.ToInt32Checked();
        long divisorSmall = divisor.ToInt32Checked();
        if (dividendSmall != Integer.MIN_VALUE || divisorSmall != -1) {
          long quotientSmall = dividendSmall / divisorSmall;
          long remainderSmall = dividendSmall - (quotientSmall * divisorSmall);
          return new EInteger[] { EInteger.FromInt64(quotientSmall),
            EInteger.FromInt64(remainderSmall),
          };
        }
      } else if (this.CanFitInInt64() && divisor.CanFitInInt64()) {
        long dividendLong = this.ToInt64Checked();
        long divisorLong = divisor.ToInt64Checked();
        if (dividendLong != Long.MIN_VALUE || divisorLong != -1) {
          long quotientLong = dividendLong / divisorLong;
          long remainderLong = dividendLong - (quotientLong * divisorLong);
          return new EInteger[] { EInteger.FromInt64(quotientLong),
            EInteger.FromInt64(remainderLong),
          };
        }
        // System.out.println("int64divrem {0}/{1}"
        // , this.ToInt64Checked(), divisor.ToInt64Checked());
      }
      // --- General case
      short[] bigRemainderreg = new short[(int)words2Size];
      short[] quotientreg = new short[(int)(words1Size - words2Size + 1)];
      GeneralDivide(
        this.words,
        0,
        this.wordCount,
        divisor.words,
        0,
        divisor.wordCount,
        quotientreg,
        0,
        bigRemainderreg,
        0);
      int remCount = CountWords(bigRemainderreg);
      int quoCount = CountWords(quotientreg);
      bigRemainderreg = ShortenArray(bigRemainderreg, remCount);
      quotientreg = ShortenArray(quotientreg, quoCount);
      EInteger bigrem = (remCount == 0) ? EInteger.FromInt32(0) : new
        EInteger(remCount, bigRemainderreg, this.negative);
      EInteger bigquo2 = (quoCount == 0) ? EInteger.FromInt32(0) : new
        EInteger(quoCount, quotientreg, this.negative ^ divisor.negative);
      return new EInteger[] { bigquo2, bigrem };
    }

    /**
     * Determines whether this object and another object are equal and have the
     * same type.
     * @param obj The parameter {@code obj} is an arbitrary object.
     * @return {@code true} if this object and another object are equal; otherwise,
     * {@code false}.
     */
    @Override public boolean equals(Object obj) {
      EInteger other = ((obj instanceof EInteger) ? (EInteger)obj : null);
      if (other == null) {
        return false;
      }
      if (this.wordCount == other.wordCount) {
        if (this.negative != other.negative) {
          return false;
        }
        for (int i = 0; i < this.wordCount; ++i) {
          if (this.words[i] != other.words[i]) {
            return false;
          }
        }
        return true;
      }
      return false;
    }

    private static EInteger LeftShiftBigIntVar(EInteger ei,
      EInteger bigShift) {
      if (ei.isZero()) {
        return ei;
      }
      while (bigShift.signum() > 0) {
        int shift = 1000000;
        if (bigShift.compareTo(EInteger.FromInt64(1000000)) < 0) {
          shift = bigShift.ToInt32Checked();
        }
        ei = ei.ShiftLeft(shift);
        bigShift = bigShift.Subtract(EInteger.FromInt32(shift));
      }
      return ei;
    }

    private static long GcdLong(long u, long v) {
      // Adapted from Christian Stigen Larsen's
      // public domain GCD code

      int shl = 0;
      while (u != 0 && v != 0 && u != v) {
        boolean eu = (u & 1L) == 0;
        boolean ev = (v & 1L) == 0;
        if (eu && ev) {
          ++shl;
          u >>= 1;
          v >>= 1;
        } else if (eu && !ev) {
          u >>= 1;
        } else if (!eu && ev) {
          v >>= 1;
        } else if (u >= v) {
          u = (u - v) >> 1;
        } else {
          long tmp = u;
          u = (v - u) >> 1;
          v = tmp;
        }
      }
      return (u == 0) ? (v << shl) : (u << shl);
    }

    /**
     * Returns the greatest common divisor of this integer and the specified
     * integer. The greatest common divisor (GCD) is also known as the greatest
     * common factor (GCF). This method works even if either or both integers are
     * negative.
     * @param bigintSecond Another arbitrary-precision integer. Can be negative.
     * @return The greatest common divisor of this integer and the specified
     * integer.
     * @throws NullPointerException The parameter {@code bigintSecond} is null.
     * @throws ArithmeticException Attempted to divide by zero.
     * @throws IllegalArgumentException BigPower is negative.
     */
    public EInteger Gcd(EInteger bigintSecond) {
      if (bigintSecond == null) {
        throw new NullPointerException("bigintSecond");
      }
      if (this.isZero()) {
        return bigintSecond.Abs();
      }
      EInteger thisValue = this.Abs();
      if (bigintSecond.isZero()) {
        return thisValue;
      }
      bigintSecond = bigintSecond.Abs();
      if (bigintSecond.equals(EInteger.FromInt32(1)) ||
        thisValue.equals(bigintSecond)) {
        return bigintSecond;
      }
      if (thisValue.equals(EInteger.FromInt32(1))) {
        return thisValue;
      }
      if (Math.max(thisValue.wordCount, bigintSecond.wordCount) > 12) {
        // if (Math.max(thisValue.wordCount, bigintSecond.wordCount) > 250) {
        return SubquadraticGCD(thisValue, bigintSecond);
      } else {
        return BaseGcd(thisValue, bigintSecond);
      }
    }

    private static EInteger BaseGcd(EInteger thisValue, EInteger bigintSecond) {
      if (thisValue.CanFitInInt64() && bigintSecond.CanFitInInt64()) {
        long u = thisValue.ToInt64Unchecked();
        long v = bigintSecond.ToInt64Unchecked();
        return EInteger.FromInt64(GcdLong(u, v));
      } else {
        boolean bigger = thisValue.compareTo(bigintSecond) >= 0;
        if (!bigger) {
          EInteger ta = thisValue;
          thisValue = bigintSecond;
          bigintSecond = ta;
        }
        EInteger eia = thisValue;
        EInteger eib = bigintSecond;
        // System.out.println("wc="+eia.wordCount+"/"+eib.wordCount);
        while (eib.wordCount > 3) {
          // Lehmer's algorithm
          EInteger eiaa, eibb, eicc, eidd;
          EInteger eish = eia.GetUnsignedBitLengthAsEInteger();
          eish = eish.Subtract(48);
          EInteger eiee = eia.ShiftRight(eish);
          EInteger eiff = eib.ShiftRight(eish);
          eiaa = eidd = EInteger.FromInt32(1);
          eibb = eicc = EInteger.FromInt32(0);
          while (true) {
            EInteger eifc = eiff.Add(eicc);
            EInteger eifd = eiff.Add(eidd);
            if (eifc.isZero() || eifd.isZero()) {
              EInteger ta = eibb.isZero() ? eib :
                eia.Multiply(eiaa).Add(eib.Multiply(eibb));
              EInteger tb = eibb.isZero() ? eia.Remainder(eib) :
                eia.Multiply(eicc).Add(eib.Multiply(eidd));
              eia = ta;
              eib = tb;
              // System.out.println("z tawc="+eia.wordCount+"/"+eib.wordCount);
              break;
            }
            EInteger eiq = eiee.Add(eiaa).Divide(eifc);
            EInteger eiq2 = eiee.Add(eibb).Divide(eifd);
            if (!eiq.equals(eiq2)) {
              EInteger ta = eibb.isZero() ? eib :
                eia.Multiply(eiaa).Add(eib.Multiply(eibb));
              EInteger tb = eibb.isZero() ? eia.Remainder(eib) :
                eia.Multiply(eicc).Add(eib.Multiply(eidd));
              // System.out.println("eia/b="+eia.wordCount+"/"+eib.wordCount);
              // System.out.println("eiaa/bb="+eiaa.wordCount+"/"+eibb.wordCount);
              eia = ta;
              eib = tb;
              break;
            } else if (eiq.CanFitInInt32() &&
              eidd.CanFitInInt32() && eicc.CanFitInInt32()) {
              EInteger t = eiff;
              // System.out.println("eiq eiffccdd="+eiff.wordCount+"/"+
              // eicc.wordCount+"/"+eidd.wordCount);
              int eiqi = eiq.ToInt32Checked();
              eiff = eiee.Subtract(eiff.Multiply(eiqi));
              eiee = t;
              t = eicc;
              eicc = eiaa.Subtract((long)eicc.ToInt32Checked() * eiqi);
              eiaa = t;
              t = eidd;
              eidd = eibb.Subtract((long)eidd.ToInt32Checked() * eiqi);
              // System.out.println("->eiffccdd="+eiff.wordCount+"/"+
              // eicc.wordCount+"/"+eidd.wordCount);
              eibb = t;
            } else {
              EInteger t = eiff;
              // System.out.println("eiffccdd="+eiff.wordCount+"/"+
              // eicc.wordCount+"/"+eidd.wordCount);
              eiff = eiee.Subtract(eiff.Multiply(eiq));
              eiee = t;
              t = eicc;
              eicc = eiaa.Subtract(eicc.Multiply(eiq));
              eiaa = t;
              t = eidd;
              eidd = eibb.Subtract(eidd.Multiply(eiq));
              // System.out.println("->eiffccdd="+eiff.wordCount+"/"+
              // eicc.wordCount+"/"+eidd.wordCount);
              eibb = t;
            }
          }
        }
        if (eib.isZero()) {
          return eia;
        }
        while (!eib.isZero()) {
          if (eia.wordCount <= 3 && eib.wordCount <= 3) {
            return EInteger.FromInt64(
                GcdLong(eia.ToInt64Checked(), eib.ToInt64Checked()));
          }
          EInteger ta = eib;
          eib = eia.Remainder(eib);
          eia = ta;
        }
        return eia;
      }
    }

    private static EInteger MinBitLength(EInteger eia, EInteger eib) {
      return EInteger.Min(BL(eia), BL(eib));
    }

    private static EInteger MaxBitLength(EInteger eia, EInteger eib) {
      return EInteger.Max(BL(eia), BL(eib));
    }

    private static void SDivStep(EInteger[] eiam, EInteger eis) {
      // a, b, m.get(0) ... m.get(3)
      if (eiam[0].compareTo(eiam[1]) > 0) {
        // a > b
        EInteger eia = eiam[0];
        EInteger eib = eiam[1];
        EInteger[] divrem = eia.DivRem(eib);
        if (BL(divrem[1]).compareTo(eis) <= 0) {
          divrem[0] = divrem[0].Subtract(1);
          if (divrem[0].signum() < 0) {
            throw new IllegalStateException();
          }
          divrem[1] = divrem[1].Add(eib);
        }
        eiam[3] = eiam[3].Add(eiam[2].Multiply(divrem[0]));
        eiam[5] = eiam[5].Add(eiam[4].Multiply(divrem[0]));
        eiam[0] = divrem[1];
      } else {
        // a <= b
        EInteger eia = eiam[1];
        EInteger eib = eiam[0];
        EInteger[] divrem = eia.DivRem(eib);
        if (BL(divrem[1]).compareTo(eis) <= 0) {
          divrem[0] = divrem[0].Subtract(1);
          if (divrem[0].signum() < 0) {
            throw new IllegalStateException();
          }
          divrem[1] = divrem[1].Add(eib);
        }
        eiam[2] = eiam[2].Add(eiam[3].Multiply(divrem[0]));
        eiam[4] = eiam[4].Add(eiam[5].Multiply(divrem[0]));
        eiam[1] = divrem[1];
      }
    }

    private static void LSDivStep(long[] longam, long ls) {
      if (longam[0] < 0) {
        throw new IllegalArgumentException("longam[0] (" + longam[0] + ") is not" +
          "\u0020greater or equal to 0");
      }
      if (longam[1] < 0) {
        throw new IllegalArgumentException("longam[1] (" + longam[1] + ") is not" +
          "\u0020greater or equal to 0");
      }
      {
        // a, b, m.get(0) ... m.get(3)
        if (longam[0] > longam[1]) {
          // a > b
          long a = longam[0];
          long b = longam[1];
          long ddiv = a / b;
          long[] ldivrem = new long[] {
            ddiv, a - (ddiv * b),
          };
          if (LBL(ldivrem[1]) <= ls) {
            --ldivrem[0];
            if (ldivrem[0] < 0) {
              throw new IllegalStateException();
            }
            ldivrem[1] += b;
          }
          longam[3] += longam[2] * ldivrem[0];
          longam[5] += longam[4] * ldivrem[0];
          longam[0] = ldivrem[1];
        } else {
          // a <= b
          long a = longam[1];
          long b = longam[0];
          long ddiv = a / b;
          long[] ldivrem = new long[] {
            ddiv, a - (ddiv * b),
          };
          if (LBL(ldivrem[1]) <= ls) {
            --ldivrem[0];
            if (ldivrem[0] < 0) {
              throw new IllegalStateException();
            }
            ldivrem[1] += b;
          }
          longam[2] += longam[3] * ldivrem[0];
          longam[4] += longam[5] * ldivrem[0];
          longam[1] = ldivrem[1];
        }
      }
    }

    private static EInteger BL(EInteger eia) {
      if (eia.isZero()) {
        return EInteger.FromInt32(0);
      }
      EInteger ret = eia.GetUnsignedBitLengthAsEInteger();
      return ret;
    }

    private static int LBL(long mantlong) {
      return (mantlong == 0) ? 0 : NumberUtility.BitLength(Math.abs(mantlong));
    }

    private static long[] LHalfGCD(long longa, long longb) {
      if (longa == 0 || longb == 0) {
        // System.out.println("LHalfGCD failed");
        return new long[] { longa, longb, 1, 0, 0, 1 };
      }
      long olonga = longa;
      long olongb = longb;
      long[] ret = new long[6];
      // System.out.println("LHalfGCD " + longa + " " + longb);
      {
        int ln = Math.max(LBL(longa), LBL(longb));
        int lnmin = Math.min(LBL(longa), LBL(longb));
        int ls = (ln >> 1) + 1;
        if (lnmin <= ls) {
          // System.out.println("LHalfGCD failed: nmin<= s");
          return new long[] { longa, longb, 1, 0, 0, 1 };
        }
        if (lnmin > ((ln * 3) >> 2) + 2) {
          int p1 = ln >> 1;
          long nhalfmask = (1L << p1) - 1;
          long longah = longa >> p1;
          long longal = longa & nhalfmask;
          long longbh = longb >> p1;
          long longbl = longb & nhalfmask;
          long[] ret2 = LHalfGCD(longah, longbh);
          if (ret2 == null) {
            return null;
          }
          System.arraycopy(ret2, 0, ret, 0, 6);
          longa = (longal * ret2[5]) - (longbl * ret2[3]);
          longb = (longbl * ret2[2]) - (longal * ret2[4]);
          longa += ret2[0] << p1;
          longb += ret2[1] << p1;
          if (longa < 0 || longb < 0) {
            throw new IllegalStateException(
              "Internal error: longa=" + olonga + " olongb=" +
              olongb);
          }
        } else {
          // Set M to identity
          ret[2] = 1;
          ret[3] = 0;
          ret[4] = 0;
          ret[5] = 1;
        }
        ret[0] = longa;
        ret[1] = longb;
        while (Math.max(LBL(ret[0]), LBL(ret[1])) > ((ln * 3) >> 2) + 1 &&
          LBL(ret[0] - ret[1]) > ls) {
          if (ret[0] < 0 || ret[1] < 0) {
            throw new IllegalStateException(
              "Internal error: longa=" + olonga + " olongb=" +
              olongb);
          }
          LSDivStep(ret, ls);
        }
        longa = ret[0];
        longb = ret[1];
        if (Math.min(LBL(longa), LBL(longb)) > ls + 2) {
          ln = Math.max(LBL(longa), LBL(longb));
          int p1 = ((ls * 2) - ln) + 1;
          long nhalfmask = (1L << p1) - 1;
          long longah = longa >> p1;
          long longal = longa & nhalfmask;
          long longbh = longb >> p1;
          long longbl = longb & nhalfmask;
          long[] ret2 = LHalfGCD(longah, longbh);
          if (ret2 == null) {
            return null;
          }
          longa = (longal * ret2[5]) - (longbl * ret2[3]);
          longb = (longbl * ret2[2]) - (longal * ret2[4]);
          longa += ret2[0] << p1;
          longb += ret2[1] << p1;
          if (longa < 0 || longb < 0) {
            throw new IllegalStateException("Internal error");
          }
          long ma, mb, mc, md;
          ma = (ret[2] * ret2[2]) + (ret[3] * ret2[4]);
          mb = (ret[2] * ret2[3]) + (ret[3] * ret2[5]);
          mc = (ret[4] * ret2[2]) + (ret[5] * ret2[4]);
          md = (ret[4] * ret2[3]) + (ret[5] * ret2[5]);
          ret[2] = ma;
          ret[3] = mb;
          ret[4] = mc;
          ret[5] = md;
        }
        ret[0] = longa;
        ret[1] = longb;
        while (LBL(ret[0] - ret[1]) > ls) {
          if (ret[0] < 0 || ret[1] < 0) {
            throw new IllegalStateException("Internal error");
          }
          LSDivStep(ret, ls);
        }
        if (ret[0] < 0 || ret[1] < 0) {
          throw new IllegalStateException("Internal error");
        }
      }
      return ret;
    }

    private static long[] SlowSgcd(long longa, long longb) {
      long[] ret = new long[] { longa, longb, 1, 0, 0, 1 };
      int ls = Math.max(LBL(longa), LBL(longb));
      ls = (ls >> 1) + 1;
      while (LBL(ret[0] - ret[1]) > ls) {
        LSDivStep(ret, ls);
      }
      return ret;
    }

    private static EInteger[] SlowSgcd(EInteger eia, EInteger eib) {
      EInteger[] ret = new EInteger[] {
        eia, eib, EInteger.FromInt32(1), EInteger.FromInt32(0),
        EInteger.FromInt32(0), EInteger.FromInt32(1),
      };
      EInteger eis = EInteger.Max(BL(eia), BL(eib));
      eis = eis.ShiftRight(1).Add(1);
      while (BL(ret[0].Subtract(ret[1])).compareTo(eis) > 0) {
        SDivStep(ret, eis);
      }
      return ret;
    }

    // Implements Niels Moeller's Half-GCD algorithm from 2008
    private static EInteger[] HalfGCD(EInteger eia, EInteger eib) {
      if (eia.signum() < 0) {
        throw new IllegalStateException("doesn't satisfy !eia.isNegative()");
      }
      if (eib.signum() < 0) {
        throw new IllegalStateException("doesn't satisfy !eib.isNegative()");
      }
      EInteger oeia = eia;
      EInteger oeib = eib;
      if (eia.isZero() || eib.isZero()) {
        // System.out.println("HalfGCD failed");
        return new EInteger[] {
          eia, eib, EInteger.FromInt32(1), EInteger.FromInt32(0),
          EInteger.FromInt32(0), EInteger.FromInt32(1),
        };
      }
      EInteger[] ret = new EInteger[6];
      // System.out.println("HalfGCD " + eia + " " + eib);
      if (eia.CanFitInInt64() && eib.CanFitInInt64()) {
        long[] lret = LHalfGCD(eia.ToInt64Checked(), eib.ToInt64Checked());
        if (lret == null) {
          return null;
        }
        for (int i = 0; i < 6; ++i) {
          ret[i] = EInteger.FromInt64(lret[i]);
        }
        return ret;
      }
      EInteger ein = MaxBitLength(eia, eib);
      EInteger einmin = MinBitLength(eia, eib);
      long ln = ein.CanFitInInt64() ? ein.ToInt64Checked() : -1;
      EInteger eis = ein.ShiftRight(1).Add(1);
      if (einmin.compareTo(eis) <= 0) {
        // System.out.println("HalfGCD failed: nmin<= s");
        return new EInteger[] {
          eia, eib, EInteger.FromInt32(1), EInteger.FromInt32(0),
          EInteger.FromInt32(0), EInteger.FromInt32(1),
        };
      }
      EInteger eiah, eial, eibh, eibl;
      if (einmin.compareTo(ein.Multiply(3).ShiftRight(2).Add(2)) > 0) {
        EInteger p1 = ein.ShiftRight(1);

        eiah = eia.ShiftRight(p1);
        eial = eia.LowBits(p1);
        eibh = eib.ShiftRight(p1);
        eibl = eib.LowBits(p1);
        EInteger[] ret2 = HalfGCD(eiah, eibh);
        if (ret2 == null) {
          return null;
        }
        System.arraycopy(ret2, 0, ret, 0, 6);
        eia = eial.Multiply(ret2[5]).Subtract(eibl.Multiply(ret2[3]));
        eib = eibl.Multiply(ret2[2]).Subtract(eial.Multiply(ret2[4]));
        eia = eia.Add(ret2[0].ShiftLeft(p1));
        eib = eib.Add(ret2[1].ShiftLeft(p1));
        if (eia.signum() < 0 || eib.signum() < 0) {
          throw new IllegalStateException(
            "Internal error: oeia=" + oeia + " oeib=" +
            oeib + " eiah=" + eiah + " eibh=" + eibh);
        }
      } else {
        // Set M to identity
        ret[2] = ret[5] = EInteger.FromInt32(1);
        ret[3] = ret[4] = EInteger.FromInt32(0);
      }
      ret[0] = eia;
      ret[1] = eib;
      /*
      for (int k = 0; k < 6; ++k) {
        System.out.println("ret_afterloop1_"+ k + "=" +
           ret[k].ToRadixString(16));
      }
      */ while (MaxBitLength(ret[0], ret[1]).compareTo(
        ein.Multiply(3).ShiftRight(2).Add(1)) > 0 &&
        BL(ret[0].Subtract(ret[1])).compareTo(eis) > 0) {
        if (ret[0].signum() < 0 || ret[1].signum() < 0) {
          throw new IllegalStateException(
            "Internal error: eia=" + oeia + " oeib=" +
            oeib);
        }
        SDivStep(ret, eis);
        // for (int k = 0; k < 6; ++k) {
        // System.out.println("ret_loop2_"+ k + "=" + ret[k].ToRadixString(16));
        // }
      }
      // for (int k = 0; k < 6; ++k) {
      // System.out.println("ret_afterloop2_"+ k + "=" +
      // ret[k].ToRadixString(16));
      // }
      eia = ret[0];
      eib = ret[1];
      if (MinBitLength(eia, eib).compareTo(eis.Add(2)) > 0) {
        ein = MaxBitLength(eia, eib);
        EInteger p1 = eis.Add(eis).Subtract(ein).Add(1);
        eiah = eia.ShiftRight(p1);
        eial = eia.LowBits(p1);
        eibh = eib.ShiftRight(p1);
        eibl = eib.LowBits(p1);
        EInteger[] ret2 = HalfGCD(eiah, eibh);
        if (ret2 == null) {
          return null;
        }
        eia = eial.Multiply(ret2[5]).Subtract(eibl.Multiply(ret2[3]));
        eib = eibl.Multiply(ret2[2]).Subtract(eial.Multiply(ret2[4]));
        eia = eia.Add(ret2[0].ShiftLeft(p1));
        eib = eib.Add(ret2[1].ShiftLeft(p1));
        if (eia.signum() < 0 || eib.signum() < 0) {
          throw new IllegalStateException("Internal error");
        }
        EInteger ma, mb, mc, md;
        // System.out.println("m "+Arrays.toString(new
        // EInteger[] { ret[2], ret[3], ret[4], ret[5]}));
        // System.out.println("m' "+Arrays.toString(new
        // EInteger[] { ret2[2], ret2[3], ret2[4], ret2[5]}));
        ma = ret[2].Multiply(ret2[2]).Add(ret[3].Multiply(ret2[4]));
        mb = ret[2].Multiply(ret2[3]).Add(ret[3].Multiply(ret2[5]));
        mc = ret[4].Multiply(ret2[2]).Add(ret[5].Multiply(ret2[4]));
        md = ret[4].Multiply(ret2[3]).Add(ret[5].Multiply(ret2[5]));
        ret[2] = ma;
        ret[3] = mb;
        ret[4] = mc;
        ret[5] = md;
        // System.out.println("newm "+Arrays.toString(ret));
      }
      ret[0] = eia;
      ret[1] = eib;
      // for (int k = 0; k < 6; ++k) {
      // System.out.println("ret_afterloop3.set("+k+"," +
      // ret[k].ToRadixString(16)));
      // }
      while (BL(ret[0].Subtract(ret[1])).compareTo(eis) > 0) {
        if (ret[0].signum() < 0 || ret[1].signum() < 0) {
          throw new IllegalStateException("Internal error");
        }
        SDivStep(ret, eis);
        // System.out.println("[sdiv2]ret="+Arrays.toString(ret));
      }

      return ret;
    }

    private static EInteger SubquadraticGCD(EInteger eia, EInteger eib) {
      EInteger ein = MaxBitLength(eia, eib);
      EInteger[] ret = new EInteger[] { eia, eib };
      while (true) {
        if (ein.compareTo(48) < 0) {
          break;
        }
        // System.out.println("eia=" + ret[0].ToRadixString(16));
        // System.out.println("eib=" + ret[1].ToRadixString(16));
        EInteger nhalf = ein.ShiftRight(1);
        EInteger eiah = ret[0].ShiftRight(nhalf);
        EInteger eial = ret[0].LowBits(nhalf);
        EInteger eibh = ret[1].ShiftRight(nhalf);
        EInteger eibl = ret[1].LowBits(nhalf);
        // System.out.println("eiah->" + eiah.ToRadixString(16));
        // System.out.println("eibh->" + eibh.ToRadixString(16));
        EInteger[] hgcd = HalfGCD(eiah, eibh);
        if (hgcd == null) {
          // System.out.println("hgcd failed");
          break;
        }
        eia = eial.Multiply(hgcd[5]).Subtract(eibl.Multiply(hgcd[3]));
        eib = eibl.Multiply(hgcd[2]).Subtract(eial.Multiply(hgcd[4]));
        eia = eia.Add(hgcd[0].ShiftLeft(nhalf));
        eib = eib.Add(hgcd[1].ShiftLeft(nhalf));
        // System.out.println("eia->" + eia.ToRadixString(16));
        // System.out.println("eib->" + eib.ToRadixString(16));
        if (eia.signum() < 0 || eib.signum() < 0) {
          /*StringBuilder sb = new StringBuilder();
          sb.append("eia=" + ret[0] + "\n");
          sb.append("eib=" + ret[1] + "\n");
          for (int k = 0; k < 6; ++k) {
            sb.append("hgcd_" + k + "=" + hgcd[k].ToRadixString(16));
            sb.append("\n");
          }*/
          throw new IllegalStateException("Internal error\n");
        }
        if (ret[0].equals(eia) && ret[1].equals(eib)) {
          // Didn't change
          break;
        }
        ein = MaxBitLength(eia, eib);
        ret[0] = eia;
        ret[1] = eib;
      }
      // System.out.println("eia final "+eia.ToRadixString(16));
      // System.out.println("eib final "+eib.ToRadixString(16));
      return BaseGcd(ret[0], ret[1]);
    }

    /**
     * Returns the number of decimal digits used by this integer, in the form of an
     * arbitrary-precision integer.
     * @return The number of digits in the decimal form of this integer. Returns 1
     * if this number is 0.
     */
    public EInteger GetDigitCountAsEInteger() {
      // NOTE: All digit counts can currently fit in Int64, so just
      // use GetDigitCountAsInt64 for the time being
      return EInteger.FromInt64(this.GetDigitCountAsInt64());
    }

    /**
     * Returns the number of decimal digits used by this integer.
     * @return The number of digits in the decimal form of this integer. Returns 1
     * if this number is 0.
     * @throws ArithmeticException The return value would exceed the range of a
     * 32-bit signed integer.
     * @deprecated This method may overflow. Use GetDigitCountAsEInteger instead.
 */
@Deprecated
    public int GetDigitCount() {
      long dc = this.GetDigitCountAsInt64();
      if (dc < Integer.MIN_VALUE || dc > Integer.MAX_VALUE) {
        throw new ArithmeticException();
      }
      return (int)dc;
    }

    /**
     * Returns the number of decimal digits used by this integer, in the form of a
     * 64-bit signed integer.
     * @return The number of digits in the decimal form of this integer. Returns 1
     * if this number is 0. Returns 2^63 - 1({@code Long.MAX_VALUE} in.NET or
     * {@code Long.MAX_VALUE} in Java) if the number of decimal digits is 2^63 - 1
     * or greater. (Use {@code GetDigitCountAsEInteger} instead if the application
     * relies on the exact number of decimal digits.).
     */
    public long GetDigitCountAsInt64() {
      // NOTE: Currently can't be 2^63-1 or greater, due to int32 word counts
      EInteger ei = this;
      long retval;
      if (ei.isZero()) {
        return 1;
      }
      retval = 0L;
      while (true) {
        if (ei.CanFitInInt64()) {
          long value = ei.ToInt64Checked();
          if (value == 0) {
            // Treat zero after division as having no digits
            break;
          }
          if (value == Long.MIN_VALUE) {
            retval += 19;
            break;
          }
          if (value < 0) {
            value = -value;
          }
          if (value >= 1000000000L) {
            retval += (value >= 1000000000000000000L) ? 19 : ((value >=
              100000000000000000L) ? 18 : ((value >= 10000000000000000L) ?
                17 : ((value >= 1000000000000000L) ? 16 :
              ((value >= 100000000000000L) ? 15 : ((value
              >= 10000000000000L) ?
              14 : ((value >= 1000000000000L) ? 13 : ((value
              >= 100000000000L) ? 12 : ((value >= 10000000000L) ?
                11 : ((value >= 1000000000L) ? 10 : 9)))))))));
          } else {
            int v2 = (int)value;
            retval += (v2 >= 100000000) ? 9 : ((v2 >= 10000000) ? 8 : ((v2 >=
              1000000) ? 7 : ((v2 >= 100000) ? 6 : ((v2
              >= 10000) ? 5 : ((v2 >= 1000) ? 4 : ((v2 >= 100) ?
              3 : ((v2 >= 10) ? 2 : 1)))))));
          }
          break;
        }
        // NOTE: Bitlength accurate for wordCount<1000000 here, only as
        // an approximation
        int bitlen = (ei.wordCount < 1000000) ?
          (int)ei.GetUnsignedBitLengthAsInt64() :
          Integer.MAX_VALUE;
        int maxDigits = 0;
        int minDigits = 0;
        if (bitlen <= 2135) {
          // (x*631305) >> 21 is an approximation
          // to trunc(x*log10(2)) that is correct up
          // to x = 2135; the multiplication would require
          // up to 31 bits in all cases up to 2135
          // (cases up to 63 are already handled above)
          minDigits = 1 + (((bitlen - 1) * 631305) >> 21);
          maxDigits = 1 + ((bitlen * 631305) >> 21);
          if (minDigits == maxDigits) {
            // Number of digits is the same for
            // all numbers with this bit length
            retval += minDigits;
            break;
          }
        } else if (bitlen <= 6432162) {
          // Much more accurate approximation
          // Approximation of ln(2)/ln(10)
          minDigits = 1 + (int)(((long)(bitlen - 1) * 661971961083L) >> 41);
          maxDigits = 1 + (int)(((long)bitlen * 661971961083L) >> 41);
          if (minDigits == maxDigits) {
            // Number of digits is the same for
            // all numbers with this bit length
            retval += minDigits;
            break;
          }
        }
        if (ei.wordCount >= 100) {
          long digits = ei.wordCount * 3;
          EInteger pow = NumberUtility.FindPowerOfTen(digits);
          EInteger div = ei.Divide(pow);
          retval += digits;
          ei = div;
          continue;
        }
        if (bitlen <= 2135) {
          retval += ei.Abs().compareTo(NumberUtility.FindPowerOfTen(
            minDigits)) >= 0 ? maxDigits : minDigits;
          break;
        } else if (bitlen < 50000) {
          retval += ei.Abs().compareTo(NumberUtility.FindPowerOfTen(
            minDigits + 1)) >= 0 ? maxDigits + 1 : minDigits + 1;
          break;
        }
        short[] tempReg = null;
        int currentCount = ei.wordCount;
        boolean done = false;
        while (!done && currentCount != 0) {
          if (currentCount == 1 || (currentCount == 2 && tempReg[1] == 0)) {
            int rest = ((int)tempReg[0]) & ShortMask;
            if (rest >= 10000) {
              retval += 5;
            } else if (rest >= 1000) {
              retval += 4;
            } else if (rest >= 100) {
              retval += 3;
            } else if (rest >= 10) {
              retval += 2;
            } else {
              ++retval;
            }
            break;
          }
          if (currentCount == 2 && tempReg[1] > 0 && tempReg[1] <= 0x7fff) {
            int rest = ((int)tempReg[0]) & ShortMask;
            rest |= (((int)tempReg[1]) & ShortMask) << 16;
            if (rest >= 1000000000) {
              retval += 10;
            } else if (rest >= 100000000) {
              retval += 9;
            } else if (rest >= 10000000) {
              retval += 8;
            } else if (rest >= 1000000) {
              retval += 7;
            } else if (rest >= 100000) {
              retval += 6;
            } else if (rest >= 10000) {
              retval += 5;
            } else if (rest >= 1000) {
              retval += 4;
            } else if (rest >= 100) {
              retval += 3;
            } else if (rest >= 10) {
              retval += 2;
            } else {
              ++retval;
            }
            break;
          } else {
            int wci = currentCount;
            short remainderShort = 0;
            int quo, rem;
            boolean firstdigit = false;
            short[] dividend = (tempReg == null) ? (ei.words) : tempReg;
            // Divide by 10000
            while (!done && (wci--) > 0) {
              int curValue = ((int)dividend[wci]) & ShortMask;
              int currentDividend = ((int)(curValue |
                ((int)remainderShort << 16)));
              quo = currentDividend / 10000;
              if (!firstdigit && quo != 0) {
                firstdigit = true;
                // Since we are dividing from left to right, the first
                // nonzero result is the first part of the
                // new quotient
                // NOTE: Bitlength accurate for wci<1000000 here, only as
                // an approximation
                bitlen = (wci < 1000000) ? GetUnsignedBitLengthEx(
                  quo,
                  wci + 1) :
                  Integer.MAX_VALUE;
                if (bitlen <= 2135) {
                  // (x*631305) >> 21 is an approximation
                  // to trunc(x*log10(2)) that is correct up
                  // to x = 2135; the multiplication would require
                  // up to 31 bits in all cases up to 2135
                  // (cases up to 64 are already handled above)
                  minDigits = 1 + (((bitlen - 1) * 631305) >> 21);
                  maxDigits = 1 + ((bitlen * 631305) >> 21);
                  if (minDigits == maxDigits) {
                    // Number of digits is the same for
                    // all numbers with this bit length
                    // NOTE: The 4 is the number of digits just
                    // taken out of the number, and "i" is the
                    // number of previously known digits
                    retval += minDigits + 4;
                    done = true;
                    break;
                  }
                  if (minDigits > 1) {
                    int maxDigitEstimate = maxDigits + 4;
                    int minDigitEstimate = minDigits + 4;
                    retval += ei.Abs().compareTo(NumberUtility.FindPowerOfTen(
                      minDigitEstimate)) >= 0 ? retval + maxDigitEstimate :
                      retval + minDigitEstimate;
                    done = true;
                    break;
                  }
                } else if (bitlen <= 6432162) {
                  // Much more accurate approximation
                  // Approximation of ln(2)/ln(10)
                  minDigits = 1 + (int)(((long)(bitlen - 1) * 661971961083L) >>
                    41);
                  maxDigits = 1 + (int)(((long)bitlen * 661971961083L) >> 41);
                  if (minDigits == maxDigits) {
                    // Number of digits is the same for
                    // all numbers with this bit length
                    retval += minDigits + 4;
                    done = true;
                    break;
                  }
                }
              }
              if (tempReg == null) {
                if (quo != 0) {
                  tempReg = new short[ei.wordCount];
                  System.arraycopy(ei.words, 0, tempReg, 0, tempReg.length);
                  // Use the calculated word count during division;
                  // zeros that may have occurred in division
                  // are not incorporated in the tempReg
                  currentCount = wci + 1;
                  tempReg[wci] = ((short)quo);
                }
              } else {
                tempReg[wci] = ((short)quo);
              }
              rem = currentDividend - (10000 * quo);
              remainderShort = ((short)rem);
            }
            // Recalculate word count
            while (currentCount != 0 && tempReg[currentCount - 1] == 0) {
              --currentCount;
            }
            retval += 4;
          }
        }
      }
      return retval;
    }

    /**
     * Returns the hash code for this instance. No application or process IDs are
     * used in the hash code calculation.
     * @return A 32-bit signed integer.
     */
    @Override public int hashCode() {
      int hashCodeValue = 0;
      {
        hashCodeValue += 1000000007 * this.signum();
        if (this.words != null) {
          for (int i = 0; i < this.wordCount; ++i) {
            hashCodeValue += 1000000013 * this.words[i];
          }
        }
      }
      return hashCodeValue;
    }

    /**
     * Gets the bit position of the lowest set bit in this number's absolute value.
     * (This will also be the position of the lowest set bit in the number's
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}).).
     * @return The bit position of the lowest bit set in the number's absolute
     * value, starting at 0. Returns -1 if this value is 0.
     * @deprecated This method may overflow. Use GetLowBitAsEInteger instead.
 */
@Deprecated
    public int GetLowBit() {
      return this.GetLowBitAsEInteger().ToInt32Checked();
    }

    /**
     * Gets the bit position of the lowest set bit in this number's absolute value,
     * in the form of a 64-bit signed integer. (This will also be the position of
     * the lowest set bit in the number's two's-complement form (see {@link
     * com.upokecenter.numbers.EDecimal "Forms of numbers"}).).
     * @return The bit position of the lowest bit set in the number's absolute
     * value, starting at 0. Returns -1 if this value is 0 or odd. Returns 2^63 - 1
     * ({@code Long.MAX_VALUE} in.NET or {@code Long.MAX_VALUE} in Java) if this
     * number is other than zero but the lowest set bit is at 2^63 - 1 or greater.
     * (Use {@code GetLowBitAsEInteger} instead if the application relies on the
     * exact value of the lowest set bit position.).
     */
    public long GetLowBitAsInt64() {
      // NOTE: Currently can't be 2^63-1 or greater, due to int32 word counts
      long retSetBitLong = 0;
      for (int i = 0; i < this.wordCount; ++i) {
        int c = ((int)this.words[i]) & ShortMask;
        if (c == 0) {
          retSetBitLong += 16;
        } else {
          int rsb = (((c << 15) & ShortMask) != 0) ? 0 : ((((c <<
            14) & ShortMask) != 0) ? 1 : ((((c <<
            13) & ShortMask) != 0) ? 2 : ((((c <<
            12) & ShortMask) != 0) ? 3 : ((((c << 11) &
                  0xffff) != 0) ? 4 : ((((c << 10) & ShortMask) != 0) ?
                5 : ((((c << 9) & ShortMask) != 0) ? 6 : ((((c <<
            8) & ShortMask) != 0) ? 7 : ((((c << 7) &
                  ShortMask) != 0) ? 8 : ((((c << 6) & ShortMask) != 0) ? 9 :
                ((((c << 5) & ShortMask) != 0) ? 10 : ((((c <<
            4) & ShortMask) != 0) ? 11 : ((((c << 3) &
                  0xffff) != 0) ? 12 : ((((c << 2) &
            0xffff) != 0) ? 13 : ((((c << 1) &
            ShortMask) != 0) ? 14 : 15))))))))))))));
          retSetBitLong += rsb;
          return retSetBitLong;
        }
      }
      return -1;
    }

    /**
     * Gets the bit position of the lowest set bit in this number's absolute value,
     * in the form of an arbitrary-precision integer. (This will also be the
     * position of the lowest set bit in the number's two's-complement form (see
     * {@link com.upokecenter.numbers.EDecimal "Forms of numbers"}).).
     * @return The bit position of the lowest bit set in the number's absolute
     * value, starting at 0. Returns -1 if this value is 0 or odd.
     */
    public EInteger GetLowBitAsEInteger() {
      return EInteger.FromInt64(this.GetLowBitAsInt64());
    }

    /**
     * Returns whether a bit is set in the two's-complement form (see {@link
     * com.upokecenter.numbers.EDecimal "Forms of numbers"}) of this object's
     * value.
     * @param bigIndex The index, starting at zero, of the bit to test, where 0 is
     * the least significant bit, 1 is the next least significant bit, and so on.
     * @return {@code true} if the specified bit is set in the two' s-complement
     * form (see {@code com.upokecenter.numbers.EDecimal}) of this object's value;
     * otherwise, {@code false}.
     * @throws NullPointerException The parameter {@code bigIndex} is null.
     */
    public boolean GetSignedBit(EInteger bigIndex) {
      if (bigIndex == null) {
        throw new NullPointerException("bigIndex");
      }
      if (bigIndex.signum() < 0) {
        throw new IllegalArgumentException("bigIndex");
      }
      if (this.negative) {
        if (bigIndex.CanFitInInt32()) {
          return this.GetSignedBit(bigIndex.ToInt32Checked());
        }
        EInteger valueEWordPos = bigIndex.Divide(16);
        if (valueEWordPos.compareTo(this.words.length) >= 0) {
          return true;
        }
        long tcindex = 0;
        while (valueEWordPos.compareTo(EInteger.FromInt64(tcindex)) > 0 &&
          this.words[((int)tcindex)] == 0) {
          ++tcindex;
        }
        short tc;
        // NOTE: array indices are currently limited to Int32
        int wordpos = valueEWordPos.ToInt32Checked();
        {
          tc = this.words[wordpos];
          if (tcindex == wordpos) {
            --tc;
          }
          tc = (short)~tc;
        }
        int mod15 = bigIndex.Remainder(16).ToInt32Checked();
        return (boolean)(((tc >> mod15) & 1) != 0);
      } else {
        return this.GetUnsignedBit(bigIndex);
      }
    }

    /**
     * Returns whether a bit is set in the two's-complement form (see {@link
     * com.upokecenter.numbers.EDecimal "Forms of numbers"}) of this object's
     * value.
     * @param index The index, starting at 0, of the bit to test, where 0 is the
     * least significant bit, 1 is the next least significant bit, and so on.
     * @return {@code true} if the specified bit is set in the two' s-complement
     * form (see {@code com.upokecenter.numbers.EDecimal}) of this object's value;
     * otherwise, {@code false}.
     */
    public boolean GetSignedBit(int index) {
      if (index < 0) {
        throw new IllegalArgumentException("index");
      }
      if (this.wordCount == 0) {
        return false;
      }
      if (this.negative) {
        int tcindex = 0;
        int wordpos = index / 16;
        if (wordpos >= this.words.length) {
          return true;
        }
        while (tcindex < wordpos && this.words[tcindex] == 0) {
          ++tcindex;
        }
        short tc;
        {
          tc = this.words[wordpos];
          if (tcindex == wordpos) {
            --tc;
          }
          tc = (short)~tc;
        }
        return (boolean)(((tc >> (int)(index & 15)) & 1) != 0);
      }
      return this.GetUnsignedBit(index);
    }

    /**
     * Finds the minimum number of bits needed to represent this object's value,
     * except for its sign, and returns that number of bits as an
     * arbitrary-precision integer. If the value is negative, finds the number of
     * bits in the value equal to this object's absolute value minus 1. For
     * example, all integers in the interval [-(2^63), (2^63) - 1], which is the
     * same as the range of integers in Java's and.NET's {@code long} type, have a
     * signed bit length of 63 or less, and all other integers have a signed bit
     * length of greater than 63.
     * @return The number of bits in this object's value, except for its sign.
     * Returns 0 if this object's value is 0 or negative 1.
     */
    public EInteger GetSignedBitLengthAsEInteger() {
      // NOTE: Currently can't be 2^63-1 or greater, due to int32 word counts
      return EInteger.FromInt64(this.GetSignedBitLengthAsInt64());
    }

    /**
     * Finds the minimum number of bits needed to represent this object's value,
     * except for its sign, and returns that number of bits as a 64-bit signed
     * integer. If the value is negative, finds the number of bits in the value
     * equal to this object's absolute value minus 1. For example, all integers in
     * the interval [-(2^63), (2^63) - 1], which is the same as the range of
     * integers in Java's and.NET's {@code long} type, have a signed bit length of
     * 63 or less, and all other integers have a signed bit length of greater than
     * 63.
     * @return The number of bits in this object's value, except for its sign.
     * Returns 0 if this object's value is 0 or negative 1. If the return value
     * would be greater than 2^63 - 1 ({@code Long.MAX_VALUE} in.NET or {@code
     * Long.MAX_VALUE} in Java), returns 2^63 - 1 instead. (Use {@code
     * GetSignedBitLengthAsEInteger} instead of this method if the application
     * relies on the exact number of bits.).
     */
    public long GetSignedBitLengthAsInt64() {
      // NOTE: Currently can't be 2^63-1 or greater, due to int32 word counts
      int wc = this.wordCount;
      if (wc != 0) {
        if (this.negative) {
          // Two's complement operation
          EInteger eiabs = this.Abs();
          long eiabsbl = eiabs.GetSignedBitLengthAsInt64();
          if (eiabs.isPowerOfTwo()) {
            // Absolute value is a power of 2
            --eiabsbl;
          }
          return eiabsbl;
        }
        int numberValue = ((int)this.words[wc - 1]) & ShortMask;
        int wcextra = 0;
        if (numberValue != 0) {
          wcextra = 16;
          {
            if ((numberValue >> 8) == 0) {
              numberValue <<= 8;
              wcextra -= 8;
            }
            if ((numberValue >> 12) == 0) {
              numberValue <<= 4;
              wcextra -= 4;
            }
            if ((numberValue >> 14) == 0) {
              numberValue <<= 2;
              wcextra -= 2;
            }
            wcextra = ((numberValue >> 15) == 0) ? wcextra - 1 : wcextra;
          }
        }
        return (((long)wc - 1) * 16) + wcextra;
      }
      return 0;
    }

    /**
     * Finds the minimum number of bits needed to represent this object's value,
     * except for its sign. If the value is negative, finds the number of bits in
     * the value equal to this object's absolute value minus 1. For example, all
     * integers in the interval [-(2^63), (2^63) - 1], which is the same as the
     * range of integers in Java's and.NET's {@code long} type, have a signed bit
     * length of 63 or less, and all other integers have a signed bit length of
     * greater than 63.
     * @return The number of bits in this object's value, except for its sign.
     * Returns 0 if this object's value is 0 or negative 1.
     * @throws ArithmeticException The return value would exceed the range of a
     * 32-bit signed integer.
     * @deprecated This method may overflow. Use GetSignedBitLengthAsEInteger instead.
 */
@Deprecated
    public int GetSignedBitLength() {
      return this.GetSignedBitLengthAsEInteger().ToInt32Checked();
    }

    /**
     * Returns whether a bit is set in this number's absolute value.
     * @param bigIndex The index, starting at zero, of the bit to test, where 0 is
     * the least significant bit, 1 is the next least significant bit, and so on.
     * @return {@code true} if the specified bit is set in this number's absolute
     * value.
     * @throws NullPointerException The parameter {@code bigIndex} is null.
     */
    public boolean GetUnsignedBit(EInteger bigIndex) {
      if (bigIndex == null) {
        throw new NullPointerException("bigIndex");
      }
      if (bigIndex.signum() < 0) {
        throw new IllegalArgumentException("bigIndex(" + bigIndex +
          ") is less than 0");
      }
      if (bigIndex.CanFitInInt32()) {
        return this.GetUnsignedBit(bigIndex.ToInt32Checked());
      }
      if (bigIndex.Divide(16).compareTo(this.words.length) < 0) {
        return false;
      }
      int index = bigIndex.ShiftRight(4).ToInt32Checked();
      int indexmod = bigIndex.Remainder(16).ToInt32Checked();
      return (boolean)(((this.words[index] >> (int)indexmod) & 1) != 0);
    }

    /**
     * Returns whether a bit is set in this number's absolute value.
     * @param index The index, starting at 0, of the bit to test, where 0 is the
     * least significant bit, 1 is the next least significant bit, and so on.
     * @return {@code true} if the specified bit is set in this number's absolute
     * value.
     */
    public boolean GetUnsignedBit(int index) {
      if (index < 0) {
        throw new IllegalArgumentException("index(" + index + ") is less than 0");
      }
      return ((index >> 4) < this.words.length) &&
        ((boolean)(((this.words[index >> 4] >> (int)(index & 15)) & 1) != 0));
    }

    /**
     * Finds the minimum number of bits needed to represent this number's absolute
     * value, and returns that number of bits as an arbitrary-precision integer.
     * For example, all integers in the interval [-((2^63) - 1), (2^63) - 1] have
     * an unsigned bit length of 63 or less, and all other integers have an
     * unsigned bit length of greater than 63. This interval is not the same as the
     * range of integers in Java's and.NET's {@code long} type.
     * @return The number of bits in this object's absolute value. Returns 0 if
     * this object's value is 0, and returns 1 if the value is negative 1.
     */
    public EInteger GetUnsignedBitLengthAsEInteger() {
      // NOTE: Currently can't be 2^63-1 or greater, due to int32 word counts
      return EInteger.FromInt64(this.GetUnsignedBitLengthAsInt64());
    }

    /**
     * Finds the minimum number of bits needed to represent this number's absolute
     * value, and returns that number of bits as a 64-bit signed integer. For
     * example, all integers in the interval [-((2^63) - 1), (2^63) - 1] have an
     * unsigned bit length of 63 or less, and all other integers have an unsigned
     * bit length of greater than 63. This interval is not the same as the range of
     * integers in Java's and.NET's {@code long} type.
     * @return The number of bits in this object's absolute value. Returns 0 if
     * this object's value is 0, and returns 1 if the value is negative 1. If the
     * return value would be greater than 2^63 - 1({@code Long.MAX_VALUE} in.NET
     * or {@code Long.MAX_VALUE} in Java), returns 2^63 - 1 instead. (Use {@code
     * GetUnsignedBitLengthAsEInteger} instead of this method if the application
     * relies on the exact number of bits.).
     */
    public long GetUnsignedBitLengthAsInt64() {
      // NOTE: Currently can't be 2^63-1 or greater, due to int32 word counts
      int wc = this.wordCount;
      if (wc != 0) {
        int numberValue = ((int)this.words[wc - 1]) & ShortMask;
        long longBase = ((long)wc - 1) << 4;
        if (numberValue == 0) {
          return longBase;
        }
        wc = 16;
        {
          if ((numberValue >> 8) == 0) {
            numberValue <<= 8;
            wc -= 8;
          }
          if ((numberValue >> 12) == 0) {
            numberValue <<= 4;
            wc -= 4;
          }
          if ((numberValue >> 14) == 0) {
            numberValue <<= 2;
            wc -= 2;
          }
          if ((numberValue >> 15) == 0) {
            --wc;
          }
        }
        return longBase + wc;
      }
      return 0;
    }

    /**
     * Finds the minimum number of bits needed to represent this number's absolute
     * value. For example, all integers in the interval [-((2^63) - 1), (2^63) - 1]
     * have an unsigned bit length of 63 or less, and all other integers have an
     * unsigned bit length of greater than 63. This interval is not the same as the
     * range of integers in Java's and.NET's {@code long} type.
     * @return The number of bits in this object's absolute value. Returns 0 if
     * this object's value is 0, and returns 1 if the value is negative 1.
     * @throws ArithmeticException The return value would exceed the range of a
     * 32-bit signed integer.
     * @deprecated This method may overflow. Use GetUnsignedBitLengthAsEInteger instead.
 */
@Deprecated
    public int GetUnsignedBitLength() {
      return this.GetUnsignedBitLengthAsEInteger().ToInt32Checked();
    }

    /**
     * Finds the modulus remainder that results when this instance is divided by
     * the value of an arbitrary-precision integer. The modulus remainder is the
     * same as the normal remainder if the normal remainder is positive, and equals
     * divisor plus normal remainder if the normal remainder is negative.
     * @param divisor The number to divide by.
     * @return An arbitrary-precision integer.
     * @throws IllegalArgumentException The parameter {@code divisor} is less than 0.
     * @throws NullPointerException The parameter {@code divisor} is null.
     */
    public EInteger Mod(EInteger divisor) {
      if (divisor == null) {
        throw new NullPointerException("divisor");
      }
      if (divisor.signum() < 0) {
        throw new ArithmeticException("Divisor is negative");
      }
      EInteger remainderEInt = this.Remainder(divisor);
      if (remainderEInt.signum() < 0) {
        remainderEInt = divisor.Add(remainderEInt);
      }
      return remainderEInt;
    }

    /**
     * Finds the modulus remainder that results when this instance is divided by
     * the value of another integer. The modulus remainder is the same as the
     * normal remainder if the normal remainder is positive, and equals divisor
     * plus normal remainder if the normal remainder is negative.
     * @param smallDivisor The divisor of the modulus.
     * @return The modulus remainder.
     * @throws IllegalArgumentException The parameter {@code smallDivisor} is less than 0.
     */
    public EInteger Mod(int smallDivisor) {
      if (smallDivisor < 0) {
        throw new ArithmeticException("Divisor is negative");
      }
      EInteger remainderEInt = this.Remainder(smallDivisor);
      if (remainderEInt.signum() < 0) {
        remainderEInt = EInteger.FromInt32(smallDivisor).Add(remainderEInt);
      }
      return remainderEInt;
    }

    /**
     * Calculates the remainder when this arbitrary-precision integer raised to a
     * certain power is divided by another arbitrary-precision integer.
     * @param pow The power to raise this integer by.
     * @param mod The integer to divide the raised number by.
     * @return An arbitrary-precision integer.
     * @throws NullPointerException The parameter {@code pow} or {@code mod} is
     * null.
     */
    public EInteger ModPow(EInteger pow, EInteger mod) {
      if (pow == null) {
        throw new NullPointerException("pow");
      }
      if (mod == null) {
        throw new NullPointerException("mod");
      }
      if (pow.signum() < 0) {
        throw new IllegalArgumentException("pow(" + pow + ") is less than 0");
      }
      if (mod.signum() <= 0) {
        throw new IllegalArgumentException("mod(" + mod + ") is not greater than 0");
      }
      EInteger r = EInteger.FromInt32(1);
      EInteger eiv = this;
      while (!pow.isZero()) {
        if (!pow.isEven()) {
          r = (r.Multiply(eiv)).Mod(mod);
        }
        pow = pow.ShiftRight(1);
        if (!pow.isZero()) {
          eiv = (eiv.Multiply(eiv)).Mod(mod);
        }
      }
      return r;
    }

    /**
     * Multiplies this arbitrary-precision integer by another arbitrary-precision
     * integer and returns the result.
     * @param bigintMult Another arbitrary-precision integer.
     * @return The product of the two numbers, that is, this arbitrary-precision
     * integer times another arbitrary-precision integer.
     * @throws NullPointerException The parameter {@code bigintMult} is null.
     */
    public EInteger Multiply(EInteger bigintMult) {
      if (bigintMult == null) {
        throw new NullPointerException("bigintMult");
      }
      if (this.wordCount == 0 || bigintMult.wordCount == 0) {
        return EInteger.FromInt32(0);
      }
      if (this.wordCount == 1 && this.words[0] == 1) {
        return this.negative ? bigintMult.Negate() : bigintMult;
      }
      if (bigintMult.wordCount == 1 && bigintMult.words[0] == 1) {
        return bigintMult.negative ? this.Negate() : this;
      }
      // System.out.println("multiply " + this + " " + bigintMult);
      short[] productreg;
      int productwordCount;
      boolean needShorten = true;
      if (this.wordCount == 1) {
        int wc;
        if (bigintMult.wordCount == 1) {
          // NOTE: Result can't be 0 here, since checks
          // for 0 were already made earlier in this function
          productreg = new short[2];
          int ba = ((int)this.words[0]) & ShortMask;
          int bb = ((int)bigintMult.words[0]) & ShortMask;
          ba = (ba * bb);
          productreg[0] = ((short)(ba & ShortMask));
          productreg[1] = ((short)((ba >> 16) & ShortMask));
          short preg = productreg[1];
          wc = (preg == 0) ? 1 : 2;
          return new EInteger(
              wc,
              productreg,
              this.negative ^ bigintMult.negative);
        }
        wc = bigintMult.wordCount;
        int regLength = wc + 1;
        productreg = new short[regLength];
        productreg[wc] = LinearMultiply(
            productreg,
            0,
            bigintMult.words,
            0,
            this.words[0],
            wc);
        productwordCount = productreg.length;
        needShorten = false;
      } else if (bigintMult.wordCount == 1) {
        int wc = this.wordCount;
        int regLength = wc + 1;
        productreg = new short[regLength];
        productreg[wc] = LinearMultiply(
            productreg,
            0,
            this.words,
            0,
            bigintMult.words[0],
            wc);
        productwordCount = productreg.length;
        needShorten = false;
      } else if (this.equals(bigintMult)) {
        int words1Size = this.wordCount;
        productreg = new short[words1Size + words1Size];
        productwordCount = productreg.length;
        short[] workspace = new short[words1Size + words1Size];
        RecursiveSquare(
          productreg,
          0,
          workspace,
          0,
          this.words,
          0,
          words1Size);
      } else if (this.wordCount <= MultRecursionThreshold &&
        bigintMult.wordCount <= MultRecursionThreshold) {
        int wc = this.wordCount + bigintMult.wordCount;
        productreg = new short[wc];
        productwordCount = productreg.length;
        SchoolbookMultiply(
          productreg,
          0,
          this.words,
          0,
          this.wordCount,
          bigintMult.words,
          0,
          bigintMult.wordCount);
        needShorten = false;
      } else {
        int words1Size = this.wordCount;
        int words2Size = bigintMult.wordCount;
        productreg = new short[words1Size + words2Size];
        short[] workspace = new short[words1Size + words2Size];
        productwordCount = productreg.length;
        AsymmetricMultiply(
          productreg,
          0,
          workspace,
          0,
          this.words,
          0,
          words1Size,
          bigintMult.words,
          0,
          words2Size);
      }
      // Recalculate word count
      while (productwordCount != 0 && productreg[productwordCount - 1] == 0) {
        --productwordCount;
      }
      if (needShorten) {
        productreg = ShortenArray(productreg, productwordCount);
      }
      return new EInteger(
          productwordCount,
          productreg,
          this.negative ^ bigintMult.negative);
    }

    private static EInteger MakeEInteger(
      short[] words,
      int wordsEnd,
      int offset,
      int count) {
      if (offset >= wordsEnd) {
        return EInteger.FromInt32(0);
      }
      int ct = Math.min(count, wordsEnd - offset);
      while (ct != 0 && words[offset + ct - 1] == 0) {
        --ct;
      }
      if (ct == 0) {
        return EInteger.FromInt32(0);
      }
      short[] newwords = new short[ct];
      System.arraycopy(words, offset, newwords, 0, ct);
      return new EInteger(ct, newwords, false);
    }

    private static void Toom3(
      short[] resultArr,
      int resultStart,
      short[] wordsA,
      int wordsAStart,
      int countA,
      short[] wordsB,
      int wordsBStart,
      int countB) {
      int imal = Math.max(countA, countB);
      int im3 = (imal / 3) + (((imal % 3) + 2) / 3);
      EInteger m3mul16 = EInteger.FromInt32(im3).ShiftLeft(4);
      EInteger x0 = MakeEInteger(
          wordsA,
          wordsAStart + countA,
          wordsAStart,
          im3);
      EInteger x1 = MakeEInteger(
          wordsA,
          wordsAStart + countA,
          wordsAStart + im3,
          im3);
      EInteger x2 = MakeEInteger(
          wordsA,
          wordsAStart + countA,
          wordsAStart + (im3 * 2),
          im3);
      EInteger w0, wt1, wt2, wt3, w4;
      if (wordsA == wordsB && wordsAStart == wordsBStart &&
        countA == countB) {
        // Same array, offset, and count, so we're squaring
        w0 = x0.Multiply(x0);
        w4 = x2.Multiply(x2);
        EInteger x2x0 = x2.Add(x0);
        wt1 = x2x0.Add(x1);
        wt2 = x2x0.Subtract(x1);
        wt3 = x2.ShiftLeft(2).Add(x1.ShiftLeft(1)).Add(x0);
        wt1 = wt1.Multiply(wt1);
        wt2 = wt2.Multiply(wt2);
        wt3 = wt3.Multiply(wt3);
      } else {
        EInteger y0 = MakeEInteger(
            wordsB,
            wordsBStart + countB,
            wordsBStart,
            im3);
        EInteger y1 = MakeEInteger(
            wordsB,
            wordsBStart + countB,
            wordsBStart + im3,
            im3);
        EInteger y2 = MakeEInteger(
            wordsB,
            wordsBStart + countB,
            wordsBStart + (im3 * 2),
            im3);
        w0 = x0.Multiply(y0);
        w4 = x2.Multiply(y2);
        EInteger x2x0 = x2.Add(x0);
        EInteger y2y0 = y2.Add(y0);
        wt1 = x2x0.Add(x1).Multiply(y2y0.Add(y1));
        wt2 = x2x0.Subtract(x1).Multiply(y2y0.Subtract(y1));
        wt3 = x2.ShiftLeft(2).Add(x1.ShiftLeft(1)).Add(x0)
          .Multiply(y2.ShiftLeft(2).Add(y1.ShiftLeft(1)).Add(y0));
      }
      EInteger w4mul2 = w4.ShiftLeft(1);
      EInteger w4mul12 = w4mul2.Multiply(6);
      EInteger w0mul3 = w0.Multiply(3);
      EInteger w3 = w0mul3.Subtract(w4mul12).Subtract(wt1.Multiply(3))
        .Subtract(wt2).Add(wt3).Divide(6);
      EInteger w2 = wt1.Add(wt2).Subtract(w0.ShiftLeft(1))
        .Subtract(w4mul2).ShiftRight(1);
      EInteger w1 = wt1.Multiply(6).Add(w4mul12)
        .Subtract(wt3).Subtract(wt2).Subtract(wt2)
        .Subtract(w0mul3).Divide(6);
      if (m3mul16.compareTo(0x70000000) < 0) {
        im3 <<= 4; // multiply by 16
        w0 = w0.Add(w1.ShiftLeft(im3));
        w0 = w0.Add(w2.ShiftLeft(im3 * 2));
        w0 = w0.Add(w3.ShiftLeft(im3 * 3));
        w0 = w0.Add(w4.ShiftLeft(im3 * 4));
      } else {
        w0 = w0.Add(w1.ShiftLeft(m3mul16));
        w0 = w0.Add(w2.ShiftLeft(m3mul16.Multiply(2)));
        w0 = w0.Add(w3.ShiftLeft(m3mul16.Multiply(3)));
        w0 = w0.Add(w4.ShiftLeft(m3mul16.Multiply(4)));
      }
      java.util.Arrays.fill(resultArr, resultStart, (resultStart)+(countA + countB), (short)0);
      System.arraycopy(
        w0.words,
        0,
        resultArr,
        resultStart,
        Math.min(countA + countB, w0.wordCount));
    }

    private static EInteger Interpolate(
      EInteger[] wts,
      int[] values,
      int divisor) {
      EInteger ret = EInteger.FromInt32(0);
      for (int i = 0; i < wts.length; ++i) {
        int v = values[i];
        if (v == 0) {
          continue;
        } else {
          ret = (v == 1) ? ret.Add(wts[i]) : ((v == -1) ? ret.Subtract(
            wts[i]) : ret.Add(wts[i].Multiply(v)));
        }
      }
      return ret.Divide(divisor);
    }
    /*
    public EInteger Toom4Multiply(EInteger eib) {
      short[] r = new short[this.wordCount + eib.wordCount];
      Toom4(r, 0, this.words, 0, this.wordCount, eib.words, 0, eib.wordCount);
      EInteger ret = MakeEInteger(r, r.length, 0, r.length);
      return (this.negative^eib.negative) ? ret.Negate() : ret;
    }
    */
    private static void Toom4(
      short[] resultArr,
      int resultStart,
      short[] wordsA,
      int wordsAStart,
      int countA,
      short[] wordsB,
      int wordsBStart,
      int countB) {
      int imal = Math.max(countA, countB);
      int im3 = (imal / 4) + (((imal % 4) + 3) / 4);
      EInteger m3mul16 = EInteger.FromInt32(im3).ShiftLeft(4);
      EInteger x0 = MakeEInteger(
          wordsA,
          wordsAStart + countA,
          wordsAStart,
          im3);
      EInteger x1 = MakeEInteger(
          wordsA,
          wordsAStart + countA,
          wordsAStart + im3,
          im3);
      EInteger x2 = MakeEInteger(
          wordsA,
          wordsAStart + countA,
          wordsAStart + (im3 * 2),
          im3);
      EInteger x3 = MakeEInteger(
          wordsA,
          wordsAStart + countA,
          wordsAStart + (im3 * 3),
          im3);
      EInteger w0, wt1, wt2, wt3, wt4, wt5, w6;
      if (wordsA == wordsB && wordsAStart == wordsBStart &&
        countA == countB) {
        // Same array, offset, and count, so we're squaring
        w0 = x0.Multiply(x0);
        w6 = x3.Multiply(x3);
        EInteger x2mul2 = x2.ShiftLeft(1);
        EInteger x1mul4 = x1.ShiftLeft(2);
        EInteger x0mul8 = x0.ShiftLeft(3);
        EInteger x1x3 = x1.Add(x3);
        EInteger x0x2 = x0.Add(x2);
        wt1 = x3.Add(x2mul2).Add(x1mul4).Add(x0mul8);
        wt2 = x3.Negate().Add(x2mul2).Subtract(x1mul4).Add(x0mul8);
        wt3 = x0x2.Add(x1x3);
        wt4 = x0x2.Subtract(x1x3);
        wt5 = x0.Add(
            x3.ShiftLeft(3)).Add(x2.ShiftLeft(2)).Add(x1.ShiftLeft(1));
        wt1 = wt1.Multiply(wt1);
        wt2 = wt2.Multiply(wt2);
        wt3 = wt3.Multiply(wt3);
        wt4 = wt4.Multiply(wt4);
        wt5 = wt5.Multiply(wt5);
      } else {
        EInteger y0 = MakeEInteger(
            wordsB,
            wordsBStart + countB,
            wordsBStart,
            im3);
        EInteger y1 = MakeEInteger(
            wordsB,
            wordsBStart + countB,
            wordsBStart + im3,
            im3);
        EInteger y2 = MakeEInteger(
            wordsB,
            wordsBStart + countB,
            wordsBStart + (im3 * 2),
            im3);
        EInteger y3 = MakeEInteger(
            wordsB,
            wordsBStart + countB,
            wordsBStart + (im3 * 3),
            im3);
        w0 = x0.Multiply(y0);
        w6 = x3.Multiply(y3);
        EInteger x2mul2 = x2.ShiftLeft(1);
        EInteger x1mul4 = x1.ShiftLeft(2);
        EInteger x0mul8 = x0.ShiftLeft(3);
        EInteger y2mul2 = y2.ShiftLeft(1);
        EInteger y1mul4 = y1.ShiftLeft(2);
        EInteger y0mul8 = y0.ShiftLeft(3);
        EInteger x1x3 = x1.Add(x3);
        EInteger x0x2 = x0.Add(x2);
        EInteger y1y3 = y1.Add(y3);
        EInteger y0y2 = y0.Add(y2);
        wt1 = x3.Add(x2mul2).Add(x1mul4).Add(x0mul8);
        wt1 = wt1.Multiply(y3.Add(y2mul2).Add(y1mul4).Add(y0mul8));
        wt2 = x3.Negate().Add(x2mul2).Subtract(x1mul4).Add(x0mul8);
        wt2 = wt2.Multiply(
            y3.Negate().Add(y2mul2).Subtract(y1mul4).Add(y0mul8));
        wt3 = x0x2.Add(x1x3);
        wt3 = wt3.Multiply(y0y2.Add(y1y3));
        wt4 = x0x2.Subtract(x1x3);
        wt4 = wt4.Multiply(y0y2.Subtract(y1y3));
        wt5 = x0.Add(
            x3.ShiftLeft(3)).Add(x2.ShiftLeft(2)).Add(x1.ShiftLeft(1));
        wt5 = wt5.Multiply(
            y0.Add(
              y3.ShiftLeft(3)).Add(y2.ShiftLeft(2)).Add(y1.ShiftLeft(1)));
      }
      EInteger[] wts = { w0, wt1, wt2, wt3, wt4, wt5, w6 };
      int[] wts2 = new int[] {
        -90, 5, -3, -60, 20, 2,
        -90,
      };
      EInteger w1 = Interpolate(wts, wts2, 180);
      wts2 = new int[] {
        -120,
        1,
        1,
        -4,
        -4,
        0,
        6,
      };
      EInteger w2 = Interpolate(wts, wts2, 24);
      wts2 = new int[] {
        45,
        -1,
        0,
        27,
        -7,
        -1,
        45,
      };
      EInteger w3 = Interpolate(wts,
          wts2,
          18);
      wts2 = new int[] {
        96,
        -1,
        -1,
        16,
        16,
        0,
        -30,
      };
      EInteger w4 = Interpolate(
          wts,
          wts2,
          24);
      wts2 = new int[] {
        -360, 5, 3, -120, -40, 8,
        -360,
      };
      EInteger w5 = Interpolate(wts,
          wts2,
          180);
      if (m3mul16.compareTo(0x70000000) < 0) {
        im3 <<= 4; // multiply by 16
        w0 = w0.Add(w1.ShiftLeft(im3));
        w0 = w0.Add(w2.ShiftLeft(im3 * 2));
        w0 = w0.Add(w3.ShiftLeft(im3 * 3));
        w0 = w0.Add(w4.ShiftLeft(im3 * 4));
        w0 = w0.Add(w5.ShiftLeft(im3 * 5));
        w0 = w0.Add(w6.ShiftLeft(im3 * 6));
      } else {
        w0 = w0.Add(w1.ShiftLeft(m3mul16));
        w0 = w0.Add(w2.ShiftLeft(m3mul16.Multiply(2)));
        w0 = w0.Add(w3.ShiftLeft(m3mul16.Multiply(3)));
        w0 = w0.Add(w4.ShiftLeft(m3mul16.Multiply(4)));
        w0 = w0.Add(w5.ShiftLeft(m3mul16.Multiply(5)));
        w0 = w0.Add(w6.ShiftLeft(m3mul16.Multiply(6)));
      }
      java.util.Arrays.fill(resultArr, resultStart, (resultStart)+(countA + countB), (short)0);
      System.arraycopy(
        w0.words,
        0,
        resultArr,
        resultStart,
        Math.min(countA + countB, w0.wordCount));
    }

    /**
     * Gets the value of this object with the sign reversed.
     * @return This object's value with the sign reversed.
     */
    public EInteger Negate() {
      return this.wordCount == 0 ? this : new EInteger(
          this.wordCount,
          this.words,
          !this.negative);
    }

    /**
     * Raises an arbitrary-precision integer to a power.
     * @param longPower The exponent to raise this integer to.
     * @return The result. Returns 1 if {@code longPower} is 0.
     * @throws IllegalArgumentException BigPower is negative.
     */
    public EInteger Pow(long longPower) {
      if (longPower < 0) {
        throw new IllegalArgumentException("bigPower is negative");
      }
      if (longPower == 0) {
        return EInteger.FromInt32(1);
      }
      return (longPower < Integer.MAX_VALUE) ? this.Pow((int)longPower) :
        this.Pow(EInteger.FromInt64(longPower));
    }

    /**
     * Raises an arbitrary-precision integer to a power.
     * @param bigPower The exponent to raise this integer to.
     * @return The result. Returns 1 if {@code bigPower} is 0.
     * @throws NullPointerException The parameter {@code bigPower} is null.
     * @throws IllegalArgumentException BigPower is negative.
     */
    public EInteger Pow(EInteger bigPower) {
      if (bigPower == null) {
        throw new NullPointerException("bigPower");
      }
      if (bigPower.signum() < 0) {
        throw new IllegalArgumentException("bigPower is negative");
      }
      if (bigPower.signum() == 0) {
        // however 0 to the power of 0 is undefined
        return EInteger.FromInt32(1);
      }
      if (bigPower.compareTo(1) == 0) {
        return this;
      }
      if (this.isZero() || this.compareTo(1) == 0) {
        return this;
      }
      if (this.compareTo(-1) == 0) {
        return bigPower.isEven() ? EInteger.FromInt32(1) : this;
      }
      EInteger bitLength = this.GetUnsignedBitLengthAsEInteger();
      if (!this.isPowerOfTwo()) {
        bitLength = bitLength.Subtract(1);
      }
      // System.out.println("sizeNeeded=" + bitLength.Multiply(bigPower));
      // System.out.println("bigPower=" + bigPower);
      if (bigPower.CanFitInInt32()) {
        return this.Pow(bigPower.ToInt32Checked());
      }
      EInteger bp = bigPower;
      EInteger ret = EInteger.FromInt32(1);
      EInteger rmax = this.Pow(Integer.MAX_VALUE);
      while (!bp.CanFitInInt32()) {
        ret = ret.Multiply(rmax);
        bp = bp.Subtract(Integer.MAX_VALUE);
      }
      int lastp = bp.ToInt32Checked();
      ret = (lastp == Integer.MAX_VALUE) ? ret.Multiply(rmax) :
        ret.Multiply(this.Pow(lastp));
      return ret;
    }

    /**
     * Raises an arbitrary-precision integer to a power.
     * @param powerSmall The exponent to raise this integer to.
     * @return The result. Returns 1 if {@code powerSmall} is 0.
     */
    public EInteger Pow(int powerSmall) {
      if (powerSmall < 0) {
        throw new IllegalArgumentException("powerSmall(" + powerSmall +
          ") is less than 0");
      }
      EInteger thisVar = this;
      if (powerSmall == 0) {
        // however 0 to the power of 0 is undefined
        return EInteger.FromInt32(1);
      }
      if (powerSmall == 1) {
        return this;
      }
      if (this.isZero() || this.compareTo(1) == 0) {
        return this;
      }
      if (this.compareTo(-1) == 0) {
        return (powerSmall & 1) == 0 ? EInteger.FromInt32(1) : this;
      }
      if (powerSmall == 2) {
        return thisVar.Multiply(thisVar);
      }
      if (powerSmall == 3) {
        return thisVar.Multiply(thisVar).Multiply(thisVar);
      }
      EInteger r = EInteger.FromInt32(1);
      // boolean negatePower = (powerSmall & 1) != 0 && thisVar.signum() < 0;
      // thisVar = thisVar.Abs();
      while (powerSmall != 0) {
        if ((powerSmall & 1) != 0) {
          r = r.Multiply(thisVar);
        }
        powerSmall >>= 1;
        if (powerSmall != 0) {
          thisVar = thisVar.Multiply(thisVar);
        }
      }
      return r; // negatePower ? r.Negate() : r;
    }

    /**
     * Raises an arbitrary-precision integer to a power, which is given as another
     * arbitrary-precision integer.
     * @param power The exponent to raise to.
     * @return The result. Returns 1 if {@code power} is 0.
     * @throws IllegalArgumentException The parameter {@code power} is less than 0.
     * @throws NullPointerException The parameter {@code power} is null.
     * @deprecated Use Pow instead.
 */
@Deprecated
    public EInteger PowBigIntVar(EInteger power) {
      if (power == null) {
        throw new NullPointerException("power");
      }
      return this.Pow(power);
    }

    /**
     * Returns the remainder that would result when this arbitrary-precision
     * integer is divided by another arbitrary-precision integer. The remainder is
     * the number that remains when the absolute value of this arbitrary-precision
     * integer is divided by the absolute value of the other arbitrary-precision
     * integer; the remainder has the same sign (positive or negative) as this
     * arbitrary-precision integer.
     * @param divisor The number to divide by.
     * @return The remainder that would result when this arbitrary-precision
     * integer is divided by another arbitrary-precision integer.
     * @throws ArithmeticException Attempted to divide by zero.
     * @throws NullPointerException The parameter {@code divisor} is null.
     */
    public EInteger Remainder(EInteger divisor) {
      if (divisor == null) {
        throw new NullPointerException("divisor");
      }
      int words1Size = this.wordCount;
      int words2Size = divisor.wordCount;
      if (words2Size == 0) {
        throw new ArithmeticException();
      }
      if (words1Size < words2Size) {
        // dividend is less than divisor
        return this;
      }
      if (words2Size == 1) {
        short shortRemainder = FastRemainder(
            this.words,
            this.wordCount,
            divisor.words[0]);
        int smallRemainder = ((int)shortRemainder) & ShortMask;
        if (this.negative) {
          smallRemainder = -smallRemainder;
        }
        return EInteger.FromInt64(smallRemainder);
      }
      if (this.PositiveCompare(divisor) < 0) {
        return this;
      }
      short[] remainderReg = new short[(int)words2Size];
      GeneralDivide(
        this.words,
        0,
        this.wordCount,
        divisor.words,
        0,
        divisor.wordCount,
        null,
        0,
        remainderReg,
        0);
      int count = CountWords(remainderReg);
      if (count == 0) {
        return EInteger.FromInt32(0);
      }
      remainderReg = ShortenArray(remainderReg, count);
      return new EInteger(count, remainderReg, this.negative);
    }

    /**
     * Returns an arbitrary-precision integer with the bits shifted to the right.
     * For this operation, the arbitrary-precision integer is treated as a
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}). Thus, for negative values, the arbitrary-precision integer is
     * sign-extended.
     * @param eshift The number of bits to shift. Can be negative, in which case
     * this is the same as ShiftLeft with the absolute value of this parameter.
     * @return An arbitrary-precision integer.
     * @throws NullPointerException The parameter {@code eshift} is null.
     */
    public EInteger ShiftRight(EInteger eshift) {
      if (eshift == null) {
        throw new NullPointerException("eshift");
      }
      EInteger valueETempShift = eshift;
      EInteger ret = this;
      if (valueETempShift.signum() < 0) {
        return ret.ShiftLeft(valueETempShift.Negate());
      }
      while (!valueETempShift.CanFitInInt32()) {
        valueETempShift = valueETempShift.Subtract(0x7ffffff0);
        ret = ret.ShiftRight(0x7ffffff0);
      }
      return ret.ShiftRight(valueETempShift.ToInt32Checked());
    }

    /**
     * Returns an arbitrary-precision integer with the bits shifted to the left by
     * a number of bits given as an arbitrary-precision integer. A value of 1
     * doubles this value, a value of 2 multiplies it by 4; a value of 3, by 8; a
     * value of 4, by 16; and in general, a value of N, by 2^N, where N is 1 or
     * greater.
     * @param eshift The number of bits to shift. Can be negative, in which case
     * this is the same as ShiftRight with the absolute value of this parameter.
     * @return An arbitrary-precision integer.
     * @throws NullPointerException The parameter {@code eshift} is null.
     */
    public EInteger ShiftLeft(EInteger eshift) {
      if (eshift == null) {
        throw new NullPointerException("eshift");
      }
      EInteger valueETempShift = eshift;
      EInteger ret = this;
      if (valueETempShift.signum() < 0) {
        return ret.ShiftRight(valueETempShift.Negate());
      }
      while (!valueETempShift.CanFitInInt32()) {
        valueETempShift = valueETempShift.Subtract(0x7ffffff0);
        ret = ret.ShiftLeft(0x7ffffff0);
      }
      return ret.ShiftLeft(valueETempShift.ToInt32Checked());
    }

    /**
     * Returns an arbitrary-precision integer with the bits shifted to the left by
     * a number of bits. A value of 1 doubles this value, a value of 2 multiplies
     * it by 4, a value of 3, by 8, a value of 4, by 16, and in general, a value of
     * N, by 2^N, where N is 1 or greater.
     * @param numberBits The number of bits to shift. Can be negative, in which
     * case this is the same as shiftRight with the absolute value of this
     * parameter.
     * @return An arbitrary-precision integer.
     */
    public EInteger ShiftLeft(int numberBits) {
      if (numberBits == 0 || this.wordCount == 0) {
        return this;
      }
      if (numberBits < 0) {
        return (numberBits == Integer.MIN_VALUE) ?
          this.ShiftRight(1).ShiftRight(Integer.MAX_VALUE) :
          this.ShiftRight(-numberBits);
      }
      int numWords = this.wordCount;
      int shiftWords = (int)(numberBits >> 4);
      int shiftBits = (int)(numberBits & 15);
      if (!this.negative) {
        // Determine shifted integer's word count in advance;
        // it's more cache-friendly to do so because the
        // unshifted word has less memory
        int lastWord = ((int)this.words[this.wordCount - 1]) & 0xffff;
        int lastWordBL = NumberUtility.BitLength(lastWord) +
          shiftBits;
        int newWordCount = 0;
        if (lastWordBL <= 16) {
          // New bit count is such that an additional word
          // is not needed
          newWordCount = numWords + shiftWords;
        } else {
          newWordCount = numWords + BitsToWords(numberBits);
        }
        short[] ret = new short[newWordCount];
        System.arraycopy(this.words, 0, ret, shiftWords, numWords);
        ShiftWordsLeftByBits(
          ret,
          shiftWords,
          newWordCount - shiftWords,
          shiftBits);

        return new EInteger(newWordCount, ret, false);
      } else {
        short[] ret = new short[numWords + BitsToWords((int)numberBits)];
        System.arraycopy(this.words, 0, ret, 0, numWords);
        TwosComplement(ret, 0, (int)ret.length);
        ShiftWordsLeftByWords(ret, 0, numWords + shiftWords, shiftWords);
        ShiftWordsLeftByBits(
          ret,
          (int)shiftWords,
          numWords + BitsToWords(shiftBits),
          shiftBits);
        TwosComplement(ret, 0, (int)ret.length);
        return new EInteger(CountWords(ret), ret, true);
      }
    }

    private static void OrWords(short[] r, short[] a, short[] b, int n) {
      for (int i = 0; i < n; ++i) {
        r[i] = ((short)(a[i] | b[i]));
      }
    }

    private static void XorWords(short[] r, short[] a, short[] b, int n) {
      for (int i = 0; i < n; ++i) {
        r[i] = ((short)(a[i] ^ b[i]));
      }
    }

    private static void NotWords(short[] r, int n) {
      for (int i = 0; i < n; ++i) {
        r[i] = ((short)(~r[i]));
      }
    }

    private static void AndWords(short[] r, short[] a, short[] b, int n) {
      for (int i = 0; i < n; ++i) {
        r[i] = ((short)(a[i] & b[i]));
      }
    }

    /**
     * Returns an arbitrary-precision integer with every bit flipped from this one
     * (also called an inversion or NOT operation).
     * @return An arbitrary-precision integer in which each bit in its two's
     * complement representation is set if the corresponding bit of this integer is
     * clear, and vice versa. Returns -1 if this integer is 0. If this integer is
     * positive, the return value is negative, and vice versa. This method uses the
     * two's complement form of negative integers (see {@code
     * com.upokecenter.numbers.EDecimal}). For example, in binary, NOT 10100 =...11101011 (or in decimal, NOT 20 = -21). In binary, NOT...11100110 =
     * 11001 (or in decimal, NOT -26 = 25).
     */
    public EInteger Not() {
      if (this.wordCount == 0) {
        return EInteger.FromInt32(-1);
      }
      boolean valueXaNegative = false;
      int valueXaWordCount = 0;
      short[] valueXaReg = new short[this.wordCount];
      System.arraycopy(this.words, 0, valueXaReg, 0, valueXaReg.length);
      valueXaWordCount = this.wordCount;
      if (this.negative) {
        TwosComplement(valueXaReg, 0, (int)valueXaReg.length);
      }
      NotWords(
        valueXaReg,
        (int)valueXaReg.length);
      if (this.negative) {
        TwosComplement(valueXaReg, 0, (int)valueXaReg.length);
      }
      valueXaNegative = !this.negative;
      valueXaWordCount = CountWords(valueXaReg);
      return (valueXaWordCount == 0) ? EInteger.FromInt32(0) : new
        EInteger(valueXaWordCount, valueXaReg, valueXaNegative);
    }

    /**
     * Extracts the lowest bits of this integer. This is equivalent to {@code
     * And(2^longBitCount - 1)}, but is more efficient when this integer is
     * nonnegative and longBitCount's value is large.
     * @param longBitCount The number of bits to extract from the lowest part of
     * this integer.
     * @return A value equivalent to {@code And(2^longBitCount - 1)}.
     */
    public EInteger LowBits(long longBitCount) {
      if (longBitCount < 0) {
        throw new IllegalArgumentException("\"longBitCount\" (" + longBitCount +
          ") is" + "\u0020not greater or equal to 0");
      }
      return (longBitCount <= Integer.MAX_VALUE) ?
        this.LowBits((int)longBitCount) :
        this.LowBits(EInteger.FromInt64(longBitCount));
    }

    /**
     * Extracts the lowest bits of this integer. This is equivalent to {@code
     * And(2^bitCount - 1)}, but is more efficient when this integer is nonnegative
     * and bitCount's value is large.
     * @param bitCount The number of bits to extract from the lowest part of this
     * integer.
     * @return A value equivalent to {@code And(2^bitCount - 1)}.
     */
    public EInteger LowBits(int bitCount) {
      if (bitCount < 0) {
        throw new IllegalArgumentException("\"bitCount\" (" + bitCount + ") is" +
          "\u0020not greater or equal to 0");
      }
      if (bitCount == 0 || this.signum() == 0) {
        return EInteger.FromInt32(0);
      }
      if (this.signum() > 0) {
        long bits = this.GetUnsignedBitLengthAsInt64();
        if (bits <= bitCount) {
          return this;
        }
      }
      if (!this.negative) {
        long otherWordCount = BitsToWords(bitCount);
        if (this.wordCount < otherWordCount) {
          return this;
        } else if (otherWordCount == 0) {
          return EInteger.FromInt32(0);
        } else {
          int intOtherWordCount = ((int)otherWordCount);
          int bitRemainder = bitCount & 15;
          int smallerCount = Math.min(this.wordCount, intOtherWordCount);
          short[] result = new short[intOtherWordCount];
          if (bitRemainder == 0) {
            System.arraycopy(this.words, 0, result, 0, intOtherWordCount);
          } else {
            short shortMask = ((short)((1 << bitRemainder) - 1));
            // System.out.println("wc={0} bc={1} br={2}
            // sm={3}",otherWordCount,bitCount,bitRemainder,shortMask);
            System.arraycopy(this.words, 0, result, 0, intOtherWordCount - 1);
            result[intOtherWordCount - 1] = ((short)(
              this.words[intOtherWordCount - 1] & shortMask));
          }
          smallerCount = CountWords(result);
          return (smallerCount == 0) ? EInteger.FromInt32(0) : new
            EInteger(smallerCount, result, false);
        }
      }
      return this.And(EInteger.FromInt32(1).ShiftLeft(bitCount).Subtract(1));
    }

    /**
     * Extracts the lowest bits of this integer. This is equivalent to {@code
     * And(2^bigBitCount - 1)}, but is more efficient when this integer is
     * nonnegative and bigBitCount's value is large.
     * @param bigBitCount The number of bits to extract from the lowest part of
     * this integer.
     * @return A value equivalent to {@code And(2^bigBitCount - 1)}.
     * @throws NullPointerException The parameter {@code bigBitCount} is null.
     */
    public EInteger LowBits(EInteger bigBitCount) {
      if (bigBitCount == null) {
        throw new NullPointerException("bigBitCount");
      }
      if (bigBitCount.signum() < 0) {
        throw new IllegalArgumentException("\"bigBitCount.signum()\" (" +
          bigBitCount.signum() + ") is not greater or equal to 0");
      }
      if (bigBitCount.signum() == 0 || this.signum() == 0) {
        return EInteger.FromInt32(0);
      }
      if (this.signum() > 0) {
        EInteger bigBits = this.GetUnsignedBitLengthAsEInteger();
        if (bigBits.compareTo(bigBitCount) <= 0) {
          return this;
        }
        if (bigBitCount.CanFitInInt32()) {
          return this.LowBits((int)bigBitCount.ToInt32Checked());
        }
      }
      if (!this.negative) {
        EInteger bigOtherWordCount = bigBitCount.Add(15).Divide(16);
        if (
          EInteger.FromInt32(this.wordCount).compareTo(bigOtherWordCount) <
          0) {
          return this;
        }
        long otherWordCount = bigOtherWordCount.ToInt32Checked();
        if (otherWordCount == 0) {
          return EInteger.FromInt32(0);
        } else {
          int intOtherWordCount = ((int)otherWordCount);
          int bitRemainder = bigBitCount.Remainder(16).ToInt32Checked();
          int smallerCount = Math.min(this.wordCount, intOtherWordCount);
          short[] result = new short[intOtherWordCount];
          if (bitRemainder == 0) {
            System.arraycopy(this.words, 0, result, 0, intOtherWordCount);
          } else {
            short shortMask = ((short)((1 << bitRemainder) - 1));
            // System.out.println("wc={0} bc={1} br={2} sm={3}
            // big",otherWordCount,bigBitCount,bitRemainder,shortMask);
            System.arraycopy(this.words, 0, result, 0, intOtherWordCount - 1);
            result[intOtherWordCount - 1] = ((short)(
              this.words[intOtherWordCount - 1] & shortMask));
          }
          smallerCount = CountWords(result);
          return (smallerCount == 0) ? EInteger.FromInt32(0) : new
            EInteger(smallerCount, result, false);
        }
      }
      return this.And(EInteger.FromInt32(1).ShiftLeft(bigBitCount).Subtract(1));
    }

    /**
     * <p>Does an AND operation between this arbitrary-precision integer and
     * another one.</p> <p>Each arbitrary-precision integer is treated as a
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}) for the purposes of this operator.</p>
     * @param other Another arbitrary-precision integer that participates in the
     * operation.
     * @return An arbitrary-precision integer in which each bit is set if the
     * corresponding bits of this integer and the other integer (in their
     * two's-complement representation) are both set. For example, in binary, 10110
     * AND 01100 = 00100 (or in decimal, 22 AND 12 = 4). This method uses the two's
     * complement form of negative integers (see {@code
     * com.upokecenter.numbers.EDecimal}). For example, in binary, ...11100111 AND
     * 01100 = 00100 (or in decimal, -25 AND 12 = 4).
     * @throws NullPointerException The parameter {@code other} is null.
     */
    public EInteger And(EInteger other) {
      if (other == null) {
        throw new NullPointerException("other");
      }
      if (other.isZero() || this.isZero()) {
        return EInteger.FromInt32(0);
      }
      if (!this.negative && !other.negative) {
        int smallerCount = Math.min(this.wordCount, other.wordCount);
        short[] smaller = (this.wordCount == smallerCount) ?
          this.words : other.words;
        short[] bigger = (this.wordCount == smallerCount) ?
          other.words : this.words;
        short[] result = new short[smallerCount];
        for (int i = 0; i < smallerCount; ++i) {
          result[i] = ((short)(smaller[i] & bigger[i]));
        }
        smallerCount = CountWords(result);
        return (smallerCount == 0) ? EInteger.FromInt32(0) : new
          EInteger(smallerCount, result, false);
      }
      boolean valueXaNegative = false;
      int valueXaWordCount = 0;
      short[] valueXaReg = new short[this.wordCount];
      System.arraycopy(this.words, 0, valueXaReg, 0, valueXaReg.length);
      boolean valueXbNegative = false;
      short[] valueXbReg = new short[other.wordCount];
      System.arraycopy(other.words, 0, valueXbReg, 0, valueXbReg.length);
      valueXaNegative = this.negative;
      valueXaWordCount = this.wordCount;
      valueXbNegative = other.negative;
      valueXaReg = CleanGrow(
          valueXaReg,
          Math.max(valueXaReg.length, valueXbReg.length));
      valueXbReg = CleanGrow(
          valueXbReg,
          Math.max(valueXaReg.length, valueXbReg.length));
      if (valueXaNegative) {
        TwosComplement(valueXaReg, 0, (int)valueXaReg.length);
      }
      if (valueXbNegative) {
        TwosComplement(valueXbReg, 0, (int)valueXbReg.length);
      }
      valueXaNegative &= valueXbNegative;
      AndWords(valueXaReg, valueXaReg, valueXbReg, (int)valueXaReg.length);
      if (valueXaNegative) {
        TwosComplement(valueXaReg, 0, (int)valueXaReg.length);
      }
      valueXaWordCount = CountWords(valueXaReg);
      return (valueXaWordCount == 0) ? EInteger.FromInt32(0) : new
        EInteger(valueXaWordCount, valueXaReg, valueXaNegative);
    }

    /**
     * <p>Does an OR operation between this arbitrary-precision integer and another
     * one.</p> <p>Each arbitrary-precision integer is treated as a
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}) for the purposes of this operator.</p>
     * @param second Another arbitrary-precision integer that participates in the
     * operation.
     * @return An arbitrary-precision integer in which each bit is set if the
     * corresponding bit of this integer is set, the other integer's corresponding
     * bit is set, or both. For example, in binary, 10110 OR 11010 = 11110 (or in
     * decimal, 22 OR 26 = 30). This method uses the two's complement form of
     * negative integers (see {@code com.upokecenter.numbers.EDecimal}). For
     * example, in binary, ...11101110 OR 01011 =...11101111 (or in decimal, -18
     * OR 11 = -17).
     * @throws NullPointerException The parameter {@code second} is null.
     */
    public EInteger Or(EInteger second) {
      if (second == null) {
        throw new NullPointerException("second");
      }
      if (this.wordCount == 0) {
        return second;
      }
      if (second.wordCount == 0) {
        return this;
      }
      if (!this.negative && !second.negative) {
        int smallerCount = Math.min(this.wordCount, second.wordCount);
        int biggerCount = Math.max(this.wordCount, second.wordCount);
        short[] smaller = (this.wordCount == smallerCount) ?
          this.words : second.words;
        short[] bigger = (this.wordCount == smallerCount) ?
          second.words : this.words;
        short[] result = new short[biggerCount];
        for (int i = 0; i < smallerCount; ++i) {
          result[i] = ((short)(smaller[i] | bigger[i]));
        }
        System.arraycopy(
          bigger,
          smallerCount,
          result,
          smallerCount,
          biggerCount - smallerCount);

        return new EInteger(biggerCount, result, false);
      }
      boolean valueXaNegative = false;
      int valueXaWordCount = 0;
      short[] valueXaReg = new short[this.wordCount];
      System.arraycopy(this.words, 0, valueXaReg, 0, valueXaReg.length);
      boolean valueXbNegative = false;
      short[] valueXbReg = new short[second.wordCount];
      System.arraycopy(second.words, 0, valueXbReg, 0, valueXbReg.length);
      valueXaNegative = this.negative;
      valueXaWordCount = this.wordCount;
      valueXbNegative = second.negative;
      valueXaReg = CleanGrow(
          valueXaReg,
          Math.max(valueXaReg.length, valueXbReg.length));
      valueXbReg = CleanGrow(
          valueXbReg,
          Math.max(valueXaReg.length, valueXbReg.length));
      if (valueXaNegative) {
        TwosComplement(valueXaReg, 0, (int)valueXaReg.length);
      }
      if (valueXbNegative) {
        TwosComplement(valueXbReg, 0, (int)valueXbReg.length);
      }
      valueXaNegative |= valueXbNegative;
      OrWords(valueXaReg, valueXaReg, valueXbReg, (int)valueXaReg.length);
      if (valueXaNegative) {
        TwosComplement(valueXaReg, 0, (int)valueXaReg.length);
      }
      valueXaWordCount = CountWords(valueXaReg);
      return (valueXaWordCount == 0) ? EInteger.FromInt32(0) : new
        EInteger(valueXaWordCount, valueXaReg, valueXaNegative);
    }

    /**
     * <p>Does an AND NOT operation between this arbitrary-precision integer and
     * another one.</p> <p>Each arbitrary-precision integer is treated as a
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}) for the purposes of this operator.</p>
     * @param second Another arbitrary-precision integer that participates in the
     * operation.
     * @return An arbitrary-precision integer in which each bit is set if the
     * corresponding bit of this integer is set, and the other integer's
     * corresponding bit is not set. For example, in binary, 10110 AND NOT 11010 =
     * 00100 (or in decimal, 22 AND NOT 26 = 4). This method uses the two's
     * complement form of negative integers (see {@code
     * com.upokecenter.numbers.EDecimal}). For example, in binary, ...11101110 AND
     * NOT 01011 = 00100 (or in decimal, -18 OR 11 = 4).
     * @throws NullPointerException The parameter {@code second} is null.
     */
    public EInteger AndNot(EInteger second) {
      if (second == null) {
        throw new NullPointerException("second");
      }
      return this.And(second.Not());
    }

    /**
     * <p>Does an OR NOT operation between this arbitrary-precision integer and
     * another one.</p> <p>Each arbitrary-precision integer is treated as a
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}) for the purposes of this operator.</p>
     * @param second Another arbitrary-precision integer that participates in the
     * operation.
     * @return An arbitrary-precision integer in which each bit is set if the
     * corresponding bit of this integer is set, the other integer's corresponding
     * bit is not set, or both. For example, in binary, 10110 OR NOT 11010 = 00100
     * (or in decimal, 22 OR NOT 26 = 23). This method uses the two's complement
     * form of negative integers (see {@code com.upokecenter.numbers.EDecimal}).
     * For example, in binary, ...11101110 OR NOT 01011 =...11111110 (or in
     * decimal, -18 OR 11 = -2).
     * @throws NullPointerException The parameter {@code second} is null.
     */
    public EInteger OrNot(EInteger second) {
      if (second == null) {
        throw new NullPointerException("second");
      }
      return this.Or(second.Not());
    }

    /**
     * <p>Does an OR NOT operation between this arbitrary-precision integer and
     * another one.</p> <p>Each arbitrary-precision integer is treated as a
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}) for the purposes of this operator.</p>
     * @param second Another arbitrary-precision integer that participates in the
     * operation.
     * @return An arbitrary-precision integer in which each bit is set if the
     * corresponding bit of this integer is set, the other integer's corresponding
     * bit is not set, or both. For example, in binary, 10110 OR NOT 11010 = 00100
     * (or in decimal, 22 OR NOT 26 = 23). This method uses the two's complement
     * form of negative integers (see {@code com.upokecenter.numbers.EDecimal}).
     * For example, in binary, ...11101110 OR NOT 01011 =...11111110 (or in
     * decimal, -18 OR 11 = -2).
     * @throws NullPointerException The parameter {@code second} is null.
     * @deprecated Does the incorrect implication operation. Use Imply instead.
 */
@Deprecated
    public EInteger Imp(EInteger second) {
      return this.OrNot(second);
    }

    /**
     * <p>Does an implication or IMP operation between this arbitrary-precision
     * integer and another one. Also means SECOND OR NOT FIRST.</p> <p>Each
     * arbitrary-precision integer is treated as a two's-complement form (see
     * {@link com.upokecenter.numbers.EDecimal "Forms of numbers"}) for the
     * purposes of this operator.</p>
     * @param second Another arbitrary-precision integer that participates in the
     * operation.
     * @return An arbitrary-precision integer in which each bit is set if the
     * corresponding bit of the other integer is set, this integer's corresponding
     * bit is not set, or both. For example, in binary, 10110 OR NOT 11010 = 11010
     * IMP 10110 = 00100 (or in decimal, 22 OR NOT 26 = 23). This method uses the
     * two's complement form of negative integers (see {@code
     * com.upokecenter.numbers.EDecimal}). For example, in binary, ...11101110 OR
     * NOT 01011 = 01011 IMP...11101110 =...11111110 (or in decimal, -18 OR 11 =
     * -2).
     * @throws NullPointerException The parameter {@code second} is null.
     */
    private EInteger Imply(EInteger second) {
      if (second == null) {
        throw new NullPointerException("second");
      }
      return second.OrNot(this);
    }

    /**
     * <p>Does an XOR NOT operation (or equivalence operation, EQV operation, or
     * exclusive-OR NOT operation) between this arbitrary-precision integer and
     * another one.</p> <p>Each arbitrary-precision integer is treated as a
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}) for the purposes of this operator.</p>
     * @param second Another arbitrary-precision integer that participates in the
     * operation.
     * @return An arbitrary-precision integer in which each bit is set if the
     * corresponding bit of this integer is set or the other integer's
     * corresponding bit is not set, but not both. For example, in binary, 10110
     * XOR NOT 11010 = 10011 (or in decimal, 22 XOR NOT 26 = 19). This method uses
     * the two's complement form of negative integers (see {@code
     * com.upokecenter.numbers.EDecimal}). For example, in binary, ...11101110 XOR
     * NOT 01011 =...11111010 (or in decimal, -18 OR 11 = -6).
     * @throws NullPointerException The parameter {@code second} is null.
     */
    public EInteger XorNot(EInteger second) {
      if (second == null) {
        throw new NullPointerException("second");
      }
      return this.Xor(second.Not());
    }

    /**
     * <p>Does an XOR NOT operation (or equivalence operation, EQV operation, or
     * exclusive-OR NOT operation) between this arbitrary-precision integer and
     * another one.</p> <p>Each arbitrary-precision integer is treated as a
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}) for the purposes of this operator.</p>
     * @param second Another arbitrary-precision integer that participates in the
     * operation.
     * @return An arbitrary-precision integer in which each bit is set if the
     * corresponding bit of this integer is set or the other integer's
     * corresponding bit is not set, but not both. For example, in binary, 10110
     * XOR NOT 11010 = 10011 (or in decimal, 22 XOR NOT 26 = 19). This method uses
     * the two's complement form of negative integers (see {@code
     * com.upokecenter.numbers.EDecimal}). For example, in binary, ...11101110 XOR
     * NOT 01011 =...11111010 (or in decimal, -18 OR 11 = -6).
     * @throws NullPointerException The parameter {@code second} is null.
     */
    public EInteger Eqv(EInteger second) {
      if (second == null) {
        throw new NullPointerException("second");
      }
      return this.XorNot(second);
    }

    /**
     * Does an exclusive OR (XOR) operation between this arbitrary-precision
     * integer and another one.
     * @param other Another arbitrary-precision integer that participates in the
     * operation.
     * @return An arbitrary-precision integer in which each bit is set if the
     * corresponding bit is set in one input integer but not in the other. For
     * example, in binary, 11010 XOR 01001 = 10011 (or in decimal, 26 XOR 9 = 19).
     * This method uses the two's complement form of negative integers (see {@code
     * com.upokecenter.numbers.EDecimal}). For example, in binary, ...11101101 XOR
     * 00011 =...11101110 (or in decimal, -19 XOR 3 = -18).
     * @throws NullPointerException The parameter {@code other} is null.
     */
    public EInteger Xor(EInteger other) {
      if (other == null) {
        throw new NullPointerException("other");
      }
      if (this.equals(other)) {
        return EInteger.FromInt32(0);
      }
      if (this.wordCount == 0) {
        return other;
      }
      if (other.wordCount == 0) {
        return this;
      }
      if (!this.negative && !other.negative) {
        int smallerCount = Math.min(this.wordCount, other.wordCount);
        int biggerCount = Math.max(this.wordCount, other.wordCount);
        short[] smaller = (this.wordCount == smallerCount) ?
          this.words : other.words;
        short[] bigger = (this.wordCount == smallerCount) ?
          other.words : this.words;
        short[] result = new short[biggerCount];
        for (int i = 0; i < smallerCount; ++i) {
          result[i] = ((short)(smaller[i] ^ bigger[i]));
        }
        System.arraycopy(
          bigger,
          smallerCount,
          result,
          smallerCount,
          biggerCount - smallerCount);
        smallerCount = (smallerCount == biggerCount) ?
          CountWords(result) : biggerCount;

        return (smallerCount == 0) ? EInteger.FromInt32(0) :
          new EInteger(smallerCount, result, false);
      }
      boolean valueXaNegative = false;
      int valueXaWordCount = 0;
      short[] valueXaReg = new short[this.wordCount];
      System.arraycopy(this.words, 0, valueXaReg, 0, valueXaReg.length);
      boolean valueXbNegative = false;
      short[] valueXbReg = new short[other.wordCount];
      System.arraycopy(other.words, 0, valueXbReg, 0, valueXbReg.length);
      valueXaNegative = this.negative;
      valueXaWordCount = this.wordCount;
      valueXbNegative = other.negative;
      valueXaReg = CleanGrow(
          valueXaReg,
          Math.max(valueXaReg.length, valueXbReg.length));
      valueXbReg = CleanGrow(
          valueXbReg,
          Math.max(valueXaReg.length, valueXbReg.length));
      if (valueXaNegative) {
        TwosComplement(valueXaReg, 0, (int)valueXaReg.length);
      }
      if (valueXbNegative) {
        TwosComplement(valueXbReg, 0, (int)valueXbReg.length);
      }
      valueXaNegative ^= valueXbNegative;
      XorWords(valueXaReg, valueXaReg, valueXbReg, (int)valueXaReg.length);
      if (valueXaNegative) {
        TwosComplement(valueXaReg, 0, (int)valueXaReg.length);
      }
      valueXaWordCount = CountWords(valueXaReg);
      return (valueXaWordCount == 0) ? EInteger.FromInt32(0) : new
        EInteger(valueXaWordCount, valueXaReg, valueXaNegative);
    }

    private short[] Copy() {
      short[] words = new short[this.words.length];
      System.arraycopy(this.words, 0, words, 0, this.wordCount);
      return words;
    }

    private static int WordsCompare(
      short[] words,
      int wordCount,
      short[] words2,
      int wordCount2) {
      return WordsCompare(words, 0, wordCount, words2, 0, wordCount2);
    }

    private static int WordsCompare(
      short[] words,
      int pos1,
      int wordCount,
      short[] words2,
      int pos2,
      int wordCount2) {
      // NOTE: Assumes the number is nonnegative
      int size = wordCount;
      if (size == 0) {
        return (wordCount2 == 0) ? 0 : -1;
      } else if (wordCount2 == 0) {
        return 1;
      }
      if (size == wordCount2) {
        if (size == 1 && words[pos1] == words2[pos2]) {
          return 0;
        } else {
          int p1 = pos1 + size - 1;
          int p2 = pos2 + size - 1;
          while ((size--) != 0) {
            int an = ((int)words[p1]) & ShortMask;
            int bn = ((int)words2[p2]) & ShortMask;
            if (an > bn) {
              return 1;
            }
            if (an < bn) {
              return -1;
            }
            --p1;
            --p2;
          }
          return 0;
        }
      }
      return (size > wordCount2) ? 1 : -1;
    }

    private static long WordsToLongUnchecked(short[] words, int wordCount) {
      // NOTE: Assumes the number is nonnegative
      int c = (int)wordCount;
      if (c == 0) {
        return 0L;
      }
      long ivv = 0;
      int intRetValue = ((int)words[0]) & ShortMask;
      if (c > 1) {
        intRetValue |= (((int)words[1]) & ShortMask) << 16;
      }
      if (c > 2) {
        int intRetValue2 = ((int)words[2]) & ShortMask;
        if (c > 3) {
          intRetValue2 |= (((int)words[3]) & ShortMask) << 16;
        }
        ivv = ((long)intRetValue) & 0xffffffffL;
        ivv |= ((long)intRetValue2) << 32;
        return ivv;
      }
      ivv = ((long)intRetValue) & 0xffffffffL;
      return ivv;
    }

    private static boolean WordsEqual(
      short[] words,
      int wordCount,
      short[] words2,
      int wordCount2) {
      // NOTE: Assumes the number is nonnegative
      if (wordCount == wordCount2) {
        for (int i = 0; i < wordCount; ++i) {
          if (words[i] != words2[i]) {
            return false;
          }
        }
        return true;
      }
      return false;
    }

    private static boolean WordsIsEven(short[] words, int wordCount) {
      return wordCount == 0 || (words[0] & 0x01) == 0;
    }

    private static int WordsShiftRightTwo(short[] words, int wordCount) {
      // NOTE: Assumes the number is nonnegative
      if (wordCount != 0) {
        int u;
        int carry = 0;
        for (int i = wordCount - 1; i >= 0; --i) {
          int w = words[i];
          u = ((w & 0xfffc) >> 2) | carry;
          carry = (w << 14) & 0xc000;
          words[i] = ((short)u);
        }
        if (words[wordCount - 1] == 0) {
          --wordCount;
        }
      }
      return wordCount;
    }

    private static int WordsShiftRightEight(short[] words, int wordCount) {
      // NOTE: Assumes the number is nonnegative
      if (wordCount != 0) {
        int u;
        int carry = 0;
        for (int i = wordCount - 1; i >= 0; --i) {
          int w = words[i];
          u = ((w & 0xff00) >> 8) | carry;
          carry = (w << 8) & 0xff00;
          words[i] = ((short)u);
        }
        if (words[wordCount - 1] == 0) {
          --wordCount;
        }
      }
      return wordCount;
    }

    private static int WordsShiftRightFour(short[] words, int wordCount) {
      // NOTE: Assumes the number is nonnegative
      if (wordCount != 0) {
        int u;
        int carry = 0;
        for (int i = wordCount - 1; i >= 0; --i) {
          int w = words[i];
          u = ((w & 0xfff0) >> 4) | carry;
          carry = (w << 12) & 0xf000;
          words[i] = ((short)u);
        }
        if (words[wordCount - 1] == 0) {
          --wordCount;
        }
      }
      return wordCount;
    }

    private static int WordsShiftRightOne(short[] words, int wordCount) {
      // NOTE: Assumes the number is nonnegative
      if (wordCount != 0) {
        int u;
        int carry = 0;
        for (int i = wordCount - 1; i >= 0; --i) {
          int w = words[i];
          u = ((w & 0xfffe) >> 1) | carry;
          carry = (w << 15) & 0x8000;
          words[i] = ((short)u);
        }
        if (words[wordCount - 1] == 0) {
          --wordCount;
        }
      }
      return wordCount;
    }

    private static int WordsSubtract(
      short[] words,
      int wordCount,
      short[] subtrahendWords,
      int subtrahendCount) {
      // NOTE: Assumes this value is at least as high as the subtrahend
      // and both numbers are nonnegative
      short borrow = (short)SubtractInternal(
        words,
        0,
        words,
        0,
        subtrahendWords,
        0,
        subtrahendCount);
      if (borrow != 0) {
        DecrementWords(
          words,
          subtrahendCount,
          (int)(wordCount - subtrahendCount),
          borrow);
      }
      while (wordCount != 0 && words[wordCount - 1] == 0) {
        --wordCount;
      }
      return wordCount;
    }

    /**
     * Returns an arbitrary-precision integer with the bits shifted to the right.
     * For this operation, the arbitrary-precision integer is treated as a
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}). Thus, for negative values, the arbitrary-precision integer is
     * sign-extended.
     * @param numberBits The number of bits to shift. Can be negative, in which
     * case this is the same as shiftLeft with the absolute value of this
     * parameter.
     * @return An arbitrary-precision integer.
     */
    public EInteger ShiftRight(int numberBits) {
      if (numberBits == 0 || this.wordCount == 0) {
        return this;
      }
      if (numberBits < 0) {
        return (numberBits == Integer.MIN_VALUE) ?
          this.ShiftLeft(1).ShiftLeft(Integer.MAX_VALUE) :
          this.ShiftLeft(-numberBits);
      }
      int numWords = (int)this.wordCount;
      int shiftWords = (int)(numberBits >> 4);
      int shiftBits = (int)(numberBits & 15);
      short[] ret;
      int retWordCount;
      if (this.negative) {
        ret = new short[this.words.length];
        System.arraycopy(this.words, 0, ret, 0, numWords);
        TwosComplement(ret, 0, (int)ret.length);
        ShiftWordsRightByWordsSignExtend(ret, 0, numWords, shiftWords);
        if (numWords > shiftWords) {
          ShiftWordsRightByBitsSignExtend(
            ret,
            0,
            numWords - shiftWords,
            shiftBits);
        }
        TwosComplement(ret, 0, (int)ret.length);
        retWordCount = ret.length;
      } else {
        if (shiftWords >= numWords) {
          return EInteger.FromInt32(0);
        }
        ret = new short[this.words.length];
        System.arraycopy(this.words, shiftWords, ret, 0, numWords - shiftWords);
        if (shiftBits != 0) {
          ShiftWordsRightByBits(ret, 0, numWords - shiftWords, shiftBits);
        }
        retWordCount = numWords - shiftWords;
      }
      while (retWordCount != 0 &&
        ret[retWordCount - 1] == 0) {
        --retWordCount;
      }
      if (retWordCount == 0) {
        return EInteger.FromInt32(0);
      }
      if (shiftWords > 2) {
        ret = ShortenArray(ret, retWordCount);
      }
      return new EInteger(retWordCount, ret, this.negative);
    }

    /**
     * Finds the square root of this instance's value, rounded down.
     * @return The square root of this object's value. Returns 0 if this value is 0
     * or less.
     */
    public EInteger Sqrt() {
      EInteger[] srrem = this.SqrtRemInternal(false);
      return srrem[0];
    }

    /**
     * Calculates the square root and the remainder.
     * @return An array of two arbitrary-precision integers: the first integer is
     * the square root, and the second is the difference between this value and the
     * square of the first integer. Returns two zeros if this value is 0 or less,
     * or one and zero if this value equals 1.
     */
    public EInteger[] SqrtRem() {
      return this.SqrtRemInternal(true);
    }

    /**
     * Finds the nth root of this instance's value, rounded down.
     * @param root The root to find; must be 1 or greater. If this value is 2, this
     * method finds the square root; if 3, the cube root, and in general, if N, the
     * N-th root.
     * @return The square root of this object's value. Returns 0 if this value is 0
     * or less.
     * @throws NullPointerException The parameter {@code root} is null.
     */
    public EInteger Root(EInteger root) {
      if (root == null) {
        throw new NullPointerException("root");
      }
      EInteger[] srrem = this.RootRemInternal(root, false);
      return srrem[0];
    }

    /**
     * Calculates the nth root and the remainder.
     * @param root The root to find; must be 1 or greater. If this value is 2, this
     * method finds the square root; if 3, the cube root, and in general, if N, the
     * N-th root.
     * @return An array of two arbitrary-precision integers: the first integer is
     * the nth root, and the second is the difference between this value and the
     * nth power of the first integer. Returns two zeros if this value is 0 or
     * less, or one and zero if this value equals 1.
     * @throws NullPointerException The parameter {@code root} is null.
     */
    public EInteger[] RootRem(EInteger root) {
      if (root == null) {
        throw new NullPointerException("root");
      }
      return this.RootRemInternal(root, true);
    }

    /**
     * Finds the nth root of this instance's value, rounded down.
     * @param root The root to find; must be 1 or greater. If this value is 2, this
     * method finds the square root; if 3, the cube root, and in general, if N, the
     * N-th root.
     * @return The square root of this object's value. Returns 0 if this value is 0
     * or less.
     */
    public EInteger Root(int root) {
      EInteger[] srrem = this.RootRemInternal(EInteger.FromInt32(root), false);
      return srrem[0];
    }

    /**
     * Calculates the nth root and the remainder.
     * @param root The root to find; must be 1 or greater. If this value is 2, this
     * method finds the square root; if 3, the cube root, and in general, if N, the
     * N-th root.
     * @return An array of two arbitrary-precision integers: the first integer is
     * the nth root, and the second is the difference between this value and the
     * nth power of the first integer. Returns two zeros if this value is 0 or
     * less, or one and zero if this value equals 1.
     */
    public EInteger[] RootRem(int root) {
      return this.RootRemInternal(EInteger.FromInt32(root), true);
    }

    /**
     * Subtracts an arbitrary-precision integer from this arbitrary-precision
     * integer and returns the result.
     * @param subtrahend Another arbitrary-precision integer.
     * @return The difference between the two numbers, that is, this
     * arbitrary-precision integer minus another arbitrary-precision integer.
     * @throws NullPointerException The parameter {@code subtrahend} is null.
     */
    public EInteger Subtract(EInteger subtrahend) {
      if (subtrahend == null) {
        throw new NullPointerException("subtrahend");
      }
      return (this.wordCount == 0) ? subtrahend.Negate() :
        ((subtrahend.wordCount == 0) ? this : this.Add(subtrahend.Negate()));
    }

    /**
     * Returns a byte array of this integer's value. The byte array will take the
     * number's two's-complement form (see {@link com.upokecenter.numbers.EDecimal
     * "Forms of numbers"}), using the fewest bytes necessary to store its value
     * unambiguously. If this value is negative, the bits that appear beyond the
     * most significant bit of the number will be all ones. The resulting byte
     * array can be passed to the {@code FromBytes()} method (with the same byte
     * order) to reconstruct this integer's value.
     * @param littleEndian See the 'littleEndian' parameter of the {@code
     * FromBytes()} method.
     * @return A byte array. If this value is 0, returns a byte array with the
     * single element 0.
     */
    public byte[] ToBytes(boolean littleEndian) {
      int sign = this.signum();
      if (sign == 0) {
        return new byte[] { (byte)0 };
      }
      if (sign > 0) {
        int byteCount = this.ByteCount();
        int byteArrayLength = byteCount;
        if (this.GetUnsignedBit((byteCount * 8) - 1)) {
          ++byteArrayLength;
        }
        byte[] bytes = new byte[byteArrayLength];
        int j = 0;
        for (int i = 0; i < byteCount; i += 2, j++) {
          int index = littleEndian ? i : bytes.length - 1 - i;
          int index2 = littleEndian ? i + 1 : bytes.length - 2 - i;
          bytes[index] = (byte)(this.words[j] & 0xff);
          if (index2 >= 0 && index2 < byteArrayLength) {
            bytes[index2] = (byte)((this.words[j] >> 8) & 0xff);
          }
        }
        return bytes;
      } else {
        short[] regdata = new short[this.words.length];
        System.arraycopy(this.words, 0, regdata, 0, this.words.length);
        TwosComplement(regdata, 0, (int)regdata.length);
        int byteCount = regdata.length * 2;
        for (int i = regdata.length - 1; i >= 0; --i) {
          if (regdata[i] == ((short)0xffff)) {
            byteCount -= 2;
          } else if ((regdata[i] & 0xff80) == 0xff80) {
            // signed first byte, 0xff second
            --byteCount;
            break;
          } else if ((regdata[i] & 0x8000) == 0x8000) {
            // signed second byte
            break;
          } else {
            // unsigned second byte
            ++byteCount;
            break;
          }
        }
        if (byteCount == 0) {
          byteCount = 1;
        }
        byte[] bytes = new byte[byteCount];
        bytes[littleEndian ? bytes.length - 1 : 0] = (byte)0xff;
        byteCount = Math.min(byteCount, regdata.length * 2);
        int j = 0;
        for (int i = 0; i < byteCount; i += 2, j++) {
          int index = littleEndian ? i : bytes.length - 1 - i;
          int index2 = littleEndian ? i + 1 : bytes.length - 2 - i;
          bytes[index] = (byte)(regdata[j] & 0xff);
          if (index2 >= 0 && index2 < byteCount) {
            bytes[index2] = (byte)((regdata[j] >> 8) & 0xff);
          }
        }
        return bytes;
      }
    }

    /**
     * Converts this object's value to a 32-bit signed integer, throwing an
     * exception if it can't fit.
     * @return A 32-bit signed integer.
     * @throws ArithmeticException This object's value is too big to fit a
     * 32-bit signed integer.
     */
    public int ToInt32Checked() {
      int count = this.wordCount;
      if (count == 0) {
        return 0;
      }
      if (count > 2) {
        throw new ArithmeticException();
      }
      if (count == 2 && (this.words[1] & 0x8000) != 0) {
        if (this.negative && this.words[1] == ((short)0x8000) &&
          this.words[0] == 0) {
          return Integer.MIN_VALUE;
        }
        throw new ArithmeticException();
      }
      return this.ToInt32Unchecked();
    }

    /**
     * Converts this object's value to a 32-bit signed integer. If the value can't
     * fit in a 32-bit integer, returns the lower 32 bits of this object's
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}) (in which case the return value might have a different sign than
     * this object's value).
     * @return A 32-bit signed integer.
     */
    public int ToInt32Unchecked() {
      int c = (int)this.wordCount;
      if (c == 0) {
        return 0;
      }
      int intRetValue = ((int)this.words[0]) & ShortMask;
      if (c > 1) {
        intRetValue |= (((int)this.words[1]) & ShortMask) << 16;
      }
      if (this.negative) {
        intRetValue = (intRetValue - 1);
        intRetValue = (~intRetValue);
      }
      return intRetValue;
    }

    /**
     * Converts this object's value to a 64-bit signed integer, throwing an
     * exception if it can't fit.
     * @return A 64-bit signed integer.
     * @throws ArithmeticException This object's value is too big to fit a
     * 64-bit signed integer.
     */
    public long ToInt64Checked() {
      int count = this.wordCount;
      if (count == 0) {
        return 0L;
      }
      if (count > 4) {
        throw new ArithmeticException();
      }
      if (count == 4 && (this.words[3] & 0x8000) != 0) {
        if (this.negative && this.words[3] == ((short)0x8000) &&
          this.words[2] == 0 && this.words[1] == 0 &&
          this.words[0] == 0) {
          return Long.MIN_VALUE;
        }
        throw new ArithmeticException();
      }
      return this.ToInt64Unchecked();
    }

    /**
     * Converts this object's value to a 64-bit signed integer. If the value can't
     * fit in a 64-bit integer, returns the lower 64 bits of this object's
     * two's-complement form (see {@link com.upokecenter.numbers.EDecimal "Forms of
     * numbers"}) (in which case the return value might have a different sign than
     * this object's value).
     * @return A 64-bit signed integer.
     */
    public long ToInt64Unchecked() {
      int c = (int)this.wordCount;
      if (c == 0) {
        return 0L;
      }
      long ivv = 0;
      int intRetValue = ((int)this.words[0]) & ShortMask;
      if (c > 1) {
        intRetValue |= (((int)this.words[1]) & ShortMask) << 16;
      }
      if (c > 2) {
        int intRetValue2 = ((int)this.words[2]) & ShortMask;
        if (c > 3) {
          intRetValue2 |= (((int)this.words[3]) & ShortMask) << 16;
        }
        if (this.negative) {
          if (intRetValue == 0) {
            intRetValue = (intRetValue - 1);
            intRetValue2 = (intRetValue2 - 1);
          } else {
            intRetValue = (intRetValue - 1);
          }
          intRetValue = (~intRetValue);
          intRetValue2 = (~intRetValue2);
        }
        ivv = ((long)intRetValue) & 0xffffffffL;
        ivv |= ((long)intRetValue2) << 32;
        return ivv;
      }
      ivv = ((long)intRetValue) & 0xffffffffL;
      if (this.negative) {
        ivv = -ivv;
      }
      return ivv;
    }

    // Estimated number of base-N digits, divided by 8 (or estimated
    // number of half-digits divided by 16) contained in each 16-bit
    // word of an EInteger. Used in divide-and-conquer to guess
    // the power-of-base needed to split an EInteger by roughly half.
    // Calculated from: ln(65536)*(16/2)/ln(base)
    private static int[] estimatedHalfDigitCountPerWord = {
      0, 0,
      128, 80, 64, 55, 49, 45, 42, 40, 38, 37, 35, 34, 33,
      32, 32, 31, 30, 30, 29, 29, 28, 28, 27, 27, 27, 26,
      26, 26, 26, 25, 25, 25, 25, 24, 24,
    };

    private void ToRadixStringGeneral(
      StringBuilder outputSB,
      int radix) {
      int i = 0;
      if (this.wordCount >= 100) {
        StringBuilder rightBuilder = new StringBuilder();
        long digits = ((long)estimatedHalfDigitCountPerWord[radix] *
          this.wordCount) / 16;
        EInteger pow = null;
        if (radix == 10) {
          pow = NumberUtility.FindPowerOfTen(digits);
        } else {
          pow = (radix == 5) ?
            NumberUtility.FindPowerOfFiveFromBig(EInteger.FromInt64(digits)) :
            EInteger.FromInt32(radix).Pow(EInteger.FromInt64(digits));
        }
        EInteger[] divrem = this.DivRem(pow);
        // System.out.println("divrem wc=" + divrem[0].wordCount + " wc=" + (//
        // divrem[1].wordCount));
        divrem[0].ToRadixStringGeneral(outputSB, radix);
        divrem[1].ToRadixStringGeneral(rightBuilder, radix);
        for (i = rightBuilder.length(); i < digits; ++i) {
          outputSB.append('0');
        }
        outputSB.append(rightBuilder.toString());
        return;
      }
      char[] s;
      short[] tempReg;
      int numWordCount;
      if (radix == 10) {
        if (this.CanFitInInt64()) {
          outputSB.append(FastInteger.LongToString(this.ToInt64Unchecked()));
          return;
        }
        tempReg = new short[this.wordCount];
        System.arraycopy(this.words, 0, tempReg, 0, tempReg.length);
        numWordCount = tempReg.length;
        while (numWordCount != 0 && tempReg[numWordCount - 1] == 0) {
          --numWordCount;
        }
        s = new char[(numWordCount << 4) + 1];
        while (numWordCount != 0) {
          if (numWordCount == 1 && tempReg[0] > 0 && tempReg[0] <= 0x7fff) {
            int rest = tempReg[0];
            while (rest != 0) {
              // accurate approximation to rest/10 up to 43698,
              // and rest can go up to 32767
              int newrest = (rest * 26215) >> 18;
              s[i++] = Digits.charAt(rest - (newrest * 10));
              rest = newrest;
            }
            break;
          }
          if (numWordCount == 2 && tempReg[1] > 0 && tempReg[1] <= 0x7fff) {
            int rest = ((int)tempReg[0]) & ShortMask;
            rest |= (((int)tempReg[1]) & ShortMask) << 16;
            while (rest != 0) {
              int newrest = (rest < 81920) ? (((rest * 52429) >> 19) & 8191) :
                (rest / 10);
              s[i++] = Digits.charAt(rest - (newrest * 10));
              rest = newrest;
            }
            break;
          } else {
            int wci = numWordCount;
            short remainderShort = 0;
            int quo, rem;
            // Divide by 10000
            while ((wci--) > 0) {
              int currentDividend = ((int)((((int)tempReg[wci]) &
                0xffff) | ((int)remainderShort << 16)));
              quo = currentDividend / 10000;
              tempReg[wci] = ((short)quo);
              rem = currentDividend - (10000 * quo);
              remainderShort = ((short)rem);
            }
            int remainderSmall = remainderShort;
            // Recalculate word count
            while (numWordCount != 0 && tempReg[numWordCount - 1] == 0) {
              --numWordCount;
            }
            // accurate approximation to rest/10 up to 16388,
            // and rest can go up to 9999
            int newrest = (remainderSmall * 3277) >> 15;
            s[i++] = Digits.charAt((int)(remainderSmall - (newrest * 10)));
            remainderSmall = newrest;
            newrest = (remainderSmall * 3277) >> 15;
            s[i++] = Digits.charAt((int)(remainderSmall - (newrest * 10)));
            remainderSmall = newrest;
            newrest = (remainderSmall * 3277) >> 15;
            s[i++] = Digits.charAt((int)(remainderSmall - (newrest * 10)));
            remainderSmall = newrest;
            s[i++] = Digits.charAt(remainderSmall);
          }
        }
        ReverseChars(s, 0, i);
        outputSB.append(s, 0, i);
        return;
      }
      tempReg = new short[this.wordCount];
      System.arraycopy(this.words, 0, tempReg, 0, tempReg.length);
      numWordCount = tempReg.length;
      while (numWordCount != 0 && tempReg[numWordCount - 1] == 0) {
        --numWordCount;
      }
      i = 0;
      s = new char[(numWordCount << 4) + 1];
      while (numWordCount != 0) {
        if (numWordCount == 1 && tempReg[0] > 0 && tempReg[0] <= 0x7fff) {
          int rest = tempReg[0];
          while (rest != 0) {
            int newrest = rest / radix;
            s[i++] = Digits.charAt(rest - (newrest * radix));
            rest = newrest;
          }
          break;
        }
        if (numWordCount == 2 && tempReg[1] > 0 && tempReg[1] <= 0x7fff) {
          int rest = ((int)tempReg[0]) & ShortMask;
          rest |= (((int)tempReg[1]) & ShortMask) << 16;
          while (rest != 0) {
            int newrest = rest / radix;
            s[i++] = Digits.charAt(rest - (newrest * radix));
            rest = newrest;
          }
          break;
        } else {
          int wci = numWordCount;
          short remainderShort = 0;
          int quo, rem;
          // Divide by radix
          while ((wci--) > 0) {
            int currentDividend = ((int)((((int)tempReg[wci]) &
              0xffff) | ((int)remainderShort << 16)));
            quo = currentDividend / radix;
            tempReg[wci] = ((short)quo);
            rem = currentDividend - (radix * quo);
            remainderShort = ((short)rem);
          }
          int remainderSmall = remainderShort;
          // Recalculate word count
          while (numWordCount != 0 && tempReg[numWordCount - 1] == 0) {
            --numWordCount;
          }
          s[i++] = Digits.charAt(remainderSmall);
        }
      }
      ReverseChars(s, 0, i);
      outputSB.append(s, 0, i);
    }

    /**
     * Generates a string representing the value of this object, in the specified
     * radix.
     * @param radix A radix from 2 through 36. For example, to generate a
     * hexadecimal (base-16) string, specify 16. To generate a decimal (base-10)
     * string, specify 10.
     * @return A string representing the value of this object. If this value is 0,
     * returns "0". If negative, the string will begin with a minus sign ("-",
     * U+002D). Depending on the radix, the string will use the basic digits 0 to 9
     * (U+0030 to U+0039) and then the basic uppercase letters A to Z (U+0041 to
     * U+005A). For example, 0-9 in radix 10, and 0-9, then A-F in radix 16.
     */
    public String ToRadixString(int radix) {
      if (radix < 2) {
        throw new IllegalArgumentException("radix(" + radix +
          ") is less than 2");
      }
      if (radix > 36) {
        throw new IllegalArgumentException("radix(" + radix +
          ") is more than 36");
      }
      if (this.wordCount == 0) {
        return "0";
      }
      if (radix == 10) {
        // Decimal
        if (this.CanFitInInt64()) {
          return FastInteger.LongToString(this.ToInt64Unchecked());
        }
        StringBuilder sb = new StringBuilder();
        if (this.negative) {
          sb.append('-');
        }
        this.Abs().ToRadixStringGeneral(sb, radix);
        return sb.toString();
      }
      if (radix == 16) {
        // Hex
        StringBuilder sb = new StringBuilder();
        if (this.negative) {
          sb.append('-');
        }
        boolean firstBit = true;
        int word = this.words[this.wordCount - 1];
        for (int i = 0; i < 4; ++i) {
          if (!firstBit || (word & 0xf000) != 0) {
            sb.append(Digits.charAt((word >> 12) & 0x0f));
            firstBit = false;
          }
          word <<= 4;
        }
        for (int j = this.wordCount - 2; j >= 0; --j) {
          word = this.words[j];
          for (int i = 0; i < 4; ++i) {
            sb.append(Digits.charAt((word >> 12) & 0x0f));
            word <<= 4;
          }
        }
        return sb.toString();
      }
      if (radix == 2) {
        // Binary
        StringBuilder sb = new StringBuilder();
        if (this.negative) {
          sb.append('-');
        }
        boolean firstBit = true;
        int word = this.words[this.wordCount - 1];
        for (int i = 0; i < 16; ++i) {
          if (!firstBit || (word & 0x8000) != 0) {
            sb.append((word & 0x8000) == 0 ? '0' : '1');
            firstBit = false;
          }
          word <<= 1;
        }
        for (int j = this.wordCount - 2; j >= 0; --j) {
          word = this.words[j];
          for (int i = 0; i < 16; ++i) {
            sb.append((word & 0x8000) == 0 ? '0' : '1');
            word <<= 1;
          }
        }
        return sb.toString();
      } else {
        // Other radixes
        StringBuilder sb = new StringBuilder();
        if (this.negative) {
          sb.append('-');
        }
        this.Abs().ToRadixStringGeneral(sb, radix);
        return sb.toString();
      }
    }

    /**
     * Converts this object to a text string in base 10.
     * @return A string representation of this object. If this value is 0, returns
     * "0". If negative, the string will begin with a minus sign ("-", U+002D). The
     * string will use the basic digits 0 to 9 (U+0030 to U+0039).
     */
    @Override public String toString() {
      if (this.isZero()) {
        return "0";
      }
      return this.CanFitInInt64() ?
        FastInteger.LongToString(this.ToInt64Unchecked()) :
        this.ToRadixString(10);
    }

    private static int AddInternal(
      short[] c,
      int cstart,
      short[] words1,
      int astart,
      short[] words2,
      int bstart,
      int n) {
      {
        int u;
        u = 0;
        boolean evn = (n & 1) == 0;
        int valueNEven = evn ? n : n - 1;
        int i = 0;
        while (i < valueNEven) {
          u = (((int)words1[astart + i]) & ShortMask) +
            (((int)words2[bstart + i]) & ShortMask) + (u >> 16);
          c[cstart + i] = (short)u;
          ++i;
          u = (((int)words1[astart + i]) & ShortMask) +
            (((int)words2[bstart + i]) & ShortMask) + (u >> 16);
          c[cstart + i] = (short)u;
          ++i;
        }
        if (!evn) {
          u = (((int)words1[astart + valueNEven]) & ShortMask) +
            (((int)words2[bstart + valueNEven]) & ShortMask) + (u >> 16);
          c[cstart + valueNEven] = (short)u;
        }
        return u >> 16;
      }
    }

    private static int AddUnevenSize(
      short[] c,
      int cstart,
      short[] wordsBigger,
      int astart,
      int acount,
      short[] wordsSmaller,
      int bstart,
      int bcount) {
      {
        int u;
        u = 0;
        for (int i = 0; i < bcount; i += 1) {
          u = (((int)wordsBigger[astart + i]) & ShortMask) +
            (((int)wordsSmaller[bstart + i]) & ShortMask) + (short)(u >> 16);
          c[cstart + i] = (short)u;
        }
        for (int i = bcount; i < acount; i += 1) {
          u = (((int)wordsBigger[astart + i]) & ShortMask) + (short)(u >> 16);
          c[cstart + i] = (short)u;
        }
        return ((int)u >> 16) & ShortMask;
      }
    }

    // Multiplies two operands of different sizes
    private static void AsymmetricMultiply(
      short[] resultArr,
      int resultStart, // uses words1Count + words2Count
      short[] tempArr,
      int tempStart, // uses words1Count + words2Count
      short[] words1,
      int words1Start,
      int words1Count,
      short[] words2,
      int words2Start,
      int words2Count) {
      // System.out.println("AsymmetricMultiply " + words1Count + " " +
      // words2Count + " [r=" + resultStart + " t=" + tempStart + " a=" +
      // words1Start + " b=" + words2Start + "]");

      if (words1Count == words2Count) {
        if (words1Start == words2Start && words1 == words2) {
          // Both operands have the same value and the same word count
          RecursiveSquare(
            resultArr,
            resultStart,
            tempArr,
            tempStart,
            words1,
            words1Start,
            words1Count);
        } else if (words1Count == 2) {
          // Both operands have a word count of 2
          BaselineMultiply2(
            resultArr,
            resultStart,
            words1,
            words1Start,
            words2,
            words2Start);
        } else {
          // Other cases where both operands have the same word count
          SameSizeMultiply(
            resultArr,
            resultStart,
            tempArr,
            tempStart,
            words1,
            words1Start,
            words2,
            words2Start,
            words1Count);
        }
        return;
      }
      if (words1Count > words2Count) {
        // Ensure that words1 is smaller by swapping if necessary
        short[] tmp1 = words1;
        words1 = words2;
        words2 = tmp1;
        int tmp3 = words1Start;
        words1Start = words2Start;
        words2Start = tmp3;
        int tmp2 = words1Count;
        words1Count = words2Count;
        words2Count = tmp2;
      }

      if (words1Count == 1 || (words1Count == 2 && words1[words1Start + 1] ==
        0)) {
        switch (words1[words1Start]) {
          case 0:
            // words1 is zero, so result is 0
            java.util.Arrays.fill(resultArr, resultStart, (resultStart)+(words2Count + 2), (short)0);
            return;
          case 1:
            System.arraycopy(
              words2,
              words2Start,
              resultArr,
              resultStart,
              (int)words2Count);
            resultArr[resultStart + words2Count] = (short)0;
            resultArr[resultStart + words2Count + 1] = (short)0;
            return;
          default:
            resultArr[resultStart + words2Count] = LinearMultiply(
                resultArr,
                resultStart,
                words2,
                words2Start,
                words1[words1Start],
                words2Count);
            resultArr[resultStart + words2Count + 1] = (short)0;
            return;
        }
      }
      if (words1Count == 2 && (words2Count & 1) == 0) {
        int a0 = ((int)words1[words1Start]) & ShortMask;
        int a1 = ((int)words1[words1Start + 1]) & ShortMask;
        resultArr[resultStart + words2Count] = (short)0;
        resultArr[resultStart + words2Count + 1] = (short)0;
        AtomicMultiplyOpt(
          resultArr,
          resultStart,
          a0,
          a1,
          words2,
          words2Start,
          0,
          words2Count);
        AtomicMultiplyAddOpt(
          resultArr,
          resultStart,
          a0,
          a1,
          words2,
          words2Start,
          2,
          words2Count);
        return;
      }
      if (words1Count <= MultRecursionThreshold && words2Count <=
        MultRecursionThreshold) {
        SchoolbookMultiply(
          resultArr,
          resultStart,
          words1,
          words1Start,
          words1Count,
          words2,
          words2Start,
          words2Count);
      } else if (words1Count >= Toom4Threshold && words2Count >=
        Toom4Threshold) {
        Toom4(
          resultArr,
          resultStart,
          words1,
          words1Start,
          words1Count,
          words2,
          words2Start,
          words2Count);
      } else if (words1Count >= Toom3Threshold && words2Count >=
        Toom3Threshold) {
        Toom3(
          resultArr,
          resultStart,
          words1,
          words1Start,
          words1Count,
          words2,
          words2Start,
          words2Count);
      } else {
        int wordsRem = words2Count % words1Count;
        int evenmult = (words2Count / words1Count) & 1;
        int i;
        // System.out.println("counts=" + words1Count + "," + words2Count +
        // " res=" + (resultStart + words1Count) + " temp=" + (tempStart +
        // (words1Count << 1)) + " rem=" + wordsRem + " evenwc=" + evenmult);
        if (wordsRem == 0) {
          // words2Count is divisible by words1count
          if (evenmult == 0) {
            SameSizeMultiply(
              resultArr,
              resultStart,
              tempArr,
              tempStart,
              words1,
              words1Start,
              words2,
              words2Start,
              words1Count);
            System.arraycopy(
              resultArr,
              resultStart + words1Count,
              tempArr,
              (int)(tempStart + (words1Count << 1)),
              words1Count);
            for (i = words1Count << 1; i < words2Count; i += words1Count << 1) {
              SameSizeMultiply(
                tempArr,
                tempStart + words1Count + i,
                tempArr,
                tempStart,
                words1,
                words1Start,
                words2,
                words2Start + i,
                words1Count);
            }
            for (i = words1Count; i < words2Count; i += words1Count << 1) {
              SameSizeMultiply(
                resultArr,
                resultStart + i,
                tempArr,
                tempStart,
                words1,
                words1Start,
                words2,
                words2Start + i,
                words1Count);
            }
          } else {
            for (i = 0; i < words2Count; i += words1Count << 1) {
              SameSizeMultiply(
                resultArr,
                resultStart + i,
                tempArr,
                tempStart,
                words1,
                words1Start,
                words2,
                words2Start + i,
                words1Count);
            }
            for (i = words1Count; i < words2Count; i += words1Count << 1) {
              SameSizeMultiply(
                tempArr,
                tempStart + words1Count + i,
                tempArr,
                tempStart,
                words1,
                words1Start,
                words2,
                words2Start + i,
                words1Count);
            }
          }
          if (
            AddInternal(
              resultArr,
              resultStart + words1Count,
              resultArr,
              resultStart + words1Count,
              tempArr,
              tempStart + (words1Count << 1),
              words2Count - words1Count) != 0) {
            IncrementWords(
              resultArr,
              (int)(resultStart + words2Count),
              words1Count,
              (short)1);
          }
        } else if ((words1Count + words2Count) >= (words1Count << 2)) {
          // System.out.println("Chunked Linear Multiply long");
          ChunkedLinearMultiply(
            resultArr,
            resultStart,
            tempArr,
            tempStart,
            words2,
            words2Start,
            words2Count,
            words1,
            words1Start,
            words1Count);
        } else if (words1Count + 1 == words2Count ||
          (words1Count + 2 == words2Count && words2[words2Start +
              words2Count - 1] == 0)) {
          java.util.Arrays.fill(resultArr, resultStart, (resultStart)+(words1Count + words2Count), (short)0);
          // Multiply the low parts of each operand
          SameSizeMultiply(
            resultArr,
            resultStart,
            tempArr,
            tempStart,
            words1,
            words1Start,
            words2,
            words2Start,
            words1Count);
          // Multiply the high parts
          // while adding carry from the high part of the product
          short carry = LinearMultiplyAdd(
              resultArr,
              resultStart + words1Count,
              words1,
              words1Start,
              words2[words2Start + words1Count],
              words1Count);
          resultArr[resultStart + words1Count + words1Count] = carry;
        } else {
          short[] t2 = new short[words1Count << 2];
          // System.out.println("Chunked Linear Multiply Short");
          ChunkedLinearMultiply(
            resultArr,
            resultStart,
            t2,
            0,
            words2,
            words2Start,
            words2Count,
            words1,
            words1Start,
            words1Count);
        }
      }
    }

    private static void AtomicMultiplyAddOpt(
      short[] c,
      int valueCstart,
      int valueA0,
      int valueA1,
      short[] words2,
      int words2Start,
      int istart,
      int iend) {
      short s;
      int d;
      int first1MinusFirst0 = ((int)valueA1 - valueA0) & ShortMask;
      valueA1 &= 0xffff;
      valueA0 &= 0xffff;
      {
        if (valueA1 >= valueA0) {
          for (int i = istart; i < iend; i += 4) {
            int b0 = ((int)words2[words2Start + i]) & ShortMask;
            int b1 = ((int)words2[words2Start + i + 1]) & ShortMask;
            int csi = valueCstart + i;
            if (b0 >= b1) {
              s = (short)0;
              d = first1MinusFirst0 * (((int)b0 - b1) & ShortMask);
            } else {
              s = (short)first1MinusFirst0;
              d = (((int)s) & ShortMask) * (((int)b0 - b1) & ShortMask);
            }
            int valueA0B0 = valueA0 * b0;
            int a0b0high = (valueA0B0 >> 16) & ShortMask;
            int tempInt;
            tempInt = valueA0B0 + (((int)c[csi]) & ShortMask);
            c[csi] = (short)(((int)tempInt) & ShortMask);

            int valueA1B1 = valueA1 * b1;
            int a1b1low = valueA1B1 & ShortMask;
            int a1b1high = ((int)(valueA1B1 >> 16)) & ShortMask;
            tempInt = (((int)(tempInt >> 16)) & ShortMask) +
              (((int)valueA0B0) & 0xffff) + (((int)d) & ShortMask) + a1b1low +
              (((int)c[csi + 1]) & ShortMask);
            c[csi + 1] = (short)(((int)tempInt) & ShortMask);

            tempInt = (((int)(tempInt >> 16)) & ShortMask) + a1b1low +
              a0b0high + (((int)(d >> 16)) & ShortMask) +
              a1b1high - (((int)s) & ShortMask) + (((int)c[csi + 2]) &
              ShortMask);
            c[csi + 2] = (short)(((int)tempInt) & ShortMask);

            tempInt = (((int)(tempInt >> 16)) & ShortMask) + a1b1high +
              (((int)c[csi + 3]) & ShortMask);
            c[csi + 3] = (short)(((int)tempInt) & ShortMask);
            if ((tempInt >> 16) != 0) {
              ++c[csi + 4];
              c[csi + 5] += (short)((c[csi + 4] == 0) ? 1 : 0);
            }
          }
        } else {
          for (int i = istart; i < iend; i += 4) {
            int valueB0 = ((int)words2[words2Start + i]) & ShortMask;
            int valueB1 = ((int)words2[words2Start + i + 1]) & ShortMask;
            int csi = valueCstart + i;
            if (valueB0 > valueB1) {
              s = (short)(((int)valueB0 - valueB1) & ShortMask);
              d = first1MinusFirst0 * (((int)s) & ShortMask);
            } else {
              s = (short)0;
              d = (((int)valueA0 - valueA1) & ShortMask) * (((int)valueB1 -
                valueB0) & ShortMask);
            }
            int valueA0B0 = valueA0 * valueB0;
            int a0b0high = (valueA0B0 >> 16) & ShortMask;
            int tempInt;
            tempInt = valueA0B0 + (((int)c[csi]) & ShortMask);
            c[csi] = (short)(((int)tempInt) & ShortMask);

            int valueA1B1 = valueA1 * valueB1;
            int a1b1low = valueA1B1 & ShortMask;
            int a1b1high = (valueA1B1 >> 16) & ShortMask;
            tempInt = (((int)(tempInt >> 16)) & ShortMask) +
              (((int)valueA0B0) & 0xffff) + (((int)d) & ShortMask) + a1b1low +
              (((int)c[csi + 1]) & ShortMask);
            c[csi + 1] = (short)(((int)tempInt) & ShortMask);

            tempInt = (((int)(tempInt >> 16)) & ShortMask) + a1b1low +
              a0b0high + (((int)(d >> 16)) & ShortMask) +
              a1b1high - (((int)s) & ShortMask) + (((int)c[csi + 2]) &
              ShortMask);
            c[csi + 2] = (short)(((int)tempInt) & ShortMask);

            tempInt = (((int)(tempInt >> 16)) & ShortMask) + a1b1high +
              (((int)c[csi + 3]) & ShortMask);
            c[csi + 3] = (short)(((int)tempInt) & ShortMask);
            if ((tempInt >> 16) != 0) {
              ++c[csi + 4];
              c[csi + 5] += (short)((c[csi + 4] == 0) ? 1 : 0);
            }
          }
        }
      }
    }

    private static void AtomicMultiplyOpt(
      short[] c,
      int valueCstart,
      int valueA0,
      int valueA1,
      short[] words2,
      int words2Start,
      int istart,
      int iend) {
      short s;
      int d;
      int first1MinusFirst0 = ((int)valueA1 - valueA0) & ShortMask;
      valueA1 &= 0xffff;
      valueA0 &= 0xffff;
      {
        if (valueA1 >= valueA0) {
          for (int i = istart; i < iend; i += 4) {
            int valueB0 = ((int)words2[words2Start + i]) & ShortMask;
            int valueB1 = ((int)words2[words2Start + i + 1]) & ShortMask;
            int csi = valueCstart + i;
            if (valueB0 >= valueB1) {
              s = (short)0;
              d = first1MinusFirst0 * (((int)valueB0 - valueB1) & ShortMask);
            } else {
              s = (short)first1MinusFirst0;
              d = (((int)s) & ShortMask) * (((int)valueB0 - valueB1) &
                ShortMask);
            }
            int valueA0B0 = valueA0 * valueB0;
            c[csi] = (short)(((int)valueA0B0) & ShortMask);
            int a0b0high = (valueA0B0 >> 16) & ShortMask;
            int valueA1B1 = valueA1 * valueB1;
            int tempInt;
            tempInt = a0b0high + (((int)valueA0B0) & ShortMask) + (((int)d) &
              0xffff) + (((int)valueA1B1) & ShortMask);
            c[csi + 1] = (short)tempInt;
            tempInt = valueA1B1 + (((int)(tempInt >> 16)) & ShortMask) +
              a0b0high + (((int)(d >> 16)) & ShortMask) + (((int)(valueA1B1 >>
              16)) & ShortMask) - (((int)s) & ShortMask);
            c[csi + 2] = (short)tempInt;
            tempInt >>= 16;
            c[csi + 3] = (short)tempInt;
          }
        } else {
          for (int i = istart; i < iend; i += 4) {
            int valueB0 = ((int)words2[words2Start + i]) & ShortMask;
            int valueB1 = ((int)words2[words2Start + i + 1]) & ShortMask;
            int csi = valueCstart + i;
            if (valueB0 > valueB1) {
              s = (short)(((int)valueB0 - valueB1) & ShortMask);
              d = first1MinusFirst0 * (((int)s) & ShortMask);
            } else {
              s = (short)0;
              d = (((int)valueA0 - valueA1) & ShortMask) * (((int)valueB1 -
                valueB0) & ShortMask);
            }
            int valueA0B0 = valueA0 * valueB0;
            int a0b0high = (valueA0B0 >> 16) & ShortMask;
            c[csi] = (short)(((int)valueA0B0) & ShortMask);

            int valueA1B1 = valueA1 * valueB1;
            int tempInt;
            tempInt = a0b0high + (((int)valueA0B0) & ShortMask) + (((int)d) &
              0xffff) + (((int)valueA1B1) & ShortMask);
            c[csi + 1] = (short)tempInt;

            tempInt = valueA1B1 + (((int)(tempInt >> 16)) & ShortMask) +
              a0b0high + (((int)(d >> 16)) & ShortMask) + (((int)(valueA1B1 >>
              16)) & ShortMask) - (((int)s) & ShortMask);

            c[csi + 2] = (short)tempInt;
            tempInt >>= 16;
            c[csi + 3] = (short)tempInt;
          }
        }
      }
    }
    // Multiplies two words by two words with overflow checking
    private static void BaselineMultiply2(
      short[] result,
      int rstart,
      short[] words1,
      int astart,
      short[] words2,
      int bstart) {
      {
        int p;
        short c;
        int d;
        int a0 = ((int)words1[astart]) & ShortMask;
        int a1 = ((int)words1[astart + 1]) & ShortMask;
        int b0 = ((int)words2[bstart]) & ShortMask;
        int b1 = ((int)words2[bstart + 1]) & ShortMask;
        p = a0 * b0;
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        result[rstart] = c;
        c = (short)d;
        d = ((int)d >> 16) & ShortMask;
        p = a0 * b1;
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        p = a1 * b0;
        p += ((int)c) & ShortMask;
        d += ((int)p >> 16) & ShortMask;
        result[rstart + 1] = (short)p;
        p = a1 * b1;
        p += d;
        result[rstart + 2] = (short)p;
        result[rstart + 3] = (short)(p >> 16);
      }
    }

    // Multiplies four words by four words with overflow checking
    private static void BaselineMultiply4(
      short[] result,
      int rstart,
      short[] words1,
      int astart,
      short[] words2,
      int bstart) {
      long p;
      int c;
      long d;
      {
        // System.out.println("ops={0:X4}{1:X4}{2:X4}{3:X4} {4:X4}{5:X4}{6:X4}{7:X4}",
        // words1[astart + 3], words1[astart + 2], words1[astart + 1], words1[astart],
        // words2[bstart + 3], words2[bstart + 2], words2[bstart + 1],
        // words2[bstart]);
        long a0 = ((long)words1[astart]) & 0xffffL;
        a0 |= (((long)words1[astart + 1]) & 0xffffL) << 16;
        long a1 = ((long)words1[astart + 2]) & 0xffffL;
        a1 |= (((long)words1[astart + 3]) & 0xffffL) << 16;
        long b0 = ((long)words2[bstart]) & 0xffffL;
        b0 |= (((long)words2[bstart + 1]) & 0xffffL) << 16;
        long b1 = ((long)words2[bstart + 2]) & 0xffffL;
        b1 |= (((long)words2[bstart + 3]) & 0xffffL) << 16;
        p = a0 * b0;
        d = (p >> 32) & 0xffffffffL;
        result[rstart] = (short)p;
        result[rstart + 1] = (short)(p >> 16);
        c = (int)d;
        d = (d >> 32) & 0xffffffffL;
        p = a0 * b1;
        p += ((long)c) & 0xffffffffL;
        c = (int)p;
        d += (p >> 32) & 0xffffffffL;
        p = a1 * b0;
        p += ((long)c) & 0xffffffffL;
        d += (p >> 32) & 0xffffffffL;
        result[rstart + 2] = (short)p;
        result[rstart + 3] = (short)(p >> 16);
        p = a1 * b1;
        p += d;
        // System.out.println("opsx={0:X16} {1:X16}",a1,b1);
        result[rstart + 4] = (short)p;
        result[rstart + 5] = (short)(p >> 16);
        result[rstart + 6] = (short)(p >> 32);
        result[rstart + 7] = (short)(p >> 48);
      }
    }

    // Multiplies eight words by eight words without overflow
    private static void BaselineMultiply8(
      short[] result,
      int rstart,
      short[] words1,
      int astart,
      short[] words2,
      int bstart) {
      {
        int p;
        short c;
        int d;
        int SMask = ShortMask;
        int a0 = ((int)words1[astart]) & SMask;
        int a1 = ((int)words1[astart + 1]) & SMask;
        int a2 = ((int)words1[astart + 2]) & SMask;
        int a3 = ((int)words1[astart + 3]) & SMask;
        int a4 = ((int)words1[astart + 4]) & SMask;
        int a5 = ((int)words1[astart + 5]) & SMask;
        int a6 = ((int)words1[astart + 6]) & SMask;
        int a7 = ((int)words1[astart + 7]) & SMask;
        int b0 = ((int)words2[bstart]) & SMask;
        int b1 = ((int)words2[bstart + 1]) & SMask;
        int b2 = ((int)words2[bstart + 2]) & SMask;
        int b3 = ((int)words2[bstart + 3]) & SMask;
        int b4 = ((int)words2[bstart + 4]) & SMask;
        int b5 = ((int)words2[bstart + 5]) & SMask;
        int b6 = ((int)words2[bstart + 6]) & SMask;
        int b7 = ((int)words2[bstart + 7]) & SMask;
        p = a0 * b0;
        c = (short)p;
        d = ((int)p >> 16) & SMask;
        result[rstart] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a0 * b1;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a1 * b0;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 1] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a0 * b2;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a1 * b1;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a2 * b0;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 2] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a0 * b3;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a1 * b2;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a2 * b1;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a3 * b0;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 3] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a0 * b4;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a1 * b3;
        p += ((int)c) & SMask;
        d += ((int)p >> 16) & SMask;
        p = (a2 * b2) + (p & ShortMask);
        d += ((int)p >> 16) & SMask;
        p = (a3 * b1) + (p & ShortMask);
        d += ((int)p >> 16) & SMask;
        p = (a4 * b0) + (p & ShortMask);
        d += ((int)p >> 16) & SMask;
        result[rstart + 4] = (short)p;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a0 * b5;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a1 * b4;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a2 * b3;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a3 * b2;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a4 * b1;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a5 * b0;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 5] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a0 * b6;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a1 * b5;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a2 * b4;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a3 * b3;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a4 * b2;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a5 * b1;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a6 * b0;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 6] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a0 * b7;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a1 * b6;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a2 * b5;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a3 * b4;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a4 * b3;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a5 * b2;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a6 * b1;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a7 * b0;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 7] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a1 * b7;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a2 * b6;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a3 * b5;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a4 * b4;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a5 * b3;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a6 * b2;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a7 * b1;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 8] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a2 * b7;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a3 * b6;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a4 * b5;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a5 * b4;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a6 * b3;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a7 * b2;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 9] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a3 * b7;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a4 * b6;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a5 * b5;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a6 * b4;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a7 * b3;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 10] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a4 * b7;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a5 * b6;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a6 * b5;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a7 * b4;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 11] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a5 * b7;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a6 * b6;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a7 * b5;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 12] = c;
        c = (short)d;
        d = ((int)d >> 16) & SMask;
        p = a6 * b7;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        p = a7 * b6;
        p += ((int)c) & SMask;
        c = (short)p;
        d += ((int)p >> 16) & SMask;
        result[rstart + 13] = c;
        p = a7 * b7;
        p += d;
        result[rstart + 14] = (short)p;
        result[rstart + 15] =
          (short)(p >> 16);
      }
    }
    //-----------------------------
    // Baseline Square
    //-----------------------------
    private static void BaselineSquare2(
      short[] result,
      int rstart,
      short[] words1,
      int astart) {
      {
        int p;
        short c;
        int d;
        int e;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart]) &
          0xffff);
        result[rstart] = (short)p;
        e = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart + 1]) &
          0xffff);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) & ShortMask);
        result[rstart + 1] = c;
        p = (((int)words1[astart + 1]) & ShortMask) * (((int)words1[astart +
          1]) & ShortMask);
        p += e;
        result[rstart + 2] = (short)p;
        result[rstart + 3] = (short)(p >>
          16);
      }
    }

    private static void BaselineSquare4(
      short[] result,
      int rstart,
      short[] words1,
      int astart) {
      {
        int p;
        short c;
        int d;
        int e;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart]) &
          0xffff);
        result[rstart] = (short)p;
        e = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart + 1]) &
          0xffff);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) & ShortMask);
        result[rstart + 1] = c;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart + 2]) &
          0xffff);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        p = (((int)words1[astart + 1]) & ShortMask) * (((int)words1[astart +
          1]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) & ShortMask);
        result[rstart + 2] = c;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart + 3]) &
          0xffff);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 1]) & ShortMask) * (((int)words1[astart +
          2]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) &
          0xffff);
        result[rstart + 3] = c;
        p = (((int)words1[astart + 1]) & ShortMask) * (((int)words1[astart +
          3]) & ShortMask);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        p = (((int)words1[astart + 2]) & ShortMask) * (((int)words1[astart +
          2]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) & ShortMask);
        result[rstart + 4] = c;
        p = (((int)words1[astart + 2]) & ShortMask) * (((int)words1[astart +
          3]) & ShortMask);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) &
          0xffff);
        result[rstart + (2 * 4) - 3] = c;
        p = (((int)words1[astart + 3]) & ShortMask) * (((int)words1[astart +
          3]) & ShortMask);
        p += e;
        result[rstart + 6] = (short)p;
        result[rstart + 7] = (short)(p >>
          16);
      }
    }

    private static void BaselineSquare8(
      short[] result,
      int rstart,
      short[] words1,
      int astart) {
      {
        int p;
        short c;
        int d;
        int e;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart]) &
          0xffff);
        result[rstart] = (short)p;
        e = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart + 1]) &
          0xffff);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) & ShortMask);
        result[rstart + 1] = c;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart + 2]) &
          0xffff);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        p = (((int)words1[astart + 1]) & ShortMask) * (((int)words1[astart +
          1]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) & ShortMask);
        result[rstart + 2] = c;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart + 3]) &
          0xffff);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 1]) & ShortMask) * (((int)words1[astart +
          2]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) &
          0xffff);
        result[rstart + 3] = c;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart + 4]) &
          0xffff);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 1]) & ShortMask) * (((int)words1[astart +
          3]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        p = (((int)words1[astart + 2]) & ShortMask) * (((int)words1[astart +
          2]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) & ShortMask);
        result[rstart + 4] = c;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart + 5]) &
          0xffff);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 1]) & ShortMask) * (((int)words1[astart +
          4]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 2]) & ShortMask) * (((int)words1[astart +
          3]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) &
          0xffff);
        result[rstart + 5] = c;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart + 6]) &
          0xffff);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 1]) & ShortMask) * (((int)words1[astart +
          5]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 2]) & ShortMask) * (((int)words1[astart +
          4]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        p = (((int)words1[astart + 3]) & ShortMask) * (((int)words1[astart +
          3]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) & ShortMask);
        result[rstart + 6] = c;
        p = (((int)words1[astart]) & ShortMask) * (((int)words1[astart + 7]) &
          0xffff);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 1]) & ShortMask) * (((int)words1[astart +
          6]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 2]) & ShortMask) * (((int)words1[astart +
          5]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 3]) & ShortMask) * (((int)words1[astart +
          4]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) &
          0xffff);
        result[rstart + 7] = c;
        p = (((int)words1[astart + 1]) & ShortMask) * (((int)words1[astart +
          7]) & ShortMask);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 2]) & ShortMask) * (((int)words1[astart +
          6]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 3]) & ShortMask) * (((int)words1[astart +
          5]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        p = (((int)words1[astart + 4]) & ShortMask) * (((int)words1[astart +
          4]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) & ShortMask);
        result[rstart + 8] = c;
        p = (((int)words1[astart + 2]) & ShortMask) * (((int)words1[astart +
          7]) & ShortMask);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 3]) & ShortMask) * (((int)words1[astart +
          6]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 4]) & ShortMask) * (((int)words1[astart +
          5]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) &
          0xffff);
        result[rstart + 9] = c;
        p = (((int)words1[astart + 3]) & ShortMask) * (((int)words1[astart +
          7]) & ShortMask);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 4]) & ShortMask) * (((int)words1[astart +
          6]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        p = (((int)words1[astart + 5]) & ShortMask) * (((int)words1[astart +
          5]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) & ShortMask);
        result[rstart + 10] = c;
        p = (((int)words1[astart + 4]) & ShortMask) * (((int)words1[astart +
          7]) & ShortMask);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        p = (((int)words1[astart + 5]) & ShortMask) * (((int)words1[astart +
          6]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) &
          0xffff);
        result[rstart + 11] = c;
        p = (((int)words1[astart + 5]) & ShortMask) * (((int)words1[astart +
          7]) & ShortMask);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        p = (((int)words1[astart + 6]) & ShortMask) * (((int)words1[astart +
          6]) & ShortMask);
        p += ((int)c) & ShortMask;
        c = (short)p;
        d += ((int)p >> 16) & ShortMask;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) & ShortMask);
        result[rstart + 12] = c;
        p = (((int)words1[astart + 6]) & ShortMask) * (((int)words1[astart +
          7]) & ShortMask);
        c = (short)p;
        d = ((int)p >> 16) & ShortMask;
        d = (int)((d << 1) + (((int)c >> 15) & 1));
        c <<= 1;
        e += ((int)c) & ShortMask;
        c = (short)e;
        e = d + (((int)e >> 16) &
          0xffff);
        result[rstart + 13] = c;
        p = (((int)words1[astart + 7]) & ShortMask) * (((int)words1[astart +
          7]) & ShortMask);
        p += e;
        result[rstart + 14] = (short)p;
        result[rstart + 15] =
          (short)(p >> 16);
      }
    }

    private static int BitPrecision(short numberValue) {
      if (numberValue == 0) {
        return 0;
      }
      int i = 16;
      {
        if ((numberValue >> 8) == 0) {
          numberValue <<= 8;
          i -= 8;
        }

        if ((numberValue >> 12) == 0) {
          numberValue <<= 4;
          i -= 4;
        }

        if ((numberValue >> 14) == 0) {
          numberValue <<= 2;
          i -= 2;
        }

        if ((numberValue >> 15) == 0) {
          --i;
        }
      }
      return i;
    }

    private static int BitsToWords(int bitCount) {
      return ((bitCount & 0x0f) == 0) ? (bitCount >> 4) : (bitCount >> 4) + 1;
    }

    private static void ChunkedLinearMultiply(
      short[] productArr,
      int cstart,
      short[] tempArr,
      int tempStart, // uses bcount*4 space
      short[] words1,
      int astart,
      int acount, // Equal size or longer
      short[] words2,
      int bstart,
      int bcount) {
      {
        int carryPos = 0;
        // Set carry to zero
        java.util.Arrays.fill(productArr, cstart, (cstart)+(bcount), (short)0);
        for (int i = 0; i < acount; i += bcount) {
          int diff = acount - i;
          if (diff > bcount) {
            SameSizeMultiply(
              tempArr,
              tempStart,
              tempArr,
              tempStart + bcount + bcount,
              words1,
              astart + i,
              words2,
              bstart,
              bcount);
            // Add carry
            AddUnevenSize(
              tempArr,
              tempStart,
              tempArr,
              tempStart,
              bcount + bcount,
              productArr,
              cstart + carryPos,
              bcount);
            // Copy product and carry
            System.arraycopy(
              tempArr,
              tempStart,
              productArr,
              cstart + i,
              bcount + bcount);
            carryPos += bcount;
          } else {
            AsymmetricMultiply(
              tempArr,
              tempStart, // uses diff + bcount space
              tempArr,
              tempStart + diff + bcount, // uses diff + bcount
              words1,
              astart + i,
              diff,
              words2,
              bstart,
              bcount);
            // Add carry
            AddUnevenSize(
              tempArr,
              tempStart,
              tempArr,
              tempStart,
              diff + bcount,
              productArr,
              cstart + carryPos,
              bcount);
            // Copy product without carry
            System.arraycopy(
              tempArr,
              tempStart,
              productArr,
              cstart + i,
              diff + bcount);
          }
        }
      }
    }

    static short[] CleanGrow(short[] a, int size) {
      if (size > a.length) {
        short[] newa = new short[size];
        System.arraycopy(a, 0, newa, 0, a.length);
        return newa;
      }
      return a;
    }

    private static int Compare(
      short[] words1,
      int astart,
      short[] words2,
      int bstart,
      int n) {
      while ((n--) != 0) {
        int an = ((int)words1[astart + n]) & ShortMask;
        int bn = ((int)words2[bstart + n]) & ShortMask;
        if (an > bn) {
          return 1;
        }
        if (an < bn) {
          return -1;
        }
      }
      return 0;
    }

    private static int CompareWithWords1IsOneBigger(
      short[] words1,
      int astart,
      short[] words2,
      int bstart,
      int words1Count) {
      // NOTE: Assumes that words2's count is 1 less
      if (words1[astart + words1Count - 1] != 0) {
        return 1;
      }
      int w1c = words1Count;
      --w1c;
      while ((w1c--) != 0) {
        int an = ((int)words1[astart + w1c]) & ShortMask;
        int bn = ((int)words2[bstart + w1c]) & ShortMask;
        if (an > bn) {
          return 1;
        }
        if (an < bn) {
          return -1;
        }
      }
      return 0;
    }

    static int CountWords(short[] array) {
      int n = array.length;
      while (n != 0 && array[n - 1] == 0) {
        --n;
      }
      return (int)n;
    }

    static int CountWords(short[] array, int pos, int len) {
      int n = len;
      while (n != 0 && array[pos + n - 1] == 0) {
        --n;
      }
      return (int)n;
    }

    private static int DecrementWords(
      short[] words1,
      int words1Start,
      int n,
      short words2) {
      {
        short tmp = words1[words1Start];
        words1[words1Start] = (short)(tmp - words2);
        if ((((int)words1[words1Start]) & ShortMask) <= (((int)tmp) &
          ShortMask)) {
          return 0;
        }
        for (int i = 1; i < n; ++i) {
          tmp = words1[words1Start + i];
          --words1[words1Start + i];
          if (tmp != 0) {
            return 0;
          }
        }
        return 1;
      }
    }

    private static short Divide32By16(
      int dividendLow,
      short divisorShort,
      boolean returnRemainder) {
      int tmpInt;
      int dividendHigh = 0;
      int intDivisor = ((int)divisorShort) & ShortMask;
      for (int i = 0; i < 32; ++i) {
        tmpInt = dividendHigh >> 31;
        dividendHigh <<= 1;
        dividendHigh = ((int)(dividendHigh | ((int)((dividendLow >>
          31) & 1))));
        dividendLow <<= 1;
        tmpInt |= dividendHigh;
        // unsigned greater-than-or-equal check
        if (((tmpInt >> 31) != 0) || (tmpInt >= intDivisor)) {
          {
            dividendHigh -= intDivisor;
            ++dividendLow;
          }
        }
      }
      return returnRemainder ? ((short)(((int)dividendHigh) &
        0xffff)) : ((short)(((int)dividendLow) &
            0xffff));
    }

    private static short DivideUnsigned(int x, short y) {
      if ((x >> 31) == 0) {
        // x is already nonnegative
        int iy = ((int)y) & ShortMask;
        return (short)((int)x / iy);
      } else {
        long longX = ((long)x) & 0xffffffffL;
        int iy = ((int)y) & ShortMask;
        return (short)(longX / iy);
      }
    }

    private static void FastDivide(
      short[] quotientReg,
      short[] dividendReg,
      int count,
      short divisorSmall) {
      switch (divisorSmall) {
        case 2:
          FastDivideAndRemainderTwo(quotientReg, 0, dividendReg, 0, count);
          break;
        case 10:
          FastDivideAndRemainderTen(quotientReg, 0, dividendReg, 0, count);
          break;
        default:
          FastDivideAndRemainder(
            quotientReg,
            0,
            dividendReg,
            0,
            count,
            divisorSmall);
          break;
      }
    }

    private static short FastDivideAndRemainderTwo(
      short[] quotientReg,
      int quotientStart,
      short[] dividendReg,
      int dividendStart,
      int count) {
      int quo;
      int rem = 0;
      int currentDividend;
      int ds = dividendStart + count - 1;
      int qs = quotientStart + count - 1;
      for (int i = 0; i < count; ++i) {
        currentDividend = ((int)dividendReg[ds]) & ShortMask;
        currentDividend |= rem << 16;
        quo = currentDividend >> 1;
        quotientReg[qs] = ((short)quo);
        rem = currentDividend & 1;
        --ds;
        --qs;
      }
      return (short)rem;
    }

    private static short FastDivideAndRemainderTen(
      short[] quotientReg,
      int quotientStart,
      short[] dividendReg,
      int dividendStart,
      int count) {
      int quo;
      int rem = 0;
      int currentDividend;
      int ds = dividendStart + count - 1;
      int qs = quotientStart + count - 1;
      for (int i = 0; i < count; ++i) {
        currentDividend = ((int)dividendReg[ds]) & ShortMask;
        currentDividend |= rem << 16;
        // Fast division by 10
        quo = (currentDividend >= 81920) ? currentDividend / 10 :
          (((currentDividend * 52429) >> 19) & 8191);
        quotientReg[qs] = ((short)quo);
        rem = currentDividend - (10 * quo);
        --ds;
        --qs;
      }
      return (short)rem;
    }

    private static short FastDivideAndRemainder(
      short[] quotientReg,
      int quotientStart,
      short[] dividendReg,
      int dividendStart,
      int count,
      short divisorSmall) {
      int idivisor = ((int)divisorSmall) & ShortMask;
      int quo;
      int rem = 0;
      int ds = dividendStart + count - 1;
      int qs = quotientStart + count - 1;
      int currentDividend;
      if (idivisor < 0x8000) {
        for (int i = 0; i < count; ++i) {
          currentDividend = ((int)dividendReg[ds]) & ShortMask;
          currentDividend |= rem << 16;
          quo = currentDividend / idivisor;
          quotientReg[qs] = ((short)quo);
          rem = currentDividend - (idivisor * quo);
          --ds;
          --qs;
        }
      } else {
        for (int i = 0; i < count; ++i) {
          currentDividend = ((int)dividendReg[ds]) & ShortMask;
          currentDividend |= rem << 16;
          if ((currentDividend >> 31) == 0) {
            quo = currentDividend / idivisor;
            quotientReg[qs] = ((short)quo);
            rem = currentDividend - (idivisor * quo);
          } else {
            quo = ((int)DivideUnsigned(
              currentDividend,
              divisorSmall)) & ShortMask;
            quotientReg[qs] = ((short)quo);
            rem = (currentDividend - (idivisor * quo));
          }
          --ds;
          --qs;
        }
      }
      return (short)rem;
    }

    private static short FastRemainder(
      short[] dividendReg,
      int count,
      short divisorSmall) {
      int i = count;
      short remainder = 0;
      while ((i--) > 0) {
        int dividendSmall = ((int)((((int)dividendReg[i]) &
          ShortMask) | ((int)remainder << 16)));
        remainder = RemainderUnsigned(
            dividendSmall,
            divisorSmall);
      }
      return remainder;
    }

    private static short GetHighHalfAsBorrow(int val) {
      return ((short)(0 - ((val >> 16) & ShortMask)));
    }

    private static int GetLowHalf(int val) {
      return val & ShortMask;
    }

    private static int GetUnsignedBitLengthEx(int numberValue, int wordCount) {
      // NOTE: Currently called only if wordCount <= 1000000,
      // so that overflow issues with Int32s are not present
      int wc = wordCount;
      if (wc != 0) {
        wc = (wc - 1) << 4;
        if (numberValue == 0) {
          return wc;
        }
        wc += 16;
        {
          if ((numberValue >> 8) == 0) {
            numberValue <<= 8;
            wc -= 8;
          }
          if ((numberValue >> 12) == 0) {
            numberValue <<= 4;
            wc -= 4;
          }
          if ((numberValue >> 14) == 0) {
            numberValue <<= 2;
            wc -= 2;
          }
          if ((numberValue >> 15) == 0) {
            --wc;
          }
        }
        return wc;
      }
      return 0;
    }

    static short[] GrowForCarry(short[] a, short carry) {
      int oldLength = a.length;
      short[] ret = CleanGrow(a, oldLength + 1);
      ret[oldLength] = carry;
      return ret;
    }

    static int IncrementWords(
      short[] words1,
      int words1Start,
      int n,
      short words2) {
      {
        short tmp = words1[words1Start];
        words1[words1Start] = (short)(tmp + words2);
        if ((((int)words1[words1Start]) & ShortMask) >= (((int)tmp) &
          ShortMask)) {
          return 0;
        }
        for (int i = 1; i < n; ++i) {
          ++words1[words1Start + i];
          if (words1[words1Start + i] != 0) {
            return 0;
          }
        }
        return 1;
      }
    }

    private static short LinearMultiply(
      short[] productArr,
      int cstart,
      short[] words1,
      int astart,
      short words2,
      int n) {
      {
        short carry = 0;
        int bint = ((int)words2) & ShortMask;
        for (int i = 0; i < n; ++i) {
          int p;
          p = (((int)words1[astart + i]) & ShortMask) * bint;
          p += ((int)carry) & ShortMask;
          productArr[cstart + i] = (short)p;
          carry = (short)(p >> 16);
        }
        return carry;
      }
    }

    private static short LinearMultiplyAdd(
      short[] productArr,
      int cstart,
      short[] words1,
      int astart,
      short words2,
      int n) {
      short carry = 0;
      int bint = ((int)words2) & ShortMask;
      for (int i = 0; i < n; ++i) {
        int p;
        p = ((((int)words1[astart + i]) & ShortMask) * bint);
        p = (p + (((int)carry) & ShortMask));
        p = (p + (((int)productArr[cstart + i]) & ShortMask));
        productArr[cstart + i] = ((short)p);
        carry = (short)(p >> 16);
      }
      return carry;
    }

    private static void RecursiveSquare(
      short[] resultArr,
      int resultStart,
      short[] tempArr,
      int tempStart,
      short[] words1,
      int words1Start,
      int count) {
      if (count <= MultRecursionThreshold) {
        switch (count) {
          case 2:
            BaselineSquare2(resultArr, resultStart, words1, words1Start);
            break;
          case 4:
            BaselineSquare4(resultArr, resultStart, words1, words1Start);
            break;
          case 8:
            BaselineSquare8(resultArr, resultStart, words1, words1Start);
            break;
          default:
            SchoolbookSquare(
              resultArr,
              resultStart,
              words1,
              words1Start,
              count);
            break;
        }
      } else if (count >= Toom4Threshold) {
        Toom4(
          resultArr,
          resultStart,
          words1,
          words1Start,
          count,
          words1,
          words1Start,
          count);
      } else if (count >= Toom3Threshold) {
        Toom3(
          resultArr,
          resultStart,
          words1,
          words1Start,
          count,
          words1,
          words1Start,
          count);
      } else if ((count & 1) == 0) {
        int count2 = count >> 1;
        RecursiveSquare(
          resultArr,
          resultStart,
          tempArr,
          tempStart + count,
          words1,
          words1Start,
          count2);
        RecursiveSquare(
          resultArr,
          resultStart + count,
          tempArr,
          tempStart + count,
          words1,
          words1Start + count2,
          count2);
        SameSizeMultiply(
          tempArr,
          tempStart,
          tempArr,
          tempStart + count,
          words1,
          words1Start,
          words1,
          words1Start + count2,
          count2);
        int carry = AddInternal(
            resultArr,
            resultStart + count2,
            resultArr,
            resultStart + count2,
            tempArr,
            tempStart,
            count);
        carry += AddInternal(
            resultArr,
            resultStart + count2,
            resultArr,
            resultStart + count2,
            tempArr,
            tempStart,
            count);
        IncrementWords(
          resultArr,
          (int)(resultStart + count + count2),
          count2,
          (short)carry);
      } else {
        SameSizeMultiply(
          resultArr,
          resultStart,
          tempArr,
          tempStart,
          words1,
          words1Start,
          words1,
          words1Start,
          count);
      }
    }

    private static short RemainderUnsigned(int x, short y) {
      {
        int iy = ((int)y) & ShortMask;
        return ((x >> 31) == 0) ? ((short)(((int)x % iy) & ShortMask)) :
          Divide32By16(x, y, true);
      }
    }

    private static void ReverseChars(char[] chars, int offset, int length) {
      int half = length >> 1;
      int right = offset + length - 1;
      for (int i = 0; i < half; i++, right--) {
        char value = chars[offset + i];
        chars[offset + i] = chars[right];
        chars[right] = value;
      }
    }

    // NOTE: Renamed from RecursiveMultiply to better show that
    // this function only takes operands of the same size, as opposed
    // to AsymmetricMultiply.
    private static void SameSizeMultiply(
      short[] resultArr, // size 2*count
      int resultStart,
      short[] tempArr, // size 2*count
      int tempStart,
      short[] words1,
      int words1Start, // size count
      short[] words2,
      int words2Start, // size count
      int count) {
      // System.out.println("RecursiveMultiply " + count + " " + count +
      // " [r=" + resultStart + " t=" + tempStart + " a=" + words1Start +
      // " b=" + words2Start + "]");

      if (count <= MultRecursionThreshold) {
        switch (count) {
          case 2:
            BaselineMultiply2(
              resultArr,
              resultStart,
              words1,
              words1Start,
              words2,
              words2Start);
            break;
          case 4:
            BaselineMultiply4(
              resultArr,
              resultStart,
              words1,
              words1Start,
              words2,
              words2Start);
            break;
          case 8:
            BaselineMultiply8(
              resultArr,
              resultStart,
              words1,
              words1Start,
              words2,
              words2Start);
            break;
          default:
            SchoolbookMultiply(
              resultArr,
              resultStart,
              words1,
              words1Start,
              count,
              words2,
              words2Start,
              count);
            break;
        }
      } else if (count >= Toom4Threshold) {
        Toom4(
          resultArr,
          resultStart,
          words1,
          words1Start,
          count,
          words2,
          words2Start,
          count);
      } else if (count >= Toom3Threshold) {
        Toom3(
          resultArr,
          resultStart,
          words1,
          words1Start,
          count,
          words2,
          words2Start,
          count);
      } else {
        int countA = count;
        while (countA != 0 && words1[words1Start + countA - 1] == 0) {
          --countA;
        }
        int countB = count;
        while (countB != 0 && words2[words2Start + countB - 1] == 0) {
          --countB;
        }
        int offset2For1 = 0;
        int offset2For2 = 0;
        if (countA == 0 || countB == 0) {
          // words1 or words2 is empty, so result is 0
          java.util.Arrays.fill(resultArr, resultStart, (resultStart)+(count << 1), (short)0);
          return;
        }
        // Split words1 and words2 in two parts each
        // Words1 is split into HighA and LowA
        // Words2 is split into HighB and LowB
        if ((count & 1) == 0) {
          // Count is even, so each part will be equal size
          int count2 = count >> 1;
          if (countA <= count2 && countB <= count2) {
            // Both words1 and words2 are smaller than half the
            // count (their high parts are 0)
            // System.out.println("Can be smaller: " + AN + "," + BN + "," +
            // (count2));
            java.util.Arrays.fill(resultArr, resultStart + count, (resultStart + count)+(count), (short)0);
            if (count2 == 8) {
              BaselineMultiply8(
                resultArr,
                resultStart,
                words1,
                words1Start,
                words2,
                words2Start);
            } else {
              SameSizeMultiply(
                resultArr,
                resultStart,
                tempArr,
                tempStart,
                words1,
                words1Start,
                words2,
                words2Start,
                count2);
            }
            return;
          }
          int resultMediumHigh = resultStart + count;
          int resultHigh = resultMediumHigh + count2;
          int resultMediumLow = resultStart + count2;
          int tsn = tempStart + count;
          // Find the part of words1 with the higher value
          // so we can compute the absolute value
          offset2For1 = Compare(
              words1,
              words1Start,
              words1,
              words1Start + count2,
              count2) > 0 ? 0 : count2;
          int tmpvar = (int)(words1Start + (count2 ^
            offset2For1));
          // Abs(LowA - HighA)
          SubtractInternal(
            resultArr,
            resultStart,
            words1,
            words1Start + offset2For1,
            words1,
            tmpvar,
            count2);
          // Find the part of words2 with the higher value
          // so we can compute the absolute value
          offset2For2 = Compare(
              words2,
              words2Start,
              words2,
              words2Start + count2,
              count2) > 0 ? 0 : count2;
          // Abs(LowB - HighB)
          int tmp = words2Start + (count2 ^ offset2For2);
          SubtractInternal(
            resultArr,
            resultMediumLow,
            words2,
            words2Start + offset2For2,
            words2,
            tmp,
            count2);
          // Medium-high/high result = HighA * HighB
          SameSizeMultiply(
            resultArr,
            resultMediumHigh,
            tempArr,
            tsn,
            words1,
            words1Start + count2,
            words2,
            words2Start + count2,
            count2);
          // Temp = Abs(LowA-HighA).Multiply(Abs)(LowB-HighB)
          SameSizeMultiply(
            tempArr,
            tempStart,
            tempArr,
            tsn,
            resultArr,
            resultStart,
            resultArr,
            resultMediumLow,
            count2);
          // Low/Medium-low result = LowA * LowB
          SameSizeMultiply(
            resultArr,
            resultStart,
            tempArr,
            tsn,
            words1,
            words1Start,
            words2,
            words2Start,
            count2);
          // Medium high result = Low(HighA * HighB) + High(LowA * LowB)
          int c2 = AddInternal(
              resultArr,
              resultMediumHigh,
              resultArr,
              resultMediumHigh,
              resultArr,
              resultMediumLow,
              count2);
          int c3 = c2;
          // Medium low result = Low(HighA * HighB) + High(LowA * LowB) +
          // Low(LowA * LowB)
          c2 += AddInternal(
              resultArr,
              resultMediumLow,
              resultArr,
              resultMediumHigh,
              resultArr,
              resultStart,
              count2);
          // Medium high result = Low(HighA * HighB) + High(LowA * LowB) +
          // High(HighA * HighB)
          c3 += AddInternal(
              resultArr,
              resultMediumHigh,
              resultArr,
              resultMediumHigh,
              resultArr,
              resultHigh,
              count2);
          if (offset2For1 == offset2For2) {
            // If high parts of both words were greater
            // than their low parts
            // or if low parts of both words were greater
            // than their high parts
            // Medium low/Medium high result = Medium low/Medium high result
            // - Low(Temp)
            c3 -= SubtractInternal(
                resultArr,
                resultMediumLow,
                resultArr,
                resultMediumLow,
                tempArr,
                tempStart,
                count);
          } else {
            // Medium low/Medium high result = Medium low/Medium high result
            // + Low(Temp)
            c3 += AddInternal(
                resultArr,
                resultMediumLow,
                resultArr,
                resultMediumLow,
                tempArr,
                tempStart,
                count);
          }
          // Add carry
          c3 += IncrementWords(resultArr, resultMediumHigh, count2, (short)c2);
          if (c3 != 0) {
            IncrementWords(resultArr, resultHigh, count2, (short)c3);
          }
        } else {
          // Count is odd, high part will be 1 shorter
          int countHigh = count >> 1; // Shorter part
          int countLow = count - countHigh; // Longer part
          offset2For1 = CompareWithWords1IsOneBigger(
              words1,
              words1Start,
              words1,
              words1Start + countLow,
              countLow) > 0 ? 0 : countLow;
          // Abs(LowA - HighA)
          if (offset2For1 == 0) {
            SubtractWords1IsOneBigger(
              resultArr,
              resultStart,
              words1,
              words1Start,
              words1,
              words1Start + countLow,
              countLow);
          } else {
            SubtractWords2IsOneBigger(
              resultArr,
              resultStart,
              words1,
              words1Start + countLow,
              words1,
              words1Start,
              countLow);
          }
          offset2For2 = CompareWithWords1IsOneBigger(
              words2,
              words2Start,
              words2,
              words2Start + countLow,
              countLow) > 0 ? 0 : countLow;
          // Abs(LowB, HighB)
          if (offset2For2 == 0) {
            SubtractWords1IsOneBigger(
              tempArr,
              tempStart,
              words2,
              words2Start,
              words2,
              words2Start + countLow,
              countLow);
          } else {
            SubtractWords2IsOneBigger(
              tempArr,
              tempStart,
              words2,
              words2Start + countLow,
              words2,
              words2Start,
              countLow);
          }
          // Temp = Abs(LowA-HighA).Multiply(Abs)(LowB-HighB)
          int shorterOffset = countHigh << 1;
          int longerOffset = countLow << 1;
          SameSizeMultiply(
            tempArr,
            tempStart + shorterOffset,
            resultArr,
            resultStart + shorterOffset,
            resultArr,
            resultStart,
            tempArr,
            tempStart,
            countLow);
          // Save part of temp since temp will overlap in part
          // in the Low/Medium low result multiply
          short resultTmp0 = tempArr[tempStart + shorterOffset];
          short resultTmp1 = tempArr[tempStart + shorterOffset + 1];
          // Medium high/high result = HighA * HighB
          SameSizeMultiply(
            resultArr,
            resultStart + longerOffset,
            resultArr,
            resultStart,
            words1,
            words1Start + countLow,
            words2,
            words2Start + countLow,
            countHigh);
          // Low/Medium low result = LowA * LowB
          SameSizeMultiply(
            resultArr,
            resultStart,
            tempArr,
            tempStart,
            words1,
            words1Start,
            words2,
            words2Start,
            countLow);
          // Restore part of temp
          tempArr[tempStart + shorterOffset] = resultTmp0;
          tempArr[tempStart + shorterOffset + 1] = resultTmp1;
          int countMiddle = countLow << 1;
          // Medium high result = Low(HighA * HighB) + High(LowA * LowB)
          int c2 = AddInternal(
              resultArr,
              resultStart + countMiddle,
              resultArr,
              resultStart + countMiddle,
              resultArr,
              resultStart + countLow,
              countLow);
          int c3 = c2;
          // Medium low result = Low(HighA * HighB) + High(LowA * LowB) +
          // Low(LowA * LowB)
          c2 += AddInternal(
              resultArr,
              resultStart + countLow,
              resultArr,
              resultStart + countMiddle,
              resultArr,
              resultStart,
              countLow);
          // Medium high result = Low(HighA * HighB) + High(LowA * LowB) +
          // High(HighA * HighB)
          c3 += AddUnevenSize(
              resultArr,
              resultStart + countMiddle,
              resultArr,
              resultStart + countMiddle,
              countLow,
              resultArr,
              resultStart + countMiddle + countLow,
              countLow - 2);
          if (offset2For1 == offset2For2) {
            // If high parts of both words were greater
            // than their low parts
            // or if low parts of both words were greater
            // than their high parts
            // Medium low/Medium high result = Medium low/Medium high result
            // - Low(Temp)
            c3 -= SubtractInternal(
                resultArr,
                resultStart + countLow,
                resultArr,
                resultStart + countLow,
                tempArr,
                tempStart + shorterOffset,
                countLow << 1);
          } else {
            // Medium low/Medium high result = Medium low/Medium high result
            // + Low(Temp)
            c3 += AddInternal(
                resultArr,
                resultStart + countLow,
                resultArr,
                resultStart + countLow,
                tempArr,
                tempStart + shorterOffset,
                countLow << 1);
          }
          // Add carry
          c3 += IncrementWords(
              resultArr,
              resultStart + countMiddle,
              countLow,
              (short)c2);
          if (c3 != 0) {
            IncrementWords(
              resultArr,
              resultStart + countMiddle + countLow,
              countLow - 2,
              (short)c3);
          }
        }
      }
    }

    private static void SchoolbookMultiplySameLengthEven(
      short[] resultArr,
      int resultStart,
      short[] words1,
      int words1Start,
      short[] words2,
      int words2Start,
      int count) {
      int resultPos;
      long carry = 0;
      long p;
      long valueBint;
      {
        valueBint = ((int)words2[words2Start]) & ShortMask;
        valueBint |= (((long)words2[words2Start + 1]) & ShortMask) << 16;
        for (int j = 0; j < count; j += 2) {
          p = ((int)words1[words1Start + j]) & ShortMask;
          p |= (((long)words1[words1Start + j + 1]) & ShortMask) << 16;
          p *= valueBint + carry;
          resultArr[resultStart + j] = (short)p;
          resultArr[resultStart + j + 1] = (short)(p >> 16);
          carry = (p >> 32) & 0xffffffffL;
        }
        resultArr[resultStart + count] = (short)carry;
        resultArr[resultStart + count + 1] = (short)(carry >> 16);
        for (int i = 2; i < count; i += 2) {
          resultPos = resultStart + i;
          carry = 0;
          valueBint = ((int)words2[words2Start + i]) & ShortMask;
          valueBint |= (((long)words2[words2Start + i + 1]) & ShortMask) << 16;
          for (int j = 0; j < count; j += 2, resultPos += 2) {
            p = ((int)words1[words1Start + j]) & ShortMask;
            p |= (((long)words1[words1Start + j + 1]) & ShortMask) << 16;
            p *= valueBint + carry;
            p += ((int)resultArr[resultPos]) & ShortMask;
            p += (((int)resultArr[resultPos + 1]) & ShortMask) << 16;
            resultArr[resultPos] = (short)p;
            resultArr[resultPos + 1] = (short)(p >> 16);
            carry = (p >> 32) & 0xffffffffL;
          }
          resultArr[resultStart + i + count] = (short)carry;
          resultArr[resultStart + i + count + 1] = (short)(carry >> 16);
        }
      }
    }

    private static void SchoolbookMultiplySameLengthOdd(
      short[] resultArr,
      int resultStart,
      short[] words1,
      int words1Start,
      short[] words2,
      int words2Start,
      int count) {
      int resultPos;
      long carry = 0;
      long p;
      long valueBint;
      {
        valueBint = ((int)words2[words2Start]) & ShortMask;
        valueBint |= (count > 1) ? (((long)words2[words2Start + 1]) &
          ShortMask) << 16 : 0;
        for (int j = 0; j < count; j += 2) {
          p = ((int)words1[words1Start + j]) & ShortMask;
          if (j + 1 < count) {
            p |= ((long)words1[words1Start + j + 1]) & ShortMask;
          }
          p *= valueBint + carry;
          resultArr[resultStart + j] = (short)p;
          if (j + 1 < count) {
            resultArr[resultStart + j + 1] = (short)(p >> 16);
          }
          carry = (p >> 32) & 0xffffffffL;
        }
        resultArr[resultStart + count] = (short)carry;
        if (count > 1) {
          resultArr[resultStart + count + 1] = (short)(carry >> 16);
        }
        for (int i = 2; i < count; i += 2) {
          resultPos = resultStart + i;
          carry = 0;
          valueBint = ((int)words2[words2Start + i]) & ShortMask;
          if (i + 1 < count) {
            valueBint |= (((long)words2[words2Start + i + 1]) & ShortMask) <<
              16;
          }
          for (int j = 0; j < count; j += 2, resultPos += 2) {
            p = ((int)words1[words1Start + j]) & ShortMask;
            if (j + 1 < count) {
              p |= (((long)words1[words1Start + j + 1]) & ShortMask) << 16;
            }
            p *= valueBint + carry;
            p += ((int)resultArr[resultPos]) & ShortMask;
            if (j + 1 < count) {
              p += (((int)resultArr[resultPos + 1]) & ShortMask) << 16;
              resultArr[resultPos] = (short)p;
              resultArr[resultPos + 1] = (short)(p >> 16);
              carry = (p >> 32) & 0xffffffffL;
            } else {
              resultArr[resultPos] = (short)p;
              carry = p >> 16;
            }
          }
          resultArr[resultStart + i + count] = (short)carry;
          if (i + 1 < count) {
            resultArr[resultStart + i + count + 1] = (short)(carry >> 16);
          }
        }
      }
    }

    private static void SchoolbookMultiply(
      short[] resultArr,
      int resultStart,
      short[] words1,
      int words1Start,
      int words1Count,
      short[] words2,
      int words2Start,
      int words2Count) {
      if (words1Count == words2Count && (words1Count & 1) == 0) {
        /* if ((words1Count & 1) == 0) {
                  SchoolbookMultiplySameLengthEven(
                    resultArr,
                    resultStart,
                    words1,
                    words1Start,
                    words2,
                    words2Start,
                    words1Count);
                  return;
                } else {
                  SchoolbookMultiplySameLengthOdd(
                    resultArr,
                    resultStart,
                    words1,
                    words1Start,
                    words2,
                    words2Start,
                    words1Count);
          return;
               }
         */
      }
      int resultPos, carry, valueBint;
      if (words1Count < words2Count) {
        // words1 is shorter than words2, so put words2 on top
        carry = 0;
        valueBint = ((int)words1[words1Start]) & ShortMask;
        for (int j = 0; j < words2Count; ++j) {
          int p;
          p = ((((int)words2[words2Start + j]) & ShortMask) *
            valueBint);
          p = (p + carry);
          resultArr[resultStart + j] = ((short)p);
          carry = (p >> 16) & ShortMask;
        }
        resultArr[resultStart + words2Count] = ((short)carry);
        for (int i = 1; i < words1Count; ++i) {
          resultPos = resultStart + i;
          carry = 0;
          valueBint = ((int)words1[words1Start + i]) & ShortMask;
          for (int j = 0; j < words2Count; ++j, ++resultPos) {
            int p;
            p = ((((int)words2[words2Start + j]) & ShortMask) *
              valueBint);
            p = (p + carry);
            p = (p + (((int)resultArr[resultPos]) & ShortMask));
            resultArr[resultPos] = ((short)p);
            carry = (p >> 16) & ShortMask;
          }
          resultArr[resultStart + i + words2Count] = ((short)carry);
        }
      } else {
        // words2 is shorter or the same length as words1
        carry = 0;
        valueBint = ((int)words2[words2Start]) & ShortMask;
        for (int j = 0; j < words1Count; ++j) {
          int p;
          p = ((((int)words1[words1Start + j]) & ShortMask) *
            valueBint);
          p = (p + carry);
          resultArr[resultStart + j] = ((short)p);
          carry = (p >> 16) & ShortMask;
        }
        resultArr[resultStart + words1Count] = ((short)carry);
        for (int i = 1; i < words2Count; ++i) {
          resultPos = resultStart + i;
          carry = 0;
          valueBint = ((int)words2[words2Start + i]) & ShortMask;
          for (int j = 0; j < words1Count; ++j, ++resultPos) {
            int p;
            p = ((((int)words1[words1Start + j]) & ShortMask) *
              valueBint);
            p = (p + carry);
            p = (p + (((int)resultArr[resultPos]) & ShortMask));
            resultArr[resultPos] = ((short)p);
            carry = (p >> 16) & ShortMask;
          }
          resultArr[resultStart + i + words1Count] = ((short)carry);
        }
      }
    }

    private static void SchoolbookSquare(
      short[] resultArr,
      int resultStart,
      short[] words1,
      int words1Start,
      int words1Count) {
      // Method assumes that resultArr was already zeroed,
      // if resultArr is the same as words1
      int cstart;
      for (int i = 0; i < words1Count; ++i) {
        cstart = resultStart + i;
        {
          short carry = 0;
          int valueBint = ((int)words1[words1Start + i]) & ShortMask;
          for (int j = 0; j < words1Count; ++j) {
            int p;
            p = (((int)words1[words1Start + j]) & ShortMask) * valueBint;
            p += ((int)carry) & ShortMask;
            if (i != 0) {
              p += ((int)resultArr[cstart + j]) & ShortMask;
            }
            resultArr[cstart + j] = (short)p;
            carry = (short)(p >> 16);
          }
          resultArr[cstart + words1Count] = carry;
        }
      }
    }

    private static short ShiftWordsLeftByBits(
      short[] r,
      int rstart,
      int count,
      int shiftBits) {
      int u;
      int carry = 0;
      if (shiftBits != 0) {
        int sb16 = 16 - shiftBits;
        int rs = rstart;
        for (int i = 0; i < count; ++i, ++rs) {
          u = r[rs];
          r[rs] = ((short)((u << shiftBits) | carry));
          carry = (u & ShortMask) >> sb16;
        }
      }
      return (short)carry;
    }

    private static void ShiftWordsLeftByWords(
      short[] r,
      int rstart,
      int n,
      int shiftWords) {
      shiftWords = Math.min(shiftWords, n);
      if (shiftWords != 0) {
        for (int i = n - 1; i >= shiftWords; --i) {
          r[rstart + i] = r[rstart + i - shiftWords];
        }
        java.util.Arrays.fill(r, rstart, (rstart)+(shiftWords), (short)0);
      }
    }

    private static short ShiftWordsRightByBits(
      short[] r,
      int rstart,
      int n,
      int shiftBits) {
      short u, carry = 0;
      {
        if (shiftBits != 0) {
          for (int i = n; i > 0; --i) {
            u = r[rstart + i - 1];
            r[rstart + i - 1] = (short)((((((int)u) & ShortMask) >>
              (int)shiftBits) & ShortMask) | (((int)carry) &
                0xffff));
            carry = (short)((((int)u) & ShortMask) << (int)(16 - shiftBits));
          }
        }
        return carry;
      }
    }

    private static short ShiftWordsRightByBitsSignExtend(
      short[] r,
      int rstart,
      int n,
      int shiftBits) {
      {
        short u, carry = (short)((int)0xffff << (int)(16 - shiftBits));
        if (shiftBits != 0) {
          for (int i = n; i > 0; --i) {
            u = r[rstart + i - 1];
            r[rstart + i - 1] = (short)(((((int)u) & ShortMask) >>
              (int)shiftBits) | (((int)carry) & ShortMask));
            carry = (short)((((int)u) & ShortMask) << (int)(16 - shiftBits));
          }
        }
        return carry;
      }
    }

    private static void ShiftWordsRightByWordsSignExtend(
      short[] r,
      int rstart,
      int n,
      int shiftWords) {
      shiftWords = Math.min(shiftWords, n);
      if (shiftWords != 0) {
        for (int i = 0; i + shiftWords < n; ++i) {
          r[rstart + i] = r[rstart + i + shiftWords];
        }
        rstart += n - shiftWords;
        // Sign extend
        for (int i = 0; i < shiftWords; ++i) {
          r[rstart + i] = ((short)0xffff);
        }
      }
    }

    private static short[] ShortenArray(short[] reg, int wordCount) {
      if (reg.length > 32) {
        int newLength = wordCount;
        if (newLength < reg.length && (reg.length - newLength) >= 16) {
          // Reallocate the array if the desired length
          // is much smaller than the current length
          short[] newreg = new short[newLength];
          System.arraycopy(reg, 0, newreg, 0, Math.min(newLength, reg.length));
          reg = newreg;
        }
      }
      return reg;
    }

    private static int SubtractWords1IsOneBigger(
      short[] c,
      int cstart,
      short[] words1,
      int astart,
      short[] words2,
      int bstart,
      int words1Count) {
      // Assumes that words2's count is 1 less
      {
        int u;
        u = 0;
        int cm1 = words1Count - 1;
        for (int i = 0; i < cm1; i += 1) {
          u = (((int)words1[astart]) & ShortMask) - (((int)words2[bstart]) &
            0xffff) - (int)((u >> 31) & 1);
          c[cstart++] = (short)u;
          ++astart;
          ++bstart;
        }
        u = (((int)words1[astart]) & ShortMask) - (int)((u >> 31) & 1);
        c[cstart++] = (short)u;
        return (int)((u >> 31) & 1);
      }
    }

    private static int SubtractWords2IsOneBigger(
      short[] c,
      int cstart,
      short[] words1,
      int astart,
      short[] words2,
      int bstart,
      int words2Count) {
      // Assumes that words1's count is 1 less
      int u;
      u = 0;
      int cm1 = words2Count - 1;
      for (int i = 0; i < cm1; i += 1) {
        u = ((((int)words1[astart]) & ShortMask) -
          (((int)words2[bstart]) & ShortMask) - (int)((u >> 31) & 1));
        c[cstart++] = ((short)u);
        ++astart;
        ++bstart;
      }
      u = 0 - ((((int)words2[bstart]) & ShortMask) - (int)((u >>
        31) & 1));
      c[cstart++] = ((short)u);
      return (int)((u >> 31) & 1);
    }

    private static int SubtractInternal(
      short[] c,
      int cstart,
      short[] words1,
      int astart,
      short[] words2,
      int bstart,
      int n) {
      int u = 0;
      boolean odd = (n & 1) != 0;
      if (odd) {
        --n;
      }
      int mask = 0xffff;
      for (int i = 0; i < n; i += 2) {
        int wb0 = words2[bstart] & mask;
        int wb1 = words2[bstart + 1] & mask;
        int wa0 = words1[astart] & mask;
        int wa1 = words1[astart + 1] & mask;
        u = (wa0 - wb0 - (int)((u >> 31) & 1));
        c[cstart++] = ((short)u);
        u = (wa1 - wb1 - (int)((u >> 31) & 1));
        c[cstart++] = ((short)u);
        astart += 2;
        bstart += 2;
      }
      if (odd) {
        u = ((((int)words1[astart]) & mask) -
          (((int)words2[bstart]) & mask) - (int)((u >> 31) & 1));
        c[cstart++] = ((short)u);
        ++astart;
        ++bstart;
      }
      return (int)((u >> 31) & 1);
    }

    private static void TwosComplement(
      short[] words1,
      int words1Start,
      int n) {
      DecrementWords(words1, words1Start, n, (short)1);
      for (int i = 0; i < n; ++i) {
        words1[words1Start + i] = ((short)(~words1[words1Start +
          i]));
      }
    }

    private int ByteCount() {
      int wc = this.wordCount;
      if (wc == 0) {
        return 0;
      }
      short s = this.words[wc - 1];
      wc = (wc - 1) << 1;
      return (s == 0) ? wc : (((s >> 8) == 0) ? wc + 1 : wc + 2);
    }

    private int PositiveCompare(EInteger t) {
      int size = this.wordCount, tempSize = t.wordCount;
      return (
          size == tempSize) ? Compare(
          this.words,
          0,
          t.words,
          0,
          (int)size) : (size > tempSize ? 1 : -1);
    }

    private EInteger[] RootRemInternal(EInteger root, boolean useRem) {
      if (root.compareTo(1) == 0) {
        EInteger thisValue = this;
        return new EInteger[] { thisValue, EInteger.FromInt32(0) };
      }
      if (root.compareTo(1) < 0) {
        throw new IllegalArgumentException("root");
      }
      if (root.compareTo(2) == 0) {
        return this.SqrtRemInternal(useRem);
      }
      if (this.signum() <= 0) {
        return new EInteger[] { EInteger.FromInt32(0), EInteger.FromInt32(0) };
      }
      if (this.equals(EInteger.FromInt32(1))) {
        return new EInteger[] { EInteger.FromInt32(1), EInteger.FromInt32(0) };
      }
      // if (this.CanFitInInt64()) {
      // long v = this.ToInt64Checked();
      // int bl = NumberUtility.BitLength(v);
      // }
      EInteger bl = this.GetUnsignedBitLengthAsEInteger();
      EInteger rm1 = root.Subtract(1);
      EInteger shift = EInteger.Max(
          EInteger.FromInt32(0),
          bl.Multiply(rm1).Divide(root).Subtract(1));
      EInteger ret = this.ShiftRight(shift);
      // NOTE: ret is an upper bound of the root
      if (ret.signum() > 0) {
        // System.out.println("this->"+this+" initial->"+ret);
        while (true) {
          EInteger oldret = ret;
          // System.out.println(" thiswc -> " + this.wordCount +
          // " :: wc -> " + ret.wordCount + (ret.wordCount==1 ?
          // ("=>"+this) : ""));
          ret = this.Divide(ret.Pow(rm1)).Add(ret.Multiply(rm1)).Divide(root);
          if (oldret.equals(ret)) {
            break;
          }
          if (ret.compareTo(oldret) > 0) {
            // Starting to vacillate; break
            ret = oldret;
            break;
          }
        }
      }
      if (useRem) {
        EInteger erem = this.Subtract(ret.Pow(root));
        if (erem.signum() < 0) {
          throw new IllegalStateException();
        }
        return new EInteger[] { ret, erem };
      } else {
        return new EInteger[] { ret, null };
      }
    }

    private EInteger[] SqrtRemInternal(boolean useRem) {
      if (this.signum() <= 0) {
        return new EInteger[] { EInteger.FromInt32(0), EInteger.FromInt32(0) };
      }
      if (this.equals(EInteger.FromInt32(1))) {
        return new EInteger[] { EInteger.FromInt32(1), EInteger.FromInt32(0) };
      }
      EInteger bigintX;
      EInteger bigintY;
      EInteger thisValue = this;
      if (thisValue.CanFitInInt32()) {
        int smallValue = thisValue.ToInt32Checked();
        int smallPowerBits =
          (thisValue.GetUnsignedBitLengthAsEInteger().ToInt32Checked() + 1)
          / 2;
        // No need to check for zero; already done above
        int smallintX = 0;
        int smallintY = 1 << smallPowerBits;
        do {
          smallintX = smallintY;
          smallintY = smallValue / smallintX;
          smallintY += smallintX;
          smallintY >>= 1;
        } while (smallintY < smallintX);
        if (!useRem) {
          return new EInteger[] { EInteger.FromInt32(smallintX), null };
        }
        smallintY = smallintX * smallintX;
        smallintY = smallValue - smallintY;
        return new EInteger[] { EInteger.FromInt32(smallintX), EInteger.FromInt32(smallintY),
        };
      }
      EInteger valueEPowerBits =
        thisValue.GetUnsignedBitLengthAsEInteger().Add(1).Divide(2);
      if (this.wordCount >= 4) {
        int wordsPerPart = (this.wordCount >> 2) +
          ((this.wordCount & 3) > 0 ? 1 : 0);
        long bitsPerPart = wordsPerPart * 16;
        EInteger valueEBitsPerPart = EInteger.FromInt64(bitsPerPart);
        long totalBits = bitsPerPart * 4;
        EInteger valueEBitLength = this.GetUnsignedBitLengthAsEInteger();
        boolean bitLengthEven = valueEBitLength.isEven();
        bigintX = this;
        EInteger eshift = EInteger.FromInt32(0);
        if (valueEBitLength.compareTo(EInteger.FromInt64(totalBits).Subtract(
          1)) < 0) {
          long targetLength = bitLengthEven ? totalBits : (totalBits - 1);
          eshift = EInteger.FromInt64(targetLength).Subtract(valueEBitLength);
          bigintX = bigintX.ShiftLeft(eshift);
        }
        // System.out.println("this=" + (this.ToRadixString(16)));
        // System.out.println("bigx=" + (bigintX.ToRadixString(16)));
        short[] ww = bigintX.words;
        short[] w1 = new short[wordsPerPart];
        short[] w2 = new short[wordsPerPart];
        short[] w3 = new short[wordsPerPart * 2];
        System.arraycopy(ww, 0, w1, 0, wordsPerPart);
        System.arraycopy(ww, wordsPerPart, w2, 0, wordsPerPart);
        System.arraycopy(ww, wordsPerPart * 2, w3, 0, wordsPerPart * 2);

        EInteger e1 = new EInteger(CountWords(w1), w1, false);
        EInteger e2 = new EInteger(CountWords(w2), w2, false);
        EInteger e3 = new EInteger(CountWords(w3), w3, false);
        EInteger[] srem = e3.SqrtRemInternal(true);
        // System.out.println("sqrt0({0})[depth={3}] = { 1},{2}"
        // , e3, srem[0], srem[1], 0);
        // System.out.println("sqrt1({0})[depth={3}] = { 1},{2}"
        // , e3, srem2.get(0), srem2.get(1), 0);
        // if (!srem[0].equals(srem2.get(0)) || !srem[1].equals(srem2.get(1))) {
        // throw new IllegalStateException(this.toString());
        // }
        EInteger[] qrem = srem[1].ShiftLeft(
            valueEBitsPerPart).Add(e2).DivRem(
            srem[0].ShiftLeft(1));
        EInteger sqroot =
          srem[0].ShiftLeft(valueEBitsPerPart).Add(qrem[0]);
        EInteger sqrem = qrem[1].ShiftLeft(
            valueEBitsPerPart).Add(e1).Subtract(
            qrem[0].Multiply(qrem[0]));
        // System.out.println("sqrem=" + sqrem + ",sqroot=" + sqroot);
        if (sqrem.signum() < 0) {
          if (useRem) {
            sqrem = sqrem.Add(sqroot.ShiftLeft(1)).Subtract(EInteger.FromInt32(1));
          }
          sqroot = sqroot.Subtract(EInteger.FromInt32(1));
        }
        EInteger[] retarr = new EInteger[2];
        retarr[0] = sqroot.ShiftRight(eshift.ShiftRight(1));
        if (useRem) {
          if (eshift.isZero()) {
            retarr[1] = sqrem;
          } else {
            retarr[1] = this.Subtract(retarr[0].Multiply(retarr[0]));
          }
        }
        return retarr;
      }
      bigintX = EInteger.FromInt32(0);
      bigintY = EInteger.FromInt32(1).ShiftLeft(valueEPowerBits);
      do {
        bigintX = bigintY;
        // System.out.println("" + thisValue + " " + bigintX);
        bigintY = thisValue.Divide(bigintX);
        bigintY = bigintY.Add(bigintX);
        bigintY = bigintY.ShiftRight(1);
      } while (bigintY != null && bigintY.compareTo(bigintX) < 0);
      if (!useRem) {
        return new EInteger[] { bigintX, null };
      }
      bigintY = bigintX.Multiply(bigintX);
      bigintY = thisValue.Subtract(bigintY);
      return new EInteger[] { bigintX, bigintY,
      };
    }

    /**
     * Returns one added to this arbitrary-precision integer.
     * @return The given arbitrary-precision integer plus one.
     */
    public EInteger Increment() {
      return this.Add(EInteger.FromInt32(1));
    }

    /**
     * Returns one subtracted from this arbitrary-precision integer.
     * @return The given arbitrary-precision integer minus one.
     */
    public EInteger Decrement() {
      return this.Subtract(EInteger.FromInt32(1));
    }

    // Begin integer conversions

    /**
     * Converts this number's value to a byte (from 0 to 255) if it can fit in a
     * byte (from 0 to 255).
     * @return This number's value as a byte (from 0 to 255).
     * @throws ArithmeticException This value is less than 0 or greater than 255.
     */
    public byte ToByteChecked() {
      int val = this.ToInt32Checked();
      if (val < 0 || val > 255) {
        throw new ArithmeticException("This Object's value is out of range");
      }
      return (byte)(val & 0xff);
    }

    /**
     * Converts this number to a byte (from 0 to 255), returning the
     * least-significant bits of this number's two's-complement form.
     * @return This number, converted to a byte (from 0 to 255).
     */
    public byte ToByteUnchecked() {
      int val = this.ToInt32Unchecked();
      return (byte)(val & 0xff);
    }

    /**
     * Converts a byte (from 0 to 255) to an arbitrary-precision integer.
     * @param inputByte The number to convert as a byte (from 0 to 255).
     * @return This number's value as an arbitrary-precision integer.
     */
    public static EInteger FromByte(byte inputByte) {
      int val = ((int)inputByte) & 0xff;
      return FromInt32(val);
    }

    /**
     * Converts this number's value to a 16-bit signed integer if it can fit in a
     * 16-bit signed integer.
     * @return This number's value as a 16-bit signed integer.
     * @throws ArithmeticException This value is less than -32768 or greater than
     * 32767.
     */
    public short ToInt16Checked() {
      int val = this.ToInt32Checked();
      if (val < -32768 || val > 32767) {
        throw new ArithmeticException("This Object's value is out of range");
      }
      return (short)(val & ShortMask);
    }

    /**
     * Converts this number to a 16-bit signed integer, returning the
     * least-significant bits of this number's two's-complement form.
     * @return This number, converted to a 16-bit signed integer.
     */
    public short ToInt16Unchecked() {
      int val = this.ToInt32Unchecked();
      return (short)(val & ShortMask);
    }

    /**
     * Converts a 16-bit signed integer to an arbitrary-precision integer.
     * @param inputInt16 The number to convert as a 16-bit signed integer.
     * @return This number's value as an arbitrary-precision integer.
     */
    public static EInteger FromInt16(short inputInt16) {
      int val = (int)inputInt16;
      return FromInt32(val);
    }

    // End integer conversions
  }
