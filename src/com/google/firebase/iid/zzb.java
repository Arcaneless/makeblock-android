package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.iid.MessengerCompat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzb
  extends Service
{
  @VisibleForTesting
  final ExecutorService zzbFy = Executors.newSingleThreadExecutor();
  private int zzbfI;
  private int zzbfJ = 0;
  MessengerCompat zzbhh = new MessengerCompat(new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      int i = MessengerCompat.zzc(paramAnonymousMessage);
      zzf.zzbi(zzb.this);
      zzb.this.getPackageManager();
      if ((i != zzf.zzbhs) && (i != zzf.zzbhr))
      {
        int j = zzf.zzbhr;
        int k = zzf.zzbhs;
        Log.w("FirebaseInstanceId", 77 + "Message from unexpected caller " + i + " mine=" + j + " appid=" + k);
        return;
      }
      zzb.this.zzm((Intent)paramAnonymousMessage.obj);
    }
  });
  private final Object zzrN = new Object();
  
  private void zzG(Intent arg1)
  {
    if (??? != null) {
      WakefulBroadcastReceiver.completeWakefulIntent(???);
    }
    synchronized (this.zzrN)
    {
      this.zzbfJ -= 1;
      if (this.zzbfJ == 0) {
        zzjr(this.zzbfI);
      }
      return;
    }
  }
  
  public final IBinder onBind(Intent paramIntent)
  {
    if ((paramIntent != null) && ("com.google.firebase.INSTANCE_ID_EVENT".equals(paramIntent.getAction()))) {
      return this.zzbhh.getBinder();
    }
    return null;
  }
  
  public final int onStartCommand(final Intent paramIntent, int paramInt1, int paramInt2)
  {
    synchronized (this.zzrN)
    {
      this.zzbfI = paramInt2;
      this.zzbfJ += 1;
      ??? = zzF(paramIntent);
      if (??? == null)
      {
        zzG(paramIntent);
        return 2;
      }
    }
    if (zzH((Intent)???))
    {
      zzG(paramIntent);
      return 2;
    }
    this.zzbFy.execute(new Runnable()
    {
      public void run()
      {
        zzb.this.zzm(localObject);
        zzb.zza(zzb.this, paramIntent);
      }
    });
    return 3;
  }
  
  protected abstract Intent zzF(Intent paramIntent);
  
  public boolean zzH(Intent paramIntent)
  {
    return false;
  }
  
  boolean zzjr(int paramInt)
  {
    return stopSelfResult(paramInt);
  }
  
  public abstract void zzm(Intent paramIntent);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\firebase\iid\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */