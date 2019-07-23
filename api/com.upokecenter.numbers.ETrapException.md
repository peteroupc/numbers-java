# com.upokecenter.numbers.ETrapException

    public final class ETrapException extends java.lang.ArithmeticException

Exception thrown for arithmetic trap errors. (The "E" stands for "extended",
 and has this prefix to group it with the other classes common to
 this library, particularly EDecimal, EFloat, and ERational.).

## Methods

* `ETrapException​(int flag,
              EContext ctx,
              java.lang.Object result) ETrapException`<br>
 Initializes a new instance of the ETrapException.
* `EContext getContext()`<br>
 Gets the arithmetic context used during the operation that triggered the
 trap.
* `int getError()`<br>
 Gets the flag that specifies the kind of error (EContext.FlagXXX).
* `java.lang.Object getResult()`<br>
 Gets the defined result of the operation that caused the trap.

## Constructors

* `ETrapException​(int flag,
              EContext ctx,
              java.lang.Object result) ETrapException`<br>
 Initializes a new instance of the ETrapException.

## Method Details

### ETrapException
    public ETrapException​(int flag, EContext ctx, java.lang.Object result)
Initializes a new instance of the <code>ETrapException</code>.

**Parameters:**

* <code>flag</code> - A 32-bit signed integer.

* <code>ctx</code> - An EContext object.

* <code>result</code> - An arbitrary object.

### ETrapException
    public ETrapException​(int flag, EContext ctx, java.lang.Object result)
Initializes a new instance of the <code>ETrapException</code>.

**Parameters:**

* <code>flag</code> - A 32-bit signed integer.

* <code>ctx</code> - An EContext object.

* <code>result</code> - An arbitrary object.

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
