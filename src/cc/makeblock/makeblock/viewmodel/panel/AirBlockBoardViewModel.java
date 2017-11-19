package cc.makeblock.makeblock.viewmodel.panel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import cc.makeblock.makeblock.base.ToastUtil;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import java.text.DecimalFormat;

public class AirBlockBoardViewModel
  extends BaseObservable
{
  public float battery = 0.0F;
  DecimalFormat decimalFormat = new DecimalFormat("0.#");
  public float height = 0.0F;
  private long showToastTime = 0L;
  public int signal = -100;
  public float speed = 0.0F;
  
  public AirBlockBoardViewModel()
  {
    notifyChange();
  }
  
  @Bindable
  public float getBatteryPercent()
  {
    return this.battery / 100.0F;
  }
  
  @Bindable
  public float getHeightDegrees()
  {
    return this.height / 10.0F * 262.0F;
  }
  
  @Bindable
  public float getHeightProgress()
  {
    return this.height / 10.0F;
  }
  
  @Bindable
  public String getHeightText()
  {
    return this.decimalFormat.format(this.height);
  }
  
  @Bindable
  public float getSignalPercent()
  {
    if (this.signal == -100) {
      return 0.0F;
    }
    if (this.signal > -60) {
      return 1.0F;
    }
    if (this.signal > -65) {
      return 0.8F;
    }
    if (this.signal > -70) {
      return 0.6F;
    }
    if (this.signal > -75) {
      return 0.4F;
    }
    return 0.15F;
  }
  
  @Bindable
  public String getSpeed()
  {
    return this.decimalFormat.format(this.speed);
  }
  
  public void updateAirBoardState(String paramString)
  {
    paramString = paramString.split(":");
    try
    {
      this.battery = Float.valueOf(paramString[0]).floatValue();
      if (this.battery < 25.0F)
      {
        long l = System.currentTimeMillis();
        if ((this.showToastTime == 0L) || (l - this.showToastTime > 5000L))
        {
          this.showToastTime = l;
          ToastUtil.showToast(2131493091);
        }
      }
      this.speed = Float.valueOf(paramString[1].split(",")[2]).floatValue();
      if (Math.abs(this.speed) < 0.2D) {
        this.speed = 0.0F;
      }
      this.height = Float.valueOf(paramString[2].split(",")[0]).floatValue();
      this.signal = BleManager.getInstance().getBluetoothRssi();
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    notifyPropertyChanged(0);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\panel\AirBlockBoardViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */