package cc.makeblock.makeblock.scene.connect;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.percent.PercentLayoutHelper.PercentLayoutInfo;
import android.support.percent.PercentLayoutHelper.PercentLayoutParams;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import cc.makeblock.makeblock.base.BaseActivity;
import cc.makeblock.makeblock.databinding.ActivityConnectBinding;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.viewmodel.connect.ConnectActivityViewModel;

public class ConnectActivity
  extends BaseActivity
{
  protected static final int REQUEST_DEVICE_INITIATING = 3;
  protected static final int REQUEST_ENABLE_BT = 2;
  private ObjectAnimator alphaAnimator = null;
  private ActivityConnectBinding binding;
  private ConnectStateEnum connectStateEnum = null;
  private Handler handler = new Handler();
  private ObjectAnimator scaleXAnimator = null;
  private ObjectAnimator scaleYAnimator = null;
  private Runnable startDiscoveryRunnable = new Runnable()
  {
    public void run()
    {
      ConnectActivity.this.viewModel.resume();
    }
  };
  private ConnectActivityViewModel viewModel;
  private ObjectAnimator yAnimator = null;
  
  private void cancelAnimator(Animator paramAnimator)
  {
    if (paramAnimator != null) {
      paramAnimator.cancel();
    }
  }
  
  private void cancelClosingAnimators()
  {
    cancelAnimator(this.yAnimator);
    cancelAnimator(this.scaleXAnimator);
    cancelAnimator(this.scaleYAnimator);
    cancelAnimator(this.alphaAnimator);
    this.binding.handPhone.setVisibility(8);
  }
  
  private void hideConnectingLayout()
  {
    cancelClosingAnimators();
  }
  
  private void initHandPhoneAnimator(ImageView paramImageView)
  {
    ScreenHelper.updateDeviceInfo(this);
    ViewGroup.LayoutParams localLayoutParams = paramImageView.getLayoutParams();
    float f = 0.0F;
    if ((localLayoutParams instanceof PercentLayoutHelper.PercentLayoutParams)) {
      f = ((PercentLayoutHelper.PercentLayoutParams)localLayoutParams).getPercentLayoutInfo().heightPercent;
    }
    this.yAnimator = ObjectAnimator.ofFloat(paramImageView, "y", new float[] { ScreenHelper.getPercentHeightToPx(1.0F + f), ScreenHelper.getPercentHeightToPx(1.0F - f * 0.72F) });
    this.scaleXAnimator = ObjectAnimator.ofFloat(paramImageView, "scaleX", new float[] { 1.5F, 0.72F });
    this.scaleYAnimator = ObjectAnimator.ofFloat(paramImageView, "scaleY", new float[] { 1.5F, 0.72F });
    this.alphaAnimator = ObjectAnimator.ofFloat(paramImageView, "alpha", new float[] { 1.0F, 0.0F });
    this.yAnimator.setDuration(1000L + 2000L);
    this.scaleXAnimator.setDuration(1000L + 2000L);
    this.scaleYAnimator.setDuration(1000L + 2000L);
    this.alphaAnimator.setDuration(1000L + 2000L);
    this.yAnimator.setRepeatCount(-1);
    this.scaleXAnimator.setRepeatCount(-1);
    this.scaleYAnimator.setRepeatCount(-1);
    this.alphaAnimator.setRepeatCount(-1);
    this.alphaAnimator.setRepeatCount(-1);
    paramImageView = new IntervalAccelerateDecelerateInterpolator((float)1000L * 1.0F / (float)(1000L + 2000L));
    this.yAnimator.setInterpolator(paramImageView);
    this.scaleXAnimator.setInterpolator(paramImageView);
    this.scaleYAnimator.setInterpolator(paramImageView);
    this.alphaAnimator.setInterpolator(new AlphaIntervalInterpolator((float)2000L * 1.0F / (float)(1000L + 2000L)));
  }
  
  private void startClosingAnimator()
  {
    this.binding.handPhone.setVisibility(0);
    this.yAnimator.start();
    this.scaleXAnimator.start();
    this.scaleYAnimator.start();
    this.alphaAnimator.start();
  }
  
  public void finish()
  {
    finishExitFlow();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default: 
      return;
    case 2: 
      switch (paramInt2)
      {
      }
      return;
    }
    switch (paramInt2)
    {
    default: 
      return;
    case -1: 
      this.viewModel.leave();
      return;
    }
    this.viewModel.setConnectingState(ConnectStateEnum.BLUETOOTH_ENABLE);
    this.viewModel.resume();
  }
  
  public void onBackPressed()
  {
    this.viewModel.leave();
  }
  
  public void onBluetoothConnected()
  {
    ActivityJumpUtil.onBluetoothConnected(this, 3);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.binding = ((ActivityConnectBinding)DataBindingUtil.setContentView(this, 2131427362));
    initHandPhoneAnimator(this.binding.handPhone);
    this.viewModel = new ConnectActivityViewModel(this);
    this.viewModel.initSlideMenu();
    this.binding.setViewModel(this.viewModel);
  }
  
  protected void onDestroy()
  {
    hideConnectingLayout();
    cancelClosingAnimators();
    super.onDestroy();
  }
  
  public void onPause()
  {
    this.viewModel.stopDiscovery();
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    this.startDiscoveryRunnable.run();
  }
  
  protected void onStart()
  {
    super.onStart();
    this.viewModel.reset();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void postResume(int paramInt)
  {
    if (this.viewModel != null)
    {
      this.handler.removeCallbacks(this.startDiscoveryRunnable);
      this.handler.postDelayed(this.startDiscoveryRunnable, paramInt);
    }
  }
  
  public boolean requestEnableBluetooth()
  {
    Intent localIntent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
    try
    {
      localComponentName = localIntent.resolveActivity(getPackageManager());
    }
    catch (Exception localException)
    {
      ComponentName localComponentName;
      do
      {
        break;
      } while (localComponentName != null);
    }
    startActivityForResult(localIntent, 2);
    return true;
    return false;
  }
  
  public void setState(ConnectStateEnum paramConnectStateEnum)
  {
    if ((this.connectStateEnum != null) && (this.connectStateEnum == paramConnectStateEnum)) {
      return;
    }
    switch (paramConnectStateEnum)
    {
    case ???: 
    case ???: 
    case ???: 
    case ???: 
    default: 
      return;
    case ???: 
      cancelClosingAnimators();
      return;
    case ???: 
      startClosingAnimator();
      return;
    case ???: 
      cancelClosingAnimators();
      return;
    }
    cancelClosingAnimators();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\connect\ConnectActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */