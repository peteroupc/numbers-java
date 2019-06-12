# com.upokecenter.numbers.EDecimalExtras

    public final class EDecimalExtras extends java.lang.Object

Not documented yet.

## Methods

* `static EDecimal And​(EDecimal ed1,
   EDecimal ed2,
   EContext ec)`<br>
 Not documented yet.
* `static EDecimal BoolToEDecimal​(boolean b,
              EContext ec)`<br>
 Not documented yet.
* `static EDecimal Canonical​(EDecimal ed)`<br>
 Not documented yet.
* `static int CompareTotal​(EDecimal ed,
            EDecimal other,
            EContext ec)`<br>
 Not documented yet.
* `static int CompareTotalMagnitude​(EDecimal ed,
                     EDecimal other,
                     EContext ec)`<br>
 Not documented yet.
* `static EDecimal Copy​(EDecimal ed)`<br>
 Not documented yet.
* `static EDecimal CopyAbs​(EDecimal ed)`<br>
 Not documented yet.
* `static EDecimal CopyNegate​(EDecimal ed)`<br>
 Not documented yet.
* `static EDecimal CopySign​(EDecimal ed,
        EDecimal other)`<br>
 Not documented yet.
* `static EDecimal Int32ToEDecimal​(int i32,
               EContext ec)`<br>
 Not documented yet.
* `static EDecimal Invert​(EDecimal ed1,
      EContext ec)`<br>
 Not documented yet.
* `static boolean IsCanonical​(EDecimal ed)`<br>
 Not documented yet.
* `static boolean IsFinite​(EDecimal ed)`<br>
 Not documented yet.
* `static boolean IsInfinite​(EDecimal ed)`<br>
 Not documented yet.
* `static boolean IsNaN​(EDecimal ed)`<br>
 Not documented yet.
* `static boolean IsNormal​(EDecimal ed,
        EContext ec)`<br>
 Not documented yet.
* `static boolean IsQuietNaN​(EDecimal ed)`<br>
 Not documented yet.
* `static boolean IsSignalingNaN​(EDecimal ed)`<br>
 Not documented yet.
* `static boolean IsSigned​(EDecimal ed)`<br>
 Not documented yet.
* `static boolean IsSubnormal​(EDecimal ed,
           EContext ec)`<br>
 Not documented yet.
* `static boolean IsZero​(EDecimal ed)`<br>
 Not documented yet.
* `static EDecimal LogB​(EDecimal ed,
    EContext ec)`<br>
 Not documented yet.
* `static int NumberClass​(EDecimal ed,
           EContext ec)`<br>
 Not documented yet.
* `static java.lang.String NumberClassString​(int nc)`<br>
 Not documented yet.
* `static EDecimal Or​(EDecimal ed1,
  EDecimal ed2,
  EContext ec)`<br>
 Not documented yet.
* `static EDecimal Radix​(EContext ec)`<br>
 Not documented yet.
* `static EDecimal Rescale​(EDecimal ed,
       EDecimal scale,
       EContext ec)`<br>
 Not documented yet.
* `static EDecimal Rotate​(EDecimal ed,
      EDecimal ed2,
      EContext ec)`<br>
 Not documented yet.
* `static boolean SameQuantum​(EDecimal ed1,
           EDecimal ed2)`<br>
 Not documented yet.
* `static EDecimal ScaleB​(EDecimal ed,
      EDecimal ed2,
      EContext ec)`<br>
 Not documented yet.
* `static EDecimal Shift​(EDecimal ed,
     EDecimal ed2,
     EContext ec)`<br>
 Not documented yet.
* `static EDecimal Trim​(EDecimal ed1,
    EContext ec)`<br>
 Not documented yet.
* `static EDecimal Xor​(EDecimal ed1,
   EDecimal ed2,
   EContext ec)`<br>
 Not documented yet.

## Method Details

### Radix
    public static EDecimal Radix​(EContext ec)
Not documented yet.

**Parameters:**

* <code>ec</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Int32ToEDecimal
    public static EDecimal Int32ToEDecimal​(int i32, EContext ec)
Not documented yet.

**Parameters:**

* <code>i32</code> - Not documented yet.

* <code>ec</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### BoolToEDecimal
    public static EDecimal BoolToEDecimal​(boolean b, EContext ec)
Not documented yet.

**Parameters:**

* <code>b</code> - Not documented yet.

* <code>ec</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### IsCanonical
    public static boolean IsCanonical​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* A Boolean object.

### IsFinite
    public static boolean IsFinite​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* A Boolean object.

### IsInfinite
    public static boolean IsInfinite​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* A Boolean object.

### IsNaN
    public static boolean IsNaN​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* A Boolean object.

### IsNormal
    public static boolean IsNormal​(EDecimal ed, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>ec</code> - Not documented yet.

**Returns:**

* A Boolean object.

### IsQuietNaN
    public static boolean IsQuietNaN​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* A Boolean object.

### IsSigned
    public static boolean IsSigned​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* A Boolean object.

### IsSignalingNaN
    public static boolean IsSignalingNaN​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* A Boolean object.

### NumberClassString
    public static java.lang.String NumberClassString​(int nc)
Not documented yet.

**Parameters:**

* <code>nc</code> - Not documented yet.

**Returns:**

* A string object.

### NumberClass
    public static int NumberClass​(EDecimal ed, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>ec</code> - Not documented yet.

**Returns:**

* A 32-bit signed integer.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> is null.

### IsSubnormal
    public static boolean IsSubnormal​(EDecimal ed, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>ec</code> - Not documented yet.

**Returns:**

* A Boolean object.

### IsZero
    public static boolean IsZero​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* A Boolean object.

### LogB
    public static EDecimal LogB​(EDecimal ed, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>ec</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### ScaleB
    public static EDecimal ScaleB​(EDecimal ed, EDecimal ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>ed2</code> - Not documented yet.

* <code>ec</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> or <code>ed2</code> is
 null.

### Shift
    public static EDecimal Shift​(EDecimal ed, EDecimal ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>ed2</code> - Not documented yet.

* <code>ec</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

**Throws:**

* <code>java.lang.NullPointerException</code> - The parameter <code>ed</code> or <code>ed2</code> is
 null.

### Rotate
    public static EDecimal Rotate​(EDecimal ed, EDecimal ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>ed2</code> - Not documented yet.

* <code>ec</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### CompareTotal
    public static int CompareTotal​(EDecimal ed, EDecimal other, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>other</code> - Not documented yet.

* <code>ec</code> - Not documented yet. (3).

**Returns:**

* A 32-bit signed integer.

### CompareTotalMagnitude
    public static int CompareTotalMagnitude​(EDecimal ed, EDecimal other, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>other</code> - Not documented yet.

* <code>ec</code> - Not documented yet. (3).

**Returns:**

* A 32-bit signed integer.

### Copy
    public static EDecimal Copy​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Canonical
    public static EDecimal Canonical​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### CopyAbs
    public static EDecimal CopyAbs​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### CopyNegate
    public static EDecimal CopyNegate​(EDecimal ed)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### CopySign
    public static EDecimal CopySign​(EDecimal ed, EDecimal other)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>other</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### SameQuantum
    public static boolean SameQuantum​(EDecimal ed1, EDecimal ed2)
Not documented yet.

**Parameters:**

* <code>ed1</code> - Not documented yet.

* <code>ed2</code> - Not documented yet.

**Returns:**

* A Boolean object.

### Trim
    public static EDecimal Trim​(EDecimal ed1, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - Not documented yet.

* <code>ec</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Rescale
    public static EDecimal Rescale​(EDecimal ed, EDecimal scale, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed</code> - Not documented yet.

* <code>scale</code> - Not documented yet.

* <code>ec</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### And
    public static EDecimal And​(EDecimal ed1, EDecimal ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - Not documented yet.

* <code>ed2</code> - Not documented yet.

* <code>ec</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### Invert
    public static EDecimal Invert​(EDecimal ed1, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - Not documented yet.

* <code>ec</code> - Not documented yet.

**Returns:**

* An EDecimal object.

### Xor
    public static EDecimal Xor​(EDecimal ed1, EDecimal ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - Not documented yet.

* <code>ed2</code> - Not documented yet.

* <code>ec</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.

### Or
    public static EDecimal Or​(EDecimal ed1, EDecimal ed2, EContext ec)
Not documented yet.

**Parameters:**

* <code>ed1</code> - Not documented yet.

* <code>ed2</code> - Not documented yet.

* <code>ec</code> - Not documented yet. (3).

**Returns:**

* An EDecimal object.
