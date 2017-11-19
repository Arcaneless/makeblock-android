package cc.makeblock.makeblock.scene.navigation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import cc.makeblock.makeblock.base.BaseActivity;
import cc.makeblock.makeblock.databinding.ActivityNavigationBinding;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationViewModel;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationViewModel.NavigationView;

public class NavigationActivity
  extends BaseActivity
  implements NavigationViewModel.NavigationView
{
  private ActivityNavigationBinding mBinding;
  
  private void showFragment(Fragment paramFragment)
  {
    getSupportFragmentManager().beginTransaction().replace(2131296416, paramFragment).commit();
  }
  
  public void close()
  {
    finish();
    setResult(0);
    overridePendingTransition(2130771978, 2130771988);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mBinding = ((ActivityNavigationBinding)DataBindingUtil.setContentView(this, 2131427380));
    this.mBinding.setViewModel(new NavigationViewModel(this));
  }
  
  public void showDevice()
  {
    showFragment(NavigationDeviceFragment.newInstance());
  }
  
  public void showHelp()
  {
    showFragment(NavigationHelpFragment.newInstance());
  }
  
  public void showSettings()
  {
    showFragment(NavigationSettingsFragment.newInstance());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\navigation\NavigationActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */