package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SynthesizerListener;

public class ap
  extends F
  implements K.a
{
  private Context f = null;
  private K g = null;
  private K h = null;
  
  public ap(Context paramContext)
  {
    super(paramContext);
    this.f = paramContext.getApplicationContext();
  }
  
  private int a(String paramString1, SynthesizerListener paramSynthesizerListener, String paramString2)
  {
    ad.a("new Session Start");
    this.g = new K(this.f);
    this.g.a(this);
    String str = this.b.e("tts_audio_path");
    int i = this.g.a(paramString1, this.b, paramSynthesizerListener, true, str);
    if (!TextUtils.isEmpty(paramString2))
    {
      this.h = new K(this.f);
      this.h.a(this);
      this.h.a(paramString2, this.b);
    }
    return i;
  }
  
  /* Error */
  public int a(String paramString, SynthesizerListener paramSynthesizerListener)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: ldc 70
    //   5: invokestatic 37	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield 47	com/iflytek/cloud/thirdparty/ap:b	Lcom/iflytek/cloud/thirdparty/ag;
    //   14: ldc 72
    //   16: invokevirtual 75	com/iflytek/cloud/thirdparty/ag:d	(Ljava/lang/String;)Ljava/lang/String;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 19	com/iflytek/cloud/thirdparty/ap:g	Lcom/iflytek/cloud/thirdparty/K;
    //   25: ifnull +30 -> 55
    //   28: aload_0
    //   29: getfield 19	com/iflytek/cloud/thirdparty/ap:g	Lcom/iflytek/cloud/thirdparty/K;
    //   32: invokevirtual 78	com/iflytek/cloud/thirdparty/K:h	()Z
    //   35: ifeq +20 -> 55
    //   38: aload_0
    //   39: getfield 19	com/iflytek/cloud/thirdparty/ap:g	Lcom/iflytek/cloud/thirdparty/K;
    //   42: aload_0
    //   43: getfield 47	com/iflytek/cloud/thirdparty/ap:b	Lcom/iflytek/cloud/thirdparty/ag;
    //   46: ldc 80
    //   48: iconst_0
    //   49: invokevirtual 83	com/iflytek/cloud/thirdparty/ag:a	(Ljava/lang/String;Z)Z
    //   52: invokevirtual 87	com/iflytek/cloud/thirdparty/K:cancel	(Z)V
    //   55: aload_0
    //   56: getfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   59: ifnonnull +21 -> 80
    //   62: aload_0
    //   63: aload_1
    //   64: aload_2
    //   65: aload 5
    //   67: invokespecial 89	com/iflytek/cloud/thirdparty/ap:a	(Ljava/lang/String;Lcom/iflytek/cloud/SynthesizerListener;Ljava/lang/String;)I
    //   70: istore_3
    //   71: aload_0
    //   72: monitorexit
    //   73: ldc 91
    //   75: invokestatic 37	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   78: iload_3
    //   79: ireturn
    //   80: aload_1
    //   81: aload_0
    //   82: getfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   85: getfield 94	com/iflytek/cloud/thirdparty/K:h	Ljava/lang/String;
    //   88: invokevirtual 100	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   91: ifne +28 -> 119
    //   94: aload_0
    //   95: getfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   98: iconst_0
    //   99: invokevirtual 87	com/iflytek/cloud/thirdparty/K:cancel	(Z)V
    //   102: aload_0
    //   103: aconst_null
    //   104: putfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   107: aload_0
    //   108: aload_1
    //   109: aload_2
    //   110: aload 5
    //   112: invokespecial 89	com/iflytek/cloud/thirdparty/ap:a	(Ljava/lang/String;Lcom/iflytek/cloud/SynthesizerListener;Ljava/lang/String;)I
    //   115: istore_3
    //   116: goto -45 -> 71
    //   119: aload_0
    //   120: getfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   123: getfield 104	com/iflytek/cloud/thirdparty/K:i	Lcom/iflytek/cloud/SpeechError;
    //   126: ifnonnull +13 -> 139
    //   129: aload_0
    //   130: getfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   133: getfield 107	com/iflytek/cloud/thirdparty/K:f	Z
    //   136: ifne +28 -> 164
    //   139: aload_0
    //   140: getfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   143: iconst_0
    //   144: invokevirtual 87	com/iflytek/cloud/thirdparty/K:cancel	(Z)V
    //   147: aload_0
    //   148: aconst_null
    //   149: putfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   152: aload_0
    //   153: aload_1
    //   154: aload_2
    //   155: aload 5
    //   157: invokespecial 89	com/iflytek/cloud/thirdparty/ap:a	(Ljava/lang/String;Lcom/iflytek/cloud/SynthesizerListener;Ljava/lang/String;)I
    //   160: istore_3
    //   161: goto -90 -> 71
    //   164: aload_0
    //   165: getfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   168: astore_1
    //   169: aload_0
    //   170: aconst_null
    //   171: putfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   174: aload 5
    //   176: invokestatic 64	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   179: ifne +39 -> 218
    //   182: aload_0
    //   183: new 39	com/iflytek/cloud/thirdparty/K
    //   186: dup
    //   187: aload_0
    //   188: getfield 17	com/iflytek/cloud/thirdparty/ap:f	Landroid/content/Context;
    //   191: invokespecial 40	com/iflytek/cloud/thirdparty/K:<init>	(Landroid/content/Context;)V
    //   194: putfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   197: aload_0
    //   198: getfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   201: aload_0
    //   202: invokevirtual 43	com/iflytek/cloud/thirdparty/K:a	(Lcom/iflytek/cloud/thirdparty/K$a;)V
    //   205: aload_0
    //   206: getfield 21	com/iflytek/cloud/thirdparty/ap:h	Lcom/iflytek/cloud/thirdparty/K;
    //   209: aload 5
    //   211: aload_0
    //   212: getfield 47	com/iflytek/cloud/thirdparty/ap:b	Lcom/iflytek/cloud/thirdparty/ag;
    //   215: invokevirtual 67	com/iflytek/cloud/thirdparty/K:a	(Ljava/lang/String;Lcom/iflytek/cloud/thirdparty/ag;)V
    //   218: aload_0
    //   219: aload_1
    //   220: putfield 19	com/iflytek/cloud/thirdparty/ap:g	Lcom/iflytek/cloud/thirdparty/K;
    //   223: aload_0
    //   224: getfield 19	com/iflytek/cloud/thirdparty/ap:g	Lcom/iflytek/cloud/thirdparty/K;
    //   227: aload_2
    //   228: invokevirtual 110	com/iflytek/cloud/thirdparty/K:a	(Lcom/iflytek/cloud/SynthesizerListener;)V
    //   231: aload_0
    //   232: getfield 19	com/iflytek/cloud/thirdparty/ap:g	Lcom/iflytek/cloud/thirdparty/K;
    //   235: invokevirtual 113	com/iflytek/cloud/thirdparty/K:i	()V
    //   238: iload 4
    //   240: istore_3
    //   241: aload_0
    //   242: getfield 19	com/iflytek/cloud/thirdparty/ap:g	Lcom/iflytek/cloud/thirdparty/K;
    //   245: getfield 115	com/iflytek/cloud/thirdparty/K:g	Z
    //   248: ifeq -177 -> 71
    //   251: aload_0
    //   252: invokevirtual 117	com/iflytek/cloud/thirdparty/ap:a	()V
    //   255: ldc 119
    //   257: invokestatic 37	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   260: iload 4
    //   262: istore_3
    //   263: goto -192 -> 71
    //   266: astore_1
    //   267: aload_0
    //   268: monitorexit
    //   269: aload_1
    //   270: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	271	0	this	ap
    //   0	271	1	paramString	String
    //   0	271	2	paramSynthesizerListener	SynthesizerListener
    //   70	193	3	i	int
    //   1	260	4	j	int
    //   19	191	5	str	String
    // Exception table:
    //   from	to	target	type
    //   10	55	266	finally
    //   55	71	266	finally
    //   71	73	266	finally
    //   80	116	266	finally
    //   119	139	266	finally
    //   139	161	266	finally
    //   164	218	266	finally
    //   218	238	266	finally
    //   241	260	266	finally
    //   267	269	266	finally
  }
  
  public int a(String paramString1, String paramString2, SynthesizerListener paramSynthesizerListener)
  {
    ad.a("synthesizeToUri enter");
    try
    {
      if ((this.g != null) && (this.g.h())) {
        this.g.cancel(this.b.a("tts_interrupt_error", false));
      }
      this.g = new K(this.f);
      int i = this.g.a(paramString1, paramString2, this.b, paramSynthesizerListener);
      ad.a("synthesizeToUri leave");
      return i;
    }
    finally {}
  }
  
  public void a()
  {
    try
    {
      if (this.h != null) {
        this.h.e();
      }
      return;
    }
    finally {}
  }
  
  public void a(boolean paramBoolean)
  {
    ad.a("stopSpeaking enter:" + paramBoolean);
    try
    {
      if (this.g != null)
      {
        ad.a("-->stopSpeaking cur");
        this.g.cancel(paramBoolean);
        this.g = null;
      }
      if (this.h != null)
      {
        ad.a("-->stopSpeaking cur next");
        this.h.cancel(false);
        this.h = null;
      }
      ad.a("stopSpeaking leave");
      return;
    }
    finally {}
  }
  
  public boolean destroy()
  {
    a(false);
    super.destroy();
    return true;
  }
  
  public void e()
  {
    ad.a("pauseSpeaking enter");
    try
    {
      if (this.g != null) {
        this.g.g();
      }
      ad.a("pauseSpeaking leave");
      return;
    }
    finally {}
  }
  
  public void f()
  {
    ad.a("resumeSpeaking enter");
    try
    {
      if (this.g != null) {
        this.g.i();
      }
      ad.a("resumeSpeaking leave");
      return;
    }
    finally {}
  }
  
  public boolean g()
  {
    boolean bool = false;
    ad.a("isSpeaking enter");
    try
    {
      if (this.g != null) {
        bool = this.g.h();
      }
      ad.a("isSpeaking leave");
      return bool;
    }
    finally {}
  }
  
  public int h()
  {
    int i = 4;
    ad.a("getState enter");
    try
    {
      if (this.g != null) {
        i = this.g.f();
      }
      ad.a("getState leave");
      return i;
    }
    finally {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\ap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */