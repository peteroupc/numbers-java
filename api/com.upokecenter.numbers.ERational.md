# com.upokecenter.numbers.ERational

    public final class ERational extends Object implements Comparable<ERational>

Represents an arbitrary-precision rational number. This class can't be
 inherited. (The "E" stands for "extended", meaning that instances of
 this class can be values other than numbers proper, such as infinity
 and not-a-number.) <p><b>Thread safety:</b> Instances of this class
 are immutable, so they are inherently safe for use by multiple
 threads. Multiple instances of this object with the same properties
 are interchangeable, so they should not be compared using the "=="
 operator (which might only check if each side of the operator is the
 same instance).</p>
