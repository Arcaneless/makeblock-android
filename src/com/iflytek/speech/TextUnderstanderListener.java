package com.iflytek.speech;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface TextUnderstanderListener
  extends IInterface
{
  public abstract void onError(int paramInt)
    throws RemoteException;
  
  public abstract void onResult(UnderstanderResult paramUnderstanderResult)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements TextUnderstanderListener
  {
    private static final String DESCRIPTOR = "com.iflytek.speech.TextUnderstanderListener";
    static final int TRANSACTION_onError = 2;
    static final int TRANSACTION_onResult = 1;
    
    public Stub()
    {
      attachInterface(this, "com.iflytek.speech.TextUnderstanderListener");
    }
    
    public static TextUnderstanderListener asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.iflytek.speech.TextUnderstanderListener");
      if ((localIInterface != null) && ((localIInterface instanceof TextUnderstanderListener))) {
        return (TextUnderstanderListener)localIInterface;
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
        paramParcel2.writeString("com.iflytek.speech.TextUnderstanderListener");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.iflytek.speech.TextUnderstanderListener");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (UnderstanderResult)UnderstanderResult.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          onResult(paramParcel1);
          return true;
        }
      }
      paramParcel1.enforceInterface("com.iflytek.speech.TextUnderstanderListener");
      onError(paramParcel1.readInt());
      return true;
    }
    
    private static class Proxy
      implements TextUnderstanderListener
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
        return "com.iflytek.speech.TextUnderstanderListener";
      }
      
      public void onError(int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.TextUnderstanderListener");
          localParcel.writeInt(paramInt);
          this.mRemote.transact(2, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      /* Error */
      public void onResult(UnderstanderResult paramUnderstanderResult)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: aload_2
        //   5: ldc 26
        //   7: invokevirtual 40	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   10: aload_1
        //   11: ifnull +33 -> 44
        //   14: aload_2
        //   15: iconst_1
        //   16: invokevirtual 43	android/os/Parcel:writeInt	(I)V
        //   19: aload_1
        //   20: aload_2
        //   21: iconst_0
        //   22: invokevirtual 61	com/iflytek/speech/UnderstanderResult:writeToParcel	(Landroid/os/Parcel;I)V
        //   25: aload_0
        //   26: getfield 19	com/iflytek/speech/TextUnderstanderListener$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   29: iconst_1
        //   30: aload_2
        //   31: aconst_null
        //   32: iconst_1
        //   33: invokeinterface 49 5 0
        //   38: pop
        //   39: aload_2
        //   40: invokevirtual 52	android/os/Parcel:recycle	()V
        //   43: return
        //   44: aload_2
        //   45: iconst_0
        //   46: invokevirtual 43	android/os/Parcel:writeInt	(I)V
        //   49: goto -24 -> 25
        //   52: astore_1
        //   53: aload_2
        //   54: invokevirtual 52	android/os/Parcel:recycle	()V
        //   57: aload_1
        //   58: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	59	0	this	Proxy
        //   0	59	1	paramUnderstanderResult	UnderstanderResult
        //   3	51	2	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   4	10	52	finally
        //   14	25	52	finally
        //   25	39	52	finally
        //   44	49	52	finally
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\TextUnderstanderListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */