package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableFloat
  extends BaseObservable
  implements Parcelable, Serializable
{
  public static final Parcelable.Creator<ObservableFloat> CREATOR = new Parcelable.Creator()
  {
    public ObservableFloat createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ObservableFloat(paramAnonymousParcel.readFloat());
    }
    
    public ObservableFloat[] newArray(int paramAnonymousInt)
    {
      return new ObservableFloat[paramAnonymousInt];
    }
  };
  static final long serialVersionUID = 1L;
  private float mValue;
  
  public ObservableFloat() {}
  
  public ObservableFloat(float paramFloat)
  {
    this.mValue = paramFloat;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public float get()
  {
    return this.mValue;
  }
  
  public void set(float paramFloat)
  {
    if (paramFloat != this.mValue)
    {
      this.mValue = paramFloat;
      notifyChange();
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeFloat(this.mValue);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ObservableFloat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */