package cc.makeblock.makeblock.scene.playground.airblock;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout.LayoutParams;
import cc.makeblock.makeblock.customview.UserGuideCoverView;
import cc.makeblock.makeblock.customview.dialog.GuideDialog;
import cc.makeblock.makeblock.customview.util.TouchRegion;
import cc.makeblock.makeblock.databinding.ActivityAirblockLandPlaygroundBinding;
import cc.makeblock.makeblock.databinding.LayoutPlaygroundAirblockTopBarBinding;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.viewmodel.playground.airblock.AirBlockLandViewModel;

public class AirBlockLandPlaygroundActivity
  extends AirBlockActivity<AirBlockLandViewModel>
{
  private ActivityAirblockLandPlaygroundBinding binding;
  
  private void checkUserGuide()
  {
    if (!SharedPreferencesUtils.getHasShowAirblockCarGuide())
    {
      showUserGuide();
      SharedPreferencesUtils.setHasShowAirblockCarGuide(true);
    }
  }
  
  protected AirBlockLandViewModel createViewModel()
  {
    return new AirBlockLandViewModel(this, (AirBlock)this.device);
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.binding = ((ActivityAirblockLandPlaygroundBinding)DataBindingUtil.setContentView(this, 2131427358));
    ((AirBlockLandViewModel)this.viewModel).initSlideMenu();
    this.binding.setViewModel((AirBlockLandViewModel)this.viewModel);
    checkUserGuide();
    new TouchRegion(this.binding.toolBar.back).expandViewTouchRegion(this.binding.toolBar.back, 50).expandViewTouchRegion(this.binding.toolBar.menu, 50);
  }
  
  protected void onDestroy()
  {
    ((AirBlockLandViewModel)this.viewModel).onDestroy();
    super.onDestroy();
  }
  
  protected void onStart()
  {
    super.onStart();
    if (this.viewModel != null) {
      ((AirBlockLandViewModel)this.viewModel).onStart();
    }
  }
  
  protected void onStop()
  {
    if (this.viewModel != null) {
      ((AirBlockLandViewModel)this.viewModel).onStop();
    }
    super.onStop();
  }
  
  public void openVideoTutorial(String paramString)
  {
    ActivityJumpUtil.jumpToGroupActivity(this, paramString);
  }
  
  public void showGuideDialog()
  {
    new GuideDialog(this, new int[] { 2131427408, 2131427409, 2131427410, 2131427412 }).show();
  }
  
  public void showUserGuide()
  {
    UserGuideCoverView localUserGuideCoverView = new UserGuideCoverView(this);
    localUserGuideCoverView.setLayoutIds(new int[] { 2131427466, 2131427467, 2131427468, 2131427469 });
    addContentView(localUserGuideCoverView, new RelativeLayout.LayoutParams(-1, -1));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\airblock\AirBlockLandPlaygroundActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */