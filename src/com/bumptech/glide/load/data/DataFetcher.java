package com.bumptech.glide.load.data;

import android.support.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;

public abstract interface DataFetcher<T>
{
  public abstract void cancel();
  
  public abstract void cleanup();
  
  public abstract Class<T> getDataClass();
  
  public abstract DataSource getDataSource();
  
  public abstract void loadData(Priority paramPriority, DataCallback<? super T> paramDataCallback);
  
  public static abstract interface DataCallback<T>
  {
    public abstract void onDataReady(@Nullable T paramT);
    
    public abstract void onLoadFailed(Exception paramException);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\data\DataFetcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */