package cc.makeblock.makeblock.scene.connect;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import cc.makeblock.makeblock.base.BaseActivity;
import cc.makeblock.makeblock.databinding.ActivityInitiatingDeviceBinding;
import cc.makeblock.makeblock.viewmodel.connect.InitiatingActivityViewModel;

public class InitiatingDeviceActivity
  extends BaseActivity
{
  public static final String FIRMWARE_UPDATE_INIT = "FIRMWARE_UPDATE_INIT";
  public static final int JUMP_UPDATE = 2;
  public static final int JUMP_WEB = 1;
  private final Runnable checkConnectionRunnable = new Runnable()
  {
    public void run()
    {
      InitiatingDeviceActivity.this.postCheckConnection(500L);
      InitiatingDeviceActivity.this.viewModel.checkConnection();
    }
  };
  private Handler handler = new Handler();
  private final Runnable overTimeRunnable = new Runnable()
  {
    public void run()
    {
      InitiatingDeviceActivity.this.viewModel.fail();
    }
  };
  private InitiatingActivityViewModel viewModel;
  
  private void postCheckConnection(long paramLong)
  {
    this.handler.postDelayed(this.checkConnectionRunnable, paramLong);
  }
  
  private void postOvertime(long paramLong)
  {
    this.handler.postDelayed(this.overTimeRunnable, paramLong);
  }
  
  private void reset()
  {
    this.viewModel.onCreate();
    postCheckConnection(1000L);
    postOvertime(5000L);
  }
  
  public void finish()
  {
    removeCallbacks();
    super.finish();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default: 
      return;
    case 2: 
      reset();
      return;
    }
    finish();
  }
  
  public void onBackPressed()
  {
    this.viewModel.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = (ActivityInitiatingDeviceBinding)DataBindingUtil.setContentView(this, 2131427367);
    this.viewModel = new InitiatingActivityViewModel(this);
    paramBundle.setViewModel(this.viewModel);
    reset();
  }
  
  protected void onDestroy()
  {
    removeCallbacks();
    this.viewModel.onDestroy();
    super.onDestroy();
  }
  
  public void postFinish(long paramLong)
  {
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        InitiatingDeviceActivity.this.finish();
      }
    }, paramLong);
  }
  
  public void removeCallbacks()
  {
    this.handler.removeCallbacks(this.overTimeRunnable);
    this.handler.removeCallbacks(this.checkConnectionRunnable);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\connect\InitiatingDeviceActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */