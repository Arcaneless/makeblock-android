package cc.makeblock.makeblock.engine;

import android.app.Activity;
import android.content.Intent;
import cc.makeblock.makeblock.scene.about.AboutAppActivity;
import cc.makeblock.makeblock.scene.choosedevice.ChooseDeviceActivity;
import cc.makeblock.makeblock.scene.connect.ConnectActivity;
import cc.makeblock.makeblock.scene.connect.InitiatingDeviceActivity;
import cc.makeblock.makeblock.scene.controller.create.CreateActivity;
import cc.makeblock.makeblock.scene.controller.play.PlayActivity;
import cc.makeblock.makeblock.scene.firmware.FirmwareInfoActivity;
import cc.makeblock.makeblock.scene.firmware.FirmwareUpdateActivity;
import cc.makeblock.makeblock.scene.group.GroupActivity;
import cc.makeblock.makeblock.scene.laboratory.ChooseActionActivity;
import cc.makeblock.makeblock.scene.laboratory.LaboratoryPanelActivity;
import cc.makeblock.makeblock.scene.main.LoadingBuildingActivity;
import cc.makeblock.makeblock.scene.navigation.NavigationActivity;
import cc.makeblock.makeblock.scene.playground.airblock.AirBlockDronePlaygroundActivity;
import cc.makeblock.makeblock.scene.playground.airblock.AirBlockLandPlaygroundActivity;
import cc.makeblock.makeblock.scene.playground.airblock.AirBlockWaterPlaygroundActivity;
import cc.makeblock.makeblock.unity.UnityPlayerActivity;

public class ActivityJumpUtil
{
  private static Intent createIntent(Activity paramActivity, Class paramClass)
  {
    return new Intent(paramActivity, paramClass);
  }
  
  public static void jumpToAboutAppActivity(Activity paramActivity)
  {
    paramActivity.startActivity(createIntent(paramActivity, AboutAppActivity.class));
  }
  
  public static void jumpToAirBlockDroneActivity(Activity paramActivity)
  {
    paramActivity.startActivity(createIntent(paramActivity, AirBlockDronePlaygroundActivity.class));
  }
  
  public static void jumpToAirBlockLandActivity(Activity paramActivity)
  {
    paramActivity.startActivity(createIntent(paramActivity, AirBlockLandPlaygroundActivity.class));
  }
  
  public static void jumpToAirBlockWaterActivity(Activity paramActivity)
  {
    paramActivity.startActivity(createIntent(paramActivity, AirBlockWaterPlaygroundActivity.class));
  }
  
  public static void jumpToChooseActionActivity(Activity paramActivity, boolean paramBoolean)
  {
    Intent localIntent = createIntent(paramActivity, ChooseActionActivity.class);
    localIntent.putExtra("START_UP_FROM_ENTRANCE", paramBoolean);
    paramActivity.startActivity(localIntent);
    setNewFlowAnimation(paramActivity);
  }
  
  public static void jumpToChooseActionActivity(Activity paramActivity, int[] paramArrayOfInt, int paramInt)
  {
    Intent localIntent = createIntent(paramActivity, ChooseActionActivity.class);
    localIntent.putExtra("WIDGET_LOCATION", paramArrayOfInt);
    localIntent.putExtra("START_UP_FROM_ENTRANCE", false);
    paramActivity.startActivityForResult(localIntent, paramInt);
    setNewFlowAnimation(paramActivity);
  }
  
  public static void jumpToChooseDeviceActivity(Activity paramActivity, int paramInt, String paramString)
  {
    Intent localIntent = createIntent(paramActivity, ChooseDeviceActivity.class);
    localIntent.putExtra("boardName", paramString);
    paramActivity.startActivityForResult(localIntent, 2);
  }
  
  public static void jumpToConnectActivity(Activity paramActivity, int paramInt)
  {
    paramActivity.startActivityForResult(createIntent(paramActivity, ConnectActivity.class), paramInt);
    setNewFlowAnimation(paramActivity);
  }
  
  public static void jumpToCreateActivity(Activity paramActivity)
  {
    paramActivity.startActivity(createIntent(paramActivity, CreateActivity.class));
  }
  
  public static void jumpToFirmwareInfoActivity(Activity paramActivity)
  {
    paramActivity.startActivity(createIntent(paramActivity, FirmwareInfoActivity.class));
  }
  
  public static void jumpToFirmwareUpdateActivity(Activity paramActivity)
  {
    paramActivity.startActivityForResult(createIntent(paramActivity, FirmwareUpdateActivity.class), 1);
  }
  
  public static void jumpToGroupActivity(Activity paramActivity)
  {
    jumpToGroupActivity(paramActivity, null);
  }
  
  public static void jumpToGroupActivity(Activity paramActivity, String paramString)
  {
    jumpToGroupActivity(paramActivity, paramString, -1);
  }
  
  public static void jumpToGroupActivity(Activity paramActivity, String paramString, int paramInt)
  {
    Intent localIntent = createIntent(paramActivity, GroupActivity.class);
    if (paramString != null) {
      localIntent.putExtra("URL_FOR_LOADING_KEY", paramString);
    }
    if (paramInt > 0) {
      paramActivity.startActivityForResult(localIntent, paramInt);
    }
    for (;;)
    {
      setNewFlowAnimation(paramActivity);
      return;
      paramActivity.startActivity(localIntent);
    }
  }
  
  public static void jumpToInitiatingActivity(Activity paramActivity, int paramInt)
  {
    paramActivity.startActivityForResult(createIntent(paramActivity, InitiatingDeviceActivity.class), paramInt);
  }
  
  public static void jumpToInitiatingActivity(Activity paramActivity, int paramInt, boolean paramBoolean)
  {
    Intent localIntent = createIntent(paramActivity, InitiatingDeviceActivity.class);
    localIntent.putExtra("FIRMWARE_UPDATE_INIT", paramBoolean);
    paramActivity.startActivityForResult(localIntent, paramInt);
  }
  
  public static void jumpToLabPanelActivity(Activity paramActivity, Intent paramIntent)
  {
    paramActivity.startActivity(paramIntent);
  }
  
  public static void jumpToLaboratoryPanelActivity(Activity paramActivity, int paramInt)
  {
    Intent localIntent = createIntent(paramActivity, LaboratoryPanelActivity.class);
    localIntent.putExtra("cp_id", paramInt);
    paramActivity.startActivity(localIntent);
  }
  
  public static void jumpToLoadingBuildingActivity(Activity paramActivity)
  {
    paramActivity.startActivity(createIntent(paramActivity, LoadingBuildingActivity.class));
  }
  
  public static void jumpToNavigationActivity(Activity paramActivity)
  {
    paramActivity.startActivityForResult(createIntent(paramActivity, NavigationActivity.class), 2);
    setLeftAnimation(paramActivity);
  }
  
  public static void jumpToPlayActivity(Activity paramActivity, int paramInt)
  {
    Intent localIntent = createIntent(paramActivity, PlayActivity.class);
    localIntent.putExtra("cp_type", paramInt);
    paramActivity.startActivity(localIntent);
  }
  
  public static void jumpToUnityActivity(Activity paramActivity, int paramInt)
  {
    paramActivity.startActivityForResult(createIntent(paramActivity, UnityPlayerActivity.class), paramInt);
    setWaitingAnimation(paramActivity);
  }
  
  public static void onBluetoothConnected(Activity paramActivity, int paramInt)
  {
    paramActivity.startActivityForResult(createIntent(paramActivity, InitiatingDeviceActivity.class), paramInt);
  }
  
  private static void setLeftAnimation(Activity paramActivity)
  {
    paramActivity.overridePendingTransition(2130771984, 2130771978);
  }
  
  private static void setNewFlowAnimation(Activity paramActivity)
  {
    paramActivity.overridePendingTransition(2130771983, 2130771978);
  }
  
  private static void setWaitingAnimation(Activity paramActivity)
  {
    paramActivity.overridePendingTransition(2130771999, 2130771998);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\ActivityJumpUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */