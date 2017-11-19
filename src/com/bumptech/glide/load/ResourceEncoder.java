package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.Resource;

public abstract interface ResourceEncoder<T>
  extends Encoder<Resource<T>>
{
  public abstract EncodeStrategy getEncodeStrategy(Options paramOptions);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\ResourceEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */