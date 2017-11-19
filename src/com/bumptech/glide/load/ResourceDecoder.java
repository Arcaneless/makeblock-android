package com.bumptech.glide.load;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;

public abstract interface ResourceDecoder<T, Z>
{
  @Nullable
  public abstract Resource<Z> decode(T paramT, int paramInt1, int paramInt2, Options paramOptions)
    throws IOException;
  
  public abstract boolean handles(T paramT, Options paramOptions)
    throws IOException;
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\ResourceDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */