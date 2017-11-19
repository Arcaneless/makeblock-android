package com.unity3d.player;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class UnityWebRequest
  implements Runnable
{
  private long a;
  private String b;
  private String c;
  private Map d;
  private int e;
  private long f;
  
  UnityWebRequest(long paramLong, String paramString1, Map paramMap, String paramString2, int paramInt)
  {
    this.a = paramLong;
    this.b = paramString2;
    this.c = paramString1;
    this.d = paramMap;
    this.e = paramInt;
  }
  
  private static native void contentLengthCallback(long paramLong, int paramInt);
  
  private static native boolean downloadCallback(long paramLong, ByteBuffer paramByteBuffer, int paramInt);
  
  private static native void errorCallback(long paramLong, int paramInt, String paramString);
  
  private boolean hasTimedOut()
  {
    if (this.e <= 0) {}
    while (System.currentTimeMillis() - this.f < this.e) {
      return false;
    }
    return true;
  }
  
  private static native void headerCallback(long paramLong, String paramString1, String paramString2);
  
  private static native void responseCodeCallback(long paramLong, int paramInt);
  
  private static native int uploadCallback(long paramLong, ByteBuffer paramByteBuffer);
  
  protected void badProtocolCallback(String paramString)
  {
    errorCallback(this.a, 4, paramString);
  }
  
  protected void contentLengthCallback(int paramInt)
  {
    contentLengthCallback(this.a, paramInt);
  }
  
  protected boolean downloadCallback(ByteBuffer paramByteBuffer, int paramInt)
  {
    return downloadCallback(this.a, paramByteBuffer, paramInt);
  }
  
  protected void errorCallback(String paramString)
  {
    errorCallback(this.a, 2, paramString);
  }
  
  protected void headerCallback(String paramString1, String paramString2)
  {
    headerCallback(this.a, paramString1, paramString2);
  }
  
  protected void headerCallback(Map paramMap)
  {
    if ((paramMap == null) || (paramMap.size() == 0)) {
      return;
    }
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject = (String)localEntry.getKey();
      paramMap = (Map)localObject;
      if (localObject == null) {
        paramMap = "Status";
      }
      localObject = ((List)localEntry.getValue()).iterator();
      while (((Iterator)localObject).hasNext()) {
        headerCallback(paramMap, (String)((Iterator)localObject).next());
      }
    }
  }
  
  protected void malformattedUrlCallback(String paramString)
  {
    errorCallback(this.a, 5, paramString);
  }
  
  protected void responseCodeCallback(int paramInt)
  {
    responseCodeCallback(this.a, paramInt);
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 46	java/lang/System:currentTimeMillis	()J
    //   4: putfield 48	com/unity3d/player/UnityWebRequest:f	J
    //   7: new 129	java/net/URL
    //   10: dup
    //   11: aload_0
    //   12: getfield 25	com/unity3d/player/UnityWebRequest:b	Ljava/lang/String;
    //   15: invokespecial 131	java/net/URL:<init>	(Ljava/lang/String;)V
    //   18: astore_2
    //   19: aload_2
    //   20: invokevirtual 135	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   23: astore 4
    //   25: aload 4
    //   27: aload_0
    //   28: getfield 31	com/unity3d/player/UnityWebRequest:e	I
    //   31: invokevirtual 140	java/net/URLConnection:setConnectTimeout	(I)V
    //   34: aload 4
    //   36: aload_0
    //   37: getfield 31	com/unity3d/player/UnityWebRequest:e	I
    //   40: invokevirtual 143	java/net/URLConnection:setReadTimeout	(I)V
    //   43: aload 4
    //   45: instanceof 145
    //   48: ifeq +20 -> 68
    //   51: invokestatic 150	com/unity3d/player/a:a	()Ljavax/net/ssl/SSLSocketFactory;
    //   54: astore_3
    //   55: aload_3
    //   56: ifnull +12 -> 68
    //   59: aload 4
    //   61: checkcast 145	javax/net/ssl/HttpsURLConnection
    //   64: aload_3
    //   65: invokevirtual 154	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   68: aload_2
    //   69: invokevirtual 158	java/net/URL:getProtocol	()Ljava/lang/String;
    //   72: ldc -96
    //   74: invokevirtual 164	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   77: ifeq +40 -> 117
    //   80: aload_2
    //   81: invokevirtual 167	java/net/URL:getHost	()Ljava/lang/String;
    //   84: invokevirtual 170	java/lang/String:isEmpty	()Z
    //   87: ifne +30 -> 117
    //   90: aload_0
    //   91: ldc -84
    //   93: invokevirtual 174	com/unity3d/player/UnityWebRequest:malformattedUrlCallback	(Ljava/lang/String;)V
    //   96: return
    //   97: astore_2
    //   98: aload_0
    //   99: aload_2
    //   100: invokevirtual 177	java/net/MalformedURLException:toString	()Ljava/lang/String;
    //   103: invokevirtual 174	com/unity3d/player/UnityWebRequest:malformattedUrlCallback	(Ljava/lang/String;)V
    //   106: return
    //   107: astore_2
    //   108: aload_0
    //   109: aload_2
    //   110: invokevirtual 178	java/io/IOException:toString	()Ljava/lang/String;
    //   113: invokevirtual 180	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   116: return
    //   117: aload 4
    //   119: instanceof 182
    //   122: ifeq +10 -> 132
    //   125: aload_0
    //   126: ldc -72
    //   128: invokevirtual 186	com/unity3d/player/UnityWebRequest:badProtocolCallback	(Ljava/lang/String;)V
    //   131: return
    //   132: aload 4
    //   134: instanceof 188
    //   137: ifeq +22 -> 159
    //   140: aload 4
    //   142: checkcast 188	java/net/HttpURLConnection
    //   145: astore_2
    //   146: aload_2
    //   147: aload_0
    //   148: getfield 27	com/unity3d/player/UnityWebRequest:c	Ljava/lang/String;
    //   151: invokevirtual 191	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   154: aload_2
    //   155: iconst_0
    //   156: invokevirtual 195	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   159: aload_0
    //   160: getfield 29	com/unity3d/player/UnityWebRequest:d	Ljava/util/Map;
    //   163: ifnull +73 -> 236
    //   166: aload_0
    //   167: getfield 29	com/unity3d/player/UnityWebRequest:d	Ljava/util/Map;
    //   170: invokeinterface 77 1 0
    //   175: invokeinterface 83 1 0
    //   180: astore_2
    //   181: aload_2
    //   182: invokeinterface 88 1 0
    //   187: ifeq +49 -> 236
    //   190: aload_2
    //   191: invokeinterface 92 1 0
    //   196: checkcast 94	java/util/Map$Entry
    //   199: astore_3
    //   200: aload 4
    //   202: aload_3
    //   203: invokeinterface 97 1 0
    //   208: checkcast 99	java/lang/String
    //   211: aload_3
    //   212: invokeinterface 104 1 0
    //   217: checkcast 99	java/lang/String
    //   220: invokevirtual 198	java/net/URLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   223: goto -42 -> 181
    //   226: astore_2
    //   227: aload_0
    //   228: aload_2
    //   229: invokevirtual 199	java/net/ProtocolException:toString	()Ljava/lang/String;
    //   232: invokevirtual 186	com/unity3d/player/UnityWebRequest:badProtocolCallback	(Ljava/lang/String;)V
    //   235: return
    //   236: ldc -56
    //   238: invokestatic 206	java/nio/ByteBuffer:allocateDirect	(I)Ljava/nio/ByteBuffer;
    //   241: astore 5
    //   243: aload_0
    //   244: aconst_null
    //   245: invokevirtual 209	com/unity3d/player/UnityWebRequest:uploadCallback	(Ljava/nio/ByteBuffer;)I
    //   248: ifle +84 -> 332
    //   251: aload 4
    //   253: iconst_1
    //   254: invokevirtual 212	java/net/URLConnection:setDoOutput	(Z)V
    //   257: aload 4
    //   259: invokevirtual 216	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   262: astore_2
    //   263: aload_0
    //   264: aload 5
    //   266: invokevirtual 209	com/unity3d/player/UnityWebRequest:uploadCallback	(Ljava/nio/ByteBuffer;)I
    //   269: istore_1
    //   270: iload_1
    //   271: ifle +61 -> 332
    //   274: aload_0
    //   275: invokespecial 218	com/unity3d/player/UnityWebRequest:hasTimedOut	()Z
    //   278: ifeq +29 -> 307
    //   281: aload_2
    //   282: invokevirtual 223	java/io/OutputStream:close	()V
    //   285: aload_0
    //   286: getfield 23	com/unity3d/player/UnityWebRequest:a	J
    //   289: bipush 14
    //   291: ldc -31
    //   293: invokestatic 57	com/unity3d/player/UnityWebRequest:errorCallback	(JILjava/lang/String;)V
    //   296: return
    //   297: astore_2
    //   298: aload_0
    //   299: aload_2
    //   300: invokevirtual 226	java/lang/Exception:toString	()Ljava/lang/String;
    //   303: invokevirtual 180	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   306: return
    //   307: aload_2
    //   308: aload 5
    //   310: invokevirtual 230	java/nio/ByteBuffer:array	()[B
    //   313: aload 5
    //   315: invokevirtual 233	java/nio/ByteBuffer:arrayOffset	()I
    //   318: iload_1
    //   319: invokevirtual 237	java/io/OutputStream:write	([BII)V
    //   322: aload_0
    //   323: aload 5
    //   325: invokevirtual 209	com/unity3d/player/UnityWebRequest:uploadCallback	(Ljava/nio/ByteBuffer;)I
    //   328: istore_1
    //   329: goto -59 -> 270
    //   332: aload 4
    //   334: instanceof 188
    //   337: ifeq +17 -> 354
    //   340: aload 4
    //   342: checkcast 188	java/net/HttpURLConnection
    //   345: astore_2
    //   346: aload_0
    //   347: aload_2
    //   348: invokevirtual 240	java/net/HttpURLConnection:getResponseCode	()I
    //   351: invokevirtual 242	com/unity3d/player/UnityWebRequest:responseCodeCallback	(I)V
    //   354: aload 4
    //   356: invokevirtual 246	java/net/URLConnection:getHeaderFields	()Ljava/util/Map;
    //   359: astore_2
    //   360: aload_0
    //   361: aload_2
    //   362: invokevirtual 248	com/unity3d/player/UnityWebRequest:headerCallback	(Ljava/util/Map;)V
    //   365: aload_2
    //   366: ifnull +14 -> 380
    //   369: aload_2
    //   370: ldc -6
    //   372: invokeinterface 254 2 0
    //   377: ifne +26 -> 403
    //   380: aload 4
    //   382: invokevirtual 257	java/net/URLConnection:getContentLength	()I
    //   385: iconst_m1
    //   386: if_icmpeq +17 -> 403
    //   389: aload_0
    //   390: ldc -6
    //   392: aload 4
    //   394: invokevirtual 257	java/net/URLConnection:getContentLength	()I
    //   397: invokestatic 261	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   400: invokevirtual 109	com/unity3d/player/UnityWebRequest:headerCallback	(Ljava/lang/String;Ljava/lang/String;)V
    //   403: aload_2
    //   404: ifnull +15 -> 419
    //   407: aload_2
    //   408: ldc_w 263
    //   411: invokeinterface 254 2 0
    //   416: ifne +23 -> 439
    //   419: aload 4
    //   421: invokevirtual 266	java/net/URLConnection:getContentType	()Ljava/lang/String;
    //   424: ifnull +15 -> 439
    //   427: aload_0
    //   428: ldc_w 263
    //   431: aload 4
    //   433: invokevirtual 266	java/net/URLConnection:getContentType	()Ljava/lang/String;
    //   436: invokevirtual 109	com/unity3d/player/UnityWebRequest:headerCallback	(Ljava/lang/String;Ljava/lang/String;)V
    //   439: aload 4
    //   441: invokevirtual 257	java/net/URLConnection:getContentLength	()I
    //   444: istore_1
    //   445: iload_1
    //   446: ifle +8 -> 454
    //   449: aload_0
    //   450: iload_1
    //   451: invokevirtual 268	com/unity3d/player/UnityWebRequest:contentLengthCallback	(I)V
    //   454: aload 4
    //   456: instanceof 188
    //   459: ifeq +210 -> 669
    //   462: aload 4
    //   464: checkcast 188	java/net/HttpURLConnection
    //   467: astore_2
    //   468: aload_0
    //   469: aload_2
    //   470: invokevirtual 240	java/net/HttpURLConnection:getResponseCode	()I
    //   473: invokevirtual 242	com/unity3d/player/UnityWebRequest:responseCodeCallback	(I)V
    //   476: aload_2
    //   477: invokevirtual 272	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   480: astore_2
    //   481: aload_2
    //   482: astore_3
    //   483: aload_2
    //   484: ifnonnull +9 -> 493
    //   487: aload 4
    //   489: invokevirtual 275	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   492: astore_3
    //   493: aload_3
    //   494: invokestatic 281	java/nio/channels/Channels:newChannel	(Ljava/io/InputStream;)Ljava/nio/channels/ReadableByteChannel;
    //   497: astore_2
    //   498: aload_2
    //   499: aload 5
    //   501: invokeinterface 286 2 0
    //   506: istore_1
    //   507: iload_1
    //   508: iconst_m1
    //   509: if_icmpeq +103 -> 612
    //   512: aload_0
    //   513: invokespecial 218	com/unity3d/player/UnityWebRequest:hasTimedOut	()Z
    //   516: ifeq +68 -> 584
    //   519: aload_2
    //   520: invokeinterface 287 1 0
    //   525: aload_0
    //   526: getfield 23	com/unity3d/player/UnityWebRequest:a	J
    //   529: bipush 14
    //   531: ldc -31
    //   533: invokestatic 57	com/unity3d/player/UnityWebRequest:errorCallback	(JILjava/lang/String;)V
    //   536: return
    //   537: astore_2
    //   538: aload_0
    //   539: aload_2
    //   540: invokevirtual 288	java/net/UnknownHostException:toString	()Ljava/lang/String;
    //   543: invokevirtual 291	com/unity3d/player/UnityWebRequest:unknownHostCallback	(Ljava/lang/String;)V
    //   546: return
    //   547: astore_2
    //   548: aload_0
    //   549: aload_2
    //   550: invokevirtual 288	java/net/UnknownHostException:toString	()Ljava/lang/String;
    //   553: invokevirtual 291	com/unity3d/player/UnityWebRequest:unknownHostCallback	(Ljava/lang/String;)V
    //   556: goto -202 -> 354
    //   559: astore_2
    //   560: aload_0
    //   561: getfield 23	com/unity3d/player/UnityWebRequest:a	J
    //   564: bipush 14
    //   566: aload_2
    //   567: invokevirtual 292	java/net/SocketTimeoutException:toString	()Ljava/lang/String;
    //   570: invokestatic 57	com/unity3d/player/UnityWebRequest:errorCallback	(JILjava/lang/String;)V
    //   573: return
    //   574: astore_2
    //   575: aload_0
    //   576: aload_2
    //   577: invokevirtual 178	java/io/IOException:toString	()Ljava/lang/String;
    //   580: invokevirtual 180	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   583: return
    //   584: aload_0
    //   585: aload 5
    //   587: iload_1
    //   588: invokevirtual 294	com/unity3d/player/UnityWebRequest:downloadCallback	(Ljava/nio/ByteBuffer;I)Z
    //   591: ifeq +21 -> 612
    //   594: aload 5
    //   596: invokevirtual 298	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   599: pop
    //   600: aload_2
    //   601: aload 5
    //   603: invokeinterface 286 2 0
    //   608: istore_1
    //   609: goto -102 -> 507
    //   612: aload_2
    //   613: invokeinterface 287 1 0
    //   618: return
    //   619: astore_2
    //   620: aload_0
    //   621: aload_2
    //   622: invokevirtual 299	javax/net/ssl/SSLHandshakeException:toString	()Ljava/lang/String;
    //   625: invokevirtual 302	com/unity3d/player/UnityWebRequest:sslCannotConnectCallback	(Ljava/lang/String;)V
    //   628: return
    //   629: astore_2
    //   630: aload_0
    //   631: getfield 23	com/unity3d/player/UnityWebRequest:a	J
    //   634: bipush 14
    //   636: aload_2
    //   637: invokevirtual 292	java/net/SocketTimeoutException:toString	()Ljava/lang/String;
    //   640: invokestatic 57	com/unity3d/player/UnityWebRequest:errorCallback	(JILjava/lang/String;)V
    //   643: return
    //   644: astore_2
    //   645: aload_0
    //   646: getfield 23	com/unity3d/player/UnityWebRequest:a	J
    //   649: bipush 14
    //   651: aload_2
    //   652: invokevirtual 178	java/io/IOException:toString	()Ljava/lang/String;
    //   655: invokestatic 57	com/unity3d/player/UnityWebRequest:errorCallback	(JILjava/lang/String;)V
    //   658: return
    //   659: astore_2
    //   660: aload_0
    //   661: aload_2
    //   662: invokevirtual 226	java/lang/Exception:toString	()Ljava/lang/String;
    //   665: invokevirtual 180	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   668: return
    //   669: aconst_null
    //   670: astore_2
    //   671: goto -190 -> 481
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	674	0	this	UnityWebRequest
    //   269	340	1	i	int
    //   18	63	2	localURL	java.net.URL
    //   97	3	2	localMalformedURLException	java.net.MalformedURLException
    //   107	3	2	localIOException1	java.io.IOException
    //   145	46	2	localObject1	Object
    //   226	3	2	localProtocolException	java.net.ProtocolException
    //   262	20	2	localOutputStream	java.io.OutputStream
    //   297	11	2	localException1	Exception
    //   345	175	2	localObject2	Object
    //   537	3	2	localUnknownHostException1	java.net.UnknownHostException
    //   547	3	2	localUnknownHostException2	java.net.UnknownHostException
    //   559	8	2	localSocketTimeoutException1	java.net.SocketTimeoutException
    //   574	39	2	localIOException2	java.io.IOException
    //   619	3	2	localSSLHandshakeException	javax.net.ssl.SSLHandshakeException
    //   629	8	2	localSocketTimeoutException2	java.net.SocketTimeoutException
    //   644	8	2	localIOException3	java.io.IOException
    //   659	3	2	localException2	Exception
    //   670	1	2	localObject3	Object
    //   54	440	3	localObject4	Object
    //   23	465	4	localURLConnection	java.net.URLConnection
    //   241	361	5	localByteBuffer	ByteBuffer
    // Exception table:
    //   from	to	target	type
    //   7	55	97	java/net/MalformedURLException
    //   59	68	97	java/net/MalformedURLException
    //   7	55	107	java/io/IOException
    //   59	68	107	java/io/IOException
    //   140	159	226	java/net/ProtocolException
    //   257	270	297	java/lang/Exception
    //   274	296	297	java/lang/Exception
    //   307	329	297	java/lang/Exception
    //   454	481	537	java/net/UnknownHostException
    //   487	493	537	java/net/UnknownHostException
    //   493	507	537	java/net/UnknownHostException
    //   512	536	537	java/net/UnknownHostException
    //   584	609	537	java/net/UnknownHostException
    //   612	618	537	java/net/UnknownHostException
    //   346	354	547	java/net/UnknownHostException
    //   346	354	559	java/net/SocketTimeoutException
    //   346	354	574	java/io/IOException
    //   454	481	619	javax/net/ssl/SSLHandshakeException
    //   487	493	619	javax/net/ssl/SSLHandshakeException
    //   493	507	619	javax/net/ssl/SSLHandshakeException
    //   512	536	619	javax/net/ssl/SSLHandshakeException
    //   584	609	619	javax/net/ssl/SSLHandshakeException
    //   612	618	619	javax/net/ssl/SSLHandshakeException
    //   454	481	629	java/net/SocketTimeoutException
    //   487	493	629	java/net/SocketTimeoutException
    //   493	507	629	java/net/SocketTimeoutException
    //   512	536	629	java/net/SocketTimeoutException
    //   584	609	629	java/net/SocketTimeoutException
    //   612	618	629	java/net/SocketTimeoutException
    //   454	481	644	java/io/IOException
    //   487	493	644	java/io/IOException
    //   493	507	644	java/io/IOException
    //   512	536	644	java/io/IOException
    //   584	609	644	java/io/IOException
    //   612	618	644	java/io/IOException
    //   454	481	659	java/lang/Exception
    //   487	493	659	java/lang/Exception
    //   493	507	659	java/lang/Exception
    //   512	536	659	java/lang/Exception
    //   584	609	659	java/lang/Exception
    //   612	618	659	java/lang/Exception
  }
  
  protected void sslCannotConnectCallback(String paramString)
  {
    errorCallback(this.a, 16, paramString);
  }
  
  protected void unknownHostCallback(String paramString)
  {
    errorCallback(this.a, 7, paramString);
  }
  
  protected int uploadCallback(ByteBuffer paramByteBuffer)
  {
    return uploadCallback(this.a, paramByteBuffer);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\unity3d\player\UnityWebRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */