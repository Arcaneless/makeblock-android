package cc.makeblock.makeblock.project;

import android.content.Context;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.bean.WidgetData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ProjectStoreUtils
{
  public static final String CP_NAME_DEFAULT = App.getContext().getString(2131493321);
  private static final String TAG = "ProjectStoreUtils";
  
  public static WidgetData findCurrentWidgetBean(ProjectBean paramProjectBean, int paramInt)
  {
    paramProjectBean = paramProjectBean.getWidgets().iterator();
    while (paramProjectBean.hasNext())
    {
      WidgetData localWidgetData = (WidgetData)paramProjectBean.next();
      if (paramInt == localWidgetData.widgetID) {
        return localWidgetData;
      }
    }
    return null;
  }
  
  public static ArrayList<ProjectBean> query(Context paramContext, String paramString)
  {
    return DAO.getInstance(paramContext).query(paramString);
  }
  
  public static void saveProjectData(Context paramContext, ProjectBean paramProjectBean)
  {
    if (((paramProjectBean.isOfficial & 0x1) == 0) && (paramProjectBean.screenshot == null))
    {
      int i = new Random().nextInt(4);
      paramProjectBean.screenshot = ("settings/MakeblockAppResource/OfficialControlPanels/cover-default-Random - " + (i + 1) + ".png");
    }
    DAO.getInstance(paramContext).insert(paramProjectBean);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\project\ProjectStoreUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */