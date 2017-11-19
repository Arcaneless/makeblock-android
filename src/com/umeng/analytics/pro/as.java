package com.umeng.analytics.pro;

import com.umeng.analytics.AnalyticsConfig;

public class as
  implements Thread.UncaughtExceptionHandler
{
  private Thread.UncaughtExceptionHandler a;
  private ay b;
  
  public as()
  {
    if (Thread.getDefaultUncaughtExceptionHandler() == this) {
      return;
    }
    this.a = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
  }
  
  private void a(Throwable paramThrowable)
  {
    if (AnalyticsConfig.CATCH_EXCEPTION)
    {
      this.b.a(paramThrowable);
      return;
    }
    this.b.a(null);
  }
  
  public void a(ay paramay)
  {
    this.b = paramay;
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    a(paramThrowable);
    if ((this.a != null) && (this.a != Thread.getDefaultUncaughtExceptionHandler())) {
      this.a.uncaughtException(paramThread, paramThrowable);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\as.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */