package cc.makeblock.makeblock.engine.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class FileUtils
{
  public static final String AD_IMAGE_FILE_NAME = "ad_image.png";
  private static final String TAG = "FileUtils";
  
  private static int computeSampleSize(int paramInt1, int paramInt2)
  {
    int i = ScreenHelper.SCREEN_WIDTH;
    int j = ScreenHelper.SCREEN_HEIGHT;
    float f2 = 0.0F;
    float f1;
    if ((paramInt1 > paramInt2) && (paramInt1 > i)) {
      f1 = paramInt1 * 1.0F / i;
    }
    while ((f1 > 1.1D) && (f1 <= 2.5D))
    {
      return 2;
      f1 = f2;
      if (paramInt1 < paramInt2)
      {
        f1 = f2;
        if (paramInt2 > j) {
          f1 = paramInt2 * 1.0F / j;
        }
      }
    }
    if ((f1 > 2.5D) && (f1 <= 4.0F)) {
      return 4;
    }
    if ((f1 > 4.5D) && (f1 <= 8.0F)) {
      return 8;
    }
    return (int)f1;
  }
  
  public static void copyAssetsFileToSD(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    paramString2 = new FileOutputStream(paramString2);
    paramContext = paramContext.getAssets().open(paramString1);
    paramString1 = new byte['Ѐ'];
    for (int i = paramContext.read(paramString1); i > 0; i = paramContext.read(paramString1)) {
      paramString2.write(paramString1, 0, i);
    }
    paramString2.flush();
    paramContext.close();
    paramString2.close();
  }
  
  public static String getAppExternalStorageDirectoryPath()
  {
    return Environment.getExternalStorageDirectory().getPath() + "/Makeblock";
  }
  
  public static String getCameraStorageAbsPath()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Environment.DIRECTORY_DCIM + "/";
  }
  
  public static String getCameraStorageAbsPath(String paramString)
  {
    paramString = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Environment.DIRECTORY_DCIM + "/" + paramString;
    File localFile = new File(paramString);
    if (!localFile.exists())
    {
      localFile.mkdirs();
      if (!localFile.exists()) {}
    }
    else
    {
      return paramString;
    }
    return "";
  }
  
  public static String getDataColumn(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    Context localContext = null;
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, paramString, paramArrayOfString, null);
      if (paramContext != null)
      {
        localContext = paramContext;
        if (paramContext.moveToFirst())
        {
          localContext = paramContext;
          paramUri = paramContext.getString(paramContext.getColumnIndexOrThrow("_data"));
          return paramUri;
        }
      }
      return null;
    }
    finally
    {
      if (localContext != null) {
        localContext.close();
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  public static String getPath(Context paramContext, Uri paramUri)
  {
    Object localObject1 = null;
    int i;
    if (Build.VERSION.SDK_INT >= 19)
    {
      i = 1;
      if ((i == 0) || (!DocumentsContract.isDocumentUri(paramContext, paramUri))) {
        break label211;
      }
      if (!isExternalStorageDocument(paramUri)) {
        break label87;
      }
      paramContext = DocumentsContract.getDocumentId(paramUri).split(":");
      if ("primary".equalsIgnoreCase(paramContext[0])) {
        localObject1 = Environment.getExternalStorageDirectory() + "/" + paramContext[1];
      }
    }
    label87:
    label211:
    do
    {
      do
      {
        return (String)localObject1;
        i = 0;
        break;
        if (isDownloadsDocument(paramUri))
        {
          paramUri = DocumentsContract.getDocumentId(paramUri);
          return getDataColumn(paramContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(paramUri).longValue()), null, null);
        }
      } while (!isMediaDocument(paramUri));
      localObject1 = DocumentsContract.getDocumentId(paramUri).split(":");
      Object localObject2 = localObject1[0];
      paramUri = null;
      if ("image".equals(localObject2)) {
        paramUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
      }
      for (;;)
      {
        return getDataColumn(paramContext, paramUri, "_id=?", new String[] { localObject1[1] });
        if ("video".equals(localObject2)) {
          paramUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if ("audio".equals(localObject2)) {
          paramUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
      }
      if ("content".equalsIgnoreCase(paramUri.getScheme())) {
        return getDataColumn(paramContext, paramUri, null, null);
      }
    } while (!"file".equalsIgnoreCase(paramUri.getScheme()));
    return paramUri.getPath();
  }
  
  public static File getRealFileName(String paramString1, String paramString2)
  {
    String[] arrayOfString = paramString2.split("/");
    paramString1 = new File(paramString1);
    if (arrayOfString.length > 1)
    {
      int i = 0;
      for (;;)
      {
        if (i >= arrayOfString.length - 1) {
          break label85;
        }
        paramString2 = arrayOfString[i];
        try
        {
          String str1 = new String(paramString2.getBytes("8859_1"), "GB2312");
          paramString2 = str1;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException1)
        {
          for (;;)
          {
            localUnsupportedEncodingException1.printStackTrace();
          }
        }
        paramString1 = new File(paramString1, paramString2);
        i += 1;
      }
      label85:
      if (!paramString1.exists()) {
        paramString1.mkdirs();
      }
      paramString2 = arrayOfString[(arrayOfString.length - 1)];
      try
      {
        String str2 = new String(paramString2.getBytes("8859_1"), "GB2312");
        paramString2 = str2;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException2)
      {
        for (;;)
        {
          localUnsupportedEncodingException2.printStackTrace();
        }
      }
      return new File(paramString1, paramString2);
    }
    return paramString1;
  }
  
  public static boolean isDownloadsDocument(Uri paramUri)
  {
    return "com.android.providers.downloads.documents".equals(paramUri.getAuthority());
  }
  
  public static boolean isExternalStorageDocument(Uri paramUri)
  {
    return "com.android.externalstorage.documents".equals(paramUri.getAuthority());
  }
  
  public static boolean isMediaDocument(Uri paramUri)
  {
    return "com.android.providers.media.documents".equals(paramUri.getAuthority());
  }
  
  public static boolean isSdcardAvailable()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static String readAssetJsonFile(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getAssets().open(paramString);
      paramString = new byte[paramContext.available()];
      paramContext.read(paramString);
      paramContext.close();
      paramContext = new String(paramString, "UTF-8");
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String readAssetTextFile(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getAssets().open(paramString);
      paramString = new byte[paramContext.available()];
      paramContext.read(paramString);
      paramContext.close();
      paramContext = new String(paramString);
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Bitmap readSuitableBitmapFromSDCard(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = computeSampleSize(localOptions.outWidth, localOptions.outHeight);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(paramString, localOptions);
  }
  
  public static String saveBitmapToSDCard(Bitmap paramBitmap, String paramString)
  {
    if ((paramBitmap == null) || (TextUtils.isEmpty(paramString))) {}
    Object localObject;
    do
    {
      return null;
      localObject = getAppExternalStorageDirectoryPath() + "/pic";
      localFile = new File((String)localObject);
      if (localFile.exists()) {
        break;
      }
    } while (!localFile.mkdirs());
    File localFile = new File((String)localObject + "/.nomedia");
    if (!localFile.exists()) {}
    try
    {
      boolean bool = localFile.createNewFile();
      if (!bool) {}
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        try
        {
          localObject = new FileOutputStream(paramString);
          paramBitmap.compress(Bitmap.CompressFormat.PNG, 90, (OutputStream)localObject);
          ((FileOutputStream)localObject).flush();
          ((FileOutputStream)localObject).close();
          return paramString.getAbsolutePath();
        }
        catch (IOException paramBitmap)
        {
          paramBitmap.printStackTrace();
        }
        localIOException = localIOException;
        localIOException.printStackTrace();
      }
    }
    paramString = new File((String)localObject + "/" + paramString);
    if (paramString.exists()) {
      paramString.delete();
    }
    return null;
  }
  
  /* Error */
  public static void saveFileFromInputStream(InputStream paramInputStream, String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: new 89	java/io/File
    //   9: dup
    //   10: invokestatic 339	cc/makeblock/makeblock/engine/utils/FileUtils:getAppExternalStorageDirectoryPath	()Ljava/lang/String;
    //   13: invokespecial 112	java/io/File:<init>	(Ljava/lang/String;)V
    //   16: astore 7
    //   18: aload 7
    //   20: invokevirtual 116	java/io/File:exists	()Z
    //   23: ifne +9 -> 32
    //   26: aload 7
    //   28: invokevirtual 371	java/io/File:mkdir	()Z
    //   31: pop
    //   32: new 89	java/io/File
    //   35: dup
    //   36: aload_1
    //   37: invokespecial 112	java/io/File:<init>	(Ljava/lang/String;)V
    //   40: astore 7
    //   42: aload 7
    //   44: invokevirtual 116	java/io/File:exists	()Z
    //   47: ifne +25 -> 72
    //   50: aload 7
    //   52: invokevirtual 371	java/io/File:mkdir	()Z
    //   55: istore 4
    //   57: iload 4
    //   59: ifne +19 -> 78
    //   62: ldc 11
    //   64: ldc_w 373
    //   67: invokestatic 379	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   70: pop
    //   71: return
    //   72: iconst_1
    //   73: istore 4
    //   75: goto -18 -> 57
    //   78: new 89	java/io/File
    //   81: dup
    //   82: new 80	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   89: aload_1
    //   90: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: aload_2
    //   94: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   100: invokespecial 112	java/io/File:<init>	(Ljava/lang/String;)V
    //   103: astore 7
    //   105: aload 5
    //   107: astore_1
    //   108: new 41	java/io/FileOutputStream
    //   111: dup
    //   112: aload 7
    //   114: invokespecial 352	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   117: astore_2
    //   118: sipush 2048
    //   121: newarray <illegal type>
    //   123: astore_1
    //   124: goto +127 -> 251
    //   127: aload_0
    //   128: aload_1
    //   129: invokevirtual 62	java/io/InputStream:read	([B)I
    //   132: istore_3
    //   133: iload_3
    //   134: iconst_m1
    //   135: if_icmpeq +57 -> 192
    //   138: aload_2
    //   139: aload_1
    //   140: iconst_0
    //   141: iload_3
    //   142: invokevirtual 380	java/io/FileOutputStream:write	([BII)V
    //   145: goto -18 -> 127
    //   148: astore 5
    //   150: aload_2
    //   151: astore_1
    //   152: aload 7
    //   154: invokevirtual 116	java/io/File:exists	()Z
    //   157: ifeq +11 -> 168
    //   160: aload_2
    //   161: astore_1
    //   162: aload 7
    //   164: invokevirtual 349	java/io/File:delete	()Z
    //   167: pop
    //   168: aload_2
    //   169: astore_1
    //   170: aload 5
    //   172: invokevirtual 299	java/io/IOException:printStackTrace	()V
    //   175: aload_0
    //   176: ifnull +7 -> 183
    //   179: aload_0
    //   180: invokevirtual 74	java/io/InputStream:close	()V
    //   183: aload_2
    //   184: ifnull -113 -> 71
    //   187: aload_2
    //   188: invokevirtual 366	java/io/FileOutputStream:close	()V
    //   191: return
    //   192: aload_2
    //   193: invokevirtual 365	java/io/FileOutputStream:flush	()V
    //   196: aload_0
    //   197: ifnull +7 -> 204
    //   200: aload_0
    //   201: invokevirtual 74	java/io/InputStream:close	()V
    //   204: aload_2
    //   205: ifnull +45 -> 250
    //   208: aload_2
    //   209: invokevirtual 366	java/io/FileOutputStream:close	()V
    //   212: return
    //   213: astore_2
    //   214: aload_0
    //   215: ifnull +7 -> 222
    //   218: aload_0
    //   219: invokevirtual 74	java/io/InputStream:close	()V
    //   222: aload_1
    //   223: ifnull +7 -> 230
    //   226: aload_1
    //   227: invokevirtual 366	java/io/FileOutputStream:close	()V
    //   230: aload_2
    //   231: athrow
    //   232: astore 5
    //   234: aload_2
    //   235: astore_1
    //   236: aload 5
    //   238: astore_2
    //   239: goto -25 -> 214
    //   242: astore 5
    //   244: aload 6
    //   246: astore_2
    //   247: goto -97 -> 150
    //   250: return
    //   251: goto -124 -> 127
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	254	0	paramInputStream	InputStream
    //   0	254	1	paramString1	String
    //   0	254	2	paramString2	String
    //   132	10	3	i	int
    //   55	19	4	bool	boolean
    //   1	105	5	localObject1	Object
    //   148	23	5	localIOException1	IOException
    //   232	5	5	localObject2	Object
    //   242	1	5	localIOException2	IOException
    //   4	241	6	localObject3	Object
    //   16	147	7	localFile	File
    // Exception table:
    //   from	to	target	type
    //   118	124	148	java/io/IOException
    //   127	133	148	java/io/IOException
    //   138	145	148	java/io/IOException
    //   192	196	148	java/io/IOException
    //   108	118	213	finally
    //   152	160	213	finally
    //   162	168	213	finally
    //   170	175	213	finally
    //   118	124	232	finally
    //   127	133	232	finally
    //   138	145	232	finally
    //   192	196	232	finally
    //   108	118	242	java/io/IOException
  }
  
  public static void showAllFiles(File paramFile)
    throws Exception
  {
    paramFile = paramFile.listFiles();
    int i = 0;
    for (;;)
    {
      if ((i >= paramFile.length) || (paramFile[i].isDirectory())) {}
      try
      {
        showAllFiles(paramFile[i]);
        i += 1;
        continue;
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public static int upZipFile(File paramFile, String paramString)
    throws ZipException, IOException
  {
    paramFile = new ZipFile(paramFile);
    Enumeration localEnumeration = paramFile.entries();
    byte[] arrayOfByte = new byte['Ѐ'];
    while (localEnumeration.hasMoreElements())
    {
      Object localObject = (ZipEntry)localEnumeration.nextElement();
      if (((ZipEntry)localObject).isDirectory())
      {
        new File(new String((paramString + ((ZipEntry)localObject).getName()).getBytes("8859_1"), "GB2312")).mkdir();
      }
      else
      {
        BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(getRealFileName(paramString, ((ZipEntry)localObject).getName())));
        localObject = new BufferedInputStream(paramFile.getInputStream((ZipEntry)localObject));
        for (;;)
        {
          int i = ((InputStream)localObject).read(arrayOfByte, 0, 1024);
          if (i == -1) {
            break;
          }
          localBufferedOutputStream.write(arrayOfByte, 0, i);
        }
        ((InputStream)localObject).close();
        localBufferedOutputStream.close();
      }
    }
    paramFile.close();
    return 0;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */