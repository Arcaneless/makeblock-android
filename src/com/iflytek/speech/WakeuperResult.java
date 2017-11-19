package com.iflytek.speech;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class WakeuperResult
  implements Parcelable
{
  public static final Parcelable.Creator<WakeuperResult> CREATOR = new Parcelable.Creator()
  {
    public WakeuperResult createFromParcel(Parcel paramAnonymousParcel)
    {
      return new WakeuperResult(paramAnonymousParcel);
    }
    
    public WakeuperResult[] newArray(int paramAnonymousInt)
    {
      return new WakeuperResult[paramAnonymousInt];
    }
  };
  private String json = "";
  
  public WakeuperResult(Parcel paramParcel)
  {
    this.json = paramParcel.readString();
  }
  
  public WakeuperResult(String paramString)
  {
    if (paramString != null) {
      this.json = paramString;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getResultString()
  {
    return this.json;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.json);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\WakeuperResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */