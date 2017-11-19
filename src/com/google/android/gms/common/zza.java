package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zza
  implements ServiceConnection
{
  boolean zzawV = false;
  private final BlockingQueue<IBinder> zzawW = new LinkedBlockingQueue();
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    this.zzawW.add(paramIBinder);
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {}
  
  public IBinder zza(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, TimeoutException
  {
    zzac.zzdo("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
    if (this.zzawV) {
      throw new IllegalStateException("Cannot call get on this connection more than once");
    }
    this.zzawV = true;
    paramTimeUnit = (IBinder)this.zzawW.poll(paramLong, paramTimeUnit);
    if (paramTimeUnit == null) {
      throw new TimeoutException("Timed out waiting for the service connection");
    }
    return paramTimeUnit;
  }
  
  public IBinder zzuy()
    throws InterruptedException
  {
    zzac.zzdo("BlockingServiceConnection.getService() called on main thread");
    if (this.zzawV) {
      throw new IllegalStateException("Cannot call get on this connection more than once");
    }
    this.zzawV = true;
    return (IBinder)this.zzawW.take();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\common\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */