package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.aimic.AIMic;
import com.iflytek.aimic.AIMic.IvwDataListener;
import com.iflytek.cloud.Setting;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.WakeuperListener;
import com.iflytek.cloud.record.PcmRecorder;

public class v
  extends x
  implements AIMic.IvwDataListener
{
  private final String A = "result";
  private int B = 0;
  private AIMic C = null;
  private final int D = 0;
  private final String j = "log_record";
  private final String k = "only_aimic";
  private final String l = "only_msc";
  private final String m = "ivw_caller";
  private final int n = 22;
  private final int o = 23;
  private final String w = "sid";
  private final String x = "msg";
  private final String y = "arg1";
  private final String z = "arg2";
  
  public v(Context paramContext, ag paramag, HandlerThread paramHandlerThread)
  {
    super(paramContext, paramag, paramHandlerThread);
    v().a("ivw_caller", "1", false);
    this.C = AIMic.getAIMic();
    if (this.C == null) {
      this.C = AIMic.createAIMic(v().d("aimic_init_param"));
    }
    if (this.C != null) {
      this.C.registerListener(this);
    }
  }
  
  private SpeechError c(SpeechError paramSpeechError)
  {
    ad.a("AIMicEnd enter");
    SpeechError localSpeechError = paramSpeechError;
    Object localObject;
    if (this.C != null)
    {
      this.C.unregisterListener(this);
      this.C.stopListening();
      localSpeechError = null;
      localObject = "success";
    }
    for (;;)
    {
      try
      {
        if (!this.s) {
          continue;
        }
        localObject = "user abort";
        i = this.C.setParameter("ivw_sse", (String)localObject);
        localObject = localSpeechError;
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
      {
        i = 20021;
        continue;
      }
      catch (Throwable localThrowable)
      {
        int i = 20999;
        continue;
      }
      localSpeechError = paramSpeechError;
      if (paramSpeechError == null) {
        if (localObject == null)
        {
          localSpeechError = paramSpeechError;
          if (i == 0) {}
        }
        else
        {
          ad.b("AIMicEnd error!");
          localSpeechError = new SpeechError((Throwable)localObject, i);
        }
      }
      ad.a("AIMicEnd leave");
      return localSpeechError;
      if (paramSpeechError != null) {
        localObject = "error" + paramSpeechError.getErrorCode();
      }
    }
  }
  
  private void d(SpeechError paramSpeechError)
  {
    b(paramSpeechError);
  }
  
  private void e(Message paramMessage)
    throws SpeechError, Throwable
  {
    ad.a("proc_Wakeup_Angle enter");
    Object localObject;
    int i;
    if (this.b != null)
    {
      localObject = paramMessage.peekData();
      i = ((Bundle)localObject).getInt("msg");
    }
    for (;;)
    {
      try
      {
        paramMessage = x.a.values()[i];
        i = ((Bundle)localObject).getInt("arg1");
        switch (1.a[paramMessage.ordinal()])
        {
        default: 
          paramMessage = null;
          if (paramMessage == null) {
            return;
          }
          ad.b("wakeup msg error: " + paramMessage.getErrorCode());
          throw paramMessage;
        }
      }
      catch (Throwable paramMessage)
      {
        ad.b("warn: unmatched ivw message: " + i);
        paramMessage = x.a.a;
        continue;
        a((Bundle)localObject);
        paramMessage = null;
        continue;
        paramMessage = new SpeechError(i);
        continue;
        localObject = obtainMessage(4, i, 1, ((Bundle)localObject).getByteArray("result"));
        if (hasMessages(4))
        {
          paramMessage = B.a.b;
          a((Message)localObject, paramMessage, false, 0);
          paramMessage = null;
          continue;
        }
        paramMessage = B.a.a;
        continue;
        if (ah.a.d.ordinal() != i) {
          continue;
        }
        i();
        paramMessage = null;
        continue;
        if (this.b == null) {
          continue;
        }
        this.b.onVolumeChanged(i);
        paramMessage = null;
        continue;
        a(obtainMessage(4, 0, 2, ((Bundle)localObject).getByteArray("result")), B.a.a, false, 0);
        paramMessage = null;
        continue;
        ad.a("proc_Wakeup_Msg reset msg");
        paramMessage = null;
        continue;
      }
      ad.b("proc_Wakeup_Angle error: listener is null");
      paramMessage = new SpeechError(20999);
    }
  }
  
  private void k()
    throws Throwable
  {
    if (t())
    {
      n();
      if ((u() != B.b.e) && (this.b != null)) {
        this.b.onBeginOfSpeech();
      }
      return;
    }
    ad.a("It's not running while start aimic listening!");
  }
  
  private void l()
    throws SpeechError, Throwable
  {
    if (this.B <= 0)
    {
      ad.b("Channel number " + this.B + " is less than 1 !");
      throw new SpeechError(20012);
    }
    if (this.C == null)
    {
      ad.b("create AIMIC failed!");
      throw new SpeechError(21003);
    }
    this.C.setShowLog(Setting.getShowLog());
    int i1 = this.C.reset();
    int i = i1;
    String str;
    if (i1 == 0)
    {
      str = v().d("log_record");
      i = i1;
      if (!TextUtils.isEmpty(str)) {
        i = this.C.setParameter("log_record", str);
      }
    }
    i1 = i;
    if (i == 0)
    {
      str = v().d("only_aimic");
      i1 = i;
      if (!TextUtils.isEmpty(str)) {
        i1 = this.C.setParameter("only_aimic", str);
      }
    }
    i = i1;
    if (i1 == 0)
    {
      str = v().d("only_msc");
      i = i1;
      if (!TextUtils.isEmpty(str)) {
        i = this.C.setParameter("only_msc", str);
      }
    }
    i1 = i;
    if (i == 0)
    {
      str = v().d("aimic_asr_buffer_time");
      i1 = i;
      if (!TextUtils.isEmpty(str)) {
        i1 = this.C.setParameter("aimic_asr_buffer_time", str);
      }
    }
    i = i1;
    if (i1 == 0)
    {
      str = v().d("ivw_audio");
      i = i1;
      if (!TextUtils.isEmpty(str)) {
        i = this.C.setParameter("ivw_audio", str);
      }
    }
    i1 = i;
    if (i == 0)
    {
      str = v().d("ivw_buf_tm");
      i1 = i;
      if (!TextUtils.isEmpty(str)) {
        i1 = this.C.setParameter("ivw_buf_tm", str);
      }
    }
    i = i1;
    if (i1 == 0)
    {
      str = v().d("ivw_save_file");
      i = i1;
      if (!TextUtils.isEmpty(str)) {
        i = this.C.setParameter("ivw_save_file", str);
      }
    }
    i1 = i;
    if (i == 0) {
      i1 = this.C.setParameter("ivw_thread_num", Integer.toString(this.B));
    }
    i = i1;
    if (i1 == 0) {
      i = this.C.setParameter("ivw_ssb", ai.b(this.r, this.a, this));
    }
    if (i != 0)
    {
      ad.b("aimic init error: " + i);
      throw new SpeechError(i);
    }
  }
  
  private void n()
    throws SpeechError
  {
    int i1 = 0;
    String str = v().d("alsa_card");
    if (!TextUtils.isEmpty(str)) {
      i1 = this.C.setParameter("alsa_card", str);
    }
    str = v().d("alsa_rate");
    int i = i1;
    if (i1 == 0)
    {
      i = i1;
      if (!TextUtils.isEmpty(str)) {
        i = this.C.setParameter("alsa_rate", str);
      }
    }
    str = v().d("alsa_save");
    i1 = i;
    if (i == 0)
    {
      i1 = i;
      if (!TextUtils.isEmpty(str)) {
        i1 = this.C.setParameter("alsa_save", str);
      }
    }
    i = i1;
    if (i1 == 0) {
      if (-3 != this.d) {
        break label191;
      }
    }
    label191:
    for (i = -3;; i = -1)
    {
      i = this.C.setParameter("audio_source", Integer.toString(i));
      i1 = i;
      if (i == 0) {
        i1 = this.C.startListening();
      }
      if (i1 == 0) {
        break;
      }
      ad.b("start aimic listening failed: " + i1);
      throw new SpeechError(i1);
    }
  }
  
  public int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = 22001;
    if (this.C != null) {
      i = this.C.writeAudio(paramArrayOfByte, paramInt1, paramInt2);
    }
    return i;
  }
  
  protected void a()
    throws SpeechError, Throwable
  {
    l();
    a(B.b.c);
    if (this.b != null) {
      this.b.onEvent(10010, 0, 0, null);
    }
    a(23, B.a.b, true, 0);
  }
  
  protected void a(Bundle paramBundle)
    throws SpeechError, Throwable
  {
    this.h = true;
    Object localObject = C.a.f;
    if ((this.i) || ("oneshot".equals(this.a))) {
      localObject = C.a.a;
    }
    localObject = obtainMessage(4, ((C.a)localObject).ordinal(), 0, paramBundle.getByteArray("result"));
    ((Message)localObject).setData(paramBundle);
    if (hasMessages(4)) {}
    for (paramBundle = B.a.b;; paramBundle = B.a.a)
    {
      a((Message)localObject, paramBundle, false, 0);
      return;
    }
  }
  
  protected void a(Message paramMessage)
    throws Throwable, SpeechError
  {
    super.a(paramMessage);
    switch (paramMessage.what)
    {
    default: 
      return;
    case 22: 
      e(paramMessage);
      return;
    }
    k();
  }
  
  protected void a(SpeechError paramSpeechError)
  {
    ad.a("Aimic Wakeuper onEnd enter");
    super.a(c(paramSpeechError));
  }
  
  protected void b()
    throws Exception
  {
    ad.a("start connecting");
    this.h = false;
    int i = v().a("record_read_rate", 40);
    if (-3 == this.d)
    {
      if (this.C == null)
      {
        ad.b("create AIMIC failed!");
        throw new SpeechError(21003);
      }
    }
    else if ((this.d != -1) && (t()))
    {
      ad.a("start  record");
      if (this.f == null)
      {
        this.f = new PcmRecorder(s(), i, this.d);
        this.f.startRecording(this);
      }
    }
    a(1, B.a.a, false, 0);
  }
  
  public void onError(int paramInt)
  {
    onError(new SpeechError(paramInt));
  }
  
  public void onWakeupAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Object paramObject) {}
  
  public void onWakeupMsg(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, int paramInt4, byte[] paramArrayOfByte2, int paramInt5, byte[] paramArrayOfByte3, int paramInt6)
  {
    try
    {
      Bundle localBundle = new Bundle();
      byte[] arrayOfByte = null;
      if (x.a.b.ordinal() == paramInt1)
      {
        arrayOfByte = new byte[paramInt6];
        System.arraycopy(paramArrayOfByte3, 0, arrayOfByte, 0, arrayOfByte.length);
        ad.a("onWakeupMsg audio length:" + arrayOfByte.length);
      }
      localBundle.putByteArray("ivw_audio", arrayOfByte);
      if (paramArrayOfByte1 != null)
      {
        paramArrayOfByte3 = new byte[paramInt4];
        System.arraycopy(paramArrayOfByte1, 0, paramArrayOfByte3, 0, paramArrayOfByte3.length);
        localBundle.putByteArray("sid", paramArrayOfByte3);
      }
      if (paramArrayOfByte2 != null)
      {
        paramArrayOfByte1 = new byte[paramInt5];
        System.arraycopy(paramArrayOfByte2, 0, paramArrayOfByte1, 0, paramArrayOfByte1.length);
        localBundle.putByteArray("result", paramArrayOfByte1);
      }
      localBundle.putInt("msg", paramInt1);
      localBundle.putInt("arg1", paramInt2);
      localBundle.putInt("arg2", paramInt3);
      paramArrayOfByte2 = obtainMessage(22);
      paramArrayOfByte2.setData(localBundle);
      if (hasMessages(22)) {}
      for (paramArrayOfByte1 = B.a.b;; paramArrayOfByte1 = B.a.a)
      {
        a(paramArrayOfByte2, paramArrayOfByte1, false, 0);
        return;
      }
      return;
    }
    catch (Throwable paramArrayOfByte1)
    {
      ad.a(paramArrayOfByte1);
      System.gc();
      d(new SpeechError(20999));
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\v.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */