package rx.internal.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class RxThreadFactory
  extends AtomicLong
  implements ThreadFactory
{
  public static final ThreadFactory NONE = new ThreadFactory()
  {
    public Thread newThread(Runnable paramAnonymousRunnable)
    {
      throw new AssertionError("No threads allowed.");
    }
  };
  private static final long serialVersionUID = -8841098858898482335L;
  final String prefix;
  
  public RxThreadFactory(String paramString)
  {
    this.prefix = paramString;
  }
  
  public Thread newThread(Runnable paramRunnable)
  {
    paramRunnable = new Thread(paramRunnable, this.prefix + incrementAndGet());
    paramRunnable.setDaemon(true);
    return paramRunnable;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\RxThreadFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */