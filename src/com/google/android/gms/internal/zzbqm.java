package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;

public class zzbqm
{
  private String zzahI;
  
  public zzbqm(@Nullable String paramString)
  {
    this.zzahI = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzbqm)) {
      return false;
    }
    paramObject = (zzbqm)paramObject;
    return zzaa.equal(this.zzahI, ((zzbqm)paramObject).zzahI);
  }
  
  @Nullable
  public String getToken()
  {
    return this.zzahI;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzahI });
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("token", this.zzahI).toString();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzbqm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */