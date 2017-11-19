package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zzatb
  extends zza
{
  public static final Parcelable.Creator<zzatb> CREATOR = new zzatc();
  public final String name;
  public final int versionCode;
  public final zzasz zzbqP;
  public final String zzbqQ;
  public final long zzbqR;
  
  zzatb(int paramInt, String paramString1, zzasz paramzzasz, String paramString2, long paramLong)
  {
    this.versionCode = paramInt;
    this.name = paramString1;
    this.zzbqP = paramzzasz;
    this.zzbqQ = paramString2;
    this.zzbqR = paramLong;
  }
  
  public zzatb(String paramString1, zzasz paramzzasz, String paramString2, long paramLong)
  {
    this.versionCode = 1;
    this.name = paramString1;
    this.zzbqP = paramzzasz;
    this.zzbqQ = paramString2;
    this.zzbqR = paramLong;
  }
  
  public String toString()
  {
    String str1 = this.zzbqQ;
    String str2 = this.name;
    String str3 = String.valueOf(this.zzbqP);
    return String.valueOf(str1).length() + 21 + String.valueOf(str2).length() + String.valueOf(str3).length() + "origin=" + str1 + ",name=" + str2 + ",params=" + str3;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzatc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */