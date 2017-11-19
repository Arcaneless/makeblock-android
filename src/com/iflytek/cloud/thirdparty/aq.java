package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;

public class aq
{
  protected static aq a = null;
  private ao b = null;
  
  public aq(Context paramContext)
  {
    this.b = new ao(paramContext);
  }
  
  public int a(SpeechUnderstanderListener paramSpeechUnderstanderListener)
  {
    paramSpeechUnderstanderListener = new a(paramSpeechUnderstanderListener);
    if (TextUtils.isEmpty(this.b.getParameter("asr_sch"))) {
      this.b.setParameter("asr_sch", "1");
    }
    if (TextUtils.isEmpty(this.b.getParameter("nlp_version"))) {
      this.b.setParameter("nlp_version", "2.0");
    }
    if (TextUtils.isEmpty(this.b.getParameter("result_type"))) {
      this.b.setParameter("result_type", "json");
    }
    this.b.a(paramSpeechUnderstanderListener);
    return 0;
  }
  
  public int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return this.b.a(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public void a(boolean paramBoolean)
  {
    this.b.cancel(paramBoolean);
  }
  
  public boolean a()
  {
    return this.b.g();
  }
  
  public boolean a(ag paramag)
  {
    return this.b.setParameter(paramag);
  }
  
  public void b()
  {
    this.b.e();
  }
  
  public boolean c()
  {
    boolean bool = this.b.destroy();
    if (bool) {
      a = null;
    }
    return bool;
  }
  
  private class a
    implements RecognizerListener
  {
    private final SpeechUnderstanderListener b;
    
    public a(SpeechUnderstanderListener paramSpeechUnderstanderListener)
    {
      this.b = paramSpeechUnderstanderListener;
    }
    
    public void onBeginOfSpeech()
    {
      if (this.b != null) {
        this.b.onBeginOfSpeech();
      }
    }
    
    public void onEndOfSpeech()
    {
      if (this.b != null) {
        this.b.onEndOfSpeech();
      }
    }
    
    public void onError(SpeechError paramSpeechError)
    {
      if ((this.b != null) && (paramSpeechError != null)) {
        this.b.onError(paramSpeechError);
      }
    }
    
    public void onEvent(int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle)
    {
      if (this.b != null) {
        this.b.onEvent(paramInt1, paramInt2, paramInt3, paramBundle);
      }
    }
    
    public void onResult(RecognizerResult paramRecognizerResult, boolean paramBoolean)
    {
      if (this.b != null) {
        this.b.onResult(new UnderstanderResult(paramRecognizerResult.getResultString()));
      }
    }
    
    public void onVolumeChanged(int paramInt, byte[] paramArrayOfByte)
    {
      if (this.b != null) {
        this.b.onVolumeChanged(paramInt, paramArrayOfByte);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\aq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */