package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b
{
  public static boolean a = false;
  private static String b = "lxd";
  private static int c = 1;
  private static boolean d = true;
  private static boolean e = false;
  private static boolean f = true;
  private static int g = 1;
  private static int h = 0;
  private static final String i = "act" + b;
  private static final String j = "evn" + b;
  private static final String k = "esp" + b;
  private static final String l = "err" + b;
  private static final String m = "sys" + b;
  private static String n = "";
  private static long o = 0L;
  private static String p = "";
  private static Object q = new Object();
  private static Object r = new Object();
  private static Object s = new Object();
  private static boolean t = false;
  private static boolean u = false;
  private static String v = "";
  private static ExecutorService w = Executors.newSingleThreadExecutor();
  
  /* Error */
  private static int a(Context paramContext, String paramString, JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 6
    //   3: iconst_0
    //   4: istore 7
    //   6: iconst_0
    //   7: istore 8
    //   9: iconst_0
    //   10: istore_3
    //   11: iconst_0
    //   12: istore 9
    //   14: iconst_0
    //   15: istore 10
    //   17: iconst_0
    //   18: istore 11
    //   20: iconst_0
    //   21: istore 4
    //   23: iconst_2
    //   24: istore 5
    //   26: getstatic 43	com/iflytek/cloud/thirdparty/b:c	I
    //   29: iconst_1
    //   30: if_icmpeq +5 -> 35
    //   33: iconst_2
    //   34: ireturn
    //   35: ldc -127
    //   37: new 55	java/lang/StringBuilder
    //   40: dup
    //   41: ldc -125
    //   43: invokespecial 61	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   46: aload_1
    //   47: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: ldc -123
    //   52: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: aload_0
    //   56: invokevirtual 137	java/lang/Object:getClass	()Ljava/lang/Class;
    //   59: invokevirtual 142	java/lang/Class:getName	()Ljava/lang/String;
    //   62: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: ldc -112
    //   67: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload_2
    //   71: invokevirtual 147	org/json/JSONObject:toString	()Ljava/lang/String;
    //   74: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   80: invokestatic 152	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   83: aconst_null
    //   84: astore 14
    //   86: aconst_null
    //   87: astore 15
    //   89: aconst_null
    //   90: astore 16
    //   92: aconst_null
    //   93: astore 17
    //   95: aconst_null
    //   96: astore 13
    //   98: aload 17
    //   100: astore 12
    //   102: aload_0
    //   103: invokestatic 157	com/iflytek/cloud/thirdparty/h:h	(Landroid/content/Context;)Ljava/lang/String;
    //   106: astore 18
    //   108: aload 17
    //   110: astore 12
    //   112: aload_0
    //   113: invokestatic 159	com/iflytek/cloud/thirdparty/h:i	(Landroid/content/Context;)Ljava/lang/String;
    //   116: ldc -95
    //   118: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   121: astore 19
    //   123: aload 17
    //   125: astore 12
    //   127: new 55	java/lang/StringBuilder
    //   130: dup
    //   131: aload_1
    //   132: invokestatic 173	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   135: invokespecial 61	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   138: ldc -81
    //   140: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aload 18
    //   145: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: ldc -79
    //   150: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: aload 19
    //   155: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: ldc -77
    //   160: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: bipush 106
    //   165: invokevirtual 182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   168: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   171: astore_1
    //   172: aload 17
    //   174: astore 12
    //   176: ldc -127
    //   178: aload_2
    //   179: invokevirtual 147	org/json/JSONObject:toString	()Ljava/lang/String;
    //   182: invokestatic 152	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   185: aload 17
    //   187: astore 12
    //   189: ldc -127
    //   191: ldc -72
    //   193: invokestatic 152	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   196: aload 17
    //   198: astore 12
    //   200: new 186	java/net/URL
    //   203: dup
    //   204: aload_1
    //   205: invokespecial 187	java/net/URL:<init>	(Ljava/lang/String;)V
    //   208: invokevirtual 191	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   211: checkcast 193	java/net/HttpURLConnection
    //   214: astore_1
    //   215: iload_3
    //   216: istore 4
    //   218: iload 9
    //   220: istore 6
    //   222: iload 10
    //   224: istore 7
    //   226: iload 11
    //   228: istore 8
    //   230: aload_1
    //   231: sipush 10000
    //   234: invokevirtual 197	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   237: iload_3
    //   238: istore 4
    //   240: iload 9
    //   242: istore 6
    //   244: iload 10
    //   246: istore 7
    //   248: iload 11
    //   250: istore 8
    //   252: aload_1
    //   253: iconst_1
    //   254: invokevirtual 201	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   257: iload_3
    //   258: istore 4
    //   260: iload 9
    //   262: istore 6
    //   264: iload 10
    //   266: istore 7
    //   268: iload 11
    //   270: istore 8
    //   272: aload_1
    //   273: iconst_1
    //   274: invokevirtual 204	java/net/HttpURLConnection:setDoInput	(Z)V
    //   277: iload_3
    //   278: istore 4
    //   280: iload 9
    //   282: istore 6
    //   284: iload 10
    //   286: istore 7
    //   288: iload 11
    //   290: istore 8
    //   292: aload_1
    //   293: iconst_0
    //   294: invokevirtual 207	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   297: iload_3
    //   298: istore 4
    //   300: iload 9
    //   302: istore 6
    //   304: iload 10
    //   306: istore 7
    //   308: iload 11
    //   310: istore 8
    //   312: aload_1
    //   313: ldc -47
    //   315: invokevirtual 212	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   318: iload_3
    //   319: istore 4
    //   321: iload 9
    //   323: istore 6
    //   325: iload 10
    //   327: istore 7
    //   329: iload 11
    //   331: istore 8
    //   333: invokestatic 217	com/iflytek/cloud/thirdparty/g:a	()Lcom/iflytek/cloud/thirdparty/g;
    //   336: aload_2
    //   337: invokevirtual 147	org/json/JSONObject:toString	()Ljava/lang/String;
    //   340: invokevirtual 220	com/iflytek/cloud/thirdparty/g:a	(Ljava/lang/String;)[B
    //   343: astore 12
    //   345: iload_3
    //   346: istore 4
    //   348: iload 9
    //   350: istore 6
    //   352: iload 10
    //   354: istore 7
    //   356: iload 11
    //   358: istore 8
    //   360: aload_1
    //   361: ldc -34
    //   363: new 55	java/lang/StringBuilder
    //   366: dup
    //   367: invokespecial 223	java/lang/StringBuilder:<init>	()V
    //   370: aload 12
    //   372: arraylength
    //   373: invokevirtual 182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   376: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   379: invokevirtual 226	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   382: iload_3
    //   383: istore 4
    //   385: iload 9
    //   387: istore 6
    //   389: iload 10
    //   391: istore 7
    //   393: iload 11
    //   395: istore 8
    //   397: aload_1
    //   398: ldc -28
    //   400: ldc -26
    //   402: invokevirtual 226	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   405: iload_3
    //   406: istore 4
    //   408: iload 9
    //   410: istore 6
    //   412: iload 10
    //   414: istore 7
    //   416: iload 11
    //   418: istore 8
    //   420: aload_1
    //   421: ldc -24
    //   423: ldc -95
    //   425: invokevirtual 226	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   428: iload_3
    //   429: istore 4
    //   431: iload 9
    //   433: istore 6
    //   435: iload 10
    //   437: istore 7
    //   439: iload 11
    //   441: istore 8
    //   443: aload_1
    //   444: invokevirtual 236	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   447: astore 13
    //   449: iload_3
    //   450: istore 4
    //   452: iload 9
    //   454: istore 6
    //   456: iload 10
    //   458: istore 7
    //   460: iload 11
    //   462: istore 8
    //   464: aload 13
    //   466: aload 12
    //   468: invokevirtual 242	java/io/OutputStream:write	([B)V
    //   471: iload_3
    //   472: istore 4
    //   474: iload 9
    //   476: istore 6
    //   478: iload 10
    //   480: istore 7
    //   482: iload 11
    //   484: istore 8
    //   486: aload 13
    //   488: invokevirtual 245	java/io/OutputStream:close	()V
    //   491: iload_3
    //   492: istore 4
    //   494: iload 9
    //   496: istore 6
    //   498: iload 10
    //   500: istore 7
    //   502: iload 11
    //   504: istore 8
    //   506: aload_1
    //   507: invokevirtual 249	java/net/HttpURLConnection:getResponseCode	()I
    //   510: istore_3
    //   511: iload_3
    //   512: istore 4
    //   514: iload_3
    //   515: istore 6
    //   517: iload_3
    //   518: istore 7
    //   520: iload_3
    //   521: istore 8
    //   523: ldc -127
    //   525: ldc -5
    //   527: invokestatic 152	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   530: iload 5
    //   532: istore 6
    //   534: sipush 200
    //   537: iload_3
    //   538: if_icmpne +22 -> 560
    //   541: iload_3
    //   542: istore 6
    //   544: iload_3
    //   545: istore 7
    //   547: iload_3
    //   548: istore 8
    //   550: ldc -3
    //   552: ldc -1
    //   554: invokestatic 152	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   557: iconst_1
    //   558: istore 6
    //   560: iload 6
    //   562: istore 5
    //   564: iload_3
    //   565: istore 4
    //   567: aload_1
    //   568: ifnull +14 -> 582
    //   571: aload_1
    //   572: invokevirtual 258	java/net/HttpURLConnection:disconnect	()V
    //   575: iload_3
    //   576: istore 4
    //   578: iload 6
    //   580: istore 5
    //   582: iload 4
    //   584: istore_3
    //   585: iload 5
    //   587: istore 4
    //   589: ldc_w 260
    //   592: new 55	java/lang/StringBuilder
    //   595: dup
    //   596: ldc_w 262
    //   599: invokespecial 61	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   602: iload_3
    //   603: invokevirtual 182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   606: ldc_w 264
    //   609: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   612: iload 4
    //   614: invokevirtual 182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   617: ldc_w 266
    //   620: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: aload_2
    //   624: ldc_w 268
    //   627: invokevirtual 272	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   630: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   633: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   636: invokestatic 152	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   639: ldc -127
    //   641: new 55	java/lang/StringBuilder
    //   644: dup
    //   645: ldc_w 274
    //   648: invokespecial 61	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   651: aload_0
    //   652: invokevirtual 137	java/lang/Object:getClass	()Ljava/lang/Class;
    //   655: invokevirtual 142	java/lang/Class:getName	()Ljava/lang/String;
    //   658: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   661: ldc_w 276
    //   664: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   667: iload 4
    //   669: invokevirtual 182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   672: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   675: invokestatic 152	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   678: iload 4
    //   680: ireturn
    //   681: astore 12
    //   683: iload 4
    //   685: istore_3
    //   686: aload 13
    //   688: astore_1
    //   689: aload 12
    //   691: astore 13
    //   693: aload_1
    //   694: astore 12
    //   696: ldc -127
    //   698: aload 13
    //   700: invokevirtual 277	org/apache/http/client/ClientProtocolException:toString	()Ljava/lang/String;
    //   703: invokestatic 152	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   706: aload_1
    //   707: astore 12
    //   709: aload 13
    //   711: invokevirtual 280	org/apache/http/client/ClientProtocolException:printStackTrace	()V
    //   714: iload_3
    //   715: istore 4
    //   717: aload_1
    //   718: ifnull -136 -> 582
    //   721: aload_1
    //   722: invokevirtual 258	java/net/HttpURLConnection:disconnect	()V
    //   725: iconst_2
    //   726: istore 4
    //   728: goto -139 -> 589
    //   731: astore 13
    //   733: iload 6
    //   735: istore_3
    //   736: aload 14
    //   738: astore_1
    //   739: aload_1
    //   740: astore 12
    //   742: ldc -127
    //   744: aload 13
    //   746: invokevirtual 281	java/io/IOException:toString	()Ljava/lang/String;
    //   749: invokestatic 152	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   752: aload_1
    //   753: astore 12
    //   755: aload 13
    //   757: invokevirtual 282	java/io/IOException:printStackTrace	()V
    //   760: iload_3
    //   761: istore 4
    //   763: aload_1
    //   764: ifnull -182 -> 582
    //   767: aload_1
    //   768: invokevirtual 258	java/net/HttpURLConnection:disconnect	()V
    //   771: iconst_2
    //   772: istore 4
    //   774: goto -185 -> 589
    //   777: astore 13
    //   779: iload 7
    //   781: istore_3
    //   782: aload 15
    //   784: astore_1
    //   785: aload_1
    //   786: astore 12
    //   788: ldc -127
    //   790: aload 13
    //   792: invokevirtual 283	org/json/JSONException:toString	()Ljava/lang/String;
    //   795: invokestatic 152	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   798: aload_1
    //   799: astore 12
    //   801: aload 13
    //   803: invokevirtual 284	org/json/JSONException:printStackTrace	()V
    //   806: iconst_3
    //   807: istore 5
    //   809: iload_3
    //   810: istore 4
    //   812: aload_1
    //   813: ifnull -231 -> 582
    //   816: aload_1
    //   817: invokevirtual 258	java/net/HttpURLConnection:disconnect	()V
    //   820: iconst_3
    //   821: istore 4
    //   823: goto -234 -> 589
    //   826: astore 13
    //   828: iload 8
    //   830: istore_3
    //   831: aload 16
    //   833: astore_1
    //   834: iload 5
    //   836: istore 6
    //   838: aload_1
    //   839: astore 12
    //   841: ldc -127
    //   843: aload 13
    //   845: invokevirtual 285	java/lang/Exception:toString	()Ljava/lang/String;
    //   848: invokestatic 152	com/iflytek/cloud/thirdparty/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   851: aload_1
    //   852: astore 12
    //   854: aload 13
    //   856: invokevirtual 286	java/lang/Exception:printStackTrace	()V
    //   859: iload 6
    //   861: istore 5
    //   863: iload_3
    //   864: istore 4
    //   866: aload_1
    //   867: ifnull -285 -> 582
    //   870: aload_1
    //   871: invokevirtual 258	java/net/HttpURLConnection:disconnect	()V
    //   874: iload 6
    //   876: istore 4
    //   878: goto -289 -> 589
    //   881: astore_0
    //   882: aload 12
    //   884: astore_1
    //   885: aload_1
    //   886: ifnull +7 -> 893
    //   889: aload_1
    //   890: invokevirtual 258	java/net/HttpURLConnection:disconnect	()V
    //   893: aload_0
    //   894: athrow
    //   895: astore_1
    //   896: goto -257 -> 639
    //   899: astore_0
    //   900: goto -15 -> 885
    //   903: astore 13
    //   905: iload 5
    //   907: istore 6
    //   909: iload 4
    //   911: istore_3
    //   912: goto -74 -> 838
    //   915: astore 13
    //   917: iconst_1
    //   918: istore 6
    //   920: goto -82 -> 838
    //   923: astore 13
    //   925: iload 6
    //   927: istore_3
    //   928: goto -143 -> 785
    //   931: astore 13
    //   933: iload 7
    //   935: istore_3
    //   936: goto -197 -> 739
    //   939: astore 13
    //   941: iload 8
    //   943: istore_3
    //   944: goto -251 -> 693
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	947	0	paramContext	Context
    //   0	947	1	paramString	String
    //   0	947	2	paramJSONObject	JSONObject
    //   10	934	3	i1	int
    //   21	889	4	i2	int
    //   24	882	5	i3	int
    //   1	925	6	i4	int
    //   4	930	7	i5	int
    //   7	935	8	i6	int
    //   12	483	9	i7	int
    //   15	484	10	i8	int
    //   18	485	11	i9	int
    //   100	367	12	localObject1	Object
    //   681	9	12	localClientProtocolException1	org.apache.http.client.ClientProtocolException
    //   694	189	12	str1	String
    //   96	614	13	localObject2	Object
    //   731	25	13	localIOException1	java.io.IOException
    //   777	25	13	localJSONException1	JSONException
    //   826	29	13	localException1	Exception
    //   903	1	13	localException2	Exception
    //   915	1	13	localException3	Exception
    //   923	1	13	localJSONException2	JSONException
    //   931	1	13	localIOException2	java.io.IOException
    //   939	1	13	localClientProtocolException2	org.apache.http.client.ClientProtocolException
    //   84	653	14	localObject3	Object
    //   87	696	15	localObject4	Object
    //   90	742	16	localObject5	Object
    //   93	104	17	localObject6	Object
    //   106	38	18	str2	String
    //   121	33	19	str3	String
    // Exception table:
    //   from	to	target	type
    //   102	108	681	org/apache/http/client/ClientProtocolException
    //   112	123	681	org/apache/http/client/ClientProtocolException
    //   127	172	681	org/apache/http/client/ClientProtocolException
    //   176	185	681	org/apache/http/client/ClientProtocolException
    //   189	196	681	org/apache/http/client/ClientProtocolException
    //   200	215	681	org/apache/http/client/ClientProtocolException
    //   102	108	731	java/io/IOException
    //   112	123	731	java/io/IOException
    //   127	172	731	java/io/IOException
    //   176	185	731	java/io/IOException
    //   189	196	731	java/io/IOException
    //   200	215	731	java/io/IOException
    //   102	108	777	org/json/JSONException
    //   112	123	777	org/json/JSONException
    //   127	172	777	org/json/JSONException
    //   176	185	777	org/json/JSONException
    //   189	196	777	org/json/JSONException
    //   200	215	777	org/json/JSONException
    //   102	108	826	java/lang/Exception
    //   112	123	826	java/lang/Exception
    //   127	172	826	java/lang/Exception
    //   176	185	826	java/lang/Exception
    //   189	196	826	java/lang/Exception
    //   200	215	826	java/lang/Exception
    //   102	108	881	finally
    //   112	123	881	finally
    //   127	172	881	finally
    //   176	185	881	finally
    //   189	196	881	finally
    //   200	215	881	finally
    //   696	706	881	finally
    //   709	714	881	finally
    //   742	752	881	finally
    //   755	760	881	finally
    //   788	798	881	finally
    //   801	806	881	finally
    //   841	851	881	finally
    //   854	859	881	finally
    //   589	639	895	java/lang/Exception
    //   230	237	899	finally
    //   252	257	899	finally
    //   272	277	899	finally
    //   292	297	899	finally
    //   312	318	899	finally
    //   333	345	899	finally
    //   360	382	899	finally
    //   397	405	899	finally
    //   420	428	899	finally
    //   443	449	899	finally
    //   464	471	899	finally
    //   486	491	899	finally
    //   506	511	899	finally
    //   523	530	899	finally
    //   550	557	899	finally
    //   230	237	903	java/lang/Exception
    //   252	257	903	java/lang/Exception
    //   272	277	903	java/lang/Exception
    //   292	297	903	java/lang/Exception
    //   312	318	903	java/lang/Exception
    //   333	345	903	java/lang/Exception
    //   360	382	903	java/lang/Exception
    //   397	405	903	java/lang/Exception
    //   420	428	903	java/lang/Exception
    //   443	449	903	java/lang/Exception
    //   464	471	903	java/lang/Exception
    //   486	491	903	java/lang/Exception
    //   506	511	903	java/lang/Exception
    //   523	530	903	java/lang/Exception
    //   550	557	915	java/lang/Exception
    //   230	237	923	org/json/JSONException
    //   252	257	923	org/json/JSONException
    //   272	277	923	org/json/JSONException
    //   292	297	923	org/json/JSONException
    //   312	318	923	org/json/JSONException
    //   333	345	923	org/json/JSONException
    //   360	382	923	org/json/JSONException
    //   397	405	923	org/json/JSONException
    //   420	428	923	org/json/JSONException
    //   443	449	923	org/json/JSONException
    //   464	471	923	org/json/JSONException
    //   486	491	923	org/json/JSONException
    //   506	511	923	org/json/JSONException
    //   523	530	923	org/json/JSONException
    //   550	557	923	org/json/JSONException
    //   230	237	931	java/io/IOException
    //   252	257	931	java/io/IOException
    //   272	277	931	java/io/IOException
    //   292	297	931	java/io/IOException
    //   312	318	931	java/io/IOException
    //   333	345	931	java/io/IOException
    //   360	382	931	java/io/IOException
    //   397	405	931	java/io/IOException
    //   420	428	931	java/io/IOException
    //   443	449	931	java/io/IOException
    //   464	471	931	java/io/IOException
    //   486	491	931	java/io/IOException
    //   506	511	931	java/io/IOException
    //   523	530	931	java/io/IOException
    //   550	557	931	java/io/IOException
    //   230	237	939	org/apache/http/client/ClientProtocolException
    //   252	257	939	org/apache/http/client/ClientProtocolException
    //   272	277	939	org/apache/http/client/ClientProtocolException
    //   292	297	939	org/apache/http/client/ClientProtocolException
    //   312	318	939	org/apache/http/client/ClientProtocolException
    //   333	345	939	org/apache/http/client/ClientProtocolException
    //   360	382	939	org/apache/http/client/ClientProtocolException
    //   397	405	939	org/apache/http/client/ClientProtocolException
    //   420	428	939	org/apache/http/client/ClientProtocolException
    //   443	449	939	org/apache/http/client/ClientProtocolException
    //   464	471	939	org/apache/http/client/ClientProtocolException
    //   486	491	939	org/apache/http/client/ClientProtocolException
    //   506	511	939	org/apache/http/client/ClientProtocolException
    //   523	530	939	org/apache/http/client/ClientProtocolException
    //   550	557	939	org/apache/http/client/ClientProtocolException
  }
  
  protected static SharedPreferences a(Context paramContext)
  {
    String str = paramContext.getPackageName();
    return paramContext.getSharedPreferences(b + "MoblieAgent_event_" + str, 0);
  }
  
  private static String a(Context paramContext, String paramString, SharedPreferences paramSharedPreferences)
  {
    long l2 = 0L;
    n = "";
    String str = h.a(paramContext, paramString);
    SharedPreferences.Editor localEditor = paramSharedPreferences.edit();
    localEditor.putString("appKey", paramString);
    localEditor.putString("sessionId", str);
    localEditor.putLong("lastResumeTime", System.currentTimeMillis());
    localEditor.putString("activities", i(paramContext, "onResume", null));
    long l1 = paramSharedPreferences.getLong("readFlowRev", 0L);
    paramContext = h.o(paramContext);
    localEditor.putLong("readFlowRev", paramContext[0]);
    long l3 = paramContext[0] - l1;
    l1 = l3;
    if (l3 < 0L) {
      l1 = 0L;
    }
    localEditor.putLong("consumeFlowRev", l1);
    l1 = paramSharedPreferences.getLong("readFlowSnd", 0L);
    localEditor.putLong("readFlowSnd", paramContext[1]);
    l1 = paramContext[1] - l1;
    if (l1 < 0L) {
      l1 = l2;
    }
    for (;;)
    {
      localEditor.putLong("consumeFlowSnd", l1);
      localEditor.commit();
      p = str;
      return str;
    }
  }
  
  private static void a(Context paramContext, String paramString, long paramLong)
  {
    synchronized (q)
    {
      SharedPreferences localSharedPreferences = c(paramContext);
      int i1 = localSharedPreferences.getInt("uploadcount", 0);
      paramString = localSharedPreferences.getString("uploadList", "") + paramString + "|";
      long l1 = paramLong;
      if (paramLong > 10000L) {
        l1 = 1L;
      }
      localSharedPreferences.edit().putString("uploadList", paramString).commit();
      localSharedPreferences.edit().putLong("uploadpopindex", l1).commit();
      if (paramString.split("\\|").length > 30000)
      {
        paramString = n(paramContext);
        paramContext.deleteFile(paramString);
        j(paramContext, paramString);
        return;
      }
      localSharedPreferences.edit().putInt("uploadcount", i1 + 1).commit();
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    if (c == 0)
    {
      Log.i("MobileAgent", "Du have not permission to collect data");
      return;
    }
    if (paramString1.contains("#"))
    {
      Log.w("MobileAgent", "the eventId contain illegal char");
      return;
    }
    h(paramContext, paramString1, paramString2);
  }
  
  protected static void a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      c.a("MobileAgentRun", "run into pageact onresumep");
      h(paramContext);
    }
    for (;;)
    {
      c.a("MobileAgentRun", "run out pageact");
      return;
      c.a("MobileAgentRun", "run into pageact onpausep");
      i(paramContext);
    }
  }
  
  protected static boolean a(Context paramContext, int paramInt)
  {
    paramContext = k(paramContext);
    int i1;
    if (paramInt == 3)
    {
      paramInt = paramContext.getInt("actionmonth", 0);
      i1 = paramContext.getInt("actionday", 0);
    }
    for (;;)
    {
      paramContext = new Date();
      String str = new SimpleDateFormat("dd").format(paramContext);
      if ((Integer.valueOf(new SimpleDateFormat("M").format(paramContext)).intValue() == paramInt) && (Integer.valueOf(str).intValue() == i1)) {
        break;
      }
      return true;
      if (paramInt == 2)
      {
        paramInt = paramContext.getInt("eventmonth", 0);
        i1 = paramContext.getInt("eventday", 0);
      }
      else
      {
        paramInt = paramContext.getInt("sysmonth", 0);
        i1 = paramContext.getInt("sysday", 0);
      }
    }
    return false;
  }
  
  private static boolean a(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    paramSharedPreferences = a(paramContext);
    String str1 = paramSharedPreferences.getString("eventlogs", "");
    if (!str1.equals(""))
    {
      String str2 = l(paramContext).getString("sessionId", null);
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("sid", str2);
        localJSONObject.put("logJsonAry", str1);
        if (a(paramContext, localJSONObject.toString(), 2)) {
          paramSharedPreferences.edit().putString("eventlogs", "").commit();
        }
        return true;
      }
      catch (JSONException paramContext)
      {
        for (;;)
        {
          paramContext.printStackTrace();
        }
      }
    }
    return false;
  }
  
  private static boolean a(Context paramContext, SharedPreferences paramSharedPreferences, boolean paramBoolean)
  {
    String str1 = paramSharedPreferences.getString("sessionId", "");
    String str2 = paramSharedPreferences.getString("activities", "");
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("sid", str1);
      localJSONObject.put("logs", str2);
      if (paramBoolean)
      {
        localJSONObject.put("flowConsumpRev", paramSharedPreferences.getLong("consumeFlowRev", 0L));
        localJSONObject.put("flowConsumpSnd", paramSharedPreferences.getLong("consumeFlowSnd", 0L));
      }
      while (a(paramContext, localJSONObject.toString(), 3))
      {
        paramSharedPreferences.edit().putString("activities", "").commit();
        break;
        localJSONObject.put("flowConsumpRev", 0);
        localJSONObject.put("flowConsumpSnd", 0);
      }
      return true;
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  /* Error */
  protected static boolean a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: aload_1
    //   4: invokestatic 502	com/iflytek/cloud/thirdparty/b:h	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   7: astore 4
    //   9: aload 4
    //   11: ldc 89
    //   13: invokevirtual 472	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   16: ifne +396 -> 412
    //   19: aload_0
    //   20: invokestatic 505	com/iflytek/cloud/thirdparty/b:d	(Landroid/content/Context;)Lorg/json/JSONObject;
    //   23: astore 5
    //   25: new 146	org/json/JSONObject
    //   28: dup
    //   29: aload 4
    //   31: invokespecial 506	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   34: astore 6
    //   36: aload 5
    //   38: ldc_w 477
    //   41: aload 6
    //   43: ldc_w 477
    //   46: invokevirtual 510	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   49: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   52: pop
    //   53: aload 5
    //   55: ldc_w 512
    //   58: aload_0
    //   59: invokestatic 514	com/iflytek/cloud/thirdparty/h:g	(Landroid/content/Context;)Ljava/lang/String;
    //   62: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   65: pop
    //   66: aload 5
    //   68: ldc_w 516
    //   71: invokestatic 518	com/iflytek/cloud/thirdparty/h:a	()Ljava/lang/String;
    //   74: ldc -95
    //   76: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   79: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   82: pop
    //   83: aload 5
    //   85: ldc_w 520
    //   88: invokestatic 522	com/iflytek/cloud/thirdparty/h:b	()Ljava/lang/String;
    //   91: ldc -95
    //   93: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   96: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   99: pop
    //   100: aload 5
    //   102: ldc_w 524
    //   105: invokestatic 526	com/iflytek/cloud/thirdparty/h:c	()Ljava/lang/String;
    //   108: ldc -95
    //   110: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   113: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   116: pop
    //   117: aload 5
    //   119: ldc_w 528
    //   122: aload_0
    //   123: invokestatic 530	com/iflytek/cloud/thirdparty/h:l	(Landroid/content/Context;)Ljava/lang/String;
    //   126: ldc -95
    //   128: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   131: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   134: pop
    //   135: aload 5
    //   137: ldc_w 532
    //   140: aload_0
    //   141: invokestatic 534	com/iflytek/cloud/thirdparty/h:p	(Landroid/content/Context;)Ljava/lang/String;
    //   144: ldc -95
    //   146: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   149: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   152: pop
    //   153: aload 5
    //   155: ldc_w 536
    //   158: getstatic 109	com/iflytek/cloud/thirdparty/b:v	Ljava/lang/String;
    //   161: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   164: pop
    //   165: aload 5
    //   167: ldc_w 538
    //   170: aload_0
    //   171: invokestatic 540	com/iflytek/cloud/thirdparty/h:a	(Landroid/content/Context;)Ljava/lang/String;
    //   174: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   177: pop
    //   178: aload 5
    //   180: ldc_w 542
    //   183: invokestatic 544	com/iflytek/cloud/thirdparty/h:f	()Ljava/lang/String;
    //   186: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   189: pop
    //   190: aload 5
    //   192: ldc_w 546
    //   195: invokestatic 548	com/iflytek/cloud/thirdparty/h:g	()Ljava/lang/String;
    //   198: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   201: pop
    //   202: aload 5
    //   204: ldc_w 550
    //   207: invokestatic 552	com/iflytek/cloud/thirdparty/h:h	()Ljava/lang/String;
    //   210: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   213: pop
    //   214: aload 5
    //   216: ldc_w 268
    //   219: new 554	org/json/JSONArray
    //   222: dup
    //   223: new 55	java/lang/StringBuilder
    //   226: dup
    //   227: ldc_w 556
    //   230: invokespecial 61	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   233: aload 4
    //   235: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: ldc_w 558
    //   241: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: invokespecial 559	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   250: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   253: pop
    //   254: aload_0
    //   255: ldc_w 561
    //   258: aload 5
    //   260: invokestatic 563	com/iflytek/cloud/thirdparty/b:a	(Landroid/content/Context;Ljava/lang/String;Lorg/json/JSONObject;)I
    //   263: istore_2
    //   264: iload_2
    //   265: iconst_1
    //   266: if_icmpeq +8 -> 274
    //   269: iload_2
    //   270: iconst_3
    //   271: if_icmpne +134 -> 405
    //   274: aload_0
    //   275: iconst_3
    //   276: invokestatic 566	com/iflytek/cloud/thirdparty/b:b	(Landroid/content/Context;I)V
    //   279: aload_0
    //   280: aload_1
    //   281: invokestatic 568	com/iflytek/cloud/thirdparty/b:i	(Landroid/content/Context;Ljava/lang/String;)V
    //   284: ldc -3
    //   286: ldc_w 570
    //   289: invokestatic 402	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   292: pop
    //   293: iconst_1
    //   294: istore_3
    //   295: iload_3
    //   296: ireturn
    //   297: astore 6
    //   299: aload 5
    //   301: ldc_w 477
    //   304: ldc 89
    //   306: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   309: pop
    //   310: goto -257 -> 53
    //   313: astore 4
    //   315: aload 4
    //   317: invokevirtual 284	org/json/JSONException:printStackTrace	()V
    //   320: iconst_3
    //   321: istore_2
    //   322: goto -58 -> 264
    //   325: astore 6
    //   327: aload 5
    //   329: ldc_w 516
    //   332: ldc 89
    //   334: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   337: pop
    //   338: goto -255 -> 83
    //   341: astore 6
    //   343: aload 5
    //   345: ldc_w 520
    //   348: ldc 89
    //   350: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   353: pop
    //   354: goto -254 -> 100
    //   357: astore 6
    //   359: aload 5
    //   361: ldc_w 524
    //   364: ldc 89
    //   366: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   369: pop
    //   370: goto -253 -> 117
    //   373: astore 6
    //   375: aload 5
    //   377: ldc_w 528
    //   380: ldc 89
    //   382: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   385: pop
    //   386: goto -251 -> 135
    //   389: astore 6
    //   391: aload 5
    //   393: ldc_w 532
    //   396: ldc 89
    //   398: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   401: pop
    //   402: goto -249 -> 153
    //   405: iload_2
    //   406: iconst_2
    //   407: if_icmpne -112 -> 295
    //   410: iconst_0
    //   411: ireturn
    //   412: aload_0
    //   413: iconst_3
    //   414: invokestatic 566	com/iflytek/cloud/thirdparty/b:b	(Landroid/content/Context;I)V
    //   417: aload_0
    //   418: aload_1
    //   419: invokestatic 568	com/iflytek/cloud/thirdparty/b:i	(Landroid/content/Context;Ljava/lang/String;)V
    //   422: iconst_1
    //   423: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	424	0	paramContext	Context
    //   0	424	1	paramString	String
    //   263	145	2	i1	int
    //   1	295	3	bool	boolean
    //   7	227	4	str	String
    //   313	3	4	localJSONException1	JSONException
    //   23	369	5	localJSONObject1	JSONObject
    //   34	8	6	localJSONObject2	JSONObject
    //   297	1	6	localJSONException2	JSONException
    //   325	1	6	localUnsupportedEncodingException1	UnsupportedEncodingException
    //   341	1	6	localUnsupportedEncodingException2	UnsupportedEncodingException
    //   357	1	6	localUnsupportedEncodingException3	UnsupportedEncodingException
    //   373	1	6	localUnsupportedEncodingException4	UnsupportedEncodingException
    //   389	1	6	localUnsupportedEncodingException5	UnsupportedEncodingException
    // Exception table:
    //   from	to	target	type
    //   36	53	297	org/json/JSONException
    //   25	36	313	org/json/JSONException
    //   53	66	313	org/json/JSONException
    //   66	83	313	org/json/JSONException
    //   83	100	313	org/json/JSONException
    //   100	117	313	org/json/JSONException
    //   117	135	313	org/json/JSONException
    //   135	153	313	org/json/JSONException
    //   153	264	313	org/json/JSONException
    //   299	310	313	org/json/JSONException
    //   327	338	313	org/json/JSONException
    //   343	354	313	org/json/JSONException
    //   359	370	313	org/json/JSONException
    //   375	386	313	org/json/JSONException
    //   391	402	313	org/json/JSONException
    //   66	83	325	java/io/UnsupportedEncodingException
    //   83	100	341	java/io/UnsupportedEncodingException
    //   100	117	357	java/io/UnsupportedEncodingException
    //   117	135	373	java/io/UnsupportedEncodingException
    //   135	153	389	java/io/UnsupportedEncodingException
  }
  
  /* Error */
  private static boolean a(Context paramContext, String paramString, int paramInt)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: ldc 2
    //   5: monitorenter
    //   6: iload_2
    //   7: iconst_3
    //   8: if_icmpne +118 -> 126
    //   11: getstatic 71	com/iflytek/cloud/thirdparty/b:i	Ljava/lang/String;
    //   14: astore 7
    //   16: iload 4
    //   18: istore_3
    //   19: aload_1
    //   20: invokevirtual 575	java/lang/String:trim	()Ljava/lang/String;
    //   23: ldc 89
    //   25: invokevirtual 472	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   28: ifne +93 -> 121
    //   31: aload_0
    //   32: invokestatic 578	com/iflytek/cloud/thirdparty/b:m	(Landroid/content/Context;)J
    //   35: lstore 5
    //   37: new 55	java/lang/StringBuilder
    //   40: dup
    //   41: aload 7
    //   43: invokestatic 173	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   46: invokespecial 61	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: lload 5
    //   51: invokevirtual 581	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   54: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: astore 10
    //   59: aconst_null
    //   60: astore 7
    //   62: aconst_null
    //   63: astore 9
    //   65: aload_0
    //   66: aload 10
    //   68: iconst_1
    //   69: invokevirtual 585	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   72: astore 8
    //   74: aload 8
    //   76: astore 9
    //   78: aload 8
    //   80: astore 7
    //   82: aload_0
    //   83: aload 10
    //   85: lconst_1
    //   86: lload 5
    //   88: ladd
    //   89: invokestatic 587	com/iflytek/cloud/thirdparty/b:a	(Landroid/content/Context;Ljava/lang/String;J)V
    //   92: aload 8
    //   94: astore 9
    //   96: aload 8
    //   98: astore 7
    //   100: aload 8
    //   102: aload_1
    //   103: invokevirtual 591	java/lang/String:getBytes	()[B
    //   106: invokevirtual 594	java/io/FileOutputStream:write	([B)V
    //   109: aload 8
    //   111: ifnull +8 -> 119
    //   114: aload 8
    //   116: invokevirtual 595	java/io/FileOutputStream:close	()V
    //   119: iconst_1
    //   120: istore_3
    //   121: ldc 2
    //   123: monitorexit
    //   124: iload_3
    //   125: ireturn
    //   126: iload_2
    //   127: iconst_2
    //   128: if_icmpne +11 -> 139
    //   131: getstatic 75	com/iflytek/cloud/thirdparty/b:j	Ljava/lang/String;
    //   134: astore 7
    //   136: goto -120 -> 16
    //   139: iload_2
    //   140: iconst_4
    //   141: if_icmpne +11 -> 152
    //   144: getstatic 83	com/iflytek/cloud/thirdparty/b:l	Ljava/lang/String;
    //   147: astore 7
    //   149: goto -133 -> 16
    //   152: iload_2
    //   153: iconst_1
    //   154: if_icmpne +11 -> 165
    //   157: getstatic 87	com/iflytek/cloud/thirdparty/b:m	Ljava/lang/String;
    //   160: astore 7
    //   162: goto -146 -> 16
    //   165: iload 4
    //   167: istore_3
    //   168: iload_2
    //   169: iconst_5
    //   170: if_icmpne -49 -> 121
    //   173: getstatic 79	com/iflytek/cloud/thirdparty/b:k	Ljava/lang/String;
    //   176: astore 7
    //   178: goto -162 -> 16
    //   181: astore_0
    //   182: aconst_null
    //   183: astore_0
    //   184: ldc -127
    //   186: ldc_w 597
    //   189: invokestatic 412	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   192: pop
    //   193: iload 4
    //   195: istore_3
    //   196: aload_0
    //   197: ifnull -76 -> 121
    //   200: aload_0
    //   201: invokevirtual 595	java/io/FileOutputStream:close	()V
    //   204: iload 4
    //   206: istore_3
    //   207: goto -86 -> 121
    //   210: astore_0
    //   211: aload_0
    //   212: invokevirtual 282	java/io/IOException:printStackTrace	()V
    //   215: iload 4
    //   217: istore_3
    //   218: goto -97 -> 121
    //   221: astore_0
    //   222: ldc 2
    //   224: monitorexit
    //   225: aload_0
    //   226: athrow
    //   227: astore_0
    //   228: aload 9
    //   230: astore 7
    //   232: ldc -127
    //   234: ldc_w 599
    //   237: invokestatic 412	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   240: pop
    //   241: aload 9
    //   243: ifnull -124 -> 119
    //   246: aload 9
    //   248: invokevirtual 595	java/io/FileOutputStream:close	()V
    //   251: goto -132 -> 119
    //   254: astore_0
    //   255: aload_0
    //   256: invokevirtual 282	java/io/IOException:printStackTrace	()V
    //   259: goto -140 -> 119
    //   262: astore_0
    //   263: aload 7
    //   265: ifnull +8 -> 273
    //   268: aload 7
    //   270: invokevirtual 595	java/io/FileOutputStream:close	()V
    //   273: aload_0
    //   274: athrow
    //   275: astore_1
    //   276: aload_1
    //   277: invokevirtual 282	java/io/IOException:printStackTrace	()V
    //   280: goto -7 -> 273
    //   283: astore_0
    //   284: aload_0
    //   285: invokevirtual 282	java/io/IOException:printStackTrace	()V
    //   288: goto -169 -> 119
    //   291: astore_1
    //   292: aload_0
    //   293: astore 7
    //   295: aload_1
    //   296: astore_0
    //   297: goto -34 -> 263
    //   300: astore_0
    //   301: aload 8
    //   303: astore_0
    //   304: goto -120 -> 184
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	307	0	paramContext	Context
    //   0	307	1	paramString	String
    //   0	307	2	paramInt	int
    //   18	200	3	bool1	boolean
    //   1	215	4	bool2	boolean
    //   35	52	5	l1	long
    //   14	280	7	localObject1	Object
    //   72	230	8	localFileOutputStream	java.io.FileOutputStream
    //   63	184	9	localObject2	Object
    //   57	27	10	str	String
    // Exception table:
    //   from	to	target	type
    //   65	74	181	java/io/FileNotFoundException
    //   200	204	210	java/io/IOException
    //   11	16	221	finally
    //   19	59	221	finally
    //   114	119	221	finally
    //   131	136	221	finally
    //   144	149	221	finally
    //   157	162	221	finally
    //   173	178	221	finally
    //   200	204	221	finally
    //   211	215	221	finally
    //   246	251	221	finally
    //   255	259	221	finally
    //   268	273	221	finally
    //   273	275	221	finally
    //   276	280	221	finally
    //   284	288	221	finally
    //   65	74	227	java/io/IOException
    //   82	92	227	java/io/IOException
    //   100	109	227	java/io/IOException
    //   246	251	254	java/io/IOException
    //   65	74	262	finally
    //   82	92	262	finally
    //   100	109	262	finally
    //   232	241	262	finally
    //   268	273	275	java/io/IOException
    //   114	119	283	java/io/IOException
    //   184	193	291	finally
    //   82	92	300	java/io/FileNotFoundException
    //   100	109	300	java/io/FileNotFoundException
  }
  
  private static boolean a(SharedPreferences paramSharedPreferences)
  {
    long l1 = paramSharedPreferences.getLong("endTime", -1L);
    return System.currentTimeMillis() - l1 > 30000L;
  }
  
  protected static SharedPreferences b(Context paramContext)
  {
    String str = paramContext.getPackageName();
    return paramContext.getSharedPreferences(b + "MoblieAgent_event_sp" + str, 0);
  }
  
  protected static void b(Context paramContext, int paramInt)
  {
    Date localDate = new Date();
    int i1 = Integer.parseInt(new SimpleDateFormat("dd").format(localDate));
    int i2 = Integer.parseInt(new SimpleDateFormat("M").format(localDate));
    paramContext = k(paramContext).edit();
    if (paramInt == 3)
    {
      paramContext.putInt("actionmonth", i2);
      paramContext.putInt("actionday", i1);
    }
    for (;;)
    {
      paramContext.commit();
      return;
      if (paramInt == 2)
      {
        paramContext.putInt("eventmonth", i2);
        paramContext.putInt("eventday", i1);
      }
      else
      {
        paramContext.putInt("sysmonth", i2);
        paramContext.putInt("sysday", i1);
      }
    }
  }
  
  protected static void b(Context paramContext, String paramString1, String paramString2)
  {
    synchronized (r)
    {
      paramString1 = h.a(paramString1);
      paramString2 = h.a(paramString2);
      SharedPreferences localSharedPreferences = a(paramContext);
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(localSharedPreferences.getString("eventlogs", ""));
      try
      {
        localStringBuffer.append(URLEncoder.encode(paramString1, "UTF-8"));
        localStringBuffer.append("|");
        localStringBuffer.append(URLEncoder.encode(paramString2, "UTF-8"));
        localStringBuffer.append("|");
        localStringBuffer.append(1);
        localStringBuffer.append("|");
        localStringBuffer.append(System.currentTimeMillis());
        localStringBuffer.append("\n");
        localSharedPreferences.edit().putString("eventlogs", localStringBuffer.toString()).commit();
        a(paramContext, null);
        b(paramContext, false);
        return;
      }
      catch (UnsupportedEncodingException paramContext)
      {
        c.a("MobileAgentRun", "unsupport utf-8,can't onEvent()");
        return;
      }
    }
  }
  
  protected static void b(Context paramContext, boolean paramBoolean)
  {
    if (c != 1) {
      return;
    }
    c.a("MobileAgentRun", "run into strategy");
    f(paramContext);
    if ((!e) || ((e) && (o(paramContext)))) {
      switch (g)
      {
      }
    }
    for (;;)
    {
      f = false;
      c.a("MobileAgentRun", "run out strategy");
      return;
      new d(paramContext, 6).start();
      continue;
      if (paramBoolean)
      {
        new d(paramContext, 6).start();
        f = false;
        continue;
        if (a(paramContext, 3)) {
          new d(paramContext, 6).start();
        }
      }
    }
  }
  
  private static boolean b(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    paramSharedPreferences = b(paramContext);
    String str1 = paramSharedPreferences.getString("eventlogs", "");
    if (!str1.equals(""))
    {
      String str2 = l(paramContext).getString("sessionId", null);
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("sid", str2);
        localJSONObject.put("logJsonAry", str1);
        if (a(paramContext, localJSONObject.toString(), 5)) {
          paramSharedPreferences.edit().putString("eventlogs", "").commit();
        }
        return true;
      }
      catch (JSONException paramContext)
      {
        for (;;)
        {
          paramContext.printStackTrace();
        }
      }
    }
    return false;
  }
  
  protected static boolean b(Context paramContext, String paramString)
  {
    String str = h(paramContext, paramString);
    try
    {
      if (a(paramContext, "http://da.mmarket.com/mmsdk/mmsdk?func=mmsdk:postsyslog", new JSONObject(str)) == 1)
      {
        i(paramContext, paramString);
        return true;
      }
    }
    catch (JSONException localJSONException)
    {
      i(paramContext, paramString);
      Log.i("MobileAgent", "SDK del a dirty data");
    }
    return false;
  }
  
  protected static SharedPreferences c(Context paramContext)
  {
    String str = paramContext.getPackageName();
    return paramContext.getSharedPreferences(b + "MoblieAgent_upload_" + str, 0);
  }
  
  protected static void c(Context paramContext, String paramString1, String paramString2)
  {
    synchronized (s)
    {
      paramString1 = h.a(paramString1);
      paramString2 = h.a(paramString2);
      SharedPreferences localSharedPreferences = b(paramContext);
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(localSharedPreferences.getString("eventlogs", ""));
      try
      {
        localStringBuffer.append(URLEncoder.encode(paramString1, "UTF-8"));
        localStringBuffer.append("|");
        localStringBuffer.append(URLEncoder.encode(paramString2, "UTF-8"));
        localStringBuffer.append("|");
        localStringBuffer.append(1);
        localStringBuffer.append("|");
        localStringBuffer.append(System.currentTimeMillis());
        localStringBuffer.append("\n");
        localSharedPreferences.edit().putString("eventlogs", localStringBuffer.toString()).commit();
        if ((g == 1) || (localStringBuffer.toString().getBytes().length > 10000)) {
          b(paramContext, null);
        }
        b(paramContext, false);
        return;
      }
      catch (UnsupportedEncodingException paramContext)
      {
        c.a("MobileAgent", "unsupport utf-8,can't onEvent()");
        return;
      }
    }
  }
  
  protected static boolean c(Context paramContext, String paramString)
  {
    boolean bool = false;
    String str = h(paramContext, paramString);
    try
    {
      JSONObject localJSONObject = new JSONObject(str);
      localJSONObject.put("pid", 1);
      localJSONObject.put("protocolVersion", "3.1.4");
      localJSONObject.put("sdkVersion", "3.2.0.2");
      localJSONObject.put("cid", h.b(paramContext));
      localJSONObject.put("deviceId", h.a(paramContext));
      localJSONObject.put("appKey", h.h(paramContext));
      localJSONObject.put("packageName", h.j(paramContext));
      localJSONObject.put("versionCode", h.m(paramContext));
      localJSONObject.put("versionName", h.n(paramContext));
      localJSONObject.put("sendTime", System.currentTimeMillis());
      int i1 = a(paramContext, "http://da.mmarket.com/mmsdk/mmsdk?func=mmsdk:posterrlog", localJSONObject);
      if ((i1 == 1) || (i1 == 3))
      {
        b(paramContext, 3);
        i(paramContext, paramString);
        Log.i("MobileAgent", "erLog sd");
        c.a("MobileAgent", "send errlog success \n" + str);
        bool = true;
      }
      while (i1 != 2) {
        return bool;
      }
      return false;
    }
    catch (JSONException localJSONException)
    {
      i(paramContext, paramString);
      Log.i("MobileAgent", "SDK del a dirty data");
    }
    return false;
  }
  
  protected static JSONObject d(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("pid", 1);
      localJSONObject.put("protocolVersion", "3.1.4");
      localJSONObject.put("sdkVersion", "3.2.0.2");
      localJSONObject.put("cid", h.b(paramContext));
      localJSONObject.put("appKey", h.h(paramContext));
      localJSONObject.put("packageName", paramContext.getPackageName());
      localJSONObject.put("versionCode", h.m(paramContext));
      localJSONObject.put("versionName", h.n(paramContext));
      localJSONObject.put("sendTime", System.currentTimeMillis());
      localJSONObject.put("deviceId", h.a(paramContext));
      localJSONObject.put("channel", h.i(paramContext));
      return localJSONObject;
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
    }
    return localJSONObject;
  }
  
  protected static void d(Context paramContext, String paramString)
  {
    k(paramContext).edit().putString(paramString, "record").commit();
  }
  
  public static void d(Context paramContext, String paramString1, String paramString2)
  {
    if (t)
    {
      Log.i("MobileAgent", "already init");
      return;
    }
    for (;;)
    {
      try
      {
        if (!a)
        {
          if (!paramContext.getSharedPreferences("MoblieAgent_debug", 0).getString("debug", "").equals("")) {
            continue;
          }
          bool = false;
          a = bool;
        }
      }
      catch (Exception localException)
      {
        boolean bool;
        String str;
        continue;
      }
      c.a("MobileAgentAPI", "run in init[" + paramString1 + "," + paramString2 + "]");
      if (paramContext != null) {
        continue;
      }
      Log.e("MobileAgent", "Exception occurent in joinDu ,context cann't be null");
      return;
      bool = true;
    }
    if ((TextUtils.isEmpty(paramString1)) || (h.a(paramString1, 50)))
    {
      Log.e("MobileAgent", "Exception occurent in joinDu ,appID cann't be null or empty");
      return;
    }
    if (TextUtils.isEmpty(paramString2)) {
      str = "0";
    }
    for (;;)
    {
      paramString2 = paramContext.getSharedPreferences(b + "MoblieAgent_sys_config", 0);
      paramString2.edit().putString("MOBILE_APPKEY", paramString1).commit();
      paramString2.edit().putString("MOBILE_CHANNEL", str).commit();
      if (!e(paramContext, "#lxapkmd5")) {
        new Thread(new f(paramContext)).start();
      }
      t = true;
      Log.i("MobileAgent", "finish init SUCCESS " + a);
      return;
      str = paramString2;
      if (h.a(paramString2, 100))
      {
        str = paramString2.substring(0, 99);
        Log.e("MobileAgent", "Exception occurent in joinDu ,channelID cann't be null or empty");
      }
    }
  }
  
  protected static void e(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("pid", 1);
      localJSONObject.put("protocolVersion", "3.1.4");
      localJSONObject.put("sdkVersion", "3.2.0.2");
      localJSONObject.put("cid", h.b(paramContext));
      localJSONObject.put("deviceId", h.a(paramContext));
      localJSONObject.put("appKey", h.h(paramContext));
      localJSONObject.put("packageName", paramContext.getPackageName());
      localJSONObject.put("versionCode", h.m(paramContext));
      localJSONObject.put("versionName", h.n(paramContext));
      localJSONObject.put("sendTime", System.currentTimeMillis());
      localJSONObject.put("imsi", h.c(paramContext));
      localJSONObject.put("mac", h.g(paramContext));
      localJSONObject.put("deviceDetail", URLEncoder.encode(h.a(), "UTF-8"));
      localJSONObject.put("manufacturer", URLEncoder.encode(h.b(), "UTF-8"));
      localJSONObject.put("phoneOS", URLEncoder.encode(h.c(), "UTF-8"));
      localJSONObject.put("screenWidth", h.d(paramContext));
      localJSONObject.put("screenHeight", h.e(paramContext));
      localJSONObject.put("screenDensity", h.f(paramContext));
      localJSONObject.put("carrierName", URLEncoder.encode(h.k(paramContext), "UTF-8"));
      localJSONObject.put("accessPoint", h.l(paramContext));
      localJSONObject.put("countryCode", h.d());
      localJSONObject.put("languageCode", h.e());
      localJSONObject.put("channel", URLEncoder.encode(h.i(paramContext), "UTF-8"));
      if (a(paramContext, localJSONObject.toString(), 1)) {
        b(paramContext, 1);
      }
      return;
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    catch (UnsupportedEncodingException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  protected static boolean e(Context paramContext, String paramString)
  {
    return !k(paramContext).getString(paramString, "").equals("");
  }
  
  /* Error */
  protected static boolean e(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +426 -> 427
    //   4: aload_0
    //   5: aload_1
    //   6: invokestatic 502	com/iflytek/cloud/thirdparty/b:h	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   9: astore 4
    //   11: aload 4
    //   13: ldc 89
    //   15: invokevirtual 472	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   18: ifne +389 -> 407
    //   21: new 146	org/json/JSONObject
    //   24: dup
    //   25: aload 4
    //   27: invokespecial 506	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   30: astore 4
    //   32: aload_0
    //   33: invokestatic 677	com/iflytek/cloud/thirdparty/h:b	(Landroid/content/Context;)Ljava/lang/String;
    //   36: astore 5
    //   38: aload 4
    //   40: ldc_w 665
    //   43: iconst_1
    //   44: invokevirtual 497	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   47: pop
    //   48: aload 4
    //   50: ldc_w 667
    //   53: ldc_w 669
    //   56: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   59: pop
    //   60: aload 4
    //   62: ldc_w 671
    //   65: ldc_w 673
    //   68: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   71: pop
    //   72: aload 4
    //   74: ldc_w 675
    //   77: aload 5
    //   79: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   82: pop
    //   83: aload 4
    //   85: ldc_w 310
    //   88: aload_0
    //   89: invokestatic 157	com/iflytek/cloud/thirdparty/h:h	(Landroid/content/Context;)Ljava/lang/String;
    //   92: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   95: pop
    //   96: aload 4
    //   98: ldc_w 679
    //   101: aload_0
    //   102: invokestatic 681	com/iflytek/cloud/thirdparty/h:j	(Landroid/content/Context;)Ljava/lang/String;
    //   105: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   108: pop
    //   109: aload 4
    //   111: ldc_w 683
    //   114: aload_0
    //   115: invokestatic 686	com/iflytek/cloud/thirdparty/h:m	(Landroid/content/Context;)I
    //   118: invokevirtual 497	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   121: pop
    //   122: aload 4
    //   124: ldc_w 688
    //   127: aload_0
    //   128: invokestatic 689	com/iflytek/cloud/thirdparty/h:n	(Landroid/content/Context;)Ljava/lang/String;
    //   131: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   134: pop
    //   135: aload 4
    //   137: ldc_w 691
    //   140: invokestatic 326	java/lang/System:currentTimeMillis	()J
    //   143: invokevirtual 492	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   146: pop
    //   147: aload 4
    //   149: ldc_w 512
    //   152: aload_0
    //   153: invokestatic 514	com/iflytek/cloud/thirdparty/h:g	(Landroid/content/Context;)Ljava/lang/String;
    //   156: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   159: pop
    //   160: aload 4
    //   162: ldc_w 516
    //   165: invokestatic 518	com/iflytek/cloud/thirdparty/h:a	()Ljava/lang/String;
    //   168: ldc -95
    //   170: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   173: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   176: pop
    //   177: aload 4
    //   179: ldc_w 520
    //   182: invokestatic 522	com/iflytek/cloud/thirdparty/h:b	()Ljava/lang/String;
    //   185: ldc -95
    //   187: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   190: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   193: pop
    //   194: aload 4
    //   196: ldc_w 524
    //   199: invokestatic 526	com/iflytek/cloud/thirdparty/h:c	()Ljava/lang/String;
    //   202: ldc -95
    //   204: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   207: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   210: pop
    //   211: aload 4
    //   213: ldc_w 528
    //   216: aload_0
    //   217: invokestatic 530	com/iflytek/cloud/thirdparty/h:l	(Landroid/content/Context;)Ljava/lang/String;
    //   220: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   223: pop
    //   224: aload 4
    //   226: ldc_w 538
    //   229: aload_0
    //   230: invokestatic 540	com/iflytek/cloud/thirdparty/h:a	(Landroid/content/Context;)Ljava/lang/String;
    //   233: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   236: pop
    //   237: aload 4
    //   239: ldc_w 699
    //   242: aload_0
    //   243: invokestatic 159	com/iflytek/cloud/thirdparty/h:i	(Landroid/content/Context;)Ljava/lang/String;
    //   246: ldc -95
    //   248: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   251: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   254: pop
    //   255: aload_0
    //   256: ldc_w 794
    //   259: aload 4
    //   261: invokestatic 563	com/iflytek/cloud/thirdparty/b:a	(Landroid/content/Context;Ljava/lang/String;Lorg/json/JSONObject;)I
    //   264: istore_3
    //   265: iload_3
    //   266: iconst_1
    //   267: if_icmpeq +8 -> 275
    //   270: iload_3
    //   271: iconst_3
    //   272: if_icmpne +117 -> 389
    //   275: aload_2
    //   276: ifnonnull +101 -> 377
    //   279: aload_0
    //   280: iconst_3
    //   281: invokestatic 566	com/iflytek/cloud/thirdparty/b:b	(Landroid/content/Context;I)V
    //   284: aload_0
    //   285: aload_1
    //   286: invokestatic 568	com/iflytek/cloud/thirdparty/b:i	(Landroid/content/Context;Ljava/lang/String;)V
    //   289: ldc -3
    //   291: ldc_w 796
    //   294: invokestatic 402	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   297: pop
    //   298: goto +127 -> 425
    //   301: astore 5
    //   303: aload 4
    //   305: ldc_w 516
    //   308: ldc 89
    //   310: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   313: pop
    //   314: goto -137 -> 177
    //   317: astore_2
    //   318: aload_0
    //   319: aload_1
    //   320: invokestatic 568	com/iflytek/cloud/thirdparty/b:i	(Landroid/content/Context;Ljava/lang/String;)V
    //   323: aload_2
    //   324: invokevirtual 284	org/json/JSONException:printStackTrace	()V
    //   327: iconst_1
    //   328: ireturn
    //   329: astore 5
    //   331: aload 4
    //   333: ldc_w 520
    //   336: ldc 89
    //   338: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   341: pop
    //   342: goto -148 -> 194
    //   345: astore 5
    //   347: aload 4
    //   349: ldc_w 524
    //   352: ldc 89
    //   354: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   357: pop
    //   358: goto -147 -> 211
    //   361: astore 5
    //   363: aload 4
    //   365: ldc_w 699
    //   368: ldc 89
    //   370: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   373: pop
    //   374: goto -119 -> 255
    //   377: ldc -3
    //   379: ldc_w 798
    //   382: invokestatic 402	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   385: pop
    //   386: goto +39 -> 425
    //   389: iload_3
    //   390: iconst_2
    //   391: if_icmpne +14 -> 405
    //   394: ldc -3
    //   396: ldc_w 800
    //   399: invokestatic 412	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   402: pop
    //   403: iconst_0
    //   404: ireturn
    //   405: iconst_0
    //   406: ireturn
    //   407: aload_2
    //   408: ifnonnull +15 -> 423
    //   411: aload_0
    //   412: iconst_3
    //   413: invokestatic 566	com/iflytek/cloud/thirdparty/b:b	(Landroid/content/Context;I)V
    //   416: aload_0
    //   417: aload_1
    //   418: invokestatic 568	com/iflytek/cloud/thirdparty/b:i	(Landroid/content/Context;Ljava/lang/String;)V
    //   421: iconst_1
    //   422: ireturn
    //   423: iconst_0
    //   424: ireturn
    //   425: iconst_1
    //   426: ireturn
    //   427: aload_2
    //   428: astore 4
    //   430: goto -419 -> 11
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	433	0	paramContext	Context
    //   0	433	1	paramString1	String
    //   0	433	2	paramString2	String
    //   264	128	3	i1	int
    //   9	420	4	localObject	Object
    //   36	42	5	str	String
    //   301	1	5	localUnsupportedEncodingException1	UnsupportedEncodingException
    //   329	1	5	localUnsupportedEncodingException2	UnsupportedEncodingException
    //   345	1	5	localUnsupportedEncodingException3	UnsupportedEncodingException
    //   361	1	5	localUnsupportedEncodingException4	UnsupportedEncodingException
    // Exception table:
    //   from	to	target	type
    //   160	177	301	java/io/UnsupportedEncodingException
    //   21	160	317	org/json/JSONException
    //   160	177	317	org/json/JSONException
    //   177	194	317	org/json/JSONException
    //   194	211	317	org/json/JSONException
    //   211	237	317	org/json/JSONException
    //   237	255	317	org/json/JSONException
    //   255	265	317	org/json/JSONException
    //   279	298	317	org/json/JSONException
    //   303	314	317	org/json/JSONException
    //   331	342	317	org/json/JSONException
    //   347	358	317	org/json/JSONException
    //   363	374	317	org/json/JSONException
    //   377	386	317	org/json/JSONException
    //   394	403	317	org/json/JSONException
    //   177	194	329	java/io/UnsupportedEncodingException
    //   194	211	345	java/io/UnsupportedEncodingException
    //   237	255	361	java/io/UnsupportedEncodingException
  }
  
  protected static void f(Context paramContext)
  {
    if (f) {
      if (!g(paramContext, "updateonlyonwifi").equals("1")) {
        break label79;
      }
    }
    label79:
    for (e = true;; e = false)
    {
      String str = g(paramContext, "updatedelay");
      if (!str.equals("0")) {
        h = Integer.parseInt(str) * 1000;
      }
      int i1 = Integer.parseInt(g(paramContext, "send_policy"));
      g = i1;
      if (i1 == 0) {
        g = 1;
      }
      return;
    }
  }
  
  protected static void f(Context paramContext, String paramString)
  {
    if (!paramString.trim().equals("")) {}
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      JSONArray localJSONArray = new JSONArray();
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("occurtime", System.currentTimeMillis());
      localJSONObject2.put("errmsg", URLEncoder.encode(paramString, "UTF-8"));
      localJSONArray.put(localJSONObject2);
      localJSONObject1.put("sid", p);
      localJSONObject1.put("errjsonary", localJSONArray);
      c.a("MobileAgentRun", "errJso" + localJSONObject1.toString());
      a(paramContext, localJSONObject1.toString(), 4);
      return;
    }
    catch (JSONException paramContext)
    {
      c.a("MobileAgentRun", "json exception,lost catch Exception");
      return;
    }
    catch (UnsupportedEncodingException paramContext)
    {
      c.a("MobileAgentRun", "unsupport utf-8,lost catch Exception");
    }
  }
  
  /* Error */
  protected static boolean f(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +465 -> 466
    //   4: aload_0
    //   5: aload_1
    //   6: invokestatic 502	com/iflytek/cloud/thirdparty/b:h	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   9: astore 5
    //   11: aload 5
    //   13: ldc 89
    //   15: invokevirtual 472	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   18: ifne +423 -> 441
    //   21: aload 5
    //   23: ldc_w 828
    //   26: invokevirtual 408	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   29: ifeq +430 -> 459
    //   32: iconst_1
    //   33: istore_3
    //   34: new 146	org/json/JSONObject
    //   37: dup
    //   38: aload 5
    //   40: invokespecial 506	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   43: astore 5
    //   45: aload_0
    //   46: invokestatic 677	com/iflytek/cloud/thirdparty/h:b	(Landroid/content/Context;)Ljava/lang/String;
    //   49: astore 6
    //   51: aload 5
    //   53: ldc_w 665
    //   56: iconst_1
    //   57: invokevirtual 497	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   60: pop
    //   61: aload 5
    //   63: ldc_w 667
    //   66: ldc_w 669
    //   69: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   72: pop
    //   73: aload 5
    //   75: ldc_w 671
    //   78: ldc_w 673
    //   81: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   84: pop
    //   85: aload 5
    //   87: ldc_w 675
    //   90: aload 6
    //   92: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   95: pop
    //   96: aload 5
    //   98: ldc_w 310
    //   101: aload_0
    //   102: invokestatic 157	com/iflytek/cloud/thirdparty/h:h	(Landroid/content/Context;)Ljava/lang/String;
    //   105: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   108: pop
    //   109: aload 5
    //   111: ldc_w 679
    //   114: aload_0
    //   115: invokestatic 681	com/iflytek/cloud/thirdparty/h:j	(Landroid/content/Context;)Ljava/lang/String;
    //   118: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   121: pop
    //   122: aload 5
    //   124: ldc_w 683
    //   127: aload_0
    //   128: invokestatic 686	com/iflytek/cloud/thirdparty/h:m	(Landroid/content/Context;)I
    //   131: invokevirtual 497	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   134: pop
    //   135: aload 5
    //   137: ldc_w 688
    //   140: aload_0
    //   141: invokestatic 689	com/iflytek/cloud/thirdparty/h:n	(Landroid/content/Context;)Ljava/lang/String;
    //   144: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   147: pop
    //   148: aload 5
    //   150: ldc_w 691
    //   153: invokestatic 326	java/lang/System:currentTimeMillis	()J
    //   156: invokevirtual 492	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   159: pop
    //   160: aload 5
    //   162: ldc_w 512
    //   165: aload_0
    //   166: invokestatic 514	com/iflytek/cloud/thirdparty/h:g	(Landroid/content/Context;)Ljava/lang/String;
    //   169: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   172: pop
    //   173: aload 5
    //   175: ldc_w 516
    //   178: invokestatic 518	com/iflytek/cloud/thirdparty/h:a	()Ljava/lang/String;
    //   181: ldc -95
    //   183: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   186: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   189: pop
    //   190: aload 5
    //   192: ldc_w 520
    //   195: invokestatic 522	com/iflytek/cloud/thirdparty/h:b	()Ljava/lang/String;
    //   198: ldc -95
    //   200: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   203: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   206: pop
    //   207: aload 5
    //   209: ldc_w 524
    //   212: invokestatic 526	com/iflytek/cloud/thirdparty/h:c	()Ljava/lang/String;
    //   215: ldc -95
    //   217: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   220: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   223: pop
    //   224: aload 5
    //   226: ldc_w 528
    //   229: aload_0
    //   230: invokestatic 530	com/iflytek/cloud/thirdparty/h:l	(Landroid/content/Context;)Ljava/lang/String;
    //   233: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   236: pop
    //   237: aload 5
    //   239: ldc_w 538
    //   242: aload_0
    //   243: invokestatic 540	com/iflytek/cloud/thirdparty/h:a	(Landroid/content/Context;)Ljava/lang/String;
    //   246: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   249: pop
    //   250: aload 5
    //   252: ldc_w 699
    //   255: aload_0
    //   256: invokestatic 159	com/iflytek/cloud/thirdparty/h:i	(Landroid/content/Context;)Ljava/lang/String;
    //   259: ldc -95
    //   261: invokestatic 167	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   264: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   267: pop
    //   268: aload_0
    //   269: ldc_w 830
    //   272: aload 5
    //   274: invokestatic 563	com/iflytek/cloud/thirdparty/b:a	(Landroid/content/Context;Ljava/lang/String;Lorg/json/JSONObject;)I
    //   277: istore 4
    //   279: iload 4
    //   281: iconst_1
    //   282: if_icmpeq +9 -> 291
    //   285: iload 4
    //   287: iconst_3
    //   288: if_icmpne +134 -> 422
    //   291: aload_2
    //   292: ifnonnull +118 -> 410
    //   295: aload_0
    //   296: iconst_3
    //   297: invokestatic 566	com/iflytek/cloud/thirdparty/b:b	(Landroid/content/Context;I)V
    //   300: aload_0
    //   301: aload_1
    //   302: invokestatic 568	com/iflytek/cloud/thirdparty/b:i	(Landroid/content/Context;Ljava/lang/String;)V
    //   305: ldc -3
    //   307: ldc_w 796
    //   310: invokestatic 402	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   313: pop
    //   314: iload_3
    //   315: ifeq +149 -> 464
    //   318: iload 4
    //   320: iconst_1
    //   321: if_icmpne +143 -> 464
    //   324: aload_0
    //   325: ldc_w 737
    //   328: invokestatic 832	com/iflytek/cloud/thirdparty/b:d	(Landroid/content/Context;Ljava/lang/String;)V
    //   331: goto +133 -> 464
    //   334: astore 6
    //   336: aload 5
    //   338: ldc_w 516
    //   341: ldc 89
    //   343: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   346: pop
    //   347: goto -157 -> 190
    //   350: astore_2
    //   351: aload_0
    //   352: aload_1
    //   353: invokestatic 568	com/iflytek/cloud/thirdparty/b:i	(Landroid/content/Context;Ljava/lang/String;)V
    //   356: aload_2
    //   357: invokevirtual 284	org/json/JSONException:printStackTrace	()V
    //   360: iconst_1
    //   361: ireturn
    //   362: astore 6
    //   364: aload 5
    //   366: ldc_w 520
    //   369: ldc 89
    //   371: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   374: pop
    //   375: goto -168 -> 207
    //   378: astore 6
    //   380: aload 5
    //   382: ldc_w 524
    //   385: ldc 89
    //   387: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   390: pop
    //   391: goto -167 -> 224
    //   394: astore 6
    //   396: aload 5
    //   398: ldc_w 699
    //   401: ldc 89
    //   403: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   406: pop
    //   407: goto -139 -> 268
    //   410: ldc -3
    //   412: ldc_w 798
    //   415: invokestatic 402	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   418: pop
    //   419: goto -105 -> 314
    //   422: iload 4
    //   424: iconst_2
    //   425: if_icmpne +14 -> 439
    //   428: ldc -3
    //   430: ldc_w 800
    //   433: invokestatic 412	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   436: pop
    //   437: iconst_0
    //   438: ireturn
    //   439: iconst_0
    //   440: ireturn
    //   441: aload_2
    //   442: ifnonnull +15 -> 457
    //   445: aload_0
    //   446: iconst_3
    //   447: invokestatic 566	com/iflytek/cloud/thirdparty/b:b	(Landroid/content/Context;I)V
    //   450: aload_0
    //   451: aload_1
    //   452: invokestatic 568	com/iflytek/cloud/thirdparty/b:i	(Landroid/content/Context;Ljava/lang/String;)V
    //   455: iconst_1
    //   456: ireturn
    //   457: iconst_0
    //   458: ireturn
    //   459: iconst_0
    //   460: istore_3
    //   461: goto -427 -> 34
    //   464: iconst_1
    //   465: ireturn
    //   466: aload_2
    //   467: astore 5
    //   469: goto -458 -> 11
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	472	0	paramContext	Context
    //   0	472	1	paramString1	String
    //   0	472	2	paramString2	String
    //   33	428	3	i1	int
    //   277	149	4	i2	int
    //   9	459	5	localObject	Object
    //   49	42	6	str	String
    //   334	1	6	localUnsupportedEncodingException1	UnsupportedEncodingException
    //   362	1	6	localUnsupportedEncodingException2	UnsupportedEncodingException
    //   378	1	6	localUnsupportedEncodingException3	UnsupportedEncodingException
    //   394	1	6	localUnsupportedEncodingException4	UnsupportedEncodingException
    // Exception table:
    //   from	to	target	type
    //   173	190	334	java/io/UnsupportedEncodingException
    //   34	173	350	org/json/JSONException
    //   173	190	350	org/json/JSONException
    //   190	207	350	org/json/JSONException
    //   207	224	350	org/json/JSONException
    //   224	250	350	org/json/JSONException
    //   250	268	350	org/json/JSONException
    //   268	279	350	org/json/JSONException
    //   295	314	350	org/json/JSONException
    //   324	331	350	org/json/JSONException
    //   336	347	350	org/json/JSONException
    //   364	375	350	org/json/JSONException
    //   380	391	350	org/json/JSONException
    //   396	407	350	org/json/JSONException
    //   410	419	350	org/json/JSONException
    //   428	437	350	org/json/JSONException
    //   190	207	362	java/io/UnsupportedEncodingException
    //   207	224	378	java/io/UnsupportedEncodingException
    //   250	268	394	java/io/UnsupportedEncodingException
  }
  
  private static String g(Context paramContext, String paramString)
  {
    return k(paramContext).getString(paramString, "0");
  }
  
  static void g(Context paramContext)
  {
    for (;;)
    {
      try
      {
        c.a("MobileAgentRun", "run into uploadlist :" + paramContext.getClass().getName());
        int i1 = g;
        if (i1 == 2) {}
        try
        {
          Thread.sleep(h);
          bool = true;
          System.currentTimeMillis();
          String str = n(paramContext);
          if ((str.equals("")) || (!bool))
          {
            c.a("MobileAgentRun", " finish uploadlist ");
            c.a("MobileAgentRun", "run out uploadlist :" + paramContext.getClass().getName());
            return;
          }
        }
        catch (InterruptedException localInterruptedException1)
        {
          localInterruptedException1.printStackTrace();
          continue;
        }
        if (!localInterruptedException1.substring(0, 6).equals(i)) {
          break label180;
        }
      }
      finally {}
      boolean bool = a(paramContext, localInterruptedException1);
      label147:
      if (bool) {}
      try
      {
        Thread.sleep(0L);
        c.a("MobileAgentRun", "finish a task : " + localInterruptedException1);
        continue;
        label180:
        if (localInterruptedException1.substring(0, 6).equals(j))
        {
          bool = e(paramContext, localInterruptedException1, null);
          break label147;
        }
        if (localInterruptedException1.substring(0, 6).equals(l))
        {
          bool = c(paramContext, localInterruptedException1);
          break label147;
        }
        if (localInterruptedException1.substring(0, 6).equals(m))
        {
          bool = b(paramContext, localInterruptedException1);
          break label147;
        }
        if (!localInterruptedException1.substring(0, 6).equals(k)) {
          break label147;
        }
        bool = f(paramContext, localInterruptedException1, null);
      }
      catch (InterruptedException localInterruptedException2)
      {
        for (;;)
        {
          localInterruptedException2.printStackTrace();
        }
      }
    }
  }
  
  /* Error */
  private static String h(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: aload_1
    //   5: invokevirtual 861	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   8: astore_1
    //   9: aload_1
    //   10: astore_3
    //   11: sipush 10000
    //   14: newarray <illegal type>
    //   16: astore 5
    //   18: ldc 89
    //   20: astore_0
    //   21: aload_1
    //   22: astore_3
    //   23: aload_1
    //   24: aload 5
    //   26: invokevirtual 867	java/io/FileInputStream:read	([B)I
    //   29: istore_2
    //   30: iload_2
    //   31: iconst_m1
    //   32: if_icmpne +20 -> 52
    //   35: aload_0
    //   36: astore_3
    //   37: aload_1
    //   38: ifnull +9 -> 47
    //   41: aload_1
    //   42: invokevirtual 868	java/io/FileInputStream:close	()V
    //   45: aload_0
    //   46: astore_3
    //   47: ldc 2
    //   49: monitorexit
    //   50: aload_3
    //   51: areturn
    //   52: aload_1
    //   53: astore_3
    //   54: new 55	java/lang/StringBuilder
    //   57: dup
    //   58: aload_0
    //   59: invokestatic 173	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   62: invokespecial 61	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   65: new 169	java/lang/String
    //   68: dup
    //   69: aload 5
    //   71: iconst_0
    //   72: iload_2
    //   73: invokespecial 871	java/lang/String:<init>	([BII)V
    //   76: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: astore 4
    //   84: aload 4
    //   86: astore_0
    //   87: goto -66 -> 21
    //   90: astore 4
    //   92: aconst_null
    //   93: astore_1
    //   94: ldc 89
    //   96: astore_0
    //   97: aload_1
    //   98: astore_3
    //   99: aload 4
    //   101: invokevirtual 872	java/io/FileNotFoundException:printStackTrace	()V
    //   104: aload_0
    //   105: astore_3
    //   106: aload_1
    //   107: ifnull -60 -> 47
    //   110: aload_1
    //   111: invokevirtual 868	java/io/FileInputStream:close	()V
    //   114: aload_0
    //   115: astore_3
    //   116: goto -69 -> 47
    //   119: astore_1
    //   120: aload_1
    //   121: invokevirtual 282	java/io/IOException:printStackTrace	()V
    //   124: aload_0
    //   125: astore_3
    //   126: goto -79 -> 47
    //   129: astore_0
    //   130: ldc 2
    //   132: monitorexit
    //   133: aload_0
    //   134: athrow
    //   135: astore 4
    //   137: aconst_null
    //   138: astore_1
    //   139: ldc 89
    //   141: astore_0
    //   142: aload_1
    //   143: astore_3
    //   144: aload 4
    //   146: invokevirtual 282	java/io/IOException:printStackTrace	()V
    //   149: aload_0
    //   150: astore_3
    //   151: aload_1
    //   152: ifnull -105 -> 47
    //   155: aload_1
    //   156: invokevirtual 868	java/io/FileInputStream:close	()V
    //   159: aload_0
    //   160: astore_3
    //   161: goto -114 -> 47
    //   164: astore_1
    //   165: aload_1
    //   166: invokevirtual 282	java/io/IOException:printStackTrace	()V
    //   169: aload_0
    //   170: astore_3
    //   171: goto -124 -> 47
    //   174: astore_0
    //   175: aconst_null
    //   176: astore_3
    //   177: aload_3
    //   178: ifnull +7 -> 185
    //   181: aload_3
    //   182: invokevirtual 868	java/io/FileInputStream:close	()V
    //   185: aload_0
    //   186: athrow
    //   187: astore_1
    //   188: aload_1
    //   189: invokevirtual 282	java/io/IOException:printStackTrace	()V
    //   192: goto -7 -> 185
    //   195: astore_1
    //   196: aload_1
    //   197: invokevirtual 282	java/io/IOException:printStackTrace	()V
    //   200: aload_0
    //   201: astore_3
    //   202: goto -155 -> 47
    //   205: astore_0
    //   206: goto -29 -> 177
    //   209: astore 4
    //   211: ldc 89
    //   213: astore_0
    //   214: goto -72 -> 142
    //   217: astore 4
    //   219: goto -77 -> 142
    //   222: astore 4
    //   224: ldc 89
    //   226: astore_0
    //   227: goto -130 -> 97
    //   230: astore 4
    //   232: goto -135 -> 97
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	235	0	paramContext	Context
    //   0	235	1	paramString	String
    //   29	44	2	i1	int
    //   10	192	3	localObject	Object
    //   82	3	4	str	String
    //   90	10	4	localFileNotFoundException1	java.io.FileNotFoundException
    //   135	10	4	localIOException1	java.io.IOException
    //   209	1	4	localIOException2	java.io.IOException
    //   217	1	4	localIOException3	java.io.IOException
    //   222	1	4	localFileNotFoundException2	java.io.FileNotFoundException
    //   230	1	4	localFileNotFoundException3	java.io.FileNotFoundException
    //   16	54	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   3	9	90	java/io/FileNotFoundException
    //   110	114	119	java/io/IOException
    //   41	45	129	finally
    //   110	114	129	finally
    //   120	124	129	finally
    //   155	159	129	finally
    //   165	169	129	finally
    //   181	185	129	finally
    //   185	187	129	finally
    //   188	192	129	finally
    //   196	200	129	finally
    //   3	9	135	java/io/IOException
    //   155	159	164	java/io/IOException
    //   3	9	174	finally
    //   181	185	187	java/io/IOException
    //   41	45	195	java/io/IOException
    //   11	18	205	finally
    //   23	30	205	finally
    //   54	84	205	finally
    //   99	104	205	finally
    //   144	149	205	finally
    //   11	18	209	java/io/IOException
    //   23	30	217	java/io/IOException
    //   54	84	217	java/io/IOException
    //   11	18	222	java/io/FileNotFoundException
    //   23	30	230	java/io/FileNotFoundException
    //   54	84	230	java/io/FileNotFoundException
  }
  
  private static void h(Context paramContext)
  {
    c.a("MobileAgentRun", "run into onresumep :" + paramContext.getClass().getName());
    try
    {
      c.a("page pro", "do resume start: " + paramContext.getClass().getName());
      b(paramContext, j(paramContext));
      c.a("MobileAgentRun", "run out onresume :" + paramContext.getClass().getName());
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private static void h(Context paramContext, String paramString1, String paramString2)
  {
    c.a("MobileAgentAPI", "run in onEvent [" + paramString1 + "," + paramString2 + "]");
    if (paramContext == null)
    {
      Log.e("MobileAgent", "Exception occurent in onEvent ,context cann't be null");
      return;
    }
    if ((TextUtils.isEmpty(paramString1)) || (h.a(paramString1, 100))) {
      Log.e("MobileAgent", "Exception occurent in onEvent ,channelID cann't be empty or length more than 100");
    }
    if (h.a(paramString2, 900)) {
      Log.e("MobileAgent", "Exception occurent in onEvent ,channelID cann't be empty or length more than 100");
    }
    new d(paramContext, 11, paramString1, paramString2).start();
  }
  
  private static String i(Context paramContext, String paramString1, String paramString2)
  {
    long l2 = 0L;
    long l4 = System.currentTimeMillis();
    long l1;
    if (paramString1.equals("onResume"))
    {
      o = l4;
      l1 = l2;
    }
    for (;;)
    {
      String str = paramString2;
      if (paramString2 == null) {
        str = "";
      }
      paramString2 = new StringBuffer();
      paramString2.append(str);
      paramString2.append(paramString1);
      paramString2.append("|");
      paramString2.append(paramContext.getClass().getName());
      paramString2.append("|");
      paramString2.append(l4);
      paramString2.append("|");
      paramString2.append(l1);
      paramString2.append("|");
      paramString2.append(n);
      paramString2.append("\n");
      n = paramContext.getClass().getName();
      return paramString2.toString();
      l1 = l2;
      if (paramString1.equals("onPause"))
      {
        l1 = l2;
        if (n.equals(paramContext.getClass().getName()))
        {
          long l3 = l4 - o;
          if (l3 > 12000000L)
          {
            l1 = 300000L;
          }
          else
          {
            l1 = l2;
            if (l3 >= 0L) {
              l1 = l3;
            }
          }
        }
      }
    }
  }
  
  private static void i(Context paramContext)
  {
    c.a("MobileAgentRun", "run into onpausep :" + paramContext.getClass().getName());
    Object localObject = l(paramContext);
    String str = ((SharedPreferences)localObject).getString("activities", null);
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putLong("endTime", System.currentTimeMillis());
    ((SharedPreferences.Editor)localObject).putString("activities", i(paramContext, "onPause", str));
    ((SharedPreferences.Editor)localObject).commit();
    c.a("MobileAgentRun", "run out onpausep :" + paramContext.getClass().getName());
  }
  
  private static void i(Context paramContext, String paramString)
  {
    if (paramString != null)
    {
      paramContext.deleteFile(paramString);
      j(paramContext, paramString);
    }
  }
  
  private static void j(Context paramContext, String paramString)
  {
    synchronized (q)
    {
      paramContext = c(paramContext);
      paramString = paramContext.getString("uploadList", "").replace(paramString + "|", "");
      paramContext.edit().putString("uploadList", paramString).commit();
      return;
    }
  }
  
  private static boolean j(Context paramContext)
  {
    boolean bool2 = true;
    c.a("MobileAgentRun", "run into sessionpolicy");
    String str = h.h(paramContext);
    SharedPreferences localSharedPreferences = l(paramContext);
    Object localObject = localSharedPreferences.getString("sessionId", null);
    if (a(localSharedPreferences))
    {
      if (localObject != null)
      {
        a(paramContext, localSharedPreferences, true);
        localObject = localSharedPreferences.edit();
        long l1 = localSharedPreferences.getLong("readFlowRev", 0L);
        long l2 = localSharedPreferences.getLong("readFlowSnd", 0L);
        ((SharedPreferences.Editor)localObject).clear();
        ((SharedPreferences.Editor)localObject).putLong("readFlowRev", Long.valueOf(l1).longValue()).commit();
        ((SharedPreferences.Editor)localObject).putLong("readFlowSnd", Long.valueOf(l2).longValue()).commit();
      }
      a(paramContext, str, localSharedPreferences);
      a(paramContext, localSharedPreferences, false);
      bool1 = bool2;
      if (a(paramContext, 1)) {
        e(paramContext);
      }
    }
    for (boolean bool1 = bool2;; bool1 = false)
    {
      c.a("MobileAgentRun", "run out sessionpolicy");
      return bool1;
      str = localSharedPreferences.getString("activities", null);
      localObject = localSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject).putString("activities", i(paramContext, "onResume", str));
      ((SharedPreferences.Editor)localObject).putLong("lastResumeTime", System.currentTimeMillis());
      ((SharedPreferences.Editor)localObject).commit();
      if ((g == 1) || (str.getBytes().length > 10000)) {
        a(paramContext, localSharedPreferences, false);
      }
    }
  }
  
  private static SharedPreferences k(Context paramContext)
  {
    String str = paramContext.getPackageName();
    return paramContext.getSharedPreferences(b + "MoblieAgent_config_" + str, 0);
  }
  
  private static SharedPreferences l(Context paramContext)
  {
    String str = paramContext.getPackageName();
    return paramContext.getSharedPreferences(b + "MoblieAgent_state_" + str, 0);
  }
  
  private static long m(Context paramContext)
  {
    try
    {
      long l1 = c(paramContext).getLong("uploadpopindex", 0L);
      return l1;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  private static String n(Context paramContext)
  {
    Object localObject = q;
    String str1 = "";
    try
    {
      String str2 = c(paramContext).getString("uploadList", "");
      paramContext = str1;
      if (!str2.equals("")) {
        paramContext = str2.substring(0, str2.indexOf("|"));
      }
      return paramContext;
    }
    finally {}
  }
  
  private static boolean o(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.getType() == 1);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */