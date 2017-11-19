package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableShort
  extends BaseObservable
  implements Parcelable, Serializable
{
  public static final Parcelable.Creator<ObservableShort> CREATOR = new Parcelable.Creator()
  {
    public ObservableShort createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ObservableShort((short)paramAnonymousParcel.readInt());
    }
    
    public ObservableShort[] newArray(int paramAnonymousInt)
    {
      return new ObservableShort[paramAnonymousInt];
    }
  };
  static final long serialVersionUID = 1L;
  private short mValue;
  
  public ObservableShort() {}
  
  public ObservableShort(short paramShort)
  {
    this.mValue = paramShort;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public short get()
  {
    return this.mValue;
  }
  
  public void set(short paramShort)
  {
    if (paramShort != this.mValue)
    {
      this.mValue = paramShort;
      notifyChange();
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.mValue);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ObservableShort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */