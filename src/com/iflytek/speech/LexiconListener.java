package com.iflytek.speech;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface LexiconListener
  extends IInterface
{
  public abstract void onLexiconUpdated(String paramString, int paramInt)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements LexiconListener
  {
    private static final String DESCRIPTOR = "com.iflytek.speech.LexiconListener";
    static final int TRANSACTION_onLexiconUpdated = 1;
    
    public Stub()
    {
      attachInterface(this, "com.iflytek.speech.LexiconListener");
    }
    
    public static LexiconListener asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.iflytek.speech.LexiconListener");
      if ((localIInterface != null) && ((localIInterface instanceof LexiconListener))) {
        return (LexiconListener)localIInterface;
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
        paramParcel2.writeString("com.iflytek.speech.LexiconListener");
        return true;
      }
      paramParcel1.enforceInterface("com.iflytek.speech.LexiconListener");
      onLexiconUpdated(paramParcel1.readString(), paramParcel1.readInt());
      return true;
    }
    
    private static class Proxy
      implements LexiconListener
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
        return "com.iflytek.speech.LexiconListener";
      }
      
      public void onLexiconUpdated(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.LexiconListener");
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\LexiconListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */