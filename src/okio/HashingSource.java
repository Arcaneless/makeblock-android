package okio;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashingSource
  extends ForwardingSource
{
  private final MessageDigest messageDigest;
  
  private HashingSource(Source paramSource, String paramString)
  {
    super(paramSource);
    try
    {
      this.messageDigest = MessageDigest.getInstance(paramString);
      return;
    }
    catch (NoSuchAlgorithmException paramSource)
    {
      throw new AssertionError();
    }
  }
  
  public static HashingSource md5(Source paramSource)
  {
    return new HashingSource(paramSource, "MD5");
  }
  
  public static HashingSource sha1(Source paramSource)
  {
    return new HashingSource(paramSource, "SHA-1");
  }
  
  public static HashingSource sha256(Source paramSource)
  {
    return new HashingSource(paramSource, "SHA-256");
  }
  
  public ByteString hash()
  {
    return ByteString.of(this.messageDigest.digest());
  }
  
  public long read(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    long l4 = super.read(paramBuffer, paramLong);
    if (l4 != -1L)
    {
      long l3 = paramBuffer.size - l4;
      paramLong = paramBuffer.size;
      Segment localSegment1 = paramBuffer.head;
      long l1;
      Segment localSegment2;
      long l2;
      for (;;)
      {
        l1 = paramLong;
        localSegment2 = localSegment1;
        l2 = l3;
        if (paramLong <= l3) {
          break;
        }
        localSegment1 = localSegment1.prev;
        paramLong -= localSegment1.limit - localSegment1.pos;
      }
      while (l1 < paramBuffer.size)
      {
        int i = (int)(localSegment2.pos + l2 - l1);
        this.messageDigest.update(localSegment2.data, i, localSegment2.limit - i);
        l1 += localSegment2.limit - localSegment2.pos;
        l2 = l1;
        localSegment2 = localSegment2.next;
      }
    }
    return l4;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okio\HashingSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */