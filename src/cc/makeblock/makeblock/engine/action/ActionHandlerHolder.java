package cc.makeblock.makeblock.engine.action;

import android.os.Handler;
import android.os.HandlerThread;

public class ActionHandlerHolder
{
  private static Handler handler;
  
  public static Handler getHandler()
  {
    if (handler == null) {
      throw new RuntimeException("初始化还未完成");
    }
    return handler;
  }
  
  public static void initActionHandler()
  {
    HandlerThread localHandlerThread = new HandlerThread("ActionHandlerThread");
    localHandlerThread.start();
    handler = new Handler(localHandlerThread.getLooper());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\action\ActionHandlerHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */