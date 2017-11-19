package cc.makeblock.makeblock.viewmodel.firmware;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import cc.makeblock.makeblock.customview.dialog.WarningDialog;
import cc.makeblock.makeblock.customview.dialog.WarningDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.device.AirBlock.OnUpdateFirmwareListener;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import com.gzsll.jsbridge.WVJBWebView;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FirmwareUpdateActivityViewModel
  extends BaseObservable
  implements AirBlock.OnUpdateFirmwareListener
{
  private Context context;
  private WVJBWebView firmwareHelperWebview;
  private boolean firmwareIsUnrecognizable = false;
  private float updatingProgress;
  
  public FirmwareUpdateActivityViewModel(Context paramContext, WVJBWebView paramWVJBWebView, boolean paramBoolean)
  {
    this.context = paramContext;
    this.firmwareHelperWebview = paramWVJBWebView;
    this.firmwareIsUnrecognizable = paramBoolean;
  }
  
  private void jumpToInitiatingDeviceActivity()
  {
    ActivityJumpUtil.jumpToInitiatingActivity((Activity)this.context, 1, true);
  }
  
  private void showSuccessDialog()
  {
    WarningDialog localWarningDialog = new WarningDialog(this.context);
    localWarningDialog.setTitleText(TextUtil.getStringById(2131493229));
    localWarningDialog.setOnOperationConfirmedListener(new WarningDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        FirmwareUpdateActivityViewModel.this.jumpToInitiatingDeviceActivity();
      }
    });
    if (!((Activity)this.context).isFinishing()) {
      localWarningDialog.show();
    }
  }
  
  @Bindable
  public float getUpdatingProgressFloat()
  {
    return this.updatingProgress;
  }
  
  @Bindable
  public String getUpdatingProgressText()
  {
    if (this.updatingProgress > 1.0F) {
      this.updatingProgress = 1.0F;
    }
    if (this.updatingProgress < 0.0F) {
      this.updatingProgress = 0.0F;
    }
    float f = new BigDecimal(this.updatingProgress).setScale(4, 4).floatValue();
    DecimalFormat localDecimalFormat = new DecimalFormat("0.00");
    return localDecimalFormat.format(f * 100.0F) + "%";
  }
  
  public void onCreate()
  {
    if (!ControllerManager.getInstance().isConnectedOk()) {
      onFail();
    }
    AirBlock localAirBlock = (AirBlock)ControllerManager.getInstance().getCurrentDevice();
    localAirBlock.setOnUpdateFirmwareListener(this);
    localAirBlock.updateFirmware();
  }
  
  public void onFail()
  {
    StatisticsTool.getInstance().onEvent("UpdateAirBlockFail", "Airblock更新固件失败");
    BleManager.getInstance().stopProgram();
    WarningDialog localWarningDialog = new WarningDialog(this.context);
    localWarningDialog.setTitleText(TextUtil.getStringById(2131493227));
    localWarningDialog.setOnOperationConfirmedListener(new WarningDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        if (FirmwareUpdateActivityViewModel.this.firmwareIsUnrecognizable)
        {
          ((Activity)FirmwareUpdateActivityViewModel.this.context).finish();
          return;
        }
        FirmwareUpdateActivityViewModel.this.jumpToInitiatingDeviceActivity();
      }
    });
    if (!((Activity)this.context).isFinishing()) {
      localWarningDialog.show();
    }
  }
  
  public void onProgressChange(float paramFloat)
  {
    this.updatingProgress = paramFloat;
    notifyPropertyChanged(76);
    notifyPropertyChanged(77);
  }
  
  public void onSuccess()
  {
    StatisticsTool.getInstance().onEvent("UpdateAirBlockSuccess", "Airblock更新固件成功");
    BleManager.getInstance().stopProgram();
    this.updatingProgress = 1.0F;
    notifyPropertyChanged(76);
    notifyPropertyChanged(77);
    showSuccessDialog();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\firmware\FirmwareUpdateActivityViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */