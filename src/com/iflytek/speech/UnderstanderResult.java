package com.iflytek.speech;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UnderstanderResult
  implements Parcelable
{
  public static final Parcelable.Creator<UnderstanderResult> CREATOR = new Parcelable.Creator()
  {
    public UnderstanderResult createFromParcel(Parcel paramAnonymousParcel)
    {
      return new UnderstanderResult(paramAnonymousParcel);
    }
    
    public UnderstanderResult[] newArray(int paramAnonymousInt)
    {
      return new UnderstanderResult[paramAnonymousInt];
    }
  };
  private String mXml = "";
  
  public UnderstanderResult(Parcel paramParcel)
  {
    this.mXml = paramParcel.readString();
  }
  
  public UnderstanderResult(String paramString)
  {
    if (paramString != null) {
      this.mXml = paramString;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getResultString()
  {
    return this.mXml;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mXml);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\UnderstanderResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */