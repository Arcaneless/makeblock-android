package cc.makeblock.makeblock.engine.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.engine.net.data.AdInfoData;
import cc.makeblock.makeblock.engine.net.tutorial.TutorialInfo;
import com.google.gson.Gson;

public class SharedPreferencesUtils
{
  private static final String AD_INFO = "AD_INFO";
  private static final String AD_INFO_JSON = "AD_INFO_JSON";
  private static final String AD_INFO_NEED_DOWNLOAD = "AD_INFO_NEED_DOWNLOAD";
  private static final String AIRBLOCK_GUIDE_AIR = "AIRBLOCK_GUIDE_AIR";
  private static final String AIRBLOCK_GUIDE_LAND = "AIRBLOCK_GUIDE_LAND";
  private static final String AIRBLOCK_GUIDE_WATER = "AIRBLOCK_GUIDE_WATER";
  private static final String AIRBLOCK_OFFSET_ANGLE = "AIRBLOCK_OFFSET_ANGLE";
  private static final String APPLICATION_NEW_VERSION = "APPLICATION_NEW_VERSION";
  private static final String AUTO_CONNECTION_SETTING = "AUTO_CONNECTION_SETTING";
  private static final String EXPAND_GUIDE = "expand_guide";
  private static final String HAS_SHOW_AIRBLOCK_CAR_GUIDE = "HAS_SHOW_AIRBLOCK_CAR_GUIDE";
  private static final String HAS_SHOW_AIRBLOCK_DRONE_GUIDE = "HAS_SHOW_AIRBLOCK_DRONE_GUIDE";
  private static final String HAS_SHOW_AIRBLOCK_SHIP_GUIDE = "HAS_SHOW_AIRBLOCK_SHIP_GUIDE";
  private static final String HAS_SHOW_BLOCKLY_GUIDE = "HAS_SHOW_BLOCKLY_GUIDE";
  private static final String HAS_SHOW_DRAW_AND_RUN_GUIDE = "HAS_SHOW_DRAW_AND_RUN_GUIDE";
  private static final String HAS_SHOW_MAIN_GUIDE = "HAS_SHOW_MAIN_GUIDE";
  private static final String HAS_SHOW_PANEL_EDIT_GUIDE = "HAS_SHOW_PANEL_EDIT_GUIDE";
  private static final String HAS_SHOW_SPEAKER_GUIDE = "HAS_SHOW_SPEAKER_GUIDE";
  private static final String LAST_SUCCESS_ADDRESS = "LAST_SUCCESS_ADDRESS";
  private static final String SHOW_CODEY = "SHOW_CODEY";
  private static final String SHOW_SMART_SERVO = "SHOW_SMART_SERVO";
  private static final String TUTORIAL_INFO = "TUTORIAL_INFO";
  private static final String USER_CHOOSE_DEVICE_BOARD_NAME = "USER_CHOOSE_DEVICE_BOARD_NAME";
  private static final String USER_CHOOSE_MODE_GUIDE = "USER_CHOOSE_MODE_GUIDE";
  private static final String USER_GUIDE_3 = "USER_GUIDE_3";
  private static final String USER_INFO = "USER_INFO";
  private static final String USER_START_DESIGN_GUIDE = "USER_START_DESIGN_GUIDE";
  
  public static AdInfoData getAdInfo()
  {
    AdInfoData localAdInfoData = null;
    String str = getAdInfoSp().getString("AD_INFO_JSON", null);
    if (str != null) {
      localAdInfoData = (AdInfoData)new Gson().fromJson(str, AdInfoData.class);
    }
    return localAdInfoData;
  }
  
  private static SharedPreferences getAdInfoSp()
  {
    return App.getContext().getSharedPreferences("AD_INFO", 0);
  }
  
  public static String getAirblockOffsetAngle(String paramString)
  {
    return getUserInfoSP().getString("AIRBLOCK_OFFSET_ANGLE" + paramString, null);
  }
  
  public static int getApplicationNewVersion()
  {
    return App.getContext().getSharedPreferences("APPLICATION_NEW_VERSION", 0).getInt("APPLICATION_NEW_VERSION", 0);
  }
  
  private static boolean getBooleanByKey(String paramString, boolean paramBoolean)
  {
    return getUserInfoSP().getBoolean(paramString, paramBoolean);
  }
  
  public static String getCurrentChooseDeviceBoardName()
  {
    return getUserInfoSP().getString("USER_CHOOSE_DEVICE_BOARD_NAME", "");
  }
  
  private static SharedPreferences getExpandGuideSp()
  {
    return App.getContext().getSharedPreferences("expand_guide", 0);
  }
  
  public static boolean getHasShowAirblockCarGuide()
  {
    return getUserGuide3SP().getBoolean("HAS_SHOW_AIRBLOCK_CAR_GUIDE", false);
  }
  
  public static boolean getHasShowAirblockDroneGuide()
  {
    return getUserGuide3SP().getBoolean("HAS_SHOW_AIRBLOCK_DRONE_GUIDE", false);
  }
  
  public static boolean getHasShowAirblockShipGuide()
  {
    return getUserGuide3SP().getBoolean("HAS_SHOW_AIRBLOCK_SHIP_GUIDE", false);
  }
  
  public static boolean getHasShowBlocklyGuide()
  {
    return getUserGuide3SP().getBoolean("HAS_SHOW_BLOCKLY_GUIDE", false);
  }
  
  public static boolean getHasShowDrawAndRunGuide(String paramString)
  {
    return App.getContext().getSharedPreferences("HAS_SHOW_DRAW_AND_RUN_GUIDE", 0).getBoolean(paramString, false);
  }
  
  public static boolean getHasShowMainGuide()
  {
    return getUserGuide3SP().getBoolean("HAS_SHOW_MAIN_GUIDE", false);
  }
  
  public static boolean getHasShowPanelEditGuide()
  {
    return getUserGuide3SP().getBoolean("HAS_SHOW_PANEL_EDIT_GUIDE", false);
  }
  
  public static boolean getHasShowSpeakerGuide(String paramString)
  {
    return App.getContext().getSharedPreferences("HAS_SHOW_SPEAKER_GUIDE", 0).getBoolean(paramString, false);
  }
  
  public static String getLastSuccessAddress()
  {
    return getUserInfoSP().getString("LAST_SUCCESS_ADDRESS", null);
  }
  
  public static boolean getNeedDownloadAdPic()
  {
    return getAdInfoSp().getBoolean("AD_INFO_NEED_DOWNLOAD", false);
  }
  
  public static boolean getShowCodey()
  {
    return App.getContext().getSharedPreferences("USER_INFO", 0).getBoolean("SHOW_CODEY", false);
  }
  
  public static boolean getShowSmartServo()
  {
    return App.getContext().getSharedPreferences("USER_INFO", 0).getBoolean("SHOW_SMART_SERVO", false);
  }
  
  public static TutorialInfo getTutorialInfo()
  {
    return (TutorialInfo)new Gson().fromJson(getTutorialInfoSp().getString("TUTORIAL_INFO", ""), TutorialInfo.class);
  }
  
  private static SharedPreferences getTutorialInfoSp()
  {
    return App.getContext().getSharedPreferences("TUTORIAL_INFO", 0);
  }
  
  private static SharedPreferences getUserGuide3SP()
  {
    return App.getContext().getSharedPreferences("USER_GUIDE_3", 0);
  }
  
  private static SharedPreferences getUserInfoSP()
  {
    return App.getContext().getSharedPreferences("USER_INFO", 0);
  }
  
  public static boolean hasShowAirblockGuideAir()
  {
    return getBooleanByKey("AIRBLOCK_GUIDE_AIR", false);
  }
  
  public static boolean hasShowAirblockGuideLand()
  {
    return getBooleanByKey("AIRBLOCK_GUIDE_LAND", false);
  }
  
  public static boolean hasShowAirblockGuideWater()
  {
    return getBooleanByKey("AIRBLOCK_GUIDE_WATER", false);
  }
  
  public static boolean hasShowStartDesignGuide()
  {
    return getBooleanByKey("USER_START_DESIGN_GUIDE", false);
  }
  
  public static boolean isAutoConnectionEnable()
  {
    return getUserInfoSP().getBoolean("AUTO_CONNECTION_SETTING", false);
  }
  
  public static boolean isShowExpandGuide(String paramString)
  {
    return getExpandGuideSp().getBoolean(paramString, false);
  }
  
  public static void setAdInfo(AdInfoData paramAdInfoData)
  {
    setAdInfoJson(new Gson().toJson(paramAdInfoData));
  }
  
  public static void setAdInfoJson(String paramString)
  {
    getAdInfoSp().edit().putString("AD_INFO_JSON", paramString).commit();
  }
  
  public static void setAirblockOffsetAngle(String paramString1, String paramString2)
  {
    getUserInfoSP().edit().putString("AIRBLOCK_OFFSET_ANGLE" + paramString1, paramString2).commit();
  }
  
  public static void setApplicationNewVersion(int paramInt)
  {
    App.getContext().getSharedPreferences("APPLICATION_NEW_VERSION", 0).edit().putInt("APPLICATION_NEW_VERSION", paramInt).apply();
  }
  
  public static void setAutoConnectionEnable(boolean paramBoolean)
  {
    getUserInfoSP().edit().putBoolean("AUTO_CONNECTION_SETTING", paramBoolean).commit();
  }
  
  private static void setBooleanByKey(String paramString, boolean paramBoolean)
  {
    getUserInfoSP().edit().putBoolean(paramString, paramBoolean).commit();
  }
  
  public static void setChosenBoardName(String paramString)
  {
    getUserInfoSP().edit().putString("USER_CHOOSE_DEVICE_BOARD_NAME", paramString).apply();
  }
  
  public static void setExpandGuide(String paramString, boolean paramBoolean)
  {
    getExpandGuideSp().edit().putBoolean(paramString, paramBoolean).apply();
  }
  
  public static void setHasShowAirblockCarGuide(boolean paramBoolean)
  {
    getUserGuide3SP().edit().putBoolean("HAS_SHOW_AIRBLOCK_CAR_GUIDE", paramBoolean).apply();
  }
  
  public static void setHasShowAirblockDroneGuide(boolean paramBoolean)
  {
    getUserGuide3SP().edit().putBoolean("HAS_SHOW_AIRBLOCK_DRONE_GUIDE", paramBoolean).apply();
  }
  
  public static void setHasShowAirblockShipGuide(boolean paramBoolean)
  {
    getUserGuide3SP().edit().putBoolean("HAS_SHOW_AIRBLOCK_SHIP_GUIDE", paramBoolean).apply();
  }
  
  public static void setHasShowBlocklyGuide(boolean paramBoolean)
  {
    getUserGuide3SP().edit().putBoolean("HAS_SHOW_BLOCKLY_GUIDE", paramBoolean).apply();
  }
  
  public static void setHasShowDrawAndRunGuide(String paramString)
  {
    App.getContext().getSharedPreferences("HAS_SHOW_DRAW_AND_RUN_GUIDE", 0).edit().putBoolean(paramString, true).apply();
  }
  
  public static void setHasShowMainGuide(boolean paramBoolean)
  {
    getUserGuide3SP().edit().putBoolean("HAS_SHOW_MAIN_GUIDE", paramBoolean).apply();
  }
  
  public static void setHasShowPanelEditGuide(boolean paramBoolean)
  {
    getUserGuide3SP().edit().putBoolean("HAS_SHOW_PANEL_EDIT_GUIDE", paramBoolean).apply();
  }
  
  public static void setHasShowSpeakerGuide(String paramString)
  {
    App.getContext().getSharedPreferences("HAS_SHOW_SPEAKER_GUIDE", 0).edit().putBoolean(paramString, true).apply();
  }
  
  public static void setLastSuccessAddress(String paramString)
  {
    getUserInfoSP().edit().putString("LAST_SUCCESS_ADDRESS", paramString).commit();
  }
  
  public static void setNeedDownloadAdPic(boolean paramBoolean)
  {
    getAdInfoSp().edit().putBoolean("AD_INFO_NEED_DOWNLOAD", paramBoolean).commit();
  }
  
  public static void setShowAirblockGuideAir()
  {
    setBooleanByKey("AIRBLOCK_GUIDE_AIR", true);
  }
  
  public static void setShowAirblockGuideLand()
  {
    setBooleanByKey("AIRBLOCK_GUIDE_LAND", true);
  }
  
  public static void setShowAirblockGuideWater()
  {
    setBooleanByKey("AIRBLOCK_GUIDE_WATER", true);
  }
  
  public static void setShowCodey(boolean paramBoolean)
  {
    App.getContext().getSharedPreferences("USER_INFO", 0).edit().putBoolean("SHOW_CODEY", paramBoolean).apply();
  }
  
  public static void setShowSmartServo(boolean paramBoolean)
  {
    App.getContext().getSharedPreferences("USER_INFO", 0).edit().putBoolean("SHOW_SMART_SERVO", paramBoolean).apply();
  }
  
  public static void setShowStartDesignGuide()
  {
    setBooleanByKey("USER_START_DESIGN_GUIDE", true);
  }
  
  public static void setTutorialInfo(TutorialInfo paramTutorialInfo)
  {
    if (paramTutorialInfo == null)
    {
      getTutorialInfoSp().edit().clear().apply();
      return;
    }
    getTutorialInfoSp().edit().putString("TUTORIAL_INFO", new Gson().toJson(paramTutorialInfo)).apply();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\SharedPreferencesUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */