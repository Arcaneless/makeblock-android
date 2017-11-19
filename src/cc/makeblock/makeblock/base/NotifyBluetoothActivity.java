package cc.makeblock.makeblock.base;

import android.os.Bundle;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationCanceledListener;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnConnectedStateChangeListener;
import cc.makeblock.makeblock.engine.utils.TextUtil;

public class NotifyBluetoothActivity
  extends BaseActivity
{
  private ConfirmDialog dialog;
  private boolean isVisible = false;
  private ControllerManager.OnConnectedStateChangeListener onConnectedStateChangeListener = new ControllerManager.OnConnectedStateChangeListener()
  {
    public void onConnectedStateChange(boolean paramAnonymousBoolean)
    {
      NotifyBluetoothActivity.this.onConnectedStateChanged(paramAnonymousBoolean);
    }
  };
  
  protected boolean isAutoShowConnectDialog()
  {
    return !ControllerManager.getInstance().isConnectedOk();
  }
  
  protected void onConnectedStateChanged(boolean paramBoolean)
  {
    if ((!paramBoolean) && (this.isVisible)) {
      showConnectDialog();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ControllerManager.getInstance().registerConnectingStateListener(this.onConnectedStateChangeListener);
    onConnectedStateChanged(ControllerManager.getInstance().isConnectedOk());
    if (isAutoShowConnectDialog()) {
      showConnectDialog();
    }
  }
  
  protected void onDestroy()
  {
    if ((this.dialog != null) && (this.dialog.isShowing())) {
      this.dialog.dismiss();
    }
    ControllerManager.getInstance().unRegisterConnectingStateListener(this.onConnectedStateChangeListener);
    super.onDestroy();
  }
  
  public void onPause()
  {
    this.isVisible = false;
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    this.isVisible = true;
  }
  
  public void showConnectDialog()
  {
    if (ControllerManager.getInstance().isConnectedOk()) {
      return;
    }
    this.dialog = new ConfirmDialog(this);
    this.dialog.setTitleText(TextUtil.getStringById(2131493192));
    this.dialog.setMessage(TextUtil.getStringById(2131493193));
    this.dialog.setCancelText(TextUtil.getStringById(2131493278));
    this.dialog.setConfirmText(TextUtil.getStringById(2131493008));
    this.dialog.setOnOperationConfirmedListener(new ConfirmDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        ActivityJumpUtil.jumpToConnectActivity(NotifyBluetoothActivity.this, -1);
      }
    });
    this.dialog.setOnOperationCanceledListener(new ConfirmDialog.OnOperationCanceledListener()
    {
      public void onOperationCancel()
      {
        NotifyBluetoothActivity.this.dialog.hide();
      }
    });
    this.dialog.show();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\base\NotifyBluetoothActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */