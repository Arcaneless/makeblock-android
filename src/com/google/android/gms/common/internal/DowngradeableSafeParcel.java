package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.zza;

public abstract class DowngradeableSafeParcel
  extends zza
  implements ReflectedParcelable
{
  private static final Object zzaEi = new Object();
  private static ClassLoader zzaEj = null;
  private static Integer zzaEk = null;
  private boolean zzaEl = false;
  
  protected static boolean zzdp(String paramString)
  {
    zzxn();
    return true;
  }
  
  protected static ClassLoader zzxn()
  {
    synchronized (zzaEi)
    {
      return null;
    }
  }
  
  protected static Integer zzxo()
  {
    synchronized (zzaEi)
    {
      return null;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\common\internal\DowngradeableSafeParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */