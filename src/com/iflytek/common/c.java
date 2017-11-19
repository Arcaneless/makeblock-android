package com.iflytek.common;

import android.util.Log;
import com.iflytek.cloud.thirdparty.aR;

final class c
{
  static aR a;
  
  protected static aR a()
  {
    if (a != null) {
      return a;
    }
    try
    {
      aR localaR = (aR)Class.forName("com.iflytek.common.push.impl.PushImpl").newInstance();
      a = localaR;
      if (localaR != null)
      {
        localaR = a;
        return localaR;
      }
    }
    catch (Exception localException)
    {
      Log.e("PushFactory", "getPushInstance not found push instance.");
    }
    return null;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\common\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */