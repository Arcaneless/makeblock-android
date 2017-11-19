package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;

public abstract class BitmapTransformation
  implements Transformation<Bitmap>
{
  public BitmapTransformation() {}
  
  @Deprecated
  public BitmapTransformation(Context paramContext)
  {
    this();
  }
  
  @Deprecated
  public BitmapTransformation(BitmapPool paramBitmapPool)
  {
    this();
  }
  
  protected abstract Bitmap transform(@NonNull BitmapPool paramBitmapPool, @NonNull Bitmap paramBitmap, int paramInt1, int paramInt2);
  
  public final Resource<Bitmap> transform(Context paramContext, Resource<Bitmap> paramResource, int paramInt1, int paramInt2)
  {
    if (!Util.isValidDimensions(paramInt1, paramInt2)) {
      throw new IllegalArgumentException("Cannot apply transformation on width: " + paramInt1 + " or height: " + paramInt2 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
    }
    paramContext = Glide.get(paramContext).getBitmapPool();
    Bitmap localBitmap1 = (Bitmap)paramResource.get();
    if (paramInt1 == Integer.MIN_VALUE)
    {
      paramInt1 = localBitmap1.getWidth();
      if (paramInt2 != Integer.MIN_VALUE) {
        break label123;
      }
      paramInt2 = localBitmap1.getHeight();
    }
    Bitmap localBitmap2;
    label123:
    for (;;)
    {
      localBitmap2 = transform(paramContext, localBitmap1, paramInt1, paramInt2);
      if (!localBitmap1.equals(localBitmap2)) {
        break label126;
      }
      return paramResource;
      break;
    }
    label126:
    return BitmapResource.obtain(localBitmap2, paramContext);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\BitmapTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */