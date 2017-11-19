package cc.makeblock.makeblock.scene.playground.airblock;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout.LayoutParams;
import cc.makeblock.makeblock.customview.UserGuideCoverView;
import cc.makeblock.makeblock.customview.dialog.GuideDialog;
import cc.makeblock.makeblock.customview.util.TouchRegion;
import cc.makeblock.makeblock.databinding.ActivityAirblockDronePlaygroundBinding;
import cc.makeblock.makeblock.databinding.LayoutPlaygroundAirblockTopBarBinding;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.viewmodel.playground.airblock.AirBlockDroneViewModel;

public class AirBlockDronePlaygroundActivity
  extends AirBlockActivity<AirBlockDroneViewModel>
{
  private ActivityAirblockDronePlaygroundBinding binding;
  
  private void checkUserGuide()
  {
    if (!SharedPreferencesUtils.getHasShowAirblockDroneGuide())
    {
      showUserGuide();
      SharedPreferencesUtils.setHasShowAirblockDroneGuide(true);
    }
  }
  
  protected AirBlockDroneViewModel createViewModel()
  {
    return new AirBlockDroneViewModel(this, (AirBlock)this.device);
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.binding = ((ActivityAirblockDronePlaygroundBinding)DataBindingUtil.setContentView(this, 2131427357));
    ((AirBlockDroneViewModel)this.viewModel).initSlideMenu();
    this.binding.setViewModel((AirBlockDroneViewModel)this.viewModel);
    checkUserGuide();
    new TouchRegion(this.binding.toolBar.back).expandViewTouchRegion(this.binding.toolBar.back, 50).expandViewTouchRegion(this.binding.toolBar.menu, 50);
  }
  
  protected void onDestroy()
  {
    ((AirBlockDroneViewModel)this.viewModel).onDestroy();
    super.onDestroy();
  }
  
  protected void onStart()
  {
    super.onStart();
    if (this.viewModel != null) {
      ((AirBlockDroneViewModel)this.viewModel).onStart();
    }
  }
  
  protected void onStop()
  {
    if (this.viewModel != null) {
      ((AirBlockDroneViewModel)this.viewModel).onStop();
    }
    super.onStop();
  }
  
  public void openVideoTutorial(String paramString)
  {
    ActivityJumpUtil.jumpToGroupActivity(this, paramString);
  }
  
  public void showGuideDialog()
  {
    new GuideDialog(this, new int[] { 2131427405, 2131427406, 2131427407, 2131427412 }).show();
  }
  
  public void showUserGuide()
  {
    UserGuideCoverView localUserGuideCoverView = new UserGuideCoverView(this);
    localUserGuideCoverView.setLayoutIds(new int[] { 2131427470, 2131427471, 2131427472, 2131427473 });
    addContentView(localUserGuideCoverView, new RelativeLayout.LayoutParams(-1, -1));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\airblock\AirBlockDronePlaygroundActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */