package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.AnalyticsConfig;
import java.io.File;
import org.json.JSONObject;

public class bc
{
  private static final int a = 1;
  private static final int b = 2;
  private static final int c = 3;
  private static Context g;
  private ad d;
  private af e;
  private final int f = 1;
  private be h;
  private aw i;
  private JSONObject j;
  private boolean k = false;
  private boolean l;
  
  public bc(Context paramContext, be parambe)
  {
    this.d = ad.a(paramContext);
    this.e = af.a(paramContext);
    g = paramContext;
    this.h = parambe;
    this.i = new aw(paramContext);
    this.i.a(this.h);
  }
  
  private int a(byte[] paramArrayOfByte)
  {
    bp localbp = new bp();
    cj localcj = new cj(new cy.a());
    try
    {
      localcj.a(localbp, paramArrayOfByte);
      if (localbp.a == 1)
      {
        this.e.b(localbp.i());
        this.e.d();
      }
      by.c("send log:" + localbp.f());
    }
    catch (Throwable paramArrayOfByte)
    {
      for (;;) {}
    }
    if (localbp.a == 1) {
      return 2;
    }
    return 3;
  }
  
  private void b()
  {
    cc.a(g).i().a(new cc.b()
    {
      public void a(File paramAnonymousFile) {}
      
      /* Error */
      public boolean b(File paramAnonymousFile)
      {
        // Byte code:
        //   0: new 26	java/io/FileInputStream
        //   3: dup
        //   4: aload_1
        //   5: invokespecial 28	java/io/FileInputStream:<init>	(Ljava/io/File;)V
        //   8: astore_3
        //   9: aload_3
        //   10: invokestatic 33	com/umeng/analytics/pro/bw:b	(Ljava/io/InputStream;)[B
        //   13: astore_1
        //   14: aload_3
        //   15: invokestatic 37	com/umeng/analytics/pro/bw:c	(Ljava/io/InputStream;)V
        //   18: aload_0
        //   19: getfield 17	com/umeng/analytics/pro/bc$1:a	Lcom/umeng/analytics/pro/bc;
        //   22: invokestatic 40	com/umeng/analytics/pro/bc:a	(Lcom/umeng/analytics/pro/bc;)Lcom/umeng/analytics/pro/aw;
        //   25: aload_1
        //   26: invokevirtual 45	com/umeng/analytics/pro/aw:a	([B)[B
        //   29: astore_1
        //   30: goto +44 -> 74
        //   33: aload_0
        //   34: getfield 17	com/umeng/analytics/pro/bc$1:a	Lcom/umeng/analytics/pro/bc;
        //   37: invokestatic 48	com/umeng/analytics/pro/bc:b	(Lcom/umeng/analytics/pro/bc;)Z
        //   40: ifeq +23 -> 63
        //   43: iconst_1
        //   44: ireturn
        //   45: aload_3
        //   46: invokestatic 37	com/umeng/analytics/pro/bw:c	(Ljava/io/InputStream;)V
        //   49: aload_1
        //   50: athrow
        //   51: aload_0
        //   52: getfield 17	com/umeng/analytics/pro/bc$1:a	Lcom/umeng/analytics/pro/bc;
        //   55: aload_1
        //   56: invokestatic 51	com/umeng/analytics/pro/bc:a	(Lcom/umeng/analytics/pro/bc;[B)I
        //   59: istore_2
        //   60: goto -27 -> 33
        //   63: iload_2
        //   64: iconst_1
        //   65: if_icmpne +18 -> 83
        //   68: iconst_0
        //   69: ireturn
        //   70: astore_1
        //   71: goto -26 -> 45
        //   74: aload_1
        //   75: ifnonnull -24 -> 51
        //   78: iconst_1
        //   79: istore_2
        //   80: goto -47 -> 33
        //   83: iconst_1
        //   84: ireturn
        //   85: astore_1
        //   86: aconst_null
        //   87: astore_3
        //   88: goto -43 -> 45
        //   91: astore_1
        //   92: iconst_0
        //   93: ireturn
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	94	0	this	1
        //   0	94	1	paramAnonymousFile	File
        //   59	21	2	i	int
        //   8	80	3	localFileInputStream	java.io.FileInputStream
        // Exception table:
        //   from	to	target	type
        //   9	14	70	finally
        //   0	9	85	finally
        //   14	30	91	java/lang/Exception
        //   33	43	91	java/lang/Exception
        //   45	51	91	java/lang/Exception
        //   51	60	91	java/lang/Exception
      }
      
      public void c(File paramAnonymousFile)
      {
        bc.c(bc.this).k();
      }
    });
  }
  
  private void c()
  {
    label209:
    label214:
    do
    {
      Object localObject2;
      int m;
      try
      {
        this.d.a();
        try
        {
          Object localObject1 = this.d.b();
          localObject1 = Base64.encodeToString(new cp().a((cg)localObject1), 0);
          if (!TextUtils.isEmpty((CharSequence)localObject1))
          {
            localObject2 = this.j.getJSONObject("header");
            ((JSONObject)localObject2).put("id_tracking", localObject1);
            this.j.put("header", localObject2);
          }
          localObject1 = String.valueOf(this.j).getBytes();
          break label209;
          if (!bt.a(g, (byte[])localObject1))
          {
            if (!this.k) {}
            for (localObject1 = aa.a(g, AnalyticsConfig.getAppkey(g), (byte[])localObject1);; localObject1 = aa.b(g, AnalyticsConfig.getAppkey(g), (byte[])localObject1))
            {
              localObject1 = ((aa)localObject1).c();
              cc.a(g).g();
              localObject2 = this.i.a((byte[])localObject1);
              break label214;
              if (this.l) {
                break;
              }
              cc.a(g).a((byte[])localObject1);
              return;
            }
            m = a((byte[])localObject2);
            break;
            this.d.d();
            this.h.k();
            return;
            this.h.k();
            return;
          }
        }
        catch (Exception localException)
        {
          do
          {
            for (;;) {}
          } while (localException != null);
        }
        return;
      }
      catch (Throwable localThrowable) {}
    } while (localObject2 != null);
    m = 1;
    switch (m)
    {
    }
    return;
  }
  
  /* Error */
  public void a()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 165	com/umeng/analytics/pro/bc:j	Lorg/json/JSONObject;
    //   4: ifnull +139 -> 143
    //   7: aload_0
    //   8: invokespecial 224	com/umeng/analytics/pro/bc:c	()V
    //   11: getstatic 54	com/umeng/analytics/pro/bc:g	Landroid/content/Context;
    //   14: invokestatic 229	com/umeng/analytics/pro/bv:k	(Landroid/content/Context;)Z
    //   17: ifeq +125 -> 142
    //   20: getstatic 54	com/umeng/analytics/pro/bc:g	Landroid/content/Context;
    //   23: invokestatic 234	com/umeng/analytics/pro/ba:a	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   26: astore 6
    //   28: goto +208 -> 236
    //   31: aload 6
    //   33: ldc -20
    //   35: ldc -18
    //   37: invokeinterface 244 3 0
    //   42: astore 7
    //   44: invokestatic 250	java/lang/System:currentTimeMillis	()J
    //   47: invokestatic 255	com/umeng/analytics/pro/q:b	(J)J
    //   50: lstore_2
    //   51: aload 7
    //   53: invokestatic 163	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   56: ifeq +86 -> 142
    //   59: aload 6
    //   61: ldc_w 257
    //   64: ldc2_w 258
    //   67: invokeinterface 263 4 0
    //   72: lstore 4
    //   74: aload 6
    //   76: ldc_w 265
    //   79: iconst_0
    //   80: invokeinterface 269 3 0
    //   85: istore_1
    //   86: goto +158 -> 244
    //   89: aload 6
    //   91: invokeinterface 273 1 0
    //   96: ldc_w 265
    //   99: iload_1
    //   100: iconst_1
    //   101: iadd
    //   102: invokeinterface 279 3 0
    //   107: invokeinterface 283 1 0
    //   112: pop
    //   113: aload_0
    //   114: getfield 63	com/umeng/analytics/pro/bc:i	Lcom/umeng/analytics/pro/aw;
    //   117: invokevirtual 284	com/umeng/analytics/pro/aw:a	()V
    //   120: aload 6
    //   122: invokeinterface 273 1 0
    //   127: ldc_w 257
    //   130: lload_2
    //   131: invokeinterface 288 4 0
    //   136: invokeinterface 283 1 0
    //   141: pop
    //   142: return
    //   143: aload_0
    //   144: invokespecial 290	com/umeng/analytics/pro/bc:b	()V
    //   147: goto -136 -> 11
    //   150: astore 6
    //   152: goto -141 -> 11
    //   155: lload_2
    //   156: lload 4
    //   158: lcmp
    //   159: ifeq +100 -> 259
    //   162: aload 6
    //   164: invokeinterface 273 1 0
    //   169: ldc_w 265
    //   172: iconst_1
    //   173: invokeinterface 279 3 0
    //   178: invokeinterface 283 1 0
    //   183: pop
    //   184: aload_0
    //   185: getfield 63	com/umeng/analytics/pro/bc:i	Lcom/umeng/analytics/pro/aw;
    //   188: invokevirtual 284	com/umeng/analytics/pro/aw:a	()V
    //   191: goto -71 -> 120
    //   194: aload 6
    //   196: invokeinterface 273 1 0
    //   201: ldc_w 265
    //   204: iload_1
    //   205: iconst_1
    //   206: iadd
    //   207: invokeinterface 279 3 0
    //   212: invokeinterface 283 1 0
    //   217: pop
    //   218: aload_0
    //   219: getfield 63	com/umeng/analytics/pro/bc:i	Lcom/umeng/analytics/pro/aw;
    //   222: invokevirtual 284	com/umeng/analytics/pro/aw:a	()V
    //   225: goto -105 -> 120
    //   228: aload_0
    //   229: getfield 63	com/umeng/analytics/pro/bc:i	Lcom/umeng/analytics/pro/aw;
    //   232: invokevirtual 284	com/umeng/analytics/pro/aw:a	()V
    //   235: return
    //   236: aload 6
    //   238: ifnull -10 -> 228
    //   241: goto -210 -> 31
    //   244: lload 4
    //   246: ldc2_w 258
    //   249: lcmp
    //   250: ifne -95 -> 155
    //   253: goto -164 -> 89
    //   256: astore 6
    //   258: return
    //   259: iload_1
    //   260: iconst_2
    //   261: if_icmpge -141 -> 120
    //   264: goto -70 -> 194
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	267	0	this	bc
    //   85	177	1	m	int
    //   50	106	2	l1	long
    //   72	173	4	l2	long
    //   26	95	6	localSharedPreferences	android.content.SharedPreferences
    //   150	87	6	localThrowable1	Throwable
    //   256	1	6	localThrowable2	Throwable
    //   42	10	7	str	String
    // Exception table:
    //   from	to	target	type
    //   0	11	150	java/lang/Throwable
    //   143	147	150	java/lang/Throwable
    //   11	28	256	java/lang/Throwable
    //   31	86	256	java/lang/Throwable
    //   89	120	256	java/lang/Throwable
    //   120	142	256	java/lang/Throwable
    //   162	191	256	java/lang/Throwable
    //   194	225	256	java/lang/Throwable
    //   228	235	256	java/lang/Throwable
  }
  
  public void a(az paramaz)
  {
    this.e.a(paramaz);
  }
  
  public void a(JSONObject paramJSONObject)
  {
    this.j = paramJSONObject;
  }
  
  public void a(boolean paramBoolean)
  {
    this.k = paramBoolean;
  }
  
  public void b(boolean paramBoolean)
  {
    this.l = paramBoolean;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\bc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */