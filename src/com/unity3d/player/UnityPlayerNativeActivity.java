package com.unity3d.player;

import android.os.Bundle;
import android.util.Log;

public class UnityPlayerNativeActivity
  extends UnityPlayerActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    Log.w("Unity", "UnityPlayerNativeActivity has been deprecated, please update your AndroidManifest to use UnityPlayerActivity instead");
    super.onCreate(paramBundle);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\unity3d\player\UnityPlayerNativeActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */