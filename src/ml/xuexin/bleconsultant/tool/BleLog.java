package ml.xuexin.bleconsultant.tool;

import android.util.Log;

public class BleLog
{
  public static boolean DEBUG = false;
  public static final String TAG = "BleConsultant";
  
  public static void d(String paramString)
  {
    if (DEBUG) {
      Log.d("BleConsultant", paramString);
    }
  }
  
  public static void e(String paramString)
  {
    Log.e("BleConsultant", paramString);
  }
  
  public static void i(String paramString)
  {
    if (DEBUG) {
      Log.i("BleConsultant", paramString);
    }
  }
  
  public static String parseByte(byte[] paramArrayOfByte)
  {
    String str = "";
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      str = str + String.format(" %02x", new Object[] { Integer.valueOf(paramArrayOfByte[i] & 0xFF) });
      i += 1;
    }
    return str;
  }
  
  public static void w(String paramString)
  {
    if (DEBUG) {
      Log.w("BleConsultant", paramString);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\tool\BleLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */