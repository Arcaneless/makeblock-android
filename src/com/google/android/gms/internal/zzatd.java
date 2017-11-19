package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;

public final class zzatd
{
  public static zza<Boolean> zzbqS = zza.zzl("measurement.service_enabled", true);
  public static zza<Boolean> zzbqT = zza.zzl("measurement.service_client_enabled", true);
  public static zza<Boolean> zzbqU = zza.zzl("measurement.log_installs_enabled", false);
  public static zza<String> zzbqV = zza.zzk("measurement.log_tag", "FA", "FA-SVC");
  public static zza<Long> zzbqW = zza.zzh("measurement.ad_id_cache_time", 10000L);
  public static zza<Long> zzbqX = zza.zzh("measurement.monitoring.sample_period_millis", 86400000L);
  public static zza<Long> zzbqY = zza.zzb("measurement.config.cache_time", 86400000L, 3600000L);
  public static zza<String> zzbqZ = zza.zzV("measurement.config.url_scheme", "https");
  public static zza<Long> zzbrA = zza.zzh("measurement.service_client.idle_disconnect_millis", 5000L);
  public static zza<String> zzbra = zza.zzV("measurement.config.url_authority", "app-measurement.com");
  public static zza<Integer> zzbrb = zza.zzB("measurement.upload.max_bundles", 100);
  public static zza<Integer> zzbrc = zza.zzB("measurement.upload.max_batch_size", 65536);
  public static zza<Integer> zzbrd = zza.zzB("measurement.upload.max_bundle_size", 65536);
  public static zza<Integer> zzbre = zza.zzB("measurement.upload.max_events_per_bundle", 1000);
  public static zza<Integer> zzbrf = zza.zzB("measurement.upload.max_events_per_day", 100000);
  public static zza<Integer> zzbrg = zza.zzB("measurement.upload.max_error_events_per_day", 1000);
  public static zza<Integer> zzbrh = zza.zzB("measurement.upload.max_public_events_per_day", 50000);
  public static zza<Integer> zzbri = zza.zzB("measurement.upload.max_conversions_per_day", 500);
  public static zza<Integer> zzbrj = zza.zzB("measurement.upload.max_realtime_events_per_day", 10);
  public static zza<Integer> zzbrk = zza.zzB("measurement.store.max_stored_events_per_app", 100000);
  public static zza<String> zzbrl = zza.zzV("measurement.upload.url", "https://app-measurement.com/a");
  public static zza<Long> zzbrm = zza.zzh("measurement.upload.backoff_period", 43200000L);
  public static zza<Long> zzbrn = zza.zzh("measurement.upload.window_interval", 3600000L);
  public static zza<Long> zzbro = zza.zzh("measurement.upload.interval", 3600000L);
  public static zza<Long> zzbrp = zza.zzh("measurement.upload.realtime_upload_interval", 10000L);
  public static zza<Long> zzbrq = zza.zzh("measurement.upload.minimum_delay", 500L);
  public static zza<Long> zzbrr = zza.zzh("measurement.alarm_manager.minimum_interval", 60000L);
  public static zza<Long> zzbrs = zza.zzh("measurement.upload.stale_data_deletion_interval", 86400000L);
  public static zza<Long> zzbrt = zza.zzh("measurement.upload.refresh_blacklisted_config_interval", 604800000L);
  public static zza<Long> zzbru = zza.zzh("measurement.upload.initial_upload_delay_time", 15000L);
  public static zza<Long> zzbrv = zza.zzh("measurement.upload.retry_time", 1800000L);
  public static zza<Integer> zzbrw = zza.zzB("measurement.upload.retry_count", 6);
  public static zza<Long> zzbrx = zza.zzh("measurement.upload.max_queue_time", 2419200000L);
  public static zza<Integer> zzbry = zza.zzB("measurement.lifetimevalue.max_currency_tracked", 4);
  public static zza<Integer> zzbrz = zza.zzB("measurement.audience.filter_result_max_count", 200);
  
  public static final class zza<V>
  {
    private final String zzAH;
    private final V zzaeZ;
    private final zzabs<V> zzafa;
    
    private zza(String paramString, zzabs<V> paramzzabs, V paramV)
    {
      zzac.zzw(paramzzabs);
      this.zzafa = paramzzabs;
      this.zzaeZ = paramV;
      this.zzAH = paramString;
    }
    
    static zza<Integer> zzB(String paramString, int paramInt)
    {
      return zzo(paramString, paramInt, paramInt);
    }
    
    static zza<String> zzV(String paramString1, String paramString2)
    {
      return zzk(paramString1, paramString2, paramString2);
    }
    
    static zza<Long> zzb(String paramString, long paramLong1, long paramLong2)
    {
      return new zza(paramString, zzabs.zza(paramString, Long.valueOf(paramLong2)), Long.valueOf(paramLong1));
    }
    
    static zza<Boolean> zzb(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      return new zza(paramString, zzabs.zzj(paramString, paramBoolean2), Boolean.valueOf(paramBoolean1));
    }
    
    static zza<Long> zzh(String paramString, long paramLong)
    {
      return zzb(paramString, paramLong, paramLong);
    }
    
    static zza<String> zzk(String paramString1, String paramString2, String paramString3)
    {
      return new zza(paramString1, zzabs.zzA(paramString1, paramString3), paramString2);
    }
    
    static zza<Boolean> zzl(String paramString, boolean paramBoolean)
    {
      return zzb(paramString, paramBoolean, paramBoolean);
    }
    
    static zza<Integer> zzo(String paramString, int paramInt1, int paramInt2)
    {
      return new zza(paramString, zzabs.zza(paramString, Integer.valueOf(paramInt2)), Integer.valueOf(paramInt1));
    }
    
    public V get()
    {
      return (V)this.zzaeZ;
    }
    
    public V get(V paramV)
    {
      if (paramV != null) {
        return paramV;
      }
      return (V)this.zzaeZ;
    }
    
    public String getKey()
    {
      return this.zzAH;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */