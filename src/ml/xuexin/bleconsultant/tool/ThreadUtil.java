package ml.xuexin.bleconsultant.tool;

import android.os.Looper;

public class ThreadUtil
{
  public static boolean isMainThread()
  {
    return Thread.currentThread() == Looper.getMainLooper().getThread();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\tool\ThreadUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */