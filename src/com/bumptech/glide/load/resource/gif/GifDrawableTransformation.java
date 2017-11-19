package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

public class GifDrawableTransformation
  implements Transformation<GifDrawable>
{
  private final Transformation<Bitmap> wrapped;
  
  @Deprecated
  public GifDrawableTransformation(Context paramContext, Transformation<Bitmap> paramTransformation)
  {
    this(paramTransformation);
  }
  
  public GifDrawableTransformation(Transformation<Bitmap> paramTransformation)
  {
    this.wrapped = ((Transformation)Preconditions.checkNotNull(paramTransformation));
  }
  
  @Deprecated
  public GifDrawableTransformation(Transformation<Bitmap> paramTransformation, BitmapPool paramBitmapPool)
  {
    this(paramTransformation);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof GifDrawableTransformation))
    {
      paramObject = (GifDrawableTransformation)paramObject;
      return this.wrapped.equals(((GifDrawableTransformation)paramObject).wrapped);
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.wrapped.hashCode();
  }
  
  public Resource<GifDrawable> transform(Context paramContext, Resource<GifDrawable> paramResource, int paramInt1, int paramInt2)
  {
    GifDrawable localGifDrawable = (GifDrawable)paramResource.get();
    Object localObject = Glide.get(paramContext).getBitmapPool();
    localObject = new BitmapResource(localGifDrawable.getFirstFrame(), (BitmapPool)localObject);
    paramContext = this.wrapped.transform(paramContext, (Resource)localObject, paramInt1, paramInt2);
    if (!localObject.equals(paramContext)) {
      ((Resource)localObject).recycle();
    }
    paramContext = (Bitmap)paramContext.get();
    localGifDrawable.setFrameTransformation(this.wrapped, paramContext);
    return paramResource;
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
  {
    this.wrapped.updateDiskCacheKey(paramMessageDigest);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\gif\GifDrawableTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */