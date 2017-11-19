package cc.makeblock.makeblock.scene.playground.rj25;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import cc.makeblock.makeblock.base.PlaygroundActivity;
import cc.makeblock.makeblock.databinding.ActivityMbotMusicBinding;
import cc.makeblock.makeblock.databinding.ActivityRangerMusicBinding;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.viewmodel.DeviceViewModel;
import cc.makeblock.makeblock.viewmodel.playground.rj25.mbot.MBotMusicViewModel;
import cc.makeblock.makeblock.viewmodel.playground.rj25.ranger.RangerMusicViewModel;

public class MusicActivity
  extends PlaygroundActivity<DeviceViewModel>
{
  protected DeviceViewModel createViewModel()
  {
    if ((this.device instanceof MBot)) {
      return new MBotMusicViewModel((MBot)this.device);
    }
    return new RangerMusicViewModel((Ranger)this.device);
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((this.device instanceof MBot)) {
      ((ActivityMbotMusicBinding)DataBindingUtil.setContentView(this, 2131427379)).setViewModel((MBotMusicViewModel)this.viewModel);
    }
    while (!(this.device instanceof Ranger)) {
      return;
    }
    ((ActivityRangerMusicBinding)DataBindingUtil.setContentView(this, 2131427388)).setViewModel((RangerMusicViewModel)this.viewModel);
  }
  
  protected void onStop()
  {
    super.onStop();
    if ((this.viewModel instanceof MBotMusicViewModel))
    {
      ((MBotMusicViewModel)this.viewModel).cancelAllActions();
      return;
    }
    ((RangerMusicViewModel)this.viewModel).cancelAllActions();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\rj25\MusicActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */