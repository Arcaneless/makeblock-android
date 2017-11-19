package com.umeng.analytics.pro;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class br
{
  public static String a(Context paramContext)
  {
    do
    {
      try
      {
        paramContext = b(paramContext);
      }
      catch (Exception paramContext)
      {
        return null;
      }
      paramContext = a.a(paramContext);
      return paramContext;
    } while (paramContext != null);
    return null;
  }
  
  private static a b(Context paramContext)
    throws Exception
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo("com.android.vending", 0);
      b localb = new b(null);
      Object localObject1 = new Intent("com.google.android.gms.ads.identifier.service.START");
      ((Intent)localObject1).setPackage("com.google.android.gms");
      if (paramContext.bindService((Intent)localObject1, localb, 1)) {}
      throw new IOException("Google Play connection failed");
    }
    catch (Exception paramContext)
    {
      try
      {
        localObject1 = new c(localb.a());
        localObject1 = new a(((c)localObject1).a(), ((c)localObject1).a(true));
        return (a)localObject1;
      }
      catch (Exception localException)
      {
        throw localException;
      }
      finally
      {
        paramContext.unbindService(localb);
      }
      paramContext = paramContext;
      throw paramContext;
    }
  }
  
  private static final class a
  {
    private final String a;
    private final boolean b;
    
    a(String paramString, boolean paramBoolean)
    {
      this.a = paramString;
      this.b = paramBoolean;
    }
    
    private String b()
    {
      return this.a;
    }
    
    public boolean a()
    {
      return this.b;
    }
  }
  
  private static final class b
    implements ServiceConnection
  {
    boolean a = false;
    private final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue(1);
    
    public IBinder a()
      throws InterruptedException
    {
      if (this.a) {
        throw new IllegalStateException();
      }
      this.a = true;
      return (IBinder)this.b.take();
    }
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      try
      {
        this.b.put(paramIBinder);
        return;
      }
      catch (InterruptedException paramComponentName) {}
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName) {}
  }
  
  private static final class c
    implements IInterface
  {
    private IBinder a;
    
    public c(IBinder paramIBinder)
    {
      this.a = paramIBinder;
    }
    
    public String a()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        this.a.transact(1, localParcel1, localParcel2, 0);
        localParcel2.readException();
        String str = localParcel2.readString();
        return str;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public boolean a(boolean paramBoolean)
      throws RemoteException
    {
      boolean bool = true;
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      for (;;)
      {
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        localParcel1.writeInt(i);
        this.a.transact(2, localParcel1, localParcel2, 0);
        localParcel2.readException();
        int i = localParcel2.readInt();
        if (i != 0)
        {
          paramBoolean = bool;
          localParcel2.recycle();
          localParcel1.recycle();
          return paramBoolean;
          i = 0;
        }
        else
        {
          paramBoolean = false;
          continue;
          if (paramBoolean) {
            i = 1;
          }
        }
      }
    }
    
    public IBinder asBinder()
    {
      return this.a;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\br.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */