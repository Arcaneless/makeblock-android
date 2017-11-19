package cc.makeblock.makeblock.scene.playground.rj25;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import cc.makeblock.makeblock.base.PlaygroundActivity;
import cc.makeblock.makeblock.databinding.ActivityMbotControllerBinding;
import cc.makeblock.makeblock.databinding.ActivityRangerControllerBinding;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.viewmodel.DeviceViewModel;
import cc.makeblock.makeblock.viewmodel.playground.rj25.mbot.MBotControllerViewModel;
import cc.makeblock.makeblock.viewmodel.playground.rj25.ranger.RangerControllerViewModel;

public class ControllerActivity
  extends PlaygroundActivity<DeviceViewModel>
{
  protected DeviceViewModel createViewModel()
  {
    if ((this.device instanceof MBot)) {
      return new MBotControllerViewModel((MBot)this.device);
    }
    return new RangerControllerViewModel((Ranger)this.device);
  }
  
  public void finish()
  {
    super.finish();
    if ((this.viewModel instanceof MBotControllerViewModel))
    {
      ((MBotControllerViewModel)this.viewModel).resetMBot();
      return;
    }
    ((RangerControllerViewModel)this.viewModel).resetRanger();
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = ControllerManager.getInstance().getCurrentDevice();
    if ((paramBundle instanceof MBot)) {
      ((ActivityMbotControllerBinding)DataBindingUtil.setContentView(this, 2131427377)).setViewModel((MBotControllerViewModel)this.viewModel);
    }
    while (!(paramBundle instanceof Ranger)) {
      return;
    }
    ((ActivityRangerControllerBinding)DataBindingUtil.setContentView(this, 2131427384)).setViewModel((RangerControllerViewModel)this.viewModel);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\rj25\ControllerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */