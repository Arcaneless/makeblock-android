package rx.android.schedulers;

import android.os.Handler;

@Deprecated
public final class HandlerScheduler
  extends LooperScheduler
{
  private HandlerScheduler(Handler paramHandler)
  {
    super(paramHandler);
  }
  
  @Deprecated
  public static HandlerScheduler from(Handler paramHandler)
  {
    if (paramHandler == null) {
      throw new NullPointerException("handler == null");
    }
    return new HandlerScheduler(paramHandler);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\android\schedulers\HandlerScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */