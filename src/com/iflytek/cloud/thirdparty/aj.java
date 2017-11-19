package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.util.VerifierUtil;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class aj
{
  private boolean a;
  private MSCSessionInfo b;
  
  public aj(Context paramContext, String paramString)
  {
    try
    {
      if (SpeechUtility.getUtility() == null) {
        throw new SpeechError(10111);
      }
    }
    catch (SpeechError paramContext)
    {
      ad.b(paramContext.getPlainDescription(true));
      return;
    }
    this.a = false;
    a(paramString);
  }
  
  private String a(byte[] paramArrayOfByte, MSCSessionInfo paramMSCSessionInfo)
  {
    JSONObject localJSONObject = new JSONObject();
    localObject1 = localJSONObject;
    localObject2 = localJSONObject;
    try
    {
      if (paramMSCSessionInfo.errorcode == 0)
      {
        localObject2 = localJSONObject;
        localObject1 = new JSONObject(new String(paramArrayOfByte));
      }
      localObject2 = localObject1;
      ((JSONObject)localObject1).put("ret", paramMSCSessionInfo.errorcode);
    }
    catch (JSONException paramArrayOfByte)
    {
      for (;;)
      {
        ad.b("face result add errorinfo exception");
        localObject1 = localObject2;
      }
    }
    return ((JSONObject)localObject1).toString();
  }
  
  private void a(String paramString)
  {
    if (MSC.isLoaded())
    {
      ad.a("MSC isLoaded：" + MSC.isLoaded());
      this.b = new MSCSessionInfo();
      ae.a("MSCSessionBegin", null);
      if (TextUtils.isEmpty(paramString)) {
        break label110;
      }
      MSC.QIFDInit(paramString.getBytes(), this.b);
    }
    for (;;)
    {
      ae.a("SessionBeginEnd", null);
      if (this.b.errorcode == 0) {
        break;
      }
      ad.b("QIFDINIT INIT FAIL, ERRORCODE:" + this.b.errorcode);
      return;
      label110:
      MSC.QIFDInit(null, this.b);
    }
    ad.a("QIFDINIT INIT SUCCESS");
  }
  
  public String a(Bitmap paramBitmap)
  {
    if ((paramBitmap != null) && (Bitmap.Config.ARGB_8888.equals(paramBitmap.getConfig()))) {
      return b(VerifierUtil.ARGB2Gray(paramBitmap));
    }
    ad.b("Method detectARGB:null parameter or not ARGB bitmap");
    return null;
  }
  
  public String a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramArrayOfByte == null) || (paramInt1 <= 0) || (paramInt2 <= 0) || (paramInt4 < 0) || (paramInt4 > 3))
    {
      ad.b("Method trackNV21:invalid parameters");
      return null;
    }
    this.b = new MSCSessionInfo();
    ae.a("LastDataFlag", null);
    paramArrayOfByte = MSC.QIFDMultitracker(paramArrayOfByte, paramArrayOfByte.length, paramInt1, paramInt2, paramInt3, paramInt4, this.b);
    ae.a("GetNotifyResult", null);
    return a(paramArrayOfByte, this.b);
  }
  
  public void a()
  {
    if (!this.a)
    {
      ad.a("QIFDFINIT");
      ae.a("SessionEndBegin", null);
      int i = MSC.QIFDFini();
      ad.a("MSC.QIFDFini result is " + i);
      ae.a("SessionEndEnd", null);
      this.a = true;
    }
  }
  
  public String b(Bitmap paramBitmap)
  {
    if ((paramBitmap == null) || (!Bitmap.Config.ALPHA_8.equals(paramBitmap.getConfig())))
    {
      ad.b("Method detectGray:null parameter or not gray bitmap");
      return null;
    }
    this.b = new MSCSessionInfo();
    ae.a("LastDataFlag", null);
    paramBitmap = MSC.QIFDFacedetect(paramBitmap, VerifierUtil.getBitmapsize(paramBitmap), this.b);
    ae.a("GetNotifyResult", null);
    return a(paramBitmap, this.b);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */