package com.bumptech.glide;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.InputStreamRewinder.Factory;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.BitmapPreFiller;
import com.bumptech.glide.load.engine.prefill.PreFillType.Builder;
import com.bumptech.glide.load.model.AssetUriLoader.FileDescriptorFactory;
import com.bumptech.glide.load.model.AssetUriLoader.StreamFactory;
import com.bumptech.glide.load.model.ByteArrayLoader.ByteBufferFactory;
import com.bumptech.glide.load.model.ByteArrayLoader.StreamFactory;
import com.bumptech.glide.load.model.ByteBufferEncoder;
import com.bumptech.glide.load.model.ByteBufferFileLoader.Factory;
import com.bumptech.glide.load.model.DataUrlLoader.StreamFactory;
import com.bumptech.glide.load.model.FileLoader.FileDescriptorFactory;
import com.bumptech.glide.load.model.FileLoader.StreamFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.MediaStoreFileLoader.Factory;
import com.bumptech.glide.load.model.ResourceLoader.FileDescriptorFactory;
import com.bumptech.glide.load.model.ResourceLoader.StreamFactory;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.StringLoader.FileDescriptorFactory;
import com.bumptech.glide.load.model.StringLoader.StreamFactory;
import com.bumptech.glide.load.model.UnitModelLoader.Factory;
import com.bumptech.glide.load.model.UriLoader.FileDescriptorFactory;
import com.bumptech.glide.load.model.UriLoader.StreamFactory;
import com.bumptech.glide.load.model.UrlUriLoader.StreamFactory;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Factory;
import com.bumptech.glide.load.model.stream.HttpUriLoader.Factory;
import com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader.Factory;
import com.bumptech.glide.load.model.stream.MediaStoreVideoThumbLoader.Factory;
import com.bumptech.glide.load.model.stream.UrlLoader.StreamFactory;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;
import com.bumptech.glide.load.resource.bytes.ByteBufferRewinder.Factory;
import com.bumptech.glide.load.resource.file.FileDecoder;
import com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableEncoder;
import com.bumptech.glide.load.resource.gif.GifFrameResourceDecoder;
import com.bumptech.glide.load.resource.gif.StreamGifDecoder;
import com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.BitmapDrawableTranscoder;
import com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.module.ManifestParser;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@TargetApi(14)
public class Glide
  implements ComponentCallbacks2
{
  private static final String DEFAULT_DISK_CACHE_DIR = "image_manager_disk_cache";
  private static final String TAG = "Glide";
  private static volatile Glide glide;
  private static volatile boolean isInitializing;
  private final ArrayPool arrayPool;
  private final BitmapPool bitmapPool;
  private final BitmapPreFiller bitmapPreFiller;
  private final ConnectivityMonitorFactory connectivityMonitorFactory;
  private final Engine engine;
  private final GlideContext glideContext;
  private final List<RequestManager> managers = new ArrayList();
  private final MemoryCache memoryCache;
  private MemoryCategory memoryCategory = MemoryCategory.NORMAL;
  private final Registry registry;
  private final RequestManagerRetriever requestManagerRetriever;
  
  @TargetApi(14)
  Glide(Context paramContext, Engine paramEngine, MemoryCache paramMemoryCache, BitmapPool paramBitmapPool, ArrayPool paramArrayPool, RequestManagerRetriever paramRequestManagerRetriever, ConnectivityMonitorFactory paramConnectivityMonitorFactory, int paramInt, RequestOptions paramRequestOptions)
  {
    this.engine = paramEngine;
    this.bitmapPool = paramBitmapPool;
    this.arrayPool = paramArrayPool;
    this.memoryCache = paramMemoryCache;
    this.requestManagerRetriever = paramRequestManagerRetriever;
    this.connectivityMonitorFactory = paramConnectivityMonitorFactory;
    this.bitmapPreFiller = new BitmapPreFiller(paramMemoryCache, paramBitmapPool, (DecodeFormat)paramRequestOptions.getOptions().get(Downsampler.DECODE_FORMAT));
    paramMemoryCache = paramContext.getResources();
    this.registry = new Registry();
    this.registry.register(new DefaultImageHeaderParser());
    paramRequestManagerRetriever = new Downsampler(this.registry.getImageHeaderParsers(), paramMemoryCache.getDisplayMetrics(), paramBitmapPool, paramArrayPool);
    paramConnectivityMonitorFactory = new ByteBufferGifDecoder(paramContext, this.registry.getImageHeaderParsers(), paramBitmapPool, paramArrayPool);
    this.registry.register(ByteBuffer.class, new ByteBufferEncoder()).register(InputStream.class, new StreamEncoder(paramArrayPool)).append(ByteBuffer.class, Bitmap.class, new ByteBufferBitmapDecoder(paramRequestManagerRetriever)).append(InputStream.class, Bitmap.class, new StreamBitmapDecoder(paramRequestManagerRetriever, paramArrayPool)).append(ParcelFileDescriptor.class, Bitmap.class, new VideoBitmapDecoder(paramBitmapPool)).register(Bitmap.class, new BitmapEncoder()).append(ByteBuffer.class, BitmapDrawable.class, new BitmapDrawableDecoder(paramMemoryCache, paramBitmapPool, new ByteBufferBitmapDecoder(paramRequestManagerRetriever))).append(InputStream.class, BitmapDrawable.class, new BitmapDrawableDecoder(paramMemoryCache, paramBitmapPool, new StreamBitmapDecoder(paramRequestManagerRetriever, paramArrayPool))).append(ParcelFileDescriptor.class, BitmapDrawable.class, new BitmapDrawableDecoder(paramMemoryCache, paramBitmapPool, new VideoBitmapDecoder(paramBitmapPool))).register(BitmapDrawable.class, new BitmapDrawableEncoder(paramBitmapPool, new BitmapEncoder())).prepend(InputStream.class, GifDrawable.class, new StreamGifDecoder(this.registry.getImageHeaderParsers(), paramConnectivityMonitorFactory, paramArrayPool)).prepend(ByteBuffer.class, GifDrawable.class, paramConnectivityMonitorFactory).register(GifDrawable.class, new GifDrawableEncoder()).append(GifDecoder.class, GifDecoder.class, new UnitModelLoader.Factory()).append(GifDecoder.class, Bitmap.class, new GifFrameResourceDecoder(paramBitmapPool)).register(new ByteBufferRewinder.Factory()).append(File.class, ByteBuffer.class, new ByteBufferFileLoader.Factory()).append(File.class, InputStream.class, new FileLoader.StreamFactory()).append(File.class, File.class, new FileDecoder()).append(File.class, ParcelFileDescriptor.class, new FileLoader.FileDescriptorFactory()).append(File.class, File.class, new UnitModelLoader.Factory()).register(new InputStreamRewinder.Factory(paramArrayPool)).append(Integer.TYPE, InputStream.class, new ResourceLoader.StreamFactory(paramMemoryCache)).append(Integer.TYPE, ParcelFileDescriptor.class, new ResourceLoader.FileDescriptorFactory(paramMemoryCache)).append(Integer.class, InputStream.class, new ResourceLoader.StreamFactory(paramMemoryCache)).append(Integer.class, ParcelFileDescriptor.class, new ResourceLoader.FileDescriptorFactory(paramMemoryCache)).append(String.class, InputStream.class, new DataUrlLoader.StreamFactory()).append(String.class, InputStream.class, new StringLoader.StreamFactory()).append(String.class, ParcelFileDescriptor.class, new StringLoader.FileDescriptorFactory()).append(Uri.class, InputStream.class, new HttpUriLoader.Factory()).append(Uri.class, InputStream.class, new AssetUriLoader.StreamFactory(paramContext.getAssets())).append(Uri.class, ParcelFileDescriptor.class, new AssetUriLoader.FileDescriptorFactory(paramContext.getAssets())).append(Uri.class, InputStream.class, new MediaStoreImageThumbLoader.Factory(paramContext)).append(Uri.class, InputStream.class, new MediaStoreVideoThumbLoader.Factory(paramContext)).append(Uri.class, InputStream.class, new UriLoader.StreamFactory(paramContext.getContentResolver())).append(Uri.class, ParcelFileDescriptor.class, new UriLoader.FileDescriptorFactory(paramContext.getContentResolver())).append(Uri.class, InputStream.class, new UrlUriLoader.StreamFactory()).append(URL.class, InputStream.class, new UrlLoader.StreamFactory()).append(Uri.class, File.class, new MediaStoreFileLoader.Factory(paramContext)).append(GlideUrl.class, InputStream.class, new HttpGlideUrlLoader.Factory()).append(byte[].class, ByteBuffer.class, new ByteArrayLoader.ByteBufferFactory()).append(byte[].class, InputStream.class, new ByteArrayLoader.StreamFactory()).register(Bitmap.class, BitmapDrawable.class, new BitmapDrawableTranscoder(paramMemoryCache, paramBitmapPool)).register(Bitmap.class, byte[].class, new BitmapBytesTranscoder()).register(GifDrawable.class, byte[].class, new GifDrawableBytesTranscoder());
    paramMemoryCache = new ImageViewTargetFactory();
    this.glideContext = new GlideContext(paramContext, this.registry, paramMemoryCache, paramRequestOptions, paramEngine, this, paramInt);
  }
  
  private static void checkAndInitializeGlide(Context paramContext)
  {
    if (isInitializing) {
      throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
    }
    isInitializing = true;
    initializeGlide(paramContext);
    isInitializing = false;
  }
  
  public static Glide get(Context paramContext)
  {
    if (glide == null) {}
    try
    {
      if (glide == null) {
        checkAndInitializeGlide(paramContext);
      }
      return glide;
    }
    finally {}
  }
  
  @Nullable
  private static GeneratedAppGlideModule getAnnotationGeneratedGlideModules()
  {
    Object localObject = null;
    try
    {
      GeneratedAppGlideModule localGeneratedAppGlideModule = (GeneratedAppGlideModule)Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").newInstance();
      localObject = localGeneratedAppGlideModule;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while (!Log.isLoggable("Glide", 5)) {}
      Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
      return null;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", localIllegalAccessException);
    }
    return (GeneratedAppGlideModule)localObject;
  }
  
  @Nullable
  public static File getPhotoCacheDir(Context paramContext)
  {
    return getPhotoCacheDir(paramContext, "image_manager_disk_cache");
  }
  
  @Nullable
  public static File getPhotoCacheDir(Context paramContext, String paramString)
  {
    paramContext = paramContext.getCacheDir();
    if (paramContext != null)
    {
      paramString = new File(paramContext, paramString);
      paramContext = paramString;
      if (!paramString.mkdirs()) {
        if (paramString.exists())
        {
          paramContext = paramString;
          if (paramString.isDirectory()) {}
        }
        else
        {
          paramContext = null;
        }
      }
      return paramContext;
    }
    if (Log.isLoggable("Glide", 6)) {
      Log.e("Glide", "default disk cache dir is null");
    }
    return null;
  }
  
  private static RequestManagerRetriever getRetriever(@Nullable Context paramContext)
  {
    Preconditions.checkNotNull(paramContext, "You cannot start a load on a not yet attached View or a  Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
    return get(paramContext).getRequestManagerRetriever();
  }
  
  @VisibleForTesting
  public static void init(Glide paramGlide)
  {
    try
    {
      glide = paramGlide;
      return;
    }
    finally
    {
      paramGlide = finally;
      throw paramGlide;
    }
  }
  
  private static void initializeGlide(Context paramContext)
  {
    Context localContext = paramContext.getApplicationContext();
    GeneratedAppGlideModule localGeneratedAppGlideModule = getAnnotationGeneratedGlideModules();
    paramContext = Collections.emptyList();
    if ((localGeneratedAppGlideModule == null) || (localGeneratedAppGlideModule.isManifestParsingEnabled())) {
      paramContext = new ManifestParser(localContext).parse();
    }
    Object localObject2;
    if ((localGeneratedAppGlideModule != null) && (!localGeneratedAppGlideModule.getExcludedModuleClasses().isEmpty()))
    {
      localObject1 = localGeneratedAppGlideModule.getExcludedModuleClasses();
      localObject2 = paramContext.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        GlideModule localGlideModule = (GlideModule)((Iterator)localObject2).next();
        if (((Set)localObject1).contains(localGlideModule.getClass()))
        {
          if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "AppGlideModule excludes manifest GlideModule: " + localGlideModule);
          }
          ((Iterator)localObject2).remove();
        }
      }
    }
    if (Log.isLoggable("Glide", 3))
    {
      localObject1 = paramContext.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (GlideModule)((Iterator)localObject1).next();
        Log.d("Glide", "Discovered GlideModule from manifest: " + localObject2.getClass());
      }
    }
    if (localGeneratedAppGlideModule != null) {}
    for (Object localObject1 = localGeneratedAppGlideModule.getRequestManagerFactory();; localObject1 = null)
    {
      localObject1 = new GlideBuilder().setRequestManagerFactory((RequestManagerRetriever.RequestManagerFactory)localObject1);
      localObject2 = paramContext.iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((GlideModule)((Iterator)localObject2).next()).applyOptions(localContext, (GlideBuilder)localObject1);
      }
    }
    if (localGeneratedAppGlideModule != null) {
      localGeneratedAppGlideModule.applyOptions(localContext, (GlideBuilder)localObject1);
    }
    localObject1 = ((GlideBuilder)localObject1).build(localContext);
    paramContext = paramContext.iterator();
    while (paramContext.hasNext()) {
      ((GlideModule)paramContext.next()).registerComponents(localContext, (Glide)localObject1, ((Glide)localObject1).registry);
    }
    if (localGeneratedAppGlideModule != null) {
      localGeneratedAppGlideModule.registerComponents(localContext, (Glide)localObject1, ((Glide)localObject1).registry);
    }
    glide = (Glide)localObject1;
  }
  
  @VisibleForTesting
  public static void tearDown()
  {
    try
    {
      glide = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static RequestManager with(Activity paramActivity)
  {
    return getRetriever(paramActivity).get(paramActivity);
  }
  
  public static RequestManager with(android.app.Fragment paramFragment)
  {
    return getRetriever(paramFragment.getActivity()).get(paramFragment);
  }
  
  public static RequestManager with(Context paramContext)
  {
    return getRetriever(paramContext).get(paramContext);
  }
  
  public static RequestManager with(android.support.v4.app.Fragment paramFragment)
  {
    return getRetriever(paramFragment.getActivity()).get(paramFragment);
  }
  
  public static RequestManager with(FragmentActivity paramFragmentActivity)
  {
    return getRetriever(paramFragmentActivity).get(paramFragmentActivity);
  }
  
  public static RequestManager with(View paramView)
  {
    return getRetriever(paramView.getContext()).get(paramView);
  }
  
  public void clearDiskCache()
  {
    Util.assertBackgroundThread();
    this.engine.clearDiskCache();
  }
  
  public void clearMemory()
  {
    Util.assertMainThread();
    this.memoryCache.clearMemory();
    this.bitmapPool.clearMemory();
    this.arrayPool.clearMemory();
  }
  
  public ArrayPool getArrayPool()
  {
    return this.arrayPool;
  }
  
  public BitmapPool getBitmapPool()
  {
    return this.bitmapPool;
  }
  
  ConnectivityMonitorFactory getConnectivityMonitorFactory()
  {
    return this.connectivityMonitorFactory;
  }
  
  public Context getContext()
  {
    return this.glideContext.getBaseContext();
  }
  
  GlideContext getGlideContext()
  {
    return this.glideContext;
  }
  
  public Registry getRegistry()
  {
    return this.registry;
  }
  
  public RequestManagerRetriever getRequestManagerRetriever()
  {
    return this.requestManagerRetriever;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {}
  
  public void onLowMemory()
  {
    clearMemory();
  }
  
  public void onTrimMemory(int paramInt)
  {
    trimMemory(paramInt);
  }
  
  public void preFillBitmapPool(PreFillType.Builder... paramVarArgs)
  {
    this.bitmapPreFiller.preFill(paramVarArgs);
  }
  
  void registerRequestManager(RequestManager paramRequestManager)
  {
    synchronized (this.managers)
    {
      if (this.managers.contains(paramRequestManager)) {
        throw new IllegalStateException("Cannot register already registered manager");
      }
    }
    this.managers.add(paramRequestManager);
  }
  
  void removeFromManagers(Target<?> paramTarget)
  {
    synchronized (this.managers)
    {
      Iterator localIterator = this.managers.iterator();
      while (localIterator.hasNext()) {
        if (((RequestManager)localIterator.next()).untrack(paramTarget)) {
          return;
        }
      }
      throw new IllegalStateException("Failed to remove target from managers");
    }
  }
  
  public MemoryCategory setMemoryCategory(MemoryCategory paramMemoryCategory)
  {
    Util.assertMainThread();
    this.memoryCache.setSizeMultiplier(paramMemoryCategory.getMultiplier());
    this.bitmapPool.setSizeMultiplier(paramMemoryCategory.getMultiplier());
    MemoryCategory localMemoryCategory = this.memoryCategory;
    this.memoryCategory = paramMemoryCategory;
    return localMemoryCategory;
  }
  
  public void trimMemory(int paramInt)
  {
    Util.assertMainThread();
    this.memoryCache.trimMemory(paramInt);
    this.bitmapPool.trimMemory(paramInt);
    this.arrayPool.trimMemory(paramInt);
  }
  
  void unregisterRequestManager(RequestManager paramRequestManager)
  {
    synchronized (this.managers)
    {
      if (!this.managers.contains(paramRequestManager)) {
        throw new IllegalStateException("Cannot register not yet registered manager");
      }
    }
    this.managers.remove(paramRequestManager);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\Glide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */