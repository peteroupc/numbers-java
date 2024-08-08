package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under the Unlicense: https://unlicense.org/

 */

  /**
   * <p>Exception thrown for arithmetic trap errors. (The "E" stands for
   * "extended", and has this prefix to group it with the other classes common to
   * this library, particularly EDecimal, EFloat, and ERational.). </p> <p>This
   * library may throw exceptions of this type in certain cases, notably when
   * errors occur, and may supply messages to those exceptions (the message can
   * be accessed through the {@code Message} property in.NET or the {@code
   * getMessage()} method in Java). These messages are intended to be read by
   * humans to help diagnose the error (or other cause of the exception); they
   * are not intended to be parsed by computer programs, and the exact text of
   * the messages may change at any time between versions of this library.</p>
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

    private final int errors;

    /**
     * Initializes a new instance of the {@link
     * com.upokecenter.numbers.ETrapException} class.
     */
    public ETrapException() {
 this(FlagToMessage(EContext.FlagInvalid));
    }

    /**
     * Initializes a new instance of the {@link
     * com.upokecenter.numbers.ETrapException} class.
     * @param message The parameter {@code message} is a text string.
     */
    public ETrapException(String message) {
 super(message);
      this.error = EContext.FlagInvalid;
      this.errors = EContext.FlagInvalid;
      this.ctx = null;
      this.result = null;
    }

    /**
     * Initializes a new instance of the {@link
     * com.upokecenter.numbers.ETrapException} class.
     * @param message The parameter {@code message} is a text string.
     * @param innerException The parameter {@code innerException} is an Exception
     * object.
     */
    public ETrapException(String message, Throwable innerException) {
 super(message);
initCause(innerException);;
      this.error = EContext.FlagInvalid;
      this.errors = EContext.FlagInvalid;
      this.ctx = null;
      this.result = null;
    }

    /**
     * Gets the defined result of the operation that caused the trap.
     * @return The defined result of the operation that caused the trap.
     */
    public final Object getResult() {
        return this.result;
      }

    /**
     * Gets the flag that specifies the primary kind of error in one or more
     * operations (EContext.FlagXXX). This will only be one flag, such as {@code
     * FlagInexact} or FlagSubnormal.
     * @return The flag that specifies the primary kind of error in one or more
     * operations.
     */
    public final int getError() {
        return this.error;
      }

    /**
     * Gets the flags that were signaled as the result of one or more operations.
     * This includes the flag specified in the "flag" parameter, but can include
     * other flags. For instance, if "flag" is {@code EContext.FlagInexact}, this
     * parameter might be {@code EContext.FlagInexact | EContext.FlagRounded}.
     * @return The flags that specify the errors in one or more operations.
     */
    public final int getErrors() {
        return this.errors;
      }

    /**
     * Returns whether this trap exception specifies all the flags given. (Flags
     * are signaled in a trap exception as the result of one or more operations
     * involving arbitrary-precision numbers, such as multiplication of two
     * EDecimals.).
     * @param flag A combination of one or more flags, such as {@code
     * EContext.FlagInexact | EContext.FlagRounded}.
     * @return True if this exception pertains to all of the flags given in {@code
     * flag} ; otherwise, false.
     */
    public boolean HasError(int flag) {
      return (this.getError() & flag) == flag;
    }

    private static String FlagToMessage(int flags) {
      StringBuilder sb = new StringBuilder();
      boolean first = true;
      for (int i = 0; i < 32; ++i) {
        int flag = 1 << i;
        if ((flags & flag) != 0) {
          if (!first) {
            sb.append(", ");
          }
          first = false;
          String str = (flag == EContext.FlagClamped) ? "Clamped" : ((flag ==
                EContext.FlagDivideByZero) ? "DivideByZero" : ((flag ==
                  EContext.FlagInexact) ? "Inexact" : ((flag ==
                    EContext.FlagInvalid) ? "Invalid" : ((flag ==
                      EContext.FlagOverflow) ? "Overflow" : ((flag ==
                        EContext.FlagRounded) ? "Rounded" : ((flag ==
                          EContext.FlagSubnormal) ? "Subnormal" : ((flag ==
                            EContext.FlagUnderflow) ? "Underflow" :
"Trap")))))));
          sb.append(str);
        }
      }
      return sb.toString();
    }

    /**
     * Initializes a new instance of the {@link
     * com.upokecenter.numbers.ETrapException} class.
     * @param flag The flag that specifies the kind of error from one or more
     * operations (EContext.FlagXXX). This will only be one flag, such as {@code
     * FlagInexact} or FlagSubnormal.
     * @param ctx The arithmetic context used during the operation that triggered
     * the trap. Can be null.
     * @param result The defined result of the operation that caused the trap.
     */
    public ETrapException(int flag, EContext ctx, Object result) {
 this(flag, flag, ctx, result);
    }

    /**
     * Initializes a new instance of the {@link
     * com.upokecenter.numbers.ETrapException} class.
     * @param flags Specifies the flags that were signaled as the result of one or
     * more operations. This includes the flag specified in the "flag" parameter,
     * but can include other flags. For instance, if "flag" is {@code
     * EContext.FlagInexact}, this parameter might be {@code EContext.FlagInexact |
     * EContext.FlagRounded}.
     * @param flag Specifies the flag that specifies the primary kind of error from
     * one or more operations (EContext.FlagXXX). This will only be one flag, such
     * as {@code FlagInexact} or FlagSubnormal.
     * @param ctx The arithmetic context used during the operation that triggered
     * the trap. Can be null.
     * @param result The defined result of the operation that caused the trap.
     * @throws IllegalArgumentException The parameter {@code flags} doesn't include all
     * the flags in the {@code flag} parameter.
     */
    public ETrapException(int flags, int flag, EContext ctx, Object result) {
 super(FlagToMessage(flags));
      if ((flags & flag) != flag) {
        throw new IllegalArgumentException("flags doesn't include flag");
      }
      this.error = flag;
      this.errors = flags;
      this.ctx = (ctx == null) ? null : ctx.Copy();
      this.result = result;
    }
  }
