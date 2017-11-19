package cc.makeblock.makeblock.engine;

import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestOptions;

public final class GlideOptions
  extends RequestOptions
{
  private static GlideOptions centerCropTransform2;
  private static GlideOptions centerInsideTransform1;
  private static GlideOptions circleCropTransform3;
  private static GlideOptions fitCenterTransform0;
  private static GlideOptions noAnimation5;
  private static GlideOptions noTransformation4;
  
  public static GlideOptions bitmapTransform(Transformation<Bitmap> paramTransformation)
  {
    return new GlideOptions().transform(paramTransformation);
  }
  
  public static GlideOptions centerCropTransform()
  {
    if (centerCropTransform2 == null) {
      centerCropTransform2 = new GlideOptions().centerCrop().autoClone();
    }
    return centerCropTransform2;
  }
  
  public static GlideOptions centerInsideTransform()
  {
    if (centerInsideTransform1 == null) {
      centerInsideTransform1 = new GlideOptions().centerInside().autoClone();
    }
    return centerInsideTransform1;
  }
  
  public static GlideOptions circleCropTransform()
  {
    if (circleCropTransform3 == null) {
      circleCropTransform3 = new GlideOptions().circleCrop().autoClone();
    }
    return circleCropTransform3;
  }
  
  public static GlideOptions decodeTypeOf(Class<?> paramClass)
  {
    return new GlideOptions().decode(paramClass);
  }
  
  public static GlideOptions diskCacheStrategyOf(DiskCacheStrategy paramDiskCacheStrategy)
  {
    return new GlideOptions().diskCacheStrategy(paramDiskCacheStrategy);
  }
  
  public static GlideOptions downsampleOf(DownsampleStrategy paramDownsampleStrategy)
  {
    return new GlideOptions().downsample(paramDownsampleStrategy);
  }
  
  public static GlideOptions encodeFormatOf(Bitmap.CompressFormat paramCompressFormat)
  {
    return new GlideOptions().encodeFormat(paramCompressFormat);
  }
  
  public static GlideOptions encodeQualityOf(int paramInt)
  {
    return new GlideOptions().encodeQuality(paramInt);
  }
  
  public static GlideOptions errorOf(int paramInt)
  {
    return new GlideOptions().error(paramInt);
  }
  
  public static GlideOptions errorOf(Drawable paramDrawable)
  {
    return new GlideOptions().error(paramDrawable);
  }
  
  public static GlideOptions fitCenterTransform()
  {
    if (fitCenterTransform0 == null) {
      fitCenterTransform0 = new GlideOptions().fitCenter().autoClone();
    }
    return fitCenterTransform0;
  }
  
  public static GlideOptions formatOf(DecodeFormat paramDecodeFormat)
  {
    return new GlideOptions().format(paramDecodeFormat);
  }
  
  public static GlideOptions frameOf(long paramLong)
  {
    return new GlideOptions().frame(paramLong);
  }
  
  public static GlideOptions noAnimation()
  {
    if (noAnimation5 == null) {
      noAnimation5 = new GlideOptions().dontAnimate().autoClone();
    }
    return noAnimation5;
  }
  
  public static GlideOptions noTransformation()
  {
    if (noTransformation4 == null) {
      noTransformation4 = new GlideOptions().dontTransform().autoClone();
    }
    return noTransformation4;
  }
  
  public static <T> GlideOptions option(Option<T> paramOption, T paramT)
  {
    return new GlideOptions().set(paramOption, paramT);
  }
  
  public static GlideOptions overrideOf(int paramInt)
  {
    return new GlideOptions().override(paramInt);
  }
  
  public static GlideOptions overrideOf(int paramInt1, int paramInt2)
  {
    return new GlideOptions().override(paramInt1, paramInt2);
  }
  
  public static GlideOptions placeholderOf(int paramInt)
  {
    return new GlideOptions().placeholder(paramInt);
  }
  
  public static GlideOptions placeholderOf(Drawable paramDrawable)
  {
    return new GlideOptions().placeholder(paramDrawable);
  }
  
  public static GlideOptions priorityOf(Priority paramPriority)
  {
    return new GlideOptions().priority(paramPriority);
  }
  
  public static GlideOptions signatureOf(Key paramKey)
  {
    return new GlideOptions().signature(paramKey);
  }
  
  public static GlideOptions sizeMultiplierOf(float paramFloat)
  {
    return new GlideOptions().sizeMultiplier(paramFloat);
  }
  
  public static GlideOptions skipMemoryCacheOf(boolean paramBoolean)
  {
    return new GlideOptions().skipMemoryCache(paramBoolean);
  }
  
  public GlideOptions apply(RequestOptions paramRequestOptions)
  {
    return (GlideOptions)super.apply(paramRequestOptions);
  }
  
  public GlideOptions autoClone()
  {
    return (GlideOptions)super.autoClone();
  }
  
  public GlideOptions centerCrop()
  {
    return (GlideOptions)super.centerCrop();
  }
  
  public GlideOptions centerInside()
  {
    return (GlideOptions)super.centerInside();
  }
  
  public GlideOptions circleCrop()
  {
    return (GlideOptions)super.circleCrop();
  }
  
  public GlideOptions clone()
  {
    return (GlideOptions)super.clone();
  }
  
  public GlideOptions decode(Class<?> paramClass)
  {
    return (GlideOptions)super.decode(paramClass);
  }
  
  public GlideOptions diskCacheStrategy(DiskCacheStrategy paramDiskCacheStrategy)
  {
    return (GlideOptions)super.diskCacheStrategy(paramDiskCacheStrategy);
  }
  
  public GlideOptions dontAnimate()
  {
    return (GlideOptions)super.dontAnimate();
  }
  
  public GlideOptions dontTransform()
  {
    return (GlideOptions)super.dontTransform();
  }
  
  public GlideOptions downsample(DownsampleStrategy paramDownsampleStrategy)
  {
    return (GlideOptions)super.downsample(paramDownsampleStrategy);
  }
  
  public GlideOptions encodeFormat(Bitmap.CompressFormat paramCompressFormat)
  {
    return (GlideOptions)super.encodeFormat(paramCompressFormat);
  }
  
  public GlideOptions encodeQuality(int paramInt)
  {
    return (GlideOptions)super.encodeQuality(paramInt);
  }
  
  public GlideOptions error(int paramInt)
  {
    return (GlideOptions)super.error(paramInt);
  }
  
  public GlideOptions error(Drawable paramDrawable)
  {
    return (GlideOptions)super.error(paramDrawable);
  }
  
  public GlideOptions fallback(int paramInt)
  {
    return (GlideOptions)super.fallback(paramInt);
  }
  
  public GlideOptions fallback(Drawable paramDrawable)
  {
    return (GlideOptions)super.fallback(paramDrawable);
  }
  
  public GlideOptions fitCenter()
  {
    return (GlideOptions)super.fitCenter();
  }
  
  public GlideOptions format(DecodeFormat paramDecodeFormat)
  {
    return (GlideOptions)super.format(paramDecodeFormat);
  }
  
  public GlideOptions frame(long paramLong)
  {
    return (GlideOptions)super.frame(paramLong);
  }
  
  public GlideOptions lock()
  {
    return (GlideOptions)super.lock();
  }
  
  public GlideOptions onlyRetrieveFromCache(boolean paramBoolean)
  {
    return (GlideOptions)super.onlyRetrieveFromCache(paramBoolean);
  }
  
  public GlideOptions optionalCenterCrop()
  {
    return (GlideOptions)super.optionalCenterCrop();
  }
  
  public GlideOptions optionalCenterInside()
  {
    return (GlideOptions)super.optionalCenterInside();
  }
  
  public GlideOptions optionalCircleCrop()
  {
    return (GlideOptions)super.optionalCircleCrop();
  }
  
  public GlideOptions optionalFitCenter()
  {
    return (GlideOptions)super.optionalFitCenter();
  }
  
  public GlideOptions optionalTransform(Transformation<Bitmap> paramTransformation)
  {
    return (GlideOptions)super.optionalTransform(paramTransformation);
  }
  
  public <T> GlideOptions optionalTransform(Class<T> paramClass, Transformation<T> paramTransformation)
  {
    return (GlideOptions)super.optionalTransform(paramClass, paramTransformation);
  }
  
  public GlideOptions override(int paramInt)
  {
    return (GlideOptions)super.override(paramInt);
  }
  
  public GlideOptions override(int paramInt1, int paramInt2)
  {
    return (GlideOptions)super.override(paramInt1, paramInt2);
  }
  
  public GlideOptions placeholder(int paramInt)
  {
    return (GlideOptions)super.placeholder(paramInt);
  }
  
  public GlideOptions placeholder(Drawable paramDrawable)
  {
    return (GlideOptions)super.placeholder(paramDrawable);
  }
  
  public GlideOptions priority(Priority paramPriority)
  {
    return (GlideOptions)super.priority(paramPriority);
  }
  
  public <T> GlideOptions set(Option<T> paramOption, T paramT)
  {
    return (GlideOptions)super.set(paramOption, paramT);
  }
  
  public GlideOptions signature(Key paramKey)
  {
    return (GlideOptions)super.signature(paramKey);
  }
  
  public GlideOptions sizeMultiplier(float paramFloat)
  {
    return (GlideOptions)super.sizeMultiplier(paramFloat);
  }
  
  public GlideOptions skipMemoryCache(boolean paramBoolean)
  {
    return (GlideOptions)super.skipMemoryCache(paramBoolean);
  }
  
  public GlideOptions theme(Resources.Theme paramTheme)
  {
    return (GlideOptions)super.theme(paramTheme);
  }
  
  public GlideOptions transform(Transformation<Bitmap> paramTransformation)
  {
    return (GlideOptions)super.transform(paramTransformation);
  }
  
  public <T> GlideOptions transform(Class<T> paramClass, Transformation<T> paramTransformation)
  {
    return (GlideOptions)super.transform(paramClass, paramTransformation);
  }
  
  public GlideOptions useUnlimitedSourceGeneratorsPool(boolean paramBoolean)
  {
    return (GlideOptions)super.useUnlimitedSourceGeneratorsPool(paramBoolean);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\GlideOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */