package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzf<T extends IInterface>
{
  public static final String[] zzaDT = { "service_esmobile", "service_googleme" };
  private final Context mContext;
  final Handler mHandler;
  private int zzaDB;
  private long zzaDC;
  private long zzaDD;
  private int zzaDE;
  private long zzaDF;
  private final zzn zzaDG;
  private final Object zzaDH = new Object();
  private zzv zzaDI;
  protected zzf zzaDJ;
  private T zzaDK;
  private final ArrayList<zze<?>> zzaDL = new ArrayList();
  private zzh zzaDM;
  private int zzaDN = 1;
  private final zzb zzaDO;
  private final zzc zzaDP;
  private final int zzaDQ;
  private final String zzaDR;
  protected AtomicInteger zzaDS = new AtomicInteger(0);
  private final zzc zzazw;
  private final Object zzrN = new Object();
  private final Looper zzrx;
  
  protected zzf(Context paramContext, Looper paramLooper, int paramInt, zzb paramzzb, zzc paramzzc, String paramString)
  {
    this(paramContext, paramLooper, zzn.zzaC(paramContext), zzc.zzuz(), paramInt, (zzb)zzac.zzw(paramzzb), (zzc)zzac.zzw(paramzzc), paramString);
  }
  
  protected zzf(Context paramContext, Looper paramLooper, zzn paramzzn, zzc paramzzc, int paramInt, zzb paramzzb, zzc paramzzc1, String paramString)
  {
    this.mContext = ((Context)zzac.zzb(paramContext, "Context must not be null"));
    this.zzrx = ((Looper)zzac.zzb(paramLooper, "Looper must not be null"));
    this.zzaDG = ((zzn)zzac.zzb(paramzzn, "Supervisor must not be null"));
    this.zzazw = ((zzc)zzac.zzb(paramzzc, "API availability must not be null"));
    this.mHandler = new zzd(paramLooper);
    this.zzaDQ = paramInt;
    this.zzaDO = paramzzb;
    this.zzaDP = paramzzc1;
    this.zzaDR = paramString;
  }
  
  private void zza(int paramInt, T paramT)
  {
    boolean bool = true;
    int i;
    int j;
    if (paramInt == 3)
    {
      i = 1;
      if (paramT == null) {
        break label116;
      }
      j = 1;
      label17:
      if (i != j) {
        break label122;
      }
    }
    for (;;)
    {
      zzac.zzas(bool);
      for (;;)
      {
        synchronized (this.zzrN)
        {
          this.zzaDN = paramInt;
          this.zzaDK = paramT;
          switch (paramInt)
          {
          case 2: 
            return;
            zzwR();
          }
        }
        zza(paramT);
        continue;
        zzwS();
      }
      i = 0;
      break;
      label116:
      j = 0;
      break label17;
      label122:
      bool = false;
    }
  }
  
  private boolean zza(int paramInt1, int paramInt2, T paramT)
  {
    synchronized (this.zzrN)
    {
      if (this.zzaDN != paramInt1) {
        return false;
      }
      zza(paramInt2, paramT);
      return true;
    }
  }
  
  private void zzm(ConnectionResult paramConnectionResult)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaDS.get(), paramConnectionResult.getErrorCode(), paramConnectionResult.getResolution()));
  }
  
  private void zzwR()
  {
    String str1;
    String str2;
    if (this.zzaDM != null)
    {
      str1 = String.valueOf(zzeu());
      str2 = String.valueOf(zzwP());
      Log.e("GmsClient", String.valueOf(str1).length() + 70 + String.valueOf(str2).length() + "Calling connect() while still connected, missing disconnect() for " + str1 + " on " + str2);
      this.zzaDG.zzb(zzeu(), zzwP(), this.zzaDM, zzwQ());
      this.zzaDS.incrementAndGet();
    }
    this.zzaDM = new zzh(this.zzaDS.get());
    if (!this.zzaDG.zza(zzeu(), zzwP(), this.zzaDM, zzwQ()))
    {
      str1 = String.valueOf(zzeu());
      str2 = String.valueOf(zzwP());
      Log.e("GmsClient", String.valueOf(str1).length() + 34 + String.valueOf(str2).length() + "unable to connect to service: " + str1 + " on " + str2);
      zza(16, null, this.zzaDS.get());
    }
  }
  
  private void zzwS()
  {
    if (this.zzaDM != null)
    {
      this.zzaDG.zzb(zzeu(), zzwP(), this.zzaDM, zzwQ());
      this.zzaDM = null;
    }
  }
  
  public void disconnect()
  {
    this.zzaDS.incrementAndGet();
    synchronized (this.zzaDL)
    {
      int j = this.zzaDL.size();
      int i = 0;
      while (i < j)
      {
        ((zze)this.zzaDL.get(i)).zzxb();
        i += 1;
      }
      this.zzaDL.clear();
    }
    synchronized (this.zzaDH)
    {
      this.zzaDI = null;
      zza(1, null);
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
  }
  
  public void dump(String paramString, FileDescriptor arg2, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    for (;;)
    {
      synchronized (this.zzrN)
      {
        int i = this.zzaDN;
        paramArrayOfString = this.zzaDK;
        paramPrintWriter.append(paramString).append("mConnectState=");
        switch (i)
        {
        default: 
          paramPrintWriter.print("UNKNOWN");
          paramPrintWriter.append(" mService=");
          if (paramArrayOfString != null) {
            break label482;
          }
          paramPrintWriter.println("null");
          ??? = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
          long l;
          String str;
          if (this.zzaDD > 0L)
          {
            paramArrayOfString = paramPrintWriter.append(paramString).append("lastConnectedTime=");
            l = this.zzaDD;
            str = String.valueOf(???.format(new Date(this.zzaDD)));
            paramArrayOfString.println(String.valueOf(str).length() + 21 + l + " " + str);
          }
          if (this.zzaDC > 0L) {
            paramPrintWriter.append(paramString).append("lastSuspendedCause=");
          }
          switch (this.zzaDB)
          {
          default: 
            paramPrintWriter.append(String.valueOf(this.zzaDB));
            paramArrayOfString = paramPrintWriter.append(" lastSuspendedTime=");
            l = this.zzaDC;
            str = String.valueOf(???.format(new Date(this.zzaDC)));
            paramArrayOfString.println(String.valueOf(str).length() + 21 + l + " " + str);
            if (this.zzaDF > 0L)
            {
              paramPrintWriter.append(paramString).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzaDE));
              paramString = paramPrintWriter.append(" lastFailedTime=");
              l = this.zzaDF;
              ??? = String.valueOf(???.format(new Date(this.zzaDF)));
              paramString.println(String.valueOf(???).length() + 21 + l + " " + ???);
            }
            return;
          }
          break;
        }
      }
      paramPrintWriter.print("CONNECTING");
      continue;
      paramPrintWriter.print("CONNECTED");
      continue;
      paramPrintWriter.print("DISCONNECTING");
      continue;
      paramPrintWriter.print("DISCONNECTED");
      continue;
      label482:
      paramPrintWriter.append(zzev()).append("@").println(Integer.toHexString(System.identityHashCode(paramArrayOfString.asBinder())));
      continue;
      paramPrintWriter.append("CAUSE_SERVICE_DISCONNECTED");
      continue;
      paramPrintWriter.append("CAUSE_NETWORK_LOST");
    }
  }
  
  public Account getAccount()
  {
    return null;
  }
  
  public final Context getContext()
  {
    return this.mContext;
  }
  
  public final Looper getLooper()
  {
    return this.zzrx;
  }
  
  public boolean isConnected()
  {
    for (;;)
    {
      synchronized (this.zzrN)
      {
        if (this.zzaDN == 3)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public boolean isConnecting()
  {
    for (;;)
    {
      synchronized (this.zzrN)
      {
        if (this.zzaDN == 2)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  @CallSuper
  protected void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    this.zzaDE = paramConnectionResult.getErrorCode();
    this.zzaDF = System.currentTimeMillis();
  }
  
  @CallSuper
  protected void onConnectionSuspended(int paramInt)
  {
    this.zzaDB = paramInt;
    this.zzaDC = System.currentTimeMillis();
  }
  
  protected void zza(int paramInt1, @Nullable Bundle paramBundle, int paramInt2)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(5, paramInt2, -1, new zzk(paramInt1, paramBundle)));
  }
  
  @BinderThread
  protected void zza(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramInt2, -1, new zzj(paramInt1, paramIBinder, paramBundle)));
  }
  
  @CallSuper
  protected void zza(@NonNull T paramT)
  {
    this.zzaDD = System.currentTimeMillis();
  }
  
  public void zza(@NonNull zzf paramzzf)
  {
    this.zzaDJ = ((zzf)zzac.zzb(paramzzf, "Connection progress callbacks cannot be null."));
    zza(2, null);
  }
  
  public void zza(zzf paramzzf, ConnectionResult paramConnectionResult)
  {
    this.zzaDJ = ((zzf)zzac.zzb(paramzzf, "Connection progress callbacks cannot be null."));
    this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaDS.get(), paramConnectionResult.getErrorCode(), paramConnectionResult.getResolution()));
  }
  
  @WorkerThread
  public void zza(zzr arg1, Set<Scope> paramSet)
  {
    Object localObject = zzql();
    localObject = new zzj(this.zzaDQ).zzdq(this.mContext.getPackageName()).zzp((Bundle)localObject);
    if (paramSet != null) {
      ((zzj)localObject).zzf(paramSet);
    }
    if (zzqD()) {
      ((zzj)localObject).zze(zzwU()).zzb(???);
    }
    try
    {
      synchronized (this.zzaDH)
      {
        while (this.zzaDI != null)
        {
          this.zzaDI.zza(new zzg(this, this.zzaDS.get()), (zzj)localObject);
          return;
          if (zzwX()) {
            ((zzj)localObject).zze(getAccount());
          }
        }
        Log.w("GmsClient", "mServiceBroker is null, client disconnected");
      }
      return;
    }
    catch (DeadObjectException ???)
    {
      Log.w("GmsClient", "service died");
      zzcM(1);
      return;
    }
    catch (RemoteException ???)
    {
      Log.w("GmsClient", "Remote exception occurred", ???);
      return;
    }
    catch (SecurityException ???)
    {
      throw ???;
    }
    catch (RuntimeException ???)
    {
      Log.w("GmsClient", "IGmsServiceBroker.getService failed", ???);
      zzm(new ConnectionResult(8, null, "IGmsServiceBroker.getService failed."));
    }
  }
  
  public void zzcM(int paramInt)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.zzaDS.get(), paramInt));
  }
  
  @NonNull
  protected abstract String zzeu();
  
  @NonNull
  protected abstract String zzev();
  
  @Nullable
  protected abstract T zzh(IBinder paramIBinder);
  
  public boolean zzqD()
  {
    return false;
  }
  
  public boolean zzqS()
  {
    return false;
  }
  
  public Intent zzqT()
  {
    throw new UnsupportedOperationException("Not a sign in API");
  }
  
  protected Bundle zzql()
  {
    return new Bundle();
  }
  
  public boolean zzuI()
  {
    return true;
  }
  
  @Nullable
  public IBinder zzuJ()
  {
    synchronized (this.zzaDH)
    {
      if (this.zzaDI == null) {
        return null;
      }
      IBinder localIBinder = this.zzaDI.asBinder();
      return localIBinder;
    }
  }
  
  public Bundle zzud()
  {
    return null;
  }
  
  protected String zzwP()
  {
    return "com.google.android.gms";
  }
  
  @Nullable
  protected final String zzwQ()
  {
    if (this.zzaDR == null) {
      return this.mContext.getClass().getName();
    }
    return this.zzaDR;
  }
  
  public void zzwT()
  {
    int i = this.zzazw.isGooglePlayServicesAvailable(this.mContext);
    if (i != 0)
    {
      zza(1, null);
      this.zzaDJ = new zzi();
      this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaDS.get(), i));
      return;
    }
    zza(new zzi());
  }
  
  public final Account zzwU()
  {
    if (getAccount() != null) {
      return getAccount();
    }
    return new Account("<<default account>>", "com.google");
  }
  
  protected final void zzwV()
  {
    if (!isConnected()) {
      throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
    }
  }
  
  public final T zzwW()
    throws DeadObjectException
  {
    synchronized (this.zzrN)
    {
      if (this.zzaDN == 4) {
        throw new DeadObjectException();
      }
    }
    zzwV();
    if (this.zzaDK != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zza(bool, "Client is connected but service is null");
      IInterface localIInterface = this.zzaDK;
      return localIInterface;
    }
  }
  
  public boolean zzwX()
  {
    return false;
  }
  
  protected Set<Scope> zzwY()
  {
    return Collections.EMPTY_SET;
  }
  
  private abstract class zza
    extends zzf.zze<Boolean>
  {
    public final int statusCode;
    public final Bundle zzaDU;
    
    @BinderThread
    protected zza(int paramInt, Bundle paramBundle)
    {
      super(Boolean.valueOf(true));
      this.statusCode = paramInt;
      this.zzaDU = paramBundle;
    }
    
    protected void zzc(Boolean paramBoolean)
    {
      Object localObject = null;
      if (paramBoolean == null) {
        zzf.zza(zzf.this, 1, null);
      }
      do
      {
        return;
        switch (this.statusCode)
        {
        default: 
          zzf.zza(zzf.this, 1, null);
          paramBoolean = (Boolean)localObject;
          if (this.zzaDU != null) {
            paramBoolean = (PendingIntent)this.zzaDU.getParcelable("pendingIntent");
          }
          zzn(new ConnectionResult(this.statusCode, paramBoolean));
          return;
        }
      } while (zzwZ());
      zzf.zza(zzf.this, 1, null);
      zzn(new ConnectionResult(8, null));
      return;
      zzf.zza(zzf.this, 1, null);
      throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
    }
    
    protected abstract void zzn(ConnectionResult paramConnectionResult);
    
    protected abstract boolean zzwZ();
  }
  
  public static abstract interface zzb
  {
    public abstract void onConnected(@Nullable Bundle paramBundle);
    
    public abstract void onConnectionSuspended(int paramInt);
  }
  
  public static abstract interface zzc
  {
    public abstract void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult);
  }
  
  final class zzd
    extends Handler
  {
    public zzd(Looper paramLooper)
    {
      super();
    }
    
    private void zza(Message paramMessage)
    {
      ((zzf.zze)paramMessage.obj).unregister();
    }
    
    private boolean zzb(Message paramMessage)
    {
      return (paramMessage.what == 2) || (paramMessage.what == 1) || (paramMessage.what == 5);
    }
    
    public void handleMessage(Message paramMessage)
    {
      PendingIntent localPendingIntent = null;
      if (zzf.this.zzaDS.get() != paramMessage.arg1)
      {
        if (zzb(paramMessage)) {
          zza(paramMessage);
        }
        return;
      }
      if (((paramMessage.what == 1) || (paramMessage.what == 5)) && (!zzf.this.isConnecting()))
      {
        zza(paramMessage);
        return;
      }
      if (paramMessage.what == 3)
      {
        if ((paramMessage.obj instanceof PendingIntent)) {
          localPendingIntent = (PendingIntent)paramMessage.obj;
        }
        paramMessage = new ConnectionResult(paramMessage.arg2, localPendingIntent);
        zzf.this.zzaDJ.zzg(paramMessage);
        zzf.this.onConnectionFailed(paramMessage);
        return;
      }
      if (paramMessage.what == 4)
      {
        zzf.zza(zzf.this, 4, null);
        if (zzf.zzb(zzf.this) != null) {
          zzf.zzb(zzf.this).onConnectionSuspended(paramMessage.arg2);
        }
        zzf.this.onConnectionSuspended(paramMessage.arg2);
        zzf.zza(zzf.this, 4, 1, null);
        return;
      }
      if ((paramMessage.what == 2) && (!zzf.this.isConnected()))
      {
        zza(paramMessage);
        return;
      }
      if (zzb(paramMessage))
      {
        ((zzf.zze)paramMessage.obj).zzxa();
        return;
      }
      int i = paramMessage.what;
      Log.wtf("GmsClient", 45 + "Don't know how to handle message: " + i, new Exception());
    }
  }
  
  protected abstract class zze<TListener>
  {
    private TListener mListener;
    private boolean zzaDW;
    
    public zze()
    {
      Object localObject;
      this.mListener = localObject;
      this.zzaDW = false;
    }
    
    public void unregister()
    {
      zzxb();
      synchronized (zzf.zzc(zzf.this))
      {
        zzf.zzc(zzf.this).remove(this);
        return;
      }
    }
    
    protected abstract void zzu(TListener paramTListener);
    
    /* Error */
    public void zzxa()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 24	com/google/android/gms/common/internal/zzf$zze:mListener	Ljava/lang/Object;
      //   6: astore_1
      //   7: aload_0
      //   8: getfield 26	com/google/android/gms/common/internal/zzf$zze:zzaDW	Z
      //   11: ifeq +48 -> 59
      //   14: aload_0
      //   15: invokestatic 54	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   18: astore_2
      //   19: ldc 56
      //   21: new 58	java/lang/StringBuilder
      //   24: dup
      //   25: aload_2
      //   26: invokestatic 54	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   29: invokevirtual 62	java/lang/String:length	()I
      //   32: bipush 47
      //   34: iadd
      //   35: invokespecial 65	java/lang/StringBuilder:<init>	(I)V
      //   38: ldc 67
      //   40: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   43: aload_2
      //   44: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   47: ldc 73
      //   49: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   52: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   55: invokestatic 83	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   58: pop
      //   59: aload_0
      //   60: monitorexit
      //   61: aload_1
      //   62: ifnull +8 -> 70
      //   65: aload_0
      //   66: aload_1
      //   67: invokevirtual 85	com/google/android/gms/common/internal/zzf$zze:zzu	(Ljava/lang/Object;)V
      //   70: aload_0
      //   71: monitorenter
      //   72: aload_0
      //   73: iconst_1
      //   74: putfield 26	com/google/android/gms/common/internal/zzf$zze:zzaDW	Z
      //   77: aload_0
      //   78: monitorexit
      //   79: aload_0
      //   80: invokevirtual 87	com/google/android/gms/common/internal/zzf$zze:unregister	()V
      //   83: return
      //   84: astore_1
      //   85: aload_0
      //   86: monitorexit
      //   87: aload_1
      //   88: athrow
      //   89: astore_1
      //   90: aload_1
      //   91: athrow
      //   92: astore_1
      //   93: aload_0
      //   94: monitorexit
      //   95: aload_1
      //   96: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	97	0	this	zze
      //   6	61	1	localObject1	Object
      //   84	4	1	localObject2	Object
      //   89	2	1	localRuntimeException	RuntimeException
      //   92	4	1	localObject3	Object
      //   18	26	2	str	String
      // Exception table:
      //   from	to	target	type
      //   2	59	84	finally
      //   59	61	84	finally
      //   85	87	84	finally
      //   65	70	89	java/lang/RuntimeException
      //   72	79	92	finally
      //   93	95	92	finally
    }
    
    public void zzxb()
    {
      try
      {
        this.mListener = null;
        return;
      }
      finally {}
    }
  }
  
  public static abstract interface zzf
  {
    public abstract void zzg(@NonNull ConnectionResult paramConnectionResult);
  }
  
  public static final class zzg
    extends zzu.zza
  {
    private zzf zzaDX;
    private final int zzaDY;
    
    public zzg(@NonNull zzf paramzzf, int paramInt)
    {
      this.zzaDX = paramzzf;
      this.zzaDY = paramInt;
    }
    
    private void zzxc()
    {
      this.zzaDX = null;
    }
    
    @BinderThread
    public void zza(int paramInt, @NonNull IBinder paramIBinder, @Nullable Bundle paramBundle)
    {
      zzac.zzb(this.zzaDX, "onPostInitComplete can be called only once per call to getRemoteService");
      this.zzaDX.zza(paramInt, paramIBinder, paramBundle, this.zzaDY);
      zzxc();
    }
    
    @BinderThread
    public void zzb(int paramInt, @Nullable Bundle paramBundle)
    {
      Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }
  }
  
  public final class zzh
    implements ServiceConnection
  {
    private final int zzaDY;
    
    public zzh(int paramInt)
    {
      this.zzaDY = paramInt;
    }
    
    public void onServiceConnected(ComponentName arg1, IBinder paramIBinder)
    {
      if (paramIBinder == null)
      {
        zzf.zza(zzf.this, new ConnectionResult(8, null, "ServiceBroker IBinder is null"));
        return;
      }
      synchronized (zzf.zza(zzf.this))
      {
        zzf.zza(zzf.this, zzv.zza.zzbu(paramIBinder));
        zzf.this.zza(0, null, this.zzaDY);
        return;
      }
    }
    
    public void onServiceDisconnected(ComponentName arg1)
    {
      synchronized (zzf.zza(zzf.this))
      {
        zzf.zza(zzf.this, null);
        zzf.this.mHandler.sendMessage(zzf.this.mHandler.obtainMessage(4, this.zzaDY, 1));
        return;
      }
    }
  }
  
  protected class zzi
    implements zzf.zzf
  {
    public zzi() {}
    
    public void zzg(@NonNull ConnectionResult paramConnectionResult)
    {
      if (paramConnectionResult.isSuccess()) {
        zzf.this.zza(null, zzf.this.zzwY());
      }
      while (zzf.zzd(zzf.this) == null) {
        return;
      }
      zzf.zzd(zzf.this).onConnectionFailed(paramConnectionResult);
    }
  }
  
  protected final class zzj
    extends zzf.zza
  {
    public final IBinder zzaDZ;
    
    @BinderThread
    public zzj(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    {
      super(paramInt, paramBundle);
      this.zzaDZ = paramIBinder;
    }
    
    protected void zzn(ConnectionResult paramConnectionResult)
    {
      if (zzf.zzd(zzf.this) != null) {
        zzf.zzd(zzf.this).onConnectionFailed(paramConnectionResult);
      }
      zzf.this.onConnectionFailed(paramConnectionResult);
    }
    
    protected boolean zzwZ()
    {
      do
      {
        try
        {
          String str1 = this.zzaDZ.getInterfaceDescriptor();
          if (!zzf.this.zzev().equals(str1))
          {
            String str2 = String.valueOf(zzf.this.zzev());
            Log.e("GmsClient", String.valueOf(str2).length() + 34 + String.valueOf(str1).length() + "service descriptor mismatch: " + str2 + " vs. " + str1);
            return false;
          }
        }
        catch (RemoteException localRemoteException)
        {
          Log.w("GmsClient", "service probably died");
          return false;
        }
        localObject = zzf.this.zzh(this.zzaDZ);
      } while ((localObject == null) || (!zzf.zza(zzf.this, 2, 3, (IInterface)localObject)));
      Object localObject = zzf.this.zzud();
      if (zzf.zzb(zzf.this) != null) {
        zzf.zzb(zzf.this).onConnected((Bundle)localObject);
      }
      return true;
    }
  }
  
  protected final class zzk
    extends zzf.zza
  {
    @BinderThread
    public zzk(int paramInt, @Nullable Bundle paramBundle)
    {
      super(paramInt, paramBundle);
    }
    
    protected void zzn(ConnectionResult paramConnectionResult)
    {
      zzf.this.zzaDJ.zzg(paramConnectionResult);
      zzf.this.onConnectionFailed(paramConnectionResult);
    }
    
    protected boolean zzwZ()
    {
      zzf.this.zzaDJ.zzg(ConnectionResult.zzawX);
      return true;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\common\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */