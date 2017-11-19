package cc.makeblock.makeblock.engine.blockly;

import android.content.res.Configuration;
import android.content.res.Resources;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import java.util.Locale;

public class BlocklyUtil
{
  public static final int BOARD_TYPE_AIRBLOCK = 1;
  public static final int BOARD_TYPE_HD = 0;
  public static final int BOARD_TYPE_OCTOPUS = 2;
  
  public static String createDataString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < paramArrayOfByte.length)
    {
      if (i == paramArrayOfByte.length - 1) {
        localStringBuffer.append(paramArrayOfByte[i] & 0xFF);
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuffer.append((paramArrayOfByte[i] & 0xFF) + " ");
      }
    }
    return localStringBuffer.toString();
  }
  
  public static String getCallingJSJson(String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    String str1 = "{";
    int i = 0;
    if (i < paramArrayOfString1.length)
    {
      String str2 = paramArrayOfString1[i];
      String str3 = paramArrayOfString2[i];
      str1 = str1 + "\"" + str2 + "\":\"" + str3 + "\"";
      if (i != paramArrayOfString1.length - 1) {}
      for (str1 = str1 + ",";; str1 = str1 + "}")
      {
        i += 1;
        break;
      }
    }
    return str1;
  }
  
  public static String getDataStringForLoadUrl(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    String str1 = "{";
    int i = 0;
    if (i < paramArrayOfString1.length)
    {
      String str2 = paramArrayOfString1[i];
      String str3 = paramArrayOfString2[i];
      str1 = str1 + "\"" + str2 + "\":\"" + str3 + "\"";
      if (i != paramArrayOfString1.length - 1) {}
      for (str1 = str1 + ",";; str1 = str1 + "}")
      {
        i += 1;
        break;
      }
    }
    if (paramArrayOfString1.length > 0) {
      return "javascript:nativeCallWeb('" + paramString + "','" + str1 + "')";
    }
    return "javascript:nativeCallWeb('" + paramString + "')";
  }
  
  public static String getHtmlPath()
  {
    String str1 = SharedPreferencesUtils.getCurrentChooseDeviceBoardName();
    int i;
    String str2;
    if (str1.equalsIgnoreCase(DeviceBoardName.crystal.name()))
    {
      i = 1;
      str1 = App.getContext().getResources().getConfiguration().locale.toString();
      switch (i)
      {
      default: 
        str2 = "file:///android_asset/mblockly-zero/views/makeblockhd/";
        label67:
        if ((str1.contains("zh_TW")) || (str1.contains("zh_HK"))) {
          str1 = "index-hant.html";
        }
        break;
      }
    }
    for (;;)
    {
      return str2 + str1;
      if (str1.equalsIgnoreCase(DeviceBoardName.octopus.name()))
      {
        i = 2;
        break;
      }
      i = 0;
      break;
      str2 = "file:///android_asset/mblockly-zero/views/makeblockhd/";
      break label67;
      str2 = "file:///android_asset/mblockly-zero/views/airblock/";
      break label67;
      str2 = "file:///android_asset/mblockly-zero/views/makeblockNeuron/";
      break label67;
      if (str1.contains("zh_CN")) {
        str1 = "index-hans.html";
      } else if (str1.contains("ja")) {
        str1 = "index-ja.html";
      } else if (str1.contains("hr")) {
        str1 = "index-hr.html";
      } else if (str1.contains("es")) {
        str1 = "index-es.html";
      } else if (str1.contains("fr")) {
        str1 = "index-fr.html";
      } else if (str1.contains("de")) {
        str1 = "index-de.html";
      } else if (str1.contains("ru")) {
        str1 = "index-ru.html";
      } else if (str1.contains("et")) {
        str1 = "index-et.html";
      } else {
        str1 = "index.html";
      }
    }
  }
  
  public static byte[] parseStringToBytes(String paramString)
  {
    paramString = paramString.split(" ");
    int j = paramString.length;
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    while (i < j)
    {
      arrayOfByte[i] = ((byte)(int)Float.parseFloat(paramString[i]));
      i += 1;
    }
    return arrayOfByte;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\blockly\BlocklyUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */