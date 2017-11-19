package com.iflytek.speech.aidl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.iflytek.speech.TextUnderstanderListener;
import com.iflytek.speech.TextUnderstanderListener.Stub;

public abstract interface ITextUnderstander
  extends IInterface
{
  public abstract void cancel(TextUnderstanderListener paramTextUnderstanderListener)
    throws RemoteException;
  
  public abstract boolean isUnderstanding()
    throws RemoteException;
  
  public abstract void understandText(Intent paramIntent, TextUnderstanderListener paramTextUnderstanderListener)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements ITextUnderstander
  {
    private static final String DESCRIPTOR = "com.iflytek.speech.aidl.ITextUnderstander";
    static final int TRANSACTION_cancel = 2;
    static final int TRANSACTION_isUnderstanding = 3;
    static final int TRANSACTION_understandText = 1;
    
    public Stub()
    {
      attachInterface(this, "com.iflytek.speech.aidl.ITextUnderstander");
    }
    
    public static ITextUnderstander asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.iflytek.speech.aidl.ITextUnderstander");
      if ((localIInterface != null) && ((localIInterface instanceof ITextUnderstander))) {
        return (ITextUnderstander)localIInterface;
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
        paramParcel2.writeString("com.iflytek.speech.aidl.ITextUnderstander");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ITextUnderstander");
        if (paramParcel1.readInt() != 0) {}
        for (Intent localIntent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);; localIntent = null)
        {
          understandText(localIntent, TextUnderstanderListener.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 2: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ITextUnderstander");
        cancel(TextUnderstanderListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.iflytek.speech.aidl.ITextUnderstander");
      boolean bool = isUnderstanding();
      paramParcel2.writeNoException();
      if (bool) {}
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        paramParcel2.writeInt(paramInt1);
        return true;
      }
    }
    
    private static class Proxy
      implements ITextUnderstander
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
      
      /* Error */
      public void cancel(TextUnderstanderListener paramTextUnderstanderListener)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 34
        //   11: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 42 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 45	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/iflytek/speech/aidl/ITextUnderstander$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   34: iconst_2
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 57	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 57	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 57	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 57	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	Proxy
        //   0	73	1	paramTextUnderstanderListener	TextUnderstanderListener
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.iflytek.speech.aidl.ITextUnderstander";
      }
      
      public boolean isUnderstanding()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ITextUnderstander");
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void understandText(Intent paramIntent, TextUnderstanderListener paramTextUnderstanderListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ITextUnderstander");
            if (paramIntent != null)
            {
              localParcel1.writeInt(1);
              paramIntent.writeToParcel(localParcel1, 0);
              if (paramTextUnderstanderListener != null)
              {
                paramIntent = paramTextUnderstanderListener.asBinder();
                localParcel1.writeStrongBinder(paramIntent);
                this.mRemote.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramIntent = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\aidl\ITextUnderstander.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */