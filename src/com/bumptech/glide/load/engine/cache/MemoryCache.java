package com.bumptech.glide.load.engine.cache;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;

public abstract interface MemoryCache
{
  public abstract void clearMemory();
  
  public abstract int getCurrentSize();
  
  public abstract int getMaxSize();
  
  @Nullable
  public abstract Resource<?> put(Key paramKey, Resource<?> paramResource);
  
  @Nullable
  public abstract Resource<?> remove(Key paramKey);
  
  public abstract void setResourceRemovedListener(ResourceRemovedListener paramResourceRemovedListener);
  
  public abstract void setSizeMultiplier(float paramFloat);
  
  public abstract void trimMemory(int paramInt);
  
  public static abstract interface ResourceRemovedListener
  {
    public abstract void onResourceRemoved(Resource<?> paramResource);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\cache\MemoryCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */