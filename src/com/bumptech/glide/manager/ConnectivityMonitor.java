package com.bumptech.glide.manager;

public abstract interface ConnectivityMonitor
  extends LifecycleListener
{
  public static abstract interface ConnectivityListener
  {
    public abstract void onConnectivityChanged(boolean paramBoolean);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\manager\ConnectivityMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */