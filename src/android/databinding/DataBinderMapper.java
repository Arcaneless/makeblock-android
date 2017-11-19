package android.databinding;

import android.view.View;
import cc.makeblock.makeblock.databinding.ActivityAirblockDronePlaygroundBinding;
import cc.makeblock.makeblock.databinding.ActivityAirblockLandPlaygroundBinding;
import cc.makeblock.makeblock.databinding.ActivityAirblockWaterPlaygroundBinding;
import cc.makeblock.makeblock.databinding.ActivityChooseDeviceBinding;
import cc.makeblock.makeblock.databinding.ActivityCodeyControllerBinding;
import cc.makeblock.makeblock.databinding.ActivityConnectBinding;
import cc.makeblock.makeblock.databinding.ActivityCreateBinding;
import cc.makeblock.makeblock.databinding.ActivityFirmwareInfoBinding;
import cc.makeblock.makeblock.databinding.ActivityFirmwareUpdateBinding;
import cc.makeblock.makeblock.databinding.ActivityInitiatingDeviceBinding;
import cc.makeblock.makeblock.databinding.ActivityLaboratoryChooseActionBinding;
import cc.makeblock.makeblock.databinding.ActivityLaboratoryMainBinding;
import cc.makeblock.makeblock.databinding.ActivityLaboratoryPanelBinding;
import cc.makeblock.makeblock.databinding.ActivityMain2Binding;
import cc.makeblock.makeblock.databinding.ActivityMain31Binding;
import cc.makeblock.makeblock.databinding.ActivityMain32Binding;
import cc.makeblock.makeblock.databinding.ActivityMain4in1Binding;
import cc.makeblock.makeblock.databinding.ActivityMainBinding;
import cc.makeblock.makeblock.databinding.ActivityMbotControllerBinding;
import cc.makeblock.makeblock.databinding.ActivityMbotDrawPathBinding;
import cc.makeblock.makeblock.databinding.ActivityMbotMusicBinding;
import cc.makeblock.makeblock.databinding.ActivityNavigationBinding;
import cc.makeblock.makeblock.databinding.ActivityPanelBinding;
import cc.makeblock.makeblock.databinding.ActivityPlayBinding;
import cc.makeblock.makeblock.databinding.ActivityRangerAvoidBinding;
import cc.makeblock.makeblock.databinding.ActivityRangerControllerBinding;
import cc.makeblock.makeblock.databinding.ActivityRangerDrawPathBinding;
import cc.makeblock.makeblock.databinding.ActivityRangerFollowLineBinding;
import cc.makeblock.makeblock.databinding.ActivityRangerMbotSpeakerBinding;
import cc.makeblock.makeblock.databinding.ActivityRangerMusicBinding;
import cc.makeblock.makeblock.databinding.ActivityRangerSpeakerBinding;
import cc.makeblock.makeblock.databinding.DialogAirblockTuneBinding;
import cc.makeblock.makeblock.databinding.DialogLaboratoryWidgetBinding;
import cc.makeblock.makeblock.databinding.FragmentCodeyProgramUpdateBinding;
import cc.makeblock.makeblock.databinding.FragmentDeviceBinding;
import cc.makeblock.makeblock.databinding.FragmentExpandGuideBinding;
import cc.makeblock.makeblock.databinding.FragmentNavigationDeviceBinding;
import cc.makeblock.makeblock.databinding.FragmentNavigationHelpBinding;
import cc.makeblock.makeblock.databinding.FragmentNavigationSettingsBinding;
import cc.makeblock.makeblock.databinding.ItemCreateProjectBinding;
import cc.makeblock.makeblock.databinding.ItemDeviceLargeBinding;
import cc.makeblock.makeblock.databinding.ItemDeviceSmallBinding;
import cc.makeblock.makeblock.databinding.ItemLaboratoryBinding;
import cc.makeblock.makeblock.databinding.ItemMenuSearchDeviceBinding;
import cc.makeblock.makeblock.databinding.ItemPlayProjectBinding;
import cc.makeblock.makeblock.databinding.LabLayoutWidgetDetectorBinding;
import cc.makeblock.makeblock.databinding.LabLayoutWidgetLightBinding;
import cc.makeblock.makeblock.databinding.LabLayoutWidgetMoveBinding;
import cc.makeblock.makeblock.databinding.LabLayoutWidgetSoundBinding;
import cc.makeblock.makeblock.databinding.LayoutCodePanelBinding;
import cc.makeblock.makeblock.databinding.LayoutMenuCommonHeaderBinding;
import cc.makeblock.makeblock.databinding.LayoutMenuMainTextBinding;
import cc.makeblock.makeblock.databinding.LayoutMenuSearchHeaderBinding;
import cc.makeblock.makeblock.databinding.LayoutMenuTextBinding;
import cc.makeblock.makeblock.databinding.LayoutPlaygroundAirblockTopBarBinding;
import cc.makeblock.makeblock.databinding.LayoutServoResetBinding;
import cc.makeblock.makeblock.databinding.LayoutServoUnlockBinding;
import cc.makeblock.makeblock.databinding.OnlineGroupIconBinding;
import cc.makeblock.makeblock.databinding.PopupWidgetEditBinding;
import cc.makeblock.makeblock.databinding.WidgetAirDashBoardBinding;

class DataBinderMapper
{
  static final int TARGET_MIN_SDK = 18;
  
  String convertBrIdToString(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= InnerBrLookup.sKeys.length)) {
      return null;
    }
    return InnerBrLookup.sKeys[paramInt];
  }
  
  public ViewDataBinding getDataBinder(DataBindingComponent paramDataBindingComponent, View paramView, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 2131427416: 
      return DialogLaboratoryWidgetBinding.bind(paramView, paramDataBindingComponent);
    case 2131427368: 
      return ActivityLaboratoryChooseActionBinding.bind(paramView, paramDataBindingComponent);
    case 2131427377: 
      return ActivityMbotControllerBinding.bind(paramView, paramDataBindingComponent);
    case 2131427527: 
      return PopupWidgetEditBinding.bind(paramView, paramDataBindingComponent);
    case 2131427378: 
      return ActivityMbotDrawPathBinding.bind(paramView, paramDataBindingComponent);
    case 2131427425: 
      return FragmentNavigationDeviceBinding.bind(paramView, paramDataBindingComponent);
    case 2131427426: 
      return FragmentNavigationHelpBinding.bind(paramView, paramDataBindingComponent);
    case 2131427497: 
      return LayoutServoResetBinding.bind(paramView, paramDataBindingComponent);
    case 2131427400: 
      return DialogAirblockTuneBinding.bind(paramView, paramDataBindingComponent);
    case 2131427360: 
      return ActivityChooseDeviceBinding.bind(paramView, paramDataBindingComponent);
    case 2131427367: 
      return ActivityInitiatingDeviceBinding.bind(paramView, paramDataBindingComponent);
    case 2131427486: 
      return LayoutMenuCommonHeaderBinding.bind(paramView, paramDataBindingComponent);
    case 2131427384: 
      return ActivityRangerControllerBinding.bind(paramView, paramDataBindingComponent);
    case 2131427443: 
      return LabLayoutWidgetSoundBinding.bind(paramView, paramDataBindingComponent);
    case 2131427364: 
      return ActivityFirmwareInfoBinding.bind(paramView, paramDataBindingComponent);
    case 2131427433: 
      return ItemDeviceLargeBinding.bind(paramView, paramDataBindingComponent);
    case 2131427518: 
      return OnlineGroupIconBinding.bind(paramView, paramDataBindingComponent);
    case 2131427389: 
      return ActivityRangerSpeakerBinding.bind(paramView, paramDataBindingComponent);
    case 2131427386: 
      return ActivityRangerFollowLineBinding.bind(paramView, paramDataBindingComponent);
    case 2131427424: 
      return FragmentExpandGuideBinding.bind(paramView, paramDataBindingComponent);
    case 2131427369: 
      return ActivityLaboratoryMainBinding.bind(paramView, paramDataBindingComponent);
    case 2131427465: 
      return LayoutCodePanelBinding.bind(paramView, paramDataBindingComponent);
    case 2131427361: 
      return ActivityCodeyControllerBinding.bind(paramView, paramDataBindingComponent);
    case 2131427440: 
      return LabLayoutWidgetDetectorBinding.bind(paramView, paramDataBindingComponent);
    case 2131427376: 
      return ActivityMain4in1Binding.bind(paramView, paramDataBindingComponent);
    case 2131427365: 
      return ActivityFirmwareUpdateBinding.bind(paramView, paramDataBindingComponent);
    case 2131427379: 
      return ActivityMbotMusicBinding.bind(paramView, paramDataBindingComponent);
    case 2131427359: 
      return ActivityAirblockWaterPlaygroundBinding.bind(paramView, paramDataBindingComponent);
    case 2131427498: 
      return LayoutServoUnlockBinding.bind(paramView, paramDataBindingComponent);
    case 2131427432: 
      return ItemCreateProjectBinding.bind(paramView, paramDataBindingComponent);
    case 2131427373: 
      return ActivityMain2Binding.bind(paramView, paramDataBindingComponent);
    case 2131427388: 
      return ActivityRangerMusicBinding.bind(paramView, paramDataBindingComponent);
    case 2131427383: 
      return ActivityRangerAvoidBinding.bind(paramView, paramDataBindingComponent);
    case 2131427385: 
      return ActivityRangerDrawPathBinding.bind(paramView, paramDataBindingComponent);
    case 2131427441: 
      return LabLayoutWidgetLightBinding.bind(paramView, paramDataBindingComponent);
    case 2131427372: 
      return ActivityMainBinding.bind(paramView, paramDataBindingComponent);
    case 2131427362: 
      return ActivityConnectBinding.bind(paramView, paramDataBindingComponent);
    case 2131427423: 
      return FragmentDeviceBinding.bind(paramView, paramDataBindingComponent);
    case 2131427487: 
      return LayoutMenuMainTextBinding.bind(paramView, paramDataBindingComponent);
    case 2131427438: 
      return ItemPlayProjectBinding.bind(paramView, paramDataBindingComponent);
    case 2131427489: 
      return LayoutMenuTextBinding.bind(paramView, paramDataBindingComponent);
    case 2131427370: 
      return ActivityLaboratoryPanelBinding.bind(paramView, paramDataBindingComponent);
    case 2131427382: 
      return ActivityPlayBinding.bind(paramView, paramDataBindingComponent);
    case 2131427363: 
      return ActivityCreateBinding.bind(paramView, paramDataBindingComponent);
    case 2131427380: 
      return ActivityNavigationBinding.bind(paramView, paramDataBindingComponent);
    case 2131427534: 
      return WidgetAirDashBoardBinding.bind(paramView, paramDataBindingComponent);
    case 2131427494: 
      return LayoutPlaygroundAirblockTopBarBinding.bind(paramView, paramDataBindingComponent);
    case 2131427374: 
      return ActivityMain31Binding.bind(paramView, paramDataBindingComponent);
    case 2131427442: 
      return LabLayoutWidgetMoveBinding.bind(paramView, paramDataBindingComponent);
    case 2131427357: 
      return ActivityAirblockDronePlaygroundBinding.bind(paramView, paramDataBindingComponent);
    case 2131427435: 
      return ItemLaboratoryBinding.bind(paramView, paramDataBindingComponent);
    case 2131427358: 
      return ActivityAirblockLandPlaygroundBinding.bind(paramView, paramDataBindingComponent);
    case 2131427381: 
      return ActivityPanelBinding.bind(paramView, paramDataBindingComponent);
    case 2131427437: 
      return ItemMenuSearchDeviceBinding.bind(paramView, paramDataBindingComponent);
    case 2131427434: 
      return ItemDeviceSmallBinding.bind(paramView, paramDataBindingComponent);
    case 2131427387: 
      return ActivityRangerMbotSpeakerBinding.bind(paramView, paramDataBindingComponent);
    case 2131427422: 
      return FragmentCodeyProgramUpdateBinding.bind(paramView, paramDataBindingComponent);
    case 2131427375: 
      return ActivityMain32Binding.bind(paramView, paramDataBindingComponent);
    case 2131427488: 
      return LayoutMenuSearchHeaderBinding.bind(paramView, paramDataBindingComponent);
    }
    return FragmentNavigationSettingsBinding.bind(paramView, paramDataBindingComponent);
  }
  
  ViewDataBinding getDataBinder(DataBindingComponent paramDataBindingComponent, View[] paramArrayOfView, int paramInt)
  {
    return null;
  }
  
  int getLayoutId(String paramString)
  {
    if (paramString == null) {}
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            do
                            {
                              do
                              {
                                do
                                {
                                  do
                                  {
                                    do
                                    {
                                      do
                                      {
                                        do
                                        {
                                          do
                                          {
                                            do
                                            {
                                              do
                                              {
                                                do
                                                {
                                                  do
                                                  {
                                                    do
                                                    {
                                                      do
                                                      {
                                                        do
                                                        {
                                                          do
                                                          {
                                                            do
                                                            {
                                                              do
                                                              {
                                                                do
                                                                {
                                                                  do
                                                                  {
                                                                    do
                                                                    {
                                                                      do
                                                                      {
                                                                        do
                                                                        {
                                                                          do
                                                                          {
                                                                            do
                                                                            {
                                                                              do
                                                                              {
                                                                                do
                                                                                {
                                                                                  do
                                                                                  {
                                                                                    do
                                                                                    {
                                                                                      do
                                                                                      {
                                                                                        do
                                                                                        {
                                                                                          do
                                                                                          {
                                                                                            do
                                                                                            {
                                                                                              do
                                                                                              {
                                                                                                do
                                                                                                {
                                                                                                  do
                                                                                                  {
                                                                                                    do
                                                                                                    {
                                                                                                      do
                                                                                                      {
                                                                                                        do
                                                                                                        {
                                                                                                          do
                                                                                                          {
                                                                                                            do
                                                                                                            {
                                                                                                              do
                                                                                                              {
                                                                                                                do
                                                                                                                {
                                                                                                                  do
                                                                                                                  {
                                                                                                                    do
                                                                                                                    {
                                                                                                                      do
                                                                                                                      {
                                                                                                                        do
                                                                                                                        {
                                                                                                                          do
                                                                                                                          {
                                                                                                                            return 0;
                                                                                                                            switch (paramString.hashCode())
                                                                                                                            {
                                                                                                                            default: 
                                                                                                                              return 0;
                                                                                                                            }
                                                                                                                          } while (!paramString.equals("layout/lab_layout_widget_move_0"));
                                                                                                                          return 2131427442;
                                                                                                                        } while (!paramString.equals("layout/dialog_laboratory_widget_0"));
                                                                                                                        return 2131427416;
                                                                                                                      } while (!paramString.equals("layout/activity_laboratory_choose_action_0"));
                                                                                                                      return 2131427368;
                                                                                                                    } while (!paramString.equals("layout/activity_mbot_controller_0"));
                                                                                                                    return 2131427377;
                                                                                                                  } while (!paramString.equals("layout/popup_widget_edit_0"));
                                                                                                                  return 2131427527;
                                                                                                                } while (!paramString.equals("layout/activity_mbot_draw_path_0"));
                                                                                                                return 2131427378;
                                                                                                              } while (!paramString.equals("layout/fragment_navigation_device_0"));
                                                                                                              return 2131427425;
                                                                                                            } while (!paramString.equals("layout/fragment_navigation_help_0"));
                                                                                                            return 2131427426;
                                                                                                          } while (!paramString.equals("layout/layout_servo_reset_0"));
                                                                                                          return 2131427497;
                                                                                                        } while (!paramString.equals("layout/dialog_airblock_tune_0"));
                                                                                                        return 2131427400;
                                                                                                      } while (!paramString.equals("layout/activity_choose_device_0"));
                                                                                                      return 2131427360;
                                                                                                    } while (!paramString.equals("layout/activity_initiating_device_0"));
                                                                                                    return 2131427367;
                                                                                                  } while (!paramString.equals("layout/layout_menu_common_header_0"));
                                                                                                  return 2131427486;
                                                                                                } while (!paramString.equals("layout/activity_ranger_controller_0"));
                                                                                                return 2131427384;
                                                                                              } while (!paramString.equals("layout/lab_layout_widget_sound_0"));
                                                                                              return 2131427443;
                                                                                            } while (!paramString.equals("layout/activity_firmware_info_0"));
                                                                                            return 2131427364;
                                                                                          } while (!paramString.equals("layout/item_device_large_0"));
                                                                                          return 2131427433;
                                                                                        } while (!paramString.equals("layout/online_group_icon_0"));
                                                                                        return 2131427518;
                                                                                      } while (!paramString.equals("layout/activity_ranger_speaker_0"));
                                                                                      return 2131427389;
                                                                                    } while (!paramString.equals("layout/activity_ranger_follow_line_0"));
                                                                                    return 2131427386;
                                                                                  } while (!paramString.equals("layout/fragment_expand_guide_0"));
                                                                                  return 2131427424;
                                                                                } while (!paramString.equals("layout/activity_laboratory_main_0"));
                                                                                return 2131427369;
                                                                              } while (!paramString.equals("layout/layout_code_panel_0"));
                                                                              return 2131427465;
                                                                            } while (!paramString.equals("layout/activity_codey_controller_0"));
                                                                            return 2131427361;
                                                                          } while (!paramString.equals("layout/lab_layout_widget_detector_0"));
                                                                          return 2131427440;
                                                                        } while (!paramString.equals("layout/activity_main_4in1_0"));
                                                                        return 2131427376;
                                                                      } while (!paramString.equals("layout/activity_firmware_update_0"));
                                                                      return 2131427365;
                                                                    } while (!paramString.equals("layout/activity_mbot_music_0"));
                                                                    return 2131427379;
                                                                  } while (!paramString.equals("layout/activity_airblock_water_playground_0"));
                                                                  return 2131427359;
                                                                } while (!paramString.equals("layout/layout_servo_unlock_0"));
                                                                return 2131427498;
                                                              } while (!paramString.equals("layout/item_create_project_0"));
                                                              return 2131427432;
                                                            } while (!paramString.equals("layout/activity_main_2_0"));
                                                            return 2131427373;
                                                          } while (!paramString.equals("layout/activity_ranger_music_0"));
                                                          return 2131427388;
                                                        } while (!paramString.equals("layout/activity_ranger_avoid_0"));
                                                        return 2131427383;
                                                      } while (!paramString.equals("layout/activity_ranger_draw_path_0"));
                                                      return 2131427385;
                                                    } while (!paramString.equals("layout/lab_layout_widget_light_0"));
                                                    return 2131427441;
                                                  } while (!paramString.equals("layout/activity_main_0"));
                                                  return 2131427372;
                                                } while (!paramString.equals("layout/activity_connect_0"));
                                                return 2131427362;
                                              } while (!paramString.equals("layout/fragment_device_0"));
                                              return 2131427423;
                                            } while (!paramString.equals("layout/layout_menu_main_text_0"));
                                            return 2131427487;
                                          } while (!paramString.equals("layout/item_play_project_0"));
                                          return 2131427438;
                                        } while (!paramString.equals("layout/layout_menu_text_0"));
                                        return 2131427489;
                                      } while (!paramString.equals("layout/activity_laboratory_panel_0"));
                                      return 2131427370;
                                    } while (!paramString.equals("layout/activity_play_0"));
                                    return 2131427382;
                                  } while (!paramString.equals("layout/activity_create_0"));
                                  return 2131427363;
                                } while (!paramString.equals("layout/activity_navigation_0"));
                                return 2131427380;
                              } while (!paramString.equals("layout/widget_air_dash_board_0"));
                              return 2131427534;
                            } while (!paramString.equals("layout/layout_playground_airblock_top_bar_0"));
                            return 2131427494;
                          } while (!paramString.equals("layout/activity_main_3_1_0"));
                          return 2131427374;
                        } while (!paramString.equals("layout/activity_airblock_drone_playground_0"));
                        return 2131427357;
                      } while (!paramString.equals("layout/item_laboratory_0"));
                      return 2131427435;
                    } while (!paramString.equals("layout/activity_airblock_land_playground_0"));
                    return 2131427358;
                  } while (!paramString.equals("layout/activity_panel_0"));
                  return 2131427381;
                } while (!paramString.equals("layout/item_menu_search_device_0"));
                return 2131427437;
              } while (!paramString.equals("layout/item_device_small_0"));
              return 2131427434;
            } while (!paramString.equals("layout/activity_ranger_mbot_speaker_0"));
            return 2131427387;
          } while (!paramString.equals("layout/fragment_codey_program_update_0"));
          return 2131427422;
        } while (!paramString.equals("layout/activity_main_3_2_0"));
        return 2131427375;
      } while (!paramString.equals("layout/layout_menu_search_header_0"));
      return 2131427488;
    } while (!paramString.equals("layout/fragment_navigation_settings_0"));
    return 2131427427;
  }
  
  private static class InnerBrLookup
  {
    static String[] sKeys = { "_all", "arrowDirection", "batteryPercent", "boardGroupBeanList", "buttonVisibility", "checkUpdatesButtonVisibility", "chooseLayoutVisibility", "closeDeviceVisibility", "connected", "connectedBoardName", "connectedFirmwareVersion", "connectingLayoutVisibility", "connectingStateTextId", "correctResultTextVisibility", "currentSelectedDevice", "deletable", "description", "designMode", "deviceChecked", "deviceIcon", "deviceItem", "deviceName", "devicePic", "errorLayoutVisibility", "errorTextId", "failLayoutVisibility", "finishTextVisibility", "formChangeable", "formText", "heightDegrees", "heightProgress", "heightText", "helpChecked", "horizontalJoystickPercent", "initiatingLayoutVisibility", "isLand", "isPowerOn", "listeningTextVisibility", "loadingFromNetTextVisibility", "loadingGifVisibility", "mascotShadowVisibility", "moveJoystickPercent", "name", "nameChangeable", "needClearMenu", "newVersionText", "newVersionTextVisibility", "noInternetTextVisibility", "noPermissionTextVisibility", "panelBackground", "passedIndex", "pause", "playMode", "portSelectable", "processingTextVisibility", "programmable", "projectItemName", "recognizedText", "robotImageId", "searchButtonVisibility", "settingsChecked", "shouldCancelSkills", "showBack", "showCodePanel", "showExpand", "showMenu", "showPlay", "signalPercent", "speakButtonBackground", "speed", "startCooling", "startUpdateButtonVisibility", "tuneButtonVisibility", "turnJoystickPercent", "unrecognizeTextVisibility", "upToDateTextVisibility", "updatingProgressFloat", "updatingProgressText", "verticalJoystickPercent", "viewModel", "waitAddItems", "waitingTextVisibility", "wavePlayAnimation", "widgetDataList", "widgetListOnState", "widgetPopupTitle", "widgets" };
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\DataBinderMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */