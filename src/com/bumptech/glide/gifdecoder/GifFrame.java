package com.bumptech.glide.gifdecoder;

import android.support.annotation.ColorInt;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class GifFrame
{
  public static final int DISPOSAL_BACKGROUND = 2;
  public static final int DISPOSAL_NONE = 1;
  public static final int DISPOSAL_PREVIOUS = 3;
  public static final int DISPOSAL_UNSPECIFIED = 0;
  int bufferFrameStart;
  int delay;
  int dispose;
  int ih;
  boolean interlace;
  int iw;
  int ix;
  int iy;
  @ColorInt
  int[] lct;
  int transIndex;
  boolean transparency;
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface GifDisposalMethod {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\gifdecoder\GifFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */