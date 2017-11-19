package com.umeng.analytics.pro;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.analytics.a;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import javax.microedition.khronos.opengles.GL10;

public class bv
{
  protected static final String a = bv.class.getName();
  public static final String b = "";
  public static final String c = "2G/3G";
  public static final String d = "Wi-Fi";
  public static final int e = 8;
  private static final String f = "ro.miui.ui.version.name";
  
  /* Error */
  public static String A(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +95 -> 96
    //   4: aload_0
    //   5: ldc 41
    //   7: invokevirtual 47	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   10: checkcast 49	android/telephony/TelephonyManager
    //   13: astore_1
    //   14: goto +84 -> 98
    //   17: aload_0
    //   18: ldc 51
    //   20: invokestatic 54	com/umeng/analytics/pro/bv:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   23: ifeq +68 -> 91
    //   26: aload_1
    //   27: invokevirtual 57	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   30: astore_1
    //   31: aload_1
    //   32: astore_2
    //   33: aload_1
    //   34: astore_3
    //   35: aload_1
    //   36: invokestatic 63	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   39: ifeq +44 -> 83
    //   42: aload_1
    //   43: astore_3
    //   44: aload_0
    //   45: invokevirtual 67	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   48: ldc 69
    //   50: invokestatic 75	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   53: astore_0
    //   54: aload_0
    //   55: astore_2
    //   56: aload_0
    //   57: astore_3
    //   58: aload_0
    //   59: invokestatic 63	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   62: ifeq +21 -> 83
    //   65: aload_0
    //   66: astore_2
    //   67: aload_0
    //   68: astore_3
    //   69: getstatic 80	android/os/Build$VERSION:SDK_INT	I
    //   72: bipush 9
    //   74: if_icmplt +9 -> 83
    //   77: aload_0
    //   78: astore_3
    //   79: getstatic 85	android/os/Build:SERIAL	Ljava/lang/String;
    //   82: astore_2
    //   83: aload_2
    //   84: areturn
    //   85: astore_0
    //   86: aconst_null
    //   87: areturn
    //   88: astore_0
    //   89: aload_3
    //   90: areturn
    //   91: aconst_null
    //   92: astore_1
    //   93: goto -62 -> 31
    //   96: aconst_null
    //   97: areturn
    //   98: aload_1
    //   99: ifnull -8 -> 91
    //   102: goto -85 -> 17
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	paramContext	Context
    //   13	86	1	localObject1	Object
    //   32	52	2	localObject2	Object
    //   34	56	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   4	14	85	java/lang/Throwable
    //   17	31	85	java/lang/Throwable
    //   35	42	88	java/lang/Throwable
    //   44	54	88	java/lang/Throwable
    //   58	65	88	java/lang/Throwable
    //   69	77	88	java/lang/Throwable
    //   79	83	88	java/lang/Throwable
  }
  
  private static Locale B(Context paramContext)
  {
    localObject = null;
    try
    {
      localConfiguration = new Configuration();
      localConfiguration.setToDefaults();
      Settings.System.getConfiguration(paramContext.getContentResolver(), localConfiguration);
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        Configuration localConfiguration;
        by.c(a, new Object[] { "fail to read user config locale" });
        paramContext = (Context)localObject;
        continue;
        paramContext = (Context)localObject;
        if (localConfiguration == null) {}
      }
    }
    paramContext = localConfiguration.locale;
    localObject = paramContext;
    if (paramContext == null) {
      localObject = Locale.getDefault();
    }
    return (Locale)localObject;
  }
  
  private static String C(Context paramContext)
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if (a(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
        return localWifiManager.getConnectionInfo().getMacAddress();
      }
      return "";
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  private static String D(Context paramContext)
  {
    Object localObject2;
    Object localObject1;
    if (Build.VERSION.SDK_INT < 23)
    {
      localObject2 = F(paramContext);
      localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2))
      {
        localObject2 = C(paramContext);
        localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2))
        {
          paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
          localObject1 = paramContext;
          if (TextUtils.isEmpty(paramContext)) {
            localObject1 = d();
          }
        }
      }
    }
    label138:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return (String)localObject1;
              if (Build.VERSION.SDK_INT != 23) {
                break;
              }
              localObject2 = F(paramContext);
              localObject1 = localObject2;
            } while (!TextUtils.isEmpty((CharSequence)localObject2));
            localObject1 = b();
            localObject2 = localObject1;
            if (TextUtils.isEmpty((CharSequence)localObject1)) {
              if (!a.d) {
                break label138;
              }
            }
            for (localObject2 = c();; localObject2 = C(paramContext))
            {
              localObject1 = localObject2;
              if (!TextUtils.isEmpty((CharSequence)localObject2)) {
                break;
              }
              paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
              localObject1 = paramContext;
              if (!TextUtils.isEmpty(paramContext)) {
                break;
              }
              return d();
            }
            localObject2 = F(paramContext);
            localObject1 = localObject2;
          } while (!TextUtils.isEmpty((CharSequence)localObject2));
          localObject2 = d();
          localObject1 = localObject2;
        } while (!TextUtils.isEmpty((CharSequence)localObject2));
        localObject2 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
        localObject1 = localObject2;
      } while (!TextUtils.isEmpty((CharSequence)localObject2));
      localObject2 = b();
      localObject1 = localObject2;
    } while (!TextUtils.isEmpty((CharSequence)localObject2));
    return C(paramContext);
  }
  
  private static String E(Context paramContext)
  {
    Object localObject2;
    Object localObject1;
    if (Build.VERSION.SDK_INT < 23)
    {
      localObject2 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2))
      {
        localObject2 = C(paramContext);
        localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2))
        {
          localObject2 = d();
          localObject1 = localObject2;
          if (TextUtils.isEmpty((CharSequence)localObject2)) {
            localObject1 = F(paramContext);
          }
        }
      }
    }
    label138:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return (String)localObject1;
              if (Build.VERSION.SDK_INT != 23) {
                break;
              }
              localObject2 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
              localObject1 = localObject2;
            } while (!TextUtils.isEmpty((CharSequence)localObject2));
            localObject1 = b();
            localObject2 = localObject1;
            if (TextUtils.isEmpty((CharSequence)localObject1)) {
              if (!a.d) {
                break label138;
              }
            }
            for (localObject2 = c();; localObject2 = C(paramContext))
            {
              localObject1 = localObject2;
              if (!TextUtils.isEmpty((CharSequence)localObject2)) {
                break;
              }
              localObject2 = d();
              localObject1 = localObject2;
              if (!TextUtils.isEmpty((CharSequence)localObject2)) {
                break;
              }
              return F(paramContext);
            }
            localObject2 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
            localObject1 = localObject2;
          } while (!TextUtils.isEmpty((CharSequence)localObject2));
          localObject2 = d();
          localObject1 = localObject2;
        } while (!TextUtils.isEmpty((CharSequence)localObject2));
        localObject2 = F(paramContext);
        localObject1 = localObject2;
      } while (!TextUtils.isEmpty((CharSequence)localObject2));
      localObject2 = b();
      localObject1 = localObject2;
    } while (!TextUtils.isEmpty((CharSequence)localObject2));
    return C(paramContext);
  }
  
  private static String F(Context paramContext)
  {
    Object localObject = "";
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    if (localTelephonyManager != null) {}
    for (;;)
    {
      try
      {
        if (a(paramContext, "android.permission.READ_PHONE_STATE"))
        {
          paramContext = localTelephonyManager.getDeviceId();
          localObject = paramContext;
          return (String)localObject;
        }
      }
      catch (Throwable paramContext)
      {
        return "";
      }
      paramContext = "";
    }
  }
  
  private static int a(Object paramObject, String paramString)
  {
    try
    {
      paramString = DisplayMetrics.class.getDeclaredField(paramString);
      paramString.setAccessible(true);
      int i = paramString.getInt(paramObject);
      return i;
    }
    catch (Throwable paramObject) {}
    return -1;
  }
  
  public static String a()
  {
    String str = null;
    localObject1 = null;
    for (;;)
    {
      try
      {
        FileReader localFileReader = new FileReader("/proc/cpuinfo");
        if (localFileReader != null) {
          localObject1 = str;
        }
        try
        {
          BufferedReader localBufferedReader = new BufferedReader(localFileReader, 1024);
          localObject1 = str;
          str = localBufferedReader.readLine();
          localObject1 = str;
          Object localObject2 = str;
          by.e(a, "Could not open file /proc/cpuinfo", localFileNotFoundException1);
        }
        catch (Throwable localThrowable)
        {
          try
          {
            localBufferedReader.close();
            localObject1 = str;
            localObject2 = str;
            localFileReader.close();
            localObject1 = str;
            if (localObject1 == null) {
              continue;
            }
            return ((String)localObject1).substring(((String)localObject1).indexOf(':') + 1).trim();
          }
          catch (FileNotFoundException localFileNotFoundException1)
          {
            localObject1 = localObject2;
          }
          localThrowable = localThrowable;
          localObject2 = localObject1;
          by.e(a, "Could not read from file /proc/cpuinfo", localThrowable);
        }
      }
      catch (FileNotFoundException localFileNotFoundException2)
      {
        localObject1 = null;
      }
    }
    return "";
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return String.valueOf(i);
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "";
  }
  
  /* Error */
  private static String a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 171	java/io/FileReader
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 176	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   10: astore_3
    //   11: aload_3
    //   12: ifnull +40 -> 52
    //   15: new 178	java/io/BufferedReader
    //   18: dup
    //   19: aload_3
    //   20: sipush 1024
    //   23: invokespecial 181	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   26: astore_2
    //   27: aload_2
    //   28: invokevirtual 184	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   31: astore_0
    //   32: aload_3
    //   33: ifnull +7 -> 40
    //   36: aload_3
    //   37: invokevirtual 188	java/io/FileReader:close	()V
    //   40: aload_0
    //   41: astore_1
    //   42: aload_2
    //   43: ifnull +9 -> 52
    //   46: aload_2
    //   47: invokevirtual 187	java/io/BufferedReader:close	()V
    //   50: aload_0
    //   51: astore_1
    //   52: aload_1
    //   53: areturn
    //   54: astore_0
    //   55: aconst_null
    //   56: astore_1
    //   57: aload_3
    //   58: ifnull +7 -> 65
    //   61: aload_3
    //   62: invokevirtual 188	java/io/FileReader:close	()V
    //   65: aload_1
    //   66: ifnull +7 -> 73
    //   69: aload_1
    //   70: invokevirtual 187	java/io/BufferedReader:close	()V
    //   73: aload_0
    //   74: athrow
    //   75: astore_0
    //   76: aconst_null
    //   77: areturn
    //   78: astore_1
    //   79: goto -39 -> 40
    //   82: astore_1
    //   83: aload_0
    //   84: areturn
    //   85: astore_2
    //   86: goto -21 -> 65
    //   89: astore_1
    //   90: goto -17 -> 73
    //   93: astore_0
    //   94: aload_2
    //   95: astore_1
    //   96: goto -39 -> 57
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	paramString	String
    //   1	69	1	str	String
    //   78	1	1	localThrowable1	Throwable
    //   82	1	1	localThrowable2	Throwable
    //   89	1	1	localThrowable3	Throwable
    //   95	1	1	localObject	Object
    //   26	21	2	localBufferedReader	BufferedReader
    //   85	10	2	localThrowable4	Throwable
    //   10	52	3	localFileReader	FileReader
    // Exception table:
    //   from	to	target	type
    //   15	27	54	finally
    //   2	11	75	java/lang/Throwable
    //   73	75	75	java/lang/Throwable
    //   36	40	78	java/lang/Throwable
    //   46	50	82	java/lang/Throwable
    //   61	65	85	java/lang/Throwable
    //   69	73	89	java/lang/Throwable
    //   27	32	93	finally
  }
  
  private static String a(Properties paramProperties)
  {
    paramProperties = paramProperties.getProperty("ro.yunos.version");
    if (!TextUtils.isEmpty(paramProperties)) {
      return paramProperties;
    }
    return null;
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      Object localObject2 = Integer.toHexString(paramArrayOfByte[i]);
      int j = ((String)localObject2).length();
      Object localObject1 = localObject2;
      if (j == 1) {
        localObject1 = "0" + (String)localObject2;
      }
      localObject2 = localObject1;
      if (j > 2) {
        localObject2 = ((String)localObject1).substring(j - 2, j);
      }
      localStringBuilder.append(((String)localObject2).toUpperCase(Locale.getDefault()));
      if (i < paramArrayOfByte.length - 1) {
        localStringBuilder.append(':');
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool = false;
    if (Build.VERSION.SDK_INT >= 23) {}
    try
    {
      int i = ((Integer)Class.forName("android.content.Context").getMethod("checkSelfPermission", new Class[] { String.class }).invoke(paramContext, new Object[] { paramString })).intValue();
      if (i == 0) {
        bool = true;
      }
      do
      {
        for (;;)
        {
          return bool;
          bool = false;
        }
      } while (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) != 0);
      return true;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  public static String[] a(GL10 paramGL10)
  {
    for (;;)
    {
      try
      {
        str = paramGL10.glGetString(7936);
        paramGL10 = paramGL10.glGetString(7937);
      }
      catch (Throwable paramGL10)
      {
        String str;
        return new String[0];
      }
      return new String[] { str, paramGL10 };
    }
  }
  
  private static String b()
  {
    Object localObject1;
    Object localObject2;
    int j;
    int i;
    byte b1;
    try
    {
      localObject1 = NetworkInterface.getNetworkInterfaces();
      do
      {
        if (!((Enumeration)localObject1).hasMoreElements()) {
          break;
        }
        localObject2 = (NetworkInterface)((Enumeration)localObject1).nextElement();
      } while ((!"wlan0".equals(((NetworkInterface)localObject2).getName())) && (!"eth0".equals(((NetworkInterface)localObject2).getName())));
      localObject1 = ((NetworkInterface)localObject2).getHardwareAddress();
      if ((localObject1 == null) || (localObject1.length == 0)) {
        break label151;
      }
      localObject2 = new StringBuilder();
      j = localObject1.length;
      i = 0;
    }
    catch (Throwable localThrowable) {}
    ((StringBuilder)localObject2).append(String.format("%02X:", new Object[] { Byte.valueOf(b1) }));
    label151:
    label170:
    for (;;)
    {
      if (((StringBuilder)localObject2).length() > 0) {
        ((StringBuilder)localObject2).deleteCharAt(((StringBuilder)localObject2).length() - 1);
      }
      localObject1 = ((StringBuilder)localObject2).toString().toLowerCase(Locale.getDefault());
      return (String)localObject1;
      return null;
      return null;
      for (;;)
      {
        if (i >= j) {
          break label170;
        }
        b1 = localThrowable[i];
        break;
        i += 1;
      }
    }
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "";
  }
  
  private static String b(Properties paramProperties)
  {
    try
    {
      paramProperties = paramProperties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
      if (paramProperties.contains("flyme os"))
      {
        paramProperties = paramProperties.split(" ")[2];
        return paramProperties;
      }
    }
    catch (Throwable paramProperties) {}
    return null;
  }
  
  private static String c()
  {
    try
    {
      String[] arrayOfString = new String[3];
      arrayOfString[0] = "/sys/class/net/wlan0/address";
      arrayOfString[1] = "/sys/class/net/eth0/address";
      arrayOfString[2] = "/sys/devices/virtual/net/wlan0/address";
      int i = 0;
      for (;;)
      {
        int j = arrayOfString.length;
        if (i >= j) {
          break;
        }
        try
        {
          String str = a(arrayOfString[i]);
          if (str != null) {
            return str;
          }
        }
        catch (Throwable localThrowable2)
        {
          i += 1;
        }
      }
      return null;
    }
    catch (Throwable localThrowable1) {}
  }
  
  public static String c(Context paramContext)
  {
    if ((MobclickAgent.EScenarioType.E_UM_ANALYTICS_OEM.toValue() == AnalyticsConfig.getVerticalType(paramContext)) || (MobclickAgent.EScenarioType.E_UM_GAME_OEM.toValue() == AnalyticsConfig.getVerticalType(paramContext))) {
      return E(paramContext);
    }
    return D(paramContext);
  }
  
  private static String d()
  {
    String str = "";
    if (Build.VERSION.SDK_INT >= 9) {
      str = Build.SERIAL;
    }
    return str;
  }
  
  public static String d(Context paramContext)
  {
    return bw.b(c(paramContext));
  }
  
  public static String e(Context paramContext)
  {
    if (f(paramContext) == null) {}
    int i;
    int j;
    do
    {
      return null;
      i = paramContext.getResources().getConfiguration().mcc;
      j = paramContext.getResources().getConfiguration().mnc;
    } while (i == 0);
    paramContext = String.valueOf(j);
    if (j < 10) {
      paramContext = String.format("%02d", new Object[] { Integer.valueOf(j) });
    }
    return String.valueOf(i) + paramContext;
  }
  
  /* Error */
  private static Properties e()
  {
    // Byte code:
    //   0: new 237	java/util/Properties
    //   3: dup
    //   4: invokespecial 437	java/util/Properties:<init>	()V
    //   7: astore_2
    //   8: aconst_null
    //   9: astore_1
    //   10: new 439	java/io/FileInputStream
    //   13: dup
    //   14: new 441	java/io/File
    //   17: dup
    //   18: invokestatic 447	android/os/Environment:getRootDirectory	()Ljava/io/File;
    //   21: ldc_w 449
    //   24: invokespecial 452	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   27: invokespecial 455	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   30: astore_0
    //   31: aload_2
    //   32: aload_0
    //   33: invokevirtual 459	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   36: aload_0
    //   37: ifnull +7 -> 44
    //   40: aload_0
    //   41: invokevirtual 460	java/io/FileInputStream:close	()V
    //   44: aload_2
    //   45: areturn
    //   46: astore_0
    //   47: aconst_null
    //   48: astore_0
    //   49: aload_0
    //   50: ifnull -6 -> 44
    //   53: aload_0
    //   54: invokevirtual 460	java/io/FileInputStream:close	()V
    //   57: aload_2
    //   58: areturn
    //   59: astore_0
    //   60: aload_2
    //   61: areturn
    //   62: astore_0
    //   63: aload_1
    //   64: ifnull +7 -> 71
    //   67: aload_1
    //   68: invokevirtual 460	java/io/FileInputStream:close	()V
    //   71: aload_0
    //   72: athrow
    //   73: astore_0
    //   74: aload_2
    //   75: areturn
    //   76: astore_1
    //   77: goto -6 -> 71
    //   80: astore_2
    //   81: aload_0
    //   82: astore_1
    //   83: aload_2
    //   84: astore_0
    //   85: goto -22 -> 63
    //   88: astore_1
    //   89: goto -40 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   30	11	0	localFileInputStream	java.io.FileInputStream
    //   46	1	0	localThrowable1	Throwable
    //   48	6	0	localObject1	Object
    //   59	1	0	localThrowable2	Throwable
    //   62	10	0	localObject2	Object
    //   73	9	0	localThrowable3	Throwable
    //   84	1	0	localObject3	Object
    //   9	59	1	localObject4	Object
    //   76	1	1	localThrowable4	Throwable
    //   82	1	1	localObject5	Object
    //   88	1	1	localThrowable5	Throwable
    //   7	68	2	localProperties	Properties
    //   80	4	2	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   10	31	46	java/lang/Throwable
    //   53	57	59	java/lang/Throwable
    //   10	31	62	finally
    //   40	44	73	java/lang/Throwable
    //   67	71	76	java/lang/Throwable
    //   31	36	80	finally
    //   31	36	88	java/lang/Throwable
  }
  
  public static String f(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    if (a(paramContext, "android.permission.READ_PHONE_STATE")) {
      return localTelephonyManager.getSubscriberId();
    }
    return null;
  }
  
  private static boolean f()
  {
    try
    {
      Build.class.getMethod("hasSmartBar", new Class[0]);
      return true;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  public static String g(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    if (a(paramContext, "android.permission.READ_PHONE_STATE")) {
      return localTelephonyManager.getNetworkOperator();
    }
    return null;
  }
  
  public static String h(Context paramContext)
  {
    for (;;)
    {
      TelephonyManager localTelephonyManager;
      try
      {
        localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        if (!a(paramContext, "android.permission.READ_PHONE_STATE")) {
          break label30;
        }
      }
      catch (Throwable paramContext) {}
      paramContext = localTelephonyManager.getNetworkOperatorName();
      return paramContext;
      label30:
      do
      {
        return "";
      } while (localTelephonyManager == null);
    }
  }
  
  public static String i(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.widthPixels;
      int j = localDisplayMetrics.heightPixels;
      paramContext = String.valueOf(j) + "*" + String.valueOf(i);
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static String[] j(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "";
    arrayOfString[1] = "";
    try
    {
      if (a(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {
        break label105;
      }
    }
    catch (Throwable paramContext) {}
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    break label108;
    label42:
    NetworkInfo localNetworkInfo = paramContext.getNetworkInfo(1);
    break label119;
    label51:
    if (localNetworkInfo.getState() != NetworkInfo.State.CONNECTED)
    {
      label64:
      paramContext = paramContext.getNetworkInfo(0);
      break label133;
      label73:
      if (paramContext.getState() == NetworkInfo.State.CONNECTED) {
        break label140;
      }
    }
    for (;;)
    {
      arrayOfString[1] = paramContext.getSubtypeName();
      return arrayOfString;
      label105:
      label108:
      label119:
      label133:
      do
      {
        return arrayOfString;
        arrayOfString[0] = "";
        return arrayOfString;
        break;
        if (paramContext != null) {
          break label42;
        }
        arrayOfString[0] = "";
        return arrayOfString;
        if (localNetworkInfo == null) {
          break label64;
        }
        break label51;
        arrayOfString[0] = "Wi-Fi";
        return arrayOfString;
      } while (paramContext == null);
      break label73;
      label140:
      arrayOfString[0] = "2G/3G";
    }
  }
  
  public static boolean k(Context paramContext)
  {
    return "Wi-Fi".equals(j(paramContext)[0]);
  }
  
  public static boolean l(Context paramContext)
  {
    try
    {
      if (!a(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {
        break label40;
      }
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    }
    catch (Throwable paramContext) {}
    paramContext = paramContext.getActiveNetworkInfo();
    for (;;)
    {
      boolean bool = paramContext.isConnectedOrConnecting();
      return bool;
      label40:
      do
      {
        do
        {
          return false;
        } while (paramContext == null);
        break;
      } while (paramContext == null);
    }
  }
  
  public static int m(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = Calendar.getInstance(B(paramContext));
      }
      catch (Throwable paramContext)
      {
        int i;
        by.c(a, "error in getTimeZone", paramContext);
        return 8;
      }
      i = paramContext.getTimeZone().getRawOffset() / 3600000;
      return i;
      if (paramContext == null) {}
    }
  }
  
  public static boolean n(Context paramContext)
  {
    String str = af.a(paramContext).b().e("");
    if (!TextUtils.isEmpty(str)) {
      if (!str.equals("cn")) {}
    }
    do
    {
      return true;
      return false;
      if (f(paramContext) != null) {
        break;
      }
      paramContext = o(paramContext)[0];
    } while ((!TextUtils.isEmpty(paramContext)) && (paramContext.equalsIgnoreCase("cn")));
    do
    {
      int i;
      do
      {
        return false;
        i = paramContext.getResources().getConfiguration().mcc;
        if ((i == 460) || (i == 461)) {
          break;
        }
      } while (i != 0);
      paramContext = o(paramContext)[0];
    } while ((TextUtils.isEmpty(paramContext)) || (!paramContext.equalsIgnoreCase("cn")));
    return true;
  }
  
  public static String[] o(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    try
    {
      paramContext = B(paramContext);
    }
    catch (Throwable paramContext)
    {
      by.e(a, "error in getLocaleInfo", paramContext);
      return arrayOfString;
    }
    arrayOfString[0] = paramContext.getCountry();
    arrayOfString[1] = paramContext.getLanguage();
    label27:
    if (!TextUtils.isEmpty(arrayOfString[0])) {}
    for (;;)
    {
      if (TextUtils.isEmpty(arrayOfString[1])) {
        arrayOfString[1] = "Unknown";
      }
      return arrayOfString;
      if (paramContext == null) {
        break label27;
      }
      break;
      arrayOfString[0] = "Unknown";
    }
  }
  
  public static String p(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        by.e(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", paramContext);
        continue;
        if (paramContext != null)
        {
          continue;
          label76:
          if (paramContext == null) {}
        }
      }
    }
    paramContext = paramContext.metaData.getString("UMENG_APPKEY");
    break label76;
    return paramContext.trim();
    by.c(a, new Object[] { "getAppkey failed. the applicationinfo is null!" });
    return null;
  }
  
  public static String q(Context paramContext)
  {
    Object localObject;
    if (Build.VERSION.SDK_INT < 23) {
      localObject = C(paramContext);
    }
    String str;
    do
    {
      do
      {
        return (String)localObject;
        if (Build.VERSION.SDK_INT != 23) {
          break;
        }
        str = b();
        localObject = str;
      } while (!TextUtils.isEmpty(str));
      if (a.d) {
        return c();
      }
      return C(paramContext);
      str = b();
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return C(paramContext);
  }
  
  public static int[] r(Context paramContext)
  {
    try
    {
      localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      if ((paramContext.getApplicationInfo().flags & 0x2000) != 0) {
        break label104;
      }
      j = a(localDisplayMetrics, "noncompatWidthPixels");
      i = a(localDisplayMetrics, "noncompatHeightPixels");
    }
    catch (Throwable paramContext)
    {
      DisplayMetrics localDisplayMetrics;
      label83:
      return null;
    }
    int i = localDisplayMetrics.widthPixels;
    int j = localDisplayMetrics.heightPixels;
    int k = i;
    i = j;
    paramContext = new int[2];
    for (;;)
    {
      paramContext[1] = i;
      return paramContext;
      label104:
      do
      {
        k = j;
        break label83;
        i = -1;
        j = -1;
        if (j == -1) {
          break;
        }
      } while (i != -1);
      break;
      if (k > i)
      {
        paramContext[0] = i;
        paramContext[1] = k;
        return paramContext;
      }
      paramContext[0] = k;
    }
  }
  
  public static String s(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
        if ((paramContext == null) || (paramContext.metaData == null)) {
          break label56;
        }
        paramContext = paramContext.metaData.get("UMENG_CHANNEL");
      }
      catch (Throwable paramContext)
      {
        return "Unknown";
      }
      paramContext = paramContext.toString();
      if (paramContext != null) {
        return paramContext;
      }
      label56:
      do
      {
        return "Unknown";
      } while (paramContext == null);
    }
  }
  
  public static String t(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static String u(Context paramContext)
  {
    for (;;)
    {
      try
      {
        localPackageManager = paramContext.getPackageManager();
      }
      catch (Throwable paramContext)
      {
        PackageManager localPackageManager;
        return null;
      }
      paramContext = new ByteArrayInputStream(localPackageManager.getPackageInfo(t(paramContext), 64).signatures[0].toByteArray());
      paramContext = (X509Certificate)CertificateFactory.getInstance("X509").generateCertificate(paramContext);
      paramContext = a(MessageDigest.getInstance("MD5").digest(paramContext.getEncoded()));
      return paramContext;
    }
  }
  
  public static String v(Context paramContext)
  {
    return paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo()).toString();
  }
  
  public static String w(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static String x(Context paramContext)
  {
    Properties localProperties = e();
    try
    {
      paramContext = localProperties.getProperty("ro.miui.ui.version.name");
      if (TextUtils.isEmpty(paramContext))
      {
        if (!f())
        {
          if (TextUtils.isEmpty(a(localProperties))) {
            break label51;
          }
          break label53;
        }
      }
      else {
        return "MIUI";
      }
    }
    catch (Throwable paramContext)
    {
      return null;
    }
    paramContext = "Flyme";
    label51:
    return paramContext;
    label53:
    return "YunOS";
  }
  
  public static String y(Context paramContext)
  {
    Properties localProperties = e();
    try
    {
      String str = localProperties.getProperty("ro.miui.ui.version.name");
      paramContext = str;
      if (TextUtils.isEmpty(str))
      {
        boolean bool = f();
        if (!bool) {
          break label35;
        }
      }
      label35:
      return str;
    }
    catch (Throwable paramContext)
    {
      try
      {
        paramContext = b(localProperties);
        return paramContext;
      }
      catch (Throwable paramContext)
      {
        return str;
      }
      try
      {
        paramContext = a(localProperties);
        return paramContext;
      }
      catch (Throwable paramContext) {}
      paramContext = paramContext;
      return null;
    }
  }
  
  public static String z(Context paramContext)
  {
    if (paramContext == null) {
      return "Phone";
    }
    if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3) {}
    for (int i = 1; i != 0; i = 0) {
      return "Tablet";
    }
    return "Phone";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\bv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */