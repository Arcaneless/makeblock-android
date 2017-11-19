package cc.makeblock.makeblock.scene.playground.rj25;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import cc.makeblock.makeblock.base.PlaygroundActivity;
import cc.makeblock.makeblock.databinding.ActivityRangerFollowLineBinding;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.viewmodel.playground.rj25.ranger.RangerFollowLineViewModel;

public class FollowLineActivity
  extends PlaygroundActivity<RangerFollowLineViewModel>
{
  private ActivityRangerFollowLineBinding binding;
  
  protected RangerFollowLineViewModel createViewModel()
  {
    return new RangerFollowLineViewModel((Ranger)this.device);
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.binding = ((ActivityRangerFollowLineBinding)DataBindingUtil.setContentView(this, 2131427386));
    this.binding.setViewModel((RangerFollowLineViewModel)this.viewModel);
  }
  
  protected void onStop()
  {
    super.onStop();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\rj25\FollowLineActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */