package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class Timeout
{
  public static final Timeout NONE = new Timeout()
  {
    public Timeout deadlineNanoTime(long paramAnonymousLong)
    {
      return this;
    }
    
    public void throwIfReached()
      throws IOException
    {}
    
    public Timeout timeout(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
    {
      return this;
    }
  };
  private long deadlineNanoTime;
  private boolean hasDeadline;
  private long timeoutNanos;
  
  public Timeout clearDeadline()
  {
    this.hasDeadline = false;
    return this;
  }
  
  public Timeout clearTimeout()
  {
    this.timeoutNanos = 0L;
    return this;
  }
  
  public final Timeout deadline(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong <= 0L) {
      throw new IllegalArgumentException("duration <= 0: " + paramLong);
    }
    if (paramTimeUnit == null) {
      throw new IllegalArgumentException("unit == null");
    }
    return deadlineNanoTime(System.nanoTime() + paramTimeUnit.toNanos(paramLong));
  }
  
  public long deadlineNanoTime()
  {
    if (!this.hasDeadline) {
      throw new IllegalStateException("No deadline");
    }
    return this.deadlineNanoTime;
  }
  
  public Timeout deadlineNanoTime(long paramLong)
  {
    this.hasDeadline = true;
    this.deadlineNanoTime = paramLong;
    return this;
  }
  
  public boolean hasDeadline()
  {
    return this.hasDeadline;
  }
  
  public void throwIfReached()
    throws IOException
  {
    if (Thread.interrupted()) {
      throw new InterruptedIOException("thread interrupted");
    }
    if ((this.hasDeadline) && (this.deadlineNanoTime - System.nanoTime() <= 0L)) {
      throw new InterruptedIOException("deadline reached");
    }
  }
  
  public Timeout timeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("timeout < 0: " + paramLong);
    }
    if (paramTimeUnit == null) {
      throw new IllegalArgumentException("unit == null");
    }
    this.timeoutNanos = paramTimeUnit.toNanos(paramLong);
    return this;
  }
  
  public long timeoutNanos()
  {
    return this.timeoutNanos;
  }
  
  public final void waitUntilNotified(Object paramObject)
    throws InterruptedIOException
  {
    boolean bool;
    try
    {
      bool = hasDeadline();
      l1 = timeoutNanos();
    }
    catch (InterruptedException paramObject)
    {
      throw new InterruptedIOException("interrupted");
    }
    paramObject.wait();
    return;
    label18:
    long l3 = System.nanoTime();
    break label129;
    label26:
    long l1 = Math.min(l1, deadlineNanoTime() - l3);
    break label142;
    label41:
    long l2 = l1 / 1000000L;
    paramObject.wait(l2, (int)(l1 - 1000000L * l2));
    l2 = System.nanoTime() - l3;
    for (;;)
    {
      throw new InterruptedIOException("timeout");
      label129:
      label142:
      do
      {
        do
        {
          if (bool)
          {
            l1 = deadlineNanoTime();
            l1 -= l3;
            break label142;
          }
          break label142;
          if ((bool) || (l1 != 0L)) {
            break label18;
          }
          break;
          return;
        } while ((!bool) || (l1 == 0L));
        break label26;
        l2 = 0L;
        if (l1 > 0L) {
          break label41;
        }
      } while (l2 < l1);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okio\Timeout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */