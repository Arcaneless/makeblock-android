package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class DiskCacheWriteLocker
{
  private final Map<Key, WriteLock> locks = new HashMap();
  private final WriteLockPool writeLockPool = new WriteLockPool();
  
  void acquire(Key paramKey)
  {
    for (;;)
    {
      WriteLock localWriteLock2;
      try
      {
        localWriteLock2 = (WriteLock)this.locks.get(paramKey);
      }
      finally {}
      WriteLock localWriteLock1 = this.writeLockPool.obtain();
      this.locks.put(paramKey, localWriteLock1);
      localWriteLock1.interestedThreads += 1;
      localWriteLock1.lock.lock();
      return;
      localWriteLock1 = localWriteLock2;
      if (localWriteLock2 != null) {}
    }
  }
  
  void release(Key paramKey)
  {
    WriteLock localWriteLock1;
    try
    {
      localWriteLock1 = (WriteLock)Preconditions.checkNotNull(this.locks.get(paramKey));
      if (localWriteLock1.interestedThreads < 1) {
        throw new IllegalStateException("Cannot release a lock that is not held, key: " + paramKey + ", interestedThreads: " + localWriteLock1.interestedThreads);
      }
    }
    finally {}
    localWriteLock1.interestedThreads -= 1;
    if (localWriteLock1.interestedThreads == 0)
    {
      WriteLock localWriteLock2 = (WriteLock)this.locks.remove(paramKey);
      if (!localWriteLock2.equals(localWriteLock1)) {
        throw new IllegalStateException("Removed the wrong lock, expected to remove: " + localWriteLock1 + ", but actually removed: " + localWriteLock2 + ", key: " + paramKey);
      }
      this.writeLockPool.offer(localWriteLock2);
    }
    localWriteLock1.lock.unlock();
  }
  
  private static class WriteLock
  {
    int interestedThreads;
    final Lock lock = new ReentrantLock();
  }
  
  private static class WriteLockPool
  {
    private static final int MAX_POOL_SIZE = 10;
    private final Queue<DiskCacheWriteLocker.WriteLock> pool = new ArrayDeque();
    
    DiskCacheWriteLocker.WriteLock obtain()
    {
      synchronized (this.pool)
      {
        DiskCacheWriteLocker.WriteLock localWriteLock = (DiskCacheWriteLocker.WriteLock)this.pool.poll();
        ??? = localWriteLock;
        if (localWriteLock == null) {
          ??? = new DiskCacheWriteLocker.WriteLock();
        }
        return (DiskCacheWriteLocker.WriteLock)???;
      }
    }
    
    void offer(DiskCacheWriteLocker.WriteLock paramWriteLock)
    {
      synchronized (this.pool)
      {
        if (this.pool.size() < 10) {
          this.pool.offer(paramWriteLock);
        }
        return;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\cache\DiskCacheWriteLocker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */