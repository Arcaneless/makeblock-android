package com.google.android.gms.common.internal;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import com.google.android.gms.common.stats.zza;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class zzo
  extends zzn
  implements Handler.Callback
{
  private final Handler mHandler;
  private final HashMap<zza, zzb> zzaEF = new HashMap();
  private final zza zzaEG;
  private final long zzaEH;
  private final Context zzvZ;
  
  zzo(Context paramContext)
  {
    this.zzvZ = paramContext.getApplicationContext();
    this.mHandler = new Handler(paramContext.getMainLooper(), this);
    this.zzaEG = zza.zzyc();
    this.zzaEH = 5000L;
  }
  
  private boolean zza(zza paramzza, ServiceConnection paramServiceConnection, String paramString)
  {
    zzac.zzb(paramServiceConnection, "ServiceConnection must not be null");
    for (;;)
    {
      zzb localzzb;
      synchronized (this.zzaEF)
      {
        localzzb = (zzb)this.zzaEF.get(paramzza);
        if (localzzb == null)
        {
          localzzb = new zzb(paramzza);
          localzzb.zza(paramServiceConnection, paramString);
          localzzb.zzdr(paramString);
          this.zzaEF.put(paramzza, localzzb);
          paramzza = localzzb;
          boolean bool = paramzza.isBound();
          return bool;
        }
        this.mHandler.removeMessages(0, paramzza);
        if (localzzb.zza(paramServiceConnection))
        {
          paramzza = String.valueOf(paramzza);
          throw new IllegalStateException(String.valueOf(paramzza).length() + 81 + "Trying to bind a GmsServiceConnection that was already connected before.  config=" + paramzza);
        }
      }
      localzzb.zza(paramServiceConnection, paramString);
      switch (localzzb.getState())
      {
      case 1: 
        paramServiceConnection.onServiceConnected(localzzb.getComponentName(), localzzb.getBinder());
        paramzza = localzzb;
        break;
      case 2: 
        localzzb.zzdr(paramString);
        paramzza = localzzb;
        break;
      default: 
        paramzza = localzzb;
      }
    }
  }
  
  private void zzb(zza paramzza, ServiceConnection paramServiceConnection, String paramString)
  {
    zzac.zzb(paramServiceConnection, "ServiceConnection must not be null");
    zzb localzzb;
    synchronized (this.zzaEF)
    {
      localzzb = (zzb)this.zzaEF.get(paramzza);
      if (localzzb == null)
      {
        paramzza = String.valueOf(paramzza);
        throw new IllegalStateException(String.valueOf(paramzza).length() + 50 + "Nonexistent connection status for service config: " + paramzza);
      }
    }
    if (!localzzb.zza(paramServiceConnection))
    {
      paramzza = String.valueOf(paramzza);
      throw new IllegalStateException(String.valueOf(paramzza).length() + 76 + "Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + paramzza);
    }
    localzzb.zzb(paramServiceConnection, paramString);
    if (localzzb.zzxt())
    {
      paramzza = this.mHandler.obtainMessage(0, paramzza);
      this.mHandler.sendMessageDelayed(paramzza, this.zzaEH);
    }
  }
  
  public boolean handleMessage(Message arg1)
  {
    switch (???.what)
    {
    default: 
      return false;
    }
    zza localzza = (zza)???.obj;
    synchronized (this.zzaEF)
    {
      zzb localzzb = (zzb)this.zzaEF.get(localzza);
      if ((localzzb != null) && (localzzb.zzxt()))
      {
        if (localzzb.isBound()) {
          localzzb.zzds("GmsClientSupervisor");
        }
        this.zzaEF.remove(localzza);
      }
      return true;
    }
  }
  
  public boolean zza(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString)
  {
    return zza(new zza(paramComponentName), paramServiceConnection, paramString);
  }
  
  public boolean zza(String paramString1, String paramString2, ServiceConnection paramServiceConnection, String paramString3)
  {
    return zza(new zza(paramString1, paramString2), paramServiceConnection, paramString3);
  }
  
  public void zzb(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString)
  {
    zzb(new zza(paramComponentName), paramServiceConnection, paramString);
  }
  
  public void zzb(String paramString1, String paramString2, ServiceConnection paramServiceConnection, String paramString3)
  {
    zzb(new zza(paramString1, paramString2), paramServiceConnection, paramString3);
  }
  
  private static final class zza
  {
    private final String zzaEI;
    private final ComponentName zzaEJ;
    private final String zzaca;
    
    public zza(ComponentName paramComponentName)
    {
      this.zzaca = null;
      this.zzaEI = null;
      this.zzaEJ = ((ComponentName)zzac.zzw(paramComponentName));
    }
    
    public zza(String paramString1, String paramString2)
    {
      this.zzaca = zzac.zzdv(paramString1);
      this.zzaEI = zzac.zzdv(paramString2);
      this.zzaEJ = null;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof zza)) {
          return false;
        }
        paramObject = (zza)paramObject;
      } while ((zzaa.equal(this.zzaca, ((zza)paramObject).zzaca)) && (zzaa.equal(this.zzaEJ, ((zza)paramObject).zzaEJ)));
      return false;
    }
    
    public int hashCode()
    {
      return zzaa.hashCode(new Object[] { this.zzaca, this.zzaEJ });
    }
    
    public String toString()
    {
      if (this.zzaca == null) {
        return this.zzaEJ.flattenToString();
      }
      return this.zzaca;
    }
    
    public Intent zzxs()
    {
      if (this.zzaca != null) {
        return new Intent(this.zzaca).setPackage(this.zzaEI);
      }
      return new Intent().setComponent(this.zzaEJ);
    }
  }
  
  private final class zzb
  {
    private int mState;
    private ComponentName zzaEJ;
    private final zza zzaEK;
    private final Set<ServiceConnection> zzaEL;
    private boolean zzaEM;
    private final zzo.zza zzaEN;
    private IBinder zzaEa;
    
    public zzb(zzo.zza paramzza)
    {
      this.zzaEN = paramzza;
      this.zzaEK = new zza();
      this.zzaEL = new HashSet();
      this.mState = 2;
    }
    
    public IBinder getBinder()
    {
      return this.zzaEa;
    }
    
    public ComponentName getComponentName()
    {
      return this.zzaEJ;
    }
    
    public int getState()
    {
      return this.mState;
    }
    
    public boolean isBound()
    {
      return this.zzaEM;
    }
    
    public void zza(ServiceConnection paramServiceConnection, String paramString)
    {
      zzo.zzc(zzo.this).zza(zzo.zzb(zzo.this), paramServiceConnection, paramString, this.zzaEN.zzxs());
      this.zzaEL.add(paramServiceConnection);
    }
    
    public boolean zza(ServiceConnection paramServiceConnection)
    {
      return this.zzaEL.contains(paramServiceConnection);
    }
    
    public void zzb(ServiceConnection paramServiceConnection, String paramString)
    {
      zzo.zzc(zzo.this).zzb(zzo.zzb(zzo.this), paramServiceConnection);
      this.zzaEL.remove(paramServiceConnection);
    }
    
    @TargetApi(14)
    public void zzdr(String paramString)
    {
      this.mState = 3;
      this.zzaEM = zzo.zzc(zzo.this).zza(zzo.zzb(zzo.this), paramString, this.zzaEN.zzxs(), this.zzaEK, 129);
      if (!this.zzaEM) {
        this.mState = 2;
      }
      try
      {
        zzo.zzc(zzo.this).zza(zzo.zzb(zzo.this), this.zzaEK);
        return;
      }
      catch (IllegalArgumentException paramString) {}
    }
    
    public void zzds(String paramString)
    {
      zzo.zzc(zzo.this).zza(zzo.zzb(zzo.this), this.zzaEK);
      this.zzaEM = false;
      this.mState = 2;
    }
    
    public boolean zzxt()
    {
      return this.zzaEL.isEmpty();
    }
    
    public class zza
      implements ServiceConnection
    {
      public zza() {}
      
      public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
      {
        synchronized (zzo.zza(zzo.this))
        {
          zzo.zzb.zza(zzo.zzb.this, paramIBinder);
          zzo.zzb.zza(zzo.zzb.this, paramComponentName);
          Iterator localIterator = zzo.zzb.zza(zzo.zzb.this).iterator();
          if (localIterator.hasNext()) {
            ((ServiceConnection)localIterator.next()).onServiceConnected(paramComponentName, paramIBinder);
          }
        }
        zzo.zzb.zza(zzo.zzb.this, 1);
      }
      
      public void onServiceDisconnected(ComponentName paramComponentName)
      {
        synchronized (zzo.zza(zzo.this))
        {
          zzo.zzb.zza(zzo.zzb.this, null);
          zzo.zzb.zza(zzo.zzb.this, paramComponentName);
          Iterator localIterator = zzo.zzb.zza(zzo.zzb.this).iterator();
          if (localIterator.hasNext()) {
            ((ServiceConnection)localIterator.next()).onServiceDisconnected(paramComponentName);
          }
        }
        zzo.zzb.zza(zzo.zzb.this, 2);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\common\internal\zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */