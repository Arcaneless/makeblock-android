package com.bumptech.glide.module;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class ManifestParser
{
  private static final String GLIDE_MODULE_VALUE = "GlideModule";
  private static final String TAG = "ManifestParser";
  private final Context context;
  
  public ManifestParser(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private static GlideModule parseModule(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      Object localObject;
      return (GlideModule)localIllegalAccessException;
    }
    catch (ClassNotFoundException paramString)
    {
      try
      {
        localObject = paramString.newInstance();
        if ((localObject instanceof GlideModule)) {
          break label114;
        }
        throw new RuntimeException("Expected instanceof GlideModule, but found: " + localObject);
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new RuntimeException("Unable to instantiate GlideModule implementation for " + paramString, localInstantiationException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new RuntimeException("Unable to instantiate GlideModule implementation for " + paramString, localIllegalAccessException);
      }
      paramString = paramString;
      throw new IllegalArgumentException("Unable to find GlideModule implementation", paramString);
    }
  }
  
  public List<GlideModule> parse()
  {
    if (Log.isLoggable("ManifestParser", 3)) {
      Log.d("ManifestParser", "Loading Glide modules");
    }
    ArrayList localArrayList = new ArrayList();
    try
    {
      ApplicationInfo localApplicationInfo = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
      if (localApplicationInfo.metaData == null)
      {
        if (!Log.isLoggable("ManifestParser", 3)) {
          return localArrayList;
        }
        Log.d("ManifestParser", "Got null app info metadata");
        return localArrayList;
      }
      if (Log.isLoggable("ManifestParser", 2)) {
        Log.v("ManifestParser", "Got app info metadata: " + localApplicationInfo.metaData);
      }
      Iterator localIterator = localApplicationInfo.metaData.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if ("GlideModule".equals(localApplicationInfo.metaData.get(str)))
        {
          localArrayList.add(parseModule(str));
          if (Log.isLoggable("ManifestParser", 3)) {
            Log.d("ManifestParser", "Loaded Glide module: " + str);
          }
        }
      }
      if (!Log.isLoggable("ManifestParser", 3)) {
        return localNameNotFoundException;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new RuntimeException("Unable to find metadata to parse GlideModules", localNameNotFoundException);
    }
    Log.d("ManifestParser", "Finished loading Glide modules");
    return localNameNotFoundException;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\module\ManifestParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */