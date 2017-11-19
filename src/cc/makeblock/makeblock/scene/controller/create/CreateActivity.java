package cc.makeblock.makeblock.scene.controller.create;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.MotionEvent;
import android.view.View;
import cc.makeblock.makeblock.base.NotifyBluetoothActivity;
import cc.makeblock.makeblock.customview.ProgressScrollBar;
import cc.makeblock.makeblock.databinding.ActivityCreateBinding;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnDeviceChangeListener;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.scene.controller.ProjectListChangeEvent;

public class CreateActivity
  extends NotifyBluetoothActivity
  implements CreateListsAdapter.OnListCountChangeListener, ControllerManager.OnDeviceChangeListener
{
  public static final String MODIFY_PROJECT_RESULT_BUNDLE = "MODIFY_PROJECT_RESULT_BUNDLE";
  public static final String MODIFY_PROJECT_RESULT_EVENT = "MODIFY_PROJECT_RESULT_EVENT";
  public static final String PROJECT_BOARD_NAME = "PROJECT_BOARD_NAME";
  public static final int REQUEST_CODE_MODIFY_PROJECT = 0;
  private static final String TAG = CreateActivity.class.getSimpleName();
  private ActivityCreateBinding binding;
  private String boardName;
  private ProgressScrollBar customListBar;
  private CreateListsAdapter customProjectListAdapter;
  private RecyclerView customProjectsRecyclerView;
  private Device device;
  private Handler mHandler = new Handler();
  private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener()
  {
    public void onScrolled(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      super.onScrolled(paramAnonymousRecyclerView, paramAnonymousInt1, paramAnonymousInt2);
      float f = paramAnonymousRecyclerView.computeHorizontalScrollOffset() / (paramAnonymousRecyclerView.computeHorizontalScrollRange() - ScreenHelper.getPercentWidthToPx(0.9739583F));
      if (paramAnonymousRecyclerView == CreateActivity.this.customProjectsRecyclerView) {
        CreateActivity.this.customListBar.setScrollPercent(paramAnonymousRecyclerView, f);
      }
    }
  };
  
  private void init()
  {
    this.customProjectsRecyclerView = this.binding.customProjectList;
    this.customProjectListAdapter = new CreateListsAdapter(this, false);
    setUpListRecyclerView(this.customProjectsRecyclerView, this.customProjectListAdapter);
    this.customListBar = this.binding.indicatorCustomProgress;
    notifyProjectListsChange();
  }
  
  private void setUpListRecyclerView(RecyclerView paramRecyclerView, CreateListsAdapter paramCreateListsAdapter)
  {
    paramRecyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
    int i = ScreenHelper.getPercentWidthToPx(0.013020833F);
    paramRecyclerView.setPadding(i, i, i, 0);
    paramRecyclerView.setClipToPadding(false);
    paramRecyclerView.setHasFixedSize(true);
    paramRecyclerView.setItemViewCacheSize(10);
    paramRecyclerView.setDrawingCacheEnabled(true);
    paramRecyclerView.setDrawingCacheQuality(1048576);
    paramCreateListsAdapter.setHasStableIds(true);
    paramRecyclerView.addItemDecoration(new CreateListsAdapter.ProjectItemDecoration());
    paramRecyclerView.setAdapter(paramCreateListsAdapter);
    paramRecyclerView.addOnScrollListener(this.onScrollListener);
    paramCreateListsAdapter.setOnListCountChangeListener(this);
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.customProjectListAdapter == null) {
      return super.dispatchTouchEvent(paramMotionEvent);
    }
    if (this.customProjectsRecyclerView.findChildViewUnder(paramMotionEvent.getX(), paramMotionEvent.getY()) == null) {
      this.customProjectListAdapter.setCurrentSelectedItemHolder(null);
    }
    for (;;)
    {
      return super.dispatchTouchEvent(paramMotionEvent);
      Object localObject = this.customProjectListAdapter.getCurrentSelectedItemHolder();
      if (localObject != null)
      {
        localObject = ((CreateListsAdapter.ProjectItemViewHolder)localObject).itemView;
        if ((paramMotionEvent.getX() < ((View)localObject).getX()) || (paramMotionEvent.getX() > ((View)localObject).getX() + ((View)localObject).getWidth()) || (paramMotionEvent.getY() > ((View)localObject).getY() + ((View)localObject).getHeight()) || (paramMotionEvent.getY() < ((View)localObject).getY())) {
          this.customProjectListAdapter.setCurrentSelectedItemHolder(null);
        }
      }
    }
  }
  
  protected boolean isAutoShowConnectDialog()
  {
    return false;
  }
  
  public void notifyCustomProjectListUpdate(ProjectListChangeEvent paramProjectListChangeEvent)
  {
    this.customProjectListAdapter.notifyListChange(paramProjectListChangeEvent);
  }
  
  public void notifyProjectListsChange()
  {
    this.customProjectListAdapter.notifyListChange(new ProjectListChangeEvent(3), this.boardName);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, final Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    }
    Object localObject;
    do
    {
      do
      {
        return;
      } while ((paramInt2 != -1) || (paramIntent == null));
      localObject = paramIntent.getBundleExtra("MODIFY_PROJECT_RESULT_BUNDLE");
      paramIntent = (ProjectListChangeEvent)((Bundle)localObject).get("MODIFY_PROJECT_RESULT_EVENT");
      localObject = ((Bundle)localObject).getString("PROJECT_BOARD_NAME");
    } while ((localObject == null) || (!((String)localObject).equalsIgnoreCase(this.boardName)));
    if ((paramIntent != null) && (paramIntent.eventType == 2)) {
      ((LinearLayoutManager)this.customProjectsRecyclerView.getLayoutManager()).scrollToPositionWithOffset(0, 0);
    }
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        if (!CreateActivity.this.isFinishing()) {
          CreateActivity.this.notifyCustomProjectListUpdate(paramIntent);
        }
      }
    }, 400L);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.device = ControllerManager.getInstance().getCurrentDevice();
    this.boardName = SharedPreferencesUtils.getCurrentChooseDeviceBoardName();
    this.binding = ((ActivityCreateBinding)DataBindingUtil.setContentView(this, 2131427363));
    StatisticsTool.getInstance().onEvent("EnterMainActivity", "进入主页面");
    init();
    ControllerManager.getInstance().registerDeviceListener(this);
  }
  
  protected void onDestroy()
  {
    if (this.customProjectsRecyclerView != null) {
      this.customProjectsRecyclerView.removeOnScrollListener(this.onScrollListener);
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
  
  public void onListCountChange(CreateListsAdapter paramCreateListsAdapter, int paramInt)
  {
    if (this.customListBar != null) {
      this.customListBar.setListCount(paramInt);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\controller\create\CreateActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */