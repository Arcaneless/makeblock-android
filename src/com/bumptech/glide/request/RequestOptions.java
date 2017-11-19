package com.bumptech.glide.request;

import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;
import com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;
import com.bumptech.glide.load.resource.gif.StreamGifDecoder;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
import java.util.Map;

public class RequestOptions
  implements Cloneable
{
  private static final int DISK_CACHE_STRATEGY = 4;
  private static final int ERROR_ID = 32;
  private static final int ERROR_PLACEHOLDER = 16;
  private static final int FALLBACK = 8192;
  private static final int FALLBACK_ID = 16384;
  private static final int IS_CACHEABLE = 256;
  private static final int ONLY_RETRIEVE_FROM_CACHE = 524288;
  private static final int OVERRIDE = 512;
  private static final int PLACEHOLDER = 64;
  private static final int PLACEHOLDER_ID = 128;
  private static final int PRIORITY = 8;
  private static final int RESOURCE_CLASS = 4096;
  private static final int SIGNATURE = 1024;
  private static final int SIZE_MULTIPLIER = 2;
  private static final int THEME = 32768;
  private static final int TRANSFORMATION = 2048;
  private static final int TRANSFORMATION_ALLOWED = 65536;
  private static final int TRANSFORMATION_REQUIRED = 131072;
  private static final int UNSET = -1;
  private static final int USE_UNLIMITED_SOURCE_GENERATORS_POOL = 262144;
  private static RequestOptions centerCropOptions;
  private static RequestOptions centerInsideOptions;
  private static RequestOptions circleCropOptions;
  private static RequestOptions fitCenterOptions;
  private static RequestOptions noAnimationOptions;
  private static RequestOptions noTransformOptions;
  private static RequestOptions skipMemoryCacheFalseOptions;
  private static RequestOptions skipMemoryCacheTrueOptions;
  private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;
  private int errorId;
  private Drawable errorPlaceholder;
  private Drawable fallbackDrawable;
  private int fallbackId;
  private int fields;
  private boolean isAutoCloneEnabled;
  private boolean isCacheable = true;
  private boolean isLocked;
  private boolean isTransformationAllowed = true;
  private boolean isTransformationRequired;
  private boolean onlyRetrieveFromCache;
  private Options options = new Options();
  private int overrideHeight = -1;
  private int overrideWidth = -1;
  private Drawable placeholderDrawable;
  private int placeholderId;
  private Priority priority = Priority.NORMAL;
  private Class<?> resourceClass = Object.class;
  private Key signature = EmptySignature.obtain();
  private float sizeMultiplier = 1.0F;
  private Resources.Theme theme;
  private Map<Class<?>, Transformation<?>> transformations = new HashMap();
  private boolean useUnlimitedSourceGeneratorsPool;
  
  public static RequestOptions bitmapTransform(@NonNull Transformation<Bitmap> paramTransformation)
  {
    return new RequestOptions().transform(paramTransformation);
  }
  
  public static RequestOptions centerCropTransform()
  {
    if (centerCropOptions == null) {
      centerCropOptions = new RequestOptions().centerCrop().autoClone();
    }
    return centerCropOptions;
  }
  
  public static RequestOptions centerInsideTransform()
  {
    if (centerInsideOptions == null) {
      centerInsideOptions = new RequestOptions().centerInside().autoClone();
    }
    return centerInsideOptions;
  }
  
  public static RequestOptions circleCropTransform()
  {
    if (circleCropOptions == null) {
      circleCropOptions = new RequestOptions().circleCrop().autoClone();
    }
    return circleCropOptions;
  }
  
  public static RequestOptions decodeTypeOf(@NonNull Class<?> paramClass)
  {
    return new RequestOptions().decode(paramClass);
  }
  
  public static RequestOptions diskCacheStrategyOf(@NonNull DiskCacheStrategy paramDiskCacheStrategy)
  {
    return new RequestOptions().diskCacheStrategy(paramDiskCacheStrategy);
  }
  
  public static RequestOptions downsampleOf(@NonNull DownsampleStrategy paramDownsampleStrategy)
  {
    return new RequestOptions().downsample(paramDownsampleStrategy);
  }
  
  public static RequestOptions encodeFormatOf(@NonNull Bitmap.CompressFormat paramCompressFormat)
  {
    return new RequestOptions().encodeFormat(paramCompressFormat);
  }
  
  public static RequestOptions encodeQualityOf(int paramInt)
  {
    return new RequestOptions().encodeQuality(paramInt);
  }
  
  public static RequestOptions errorOf(int paramInt)
  {
    return new RequestOptions().error(paramInt);
  }
  
  public static RequestOptions errorOf(@Nullable Drawable paramDrawable)
  {
    return new RequestOptions().error(paramDrawable);
  }
  
  public static RequestOptions fitCenterTransform()
  {
    if (fitCenterOptions == null) {
      fitCenterOptions = new RequestOptions().fitCenter().autoClone();
    }
    return fitCenterOptions;
  }
  
  public static RequestOptions formatOf(@NonNull DecodeFormat paramDecodeFormat)
  {
    return new RequestOptions().format(paramDecodeFormat);
  }
  
  public static RequestOptions frameOf(long paramLong)
  {
    return new RequestOptions().frame(paramLong);
  }
  
  private boolean isSet(int paramInt)
  {
    return isSet(this.fields, paramInt);
  }
  
  private static boolean isSet(int paramInt1, int paramInt2)
  {
    return (paramInt1 & paramInt2) != 0;
  }
  
  public static RequestOptions noAnimation()
  {
    if (noAnimationOptions == null) {
      noAnimationOptions = new RequestOptions().dontAnimate().autoClone();
    }
    return noAnimationOptions;
  }
  
  public static RequestOptions noTransformation()
  {
    if (noTransformOptions == null) {
      noTransformOptions = new RequestOptions().dontTransform().autoClone();
    }
    return noTransformOptions;
  }
  
  public static <T> RequestOptions option(@NonNull Option<T> paramOption, @NonNull T paramT)
  {
    return new RequestOptions().set(paramOption, paramT);
  }
  
  public static RequestOptions overrideOf(int paramInt)
  {
    return overrideOf(paramInt, paramInt);
  }
  
  public static RequestOptions overrideOf(int paramInt1, int paramInt2)
  {
    return new RequestOptions().override(paramInt1, paramInt2);
  }
  
  public static RequestOptions placeholderOf(int paramInt)
  {
    return new RequestOptions().placeholder(paramInt);
  }
  
  public static RequestOptions placeholderOf(@Nullable Drawable paramDrawable)
  {
    return new RequestOptions().placeholder(paramDrawable);
  }
  
  public static RequestOptions priorityOf(@NonNull Priority paramPriority)
  {
    return new RequestOptions().priority(paramPriority);
  }
  
  private RequestOptions selfOrThrowIfLocked()
  {
    if (this.isLocked) {
      throw new IllegalStateException("You cannot modify locked RequestOptions, consider clone()");
    }
    return this;
  }
  
  public static RequestOptions signatureOf(@NonNull Key paramKey)
  {
    return new RequestOptions().signature(paramKey);
  }
  
  public static RequestOptions sizeMultiplierOf(float paramFloat)
  {
    return new RequestOptions().sizeMultiplier(paramFloat);
  }
  
  public static RequestOptions skipMemoryCacheOf(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (skipMemoryCacheTrueOptions == null) {
        skipMemoryCacheTrueOptions = new RequestOptions().skipMemoryCache(true).autoClone();
      }
      return skipMemoryCacheTrueOptions;
    }
    if (skipMemoryCacheFalseOptions == null) {
      skipMemoryCacheFalseOptions = new RequestOptions().skipMemoryCache(false).autoClone();
    }
    return skipMemoryCacheFalseOptions;
  }
  
  public RequestOptions apply(RequestOptions paramRequestOptions)
  {
    if (this.isAutoCloneEnabled) {
      return clone().apply(paramRequestOptions);
    }
    if (isSet(paramRequestOptions.fields, 2)) {
      this.sizeMultiplier = paramRequestOptions.sizeMultiplier;
    }
    if (isSet(paramRequestOptions.fields, 262144)) {
      this.useUnlimitedSourceGeneratorsPool = paramRequestOptions.useUnlimitedSourceGeneratorsPool;
    }
    if (isSet(paramRequestOptions.fields, 4)) {
      this.diskCacheStrategy = paramRequestOptions.diskCacheStrategy;
    }
    if (isSet(paramRequestOptions.fields, 8)) {
      this.priority = paramRequestOptions.priority;
    }
    if (isSet(paramRequestOptions.fields, 16)) {
      this.errorPlaceholder = paramRequestOptions.errorPlaceholder;
    }
    if (isSet(paramRequestOptions.fields, 32)) {
      this.errorId = paramRequestOptions.errorId;
    }
    if (isSet(paramRequestOptions.fields, 64)) {
      this.placeholderDrawable = paramRequestOptions.placeholderDrawable;
    }
    if (isSet(paramRequestOptions.fields, 128)) {
      this.placeholderId = paramRequestOptions.placeholderId;
    }
    if (isSet(paramRequestOptions.fields, 256)) {
      this.isCacheable = paramRequestOptions.isCacheable;
    }
    if (isSet(paramRequestOptions.fields, 512))
    {
      this.overrideWidth = paramRequestOptions.overrideWidth;
      this.overrideHeight = paramRequestOptions.overrideHeight;
    }
    if (isSet(paramRequestOptions.fields, 1024)) {
      this.signature = paramRequestOptions.signature;
    }
    if (isSet(paramRequestOptions.fields, 4096)) {
      this.resourceClass = paramRequestOptions.resourceClass;
    }
    if (isSet(paramRequestOptions.fields, 8192)) {
      this.fallbackDrawable = paramRequestOptions.fallbackDrawable;
    }
    if (isSet(paramRequestOptions.fields, 16384)) {
      this.fallbackId = paramRequestOptions.fallbackId;
    }
    if (isSet(paramRequestOptions.fields, 32768)) {
      this.theme = paramRequestOptions.theme;
    }
    if (isSet(paramRequestOptions.fields, 65536)) {
      this.isTransformationAllowed = paramRequestOptions.isTransformationAllowed;
    }
    if (isSet(paramRequestOptions.fields, 131072)) {
      this.isTransformationRequired = paramRequestOptions.isTransformationRequired;
    }
    if (isSet(paramRequestOptions.fields, 2048)) {
      this.transformations.putAll(paramRequestOptions.transformations);
    }
    if (isSet(paramRequestOptions.fields, 524288)) {
      this.onlyRetrieveFromCache = paramRequestOptions.onlyRetrieveFromCache;
    }
    if (!this.isTransformationAllowed)
    {
      this.transformations.clear();
      this.fields &= 0xF7FF;
      this.isTransformationRequired = false;
      this.fields &= 0xFFFDFFFF;
    }
    this.fields |= paramRequestOptions.fields;
    this.options.putAll(paramRequestOptions.options);
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions autoClone()
  {
    if ((this.isLocked) && (!this.isAutoCloneEnabled)) {
      throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
    }
    this.isAutoCloneEnabled = true;
    return lock();
  }
  
  public RequestOptions centerCrop()
  {
    return transform(DownsampleStrategy.CENTER_OUTSIDE, new CenterCrop());
  }
  
  public RequestOptions centerInside()
  {
    return transform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
  }
  
  public RequestOptions circleCrop()
  {
    return transform(DownsampleStrategy.CENTER_INSIDE, new CircleCrop());
  }
  
  public RequestOptions clone()
  {
    try
    {
      RequestOptions localRequestOptions = (RequestOptions)super.clone();
      localRequestOptions.options = new Options();
      localRequestOptions.options.putAll(this.options);
      localRequestOptions.transformations = new HashMap();
      localRequestOptions.transformations.putAll(this.transformations);
      localRequestOptions.isLocked = false;
      localRequestOptions.isAutoCloneEnabled = false;
      return localRequestOptions;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException(localCloneNotSupportedException);
    }
  }
  
  public RequestOptions decode(@NonNull Class<?> paramClass)
  {
    if (this.isAutoCloneEnabled) {
      return clone().decode(paramClass);
    }
    this.resourceClass = ((Class)Preconditions.checkNotNull(paramClass));
    this.fields |= 0x1000;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions diskCacheStrategy(@NonNull DiskCacheStrategy paramDiskCacheStrategy)
  {
    if (this.isAutoCloneEnabled) {
      return clone().diskCacheStrategy(paramDiskCacheStrategy);
    }
    this.diskCacheStrategy = ((DiskCacheStrategy)Preconditions.checkNotNull(paramDiskCacheStrategy));
    this.fields |= 0x4;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions dontAnimate()
  {
    if (this.isAutoCloneEnabled) {
      return clone().dontAnimate();
    }
    set(ByteBufferGifDecoder.DISABLE_ANIMATION, Boolean.valueOf(true));
    set(StreamGifDecoder.DISABLE_ANIMATION, Boolean.valueOf(true));
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions dontTransform()
  {
    if (this.isAutoCloneEnabled) {
      return clone().dontTransform();
    }
    this.transformations.clear();
    this.fields &= 0xF7FF;
    this.isTransformationRequired = false;
    this.fields &= 0xFFFDFFFF;
    this.isTransformationAllowed = false;
    this.fields |= 0x10000;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions downsample(@NonNull DownsampleStrategy paramDownsampleStrategy)
  {
    return set(Downsampler.DOWNSAMPLE_STRATEGY, Preconditions.checkNotNull(paramDownsampleStrategy));
  }
  
  public RequestOptions encodeFormat(@NonNull Bitmap.CompressFormat paramCompressFormat)
  {
    return set(BitmapEncoder.COMPRESSION_FORMAT, Preconditions.checkNotNull(paramCompressFormat));
  }
  
  public RequestOptions encodeQuality(int paramInt)
  {
    return set(BitmapEncoder.COMPRESSION_QUALITY, Integer.valueOf(paramInt));
  }
  
  public RequestOptions error(int paramInt)
  {
    if (this.isAutoCloneEnabled) {
      return clone().error(paramInt);
    }
    this.errorId = paramInt;
    this.fields |= 0x20;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions error(@Nullable Drawable paramDrawable)
  {
    if (this.isAutoCloneEnabled) {
      return clone().error(paramDrawable);
    }
    this.errorPlaceholder = paramDrawable;
    this.fields |= 0x10;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions fallback(int paramInt)
  {
    if (this.isAutoCloneEnabled) {
      return clone().fallback(paramInt);
    }
    this.fallbackId = paramInt;
    this.fields |= 0x4000;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions fallback(Drawable paramDrawable)
  {
    if (this.isAutoCloneEnabled) {
      return clone().fallback(paramDrawable);
    }
    this.fallbackDrawable = paramDrawable;
    this.fields |= 0x2000;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions fitCenter()
  {
    return transform(DownsampleStrategy.FIT_CENTER, new FitCenter());
  }
  
  public RequestOptions format(@NonNull DecodeFormat paramDecodeFormat)
  {
    return set(Downsampler.DECODE_FORMAT, Preconditions.checkNotNull(paramDecodeFormat));
  }
  
  public RequestOptions frame(long paramLong)
  {
    return set(VideoBitmapDecoder.TARGET_FRAME, Long.valueOf(paramLong));
  }
  
  public final DiskCacheStrategy getDiskCacheStrategy()
  {
    return this.diskCacheStrategy;
  }
  
  public final int getErrorId()
  {
    return this.errorId;
  }
  
  public final Drawable getErrorPlaceholder()
  {
    return this.errorPlaceholder;
  }
  
  public final Drawable getFallbackDrawable()
  {
    return this.fallbackDrawable;
  }
  
  public final int getFallbackId()
  {
    return this.fallbackId;
  }
  
  public final boolean getOnlyRetrieveFromCache()
  {
    return this.onlyRetrieveFromCache;
  }
  
  public final Options getOptions()
  {
    return this.options;
  }
  
  public final int getOverrideHeight()
  {
    return this.overrideHeight;
  }
  
  public final int getOverrideWidth()
  {
    return this.overrideWidth;
  }
  
  public final Drawable getPlaceholderDrawable()
  {
    return this.placeholderDrawable;
  }
  
  public final int getPlaceholderId()
  {
    return this.placeholderId;
  }
  
  public final Priority getPriority()
  {
    return this.priority;
  }
  
  public final Class<?> getResourceClass()
  {
    return this.resourceClass;
  }
  
  public final Key getSignature()
  {
    return this.signature;
  }
  
  public final float getSizeMultiplier()
  {
    return this.sizeMultiplier;
  }
  
  public final Resources.Theme getTheme()
  {
    return this.theme;
  }
  
  public final Map<Class<?>, Transformation<?>> getTransformations()
  {
    return this.transformations;
  }
  
  public final boolean getUseUnlimitedSourceGeneratorsPool()
  {
    return this.useUnlimitedSourceGeneratorsPool;
  }
  
  public final boolean isLocked()
  {
    return this.isLocked;
  }
  
  public final boolean isMemoryCacheable()
  {
    return this.isCacheable;
  }
  
  public final boolean isPrioritySet()
  {
    return isSet(8);
  }
  
  public final boolean isTransformationAllowed()
  {
    return this.isTransformationAllowed;
  }
  
  public final boolean isTransformationRequired()
  {
    return this.isTransformationRequired;
  }
  
  public final boolean isTransformationSet()
  {
    return isSet(2048);
  }
  
  public final boolean isValidOverride()
  {
    return Util.isValidDimensions(this.overrideWidth, this.overrideHeight);
  }
  
  public RequestOptions lock()
  {
    this.isLocked = true;
    return this;
  }
  
  public RequestOptions onlyRetrieveFromCache(boolean paramBoolean)
  {
    if (this.isAutoCloneEnabled) {
      return clone().onlyRetrieveFromCache(paramBoolean);
    }
    this.onlyRetrieveFromCache = paramBoolean;
    this.fields |= 0x80000;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions optionalCenterCrop()
  {
    return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, new CenterCrop());
  }
  
  public RequestOptions optionalCenterInside()
  {
    return optionalTransform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
  }
  
  public RequestOptions optionalCircleCrop()
  {
    return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, new CircleCrop());
  }
  
  public RequestOptions optionalFitCenter()
  {
    return optionalTransform(DownsampleStrategy.FIT_CENTER, new FitCenter());
  }
  
  public RequestOptions optionalTransform(Transformation<Bitmap> paramTransformation)
  {
    if (this.isAutoCloneEnabled) {
      return clone().optionalTransform(paramTransformation);
    }
    optionalTransform(Bitmap.class, paramTransformation);
    optionalTransform(BitmapDrawable.class, new BitmapDrawableTransformation(paramTransformation));
    optionalTransform(GifDrawable.class, new GifDrawableTransformation(paramTransformation));
    return selfOrThrowIfLocked();
  }
  
  final RequestOptions optionalTransform(DownsampleStrategy paramDownsampleStrategy, Transformation<Bitmap> paramTransformation)
  {
    if (this.isAutoCloneEnabled) {
      return clone().optionalTransform(paramDownsampleStrategy, paramTransformation);
    }
    downsample(paramDownsampleStrategy);
    return optionalTransform(paramTransformation);
  }
  
  public <T> RequestOptions optionalTransform(Class<T> paramClass, Transformation<T> paramTransformation)
  {
    if (this.isAutoCloneEnabled) {
      return clone().optionalTransform(paramClass, paramTransformation);
    }
    Preconditions.checkNotNull(paramClass);
    Preconditions.checkNotNull(paramTransformation);
    this.transformations.put(paramClass, paramTransformation);
    this.fields |= 0x800;
    this.isTransformationAllowed = true;
    this.fields |= 0x10000;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions override(int paramInt)
  {
    return override(paramInt, paramInt);
  }
  
  public RequestOptions override(int paramInt1, int paramInt2)
  {
    if (this.isAutoCloneEnabled) {
      return clone().override(paramInt1, paramInt2);
    }
    this.overrideWidth = paramInt1;
    this.overrideHeight = paramInt2;
    this.fields |= 0x200;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions placeholder(int paramInt)
  {
    if (this.isAutoCloneEnabled) {
      return clone().placeholder(paramInt);
    }
    this.placeholderId = paramInt;
    this.fields |= 0x80;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions placeholder(@Nullable Drawable paramDrawable)
  {
    if (this.isAutoCloneEnabled) {
      return clone().placeholder(paramDrawable);
    }
    this.placeholderDrawable = paramDrawable;
    this.fields |= 0x40;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions priority(@NonNull Priority paramPriority)
  {
    if (this.isAutoCloneEnabled) {
      return clone().priority(paramPriority);
    }
    this.priority = ((Priority)Preconditions.checkNotNull(paramPriority));
    this.fields |= 0x8;
    return selfOrThrowIfLocked();
  }
  
  public <T> RequestOptions set(@NonNull Option<T> paramOption, @NonNull T paramT)
  {
    if (this.isAutoCloneEnabled) {
      return clone().set(paramOption, paramT);
    }
    Preconditions.checkNotNull(paramOption);
    Preconditions.checkNotNull(paramT);
    this.options.set(paramOption, paramT);
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions signature(@NonNull Key paramKey)
  {
    if (this.isAutoCloneEnabled) {
      return clone().signature(paramKey);
    }
    this.signature = ((Key)Preconditions.checkNotNull(paramKey));
    this.fields |= 0x400;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions sizeMultiplier(float paramFloat)
  {
    if (this.isAutoCloneEnabled) {
      return clone().sizeMultiplier(paramFloat);
    }
    if ((paramFloat < 0.0F) || (paramFloat > 1.0F)) {
      throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
    }
    this.sizeMultiplier = paramFloat;
    this.fields |= 0x2;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions skipMemoryCache(boolean paramBoolean)
  {
    boolean bool = true;
    if (this.isAutoCloneEnabled) {
      return clone().skipMemoryCache(true);
    }
    if (!paramBoolean) {}
    for (paramBoolean = bool;; paramBoolean = false)
    {
      this.isCacheable = paramBoolean;
      this.fields |= 0x100;
      return selfOrThrowIfLocked();
    }
  }
  
  public RequestOptions theme(Resources.Theme paramTheme)
  {
    if (this.isAutoCloneEnabled) {
      return clone().theme(paramTheme);
    }
    this.theme = paramTheme;
    this.fields |= 0x8000;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions transform(@NonNull Transformation<Bitmap> paramTransformation)
  {
    if (this.isAutoCloneEnabled) {
      return clone().transform(paramTransformation);
    }
    optionalTransform(paramTransformation);
    this.isTransformationRequired = true;
    this.fields |= 0x20000;
    return selfOrThrowIfLocked();
  }
  
  final RequestOptions transform(DownsampleStrategy paramDownsampleStrategy, Transformation<Bitmap> paramTransformation)
  {
    if (this.isAutoCloneEnabled) {
      return clone().transform(paramDownsampleStrategy, paramTransformation);
    }
    downsample(paramDownsampleStrategy);
    return transform(paramTransformation);
  }
  
  public <T> RequestOptions transform(Class<T> paramClass, Transformation<T> paramTransformation)
  {
    if (this.isAutoCloneEnabled) {
      return clone().transform(paramClass, paramTransformation);
    }
    optionalTransform(paramClass, paramTransformation);
    this.isTransformationRequired = true;
    this.fields |= 0x20000;
    return selfOrThrowIfLocked();
  }
  
  public RequestOptions useUnlimitedSourceGeneratorsPool(boolean paramBoolean)
  {
    if (this.isAutoCloneEnabled) {
      return clone().useUnlimitedSourceGeneratorsPool(paramBoolean);
    }
    this.useUnlimitedSourceGeneratorsPool = paramBoolean;
    this.fields |= 0x40000;
    return selfOrThrowIfLocked();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\request\RequestOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */