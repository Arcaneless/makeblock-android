package com.iflytek.speech;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface SynthesizerListener
  extends IInterface
{
  public abstract void onBufferProgress(int paramInt1, int paramInt2, int paramInt3, String paramString)
    throws RemoteException;
  
  public abstract void onCompleted(int paramInt)
    throws RemoteException;
  
  public abstract void onEvent(int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onSpeakBegin()
    throws RemoteException;
  
  public abstract void onSpeakPaused()
    throws RemoteException;
  
  public abstract void onSpeakProgress(int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;
  
  public abstract void onSpeakResumed()
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements SynthesizerListener
  {
    private static final String DESCRIPTOR = "com.iflytek.speech.SynthesizerListener";
    static final int TRANSACTION_onBufferProgress = 6;
    static final int TRANSACTION_onCompleted = 4;
    static final int TRANSACTION_onEvent = 7;
    static final int TRANSACTION_onSpeakBegin = 1;
    static final int TRANSACTION_onSpeakPaused = 2;
    static final int TRANSACTION_onSpeakProgress = 5;
    static final int TRANSACTION_onSpeakResumed = 3;
    
    public Stub()
    {
      attachInterface(this, "com.iflytek.speech.SynthesizerListener");
    }
    
    public static SynthesizerListener asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.iflytek.speech.SynthesizerListener");
      if ((localIInterface != null) && ((localIInterface instanceof SynthesizerListener))) {
        return (SynthesizerListener)localIInterface;
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
        paramParcel2.writeString("com.iflytek.speech.SynthesizerListener");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.iflytek.speech.SynthesizerListener");
        onSpeakBegin();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.iflytek.speech.SynthesizerListener");
        onSpeakPaused();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.iflytek.speech.SynthesizerListener");
        onSpeakResumed();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.iflytek.speech.SynthesizerListener");
        onCompleted(paramParcel1.readInt());
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.iflytek.speech.SynthesizerListener");
        onSpeakProgress(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.iflytek.speech.SynthesizerListener");
        onBufferProgress(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
        return true;
      }
      paramParcel1.enforceInterface("com.iflytek.speech.SynthesizerListener");
      paramInt1 = paramParcel1.readInt();
      paramInt2 = paramParcel1.readInt();
      int i = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        onEvent(paramInt1, paramInt2, i, paramParcel1);
        return true;
      }
    }
    
    private static class Proxy
      implements SynthesizerListener
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
        return "com.iflytek.speech.SynthesizerListener";
      }
      
      public void onBufferProgress(int paramInt1, int paramInt2, int paramInt3, String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.SynthesizerListener");
          localParcel.writeInt(paramInt1);
          localParcel.writeInt(paramInt2);
          localParcel.writeInt(paramInt3);
          localParcel.writeString(paramString);
          this.mRemote.transact(6, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void onCompleted(int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.SynthesizerListener");
          localParcel.writeInt(paramInt);
          this.mRemote.transact(4, localParcel, null, 1);
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
        //   0: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: aload 5
        //   7: ldc 26
        //   9: invokevirtual 40	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload 5
        //   14: iload_1
        //   15: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   18: aload 5
        //   20: iload_2
        //   21: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   24: aload 5
        //   26: iload_3
        //   27: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   30: aload 4
        //   32: ifnull +39 -> 71
        //   35: aload 5
        //   37: iconst_1
        //   38: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   41: aload 4
        //   43: aload 5
        //   45: iconst_0
        //   46: invokevirtual 66	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   49: aload_0
        //   50: getfield 19	com/iflytek/speech/SynthesizerListener$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   53: bipush 7
        //   55: aload 5
        //   57: aconst_null
        //   58: iconst_1
        //   59: invokeinterface 53 5 0
        //   64: pop
        //   65: aload 5
        //   67: invokevirtual 56	android/os/Parcel:recycle	()V
        //   70: return
        //   71: aload 5
        //   73: iconst_0
        //   74: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   77: goto -28 -> 49
        //   80: astore 4
        //   82: aload 5
        //   84: invokevirtual 56	android/os/Parcel:recycle	()V
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
      
      public void onSpeakBegin()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.SynthesizerListener");
          this.mRemote.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void onSpeakPaused()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.SynthesizerListener");
          this.mRemote.transact(2, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void onSpeakProgress(int paramInt1, int paramInt2, int paramInt3)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.SynthesizerListener");
          localParcel.writeInt(paramInt1);
          localParcel.writeInt(paramInt2);
          localParcel.writeInt(paramInt3);
          this.mRemote.transact(5, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void onSpeakResumed()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.iflytek.speech.SynthesizerListener");
          this.mRemote.transact(3, localParcel, null, 1);
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\SynthesizerListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */