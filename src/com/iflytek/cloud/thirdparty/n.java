package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.EvaluatorListener;
import com.iflytek.cloud.EvaluatorResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.record.PcmRecorder.PcmRecordListener;
import com.iflytek.cloud.record.a;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;

public class n
  extends B
  implements PcmRecorder.PcmRecordListener
{
  public static int a = 0;
  public static int b = 0;
  private static Boolean m = Boolean.valueOf(false);
  long c = 0L;
  long d = 0L;
  protected int e = 1;
  protected m f = new m();
  protected PcmRecorder g = null;
  protected D h = new D();
  protected String i = null;
  protected byte[] j = null;
  protected String k = null;
  protected String l = null;
  private volatile EvaluatorListener n = null;
  private ConcurrentLinkedQueue<byte[]> o = null;
  private ConcurrentLinkedQueue<byte[]> w = null;
  private ArrayList<String> x = null;
  private boolean y = false;
  private C.a z = C.a.c;
  
  public n(Context paramContext, ag paramag, HandlerThread paramHandlerThread)
  {
    super(paramContext, paramHandlerThread);
    a(paramag);
  }
  
  private void a(byte[] paramArrayOfByte, int paramInt)
  {
    if ((this.n != null) && (t())) {
      this.n.onVolumeChanged(paramInt, paramArrayOfByte);
    }
  }
  
  private void a(byte[] paramArrayOfByte, boolean paramBoolean)
    throws SpeechError
  {
    this.f.a(paramArrayOfByte, paramArrayOfByte.length);
    if (paramBoolean)
    {
      if (this.f.b() == 3) {
        j();
      }
    }
    else {
      return;
    }
    a(paramArrayOfByte, this.f.c());
  }
  
  private void d(boolean paramBoolean)
    throws SpeechError, UnsupportedEncodingException
  {
    this.t = SystemClock.elapsedRealtime();
    if ((this.f.d() != null) && (this.f.d().length > 0)) {
      this.x.add(new String(this.f.d(), "utf-8"));
    }
    c(paramBoolean);
  }
  
  private void h()
    throws SpeechError, IOException, InterruptedException
  {
    ad.a("--->onStoped: in");
    if (!t()) {
      k();
    }
    this.f.a();
    o();
    ad.a("--->onStoped: out");
  }
  
  private void i()
    throws SpeechError, UnsupportedEncodingException
  {
    C.a locala = this.f.e();
    this.z = locala;
    switch (1.a[locala.ordinal()])
    {
    case 1: 
    default: 
      return;
    case 2: 
      d(false);
      return;
    }
    d(true);
  }
  
  private void j()
  {
    if (B.b.c == u())
    {
      a(false);
      if (this.n != null) {
        this.n.onEndOfSpeech();
      }
    }
  }
  
  private void k()
  {
    if (this.g != null)
    {
      this.g.stopRecord(v().a("record_force_stop", false));
      this.g = null;
    }
  }
  
  protected void a()
    throws Exception
  {
    ad.a("--->onStart: in");
    if (v().a("net_check", true)) {
      W.b(this.r);
    }
    int i1 = v().a("record_read_rate", 40);
    this.e = v().a("audio_source", 1);
    String str;
    if ((this.e != -1) && (t()))
    {
      ad.a("start  record");
      if (this.e != -2) {
        break label190;
      }
      str = v().e("ise_source_path");
    }
    label190:
    for (this.g = new a(s(), i1, this.e, str);; this.g = new PcmRecorder(s(), i1, this.e))
    {
      this.g.startRecording(this);
      if ((u() != B.b.e) && (this.n != null)) {
        this.n.onBeginOfSpeech();
      }
      removeMessages(9);
      if (-1 != this.p) {
        a(9, B.a.b, false, this.p);
      }
      a(1, B.a.a, false, 0);
      ad.a("--->onStart: out");
      return;
    }
  }
  
  protected void a(Message paramMessage)
    throws Throwable, SpeechError
  {
    super.a(paramMessage);
    switch (paramMessage.what)
    {
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    default: 
      return;
    case 0: 
      a();
      return;
    case 1: 
      b();
      return;
    case 2: 
      b(paramMessage);
      return;
    case 3: 
      h();
      return;
    case 4: 
      c(paramMessage);
      return;
    }
    ad.a("--->on timeout vad");
    j();
  }
  
  protected void a(SpeechError paramSpeechError)
  {
    ad.a("--->onEnd: in");
    k();
    f();
    ae.a("SessionEndBegin", null);
    if (this.s)
    {
      this.f.a("user abort");
      ae.a("SessionEndEnd", null);
      super.a(paramSpeechError);
      if ((this.n != null) && (!this.s)) {
        break label127;
      }
    }
    for (;;)
    {
      this.n = null;
      ad.a("--->onEnd: out");
      return;
      if (paramSpeechError != null)
      {
        this.f.a("error" + paramSpeechError.getErrorCode());
        break;
      }
      this.f.a("success");
      break;
      label127:
      ad.a("VerifyListener#onEnd");
      if (paramSpeechError != null)
      {
        Bundle localBundle = new Bundle();
        localBundle.putString("session_id", f());
        this.n.onEvent(20001, 0, 0, localBundle);
        this.n.onError(paramSpeechError);
      }
    }
  }
  
  public void a(String paramString1, String paramString2, EvaluatorListener paramEvaluatorListener)
  {
    try
    {
      m = Boolean.valueOf(false);
      this.k = paramString1;
      this.i = paramString2;
      this.l = v().e("user_model_id");
      this.n = paramEvaluatorListener;
      ad.a("startListening called");
      a_();
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  public void a(byte[] paramArrayOfByte, String paramString, EvaluatorListener paramEvaluatorListener)
  {
    try
    {
      m = Boolean.valueOf(true);
      this.j = paramArrayOfByte;
      this.i = paramString;
      this.l = v().e("user_model_id");
      this.n = paramEvaluatorListener;
      ad.a("startListening called");
      a_();
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
  
  /* Error */
  public boolean a(boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: invokevirtual 205	com/iflytek/cloud/thirdparty/n:u	()Lcom/iflytek/cloud/thirdparty/B$b;
    //   8: getstatic 201	com/iflytek/cloud/thirdparty/B$b:c	Lcom/iflytek/cloud/thirdparty/B$b;
    //   11: if_acmpeq +35 -> 46
    //   14: new 342	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 343	java/lang/StringBuilder:<init>	()V
    //   21: ldc_w 388
    //   24: invokevirtual 349	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: aload_0
    //   28: invokevirtual 205	com/iflytek/cloud/thirdparty/n:u	()Lcom/iflytek/cloud/thirdparty/B$b;
    //   31: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   34: invokevirtual 358	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokestatic 177	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   40: iload_2
    //   41: istore_1
    //   42: aload_0
    //   43: monitorexit
    //   44: iload_1
    //   45: ireturn
    //   46: aload_0
    //   47: getfield 78	com/iflytek/cloud/thirdparty/n:g	Lcom/iflytek/cloud/record/PcmRecorder;
    //   50: ifnull +20 -> 70
    //   53: aload_0
    //   54: getfield 78	com/iflytek/cloud/thirdparty/n:g	Lcom/iflytek/cloud/record/PcmRecorder;
    //   57: aload_0
    //   58: invokevirtual 215	com/iflytek/cloud/thirdparty/n:v	()Lcom/iflytek/cloud/thirdparty/ag;
    //   61: ldc -39
    //   63: iconst_0
    //   64: invokevirtual 222	com/iflytek/cloud/thirdparty/ag:a	(Ljava/lang/String;Z)Z
    //   67: invokevirtual 227	com/iflytek/cloud/record/PcmRecorder:stopRecord	(Z)V
    //   70: aload_0
    //   71: iload_1
    //   72: putfield 99	com/iflytek/cloud/thirdparty/n:y	Z
    //   75: aload_0
    //   76: iconst_3
    //   77: invokevirtual 393	com/iflytek/cloud/thirdparty/n:a	(I)V
    //   80: iconst_1
    //   81: istore_1
    //   82: goto -40 -> 42
    //   85: astore_3
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_3
    //   89: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	n
    //   0	90	1	paramBoolean	boolean
    //   1	40	2	bool	boolean
    //   85	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   4	40	85	finally
    //   46	70	85	finally
    //   70	80	85	finally
  }
  
  protected void b()
    throws Exception
  {
    byte[] arrayOfByte2 = null;
    if (this.f.a == null)
    {
      ae.a("SDKSessionBegin", null);
      this.f.a(this.r, this.l, this);
    }
    byte[] arrayOfByte1;
    m localm;
    if (m.booleanValue()) {
      if ("1".equals(v().e("text_bom")))
      {
        arrayOfByte1 = P.a(this.j);
        localm = this.f;
        if (!TextUtils.isEmpty(this.i)) {
          break label164;
        }
      }
    }
    for (;;)
    {
      localm.a(arrayOfByte1, arrayOfByte2);
      a(B.b.c);
      a(4, B.a.b, false, 20);
      return;
      arrayOfByte1 = this.j;
      break;
      if ("1".equals(v().e("text_bom")))
      {
        arrayOfByte1 = P.a(this.k);
        break;
      }
      arrayOfByte1 = this.k.getBytes("gb2312");
      break;
      label164:
      arrayOfByte2 = this.i.getBytes("gb2312");
    }
  }
  
  protected void b(Message paramMessage)
    throws Exception
  {
    paramMessage = (byte[])paramMessage.obj;
    if ((paramMessage == null) || (paramMessage.length == 0)) {
      return;
    }
    if (!TextUtils.isEmpty(v().e("ise_audio_path"))) {
      this.o.add(paramMessage);
    }
    a(paramMessage, true);
  }
  
  public void b(boolean paramBoolean)
  {
    if ((paramBoolean) && (t()) && (this.n != null)) {
      this.n.onError(new SpeechError(20017));
    }
    k();
    super.b(paramBoolean);
  }
  
  protected void c()
  {
    this.p = v().a("speech_timeout", -1);
    ad.a("mSpeechTimeOut=" + this.p);
    if (("utf-8".equals(v().e("text_encoding"))) && (Locale.CHINA.toString().equalsIgnoreCase(v().e("language")))) {
      v().a("text_bom", "1", false);
    }
    for (;;)
    {
      super.c();
      return;
      v().a("text_bom", "0", false);
    }
  }
  
  void c(Message paramMessage)
    throws SpeechError, InterruptedException, UnsupportedEncodingException
  {
    i();
    if (C.a.c == this.z) {
      a(4, B.a.b, false, 20);
    }
    while (C.a.a != this.z) {
      return;
    }
    a(4);
  }
  
  public void c(boolean paramBoolean)
    throws SpeechError, UnsupportedEncodingException
  {
    ad.a("msc result time:" + System.currentTimeMillis());
    Object localObject = v().b("rse", "gb2312");
    localObject = new EvaluatorResult(new String(this.f.d(), (String)localObject));
    if (this.n != null)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("session_id", f());
      this.n.onEvent(20001, 0, 0, localBundle);
      ae.a("GetNotifyResult", null);
      this.n.onResult((EvaluatorResult)localObject, paramBoolean);
    }
    if (paramBoolean) {
      b(null);
    }
  }
  
  public ConcurrentLinkedQueue<byte[]> d()
  {
    for (;;)
    {
      byte[] arrayOfByte = (byte[])this.w.poll();
      if (arrayOfByte == null) {
        break;
      }
      this.o.add(arrayOfByte);
    }
    return this.o;
  }
  
  public String e()
  {
    return this.f.g();
  }
  
  public String f()
  {
    return this.f.f();
  }
  
  public String g()
  {
    return "ise";
  }
  
  public void onError(SpeechError paramSpeechError)
  {
    b(paramSpeechError);
  }
  
  public void onRecordBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (B.b.c != u()) {}
    while (paramInt2 <= 0) {
      return;
    }
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
    d(obtainMessage(2, arrayOfByte));
  }
  
  public void onRecordReleased()
  {
    if ((this.g != null) && ((this.g instanceof a))) {
      a(true);
    }
  }
  
  public void onRecordStarted(boolean paramBoolean) {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */