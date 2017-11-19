package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzaas;
import com.google.android.gms.internal.zzaso;
import com.google.android.gms.internal.zzast;
import com.google.android.gms.internal.zzati;
import com.google.android.gms.internal.zzati.zza;
import com.google.android.gms.internal.zzatp;
import com.google.android.gms.internal.zzatu;
import com.google.android.gms.internal.zzatv;
import com.google.android.gms.internal.zzaub;
import com.google.android.gms.internal.zzaue;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.analytics.FirebaseAnalytics.UserProperty;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Deprecated
public class AppMeasurement
{
  private final zzatp zzbpw;
  
  public AppMeasurement(zzatp paramzzatp)
  {
    zzac.zzw(paramzzatp);
    this.zzbpw = paramzzatp;
  }
  
  @Deprecated
  @Keep
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
  public static AppMeasurement getInstance(Context paramContext)
  {
    return zzatp.zzbu(paramContext).zzLw();
  }
  
  private void zzc(String paramString1, String paramString2, Object paramObject)
  {
    this.zzbpw.zzJi().zzd(paramString1, paramString2, paramObject);
  }
  
  @Keep
  public void beginAdUnitExposure(@NonNull @Size(min=1L) String paramString)
  {
    this.zzbpw.zzJg().beginAdUnitExposure(paramString);
  }
  
  @Keep
  public void endAdUnitExposure(@NonNull @Size(min=1L) String paramString)
  {
    this.zzbpw.zzJg().endAdUnitExposure(paramString);
  }
  
  @Keep
  public long generateEventId()
  {
    return this.zzbpw.zzJp().zzMi();
  }
  
  @Keep
  @Nullable
  @WorkerThread
  public String getAppInstanceId()
  {
    return this.zzbpw.zzJi().zzfS(null);
  }
  
  @Keep
  @Nullable
  @WorkerThread
  public String getAppInstanceIdOnPackageSide(String paramString)
  {
    return this.zzbpw.zzJi().getAppInstanceIdOnPackageSide(paramString);
  }
  
  @Keep
  @Nullable
  public String getCurrentScreenName()
  {
    zzf localzzf = this.zzbpw.zzJm().zzLV();
    if (localzzf != null) {
      return localzzf.zzbpz;
    }
    return null;
  }
  
  @Keep
  @Nullable
  public String getCurrentScreenNameOnPackageSide(String paramString)
  {
    paramString = this.zzbpw.zzJm().zzfU(paramString);
    if (paramString != null) {
      return paramString.zzbpz;
    }
    return null;
  }
  
  @Keep
  @Nullable
  public String getGmpAppId()
  {
    try
    {
      String str = zzaas.zzwj();
      return str;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      this.zzbpw.zzJt().zzLa().zzj("getGoogleAppId failed with exception", localIllegalStateException);
    }
    return null;
  }
  
  @Keep
  @Nullable
  @WorkerThread
  public String getGmpAppIdOnPackageSide(String paramString)
  {
    return this.zzbpw.zzJi().getGmpAppIdOnPackageSide(paramString);
  }
  
  public void logEvent(@NonNull @Size(max=32L, min=1L) String paramString, Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    this.zzbpw.zzJv().zzKk();
    if (!"_iap".equals(paramString))
    {
      int j = this.zzbpw.zzJp().zzfX(paramString);
      if (j != 0)
      {
        paramBundle = this.zzbpw.zzJp().zza(paramString, this.zzbpw.zzJv().zzJU(), true);
        if (paramString != null) {}
        for (int i = paramString.length();; i = 0)
        {
          this.zzbpw.zzJp().zza(j, "_ev", paramBundle, i);
          return;
        }
      }
    }
    this.zzbpw.zzJi().zza("app", paramString, localBundle, true);
  }
  
  @Keep
  public void logEventInternal(String paramString1, String paramString2, Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    this.zzbpw.zzJi().zze(paramString1, paramString2, localBundle);
  }
  
  @Keep
  public void registerOnScreenChangeCallback(@NonNull zzd paramzzd)
  {
    this.zzbpw.zzJm().registerOnScreenChangeCallback(paramzzd);
  }
  
  @Deprecated
  public void setMeasurementEnabled(boolean paramBoolean)
  {
    this.zzbpw.zzJi().setMeasurementEnabled(paramBoolean);
  }
  
  public void setMinimumSessionDuration(long paramLong)
  {
    this.zzbpw.zzJi().setMinimumSessionDuration(paramLong);
  }
  
  public void setSessionTimeoutDuration(long paramLong)
  {
    this.zzbpw.zzJi().setSessionTimeoutDuration(paramLong);
  }
  
  public void setUserId(String paramString)
  {
    zzb("app", "_id", paramString);
  }
  
  public void setUserProperty(@NonNull @Size(max=24L, min=1L) String paramString1, @Nullable @Size(max=36L) String paramString2)
  {
    int j = this.zzbpw.zzJp().zzfZ(paramString1);
    if (j != 0)
    {
      paramString2 = this.zzbpw.zzJp().zza(paramString1, this.zzbpw.zzJv().zzJV(), true);
      if (paramString1 != null) {}
      for (int i = paramString1.length();; i = 0)
      {
        this.zzbpw.zzJp().zza(j, "_ev", paramString2, i);
        return;
      }
    }
    zzb("app", paramString1, paramString2);
  }
  
  @Keep
  public void unregisterOnScreenChangeCallback(@NonNull zzd paramzzd)
  {
    this.zzbpw.zzJm().unregisterOnScreenChangeCallback(paramzzd);
  }
  
  @WorkerThread
  public void zza(zzb paramzzb)
  {
    this.zzbpw.zzJi().zza(paramzzb);
  }
  
  public void zza(zzc paramzzc)
  {
    this.zzbpw.zzJi().zza(paramzzc);
  }
  
  public void zza(String paramString1, String paramString2, Bundle paramBundle, long paramLong)
  {
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    for (;;)
    {
      this.zzbpw.zzJi().zzd(paramString1, paramString2, paramBundle, paramLong);
      return;
    }
  }
  
  @WorkerThread
  public Map<String, Object> zzaE(boolean paramBoolean)
  {
    Object localObject = this.zzbpw.zzJi().zzaI(paramBoolean);
    HashMap localHashMap = new HashMap(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      zzaub localzzaub = (zzaub)((Iterator)localObject).next();
      localHashMap.put(localzzaub.name, localzzaub.getValue());
    }
    return localHashMap;
  }
  
  public void zzb(String paramString1, String paramString2, Object paramObject)
  {
    zzc(paramString1, paramString2, paramObject);
  }
  
  public static final class zza
    extends FirebaseAnalytics.Event
  {
    public static final Map<String, String> zzbpx = zzf.zzb(new String[] { "app_clear_data", "app_exception", "app_remove", "app_install", "app_update", "firebase_campaign", "error", "first_open", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "firebase_ad_exposure", "firebase_adunit_exposure" }, new String[] { "_cd", "_ae", "_ui", "_in", "_au", "_cmp", "_err", "_f", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_e", "_xa", "_xu" });
  }
  
  public static abstract interface zzb
  {
    @WorkerThread
    public abstract void zzb(String paramString1, String paramString2, Bundle paramBundle, long paramLong);
  }
  
  public static abstract interface zzc
  {
    @WorkerThread
    public abstract void zzc(String paramString1, String paramString2, Bundle paramBundle, long paramLong);
  }
  
  public static abstract interface zzd
  {
    @MainThread
    public abstract boolean zza(AppMeasurement.zzf paramzzf1, AppMeasurement.zzf paramzzf2);
  }
  
  public static final class zze
    extends FirebaseAnalytics.Param
  {
    public static final Map<String, String> zzbpy = zzf.zzb(new String[] { "firebase_conversion", "engagement_time_msec", "exposure_time", "ad_unit_id", "firebase_error", "firebase_error_value", "firebase_error_length", "debug", "realtime", "firebase_event_origin", "firebase_screen", "firebase_screen_class", "firebase_screen_id", "message_device_time", "message_id", "message_name", "message_time", "previous_app_version", "previous_os_version", "topic", "update_with_analytics", "previous_first_open_count", "system_app", "system_app_update", "previous_install_count" }, new String[] { "_c", "_et", "_xt", "_ai", "_err", "_ev", "_el", "_dbg", "_r", "_o", "_sn", "_sc", "_si", "_ndt", "_nmid", "_nmn", "_nmt", "_pv", "_po", "_nt", "_uwa", "_pfo", "_sys", "_sysu", "_pin" });
  }
  
  public static class zzf
  {
    public String zzbpA;
    public long zzbpB;
    public String zzbpz;
    
    public zzf() {}
    
    public zzf(zzf paramzzf)
    {
      this.zzbpz = paramzzf.zzbpz;
      this.zzbpA = paramzzf.zzbpA;
      this.zzbpB = paramzzf.zzbpB;
    }
  }
  
  public static final class zzg
    extends FirebaseAnalytics.UserProperty
  {
    public static final Map<String, String> zzbpC = zzf.zzb(new String[] { "firebase_last_notification", "first_open_time", "last_deep_link_referrer", "user_id" }, new String[] { "_ln", "_fot", "_ldl", "_id" });
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\measurement\AppMeasurement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */