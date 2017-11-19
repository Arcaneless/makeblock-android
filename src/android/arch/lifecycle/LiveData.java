package android.arch.lifecycle;

import android.arch.core.executor.AppToolkitTaskExecutor;
import android.arch.core.internal.SafeIterableMap;
import android.arch.core.internal.SafeIterableMap.IteratorWithAdditions;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class LiveData<T>
{
  private static final LifecycleOwner ALWAYS_ON = new LifecycleOwner()
  {
    private LifecycleRegistry mRegistry = init();
    
    private LifecycleRegistry init()
    {
      LifecycleRegistry localLifecycleRegistry = new LifecycleRegistry(this);
      localLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
      localLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
      localLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
      return localLifecycleRegistry;
    }
    
    public Lifecycle getLifecycle()
    {
      return this.mRegistry;
    }
  };
  private static final Object NOT_SET = new Object();
  static final int START_VERSION = -1;
  private int mActiveCount = 0;
  private volatile Object mData = NOT_SET;
  private final Object mDataLock = new Object();
  private boolean mDispatchInvalidated;
  private boolean mDispatchingValue;
  private SafeIterableMap<Observer<T>, LiveData<T>.LifecycleBoundObserver> mObservers = new SafeIterableMap();
  private volatile Object mPendingData = NOT_SET;
  private final Runnable mPostValueRunnable = new Runnable()
  {
    public void run()
    {
      synchronized (LiveData.this.mDataLock)
      {
        Object localObject2 = LiveData.this.mPendingData;
        LiveData.access$102(LiveData.this, LiveData.NOT_SET);
        LiveData.this.setValue(localObject2);
        return;
      }
    }
  };
  private int mVersion = -1;
  
  private void assertMainThread(String paramString)
  {
    if (!AppToolkitTaskExecutor.getInstance().isMainThread()) {
      throw new IllegalStateException("Cannot invoke " + paramString + " on a background" + " thread");
    }
  }
  
  private void considerNotify(LiveData<T>.LifecycleBoundObserver paramLiveData)
  {
    if (!paramLiveData.active) {}
    while ((!isActiveState(paramLiveData.owner.getLifecycle().getCurrentState())) || (paramLiveData.lastVersion >= this.mVersion)) {
      return;
    }
    paramLiveData.lastVersion = this.mVersion;
    paramLiveData.observer.onChanged(this.mData);
  }
  
  private void dispatchingValue(@Nullable LiveData<T>.LifecycleBoundObserver paramLiveData)
  {
    if (this.mDispatchingValue)
    {
      this.mDispatchInvalidated = true;
      return;
    }
    this.mDispatchingValue = true;
    this.mDispatchInvalidated = false;
    LiveData<T>.LifecycleBoundObserver localLiveData;
    if (paramLiveData != null)
    {
      considerNotify(paramLiveData);
      localLiveData = null;
    }
    for (;;)
    {
      paramLiveData = localLiveData;
      if (this.mDispatchInvalidated) {
        break;
      }
      this.mDispatchingValue = false;
      return;
      SafeIterableMap.IteratorWithAdditions localIteratorWithAdditions = this.mObservers.iteratorWithAdditions();
      do
      {
        localLiveData = paramLiveData;
        if (!localIteratorWithAdditions.hasNext()) {
          break;
        }
        considerNotify((LifecycleBoundObserver)((Map.Entry)localIteratorWithAdditions.next()).getValue());
      } while (!this.mDispatchInvalidated);
      localLiveData = paramLiveData;
    }
  }
  
  static boolean isActiveState(Lifecycle.State paramState)
  {
    return paramState.isAtLeast(Lifecycle.State.STARTED);
  }
  
  @Nullable
  public T getValue()
  {
    Object localObject = this.mData;
    if (localObject != NOT_SET) {
      return (T)localObject;
    }
    return null;
  }
  
  int getVersion()
  {
    return this.mVersion;
  }
  
  public boolean hasActiveObservers()
  {
    return this.mActiveCount > 0;
  }
  
  public boolean hasObservers()
  {
    return this.mObservers.size() > 0;
  }
  
  @MainThread
  public void observe(LifecycleOwner paramLifecycleOwner, Observer<T> paramObserver)
  {
    if (paramLifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {}
    LifecycleBoundObserver localLifecycleBoundObserver;
    do
    {
      return;
      localLifecycleBoundObserver = new LifecycleBoundObserver(paramLifecycleOwner, paramObserver);
      paramObserver = (LifecycleBoundObserver)this.mObservers.putIfAbsent(paramObserver, localLifecycleBoundObserver);
      if ((paramObserver != null) && (paramObserver.owner != localLifecycleBoundObserver.owner)) {
        throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
      }
    } while (paramObserver != null);
    paramLifecycleOwner.getLifecycle().addObserver(localLifecycleBoundObserver);
    localLifecycleBoundObserver.activeStateChanged(isActiveState(paramLifecycleOwner.getLifecycle().getCurrentState()));
  }
  
  @MainThread
  public void observeForever(Observer<T> paramObserver)
  {
    observe(ALWAYS_ON, paramObserver);
  }
  
  protected void onActive() {}
  
  protected void onInactive() {}
  
  protected void postValue(T paramT)
  {
    for (;;)
    {
      synchronized (this.mDataLock)
      {
        if (this.mPendingData != NOT_SET) {
          break label50;
        }
        i = 1;
        this.mPendingData = paramT;
      }
      while (i != 0)
      {
        AppToolkitTaskExecutor.getInstance().postToMainThread(this.mPostValueRunnable);
        return;
      }
      return;
      label50:
      int i = 0;
    }
  }
  
  @MainThread
  public void removeObserver(Observer<T> paramObserver)
  {
    assertMainThread("removeObserver");
    paramObserver = (LifecycleBoundObserver)this.mObservers.remove(paramObserver);
    if (paramObserver == null) {
      return;
    }
    paramObserver.owner.getLifecycle().removeObserver(paramObserver);
    paramObserver.activeStateChanged(false);
  }
  
  @MainThread
  public void removeObservers(LifecycleOwner paramLifecycleOwner)
  {
    assertMainThread("removeObservers");
    Iterator localIterator = this.mObservers.iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (((LifecycleBoundObserver)localEntry.getValue()).owner == paramLifecycleOwner) {
        removeObserver((Observer)localEntry.getKey());
      }
    }
  }
  
  @MainThread
  protected void setValue(T paramT)
  {
    assertMainThread("setValue");
    this.mVersion += 1;
    this.mData = paramT;
    dispatchingValue(null);
  }
  
  class LifecycleBoundObserver
    implements LifecycleObserver
  {
    public boolean active;
    public int lastVersion = -1;
    public final Observer<T> observer;
    public final LifecycleOwner owner;
    
    LifecycleBoundObserver(Observer<T> paramObserver)
    {
      this.owner = paramObserver;
      Observer localObserver;
      this.observer = localObserver;
    }
    
    void activeStateChanged(boolean paramBoolean)
    {
      int j = 1;
      if (paramBoolean == this.active) {
        return;
      }
      this.active = paramBoolean;
      int i;
      label28:
      LiveData localLiveData;
      int k;
      if (LiveData.this.mActiveCount == 0)
      {
        i = 1;
        localLiveData = LiveData.this;
        k = localLiveData.mActiveCount;
        if (!this.active) {
          break label121;
        }
      }
      for (;;)
      {
        LiveData.access$302(localLiveData, j + k);
        if ((i != 0) && (this.active)) {
          LiveData.this.onActive();
        }
        if ((LiveData.this.mActiveCount == 0) && (!this.active)) {
          LiveData.this.onInactive();
        }
        if (!this.active) {
          break;
        }
        LiveData.this.dispatchingValue(this);
        return;
        i = 0;
        break label28;
        label121:
        j = -1;
      }
    }
    
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onStateChange()
    {
      if (this.owner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED)
      {
        LiveData.this.removeObserver(this.observer);
        return;
      }
      activeStateChanged(LiveData.isActiveState(this.owner.getLifecycle().getCurrentState()));
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\lifecycle\LiveData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */