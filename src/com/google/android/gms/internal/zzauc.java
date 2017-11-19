package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzauc
  implements Parcelable.Creator<zzaub>
{
  static void zza(zzaub paramzzaub, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzaub.versionCode);
    zzc.zza(paramParcel, 2, paramzzaub.name, false);
    zzc.zza(paramParcel, 3, paramzzaub.zzbuZ);
    zzc.zza(paramParcel, 4, paramzzaub.zzbva, false);
    zzc.zza(paramParcel, 5, paramzzaub.zzbvb, false);
    zzc.zza(paramParcel, 6, paramzzaub.zzaFy, false);
    zzc.zza(paramParcel, 7, paramzzaub.zzbqQ, false);
    zzc.zza(paramParcel, 8, paramzzaub.zzbvc, false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzaub zzhN(Parcel paramParcel)
  {
    Double localDouble = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    long l = 0L;
    String str1 = null;
    String str2 = null;
    Float localFloat = null;
    Long localLong = null;
    String str3 = null;
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
        str3 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        l = zzb.zzi(paramParcel, k);
        break;
      case 4: 
        localLong = zzb.zzj(paramParcel, k);
        break;
      case 5: 
        localFloat = zzb.zzm(paramParcel, k);
        break;
      case 6: 
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 7: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 8: 
        localDouble = zzb.zzo(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzaub(i, str3, l, localLong, localFloat, str2, str1, localDouble);
  }
  
  public zzaub[] zzlt(int paramInt)
  {
    return new zzaub[paramInt];
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzauc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */