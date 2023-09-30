package com.upokecenter.test;

import java.util.*;

import java.util.regex.*;
import org.junit.Assert;

import com.upokecenter.util.*;
import com.upokecenter.numbers.*;

  public final class DecTestUtil {
private DecTestUtil() {
}
    private static final String TestLineRegex =

  "^([A-Za-z0-9_]+)\\s+([A-Za-z0-9_\\-]+)\\s+(\\'[^\\']*\\'|\\S+)\\s+(?:(\\S+)\\s+)?(?:(\\S+)\\s+)?->\\s+(\\S+)\\s*(.*)";

    private static final Pattern ValuePropertyLine = Pattern.compile(
      "^(\\w+)\\:\\s*(\\S+).*");

    private static final Pattern ValueQuotes = Pattern.compile(
      "^[\\'\\\"]|[\\'\\\"]$");

    private static final Pattern ValueTestLine = Pattern.compile(
      TestLineRegex);

    public static String[] SplitAtFast(
      String str,
      char c,
      int minChunks,
      int maxChunks) {
      int[] chunks = new int[maxChunks];
      String[] ret;
      int chunk = 0;
      if (str == null) {
        throw new NullPointerException("str");
      }
      for (int i = 0; i < str.length() && chunk < maxChunks; ++i) {
        if (str.charAt(i) == c) {
          chunks[chunk++] = i;
        }
      }
      if (chunk >= minChunks - 1 && chunk < maxChunks) {
        chunks[chunk++] = str.length();
      } else if (chunk < minChunks) {
        return null;
      }
      ret = new String[chunk];
      for (int i = 0; i < chunk; ++i) {
        int st = (i == 0) ? 0 : chunks[i - 1] + 1;
        ret[i] = str.substring(st, (st)+(chunks[i] - st));
      }
      return ret;
    }

    public static String[] SplitAt(String str, String delimiter) {
      if (delimiter == null) {
        throw new NullPointerException("delimiter");
      }
      if (delimiter.length() == 0) {
        throw new IllegalArgumentException("delimiter is empty.");
      }
      if (((str) == null || (str).length() == 0)) {
        return new String[] { "" };
      }
      int index = 0;
      boolean first = true;
      ArrayList<String> strings = null;
      int delimLength = delimiter.length();
      while (true) {
        int index2 = str.indexOf(delimiter, index);
        if (index2 < 0) {
          if (first) {
            String[] strret = new String[1];
            strret[0] = str;
            return strret;
          }
          strings = (strings == null) ? (new ArrayList<String>()) : strings;
          strings.add(str.substring(index));
          break;
        } else {
          first = false;
          String newstr = str.substring(index, (index)+(index2 - index));
          strings = (strings == null) ? (new ArrayList<String>()) : strings;
          strings.add(newstr);
          index = index2 + delimLength;
        }
      }
      return strings.toArray(new String[] { });
    }

    public static String[] SplitAtSpaceRuns(String str) {
      if (((str) == null || (str).length() == 0)) {
        return new String[] { "" };
      }
      int index = 0;
      boolean first = true;
      ArrayList<String> strings = null;
      while (true) {
        int index2 = str.indexOf(' ',index);
        if (index2 < 0) {
          if (first) {
            String[] strret = new String[1];
            strret[0] = str;
            return strret;
          }
          strings = (strings == null) ? (new ArrayList<String>()) : strings;
          strings.add(str.substring(index));
          break;
        } else {
          first = false;
          String newstr = str.substring(index, (index)+(index2 - index));
          strings = (strings == null) ? (new ArrayList<String>()) : strings;
          strings.add(newstr);
          index = index2 + 1;
          // skip further spaces
          while (index < str.length() && str.charAt(index) == ' ') {
            ++index;
          }
        }
      }
      return strings.toArray(new String[] { });
    }

    /**
     * <p>Returns a string with the basic upper-case letters A to Z (U+0041 to
     * U+005A) converted to lower-case. Other characters remain unchanged.</p>
     * @param str <p>The parameter {@code str} is a text string.</p>
     * @return <p>The converted string, or null if {@code str} is null.</p>
     *
     */
    public static String ToLowerCaseAscii(String str) {
      if (str == null) {
        return null;
      }
      int len = str.length();
      char c = (char)0;
      boolean hasUpperCase = false;
      for (int i = 0; i < len; ++i) {
        c = str.charAt(i);
        if (c >= 'A' && c <= 'Z') {
          hasUpperCase = true;
          break;
        }
      }
      if (!hasUpperCase) {
        return str;
      }
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < len; ++i) {
        c = str.charAt(i);
        if (c >= 'A' && c <= 'Z') {
          builder.append((char)(c + 0x20));
        } else {
          builder.append(c);
        }
      }
      return builder.toString();
    }

    private static <TKey, TValue> TValue GetKeyOrDefault(
      Map<TKey, TValue> dict,
      TKey key,
      TValue defaultValue) {
      return (!dict.containsKey(key)) ? defaultValue : dict.get(key);
    }

    private static int StringToIntAllowPlus(String str) {
      if (((str) == null || (str).length() == 0)) {
        return 0;
      }
      return (str.charAt(0) == '+') ? TestCommon.StringToInt(str.substring(1)) :
        TestCommon.StringToInt(str);
    }

    public static String ParseJSONString(String str) {
      int c;
      if (str == null) {
        throw new NullPointerException("str");
      }
      if (str.length() == 0 || str.charAt(0) != '"') {
        return null;
      }
      int index = 1;
      StringBuilder sb = new StringBuilder();
      while (index < str.length()) {
        c = index >= str.length() ? -1 : str.charAt(index++);
        if (c == -1 || c < 0x20) {
          return null;
        }
        if ((c & 0xfc00) == 0xd800 && index < str.length() &&
          (str.charAt(index) & 0xfc00) == 0xdc00) {
          // Get the Unicode code point for the surrogate pair
          c = 0x10000 + ((c & 0x3ff) << 10) + (str.charAt(index) & 0x3ff);
          ++index;
        } else if ((c & 0xf800) == 0xd800) {
          return null;
        }
        switch (c) {
          case '\\':
            c = index >= str.length() ? -1 : str.charAt(index++);
            switch (c) {
              case '\\':
                sb.append('\\');
                break;
              case '/':
                // Now allowed to be escaped under RFC 8259
                sb.append('/');
                break;
              case '\"':
                sb.append('\"');
                break;
              case 'b':
                sb.append('\b');
                break;
              case 'f':
                sb.append('\f');
                break;
              case 'n':
                sb.append('\n');
                break;
              case 'r':
                sb.append('\r');
                break;
              case 't':
                sb.append('\t');
                break;
              case 'u': { // Unicode escape
                c = 0;
                // Consists of 4 hex digits
                for (int i = 0; i < 4; ++i) {
                  int ch = index >= str.length() ? -1 : str.charAt(index++);
                  if (ch >= '0' && ch <= '9') {
                    c <<= 4;
                    c |= ch - '0';
                  } else if (ch >= 'A' && ch <= 'F') {
                    c <<= 4;
                    c |= ch + 10 - 'A';
                  } else if (ch >= 'a' && ch <= 'f') {
                    c <<= 4;
                    c |= ch + 10 - 'a';
                  } else {
                    return null;
                  }
                }
                if ((c & 0xf800) != 0xd800) {
                  // Non-surrogate
                  sb.append((char)c);
                } else if ((c & 0xfc00) == 0xd800) {
                  int ch = index >= str.length() ? -1 : str.charAt(index++);
                  if (ch != '\\' ||
                    (index >= str.length() ? -1 : str.charAt(index++)) != 'u') {
                    return null;
                  }
                  int c2 = 0;
                  for (int i = 0; i < 4; ++i) {
                    ch = index >= str.length() ? -1 : str.charAt(index++);
                    if (ch >= '0' && ch <= '9') {
                      c2 <<= 4;
                      c2 |= ch - '0';
                    } else if (ch >= 'A' && ch <= 'F') {
                      c2 <<= 4;
                      c2 |= ch + 10 - 'A';
                    } else if (ch >= 'a' && ch <= 'f') {
                      c2 <<= 4;
                      c2 |= ch + 10 - 'a';
                    } else {
                      return null;
                    }
                  }
                  if ((c2 & 0xfc00) != 0xdc00) {
                    return null;
                  } else {
                    sb.append((char)c);
                    sb.append((char)c2);
                  }
                } else {
                  return null;
                }
                break;
              }
              default: {
                // NOTE: Includes surrogate code
                // units
                return null;
              }
            }
            break;
          case 0x22: // double quote
            return sb.toString();
          default: {
            // NOTE: Assumes the character reader
            // throws an error on finding illegal surrogate
            // pairs in the String or invalid encoding
            // in the stream
            if ((c >> 16) == 0) {
              sb.append((char)c);
            } else {
              sb.append((char)((((c - 0x10000) >> 10) & 0x3ff) | 0xd800));
              sb.append((char)(((c - 0x10000) & 0x3ff) | 0xdc00));
            }
            break;
          }
        }
      }
      return null;
    }

    public static void AssertFlagsRestricted(
      int expected,
      int actual,
      String str) {
      actual &= EContext.FlagInexact | EContext.FlagUnderflow |
        EContext.FlagOverflow | EContext.FlagInvalid |
        EContext.FlagDivideByZero;
      if (expected == actual) {
        return;
      }
      Assert.assertEquals("Inexact: " + str,(expected & EContext.FlagInexact) != 0,(actual & EContext.FlagInexact) != 0);
      Assert.assertEquals("Overflow: " + str,(expected & EContext.FlagOverflow) != 0,(actual & EContext.FlagOverflow) != 0);
      Assert.assertEquals("Underflow: " + str,(expected & EContext.FlagUnderflow) != 0,(actual & EContext.FlagUnderflow) != 0);
      Assert.assertEquals("Invalid: " + str,(expected & EContext.FlagInvalid) != 0,(actual & EContext.FlagInvalid) != 0);
      Assert.assertEquals("DivideByZero: " + str,(expected & EContext.FlagDivideByZero) != 0,(actual & EContext.FlagDivideByZero) != 0);
    }

    public static boolean Contains(String str, String sub) {
      if (str == null) {
        throw new NullPointerException("str");
      }
      if (sub == null) {
        throw new NullPointerException("sub");
      }
      if (sub.length() == 1) {
        for (int i = 0; i < str.length(); ++i) {
          if (str.charAt(i) == sub.charAt(0)) {
            return true;
          }
        }
        return false;
      }
      return str.indexOf(sub) >= 0;
    }

    private static boolean StartsWith(String str, String sub) {
      return str.startsWith(sub);
    }

    private static boolean EndsWith(String str, String sub) {
      return str.endsWith(sub);
    }

    private static int HexInt(String str) {
      return EInteger.FromRadixString(str, 16).ToInt32Unchecked();
    }

    private static EContext SetRounding(
      EContext ctx,
      String round) {
      if (round.equals(">")) {
        ctx = ctx.WithRounding(ERounding.Ceiling);
      }
      if (round.equals("<")) {
        ctx = ctx.WithRounding(ERounding.Floor);
      }
      if (round.equals("0")) {
        ctx = ctx.WithRounding(ERounding.Down);
      }
      if (round.equals("=0")) {
        ctx = ctx.WithRounding(ERounding.HalfEven);
      }
      if (round.equals("h>") ||
        round.equals("=^")) {
        ctx = ctx.WithRounding(ERounding.HalfUp);
      }
      if (round.equals("h<")) {
        ctx = ctx.WithRounding(ERounding.HalfDown);
      }
      return ctx;
    }

    private static String ConvertOp(String s) {
      return s.equals("S") ? "sNaN" :
        ((s.equals("Q") || s.equals("#")) ? "NaN" : s);
    }

    private interface IExtendedNumber extends Comparable<IExtendedNumber> {
      Object getValue();

      IExtendedNumber Add(IExtendedNumber b, EContext ctx);

      IExtendedNumber Subtract(IExtendedNumber b, EContext ctx);

      IExtendedNumber Multiply(IExtendedNumber b, EContext ctx);

      IExtendedNumber Divide(IExtendedNumber b, EContext ctx);

      IExtendedNumber SquareRoot(EContext ctx);

      IExtendedNumber MultiplyAndAdd(
        IExtendedNumber b,
        IExtendedNumber c,
        EContext ctx);

      IExtendedNumber MultiplyAndSubtract(
        IExtendedNumber b,
        IExtendedNumber c,
        EContext ctx);

      boolean IsQuietNaN();

      boolean IsSignalingNaN();

      boolean IsInfinity();

      boolean IsZeroValue();
    }

    private static final class DecimalNumber implements IExtendedNumber {
      private EDecimal ed;

      public static DecimalNumber Create(EDecimal dec) {
        DecimalNumber dn = new DecimalNumber();
        dn.ed = dec;
        return dn;
      }

      @Override public boolean equals(Object obj) {
        DecimalNumber other = ((obj instanceof DecimalNumber) ? (DecimalNumber)obj : null);
        return (other != null) && (((this.ed) == null) ? ((other.ed) == null) : (this.ed).equals(other.ed));
      }

      @Override public int hashCode() {
        int valueHashCode = 703582279;
        {
          if (this.ed != null) {
            valueHashCode += 703582387 * this.ed.hashCode();
          }
        }
        return valueHashCode;
      }

      @Override public String toString() {
        return this.ed.toString();
      }

      public final Object getValue() {
          return this.ed;
        }

      private static EDecimal ToValue(IExtendedNumber en) {
        return (EDecimal)en.getValue();
      }

      public IExtendedNumber Add(
        IExtendedNumber b,
        EContext ctx) {
        return Create(this.ed.Add(ToValue(b), ctx));
      }

      public IExtendedNumber Subtract(
        IExtendedNumber b,
        EContext ctx) {
        return Create(this.ed.Subtract(ToValue(b), ctx));
      }

      public IExtendedNumber Multiply(
        IExtendedNumber b,
        EContext ctx) {
        return Create(this.ed.Multiply(ToValue(b), ctx));
      }

      public IExtendedNumber Divide(
        IExtendedNumber b,
        EContext ctx) {
        return Create(this.ed.Divide(ToValue(b), ctx));
      }

      public IExtendedNumber SquareRoot(EContext ctx) {
        return Create(this.ed.Sqrt(ctx));
      }

      public IExtendedNumber MultiplyAndAdd(
        IExtendedNumber b,
        IExtendedNumber c,
        EContext ctx) {
        return Create(this.ed.MultiplyAndAdd(ToValue(b), ToValue(c), ctx));
      }

      public IExtendedNumber MultiplyAndSubtract(
        IExtendedNumber b,
        IExtendedNumber c,
        EContext ctx) {
        return Create(this.ed.MultiplyAndSubtract(
              ToValue(b),
              ToValue(c),
              ctx));
      }

      public boolean IsQuietNaN() {
        return this.ed != null && ToValue(this).IsQuietNaN();
      }

      public boolean IsSignalingNaN() {
        return this.ed != null && ToValue(this).IsSignalingNaN();
      }

      public boolean IsInfinity() {
        return this.ed != null && ToValue(this).IsInfinity();
      }

      public boolean IsZeroValue() {
        return this.ed != null && ToValue(this).isZero();
      }

      public int compareTo(IExtendedNumber other) {
        DecimalNumber dn = ((other instanceof DecimalNumber) ? (DecimalNumber)other : null);
        EDecimal dned = dn == null ? null : dn.ed;
        return (this.ed == null) ? ((dned == null) ? 0 : -1) : (dned == null ?
            1 : this.ed.compareTo(dned));
      }
    }

    private static final class BinaryNumber implements IExtendedNumber {
      private EFloat ef;

      public int compareTo(IExtendedNumber other) {
        BinaryNumber dn = ((other instanceof BinaryNumber) ? (BinaryNumber)other : null);
        EFloat dned = dn == null ? null : dn.ef;
        return (this.ef == null) ? ((dned == null) ? 0 : -1) : (dned == null ?
            1 : this.ef.compareTo(dned));
      }

      public static BinaryNumber Create(EFloat dec) {
        BinaryNumber dn = new BinaryNumber();
        if (dec == null) {
          throw new NullPointerException("dec");
        }
        dn.ef = dec;
        return dn;
      }

      public static BinaryNumber FromString(String str) {
        if (str.equals("NaN")) {
          return Create(EFloat.NaN);
        }
        if (str.equals("sNaN")) {
          return Create(EFloat.SignalingNaN);
        }
        if (str.equals("+Zero")) {
          return Create(EFloat.Zero);
        }
        if (str.equals("0x0")) {
          return Create(EFloat.Zero);
        }
        if (str.equals("0x1")) {
          return Create(EFloat.One);
        }
        if (str.equals("-Zero")) {
          return Create(EFloat.NegativeZero);
        }
        if (str.equals("-Inf")) {
          return Create(EFloat.NegativeInfinity);
        }
        if (str.equals("+Inf")) {
          return Create(EFloat.PositiveInfinity);
        }
        int offset = 0;
        boolean negative = false;
        if (str.charAt(0) == '+' || str.charAt(0) == '-') {
          negative = str.charAt(0) == '-';
          ++offset;
        }
        int i = offset;
        int beforeDec = 0;
        int mantissa = 0;
        int exponent = 0;
        boolean haveDec = false;
        boolean haveBinExp = false;
        boolean haveDigits = false;
        for (; i < str.length(); ++i) {
          if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            int thisdigit = (int)(str.charAt(i) - '0');
            if ((beforeDec >> 28) != 0) {
              throw new NumberFormatException(str);
            }
            beforeDec <<= 4;
            beforeDec |= thisdigit;
            haveDigits = true;
          } else if (str.charAt(i) >= 'A' && str.charAt(i) <= 'F') {
            int thisdigit = (int)(str.charAt(i) - 'A') + 10;
            if ((beforeDec >> 28) != 0) {
              throw new NumberFormatException(str);
            }
            beforeDec <<= 4;
            beforeDec |= thisdigit;
            haveDigits = true;
          } else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'f') {
            int thisdigit = (int)(str.charAt(i) - 'a') + 10;
            if ((beforeDec >> 28) != 0) {
              throw new NumberFormatException(str);
            }
            beforeDec <<= 4;
            beforeDec |= thisdigit;
            haveDigits = true;
          } else if (str.charAt(i) == '.') {
            // Decimal point reached
            haveDec = true;
            ++i;
            break;
          } else if (str.charAt(i) == 'P' || str.charAt(i) == 'p') {
            // Binary exponent reached
            haveBinExp = true;
            ++i;
            break;
          } else {
            throw new NumberFormatException(str);
          }
        }
        if (!haveDigits) {
          throw new NumberFormatException(str);
        }
        if (haveDec) {
          haveDigits = false;
          int afterDec = 0;
          for (; i < str.length(); ++i) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
              int thisdigit = (int)(str.charAt(i) - '0');
              if ((afterDec >> 28) != 0) {
                throw new NumberFormatException(str);
              }
              afterDec <<= 4;
              afterDec |= thisdigit;
              haveDigits = true;
            } else if (str.charAt(i) >= 'A' && str.charAt(i) <= 'F') {
              int thisdigit = (int)(str.charAt(i) - 'A') + 10;
              if ((afterDec >> 28) != 0) {
                throw new NumberFormatException(str);
              }
              afterDec <<= 4;
              afterDec |= thisdigit;
              haveDigits = true;
            } else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'f') {
              int thisdigit = (int)(str.charAt(i) - 'a') + 10;
              if ((afterDec >> 28) != 0) {
                throw new NumberFormatException(str);
              }
              afterDec <<= 4;
              afterDec |= thisdigit;
              haveDigits = true;
            } else if (str.charAt(i) == 'P' || str.charAt(i) == 'p') {
              // Binary exponent reached
              haveBinExp = true;
              ++i;
              break;
            } else {
              throw new NumberFormatException(str);
            }
          }
          if (!haveDigits) {
            throw new NumberFormatException(str);
          }
          mantissa = (beforeDec << 23) | afterDec;
        } else {
          mantissa = beforeDec;
        }
        if (negative) {
          mantissa = -mantissa;
        }
        if (haveBinExp) {
          haveDigits = false;
          boolean negexp = false;
          if (i < str.length() && str.charAt(i) == '-') {
            negexp = true;
            ++i;
          }
          for (; i < str.length(); ++i) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
              int thisdigit = (int)(str.charAt(i) - '0');
              if ((exponent >> 28) != 0) {
                throw new NumberFormatException(str);
              }
              exponent *= 10;
              exponent += thisdigit;
              haveDigits = true;
            } else {
              throw new NumberFormatException(str);
            }
          }
          if (!haveDigits) {
            throw new NumberFormatException(str);
          }
          if (negexp) {
            exponent = -exponent;
          }
          exponent -= 23;
        }
        if (i != str.length()) {
          throw new NumberFormatException(str);
        }
        // System.out.println("mant=" + mantissa + " exp=" + exponent);
        return Create(
            EFloat.Create(mantissa, exponent));
      }

      public static BinaryNumber FromFloatWords(int[] words) {
        if (words == null) {
          throw new NullPointerException("words");
        }
        if (words.length == 1) {
          boolean neg = (words[0] >> 31) != 0;
          int exponent = (words[0] >> 23) & 0xff;
          int mantissa = words[0] & 0x7fffff;
          if (exponent == 255) {
            return (mantissa == 0) ? Create(neg ? EFloat.NegativeInfinity :
                EFloat.PositiveInfinity) : (((mantissa &
                    0x00400000) != 0) ? Create(EFloat.NaN) :
                Create(EFloat.SignalingNaN));
          }
          if (exponent == 0) {
            if (mantissa == 0) {
              return Create(neg ? EFloat.NegativeZero :
                  EFloat.Zero);
            }
            // subnormal
            exponent = -126;
          } else {
            // normal
            exponent -= 127;
            mantissa |= 0x800000;
          }
          EInteger bigmantissa = EInteger.FromInt32(mantissa);
          if (neg) {
            bigmantissa = bigmantissa.Negate();
          }
          exponent -= 23;
          return Create(
              EFloat.Create(
                bigmantissa,
                EInteger.FromInt32(exponent)));
        }
        if (words.length == 2) {
          boolean neg = (words[0] >> 31) != 0;
          int exponent = (words[0] >> 20) & 0x7ff;
          int mantissa = words[0] & 0xfffff;
          int mantissaNonzero = mantissa | words[1];
          if (exponent == 2047) {
            return (mantissaNonzero == 0) ? Create(neg ?
                EFloat.NegativeInfinity : EFloat.PositiveInfinity) :
              (((mantissa & 0x00080000) != 0) ? Create(EFloat.NaN) :

                Create(EFloat.SignalingNaN));
          }
          if (exponent == 0) {
            if (mantissaNonzero == 0) {
              return Create(neg ? EFloat.NegativeZero :
                  EFloat.Zero);
            }
            // subnormal
            exponent = -1022;
          } else {
            // normal
            exponent -= 1023;
            mantissa |= 0x100000;
          }
          EInteger bigmantissa = EInteger.FromInt32(0);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64((mantissa >> 16) & 0xffff));
          bigmantissa = bigmantissa.ShiftLeft(16);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64(mantissa & 0xffff));
          bigmantissa = bigmantissa.ShiftLeft(16);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64((words[1] >> 16) & 0xffff));
          bigmantissa = bigmantissa.ShiftLeft(16);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64(words[1] & 0xffff));
          if (neg) {
            bigmantissa = bigmantissa.Negate();
          }
          exponent -= 52;
          return Create(
              EFloat.Create(
                bigmantissa,
                EInteger.FromInt32(exponent)));
        }
        if (words.length == 4) {
          boolean neg = (words[0] >> 31) != 0;
          int exponent = (words[0] >> 16) & 0x7fff;
          int mantissa = words[0] & 0xffff;
          int mantissaNonzero = mantissa | words[3] | words[1] | words[2];
          if (exponent == 0x7fff) {
            return (mantissaNonzero == 0) ? Create(neg ?
                EFloat.NegativeInfinity : EFloat.PositiveInfinity) :
              (((mantissa & 0x00008000) != 0) ? Create(EFloat.NaN) :

                Create(EFloat.SignalingNaN));
          }
          if (exponent == 0) {
            if (mantissaNonzero == 0) {
              return Create(neg ? EFloat.NegativeZero :
                  EFloat.Zero);
            }
            // subnormal
            exponent = -16382;
          } else {
            // normal
            exponent -= 16383;
            mantissa |= 0x10000;
          }
          EInteger bigmantissa = EInteger.FromInt32(0);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64((mantissa >> 16) & 0xffff));
          bigmantissa = bigmantissa.ShiftLeft(16);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64(mantissa & 0xffff));
          bigmantissa = bigmantissa.ShiftLeft(16);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64((words[1] >> 16) & 0xffff));
          bigmantissa = bigmantissa.ShiftLeft(16);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64(words[1] & 0xffff));
          bigmantissa = bigmantissa.ShiftLeft(16);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64((words[2] >> 16) & 0xffff));
          bigmantissa = bigmantissa.ShiftLeft(16);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64(words[2] & 0xffff));
          bigmantissa = bigmantissa.ShiftLeft(16);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64((words[3] >> 16) & 0xffff));
          bigmantissa = bigmantissa.ShiftLeft(16);
          bigmantissa = bigmantissa.Add(EInteger.FromInt64(words[3] & 0xffff));
          if (neg) {
            bigmantissa = bigmantissa.Negate();
          }
          exponent -= 112;
          return Create(
              EFloat.Create(
                bigmantissa,
                EInteger.FromInt32(exponent)));
        }
        throw new IllegalArgumentException("words has a bad length");
      }

      @Override public boolean equals(Object obj) {
        BinaryNumber other = ((obj instanceof BinaryNumber) ? (BinaryNumber)obj : null);
        return (other != null) && (this.ef.compareTo(other.ef) == 0);
      }

      @Override public int hashCode() {
        int valueHashCode = 703582379;
        {
          if (this.ef != null) {
            valueHashCode += 703582447 * this.ef.hashCode();
          }
        }
        return valueHashCode;
      }

      @Override public String toString() {
        return this.ef.toString();
      }

      public final Object getValue() {
          return this.ef;
        }

      public static EFloat ToValue(IExtendedNumber en) {
        return (EFloat)en.getValue();
      }

      public IExtendedNumber Add(
        IExtendedNumber b,
        EContext ctx) {
        return Create(this.ef.Add(ToValue(b), ctx));
      }

      public IExtendedNumber Subtract(
        IExtendedNumber b,
        EContext ctx) {
        return Create(this.ef.Subtract(ToValue(b), ctx));
      }

      public IExtendedNumber Multiply(
        IExtendedNumber b,
        EContext ctx) {
        return Create(this.ef.Multiply(ToValue(b), ctx));
      }

      public IExtendedNumber Divide(
        IExtendedNumber b,
        EContext ctx) {
        return Create(this.ef.Divide(ToValue(b), ctx));
      }

      public BinaryNumber Pow(
        IExtendedNumber b,
        EContext ctx) {
        return Create(this.ef.Pow(ToValue(b), ctx));
      }

      public IExtendedNumber SquareRoot(EContext ctx) {
        return Create(this.ef.Sqrt(ctx));
      }

      public IExtendedNumber MultiplyAndAdd(
        IExtendedNumber b,
        IExtendedNumber c,
        EContext ctx) {
        return Create(this.ef.MultiplyAndAdd(ToValue(b), ToValue(c), ctx));
      }

      public IExtendedNumber MultiplyAndSubtract(
        IExtendedNumber b,
        IExtendedNumber c,
        EContext ctx) {
        return Create(this.ef.MultiplyAndSubtract(ToValue(b), ToValue(c), ctx));
      }

      public boolean IsNear(IExtendedNumber bn) {
        EFloat ulpdiff = EFloat.Create(
            EInteger.FromInt64(2),
            ToValue(this).getExponent());
        return ToValue(this).Subtract(ToValue(bn)).Abs().compareTo(
            ulpdiff) <= 0;
      }

      public void ComparePrint(IExtendedNumber bn) {
        System.out.println("" + ToValue(this).getMantissa() + " man, " +
          ToValue(bn).getMantissa() + " exp");
      }

      public BinaryNumber RoundToIntegralExact(EContext ctx) {
        return Create(this.ef.RoundToIntegerExact(ctx));
      }

      public BinaryNumber Log(EContext ctx) {
        return Create(this.ef.Log(ctx));
      }

      public BinaryNumber Remainder(IExtendedNumber bn, EContext ctx) {
        return Create(this.ef.Remainder(ToValue(bn), ctx));
      }

      public BinaryNumber Exp(EContext ctx) {
        return Create(this.ef.Exp(ctx));
      }

      public BinaryNumber Abs(EContext ctx) {
        return Create(this.ef.Abs(ctx));
      }

      public BinaryNumber Log10(EContext ctx) {
        return Create(this.ef.Log10(ctx));
      }

      public boolean IsQuietNaN() {
        return this.ef != null && ToValue(this).IsQuietNaN();
      }

      public boolean IsSignalingNaN() {
        return this.ef != null && ToValue(this).IsSignalingNaN();
      }

      public boolean IsInfinity() {
        return this.ef != null && ToValue(this).IsInfinity();
      }

      public boolean IsZeroValue() {
        return this.ef != null && ToValue(this).isZero();
      }
    }

    static int ParseLineInput(String ln) {
      if (ln.length() == 0) {
        return 0;
      }
      int ix = -1;
      for (int i = 0; i < ln.length(); ++i) {
        if (ln.charAt(i) == '\u0020') {
          // Space found
          ix = i;
          break;
        }
      }
      // NOTE: ix < 2 includes cases where space is not found
      if (ix < 2 || (ln.charAt(ix - 1) != 'd' && ln.charAt(ix - 1) != 's' &&
          ln.charAt(ix - 1) != 'q')) {
        return 0;
      }
      String[] chunks = SplitAtSpaceRuns(ln);
      if (chunks.length < 4) {
        return 0;
      }
      String type = chunks[0];
      EContext ctx = null;
      String op = "";
      int size = 0;
      if (EndsWith(type, "d")) {
        op = type.substring(0, type.length() - 1);
        ctx = EContext.Binary64;
        size = 1;
      } else if (EndsWith(type, "s")) {
        op = type.substring(0, type.length() - 1);
        ctx = EContext.Binary32;
        size = 0;
      } else if (EndsWith(type, "q")) {
        op = type.substring(0, type.length() - 1);
        ctx = EContext.Binary128;
        size = 2;
      }
      if (ctx == null) {
        return 0;
      }
      String round = chunks[1];
      if (round.length() != 1) {
        { return 0;
        }
      }
      String flags = chunks[3];
      String compareOp = chunks[2];
      // sw.Start();
      switch (round.charAt(0)) {
        case 'm':
          ctx = ctx.WithRounding(ERounding.Floor);
          break;
        case 'p':
          ctx = ctx.WithRounding(ERounding.Ceiling);
          break;
        case 'z':
          ctx = ctx.WithRounding(ERounding.Down);
          break;
        case 'n':
          ctx = ctx.WithRounding(ERounding.HalfEven);
          break;
        default:
          return 0;
      }
      BinaryNumber op1, op2, result;
      switch (size) {
        case 0:
          // single
          if (chunks.length < 6) {
            return 0;
          }
          op1 = BinaryNumber.FromFloatWords(new int[] { HexInt(chunks[4]) });
          op2 = BinaryNumber.FromFloatWords(new int[] { HexInt(chunks[5]) });
          if (chunks.length == 6 || ((chunks[6]) == null || (chunks[6]).length() == 0)) {
            result = op2;
            op2 = null;
          } else {
            result = BinaryNumber.FromFloatWords(new int[] { HexInt(chunks[6]),
            });
          }

          break;
        case 1:
          // double
          if (chunks.length < 8) {
            return 0;
          }
          op1 = BinaryNumber.FromFloatWords(new int[] { HexInt(chunks[4]),
            HexInt(chunks[5]),
          });
          op2 = BinaryNumber.FromFloatWords(new int[] { HexInt(chunks[6]),
            HexInt(chunks[7]),
          });
          if (chunks.length == 8 || ((chunks[8]) == null || (chunks[8]).length() == 0)) {
            result = op2;
            op2 = null;
            return 0;
          }
          result = BinaryNumber.FromFloatWords(new int[] { HexInt(chunks[8]),
            HexInt(chunks[9]),
          });
          break;
        case 2:
          // quad
          if (chunks.length < 12) {
            return 0;
          }
          op1 = BinaryNumber.FromFloatWords(new int[] { HexInt(chunks[4]),
            HexInt(chunks[5]), HexInt(chunks[6]),
            HexInt(chunks[7]),
          });
          op2 = BinaryNumber.FromFloatWords(new int[] { HexInt(chunks[8]),
            HexInt(chunks[9]), HexInt(chunks[10]),
            HexInt(chunks[11]),
          });
          if (chunks.length == 12 || ((chunks[12]) == null || (chunks[12]).length() == 0)) {
            result = op2;
            op2 = null;
          } else {
            result = BinaryNumber.FromFloatWords(new int[] { HexInt(chunks[12]), HexInt(chunks[13]),
              HexInt(chunks[14]), HexInt(chunks[15]),
            });
          }

          break;
        default:
          return 0;
      }

      if (compareOp.equals("uo")) {
        result = BinaryNumber.FromString("NaN");
      }
      int expectedFlags = 0;
      int ignoredFlags = 0;
      if (Contains(flags, "?x")) {
        ignoredFlags |= EContext.FlagInexact;
      } else if (Contains(flags, "x")) {
        expectedFlags |= EContext.FlagInexact;
      }
      if (Contains(flags, "u")) {
        expectedFlags |= EContext.FlagUnderflow;
      }
      if (Contains(flags, "o")) {
        expectedFlags |= EContext.FlagOverflow;
      }
      if (Contains(flags, "v")) {
        expectedFlags |= EContext.FlagInvalid;
      }
      if (Contains(flags, "d")) {
        expectedFlags |= EContext.FlagDivideByZero;
      }

      ctx = ctx.WithBlankFlags();
      if (op.equals("add")) {
        IExtendedNumber d3 = op1.Add(op2, ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        if (op1.IsQuietNaN() && op2.IsSignalingNaN()) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (op.equals("sub")) {
        IExtendedNumber d3 = op1.Subtract(op2, ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        if (op1.IsQuietNaN() && op2.IsSignalingNaN()) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (op.equals("mul")) {
        IExtendedNumber d3 = op1.Multiply(op2, ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        if (op1.IsQuietNaN() && op2.IsSignalingNaN()) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (op.equals("pow")) {
        IExtendedNumber d3 = op1.Pow(op2, ctx);
        // Check for cases that contradict the General Decimal
        // Arithmetic spec
        if (op1.IsZeroValue() && op2.IsZeroValue()) {
          return 0;
        }
        if (((EFloat)op1.getValue()).signum() < 0 && op2.IsInfinity()) {
          return 0;
        }
        boolean powIntegral = op2.equals(op2.RoundToIntegralExact(null));
        if (((EFloat)op1.getValue()).signum() < 0 && !powIntegral) {
          return 0;
        }
        if ((op1.IsQuietNaN() || op1.IsSignalingNaN()) && op2.IsZeroValue()) {
          return 0;
        }
        if (op2.IsInfinity() && op1.Abs(null).equals(
            BinaryNumber.FromString("1"))) {
          return 0;
        }
        expectedFlags &= ~EContext.FlagDivideByZero;
        expectedFlags &= ~EContext.FlagInexact;
        expectedFlags &= ~EContext.FlagUnderflow;
        expectedFlags &= ~EContext.FlagOverflow;
        ignoredFlags |= EContext.FlagInexact;
        ignoredFlags |= EContext.FlagUnderflow;
        ignoredFlags |= EContext.FlagOverflow;
        if (!result.equals(d3)) {
          if (compareOp.equals("vn")) {
            if (!result.IsNear(d3)) {
              Assert.assertEquals(ln, result, d3);
            }
          } else if (compareOp.equals("nb")) {
            if (!result.IsNear(d3)) {
              Assert.assertEquals(ln, result, d3);
            }
          } else {
            Assert.assertEquals(ln, result, d3);
          }
        }
        if (op1.IsQuietNaN() && op2.IsSignalingNaN()) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          ctx.setFlags(ctx.getFlags()&~(ignoredFlags));
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (op.equals("floor")) {
        ctx = ctx.WithRounding(ERounding.Floor);
        IExtendedNumber d3 = op1.RoundToIntegralExact(ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
      } else if (op.equals("fabs")) {
        // NOTE: Fabs never sets flags
        IExtendedNumber d3 = op1.Abs(ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
      } else if (op.equals("ceil")) {
        ctx = ctx.WithRounding(ERounding.Ceiling);
        IExtendedNumber d3 = op1.RoundToIntegralExact(ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
      } else if (op.equals("sqrt")) {
        IExtendedNumber d3 = op1.SquareRoot(ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
      } else if (op.equals("log")) {
        IExtendedNumber d3 = op1.Log(ctx);
        if (!result.equals(d3)) {
          if (compareOp.equals("vn")) {
            if (!result.IsNear(d3)) {
              Assert.assertEquals(ln, result, d3);
            }
          } else if (compareOp.equals("nb")) {
            if (!result.IsNear(d3)) {
              Assert.assertEquals(ln, result, d3);
            }
          } else {
            Assert.assertEquals(ln, result, d3);
          }
        }
        if (!op1.IsZeroValue()) {
          // ignore flags for zero operand, expects
          // divide by zero flag where general decimal
          // spec doesn't set flags in this case
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (op.equals("exp")) {
        IExtendedNumber d3 = op1.Exp(ctx);
        if (!result.equals(d3)) {
          if (compareOp.equals("vn")) {
            if (!result.IsNear(d3)) {
              Assert.assertEquals(ln, result, d3);
            }
          } else if (compareOp.equals("nb")) {
            if (!result.IsNear(d3)) {
              Assert.assertEquals(ln, result, d3);
            }
          } else {
            Assert.assertEquals(ln, result, d3);
          }
        }
        AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
      } else if (op.equals("log10")) {
        IExtendedNumber d3 = op1.Log10(ctx);
        if (!result.equals(d3)) {
          if (compareOp.equals("vn")) {
            if (!result.IsNear(d3)) {
              System.out.println("op1=..." + op1 + " result=" + result +
                " d3=...." + d3);
              Assert.assertEquals(ln, result, d3);
            }
          } else if (compareOp.equals("nb")) {
            if (!result.IsNear(d3)) {
              System.out.println("op1=..." + op1 + " result=" + result +
                " d3=...." + d3);
              Assert.assertEquals(ln, result, d3);
            }
          } else {
            System.out.println("op1=..." + op1 + " result=" + result +
              " d3=...." + d3);
            Assert.assertEquals(ln, result, d3);
          }
        }
        expectedFlags &= ~EContext.FlagInexact;
        ignoredFlags |= EContext.FlagInexact;
        ctx.setFlags(ctx.getFlags()&~(ignoredFlags));
        if (!op1.IsZeroValue()) {
          // ignore flags for zero operand, expects
          // divide by zero flag where general decimal
          // spec doesn't set flags in this case
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (op.equals("div")) {
        IExtendedNumber d3 = op1.Divide(op2, ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        if (op1.IsQuietNaN() && op2.IsSignalingNaN()) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (op.equals("fmod")) {
        IExtendedNumber d3 = op1.Remainder(op2, ctx);
        if ((ctx.getFlags() & EContext.FlagInvalid) != 0 &&
          (expectedFlags & EContext.FlagInvalid) == 0) {
          // Skip since the quotient may be too high to fit an integer,
          // which triggers an invalid operation under the General
          // Decimal Arithmetic specification
          return 0;
        }
        if (!result.equals(d3)) {
          System.out.println("op1=..." + op1 + "\nop2=..." + op2 + "\nresult=" +
            result + "\nd3=...." + d3);
          Assert.assertEquals(ln, result, d3);
        }
        if (op1.IsQuietNaN() && op2.IsSignalingNaN()) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      }
      // sw.Stop();
      return 0;
    }

    static int ParseLine(String ln) {
      return ParseLine(ln, false);
    }

    static int ParseLine(String ln, boolean exactResultCheck) {
      String[] chunks = SplitAtFast(ln, (char)0x20, 4, 8);
      if (chunks == null) {
        return 0;
      }
      String type = chunks[0];
      EContext ctx = null;
      boolean binaryFP = false;
      String op = "";
      if (StartsWith(type, "d32")) {
        ctx = EContext.Decimal32;
        op = type.substring(3);
      }
      if (StartsWith(type, "d64")) {
        ctx = EContext.Decimal64;
        op = type.substring(3);
      }
      if (StartsWith(type, "b32")) {
        ctx = EContext.Binary32;
        binaryFP = true;
        op = type.substring(3);
      }
      if (StartsWith(type, "d128")) {
        ctx = EContext.Decimal128;
        op = type.substring(4);
      }
      if (ctx == null) {
        return 0;
      }
      if (Contains(type, "!")) {
        return 0;
      }
      if (Contains(op, "cff")) {
        // skip test cases for
        // conversion to another floating point format
        return 0;
      }
      boolean squroot = op.equals("V");
      // var mod = op.equals("%");
      boolean div = op.equals("/");
      boolean fma = op.equals("*+");
      boolean fms = op.equals("*-");
      boolean addop = op.equals("+");
      boolean subop = op.equals("-");
      boolean mul = op.equals("*");
      String round = chunks[1];
      ctx = SetRounding(ctx, round);
      int offset = 0;
      String traps = "";
      if (Contains(chunks[2], "x") ||
        chunks[2].equals("i") ||
        StartsWith(chunks[2], "o")) {
        // traps
        ++offset;
        traps = chunks[2];
      }
      if (Contains(traps, "u") || Contains(traps, "o")) {
        // skip tests that trap underflow or overflow,
        // the results there may be wrong
        /* try {
          throw new IllegalStateException();
         } catch (IllegalStateException ex) {
          System.out.print(ln+"\n"+ex);
        }
        */ return 0;
      }
      String op1str = ConvertOp(chunks[2 + offset]);
      String op2str = ConvertOp(chunks[3 + offset]);
      String op3str = "";
      if (chunks.length <= 4 + offset) {
        return 0;
      }
      String sresult = "";
      String flags = "";
      op3str = chunks[4 + offset];
      if (op2str.equals("->")) {
        if (chunks.length <= 5 + offset) {
          return 0;
        }
        op2str = "";
        op3str = "";
        sresult = chunks[4 + offset];
        flags = chunks[5 + offset];
      } else if (op3str.equals("->")) {
        if (chunks.length <= 6 + offset) {
          return 0;
        }
        op3str = "";
        sresult = chunks[5 + offset];
        flags = chunks[6 + offset];
      } else {
        if (chunks.length <= 7 + offset) {
          return 0;
        }
        op3str = ConvertOp(op3str);
        sresult = chunks[6 + offset];
        flags = chunks[7 + offset];
      }
      sresult = ConvertOp(sresult);
      // sw.Start();
      IExtendedNumber op1, op2, op3, result;
      if (binaryFP) {
        op1 = BinaryNumber.FromString(op1str);
        op2 = ((op2str) == null || (op2str).length() == 0) ? null :
          BinaryNumber.FromString(op2str);
        op3 = ((op3str) == null || (op3str).length() == 0) ? null :
          BinaryNumber.FromString(op3str);
        result = BinaryNumber.FromString(sresult);
      } else {
        op1 = DecimalNumber.Create(EDecimal.FromString(op1str));
        op2 = ((op2str) == null || (op2str).length() == 0) ? null :
          DecimalNumber.Create(EDecimal.FromString(op2str));
        op3 = ((op3str) == null || (op3str).length() == 0) ? null :
          DecimalNumber.Create(EDecimal.FromString(op3str));
        result = DecimalNumber.Create(EDecimal.FromString(sresult));
      }
      int expectedFlags = 0;
      if (Contains(flags, "x")) {
        expectedFlags |= EContext.FlagInexact;
      }
      if (Contains(flags, "u")) {
        expectedFlags |= EContext.FlagUnderflow;
      }
      if (Contains(flags, "o")) {
        expectedFlags |= EContext.FlagOverflow;
      }
      if (Contains(flags, "i")) {
        expectedFlags |= EContext.FlagInvalid;
      }
      if (Contains(flags, "z")) {
        expectedFlags |= EContext.FlagDivideByZero;
      }
      ctx = ctx.WithBlankFlags();
      if (addop) {
        IExtendedNumber d3 = op1.Add(op2, ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        if (op1.IsQuietNaN() && op2.IsSignalingNaN() && binaryFP) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          if (exactResultCheck && (expectedFlags & (EContext.FlagInexact |
                EContext.FlagInvalid)) == 0) {
            d3 = op1.Add(op2, null);
            TestCommon.CompareTestEqual(result, d3, ln);
          }
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (subop) {
        IExtendedNumber d3 = op1.Subtract(op2, ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        if (op1.IsQuietNaN() && op2.IsSignalingNaN() && binaryFP) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          if (exactResultCheck && (expectedFlags & (EContext.FlagInexact |
                EContext.FlagInvalid)) == 0) {
            d3 = op1.Subtract(op2, null);
            TestCommon.CompareTestEqual(result, d3, ln);
          }
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (mul) {
        IExtendedNumber d3 = op1.Multiply(op2, ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        if (op1.IsQuietNaN() && op2.IsSignalingNaN() && binaryFP) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          if (exactResultCheck && (expectedFlags & (EContext.FlagInexact |
                EContext.FlagInvalid)) == 0) {
            d3 = op1.Multiply(op2, null);
            TestCommon.CompareTestEqual(result, d3, ln);
          }
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (div) {
        IExtendedNumber d3 = op1.Divide(op2, ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        if (op1.IsQuietNaN() && op2.IsSignalingNaN() && binaryFP) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          if (exactResultCheck && (expectedFlags & (EContext.FlagInexact |
                EContext.FlagInvalid)) == 0) {
            d3 = op1.Divide(op2, null);
            TestCommon.CompareTestEqual(result, d3, ln);
          }
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (squroot) {
        IExtendedNumber d3 = op1.SquareRoot(ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
      } else if (fma) {
        IExtendedNumber d3 = op1.MultiplyAndAdd(op2, op3, ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        if (binaryFP && (
            (op1.IsQuietNaN() && (op2.IsSignalingNaN() ||
                op3.IsSignalingNaN())) ||
            (op2.IsQuietNaN() && op3.IsSignalingNaN()))) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          if (exactResultCheck && (expectedFlags & (EContext.FlagInexact |
                EContext.FlagInvalid)) == 0) {
            d3 = op1.MultiplyAndAdd(op2, op3, null);
            TestCommon.CompareTestEqual(result, d3, ln);
          }
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      } else if (fms) {
        IExtendedNumber d3 = op1.MultiplyAndSubtract(op2, op3, ctx);
        if (!result.equals(d3)) {
          Assert.assertEquals(ln, result, d3);
        }
        if (op1.IsQuietNaN() && op2.IsSignalingNaN() && binaryFP) {
          // Don't check flags for binary test cases involving quiet
          // NaN followed by signaling NaN, as the semantics for
          // the invalid operation flag in those cases are different
          // than in the General Decimal Arithmetic Specification
        } else {
          if (exactResultCheck && (expectedFlags & (EContext.FlagInexact |
                EContext.FlagInvalid)) == 0) {
            d3 = op1.MultiplyAndSubtract(op2, op3, null);
            TestCommon.CompareTestEqual(result, d3, ln);
          }
          AssertFlagsRestricted(expectedFlags, ctx.getFlags(), ln);
        }
      }
      return 0;
    }

    public static void ParseDecTests(
      String lines) {
      ParseDecTests(lines, true);
    }

    public static void ParseDecTests(
      String lines,
      boolean checkFlags) {
      if (lines == null) {
        throw new NullPointerException("lines");
      }
      String[] linearray = SplitAt(lines, "\n");
      HashMap<String, String> context = new HashMap<String, String>();
      for (String str : linearray) {
        ParseDecTest(str, context, checkFlags);
      }
    }

    public static void ParseDecTest(
      String ln,
      Map<String, String> context) {
      ParseDecTest(ln, context, true);
    }

    private static String TrimQuotes(String str) {
      return (str == null || str.length() == 0 || (
            str.charAt(0) != '\'' && str.charAt(0) != '\"' && str.charAt(str.length() - 1) != '\'' &&
            str.charAt(str.length() - 1) != '\"')) ? str :
        ValueQuotes.matcher(str).replaceAll("");
    }

    public static void ParseDecTest(
      String ln,
      Map<String, String> context,
      boolean checkFlags) {
      Matcher match;
      if (ln == null) {
        throw new NullPointerException("ln");
      }
      if (context == null) {
        throw new NullPointerException("context");
      }
      if (ParseLineInput(ln) != 0) {
        return;
      }
      if (ParseLine(ln) != 0) {
        return;
      }
      if (Contains(ln, "-- ")) {
        ln = ln.substring(0,ln.indexOf("-- "));
      }
      match = (!Contains(ln, ":")) ? null : ValuePropertyLine.matcher(ln);
      if (match != null && match.matches()) {
        String paramName = ToLowerCaseAscii(
            match.group(1));
        context.put(paramName, match.group(2));
        return;
      }
      // System.Diagnostics.Stopwatch sw = new System.Diagnostics.Stopwatch();
      // sw.Start();
      match = ValueTestLine.matcher(ln);
      if (match.matches()) {
        String name = match.group(1);
        String op = match.group(2);
        String input1 = match.group(3);
        String input2 = match.group(4);
        String input3 = match.group(5);
        String output = match.group(6);
        String flags = match.group(7);
        input1 = (input1 == null) ? ("") : input1;
        input2 = (input2 == null) ? ("") : input2;
        input3 = (input3 == null) ? ("") : input3;
        output = (output == null) ? ("") : output;
        flags = (flags == null) ? ("") : flags;
        input1 = TrimQuotes(input1);
        input2 = TrimQuotes(input2);
        input3 = TrimQuotes(input3);
        output = TrimQuotes(output);
        if (context == null) {
          throw new NullPointerException("context");
        }
        boolean extended = GetKeyOrDefault(
            context,
            "extended",
            "1").equals("1");
        boolean clamp = GetKeyOrDefault(context, "clamp", "0").equals("1");
        int precision = 0, minexponent = 0, maxexponent = 0;
        EContext ctx = null;
        String rounding = null;
        precision = StringToIntAllowPlus(
            GetKeyOrDefault(context, "precision", "9"));
        minexponent = StringToIntAllowPlus(
            GetKeyOrDefault(context, "minexponent", "-9999"));
        maxexponent = StringToIntAllowPlus(
            GetKeyOrDefault(context, "maxexponent", "9999"));
        // Skip tests that take null as input or output;
        // also skip tests that take a hex number format
        if (Contains(input1, "#") ||
                 Contains(input2, "#") ||
                 Contains(input3, "#") ||
                 Contains(output, "#")) {
          return;
        }
        if (!extended && (Contains(input1, "sNaN") ||
            Contains(input2, "sNaN") || Contains(input3, "sNaN") ||
            Contains(output, "sNaN"))) {
          System.out.println(ln);
        }
        if (name.equals("S")) {
          return;
        }
        if (name.equals("Q")) {
          return;
        }

        if (StartsWith(name, "d32")) {
          return;
        }
        if (StartsWith(name, "d64")) {
          return;
        }
        if (StartsWith(name, "b32")) {
          return;
        }
        if (StartsWith(name, "d128")) {
          return;
        }
        // Skip some tests that assume a maximum
        // supported precision of 999999999
        if (name.equals("pow250") ||
          name.equals("pow251") ||
          name.equals("pow252")) {
          return;
        }
        // Assumes a maximum supported
        // value for pow exponent
        if (name.equals("powx4008")) {
          return;
        }
        // Skip these unofficial test cases which involve
        // pretruncation of the first operand of a shift operation;
        // actually, the Gen. Decimal Arithmetic Spec. doesn't
        // say to truncate that operand's coefficient before
        // the shift operation, unlike for the rotate operation.
        if (name.equals("extr1651") ||
          name.equals("extr1652") ||
          name.equals("extr1653") ||
          name.equals("extr1654")) {
          return;
        }
        // Skip these unofficial test cases, which misapply the
        // 'clamp' directive to NaNs in the test case format:
        // 'clamp' governs only exponent clamping;
        // although exponent clamping can
        // indirectly affect the coefficient in certain cases, NaNs
        // have neither a coefficient nor an exponent, so the
        // 'clamp' directive doesn't properly apply to NaNs
        if (name.equals("covx5076") ||
          name.equals("covx5082") ||
          name.equals("covx5085") ||
          name.equals("covx5086")) {
          return;
        }
        // Skip these unofficial test cases, which are incorrect
        // for expecting underflow on raising a huge
        // positive integer to its own power
        if (name.equals("power_eq4") ||
          name.equals("power_eq46") ||
          name.equals("power_eq48") ||
          name.equals("power_eq11") ||
          name.equals("power_eq65") ||
          name.equals("power_eq84") ||
          name.equals("power_eq94")) {
          return;
        }
        // Skip some test cases that are incorrect
        // (all simplified arithmetic test cases)
        if (!extended) {
          if (
            // result expected by test case is wrong by > 0.5 ULP
            name.equals("ln116") ||
            // assumes that the input will underflow to 0
            name.equals("qua530") ||
            // assumes that the input will underflow to 0
            name.equals("qua531") ||
            name.equals("rpow068") ||
            name.equals("rpow159") ||
            name.equals("rpow217") ||
            name.equals("rpow272") ||
            name.equals("rpow324") ||
            name.equals("rpow327") ||
            // following cases incorrectly remove trailing zeros
            name.equals("sqtx2207") ||
            name.equals("sqtx2231") ||
            name.equals("sqtx2271") ||
            name.equals("sqtx2327") ||
            name.equals("sqtx2399") ||
            name.equals("sqtx2487") ||
            name.equals("sqtx2591") ||
            name.equals("sqtx2711") ||
            name.equals("sqtx2847")) {
            return;
          }
        }
        if (Contains(input1, "?")) {
          return;
        }
        if (Contains(flags, "Invalid_context")) {
          return;
        }

        ctx = EContext.ForPrecision(precision)
          .WithExponentClamp(clamp).WithExponentRange(
            minexponent,
            maxexponent);
        rounding = ToLowerCaseAscii(GetKeyOrDefault(
              context,
              "rounding",
              "half_even"));
        if (rounding.equals("half_up")) {
          ctx = ctx.WithRounding(ERounding.HalfUp);
        }
        if (rounding.equals("half_down")) {
          ctx = ctx.WithRounding(ERounding.HalfDown);
        }
        if (rounding.equals("half_even")) {
          ctx = ctx.WithRounding(ERounding.HalfEven);
        }
        if (rounding.equals("up")) {
          ctx = ctx.WithRounding(ERounding.Up);
        }
        if (rounding.equals("down")) {
          ctx = ctx.WithRounding(ERounding.Down);
        }
        if (rounding.equals("ceiling")) {
          ctx = ctx.WithRounding(ERounding.Ceiling);
        }
        if (rounding.equals("floor")) {
          ctx = ctx.WithRounding(ERounding.Floor);
        }
        if (rounding.equals("05up")) {
          // NOTE: This rounding mode is like Odd in the case
          // of binary numbers, and ZeroFiveUp in the case of
          // decimal numbers
          ctx = ctx.WithRounding(ERounding.OddOrZeroFiveUp);
        }
        if (!extended) {
          ctx = ctx.WithSimplified(true);
        }
        if (checkFlags) {
          ctx = ctx.WithBlankFlags();
        }
        op = ToLowerCaseAscii(op);
        if (op.length() > 3 && op.substring(op.length() - 3).equals("_eq")) {
          // Binary operators with both operands the same
          input2 = input1;
          op = op.substring(0, op.length() - 3);
        }
        EDecimal d1 = EDecimal.Zero, d2 = null, d2a = null;
        if (!op.equals("tosci") &&
          !op.equals("toeng") &&
          !op.equals("class") &&
          !op.equals("format")) {
          try {
            d1 = ((input1) == null || (input1).length() == 0) ? EDecimal.Zero :
              EDecimal.FromString(input1);
            d2 = ((input2) == null || (input2).length() == 0) ? null :
              EDecimal.FromString(input2);
            d2a = ((input3) == null || (input3).length() == 0) ? null :
              EDecimal.FromString(input3);
          } catch (NumberFormatException ex) {
            throw new IllegalStateException(ln, ex);
          }
        }
        EDecimal d3 = null;
        if (op.equals("fma") && !extended) {
          // This implementation does implement multiply-and-add
          // in the simplified arithmetic, even though the test cases expect
          // an invalid operation to be raised. This seems to be allowed
          // under appendix A, which merely says that multiply-and-add
          // "is not defined" in the simplified arithmetic.
          return;
        }
        if (op.equals("multiply")) {
          d3 = d1.Multiply(d2, ctx);
        } else if (op.equals("tosci")) {
          // handled below
        } else if (op.equals("toeng")) {
          // handled below
        } else if (op.equals("class")) {
          // handled below
        } else if (op.equals("fma")) {
          d3 = d1.MultiplyAndAdd(d2, d2a, ctx);
        } else if (op.equals("min")) {
          d3 = EDecimal.Min(d1, d2, ctx);
        } else if (op.equals("max")) {
          d3 = EDecimal.Max(d1, d2, ctx);
        } else if (op.equals("minmag")) {
          d3 = EDecimal.MinMagnitude(d1, d2, ctx);
        } else if (op.equals("maxmag")) {
          d3 = EDecimal.MaxMagnitude(d1, d2, ctx);
        } else if (op.equals("compare")) {
          d3 = d1.CompareToWithContext(d2, ctx);
        } else if (op.equals("comparetotal")) {
          int id3 = d1.CompareToTotal(d2, ctx);
          d3 = EDecimal.FromInt32(id3);
          Assert.assertEquals(ln, id3, EDecimals.CompareTotal(d1, d2, ctx));
        } else if (op.equals("comparetotmag")) {
          int id3 = d1.CompareToTotalMagnitude(d2, ctx);
          d3 = EDecimal.FromInt32(id3);
          {
            Object objectTemp = id3;
            Object objectTemp2 = EDecimals.CompareTotalMagnitude(
                d1,
                d2,
                ctx);
            String messageTemp = ln;
            Assert.assertEquals(messageTemp, objectTemp, objectTemp2);
          }
        } else if (op.equals("copyabs")) {
          d3 = d1.Abs();
          Assert.assertEquals(d3, EDecimals.CopyAbs(d1));
        } else if (op.equals("copynegate")) {
          d3 = d1.Negate();
          Assert.assertEquals(d3, EDecimals.CopyNegate(d1));
        } else if (op.equals("copysign")) {
          d3 = d1.CopySign(d2);
          Assert.assertEquals(d3, EDecimals.CopySign(d1, d2));
        } else if (op.equals("comparesig")) {
          d3 = d1.CompareToSignal(d2, ctx);
        } else if (op.equals("subtract")) {
          d3 = d1.Subtract(d2, ctx);
        } else if (op.equals("tointegral")) {
          d3 = d1.RoundToIntegerNoRoundedFlag(ctx);
        } else if (op.equals("tointegralx")) {
          d3 = d1.RoundToIntegerExact(ctx);
        } else if (op.equals("divideint")) {
          d3 = d1.DivideToIntegerZeroScale(d2, ctx);
        } else if (op.equals("divide")) {
          d3 = d1.Divide(d2, ctx);
        } else if (op.equals("remainder")) {
          d3 = d1.Remainder(d2, ctx);
        } else if (op.equals("exp")) {
          d3 = d1.Exp(ctx);
        } else if (op.equals("ln")) {
          // NOTE: Gen. Decimal Arithmetic Spec.'s ln supports
          // only round-half-even mode, but EDecimal Log is not limited
          // to that rounding mode
          ctx = ctx.WithRounding(ERounding.HalfEven);
          d3 = d1.Log(ctx);
        } else if (op.equals("log10")) {
          // NOTE: Gen. Decimal Arithmetic Spec.'s log10 supports
          // only round-half-even mode, but EDecimal Log10 is not limited
          // to that rounding mode
          ctx = ctx.WithRounding(ERounding.HalfEven);
          d3 = d1.Log10(ctx);
        } else if (op.equals("power")) {
          if (d2a != null) {
            // System.out.println("Three-op power not yet supported");
            return;
          }
          d3 = d1.Pow(d2, ctx);
        } else if (op.equals("squareroot")) {
          d3 = d1.Sqrt(ctx);
        } else if (op.equals("remaindernear") ||
          op.equals("remainderNear")) {
          d3 = d1.RemainderNear(d2, ctx);
        } else if (op.equals("nexttoward")) {
          d3 = d1.NextToward(d2, ctx);
        } else if (op.equals("nextplus")) {
          d3 = d1.NextPlus(ctx);
        } else if (op.equals("nextminus")) {
          d3 = d1.NextMinus(ctx);
        } else if (op.equals("copy")) {
          d3 = d1;
          Assert.assertEquals("copy equiv",d3,EDecimals.Copy(d1));
        } else if (op.equals("abs")) {
          d3 = d1.Abs(ctx);
        } else if (op.equals("reduce")) {
          d3 = d1.Reduce(ctx);
        } else if (op.equals("quantize")) {
          d3 = d1.Quantize(d2, ctx);
        } else if (op.equals("add")) {
          d3 = d1.Add(d2, ctx);
        } else if (op.equals("minus")) {
          d3 = d1.Negate(ctx);
        } else if (op.equals("apply")) {
          d3 = d1.RoundToPrecision(ctx);
        } else if (op.equals("plus")) {
          d3 = d1.Plus(ctx);
        } else {
          if (op.equals("and")) {
            d3 = EDecimals.And(d1, d2, ctx);
          } else if (op.equals("or")) {
            d3 = EDecimals.Or(d1, d2, ctx);
          } else if (op.equals("xor")) {
            d3 = EDecimals.Xor(d1, d2, ctx);
          } else if (op.equals("invert")) {
            d3 = EDecimals.Invert(d1, ctx);
          } else if (op.equals("rescale")) {
            d3 = EDecimals.Rescale(d1, d2, ctx);
          } else if (op.equals("shift")) {
            d3 = EDecimals.Shift(d1, d2, ctx);
          } else if (op.equals("rotate")) {
            d3 = EDecimals.Rotate(d1, d2, ctx);
          } else if (op.equals("iscanonical")) {
            d3 = EDecimal.FromBoolean(EDecimals.IsCanonical(d1));
          } else if (op.equals("isnan")) {
            Assert.assertEquals(EDecimals.IsNaN(d1), d1.IsNaN());
            d3 = EDecimal.FromBoolean(EDecimals.IsNaN(d1));
          } else if (op.equals("issigned")) {
            Assert.assertEquals(EDecimals.IsSigned(d1), d1.isNegative());
            d3 = EDecimal.FromBoolean(EDecimals.IsSigned(d1));
          } else if (op.equals("isqnan")) {
            Assert.assertEquals(EDecimals.IsQuietNaN(d1), d1.IsQuietNaN());
            d3 = EDecimal.FromBoolean(EDecimals.IsQuietNaN(d1));
          } else if (op.equals("issnan")) {
            Assert.assertEquals(EDecimals.IsSignalingNaN(d1),
              d1.IsSignalingNaN());
            d3 = EDecimal.FromBoolean(EDecimals.IsSignalingNaN(d1));
          } else if (op.equals("isfinite")) {
            Assert.assertEquals(EDecimals.IsFinite(d1), d1.isFinite());
            d3 = EDecimal.FromBoolean(EDecimals.IsFinite(d1));
          } else if (op.equals("isinfinite")) {
            Assert.assertEquals(EDecimals.IsInfinite(d1), d1.IsInfinity());
            d3 = EDecimal.FromBoolean(EDecimals.IsInfinite(d1));
          } else if (op.equals("issubnormal")) {
            d3 = EDecimal.FromBoolean(EDecimals.IsSubnormal(d1, ctx));
          } else if (op.equals("isnormal")) {
            d3 = EDecimal.FromBoolean(EDecimals.IsNormal(d1, ctx));
          } else if (op.equals("iszero")) {
            Assert.assertEquals(EDecimals.IsZero(d1), d1.isZero());
            d3 = EDecimal.FromBoolean(EDecimals.IsZero(d1));
          } else if (op.equals("logb")) {
            d3 = EDecimals.LogB(d1, ctx);
          } else if (op.equals("scaleb")) {
            d3 = EDecimals.ScaleB(d1, d2, ctx);
          } else if (op.equals("trim")) {
            d3 = EDecimals.Trim(d1, ctx);
          } else if (op.equals("samequantum")) {
            d3 = EDecimal.FromBoolean(EDecimals.SameQuantum(d1, d2));
          } else {
            // System.out.println("unknown op " + op);
            return;
          }
        }
        flags = ToLowerCaseAscii(flags);
        boolean invalid = Contains(flags, "division_impossible") ||
          Contains(flags, "division_undefined") ||
          Contains(flags, "invalid_operation");
        boolean divzero = Contains(flags, "division_by_zero");
        int expectedFlags = 0;
        if (Contains(flags, "inexact")) {
          expectedFlags |= EContext.FlagInexact;
        }
        if (Contains(flags, "subnormal")) {
          expectedFlags |= EContext.FlagSubnormal;
        }
        if (Contains(flags, "rounded")) {
          expectedFlags |= EContext.FlagRounded;
        }
        if (Contains(flags, "underflow")) {
          expectedFlags |= EContext.FlagUnderflow;
        }
        if (Contains(flags, "overflow")) {
          expectedFlags |= EContext.FlagOverflow;
        }
        if (Contains(flags, "clamped")) {
          if (extended || clamp) {
            expectedFlags |= EContext.FlagClamped;
          }
        }
        if (Contains(flags, "lost_digits")) {
          expectedFlags |= EContext.FlagLostDigits;
        }
        boolean conversionError = Contains(flags, "conversion_syntax");
        if (invalid) {
          expectedFlags |= EContext.FlagInvalid;
        }
        if (divzero) {
          expectedFlags |= EContext.FlagDivideByZero;
        }
        if (op.equals("class")) {
          d1 = EDecimal.FromString(input1);
          String numclass = EDecimals.NumberClassString(
              EDecimals.NumberClass(d1, ctx));
          Assert.assertEquals(input1, output, numclass);
        } else if (op.equals("toSci") ||
          op.equals("tosci")) {
          try {
            d1 = EDecimal.FromString(input1, ctx);
            if (!(!conversionError)) {
 Assert.fail("Expected no conversion error");
 }
            String converted = d1.toString();
            if (!output.equals("?")) {
              Assert.assertEquals(input1, output, converted);
            }
          } catch (NumberFormatException ex) {
            if (!(conversionError)) {
 Assert.fail("Expected conversion error");
 }
          }
        } else if (op.equals("toEng") ||
          op.equals("toeng")) {
          try {
            d1 = EDecimal.FromString(input1, ctx);
            if (!(!conversionError)) {
 Assert.fail("Expected no conversion error");
 }
            String converted = d1.ToEngineeringString();
            if (!output.equals("?")) {
              Assert.assertEquals(input1, output, converted);
            }
          } catch (NumberFormatException ex) {
            if (!(conversionError)) {
 Assert.fail("Expected conversion error");
 }
          }
        } else {
          if (!output.equals("?")) {
            if (output == null && d3 != null) {
              Assert.fail(name + ": d3 must be null");
            }
            if (output != null && !d3.toString().equals(output)) {
              EDecimal d4 = EDecimal.FromString(output);
              {
                Object objectTemp = output;
                Object objectTemp2 = d3.toString();
                String messageTemp = name + ": expected: [" +
                  d4.getUnsignedMantissa() + " " + d4.getExponent() +
                  "]\n" + "but was: [" + d3.getUnsignedMantissa() + " " +
                  d3.getExponent() + "]\n" + ln + "\n" + ctx;
                Assert.assertEquals(messageTemp, objectTemp, objectTemp2);
              }
            }
          }
        }
        // Don't check flags for five simplified arithmetic
        // test cases that say to set the rounded flag; the
        // extended arithmetic counterparts for at least
        // some of them have no flags in their
        // result.
        if (checkFlags && !name.equals("pow118") &&
          !name.equals("pow119") &&
          !name.equals("pow120") &&
          !name.equals("pow121") &&
          !name.equals("pow122")) {
          AssertFlags(expectedFlags, ctx.getFlags(), ln);
        }
      }
    }

    public static String ContextToDecTestForm(EContext ec) {
      String roundingstr = "half_even";
      if (ec == null) {
        throw new NullPointerException("ec");
      }
      if (ec.getRounding() == ERounding.Ceiling) {
        roundingstr = "ceiling";
      }
      if (ec.getRounding() == ERounding.Floor) {
        roundingstr = "floor";
      }
      if (ec.getRounding() == ERounding.Up) {
        roundingstr = "up";
      }
      if (ec.getRounding() == ERounding.Down) {
        roundingstr = "down";
      }
      if (ec.getRounding() == ERounding.HalfEven) {
        roundingstr = "half_even";
      }
      if (ec.getRounding() == ERounding.HalfUp) {
        roundingstr = "half_up";
      }
      if (ec.getRounding() == ERounding.HalfDown) {
        roundingstr = "half_down";
      }
      if (ec.getRounding() == ERounding.OddOrZeroFiveUp) {
        roundingstr = "05up";
      }
      return "\nprecision: " + (ec.getPrecision().signum() == 0 ? "9999999" :
          ec.getPrecision().toString()) + "\nrounding: " + roundingstr +
        "\nmaxexponent: " + (ec.getEMax().signum() == 0 ? "999999999999999" :
          ec.getEMax().toString()) +
        "\nminexponent: " + (ec.getEMin().signum() == 0 ? "-999999999999999" :
          ec.getEMin().toString()) +
        "\n# adjustexp: " + (ec.getAdjustExponent() ? "1" : "0") +
        "\nextended: 1\nclamp: " + (ec.getClampNormalExponents() ? "1" : "0") +
        "\n";
    }

    public static String FlagsToString(int flags) {
      if (flags == 0) {
        return "";
      }
      StringBuilder sb = new StringBuilder();
      if ((flags & EContext.FlagInexact) != 0) {
        if (sb.length() > 0) {
          sb.append(' ');
        }
        sb.append("Inexact");
      }
      if ((flags & EContext.FlagRounded) != 0) {
        if (sb.length() > 0) {
          sb.append(' ');
        }
        sb.append("Rounded");
      }
      if ((flags & EContext.FlagSubnormal) != 0) {
        if (sb.length() > 0) {
          sb.append(' ');
        }
        sb.append("Subnormal");
      }
      if ((flags & EContext.FlagOverflow) != 0) {
        if (sb.length() > 0) {
          sb.append(' ');
        }
        sb.append("Overflow");
      }
      if ((flags & EContext.FlagUnderflow) != 0) {
        if (sb.length() > 0) {
          sb.append(' ');
        }
        sb.append("Underflow");
      }
      if ((flags & EContext.FlagClamped) != 0) {
        if (sb.length() > 0) {
          sb.append(' ');
        }
        sb.append("Clamped");
      }
      if ((flags & EContext.FlagInvalid) != 0) {
        if (sb.length() > 0) {
          sb.append(' ');
        }
        sb.append("Invalid");
      }
      if ((flags & EContext.FlagDivideByZero) != 0) {
        if (sb.length() > 0) {
          sb.append(' ');
        }
        sb.append("Divide_by_zero");
      }
      if ((flags & EContext.FlagLostDigits) != 0) {
        if (sb.length() > 0) {
          sb.append(' ');
        }
        sb.append("Lost_digits");
      }
      return sb.toString();
    }
    public static void AssertOneFlag(
      int expected,
      int actual,
      int flag,
      String name) {
      if (((expected & flag) != 0) != ((actual & flag) != 0)) {
        String msg = name + ": " + FlagsToString(flag) +
          "\nExpected flags: " + FlagsToString(expected) +
          "\nActual flags..: " + FlagsToString(actual);
        Assert.assertEquals(msg, (expected & flag) != 0, (actual & flag) != 0);
      }
    }
    public static void AssertFlags(int expected, int actual, String name) {
      if (expected == actual) {
        return;
      }
      AssertOneFlag(expected, actual, EContext.FlagInexact, name);
      AssertOneFlag(expected, actual, EContext.FlagRounded, name);
      AssertOneFlag(expected, actual, EContext.FlagSubnormal, name);
      AssertOneFlag(expected, actual, EContext.FlagOverflow, name);
      AssertOneFlag(expected, actual, EContext.FlagUnderflow, name);
      AssertOneFlag(expected, actual, EContext.FlagClamped, name);
      AssertOneFlag(expected, actual, EContext.FlagInvalid, name);
      AssertOneFlag(expected, actual, EContext.FlagDivideByZero, name);
      AssertOneFlag(expected, actual, EContext.FlagLostDigits, name);
    }
  }
