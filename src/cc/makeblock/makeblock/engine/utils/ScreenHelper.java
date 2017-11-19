package cc.makeblock.makeblock.engine.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class ScreenHelper
{
  public static final float DEFAULT_SCREEN_HEIGHT = 1080.0F;
  public static final float DEFAULT_SCREEN_WIDTH = 1920.0F;
  public static int SCREEN_HEIGHT = 0;
  public static int SCREEN_WIDTH = 0;
  private static final String TAG = "ScreenHelper";
  
  public static void getDeviceInfo(Activity paramActivity)
  {
    if ((SCREEN_WIDTH == 0) || (SCREEN_HEIGHT == 0) || (SCREEN_WIDTH < SCREEN_HEIGHT)) {
      initDeviceInfo(paramActivity);
    }
  }
  
  public static int getPercentHeightToPx(float paramFloat)
  {
    return (int)(SCREEN_HEIGHT * paramFloat);
  }
  
  public static int getPercentWidthToPx(float paramFloat)
  {
    return (int)(SCREEN_WIDTH * paramFloat);
  }
  
  private static void initDeviceInfo(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    SCREEN_WIDTH = Math.max(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
    SCREEN_HEIGHT = Math.min(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
    paramActivity = paramActivity.findViewById(16908290);
    paramActivity.post(new Runnable()
    {
      public void run()
      {
        ScreenHelper.SCREEN_WIDTH = this.val$content.getWidth();
        ScreenHelper.SCREEN_HEIGHT = this.val$content.getHeight();
      }
    });
  }
  
  public static void updateDeviceInfo(Activity paramActivity)
  {
    initDeviceInfo(paramActivity);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\ScreenHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */