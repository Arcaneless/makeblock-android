package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzg
  implements Parcelable.Creator<Scope>
{
  static void zza(Scope paramScope, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramScope.mVersionCode);
    zzc.zza(paramParcel, 2, paramScope.zzuS(), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public Scope zzaH(Parcel paramParcel)
  {
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    String str = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        i = zzb.zzg(paramParcel, k);
        break;
      case 2: 
        str = zzb.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new Scope(i, str);
  }
  
  public Scope[] zzcs(int paramInt)
  {
    return new Scope[paramInt];
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\common\api\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */