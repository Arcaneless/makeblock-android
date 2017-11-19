package com.iflytek.speech;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface RecognizerListener
  extends IInterface
{
  public abstract void onBeginOfSpeech()
    throws RemoteException;
  
  public abstract void onEndOfSpeech()
    throws RemoteException;
  
  public abstract void onError(int paramInt)
    throws RemoteException;
  
  public abstract void onEvent(int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onResult(RecognizerResult paramRecognizerResult, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void onVolumeChanged(int paramInt, byte[] paramArrayOfByte)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements RecognizerListener
  {
    private static final String DESCRIPTOR = "com.iflytek.speech.RecognizerListener";
    static final int TRANSACTION_onBeginOfSpeech = 2;
    static final int TRANSACTION_onEndOfSpeech = 3;
    static final int TRANSACTION_onError = 5;
    static final int TRANSACTION_onEvent = 6;
    static final int TRANSACTION_onResult = 4;
    static final int TRANSACTION_onVolumeChanged = 1;
    
    public Stub()
    {
      attachInterface(this, "com.iflytek.speech.RecognizerListener");
    }
    
    public static RecognizerListener asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.iflytek.speech.RecognizerListener");
      if ((localIInterface != null) && ((localIInterface instanceof RecognizerListener))) {
        return (RecognizerListener)localIInterface;
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
      Object localObject2 = null;
      Object localObject1 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.iflytek.speech.RecognizerListener");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.iflytek.speech.RecognizerListener");
        onVolumeChanged(paramParcel1.readInt(), paramParcel1.createByteArray());
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.iflytek.speech.RecognizerListener");
        onBeginOfSpeech();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.iflytek.speech.RecognizerListener");
        onEndOfSpeech();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.iflytek.speech.RecognizerListener");
        paramParcel2 = (Parcel)localObject1;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (RecognizerResult)RecognizerResult.CREATOR.createFromParcel(paramParcel1);
        }
        if (paramParcel1.readInt() != 0) {}
        for (boolean bool = true;; bool = false)
        {
          onResult(paramParcel2, bool);
          return true;
        }
      case 5: 
        paramParcel1.enforceInterface("com.iflytek.speech.RecognizerListener");
        onError(paramParcel1.readInt());
        return true;
      }
      paramParcel1.enforceInterface("com.iflytek.speech.RecognizerListener");
      paramInt1 = paramParcel1.readInt();
      paramInt2 = paramParcel1.readInt();
      int i = paramParcel1.readInt();
      paramParcel2 = (Parcel)localObject2;
      if (paramParcel1.readInt() != 0) {
        paramParcel2 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
      }
      onEvent(paramInt1, paramInt2, i, paramParcel2);
      return true;
    }
    
    private static class Proxy
      implements RecognizerListener
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
        return "com.iflytek.speech.RecognizerListener";
      }
      
      public void onBeginOfSpeech()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.RecognizerListener");
          this.mRemote.transact(2, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void onEndOfSpeech()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.RecognizerListener");
          this.mRemote.transact(3, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void onError(int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.RecognizerListener");
          localParcel.writeInt(paramInt);
          this.mRemote.transact(5, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      /* Error */
      public void onEvent(int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 35	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: aload 5
        //   7: ldc 26
        //   9: invokevirtual 39	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload 5
        //   14: iload_1
        //   15: invokevirtual 55	android/os/Parcel:writeInt	(I)V
        //   18: aload 5
        //   20: iload_2
        //   21: invokevirtual 55	android/os/Parcel:writeInt	(I)V
        //   24: aload 5
        //   26: iload_3
        //   27: invokevirtual 55	android/os/Parcel:writeInt	(I)V
        //   30: aload 4
        //   32: ifnull +39 -> 71
        //   35: aload 5
        //   37: iconst_1
        //   38: invokevirtual 55	android/os/Parcel:writeInt	(I)V
        //   41: aload 4
        //   43: aload 5
        //   45: iconst_0
        //   46: invokevirtual 63	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   49: aload_0
        //   50: getfield 19	com/iflytek/speech/RecognizerListener$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   53: bipush 6
        //   55: aload 5
        //   57: aconst_null
        //   58: iconst_1
        //   59: invokeinterface 45 5 0
        //   64: pop
        //   65: aload 5
        //   67: invokevirtual 48	android/os/Parcel:recycle	()V
        //   70: return
        //   71: aload 5
        //   73: iconst_0
        //   74: invokevirtual 55	android/os/Parcel:writeInt	(I)V
        //   77: goto -28 -> 49
        //   80: astore 4
        //   82: aload 5
        //   84: invokevirtual 48	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramInt1	int
        //   0	90	2	paramInt2	int
        //   0	90	3	paramInt3	int
        //   0	90	4	paramBundle	Bundle
        //   3	80	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   5	30	80	finally
        //   35	49	80	finally
        //   49	65	80	finally
        //   71	77	80	finally
      }
      
      public void onResult(RecognizerResult paramRecognizerResult, boolean paramBoolean)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel.writeInterfaceToken("com.iflytek.speech.RecognizerListener");
            if (paramRecognizerResult != null)
            {
              localParcel.writeInt(1);
              paramRecognizerResult.writeToParcel(localParcel, 0);
              break label83;
              localParcel.writeInt(i);
              this.mRemote.transact(4, localParcel, null, 1);
            }
            else
            {
              localParcel.writeInt(0);
            }
          }
          finally
          {
            localParcel.recycle();
          }
          label83:
          do
          {
            i = 0;
            break;
          } while (!paramBoolean);
        }
      }
      
      public void onVolumeChanged(int paramInt, byte[] paramArrayOfByte)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.RecognizerListener");
          localParcel.writeInt(paramInt);
          localParcel.writeByteArray(paramArrayOfByte);
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\RecognizerListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */