package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import java.util.Collection;

public class zzj
  extends com.google.android.gms.common.internal.safeparcel.zza
{
  public static final Parcelable.Creator<zzj> CREATOR = new zzk();
  final int version;
  final int zzaEm;
  int zzaEn;
  String zzaEo;
  IBinder zzaEp;
  Scope[] zzaEq;
  Bundle zzaEr;
  Account zzaEs;
  long zzaEt;
  
  public zzj(int paramInt)
  {
    this.version = 3;
    this.zzaEn = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    this.zzaEm = paramInt;
  }
  
  zzj(int paramInt1, int paramInt2, int paramInt3, String paramString, IBinder paramIBinder, Scope[] paramArrayOfScope, Bundle paramBundle, Account paramAccount, long paramLong)
  {
    this.version = paramInt1;
    this.zzaEm = paramInt2;
    this.zzaEn = paramInt3;
    if ("com.google.android.gms".equals(paramString))
    {
      this.zzaEo = "com.google.android.gms";
      if (paramInt1 >= 2) {
        break label78;
      }
    }
    for (this.zzaEs = zzbq(paramIBinder);; this.zzaEs = paramAccount)
    {
      this.zzaEq = paramArrayOfScope;
      this.zzaEr = paramBundle;
      this.zzaEt = paramLong;
      return;
      this.zzaEo = paramString;
      break;
      label78:
      this.zzaEp = paramIBinder;
    }
  }
  
  private Account zzbq(IBinder paramIBinder)
  {
    Account localAccount = null;
    if (paramIBinder != null) {
      localAccount = zza.zza(zzr.zza.zzbr(paramIBinder));
    }
    return localAccount;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzk.zza(this, paramParcel, paramInt);
  }
  
  public zzj zzb(zzr paramzzr)
  {
    if (paramzzr != null) {
      this.zzaEp = paramzzr.asBinder();
    }
    return this;
  }
  
  public zzj zzdq(String paramString)
  {
    this.zzaEo = paramString;
    return this;
  }
  
  public zzj zze(Account paramAccount)
  {
    this.zzaEs = paramAccount;
    return this;
  }
  
  public zzj zzf(Collection<Scope> paramCollection)
  {
    this.zzaEq = ((Scope[])paramCollection.toArray(new Scope[paramCollection.size()]));
    return this;
  }
  
  public zzj zzp(Bundle paramBundle)
  {
    this.zzaEr = paramBundle;
    return this;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\common\internal\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */