package cc.makeblock.makeblock.scene.firmware;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import cc.makeblock.makeblock.base.BaseActivity;
import cc.makeblock.makeblock.databinding.ActivityFirmwareUpdateBinding;
import cc.makeblock.makeblock.viewmodel.firmware.FirmwareUpdateActivityViewModel;

public class FirmwareUpdateActivity
  extends BaseActivity
{
  public static final String FIRMWARE_IS_UNRECOGNIZABLE = "FIRMWARE_IS_UNRECOGNIZABLE";
  public static final int REQUEST_CODE_FIRMWARE_UPDATE_RESULT = 1;
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1)
    {
      setResult(-1);
      finish();
    }
  }
  
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    boolean bool = getIntent().getBooleanExtra("FIRMWARE_IS_UNRECOGNIZABLE", false);
    paramBundle = (ActivityFirmwareUpdateBinding)DataBindingUtil.setContentView(this, 2131427365);
    FirmwareUpdateActivityViewModel localFirmwareUpdateActivityViewModel = new FirmwareUpdateActivityViewModel(this, paramBundle.firmwareHelperWebview, bool);
    paramBundle.setViewModel(localFirmwareUpdateActivityViewModel);
    localFirmwareUpdateActivityViewModel.onCreate();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\firmware\FirmwareUpdateActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */