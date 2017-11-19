package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.zzac;

public final class zzatx
{
  private final Context mContext;
  private final Handler mHandler;
  private final zza zzbuQ;
  
  public zzatx(zza paramzza)
  {
    this.mContext = paramzza.getContext();
    zzac.zzw(this.mContext);
    this.zzbuQ = paramzza;
    this.mHandler = new Handler();
  }
  
  private zzati zzJt()
  {
    return zzatp.zzbu(this.mContext).zzJt();
  }
  
  public static boolean zzj(Context paramContext, boolean paramBoolean)
  {
    zzac.zzw(paramContext);
    if (paramBoolean) {}
    for (String str = "com.google.android.gms.measurement.PackageMeasurementService";; str = "com.google.android.gms.measurement.AppMeasurementService") {
      return zzaue.zzr(paramContext, str);
    }
  }
  
  @MainThread
  public IBinder onBind(Intent paramIntent)
  {
    if (paramIntent == null)
    {
      zzJt().zzLa().log("onBind called with null intent");
      return null;
    }
    paramIntent = paramIntent.getAction();
    if ("com.google.android.gms.measurement.START".equals(paramIntent)) {
      return new zzatq(zzatp.zzbu(this.mContext));
    }
    zzJt().zzLc().zzj("onBind received unknown action", paramIntent);
    return null;
  }
  
  @MainThread
  public void onCreate()
  {
    zzatp localzzatp = zzatp.zzbu(this.mContext);
    zzati localzzati = localzzatp.zzJt();
    localzzatp.zzJv().zzKk();
    localzzati.zzLg().log("Local AppMeasurementService is starting up");
  }
  
  @MainThread
  public void onDestroy()
  {
    zzatp localzzatp = zzatp.zzbu(this.mContext);
    zzati localzzati = localzzatp.zzJt();
    localzzatp.zzJv().zzKk();
    localzzati.zzLg().log("Local AppMeasurementService is shutting down");
  }
  
  @MainThread
  public void onRebind(Intent paramIntent)
  {
    if (paramIntent == null)
    {
      zzJt().zzLa().log("onRebind called with null intent");
      return;
    }
    paramIntent = paramIntent.getAction();
    zzJt().zzLg().zzj("onRebind called. action", paramIntent);
  }
  
  @MainThread
  public int onStartCommand(Intent paramIntent, int paramInt1, final int paramInt2)
  {
    final zzatp localzzatp = zzatp.zzbu(this.mContext);
    final zzati localzzati = localzzatp.zzJt();
    if (paramIntent == null) {
      localzzati.zzLc().log("AppMeasurementService started with null intent");
    }
    do
    {
      return 2;
      paramIntent = paramIntent.getAction();
      localzzatp.zzJv().zzKk();
      localzzati.zzLg().zze("Local AppMeasurementService called. startId, action", Integer.valueOf(paramInt2), paramIntent);
    } while (!"com.google.android.gms.measurement.UPLOAD".equals(paramIntent));
    localzzatp.zzJs().zzm(new Runnable()
    {
      public void run()
      {
        localzzatp.zzLL();
        localzzatp.zzLG();
        zzatx.zzb(zzatx.this).post(new Runnable()
        {
          public void run()
          {
            if (zzatx.zza(zzatx.this).callServiceStopSelfResult(zzatx.1.this.zzaaz))
            {
              zzatx.1.this.zzbsD.zzJv().zzKk();
              zzatx.1.this.zzbsG.zzLg().log("Local AppMeasurementService processed last upload request");
            }
          }
        });
      }
    });
    return 2;
  }
  
  @MainThread
  public boolean onUnbind(Intent paramIntent)
  {
    if (paramIntent == null)
    {
      zzJt().zzLa().log("onUnbind called with null intent");
      return true;
    }
    paramIntent = paramIntent.getAction();
    zzJt().zzLg().zzj("onUnbind called for intent. action", paramIntent);
    return true;
  }
  
  public static abstract interface zza
  {
    public abstract boolean callServiceStopSelfResult(int paramInt);
    
    public abstract Context getContext();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */