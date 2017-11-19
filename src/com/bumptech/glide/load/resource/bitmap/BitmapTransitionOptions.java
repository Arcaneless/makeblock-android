package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.BitmapTransitionFactory;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory.Builder;
import com.bumptech.glide.request.transition.TransitionFactory;

public final class BitmapTransitionOptions
  extends TransitionOptions<BitmapTransitionOptions, Bitmap>
{
  public static BitmapTransitionOptions with(TransitionFactory<Bitmap> paramTransitionFactory)
  {
    return (BitmapTransitionOptions)new BitmapTransitionOptions().transition(paramTransitionFactory);
  }
  
  public static BitmapTransitionOptions withCrossFade()
  {
    return new BitmapTransitionOptions().crossFade();
  }
  
  public static BitmapTransitionOptions withCrossFade(int paramInt)
  {
    return new BitmapTransitionOptions().crossFade(paramInt);
  }
  
  public static BitmapTransitionOptions withCrossFade(int paramInt1, int paramInt2)
  {
    return new BitmapTransitionOptions().crossFade(paramInt1, paramInt2);
  }
  
  public static BitmapTransitionOptions withCrossFade(DrawableCrossFadeFactory.Builder paramBuilder)
  {
    return new BitmapTransitionOptions().crossFade(paramBuilder);
  }
  
  public static BitmapTransitionOptions withCrossFade(DrawableCrossFadeFactory paramDrawableCrossFadeFactory)
  {
    return new BitmapTransitionOptions().crossFade(paramDrawableCrossFadeFactory);
  }
  
  public static BitmapTransitionOptions withWrapped(TransitionFactory<Drawable> paramTransitionFactory)
  {
    return new BitmapTransitionOptions().transitionUsing(paramTransitionFactory);
  }
  
  public BitmapTransitionOptions crossFade()
  {
    return crossFade(new DrawableCrossFadeFactory.Builder());
  }
  
  public BitmapTransitionOptions crossFade(int paramInt)
  {
    return crossFade(new DrawableCrossFadeFactory.Builder(paramInt));
  }
  
  public BitmapTransitionOptions crossFade(int paramInt1, int paramInt2)
  {
    return crossFade(new DrawableCrossFadeFactory.Builder(paramInt2).setDefaultAnimationId(paramInt1));
  }
  
  public BitmapTransitionOptions crossFade(DrawableCrossFadeFactory.Builder paramBuilder)
  {
    return transitionUsing(paramBuilder.build());
  }
  
  public BitmapTransitionOptions crossFade(DrawableCrossFadeFactory paramDrawableCrossFadeFactory)
  {
    return transitionUsing(paramDrawableCrossFadeFactory);
  }
  
  public BitmapTransitionOptions transitionUsing(TransitionFactory<Drawable> paramTransitionFactory)
  {
    return (BitmapTransitionOptions)transition(new BitmapTransitionFactory(paramTransitionFactory));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\BitmapTransitionOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */