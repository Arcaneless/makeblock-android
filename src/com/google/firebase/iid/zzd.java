package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import java.io.IOException;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

public class zzd
{
  static Map<String, zzd> zzbha = new HashMap();
  static String zzbhg;
  private static zzh zzcja;
  private static zzf zzcjb;
  Context mContext;
  KeyPair zzbhd;
  String zzbhe = "";
  
  protected zzd(Context paramContext, String paramString, Bundle paramBundle)
  {
    this.mContext = paramContext.getApplicationContext();
    this.zzbhe = paramString;
  }
  
  public static zzd zzb(Context paramContext, Bundle paramBundle)
  {
    String str;
    if (paramBundle == null) {
      str = "";
    }
    for (;;)
    {
      try
      {
        Context localContext = paramContext.getApplicationContext();
        if (zzcja == null)
        {
          zzcja = new zzh(localContext);
          zzcjb = new zzf(localContext);
        }
        zzbhg = Integer.toString(FirebaseInstanceId.zzbU(localContext));
        zzd localzzd = (zzd)zzbha.get(str);
        paramContext = localzzd;
        if (localzzd == null)
        {
          paramContext = new zzd(localContext, str, paramBundle);
          zzbha.put(str, paramContext);
        }
        return paramContext;
      }
      finally {}
      str = paramBundle.getString("subtype");
      while (str != null) {
        break;
      }
      str = "";
    }
  }
  
  public long getCreationTime()
  {
    return zzcja.zzjz(this.zzbhe);
  }
  
  public String getToken(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    int j = 1;
    int i;
    if ((localBundle.getString("ttl") != null) || ("jwt".equals(localBundle.getString("type")))) {
      i = 0;
    }
    do
    {
      do
      {
        paramBundle = zzc(paramString1, paramString2, localBundle);
        if ((paramBundle != null) && (i != 0)) {
          zzcja.zza(this.zzbhe, paramString1, paramString2, paramBundle, zzbhg);
        }
        return paramBundle;
        paramBundle = zzcja.zzq(this.zzbhe, paramString1, paramString2);
        i = j;
      } while (paramBundle == null);
      i = j;
    } while (paramBundle.zzjC(zzbhg));
    return paramBundle.zzbwP;
  }
  
  KeyPair zzGt()
  {
    if (this.zzbhd == null) {
      this.zzbhd = zzcja.zzeM(this.zzbhe);
    }
    if (this.zzbhd == null) {
      this.zzbhd = zzcja.zzjA(this.zzbhe);
    }
    return this.zzbhd;
  }
  
  public void zzGu()
  {
    zzcja.zzeN(this.zzbhe);
    this.zzbhd = null;
  }
  
  public zzh zzaag()
  {
    return zzcja;
  }
  
  public zzf zzaah()
  {
    return zzcjb;
  }
  
  public void zzb(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    zzcja.zzi(this.zzbhe, paramString1, paramString2);
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putString("sender", paramString1);
    if (paramString2 != null) {
      localBundle.putString("scope", paramString2);
    }
    localBundle.putString("subscription", paramString1);
    localBundle.putString("delete", "1");
    localBundle.putString("X-delete", "1");
    if ("".equals(this.zzbhe))
    {
      paramString2 = paramString1;
      localBundle.putString("subtype", paramString2);
      if (!"".equals(this.zzbhe)) {
        break label165;
      }
    }
    for (;;)
    {
      localBundle.putString("X-subtype", paramString1);
      paramString1 = zzcjb.zza(localBundle, zzGt());
      zzcjb.zzt(paramString1);
      return;
      paramString2 = this.zzbhe;
      break;
      label165:
      paramString1 = this.zzbhe;
    }
  }
  
  public String zzc(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    if (paramString2 != null) {
      paramBundle.putString("scope", paramString2);
    }
    paramBundle.putString("sender", paramString1);
    if ("".equals(this.zzbhe)) {}
    for (paramString2 = paramString1;; paramString2 = this.zzbhe)
    {
      if (!paramBundle.containsKey("legacy.register"))
      {
        paramBundle.putString("subscription", paramString1);
        paramBundle.putString("subtype", paramString2);
        paramBundle.putString("X-subscription", paramString1);
        paramBundle.putString("X-subtype", paramString2);
      }
      paramString1 = zzcjb.zza(paramBundle, zzGt());
      return zzcjb.zzt(paramString1);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\firebase\iid\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */