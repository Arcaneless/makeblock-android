package cc.makeblock.makeblock.engine.utils;

import android.util.Log;
import cc.makeblock.makeblock.bean.PanelBean;
import cc.makeblock.makeblock.bean.WidgetData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil
{
  private static final String TAG = "JsonUtil";
  
  public static String jsonToCommandText4Speaker(String paramString)
  {
    Object localObject2 = "";
    Object localObject1 = localObject2;
    for (;;)
    {
      int i;
      try
      {
        localJSONArray = new JSONObject(paramString).getJSONArray("ws");
      }
      catch (JSONException paramString)
      {
        JSONArray localJSONArray;
        paramString.printStackTrace();
        Log.e("JsonUtil", "json parsing exception");
        localObject2 = localObject1;
      }
      localObject1 = paramString;
      localObject2 = paramString;
      if (i < localJSONArray.length())
      {
        localObject1 = paramString;
        localObject2 = localJSONArray.getJSONObject(i).getJSONArray("cw").getJSONObject(0).getString("w");
        localObject1 = paramString;
        paramString = paramString + (String)localObject2;
        localObject1 = paramString;
        Log.e("JsonUtil", paramString);
        i += 1;
      }
      else
      {
        return (String)localObject2;
        i = 0;
        paramString = (String)localObject2;
      }
    }
  }
  
  public static <T> T jsonToObject(String paramString, Type paramType)
  {
    try
    {
      paramString = new Gson().fromJson(paramString, paramType);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static ArrayList<WidgetData> jsonToObjectArray(String paramString)
  {
    Object localObject = new JsonParser().parse(paramString);
    paramString = null;
    if (((JsonElement)localObject).isJsonArray()) {
      paramString = ((JsonElement)localObject).getAsJsonArray();
    }
    localObject = new ArrayList();
    paramString = paramString.iterator();
    while (paramString.hasNext())
    {
      JsonElement localJsonElement = (JsonElement)paramString.next();
      ((ArrayList)localObject).add((WidgetData)new Gson().fromJson(localJsonElement, WidgetData.class));
    }
    return (ArrayList<WidgetData>)localObject;
  }
  
  public static ArrayList<PanelBean> jsonToObjectArray4Setting(String paramString)
  {
    Object localObject = new JsonParser().parse(paramString);
    paramString = null;
    if (((JsonElement)localObject).isJsonArray()) {
      paramString = ((JsonElement)localObject).getAsJsonArray();
    }
    localObject = new ArrayList();
    paramString = paramString.iterator();
    while (paramString.hasNext())
    {
      JsonElement localJsonElement = (JsonElement)paramString.next();
      ((ArrayList)localObject).add((PanelBean)new Gson().fromJson(localJsonElement, PanelBean.class));
    }
    return (ArrayList<PanelBean>)localObject;
  }
  
  public static String objectToJson(Object paramObject)
  {
    return new Gson().toJson(paramObject);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\JsonUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */