package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class InflaterSource
  implements Source
{
  private int bufferBytesHeldByInflater;
  private boolean closed;
  private final Inflater inflater;
  private final BufferedSource source;
  
  InflaterSource(BufferedSource paramBufferedSource, Inflater paramInflater)
  {
    if (paramBufferedSource == null) {
      throw new IllegalArgumentException("source == null");
    }
    if (paramInflater == null) {
      throw new IllegalArgumentException("inflater == null");
    }
    this.source = paramBufferedSource;
    this.inflater = paramInflater;
  }
  
  public InflaterSource(Source paramSource, Inflater paramInflater)
  {
    this(Okio.buffer(paramSource), paramInflater);
  }
  
  private void releaseInflatedBytes()
    throws IOException
  {
    if (this.bufferBytesHeldByInflater == 0) {
      return;
    }
    int i = this.bufferBytesHeldByInflater - this.inflater.getRemaining();
    this.bufferBytesHeldByInflater -= i;
    this.source.skip(i);
  }
  
  public void close()
    throws IOException
  {
    if (this.closed) {
      return;
    }
    this.inflater.end();
    this.closed = true;
    this.source.close();
  }
  
  public long read(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("byteCount < 0: " + paramLong);
    }
    if (this.closed) {
      throw new IllegalStateException("closed");
    }
    if (paramLong == 0L) {
      return 0L;
    }
    boolean bool = refill();
    int i;
    for (;;)
    {
      try
      {
        localSegment = paramBuffer.writableSegment(1);
        i = this.inflater.inflate(localSegment.data, localSegment.limit, 8192 - localSegment.limit);
      }
      catch (DataFormatException paramBuffer)
      {
        Segment localSegment;
        throw new IOException(paramBuffer);
      }
      localSegment.limit += i;
      paramBuffer.size += i;
      break label217;
      if ((this.inflater.finished()) || (this.inflater.needsDictionary()))
      {
        releaseInflatedBytes();
        if (localSegment.pos != localSegment.limit) {
          break label221;
        }
        paramBuffer.head = localSegment.pop();
        SegmentPool.recycle(localSegment);
        break label221;
      }
      if (!bool) {
        break;
      }
      throw new EOFException("source exhausted prematurely");
      if (i <= 0) {}
    }
    label217:
    return i;
    label221:
    return -1L;
  }
  
  public boolean refill()
    throws IOException
  {
    if (!this.inflater.needsInput()) {
      return false;
    }
    releaseInflatedBytes();
    if (this.inflater.getRemaining() != 0) {
      throw new IllegalStateException("?");
    }
    if (this.source.exhausted()) {
      return true;
    }
    Segment localSegment = this.source.buffer().head;
    this.bufferBytesHeldByInflater = (localSegment.limit - localSegment.pos);
    this.inflater.setInput(localSegment.data, localSegment.pos, this.bufferBytesHeldByInflater);
    return false;
  }
  
  public Timeout timeout()
  {
    return this.source.timeout();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okio\InflaterSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */