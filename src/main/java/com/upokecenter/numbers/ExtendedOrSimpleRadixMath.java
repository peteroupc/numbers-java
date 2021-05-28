package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
http://creativecommons.org/publicdomain/zero/1.0/

 */

  class ExtendedOrSimpleRadixMath<T> implements IRadixMath<T> {
    private final RadixMath<T> ext;
    private final SimpleRadixMath<T> simp;

    public ExtendedOrSimpleRadixMath(IRadixMathHelper<T> helper) {
      this.ext = new RadixMath<T>(helper);
      this.simp = new SimpleRadixMath<T>(this.ext);
    }

    public IRadixMathHelper<T> GetHelper() {
      // Both RadixMath implementations return the
      // same helper, so use the ext implementation
      return this.ext.GetHelper();
    }

    public T DivideToIntegerNaturalScale(
      T thisValue,
      T divisor,
      EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.DivideToIntegerNaturalScale(thisValue, divisor, ctx) :
        this.simp.DivideToIntegerNaturalScale(thisValue, divisor, ctx);
    }

    public T DivideToIntegerZeroScale(
      T thisValue,
      T divisor,
      EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.DivideToIntegerZeroScale(thisValue, divisor, ctx) :
        this.simp.DivideToIntegerZeroScale(thisValue, divisor, ctx);
    }

    public T Abs(T value, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ? this.ext.Abs(value, ctx) :
        this.simp.Abs(value, ctx);
    }

    public T Negate(T value, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ? this.ext.Negate(value, ctx) :
        this.simp.Negate(value, ctx);
    }

    public T Remainder(
      T thisValue,
      T divisor,
      EContext ctx,
      boolean roundAfterDivide) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.Remainder(thisValue, divisor, ctx, roundAfterDivide) :
        this.simp.Remainder(thisValue, divisor, ctx, roundAfterDivide);
    }

    public T RemainderNear(T thisValue, T divisor, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.RemainderNear(thisValue, divisor, ctx) :
        this.simp.RemainderNear(thisValue, divisor, ctx);
    }

    public T Pi(EContext ctx) {
      return (!ctx.isSimplified()) ? this.ext.Pi(ctx) : this.simp.Pi(ctx);
    }

    public T Power(T thisValue, T pow, EContext ctx) {
      return (
          ctx == null || !ctx.isSimplified()) ? this.ext.Power(
          thisValue,
          pow,
          ctx) : this.simp.Power(thisValue, pow, ctx);
    }

    public T Ln(T thisValue, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ? this.ext.Ln(thisValue, ctx) :
        this.simp.Ln(thisValue, ctx);
    }

    public T Exp(T thisValue, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ? this.ext.Exp(thisValue,
  ctx) : this.simp.Exp(thisValue, ctx);
    }

    public T SquareRoot(T thisValue, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.SquareRoot(
          thisValue,
          ctx) : this.simp.SquareRoot(
          thisValue,
          ctx);
    }

    public T NextMinus(T thisValue, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.NextMinus(thisValue, ctx) : this.simp.NextMinus(
          thisValue,
          ctx);
    }

    public T NextToward(T thisValue, T otherValue, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.NextToward(thisValue, otherValue, ctx) :
        this.simp.NextToward(thisValue, otherValue, ctx);
    }

    public T NextPlus(T thisValue, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.NextPlus(thisValue, ctx) : this.simp.NextPlus(thisValue,
  ctx);
    }

    public T DivideToExponent(
      T thisValue,
      T divisor,
      EInteger desiredExponent,
      EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.DivideToExponent(thisValue, divisor, desiredExponent, ctx) :
        this.simp.DivideToExponent(thisValue, divisor, desiredExponent, ctx);
    }
    public T Divide(T thisValue, T divisor, EContext ctx) {
      return (
          ctx == null || !ctx.isSimplified()) ? this.ext.Divide(
          thisValue,
          divisor,
          ctx) : this.simp.Divide(thisValue, divisor, ctx);
    }

    public T MinMagnitude(T a, T b, EContext ctx) {
      return (
          ctx == null || !ctx.isSimplified()) ? this.ext.MinMagnitude(
          a,
          b,
          ctx) : this.simp.MinMagnitude(a, b, ctx);
    }

    public T MaxMagnitude(T a, T b, EContext ctx) {
      return (
          ctx == null || !ctx.isSimplified()) ? this.ext.MaxMagnitude(
          a,
          b,
          ctx) : this.simp.MaxMagnitude(a, b, ctx);
    }

    public T Max(T a, T b, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ? this.ext.Max(a, b, ctx) :
        this.simp.Max(a, b, ctx);
    }

    public T Min(T a, T b, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ? this.ext.Min(a, b, ctx) :
        this.simp.Min(a, b, ctx);
    }

    public T Multiply(T thisValue, T other, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.Multiply(thisValue, other, ctx) :
        this.simp.Multiply(thisValue, other, ctx);
    }

    public T MultiplyAndAdd(
      T thisValue,
      T multiplicand,
      T augend,
      EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.MultiplyAndAdd(thisValue, multiplicand, augend, ctx) :
        this.simp.MultiplyAndAdd(thisValue, multiplicand, augend, ctx);
    }

    public T Plus(T thisValue, EContext ctx) {
      return (
          ctx == null || !ctx.isSimplified()) ? this.ext.Plus(
          thisValue,
          ctx) : this.simp.Plus(thisValue, ctx);
    }

    public T RoundToPrecision(T thisValue, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.RoundToPrecision(thisValue, ctx) :
        this.simp.RoundToPrecision(thisValue, ctx);
    }

    public T RoundAfterConversion(T thisValue, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.RoundAfterConversion(thisValue, ctx) :
        this.simp.RoundAfterConversion(thisValue, ctx);
    }

    public T SignalOverflow(EContext ctx, boolean neg) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.SignalOverflow(ctx, neg) : this.simp.SignalOverflow(ctx, neg);
    }

    public T Quantize(T thisValue, T otherValue, EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.Quantize(thisValue, otherValue, ctx) :
        this.simp.Quantize(thisValue, otherValue, ctx);
    }

    public T RoundToExponentExact(
      T thisValue,
      EInteger expOther,
      EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.RoundToExponentExact(thisValue, expOther, ctx) :
        this.simp.RoundToExponentExact(thisValue, expOther, ctx);
    }

    public T RoundToExponentSimple(
      T thisValue,
      EInteger expOther,
      EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.RoundToExponentSimple(thisValue, expOther, ctx) :
        this.simp.RoundToExponentSimple(thisValue, expOther, ctx);
    }

    public T RoundToExponentNoRoundedFlag(
      T thisValue,
      EInteger exponent,
      EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.RoundToExponentNoRoundedFlag(thisValue, exponent, ctx) :
        this.simp.RoundToExponentNoRoundedFlag(thisValue, exponent, ctx);
    }

    public T Reduce(T thisValue, EContext ctx) {
      return (
          ctx == null || !ctx.isSimplified()) ? this.ext.Reduce(
          thisValue,
          ctx) : this.simp.Reduce(thisValue, ctx);
    }

    public T Add(T thisValue, T other, EContext ctx) {
      return (
          ctx == null || !ctx.isSimplified()) ? this.ext.Add(
          thisValue,
          other,
          ctx) : this.simp.Add(thisValue, other, ctx);
    }

    public T AddEx(
      T thisValue,
      T other,
      EContext ctx,
      boolean roundToOperandPrecision) {
      return (
          ctx == null || !ctx.isSimplified()) ? this.ext.AddEx(
          thisValue,
          other,
          ctx,
          roundToOperandPrecision) :
        this.simp.AddEx(thisValue, other, ctx, roundToOperandPrecision);
    }

    public T CompareToWithContext(
      T thisValue,
      T otherValue,
      boolean treatQuietNansAsSignaling,
      EContext ctx) {
      return (ctx == null || !ctx.isSimplified()) ?
        this.ext.CompareToWithContext(
          thisValue,
          otherValue,
          treatQuietNansAsSignaling,
          ctx) : this.simp.CompareToWithContext(
          thisValue,
          otherValue,
          treatQuietNansAsSignaling,
          ctx);
    }

    public int compareTo(T thisValue, T otherValue) {
      return this.ext.compareTo(thisValue, otherValue);
    }
  }
