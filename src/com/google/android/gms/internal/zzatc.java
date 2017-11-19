package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzatc
  implements Parcelable.Creator<zzatb>
{
  static void zza(zzatb paramzzatb, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzatb.versionCode);
    zzc.zza(paramParcel, 2, paramzzatb.name, false);
    zzc.zza(paramParcel, 3, paramzzatb.zzbqP, paramInt, false);
    zzc.zza(paramParcel, 4, paramzzatb.zzbqQ, false);
    zzc.zza(paramParcel, 5, paramzzatb.zzbqR);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzatb zzhM(Parcel paramParcel)
  {
    String str1 = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    long l = 0L;
    zzasz localzzasz = null;
    String str2 = null;
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
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        localzzasz = (zzasz)zzb.zza(paramParcel, k, zzasz.CREATOR);
        break;
      case 4: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 5: 
        l = zzb.zzi(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzatb(i, str2, localzzasz, str1, l);
  }
  
  public zzatb[] zzlr(int paramInt)
  {
    return new zzatb[paramInt];
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */