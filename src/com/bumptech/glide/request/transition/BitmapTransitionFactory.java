package com.bumptech.glide.request.transition;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

public class BitmapTransitionFactory
  extends BitmapContainerTransitionFactory<Bitmap>
{
  public BitmapTransitionFactory(@NonNull TransitionFactory<Drawable> paramTransitionFactory)
  {
    super(paramTransitionFactory);
  }
  
  @NonNull
  protected Bitmap getBitmap(@NonNull Bitmap paramBitmap)
  {
    return paramBitmap;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\request\transition\BitmapTransitionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */