# com.upokecenter.numbers.ETrapException

    public final class ETrapException extends java.lang.ArithmeticException

Exception thrown for arithmetic trap errors. (The "E" stands for "extended",
 and has this prefix to group it with the other classes common to this
 library, particularly EDecimal, EFloat, and ERational.).

## Methods

* `ETrapException() ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `ETrapException​(int flags,
              int flag,
              EContext ctx,
              java.lang.Object result) ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `ETrapException​(int flag,
              EContext ctx,
              java.lang.Object result) ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `ETrapException​(java.lang.String message) ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `ETrapException​(java.lang.String message,
              java.lang.Throwable innerException) ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `EContext getContext()`<br>
 Gets the arithmetic context used during the operation that triggered the
 trap.
* `int getError()`<br>
 Gets the flag that specifies the primary kind of error in one or more
 operations (EContext.FlagXXX).
* `int getErrors()`<br>
 Gets the flags that were signaled as the result of one or more operations.
* `java.lang.Object getResult()`<br>
 Gets the defined result of the operation that caused the trap.
* `boolean HasError​(int flag)`<br>
 Returns whether this trap exception specifies all the flags given.

## Constructors

* `ETrapException() ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `ETrapException​(int flags,
              int flag,
              EContext ctx,
              java.lang.Object result) ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `ETrapException​(int flag,
              EContext ctx,
              java.lang.Object result) ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `ETrapException​(java.lang.String message) ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `ETrapException​(java.lang.String message,
              java.lang.Throwable innerException) ETrapException`<br>
 Initializes a new instance of the ETrapException class.

## Method Details

### ETrapException
    public ETrapException()
Initializes a new instance of the <code>ETrapException</code> class.
### ETrapException
    public ETrapException​(java.lang.String message)
Initializes a new instance of the <code>ETrapException</code> class.

**Parameters:**

* <code>message</code> - The parameter <code>message</code> is a text string.

### ETrapException
    public ETrapException​(java.lang.String message, java.lang.Throwable innerException)
Initializes a new instance of the <code>ETrapException</code> class.

**Parameters:**

* <code>message</code> - The parameter <code>message</code> is a text string.

* <code>innerException</code> - The parameter <code>innerException</code> is an Exception
 object.

### ETrapException
    public ETrapException​(int flag, EContext ctx, java.lang.Object result)
Initializes a new instance of the <code>ETrapException</code> class.

**Parameters:**

* <code>flag</code> - The flag that specifies the kind of error from one or more
 operations (EContext.FlagXXX). This will only be one flag, such as
 <code>FlagInexact</code> or FlagSubnormal.

* <code>ctx</code> - The arithmetic context used during the operation that triggered
 the trap. Can be null.

* <code>result</code> - The defined result of the operation that caused the trap.

### ETrapException
    public ETrapException​(int flags, int flag, EContext ctx, java.lang.Object result)
Initializes a new instance of the <code>ETrapException</code> class.

**Parameters:**

* <code>flags</code> - Specifies the flags that were signaled as the result of one or
  more operations. This includes the flag specified in the "flag"
  parameter, but can include other flags. For instance, if "flag" is
 <code>EContext.FlagInexact</code>, this parameter might be <code>
 EContext.FlagInexact | EContext.FlagRounded</code>.

* <code>flag</code> - Specifies the flag that specifies the primary kind of error from
 one or more operations (EContext.FlagXXX). This will only be one
 flag, such as <code>FlagInexact</code> or FlagSubnormal.

* <code>ctx</code> - The arithmetic context used during the operation that triggered
 the trap. Can be null.

* <code>result</code> - The defined result of the operation that caused the trap.

**Throws:**

* <code>java.lang.IllegalArgumentException</code> - The parameter <code>flags</code> doesn't include all
 the flags in the <code>flag</code> parameter.

### getContext
    public final EContext getContext()
Gets the arithmetic context used during the operation that triggered the
 trap. May be null.

**Returns:**

* The arithmetic context used during the operation that triggered the
 trap. May be null.

### getResult
    public final java.lang.Object getResult()
Gets the defined result of the operation that caused the trap.

**Returns:**

* The defined result of the operation that caused the trap.

### getError
    public final int getError()
Gets the flag that specifies the primary kind of error in one or more
 operations (EContext.FlagXXX). This will only be one flag, such as
 <code>FlagInexact</code> or FlagSubnormal.

**Returns:**

* The flag that specifies the primary kind of error in one or more
 operations.

### getErrors
    public final int getErrors()
Gets the flags that were signaled as the result of one or more operations.
  This includes the flag specified in the "flag" parameter, but can
  include other flags. For instance, if "flag" is
 <code>EContext.FlagInexact</code>, this parameter might be
 <code>EContext.FlagInexact | EContext.FlagRounded</code>.

**Returns:**

* The flags that specify the errors in one or more operations.

### HasError
    public boolean HasError​(int flag)
Returns whether this trap exception specifies all the flags given. (Flags
 are signaled in a trap exception as the result of one or more
 operations involving arbitrary-precision numbers, such as
 multiplication of two EDecimals.).

**Parameters:**

* <code>flag</code> - A combination of one or more flags, such as <code>
 EContext.FlagInexact | EContext.FlagRounded</code>.

**Returns:**

* True if this exception pertains to all of the flags given in <code>
 flag</code> ; otherwise, false.
