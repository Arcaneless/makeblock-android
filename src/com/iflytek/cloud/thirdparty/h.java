package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;

public final class h
{
  public static String a()
  {
    return a(Build.MODEL);
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      return a(paramContext);
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = "";
      }
    }
  }
  
  public static String a(Context paramContext, String paramString)
  {
    try
    {
      int i = new Random().nextInt(10);
      paramContext = String.valueOf(b(System.currentTimeMillis() + paramString + a(paramContext) + g(paramContext) + i).toCharArray(), 8, 16);
      return c(paramContext);
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = "";
      }
    }
  }
  
  protected static String a(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    String str = paramString;
    if (paramString.length() > 901) {
      str = paramString.substring(0, 900);
    }
    return str.replace("\\", "").replace("|", "");
  }
  
  protected static boolean a(String paramString, int paramInt)
  {
    return (paramString != null) && (paramString.length() > paramInt);
  }
  
  public static String b()
  {
    return a(Build.MANUFACTURER);
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      String str = a(paramContext);
      if ((str != null) && (str.length() > 6)) {
        return String.valueOf(b(str).toCharArray(), 7, 18);
      }
      str = g(paramContext);
      if ((str == null) || (str.length() < 9))
      {
        paramContext = c(paramContext);
        if ((paramContext == null) || (paramContext.length() == 0)) {
          return String.valueOf(b(a(Build.MODEL)).toCharArray(), 7, 18);
        }
        return String.valueOf(b(paramContext).toCharArray(), 7, 18);
      }
      paramContext = String.valueOf(b(str).toCharArray(), 7, 18);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  private static String b(String paramString)
  {
    int j = 0;
    for (;;)
    {
      int i;
      try
      {
        Object localObject = MessageDigest.getInstance("MD5");
        paramString = paramString.toCharArray();
        byte[] arrayOfByte = new byte[paramString.length];
        i = 0;
        if (i >= paramString.length)
        {
          paramString = ((MessageDigest)localObject).digest(arrayOfByte);
          localObject = new StringBuffer();
          i = j;
          if (i >= paramString.length) {
            return ((StringBuffer)localObject).toString();
          }
          j = paramString[i] & 0xFF;
          if (j < 16) {
            ((StringBuffer)localObject).append("0");
          }
          ((StringBuffer)localObject).append(Integer.toHexString(j));
          i += 1;
          continue;
        }
        arrayOfByte[i] = ((byte)paramString[i]);
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return "";
      }
      i += 1;
    }
  }
  
  private static boolean b(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName());
      if (i == 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static String c()
  {
    return a("android " + Build.VERSION.RELEASE);
  }
  
  public static String c(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null) {
        break label60;
      }
      paramContext = paramContext.getSubscriberId();
      if (paramContext == null) {
        break label54;
      }
      paramContext = paramContext.trim();
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = "";
        Log.i("MobileUtils", "can't not read imsi");
        continue;
        label54:
        paramContext = "";
        continue;
        label60:
        paramContext = "";
      }
    }
    c.a("MobileUtils", paramContext);
    return c(paramContext);
  }
  
  private static String c(String paramString)
  {
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = "";
    }
    return str;
  }
  
  public static int d(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.widthPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static String d()
  {
    try
    {
      String str1 = Locale.getDefault().getCountry();
      return c(str1);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        String str2 = "";
      }
    }
  }
  
  public static int e(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.heightPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static String e()
  {
    try
    {
      String str1 = Locale.getDefault().getLanguage();
      return c(str1);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        String str2 = "";
      }
    }
  }
  
  public static int f(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.densityDpi;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  /* Error */
  public static String f()
  {
    // Byte code:
    //   0: ldc 34
    //   2: astore_3
    //   3: aconst_null
    //   4: astore_2
    //   5: aconst_null
    //   6: astore_1
    //   7: new 234	java/lang/ProcessBuilder
    //   10: dup
    //   11: iconst_2
    //   12: anewarray 55	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc -20
    //   19: aastore
    //   20: dup
    //   21: iconst_1
    //   22: ldc -18
    //   24: aastore
    //   25: invokespecial 241	java/lang/ProcessBuilder:<init>	([Ljava/lang/String;)V
    //   28: invokevirtual 245	java/lang/ProcessBuilder:start	()Ljava/lang/Process;
    //   31: invokevirtual 251	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   34: astore 4
    //   36: aload 4
    //   38: astore_1
    //   39: aload 4
    //   41: astore_2
    //   42: bipush 24
    //   44: newarray <illegal type>
    //   46: astore 5
    //   48: aload 4
    //   50: astore_1
    //   51: aload 4
    //   53: astore_2
    //   54: aload 4
    //   56: aload 5
    //   58: invokevirtual 257	java/io/InputStream:read	([B)I
    //   61: istore_0
    //   62: iload_0
    //   63: iconst_m1
    //   64: if_icmpne +13 -> 77
    //   67: aload 4
    //   69: invokevirtual 260	java/io/InputStream:close	()V
    //   72: aload_3
    //   73: invokevirtual 169	java/lang/String:trim	()Ljava/lang/String;
    //   76: areturn
    //   77: aload 4
    //   79: astore_1
    //   80: aload 4
    //   82: astore_2
    //   83: new 47	java/lang/StringBuilder
    //   86: dup
    //   87: aload_3
    //   88: invokestatic 263	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   91: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   94: new 55	java/lang/String
    //   97: dup
    //   98: aload 5
    //   100: invokespecial 266	java/lang/String:<init>	([B)V
    //   103: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   109: astore_3
    //   110: goto -62 -> 48
    //   113: astore_3
    //   114: aload_1
    //   115: astore_2
    //   116: aload_3
    //   117: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   120: ldc 34
    //   122: astore_3
    //   123: aload_1
    //   124: invokevirtual 260	java/io/InputStream:close	()V
    //   127: goto -55 -> 72
    //   130: astore_1
    //   131: aload_1
    //   132: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   135: goto -63 -> 72
    //   138: astore_1
    //   139: aload_2
    //   140: invokevirtual 260	java/io/InputStream:close	()V
    //   143: aload_1
    //   144: athrow
    //   145: astore_2
    //   146: aload_2
    //   147: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   150: goto -7 -> 143
    //   153: astore_1
    //   154: aload_1
    //   155: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   158: goto -86 -> 72
    // Local variable table:
    //   start	length	slot	name	signature
    //   61	4	0	i	int
    //   6	118	1	localObject1	Object
    //   130	2	1	localException1	Exception
    //   138	6	1	localObject2	Object
    //   153	2	1	localException2	Exception
    //   4	136	2	localObject3	Object
    //   145	2	2	localException3	Exception
    //   2	108	3	str1	String
    //   113	4	3	localException4	Exception
    //   122	1	3	str2	String
    //   34	47	4	localInputStream	java.io.InputStream
    //   46	53	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   7	36	113	java/lang/Exception
    //   42	48	113	java/lang/Exception
    //   54	62	113	java/lang/Exception
    //   83	110	113	java/lang/Exception
    //   123	127	130	java/lang/Exception
    //   7	36	138	finally
    //   42	48	138	finally
    //   54	62	138	finally
    //   83	110	138	finally
    //   116	120	138	finally
    //   139	143	145	java/lang/Exception
    //   67	72	153	java/lang/Exception
  }
  
  /* Error */
  public static String g()
  {
    // Byte code:
    //   0: new 268	java/io/FileReader
    //   3: dup
    //   4: ldc_w 270
    //   7: invokespecial 271	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   10: astore_1
    //   11: aload_1
    //   12: astore_0
    //   13: new 273	java/io/BufferedReader
    //   16: dup
    //   17: aload_1
    //   18: invokespecial 276	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   21: invokevirtual 279	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   24: invokevirtual 169	java/lang/String:trim	()Ljava/lang/String;
    //   27: astore_2
    //   28: aload_1
    //   29: invokevirtual 280	java/io/FileReader:close	()V
    //   32: aload_2
    //   33: areturn
    //   34: astore_0
    //   35: aload_0
    //   36: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   39: aload_2
    //   40: areturn
    //   41: astore_2
    //   42: aconst_null
    //   43: astore_1
    //   44: aload_1
    //   45: astore_0
    //   46: aload_2
    //   47: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   50: aload_1
    //   51: invokevirtual 280	java/io/FileReader:close	()V
    //   54: ldc 34
    //   56: areturn
    //   57: astore_0
    //   58: aload_0
    //   59: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   62: goto -8 -> 54
    //   65: astore_1
    //   66: aconst_null
    //   67: astore_0
    //   68: aload_0
    //   69: invokevirtual 280	java/io/FileReader:close	()V
    //   72: aload_1
    //   73: athrow
    //   74: astore_0
    //   75: aload_0
    //   76: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   79: goto -7 -> 72
    //   82: astore_1
    //   83: goto -15 -> 68
    //   86: astore_2
    //   87: goto -43 -> 44
    // Local variable table:
    //   start	length	slot	name	signature
    //   12	1	0	localFileReader1	java.io.FileReader
    //   34	2	0	localException1	Exception
    //   45	1	0	localFileReader2	java.io.FileReader
    //   57	2	0	localException2	Exception
    //   67	2	0	localObject1	Object
    //   74	2	0	localException3	Exception
    //   10	41	1	localFileReader3	java.io.FileReader
    //   65	8	1	localObject2	Object
    //   82	1	1	localObject3	Object
    //   27	13	2	str	String
    //   41	6	2	localException4	Exception
    //   86	1	2	localException5	Exception
    // Exception table:
    //   from	to	target	type
    //   28	32	34	java/lang/Exception
    //   0	11	41	java/lang/Exception
    //   50	54	57	java/lang/Exception
    //   0	11	65	finally
    //   68	72	74	java/lang/Exception
    //   13	28	82	finally
    //   46	50	82	finally
    //   13	28	86	java/lang/Exception
  }
  
  public static String g(Context paramContext)
  {
    try
    {
      if (b(paramContext, "android.permission.ACCESS_WIFI_STATE"))
      {
        String str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        if (str != null)
        {
          paramContext = str;
          if (!str.equals("")) {
            break label46;
          }
        }
      }
      for (paramContext = "unknown";; paramContext = "unknown")
      {
        label46:
        return c(paramContext);
        Log.w("MobileUtils", "Could not read MAC, forget to include ACCESS_WIFI_STATE permission?");
      }
      return "unknown";
    }
    catch (Exception paramContext)
    {
      Log.w("MobileUtils", "Could not read MAC, forget to include ACCESS_WIFI_STATE permission?", paramContext);
    }
  }
  
  /* Error */
  public static String h()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 268	java/io/FileReader
    //   5: dup
    //   6: ldc_w 314
    //   9: invokespecial 271	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   12: astore_2
    //   13: new 273	java/io/BufferedReader
    //   16: dup
    //   17: aload_2
    //   18: sipush 8192
    //   21: invokespecial 317	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   24: astore_0
    //   25: aload_0
    //   26: invokevirtual 279	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   29: astore_1
    //   30: aload_1
    //   31: ifnull +106 -> 137
    //   34: aload_1
    //   35: aload_1
    //   36: ldc_w 319
    //   39: invokevirtual 323	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   42: iconst_2
    //   43: iadd
    //   44: aload_1
    //   45: ldc_w 325
    //   48: invokevirtual 323	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   51: iconst_1
    //   52: isub
    //   53: invokevirtual 98	java/lang/String:substring	(II)Ljava/lang/String;
    //   56: invokevirtual 169	java/lang/String:trim	()Ljava/lang/String;
    //   59: astore_1
    //   60: ldc -85
    //   62: aload_1
    //   63: invokestatic 176	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   66: aload_1
    //   67: invokestatic 90	com/iflytek/cloud/thirdparty/h:c	(Ljava/lang/String;)Ljava/lang/String;
    //   70: astore_1
    //   71: aload_2
    //   72: invokevirtual 280	java/io/FileReader:close	()V
    //   75: aload_0
    //   76: invokevirtual 326	java/io/BufferedReader:close	()V
    //   79: aload_1
    //   80: areturn
    //   81: astore_0
    //   82: aload_0
    //   83: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   86: aload_1
    //   87: areturn
    //   88: astore_2
    //   89: aconst_null
    //   90: astore_0
    //   91: aload_2
    //   92: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   95: aload_1
    //   96: invokevirtual 280	java/io/FileReader:close	()V
    //   99: aload_0
    //   100: invokevirtual 326	java/io/BufferedReader:close	()V
    //   103: ldc 34
    //   105: areturn
    //   106: astore_0
    //   107: aload_0
    //   108: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   111: goto -8 -> 103
    //   114: astore_1
    //   115: aconst_null
    //   116: astore_0
    //   117: aconst_null
    //   118: astore_2
    //   119: aload_2
    //   120: invokevirtual 280	java/io/FileReader:close	()V
    //   123: aload_0
    //   124: invokevirtual 326	java/io/BufferedReader:close	()V
    //   127: aload_1
    //   128: athrow
    //   129: astore_0
    //   130: aload_0
    //   131: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   134: goto -7 -> 127
    //   137: aload_2
    //   138: invokevirtual 280	java/io/FileReader:close	()V
    //   141: aload_0
    //   142: invokevirtual 326	java/io/BufferedReader:close	()V
    //   145: goto -42 -> 103
    //   148: astore_0
    //   149: aload_0
    //   150: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   153: goto -50 -> 103
    //   156: astore_1
    //   157: aconst_null
    //   158: astore_0
    //   159: goto -40 -> 119
    //   162: astore_1
    //   163: goto -44 -> 119
    //   166: astore_3
    //   167: aload_1
    //   168: astore_2
    //   169: aload_3
    //   170: astore_1
    //   171: goto -52 -> 119
    //   174: astore_3
    //   175: aconst_null
    //   176: astore_0
    //   177: aload_2
    //   178: astore_1
    //   179: aload_3
    //   180: astore_2
    //   181: goto -90 -> 91
    //   184: astore_3
    //   185: aload_2
    //   186: astore_1
    //   187: aload_3
    //   188: astore_2
    //   189: goto -98 -> 91
    // Local variable table:
    //   start	length	slot	name	signature
    //   24	52	0	localBufferedReader	java.io.BufferedReader
    //   81	2	0	localException1	Exception
    //   90	10	0	localObject1	Object
    //   106	2	0	localException2	Exception
    //   116	8	0	localObject2	Object
    //   129	13	0	localException3	Exception
    //   148	2	0	localException4	Exception
    //   158	19	0	localObject3	Object
    //   1	95	1	str	String
    //   114	14	1	localObject4	Object
    //   156	1	1	localObject5	Object
    //   162	6	1	localObject6	Object
    //   170	17	1	localObject7	Object
    //   12	60	2	localFileReader	java.io.FileReader
    //   88	4	2	localException5	Exception
    //   118	71	2	localObject8	Object
    //   166	4	3	localObject9	Object
    //   174	6	3	localException6	Exception
    //   184	4	3	localException7	Exception
    // Exception table:
    //   from	to	target	type
    //   71	79	81	java/lang/Exception
    //   2	13	88	java/lang/Exception
    //   95	103	106	java/lang/Exception
    //   2	13	114	finally
    //   119	127	129	java/lang/Exception
    //   137	145	148	java/lang/Exception
    //   13	25	156	finally
    //   25	30	162	finally
    //   34	71	162	finally
    //   91	95	166	finally
    //   13	25	174	java/lang/Exception
    //   25	30	184	java/lang/Exception
    //   34	71	184	java/lang/Exception
  }
  
  public static String h(Context paramContext)
  {
    try
    {
      String str = paramContext.getSharedPreferences("lxdMoblieAgent_sys_config", 0).getString("MOBILE_APPKEY", "");
      Object localObject = str;
      if (str.equals(""))
      {
        localObject = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
        paramContext = str;
        if (localObject != null) {
          paramContext = ((ApplicationInfo)localObject).metaData.getString("MOBILE_APPKEY");
        }
        if (TextUtils.isEmpty(paramContext)) {
          Log.e("MobileUtils", "the appkey is empty,please init datau.sdk");
        }
        localObject = paramContext;
        if (TextUtils.isEmpty(paramContext)) {
          localObject = "00000";
        }
      }
      return (String)localObject;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "00000";
  }
  
  public static String i(Context paramContext)
  {
    try
    {
      String str = paramContext.getSharedPreferences("lxdMoblieAgent_sys_config", 0).getString("MOBILE_CHANNEL", "");
      if (str.equals(""))
      {
        ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
        paramContext = str;
        if (localApplicationInfo != null)
        {
          str = localApplicationInfo.metaData.getString("MOBILE_CHANNEL");
          paramContext = str;
          if (str == null)
          {
            Log.w("MobileUtils", "Could not read MOBILE_CHANNEL meta-data from AndroidManifest.xml.");
            paramContext = "";
          }
        }
        return a(paramContext);
      }
      paramContext = a(str);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String j(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageName();
      return c(paramContext);
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = "";
      }
    }
  }
  
  public static String k(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
      return c(paramContext);
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = "";
      }
    }
  }
  
  public static String l(Context paramContext)
  {
    try
    {
      if (b(paramContext, "android.permission.ACCESS_NETWORK_STATE"))
      {
        paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (paramContext == null) {
          return "unknown";
        }
        int i = paramContext.getType();
        if (i == 1) {
          return "wifi";
        }
        paramContext = paramContext.getExtraInfo();
        if (paramContext == null) {
          return "unknown";
        }
        c.a("MobileUtils", "net type:" + paramContext);
        paramContext = paramContext.trim();
        return paramContext;
      }
      return "unknown";
    }
    catch (Exception paramContext)
    {
      Log.w("MobileUtils", "Could not read ACCESSPOINT, forget to include ACCESS_NETSTATE_STATE permission?", paramContext);
    }
    return "unknown";
  }
  
  public static int m(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }
  
  public static String n(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return c(paramContext);
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = "";
      }
    }
  }
  
  /* Error */
  public static long[] o(Context paramContext)
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore 8
    //   3: aconst_null
    //   4: astore 12
    //   6: aconst_null
    //   7: astore 11
    //   9: aload_0
    //   10: invokestatic 410	com/iflytek/cloud/thirdparty/h:q	(Landroid/content/Context;)I
    //   13: istore_1
    //   14: new 47	java/lang/StringBuilder
    //   17: dup
    //   18: ldc_w 412
    //   21: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   24: iload_1
    //   25: invokevirtual 74	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   28: ldc_w 414
    //   31: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: astore_0
    //   38: new 47	java/lang/StringBuilder
    //   41: dup
    //   42: ldc_w 412
    //   45: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   48: iload_1
    //   49: invokevirtual 74	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   52: ldc_w 416
    //   55: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: astore 13
    //   63: new 268	java/io/FileReader
    //   66: dup
    //   67: aload_0
    //   68: invokespecial 271	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   71: astore 10
    //   73: new 273	java/io/BufferedReader
    //   76: dup
    //   77: aload 10
    //   79: sipush 500
    //   82: invokespecial 317	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   85: astore_0
    //   86: aload_0
    //   87: invokevirtual 279	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   90: invokevirtual 417	java/lang/String:toString	()Ljava/lang/String;
    //   93: invokevirtual 169	java/lang/String:trim	()Ljava/lang/String;
    //   96: invokestatic 423	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   99: lstore_2
    //   100: aload 10
    //   102: invokevirtual 280	java/io/FileReader:close	()V
    //   105: aload_0
    //   106: invokevirtual 326	java/io/BufferedReader:close	()V
    //   109: aconst_null
    //   110: astore_0
    //   111: aload 11
    //   113: astore 10
    //   115: new 268	java/io/FileReader
    //   118: dup
    //   119: aload 13
    //   121: invokespecial 271	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   124: astore 11
    //   126: new 273	java/io/BufferedReader
    //   129: dup
    //   130: aload 11
    //   132: sipush 500
    //   135: invokespecial 317	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   138: astore 10
    //   140: aload 10
    //   142: invokevirtual 279	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   145: invokevirtual 417	java/lang/String:toString	()Ljava/lang/String;
    //   148: invokevirtual 169	java/lang/String:trim	()Ljava/lang/String;
    //   151: invokestatic 423	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   154: lstore 4
    //   156: aload 11
    //   158: invokevirtual 280	java/io/FileReader:close	()V
    //   161: aload 10
    //   163: invokevirtual 326	java/io/BufferedReader:close	()V
    //   166: ldc -85
    //   168: new 47	java/lang/StringBuilder
    //   171: dup
    //   172: ldc_w 425
    //   175: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   178: lload_2
    //   179: invokevirtual 428	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   182: ldc_w 430
    //   185: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: lload 4
    //   190: invokevirtual 428	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   193: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: invokestatic 176	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   199: lload_2
    //   200: lstore 6
    //   202: lload_2
    //   203: lconst_0
    //   204: lcmp
    //   205: ifge +6 -> 211
    //   208: lconst_0
    //   209: lstore 6
    //   211: lload 4
    //   213: lconst_0
    //   214: lcmp
    //   215: ifge +187 -> 402
    //   218: lload 8
    //   220: lstore_2
    //   221: iconst_2
    //   222: newarray <illegal type>
    //   224: dup
    //   225: iconst_0
    //   226: lload 6
    //   228: lastore
    //   229: dup
    //   230: iconst_1
    //   231: lload_2
    //   232: lastore
    //   233: areturn
    //   234: astore_0
    //   235: aconst_null
    //   236: astore_0
    //   237: aconst_null
    //   238: astore 10
    //   240: aload 10
    //   242: ifnull +245 -> 487
    //   245: aload 10
    //   247: invokevirtual 280	java/io/FileReader:close	()V
    //   250: aload_0
    //   251: invokevirtual 326	java/io/BufferedReader:close	()V
    //   254: lconst_0
    //   255: lstore_2
    //   256: aconst_null
    //   257: astore_0
    //   258: aload 11
    //   260: astore 10
    //   262: goto -147 -> 115
    //   265: astore 11
    //   267: aload 11
    //   269: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   272: lconst_0
    //   273: lstore_2
    //   274: goto -159 -> 115
    //   277: astore_0
    //   278: aconst_null
    //   279: astore 10
    //   281: aload 12
    //   283: astore 11
    //   285: aload 10
    //   287: ifnull +13 -> 300
    //   290: aload 10
    //   292: invokevirtual 280	java/io/FileReader:close	()V
    //   295: aload 11
    //   297: invokevirtual 326	java/io/BufferedReader:close	()V
    //   300: aload_0
    //   301: athrow
    //   302: astore 10
    //   304: aload 10
    //   306: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   309: goto -9 -> 300
    //   312: astore 11
    //   314: aload 11
    //   316: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   319: goto -204 -> 115
    //   322: astore 11
    //   324: aload 10
    //   326: ifnull +155 -> 481
    //   329: aload 10
    //   331: invokevirtual 280	java/io/FileReader:close	()V
    //   334: aload_0
    //   335: invokevirtual 326	java/io/BufferedReader:close	()V
    //   338: lconst_0
    //   339: lstore 4
    //   341: goto -175 -> 166
    //   344: astore_0
    //   345: aload_0
    //   346: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   349: lconst_0
    //   350: lstore 4
    //   352: goto -186 -> 166
    //   355: astore 11
    //   357: aload 10
    //   359: astore 12
    //   361: aload_0
    //   362: astore 10
    //   364: aload 11
    //   366: astore_0
    //   367: aload 12
    //   369: ifnull +13 -> 382
    //   372: aload 12
    //   374: invokevirtual 280	java/io/FileReader:close	()V
    //   377: aload 10
    //   379: invokevirtual 326	java/io/BufferedReader:close	()V
    //   382: aload_0
    //   383: athrow
    //   384: astore 10
    //   386: aload 10
    //   388: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   391: goto -9 -> 382
    //   394: astore_0
    //   395: aload_0
    //   396: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   399: goto -233 -> 166
    //   402: lload 4
    //   404: lstore_2
    //   405: goto -184 -> 221
    //   408: astore 12
    //   410: aload_0
    //   411: astore 10
    //   413: aload 12
    //   415: astore_0
    //   416: aload 11
    //   418: astore 12
    //   420: goto -53 -> 367
    //   423: astore_0
    //   424: aload 11
    //   426: astore 12
    //   428: goto -61 -> 367
    //   431: astore 10
    //   433: aload 11
    //   435: astore 10
    //   437: goto -113 -> 324
    //   440: astore_0
    //   441: aload 10
    //   443: astore_0
    //   444: aload 11
    //   446: astore 10
    //   448: goto -124 -> 324
    //   451: astore_0
    //   452: aload 12
    //   454: astore 11
    //   456: goto -171 -> 285
    //   459: astore 12
    //   461: aload_0
    //   462: astore 11
    //   464: aload 12
    //   466: astore_0
    //   467: goto -182 -> 285
    //   470: astore_0
    //   471: aconst_null
    //   472: astore_0
    //   473: goto -233 -> 240
    //   476: astore 12
    //   478: goto -238 -> 240
    //   481: lconst_0
    //   482: lstore 4
    //   484: goto -318 -> 166
    //   487: lconst_0
    //   488: lstore_2
    //   489: goto -374 -> 115
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	492	0	paramContext	Context
    //   13	36	1	i	int
    //   99	390	2	l1	long
    //   154	329	4	l2	long
    //   200	27	6	l3	long
    //   1	218	8	l4	long
    //   71	220	10	localObject1	Object
    //   302	56	10	localException1	Exception
    //   362	16	10	localContext1	Context
    //   384	3	10	localException2	Exception
    //   411	1	10	localContext2	Context
    //   431	1	10	localException3	Exception
    //   435	12	10	localObject2	Object
    //   7	252	11	localFileReader	java.io.FileReader
    //   265	3	11	localException4	Exception
    //   283	13	11	localObject3	Object
    //   312	3	11	localException5	Exception
    //   322	1	11	localException6	Exception
    //   355	90	11	localObject4	Object
    //   454	9	11	localObject5	Object
    //   4	369	12	localObject6	Object
    //   408	6	12	localObject7	Object
    //   418	35	12	localObject8	Object
    //   459	6	12	localObject9	Object
    //   476	1	12	localException7	Exception
    //   61	59	13	str	String
    // Exception table:
    //   from	to	target	type
    //   63	73	234	java/lang/Exception
    //   245	254	265	java/lang/Exception
    //   63	73	277	finally
    //   290	300	302	java/lang/Exception
    //   100	109	312	java/lang/Exception
    //   115	126	322	java/lang/Exception
    //   329	338	344	java/lang/Exception
    //   115	126	355	finally
    //   372	382	384	java/lang/Exception
    //   156	166	394	java/lang/Exception
    //   126	140	408	finally
    //   140	156	423	finally
    //   126	140	431	java/lang/Exception
    //   140	156	440	java/lang/Exception
    //   73	86	451	finally
    //   86	100	459	finally
    //   73	86	470	java/lang/Exception
    //   86	100	476	java/lang/Exception
  }
  
  public static String p(Context paramContext)
  {
    try
    {
      Object localObject = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localObject != null) && (((NetworkInfo)localObject).isConnected()))
      {
        localObject = ((NetworkInfo)localObject).getTypeName();
        if (((String)localObject).equalsIgnoreCase("WIFI")) {
          return "WIFI";
        }
        if (((String)localObject).equalsIgnoreCase("MOBILE")) {
          return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType();
        }
      }
      else
      {
        return "";
      }
    }
    catch (Exception paramContext)
    {
      return "";
    }
    return "";
  }
  
  private static int q(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 1).uid;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */