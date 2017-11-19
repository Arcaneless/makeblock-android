package cc.makeblock.makeblock.viewmodel.choosedevice;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableFloat;
import android.graphics.drawable.Drawable;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.TextUtil;

public class ChooseDeviceItemViewModel
  extends BaseObservable
{
  public ObservableFloat alpha = new ObservableFloat(1.0F);
  private String mBoardName;
  private ChooseDeviceItem mDeviceItem;
  private Drawable mShadeDrawable;
  
  public ChooseDeviceItemViewModel(ChooseDeviceItem paramChooseDeviceItem, String paramString, Drawable paramDrawable)
  {
    this.mDeviceItem = paramChooseDeviceItem;
    this.mBoardName = paramString;
    this.mShadeDrawable = paramDrawable;
  }
  
  private void returnToCP(Activity paramActivity)
  {
    StatisticsTool.getInstance().onEvent("SwitchRobotTo" + this.mDeviceItem.boardName, "切换机器人到" + this.mDeviceItem.boardName);
    Intent localIntent = new Intent();
    localIntent.putExtra("boardName", this.mDeviceItem.boardName);
    paramActivity.setResult(-1, localIntent);
    paramActivity.finish();
    ControllerManager.getInstance().setChosenBoardName(this.mDeviceItem.boardName);
  }
  
  private void showChooseConflictDialog(final Activity paramActivity)
  {
    ConfirmDialog localConfirmDialog = new ConfirmDialog(paramActivity);
    localConfirmDialog.setTitleText(TextUtil.getStringById(2131493183));
    localConfirmDialog.setOnOperationConfirmedListener(new ConfirmDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        ControllerManager.getInstance().disconnect();
        ChooseDeviceItemViewModel.this.returnToCP(paramActivity);
      }
    });
    localConfirmDialog.show();
  }
  
  @Bindable
  public String getDescription()
  {
    return this.mDeviceItem.description;
  }
  
  @Bindable
  public int getDeviceIcon()
  {
    return this.mDeviceItem.deviceIcon;
  }
  
  @Bindable
  public String getDeviceName()
  {
    return this.mDeviceItem.deviceName;
  }
  
  @Bindable
  public int getDevicePic()
  {
    return this.mDeviceItem.devicePic;
  }
  
  public Drawable getShadeDrawable()
  {
    return this.mShadeDrawable;
  }
  
  public void onSelected(Activity paramActivity)
  {
    if (ControllerManager.getInstance().isConnectedOk())
    {
      if (this.mDeviceItem.boardName.equals(this.mBoardName))
      {
        returnToCP(paramActivity);
        return;
      }
      showChooseConflictDialog(paramActivity);
      return;
    }
    returnToCP(paramActivity);
  }
  
  public void setAlpha(float paramFloat)
  {
    if (paramFloat != this.alpha.get()) {
      this.alpha.set(paramFloat);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\choosedevice\ChooseDeviceItemViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */