package cc.makeblock.makeblock.unity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import cc.makeblock.makeblock.engine.ControllerManager;
import com.unity3d.player.UnityPlayer;

public class UnityPlayerActivity
  extends Activity
{
  protected UnityPlayer mUnityPlayer;
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 2) {
      return this.mUnityPlayer.injectEvent(paramKeyEvent);
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  public void finish()
  {
    super.finish();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.mUnityPlayer.configurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    getWindow().setFormat(2);
    this.mUnityPlayer = new UnityPlayer(this);
    setContentView(this.mUnityPlayer);
    this.mUnityPlayer.requestFocus();
    paramBundle = ControllerManager.getInstance().getChosenBoardName();
    switch (cc.makeblock.makeblock.engine.device.DeviceBoardName.valueOf(paramBundle))
    {
    }
    for (paramBundle = "Ranger";; paramBundle = "mBot")
    {
      UnityPlayer.UnitySendMessage("ManagerOfManagers", "PassDeviceParam", paramBundle);
      return;
    }
  }
  
  protected void onDestroy()
  {
    this.mUnityPlayer.quit();
    super.onDestroy();
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return this.mUnityPlayer.injectEvent(paramMotionEvent);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return this.mUnityPlayer.injectEvent(paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    return this.mUnityPlayer.injectEvent(paramKeyEvent);
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    this.mUnityPlayer.lowMemory();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    setIntent(paramIntent);
  }
  
  protected void onPause()
  {
    super.onPause();
    this.mUnityPlayer.pause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.mUnityPlayer.resume();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.mUnityPlayer.injectEvent(paramMotionEvent);
  }
  
  public void onTrimMemory(int paramInt)
  {
    super.onTrimMemory(paramInt);
    if (paramInt == 15) {
      this.mUnityPlayer.lowMemory();
    }
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    this.mUnityPlayer.windowFocusChanged(paramBoolean);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\unity\UnityPlayerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */