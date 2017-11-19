package cc.makeblock.makeblock.viewmodel.playground.rj25.codey;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Handler;
import android.os.Message;
import cc.makeblock.makeblock.engine.device.Codey;
import cc.makeblock.makeblock.engine.device.Codey.CodeyStatusAdapter;
import cc.makeblock.makeblock.engine.firmware.Programmer.OnProgressChangeListener;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import cc.makeblock.makeblock.viewmodel.DeviceViewModel;

public class UpdateProgramViewModel
  extends DeviceViewModel<Codey>
{
  private static final int OUT_TIME = 2000;
  private static final int OUT_TIME_WHAT = 100;
  public ObservableBoolean isFail = new ObservableBoolean(false);
  private boolean isInstalling;
  private boolean isUpdating;
  private Codey.CodeyStatusAdapter mCodeyStatusAdapter;
  private String mFileName;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      if (paramAnonymousMessage.what == 100) {
        UpdateProgramViewModel.this.installProgram(UpdateProgramViewModel.this.mFileName);
      }
    }
  };
  private String mVersion;
  private ProgramUpdateView mView;
  public ObservableField<Integer> updatePic = new ObservableField();
  public ObservableField<String> updateTip = new ObservableField();
  
  public UpdateProgramViewModel(Codey paramCodey, String paramString1, String paramString2, ProgramUpdateView paramProgramUpdateView)
  {
    super(paramCodey);
    this.mFileName = paramString1;
    this.mVersion = paramString2;
    this.mView = paramProgramUpdateView;
    setViewStatus(paramCodey.isConnected());
    checkUpdate(paramString1);
  }
  
  private void checkUpdate(final String paramString)
  {
    if (this.mCodeyStatusAdapter == null)
    {
      this.mCodeyStatusAdapter = new Codey.CodeyStatusAdapter()
      {
        public void onScriptVersionReceived(String paramAnonymousString)
        {
          super.onScriptVersionReceived(paramAnonymousString);
          UpdateProgramViewModel.this.mHandler.removeMessages(100);
          if (UpdateProgramViewModel.this.hasUpdate(UpdateProgramViewModel.this.mVersion, paramAnonymousString))
          {
            UpdateProgramViewModel.this.installProgram(paramString);
            return;
          }
          UpdateProgramViewModel.this.mView.hideDialog();
        }
      };
      ((Codey)this.device).registerCodeyStatusAdapter(this.mCodeyStatusAdapter);
    }
    ((Codey)this.device).queryScriptVersion();
    this.mHandler.sendEmptyMessageDelayed(100, 2000L);
  }
  
  private boolean hasUpdate(String paramString1, String paramString2)
  {
    paramString1 = paramString1.split("_");
    paramString2 = paramString2.split("_");
    paramString1 = stringArrayToIntArray(paramString1);
    paramString2 = stringArrayToIntArray(paramString2);
    if (paramString1[0] != paramString2[0]) {}
    while (paramString1[1] > paramString2[1]) {
      return true;
    }
    return false;
  }
  
  private void installProgram(String paramString)
  {
    if (this.isInstalling) {
      return;
    }
    this.isInstalling = true;
    ((Codey)this.device).installProgram(paramString, new Programmer.OnProgressChangeListener()
    {
      public void onFail()
      {
        UpdateProgramViewModel.this.setViewStatus(false);
      }
      
      public void onProgressChange(float paramAnonymousFloat) {}
      
      public void onSuccess()
      {
        UpdateProgramViewModel.this.mView.onSuccess();
        UpdateProgramViewModel.access$602(UpdateProgramViewModel.this, false);
      }
    });
  }
  
  private void setViewStatus(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.isFail.set(false);
      this.updateTip.set(TextUtil.getStringById(2131493003));
      this.updatePic.set(Integer.valueOf(2131165728));
    }
    for (;;)
    {
      this.isUpdating = paramBoolean;
      return;
      this.isFail.set(true);
      this.updateTip.set(TextUtil.getStringById(2131492997));
      this.updatePic.set(Integer.valueOf(2131165727));
    }
  }
  
  private int[] stringArrayToIntArray(String[] paramArrayOfString)
  {
    int j = paramArrayOfString.length;
    int[] arrayOfInt = new int[j];
    int i = 0;
    for (;;)
    {
      if (i < j) {
        try
        {
          arrayOfInt[i] = Integer.parseInt(paramArrayOfString[i]);
          i += 1;
        }
        catch (NumberFormatException paramArrayOfString)
        {
          paramArrayOfString.printStackTrace();
        }
      }
    }
    return arrayOfInt;
  }
  
  public void close()
  {
    this.mView.hideDialog();
  }
  
  public void onDestroy()
  {
    ((Codey)this.device).removeCodeyStatusAdapter(this.mCodeyStatusAdapter);
  }
  
  public void onResume()
  {
    if ((((Codey)this.device).isConnected()) && (!this.isUpdating))
    {
      this.isInstalling = false;
      checkUpdate(this.mFileName);
      setViewStatus(true);
    }
  }
  
  public void restartInstallProgram()
  {
    if (!((Codey)this.device).isConnected())
    {
      this.mView.showConnectDialog();
      return;
    }
    this.isInstalling = false;
    setViewStatus(true);
    installProgram(this.mFileName);
  }
  
  public static abstract interface ProgramUpdateView
  {
    public abstract void hideDialog();
    
    public abstract void onSuccess();
    
    public abstract void showConnectDialog();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\rj25\codey\UpdateProgramViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */