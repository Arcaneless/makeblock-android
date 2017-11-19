package com.iflytek.speech;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface GrammarListener
  extends IInterface
{
  public abstract void onBuildFinish(String paramString, int paramInt)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements GrammarListener
  {
    private static final String DESCRIPTOR = "com.iflytek.speech.GrammarListener";
    static final int TRANSACTION_onBuildFinish = 1;
    
    public Stub()
    {
      attachInterface(this, "com.iflytek.speech.GrammarListener");
    }
    
    public static GrammarListener asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.iflytek.speech.GrammarListener");
      if ((localIInterface != null) && ((localIInterface instanceof GrammarListener))) {
        return (GrammarListener)localIInterface;
      }
      return new Proxy(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.iflytek.speech.GrammarListener");
        return true;
      }
      paramParcel1.enforceInterface("com.iflytek.speech.GrammarListener");
      onBuildFinish(paramParcel1.readString(), paramParcel1.readInt());
      return true;
    }
    
    private static class Proxy
      implements GrammarListener
    {
      private IBinder mRemote;
      
      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.iflytek.speech.GrammarListener";
      }
      
      public void onBuildFinish(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.GrammarListener");
          localParcel.writeString(paramString);
          localParcel.writeInt(paramInt);
          this.mRemote.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\GrammarListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */