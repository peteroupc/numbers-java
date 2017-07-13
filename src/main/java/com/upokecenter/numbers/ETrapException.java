package com.upokecenter.numbers;
/*
Written by Peter O. in 2014.
Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/
If you like this, you should donate to Peter O.
at: http://peteroupc.github.io/
 */

    /**
     * Exception thrown for arithmetic trap errors. (The "E" stands for "extended",
     * and has this prefix to group it with the other classes common to this
     * library, particularly EDecimal, EFloat, and ERational.).
     */
  public final class ETrapException extends ArithmeticException {
private static final long serialVersionUID = 1L;
    private final Object result;
    private final EContext ctx;

    /**
     * Gets the arithmetic context used during the operation that triggered the
     * trap. May be null.
     * @return The arithmetic context used during the operation that triggered the
     * trap. May be null.
     */
    public final EContext getContext() {
        return this.ctx;
      }

    private final int error;

    /**
     * Gets the defined result of the operation that caused the trap.
     * @return The defined result of the operation that caused the trap.
     */
    public final Object getResult() {
        return this.result;
      }

    /**
     * Gets the flag that specifies the kind of error (EContext.FlagXXX). This will
     * only be one flag, such as <code>FlagInexact</code> or FlagSubnormal.
     * @return The flag that specifies the kind of error (EContext.FlagXXX). This
     * will only be one flag, such as. {@code FlagInexact} or FlagSubnormal.
     */
    public final int getError() {
        return this.error;
      }

    private static String FlagToMessage(int flag) {
      return (flag == EContext.FlagClamped) ? "Clamped" : ((flag ==
        EContext.FlagDivideByZero) ? "DivideByZero" : ((flag ==
        EContext.FlagInexact) ? "Inexact" : ((flag ==
        EContext.FlagInvalid) ? "Invalid" : ((flag ==
        EContext.FlagOverflow) ? "Overflow" : ((flag ==
        EContext.FlagRounded) ? "Rounded" : ((flag ==
        EContext.FlagSubnormal) ? "Subnormal" : ((flag ==
        EContext.FlagUnderflow) ? "Underflow" : "Trap")))))));
    }

    /**
     * Initializes a new instance of the {@link
     * com.upokecenter.numbers.ETrapException} class.
     * @param flag A flag that specifies the kind of error ({@code
     * EContext.FlagXXYY}). This will only be one flag, such as {@code
     * FlagInexact} or FlagSubnormal.
     * @param ctx A context object for arbitrary-precision arithmetic settings.
     * @param result The parameter {@code result} is an arbitrary object.
     */
    public ETrapException(int flag, EContext ctx, Object result) {
 super(FlagToMessage(flag));
      this.error = flag;
      this.ctx = (ctx == null) ? null : ctx.Copy();
      this.result = result;
    }
  }
