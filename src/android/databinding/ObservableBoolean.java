package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableBoolean
  extends BaseObservable
  implements Parcelable, Serializable
{
  public static final Parcelable.Creator<ObservableBoolean> CREATOR = new Parcelable.Creator()
  {
    public ObservableBoolean createFromParcel(Parcel paramAnonymousParcel)
    {
      boolean bool = true;
      if (paramAnonymousParcel.readInt() == 1) {}
      for (;;)
      {
        return new ObservableBoolean(bool);
        bool = false;
      }
    }
    
    public ObservableBoolean[] newArray(int paramAnonymousInt)
    {
      return new ObservableBoolean[paramAnonymousInt];
    }
  };
  static final long serialVersionUID = 1L;
  private boolean mValue;
  
  public ObservableBoolean() {}
  
  public ObservableBoolean(boolean paramBoolean)
  {
    this.mValue = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean get()
  {
    return this.mValue;
  }
  
  public void set(boolean paramBoolean)
  {
    if (paramBoolean != this.mValue)
    {
      this.mValue = paramBoolean;
      notifyChange();
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (this.mValue) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      return;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ObservableBoolean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */