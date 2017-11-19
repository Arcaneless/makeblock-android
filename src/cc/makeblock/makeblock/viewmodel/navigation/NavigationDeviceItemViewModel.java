package cc.makeblock.makeblock.viewmodel.navigation;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;

public class NavigationDeviceItemViewModel
  extends BaseObservable
{
  private ChooseDeviceItem mDeviceItem;
  
  public NavigationDeviceItemViewModel(ChooseDeviceItem paramChooseDeviceItem)
  {
    this.mDeviceItem = paramChooseDeviceItem;
  }
  
  @Bindable
  public ChooseDeviceItem getDeviceItem()
  {
    return this.mDeviceItem;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\navigation\NavigationDeviceItemViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */