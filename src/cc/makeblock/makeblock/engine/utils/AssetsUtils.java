package cc.makeblock.makeblock.engine.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetsUtils
{
  private static final int hexFileDataContentIndex = 9;
  private static final int hexFileDataLengthIndex = 1;
  
  private static byte duoHexToByte(char paramChar1, char paramChar2)
  {
    return (byte)(hexToByte(paramChar1) << 4 | hexToByte(paramChar2));
  }
  
  public static byte[] getFirmwareFromAssetsFile(Context paramContext, String paramString)
  {
    paramContext = paramContext.getResources().getAssets();
    byte[] arrayOfByte1 = new byte[1000000];
    int k = 0;
    int i = 0;
    int j = i;
    try
    {
      paramString = paramContext.open(paramString);
      j = i;
      localInputStreamReader = new InputStreamReader(paramString);
      j = i;
      localBufferedReader = new BufferedReader(localInputStreamReader);
      j = i;
      arrayOfByte2 = new byte[20];
      j = i;
      paramContext = localBufferedReader.readLine();
      i = k;
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        InputStreamReader localInputStreamReader;
        BufferedReader localBufferedReader;
        byte[] arrayOfByte2;
        paramContext.printStackTrace();
        i = j;
      }
    }
    j = i;
    paramContext = paramContext.toCharArray();
    break label213;
    label84:
    j = i;
    int n = duoHexToByte(paramContext[1], paramContext[2]);
    break label219;
    j = i;
    int m;
    arrayOfByte2[m] = duoHexToByte(paramContext[(k * 2 + 9)], paramContext[(k * 2 + 9 + 1)]);
    break label232;
    label133:
    j = i;
    System.arraycopy(arrayOfByte2, 0, arrayOfByte1, i, m);
    break label259;
    label149:
    j = k;
    paramContext = localBufferedReader.readLine();
    label213:
    label219:
    label232:
    label248:
    label259:
    label272:
    for (;;)
    {
      j = i;
      localBufferedReader.close();
      j = i;
      localInputStreamReader.close();
      j = i;
      paramString.close();
      paramContext = new byte[i];
      System.arraycopy(arrayOfByte1, 0, paramContext, 0, i);
      return paramContext;
      for (;;)
      {
        if (paramContext == null) {
          break label272;
        }
        break;
        m = 0;
        break label84;
        for (k = 0;; k = (char)(k + 1))
        {
          if (k >= n) {
            break label248;
          }
          break;
          m += 1;
        }
        k = i;
        if (m <= 0) {
          break label149;
        }
        break label133;
        k = i + m;
        break label149;
        i = k;
      }
    }
  }
  
  public static Bitmap getImageFromAssetsFile(Context paramContext, String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = paramContext.getResources().getAssets();
    paramContext = (Context)localObject1;
    try
    {
      localObject2 = ((AssetManager)localObject2).open(paramString);
      paramContext = (Context)localObject1;
      paramString = BitmapFactory.decodeStream((InputStream)localObject2);
      paramContext = paramString;
      ((InputStream)localObject2).close();
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return paramContext;
  }
  
  private static byte hexToByte(char paramChar)
  {
    if (paramChar > '9') {
      return (byte)(paramChar - 'A' + 10);
    }
    return (byte)(paramChar - '0');
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\AssetsUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */