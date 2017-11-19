package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;

public class zzaub
  extends zza
{
  public static final Parcelable.Creator<zzaub> CREATOR = new zzauc();
  public final String name;
  public final int versionCode;
  public final String zzaFy;
  public final String zzbqQ;
  public final long zzbuZ;
  public final Long zzbva;
  public final Float zzbvb;
  public final Double zzbvc;
  
  zzaub(int paramInt, String paramString1, long paramLong, Long paramLong1, Float paramFloat, String paramString2, String paramString3, Double paramDouble)
  {
    this.versionCode = paramInt;
    this.name = paramString1;
    this.zzbuZ = paramLong;
    this.zzbva = paramLong1;
    this.zzbvb = null;
    if (paramInt == 1)
    {
      paramString1 = (String)localObject;
      if (paramFloat != null) {
        paramString1 = Double.valueOf(paramFloat.doubleValue());
      }
    }
    for (this.zzbvc = paramString1;; this.zzbvc = paramDouble)
    {
      this.zzaFy = paramString2;
      this.zzbqQ = paramString3;
      return;
    }
  }
  
  zzaub(zzaud paramzzaud)
  {
    this(paramzzaud.mName, paramzzaud.zzbvd, paramzzaud.zzYe, paramzzaud.zzVQ);
  }
  
  zzaub(String paramString1, long paramLong, Object paramObject, String paramString2)
  {
    zzac.zzdv(paramString1);
    this.versionCode = 2;
    this.name = paramString1;
    this.zzbuZ = paramLong;
    this.zzbqQ = paramString2;
    if (paramObject == null)
    {
      this.zzbva = null;
      this.zzbvb = null;
      this.zzbvc = null;
      this.zzaFy = null;
      return;
    }
    if ((paramObject instanceof Long))
    {
      this.zzbva = ((Long)paramObject);
      this.zzbvb = null;
      this.zzbvc = null;
      this.zzaFy = null;
      return;
    }
    if ((paramObject instanceof String))
    {
      this.zzbva = null;
      this.zzbvb = null;
      this.zzbvc = null;
      this.zzaFy = ((String)paramObject);
      return;
    }
    if ((paramObject instanceof Double))
    {
      this.zzbva = null;
      this.zzbvb = null;
      this.zzbvc = ((Double)paramObject);
      this.zzaFy = null;
      return;
    }
    throw new IllegalArgumentException("User attribute given of un-supported type");
  }
  
  public Object getValue()
  {
    if (this.zzbva != null) {
      return this.zzbva;
    }
    if (this.zzbvc != null) {
      return this.zzbvc;
    }
    if (this.zzaFy != null) {
      return this.zzaFy;
    }
    return null;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzauc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzaub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */