package com.iflytek.speech.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.iflytek.speech.VerifierListener;
import com.iflytek.speech.VerifierListener.Stub;

public abstract interface ISpeakerVerifier
  extends IInterface
{
  public abstract void endSpeak()
    throws RemoteException;
  
  public abstract String getParameter(String paramString)
    throws RemoteException;
  
  public abstract int register(String paramString1, String paramString2, VerifierListener paramVerifierListener)
    throws RemoteException;
  
  public abstract int setParameter(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void stopSpeak()
    throws RemoteException;
  
  public abstract int verify(String paramString1, String paramString2, VerifierListener paramVerifierListener)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements ISpeakerVerifier
  {
    private static final String DESCRIPTOR = "com.iflytek.speech.aidl.ISpeakerVerifier";
    static final int TRANSACTION_endSpeak = 3;
    static final int TRANSACTION_getParameter = 6;
    static final int TRANSACTION_register = 2;
    static final int TRANSACTION_setParameter = 5;
    static final int TRANSACTION_stopSpeak = 4;
    static final int TRANSACTION_verify = 1;
    
    public Stub()
    {
      attachInterface(this, "com.iflytek.speech.aidl.ISpeakerVerifier");
    }
    
    public static ISpeakerVerifier asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.iflytek.speech.aidl.ISpeakerVerifier");
      if ((localIInterface != null) && ((localIInterface instanceof ISpeakerVerifier))) {
        return (ISpeakerVerifier)localIInterface;
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
        paramParcel2.writeString("com.iflytek.speech.aidl.ISpeakerVerifier");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeakerVerifier");
        paramInt1 = verify(paramParcel1.readString(), paramParcel1.readString(), VerifierListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeakerVerifier");
        paramInt1 = register(paramParcel1.readString(), paramParcel1.readString(), VerifierListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeakerVerifier");
        endSpeak();
        paramParcel2.writeNoException();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeakerVerifier");
        stopSpeak();
        paramParcel2.writeNoException();
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeakerVerifier");
        paramInt1 = setParameter(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      }
      paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeakerVerifier");
      paramParcel1 = getParameter(paramParcel1.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    }
    
    private static class Proxy
      implements ISpeakerVerifier
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
      
      public void endSpeak()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeakerVerifier");
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.iflytek.speech.aidl.ISpeakerVerifier";
      }
      
      public String getParameter(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeakerVerifier");
          localParcel1.writeString(paramString);
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.readString();
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public int register(String paramString1, String paramString2, VerifierListener paramVerifierListener)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 33
        //   14: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 5
        //   19: aload_1
        //   20: invokevirtual 57	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   23: aload 5
        //   25: aload_2
        //   26: invokevirtual 57	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   29: aload_3
        //   30: ifnull +57 -> 87
        //   33: aload_3
        //   34: invokeinterface 66 1 0
        //   39: astore_1
        //   40: aload 5
        //   42: aload_1
        //   43: invokevirtual 69	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   46: aload_0
        //   47: getfield 19	com/iflytek/speech/aidl/ISpeakerVerifier$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   50: iconst_2
        //   51: aload 5
        //   53: aload 6
        //   55: iconst_0
        //   56: invokeinterface 43 5 0
        //   61: pop
        //   62: aload 6
        //   64: invokevirtual 46	android/os/Parcel:readException	()V
        //   67: aload 6
        //   69: invokevirtual 73	android/os/Parcel:readInt	()I
        //   72: istore 4
        //   74: aload 6
        //   76: invokevirtual 49	android/os/Parcel:recycle	()V
        //   79: aload 5
        //   81: invokevirtual 49	android/os/Parcel:recycle	()V
        //   84: iload 4
        //   86: ireturn
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -49 -> 40
        //   92: astore_1
        //   93: aload 6
        //   95: invokevirtual 49	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: invokevirtual 49	android/os/Parcel:recycle	()V
        //   103: aload_1
        //   104: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	Proxy
        //   0	105	1	paramString1	String
        //   0	105	2	paramString2	String
        //   0	105	3	paramVerifierListener	VerifierListener
        //   72	13	4	i	int
        //   3	96	5	localParcel1	Parcel
        //   8	86	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	29	92	finally
        //   33	40	92	finally
        //   40	74	92	finally
      }
      
      public int setParameter(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeakerVerifier");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void stopSpeak()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeakerVerifier");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public int verify(String paramString1, String paramString2, VerifierListener paramVerifierListener)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 33
        //   14: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 5
        //   19: aload_1
        //   20: invokevirtual 57	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   23: aload 5
        //   25: aload_2
        //   26: invokevirtual 57	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   29: aload_3
        //   30: ifnull +57 -> 87
        //   33: aload_3
        //   34: invokeinterface 66 1 0
        //   39: astore_1
        //   40: aload 5
        //   42: aload_1
        //   43: invokevirtual 69	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   46: aload_0
        //   47: getfield 19	com/iflytek/speech/aidl/ISpeakerVerifier$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   50: iconst_1
        //   51: aload 5
        //   53: aload 6
        //   55: iconst_0
        //   56: invokeinterface 43 5 0
        //   61: pop
        //   62: aload 6
        //   64: invokevirtual 46	android/os/Parcel:readException	()V
        //   67: aload 6
        //   69: invokevirtual 73	android/os/Parcel:readInt	()I
        //   72: istore 4
        //   74: aload 6
        //   76: invokevirtual 49	android/os/Parcel:recycle	()V
        //   79: aload 5
        //   81: invokevirtual 49	android/os/Parcel:recycle	()V
        //   84: iload 4
        //   86: ireturn
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -49 -> 40
        //   92: astore_1
        //   93: aload 6
        //   95: invokevirtual 49	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: invokevirtual 49	android/os/Parcel:recycle	()V
        //   103: aload_1
        //   104: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	Proxy
        //   0	105	1	paramString1	String
        //   0	105	2	paramString2	String
        //   0	105	3	paramVerifierListener	VerifierListener
        //   72	13	4	i	int
        //   3	96	5	localParcel1	Parcel
        //   8	86	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	29	92	finally
        //   33	40	92	finally
        //   40	74	92	finally
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\aidl\ISpeakerVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */