package com.google.android.gms.measurement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.internal.zzatm;
import com.google.android.gms.internal.zzatm.zza;

public final class AppMeasurementReceiver
  extends WakefulBroadcastReceiver
  implements zzatm.zza
{
  private zzatm zzbpD;
  
  private zzatm zzJa()
  {
    if (this.zzbpD == null) {
      this.zzbpD = new zzatm(this);
    }
    return this.zzbpD;
  }
  
  @MainThread
  public void doStartService(Context paramContext, Intent paramIntent)
  {
    startWakefulService(paramContext, paramIntent);
  }
  
  @MainThread
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    zzJa().onReceive(paramContext, paramIntent);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\measurement\AppMeasurementReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */