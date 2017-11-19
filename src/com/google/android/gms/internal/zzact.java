package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public class zzact
  implements Executor
{
  private final Handler mHandler;
  
  public zzact(Looper paramLooper)
  {
    this.mHandler = new Handler(paramLooper);
  }
  
  public void execute(@NonNull Runnable paramRunnable)
  {
    this.mHandler.post(paramRunnable);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzact.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */