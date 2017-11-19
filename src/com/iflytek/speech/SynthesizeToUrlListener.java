package com.iflytek.speech;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface SynthesizeToUrlListener
  extends IInterface
{
  public abstract void onSynthesizeCompleted(String paramString, int paramInt)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements SynthesizeToUrlListener
  {
    private static final String DESCRIPTOR = "com.iflytek.speech.SynthesizeToUrlListener";
    static final int TRANSACTION_onSynthesizeCompleted = 1;
    
    public Stub()
    {
      attachInterface(this, "com.iflytek.speech.SynthesizeToUrlListener");
    }
    
    public static SynthesizeToUrlListener asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.iflytek.speech.SynthesizeToUrlListener");
      if ((localIInterface != null) && ((localIInterface instanceof SynthesizeToUrlListener))) {
        return (SynthesizeToUrlListener)localIInterface;
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
        paramParcel2.writeString("com.iflytek.speech.SynthesizeToUrlListener");
        return true;
      }
      paramParcel1.enforceInterface("com.iflytek.speech.SynthesizeToUrlListener");
      onSynthesizeCompleted(paramParcel1.readString(), paramParcel1.readInt());
      return true;
    }
    
    private static class Proxy
      implements SynthesizeToUrlListener
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
        return "com.iflytek.speech.SynthesizeToUrlListener";
      }
      
      public void onSynthesizeCompleted(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.SynthesizeToUrlListener");
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\SynthesizeToUrlListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */