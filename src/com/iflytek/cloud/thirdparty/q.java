package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.iflytek.aimic.AIMic;
import com.iflytek.aimic.AIMic.AsrDataListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.record.a;

public class q
  extends p
  implements AIMic.AsrDataListener
{
  private AIMic w = null;
  private int x = 0;
  private int y = this.p * this.q * 2 / 1000;
  
  public q(Context paramContext, ag paramag, HandlerThread paramHandlerThread)
  {
    super(paramContext, paramag, paramHandlerThread);
    if (this.w == null) {
      this.w = AIMic.createAIMic(v().d("aimic_init_param"));
    }
    if (this.w != null) {
      this.w.registerListener(this);
    }
    this.y = (this.p * this.q * 2 / 1000);
  }
  
  private void A()
    throws SpeechError
  {
    int j = 0;
    String str = v().d("alsa_card");
    if (!TextUtils.isEmpty(str)) {
      j = this.w.setParameter("alsa_card", str);
    }
    str = v().d("alsa_rate");
    int i = j;
    if (j == 0)
    {
      i = j;
      if (!TextUtils.isEmpty(str)) {
        i = this.w.setParameter("alsa_rate", str);
      }
    }
    str = v().d("alsa_save");
    j = i;
    if (i == 0)
    {
      j = i;
      if (!TextUtils.isEmpty(str)) {
        j = this.w.setParameter("alsa_save", str);
      }
    }
    i = j;
    if (j == 0) {
      i = this.w.startListening();
    }
    if (i != 0)
    {
      ad.b("start aimic listening failed: " + i);
      throw new SpeechError(i);
    }
  }
  
  private boolean B()
  {
    return this.y <= this.x;
  }
  
  public int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = 22001;
    if (this.w != null) {
      i = this.w.writeAudio(paramArrayOfByte, paramInt1, paramInt2);
    }
    return i;
  }
  
  public void a(SpeechError paramSpeechError)
  {
    if (this.w != null)
    {
      this.w.unregisterListener(this);
      this.w.stopListening();
    }
    super.a(paramSpeechError);
  }
  
  protected void h()
    throws Exception
  {
    ad.a("start connecting");
    String str1 = v().e("engine_type");
    if (v().a("net_check", true))
    {
      if (!"cloud".equals(str1)) {
        break label88;
      }
      W.b(this.r);
    }
    int i;
    for (;;)
    {
      i = v().a("record_read_rate", 40);
      if (-3 != this.f) {
        break label188;
      }
      if (this.w != null) {
        break;
      }
      ad.b("create AIMIC failed!");
      throw new SpeechError(21003);
      label88:
      if (("mix".equals(str1)) || ("mixed".equals(str1))) {
        try
        {
          W.b(this.r);
        }
        catch (Exception localException)
        {
          v().a("engine_type", "local");
        }
      }
    }
    A();
    label188:
    while ((this.f == -1) || (!t()))
    {
      if ((this.a != null) && ((this.f > -1) || (-3 == this.f))) {
        this.a.onBeginOfSpeech();
      }
      this.o.a("app_ssb");
      a(1, B.a.a, false, 0);
      return;
    }
    ad.a("start  record");
    if (this.f == -2)
    {
      String str2 = v().e("asr_source_path");
      this.i = new a(s(), i, this.f, str2);
    }
    do
    {
      this.o.a("rec_open");
      this.i.startRecording(this);
      a(9, B.a.b, false, this.p);
      break;
      this.i = new PcmRecorder(s(), i, this.f);
    } while (!hasMessages(3));
    throw new SpeechError(10118);
  }
  
  public void l()
  {
    this.w.unregisterListener(this);
    super.l();
  }
  
  public void onError(int paramInt)
  {
    onError(new SpeechError(paramInt));
  }
  
  public void onRecogAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Object paramObject)
  {
    if (B())
    {
      l();
      return;
    }
    this.x += paramInt1;
    onRecordBuffer(paramArrayOfByte, 0, paramInt1);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */