package com.iflytek.speech.aidl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.iflytek.speech.SynthesizerListener;
import com.iflytek.speech.SynthesizerListener.Stub;

public abstract interface ISpeechSynthesizer
  extends IInterface
{
  public abstract String getLocalSpeakerList()
    throws RemoteException;
  
  public abstract boolean isSpeaking()
    throws RemoteException;
  
  public abstract int pauseSpeaking(SynthesizerListener paramSynthesizerListener)
    throws RemoteException;
  
  public abstract int resumeSpeaking(SynthesizerListener paramSynthesizerListener)
    throws RemoteException;
  
  public abstract int startSpeaking(Intent paramIntent, SynthesizerListener paramSynthesizerListener)
    throws RemoteException;
  
  public abstract int stopSpeaking(SynthesizerListener paramSynthesizerListener)
    throws RemoteException;
  
  public abstract int synthesizeToUrl(Intent paramIntent, SynthesizerListener paramSynthesizerListener)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements ISpeechSynthesizer
  {
    private static final String DESCRIPTOR = "com.iflytek.speech.aidl.ISpeechSynthesizer";
    static final int TRANSACTION_getLocalSpeakerList = 7;
    static final int TRANSACTION_isSpeaking = 6;
    static final int TRANSACTION_pauseSpeaking = 3;
    static final int TRANSACTION_resumeSpeaking = 4;
    static final int TRANSACTION_startSpeaking = 2;
    static final int TRANSACTION_stopSpeaking = 5;
    static final int TRANSACTION_synthesizeToUrl = 1;
    
    public Stub()
    {
      attachInterface(this, "com.iflytek.speech.aidl.ISpeechSynthesizer");
    }
    
    public static ISpeechSynthesizer asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.iflytek.speech.aidl.ISpeechSynthesizer");
      if ((localIInterface != null) && ((localIInterface instanceof ISpeechSynthesizer))) {
        return (ISpeechSynthesizer)localIInterface;
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
        paramParcel2.writeString("com.iflytek.speech.aidl.ISpeechSynthesizer");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechSynthesizer");
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        paramInt1 = synthesizeToUrl((Intent)localObject1, SynthesizerListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechSynthesizer");
        localObject1 = localObject2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        paramInt1 = startSpeaking((Intent)localObject1, SynthesizerListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechSynthesizer");
        paramInt1 = pauseSpeaking(SynthesizerListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechSynthesizer");
        paramInt1 = resumeSpeaking(SynthesizerListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechSynthesizer");
        paramInt1 = stopSpeaking(SynthesizerListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechSynthesizer");
        boolean bool = isSpeaking();
        paramParcel2.writeNoException();
        if (bool) {}
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          paramParcel2.writeInt(paramInt1);
          return true;
        }
      }
      paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechSynthesizer");
      paramParcel1 = getLocalSpeakerList();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    }
    
    private static class Proxy
      implements ISpeechSynthesizer
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
        return "com.iflytek.speech.aidl.ISpeechSynthesizer";
      }
      
      public String getLocalSpeakerList()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeechSynthesizer");
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
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
      
      public boolean isSpeaking()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeechSynthesizer");
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
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
      
      /* Error */
      public int pauseSpeaking(SynthesizerListener paramSynthesizerListener)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 35	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 35	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 26
        //   12: invokevirtual 39	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 67 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 70	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_0
        //   32: getfield 19	com/iflytek/speech/aidl/ISpeechSynthesizer$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   35: iconst_3
        //   36: aload_3
        //   37: aload 4
        //   39: iconst_0
        //   40: invokeinterface 45 5 0
        //   45: pop
        //   46: aload 4
        //   48: invokevirtual 48	android/os/Parcel:readException	()V
        //   51: aload 4
        //   53: invokevirtual 61	android/os/Parcel:readInt	()I
        //   56: istore_2
        //   57: aload 4
        //   59: invokevirtual 54	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 54	android/os/Parcel:recycle	()V
        //   66: iload_2
        //   67: ireturn
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 54	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 54	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramSynthesizerListener	SynthesizerListener
        //   56	11	2	i	int
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	57	73	finally
      }
      
      /* Error */
      public int resumeSpeaking(SynthesizerListener paramSynthesizerListener)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 35	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 35	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 26
        //   12: invokevirtual 39	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 67 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 70	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_0
        //   32: getfield 19	com/iflytek/speech/aidl/ISpeechSynthesizer$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   35: iconst_4
        //   36: aload_3
        //   37: aload 4
        //   39: iconst_0
        //   40: invokeinterface 45 5 0
        //   45: pop
        //   46: aload 4
        //   48: invokevirtual 48	android/os/Parcel:readException	()V
        //   51: aload 4
        //   53: invokevirtual 61	android/os/Parcel:readInt	()I
        //   56: istore_2
        //   57: aload 4
        //   59: invokevirtual 54	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 54	android/os/Parcel:recycle	()V
        //   66: iload_2
        //   67: ireturn
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 54	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 54	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramSynthesizerListener	SynthesizerListener
        //   56	11	2	i	int
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	57	73	finally
      }
      
      public int startSpeaking(Intent paramIntent, SynthesizerListener paramSynthesizerListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeechSynthesizer");
            if (paramIntent != null)
            {
              localParcel1.writeInt(1);
              paramIntent.writeToParcel(localParcel1, 0);
              if (paramSynthesizerListener != null)
              {
                paramIntent = paramSynthesizerListener.asBinder();
                localParcel1.writeStrongBinder(paramIntent);
                this.mRemote.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
                int i = localParcel2.readInt();
                return i;
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
      
      /* Error */
      public int stopSpeaking(SynthesizerListener paramSynthesizerListener)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 35	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 35	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 26
        //   12: invokevirtual 39	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 67 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 70	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_0
        //   32: getfield 19	com/iflytek/speech/aidl/ISpeechSynthesizer$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   35: iconst_5
        //   36: aload_3
        //   37: aload 4
        //   39: iconst_0
        //   40: invokeinterface 45 5 0
        //   45: pop
        //   46: aload 4
        //   48: invokevirtual 48	android/os/Parcel:readException	()V
        //   51: aload 4
        //   53: invokevirtual 61	android/os/Parcel:readInt	()I
        //   56: istore_2
        //   57: aload 4
        //   59: invokevirtual 54	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 54	android/os/Parcel:recycle	()V
        //   66: iload_2
        //   67: ireturn
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 54	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 54	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramSynthesizerListener	SynthesizerListener
        //   56	11	2	i	int
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	57	73	finally
      }
      
      public int synthesizeToUrl(Intent paramIntent, SynthesizerListener paramSynthesizerListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeechSynthesizer");
            if (paramIntent != null)
            {
              localParcel1.writeInt(1);
              paramIntent.writeToParcel(localParcel1, 0);
              if (paramSynthesizerListener != null)
              {
                paramIntent = paramSynthesizerListener.asBinder();
                localParcel1.writeStrongBinder(paramIntent);
                this.mRemote.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
                int i = localParcel2.readInt();
                return i;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\aidl\ISpeechSynthesizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */