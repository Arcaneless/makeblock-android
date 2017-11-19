package com.google.android.gms.internal;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement.zzd;
import com.google.android.gms.measurement.AppMeasurement.zzf;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class zzatv
  extends zzats
{
  protected zza zzbul;
  private volatile AppMeasurement.zzf zzbum;
  private AppMeasurement.zzf zzbun;
  private long zzbuo;
  private final Map<Activity, zza> zzbup = new ArrayMap();
  private final CopyOnWriteArrayList<AppMeasurement.zzd> zzbuq = new CopyOnWriteArrayList();
  private boolean zzbur;
  private AppMeasurement.zzf zzbus;
  private String zzbut;
  
  public zzatv(zzatp paramzzatp)
  {
    super(paramzzatp);
  }
  
  @MainThread
  private void zza(final Activity paramActivity, zza paramzza, final boolean paramBoolean)
  {
    boolean bool2 = true;
    boolean bool1 = true;
    if (this.zzbum != null) {}
    label164:
    Object localObject;
    label290:
    for (AppMeasurement.zzf localzzf = this.zzbum;; localObject = null)
    {
      if (localzzf != null) {}
      for (localzzf = new AppMeasurement.zzf(localzzf);; localObject = null)
      {
        this.zzbur = true;
        try
        {
          Iterator localIterator = this.zzbuq.iterator();
          for (;;)
          {
            bool2 = bool1;
            if (!localIterator.hasNext()) {
              break label164;
            }
            bool2 = bool1;
            AppMeasurement.zzd localzzd = (AppMeasurement.zzd)localIterator.next();
            try
            {
              boolean bool3 = localzzd.zza(localzzf, paramzza);
              bool1 = bool3 & bool1;
            }
            catch (Exception localException2)
            {
              for (;;)
              {
                bool2 = bool1;
                zzJt().zzLa().zzj("onScreenChangeCallback threw exception", localException2);
              }
            }
          }
          if ((this.zzbun == null) || (Math.abs(zznq().elapsedRealtime() - this.zzbuo) >= 1000L)) {
            break label290;
          }
          localzzf = this.zzbun;
          break;
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            zzJt().zzLa().zzj("onScreenChangeCallback loop threw exception", localException1);
            this.zzbur = false;
            bool1 = bool2;
          }
        }
        finally
        {
          this.zzbur = false;
        }
        if (bool1)
        {
          if (paramzza.zzbpA == null) {
            paramzza.zzbpA = zzfV(paramActivity.getClass().getCanonicalName());
          }
          paramActivity = new zza(paramzza);
          this.zzbun = this.zzbum;
          this.zzbuo = zznq().elapsedRealtime();
          this.zzbum = paramActivity;
          zzJs().zzm(new Runnable()
          {
            public void run()
            {
              if ((paramBoolean) && (zzatv.this.zzbul != null)) {
                zzatv.zza(zzatv.this, zzatv.this.zzbul);
              }
              zzatv.this.zzbul = paramActivity;
              zzatv.this.zzJl().zza(paramActivity);
            }
          });
        }
        return;
      }
    }
  }
  
  @WorkerThread
  private void zza(@NonNull zza paramzza)
  {
    zzJg().zzV(zznq().elapsedRealtime());
    if (zzJr().zzaJ(paramzza.zzbuy)) {
      paramzza.zzbuy = false;
    }
  }
  
  public static void zza(AppMeasurement.zzf paramzzf, Bundle paramBundle)
  {
    if ((paramBundle != null) && (paramzzf != null) && (!paramBundle.containsKey("_sc")))
    {
      if (paramzzf.zzbpz != null) {
        paramBundle.putString("_sn", paramzzf.zzbpz);
      }
      paramBundle.putString("_sc", paramzzf.zzbpA);
      paramBundle.putLong("_si", paramzzf.zzbpB);
    }
  }
  
  static String zzfV(String paramString)
  {
    Object localObject = paramString.split("\\.");
    if (localObject.length == 0) {
      paramString = paramString.substring(0, 36);
    }
    do
    {
      return paramString;
      localObject = localObject[(localObject.length - 1)];
      paramString = (String)localObject;
    } while (((String)localObject).length() <= 36);
    return ((String)localObject).substring(0, 36);
  }
  
  @MainThread
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
    if (paramBundle == null) {}
    do
    {
      return;
      paramBundle = paramBundle.getBundle("com.google.firebase.analytics.screen_service");
    } while (paramBundle == null);
    paramActivity = zzv(paramActivity);
    paramActivity.zzbpB = paramBundle.getLong("id");
    paramActivity.zzbpz = paramBundle.getString("name");
    paramActivity.zzbpA = paramBundle.getString("referrer_name");
  }
  
  @MainThread
  public void onActivityDestroyed(Activity paramActivity)
  {
    this.zzbup.remove(paramActivity);
  }
  
  @MainThread
  public void onActivityPaused(final Activity paramActivity)
  {
    paramActivity = zzv(paramActivity);
    this.zzbun = this.zzbum;
    this.zzbuo = zznq().elapsedRealtime();
    this.zzbum = null;
    zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzatv.zza(zzatv.this, paramActivity);
        zzatv.this.zzbul = null;
        zzatv.this.zzJl().zza(null);
      }
    });
  }
  
  @MainThread
  public void onActivityResumed(Activity paramActivity)
  {
    zza(paramActivity, zzv(paramActivity), false);
    zzJg().zzJc();
  }
  
  @MainThread
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle)
  {
    if (paramBundle == null) {}
    do
    {
      return;
      paramActivity = (zza)this.zzbup.get(paramActivity);
    } while (paramActivity == null);
    Bundle localBundle = new Bundle();
    localBundle.putLong("id", paramActivity.zzbpB);
    localBundle.putString("name", paramActivity.zzbpz);
    localBundle.putString("referrer_name", paramActivity.zzbpA);
    paramBundle.putBundle("com.google.firebase.analytics.screen_service", localBundle);
  }
  
  @MainThread
  public void registerOnScreenChangeCallback(@NonNull AppMeasurement.zzd paramzzd)
  {
    zzJe();
    if (paramzzd == null)
    {
      zzJt().zzLc().log("Attempting to register null OnScreenChangeCallback");
      return;
    }
    this.zzbuq.remove(paramzzd);
    this.zzbuq.add(paramzzd);
  }
  
  @MainThread
  public void setCurrentScreen(@NonNull Activity paramActivity, @Nullable @Size(max=36L, min=1L) String paramString1, @Nullable @Size(max=36L, min=1L) String paramString2)
  {
    if (Build.VERSION.SDK_INT < 14)
    {
      zzJt().zzLd().log("Screen engagement recording is only available at API level 14+");
      return;
    }
    if (paramActivity == null)
    {
      zzJt().zzLc().log("setCurrentScreen must be called with a non-null activity");
      return;
    }
    if (!zzJs().zzbd())
    {
      zzJt().zzLc().log("setCurrentScreen must be called from the main thread");
      return;
    }
    if (this.zzbur)
    {
      zzJt().zzLc().log("Cannot call setCurrentScreen from onScreenChangeCallback");
      return;
    }
    if (this.zzbum == null)
    {
      zzJt().zzLc().log("setCurrentScreen cannot be called while no activity active");
      return;
    }
    if (this.zzbup.get(paramActivity) == null)
    {
      zzJt().zzLc().log("setCurrentScreen must be called with an activity in the activity lifecycle");
      return;
    }
    String str = paramString2;
    if (paramString2 == null) {
      str = zzfV(paramActivity.getClass().getCanonicalName());
    }
    boolean bool = this.zzbum.zzbpA.equals(str);
    if (((this.zzbum.zzbpz == null) && (paramString1 == null)) || ((this.zzbum.zzbpz != null) && (this.zzbum.zzbpz.equals(paramString1)))) {}
    for (int i = 1; (bool) && (i != 0); i = 0)
    {
      zzJt().zzLd().log("setCurrentScreen cannot be called with the same class and name");
      return;
    }
    if ((paramString1 != null) && ((paramString1.length() < 1) || (paramString1.length() > zzJv().zzJX())))
    {
      zzJt().zzLc().zzj("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(paramString1.length()));
      return;
    }
    if ((str != null) && ((str.length() < 1) || (str.length() > zzJv().zzJX())))
    {
      zzJt().zzLc().zzj("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
      return;
    }
    zzati.zza localzza = zzJt().zzLg();
    if (paramString1 == null) {}
    for (paramString2 = "null";; paramString2 = paramString1)
    {
      localzza.zze("Setting current screen to name, class", paramString2, str);
      paramString1 = new zza(paramString1, str, zzJp().zzMi());
      this.zzbup.put(paramActivity, paramString1);
      zza(paramActivity, paramString1, true);
      return;
    }
  }
  
  @MainThread
  public void unregisterOnScreenChangeCallback(@NonNull AppMeasurement.zzd paramzzd)
  {
    zzJe();
    this.zzbuq.remove(paramzzd);
  }
  
  @WorkerThread
  public zza zzLU()
  {
    zznA();
    zzmq();
    return this.zzbul;
  }
  
  public AppMeasurement.zzf zzLV()
  {
    zzJe();
    AppMeasurement.zzf localzzf = this.zzbum;
    if (localzzf == null) {
      return null;
    }
    return new AppMeasurement.zzf(localzzf);
  }
  
  @WorkerThread
  public void zza(String paramString, AppMeasurement.zzf paramzzf)
  {
    zzmq();
    try
    {
      if ((this.zzbut == null) || (this.zzbut.equals(paramString)) || (paramzzf != null))
      {
        this.zzbut = paramString;
        this.zzbus = paramzzf;
      }
      return;
    }
    finally {}
  }
  
  public AppMeasurement.zzf zzfU(String paramString)
  {
    try
    {
      if ((this.zzbus == null) || (this.zzbut == null) || (!this.zzbut.equals(paramString))) {
        return null;
      }
      paramString = this.zzbus;
      return paramString;
    }
    finally {}
  }
  
  protected void zzmr() {}
  
  @MainThread
  zza zzv(@NonNull Activity paramActivity)
  {
    zzac.zzw(paramActivity);
    zza localzza2 = (zza)this.zzbup.get(paramActivity);
    zza localzza1 = localzza2;
    if (localzza2 == null)
    {
      localzza1 = new zza(null, zzfV(paramActivity.getClass().getCanonicalName()), zzJp().zzMi());
      this.zzbup.put(paramActivity, localzza1);
    }
    return localzza1;
  }
  
  static class zza
    extends AppMeasurement.zzf
  {
    public boolean zzbuy;
    
    public zza(zza paramzza)
    {
      this.zzbpz = paramzza.zzbpz;
      this.zzbpA = paramzza.zzbpA;
      this.zzbpB = paramzza.zzbpB;
      this.zzbuy = paramzza.zzbuy;
    }
    
    public zza(String paramString1, String paramString2, long paramLong)
    {
      this.zzbpz = paramString1;
      this.zzbpA = paramString2;
      this.zzbpB = paramLong;
      this.zzbuy = false;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */