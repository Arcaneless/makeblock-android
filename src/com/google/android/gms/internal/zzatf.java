package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;

public class zzatf
  extends zzats
{
  private String zzVQ;
  private String zzabK;
  private String zzabL;
  private String zzbpK;
  private String zzbpR;
  private int zzbrB;
  private long zzbrC;
  
  zzatf(zzatp paramzzatp)
  {
    super(paramzzatp);
  }
  
  String getGmpAppId()
  {
    zznA();
    return this.zzbpK;
  }
  
  String zzJC()
  {
    zznA();
    return this.zzbpR;
  }
  
  long zzJD()
  {
    return zzJv().zzJD();
  }
  
  @WorkerThread
  long zzJE()
  {
    zznA();
    zzmq();
    if (this.zzbrC == 0L) {
      this.zzbrC = this.zzbpw.zzJp().zzE(getContext(), getContext().getPackageName());
    }
    return this.zzbrC;
  }
  
  int zzKZ()
  {
    zznA();
    return this.zzbrB;
  }
  
  protected void zzbv(Status paramStatus)
  {
    if (paramStatus == null)
    {
      zzJt().zzLa().log("GoogleService failed to initialize (no status)");
      return;
    }
    zzJt().zzLa().zze("GoogleService failed to initialize, status", Integer.valueOf(paramStatus.getStatusCode()), paramStatus.getStatusMessage());
  }
  
  @WorkerThread
  zzasq zzfH(String paramString)
  {
    zzmq();
    String str1 = zzjI();
    String str2 = getGmpAppId();
    String str3 = zzmy();
    long l1 = zzKZ();
    String str4 = zzJC();
    long l2 = zzJD();
    long l3 = zzJE();
    boolean bool2 = this.zzbpw.isEnabled();
    if (!zzJu().zzbsu) {}
    for (boolean bool1 = true;; bool1 = false) {
      return new zzasq(str1, str2, str3, l1, str4, l2, l3, paramString, bool2, bool1, zzJu().zzJy());
    }
  }
  
  String zzjI()
  {
    zznA();
    return this.zzVQ;
  }
  
  protected void zzmr()
  {
    Object localObject3 = "unknown";
    String str3 = "Unknown";
    int j = Integer.MIN_VALUE;
    String str1 = "Unknown";
    String str4 = getContext().getPackageName();
    Object localObject6 = getContext().getPackageManager();
    Object localObject5;
    String str2;
    int i;
    Object localObject1;
    if (localObject6 == null)
    {
      zzJt().zzLa().zzj("PackageManager is null, app identity information might be inaccurate. appId", zzati.zzfI(str4));
      localObject5 = localObject3;
      str2 = str3;
      i = j;
      localObject3 = str1;
      this.zzVQ = str4;
      this.zzbpR = ((String)localObject5);
      this.zzabL = str2;
      this.zzbrB = i;
      this.zzabK = ((String)localObject3);
      zzJv().zzKk();
      localObject1 = zzaas.zzay(getContext());
      if ((localObject1 == null) || (!((Status)localObject1).isSuccess())) {
        break label473;
      }
      i = 1;
      label127:
      if (i == 0) {
        zzbv((Status)localObject1);
      }
      if (i == 0) {
        break label578;
      }
      localObject1 = zzJv().zzKm();
      if (!zzJv().zzKl()) {
        break label478;
      }
      zzJt().zzLe().log("Collection disabled with firebase_analytics_collection_deactivated=1");
      i = 0;
    }
    for (;;)
    {
      this.zzbpK = "";
      zzJv().zzKk();
      try
      {
        localObject3 = zzaas.zzwj();
        localObject1 = localObject3;
        if (TextUtils.isEmpty((CharSequence)localObject3)) {
          localObject1 = "";
        }
        this.zzbpK = ((String)localObject1);
        if (i != 0) {
          zzJt().zzLg().zze("App package, google app id", this.zzVQ, this.zzbpK);
        }
        return;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        label250:
        label259:
        PackageInfo localPackageInfo;
        Object localObject2;
        Object localObject4;
        zzJt().zzLa().zze("getGoogleAppId or isMeasurementEnabled failed with exception. appId", zzati.zzfI(str4), localIllegalStateException);
        return;
      }
      try
      {
        localObject1 = ((PackageManager)localObject6).getInstallerPackageName(str4);
        localObject3 = localObject1;
        if (localObject3 == null)
        {
          localObject1 = "manual_install";
          localObject5 = str1;
          str2 = str3;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        try
        {
          localPackageInfo = ((PackageManager)localObject6).getPackageInfo(getContext().getPackageName(), 0);
          localObject3 = str1;
          i = j;
          str2 = str3;
          localObject5 = localObject1;
          if (localPackageInfo == null) {
            break;
          }
          localObject5 = str1;
          str2 = str3;
          localObject6 = ((PackageManager)localObject6).getApplicationLabel(localPackageInfo.applicationInfo);
          localObject3 = str1;
          localObject5 = str1;
          str2 = str3;
          if (!TextUtils.isEmpty((CharSequence)localObject6))
          {
            localObject5 = str1;
            str2 = str3;
            localObject3 = ((CharSequence)localObject6).toString();
          }
          localObject5 = localObject3;
          str2 = str3;
          str1 = localPackageInfo.versionName;
          localObject5 = localObject3;
          str2 = str1;
          i = localPackageInfo.versionCode;
          str2 = str1;
          localObject5 = localObject1;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          zzJt().zzLa().zze("Error retrieving package info. appId, appName", zzati.zzfI(str4), localObject5);
          localObject4 = localObject5;
          i = j;
          localObject5 = localObject2;
        }
        localIllegalArgumentException = localIllegalArgumentException;
        zzJt().zzLa().zzj("Error retrieving app installer package name. appId", zzati.zzfI(str4));
        break label250;
        localObject2 = localObject3;
        if (!"com.android.vending".equals(localObject3)) {
          break label259;
        }
        localObject2 = "";
        break label259;
      }
      break;
      label473:
      i = 0;
      break label127;
      label478:
      if ((localObject2 != null) && (!((Boolean)localObject2).booleanValue()))
      {
        zzJt().zzLe().log("Collection disabled with firebase_analytics_collection_enabled=0");
        i = 0;
      }
      else if ((localObject2 == null) && (zzJv().zzwk()))
      {
        zzJt().zzLe().log("Collection disabled with google_app_measurement_enable=0");
        i = 0;
      }
      else
      {
        zzJt().zzLg().log("Collection enabled");
        i = 1;
        continue;
        label578:
        i = 0;
      }
    }
  }
  
  String zzmy()
  {
    zznA();
    return this.zzabL;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */