package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

public class w
  extends C
{
  private MSCSessionInfo c = new MSCSessionInfo();
  private MSCSessionInfo d = new MSCSessionInfo();
  
  private void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SpeechError
  {
    try
    {
      paramInt2 = MSC.QIVWAudioWrite(this.a, paramArrayOfByte, paramInt1, paramInt2, this.d);
      this.c.sesstatus = this.d.sesstatus;
      ad.a("QIVWAudioWrite length:" + paramInt1);
      if (paramInt2 != 0) {
        throw new SpeechError(this.d.errorcode);
      }
    }
    finally {}
  }
  
  public int a(Context paramContext, String paramString, B paramB)
    throws SpeechError, UnsupportedEncodingException
  {
    paramContext = ai.b(paramContext, paramString, paramB);
    paramString = paramB.v().e("cloud_grammar");
    long l = SystemClock.elapsedRealtime();
    ae.a("MSCSessionBegin", null);
    if (TextUtils.isEmpty(paramString)) {}
    int i;
    for (this.a = MSC.QIVWSessionBegin(null, paramContext.getBytes(paramB.p()), this.c);; this.a = MSC.QIVWSessionBegin(paramString.getBytes(paramB.p()), paramContext.getBytes(paramB.p()), this.c))
    {
      ae.a("SessionBeginEnd", null);
      ad.a("sessionBegin ErrCode:" + this.c.errorcode + " time:" + (SystemClock.elapsedRealtime() - l));
      i = this.c.errorcode;
      if ((i == 0) || (i == 10129) || (i == 10113) || (i == 10132)) {
        break;
      }
      throw new SpeechError(i);
    }
    return i;
  }
  
  public void a()
    throws SpeechError
  {
    try
    {
      ae.a("LastDataFlag", null);
      a(new byte[0], 0, 4);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void a(String paramString)
  {
    if (this.a == null) {
      return;
    }
    ad.a("sessionEnd enter ");
    long l = System.currentTimeMillis();
    if (MSC.QIVWSessionEnd(this.a, paramString.getBytes()) == 0) {}
    for (boolean bool = true;; bool = false)
    {
      ad.a("sessionEnd leavel:" + bool + " time:" + (System.currentTimeMillis() - l));
      this.a = null;
      this.b = null;
      return;
    }
  }
  
  public void a(byte[] paramArrayOfByte, int paramInt)
    throws SpeechError
  {
    try
    {
      a(paramArrayOfByte, paramInt, 2);
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */