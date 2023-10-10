package com.upokecenter.numbers;
/*
Written by Peter O.
Any copyright to this work is released to the Public Domain.
In case this is not possible, this work is also
licensed under Creative Commons Zero (CC0):
https://creativecommons.org/publicdomain/zero/1.0/

 */

  public final class ETrapException extends ArithmeticException {
private static final long serialVersionUID = 1L;
    private final Object result;
    private final EContext ctx;

    public final EContext getContext() {
        return this.ctx;
      }

    private final int error;

    private final int errors;

    public ETrapException() {
 this(FlagToMessage(EContext.FlagInvalid));
    }

    public ETrapException(String message) {
 super(message);
      this.error = EContext.FlagInvalid;
      this.errors = EContext.FlagInvalid;
      this.ctx = null;
      this.result = null;
    }

    public ETrapException(String message, Throwable innerException) {
 super(message);
initCause(innerException);;
      this.error = EContext.FlagInvalid;
      this.errors = EContext.FlagInvalid;
      this.ctx = null;
      this.result = null;
    }

    public final Object getResult() {
        return this.result;
      }

    public final int getError() {
        return this.error;
      }

    public final int getErrors() {
        return this.errors;
      }

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

    public ETrapException(int flag, EContext ctx, Object result) {
 this(flag, flag, ctx, result);
    }

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
