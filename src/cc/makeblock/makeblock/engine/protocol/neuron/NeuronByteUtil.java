package cc.makeblock.makeblock.engine.protocol.neuron;

public class NeuronByteUtil
{
  static final int SIZE_BYTE = 1;
  static final int SIZE_SHORT = 2;
  static final int SIZE_byte = 2;
  static final int SIZE_float = 5;
  static final int SIZE_long = 5;
  static final int SIZE_short = 3;
  static final int TYPE_BYTE = 1;
  static final int TYPE_SHORT = 3;
  static final int TYPE_byte = 2;
  static final int TYPE_float = 6;
  static final int TYPE_long = 5;
  static final int TYPE_short = 4;
  
  public static int convert7to8(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = 0;
    while (i < paramInt2 - paramInt1)
    {
      j |= paramArrayOfByte[(i + paramInt1)] << i * 7;
      i += 1;
    }
    return j;
  }
  
  public static byte[] convert8to7(int paramInt1, int paramInt2)
  {
    int i;
    switch (paramInt1)
    {
    default: 
      return null;
    case 1: 
      return new byte[] { (byte)(paramInt2 & 0x7F) };
    case 3: 
      i = (byte)((paramInt2 & 0x3F80) >>> 7);
      return new byte[] { (byte)(paramInt2 & 0x7F), i };
    case 2: 
      i = (byte)((paramInt2 & 0x80) >>> 7);
      return new byte[] { (byte)(paramInt2 & 0x7F), i };
    case 4: 
      return new byte[] { (byte)(paramInt2 & 0x7F), (byte)((paramInt2 & 0x3F80) >>> 7), (byte)((0xC000 & paramInt2) >>> 14) };
    case 5: 
      return new byte[] { (byte)(paramInt2 & 0x7F), (byte)((paramInt2 & 0x3F80) >>> 7), (byte)((0x1FC000 & paramInt2) >>> 14), (byte)((0xFE00000 & paramInt2) >>> 21), (byte)((paramInt2 & 0xF0000000) >>> 28) };
    }
    return new byte[] { (byte)(paramInt2 & 0x7F), (byte)((paramInt2 & 0x3F80) >>> 7), (byte)((0x1FC000 & paramInt2) >>> 14), (byte)((0xFE00000 & paramInt2) >>> 21), (byte)((paramInt2 & 0xF0000000) >>> 28) };
  }
  
  public static byte getCheckSum(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramArrayOfByte == null) || (paramInt1 <= 0) || (paramInt2 >= paramArrayOfByte.length)) {
      throw new RuntimeException("数据不对怎么算校验");
    }
    int i = 0;
    while (paramInt1 < paramInt2)
    {
      i = (byte)(paramArrayOfByte[paramInt1] + i);
      paramInt1 += 1;
    }
    return (byte)(i & 0x7F);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\NeuronByteUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */