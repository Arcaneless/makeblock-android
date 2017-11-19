package cc.makeblock.makeblock.engine;

import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import java.net.URL;

public class GlideRequest<TranscodeType>
  extends RequestBuilder<TranscodeType>
{
  GlideRequest(Glide paramGlide, RequestManager paramRequestManager, Class<TranscodeType> paramClass)
  {
    super(paramGlide, paramRequestManager, paramClass);
  }
  
  GlideRequest(Class<TranscodeType> paramClass, RequestBuilder<?> paramRequestBuilder)
  {
    super(paramClass, paramRequestBuilder);
  }
  
  public GlideRequest<TranscodeType> apply(RequestOptions paramRequestOptions)
  {
    return (GlideRequest)super.apply(paramRequestOptions);
  }
  
  public GlideRequest<TranscodeType> centerCrop()
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).centerCrop();
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).centerCrop();
    return this;
  }
  
  public GlideRequest<TranscodeType> centerInside()
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).centerInside();
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).centerInside();
    return this;
  }
  
  public GlideRequest<TranscodeType> circleCrop()
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).circleCrop();
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).circleCrop();
    return this;
  }
  
  public GlideRequest<TranscodeType> clone()
  {
    return (GlideRequest)super.clone();
  }
  
  public GlideRequest<TranscodeType> decode(Class<?> paramClass)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).decode(paramClass);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).decode(paramClass);
    return this;
  }
  
  public GlideRequest<TranscodeType> diskCacheStrategy(DiskCacheStrategy paramDiskCacheStrategy)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).diskCacheStrategy(paramDiskCacheStrategy);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).diskCacheStrategy(paramDiskCacheStrategy);
    return this;
  }
  
  public GlideRequest<TranscodeType> dontAnimate()
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).dontAnimate();
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).dontAnimate();
    return this;
  }
  
  public GlideRequest<TranscodeType> dontTransform()
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).dontTransform();
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).dontTransform();
    return this;
  }
  
  public GlideRequest<TranscodeType> downsample(DownsampleStrategy paramDownsampleStrategy)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).downsample(paramDownsampleStrategy);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).downsample(paramDownsampleStrategy);
    return this;
  }
  
  public GlideRequest<TranscodeType> encodeFormat(Bitmap.CompressFormat paramCompressFormat)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).encodeFormat(paramCompressFormat);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).encodeFormat(paramCompressFormat);
    return this;
  }
  
  public GlideRequest<TranscodeType> encodeQuality(int paramInt)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).encodeQuality(paramInt);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).encodeQuality(paramInt);
    return this;
  }
  
  public GlideRequest<TranscodeType> error(int paramInt)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).error(paramInt);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).error(paramInt);
    return this;
  }
  
  public GlideRequest<TranscodeType> error(Drawable paramDrawable)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).error(paramDrawable);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).error(paramDrawable);
    return this;
  }
  
  public GlideRequest<TranscodeType> fallback(int paramInt)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).fallback(paramInt);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).fallback(paramInt);
    return this;
  }
  
  public GlideRequest<TranscodeType> fallback(Drawable paramDrawable)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).fallback(paramDrawable);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).fallback(paramDrawable);
    return this;
  }
  
  public GlideRequest<TranscodeType> fitCenter()
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).fitCenter();
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).fitCenter();
    return this;
  }
  
  public GlideRequest<TranscodeType> format(DecodeFormat paramDecodeFormat)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).format(paramDecodeFormat);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).format(paramDecodeFormat);
    return this;
  }
  
  public GlideRequest<TranscodeType> frame(long paramLong)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).frame(paramLong);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).frame(paramLong);
    return this;
  }
  
  protected GlideRequest<File> getDownloadOnlyRequest()
  {
    return new GlideRequest(File.class, this).apply(DOWNLOAD_ONLY_OPTIONS);
  }
  
  public GlideRequest<TranscodeType> listener(RequestListener<TranscodeType> paramRequestListener)
  {
    return (GlideRequest)super.listener(paramRequestListener);
  }
  
  public GlideRequest<TranscodeType> load(Uri paramUri)
  {
    return (GlideRequest)super.load(paramUri);
  }
  
  public GlideRequest<TranscodeType> load(File paramFile)
  {
    return (GlideRequest)super.load(paramFile);
  }
  
  public GlideRequest<TranscodeType> load(Integer paramInteger)
  {
    return (GlideRequest)super.load(paramInteger);
  }
  
  public GlideRequest<TranscodeType> load(Object paramObject)
  {
    return (GlideRequest)super.load(paramObject);
  }
  
  public GlideRequest<TranscodeType> load(String paramString)
  {
    return (GlideRequest)super.load(paramString);
  }
  
  public GlideRequest<TranscodeType> load(URL paramURL)
  {
    return (GlideRequest)super.load(paramURL);
  }
  
  public GlideRequest<TranscodeType> load(byte[] paramArrayOfByte)
  {
    return (GlideRequest)super.load(paramArrayOfByte);
  }
  
  public GlideRequest<TranscodeType> onlyRetrieveFromCache(boolean paramBoolean)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).onlyRetrieveFromCache(paramBoolean);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).onlyRetrieveFromCache(paramBoolean);
    return this;
  }
  
  public GlideRequest<TranscodeType> optionalCenterCrop()
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).optionalCenterCrop();
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalCenterCrop();
    return this;
  }
  
  public GlideRequest<TranscodeType> optionalCenterInside()
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).optionalCenterInside();
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalCenterInside();
    return this;
  }
  
  public GlideRequest<TranscodeType> optionalCircleCrop()
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).optionalCircleCrop();
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalCircleCrop();
    return this;
  }
  
  public GlideRequest<TranscodeType> optionalFitCenter()
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).optionalFitCenter();
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalFitCenter();
    return this;
  }
  
  public GlideRequest<TranscodeType> optionalTransform(Transformation<Bitmap> paramTransformation)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).optionalTransform(paramTransformation);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalTransform(paramTransformation);
    return this;
  }
  
  public <T> GlideRequest<TranscodeType> optionalTransform(Class<T> paramClass, Transformation<T> paramTransformation)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).optionalTransform(paramClass, paramTransformation);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalTransform(paramClass, paramTransformation);
    return this;
  }
  
  public GlideRequest<TranscodeType> override(int paramInt)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).override(paramInt);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).override(paramInt);
    return this;
  }
  
  public GlideRequest<TranscodeType> override(int paramInt1, int paramInt2)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).override(paramInt1, paramInt2);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).override(paramInt1, paramInt2);
    return this;
  }
  
  public GlideRequest<TranscodeType> placeholder(int paramInt)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).placeholder(paramInt);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).placeholder(paramInt);
    return this;
  }
  
  public GlideRequest<TranscodeType> placeholder(Drawable paramDrawable)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).placeholder(paramDrawable);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).placeholder(paramDrawable);
    return this;
  }
  
  public GlideRequest<TranscodeType> priority(Priority paramPriority)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).priority(paramPriority);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).priority(paramPriority);
    return this;
  }
  
  public <T> GlideRequest<TranscodeType> set(Option<T> paramOption, T paramT)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).set(paramOption, paramT);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).set(paramOption, paramT);
    return this;
  }
  
  public GlideRequest<TranscodeType> signature(Key paramKey)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).signature(paramKey);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).signature(paramKey);
    return this;
  }
  
  public GlideRequest<TranscodeType> sizeMultiplier(float paramFloat)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).sizeMultiplier(paramFloat);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).sizeMultiplier(paramFloat);
    return this;
  }
  
  public GlideRequest<TranscodeType> skipMemoryCache(boolean paramBoolean)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).skipMemoryCache(paramBoolean);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).skipMemoryCache(paramBoolean);
    return this;
  }
  
  public GlideRequest<TranscodeType> theme(Resources.Theme paramTheme)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).theme(paramTheme);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).theme(paramTheme);
    return this;
  }
  
  public GlideRequest<TranscodeType> thumbnail(float paramFloat)
  {
    return (GlideRequest)super.thumbnail(paramFloat);
  }
  
  public GlideRequest<TranscodeType> thumbnail(RequestBuilder<TranscodeType> paramRequestBuilder)
  {
    return (GlideRequest)super.thumbnail(paramRequestBuilder);
  }
  
  public GlideRequest<TranscodeType> transform(Transformation<Bitmap> paramTransformation)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).transform(paramTransformation);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).transform(paramTransformation);
    return this;
  }
  
  public <T> GlideRequest<TranscodeType> transform(Class<T> paramClass, Transformation<T> paramTransformation)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).transform(paramClass, paramTransformation);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).transform(paramClass, paramTransformation);
    return this;
  }
  
  public GlideRequest<TranscodeType> transition(TransitionOptions<?, ? super TranscodeType> paramTransitionOptions)
  {
    return (GlideRequest)super.transition(paramTransitionOptions);
  }
  
  public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(boolean paramBoolean)
  {
    if ((getMutableOptions() instanceof GlideOptions))
    {
      this.requestOptions = ((GlideOptions)getMutableOptions()).useUnlimitedSourceGeneratorsPool(paramBoolean);
      return this;
    }
    this.requestOptions = new GlideOptions().apply(this.requestOptions).useUnlimitedSourceGeneratorsPool(paramBoolean);
    return this;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\GlideRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */