package cc.makeblock.makeblock.scene.playground.codey;

import android.os.Bundle;
import cc.makeblock.makeblock.base.PlaygroundActivity;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationCanceledListener;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import cc.makeblock.makeblock.viewmodel.playground.rj25.codey.CodeyOffLineUpdateViewModel;

public abstract class CodeyOfflinePlaygroundActivity<T extends CodeyOffLineUpdateViewModel>
  extends PlaygroundActivity<T>
{
  private boolean isShowRepeatConnectDialog;
  private boolean isShowUpdateDialog;
  private ConfirmDialog reconnectDialog;
  
  protected void onConnectedStateChanged(boolean paramBoolean)
  {
    super.onConnectedStateChanged(paramBoolean);
    this.isShowUpdateDialog = paramBoolean;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.isShowUpdateDialog = this.device.isConnected();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if ((this.reconnectDialog != null) && (this.reconnectDialog.isShowing())) {
      this.reconnectDialog.dismiss();
    }
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.isShowUpdateDialog)
    {
      this.isShowUpdateDialog = false;
      ((CodeyOffLineUpdateViewModel)this.viewModel).showProgramUpdateDialog();
    }
  }
  
  public void showConnectDialog()
  {
    if (!this.isShowRepeatConnectDialog) {
      super.showConnectDialog();
    }
  }
  
  public void showReconnectDialog()
  {
    this.isShowRepeatConnectDialog = true;
    this.reconnectDialog = new ConfirmDialog(this);
    this.reconnectDialog.setTitleText(TextUtil.getStringById(2131492999));
    this.reconnectDialog.setMessage(TextUtil.getStringById(2131493193));
    this.reconnectDialog.setCancelText(TextUtil.getStringById(2131493278));
    this.reconnectDialog.setConfirmText(TextUtil.getStringById(2131493008));
    this.reconnectDialog.setOnOperationConfirmedListener(new ConfirmDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        CodeyOfflinePlaygroundActivity.this.reconnectDialog.dismiss();
        CodeyOfflinePlaygroundActivity.access$102(CodeyOfflinePlaygroundActivity.this, false);
        ActivityJumpUtil.jumpToConnectActivity(CodeyOfflinePlaygroundActivity.this, -1);
      }
    });
    this.reconnectDialog.setOnOperationCanceledListener(new ConfirmDialog.OnOperationCanceledListener()
    {
      public void onOperationCancel()
      {
        CodeyOfflinePlaygroundActivity.this.reconnectDialog.dismiss();
        CodeyOfflinePlaygroundActivity.access$102(CodeyOfflinePlaygroundActivity.this, false);
      }
    });
    this.reconnectDialog.show();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\codey\CodeyOfflinePlaygroundActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */