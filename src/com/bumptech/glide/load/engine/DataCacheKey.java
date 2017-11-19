package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import java.security.MessageDigest;

final class DataCacheKey
  implements Key
{
  private final Key signature;
  private final Key sourceKey;
  
  public DataCacheKey(Key paramKey1, Key paramKey2)
  {
    this.sourceKey = paramKey1;
    this.signature = paramKey2;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof DataCacheKey))
    {
      paramObject = (DataCacheKey)paramObject;
      bool1 = bool2;
      if (this.sourceKey.equals(((DataCacheKey)paramObject).sourceKey))
      {
        bool1 = bool2;
        if (this.signature.equals(((DataCacheKey)paramObject).signature)) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public Key getSourceKey()
  {
    return this.sourceKey;
  }
  
  public int hashCode()
  {
    return this.sourceKey.hashCode() * 31 + this.signature.hashCode();
  }
  
  public String toString()
  {
    return "DataCacheKey{sourceKey=" + this.sourceKey + ", signature=" + this.signature + '}';
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
  {
    this.sourceKey.updateDiskCacheKey(paramMessageDigest);
    this.signature.updateDiskCacheKey(paramMessageDigest);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\DataCacheKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */