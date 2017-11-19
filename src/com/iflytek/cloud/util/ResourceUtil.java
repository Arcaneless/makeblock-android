package com.iflytek.cloud.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;

public class ResourceUtil
{
  public static final String ASR_RES_PATH = "asr_res_path";
  public static final String ENGINE_DESTROY = "engine_destroy";
  public static final String ENGINE_START = "engine_start";
  public static final String GRM_BUILD_PATH = "grm_build_path";
  public static final String IVW_RES_PATH = "ivw_res_path";
  public static final String TTS_RES_PATH = "tts_res_path";
  
  /* Error */
  private static String a(Context paramContext, RESOURCE_TYPE paramRESOURCE_TYPE, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: aconst_null
    //   4: astore 10
    //   6: aload 10
    //   8: astore 8
    //   10: aload_2
    //   11: invokestatic 41	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   14: ifne +11 -> 25
    //   17: aload_0
    //   18: ifnonnull +10 -> 28
    //   21: aload 10
    //   23: astore 8
    //   25: aload 8
    //   27: areturn
    //   28: aload_0
    //   29: invokevirtual 47	android/content/Context:getPackageResourcePath	()Ljava/lang/String;
    //   32: astore 8
    //   34: aload_1
    //   35: getstatic 51	com/iflytek/cloud/util/ResourceUtil$RESOURCE_TYPE:assets	Lcom/iflytek/cloud/util/ResourceUtil$RESOURCE_TYPE;
    //   38: if_acmpne +101 -> 139
    //   41: aload_0
    //   42: invokevirtual 55	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   45: aload_2
    //   46: invokevirtual 61	android/content/res/AssetManager:openFd	(Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;
    //   49: astore_2
    //   50: aload_2
    //   51: astore_0
    //   52: aload_2
    //   53: astore_1
    //   54: aload_2
    //   55: invokevirtual 67	android/content/res/AssetFileDescriptor:getStartOffset	()J
    //   58: lstore 6
    //   60: aload_2
    //   61: astore_0
    //   62: aload_2
    //   63: astore_1
    //   64: aload_2
    //   65: invokevirtual 70	android/content/res/AssetFileDescriptor:getLength	()J
    //   68: lstore 4
    //   70: aload_2
    //   71: astore_0
    //   72: aload_2
    //   73: astore_1
    //   74: new 72	java/lang/StringBuilder
    //   77: dup
    //   78: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   81: ldc 75
    //   83: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: aload 8
    //   88: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: ldc 81
    //   93: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: lload 6
    //   98: invokevirtual 84	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   101: ldc 81
    //   103: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: lload 4
    //   108: invokevirtual 84	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   111: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: astore 8
    //   116: aload 8
    //   118: astore_0
    //   119: aload_0
    //   120: astore 8
    //   122: aload_2
    //   123: ifnull -98 -> 25
    //   126: aload_2
    //   127: invokevirtual 90	android/content/res/AssetFileDescriptor:close	()V
    //   130: aload_0
    //   131: areturn
    //   132: astore_1
    //   133: aload_1
    //   134: invokevirtual 93	java/lang/Exception:printStackTrace	()V
    //   137: aload_0
    //   138: areturn
    //   139: aload_2
    //   140: invokestatic 99	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   143: invokevirtual 103	java/lang/Integer:intValue	()I
    //   146: istore_3
    //   147: aload_0
    //   148: invokevirtual 107	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   151: iload_3
    //   152: invokevirtual 113	android/content/res/Resources:openRawResourceFd	(I)Landroid/content/res/AssetFileDescriptor;
    //   155: astore_2
    //   156: aload_2
    //   157: astore_0
    //   158: aload_2
    //   159: astore_1
    //   160: aload_2
    //   161: invokevirtual 67	android/content/res/AssetFileDescriptor:getStartOffset	()J
    //   164: lstore 6
    //   166: aload_2
    //   167: astore_0
    //   168: aload_2
    //   169: astore_1
    //   170: aload_2
    //   171: invokevirtual 70	android/content/res/AssetFileDescriptor:getLength	()J
    //   174: lstore 4
    //   176: goto -106 -> 70
    //   179: astore_2
    //   180: aconst_null
    //   181: astore_1
    //   182: aload_1
    //   183: astore_0
    //   184: aload_2
    //   185: invokestatic 118	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/Throwable;)V
    //   188: aload 10
    //   190: astore 8
    //   192: aload_1
    //   193: ifnull -168 -> 25
    //   196: aload_1
    //   197: invokevirtual 90	android/content/res/AssetFileDescriptor:close	()V
    //   200: aconst_null
    //   201: areturn
    //   202: astore_1
    //   203: aload 9
    //   205: astore_0
    //   206: goto -73 -> 133
    //   209: astore_1
    //   210: aconst_null
    //   211: astore_0
    //   212: aload_0
    //   213: ifnull +7 -> 220
    //   216: aload_0
    //   217: invokevirtual 90	android/content/res/AssetFileDescriptor:close	()V
    //   220: aload_1
    //   221: athrow
    //   222: astore_0
    //   223: aload_0
    //   224: invokevirtual 93	java/lang/Exception:printStackTrace	()V
    //   227: goto -7 -> 220
    //   230: astore_1
    //   231: goto -19 -> 212
    //   234: astore_2
    //   235: goto -53 -> 182
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	238	0	paramContext	Context
    //   0	238	1	paramRESOURCE_TYPE	RESOURCE_TYPE
    //   0	238	2	paramString	String
    //   146	6	3	i	int
    //   68	107	4	l1	long
    //   58	107	6	l2	long
    //   8	183	8	localObject1	Object
    //   1	203	9	localObject2	Object
    //   4	185	10	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   126	130	132	java/lang/Exception
    //   34	50	179	java/lang/Exception
    //   139	156	179	java/lang/Exception
    //   196	200	202	java/lang/Exception
    //   34	50	209	finally
    //   139	156	209	finally
    //   216	220	222	java/lang/Exception
    //   54	60	230	finally
    //   64	70	230	finally
    //   74	116	230	finally
    //   160	166	230	finally
    //   170	176	230	finally
    //   184	188	230	finally
    //   54	60	234	java/lang/Exception
    //   64	70	234	java/lang/Exception
    //   74	116	234	java/lang/Exception
    //   160	166	234	java/lang/Exception
    //   170	176	234	java/lang/Exception
  }
  
  private static String a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    File localFile;
    do
    {
      return null;
      localFile = new File(paramString);
    } while ((!localFile.exists()) || (!localFile.isFile()));
    long l = localFile.length();
    return "fo|" + paramString + "|" + 0L + "|" + l;
  }
  
  public static String generateResourcePath(Context paramContext, RESOURCE_TYPE paramRESOURCE_TYPE, String paramString)
  {
    if (paramRESOURCE_TYPE == RESOURCE_TYPE.path) {
      return a(paramString);
    }
    return a(paramContext, paramRESOURCE_TYPE, paramString);
  }
  
  public static enum RESOURCE_TYPE
  {
    static
    {
      path = new RESOURCE_TYPE("path", 2);
    }
    
    private RESOURCE_TYPE() {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\util\ResourceUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */