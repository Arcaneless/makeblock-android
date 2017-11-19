package cc.makeblock.makeblock.engine.utils;

import android.util.Pair;

public class CodeyByteUtil
{
  public static Pair<String, String> byteArrayToString(byte[] paramArrayOfByte)
  {
    int i = findEndChar(paramArrayOfByte);
    if (i == -1) {}
    String str;
    do
    {
      return null;
      str = new String(paramArrayOfByte, 0, i);
    } while (paramArrayOfByte[(i + 1)] != 12);
    return new Pair(str, new String(paramArrayOfByte, i + 2, paramArrayOfByte.length - i - 2));
  }
  
  public static byte[] calcFileCheckSum(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte1 = new byte[4];
    byte[] tmp7_5 = arrayOfByte1;
    tmp7_5[0] = 0;
    byte[] tmp12_7 = tmp7_5;
    tmp12_7[1] = 0;
    byte[] tmp17_12 = tmp12_7;
    tmp17_12[2] = 0;
    byte[] tmp22_17 = tmp17_12;
    tmp22_17[3] = 0;
    tmp22_17;
    int j = paramArrayOfByte.length;
    int k = j % 4;
    byte[] arrayOfByte2 = new byte[k + j];
    int i = 0;
    if (i < k + j)
    {
      if (i < j) {
        arrayOfByte2[i] = paramArrayOfByte[i];
      }
      for (;;)
      {
        i += 1;
        break;
        arrayOfByte2[i] = 0;
      }
    }
    j = (int)Math.ceil(arrayOfByte2.length / 4);
    i = 0;
    while (i < j)
    {
      arrayOfByte1[0] = ((byte)(arrayOfByte1[0] ^ arrayOfByte2[(i * 4 + 0)]));
      arrayOfByte1[1] = ((byte)(arrayOfByte1[1] ^ arrayOfByte2[(i * 4 + 1)]));
      arrayOfByte1[2] = ((byte)(arrayOfByte1[2] ^ arrayOfByte2[(i * 4 + 2)]));
      arrayOfByte1[3] = ((byte)(arrayOfByte1[3] ^ arrayOfByte2[(i * 4 + 3)]));
      i += 1;
    }
    return arrayOfByte1;
  }
  
  public static byte calcFrameCheckSum(byte[] paramArrayOfByte)
  {
    return calcFrameCheckSum(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static byte calcFrameCheckSum(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = paramInt1;
    while (i < paramInt1 + paramInt2)
    {
      j = (byte)(paramArrayOfByte[i] + j);
      i += 1;
    }
    return (byte)(j & 0xFF);
  }
  
  private static int findEndChar(byte[] paramArrayOfByte)
  {
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      if (paramArrayOfByte[i] == 0) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static byte[] intToByteArray(Integer paramInteger)
  {
    return new byte[] { (byte)(paramInteger.intValue() >> 24 & 0xFF), (byte)(paramInteger.intValue() >> 16 & 0xFF), (byte)(paramInteger.intValue() >> 8 & 0xFF), (byte)(paramInteger.intValue() & 0xFF) };
  }
  
  public static byte[] reverseBytes(byte[] paramArrayOfByte)
  {
    int j = paramArrayOfByte.length;
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    while (i < j)
    {
      arrayOfByte[(j - 1 - i)] = paramArrayOfByte[i];
      i += 1;
    }
    return arrayOfByte;
  }
  
  public static byte[] sliceBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt2 - paramInt1];
    int i = paramInt1;
    while (i < paramInt2)
    {
      arrayOfByte[(i - paramInt1)] = paramArrayOfByte[i];
      i += 1;
    }
    return arrayOfByte;
  }
  
  public static byte[] stringToByteArray(String paramString)
  {
    paramString = paramString.toCharArray();
    int j = paramString.length;
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    while (i < j)
    {
      arrayOfByte[i] = ((byte)paramString[i]);
      i += 1;
    }
    return arrayOfByte;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\CodeyByteUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */