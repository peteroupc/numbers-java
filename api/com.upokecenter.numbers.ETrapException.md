# com.upokecenter.numbers.ETrapException

    public final class ETrapException extends java.lang.ArithmeticException

Exception thrown for arithmetic trap errors. (The "E" stands for "extended",
 and has this prefix to group it with the other classes common to this
 library, particularly EDecimal, EFloat, and ERational.).

## Methods

* `ETrapException() ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `ETrapException​(int flag,
              EContext ctx,
              java.lang.Object result) ETrapException`<br>
 Initializes a new instance of the ETrapException.
* `ETrapException​(java.lang.String message) ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `ETrapException​(java.lang.String message,
              java.lang.Throwable innerException) ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `EContext getContext()`<br>
 Gets the arithmetic context used during the operation that triggered the
 trap.
* `int getError()`<br>
 Gets the flag that specifies the kind of error (EContext.FlagXXX).
* `java.lang.Object getResult()`<br>
 Gets the defined result of the operation that caused the trap.

## Constructors

* `ETrapException() ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `ETrapException​(int flag,
              EContext ctx,
              java.lang.Object result) ETrapException`<br>
 Initializes a new instance of the ETrapException.
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
Initializes a new instance of the <code>ETrapException</code>.

**Parameters:**

* <code>flag</code> - The flag that specifies the kind of error (EContext.FlagXXX).
 This will only be one flag, such as <code>FlagInexact</code> or
 FlagSubnormal.

* <code>ctx</code> - The arithmetic context used during the operation that triggered
 the trap. Can be null.

* <code>result</code> - The defined result of the operation that caused the trap.

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
Gets the flag that specifies the kind of error (EContext.FlagXXX). This will
 only be one flag, such as <code>FlagInexact</code> or FlagSubnormal.

**Returns:**

* The flag that specifies the kind of error (EContext.FlagXXX). This
 will only be one flag, such as. <code>FlagInexact</code> or
 FlagSubnormal.
