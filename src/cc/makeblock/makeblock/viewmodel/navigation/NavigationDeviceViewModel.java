package cc.makeblock.makeblock.viewmodel.navigation;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import java.util.ArrayList;
import java.util.List;

public class NavigationDeviceViewModel
  extends BaseObservable
{
  public ObservableArrayList<ChooseDeviceItem> deviceItems = new ObservableArrayList();
  private NavigationDeviceView mView;
  
  public NavigationDeviceViewModel(NavigationDeviceView paramNavigationDeviceView)
  {
    this.mView = paramNavigationDeviceView;
    initDeviceData();
  }
  
  @NonNull
  private List<ChooseDeviceItem> createDevices()
  {
    ArrayList localArrayList = new ArrayList(6);
    localArrayList.add(new ChooseDeviceItem(2131165323, 2131165730, 2131165403, TextUtil.getStringById(2131492932), TextUtil.getStringById(2131493296)));
    if (SharedPreferencesUtils.getShowCodey()) {
      localArrayList.add(new ChooseDeviceItem(2131165324, 2131165731, 2131165426, DeviceBoardName.codey.toString(), DeviceBoardName.codey.getDeviceName()));
    }
    localArrayList.add(new ChooseDeviceItem(2131165325, 2131165732, 2131165441, TextUtil.getStringById(2131493287), TextUtil.getStringById(2131493309)));
    localArrayList.add(new ChooseDeviceItem(2131165325, 2131165733, 2131165466, TextUtil.getStringById(2131493288), TextUtil.getStringById(2131493310)));
    localArrayList.add(new ChooseDeviceItem(2131165325, 2131165735, 2131165501, TextUtil.getStringById(2131493379), TextUtil.getStringById(2131493302)));
    localArrayList.add(new ChooseDeviceItem(2131165325, 2131165736, 2131165502, TextUtil.getStringById(2131493378), TextUtil.getStringById(2131493303)));
    if (SharedPreferencesUtils.getShowSmartServo()) {
      localArrayList.add(new ChooseDeviceItem(2131165326, 2131165734, 2131165500, TextUtil.getStringById(2131493348), TextUtil.getStringById(2131493319)));
    }
    return localArrayList;
  }
  
  private void initDeviceData()
  {
    List localList = createDevices();
    sortItems(localList);
    this.deviceItems.addAll(localList);
  }
  
  private void sortItems(List<ChooseDeviceItem> paramList)
  {
    String str = SharedPreferencesUtils.getCurrentChooseDeviceBoardName();
    int j = paramList.size();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        ChooseDeviceItem localChooseDeviceItem = (ChooseDeviceItem)paramList.get(i);
        if (localChooseDeviceItem.boardName.equals(str))
        {
          paramList.remove(localChooseDeviceItem);
          paramList.add(0, localChooseDeviceItem);
        }
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
  
  public void disconnectSelect(int paramInt)
  {
    ControllerManager.getInstance().disconnect();
    this.mView.onItemClick((ChooseDeviceItem)this.deviceItems.get(paramInt));
    SharedPreferencesUtils.setChosenBoardName(((ChooseDeviceItem)this.deviceItems.get(paramInt)).boardName);
  }
  
  public void onItemClick(int paramInt)
  {
    ChooseDeviceItem localChooseDeviceItem = (ChooseDeviceItem)this.deviceItems.get(paramInt);
    if ((ControllerManager.getInstance().isConnectedOk()) && (!localChooseDeviceItem.boardName.equals(ControllerManager.getInstance().getChosenBoardName())))
    {
      this.mView.showChooseConflictDialog(paramInt);
      return;
    }
    this.mView.onItemClick(localChooseDeviceItem);
    ControllerManager.getInstance().setChosenBoardName(localChooseDeviceItem.boardName);
  }
  
  public static abstract interface NavigationDeviceView
  {
    public abstract void onItemClick(ChooseDeviceItem paramChooseDeviceItem);
    
    public abstract void showChooseConflictDialog(int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\navigation\NavigationDeviceViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */