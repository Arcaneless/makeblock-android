package com.bumptech.glide;

import android.content.Context;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCache.Factory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator.Builder;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory;
import com.bumptech.glide.request.RequestOptions;

public final class GlideBuilder
{
  private ArrayPool arrayPool;
  private BitmapPool bitmapPool;
  private ConnectivityMonitorFactory connectivityMonitorFactory;
  private RequestOptions defaultRequestOptions = new RequestOptions();
  private GlideExecutor diskCacheExecutor;
  private DiskCache.Factory diskCacheFactory;
  private Engine engine;
  private int logLevel = 4;
  private MemoryCache memoryCache;
  private MemorySizeCalculator memorySizeCalculator;
  @Nullable
  private RequestManagerRetriever.RequestManagerFactory requestManagerFactory;
  private GlideExecutor sourceExecutor;
  
  public Glide build(Context paramContext)
  {
    if (this.sourceExecutor == null) {
      this.sourceExecutor = GlideExecutor.newSourceExecutor();
    }
    if (this.diskCacheExecutor == null) {
      this.diskCacheExecutor = GlideExecutor.newDiskCacheExecutor();
    }
    if (this.memorySizeCalculator == null) {
      this.memorySizeCalculator = new MemorySizeCalculator.Builder(paramContext).build();
    }
    if (this.connectivityMonitorFactory == null) {
      this.connectivityMonitorFactory = new DefaultConnectivityMonitorFactory();
    }
    if (this.bitmapPool == null) {
      this.bitmapPool = new LruBitmapPool(this.memorySizeCalculator.getBitmapPoolSize());
    }
    if (this.arrayPool == null) {
      this.arrayPool = new LruArrayPool(this.memorySizeCalculator.getArrayPoolSizeInBytes());
    }
    if (this.memoryCache == null) {
      this.memoryCache = new LruResourceCache(this.memorySizeCalculator.getMemoryCacheSize());
    }
    if (this.diskCacheFactory == null) {
      this.diskCacheFactory = new InternalCacheDiskCacheFactory(paramContext);
    }
    if (this.engine == null) {
      this.engine = new Engine(this.memoryCache, this.diskCacheFactory, this.diskCacheExecutor, this.sourceExecutor, GlideExecutor.newUnlimitedSourceExecutor());
    }
    RequestManagerRetriever localRequestManagerRetriever = new RequestManagerRetriever(this.requestManagerFactory);
    return new Glide(paramContext, this.engine, this.memoryCache, this.bitmapPool, this.arrayPool, localRequestManagerRetriever, this.connectivityMonitorFactory, this.logLevel, this.defaultRequestOptions.lock());
  }
  
  public GlideBuilder setArrayPool(ArrayPool paramArrayPool)
  {
    this.arrayPool = paramArrayPool;
    return this;
  }
  
  public GlideBuilder setBitmapPool(BitmapPool paramBitmapPool)
  {
    this.bitmapPool = paramBitmapPool;
    return this;
  }
  
  public GlideBuilder setConnectivityMonitorFactory(ConnectivityMonitorFactory paramConnectivityMonitorFactory)
  {
    this.connectivityMonitorFactory = paramConnectivityMonitorFactory;
    return this;
  }
  
  @Deprecated
  public GlideBuilder setDecodeFormat(DecodeFormat paramDecodeFormat)
  {
    this.defaultRequestOptions.apply(new RequestOptions().format(paramDecodeFormat));
    return this;
  }
  
  public GlideBuilder setDefaultRequestOptions(RequestOptions paramRequestOptions)
  {
    this.defaultRequestOptions = paramRequestOptions;
    return this;
  }
  
  public GlideBuilder setDiskCache(DiskCache.Factory paramFactory)
  {
    this.diskCacheFactory = paramFactory;
    return this;
  }
  
  @Deprecated
  public GlideBuilder setDiskCache(final DiskCache paramDiskCache)
  {
    setDiskCache(new DiskCache.Factory()
    {
      public DiskCache build()
      {
        return paramDiskCache;
      }
    });
  }
  
  public GlideBuilder setDiskCacheExecutor(GlideExecutor paramGlideExecutor)
  {
    this.diskCacheExecutor = paramGlideExecutor;
    return this;
  }
  
  GlideBuilder setEngine(Engine paramEngine)
  {
    this.engine = paramEngine;
    return this;
  }
  
  public GlideBuilder setLogLevel(int paramInt)
  {
    if ((paramInt < 2) || (paramInt > 6)) {
      throw new IllegalArgumentException("Log level must be one of Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, or Log.ERROR");
    }
    this.logLevel = paramInt;
    return this;
  }
  
  public GlideBuilder setMemoryCache(MemoryCache paramMemoryCache)
  {
    this.memoryCache = paramMemoryCache;
    return this;
  }
  
  public GlideBuilder setMemorySizeCalculator(MemorySizeCalculator.Builder paramBuilder)
  {
    return setMemorySizeCalculator(paramBuilder.build());
  }
  
  public GlideBuilder setMemorySizeCalculator(MemorySizeCalculator paramMemorySizeCalculator)
  {
    this.memorySizeCalculator = paramMemorySizeCalculator;
    return this;
  }
  
  GlideBuilder setRequestManagerFactory(@Nullable RequestManagerRetriever.RequestManagerFactory paramRequestManagerFactory)
  {
    this.requestManagerFactory = paramRequestManagerFactory;
    return this;
  }
  
  public GlideBuilder setResizeExecutor(GlideExecutor paramGlideExecutor)
  {
    this.sourceExecutor = paramGlideExecutor;
    return this;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\GlideBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */