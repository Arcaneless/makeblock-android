package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableByte
  extends BaseObservable
  implements Parcelable, Serializable
{
  public static final Parcelable.Creator<ObservableByte> CREATOR = new Parcelable.Creator()
  {
    public ObservableByte createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ObservableByte(paramAnonymousParcel.readByte());
    }
    
    public ObservableByte[] newArray(int paramAnonymousInt)
    {
      return new ObservableByte[paramAnonymousInt];
    }
  };
  static final long serialVersionUID = 1L;
  private byte mValue;
  
  public ObservableByte() {}
  
  public ObservableByte(byte paramByte)
  {
    this.mValue = paramByte;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public byte get()
  {
    return this.mValue;
  }
  
  public void set(byte paramByte)
  {
    if (paramByte != this.mValue)
    {
      this.mValue = paramByte;
      notifyChange();
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeByte(this.mValue);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ObservableByte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */