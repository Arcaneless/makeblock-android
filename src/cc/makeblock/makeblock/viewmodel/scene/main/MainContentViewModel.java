package cc.makeblock.makeblock.viewmodel.scene.main;

import android.app.Activity;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.device.Codey;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.viewmodel.DeviceViewModel;

public class MainContentViewModel
  extends DeviceViewModel<Device>
{
  public MainContentViewModel(Device paramDevice)
  {
    super(paramDevice);
  }
  
  public void build(Activity paramActivity)
  {
    ActivityJumpUtil.jumpToLoadingBuildingActivity(paramActivity);
  }
  
  public void create(Activity paramActivity)
  {
    if ((this.device instanceof Codey)) {
      return;
    }
    ActivityJumpUtil.jumpToCreateActivity(paramActivity);
  }
  
  public void expand(Activity paramActivity)
  {
    ActivityJumpUtil.jumpToPlayActivity(paramActivity, 3);
  }
  
  public boolean isCodey()
  {
    return this.device instanceof Codey;
  }
  
  public void play(Activity paramActivity)
  {
    ActivityJumpUtil.jumpToPlayActivity(paramActivity, 1);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\scene\main\MainContentViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */