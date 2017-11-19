package android.arch.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

public class LifecycleService
  extends Service
  implements LifecycleOwner
{
  private final ServiceLifecycleDispatcher mDispatcher = new ServiceLifecycleDispatcher(this);
  
  public Lifecycle getLifecycle()
  {
    return this.mDispatcher.getLifecycle();
  }
  
  @CallSuper
  @Nullable
  public IBinder onBind(Intent paramIntent)
  {
    this.mDispatcher.onServicePreSuperOnBind();
    return null;
  }
  
  @CallSuper
  public void onCreate()
  {
    this.mDispatcher.onServicePreSuperOnCreate();
    super.onCreate();
  }
  
  @CallSuper
  public void onDestroy()
  {
    this.mDispatcher.onServicePreSuperOnDestroy();
    super.onDestroy();
  }
  
  @CallSuper
  public void onStart(Intent paramIntent, int paramInt)
  {
    this.mDispatcher.onServicePreSuperOnStart();
    super.onStart(paramIntent, paramInt);
  }
  
  @CallSuper
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\lifecycle\LifecycleService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */