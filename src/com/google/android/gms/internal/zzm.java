package com.google.android.gms.internal;

public class zzm<T>
{
  public final T result;
  public final zzb.zza zzae;
  public final zzr zzaf;
  public boolean zzag = false;
  
  private zzm(zzr paramzzr)
  {
    this.result = null;
    this.zzae = null;
    this.zzaf = paramzzr;
  }
  
  private zzm(T paramT, zzb.zza paramzza)
  {
    this.result = paramT;
    this.zzae = paramzza;
    this.zzaf = null;
  }
  
  public static <T> zzm<T> zza(T paramT, zzb.zza paramzza)
  {
    return new zzm(paramT, paramzza);
  }
  
  public static <T> zzm<T> zzd(zzr paramzzr)
  {
    return new zzm(paramzzr);
  }
  
  public boolean isSuccess()
  {
    return this.zzaf == null;
  }
  
  public static abstract interface zza
  {
    public abstract void zze(zzr paramzzr);
  }
  
  public static abstract interface zzb<T>
  {
    public abstract void zzb(T paramT);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */