# com.upokecenter.numbers.EFloats

    public final class EFloats extends java.lang.Object

## Methods

* `static EFloat And​(EFloat ed1,
   EFloat ed2,
   EContext ec)`<br>
* `static EFloat BooleanToEFloat​(boolean b,
               EContext ec)`<br>
* `static EFloat Canonical​(EFloat ed)`<br>
* `static int CompareTotal​(EFloat ed,
            EFloat other,
            EContext ec)`<br>
* `static int CompareTotalMagnitude​(EFloat ed,
                     EFloat other,
                     EContext ec)`<br>
* `static EFloat Copy​(EFloat ed)`<br>
* `static EFloat CopyAbs​(EFloat ed)`<br>
* `static EFloat CopyNegate​(EFloat ed)`<br>
* `static EFloat CopySign​(EFloat ed,
        EFloat other)`<br>
* `static EFloat Int32ToEFloat​(int i32,
             EContext ec)`<br>
* `static EFloat Invert​(EFloat ed1,
      EContext ec)`<br>
* `static boolean IsCanonical​(EFloat ed)`<br>
* `static boolean IsFinite​(EFloat ed)`<br>
* `static boolean IsInfinite​(EFloat ed)`<br>
* `static boolean IsNaN​(EFloat ed)`<br>
* `static boolean IsNormal​(EFloat ed,
        EContext ec)`<br>
* `static boolean IsQuietNaN​(EFloat ed)`<br>
* `static boolean IsSignalingNaN​(EFloat ed)`<br>
* `static boolean IsSigned​(EFloat ed)`<br>
* `static boolean IsSubnormal​(EFloat ed,
           EContext ec)`<br>
* `static boolean IsZero​(EFloat ed)`<br>
* `static EFloat LogB​(EFloat ed,
    EContext ec)`<br>
* `static int NumberClass​(EFloat ed,
           EContext ec)`<br>
* `static java.lang.String NumberClassString​(int nc)`<br>
* `static EFloat Or​(EFloat ed1,
  EFloat ed2,
  EContext ec)`<br>
* `static EFloat Radix​(EContext ec)`<br>
* `static EFloat Rescale​(EFloat ed,
       EFloat scale,
       EContext ec)`<br>
* `static EFloat Rotate​(EFloat ed,
      EFloat ed2,
      EContext ec)`<br>
* `static boolean SameQuantum​(EFloat ed1,
           EFloat ed2)`<br>
* `static EFloat ScaleB​(EFloat ed,
      EFloat ed2,
      EContext ec)`<br>
* `static EFloat Shift​(EFloat ed,
     EFloat ed2,
     EContext ec)`<br>
* `static EFloat Trim​(EFloat ed1,
    EContext ec)`<br>
* `static EFloat Xor​(EFloat ed1,
   EFloat ed2,
   EContext ec)`<br>

## Method Details

### Radix
    public static EFloat Radix​(EContext ec)
### Int32ToEFloat
    public static EFloat Int32ToEFloat​(int i32, EContext ec)
### BooleanToEFloat
    public static EFloat BooleanToEFloat​(boolean b, EContext ec)
### IsCanonical
    public static boolean IsCanonical​(EFloat ed)
### IsFinite
    public static boolean IsFinite​(EFloat ed)
### IsInfinite
    public static boolean IsInfinite​(EFloat ed)
### IsNaN
    public static boolean IsNaN​(EFloat ed)
### IsNormal
    public static boolean IsNormal​(EFloat ed, EContext ec)
### IsQuietNaN
    public static boolean IsQuietNaN​(EFloat ed)
### IsSigned
    public static boolean IsSigned​(EFloat ed)
### IsSignalingNaN
    public static boolean IsSignalingNaN​(EFloat ed)
### NumberClassString
    public static java.lang.String NumberClassString​(int nc)
### NumberClass
    public static int NumberClass​(EFloat ed, EContext ec)
### IsSubnormal
    public static boolean IsSubnormal​(EFloat ed, EContext ec)
### IsZero
    public static boolean IsZero​(EFloat ed)
### LogB
    public static EFloat LogB​(EFloat ed, EContext ec)
### ScaleB
    public static EFloat ScaleB​(EFloat ed, EFloat ed2, EContext ec)
### Shift
    public static EFloat Shift​(EFloat ed, EFloat ed2, EContext ec)
### Rotate
    public static EFloat Rotate​(EFloat ed, EFloat ed2, EContext ec)
### CompareTotal
    public static int CompareTotal​(EFloat ed, EFloat other, EContext ec)
### CompareTotalMagnitude
    public static int CompareTotalMagnitude​(EFloat ed, EFloat other, EContext ec)
### Copy
    public static EFloat Copy​(EFloat ed)
### Canonical
    public static EFloat Canonical​(EFloat ed)
### CopyAbs
    public static EFloat CopyAbs​(EFloat ed)
### CopyNegate
    public static EFloat CopyNegate​(EFloat ed)
### CopySign
    public static EFloat CopySign​(EFloat ed, EFloat other)
### SameQuantum
    public static boolean SameQuantum​(EFloat ed1, EFloat ed2)
### Trim
    public static EFloat Trim​(EFloat ed1, EContext ec)
### Rescale
    public static EFloat Rescale​(EFloat ed, EFloat scale, EContext ec)
### And
    public static EFloat And​(EFloat ed1, EFloat ed2, EContext ec)
### Invert
    public static EFloat Invert​(EFloat ed1, EContext ec)
### Xor
    public static EFloat Xor​(EFloat ed1, EFloat ed2, EContext ec)
### Or
    public static EFloat Or​(EFloat ed1, EFloat ed2, EContext ec)
