package cc.makeblock.makeblock.scene.playground.rj25;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import cc.makeblock.makeblock.base.PlaygroundActivity;
import cc.makeblock.makeblock.databinding.ActivityRangerAvoidBinding;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.viewmodel.playground.rj25.ranger.RangerAvoidViewModel;

public class AvoidActivity
  extends PlaygroundActivity<RangerAvoidViewModel>
{
  protected RangerAvoidViewModel createViewModel()
  {
    return new RangerAvoidViewModel((Ranger)this.device);
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ((ActivityRangerAvoidBinding)DataBindingUtil.setContentView(this, 2131427383)).setViewModel((RangerAvoidViewModel)this.viewModel);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\rj25\AvoidActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */