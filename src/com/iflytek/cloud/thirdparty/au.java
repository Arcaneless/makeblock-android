package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.Version;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class au
  extends at
{
  public static final int b;
  public static final int c;
  public static final int d;
  public static final int e;
  public static final int f;
  private static boolean g;
  private static String h;
  private static final byte[] i;
  private static final String j;
  private static Context k;
  private static boolean l;
  private static int m;
  private static final int n;
  private static final int o;
  private static final int p;
  private static final int q;
  private static final int r;
  private static final int s;
  private static boolean v;
  private String[] t = new String[s];
  private Object u = this;
  private at.a w = null;
  private boolean x = false;
  private long y = System.currentTimeMillis();
  
  static
  {
    boolean bool = true;
    g = true;
    h = "300008448508";
    i = new byte[] { 31 };
    j = new String(i);
    k = null;
    l = false;
    m = 0;
    int i1 = m;
    m = i1 + 1;
    b = i1;
    i1 = m;
    m = i1 + 1;
    n = i1;
    i1 = m;
    m = i1 + 1;
    o = i1;
    i1 = m;
    m = i1 + 1;
    c = i1;
    i1 = m;
    m = i1 + 1;
    p = i1;
    i1 = m;
    m = i1 + 1;
    q = i1;
    i1 = m;
    m = i1 + 1;
    r = i1;
    i1 = m;
    m = i1 + 1;
    d = i1;
    i1 = m;
    m = i1 + 1;
    e = i1;
    i1 = m;
    m = i1 + 1;
    f = i1;
    s = m;
    if (!MSC.isIflyVersion()) {}
    for (;;)
    {
      v = bool;
      return;
      bool = false;
    }
  }
  
  private au(at.a parama)
  {
    this.w = parama;
    h();
  }
  
  private static String a(String paramString)
  {
    try
    {
      paramString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.CHINA).format(Long.valueOf(paramString));
      return paramString;
    }
    catch (Throwable paramString)
    {
      h("DC exception:" + paramString.getLocalizedMessage());
      paramString.printStackTrace();
    }
    return null;
  }
  
  private static void a(Context paramContext)
  {
    synchronized (a)
    {
      k = paramContext;
      return;
    }
  }
  
  /* Error */
  private static void a(boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 165	com/iflytek/cloud/thirdparty/au:a	Ljava/lang/Object;
    //   6: astore_1
    //   7: aload_1
    //   8: monitorenter
    //   9: iload_0
    //   10: putstatic 41	com/iflytek/cloud/thirdparty/au:g	Z
    //   13: aload_1
    //   14: monitorexit
    //   15: ldc 2
    //   17: monitorexit
    //   18: return
    //   19: astore_2
    //   20: aload_1
    //   21: monitorexit
    //   22: aload_2
    //   23: athrow
    //   24: astore_1
    //   25: ldc 2
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	30	0	paramBoolean	boolean
    //   24	5	1	localObject2	Object
    //   19	4	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   9	15	19	finally
    //   20	22	19	finally
    //   3	9	24	finally
    //   22	24	24	finally
  }
  
  private static final boolean a(int paramInt)
  {
    return (o == paramInt) || (p == paramInt) || (q == paramInt) || (r == paramInt);
  }
  
  public static au b(at.a parama)
  {
    try
    {
      g("DC create enter.");
      parama = new au(parama);
      g("DC create leave.");
      return parama;
    }
    finally
    {
      parama = finally;
      throw parama;
    }
  }
  
  /* Error */
  public static boolean b(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: ldc -77
    //   5: invokestatic 172	com/iflytek/cloud/thirdparty/au:g	(Ljava/lang/String;)V
    //   8: getstatic 165	com/iflytek/cloud/thirdparty/au:a	Ljava/lang/Object;
    //   11: astore_3
    //   12: aload_3
    //   13: monitorenter
    //   14: iload_2
    //   15: invokestatic 181	com/iflytek/cloud/thirdparty/au:a	(Z)V
    //   18: aload_0
    //   19: invokestatic 183	com/iflytek/cloud/thirdparty/au:a	(Landroid/content/Context;)V
    //   22: aload_1
    //   23: invokestatic 185	com/iflytek/cloud/thirdparty/au:c	(Ljava/lang/String;)V
    //   26: invokestatic 187	com/iflytek/cloud/thirdparty/au:g	()V
    //   29: aload_3
    //   30: monitorexit
    //   31: ldc -67
    //   33: invokestatic 172	com/iflytek/cloud/thirdparty/au:g	(Ljava/lang/String;)V
    //   36: ldc 2
    //   38: monitorexit
    //   39: iconst_0
    //   40: ireturn
    //   41: astore_0
    //   42: new 142	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   49: ldc -111
    //   51: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: aload_0
    //   55: invokevirtual 153	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   58: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   64: invokestatic 159	com/iflytek/cloud/thirdparty/au:h	(Ljava/lang/String;)V
    //   67: aload_0
    //   68: invokevirtual 162	java/lang/Throwable:printStackTrace	()V
    //   71: goto -42 -> 29
    //   74: astore_0
    //   75: aload_3
    //   76: monitorexit
    //   77: aload_0
    //   78: athrow
    //   79: astore_0
    //   80: ldc 2
    //   82: monitorexit
    //   83: aload_0
    //   84: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	85	0	paramContext	Context
    //   0	85	1	paramString	String
    //   0	85	2	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   14	29	41	java/lang/Throwable
    //   14	29	74	finally
    //   29	31	74	finally
    //   42	71	74	finally
    //   75	77	74	finally
    //   3	14	79	finally
    //   31	36	79	finally
    //   77	79	79	finally
  }
  
  private static boolean b(String paramString)
  {
    return (paramString != null) && (paramString.length() >= 12);
  }
  
  /* Error */
  public static void c()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: ldc -60
    //   5: invokestatic 172	com/iflytek/cloud/thirdparty/au:g	(Ljava/lang/String;)V
    //   8: getstatic 165	com/iflytek/cloud/thirdparty/au:a	Ljava/lang/Object;
    //   11: astore_0
    //   12: aload_0
    //   13: monitorenter
    //   14: aconst_null
    //   15: putstatic 57	com/iflytek/cloud/thirdparty/au:k	Landroid/content/Context;
    //   18: iconst_0
    //   19: putstatic 59	com/iflytek/cloud/thirdparty/au:l	Z
    //   22: aload_0
    //   23: monitorexit
    //   24: ldc -58
    //   26: invokestatic 172	com/iflytek/cloud/thirdparty/au:g	(Ljava/lang/String;)V
    //   29: ldc 2
    //   31: monitorexit
    //   32: return
    //   33: astore_1
    //   34: new 142	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   41: ldc -111
    //   43: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: aload_1
    //   47: invokevirtual 153	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   50: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 159	com/iflytek/cloud/thirdparty/au:h	(Ljava/lang/String;)V
    //   59: aload_1
    //   60: invokevirtual 162	java/lang/Throwable:printStackTrace	()V
    //   63: goto -41 -> 22
    //   66: astore_1
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_1
    //   70: athrow
    //   71: astore_0
    //   72: ldc 2
    //   74: monitorexit
    //   75: aload_0
    //   76: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   71	5	0	localObject2	Object
    //   33	27	1	localThrowable	Throwable
    //   66	4	1	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   14	22	33	java/lang/Throwable
    //   14	22	66	finally
    //   22	24	66	finally
    //   34	63	66	finally
    //   67	69	66	finally
    //   3	14	71	finally
    //   24	29	71	finally
    //   69	71	71	finally
  }
  
  private static void c(String paramString)
  {
    synchronized (a)
    {
      if (b(paramString)) {
        h = paramString;
      }
      return;
    }
  }
  
  private static String d(String paramString)
  {
    Object localObject2 = null;
    g("getMscParameter enter key=" + paramString);
    if (!MSC.isLoaded())
    {
      h("getMscParameter MSC is not loaded");
      paramString = (String)localObject2;
    }
    for (;;)
    {
      g("getMscParameter value=" + paramString);
      g("getMscParameter leave");
      return paramString;
      if (TextUtils.isEmpty(paramString))
      {
        h("getMscParameter key is empty");
        paramString = (String)localObject2;
      }
      else
      {
        for (;;)
        {
          try
          {
            localObject1 = SpeechUtility.getUtility();
            if (localObject1 == null) {
              break label201;
            }
            localObject1 = ((SpeechUtility)localObject1).getParameter("pte");
            if (!TextUtils.isEmpty((CharSequence)localObject1)) {
              break label198;
            }
            localObject1 = "utf-8";
            paramString = paramString.getBytes((String)localObject1);
            MSCSessionInfo localMSCSessionInfo = new MSCSessionInfo();
            int i1 = MSC.QMSPGetParam(paramString, localMSCSessionInfo);
            if (i1 == 0)
            {
              paramString = new String(localMSCSessionInfo.buffer, (String)localObject1);
            }
            else
            {
              g("getMscParameter MSC return " + i1);
              paramString = null;
            }
          }
          catch (UnsatisfiedLinkError paramString)
          {
            paramString.printStackTrace();
            paramString = (String)localObject2;
            break;
          }
          catch (Throwable paramString)
          {
            paramString.printStackTrace();
            paramString = (String)localObject2;
          }
          break;
          label198:
          continue;
          label201:
          Object localObject1 = null;
        }
      }
    }
  }
  
  public static boolean d()
  {
    for (;;)
    {
      try
      {
        g("DC getEnable enter.");
        synchronized (a)
        {
          g("DC getEnable static value=" + g);
          boolean bool2 = g;
          boolean bool1 = bool2;
          if (bool2)
          {
            ??? = d("idc");
            g("DC getEnable msc val=" + (String)???);
            if (!TextUtils.isEmpty((CharSequence)???))
            {
              if ("li".equalsIgnoreCase((String)???)) {
                v = true;
              }
            }
            else {
              bool1 = v;
            }
          }
          else
          {
            g("DC getEnable value=" + bool1);
            g("DC getEnable leave.");
            return bool1;
          }
        }
        v = false;
      }
      finally {}
    }
  }
  
  private static void e(String paramString) {}
  
  public static boolean e()
  {
    f("DC isActive enter.");
    synchronized (a)
    {
      boolean bool = l;
      g("DC isActive=" + bool);
      f("DC isActive leave.");
      return bool;
    }
  }
  
  private static void f(String paramString) {}
  
  private static void g()
  {
    f("DC inner init enter.");
    synchronized (a)
    {
      try
      {
        if (d()) {
          if (n() != null)
          {
            g("DC calling MA.init");
            b.d(n(), m(), "MobileMarket");
            g("DC MA.init end");
            l = true;
          }
        }
        for (;;)
        {
          f("DC inner init leave.");
          return;
          g("DC init is not enable.");
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          h("DC exception:" + localThrowable.getLocalizedMessage());
          localThrowable.printStackTrace();
        }
      }
    }
  }
  
  private static void g(String paramString)
  {
    ad.a(paramString);
  }
  
  /* Error */
  private void h()
  {
    // Byte code:
    //   0: ldc_w 317
    //   3: invokestatic 172	com/iflytek/cloud/thirdparty/au:g	(Ljava/lang/String;)V
    //   6: aload_0
    //   7: getfield 99	com/iflytek/cloud/thirdparty/au:u	Ljava/lang/Object;
    //   10: astore_2
    //   11: aload_2
    //   12: monitorenter
    //   13: iconst_0
    //   14: istore_1
    //   15: iload_1
    //   16: aload_0
    //   17: getfield 97	com/iflytek/cloud/thirdparty/au:t	[Ljava/lang/String;
    //   20: arraylength
    //   21: if_icmpge +49 -> 70
    //   24: aload_0
    //   25: getfield 97	com/iflytek/cloud/thirdparty/au:t	[Ljava/lang/String;
    //   28: iload_1
    //   29: ldc_w 319
    //   32: aastore
    //   33: iload_1
    //   34: iconst_1
    //   35: iadd
    //   36: istore_1
    //   37: goto -22 -> 15
    //   40: astore_3
    //   41: new 142	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   48: ldc -111
    //   50: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_3
    //   54: invokevirtual 153	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   57: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokestatic 159	com/iflytek/cloud/thirdparty/au:h	(Ljava/lang/String;)V
    //   66: aload_3
    //   67: invokevirtual 162	java/lang/Throwable:printStackTrace	()V
    //   70: aload_2
    //   71: monitorexit
    //   72: ldc_w 321
    //   75: invokestatic 172	com/iflytek/cloud/thirdparty/au:g	(Ljava/lang/String;)V
    //   78: return
    //   79: astore_3
    //   80: aload_2
    //   81: monitorexit
    //   82: aload_3
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	au
    //   14	23	1	i1	int
    //   10	71	2	localObject1	Object
    //   40	27	3	localThrowable	Throwable
    //   79	4	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   15	33	40	java/lang/Throwable
    //   15	33	79	finally
    //   41	70	79	finally
    //   70	72	79	finally
    //   80	82	79	finally
  }
  
  private static void h(String paramString)
  {
    ad.b(paramString);
  }
  
  /* Error */
  private void i()
  {
    // Byte code:
    //   0: ldc_w 325
    //   3: invokestatic 172	com/iflytek/cloud/thirdparty/au:g	(Ljava/lang/String;)V
    //   6: aload_0
    //   7: getfield 99	com/iflytek/cloud/thirdparty/au:u	Ljava/lang/Object;
    //   10: astore_2
    //   11: aload_2
    //   12: monitorenter
    //   13: iconst_0
    //   14: istore_1
    //   15: iload_1
    //   16: aload_0
    //   17: getfield 97	com/iflytek/cloud/thirdparty/au:t	[Ljava/lang/String;
    //   20: arraylength
    //   21: if_icmpge +56 -> 77
    //   24: iload_1
    //   25: invokestatic 327	com/iflytek/cloud/thirdparty/au:a	(I)Z
    //   28: ifne +12 -> 40
    //   31: aload_0
    //   32: getfield 97	com/iflytek/cloud/thirdparty/au:t	[Ljava/lang/String;
    //   35: iload_1
    //   36: ldc_w 319
    //   39: aastore
    //   40: iload_1
    //   41: iconst_1
    //   42: iadd
    //   43: istore_1
    //   44: goto -29 -> 15
    //   47: astore_3
    //   48: new 142	java/lang/StringBuilder
    //   51: dup
    //   52: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   55: ldc -111
    //   57: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: aload_3
    //   61: invokevirtual 153	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   64: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokestatic 159	com/iflytek/cloud/thirdparty/au:h	(Ljava/lang/String;)V
    //   73: aload_3
    //   74: invokevirtual 162	java/lang/Throwable:printStackTrace	()V
    //   77: aload_2
    //   78: monitorexit
    //   79: ldc_w 329
    //   82: invokestatic 172	com/iflytek/cloud/thirdparty/au:g	(Ljava/lang/String;)V
    //   85: return
    //   86: astore_3
    //   87: aload_2
    //   88: monitorexit
    //   89: aload_3
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	au
    //   14	30	1	i1	int
    //   10	78	2	localObject1	Object
    //   47	27	3	localThrowable	Throwable
    //   86	4	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   15	40	47	java/lang/Throwable
    //   15	40	86	finally
    //   48	77	86	finally
    //   77	79	86	finally
    //   87	89	86	finally
  }
  
  private void j()
  {
    g("DC initStaticValues enter.");
    synchronized (this.u)
    {
      try
      {
        if (TextUtils.isEmpty(this.t[o]))
        {
          this.t[o] = SpeechUtility.getUtility().getParameter("appid");
          f("DC info APPID:" + this.t[o]);
        }
        if (TextUtils.isEmpty(this.t[p])) {
          this.t[p] = Version.getVersion();
        }
        if (TextUtils.isEmpty(this.t[q])) {
          this.t[q] = M.a(k).e("os.imsi");
        }
        if (TextUtils.isEmpty(this.t[r])) {
          this.t[r] = M.a(k).e("os.imei");
        }
        if (TextUtils.isEmpty(this.t[n])) {
          this.t[n] = l();
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          h("DC exception:" + localThrowable.getLocalizedMessage());
          localThrowable.printStackTrace();
        }
      }
      g("DC initStaticValues leave.");
      return;
    }
  }
  
  private final void k()
  {
    f("DC check enter.");
    int i1 = 0;
    try
    {
      while (i1 < this.t.length)
      {
        if (this.t[i1] == null) {
          this.t[i1] = "";
        }
        i1 += 1;
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      h("DC exception:" + localThrowable.getLocalizedMessage());
      localThrowable.printStackTrace();
      f("DC check leave.");
    }
  }
  
  private static String l()
  {
    for (;;)
    {
      Object localObject5;
      int i1;
      try
      {
        g("DC getUid enter.");
        localObject5 = null;
        try
        {
          Object localObject6 = SpeechUtility.getUtility().getParameter("pte");
          localObject1 = localObject6;
          if (TextUtils.isEmpty((CharSequence)localObject6)) {
            localObject1 = "utf-8";
          }
          localObject6 = new MSCSessionInfo();
          i1 = MSC.QMSPGetParam("loginid".getBytes((String)localObject1), (MSCSessionInfo)localObject6);
          if (i1 != 0) {
            break label201;
          }
          localObject1 = new String(((MSCSessionInfo)localObject6).buffer).trim();
          f("DC getUid loginid=" + (String)localObject1);
          i1 = ((String)localObject1).indexOf("@");
          if ((i1 < 0) || (i1 >= ((String)localObject1).length())) {
            continue;
          }
          localObject1 = ((String)localObject1).substring(0, i1);
        }
        catch (Throwable localThrowable)
        {
          Object localObject1;
          h("DC exception:" + localThrowable.getLocalizedMessage());
          localThrowable.printStackTrace();
          Object localObject2 = localObject5;
          continue;
        }
        g("DC getUid leave.");
        return (String)localObject1;
      }
      finally {}
      h("DC getUid error, loginid has no tag of @:" + (String)localObject1);
      localObject1 = localObject5;
      continue;
      label201:
      h("DC getUid error:" + i1);
      Object localObject4 = localObject5;
    }
  }
  
  private static String m()
  {
    synchronized (a)
    {
      String str = h;
      return str;
    }
  }
  
  private static Context n()
  {
    synchronized (a)
    {
      Context localContext = k;
      return localContext;
    }
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    a(paramInt1, String.valueOf(paramInt2));
  }
  
  public void a(int paramInt, long paramLong)
  {
    a(paramInt, String.valueOf(paramLong));
  }
  
  public void a(int paramInt, String paramString)
  {
    f("DC setData enter: key=" + paramInt + ", value=" + paramString);
    synchronized (this.u)
    {
      try
      {
        this.t[paramInt] = paramString;
        if ((d == paramInt) || (e == paramInt)) {
          this.t[paramInt] = a(paramString);
        }
        if ((d == paramInt) || (e == paramInt) || (b == paramInt) || (c == paramInt)) {
          g("DC info: key=" + paramInt + ", value=" + this.t[paramInt]);
        }
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          h("DC exception:" + paramString.getLocalizedMessage());
          paramString.printStackTrace();
        }
      }
      f("DC setData leave.");
      return;
    }
  }
  
  /* Error */
  public void a(com.iflytek.cloud.SpeechError paramSpeechError)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 99	com/iflytek/cloud/thirdparty/au:u	Ljava/lang/Object;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 103	com/iflytek/cloud/thirdparty/au:x	Z
    //   11: ifeq +6 -> 17
    //   14: aload_3
    //   15: monitorexit
    //   16: return
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield 103	com/iflytek/cloud/thirdparty/au:x	Z
    //   22: aload_3
    //   23: monitorexit
    //   24: invokestatic 291	com/iflytek/cloud/thirdparty/au:d	()Z
    //   27: ifeq +155 -> 182
    //   30: aload_0
    //   31: getfield 101	com/iflytek/cloud/thirdparty/au:w	Lcom/iflytek/cloud/thirdparty/at$a;
    //   34: ifnull +148 -> 182
    //   37: aload_0
    //   38: getfield 101	com/iflytek/cloud/thirdparty/au:w	Lcom/iflytek/cloud/thirdparty/at$a;
    //   41: invokeinterface 412 1 0
    //   46: astore_3
    //   47: aload_3
    //   48: invokestatic 219	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   51: ifne +131 -> 182
    //   54: aload_0
    //   55: getstatic 69	com/iflytek/cloud/thirdparty/au:c	I
    //   58: aload_3
    //   59: invokevirtual 393	com/iflytek/cloud/thirdparty/au:a	(ILjava/lang/String;)V
    //   62: aload_0
    //   63: getstatic 77	com/iflytek/cloud/thirdparty/au:d	I
    //   66: aload_0
    //   67: getfield 111	com/iflytek/cloud/thirdparty/au:y	J
    //   70: invokevirtual 414	com/iflytek/cloud/thirdparty/au:a	(IJ)V
    //   73: ldc_w 416
    //   76: aload_0
    //   77: getfield 101	com/iflytek/cloud/thirdparty/au:w	Lcom/iflytek/cloud/thirdparty/at$a;
    //   80: invokeinterface 418 1 0
    //   85: invokevirtual 422	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   88: ifeq +95 -> 183
    //   91: ldc_w 424
    //   94: astore_3
    //   95: aload_0
    //   96: getstatic 63	com/iflytek/cloud/thirdparty/au:b	I
    //   99: new 142	java/lang/StringBuilder
    //   102: dup
    //   103: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   106: aload_3
    //   107: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: aload_0
    //   111: getfield 101	com/iflytek/cloud/thirdparty/au:w	Lcom/iflytek/cloud/thirdparty/at$a;
    //   114: invokeinterface 427 1 0
    //   119: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   125: invokevirtual 393	com/iflytek/cloud/thirdparty/au:a	(ILjava/lang/String;)V
    //   128: aload_1
    //   129: ifnonnull +45 -> 174
    //   132: iconst_0
    //   133: istore_2
    //   134: aload_0
    //   135: getstatic 81	com/iflytek/cloud/thirdparty/au:f	I
    //   138: iload_2
    //   139: invokevirtual 429	com/iflytek/cloud/thirdparty/au:a	(II)V
    //   142: aload_0
    //   143: getstatic 79	com/iflytek/cloud/thirdparty/au:e	I
    //   146: invokestatic 109	java/lang/System:currentTimeMillis	()J
    //   149: invokevirtual 414	com/iflytek/cloud/thirdparty/au:a	(IJ)V
    //   152: aload_0
    //   153: invokevirtual 431	com/iflytek/cloud/thirdparty/au:f	()Z
    //   156: pop
    //   157: return
    //   158: astore_1
    //   159: ldc -111
    //   161: invokestatic 323	com/iflytek/cloud/thirdparty/ad:b	(Ljava/lang/String;)V
    //   164: aload_1
    //   165: invokestatic 434	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/Throwable;)V
    //   168: return
    //   169: astore_1
    //   170: aload_3
    //   171: monitorexit
    //   172: aload_1
    //   173: athrow
    //   174: aload_1
    //   175: invokevirtual 439	com/iflytek/cloud/SpeechError:getErrorCode	()I
    //   178: istore_2
    //   179: goto -45 -> 134
    //   182: return
    //   183: ldc_w 441
    //   186: astore_3
    //   187: goto -92 -> 95
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	190	0	this	au
    //   0	190	1	paramSpeechError	com.iflytek.cloud.SpeechError
    //   133	46	2	i1	int
    // Exception table:
    //   from	to	target	type
    //   0	7	158	java/lang/Throwable
    //   24	91	158	java/lang/Throwable
    //   95	128	158	java/lang/Throwable
    //   134	157	158	java/lang/Throwable
    //   172	174	158	java/lang/Throwable
    //   174	179	158	java/lang/Throwable
    //   7	16	169	finally
    //   17	24	169	finally
    //   170	172	169	finally
  }
  
  public void a(String paramString, boolean paramBoolean) {}
  
  public void b()
  {
    this.y = System.currentTimeMillis();
  }
  
  public boolean f()
  {
    int i1 = 0;
    g("DC flush enter.");
    for (;;)
    {
      try
      {
        if (!e()) {
          g();
        }
        if (e())
        {
          j();
          k();
          ??? = new StringBuffer();
          if (i1 < this.t.length)
          {
            if (this.t[i1] != null) {
              ((StringBuffer)???).append(this.t[i1]);
            }
            ((StringBuffer)???).append(j);
            i1 += 1;
            continue;
          }
          str = ((StringBuffer)???).substring(0, ((StringBuffer)???).length() - 1);
          f("DC flush data=" + str);
          e(str);
        }
      }
      catch (Throwable localThrowable)
      {
        String str;
        h("DC exception:" + localThrowable.getLocalizedMessage());
        localThrowable.printStackTrace();
        continue;
      }
      synchronized (a)
      {
        if ((!TextUtils.isEmpty(str)) && (e()))
        {
          g("DC calling MA.onEvent");
          b.a(k, "iflytek", str);
          g("DC MA.onEvent end");
        }
        i();
        g("DC flush leave.");
        return true;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\au.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */