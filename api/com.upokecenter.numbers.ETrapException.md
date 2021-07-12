# com.upokecenter.numbers.ETrapException

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

## Methods

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

## Method Details

### <a id='getContext()'>getContext</a>

Gets the arithmetic context used during the operation that triggered the
 trap. May be null.

**Returns:**

* The arithmetic context used during the operation that triggered the
 trap. May be null.

### <a id='getResult()'>getResult</a>

Gets the defined result of the operation that caused the trap.

**Returns:**

* The defined result of the operation that caused the trap.

### <a id='getError()'>getError</a>

Gets the flag that specifies the primary kind of error in one or more
 operations (EContext.FlagXXX). This will only be one flag, such as
 <code>FlagInexact</code> or FlagSubnormal.

**Returns:**

* The flag that specifies the primary kind of error in one or more
 operations.

### <a id='getErrors()'>getErrors</a>

Gets the flags that were signaled as the result of one or more operations.
  This includes the flag specified in the "flag" parameter, but can
  include other flags. For instance, if "flag" is
 <code>EContext.FlagInexact</code>, this parameter might be
 <code>EContext.FlagInexact | EContext.FlagRounded</code>.

**Returns:**

* The flags that specify the errors in one or more operations.

### <a id='HasError(int)'>HasError</a>

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
