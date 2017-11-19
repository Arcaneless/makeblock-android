package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.record.PcmRecorder.PcmRecordListener;
import com.iflytek.cloud.record.a;
import com.iflytek.msc.MSC;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class p
  extends B
  implements PcmRecorder.PcmRecordListener
{
  public static int j = 0;
  public static int k = 0;
  protected volatile RecognizerListener a = null;
  protected boolean b = false;
  protected boolean c = false;
  protected boolean d = false;
  protected boolean e = false;
  protected int f = 1;
  protected boolean g = true;
  protected o h = new o();
  protected PcmRecorder i = null;
  protected String l = null;
  protected ConcurrentLinkedQueue<byte[]> m = null;
  protected ArrayList<String> n = null;
  protected D o = new D();
  private int w = 0;
  private boolean x = false;
  private int y = 0;
  
  public p(Context paramContext, ag paramag, HandlerThread paramHandlerThread)
  {
    super(paramContext, paramHandlerThread);
    a(paramag);
  }
  
  private void A()
  {
    if (this.i != null)
    {
      this.i.stopRecord(v().a("record_force_stop", false));
      this.i = null;
      this.o.a("rec_close");
      if (this.a != null) {
        this.a.onEvent(22003, 0, 0, null);
      }
    }
  }
  
  private void a(boolean paramBoolean, byte[] paramArrayOfByte)
    throws SpeechError, UnsupportedEncodingException
  {
    this.t = SystemClock.elapsedRealtime();
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0)) {
      paramArrayOfByte = new String(paramArrayOfByte, "utf-8");
    }
    try
    {
      for (;;)
      {
        this.v.a(paramArrayOfByte, paramBoolean);
        this.n.add(paramArrayOfByte);
        if ((this.a != null) && (t()))
        {
          Bundle localBundle = new Bundle();
          localBundle.putString("session_id", f());
          this.a.onEvent(20001, 0, 0, localBundle);
          if ((paramBoolean) && (v().a("request_audio_url", false)))
          {
            localBundle = new Bundle();
            localBundle.putString("audio_url", this.h.d());
            this.a.onEvent(23001, 0, 0, localBundle);
          }
          paramArrayOfByte = new RecognizerResult(paramArrayOfByte);
          ae.a("GetNotifyResult", null);
          this.a.onResult(paramArrayOfByte, paramBoolean);
        }
        ad.a("msc result time:" + System.currentTimeMillis());
        if (paramBoolean) {
          b(null);
        }
        return;
        if (this.n.size() <= 0)
        {
          paramArrayOfByte = v().e("local_grammar");
          if ((!TextUtils.isEmpty(paramArrayOfByte)) && (!"sms.irf".equals(paramArrayOfByte))) {
            throw new SpeechError(20005);
          }
          if (v().a("asr_nomatch_error", true)) {
            throw new SpeechError(10118);
          }
          paramArrayOfByte = "";
        }
        else
        {
          paramArrayOfByte = "";
        }
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        ad.b("DC exception:");
        ad.a(localThrowable);
      }
    }
  }
  
  public int a()
  {
    return this.f;
  }
  
  public int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    onRecordBuffer(paramArrayOfByte, paramInt1, paramInt2);
    return 0;
  }
  
  protected void a(Message paramMessage)
    throws Throwable, SpeechError
  {
    super.a(paramMessage);
    switch (paramMessage.what)
    {
    case 5: 
    case 6: 
    case 8: 
    default: 
      return;
    case 0: 
      h();
      return;
    case 1: 
      i();
      return;
    case 9: 
      l();
      return;
    case 2: 
      b(paramMessage);
      return;
    case 3: 
      j();
      return;
    case 4: 
      c(paramMessage);
      return;
    }
    k();
  }
  
  public void a(RecognizerListener paramRecognizerListener)
  {
    try
    {
      this.a = paramRecognizerListener;
      ad.a("startListening called");
      a_();
      return;
    }
    finally
    {
      paramRecognizerListener = finally;
      throw paramRecognizerListener;
    }
  }
  
  protected void a(SpeechError paramSpeechError)
  {
    ad.a("onSessionEnd");
    A();
    j = this.h.b("upflow");
    k = this.h.b("downflow");
    f();
    SpeechError localSpeechError = paramSpeechError;
    if (this.n.size() <= 0)
    {
      localSpeechError = paramSpeechError;
      if (paramSpeechError == null)
      {
        localSpeechError = paramSpeechError;
        if (v().a("asr_nomatch_error", true)) {
          localSpeechError = new SpeechError(10118);
        }
      }
    }
    if (localSpeechError != null)
    {
      this.o.a("app_ret", localSpeechError.getErrorCode(), false);
      D localD = this.o;
      if (!this.e) {
        break label225;
      }
      paramSpeechError = "1";
      label121:
      localD.a("rec_ustop", paramSpeechError, false);
      this.h.a("sessinfo", this.o.a());
      ae.a("SessionEndBegin", null);
      if (!this.s) {
        break label232;
      }
      this.h.a("user abort");
      label172:
      ae.a("SessionEndEnd", null);
      super.a(localSpeechError);
      if (this.a != null)
      {
        if (!this.s) {
          break label282;
        }
        ad.a("RecognizerListener#onCancel");
      }
    }
    for (;;)
    {
      this.a = null;
      return;
      this.o.a("app_ret", 0L, false);
      break;
      label225:
      paramSpeechError = "0";
      break label121;
      label232:
      if (localSpeechError != null)
      {
        this.h.a("error" + localSpeechError.getErrorCode());
        break label172;
      }
      this.h.a("success");
      break label172;
      label282:
      ad.a("RecognizerListener#onEnd");
      if (localSpeechError != null)
      {
        paramSpeechError = new Bundle();
        paramSpeechError.putString("session_id", f());
        this.a.onEvent(20001, 0, 0, paramSpeechError);
        this.a.onError(localSpeechError);
      }
    }
  }
  
  public void a(byte[] paramArrayOfByte, int paramInt)
  {
    if ((this.a != null) && (t()))
    {
      this.a.onVolumeChanged(paramInt, paramArrayOfByte);
      if (this.x)
      {
        Bundle localBundle = new Bundle();
        localBundle.putByteArray("data", paramArrayOfByte);
        this.a.onEvent(21003, paramInt, 0, localBundle);
      }
    }
  }
  
  protected void a(byte[] paramArrayOfByte, boolean paramBoolean)
    throws SpeechError
  {
    if (!this.c)
    {
      this.c = true;
      this.o.a("app_fau");
      if (this.a != null) {
        this.a.onEvent(22002, 0, 0, null);
      }
    }
    this.h.a(paramArrayOfByte, paramArrayOfByte.length);
    if (paramBoolean)
    {
      int i1 = this.h.b();
      ad.a("QISRAudioWrite volume:" + i1);
      a(paramArrayOfByte, i1);
    }
  }
  
  public boolean a(boolean paramBoolean)
  {
    try
    {
      ad.a("stopRecognize, current status is :" + u() + " usercancel : " + paramBoolean);
      this.o.a("app_stop");
      A();
      this.e = paramBoolean;
      a(3);
      return true;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected void a_()
  {
    this.o.a(v());
    super.a_();
  }
  
  public ConcurrentLinkedQueue<byte[]> b()
  {
    return this.m;
  }
  
  protected void b(Message paramMessage)
    throws Exception
  {
    paramMessage = (byte[])paramMessage.obj;
    if ((paramMessage == null) || (paramMessage.length == 0)) {
      return;
    }
    this.m.add(paramMessage);
    a(paramMessage, true);
  }
  
  public void b(boolean paramBoolean)
  {
    if ((paramBoolean) && (t()) && (this.a != null)) {
      this.a.onError(new SpeechError(20017));
    }
    A();
    if (u() == B.b.c) {
      this.e = true;
    }
    super.b(paramBoolean);
  }
  
  protected void c()
  {
    this.l = v().e("cloud_grammar");
    this.f = v().a("audio_source", 1);
    this.g = ai.a(v().e("domain"));
    this.w = (v().a("filter_audio_time", 0) * (v().a("sample_rate", this.q) / 1000 * 16 / 8));
    this.p = v().a("speech_timeout", this.p);
    this.x = v().a("notify_record_data", false);
    ad.a("mSpeechTimeOut=" + this.p);
    super.c();
  }
  
  void c(Message paramMessage)
    throws SpeechError, InterruptedException, UnsupportedEncodingException
  {
    int i1 = paramMessage.arg1;
    paramMessage = (byte[])paramMessage.obj;
    switch (i1)
    {
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    default: 
      return;
    case 0: 
      if (!this.d)
      {
        this.d = true;
        this.o.a("app_frs");
      }
      a(false, paramMessage);
      return;
    }
    if (!this.d)
    {
      this.d = true;
      this.o.a("app_frs");
    }
    this.o.a("app_lrs");
    a(true, paramMessage);
  }
  
  public String e()
  {
    return this.h.g();
  }
  
  void errCb(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
  {
    onError(new SpeechError(paramInt));
  }
  
  public String f()
  {
    return this.h.c();
  }
  
  /* Error */
  public String g()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 100	com/iflytek/cloud/thirdparty/p:v	()Lcom/iflytek/cloud/thirdparty/ag;
    //   4: astore_3
    //   5: aload_3
    //   6: ifnull +93 -> 99
    //   9: aload_3
    //   10: ldc -29
    //   12: invokevirtual 230	com/iflytek/cloud/thirdparty/ag:e	(Ljava/lang/String;)Ljava/lang/String;
    //   15: astore_1
    //   16: aload_0
    //   17: getfield 71	com/iflytek/cloud/thirdparty/p:l	Ljava/lang/String;
    //   20: invokestatic 236	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   23: ifeq +50 -> 73
    //   26: aload_1
    //   27: invokestatic 236	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   30: ifeq +43 -> 73
    //   33: ldc_w 465
    //   36: astore_1
    //   37: aload_1
    //   38: astore_2
    //   39: aload_3
    //   40: ifnull +31 -> 71
    //   43: aload_3
    //   44: ldc_w 467
    //   47: iconst_0
    //   48: invokevirtual 107	com/iflytek/cloud/thirdparty/ag:a	(Ljava/lang/String;Z)Z
    //   51: ifne +16 -> 67
    //   54: aload_1
    //   55: astore_2
    //   56: aload_3
    //   57: ldc_w 469
    //   60: iconst_0
    //   61: invokevirtual 107	com/iflytek/cloud/thirdparty/ag:a	(Ljava/lang/String;Z)Z
    //   64: ifeq +7 -> 71
    //   67: ldc_w 471
    //   70: astore_2
    //   71: aload_2
    //   72: areturn
    //   73: ldc_w 473
    //   76: astore_1
    //   77: goto -40 -> 37
    //   80: astore_2
    //   81: aconst_null
    //   82: astore_1
    //   83: ldc_w 475
    //   86: invokestatic 252	com/iflytek/cloud/thirdparty/ad:b	(Ljava/lang/String;)V
    //   89: aload_2
    //   90: invokestatic 255	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/Throwable;)V
    //   93: aload_1
    //   94: areturn
    //   95: astore_2
    //   96: goto -13 -> 83
    //   99: aconst_null
    //   100: astore_1
    //   101: goto -85 -> 16
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	104	0	this	p
    //   15	86	1	str1	String
    //   38	34	2	str2	String
    //   80	10	2	localException1	Exception
    //   95	1	2	localException2	Exception
    //   4	53	3	localag	ag
    // Exception table:
    //   from	to	target	type
    //   0	5	80	java/lang/Exception
    //   9	16	80	java/lang/Exception
    //   16	33	80	java/lang/Exception
    //   43	54	95	java/lang/Exception
    //   56	67	95	java/lang/Exception
  }
  
  protected void h()
    throws Exception
  {
    ad.a("start connecting");
    String str = v().e("engine_type");
    int i1;
    if (v().a("net_check", true))
    {
      if ("cloud".equals(str)) {
        W.b(this.r);
      }
    }
    else
    {
      i1 = v().a("record_read_rate", 40);
      if ((this.f != -1) && (t()))
      {
        ad.a("start  record");
        if (this.f != -2) {
          break label255;
        }
        str = v().e("asr_source_path");
        this.i = new a(s(), i1, this.f, str);
      }
    }
    label255:
    do
    {
      this.o.a("rec_open");
      this.i.startRecording(this);
      if (-1 != this.p) {
        a(9, B.a.b, false, this.p);
      }
      if ((this.a != null) && (this.f > -1)) {
        this.a.onBeginOfSpeech();
      }
      this.o.a("app_ssb");
      a(1, B.a.a, false, 0);
      return;
      if ((!"mix".equals(str)) && (!"mixed".equals(str))) {
        break;
      }
      try
      {
        W.b(this.r);
      }
      catch (Exception localException)
      {
        v().a("engine_type", "local");
      }
      break;
      this.i = new PcmRecorder(s(), i1, this.f);
    } while (!hasMessages(3));
    throw new SpeechError(10118);
  }
  
  protected void i()
    throws Exception
  {
    ae.a("SDKSessionBegin", null);
    int i1 = this.h.a(this.r, this.l, this);
    if ((i1 == 0) && (this.h.a != null)) {
      if (t())
      {
        MSC.QISRRegisterNotify(this.h.a, "rsltCb", "stusCb", "errCb", this);
        a(B.b.c);
        if (v().a("asr_net_perf", false)) {
          a(7, B.a.a, false, 0);
        }
      }
    }
    do
    {
      return;
      this.y += 1;
      if (this.y > 40) {
        throw new SpeechError(i1);
      }
    } while (!t());
    Thread.sleep(15L);
    a(1, B.a.a, false, 0);
  }
  
  protected void j()
    throws SpeechError, IOException, InterruptedException
  {
    ad.a("recording stop");
    A();
    this.o.a("app_lau");
    this.h.a();
    o();
  }
  
  public void k()
  {
    if (t())
    {
      int i1 = this.h.b("netperf");
      if (this.a != null) {
        this.a.onEvent(10001, i1, 0, null);
      }
      a(7, B.a.b, false, 100);
    }
  }
  
  public void l()
  {
    if (B.b.c == u())
    {
      if (this.a != null) {
        this.a.onEndOfSpeech();
      }
      a(false);
    }
  }
  
  public boolean m()
  {
    return this.g;
  }
  
  public D n()
  {
    return this.o;
  }
  
  public void onError(SpeechError paramSpeechError)
  {
    b(paramSpeechError);
  }
  
  public void onRecordBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramArrayOfByte == null) || (paramInt2 <= 0) || (paramArrayOfByte.length < paramInt2)) {}
    while ((paramInt2 <= 0) || (!t())) {
      return;
    }
    if (!this.b)
    {
      this.b = true;
      this.o.a("rec_start");
    }
    if (this.w > 0)
    {
      if (this.w >= paramInt2)
      {
        this.w -= paramInt2;
        return;
      }
      arrayOfByte = new byte[paramInt2 - this.w];
      System.arraycopy(paramArrayOfByte, this.w + paramInt1, arrayOfByte, 0, paramInt2 - this.w);
      d(obtainMessage(2, arrayOfByte));
      this.w = 0;
      return;
    }
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
    d(obtainMessage(2, arrayOfByte));
  }
  
  public void onRecordReleased()
  {
    if ((this.i != null) && ((this.i instanceof a))) {
      a(true);
    }
  }
  
  public void onRecordStarted(boolean paramBoolean)
  {
    this.o.a("rec_ready");
  }
  
  void rsltCb(char[] paramArrayOfChar, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte != null) {
      ad.a("MscRecognizer", "rsltCb:" + paramInt2 + "result:" + new String(paramArrayOfByte));
    }
    for (;;)
    {
      paramArrayOfChar = obtainMessage(4, paramInt2, 0, paramArrayOfByte);
      if (!hasMessages(4)) {
        break;
      }
      a(paramArrayOfChar, B.a.b, false, 0);
      return;
      ad.a("MscRecognizer", "rsltCb:" + paramInt2 + "result:null");
    }
    a(paramArrayOfChar, B.a.a, false, 0);
  }
  
  void stusCb(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte)
  {
    if ((paramInt1 == 0) && (paramInt2 == 3))
    {
      ad.a("MscRecognizer", "stusCb:" + paramInt2 + ",type:" + paramInt1);
      l();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */