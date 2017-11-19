package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class at
{
  private static final String a = "fs_lc_tl";
  private final int b = 128;
  private final int c = 256;
  private final int d = 10;
  private Context e;
  private ar f = null;
  private aq g = null;
  private JSONObject h = null;
  private ar i;
  
  public at(Context paramContext)
  {
    if (paramContext == null) {}
    try
    {
      by.e("Context is null, can't track event");
      this.i = ar.b(paramContext);
      this.e = paramContext;
      this.f = ar.b(this.e);
      this.g = this.f.a(this.e);
      if (this.h == null) {
        a(paramContext);
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private JSONObject a(Map<String, Object> paramMap)
  {
    JSONObject localJSONObject = new JSONObject();
    Object localObject;
    label58:
    label80:
    ArrayList localArrayList;
    int j;
    for (;;)
    {
      label120:
      try
      {
        paramMap = paramMap.entrySet().iterator();
        boolean bool = paramMap.hasNext();
        if (!bool) {
          break label169;
        }
        try
        {
          localObject = (Map.Entry)paramMap.next();
          str = (String)((Map.Entry)localObject).getKey();
        }
        catch (Exception localException)
        {
          String str;
          by.e(localException);
        }
        str = bw.a(str, 128);
        localObject = ((Map.Entry)localObject).getValue();
      }
      catch (Exception paramMap) {}
      if (!localObject.getClass().isArray()) {
        break label670;
      }
      if ((localObject instanceof int[]))
      {
        localObject = (int[])localObject;
        localArrayList = new ArrayList();
        j = 0;
        if (j < localObject.length)
        {
          localArrayList.add(Integer.valueOf(localObject[j]));
          break label729;
        }
        localJSONObject.put(str, localArrayList);
        continue;
        continue;
        label169:
        return localJSONObject;
      }
      else if ((localObject instanceof double[]))
      {
        localObject = (double[])localObject;
        localArrayList = new ArrayList();
        j = 0;
        label201:
        if (j < localObject.length)
        {
          localArrayList.add(Double.valueOf(localObject[j]));
          break label736;
        }
        localJSONObject.put(localException, localArrayList);
      }
      else if ((localObject instanceof long[]))
      {
        localObject = (long[])localObject;
        localArrayList = new ArrayList();
        j = 0;
        label268:
        if (j < localObject.length)
        {
          localArrayList.add(Long.valueOf(localObject[j]));
          break label743;
        }
        localJSONObject.put(localException, localArrayList);
      }
      else if ((localObject instanceof float[]))
      {
        localObject = (float[])localObject;
        localArrayList = new ArrayList();
        j = 0;
        label335:
        if (j < localObject.length)
        {
          localArrayList.add(Float.valueOf(localObject[j]));
          break label750;
        }
        localJSONObject.put(localException, localArrayList);
      }
      else if ((localObject instanceof boolean[]))
      {
        localObject = (boolean[])localObject;
        localArrayList = new ArrayList();
        j = 0;
        label402:
        if (j < localObject.length)
        {
          localArrayList.add(Boolean.valueOf(localObject[j]));
          break label757;
        }
        localJSONObject.put(localException, localArrayList);
      }
      else if ((localObject instanceof byte[]))
      {
        localObject = (byte[])localObject;
        localArrayList = new ArrayList();
        j = 0;
        label469:
        if (j < localObject.length)
        {
          localArrayList.add(Byte.valueOf(localObject[j]));
          break label764;
        }
        localJSONObject.put(localException, localArrayList);
      }
      else
      {
        if (!(localObject instanceof short[])) {
          break;
        }
        localObject = (short[])localObject;
        localArrayList = new ArrayList();
        j = 0;
        label536:
        if (j < localObject.length)
        {
          localArrayList.add(Short.valueOf(localObject[j]));
          break label771;
        }
        localJSONObject.put(localException, localArrayList);
      }
    }
    if ((localObject instanceof char[]))
    {
      localObject = (char[])localObject;
      localArrayList = new ArrayList();
      j = 0;
    }
    for (;;)
    {
      if (j < localObject.length)
      {
        localArrayList.add(Character.valueOf(localObject[j]));
      }
      else
      {
        localJSONObject.put(localException, localArrayList);
        break;
        localJSONObject.put(localException, new ArrayList(Arrays.asList((Object[])localObject)));
        break;
        label670:
        if ((localObject instanceof String))
        {
          localJSONObject.put(localException, bw.a(localObject.toString(), 256));
          break;
        }
        localJSONObject.put(localException, localObject);
        break;
        if (localException == null) {
          break;
        }
        break label58;
        if (localObject == null) {
          break;
        }
        break label80;
        label729:
        j += 1;
        break label120;
        label736:
        j += 1;
        break label201;
        label743:
        j += 1;
        break label268;
        label750:
        j += 1;
        break label335;
        label757:
        j += 1;
        break label402;
        label764:
        j += 1;
        break label469;
        label771:
        j += 1;
        break label536;
      }
      j += 1;
    }
  }
  
  private void a()
  {
    int k = 0;
    int m = 0;
    for (;;)
    {
      int j;
      try
      {
        if (!TextUtils.isEmpty(this.g.a))
        {
          String[] arrayOfString = this.g.a.split("!");
          JSONObject localJSONObject = new JSONObject();
          if (this.h != null)
          {
            j = 0;
            if (j < arrayOfString.length)
            {
              String str = bw.a(arrayOfString[j], 128);
              if (!this.h.has(str)) {
                break label179;
              }
              localJSONObject.put(str, this.h.get(str));
              break label179;
            }
          }
          this.h = new JSONObject();
          j = m;
          if (arrayOfString.length >= 10)
          {
            j = k;
            break label186;
            a(arrayOfString[j], localJSONObject);
            break label195;
          }
          if (j < arrayOfString.length)
          {
            a(arrayOfString[j], localJSONObject);
            break label202;
          }
          b(this.e);
          this.g.a = null;
        }
        else
        {
          return;
        }
      }
      catch (Exception localException)
      {
        return;
      }
      label179:
      j += 1;
      continue;
      for (;;)
      {
        label186:
        if (j >= 10) {
          break label200;
        }
        break;
        label195:
        j += 1;
      }
      label200:
      continue;
      label202:
      j += 1;
    }
  }
  
  private void a(Context paramContext)
  {
    try
    {
      paramContext = ba.a(paramContext).getString("fs_lc_tl", null);
      if (!TextUtils.isEmpty(paramContext)) {
        this.h = new JSONObject(paramContext);
      }
      a();
      return;
    }
    catch (Exception paramContext) {}
  }
  
  private void a(String paramString, JSONObject paramJSONObject)
    throws JSONException
  {
    paramString = bw.a(paramString, 128);
    if (paramJSONObject.has(paramString))
    {
      a(paramString, ((Boolean)paramJSONObject.get(paramString)).booleanValue());
      return;
    }
    a(paramString, false);
  }
  
  private void a(String paramString, boolean paramBoolean)
  {
    try
    {
      if ((!"$st_fl".equals(paramString)) && (!"dplus_st".equals(paramString)) && (!"du".equals(paramString)) && (!"id".equals(paramString)) && (!"ts".equals(paramString)) && (!this.h.has(paramString))) {
        this.h.put(paramString, paramBoolean);
      }
      return;
    }
    catch (Exception paramString) {}
  }
  
  private boolean a(String paramString)
  {
    if (paramString != null) {}
    try
    {
      j = paramString.trim().getBytes().length;
    }
    catch (Exception paramString)
    {
      int j;
      do
      {
        for (;;) {}
      } while ((j <= 0) || (j > 128));
    }
    by.e("Event id is empty or too long in tracking Event");
    return false;
    return true;
  }
  
  private void b(Context paramContext)
  {
    try
    {
      if (this.h != null) {
        ba.a(this.e).edit().putString("fs_lc_tl", this.h.toString()).commit();
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private boolean b(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return true;
      try
      {
        if (paramString.trim().getBytes().length <= 256) {
          continue;
        }
        by.e("Event label or value is empty or too long in tracking Event");
        return false;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
    }
  }
  
  private boolean b(Map<String, Object> paramMap)
  {
    if (paramMap != null) {}
    try
    {
      if (paramMap.isEmpty())
      {
        by.e("map is null or empty in onEvent");
        return false;
      }
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        if (!a((String)localEntry.getKey())) {
          break label118;
        }
        if (localEntry.getValue() == null) {
          break label120;
        }
        if ((localEntry.getValue() instanceof String))
        {
          boolean bool = b(localEntry.getValue().toString());
          if (!bool) {
            return false;
          }
        }
      }
    }
    catch (Exception paramMap) {}
    return true;
    label118:
    return false;
    label120:
    return false;
  }
  
  public void a(Context paramContext, List<String> paramList)
  {
    do
    {
      try
      {
        if (this.e != null) {
          break;
        }
      }
      catch (Exception paramContext)
      {
        return;
      }
      this.e = paramContext;
      return;
    } while (paramContext != null);
  }
  
  public void a(String paramString1, String paramString2, long paramLong, int paramInt)
  {
    JSONObject localJSONObject;
    try
    {
      if (!a(paramString1)) {
        break label124;
      }
      if (!b(paramString2)) {
        return;
      }
      localJSONObject = new JSONObject();
      localJSONObject.put("id", paramString1);
      localJSONObject.put("ts", System.currentTimeMillis());
    }
    catch (Throwable paramString1) {}
    localJSONObject.put("du", paramLong);
    label61:
    localJSONObject.put("__t", 2049);
    for (;;)
    {
      localJSONObject.put(paramString1, localObject);
      localJSONObject.put("__i", bd.g(this.e));
      localJSONObject.put("_umpname", ap.a);
      this.i.a(localJSONObject);
      return;
      label124:
      return;
      if (paramLong <= 0L) {
        break label61;
      }
      break;
      Object localObject = paramString2;
      if (paramString2 == null) {
        localObject = "";
      }
    }
  }
  
  public void a(String paramString, Map<String, Object> paramMap) {}
  
  public void a(String paramString, Map<String, Object> paramMap, long paramLong)
  {
    JSONObject localJSONObject;
    try
    {
      if (!a(paramString)) {
        return;
      }
      if (!b(paramMap)) {
        break label280;
      }
      localJSONObject = new JSONObject();
      localJSONObject.put("id", paramString);
      localJSONObject.put("ts", System.currentTimeMillis());
    }
    catch (Throwable paramString) {}
    localJSONObject.put("du", paramLong);
    label61:
    localJSONObject.put("__t", 2049);
    paramString = paramMap.entrySet().iterator();
    for (;;)
    {
      int j;
      if ((j < 10) && (paramString.hasNext()))
      {
        paramMap = (Map.Entry)paramString.next();
        if ((!"$st_fl".equals(paramMap.getKey())) && (!"dplus_st".equals(paramMap.getKey())) && (!"du".equals(paramMap.getKey())) && (!"id".equals(paramMap.getKey())) && (!"ts".equals(paramMap.getKey())))
        {
          Object localObject = paramMap.getValue();
          if (((localObject instanceof String)) || ((localObject instanceof Integer)) || ((localObject instanceof Long))) {
            localJSONObject.put((String)paramMap.getKey(), localObject);
          }
        }
      }
      else
      {
        localJSONObject.put("__i", bd.g(this.e));
        localJSONObject.put("_umpname", ap.a);
        this.i.a(localJSONObject);
        return;
        label280:
        return;
        if (paramLong <= 0L) {
          break label61;
        }
        break;
        j = 0;
        continue;
      }
      j += 1;
    }
  }
  
  public boolean a(List<String> paramList, int paramInt, final String paramString)
  {
    int j = 0;
    for (;;)
    {
      try
      {
        n localn = n.a();
        continue;
        by.e("cklist is null!");
        continue;
        if (paramList.size() <= 0)
        {
          by.e("the KeyList is null!");
          continue;
        }
        ArrayList localArrayList = new ArrayList(paramList);
        if (!localn.b((String)localArrayList.get(0)))
        {
          by.e("Primary key Invalid!");
          continue;
        }
        if (localArrayList.size() > 8)
        {
          paramList = (String)localArrayList.get(0);
          localArrayList.clear();
          localArrayList.add(paramList);
          localArrayList.add("__cc");
          localArrayList.add("illegal");
          if (!localn.a(paramString))
          {
            by.e("label  Invalid!");
            continue;
            paramString = new HashMap();
            paramString.put(localArrayList, new l(localArrayList, paramInt, paramList, System.currentTimeMillis()));
            bz.b(new cb()
            {
              public void a()
              {
                m.a(at.a(at.this)).a(new f()
                {
                  public void a(Object paramAnonymous2Object, boolean paramAnonymous2Boolean)
                  {
                    if (paramAnonymous2Object.equals("success")) {}
                  }
                }, paramString);
              }
            });
            continue;
          }
        }
        else
        {
          if (!localn.a(localArrayList))
          {
            paramList = (String)localArrayList.get(0);
            localArrayList.clear();
            localArrayList.add(paramList);
            localArrayList.add("__cc");
            localArrayList.add("illegal");
            continue;
          }
          if (!localn.b(localArrayList))
          {
            paramList = (String)localArrayList.get(0);
            localArrayList.clear();
            localArrayList.add(paramList);
            localArrayList.add("__cc");
            localArrayList.add("illegal");
            continue;
          }
          if (j < localArrayList.size())
          {
            paramList = (String)localArrayList.get(j);
            if (paramList.length() > 16)
            {
              localArrayList.remove(j);
              localArrayList.add(j, paramList.substring(0, 16));
            }
            j += 1;
            continue;
          }
          continue;
        }
        paramList = paramString;
        continue;
        if (paramList != null) {
          continue;
        }
        continue;
      }
      catch (Exception paramList)
      {
        continue;
      }
      return true;
      return false;
      return false;
      paramList = "__illegal";
    }
  }
  
  public void b(String paramString, Map<String, Object> paramMap)
  {
    for (;;)
    {
      int j;
      try
      {
        if (!a(paramString)) {
          return;
        }
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("id", paramString);
        localJSONObject.put("ts", System.currentTimeMillis());
        localJSONObject.put("du", 0);
        localJSONObject.put("__t", 2050);
        paramString = paramMap.entrySet().iterator();
        j = 0;
        if ((j < 10) && (paramString.hasNext()))
        {
          paramMap = (Map.Entry)paramString.next();
          if ((!"$st_fl".equals(paramMap.getKey())) && (!"dplus_st".equals(paramMap.getKey())) && (!"du".equals(paramMap.getKey())) && (!"id".equals(paramMap.getKey())) && (!"ts".equals(paramMap.getKey())))
          {
            Object localObject = paramMap.getValue();
            if (((localObject instanceof String)) || ((localObject instanceof Integer)) || ((localObject instanceof Long))) {
              localJSONObject.put((String)paramMap.getKey(), localObject);
            }
          }
        }
        else
        {
          localJSONObject.put("__i", bd.g(this.e));
          this.i.a(localJSONObject);
          return;
        }
      }
      catch (Throwable paramString)
      {
        return;
      }
      j += 1;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\at.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */