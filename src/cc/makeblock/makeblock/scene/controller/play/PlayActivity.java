package cc.makeblock.makeblock.scene.controller.play;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import cc.makeblock.makeblock.base.NotifyBluetoothActivity;
import cc.makeblock.makeblock.customview.ProgressScrollBar;
import cc.makeblock.makeblock.databinding.ActivityPlayBinding;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnDeviceChangeListener;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.net.tutorial.TutorialInfo.Data.Devices;
import cc.makeblock.makeblock.engine.net.tutorial.TutorialInfo.Data.Devices.Device;
import cc.makeblock.makeblock.engine.net.tutorial.TutorialInfo.Data.Devices.Device.LanguageUnitData;
import cc.makeblock.makeblock.engine.net.tutorial.TutorialManager;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.FileUtils;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.project.Playgrounds.Playground;
import cc.makeblock.makeblock.project.SettingsManager;
import cc.makeblock.makeblock.scene.controller.ListItemData;
import cc.makeblock.makeblock.scene.controller.ProjectListChangeEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PlayActivity
  extends NotifyBluetoothActivity
  implements ControllerManager.OnDeviceChangeListener
{
  public static final String KEY_CP_TYPE = "cp_type";
  private static final String TAG = PlayActivity.class.getSimpleName();
  private ActivityPlayBinding binding;
  private String boardName;
  private int cpType;
  private Device device;
  private ProgressScrollBar officialListBar;
  private PlayListsAdapter officialProjectListAdapter;
  private RecyclerView officialProjectsRecyclerView;
  private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener()
  {
    public void onScrolled(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      super.onScrolled(paramAnonymousRecyclerView, paramAnonymousInt1, paramAnonymousInt2);
      float f = paramAnonymousRecyclerView.computeHorizontalScrollOffset() / (paramAnonymousRecyclerView.computeHorizontalScrollRange() - ScreenHelper.getPercentWidthToPx(0.9739583F));
      if (paramAnonymousRecyclerView == PlayActivity.this.officialProjectsRecyclerView) {
        PlayActivity.this.officialListBar.setScrollPercent(paramAnonymousRecyclerView, f);
      }
    }
  };
  
  private void init()
  {
    this.officialProjectsRecyclerView = this.binding.officialProjectList;
    this.officialProjectListAdapter = new PlayListsAdapter(this, this.cpType);
    setUpListRecyclerView(this.officialProjectsRecyclerView, this.officialProjectListAdapter);
    this.officialListBar = this.binding.indicatorOfficialProgress;
    notifyProjectListsChange();
    this.officialListBar.setListCount(this.officialProjectListAdapter.getItemCount());
  }
  
  private void notifyProjectListsChange()
  {
    Playgrounds.Playground localPlayground = null;
    Object localObject2 = TutorialManager.getInstance().getTutorial();
    Object localObject1 = localPlayground;
    if (localObject2 != null)
    {
      localObject2 = ((TutorialInfo.Data.Devices)localObject2).getDeviceMap();
      localObject1 = localPlayground;
      if (localObject2 != null)
      {
        localObject2 = (TutorialInfo.Data.Devices.Device)((HashMap)localObject2).get(((MakeBlockDevice)this.device).getDeviceName());
        localObject1 = localPlayground;
        if (localObject2 != null)
        {
          localObject2 = (TutorialInfo.Data.Devices.Device.LanguageUnitData)((TutorialInfo.Data.Devices.Device)localObject2).getLanguageUnitDataMap().get(getString(2131493377));
          localObject1 = localPlayground;
          if (localObject2 != null)
          {
            localObject1 = localPlayground;
            if (((TutorialInfo.Data.Devices.Device.LanguageUnitData)localObject2).getShow() == 1.0D)
            {
              String str = ((TutorialInfo.Data.Devices.Device.LanguageUnitData)localObject2).getImage();
              localObject1 = localPlayground;
              if (new File(FileUtils.getAppExternalStorageDirectoryPath() + "/pic/" + TutorialManager.getInstance().getPicFileName(str)).exists()) {
                localObject1 = new ListItemData((TutorialInfo.Data.Devices.Device.LanguageUnitData)localObject2);
              }
            }
          }
        }
      }
    }
    if (localObject1 != null) {
      this.officialProjectListAdapter.addUrlItemData((ListItemData)localObject1);
    }
    for (;;)
    {
      localObject1 = SettingsManager.getPlaygrounds(this.boardName, this.cpType);
      this.officialProjectListAdapter.clearPlaygroundItemDataList();
      if (localObject1 == null) {
        break;
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localPlayground = (Playgrounds.Playground)((Iterator)localObject1).next();
        this.officialProjectListAdapter.addPlayGroundItemData(new ListItemData(localPlayground));
      }
      this.officialProjectListAdapter.removeUrlItemData();
    }
    this.officialProjectListAdapter.notifyListChange(new ProjectListChangeEvent(3), this.boardName);
  }
  
  private void setUpListRecyclerView(RecyclerView paramRecyclerView, PlayListsAdapter paramPlayListsAdapter)
  {
    paramRecyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
    int i = ScreenHelper.getPercentWidthToPx(0.013020833F);
    paramRecyclerView.setPadding(i, i, i, 0);
    paramRecyclerView.setClipToPadding(false);
    paramRecyclerView.setHasFixedSize(true);
    paramRecyclerView.setItemViewCacheSize(10);
    paramRecyclerView.setDrawingCacheEnabled(true);
    paramRecyclerView.setDrawingCacheQuality(1048576);
    paramPlayListsAdapter.setHasStableIds(true);
    paramRecyclerView.addItemDecoration(new PlayListsAdapter.ProjectItemDecoration());
    paramRecyclerView.setAdapter(paramPlayListsAdapter);
    paramRecyclerView.addOnScrollListener(this.onScrollListener);
  }
  
  protected boolean isAutoShowConnectDialog()
  {
    return false;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.boardName = SharedPreferencesUtils.getCurrentChooseDeviceBoardName();
    this.device = ControllerManager.getInstance().getCurrentDevice();
    this.cpType = getIntent().getIntExtra("cp_type", 0);
    this.binding = ((ActivityPlayBinding)DataBindingUtil.setContentView(this, 2131427382));
    StatisticsTool.getInstance().onEvent("EnterMainActivity", "进入主页面");
    init();
    ControllerManager.getInstance().registerDeviceListener(this);
  }
  
  protected void onDestroy()
  {
    if (this.officialProjectsRecyclerView != null) {
      this.officialProjectsRecyclerView.removeOnScrollListener(this.onScrollListener);
    }
    ControllerManager.getInstance().unRegisterDeviceListener(this);
    super.onDestroy();
  }
  
  public void onDeviceChange(Device paramDevice)
  {
    if ((paramDevice.isConnected()) && (!this.device.equals(paramDevice))) {
      finish();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\controller\play\PlayActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */