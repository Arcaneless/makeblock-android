package cc.makeblock.makeblock.scene.playground.rj25;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout.LayoutParams;
import cc.makeblock.makeblock.base.PlaygroundActivity;
import cc.makeblock.makeblock.customview.UserGuideCoverView;
import cc.makeblock.makeblock.databinding.ActivityMbotDrawPathBinding;
import cc.makeblock.makeblock.databinding.ActivityRangerDrawPathBinding;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.viewmodel.DeviceViewModel;
import cc.makeblock.makeblock.viewmodel.playground.rj25.mbot.MBotDrawPathViewModel;
import cc.makeblock.makeblock.viewmodel.playground.rj25.ranger.RangerDrawPathViewModel;

public class DrawPathActivity
  extends PlaygroundActivity<DeviceViewModel>
{
  private void checkGuide()
  {
    if (!SharedPreferencesUtils.getHasShowDrawAndRunGuide(ControllerManager.getInstance().getChosenBoardName()))
    {
      UserGuideCoverView localUserGuideCoverView = new UserGuideCoverView(this);
      localUserGuideCoverView.setLayoutIds(new int[] { 2131427479 });
      addContentView(localUserGuideCoverView, new RelativeLayout.LayoutParams(-1, -1));
      SharedPreferencesUtils.setHasShowDrawAndRunGuide(ControllerManager.getInstance().getChosenBoardName());
    }
  }
  
  protected DeviceViewModel createViewModel()
  {
    if ((this.device instanceof MBot)) {
      return new MBotDrawPathViewModel((MBot)this.device);
    }
    return new RangerDrawPathViewModel((Ranger)this.device);
  }
  
  public void finish()
  {
    super.finish();
    if ((this.viewModel instanceof MBotDrawPathViewModel))
    {
      ((MBotDrawPathViewModel)this.viewModel).cancelAllActions();
      return;
    }
    ((RangerDrawPathViewModel)this.viewModel).cancelAllActions();
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = ControllerManager.getInstance().getCurrentDevice();
    if ((paramBundle instanceof MBot)) {
      ((ActivityMbotDrawPathBinding)DataBindingUtil.setContentView(this, 2131427378)).setViewModel((MBotDrawPathViewModel)this.viewModel);
    }
    for (;;)
    {
      checkGuide();
      return;
      if ((paramBundle instanceof Ranger)) {
        ((ActivityRangerDrawPathBinding)DataBindingUtil.setContentView(this, 2131427385)).setViewModel((RangerDrawPathViewModel)this.viewModel);
      }
    }
  }
  
  protected void onStop()
  {
    super.onStop();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\rj25\DrawPathActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */