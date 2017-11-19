package cc.makeblock.makeblock.project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.engine.utils.FileUtils;
import cc.makeblock.makeblock.engine.utils.JsonUtil;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class ProjectBean
  implements Serializable
{
  public static final int IS_CP_BETA = 4;
  public static final int IS_CP_CUSTOM_IS_OFFICIAL_VALUE = 0;
  public static final int IS_CP_EXTEND = 2;
  public static final int IS_CP_OFFICIAL = 1;
  public static final int IS_CP_OFFICIAL_EXTEND = 3;
  private static final String TAG = "ProjectBean";
  private static final long serialVersionUID = 1L;
  public String boardName;
  public String buildName;
  public String codeSheet;
  public int id;
  public int isOfficial = 0;
  public String name = "";
  public String nameKey = null;
  public String productName;
  public String robotForm;
  public String screenshot;
  private ArrayList<WidgetData> widgets = new ArrayList();
  
  public ProjectBean deepCopy()
  {
    ProjectBean localProjectBean = new ProjectBean();
    localProjectBean.id = this.id;
    localProjectBean.screenshot = this.screenshot;
    localProjectBean.name = this.name;
    localProjectBean.codeSheet = this.codeSheet;
    localProjectBean.buildName = this.buildName;
    localProjectBean.productName = this.productName;
    localProjectBean.robotForm = this.robotForm;
    localProjectBean.isOfficial = this.isOfficial;
    localProjectBean.boardName = this.boardName;
    localProjectBean.nameKey = this.nameKey;
    int i = 0;
    while (i < this.widgets.size())
    {
      localProjectBean.widgets.add(((WidgetData)this.widgets.get(i)).deepCopy());
      i += 1;
    }
    return localProjectBean;
  }
  
  public String getScreenshotUri()
  {
    return "file:///android_asset/" + this.screenshot;
  }
  
  public ArrayList<WidgetData> getWidgets()
  {
    return this.widgets;
  }
  
  public String getWidgetsJson()
  {
    if ((this.widgets == null) || (this.widgets.size() == 0)) {
      return "";
    }
    return JsonUtil.objectToJson(this.widgets);
  }
  
  public void setScreenshot(Context paramContext, View paramView)
  {
    if ((getWidgets() == null) || (getWidgets().size() == 0))
    {
      if (!TextUtils.isEmpty(this.screenshot))
      {
        new File(this.screenshot).deleteOnExit();
        this.screenshot = null;
      }
      return;
    }
    if (!TextUtils.isEmpty(this.screenshot)) {
      new File(this.screenshot).deleteOnExit();
    }
    int i = Math.max(paramView.getWidth(), paramView.getHeight());
    int j = Math.min(paramView.getWidth(), paramView.getHeight());
    Object localObject1 = new BitmapFactory.Options();
    ((BitmapFactory.Options)localObject1).inPreferredConfig = Bitmap.Config.RGB_565;
    paramContext = BitmapFactory.decodeResource(paramContext.getResources(), 2131165284, (BitmapFactory.Options)localObject1);
    localObject1 = Bitmap.createScaledBitmap(paramContext, i, i, true);
    Object localObject2 = new Canvas((Bitmap)localObject1);
    ((Canvas)localObject2).translate(0.0F, (i - j) / 2);
    paramView.draw((Canvas)localObject2);
    paramView = Bitmap.createScaledBitmap((Bitmap)localObject1, i / 3, i / 3, true);
    localObject2 = FileUtils.saveBitmapToSDCard(paramView, System.currentTimeMillis() + ".png");
    if (!TextUtils.isEmpty((CharSequence)localObject2)) {}
    for (this.screenshot = ((String)localObject2);; this.screenshot = null)
    {
      paramContext.recycle();
      ((Bitmap)localObject1).recycle();
      paramView.recycle();
      return;
    }
  }
  
  public void setWidgets(ArrayList<WidgetData> paramArrayList)
  {
    this.widgets = paramArrayList;
  }
  
  public void setWidgetsJson(String paramString)
  {
    this.widgets = JsonUtil.jsonToObjectArray(paramString);
  }
  
  public String toString()
  {
    return "ProjectBean [id=" + this.id + ", name=" + this.name + ", widgets=" + this.widgets + ", buildName=" + this.buildName + ", productName=" + this.productName + ", robotForm=" + this.robotForm + ", isOfficial=" + this.isOfficial + ", boardName=" + this.boardName + "]";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\project\ProjectBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */