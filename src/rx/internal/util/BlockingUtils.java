package rx.internal.util;

import java.util.concurrent.CountDownLatch;
import rx.Subscription;
import rx.annotations.Experimental;

@Experimental
public final class BlockingUtils
{
  @Experimental
  public static void awaitForComplete(CountDownLatch paramCountDownLatch, Subscription paramSubscription)
  {
    if (paramCountDownLatch.getCount() == 0L) {
      return;
    }
    try
    {
      paramCountDownLatch.await();
      return;
    }
    catch (InterruptedException paramCountDownLatch)
    {
      paramSubscription.unsubscribe();
      Thread.currentThread().interrupt();
      throw new RuntimeException("Interrupted while waiting for subscription to complete.", paramCountDownLatch);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\BlockingUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */