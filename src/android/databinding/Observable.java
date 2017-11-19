package android.databinding;

public abstract interface Observable
{
  public abstract void addOnPropertyChangedCallback(OnPropertyChangedCallback paramOnPropertyChangedCallback);
  
  public abstract void removeOnPropertyChangedCallback(OnPropertyChangedCallback paramOnPropertyChangedCallback);
  
  public static abstract class OnPropertyChangedCallback
  {
    public abstract void onPropertyChanged(Observable paramObservable, int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\Observable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */