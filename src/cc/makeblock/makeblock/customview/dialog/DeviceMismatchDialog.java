package cc.makeblock.makeblock.customview.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.utils.TextUtil;

public class DeviceMismatchDialog
  extends WarningDialog
{
  public DeviceMismatchDialog(@NonNull Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  private void init()
  {
    setTitleText(TextUtil.getStringById(2131493312));
    setOnOperationConfirmedListener(new WarningDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        BleManager.getInstance().disconnectBluetooth();
        DeviceMismatchDialog.this.dismiss();
      }
    });
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\dialog\DeviceMismatchDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */