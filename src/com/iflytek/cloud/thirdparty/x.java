package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.WakeuperListener;
import com.iflytek.cloud.WakeuperResult;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.record.PcmRecorder.PcmRecordListener;
import com.iflytek.msc.MSC;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.json.JSONObject;

public class x
  extends B
  implements PcmRecorder.PcmRecordListener
{
  public String a;
  protected volatile WakeuperListener b = null;
  protected boolean c = false;
  protected int d = 1;
  protected w e = new w();
  protected PcmRecorder f = null;
  protected ArrayList<String> g = null;
  public boolean h = false;
  public boolean i = false;
  private int j = 0;
  
  public x(Context paramContext, ag paramag, HandlerThread paramHandlerThread)
  {
    super(paramContext, paramHandlerThread);
    a(paramag);
    this.g = new ArrayList();
  }
  
  private void a(boolean paramBoolean, byte[] paramArrayOfByte, int paramInt, Bundle paramBundle)
    throws SpeechError, UnsupportedEncodingException
  {
    int k = 0;
    if (paramInt == 1) {
      if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0))
      {
        paramArrayOfByte = new String(paramArrayOfByte, r());
        this.g.add(paramArrayOfByte);
        if ((this.b != null) && (t()))
        {
          paramBundle = new Bundle();
          paramBundle.putParcelable("rec_result", new RecognizerResult(paramArrayOfByte));
          paramArrayOfByte = this.b;
          if (!paramBoolean) {
            break label199;
          }
          paramInt = 1;
          label88:
          paramArrayOfByte.onEvent(22001, paramInt, 0, paramBundle);
        }
        ad.a("msc result time:" + System.currentTimeMillis());
      }
    }
    label199:
    String str;
    do
    {
      if (paramBoolean) {
        b(null);
      }
      return;
      if (this.g.size() <= 0)
      {
        paramArrayOfByte = v().e("local_grammar");
        if ((!TextUtils.isEmpty(paramArrayOfByte)) && (!"sms.irf".equals(paramArrayOfByte))) {
          throw new SpeechError(20005);
        }
        throw new SpeechError(10118);
      }
      paramArrayOfByte = "";
      break;
      paramInt = 0;
      break label88;
      if (paramInt != 0) {
        break label297;
      }
      if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0)) {
        break label286;
      }
      str = new String(paramArrayOfByte, "utf-8");
    } while ((this.b == null) || (!t()));
    if (paramBundle != null) {
      paramArrayOfByte = paramBundle.getByteArray("ivw_audio");
    }
    for (;;)
    {
      paramArrayOfByte = new WakeuperResult(str, paramArrayOfByte);
      ae.a("GetNotifyResult", null);
      this.b.onResult(paramArrayOfByte);
      break;
      label286:
      throw new SpeechError(10118);
      label297:
      if (paramInt != 2) {
        break;
      }
      if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0))
      {
        paramArrayOfByte = new String(paramArrayOfByte, "utf-8");
        paramInt = k;
        if (this.a.equals("enroll")) {
          if (this.b != null)
          {
            paramBundle = new WakeuperResult(paramArrayOfByte);
            this.b.onResult(paramBundle);
          }
        }
      }
      try
      {
        paramBundle = new JSONObject(paramArrayOfByte);
        if (paramBundle.getInt("suc_times") != paramBundle.getInt("total_times")) {
          a(0);
        }
        for (paramInt = k; (this.b != null) && (t()); paramInt = 1)
        {
          paramArrayOfByte = new WakeuperResult(paramArrayOfByte);
          this.b.onResult(paramArrayOfByte);
          if (paramInt == 0) {
            break;
          }
          b(null);
          break;
          throw new SpeechError(10118);
        }
        paramArrayOfByte = null;
      }
      catch (Exception paramArrayOfByte)
      {
        throw new SpeechError(10118);
      }
    }
  }
  
  private void k()
    throws SpeechError, IOException, InterruptedException
  {
    ad.a("recording stop");
    if (!this.a.equals("enroll")) {
      l();
    }
    this.e.a();
  }
  
  private void l()
  {
    if (this.f != null)
    {
      this.f.stopRecord(v().a("record_force_stop", false));
      this.f = null;
    }
  }
  
  int MsgProcCallBack(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null) {
      ad.a("MscWakeuper", "msg:" + paramInt1 + "param1:" + paramInt2 + "param2:" + paramInt3 + "result:" + new String(paramArrayOfByte));
    }
    for (;;)
    {
      try
      {
        paramArrayOfChar = a.values()[paramInt1];
        if (paramArrayOfChar != null) {}
        switch (1.a[paramArrayOfChar.ordinal()])
        {
        default: 
          return 0;
          ad.a("MscWakeuper", "msg:" + paramInt1 + "param1:" + paramInt2 + "param2:" + paramInt3 + "result:null");
          continue;
        }
      }
      catch (Throwable paramArrayOfChar)
      {
        ad.b("unmatched ivw message!");
        ad.a(paramArrayOfChar);
        paramArrayOfChar = null;
        continue;
        this.h = true;
        if ((this.i) || (this.a.equals("oneshot")))
        {
          paramArrayOfChar = obtainMessage(4, 0, 0, paramArrayOfByte);
          if (hasMessages(4))
          {
            a(paramArrayOfChar, B.a.b, false, 0);
            return 0;
          }
        }
        else
        {
          paramArrayOfChar = obtainMessage(4, 5, 0, paramArrayOfByte);
          continue;
        }
        a(paramArrayOfChar, B.a.a, false, 0);
        return 0;
      }
      onError(new SpeechError(paramInt2));
      return 0;
      paramArrayOfChar = obtainMessage(4, paramInt2, 1, paramArrayOfByte);
      if (hasMessages(4))
      {
        a(paramArrayOfChar, B.a.b, false, 0);
        return 0;
      }
      a(paramArrayOfChar, B.a.a, false, 0);
      return 0;
      if (paramInt2 == 3)
      {
        i();
        return 0;
        try
        {
          if (this.b != null)
          {
            this.b.onVolumeChanged(paramInt2);
            return 0;
          }
        }
        catch (Exception paramArrayOfChar)
        {
          return 0;
        }
      }
    }
    a(obtainMessage(4, 0, 2, paramArrayOfByte), B.a.a, false, 0);
    return 0;
  }
  
  public int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    onRecordBuffer(paramArrayOfByte, paramInt1, paramInt2);
    return 0;
  }
  
  protected void a()
    throws SpeechError, Throwable
  {
    int k;
    if (this.e.a == null)
    {
      ae.a("SDKSessionBegin", null);
      k = this.e.a(this.r, this.a, this);
      if ((k != 0) || (this.e.a == null)) {
        break label78;
      }
      if (t())
      {
        MSC.QIVWRegisterNotify(this.e.a, "MsgProcCallBack", this);
        a(B.b.c);
      }
    }
    label78:
    do
    {
      return;
      if (k == 0) {
        break;
      }
      this.j += 1;
      if (this.j > 40) {
        throw new SpeechError(k);
      }
    } while (!t());
    Thread.sleep(15L);
    a(1, B.a.a, false, 0);
    return;
    ad.b("current csid: " + this.e.b);
    throw new SpeechError(20999);
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
    default: 
      return;
    case 0: 
      b();
      return;
    case 1: 
      a();
      return;
    case 2: 
      b(paramMessage);
      return;
    case 3: 
      k();
      return;
    }
    c(paramMessage);
  }
  
  protected void a(SpeechError paramSpeechError)
  {
    ad.a("onSessionEnd");
    l();
    SpeechError localSpeechError = paramSpeechError;
    if (this.a.equals("oneshot"))
    {
      localSpeechError = paramSpeechError;
      if (this.h)
      {
        localSpeechError = paramSpeechError;
        if (this.g.size() <= 0)
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
      }
    }
    ae.a("SessionEndBegin", null);
    if (this.s)
    {
      this.e.a("user abort");
      ae.a("SessionEndEnd", null);
      super.a(localSpeechError);
      if (this.b != null)
      {
        if (!this.s) {
          break label191;
        }
        ad.a("WakeuperListener#onCancel");
      }
    }
    for (;;)
    {
      this.b = null;
      return;
      if (localSpeechError != null)
      {
        this.e.a("error" + localSpeechError.getErrorCode());
        break;
      }
      this.e.a("success");
      break;
      label191:
      ad.a("WakeuperListener#onEnd");
      if (localSpeechError != null) {
        this.b.onError(localSpeechError);
      }
    }
  }
  
  public void a(WakeuperListener paramWakeuperListener)
  {
    try
    {
      this.b = paramWakeuperListener;
      ad.a("startListening called");
      a_();
      return;
    }
    finally
    {
      paramWakeuperListener = finally;
      throw paramWakeuperListener;
    }
  }
  
  protected void a(byte[] paramArrayOfByte, boolean paramBoolean)
    throws SpeechError
  {
    this.e.a(paramArrayOfByte, paramArrayOfByte.length);
  }
  
  public boolean a(boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        ad.a("stopListening, current status is :" + u() + " usercancel : " + paramBoolean);
        if (this.a.equals("enroll"))
        {
          this.c = paramBoolean;
          a(3);
          return true;
        }
        if ((this.a.equals("oneshot")) && (this.h))
        {
          l();
          this.c = paramBoolean;
          a(3);
        }
        else
        {
          b(false);
        }
      }
      finally {}
    }
  }
  
  protected void b()
    throws Exception
  {
    ad.a("start connecting");
    this.h = false;
    int k = v().a("record_read_rate", 40);
    if ((this.d != -1) && (t()))
    {
      ad.a("start  record");
      if (this.f == null)
      {
        this.f = new PcmRecorder(s(), k, this.d);
        this.f.startRecording(this);
      }
    }
    if ((u() != B.b.e) && (this.b != null)) {
      this.b.onBeginOfSpeech();
    }
    a(1, B.a.a, false, 0);
  }
  
  protected void b(Message paramMessage)
    throws Throwable
  {
    paramMessage = (byte[])paramMessage.obj;
    if ((paramMessage == null) || (paramMessage.length == 0)) {
      return;
    }
    a(paramMessage, true);
  }
  
  public void b(boolean paramBoolean)
  {
    if ((paramBoolean) && (t()) && (this.b != null)) {
      this.b.onError(new SpeechError(20017));
    }
    ad.a("cancel");
    l();
    if (u() == B.b.c) {
      this.c = true;
    }
    super.b(paramBoolean);
  }
  
  protected void c()
  {
    this.a = v().b("sst", "wakeup");
    this.i = v().a("keep_alive", false);
    this.d = v().a("audio_source", 1);
    int k = N.a(this.r).b("ivw_netval", 20);
    v().a("ivw_netval", k + "");
    super.c();
  }
  
  void c(Message paramMessage)
    throws SpeechError, InterruptedException, UnsupportedEncodingException
  {
    int k = paramMessage.arg1;
    byte[] arrayOfByte = (byte[])paramMessage.obj;
    switch (k)
    {
    case 1: 
    case 3: 
    case 4: 
    default: 
      return;
    case 0: 
      a(false, arrayOfByte, paramMessage.arg2, paramMessage.getData());
      return;
    case 2: 
      throw new SpeechError(20010);
    }
    a(true, arrayOfByte, paramMessage.arg2, paramMessage.getData());
  }
  
  public String e()
  {
    return this.e.g();
  }
  
  public String f()
  {
    return null;
  }
  
  public String g()
  {
    return "ivw";
  }
  
  public int h()
  {
    return this.d;
  }
  
  public void i()
  {
    if (B.b.c == u()) {
      a(false);
    }
  }
  
  public WakeuperListener j()
  {
    return this.b;
  }
  
  public boolean m()
  {
    return false;
  }
  
  public void onError(SpeechError paramSpeechError)
  {
    b(paramSpeechError);
  }
  
  public void onRecordBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramArrayOfByte.length < paramInt2) || (paramArrayOfByte == null) || (paramInt2 <= 0)) {}
    while ((paramInt2 <= 0) || (!t())) {
      return;
    }
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
    d(obtainMessage(2, arrayOfByte));
  }
  
  public void onRecordReleased() {}
  
  public void onRecordStarted(boolean paramBoolean) {}
  
  public static enum a
  {
    private a() {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\x.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */