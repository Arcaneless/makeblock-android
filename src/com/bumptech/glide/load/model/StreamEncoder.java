package com.bumptech.glide.load.model;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.InputStream;

public class StreamEncoder
  implements Encoder<InputStream>
{
  private static final String TAG = "StreamEncoder";
  private final ArrayPool byteArrayPool;
  
  public StreamEncoder(ArrayPool paramArrayPool)
  {
    this.byteArrayPool = paramArrayPool;
  }
  
  /* Error */
  public boolean encode(InputStream paramInputStream, java.io.File paramFile, com.bumptech.glide.load.Options paramOptions)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	com/bumptech/glide/load/model/StreamEncoder:byteArrayPool	Lcom/bumptech/glide/load/engine/bitmap_recycle/ArrayPool;
    //   4: ldc 26
    //   6: ldc 28
    //   8: invokeinterface 34 3 0
    //   13: checkcast 28	[B
    //   16: astore 6
    //   18: aconst_null
    //   19: astore_3
    //   20: aconst_null
    //   21: astore 5
    //   23: new 36	java/io/FileOutputStream
    //   26: dup
    //   27: aload_2
    //   28: invokespecial 39	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   31: astore_2
    //   32: aload_1
    //   33: aload 6
    //   35: invokevirtual 45	java/io/InputStream:read	([B)I
    //   38: istore 4
    //   40: goto +141 -> 181
    //   43: aload_2
    //   44: aload 6
    //   46: iconst_0
    //   47: iload 4
    //   49: invokevirtual 51	java/io/OutputStream:write	([BII)V
    //   52: goto -20 -> 32
    //   55: astore_3
    //   56: aload_2
    //   57: astore_1
    //   58: aload_3
    //   59: astore_2
    //   60: aload_1
    //   61: astore_3
    //   62: ldc 11
    //   64: iconst_3
    //   65: invokestatic 57	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   68: ifeq +14 -> 82
    //   71: aload_1
    //   72: astore_3
    //   73: ldc 11
    //   75: ldc 59
    //   77: aload_2
    //   78: invokestatic 63	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   81: pop
    //   82: aload_1
    //   83: ifnull +7 -> 90
    //   86: aload_1
    //   87: invokevirtual 66	java/io/OutputStream:close	()V
    //   90: aload_0
    //   91: getfield 20	com/bumptech/glide/load/model/StreamEncoder:byteArrayPool	Lcom/bumptech/glide/load/engine/bitmap_recycle/ArrayPool;
    //   94: aload 6
    //   96: ldc 28
    //   98: invokeinterface 70 3 0
    //   103: iconst_0
    //   104: ireturn
    //   105: aload_2
    //   106: invokevirtual 66	java/io/OutputStream:close	()V
    //   109: aload_2
    //   110: ifnull +7 -> 117
    //   113: aload_2
    //   114: invokevirtual 66	java/io/OutputStream:close	()V
    //   117: aload_0
    //   118: getfield 20	com/bumptech/glide/load/model/StreamEncoder:byteArrayPool	Lcom/bumptech/glide/load/engine/bitmap_recycle/ArrayPool;
    //   121: aload 6
    //   123: ldc 28
    //   125: invokeinterface 70 3 0
    //   130: iconst_1
    //   131: ireturn
    //   132: astore_1
    //   133: aload_3
    //   134: ifnull +7 -> 141
    //   137: aload_3
    //   138: invokevirtual 66	java/io/OutputStream:close	()V
    //   141: aload_0
    //   142: getfield 20	com/bumptech/glide/load/model/StreamEncoder:byteArrayPool	Lcom/bumptech/glide/load/engine/bitmap_recycle/ArrayPool;
    //   145: aload 6
    //   147: ldc 28
    //   149: invokeinterface 70 3 0
    //   154: aload_1
    //   155: athrow
    //   156: astore_1
    //   157: goto -40 -> 117
    //   160: astore_1
    //   161: goto -71 -> 90
    //   164: astore_2
    //   165: goto -24 -> 141
    //   168: astore_1
    //   169: aload_2
    //   170: astore_3
    //   171: goto -38 -> 133
    //   174: astore_2
    //   175: aload 5
    //   177: astore_1
    //   178: goto -118 -> 60
    //   181: iload 4
    //   183: iconst_m1
    //   184: if_icmpeq -79 -> 105
    //   187: goto -144 -> 43
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	190	0	this	StreamEncoder
    //   0	190	1	paramInputStream	InputStream
    //   0	190	2	paramFile	java.io.File
    //   0	190	3	paramOptions	com.bumptech.glide.load.Options
    //   38	147	4	i	int
    //   21	155	5	localObject	Object
    //   16	130	6	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   32	40	55	java/io/IOException
    //   43	52	55	java/io/IOException
    //   105	109	55	java/io/IOException
    //   23	32	132	finally
    //   62	71	132	finally
    //   73	82	132	finally
    //   113	117	156	java/io/IOException
    //   86	90	160	java/io/IOException
    //   137	141	164	java/io/IOException
    //   32	40	168	finally
    //   43	52	168	finally
    //   105	109	168	finally
    //   23	32	174	java/io/IOException
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\model\StreamEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */