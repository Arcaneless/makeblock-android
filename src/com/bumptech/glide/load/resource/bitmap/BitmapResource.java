package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;

public class BitmapResource
  implements Resource<Bitmap>, Initializable
{
  private final Bitmap bitmap;
  private final BitmapPool bitmapPool;
  
  public BitmapResource(Bitmap paramBitmap, BitmapPool paramBitmapPool)
  {
    this.bitmap = ((Bitmap)Preconditions.checkNotNull(paramBitmap, "Bitmap must not be null"));
    this.bitmapPool = ((BitmapPool)Preconditions.checkNotNull(paramBitmapPool, "BitmapPool must not be null"));
  }
  
  @Nullable
  public static BitmapResource obtain(@Nullable Bitmap paramBitmap, BitmapPool paramBitmapPool)
  {
    if (paramBitmap == null) {
      return null;
    }
    return new BitmapResource(paramBitmap, paramBitmapPool);
  }
  
  public Bitmap get()
  {
    return this.bitmap;
  }
  
  public Class<Bitmap> getResourceClass()
  {
    return Bitmap.class;
  }
  
  public int getSize()
  {
    return Util.getBitmapByteSize(this.bitmap);
  }
  
  public void initialize()
  {
    this.bitmap.prepareToDraw();
  }
  
  public void recycle()
  {
    this.bitmapPool.put(this.bitmap);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\BitmapResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */