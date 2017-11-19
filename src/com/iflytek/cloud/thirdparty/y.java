package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.cloud.RequestListener;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.util.FileDownloadListener;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class y
{
  private static String f = "http://wke.openspeech.cn/wakeup/";
  public V.a a = new V.a()
  {
    public void a(SpeechError paramAnonymousSpeechError)
    {
      if (paramAnonymousSpeechError != null) {
        ad.a("query resource error. errorcode:" + paramAnonymousSpeechError.getErrorCode());
      }
      for (;;)
      {
        if (y.b(y.this) != null) {
          y.b(y.this).onCompleted(paramAnonymousSpeechError);
        }
        return;
        ad.c("query resource succeed");
      }
    }
    
    public void a(V paramAnonymousV, byte[] paramAnonymousArrayOfByte)
    {
      try
      {
        paramAnonymousV = new String(paramAnonymousArrayOfByte, "utf-8");
        ad.c("updateInfo:" + paramAnonymousV);
        paramAnonymousV = new JSONObject(paramAnonymousV);
        try
        {
          int i = Integer.valueOf((String)paramAnonymousV.remove("netval")).intValue();
          N.a(y.a(y.this)).a("ivw_netval", i);
          i = paramAnonymousV.getInt("ret");
          if (i != 0)
          {
            paramAnonymousV = paramAnonymousV.getString("sid");
            ad.a("query ivw res sid:" + paramAnonymousV);
            if (y.b(y.this) != null)
            {
              paramAnonymousArrayOfByte = new Bundle();
              paramAnonymousArrayOfByte.putString("session_id", paramAnonymousV);
              y.b(y.this).onEvent(20001, paramAnonymousArrayOfByte);
            }
            a(new SpeechError(i));
            return;
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            ad.a(localException);
          }
        }
        if (y.b(y.this) == null) {
          break label221;
        }
      }
      catch (Exception paramAnonymousV)
      {
        ad.a(paramAnonymousV);
        a(new SpeechError(20004));
        return;
      }
      y.b(y.this).onBufferReceived(paramAnonymousArrayOfByte);
      label221:
      a(null);
    }
  };
  private Context b = null;
  private V c = null;
  private RequestListener d = null;
  private R e = null;
  
  public y(Context paramContext)
  {
    this.b = paramContext;
    this.e = R.a(this.b);
    this.c = new V();
  }
  
  /* Error */
  public static JSONObject a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_1
    //   4: invokestatic 58	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   7: ifeq +15 -> 22
    //   10: aload_0
    //   11: ldc 60
    //   13: invokestatic 62	com/iflytek/cloud/thirdparty/y:b	(Ljava/lang/String;Ljava/lang/String;)Lorg/json/JSONObject;
    //   16: astore_0
    //   17: ldc 2
    //   19: monitorexit
    //   20: aload_0
    //   21: areturn
    //   22: aload_0
    //   23: ldc 60
    //   25: invokestatic 62	com/iflytek/cloud/thirdparty/y:b	(Ljava/lang/String;Ljava/lang/String;)Lorg/json/JSONObject;
    //   28: astore_2
    //   29: aload_2
    //   30: ifnonnull +8 -> 38
    //   33: aconst_null
    //   34: astore_0
    //   35: goto -18 -> 17
    //   38: aload_1
    //   39: ldc 60
    //   41: invokestatic 62	com/iflytek/cloud/thirdparty/y:b	(Ljava/lang/String;Ljava/lang/String;)Lorg/json/JSONObject;
    //   44: astore_1
    //   45: aload_2
    //   46: astore_0
    //   47: aload_1
    //   48: ifnull -31 -> 17
    //   51: aload_2
    //   52: aload_1
    //   53: invokestatic 65	com/iflytek/cloud/thirdparty/y:b	(Lorg/json/JSONObject;Lorg/json/JSONObject;)Lorg/json/JSONObject;
    //   56: astore_0
    //   57: goto -40 -> 17
    //   60: astore_0
    //   61: ldc 2
    //   63: monitorexit
    //   64: aload_0
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	paramString1	String
    //   0	66	1	paramString2	String
    //   28	24	2	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   3	17	60	finally
    //   22	29	60	finally
    //   38	45	60	finally
    //   51	57	60	finally
  }
  
  public static boolean a(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    if (paramJSONObject1 != null)
    {
      if (paramJSONObject2 == null) {}
      for (;;)
      {
        return true;
        try
        {
          if (paramJSONObject1.getString("resver").equalsIgnoreCase("unkown")) {
            return false;
          }
          if (!paramJSONObject2.getString("resver").equalsIgnoreCase("unkown")) {
            if ((P.a("appid", paramJSONObject1, paramJSONObject2)) && (P.a("resid", paramJSONObject1, paramJSONObject2)) && (P.a("restype", paramJSONObject1, paramJSONObject2)) && (P.a("wakelist", paramJSONObject1, paramJSONObject2)))
            {
              long l1 = paramJSONObject1.getLong("restime");
              long l2 = paramJSONObject2.getLong("restime");
              if (l1 > l2) {}
            }
            else
            {
              return false;
            }
          }
        }
        catch (JSONException paramJSONObject1)
        {
          ad.a(paramJSONObject1);
          return false;
        }
      }
    }
    return false;
  }
  
  public static JSONObject b(String paramString1, String paramString2)
  {
    MSCSessionInfo localMSCSessionInfo = new MSCSessionInfo();
    try
    {
      ad.a("getIvwResInfo resPath: " + paramString1);
      int i = MSC.QIVWGetResInfo(paramString1.getBytes(), paramString2.getBytes(), localMSCSessionInfo);
      if (i != 0)
      {
        ad.b("read ivw resoure error:" + i);
        return null;
      }
    }
    catch (UnsatisfiedLinkError paramString1)
    {
      ad.b("get res info error, unsatisfiedlinkerror");
      return null;
    }
    catch (Exception paramString1)
    {
      ad.b("get res info error");
      return null;
    }
    try
    {
      paramString2 = new String(localMSCSessionInfo.buffer, "utf-8");
      ad.c("resInfo:" + new String(localMSCSessionInfo.buffer));
      return c(paramString1, paramString2);
    }
    catch (UnsupportedEncodingException paramString1)
    {
      ad.a(paramString1);
    }
    return null;
  }
  
  private static JSONObject b(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    try
    {
      if (paramJSONObject1.getString("resver").equalsIgnoreCase("unkown"))
      {
        ad.c("user ivw resver unkown");
        return null;
      }
      if ((paramJSONObject2.getString("resver").equalsIgnoreCase("unkown")) || (!paramJSONObject1.getString("resver").equalsIgnoreCase(paramJSONObject2.getString("resver"))))
      {
        ad.c("cfg ivw resver unkown or unequal");
        return paramJSONObject1;
      }
    }
    catch (JSONException paramJSONObject1)
    {
      ad.a(paramJSONObject1);
      return null;
    }
    JSONObject localJSONObject = paramJSONObject1;
    if (P.a("appid", paramJSONObject1, paramJSONObject2))
    {
      localJSONObject = paramJSONObject1;
      if (P.a("resid", paramJSONObject1, paramJSONObject2))
      {
        localJSONObject = paramJSONObject1;
        if (P.a("restype", paramJSONObject1, paramJSONObject2))
        {
          localJSONObject = paramJSONObject1;
          if (P.a("wakelist", paramJSONObject1, paramJSONObject2))
          {
            boolean bool = P.b("restime", paramJSONObject1, paramJSONObject2);
            localJSONObject = paramJSONObject1;
            if (bool) {
              localJSONObject = paramJSONObject2;
            }
          }
        }
      }
    }
    return localJSONObject;
  }
  
  /* Error */
  private static JSONObject c(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 72	org/json/JSONObject
    //   5: dup
    //   6: invokespecial 180	org/json/JSONObject:<init>	()V
    //   9: astore_3
    //   10: aload_3
    //   11: ldc -74
    //   13: aload_0
    //   14: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   17: pop
    //   18: new 72	org/json/JSONObject
    //   21: dup
    //   22: new 188	org/json/JSONTokener
    //   25: dup
    //   26: aload_1
    //   27: invokespecial 190	org/json/JSONTokener:<init>	(Ljava/lang/String;)V
    //   30: invokespecial 193	org/json/JSONObject:<init>	(Lorg/json/JSONTokener;)V
    //   33: astore_1
    //   34: aload_1
    //   35: ldc -61
    //   37: invokevirtual 199	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   40: astore_0
    //   41: aload_3
    //   42: ldc 86
    //   44: aload_0
    //   45: ldc 86
    //   47: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   50: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   53: pop
    //   54: aload_3
    //   55: ldc 93
    //   57: aload_0
    //   58: ldc 93
    //   60: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   63: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   66: pop
    //   67: aload_0
    //   68: ldc 95
    //   70: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   73: astore 4
    //   75: aload_3
    //   76: ldc 95
    //   78: aload 4
    //   80: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   83: pop
    //   84: ldc -56
    //   86: aload 4
    //   88: invokevirtual 84	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   91: ifne +29 -> 120
    //   94: aload_3
    //   95: ldc -54
    //   97: aload_0
    //   98: ldc -54
    //   100: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   103: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   106: pop
    //   107: aload_3
    //   108: ldc -52
    //   110: aload_0
    //   111: ldc -52
    //   113: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   116: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   119: pop
    //   120: aload_1
    //   121: ldc -50
    //   123: invokevirtual 199	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   126: ldc -48
    //   128: invokevirtual 212	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   131: astore 4
    //   133: ldc 60
    //   135: astore_0
    //   136: iload_2
    //   137: aload 4
    //   139: invokevirtual 218	org/json/JSONArray:length	()I
    //   142: if_icmpge +108 -> 250
    //   145: new 120	java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   152: aload_0
    //   153: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: aload 4
    //   158: iload_2
    //   159: invokevirtual 221	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   162: ldc -33
    //   164: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   167: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: astore_0
    //   174: new 120	java/lang/StringBuilder
    //   177: dup
    //   178: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   181: aload_0
    //   182: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: ldc -31
    //   187: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: astore_0
    //   194: new 120	java/lang/StringBuilder
    //   197: dup
    //   198: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   201: aload_0
    //   202: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: aload 4
    //   207: iload_2
    //   208: invokevirtual 221	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   211: ldc -29
    //   213: invokevirtual 231	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   216: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   219: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   222: astore_0
    //   223: new 120	java/lang/StringBuilder
    //   226: dup
    //   227: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   230: aload_0
    //   231: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: ldc -23
    //   236: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: astore_0
    //   243: iload_2
    //   244: iconst_1
    //   245: iadd
    //   246: istore_2
    //   247: goto -111 -> 136
    //   250: aload_3
    //   251: ldc 97
    //   253: aload_0
    //   254: iconst_0
    //   255: aload_0
    //   256: invokevirtual 234	java/lang/String:length	()I
    //   259: iconst_1
    //   260: isub
    //   261: invokevirtual 238	java/lang/String:substring	(II)Ljava/lang/String;
    //   264: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   267: pop
    //   268: aload_3
    //   269: ldc 70
    //   271: aload_1
    //   272: ldc 70
    //   274: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   277: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   280: pop
    //   281: aload_3
    //   282: ldc 99
    //   284: aload_1
    //   285: ldc 99
    //   287: invokevirtual 103	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   290: invokevirtual 241	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   293: pop
    //   294: ldc 60
    //   296: astore_0
    //   297: invokestatic 247	com/iflytek/cloud/SpeechUtility:getUtility	()Lcom/iflytek/cloud/SpeechUtility;
    //   300: ifnull +12 -> 312
    //   303: invokestatic 247	com/iflytek/cloud/SpeechUtility:getUtility	()Lcom/iflytek/cloud/SpeechUtility;
    //   306: ldc -7
    //   308: invokevirtual 252	com/iflytek/cloud/SpeechUtility:getParameter	(Ljava/lang/String;)Ljava/lang/String;
    //   311: astore_0
    //   312: aload_3
    //   313: ldc -2
    //   315: aload_0
    //   316: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   319: pop
    //   320: new 120	java/lang/StringBuilder
    //   323: dup
    //   324: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   327: ldc_w 256
    //   330: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   333: aload_3
    //   334: invokevirtual 257	org/json/JSONObject:toString	()Ljava/lang/String;
    //   337: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   343: invokestatic 171	com/iflytek/cloud/thirdparty/ad:c	(Ljava/lang/String;)V
    //   346: aload_3
    //   347: areturn
    //   348: astore_0
    //   349: aload_3
    //   350: ldc 70
    //   352: ldc 78
    //   354: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   357: pop
    //   358: goto -77 -> 281
    //   361: astore_0
    //   362: ldc_w 259
    //   365: invokestatic 134	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   368: aconst_null
    //   369: areturn
    //   370: astore_0
    //   371: aload_3
    //   372: ldc 99
    //   374: lconst_0
    //   375: invokevirtual 241	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   378: pop
    //   379: goto -85 -> 294
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	382	0	paramString1	String
    //   0	382	1	paramString2	String
    //   1	246	2	i	int
    //   9	363	3	localJSONObject	JSONObject
    //   73	133	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   268	281	348	org/json/JSONException
    //   10	120	361	org/json/JSONException
    //   120	133	361	org/json/JSONException
    //   136	243	361	org/json/JSONException
    //   250	268	361	org/json/JSONException
    //   297	312	361	org/json/JSONException
    //   312	346	361	org/json/JSONException
    //   349	358	361	org/json/JSONException
    //   371	379	361	org/json/JSONException
    //   281	294	370	org/json/JSONException
  }
  
  public int a(String paramString1, String paramString2, String paramString3, FileDownloadListener paramFileDownloadListener)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return 20012;
    }
    return this.e.a(paramString1, paramString2, paramString3, paramFileDownloadListener);
  }
  
  public int a(JSONObject paramJSONObject, RequestListener paramRequestListener)
  {
    if (paramJSONObject == null) {
      return 20014;
    }
    if (paramRequestListener != null) {}
    try
    {
      this.d = paramRequestListener;
      paramRequestListener = new JSONObject();
      paramRequestListener.put("header", ai.d(this.b, new ag()));
      paramRequestListener.put("info", paramJSONObject);
      ad.c("post data:" + paramRequestListener);
      paramJSONObject = Q.b(paramRequestListener.toString().getBytes());
      this.c.a(1);
      this.c.a(f, null, paramJSONObject);
      this.c.a(this.a);
      return 0;
    }
    catch (Exception paramJSONObject)
    {
      ad.a(paramJSONObject);
    }
    return 20003;
  }
  
  public void a()
  {
    if (this.c != null)
    {
      this.c.a();
      this.c = null;
    }
    this.d = null;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */