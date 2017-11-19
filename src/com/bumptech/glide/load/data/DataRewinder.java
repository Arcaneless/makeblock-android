package com.bumptech.glide.load.data;

import java.io.IOException;

public abstract interface DataRewinder<T>
{
  public abstract void cleanup();
  
  public abstract T rewindAndGet()
    throws IOException;
  
  public static abstract interface Factory<T>
  {
    public abstract DataRewinder<T> build(T paramT);
    
    public abstract Class<T> getDataClass();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\data\DataRewinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */