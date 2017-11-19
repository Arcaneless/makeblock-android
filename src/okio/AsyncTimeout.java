package okio;

import java.io.IOException;
import java.io.InterruptedIOException;

public class AsyncTimeout
  extends Timeout
{
  private static final int TIMEOUT_WRITE_SIZE = 65536;
  private static AsyncTimeout head;
  private boolean inQueue;
  private AsyncTimeout next;
  private long timeoutAt;
  
  static AsyncTimeout awaitTimeout()
    throws InterruptedException
  {
    label22:
    label28:
    label40:
    try
    {
      localAsyncTimeout = head.next;
    }
    finally {}
    AsyncTimeout.class.wait();
    AsyncTimeout localAsyncTimeout = null;
    label105:
    label117:
    for (;;)
    {
      return localAsyncTimeout;
      long l1 = localAsyncTimeout.remainingNanos(System.nanoTime());
      break label105;
      long l2 = l1 / 1000000L;
      for (;;)
      {
        AsyncTimeout.class.wait(l2, (int)(l1 - l2 * 1000000L));
        localAsyncTimeout = null;
        break label117;
        do
        {
          head.next = localAsyncTimeout.next;
          localAsyncTimeout.next = null;
          break label22;
          if (localObject != null) {
            break label28;
          }
          break;
        } while (l1 <= 0L);
        break label40;
      }
    }
  }
  
  private static boolean cancelScheduledTimeout(AsyncTimeout paramAsyncTimeout)
  {
    for (;;)
    {
      try
      {
        localAsyncTimeout = head;
      }
      finally {}
      if (localAsyncTimeout.next == paramAsyncTimeout)
      {
        localAsyncTimeout.next = paramAsyncTimeout.next;
        paramAsyncTimeout.next = null;
        bool = false;
        return bool;
      }
      AsyncTimeout localAsyncTimeout = localAsyncTimeout.next;
      break label57;
      boolean bool = true;
      continue;
      label57:
      if (localAsyncTimeout == null) {}
    }
  }
  
  private long remainingNanos(long paramLong)
  {
    return this.timeoutAt - paramLong;
  }
  
  private static void scheduleTimeout(AsyncTimeout paramAsyncTimeout, long paramLong, boolean paramBoolean)
  {
    for (;;)
    {
      long l;
      try
      {
        if (head == null)
        {
          head = new AsyncTimeout();
          new Watchdog().start();
        }
        l = System.nanoTime();
      }
      finally {}
      paramAsyncTimeout.timeoutAt = (Math.min(paramLong, paramAsyncTimeout.deadlineNanoTime() - l) + l);
      paramLong = paramAsyncTimeout.remainingNanos(l);
      AsyncTimeout localAsyncTimeout = head;
      if ((localAsyncTimeout.next == null) || (paramLong < localAsyncTimeout.next.remainingNanos(l)))
      {
        paramAsyncTimeout.next = localAsyncTimeout.next;
        localAsyncTimeout.next = paramAsyncTimeout;
        if (localAsyncTimeout == head) {
          AsyncTimeout.class.notify();
        }
        return;
        if (paramLong != 0L) {
          paramAsyncTimeout.timeoutAt = (l + paramLong);
        } else if (paramBoolean) {
          paramAsyncTimeout.timeoutAt = paramAsyncTimeout.deadlineNanoTime();
        } else {
          throw new AssertionError();
        }
      }
      else
      {
        localAsyncTimeout = localAsyncTimeout.next;
        continue;
        if ((paramLong == 0L) || (!paramBoolean)) {}
      }
    }
  }
  
  public final void enter()
  {
    if (this.inQueue) {
      throw new IllegalStateException("Unbalanced enter/exit");
    }
    long l = timeoutNanos();
    boolean bool = hasDeadline();
    if ((l == 0L) && (!bool)) {
      return;
    }
    this.inQueue = true;
    scheduleTimeout(this, l, bool);
  }
  
  final IOException exit(IOException paramIOException)
    throws IOException
  {
    if (!exit()) {
      return paramIOException;
    }
    return newTimeoutException(paramIOException);
  }
  
  final void exit(boolean paramBoolean)
    throws IOException
  {
    if ((exit()) && (paramBoolean)) {
      throw newTimeoutException(null);
    }
  }
  
  public final boolean exit()
  {
    if (!this.inQueue) {
      return false;
    }
    this.inQueue = false;
    return cancelScheduledTimeout(this);
  }
  
  protected IOException newTimeoutException(IOException paramIOException)
  {
    InterruptedIOException localInterruptedIOException = new InterruptedIOException("timeout");
    if (paramIOException != null) {
      localInterruptedIOException.initCause(paramIOException);
    }
    return localInterruptedIOException;
  }
  
  public final Sink sink(final Sink paramSink)
  {
    new Sink()
    {
      public void close()
        throws IOException
      {
        AsyncTimeout.this.enter();
        try
        {
          paramSink.close();
          AsyncTimeout.this.exit(true);
          return;
        }
        catch (IOException localIOException)
        {
          throw AsyncTimeout.this.exit(localIOException);
        }
        finally
        {
          AsyncTimeout.this.exit(false);
        }
      }
      
      public void flush()
        throws IOException
      {
        AsyncTimeout.this.enter();
        try
        {
          paramSink.flush();
          AsyncTimeout.this.exit(true);
          return;
        }
        catch (IOException localIOException)
        {
          throw AsyncTimeout.this.exit(localIOException);
        }
        finally
        {
          AsyncTimeout.this.exit(false);
        }
      }
      
      public Timeout timeout()
      {
        return AsyncTimeout.this;
      }
      
      public String toString()
      {
        return "AsyncTimeout.sink(" + paramSink + ")";
      }
      
      public void write(Buffer paramAnonymousBuffer, long paramAnonymousLong)
        throws IOException
      {
        Util.checkOffsetAndCount(paramAnonymousBuffer.size, 0L, paramAnonymousLong);
        if (paramAnonymousLong > 0L)
        {
          long l1 = 0L;
          for (Segment localSegment = paramAnonymousBuffer.head;; localSegment = localSegment.next)
          {
            long l2 = l1;
            if (l1 < 65536L)
            {
              l1 += paramAnonymousBuffer.head.limit - paramAnonymousBuffer.head.pos;
              if (l1 >= paramAnonymousLong) {
                l2 = paramAnonymousLong;
              }
            }
            else
            {
              AsyncTimeout.this.enter();
            }
            try
            {
              paramSink.write(paramAnonymousBuffer, l2);
              paramAnonymousLong -= l2;
              AsyncTimeout.this.exit(true);
              break;
            }
            catch (IOException paramAnonymousBuffer)
            {
              throw AsyncTimeout.this.exit(paramAnonymousBuffer);
            }
            finally
            {
              AsyncTimeout.this.exit(false);
            }
          }
        }
      }
    };
  }
  
  public final Source source(final Source paramSource)
  {
    new Source()
    {
      public void close()
        throws IOException
      {
        try
        {
          paramSource.close();
          AsyncTimeout.this.exit(true);
          return;
        }
        catch (IOException localIOException)
        {
          throw AsyncTimeout.this.exit(localIOException);
        }
        finally
        {
          AsyncTimeout.this.exit(false);
        }
      }
      
      public long read(Buffer paramAnonymousBuffer, long paramAnonymousLong)
        throws IOException
      {
        AsyncTimeout.this.enter();
        try
        {
          paramAnonymousLong = paramSource.read(paramAnonymousBuffer, paramAnonymousLong);
          AsyncTimeout.this.exit(true);
          return paramAnonymousLong;
        }
        catch (IOException paramAnonymousBuffer)
        {
          throw AsyncTimeout.this.exit(paramAnonymousBuffer);
        }
        finally
        {
          AsyncTimeout.this.exit(false);
        }
      }
      
      public Timeout timeout()
      {
        return AsyncTimeout.this;
      }
      
      public String toString()
      {
        return "AsyncTimeout.source(" + paramSource + ")";
      }
    };
  }
  
  protected void timedOut() {}
  
  private static final class Watchdog
    extends Thread
  {
    public Watchdog()
    {
      super();
      setDaemon(true);
    }
    
    public void run()
    {
      for (;;)
      {
        AsyncTimeout localAsyncTimeout;
        try
        {
          localAsyncTimeout = AsyncTimeout.awaitTimeout();
        }
        catch (InterruptedException localInterruptedException) {}
        localAsyncTimeout.timedOut();
        continue;
        continue;
        if (localInterruptedException == null) {}
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okio\AsyncTimeout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */