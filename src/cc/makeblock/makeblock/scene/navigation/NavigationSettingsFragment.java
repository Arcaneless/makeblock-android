package cc.makeblock.makeblock.scene.navigation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.base.BaseFragment;
import cc.makeblock.makeblock.customview.MakeBlockToast;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.customview.dialog.UpdateHintDialog;
import cc.makeblock.makeblock.customview.dialog.UpdateHintDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.databinding.FragmentNavigationSettingsBinding;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.net.data.AndroidVerInfoData;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationSettingsViewModel;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationSettingsViewModel.NavigationSettingsView;

public class NavigationSettingsFragment
  extends BaseFragment
  implements NavigationSettingsViewModel.NavigationSettingsView
{
  private MakeBlockToast checkingToast;
  private FragmentNavigationSettingsBinding mBinding;
  
  public static Fragment newInstance()
  {
    return new NavigationSettingsFragment();
  }
  
  public void cancelUpdateToastWithDelay(int paramInt)
  {
    this.checkingToast.cancelWithDelay(paramInt);
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    this.mBinding = ((FragmentNavigationSettingsBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427427, paramViewGroup, false));
    this.mBinding.setViewModel(new NavigationSettingsViewModel(this));
    return this.mBinding.getRoot();
  }
  
  public void openAbout()
  {
    ActivityJumpUtil.jumpToAboutAppActivity(getActivity());
  }
  
  public void openFirmwareInfoPage()
  {
    ActivityJumpUtil.jumpToFirmwareInfoActivity(getActivity());
  }
  
  public void showNewVersionDialog(AndroidVerInfoData paramAndroidVerInfoData)
  {
    final UpdateHintDialog localUpdateHintDialog = new UpdateHintDialog(getActivity());
    localUpdateHintDialog.setData(paramAndroidVerInfoData);
    localUpdateHintDialog.setOnOperationConfirmedListener(new UpdateHintDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        localUpdateHintDialog.dismiss();
      }
    });
    localUpdateHintDialog.show();
  }
  
  public void showNonFirmwareDialog()
  {
    ConfirmDialog localConfirmDialog = new ConfirmDialog(getActivity());
    localConfirmDialog.setTitleText(TextUtil.getStringById(2131493282));
    localConfirmDialog.setOnOperationConfirmedListener(new ConfirmDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        ActivityJumpUtil.jumpToConnectActivity(NavigationSettingsFragment.this.getActivity(), 1);
      }
    });
    localConfirmDialog.show();
  }
  
  public void showToast(String paramString)
  {
    MakeBlockToast localMakeBlockToast = new MakeBlockToast(getActivity());
    localMakeBlockToast.setText(paramString);
    localMakeBlockToast.show();
    localMakeBlockToast.cancelWithDelay(2000);
  }
  
  public void showUpdateToast(String paramString)
  {
    this.checkingToast = new MakeBlockToast(getActivity());
    this.checkingToast.setText(paramString);
    this.checkingToast.show();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\navigation\NavigationSettingsFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */