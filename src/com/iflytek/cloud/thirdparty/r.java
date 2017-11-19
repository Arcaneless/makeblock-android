package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.record.a;
import com.iflytek.cloud.util.AudioDetector;
import com.iflytek.cloud.util.AudioDetector.DetectorResult;
import java.io.IOException;

public class r
  extends p
{
  private AudioDetector w = null;
  private byte[] x = null;
  
  public r(Context paramContext, ag paramag, HandlerThread paramHandlerThread)
  {
    super(paramContext, paramag, paramHandlerThread);
    if (paramag != null) {
      i = paramag.a("sample_rate", 16000);
    }
    int j = i * 300 * 2 / 1000;
    ad.a("MscRecognizerMeta last buffer len: " + j);
    this.x = new byte[j];
    this.w = AudioDetector.getDetector();
    if (this.w == null)
    {
      paramHandlerThread = new StringBuffer();
      paramHandlerThread.append("sample_rate").append("=").append(i);
      String str = paramag.b("vad_engine", "meta");
      paramHandlerThread.append(",").append("vad_engine").append("=").append(str);
      if (paramag == null) {
        break label205;
      }
    }
    label205:
    for (paramag = paramag.e("vad_res_path");; paramag = null)
    {
      if (!TextUtils.isEmpty(paramag)) {
        paramHandlerThread.append(",").append("vad_res_path").append("=").append(paramag);
      }
      this.w = AudioDetector.createDetector(paramContext, paramHandlerThread.toString());
      return;
    }
  }
  
  private void A()
  {
    this.o.a("app_ssb");
    a(1, B.a.a, false, 0);
    ad.a("begin session");
  }
  
  byte[] a(byte[] paramArrayOfByte)
    throws Exception
  {
    int m;
    int i;
    Object localObject;
    if (this.w != null)
    {
      int j = Math.min(32768, paramArrayOfByte.length);
      m = 0;
      i = 0;
      localObject = null;
      if (j <= 0) {
        break label321;
      }
      localObject = this.w.detect(paramArrayOfByte, i, j, false);
      if (((AudioDetector.DetectorResult)localObject).error != 0) {
        throw new SpeechError(((AudioDetector.DetectorResult)localObject).error);
      }
      int k;
      int n;
      if (3 == ((AudioDetector.DetectorResult)localObject).status)
      {
        j = Math.max(m, i - this.x.length);
        this.w.reset();
        k = j;
        n = 0;
      }
      do
      {
        i = j + n;
        j = Math.min(32768, paramArrayOfByte.length - i);
        m = k;
        break;
        n = j;
        k = m;
        j = i;
      } while (((AudioDetector.DetectorResult)localObject).status == 0);
    }
    label321:
    for (;;)
    {
      if ((localObject != null) && (3 != ((AudioDetector.DetectorResult)localObject).status) && (((AudioDetector.DetectorResult)localObject).status != 0))
      {
        i = m + i;
        localObject = new byte[paramArrayOfByte.length - i + this.x.length];
        if (this.x.length <= i)
        {
          System.arraycopy(paramArrayOfByte, i - this.x.length, localObject, 0, localObject.length);
          return (byte[])localObject;
        }
        System.arraycopy(this.x, i, localObject, 0, this.x.length - i);
        System.arraycopy(paramArrayOfByte, 0, localObject, this.x.length - i, paramArrayOfByte.length);
        return (byte[])localObject;
      }
      i = Math.min(this.x.length, paramArrayOfByte.length);
      System.arraycopy(this.x, i, this.x, 0, this.x.length - i);
      System.arraycopy(paramArrayOfByte, paramArrayOfByte.length - i, this.x, this.x.length - i, i);
      if (localObject != null) {
        a(paramArrayOfByte, ((AudioDetector.DetectorResult)localObject).volume);
      }
      return null;
      throw new SpeechError(22001);
    }
  }
  
  protected void b(Message paramMessage)
    throws Exception
  {
    byte[] arrayOfByte = (byte[])paramMessage.obj;
    if ((arrayOfByte != null) && (arrayOfByte.length > 0))
    {
      if (this.w == null) {
        break label74;
      }
      paramMessage = a(arrayOfByte);
      if (paramMessage != null)
      {
        this.w.reset();
        this.w = null;
        a(obtainMessage(2, paramMessage), B.a.a, false, 0);
        ad.a("detectAudioData find start and begin session");
        A();
      }
    }
    return;
    label74:
    super.b(paramMessage);
  }
  
  protected void h()
    throws Exception
  {
    ad.a("start connecting");
    String str = v().e("engine_type");
    int i;
    if (v().a("net_check", true))
    {
      if ("cloud".equals(str)) {
        W.b(this.r);
      }
    }
    else
    {
      i = v().a("record_read_rate", 40);
      if ((this.f != -1) && (t()))
      {
        ad.a("start  record");
        if (this.f != -2) {
          break label257;
        }
        str = v().e("asr_source_path");
        this.i = new a(s(), i, this.f, str);
      }
    }
    label257:
    do
    {
      this.o.a("rec_open");
      this.i.startRecording(this);
      this.p = v().a("speech_timeout", -1);
      if (-1 != this.p) {
        a(9, B.a.b, false, this.p);
      }
      if ((this.a != null) && (this.f > -1)) {
        this.a.onBeginOfSpeech();
      }
      if (this.w != null) {
        break label296;
      }
      throw new SpeechError(21003);
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
      this.i = new PcmRecorder(s(), i, this.f);
    } while (!hasMessages(3));
    throw new SpeechError(10118);
    label296:
    this.w.reset();
  }
  
  protected void j()
    throws SpeechError, IOException, InterruptedException
  {
    if (this.w != null)
    {
      this.w = null;
      A();
      a(3);
      return;
    }
    super.j();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */