package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UnderstanderResult
  implements Parcelable
{
  public static final Parcelable.Creator<UnderstanderResult> CREATOR = new Parcelable.Creator()
  {
    public UnderstanderResult a(Parcel paramAnonymousParcel)
    {
      return new UnderstanderResult(paramAnonymousParcel);
    }
    
    public UnderstanderResult[] a(int paramAnonymousInt)
    {
      return new UnderstanderResult[paramAnonymousInt];
    }
  };
  private String a = "";
  
  public UnderstanderResult(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
  }
  
  public UnderstanderResult(String paramString)
  {
    if (paramString != null) {
      this.a = paramString;
    }
  }
  
  public int describeContents()
  {
    return 0;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\UnderstanderResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */