package cc.makeblock.makeblock.bean;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.ArrayList;

public class GroupBean
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public String name;
  private ArrayList<WidgetData> widgets;
  
  public ArrayList<WidgetData> getWidgets()
  {
    return this.widgets;
  }
  
  public ArrayList<WidgetData> getWidgets(String paramString)
  {
    Object localObject;
    if ((this.widgets == null) || (this.widgets.size() == 0) || (TextUtils.isEmpty(paramString))) {
      localObject = null;
    }
    ArrayList localArrayList;
    int i;
    do
    {
      return (ArrayList<WidgetData>)localObject;
      localArrayList = new ArrayList();
      i = 0;
      localObject = localArrayList;
    } while (i >= this.widgets.size());
    if (TextUtils.isEmpty(((WidgetData)this.widgets.get(i)).boardName)) {
      localArrayList.add(this.widgets.get(i));
    }
    for (;;)
    {
      i += 1;
      break;
      if (((WidgetData)this.widgets.get(i)).boardName.toLowerCase().contains(paramString.toLowerCase())) {
        localArrayList.add(this.widgets.get(i));
      }
    }
  }
  
  public String toString()
  {
    return "GroupBean [name=" + this.name + ", widgets=" + this.widgets + "]";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\bean\GroupBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */