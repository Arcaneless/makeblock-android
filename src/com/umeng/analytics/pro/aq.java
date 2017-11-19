package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.AnalyticsConfig;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class aq
  implements au, az
{
  private static Context j;
  private static final String q = "thtstart";
  private static final String r = "gkvc";
  private static final String s = "ekvc";
  String a = null;
  private cc b = null;
  private be c = null;
  private bh d = null;
  private bg e = null;
  private bi f = null;
  private a g = null;
  private af.a h = null;
  private long i = 0L;
  private int k = 10;
  private JSONArray l = new JSONArray();
  private final int m = 5000;
  private int n = 0;
  private int o = 0;
  private long p = 0L;
  private final long t = 28800000L;
  
  public aq(Context paramContext)
  {
    j = paramContext;
    this.c = new be(paramContext);
    this.b = cc.a(paramContext);
    this.h = af.a(paramContext).b();
    this.g = new a();
    this.e = bg.a(j);
    this.d = bh.a(j);
    this.f = bi.a(j, this.c);
    paramContext = ba.a(j);
    this.p = paramContext.getLong("thtstart", 0L);
    this.n = paramContext.getInt("gkvc", 0);
    this.o = paramContext.getInt("ekvc", 0);
    this.a = af.a(j).b().b(null);
  }
  
  private void a(int paramInt)
  {
    c(a(new int[] { paramInt, (int)(System.currentTimeMillis() - this.c.m()) }));
    bz.a(new cb()
    {
      public void a()
      {
        aq.this.a();
      }
    }, paramInt);
  }
  
  private void a(JSONObject paramJSONObject, String paramString1, String paramString2)
    throws JSONException
  {
    String str = paramString1;
    if (TextUtils.isEmpty(paramString1)) {
      str = "0";
    }
    paramJSONObject.put("$pr_ve", str);
    paramString1 = paramString2;
    if (TextUtils.isEmpty(paramString2)) {
      paramString1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
    }
    paramJSONObject.put("$ud_da", paramString1);
  }
  
  private boolean a(boolean paramBoolean)
  {
    if (!bv.l(j))
    {
      by.e("network is unavailable");
      return false;
    }
    if (this.c.f()) {
      return true;
    }
    return this.g.b(paramBoolean).a(paramBoolean);
  }
  
  private String[] a(SharedPreferences.Editor paramEditor, SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    paramString2 = paramSharedPreferences.getString("pre_version", "");
    paramString1 = paramSharedPreferences.getString("pre_date", "");
    paramSharedPreferences = paramSharedPreferences.getString("cur_version", "");
    String str = bv.b(j);
    if (!paramSharedPreferences.equals(str))
    {
      paramString1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
      paramEditor.putString("pre_version", paramSharedPreferences);
      paramEditor.putString("pre_date", paramString1);
      paramEditor.putString("cur_version", str);
      paramEditor.commit();
    }
    for (paramEditor = paramString1;; paramEditor = paramString1)
    {
      return new String[] { paramSharedPreferences, paramEditor };
      paramSharedPreferences = paramString2;
    }
  }
  
  private void b(int paramInt)
  {
    a(paramInt);
  }
  
  private void b(JSONObject paramJSONObject)
  {
    try
    {
      if (2050 == paramJSONObject.getInt("__t"))
      {
        if (!c(this.n)) {
          return;
        }
        this.n += 1;
      }
      for (;;)
      {
        if (this.l.length() > this.k)
        {
          w.a(j).a(this.l);
          this.l = new JSONArray();
        }
        if (this.p == 0L) {
          this.p = System.currentTimeMillis();
        }
        this.l.put(paramJSONObject);
        return;
        if (2049 == paramJSONObject.getInt("__t"))
        {
          if (!c(this.o)) {
            break;
          }
          this.o += 1;
        }
      }
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  private void c(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    for (;;)
    {
      return;
      try
      {
        ad localad = ad.a(j);
        localad.a();
        for (;;)
        {
          try
          {
            Object localObject = localad.b();
            localObject = Base64.encodeToString(new cp().a((cg)localObject), 0);
            if (!TextUtils.isEmpty((CharSequence)localObject))
            {
              JSONObject localJSONObject = paramJSONObject.getJSONObject("header");
              localJSONObject.put("id_tracking", localObject);
              paramJSONObject.put("header", localJSONObject);
            }
            paramJSONObject = String.valueOf(paramJSONObject).getBytes();
            break label162;
            if (bt.a(j, paramJSONObject)) {
              break;
            }
            if (e())
            {
              paramJSONObject = aa.b(j, AnalyticsConfig.getAppkey(j), paramJSONObject);
              paramJSONObject = paramJSONObject.c();
              localObject = cc.a(j);
              ((cc)localObject).g();
              ((cc)localObject).a(paramJSONObject);
              localad.d();
              return;
            }
            paramJSONObject = aa.a(j, AnalyticsConfig.getAppkey(j), paramJSONObject);
            continue;
          }
          catch (Exception localException)
          {
            continue;
          }
          label162:
          if (paramJSONObject == null) {
            break;
          }
        }
        return;
      }
      catch (Exception paramJSONObject) {}
    }
  }
  
  private boolean c(int paramInt)
  {
    if (this.p != 0L)
    {
      if (System.currentTimeMillis() - this.p <= 28800000L) {
        break label30;
      }
      f();
    }
    label30:
    while (paramInt <= 5000) {
      return true;
    }
    return false;
  }
  
  private void d()
  {
    try
    {
      if (this.b.h())
      {
        localObject = new bc(j, this.c);
        ((bc)localObject).a(this);
        if (this.d.d()) {
          ((bc)localObject).b(true);
        }
        ((bc)localObject).a();
        return;
      }
      Object localObject = a(new int[0]);
      if (((JSONObject)localObject).length() > 0)
      {
        bc localbc = new bc(j, this.c);
        localbc.a(this);
        if (this.d.d()) {
          localbc.b(true);
        }
        localbc.a((JSONObject)localObject);
        localbc.a(e());
        localbc.a();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      if (localThrowable != null) {
        localThrowable.printStackTrace();
      }
    }
  }
  
  private void d(JSONObject paramJSONObject)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put(bg.a(j).f(), bg.a(j).e());
    paramJSONObject.put("group_info", localJSONObject);
  }
  
  private boolean e()
  {
    switch (this.h.c(-1))
    {
    case 0: 
    default: 
      return false;
    case 1: 
      return true;
    }
    return AnalyticsConfig.sEncrypt;
  }
  
  private void f()
  {
    this.n = 0;
    this.o = 0;
    this.p = System.currentTimeMillis();
  }
  
  /* Error */
  protected JSONObject a(int... paramVarArgs)
  {
    // Byte code:
    //   0: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   3: invokestatic 355	com/umeng/analytics/AnalyticsConfig:getAppkey	(Landroid/content/Context;)Ljava/lang/String;
    //   6: invokestatic 184	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   9: ifeq +11 -> 20
    //   12: ldc_w 416
    //   15: invokestatic 232	com/umeng/analytics/pro/by:e	(Ljava/lang/String;)V
    //   18: aconst_null
    //   19: areturn
    //   20: aload_0
    //   21: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   24: invokevirtual 418	com/umeng/analytics/pro/aq:a	(Landroid/content/Context;)V
    //   27: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   30: invokestatic 295	com/umeng/analytics/pro/w:a	(Landroid/content/Context;)Lcom/umeng/analytics/pro/w;
    //   33: invokevirtual 421	com/umeng/analytics/pro/w:a	()Lorg/json/JSONObject;
    //   36: astore_3
    //   37: goto +1605 -> 1642
    //   40: new 190	org/json/JSONObject
    //   43: dup
    //   44: invokespecial 399	org/json/JSONObject:<init>	()V
    //   47: astore_2
    //   48: aload_2
    //   49: ldc_w 423
    //   52: invokevirtual 333	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   55: astore 4
    //   57: new 190	org/json/JSONObject
    //   60: dup
    //   61: aload 4
    //   63: invokevirtual 426	org/json/JSONObject:toString	()Ljava/lang/String;
    //   66: invokespecial 428	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   69: astore 5
    //   71: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   74: invokestatic 134	com/umeng/analytics/pro/ba:a	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   77: astore_3
    //   78: goto +1573 -> 1651
    //   81: aload_3
    //   82: ldc_w 430
    //   85: ldc -9
    //   87: invokeinterface 251 3 0
    //   92: astore 6
    //   94: aload 6
    //   96: invokestatic 184	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   99: ifne +14 -> 113
    //   102: aload 4
    //   104: ldc_w 430
    //   107: aload 6
    //   109: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   112: pop
    //   113: aload_0
    //   114: getfield 59	com/umeng/analytics/pro/aq:c	Lcom/umeng/analytics/pro/be;
    //   117: invokevirtual 235	com/umeng/analytics/pro/be:f	()Z
    //   120: ifeq +56 -> 176
    //   123: aload_0
    //   124: getfield 71	com/umeng/analytics/pro/aq:i	J
    //   127: lconst_0
    //   128: lcmp
    //   129: ifeq +47 -> 176
    //   132: new 190	org/json/JSONObject
    //   135: dup
    //   136: invokespecial 399	org/json/JSONObject:<init>	()V
    //   139: astore 6
    //   141: aload 6
    //   143: ldc_w 432
    //   146: aload_0
    //   147: getfield 71	com/umeng/analytics/pro/aq:i	J
    //   150: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   153: pop
    //   154: aload 4
    //   156: ldc_w 437
    //   159: aload 6
    //   161: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   164: pop
    //   165: aload 5
    //   167: ldc_w 437
    //   170: aload 6
    //   172: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   175: pop
    //   176: new 190	org/json/JSONObject
    //   179: dup
    //   180: invokespecial 399	org/json/JSONObject:<init>	()V
    //   183: astore 6
    //   185: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   188: invokestatic 442	com/umeng/analytics/pro/m:a	(Landroid/content/Context;)Lcom/umeng/analytics/pro/m;
    //   191: invokevirtual 444	com/umeng/analytics/pro/m:b	()Lorg/json/JSONObject;
    //   194: astore 7
    //   196: goto +1462 -> 1658
    //   199: aload 7
    //   201: invokevirtual 391	org/json/JSONObject:length	()I
    //   204: ifle +14 -> 218
    //   207: aload 6
    //   209: ldc_w 446
    //   212: aload 7
    //   214: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   217: pop
    //   218: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   221: invokestatic 442	com/umeng/analytics/pro/m:a	(Landroid/content/Context;)Lcom/umeng/analytics/pro/m;
    //   224: invokevirtual 448	com/umeng/analytics/pro/m:c	()Lorg/json/JSONObject;
    //   227: astore 7
    //   229: goto +1437 -> 1666
    //   232: aload 7
    //   234: invokevirtual 391	org/json/JSONObject:length	()I
    //   237: ifle +14 -> 251
    //   240: aload 6
    //   242: ldc_w 450
    //   245: aload 7
    //   247: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   250: pop
    //   251: aload 6
    //   253: invokevirtual 391	org/json/JSONObject:length	()I
    //   256: ifle +25 -> 281
    //   259: aload 4
    //   261: ldc_w 452
    //   264: aload 6
    //   266: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   269: pop
    //   270: aload 5
    //   272: ldc_w 452
    //   275: aload 6
    //   277: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   280: pop
    //   281: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   284: invokestatic 457	com/umeng/analytics/c:a	(Landroid/content/Context;)[Ljava/lang/String;
    //   287: astore 6
    //   289: aload 6
    //   291: ifnull +88 -> 379
    //   294: aload 6
    //   296: iconst_0
    //   297: aaload
    //   298: invokestatic 184	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   301: ifne +78 -> 379
    //   304: aload 6
    //   306: iconst_1
    //   307: aaload
    //   308: invokestatic 184	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   311: ifne +68 -> 379
    //   314: new 190	org/json/JSONObject
    //   317: dup
    //   318: invokespecial 399	org/json/JSONObject:<init>	()V
    //   321: astore 7
    //   323: aload 7
    //   325: ldc_w 459
    //   328: aload 6
    //   330: iconst_0
    //   331: aaload
    //   332: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   335: pop
    //   336: aload 7
    //   338: ldc_w 461
    //   341: aload 6
    //   343: iconst_1
    //   344: aaload
    //   345: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   348: pop
    //   349: aload 7
    //   351: invokevirtual 391	org/json/JSONObject:length	()I
    //   354: ifle +25 -> 379
    //   357: aload 4
    //   359: ldc_w 463
    //   362: aload 7
    //   364: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   367: pop
    //   368: aload 5
    //   370: ldc_w 463
    //   373: aload 7
    //   375: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   378: pop
    //   379: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   382: invokestatic 119	com/umeng/analytics/pro/bg:a	(Landroid/content/Context;)Lcom/umeng/analytics/pro/bg;
    //   385: invokevirtual 465	com/umeng/analytics/pro/bg:a	()Z
    //   388: ifeq +9 -> 397
    //   391: aload_0
    //   392: aload 4
    //   394: invokespecial 467	com/umeng/analytics/pro/aq:d	(Lorg/json/JSONObject;)V
    //   397: aload_0
    //   398: getfield 61	com/umeng/analytics/pro/aq:d	Lcom/umeng/analytics/pro/bh;
    //   401: aload 4
    //   403: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   406: invokevirtual 470	com/umeng/analytics/pro/bh:a	(Lorg/json/JSONObject;Landroid/content/Context;)V
    //   409: aload_1
    //   410: ifnull +77 -> 487
    //   413: aload_1
    //   414: arraylength
    //   415: iconst_2
    //   416: if_icmpne +71 -> 487
    //   419: new 190	org/json/JSONObject
    //   422: dup
    //   423: invokespecial 399	org/json/JSONObject:<init>	()V
    //   426: astore 6
    //   428: new 190	org/json/JSONObject
    //   431: dup
    //   432: invokespecial 399	org/json/JSONObject:<init>	()V
    //   435: astore 7
    //   437: aload 7
    //   439: ldc_w 472
    //   442: aload_1
    //   443: iconst_0
    //   444: iaload
    //   445: sipush 1000
    //   448: idiv
    //   449: invokevirtual 475	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   452: pop
    //   453: aload 7
    //   455: ldc_w 477
    //   458: aload_1
    //   459: iconst_1
    //   460: iaload
    //   461: invokevirtual 475	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   464: pop
    //   465: aload 6
    //   467: ldc_w 479
    //   470: aload 7
    //   472: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   475: pop
    //   476: aload 4
    //   478: ldc_w 481
    //   481: aload 6
    //   483: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   486: pop
    //   487: aload 4
    //   489: invokevirtual 391	org/json/JSONObject:length	()I
    //   492: ifle +978 -> 1470
    //   495: aload_2
    //   496: ldc_w 423
    //   499: aload 4
    //   501: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   504: pop
    //   505: new 190	org/json/JSONObject
    //   508: dup
    //   509: invokespecial 399	org/json/JSONObject:<init>	()V
    //   512: astore 4
    //   514: aload 4
    //   516: ldc_w 483
    //   519: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   522: invokestatic 355	com/umeng/analytics/AnalyticsConfig:getAppkey	(Landroid/content/Context;)Ljava/lang/String;
    //   525: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   528: pop
    //   529: aload 4
    //   531: ldc_w 485
    //   534: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   537: invokestatic 488	com/umeng/analytics/AnalyticsConfig:getChannel	(Landroid/content/Context;)Ljava/lang/String;
    //   540: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   543: pop
    //   544: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   547: invokestatic 491	com/umeng/analytics/AnalyticsConfig:getSecretKey	(Landroid/content/Context;)Ljava/lang/String;
    //   550: invokestatic 495	com/umeng/analytics/pro/bw:a	(Ljava/lang/String;)Ljava/lang/String;
    //   553: astore_1
    //   554: aload_1
    //   555: invokestatic 184	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   558: ifne +13 -> 571
    //   561: aload 4
    //   563: ldc_w 497
    //   566: aload_1
    //   567: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   570: pop
    //   571: aload 4
    //   573: ldc_w 499
    //   576: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   579: invokestatic 502	com/umeng/analytics/pro/bv:w	(Landroid/content/Context;)Ljava/lang/String;
    //   582: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   585: pop
    //   586: aload 4
    //   588: ldc_w 504
    //   591: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   594: invokestatic 506	com/umeng/analytics/pro/bv:t	(Landroid/content/Context;)Ljava/lang/String;
    //   597: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   600: pop
    //   601: aload 4
    //   603: ldc_w 508
    //   606: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   609: invokestatic 511	com/umeng/analytics/pro/bv:u	(Landroid/content/Context;)Ljava/lang/String;
    //   612: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   615: pop
    //   616: aload_3
    //   617: astore_1
    //   618: aload_3
    //   619: ifnonnull +1055 -> 1674
    //   622: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   625: invokestatic 134	com/umeng/analytics/pro/ba:a	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   628: astore_1
    //   629: goto +1045 -> 1674
    //   632: aload_1
    //   633: astore_3
    //   634: aload_1
    //   635: ldc_w 513
    //   638: ldc -9
    //   640: invokeinterface 251 3 0
    //   645: astore 6
    //   647: aload_1
    //   648: astore_3
    //   649: aload 6
    //   651: invokestatic 184	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   654: ifne +831 -> 1485
    //   657: aload_1
    //   658: astore_3
    //   659: aload 4
    //   661: ldc_w 515
    //   664: aload 6
    //   666: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   669: pop
    //   670: aload_1
    //   671: astore_3
    //   672: aload 4
    //   674: ldc_w 517
    //   677: aload_1
    //   678: ldc_w 519
    //   681: iconst_0
    //   682: invokeinterface 144 3 0
    //   687: invokevirtual 475	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   690: pop
    //   691: aload_1
    //   692: astore_3
    //   693: getstatic 522	com/umeng/analytics/AnalyticsConfig:mWrapperType	Ljava/lang/String;
    //   696: ifnull +33 -> 729
    //   699: getstatic 525	com/umeng/analytics/AnalyticsConfig:mWrapperVersion	Ljava/lang/String;
    //   702: ifnull +27 -> 729
    //   705: aload 4
    //   707: ldc_w 527
    //   710: getstatic 522	com/umeng/analytics/AnalyticsConfig:mWrapperType	Ljava/lang/String;
    //   713: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   716: pop
    //   717: aload 4
    //   719: ldc_w 529
    //   722: getstatic 525	com/umeng/analytics/AnalyticsConfig:mWrapperVersion	Ljava/lang/String;
    //   725: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   728: pop
    //   729: aload 4
    //   731: ldc_w 531
    //   734: ldc_w 533
    //   737: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   740: pop
    //   741: aload 4
    //   743: ldc_w 535
    //   746: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   749: invokestatic 538	com/umeng/analytics/AnalyticsConfig:getSDKVersion	(Landroid/content/Context;)Ljava/lang/String;
    //   752: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   755: pop
    //   756: aload 4
    //   758: ldc_w 540
    //   761: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   764: invokestatic 544	com/umeng/analytics/AnalyticsConfig:getVerticalType	(Landroid/content/Context;)I
    //   767: invokevirtual 475	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   770: pop
    //   771: aload 4
    //   773: ldc_w 546
    //   776: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   779: invokestatic 548	com/umeng/analytics/pro/bv:d	(Landroid/content/Context;)Ljava/lang/String;
    //   782: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   785: pop
    //   786: aload 4
    //   788: ldc_w 550
    //   791: invokestatic 552	com/umeng/analytics/pro/bv:a	()Ljava/lang/String;
    //   794: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   797: pop
    //   798: aload 4
    //   800: ldc_w 554
    //   803: ldc_w 533
    //   806: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   809: pop
    //   810: aload 4
    //   812: ldc_w 556
    //   815: getstatic 561	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   818: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   821: pop
    //   822: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   825: invokestatic 564	com/umeng/analytics/pro/bv:r	(Landroid/content/Context;)[I
    //   828: astore_1
    //   829: goto +854 -> 1683
    //   832: aload 4
    //   834: ldc_w 566
    //   837: new 568	java/lang/StringBuilder
    //   840: dup
    //   841: invokespecial 569	java/lang/StringBuilder:<init>	()V
    //   844: aload_1
    //   845: iconst_1
    //   846: iaload
    //   847: invokevirtual 573	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   850: ldc_w 575
    //   853: invokevirtual 578	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   856: aload_1
    //   857: iconst_0
    //   858: iaload
    //   859: invokevirtual 573	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   862: invokevirtual 579	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   865: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   868: pop
    //   869: aload 4
    //   871: ldc_w 581
    //   874: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   877: invokestatic 583	com/umeng/analytics/pro/bv:q	(Landroid/content/Context;)Ljava/lang/String;
    //   880: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   883: pop
    //   884: aload 4
    //   886: ldc_w 585
    //   889: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   892: invokestatic 587	com/umeng/analytics/pro/bv:c	(Landroid/content/Context;)Ljava/lang/String;
    //   895: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   898: pop
    //   899: aload 4
    //   901: ldc_w 589
    //   904: getstatic 594	android/os/Build:MODEL	Ljava/lang/String;
    //   907: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   910: pop
    //   911: aload 4
    //   913: ldc_w 596
    //   916: getstatic 599	android/os/Build:BOARD	Ljava/lang/String;
    //   919: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   922: pop
    //   923: aload 4
    //   925: ldc_w 601
    //   928: getstatic 604	android/os/Build:BRAND	Ljava/lang/String;
    //   931: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   934: pop
    //   935: aload 4
    //   937: ldc_w 606
    //   940: getstatic 609	android/os/Build:TIME	J
    //   943: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   946: pop
    //   947: aload 4
    //   949: ldc_w 611
    //   952: getstatic 614	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   955: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   958: pop
    //   959: aload 4
    //   961: ldc_w 616
    //   964: getstatic 619	android/os/Build:ID	Ljava/lang/String;
    //   967: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   970: pop
    //   971: aload 4
    //   973: ldc_w 621
    //   976: getstatic 624	android/os/Build:DEVICE	Ljava/lang/String;
    //   979: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   982: pop
    //   983: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   986: invokestatic 627	com/umeng/analytics/pro/bv:x	(Landroid/content/Context;)Ljava/lang/String;
    //   989: astore_1
    //   990: aload_1
    //   991: invokestatic 184	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   994: ifne +13 -> 1007
    //   997: aload 4
    //   999: ldc_w 629
    //   1002: aload_1
    //   1003: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1006: pop
    //   1007: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1010: invokestatic 632	com/umeng/analytics/pro/bv:y	(Landroid/content/Context;)Ljava/lang/String;
    //   1013: astore_1
    //   1014: aload_1
    //   1015: invokestatic 184	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1018: ifne +13 -> 1031
    //   1021: aload 4
    //   1023: ldc_w 634
    //   1026: aload_1
    //   1027: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1030: pop
    //   1031: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1034: invokestatic 636	com/umeng/analytics/pro/bv:j	(Landroid/content/Context;)[Ljava/lang/String;
    //   1037: astore_1
    //   1038: ldc_w 638
    //   1041: aload_1
    //   1042: iconst_0
    //   1043: aaload
    //   1044: invokevirtual 264	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1047: ifeq +529 -> 1576
    //   1050: aload 4
    //   1052: ldc_w 640
    //   1055: ldc_w 642
    //   1058: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1061: pop
    //   1062: ldc -9
    //   1064: aload_1
    //   1065: iconst_1
    //   1066: aaload
    //   1067: invokevirtual 264	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1070: ifne +15 -> 1085
    //   1073: aload 4
    //   1075: ldc_w 644
    //   1078: aload_1
    //   1079: iconst_1
    //   1080: aaload
    //   1081: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1084: pop
    //   1085: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1088: invokestatic 646	com/umeng/analytics/pro/bv:e	(Landroid/content/Context;)Ljava/lang/String;
    //   1091: astore_1
    //   1092: aload_1
    //   1093: invokestatic 184	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1096: ifne +522 -> 1618
    //   1099: aload 4
    //   1101: ldc_w 648
    //   1104: aload_1
    //   1105: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1108: pop
    //   1109: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1112: invokestatic 650	com/umeng/analytics/pro/bv:o	(Landroid/content/Context;)[Ljava/lang/String;
    //   1115: astore_1
    //   1116: aload 4
    //   1118: ldc_w 652
    //   1121: aload_1
    //   1122: iconst_0
    //   1123: aaload
    //   1124: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1127: pop
    //   1128: aload 4
    //   1130: ldc_w 654
    //   1133: aload_1
    //   1134: iconst_1
    //   1135: aaload
    //   1136: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1139: pop
    //   1140: aload 4
    //   1142: ldc_w 656
    //   1145: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1148: invokestatic 658	com/umeng/analytics/pro/bv:m	(Landroid/content/Context;)I
    //   1151: invokevirtual 475	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1154: pop
    //   1155: aload 4
    //   1157: ldc_w 660
    //   1160: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1163: invokestatic 662	com/umeng/analytics/pro/bv:h	(Landroid/content/Context;)Ljava/lang/String;
    //   1166: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1169: pop
    //   1170: aload 4
    //   1172: ldc_w 664
    //   1175: aload_3
    //   1176: ldc_w 666
    //   1179: iconst_0
    //   1180: invokeinterface 144 3 0
    //   1185: invokevirtual 475	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1188: pop
    //   1189: aload 4
    //   1191: ldc_w 668
    //   1194: aload_3
    //   1195: ldc_w 668
    //   1198: iconst_0
    //   1199: invokeinterface 144 3 0
    //   1204: invokevirtual 475	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1207: pop
    //   1208: aload 4
    //   1210: ldc_w 670
    //   1213: aload_3
    //   1214: ldc_w 672
    //   1217: iconst_0
    //   1218: invokeinterface 144 3 0
    //   1223: invokevirtual 475	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1226: pop
    //   1227: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1230: invokestatic 108	com/umeng/analytics/pro/af:a	(Landroid/content/Context;)Lcom/umeng/analytics/pro/af;
    //   1233: invokevirtual 675	com/umeng/analytics/pro/af:a	()Lcom/umeng/analytics/pro/bn;
    //   1236: astore_1
    //   1237: goto +453 -> 1690
    //   1240: aload 4
    //   1242: ldc_w 677
    //   1245: new 317	com/umeng/analytics/pro/cp
    //   1248: dup
    //   1249: invokespecial 318	com/umeng/analytics/pro/cp:<init>	()V
    //   1252: aload_1
    //   1253: invokevirtual 321	com/umeng/analytics/pro/cp:a	(Lcom/umeng/analytics/pro/cg;)[B
    //   1256: iconst_0
    //   1257: invokestatic 327	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   1260: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1263: pop
    //   1264: aload_2
    //   1265: ldc_w 329
    //   1268: aload 4
    //   1270: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1273: pop
    //   1274: aload 5
    //   1276: ldc_w 535
    //   1279: aload 4
    //   1281: ldc_w 535
    //   1284: invokevirtual 679	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1287: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1290: ldc_w 585
    //   1293: aload 4
    //   1295: ldc_w 585
    //   1298: invokevirtual 679	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1301: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1304: ldc_w 589
    //   1307: aload 4
    //   1309: ldc_w 589
    //   1312: invokevirtual 679	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1315: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1318: ldc_w 681
    //   1321: aload 4
    //   1323: ldc_w 517
    //   1326: invokevirtual 679	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1329: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1332: ldc_w 483
    //   1335: aload 4
    //   1337: ldc_w 483
    //   1340: invokevirtual 679	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1343: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1346: ldc_w 485
    //   1349: aload 4
    //   1351: ldc_w 485
    //   1354: invokevirtual 679	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1357: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1360: pop
    //   1361: aload_0
    //   1362: aload 4
    //   1364: invokevirtual 684	com/umeng/analytics/pro/aq:a	(Lorg/json/JSONObject;)Z
    //   1367: ifne +5 -> 1372
    //   1370: aconst_null
    //   1371: astore_2
    //   1372: getstatic 686	com/umeng/analytics/pro/by:a	Z
    //   1375: ifeq +19 -> 1394
    //   1378: aload 5
    //   1380: invokevirtual 391	org/json/JSONObject:length	()I
    //   1383: ifle +11 -> 1394
    //   1386: aload 5
    //   1388: invokestatic 339	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1391: invokestatic 688	com/umeng/analytics/pro/by:b	(Ljava/lang/String;)V
    //   1394: aload_3
    //   1395: ifnull +245 -> 1640
    //   1398: aload_3
    //   1399: invokeinterface 692 1 0
    //   1404: astore_1
    //   1405: aload_1
    //   1406: ldc_w 513
    //   1409: invokeinterface 696 2 0
    //   1414: pop
    //   1415: aload_1
    //   1416: ldc_w 519
    //   1419: invokeinterface 696 2 0
    //   1424: pop
    //   1425: aload_1
    //   1426: ldc_w 698
    //   1429: invokeinterface 696 2 0
    //   1434: pop
    //   1435: aload_1
    //   1436: ldc_w 700
    //   1439: invokeinterface 696 2 0
    //   1444: pop
    //   1445: aload_1
    //   1446: invokeinterface 273 1 0
    //   1451: pop
    //   1452: aload_2
    //   1453: areturn
    //   1454: astore_1
    //   1455: aload_2
    //   1456: areturn
    //   1457: astore_3
    //   1458: new 190	org/json/JSONObject
    //   1461: dup
    //   1462: invokespecial 399	org/json/JSONObject:<init>	()V
    //   1465: astore 4
    //   1467: goto -1410 -> 57
    //   1470: aload_2
    //   1471: ldc_w 423
    //   1474: invokevirtual 703	org/json/JSONObject:remove	(Ljava/lang/String;)Ljava/lang/Object;
    //   1477: pop
    //   1478: goto -973 -> 505
    //   1481: astore_1
    //   1482: goto -977 -> 505
    //   1485: aload_1
    //   1486: astore_3
    //   1487: aload 4
    //   1489: ldc_w 515
    //   1492: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1495: invokestatic 258	com/umeng/analytics/pro/bv:b	(Landroid/content/Context;)Ljava/lang/String;
    //   1498: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1501: pop
    //   1502: aload_1
    //   1503: astore_3
    //   1504: aload 4
    //   1506: ldc_w 517
    //   1509: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1512: invokestatic 705	com/umeng/analytics/pro/bv:a	(Landroid/content/Context;)Ljava/lang/String;
    //   1515: invokestatic 710	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1518: invokevirtual 475	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1521: pop
    //   1522: aload_1
    //   1523: astore_3
    //   1524: goto -831 -> 693
    //   1527: astore_1
    //   1528: aload 4
    //   1530: ldc_w 515
    //   1533: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1536: invokestatic 258	com/umeng/analytics/pro/bv:b	(Landroid/content/Context;)Ljava/lang/String;
    //   1539: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1542: pop
    //   1543: aload 4
    //   1545: ldc_w 517
    //   1548: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1551: invokestatic 705	com/umeng/analytics/pro/bv:a	(Landroid/content/Context;)Ljava/lang/String;
    //   1554: invokestatic 710	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1557: invokevirtual 475	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1560: pop
    //   1561: goto -868 -> 693
    //   1564: astore_1
    //   1565: getstatic 94	com/umeng/analytics/pro/aq:j	Landroid/content/Context;
    //   1568: invokestatic 103	com/umeng/analytics/pro/cc:a	(Landroid/content/Context;)Lcom/umeng/analytics/pro/cc;
    //   1571: invokevirtual 364	com/umeng/analytics/pro/cc:g	()V
    //   1574: aconst_null
    //   1575: areturn
    //   1576: ldc_w 712
    //   1579: aload_1
    //   1580: iconst_0
    //   1581: aaload
    //   1582: invokevirtual 264	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1585: ifeq +18 -> 1603
    //   1588: aload 4
    //   1590: ldc_w 640
    //   1593: ldc_w 712
    //   1596: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1599: pop
    //   1600: goto -538 -> 1062
    //   1603: aload 4
    //   1605: ldc_w 640
    //   1608: ldc_w 714
    //   1611: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1614: pop
    //   1615: goto -553 -> 1062
    //   1618: aload 4
    //   1620: ldc_w 648
    //   1623: ldc -9
    //   1625: invokevirtual 194	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1628: pop
    //   1629: goto -520 -> 1109
    //   1632: astore_1
    //   1633: goto -369 -> 1264
    //   1636: astore_1
    //   1637: goto -410 -> 1227
    //   1640: aload_2
    //   1641: areturn
    //   1642: aload_3
    //   1643: astore_2
    //   1644: aload_3
    //   1645: ifnonnull -1597 -> 48
    //   1648: goto -1608 -> 40
    //   1651: aload_3
    //   1652: ifnull -1539 -> 113
    //   1655: goto -1574 -> 81
    //   1658: aload 7
    //   1660: ifnull -1442 -> 218
    //   1663: goto -1464 -> 199
    //   1666: aload 7
    //   1668: ifnull -1417 -> 251
    //   1671: goto -1439 -> 232
    //   1674: aload_1
    //   1675: astore_3
    //   1676: aload_1
    //   1677: ifnull -984 -> 693
    //   1680: goto -1048 -> 632
    //   1683: aload_1
    //   1684: ifnull -815 -> 869
    //   1687: goto -855 -> 832
    //   1690: aload_1
    //   1691: ifnull -427 -> 1264
    //   1694: goto -454 -> 1240
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1697	0	this	aq
    //   0	1697	1	paramVarArgs	int[]
    //   47	1597	2	localObject1	Object
    //   36	1363	3	localObject2	Object
    //   1457	1	3	localThrowable	Throwable
    //   1486	190	3	arrayOfInt	int[]
    //   55	1564	4	localJSONObject1	JSONObject
    //   69	1318	5	localJSONObject2	JSONObject
    //   92	573	6	localObject3	Object
    //   194	1473	7	localJSONObject3	JSONObject
    // Exception table:
    //   from	to	target	type
    //   1398	1452	1454	java/lang/Throwable
    //   48	57	1457	java/lang/Throwable
    //   1470	1478	1481	java/lang/Throwable
    //   622	629	1527	java/lang/Throwable
    //   634	647	1527	java/lang/Throwable
    //   649	657	1527	java/lang/Throwable
    //   659	670	1527	java/lang/Throwable
    //   672	691	1527	java/lang/Throwable
    //   1487	1502	1527	java/lang/Throwable
    //   1504	1522	1527	java/lang/Throwable
    //   0	18	1564	java/lang/Throwable
    //   20	37	1564	java/lang/Throwable
    //   40	48	1564	java/lang/Throwable
    //   57	78	1564	java/lang/Throwable
    //   81	113	1564	java/lang/Throwable
    //   113	176	1564	java/lang/Throwable
    //   176	196	1564	java/lang/Throwable
    //   199	218	1564	java/lang/Throwable
    //   218	229	1564	java/lang/Throwable
    //   232	251	1564	java/lang/Throwable
    //   251	281	1564	java/lang/Throwable
    //   281	289	1564	java/lang/Throwable
    //   294	379	1564	java/lang/Throwable
    //   379	397	1564	java/lang/Throwable
    //   397	409	1564	java/lang/Throwable
    //   413	487	1564	java/lang/Throwable
    //   487	505	1564	java/lang/Throwable
    //   505	571	1564	java/lang/Throwable
    //   571	616	1564	java/lang/Throwable
    //   693	729	1564	java/lang/Throwable
    //   729	829	1564	java/lang/Throwable
    //   832	869	1564	java/lang/Throwable
    //   869	1007	1564	java/lang/Throwable
    //   1007	1031	1564	java/lang/Throwable
    //   1031	1062	1564	java/lang/Throwable
    //   1062	1085	1564	java/lang/Throwable
    //   1085	1109	1564	java/lang/Throwable
    //   1109	1170	1564	java/lang/Throwable
    //   1170	1227	1564	java/lang/Throwable
    //   1227	1237	1564	java/lang/Throwable
    //   1240	1264	1564	java/lang/Throwable
    //   1264	1361	1564	java/lang/Throwable
    //   1361	1370	1564	java/lang/Throwable
    //   1372	1394	1564	java/lang/Throwable
    //   1458	1467	1564	java/lang/Throwable
    //   1528	1561	1564	java/lang/Throwable
    //   1576	1600	1564	java/lang/Throwable
    //   1603	1615	1564	java/lang/Throwable
    //   1618	1629	1564	java/lang/Throwable
    //   1227	1237	1632	java/lang/Exception
    //   1240	1264	1632	java/lang/Exception
    //   1170	1227	1636	java/lang/Exception
  }
  
  public void a()
  {
    if (bv.l(j))
    {
      d();
      return;
    }
    by.b("network is unavailable");
  }
  
  public void a(Context paramContext)
  {
    try
    {
      if (j == null) {
        j = paramContext;
      }
      if (this.l.length() > 0)
      {
        w.a(j).a(this.l);
        this.l = new JSONArray();
      }
      ba.a(j).edit().putLong("thtstart", this.p).putInt("gkvc", this.n).putInt("ekvc", this.o).commit();
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public void a(af.a parama)
  {
    this.e.a(parama);
    this.d.a(parama);
    this.f.a(parama);
    this.g.a(parama);
    this.a = af.a(j).b().b(null);
  }
  
  public void a(Object paramObject)
  {
    if (this.c.f()) {
      this.i = this.c.l();
    }
    boolean bool = true;
    if ((paramObject instanceof JSONObject)) {
      bool = false;
    }
    try
    {
      b((JSONObject)paramObject);
      if (a(bool)) {
        d();
      }
      return;
    }
    catch (Throwable paramObject)
    {
      for (;;) {}
    }
  }
  
  public boolean a(JSONObject paramJSONObject)
  {
    return (!TextUtils.isEmpty("device_id")) && (!TextUtils.isEmpty("mc")) && (!TextUtils.isEmpty("resolution")) && (!TextUtils.isEmpty("appkey")) && (!TextUtils.isEmpty("channel")) && (!TextUtils.isEmpty("app_signature")) && (!TextUtils.isEmpty("package_name")) && (!TextUtils.isEmpty("app_version"));
  }
  
  public void b()
  {
    c(a(new int[0]));
  }
  
  public class a
  {
    private ca.h b;
    private int c = -1;
    private int d = -1;
    private int e = -1;
    private int f = -1;
    
    public a()
    {
      this$1 = aq.a(aq.this).a(-1, -1);
      this.c = aq.this[0];
      this.d = aq.this[1];
    }
    
    private ca.h b(int paramInt1, int paramInt2)
    {
      switch (paramInt1)
      {
      case 2: 
      case 3: 
      case 7: 
      default: 
        if ((this.b instanceof ca.d)) {
          return this.b;
        }
        break;
      case 1: 
        if ((this.b instanceof ca.d)) {
          return this.b;
        }
        return new ca.d();
      case 6: 
        if ((this.b instanceof ca.e))
        {
          ca.h localh = this.b;
          ((ca.e)localh).a(paramInt2);
          return localh;
        }
        return new ca.e(aq.c(aq.this), paramInt2);
      case 4: 
        if ((this.b instanceof ca.f)) {
          return this.b;
        }
        return new ca.f(aq.c(aq.this));
      case 0: 
        if ((this.b instanceof ca.g)) {
          return this.b;
        }
        return new ca.g();
      case 5: 
        if ((this.b instanceof ca.i)) {
          return this.b;
        }
        return new ca.i(aq.c());
      case 8: 
        if ((this.b instanceof ca.j)) {
          return this.b;
        }
        return new ca.j(aq.c(aq.this));
      }
      return new ca.d();
    }
    
    public void a(int paramInt1, int paramInt2)
    {
      this.e = paramInt1;
      this.f = paramInt2;
    }
    
    public void a(af.a parama)
    {
      parama = parama.a(-1, -1);
      this.c = parama[0];
      this.d = parama[1];
    }
    
    protected void a(boolean paramBoolean)
    {
      int k = 1;
      int i = 1;
      int j = 0;
      Object localObject;
      if (aq.b(aq.this).d()) {
        if (((this.b instanceof ca.b)) && (this.b.a()))
        {
          if (i == 0) {
            break label62;
          }
          localObject = this.b;
          label50:
          this.b = ((ca.h)localObject);
        }
      }
      for (;;)
      {
        return;
        i = 0;
        break;
        label62:
        localObject = new ca.b(aq.c(aq.this), aq.b(aq.this));
        break label50;
        if (((this.b instanceof ca.c)) && (this.b.a())) {}
        for (i = k; i == 0; i = 0)
        {
          if ((!paramBoolean) || (!aq.d(aq.this).a())) {
            break label178;
          }
          this.b = new ca.c((int)aq.d(aq.this).b());
          aq.a(aq.this, (int)aq.d(aq.this).b());
          return;
        }
      }
      label178:
      if ((by.a) && (aq.a(aq.this).b()))
      {
        this.b = new ca.a(aq.c(aq.this));
        return;
      }
      if ((aq.e(aq.this).a()) && ("RPT".equals(aq.e(aq.this).f())))
      {
        i = j;
        if (aq.e(aq.this).b() == 6)
        {
          if (!aq.a(aq.this).a()) {
            break label310;
          }
          i = aq.a(aq.this).d(90000);
        }
        for (;;)
        {
          this.b = b(aq.e(aq.this).b(), i);
          return;
          label310:
          if (this.d > 0) {
            i = this.d;
          } else {
            i = this.f;
          }
        }
      }
      j = this.e;
      i = this.f;
      if (this.c != -1)
      {
        j = this.c;
        i = this.d;
      }
      this.b = b(j, i);
    }
    
    public ca.h b(boolean paramBoolean)
    {
      a(paramBoolean);
      return this.b;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\aq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */