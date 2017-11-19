package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.measurement.AppMeasurement;

public final class zzatm
{
  private final zza zzbsC;
  
  public zzatm(zza paramzza)
  {
    zzac.zzw(paramzza);
    this.zzbsC = paramzza;
  }
  
  public static boolean zzi(Context paramContext, boolean paramBoolean)
  {
    zzac.zzw(paramContext);
    if (paramBoolean) {}
    for (String str = "com.google.android.gms.measurement.PackageMeasurementReceiver";; str = "com.google.android.gms.measurement.AppMeasurementReceiver") {
      return zzaue.zza(paramContext, str, false);
    }
  }
  
  @MainThread
  public void onReceive(final Context paramContext, Intent paramIntent)
  {
    final zzatp localzzatp = zzatp.zzbu(paramContext);
    final zzati localzzati = localzzatp.zzJt();
    if (paramIntent == null) {
      localzzati.zzLc().log("Receiver called with null intent");
    }
    do
    {
      return;
      localzzatp.zzJv().zzKk();
      localObject = paramIntent.getAction();
      localzzati.zzLg().zzj("Local receiver got", localObject);
      if ("com.google.android.gms.measurement.UPLOAD".equals(localObject))
      {
        zzatx.zzj(paramContext, false);
        paramIntent = new Intent().setClassName(paramContext, "com.google.android.gms.measurement.AppMeasurementService");
        paramIntent.setAction("com.google.android.gms.measurement.UPLOAD");
        this.zzbsC.doStartService(paramContext, paramIntent);
        return;
      }
    } while (!"com.android.vending.INSTALL_REFERRER".equals(localObject));
    Object localObject = paramIntent.getStringExtra("referrer");
    if (localObject == null)
    {
      localzzati.zzLg().log("Install referrer extras are null");
      return;
    }
    localObject = Uri.parse((String)localObject);
    localObject = localzzatp.zzJp().zzu((Uri)localObject);
    if (localObject == null)
    {
      localzzati.zzLg().log("No campaign defined in install referrer broadcast");
      return;
    }
    final long l = 1000L * paramIntent.getLongExtra("referrer_timestamp_seconds", 0L);
    if (l == 0L) {
      localzzati.zzLc().log("Install referrer is missing timestamp");
    }
    localzzatp.zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzaud localzzaud = localzzatp.zzJo().zzR(localzzatp.zzJj().zzjI(), "_fot");
        if ((localzzaud != null) && ((localzzaud.zzYe instanceof Long))) {}
        for (long l1 = ((Long)localzzaud.zzYe).longValue();; l1 = 0L)
        {
          long l2 = l;
          if ((l1 > 0L) && ((l2 >= l1) || (l2 <= 0L))) {}
          for (l1 -= 1L;; l1 = l2)
          {
            if (l1 > 0L) {
              paramContext.putLong("click_timestamp", l1);
            }
            AppMeasurement.getInstance(localzzati).logEventInternal("auto", "_cmp", paramContext);
            this.zzbsG.zzLg().log("Install campaign recorded");
            return;
          }
        }
      }
    });
  }
  
  public static abstract interface zza
  {
    public abstract void doStartService(Context paramContext, Intent paramIntent);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */