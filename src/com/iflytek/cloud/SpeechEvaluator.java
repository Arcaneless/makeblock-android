package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.thirdparty.E;
import com.iflytek.cloud.thirdparty.ad;
import com.iflytek.cloud.thirdparty.an;
import com.iflytek.msc.MSC;

public class SpeechEvaluator
  extends E
{
  private static SpeechEvaluator a = null;
  private an c = null;
  
  protected SpeechEvaluator(Context paramContext, InitListener paramInitListener)
  {
    if (MSC.isLoaded()) {
      this.c = new an(paramContext);
    }
    if (paramInitListener != null) {}
  }
  
  public static SpeechEvaluator createEvaluator(Context paramContext, InitListener paramInitListener)
  {
    if (a == null) {
      a = new SpeechEvaluator(paramContext, null);
    }
    return a;
  }
  
  public static SpeechEvaluator getEvaluator()
  {
    return a;
  }
  
  public void cancel()
  {
    if ((this.c != null) && (this.c.g())) {
      this.c.cancel(false);
    }
  }
  
  public boolean destroy()
  {
    boolean bool = true;
    if (this.c != null) {
      bool = this.c.destroy();
    }
    if (bool) {
      a = null;
    }
    return bool;
  }
  
  public String getParameter(String paramString)
  {
    return super.getParameter(paramString);
  }
  
  public boolean isEvaluating()
  {
    return (this.c != null) && (this.c.g());
  }
  
  public boolean setParameter(String paramString1, String paramString2)
  {
    return super.setParameter(paramString1, paramString2);
  }
  
  public int startEvaluating(String paramString1, String paramString2, EvaluatorListener paramEvaluatorListener)
  {
    if (this.c == null) {
      return 21001;
    }
    this.c.setParameter(this.b);
    return this.c.a(paramString1, paramString2, paramEvaluatorListener);
  }
  
  public int startEvaluating(byte[] paramArrayOfByte, String paramString, EvaluatorListener paramEvaluatorListener)
  {
    if (this.c == null) {
      return 21001;
    }
    this.c.setParameter(this.b);
    return this.c.a(paramArrayOfByte, paramString, paramEvaluatorListener);
  }
  
  public void stopEvaluating()
  {
    if ((this.c != null) && (this.c.g()))
    {
      this.c.e();
      return;
    }
    ad.b("SpeechEvaluator stopEvaluating failed, is not running");
  }
  
  public boolean writeAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((this.c != null) && (this.c.g())) {
      return this.c.a(paramArrayOfByte, paramInt1, paramInt2);
    }
    ad.b("SpeechEvaluator writeAudio failed, is not running");
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\SpeechEvaluator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */