package cc.makeblock.makeblock.scene.playground.codey;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import cc.makeblock.makeblock.base.NotifyBluetoothActivity;
import cc.makeblock.makeblock.base.PlaygroundActivity;
import cc.makeblock.makeblock.databinding.FragmentCodeyProgramUpdateBinding;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnDeviceChangeListener;
import cc.makeblock.makeblock.engine.device.Codey;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.viewmodel.playground.rj25.codey.UpdateProgramViewModel;
import cc.makeblock.makeblock.viewmodel.playground.rj25.codey.UpdateProgramViewModel.ProgramUpdateView;

public class UpdateProgramDialog
  extends DialogFragment
  implements UpdateProgramViewModel.ProgramUpdateView, ControllerManager.OnDeviceChangeListener
{
  private static final String KEY_FILE_NAME = "file_name";
  private static final String KEY_VERSION = "version";
  private boolean isShowConnectDialog;
  private Codey mCodey;
  private String mFileName;
  private String mVersion;
  private UpdateProgramViewModel mViewModel;
  
  public static UpdateProgramDialog newInstance(String paramString1, String paramString2)
  {
    UpdateProgramDialog localUpdateProgramDialog = new UpdateProgramDialog();
    Bundle localBundle = new Bundle();
    localBundle.putString("file_name", paramString1);
    localBundle.putString("version", paramString2);
    localUpdateProgramDialog.setArguments(localBundle);
    return localUpdateProgramDialog;
  }
  
  public void hideDialog()
  {
    if ((getDialog() != null) && (getDialog().isShowing())) {
      getDialog().dismiss();
    }
    getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    getDialog().setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        return true;
      }
    });
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setStyle(0, 16973834);
    this.mCodey = ((Codey)ControllerManager.getInstance().getCurrentDevice());
    this.mFileName = getArguments().getString("file_name");
    this.mVersion = getArguments().getString("version");
    ControllerManager.getInstance().registerDeviceListener(this);
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    getDialog().getWindow().requestFeature(1);
    getDialog().getWindow().setWindowAnimations(2131558835);
    paramLayoutInflater = (FragmentCodeyProgramUpdateBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427422, paramViewGroup, false);
    this.mViewModel = new UpdateProgramViewModel(this.mCodey, this.mFileName, this.mVersion, this);
    paramLayoutInflater.setViewModel(this.mViewModel);
    return paramLayoutInflater.getRoot();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    ControllerManager.getInstance().unRegisterDeviceListener(this);
    this.mViewModel.onDestroy();
  }
  
  public void onDeviceChange(Device paramDevice)
  {
    if (!(paramDevice instanceof Codey))
    {
      hideDialog();
      return;
    }
    this.mViewModel.setDevice((Codey)paramDevice);
  }
  
  public void onResume()
  {
    super.onResume();
    this.mViewModel.onResume();
  }
  
  public void onStart()
  {
    super.onStart();
    if (!this.isShowConnectDialog)
    {
      showConnectDialog();
      this.isShowConnectDialog = true;
    }
  }
  
  public void onSuccess()
  {
    hideDialog();
  }
  
  public void showConnectDialog()
  {
    if (((getActivity() instanceof NotifyBluetoothActivity)) && (!this.mCodey.isConnected())) {
      ((PlaygroundActivity)getActivity()).showConnectDialog();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\codey\UpdateProgramDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */