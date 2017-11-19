package com.google.android.gms.measurement;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.internal.zzatx;
import com.google.android.gms.internal.zzatx.zza;

public final class AppMeasurementService
  extends Service
  implements zzatx.zza
{
  private zzatx zzbpE;
  
  private zzatx zzJb()
  {
    if (this.zzbpE == null) {
      this.zzbpE = new zzatx(this);
    }
    return this.zzbpE;
  }
  
  public boolean callServiceStopSelfResult(int paramInt)
  {
    return stopSelfResult(paramInt);
  }
  
  public Context getContext()
  {
    return this;
  }
  
  @MainThread
  public IBinder onBind(Intent paramIntent)
  {
    return zzJb().onBind(paramIntent);
  }
  
  @MainThread
  public void onCreate()
  {
    super.onCreate();
    zzJb().onCreate();
  }
  
  @MainThread
  public void onDestroy()
  {
    zzJb().onDestroy();
    super.onDestroy();
  }
  
  @MainThread
  public void onRebind(Intent paramIntent)
  {
    zzJb().onRebind(paramIntent);
  }
  
  @MainThread
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    zzJb().onStartCommand(paramIntent, paramInt1, paramInt2);
    AppMeasurementReceiver.completeWakefulIntent(paramIntent);
    return 2;
  }
  
  @MainThread
  public boolean onUnbind(Intent paramIntent)
  {
    return zzJb().onUnbind(paramIntent);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\measurement\AppMeasurementService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */