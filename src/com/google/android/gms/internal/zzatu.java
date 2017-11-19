package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.measurement.AppMeasurement.zzb;
import com.google.android.gms.measurement.AppMeasurement.zzc;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public class zzatu
  extends zzats
{
  protected zza zzbtU;
  private AppMeasurement.zzb zzbtV;
  private final Set<AppMeasurement.zzc> zzbtW = new CopyOnWriteArraySet();
  private boolean zzbtX;
  private String zzbtY = null;
  private String zzbtZ = null;
  
  protected zzatu(zzatp paramzzatp)
  {
    super(paramzzatp);
  }
  
  @WorkerThread
  private void zzLS()
  {
    try
    {
      zzf(Class.forName(zzLT()));
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      zzJt().zzLe().log("Tag Manager is not found and thus will not be used");
    }
  }
  
  private String zzLT()
  {
    return "com.google.android.gms.tagmanager.TagManagerService";
  }
  
  private void zza(String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString3)
  {
    zza(paramString1, paramString2, zznq().currentTimeMillis(), paramBundle, paramBoolean1, paramBoolean2, paramBoolean3, paramString3);
  }
  
  @WorkerThread
  private void zza(String paramString1, String paramString2, Object paramObject, long paramLong)
  {
    zzac.zzdv(paramString1);
    zzac.zzdv(paramString2);
    zzmq();
    zzJe();
    zznA();
    if (!this.zzbpw.isEnabled()) {
      zzJt().zzLf().log("User property not set since app measurement is disabled");
    }
    while (!this.zzbpw.zzLt()) {
      return;
    }
    zzJt().zzLf().zze("Setting user property (FE)", paramString2, paramObject);
    paramString1 = new zzaub(paramString2, paramLong, paramObject, paramString1);
    zzJl().zzb(paramString1);
  }
  
  @WorkerThread
  private void zzaH(boolean paramBoolean)
  {
    zzmq();
    zzJe();
    zznA();
    zzJt().zzLf().zzj("Setting app measurement enabled (FE)", Boolean.valueOf(paramBoolean));
    zzJu().setMeasurementEnabled(paramBoolean);
    zzJl().zzLW();
  }
  
  @WorkerThread
  private void zzb(String paramString1, String paramString2, long paramLong, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString3)
  {
    zzac.zzdv(paramString1);
    zzac.zzdv(paramString2);
    zzac.zzw(paramBundle);
    zzmq();
    zznA();
    if (!this.zzbpw.isEnabled()) {
      zzJt().zzLf().log("Event not sent since app measurement is disabled");
    }
    boolean bool1;
    do
    {
      return;
      if (!this.zzbtX)
      {
        this.zzbtX = true;
        zzLS();
      }
      bool1 = "am".equals(paramString1);
      boolean bool2 = zzaue.zzgg(paramString2);
      if ((paramBoolean1) && (this.zzbtV != null) && (!bool2) && (!bool1))
      {
        zzJt().zzLf().zze("Passing event to registered event handler (FE)", paramString2, paramBundle);
        this.zzbtV.zzb(paramString1, paramString2, paramBundle, paramLong);
        return;
      }
    } while (!this.zzbpw.zzLt());
    int j = zzJp().zzfY(paramString2);
    if (j != 0)
    {
      paramString1 = zzJp().zza(paramString2, zzJv().zzJU(), true);
      if (paramString2 != null) {}
      for (int i = paramString2.length();; i = 0)
      {
        this.zzbpw.zzJp().zza(j, "_ev", paramString1, i);
        return;
      }
    }
    paramBundle.putString("_o", paramString1);
    Object localObject = zzf.zzx("_o");
    localObject = zzJp().zza(paramString2, paramBundle, (List)localObject, paramBoolean3);
    if (!paramBundle.containsKey("_sc"))
    {
      zzJv().zzKk();
      paramBundle = zzJm().zzLU();
      if (paramBundle != null) {
        paramBundle.zzbuy = true;
      }
      zzatv.zza(paramBundle, (Bundle)localObject);
    }
    if (paramBoolean2) {}
    for (paramBundle = zzN((Bundle)localObject);; paramBundle = (Bundle)localObject)
    {
      zzJt().zzLf().zze("Logging event (FE)", paramString2, paramBundle);
      localObject = new zzatb(paramString2, new zzasz(paramBundle), paramString1, paramLong);
      zzJl().zzc((zzatb)localObject, paramString3);
      if (bool1) {
        break;
      }
      paramString3 = this.zzbtW.iterator();
      while (paramString3.hasNext()) {
        ((AppMeasurement.zzc)paramString3.next()).zzc(paramString1, paramString2, new Bundle(paramBundle), paramLong);
      }
      break;
    }
  }
  
  @Nullable
  public String getAppInstanceIdOnPackageSide(String paramString)
  {
    zzJd();
    return this.zzbpw.zzfR(paramString);
  }
  
  @Nullable
  public String getGmpAppIdOnPackageSide(String paramString)
  {
    zzJd();
    return this.zzbpw.getGmpAppIdOnPackageSide(paramString);
  }
  
  public void setMeasurementEnabled(final boolean paramBoolean)
  {
    zznA();
    zzJe();
    zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzatu.zza(zzatu.this, paramBoolean);
      }
    });
  }
  
  public void setMinimumSessionDuration(final long paramLong)
  {
    zzJe();
    zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzatu.this.zzJu().zzbsp.set(paramLong);
        zzatu.this.zzJt().zzLf().zzj("Minimum session duration set", Long.valueOf(paramLong));
      }
    });
  }
  
  public void setSessionTimeoutDuration(final long paramLong)
  {
    zzJe();
    zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzatu.this.zzJu().zzbsq.set(paramLong);
        zzatu.this.zzJt().zzLf().zzj("Session timeout duration set", Long.valueOf(paramLong));
      }
    });
  }
  
  @TargetApi(14)
  public void zzLQ()
  {
    if ((getContext().getApplicationContext() instanceof Application))
    {
      Application localApplication = (Application)getContext().getApplicationContext();
      if (this.zzbtU == null) {
        this.zzbtU = new zza(null);
      }
      localApplication.unregisterActivityLifecycleCallbacks(this.zzbtU);
      localApplication.registerActivityLifecycleCallbacks(this.zzbtU);
      zzJt().zzLg().log("Registered activity lifecycle callback");
    }
  }
  
  @WorkerThread
  public void zzLR()
  {
    zzmq();
    zzJe();
    zznA();
    if (!this.zzbpw.zzLt()) {}
    String str;
    do
    {
      return;
      zzJl().zzLR();
      str = zzJu().zzLp();
    } while ((TextUtils.isEmpty(str)) || (str.equals(zzJk().zzKU())));
    Bundle localBundle = new Bundle();
    localBundle.putString("_po", str);
    zze("auto", "_ou", localBundle);
  }
  
  Bundle zzN(Bundle paramBundle)
  {
    Bundle localBundle = new Bundle();
    if (paramBundle != null)
    {
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = zzJp().zzl(str, paramBundle.get(str));
        if (localObject == null) {
          zzJt().zzLc().zzj("Param value can't be null", str);
        } else {
          zzJp().zza(localBundle, str, localObject);
        }
      }
    }
    return localBundle;
  }
  
  @WorkerThread
  public void zza(AppMeasurement.zzb paramzzb)
  {
    zzmq();
    zzJe();
    zznA();
    if ((paramzzb != null) && (paramzzb != this.zzbtV)) {
      if (this.zzbtV != null) {
        break label46;
      }
    }
    label46:
    for (boolean bool = true;; bool = false)
    {
      zzac.zza(bool, "EventInterceptor already set.");
      this.zzbtV = paramzzb;
      return;
    }
  }
  
  public void zza(AppMeasurement.zzc paramzzc)
  {
    zzJe();
    zznA();
    zzac.zzw(paramzzc);
    if (!this.zzbtW.add(paramzzc)) {
      zzJt().zzLc().log("OnEventListener already registered");
    }
  }
  
  protected void zza(final String paramString1, final String paramString2, final long paramLong, Bundle paramBundle, final boolean paramBoolean1, final boolean paramBoolean2, final boolean paramBoolean3, final String paramString3)
  {
    if (paramBundle != null) {}
    for (paramBundle = new Bundle(paramBundle);; paramBundle = new Bundle())
    {
      zzJs().zzm(new Runnable()
      {
        public void run()
        {
          zzatu.zza(zzatu.this, paramString1, paramString2, paramLong, paramBoolean1, paramBoolean2, paramBoolean3, paramString3, this.zzbky);
        }
      });
      return;
    }
  }
  
  void zza(final String paramString1, final String paramString2, final long paramLong, final Object paramObject)
  {
    zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzatu.zza(zzatu.this, paramString1, paramString2, paramObject, paramLong);
      }
    });
  }
  
  public void zza(String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean)
  {
    zzJe();
    if ((this.zzbtV == null) || (zzaue.zzgg(paramString2))) {}
    for (boolean bool = true;; bool = false)
    {
      zza(paramString1, paramString2, paramBundle, true, bool, paramBoolean, null);
      return;
    }
  }
  
  public List<zzaub> zzaI(final boolean paramBoolean)
  {
    zzJe();
    zznA();
    zzJt().zzLf().log("Fetching user attributes (FE)");
    synchronized (new AtomicReference())
    {
      this.zzbpw.zzJs().zzm(new Runnable()
      {
        public void run()
        {
          zzatu.this.zzJl().zza(localObject1, paramBoolean);
        }
      });
      try
      {
        ???.wait(5000L);
        List localList = (List)((AtomicReference)???).get();
        ??? = localList;
        if (localList == null)
        {
          zzJt().zzLc().log("Timed out waiting for get user properties");
          ??? = Collections.emptyList();
        }
        return (List<zzaub>)???;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          zzJt().zzLc().zzj("Interrupted waiting for get user properties", localInterruptedException);
        }
      }
    }
  }
  
  public void zzd(String paramString1, String paramString2, Bundle paramBundle, long paramLong)
  {
    zzJe();
    zza(paramString1, paramString2, paramLong, paramBundle, false, true, true, null);
  }
  
  public void zzd(String paramString1, String paramString2, Object paramObject)
  {
    int i = 0;
    int j = 0;
    zzac.zzdv(paramString1);
    long l = zznq().currentTimeMillis();
    int k = zzJp().zzga(paramString2);
    if (k != 0)
    {
      paramString1 = zzJp().zza(paramString2, zzJv().zzJV(), true);
      i = j;
      if (paramString2 != null) {
        i = paramString2.length();
      }
      this.zzbpw.zzJp().zza(k, "_ev", paramString1, i);
    }
    do
    {
      return;
      if (paramObject == null) {
        break;
      }
      j = zzJp().zzm(paramString2, paramObject);
      if (j != 0)
      {
        paramString1 = zzJp().zza(paramString2, zzJv().zzJV(), true);
        if (((paramObject instanceof String)) || ((paramObject instanceof CharSequence))) {
          i = String.valueOf(paramObject).length();
        }
        this.zzbpw.zzJp().zza(j, "_ev", paramString1, i);
        return;
      }
      paramObject = zzJp().zzn(paramString2, paramObject);
    } while (paramObject == null);
    zza(paramString1, paramString2, l, paramObject);
    return;
    zza(paramString1, paramString2, l, null);
  }
  
  public void zze(String paramString1, String paramString2, Bundle paramBundle)
  {
    zzJe();
    if ((this.zzbtV == null) || (zzaue.zzgg(paramString2))) {}
    for (boolean bool = true;; bool = false)
    {
      zza(paramString1, paramString2, paramBundle, true, bool, false, null);
      return;
    }
  }
  
  @WorkerThread
  public void zzf(Class<?> paramClass)
  {
    try
    {
      paramClass.getDeclaredMethod("initialize", new Class[] { Context.class }).invoke(null, new Object[] { getContext() });
      return;
    }
    catch (Exception paramClass)
    {
      zzJt().zzLc().zzj("Failed to invoke Tag Manager's initialize() method", paramClass);
    }
  }
  
  @Nullable
  @WorkerThread
  public String zzfS(String paramString)
  {
    Object localObject = null;
    for (;;)
    {
      try
      {
        zznA();
        zzJe();
        if (zzJs().zzLr())
        {
          zzJt().zzLa().log("Cannot retrieve app instance id from analytics worker thread");
          paramString = (String)localObject;
          return paramString;
        }
        if (zzJs().zzbd())
        {
          zzJt().zzLa().log("Cannot retrieve app instance id from main thread");
          paramString = (String)localObject;
          continue;
        }
        if (paramString == null) {
          break label97;
        }
      }
      finally {}
      if (paramString.equals(this.zzbtZ))
      {
        paramString = this.zzbtY;
        continue;
      }
      label97:
      synchronized (new AtomicReference())
      {
        this.zzbpw.zzJs().zzm(new Runnable()
        {
          public void run()
          {
            zzatu.this.zzJl().zza(localAtomicReference);
          }
        });
        try
        {
          ???.wait(30000L);
          this.zzbtZ = paramString;
          this.zzbtY = ((String)???.get());
          paramString = this.zzbtY;
        }
        catch (InterruptedException paramString)
        {
          zzJt().zzLc().log("Interrupted waiting for app instance id");
          paramString = (String)localObject;
        }
      }
    }
  }
  
  protected void zzmr() {}
  
  @TargetApi(14)
  @MainThread
  private class zza
    implements Application.ActivityLifecycleCallbacks
  {
    private zza() {}
    
    private boolean zzfT(String paramString)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        zzatu.this.zzd("auto", "_ldl", paramString);
        return true;
      }
      return false;
    }
    
    public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
    {
      for (;;)
      {
        int i;
        try
        {
          zzatu.this.zzJt().zzLg().log("onActivityCreated");
          Object localObject = paramActivity.getIntent();
          if (localObject == null) {
            continue;
          }
          Uri localUri = ((Intent)localObject).getData();
          if ((localUri == null) || (!localUri.isHierarchical())) {
            continue;
          }
          if (paramBundle == null)
          {
            Bundle localBundle = zzatu.this.zzJp().zzu(localUri);
            if (!zzatu.this.zzJp().zzD((Intent)localObject)) {
              break label266;
            }
            localObject = "gs";
            if (localBundle != null) {
              zzatu.this.zze((String)localObject, "_cmp", localBundle);
            }
          }
          localObject = localUri.getQueryParameter("referrer");
          if (TextUtils.isEmpty((CharSequence)localObject)) {
            return;
          }
          if (!((String)localObject).contains("gclid")) {
            break label234;
          }
          if ((((String)localObject).contains("utm_campaign")) || (((String)localObject).contains("utm_source")) || (((String)localObject).contains("utm_medium")) || (((String)localObject).contains("utm_term"))) {
            break label273;
          }
          if (!((String)localObject).contains("utm_content")) {
            break label234;
          }
        }
        catch (Throwable localThrowable)
        {
          zzatu.this.zzJt().zzLa().zzj("Throwable caught in onActivityCreated", localThrowable);
          zzatu.this.zzJm().onActivityCreated(paramActivity, paramBundle);
          return;
        }
        if (i == 0)
        {
          zzatu.this.zzJt().zzLf().log("Activity created with data 'referrer' param without gclid and at least one utm field");
          return;
          label234:
          i = 0;
        }
        else
        {
          zzatu.this.zzJt().zzLf().zzj("Activity created with referrer", localThrowable);
          zzfT(localThrowable);
          continue;
          label266:
          String str = "auto";
          continue;
          label273:
          i = 1;
        }
      }
    }
    
    public void onActivityDestroyed(Activity paramActivity)
    {
      zzatu.this.zzJm().onActivityDestroyed(paramActivity);
    }
    
    @MainThread
    public void onActivityPaused(Activity paramActivity)
    {
      zzatu.this.zzJm().onActivityPaused(paramActivity);
      zzatu.this.zzJr().zzMe();
    }
    
    @MainThread
    public void onActivityResumed(Activity paramActivity)
    {
      zzatu.this.zzJm().onActivityResumed(paramActivity);
      zzatu.this.zzJr().zzMc();
    }
    
    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle)
    {
      zzatu.this.zzJm().onActivitySaveInstanceState(paramActivity, paramBundle);
    }
    
    public void onActivityStarted(Activity paramActivity) {}
    
    public void onActivityStopped(Activity paramActivity) {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */