package com.google.firebase.iid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.util.Log;
import java.io.IOException;

public class FirebaseInstanceIdService
  extends zzb
{
  private static BroadcastReceiver zzciU;
  @VisibleForTesting
  static final Object zzciV = new Object();
  @VisibleForTesting
  static boolean zzciW = false;
  private boolean zzciX = false;
  
  private String zzJ(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("subtype");
    paramIntent = str;
    if (str == null) {
      paramIntent = "";
    }
    return paramIntent;
  }
  
  private int zza(Intent paramIntent, boolean paramBoolean)
  {
    int j = 10;
    int i;
    if (paramIntent == null)
    {
      i = 10;
      if ((i >= 10) || (paramBoolean)) {
        break label39;
      }
      j = 30;
    }
    label39:
    while (i < 10)
    {
      return j;
      i = paramIntent.getIntExtra("next_retry_delay_in_seconds", 0);
      break;
    }
    if (i > 28800) {
      return 28800;
    }
    return i;
  }
  
  static void zza(Context paramContext, FirebaseInstanceId paramFirebaseInstanceId)
  {
    synchronized (zzciV)
    {
      if (zzciW) {
        return;
      }
      ??? = paramFirebaseInstanceId.zzaad();
      if ((??? == null) || (((zzh.zza)???).zzjC(zzd.zzbhg)) || (paramFirebaseInstanceId.zzaaf().zzaai() != null))
      {
        zzbV(paramContext);
        return;
      }
    }
  }
  
  private void zza(Intent paramIntent, boolean paramBoolean1, boolean paramBoolean2)
  {
    String str2;
    for (;;)
    {
      synchronized (zzciV)
      {
        zzciW = false;
        if (zzf.zzbi(this) == null) {
          return;
        }
      }
      ??? = FirebaseInstanceId.getInstance();
      localObject2 = ((FirebaseInstanceId)???).zzaad();
      if ((localObject2 == null) || (((zzh.zza)localObject2).zzjC(zzd.zzbhg))) {
        try
        {
          str2 = ((FirebaseInstanceId)???).zzaae();
          if (str2 != null)
          {
            if (this.zzciX) {
              Log.d("FirebaseInstanceId", "get master token succeeded");
            }
            zza(this, (FirebaseInstanceId)???);
            if ((!paramBoolean2) && (localObject2 != null) && ((localObject2 == null) || (str2.equals(((zzh.zza)localObject2).zzbwP)))) {
              continue;
            }
            onTokenRefresh();
          }
        }
        catch (IOException localIOException1)
        {
          zzd(paramIntent, localIOException1.getMessage());
          return;
          zzd(paramIntent, "returned token is null");
          return;
        }
        catch (SecurityException paramIntent)
        {
          Log.e("FirebaseInstanceId", "Unable to get master token", paramIntent);
          return;
        }
      }
    }
    Object localObject2 = localIOException1.zzaaf();
    String str1 = ((zze)localObject2).zzaai();
    if (str1 != null)
    {
      Object localObject3 = str1.split("!");
      int j;
      if (localObject3.length == 2)
      {
        str2 = localObject3[0];
        localObject3 = localObject3[1];
        j = -1;
      }
      for (;;)
      {
        try
        {
          int k = str2.hashCode();
          int i = j;
          switch (k)
          {
          default: 
            i = j;
          case 84: 
            switch (i)
            {
            default: 
              ((zze)localObject2).zzjy(str1);
              str1 = ((zze)localObject2).zzaai();
            }
            break;
          case 83: 
            i = j;
            if (!str2.equals("S")) {
              continue;
            }
            i = 0;
            break;
          case 85: 
            i = j;
            if (!str2.equals("U")) {
              continue;
            }
            i = 1;
            continue;
            FirebaseInstanceId.getInstance().zzjv((String)localObject3);
            if (!this.zzciX) {
              continue;
            }
            Log.d("FirebaseInstanceId", "subscribe operation succeeded");
            continue;
            FirebaseInstanceId.getInstance().zzjw((String)localObject3);
          }
        }
        catch (IOException localIOException2)
        {
          zzd(paramIntent, localIOException2.getMessage());
          return;
        }
        if (this.zzciX) {
          Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
        }
      }
    }
    Log.d("FirebaseInstanceId", "topic sync succeeded");
  }
  
  private void zza(zzf paramzzf, Bundle paramBundle)
  {
    String str = zzf.zzbi(this);
    if (str == null)
    {
      Log.w("FirebaseInstanceId", "Unable to respond to ping due to missing target package");
      return;
    }
    Intent localIntent = new Intent("com.google.android.gcm.intent.SEND");
    localIntent.setPackage(str);
    localIntent.putExtras(paramBundle);
    paramzzf.zzs(localIntent);
    localIntent.putExtra("google.to", "google.com/iid");
    localIntent.putExtra("google.message_id", zzf.zzGz());
    sendOrderedBroadcast(localIntent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
  }
  
  static void zzbV(Context paramContext)
  {
    if (zzf.zzbi(paramContext) == null) {
      return;
    }
    synchronized (zzciV)
    {
      if (!zzciW)
      {
        zzg.zzaaj().zzf(paramContext, zzpR(0));
        zzciW = true;
      }
      return;
    }
  }
  
  private static boolean zzbW(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  private void zzd(Intent arg1, String paramString)
  {
    boolean bool = zzbW(this);
    final int i = zza(???, bool);
    Log.d("FirebaseInstanceId", String.valueOf(paramString).length() + 47 + "background sync failed: " + paramString + ", retry in " + i + "s");
    synchronized (zzciV)
    {
      zzpS(i);
      zzciW = true;
      if (!bool)
      {
        if (this.zzciX) {
          Log.d("FirebaseInstanceId", "device not connected. Connectivity change received registered");
        }
        if (zzciU == null) {
          zzciU = new BroadcastReceiver()
          {
            public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
            {
              if (FirebaseInstanceIdService.zzbX(paramAnonymousContext))
              {
                if (FirebaseInstanceIdService.zza(FirebaseInstanceIdService.this)) {
                  Log.d("FirebaseInstanceId", "connectivity changed. starting background sync.");
                }
                FirebaseInstanceIdService.this.getApplicationContext().unregisterReceiver(this);
                zzg.zzaaj().zzf(paramAnonymousContext, FirebaseInstanceIdService.zzpT(i));
              }
            }
          };
        }
        getApplicationContext().registerReceiver(zzciU, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
      }
      return;
    }
  }
  
  private zzd zzjx(String paramString)
  {
    if (paramString == null) {
      return zzd.zzb(this, null);
    }
    Bundle localBundle = new Bundle();
    localBundle.putString("subtype", paramString);
    return zzd.zzb(this, localBundle);
  }
  
  private static Intent zzpR(int paramInt)
  {
    Intent localIntent = new Intent("ACTION_TOKEN_REFRESH_RETRY");
    localIntent.putExtra("next_retry_delay_in_seconds", paramInt);
    return localIntent;
  }
  
  private void zzpS(int paramInt)
  {
    AlarmManager localAlarmManager = (AlarmManager)getSystemService("alarm");
    PendingIntent localPendingIntent = zzg.zza(this, 0, zzpR(paramInt * 2), 134217728);
    localAlarmManager.set(3, SystemClock.elapsedRealtime() + paramInt * 1000, localPendingIntent);
  }
  
  @WorkerThread
  public void onTokenRefresh() {}
  
  protected Intent zzF(Intent paramIntent)
  {
    return zzg.zzaaj().zzaak();
  }
  
  public boolean zzH(Intent paramIntent)
  {
    this.zzciX = Log.isLoggable("FirebaseInstanceId", 3);
    if ((paramIntent.getStringExtra("error") != null) || (paramIntent.getStringExtra("registration_id") != null))
    {
      String str2 = zzJ(paramIntent);
      if (this.zzciX)
      {
        str1 = String.valueOf(str2);
        if (str1.length() == 0) {
          break label84;
        }
      }
      label84:
      for (String str1 = "Register result in service ".concat(str1);; str1 = new String("Register result in service "))
      {
        Log.d("FirebaseInstanceId", str1);
        zzjx(str2).zzaah().zzv(paramIntent);
        return true;
      }
    }
    return false;
  }
  
  public void zzI(Intent paramIntent)
  {
    String str2 = zzJ(paramIntent);
    zzd localzzd = zzjx(str2);
    String str1 = paramIntent.getStringExtra("CMD");
    Object localObject;
    if (this.zzciX)
    {
      localObject = String.valueOf(paramIntent.getExtras());
      Log.d("FirebaseInstanceId", String.valueOf(str2).length() + 18 + String.valueOf(str1).length() + String.valueOf(localObject).length() + "Service command " + str2 + " " + str1 + " " + (String)localObject);
    }
    if (paramIntent.getStringExtra("unregistered") != null)
    {
      localObject = localzzd.zzaag();
      str1 = str2;
      if (str2 == null) {
        str1 = "";
      }
      ((zzh)localObject).zzeO(str1);
      localzzd.zzaah().zzv(paramIntent);
    }
    do
    {
      do
      {
        return;
        if ("gcm.googleapis.com/refresh".equals(paramIntent.getStringExtra("from")))
        {
          localzzd.zzaag().zzeO(str2);
          zza(paramIntent, false, true);
          return;
        }
        if ("RST".equals(str1))
        {
          localzzd.zzGu();
          zza(paramIntent, true, true);
          return;
        }
        if (!"RST_FULL".equals(str1)) {
          break;
        }
      } while (localzzd.zzaag().isEmpty());
      localzzd.zzGu();
      localzzd.zzaag().zzGA();
      zza(paramIntent, true, true);
      return;
      if ("SYNC".equals(str1))
      {
        localzzd.zzaag().zzeO(str2);
        zza(paramIntent, false, true);
        return;
      }
    } while (!"PING".equals(str1));
    zza(localzzd.zzaah(), paramIntent.getExtras());
  }
  
  public void zzm(Intent paramIntent)
  {
    String str2 = paramIntent.getAction();
    String str1 = str2;
    if (str2 == null) {
      str1 = "";
    }
    switch (str1.hashCode())
    {
    }
    label40:
    for (int i = -1;; i = 0) {
      switch (i)
      {
      default: 
        zzI(paramIntent);
        return;
        if (!str1.equals("ACTION_TOKEN_REFRESH_RETRY")) {
          break label40;
        }
      }
    }
    zza(paramIntent, false, false);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\firebase\iid\FirebaseInstanceIdService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */