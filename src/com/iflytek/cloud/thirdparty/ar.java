package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.TextUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;
import java.io.UnsupportedEncodingException;

public class ar
  extends F
{
  public ar(Context paramContext)
  {
    super(paramContext);
  }
  
  public int a(String paramString, TextUnderstanderListener paramTextUnderstanderListener)
  {
    try
    {
      if (TextUtils.isEmpty(getParameter("asr_sch"))) {
        setParameter("asr_sch", "1");
      }
      if (TextUtils.isEmpty(getParameter("nlp_version"))) {
        setParameter("nlp_version", "2.0");
      }
      if (TextUtils.isEmpty(getParameter("result_type"))) {
        setParameter("result_type", "json");
      }
      if (d()) {
        return 21005;
      }
      this.d = new H(this.a, this.b, a("textunderstand"));
      paramTextUnderstanderListener = new a(paramTextUnderstanderListener);
      ((H)this.d).a(new F.a(this, paramTextUnderstanderListener), paramString);
      return 0;
    }
    catch (SpeechError paramString)
    {
      int i = paramString.getErrorCode();
      ad.a(paramString);
      return i;
    }
    catch (Throwable paramString)
    {
      ad.a(paramString);
    }
    return 20999;
  }
  
  public void cancel(boolean paramBoolean)
  {
    super.cancel(paramBoolean);
  }
  
  public boolean destroy()
  {
    return super.destroy();
  }
  
  public boolean e()
  {
    return d();
  }
  
  public String getParameter(String paramString)
  {
    return super.getParameter(paramString);
  }
  
  public boolean setParameter(String paramString1, String paramString2)
  {
    return super.setParameter(paramString1, paramString2);
  }
  
  private class a
    implements SpeechListener
  {
    private TextUnderstanderListener b;
    
    public a(TextUnderstanderListener paramTextUnderstanderListener)
    {
      this.b = paramTextUnderstanderListener;
    }
    
    public void onBufferReceived(byte[] paramArrayOfByte)
    {
      if (paramArrayOfByte != null) {}
      try
      {
        paramArrayOfByte = new UnderstanderResult(new String(paramArrayOfByte, "utf-8"));
        this.b.onResult(paramArrayOfByte);
        return;
      }
      catch (NullPointerException paramArrayOfByte)
      {
        ad.a(paramArrayOfByte);
        return;
      }
      catch (UnsupportedEncodingException paramArrayOfByte)
      {
        ad.a(paramArrayOfByte);
      }
    }
    
    public void onCompleted(SpeechError paramSpeechError)
    {
      if ((this.b != null) && (paramSpeechError != null)) {
        this.b.onError(paramSpeechError);
      }
    }
    
    public void onEvent(int paramInt, Bundle paramBundle) {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\ar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */