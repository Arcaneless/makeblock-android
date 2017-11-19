package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;

public final class Status
  extends zza
  implements Result, ReflectedParcelable
{
  public static final Parcelable.Creator<Status> CREATOR = new zzh();
  public static final Status zzayh = new Status(0);
  public static final Status zzayi = new Status(14);
  public static final Status zzayj = new Status(8);
  public static final Status zzayk = new Status(15);
  public static final Status zzayl = new Status(16);
  public static final Status zzaym = new Status(17);
  public static final Status zzayn = new Status(18);
  private final PendingIntent mPendingIntent;
  final int mVersionCode;
  private final int zzauz;
  private final String zzawY;
  
  public Status(int paramInt)
  {
    this(paramInt, null);
  }
  
  Status(int paramInt1, int paramInt2, String paramString, PendingIntent paramPendingIntent)
  {
    this.mVersionCode = paramInt1;
    this.zzauz = paramInt2;
    this.zzawY = paramString;
    this.mPendingIntent = paramPendingIntent;
  }
  
  public Status(int paramInt, String paramString)
  {
    this(1, paramInt, paramString, null);
  }
  
  public Status(int paramInt, String paramString, PendingIntent paramPendingIntent)
  {
    this(1, paramInt, paramString, paramPendingIntent);
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Status)) {}
    do
    {
      return false;
      paramObject = (Status)paramObject;
    } while ((this.mVersionCode != ((Status)paramObject).mVersionCode) || (this.zzauz != ((Status)paramObject).zzauz) || (!zzaa.equal(this.zzawY, ((Status)paramObject).zzawY)) || (!zzaa.equal(this.mPendingIntent, ((Status)paramObject).mPendingIntent)));
    return true;
  }
  
  public PendingIntent getResolution()
  {
    return this.mPendingIntent;
  }
  
  public Status getStatus()
  {
    return this;
  }
  
  public int getStatusCode()
  {
    return this.zzauz;
  }
  
  @Nullable
  public String getStatusMessage()
  {
    return this.zzawY;
  }
  
  public boolean hasResolution()
  {
    return this.mPendingIntent != null;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { Integer.valueOf(this.mVersionCode), Integer.valueOf(this.zzauz), this.zzawY, this.mPendingIntent });
  }
  
  public boolean isCanceled()
  {
    return this.zzauz == 16;
  }
  
  public boolean isInterrupted()
  {
    return this.zzauz == 14;
  }
  
  public boolean isSuccess()
  {
    return this.zzauz <= 0;
  }
  
  public void startResolutionForResult(Activity paramActivity, int paramInt)
    throws IntentSender.SendIntentException
  {
    if (!hasResolution()) {
      return;
    }
    paramActivity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), paramInt, null, 0, 0, 0);
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("statusCode", zzuU()).zzg("resolution", this.mPendingIntent).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
  
  PendingIntent zzuT()
  {
    return this.mPendingIntent;
  }
  
  public String zzuU()
  {
    if (this.zzawY != null) {
      return this.zzawY;
    }
    return CommonStatusCodes.getStatusCodeString(this.zzauz);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\common\api\Status.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */