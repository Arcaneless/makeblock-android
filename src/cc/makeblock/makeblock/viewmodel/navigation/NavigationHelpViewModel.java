package cc.makeblock.makeblock.viewmodel.navigation;

import android.databinding.BaseObservable;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import java.util.Locale;

public class NavigationHelpViewModel
  extends BaseObservable
{
  private NavigationHelpView mView;
  
  public NavigationHelpViewModel(NavigationHelpView paramNavigationHelpView)
  {
    this.mView = paramNavigationHelpView;
  }
  
  public void openCommunity()
  {
    StatisticsTool.getInstance().onEvent("OpenHelpCommunity", "打开帮助社区");
    String str = TextUtil.getStringById(2131493238);
    this.mView.openWebPage(str);
  }
  
  public void openQA()
  {
    StatisticsTool.getInstance().onEvent("OpenQA", "打开Q&A");
    String str = "http://us.makeblock.com/topic/114/how-do-i-ask-for-help";
    if (Locale.getDefault().getLanguage().equalsIgnoreCase("zh")) {
      str = "http://we.makeblock.com/topic/2/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98%E6%B1%87%E6%80%BB-%E6%AC%A2%E8%BF%8E%E6%8F%90%E9%97%AE";
    }
    this.mView.openWebPage(str);
  }
  
  public void openTutorial()
  {
    StatisticsTool.getInstance().onEvent("OpenTutorial", "打开新手引导");
    String str = SharedPreferencesUtils.getCurrentChooseDeviceBoardName();
    if (str != null) {
      switch (cc.makeblock.makeblock.engine.device.DeviceBoardName.valueOf(str))
      {
      default: 
        str = TextUtil.getStringById(2131493341);
      }
    }
    for (;;)
    {
      this.mView.openWebPage(str);
      return;
      str = TextUtil.getStringById(2131493342);
      continue;
      str = TextUtil.getStringById(2131493339);
      continue;
      str = TextUtil.getStringById(2131493345);
      continue;
      str = TextUtil.getStringById(2131493343);
      continue;
      str = TextUtil.getStringById(2131493340);
      continue;
      str = TextUtil.getStringById(2131493344);
      continue;
      str = TextUtil.getStringById(2131493341);
    }
  }
  
  public static abstract interface NavigationHelpView
  {
    public abstract void openWebPage(String paramString);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\navigation\NavigationHelpViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */