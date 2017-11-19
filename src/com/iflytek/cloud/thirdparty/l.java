package com.iflytek.cloud.thirdparty;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class l
{
  public static String a(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, "gb2312");
  }
  
  public static String a(byte[] paramArrayOfByte, String paramString)
  {
    String str = null;
    int i;
    if (paramArrayOfByte != null) {
      i = 0;
    }
    for (;;)
    {
      if ((i >= paramArrayOfByte.length) || (paramArrayOfByte[i] == 0))
      {
        str = new String(paramArrayOfByte, 0, Math.min(i, paramArrayOfByte.length), Charset.forName(paramString));
        return str;
      }
      i += 1;
    }
  }
  
  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  public static byte[] a(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    byte[] arrayOfByte = null;
    if (paramString1 != null)
    {
      if (paramString1.length() > 0)
      {
        paramString1 = paramString1.getBytes(paramString2);
        arrayOfByte = new byte[paramString1.length + 1];
        System.arraycopy(paramString1, 0, arrayOfByte, 0, paramString1.length);
        arrayOfByte[paramString1.length] = 0;
      }
    }
    else {
      return arrayOfByte;
    }
    return new byte[] { 0 };
  }
  
  public static byte[] b(String paramString)
    throws UnsupportedEncodingException
  {
    return a(paramString, "gb2312");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */