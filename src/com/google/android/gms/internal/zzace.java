package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.internal.zzq;

public class zzace
{
  private final String mTag;
  private final String zzaEV;
  private final zzq zzaFr;
  private final int zzaeb;
  
  private zzace(String paramString1, String paramString2)
  {
    this.zzaEV = paramString2;
    this.mTag = paramString1;
    this.zzaFr = new zzq(paramString1);
    this.zzaeb = getLogLevel();
  }
  
  public zzace(String paramString, String... paramVarArgs)
  {
    this(paramString, zzd(paramVarArgs));
  }
  
  private int getLogLevel()
  {
    int i = 2;
    while ((7 >= i) && (!Log.isLoggable(this.mTag, i))) {
      i += 1;
    }
    return i;
  }
  
  private static String zzd(String... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      String str = paramVarArgs[i];
      if (localStringBuilder.length() > 1) {
        localStringBuilder.append(",");
      }
      localStringBuilder.append(str);
      i += 1;
    }
    localStringBuilder.append(']').append(' ');
    return localStringBuilder.toString();
  }
  
  protected String format(String paramString, Object... paramVarArgs)
  {
    String str = paramString;
    if (paramVarArgs != null)
    {
      str = paramString;
      if (paramVarArgs.length > 0) {
        str = String.format(paramString, paramVarArgs);
      }
    }
    return this.zzaEV.concat(str);
  }
  
  public void zza(String paramString, Object... paramVarArgs)
  {
    if (zzai(2)) {
      Log.v(this.mTag, format(paramString, paramVarArgs));
    }
  }
  
  public boolean zzai(int paramInt)
  {
    return this.zzaeb <= paramInt;
  }
  
  public void zzb(String paramString, Object... paramVarArgs)
  {
    if (zzai(3)) {
      Log.d(this.mTag, format(paramString, paramVarArgs));
    }
  }
  
  public void zze(String paramString, Object... paramVarArgs)
  {
    Log.i(this.mTag, format(paramString, paramVarArgs));
  }
  
  public void zzf(String paramString, Object... paramVarArgs)
  {
    Log.w(this.mTag, format(paramString, paramVarArgs));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */