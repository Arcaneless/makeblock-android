package com.bumptech.glide.util;

import android.support.annotation.Nullable;
import java.util.LinkedHashMap;

public class LruCache<T, Y>
{
  private final LinkedHashMap<T, Y> cache = new LinkedHashMap(100, 0.75F, true);
  private int currentSize = 0;
  private final int initialMaxSize;
  private int maxSize;
  
  public LruCache(int paramInt)
  {
    this.initialMaxSize = paramInt;
    this.maxSize = paramInt;
  }
  
  private void evict()
  {
    trimToSize(this.maxSize);
  }
  
  public void clearMemory()
  {
    trimToSize(0);
  }
  
  public boolean contains(T paramT)
  {
    try
    {
      boolean bool = this.cache.containsKey(paramT);
      return bool;
    }
    finally
    {
      paramT = finally;
      throw paramT;
    }
  }
  
  @Nullable
  public Y get(T paramT)
  {
    try
    {
      paramT = this.cache.get(paramT);
      return paramT;
    }
    finally
    {
      paramT = finally;
      throw paramT;
    }
  }
  
  public int getCurrentSize()
  {
    try
    {
      int i = this.currentSize;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getMaxSize()
  {
    try
    {
      int i = this.maxSize;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected int getSize(Y paramY)
  {
    return 1;
  }
  
  protected void onItemEvicted(T paramT, Y paramY) {}
  
  public Y put(T paramT, Y paramY)
  {
    label39:
    try
    {
      if (getSize(paramY) >= this.maxSize)
      {
        onItemEvicted(paramT, paramY);
        paramT = null;
        return paramT;
      }
      paramT = this.cache.put(paramT, paramY);
    }
    finally {}
    this.currentSize += getSize(paramY);
    for (;;)
    {
      this.currentSize -= getSize(paramT);
      do
      {
        evict();
        break;
        if (paramY != null) {
          break label39;
        }
      } while (paramT == null);
    }
  }
  
  @Nullable
  public Y remove(T paramT)
  {
    for (;;)
    {
      try
      {
        paramT = this.cache.remove(paramT);
      }
      finally {}
      this.currentSize -= getSize(paramT);
      return paramT;
      if (paramT == null) {}
    }
  }
  
  public void setSizeMultiplier(float paramFloat)
  {
    if (paramFloat < 0.0F) {
      try
      {
        throw new IllegalArgumentException("Multiplier must be >= 0");
      }
      finally {}
    }
    this.maxSize = Math.round(this.initialMaxSize * paramFloat);
    evict();
  }
  
  /* Error */
  protected void trimToSize(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 27	com/bumptech/glide/util/LruCache:currentSize	I
    //   6: iload_1
    //   7: if_icmple +75 -> 82
    //   10: aload_0
    //   11: getfield 25	com/bumptech/glide/util/LruCache:cache	Ljava/util/LinkedHashMap;
    //   14: invokevirtual 93	java/util/LinkedHashMap:entrySet	()Ljava/util/Set;
    //   17: invokeinterface 99 1 0
    //   22: invokeinterface 105 1 0
    //   27: checkcast 107	java/util/Map$Entry
    //   30: astore_3
    //   31: aload_3
    //   32: invokeinterface 110 1 0
    //   37: astore_2
    //   38: aload_0
    //   39: aload_0
    //   40: getfield 27	com/bumptech/glide/util/LruCache:currentSize	I
    //   43: aload_0
    //   44: aload_2
    //   45: invokevirtual 64	com/bumptech/glide/util/LruCache:getSize	(Ljava/lang/Object;)I
    //   48: isub
    //   49: putfield 27	com/bumptech/glide/util/LruCache:currentSize	I
    //   52: aload_3
    //   53: invokeinterface 113 1 0
    //   58: astore_3
    //   59: aload_0
    //   60: getfield 25	com/bumptech/glide/util/LruCache:cache	Ljava/util/LinkedHashMap;
    //   63: aload_3
    //   64: invokevirtual 74	java/util/LinkedHashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   67: pop
    //   68: aload_0
    //   69: aload_3
    //   70: aload_2
    //   71: invokevirtual 66	com/bumptech/glide/util/LruCache:onItemEvicted	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   74: goto -72 -> 2
    //   77: astore_2
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_2
    //   81: athrow
    //   82: aload_0
    //   83: monitorexit
    //   84: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	85	0	this	LruCache
    //   0	85	1	paramInt	int
    //   37	34	2	localObject1	Object
    //   77	4	2	localObject2	Object
    //   30	40	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   2	74	77	finally
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\util\LruCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */