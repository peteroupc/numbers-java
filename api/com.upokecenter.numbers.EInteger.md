# com.upokecenter.numbers.EInteger

    public final class EInteger extends Object implements Comparable<EInteger>

Represents an arbitrary-precision integer. (The "E" stands for "extended",
 and has this prefix to group it with the other classes common to this
 library, particularly EDecimal, EFloat, and ERational.) <p>Instances
 of this class are immutable, so they are inherently safe for use by
 multiple threads. Multiple instances of this object with the same
 value are interchangeable, but they should be compared using the
 "Equals" method rather than the "==" operator.</p>
