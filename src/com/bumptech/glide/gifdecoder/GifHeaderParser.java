package com.bumptech.glide.gifdecoder;

import android.util.Log;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;

public class GifHeaderParser
{
  static final int DEFAULT_FRAME_DELAY = 10;
  private static final int DESCRIPTOR_MASK_INTERLACE_FLAG = 64;
  private static final int DESCRIPTOR_MASK_LCT_FLAG = 128;
  private static final int DESCRIPTOR_MASK_LCT_SIZE = 7;
  private static final int DESCRIPTOR_MASK_RESERVED = 24;
  private static final int DESCRIPTOR_MASK_SORT_FLAG = 32;
  private static final int EXTENSION_INTRODUCER = 33;
  private static final int GCE_DISPOSAL_METHOD_SHIFT = 2;
  private static final int GCE_MASK_DISPOSAL_METHOD = 28;
  private static final int GCE_MASK_RESERVED_BITS = 224;
  private static final int GCE_MASK_TRANSPARENT_COLOR_FLAG = 1;
  private static final int GCE_MASK_USER_INPUT_FLAG = 2;
  private static final int IMAGE_SEPARATOR = 44;
  private static final int LABEL_APPLICATION_EXTENSION = 255;
  private static final int LABEL_COMMENT_EXTENSION = 254;
  private static final int LABEL_GRAPHIC_CONTROL_EXTENSION = 249;
  private static final int LABEL_PLAIN_TEXT_EXTENSION = 1;
  private static final int LSD_MASK_COLOR_RESOLUTION = 112;
  private static final int LSD_MASK_GCT_FLAG = 128;
  private static final int LSD_MASK_GCT_SIZE = 7;
  private static final int LSD_MASK_SORT_FLAG = 8;
  private static final int MASK_INT_LOWEST_BYTE = 255;
  private static final int MAX_BLOCK_SIZE = 256;
  static final int MIN_FRAME_DELAY = 2;
  public static final String TAG = "GifHeaderParser";
  private static final int TRAILER = 59;
  private final byte[] block = new byte['Ā'];
  private int blockSize = 0;
  private GifHeader header;
  private ByteBuffer rawData;
  
  private boolean err()
  {
    return this.header.status != 0;
  }
  
  private int read()
  {
    try
    {
      int i = this.rawData.get();
      return i & 0xFF;
    }
    catch (Exception localException)
    {
      this.header.status = 1;
    }
    return 0;
  }
  
  private void readBitmap()
  {
    boolean bool = true;
    this.header.currentFrame.ix = readShort();
    this.header.currentFrame.iy = readShort();
    this.header.currentFrame.iw = readShort();
    this.header.currentFrame.ih = readShort();
    int j = read();
    int i;
    int k;
    if ((j & 0x80) != 0)
    {
      i = 1;
      k = (int)Math.pow(2.0D, (j & 0x7) + 1);
      localObject = this.header.currentFrame;
      if ((j & 0x40) == 0) {
        break label165;
      }
      label105:
      ((GifFrame)localObject).interlace = bool;
      if (i == 0) {
        break label171;
      }
    }
    label165:
    label171:
    for (this.header.currentFrame.lct = readColorTable(k);; this.header.currentFrame.lct = null)
    {
      this.header.currentFrame.bufferFrameStart = this.rawData.position();
      skipImageData();
      if (!err()) {
        break label185;
      }
      return;
      i = 0;
      break;
      bool = false;
      break label105;
    }
    label185:
    Object localObject = this.header;
    ((GifHeader)localObject).frameCount += 1;
    this.header.frames.add(this.header.currentFrame);
  }
  
  private int readBlock()
  {
    this.blockSize = read();
    int m = 0;
    int i = 0;
    if (this.blockSize > 0)
    {
      int j = 0;
      for (;;)
      {
        int k = j;
        m = i;
        try
        {
          if (i < this.blockSize)
          {
            k = j;
            j = this.blockSize - i;
            k = j;
            this.rawData.get(this.block, i, j);
            i += j;
          }
        }
        catch (Exception localException)
        {
          if (Log.isLoggable("GifHeaderParser", 3)) {
            Log.d("GifHeaderParser", "Error Reading Block n: " + i + " count: " + k + " blockSize: " + this.blockSize, localException);
          }
          this.header.status = 1;
          m = i;
        }
      }
    }
    return m;
  }
  
  private int[] readColorTable(int paramInt)
  {
    Object localObject = null;
    byte[] arrayOfByte = new byte[paramInt * 3];
    for (;;)
    {
      int i;
      try
      {
        this.rawData.get(arrayOfByte);
        arrayOfInt = new int['Ā'];
      }
      catch (BufferUnderflowException localBufferUnderflowException)
      {
        int[] arrayOfInt;
        if (!Log.isLoggable("GifHeaderParser", 3)) {
          continue;
        }
        Log.d("GifHeaderParser", "Format Error Reading Color Table", localBufferUnderflowException);
        this.header.status = 1;
      }
      arrayOfInt[i] = (0xFF000000 | (j & 0xFF) << 16 | (m & 0xFF) << 8 | n & 0xFF);
      int j = k + 1;
      i += 1;
      do
      {
        return (int[])localObject;
        j = 0;
        i = 0;
        localObject = localBufferUnderflowException;
      } while (i >= paramInt);
      int m = j + 1;
      j = arrayOfByte[j];
      int k = m + 1;
      m = arrayOfByte[m];
      int n = arrayOfByte[k];
    }
  }
  
  private void readContents()
  {
    readContents(Integer.MAX_VALUE);
  }
  
  private void readContents(int paramInt)
  {
    int i = 0;
    while ((i == 0) && (!err()) && (this.header.frameCount <= paramInt)) {
      switch (read())
      {
      default: 
        this.header.status = 1;
        break;
      case 44: 
        if (this.header.currentFrame == null) {
          this.header.currentFrame = new GifFrame();
        }
        readBitmap();
        break;
      case 33: 
        switch (read())
        {
        default: 
          skip();
          break;
        case 249: 
          this.header.currentFrame = new GifFrame();
          readGraphicControlExt();
          break;
        case 255: 
          readBlock();
          String str = "";
          int j = 0;
          while (j < 11)
          {
            str = str + (char)this.block[j];
            j += 1;
          }
          if (str.equals("NETSCAPE2.0")) {
            readNetscapeExt();
          } else {
            skip();
          }
          break;
        case 254: 
          skip();
          break;
        case 1: 
          skip();
        }
        break;
      case 59: 
        i = 1;
      }
    }
  }
  
  private void readGraphicControlExt()
  {
    boolean bool = true;
    read();
    int i = read();
    this.header.currentFrame.dispose = ((i & 0x1C) >> 2);
    if (this.header.currentFrame.dispose == 0) {
      this.header.currentFrame.dispose = 1;
    }
    GifFrame localGifFrame = this.header.currentFrame;
    if ((i & 0x1) != 0) {}
    for (;;)
    {
      localGifFrame.transparency = bool;
      int j = readShort();
      i = j;
      if (j < 2) {
        i = 10;
      }
      this.header.currentFrame.delay = (i * 10);
      this.header.currentFrame.transIndex = read();
      read();
      return;
      bool = false;
    }
  }
  
  private void readHeader()
  {
    String str = "";
    int i = 0;
    while (i < 6)
    {
      str = str + (char)read();
      i += 1;
    }
    if (!str.startsWith("GIF")) {
      this.header.status = 1;
    }
    do
    {
      return;
      readLSD();
    } while ((!this.header.gctFlag) || (err()));
    this.header.gct = readColorTable(this.header.gctSize);
    this.header.bgColor = this.header.gct[this.header.bgIndex];
  }
  
  private void readLSD()
  {
    this.header.width = readShort();
    this.header.height = readShort();
    int i = read();
    GifHeader localGifHeader = this.header;
    if ((i & 0x80) != 0) {}
    for (boolean bool = true;; bool = false)
    {
      localGifHeader.gctFlag = bool;
      this.header.gctSize = ((int)Math.pow(2.0D, (i & 0x7) + 1));
      this.header.bgIndex = read();
      this.header.pixelAspect = read();
      return;
    }
  }
  
  private void readNetscapeExt()
  {
    do
    {
      readBlock();
      if (this.block[0] == 1)
      {
        int i = this.block[1];
        int j = this.block[2];
        this.header.loopCount = ((j & 0xFF) << 8 | i & 0xFF);
      }
    } while ((this.blockSize > 0) && (!err()));
  }
  
  private int readShort()
  {
    return this.rawData.getShort();
  }
  
  private void reset()
  {
    this.rawData = null;
    Arrays.fill(this.block, (byte)0);
    this.header = new GifHeader();
    this.blockSize = 0;
  }
  
  private void skip()
  {
    int i;
    do
    {
      i = read();
      int j = Math.min(this.rawData.position() + i, this.rawData.limit());
      this.rawData.position(j);
    } while (i > 0);
  }
  
  private void skipImageData()
  {
    read();
    skip();
  }
  
  public void clear()
  {
    this.rawData = null;
    this.header = null;
  }
  
  public boolean isAnimated()
  {
    readHeader();
    if (!err()) {
      readContents(2);
    }
    return this.header.frameCount > 1;
  }
  
  public GifHeader parseHeader()
  {
    if (this.rawData == null) {
      throw new IllegalStateException("You must call setData() before parseHeader()");
    }
    if (err()) {
      return this.header;
    }
    readHeader();
    if (!err())
    {
      readContents();
      if (this.header.frameCount < 0) {
        this.header.status = 1;
      }
    }
    return this.header;
  }
  
  public GifHeaderParser setData(ByteBuffer paramByteBuffer)
  {
    reset();
    this.rawData = paramByteBuffer.asReadOnlyBuffer();
    this.rawData.position(0);
    this.rawData.order(ByteOrder.LITTLE_ENDIAN);
    return this;
  }
  
  public GifHeaderParser setData(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
    {
      setData(ByteBuffer.wrap(paramArrayOfByte));
      return this;
    }
    this.rawData = null;
    this.header.status = 2;
    return this;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\gifdecoder\GifHeaderParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */