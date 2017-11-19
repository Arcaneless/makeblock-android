package android.arch.lifecycle;

import android.arch.core.executor.AppToolkitTaskExecutor;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract class ComputableLiveData<T>
{
  private AtomicBoolean mComputing = new AtomicBoolean(false);
  private AtomicBoolean mInvalid = new AtomicBoolean(true);
  @VisibleForTesting
  final Runnable mInvalidationRunnable = new Runnable()
  {
    @MainThread
    public void run()
    {
      boolean bool = ComputableLiveData.this.mLiveData.hasActiveObservers();
      if ((ComputableLiveData.this.mInvalid.compareAndSet(false, true)) && (bool)) {
        AppToolkitTaskExecutor.getInstance().executeOnDiskIO(ComputableLiveData.this.mRefreshRunnable);
      }
    }
  };
  private final LiveData<T> mLiveData = new LiveData()
  {
    protected void onActive()
    {
      AppToolkitTaskExecutor.getInstance().executeOnDiskIO(ComputableLiveData.this.mRefreshRunnable);
    }
  };
  @VisibleForTesting
  final Runnable mRefreshRunnable = new Runnable()
  {
    @WorkerThread
    public void run()
    {
      int i = 0;
      int j = 0;
      if (ComputableLiveData.this.mComputing.compareAndSet(false, true))
      {
        localObject1 = null;
        i = j;
      }
      try
      {
        label23:
        if (!ComputableLiveData.this.mInvalid.compareAndSet(true, false)) {
          break label114;
        }
      }
      finally
      {
        label41:
        ComputableLiveData.this.mComputing.set(false);
      }
      Object localObject1 = ComputableLiveData.this.compute();
      for (;;)
      {
        ComputableLiveData.this.mLiveData.postValue(localObject1);
        label114:
        do
        {
          ComputableLiveData.this.mComputing.set(false);
          if ((i != 0) && (ComputableLiveData.this.mInvalid.get())) {
            break;
          }
          return;
          i = 1;
          break label41;
          break label23;
        } while (i == 0);
      }
    }
  };
  
  @WorkerThread
  protected abstract T compute();
  
  @NonNull
  public LiveData<T> getLiveData()
  {
    return this.mLiveData;
  }
  
  public void invalidate()
  {
    AppToolkitTaskExecutor.getInstance().executeOnMainThread(this.mInvalidationRunnable);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\lifecycle\ComputableLiveData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */