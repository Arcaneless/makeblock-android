package com.bumptech.glide.load.engine;

import android.os.Looper;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;

class EngineResource<Z>
  implements Resource<Z>
{
  private int acquired;
  private final boolean isCacheable;
  private boolean isRecycled;
  private Key key;
  private ResourceListener listener;
  private final Resource<Z> resource;
  
  EngineResource(Resource<Z> paramResource, boolean paramBoolean)
  {
    this.resource = ((Resource)Preconditions.checkNotNull(paramResource));
    this.isCacheable = paramBoolean;
  }
  
  void acquire()
  {
    if (this.isRecycled) {
      throw new IllegalStateException("Cannot acquire a recycled resource");
    }
    if (!Looper.getMainLooper().equals(Looper.myLooper())) {
      throw new IllegalThreadStateException("Must call acquire on the main thread");
    }
    this.acquired += 1;
  }
  
  public Z get()
  {
    return (Z)this.resource.get();
  }
  
  public Class<Z> getResourceClass()
  {
    return this.resource.getResourceClass();
  }
  
  public int getSize()
  {
    return this.resource.getSize();
  }
  
  boolean isCacheable()
  {
    return this.isCacheable;
  }
  
  public void recycle()
  {
    if (this.acquired > 0) {
      throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
    }
    if (this.isRecycled) {
      throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
    }
    this.isRecycled = true;
    this.resource.recycle();
  }
  
  void release()
  {
    if (this.acquired <= 0) {
      throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
    }
    if (!Looper.getMainLooper().equals(Looper.myLooper())) {
      throw new IllegalThreadStateException("Must call release on the main thread");
    }
    int i = this.acquired - 1;
    this.acquired = i;
    if (i == 0) {
      this.listener.onResourceReleased(this.key, this);
    }
  }
  
  void setResourceListener(Key paramKey, ResourceListener paramResourceListener)
  {
    this.key = paramKey;
    this.listener = paramResourceListener;
  }
  
  public String toString()
  {
    return "EngineResource{isCacheable=" + this.isCacheable + ", listener=" + this.listener + ", key=" + this.key + ", acquired=" + this.acquired + ", isRecycled=" + this.isRecycled + ", resource=" + this.resource + '}';
  }
  
  static abstract interface ResourceListener
  {
    public abstract void onResourceReleased(Key paramKey, EngineResource<?> paramEngineResource);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\EngineResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */