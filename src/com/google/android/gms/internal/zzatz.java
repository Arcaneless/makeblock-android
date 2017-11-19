package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;

class zzatz
{
  private long zzKH;
  private final zze zzuI;
  
  public zzatz(zze paramzze)
  {
    zzac.zzw(paramzze);
    this.zzuI = paramzze;
  }
  
  public void clear()
  {
    this.zzKH = 0L;
  }
  
  public void start()
  {
    this.zzKH = this.zzuI.elapsedRealtime();
  }
  
  public boolean zzz(long paramLong)
  {
    if (this.zzKH == 0L) {}
    while (this.zzuI.elapsedRealtime() - this.zzKH >= paramLong) {
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */