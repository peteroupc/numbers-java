package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
https://creativecommons.org/publicdomain/zero/1.0/

 */

  final class FastIntegerFixed implements Comparable<FastIntegerFixed> {
    // NOTE: Integer modes are mutually exclusive
    private enum IntegerMode {
      SmallValue(0),
      LargeValue(2);
private int v; IntegerMode(int v) { this.v = v; }}
    private static final int CacheFirst = -24;
    private static final int CacheLast = 128;

    private final int smallValue; // if integerMode is 0
    private final EInteger largeValue; // if integerMode is 2
    private final IntegerMode integerMode;

    public static final FastIntegerFixed Zero = new FastIntegerFixed(
      IntegerMode.SmallValue,
      0,
      null);
    public static final FastIntegerFixed One = new FastIntegerFixed(
      IntegerMode.SmallValue,
      1,
      null);

    private static final FastIntegerFixed[] Cache =
FastIntegerFixedCache(CacheFirst,
  CacheLast);

    private static FastIntegerFixed[] FastIntegerFixedCache(
      int first,
      int last) {
FastIntegerFixed[] cache = new FastIntegerFixed[(last - first) + 1];
for (int i = first; i <= last; ++i) {
  if (i == 0) {
    cache[i - first] = Zero;
  } else if (i == 1) {
    cache[i - first] = One;
  } else {
 cache[i - first] = new FastIntegerFixed(IntegerMode.SmallValue, i, null);
}
}
return cache;
    }

    private FastIntegerFixed(
      IntegerMode integerMode,
      int smallValue,
      EInteger largeValue) {
      this.integerMode = integerMode;
      this.smallValue = smallValue;
      this.largeValue = largeValue;
    }

    @Override public boolean equals(Object obj) {
      FastIntegerFixed fi = ((obj instanceof FastIntegerFixed) ? (FastIntegerFixed)obj : null); if (fi == null) {
        return false;
      }
      if (this.integerMode != fi.integerMode) {
        return false;
      }
      switch (this.integerMode) {
        case SmallValue:
          return this.smallValue == fi.smallValue;
        case LargeValue:
          return this.largeValue.equals(fi.largeValue);
        default: return true;
      }
    }

    @Override public int hashCode() {
      int hash = this.integerMode.hashCode();
      switch (this.integerMode) {
        case SmallValue:
          hash = ((hash * 31) + this.smallValue);
          break;
        case LargeValue:
          hash = ((hash * 31) + this.largeValue.hashCode());
          break;
      }
      return hash;
    }

    static FastIntegerFixed FromInt32(int intVal) {
return (intVal >= CacheFirst && intVal <= CacheLast) ?
Cache[intVal - CacheFirst] :
      new FastIntegerFixed(IntegerMode.SmallValue, intVal, null);
    }

    static FastIntegerFixed FromInt64(long longVal) {
      return (longVal >= Integer.MIN_VALUE && longVal <= Integer.MAX_VALUE) ?
FromInt32((int)longVal) : new FastIntegerFixed(
          IntegerMode.LargeValue,
          0,
          EInteger.FromInt64(longVal));
    }

    static FastIntegerFixed FromBig(EInteger bigintVal) {
      return bigintVal.CanFitInInt32() ?
FromInt32(bigintVal.ToInt32Unchecked()) : new
        FastIntegerFixed(IntegerMode.LargeValue, 0, bigintVal);
    }

    int ToInt32() {
      return (this.integerMode == IntegerMode.SmallValue) ?
        this.smallValue : this.largeValue.ToInt32Unchecked();
    }

    public static FastIntegerFixed FromFastInteger(FastInteger fi) {
      if (fi.CanFitInInt32()) {
        return FromInt32(fi.ToInt32());
      } else {
        return FastIntegerFixed.FromBig(fi.ToEInteger());
      }
    }

    public FastInteger ToFastInteger() {
      if (this.integerMode == IntegerMode.SmallValue) {
        return new FastInteger(this.smallValue);
      } else {
        return FastInteger.FromBig(this.largeValue);
      }
    }

    public FastIntegerFixed Increment() {
      if (this.integerMode == IntegerMode.SmallValue && this.smallValue !=
Integer.MAX_VALUE) {
        return FromInt32(this.smallValue + 1);
      } else {
        return Add(this, FastIntegerFixed.One);
      }
    }

    public int Mod(int value) {
      if (value < 0) {
        throw new UnsupportedOperationException();
      }
      if (this.integerMode == IntegerMode.SmallValue && this.smallValue >= 0) {
        return this.smallValue % value;
      } else {
        EInteger retval = this.ToEInteger().Remainder(EInteger.FromInt32(
  value));
        return retval.ToInt32Checked();
      }
    }

    public static FastIntegerFixed Add(FastIntegerFixed a,
      FastIntegerFixed b) {
      if (a.integerMode == IntegerMode.SmallValue &&
           b.integerMode == IntegerMode.SmallValue) {
        if (a.smallValue == 0) {
          return b;
        }
        if (b.smallValue == 0) {
          return a;
        }
        if (((a.smallValue | b.smallValue) >> 30) == 0) {
          return FromInt32(a.smallValue + b.smallValue);
        }
        if ((a.smallValue < 0 && b.smallValue >= Integer.MIN_VALUE -
            a.smallValue) || (a.smallValue > 0 && b.smallValue <=
            Integer.MAX_VALUE - a.smallValue)) {
          return FromInt32(a.smallValue + b.smallValue);
        }
      }
      EInteger bigA = a.ToEInteger();
      EInteger bigB = b.ToEInteger();
      return FastIntegerFixed.FromBig(bigA.Add(bigB));
    }

    public static FastIntegerFixed Subtract(
      FastIntegerFixed a,
      FastIntegerFixed b) {
      if (a.integerMode == IntegerMode.SmallValue && b.integerMode ==
IntegerMode.SmallValue) {
        if (b.smallValue == 0) {
          return a;
        }
        if (
          (b.smallValue < 0 && Integer.MAX_VALUE + b.smallValue >= a.smallValue) ||
          (b.smallValue > 0 && Integer.MIN_VALUE + b.smallValue <=
            a.smallValue)) {
          return FromInt32(a.smallValue - b.smallValue);
        }
      }
      EInteger bigA = a.ToEInteger();
      EInteger bigB = b.ToEInteger();
      return FastIntegerFixed.FromBig(bigA.Subtract(bigB));
    }

    public FastIntegerFixed Add(int ib) {
      FastIntegerFixed a = this;
      if (this.integerMode == IntegerMode.SmallValue) {
        if (ib == 0) {
          return this;
        }
        if (this.smallValue == 0) {
          return FromInt32(ib);
        }
        if (((a.smallValue | ib) >> 30) == 0) {
          return FromInt32(a.smallValue + ib);
        }
        if ((a.smallValue < 0 && ib >= Integer.MIN_VALUE -
            a.smallValue) || (a.smallValue > 0 && ib <=
            Integer.MAX_VALUE - a.smallValue)) {
          return FromInt32(a.smallValue + ib);
        }
      }
      EInteger bigA = a.ToEInteger();
      return FastIntegerFixed.FromBig(bigA.Add(ib));
    }

    public FastIntegerFixed Subtract(int ib) {
      if (ib == 0) {
        return this;
      }
      if (this.integerMode == IntegerMode.SmallValue) {
        if (
          (ib < 0 && Integer.MAX_VALUE + ib >= this.smallValue) ||
          (ib > 0 && Integer.MIN_VALUE + ib <= this.smallValue)) {
          return FromInt32(this.smallValue - ib);
        }
      }
      EInteger bigA = this.ToEInteger();
      return FastIntegerFixed.FromBig(bigA.Subtract(ib));
    }

    public FastIntegerFixed Add(
      FastIntegerFixed b) {
      return Add(this, b);
    }

    public FastIntegerFixed Subtract(
      FastIntegerFixed b) {
      return Subtract(this, b);
    }

    public FastIntegerFixed Add(
      EInteger b) {
      if (this.integerMode == IntegerMode.SmallValue && b.CanFitInInt32()) {
        return this.Add(b.ToInt32Unchecked());
      } else {
        return FastIntegerFixed.FromBig(
           this.ToEInteger().Add(b));
      }
    }

    public FastIntegerFixed Subtract(
      EInteger b) {
      if (this.integerMode == IntegerMode.SmallValue && b.CanFitInInt32()) {
        return this.Subtract(b.ToInt32Unchecked());
      } else {
        return FastIntegerFixed.FromBig(
           this.ToEInteger().Subtract(b));
      }
    }

    public FastIntegerFixed Abs() {
      switch (this.integerMode) {
        case SmallValue:
          if (this.smallValue == Integer.MIN_VALUE) {
            return FastIntegerFixed.FromInt32(Integer.MAX_VALUE).Increment();
          } else if (this.smallValue < 0) {
            return FastIntegerFixed.FromInt32(-this.smallValue);
          } else {
            return this;
          }
        case LargeValue:
          return this.largeValue.signum() < 0 ? new
            FastIntegerFixed(IntegerMode.LargeValue, 0, this.largeValue.Abs()) :
            this;
        default: throw new IllegalStateException();
      }
    }

    public FastIntegerFixed Negate() {
      switch (this.integerMode) {
        case SmallValue:
          if (this.smallValue == Integer.MIN_VALUE) {
            return FastIntegerFixed.FromInt32(Integer.MAX_VALUE).Increment();
          } else {
            return FastIntegerFixed.FromInt32(-this.smallValue);
          }
        case LargeValue:
          return new FastIntegerFixed(
            IntegerMode.LargeValue,
            0,
            this.largeValue.Negate());
        default: throw new IllegalStateException();
      }
    }

    public int compareTo(EInteger evalue) {
      switch (this.integerMode) {
        case SmallValue:
          return -evalue.compareTo(this.smallValue);
        case LargeValue:
          return this.largeValue.compareTo(evalue);
        default: throw new IllegalStateException();
      }
    }

    public int compareTo(FastInteger fint) {
      switch (this.integerMode) {
        case SmallValue:
          return -fint.CompareToInt(this.smallValue);
        case LargeValue:
          return -fint.compareTo(this.largeValue);
        default: throw new IllegalStateException();
      }
    }

    public int compareTo(FastIntegerFixed val) {
      switch (this.integerMode) {
        case SmallValue:
          switch (val.integerMode) {
            case SmallValue:
              int vsv = val.smallValue;
              return (this.smallValue == vsv) ? 0 : (this.smallValue < vsv ?
-1 :
                  1);
            case LargeValue:
              return -val.largeValue.compareTo(this.smallValue);
          }
          break;
        case LargeValue:
          return this.largeValue.compareTo(val.ToEInteger());
      }
      throw new IllegalStateException();
    }

    FastIntegerFixed Copy() {
      switch (this.integerMode) {
        case SmallValue:
          return FromInt32(this.smallValue);
        case LargeValue:
          return FastIntegerFixed.FromBig(this.largeValue);
        default: throw new IllegalStateException();
      }
    }

    final boolean isEvenNumber() {
        switch (this.integerMode) {
          case SmallValue:
            return (this.smallValue & 1) == 0;
          case LargeValue:
            return this.largeValue.isEven();
          default:
            throw new IllegalStateException();
        }
      }

    boolean CanFitInInt32() {
      return this.integerMode == IntegerMode.SmallValue ||
this.largeValue.CanFitInInt32();
    }

    /**
     * This is an internal API.
     * @return A text string.
     */
    @Override public String toString() {
      switch (this.integerMode) {
        case SmallValue:
          return FastInteger.IntToString(this.smallValue);
        case LargeValue:
          return this.largeValue.toString();
        default: return "";
      }
    }

    final int signum() {
        switch (this.integerMode) {
          case SmallValue:
            return (this.smallValue == 0) ? 0 : ((this.smallValue < 0) ? -1 :
                1);
          case LargeValue:
            return this.largeValue.signum();
          default:
            return 0;
        }
      }

    final boolean isValueZero() {
        switch (this.integerMode) {
          case SmallValue:
            return this.smallValue == 0;
          case LargeValue:
            return this.largeValue.isZero();
          default:
            return false;
        }
      }

    boolean CanFitInInt64() {
      switch (this.integerMode) {
        case SmallValue:
          return true;
        case LargeValue:
          return this.largeValue
            .CanFitInInt64();
        default: throw new IllegalStateException();
      }
    }

    long ToInt64() {
      switch (this.integerMode) {
        case SmallValue:
          return (long)this.smallValue;
        case LargeValue:
          return this.largeValue
            .ToInt64Unchecked();
        default: throw new IllegalStateException();
      }
    }

    int CompareToInt64(long valLong) {
      switch (this.integerMode) {
        case SmallValue:
          return (valLong == this.smallValue) ? 0 : (this.smallValue <
valLong ? -1 :
              1);
        case LargeValue:
          return this.largeValue.compareTo(valLong);
        default: return 0;
      }
    }

    int CompareToInt(int val) {
      switch (this.integerMode) {
        case SmallValue:
          return (val == this.smallValue) ? 0 : (this.smallValue < val ? -1 :
              1);
        case LargeValue:
          return this.largeValue.compareTo(EInteger.FromInt32(val));
        default: return 0;
      }
    }

    EInteger ToEInteger() {
      switch (this.integerMode) {
        case SmallValue:
          return EInteger.FromInt32(this.smallValue);
        case LargeValue:
          return this.largeValue;
        default: throw new IllegalStateException();
      }
    }
  }
