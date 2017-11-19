package cc.makeblock.makeblock.viewmodel.choosedevice;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.text.TextUtils;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import java.util.ArrayList;
import java.util.List;

public class ChooseDeviceViewModel
  extends BaseObservable
{
  public ObservableArrayList<ChooseDeviceItem> deviceItems = new ObservableArrayList();
  private boolean isShowBack = true;
  private final String mBoardName;
  
  public ChooseDeviceViewModel(String paramString)
  {
    this.mBoardName = paramString;
    initDeviceData();
    if (TextUtils.isEmpty(paramString)) {
      this.isShowBack = false;
    }
  }
  
  private void initDeviceData()
  {
    ArrayList localArrayList = new ArrayList(6);
    localArrayList.add(new ChooseDeviceItem(2131165378, 2131165725, 2131165403, TextUtil.getStringById(2131492932), TextUtil.getStringById(2131493296), TextUtil.getStringById(2131493184)));
    if (SharedPreferencesUtils.getShowCodey()) {
      localArrayList.add(new ChooseDeviceItem(2131165379, 2131165726, 2131165426, DeviceBoardName.codey.toString(), DeviceBoardName.codey.getDeviceName(), TextUtil.getStringById(2131493185)));
    }
    localArrayList.add(new ChooseDeviceItem(2131165381, 2131165729, 2131165441, TextUtil.getStringById(2131493287), TextUtil.getStringById(2131493309), TextUtil.getStringById(2131493186)));
    localArrayList.add(new ChooseDeviceItem(2131165382, 2131165737, 2131165466, TextUtil.getStringById(2131493288), TextUtil.getStringById(2131493310), TextUtil.getStringById(2131493187)));
    localArrayList.add(new ChooseDeviceItem(2131165384, 2131165739, 2131165501, TextUtil.getStringById(2131493379), TextUtil.getStringById(2131493302), TextUtil.getStringById(2131493189)));
    localArrayList.add(new ChooseDeviceItem(2131165385, 2131165740, 2131165502, TextUtil.getStringById(2131493378), TextUtil.getStringById(2131493303), TextUtil.getStringById(2131493190)));
    if (SharedPreferencesUtils.getShowSmartServo()) {
      localArrayList.add(new ChooseDeviceItem(2131165383, 2131165738, 2131165500, TextUtil.getStringById(2131493348), TextUtil.getStringById(2131493319), TextUtil.getStringById(2131493188)));
    }
    this.deviceItems.addAll(localArrayList);
  }
  
  public boolean isShowBack()
  {
    return this.isShowBack;
  }
  
  public void onBackPressed(Activity paramActivity)
  {
    if (!TextUtils.isEmpty(this.mBoardName)) {
      paramActivity.finish();
    }
  }
  
  public void onFinish(Activity paramActivity)
  {
    paramActivity.finish();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\choosedevice\ChooseDeviceViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */