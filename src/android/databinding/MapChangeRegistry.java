package android.databinding;

public class MapChangeRegistry
  extends CallbackRegistry<ObservableMap.OnMapChangedCallback, ObservableMap, Object>
{
  private static CallbackRegistry.NotifierCallback<ObservableMap.OnMapChangedCallback, ObservableMap, Object> NOTIFIER_CALLBACK = new CallbackRegistry.NotifierCallback()
  {
    public void onNotifyCallback(ObservableMap.OnMapChangedCallback paramAnonymousOnMapChangedCallback, ObservableMap paramAnonymousObservableMap, int paramAnonymousInt, Object paramAnonymousObject)
    {
      paramAnonymousOnMapChangedCallback.onMapChanged(paramAnonymousObservableMap, paramAnonymousObject);
    }
  };
  
  public MapChangeRegistry()
  {
    super(NOTIFIER_CALLBACK);
  }
  
  public void notifyChange(ObservableMap paramObservableMap, Object paramObject)
  {
    notifyCallbacks(paramObservableMap, 0, paramObject);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\MapChangeRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */