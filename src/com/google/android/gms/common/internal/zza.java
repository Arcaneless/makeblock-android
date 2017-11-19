package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.zze;

public class zza
  extends zzr.zza
{
  int zzaDw;
  
  public static Account zza(zzr paramzzr)
  {
    Account localAccount = null;
    long l;
    if (paramzzr != null) {
      l = Binder.clearCallingIdentity();
    }
    try
    {
      localAccount = paramzzr.getAccount();
      return localAccount;
    }
    catch (RemoteException paramzzr)
    {
      Log.w("AccountAccessor", "Remote account accessor probably died");
      return null;
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zza)) {
      return false;
    }
    throw new NullPointerException();
  }
  
  public Account getAccount()
  {
    int i = Binder.getCallingUid();
    if (i == this.zzaDw) {
      return null;
    }
    if (zze.zzf(null, i))
    {
      this.zzaDw = i;
      return null;
    }
    throw new SecurityException("Caller is not GooglePlayServices");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\common\internal\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */