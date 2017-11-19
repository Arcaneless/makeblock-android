package com.bumptech.glide.load.engine.bitmap_recycle;

import com.bumptech.glide.util.Util;
import java.util.Queue;

abstract class BaseKeyPool<T extends Poolable>
{
  private static final int MAX_SIZE = 20;
  private final Queue<T> keyPool = Util.createQueue(20);
  
  protected abstract T create();
  
  protected T get()
  {
    Poolable localPoolable2 = (Poolable)this.keyPool.poll();
    Poolable localPoolable1 = localPoolable2;
    if (localPoolable2 == null) {
      localPoolable1 = create();
    }
    return localPoolable1;
  }
  
  public void offer(T paramT)
  {
    if (this.keyPool.size() < 20) {
      this.keyPool.offer(paramT);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\bitmap_recycle\BaseKeyPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */