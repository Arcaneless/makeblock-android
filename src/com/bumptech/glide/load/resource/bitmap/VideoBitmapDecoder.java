package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Option.CacheKeyUpdater;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class VideoBitmapDecoder
  implements ResourceDecoder<ParcelFileDescriptor, Bitmap>
{
  private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY = new MediaMetadataRetrieverFactory();
  public static final long DEFAULT_FRAME = -1L;
  public static final Option<Integer> FRAME_OPTION;
  public static final Option<Long> TARGET_FRAME = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", Long.valueOf(-1L), new Option.CacheKeyUpdater()
  {
    private final ByteBuffer buffer = ByteBuffer.allocate(8);
    
    public void update(byte[] arg1, Long paramAnonymousLong, MessageDigest paramAnonymousMessageDigest)
    {
      paramAnonymousMessageDigest.update(???);
      synchronized (this.buffer)
      {
        this.buffer.position(0);
        paramAnonymousMessageDigest.update(this.buffer.putLong(paramAnonymousLong.longValue()).array());
        return;
      }
    }
  });
  private final BitmapPool bitmapPool;
  private final MediaMetadataRetrieverFactory factory;
  
  static
  {
    FRAME_OPTION = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", null, new Option.CacheKeyUpdater()
    {
      private final ByteBuffer buffer = ByteBuffer.allocate(4);
      
      public void update(byte[] arg1, Integer paramAnonymousInteger, MessageDigest paramAnonymousMessageDigest)
      {
        if (paramAnonymousInteger == null) {
          return;
        }
        paramAnonymousMessageDigest.update(???);
        synchronized (this.buffer)
        {
          this.buffer.position(0);
          paramAnonymousMessageDigest.update(this.buffer.putInt(paramAnonymousInteger.intValue()).array());
          return;
        }
      }
    });
  }
  
  public VideoBitmapDecoder(Context paramContext)
  {
    this(Glide.get(paramContext).getBitmapPool());
  }
  
  public VideoBitmapDecoder(BitmapPool paramBitmapPool)
  {
    this(paramBitmapPool, DEFAULT_FACTORY);
  }
  
  VideoBitmapDecoder(BitmapPool paramBitmapPool, MediaMetadataRetrieverFactory paramMediaMetadataRetrieverFactory)
  {
    this.bitmapPool = paramBitmapPool;
    this.factory = paramMediaMetadataRetrieverFactory;
  }
  
  public Resource<Bitmap> decode(ParcelFileDescriptor paramParcelFileDescriptor, int paramInt1, int paramInt2, Options paramOptions)
    throws IOException
  {
    long l = ((Long)paramOptions.get(TARGET_FRAME)).longValue();
    if ((l < 0L) && (l != -1L)) {
      throw new IllegalArgumentException("Requested frame must be non-negative, or DEFAULT_FRAME, given: " + l);
    }
    paramOptions = (Integer)paramOptions.get(FRAME_OPTION);
    MediaMetadataRetriever localMediaMetadataRetriever = this.factory.build();
    try
    {
      localMediaMetadataRetriever.setDataSource(paramParcelFileDescriptor.getFileDescriptor());
    }
    finally
    {
      localMediaMetadataRetriever.release();
    }
    paramOptions = localMediaMetadataRetriever.getFrameAtTime();
    label174:
    for (;;)
    {
      localMediaMetadataRetriever.release();
      paramParcelFileDescriptor.close();
      return BitmapResource.obtain(paramOptions, this.bitmapPool);
      do
      {
        if (paramOptions == null)
        {
          paramOptions = localMediaMetadataRetriever.getFrameAtTime(l);
          break label174;
        }
        paramOptions = localMediaMetadataRetriever.getFrameAtTime(l, paramOptions.intValue());
        break;
      } while (l != -1L);
      break;
    }
  }
  
  public boolean handles(ParcelFileDescriptor paramParcelFileDescriptor, Options paramOptions)
  {
    paramOptions = this.factory.build();
    try
    {
      paramOptions.setDataSource(paramParcelFileDescriptor.getFileDescriptor());
      paramOptions.release();
      return true;
    }
    catch (RuntimeException paramParcelFileDescriptor)
    {
      paramParcelFileDescriptor = paramParcelFileDescriptor;
      paramOptions.release();
      return false;
    }
    finally
    {
      paramParcelFileDescriptor = finally;
      paramOptions.release();
      throw paramParcelFileDescriptor;
    }
  }
  
  static class MediaMetadataRetrieverFactory
  {
    public MediaMetadataRetriever build()
    {
      return new MediaMetadataRetriever();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\VideoBitmapDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */