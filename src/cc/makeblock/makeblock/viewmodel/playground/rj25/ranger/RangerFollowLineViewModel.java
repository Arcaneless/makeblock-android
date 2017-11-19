package cc.makeblock.makeblock.viewmodel.playground.rj25.ranger;

import android.databinding.Bindable;
import cc.makeblock.makeblock.engine.device.Ranger;

public class RangerFollowLineViewModel
  extends RangerViewModel
{
  private boolean isRunning = false;
  
  public RangerFollowLineViewModel(Ranger paramRanger)
  {
    super(paramRanger);
    paramRanger.setMode(0);
    setRunning(false);
  }
  
  private void setRunning(boolean paramBoolean)
  {
    this.isRunning = paramBoolean;
    notifyPropertyChanged(66);
  }
  
  @Bindable
  public boolean isShowPlay()
  {
    return !this.isRunning;
  }
  
  public void play()
  {
    ((Ranger)this.device).setMode(2);
    setRunning(true);
  }
  
  public void stop()
  {
    ((Ranger)this.device).setMode(0);
    setRunning(false);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\rj25\ranger\RangerFollowLineViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */