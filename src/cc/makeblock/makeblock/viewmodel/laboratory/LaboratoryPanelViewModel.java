package cc.makeblock.makeblock.viewmodel.laboratory;

import android.databinding.BaseObservable;
import android.databinding.ObservableInt;
import android.util.Log;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnDeviceChangeListener;
import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.action.ActionExecutor;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.device.MBot.MBotStatusAdapter;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.device.operation.Buzzer;
import cc.makeblock.makeblock.engine.device.operation.Joystick;
import cc.makeblock.makeblock.engine.device.operation.LedOnBoard;
import cc.makeblock.makeblock.engine.device.operation.LightSensor;
import cc.makeblock.makeblock.engine.device.operation.LineFollowerSensor;
import cc.makeblock.makeblock.engine.device.operation.UltrasonicSensor;
import cc.makeblock.makeblock.engine.diy.DiyActionFactory;
import cc.makeblock.makeblock.engine.diy.DiyProjectBean;
import cc.makeblock.makeblock.engine.diy.Widget;
import cc.makeblock.makeblock.engine.diy.database.AppDatabase;
import cc.makeblock.makeblock.engine.diy.database.DiyProjectDao;
import cc.makeblock.makeblock.engine.diy.database.DiyProjectEntity;
import cc.makeblock.makeblock.engine.utils.JsonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import rx.Observer;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class LaboratoryPanelViewModel
  extends BaseObservable
  implements ControllerManager.OnDeviceChangeListener
{
  private int cpId;
  private ActionExecutor mActionExecutor;
  private int mCurrentModel = 1;
  private MBot.MBotStatusAdapter mMBotStatusAdapter = new MBot.MBotStatusAdapter()
  {
    public void onLightSensorReply(float paramAnonymousFloat)
    {
      LaboratoryPanelViewModel.this.mView.setLightSensor(paramAnonymousFloat);
    }
    
    public void onLineFollowerSensorReply(float paramAnonymousFloat)
    {
      LaboratoryPanelViewModel.this.mView.setLineFollowerSensor(paramAnonymousFloat);
    }
    
    public void onUltrasonicSensorReply(float paramAnonymousFloat)
    {
      LaboratoryPanelViewModel.this.mView.setUltrasonicSensor(paramAnonymousFloat);
    }
  };
  private MakeBlockDevice mMakeBlockDevice;
  private DiyProjectBean mProjectBean;
  private LaboratoryPanelView mView;
  public final ObservableInt switchIcon = new ObservableInt();
  public final ObservableInt toolBarBg = new ObservableInt();
  
  public LaboratoryPanelViewModel(LaboratoryPanelView paramLaboratoryPanelView, int paramInt)
  {
    setCurrentDevice(ControllerManager.getInstance().getCurrentDevice());
    this.mView = paramLaboratoryPanelView;
    this.cpId = paramInt;
    if (paramInt > 0)
    {
      this.mCurrentModel = 1;
      this.switchIcon.set(2131165431);
    }
    for (;;)
    {
      this.mView.setModel(this.mCurrentModel);
      queryCPData();
      this.mActionExecutor = new ActionExecutor();
      ControllerManager.getInstance().registerDeviceListener(this);
      return;
      this.mCurrentModel = 2;
      this.switchIcon.set(2131165468);
    }
  }
  
  private Action createAction(Widget paramWidget, Map<String, String> paramMap)
  {
    HashMap localHashMap = new HashMap();
    if (paramMap != null) {
      localHashMap.putAll(paramMap);
    }
    if (paramWidget.arguments != null) {
      localHashMap.putAll(paramWidget.arguments);
    }
    paramMap = paramWidget.type;
    int i = -1;
    switch (paramMap.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return null;
        if (paramMap.equals("move_joystick"))
        {
          i = 0;
          continue;
          if (paramMap.equals("move_sprint"))
          {
            i = 1;
            continue;
            if (paramMap.equals("move_joystick_speed"))
            {
              i = 2;
              continue;
              if (paramMap.equals("move_rotate"))
              {
                i = 3;
                continue;
                if (paramMap.equals("move_turn"))
                {
                  i = 4;
                  continue;
                  if (paramMap.equals("light_colorPicker"))
                  {
                    i = 5;
                    continue;
                    if (paramMap.equals("light_gradient"))
                    {
                      i = 6;
                      continue;
                      if (paramMap.equals("light_alarm"))
                      {
                        i = 7;
                        continue;
                        if (paramMap.equals("light_random"))
                        {
                          i = 8;
                          continue;
                          if (paramMap.equals("sound_star"))
                          {
                            i = 9;
                            continue;
                            if (paramMap.equals("sound_birthday"))
                            {
                              i = 10;
                              continue;
                              if (paramMap.equals("sound_tiger"))
                              {
                                i = 11;
                                continue;
                                if (paramMap.equals("sound_alert"))
                                {
                                  i = 12;
                                  continue;
                                  if (paramMap.equals("sound_whistle"))
                                  {
                                    i = 13;
                                    continue;
                                    if (paramMap.equals("sound_christmas"))
                                    {
                                      i = 14;
                                      continue;
                                      if (paramMap.equals("detector_distance"))
                                      {
                                        i = 15;
                                        continue;
                                        if (paramMap.equals("detector_line_follower"))
                                        {
                                          i = 16;
                                          continue;
                                          if (paramMap.equals("detector_light")) {
                                            i = 17;
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        break;
      }
    }
    return DiyActionFactory.createJoystickAction((Joystick)this.mMakeBlockDevice, paramWidget.type, localHashMap);
    return DiyActionFactory.createLedOnBoardAction((LedOnBoard)this.mMakeBlockDevice, paramWidget.type, localHashMap);
    return DiyActionFactory.createBuzzerAction((Buzzer)this.mMakeBlockDevice, paramWidget.type, localHashMap);
    return DiyActionFactory.createUltrasonicSensorAction((UltrasonicSensor)this.mMakeBlockDevice, paramWidget.type, localHashMap);
    return DiyActionFactory.createLineFollowerSensorAction((LineFollowerSensor)this.mMakeBlockDevice, paramWidget.type, localHashMap);
    return DiyActionFactory.createLightSensorAction((LightSensor)this.mMakeBlockDevice, paramWidget.type, localHashMap);
  }
  
  private void createProjectBean()
  {
    this.mProjectBean = new DiyProjectBean();
    this.mProjectBean.version = 1;
    this.mProjectBean.widgets = new ArrayList();
    this.mProjectBean.boardName = this.mMakeBlockDevice.getBoardName().name();
    this.mProjectBean.lastModifyTime = System.currentTimeMillis();
  }
  
  private void fillView()
  {
    if (this.mProjectBean != null) {
      this.mView.setWidgets(this.mProjectBean.widgets);
    }
  }
  
  private boolean isDetectorWidget(Widget paramWidget)
  {
    paramWidget = paramWidget.type;
    int i = -1;
    switch (paramWidget.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return false;
        if (paramWidget.equals("detector_distance"))
        {
          i = 0;
          continue;
          if (paramWidget.equals("detector_line_follower"))
          {
            i = 1;
            continue;
            if (paramWidget.equals("detector_light")) {
              i = 2;
            }
          }
        }
        break;
      }
    }
    return true;
  }
  
  private boolean isDragWidget(Widget paramWidget, int paramInt1, int paramInt2)
  {
    return (paramWidget.x == paramInt1) && (paramWidget.y == paramInt2);
  }
  
  private boolean isForceExecuteAction(Widget paramWidget)
  {
    paramWidget = paramWidget.type;
    int i = -1;
    switch (paramWidget.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return false;
        if (paramWidget.equals("light_colorPicker"))
        {
          i = 0;
          continue;
          if (paramWidget.equals("light_gradient"))
          {
            i = 1;
            continue;
            if (paramWidget.equals("light_alarm"))
            {
              i = 2;
              continue;
              if (paramWidget.equals("light_random")) {
                i = 3;
              }
            }
          }
        }
        break;
      }
    }
    return true;
  }
  
  private void queryCPData()
  {
    Single.just(Integer.valueOf(this.cpId)).map(new Func1()
    {
      public DiyProjectBean call(Integer paramAnonymousInteger)
      {
        Object localObject = ControllerManager.getInstance().getAppDatabase().diyProjectDao().loadAllByIds(new int[] { paramAnonymousInteger.intValue() });
        int i = ((List)localObject).size();
        paramAnonymousInteger = null;
        if (i > 0)
        {
          localObject = (DiyProjectEntity)((List)localObject).get(0);
          paramAnonymousInteger = (DiyProjectBean)JsonUtil.jsonToObject(((DiyProjectEntity)localObject).project, DiyProjectBean.class);
          paramAnonymousInteger.id = ((DiyProjectEntity)localObject).id;
        }
        return paramAnonymousInteger;
      }
    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer()
    {
      public void onCompleted()
      {
        LaboratoryPanelViewModel.this.fillView();
        LaboratoryPanelViewModel.this.sendDetectorAction();
      }
      
      public void onError(Throwable paramAnonymousThrowable) {}
      
      public void onNext(DiyProjectBean paramAnonymousDiyProjectBean)
      {
        if (LaboratoryPanelViewModel.this.cpId >= 0) {
          LaboratoryPanelViewModel.access$402(LaboratoryPanelViewModel.this, paramAnonymousDiyProjectBean);
        }
      }
    });
  }
  
  private void registerDeviceListener(Device paramDevice)
  {
    if ((paramDevice instanceof MBot)) {
      ((MBot)this.mMakeBlockDevice).registerMBotStatusAdapter(this.mMBotStatusAdapter);
    }
  }
  
  private void saveData()
  {
    Single.just(this.mProjectBean).map(new Func1()
    {
      public String call(DiyProjectBean paramAnonymousDiyProjectBean)
      {
        if (paramAnonymousDiyProjectBean == null) {}
        DiyProjectEntity localDiyProjectEntity;
        DiyProjectDao localDiyProjectDao;
        do
        {
          return null;
          paramAnonymousDiyProjectBean.lastModifyTime = System.currentTimeMillis();
          localDiyProjectEntity = new DiyProjectEntity();
          localDiyProjectEntity.id = paramAnonymousDiyProjectBean.id;
          localDiyProjectEntity.project = JsonUtil.objectToJson(paramAnonymousDiyProjectBean);
          localDiyProjectDao = ControllerManager.getInstance().getAppDatabase().diyProjectDao();
          if (paramAnonymousDiyProjectBean.widgets.size() == 0)
          {
            localDiyProjectDao.delete(localDiyProjectEntity);
            return null;
          }
        } while (localDiyProjectDao.update(new DiyProjectEntity[] { localDiyProjectEntity }) > 0);
        localDiyProjectEntity.id = 0;
        localDiyProjectDao.insert(new DiyProjectEntity[] { localDiyProjectEntity });
        return null;
      }
    }).subscribeOn(Schedulers.io()).subscribe(new Action1()
    {
      public void call(String paramAnonymousString)
      {
        Log.d("Laboratory Panel", "save success");
      }
    });
  }
  
  private void sendDetectorAction()
  {
    if (this.mProjectBean == null) {}
    for (;;)
    {
      return;
      List localList = this.mProjectBean.widgets;
      int j = localList.size();
      int i = 0;
      while (i < j)
      {
        Widget localWidget = (Widget)localList.get(i);
        if (isDetectorWidget(localWidget)) {
          this.mActionExecutor.executeAction(createAction(localWidget, null));
        }
        i += 1;
      }
    }
  }
  
  private void setCurrentDevice(Device paramDevice)
  {
    if (paramDevice != null)
    {
      this.mMakeBlockDevice = ((MakeBlockDevice)paramDevice);
      unRegisterDeviceListener(paramDevice);
      registerDeviceListener(paramDevice);
    }
  }
  
  private void unRegisterDeviceListener(Device paramDevice)
  {
    if ((paramDevice instanceof MBot)) {
      ((MBot)this.mMakeBlockDevice).removeMBotStatusAdapter(this.mMBotStatusAdapter);
    }
  }
  
  public void addWidget(Widget paramWidget)
  {
    if (paramWidget == null) {
      return;
    }
    if (this.mProjectBean == null) {
      createProjectBean();
    }
    this.mProjectBean.widgets.add(paramWidget);
    this.mView.addWidget(paramWidget);
  }
  
  public void clickSwitch()
  {
    this.mActionExecutor.cancelAllActions();
    if (this.mCurrentModel == 2)
    {
      this.switchIcon.set(2131165431);
      this.toolBarBg.set(0);
      this.mCurrentModel = 1;
      sendDetectorAction();
      this.mView.goToRun();
    }
    for (;;)
    {
      this.mView.setModel(this.mCurrentModel);
      return;
      this.switchIcon.set(2131165468);
      this.toolBarBg.set(2131165321);
      this.mCurrentModel = 2;
      this.mView.goToDesign();
    }
  }
  
  public int getCurrentModel()
  {
    return this.mCurrentModel;
  }
  
  public void onButtonTrigger(Widget paramWidget)
  {
    Action localAction = createAction(paramWidget, null);
    if (!this.mActionExecutor.executeAction(localAction, isForceExecuteAction(paramWidget))) {
      this.mActionExecutor.cancelAction(localAction);
    }
  }
  
  public void onCellClick(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (paramBoolean) {
      this.mView.openChooseAction(new int[] { paramInt1, paramInt2 });
    }
  }
  
  public void onColorPick(Widget paramWidget, int paramInt)
  {
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("light_colorPicker_color", String.valueOf(paramInt));
    this.mActionExecutor.executeAction(createAction(paramWidget, localHashMap));
  }
  
  public void onDestroy()
  {
    ControllerManager.getInstance().unRegisterDeviceListener(this);
    this.mActionExecutor.cancelAllActions();
    saveData();
    if ((this.mMakeBlockDevice instanceof MBot)) {
      ((MBot)this.mMakeBlockDevice).removeMBotStatusAdapter(this.mMBotStatusAdapter);
    }
  }
  
  public void onDeviceChange(Device paramDevice)
  {
    setCurrentDevice(paramDevice);
  }
  
  public void onJoystickTrigger(Widget paramWidget, int paramInt, float paramFloat)
  {
    HashMap localHashMap = new HashMap(2);
    localHashMap.put("move_joystick_angle", String.valueOf(paramInt));
    localHashMap.put("move_joystick_percent_r", String.valueOf(paramFloat));
    this.mActionExecutor.executeAction(createAction(paramWidget, localHashMap));
  }
  
  public void removeWidget(String paramString)
  {
    int j = this.mProjectBean.widgets.size();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        if (((Widget)this.mProjectBean.widgets.get(i)).type.equals(paramString)) {
          this.mProjectBean.widgets.remove(i);
        }
      }
      else {
        return;
      }
      i += 1;
    }
  }
  
  public void updateWidgetPosition(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramString = this.mProjectBean.widgets.iterator();
    while (paramString.hasNext())
    {
      Widget localWidget = (Widget)paramString.next();
      if (isDragWidget(localWidget, paramInt1, paramInt2))
      {
        localWidget.x = paramInt3;
        localWidget.y = paramInt4;
      }
    }
  }
  
  public static abstract interface LaboratoryPanelView
  {
    public abstract void addWidget(Widget paramWidget);
    
    public abstract void goToDesign();
    
    public abstract void goToRun();
    
    public abstract void openChooseAction(int[] paramArrayOfInt);
    
    public abstract void setLightSensor(float paramFloat);
    
    public abstract void setLineFollowerSensor(float paramFloat);
    
    public abstract void setModel(int paramInt);
    
    public abstract void setUltrasonicSensor(float paramFloat);
    
    public abstract void setWidgets(List<Widget> paramList);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\laboratory\LaboratoryPanelViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */