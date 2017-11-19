package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class DrawableThumbnailImageViewTarget
  extends ThumbnailImageViewTarget<Drawable>
{
  public DrawableThumbnailImageViewTarget(ImageView paramImageView)
  {
    super(paramImageView);
  }
  
  protected Drawable getDrawable(Drawable paramDrawable)
  {
    return paramDrawable;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\request\target\DrawableThumbnailImageViewTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */