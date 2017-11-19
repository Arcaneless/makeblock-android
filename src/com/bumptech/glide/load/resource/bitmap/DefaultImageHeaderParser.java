package com.bumptech.glide.load.resource.bitmap;

import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public final class DefaultImageHeaderParser
  implements ImageHeaderParser
{
  private static final int[] BYTES_PER_FORMAT = { 0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8 };
  static final int EXIF_MAGIC_NUMBER = 65496;
  static final int EXIF_SEGMENT_TYPE = 225;
  private static final int GIF_HEADER = 4671814;
  private static final int INTEL_TIFF_MAGIC_NUMBER = 18761;
  static final String JPEG_EXIF_SEGMENT_PREAMBLE = "Exif\000\000";
  static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = "Exif\000\000".getBytes(Charset.forName("UTF-8"));
  private static final int MARKER_EOI = 217;
  private static final int MOTOROLA_TIFF_MAGIC_NUMBER = 19789;
  private static final int ORIENTATION_TAG_TYPE = 274;
  private static final int PNG_HEADER = -1991225785;
  private static final int RIFF_HEADER = 1380533830;
  private static final int SEGMENT_SOS = 218;
  static final int SEGMENT_START_ID = 255;
  private static final String TAG = "DfltImageHeaderParser";
  private static final int VP8_HEADER = 1448097792;
  private static final int VP8_HEADER_MASK = -256;
  private static final int VP8_HEADER_TYPE_EXTENDED = 88;
  private static final int VP8_HEADER_TYPE_LOSSLESS = 76;
  private static final int VP8_HEADER_TYPE_MASK = 255;
  private static final int WEBP_EXTENDED_ALPHA_FLAG = 16;
  private static final int WEBP_HEADER = 1464156752;
  private static final int WEBP_LOSSLESS_ALPHA_FLAG = 8;
  
  private static int calcTagOffset(int paramInt1, int paramInt2)
  {
    return paramInt1 + 2 + paramInt2 * 12;
  }
  
  private int getOrientation(Reader paramReader, ArrayPool paramArrayPool)
    throws IOException
  {
    int i = paramReader.getUInt16();
    if (!handles(i)) {
      if (Log.isLoggable("DfltImageHeaderParser", 3)) {
        Log.d("DfltImageHeaderParser", "Parser doesn't handle magic number: " + i);
      }
    }
    do
    {
      return -1;
      i = moveToExifSegmentAndGetLength(paramReader);
      if (i != -1) {
        break;
      }
    } while (!Log.isLoggable("DfltImageHeaderParser", 3));
    Log.d("DfltImageHeaderParser", "Failed to parse exif segment length, or exif segment not found");
    return -1;
    byte[] arrayOfByte = (byte[])paramArrayPool.get(i, byte[].class);
    try
    {
      i = parseExifSegment(paramReader, arrayOfByte, i);
      return i;
    }
    finally
    {
      paramArrayPool.put(arrayOfByte, byte[].class);
    }
  }
  
  private ImageHeaderParser.ImageType getType(Reader paramReader)
    throws IOException
  {
    int i = paramReader.getUInt16();
    if (i == 65496) {
      return ImageHeaderParser.ImageType.JPEG;
    }
    i = i << 16 & 0xFFFF0000 | paramReader.getUInt16() & 0xFFFF;
    if (i == -1991225785)
    {
      paramReader.skip(21L);
      if (paramReader.getByte() >= 3) {
        return ImageHeaderParser.ImageType.PNG_A;
      }
      return ImageHeaderParser.ImageType.PNG;
    }
    if (i >> 8 == 4671814) {
      return ImageHeaderParser.ImageType.GIF;
    }
    if (i != 1380533830) {
      return ImageHeaderParser.ImageType.UNKNOWN;
    }
    paramReader.skip(4L);
    if ((paramReader.getUInt16() << 16 & 0xFFFF0000 | paramReader.getUInt16() & 0xFFFF) != 1464156752) {
      return ImageHeaderParser.ImageType.UNKNOWN;
    }
    i = paramReader.getUInt16() << 16 & 0xFFFF0000 | paramReader.getUInt16() & 0xFFFF;
    if ((i & 0xFF00) != 1448097792) {
      return ImageHeaderParser.ImageType.UNKNOWN;
    }
    if ((i & 0xFF) == 88)
    {
      paramReader.skip(4L);
      if ((paramReader.getByte() & 0x10) != 0) {
        return ImageHeaderParser.ImageType.WEBP_A;
      }
      return ImageHeaderParser.ImageType.WEBP;
    }
    if ((i & 0xFF) == 76)
    {
      paramReader.skip(4L);
      if ((paramReader.getByte() & 0x8) != 0) {
        return ImageHeaderParser.ImageType.WEBP_A;
      }
      return ImageHeaderParser.ImageType.WEBP;
    }
    return ImageHeaderParser.ImageType.WEBP;
  }
  
  private static boolean handles(int paramInt)
  {
    return ((paramInt & 0xFFD8) == 65496) || (paramInt == 19789) || (paramInt == 18761);
  }
  
  private boolean hasJpegExifPreamble(byte[] paramArrayOfByte, int paramInt)
  {
    boolean bool1;
    boolean bool2;
    if ((paramArrayOfByte != null) && (paramInt > JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length))
    {
      bool1 = true;
      bool2 = bool1;
      if (bool1) {
        paramInt = 0;
      }
    }
    for (;;)
    {
      bool2 = bool1;
      if (paramInt < JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length)
      {
        if (paramArrayOfByte[paramInt] != JPEG_EXIF_SEGMENT_PREAMBLE_BYTES[paramInt]) {
          bool2 = false;
        }
      }
      else
      {
        return bool2;
        bool1 = false;
        break;
      }
      paramInt += 1;
    }
  }
  
  private int moveToExifSegmentAndGetLength(Reader paramReader)
    throws IOException
  {
    int k;
    int j;
    long l;
    do
    {
      int i = paramReader.getUInt8();
      if (i != 255)
      {
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
          Log.d("DfltImageHeaderParser", "Unknown segmentId=" + i);
        }
        i = -1;
      }
      do
      {
        return i;
        k = paramReader.getUInt8();
        if (k == 218) {
          return -1;
        }
        if (k == 217)
        {
          if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Found MARKER_EOI in exif segment");
          }
          return -1;
        }
        j = paramReader.getUInt16() - 2;
        i = j;
      } while (k == 225);
      l = paramReader.skip(j);
    } while (l == j);
    if (Log.isLoggable("DfltImageHeaderParser", 3)) {
      Log.d("DfltImageHeaderParser", "Unable to skip enough data, type: " + k + ", wanted to skip: " + j + ", but actually skipped: " + l);
    }
    return -1;
  }
  
  private static int parseExifSegment(RandomAccessReader paramRandomAccessReader)
  {
    int i = "Exif\000\000".length();
    int j = paramRandomAccessReader.getInt16(i);
    ByteOrder localByteOrder;
    label48:
    int n;
    int m;
    if (j == 19789)
    {
      localByteOrder = ByteOrder.BIG_ENDIAN;
      paramRandomAccessReader.order(localByteOrder);
      j = paramRandomAccessReader.getInt32(i + 4) + i;
      int k = paramRandomAccessReader.getInt16(j);
      i = 0;
      if (i >= k) {
        break label488;
      }
      n = calcTagOffset(j, i);
      m = paramRandomAccessReader.getInt16(n);
      if (m == 274) {
        break label140;
      }
    }
    for (;;)
    {
      i += 1;
      break label48;
      if (j == 18761)
      {
        localByteOrder = ByteOrder.LITTLE_ENDIAN;
        break;
      }
      if (Log.isLoggable("DfltImageHeaderParser", 3)) {
        Log.d("DfltImageHeaderParser", "Unknown endianness = " + j);
      }
      localByteOrder = ByteOrder.BIG_ENDIAN;
      break;
      label140:
      int i1 = paramRandomAccessReader.getInt16(n + 2);
      if ((i1 < 1) || (i1 > 12))
      {
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
          Log.d("DfltImageHeaderParser", "Got invalid format code = " + i1);
        }
      }
      else
      {
        int i2 = paramRandomAccessReader.getInt32(n + 4);
        if (i2 < 0)
        {
          if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Negative tiff component count");
          }
        }
        else
        {
          if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Got tagIndex=" + i + " tagType=" + m + " formatCode=" + i1 + " componentCount=" + i2);
          }
          i2 += BYTES_PER_FORMAT[i1];
          if (i2 > 4)
          {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
              Log.d("DfltImageHeaderParser", "Got byte count > 4, not orientation, continuing, formatCode=" + i1);
            }
          }
          else
          {
            n += 8;
            if ((n < 0) || (n > paramRandomAccessReader.length()))
            {
              if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Illegal tagValueOffset=" + n + " tagType=" + m);
              }
            }
            else
            {
              if ((i2 >= 0) && (n + i2 <= paramRandomAccessReader.length())) {
                break label481;
              }
              if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Illegal number of bytes for TI tag data tagType=" + m);
              }
            }
          }
        }
      }
    }
    label481:
    return paramRandomAccessReader.getInt16(n);
    label488:
    return -1;
  }
  
  private int parseExifSegment(Reader paramReader, byte[] paramArrayOfByte, int paramInt)
    throws IOException
  {
    int i = paramReader.read(paramArrayOfByte, paramInt);
    if (i != paramInt) {
      if (Log.isLoggable("DfltImageHeaderParser", 3)) {
        Log.d("DfltImageHeaderParser", "Unable to read exif segment data, length: " + paramInt + ", actually read: " + i);
      }
    }
    do
    {
      return -1;
      if (hasJpegExifPreamble(paramArrayOfByte, paramInt)) {
        return parseExifSegment(new RandomAccessReader(paramArrayOfByte, paramInt));
      }
    } while (!Log.isLoggable("DfltImageHeaderParser", 3));
    Log.d("DfltImageHeaderParser", "Missing jpeg exif preamble");
    return -1;
  }
  
  public int getOrientation(InputStream paramInputStream, ArrayPool paramArrayPool)
    throws IOException
  {
    return getOrientation(new StreamReader((InputStream)Preconditions.checkNotNull(paramInputStream)), (ArrayPool)Preconditions.checkNotNull(paramArrayPool));
  }
  
  public int getOrientation(ByteBuffer paramByteBuffer, ArrayPool paramArrayPool)
    throws IOException
  {
    return getOrientation(new ByteBufferReader((ByteBuffer)Preconditions.checkNotNull(paramByteBuffer)), (ArrayPool)Preconditions.checkNotNull(paramArrayPool));
  }
  
  public ImageHeaderParser.ImageType getType(InputStream paramInputStream)
    throws IOException
  {
    return getType(new StreamReader((InputStream)Preconditions.checkNotNull(paramInputStream)));
  }
  
  public ImageHeaderParser.ImageType getType(ByteBuffer paramByteBuffer)
    throws IOException
  {
    return getType(new ByteBufferReader((ByteBuffer)Preconditions.checkNotNull(paramByteBuffer)));
  }
  
  private static final class ByteBufferReader
    implements DefaultImageHeaderParser.Reader
  {
    private final ByteBuffer byteBuffer;
    
    ByteBufferReader(ByteBuffer paramByteBuffer)
    {
      this.byteBuffer = paramByteBuffer;
      paramByteBuffer.order(ByteOrder.BIG_ENDIAN);
    }
    
    public int getByte()
      throws IOException
    {
      if (this.byteBuffer.remaining() < 1) {
        return -1;
      }
      return this.byteBuffer.get();
    }
    
    public int getUInt16()
      throws IOException
    {
      return getByte() << 8 & 0xFF00 | getByte() & 0xFF;
    }
    
    public short getUInt8()
      throws IOException
    {
      return (short)(getByte() & 0xFF);
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt)
      throws IOException
    {
      paramInt = Math.min(paramInt, this.byteBuffer.remaining());
      if (paramInt == 0) {
        return -1;
      }
      this.byteBuffer.get(paramArrayOfByte, 0, paramInt);
      return paramInt;
    }
    
    public long skip(long paramLong)
      throws IOException
    {
      int i = (int)Math.min(this.byteBuffer.remaining(), paramLong);
      this.byteBuffer.position(this.byteBuffer.position() + i);
      return i;
    }
  }
  
  private static final class RandomAccessReader
  {
    private final ByteBuffer data;
    
    RandomAccessReader(byte[] paramArrayOfByte, int paramInt)
    {
      this.data = ((ByteBuffer)ByteBuffer.wrap(paramArrayOfByte).order(ByteOrder.BIG_ENDIAN).limit(paramInt));
    }
    
    private boolean isAvailable(int paramInt1, int paramInt2)
    {
      return this.data.remaining() - paramInt1 >= paramInt2;
    }
    
    short getInt16(int paramInt)
    {
      if (isAvailable(paramInt, 2)) {
        return this.data.getShort(paramInt);
      }
      return -1;
    }
    
    int getInt32(int paramInt)
    {
      if (isAvailable(paramInt, 4)) {
        return this.data.getInt(paramInt);
      }
      return -1;
    }
    
    int length()
    {
      return this.data.remaining();
    }
    
    void order(ByteOrder paramByteOrder)
    {
      this.data.order(paramByteOrder);
    }
  }
  
  private static abstract interface Reader
  {
    public abstract int getByte()
      throws IOException;
    
    public abstract int getUInt16()
      throws IOException;
    
    public abstract short getUInt8()
      throws IOException;
    
    public abstract int read(byte[] paramArrayOfByte, int paramInt)
      throws IOException;
    
    public abstract long skip(long paramLong)
      throws IOException;
  }
  
  private static final class StreamReader
    implements DefaultImageHeaderParser.Reader
  {
    private final InputStream is;
    
    StreamReader(InputStream paramInputStream)
    {
      this.is = paramInputStream;
    }
    
    public int getByte()
      throws IOException
    {
      return this.is.read();
    }
    
    public int getUInt16()
      throws IOException
    {
      return this.is.read() << 8 & 0xFF00 | this.is.read() & 0xFF;
    }
    
    public short getUInt8()
      throws IOException
    {
      return (short)(this.is.read() & 0xFF);
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt)
      throws IOException
    {
      int i = paramInt;
      while (i > 0)
      {
        int j = this.is.read(paramArrayOfByte, paramInt - i, i);
        if (j == -1) {
          break;
        }
        i -= j;
      }
      return paramInt - i;
    }
    
    public long skip(long paramLong)
      throws IOException
    {
      if (paramLong < 0L) {
        return 0L;
      }
      long l1 = paramLong;
      for (;;)
      {
        if (l1 > 0L)
        {
          long l2 = this.is.skip(l1);
          if (l2 > 0L)
          {
            l1 -= l2;
            continue;
          }
          if (this.is.read() != -1) {}
        }
        else
        {
          return paramLong - l1;
        }
        l1 -= 1L;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\DefaultImageHeaderParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */