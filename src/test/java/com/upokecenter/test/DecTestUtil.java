package com.upokecenter.test;

import java.util.*;

import java.util.regex.*;
import org.junit.Assert;

import com.upokecenter.util.*;
import com.upokecenter.numbers.*;

  public final class DecTestUtil {
private DecTestUtil() {
}
    private static final Pattern ValuePropertyLine = Pattern.compile(
      "^(\\w+)\\:\\s*(\\S+)");

    private static final Pattern ValueQuotes = Pattern.compile(
      "^[\\'\\\"]|[\\'\\\"]$");

    private static final Pattern ValueTestLine = Pattern.compile(
  "^([A-Za-z0-9_]+)\\s+([A-Za-z0-9_\\-]+)\\s+(\\'[^\\']*\\'|\\S+)\\s+(?:(\\S+)\\s+)?(?:(\\S+)\\s+)?->\\s+(\\S+)\\s*(.*)");

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

    /**
     * Returns a string with the basic upper-case letters A to Z (U+0041 to U+005A)
     * converted to lower-case. Other characters remain unchanged.
     * @param str The parameter {@code str} is a text string.
     * @return The converted string, or null if {@code str} is null.
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

    public static void ParseDecTest(
  String ln,
  Map<String, String> context) {
      Matcher match;
      if (ln == null) {
        throw new NullPointerException("ln");
      }
      if (ln.contains("-- ")) {
        ln = ln.substring(0,ln.indexOf("-- "));
      }
      match = (!ln.contains(":")) ? null : ValuePropertyLine.matcher(ln);
      if (match != null && match.matches()) {
        String paramName = ToLowerCaseAscii(
           match.group(1));
        if (context == null) {
          throw new NullPointerException("context");
        }
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
        input1 = ValueQuotes.matcher(input1).replaceAll("");
        input2 = ValueQuotes.matcher(input2).replaceAll("");
        input3 = ValueQuotes.matcher(input3).replaceAll("");
        output = ValueQuotes.matcher(output).replaceAll("");
        if (context == null) {
          throw new NullPointerException("context");
        }
        boolean extended = GetKeyOrDefault(context, "extended",
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
        if (input1.contains("#") ||
input2.contains("#") ||
input3.contains("#") ||
output.contains("#")) {
          return;
        }
        if (!extended && (input1.contains("sNaN") ||
input2.contains("sNaN") ||
input3.contains("sNaN") ||
output.contains("sNaN"))) {
          System.out.println(ln);
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
        if (input1.contains("?")) {
          return;
        }
        if (flags.contains("Invalid_context")) {
          System.out.println(ln);
          return;
        }

        ctx = EContext.ForPrecision(precision)
          .WithExponentClamp(clamp).WithExponentRange(
            minexponent,
            maxexponent);
        rounding = GetKeyOrDefault(
          context,
          "rounding",
          "half_even");
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
        ctx = ctx.WithBlankFlags();
        if (op.length() > 3 && op.substring(op.length() - 3).equals("_eq")) {
          // Binary operators with both operands the same
          input2 = input1;
          op = op.substring(0, op.length() - 3);
        }
        EDecimal d1 = EDecimal.Zero, d2 = null, d2a = null;
        if (!op.equals("toSci") &&
!op.equals("toEng") &&
!op.equals("tosci") &&
!op.equals("toeng") &&
!op.equals("class") &&
!op.equals("format")) {
          d1 = ((input1) == null || (input1).length() == 0) ? EDecimal.Zero :
            EDecimal.FromString(input1);
          d2 = ((input2) == null || (input2).length() == 0) ? null :
            EDecimal.FromString(input2);
          d2a = ((input3) == null || (input3).length() == 0) ? null :
            EDecimal.FromString(input3);
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
        } else if (op.equals("toSci")) {
// handled below
        } else if (op.equals("toEng")) {
// handled below
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
            System.out.println("Three-op power not yet supported");
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
            Assert.assertEquals(EDecimals.IsSignalingNaN(d1), d1.IsSignalingNaN());
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
            System.out.println("unknown op " + op);
            return;
          }
        }
        boolean invalid = flags.contains("Division_impossible") ||
          flags.contains("Division_undefined") ||
          flags.contains("Invalid_operation");
        boolean divzero = flags.contains("Division_by_zero");
        int expectedFlags = 0;
        if (flags.contains("Inexact") || flags.contains("inexact")) {
          expectedFlags |= EContext.FlagInexact;
        }
        if (flags.contains("Subnormal")) {
          expectedFlags |= EContext.FlagSubnormal;
        }
        if (flags.contains("Rounded") || flags.contains("rounded")) {
          expectedFlags |= EContext.FlagRounded;
        }
        if (flags.contains("Underflow")) {
          expectedFlags |= EContext.FlagUnderflow;
        }
        if (flags.contains("Overflow")) {
          expectedFlags |= EContext.FlagOverflow;
        }
        if (flags.contains("Clamped")) {
          if (extended || clamp) {
            expectedFlags |= EContext.FlagClamped;
          }
        }
        if (flags.contains("Lost_digits")) {
          expectedFlags |= EContext.FlagLostDigits;
        }
        boolean conversionError = flags.contains("Conversion_syntax");
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
                    d3.getExponent() + "]\n" + ln;
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
        if (!name.equals("pow118") &&
!name.equals("pow119") &&
!name.equals("pow120") &&
!name.equals("pow121") &&
!name.equals("pow122")) {
          AssertFlags(expectedFlags, ctx.getFlags(), ln);
        }
      }
    }

    public static void AssertFlags(int expected, int actual, String name) {
      if (expected == actual) {
        return;
      }
      Assert.assertEquals(name + ": Inexact",(expected & EContext.FlagInexact) != 0,(actual & EContext.FlagInexact) != 0);
      Assert.assertEquals(name + ": Rounded",(expected & EContext.FlagRounded) != 0,(actual & EContext.FlagRounded) != 0);
      Assert.assertEquals(name + ": Subnormal",(expected & EContext.FlagSubnormal) != 0,(actual & EContext.FlagSubnormal) != 0);
      Assert.assertEquals(name + ": Overflow",(expected & EContext.FlagOverflow) != 0,(actual & EContext.FlagOverflow) != 0);
      Assert.assertEquals(name + ": Underflow",(expected & EContext.FlagUnderflow) != 0,(actual & EContext.FlagUnderflow) != 0);
      Assert.assertEquals(name + ": Clamped",(expected & EContext.FlagClamped) != 0,(actual & EContext.FlagClamped) != 0);
      Assert.assertEquals(name + ": Invalid",(expected & EContext.FlagInvalid) != 0,(actual & EContext.FlagInvalid) != 0);
      Assert.assertEquals(name + ": DivideByZero",(expected & EContext.FlagDivideByZero) != 0,(actual & EContext.FlagDivideByZero) != 0);
      Assert.assertEquals(name + ": LostDigits",(expected & EContext.FlagLostDigits) != 0,(actual & EContext.FlagLostDigits) != 0);
    }
  }
