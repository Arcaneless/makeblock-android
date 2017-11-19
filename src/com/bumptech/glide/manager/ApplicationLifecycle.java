package com.bumptech.glide.manager;

class ApplicationLifecycle
  implements Lifecycle
{
  public void addListener(LifecycleListener paramLifecycleListener)
  {
    paramLifecycleListener.onStart();
  }
  
  public void removeListener(LifecycleListener paramLifecycleListener) {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\manager\ApplicationLifecycle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */