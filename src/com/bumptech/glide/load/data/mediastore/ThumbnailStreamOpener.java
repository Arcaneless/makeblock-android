package com.bumptech.glide.load.data.mediastore;

import android.content.ContentResolver;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.util.List;

class ThumbnailStreamOpener
{
  private static final FileService DEFAULT_SERVICE = new FileService();
  private static final String TAG = "ThumbStreamOpener";
  private final ArrayPool byteArrayPool;
  private final ContentResolver contentResolver;
  private final List<ImageHeaderParser> parsers;
  private final ThumbnailQuery query;
  private final FileService service;
  
  public ThumbnailStreamOpener(List<ImageHeaderParser> paramList, FileService paramFileService, ThumbnailQuery paramThumbnailQuery, ArrayPool paramArrayPool, ContentResolver paramContentResolver)
  {
    this.service = paramFileService;
    this.query = paramThumbnailQuery;
    this.byteArrayPool = paramArrayPool;
    this.contentResolver = paramContentResolver;
    this.parsers = paramList;
  }
  
  public ThumbnailStreamOpener(List<ImageHeaderParser> paramList, ThumbnailQuery paramThumbnailQuery, ArrayPool paramArrayPool, ContentResolver paramContentResolver)
  {
    this(paramList, DEFAULT_SERVICE, paramThumbnailQuery, paramArrayPool, paramContentResolver);
  }
  
  /* Error */
  public int getOrientation(android.net.Uri paramUri)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aconst_null
    //   7: astore_3
    //   8: aload_0
    //   9: getfield 40	com/bumptech/glide/load/data/mediastore/ThumbnailStreamOpener:contentResolver	Landroid/content/ContentResolver;
    //   12: aload_1
    //   13: invokevirtual 60	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   16: astore 6
    //   18: aload 6
    //   20: astore_3
    //   21: aload 6
    //   23: astore 4
    //   25: aload 6
    //   27: astore 5
    //   29: aload_0
    //   30: getfield 42	com/bumptech/glide/load/data/mediastore/ThumbnailStreamOpener:parsers	Ljava/util/List;
    //   33: aload 6
    //   35: aload_0
    //   36: getfield 38	com/bumptech/glide/load/data/mediastore/ThumbnailStreamOpener:byteArrayPool	Lcom/bumptech/glide/load/engine/bitmap_recycle/ArrayPool;
    //   39: invokestatic 65	com/bumptech/glide/load/ImageHeaderParserUtils:getOrientation	(Ljava/util/List;Ljava/io/InputStream;Lcom/bumptech/glide/load/engine/bitmap_recycle/ArrayPool;)I
    //   42: istore_2
    //   43: aload 6
    //   45: ifnull +8 -> 53
    //   48: aload 6
    //   50: invokevirtual 70	java/io/InputStream:close	()V
    //   53: iload_2
    //   54: ireturn
    //   55: astore 6
    //   57: aload_3
    //   58: astore 4
    //   60: ldc 10
    //   62: iconst_3
    //   63: invokestatic 76	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   66: ifeq +33 -> 99
    //   69: aload_3
    //   70: astore 4
    //   72: ldc 10
    //   74: new 78	java/lang/StringBuilder
    //   77: dup
    //   78: invokespecial 79	java/lang/StringBuilder:<init>	()V
    //   81: ldc 81
    //   83: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: aload_1
    //   87: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   90: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: aload 6
    //   95: invokestatic 96	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   98: pop
    //   99: aload_3
    //   100: ifnull +7 -> 107
    //   103: aload_3
    //   104: invokevirtual 70	java/io/InputStream:close	()V
    //   107: iconst_m1
    //   108: ireturn
    //   109: astore_1
    //   110: aload 4
    //   112: ifnull +8 -> 120
    //   115: aload 4
    //   117: invokevirtual 70	java/io/InputStream:close	()V
    //   120: aload_1
    //   121: athrow
    //   122: astore 6
    //   124: aload 5
    //   126: astore_3
    //   127: goto -70 -> 57
    //   130: astore_1
    //   131: iload_2
    //   132: ireturn
    //   133: astore_1
    //   134: goto -27 -> 107
    //   137: astore_3
    //   138: goto -18 -> 120
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	this	ThumbnailStreamOpener
    //   0	141	1	paramUri	android.net.Uri
    //   42	90	2	i	int
    //   7	120	3	localObject1	Object
    //   137	1	3	localIOException1	java.io.IOException
    //   1	115	4	localObject2	Object
    //   4	121	5	localObject3	Object
    //   16	33	6	localInputStream	java.io.InputStream
    //   55	39	6	localIOException2	java.io.IOException
    //   122	1	6	localNullPointerException	NullPointerException
    // Exception table:
    //   from	to	target	type
    //   8	18	55	java/io/IOException
    //   29	43	55	java/io/IOException
    //   8	18	109	finally
    //   29	43	109	finally
    //   60	69	109	finally
    //   72	99	109	finally
    //   8	18	122	java/lang/NullPointerException
    //   29	43	122	java/lang/NullPointerException
    //   48	53	130	java/io/IOException
    //   103	107	133	java/io/IOException
    //   115	120	137	java/io/IOException
  }
  
  /* Error */
  public java.io.InputStream open(android.net.Uri paramUri)
    throws java.io.FileNotFoundException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aload_0
    //   6: getfield 36	com/bumptech/glide/load/data/mediastore/ThumbnailStreamOpener:query	Lcom/bumptech/glide/load/data/mediastore/ThumbnailQuery;
    //   9: aload_1
    //   10: invokeinterface 104 2 0
    //   15: astore 5
    //   17: aload 5
    //   19: ifnull +15 -> 34
    //   22: aload 5
    //   24: invokeinterface 110 1 0
    //   29: istore_2
    //   30: iload_2
    //   31: ifne +17 -> 48
    //   34: aload 5
    //   36: ifnull +10 -> 46
    //   39: aload 5
    //   41: invokeinterface 111 1 0
    //   46: aconst_null
    //   47: areturn
    //   48: aload 5
    //   50: iconst_0
    //   51: invokeinterface 115 2 0
    //   56: astore_1
    //   57: aload_1
    //   58: invokestatic 121	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   61: istore_2
    //   62: iload_2
    //   63: ifeq +17 -> 80
    //   66: aload 5
    //   68: ifnull -22 -> 46
    //   71: aload 5
    //   73: invokeinterface 111 1 0
    //   78: aconst_null
    //   79: areturn
    //   80: aload_0
    //   81: getfield 34	com/bumptech/glide/load/data/mediastore/ThumbnailStreamOpener:service	Lcom/bumptech/glide/load/data/mediastore/FileService;
    //   84: aload_1
    //   85: invokevirtual 125	com/bumptech/glide/load/data/mediastore/FileService:get	(Ljava/lang/String;)Ljava/io/File;
    //   88: astore 6
    //   90: aload 4
    //   92: astore_1
    //   93: aload_0
    //   94: getfield 34	com/bumptech/glide/load/data/mediastore/ThumbnailStreamOpener:service	Lcom/bumptech/glide/load/data/mediastore/FileService;
    //   97: aload 6
    //   99: invokevirtual 129	com/bumptech/glide/load/data/mediastore/FileService:exists	(Ljava/io/File;)Z
    //   102: ifeq +26 -> 128
    //   105: aload 4
    //   107: astore_1
    //   108: aload_0
    //   109: getfield 34	com/bumptech/glide/load/data/mediastore/ThumbnailStreamOpener:service	Lcom/bumptech/glide/load/data/mediastore/FileService;
    //   112: aload 6
    //   114: invokevirtual 133	com/bumptech/glide/load/data/mediastore/FileService:length	(Ljava/io/File;)J
    //   117: lconst_0
    //   118: lcmp
    //   119: ifle +9 -> 128
    //   122: aload 6
    //   124: invokestatic 139	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
    //   127: astore_1
    //   128: aload 5
    //   130: ifnull +10 -> 140
    //   133: aload 5
    //   135: invokeinterface 111 1 0
    //   140: aload_1
    //   141: ifnull +12 -> 153
    //   144: aload_0
    //   145: getfield 40	com/bumptech/glide/load/data/mediastore/ThumbnailStreamOpener:contentResolver	Landroid/content/ContentResolver;
    //   148: aload_1
    //   149: invokevirtual 60	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   152: astore_3
    //   153: aload_3
    //   154: areturn
    //   155: astore_1
    //   156: aload 5
    //   158: ifnull +10 -> 168
    //   161: aload 5
    //   163: invokeinterface 111 1 0
    //   168: aload_1
    //   169: athrow
    //   170: astore_3
    //   171: new 99	java/io/FileNotFoundException
    //   174: dup
    //   175: new 78	java/lang/StringBuilder
    //   178: dup
    //   179: invokespecial 79	java/lang/StringBuilder:<init>	()V
    //   182: ldc -115
    //   184: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: aload_1
    //   188: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   191: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   194: invokespecial 144	java/io/FileNotFoundException:<init>	(Ljava/lang/String;)V
    //   197: aload_3
    //   198: invokevirtual 148	java/io/FileNotFoundException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   201: checkcast 99	java/io/FileNotFoundException
    //   204: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	205	0	this	ThumbnailStreamOpener
    //   0	205	1	paramUri	android.net.Uri
    //   29	34	2	bool	boolean
    //   4	150	3	localInputStream	java.io.InputStream
    //   170	28	3	localNullPointerException	NullPointerException
    //   1	105	4	localObject	Object
    //   15	147	5	localCursor	android.database.Cursor
    //   88	35	6	localFile	java.io.File
    // Exception table:
    //   from	to	target	type
    //   22	30	155	finally
    //   48	62	155	finally
    //   80	90	155	finally
    //   93	105	155	finally
    //   108	128	155	finally
    //   144	153	170	java/lang/NullPointerException
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\data\mediastore\ThumbnailStreamOpener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */