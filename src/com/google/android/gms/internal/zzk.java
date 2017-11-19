package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Map;

public abstract class zzk<T>
  implements Comparable<zzk<T>>
{
  private final zzs.zza zzB;
  private final int zzC;
  private final String zzD;
  private final int zzE;
  private final zzm.zza zzF;
  private Integer zzG;
  private zzl zzH;
  private boolean zzI;
  private boolean zzJ;
  private boolean zzK;
  private long zzL;
  private zzo zzM;
  private zzb.zza zzN;
  
  public zzk(int paramInt, String paramString, zzm.zza paramzza)
  {
    if (zzs.zza.zzai) {}
    for (zzs.zza localzza = new zzs.zza();; localzza = null)
    {
      this.zzB = localzza;
      this.zzI = true;
      this.zzJ = false;
      this.zzK = false;
      this.zzL = 0L;
      this.zzN = null;
      this.zzC = paramInt;
      this.zzD = paramString;
      this.zzF = paramzza;
      zza(new zzd());
      this.zzE = zzb(paramString);
      return;
    }
  }
  
  private static int zzb(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = Uri.parse(paramString);
      if (paramString != null)
      {
        paramString = paramString.getHost();
        if (paramString != null) {
          return paramString.hashCode();
        }
      }
    }
    return 0;
  }
  
  public Map<String, String> getHeaders()
    throws zza
  {
    return Collections.emptyMap();
  }
  
  public int getMethod()
  {
    return this.zzC;
  }
  
  public String getUrl()
  {
    return this.zzD;
  }
  
  public String toString()
  {
    String str1 = String.valueOf(Integer.toHexString(zzf()));
    if (str1.length() != 0) {}
    for (str1 = "0x".concat(str1);; str1 = new String("0x"))
    {
      String str2 = String.valueOf(getUrl());
      String str3 = String.valueOf(zzo());
      String str4 = String.valueOf(this.zzG);
      return String.valueOf("[ ] ").length() + 3 + String.valueOf(str2).length() + String.valueOf(str1).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + "[ ] " + str2 + " " + str1 + " " + str3 + " " + str4;
    }
  }
  
  public final zzk<?> zza(int paramInt)
  {
    this.zzG = Integer.valueOf(paramInt);
    return this;
  }
  
  public zzk<?> zza(zzb.zza paramzza)
  {
    this.zzN = paramzza;
    return this;
  }
  
  public zzk<?> zza(zzl paramzzl)
  {
    this.zzH = paramzzl;
    return this;
  }
  
  public zzk<?> zza(zzo paramzzo)
  {
    this.zzM = paramzzo;
    return this;
  }
  
  protected abstract zzm<T> zza(zzi paramzzi);
  
  protected abstract void zza(T paramT);
  
  protected zzr zzb(zzr paramzzr)
  {
    return paramzzr;
  }
  
  public int zzc(zzk<T> paramzzk)
  {
    zza localzza1 = zzo();
    zza localzza2 = paramzzk.zzo();
    if (localzza1 == localzza2) {
      return this.zzG.intValue() - paramzzk.zzG.intValue();
    }
    return localzza2.ordinal() - localzza1.ordinal();
  }
  
  public void zzc(zzr paramzzr)
  {
    if (this.zzF != null) {
      this.zzF.zze(paramzzr);
    }
  }
  
  public void zzc(String paramString)
  {
    if (zzs.zza.zzai) {
      this.zzB.zza(paramString, Thread.currentThread().getId());
    }
    while (this.zzL != 0L) {
      return;
    }
    this.zzL = SystemClock.elapsedRealtime();
  }
  
  void zzd(final String paramString)
  {
    if (this.zzH != null) {
      this.zzH.zzf(this);
    }
    final long l;
    if (zzs.zza.zzai)
    {
      l = Thread.currentThread().getId();
      if (Looper.myLooper() != Looper.getMainLooper()) {
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            zzk.zzd(zzk.this).zza(paramString, l);
            zzk.zzd(zzk.this).zzd(toString());
          }
        });
      }
    }
    do
    {
      return;
      this.zzB.zza(paramString, l);
      this.zzB.zzd(toString());
      return;
      l = SystemClock.elapsedRealtime() - this.zzL;
    } while (l < 3000L);
    zzs.zzb("%d ms: %s", new Object[] { Long.valueOf(l), toString() });
  }
  
  public int zzf()
  {
    return this.zzE;
  }
  
  public String zzg()
  {
    return getUrl();
  }
  
  public zzb.zza zzh()
  {
    return this.zzN;
  }
  
  @Deprecated
  public String zzi()
  {
    return zzl();
  }
  
  @Deprecated
  public byte[] zzj()
    throws zza
  {
    return null;
  }
  
  protected String zzk()
  {
    return "UTF-8";
  }
  
  public String zzl()
  {
    String str = String.valueOf(zzk());
    if (str.length() != 0) {
      return "application/x-www-form-urlencoded; charset=".concat(str);
    }
    return new String("application/x-www-form-urlencoded; charset=");
  }
  
  public byte[] zzm()
    throws zza
  {
    return null;
  }
  
  public final boolean zzn()
  {
    return this.zzI;
  }
  
  public zza zzo()
  {
    return zza.zzS;
  }
  
  public final int zzp()
  {
    return this.zzM.zzc();
  }
  
  public zzo zzq()
  {
    return this.zzM;
  }
  
  public void zzr()
  {
    this.zzK = true;
  }
  
  public boolean zzs()
  {
    return this.zzK;
  }
  
  public static enum zza
  {
    private zza() {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */