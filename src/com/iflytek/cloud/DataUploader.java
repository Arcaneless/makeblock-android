package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.thirdparty.F;
import com.iflytek.cloud.thirdparty.F.a;
import com.iflytek.cloud.thirdparty.H;
import com.iflytek.cloud.thirdparty.ad;

public class DataUploader
  extends F
{
  public DataUploader(Context paramContext)
  {
    super(paramContext);
  }
  
  protected boolean b_()
  {
    return true;
  }
  
  public int uploadData(SpeechListener paramSpeechListener, String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      this.d = new H(this.a, this.b, a("upload"));
      ((H)this.d).a(new F.a(this, paramSpeechListener), paramString, paramArrayOfByte);
      return 0;
    }
    catch (SpeechError paramSpeechListener)
    {
      int i = paramSpeechListener.getErrorCode();
      ad.a(paramSpeechListener);
      return i;
    }
    catch (Throwable paramSpeechListener)
    {
      ad.a(paramSpeechListener);
    }
    return 20999;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\DataUploader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */