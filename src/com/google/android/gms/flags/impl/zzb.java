package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.zzaps;
import java.util.concurrent.Callable;

public class zzb
{
  private static SharedPreferences zzaWS = null;
  
  public static SharedPreferences zzm(Context paramContext)
  {
    try
    {
      if (zzaWS == null) {
        zzaWS = (SharedPreferences)zzaps.zzb(new Callable()
        {
          public SharedPreferences zzCU()
          {
            return zzb.this.getSharedPreferences("google_sdk_flags", 1);
          }
        });
      }
      paramContext = zzaWS;
      return paramContext;
    }
    finally {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\flags\impl\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */