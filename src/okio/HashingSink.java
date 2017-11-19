package okio;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashingSink
  extends ForwardingSink
{
  private final MessageDigest messageDigest;
  
  private HashingSink(Sink paramSink, String paramString)
  {
    super(paramSink);
    try
    {
      this.messageDigest = MessageDigest.getInstance(paramString);
      return;
    }
    catch (NoSuchAlgorithmException paramSink)
    {
      throw new AssertionError();
    }
  }
  
  public static HashingSink md5(Sink paramSink)
  {
    return new HashingSink(paramSink, "MD5");
  }
  
  public static HashingSink sha1(Sink paramSink)
  {
    return new HashingSink(paramSink, "SHA-1");
  }
  
  public static HashingSink sha256(Sink paramSink)
  {
    return new HashingSink(paramSink, "SHA-256");
  }
  
  public ByteString hash()
  {
    return ByteString.of(this.messageDigest.digest());
  }
  
  public void write(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    Util.checkOffsetAndCount(paramBuffer.size, 0L, paramLong);
    long l = 0L;
    for (Segment localSegment = paramBuffer.head; l < paramLong; localSegment = localSegment.next)
    {
      int i = (int)Math.min(paramLong - l, localSegment.limit - localSegment.pos);
      this.messageDigest.update(localSegment.data, localSegment.pos, i);
      l += i;
    }
    super.write(paramBuffer, paramLong);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okio\HashingSink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */