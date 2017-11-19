package com.google.android.gms.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.util.zze;

public class zzaua
  extends zzats
{
  private boolean zzaeg;
  private final AlarmManager zzaeh = (AlarmManager)getContext().getSystemService("alarm");
  private final zzasv zzbuX;
  
  protected zzaua(zzatp paramzzatp)
  {
    super(paramzzatp);
    this.zzbuX = new zzasv(paramzzatp)
    {
      public void run()
      {
        zzaua.zza(zzaua.this);
      }
    };
  }
  
  private void zzMh()
  {
    Intent localIntent = new Intent();
    Context localContext = getContext();
    zzJv().zzKk();
    localIntent = localIntent.setClassName(localContext, "com.google.android.gms.measurement.AppMeasurementReceiver");
    localIntent.setAction("com.google.android.gms.measurement.UPLOAD");
    getContext().sendBroadcast(localIntent);
  }
  
  private PendingIntent zzpe()
  {
    Intent localIntent = new Intent();
    Context localContext = getContext();
    zzJv().zzKk();
    localIntent = localIntent.setClassName(localContext, "com.google.android.gms.measurement.AppMeasurementReceiver");
    localIntent.setAction("com.google.android.gms.measurement.UPLOAD");
    return PendingIntent.getBroadcast(getContext(), 0, localIntent, 0);
  }
  
  public void cancel()
  {
    zznA();
    this.zzaeg = false;
    this.zzaeh.cancel(zzpe());
    this.zzbuX.cancel();
  }
  
  protected void zzmr()
  {
    this.zzaeh.cancel(zzpe());
  }
  
  public void zzx(long paramLong)
  {
    zznA();
    zzJv().zzKk();
    if (!zzatm.zzi(getContext(), false)) {
      zzJt().zzLf().log("Receiver not registered/enabled");
    }
    zzJv().zzKk();
    if (!zzatx.zzj(getContext(), false)) {
      zzJt().zzLf().log("Service not registered/enabled");
    }
    cancel();
    long l = zznq().elapsedRealtime();
    this.zzaeg = true;
    if ((paramLong < zzJv().zzKA()) && (!this.zzbuX.zzcv())) {
      this.zzbuX.zzx(paramLong);
    }
    this.zzaeh.setInexactRepeating(2, l + paramLong, Math.max(zzJv().zzKB(), paramLong), zzpe());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzaua.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */