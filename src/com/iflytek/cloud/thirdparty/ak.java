package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.RequestListener;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import org.json.JSONObject;

public class ak
  extends E
{
  V.a a = new V.a()
  {
    public void a(SpeechError paramAnonymousSpeechError)
    {
      if (paramAnonymousSpeechError != null) {
        ad.b("upload error. please check net state:" + paramAnonymousSpeechError.getErrorCode());
      }
      for (;;)
      {
        if (ak.a(ak.this) != null) {
          ak.a(ak.this).onCompleted(paramAnonymousSpeechError);
        }
        return;
        ad.a("upload succeed");
      }
    }
    
    public void a(V paramAnonymousV, byte[] paramAnonymousArrayOfByte)
    {
      if (paramAnonymousArrayOfByte != null) {
        try
        {
          paramAnonymousV = new String(paramAnonymousArrayOfByte, "utf-8");
          ad.a(paramAnonymousV);
          int i = Integer.parseInt(new JSONObject(paramAnonymousV).getString("ret"));
          if (i != 0)
          {
            a(new SpeechError(i, "wfr"));
            return;
          }
          if (ak.a(ak.this) != null)
          {
            ae.a("GetNotifyResult", null);
            ak.a(ak.this).onBufferReceived(paramAnonymousArrayOfByte);
          }
          a(null);
          return;
        }
        catch (Exception paramAnonymousV)
        {
          a(new SpeechError(20004));
        }
      }
    }
  };
  private String c = "http://openapi.openspeech.cn/webapi/wfr.do";
  private String d = "pver=1.0";
  private Context e = null;
  private V f = null;
  private RequestListener g;
  
  public ak(Context paramContext, ag paramag)
  {
    this.b = paramag;
    this.e = paramContext;
    this.f = new V();
  }
  
  public int a(byte[] paramArrayOfByte, RequestListener paramRequestListener)
  {
    try
    {
      this.g = paramRequestListener;
      if (SpeechUtility.getUtility() == null) {
        return 10111;
      }
      String str = this.b.d("server_url");
      paramRequestListener = str;
      if (TextUtils.isEmpty(str)) {
        paramRequestListener = this.c;
      }
      str = ai.c(this.e, this.b);
      this.f.b(this.b.a("timeout", 20000));
      this.f.a(1);
      this.f.a(paramRequestListener, this.d, paramArrayOfByte, str);
      this.f.a(this.a);
      ae.a("LastDataFlag", null);
      return 0;
    }
    catch (Exception paramArrayOfByte) {}
    return 20999;
  }
  
  public void a()
  {
    this.f.a();
    this.f = null;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */