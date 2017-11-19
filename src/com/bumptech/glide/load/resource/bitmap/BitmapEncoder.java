package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;

public class BitmapEncoder
  implements ResourceEncoder<Bitmap>
{
  public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");
  public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", Integer.valueOf(90));
  private static final String TAG = "BitmapEncoder";
  
  private Bitmap.CompressFormat getFormat(Bitmap paramBitmap, Options paramOptions)
  {
    paramOptions = (Bitmap.CompressFormat)paramOptions.get(COMPRESSION_FORMAT);
    if (paramOptions != null) {
      return paramOptions;
    }
    if (paramBitmap.hasAlpha()) {
      return Bitmap.CompressFormat.PNG;
    }
    return Bitmap.CompressFormat.JPEG;
  }
  
  /* Error */
  public boolean encode(com.bumptech.glide.load.engine.Resource<Bitmap> paramResource, java.io.File paramFile, Options paramOptions)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 77 1 0
    //   6: checkcast 57	android/graphics/Bitmap
    //   9: astore 10
    //   11: aload_0
    //   12: aload 10
    //   14: aload_3
    //   15: invokespecial 79	com/bumptech/glide/load/resource/bitmap/BitmapEncoder:getFormat	(Landroid/graphics/Bitmap;Lcom/bumptech/glide/load/Options;)Landroid/graphics/Bitmap$CompressFormat;
    //   18: astore 11
    //   20: new 81	java/lang/StringBuilder
    //   23: dup
    //   24: invokespecial 82	java/lang/StringBuilder:<init>	()V
    //   27: ldc 84
    //   29: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: aload 10
    //   34: invokevirtual 92	android/graphics/Bitmap:getWidth	()I
    //   37: invokevirtual 95	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   40: ldc 97
    //   42: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: aload 10
    //   47: invokevirtual 100	android/graphics/Bitmap:getHeight	()I
    //   50: invokevirtual 95	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   53: ldc 102
    //   55: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: aload 11
    //   60: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   63: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: invokestatic 115	android/support/v4/os/TraceCompat:beginSection	(Ljava/lang/String;)V
    //   69: invokestatic 121	com/bumptech/glide/util/LogTime:getLogTime	()J
    //   72: lstore 5
    //   74: aload_3
    //   75: getstatic 34	com/bumptech/glide/load/resource/bitmap/BitmapEncoder:COMPRESSION_QUALITY	Lcom/bumptech/glide/load/Option;
    //   78: invokevirtual 53	com/bumptech/glide/load/Options:get	(Lcom/bumptech/glide/load/Option;)Ljava/lang/Object;
    //   81: checkcast 22	java/lang/Integer
    //   84: invokevirtual 124	java/lang/Integer:intValue	()I
    //   87: istore 4
    //   89: iconst_0
    //   90: istore 8
    //   92: aconst_null
    //   93: astore_1
    //   94: aconst_null
    //   95: astore 9
    //   97: new 126	java/io/FileOutputStream
    //   100: dup
    //   101: aload_2
    //   102: invokespecial 129	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   105: astore_2
    //   106: aload 10
    //   108: aload 11
    //   110: iload 4
    //   112: aload_2
    //   113: invokevirtual 133	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   116: pop
    //   117: aload_2
    //   118: invokevirtual 138	java/io/OutputStream:close	()V
    //   121: iconst_1
    //   122: istore 7
    //   124: aload_2
    //   125: ifnull +197 -> 322
    //   128: aload_2
    //   129: invokevirtual 138	java/io/OutputStream:close	()V
    //   132: ldc 16
    //   134: iconst_2
    //   135: invokestatic 144	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   138: ifeq +83 -> 221
    //   141: ldc 16
    //   143: new 81	java/lang/StringBuilder
    //   146: dup
    //   147: invokespecial 82	java/lang/StringBuilder:<init>	()V
    //   150: ldc -110
    //   152: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: aload 11
    //   157: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   160: ldc -108
    //   162: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: aload 10
    //   167: invokestatic 154	com/bumptech/glide/util/Util:getBitmapByteSize	(Landroid/graphics/Bitmap;)I
    //   170: invokevirtual 95	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   173: ldc -100
    //   175: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: lload 5
    //   180: invokestatic 160	com/bumptech/glide/util/LogTime:getElapsedMillis	(J)D
    //   183: invokevirtual 163	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   186: ldc -91
    //   188: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: aload_3
    //   192: getstatic 41	com/bumptech/glide/load/resource/bitmap/BitmapEncoder:COMPRESSION_FORMAT	Lcom/bumptech/glide/load/Option;
    //   195: invokevirtual 53	com/bumptech/glide/load/Options:get	(Lcom/bumptech/glide/load/Option;)Ljava/lang/Object;
    //   198: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   201: ldc -89
    //   203: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: aload 10
    //   208: invokevirtual 61	android/graphics/Bitmap:hasAlpha	()Z
    //   211: invokevirtual 170	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   214: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   217: invokestatic 174	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   220: pop
    //   221: invokestatic 177	android/support/v4/os/TraceCompat:endSection	()V
    //   224: iload 7
    //   226: ireturn
    //   227: astore_1
    //   228: goto -96 -> 132
    //   231: astore_1
    //   232: aload 9
    //   234: astore_2
    //   235: aload_1
    //   236: astore 9
    //   238: aload_2
    //   239: astore_1
    //   240: ldc 16
    //   242: iconst_3
    //   243: invokestatic 144	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   246: ifeq +15 -> 261
    //   249: aload_2
    //   250: astore_1
    //   251: ldc 16
    //   253: ldc -77
    //   255: aload 9
    //   257: invokestatic 183	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   260: pop
    //   261: iload 8
    //   263: istore 7
    //   265: aload_2
    //   266: ifnull -134 -> 132
    //   269: aload_2
    //   270: invokevirtual 138	java/io/OutputStream:close	()V
    //   273: iload 8
    //   275: istore 7
    //   277: goto -145 -> 132
    //   280: astore_1
    //   281: iload 8
    //   283: istore 7
    //   285: goto -153 -> 132
    //   288: astore_2
    //   289: aload_1
    //   290: ifnull +7 -> 297
    //   293: aload_1
    //   294: invokevirtual 138	java/io/OutputStream:close	()V
    //   297: aload_2
    //   298: athrow
    //   299: astore_1
    //   300: invokestatic 177	android/support/v4/os/TraceCompat:endSection	()V
    //   303: aload_1
    //   304: athrow
    //   305: astore_1
    //   306: goto -9 -> 297
    //   309: astore_3
    //   310: aload_2
    //   311: astore_1
    //   312: aload_3
    //   313: astore_2
    //   314: goto -25 -> 289
    //   317: astore 9
    //   319: goto -81 -> 238
    //   322: goto -190 -> 132
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	325	0	this	BitmapEncoder
    //   0	325	1	paramResource	com.bumptech.glide.load.engine.Resource<Bitmap>
    //   0	325	2	paramFile	java.io.File
    //   0	325	3	paramOptions	Options
    //   87	24	4	i	int
    //   72	107	5	l	long
    //   122	162	7	bool1	boolean
    //   90	192	8	bool2	boolean
    //   95	161	9	localResource	com.bumptech.glide.load.engine.Resource<Bitmap>
    //   317	1	9	localIOException	java.io.IOException
    //   9	198	10	localBitmap	Bitmap
    //   18	138	11	localCompressFormat	Bitmap.CompressFormat
    // Exception table:
    //   from	to	target	type
    //   128	132	227	java/io/IOException
    //   97	106	231	java/io/IOException
    //   269	273	280	java/io/IOException
    //   97	106	288	finally
    //   240	249	288	finally
    //   251	261	288	finally
    //   69	89	299	finally
    //   128	132	299	finally
    //   132	221	299	finally
    //   269	273	299	finally
    //   293	297	299	finally
    //   297	299	299	finally
    //   293	297	305	java/io/IOException
    //   106	121	309	finally
    //   106	121	317	java/io/IOException
  }
  
  public EncodeStrategy getEncodeStrategy(Options paramOptions)
  {
    return EncodeStrategy.TRANSFORMED;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\BitmapEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */