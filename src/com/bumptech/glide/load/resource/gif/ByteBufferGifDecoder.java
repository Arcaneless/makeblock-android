package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.gifdecoder.StandardGifDecoder;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;

public class ByteBufferGifDecoder
  implements ResourceDecoder<ByteBuffer, GifDrawable>
{
  public static final Option<Boolean> DISABLE_ANIMATION = Option.memory("com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder.DisableAnimation", Boolean.valueOf(false));
  private static final GifDecoderFactory GIF_DECODER_FACTORY = new GifDecoderFactory();
  private static final GifHeaderParserPool PARSER_POOL = new GifHeaderParserPool();
  private static final String TAG = "BufferGifDecoder";
  private final BitmapPool bitmapPool;
  private final Context context;
  private final GifDecoderFactory gifDecoderFactory;
  private final GifHeaderParserPool parserPool;
  private final List<ImageHeaderParser> parsers;
  private final GifBitmapProvider provider;
  
  public ByteBufferGifDecoder(Context paramContext)
  {
    this(paramContext, Glide.get(paramContext).getRegistry().getImageHeaderParsers(), Glide.get(paramContext).getBitmapPool(), Glide.get(paramContext).getArrayPool());
  }
  
  public ByteBufferGifDecoder(Context paramContext, List<ImageHeaderParser> paramList, BitmapPool paramBitmapPool, ArrayPool paramArrayPool)
  {
    this(paramContext, paramList, paramBitmapPool, paramArrayPool, PARSER_POOL, GIF_DECODER_FACTORY);
  }
  
  ByteBufferGifDecoder(Context paramContext, List<ImageHeaderParser> paramList, BitmapPool paramBitmapPool, ArrayPool paramArrayPool, GifHeaderParserPool paramGifHeaderParserPool, GifDecoderFactory paramGifDecoderFactory)
  {
    this.context = paramContext.getApplicationContext();
    this.parsers = paramList;
    this.bitmapPool = paramBitmapPool;
    this.gifDecoderFactory = paramGifDecoderFactory;
    this.provider = new GifBitmapProvider(paramBitmapPool, paramArrayPool);
    this.parserPool = paramGifHeaderParserPool;
  }
  
  private GifDrawableResource decode(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, GifHeaderParser paramGifHeaderParser)
  {
    long l = LogTime.getLogTime();
    paramGifHeaderParser = paramGifHeaderParser.parseHeader();
    if ((paramGifHeaderParser.getNumFrames() <= 0) || (paramGifHeaderParser.getStatus() != 0)) {
      return null;
    }
    int i = getSampleSize(paramGifHeaderParser, paramInt1, paramInt2);
    paramByteBuffer = this.gifDecoderFactory.build(this.provider, paramGifHeaderParser, paramByteBuffer, i);
    paramByteBuffer.advance();
    paramGifHeaderParser = paramByteBuffer.getNextFrame();
    if (paramGifHeaderParser == null) {
      return null;
    }
    UnitTransformation localUnitTransformation = UnitTransformation.get();
    paramByteBuffer = new GifDrawable(this.context, paramByteBuffer, this.bitmapPool, localUnitTransformation, paramInt1, paramInt2, paramGifHeaderParser);
    if (Log.isLoggable("BufferGifDecoder", 2)) {
      Log.v("BufferGifDecoder", "Decoded GIF from stream in " + LogTime.getElapsedMillis(l));
    }
    return new GifDrawableResource(paramByteBuffer);
  }
  
  private static int getSampleSize(GifHeader paramGifHeader, int paramInt1, int paramInt2)
  {
    int i = Math.min(paramGifHeader.getHeight() / paramInt2, paramGifHeader.getWidth() / paramInt1);
    if (i == 0) {}
    for (i = 0;; i = Integer.highestOneBit(i))
    {
      i = Math.max(1, i);
      if (Log.isLoggable("BufferGifDecoder", 2)) {
        Log.v("BufferGifDecoder", "Downsampling GIF, sampleSize: " + i + ", target dimens: [" + paramInt1 + "x" + paramInt2 + "], actual dimens: [" + paramGifHeader.getWidth() + "x" + paramGifHeader.getHeight() + "]");
      }
      return i;
    }
  }
  
  public GifDrawableResource decode(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, Options paramOptions)
  {
    paramOptions = this.parserPool.obtain(paramByteBuffer);
    try
    {
      paramByteBuffer = decode(paramByteBuffer, paramInt1, paramInt2, paramOptions);
      return paramByteBuffer;
    }
    finally
    {
      this.parserPool.release(paramOptions);
    }
  }
  
  public boolean handles(ByteBuffer paramByteBuffer, Options paramOptions)
    throws IOException
  {
    return (!((Boolean)paramOptions.get(DISABLE_ANIMATION)).booleanValue()) && (ImageHeaderParserUtils.getType(this.parsers, paramByteBuffer) == ImageHeaderParser.ImageType.GIF);
  }
  
  static class GifDecoderFactory
  {
    public GifDecoder build(GifDecoder.BitmapProvider paramBitmapProvider, GifHeader paramGifHeader, ByteBuffer paramByteBuffer, int paramInt)
    {
      return new StandardGifDecoder(paramBitmapProvider, paramGifHeader, paramByteBuffer, paramInt);
    }
  }
  
  static class GifHeaderParserPool
  {
    private final Queue<GifHeaderParser> pool = Util.createQueue(0);
    
    public GifHeaderParser obtain(ByteBuffer paramByteBuffer)
    {
      for (;;)
      {
        GifHeaderParser localGifHeaderParser2;
        try
        {
          localGifHeaderParser2 = (GifHeaderParser)this.pool.poll();
        }
        finally {}
        GifHeaderParser localGifHeaderParser1 = new GifHeaderParser();
        paramByteBuffer = localGifHeaderParser1.setData(paramByteBuffer);
        return paramByteBuffer;
        localGifHeaderParser1 = localGifHeaderParser2;
        if (localGifHeaderParser2 != null) {}
      }
    }
    
    public void release(GifHeaderParser paramGifHeaderParser)
    {
      try
      {
        paramGifHeaderParser.clear();
        this.pool.offer(paramGifHeaderParser);
        return;
      }
      finally
      {
        paramGifHeaderParser = finally;
        throw paramGifHeaderParser;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\gif\ByteBufferGifDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */