package com.upokecenter.numbers;
/*
Written by Peter O. in 2013.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

  final class FastIntegerFixed implements Comparable<FastIntegerFixed> {
    private int smallValue;  // if integerMode is 0
    private EInteger largeValue;  // if integerMode is 2
    private int integerMode;

    public static final FastIntegerFixed Zero = new FastIntegerFixed(0);
    public static final FastIntegerFixed One = new FastIntegerFixed(1);

    private static final EInteger ValueInt32MinValue =
      EInteger.FromInt64(Integer.MIN_VALUE);

    private static final EInteger ValueNegativeInt32MinValue=(ValueInt32MinValue).Negate();

    FastIntegerFixed(int value) {
      this.smallValue = value;
    }

    @Override public boolean equals(Object obj) {
      FastIntegerFixed fi = ((obj instanceof FastIntegerFixed) ? (FastIntegerFixed)obj : null);
      if (fi == null) {
 return false;
}
      if (this.integerMode != fi.integerMode) {
        return false;
      }
      if (this.integerMode == 0) {
        if (this.smallValue != fi.smallValue) {
 return false;
}
      } else if (this.integerMode == 1) {
        if (!this.largeValue.equals(fi.largeValue)) {
 return false;
}
      }
      return true;
    }

    @Override public int hashCode() {
      int hash = (31 + this.integerMode);
      if (this.integerMode == 0) {
       hash = ((hash * 31) + this.smallValue);
      } else if (this.integerMode == 1) {
       hash = ((hash * 31) + this.largeValue.hashCode());
      }
      return hash;
    }

    static FastIntegerFixed FromLong(long longVal) {
      if (longVal >= Integer.MIN_VALUE && longVal <= Integer.MAX_VALUE) {
        return new FastIntegerFixed((int)longVal);
      }
      FastIntegerFixed fi = new FastIntegerFixed(0);
      fi.integerMode = 2;
      fi.largeValue = EInteger.FromInt64(longVal);
      return fi;
    }

    static FastIntegerFixed FromBig(EInteger bigintVal) {
      if (bigintVal.CanFitInInt32()) {
        return new FastIntegerFixed(bigintVal.ToInt32Unchecked());
      }
      FastIntegerFixed fi = new FastIntegerFixed(0);
      fi.integerMode = 2;
      fi.largeValue = bigintVal;
      return fi;
    }

    int AsInt32() {
      return (this.integerMode == 0) ?
        this.smallValue : this.largeValue.ToInt32Unchecked();
    }

    public static FastIntegerFixed FromFastInteger(FastInteger fi) {
      if (fi.CanFitInInt32()) {
        return new FastIntegerFixed(fi.AsInt32());
      } else {
        return FastIntegerFixed.FromBig(fi.AsEInteger());
      }
    }

    public FastInteger ToFastInteger() {
      if (this.integerMode == 0) {
 return new FastInteger(this.smallValue);
} else {
 return FastInteger.FromBig(this.largeValue);
}
    }

    public FastIntegerFixed Increment() {
      if (this.integerMode == 0 && this.smallValue != Integer.MAX_VALUE) {
        return new FastIntegerFixed(this.smallValue + 1);
      } else {
        return Add(this, FastIntegerFixed.One);
      }
    }

    public int Mod(int value) {
      if (value < 0) {
        throw new UnsupportedOperationException();
      }
      if (this.integerMode == 0 && this.smallValue >= 0) {
        return this.smallValue % value;
      } else {
      EInteger retval = this.ToEInteger().Remainder(EInteger.FromInt32(value));
        return retval.ToInt32Checked();
      }
    }

    public static FastIntegerFixed Add(FastIntegerFixed a, FastIntegerFixed b) {
      if (a.integerMode == 0 && b.integerMode == 0) {
        if (a.smallValue == 0) {
 return b;
}
        if (b.smallValue == 0) {
 return a;
}
        if ((a.smallValue < 0 && b.smallValue >= Integer.MIN_VALUE -
            a.smallValue) || (a.smallValue > 0 && b.smallValue <=
            Integer.MAX_VALUE - a.smallValue)) {
        return new FastIntegerFixed(a.smallValue + b.smallValue);
      }
    }
      EInteger bigA = a.ToEInteger();
      EInteger bigB = b.ToEInteger();
      return FastIntegerFixed.FromBig(bigA.Add(bigB));
    }

    public static FastIntegerFixed Subtract(
  FastIntegerFixed a,
  FastIntegerFixed b) {
      if (a.integerMode == 0 && b.integerMode == 0) {
        if (b.smallValue == 0) {
 return a;
}
      if ((b.smallValue < 0 && Integer.MAX_VALUE + b.smallValue >= a.smallValue) ||
          (b.smallValue > 0 && Integer.MIN_VALUE + b.smallValue <=
                  a.smallValue)) {
        return new FastIntegerFixed(a.smallValue - b.smallValue);
      }
    }
      EInteger bigA = a.ToEInteger();
      EInteger bigB = b.ToEInteger();
      return FastIntegerFixed.FromBig(bigA.Subtract(bigB));
    }

    public int compareTo(FastIntegerFixed val) {
      switch ((this.integerMode << 2) | val.integerMode) {
        case (0 << 2) | 0:
          {
            int vsv = val.smallValue;
            return (this.smallValue == vsv) ? 0 : (this.smallValue < vsv ? -1 :
                  1);
          }
        case (0 << 2) | 2:
          return this.ToEInteger().compareTo(val.largeValue);
        case (2 << 2) | 0:
        case (2 << 2) | 2:
          return this.largeValue.compareTo(val.ToEInteger());
        default: throw new IllegalStateException();
      }
    }

    /**
     * This is an internal API.
     * @return A FastIntegerFixed object.
     */
    FastIntegerFixed Negate() {
      switch (this.integerMode) {
        case 0:
          if (this.smallValue == Integer.MIN_VALUE) {
            return FastIntegerFixed.FromBig(ValueNegativeInt32MinValue);
          } else {
            return new FastIntegerFixed(-smallValue);
          }
        case 2:
          return FastIntegerFixed.FromBig((this.largeValue).Negate());
        default: throw new IllegalStateException();
      }
    }

    final boolean isEvenNumber() {
        switch (this.integerMode) {
          case 0:
            return (this.smallValue & 1) == 0;
          case 2:
            return this.largeValue.isEven();
          default:
            throw new IllegalStateException();
        }
      }

    boolean CanFitInInt32() {
      return this.integerMode == 0 || this.largeValue.CanFitInInt32();
    }

    /**
     * This is an internal API.
     * @return A text string.
     */
    @Override public String toString() {
      switch (this.integerMode) {
        case 0:
          return FastInteger.IntToString(this.smallValue);
        case 2:
          return this.largeValue.toString();
        default: return "";
      }
    }

    final int signum() {
        switch (this.integerMode) {
          case 0:
            return (this.smallValue == 0) ? 0 : ((this.smallValue < 0) ? -1 :
                1);
          case 2:
            return this.largeValue.signum();
          default: return 0;
        }
      }

    final boolean isValueZero() {
        switch (this.integerMode) {
          case 0:
            return this.smallValue == 0;
          case 2:
            return this.largeValue.isZero();
          default:
            return false;
        }
      }

    boolean CanFitInInt64() {
      switch (this.integerMode) {
        case 0:
          return true;
          case 2: {
            return this.largeValue.CanFitInInt64();
          }
        default:
          throw new IllegalStateException();
      }
    }

    long AsInt64() {
      switch (this.integerMode) {
        case 0:
          return (long)this.smallValue;
          case 2: {
            return this.largeValue.ToInt64Unchecked();
          }
        default:
          throw new IllegalStateException();
      }
    }

    int CompareToInt(int val) {
      switch (this.integerMode) {
        case 0:
          return (val == this.smallValue) ? 0 : (this.smallValue < val ? -1 :
          1);
        case 2:
          return this.largeValue.compareTo(EInteger.FromInt32(val));
        default: return 0;
      }
    }

    EInteger ToEInteger() {
      switch (this.integerMode) {
        case 0:
          return EInteger.FromInt32(this.smallValue);
        case 2:
          return this.largeValue;
        default: throw new IllegalStateException();
      }
    }
  }
