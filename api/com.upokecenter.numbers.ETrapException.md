# com.upokecenter.numbers.ETrapException

    public final class ETrapException extends ArithmeticException

<p>Exception thrown for arithmetic trap errors. (The "E" stands for
 "extended", and has this prefix to group it with the other classes common to
 this library, particularly EDecimal, EFloat, and ERational.). </p> <p>This
 library may throw exceptions of this type in certain cases, notably when
 errors occur, and may supply messages to those exceptions (the message can
 be accessed through the <code>Message</code> property in.NET or the <code>
 getMessage()</code> method in Java). These messages are intended to be read by
 humans to help diagnose the error (or other cause of the exception); they
 are not intended to be parsed by computer programs, and the exact text of
 the messages may change at any time between versions of this library.</p>

## Constructors

## Methods

* `final EContext getContext()`<br>
 Gets the arithmetic context used during the operation that triggered the
 trap.

* `final int getError()`<br>
 Gets the flag that specifies the primary kind of error in one or more
 operations (EContext.FlagXXX).

* `final int getErrors()`<br>
 Gets the flags that were signaled as the result of one or more operations.

* `final Object getResult()`<br>
 Gets the defined result of the operation that caused the trap.

* `boolean HasError(int flag)`<br>
 Returns whether this trap exception specifies all the flags given.

## Method Details

### getContext
    public final EContext getContext()
Gets the arithmetic context used during the operation that triggered the
 trap. May be null.

**Returns:**

* The arithmetic context used during the operation that triggered the
 trap. May be null.

### getResult
    public final Object getResult()
Gets the defined result of the operation that caused the trap.

**Returns:**

* The defined result of the operation that caused the trap.

### getError
    public final int getError()
Gets the flag that specifies the primary kind of error in one or more
 operations (EContext.FlagXXX). This will only be one flag, such as <code>
 FlagInexact</code> or FlagSubnormal.

**Returns:**

* The flag that specifies the primary kind of error in one or more
 operations.

### getErrors
    public final int getErrors()
Gets the flags that were signaled as the result of one or more operations.
 This includes the flag specified in the "flag" parameter, but can include
 other flags. For instance, if "flag" is <code>EContext.FlagInexact</code>, this
 parameter might be <code>EContext.FlagInexact | EContext.FlagRounded</code>.

**Returns:**

* The flags that specify the errors in one or more operations.

### HasError
    public boolean HasError(int flag)
Returns whether this trap exception specifies all the flags given. (Flags
 are signaled in a trap exception as the result of one or more operations
 involving arbitrary-precision numbers, such as multiplication of two
 EDecimals.).

**Parameters:**

* <code>flag</code> - A combination of one or more flags, such as <code>
 EContext.FlagInexact | EContext.FlagRounded</code>.

**Returns:**

* True if this exception pertains to all of the flags given in <code>
 flag</code> ; otherwise, false.
