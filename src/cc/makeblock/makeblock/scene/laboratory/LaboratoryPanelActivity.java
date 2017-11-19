package cc.makeblock.makeblock.scene.laboratory;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cc.makeblock.makeblock.base.NotifyBluetoothActivity;
import cc.makeblock.makeblock.customview.laboratory.Button;
import cc.makeblock.makeblock.customview.laboratory.ColorPicker;
import cc.makeblock.makeblock.customview.laboratory.ColorPicker.OnColorPickListener;
import cc.makeblock.makeblock.customview.laboratory.Joystick;
import cc.makeblock.makeblock.customview.laboratory.Joystick.OnJoystickTriggerListener;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryPanelLayout;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryPanelLayout.CellViewWrap;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryPanelLayout.onDragListener;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryView;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryWidgetFactory;
import cc.makeblock.makeblock.customview.laboratory.LineFollowIndicator;
import cc.makeblock.makeblock.customview.laboratory.ValueDetector;
import cc.makeblock.makeblock.databinding.ActivityLaboratoryPanelBinding;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnDeviceChangeListener;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.diy.Widget;
import cc.makeblock.makeblock.viewmodel.laboratory.LaboratoryPanelViewModel;
import cc.makeblock.makeblock.viewmodel.laboratory.LaboratoryPanelViewModel.LaboratoryPanelView;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryPanelActivity
  extends NotifyBluetoothActivity
  implements LaboratoryPanelViewModel.LaboratoryPanelView, ControllerManager.OnDeviceChangeListener
{
  private static final int CODE_REQUEST_ACTION = 256;
  public static final String KEY_CP_ID = "cp_id";
  public static final String KEY_WIDGET = "cp_widget";
  private ActivityLaboratoryPanelBinding mBinding;
  final List<LaboratoryPanelLayout.CellViewWrap> mCellViewWraps = new ArrayList();
  private Rect mDeleteRect;
  private Device mDevice;
  private final Runnable mLoadingRunnable = new Runnable()
  {
    public void run()
    {
      LaboratoryPanelActivity.this.initDeleteRect();
    }
  };
  private LaboratoryPanelViewModel mViewModel;
  private Handler myHandler = new Handler();
  
  private LaboratoryPanelLayout.CellViewWrap createCellViewWrap(Widget paramWidget)
  {
    LaboratoryView localLaboratoryView = LaboratoryWidgetFactory.createWidgetView(this, paramWidget.type);
    if (localLaboratoryView == null) {
      return null;
    }
    if (this.mViewModel.getCurrentModel() == 1) {
      localLaboratoryView.setMode(0);
    }
    for (;;)
    {
      LaboratoryPanelLayout.CellViewWrap localCellViewWrap = new LaboratoryPanelLayout.CellViewWrap(localLaboratoryView, paramWidget.type, getIndex(paramWidget.x, paramWidget.y), localLaboratoryView.getSizePercent());
      setListener(paramWidget, localLaboratoryView);
      return localCellViewWrap;
      localLaboratoryView.setMode(1);
    }
  }
  
  private int getIndex(int paramInt1, int paramInt2)
  {
    return paramInt1 * 4 + paramInt2;
  }
  
  private void initDeleteRect()
  {
    int[] arrayOfInt = new int[2];
    this.mBinding.delete.getLocationInWindow(arrayOfInt);
    int i = arrayOfInt[0];
    int j = arrayOfInt[1];
    this.mDeleteRect = new Rect(i, j, i + this.mBinding.delete.getWidth(), j + this.mBinding.delete.getHeight());
  }
  
  private void listenerDrag()
  {
    this.mBinding.panel.registerDragListener(new LaboratoryPanelLayout.onDragListener()
    {
      public boolean onDrag(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
      {
        if (paramAnonymousInt2 + paramAnonymousView.getHeight() / 3 < LaboratoryPanelActivity.this.mDeleteRect.bottom)
        {
          LaboratoryPanelActivity.this.mBinding.deleteIcon.setImageResource(2131165430);
          paramAnonymousView.setBackgroundResource(2131165277);
          return true;
        }
        paramAnonymousView.setBackgroundResource(2131165278);
        LaboratoryPanelActivity.this.mBinding.deleteIcon.setImageResource(2131165429);
        return false;
      }
      
      public boolean onDrop(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
      {
        LaboratoryPanelActivity.this.mBinding.delete.setVisibility(4);
        if (paramAnonymousInt2 + paramAnonymousView.getHeight() / 3 < LaboratoryPanelActivity.this.mDeleteRect.bottom)
        {
          LaboratoryPanelActivity.this.removeCellViewWrap(paramAnonymousView);
          return true;
        }
        return false;
      }
      
      public void onFinish(View paramAnonymousView, String paramAnonymousString, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
      {
        LaboratoryPanelActivity.this.mViewModel.updateWidgetPosition(paramAnonymousString, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3, paramAnonymousInt4);
      }
      
      public boolean onStart(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
      {
        LaboratoryPanelActivity.this.mBinding.delete.setVisibility(0);
        LaboratoryPanelActivity.this.mBinding.deleteIcon.setImageResource(2131165429);
        return false;
      }
    });
  }
  
  private void removeCellViewWrap(View paramView)
  {
    paramView = ((ViewGroup)paramView).getChildAt(0);
    int j = this.mCellViewWraps.size();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        if (((LaboratoryPanelLayout.CellViewWrap)this.mCellViewWraps.get(i)).view == paramView) {
          this.mCellViewWraps.remove(i);
        }
      }
      else
      {
        this.mViewModel.removeWidget(paramView.getTag(2131296606).toString());
        return;
      }
      i += 1;
    }
  }
  
  private void setLaboratoryViewModel(int paramInt)
  {
    int j = this.mCellViewWraps.size();
    int i = 0;
    while (i < j)
    {
      ((LaboratoryView)((LaboratoryPanelLayout.CellViewWrap)this.mCellViewWraps.get(i)).view).setMode(paramInt);
      i += 1;
    }
  }
  
  private void setListener(final Widget paramWidget, LaboratoryView paramLaboratoryView)
  {
    if ((paramLaboratoryView instanceof Button)) {
      paramLaboratoryView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          LaboratoryPanelActivity.this.mViewModel.onButtonTrigger(paramWidget);
        }
      });
    }
    do
    {
      return;
      if ((paramLaboratoryView instanceof Joystick))
      {
        ((Joystick)paramLaboratoryView).setOnJoystickTriggerListener(new Joystick.OnJoystickTriggerListener()
        {
          public void onJoystickTrigger(int paramAnonymousInt, float paramAnonymousFloat)
          {
            LaboratoryPanelActivity.this.mViewModel.onJoystickTrigger(paramWidget, paramAnonymousInt, paramAnonymousFloat);
          }
        });
        return;
      }
    } while (!(paramLaboratoryView instanceof ColorPicker));
    ((ColorPicker)paramLaboratoryView).setOnColorPickListener(new ColorPicker.OnColorPickListener()
    {
      public void onColorPick(int paramAnonymousInt)
      {
        LaboratoryPanelActivity.this.mViewModel.onColorPick(paramWidget, paramAnonymousInt);
      }
    });
  }
  
  public void addWidget(Widget paramWidget)
  {
    paramWidget = createCellViewWrap(paramWidget);
    this.mCellViewWraps.add(paramWidget);
    this.mBinding.panel.addWidgets(paramWidget);
  }
  
  public void goToDesign()
  {
    setLaboratoryViewModel(1);
  }
  
  public void goToRun()
  {
    setLaboratoryViewModel(0);
  }
  
  protected boolean isAutoShowConnectDialog()
  {
    return false;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt2 == -1) && (paramInt1 == 256)) {
      this.mViewModel.addWidget((Widget)paramIntent.getSerializableExtra("cp_widget"));
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ControllerManager.getInstance().registerDeviceListener(this);
    setCurrentDevice(ControllerManager.getInstance().getCurrentDevice());
    int i = getIntent().getIntExtra("cp_id", -1);
    this.mBinding = ((ActivityLaboratoryPanelBinding)DataBindingUtil.setContentView(this, 2131427370));
    this.mViewModel = new LaboratoryPanelViewModel(this, i);
    this.mBinding.setViewModel(this.mViewModel);
    getWindow().getDecorView().post(new Runnable()
    {
      public void run()
      {
        LaboratoryPanelActivity.this.myHandler.post(LaboratoryPanelActivity.this.mLoadingRunnable);
      }
    });
    listenerDrag();
    paramBundle = (Widget)getIntent().getSerializableExtra("cp_widget");
    this.mViewModel.addWidget(paramBundle);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    ControllerManager.getInstance().unRegisterDeviceListener(this);
    this.mViewModel.onDestroy();
  }
  
  public void onDeviceChange(Device paramDevice)
  {
    if ((paramDevice != null) && (!paramDevice.equals(this.mDevice)) && (paramDevice.isConnected())) {
      finish();
    }
    setCurrentDevice(paramDevice);
  }
  
  public void openChooseAction(int[] paramArrayOfInt)
  {
    ActivityJumpUtil.jumpToChooseActionActivity(this, paramArrayOfInt, 256);
  }
  
  public void setCurrentDevice(Device paramDevice)
  {
    this.mDevice = paramDevice;
  }
  
  public void setLightSensor(float paramFloat)
  {
    int j = this.mCellViewWraps.size();
    int i = 0;
    while (i < j)
    {
      LaboratoryPanelLayout.CellViewWrap localCellViewWrap = (LaboratoryPanelLayout.CellViewWrap)this.mCellViewWraps.get(i);
      if (localCellViewWrap.type.equals("detector_light")) {
        ((ValueDetector)localCellViewWrap.view).setValue((int)paramFloat);
      }
      i += 1;
    }
  }
  
  public void setLineFollowerSensor(float paramFloat)
  {
    int j = this.mCellViewWraps.size();
    int i = 0;
    while (i < j)
    {
      LaboratoryPanelLayout.CellViewWrap localCellViewWrap = (LaboratoryPanelLayout.CellViewWrap)this.mCellViewWraps.get(i);
      if (localCellViewWrap.type.equals("detector_line_follower")) {
        ((LineFollowIndicator)localCellViewWrap.view).setLineFollowState((int)paramFloat);
      }
      i += 1;
    }
  }
  
  public void setModel(int paramInt)
  {
    this.mBinding.panel.setModel(paramInt);
    if (paramInt == 1) {
      showConnectDialog();
    }
  }
  
  public void setUltrasonicSensor(float paramFloat)
  {
    int j = this.mCellViewWraps.size();
    int i = 0;
    while (i < j)
    {
      LaboratoryPanelLayout.CellViewWrap localCellViewWrap = (LaboratoryPanelLayout.CellViewWrap)this.mCellViewWraps.get(i);
      if (localCellViewWrap.type.equals("detector_distance")) {
        ((ValueDetector)localCellViewWrap.view).setValue((int)paramFloat);
      }
      i += 1;
    }
  }
  
  public void setWidgets(List<Widget> paramList)
  {
    this.mCellViewWraps.clear();
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      Widget localWidget = (Widget)paramList.get(i);
      this.mCellViewWraps.add(createCellViewWrap(localWidget));
      i += 1;
    }
    this.mBinding.panel.setWidgets(this.mCellViewWraps);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\laboratory\LaboratoryPanelActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */