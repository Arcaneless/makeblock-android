package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LruBitmapPool
  implements BitmapPool
{
  private static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.ARGB_8888;
  private static final String TAG = "LruBitmapPool";
  private final Set<Bitmap.Config> allowedConfigs;
  private int currentSize;
  private int evictions;
  private int hits;
  private final int initialMaxSize;
  private int maxSize;
  private int misses;
  private int puts;
  private final LruPoolStrategy strategy;
  private final BitmapTracker tracker;
  
  public LruBitmapPool(int paramInt)
  {
    this(paramInt, getDefaultStrategy(), getDefaultAllowedConfigs());
  }
  
  LruBitmapPool(int paramInt, LruPoolStrategy paramLruPoolStrategy, Set<Bitmap.Config> paramSet)
  {
    this.initialMaxSize = paramInt;
    this.maxSize = paramInt;
    this.strategy = paramLruPoolStrategy;
    this.allowedConfigs = paramSet;
    this.tracker = new NullBitmapTracker();
  }
  
  public LruBitmapPool(int paramInt, Set<Bitmap.Config> paramSet)
  {
    this(paramInt, getDefaultStrategy(), paramSet);
  }
  
  private void dump()
  {
    if (Log.isLoggable("LruBitmapPool", 2)) {
      dumpUnchecked();
    }
  }
  
  private void dumpUnchecked()
  {
    Log.v("LruBitmapPool", "Hits=" + this.hits + ", misses=" + this.misses + ", puts=" + this.puts + ", evictions=" + this.evictions + ", currentSize=" + this.currentSize + ", maxSize=" + this.maxSize + "\nStrategy=" + this.strategy);
  }
  
  private void evict()
  {
    trimToSize(this.maxSize);
  }
  
  private static Set<Bitmap.Config> getDefaultAllowedConfigs()
  {
    HashSet localHashSet = new HashSet();
    localHashSet.addAll(Arrays.asList(Bitmap.Config.values()));
    if (Build.VERSION.SDK_INT >= 19) {
      localHashSet.add(null);
    }
    return Collections.unmodifiableSet(localHashSet);
  }
  
  private static LruPoolStrategy getDefaultStrategy()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return new SizeConfigStrategy();
    }
    return new AttributeStrategy();
  }
  
  @Nullable
  private Bitmap getDirtyOrNull(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    label202:
    for (;;)
    {
      try
      {
        LruPoolStrategy localLruPoolStrategy = this.strategy;
        Object localObject;
        if (paramConfig != null)
        {
          localObject = paramConfig;
          localObject = localLruPoolStrategy.get(paramInt1, paramInt2, (Bitmap.Config)localObject);
          break label202;
          if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "Missing bitmap=" + this.strategy.logBitmap(paramInt1, paramInt2, paramConfig));
          }
          this.misses += 1;
          if (Log.isLoggable("LruBitmapPool", 2)) {
            Log.v("LruBitmapPool", "Get bitmap=" + this.strategy.logBitmap(paramInt1, paramInt2, paramConfig));
          }
          dump();
          return (Bitmap)localObject;
        }
        else
        {
          localObject = DEFAULT_CONFIG;
          continue;
          this.hits += 1;
          this.currentSize -= this.strategy.getSize((Bitmap)localObject);
          this.tracker.remove((Bitmap)localObject);
          normalize((Bitmap)localObject);
          continue;
        }
        if (localObject != null) {}
      }
      finally {}
    }
  }
  
  @TargetApi(19)
  private static void maybeSetPreMultiplied(Bitmap paramBitmap)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      paramBitmap.setPremultiplied(true);
    }
  }
  
  private static void normalize(Bitmap paramBitmap)
  {
    paramBitmap.setHasAlpha(true);
    maybeSetPreMultiplied(paramBitmap);
  }
  
  private void trimToSize(int paramInt)
  {
    for (;;)
    {
      Bitmap localBitmap;
      try
      {
        if (this.currentSize > paramInt)
        {
          localBitmap = this.strategy.removeLast();
          break label150;
          if (Log.isLoggable("LruBitmapPool", 5))
          {
            Log.w("LruBitmapPool", "Size mismatch, resetting");
            dumpUnchecked();
          }
          this.currentSize = 0;
        }
        return;
      }
      finally {}
      this.tracker.remove(localBitmap);
      this.currentSize -= this.strategy.getSize(localBitmap);
      this.evictions += 1;
      if (Log.isLoggable("LruBitmapPool", 3)) {
        Log.d("LruBitmapPool", "Evicting bitmap=" + this.strategy.logBitmap(localBitmap));
      }
      dump();
      localBitmap.recycle();
      continue;
      label150:
      if (localObject != null) {}
    }
  }
  
  public void clearMemory()
  {
    if (Log.isLoggable("LruBitmapPool", 3)) {
      Log.d("LruBitmapPool", "clearMemory");
    }
    trimToSize(0);
  }
  
  @NonNull
  public Bitmap get(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    Bitmap localBitmap = getDirtyOrNull(paramInt1, paramInt2, paramConfig);
    if (localBitmap != null)
    {
      localBitmap.eraseColor(0);
      return localBitmap;
    }
    return Bitmap.createBitmap(paramInt1, paramInt2, paramConfig);
  }
  
  @NonNull
  public Bitmap getDirty(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    Bitmap localBitmap2 = getDirtyOrNull(paramInt1, paramInt2, paramConfig);
    Bitmap localBitmap1 = localBitmap2;
    if (localBitmap2 == null) {
      localBitmap1 = Bitmap.createBitmap(paramInt1, paramInt2, paramConfig);
    }
    return localBitmap1;
  }
  
  public int getMaxSize()
  {
    return this.maxSize;
  }
  
  public void put(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      try
      {
        throw new NullPointerException("Bitmap must not be null");
      }
      finally {}
    }
    if (paramBitmap.isRecycled()) {
      throw new IllegalStateException("Cannot pool recycled bitmap");
    }
    if ((!paramBitmap.isMutable()) || (this.strategy.getSize(paramBitmap) > this.maxSize) || (!this.allowedConfigs.contains(paramBitmap.getConfig())))
    {
      if (Log.isLoggable("LruBitmapPool", 2)) {
        Log.v("LruBitmapPool", "Reject bitmap from pool, bitmap: " + this.strategy.logBitmap(paramBitmap) + ", is mutable: " + paramBitmap.isMutable() + ", is allowed config: " + this.allowedConfigs.contains(paramBitmap.getConfig()));
      }
      paramBitmap.recycle();
    }
    for (;;)
    {
      return;
      int i = this.strategy.getSize(paramBitmap);
      this.strategy.put(paramBitmap);
      this.tracker.add(paramBitmap);
      this.puts += 1;
      this.currentSize += i;
      if (Log.isLoggable("LruBitmapPool", 2)) {
        Log.v("LruBitmapPool", "Put bitmap in pool=" + this.strategy.logBitmap(paramBitmap));
      }
      dump();
      evict();
    }
  }
  
  public void setSizeMultiplier(float paramFloat)
  {
    try
    {
      this.maxSize = Math.round(this.initialMaxSize * paramFloat);
      evict();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  @SuppressLint({"InlinedApi"})
  public void trimMemory(int paramInt)
  {
    if (Log.isLoggable("LruBitmapPool", 3)) {
      Log.d("LruBitmapPool", "trimMemory, level=" + paramInt);
    }
    if (paramInt >= 40) {
      clearMemory();
    }
    while (paramInt < 20) {
      return;
    }
    trimToSize(this.maxSize / 2);
  }
  
  private static abstract interface BitmapTracker
  {
    public abstract void add(Bitmap paramBitmap);
    
    public abstract void remove(Bitmap paramBitmap);
  }
  
  private static class NullBitmapTracker
    implements LruBitmapPool.BitmapTracker
  {
    public void add(Bitmap paramBitmap) {}
    
    public void remove(Bitmap paramBitmap) {}
  }
  
  private static class ThrowingBitmapTracker
    implements LruBitmapPool.BitmapTracker
  {
    private final Set<Bitmap> bitmaps = Collections.synchronizedSet(new HashSet());
    
    public void add(Bitmap paramBitmap)
    {
      if (this.bitmaps.contains(paramBitmap)) {
        throw new IllegalStateException("Can't add already added bitmap: " + paramBitmap + " [" + paramBitmap.getWidth() + "x" + paramBitmap.getHeight() + "]");
      }
      this.bitmaps.add(paramBitmap);
    }
    
    public void remove(Bitmap paramBitmap)
    {
      if (!this.bitmaps.contains(paramBitmap)) {
        throw new IllegalStateException("Cannot remove bitmap not in tracker");
      }
      this.bitmaps.remove(paramBitmap);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\bitmap_recycle\LruBitmapPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */