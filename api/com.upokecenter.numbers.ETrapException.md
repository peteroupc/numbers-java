# com.upokecenter.numbers.ETrapException

    public final class ETrapException extends java.lang.ArithmeticException

## Methods

* `ETrapException()`<br>
* `ETrapException​(int flags,
              int flag,
              EContext ctx,
              java.lang.Object result)`<br>
* `ETrapException​(int flag,
              EContext ctx,
              java.lang.Object result)`<br>
* `ETrapException​(java.lang.String message)`<br>
* `ETrapException​(java.lang.String message,
              java.lang.Throwable innerException)`<br>
* `EContext getContext()`<br>
* `int getError()`<br>
* `int getErrors()`<br>
* `java.lang.Object getResult()`<br>
* `boolean HasError​(int flag)`<br>

## Constructors

* `ETrapException()`<br>
* `ETrapException​(int flags,
              int flag,
              EContext ctx,
              java.lang.Object result)`<br>
* `ETrapException​(int flag,
              EContext ctx,
              java.lang.Object result)`<br>
* `ETrapException​(java.lang.String message)`<br>
* `ETrapException​(java.lang.String message,
              java.lang.Throwable innerException)`<br>

## Method Details

### ETrapException
    public ETrapException()
### ETrapException
    public ETrapException​(java.lang.String message)
### ETrapException
    public ETrapException​(java.lang.String message, java.lang.Throwable innerException)
### ETrapException
    public ETrapException​(int flag, EContext ctx, java.lang.Object result)
### ETrapException
    public ETrapException​(int flags, int flag, EContext ctx, java.lang.Object result)
### getContext
    public final EContext getContext()
### getResult
    public final java.lang.Object getResult()
### getError
    public final int getError()
### getErrors
    public final int getErrors()
### HasError
    public boolean HasError​(int flag)
