package com.iflytek.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.iflytek.cloud.thirdparty.aM;
import com.iflytek.cloud.thirdparty.aU;

final class b
  extends BroadcastReceiver
{
  b(LaunchService paramLaunchService) {}
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    aU.a(paramContext, "alarm onReceive");
    aM.a(paramContext);
    LaunchService.a(this.a);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\common\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */