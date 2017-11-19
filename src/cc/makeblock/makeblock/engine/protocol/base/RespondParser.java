package cc.makeblock.makeblock.engine.protocol.base;

public abstract class RespondParser
{
  private final byte[] buffer = new byte['Ϩ'];
  private final byte[] head;
  private int length = 0;
  private final byte[] tail;
  
  protected RespondParser(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    this.head = paramArrayOfByte1;
    this.tail = paramArrayOfByte2;
  }
  
  private byte[] extractBytes(int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    System.arraycopy(this.buffer, 0, arrayOfByte, 0, paramInt);
    System.arraycopy(this.buffer, paramInt, this.buffer, 0, this.length - paramInt);
    this.length -= paramInt;
    return arrayOfByte;
  }
  
  private int findHeadIndex()
  {
    int i = 0;
    while (i <= this.length - this.head.length)
    {
      int m = 1;
      int j = 0;
      for (;;)
      {
        int k = m;
        if (j < this.head.length)
        {
          if (this.buffer[(i + j)] != this.head[j]) {
            k = 0;
          }
        }
        else
        {
          if (k == 0) {
            break;
          }
          return i;
        }
        j += 1;
      }
      i += 1;
    }
    return -1;
  }
  
  private int findTailIndex()
  {
    int i = this.head.length;
    while (i <= this.length - this.tail.length)
    {
      int m = 1;
      int j = 0;
      for (;;)
      {
        int k = m;
        if (j < this.tail.length)
        {
          if (this.buffer[(i + j)] != this.tail[j]) {
            k = 0;
          }
        }
        else
        {
          if (k == 0) {
            break;
          }
          return i;
        }
        j += 1;
      }
      i += 1;
    }
    return -1;
  }
  
  private void parseData()
  {
    if (this.length < this.head.length + this.tail.length) {}
    int i;
    do
    {
      do
      {
        return;
        if (this.length > this.buffer.length * 2 / 3) {
          extractBytes(this.buffer.length / 2);
        }
        i = findHeadIndex();
        if (i > 0)
        {
          extractBytes(i);
          parseData();
          return;
        }
      } while (i < 0);
      i = findTailIndex();
    } while (i <= 0);
    packData(extractBytes(this.tail.length + i));
    parseData();
  }
  
  protected abstract void packData(byte[] paramArrayOfByte);
  
  public void parseBytes(byte[] paramArrayOfByte)
  {
    int i = this.length;
    this.length += paramArrayOfByte.length;
    if (this.length > this.buffer.length) {
      throw new RuntimeException("缓冲区大小不够,真的假的");
    }
    System.arraycopy(paramArrayOfByte, 0, this.buffer, i, paramArrayOfByte.length);
    parseData();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\base\RespondParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */