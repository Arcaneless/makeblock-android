package com.bumptech.glide.load;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

public final class ImageHeaderParserUtils
{
  private static final int MARK_POSITION = 5242880;
  
  public static int getOrientation(List<ImageHeaderParser> paramList, @Nullable InputStream paramInputStream, ArrayPool paramArrayPool)
    throws IOException
  {
    if (paramInputStream == null) {
      return -1;
    }
    Object localObject = paramInputStream;
    if (!paramInputStream.markSupported()) {
      localObject = new RecyclableBufferedInputStream(paramInputStream, paramArrayPool);
    }
    ((InputStream)localObject).mark(5242880);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      paramInputStream = (ImageHeaderParser)paramList.next();
      try
      {
        int i = paramInputStream.getOrientation((InputStream)localObject, paramArrayPool);
        if (i != -1) {
          return i;
        }
        ((InputStream)localObject).reset();
      }
      finally
      {
        ((InputStream)localObject).reset();
      }
    }
    return -1;
  }
  
  public static ImageHeaderParser.ImageType getType(List<ImageHeaderParser> paramList, @Nullable InputStream paramInputStream, ArrayPool paramArrayPool)
    throws IOException
  {
    if (paramInputStream == null) {
      return ImageHeaderParser.ImageType.UNKNOWN;
    }
    Object localObject = paramInputStream;
    if (!paramInputStream.markSupported()) {
      localObject = new RecyclableBufferedInputStream(paramInputStream, paramArrayPool);
    }
    ((InputStream)localObject).mark(5242880);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      paramInputStream = (ImageHeaderParser)paramList.next();
      try
      {
        paramInputStream = paramInputStream.getType((InputStream)localObject);
        paramArrayPool = ImageHeaderParser.ImageType.UNKNOWN;
        if (paramInputStream != paramArrayPool) {
          return paramInputStream;
        }
        ((InputStream)localObject).reset();
      }
      finally
      {
        ((InputStream)localObject).reset();
      }
    }
    return ImageHeaderParser.ImageType.UNKNOWN;
  }
  
  public static ImageHeaderParser.ImageType getType(List<ImageHeaderParser> paramList, @Nullable ByteBuffer paramByteBuffer)
    throws IOException
  {
    if (paramByteBuffer == null) {
      return ImageHeaderParser.ImageType.UNKNOWN;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      ImageHeaderParser.ImageType localImageType = ((ImageHeaderParser)paramList.next()).getType(paramByteBuffer);
      if (localImageType != ImageHeaderParser.ImageType.UNKNOWN) {
        return localImageType;
      }
    }
    return ImageHeaderParser.ImageType.UNKNOWN;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\ImageHeaderParserUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */