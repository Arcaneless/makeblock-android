package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class WakeuperResult
  implements Parcelable
{
  public static final Parcelable.Creator<WakeuperResult> CREATOR = new Parcelable.Creator()
  {
    public WakeuperResult a(Parcel paramAnonymousParcel)
    {
      return new WakeuperResult(paramAnonymousParcel);
    }
    
    public WakeuperResult[] a(int paramAnonymousInt)
    {
      return new WakeuperResult[paramAnonymousInt];
    }
  };
  private String a = "";
  private byte[] b = null;
  
  public WakeuperResult(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
  }
  
  public WakeuperResult(String paramString)
  {
    if (paramString != null) {
      this.a = paramString;
    }
  }
  
  public WakeuperResult(String paramString, byte[] paramArrayOfByte)
  {
    this(paramString);
    this.b = paramArrayOfByte;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public byte[] getBuffer()
  {
    return this.b;
  }
  
  public String getResultString()
  {
    return this.a;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\WakeuperResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */