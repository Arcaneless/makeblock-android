package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

public class CenterInside
  extends BitmapTransformation
{
  private static final String ID = "com.bumptech.glide.load.resource.bitmap.CenterInside";
  private static final byte[] ID_BYTES = "com.bumptech.glide.load.resource.bitmap.CenterInside".getBytes(CHARSET);
  
  public CenterInside() {}
  
  @Deprecated
  public CenterInside(Context paramContext)
  {
    this();
  }
  
  @Deprecated
  public CenterInside(BitmapPool paramBitmapPool)
  {
    this();
  }
  
  public boolean equals(Object paramObject)
  {
    return paramObject instanceof CenterInside;
  }
  
  public int hashCode()
  {
    return "com.bumptech.glide.load.resource.bitmap.CenterInside".hashCode();
  }
  
  protected Bitmap transform(@NonNull BitmapPool paramBitmapPool, @NonNull Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    return TransformationUtils.centerInside(paramBitmapPool, paramBitmap, paramInt1, paramInt2);
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
  {
    paramMessageDigest.update(ID_BYTES);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\CenterInside.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */