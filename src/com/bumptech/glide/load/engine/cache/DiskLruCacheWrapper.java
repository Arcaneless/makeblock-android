package com.bumptech.glide.load.engine.cache;

import android.util.Log;
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.bumptech.glide.disklrucache.DiskLruCache.Editor;
import com.bumptech.glide.disklrucache.DiskLruCache.Value;
import com.bumptech.glide.load.Key;
import java.io.File;
import java.io.IOException;

public class DiskLruCacheWrapper
  implements DiskCache
{
  private static final int APP_VERSION = 1;
  private static final String TAG = "DiskLruCacheWrapper";
  private static final int VALUE_COUNT = 1;
  private static DiskLruCacheWrapper wrapper = null;
  private final File directory;
  private DiskLruCache diskLruCache;
  private final int maxSize;
  private final SafeKeyGenerator safeKeyGenerator;
  private final DiskCacheWriteLocker writeLocker = new DiskCacheWriteLocker();
  
  protected DiskLruCacheWrapper(File paramFile, int paramInt)
  {
    this.directory = paramFile;
    this.maxSize = paramInt;
    this.safeKeyGenerator = new SafeKeyGenerator();
  }
  
  public static DiskCache get(File paramFile, int paramInt)
  {
    try
    {
      if (wrapper == null) {
        wrapper = new DiskLruCacheWrapper(paramFile, paramInt);
      }
      paramFile = wrapper;
      return paramFile;
    }
    finally {}
  }
  
  private DiskLruCache getDiskCache()
    throws IOException
  {
    try
    {
      if (this.diskLruCache == null) {
        this.diskLruCache = DiskLruCache.open(this.directory, 1, 1, this.maxSize);
      }
      DiskLruCache localDiskLruCache = this.diskLruCache;
      return localDiskLruCache;
    }
    finally {}
  }
  
  private void resetDiskCache()
  {
    try
    {
      this.diskLruCache = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void clear()
  {
    try
    {
      getDiskCache().delete();
      resetDiskCache();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
          Log.w("DiskLruCacheWrapper", "Unable to clear disk cache", localIOException);
        }
      }
    }
    finally {}
  }
  
  public void delete(Key paramKey)
  {
    paramKey = this.safeKeyGenerator.getSafeKey(paramKey);
    try
    {
      getDiskCache().remove(paramKey);
      return;
    }
    catch (IOException paramKey)
    {
      while (!Log.isLoggable("DiskLruCacheWrapper", 5)) {}
      Log.w("DiskLruCacheWrapper", "Unable to delete from disk cache", paramKey);
    }
  }
  
  public File get(Key paramKey)
  {
    Object localObject2 = this.safeKeyGenerator.getSafeKey(paramKey);
    if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
      Log.v("DiskLruCacheWrapper", "Get: Obtained: " + (String)localObject2 + " for for Key: " + paramKey);
    }
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        localObject2 = getDiskCache().get((String)localObject2);
      }
      catch (IOException localIOException)
      {
        paramKey = (Key)localObject1;
        if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
          continue;
        }
        Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", localIOException);
        return null;
      }
      paramKey = ((DiskLruCache.Value)localObject2).getFile(0);
      return paramKey;
      paramKey = (Key)localObject1;
      if (localIOException == null) {}
    }
  }
  
  public void put(Key paramKey, DiskCache.Writer paramWriter)
  {
    this.writeLocker.acquire(paramKey);
    for (;;)
    {
      Object localObject;
      try
      {
        str = this.safeKeyGenerator.getSafeKey(paramKey);
        if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
          Log.v("DiskLruCacheWrapper", "Put: Obtained: " + str + " for for Key: " + paramKey);
        }
      }
      finally
      {
        try
        {
          String str;
          DiskLruCache.Value localValue;
          if (paramWriter.write(((DiskLruCache.Editor)localObject).getFile(0))) {
            ((DiskLruCache.Editor)localObject).commit();
          }
          ((DiskLruCache.Editor)localObject).abortUnlessCommitted();
          continue;
        }
        finally
        {
          ((DiskLruCache.Editor)localObject).abortUnlessCommitted();
        }
        paramWriter = finally;
        this.writeLocker.release(paramKey);
      }
      try
      {
        localObject = getDiskCache();
        localValue = ((DiskLruCache)localObject).get(str);
        if (localValue != null)
        {
          this.writeLocker.release(paramKey);
          return;
        }
        localObject = ((DiskLruCache)localObject).edit(str);
      }
      catch (IOException paramWriter)
      {
        if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
          continue;
        }
        Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", paramWriter);
        this.writeLocker.release(paramKey);
        return;
      }
      throw new IllegalStateException("Had two simultaneous puts for: " + str);
      if (localObject != null) {}
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\cache\DiskLruCacheWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */