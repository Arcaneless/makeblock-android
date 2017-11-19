package com.squareup.leakcanary;

import android.app.Application;
import android.content.Context;

public final class LeakCanary
{
  private LeakCanary()
  {
    throw new AssertionError();
  }
  
  public static RefWatcher install(Application paramApplication)
  {
    return RefWatcher.DISABLED;
  }
  
  public static boolean isInAnalyzerProcess(Context paramContext)
  {
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\squareup\leakcanary\LeakCanary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */