package com.upokecenter.numbers;
/*
Written in 2013 by Peter O.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://upokecenter.dreamhosters.com/articles/donate-now-2/
 */

  final class FastInteger2 implements Comparable<FastInteger2> {
    private int smallValue;  // if integerMode is 0
    private EInteger largeValue;  // if integerMode is 2
    private int integerMode;
    private static final EInteger ValueInt32MinValue =
      EInteger.FromInt64(Integer.MIN_VALUE);

    private static final EInteger ValueInt32MaxValue =
      EInteger.FromInt64(Integer.MAX_VALUE);

    private static final EInteger ValueNegativeInt32MinValue=(ValueInt32MinValue).Negate();

    FastInteger2(int value) {
      this.smallValue = value;
    }

    @Override public boolean equals(Object obj) {
      FastInteger2 fi = ((obj instanceof FastInteger2) ? (FastInteger2)obj : null);
      if (fi == null) {
 return false;
}
      return this.integerMode == fi.integerMode &&
        this.smallValue == fi.smallValue &&
        (this.largeValue == null ? fi.largeValue == null :
          this.largeValue.equals(fi.largeValue));
    }

    @Override public int hashCode() {
      int hash = (31 + this.integerMode);
      hash = (hash * 31 + this.smallValue);
      hash = (hash * 31 +
        (this.largeValue == null ? 0 : this.largeValue.hashCode()));
      return hash;
    }

    static FastInteger2 FromBig(EInteger bigintVal) {
      if (bigintVal.CanFitInInt32()) {
        return new FastInteger2(bigintVal.ToInt32Unchecked());
      }
      FastInteger2 fi = new FastInteger2(0);
      fi.integerMode = 2;
      fi.largeValue = bigintVal;
      return fi;
    }

    int AsInt32() {
      return (this.integerMode == 0) ?
        this.smallValue : this.largeValue.ToInt32Unchecked();
    }

    public static FastInteger2 Add(FastInteger2 a, FastInteger2 b) {
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
        return new FastInteger2(a.smallValue + b.smallValue);
      }
    }
      EInteger bigA = a.AsEInteger();
      EInteger bigB = b.AsEInteger();
      return FastInteger2.FromBig(bigA.Add(bigB));
    }

    public static FastInteger2 Subtract(FastInteger2 a, FastInteger2 b) {
      if (a.integerMode == 0 && b.integerMode == 0) {
        if (b.smallValue == 0) {
 return a;
}
      if ((b.smallValue < 0 && Integer.MAX_VALUE + b.smallValue >= a.smallValue)||
          (b.smallValue > 0 && Integer.MIN_VALUE + b.smallValue <=
                  a.smallValue)) {
        return new FastInteger2(a.smallValue - b.smallValue);
      }
    }
      EInteger bigA = a.AsEInteger();
      EInteger bigB = b.AsEInteger();
      return FastInteger2.FromBig(bigA.Subtract(bigB));
    }

    public int compareTo(FastInteger2 val) {
      switch ((this.integerMode << 2) | val.integerMode) {
        case (0 << 2) | 0:
          {
            int vsv = val.smallValue;
            return (this.smallValue == vsv) ? 0 : (this.smallValue < vsv ? -1 :
                  1);
          }
        case (0 << 2) | 2:
          return this.AsEInteger().compareTo(val.largeValue);
        case (2 << 2) | 0:
        case (2 << 2) | 2:
          return this.largeValue.compareTo(val.AsEInteger());
        default: throw new IllegalStateException();
      }
    }

    /**
     *
     * @return A FastInteger2 object.
     */
    FastInteger2 Negate() {
      switch (this.integerMode) {
        case 0:
          if (this.smallValue == Integer.MIN_VALUE) {
            return FastInteger2.FromBig(ValueNegativeInt32MinValue);
          } else {
            return new FastInteger2(-smallValue);
          }
        case 2:
          return FastInteger2.FromBig((this.largeValue).Negate());
        default: throw new IllegalStateException();
      }
    }

    /**
     *
     */
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
     *
     * @return A string object.
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

    /**
     *
     */
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

    /**
     *
     */
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

    int CompareToInt(int val) {
      switch (this.integerMode) {
        case 0:
          return (val == this.smallValue) ? 0 : (this.smallValue < val ? -1 :
          1);
        case 2:
          return this.largeValue.compareTo(EInteger.FromInt64(val));
        default: return 0;
      }
    }

    EInteger AsEInteger() {
      switch (this.integerMode) {
        case 0:
          return EInteger.FromInt32(this.smallValue);
        case 2:
          return this.largeValue;
        default: throw new IllegalStateException();
      }
    }
  }
