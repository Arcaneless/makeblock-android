package com.bumptech.glide.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

public class DefaultConnectivityMonitorFactory
  implements ConnectivityMonitorFactory
{
  private static final String NETWORK_PERMISSION = "android.permission.ACCESS_NETWORK_STATE";
  
  @NonNull
  public ConnectivityMonitor build(@NonNull Context paramContext, @NonNull ConnectivityMonitor.ConnectivityListener paramConnectivityListener)
  {
    if (ContextCompat.checkSelfPermission(paramContext, "android.permission.ACCESS_NETWORK_STATE") == 0) {}
    for (int i = 1; i != 0; i = 0) {
      return new DefaultConnectivityMonitor(paramContext, paramConnectivityListener);
    }
    return new NullConnectivityMonitor();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\manager\DefaultConnectivityMonitorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */