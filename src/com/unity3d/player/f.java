package com.unity3d.player;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import java.util.LinkedList;
import java.util.List;

public final class f
  implements c
{
  private static boolean a(PackageItemInfo paramPackageItemInfo)
  {
    try
    {
      boolean bool = paramPackageItemInfo.metaData.getBoolean("unityplayer.SkipPermissionsDialog");
      return bool;
    }
    catch (Exception paramPackageItemInfo) {}
    return false;
  }
  
  public final void a(final Activity paramActivity, final Runnable paramRunnable)
  {
    int i = 0;
    if (paramActivity == null) {
      return;
    }
    PackageManager localPackageManager = paramActivity.getPackageManager();
    Object localObject;
    try
    {
      ActivityInfo localActivityInfo = localPackageManager.getActivityInfo(paramActivity.getComponentName(), 128);
      localObject = localPackageManager.getApplicationInfo(paramActivity.getPackageName(), 128);
      if ((a(localActivityInfo)) || (a((PackageItemInfo)localObject)))
      {
        paramRunnable.run();
        return;
      }
    }
    catch (Exception localException) {}
    for (;;)
    {
      final LinkedList localLinkedList;
      try
      {
        localObject = localPackageManager.getPackageInfo(paramActivity.getPackageName(), 4096);
        if (((PackageInfo)localObject).requestedPermissions == null) {
          ((PackageInfo)localObject).requestedPermissions = new String[0];
        }
        localLinkedList = new LinkedList();
        localObject = ((PackageInfo)localObject).requestedPermissions;
        int j = localObject.length;
        if (i < j)
        {
          String str = localObject[i];
          try
          {
            if ((localPackageManager.getPermissionInfo(str, 128).protectionLevel != 1) || (paramActivity.checkCallingOrSelfPermission(str) == 0)) {
              break label308;
            }
            localLinkedList.add(str);
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            e.Log(5, "Failed to get permission info for " + str + ", manifest likely missing custom permission declaration");
            e.Log(5, "Permission " + str + " ignored");
          }
        }
        if (!localLinkedList.isEmpty()) {
          break label272;
        }
      }
      catch (Exception paramActivity)
      {
        e.Log(6, "Unable to query for permission: " + paramActivity.getMessage());
        return;
      }
      paramRunnable.run();
      return;
      label272:
      paramActivity = paramActivity.getFragmentManager();
      paramRunnable = new Fragment()
      {
        public final void onCreate(Bundle paramAnonymousBundle)
        {
          super.onCreate(paramAnonymousBundle);
          requestPermissions((String[])localLinkedList.toArray(new String[0]), 15881);
        }
        
        public final void onRequestPermissionsResult(int paramAnonymousInt, String[] paramAnonymousArrayOfString, int[] paramAnonymousArrayOfInt)
        {
          if (paramAnonymousInt != 15881) {
            return;
          }
          paramAnonymousInt = 0;
          if ((paramAnonymousInt < paramAnonymousArrayOfString.length) && (paramAnonymousInt < paramAnonymousArrayOfInt.length))
          {
            StringBuilder localStringBuilder = new StringBuilder().append(paramAnonymousArrayOfString[paramAnonymousInt]);
            if (paramAnonymousArrayOfInt[paramAnonymousInt] == 0) {}
            for (String str = " granted";; str = " denied")
            {
              e.Log(4, str);
              paramAnonymousInt += 1;
              break;
            }
          }
          paramAnonymousArrayOfString = paramActivity.beginTransaction();
          paramAnonymousArrayOfString.remove(this);
          paramAnonymousArrayOfString.commit();
          paramRunnable.run();
        }
      };
      paramActivity = paramActivity.beginTransaction();
      paramActivity.add(0, paramRunnable);
      paramActivity.commit();
      return;
      label308:
      i += 1;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\unity3d\player\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */