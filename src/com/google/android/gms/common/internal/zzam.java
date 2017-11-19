package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.R.string;

public class zzam
{
  private final Resources zzaFn;
  private final String zzaFo;
  
  public zzam(Context paramContext)
  {
    zzac.zzw(paramContext);
    this.zzaFn = paramContext.getResources();
    this.zzaFo = this.zzaFn.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
  }
  
  public String getString(String paramString)
  {
    int i = this.zzaFn.getIdentifier(paramString, "string", this.zzaFo);
    if (i == 0) {
      return null;
    }
    return this.zzaFn.getString(i);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\common\internal\zzam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */