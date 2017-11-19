package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableInt
  extends BaseObservable
  implements Parcelable, Serializable
{
  public static final Parcelable.Creator<ObservableInt> CREATOR = new Parcelable.Creator()
  {
    public ObservableInt createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ObservableInt(paramAnonymousParcel.readInt());
    }
    
    public ObservableInt[] newArray(int paramAnonymousInt)
    {
      return new ObservableInt[paramAnonymousInt];
    }
  };
  static final long serialVersionUID = 1L;
  private int mValue;
  
  public ObservableInt() {}
  
  public ObservableInt(int paramInt)
  {
    this.mValue = paramInt;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int get()
  {
    return this.mValue;
  }
  
  public void set(int paramInt)
  {
    if (paramInt != this.mValue)
    {
      this.mValue = paramInt;
      notifyChange();
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.mValue);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ObservableInt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */