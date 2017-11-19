package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzapq.zza;

@DynamiteApi
public class FlagProviderImpl
  extends zzapq.zza
{
  private SharedPreferences zzAN;
  private boolean zztW = false;
  
  public boolean getBooleanFlagValue(String paramString, boolean paramBoolean, int paramInt)
  {
    if (!this.zztW) {
      return paramBoolean;
    }
    return zza.zza.zza(this.zzAN, paramString, Boolean.valueOf(paramBoolean)).booleanValue();
  }
  
  public int getIntFlagValue(String paramString, int paramInt1, int paramInt2)
  {
    if (!this.zztW) {
      return paramInt1;
    }
    return zza.zzb.zza(this.zzAN, paramString, Integer.valueOf(paramInt1)).intValue();
  }
  
  public long getLongFlagValue(String paramString, long paramLong, int paramInt)
  {
    if (!this.zztW) {
      return paramLong;
    }
    return zza.zzc.zza(this.zzAN, paramString, Long.valueOf(paramLong)).longValue();
  }
  
  public String getStringFlagValue(String paramString1, String paramString2, int paramInt)
  {
    if (!this.zztW) {
      return paramString2;
    }
    return zza.zzd.zza(this.zzAN, paramString1, paramString2);
  }
  
  public void init(zzd paramzzd)
  {
    paramzzd = (Context)zze.zzE(paramzzd);
    if (this.zztW) {
      return;
    }
    try
    {
      this.zzAN = zzb.zzm(paramzzd.createPackageContext("com.google.android.gms", 0));
      this.zztW = true;
      return;
    }
    catch (PackageManager.NameNotFoundException paramzzd) {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\flags\impl\FlagProviderImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */