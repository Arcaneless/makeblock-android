package android.arch.lifecycle;

public class LiveData_LifecycleBoundObserver_LifecycleAdapter
  implements GenericLifecycleObserver
{
  final LiveData.LifecycleBoundObserver mReceiver;
  
  LiveData_LifecycleBoundObserver_LifecycleAdapter(LiveData.LifecycleBoundObserver paramLifecycleBoundObserver)
  {
    this.mReceiver = paramLifecycleBoundObserver;
  }
  
  public void onStateChanged(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent)
  {
    this.mReceiver.onStateChange();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\lifecycle\LiveData_LifecycleBoundObserver_LifecycleAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */