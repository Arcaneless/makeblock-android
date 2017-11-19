package com.google.android.gms.common.util;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzac;
import java.util.Set;

public final class zzu
{
  public static String[] zza(Scope[] paramArrayOfScope)
  {
    zzac.zzb(paramArrayOfScope, "scopes can't be null.");
    String[] arrayOfString = new String[paramArrayOfScope.length];
    int i = 0;
    while (i < paramArrayOfScope.length)
    {
      arrayOfString[i] = paramArrayOfScope[i].zzuS();
      i += 1;
    }
    return arrayOfString;
  }
  
  public static String[] zzd(Set<Scope> paramSet)
  {
    zzac.zzb(paramSet, "scopes can't be null.");
    return zza((Scope[])paramSet.toArray(new Scope[paramSet.size()]));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\common\util\zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */