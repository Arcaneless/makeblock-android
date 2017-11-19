package cc.makeblock.makeblock.engine.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import cc.makeblock.makeblock.base.App;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;

public class BitmapUtils
{
  private static final String TAG = "BitmapUtils";
  
  public static Bitmap addFrame(Bitmap paramBitmap, float paramFloat, int paramInt)
  {
    Canvas localCanvas = new Canvas(paramBitmap);
    Paint localPaint = new Paint();
    localPaint.setColor(paramInt);
    localPaint.setStrokeWidth(paramFloat);
    paramInt = paramBitmap.getWidth();
    int i = paramBitmap.getHeight();
    localCanvas.drawLine(0.0F, 0.0F, paramInt, 0.0F, localPaint);
    localCanvas.drawLine(paramInt, 0.0F, paramInt, i, localPaint);
    localCanvas.drawLine(0.0F, 0.0F, 0.0F, i, localPaint);
    localCanvas.drawLine(0.0F, i, paramInt, i, localPaint);
    return paramBitmap;
  }
  
  public static Bitmap base64ToBitmap(String paramString)
  {
    paramString = Base64.decode(paramString, 0);
    return BitmapFactory.decodeByteArray(paramString, 0, paramString.length);
  }
  
  /* Error */
  public static String bitmapToBase64(Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_1
    //   4: aconst_null
    //   5: astore 4
    //   7: aconst_null
    //   8: astore 5
    //   10: aconst_null
    //   11: astore_2
    //   12: aload_0
    //   13: ifnull +46 -> 59
    //   16: aload 5
    //   18: astore_2
    //   19: new 64	java/io/ByteArrayOutputStream
    //   22: dup
    //   23: invokespecial 65	java/io/ByteArrayOutputStream:<init>	()V
    //   26: astore_1
    //   27: aload_0
    //   28: getstatic 71	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   31: bipush 100
    //   33: aload_1
    //   34: invokevirtual 75	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   37: pop
    //   38: aload_1
    //   39: invokevirtual 78	java/io/ByteArrayOutputStream:flush	()V
    //   42: aload_1
    //   43: invokevirtual 81	java/io/ByteArrayOutputStream:close	()V
    //   46: aload_1
    //   47: invokevirtual 85	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   50: iconst_0
    //   51: invokestatic 89	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   54: astore_0
    //   55: aload_1
    //   56: astore_2
    //   57: aload_0
    //   58: astore_1
    //   59: aload_1
    //   60: astore_3
    //   61: aload_2
    //   62: ifnull +13 -> 75
    //   65: aload_2
    //   66: invokevirtual 78	java/io/ByteArrayOutputStream:flush	()V
    //   69: aload_2
    //   70: invokevirtual 81	java/io/ByteArrayOutputStream:close	()V
    //   73: aload_1
    //   74: astore_3
    //   75: aload_3
    //   76: areturn
    //   77: astore_0
    //   78: aload_0
    //   79: invokevirtual 92	java/io/IOException:printStackTrace	()V
    //   82: aload_1
    //   83: areturn
    //   84: astore_1
    //   85: aload 4
    //   87: astore_0
    //   88: aload_0
    //   89: astore_2
    //   90: aload_1
    //   91: invokevirtual 92	java/io/IOException:printStackTrace	()V
    //   94: aload_0
    //   95: ifnull -20 -> 75
    //   98: aload_0
    //   99: invokevirtual 78	java/io/ByteArrayOutputStream:flush	()V
    //   102: aload_0
    //   103: invokevirtual 81	java/io/ByteArrayOutputStream:close	()V
    //   106: aconst_null
    //   107: areturn
    //   108: astore_0
    //   109: aload_0
    //   110: invokevirtual 92	java/io/IOException:printStackTrace	()V
    //   113: aconst_null
    //   114: areturn
    //   115: astore_0
    //   116: aload_2
    //   117: ifnull +11 -> 128
    //   120: aload_2
    //   121: invokevirtual 78	java/io/ByteArrayOutputStream:flush	()V
    //   124: aload_2
    //   125: invokevirtual 81	java/io/ByteArrayOutputStream:close	()V
    //   128: aload_0
    //   129: athrow
    //   130: astore_1
    //   131: aload_1
    //   132: invokevirtual 92	java/io/IOException:printStackTrace	()V
    //   135: goto -7 -> 128
    //   138: astore_0
    //   139: aload_1
    //   140: astore_2
    //   141: goto -25 -> 116
    //   144: astore_2
    //   145: aload_1
    //   146: astore_0
    //   147: aload_2
    //   148: astore_1
    //   149: goto -61 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	152	0	paramBitmap	Bitmap
    //   3	80	1	localObject1	Object
    //   84	7	1	localIOException1	IOException
    //   130	16	1	localIOException2	IOException
    //   148	1	1	localObject2	Object
    //   11	130	2	localObject3	Object
    //   144	4	2	localIOException3	IOException
    //   1	75	3	localObject4	Object
    //   5	81	4	localObject5	Object
    //   8	9	5	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   65	73	77	java/io/IOException
    //   19	27	84	java/io/IOException
    //   98	106	108	java/io/IOException
    //   19	27	115	finally
    //   90	94	115	finally
    //   120	128	130	java/io/IOException
    //   27	55	138	finally
    //   27	55	144	java/io/IOException
  }
  
  public static Drawable bitmapToDrawble(Bitmap paramBitmap, Context paramContext)
  {
    return new BitmapDrawable(paramContext.getResources(), paramBitmap);
  }
  
  private static int calculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int j = paramOptions.outHeight;
    int k = paramOptions.outWidth;
    int i = 1;
    if ((j > paramInt2) || (k > paramInt1))
    {
      paramInt1 = k / paramInt1;
      paramInt2 = j / paramInt2;
      if (paramInt1 > paramInt2) {}
      for (;;)
      {
        paramInt2 = 1;
        while (Math.pow(2.0D, paramInt2) < paramInt1) {
          paramInt2 += 1;
        }
        paramInt1 = paramInt2;
      }
      i = (int)Math.pow(2.0D, paramInt2);
    }
    return i;
  }
  
  public static Bitmap convertBmp(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(-1.0F, 1.0F);
    paramBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
    localCanvas.drawBitmap(paramBitmap, new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), new Rect(0, 0, i, j), null);
    return localBitmap;
  }
  
  public static Bitmap decodeSampledBitmapFromFile_565(String paramString, int paramInt1, int paramInt2)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt1, paramInt2);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(paramString, localOptions);
  }
  
  public static Bitmap decodeSampledBitmapFromFile_8888(String paramString, int paramInt1, int paramInt2)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt1, paramInt2);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(paramString, localOptions);
  }
  
  public static Bitmap decodeSampledBitmapFromResource(Resources paramResources, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramResources == null) || (paramInt2 <= 0) || (paramInt3 <= 0)) {
      return null;
    }
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
    localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt2, paramInt3);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
  }
  
  public static Bitmap fastblur(Context paramContext, Bitmap paramBitmap, int paramInt)
  {
    System.currentTimeMillis();
    paramContext = paramBitmap.copy(paramBitmap.getConfig(), true);
    if (paramInt < 1) {
      return null;
    }
    int i10 = paramContext.getWidth();
    int i11 = paramContext.getHeight();
    paramBitmap = new int[i10 * i11];
    paramContext.getPixels(paramBitmap, 0, i10, 0, 0, i10, i11);
    int i15 = i10 - 1;
    int i12 = i11 - 1;
    int i = i10 * i11;
    int i13 = paramInt + paramInt + 1;
    int[] arrayOfInt1 = new int[i];
    int[] arrayOfInt2 = new int[i];
    int[] arrayOfInt3 = new int[i];
    int[] arrayOfInt4 = new int[Math.max(i10, i11)];
    i = i13 + 1 >> 1;
    int j = i * i;
    int k = j * 300;
    int[] arrayOfInt5 = new int[k];
    i = 0;
    while (i < k)
    {
      arrayOfInt5[i] = (i / j);
      i += 1;
    }
    int i7 = 0;
    int i5 = 0;
    int[][] arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, new int[] { i13, 3 });
    int i14 = paramInt + 1;
    int i6 = 0;
    int i1;
    int i3;
    int i4;
    int n;
    int i2;
    int m;
    int i8;
    int i9;
    int[] arrayOfInt6;
    int i17;
    int i16;
    while (i6 < i11)
    {
      i1 = 0;
      i3 = 0;
      i4 = 0;
      j = 0;
      n = 0;
      i2 = 0;
      i = 0;
      k = 0;
      m = 0;
      i8 = -paramInt;
      if (i8 <= paramInt)
      {
        i9 = paramBitmap[(Math.min(i15, Math.max(i8, 0)) + i7)];
        arrayOfInt6 = arrayOfInt[(i8 + paramInt)];
        arrayOfInt6[0] = ((0xFF0000 & i9) >> 16);
        arrayOfInt6[1] = ((0xFF00 & i9) >> 8);
        arrayOfInt6[2] = (i9 & 0xFF);
        i9 = i14 - Math.abs(i8);
        i4 += arrayOfInt6[0] * i9;
        i3 += arrayOfInt6[1] * i9;
        i1 += arrayOfInt6[2] * i9;
        if (i8 > 0)
        {
          m += arrayOfInt6[0];
          k += arrayOfInt6[1];
          i += arrayOfInt6[2];
        }
        for (;;)
        {
          i8 += 1;
          break;
          i2 += arrayOfInt6[0];
          n += arrayOfInt6[1];
          j += arrayOfInt6[2];
        }
      }
      i9 = paramInt;
      i8 = 0;
      while (i8 < i10)
      {
        arrayOfInt1[i7] = arrayOfInt5[i4];
        arrayOfInt2[i7] = arrayOfInt5[i3];
        arrayOfInt3[i7] = arrayOfInt5[i1];
        arrayOfInt6 = arrayOfInt[((i9 - paramInt + i13) % i13)];
        int i18 = arrayOfInt6[0];
        i17 = arrayOfInt6[1];
        i16 = arrayOfInt6[2];
        if (i6 == 0) {
          arrayOfInt4[i8] = Math.min(i8 + paramInt + 1, i15);
        }
        int i19 = paramBitmap[(arrayOfInt4[i8] + i5)];
        arrayOfInt6[0] = ((0xFF0000 & i19) >> 16);
        arrayOfInt6[1] = ((0xFF00 & i19) >> 8);
        arrayOfInt6[2] = (i19 & 0xFF);
        m += arrayOfInt6[0];
        k += arrayOfInt6[1];
        i += arrayOfInt6[2];
        i4 = i4 - i2 + m;
        i3 = i3 - n + k;
        i1 = i1 - j + i;
        i9 = (i9 + 1) % i13;
        arrayOfInt6 = arrayOfInt[(i9 % i13)];
        i2 = i2 - i18 + arrayOfInt6[0];
        n = n - i17 + arrayOfInt6[1];
        j = j - i16 + arrayOfInt6[2];
        m -= arrayOfInt6[0];
        k -= arrayOfInt6[1];
        i -= arrayOfInt6[2];
        i7 += 1;
        i8 += 1;
      }
      i5 += i10;
      i6 += 1;
    }
    i = 0;
    while (i < i10)
    {
      i2 = 0;
      i4 = 0;
      i5 = 0;
      k = 0;
      i1 = 0;
      i3 = 0;
      j = 0;
      m = 0;
      n = 0;
      i6 = -paramInt * i10;
      i7 = -paramInt;
      if (i7 <= paramInt)
      {
        i8 = Math.max(0, i6) + i;
        arrayOfInt6 = arrayOfInt[(i7 + paramInt)];
        arrayOfInt6[0] = arrayOfInt1[i8];
        arrayOfInt6[1] = arrayOfInt2[i8];
        arrayOfInt6[2] = arrayOfInt3[i8];
        i9 = i14 - Math.abs(i7);
        i5 += arrayOfInt1[i8] * i9;
        i4 += arrayOfInt2[i8] * i9;
        i2 += arrayOfInt3[i8] * i9;
        if (i7 > 0)
        {
          n += arrayOfInt6[0];
          m += arrayOfInt6[1];
          j += arrayOfInt6[2];
        }
        for (;;)
        {
          i8 = i6;
          if (i7 < i12) {
            i8 = i6 + i10;
          }
          i7 += 1;
          i6 = i8;
          break;
          i3 += arrayOfInt6[0];
          i1 += arrayOfInt6[1];
          k += arrayOfInt6[2];
        }
      }
      i7 = i;
      i8 = paramInt;
      i6 = 0;
      while (i6 < i11)
      {
        paramBitmap[i7] = (0xFF000000 & paramBitmap[i7] | arrayOfInt5[i5] << 16 | arrayOfInt5[i4] << 8 | arrayOfInt5[i2]);
        arrayOfInt6 = arrayOfInt[((i8 - paramInt + i13) % i13)];
        i16 = arrayOfInt6[0];
        i15 = arrayOfInt6[1];
        i9 = arrayOfInt6[2];
        if (i == 0) {
          arrayOfInt4[i6] = (Math.min(i6 + i14, i12) * i10);
        }
        i17 = i + arrayOfInt4[i6];
        arrayOfInt6[0] = arrayOfInt1[i17];
        arrayOfInt6[1] = arrayOfInt2[i17];
        arrayOfInt6[2] = arrayOfInt3[i17];
        n += arrayOfInt6[0];
        m += arrayOfInt6[1];
        j += arrayOfInt6[2];
        i5 = i5 - i3 + n;
        i4 = i4 - i1 + m;
        i2 = i2 - k + j;
        i8 = (i8 + 1) % i13;
        arrayOfInt6 = arrayOfInt[i8];
        i3 = i3 - i16 + arrayOfInt6[0];
        i1 = i1 - i15 + arrayOfInt6[1];
        k = k - i9 + arrayOfInt6[2];
        n -= arrayOfInt6[0];
        m -= arrayOfInt6[1];
        j -= arrayOfInt6[2];
        i7 += i10;
        i6 += 1;
      }
      i += 1;
    }
    paramContext.setPixels(paramBitmap, 0, i10, 0, 0, i10, i11);
    System.currentTimeMillis();
    return paramContext;
  }
  
  public static Drawable getDrawable(int paramInt)
  {
    return ContextCompat.getDrawable(App.getContext(), paramInt);
  }
  
  public static Drawable getDrawable(Context paramContext, int paramInt)
  {
    return ContextCompat.getDrawable(paramContext, paramInt);
  }
  
  public static Bitmap getScreenShot(Activity paramActivity)
  {
    View localView = paramActivity.getWindow().getDecorView();
    localView.buildDrawingCache();
    Rect localRect = new Rect();
    localView.getWindowVisibleDisplayFrame(localRect);
    int i = localRect.top;
    paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
    int j = paramActivity.getWidth();
    int k = paramActivity.getHeight();
    localView.setDrawingCacheEnabled(true);
    paramActivity = Bitmap.createBitmap(localView.getDrawingCache(), 0, i, j, k - i);
    localView.destroyDrawingCache();
    return paramActivity;
  }
  
  public static Bitmap rotate(Bitmap paramBitmap, int paramInt)
  {
    Object localObject = paramBitmap;
    if (paramInt != 0)
    {
      localObject = paramBitmap;
      if (paramBitmap != null)
      {
        localObject = new Matrix();
        ((Matrix)localObject).setRotate(paramInt, paramBitmap.getWidth() / 2.0F, paramBitmap.getHeight() / 2.0F);
      }
    }
    for (;;)
    {
      Bitmap localBitmap2;
      try
      {
        localBitmap2 = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), (Matrix)localObject, true);
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        return paramBitmap;
      }
      paramBitmap.recycle();
      localObject = localBitmap2;
      return (Bitmap)localObject;
      Bitmap localBitmap1 = paramBitmap;
      if (paramBitmap == localBitmap2) {}
    }
  }
  
  public static boolean saveBitmapToFile(Bitmap paramBitmap, String paramString)
  {
    return saveBitmapToFile(paramBitmap, paramString, 0);
  }
  
  public static boolean saveBitmapToFile(Bitmap paramBitmap, String paramString, int paramInt)
  {
    if (paramBitmap == null) {}
    Object localObject;
    do
    {
      do
      {
        return false;
      } while (TextUtils.isEmpty(paramString));
      localObject = new File(paramString);
    } while (((File)localObject).exists());
    if (paramInt <= 0) {}
    for (;;)
    {
      try
      {
        localObject = new FileOutputStream(paramString);
        paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)localObject);
        ((FileOutputStream)localObject).flush();
        ((FileOutputStream)localObject).close();
        if (new File(paramString).exists()) {
          break;
        }
        return true;
      }
      catch (Exception paramBitmap)
      {
        paramBitmap.printStackTrace();
        continue;
      }
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
      int i = 100;
      while ((localByteArrayOutputStream.toByteArray().length > paramInt) && (i > 10))
      {
        localByteArrayOutputStream.reset();
        i = (int)(i * 0.9D);
        paramBitmap.compress(Bitmap.CompressFormat.JPEG, i, localByteArrayOutputStream);
      }
      try
      {
        localByteArrayOutputStream.writeTo(new FileOutputStream((File)localObject));
        localByteArrayOutputStream.close();
      }
      catch (FileNotFoundException paramBitmap)
      {
        paramBitmap.printStackTrace();
        return false;
      }
      catch (IOException paramBitmap)
      {
        paramBitmap.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean saveSquarePhotoThumbnailToFile(String paramString, double paramDouble, Bitmap paramBitmap)
  {
    if (paramBitmap == null) {}
    while (TextUtils.isEmpty(paramString)) {
      return false;
    }
    double d = paramDouble;
    if (paramDouble <= 0.0D) {
      d = 0.5D;
    }
    paramDouble = d;
    if (d > 1.0D) {
      paramDouble = 1.0D;
    }
    int i = (int)(paramBitmap.getWidth() * paramDouble);
    int j = (int)(paramBitmap.getHeight() * paramDouble);
    if (i >= j) {
      i = j;
    }
    for (;;)
    {
      return saveBitmapToFile(ThumbnailUtils.extractThumbnail(paramBitmap, i, i), paramString);
    }
  }
  
  public static boolean saveSquareVideoThumbnailToFile(String paramString1, String paramString2)
  {
    return saveBitmapToFile(ThumbnailUtils.createVideoThumbnail(paramString1, 3), paramString2);
  }
  
  public static Bitmap small(Bitmap paramBitmap, float paramFloat)
  {
    float f = paramBitmap.getWidth();
    f = paramBitmap.getHeight();
    return ThumbnailUtils.extractThumbnail(paramBitmap, (int)(paramBitmap.getWidth() * paramFloat), (int)(paramBitmap.getHeight() * paramFloat));
  }
  
  public static Bitmap toRoundBitmap(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f6;
    float f1;
    float f4;
    float f5;
    float f3;
    if (i <= j)
    {
      f6 = i / 2;
      f1 = i;
      f4 = 0.0F;
      f5 = i;
      j = i;
      f3 = i;
    }
    for (float f2 = i;; f2 = j)
    {
      Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      Rect localRect1 = new Rect((int)f4, (int)0.0F, (int)f5, (int)f1);
      Rect localRect2 = new Rect((int)0.0F, (int)0.0F, (int)f3, (int)f2);
      RectF localRectF = new RectF(localRect2);
      localPaint.setAntiAlias(true);
      localCanvas.drawARGB(0, 0, 0, 0);
      localPaint.setColor(-12434878);
      localCanvas.drawRoundRect(localRectF, f6, f6, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, localRect1, localRect2, localPaint);
      return localBitmap;
      f6 = j / 2;
      f1 = (i - j) / 2;
      f4 = f1;
      f5 = i - f1;
      f1 = j;
      i = j;
      f3 = j;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\BitmapUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */