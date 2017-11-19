package com.bumptech.glide.signature;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

public final class ObjectKey
  implements Key
{
  private final Object object;
  
  public ObjectKey(Object paramObject)
  {
    this.object = Preconditions.checkNotNull(paramObject);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof ObjectKey))
    {
      paramObject = (ObjectKey)paramObject;
      return this.object.equals(((ObjectKey)paramObject).object);
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.object.hashCode();
  }
  
  public String toString()
  {
    return "ObjectKey{object=" + this.object + '}';
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
  {
    paramMessageDigest.update(this.object.toString().getBytes(CHARSET));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\signature\ObjectKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */