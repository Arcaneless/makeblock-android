package cc.makeblock.makeblock.engine.utils;

import android.content.Context;
import android.content.res.AssetManager;
import cc.makeblock.makeblock.base.App;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoFileUtil
{
  public static final String CPU_TYPE = "armeabi";
  
  private static void copyFilesFassets(Context paramContext, String paramString1, String paramString2)
  {
    int i = 0;
    label162:
    label177:
    label182:
    for (;;)
    {
      String[] arrayOfString;
      int j;
      String str;
      try
      {
        arrayOfString = paramContext.getAssets().list(paramString1);
        if (arrayOfString.length > 0)
        {
          new File(paramString2).mkdirs();
          j = arrayOfString.length;
          break label162;
          copyFilesFassets(paramContext, paramString1 + "/" + str, paramString2 + str);
          break label177;
        }
        paramContext = paramContext.getAssets().open(paramString1);
        paramString1 = new FileOutputStream(new File(paramString2));
        paramString2 = new byte['Ѐ'];
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      i = paramContext.read(paramString2);
      if (i != -1)
      {
        paramString1.write(paramString2, 0, i);
      }
      else
      {
        paramString1.flush();
        paramContext.close();
        paramString1.close();
        return;
        for (;;)
        {
          if (i >= j) {
            break label182;
          }
          str = arrayOfString[i];
          break;
          i += 1;
        }
      }
    }
  }
  
  public static void initAssetsFile()
  {
    k = 0;
    Object localObject = App.getContext().getDir("armeabi", 0);
    localObject = ((File)localObject).getAbsolutePath() + "/";
    for (;;)
    {
      try
      {
        arrayOfString = App.getContext().getAssets().list("armeabi");
      }
      catch (IOException localIOException)
      {
        String[] arrayOfString;
        boolean bool;
        localIOException.printStackTrace();
        int j = k;
        continue;
        int i = 0;
        continue;
      }
      j = k;
      if (arrayOfString != null)
      {
        j = k;
        if (i < arrayOfString.length)
        {
          bool = new File((String)localObject + arrayOfString[i]).exists();
          if (bool) {
            continue;
          }
          j = 1;
        }
      }
      if (j != 0) {
        copyFilesFassets(App.getContext(), "armeabi", (String)localObject);
      }
      return;
      i += 1;
    }
  }
  
  public static void load()
  {
    File[] arrayOfFile = App.getContext().getDir("armeabi", 0).listFiles();
    int i = 0;
    while (i < arrayOfFile.length)
    {
      String str = arrayOfFile[i].getAbsolutePath();
      if (str.contains("main")) {
        System.load(str);
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\SoFileUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */