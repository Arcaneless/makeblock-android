package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.firebase.iid.zzc;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Locale;

class zzatl
  extends zzats
{
  static final Pair<String, Long> zzbse = new Pair("", Long.valueOf(0L));
  private SharedPreferences zzafC;
  public final zzc zzbsf = new zzc("health_monitor", zzJv().zzoZ(), null);
  public final zzb zzbsg = new zzb("last_upload", 0L);
  public final zzb zzbsh = new zzb("last_upload_attempt", 0L);
  public final zzb zzbsi = new zzb("backoff", 0L);
  public final zzb zzbsj = new zzb("last_delete_stale", 0L);
  public final zzb zzbsk = new zzb("midnight_offset", 0L);
  private String zzbsl;
  private boolean zzbsm;
  private long zzbsn;
  private SecureRandom zzbso;
  public final zzb zzbsp = new zzb("time_before_start", 10000L);
  public final zzb zzbsq = new zzb("session_timeout", 1800000L);
  public final zza zzbsr = new zza("start_new_session", true);
  public final zzb zzbss = new zzb("last_pause_time", 0L);
  public final zzb zzbst = new zzb("time_active", 0L);
  public boolean zzbsu;
  
  zzatl(zzatp paramzzatp)
  {
    super(paramzzatp);
  }
  
  @WorkerThread
  private SecureRandom zzLi()
  {
    zzmq();
    if (this.zzbso == null) {
      this.zzbso = new SecureRandom();
    }
    return this.zzbso;
  }
  
  @WorkerThread
  private SharedPreferences zzLl()
  {
    zzmq();
    zznA();
    return this.zzafC;
  }
  
  @WorkerThread
  void setMeasurementEnabled(boolean paramBoolean)
  {
    zzmq();
    zzJt().zzLg().zzj("Setting measurementEnabled", Boolean.valueOf(paramBoolean));
    SharedPreferences.Editor localEditor = zzLl().edit();
    localEditor.putBoolean("measurement_enabled", paramBoolean);
    localEditor.apply();
  }
  
  @WorkerThread
  String zzJy()
  {
    zzmq();
    try
    {
      String str = zzc.zzaab().getId();
      return str;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      zzJt().zzLc().log("Failed to retrieve Firebase Instance Id");
    }
    return null;
  }
  
  @WorkerThread
  String zzLj()
  {
    byte[] arrayOfByte = new byte[16];
    zzLi().nextBytes(arrayOfByte);
    return String.format(Locale.US, "%032x", new Object[] { new BigInteger(1, arrayOfByte) });
  }
  
  @WorkerThread
  long zzLk()
  {
    zznA();
    zzmq();
    long l2 = this.zzbsk.get();
    long l1 = l2;
    if (l2 == 0L)
    {
      l1 = zzLi().nextInt(86400000) + 1;
      this.zzbsk.set(l1);
    }
    return l1;
  }
  
  @WorkerThread
  String zzLm()
  {
    zzmq();
    return zzLl().getString("gmp_app_id", null);
  }
  
  @WorkerThread
  Boolean zzLn()
  {
    zzmq();
    if (!zzLl().contains("use_service")) {
      return null;
    }
    return Boolean.valueOf(zzLl().getBoolean("use_service", false));
  }
  
  @WorkerThread
  void zzLo()
  {
    boolean bool1 = true;
    zzmq();
    zzJt().zzLg().log("Clearing collection preferences.");
    boolean bool2 = zzLl().contains("measurement_enabled");
    if (bool2) {
      bool1 = zzaG(true);
    }
    SharedPreferences.Editor localEditor = zzLl().edit();
    localEditor.clear();
    localEditor.apply();
    if (bool2) {
      setMeasurementEnabled(bool1);
    }
  }
  
  @WorkerThread
  protected String zzLp()
  {
    zzmq();
    String str1 = zzLl().getString("previous_os_version", null);
    String str2 = zzJk().zzKU();
    if ((!TextUtils.isEmpty(str2)) && (!str2.equals(str1)))
    {
      SharedPreferences.Editor localEditor = zzLl().edit();
      localEditor.putString("previous_os_version", str2);
      localEditor.apply();
    }
    return str1;
  }
  
  @WorkerThread
  void zzaF(boolean paramBoolean)
  {
    zzmq();
    zzJt().zzLg().zzj("Setting useService", Boolean.valueOf(paramBoolean));
    SharedPreferences.Editor localEditor = zzLl().edit();
    localEditor.putBoolean("use_service", paramBoolean);
    localEditor.apply();
  }
  
  @WorkerThread
  boolean zzaG(boolean paramBoolean)
  {
    zzmq();
    return zzLl().getBoolean("measurement_enabled", paramBoolean);
  }
  
  @NonNull
  @WorkerThread
  Pair<String, Boolean> zzfK(String paramString)
  {
    zzmq();
    long l = zznq().elapsedRealtime();
    if ((this.zzbsl != null) && (l < this.zzbsn)) {
      return new Pair(this.zzbsl, Boolean.valueOf(this.zzbsm));
    }
    this.zzbsn = (l + zzJv().zzfq(paramString));
    AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
    try
    {
      paramString = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
      this.zzbsl = paramString.getId();
      if (this.zzbsl == null) {
        this.zzbsl = "";
      }
      this.zzbsm = paramString.isLimitAdTrackingEnabled();
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        zzJt().zzLf().zzj("Unable to get advertising id", paramString);
        this.zzbsl = "";
      }
    }
    AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
    return new Pair(this.zzbsl, Boolean.valueOf(this.zzbsm));
  }
  
  @WorkerThread
  String zzfL(String paramString)
  {
    zzmq();
    paramString = (String)zzfK(paramString).first;
    MessageDigest localMessageDigest = zzaue.zzcg("MD5");
    if (localMessageDigest == null) {
      return null;
    }
    return String.format(Locale.US, "%032X", new Object[] { new BigInteger(1, localMessageDigest.digest(paramString.getBytes())) });
  }
  
  @WorkerThread
  void zzfM(String paramString)
  {
    zzmq();
    SharedPreferences.Editor localEditor = zzLl().edit();
    localEditor.putString("gmp_app_id", paramString);
    localEditor.apply();
  }
  
  protected void zzmr()
  {
    this.zzafC = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
    this.zzbsu = this.zzafC.getBoolean("has_been_opened", false);
    if (!this.zzbsu)
    {
      SharedPreferences.Editor localEditor = this.zzafC.edit();
      localEditor.putBoolean("has_been_opened", true);
      localEditor.apply();
    }
  }
  
  public final class zza
  {
    private final String zzAH;
    private boolean zzaxF;
    private final boolean zzbsv;
    private boolean zzbsw;
    
    public zza(String paramString, boolean paramBoolean)
    {
      zzac.zzdv(paramString);
      this.zzAH = paramString;
      this.zzbsv = paramBoolean;
    }
    
    @WorkerThread
    private void zzLq()
    {
      if (this.zzbsw) {
        return;
      }
      this.zzbsw = true;
      this.zzaxF = zzatl.zza(zzatl.this).getBoolean(this.zzAH, this.zzbsv);
    }
    
    @WorkerThread
    public boolean get()
    {
      zzLq();
      return this.zzaxF;
    }
    
    @WorkerThread
    public void set(boolean paramBoolean)
    {
      SharedPreferences.Editor localEditor = zzatl.zza(zzatl.this).edit();
      localEditor.putBoolean(this.zzAH, paramBoolean);
      localEditor.apply();
      this.zzaxF = paramBoolean;
    }
  }
  
  public final class zzb
  {
    private final String zzAH;
    private long zzacc;
    private boolean zzbsw;
    private final long zzbsy;
    
    public zzb(String paramString, long paramLong)
    {
      zzac.zzdv(paramString);
      this.zzAH = paramString;
      this.zzbsy = paramLong;
    }
    
    @WorkerThread
    private void zzLq()
    {
      if (this.zzbsw) {
        return;
      }
      this.zzbsw = true;
      this.zzacc = zzatl.zza(zzatl.this).getLong(this.zzAH, this.zzbsy);
    }
    
    @WorkerThread
    public long get()
    {
      zzLq();
      return this.zzacc;
    }
    
    @WorkerThread
    public void set(long paramLong)
    {
      SharedPreferences.Editor localEditor = zzatl.zza(zzatl.this).edit();
      localEditor.putLong(this.zzAH, paramLong);
      localEditor.apply();
      this.zzacc = paramLong;
    }
  }
  
  public final class zzc
  {
    private final long zzafG;
    private final String zzbsA;
    private final String zzbsB;
    final String zzbsz;
    
    private zzc(String paramString, long paramLong)
    {
      zzac.zzdv(paramString);
      if (paramLong > 0L) {}
      for (boolean bool = true;; bool = false)
      {
        zzac.zzas(bool);
        this.zzbsz = String.valueOf(paramString).concat(":start");
        this.zzbsA = String.valueOf(paramString).concat(":count");
        this.zzbsB = String.valueOf(paramString).concat(":value");
        this.zzafG = paramLong;
        return;
      }
    }
    
    @WorkerThread
    private void zzpK()
    {
      zzatl.this.zzmq();
      long l = zzatl.this.zznq().currentTimeMillis();
      SharedPreferences.Editor localEditor = zzatl.zza(zzatl.this).edit();
      localEditor.remove(this.zzbsA);
      localEditor.remove(this.zzbsB);
      localEditor.putLong(this.zzbsz, l);
      localEditor.apply();
    }
    
    @WorkerThread
    private long zzpL()
    {
      zzatl.this.zzmq();
      long l = zzpN();
      if (l == 0L)
      {
        zzpK();
        return 0L;
      }
      return Math.abs(l - zzatl.this.zznq().currentTimeMillis());
    }
    
    @WorkerThread
    private long zzpN()
    {
      return zzatl.zzc(zzatl.this).getLong(this.zzbsz, 0L);
    }
    
    @WorkerThread
    public void zzcb(String paramString)
    {
      zzi(paramString, 1L);
    }
    
    @WorkerThread
    public void zzi(String paramString, long paramLong)
    {
      zzatl.this.zzmq();
      if (zzpN() == 0L) {
        zzpK();
      }
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      long l = zzatl.zza(zzatl.this).getLong(this.zzbsA, 0L);
      if (l <= 0L)
      {
        paramString = zzatl.zza(zzatl.this).edit();
        paramString.putString(this.zzbsB, str);
        paramString.putLong(this.zzbsA, paramLong);
        paramString.apply();
        return;
      }
      if ((zzatl.zzb(zzatl.this).nextLong() & 0x7FFFFFFFFFFFFFFF) < Long.MAX_VALUE / (l + paramLong) * paramLong) {}
      for (int i = 1;; i = 0)
      {
        paramString = zzatl.zza(zzatl.this).edit();
        if (i != 0) {
          paramString.putString(this.zzbsB, str);
        }
        paramString.putLong(this.zzbsA, l + paramLong);
        paramString.apply();
        return;
      }
    }
    
    @WorkerThread
    public Pair<String, Long> zzpM()
    {
      zzatl.this.zzmq();
      long l = zzpL();
      if (l < this.zzafG) {
        return null;
      }
      if (l > this.zzafG * 2L)
      {
        zzpK();
        return null;
      }
      String str = zzatl.zzc(zzatl.this).getString(this.zzbsB, null);
      l = zzatl.zzc(zzatl.this).getLong(this.zzbsA, 0L);
      zzpK();
      if ((str == null) || (l <= 0L)) {
        return zzatl.zzbse;
      }
      return new Pair(str, Long.valueOf(l));
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */