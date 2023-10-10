# com.upokecenter.numbers.EDecimals

    public final class EDecimals extends Object

## Methods

* `static EDecimal And(EDecimal ed1,
 EDecimal ed2,
 EContext ec)`<br>
  
* `static EDecimal BooleanToEDecimal(boolean b,
 EContext ec)`<br>
  
* `static EDecimal Canonical(EDecimal ed)`<br>
  
* `static int CompareTotal(EDecimal ed,
 EDecimal other,
 EContext ec)`<br>
  
* `static int CompareTotalMagnitude(EDecimal ed,
 EDecimal other,
 EContext ec)`<br>
  
* `static EDecimal Copy(EDecimal ed)`<br>
  
* `static EDecimal CopyAbs(EDecimal ed)`<br>
  
* `static EDecimal CopyNegate(EDecimal ed)`<br>
  
* `static EDecimal CopySign(EDecimal ed,
 EDecimal other)`<br>
  
* `static EDecimal Int32ToEDecimal(int i32,
 EContext ec)`<br>
  
* `static EDecimal Invert(EDecimal ed1,
 EContext ec)`<br>
  
* `static boolean IsCanonical(EDecimal ed)`<br>
  
* `static boolean IsFinite(EDecimal ed)`<br>
  
* `static boolean IsInfinite(EDecimal ed)`<br>
  
* `static boolean IsNaN(EDecimal ed)`<br>
  
* `static boolean IsNormal(EDecimal ed,
 EContext ec)`<br>
  
* `static boolean IsQuietNaN(EDecimal ed)`<br>
  
* `static boolean IsSignalingNaN(EDecimal ed)`<br>
  
* `static boolean IsSigned(EDecimal ed)`<br>
  
* `static boolean IsSubnormal(EDecimal ed,
 EContext ec)`<br>
  
* `static boolean IsZero(EDecimal ed)`<br>
  
* `static EDecimal LogB(EDecimal ed,
 EContext ec)`<br>
  
* `static int NumberClass(EDecimal ed,
 EContext ec)`<br>
  
* `static String NumberClassString(int nc)`<br>
  
* `static EDecimal Or(EDecimal ed1,
 EDecimal ed2,
 EContext ec)`<br>
  
* `static EDecimal Radix(EContext ec)`<br>
  
* `static EDecimal Rescale(EDecimal ed,
 EDecimal scale,
 EContext ec)`<br>
  
* `static EDecimal Rotate(EDecimal ed,
 EDecimal ed2,
 EContext ec)`<br>
  
* `static boolean SameQuantum(EDecimal ed1,
 EDecimal ed2)`<br>
  
* `static EDecimal ScaleB(EDecimal ed,
 EDecimal ed2,
 EContext ec)`<br>
  
* `static EDecimal Shift(EDecimal ed,
 EDecimal ed2,
 EContext ec)`<br>
  
* `static EDecimal Trim(EDecimal ed1,
 EContext ec)`<br>
  
* `static EDecimal Xor(EDecimal ed1,
 EDecimal ed2,
 EContext ec)`<br>
  

## Method Details

### Radix
    public static EDecimal Radix(EContext ec)
### Int32ToEDecimal
    public static EDecimal Int32ToEDecimal(int i32, EContext ec)
### BooleanToEDecimal
    public static EDecimal BooleanToEDecimal(boolean b, EContext ec)
### IsCanonical
    public static boolean IsCanonical(EDecimal ed)
### IsFinite
    public static boolean IsFinite(EDecimal ed)
### IsInfinite
    public static boolean IsInfinite(EDecimal ed)
### IsNaN
    public static boolean IsNaN(EDecimal ed)
### IsNormal
    public static boolean IsNormal(EDecimal ed, EContext ec)
### IsQuietNaN
    public static boolean IsQuietNaN(EDecimal ed)
### IsSigned
    public static boolean IsSigned(EDecimal ed)
### IsSignalingNaN
    public static boolean IsSignalingNaN(EDecimal ed)
### NumberClassString
    public static String NumberClassString(int nc)
### NumberClass
    public static int NumberClass(EDecimal ed, EContext ec)
### IsSubnormal
    public static boolean IsSubnormal(EDecimal ed, EContext ec)
### IsZero
    public static boolean IsZero(EDecimal ed)
### LogB
    public static EDecimal LogB(EDecimal ed, EContext ec)
### ScaleB
    public static EDecimal ScaleB(EDecimal ed, EDecimal ed2, EContext ec)
### Shift
    public static EDecimal Shift(EDecimal ed, EDecimal ed2, EContext ec)
### Rotate
    public static EDecimal Rotate(EDecimal ed, EDecimal ed2, EContext ec)
### CompareTotal
    public static int CompareTotal(EDecimal ed, EDecimal other, EContext ec)
### CompareTotalMagnitude
    public static int CompareTotalMagnitude(EDecimal ed, EDecimal other, EContext ec)
### Copy
    public static EDecimal Copy(EDecimal ed)
### Canonical
    public static EDecimal Canonical(EDecimal ed)
### CopyAbs
    public static EDecimal CopyAbs(EDecimal ed)
### CopyNegate
    public static EDecimal CopyNegate(EDecimal ed)
### CopySign
    public static EDecimal CopySign(EDecimal ed, EDecimal other)
### SameQuantum
    public static boolean SameQuantum(EDecimal ed1, EDecimal ed2)
### Trim
    public static EDecimal Trim(EDecimal ed1, EContext ec)
### Rescale
    public static EDecimal Rescale(EDecimal ed, EDecimal scale, EContext ec)
### And
    public static EDecimal And(EDecimal ed1, EDecimal ed2, EContext ec)
### Invert
    public static EDecimal Invert(EDecimal ed1, EContext ec)
### Xor
    public static EDecimal Xor(EDecimal ed1, EDecimal ed2, EContext ec)
### Or
    public static EDecimal Or(EDecimal ed1, EDecimal ed2, EContext ec)
