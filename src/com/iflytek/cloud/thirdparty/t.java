package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.VerifierListener;
import com.iflytek.cloud.VerifierResult;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.record.PcmRecorder.PcmRecordListener;
import com.iflytek.cloud.record.a;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class t
  extends B
  implements PcmRecorder.PcmRecordListener
{
  protected volatile VerifierListener a = null;
  protected long b = 0L;
  protected boolean c = true;
  protected u d = new u();
  protected PcmRecorder e = null;
  protected String f = "train";
  protected String g = "";
  protected VerifierResult h = null;
  protected ConcurrentLinkedQueue<byte[]> i = null;
  protected int j = 1;
  private long k = 0L;
  private int l = 0;
  
  public t(Context paramContext, ag paramag, HandlerThread paramHandlerThread)
  {
    super(paramContext, paramHandlerThread);
    a(paramag);
  }
  
  private void A()
    throws SpeechError, UnsupportedEncodingException
  {
    ad.a("--->requestResult: in");
    C.a locala = this.d.e();
    switch (1.a[locala.ordinal()])
    {
    }
    for (;;)
    {
      ad.a("--->requestResult: out");
      return;
      B();
    }
  }
  
  private void B()
    throws SpeechError, UnsupportedEncodingException
  {
    this.t = SystemClock.elapsedRealtime();
    this.h = new VerifierResult(new String(this.d.d(), "utf-8"));
    if (this.a != null)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("session_id", f());
      this.a.onEvent(20001, 0, 0, localBundle);
    }
    if ((this.f.equals("train")) && (this.h.ret == 0) && (this.h.suc < this.h.rgn))
    {
      if (this.a != null)
      {
        ae.a("GetNotifyResult", null);
        this.a.onResult(this.h);
      }
      a(0);
      return;
    }
    if (this.a != null)
    {
      ae.a("GetNotifyResult", null);
      this.a.onResult(this.h);
    }
    b(null);
  }
  
  private void C()
  {
    if (this.e != null)
    {
      this.e.stopRecord(v().a("record_force_stop", false));
      this.e = null;
    }
  }
  
  private boolean l()
  {
    return "train".equalsIgnoreCase(v().e("sst"));
  }
  
  private void n()
    throws SpeechError, IOException, InterruptedException
  {
    ad.a("--->onStoped: in");
    if (!l()) {
      C();
    }
    this.d.a();
    a(4);
    ad.a("--->onStoped: out");
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
      b();
      return;
    case 1: 
      h();
      return;
    case 2: 
      b(paramMessage);
      return;
    case 3: 
      n();
      return;
    case 4: 
      c(paramMessage);
      return;
    }
    ad.a("--->on timeout vad");
    i();
  }
  
  protected void a(SpeechError paramSpeechError)
  {
    ad.a("--->onEnd: in");
    C();
    f();
    ae.a("SessionEndBegin", null);
    if (this.s)
    {
      this.d.a("user abort");
      ae.a("SessionEndEnd", null);
      super.a(paramSpeechError);
      if ((this.a != null) && (!this.s)) {
        break label123;
      }
    }
    for (;;)
    {
      this.a = null;
      ad.a("--->onEnd: out");
      return;
      if (paramSpeechError != null)
      {
        this.d.a("error" + paramSpeechError.getErrorCode());
        break;
      }
      this.d.a("success");
      break;
      label123:
      ad.a("VerifyListener#onEnd");
      if (paramSpeechError != null)
      {
        Bundle localBundle = new Bundle();
        localBundle.putString("session_id", f());
        this.a.onEvent(20001, 0, 0, localBundle);
        this.a.onError(paramSpeechError);
      }
    }
  }
  
  public void a(VerifierListener paramVerifierListener)
  {
    try
    {
      ad.a("--->startVerify: in");
      this.a = paramVerifierListener;
      a_();
      ad.a("--->startVerify: out");
      return;
    }
    finally
    {
      paramVerifierListener = finally;
      throw paramVerifierListener;
    }
  }
  
  public void a(byte[] paramArrayOfByte, int paramInt)
  {
    if (t()) {
      this.a.onVolumeChanged(paramInt, paramArrayOfByte);
    }
  }
  
  protected void a(byte[] paramArrayOfByte, boolean paramBoolean)
    throws SpeechError
  {
    this.d.a(paramArrayOfByte, paramArrayOfByte.length);
    if (paramBoolean)
    {
      if (this.d.b())
      {
        ad.a("---> VadCheck Time: Vad End Point");
        i();
      }
    }
    else {
      return;
    }
    a(paramArrayOfByte, this.d.c());
  }
  
  /* Error */
  public boolean a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 310
    //   5: invokestatic 87	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   8: aload_0
    //   9: invokevirtual 314	com/iflytek/cloud/thirdparty/t:u	()Lcom/iflytek/cloud/thirdparty/B$b;
    //   12: getstatic 319	com/iflytek/cloud/thirdparty/B$b:c	Lcom/iflytek/cloud/thirdparty/B$b;
    //   15: if_acmpeq +35 -> 50
    //   18: new 260	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 261	java/lang/StringBuilder:<init>	()V
    //   25: ldc_w 321
    //   28: invokevirtual 267	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_0
    //   32: invokevirtual 314	com/iflytek/cloud/thirdparty/t:u	()Lcom/iflytek/cloud/thirdparty/B$b;
    //   35: invokevirtual 324	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 276	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: invokestatic 87	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   44: iconst_0
    //   45: istore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: iload_1
    //   49: ireturn
    //   50: aload_0
    //   51: invokespecial 212	com/iflytek/cloud/thirdparty/t:l	()Z
    //   54: ifne +7 -> 61
    //   57: aload_0
    //   58: invokespecial 214	com/iflytek/cloud/thirdparty/t:C	()V
    //   61: aload_0
    //   62: iconst_3
    //   63: invokevirtual 172	com/iflytek/cloud/thirdparty/t:a	(I)V
    //   66: ldc_w 326
    //   69: invokestatic 87	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   72: iconst_1
    //   73: istore_1
    //   74: goto -28 -> 46
    //   77: astore_2
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_2
    //   81: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	this	t
    //   45	29	1	bool	boolean
    //   77	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	44	77	finally
    //   50	61	77	finally
    //   61	72	77	finally
  }
  
  protected void b()
    throws Exception
  {
    ad.a("--->onStart: in");
    String str = v().e("engine_type");
    boolean bool = v().a("net_check", true);
    if (("cloud".equals(str)) && (bool)) {
      W.b(this.r);
    }
    int m = v().a("record_read_rate", 40);
    if ((this.j != -1) && (t()))
    {
      ad.a("start  record");
      if (this.e == null)
      {
        this.e = new PcmRecorder(s(), m, this.j);
        this.e.startRecording(this);
      }
    }
    if ((u() != B.b.e) && (this.a != null)) {
      this.a.onBeginOfSpeech();
    }
    this.b = SystemClock.elapsedRealtime();
    removeMessages(9);
    a(9, B.a.b, false, this.p);
    a(1, B.a.a, false, 0);
    ad.a("--->onStart: out");
  }
  
  protected void b(Message paramMessage)
    throws Exception
  {
    paramMessage = (byte[])paramMessage.obj;
    if ((paramMessage == null) || (paramMessage.length == 0)) {
      return;
    }
    this.i.add(paramMessage);
    a(paramMessage, true);
  }
  
  public void b(boolean paramBoolean)
  {
    if ((paramBoolean) && (t()) && (this.a != null)) {
      this.a.onError(new SpeechError(20017));
    }
    C();
    super.b(paramBoolean);
  }
  
  protected void c()
  {
    this.p = v().a("speech_timeout", this.p);
    this.g = v().e("vid");
    this.j = v().a("audio_source", 1);
    this.l = (v().a("filter_audio_time", 0) * (v().a("sample_rate", this.q) / 1000 * 16 / 8));
    ad.a("mSpeechTimeOut=" + this.p);
    super.c();
  }
  
  void c(Message paramMessage)
    throws SpeechError, InterruptedException, UnsupportedEncodingException
  {
    if (!l()) {
      C();
    }
    A();
    if (u() == B.b.d) {
      a(4, B.a.b, false, 20);
    }
  }
  
  public String e()
  {
    return this.d.g();
  }
  
  public String f()
  {
    return this.d.f();
  }
  
  public String g()
  {
    return "ivp";
  }
  
  protected void h()
    throws Exception
  {
    if (this.d.a == null)
    {
      ae.a("SDKSessionBegin", null);
      this.d.a(this.r, this.g, this);
    }
    a(B.b.c);
  }
  
  public void i()
  {
    if (B.b.c == u())
    {
      ad.a("--->vadEndCall: out");
      a();
      if (this.a != null) {
        this.a.onEndOfSpeech();
      }
    }
  }
  
  public ConcurrentLinkedQueue<byte[]> j()
  {
    return this.i;
  }
  
  public int k()
  {
    return this.j;
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
    if (this.l > 0)
    {
      if (this.l >= paramInt2)
      {
        this.l -= paramInt2;
        return;
      }
      arrayOfByte = new byte[paramInt2 - this.l];
      System.arraycopy(paramArrayOfByte, this.l + paramInt1, arrayOfByte, 0, paramInt2 - this.l);
      d(obtainMessage(2, arrayOfByte));
      this.l = 0;
      return;
    }
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
    d(obtainMessage(2, arrayOfByte));
  }
  
  public void onRecordReleased()
  {
    if ((this.e != null) && ((this.e instanceof a))) {
      a();
    }
  }
  
  public void onRecordStarted(boolean paramBoolean)
  {
    ad.a("time cost: onRecordStarted:" + (SystemClock.elapsedRealtime() - this.k));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */