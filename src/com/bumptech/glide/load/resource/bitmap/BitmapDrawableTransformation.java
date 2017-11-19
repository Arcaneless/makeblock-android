package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

public class BitmapDrawableTransformation
  implements Transformation<BitmapDrawable>
{
  private final Transformation<Bitmap> wrapped;
  
  @Deprecated
  public BitmapDrawableTransformation(Context paramContext, Transformation<Bitmap> paramTransformation)
  {
    this(paramTransformation);
  }
  
  @Deprecated
  public BitmapDrawableTransformation(Context paramContext, BitmapPool paramBitmapPool, Transformation<Bitmap> paramTransformation)
  {
    this(paramTransformation);
  }
  
  public BitmapDrawableTransformation(Transformation<Bitmap> paramTransformation)
  {
    this.wrapped = ((Transformation)Preconditions.checkNotNull(paramTransformation));
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof BitmapDrawableTransformation))
    {
      paramObject = (BitmapDrawableTransformation)paramObject;
      return this.wrapped.equals(((BitmapDrawableTransformation)paramObject).wrapped);
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.wrapped.hashCode();
  }
  
  public Resource<BitmapDrawable> transform(Context paramContext, Resource<BitmapDrawable> paramResource, int paramInt1, int paramInt2)
  {
    BitmapResource localBitmapResource = BitmapResource.obtain(((BitmapDrawable)paramResource.get()).getBitmap(), Glide.get(paramContext).getBitmapPool());
    Resource localResource = this.wrapped.transform(paramContext, localBitmapResource, paramInt1, paramInt2);
    if (localResource.equals(localBitmapResource)) {
      return paramResource;
    }
    return LazyBitmapDrawableResource.obtain(paramContext, (Bitmap)localResource.get());
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
  {
    this.wrapped.updateDiskCacheKey(paramMessageDigest);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\BitmapDrawableTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */