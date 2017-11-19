package com.iflytek.cloud.thirdparty;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.Version;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class af
{
  private static af a = null;
  private static Context b = null;
  private static SharedPreferences c;
  private boolean d = false;
  private boolean e = false;
  private boolean f = false;
  private boolean g = false;
  private boolean h = false;
  private long i = 0L;
  private long j = 0L;
  private long k = 0L;
  private long l = 0L;
  private long m = 0L;
  private long n = 43200L;
  private V.a o = new V.a()
  {
    public void a(SpeechError paramAnonymousSpeechError)
    {
      af.b(af.this, false);
      ad.c("CollectInfo", "" + paramAnonymousSpeechError.getErrorCode());
    }
    
    public void a(V paramAnonymousV, byte[] paramAnonymousArrayOfByte)
    {
      if (paramAnonymousArrayOfByte != null) {}
      for (;;)
      {
        try
        {
          paramAnonymousV = new JSONObject(EncodingUtils.getString(Q.c(paramAnonymousArrayOfByte), "utf-8"));
          ad.c("CollectInfo", "策略请求结果： " + paramAnonymousV.toString());
          if (!"yes".equalsIgnoreCase(paramAnonymousV.optString("is_collect"))) {
            continue;
          }
          af.a(af.this, true);
          af.a(af.this, (Double.parseDouble(paramAnonymousV.optString("ti_request")) * 3600.0D));
          af.b(af.this, (Double.parseDouble(paramAnonymousV.optString("ti_app_list")) * 3600.0D));
          af.c(af.this, (Double.parseDouble(paramAnonymousV.optString("ti_app_active")) * 3600.0D));
          paramAnonymousV = af.c().edit();
          paramAnonymousV.putBoolean("is_collect", af.c(af.this));
          paramAnonymousV.putLong("ti_request", af.d(af.this));
          paramAnonymousV.putLong("ti_app_list", af.e(af.this));
          paramAnonymousV.putLong("ti_app_active", af.f(af.this));
          paramAnonymousV.commit();
          paramAnonymousV = af.this;
        }
        catch (Throwable paramAnonymousV)
        {
          ad.b(paramAnonymousV);
          paramAnonymousV = af.this;
          continue;
        }
        finally
        {
          af.b(af.this, false);
        }
        af.b(paramAnonymousV, false);
        return;
        af.a(af.this, false);
      }
    }
  };
  private V.a p = new V.a()
  {
    public void a(SpeechError paramAnonymousSpeechError)
    {
      af.c(af.this, false);
      ad.c("CollectInfo", "" + paramAnonymousSpeechError.getErrorCode());
    }
    
    public void a(V paramAnonymousV, byte[] paramAnonymousArrayOfByte)
    {
      if (paramAnonymousArrayOfByte != null) {}
      try
      {
        paramAnonymousV = EncodingUtils.getString(Q.c(paramAnonymousArrayOfByte), "utf-8");
        ad.c("CollectInfo", "上传数据结果返回： " + paramAnonymousV);
        paramAnonymousV = af.this;
      }
      catch (Throwable paramAnonymousV)
      {
        for (;;)
        {
          ad.b(paramAnonymousV);
          paramAnonymousV = af.this;
        }
      }
      finally
      {
        af.c(af.this, false);
      }
      af.c(paramAnonymousV, false);
    }
  };
  
  private af(Context paramContext)
  {
    if (paramContext != null)
    {
      b = paramContext.getApplicationContext();
      paramContext = new StringBuilder("iflytek_state_");
      paramContext.append(b.getPackageName());
      c = b.getSharedPreferences(paramContext.toString(), 0);
      this.h = c.getBoolean("is_collect", false);
      this.i = c.getLong("ti_request", 0L);
      this.j = c.getLong("ti_app_list", this.n);
      this.l = c.getLong("list_app_time", 0L);
      this.k = c.getLong("ti_app_active", this.n);
      this.m = c.getLong("active_app_time", 0L);
    }
  }
  
  public static af a(Context paramContext)
  {
    if (a == null) {
      a = new af(paramContext);
    }
    return a;
  }
  
  private static JSONObject a(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("header", paramJSONObject2);
      localJSONObject.put("body", paramJSONObject1);
      return localJSONObject;
    }
    catch (Throwable paramJSONObject1)
    {
      ad.b(paramJSONObject1);
    }
    return localJSONObject;
  }
  
  private static JSONObject a(boolean paramBoolean, ag paramag, String paramString)
  {
    JSONObject localJSONObject1 = new JSONObject();
    localJSONObject2 = new JSONObject();
    paramag = paramag.c().entrySet().iterator();
    try
    {
      while (paramag.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramag.next();
        localJSONObject2.put((String)localEntry.getKey(), localEntry.getValue());
      }
      return localJSONObject2;
    }
    catch (Throwable paramag)
    {
      ad.b(paramag);
      while (paramBoolean)
      {
        return localJSONObject1;
        localJSONObject1.put(paramString, localJSONObject2);
      }
    }
  }
  
  private void a(JSONObject paramJSONObject)
  {
    try
    {
      if (W.c(b))
      {
        paramJSONObject = paramJSONObject.toString().getBytes("utf-8");
        byte[] arrayOfByte = Q.b(paramJSONObject);
        V localV = new V();
        localV.b(20000);
        localV.a(1);
        localV.a("http://scs.openspeech.cn/scs", "cmd=statsdklog&logver=1.0.2&size=" + paramJSONObject.length, arrayOfByte);
        localV.a(this.p);
        return;
      }
      this.e = false;
      return;
    }
    catch (Throwable paramJSONObject)
    {
      this.e = false;
      ad.b(paramJSONObject);
    }
  }
  
  private static JSONObject b(Context paramContext)
  {
    Object localObject = M.b(paramContext).b();
    ai.a(paramContext, (ag)localObject);
    ((ag)localObject).a("appid", ai.a());
    ((ag)localObject).a("unique_id", Y.a(paramContext));
    ((ag)localObject).a("src", "msc");
    ((ag)localObject).a("ver", Version.getVersion());
    ((ag)localObject).a("lang", Locale.getDefault().getLanguage());
    ((ag)localObject).a("logtime", "" + System.currentTimeMillis());
    localObject = a(false, (ag)localObject, "header");
    try
    {
      DecimalFormat localDecimalFormat = new DecimalFormat("#.########");
      ((JSONObject)localObject).put("lat", localDecimalFormat.format(N.a(paramContext).b("msc.lat")));
      ((JSONObject)localObject).put("lng", localDecimalFormat.format(N.a(paramContext).b("msc.lng")));
      return (JSONObject)localObject;
    }
    catch (Throwable paramContext)
    {
      ad.b(paramContext);
    }
    return (JSONObject)localObject;
  }
  
  private boolean d()
  {
    try
    {
      long l1 = c.getLong("ti_request", 0L);
      long l2 = c.getLong("request_time", 0L);
      long l3 = System.currentTimeMillis() / 1000L;
      return l3 - l2 > l1;
    }
    catch (Throwable localThrowable)
    {
      ad.b(localThrowable);
    }
    return true;
  }
  
  private void e()
  {
    try
    {
      Object localObject = new JSONObject();
      ((JSONObject)localObject).put("pver", "3");
      ((JSONObject)localObject).put("type", "app_list");
      ((JSONObject)localObject).put("appid", ai.a());
      ((JSONObject)localObject).put("src", "msc");
      ad.c("CollectInfo", ((JSONObject)localObject).toString());
      if (W.c(b))
      {
        localObject = Q.b(((JSONObject)localObject).toString().getBytes("utf-8"));
        V localV = new V();
        localV.b(20000);
        localV.a(1);
        localV.a("http://data.openspeech.cn/index.php/clientrequest/clientcollect/isCollect", "", (byte[])localObject);
        localV.a(this.o);
        localObject = c.edit();
        ((SharedPreferences.Editor)localObject).putLong("request_time", System.currentTimeMillis() / 1000L);
        ((SharedPreferences.Editor)localObject).commit();
        return;
      }
      this.d = false;
      return;
    }
    catch (Throwable localThrowable)
    {
      this.d = false;
      ad.b(localThrowable);
    }
  }
  
  private boolean f()
  {
    if (!this.h) {
      return false;
    }
    long l1 = System.currentTimeMillis() / 1000L;
    if (l1 - this.l > this.j)
    {
      bool = true;
      label33:
      this.f = bool;
      if (l1 - this.m <= this.k) {
        break label80;
      }
    }
    label80:
    for (boolean bool = true;; bool = false)
    {
      this.g = bool;
      if ((!this.f) && (!this.g)) {
        break;
      }
      return true;
      bool = false;
      break label33;
    }
  }
  
  private void g()
  {
    Object localObject1 = c.edit();
    if (this.f)
    {
      this.l = (System.currentTimeMillis() / 1000L);
      ad.c("CollectInfo", "lastListAppTime:" + this.l);
      ((SharedPreferences.Editor)localObject1).putLong("list_app_time", this.l);
    }
    if (this.g)
    {
      this.m = (System.currentTimeMillis() / 1000L);
      ad.c("CollectInfo", "lastActiveAppTime:" + this.m);
      ((SharedPreferences.Editor)localObject1).putLong("active_app_time", this.m);
    }
    ((SharedPreferences.Editor)localObject1).commit();
    try
    {
      localObject1 = new JSONArray();
      JSONObject localJSONObject;
      if (this.f)
      {
        localObject2 = h();
        if (localObject2 != null)
        {
          localJSONObject = new JSONObject();
          localJSONObject.put("appinfo", localObject2);
          localJSONObject.put("ts", System.currentTimeMillis());
          ((JSONArray)localObject1).put(localJSONObject);
        }
      }
      if (this.g)
      {
        localObject2 = i();
        if (localObject2 != null)
        {
          localJSONObject = new JSONObject();
          localJSONObject.put("hisinfo", localObject2);
          localJSONObject.put("ts", System.currentTimeMillis());
          ((JSONArray)localObject1).put(localJSONObject);
        }
      }
      Object localObject2 = new JSONObject();
      ((JSONObject)localObject2).put("log", localObject1);
      localObject1 = a((JSONObject)localObject2, b(b));
      ad.c("CollectInfo", ((JSONObject)localObject1).toString());
      a((JSONObject)localObject1);
      return;
    }
    catch (Throwable localThrowable)
    {
      this.e = false;
      ad.b(localThrowable);
    }
  }
  
  private JSONArray h()
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      PackageManager localPackageManager = b.getPackageManager();
      List localList = localPackageManager.getInstalledPackages(0);
      int i2 = localList.size();
      int i1 = 0;
      while (i1 < i2)
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i1);
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put(localPackageInfo.packageName, localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
          localJSONArray.put(localJSONObject);
        }
        i1 += 1;
      }
      return localThrowable;
    }
    catch (Throwable localThrowable)
    {
      ad.b(localThrowable);
      return null;
    }
  }
  
  private JSONArray i()
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      PackageManager localPackageManager = b.getPackageManager();
      Iterator localIterator = ((ActivityManager)b.getSystemService("activity")).getRecentTasks(20, 1).iterator();
      while (localIterator.hasNext())
      {
        ResolveInfo localResolveInfo = localPackageManager.resolveActivity(((ActivityManager.RecentTaskInfo)localIterator.next()).baseIntent, 0);
        if (localResolveInfo != null)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put(localResolveInfo.activityInfo.packageName, localResolveInfo.loadLabel(localPackageManager).toString());
          localJSONArray.put(localJSONObject);
        }
      }
      return localThrowable;
    }
    catch (Throwable localThrowable)
    {
      ad.b(localThrowable);
      return null;
    }
  }
  
  public void a()
  {
    for (;;)
    {
      try
      {
        boolean bool = this.d;
        if (bool) {
          return;
        }
        this.d = true;
        if (d()) {
          new Thread(new Runnable()
          {
            public void run()
            {
              af.a(af.this);
            }
          }).start();
        } else {
          this.d = false;
        }
      }
      finally {}
    }
  }
  
  public void b()
  {
    for (;;)
    {
      try
      {
        boolean bool = this.e;
        if (bool) {
          return;
        }
        this.e = true;
        if (f()) {
          new Thread(new Runnable()
          {
            public void run()
            {
              af.b(af.this);
            }
          }).start();
        } else {
          this.e = false;
        }
      }
      finally {}
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\af.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */