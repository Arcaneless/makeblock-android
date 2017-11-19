package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzac;

class zzatk
  extends BroadcastReceiver
{
  static final String zzafu = zzatk.class.getName();
  private boolean zzafv;
  private boolean zzafw;
  private final zzatp zzbpw;
  
  zzatk(zzatp paramzzatp)
  {
    zzac.zzw(paramzzatp);
    this.zzbpw = paramzzatp;
  }
  
  private Context getContext()
  {
    return this.zzbpw.getContext();
  }
  
  private zzati zzJt()
  {
    return this.zzbpw.zzJt();
  }
  
  @WorkerThread
  public boolean isRegistered()
  {
    this.zzbpw.zzmq();
    return this.zzafv;
  }
  
  @MainThread
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.zzbpw.zznA();
    paramContext = paramIntent.getAction();
    zzJt().zzLg().zzj("NetworkBroadcastReceiver received action", paramContext);
    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramContext))
    {
      final boolean bool = this.zzbpw.zzLy().zzpA();
      if (this.zzafw != bool)
      {
        this.zzafw = bool;
        this.zzbpw.zzJs().zzm(new Runnable()
        {
          public void run()
          {
            zzatk.zza(zzatk.this).zzV(bool);
          }
        });
      }
      return;
    }
    zzJt().zzLc().zzj("NetworkBroadcastReceiver received unknown action", paramContext);
  }
  
  @WorkerThread
  public void unregister()
  {
    this.zzbpw.zznA();
    this.zzbpw.zzmq();
    if (!isRegistered()) {
      return;
    }
    zzJt().zzLg().log("Unregistering connectivity change receiver");
    this.zzafv = false;
    this.zzafw = false;
    Context localContext = getContext();
    try
    {
      localContext.unregisterReceiver(this);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      zzJt().zzLa().zzj("Failed to unregister the network broadcast receiver", localIllegalArgumentException);
    }
  }
  
  @WorkerThread
  public void zzpx()
  {
    this.zzbpw.zznA();
    this.zzbpw.zzmq();
    if (this.zzafv) {
      return;
    }
    getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    this.zzafw = this.zzbpw.zzLy().zzpA();
    zzJt().zzLg().zzj("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzafw));
    this.zzafv = true;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */