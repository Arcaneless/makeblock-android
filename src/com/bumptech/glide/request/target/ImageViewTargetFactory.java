package com.bumptech.glide.request.target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ImageViewTargetFactory
{
  public <Z> Target<Z> buildTarget(ImageView paramImageView, Class<Z> paramClass)
  {
    if (Bitmap.class.equals(paramClass)) {
      return new BitmapImageViewTarget(paramImageView);
    }
    if (Drawable.class.isAssignableFrom(paramClass)) {
      return new DrawableImageViewTarget(paramImageView);
    }
    throw new IllegalArgumentException("Unhandled class: " + paramClass + ", try .as*(Class).transcode(ResourceTranscoder)");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\request\target\ImageViewTargetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */