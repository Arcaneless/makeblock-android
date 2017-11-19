package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Lock;

public final class Downsampler
{
  public static final Option<DecodeFormat> DECODE_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);
  public static final Option<DownsampleStrategy> DOWNSAMPLE_STRATEGY = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", DownsampleStrategy.AT_LEAST);
  private static final DecodeCallbacks EMPTY_CALLBACKS;
  public static final Option<Boolean> FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", Boolean.valueOf(false));
  private static final String ICO_MIME_TYPE = "image/x-ico";
  private static final int MARK_POSITION = 5242880;
  private static final Set<String> NO_DOWNSAMPLE_PRE_N_MIME_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { "image/vnd.wap.wbmp", "image/x-ico" })));
  private static final Queue<BitmapFactory.Options> OPTIONS_QUEUE = Util.createQueue(0);
  private static final String TAG = "Downsampler";
  private static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL_PRE_KITKAT;
  private static final String WBMP_MIME_TYPE = "image/vnd.wap.wbmp";
  private final BitmapPool bitmapPool;
  private final ArrayPool byteArrayPool;
  private final DisplayMetrics displayMetrics;
  private final List<ImageHeaderParser> parsers;
  
  static
  {
    EMPTY_CALLBACKS = new DecodeCallbacks()
    {
      public void onDecodeComplete(BitmapPool paramAnonymousBitmapPool, Bitmap paramAnonymousBitmap)
        throws IOException
      {}
      
      public void onObtainBounds() {}
    };
    TYPES_THAT_USE_POOL_PRE_KITKAT = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
  }
  
  public Downsampler(List<ImageHeaderParser> paramList, DisplayMetrics paramDisplayMetrics, BitmapPool paramBitmapPool, ArrayPool paramArrayPool)
  {
    this.parsers = paramList;
    this.displayMetrics = ((DisplayMetrics)Preconditions.checkNotNull(paramDisplayMetrics));
    this.bitmapPool = ((BitmapPool)Preconditions.checkNotNull(paramBitmapPool));
    this.byteArrayPool = ((ArrayPool)Preconditions.checkNotNull(paramArrayPool));
  }
  
  static void calculateScaling(DownsampleStrategy paramDownsampleStrategy, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BitmapFactory.Options paramOptions)
  {
    if ((paramInt2 <= 0) || (paramInt3 <= 0)) {}
    label189:
    label384:
    label426:
    label439:
    for (;;)
    {
      return;
      if ((paramInt1 == 90) || (paramInt1 == 270)) {}
      for (float f1 = paramDownsampleStrategy.getScaleFactor(paramInt3, paramInt2, paramInt4, paramInt5); f1 <= 0.0F; f1 = paramDownsampleStrategy.getScaleFactor(paramInt2, paramInt3, paramInt4, paramInt5)) {
        throw new IllegalArgumentException("Cannot scale with factor: " + f1 + " from: " + paramDownsampleStrategy);
      }
      paramDownsampleStrategy = paramDownsampleStrategy.getSampleSizeRounding(paramInt2, paramInt3, paramInt4, paramInt5);
      if (paramDownsampleStrategy == null) {
        throw new IllegalArgumentException("Cannot round with null rounding");
      }
      int i = (int)(paramInt2 * f1 + 0.5F);
      paramInt1 = (int)(paramInt3 * f1 + 0.5F);
      i = paramInt2 / i;
      paramInt1 = paramInt3 / paramInt1;
      float f2;
      if (paramDownsampleStrategy == DownsampleStrategy.SampleSizeRounding.MEMORY)
      {
        paramInt1 = Math.max(i, paramInt1);
        if ((Build.VERSION.SDK_INT > 23) || (!NO_DOWNSAMPLE_PRE_N_MIME_TYPES.contains(paramOptions.outMimeType))) {
          break label384;
        }
        paramInt1 = 1;
        f2 = paramInt1 * f1;
        paramOptions.inSampleSize = paramInt1;
        if (Build.VERSION.SDK_INT >= 19)
        {
          paramOptions.inTargetDensity = ((int)(1000.0F * f2 + 0.5F));
          paramOptions.inDensity = 1000;
        }
        if (!isScaling(paramOptions)) {
          break label426;
        }
        paramOptions.inScaled = true;
      }
      for (;;)
      {
        if (!Log.isLoggable("Downsampler", 2)) {
          break label439;
        }
        Log.v("Downsampler", "Calculate scaling, source: [" + paramInt2 + "x" + paramInt3 + "], target: [" + paramInt4 + "x" + paramInt5 + "], exact scale factor: " + f1 + ", power of 2 sample size: " + paramInt1 + ", adjusted scale factor: " + f2 + ", target density: " + paramOptions.inTargetDensity + ", density: " + paramOptions.inDensity);
        return;
        paramInt1 = Math.min(i, paramInt1);
        break;
        i = Math.max(1, Integer.highestOneBit(paramInt1));
        paramInt1 = i;
        if (paramDownsampleStrategy != DownsampleStrategy.SampleSizeRounding.MEMORY) {
          break label189;
        }
        paramInt1 = i;
        if (i >= 1.0F / f1) {
          break label189;
        }
        paramInt1 = i << 1;
        break label189;
        paramOptions.inTargetDensity = 0;
        paramOptions.inDensity = 0;
      }
    }
  }
  
  private Bitmap decodeFromWrappedStreams(InputStream paramInputStream, BitmapFactory.Options paramOptions, DownsampleStrategy paramDownsampleStrategy, DecodeFormat paramDecodeFormat, int paramInt1, int paramInt2, boolean paramBoolean, DecodeCallbacks paramDecodeCallbacks)
    throws IOException
  {
    Object localObject = getDimensions(paramInputStream, paramOptions, paramDecodeCallbacks, this.bitmapPool);
    int m = localObject[0];
    int n = localObject[1];
    localObject = paramOptions.outMimeType;
    int i2 = ImageHeaderParserUtils.getOrientation(this.parsers, paramInputStream, this.byteArrayPool);
    int k = TransformationUtils.getExifOrientationDegrees(i2);
    paramOptions.inPreferredConfig = getConfig(paramInputStream, paramDecodeFormat);
    if (paramOptions.inPreferredConfig != Bitmap.Config.ARGB_8888) {
      paramOptions.inDither = true;
    }
    int i;
    int j;
    if (paramInt1 == Integer.MIN_VALUE)
    {
      i = m;
      if (paramInt2 != Integer.MIN_VALUE) {
        break label288;
      }
      j = n;
      label102:
      calculateScaling(paramDownsampleStrategy, k, m, n, i, j, paramOptions);
      if (Build.VERSION.SDK_INT < 19) {
        break label295;
      }
    }
    label288:
    label295:
    for (k = 1;; k = 0)
    {
      if (((paramOptions.inSampleSize == 1) || (k != 0)) && (shouldUsePool(paramInputStream)))
      {
        if ((!paramBoolean) || (k == 0)) {
          break label301;
        }
        if ((i > 0) && (j > 0)) {
          setInBitmap(paramOptions, this.bitmapPool, i, j);
        }
      }
      paramDownsampleStrategy = decodeStream(paramInputStream, paramOptions, paramDecodeCallbacks, this.bitmapPool);
      paramDecodeCallbacks.onDecodeComplete(this.bitmapPool, paramDownsampleStrategy);
      if (Log.isLoggable("Downsampler", 2)) {
        logDecode(m, n, (String)localObject, paramOptions, paramDownsampleStrategy, paramInt1, paramInt2);
      }
      paramInputStream = null;
      if (paramDownsampleStrategy != null)
      {
        paramDownsampleStrategy.setDensity(this.displayMetrics.densityDpi);
        paramOptions = TransformationUtils.rotateImageExif(this.bitmapPool, paramDownsampleStrategy, i2);
        paramInputStream = paramOptions;
        if (!paramDownsampleStrategy.equals(paramOptions))
        {
          this.bitmapPool.put(paramDownsampleStrategy);
          paramInputStream = paramOptions;
        }
      }
      return paramInputStream;
      i = paramInt1;
      break;
      j = paramInt2;
      break label102;
    }
    label301:
    if (isScaling(paramOptions)) {}
    for (float f = paramOptions.inTargetDensity / paramOptions.inDensity;; f = 1.0F)
    {
      int i3 = paramOptions.inSampleSize;
      j = (int)Math.ceil(m / i3);
      i = (int)Math.ceil(n / i3);
      k = Math.round(j * f);
      int i1 = Math.round(i * f);
      j = i1;
      i = k;
      if (!Log.isLoggable("Downsampler", 2)) {
        break;
      }
      Log.v("Downsampler", "Calculated target [" + k + "x" + i1 + "] for source [" + m + "x" + n + "], sampleSize: " + i3 + ", targetDensity: " + paramOptions.inTargetDensity + ", density: " + paramOptions.inDensity + ", density multiplier: " + f);
      j = i1;
      i = k;
      break;
    }
  }
  
  private static Bitmap decodeStream(InputStream paramInputStream, BitmapFactory.Options paramOptions, DecodeCallbacks paramDecodeCallbacks, BitmapPool paramBitmapPool)
    throws IOException
  {
    if (paramOptions.inJustDecodeBounds) {
      paramInputStream.mark(5242880);
    }
    int i;
    int j;
    Object localObject;
    for (;;)
    {
      i = paramOptions.outWidth;
      j = paramOptions.outHeight;
      localObject = paramOptions.outMimeType;
      TransformationUtils.getBitmapDrawableLock().lock();
      try
      {
        Bitmap localBitmap1 = BitmapFactory.decodeStream(paramInputStream, null, paramOptions);
        TransformationUtils.getBitmapDrawableLock().unlock();
        if (paramOptions.inJustDecodeBounds) {
          paramInputStream.reset();
        }
        return localBitmap1;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localObject = newIoExceptionForInBitmapAssertion(localIllegalArgumentException, i, j, (String)localObject, paramOptions);
        if (!Log.isLoggable("Downsampler", 3)) {
          break;
        }
        Log.d("Downsampler", "Failed to decode with inBitmap, trying again without Bitmap re-use", (Throwable)localObject);
        Bitmap localBitmap2 = paramOptions.inBitmap;
        if (localBitmap2 == null) {
          break label177;
        }
        try
        {
          paramInputStream.reset();
          paramBitmapPool.put(paramOptions.inBitmap);
          paramOptions.inBitmap = null;
          paramInputStream = decodeStream(paramInputStream, paramOptions, paramDecodeCallbacks, paramBitmapPool);
          return paramInputStream;
        }
        catch (IOException paramInputStream)
        {
          throw ((Throwable)localObject);
        }
      }
      finally
      {
        TransformationUtils.getBitmapDrawableLock().unlock();
      }
      paramDecodeCallbacks.onObtainBounds();
    }
    label177:
    throw ((Throwable)localObject);
  }
  
  @TargetApi(19)
  @Nullable
  private static String getBitmapString(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      return null;
    }
    if (Build.VERSION.SDK_INT >= 19) {}
    for (String str = " (" + paramBitmap.getAllocationByteCount() + ")";; str = "") {
      return "[" + paramBitmap.getWidth() + "x" + paramBitmap.getHeight() + "] " + paramBitmap.getConfig() + str;
    }
  }
  
  private Bitmap.Config getConfig(InputStream paramInputStream, DecodeFormat paramDecodeFormat)
    throws IOException
  {
    if ((paramDecodeFormat == DecodeFormat.PREFER_ARGB_8888) || (Build.VERSION.SDK_INT == 16)) {
      return Bitmap.Config.ARGB_8888;
    }
    boolean bool2 = false;
    try
    {
      bool1 = ImageHeaderParserUtils.getType(this.parsers, paramInputStream, this.byteArrayPool).hasAlpha();
      if (bool1) {
        return Bitmap.Config.ARGB_8888;
      }
    }
    catch (IOException paramInputStream)
    {
      for (;;)
      {
        boolean bool1 = bool2;
        if (Log.isLoggable("Downsampler", 3))
        {
          Log.d("Downsampler", "Cannot determine whether the image has alpha or not from header, format " + paramDecodeFormat, paramInputStream);
          bool1 = bool2;
        }
      }
    }
    return Bitmap.Config.RGB_565;
  }
  
  /* Error */
  private static BitmapFactory.Options getDefaultOptions()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 140	com/bumptech/glide/load/resource/bitmap/Downsampler:OPTIONS_QUEUE	Ljava/util/Queue;
    //   6: astore_0
    //   7: aload_0
    //   8: monitorenter
    //   9: getstatic 140	com/bumptech/glide/load/resource/bitmap/Downsampler:OPTIONS_QUEUE	Ljava/util/Queue;
    //   12: invokeinterface 490 1 0
    //   17: checkcast 223	android/graphics/BitmapFactory$Options
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: astore_0
    //   25: aload_1
    //   26: ifnonnull +15 -> 41
    //   29: new 223	android/graphics/BitmapFactory$Options
    //   32: dup
    //   33: invokespecial 491	android/graphics/BitmapFactory$Options:<init>	()V
    //   36: astore_0
    //   37: aload_0
    //   38: invokestatic 495	com/bumptech/glide/load/resource/bitmap/Downsampler:resetOptions	(Landroid/graphics/BitmapFactory$Options;)V
    //   41: ldc 2
    //   43: monitorexit
    //   44: aload_0
    //   45: areturn
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    //   51: astore_0
    //   52: ldc 2
    //   54: monitorexit
    //   55: aload_0
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   51	5	0	localObject2	Object
    //   20	6	1	localOptions	BitmapFactory.Options
    //   46	4	1	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   9	23	46	finally
    //   47	49	46	finally
    //   3	9	51	finally
    //   29	41	51	finally
    //   49	51	51	finally
  }
  
  private static int[] getDimensions(InputStream paramInputStream, BitmapFactory.Options paramOptions, DecodeCallbacks paramDecodeCallbacks, BitmapPool paramBitmapPool)
    throws IOException
  {
    paramOptions.inJustDecodeBounds = true;
    decodeStream(paramInputStream, paramOptions, paramDecodeCallbacks, paramBitmapPool);
    paramOptions.inJustDecodeBounds = false;
    return new int[] { paramOptions.outWidth, paramOptions.outHeight };
  }
  
  private static String getInBitmapString(BitmapFactory.Options paramOptions)
  {
    return getBitmapString(paramOptions.inBitmap);
  }
  
  private static boolean isScaling(BitmapFactory.Options paramOptions)
  {
    return (paramOptions.inTargetDensity > 0) && (paramOptions.inDensity > 0) && (paramOptions.inTargetDensity != paramOptions.inDensity);
  }
  
  private static void logDecode(int paramInt1, int paramInt2, String paramString, BitmapFactory.Options paramOptions, Bitmap paramBitmap, int paramInt3, int paramInt4)
  {
    Log.v("Downsampler", "Decoded " + getBitmapString(paramBitmap) + " from [" + paramInt1 + "x" + paramInt2 + "] " + paramString + " with inBitmap " + getInBitmapString(paramOptions) + " for [" + paramInt3 + "x" + paramInt4 + "], sample size: " + paramOptions.inSampleSize + ", density: " + paramOptions.inDensity + ", target density: " + paramOptions.inTargetDensity + ", thread: " + Thread.currentThread().getName());
  }
  
  private static IOException newIoExceptionForInBitmapAssertion(IllegalArgumentException paramIllegalArgumentException, int paramInt1, int paramInt2, String paramString, BitmapFactory.Options paramOptions)
  {
    return new IOException("Exception decoding bitmap, outWidth: " + paramInt1 + ", outHeight: " + paramInt2 + ", outMimeType: " + paramString + ", inBitmap: " + getInBitmapString(paramOptions), paramIllegalArgumentException);
  }
  
  private static void releaseOptions(BitmapFactory.Options paramOptions)
  {
    resetOptions(paramOptions);
    synchronized (OPTIONS_QUEUE)
    {
      OPTIONS_QUEUE.offer(paramOptions);
      return;
    }
  }
  
  private static void resetOptions(BitmapFactory.Options paramOptions)
  {
    paramOptions.inTempStorage = null;
    paramOptions.inDither = false;
    paramOptions.inScaled = false;
    paramOptions.inSampleSize = 1;
    paramOptions.inPreferredConfig = null;
    paramOptions.inJustDecodeBounds = false;
    paramOptions.inDensity = 0;
    paramOptions.inTargetDensity = 0;
    paramOptions.outWidth = 0;
    paramOptions.outHeight = 0;
    paramOptions.outMimeType = null;
    paramOptions.inBitmap = null;
    paramOptions.inMutable = true;
  }
  
  private static void setInBitmap(BitmapFactory.Options paramOptions, BitmapPool paramBitmapPool, int paramInt1, int paramInt2)
  {
    paramOptions.inBitmap = paramBitmapPool.getDirty(paramInt1, paramInt2, paramOptions.inPreferredConfig);
  }
  
  private boolean shouldUsePool(InputStream paramInputStream)
    throws IOException
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return true;
    }
    try
    {
      paramInputStream = ImageHeaderParserUtils.getType(this.parsers, paramInputStream, this.byteArrayPool);
      boolean bool = TYPES_THAT_USE_POOL_PRE_KITKAT.contains(paramInputStream);
      return bool;
    }
    catch (IOException paramInputStream)
    {
      if (Log.isLoggable("Downsampler", 3)) {
        Log.d("Downsampler", "Cannot determine the image type from header", paramInputStream);
      }
    }
    return false;
  }
  
  public Resource<Bitmap> decode(InputStream paramInputStream, int paramInt1, int paramInt2, Options paramOptions)
    throws IOException
  {
    return decode(paramInputStream, paramInt1, paramInt2, paramOptions, EMPTY_CALLBACKS);
  }
  
  public Resource<Bitmap> decode(InputStream paramInputStream, int paramInt1, int paramInt2, Options paramOptions, DecodeCallbacks paramDecodeCallbacks)
    throws IOException
  {
    Preconditions.checkArgument(paramInputStream.markSupported(), "You must provide an InputStream that supports mark()");
    byte[] arrayOfByte = (byte[])this.byteArrayPool.get(65536, byte[].class);
    BitmapFactory.Options localOptions = getDefaultOptions();
    localOptions.inTempStorage = arrayOfByte;
    DecodeFormat localDecodeFormat = (DecodeFormat)paramOptions.get(DECODE_FORMAT);
    DownsampleStrategy localDownsampleStrategy = (DownsampleStrategy)paramOptions.get(DOWNSAMPLE_STRATEGY);
    boolean bool = ((Boolean)paramOptions.get(FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS)).booleanValue();
    try
    {
      paramInputStream = BitmapResource.obtain(decodeFromWrappedStreams(paramInputStream, localOptions, localDownsampleStrategy, localDecodeFormat, paramInt1, paramInt2, bool, paramDecodeCallbacks), this.bitmapPool);
      return paramInputStream;
    }
    finally
    {
      releaseOptions(localOptions);
      this.byteArrayPool.put(arrayOfByte, byte[].class);
    }
  }
  
  public boolean handles(InputStream paramInputStream)
  {
    return true;
  }
  
  public boolean handles(ByteBuffer paramByteBuffer)
  {
    return true;
  }
  
  public static abstract interface DecodeCallbacks
  {
    public abstract void onDecodeComplete(BitmapPool paramBitmapPool, Bitmap paramBitmap)
      throws IOException;
    
    public abstract void onObtainBounds();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\Downsampler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */