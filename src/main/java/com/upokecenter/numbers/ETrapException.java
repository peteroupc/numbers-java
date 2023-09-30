package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
https://creativecommons.org/publicdomain/zero/1.0/

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
   *
   */

  public final class ETrapException extends ArithmeticException {
private static final long serialVersionUID = 1L;
    private final Object result;
    private final EContext ctx;

    /**
     * <p>Gets the arithmetic context used during the operation that triggered the
     * trap. May be null.</p>
     * @return <p>The arithmetic context used during the operation that triggered
     * the trap. May be null.</p>
     *
     */
    public final EContext getContext() {
        return this.ctx;
      }

    private final int error;

    private final int errors;

    /**
     * <p>Initializes a new instance of the {@link
     * com.upokecenter.numbers.ETrapException} class.</p>
     *
     */
    public ETrapException() {
 this(FlagToMessage(EContext.FlagInvalid));
    }

    /**
     * <p>Initializes a new instance of the {@link
     * com.upokecenter.numbers.ETrapException} class.</p>
     * <p>@param message </p>
     * <p>The parameter {@code message} is a text string.</p>
     *
     */
    public ETrapException(String message) {
 super(message);
      this.error = EContext.FlagInvalid;
      this.errors = EContext.FlagInvalid;
      this.ctx = null;
      this.result = null;
    }

    /**
     * <p>Initializes a new instance of the {@link
     * com.upokecenter.numbers.ETrapException} class.</p>
     * <p>@param message </p>
     * <p>The parameter {@code message} is a text string.</p>
     * <p>@param innerException </p>
     * <p>The parameter {@code innerException} is an
     * Exception object.</p>
     *
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
     * <p>Gets the defined result of the operation that caused the trap.</p>
     * @return <p>The defined result of the operation that caused the trap.</p>
     *
     */
    public final Object getResult() {
        return this.result;
      }

    /**
     * <p>Gets the flag that specifies the primary kind of error in one or more
     * operations (EContext.FlagXXX). This will only be one flag, such as {@code
     * FlagInexact} or FlagSubnormal.</p>
     * @return <p>The flag that specifies the primary kind of error in one or more
     * operations.</p>
     *
     */
    public final int getError() {
        return this.error;
      }

    /**
     * <p>Gets the flags that were signaled as the result of one or more
     * operations. This includes the flag specified in the "flag" parameter, but
     * can include other flags. For instance, if "flag" is {@code
     * EContext.FlagInexact}, this parameter might be {@code EContext.FlagInexact |
     * EContext.FlagRounded}.</p>
     * @return <p>The flags that specify the errors in one or more operations.</p>
     *
     */
    public final int getErrors() {
        return this.errors;
      }

    /**
     * <p>Returns whether this trap exception specifies all the flags given. (Flags
     * are signaled in a trap exception as the result of one or more operations
     * involving arbitrary-precision numbers, such as multiplication of two
     * EDecimals.).</p>
     * <p>@param flag </p>
     * <p>A combination of one or more flags, such as {@code
     * EContext.FlagInexact | EContext.FlagRounded}.</p>
     * @return <p>True if this exception pertains to all of the flags given in
     * {@code flag} ; otherwise, false.</p>
     *
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
     * <p>Initializes a new instance of the {@link
     * com.upokecenter.numbers.ETrapException} class.</p>
     * <p>@param flag </p>
     * <p>The flag that specifies the kind of error from one or more
     * operations (EContext.FlagXXX). This will only be one flag, such as {@code
     * FlagInexact} or FlagSubnormal.</p>
     * <p>@param ctx </p>
     * <p>The arithmetic context used during the operation that
     * triggered the trap. Can be null.</p>
     * <p>@param result </p>
     * <p>The defined result of the operation that caused the
     * trap.</p>
     *
     */
    public ETrapException(int flag, EContext ctx, Object result) {
 this(flag, flag, ctx, result);
    }

    /**
     * <p>Initializes a new instance of the {@link
     * com.upokecenter.numbers.ETrapException} class.</p>
     * <p>@param flags </p>
     * <p>Specifies the flags that were signaled as the result of one
     * or more operations. This includes the flag specified in the "flag"
     * parameter, but can include other flags. For instance, if "flag" is {@code
     * EContext.FlagInexact}, this parameter might be {@code EContext.FlagInexact |
     * EContext.FlagRounded}.</p>
     * <p>@param flag </p>
     * <p>Specifies the flag that specifies the primary kind of error
     * from one or more operations (EContext.FlagXXX). This will only be one flag,
     * such as {@code FlagInexact} or FlagSubnormal.</p>
     * <p>@param ctx </p>
     * <p>The arithmetic context used during the operation that
     * triggered the trap. Can be null.</p>
     * <p>@param result </p>
     * <p>The defined result of the operation that caused the
     * trap.</p>
     * @throws IllegalArgumentException <p>The parameter {@code flags} doesn't include all
     * the flags in the {@code flag} parameter.</p>
     *
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
