package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
https://creativecommons.org/publicdomain/zero/1.0/

 */

  public final class EContext {
    public static final int FlagClamped = 32;

    public static final int FlagDivideByZero = 128;

    public static final int FlagInexact = 1;

    public static final int FlagInvalid = 64;

    public static final int FlagLostDigits = 256;

    public static final int FlagOverflow = 16;

    public static final int FlagRounded = 2;

    public static final int FlagSubnormal = 4;

    public static final int FlagUnderflow = 8;

    public static final EContext Basic =
      EContext.ForPrecisionAndRounding(9, ERounding.HalfUp);

    public static final EContext BigDecimalJava =
      new EContext(0, ERounding.HalfUp, 0, 0, true)
    .WithExponentClamp(true).WithAdjustExponent(false)
    .WithBigExponentRange(
      EInteger.FromInt32(0).Subtract(EInteger.FromInt64(Integer.MAX_VALUE)),
      EInteger.FromInt32(1).Add(EInteger.FromInt64(Integer.MAX_VALUE)));

    public static final EContext Binary128 =
      EContext.ForPrecisionAndRounding(113, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-16382, 16383);

    public static final EContext Binary16 =
      EContext.ForPrecisionAndRounding(11, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-14, 15);

    public static final EContext Binary32 =
      EContext.ForPrecisionAndRounding(24, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-126, 127);

    public static final EContext Binary64 =
      EContext.ForPrecisionAndRounding(53, ERounding.HalfEven)
      .WithExponentClamp(true).WithExponentRange(-1022, 1023);

    public static final EContext CliDecimal =
      new EContext(96, ERounding.HalfEven, 0, 28, true)
    .WithPrecisionInBits(true);

    public static final EContext Decimal128 =
      new EContext(34, ERounding.HalfEven, -6143, 6144, true);

    public static final EContext Decimal32 =
      new EContext(7, ERounding.HalfEven, -95, 96, true);

    public static final EContext Decimal64 =
      new EContext(16, ERounding.HalfEven, -383, 384, true);

    public static final EContext Unlimited =
      EContext.ForPrecision(0);

    public static final EContext UnlimitedHalfEven =
      EContext.ForPrecision(0).WithRounding(ERounding.HalfEven);

    private EContext(
      boolean adjustExponent,
      EInteger bigintPrecision,
      boolean clampNormalExponents,
      EInteger exponentMax,
      EInteger exponentMin,
      int flags,
      boolean hasExponentRange,
      boolean hasFlags,
      boolean precisionInBits,
      ERounding rounding,
      boolean simplified,
      int traps) {
      if (bigintPrecision == null) {
        throw new NullPointerException("bigintPrecision");
      }
      if (exponentMin == null) {
        throw new NullPointerException("exponentMin");
      }
      if (exponentMax == null) {
        throw new NullPointerException("exponentMax");
      }
      if (bigintPrecision.signum() < 0) {
        throw new IllegalArgumentException("precision(" + bigintPrecision +
          ") is less than 0");
      }
      if (exponentMin.compareTo(exponentMax) > 0) {
        throw new IllegalArgumentException("exponentMinSmall(" + exponentMin +
          ") is more than " + exponentMax);
      }
      this.adjustExponent = adjustExponent;
      this.bigintPrecision = bigintPrecision;
      this.clampNormalExponents = clampNormalExponents;
      this.exponentMax = exponentMax;
      this.exponentMin = exponentMin;
      this.flags = flags;
      this.hasExponentRange = hasExponentRange;
      this.hasFlags = hasFlags;
      this.precisionInBits = precisionInBits;
      this.rounding = rounding;
      this.simplified = simplified;
      this.traps = traps;
    }

    private final boolean adjustExponent;

    private final EInteger bigintPrecision;

    private final boolean clampNormalExponents;
    private final EInteger exponentMax;

    private final EInteger exponentMin;

    private final boolean hasExponentRange;
    private final boolean hasFlags;

    private final boolean precisionInBits;

    private final ERounding rounding;

    private final boolean simplified;

    private final int traps;

    private int flags;

    public EContext(
      int precision,
      ERounding rounding,
      int exponentMinSmall,
      int exponentMaxSmall,
      boolean clampNormalExponents) {
 this(
          true,
          EInteger.FromInt32(precision),
          clampNormalExponents,
          EInteger.FromInt32(exponentMaxSmall),
          EInteger.FromInt32(exponentMinSmall),
          0,
          true,
          false,
          false,
          rounding,
          false,
          0);
    }

    public EContext(
      EInteger bigintPrecision,
      ERounding rounding,
      EInteger exponentMin,
      EInteger exponentMax,
      boolean clampNormalExponents) {
 this(
          true,
          bigintPrecision,
          clampNormalExponents,
          exponentMax,
          exponentMin,
          0,
          true,
          false,
          false,
          rounding,
          false,
          0);
    }

    public final boolean getAdjustExponent() {
        return this.adjustExponent;
      }

    public final boolean getClampNormalExponents() {
        return this.hasExponentRange && this.clampNormalExponents;
      }

    public final EInteger getEMax() {
        return this.hasExponentRange ? this.exponentMax : EInteger.FromInt32(0);
      }

    public final EInteger getEMin() {
        return this.hasExponentRange ? this.exponentMin : EInteger.FromInt32(0);
      }

    public final int getFlags() {
        return this.flags;
      }
public final void setFlags(int value) {
        if (!this.getHasFlags()) {
          throw new IllegalStateException("Can't set flags");
        }
        this.flags = value;
      }

    public final boolean getHasExponentRange() {
        return this.hasExponentRange;
      }

    public final boolean getHasFlags() {
        return this.hasFlags;
      }

    public final boolean getHasMaxPrecision() {
        return !this.bigintPrecision.isZero();
      }

    public final boolean isPrecisionInBits() {
        return this.precisionInBits;
      }

    public final boolean isSimplified() {
        return this.simplified;
      }

    public final EInteger getPrecision() {
        return this.bigintPrecision;
      }

    public final ERounding getRounding() {
        return this.rounding;
      }

    public final int getTraps() {
        return this.traps;
      }

    public static EContext ForPrecision(int precision) {
      return new EContext(
        precision,
        ERounding.HalfUp,
        0,
        0,
        false).WithUnlimitedExponents();
    }

    public static EContext ForPrecisionAndRounding(
      int precision,
      ERounding rounding) {
      return new EContext(
        precision,
        rounding,
        0,
        0,
        false).WithUnlimitedExponents();
    }

    private static final EContext ForRoundingHalfEven = new EContext(
      0,
      ERounding.HalfEven,
      0,
      0,
      false).WithUnlimitedExponents();

    private static final EContext ForRoundingDown = new EContext(
      0,
      ERounding.Down,
      0,
      0,
      false).WithUnlimitedExponents();

    public static EContext ForRounding(ERounding rounding) {
      if (rounding == ERounding.HalfEven) {
        return ForRoundingHalfEven;
      }
      if (rounding == ERounding.Down) {
        return ForRoundingDown;
      }
      return new EContext(
        0,
        rounding,
        0,
        0,
        false).WithUnlimitedExponents();
    }

    public EContext Copy() {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    public boolean ExponentWithinRange(EInteger exponent) {
      if (exponent == null) {
        throw new NullPointerException("exponent");
      }
      if (!this.getHasExponentRange()) {
        return true;
      }
      if (this.bigintPrecision.isZero()) {
        // Only check EMax, since with an unlimited
        // precision, any exponent less than EMin will exceed EMin if
        // the significand is the right size
        // TODO: In next major version, perhaps correct this to check
        // EMin here as well if AdjustExponent is true
        return exponent.compareTo(this.getEMax()) <= 0;
      } else {
        EInteger bigint = exponent;
        if (this.adjustExponent) {
          bigint = bigint.Add(this.bigintPrecision).Subtract(1);
        }
        return (bigint.compareTo(this.getEMin()) >= 0) &&
(exponent.compareTo(this.getEMax()) <= 0);
      }
    }

    @Override public String toString() {
      return "[PrecisionContext ExponentMax=" + this.exponentMax +
        ", Traps=" + this.traps + ", ExponentMin=" + this.exponentMin +
        ", HasExponentRange=" + this.hasExponentRange + ", BigintPrecision=" +
        this.bigintPrecision + ", Rounding=" + this.rounding +
        ", ClampNormalExponents=" + this.clampNormalExponents +
        ", AdjustExponent=" + this.adjustExponent + ", Flags=" +
        this.flags + ", HasFlags=" + this.hasFlags + ", IsSimplified=" +
this.simplified +
        "]";
    }

    public final boolean getHasFlagsOrTraps() {
        return this.getHasFlags() || this.getTraps() != 0;
      }

    public EContext WithAdjustExponent(boolean adjustExponent) {
      return new EContext(
        adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    public EContext WithBigExponentRange(
      EInteger exponentMin,
      EInteger exponentMax) {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        exponentMax,
        exponentMin,
        this.flags,
        true,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    public EContext WithNoFlagsOrTraps() {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        0,
        this.hasExponentRange,
        false,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        0);
    }

    public EContext WithBigPrecision(EInteger bigintPrecision) {
      return new EContext(
        this.adjustExponent,
        bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    public EContext WithBlankFlags() {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        0,
        this.hasExponentRange,
        true,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    public EContext WithExponentClamp(boolean clamp) {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        clamp,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    public EContext WithExponentRange(
      int exponentMinSmall,
      int exponentMaxSmall) {
      return this.WithBigExponentRange(
          EInteger.FromInt32(exponentMinSmall),
          EInteger.FromInt32(exponentMaxSmall));
    }

    public EContext WithNoFlags() {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        0,
        this.hasExponentRange,
        false,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    public EContext WithPrecision(int precision) {
      return this.WithBigPrecision(EInteger.FromInt32(precision));
    }

    public EContext WithPrecisionInBits(boolean isPrecisionBits) {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        isPrecisionBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    public EContext WithRounding(ERounding rounding) {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        rounding,
        this.simplified,
        this.traps);
    }

    public EContext WithSimplified(boolean simplified) {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        simplified,
        this.traps);
    }

    public EContext WithTraps(int traps) {
      // NOTE: Apparently HasFlags must be set to true because
      // some parts of code may treat HasFlags as HasFlagsOrTraps
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        this.hasExponentRange,
        true,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        traps);
    }

    public EContext WithUnlimitedExponents() {
      return new EContext(
        this.adjustExponent,
        this.bigintPrecision,
        this.clampNormalExponents,
        this.exponentMax,
        this.exponentMin,
        this.flags,
        false,
        this.hasFlags,
        this.precisionInBits,
        this.rounding,
        this.simplified,
        this.traps);
    }

    public EContext GetNontrapping() {
       return (this.getTraps() == 0) ? this : this.WithTraps(0).WithBlankFlags();
    }

    public <T> T TriggerTraps(
      T result,
      EContext trappableContext) {
      if (trappableContext == null || trappableContext.getFlags() == 0) {
        return result;
      }
      if (this.getHasFlags()) {
        this.flags |= trappableContext.getFlags();
      }
      int traps = this.getTraps() & trappableContext.getFlags();
      if (traps == 0) {
        return result;
      }
      int mutexConditions = traps & (~(
            EContext.FlagClamped | EContext.FlagInexact |
            EContext.FlagRounded | EContext.FlagSubnormal));
      if (mutexConditions != 0) {
        for (int i = 0; i < 32; ++i) {
          int flag = mutexConditions & (1 << i);
          if (flag != 0) {
            throw new ETrapException(traps, flag, this, result);
          }
        }
      }
      if ((traps & EContext.FlagSubnormal) != 0) {
        throw new ETrapException(
          traps,
          traps & EContext.FlagSubnormal,
          this,
          result);
      }
      if ((traps & EContext.FlagInexact) != 0) {
        throw new ETrapException(
          traps,
          traps & EContext.FlagInexact,
          this,
          result);
      }
      if ((traps & EContext.FlagRounded) != 0) {
        throw new ETrapException(
          traps,
          traps & EContext.FlagRounded,
          this,
          result);
      }
      if ((traps & EContext.FlagClamped) != 0) {
        throw new ETrapException(
          traps,
          traps & EContext.FlagClamped,
          this,
          result);
      }
      return result;
    }
  }
