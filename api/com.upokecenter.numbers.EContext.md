# com.upokecenter.numbers.EContext

    public final class EContext extends Object

Contains parameters for controlling the precision, rounding, and exponent
 range of arbitrary-precision numbers. (The "E" stands for "extended",
 and has this prefix to group it with the other classes common to this
 library, particularly EDecimal, EFloat, and ERational.). <p><b>Thread
 safety:</b> With one exception, instances of this class are immutable
 and are safe to use among multiple threads. The one exception
 involves the <code>Flags</code> property. If the context's <code>HasFlags</code>
 property (a read-only property) is <code>true</code>, the <code>Flags</code>
 property is mutable, thus making the context mutable. This class
 doesn't synchronize access to such mutable contexts, so applications
 should provide their own synchronization if a context with the
 <code>HasFlags</code> property set to <code>true</code> will be shared among
 multiple threads and at least one of those threads needs to write the
 <code>Flags</code> property (which can happen, for example, by passing the
 context to most methods of <code>EDecimal</code> such as <code>Add</code>).</p>
