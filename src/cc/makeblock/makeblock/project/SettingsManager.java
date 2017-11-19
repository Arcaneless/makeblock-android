package cc.makeblock.makeblock.project;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.bean.BoardGroupBean;
import cc.makeblock.makeblock.bean.EnumXibName;
import cc.makeblock.makeblock.bean.ExpandGuideData;
import cc.makeblock.makeblock.bean.WidgetBean;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SettingsManager
{
  public static final String EXPAND_ASSET_FOLDER = "settings/MakeblockAppResource/Expand";
  public static final String PLAYGROUND_LIST_FILE = "settings/playgrounds.json";
  private static final String TAG = "SettingsManager";
  private static Map<String, List<BoardGroupBean>> boardGroupBeansMap = null;
  private static ArrayList<ProjectBean> officialCpBeans = new ArrayList();
  private static HashMap<String, ArrayList<ProjectBean>> officialCpMap = new HashMap();
  private static Map<String, WidgetBean> widgetBeans = null;
  
  public static List<BoardGroupBean> getBoardGroupBeans(String paramString)
  {
    if (boardGroupBeansMap == null)
    {
      loadOfficialProjectBeans(App.getContext());
      loadPanelBeans(App.getContext());
    }
    if (boardGroupBeansMap.get(paramString) != null) {
      return (List)boardGroupBeansMap.get(paramString);
    }
    return (List)boardGroupBeansMap.get("Default");
  }
  
  public static ExpandGuideData getExpandGuideData(Context paramContext, String paramString)
  {
    Gson localGson = new Gson();
    paramContext = readAssetTextFile(paramContext, "settings/MakeblockAppResource/Expand" + paramString + ".json");
    if (TextUtils.isEmpty(paramContext)) {
      return null;
    }
    paramContext = (ExpandGuideData)localGson.fromJson(paramContext, ExpandGuideData.class);
    wrapExpandGuideData(paramContext);
    return paramContext;
  }
  
  public static ArrayList<WidgetData> getGroupWidgetBeans(String paramString, BoardGroupBean paramBoardGroupBean)
  {
    ArrayList localArrayList = new ArrayList(paramBoardGroupBean.widgetsList.size());
    paramBoardGroupBean = paramBoardGroupBean.widgetsList.iterator();
    while (paramBoardGroupBean.hasNext())
    {
      String str = (String)paramBoardGroupBean.next();
      localArrayList.add(new WidgetData(paramString, (WidgetBean)widgetBeans.get(str)));
    }
    return localArrayList;
  }
  
  public static List<ProjectBean> getOfficialCpBeans(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return officialCpBeans;
    }
    return (List)officialCpMap.get(paramString);
  }
  
  public static List<ProjectBean> getOfficialCps(String paramString, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = (ArrayList)officialCpMap.get(paramString);
    if (paramString == null) {}
    for (;;)
    {
      return localArrayList;
      int j = paramString.size();
      int i = 0;
      while (i < j)
      {
        ProjectBean localProjectBean = (ProjectBean)paramString.get(i);
        if (localProjectBean.isOfficial == paramInt) {
          localArrayList.add(localProjectBean);
        }
        i += 1;
      }
    }
  }
  
  public static List<Playgrounds.Playground> getPlaygrounds(String paramString, int paramInt)
  {
    paramString = readAssetTextFile(App.getContext(), "settings/playground_" + paramString + ".json");
    paramString = (Playgrounds)new Gson().fromJson(paramString, Playgrounds.class);
    if (paramInt == 1) {
      return paramString.play;
    }
    if (paramInt == 3) {
      return paramString.expand;
    }
    return null;
  }
  
  public static ProjectBean getProjectBean(String paramString1, String paramString2)
  {
    if (officialCpMap.size() <= 0)
    {
      loadOfficialProjectBeans(App.getContext());
      loadPanelBeans(App.getContext());
    }
    Object localObject = null;
    List localList = getOfficialCpBeans(paramString1);
    if (localList == null) {
      return null;
    }
    int j = localList.size();
    int i = 0;
    for (;;)
    {
      paramString1 = (String)localObject;
      if (i < j)
      {
        paramString1 = (ProjectBean)localList.get(i);
        if (!paramString2.equals(paramString1.nameKey)) {}
      }
      else
      {
        return paramString1;
      }
      i += 1;
    }
  }
  
  public static void loadAdInfo(Context paramContext)
  {
    if (SharedPreferencesUtils.getAdInfo() == null) {
      SharedPreferencesUtils.setAdInfoJson(readAssetTextFile(paramContext, "settings/adInfo.json"));
    }
  }
  
  private static void loadOfficialProjectBeans(Context paramContext)
  {
    if (officialCpMap.size() > 0) {
      return;
    }
    ArrayList localArrayList = parseXml(readAssetTextFile(paramContext, "settings/MakeblockAppResource/OfficialControlPanels/officialControlPanels.plist"));
    int i = 0;
    label22:
    Object localObject1;
    if (i < localArrayList.size())
    {
      Object localObject2 = ((String)localArrayList.get(i)).trim();
      localObject1 = "workspace-" + (String)localObject2 + ".json";
      localObject2 = "cover-program-" + (String)localObject2 + ".png";
      localObject1 = (ProjectBean)new Gson().fromJson(readAssetTextFile(paramContext, "settings/MakeblockAppResource/OfficialControlPanels/" + (String)localObject1), ProjectBean.class);
      ((ProjectBean)localObject1).screenshot = ("settings/MakeblockAppResource/OfficialControlPanels/" + (String)localObject2);
      localObject2 = TextUtil.getStringByKey(((ProjectBean)localObject1).name);
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        ((ProjectBean)localObject1).nameKey = ((ProjectBean)localObject1).name;
        ((ProjectBean)localObject1).name = ((String)localObject2);
      }
      int j = 0;
      while (j < ((ProjectBean)localObject1).getWidgets().size())
      {
        localObject2 = (WidgetData)((ProjectBean)localObject1).getWidgets().get(j);
        if (!TextUtils.isEmpty(((WidgetData)localObject2).icon))
        {
          String str = TextUtil.getStringByKey(((WidgetData)localObject2).name);
          if (!TextUtils.isEmpty(str)) {
            ((WidgetData)localObject2).name = str;
          }
          str = ((WidgetData)localObject2).icon;
          ((WidgetData)localObject2).icon = ("settings/widgetIcon/" + ((WidgetData)localObject2).xibName + "/" + str + ".png");
          if (EnumXibName.MBWIconButton.toString().equals(((WidgetData)localObject2).xibName)) {
            ((WidgetData)localObject2).icon_pre = ("settings/widgetIcon/" + ((WidgetData)localObject2).xibName + "/" + str + "-pre.png");
          }
          if (EnumXibName.MBWLineGraph.toString().equals(((WidgetData)localObject2).xibName)) {
            ((WidgetData)localObject2).icon = ("settings/widgetIcon/" + ((WidgetData)localObject2).xibName + "/" + str + "-huge.png");
          }
        }
        j += 1;
      }
      officialCpBeans.add(localObject1);
      if (officialCpMap.get(((ProjectBean)localObject1).boardName) != null) {
        break label502;
      }
      localObject2 = new ArrayList();
      ((ArrayList)localObject2).add(localObject1);
      officialCpMap.put(((ProjectBean)localObject1).boardName, localObject2);
    }
    for (;;)
    {
      i += 1;
      break label22;
      break;
      label502:
      ((ArrayList)officialCpMap.get(((ProjectBean)localObject1).boardName)).add(localObject1);
    }
  }
  
  private static void loadPanelBeans(Context paramContext)
  {
    if (boardGroupBeansMap != null) {}
    for (;;)
    {
      return;
      Object localObject2 = readAssetTextFile(paramContext, "settings/MakeblockAppResource/Toolbox/toolboxList.json");
      Object localObject1 = new Gson();
      boardGroupBeansMap = (Map)((Gson)localObject1).fromJson((String)localObject2, new TypeToken() {}.getType());
      localObject2 = boardGroupBeansMap.entrySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Object localObject3 = (Map.Entry)((Iterator)localObject2).next();
        List localList = (List)((Map.Entry)localObject3).getValue();
        localObject3 = (String)((Map.Entry)localObject3).getKey();
        int i = 0;
        while (i < localList.size())
        {
          localObject3 = (BoardGroupBean)localList.get(i);
          ((BoardGroupBean)localObject3).groupName = TextUtil.getStringByKey(((BoardGroupBean)localObject3).groupName);
          ((BoardGroupBean)localObject3).icon_pre = ("settings/toolboxIcon/" + ((BoardGroupBean)localObject3).iconName + "-pre.png");
          ((BoardGroupBean)localObject3).icon = ("settings/toolboxIcon/" + ((BoardGroupBean)localObject3).iconName + ".png");
          i += 1;
        }
      }
      widgetBeans = (Map)((Gson)localObject1).fromJson(readAssetTextFile(paramContext, "settings/MakeblockAppResource/Toolbox/widgetsList.json"), new TypeToken() {}.getType());
      paramContext = widgetBeans.entrySet().iterator();
      while (paramContext.hasNext())
      {
        localObject1 = (WidgetBean)((Map.Entry)paramContext.next()).getValue();
        if (TextUtils.isEmpty(((WidgetBean)localObject1).name)) {
          ((WidgetBean)localObject1).name = ((WidgetBean)localObject1).type;
        }
        localObject2 = TextUtil.getStringByKey(((WidgetBean)localObject1).name);
        if ((!TextUtils.isEmpty(((WidgetBean)localObject1).name)) && (localObject2 != null)) {
          ((WidgetBean)localObject1).name = ((String)localObject2);
        }
        if (!TextUtils.isEmpty(((WidgetBean)localObject1).iconName))
        {
          localObject2 = ((WidgetBean)localObject1).iconName;
          ((WidgetBean)localObject1).icon = ("settings/widgetIcon/" + ((WidgetBean)localObject1).className + "/" + (String)localObject2 + ".png");
          if (EnumXibName.MBWIconButton.toString().equals(((WidgetBean)localObject1).className)) {
            ((WidgetBean)localObject1).icon_pre = ("settings/widgetIcon/" + ((WidgetBean)localObject1).className + "/" + (String)localObject2 + "-pre.png");
          }
          if (EnumXibName.MBWLineGraph.toString().equals(((WidgetBean)localObject1).className)) {
            ((WidgetBean)localObject1).icon = ("settings/widgetIcon/" + ((WidgetBean)localObject1).className + "/" + (String)localObject2 + "-huge.png");
          }
        }
        else
        {
          ((WidgetBean)localObject1).icon = ("settings/widgetIcon/" + ((WidgetBean)localObject1).className + "/" + "default" + ".png");
          if (EnumXibName.MBWIconButton.toString().equals(((WidgetBean)localObject1).className)) {
            ((WidgetBean)localObject1).icon_pre = ("settings/widgetIcon/" + ((WidgetBean)localObject1).className + "/" + "default" + "-pre.png");
          }
        }
      }
    }
  }
  
  public static void loadSettingsFile(Context paramContext)
  {
    loadOfficialProjectBeans(paramContext);
    loadPanelBeans(paramContext);
  }
  
  private static ArrayList<String> parseXml(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    for (;;)
    {
      int i;
      try
      {
        paramString = localDocumentBuilderFactory.newDocumentBuilder().parse(new InputSource(new StringReader(paramString))).getDocumentElement().getElementsByTagName("string");
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      if (i < paramString.getLength())
      {
        localArrayList.add(((Element)paramString.item(i)).getFirstChild().getNodeValue());
        i += 1;
      }
      else
      {
        return localArrayList;
        i = 0;
      }
    }
  }
  
  private static String readAssetTextFile(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getAssets().open(paramString);
      paramString = new byte[paramContext.available()];
      paramContext.read(paramString);
      paramContext.close();
      paramContext = new String(paramString);
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private static void wrapExpandGuideData(ExpandGuideData paramExpandGuideData)
  {
    paramExpandGuideData.formMsg = TextUtil.getStringByKey(paramExpandGuideData.formMsg);
    paramExpandGuideData.playMsg = TextUtil.getStringByKey(paramExpandGuideData.playMsg);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\project\SettingsManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */