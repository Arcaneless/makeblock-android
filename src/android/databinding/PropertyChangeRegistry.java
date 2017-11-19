package android.databinding;

public class PropertyChangeRegistry
  extends CallbackRegistry<Observable.OnPropertyChangedCallback, Observable, Void>
{
  private static final CallbackRegistry.NotifierCallback<Observable.OnPropertyChangedCallback, Observable, Void> NOTIFIER_CALLBACK = new CallbackRegistry.NotifierCallback()
  {
    public void onNotifyCallback(Observable.OnPropertyChangedCallback paramAnonymousOnPropertyChangedCallback, Observable paramAnonymousObservable, int paramAnonymousInt, Void paramAnonymousVoid)
    {
      paramAnonymousOnPropertyChangedCallback.onPropertyChanged(paramAnonymousObservable, paramAnonymousInt);
    }
  };
  
  public PropertyChangeRegistry()
  {
    super(NOTIFIER_CALLBACK);
  }
  
  public void notifyChange(Observable paramObservable, int paramInt)
  {
    notifyCallbacks(paramObservable, paramInt, null);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\PropertyChangeRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */