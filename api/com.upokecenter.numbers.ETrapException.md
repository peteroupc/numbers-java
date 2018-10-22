# com.upokecenter.numbers.ETrapException

    public final class ETrapException extends ArithmeticException

Exception thrown for arithmetic trap errors. (The "E" stands for "extended",
 and has this prefix to group it with the other classes common to this
 library, particularly EDecimal, EFloat, and ERational.).

## Methods

* `ETrapException​(int flag,
              EContext ctx,
              Object result) ETrapException`<br>
 Initializes a new instance of the ETrapException class.
* `EContext getContext()`<br>
 Gets the arithmetic context used during the operation that triggered the
 trap.
* `int getError()`<br>
 Gets the flag that specifies the kind of error (EContext.FlagXXX).
* `Object getResult()`<br>
 Gets the defined result of the operation that caused the trap.

## Constructors

* `ETrapException​(int flag,
              EContext ctx,
              Object result) ETrapException`<br>
 Initializes a new instance of the ETrapException class.

## Method Details

### ETrapException
    public ETrapException​(int flag, EContext ctx, Object result)
Initializes a new instance of the <code>ETrapException</code> class.

**Parameters:**

* <code>flag</code> - A flag that specifies the kind of error (<code>
 EContext.FlagXXYY</code>). This will only be one flag, such as <code>
 FlagInexact</code> or FlagSubnormal.

* <code>ctx</code> - A context object for arbitrary-precision arithmetic settings.

* <code>result</code> - The parameter <code>result</code> is an arbitrary object.

### ETrapException
    public ETrapException​(int flag, EContext ctx, Object result)
Initializes a new instance of the <code>ETrapException</code> class.

**Parameters:**

* <code>flag</code> - A flag that specifies the kind of error (<code>
 EContext.FlagXXYY</code>). This will only be one flag, such as <code>
 FlagInexact</code> or FlagSubnormal.

* <code>ctx</code> - A context object for arbitrary-precision arithmetic settings.

* <code>result</code> - The parameter <code>result</code> is an arbitrary object.

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
Gets the flag that specifies the kind of error (EContext.FlagXXX). This will
 only be one flag, such as <code>FlagInexact</code> or FlagSubnormal.

**Returns:**

* The flag that specifies the kind of error (EContext.FlagXXX). This
 will only be one flag, such as. <code>FlagInexact</code> or FlagSubnormal.
