package cc.makeblock.makeblock.viewmodel.customview.dialog;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;
import cc.makeblock.makeblock.customview.dialog.AirblockTuneDialog;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.device.common.Device;

public class AirblockTuneDialogViewModel
  extends BaseObservable
{
  private Device device;
  private AirblockTuneDialog dialog;
  private Handler handler;
  private boolean hasTuned = false;
  private boolean isTuning = false;
  
  public AirblockTuneDialogViewModel(AirblockTuneDialog paramAirblockTuneDialog)
  {
    this.dialog = paramAirblockTuneDialog;
    this.handler = new Handler();
    this.device = ControllerManager.getInstance().getCurrentDevice();
  }
  
  public void dismissDialog()
  {
    this.dialog.dismiss();
  }
  
  @Bindable
  public boolean getButtonVisibility()
  {
    return (!this.hasTuned) && (!this.isTuning);
  }
  
  @Bindable
  public boolean getFinishTextVisibility()
  {
    return this.hasTuned;
  }
  
  public int getGif()
  {
    return 2131165396;
  }
  
  @Bindable
  public boolean getLoadingGifVisibility()
  {
    return this.isTuning;
  }
  
  public void startTuning()
  {
    if ((this.device instanceof AirBlock))
    {
      ((AirBlock)this.device).boardCalibrate();
      this.isTuning = true;
      notifyChange();
      this.handler.postDelayed(new Runnable()
      {
        public void run()
        {
          ((AirBlock)AirblockTuneDialogViewModel.this.device).gyroscopeCalibrate();
        }
      }, 1000L);
      this.handler.postDelayed(new Runnable()
      {
        public void run()
        {
          AirblockTuneDialogViewModel.access$102(AirblockTuneDialogViewModel.this, false);
          AirblockTuneDialogViewModel.access$202(AirblockTuneDialogViewModel.this, true);
          AirblockTuneDialogViewModel.this.notifyChange();
        }
      }, 2000L);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\customview\dialog\AirblockTuneDialogViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */