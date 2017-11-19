package com.bumptech.glide.load.resource.bytes;

import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

public class BytesResource
  implements Resource<byte[]>
{
  private final byte[] bytes;
  
  public BytesResource(byte[] paramArrayOfByte)
  {
    this.bytes = ((byte[])Preconditions.checkNotNull(paramArrayOfByte));
  }
  
  public byte[] get()
  {
    return this.bytes;
  }
  
  public Class<byte[]> getResourceClass()
  {
    return byte[].class;
  }
  
  public int getSize()
  {
    return this.bytes.length;
  }
  
  public void recycle() {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\bytes\BytesResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */