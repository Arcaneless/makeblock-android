package rx.android.schedulers;

import android.os.Looper;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.annotations.Experimental;

public final class AndroidSchedulers
{
  private static final AtomicReference<AndroidSchedulers> INSTANCE = new AtomicReference();
  private final Scheduler mainThreadScheduler;
  
  private AndroidSchedulers()
  {
    Scheduler localScheduler = RxAndroidPlugins.getInstance().getSchedulersHook().getMainThreadScheduler();
    if (localScheduler != null)
    {
      this.mainThreadScheduler = localScheduler;
      return;
    }
    this.mainThreadScheduler = new LooperScheduler(Looper.getMainLooper());
  }
  
  public static Scheduler from(Looper paramLooper)
  {
    if (paramLooper == null) {
      throw new NullPointerException("looper == null");
    }
    return new LooperScheduler(paramLooper);
  }
  
  private static AndroidSchedulers getInstance()
  {
    AndroidSchedulers localAndroidSchedulers;
    do
    {
      localAndroidSchedulers = (AndroidSchedulers)INSTANCE.get();
      if (localAndroidSchedulers != null) {
        return localAndroidSchedulers;
      }
      localAndroidSchedulers = new AndroidSchedulers();
    } while (!INSTANCE.compareAndSet(null, localAndroidSchedulers));
    return localAndroidSchedulers;
  }
  
  public static Scheduler mainThread()
  {
    return getInstance().mainThreadScheduler;
  }
  
  @Experimental
  public static void reset()
  {
    INSTANCE.set(null);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\android\schedulers\AndroidSchedulers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */