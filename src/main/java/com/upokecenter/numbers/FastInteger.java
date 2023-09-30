package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
https://creativecommons.org/publicdomain/zero/1.0/

 */

  final class FastInteger implements Comparable<FastInteger> {
    private static final class MutableNumber {
      private int[] data;
      private int wordCount;

      static MutableNumber FromEInteger(EInteger bigintVal) {
        MutableNumber mnum = new MutableNumber(0);
        if (bigintVal.signum() < 0) {
          throw new IllegalArgumentException("bigintVal's sign(" + bigintVal.signum() +
            ") is less than " + "0 ");
        }
        byte[] bytes = bigintVal.ToBytes(true);
        int len = bytes.length;
        int newWordCount = Math.max(4, (len / 4) + 1);
        if (newWordCount > mnum.data.length) {
          mnum.data = new int[newWordCount];
        }
        mnum.wordCount = newWordCount;
        {
          for (int i = 0; i < len; i += 4) {
            int x = ((int)bytes[i]) & 0xff;
            if (i + 1 < len) {
              x |= (((int)bytes[i + 1]) & 0xff) << 8;
            }
            if (i + 2 < len) {
              x |= (((int)bytes[i + 2]) & 0xff) << 16;
            }
            if (i + 3 < len) {
              x |= (((int)bytes[i + 3]) & 0xff) << 24;
            }
            mnum.data[i >> 2] = x;
          }
        }
        // Calculate the correct data length
        while (mnum.wordCount != 0 && mnum.data[mnum.wordCount - 1] == 0) {
          --mnum.wordCount;
        }
        return mnum;
      }

      MutableNumber(int val) {
        if (val < 0) {
          throw new IllegalArgumentException("val(" + val + ") is less than " +
"0 ");
        }
        this.data = new int[4];
        this.wordCount = (val == 0) ? 0 : 1;
        this.data[0] = val;
      }

      MutableNumber SetInt(int val) {
        if (val < 0) {
          throw new IllegalArgumentException("val(" + val + ") is less than " +
"0 ");
        }
        this.wordCount = (val == 0) ? 0 : 1;
        this.data[0] = val;
        return this;
      }

      EInteger ToEInteger() {
        if (this.wordCount == 1 && (this.data[0] >> 31) == 0) {
          return EInteger.FromInt64((int)this.data[0]);
        }
        if (this.wordCount == 2 && (this.data[1] >> 31) == 0) {
          long longV = ((long)this.data[0]);
          longV &= 0xffffffffL;
          longV |= (((long)this.data[1]) << 32);
          return EInteger.FromInt64(longV);
        }
        return EInteger.FromInts(this.data, this.wordCount);
      }

      int[] GetLastWordsInternal(int numWords32Bit) {
        int[] ret = new int[numWords32Bit];
        System.arraycopy(this.data, 0, ret, 0, Math.min(numWords32Bit, this.wordCount));
        return ret;
      }

      boolean CanFitInInt32() {
        return this.wordCount == 0 || (this.wordCount == 1 && (this.data[0] >>
              31) == 0);
      }

      int ToInt32() {
        return this.wordCount == 0 ? 0 : this.data[0];
      }
      public static MutableNumber FromInt64(long longVal) {
        if (longVal < 0) {
          throw new IllegalArgumentException("longVal");
        }
        if (longVal == 0) {
          return new MutableNumber(0);
        }
        MutableNumber mbi = new MutableNumber(0);
        mbi.data[0] = ((int)longVal);
        int mbd = ((int)(longVal >> 32));
        mbi.data[1] = mbd;
        mbi.wordCount = (mbd == 0) ? 1 : 2;
        return mbi;
      }

      MutableNumber Copy() {
        MutableNumber mbi = new MutableNumber(0);
        if (this.wordCount > mbi.data.length) {
          mbi.data = new int[this.wordCount];
        }
        System.arraycopy(this.data, 0, mbi.data, 0, this.wordCount);
        mbi.wordCount = this.wordCount;
        return mbi;
      }
      MutableNumber Multiply(int multiplicand) {
        if (multiplicand < 0) {
          throw new IllegalArgumentException("multiplicand(" + multiplicand +
            ") is less than " + "0 ");
        }
        if (multiplicand != 0) {
          int carry = 0;
          if (this.wordCount == 0) {
            if (this.data.length == 0) {
              this.data = new int[4];
            }
            this.data[0] = 0;
            this.wordCount = 1;
          }
          int result0, result1, result2, result3;
          if (multiplicand < 65536) {
            if (this.wordCount == 2 && (this.data[1] >> 16) == 0) {
              long longV = ((long)this.data[0]);
              longV &= 0xffffffffL;
              longV |= (((long)this.data[1]) << 32);
              longV = (longV * multiplicand);
              this.data[0] = ((int)longV);
              this.data[1] = ((int)(longV >> 32));
              carry = 0;
            } else if (this.wordCount == 1) {
              long longV = ((long)this.data[0]);
              longV &= 0xffffffffL;
              longV = (longV * multiplicand);
              this.data[0] = ((int)longV);
              carry = ((int)(longV >> 32));
            } else {
              for (int i = 0; i < this.wordCount; ++i) {
                int x0 = this.data[i];
                int x1 = x0;
                int y0 = multiplicand;
                x0 &= 65535;
                x1 = (x1 >> 16) & 65535;
                int temp = (x0 * y0); // a * c
                result1 = (temp >> 16) & 65535;
                result0 = temp & 65535;
                result2 = 0;
                temp = (x1 * y0); // b * c
                result2 += (temp >> 16) & 65535;
                result1 += temp & 65535;
                result2 += (result1 >> 16) & 65535;
                result1 &= 65535;
                result3 = (result2 >> 16) & 65535;
                result2 &= 65535;
                // Add carry
                x0 = ((int)(result0 | (result1 << 16)));
                x1 = ((int)(result2 | (result3 << 16)));
                int x2 = (x0 + carry);
                if (((x2 >> 31) == (x0 >> 31)) ? ((x2 & Integer.MAX_VALUE) < (x0 &
                      Integer.MAX_VALUE)) : ((x2 >> 31) == 0)) {
                  // Carry in addition
                  x1 = (x1 + 1);
                }
                this.data[i] = x2;
                carry = x1;
              }
            }
          } else {
            if (this.wordCount == 1) {
              long longV = ((long)this.data[0]);
              longV &= 0xffffffffL;
              longV = (longV * multiplicand);
              this.data[0] = ((int)longV);
              carry = ((int)(longV >> 32));
            } else {
              for (int i = 0; i < this.wordCount; ++i) {
                int x0 = this.data[i];
                int x1 = x0;
                int y0 = multiplicand;
                int y1 = y0;
                x0 &= 65535;
                y0 &= 65535;
                x1 = (x1 >> 16) & 65535;
                y1 = (y1 >> 16) & 65535;
                int temp = (x0 * y0); // a * c
                result1 = (temp >> 16) & 65535;
                result0 = temp & 65535;
                temp = (x0 * y1); // a * d
                result2 = (temp >> 16) & 65535;
                result1 += temp & 65535;
                result2 += (result1 >> 16) & 65535;
                result1 &= 65535;
                temp = (x1 * y0); // b * c
                result2 += (temp >> 16) & 65535;
                result1 += temp & 65535;
                result2 += (result1 >> 16) & 65535;
                result1 &= 65535;
                result3 = (result2 >> 16) & 65535;
                result2 &= 65535;
                temp = (x1 * y1); // b * d
                result3 += (temp >> 16) & 65535;
                result2 += temp & 65535;
                result3 += (result2 >> 16) & 65535;
                result2 &= 65535;
                // Add carry
                x0 = ((int)(result0 | (result1 << 16)));
                x1 = ((int)(result2 | (result3 << 16)));
                int x2 = (x0 + carry);
                if (((x2 >> 31) == (x0 >> 31)) ? ((x2 & Integer.MAX_VALUE) < (x0 &
                      Integer.MAX_VALUE)) : ((x2 >> 31) == 0)) {
                  // Carry in addition
                  x1 = (x1 + 1);
                }
                this.data[i] = x2;
                carry = x1;
              }
            }
          }
          if (carry != 0) {
            if (this.wordCount >= this.data.length) {
              int[] newdata = new int[this.wordCount + 20];
              System.arraycopy(this.data, 0, newdata, 0, this.data.length);
              this.data = newdata;
            }
            this.data[this.wordCount] = carry;
            ++this.wordCount;
          }
          // Calculate the correct data length
          while (this.wordCount != 0 && this.data[this.wordCount - 1] == 0) {
            --this.wordCount;
          }
        } else {
          if (this.data.length > 0) {
            this.data[0] = 0;
          }
          this.wordCount = 0;
        }
        return this;
      }

      final int signum() {
          return this.wordCount == 0 ? 0 : 1;
        }
      final boolean isEvenNumber() {
          return this.wordCount == 0 || (this.data[0] & 1) == 0;
        }

      int CompareToInt(int val) {
        if (val < 0 || this.wordCount > 1) {
          return 1;
        }
        if (this.wordCount == 0) {
          // this value is 0
          return (val == 0) ? 0 : -1;
        }
        if (this.data[0] == val) {
          return 0;
        }
        return (((this.data[0] >> 31) == (val >> 31)) ? ((this.data[0] &
                Integer.MAX_VALUE) < (val & Integer.MAX_VALUE)) :
            ((this.data[0] >> 31) == 0)) ? -1 : 1;
      }

      MutableNumber SubtractInt(int other) {
        if (other < 0) {
          throw new IllegalArgumentException("other(" + other + ") is less than " +
            "0 ");
        }
        // NOTE: Mutable numbers are always zero or positive,
        // and this method assumes 'other' is less than or equal to this number
        // System.out.println("sub1="+this.ToEInteger());
        // System.out.println("sub2="+other);
        if (other != 0) {
          {
            // Ensure a length of at least 1
            if (this.wordCount == 0) {
              if (this.data.length == 0) {
                this.data = new int[4];
              }
              this.data[0] = 0;
              this.wordCount = 1;
            }
            int borrow;
            int u;
            int a = this.data[0];
            u = a - other;
            borrow = ((((a >> 31) == (u >> 31)) ?
                  ((a & Integer.MAX_VALUE) < (u & Integer.MAX_VALUE)) :
                  ((a >> 31) == 0)) || (a == u && other != 0)) ? 1 : 0;
            this.data[0] = (int)u;
            if (borrow != 0) {
              for (int i = 1; i < this.wordCount; ++i) {
                u = this.data[i] - borrow;
                borrow = (((this.data[i] >> 31) == (u >> 31)) ?
                    ((this.data[i] & Integer.MAX_VALUE) < (u & Integer.MAX_VALUE)) :
                    ((this.data[i] >> 31) == 0)) ? 1 : 0;
                this.data[i] = (int)u;
              }
            }
            // Calculate the correct data length
            while (this.wordCount != 0 && this.data[this.wordCount - 1] == 0) {
              --this.wordCount;
            }
          }
        }
        // System.out.println("result="+this.ToEInteger());
        return this;
      }

      MutableNumber Subtract(MutableNumber other) {
        // NOTE: Mutable numbers are always zero or positive,
        // and this method assumes 'other' is less than or equal to this number
        {
          {
            // System.out.println("sub1="+this.ToEInteger());
            // System.out.println("sub2="+other.ToEInteger());
            int neededSize = (this.wordCount > other.wordCount) ?
              this.wordCount : other.wordCount;
            if (this.data.length < neededSize) {
              int[] newdata = new int[neededSize + 20];
              System.arraycopy(this.data, 0, newdata, 0, this.data.length);
              this.data = newdata;
            }
            neededSize = (this.wordCount < other.wordCount) ? this.wordCount :
              other.wordCount;
            int u = 0;
            int borrow = 0;
            for (int i = 0; i < neededSize; ++i) {
              int a = this.data[i];
              u = (a - other.data[i]) - borrow;
              borrow = ((((a >> 31) == (u >> 31)) ? ((a & Integer.MAX_VALUE) <
                      (u & Integer.MAX_VALUE)) :
                    ((a >> 31) == 0)) || (a == u && other.data[i] !=
                    0)) ? 1 : 0;
              this.data[i] = (int)u;
            }
            if (borrow != 0) {
              for (int i = neededSize; i < this.wordCount; ++i) {
                int a = this.data[i];
                int b = i >= other.wordCount ? 0 : other.data[i];
                u = (a - b) - borrow;
                borrow = ((((a >> 31) == (u >> 31)) ? ((a & Integer.MAX_VALUE) <
                        (u & Integer.MAX_VALUE)) :
                      ((a >> 31) == 0)) || (a == u && b != 0)) ? 1 : 0;
                this.data[i] = (int)u;
              }
            }
            // Calculate the correct data length
            while (this.wordCount != 0 && this.data[this.wordCount - 1] == 0) {
              --this.wordCount;
            }
            // System.out.println("result="+this.ToEInteger());
            return this;
          }
        }
      }

      public int compareTo(MutableNumber other) {
        if (this.wordCount != other.wordCount) {
          return (this.wordCount < other.wordCount) ? -1 : 1;
        }
        int valueN = this.wordCount;
        while ((valueN--) != 0) {
          int an = this.data[valueN];
          int bn = other.data[valueN];
          // Unsigned less-than check
          if (((an >> 31) == (bn >> 31)) ?
            ((an & Integer.MAX_VALUE) < (bn & Integer.MAX_VALUE)) :
            ((an >> 31) == 0)) {
            return -1;
          }
          if (an != bn) {
            return 1;
          }
        }
        return 0;
      }
      MutableNumber Add(int augend) {
        if (augend < 0) {
          throw new IllegalArgumentException("augend(" + augend + ") is less than " +
            "0 ");
        }
        {
          if (augend != 0) {
            int carry = 0;
            // Ensure a length of at least 1
            if (this.wordCount == 0) {
              if (this.data.length == 0) {
                this.data = new int[4];
              }
              this.data[0] = 0;
              this.wordCount = 1;
            }
            for (int i = 0; i < this.wordCount; ++i) {
              int u;
              int a = this.data[i];
              u = (a + augend) + carry;
              carry = ((((u >> 31) == (a >> 31)) ? ((u & Integer.MAX_VALUE) < (a &
                        Integer.MAX_VALUE)) :
                    ((u >> 31) == 0)) || (u == a && augend != 0)) ? 1 : 0;
              this.data[i] = u;
              if (carry == 0) {
                return this;
              }
              augend = 0;
            }
            if (carry != 0) {
              if (this.wordCount >= this.data.length) {
                int[] newdata = new int[this.wordCount + 20];
                System.arraycopy(this.data, 0, newdata, 0, this.data.length);
                this.data = newdata;
              }
              this.data[this.wordCount] = carry;
              ++this.wordCount;
            }
          }
          // Calculate the correct data length
          while (this.wordCount != 0 && this.data[this.wordCount - 1] == 0) {
            --this.wordCount;
          }
          return this;
        }
      }
    }

    // Hexadecimal digits
    private static final String Digits = "0123456789ABCDEF";
    private int smallValue; // if integerMode is 0
    private MutableNumber mnum; // if integerMode is 1
    private EInteger largeValue; // if integerMode is 2
    private int integerMode;
    private boolean frozen;

    private static final EInteger ValueInt32MinValue =
      EInteger.FromInt64(Integer.MIN_VALUE);

    private static final EInteger ValueInt32MaxValue =
      EInteger.FromInt64(Integer.MAX_VALUE);

    private static final EInteger ValueNegativeInt32MinValue=(ValueInt32MinValue).Negate();

    FastInteger(int value) {
      this.smallValue = value;
    }

    FastInteger Copy() {
      FastInteger fi = new FastInteger(this.smallValue);
      fi.integerMode = this.integerMode;
      fi.largeValue = this.largeValue;
      fi.mnum = (this.mnum == null || this.integerMode != 1) ? null :
        this.mnum.Copy();
      return fi;
    }

    static FastInteger CopyFrozen(FastInteger value) {
      FastInteger fi = new FastInteger(value.smallValue);
      fi.integerMode = value.integerMode;
      fi.largeValue = value.largeValue;
      fi.mnum = (value.mnum == null || value.integerMode != 1) ? null :
        value.mnum.Copy();
      fi.frozen = true;
      return fi;
    }

    static FastInteger FromInt64(long longVal) {
      return (longVal >= Integer.MIN_VALUE && longVal <= Integer.MAX_VALUE) ? new
FastInteger((int)longVal) : FromBig(EInteger.FromInt64(longVal));
    }

    static FastInteger FromBig(EInteger bigintVal) {
      if (bigintVal.CanFitInInt32()) {
        return new FastInteger(bigintVal.ToInt32Unchecked());
      }
      if (bigintVal.signum() > 0 && bigintVal.GetUnsignedBitLengthAsInt64() <
2048) {
        // Limit bit length because of the overhead of copying
        // to a mutable number
        FastInteger fi = new FastInteger(0);
        fi.integerMode = 1;
        fi.mnum = MutableNumber.FromEInteger(bigintVal);
        return fi;
      } else {
        FastInteger fi = new FastInteger(0);
        fi.integerMode = 2;
        fi.largeValue = bigintVal;
        return fi;
      }
    }

    int ToInt32() {
      switch (this.integerMode) {
        case 0:
          return this.smallValue;
        case 1:
          return this.mnum.ToInt32();
        case 2:
          return this.largeValue.ToInt32Checked();
        default: throw new IllegalStateException();
      }
    }

    public int compareTo(EInteger ei) {
switch (this.integerMode) {
 case 0:
   return -ei.compareTo(this.smallValue);
 case 1:
   return this.ToEInteger().compareTo(ei);
 case 2:
   return this.largeValue.compareTo(ei);
 default: throw new IllegalStateException();
}
    }

    public int compareTo(FastInteger val) {
      switch ((this.integerMode << 2) | val.integerMode) {
        case (0 << 2) | 0: {
          int vsv = val.smallValue;
          return (this.smallValue == vsv) ? 0 : (this.smallValue < vsv ? -1 :
              1);
        }
        case (0 << 2) | 1:
          return -val.mnum.CompareToInt(this.smallValue);
        case (0 << 2) | 2:
          return -val.largeValue.compareTo(this.smallValue);
        case (1 << 2) | 0:
          return this.mnum.CompareToInt(val.smallValue);
        case (1 << 2) | 1:
          return this.mnum.compareTo(val.mnum);
        case (1 << 2) | 2:
          return this.ToEInteger().compareTo(val.largeValue);
        case (2 << 2) | 0:
        case (2 << 2) | 1:
        case (2 << 2) | 2:
          return this.largeValue.compareTo(val.ToEInteger());
        default: throw new IllegalStateException();
      }
    }

    FastInteger Abs() {
      if (this.frozen) {
        throw new IllegalStateException();
      }
      switch (this.integerMode) {
        case 0:
          if (this.smallValue == Integer.MIN_VALUE) {
            return this.Negate();
          }
          this.smallValue = Math.abs(this.smallValue);
          return this;
        default:
          return (this.signum() < 0) ? this.Negate() : this;
      }
    }

    static int[] GetLastWords(EInteger bigint, int numWords32Bit) {
      return MutableNumber.FromEInteger(bigint).GetLastWordsInternal(
  numWords32Bit);
    }

    FastInteger SetInt(int val) {
      this.smallValue = val;
      this.integerMode = 0;
      return this;
    }

    /**
     * This is an internal API.
     * @param val The parameter {@code val} is an internal value.
     * @return A FastInteger object.
     */
    FastInteger Multiply(int val) {
      if (val == 0) {
        this.smallValue = 0;
        this.integerMode = 0;
      } else {
        switch (this.integerMode) {
          case 0: {
            long amult = ((long)val) * ((long)this.smallValue);
            if (amult > Integer.MAX_VALUE || amult < Integer.MIN_VALUE) {
              // would overflow, convert to large
              boolean apos = this.smallValue > 0L;
              boolean bpos = val > 0L;
              if (apos && bpos) {
                // if both operands are nonnegative
                // convert to mutable big integer
                this.integerMode = 1;
                this.mnum = MutableNumber.FromInt64(amult);
              } else {
                // if either operand is negative
                // convert to big integer
                this.integerMode = 2;
                this.largeValue = EInteger.FromInt64(amult);
              }
            } else {
              this.smallValue = ((int)amult);
            }
            break;
          }
          case 1:
            if (val < 0) {
              this.integerMode = 2;
              this.largeValue = this.mnum.ToEInteger();
              this.largeValue = this.largeValue.Multiply(EInteger.FromInt32(val));
            } else {
              this.mnum.Multiply(val);
            }
            break;
          case 2:
            this.largeValue = this.largeValue.Multiply(EInteger.FromInt32(val));
            break;
          default: throw new IllegalStateException();
        }
      }
      return this;
    }

    /**
     * This is an internal API.
     * @return A FastInteger object.
     */
    FastInteger Negate() {
      switch (this.integerMode) {
        case 0:
          if (this.smallValue == Integer.MIN_VALUE) {
            // would overflow, convert to large
            this.integerMode = 1;
            this.mnum =
              MutableNumber.FromEInteger(ValueNegativeInt32MinValue);
            } else {
            this.smallValue = -this.smallValue;
          }
          break;
        case 1:
          this.integerMode = 2;
          this.largeValue = this.mnum.ToEInteger();
          this.largeValue=(this.largeValue).Negate();
          break;
        case 2:
          this.largeValue=(this.largeValue).Negate();
          break;
        default:
          throw new IllegalStateException();
      }
      return this;
    }

    /**
     * This is an internal API.
     * @param val The parameter {@code val} is an internal value.
     * @return A FastInteger object.
     */
    FastInteger Subtract(FastInteger val) {
      EInteger valValue;
      switch (this.integerMode) {
        case 0:
          if (val.integerMode == 0) {
            int vsv = val.smallValue;
            if ((vsv < 0 && Integer.MAX_VALUE + vsv < this.smallValue) ||
              (vsv > 0 && Integer.MIN_VALUE + vsv > this.smallValue)) {
              // would overflow, convert to large
              this.integerMode = 2;
              this.largeValue = EInteger.FromInt32(this.smallValue);
              this.largeValue = this.largeValue.Subtract(EInteger.FromInt32(vsv));
            } else {
              this.smallValue -= vsv;
            }
          } else {
            this.integerMode = 2;
            this.largeValue = EInteger.FromInt32(this.smallValue);
            valValue = val.ToEInteger();
            this.largeValue = this.largeValue.Subtract(valValue);
          }
          break;
        case 1:
          if (val.integerMode == 1 && this.mnum.compareTo(val.mnum) >= 0 &&
              val.mnum.CompareToInt(0) >= 0) {
            this.mnum.Subtract(val.mnum);
          } else if (val.integerMode == 0 && val.smallValue >= 0 &&
              this.mnum.CompareToInt(val.smallValue) >= 0) {
            this.mnum.SubtractInt(val.smallValue);
          } else {
            this.integerMode = 2;
            this.largeValue = this.mnum.ToEInteger();
            valValue = val.ToEInteger();
            this.largeValue = this.largeValue.Subtract(valValue);
          }
          break;
        case 2:
          valValue = val.ToEInteger();
          this.largeValue = this.largeValue.Subtract(valValue);
          break;
        default: throw new IllegalStateException();
      }
      return this;
    }

    /**
     * This is an internal API.
     * @param val The parameter {@code val} is an internal value.
     * @return A FastInteger object.
     */
    FastInteger SubtractInt(int val) {
      if (val == Integer.MIN_VALUE) {
        return this.AddBig(ValueNegativeInt32MinValue);
      }
      if (this.integerMode == 0) {
        if ((val < 0 && Integer.MAX_VALUE + val < this.smallValue) ||
          (val > 0 && Integer.MIN_VALUE + val > this.smallValue)) {
          // would overflow, convert to large
          this.integerMode = 2;
          this.largeValue = EInteger.FromInt32(this.smallValue);
          this.largeValue = this.largeValue.Subtract(EInteger.FromInt32(val));
        } else {
          this.smallValue -= val;
        }
        return this;
      }
      return this.AddInt(-val);
    }

    /**
     * This is an internal API.
     * @param bigintVal The parameter {@code bigintVal} is an internal value.
     * @return A FastInteger object.
     */
    FastInteger AddBig(EInteger bigintVal) {
      switch (this.integerMode) {
        case 0: {
          return bigintVal.CanFitInInt32() ? this.AddInt(bigintVal.ToInt32Checked()) :
            this.Add(FastInteger.FromBig(bigintVal));
        }
        case 1:
          this.integerMode = 2;
          this.largeValue = this.mnum.ToEInteger();
          this.largeValue = largeValue.Add(bigintVal);
          break;
        case 2:
          this.largeValue = largeValue.Add(bigintVal);
          break;
        default:
          throw new IllegalStateException();
      }
      return this;
    }

    /**
     * This is an internal API.
     * @param bigintVal The parameter {@code bigintVal} is an internal value.
     * @return A FastInteger object.
     */
    FastInteger SubtractBig(EInteger bigintVal) {
      if (this.integerMode == 2) {
        this.largeValue = this.largeValue.Subtract(bigintVal);
        return this;
      } else {
        int sign = bigintVal.signum();
        if (sign == 0) {
          return this;
        }
        // Check if this value fits an int, except if
        // it's MinValue
        if (sign < 0 && bigintVal.compareTo(ValueInt32MinValue) > 0) {
          return this.AddInt(-(bigintVal.ToInt32Checked()));
        }
        if (sign > 0 && bigintVal.compareTo(ValueInt32MaxValue) <= 0) {
          return this.SubtractInt(bigintVal.ToInt32Checked());
        }
        bigintVal = bigintVal.Negate();
        return this.AddBig(bigintVal);
      }
    }

    FastInteger Add(FastInteger val) {
      EInteger valValue;
      switch (this.integerMode) {
        case 0:
          if (val.integerMode == 0) {
            if ((this.smallValue < 0 && (int)val.smallValue < Integer.MIN_VALUE
                - this.smallValue) ||
              (this.smallValue > 0 && (int)val.smallValue > Integer.MAX_VALUE
                - this.smallValue)) {
              // would overflow
              if (val.smallValue >= 0) {
                this.integerMode = 1;
                this.mnum = new MutableNumber(this.smallValue);
                this.mnum.Add(val.smallValue);
              } else {
                this.integerMode = 2;
                this.largeValue = EInteger.FromInt32(this.smallValue);
                this.largeValue = this.largeValue.Add(EInteger.FromInt64(val.smallValue));
              }
            } else {
              this.smallValue += val.smallValue;
            }
          } else {
            this.integerMode = 2;
            this.largeValue = EInteger.FromInt32(this.smallValue);
            valValue = val.ToEInteger();
            this.largeValue = this.largeValue.Add(valValue);
          }
          break;
        case 1:
          if (val.integerMode == 0 && val.smallValue >= 0) {
            this.mnum.Add(val.smallValue);
          } else {
            this.integerMode = 2;
            this.largeValue = this.mnum.ToEInteger();
            valValue = val.ToEInteger();
            this.largeValue = this.largeValue.Add(valValue);
          }
          break;
        case 2:
          valValue = val.ToEInteger();
          this.largeValue = this.largeValue.Add(valValue);
          break;
        default: throw new IllegalStateException();
      }
      return this;
    }

    FastInteger Remainder(int divisor) {
      // Mod operator will always result in a
      // number that fits an int for int divisors

      if (divisor != 0) {
        switch (this.integerMode) {
          case 0:
            this.smallValue %= divisor;
            break;
          case 1:
            this.largeValue = this.mnum.ToEInteger();
            this.largeValue = this.largeValue.Remainder(EInteger.FromInt64(divisor));
            this.smallValue = this.largeValue.ToInt32Checked();
            this.integerMode = 0;
            break;
          case 2:
            this.largeValue = this.largeValue.Remainder(EInteger.FromInt64(divisor));
            this.smallValue = this.largeValue.ToInt32Checked();
            this.integerMode = 0;
            break;
          default:
            throw new IllegalStateException();
        }
      } else {
        throw new ArithmeticException();
      }
      return this;
    }

    FastInteger Increment() {
      if (this.integerMode == 0) {
        if (this.smallValue != Integer.MAX_VALUE) {
          ++this.smallValue;
        } else {
          this.integerMode = 1;
          this.mnum = MutableNumber.FromEInteger(ValueNegativeInt32MinValue);
        }
        return this;
      }
      return this.AddInt(1);
    }

    FastInteger Decrement() {
      if (this.integerMode == 0) {
        if (this.smallValue != Integer.MIN_VALUE) {
          --this.smallValue;
        } else {
          this.integerMode = 1;
          this.mnum = MutableNumber.FromEInteger(ValueInt32MinValue);
          this.mnum.SubtractInt(1);
        }
        return this;
      }
      return this.SubtractInt(1);
    }

    FastInteger Divide(int divisor) {
      if (divisor != 0) {
        switch (this.integerMode) {
          case 0:
            if (divisor == -1 && this.smallValue == Integer.MIN_VALUE) {
              // would overflow, convert to large
              this.integerMode = 1;
              this.mnum =
                MutableNumber.FromEInteger(ValueNegativeInt32MinValue);
              } else {
              this.smallValue /= divisor;
            }
            break;
          case 1:
            this.integerMode = 2;
            this.largeValue = this.mnum.ToEInteger();
            this.largeValue = this.largeValue.Divide(EInteger.FromInt64(divisor));
            if (this.largeValue.isZero()) {
              this.integerMode = 0;
              this.smallValue = 0;
            }
            break;
          case 2:
            this.largeValue = this.largeValue.Divide(EInteger.FromInt64(divisor));
            if (this.largeValue.isZero()) {
              this.integerMode = 0;
              this.smallValue = 0;
            }
            break;
          default:
            throw new IllegalStateException();
        }
      } else {
        throw new ArithmeticException();
      }
      return this;
    }

    EInteger ShiftEIntegerLeftByThis(EInteger ei) {
      switch (this.integerMode) {
        case 0:
          return ei.ShiftLeft(this.smallValue);
        case 1:
          return ei.ShiftLeft(this.mnum.ToEInteger());
        case 2:
          return ei.ShiftLeft(this.largeValue);
        default: throw new IllegalStateException();
      }
    }

    final boolean isEvenNumber() {
        switch (this.integerMode) {
          case 0:
            return (this.smallValue & 1) == 0;
          case 1:
            return this.mnum.isEvenNumber();
          case 2:
            return this.largeValue.isEven();
          default:
            throw new IllegalStateException();
        }
      }

    FastInteger AddInt64(long longVal) {
      return longVal >= Integer.MIN_VALUE && longVal <= Integer.MAX_VALUE ?
         this.AddInt((int)longVal) : this.AddBig(EInteger.FromInt64(longVal));
    }

    FastInteger SubtractInt64(long longVal) {
      return longVal >= Integer.MIN_VALUE && longVal <= Integer.MAX_VALUE ?
         this.SubtractInt((int)longVal) :
this.SubtractBig(EInteger.FromInt64(longVal));
    }

    FastInteger AddInt(int val) {
      EInteger valValue;
      switch (this.integerMode) {
        case 0:
          if ((this.smallValue < 0 && (int)val < Integer.MIN_VALUE -
              this.smallValue) || (this.smallValue > 0 && (int)val >
              Integer.MAX_VALUE - this.smallValue)) {
            // would overflow
            if (val >= 0) {
              this.integerMode = 1;
              this.mnum = new MutableNumber(this.smallValue);
              this.mnum.Add(val);
            } else {
              this.integerMode = 2;
              this.largeValue = EInteger.FromInt32(this.smallValue);
              this.largeValue = this.largeValue.Add(EInteger.FromInt32(val));
            }
          } else {
            this.smallValue += val;
          }
          break;
        case 1:
          if (val >= 0) {
            this.mnum.Add(val);
          } else {
            this.integerMode = 2;
            this.largeValue = this.mnum.ToEInteger();
            valValue = EInteger.FromInt32(val);
            this.largeValue = this.largeValue.Add(valValue);
          }
          break;
        case 2:
          valValue = EInteger.FromInt32(val);
          this.largeValue = this.largeValue.Add(valValue);
          break;
        default: throw new IllegalStateException();
      }
      return this;
    }

    boolean CanFitInInt32() {
      switch (this.integerMode) {
        case 0:
          return true;
        case 1:
          return this.mnum.CanFitInInt32();
        case 2:
          return this.largeValue.CanFitInInt32();
        default: throw new IllegalStateException();
      }
    }

    boolean CanFitInInt64() {
      switch (this.integerMode) {
        case 0:
          return true;
        case 1:
          return this.ToEInteger().CanFitInInt64();
        case 2:
          return this.largeValue.CanFitInInt64();

        default: throw new IllegalStateException();
      }
    }

    long ToInt64() {
      switch (this.integerMode) {
        case 0:
          return (long)this.smallValue;
        case 1:
          return this.ToEInteger().ToInt64Unchecked();
        case 2:
          return this.largeValue.ToInt64Unchecked();

        default: throw new IllegalStateException();
      }
    }

    public static String IntToString(int value) {
      if (value == 0) {
        return "0";
      }
      if (value == Integer.MIN_VALUE) {
        return "-2147483648";
      }
      boolean neg = value < 0;
      if (neg) {
        value = -value;
      }
      char[] chars;
      int count;
      if (value < 100000) {
        if (neg) {
         chars = new char[6];
         count = 5;
       } else {
         chars = new char[5];
         count = 4;
        }
        while (value > 9) {
          int intdivvalue = ((((value >> 1) * 52429) >> 18) & 16383);
          char digit = Digits.charAt((int)(value - (intdivvalue * 10)));
          chars[count--] = digit;
          value = intdivvalue;
        }
        if (value != 0) {
          chars[count--] = Digits.charAt((int)value);
        }
        if (neg) {
          chars[count] = '-';
        } else {
          ++count;
        }
        return new String(chars, count, chars.length - count);
      }
      chars = new char[12];
      count = 11;
      while (value >= 163840) {
        int intdivvalue = value / 10;
        char digit = Digits.charAt((int)(value - (intdivvalue * 10)));
        chars[count--] = digit;
        value = intdivvalue;
      }
      while (value > 9) {
        int intdivvalue = ((((value >> 1) * 52429) >> 18) & 16383);
        char digit = Digits.charAt((int)(value - (intdivvalue * 10)));
        chars[count--] = digit;
        value = intdivvalue;
      }
      if (value != 0) {
        chars[count--] = Digits.charAt((int)value);
      }
      if (neg) {
        chars[count] = '-';
      } else {
        ++count;
      }
      return new String(chars, count, 12 - count);
    }

    public static String LongToString(long longValue) {
      if (longValue == Long.MIN_VALUE) {
        return "-9223372036854775808";
      }
      if (longValue == 0L) {
        return "0";
      }
      boolean neg = longValue < 0;
      int count = 0;
      char[] chars;
      int intlongValue = ((int)longValue);
      if ((long)intlongValue == longValue) {
        return IntToString(intlongValue);
      } else {
        chars = new char[24];
        count = 23;
        if (neg) {
          longValue = -longValue;
        }
        while (longValue >= 163840) {
          long divValue = longValue / 10;
          char digit = Digits.charAt((int)(longValue - (divValue * 10)));
          chars[count--] = digit;
          longValue = divValue;
        }
        while (longValue > 9) {
          long divValue = ((((longValue >> 1) * 52429) >> 18) & 16383);
          char digit = Digits.charAt((int)(longValue - (divValue * 10)));
          chars[count--] = digit;
          longValue = divValue;
        }
        if (longValue != 0) {
          chars[count--] = Digits.charAt((int)longValue);
        }
        if (neg) {
          chars[count] = '-';
        } else {
          ++count;
        }
        return new String(chars, count, 24 - count);
      }
    }

    /**
     * This is an internal API.
     * @return A text string.
     */
    @Override public String toString() {
      switch (this.integerMode) {
        case 0:
          return IntToString(this.smallValue);
        case 1:
          return this.mnum.ToEInteger().toString();
        case 2:
          return this.largeValue.toString();
        default: return "";
      }
    }

    /**
     * Gets an internal value.
     * @return An internal value.
     */
    final int signum() {
        switch (this.integerMode) {
          case 0:
            return (this.smallValue == 0) ? 0 : ((this.smallValue < 0) ? -1 :
                1);
          case 1:
            return this.mnum.signum();
          case 2:
            return this.largeValue.signum();
          default:
            return 0;
        }
      }

    final boolean isValueZero() {
        switch (this.integerMode) {
          case 0:
            return this.smallValue == 0;
          case 1:
            return this.mnum.signum() == 0;
          case 2:
            return this.largeValue.isZero();
          default:
            throw new IllegalStateException();
        }
      }

    int CompareToInt(int val) {
      switch (this.integerMode) {
        case 0:
          return (val == this.smallValue) ? 0 : (this.smallValue < val ? -1 :
              1);
        case 1:
          return this.mnum.ToEInteger().compareTo(EInteger.FromInt32(val));
        case 2:
          return this.largeValue.compareTo(EInteger.FromInt32(val));
        default: throw new IllegalStateException();
      }
    }

    EInteger ToEInteger() {
      switch (this.integerMode) {
        case 0:
          return EInteger.FromInt32(this.smallValue);
        case 1:
          return this.mnum.ToEInteger();
        case 2:
          return this.largeValue;
        default: throw new IllegalStateException();
      }
    }
  }
