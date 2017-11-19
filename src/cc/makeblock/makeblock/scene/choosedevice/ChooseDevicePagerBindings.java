package cc.makeblock.makeblock.scene.choosedevice;

import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import java.util.List;

public class ChooseDevicePagerBindings
{
  @BindingAdapter({"items"})
  public static void setItems(ViewPager paramViewPager, List<ChooseDeviceItem> paramList)
  {
    paramViewPager = (ChooseDeviceActivity.DeviceViewPageAdapter)paramViewPager.getAdapter();
    if (paramViewPager != null) {
      paramViewPager.replaceData(paramList);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\choosedevice\ChooseDevicePagerBindings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */