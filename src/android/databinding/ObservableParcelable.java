package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableParcelable<T extends Parcelable>
  extends ObservableField<T>
  implements Parcelable, Serializable
{
  public static final Parcelable.Creator<ObservableParcelable> CREATOR = new Parcelable.Creator()
  {
    public ObservableParcelable createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ObservableParcelable(paramAnonymousParcel.readParcelable(getClass().getClassLoader()));
    }
    
    public ObservableParcelable[] newArray(int paramAnonymousInt)
    {
      return new ObservableParcelable[paramAnonymousInt];
    }
  };
  static final long serialVersionUID = 1L;
  
  public ObservableParcelable() {}
  
  public ObservableParcelable(T paramT)
  {
    super(paramT);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable((Parcelable)get(), 0);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ObservableParcelable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */