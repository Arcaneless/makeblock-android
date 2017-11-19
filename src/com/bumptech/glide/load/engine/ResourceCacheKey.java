package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

final class ResourceCacheKey
  implements Key
{
  private static final LruCache<Class<?>, byte[]> RESOURCE_CLASS_BYTES = new LruCache(50);
  private final Class<?> decodedResourceClass;
  private final int height;
  private final Options options;
  private final Key signature;
  private final Key sourceKey;
  private final Transformation<?> transformation;
  private final int width;
  
  public ResourceCacheKey(Key paramKey1, Key paramKey2, int paramInt1, int paramInt2, Transformation<?> paramTransformation, Class<?> paramClass, Options paramOptions)
  {
    this.sourceKey = paramKey1;
    this.signature = paramKey2;
    this.width = paramInt1;
    this.height = paramInt2;
    this.transformation = paramTransformation;
    this.decodedResourceClass = paramClass;
    this.options = paramOptions;
  }
  
  private byte[] getResourceClassBytes()
  {
    byte[] arrayOfByte2 = (byte[])RESOURCE_CLASS_BYTES.get(this.decodedResourceClass);
    byte[] arrayOfByte1 = arrayOfByte2;
    if (arrayOfByte2 == null)
    {
      arrayOfByte1 = this.decodedResourceClass.getName().getBytes(CHARSET);
      RESOURCE_CLASS_BYTES.put(this.decodedResourceClass, arrayOfByte1);
    }
    return arrayOfByte1;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof ResourceCacheKey))
    {
      paramObject = (ResourceCacheKey)paramObject;
      bool1 = bool2;
      if (this.height == ((ResourceCacheKey)paramObject).height)
      {
        bool1 = bool2;
        if (this.width == ((ResourceCacheKey)paramObject).width)
        {
          bool1 = bool2;
          if (Util.bothNullOrEqual(this.transformation, ((ResourceCacheKey)paramObject).transformation))
          {
            bool1 = bool2;
            if (this.decodedResourceClass.equals(((ResourceCacheKey)paramObject).decodedResourceClass))
            {
              bool1 = bool2;
              if (this.sourceKey.equals(((ResourceCacheKey)paramObject).sourceKey))
              {
                bool1 = bool2;
                if (this.signature.equals(((ResourceCacheKey)paramObject).signature))
                {
                  bool1 = bool2;
                  if (this.options.equals(((ResourceCacheKey)paramObject).options)) {
                    bool1 = true;
                  }
                }
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public int hashCode()
  {
    int j = ((this.sourceKey.hashCode() * 31 + this.signature.hashCode()) * 31 + this.width) * 31 + this.height;
    int i = j;
    if (this.transformation != null) {
      i = j * 31 + this.transformation.hashCode();
    }
    return (i * 31 + this.decodedResourceClass.hashCode()) * 31 + this.options.hashCode();
  }
  
  public String toString()
  {
    return "ResourceCacheKey{sourceKey=" + this.sourceKey + ", signature=" + this.signature + ", width=" + this.width + ", height=" + this.height + ", decodedResourceClass=" + this.decodedResourceClass + ", transformation='" + this.transformation + '\'' + ", options=" + this.options + '}';
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
  {
    byte[] arrayOfByte = ByteBuffer.allocate(8).putInt(this.width).putInt(this.height).array();
    this.signature.updateDiskCacheKey(paramMessageDigest);
    this.sourceKey.updateDiskCacheKey(paramMessageDigest);
    paramMessageDigest.update(arrayOfByte);
    if (this.transformation != null) {
      this.transformation.updateDiskCacheKey(paramMessageDigest);
    }
    this.options.updateDiskCacheKey(paramMessageDigest);
    paramMessageDigest.update(getResourceClassBytes());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\ResourceCacheKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */