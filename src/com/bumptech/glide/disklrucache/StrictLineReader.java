package com.bumptech.glide.disklrucache;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

class StrictLineReader
  implements Closeable
{
  private static final byte CR = 13;
  private static final byte LF = 10;
  private byte[] buf;
  private final Charset charset;
  private int end;
  private final InputStream in;
  private int pos;
  
  public StrictLineReader(InputStream paramInputStream, int paramInt, Charset paramCharset)
  {
    if ((paramInputStream == null) || (paramCharset == null)) {
      throw new NullPointerException();
    }
    if (paramInt < 0) {
      throw new IllegalArgumentException("capacity <= 0");
    }
    if (!paramCharset.equals(Util.US_ASCII)) {
      throw new IllegalArgumentException("Unsupported encoding");
    }
    this.in = paramInputStream;
    this.charset = paramCharset;
    this.buf = new byte[paramInt];
  }
  
  public StrictLineReader(InputStream paramInputStream, Charset paramCharset)
  {
    this(paramInputStream, 8192, paramCharset);
  }
  
  private void fillBuf()
    throws IOException
  {
    int i = this.in.read(this.buf, 0, this.buf.length);
    if (i == -1) {
      throw new EOFException();
    }
    this.pos = 0;
    this.end = i;
  }
  
  public void close()
    throws IOException
  {
    synchronized (this.in)
    {
      if (this.buf != null)
      {
        this.buf = null;
        this.in.close();
      }
      return;
    }
  }
  
  public boolean hasUnterminatedLine()
  {
    return this.end == -1;
  }
  
  public String readLine()
    throws IOException
  {
    synchronized (this.in)
    {
      if (this.buf == null) {
        throw new IOException("LineReader is closed");
      }
    }
    if (this.pos >= this.end) {
      fillBuf();
    }
    int i = this.pos;
    for (;;)
    {
      if (i != this.end)
      {
        if (this.buf[i] != 10) {
          break label272;
        }
        if ((i == this.pos) || (this.buf[(i - 1)] != 13)) {
          break label267;
        }
      }
      label267:
      for (int j = i - 1;; j = i)
      {
        Object localObject2 = new String(this.buf, this.pos, j - this.pos, this.charset.name());
        this.pos = (i + 1);
        return (String)localObject2;
        localObject2 = new ByteArrayOutputStream(this.end - this.pos + 80)
        {
          public String toString()
          {
            if ((this.count > 0) && (this.buf[(this.count - 1)] == 13)) {}
            for (int i = this.count - 1;; i = this.count) {
              try
              {
                String str = new String(this.buf, 0, i, StrictLineReader.this.charset.name());
                return str;
              }
              catch (UnsupportedEncodingException localUnsupportedEncodingException)
              {
                throw new AssertionError(localUnsupportedEncodingException);
              }
            }
          }
        };
        for (;;)
        {
          ((ByteArrayOutputStream)localObject2).write(this.buf, this.pos, this.end - this.pos);
          this.end = -1;
          fillBuf();
          i = this.pos;
          while (i != this.end)
          {
            if (this.buf[i] == 10)
            {
              if (i != this.pos) {
                ((ByteArrayOutputStream)localObject2).write(this.buf, this.pos, i - this.pos);
              }
              this.pos = (i + 1);
              localObject2 = ((ByteArrayOutputStream)localObject2).toString();
              return (String)localObject2;
            }
            i += 1;
          }
        }
      }
      label272:
      i += 1;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\disklrucache\StrictLineReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */