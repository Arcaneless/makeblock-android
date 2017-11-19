package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.LinkedList;
import java.util.Queue;

public class zzg
{
  private static zzg zzcje;
  private final SimpleArrayMap<String, String> zzcjf = new SimpleArrayMap();
  private Boolean zzcjg = null;
  @VisibleForTesting
  final Queue<Intent> zzcjh = new LinkedList();
  @VisibleForTesting
  final Queue<Intent> zzcji = new LinkedList();
  
  public static PendingIntent zza(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2)
  {
    return zza(paramContext, paramInt1, "com.google.firebase.INSTANCE_ID_EVENT", paramIntent, paramInt2);
  }
  
  private static PendingIntent zza(Context paramContext, int paramInt1, String paramString, Intent paramIntent, int paramInt2)
  {
    Intent localIntent = new Intent(paramContext, FirebaseInstanceIdInternalReceiver.class);
    localIntent.setAction(paramString);
    localIntent.putExtra("wrapped_intent", paramIntent);
    return PendingIntent.getBroadcast(paramContext, paramInt1, localIntent, paramInt2);
  }
  
  public static zzg zzaaj()
  {
    try
    {
      if (zzcje == null) {
        zzcje = new zzg();
      }
      zzg localzzg = zzcje;
      return localzzg;
    }
    finally {}
  }
  
  public static PendingIntent zzb(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2)
  {
    return zza(paramContext, paramInt1, "com.google.firebase.MESSAGING_EVENT", paramIntent, paramInt2);
  }
  
  private boolean zzbY(Context paramContext)
  {
    if (this.zzcjg == null) {
      if (paramContext.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") != 0) {
        break label34;
      }
    }
    label34:
    for (boolean bool = true;; bool = false)
    {
      this.zzcjg = Boolean.valueOf(bool);
      return this.zzcjg.booleanValue();
    }
  }
  
  private void zze(Context paramContext, Intent paramIntent)
  {
    synchronized (this.zzcjf)
    {
      ??? = (String)this.zzcjf.get(paramIntent.getAction());
      ??? = ???;
      if (??? != null) {
        break label237;
      }
      ??? = paramContext.getPackageManager().resolveService(paramIntent, 0);
      if ((??? == null) || (((ResolveInfo)???).serviceInfo == null))
      {
        Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
        return;
      }
    }
    ??? = ((ResolveInfo)???).serviceInfo;
    if ((!paramContext.getPackageName().equals(((ServiceInfo)???).packageName)) || (((ServiceInfo)???).name == null))
    {
      paramContext = String.valueOf(((ServiceInfo)???).packageName);
      paramIntent = String.valueOf(((ServiceInfo)???).name);
      Log.e("FirebaseInstanceId", String.valueOf(paramContext).length() + 94 + String.valueOf(paramIntent).length() + "Error resolving target intent service, skipping classname enforcement. Resolved service was: " + paramContext + "/" + paramIntent);
      return;
    }
    ??? = ((ServiceInfo)???).name;
    ??? = ???;
    if (((String)???).startsWith("."))
    {
      ??? = String.valueOf(paramContext.getPackageName());
      ??? = String.valueOf(???);
      if (((String)???).length() == 0) {
        break label288;
      }
      ??? = ((String)???).concat((String)???);
    }
    for (;;)
    {
      synchronized (this.zzcjf)
      {
        this.zzcjf.put(paramIntent.getAction(), ???);
        label237:
        if (Log.isLoggable("FirebaseInstanceId", 3))
        {
          ??? = String.valueOf(???);
          if (((String)???).length() != 0)
          {
            ??? = "Restricting intent to a specific service: ".concat((String)???);
            Log.d("FirebaseInstanceId", (String)???);
          }
        }
        else
        {
          paramIntent.setClassName(paramContext.getPackageName(), (String)???);
          return;
          label288:
          ??? = new String((String)???);
        }
      }
      ??? = new String("Restricting intent to a specific service: ");
    }
  }
  
  private int zzg(Context paramContext, Intent paramIntent)
  {
    zze(paramContext, paramIntent);
    try
    {
      if (zzbY(paramContext)) {
        paramContext = WakefulBroadcastReceiver.startWakefulService(paramContext, paramIntent);
      }
      while (paramContext == null)
      {
        Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
        return 404;
        paramContext = paramContext.startService(paramIntent);
        Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
      }
      return -1;
    }
    catch (SecurityException paramContext)
    {
      Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", paramContext);
      return 401;
    }
  }
  
  public Intent zzaak()
  {
    return (Intent)this.zzcjh.poll();
  }
  
  public Intent zzaal()
  {
    return (Intent)this.zzcji.poll();
  }
  
  public int zzb(Context paramContext, String paramString, Intent paramIntent)
  {
    int i = -1;
    switch (paramString.hashCode())
    {
    default: 
      switch (i)
      {
      default: 
        paramContext = String.valueOf(paramString);
        if (paramContext.length() == 0) {}
        break;
      }
      break;
    }
    for (paramContext = "Unknown service action: ".concat(paramContext);; paramContext = new String("Unknown service action: "))
    {
      Log.w("FirebaseInstanceId", paramContext);
      return 500;
      if (!paramString.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
        break;
      }
      i = 0;
      break;
      if (!paramString.equals("com.google.firebase.MESSAGING_EVENT")) {
        break;
      }
      i = 1;
      break;
      this.zzcjh.offer(paramIntent);
      for (;;)
      {
        paramString = new Intent(paramString);
        paramString.setPackage(paramContext.getPackageName());
        return zzg(paramContext, paramString);
        this.zzcji.offer(paramIntent);
      }
    }
  }
  
  public void zzf(Context paramContext, Intent paramIntent)
  {
    zzb(paramContext, "com.google.firebase.INSTANCE_ID_EVENT", paramIntent);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\firebase\iid\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */