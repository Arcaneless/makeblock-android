package cc.makeblock.makeblock.scene.navigation;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import java.util.List;

public class DeviceListBindings
{
  @BindingAdapter({"items"})
  public static void setItems(RecyclerView paramRecyclerView, List<ChooseDeviceItem> paramList)
  {
    paramRecyclerView = (NavigationDeviceFragment.DeviceAdapter)paramRecyclerView.getAdapter();
    if (paramRecyclerView != null) {
      paramRecyclerView.replaceData(paramList);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\navigation\DeviceListBindings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */