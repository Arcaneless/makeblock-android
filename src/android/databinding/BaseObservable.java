package android.databinding;

public class BaseObservable
  implements Observable
{
  private transient PropertyChangeRegistry mCallbacks;
  
  public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback paramOnPropertyChangedCallback)
  {
    try
    {
      if (this.mCallbacks == null) {
        this.mCallbacks = new PropertyChangeRegistry();
      }
      this.mCallbacks.add(paramOnPropertyChangedCallback);
      return;
    }
    finally {}
  }
  
  public void notifyChange()
  {
    try
    {
      if (this.mCallbacks == null) {
        return;
      }
      this.mCallbacks.notifyCallbacks(this, 0, null);
      return;
    }
    finally {}
  }
  
  public void notifyPropertyChanged(int paramInt)
  {
    try
    {
      if (this.mCallbacks == null) {
        return;
      }
      this.mCallbacks.notifyCallbacks(this, paramInt, null);
      return;
    }
    finally {}
  }
  
  public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback paramOnPropertyChangedCallback)
  {
    try
    {
      if (this.mCallbacks == null) {
        return;
      }
      this.mCallbacks.remove(paramOnPropertyChangedCallback);
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\BaseObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */