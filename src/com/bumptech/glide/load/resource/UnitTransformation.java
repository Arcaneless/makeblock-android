package com.bumptech.glide.load.resource;

import android.content.Context;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import java.security.MessageDigest;

public final class UnitTransformation<T>
  implements Transformation<T>
{
  private static final Transformation<?> TRANSFORMATION = new UnitTransformation();
  
  public static <T> UnitTransformation<T> get()
  {
    return (UnitTransformation)TRANSFORMATION;
  }
  
  public Resource<T> transform(Context paramContext, Resource<T> paramResource, int paramInt1, int paramInt2)
  {
    return paramResource;
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest) {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\UnitTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */