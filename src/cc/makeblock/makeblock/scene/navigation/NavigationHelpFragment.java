package cc.makeblock.makeblock.scene.navigation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.base.BaseFragment;
import cc.makeblock.makeblock.databinding.FragmentNavigationHelpBinding;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationHelpViewModel;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationHelpViewModel.NavigationHelpView;

public class NavigationHelpFragment
  extends BaseFragment
  implements NavigationHelpViewModel.NavigationHelpView
{
  private FragmentNavigationHelpBinding mBinding;
  
  public static Fragment newInstance()
  {
    return new NavigationHelpFragment();
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    this.mBinding = ((FragmentNavigationHelpBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427426, paramViewGroup, false));
    this.mBinding.setViewModel(new NavigationHelpViewModel(this));
    return this.mBinding.getRoot();
  }
  
  public void openWebPage(String paramString)
  {
    ActivityJumpUtil.jumpToGroupActivity(getActivity(), paramString);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\navigation\NavigationHelpFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */