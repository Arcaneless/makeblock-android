package android.databinding;

import android.support.v4.util.Pools.SynchronizedPool;

public class ListChangeRegistry
  extends CallbackRegistry<ObservableList.OnListChangedCallback, ObservableList, ListChanges>
{
  private static final int ALL = 0;
  private static final int CHANGED = 1;
  private static final int INSERTED = 2;
  private static final int MOVED = 3;
  private static final CallbackRegistry.NotifierCallback<ObservableList.OnListChangedCallback, ObservableList, ListChanges> NOTIFIER_CALLBACK = new CallbackRegistry.NotifierCallback()
  {
    public void onNotifyCallback(ObservableList.OnListChangedCallback paramAnonymousOnListChangedCallback, ObservableList paramAnonymousObservableList, int paramAnonymousInt, ListChangeRegistry.ListChanges paramAnonymousListChanges)
    {
      switch (paramAnonymousInt)
      {
      default: 
        paramAnonymousOnListChangedCallback.onChanged(paramAnonymousObservableList);
        return;
      case 1: 
        paramAnonymousOnListChangedCallback.onItemRangeChanged(paramAnonymousObservableList, paramAnonymousListChanges.start, paramAnonymousListChanges.count);
        return;
      case 2: 
        paramAnonymousOnListChangedCallback.onItemRangeInserted(paramAnonymousObservableList, paramAnonymousListChanges.start, paramAnonymousListChanges.count);
        return;
      case 3: 
        paramAnonymousOnListChangedCallback.onItemRangeMoved(paramAnonymousObservableList, paramAnonymousListChanges.start, paramAnonymousListChanges.to, paramAnonymousListChanges.count);
        return;
      }
      paramAnonymousOnListChangedCallback.onItemRangeRemoved(paramAnonymousObservableList, paramAnonymousListChanges.start, paramAnonymousListChanges.count);
    }
  };
  private static final int REMOVED = 4;
  private static final Pools.SynchronizedPool<ListChanges> sListChanges = new Pools.SynchronizedPool(10);
  
  public ListChangeRegistry()
  {
    super(NOTIFIER_CALLBACK);
  }
  
  private static ListChanges acquire(int paramInt1, int paramInt2, int paramInt3)
  {
    ListChanges localListChanges2 = (ListChanges)sListChanges.acquire();
    ListChanges localListChanges1 = localListChanges2;
    if (localListChanges2 == null) {
      localListChanges1 = new ListChanges();
    }
    localListChanges1.start = paramInt1;
    localListChanges1.to = paramInt2;
    localListChanges1.count = paramInt3;
    return localListChanges1;
  }
  
  public void notifyCallbacks(ObservableList paramObservableList, int paramInt, ListChanges paramListChanges)
  {
    for (;;)
    {
      try
      {
        super.notifyCallbacks(paramObservableList, paramInt, paramListChanges);
      }
      finally {}
      sListChanges.release(paramListChanges);
      return;
      if (paramListChanges == null) {}
    }
  }
  
  public void notifyChanged(ObservableList paramObservableList)
  {
    notifyCallbacks(paramObservableList, 0, null);
  }
  
  public void notifyChanged(ObservableList paramObservableList, int paramInt1, int paramInt2)
  {
    notifyCallbacks(paramObservableList, 1, acquire(paramInt1, 0, paramInt2));
  }
  
  public void notifyInserted(ObservableList paramObservableList, int paramInt1, int paramInt2)
  {
    notifyCallbacks(paramObservableList, 2, acquire(paramInt1, 0, paramInt2));
  }
  
  public void notifyMoved(ObservableList paramObservableList, int paramInt1, int paramInt2, int paramInt3)
  {
    notifyCallbacks(paramObservableList, 3, acquire(paramInt1, paramInt2, paramInt3));
  }
  
  public void notifyRemoved(ObservableList paramObservableList, int paramInt1, int paramInt2)
  {
    notifyCallbacks(paramObservableList, 4, acquire(paramInt1, 0, paramInt2));
  }
  
  static class ListChanges
  {
    public int count;
    public int start;
    public int to;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ListChangeRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */