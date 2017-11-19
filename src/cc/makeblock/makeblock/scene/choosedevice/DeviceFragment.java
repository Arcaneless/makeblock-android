package cc.makeblock.makeblock.scene.choosedevice;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.base.BaseFragment;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import cc.makeblock.makeblock.databinding.FragmentDeviceBinding;
import cc.makeblock.makeblock.viewmodel.choosedevice.ChooseDeviceItemViewModel;

public class DeviceFragment
  extends BaseFragment
{
  public static final String KEY_BOARD_NAME = "boardName";
  public static final String KEY_DEVICE = "device";
  private ChooseDeviceItemViewModel mItemViewModel;
  
  public static DeviceFragment newInstance(ChooseDeviceItem paramChooseDeviceItem, String paramString)
  {
    DeviceFragment localDeviceFragment = new DeviceFragment();
    Bundle localBundle = new Bundle();
    localBundle.putSerializable("device", paramChooseDeviceItem);
    localBundle.putString("boardName", paramString);
    localDeviceFragment.setArguments(localBundle);
    return localDeviceFragment;
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getArguments().getString("boardName");
    this.mItemViewModel = new ChooseDeviceItemViewModel((ChooseDeviceItem)getArguments().getSerializable("device"), paramBundle, ((ChooseDeviceActivity)getActivity()).getShadeDrawable());
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    paramLayoutInflater = (FragmentDeviceBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427423, paramViewGroup, false);
    paramLayoutInflater.setViewModel(this.mItemViewModel);
    return paramLayoutInflater.getRoot();
  }
  
  public void setAlpha(float paramFloat)
  {
    if (this.mItemViewModel != null) {
      this.mItemViewModel.setAlpha(paramFloat);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\choosedevice\DeviceFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */