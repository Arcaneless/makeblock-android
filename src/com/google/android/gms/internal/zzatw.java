package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzf.zzb;
import com.google.android.gms.common.internal.zzf.zzc;
import com.google.android.gms.common.zzc;
import com.google.android.gms.measurement.AppMeasurement.zzf;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class zzatw
  extends zzats
{
  private zzate zzbuA;
  private Boolean zzbuB;
  private final zzasv zzbuC;
  private final zzatz zzbuD;
  private final List<Runnable> zzbuE = new ArrayList();
  private final zzasv zzbuF;
  private final zza zzbuz;
  
  protected zzatw(zzatp paramzzatp)
  {
    super(paramzzatp);
    this.zzbuD = new zzatz(paramzzatp.zznq());
    this.zzbuz = new zza();
    this.zzbuC = new zzasv(paramzzatp)
    {
      public void run()
      {
        zzatw.zzb(zzatw.this);
      }
    };
    this.zzbuF = new zzasv(paramzzatp)
    {
      public void run()
      {
        zzatw.this.zzJt().zzLc().log("Tasks have been queued for a long time");
      }
    };
  }
  
  @WorkerThread
  private void onServiceDisconnected(ComponentName paramComponentName)
  {
    zzmq();
    if (this.zzbuA != null)
    {
      this.zzbuA = null;
      zzJt().zzLg().zzj("Disconnected from device MeasurementService", paramComponentName);
      zzLZ();
    }
  }
  
  private boolean zzLX()
  {
    zzJv().zzKk();
    List localList = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
    return (localList != null) && (localList.size() > 0);
  }
  
  @WorkerThread
  private void zzLZ()
  {
    zzmq();
    zzoc();
  }
  
  @WorkerThread
  private void zzMa()
  {
    zzmq();
    zzJt().zzLg().zzj("Processing queued up service tasks", Integer.valueOf(this.zzbuE.size()));
    Iterator localIterator = this.zzbuE.iterator();
    while (localIterator.hasNext())
    {
      Runnable localRunnable = (Runnable)localIterator.next();
      zzJs().zzm(localRunnable);
    }
    this.zzbuE.clear();
    this.zzbuF.cancel();
  }
  
  @WorkerThread
  private void zza(zzate paramzzate)
  {
    zzmq();
    zzac.zzw(paramzzate);
    this.zzbuA = paramzzate;
    zznN();
    zzMa();
  }
  
  @WorkerThread
  private void zznN()
  {
    zzmq();
    this.zzbuD.start();
    this.zzbuC.zzx(zzJv().zzoQ());
  }
  
  @WorkerThread
  private void zznO()
  {
    zzmq();
    if (!isConnected()) {
      return;
    }
    zzJt().zzLg().log("Inactivity, disconnecting from the service");
    disconnect();
  }
  
  @WorkerThread
  private void zzo(Runnable paramRunnable)
    throws IllegalStateException
  {
    zzmq();
    if (isConnected())
    {
      paramRunnable.run();
      return;
    }
    if (this.zzbuE.size() >= zzJv().zzKq())
    {
      zzJt().zzLa().log("Discarding data. Max runnable queue size reached");
      return;
    }
    this.zzbuE.add(paramRunnable);
    this.zzbuF.zzx(60000L);
    zzoc();
  }
  
  @WorkerThread
  public void disconnect()
  {
    zzmq();
    zznA();
    try
    {
      com.google.android.gms.common.stats.zza.zzyc().zza(getContext(), this.zzbuz);
      this.zzbuA = null;
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
  }
  
  @WorkerThread
  public boolean isConnected()
  {
    zzmq();
    zznA();
    return this.zzbuA != null;
  }
  
  @WorkerThread
  protected void zzLR()
  {
    zzmq();
    zznA();
    zzo(new Runnable()
    {
      public void run()
      {
        zzate localzzate = zzatw.zzc(zzatw.this);
        if (localzzate == null)
        {
          zzatw.this.zzJt().zzLa().log("Discarding data. Failed to send app launch");
          return;
        }
        try
        {
          zzatw.this.zza(localzzate, null);
          localzzate.zza(zzatw.this.zzJj().zzfH(zzatw.this.zzJt().zzLh()));
          zzatw.zzd(zzatw.this);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          zzatw.this.zzJt().zzLa().zzj("Failed to send app launch to the service", localRemoteException);
        }
      }
    });
  }
  
  @WorkerThread
  protected void zzLW()
  {
    zzmq();
    zznA();
    zzo(new Runnable()
    {
      public void run()
      {
        zzate localzzate = zzatw.zzc(zzatw.this);
        if (localzzate == null)
        {
          zzatw.this.zzJt().zzLa().log("Failed to send measurementEnabled to service");
          return;
        }
        try
        {
          localzzate.zzb(zzatw.this.zzJj().zzfH(zzatw.this.zzJt().zzLh()));
          zzatw.zzd(zzatw.this);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          zzatw.this.zzJt().zzLa().zzj("Failed to send measurementEnabled to the service", localRemoteException);
        }
      }
    });
  }
  
  @WorkerThread
  protected boolean zzLY()
  {
    zzmq();
    zznA();
    zzJv().zzKk();
    zzJt().zzLg().log("Checking service availability");
    switch (zzc.zzuz().isGooglePlayServicesAvailable(getContext()))
    {
    default: 
      return false;
    case 0: 
      zzJt().zzLg().log("Service available");
      return true;
    case 1: 
      zzJt().zzLg().log("Service missing");
      return false;
    case 18: 
      zzJt().zzLc().log("Service updating");
      return true;
    case 2: 
      zzJt().zzLf().log("Service container out of date");
      return true;
    case 3: 
      zzJt().zzLc().log("Service disabled");
      return false;
    }
    zzJt().zzLc().log("Service invalid");
    return false;
  }
  
  @WorkerThread
  void zza(zzate paramzzate, com.google.android.gms.common.internal.safeparcel.zza paramzza)
  {
    zzmq();
    zzJe();
    zznA();
    int j;
    ArrayList localArrayList;
    int k;
    label54:
    Object localObject;
    if (Build.VERSION.SDK_INT >= 11)
    {
      zzJv().zzKk();
      j = 1;
      localArrayList = new ArrayList();
      zzJv().zzKt();
      k = 0;
      i = 100;
      if ((k >= 1001) || (i != 100)) {
        break label300;
      }
      if (j == 0) {
        break label301;
      }
      localObject = zzJn().zzls(100);
      if (localObject == null) {
        break label301;
      }
      localArrayList.addAll((Collection)localObject);
    }
    label291:
    label300:
    label301:
    for (int i = ((List)localObject).size();; i = 0)
    {
      if ((paramzza != null) && (i < 100)) {
        localArrayList.add(paramzza);
      }
      localObject = localArrayList.iterator();
      for (;;)
      {
        if (!((Iterator)localObject).hasNext()) {
          break label291;
        }
        com.google.android.gms.common.internal.safeparcel.zza localzza = (com.google.android.gms.common.internal.safeparcel.zza)((Iterator)localObject).next();
        if ((localzza instanceof zzatb))
        {
          try
          {
            paramzzate.zza((zzatb)localzza, zzJj().zzfH(zzJt().zzLh()));
          }
          catch (RemoteException localRemoteException1)
          {
            zzJt().zzLa().zzj("Failed to send event to the service", localRemoteException1);
          }
          continue;
          j = 0;
          break;
        }
        if ((localRemoteException1 instanceof zzaub)) {
          try
          {
            paramzzate.zza((zzaub)localRemoteException1, zzJj().zzfH(zzJt().zzLh()));
          }
          catch (RemoteException localRemoteException2)
          {
            zzJt().zzLa().zzj("Failed to send attribute to the service", localRemoteException2);
          }
        } else {
          zzJt().zzLa().log("Discarding data. Unrecognized parcel type.");
        }
      }
      k += 1;
      break label54;
      return;
    }
  }
  
  @WorkerThread
  protected void zza(final AppMeasurement.zzf paramzzf)
  {
    zzmq();
    zznA();
    zzo(new Runnable()
    {
      public void run()
      {
        zzate localzzate = zzatw.zzc(zzatw.this);
        if (localzzate == null)
        {
          zzatw.this.zzJt().zzLa().log("Failed to send current screen to service");
          return;
        }
        for (;;)
        {
          try
          {
            if (paramzzf == null)
            {
              localzzate.zza(0L, null, null, zzatw.this.getContext().getPackageName());
              zzatw.zzd(zzatw.this);
              return;
            }
          }
          catch (RemoteException localRemoteException)
          {
            zzatw.this.zzJt().zzLa().zzj("Failed to send current screen to the service", localRemoteException);
            return;
          }
          localRemoteException.zza(paramzzf.zzbpB, paramzzf.zzbpz, paramzzf.zzbpA, zzatw.this.getContext().getPackageName());
        }
      }
    });
  }
  
  @WorkerThread
  public void zza(final AtomicReference<String> paramAtomicReference)
  {
    zzmq();
    zznA();
    zzo(new Runnable()
    {
      public void run()
      {
        localAtomicReference = paramAtomicReference;
        for (;;)
        {
          try
          {
            localzzate = zzatw.zzc(zzatw.this);
            if (localzzate == null) {
              zzatw.this.zzJt().zzLa().log("Failed to get app instance id");
            }
          }
          catch (RemoteException localRemoteException)
          {
            zzate localzzate;
            zzatw.this.zzJt().zzLa().zzj("Failed to get app instance id", localRemoteException);
            paramAtomicReference.notify();
            continue;
          }
          finally
          {
            paramAtomicReference.notify();
          }
          try
          {
            paramAtomicReference.notify();
            return;
          }
          finally {}
        }
        paramAtomicReference.set(localzzate.zzc(zzatw.this.zzJj().zzfH(null)));
        zzatw.zzd(zzatw.this);
        paramAtomicReference.notify();
      }
    });
  }
  
  @WorkerThread
  protected void zza(final AtomicReference<List<zzaub>> paramAtomicReference, final boolean paramBoolean)
  {
    zzmq();
    zznA();
    zzo(new Runnable()
    {
      public void run()
      {
        localAtomicReference = paramAtomicReference;
        for (;;)
        {
          try
          {
            localzzate = zzatw.zzc(zzatw.this);
            if (localzzate == null) {
              zzatw.this.zzJt().zzLa().log("Failed to get user properties");
            }
          }
          catch (RemoteException localRemoteException)
          {
            zzate localzzate;
            zzatw.this.zzJt().zzLa().zzj("Failed to get user properties", localRemoteException);
            paramAtomicReference.notify();
            continue;
          }
          finally
          {
            paramAtomicReference.notify();
          }
          try
          {
            paramAtomicReference.notify();
            return;
          }
          finally {}
        }
        paramAtomicReference.set(localzzate.zza(zzatw.this.zzJj().zzfH(null), paramBoolean));
        zzatw.zzd(zzatw.this);
        paramAtomicReference.notify();
      }
    });
  }
  
  @WorkerThread
  protected void zzb(final zzaub paramzzaub)
  {
    final boolean bool = true;
    zzmq();
    zznA();
    int i;
    if (Build.VERSION.SDK_INT >= 11)
    {
      zzJv().zzKk();
      i = 1;
      if ((i == 0) || (!zzJn().zza(paramzzaub))) {
        break label63;
      }
    }
    for (;;)
    {
      zzo(new Runnable()
      {
        public void run()
        {
          zzate localzzate = zzatw.zzc(zzatw.this);
          if (localzzate == null)
          {
            zzatw.this.zzJt().zzLa().log("Discarding data. Failed to set user attribute");
            return;
          }
          zzatw localzzatw = zzatw.this;
          if (bool) {}
          for (Object localObject = null;; localObject = paramzzaub)
          {
            localzzatw.zza(localzzate, (com.google.android.gms.common.internal.safeparcel.zza)localObject);
            zzatw.zzd(zzatw.this);
            return;
          }
        }
      });
      return;
      i = 0;
      break;
      label63:
      bool = false;
    }
  }
  
  @WorkerThread
  protected void zzc(final zzatb paramzzatb, final String paramString)
  {
    final boolean bool2 = true;
    zzac.zzw(paramzzatb);
    zzmq();
    zznA();
    final boolean bool1;
    if (Build.VERSION.SDK_INT >= 11)
    {
      zzJv().zzKk();
      bool1 = true;
      if ((!bool1) || (!zzJn().zza(paramzzatb))) {
        break label72;
      }
    }
    for (;;)
    {
      zzo(new Runnable()
      {
        public void run()
        {
          zzate localzzate = zzatw.zzc(zzatw.this);
          if (localzzate == null)
          {
            zzatw.this.zzJt().zzLa().log("Discarding data. Failed to send event to service");
            return;
          }
          Object localObject;
          if (bool1)
          {
            zzatw localzzatw = zzatw.this;
            if (bool2)
            {
              localObject = null;
              localzzatw.zza(localzzate, (com.google.android.gms.common.internal.safeparcel.zza)localObject);
            }
          }
          for (;;)
          {
            zzatw.zzd(zzatw.this);
            return;
            localObject = paramzzatb;
            break;
            try
            {
              if (!TextUtils.isEmpty(paramString)) {
                break label134;
              }
              localzzate.zza(paramzzatb, zzatw.this.zzJj().zzfH(zzatw.this.zzJt().zzLh()));
            }
            catch (RemoteException localRemoteException)
            {
              zzatw.this.zzJt().zzLa().zzj("Failed to send event to the service", localRemoteException);
            }
            continue;
            label134:
            localzzate.zza(paramzzatb, paramString, zzatw.this.zzJt().zzLh());
          }
        }
      });
      return;
      bool1 = false;
      break;
      label72:
      bool2 = false;
    }
  }
  
  protected void zzmr() {}
  
  @WorkerThread
  void zzoc()
  {
    zzmq();
    zznA();
    if (isConnected()) {
      return;
    }
    if (this.zzbuB == null)
    {
      this.zzbuB = zzJu().zzLn();
      if (this.zzbuB == null)
      {
        zzJt().zzLg().log("State of service unknown");
        this.zzbuB = Boolean.valueOf(zzLY());
        zzJu().zzaF(this.zzbuB.booleanValue());
      }
    }
    if (this.zzbuB.booleanValue())
    {
      zzJt().zzLg().log("Using measurement service");
      this.zzbuz.zzMb();
      return;
    }
    if (zzLX())
    {
      zzJt().zzLg().log("Using local app measurement service");
      Intent localIntent = new Intent("com.google.android.gms.measurement.START");
      Context localContext = getContext();
      zzJv().zzKk();
      localIntent.setComponent(new ComponentName(localContext, "com.google.android.gms.measurement.AppMeasurementService"));
      this.zzbuz.zzC(localIntent);
      return;
    }
    zzJt().zzLa().log("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
  }
  
  protected class zza
    implements ServiceConnection, zzf.zzb, zzf.zzc
  {
    private volatile boolean zzbuL;
    private volatile zzath zzbuM;
    
    protected zza() {}
    
    /* Error */
    @MainThread
    public void onConnected(@android.support.annotation.Nullable final android.os.Bundle paramBundle)
    {
      // Byte code:
      //   0: ldc 48
      //   2: invokestatic 54	com/google/android/gms/common/internal/zzac:zzdn	(Ljava/lang/String;)V
      //   5: aload_0
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 56	com/google/android/gms/internal/zzatw$zza:zzbuM	Lcom/google/android/gms/internal/zzath;
      //   11: invokevirtual 62	com/google/android/gms/internal/zzath:zzwW	()Landroid/os/IInterface;
      //   14: checkcast 64	com/google/android/gms/internal/zzate
      //   17: astore_1
      //   18: aload_0
      //   19: aconst_null
      //   20: putfield 56	com/google/android/gms/internal/zzatw$zza:zzbuM	Lcom/google/android/gms/internal/zzath;
      //   23: aload_0
      //   24: getfield 31	com/google/android/gms/internal/zzatw$zza:zzbuG	Lcom/google/android/gms/internal/zzatw;
      //   27: invokevirtual 68	com/google/android/gms/internal/zzatw:zzJs	()Lcom/google/android/gms/internal/zzato;
      //   30: new 19	com/google/android/gms/internal/zzatw$zza$3
      //   33: dup
      //   34: aload_0
      //   35: aload_1
      //   36: invokespecial 71	com/google/android/gms/internal/zzatw$zza$3:<init>	(Lcom/google/android/gms/internal/zzatw$zza;Lcom/google/android/gms/internal/zzate;)V
      //   39: invokevirtual 77	com/google/android/gms/internal/zzato:zzm	(Ljava/lang/Runnable;)V
      //   42: aload_0
      //   43: monitorexit
      //   44: return
      //   45: aload_0
      //   46: aconst_null
      //   47: putfield 56	com/google/android/gms/internal/zzatw$zza:zzbuM	Lcom/google/android/gms/internal/zzath;
      //   50: aload_0
      //   51: iconst_0
      //   52: putfield 38	com/google/android/gms/internal/zzatw$zza:zzbuL	Z
      //   55: goto -13 -> 42
      //   58: astore_1
      //   59: aload_0
      //   60: monitorexit
      //   61: aload_1
      //   62: athrow
      //   63: astore_1
      //   64: goto -19 -> 45
      //   67: astore_1
      //   68: goto -23 -> 45
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	71	0	this	zza
      //   0	71	1	paramBundle	android.os.Bundle
      // Exception table:
      //   from	to	target	type
      //   7	42	58	finally
      //   42	44	58	finally
      //   45	55	58	finally
      //   59	61	58	finally
      //   7	42	63	android/os/DeadObjectException
      //   7	42	67	java/lang/IllegalStateException
    }
    
    @MainThread
    public void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
    {
      zzac.zzdn("MeasurementServiceConnection.onConnectionFailed");
      zzati localzzati = zzatw.this.zzbpw.zzLu();
      if (localzzati != null) {
        localzzati.zzLc().zzj("Service connection failed", paramConnectionResult);
      }
      try
      {
        this.zzbuL = false;
        this.zzbuM = null;
        return;
      }
      finally {}
    }
    
    @MainThread
    public void onConnectionSuspended(int paramInt)
    {
      zzac.zzdn("MeasurementServiceConnection.onConnectionSuspended");
      zzatw.this.zzJt().zzLf().log("Service connection suspended");
      zzatw.this.zzJs().zzm(new Runnable()
      {
        public void run()
        {
          zzatw localzzatw = zzatw.this;
          Context localContext = zzatw.this.getContext();
          zzatw.this.zzJv().zzKk();
          zzatw.zza(localzzatw, new ComponentName(localContext, "com.google.android.gms.measurement.AppMeasurementService"));
        }
      });
    }
    
    /* Error */
    @MainThread
    public void onServiceConnected(final ComponentName paramComponentName, android.os.IBinder paramIBinder)
    {
      // Byte code:
      //   0: ldc -122
      //   2: invokestatic 54	com/google/android/gms/common/internal/zzac:zzdn	(Ljava/lang/String;)V
      //   5: aload_0
      //   6: monitorenter
      //   7: aload_2
      //   8: ifnonnull +26 -> 34
      //   11: aload_0
      //   12: iconst_0
      //   13: putfield 38	com/google/android/gms/internal/zzatw$zza:zzbuL	Z
      //   16: aload_0
      //   17: getfield 31	com/google/android/gms/internal/zzatw$zza:zzbuG	Lcom/google/android/gms/internal/zzatw;
      //   20: invokevirtual 115	com/google/android/gms/internal/zzatw:zzJt	()Lcom/google/android/gms/internal/zzati;
      //   23: invokevirtual 137	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
      //   26: ldc -117
      //   28: invokevirtual 123	com/google/android/gms/internal/zzati$zza:log	(Ljava/lang/String;)V
      //   31: aload_0
      //   32: monitorexit
      //   33: return
      //   34: aconst_null
      //   35: astore 4
      //   37: aconst_null
      //   38: astore_3
      //   39: aload 4
      //   41: astore_1
      //   42: aload_2
      //   43: invokeinterface 145 1 0
      //   48: astore 5
      //   50: aload 4
      //   52: astore_1
      //   53: ldc -109
      //   55: aload 5
      //   57: invokevirtual 153	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   60: ifeq +67 -> 127
      //   63: aload 4
      //   65: astore_1
      //   66: aload_2
      //   67: invokestatic 159	com/google/android/gms/internal/zzate$zza:zzer	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/zzate;
      //   70: astore_2
      //   71: aload_2
      //   72: astore_1
      //   73: aload_0
      //   74: getfield 31	com/google/android/gms/internal/zzatw$zza:zzbuG	Lcom/google/android/gms/internal/zzatw;
      //   77: invokevirtual 115	com/google/android/gms/internal/zzatw:zzJt	()Lcom/google/android/gms/internal/zzati;
      //   80: invokevirtual 162	com/google/android/gms/internal/zzati:zzLg	()Lcom/google/android/gms/internal/zzati$zza;
      //   83: ldc -92
      //   85: invokevirtual 123	com/google/android/gms/internal/zzati$zza:log	(Ljava/lang/String;)V
      //   88: aload_2
      //   89: astore_1
      //   90: aload_1
      //   91: ifnonnull +80 -> 171
      //   94: aload_0
      //   95: iconst_0
      //   96: putfield 38	com/google/android/gms/internal/zzatw$zza:zzbuL	Z
      //   99: invokestatic 170	com/google/android/gms/common/stats/zza:zzyc	()Lcom/google/android/gms/common/stats/zza;
      //   102: aload_0
      //   103: getfield 31	com/google/android/gms/internal/zzatw$zza:zzbuG	Lcom/google/android/gms/internal/zzatw;
      //   106: invokevirtual 174	com/google/android/gms/internal/zzatw:getContext	()Landroid/content/Context;
      //   109: aload_0
      //   110: getfield 31	com/google/android/gms/internal/zzatw$zza:zzbuG	Lcom/google/android/gms/internal/zzatw;
      //   113: invokestatic 177	com/google/android/gms/internal/zzatw:zza	(Lcom/google/android/gms/internal/zzatw;)Lcom/google/android/gms/internal/zzatw$zza;
      //   116: invokevirtual 180	com/google/android/gms/common/stats/zza:zza	(Landroid/content/Context;Landroid/content/ServiceConnection;)V
      //   119: aload_0
      //   120: monitorexit
      //   121: return
      //   122: astore_1
      //   123: aload_0
      //   124: monitorexit
      //   125: aload_1
      //   126: athrow
      //   127: aload 4
      //   129: astore_1
      //   130: aload_0
      //   131: getfield 31	com/google/android/gms/internal/zzatw$zza:zzbuG	Lcom/google/android/gms/internal/zzatw;
      //   134: invokevirtual 115	com/google/android/gms/internal/zzatw:zzJt	()Lcom/google/android/gms/internal/zzati;
      //   137: invokevirtual 137	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
      //   140: ldc -74
      //   142: aload 5
      //   144: invokevirtual 108	com/google/android/gms/internal/zzati$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
      //   147: aload_3
      //   148: astore_1
      //   149: goto -59 -> 90
      //   152: astore_2
      //   153: aload_0
      //   154: getfield 31	com/google/android/gms/internal/zzatw$zza:zzbuG	Lcom/google/android/gms/internal/zzatw;
      //   157: invokevirtual 115	com/google/android/gms/internal/zzatw:zzJt	()Lcom/google/android/gms/internal/zzati;
      //   160: invokevirtual 137	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
      //   163: ldc -72
      //   165: invokevirtual 123	com/google/android/gms/internal/zzati$zza:log	(Ljava/lang/String;)V
      //   168: goto -78 -> 90
      //   171: aload_0
      //   172: getfield 31	com/google/android/gms/internal/zzatw$zza:zzbuG	Lcom/google/android/gms/internal/zzatw;
      //   175: invokevirtual 68	com/google/android/gms/internal/zzatw:zzJs	()Lcom/google/android/gms/internal/zzato;
      //   178: new 15	com/google/android/gms/internal/zzatw$zza$1
      //   181: dup
      //   182: aload_0
      //   183: aload_1
      //   184: invokespecial 185	com/google/android/gms/internal/zzatw$zza$1:<init>	(Lcom/google/android/gms/internal/zzatw$zza;Lcom/google/android/gms/internal/zzate;)V
      //   187: invokevirtual 77	com/google/android/gms/internal/zzato:zzm	(Ljava/lang/Runnable;)V
      //   190: goto -71 -> 119
      //   193: astore_1
      //   194: goto -75 -> 119
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	197	0	this	zza
      //   0	197	1	paramComponentName	ComponentName
      //   0	197	2	paramIBinder	android.os.IBinder
      //   38	110	3	localObject1	Object
      //   35	93	4	localObject2	Object
      //   48	95	5	str	String
      // Exception table:
      //   from	to	target	type
      //   11	33	122	finally
      //   42	50	122	finally
      //   53	63	122	finally
      //   66	71	122	finally
      //   73	88	122	finally
      //   94	99	122	finally
      //   99	119	122	finally
      //   119	121	122	finally
      //   123	125	122	finally
      //   130	147	122	finally
      //   153	168	122	finally
      //   171	190	122	finally
      //   42	50	152	android/os/RemoteException
      //   53	63	152	android/os/RemoteException
      //   66	71	152	android/os/RemoteException
      //   73	88	152	android/os/RemoteException
      //   130	147	152	android/os/RemoteException
      //   99	119	193	java/lang/IllegalArgumentException
    }
    
    @MainThread
    public void onServiceDisconnected(final ComponentName paramComponentName)
    {
      zzac.zzdn("MeasurementServiceConnection.onServiceDisconnected");
      zzatw.this.zzJt().zzLf().log("Service disconnected");
      zzatw.this.zzJs().zzm(new Runnable()
      {
        public void run()
        {
          zzatw.zza(zzatw.this, paramComponentName);
        }
      });
    }
    
    @WorkerThread
    public void zzC(Intent paramIntent)
    {
      zzatw.this.zzmq();
      Context localContext = zzatw.this.getContext();
      com.google.android.gms.common.stats.zza localzza = com.google.android.gms.common.stats.zza.zzyc();
      try
      {
        if (this.zzbuL)
        {
          zzatw.this.zzJt().zzLg().log("Connection attempt already in progress");
          return;
        }
        this.zzbuL = true;
        localzza.zza(localContext, paramIntent, zzatw.zza(zzatw.this), 129);
        return;
      }
      finally {}
    }
    
    @WorkerThread
    public void zzMb()
    {
      zzatw.this.zzmq();
      Context localContext1 = zzatw.this.getContext();
      try
      {
        if (this.zzbuL)
        {
          zzatw.this.zzJt().zzLg().log("Connection attempt already in progress");
          return;
        }
        if (this.zzbuM != null)
        {
          zzatw.this.zzJt().zzLg().log("Already awaiting connection attempt");
          return;
        }
      }
      finally {}
      this.zzbuM = new zzath(localContext2, Looper.getMainLooper(), this, this);
      zzatw.this.zzJt().zzLg().log("Connecting to remote service");
      this.zzbuL = true;
      this.zzbuM.zzwT();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */